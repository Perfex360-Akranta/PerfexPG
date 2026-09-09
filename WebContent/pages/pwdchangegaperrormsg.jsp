
<script type="text/javascript"> 
jQuery(document).ready(function(){	
	
    jQuery("#btnExit").click(function(){    	
    	closePopUpDialoge('LoaddefContenpwd',false);
    });
    //setTimeout(function() { setFocusOnField('lblpwdmsg'); },350);
});
</script>
<div id="PasswordDivmsg" align="center" >
		<form  name="frmPasswordmsg" id="frmPasswordmsg">
		<table>
		<tr>
		<td style=" padding-top: 50px; ">
			<label id="lblpwdmsg" style=" font-size:larger;color: blue;  " > ${requestScope.pwdmsg1} </label>
		</td>
		</tr>
		<tr>
		<td style=" padding-top: 50px; ">
			<label id="lblpwdmsg" style=" font-size:larger;color: blue;  " > ${requestScope.pwdmsg} </label>
		</td>
		</tr>
		<tr >
		<td style=" text-align: center;padding-top: 50px; ">
			<input id="btnExit" class="easyui-button"  type="button" value="Close" />
		</td>
		</tr>
		</table>
			
         </form>
 </div>

