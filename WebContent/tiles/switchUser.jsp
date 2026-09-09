<script type="text/javascript">
	jQuery(document).ready(function(){				
		//initialiseForm('frmSwitchUser');
	//	var dataString = jQuery('#hdnDatastr').val();
		//alert(dataString);
		fillComboBox("frmSwitchUser","cmbCurrentRoles","Combo_UserRoll.creat?loginUserRole=Y" );
		fillComboBox("frmSwitchUser","cmbSwitchRoles","Combo_UserRoll.creat?loginUserRole=Y" );
		
		jQuery("#btnSwtichUserOk").click(function(){
			switchUsercmbSwitchRoles();		
		});
		
		jQuery("#btnSwtichUserCancel").click(function(){
			closePopUpDialoge("loadSwithUser");
		});

	});
	
	function frmSwitchUsercmbSwitchRoles_onLoadSuccess(){
		var currentRole = getFieldValue("hdnUserRole");
		var currentFlid = getFieldValue("hdnLoginFlid");
		var rolesId = currentRole+'-'+currentFlid;
		setFieldValue("cmbCurrentRoles", rolesId);
		//setComboFirstValue("frmBanner", "cmbSwitchRoles");
	}
	
	function frmSwitchUsercmbCurrentRoles_onLoadSuccess(){
		
	}

	function loadSwithUser_onClose(){
		return true;
	}
	function switchUsercmbSwitchRoles(){

		/*if(jQuery('#hdnFirstTime').val()=="Y") {
			jQuery('#hdnFirstTime').val("N");
			return;
		}
		else { */ 
			//if (jQuery('#submitForm').val().length>3) {
				var roleId = getFieldValue("cmbSwitchRoles");
					if(roleId ==null || roleId=="" || roleId.length ==0 ){
						alert("Select Switch Role");
						return;
					}	
		 		var msg ="This will change your Role,  It requires the current page reload ! Press Ok to Continue ";
				if(confirm(msg) == false)				
					return;	
				
				 var newRol =  getComboBoxText("cmbSwitchRoles");
				jQuery("#divLoginUserRole").html(newRol);

				//var prevFrmNavig = formNavigations.pop();
				//var curFrmNavig = formNavigations.pop(); 
				//if(curFrmNavig != null){
					//alert(curFrmNavig.divId);
					jQuery("#preLoadContent").addClass("tpm-loading");
					jQuery("#preLoadContent").html("Loading...");
					jQuery("#preLoadContent").css("display","block");
					//formNavigations.push(curFrmNavig);
					//formNavigations.push(prevFrmNavig);
				
					closePopUpDialoge("loadSwithUser");
				
					
						//alert(roleId);
					//jQuery('#cmbEmployeRoles').combobox('setValue',roleId);
					//setFieldValue("cmbEmployeRoles", roleId,"frmBanner");
			setTimeout(function() {
					
					jQuery("#cmbCurrentRoles").combobox("setValue",roleId);
					jQuery("#cmbSwitchRoles").combobox("clear");
					
					var roleFlid = roleId.split('-');
					var flid = "";
					if(roleFlid[0].length>0) {
						jQuery("#hdnUserRole").val(roleFlid[0]);
						roleId = roleFlid[0];
					}
					//alert(roleId);
					if(roleFlid[1].length>0)
						flid=roleFlid[1];
					//alert(flid);
					setLoginElementDetails(roleId, flid);
					//processAjaxCalls("getElementId.userLogin","roleId="+roleId,"getElementIdsuccessCallBack","getElementIdonerrorCallBack","","");
					//alert('refresh');
					 //refreshForm();
					jQuery("#preLoadContent").css("display","none"); 
			},550); 
				//}
			//}
	 //}

		
		//refreshForm();
	}
/*
	function getElementIdsuccessCallBack(result)
	{
		jQuery("#hdnLoginElementid").val(result.elementId);
		jQuery("#hdnLoginFlid").val(result.flid);
		refreshForm();
		jQuery("#preLoadContent").css("display","none");
		
	}
*/
	
</script> 

<form id="frmSwitchUser"  >
	<div style="margin-left:10%; margin-top:15% ">
	
	<div  class="easyui-paddingbfpx">
		<label>Current Role</label>                       
     </div> 
	<div class="easyui-paddingbfpx" style=" width:280px;">
		<input id="cmbCurrentRoles" name="cmbCurrentRoles" disabled="disabled" class="easyui-combobox"  style="width:230px" value=""  >
	</div>
	
	<div  class="easyui-paddingbfpx">
		<label>Switch Role</label>                       
     </div> 
	<div class="easyui-paddingbfpx" style=" width:280px;">
		<input id="cmbSwitchRoles" name="cmbSwitchRoles" class="easyui-combobox"  style="width:230px" value=""  >
	</div>
	
	</div>
	<div align="center" style="padding-top: 3%;margin-top:5% ">
    <input type="button" id="btnSwtichUserOk"  class="easyui-button"  value="Ok" />
    <input type="button" id="btnSwtichUserCancel"  class="easyui-button"  value="Cancel"  />
	</div>
	
</form>