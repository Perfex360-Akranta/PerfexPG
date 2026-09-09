  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--  /**-->
<!-- * Author:N Arun-->
<!-- * Created on:25.11.2011-->
<!-- */-->
  <script type="text/javascript">	

  			jQuery(document).ready(function(){	
  	  			var locnId = null;
  	  			initialiseForm('frmFactory');	
	  			jQuery('#submitForm').val('frmFactory'); // set the id of form to submit
	  		/*	fillComboBox("frmFactory","cmbFactKeyid","factroyCombo.commonFilter" );
	  			fillComboBox("frmFactory","cmbFactCompanyid","companyCombo.commonFilter" );
	  			fillComboBox("frmFactory","cmbFactLocationid","combo_genTlFactorymst.fact" );
	  		*/	
	  			fillComboBox("frmFactory","cmbFactKeyid","factroyCombo.commonFilter" );
	  			fillComboBox("frmFactory","cmbFactCompanyid","companyCombo.commonFilter" );
	  			jQuery('#frmFactory .easyui-combobox').css('text-transform', 'uppercase');
	  			jQuery('#frmFactory .easyui-text').css('text-transform', 'uppercase');
	  			jQuery('#frmFactory textarea').css('text-transform', 'uppercase');
  				
  			});
  			
  			function frmFactorycmbFactKeyid_onLoadSuccess()
  			{
  				//jQuery("#cmbFactCompanyid").combobox('clear');
  				
  				
  	  		}
  			function frmFactorycmbFactCompanyid_onLoadSuccess()
  			{
  				//jQuery("#cmbFactLocationid").combobox('clear');
  				
  				fillComboBox("frmFactory","cmbFactLocationid","location.commonFilter" );
  	  		}
  			function frmFactorycmbFactLocationid_onLoadSuccess()
  			{
  				
  	  		}
  			
  			function  frmFactorycmbFactCompanyid_onSelect(record)
  			{
  				jQuery("#cmbFactLocationid").combobox("clear");
  				reloadCombo("frmFactory","cmbFactLocationid","combo_factorymst.fact?compId="+record.id);
  				
  	  		}
  			function frmFactorycmbFactLocationid_onSelect(record)
  			{ //alert("record.id" +record.id);
  				//reloadCombo("frmFactory","cmbFactCompanyid","companyCombo.commonFilter?locnId="+record.id );	
  				
  				filllocationHierarchy("locationHierarchy.commonFilter",record.id,"cmbFactCompanyid");
  	  		}
  		
	  		function  frmFactorycmbFactKeyid_onSelect(record)
	  		{
	  	  		//alert(record.id);
	  			processAjaxCalls("factory_recall.fact","keyId="+record.id, "frmFactory_RecallsuccessCallback","frmFactory_RecallerrorCallback");
	  			//alert("saveForm" );
	  		}
	  		function frmFactory_successsCallback(result)
  			{
	  			//alert("frmFactory");
	  			jQuery("#cmbFactKeyid").combobox("clear");
  				reloadCombo("frmFactory","cmbFactKeyid","factroyCombo.commonFilter");
  			}	
	  		
	 		 function frmFactory_RecallsuccessCallback(result)
	 		 { 
	 	  		//alert("sucess");
	 	  		
	 	  		jQuery("#cmbFactKeyid").combobox("setValue",result.fact.FactKeyid);
	 	  		jQuery("#cmbFactCompanyid").combobox("setValue",result.fact.FactCompanyid);
	 	  	    jQuery("#cmbFactLocationid").combobox("setValue",result.fact.FactLocationid);
	 	  		jQuery("#txtFactName").val(result.fact.FactName);
	 	  		jQuery("#txtFactCode").val(result.fact.FactCode);
	 	  		jQuery("#txtFactAddress").val(result.fact.FactAddress);
	 		 }
	 		
	  		 function frmFactory_RecallerrorCallback(result)
	  		 {
	  			//alert("Error in callback");
	  		 }
	  		 function frmFactory_deleteSuccessCallback(result)
	  		 {  
	  			alert(result.successData.msg);
	  		 }
</script>
	<form name="frmFactory" id="frmFactory" >
<div class="" style="height: 420px;padding-top: 30px;padding-left:450">
 	
<!--  <div id="bscdiv">	-->
	<!-- ******Tabs******* -->
<!--		<div id="AsmblyTab" style="padding-left:0px;width : 1100px; ; height : 480px;margin: 12px;">-->
<!--					<div class="easyui-tabs" fit="true" plain="true" style="width:400px; height : 450px;">-->
					  
<!--	          <div class="floatleft" style="margin:250px;">-->
	          			
	          					<div ><label>Unit</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbFactKeyid" name="cmbFactKeyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlFactorymst.factKeyid}" <c:out value = "${requestScope.genTlFactorymstBean.disableFactKeyid == true ? ' disabled':''}"/>/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Company</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbFactCompanyid" name="cmbFactCompanyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlFactorymst.factCompanyid}" <c:out value = "${requestScope.genTlFactorymstBean.disableFactCompanyid == true ? ' disabled':''}"/>/></div>
	          					<div  class="easyui-paddingbfpx"><label>Location</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbFactLocationid" name="cmbFactLocationid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlFactorymst.factLocationid}" <c:out value = "${requestScope.genTlFactorymstBean.disableFactLocationid == true ? ' disabled':''}"/>/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Name</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtFactName" name="txtFactName" type="text" class="easyui-text"  maxlength="95" style="width: 300px;" value="${requestScope.genTlFactorymst.factName}" <c:out value = "${requestScope.genTlFactorymstBean.disableFactName == true ? ' disabled':''}"/>/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Code</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtFactCode" name="txtFactCode" type="text" class="easyui-text"  maxlength="11" style="width: 300px;" value="${requestScope.genTlFactorymst.factCode}" <c:out value = "${requestScope.genTlFactorymstBean.disableFactCode == true ? ' disabled':''}"/>/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Address</label></div>
	          					<div  class="easyui-paddingbfpx"><textarea id="txtFactAddress" name="txtFactAddress"  style="width : 300px; height : 90px;resize:none"  cols="6" rows="5" maxlength="255" <c:out value = "${requestScope.genTlFactorymstBean.disableFactAddress == true ? ' disabled':''}"/>>${requestScope.genTlFactorymst.factAddress}</textarea></div>
	          			</div>
	          	<input type="hidden" id="mode" value="${requestScope.genTlFactorymstBean.formMode}" />
					</form> 
