<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 

  <script type="text/javascript">	
  				jQuery(document).ready(function(){
  	  	  		initialiseForm('frmPhenomena');			
  				jQuery('#submitForm').val('frmPhenomena'); // set the id of form to submit
  				fillComboBox("frmPhenomena","cmbBphmKeyid","combo_phenomena.brdn" );
  				fillComboBox("frmPhenomena","cmbBphmAssemblyid","assembly.commonFilter" );
				jQuery('#frmPhenomena .easyui-text').css('text-transform', 'uppercase');
				jQuery('#frmPhenomena .easyui-combobox').css('text-transform', 'uppercase');
  				jQuery('#frmPhenomena textarea').css('text-transform', 'uppercase');
  			});
 </script>
<form name="frmPhenomena" id="frmPhenomena" >
<div id="wrapper">	
	<div class="easyui-paddingbfpx" style="padding-top:20px; padding-left: 450px">
 	<br>
			<div ><label>Phenomena</label></div>
			<div class="easyui-paddingbfpx"><input id="cmbBphmKeyid" name="cmbBphmKeyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.bdmTlPhenomenamst.bphmKeyid}" <c:out value = "${requestScope.phenBean.disableBphmKeyid == true ? ' disabled':''}"/>/></div>
			<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Assembly</label></div>
			<div class="easyui-paddingbfpx"><input id="cmbBphmAssemblyid" name="cmbBphmAssemblyid"   type="text" class="easyui-combobox"   style="width: 300px;" value="${requestScope.bdmTlPhenomenamst.bphmAssemblyid}" <c:out value = "${requestScope.phenBean.disableBphmAssemblyid == true ? ' disabled':''}"/>/></div>
			<div class="easyui-paddingbfpx"><label>Name</label></div>
			<div class="easyui-paddingbfpx"><input id="txtBphmPhenomenaname" name="txtBphmPhenomenaname"   type="text" class="easyui-text"   style="width: 300px;" value="${requestScope.bdmTlPhenomenamst.bphmPhenomenaname}" <c:out value = "${requestScope.phenBean.disableBphmPhenomenaname == true ? ' disabled':''}"/>/></div>			
			<div class="easyui-paddingbfpx"><label>Remarks</label></div>
			<div class="easyui-paddingbfpx"><textarea id="txtBphmRemarks" name="txtBphmRemarks"  cols="34" rows="5"  <c:out value = "${requestScope.phenBean.disableBphmRemarks == true ? ' disabled':''}"/>>${requestScope.bdmTlPhenomenamst.bphmRemarks}</textarea></div>

	</div>
</div>

<input id="mode" name="mode" value="${requestScope.phenBean.formMode}" type="hidden"/>
</form> 
