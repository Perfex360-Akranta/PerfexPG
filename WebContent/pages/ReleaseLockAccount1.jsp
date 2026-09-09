<script>
jQuery( "#cal" ).click(function() {
	
	jQuery( "#PasswordDiv" ).hide();
	jQuery( "#Main" ).show();
	/*.dialog( "close" );*/
});	
</script>

<div class="main-cntborder" style="height: 490px">
<div id="PasswordDiv" >
		<form action="">
		<div class="main-header" align="center">Change Default Password </div>
				 <table>
				 <tr>
				 <td>
				  <div style=" padding-left:450px;"> <label class="mandatory-lbl">Old Password</label> </div> 
                  <div style=" padding-left:450px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="oldPwd" class="easyui-text"; style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:450px;"> <label class="mandatory-lbl">New Password</label> </div> 
                  <div style=" padding-left:450px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="NewPwd" class="easyui-text"; style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:450px;"> <label class="mandatory-lbl">Confirm  Password</label> </div> 
                  <div style=" padding-left:450px;" class="easyui-paddingbfpx"> 
                  <input type="password" id="CmfPwd" class="easyui-text"; style="width: 255px;"/>
                   </div>
                   
                   <div style=" padding-left:450px;" class="easyui-paddingbfpx">
						<input type="submit" id="save" value="Save" class="easyui-button"/>
						<input type="reset" id="clear" value="Clear" class="easyui-button"/>
						<input type="button" id="cal" value="Cancel" class="easyui-button"/>
				  </div>
                   
            		</td></tr></table>
                   </form>
                   </div>
</div>
