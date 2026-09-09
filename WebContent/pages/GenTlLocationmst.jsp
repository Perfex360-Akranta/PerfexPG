  <%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<!--  /**-->
<!-- * Author:N Arun-->
<!-- * Created on:25.11.2011-->
<!-- */-->
  <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  			initialiseForm('frmLocation');	
  			jQuery('#submitForm').val('frmLocation'); // set the id of form to submit
  		/*	fillComboBox("frmLocation","cmbLocnKeyid","factroyCombo.commonFilter" );
  			fillComboBox("frmLocation","cmbLocnCompanyid","companyCombo.commonFilter" );
  			fillComboBox("frmLocation","cmbLocnLocationid","combo_genTlFactorymst.fact" );
  		*/
  			fillComboBox("frmLocation","cmbLocnKeyid","location.commonFilter" );
  			fillComboBox("frmLocation","cmbLocnCompanyid","companyCombo.commonFilter" );		
  			jQuery('#frmLocation .easyui-combobox').css('text-transform', 'uppercase');
  			jQuery('#frmLocation .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmLocation textarea').css('text-transform', 'uppercase');
  				
  			});
  			
		function frmLocationcmbLocnKeyid_onLoadSuccess()
		{
			
  		}
  			
		function frmLocationcmbLocnCompanyid_onLoadSuccess()
		{
			
  		}
  		function  frmLocationcmbLocnKeyid_onSelect(record)
  		{
  	  		//alert(record.id);
  			processAjaxCalls("location_recall.locn","keyId="+record.id, "frmLocation_RecallsuccessCallback","frmLocation_RecallerrorCallback");
  		}
  		function frmLocation_successsCallback(result)
		{
  			jQuery("#cmbLocnKeyid").combobox("clear");
			//alert("frmCompany");
			reloadCombo("frmLocation","cmbLocnKeyid","location.commonFilter");
		}
 		 function frmLocation_RecallsuccessCallback(result)
 		 { 
 	  		//alert("sucess");
 	  		//alert("result.locn.LocnKeyid " +result.locn.LocnKeyid);
 	  		jQuery("#cmbLocnKeyid").combobox("setValue",result.locn.LocnKeyid);
 	  		jQuery("#cmbLocnCompanyid").combobox("setValue",result.locn.LocnCompanyid);
 	  	   	jQuery("#txtLocnName").val(result.locn.LocnName);
 	  		jQuery("#txtLocnCode").val(result.locn.LocnCode);
 	  		jQuery("#txtLocnDescription").val(result.locn.LocnDescription);
 	  		//jQuery("#txtLocnName").val(result.locn.LocnName);
 		 }
 		
  		 function frmLocation_RecallerrorCallback(result)
  		 {
  			//alert("Error in callback");
  		 }
  		 function frmLocation_deleteSuccessCallback(result)
  		 {  
  			alert(result.successData.msg);
  		 } 
</script>
	<form name="frmLocation" id="frmLocation" >
<div class="" style="height: 420px;padding-top: 30px;padding-left:450">
 	
<!--  <div id="bscdiv">	-->
	<!-- ******Tabs******* -->
<!--		<div id="AsmblyTab" style="padding-left:0px;width : 1100px; ; height : 480px;margin: 12px;">-->
<!--					<div class="easyui-tabs" fit="true" plain="true" style="width:400px; height : 450px;">-->
					  
<!--	          <div class="floatleft" style="margin:250px;">-->
	          			
	          					<div  class="easyui-paddingbfpx"><label>Location</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbLocnKeyid" name="cmbLocnKeyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlLocationmst.locnKeyid}"  "${requestScope.genTlLocationmstBean.disableLocnKeyid == true ? ' disabled':''}"/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Company</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbLocnCompanyid" name="cmbLocnCompanyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlLocationmst.locnCompanyid}"  "${requestScope.genTlLocationmstBean.disableLocnCompanyid == true ? ' disabled':''}"/></div>
	          					
	          					
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Name</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtLocnName" name="txtLocnName" type="text" class="easyui-text" maxlength="95" style="width: 300px;" value="${requestScope.genTlLocationmst.locnName}"  "${requestScope.genTlLocationmstBean.disableLocnName == true ? ' disabled':''}"/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Code</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtLocnCode" name="txtLocnCode" type="text" class="easyui-text" maxlength="11" style="width: 300px;" value="${requestScope.genTlLocationmst.locnCode}"  "${requestScope.genTlLocationmstBean.disableLocnCode == true ? ' disabled':''}"/></div>
	          					<div  class="easyui-paddingbfpx"><label>Description</label></div>
	          					<div  class="easyui-paddingbfpx"><textarea id="txtLocnDescription" name="txtLocnDescription"  style="width : 300px; height : 90px;resize:none"  cols="6" rows="5" maxlength="495"  "${requestScope.genTlLocationmstBean.disableLocnDescription == true ? ' disabled':''}"/>>${requestScope.genTlLocationmst.locnDescription}</textarea></div>
	          			</div>
	          	   <input id="txtLocnFlid" type='hidden' name="txtLocnFlid" value="${requestScope.genTlLocationmst.locnFlid}" />
	          	<input type="hidden" id="mode"     value="${requestScope.genTlLocationmstBean.formMode}" />
</form> 
