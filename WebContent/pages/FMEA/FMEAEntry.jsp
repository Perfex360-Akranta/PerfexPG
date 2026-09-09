<script><!--
jQuery(document).ready(function(){
	initialiseForm('frmFmeaEntry');
	jQuery('#submitForm').val('frmFmeaEntry'); 
	fillComboBox('frmFmeaEntry','cmbFmeaPreparedby', 'employee.commonFilter');
	fillComboBox('frmFmeaEntry','cmbFmpmProcessid', 'process.commonFilter');
	fillComboBox('frmFmeaEntry','cmbFmpmSupprocessid', 'subprocess.commonFilter');
	fillComboBox('frmFmeaEntry','cmbFmdmSystemid', 'systemCombo.fmeaf');
	fillComboBox('frmFmeaEntry','cmbFmdmSupsystemid', 'subSystemCombo.fmeaf');
	fillComboBox('frmFmeaEntry','cmbFmeqEquipid', 'machineCombo.commonFilter');
	
	
	fillComboBox('frmFmeaEntry','cmbFmeqSupequipid', 'subEqpmnCombo.fmeaf');
	
	
	formatDateBox('dteFmeaDate','dd-MMM-YYYY');
	
	disableField('frmFmeaEntry', "txtFmeaNo");
	var fmeaType=jQuery("#txtFmeaType").val();
	if(fmeaType=='design'){
		fileManagerPopUp("","DFM","frmFmeaEntry","btnfilemgr","fmeaFilemgr");
	}
	else if(fmeaType=='equipment'){
		fileManagerPopUp("","PFM","frmFmeaEntry","btnfilemgr","fmeaFilemgr");
	}
	else if(fmeaType=='process'){
		fileManagerPopUp("","EFM","frmFmeaEntry","btnfilemgr","fmeaFilemgr");
	}
	var factId = jQuery("#frmFmeaEntry input[id='factory']").val();
    var sectionId = jQuery("#frmFmeaEntry input[id='section']").val();
    var cellId = jQuery("#frmFmeaEntry input[id='cell']").val();
    var machId = jQuery("#frmFmeaEntry input[id='machine']").val();
    var flid = jQuery("#frmFmeaEntry input[id='flid']").val(); 
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
    
    if(fmeaType=='process')
    {
    	loadFunctionalLocation("designfunlocation","functionalLocprocess.fmeaf","fmeafunLocationValues","frmFmeaEntry",dataStr);
    }else if(fmeaType=='equipment')
    {
    	loadFunctionalLocation("designfunlocation","functionalLocequp.fmeaf","fmeafunLocationValues","frmFmeaEntry",dataStr);
    }else
    {
    	loadFunctionalLocation("designfunlocation","functionalLocequp.fmeaf","fmeafunLocationValues","frmFmeaEntry",dataStr);
    }
    
    
    showFmeaType();
    loadDtlsGrid();
    loadReviewGrid();
    jQuery("#btnDelete").click(function(){
   	 	deleteDtls();
    });
    jQuery("#btnXlsView").click(function(){
    	var keyid= getFieldValue("txtFmeaKeyid");
        var type=getFieldValue("txtFmeaType");
        var fmeaDocType=jQuery("#txtFmeaDoctype").val();
    	var fmeaDocmstid=jQuery("#txtFmeaDocmstid").val();
    	var fmeaDocdtlsid=jQuery("#txtFmeaDocdtlsid").val();
    	window.open("FMEA_viewExcel.fmeaf?keyid="+keyid+"&type="+type+"&fmeaDocType="+fmeaDocType+"&fmeaDocmstid="+fmeaDocmstid+"&fmeaDocdtlsid="+fmeaDocdtlsid);
        
    });
    jQuery("#btnAddNew").click(function(){
    	var row = jQuery("#fmeadetailsgrid").jqGrid("getDataIDs");
    	addRow(row);
    });
        
    
	jQuery("#cmbFmpmProcessid").combobox({onRequest:function(){
		
		var cellId = jQuery("#frmFmeaEntry input[id='cell']").val();
	    var machId = jQuery("#frmFmeaEntry input[id='machine']").val();
	    var section = jQuery("#frmFmeaEntry input[id='section']").val();
	    
		
	    if(cellId != undefined && cellId.trim() != "" && cellId.trim() !=null)
		{	
			//reloadCombo("frmFmeaEntry","cmbFmeqEquipid","machineCombo.commonFilter?cellId="+cellId);
			var retStr = "flid="+cellId;
			return retStr;
	    }
		
		if(section != undefined && section.trim() != "" && section.trim() !=null)
		{
			var retStr = "flid="+section; 
			return retStr;
		}
		
		
	}});
	
	
	
	   
	   jQuery("#cmbFmeaPreparedby").combobox({onRequest:function(){		  
		
		var locnId = jQuery("#frmFmeaEntry input[id='location']").val();
		var flid = jQuery("#frmFmeaEntry input[id='flid']").val();
		//var section = jQuery("#frmFmeaEntry input[id='section']").val();
		
		//alert('others' + section);
		
		
			if(flid == '' || flid == ' ' || flid == undefined)
			 {
				var retStr = "locnId="+locnId;
			 }
			else
			{
				var retStr = "flid="+flid;
			}
		
		//alert(retStr);
		return retStr;

		}});   
	   
	
		jQuery("#cmbFmeqEquipid").combobox({onRequest:function(){
		
		var cellId = jQuery("#frmFmeaEntry input[id='cell']").val();	       
		
	    if(cellId != undefined && cellId.trim() != "" && cellId.trim() !=null)
		{	
			//reloadCombo("frmFmeaEntry","cmbFmeqEquipid","machineCombo.commonFilter?cellId="+cellId);
			var retStr = "cellId="+cellId;
			//alert("cellId"+cellId);
			return retStr;
	    }
		
		
	}});
	
});

/* function frmFmeaEntrycmbFmeqEquipid_onSelect(record) 	{
	
	var cellid = jQuery("#frmCriticality input[id='cell']").val();
	//alert("cellid"+cellid);
	//alert('sectionid' + sectionid);
	if(cellid=='' || cellid==' ' || cellid == undefined )
	{
		    	alert('Select JH');
		    	setFieldValue('cmbFmeqEquipid',cellid);
		    	return false;
	}
} */

function frmFmeaEntrycmbFmpmProcessid_onSelect(record) 	{
	//jQuery('#cmbQirdSubprocessid').combobox('clear');
	//alert(123);
	var sectionid = jQuery("#frmFmeaEntry input[id='section']").val();
	//alert('sectionid' + sectionid);
	if(sectionid=='' || sectionid==' ' || sectionid == undefined )
	{
		    	alert('Select DMT');
		    	setFieldValue('cmbFmpmProcessid','');
		    	return false;
	}
	else
	{  //alert('else');
		reloadCombo("frmFmeaEntry","cmbFmpmSupprocessid","subprocess.commonFilter?&processId="+record.id);
	}

	
}

function loadDtlsGrid(){
	//var url = jQuery('#hiddenUrl').val();
	var keyid= getFieldValue("txtFmeaKeyid");
    var type=getFieldValue("txtFmeaType");
    var fmeaDocType=jQuery("#txtFmeaDoctype").val();
	var fmeaDocmstid=jQuery("#txtFmeaDocmstid").val();
	var fmeaDocdtlsid=jQuery("#txtFmeaDocdtlsid").val();
    processGridnew("FMEAEntry_view.fmeaf","&q=2&keyid="+keyid+"&type="+type+"&fmeaDocType="+fmeaDocType+"&fmeaDocmstid="+fmeaDocmstid+"&fmeaDocdtlsid="+fmeaDocdtlsid,"fmeadetailsgrid","fmeadetailspager","","");
    
}

function loadReviewGrid(){
	var url = jQuery('#hiddenUrl').val();
    var keyid= getFieldValue("txtFmeaKeyid");
    var type=getFieldValue("txtFmeaType");
    var fmeaDocType=jQuery("#txtFmeaDoctype").val();
	var fmeaDocmstid=jQuery("#txtFmeaDocmstid").val();
	var fmeaDocdtlsid=jQuery("#txtFmeaDocdtlsid").val();
    processGridnew("FMEARe_view.fmeaf","q=2&keyid="+keyid+"&type="+type+"&fmeaDocType="+fmeaDocType+"&fmeaDocmstid="+fmeaDocmstid+"&fmeaDocdtlsid="+fmeaDocdtlsid,"fmeareviewgrid","fmeareviewpager","","");
}

function cmbSeverity_fmeadetailsgrid_onSelect(record,rowId){		 
	//alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	 var SeverityValue =record.id;
	 calRPN(rowId, SeverityValue, null, null);	
	//calRPN(rowId);
}

function cmbOccurrence_fmeadetailsgrid_onSelect(record,rowId){
	 var OccurrenceValue =record.id;
	 calRPN(rowId, null, OccurrenceValue, null);
	//calRPN(rowId);
}

function cmbDetection_fmeadetailsgrid_onSelect(record,rowId){
	 var DetectionValue =record.id;
	 calRPN(rowId, null, null, DetectionValue);
	//calRPN(rowId);
}

function calRPN(rowId , Severityparam, Occurrenceparam, Detectionparam ){
	var jqGridId="fmeadetailsgrid";
	
	//var sev= getFieldValue("cmbSeverity_"+jqGridId+"_"+rowId);
	var sev= Severityparam == null ? getFieldValue("cmbSeverity_"+jqGridId+"_"+rowId):Severityparam;
	
	
	//var occ =getFieldValue("cmbOccurrence_"+jqGridId+"_"+rowId);
	var occ = Occurrenceparam == null ? getFieldValue("cmbOccurrence_"+jqGridId+"_"+rowId):Occurrenceparam;
	
	//var det= getFieldValue("cmbDetection_"+jqGridId+"_"+rowId);
	var det= Detectionparam == null ? getFieldValue("cmbDetection_"+jqGridId+"_"+rowId):Detectionparam;
	
	processAjaxCalls("getTotalVal.fmeaf","severity="+sev+"&occurance="+occ+"&detection="+det+"&rowId="+rowId,"onsuccessCallBack" );
	/*var sev=jQuery("#cmbSeverity_"+jqGridId+"_"+rowId).combobox('getText'); 
	var occ=jQuery("#cmbOccurrence_"+jqGridId+"_"+rowId).combobox('getText');
	var det=jQuery("#cmbDetection_"+jqGridId+"_"+rowId).combobox('getText'); 
	sev=sev|0;
	occ=occ|0;
	det=det|0;
	//alert("sev:"+sev+"occ:"+"det:"+det);
	var calrpn=parseInt(sev)*parseInt(occ)*parseInt(det);
	var fmeaType=jQuery("#txtFmeaType").val();
	 if(fmeaType=='design'){
		 jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmddRpn",calrpn);
	 }
	 else if(fmeaType=='equipment'){
		 jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmedRpn",calrpn);
	 }
	 else if(fmeaType=='process'){
		  jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmpdRpn",calrpn);
	 }*/
}
function onsuccessCallBack(result){
	var rowId =result[1][0];
	var fmeaType=jQuery("#txtFmeaType").val();
	var totalVal=result[0][0];
	if(!(result[0][0].length>0))
		totalVal=0;
	if(fmeaType=='design'){
		 jQuery("#fmeadetailsgrid").jqGrid('setCell', rowId,"txtFmddRpn",totalVal);
	 }
	 else if(fmeaType=='equipment'){
		 jQuery("#fmeadetailsgrid").jqGrid('setCell', rowId,"txtFmedRpn",totalVal);
	 }
	 else if(fmeaType=='process'){
		  jQuery("#fmeadetailsgrid").jqGrid('setCell', rowId,"txtFmpdRpn",totalVal);
	 }
}
function ReviewonsuccessCallBack(result){
	var rowId =result[1][0];
	var fmeaType=jQuery("#txtFmeaType").val();
	var totalVal=result[0][0];
	if(!(result[0][0].length>0))
		totalVal=0;
	if(fmeaType=='design'){
		 jQuery("#fmeareviewgrid").jqGrid('setCell', rowId,"txtFmddRerpn",totalVal);
	 }
	 else if(fmeaType=='equipment'){
		 jQuery("#fmeareviewgrid").jqGrid('setCell', rowId,"txtFmedRerpn",totalVal);
	 }
	 else if(fmeaType=='process'){
		  jQuery("#fmeareviewgrid").jqGrid('setCell', rowId,"txtFmpdRerpn",totalVal);
	 }
}

function cmbReSeverity_fmeareviewgrid_onSelect(record,rowId){		 
	//alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	var SeverityValue = record.id;
    calRERPN(rowId, SeverityValue, null, null);
	//calRERPN(rowId);
}

function cmbReOccurrence_fmeareviewgrid_onSelect(record,rowId){
	 var OccurrenceValue = record.id;
	 calRERPN(rowId, null, OccurrenceValue, null);
	//calRERPN(rowId);
}

function cmbReDetection_fmeareviewgrid_onSelect(record,rowId){
	var DetectionValue = record.id;
    calRERPN(rowId, null, null, DetectionValue);
	//calRERPN(rowId);
}

/* function calRERPN(rowId){
	/*var jqGridId="fmeareviewgrid";
	var sev=jQuery("#cmbReSeverity_"+jqGridId+"_"+rowId).combobox('getText'); 
	var occ=jQuery("#cmbReOccurrence_"+jqGridId+"_"+rowId).combobox('getText');
	var det=jQuery("#cmbReDetection_"+jqGridId+"_"+rowId).combobox('getText'); 
	sev=sev|0;
	occ=occ|0;
	det=det|0;
	//alert("sev:"+sev+"occ:"+"det:"+det);
	var calrpn=parseInt(sev)*parseInt(occ)*parseInt(det);
	var fmeaType=jQuery("#txtFmeaType").val();
	 if(fmeaType=='design'){
		 jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmddRerpn",calrpn);
	 }
	 else if(fmeaType=='equipment'){
		 jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmedRerpn",calrpn);
	 }
	 else if(fmeaType=='process'){
		  jQuery("#"+jqGridId).jqGrid('setCell', rowId,"txtFmpdRerpn",calrpn);
	 }*/
	/*var jqGridId="fmeareviewgrid";
	var sev= getFieldValue("cmbReSeverity_"+jqGridId+"_"+rowId);
	var occ =getFieldValue("cmbReOccurrence_"+jqGridId+"_"+rowId);
	var det= getFieldValue("cmbReDetection_"+jqGridId+"_"+rowId);
	
	processAjaxCalls("getTotalVal.fmeaf","severity="+sev+"&occurance="+occ+"&detection="+det+"&rowId="+rowId,"ReviewonsuccessCallBack" );
} */

function calRERPN(rowId, Severityparam, Occurrenceparam, Detectionparam) {
    var jqGridId = "fmeareviewgrid";
    var sev = Severityparam   == null ? getFieldValue("cmbReSeverity_"   + jqGridId + "_" + rowId) : Severityparam;
    var occ = Occurrenceparam == null ? getFieldValue("cmbReOccurrence_" + jqGridId + "_" + rowId) : Occurrenceparam;
    var det = Detectionparam  == null ? getFieldValue("cmbReDetection_"  + jqGridId + "_" + rowId) : Detectionparam;
	processAjaxCalls("getTotalVal.fmeaf","severity="+sev+"&occurance="+occ+"&detection="+det+"&rowId="+rowId,"ReviewonsuccessCallBack" );

}


function fmeadetailsgridbtnActPlan_onClick(result){	
	var rowid=result.rowId;
	var btnid=result.btnId;	
	var refDocId ="";
	var mainTask ="";
	var fmeaType=jQuery("#txtFmeaType").val();
	if(fmeaType=='design'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell', rowid,"txtFmddKeyid");
		mainTask = getFieldValue("txtFmddPotentialfailmode_fmeadetailsgrid_"+rowid);
	}
	else if(fmeaType=='equipment'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell', rowid,"txtFmedKeyid");
		mainTask = getFieldValue("txtFmedPotentialfailmode_fmeadetailsgrid_"+rowid);
	}
	else if(fmeaType=='process'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell', rowid,"txtFmpdKeyid");
		mainTask = getFieldValue("txtFmpdPotentialfailmode_fmeadetailsgrid_"+rowid);
	}
	
	var docType = "FMEA";
	//var flid = jQuery("#frmFmeaEntry input[id='flid']").val(); 
	var cellid = jQuery("#frmFmeaEntry input[id='cell']").val();
	jQuery("#hdnSelect").val('Y');
	processAjaxCalls("FMEAEntryFLID_input.fmeaf", "&cellid="+cellid+"&rowid="+rowid, 'FmeaFLidSuccessCallBack','FmeaFLidErrorCallBack');
	
	//alert(flid);
	
	
	/*var keyid = jQuery("#txtFmeaKeyid").val();
	//alert("fmeaType:"+fmeaType+",refDocId:"+refDocId+",mainTask:"+mainTask+",docType:"+docType+",flid="+flid+",keyid:"+keyid);
	if(refDocId.trim().length>0)
		openActionPlan("divFmeaActionplan",keyid,docType,cellid,mainTask,refDocId,"","create");
	else
		saveForm("frmFmeaEntry","FMEAEntry_save.fmeaf?actplan=true&rowid="+rowid,"");*/
}

function FmeaFLidSuccessCallBack(result)
{
	//alert(1); 
 var flid=result.successData.flid;
 var row=result.successData.rowid;
 //alert("flid"+flid);
// alert("flid"+row);
 var keyid = jQuery("#txtFmeaKeyid").val();
// alert(keyid);
 var fmeaType=jQuery("#txtFmeaType").val();
 jQuery("#hdnflid").val(flid);
 //alert(fmeaType);
 var refDocId ="";
var mainTask ="";
 if(fmeaType=='design'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell',row,"txtFmddKeyid");
		mainTask = getFieldValue("txtFmddPotentialfailmode_fmeadetailsgrid_"+row);
	}
	else if(fmeaType=='equipment'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell',row,"txtFmedKeyid");
		mainTask = getFieldValue("txtFmedPotentialfailmode_fmeadetailsgrid_"+row);
	}
	else if(fmeaType=='process'){
		refDocId = jQuery("#fmeadetailsgrid").jqGrid('getCell',row,"txtFmpdKeyid");
		mainTask = getFieldValue("txtFmpdPotentialfailmode_fmeadetailsgrid_"+row);
	}
	
	var docType = "FMEA";
	//alert("refDocId"+refDocId);
	if(refDocId.trim().length>0)
		{
		openActionPlan("divFmeaActionplan",keyid,docType,flid,mainTask,refDocId,"","create");
		}
	else
		{
		saveForm("frmFmeaEntry","FMEAEntry_save.fmeaf?actplan=true&rowid="+row,"");
		}
	

}

function showFmeaType(){
	var fmeaType=jQuery("#txtFmeaType").val();
	//alert("fmeaType:"+fmeaType);
	jQuery("#div"+fmeaType).css('display','block');
}


function addRow(row)
{
	 var emptyItem="";
	 var fmeaType=jQuery("#txtFmeaType").val();
	 if(fmeaType=='design'){
		 emptyItem =[{txtFmddKeyid:" ",txtFmddItem:" ",txtFmddFunction:" ",txtFmddPotentialfailmode:" ",txtFmddPotentialeffectfail:" ",
			 txtFmddSeverityKeyid:" ",cmbSeverity:" ",txtFmddPotentialcausefail:" ",txtFmddOccurrenceKeyid:" ",cmbOccurrence:" ",txtFmddCurrentcontrol:" ",
			 txtFmddDetectionKeyid:" ",cmbDetection:" ",txtFmddRpn:" ",txtFmddActionplan:" ",btnActPlan:" ",txtActPlanStatus:" "}];
	 }
	 else if(fmeaType=='equipment'){
		 emptyItem =[{txtFmedKeyid:" ",txtFmedComponent:" ",txtFmedFunction:" ",txtFmedFunctionfail:" ",txtFmedPotentialfailmode:" ",txtFmedPotentialeffectfail:" ",
			 txtFmedSeverityKeyid:" ",cmbSeverity:" ",txtFmedPotentialcausefail:" ",txtFmedOccurrenceKeyid:" ",cmbOccurrence:" ",txtFmedCurrentcontrol:" ",
			 txtFmedDetectionKeyid:" ",cmbDetection:" ",txtFmedRpn:" ",txtFmedActionplan:" ",btnActPlan:" ",txtActPlanStatus:" "}];
	 }
	 else if(fmeaType=='process'){
		 emptyItem =[{txtFmpdKeyid:" ",txtFmpdProcessstep:" ",txtFmpdKeyprocessinput:" ",txtFmpdPotentialfailmode:" ",txtFmpdPotentialeffectfail:" ",
			 txtFmpdSeverityKeyid:" ",cmbSeverity:" ",txtFmpdPotentialcausefail:" ",txtFmpdOccurrenceKeyid:" ",cmbOccurrence:" ",txtFmpdCurrentcontrol:" ",
			 txtFmpdDetectionKeyid:" ",cmbDetection:" ",txtFmpdRpn:" ",txtFmpdActionplan:" ",btnActPlan:" ",txtActPlanStatus:" "}];
	 }
	 if ( row == null || row == '' || parseInt(row) <= 0) {		 
		jQuery("#fmeadetailsgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		
		jQuery("#fmeadetailsgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

function deleteDtls(){
	var keyid= jQuery("#txtFmeaKeyid").val();
	var fmeaType=jQuery("#txtFmeaType").val();
	var flid= jQuery("#frmFmeaEntry input[id='flid']").val();	
	var preparedBy=getFieldValue("cmbFmeaPreparedby");
	var fmeaNo=getFieldValue("txtFmeaNo");
	var preparedDate=getFieldValue("dteFmeaDate");
	var coreTeam=getFieldValue("txtFmeaCoreteam");
	var docType=getFieldValue("txtFmeaDoctype");
	var docmstid=getFieldValue("txtFmeaDocmstid");
	var docdtlsid=getFieldValue("txtFmeaDocdtlsid");
	
	var selArray =  jQuery("#fmeadetailsgrid").jqGrid('getGridParam', 'selarrrow');
	if (selArray.length <= 0)
	{  
		alert("No Data Selected");
		return false;
	}
	
	var gridval=getGridSelectArray('fmeadetailsgrid'); 
	var gridvalReview=getGridSelectArray('fmeareviewgrid'); 
	//alert(gridval+gridvalReview);	
	
	var dataStr="&type="+fmeaType;
	if(fmeaType=='design'){
		dataStr+="&txtFmdmKeyid="+keyid+"&txtFmdmFlid="+flid+"&dteFmdmDate="+preparedDate+"&txtFmdmNo="+fmeaNo+"&cmbFmdmPreparedby="+preparedBy+"&txtFmdmCoreteam="+coreTeam;
		dataStr+="&txtFmdmDoctype="+docType+"&txtFmdmDocmstid="+docmstid+"&txtFmdmDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='equipment'){
		dataStr+="&txtFmeqKeyid="+keyid+"&txtFmeqFlid="+flid+"&dteFmeqDate="+preparedDate+"&txtFmeqNo="+fmeaNo+"&cmbFmeqPreparedby="+preparedBy+"&txtFmeqCoreteam="+coreTeam;
		dataStr+="&txtFmeqDoctype="+docType+"&txtFmeqDocmstid="+docmstid+"&txtFmeqDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='process'){
		dataStr+="&txtFmpmKeyid="+keyid+"&txtFmpmFlid="+flid+"&dteFmpmDate="+preparedDate+"&txtFmpmNo="+fmeaNo+"&cmbFmpmPreparedby="+preparedBy+"&txtFmpmCoreteam="+coreTeam;
		dataStr+="&txtFmpmDoctype="+docType+"&txtFmpmDocmstid="+docmstid+"&txtFmpmDocdtlsid="+docdtlsid;
	}
	var gridData=dataStr;
	gridData+='&fmeadtls='+gridval;
	gridData+='&fmeadtlsreview='+gridvalReview;
	if(gridval.trim().length>0 || gridvalReview.trim().length>0  ){	
		var r =confirm("Are you sure to Delete?");
		if(r){	
			processAjaxCalls("FMEAEntryDtls_delete.fmeaf",gridData,"fmeaDeleteDtlsSuccess","fmeaDeleteDtlsError");
		}
		else
			return false;	
	}
	else{
		showCommonErrorMsg("Enter Data to save");
		return false;
	}
}

function fmeaDeleteDtlsSuccess(result){
	alert(result.successData.msg);
	jQuery("#fmeadetailsgrid").trigger("reloadGrid");
	jQuery("#fmeareviewgrid").trigger("reloadGrid");	
}

function fmeaDeleteDtlsError(result){
	
}

function frmFmeaEntry_beforeDelete(){
	var keyid= jQuery("#txtFmeaKeyid").val();
	var fmeaType=jQuery("#txtFmeaType").val();
	var flid= jQuery("#frmFmeaEntry input[id='flid']").val();	
	var preparedBy=getFieldValue("cmbFmeaPreparedby");
	var fmeaNo=getFieldValue("txtFmeaNo");
	var preparedDate=getFieldValue("dteFmeaDate");
	var coreTeam=getFieldValue("txtFmeaCoreteam");
	var docType=getFieldValue("txtFmeaDoctype");
	var docmstid=getFieldValue("txtFmeaDocmstid");
	var docdtlsid=getFieldValue("txtFmeaDocdtlsid");
	if(keyid.length>0){	
		var r =confirm("Are you sure to Delete?");
		if(r){	
			
		}
		else
			return false;	
	}
	else
		return false;
	var dataStr="&type="+fmeaType;
	if(fmeaType=='design'){
		dataStr+="&txtFmdmKeyid="+keyid+"&txtFmdmFlid="+flid+"&dteFmdmDate="+preparedDate+"&txtFmdmNo="+fmeaNo+"&cmbFmdmPreparedby="+preparedBy+"&txtFmdmCoreteam="+coreTeam;
		dataStr+="&txtFmdmDoctype="+docType+"&txtFmdmDocmstid="+docmstid+"&txtFmdmDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='equipment'){
		dataStr+="&txtFmeqKeyid="+keyid+"&txtFmeqFlid="+flid+"&dteFmeqDate="+preparedDate+"&txtFmeqNo="+fmeaNo+"&cmbFmeqPreparedby="+preparedBy+"&txtFmeqCoreteam="+coreTeam;
		dataStr+="&txtFmeqDoctype="+docType+"&txtFmeqDocmstid="+docmstid+"&txtFmeqDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='process'){
		dataStr+="&txtFmpmKeyid="+keyid+"&txtFmpmFlid="+flid+"&dteFmpmDate="+preparedDate+"&txtFmpmNo="+fmeaNo+"&cmbFmpmPreparedby="+preparedBy+"&txtFmpmCoreteam="+coreTeam;
		dataStr+="&txtFmpmDoctype="+docType+"&txtFmpmDocmstid="+docmstid+"&txtFmpmDocdtlsid="+docdtlsid;
	}
	var gridData=dataStr;
		
	var selArray =  jQuery("#fmeadetailsgrid").jqGrid('getGridParam', 'selarrrow');
	if (selArray.length <= 0)
	{  
		alert("No Data Selected");
		return false;
	}
	
	var gridval=getGridSelectArray('fmeadetailsgrid'); 
	var gridvalReview=getGridSelectArray('fmeareviewgrid'); 
	//alert(gridvalReview);	
	
	gridData+='&fmeadtls='+gridval;
	gridData+='&fmeadtlsreview='+gridvalReview;
	//gridData  +=dataStr; 
  	 //alert(gridData);
    if(gridval.trim().length>0 || gridvalReview.trim().length>0){       
		return gridData;
    }
    else{        
        setTimeout(function() {
			showCommonErrorMsg("Select Any Fmea Detail To Delete");
		}, 200);
		div_err();
		return false;
    }
    return gridData;
   //	processAjaxCalls("FMEAEntryDtls_delete.fmeaf",gridData,"fmeaDeleteDtlsSuccess","fmeaDeleteDtlsError");	
}
function divFmeaActionplan_onClose(result){
	jQuery("#fmeadetailsgrid").trigger("reloadGrid");
	jQuery("#fmeareviewgrid").trigger("reloadGrid");	
	return true;
}
function frmFmeaEntry_successsCallback(result)
{
	 var actplan=result.successData.actplan;
	 var keyid=result.successData.keyId;
	 if(keyid.trim().length>0){	
	 	setFieldValue("txtFmeaKeyid",keyid);
	 	setFieldValue("txtFmeaNo",keyid);
	 	
	 	//loadDtlsGrid();
	 	var keyid= getFieldValue("txtFmeaKeyid");
	    var type=getFieldValue("txtFmeaType");
	    var fmeaDocType=jQuery("#txtFmeaDoctype").val();
		var fmeaDocmstid=jQuery("#txtFmeaDocmstid").val();
		var fmeaDocdtlsid=jQuery("#txtFmeaDocdtlsid").val();
		var url="FMEAEntry_getData.fmeaf?q=2&keyid="+keyid+"&type="+type+"&fmeaDocType="+fmeaDocType+"&fmeaDocmstid="+fmeaDocmstid+"&fmeaDocdtlsid="+fmeaDocdtlsid;
	    jQuery("#fmeadetailsgrid").setGridParam({url:url}).trigger('reloadGrid');
	    //jQuery("#fmeareviewgrid").setGridParam({url:url}).trigger('reloadGrid');
	    loadReviewGrid();
	 }	
	 //alert('actplan'+actplan);
	 if(actplan==true){
		var rowId=result.successData.rowid;
		var refDocId=result.successData.dtlkeyid;		
		var activity=result.successData.activity;
		var flid =null;	
		if(jQuery("#hdnSelect").val()=='Y'&&jQuery("#hdnSelect").val().length!=0)
			{
			flid=jQuery("#hdnflid").val();
			//alert(jQuery("#hdnflid").val());
			}
		else{
			flid = jQuery("#frmFmeaEntry input[id='flid']").val();	
		}
		
		
		/*alert('rowId'+rowId);
		alert('refDocId'+refDocId);
		alert('keyid'+keyid);*/				
		//jQuery("#fmeadetailsgrid").trigger("reloadGrid"); 
		//var mainTask = getFieldValue("txtRasdActivity_fmeadetailsgrid_"+rowId);
			//var cellid = jQuery("#frmFmeaEntry input[id='cell']").val();
		var docType = "FMEA";
		
		//processAjaxCalls("FMEAEntryFLID_input.fmeaf", "&cellid="+cellid+"&rowid="+rowId, 'FmeaFLidSuccessCallBack','FmeaFLidErrorCallBack');
	
		if(refDocId.trim().length>0)
			openActionPlan("divFmeaActionplan",keyid,docType,flid,activity,refDocId,"","create");		
 	}	
 	/* else{  
		if (jQuery("#txtFmeaKeyid").val().trim().length>0){
			jQuery("#fmeadetailsgrid").trigger("reloadGrid");
			jQuery("#fmeareviewgrid").trigger("reloadGrid");
		}  
		else{		
			clearFields();
		}
 	} */
 	else{  
 	    if (jQuery("#txtFmeaKeyid").val().trim().length>0){
 	        jQuery("#fmeadetailsgrid").trigger("reloadGrid");
 	        jQuery("#fmeareviewgrid").trigger("reloadGrid");
 	        refreshButtonPress();
 	    }  
 	    else{		
 	        clearFields();
 	        refreshButtonPress();
 	    }
 	  }
} 

function frmFmeaEntry_beforeSubmit()
{	
	var keyid= jQuery("#txtFmeaKeyid").val();
	var fmeaType=jQuery("#txtFmeaType").val();
	var flid= jQuery("#frmFmeaEntry input[id='flid']").val();	
	var preparedBy=getFieldValue("cmbFmeaPreparedby");
	var fmeaNo=getFieldValue("txtFmeaNo");
	var preparedDate=getFieldValue("dteFmeaDate");
	var coreTeam=getFieldValue("txtFmeaCoreteam");
	var docType=getFieldValue("txtFmeaDoctype");
	var docmstid=getFieldValue("txtFmeaDocmstid");
	var docdtlsid=getFieldValue("txtFmeaDocdtlsid");
	//var actPlanText = getFieldValue("txtFmedActionplanText");
	//alert("action "+actPlanText);
	//alert("frmFmeaEntry_beforeSubmit");
	
	
	
	if(keyid.trim().length>0){
		var r =confirm("Data have been changed, Do you want to proceed?");
		if(r){			
		}
		else
			return false;
	}
	
	/* var selArray =  jQuery("#fmeadetailsgrid").jqGrid('getGridParam', 'selarrrow');
	//alert(selArray.length);
	if (selArray.length == 0)
	{  	//alert(selArray.length);
		alert("No Data Selected");
		return false;
	}
	 */
	 
	 
	 var rowIds = jQuery("#fmeadetailsgrid").jqGrid('getDataIDs');

	 if (rowIds.length == 0) {
	     alert("No Data Selected");
	     return false;
	 }

	var gridval=getGridSelectArray('fmeadetailsgrid'); 
	//alert("gridval"+gridval)
	var gridvalReview=getGridSelectArray('fmeareviewgrid'); 
	//alert(gridvalReview);	
	
	// ÃÂ°ÃÂÃÂÃÂ¥ CRITICAL
jQuery("#fmeadtls").val(gridval);
jQuery("#fmeadtlsreview").val(gridvalReview);

	
	var dataStr="&type="+fmeaType;
	if(fmeaType=='design'){
		dataStr+="&txtFmdmKeyid="+keyid+"&txtFmdmFlid="+flid+"&dteFmdmDate="+preparedDate+"&txtFmdmNo="+fmeaNo+"&cmbFmdmPreparedby="+preparedBy+"&txtFmdmCoreteam="+coreTeam;
		dataStr+="&txtFmdmDoctype="+docType+"&txtFmdmDocmstid="+docmstid+"&txtFmdmDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='equipment'){
		dataStr+="&txtFmeqKeyid="+keyid+"&txtFmeqFlid="+flid+"&dteFmeqDate="+preparedDate+"&txtFmeqNo="+fmeaNo+"&cmbFmeqPreparedby="+preparedBy+"&txtFmeqCoreteam="+coreTeam;
		dataStr+="&txtFmeqDoctype="+docType+"&txtFmeqDocmstid="+docmstid+"&txtFmeqDocdtlsid="+docdtlsid;
	}
	else if(fmeaType=='process'){
		dataStr+="&txtFmpmKeyid="+keyid+"&txtFmpmFlid="+flid+"&dteFmpmDate="+preparedDate+"&txtFmpmNo="+fmeaNo+"&cmbFmpmPreparedby="+preparedBy+"&txtFmpmCoreteam="+coreTeam;
		dataStr+="&txtFmpmDoctype="+docType+"&txtFmpmDocmstid="+docmstid+"&txtFmpmDocdtlsid="+docdtlsid;
	}
	var gridData=dataStr;
	//alert(gridData);	
/* 	gridData+='&fmeadtls='+gridval;
	gridData+='&fmeadtlsreview='+gridvalReview; */
	
	 
    gridData+='&fmeadtls='+encodeURIComponent(gridval);
    gridData+='&fmeadtlsreview='+encodeURIComponent(gridvalReview);
    
	//gridData  +=dataStr; 
  	 //alert("gridData"+gridData);
  	
  	console.log("Details:", gridval);
    console.log("Review:", gridvalReview);

    if(gridval.trim().length>0 || gridvalReview.trim().length>0){       
		return gridData;
    }
    else{
    	showCommonErrorMsg("Enter Data to save");
        return false;
    }
		
}

function frmFmeaEntry_deleteSuccessCallback(result)
{
	alert(result.successData.msg);	
	jQuery("#fmeadetailsgrid").trigger("reloadGrid");
	jQuery("#fmeareviewgrid").trigger("reloadGrid");	
}

function frmFmeaEntry_FuntLocHierarchy_SuccessCallBack(result){
	var flid = jQuery("#frmFmeaEntry input[id='flid']").val(); 
	
	//jQuery("#cmbFmeaFlid").val(flid);
	var mchId = jQuery("#frmFmeaEntry input[id='machine']").val();
	//setFieldValue("cmbFmeqEquipid",mchId);
	
	 //var equipId = jQuery("#cmbFmeqEquipid").val();
	    if(mchId != undefined && mchId.trim() != ""){
	        checkExistingFMEA(mchId);
	    }
	
	//fillComboBox('frmFmeaEntry','cmbFmeqSupequipid', 'subEqpmnCombo.fmeaf');
	
	var cellId = jQuery("#frmFmeaEntry input[id='cell']").val();
	jQuery("#cell").val(cellId);
    var machId = jQuery("#frmFmeaEntry input[id='machine']").val();
    var section = jQuery("#frmFmeaEntry input[id='section']").val();
    var locnId = jQuery("#frmFmeaEntry input[id='location']").val();
    
    
	if(cellId != undefined && cellId.trim() != "" && cellId.trim() !=null)
	{	
		reloadCombo("frmFmeaEntry","cmbFmeqEquipid","machineCombo.commonFilter?cellId="+cellId);
		reloadCombo("frmFmeaEntry","cmbFmpmProcessid","process.commonFilter?flid="+cellId);
    }
	
	if(section != undefined && section.trim() != "" && section.trim() !=null)
	{
		reloadCombo("frmFmeaEntry","cmbFmpmProcessid","process.commonFilter?originalid="+section);
	}
	
	if(flid != undefined && flid.trim() != "" && flid.trim() !=null)
	{   // alert('flid' + flid);
		reloadCombo("frmFmeaEntry","cmbFmeaPreparedby","employee.commonFilter?flid="+flid); 
	}
	else
	{   // alert('locnId' + locnId);
		reloadCombo("frmFmeaEntry","cmbFmeaPreparedby","employee.commonFilter?locnId="+locnId); 
	}
		
	
	reloadCombo("frmFmeaEntry","cmbFmeqSupequipid","subEqpmnCombo.fmeaf?flid="+flid);
	
}

/* function  frmFmeaEntrycmbFmeqEquipid_onSelect(record){
	loadFunctionalLocation("designfunlocation","functionalLocequp.fmeaf","fmeafunLocationValues","frmFmeaEntry","&machId="+record.id);
} */

//*************************************************************Gopi Fmea changes*******************************//
function frmFmeaEntrycmbFmeqEquipid_onSelect(record){
	    loadFunctionalLocation(
	        "designfunlocation",
	        "functionalLocequp.fmeaf",
	        "fmeafunLocationValues",
	        "frmFmeaEntry",
	        "&machId="+record.id
	    );
	    
	}

function btnfilemgr_click()
{
	//alert("11");
    var documentNo =jQuery("#txtFmeaKeyid").val();
    if(documentNo.trim().length>0){
    	var fmeaType=jQuery("#txtFmeaType").val();
    	if(fmeaType=='design'){
    		fileManagerPopUp(documentNo,"DFM","","","");
    	}
    	else if(fmeaType=='equipment'){
    		fileManagerPopUp(documentNo,"EFM","","","");
    	}
    	else if(fmeaType=='process'){
    		fileManagerPopUp(documentNo,"PFM","","","");
    	}
    }
    else
    {
    	alert('File can be added after data saved');
    }
}

function checkExistingFMEA(equipId) {
    var flid = jQuery("#frmFmeaEntry input[id='flid']").val();
    processAjaxCalls(
        "FMEAEntry_getEquipFmea.fmeaf",
        "equipId=" + equipId + "&flid=" + flid,
        "existingFMEAFound",
        "existingFMEANotFound"
    );
}

function existingFMEAFound(result) {
    var keyid      = result.successData.keyid;
    var fmeaNo     = result.successData.fmeaNo;
    var coreTeam   = result.successData.coreTeam;
    var preparedBy = result.successData.preparedBy;
    var fmeaDate   = result.successData.fmeaDate;
    var supEquipid = result.successData.supEquipid;

    if (keyid != null && keyid.trim() != "") {
        setFieldValue("txtFmeaKeyid",  keyid);
        setFieldValue("txtFmeaNo",     fmeaNo);

        if (coreTeam   && coreTeam.trim()   != "") setFieldValue("txtFmeaCoreteam",   coreTeam);
        if (fmeaDate   && fmeaDate.trim()   != "") setFieldValue("dteFmeaDate",        fmeaDate);
        if (preparedBy && preparedBy.trim() != "") setFieldValue("cmbFmeaPreparedby",  preparedBy);
        if (supEquipid && supEquipid.trim() != "") setFieldValue("cmbFmeqSupequipid",  supEquipid);

        loadDtlsGrid();
        loadReviewGrid();
    } else {
        existingFMEANotFound(result);
    }
}
function existingFMEANotFound(result) {
    setFieldValue("txtFmeaKeyid",    "");
    setFieldValue("txtFmeaNo",       "");
    setFieldValue("txtFmeaCoreteam", "");
    setFieldValue("dteFmeaDate",     "");
    setFieldValue("cmbFmeaPreparedby", "");
    setFieldValue("cmbFmeqSupequipid", "");
    jQuery("#fmeadetailsgrid").clearGridData();
    jQuery("#fmeareviewgrid").clearGridData();
}


</script>

<form name="frmFmeaEntry" id="frmFmeaEntry">
	<div id="wrapper" style="width:auto;">
		<div  style=" margin-left:20px; margin-top:10px;margin-top:20px\9;">
			<table>
				<tr>
					<td colspan="3">
						<div id="frmFmeaEntryFuntKeyIds"  >							
							<input type="hidden" id="factory" name="factory"  value="" ></input>
							<input type="hidden" id="section" name="section"  value=""></input>
							<input type="hidden" id="cell"    name="cell"     value=""></input>
							<input type="hidden" id="machine" name="machine"  value=""></input>
							<input type="hidden" id="flid" name="cmbFmeaFlid" value="${requestScope.fmeaFlid}"></input>							
						</div>						
						<div id="designfunlocation" style="width:103%;"></div>	
					</td>
				</tr>
				<tr>
					<td style=" padding-top:10px;">
						<div class="easyui-paddingbfpx"><label>FMEA No</label></div>
						<div class="easyui-paddingbfpx">
							<input type="text" id="txtFmeaNo" name="txtFmeaNo"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value="${requestScope.fmeaNo}"/>
						</div>
					</td>
					<td style=" padding-top:10px; padding-left:10px;">
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Date</label><label class="mandatory-lbl" style="padding-left:107px;" >Prepared By</label></div>
						<div class="easyui-paddingbfpx" style="">							
							<input type="text" id="dteFmeaDate" name="dteFmeaDate"  maxlength="25" class="easyui-text" style="width:120px;width:115px\9; height: 21px;"value="${requestScope.fmeaDate}"/>
							<span style="padding-left:10px;">
								<input id="cmbFmeaPreparedby" name="cmbFmeaPreparedby" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.fmeaPreparedby}"/>
							</span>
						</div>
					</td>
					<td style="padding-top:0px;">
						 <div style="position:relative;">
						 	<span style="position:relative;right:83%;padding-left:3px;top:12px;">
								<input type="button" class="easyui-button" value ="Excel View" id="btnXlsView" style="height:23px;"/>	
							</span>
							 <span  id="fmeaFilemgr" style="position:absolute;right:5%;right:5%\9;top:12px;top:12px\9;" >	 </span> 
				         </div>
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;">
						<div class="easyui-paddingbfpx"><label>Core Team</label></div>
						<div class="easyui-paddingbfpx">
							<input type="text" id="txtFmeaCoreteam" name="txtFmeaCoreteam"  maxlength="250" class="easyui-text" style="width:255px; height: 21px;"value="${requestScope.fmeaCoreteam}"/>
							
						</div>
					</td>
					<td style=" padding-top:10px; padding-left:10px;">
						<div id="divprocess" style="display:none">
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Process</label><label class="mandatory-lbl" style="padding-left:224px;">Sub Process</label></div>
							<div class="easyui-paddingbfpx" style="">							
								<input id="cmbFmpmProcessid" name="cmbFmpmProcessid" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.fmpmProcessid}"/>
								<span style="padding-left:10px;">
									<input id="cmbFmpmSupprocessid" name="cmbFmpmSupprocessid" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.fmpmSupprocessid}"/>
								</span>
							</div>
						</div>
						<div id="divdesign" style="display:none">
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">System</label><label class="mandatory-lbl" style="padding-left:224px;">Sub System</label></div>
							<div class="easyui-paddingbfpx" style="">							
								<input id="cmbFmdmSystemid" name="cmbFmdmSystemid" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.fmdmSystemid}"/>								
								<span style="padding-left:10px;">
									<input id="cmbFmdmSupsystemid" name="cmbFmdmSupsystemid" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.fmdmSupsystemid}"/>
								</span>
							</div>
						</div>
						<div id="divequipment" style="display:none">
							<div class="easyui-paddingbfpx"><label  style="padding-left:0px;" >Equipment Area</label><label style="padding-left:200px;">Equipment</label></div>
							<div class="easyui-paddingbfpx" style="">							
								<input id="cmbFmeqSupequipid" name="cmbFmeqSupequipid" class="easyui-combobox"  style="width: 265px;" />
								<span style="padding-left:10px;">
								<input id="cmbFmeqEquipid" name="cmbFmeqEquipid" class="easyui-combobox"  style="width: 265px;" value="${requestScope.fmeqEquipid}"/>
								</span>
							</div>
						</div>
						
					</td>
					<td>
						<div style="margin-top:24px;">
							<span style="">
								<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:23px;"/>	
							</span>
							<span style="">
								<input type="button" class="easyui-button" value ="Delete" id="btnDelete" style="height:23px;"/>	
							</span>
						</div>
					</td>
				</tr>
			</table>
			<div class="sub-header" style="">
				<span style="position:absolute;">FMEA Details</span>
		    </div>
		    <div style="position:absoulte;margin-top:0px;">
				<table id="fmeadetailsgrid" ></table> 
				<div id="fmeadetailspager"></div>
			</div>
			<div class="sub-header" style="">
				<span style="position:absolute;">FMEA Review</span>
		    </div>
		    <div>
				<table id="fmeareviewgrid" ></table> 
				<div id="fmeareviewpager"></div>
			</div>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" /> 
	<input type="hidden" id="txtFmeaType" name="txtFmeaType" value="${requestScope.fmeaType}" />
	<input type="hidden" id="txtFmeaKeyid" name="txtFmeaKeyid" value="${requestScope.fmeaKeyid}" />
	<input type="hidden" id="txtFmeaDoctype" name="txtFmeaDoctype" value="${requestScope.fmeaDocType}" />
	<input type="hidden" id="txtFmeaDocmstid" name="txtFmeaDocmstid" value="${requestScope.fmeaDocmstid}" />
	<input type="hidden" id="txtFmeaDocdtlsid" name="txtFmeaDocdtlsid" value="${requestScope.fmeaDocdtlsid}" />
	<input type="hidden" id="hdnflid" name="hdnflid" value="" />
		<input type="hidden" id="hdnSelect" name="hdnSelect" value="" />
		
		
		<input type="hidden" id="fmeadtls" name="fmeadtls" />
		<input type="hidden" id="fmeadtlsreview" name="fmeadtlsreview" />
		
</form>
