<script type="text/javascript">
jQuery(document).ready(function(){	
	initialiseForm('frmPassword');	    	  
    jQuery('#submitForm').val('frmPassword'); 
});	
jQuery("#btnCPsave").click(function(){	
	saveForm('frmPassword','savenew_pwd.ReleaseAcc');
});
function frmPassword_successsCallback(result)
{
	showValidationErrorMsg('saveMsg',result.successData.msg);
	//jQuery('#saveMsg').html(result.successData.msg);//alert(result.successData.msg);
}
</script>


<div id="PasswordDiv" >
		<form  name="frmPassword" id="frmPassword">
		
				 <table>
				 <tr>
				 <td>
				  <div style=" padding-left:50px;"> <label class="mandatory-lbl">Old Password</label> </div> 
                  <div class="easyui-paddingbfpx" style=" padding-left:50px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtCpwdOldpwd" name="txtCpwdOldpwd" class="easyui-text" style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:50px;"> <label class="mandatory-lbl">New Password</label> </div> 
                  <div style=" padding-left:50px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtCpwdNewpwd" name="txtCpwdNewpwd" class="easyui-text"; style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:50px;"> <label class="mandatory-lbl">Confirm  Password</label> </div> 
                  <div style=" padding-left:50px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtCpwdConfpwd" name="txtCpwdConfpwd" class="easyui-text"; style="width: 255px;"/>
                   </div>
                 
                   <div  style="padding-left:150px;padding-top:20px;padding-bottom:10px;" class="easyui-paddingbfpx"><input id="btnCPsave" class="easyui-button"  type="button" value="Save"/> 
				   </div>
                   <div id="saveMsg"></div>
                   
            		</td></tr></table>
                   </form>
           </div>

