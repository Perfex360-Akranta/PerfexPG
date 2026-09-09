<script type="text/javascript"> 
jQuery(document).ready(function(){	
	initialiseForm('frmPassword');	    	  
    jQuery('#submitForm').val('frmPassword');
	var btnVisble = jQuery("#btnVisble").val();
	if(btnVisble == "Y")
	jQuery("#btnExitSpan").show();
	//alert(btnVisble);
	//alert(123);
	//var oldpass = jQuery("#txtcpwdOldpwd").val();
    //alert('oldpwd' + oldpass);
     
    jQuery("#btnCPsave").click(function(){
    	saveForm('frmPassword','savenew_pwd.userLogin');
    });

    jQuery("#btnExitSpan").click(function(){
    	document.location.href="logout.userLogin";
    	//closePopUpDialoge('LoaddefContenpwd',false);
    });
    
    //var oldpass = jQuery("#txtcpwdOldpwd").val();
   	//alert('oldpwd 2' + oldpass);
    
    setTimeout(function() { setFocusOnField('txtcpwdOldpwd'); },350);
    
});
//jQuery("#txtcpwdOldpwd").val('');
	
	function saveonkey(e)
	{   
		var code = (e.keyCode ? e.keyCode : e.which);
		if ( code == 13)
		{
			saveForm('frmPassword','savenew_pwd.userLogin');
		}
	}
function frmPassword_successsCallback(result)
{   
	//alert(123);
	showValidationErrorMsg('saveMsg',result.successData.msg);
	
	setTimeout(function() {document.location.href="logout.userLogin";},1250);
}

</script>


<div id="PasswordDiv" align="center" >
		<form onkeypress="saveonkey(event)" name="frmPassword" id="frmPassword">
		
				 <table  style="padding-left: 100px;" align="left">
				 <tr>
				 <td >
				 <div style=" padding-left:0px;"> <label class="mandatory-lbl">Login Name</label> </div> 
                  <div class="easyui-paddingbfpx" style=" padding-left:0px;" class="easyui-paddingbfpx"> 
                 		 <input id="txtcpwdUsername" name="txtcpwdUsername" class="easyui-text" type="text"  value="${requestScope.loginId}" readonly="readonly" style="width: 255px;"/>
                   </div>
<!--                   <div style=" padding-left:50px;"> <label class="mandatory-lbl">LastLogged on</label> </div> -->
<!--                  <div style=" padding-left:50px;" class="easyui-paddingbfpx"> -->
<!--                  <input type="text" id="loggedon" class="easyui-text"; style="width: 255px;"/>-->
<!--                   </div>-->
				  <div style=" padding-left:0px;"> <label class="mandatory-lbl">Old Password</label> </div> 
                  <div class="easyui-paddingbfpx" style=" padding-left:0px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtcpwdOldpwd" name="txtcpwdOldpwd" autocomplete="off" value="" class="easyui-text" style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:0px;"> <label class="mandatory-lbl">New Password</label> </div> 
                  <div style=" padding-left:0px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtcpwdNewpwd" name="txtcpwdNewpwd" onchange="fnchkpasswrd" autocomplete="off" class="easyui-text" style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:0px;"> <label class="mandatory-lbl">Confirm  Password</label> </div> 
                  <div style=" padding-left:0px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="txtcpwdConfpwd" name="txtcpwdConfpwd" onchange="fnchkpasswrd" autocomplete="off"  class="easyui-text" style="width: 255px;"/>
                   </div>
                 
                   <div  style="padding-left:100px;padding-top:20px;padding-bottom:10px;" class="easyui-paddingbfpx">
                   <input id="btnCPsave" class="easyui-button"  type="button"  value="Save"/> 
                   <span id="btnExitSpan" style="display:none;"><input id="btnExit" class="easyui-button"  type="button" value="Exit" /></span>
				   </div>
                   <div id="saveMsg"></div>
                   
            		</td></tr></table>    	
            		<div style="border-left:6px solid rgb(193,205,193);margin-left: 50%;  height:300px;">
            		<table align="right" style="">
            		<tr>
            		<td>
            		
            		<div style=" padding-left:0px;padding-top:20px;" align="center"> <label style="font-size: 20px;font: bold; " class="mandatory-lbl">Password Policy</label> </div>
            		 <div style=" padding-left:0px;padding-top:20px;"> <label class="mandatory-lbl">Lower case or upper case alphabets or a combination of both</label> </div>
            		  <div style=" padding-left:0px; padding-top:20px; "> <label class="mandatory-lbl">At-least one Number</label> </div>
            		   <div style=" padding-left:0px; padding-top:20px; "> <label class="mandatory-lbl">At-least one special character (e.g. !@#$%^&*()_)</label> </div>
            		    <div style=" padding-left:0px; padding-top:20px; "> <label class="mandatory-lbl">Minimum password length shall be set to at least 8 characters</label> </div>
            		     
            		  
            		</td>
            		</tr>
            		</table>
            		</div>
            		<input type="hidden" id="btnVisble" value="${requestScope.btnVisb}"/>
            		<input type="hidden" id="hdnusrcode" name="hdnusrcode" value="${requestScope.usercode}"/>
            		<input type="hidden" id="mode" name="mode"  />
                   </form>
           </div>

