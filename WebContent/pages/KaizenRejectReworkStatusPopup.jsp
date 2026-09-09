<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmKaizenrejrewStaus'); 
	var url=jQuery("#hiddenUrl").val();
    var keyid=jQuery("#hdnKzbnstaKeyid").val();
  //  alert("The keyid:::"+keyid);
});

jQuery("#btnSubmit").click(function(){
	    var kznKeyid=jQuery("#hdnKzbnstaKeyid").val();
	//	 alert("The KeyID::::"+kznKeyid);
		 var kznStatus=getFieldValue("cmbKaizenrejrewStatus");
	//	 alert(" The kznstatus"+kznStatus);
		var dataString="?&frmsave=popup&kznKeyid="+kznKeyid+"&kznStatus="+kznStatus;
	   processAjaxCalls("Kaizenrejectreworkststatus_save.kaizen",dataString,"updateKaizenStatusDatapopup","");
	   closePopUpDialoge("loadkaizenRejRewStatus");     
});

function updateKaizenStatusDatapopup(result){
	alert("Data Updated Successfully");
	setFieldValue("cmbKaizenrejrewStatus","");
	jQuery("#list").trigger("reloadGrid");	
	return true;
}


function loadkaizenbank_onClose(){
	jQuery("#KZBankGrid").trigger("reloadGrid");
	return true;
}
</script>
<form id="frmKaizenrejrewStaus" name="frmKaizenrejrewStaus">
<table align="center">
     <tr>     
        <td valign="top">
            <div id="Status"  style="margin-left:5px;">
				<label class=""><b>Status</b></label>
			<div>
			<select id="cmbKaizenrejrewStatus" class="easyui-combobox" name="cmbKaizenrejrewStatus"   style="width:120px; ">  		
					<option value='R'><b>Rejected</b></option>
					<option value='E'><b>Rework</b></option> 
			</select>
		    </div>
		    </div>
        </td> 
     </tr>
     </table>
        <table>
        <tr>
	   <td colspan="3">
	   <div style="margin-left:20px;margin-top:14px;">
	   	 <input type="button" id="btnSubmit" name="btnSubmit" class="easyui-button" value="Submit"/>
	   </div>
	   </td> 
     </tr>
   </table>           
<input type="hidden"id="hdnApmode" name="hdnApmode" value="${requestScope.apMode}" />
<input type="hidden" id="hdnKzbnstaKeyid" name="hdnKzbnstaKeyid" value="${requestScope.kznKeyid}"></input>
</form>
