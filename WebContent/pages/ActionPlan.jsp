<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 <script type="text/javascript">
 jQuery(document).ready(function()
	{
	  	initialiseForm('frmActionPlan');	
	  	jQuery('#submitForm').val('frmActionPlan'); 
	  	var apLcnId = jQuery('#frmActionPlan input[id="location"]').val();
	  	var mchId=jQuery('#frmActionPlan input[id="machine"]').val();
		funLocn(apLcnId,mchId);
	  	formatDateBox('dteAplmWhendate','dd-MMM-yyyy');
		formatDateBox('dteAplmTargetdate','dd-MMM-yyyy');
		formatDateBox('dteAplmCompleteddate','dd-MMM-yyyy');
		formatDateBox('dteAplmAllotedddate','dd-MMM-yyyy');
		formatDateBox('dteAplmApproveddate','dd-MMM-yyyy');
		fillComboBox("frmActionPlan","cmbAplmAssemblyid","assembly.commonFilter");
		fillComboBox("frmActionPlan","cmbAplmAllottedby","employee.commonFilter");
		fillComboBox("frmActionPlan","cmbAplmAllotedto","employee.commonFilter");	
		fillComboBox("frmActionPlan","cmbAplmSparesid","spareCombo.commonFilter");
		fillComboBox("frmActionPlan","cmbAplmStatus","Combo_Status.ap");
		fillComboBox("frmActionPlan","cmbAplmPhenomenaid","phenomena.ap");	
		fillComboBox("frmActionPlan","cmbAplmCompletdby","employee.commonFilter");
		fillComboBox("frmActionPlan","cmbAplmCauseid","cause.commonFilter");
		fillComboBox("frmActionPlan","cmbAplmWhichmachine","machineCombo.commonFilter");	
		fillComboBox("frmActionPlan","cmbAplmWhoresppersonid","employee.commonFilter");	
		fillComboBox("frmActionPlan","cmbAplmApprovedby","employee.commonFilter");	
	 	jQuery('#frmActionPlan .easyui-text').css('text-transform', 'uppercase');
		jQuery('#frmActionPlan textarea').css('text-transform', 'uppercase');
		
		var status=jQuery("#status").val();
		if(status=='C')
		{
			 	jQuery('#chkIsApprovedY').attr('checked', true);
			 	jQuery('#chkIsApprovedN').attr('checked', false);
				jQuery("#compltedDetails").css('display','block');
				deatails();
		}
		if(status=='P'||status=='A'||status=='R'||status==null)
			jQuery("#compltedDetails").css('display','none');
		var user=jQuery("#hdnUser").val();
		var date=jQuery("#hdnDate").val();
		var from =jQuery("#hdnfromLink").val();
		//alert(from);
		if((from!="view")||(from!="approval")||(from!="completion") )
		{
			jQuery("#cmbAplmStatus").combobox('setValue','P');
		}
		
		if((from=="view")||(from=="approval")||(from=="completion")||(from=="frmMenu") )
		{
			jQuery("#tblActionPlan").css("margin-left","220px");
			jQuery("#ApfunLocation").css("margin-left","220px");
			jQuery("#ApfunLocation").css("width","1030px");
			jQuery("#tblActionPlan").attr("width","85%");
			jQuery("#leftTd").attr("width","35%");
			jQuery("#rightTd").attr("width","35%");	
		}
		else
		{
			jQuery("#tblActionPlan").css("margin-left","90px");
			jQuery("#ApfunLocation").css("margin-left","90px");
			jQuery("#ApfunLocation").css("width","920px");
			jQuery("#tblActionPlan").attr("width","85%");
			jQuery("#leftTd").attr("width","35%");
			jQuery("#rightTd").attr("width","35%");	
		}
		if(from=="view")
		{
				disableForm("frmActionPlan");
				jQuery("#approvedDetails").css('display','block');
				if(status=='R')
					{
						jQuery("#header").text("Cancelled Details");
						jQuery("#dteAplmTargetdate").datebox('setValue','');
						jQuery("#cmbAplmStatus").combobox('setValue','CANCELLED');
					}
				else if(status=='A')
					jQuery("#cmbAplmStatus").combobox('setValue','APPROVED');
					jQuery("#approved").css('display','block');	
		}
		else if(from=="approval")
		{
				disableForm("frmActionPlan");
				jQuery("#approvedDetails").css('display','block');
				enableFields('chkIsApprovedY');
				enableFields('chkIsApprovedN');
				enableFields('cmbAplmApprovedby');
				enableFields('dteAplmApproveddate');
				enableFields('txtAplmAppremarks');
				jQuery("#approved").css('display','block');
				if(jQuery("#hdnApprovedBy").val()==null||jQuery("#hdnApprovedBy").val()==" "||jQuery("#hdnApprovedBy").val()==''){
					setComboValueSilent("cmbAplmApprovedby", user);
					//jQuery("#cmbAplmApprovedby").combobox("setValue",user);
				}
					
				
		}
		else if(from=="completion")
		{
				disableForm("frmActionPlan");
				jQuery("#approvedDetails").css('display','block');
				jQuery("#approved").css('display','block');
				enableFields('cmbAplmCompletdby');
				enableFields('dteAplmCompleteddate');
				enableFields('txtAplmCountermeasure');
				enableFields('txtAplmRemarks');
				deatails();
				
		}
		settingValues();
		var  fromLink=jQuery("#hdnfromLink").val();
		/*if(fromLink=="KMI")
		{
			jQuery("#tblActionPlan").css("margin-left","90px");
			jQuery("#ApfunLocation").css("margin-left","90px");
			jQuery("#ApfunLocation").css("width","920px");
			jQuery("#tblActionPlan").attr("width","85%");
			jQuery("#leftTd").attr("width","35%");
			jQuery("#rightTd").attr("width","35%");			
		}
		else
		{
			jQuery("#tblActionPlan").css("margin-left","90px");
			jQuery("#ApfunLocation").css("margin-left","90px");
			jQuery("#ApfunLocation").css("width","920px");
			jQuery("#tblActionPlan").attr("width","85%");
			jQuery("#leftTd").attr("width","35%");
			jQuery("#rightTd").attr("width","35%");	
			
		}*/
		
	});
	function settingValues() 
	{
		var user=jQuery("#hdnUser").val();
		var date=jQuery("#hdnDate").val();
		if(jQuery("#hdnWho").val()==null||jQuery("#hdnWho").val()==" "||jQuery("#hdnWho").val()==''){
			setComboValueSilent("cmbAplmWhoresppersonid", user);
			//jQuery("#cmbAplmWhoresppersonid").combobox("setValue",user);
		}
			
		if(jQuery("#hdnAllotedBy").val()==null||jQuery("#hdnAllotedBy").val()==" "||jQuery("#hdnAllotedBy").val()==''){
			setComboValueSilent("cmbAplmAllottedby", user);
			//jQuery("#cmbAplmAllottedby").combobox("setValue",user);
		}
			
		if(jQuery("#hdnWhen").val()==null||jQuery("#hdnWhen").val()==" "||jQuery("#hdnWhen").val()=='')
			jQuery("#dteAplmWhendate").datebox('setValue', date);
		if(jQuery("#hdnAllottedDate").val()==null||jQuery("#hdnAllottedDate").val()==" "||jQuery("#hdnAllottedDate").val()=='')
			jQuery("#dteAplmAllotedddate").datebox('setValue', date);
		else if((jQuery("#hdnAllottedDate").val()=="01-Jan-1801")||(jQuery("#hdnAllottedDate").val()=="31-Dec-2100"))
			jQuery("#dteAplmAllotedddate").datebox('setValue', '');
		if(jQuery("#hdnTargetDate").val()==null||jQuery("#hdnTargetDate").val()==" "||jQuery("#hdnTargetDate").val()=='')
			jQuery("#dteAplmTargetdate").datebox('setValue', date);
		else if((jQuery("#hdnTargetDate").val()=="01-Jan-1801")||(jQuery("#hdnTargetDate").val()=="31-Dec-2100"))
			jQuery("#dteAplmTargetdate").datebox('setValue', '');
		}
	function deatails()
		{
			var user=jQuery("#hdnUser").val();
			var date=jQuery("#hdnDate").val();
			if(jQuery("#hdnApprovedBy").val()==null||jQuery("#hdnApprovedBy").val()==" "||jQuery("#hdnApprovedBy").val()==''){
				setComboValueSilent("cmbAplmApprovedby", user);
				//jQuery("#cmbAplmApprovedby").combobox("setValue",user);
			}
				
			if(jQuery("#hdnCompDate").val()==null||jQuery("#hdnCompDate").val()==" "||jQuery("#hdnCompDate").val()=='')
			  	jQuery("#dteAplmCompleteddate").datebox('setValue', date);
			else if((jQuery("#hdnCompDate").val()=="01-Jan-1801")||(jQuery("#hdnCompDate").val()=="31-Dec-2100"))
				jQuery("#dteAplmCompleteddate").datebox('setValue', '');
			if(jQuery("#hdnCompletedBy").val()==null||jQuery("#hdnCompletedBy").val()==" "||jQuery("#hdnCompletedBy").val()=='')
				{
				setComboValueSilent("cmbAplmCompletdby", user);
				//jQuery("#cmbAplmCompletdby").combobox("setValue",user);
				}
				
			}
	function funLocn(lcnID,mchId)
	 {
		if(mchId.trim().length>0 && mchId!="NULL" && mchId!=null && mchId!=''&&mchId!=' ')
			loadFunctionalLocation("ApfunLocation", "functionalLoc.ap","ApfunLocationValues", "frmActionPlan", "&machId=" + mchId);
		else if (lcnID != '' && lcnID != ' ' && lcnID != null)
			loadFunctionalLocation("ApfunLocation", "functionalLoc.ap","ApfunLocationValues", "frmActionPlan", "&locnId=" + lcnID);
		else
			loadFunctionalLocation("ApfunLocation", "functionalLoc.ap","ApfunLocationValues", "frmActionPlan", "");
	}
	function frmActionPlan_FuntLocHierarchy_SuccessCallBack(keyIds) {
		//alert(keyIds.machId);
		if (keyIds.machId != null && keyIds.machId != ""
				&& keyIds.machId != " " && keyIds.machId != undefined
				&& keyIds.machId != 'null')
			setFieldValue('cmbAplmWhichmachine', keyIds.machId);
		else
			jQuery("#cmbAplmWhichmachine").combobox('clear');
		
	}
	jQuery("#chkIsHdPosY").click(function() {
		if (jQuery("#chkIsHdPosY").is(':checked'))
			jQuery('#chkIsHdPosN').attr('checked', false);
		else
			jQuery('#chkIsHdPosN').attr('checked', true);
	});
	jQuery("#chkIsHdPosN").click(function() {
		if (jQuery("#chkIsHdPosN").is(':checked'))
			jQuery('#chkIsHdPosY').attr('checked', false);
		else
			jQuery('#chkIsHdPosY').attr('checked', true);
	});
	jQuery("#chkIsApprovedY").click(function() {
		if (jQuery("#chkIsApprovedY").is(':checked'))
			jQuery('#chkIsApprovedN').attr('checked', false);
		else
			jQuery('#chkIsApprovedN').attr('checked', true);
	});
	jQuery("#chkIsApprovedN").click(function() {
		if (jQuery("#chkIsApprovedN").is(':checked'))
			jQuery('#chkIsApprovedY').attr('checked', false);
		else
			jQuery('#chkIsApprovedY').attr('checked', true);
	});
	function frmActionPlancmbAplmStatus_onSelect(record) {
		if (record.id == 'C') {
			jQuery("#compltedDetails").css('display', 'block');
			var date = jQuery("#hdnDate").val();
			var user = jQuery("#hdnUser").val();
			deatails();

		} else if (record.id == 'P' || record.id == 'A' || record.id == 'R')
			jQuery("#compltedDetails").css('display', 'none');

	}
	function frmActionPlancmbAplmAssemblyid_onSelect(record) {
		var cmbAssmbid = record.id;
		reloadCombo("frmActionPlan", "cmbAplmPhenomenaid",
				"phenomena.ap?&cmbAssmbid=" + cmbAssmbid);
		jQuery("#cmbAplmSparesid").combobox('clear');
		reloadCombo("frmActionPlan", "cmbAplmSparesid",
				"spareCombo.commonFilter?cmbAssmbid=" + cmbAssmbid);
	}
	function frmActionPlancmbAplmWhichmachine_onSelect(record) {
		
		var cmbMchid = record.id;
		jQuery("#cmbAplmAssemblyid").combobox('clear');
		reloadCombo("frmActionPlan", "cmbAplmAssemblyid",
				"assembly.commonFilter?machineId=" + cmbMchid);
		jQuery("#cmbAplmSparesid").combobox('clear');
		reloadCombo("frmActionPlan", "cmbAplmSparesid",
				"spareCombo.commonFilter?machineId=" + cmbMchid);
		funLocn("",cmbMchid);
	}
	function frmActionPlancmbAplmPhenomenaid_onSelect(record) {
		var phenId = record.id;
		
		reloadCombo("frmActionPlan", "cmbAplmCauseid",
				"cause.commonFilter?&Phenomena=" + phenId);
	}
	numericTextBox('txtAplmHowmany');
	numericTextBox('txtAplmHowmuchvalue');
	function frmActionPlan_successsCallback(result) {
		// closePopUpDialoge('divShowActionPlanSave');
		// refresh();
	}
	function frmActionPlan_deleteSuccessCallback(result) {
		// closePopUpDialoge('divShowActionPlanSave');
		// refresh();
	}
	function dteAplmAllotedddate_onSelect(date)
  	{		
		var currentDate = getServerDateTime();
		if( date > currentDate)	
		{	
			jQuery('#dteAplmAllotedddate').datebox('clear');
			showValidationErrorMsg('dteAplmAllotedddate','Should Not Exceed Current Date');	
		}	
	 }
	function dteAplmCompleteddate_onSelect(date)
  	{		
		var currentDate = getServerDateTime();
		if( date > currentDate)	
		{	
			jQuery('#dteAplmCompleteddate').datebox('clear');
			showValidationErrorMsg('dteAplmCompleteddate','Should Not Exceed Current Date');	
		}	
	 }
	/*if(screen.width>1280 && screen.width<=1366)
	{
		jQuery("#ApfunLocation").css("width","1048px");
		jQuery("#ApfunLocation").css("margin-left","180px");
	}*/
</script>

<form name="frmActionPlan" id="frmActionPlan" >

		<div  id="frmActionPlanFuntKeyIds"  >
			<input type="hidden" id="location" name="cmbAplmLocationid" value="${requestScope.genTlActionplanmst.aplmLocationid}"/>
			<input type="hidden" id="factory" name="cmbAplmFactoryid" value="${requestScope.genTlActionplanmst.aplmFactoryid}" / >
			<input type="hidden" id="section" name="cmbAplmSectionid" value="${requestScope.genTlActionplanmst.aplmSectionid}" / >
			<input type="hidden" id="cell" name="cmbAplmWherecellid" value="${requestScope.genTlActionplanmst.aplmWherecellid}" / >
			<input type="hidden" id="machine" name="cmbAplmWhichmachine" value="${requestScope.genTlActionplanmst.aplmWhichmachine}" / >
			<div id="ApfunLocation" style="width:994px;margin-left:170px;"></div>
			<div ></div>
		</div>
		<table  id="tblActionPlan"  align="center">
			<tr>
				<td id="leftTd" width="33%">
				</td>
				<td id="rightTd" width="33%" >
					<div class="sub-header" style="text-align:left;width:345px">
						<span style="position:absolute;">How</span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label>Equipment</label>
				</td>
				<td>
					<label>How To Do</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="cmbAplmWhichmachine" name="cmbAplmWhichmachine" type="text" class="easyui-combobox" style="width: 350px;" value="${requestScope.genTlActionplanmst.aplmWhichmachine}" />
				</td>
				<td rowspan="5">
					<textarea rows="5" cols="34"  id="txtAplmHowexplanation" name="txtAplmHowexplanation" style="width: 350px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<label>Assembly</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="cmbAplmAssemblyid" name="cmbAplmAssemblyid" type="text" class="easyui-combobox" style="width: 350px;" value="${requestScope.genTlActionplanmst.aplmAssemblyid}" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Spares</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="cmbAplmSparesid" name="cmbAplmSparesid" type="text" class="easyui-combobox" style="width: 350px;" value="${requestScope.genTlActionplanmst.aplmSparesid}" />
				</td>
			</tr>
			<tr>
				<td>
					<label class="mandatory-lbl">Action Plan</label>
				</td>
				<td>
					<label>Bench Mark</label>
				</td>
			</tr>
			<tr>
				<td rowspan="6">
					<textarea rows="5" cols="34"  id="txtAplmWhatprobdesc" name="txtAplmWhatprobdesc" style="width: 350px;">${requestScope.genTlActionplanmst.aplmWhatprobdesc}</textarea>
				</td>
			</tr>
			
			<tr>
				<td>
					<input id="txtAplmBenchmark" name="txtAplmBenchmark" type="text" class="easyui-text"  style="width:350px;" value="${requestScope.genTlActionplanmst.aplmBenchmark}" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Target</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="txtAplmTarget" name="txtAplmTarget" type="text" class="easyui-text" maxlength="95" style="width:350px;" value="${requestScope.genTlActionplanmst.aplmTarget}" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Saving</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="txtAplmSavings" name="txtAplmSavings" type="text" class="easyui-text" maxlength="95" style="width:350px;" value="${requestScope.genTlActionplanmst.aplmSavings}" />
				</td>
				
			</tr>
			<tr>
				<td>
					<label class="mandatory-lbl">When</label>
				</td>
				
				
			</tr>
			<tr>
				<td>
					<input id="dteAplmWhendate" name="dteAplmWhendate" type="text" class="easyui-combobox" style="width: 350px;" value="${requestScope.genTlActionplanmst.aplmWhendate}" />
				</td>
				<td >
					<span>
						<label>HD Required</label>
					</span>
					<span>
						<input id="chkIsHdPosY" name="chkAplmIshdpossible" type="checkbox" />
						<label>Yes</label>
						<input id="chkIsHdPosN" name="ch1AplmIshdpossible" type="checkbox"  checked="checked"/>
						<label>No</label>
					</span>
					<span style="padding-left:80px;">
						<input type="button" value=" HD View" style="height:21px;width:100px"  class=" easyui-button" id="btnprogRole" name="btnprogRole"/>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="mandatory-lbl">Who</label>
				</td>
				<td>
					<span>
						<label >How Many</label>
					</span>
					<span style="padding-left:128px;">
						<label >How Much</label>
					</span>
				</td>
				
			</tr>
			<tr>
				<td>
					<input id="cmbAplmWhoresppersonid" name="cmbAplmWhoresppersonid" type="text" class="easyui-text" maxlength="95" style="width:350px;" value="${requestScope.genTlActionplanmst.aplmWhoresppersonid}" />
				</td>
				<td>
					<input id="txtAplmHowmany" name="txtAplmHowmany" type="text" class="easyui-text" maxlength="95" style="width:160px;text-align:right;" value="${requestScope.genTlActionplanmst.aplmHowmany}" />
					<span style="padding-left:25px;"><input id="txtAplmHowmuchvalue" name="txtAplmHowmuchvalue" type="text" class="easyui-text" maxlength="95" style="width:160px;text-align:right;" value="${requestScope.genTlActionplanmst.aplmHowmuchvalue}" /></span>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sub-header" style="text-align:left;width:345px">
						<span style="position:absolute;">What</span>
					</div>
				</td>
				<td>
					<div class="sub-header" style="text-align:left;width:345px">
						<span style="position:absolute;">Allotted Details</span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="mandatory-lbl">Activity</label>
				</td>
				<td>
					<label >Created By</label>
				</td>
			</tr>
			<tr>
				<td rowspan="5">
					<textarea rows="5" cols="34"  id="txtAplmActivitydesc" name="txtAplmActivitydesc" style="width:350px;">${requestScope.genTlActionplanmst.aplmActivitydesc}</textarea>
				</td>
				<td>
					<input id="cmbAplmAllottedby" name="cmbAplmAllottedby" type="text" class="easyui-combobox" style="width:350px;" value="${requestScope.genTlActionplanmst.aplmAllottedby}" />
				</td>
			</tr>
			<tr>
				<td>
					<span>
						<label>Allotted To</label>
					</span>
					<span style="margin-left:113px;">
						<label>Date</label>
					</span>
				</td>	
			</tr>
			<tr>
				<td>
					<span>
						<input id="cmbAplmAllotedto" name="cmbAplmAllotedto" type="text" class="easyui-combobox" style="width: 170px;" value="${requestScope.genTlActionplanmst.aplmAllotedto}" />
					</span>
					<span>
						<input id="dteAplmAllotedddate" name="dteAplmAllotedddate" type="text" class="easyui-combobox" style="width: 175px;" value="${requestScope.genTlActionplanmst.aplmAllotedddate}" />
					</span>
				</td>	
			</tr>
			<tr>
				<td>
					<span>
						<label>Status</label>
					</span>
					<span style="margin-left:134px;">
						<label>Target Date</label>
					</span>
				</td>	
			</tr>
			<tr>
				<td>
					<span>
						<input id="cmbAplmStatus" name="cmbAplmStatus" type="text" class="easyui-combobox" style="width: 170px;" value="${requestScope.genTlActionplanmst.aplmStatus}" />
					</span>
					<span>
						<input id="dteAplmTargetdate" name="dteAplmTargetdate" type="text" class="easyui-combobox" style="width: 175px;" value="${requestScope.genTlActionplanmst. aplmTargetdate}" />
					</span>
				</td>	
			</tr>
			<tr>
				<td valign="top">
					<div id="WhyDetails" >
						<div class="sub-header" style="text-align:left;width:345px">
							<span style="position:absolute;">Why</span>
						</div>
						<div>
							<label>Phenomena</label>
						</div>
						<div>
							<input id="cmbAplmPhenomenaid" name="cmbAplmPhenomenaid" type="text" class="easyui-text" maxlength="95" style="width:350px;" value="${requestScope.genTlActionplanmst.aplmPhenomenaid}" />
						</div>
						<div>
							<label>Cause</label>
						</div>
						<div>
							<input id="cmbAplmCauseid" name="cmbAplmCauseid" type="text" class="easyui-combobox" style="width: 350px;" value="${requestScope.genTlActionplanmst.aplmCauseid}" />
						</div>
						<div>
							<label class="mandatory-lbl">Reason</label>
						</div>
						<div>
							<textarea rows="1" cols="34"  id="txtAplmReason" name="txtAplmReason" style="width: 350px;">${requestScope.genTlActionplanmst.aplmReason}</textarea>
						</div>
						</div>
				</td>
				<td valign="top" height="0">
				
				<div id="approvedDetails" style="display:none">
					<div class="sub-header" style="text-align:left;width:345px">
						<span id="header" style="position:absolute;">Approved Details</span>
					</div>
					<span>
						<label>Approved</label>
					</span>
					<span>
						<input id="chkIsApprovedY" name="chkAplmStatus" type="checkbox" />
						<label>Yes</label>
						<input id="chkIsApprovedN" name="ch1AplmStatus" type="checkbox"  checked="checked"/>
						<label>No</label>
					</span>
				</div>
				<div id="approved" style="display:none"  >
					
					<div>
						<label class="mandatory-lbl"> By</label>
						<span style="margin-left:156px;">
							<label class="mandatory-lbl"> Date</label>
						</span>
					</div>
					<div  >
						 <span><input id="cmbAplmApprovedby" name="cmbAplmApprovedby" type="text" class="easyui-text" maxlength="95" style="width:170px;" value="${requestScope.genTlActionplanmst.aplmApprovedby}" /></span> 
						
						 <span><input id="dteAplmApproveddate" name="dteAplmApproveddate" type="text" class="easyui-text" maxlength="95" style="width:175px;" value="${requestScope.genTlActionplanmst.aplmApproveddate}" /></span> 
					</div>
					<div>
						<label> Remarks</label>
				    </div>
				   <div>
						 <textarea rows="1" cols="34"  id="txtAplmAppremarks" name="txtAplmAppremarks"  maxlength="500" style="width: 350px;">${requestScope.genTlActionplanmst.aplmAppremarks}</textarea>
					</div>
					</div>
				<div id="compltedDetails" style="display:none">
					<div class="sub-header" style="text-align:left;width:345px">
						<span style="position:absolute;">Completed Details</span>
					</div>
					<div>
						<label class="mandatory-lbl">Completed By</label>
						<span style="margin-left:93px;">
							<label class="mandatory-lbl">Completed Date</label>
						</span>
					</div>
					<div>
						<span><input id="cmbAplmCompletdby" name="cmbAplmCompletdby" type="text" class="easyui-text" maxlength="95" style="width:170px;" value="${requestScope.genTlActionplanmst.aplmCompletdby}" /></span>
						<span><input id="dteAplmCompleteddate" name="dteAplmCompleteddate" type="text" class="easyui-text" maxlength="95" style="width:170px;" value="${requestScope.genTlActionplanmst.aplmCompleteddate}" /></span>
					</div>
					<div>
						<label>Counter Measure</label>
				    </div>
				   <div>
						<textarea rows="1" cols="34"  id="txtAplmCountermeasure" name="txtAplmCountermeasure" style="width: 350px;">${requestScope.genTlActionplanmst.aplmCountermeasure}</textarea>
					</div>
					<div>
						<label>Remarks</label>
				    </div>
				    <div>
						<textarea rows="1" cols="34"  id="txtAplmRemarks" name="txtAplmRemarks" style="width: 350px;">${requestScope.genTlActionplanmst.aplmRemarks}</textarea>
					</div>
				</div>	
				</td>
			</tr>
			
			
		</table>
		<input type="hidden" id="mode" name="mode"/>
		<input type="hidden" id="status" value="${requestScope.genTlActionplanmst.aplmStatus }"/>
		<input type="hidden" id="hdnFrm" value="${requestScope.from }"/> 
		<input type="hidden" id="hdnWhen" value="${requestScope.genTlActionplanmst.aplmWhendate}"/>
		<input type="hidden" id="hdnWho" value="${requestScope.genTlActionplanmst.aplmWhoresppersonid}"/>
		<input type="hidden" id="hdnAllotedBy" value="${requestScope.genTlActionplanmst.aplmAllottedby}"/>
		<input type="hidden" id="hdnAllottedDate" value="${requestScope.genTlActionplanmst.aplmAllotedddate}"/>
		<input type="hidden" id="hdnTargetDate" value="${requestScope.genTlActionplanmst.aplmTarget}"/>
		<input type="hidden" id="hdnCompletedBy" value="${requestScope.genTlActionplanmst.aplmCompletdby}"/>
		<input type="hidden" id="hdnCompDate"	value="${requestScope.genTlActionplanmst.aplmCompleteddate}"/>	
		<input type="hidden" id="hdnApprovedBy" value="${requestScope.genTlActionplanmst.aplmApprovedby}"/>
		<input type="hidden" id="hdnfromLink" value="${requestScope.fromLink}"/>	
		<input type="hidden" id="hdnKaiKeyId" value="${requestScope.kaiKeyId}"/>	
		<input type="hidden" id="hdnhowmuch" value="${requestScope.genTlActionplanmst.aplmHowmuchvalue}"/>
		<input type="hidden" id="hdnhowmany"	value="${requestScope.genTlActionplanmst.aplmHowmany}"/>
		<input type="hidden" id="hdnUser" value="${requestScope.user}"/>
		<input type="hidden" id="hdnDate" value="${requestScope.Date}"/>
		<input type="hidden" id="hdnAplmRefdoctype" name="hdnAplmRefdoctype" value="${requestScope.refDocType}"/>
		<input type="hidden" id="hdnAplmRefdocid" name="hdnAplmRefdocid" value="${requestScope.refDocId}"/>
</form>