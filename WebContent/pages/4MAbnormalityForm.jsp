 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 <script type="text/javascript">
  jQuery(document).ready(function(){	
  	initialiseForm('frm4MAbnormality');	
  	jQuery('#submitForm').val('frm4MAbnormality'); 
  	formatDateBox('dteQfmfDetecteddate','dd-MMM-yyyy');
	formatDateBox('dteQfmfCompleteddate','dd-MMM-yyyy');
	formatDateBox('dteQfmfTargetdate','dd-MMM-yyyy');
	fillComboBox("frm4MAbnormality","cmbQfmfResponsibility","employee.commonFilter");
	fillComboBox("frm4MAbnormality","cmbQfmfDetectedby","Combo_DetectedBy.4mfugal");
	 fillComboBox("frm4MAbnormality","cmbQfmfStatus","Combo_CompletedBy.4mfugal");	
	 fillComboBox("frm4MAbnormality","cmbQfmfPhenomenaid","Combo_Phenomena.4mfugal");
	 fillComboBox("frm4MAbnormality","cmbQfmfCompletedby","Combo_By.4mfugal");
	 fillComboBox("frm4MAbnormality","cmbQfmfAssemblyid","assembly.commonFilter");	
	 fillComboBox("frm4MAbnormality","cmbQfmfKeyid","Combo_4MAbnormality.4mfugal");	
		var qfmfmchId = jQuery("#frm4MAbnormality input[id='machine']").val();
		
    if( qfmfmchId != '' && qfmfmchId != ' ' && qfmfmchId != null )
	  	loadFunctionalLocation("4MabnmfunLocation","functionalLoc.4mfugal","abnmfunLocationValues","frm4MAbnormality","&machId="+qfmfmchId);
    else
    	loadFunctionalLocation("4MabnmfunLocation","functionalLoc.4mfugal","abnmfunLocationValues","frm4MAbnormality","");
  	jQuery('#frm4MAbnormality .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frm4MAbnormality textarea').css('text-transform', 'uppercase');
	var status=jQuery("#cmbQfmfStatus").combobox("getValue");
	
	
	if(status=='C')
		{
		
		jQuery("#completedByBlk").css('display','block');
		}
	else
		{
		jQuery("#completedByBlk").css('display','none');
		}
	var from=jQuery("#hdnfromValue").val();
	//alert(from);
	if(from=="View")
	{
	//alert('inside');
	disableForm("frm4MAbnormality");
	}
	else if(from=="Approval")
		{
		//alert('inside');
		disableField("frm4MAbnormality", "cmbQfmfStatus");
		setFieldValue("cmbQfmfStatus", "C","frm4MAbnormality");
		jQuery("#completedByBlk").css('display','block');
		var month=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		var d = new Date();
		var strDate =  d.getDate()+"-"+month[(d.getMonth())]+"-"+d.getFullYear() ;
		setFieldValue("dteQfmfCompleteddate",strDate,"frm4MAbnormality");	
		}
  	});
  function frm4MAbnormalitycmbQfmfStatus_onSelect(record)
  {
	  if(record.id=='C')
		{
			jQuery("#completedByBlk").css('display','block');
		}
		if(record.id=='P')
			{
			jQuery("#completedByBlk").css('display','none');
			}
	  }
	</script>
<form name="frm4MAbnormality" id="frm4MAbnormality">
<div id="" align="center" style="margin-top:20px;">
<table id="tblAbnormality" border="0" width="50%">
	<tr>
		<td colspan="2">
		<div  id="frm4MAbnormalityFuntKeyIds"  >
			<div style="float: left;">
			<input type="hidden" id="factory" name="cmbQfmfFactoryid" value="${requestScope.qtmTl4mfuguaimst.qfmfFactoryid}" / >
			<input type="hidden" id="section" name="cmbQfmfSectionid" value="${requestScope.qtmTl4mfuguaimst.qfmfSectionid}" / >
			<input type="hidden" id="cell" name="cmbQfmfCellid" value="${requestScope.qtmTl4mfuguaimst.qfmfCellid}" / >
			<input type="hidden" id="machine" name="cmbQfmfMachineid" value="${requestScope.qtmTl4mfuguaimst.qfmfMachineid}" / >
			
			
			</div>
			<div class="" style="width:320px;">
				<div id="4MabnmfunLocation" style="width:807px; "></div>
				
			</div>
			<div class="clear"></div>
			</div></td></tr>
			<tr><td>
		
		<div id="div4mAbnormality"><label class="mandatory-lbl">4M
		Abnormality</label></div>
		<div class=""><input id="cmbQfmfKeyid"
			name="cmbQfmfKeyid" type="text" class="easyui-combobox"
			maxlength="95" style="width: 300px;" value="${requestScope.qtmTl4mfuguaimst.qfmfKeyid}" <c:out value = "${requestScope.qtmTl4mAbnormalityBean.disabled == true ? 'disabled':''}"/> /></div>
			<div id="div4mAbnormality"><label class="mandatory-lbl">
		Abnormality</label></div>
		<div class=""><input id="cmbQfmfAssemblyid"
			name="cmbQfmfAssemblyid" type="text" class="easyui-combobox"
			maxlength="95" style="width: 300px;" value="${requestScope.qtmTl4mfuguaimst.qfmfAssemblyid}" /></div>
		<div class="easyui-paddingbfpx"><span><label
			class="mandatory-lbl">Detected On</label></span> <span style="margin-left:91px" > <label class="mandatory-lbl">Detected
		By</label></span></div>
		<div class="easyui-paddingbfpx"><input class="easyui-text"
			style="width: 120px;" id="dteQfmfDetecteddate" name="dteQfmfDetecteddate"
			value="${requestScope.qtmTl4mfuguaimst.qfmfDetecteddate}" /> <span style="margin-left:41px"><input  id="cmbQfmfDetectedby" name="cmbQfmfDetectedby" type="text" style="width:135px;" class="easyui-combobox" value="${requestScope.qtmTl4mfuguaimst.qfmfDetectedby}"  panelHeight="40px">
</span></div>
		<div class=""><label class="mandatory-lbl">Phenomena</label><br>
		</div>
		<div class=""><input id="cmbQfmfPhenomenaid" name="cmbQfmfPhenomenaid"
			type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.qtmTl4mfuguaimst.qfmfPhenomenaid}" /></div>
		<div class=""><label class="mandatory-lbl">Problem</label></div>
		<div class=""><textarea rows="2" cols="34" maxlength="175"
			id="txtQfmfProblem" name="txtQfmfProblem" style="height:60px" value="">${requestScope.qtmTl4mfuguaimst.qfmfProblem}</textarea></div>
		<div class=""><label class="mandatory-lbl">Method</label></div>
		<div class=""><textarea rows="2" cols="34" maxlength="175"
			id="txtQfmfMethod" name="txtQfmfMethod" style="" value="">${requestScope.qtmTl4mfuguaimst.qfmfMethod}</textarea></div>
		<div class=""><label>Result</label></div>
		<div class=""><textarea rows="2" cols="34" maxlength="175"
			id="txtQfmfResult" name="txtQfmfResult" style="" value="">${requestScope.qtmTl4mfuguaimst.qfmfResult}</textarea></div>
		</td>
		<td valign="top">
	
		<div style="margin-left:50px" ><label class="mandatory-lbl">Counter Measure</label></div>
		<div class="" style="margin-left:50px"><textarea rows="2" cols="34" maxlength="175"
			id="txtQfmfCountermeasure" name="txtQfmfCountermeasure" style="height:60px" >${requestScope.qtmTl4mfuguaimst.qfmfCountermeasure}</textarea></div>

		<div class="" style="margin-top:5px;margin-left:50px"  ><label class="mandatory-lbl">Responsibility</label></div>
		<div class="" style="margin-left:50px"><input id="cmbQfmfResponsibility" name="cmbQfmfResponsibility" class="easyui-combobox" tabindex="19"  style="width:300px;" value="${requestScope.qtmTl4mfuguaimst.qfmfResponsibility}" panelHeight="40px"  ></div>
		<div class="easyui-paddingbfpx"  style="margin-left:50px"><span><label
			class="mandatory-lbl">Status</label></span> <span style="margin-left:103px"> <label class="mandatory-lbl">Target
		Date</label></span></div>
		<div  style="margin-left:50px"><span  ><input id="cmbQfmfStatus" name="cmbQfmfStatus" class="easyui-combobox" tabindex="19"  style="width:125px;" value="${requestScope.qtmTl4mfuguaimst.qfmfStatus}" panelHeight="40px"  ></span>
 <span style="margin-left:17px"><input type="text"
			id="dteQfmfTargetdate" name="dteQfmfTargetdate" style="width:153px;"
			value="${requestScope.qtmTl4mfuguaimst.qfmfTargetdate}" class="easyui-text"></span></div>
			<div id="completedByBlk" style="display:none">
		<div style="margin-left:50px"><label class="mandatory-lbl">By</label></div>
		<div class="" style="margin-left:50px"><input id="cmbQfmfCompletedby" name="cmbQfmfCompletedby" type="text"
			class="easyui-combobox" maxlength="95" style="width: 300px;" value="${requestScope.qtmTl4mfuguaimst.qfmfCompletedby}" /></div>
		<div style="margin-left:50px"><label class="mandatory-lbl">On</label></div>
		<div class="" style="margin-left:50px"><input id="dteQfmfCompleteddate" name="dteQfmfCompleteddate" type="text"
			class="easyui-text" maxlength="95" style="width:200x;" value="${requestScope.qtmTl4mfuguaimst.qfmfCompleteddate}" /></div></div>
		<div class="" style="margin-left:50px"><label>Remarks</label></div>
		<div class="" style="margin-left:50px"><textarea rows="2" cols="34" maxlength="175"
			id="txtQfmfRemarks" name="txtQfmfRemarks" style="">${requestScope.qtmTl4mfuguaimst.qfmfRemarks}</textarea></div>
		</td>
	</tr>
</table>
</div>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnfromValue" name="hdnfromValue" value="${requestScope.fromStatus}"/>
</form>