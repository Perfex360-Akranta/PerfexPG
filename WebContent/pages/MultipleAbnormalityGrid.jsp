<script>
jQuery(document).ready(function(){
	initialiseForm("frmMultipleAbnormality");
	jQuery("#submitForm").val("frmMultipleAbnormality");
	var url = jQuery("#hiddenUrl").val();
  // viewGrid(url,"q=2&flid="+keyIds.flId");
	//viewGrid(url,"");
	
   var factId = jQuery("#frmMultipleAbnormality input[id='factory']").val();
   var sectionId = jQuery("#frmMultipleAbnormality input[id='section']").val();
   var cellId = jQuery("#frmMultipleAbnormality input[id='cell']").val();
   var machId = jQuery("#frmMultipleAbnormality input[id='machine']").val();
   var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
   var hdnflid = jQuery("#hdnflid").val();
   var types=jQuery("#hdnRefDoctype").val();
   var docId=jQuery("#hdnRefDocId").val();
   var remarks=jQuery("#hdnremarks").val();
   if (hdnflid!=null && hdnflid.trim().length>0 )
   	flid=hdnflid;
   
   viewGrid(url,"q=2&flid="+flid);
   	
   var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
   loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmMultipleAbnormality",dataStr);

});
function frmMultipleAbnormality_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
		
		if( keyIds.flId != undefined && keyIds.flId.trim().length>0){
			
			viewGrid(url,"q=2&flid="+keyIds.flId);
		}
		else {
			viewGrid(url,"q=2");
		}
	}

/* var factId = jQuery("#frmMultipleAbnormality input[id='factory']").val();
var sectionId = jQuery("#frmMultipleAbnormality input[id='section']").val();
var cellId = jQuery("#frmMultipleAbnormality input[id='cell']").val();
var machId = jQuery("#frmMultipleAbnormality input[id='machine']").val();
var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
var hdnflid = jQuery("#hdnflid").val();
var types=jQuery("#hdnRefDoctype").val();
var docId=jQuery("#hdnRefDocId").val();
var remarks=jQuery("#hdnremarks").val();
if (hdnflid!=null && hdnflid.trim().length>0 )
	flid=hdnflid;
	
var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmMultipleAbnormality",dataStr); */
//fillComboBox("frmMultipleAbnormality","cmbAbnmStatus","Combo_Status.abnForm","",false);
fillComboBox("frmMultipleAbnormality","cmbAbnmTypeid","Combo_Type.abnForm");
fillComboBox("frmMultipleAbnormality","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");	
fillComboBox("frmMultipleAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?frmType="+jQuery('#hdnfrmType').val());
fillComboBox("frmMultipleAbnormality","cmbAbnmTradeid","Combo_Trade.abnForm");

var keyid=jQuery('#hdnkeyId').val();
var typeId = getFieldValue("cmbAbnmTypeid");
if( typeId != null && typeId.length > 0)
{
	fillComboBox("frmMultipleAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?abtmKeyid="+typeId+"&frmType="+jQuery('#hdnfrmType').val());
}

function viewGrid(url,filterString)
{
	processGridnew(url,filterString,"AbnMutipleGrd","pager","","","EmpConfigselectRowFunction");
	return true;    
}


var clirefdocid = jQuery('#hdnclirefDocID').val();
if(clirefdocid.trim().length>0) 
	jQuery('#txtAbnmRefdocid').val(clirefdocid);

jQuery("#btnAdd").click(function(){
	var row=jQuery("#AbnMutipleGrd").jqGrid("getDataIDs");
	addRow(row);
});

function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnAbnmdKeyid:" ",
			 cmbAbnmDetectedby:" ",
			 txtAbnmDescription:" ",
			 cmbAbnmEquipmentid:" ",
			 cmbAbnmTypeid:" ",
			 cmbAbnmSubtype:" ",
			 cmbAbnmCategoryid:" ",
			 cmbAbnmTagclassid:" ",
			 cmbAbnmImpactid:" ",
			 cmbAbnmTradeid:" ",
			 cmbAbnmResponsibleid:" ",
			 Responsibiltyby:" ",
			 cmbAbnmStatus:" ",
			 StatusDetail:" ",
			 dteAbnmTargetdate:" ",
			 txtAbnmCountermeasure:" ",
			 cmbAbnmCompletedby:" ",
			 completedby:" ",
			 dteAbnmWoendtime:" ",
			 chkAbnmShutdownmaint:" ",
			 txtAbnmRemarks:" ",
			 btnFilManage:" ",
			 btnabnActionpln:" ",
			 btnAbnYYLink:" "}
		 
		 ];
	jQuery("#AbnMutipleGrd").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnAbndKeyid:" ",
			cmbAbnmDetectedby:" ",
			txtAbnmDescription:" ",
			cmbAbnmEquipmentid:" ",
			cmbAbnmTypeid:" ",
			cmbAbnmSubtype:" ",
			cmbAbnmCategoryid:" ",
			cmbAbnmTagclassid:" ",
	        cmbAbnmImpactid:" ",
	        cmbAbnmTradeid:" ",
	        cmbAbnmResponsibleid:" ",
	        cmbAbnmStatus:" ",
	        StatusDetail:" ",
	        dteAbnmTargetdate:" ",
	        txtAbnmCountermeasure:" ",
	        cmbAbnmCompletedby:" " ,
	        completedby:" ",
	        dteAbnmWoendtime:" ",
	        chkAbnmShutdownmaint:" ",
	        txtAbnmRemarks:" " ,
	        btnFilManage:" ",
	        btnabnActionpln:" ",
	        btnAbnYYLink:"  "}];
		jQuery("#AbnMutipleGrd").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

jQuery("#btndelete").click(function()
	   	{ 
			 removeRecord();
		});

  function removeRecord(keyid){
   var Abnrow = jQuery("#AbnMutipleGrd").jqGrid('getDataIDs');
   for (var i = 0; i < Abnrow.length; i++) {
   var rowid = Abnrow[i];
   if (jQuery('#jqg_AbnMutipleGrd_'+Abnrow[i]).is(':checked') == true) {

   keyid = jQuery("#AbnMutipleGrd").jqGrid('getCell', rowid, "hdnAbndKeyid"); 

    if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
	    var r = confirm("Do You Want To Delete?");
		if (r == true) {
			//processAjaxCalls("MoMeeting_remove.mom", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
		} else{
			return false;
		} 
   	}else {
        var r = confirm("Do You Want To Remove Row?");
		if (r == true)
			jQuery("#AbnMutipleGrd").trigger("reloadGrid");
		else
			return false;
	}
 }
}	
}
  
 	 jQuery("#btnFilManage").click(function(){
  
  var documentNo =result.successData.keyId;
	
  if(jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
	 	var fmgMode = "view";
	//alert('1==='+fmgMode);
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"ABN","","","");
	}
});
 
  
  
function AbnMutipleGrd_selectRow(rowId){
	var jqGridId="AbnMutipleGrd";
    disableField('frmMultipleAbnormality',"cmbAbnmCompletedby_"+jqGridId+"_"+rowId);
    disableUIButton("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
    disableUIButton("txtAbnmRefdocid_"+jqGridId+"_"+rowId);
  //  jQuery("#cmbAbnmStatus_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
    removeMandatoryFieldForComplete("AbnMutipleGrd",rowId);
    removeMandatoryFieldForCompletedBy("AbnMutipleGrd",rowId);
    removeMandatoryFieldForCompletedDate("AbnMutipleGrd",rowId);
    var remarks=jQuery("#hdnremarks").val();
    var docId=jQuery("#hdnRefDocId").val();
  //  var cellId = jQuery("#frmMultipleAbnormality input[id='cell']").val();
    setTimeout(function(){
	      disableField('frmMultipleAbnormality',"dteAbnmWoendtime_"+jqGridId+"_"+rowId);},
	 1250);   
    disableField('frmMultipleAbnormality',"dteAbnmTargetdate_"+jqGridId+"_"+rowId);
   /*  setTimeout(function(){reloadCombo("frmMultipleAbnormality","cmbAbnmDetectedby_"+jqGridId+"_"+rowId,"employee.commonFilter?&cellId="+cellId);
	},1000); */
	
	 var types=jQuery("#hdnRefDoctype").val();
	 if(types=="VCC"){
		 jQuery("#txtAbnmRemarks_"+jqGridId+"_"+rowId).val(remarks); 
		 disableUIButton("txtAbnmRemarks_"+jqGridId+"_"+rowId);
		 jQuery("#txtAbnmRefdocid_"+jqGridId+"_"+rowId).val(docId);
	 }
	 else{
		 enableUIButton("txtAbnmRemarks_"+jqGridId+"_"+rowId);
	 }
	 
    jQuery("#cmbAbnmStatus_AbnMutipleGrd_"+rowId).combobox({  	   
    	onSelect:function(recordid)
    		{ 
    		var status=jQuery("#cmbAbnmStatus_AbnMutipleGrd_"+rowId).combobox('getValue');
    	    if(status=="P")
    	    {
    	    disableField('frmMultipleAbnormality',"cmbAbnmCompletedby_"+jqGridId+"_"+rowId);
    	    disableField('frmMultipleAbnormality',"dteAbnmWoendtime_"+jqGridId+"_"+rowId);
    	    disableUIButton("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
    	    clearField("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
    	    jQuery("#cmbAbnmCompletedby_"+jqGridId+"_"+rowId).combobox('clear');
    	    jQuery("#dteAbnmWoendtime_"+jqGridId+"_"+rowId).datebox("clear");
    	    enableUIButton("btnActionplan_"+jqGridId+"_"+rowId);
    	    removeMandatoryFieldForComplete("AbnMutipleGrd",rowId);
    	    removeMandatoryFieldForCompletedBy("AbnMutipleGrd",rowId);
    	    removeMandatoryFieldForCompletedDate("AbnMutipleGrd",rowId);
    	    }
    	    else if(status=="C")
    	    { 	
    	    	 
    	     enableFormFields('frmMultipleAbnormality',"cmbAbnmCompletedby_"+jqGridId+"_"+rowId);
    	     enableUIButton("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
    	     jQuery("#dteAbnmWoendtime_"+jqGridId+"_"+rowId).datebox("enable");
    	     disableUIButton("btnActionplan_"+jqGridId+"_"+rowId);
    	     makeMandatoryFieldForPending("AbnMutipleGrd",rowId);
    	     makeMandatoryFieldForCompletedBy("AbnMutipleGrd",rowId);
     	     makeMandatoryFieldForCompletedDate("AbnMutipleGrd",rowId);
    	    }
    		}
    	});

  /*   var responsibleId="cmbAbnmResponsibleid_AbnMutipleGrd_"+rowId;
    jQuery("#cmbAbnmTradeid_AbnMutipleGrd_"+rowId).combobox({ 
    	onSelect:function(recordid)
		{ 
    	    var cellId = jQuery("#frmMultipleAbnormality input[id='cell']").val();
    		var respon = jQuery("#hdnRespond").val();
    		var trade=jQuery("#cmbAbnmTradeid_AbnMutipleGrd_"+rowId).combobox('getValue');
    	    setTimeout(function() {reloadCombo("frmMultipleAbnormality",responsibleId,"employee.commonFilter?&cellId="+cellId+"&trade="+trade+"&combokey="+respon);
  			},1000);
		}
    	});    */
    
 jQuery("#cmbAbnmTagclassid_AbnMutipleGrd_"+rowId).combobox({
	onSelect:function(recordid) 
	 {
	    var tagText=jQuery("#cmbAbnmTagclassid_AbnMutipleGrd_"+rowId).combobox('getValue');
		var targetDate = jQuery('#dteAbnmDetectiondate_'+jqGridId+"_"+rowId).datebox("getValue");
		fillTargetDate(targetDate,recordid.text,rowId);		 	    
	    if(tagText=="TAG000002"){ //Red Tag
	     enableFormFields("frmMultipleAbnormality","cmbAbnmTradeid_"+jqGridId+"_"+rowId);
		 jQuery("#cmbAbnmStatus_"+jqGridId+"_"+rowId).combobox('disable');
		 removeMandatoryFieldForComplete("AbnMutipleGrd",rowId);
		 removeMandatoryFieldForCompletedBy("AbnMutipleGrd",rowId);
		jQuery("#chkAbnmShutdownmaint_"+jqGridId+"_"+rowId).attr('disabled',false);
	     }
	    
	    else if(tagText== "TAG000001"){  //White Tag
	    	disableField("frmMultipleAbnormality","cmbAbnmTradeid_"+jqGridId+"_"+rowId);
            jQuery("#cmbAbnmStatus_"+jqGridId+"_"+rowId).combobox('enable');
			jQuery("#chkAbnmShutdownmaint_"+jqGridId+"_"+rowId).attr('disabled','disable');
			
			
			
	    }  
	 }
 });
    	
  var subType="cmbAbnmSubtype_AbnMutipleGrd_"+rowId; 
//  alert(subType);
  jQuery("#cmbAbnmTypeid_AbnMutipleGrd_"+rowId).combobox({ 
  	onSelect:function(recordid){ 
  	   var abnTypeId=jQuery("#cmbAbnmTypeid_AbnMutipleGrd_"+rowId).combobox('getValue');
  	  // alert(abnTypeId);
  	   setTimeout(function(){reloadCombo("frmMultipleAbnormality",subType,"Combo_SubType.abnForm?&abtmKeyid="+recordid.id);
			},1000);
		}
  	});
  
  
 var AbnormalityDate=jQuery("#AbnMutipleGrd").jqGrid('getCell', rowId,"dteAbnmDetectiondate");
 var dateCtrl="dteAbnmWoendtime_AbnMutipleGrd_"+rowId;	
 

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(date)
				{ 
				
				isValidDate(dateCtrl,rowId,date);	
				} 
			});  
	
	
 var detecteddateCtrl="dteAbnmDetectiondate_AbnMutipleGrd_"+rowId;
 setTimeout(function(){
		fillWithCurrentDate(detecteddateCtrl);
		
		},550); 
 jQuery("#"+detecteddateCtrl).datebox({  	   
		onSelect:function(date)
			{ 
			isValidDetectedDate(detecteddateCtrl,rowId,date);    	
			} 
		});
 
  jQuery("#chkAbnmShutdownmaint_AbnMutipleGrd_"+rowId).click(function(){	
	 var isChecked =jQuery("#chkAbnmShutdownmaint_AbnMutipleGrd_"+rowId).is(':checked');
	});
}

function formatDate(date){
 	let newDate = new Date(date);
 		   
 	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

 	// Format DD-MMM-YYYY
     let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
 				            months[newDate.getMonth()] + '-' +
 				            newDate.getFullYear();
 	return formatted;
  }

  function isValidDetectedDate(detecteddateCtrl,rowId,date){
	    var currdate=jQuery("#hdncurrentDate").val();
	
	    var approvalDate = formatDate(date) ; // getFieldValue(detecteddateCtrl);
		var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
			{
		var stringdate=convertStringToDate(approvalDate);
	if(convertStringToDate(approvalDate)== currentDate)
			{
			}
		if(convertStringToDate(currdate) < convertStringToDate(approvalDate))
		{  
			if(stringdate==convertStringToDate(currdate))
				{
				  clearValidationErrorMsg(detecteddateCtrl);
			    	return false;
				}
			else{
			 popupCommonErrorMsg('Should Not Enter Future Date');
			 setTimeout(function() { fillWithCurrentDate(detecteddateCtrl); },250);
			return false;
			}
		}
		}
	}

function isValidDate(dateCtrl,rowId,date){
	var approvalDate = formatDate(date) ; //  getFieldValue(dateCtrl);
	var minDate="dteAbnmDetectiondate_AbnMutipleGrd_"+rowId;
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	if (approvalDate=="undefined" || approvalDate=="" || approvalDate==" ") {  
		if(convertStringToDate(approvalDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			setTimeout(function() { fillWithCurrentDate(dateCtrl); },250);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	}
	else{
		if(convertStringToDate(approvalDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			setTimeout(function() { fillWithCurrentDate(dateCtrl); },250);
			return false;
		}
	}
	
	if (minDate!= undefined && minDate!="" && minDate!=" ") {
		if(convertStringToDate(approvalDate) < convertStringToDate(minDate))
		{
			alert('Should Not Less Than '+minDate);
			setTimeout(function() { fillWithCurrentDate(dateCtrl); },250);
			return false;
		}
	}		
}

function fillTargetDate(targetDate,tagClass,rowId)
{
	 processAjaxCalls("DateboxMultiple_FillTargetDate.abnForm?row=0","&targetDate="+targetDate+"&tagClass="+tagClass+"&rowId="+rowId,"TargetDateonSuccessCallback");
}

function TargetDateonSuccessCallback(result)
{
	var jqGridId="AbnMutipleGrd";
	setFieldValue("dteAbnmTargetdate_"+jqGridId+"_"+result.rowId,result.date);
}


function cmbAbnmTypeid_AbnMutipleGrd_onSelect(recordid,rowId){
	
processAjaxCalls("getAbnSubType.abnForm?abtmKeyid="+recordid.id,"rowid="+rowId,"Subtype_OnSuccess");
//reloadCombo("frmMultipleAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?abtmKeyid="+record.id);
} 

function makeMandatoryFieldForPending(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		
		
		if(colModel[k].index =="txtAbnmCountermeasure"){
			colModel[k].mandatory = false;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function removeMandatoryFieldForComplete(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="txtAbnmCountermeasure"){
			colModel[k].mandatory = false;
			removeMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function makeMandatoryFieldForCompletedBy(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="cmbAbnmCompletedby"){
			
			colModel[k].mandatory = false;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function removeMandatoryFieldForCompletedBy(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="cmbAbnmCompletedby"){
			colModel[k].mandatory = false;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function makeMandatoryFieldForCompletedDate(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="dteAbnmWoendtime"){
			colModel[k].mandatory = false;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function removeMandatoryFieldForCompletedDate(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="dteAbnmWoendtime"){
			colModel[k].mandatory = false;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}


function checkRepAbn()
{
	 var machine = jQuery("#cmbAbnmEquipmentid").combobox('getValue');
	 var type = jQuery("#cmbAbnmTypeid").combobox('getValue');
	 var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
	 if(machine.trim()!="" && type.trim()!=""){
	 	var dataString = "&cmbMchid="+machine+"&cmbabntype="+type+"&flid="+flid;
		processAjaxCalls("repeatedAbn_getData.abnForm?"+dataString,"","repAbnSuccessCallBack");
	 }
}


function abnTypeOnsuccessCallback(result)
{
	 
	 
	if(result.abnType=="H")
	{
		jQuery('#cmbAbnmSubtype').combobox('enable');
		jQuery('#lblVal').html('HTA Type');
		jQuery('#lblAbnDesc').html('Abnormality Description(Difficulty)');
		jQuery('#lblAbnHappend').html('Why it is difficult (Why Abnormality Happened?)');
		isNotSOC();
		isAbnFormType();
		enableFields('cmbAbnmAfeemid');
		
	}
	else if(result.abnType=="`")
	{	
		jQuery('#cmbAbnmSubtype').combobox('enable');
		jQuery('#lblVal').html('SOC Type');
		jQuery('#txtAbnmContaminant').attr({'disabled':false});
		jQuery('#txtAbnmMode').attr({'disabled':false});
		jQuery('.socType').addClass('mandatory-lbl');
		jQuery('#lblAbnDesc').html('Abnormality Description(Contamination)');
		jQuery('#lblAbnHappend').html('Source');
		enableFields('cmbAbnmAfeemid');
		isAbnFormType();
	}
	else if(result.abnType=="U")
	{
		
		isHseFormType();
		enableHseForm();
		
	}
	else
	{	
		jQuery('#lblVal').html('Sub Type');
		//jQuery('#cmbAbnmSubtype').combobox('disable');
		jQuery('#lblAbnHappend').html('Why Abnormality Happened?');
		jQuery('#lblAbnDesc').html('Abnormality Description');
		isNotSOC();
		isAbnFormType();
		readOnlyFields("cmbAbnmAfeemid");
	}
	var prvData = jQuery("#hdnAbnType").val();
	var abntype = "N";
	if(result.abnType != "U")
	{
		abntype = "N";
		if(prvData != abntype)
			reloadCombo("frmMultipleAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType="+jQuery('#hdnfrmType').val());
		jQuery("#hdnAbnType").val(abntype);
	}
	else 
	{
		abntype = "U";
		if(prvData != abntype)
			reloadCombo("frmMultipleAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");
		jQuery("#hdnAbnType").val(abntype);
	}
}


function  frmMultipleAbnormalitycmbAbnmStatus_onSelect(record)
{
	 abnmStatusSelect(record.id) ;
	 //comboFillWithCell('cmbAbnmCompletedby');
	 var compkey = getFieldValue("cmbAbnmCompletedby");
	 var cellId = jQuery("#frmMultipleAbnormality input[id='cell']").val();
	 reloadCombo("frmMultipleAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId="+cellId+"&combokey="+compkey);
	 if("C" == record.id)
	 	enableFields('dteAbnmWoendtime');
	else if("P"==record.id)
		jQuery('#txtAbnmCountermeasure').val('');
}

function abnmStatusSelect(id) {
	 
	 var tagClass = jQuery("#cmbAbnmTagclassid").combobox('getText');
	 
	 var tagFlag = jQuery('#hdnPendComp').val();
	 var showCompletedDate = jQuery('#hdnShowCompDate').val();
	 if(tagFlag != null && tagFlag != '' && tagFlag != ' ')
	 {	 
		if(tagFlag == "N")
			jQuery('#startandendtimediv').css('display','none');
	 }
			
	if(id=='C')
	{
		jQuery('#hdnempty').val("C");
		//loadTeamCombo();		
		jQuery("#pend").hide();
		jQuery("#comp").show();		
		
		jQuery(".completedtls").show();
		

		disableUIButton("btnAbnWOLink");
		disableUIButton("btnabnActionpln");
		//disableUIButton("btnFilManage");
		disableUIButton("btnRepAbn");
		
		
		
		jQuery('#txtAbnmCountermeasure').attr({'disabled':false});
		if(tagClass.trim().substring(0,3)== "RED")
		{
				jQuery('.incompletion').addClass('mandatory-lbl');
				if(showCompletedDate != null && showCompletedDate != '' && showCompletedDate != ' ' && showCompletedDate)
				{
					if(showCompletedDate == 'Y')
					{
						jQuery('#startandendtimediv').css('display','none');						
					}
					else
					{
					//	jQuery('#lblCompDate').css('display','none');
						//jQuery('#divCompDate').css('display','none');
					}
				}
				else
				{
					jQuery('#lblCompDate').css('display','none');
					jQuery('#divCompDate').css('display','none');
				}
		}
		else
		{
			jQuery('#startandendtimediv').css('display','none');	
		}
		enableUIButton('btnAbnYYLink');
		//enableUIButton('btnAbnWOLink');
	}					
	else if(id=='P')
	{	
		jQuery('#hdnempty').val("R");
		//loadTeamCombo();
		jQuery(".completedtls").hide();
		jQuery("#pend").show();	
		jQuery("#comp").hide();				
		jQuery('.incompletion').removeClass('mandatory-lbl');
//		jQuery('#txtAbnmCountermeasure').val("");
		jQuery('#txtAbnmCountermeasure').attr({'disabled':true});
		disableUIButton('btnAbnYYLink');
		disableUIButton('btnAbnWOLink');
		
		enableUIButton("btnabnActionpln");
		enableUIButton("btnFilManage");
		enableUIButton("btnRepAbn");
	}

}

function validateMandtory(gridSelArr){
var selArr = JSON.parse(gridSelArr);
for(var i = 0;i<selArr.length;i++){
	if(selArr[i].cmbAbnmDetectedby.trim()==""){
		alert("Select DetectedBy");
		return false;
	}

	if(selArr[i].txtAbnmDescription.trim()==""){
	  	alert("Enter the Description");
	  	return false;
	}
	
	if(selArr[i].cmbAbnmTypeid.trim()==""){
		alert("Select the Abnormality Type");
		return false;
	}
	if(selArr[i].cmbAbnmTagclassid.trim()==""){
		alert("Select Tag");
		return false;
	}
	if(selArr[i].cmbAbnmImpactid.trim()==""){
		alert("Select Abnormality Impact");
		return false;
	}	
if(selArr[i].cmbAbnmTagclassid=="TAG000002"){
	if(selArr[i].cmbAbnmTradeid.trim()==""){
	  alert("Select Maintenance Section");
	  return false;
	}	
}
if(selArr[i].cmbAbnmTagclassid=="TAG000001"){
	if(selArr[i].cmbAbnmResponsibleid.trim()==""){
		alert("Select Responsiblity");
  	    return false;
	}
}

/* if(selArr[i].cmbAbnmStatus=="C"){
  if(selArr[i].txtAbnmCountermeasure.trim()==""){
    aler("Enter the Counter Measure");
    return false;
  } 
  else if(selArr[i].cmbAbnmCompletedby.trim()==""){
	alert("Enter the Completed by");
	return false;
  }
  else if(selArr[i].dteAbnmWoendtime.trim()==""){
		alert("Select the Completed Date");
		return false;
	  }  
} */
}
return true;
}

function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName){
	var Rowrow = jQuery("#AbnMutipleGrd").jqGrid('getDataIDs');
	var rowid ="";
	var jsonArrO = '[';
	for ( i =0; i <Rowrow.length; i++) {
		rowid = Rowrow[i];
	  var isChecked =jQuery("#chkAbnmShutdownmaint_AbnMutipleGrd_"+rowid).is(':checked');
    }		
}

function frmMultipleAbnormality_beforeSubmit(){
 var sectionId=jQuery("#frmMultipleAbnormality input[id='section']").val();
 var cellId=jQuery("#frmMultipleAbnormality input[id='cell']").val(); 
 var flId=jQuery("#frmMultipleAbnormality input[id='flid']").val();
 var docId=jQuery("#hdnRefDocId").val();
 var types=jQuery("#hdnRefDoctype").val();
 var remarks=jQuery("#hdnremarks").val();
 if(cellId==null || cellId==''){
  alert("Enter the JH");
  return false;
 }
var gridval=getGridSelectArray('AbnMutipleGrd');
var Ism= getSelectdRowsAtt('AbnMutipleGrd', 'chkAbnmShutdownmaint', '');
if(gridval != ""){
 if( validateMandtory(gridval))	
   return 'abnormalitydetails='+encodeURIComponent(gridval)+'&flId='+flId+'&sectionId='+sectionId+"&Ism="+Ism+"&docId="+docId+"&types="+types+"&remarks="+encodeURIComponent(remarks);
}
return false;
}

function loadCompleteAction(){   
	    var abnactnpln=jQuery('#hdnactnmode').val();
		var rowid=jQuery('#hdnrowid').val();
		var MstKeyid=jQuery('#hdnAbnmdKeyid').val();
		if(abnactnpln==true || abnactnpln=='true'){
        	if(MstKeyid.trim().length>0){
				 var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
				 var refDocId = jQuery("#AbnMutipleGrd").jqGrid('getCell',rowid, "hdnAbnmdKeyid");  
				 var mainTask = jQuery("#AbnMutipleGrd").jqGrid('getCell',rowid, "txtAbnmDescription");
				 //var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
			   //  var keyid=jQuery('#txtMomsKeyid').val();
				 var mode = jQuery("#frmMultipleAbnormality input[id=mode]").val();
				 if(mode.trim().length>0){
					 apMode = "view";
  					 openActionPlan("Actionplane",MstKeyid,"ABN",flid,mainTask,refDocId,"","");
				 }
				/*  else{
					 var dataStr ="actPlanRefMasId="+MstKeyid+"&actPlanRefDocType=MOM"+"&flid="+flid+"&actPlanMainTask="+escape(mainTask) +"&actPlanRefDtlId="+refDocId;
						dataStr+="&actPlanRefDate="+pasdate+"&apMode=create"+"&type="+type+"&pillarid="+pillarid;
						LoadPopUp("Actionplane","ActionPlan_input.api?"+dataStr,true,"83%","90%","3%","7%","","Action Plan","",false);
				 } */
			}
		}
        else
			 { 
        	     //jQuery('#txtMomsKeyid').val("");
			     //jQuery('#txtMomsKeyid').val(result.MomMstkeyid);
				 return false;
			 }
}

function frmMultipleAbnormality_successsCallback(result){
	 var abnKeyid=result.keyId;
    var openfilemgr =result.openfilemgr;
    var actionplan =result.abnactnpln;
    var flid =result.flid;
    var mainTask =result.mainTask;
    var url = jQuery("#hiddenUrl").val();
    //alert("flid:"+flid);
    //alert("mainTask:"+mainTask);
    //alert("KeyId:"+abnKeyid);
    jQuery("#AbnMutipleGrd").trigger("reloadGrid");
    //alert("After Reload");
    viewGrid(url,"q=2");
  //  alert("File Manager:::"+openfilemgr);
  if (actionplan==true ) {
		 //var keyid = result.returnData.keyId;
		// alert("KeyId:"+abnKeyid);
		 openActionPlan("Actionplane","","ABN",flid,mainTask,abnKeyid,"","");
	}
    if (openfilemgr==true ) {
		 var keyid = result.returnData.keyId;
		// alert("KeyId:"+keyid);
		 fileManagerPopUp(keyid,"ABN","","","", "");
	}  
   // processGridnew("MultipleAbnormality_input.abnForm","?q=2","AbnMutipleGrd","pager","","","","loadCompleteAction");
 // navigateToPrevForm();
	 /*var rowid=result.RowId;
	 jQuery('#hdnrowid').val(rowid);
     var abnactnpln =result.abnactnpln;
     processGridnew("MultipleAbnormality_input.abnForm","?q=2&abnKeyid="+abnKeyid,"AbnMutipleGrd","pager","","","","loadCompleteAction");*/
     
     
     
     
}

function AbnMutipleGrdbtnFilManage_onClick(result){
	 var keyid=jQuery('#hdnkeyId').val();
	 var rowid=result.rowId;
	//alert("rowid :"+rowid);
	var btnid=result.btnId;
	var refDocId = jQuery("#AbnMutipleGrd").jqGrid('getCell',rowid,"hdnAbnmdKeyid");
/* 	 if(keyid.length==0){
		 alert("Please Enter the Mandatory");
		 return false;
	 }
	 else{
			var rowid=result.rowId;
			
			saveForm('frmMultipleAbnormality','MultipleAbnormality_save.abnForm?openfilemgr=openfilemgr&rowid='+rowid);
			fileManagerPopUp(refDocId,"ABN","","","", "");
	 } */
	 
	 if(refDocId.length==1){
			//  alert("Save");
	        saveForm('frmMultipleAbnormality','MultipleAbnormality_save.abnForm?openfilemgr=openfilemgr&rowid='+rowid);
		  }
		  else{
			  fileManagerPopUp(refDocId,"ABN","","","", "");
		  }
}

function AbnMutipleGrdbtnActionplan_onClick(result){
	var rowid=result.rowId;
	//alert("rowid :"+rowid);
	var btnid=result.btnId;
	var refDocId = jQuery("#AbnMutipleGrd").jqGrid('getCell',rowid,"hdnAbnmKeyid");
    var	mainTask = getFieldValue("txtAbnmDescription_AbnMutipleGrd_"+rowid);
	if (mainTask.trim().length==0) {
		popupCommonErrorMsg("Enter Description Details");
		return false;
	}
	
	var abn = "ABN";
	var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
	var mode="view";
	//var Mstkeyid = jQuery('#hdnAbnmdKeyid').val();	
	//alert(Mstkeyid);
	//var ActionPlan = jQuery("#AbnMutipleGrd").jqGrid('getCell',rowid,"txtApldActionplan");
	//alert(ActionPlan);
	//alert("Mstkeyid"+Mstkeyid);
	//if(Mstkeyid.trim().length>0){
		//if(refDocId.trim().length>0){
		// var flid = jQuery("#frmMultipleAbnormality input[id='flid']").val();
		 //alert(flid);	
			  if(refDocId.length==1){
				//  alert("Save");
       	        saveForm('frmMultipleAbnormality','MultipleAbnormality_save.abnForm?abnactnpln=abnactnpln&rowid='+rowid);
			  }
			  else{
				  openActionPlan("Actionplane","","ABN",flid,mainTask,refDocId,"","");
			  }
	       //  }	
		//}
	}
		
		/* else
			 saveForm('frmMultipleAbnormality','MultipleAbnormality_save.abnForm?abnactnpln=abnactnpln&rowid='+rowid);
	}else
		 saveForm('frmMultipleAbnormality','MultipleAbnormality_save.abnForm?abnactnpln=abnactnpln&rowid='+rowid);
	} */





</script>
<form id="frmMultipleAbnormality">
<div id='wrapperRpt' >
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;" >

<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
	
			<div  id="frmMultipleAbnormalityFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="cmbAbnmFactoryid" value="${requestScope.abnormalityBean.factory}"></input>			
			<input type="hidden" id="section" name="cmbAbnmSectionid" value="${requestScope.abnTlAbnormality.abnmSectionid}"  ></input>
			<input type="hidden" id="cell" name="cmbAbnmCellid" value="${requestScope.abnTlAbnormality.abnmCellid}"></input>
			<input type="hidden" id="machine" name="cmbAbnmEquipmentid1" value="${requestScope.abnTlAbnormality.abnmEquipmentid}"></input>
			<input type="hidden" id="flid" name="cmbAbnmFlid" value="${requestScope.abnTlAbnormality.abnmFlid}"></input>
			
			</div>
			
			<div id="abnmfunLocation" style="width: 50%; "></div>

			<table>
			<tr>
			<td>	
			<span id="err_abnmfunLocation" class="tpm-errormsg"></span>
			</td>
			</tr>
			</table>
             <div style="margin-left:0px; margin-top:10px;">
						  <input type="button" class="easyui-button" value ="Add" id="btnAdd" style="height:25px;" >
						  </div>
						   <div style="margin-left:55px; margin-top:-25px;">
						  <span>
						  
						  <input type="button" class="easyui-button" value ="Delete" id="btndelete" style="height:23px;" >
						  
						  </span>	
						   
							</div>
							
							</div>
            
							</td>
							</tr></table>

<div style="margin-top: 0px">
<table  id='AbnMutipleGrd' >
			<tr>
				<td >
				</td>
			</tr>
		</table>
		<div id='pager'></div>
		</div>
</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="hdnfrmType" name="hdnfrmType" value="${requestScope.frmType}"/>
<input type="hidden" name="hdnRespond" id="hdnRespond" value="${requestScope.abnTlAbnormality.abnmResponsibleid}" />
<input type="hidden" id="hdncurrentDate" name="hdncurrentDate" value="${requestScope.currentDate}">
 <input type="hidden" id="hdnrowid" name="hdnrowid" value=""/>
 <input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}">
 <input type="hidden" id="hdnRefDoctype" name="hdnRefDoctype" value="${requestScope.refDoctype}"/>
 <input type="hidden" id="hdnRefDocId" name="hdnRefDocId" value="${requestScope.refDocId}"/>
 <input type="hidden" id="hdnclirefDocID" name="hdnclirefDocID" value="${requestScope.clirefDocID}">
 <input type="hidden" id="hdnremarks" name="hdnremarks" value="${requestScope.remarks}">   
 
 <input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}">   
</form>