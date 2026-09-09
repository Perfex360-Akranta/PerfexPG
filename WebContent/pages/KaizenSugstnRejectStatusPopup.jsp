<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmKaizensuggstatus');
    var keyid=jQuery("#hdnKzbnsugstaKeyid").val();
   // alert("The keyid:::"+keyid);
//    processAjaxCalls("kaizenFillkzn_modify.kznbnk","keyid="+keyid,"updatekznSuccess","");

});
jQuery("#btnSubmit").click(function(){
	
		 var kznsuggkeyid=jQuery("#hdnKzbnsugstaKeyid").val();
		// alert("The KeyID::::"+kznsuggkeyid);
		 var kznstatus=getFieldValue("cmbSdamAudittypeStatus");
		 //alert(" The kznstatus"+kznstatus);
	 	if (kznstatus.trim().length==0) { 
	 		alert(" Select Reject/Rework");
			return false;
		}

		var dataString="?&frmsave=popup&kznsuggkeyid="+kznsuggkeyid+"&kznstatus="+kznstatus;
	     processAjaxCalls("KaizenSuggrejectststatus_save.kznbnk",dataString,"updateKaizenSuggStatusDatapopup","");
	     closePopUpDialoge("loadkaizenbankSugg");     
});

function updateKaizenSuggStatusDatapopup(result){
	alert("Data Updated Successfully");
	setFieldValue("cmbSdamAudittypeStatus","");
	jQuery("#KZBankGrid").trigger("reloadGrid");	
	return true;
}


function loadkaizenbank_onClose(){
	jQuery("#KZBankGrid").trigger("reloadGrid");
	return true;
}

/*function updatekznSuccess(result){
	jQuery('#txtKzbnKaizen').val(result[0][0]);
}*/

</script>
<form id="frmKaizensuggstatus" name="frmKaizensuggstatus">
<table align="center">
     <tr>     
        <td valign="top">
            <div id="Status"  style="margin-left:20px;">
				<label class=""><b>Status</b></label>
			<div>
			<select id="cmbSdamAudittypeStatus" class="easyui-combobox" name="cmbSdamAudittypeStatus"   style="width:120px; ">  		
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
	   <div style="margin-left:40px;margin-top:14px;">
	   	 <input type="button" id="btnSubmit" name="btnSubmit" class="easyui-button" value="Submit"/>
	   </div>
	   </td> 
     </tr>
   </table>           
<input type="hidden"id="hdnApmode" name="hdnApmode" value="${requestScope.apMode}" />
<input type="hidden" id="hdnKzbnsugstaKeyid" name="hdnKzbnsugstaKeyid" value="${requestScope.kznsuggKeyid}"></input>
</form>
