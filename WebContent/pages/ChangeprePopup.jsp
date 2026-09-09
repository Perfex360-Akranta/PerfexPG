<script type="text/javascript">

jQuery(document).ready(function(){
	initialiseForm('frmChangepop');
	jQuery('#submitForm').val('frmChangepop');	
	fillComboBox("frmChangepop","cmbSdadResponsibilty","employee.commonFilter" );
});

//var button=jQuery("#btnsaveres").val();
jQuery("#btnsaveres").click(function(){
   var chkid=jQuery("#hdnchkid").val();
   var Keyid=jQuery("#hdnkeyid").val();
   var oldemployee=jQuery("#hdnoldemployeeid").val();
   var responsevalue=jQuery("#cmbSdadResponsibilty").combobox('getValue');
  if(responsevalue.length!=0&&oldemployee.length!=0)
  	{
	  processAjaxCalls("ChangeprePopup_save.prpo","q=2&responsevalue="+responsevalue+"&Projectkeyid="+Keyid+"&oldemployee="+oldemployee,"","");
 closePopUpDialoge("divChangeprolead");
  alert("Response Assigned Successfully");
setTimeout(function() {
	jQuery("#projectgrid").jqGrid().trigger("reloadGrid");
},10);
   	}
});


jQuery(".btn_close").click(function(){
	
	setTimeout(function() {
	jQuery('.cbox').attr('checked', false);
	},10);
	//jQuery('#cbox'+ gridId +'_'+rowId).is(':checked')== false)
});
  


</script>
<form id="frmChangepop" >
<div id="loadresponse">
<table id="ChangepopupGrid"></table>
<table align="center" >
     <tr>
            <td>
               <div style="margin-left:40%;"><label class="mandatory-lbl">Responsibility</label></div>
								<div style="margin-left:40%;">
									<input id="cmbSdadResponsibilty" name="cmbSdadResponsibilty" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Response}"/>
				
          </td>
     
     </tr>
    
    
    
  
</table>
          <div style="margin-left:37%; margin-top:3%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
	
           


<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.Keyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnprojectid" name="hdnprojectid" value="${requestScope.projectid}"></input>
<input type="hidden" id="hdnoldemployeeid" name="hdnoldemployeeid" value="${requestScope.oldemployeeid}"></input>
<input type="hidden" id="hdnchkid" name="hdnchkid" value="${requestScope.chkid}"></input>
</div>
</form>
