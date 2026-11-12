<script>
jQuery(document).ready(function(){
	 initialiseForm('frmCAPA');
	 var flag="false";
	 jQuery('#submitForm').val('frmCAPA');
	 formatDateBox('dteQcapDate','DD-MMM-YYYY');
     var flid = jQuery("#frmCAPA input[id='flid']").val();
	 fillComboBox("frmCAPA","cmbQcapTodmtleader","employee.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapComplaintraisedby","employee.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapAreaincharge","employee.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapTodmt","sectionCombo.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapTojh","cellCombo.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapUom","uomCombo.commonFilter");
	 fillComboBox("frmCAPA","cmbQcapDefect","DefectCombo.ncapa");
	 fillComboBox("frmCAPA","cmbQcapType","ComplaintType.ncapa");
	 var mode=jQuery("#mode").val();
	var TargetFlid=jQuery("#hdnTargetFlid").val();
	//alert("TargetFlid"+TargetFlid)
	 if(mode=="Create"){
	 fillWithCurrentDate("dteQcapDate");
	 }
	 //alert("MODE"+mode)
	 if(mode=="Approve" || mode=="Capa"){
		 setTimeout(function() {readOnlyFields('cmbQcapTodmtleader');},1250);
		 setTimeout(function() {readOnlyFields('cmbQcapComplaintraisedby');},1250);
		 setTimeout(function() {readOnlyFields('cmbQcapAreaincharge');},1250); 
		 setTimeout(function() {readOnlyFields('cmbQcapTodmt');},1250);
		 setTimeout(function() {readOnlyFields('cmbQcapTojh');},1250);
		 setTimeout(function() {readOnlyFields('cmbQcapDefect');},1250);
		 setTimeout(function() {readOnlyFields('cmbQcapUom');},1250); 
		 setTimeout(function() {readOnlyFields('txtQcapQuantityeffect');},1250);
		 setTimeout(function() {readOnlyFields('txtQcapQualityIssue');},1250);
		 setTimeout(function() {readOnlyFields('dteQcapDate');},1250);
	 }
	 if(mode=="Approve" || mode=="Create"){
		 disableUIButton("btnAddRow");
		 disableUIButton("btnRemove");
		 
	 }
	 
	 var Status=jQuery("#hdnStatus").val();
	 alert("Status" + Status);
	 alert("MODE " + mode);
		if(Status=="Approved"){
			 var LoginUseid=jQuery("#hdnLoginUser").val();
			 var AreaIncharge=jQuery("#cmbQcapAreaincharge").combobox("getValue");
			if(LoginUseid==AreaIncharge){
			
			enableUIButton("btnAddRow");
			enableUIButton("btnRemove");
			}
			else{
				 disableUIButton("btnAddRow");
				 disableUIButton("btnRemove");
				 
			}
			
		}
		else{
			 disableUIButton("btnAddRow");
			 disableUIButton("btnRemove");
		}
		
	
	
	

	 numericTextBox("txtQcapQuantityeffect");
	 fileManagerPopUp("","CAPA","frmCAPA","btnFilManage","newCapaFilemgr")
	 var url = jQuery('#hiddenUrl').val();
	 var locnId = jQuery("#frmCAPA input[id='location']").val();
	 var factId = jQuery("#frmCAPA input[id='factory']").val();
	 var sectionId = jQuery("#frmCAPA input[id='section']").val();
	 var cellId = jQuery("#frmCAPA input[id='cell']").val();
	 var machId = jQuery("#frmCAPA input[id='machine']").val();
	 var dataStr = "&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;	  

	 var keyid=jQuery("#txtQcapKeyid").val();
	 if(keyid!=null&&keyid.length!=0)
		 {
		
		   disableField("frmCAPA","dteQcapDate");
		   var calendardate=jQuery("#dteQcapDate").datebox('getValue');
		   jQuery("#dteQcapDate").datebox('setValue',calendardate.substring(0,11));
	     
		 }
	var funclocn="";
	 funclocn= "functionalLoc.ncapa";
	 if(flid.length<=0)
		 {
		 loadFunctionalLocation("CAPAFunctionalLoc",funclocn,"CAPAFunctionalLoc","frmCAPA","&flid=");
		 }
	 else{
		 loadFunctionalLocation("CAPAFunctionalLoc",funclocn,"CAPAFunctionalLoc","frmCAPA","&flid="+flid);
	 }	  
	 fileManagerPopUp("","CAPA","frmCAPA","btnFilManage","newCapaFilemgr")
	 // --vignesh 
	 function getUrlParam(name){
		    name = name.replace(/[\[\]]/g, "\\$&");
		    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)");
		    var results = regex.exec(window.location.href);
		    if(!results) return null;
		    if(!results[2]) return '';
		    return decodeURIComponent(results[2].replace(/\+/g, " "));
		}

		function applyCapaPopupMode(){
		    if(getUrlParam("popup") !== "Y") return;

		    // ✅ no add/remove in popup
		    jQuery("#btnAddRow").hide();
		    jQuery("#btnRemove").hide();

		    // ✅ open the right tab automatically
		    try{ jQuery('#tabCAPA').tabs('select', 'Corrective and Preventive Actions'); }catch(e){}

		    // wait until grid is initialized, then lock it down
		    var g = jQuery("#CAPAGrid");
		    if(g.length && g[0].grid){

		        // ✅ block editing
		        try{
		            g.jqGrid('setGridParam', { ondblClickRow: null, cellEdit: false, editurl: 'clientArray' });
		        }catch(e){}

		        // ✅ hide any “edit” column (name/label contains edit)
		        try{
		            var cm = g.jqGrid('getGridParam','colModel') || [];
		            for(var i=0;i<cm.length;i++){
		                var n = (cm[i].name || '').toLowerCase();
		                var l = (cm[i].label || cm[i].name || '').toLowerCase();
		                if(n.indexOf('edit') >= 0 || l.indexOf('edit') >= 0){
		                    g.jqGrid('hideCol', cm[i].name);
		                }
		            }
		        }catch(e){}
		    }else{
		        setTimeout(applyCapaPopupMode, 250);
		    }
		}

		applyCapaPopupMode();

	 
});
 // -- ADDED BY vIGNESH
(function () {
    jQuery(document).ajaxSend(function (e, xhr, opt) {
        console.log("[AJAX SEND]", opt.type || "GET", opt.url);
    });

    jQuery(document).ajaxComplete(function (e, xhr, opt) {
        console.log("[AJAX DONE]", opt.type || "GET", opt.url, "status=", xhr.status);
    });

    jQuery(document).ajaxError(function (e, xhr, opt, err) {
        console.error("[AJAX ERROR]", opt.type || "GET", opt.url,
            "status=", xhr.status, "err=", err, "response=", xhr.responseText);
    });
})();

function btnFilManage_click(){
    var documentNo =jQuery("#txtQcapKeyid").val();
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#frmMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"CAP","","","","create");		
	} 
	else  
    {  
		 return false;
     }	
}

function CAPAGridbtnFileManager_onClick(result){
	var documentNo =jQuery("#txtQcapKeyid").val();
	//alert("Document No"+documentNo)
	if(documentNo.length!=0)
		{
	//var documentNo =jQuery("#hdnSpsdKeyid").val();
	//alert("The documentNo :::"+documentNo);
	if(documentNo != null && documentNo != ''){
	    var frmMode=jQuery('#frmMode').val();
		apMode = "CAPA";
		if(frmMode=="CAPA")
		   apMode = "CAPA";

		fileManagerPopUp(documentNo,"CAPA","","","",apMode);	
	}
		}else{
			alert("Pls Save COMPLAINT");
		}

}
/* function CAPAGrid_selectRow(rowId)
{ 
alert("inside");
	var filemanagerbtn="btnFilManage_CAPAGrid_"+rowId;
alert(filemanagerbtn);
var jqGridId="safetyauditgrid";
			jQuery("#"+filemanagerbtn).click(function()
	            {
	            	alert("Clicked button");
	            	var documentNo =jQuery("#txtQcapKeyid").val();
	            	alert("Document No"+documentNo)
	            	if(documentNo.length!=0)
	            		{
	            	//var documentNo =jQuery("#hdnSpsdKeyid").val();
	            	//alert("The documentNo :::"+documentNo);
	            	if(documentNo != null && documentNo != ''){
	            	    var frmMode=jQuery('#frmMode').val();
	            		apMode = "CAPA";
	            		if(frmMode=="documentNo")
	            		   apMode = "documentNo";

	            		fileManagerPopUp(documentNo,"CAPA","","","",apMode);	
	            	}
	            		}else{
	            			alert("Pls Save COMPLAINT");
	            		}
	            }
			);	
} */
function frmCAPAcmbQcapTodmt_onSelect(record) 	{
	console.log("Target DMT selected", record);
	var sectId=record.id;
	//////alert("dataStr"+sectId);
	var DMTID=record.id;
	
	
	reloadCombo("frmCAPA","cmbQcapTojh","cellCombo.commonFilter?sectId="+sectId);
	reloadCombo("frmCAPA","cmbQcapDefect","DefectCombo.ncapa?sectId="+sectId);
    reloadCombo("frmCAPA","cmbQcapTodmtleader","employee.commonFilter?isDmtLeader=Y&DMTID="+DMTID+"&loginEmpshow=false");  
    
	
    
} 

   function frmCAPAcmbQcapTodmtleader_onLoadSuccess() {
  //alert("load success");
	setComboDefaultValue("frmCAPA", "cmbQcapTodmtleader");
}  
 






function frmCAPAcmbQcapType_onLoadSuccess() {
	  setComboDefaultValue("frmCAPA", "cmbQcapType");
	}
jQuery("#tabCAPA").tabs(
 	    {
 		onSelect : function(title)
 		 {
 			if(title=="WorkFlow"){
 				 var CapaId = jQuery("#txtQcapKeyid").val();
 				 //////alert("CapaId"+CapaId);
 				 
 				 jQuery("#hdntitle").val("WorkFlow");
 				 
 			     processGridnew("CAPAWorkFlow_input.ncapa","?q=2&CapaId="+CapaId,"WorkFLowGrid","Workflowpager","","","","WorkFlowloadComFunction");	  		
 			
 			}
 			else if(title =="Corrective and Preventive Actions"){
 				 jQuery("#hdntitle").val("CAPA");
 				
 				//jQuery("#hdntitle").val("Corrective and Preventive Actions");
 				 var CapaId = jQuery("#txtQcapKeyid").val();
 				processGridnew("CustomerComplaint_input.ncapa","?q=2&CapaId="+CapaId,"CAPAGrid", "CAPApager","","", "", "CAPAloadComFunction");
 			
 			}	
 			else{
 				
 				jQuery("#hdntitle").val("WorkFlow");
 			
 			}
 		 }
 	    
 		 });

//////////////////////////////////////////////////////alert("Load Complete") 
function WorkFlowloadComFunction(){
var row = jQuery("#WorkFLowGrid").jqGrid('getDataIDs');

var cm = jQuery("#WorkFLowGrid").jqGrid("getGridParam", "colModel");
var LoginUser=jQuery("#hdnLoginUser").val();
//////alert("LoginUser"+LoginUser);
var LoginDMTUser=jQuery("#hdnLoginDMTUser").val();
//////alert("LoginDMTUser"+LoginDMTUser)
 var mode=jQuery("#mode").val();
 //alert("mode"+mode);
 
for(var i=0;i<row.length;i++)
{
	 var status = jQuery("#WorkFLowGrid").jqGrid('getCell',row[i],"selQcwfStatus");	
	 //////alert("status"+status);
	if(LoginUser==LoginDMTUser && status!="A"){
		  
		jQuery('input:checkbox[id=jqg_WorkFLowGrid_'+row[i]+']').attr('disabled',false);
	}
	else if(status=="A"){
		jQuery('input:checkbox[id=jqg_WorkFLowGrid_'+row[i]+']').attr('disabled',true);
	}
	else if(mode=="Create"){
		jQuery('input:checkbox[id=jqg_WorkFLowGrid_'+row[i]+']').attr('disabled',true);
	}
	
	else{
		jQuery('input:checkbox[id=jqg_WorkFLowGrid_'+row[i]+']').attr('disabled',true);
	}
	
}
}

function CAPAloadComFunction(){
	var row = jQuery("#CAPAGrid").jqGrid('getDataIDs');

	var cm = jQuery("#CAPAGrid").jqGrid("getGridParam", "colModel");
	 var LoginUseid=jQuery("#hdnLoginUser").val();
		
	 var AreaIncharge=jQuery("#cmbQcapAreaincharge").combobox("getValue");
	 for(var i=0;i<row.length;i++)
	 {
		 if(LoginUseid==AreaIncharge){
			// alert("IF");
				jQuery('input:checkbox[id=jqg_CAPAGrid_'+row[i]+']').attr('disabled',false);	 
		 }
		 else{
			// alert("else");
			 jQuery('input:checkbox[id=jqg_CAPAGrid_'+row[i]+']').attr('disabled',true);	  
		 }
	 }
}
function WorkFLowGridbtnComPSave_onClick(result){	
	
	var rowid=result.rowId;
	var btnid=result.btnId;
	
	var keyid = jQuery("#WorkFLowGrid").jqGrid('getCell',rowid,"hdnQcwfKeyid");
	
	var EmpId = jQuery("#WorkFLowGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////alert("EMPId"+EmpId)
     var CapaId = jQuery("#WorkFLowGrid").jqGrid('getCell',rowid,"hdnQcwfCaparefid");
	//////alert("CapaId=="+CapaId)
	var status = getFieldValue("selQcwfStatus_WorkFLowGrid_"+rowid);
	//////alert("Status=="+status)
	var date = getFieldValue("dteQcwfDate_WorkFLowGrid_"+rowid);
	//////alert("date"+date)
	var remarks = getFieldValue("txtQcwfRemarks_WorkFLowGrid_"+rowid);
	 var TargetDmtLeader=jQuery("#cmbQcapTodmtleader").combobox("getValue");
	  var TargetAreaManager=jQuery("#cmbQcapAreaincharge").combobox("getValue");
	  var Description=jQuery("txtQcapQualityIssue").val();
	//////alert("remarks"+remarks);
	saveForm("frmCAPA","ComplainWorkFlow_save.ncapa?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&CapaId="+CapaId+"&TargetDmtLeader="+TargetDmtLeader+"&TargetAreaManager="+TargetAreaManager+"&Description="+Description);	
	
	
}


jQuery("#btnAddRow").click(function(){
	var row = jQuery("#CAPAGrid").jqGrid("getDataIDs");
	addRow(row);
 });
 
 //--added--------------------mar05-------------------------------------------
jQuery("#btnRemove").click(function(){
	//var row = jQuery("#CAPAGrid").jqGrid("getDataIDs");
	
	var row = jQuery("#CAPAGrid").jqGrid('getGridParam','selrow');
	alert("row " + row);
	//CAPAGrid_hdnQcpaQcapkeyid
	//CAPAGrid_hdnQcpaKeyid
	alert("row "+row);
	var dtlKeyid=jQuery("#CAPAGrid").jqGrid('getCell',row,'hdnQcpaKeyid');
	alert("id"+dtlKeyid);
	processAjaxCalls("CapaGridActionPlan_remove.ncapa","&dtlKeyid="+dtlKeyid,"","");
	//addRow(row);
	alert("Delete");
	jQuery("#CAPAGrid").trigger("reloadGrid");
 });
 

function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnFasdKeyid:" ",txtFasdEspid:" ",cmbFasdEspid:" ",txtFasdppeid:" ",cmbFasdppeid:" ",
			 txtFasdTools:" ",txtFasdWorkpermitsafety:" ",txtFasdKnowledge:" ",txtFasdRemarks:" "}];
		jQuery("#CAPAGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnFasdKeyid:" ",txtFasdEspid:" ",cmbFasdEspid:" ",txtFasdppeid:" ",cmbFasdppeid:" ",
			txtFasdTools:" ",txtFasdWorkpermitsafety:" ",txtFasdKnowledge:" ",txtFasdRemarks:" "}];
		jQuery("#CAPAGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

function frmCAPA_FuntLocHierarchy_SuccessCallBack(keyIds)
{
  
	    setFunctionalLocWidth('frmCAPA','650px');
}
var LoginId=jQuery("#hdnLoginUser").val();
////alert("LoginId"+LoginId);
if(LoginId!=null){
//////alert("inside");

//setFieldValue('cmbQcapComplaintraisedby',LoginId);
setFieldValue("cmbQcapComplaintraisedby",LoginId);
}

//Actionplanbtn_CAPAGrid_1

function CAPAGridbtnActionplan_onClick(result){	

var rowid=result.rowId;
var btnid=result.btnId;
var CorrectiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
var PreventiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
var RootCause=getFieldValue("txtQcpaRootcause_CAPAGrid_"+rowid);
var TargetDate=getFieldValue("dteQcpaTargetdate_CAPAGrid_"+rowid);
//alert("TargetDate"+TargetDate)
var Status=getFieldValue("cmbQcpaStatus_CAPAGrid_"+rowid);
//alert("Status"+Status);
var MType=getFieldValue("selQcpa4mcondition_CAPAGrid_"+rowid);

var Defect=getFieldValue("selQcpaDefecttype_CAPAGrid_"+rowid);
var Keyid=jQuery("#txtQcapKeyid").val();
var CAPA=jQuery("#CAPAGrid").jqGrid('getCell',rowid,"hdnQcpaKeyid");
alert("CAPA"+CAPA);
var Responsibility=getFieldValue("cmbQcpaResponsibleid_CAPAGrid_"+rowid);
var ButtonType="ActionPlan"
saveForm("frmCAPA","CapaGridActionPlan_save.ncapa?&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility+"&ButtonType="+ButtonType+"&CAPA="+CAPA);	
//processAjaxCalls("CapaGridActionPlan_save.ncapa","&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility,"ActionPlanCapa_successCallBack","");	

}

function CAPAGridbtnwhywhwy_onClick(result){	

	var rowid=result.rowId;
	var btnid=result.btnId;
	var CorrectiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
	var PreventiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
	var RootCause=getFieldValue("txtQcpaRootcause_CAPAGrid_"+rowid);
	var TargetDate=getFieldValue("dteQcpaTargetdate_CAPAGrid_"+rowid);
	var Status=getFieldValue("cmbQcpaStatus_CAPAGrid_"+rowid);
	var MType=getFieldValue("selQcpa4mcondition_CAPAGrid_"+rowid);
	var Defect=getFieldValue("cmbQcpaStatus_CAPAGrid_"+rowid);
	var Keyid=jQuery("#txtQcapKeyid").val();
	var Responsibility=getFieldValue("cmbQcpaResponsibleid_CAPAGrid_"+rowid);
	var CAPA=jQuery("#CAPAGrid").jqGrid('getCell',rowid,"hdnQcpaKeyid");
	var ButtonType="WHYWHY"
	saveForm("frmCAPA","CapaGridActionPlan_save.ncapa?&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility+"&ButtonType="+ButtonType+"&CAPA="+CAPA,Responsibility,"ActionPlanCapa_successCallBack","");	
	//processAjaxCalls("CapaGridActionPlan_save.ncapa","&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility,"ActionPlanCapa_successCallBack","");	

	}
	
	
function CAPAGridbtnsuggestion_onClick(result){	

	var rowid=result.rowId;
	var btnid=result.btnId;
	var CorrectiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
	var PreventiveAction=getFieldValue("txtQcpaCorrectiveaction_CAPAGrid_"+rowid);
	var RootCause=getFieldValue("txtQcpaRootcause_CAPAGrid_"+rowid);
	var TargetDate=getFieldValue("dteQcpaTargetdate_CAPAGrid_"+rowid);
	var Status=getFieldValue("cmbQcpaStatus_CAPAGrid_"+rowid);
	var MType=getFieldValue("selQcpa4mcondition_CAPAGrid_"+rowid);
	var Defect=getFieldValue("cmbQcpaStatus_CAPAGrid_"+rowid);
	var Keyid=jQuery("#txtQcapKeyid").val();
	var Responsibility=getFieldValue("cmbQcpaResponsibleid_CAPAGrid_"+rowid);
	var CAPA=jQuery("#CAPAGrid").jqGrid('getCell',rowid,"hdnQcpaKeyid");
	var ButtonType="SUGGESTION"
	
	saveForm("frmCAPA","CapaGridActionPlan_save.ncapa?&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility+"&ButtonType="+ButtonType+"&CAPA="+CAPA);	
	//processAjaxCalls("CapaGridActionPlan_save.ncapa","&CorrectiveAction="+CorrectiveAction+"&Keyid="+Keyid+"&PreventiveAction="+PreventiveAction+"&RootCause="+RootCause+"&TargetDate="+TargetDate+"&Status="+Status+"&MType="+MType+"&Defect="+Defect+"&Responsibility="+Responsibility,"ActionPlanCapa_successCallBack","");	

	}	


function frmCAPA_beforeSubmit(){
	 var title=jQuery("#hdntitle").val();
	 var TargetDmtLeader=jQuery("#cmbQcapTodmtleader").combobox("getValue");
	// ////alert("TargetDmtLeader"+TargetDmtLeader);
     var TargetDMT=jQuery("#cmbQcapTodmt").combobox("getValue");
    // ////alert("TargetDMT"+TargetDMT);
     var TargetJH=jQuery("#cmbQcapTojh").combobox("getValue");
     
     jQuery('#hdnTargetJh').val(TargetJH);
     
	 var TargetAreaManager=jQuery("#cmbQcapAreaincharge").combobox("getValue");
	 var ComplaintRaisedBy=jQuery("#cmbQcapComplaintraisedby").combobox("getValue");
	 ////alert("ComplaintRaisedBy"+TargetDMT);
	var Description=jQuery("txtQcapQualityIssue").val();
	 if(title=="WorkFlow"){
 return "TargetDmtLeader="+TargetDmtLeader+"&TargetDMT="+TargetDMT+"&TargetJH="+TargetJH+"&TargetAreaManager="+TargetAreaManager+"&ComplaintRaisedBy="+ComplaintRaisedBy+"&title="+title+"&Description="+Description;
	 }
	 else if(title=="CAPA"){
		// alert("inside")
			var CapaKeyid=jQuery("#hdnWhatifKey").val();
		    var CapaMasterkeyid=jQuery("#txtQcapKeyid").val();
	        var gridCapa=getGridSelectArray('CAPAGrid');
	        var Title=jQuery("#hdntitle").val();
	    	 if(gridCapa !=""){
	         return '&gridCapa='+gridCapa+"&CapaMasterkeyid="+CapaMasterkeyid+"&title="+title+"&CapaKeyid="+CapaKeyid;
	 }
	 }
	/*   else if(title=="CAPA"){
			var CapaKeyid=jQuery("#hdnWhatifKey").val();
			////////////////////////alert(WhatifKeyId);
			 var CapaMasterkeyid=jQuery("#txtQcapKeyid").val();
	       
	         var gridCapa=getGridSelectArray('CAPAGrid');
	         ////alert("Grid CAPA"+gridCapa);
	         var Title=jQuery("#hdntitle").val();
	    	 ////alert("title"+title);
	         if(gridCapa !=""){
	        	// if(validateMandtoryforWahtif(gridCapa))
	        		 ////alert("if con")
	       		  return '&gridCapa='+gridCapa+"&CapaMasterkeyid="+CapaMasterkeyid+"&title="+title+"&CapaKeyid="+CapaKeyid;

	         }
	         return false;
			  ////////////////////////////////////////////////////alert(gridData);
		 	 //  if(gridWhatifval.trim().length>0)	
					//return gridData;
		} */

}

function frmCAPA_successsCallback(result){ 	 
    var CapaId = result.QcapkeyId || "";
    alert("CapaId Id"+CapaId);
    setFieldValue('txtQcapKeyid', CapaId);

    var ButtonType = result.ButtonType || "";
    alert("call back from why")

    // ✅ SUGGESTION: navigate immediately and STOP further grid reloads
    if (ButtonType === "SUGGESTION") {

        var flid = jQuery("#hdnTargetFlid").val() || "";
        var cell = jQuery("#cmbQcapTojh").combobox("getValue") || jh;
        var date = jQuery('#dteQcapDate').datebox('getValue') || "";
        var proposedID = jQuery("#txtQcapKeyid").val() || CapaId;
        
        var jh = jQuery("#hdnTargetJh").val();

        console.log("[SUGGESTION] flid=", flid, "cell=", cell, "date=", date, "proposedID=", proposedID);

        // ✅ Build encoded querystring (no ?&, no missing &)
        var params = {
            refDocNo: proposedID,
            flid: flid,
            refDocType: "CAPA",
            AccSingle: "N",
            cell: cell,
            date: date,
            mode: "create"
        };

        var finalUrl = "KaizenBankSuggestion_input.kznbnk?" + jQuery.param(params);

        var forwardData = { flid: flid, cell: cell, date: date, mode: "create" };
        var persistentData = { flid: flid, cell: cell, date: date, mode: "create" };

        console.log("[SUGGESTION] finalUrl =", finalUrl);
        console.log("[SUGGESTION] forwardData =", forwardData);
        console.log("[SUGGESTION] persistentData =", persistentData);

        try {
            navigateToNextForm(finalUrl, "Kaizen Suggestion", forwardData, persistentData);
            console.log("[SUGGESTION] navigateToNextForm() triggered");
        } catch (e) {
            console.error("[SUGGESTION] navigateToNextForm failed, redirecting.", e);
            window.location.href = finalUrl; // fallback
        }

        return; // ✅ VERY IMPORTANT: prevent CAPA grid reload AJAX from running
    }

    // ✅ Only refresh grids when you stay on CAPA page
    processGridnew("CAPAWorkFlow_input.ncapa", "?q=2&CapaId=" + CapaId,
        "WorkFLowGrid", "Workflowpager", "", "", "", "WorkFlowloadComFunction");

    processGridnew("CustomerComplaint_input.ncapa", "q=2&CapaId=" + CapaId,
        "CAPAGrid", "CAPApager", "", "", "", "");

    if (ButtonType === "ActionPlan") {
        var flid1 = jQuery("#hdnTargetFlid").val();
        var date1 = jQuery('#dteQcapDate').datebox('getValue');
        var mainTask1 = jQuery("#txtQcapQualityIssue").val();

        openActionPlan("Actionplane", CapaId, "CAPA", flid1, mainTask1, CapaId, date1);
    }
    else if (ButtonType === "WHYWHY") {
        var flid2 = jQuery("#hdnTargetFlid").val();
        
        var jh = jQuery("#hdnTargetJh").val();
        alert("Flid"+flid2);
        alert("jh"+jh);
        console.log(result+"WH WHy");
        console.log(JSON.stringify(result));
        console.log("WH WHy");
        var date2 = jQuery('#dteQcapDate').datebox('getValue');
        var mainTask2 = jQuery("#txtQcapQualityIssue").val();
        alert("call back from why")
        openWhyWhy("divWhyWhy", false, CapaId, "CAPA", flid2, date2, mainTask2, "create");
    }
}


function ActionPlanCapa_successCallBack(result)
{
	alert("call back from why")
	console.log(result);
	}

// function frmCAPA_successsCallback(result){ 	 
// 	var CapaId=result.QcapkeyId;
// //alert("CapaId::::"+CapaId);
// setFieldValue('txtQcapKeyid',CapaId);
// 	var ButtonType=result.ButtonType;
// 	//alert("ButtonType"+ButtonType);
// 	 var mode=jQuery("#mode").val();
// 	 //alert(mode);
	 
// 	 processGridnew("CAPAWorkFlow_input.ncapa","?q=2&CapaId="+CapaId,"WorkFLowGrid","Workflowpager","","","","WorkFlowloadComFunction");	 
// 		processGridnew("CustomerComplaint_input.ncapa","q=2&CapaId="+CapaId,"CAPAGrid", "CAPApager","","", "", "");
			
// 	 if(ButtonType!=null&&ButtonType=="ActionPlan"){
// 			var CapaId = jQuery("#txtQcapKeyid").val();
// 			//alert("CapaId"+CapaId)
// 			var flid =jQuery("#hdnTargetFlid").val();
// 		//	alert("flid"+flid);
// 			var date = jQuery('#dteQcapDate').datebox('getValue');
// 			//alert("date"+date);
// 			var proposedID = result.CapaDetId;
// 			//alert("proposedID"+proposedID);
// 			var mainTask =jQuery("#txtQcapQualityIssue").val();
// 			//alert("mainTask"+mainTask);
// 			//openActionPlan(divId, actPlanRefMasId, actPlanRefDocType, flId, actPlanMainTask, actPlanRefDtlId, actPlanRefDate, apMode)
// 			openActionPlan("Actionplane",CapaId,"CAPA",flid,mainTask,CapaId,date);
		 
// 	 }
// 	 else if(ButtonType!=null&&ButtonType=="WHYWHY") {
		 

// 		 var CapaId = jQuery("#txtQcapKeyid").val();
// 			//alert("CapaId"+CapaId)
// 			var flid =jQuery("#hdnTargetFlid").val();
// 			//alert("flid"+flid);
// 			var date = jQuery('#dteQcapDate').datebox('getValue');
// 			//alert("date"+date);
			
// 			var mainTask =jQuery("#txtQcapQualityIssue").val();
// 			//alert("mainTask"+mainTask);
// 		 var forwardData = "";
// 		 var persistentData ="";
//  openWhyWhy("divWhyWhy",false,CapaId,"CAPA",flid, date, mainTask, "create");
		 
// 	 }
	  
// 	 // ---Vignesh 22Dec2025  ----------------//
	 
// 	 else if(ButtonType!=null&&ButtonType=="SUGGESTION"){
		 
// 			var flid = jQuery("#hdnTargetFlid").val();
// 			var cell = jQuery("#cmbQcapTojh").combobox("getValue");
// 			var date = jQuery('#dteQcapDate').datebox('getValue');
// 			var proposedID = jQuery("#txtQcapKeyid").val();
// 			var persistentData={"flid":flid,"flid":flid,"date":date,"mode":"create"};
// 			//alert(persistentData);
// 			var forwardData={"flid":flid,"flid":flid,"date":date,"mode":"create"};;
// 			//alert(forwardData);
// 			////alert(proposedID);
// 		//	var dataStr ="?&refDocNo="+proposedID+"&flid="+flid+"&refDocType=CAPA&AccSingle=N";
// 		var dataStr="?&flid="+flid+"&refDocType=CAPA&refDocNo="+proposedID+"&AccSingle=N";
// 		//alert(dataStr);
// 		navigateToNextForm("KaizenBankSuggestion_input.kznbnk"+dataStr,"Kaizen Suggestion",forwardData,persistentData);	 
// 		}
	 
// // 	 else if (ButtonType != null && ButtonType == "SUGGESTION") {

// // 		    var flid = jQuery("#hdnTargetFlid").val() || "";
// // 		    var cell = jQuery("#cmbQcapTojh").combobox("getValue") || "";
// // 		    var date = jQuery("#dteQcapDate").datebox("getValue") || "";
// // 		    var proposedID = jQuery("#txtQcapKeyid").val() || "";

// // 		    // ✅ Build base params safely (encodes everything)
// // 		    var baseParams = jQuery.param({
// // 		        refDocNo: proposedID,
// // 		        flid: flid,
// // 		        refDocType: "CAPA",
// // 		        AccSingle: "N"
// // 		    });

// // 		    // ✅ IMPORTANT: keep trailing '&' because navigateToNextForm appends more params
// // 		    var finalUrl = "KaizenBankSuggestion_input.kznbnk?" + baseParams + "&";

// // 		    // ✅ FIX: you had {"flid":flid,"flid":flid,...} (duplicate key). Add cell properly.
// // 		    var forwardData = { flid: flid, cell: cell, date: date, mode: "create" };
// // 		    var persistentData = { flid: flid, cell: cell, date: date, mode: "create" };

// // 		    console.log("[SUGGESTION] flid=", flid, "cell=", cell, "date=", date, "proposedID=", proposedID);
// // 		    console.log("[SUGGESTION] finalUrl =", finalUrl);
		    
// // 		    window.open(finalUrl, "_blank");

// // 		    console.log("[SUGGESTION] forwardData =", forwardData);
// // 		    console.log("[SUGGESTION] persistentData =", persistentData);

// // 		    navigateToNextForm(finalUrl, "Kaizen Suggestion", forwardData, persistentData);
// // 		    console.log("[SUGGESTION] navigateToNextForm() triggered");
// // 		}

	 


	 // ---Vignesh 22Dec2025  ----------------//
	 


function Actionplane_onClose(){
	jQuery("#CAPAGrid").trigger("reloadGrid");
    return true;
}
function divWhyWhy_onClose(){
	jQuery("#CAPAGrid").trigger("reloadGrid");
    return true;
}

function viewGrid(filterString){ 
	   processGridnew("CAPAWorkFlow_input.ncapa",filterString,"WorkFLowGrid","pagerid","","","","");
       processGridnew("CustomerComplaint_input.ncapa",filterString,"Gengrid","Workflowpager","","", "", "");
}




//✅ Set when opened from CAPA Report popup
var isCapaReportPopup = ("${param.popup}" === "Y");

function applyCapaReportPopupMode(){
	try{
		if(!isCapaReportPopup) return;

		// Hide Add/Remove row buttons
		jQuery("#btnAddRow").hide();
		jQuery("#btnRemove").hide();

		// Hide "Edit" column if present (name/label contains edit OR name is act/actions)
		var cm = jQuery("#CAPAGrid").jqGrid("getGridParam","colModel") || [];
		for (var i=0;i<cm.length;i++){
			var nm = (cm[i].name || cm[i].index || "");
			var lb = (cm[i].label || "");
			var nml = (nm+"").toLowerCase();
			var lbl = (lb+"").toLowerCase();

			if (nml.indexOf("edit")>=0 || lbl.indexOf("edit")>=0 || nml==="act" || nml==="actions"){
				jQuery("#CAPAGrid").jqGrid("hideCol", nm);
			}
		}
	}catch(e){ /* ignore */ }
}

// call twice (grid is created async)
jQuery(document).ready(function(){
	setTimeout(applyCapaReportPopupMode, 300);
	setTimeout(applyCapaReportPopupMode, 1200);
});

</script>
<form id="frmCAPA">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3">
					<div id="frmCAPAFuntKeyIds">
					<input type="hidden" id="factory"  name="cmbQcapFactoryid" value=""></input>
					<input type="hidden" id="flid"     name="cmbQcapFlid"  value="${requestScope.qtmTlCapa.qcapFlid}"></input>
					<input type="hidden" id="location" name="cmbEtcmLocation" value="}"></input> 
					<input type="hidden" id="sbu"      name="cmbQcapSbu" value=""></input> 
					<input type="hidden" id="pbu"      name="cmbQcapPbu" value=""></input>
					<input type="hidden" id="section"  name="cmbQcapSect" value=""></input>
					<input type="hidden" id="cell"     name="cmbQcapJh" value=""></input>
					 
					</div>
			 		<div id="CAPAFunctionalLoc"   style="width:100%; margin-left:-10px;"></div>	
			      </tr>
			      <tr>
			      <td>
				  <div style="margin-left:680px; margin-top:-45px;"><label>CAPA No.</label></div>
				  <div style="margin-left:680px; margin-top:0%;">
				  <input id="txtQcapKeyid" name="txtQcapKeyid" type="text" class="easyui-text" disabled="disabled" style="width:160px; height:30px;text-align:center; font-weight:bold" value="${requestScope.qtmTlCapa.qcapKeyid}"/> 
				  </div>
				  </td>
				  </tr>
				 
				 <tr>
				 <td> 
				<div style="margin-left:850px; margin-top:-48px;">
				<label class="mandatory-lbl">CAPA Date</label></div> 
				<div style="margin-left:850px; margin-top:-1px;">
				<input class="easyui-text" id="dteQcapDate" name="dteQcapDate" style="width:100px;/* height:165px; */" value="${requestScope.qtmTlCapa.qcapDate}"/>  
	            </div>
	            </td>
	            </tr> 
	            
	           <tr>
				 <td>
			<div style="position: relative;margin-left:970px;margin-top:-36px;"><span id="newCapaFilemgr" style="position: absolute;padding-right:30px;margin-top:0px;">
		</span></div>  
	    </td>
				</tr>
	        
	        
	        	<tr>
			<td>
			<div style="margin-left:-10px;margin-top:9px;"><label id="" class="mandatory-lbl" >Target DMT</label></div>
			<div style="margin-left:-10px;">
            <input id="cmbQcapTodmt" name="cmbQcapTodmt" type="text" class="easyui-text"  style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapTodmt}"/>
			</div>
			</td>
			</tr>
			
		    <tr>
			<td>
			<div style="margin-left:170px;margin-top:-40px;"><label id="" class="mandatory-lbl">Target JH</label></div>
			<div style="margin-left:170px;">
            <input id="cmbQcapTojh" name="cmbQcapTojh" type="text" class="easyui-text"  style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapTojh}"/>
			</div>
			</td>
			</tr>
			
			<tr>
			<td>
			<div style="margin-left:340px;margin-top:-43px;"><label id=""class="mandatory-lbl">Defect</label></div>
			<div style="margin-left:340px;">
            <input id="cmbQcapDefect" name="cmbQcapDefect" type="text" class="easyui-text"  style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapDefect}" />
			</div>
			</td>
			</tr>

		   <tr> 
		   <td>
		   <div style="margin-left:520px;margin-top:-46px;"><label id=""class="mandatory-lbl">Description of Quality Issue</label></div>
	       <div style="margin-left:520px;"> 
		   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtQcapQualityIssue" name="txtQcapQualityIssue" style="resize:none;width:325px;height:78px;"  
									value="">${requestScope.qtmTlCapa.qcapQualityIssue}</textarea>	  
			</div>
			</td>
			</tr>
			
			<tr>
			<td>
			 <div style="margin-left:850px;margin-top:-87px;"><label id=""class="mandatory-lbl" >Quantity Affected</label></div>
			 <div style="margin-left:850px;">
			 <input id="txtQcapQuantityeffect" name="txtQcapQuantityeffect" type="text" class="easyui-text" maxlength="100" value="${requestScope.qtmTlCapa.qcapQuantityeffect}"style="width: 100px;text-transform:uppercase;"/>
			 </div>
			 </td>
			 </tr>
			
			<tr>
			<td>
			<div style="margin-left:960px;margin-top:-89px;"><label id=""class="mandatory-lbl" >UOM</label></div>
			<div style="margin-left:960px;"> 
			<input id="cmbQcapUom" name="cmbQcapUom" type="text" class="easyui-text" value="${requestScope.qtmTlCapa.qcapUom}" style="width:140px;text-transform:uppercase;"/>                                                       
			</div>
			</td>
			</tr>
		
	       <tr>
	       <td>
		<div style="margin-left:-7px; margin-top:-45px;"><label class="">Complaint Type</label></div>
		<div style="margin-left:-7px;">		  
<%-- 		    <input id="cmbQcapType" name="cmbQcapType"  class="easyui-combobox" style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapTodmtleader}" />
 --%>				
 <!-- Elumalai -->
 <input id="cmbQcapType" name="cmbQcapType"  class="easyui-combobox" style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapType}" />
   </div>
				</td>
		   <td>
		   <div style="margin-left:-930;margin-top:-47px;"><label class="mandatory-lbl">Complaint Raised By</label></div>
			<div style="margin-left:-930;">
			<span style="float:left;padding-right:0px;">
		    <input id="cmbQcapComplaintraisedby" name="cmbQcapComplaintraisedby"  class="easyui-combobox"  style="width:164px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapComplaintraisedby}"/>
			</span>
		    </div>
		   </td> 
		   </tr>
	       
	       <tr>
			<td>
		<div style="margin-left:340px; margin-top:-50px;"><label class="mandatory-lbl">Target DMT Leader</label></div>
		<div style="margin-left:340px;">		  
		    <input id="cmbQcapTodmtleader" name="cmbQcapTodmtleader"  class="easyui-combobox" style="width:165px;text-transform:uppercase;" value="${requestScope.qtmTlCapa.qcapTodmtleader}" />
				  </div>
				</td>
				</tr>
				
				<tr>
				<td>
				<div style="margin-left:850px; margin-top:-53px;"><label class="mandatory-lbl">Target Area Incharge</label></div>
				  <div style="margin-left:850px;">
				  <input id="cmbQcapAreaincharge" name="cmbQcapAreaincharge" class="easyui-combobox" style="width:250px;"  value="${requestScope.qtmTlCapa.qcapAreaincharge}"/>
				  </div>
				</td>
				</tr>
				
			
	        
		</table>
		
	</div>
	
<div id="tabCAPA" class="easyui-tabs" style="height:auto;width:1102px;margin-top:6px;margin-left:3%; float:left;">
<div title="WorkFlow">
 <table style="margin-left:8px;margin-left:2px\9;"> 
 <tr>
						
 
 </tr>

</table>


     <div style="margin-left:39%;margin-left:39px\9;margin-top:-180px;\0\margin-top:5px;" >

     <div style="margin-left:38%;margin-left:60%\9;">
      
     </div>
     <div style="margin-left:-400px;margin-top:200px;">
	<table id="WorkFLowGrid" style=" "> <tr> <td> </td></tr> </table>
	 </div>
	<div style="height:10px;"></div>
	</div>
</div>


 <div title="Corrective and Preventive Actions">
 <table style="margin-left:30px;margin-top:30px;margin-left:50px\9"> 
 <tr>
 <td valign="top">
<td style="margin-left:5px;"  valign="top">
</td>
	 		
	<td>	
	 <div style="margin-left:1px;margin-top:-20px;">
	<input type="button" class="easyui-button" value="Add Row" id="btnAddRow"/>
	</div>	
	
	<div style="margin-left:70px;margin-top:-28px;">
	<input type="button" class="easyui-button" value="Remove Row" id="btnRemove"/>
	</div>		
    </td> 	
 </table>


<div style="margin-left:15px; margin-left:65px\9;">
	<table id="CAPAGrid">
	<tr><td></td></tr>
	</table>
	<div id="CAPApager"></div>
   </div>   
 </div>

  </div>
  
<%--  <input type="hidden" id="mode" name="mode" value="${requestScope.mode}">  vignesh --%>
<%--  <input type="hidden" id="hdnCapaId" name="hdnCapaId" value="${requestScope.qtmTlCapa.qcapKeyid}"/> --%>
 
 <input type="hidden" id="mode" name="mode"
       value="${empty requestScope.mode ? param.mode : requestScope.mode}">
 <input type="hidden" id="hdnCapaId" name="hdnCapaId"
       value="${empty requestScope.qtmTlCapa.qcapKeyid ? param.CapaId : requestScope.qtmTlCapa.qcapKeyid}"/>
 
 
 <input type="hidden" id="hdnLoginUser" name="hdnLoginUser" value="${requestScope.LoginUser}"/> 
  <input type="hidden" id="hdnLoginDMTUser" name="hdnLoginDMTUser" value="${requestScope.LoginDMTUser}"/>
   <input type="hidden" id="hdntitle" name="hdntitle" value=""/>
    <input type="hidden" id="hdnStatus" name="hdnStatus" value="${requestScope.Status}"/>
     <input type="hidden" id="hdnTargetFlid" name="hdnTargetFlid" value="${requestScope.TargetFlid}"/>
     
     <input type="hidden" id="hdnTargetJh" name="hdnTargetJh" value=""/>
     
     <input type="hidden" id="CapaMasterKeyIdVal" name="CapaMasterKeyIdVal" value="${requestScope.CapaMasterKeyIdVal}"/>
</form>