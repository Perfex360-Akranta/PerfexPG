	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<script type="text/javascript">
	    jQuery(document).ready(function(){	
		initialiseForm('frmloginfrmwrk');	
		jQuery('#submitForm').val('frmloginfrmwrk');		

		fillComboBox("frmloginfrmwrk","cmblgfrPassneverexpires","combo_lgpsexp.userLogin","",false);
		fillComboBox("frmloginfrmwrk","cmblgfrIsloginaudit","combo_lgadt.userLogin","",false);
		fillComboBox("frmloginfrmwrk","cmblgfrIspassaudit","combo_pasadt.userLogin","",false);
		fillComboBox("frmloginfrmwrk","cmblgfrIsprivaudit","combo_prvadt.userLogin","",false);
		
		
		numericTextBox('txtlgfrUsernamelength');
		numericTextBox('txtlgfrMinpasslength');
		numericTextBox('txtlgfrMaxpasslength');
		numericTextBox('txtlgfrAlphabets');
		numericTextBox('txtlgfrNumerals');
		numericTextBox('txtlgfrPasschangegap');
		numericTextBox('txtlgfrPasschangefreq');
		numericTextBox('txtlgfrPassgraceperiod');
		numericTextBox('txtlgfrPassintimation');
		numericTextBox('txtlgfrMincharpasschange');
		numericTextBox('txtlgfrFailedloginattempts');
		numericTextBox('txtlgfrPasshistoryremember');

		processGridnew("loginframwork_input.userLogin","&q=2","loginframeGrid", "pager", "LoginFrameWork", "fndoubleClick","","");
		
			
	});
		function IspassautogenChk()
		{  
			if (jQuery("#chklgfrIsautogenpass").is(":checked"))
			{
				setFieldValue('hdnlgfrIspassautogen',"Y",'frmloginfrmwrk');
				setFieldValue('txtlgfrDefsyspassword','','frmloginfrmwrk');
				document.getElementById("txtlgfrDefsyspassword").disabled = true;
			}
			else
			{
				setFieldValue('hdnlgfrIspassautogen',"N",'frmloginfrmwrk');
				document.getElementById("txtlgfrDefsyspassword").disabled = false;
				setFieldValue('txtlgfrDefsyspassword','','frmloginfrmwrk');
			}
		}
		
		function Ispolicyactive()
		{   
			if (jQuery("#chkIspolicyactive").is(":checked"))
			{
				setFieldValue('hdnlgfrIspolicyactive',"Y",'frmloginfrmwrk');		
			
				
			}
			else
			{
				setFieldValue('hdnlgfrIspolicyactive',"N",'frmloginfrmwrk');				
				
			}
		}
		
	function frmloginfrmwrk_successsCallback(result){
		processGridnew("loginframwork_input.userLogin","&q=2","loginframeGrid", "pager", "LoginFrameWork", "fndoubleClick","","");
		}
	function fndoubleClick(id)
	{
		var rowData = jQuery("#loginframeGrid").jqGrid('getRowData',id);
		
		 setFieldValue('hdnlgfrKeyid',rowData.Keyid, 'frmloginfrmwrk');
		 
		 setFieldValue('txtlgfrUsernamelength',rowData.UserNameLength, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrMinpasslength',rowData.MinPasswordLength, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrMaxpasslength',rowData.MaxPasswordLength, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrAlphabets',rowData.NoofAlphabets, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrNumerals',rowData.NoofNumbers, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrPasschangegap',rowData.PasswordChangeGap, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrPasschangefreq',rowData.PasswordChangeFreq, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrPassgraceperiod',rowData.PasswordGracePeriod, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrPassintimation',rowData.PasswordIntimation, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrMincharpasschange',rowData.MinPasswordChange, 'frmloginfrmwrk');
		 setFieldValue('txtlgfrFailedloginattempts',rowData.NoofFaildLoginAttempts,'frmloginfrmwrk');
		 setFieldValue('txtlgfrPasshistoryremember',rowData.PasswordHistoryRemember,'frmloginfrmwrk');
		 setFieldValue('cmblgfrPassneverexpires',rowData.PasswordNeverExp,'frmloginfrmwrk');
		 setFieldValue('cmblgfrIsloginaudit',rowData.IsLoginAudit,'frmloginfrmwrk');
		 setFieldValue('cmblgfrIspassaudit',rowData.IsPassAudit,'frmloginfrmwrk');
		 setFieldValue('cmblgfrIsprivaudit',rowData.IsPrivateAudit,'frmloginfrmwrk');
		 setFieldValue('txtlgfrDefsyspassword',rowData.Defaultpassword,'frmloginfrmwrk');
		 var chkIspasswordAutoGen = rowData.IsPasswordAutoGen;
		 var chkIsSecurityPolicyActive = rowData.IsSecurityPolicyActive;

		 if(chkIspasswordAutoGen == 'Y')
		 {   
			 jQuery("#chklgfrIsautogenpass").attr("checked",true);
			 setFieldValue('hdnlgfrIspassautogen',"Y",'frmloginfrmwrk');
			 document.getElementById("txtlgfrDefsyspassword").disabled = true;
		 }
		 else
		 {
			 jQuery("#chklgfrIsautogenpass").attr("checked",false);
			 setFieldValue('hdnlgfrIspassautogen',"N",'frmloginfrmwrk');
			 document.getElementById("txtlgfrDefsyspassword").disabled = false;
		 }
		 
		 if(chkIsSecurityPolicyActive == 'Y')
		 {   
			 jQuery("#chkIspolicyactive").attr("checked",true);
			 setFieldValue('hdnlgfrIspolicyactive',"Y",'frmloginfrmwrk');
			 
		 }
		 else
		 {
			 jQuery("#chkIspolicyactive").attr("checked",false);
			 setFieldValue('hdnlgfrIspolicyactive',"N",'frmloginfrmwrk');
			 
		 }
	}
	</script>

<form id="frmloginfrmwrk" name="frmloginfrmwrk">
	<div id="wrapper" style="width:100%">
		<div style="padding-left: 7%;">
				<table>
						<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">User Name Length</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrUsernamelength" name="txtlgfrUsernamelength" 
									maxlength="30" style="width: 115px; height: 21px;"
									value="${requestScope.AdmTlLoginframework.LgfrUsernamelength}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Minimum Password Length</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrMinpasslength" name="txtlgfrMinpasslength" 
									maxlength="30" style="width: 115px; height: 21px;"
								 value="${requestScope.AdmTlLoginframework.LgfrMinpasslength}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Maximum Password Length</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrMaxpasslength" name="txtlgfrMaxpasslength" 
									maxlength="30" style="width: 115px; height: 21px;"
									 value="${requestScope.AdmTlLoginframework.LgfrMaxpasslength}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Password Change Gap</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrPasschangegap" name="txtlgfrPasschangegap" 
									maxlength="30" style="width: 115px; height: 21px;"
								 value="${requestScope.AdmTlLoginframework.LgfrPasschangegap}"/>
								</div>
							</td>
							
							
							</tr>
							<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">No Of Alphabets</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrAlphabets" name="txtlgfrAlphabets" 
									maxlength="30" style="width: 115px; height: 21px;"
									 value="${requestScope.AdmTlLoginframework.LgfrAlphabets}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">No Of Numerals</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrNumerals" name="txtlgfrNumerals" 
									maxlength="30" style="width: 115px; height: 21px;"
								 value="${requestScope.AdmTlLoginframework.LgfrNumerals}"/>
								</div>
							</td>
							
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Default Password</label>
								</div>
								<div class="easyui-paddingbfpx">							
									<input type="password" class="easyui-text" id="txtlgfrDefsyspassword" name="txtlgfrDefsyspassword" 
									maxlength="30" style="width: 115px; height: 21px;"
								 value="${requestScope.AdmTlLoginframework.LgfrDefsyspassword}"/>
								</div>
							</td>
							
						  
							<td style="padding-left:30px">
								<div class="easyui-paddingbfpx">
									<input id="chklgfrIsautogenpass" name="chklgfrIspassautogen" onclick="IspassautogenChk();" type="checkbox"  value="" /><span Style="padding-left: 3px;"><label>Is Password Auto Generated </label></span>
								</div>
								
							</td>
						<!--  	<td style="padding-left:30px">
								<div class="easyui-paddingbfpx">
									<input id="chkIspolicyactive" name="chkIspolicyactive" onclick="Ispolicyactive();" type="checkbox"  value="" /><span Style="padding-left: 3px;"><label>Is Security Policy Active </label></span>
								</div>
								
							</td>-->
							</tr>
							
						<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Password Change Ferq Days</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrPasschangefreq" name="txtlgfrPasschangefreq" style="width: 115px; height: 21px;"
									maxlength="30" style="width: 90px; height: 21px;" 
								 value="${requestScope.AdmTlLoginframework.LgfrPasschangefreq}"/>
								</div>
							</td>
							<td style="padding-left:30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Password Grace Period Days</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrPassgraceperiod" name="txtlgfrPassgraceperiod" 
									maxlength="30" style="width: 115px; height: 21px;" 
									 value="${requestScope.AdmTlLoginframework.LgfrPassgraceperiod}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Password Intimation Days</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrPassintimation" name="txtlgfrPassintimation"
									 maxlength="30" style="width: 115px; height: 21px;"
									  value="${requestScope.AdmTlLoginframework.LgfrPassintimation}"/>
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">PassWord History Remember</label>
								</div>
								<div class="easyui-paddingbfpx">
								<input class="easyui-text" id="txtlgfrPasshistoryremember" name="txtlgfrPasshistoryremember"
									 maxlength="30" style="width: 115px; height: 21px;"
									  value="${requestScope.AdmTlLoginframework.LgfrPassintimation}"/>									
								</div>
							</td>
							<td>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Password Never Expires</label>
								</div>
								<div class="easyui-paddingbfpx">
								<input class="easyui-combo" id="cmblgfrPassneverexpires" name="cmblgfrPassneverexpires" 
									style="width: 115px; height: 21px;" value="${requestScope.JhnTlSladtl.LgfrPassneverexpires}" />															
								</div>
							</td>
							</tr>
							<tr style=" display:none; " >
							
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Is Login Audit</label>
								</div>
								<div class="easyui-paddingbfpx">
								<input class="easyui-combo" id="cmblgfrIsloginaudit" name="cmblgfrIsloginaudit" 
									style="width: 115px; height: 21px;" value="${requestScope.JhnTlSladtl.LgfrIsloginaudit}" />								
									
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Is Pass Audit</label>
								</div>
								<div class="easyui-paddingbfpx">
								<input class="easyui-combo" id="cmblgfrIspassaudit" name="cmblgfrIspassaudit" 
									style="width: 115px; height: 21px;" value="${requestScope.JhnTlSladtl.LgfrIspassaudit}" />
																		
								</div>
							</td>
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Is Private Audit</label>
								</div>
								<div class="easyui-paddingbfpx">
								<input class="easyui-combo" id="cmblgfrIsprivaudit" name="cmblgfrIsprivaudit" 
									style="width: 115px; height: 21px;" value="${requestScope.JhnTlSladtl.LgfrIsprivaudit}" />
									
								</div>
							</td>
								<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">No Of Faild Password Attempt</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrFailedloginattempts" name="txtlgfrFailedloginattempts" 
									maxlength="30" style="width: 115px; height: 21px;"
									 value="${requestScope.AdmTlLoginframework.LgfrFailedloginattempts}"/>
								</div>
							</td> 
							<td style="padding-left: 30px">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Min Char Password Change</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtlgfrMincharpasschange" name="txtlgfrMincharpasschange" 
									maxlength="30" style="width: 115px; height: 21px;"
									 value="${requestScope.AdmTlLoginframework.LgfrMincharpasschange}"/>
								</div>
							</td>
							</tr>
				</table>
				<div style="width:79%; padding-top:30px;">
					<table id='loginframeGrid'>
						<tr>
							<td></td>
						</tr>
					</table>
				<div id='pager'></div>
		</div>
			</div>
		</div>
		<input type="hidden" id="hdnlgfrKeyid" name="hdnlgfrKeyid" value="${requestScope.AdmTlLoginframework.lgfrKeyid}" />
		<input type="hidden" id="hdnlgfrIspassautogen" name="hdnlgfrIspassautogen" value="" /> 
			<input type="hidden" id="hdnlgfrIspolicyactive" name="hdnlgfrIspolicyactive" value="" /> 
		<input type="hidden" id="mode" name="mode"/>
</form>