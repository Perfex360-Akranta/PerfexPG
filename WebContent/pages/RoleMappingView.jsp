<style>
.table {
	margin-left: -36px;
	margin-left: -22px\9

}
</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				initialiseForm('frmRoleView');
				jQuery('#submitForm').val('frmRoleView');				
				fillComboBox("frmRoleView", "cmbEmployee","employee.commonFilter");				
				fillComboBox("frmRoleView", "cmbRole","employeeRole.commonFilter");
				fillComboBox("frmRoleView", "cmbLocation","companyCombo.commonFilter");
				fillComboBox("frmRoleView","cmbActiveInactive","Combo_ActiveInactive.roleteam");
				fillComboBox("frmRoleView","cmbCategory","combo_EmployeeCategory.emp");//sriram16
				jQuery("#cmbActiveInactive").combobox("setValue",'Y');
				var ActiveYN=jQuery("#cmbActiveInactive").combobox("getValue");
				//alert(ActiveYN);
				var url = jQuery('#hiddenUrl').val();
				var  dataString="q=2";
				viewGrid(url,dataString+"&ActiveYN="+ActiveYN);
				//viewGrid(url,dataString);
				
						jQuery("#level").combobox(
			{
				onSelect : function(recordid) {
					jQuery("#cmbLocation").combobox('clear');
					reloadCombo("frmRoleView", "cmbLocation",
							getComboUrl(recordid.text));
					//jQuery("#cmbLocation").focus();						
				}
			});
				
				jQuery("#btnShow").click(function() {
					//alert("In progress.....");
					
					//var cmbLocationVal=getFieldValue("cmbLocation","frmRoleView");
					//var cmbEmployeeVal=getFieldValue("cmbEmployee","frmRoleView");
					//var cmbRoleVal=getFieldValue("cmbRole","frmRoleView");
					//sriram
						var categoryVal = getFieldValue("cmbCategory", "frmRoleView");//sriram16
						//alert(" 3 :" +cmbLevelVal);
						var url = jQuery('#hiddenUrl').val();
						var  dataString="q=2";
						//var fnLevel = jQuery("#level").combobox("getText");
						//var fnLocation = jQuery("#cmbLocation").combobox("getText");
						var cmbLevelVal=getFieldValue("level");
						var fnLocationVal =getFieldValue("cmbLocation","frmRoleView");
						var empKeyId =getFieldValue("cmbEmployee","frmRoleView");
						var roleKeyId=getFieldValue("cmbRole","frmRoleView");
						//alert(roleKeyId);
						var ActiveYN=jQuery("#cmbActiveInactive").combobox("getValue");
						//alert(ActiveYN);
						if(isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId)  ){
									jQuery('#msg').html('<font color="red">Select Function Location, Employee or Employee Role</font>');
						}else{
							jQuery('#msg').html('<font color="red"></font>');
							//viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId+"&ActiveYN="+ActiveYN);
							viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId+ "&txtEmpwiseType=" + categoryVal+"&ActiveYN="+ActiveYN);

						}
						//jQuery("#level").combobox('clear');
						//jQuery("#cmbLocation").combobox('clear');
						//jQuery("#cmbEmployee").combobox('clear');
						//jQuery("#cmbRole").combobox('clear');
						//alert("roleKeyId " + roleKeyId);
						/*
						if((!isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId) ) || (isEmpty(fnLocationVal) && !isEmpty(empKeyId) && isEmpty(roleKeyId) )
								||(isEmpty(fnLocationVal) && isEmpty(empKeyId) && !isEmpty(roleKeyId) )|| (isEmpty(fnLocationVal) && !isEmpty(empKeyId) && !isEmpty(roleKeyId) )
								(!isEmpty(fnLocationVal) && !isEmpty(empKeyId) && isEmpty(roleKeyId) )||(!isEmpty(fnLocationVal) && isEmpty(empKeyId) && !isEmpty(roleKeyId) )){
							viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId);
						}*/
							
						
					
					
				});
				
			});

			jQuery("#level").combobox(
			{
				onSelect : function(recordid) {
					jQuery("#cmbLocation").combobox('clear');
					reloadCombo("frmRoleView", "cmbLocation",
							getComboUrl(recordid.text));
					//jQuery("#cmbLocation").focus();						
				}
			});
			
		/*	jQuery("#cmbLocation").combobox(
					{
						onSelect : function(recordid) {
							jQuery("#cmbEmployee").combobox('clear');
							reloadCombo("frmRoleView", "cmbEmployee",getComboUrleEmployee(recordid.text));
							//jQuery("#cmbLocation").focus();						
						}
			});
			function getComboUrleEmployee(cmbTxt) {
				var cmbUrl = null;
				//var txt=jQuery("#cmbLocation").combobox("getText");
				//alert("Get Text :" +txt);
				var val=getFieldValue("cmbLocation","frmRoleView");
				//alert("Get val :" +val);
				var locationId=jQuery("#hdnflid").val();
				//alert("Location id:" + locationId);
				if (cmbTxt == 'BCM-2000')
					cmbUrl = "employee.commonFilter?locnId=" + val;
				else if (cmbTxt == 'KOVAI-4000')
					cmbUrl = "employee.commonFilter?locnId=" + val;
				else
					cmbUrl = "employee.commonFilter?locnId=" + locationId;
				//alert(" cmbUrl :" +cmbUrl);
				return cmbUrl;

			}*/
		jQuery("#btnShow").click(function() {
		//alert("In progress.....");
		
		//var cmbLocationVal=getFieldValue("cmbLocation","frmRoleView");
		//var cmbEmployeeVal=getFieldValue("cmbEmployee","frmRoleView");
		//var cmbRoleVal=getFieldValue("cmbRole","frmRoleView");
		//sriram
			var categoryVal = getFieldValue("cmbCategory", "frmRoleView");//sriram16
			//alert(" 3 :" +cmbLevelVal);
			var url = jQuery('#hiddenUrl').val();
			var  dataString="q=2";
			//var fnLevel = jQuery("#level").combobox("getText");
			//var fnLocation = jQuery("#cmbLocation").combobox("getText");
			var cmbLevelVal=getFieldValue("level");
			var fnLocationVal =getFieldValue("cmbLocation","frmRoleView");
			var empKeyId =getFieldValue("cmbEmployee","frmRoleView");
			var roleKeyId=getFieldValue("cmbRole","frmRoleView");
			//alert(roleKeyId);
			var ActiveYN=jQuery("#cmbActiveInactive").combobox("getValue");
			//alert(ActiveYN);
			if(isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId)  ){
						jQuery('#msg').html('<font color="red">Select Function Location, Employee or Employee Role</font>');
			}else{
				jQuery('#msg').html('<font color="red"></font>');
				//viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId+"&ActiveYN="+ActiveYN);
				viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId+ "&txtEmpwiseType=" + categoryVal+"&ActiveYN="+ActiveYN);

			}
			//jQuery("#level").combobox('clear');
			//jQuery("#cmbLocation").combobox('clear');
			//jQuery("#cmbEmployee").combobox('clear');
			//jQuery("#cmbRole").combobox('clear');
			//alert("roleKeyId " + roleKeyId);
			/*
			if((!isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId) ) || (isEmpty(fnLocationVal) && !isEmpty(empKeyId) && isEmpty(roleKeyId) )
					||(isEmpty(fnLocationVal) && isEmpty(empKeyId) && !isEmpty(roleKeyId) )|| (isEmpty(fnLocationVal) && !isEmpty(empKeyId) && !isEmpty(roleKeyId) )
					(!isEmpty(fnLocationVal) && !isEmpty(empKeyId) && isEmpty(roleKeyId) )||(!isEmpty(fnLocationVal) && isEmpty(empKeyId) && !isEmpty(roleKeyId) )){
				viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId);
			}*/
				
			
		
		
	});
	
	function docDoubleClick(id) { 
		//var rowData = jQuery("#list").jqGrid('getRowData',id);
		//var level = rowData.frl_level;
		//var Flid =  rowData.flid;
		//navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
	}
	
	function viewGrid(url,dataString)
	{
		
		processGridnew(url,dataString,"roleViewGrid","pager","","docDoubleClick");
	}
	function getComboUrl(cmbTxt) {
		var cmbUrl = null;
		
		var locationId=jQuery("#hdnflid").val();
		//alert("Location id:" + locationId);
		if (cmbTxt == 'Company')
			cmbUrl = "companyCombo.commonFilter";
		if (cmbTxt == 'Location')
			cmbUrl = "location.funlocn";
		if (cmbTxt == 'SBU')
			cmbUrl = "sbuCombo.commonFilter?locnId=" + locationId;
		if (cmbTxt == 'PBU')
			cmbUrl = "pbuCombo.commonFilter?locnId=" + locationId;
		if (cmbTxt == 'DMT')
			cmbUrl = "sectionCombo.commonFilter?locnId=" + locationId;
		if (cmbTxt == 'JH')
			cmbUrl = "cellCombo.commonFilter?locnId=" + locationId;
		//alert(" cmbUrl :" +cmbUrl);
		return cmbUrl;
	}
	jQuery("#btnActiveorInactive").click(function() {
		var ActiveYN=jQuery("#cmbActiveInactive").combobox("getValue");
		
		//alert(" 3 :" +cmbLevelVal);
		var url = jQuery('#hiddenUrl').val();
		var  dataString="q=2";
		//var fnLevel = jQuery("#level").combobox("getText");
		//var fnLocation = jQuery("#cmbLocation").combobox("getText");
		var cmbLevelVal=getFieldValue("level");
		
		var fnLocationVal =getFieldValue("cmbLocation","frmRoleView");
		var empKeyId =getFieldValue("cmbEmployee","frmRoleView");
		var roleKeyId=getFieldValue("cmbRole","frmRoleView");
		//alert(roleKeyId);
		viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId+"&ActiveYN="+ActiveYN);
	
	});
</script>
<form action="" method="post" id="frmRoleView">
	<div id="wrapper" style="width: 100%; padding: 0%;">	
		<div style="padding-left: 3%; padding-left: 2% \9;">		
			<div class="easyui-paddingbfpx" style="padding-top: 0px;">
				<label style="padding-left: 10px;">Function Location Level</label>
				<label style="padding-left: 255px;">Functional Location</label>
				<label style="padding-left: 316px;">Employee Category</label>
				<label style="padding-left:115px; /* display: none; */">Active/InActive</label>
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				     <select id="level" class="easyui-combobox" name="level" style="width: 350px;/*  height: 20px; */">
						<option value="cmp">Company</option>
						<option value="lcn">Location</option>
						<option value="sbu">SBU</option>
						<option value="subunt">PBU</option>
						<option value="sect">DMT</option>
						<option value="cell">JH</option>

				    </select>
				</span> 
				<span style="padding-left: 35px; padding-left: 45px\9; vertical-align: top;">
					<input id="cmbLocation" name="cmbLocation" class="easyui-combobox" style="width: 400px; /* height: 20px; */" />
				</span>
					<span style="padding-left: 20px; vertical-align: top;">
				<input id="cmbCategory" name="cmbCategory" class="easyui-combobox" style="width: 200px;"/>
</span>
				<span id="msg" style="padding-left: 10px;vertical-align: top;padding-top: 1px;">
				
					
				</span>
			
				
				<span style="padding-left:10px; padding-left:10px\9; vertical-align: top; /* display : none; */">
				<input id="cmbActiveInactive" name="cmbActiveInactive" class="easyui-combobox" style="width:100px; /* height:20px; */" />
				</span>
				
			
			</div>
		
		
			<div class="easyui-paddingbfpx" style="padding-top: 2px;">
				<span style="padding-left: 0px;vertical-align: top;padding-top: 1px;">
					<label style="padding-left: 10px;">Employee</label>
				</span>
			
				<span style="padding-left: 327px;vertical-align:top;padding-top: 1px;">
					<label>Employee Role</label>
				</span>
				
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				     <input id="cmbEmployee" name="cmbEmployee" class="easyui-combobox" style="width: 350px;/*  height: 20px; */" />
				</span> 
				
				<span style="vertical-align: top; padding-left: 35px;"> 
				     <input id="cmbRole" name="cmbRole" class="easyui-combobox" style="width: 400px;/*  height: 20px; */" />
				</span> 
				
				<span style="padding-left:40px;  vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnShow" name="btnShow" value="View Employee"
					style="height: 20px;" />
					
				</span>
				<span style="padding-left:250px; padding-left:15px; vertical-align: top; display:none ;"> 
					<input type="button" class="easyui-button" id="btnActiveorInactive" name="btnActiveorInactive" value="View Active OR Inactive"
					style="height: 20px;" />
					
				</span>
			</div>		
		
	
		</div>
		<div id="wrapperRpt" >
   			<div style="margin-top: -28px; padding-top: 2px;"></div>
				<table id='roleViewGrid'>
					<tr>
						<td></td>
					<tr>
				</table>
			<div id='pager'></div>	
		</div>
	</div>
	

</form>


<input type="hidden" id="hdnflid" name="hdnflid" value="${loginLocnId}"/>
