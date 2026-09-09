
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	processGridnew(url,"q=","grdCustCom","pgrCustCom","","","","");
	
});

function whywhyBtn(id, options, rowObject){
	return '<input type="button" id="btnwhywhy_'+options.rowId+'_'+options.pos+'"  name="btnActionGrid_'+options.rowId+'_'+options.pos+'" onclick="showWhyWhy('+options.rowId+')" style="width:80px;  height:23px;"   class="easyui-button" value="Why Why"/>';
}
function kaizenBtn(id, options, rowObject){
	return '<input type="button" id="btnkaizen_'+options.rowId+'_'+options.pos+'" name="btnActionGrid_'+options.rowId+'_'+options.pos+'" onclick="showwkaizen('+options.rowId+')"  style="width:80px;  height:23px;"   class="easyui-button" value="Kaizen"/>';
}
function actionPlanBtn(id, options, rowObject){
	return '<input type="button" id="btnAction_'+options.rowId+'_'+options.pos+'" name="btnActionGrid_'+options.rowId+'_'+options.pos+'" onclick="showActionPlan('+options.rowId+')"  style="width:80px;  height:23px;"   class="easyui-button" value="Action Plan"/>';
}

function FileMgrBtn(id, options, rowObject){
	return '<input type="button" id="btnFileMgr_'+options.rowId+'_'+options.pos+'" name="btnActionGrid_'+options.rowId+'_'+options.pos+'" onclick="openFileManager('+options.rowId+')"  style="width:80px;  height:23px;"   class="easyui-button" value="File Manager"/>';
}

function showWhyWhy(id){
	var complaintNo = jQuery("#grdCustCom").jqGrid('getCell',id,1);
	//alert("The complaintNo:::"+complaintNo);
	var problem=jQuery("#grdCustCom").jqGrid("getCell",id,"BillingDocument");
	var forwardData = "";
	var persistentData ="";
	openWhyWhy("divWhyWhy",false,complaintNo,"CRM","","",problem,"create","","","","","");
}

//function openWhyWhy(divId,isPopup,refDocId,refDocType,flId, refDocDate, problem,
//yyMode, forwardData, persistentData, attendedBy,area,pillar)


function showwkaizen(id){
	var complaintNo = jQuery("#grdCustCom").jqGrid('getCell',id,1);
	//alert("complaintNo::::"+complaintNo);
	var flid = "";
	var date = jQuery("#grdCustCom").jqGrid("getCell",id,"ComplaintDate");
	var proposedID = jQuery("#grdCustCom ").jqGrid('getCell',id,"KaizenNo");
	//alert("Propose id"+proposedID);
	var persistentData ="";
	var forwardData = "";
	var persistentData = {"flid":flid,"date":date,"mode":"create"};
	var forwardData = {"flid":flid,"date":date,"mode":"create"};
	var dataStr ="?&refDocNo="+complaintNo+"&flid="+flid+"&refDocType=CRM&Keyid="+proposedID+"&AccSingle=N";
	navigateToNextForm("KaizenBankSuggestion_input.kznbnk"+dataStr,"Kaizen Suggestion",forwardData,persistentData);
}
 function showActionPlan(id){
 var complaintNo = jQuery("#grdCustCom").jqGrid('getCell',id,1);
 var actionplanno = jQuery("#grdCustCom ").jqGrid('getCell',id,"ActionPlanNo");
	var date = jQuery("#grdCustCom").jqGrid("getCell",id,"ComplaintDate");
 //alert("complaintNo:::"+complaintNo);

 
	 openActionPlan("CustActionplan",complaintNo,"CRM","","","","","modify");


 }
 
 function openFileManager(id)
 {
 var complaintNo = jQuery("#grdCustCom").jqGrid('getCell',id,1);
 if(complaintNo!=null)
	{ 
   fileManagerPopUp(complaintNo,"CRM"," ","btnFileMgr","btnFileMgr");
	}
 }

 /*function frmCustComplaintDoubleClick(id){
	    var complaintNo = jQuery("#grdCustCom").jqGrid('getCell',id,1);
	    alert(complaintNo);
        LoadPopUp("divCRMCustomer","NewCustComp_input.cuscom?&complaintNo="+complaintNo,true,"75%","75%","20%","20%","showResult_successCallBack","CRM",true);  	
 }*/
 
 
 
</script>
<form id="frmCustComplaint">
<div style="margin-left:2%;">
	<table id="grdCustCom" style="width:100%;"><tr><td/></tr></table>
	<div id="pgrCustCom"></div>
</div>
</form>
