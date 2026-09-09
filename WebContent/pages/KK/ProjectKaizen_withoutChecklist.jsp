<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm("frmProject");
	jQuery('#submitForm').val('frmProject');
	var type = jQuery("#hdntype").val();
	
	
	disableUIButton("btnPrjYYLink");
	disableUIButton("btnFishBone");
	formatDateBox('dteKzpmStartdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmEnddate','dd-MMM-yyyy');
	readOnlyFields("dteKzpmStartdate");
	if(type=="project"){		
		jQuery("#Stagesbutton").hide();
		jQuery("#linkbutton").hide();
		jQuery("#projectcreation").hide();
		jQuery("#DMAIC").hide();
		enableFields("dteKzpmStartdate");
	}
	numericTextBox("txtKzpmBenefits");
	fillComboBox("frmProject","cmbKzpmProjectchamp","employee.commonFilter" );
	fillComboBox("frmProject","cmbKzpmImprCategory","kaizenCategory.commonFilter");
	
	fillComboBox("frmProject","cmbKzpmProjectmetrics","combo_KPIIndicator.prpo");

	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	processGridnew("projectsproto_input.prpo","q=2&keyid="+masterkeyid,"resourcesgrid","pager","","ResouredoubleClick","","loadResource_complete");
	processGridnew("projectsdmaic_input.prpo","q=2&keyid="+masterkeyid,"dmaicgrid","","","","","damicOnLoadComplete");
	processGridnew("projectsmile_input.prpo","q=2&keyid="+masterkeyid,"fourgrid","pagergrid","","doubleClickMile","","load_complete");
	processGridnew("projectsKpi_input.prpo","q=2&masterkeyid="+masterkeyid,"Kpigrid","pagerKpi","","","","loadComplKPI");
	var factId = jQuery("#frmProject input[id='factory']").val();
	var sectionId = jQuery("#frmProject input[id='section']").val();
	var cellId = jQuery("#frmProject input[id='cell']").val();
	var machId = jQuery("#frmProject input[id='machine']").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject",dataStr);
	processGridnew("projectskaizen_input.prpo","q=2&flid="+flid+"&master="+masterkeyid,"Kaizengrid","pagerkaizen","","","","load_complete");
	fileManagerPopUp(masterkeyid,"PRO","frmProject","btnfilemgr","abnFilemgr");
	//alert('flid:'+flid);
	loadDefineStage();	 
	jQuery("#btnAddMst").click(function(){
		addMileStone();
	});
	if(jQuery("#hdnIsClosure").val()=="y"){		
		getClosureStage();
	}	

	if (jQuery("#mode").val()=="view"){
		disableProject();
	}
	else if (jQuery("#mode").val() == "update"){
		projectDefineMandatory();
	}	
	getBenefits();
	jQuery("#cmbKzpmProjectchamp").combobox({onRequest:function( ){	    
		var flid = jQuery("#frmProject input[id='flid']").val();
		return "&isPbuHead=Y&flid="+flid ;
	}});

	if( jQuery('#chkKzpmIstangible').is(":checked"))
		jQuery("#lblAmount").addClass("mandatory-lbl");
	//if( jQuery('#chkKzpmIsintangible').is(":checked"))
	//	jQuery("#lblImprCategory").addClass("mandatory-lbl");
	
	jQuery('#chkKzpmIstangible').click(function() {
		
		if(jQuery(this).is(":checked")){
			jQuery("#lblAmount").addClass("mandatory-lbl");	
		}
		else
			jQuery("#lblAmount").removeClass("mandatory-lbl");
	});	
	
/*	jQuery('#chkKzpmIsintangible').click(function() {
		
		if(jQuery(this).is(":checked")){
			jQuery("#lblImprCategory").addClass("mandatory-lbl");	
		}
		else
			jQuery("#lblImprCategory").removeClass("mandatory-lbl");
	}); */	
});

function addMileStone(){
	if(jQuery("#mode").val()!="view"){
		var mode =  loadStagesMaic("add","");
		//alert("mode "+ mode);
		if (mode=="add" || mode=="modify" || mode=="view"){	
			var currStage=getCurrentStage();
			//alert("currStage "+ currStage);
			var keyId="";
			if(currStage.trim().length>0){
				keyId=getCurrentStageKeyId(currStage);
				//alert("keyId "+ keyId);
			}
			LoadPopUp("divMilestonePop", "milestone_view.prpo?q=2&mode="+mode+"&Keyid="+keyId, true,"90%","80%","60px","7%", "multiSelectOk_Callback","MilesStone"," ",true);
		}
		else{
			setTimeout(function() {
				showCommonErrorMsg(mode);
			}, 200);
			div_err();
			return false;
		}
	}
}

function getCurrentStageKeyId(currStage){
	var jqGridId="fourgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var keyid ="";
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;
			var mileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');	
			//alert("mileStoneStage:"+mileStoneStage);
			//alert("currStage:"+currStage);
			if(mileStoneStage==currStage){	
				keyid = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Keyid');		
				return keyid;
			}			
		}
	}
	
	return keyid;
}

function getCurrentStage(){	
	var dStatus=jQuery("#hdnKzpmDefinestage").val();
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var xStatus=jQuery("#hdnKzpmClosurestage").val();
	var currStage="";
	if(mStatus=="-" || mStatus=="P" || mStatus=="I"){
		currStage="Measure";
	}
	else if(aStatus=="-" || aStatus=="P" || aStatus=="I"){
		currStage="Analyse";
	}
	else if(iStatus=="-" || iStatus=="L" || iStatus=="P" || iStatus=="I"){
		currStage="Improve";
	}
	else if(cStatus=="-" || cStatus=="L" ||  cStatus=="P" || cStatus=="I"){
		currStage="Control";
	}
	else if(cStatus == "C" && ( xStatus == "-" || xStatus=="L" || xStatus == "P" || xStatus=="I") )
		currStage="Closure";
	return currStage;
}

function frmProject_beforeSubmit()
{
	if (jQuery("#mode").val()=="view"){
		return false;
	}	
	return true;
}

function disableProject(){
	readOnlyFields("txtKzpmProjectname");
	readOnlyFields("txtKzpmArea");
	readOnlyFields("txtKzpmBenefits");
	readOnlyFields("txtKzpmSavings");
	readOnlyFields("cmbKzpmProjectmetrics");
	readOnlyFields("txtKzpmProblemstatement");
	readOnlyFields("txtKzpmGoalobj");
	readOnlyFields("txtKzpmScopeconst");
	readOnlyFields("cmbKzpmProjectchamp");
	readOnlyFields("txtKzpmBusinesscase");
	readOnlyFields("dteKzpmStartdate");
	readOnlyFields("dteKzpmEnddate");
	readOnlyFields("cmbKzpmImprCategory");

	readOnlyFields("chkKzpmIstangible");
	readOnlyFields("chkKzpmIsintangible");
}

function loadComplKPI() { 
	var define=jQuery("#hdnKzpmDefinestage").val();
	if(define=="C"){
		 jQuery("#DefineStage").attr("disabled","disabled");		
		 
		 setTimeout(function() {
			 jQuery('.divDefApproval').before('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:90%;background:red;"> </div>');
		}, 500);
	 }
}

function projectDefineMandatory(){
	jQuery("#lblKzpmProjectmetrics").addClass("mandatory-lbl");
	jQuery("#lblKzpmProblemstatement").addClass("mandatory-lbl");

	jQuery("#lblKzpmBusinesscase").addClass("mandatory-lbl");
	jQuery("#lblKzpmGoalobj").addClass("mandatory-lbl");
	
	jQuery("#lblKzpmSavings").addClass("mandatory-lbl");	
}
function resourcesgrid_selectRow(rowId)
{
	var jqGridId="resourcesgrid";	
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKprlKeyid');	
	
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{		
		//alert("un");	
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert("check");
		var hrsEstimate = getFieldValue('txtKprlHrsestimate_'+jqGridId+'_'+rowId);
		if(hrsEstimate.trim().length==0)
     		setFieldValue('txtKprlHrsestimate_'+jqGridId+'_'+rowId,"");
		jQuery("#cmbEmpName_"+jqGridId+"_"+rowId).attr('disabled','disabled');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');			
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
}

jQuery("#btnAddNew").click(function(){
	if(jQuery("#mode").val()!="view"){
		var row = jQuery("#resourcesgrid").jqGrid("getDataIDs");
		var kzpmKeyid = jQuery("#hdnKzpmKeyid").val();
		var flid = jQuery("#frmProject input[id='flid']").val();
		LoadPopUp("divAddResource","addResourcesList_input.prpo?&kzpmKeyid="+kzpmKeyid+"&flid="+flid,true,"45%","78%","0px","20%", "multiSelectOk_Callback","Resources List");
	}
	//addRow(row);
});
function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
	 	var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:" ",cmbEmpName:" ",txtKprlLeadMemb:" ",cmbLeadMemb:" ",txtKprlRoleKeyid:" ",txtKprlHrsestimate:" "}];
		jQuery("#resourcesgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
			lastRow = row[i];

		var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:" ",cmbEmpName:" ",txtKprlLeadMemb:" ",cmbLeadMemb:" ",txtKprlRoleKeyid:" ",txtKprlHrsestimate:" "}];
		jQuery("#resourcesgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}
function saveResources()
{		  
	var flg=false;
	var errText="";
	var jqGridId="resourcesgrid";
	var gridval=getGridSelectArray('resourcesgrid');  
    var gridData  = '&resourcedtls='+gridval;	
    //alert('gridData:'+gridData);
    var gridvaldel=getGridUnselectedKeyids('resourcesgrid','txtKprlKeyid','hdnIsDelete');
    gridData += '&resourcedtlsdel='+gridvaldel;
    //alert('gridData:'+gridData); 
    if (gridval.trim().length==0 && gridvaldel.trim().length==0) {
    	setTimeout(function() {
			showCommonErrorMsg('No Resources Selected To Save');
		}, 200);
		div_err();		
        return false;
    } 
    //alert('gridval:'+gridval);
    return gridData;
}
function saveDMAICStatus(){
	var flg=false;
	var errText="";
	var jqGridId="dmaicgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
				flg=true;
			}	
		}
	}
	
	if (flg==false){
		setTimeout(function() {
			showCommonErrorMsg('Select Any DMAIC Verification Details');
		}, 200);
		div_err();		
        return false;
	}
	
	var gridval=getGridSelectArray('dmaicgrid');  
    var gridData  = '&projectDmaic='+gridval;	
    //alert('gridData:'+gridData);
    var gridvaldel=getGridUnselectedKeyids('dmaicgrid','txtKpdsKeyid','hdnIsDelete');
    gridData += '&projectDmaiceDel='+gridvaldel;
    
    if (gridval.trim().length<=0 && gridvaldel.trim().length<=0) {
    	setTimeout(function() {
			showCommonErrorMsg("Select Any Stage To Save");
		}, 200);
		div_err();		
        return false;
    } 
    //alert('gridval:'+gridval);   
    
	return gridData; 	
}

function getWorkFlowStage(){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var jqGridId="dmaicgrid";
	if (masterkeyid.trim().length>0){	
		var fromDate = "";
		var toDate = "";	
		var mStatus=jQuery("#hdnKzpmMeasurestage").val();
		var aStatus=jQuery("#hdnKzpmAnalysestage").val();
		var iStatus=jQuery("#hdnKzpmImprovestage").val();
		var cStatus=jQuery("#hdnKzpmControlstage").val();
		var flid = jQuery("#frmProject input[id='flid']").val();
		//alert('flid:'+flid);	
		
		
		if(cStatus=="W" || cStatus=="R" || cStatus=="E" || cStatus=="L"){
			fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 4, 'dteKpdsVerifieddate');					
			fromDate=fromDate.replace(" 00:00:00","").trim();	
			//var stage=jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKpdsStage');
			workFlow("divMAICApproval",false,"FIPROCON", masterkeyid, "PROCO",flid,fromDate,toDate);	
		}
		else if(iStatus=="W" || iStatus=="R" || iStatus=="E" || iStatus=="L"){
			fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 3, 'dteKpdsVerifieddate');					
			fromDate=fromDate.replace(" 00:00:00","").trim();	
			workFlow ("divMAICApproval",false,"FIPROIMP", masterkeyid, "PROIM",flid,fromDate,toDate);	
		}
		else if(aStatus=="W" || aStatus=="R" || aStatus=="E" || aStatus=="L" ){
			fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 2, 'dteKpdsVerifieddate');					
			fromDate=fromDate.replace(" 00:00:00","").trim();	
			workFlow ("divMAICApproval",false,"FIPROANA", masterkeyid, "PROAN",flid,fromDate,toDate);			
		}
		else if(mStatus=="W" || mStatus=="R" || mStatus=="E" || mStatus=="L" ){
			fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 1, 'dteKpdsVerifieddate');					
			fromDate=fromDate.replace(" 00:00:00","").trim();	
			workFlow ("divMAICApproval",false,"FIPROMEA", masterkeyid, "PROME",flid,fromDate,toDate);	
		}
		/*else{
			workFlow ("divMAICApproval",false,"FIPRO", masterkeyid, "PRO",flid);
		}*/
	}	
}

function workFlow_Load_callbackOnSuccess(result){
	//alert("workFlow_Load_callbackOnSuccess");	
	//jQuery('#grdWorkFlowAppr').setGridWidth("100%%");
	//jQuery('#grdWorkFlowAppr').setGridHeight("20%%");
	/*setTimeout(function(){
		jQuery('#grdWorkFlowAppr').setGridWidth(1049);
		jQuery('#grdWorkFlowAppr').setGridHeight(150);
	},1150);*/
}

function frmProject_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmProject",'585px');
	//jQuery("#frmProject div[id=dispFunctionalLoc]").css('width','465px');
	jQuery("#cmbKzpmFlid").val(result.flId);	
	reloadCombo("frmProject","cmbKzpmProjectchamp","employee.commonFilter?isPbuHead=Y&flid="+result.flId);
}
function frmProjectcmbKzpmProjectchamp_onLoadSuccess() {
	var fromDate = jQuery("#dteKzpmStartdate").datebox("getValue");
	if (fromDate=='' || fromDate==' ' || fromDate==null)
		fillWithCurrentDate("dteKzpmStartdate");
	setComboDefaultValue("frmProject", "cmbKzpmProjectchamp");
}

function frmProject_successsCallback(result)
{
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	if (masterkeyid.trim().length<=0){
		//alert("Project Number Is "+result.successData.kzpmKeyid);
		jQuery("#hdnKzpmKeyid").val(result.successData.kzpmKeyid);
		jQuery("#txtKzpmProjectno").val(result.successData.kzpmKeyid);		
	}		
	if(result.successData.Type=="fileMng"){		
		fileManagerPopUp(result.successData.kzpmKeyid,"KZP","","","");	
	}
	else{
		//clearForm("frmProject");
		//loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject"," ");
	}	
}

function frmProject_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject"," ");
}

function dmaicgrid_selectRow(rowId)
{	
	var jqGridId="dmaicgrid";
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKpdsKeyid');
	var dateCtrl="dteKpdsVerifieddate_dmaicgrid_"+rowId;
	var stage=jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKpdsStage');
	jQuery("#"+dateCtrl).datebox({  	   
	onSelect:function(recordid)
		{
			//alert("dateCtrl:"+dateCtrl+"stage:"+stage);
			isValidVerifiedDate(dateCtrl,rowId,stage);
		} 
	});
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{			
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert('checked==true');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
		
}
function isValidVerifiedDate(dateCtrl,ctrlRowId,stage){
	var verifiedDate = getFieldValue(dateCtrl);	
	var currentDate = getServerDateTime();	
	var fromDate = jQuery("#dteKzpmStartdate").datebox("getValue");
	var toDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	if (verifiedDate=="undefined" || verifiedDate=="" || verifiedDate==" ") {  
		if(convertStringToDate(verifiedDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	}
	else{
		if(convertStringToDate(verifiedDate) > currentDate)
		{
			alert('Verified Date Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		if(convertStringToDate(verifiedDate) <convertStringToDate(fromDate))
		{
			alert('Verified Date Should Not Less Than Project Start Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		/*if(convertStringToDate(verifiedDate) >convertStringToDate(toDate))
		{
			alert('Verified Date Should Not Exceed Project End Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}*/
		
	}
	
	var jqGridId="fourgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var verifiedDate = jQuery("#"+dateCtrl).datebox("getValue");
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;	
			
				var completedDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'CompletedDate');	
				var maileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');		
				completedDate=completedDate.replace(" 00:00:00","").trim();	
				if(maileStoneStage==stage){		
				if(convertStringToDate(verifiedDate) < convertStringToDate(completedDate))
				{
					alert('Verified Date Should Not Less than MileStone Completed Date');
					fillWithCurrentDate(dateCtrl);
					return false;
				}
			}			
		}
	}
	
	jqGridId="dmaicgrid";	
	allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;			
			var completedDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');	
			var maileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');		
			completedDate=completedDate.replace(" 00:00:00","").trim();	
			if(parseInt(ctrlRowId)>parseInt(rowId)){		
				if(convertStringToDate(verifiedDate) < convertStringToDate(completedDate))
				{
					alert('Verified Date Should Not Less than Previous Verified Date');
					fillWithCurrentDate(dateCtrl);
					return false;
				}
			}						
		}
	}	
}

function damicOnLoadComplete()
{		
	var define=jQuery("#hdnKzpmDefinestage").val();
	var closure=jQuery("#hdnKzpmClosurestage").val();
	
	var jqGridId="dmaicgrid";	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	jQuery("#define").css('background-color','#D9D9DB');
	jQuery("#measure").css('background-color','#D9D9DB');
	jQuery("#Analyse").css('background-color','#D9D9DB');
	jQuery("#Improve").css('background-color','#D9D9DB');
	jQuery("#Control").css('background-color','#D9D9DB');
	jQuery("#Closure").css('background-color','#D9D9DB');

	/** added by prasanth**/
	var cuStage = getCurrentStage();
		if( cuStage != undefined && cuStage != "")
			jQuery('#'+cuStage).css('background-color','#FFA500');
		
	disableUIButton("btnPrjYYLink");
	disableUIButton("btnFishBone");	
	if(define=="P"){
		jQuery("#define").css('background-color','#FAF687');
	}
	else if(define=="C"){
		jQuery("#define").css('background-color','#95FB94');		
	}else if(define=="R" || define=="E"){
		jQuery("#define").css('background-color','#FC6767');
	}
		
	if(closure=="P"){
		jQuery("#Closure").css('background-color','#FAF687');
	}
	else if(closure=="C"){
		jQuery("#Closure").css('background-color','#95FB94');		
	}else if(closure=="R" || closure=="E"){
		jQuery("#Closure").css('background-color','#FC6767');
	}	
	//alert("closure:"+closure);
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var rowId=0;
	var colName="txtKpdsStage";
	rowId=1;
	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(mStatus=="C"){
		jQuery("#measure").css('background-color','#95FB94');
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if( mStatus=="W" || mStatus=="I" || mStatus=="P"){
		jQuery("#measure").css('background-color','#FAF687');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
	}else if(mStatus=="R" || mStatus=="E"){
		jQuery("#measure").css('background-color','#FC6767');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',true);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});
	}
	
	rowId=2;
	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(aStatus=="C"){
		jQuery("#Analyse").css('background-color','#95FB94');
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((mStatus=="C" && aStatus =="-") || aStatus=="W" || aStatus=="I" || aStatus=="P"){
		jQuery("#Analyse").css('background-color','#FAF687');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
		enableUIButton("btnPrjYYLink");
		enableUIButton("btnFishBone");		
	}else if(aStatus=="R" || aStatus=="E"){
		jQuery("#Analyse").css('background-color','#FC6767');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
		enableUIButton("btnPrjYYLink");
		enableUIButton("btnFishBone");	
	}
	rowId=3;
	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(iStatus=="C"){
		jQuery("#Improve").css('background-color','#95FB94');
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((aStatus=="C" && iStatus =="-") || iStatus=="W" || iStatus=="I" || iStatus=="P"){
		jQuery("#Improve").css('background-color','#FAF687');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});			
	}else if(iStatus=="R" || iStatus=="E"){
		jQuery("#Improve").css('background-color','#FC6767');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
	}
	rowId=4;
	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(cStatus=="C"){
		jQuery("#Control").css('background-color','#95FB94');
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((iStatus=="C" && cStatus =="-") ||cStatus=="W" || cStatus=="I" || cStatus=="P"){
		jQuery("#Control").css('background-color','#FAF687');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
	}else if(cStatus=="R" || cStatus=="E"){
		jQuery("#Control").css('background-color','#FC6767');
		jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
		var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
		jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
	}

	
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if( jQuery("#hdntype").val()== "team"){
		if(define=="C"){
			//alert(1);
			jQuery("#DefineStage").hide();
			jQuery("#ClosureStage").hide();
			jQuery("#MaicStage").show();
			jQuery("#divDefApproval").html("");
			jQuery("#divMAICApproval").html("");
			jQuery("#divClosureApproval").html("");
			jQuery("#DefineStage").attr('disabled','disabled');			
		}
		getWorkFlowStage();			
	}
	
	if(jQuery("#hdnIsClosure").val()=="y" || jQuery("#hdnIsClosure").val() == "Y"){		
		getClosureStage();
	}
				
}

function FIPRODEF_successCallback(result){
	//alert("PRODE_successCallback");	
	//alert("result.wfStatus"+result.wfStatus);
	refreshWorkFlowDMAIC("D",result.wfStatus);
}
function FIPROMEA_successCallback(result){
	//alert("PROME_successCallback");
	refreshWorkFlowDMAIC("M",result.wfStatus);
}
function FIPROANA_successCallback(result){
	//alert("PROAN_successCallback");
	refreshWorkFlowDMAIC("A",result.wfStatus);
}
function FIPROIMP_successCallback(result){
	refreshWorkFlowDMAIC("I",result.wfStatus);
}
function FIPROCON_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("C",result.wfStatus);
}
function FIPROCLO_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("X",result.wfStatus);
}

function refreshWorkFlowDMAIC(updateStage,wfStatus){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	processAjaxCalls("getWorkFlowStatus.prpo","updateStage="+updateStage+"&kznKeyId="+masterkeyid+"&wfStatus="+wfStatus,"workFlowStatusSuccess","");
	//refreshForm();
}
jQuery("#MaicStage").hide();
jQuery("#DefineStage").hide();

if(jQuery("#hdntype").val() == "define"){
	jQuery("#DefineStage").show();
	jQuery("#MaicStage").hide();
}
if(jQuery("#hdntype").val() == "team"){
	jQuery("#DefineStage").show();
	//jQuery("#MaicStage").show();
}
/*jQuery("#btnDefineStage").hide();
jQuery("#btnMaicStage").hide();
jQuery("#btnFishBone").hide();
jQuery("#btnPrjYYLink").hide();*/

jQuery("#btnDefineStage").click(function(){ 
	loadDefineStage();
	//loadComplKPI();
	
});

function loadDefineStage(){	
	 jQuery("#DefineStage").show();
	 jQuery("#MaicStage").hide();
	 jQuery("#ClosureStage").hide();
	 jQuery("#divDefApproval").html("");
	 jQuery("#divMAICApproval").html("");
	 jQuery("#divClosureApproval").html("");
	 var define=jQuery("#hdnKzpmDefinestage").val();
	 var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	 var flid = jQuery("#frmProject input[id='flid']").val();
	 //alert('flid:'+flid);	 
	 var fromDate = "";//jQuery("#dteKzpmStartdate").datebox("getValue");
	 var toDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	 workFlow ("divDefApproval",false,"FIPRODEF", masterkeyid, "PRODE",flid,fromDate,toDate);
	  
	 if(define=="C"){
		 disableUIButton("btnInsertKpi");
		 disableUIButton("btnAddNew");
		 disableUIButton("btnAddrow");
		//alert("C");
		 setTimeout(function() {			  
			 jQuery('input:checkbox[name=workflow_PRODE]').attr('disabled','disabled');
			 if(jQuery('#blockvf').html()==null||jQuery('#blockvf').html()=="")
			 	jQuery('#divDefApproval').after('<div id="blockvf" style="position:absolute;top:141%;left:4%;height:30%;width:91%;z-index:2;opacity:0.4;"> </div>');		 
			 
		 }, 400);
		
	 }	
}
jQuery("#btnMaicStage").click(function(){ 
	jQuery("#ClosureStage").hide();
	jQuery("#divDefApproval").html("");
	jQuery("#divMAICApproval").html("");
	jQuery("#divClosureApproval").html("");
	if(jQuery("#hdntype").val() == "define"){
		jQuery("#DefineStage").show();
		jQuery("#MaicStage").hide();
	}
	else if(jQuery("#hdntype").val() == "team"){
		jQuery("#DefineStage").hide();
		jQuery("#MaicStage").show();		
	}
	else if(jQuery("#hdntype").val() == "closure"){
		jQuery("#DefineStage").hide();
		jQuery("#MaicStage").show();
		//jQuery("#ClosureStage").show();		
	}
	getWorkFlowStage();	
});

jQuery("#btnClosureStage").click(function(){ 
	getClosureStage();
});

function getClosureStage(){
	jQuery("#divDefApproval").html("");
	jQuery("#divMAICApproval").html("");
	jQuery("#divClosureApproval").html("");
	jQuery("#DefineStage").hide();
	jQuery("#MaicStage").hide();
	jQuery("#ClosureStage").show();

//	jQuery('#Closure').css('background-color','#FFA500');
	//alert(2);
	var xStatus=jQuery("#hdnKzpmClosurestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	//alert('flid:'+flid);	
	if(cStatus=="C"){ //} && xStatus!="C" ){ 
		//var fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 4, 'dteKpdsVerifieddate');					
		//fromDate=fromDate.replace(" 00:00:00","").trim();	
		workFlow("divClosureApproval",false,"FIPROCLO", masterkeyid, "PROCL",flid,'','');	
	}
}

jQuery("#btnInsertKpi").click(function() {
	if(jQuery("#mode").val()!="view"){
		 var pilar = "TGT003";
		 LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.prpo?from=project&pillar="+pilar, true,"48%","75%","50px","30%", "multiSelectOk_Callback","Key Performance Indicator",false);
	}
});


function loadStagesMaic(mode,modifyStage){
	
	var defineStage= jQuery("#hdnKzpmDefinestage").val();
	//alert("len"+stage.length);
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();	
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(mode=="add"){
		if (defineStage=="P" ||  defineStage=="-"  || defineStage=="R" || defineStage=="E" || defineStage=="L" || defineStage.trim().length<=0){
			return "Define Stage Not Completed";
		}
		else if(defineStage=="C" && (mStatus=="-" || mStatus=="P" || mStatus=="I"  || mStatus=="R" || mStatus=="E" || mStatus=="L" || mStatus.trim().length<=0) ){
			//alert("mStatus");
			return mode;
		}
		else if(mStatus=="C" && ( aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus=="R" || aStatus=="E"  || aStatus=="L" || aStatus.trim().length<=0)  ){	
			//alert("aStatus");
			return mode;	
		}			
		else if(aStatus=="C" && ( iStatus=="-" || iStatus=="P" || iStatus=="I" || iStatus=="R" || iStatus=="E"  || iStatus=="L" || iStatus.trim().length<=0 ) ){
			//alert("iStatus");
			return mode;
		}
		else if(iStatus=="C" && ( cStatus=="-" || cStatus=="P"  || cStatus=="I"  || cStatus=="R" || cStatus=="E" || cStatus=="L" || cStatus.trim().length<=0 ) ){
			//alert("cStatus");			
			return mode;
		}
		else{
			return "Current Stage Is Waiting For Approval";			
		}
	}
	else if(mode=="modify"){
		modifyStage=modifyStage.substring(0,1);
		//alert("modifyStage"+modifyStage);
		var stageCom="";
		if(defineStage=="C" && (mStatus=="-" || mStatus=="P" || mStatus=="I" || mStatus=="R" || mStatus=="E" || mStatus=="L"  || mStatus.trim().length<=0) ){
			stageCom="M";
		}
		else if(mStatus=="C" && ( aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus=="R" || aStatus=="E" || aStatus=="L" || aStatus.trim().length<=0)  ){	
			stageCom="A";
		}			
		else if(aStatus=="C" && ( iStatus=="-" || iStatus=="P" || iStatus=="I" || iStatus=="R" || iStatus=="E" ||  iStatus=="L" || iStatus.trim().length<=0 ) ){
			stageCom="I";
		}
		else if(iStatus=="C" && ( cStatus=="-" || cStatus=="P"  || cStatus=="I" || cStatus=="R" || cStatus=="E" ||  cStatus=="L" || cStatus.trim().length<=0 ) ){
			stageCom="C";
		}
		//alert("stageCom"+stageCom);
		if(modifyStage!=stageCom){
			return "view";
		}
		else{
			return mode;			
		}
	} 	
}
jQuery("#btnAddKaizen").click(function(){	
	if(jQuery("#mode").val()!="view"){
		LoadPopUp("divKaizenPop", "kaizenList_view.prpo?", true,"70%","75%","60px","10%", "multiSelectOk_Callback","List Of Kaizen",false);
	}
});

jQuery("#btnSaveDMAIC").click(function(){
	if(jQuery("#mode").val()!="view"){
		var projectadmaic=saveDMAICStatus();
		//alert(projectadmaic);
		var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
		processAjaxCalls("projectsdmaic_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+"&Type=dmaicStatus"+projectadmaic,"mAICSaveSuccess");
	}
});

function mAICSaveSuccess(result){
	//if(result.successData.Type=="dmaicStatus"){
	alert(result.successData.msg);
	jQuery("#dmaicgrid").trigger("reloadGrid");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");	
}
	
function workFlowStatusSuccess(result){
	var measureStage=result.successData.measureStage;
	var analyseStage=result.successData.analyseStage;
	var improveStage=result.successData.improveStage;
	var controlStage=result.successData.controlStage;
	var closureStage=result.successData.closureStage;
	
	//alert("measureStage:"+measureStage+",analyseStage:"+analyseStage+",improveStage:"+improveStage+",controlStage:"+controlStage);
	if(measureStage.trim().length>0 && measureStage!=undefined && measureStage!="undefined")
		jQuery("#hdnKzpmMeasurestage").val(measureStage);	
	if(analyseStage.trim().length>0 && analyseStage!=undefined && analyseStage!="undefined")
		jQuery("#hdnKzpmAnalysestage").val(analyseStage);
	if(improveStage.trim().length>0 && improveStage!=undefined && improveStage!="undefined")
		jQuery("#hdnKzpmImprovestage").val(improveStage);
	if(controlStage.trim().length>0 && controlStage!=undefined && controlStage!="undefined")
		jQuery("#hdnKzpmControlstage").val(controlStage);
	if(closureStage.trim().length>0 && closureStage!=undefined && closureStage!="undefined")
		jQuery("#hdnKzpmClosurestage").val(closureStage);
	
	var defineStage=result.successData.defineStage;	
	if(defineStage.trim().length>0)
		jQuery("#hdnKzpmDefinestage").val(defineStage);		
	
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	
	processGridnew("projectsdmaic_input.prpo","q=2&keyid="+masterkeyid,"dmaicgrid","","","","","damicOnLoadComplete");
}

function divMilestonePop_onClose(){
	//alert("divMilestonePop_onClose");	
	/*var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");		
	processGridnew("projectsmile_input.prpo","q=2&keyid="+jQuery("#hdnKzpmKeyid").val(),"fourgrid","pagergrid","","doubleClickMile","","load_complete");
	*/
	return true;
}

function divKaizenPop_onClose(){
	//alert("divKaizenPop_onClose");
	//var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//var flid = jQuery("#frmProject input[id='flid']").val();
	jQuery("#Kaizengrid").trigger("reloadGrid");
	//processGridnew("projectskaizen_input.prpo","q=2&flid="+flid+"&master="+masterkeyid,"Kaizengrid","pagerkaizen","","","","load_complete");
	return true;
}

function doubleClickMile(id){
	var rowData = jQuery("#fourgrid").jqGrid('getRowData',id);
	var keyId = rowData.Keyid;	
	var stage = rowData.Stages;
	var mode=loadStagesMaic("modify",stage);	
	//alert("mode"+mode);
	if (mode=="add" || mode=="modify" || mode=="view"){	
		LoadPopUp("divMilestonePop", "milestone_view.prpo?q=2&Keyid="+keyId+"&mode="+mode, true,"90%","80%","60px","7%", "multiSelectOk_Callback","MilesStone"," ",true);
	}
	else{
		setTimeout(function() {
			showCommonErrorMsg(mode);
		}, 200);
		div_err();
		return false;
	}
	
}

function ResouredoubleClick(id){
	/*var rowData = jQuery("#resourcesgrid").jqGrid('getRowData',id);
	var keyId = rowData.keyid;	
	LoadPopUp("divResourcePop", "resource_view.prpo?q=2&resourceKeyid="+keyId, true,"70%","40%","20%","6%", "multiSelectOk_Callback","Resource"," ",true);*/
}

jQuery("#btnAddrow").click(function(){
	if(jQuery("#mode").val()!="view"){
		var projectResourcesc=saveResources();
		//alert(projectadmaic);
		//alert("projectResourcesc"+projectResourcesc);
		if(projectResourcesc!="false" && projectResourcesc!=false && projectResourcesc.trim().length>0){
			var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
			processAjaxCalls("resource_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+projectResourcesc,"resourcesSaveSuccess");
		}
	}
	//LoadPopUp("divResourcePop", "resource_view.prpo?q=2", true,"70%","40%","20%","6%", "multiSelectOk_Callback","Resource"," ",true);
});
function resourcesSaveSuccess(result){
	//if(result.successData.Type=="dmaicStatus"){
	alert(result.successData.msg);
	//alert("dmaicStatus success");
	jQuery("#resourcesgrid").trigger("reloadGrid");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");	
}

function divResourcePop_onClose(){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	processGridnew("projectsproto_input.prpo","q=2&keyid="+masterkeyid,"resourcesgrid","pager","","ResouredoubleClick","","loadResource_complete");
	return true;
}

function convertResoureDeleteToJsonString(jqGridId)//define stage for resource 
{
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var jsonArrO='[';
	for(var i=0;i<row.length;i++){
		 var rowid=row[i];
	     var detailKeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"1");
	     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
	     if(select=="1"){
	        var keyid;
	        if(detailKeyid.trim().length >=0){
	            keyid = detailKeyid;
	     		jsonArrO+= '{';
				jsonArrO += '"txtKprlKeyid":"'+keyid+'"';
				jsonArrO+= '},';
		     }		
	     }
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO;	
}

function loadResource_complete()
{	
	var jqGridId="resourcesgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKprlKeyid');			
			if (keyId.trim().length>0){
				//jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(allRows[i], true);
				jQuery('#'+jqGridId).setSelection(rowId, true);	
				//jQuery('#'+jqGridId).setSelection(allRows[i], true);				
				jQuery("#jqg_"+jqGridId+"_"+rowId).attr('checked',true);
			}
		}	
	}	
}

var keyid=jQuery("#hdnKzpmKeyid").val();//jQuery("#hdnabnkeyID").val();
var flid=jQuery("#frmProject input[id='flid']").val();
jQuery("#btnPrjYYLink").click(function(){ 
	//openWhyWhy(divId,isPopup,refDocId,refDocType,flId, refDocDate, problem, yyMode)
	var pbm=jQuery("#txtKzpmProblemstatement").val();
	openWhyWhy("divProWhyWhy",false,keyid,"PRO",flid, "", pbm, jQuery("#mode").val());
});

jQuery("#btnActionPlan").click(function(){
	//divId,actPlanRefMasId,actPlanRefDocType,flId,actPlanMainTask,actPlanRefDtlId, actPlanRefDate, apMode)}
	var mainTask=jQuery("#txtKzpmProjectname").val();
	openActionPlan("divProActioPlan",keyid,"PRO",flid,mainTask,keyid,"",jQuery("#mode").val());
});

jQuery("#btnFishBone").click(function(){ 
	 navigateToNextForm('FishBone_input.fishbone?&refDocId='+keyid+"&refDocType=PRO&flid="+flid+"&mode="+jQuery("#mode").val(),'Fish Bone');
});

jQuery("#btnMOM").click(function(){ 	
	 navigateToNextForm('MeetingMin_input.mom?momRefDocId='+keyid+'&momRefDocType=PRO&flid='+flid+"&mode="+jQuery("#mode").val(),'Meeting Minutes');
});

function txtFormatterKaizen(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="button" id="btnActionplan_'+rowId+'_'+colId+'" name="btnActionplanGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:50px;  height:15px;height:10px\9;"   class="easyui-button" value="..."/>';
}

function btnDocRefFormatter(id,options,rowObject){
	var rowId = options.rowId;
	return '<input type="button" id="btnFileMgr_'+rowId+'" name="btnFileMgr_'+rowId+'" onclick="fileMgr('+rowId+')" style="width:50px;  height:18px;height:15px\9;"   class="easyui-button" value="..."/>';
}

function btnfilemgr_click()
{	
	var documentNo =getFieldValue("hdnKzpmKeyid");     
	if(documentNo != null && documentNo != '')
		{
		fileManagerPopUp(documentNo,"PRO","","","");
	}
	else{
		saveForm("frmProject","projectsprotoview_save.prpo?&Type=fileMng","");
	}
}

function fileMgr(id) {
	//http://localhost:8080/TPMToolKit/file_input.file?q=2&documentNo=1&documentType=ABN&buttonId=
	fileManagerPopUp(1,"PCS","","","");
		
}

function kaizen(id, colId){
	LoadPopUp("Kaizen","kaizen_input.kaizen" ,true,"90%","500px","1%","3%","","Kaizen ");
}

function divIndicatorPop_onClose(){	
	//alert("divIndicatorPop_onClose");
	jQuery("#Kpigrid").trigger("reloadGrid");
   return true;
}

function load_complete(){

}
function getBenefits(){
	var value=getFieldValue("hdnBenefits");	
	jQuery("#cboKzpmBenefits").val(value);	
}
</script>
 <form id="frmProject" name="frmProject">
      <div id="wrapper" style="width:100%;padding-left:0px;">       
      	<div style="padding-left:0px;">
	       	<table style="width:98%;">
	       		<tr>
			    
			         <td colspan="2">
			          <div id="frmProjectFormatFuntKeyIds" >						
							<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
							<input type="hidden" id="section" name="cmbsection"  value=""></input>
							<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
							<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
							<input type="hidden" id= "flid"  name= "cmbKzpmFlid" value="${requestScope.Project.kzpmFlid}"/>
							<div id="projectfun" style=" ">
							</div>
						</div>
					</td>
					<td  style="padding-left:10px;">												
						<div class="">
							<label class="mandatory-lbl">Start Date</label>
							<span style="margin-left:25%;">
								<label>End Date</label>
							</span>
						</div>
						<div>
							<input id="dteKzpmStartdate" name="dteKzpmStartdate" class="easyui-datebox"  value="${requestScope.Project.kzpmStartdate}" style="width:110px;width:110px;\9" />
							<span style="margin-left:10%;">
							<input id="dteKzpmEnddate" name="dteKzpmEnddate" class="easyui-datebox"  value="${requestScope.Project.kzpmEnddate}" style="width:110px;width:110px;\9" />
							</span>						
						</div>	
						<table>
								<tr>
									<td>
										<span id="err_dteKzpmStartdate" class="tpm-errormsg" style=" "></span>
									</td>
									<td>
										<span id="err_dteKzpmEnddate" class="tpm-errormsg" style=" "></span>
									</td>
								</tr>
						</table>	
					</td>
				
			   			
			    
					<td valign="top" align="center" style="padding-left: 1% ">
						<div style="margin-top:-20px;margin-left:-10px;">   <label style="font-weight:bold" > Project Number</label>
						<div  style="margin-left:12px;" >
							<input class="easyui-text"  maxlength="50" style="background-color:cyan;font-weight:bold;width:125px;text-align: center; height:21px;text-align: left;"  id="txtKzpmProjectno"   name="txtKzpmProjectno" value="${requestScope.Project.kzpmKeyid}" readonly="readonly"/>   
			            </div>
			            </div>
						<div class="sub-header" id="DMAIC" style="margin-left:10px; text-align: left;font-size:15px;float:left;width:100px; width:100px\9;height:18px\9;padding-right: 10px;">
							<span style="position:absolute;">DMAIC-Status</span>
						</div>
					</td>
				</tr>
						
		    	<tr>
	       			<td valign="top">
	               		<div style="padding-top:3px;">   <label class="mandatory-lbl">Project Name  </label></div>
		                <div  class="easyui-paddingbfpx" >
						 <input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmProjectname"   name="txtKzpmProjectname" value="${requestScope.Project.kzpmProjectname}"/>
						</div>
						<div>  <label> Tangible </label> 
							<span style="margin-left:10%;">
								<label id="lblAmount">Amount</label>
							</span>
					     </div>
						<div >
						<input type="checkbox" name="chkKzpmIstangible" id="chkKzpmIstangible"  value="Y"  <c:out value = "${requestScope.Project.kzpmIstangible == 'Y' ? 'checked':''}"/> /> 
						<span style="margin-left:20%;">	
							
							<input class="easyui-text" id="txtKzpmBenefits" name="txtKzpmBenefits"   style="width:100px; height: 21px;"  value="${requestScope.Project.kzpmBenefits}"  />
						</span>	
							<!--<input class="easyui-text"  maxlength="50" style="width:255px; height:21px;"  id="txtKzpmBenefits"   name="cboKzpmBenefits" value="${requestScope.Project.kzpmBenefits}"size="15"/>-->   
		               </div>
		               <!-- 
		               <div style="padding-top:3px">   <label id='lblKzpmProjectmetrics'> Project Metrics</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmProjectmetrics" name="txtKzpmProjectmetrics" >${requestScope.Project.kzpmProjectmetrics}</textarea>   
		               </div>
		                -->
		                <div style="padding-top:3px">   <label id='lblKzpmProjectmetrics'> Project Metrics (Key Perfomance Indicator)</label></div>
					   <div  class="easyui-paddingbfpx" >
							<input id="cmbKzpmProjectmetrics" name="cmbKzpmProjectmetrics" class="easyui-combobox"  style="width:255px; height: 21px;  cursor: default;" value="${requestScope.Project.kzpmProjectmetrics}"  />   
		               </div>
		               
		               <div style="padding-top:3px">   <label id="lblKzpmGoalobj">Goal/Objectives</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmGoalobj" name="txtKzpmGoalobj" >${requestScope.Project.kzpmGoalobj}</textarea>   
		               </div>
		               <div style="padding-top:10px;" id="Stagesbutton">
			               	<input type="button" class="easyui-button"  value="Define Stage" id="btnDefineStage" style="height: 22px;width:80px;"/>
			               	<input type="button" class="easyui-button"  value="MAIC Stages" id="btnMaicStage" style="height: 22px;width:80px;"/>
			               	<input type="button" class="easyui-button"  value="Closure Stage" id="btnClosureStage" style="height: 22px;width:80px;"/>
		               </div>
	          		</td>
		 		  <td style="padding-left:10px;">
					 <div style="padding-top:5px"><label> Area </label></div>
					 	<div  class="easyui-paddingbfpx" >
						 	<input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmArea"   name="txtKzpmArea" value="${requestScope.Project.kzpmArea}"size="15"/>   
		             	</div>   
					  	<div  >
					  	 <label> InTangible </label> 
							<span style="margin-left:3%;">
					  	<label id="lblImprCategory" class="mandatory-lbl">Improvement Category</label>
					  	</span></div>
	                 	 <div class="easyui-paddingbfpx">
	                 	 <input type="checkbox" name="chkKzpmIsintangible" id="chkKzpmIsintangible"  value="Y" <c:out value = "${requestScope.Project.kzpmIsintangible == 'Y' ? 'checked':''}"/> /> 
						<span style="margin-left:15%;">	
						
			                <input id="cmbKzpmImprCategory" name="cmbKzpmImprCategory" class="easyui-combobox"  style="width: 185px; height: 21px;  display: none; cursor: default;" value="${requestScope.Project.kzpmImprCategory}"  />
			             </span>
						</div>
					  	
		                <div style="padding-top:2px">      <label id='lblKzpmProblemstatement'> Problem Statement</label></div>
						<div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmProblemstatement" name="txtKzpmProblemstatement" >${requestScope.Project.kzpmProblemstatement}</textarea>   
		               </div>
		               <div style="padding-top:3px">   <label>Scope/Constraints</label></div>
							<div  class="easyui-paddingbfpx" >
						 	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width:255px;height:44px;" id="txtKzpmScopeconst" name="txtKzpmScopeconst" >${requestScope.Project.kzpmScopeconst}</textarea>   
		               </div>
		 			</td>
	      			<td style="padding-left:10px;" valign="top">
	      				<div style="padding-top:3px">   
			   				 <label class="mandatory-lbl"> Project Champion</label>
	      				</div>
	      				<div  class="easyui-paddingbfpx" >
	    						<input class="easyui-combobox"  style="width:255px; height:21px;"  id="cmbKzpmProjectchamp"   name="cmbKzpmProjectchamp" value="${requestScope.Project.kzpmProjectchamp}"size="15"/>   
	                    </div>
	    				
	    				<div style="padding-top:3px"><label id="lblKzpmSavings"> Savings</label></div>
						<div  class="easyui-paddingbfpx" >
							<input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmSavings"   name="txtKzpmSavings" value="${requestScope.Project.kzpmSavings}"size="15"/>   
		               	</div>
		               	  
	                    <div style="padding-top:3px">   <label id='lblKzpmBusinesscase'> Business Case</label></div>
	     						<div  class="easyui-paddingbfpx" >
	    						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80"  maxlength="500" style="width:255px;height:44px;" id="txtKzpmBusinesscase" name="txtKzpmBusinesscase" >${requestScope.Project.kzpmBusinesscase}</textarea>   
	                    </div>
	      				
						<div style="padding-top:10px;" id="linkbutton">					 
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Why Why" id="btnPrjYYLink" style="height: 22px;width:65px;"/></span>
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Action Plan" id="btnActionPlan" style="height: 22px;width:70px;"/></span>
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Fish Bone" id="btnFishBone" style="height: 22px;width:65px;"/></span>
						  	<input type="button" class="easyui-button"  value="MOM" id="btnMOM" style="height: 22px;width:40px;"/>             
	             		</div>
		      		</td>
		      		<td align="center" valign="top" style="padding-left: 1%;">
				      	<div id="projectcreation">				
						<div style="padding-top:10px;">
							<input type="text" id="define" value="Define" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="measure" value="Measure" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Analyse" value="Analyse" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />			
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Improve" value="Improve" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Control"  value="Control" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Closure"  value="Closure" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						</div>
						<div style="padding-top:12px;">
							<span  id="abnFilemgr" style="left:00%;left:00%\9;top:160px;top:165px\9;" ></span>
						</div>			
	    			</td>
	      		</tr>
	      	</table>
      		<div id="DefineStage">
	       		<div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%">
		      		<span style="position:absolute;">Define Stage</span>
				</div>
	      		<div class="main-cntborder" style="width:auto;height:auto;float: left;">
	      			<div style="padding-left:5px;">
	      				<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
					      		<span style="position:absolute;">Key Performance Indicator</span>
					      		<span style="position:absolute; right:0%;" id="skilbtn">
									<img id="btnInsertKpi" alt="" title="Add Details" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
								</span>
					      </div>
						 <div style="float:left;">
						 	 <table  id='Kpigrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagerKpi'></div>
						 </div>
						 <div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
				      		<span style="position:absolute;">Approval</span>
					     </div>
					     
						 <div id="divDefApproval" style="float:left;width:100%; width:800px\9;margin-right:4%;position:relative;"> 
						 	
						 </div>
						
						 <div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
					      		<span style="position:absolute;">Resources</span>
					      		<span style="position:absolute; right:0%;" id="skilbtn">
					      			<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:19px;"/>
					      			<input type="button" class="easyui-button" value ="Save" id="btnAddrow" style="height:19px;"/>
								</span>
			    	      </div>
						 <div style="float:left;"> 
						     <table  id='resourcesgrid' >
								<tr><td></td></tr>
							 </table>
							 <div id='pager'></div>
						 </div>
					</div>
				</div>
			</div>
			<div id="MaicStage">
				<div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%;">
					<span style="position:absolute;">MAIC Stages</span>
				</div>
	      		<div class="main-cntborder" style="width:auto;height:200%;float: left">
	      			<div style="padding-left:5px;">
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">Milestones</span>
							<span style="position:absolute; right:0%;" id="skilbtn">
								<img id="btnAddMst" alt="" title="Add Milestone" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
							</span>
	      				</div>
						<div style="float:left;">
							<table  id='fourgrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagergrid'></div>
						</div>
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">List Of Kaizens</span>
							<span style="position:absolute; right:0%;" id="skilbtn">
									<img id="btnAddKaizen" alt="" title="Select the Kaizen" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
							</span>
		      			</div>
						<div style="float:left;">
							<table  id='Kaizengrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagerkaizen'></div>
						</div>
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">DMAIC</span>
							<span style="position:absolute; right:0%;" id="saveDMAICStatusbtn">
								<input type="button" class="easyui-button" value ="Save" id="btnSaveDMAIC" style="height:19px;"/>
								<!--<img id="btnSaveDMAIC" alt="" title="Save DMAIC Verification" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
								-->
							</span>
		      			</div>
						<div id="" style="float:left;">
							<table  id='dmaicgrid' >
							</table>
							<div id='pagerdmaic'></div>
						</div>
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">Authorization</span>
		      			</div>
						<div id="divMAICApproval" style="float:left;">					
						</div>
					</div>
				</div>
			</div>
			<div id="ClosureStage">
				<div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%;">
					<span style="position:absolute;">Closure Stages</span>
				</div>
	      		<div class="main-cntborder" style="width:auto;height:200%;float: left">
	      			<div id="divClosureApproval" style="float:left;">					
					</div>
	      		</div>
      		</div>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdntype" value="${requestScope.type}"/>
	<input type="hidden" id="hdnKzpmDefinestage"  name="hdnKzpmDefinestage" value="${requestScope.Project.kzpmDefinestage}"/>
	<input type="hidden" id="hdnMaicstage"   value=""/>
	<input type="hidden" id="hdnKzpmMeasurestage"  name="hdnKzpmMeasurestage" value="${requestScope.Project.kzpmMeasurestage}"/>
	<input type="hidden" id="hdnKzpmAnalysestage" name="hdnKzpmAnalysestage" value="${requestScope.Project.kzpmAnalysestage}"/>
	<input type="hidden" id="hdnKzpmImprovestage" name="hdnKzpmImprovestage" value="${requestScope.Project.kzpmImprovestage}"/>
	<input type="hidden" id="hdnKzpmControlstage" name="hdnKzpmControlstage" value="${requestScope.Project.kzpmControlstage}"/>
	<input type="hidden" id="hdnKzpmClosurestage" name="hdnKzpmClosurestage" value="${requestScope.Project.kzpmClosurestage}"/>
	<input type="hidden" id="txtKzpmProjectnumber" name="txtKzpmProjectnumber" value="${requestScope.Project.kzpmProjectno}"/>   
	<input type="hidden" id="txtKzpmCreatedby" name="txtKzpmCreatedby" value="${requestScope.Project.kzpmCreatedby}"/>        
	<input type="hidden" id="hdnKzpmKeyid" name="hdnKzpmKeyid" value="${requestScope.Project.kzpmKeyid}"/>
	<input type="hidden" id="hdnBenefits" name="hdnBenefits" value="${requestScope.Project.kzpmBenefits}"/>
	<input type="hidden" id="hdnkkeyid" name="hdnkkeyid" value="${requestScope.kkeyid }"/>
	<input type="hidden" id="hdnIsClosure"  name="hdnIsClosure" value="${requestScope.isClosure}"/>
 </form>