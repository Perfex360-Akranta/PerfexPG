<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmMaillst');
	//jQuery('#neweml').hide();
	var sectionId=jQuery('#hdnsectionId').val();
	
processGridnew("employee_mail_input.clitcal","&q=2&sectionId="+sectionId,"empLstGrid","empLstPager","","","","loadSuccess");


});



function MailFormater(id, options, rowObject){//alert("");
	var rowId = options.rowId;
	return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value=""  onclick="if(this.checked){SapchkboxCheck(\''+rowId + '\');}else{SapchkboxUnCheck(\''+ rowId +'\')}"/>';
}
function SapchkboxCheck(rowId){
  // KEYID -- selected value will be in this column [1 or 0]
   jQuery("#empLstGrid").jqGrid('setCell',rowId,'checkVal','1');
}
function SapchkboxUnCheck(rowId){
   jQuery("#empLstGrid").jqGrid('setCell',rowId,'checkVal','0');
}

jQuery('#btnSave').click(function(){
	//randomNumberFromRange(1,100);
	
	//alert(12365);
	
	 var x = jQuery('#txtEmpEmail').val();//document.forms["myForm"]["email"].value;
      //alert(x +" email");
	    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
	
	saveForm("frmMaillst","kaizen_Mail_save.kaizen");	
	
});
jQuery('#btnAddNew').click(function()
		{ 
			jQuery('#neweml').show();
	});
function frmMaillst_successsCallback(result)
{
	// var keyId=result.successData.keyId;

		jQuery("#empLstGrid").trigger("reloadGrid");
 
	
	 return true;
}
jQuery('#btnemail').click(function(){
	//alert('45');	
	 var colData = JqGridToJsonSelectdRows("empLstGrid","checkbox","checkVal" );/*JqGridToJsonSelectdRows*/
	//alert("colData  "+colData);	 
	var employeeList=colData;//'[{"item":'+colData+'}]';
	//var fileName = jQuery("#hdnattachFile").val();
	var refId = jQuery("#hdnrefId").val();
	
	
	var cellName=jQuery('#hdnCellName').val();
	var machineName=jQuery('#hdnMachineName').val();
	var assembly=jQuery('#hdnAssembly').val();
	var activity=jQuery('#hdnActivity').val();
	var observation=jQuery('#hdnObservation').val();
	//alert("fileName "+ fileName +" kaizenId "+refId);
	var ds =employeeList;
	
     processAjaxCalls("email_send.clitcal?","&ds="+ds+"&refId="+refId+"&cellName="+cellName+"&machineName="+machineName+"&assembly="+assembly+"&activity="+activity+"&observation="+observation, "sendMail_successcalBack", "", "", "", "new");
		//processAjaxCalls("email_send.kaizen","&sendTo="+sendTo+"&ccTo="+ccTo+"&subject="+subject+"&message="+message+"&fileName="+fileName+"&kaizenId="+kaizenId, "sendMail_successcalBack", "", "", "", "new");
	
});
function sendMail_successcalBack(result){
	//if(result.isSuccss){
	
		alert(result.msg);
	//}
}

function divMailList_onClose() {
	jQuery("#listGrid").trigger("reloadGrid");
	jQuery("#txtObservation").val('');
	return true;
}
</script>


<form id="frmMaillst">
<!--<div id="neweml">
<table>
<tr><td> <label  style="padding-left: 20px;">Employee Name</label>
</td><td> <label class="mandatory-lbl" style="padding-left: 30px;">Email</label>
</td>
</tr><tr>

<td style="padding-left:20px">
      <input type="text" id="txtEmpName" name="txtEmpName" class="easyui-text"  style=" width : 200px; text-align: left; " value="${requestScope.kznTlMst.kznmBenefitvalue}"   align="right"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
	 </td>
	 <td style="padding-left:30px"><input type="text" id="txtEmpEmail" name="txtEmpEmail" class="easyui-text"  style=" width : 220px; text-align: left;  " value="${requestScope.kznTlMst.kznmBenefitvalue}"   align="right"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
	 </td>
	 <td style="padding-left:20px">
	        <span ><input type="button" class="easyui-button" id="btnSave" style="width:50px"name="btnSave" value="Save"></span>
	 	 </td>
	 </tr>
	 <tr>
    <td style="padding-left:10px;">
    <div style="margin-top:0px;">
    <!--    <span><input type="button" class="easyui-button" id="btnSave" name="btnSave" value="Save"></span>
	    <span style="padding-left:20px"><input type="button" class="easyui-button" id="btnListClose" name="btnListClose" value="Delete"></span>	 
	 
	 --><!--</div></td></tr>
</table>
</div>-->
<div style="margin-top: 05px;padding-left:20px;">
 <!--       <span><input type="button" class="easyui-button" id="btnAddNew" name="btnAddNew" value="Add New MailId"></span>
       --> 
       <span style="margin-top: 05px;padding-left:20px;"><input type="button" class="easyui-button" id="btnemail" name="btnemail" value="Send"></span>

</div>

<div style="width:500px;margin-top:20px; padding-left:20px;">
 
	 <table id="empLstGrid" style="width:50%;" ><tr><td></td></tr></table>
	 <div id="empLstPager"></div>
</div>
	<!-- <div style="margin-top: 10px;padding-left:50px;">
	 <span><input type="button" class="easyui-button" id="btnListSave" name="btnListSave" value="Save"></span>
	 <span><input type="button" class="easyui-button" id="btnListClose" name="btnListClose" value="Close"></span>	 
	 </div>-->
	 <div>
			<input type="hidden" name="hdnsectionId" id="hdnsectionId" value="${requestScope.sectionId}">
	 		<input type="hidden" name="hdnrefId" id="hdnrefId" value="${requestScope.refId}">
	        <input type="hidden" name="hdnattachFile" id="hdnattachFile" value="${requestScope.refId}">
	        <input type="hidden" name="hdnCellName" id="hdnCellName" value="${requestScope.cellName}">
			<input type="hidden" name="hdnMachineName" id="hdnMachineName" value="${requestScope.machineName}">
	 		<input type="hidden" name="hdnAssembly" id="hdnAssembly" value="${requestScope.assembly}">
			<input type="hidden" name="hdnActivity" id="hdnActivity" value="${requestScope.activity}">
			<input type="hidden" name="hdnObservation" id="hdnObservation" value="${requestScope.observation}">
			
	 </div> 
	 
	
</form>