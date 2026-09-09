  <%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<!--  /**-->
<!-- * Author:N Arun-->
<!-- * Created on:25.11.2011-->
<!-- */-->
  <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  			initialiseForm('frmCompany');	
  			jQuery('#submitForm').val('frmCompany'); // set the id of form to submit
  			fillComboBox("frmCompany","cmbCompKeyid","companyCombo.commonFilter" );
  			jQuery('#frmCompany .easyui-combobox').css('text-transform', 'uppercase');
  			jQuery('#frmCompany .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmCompany textarea').css('text-transform', 'uppercase');
  			
  				
  		});
  		function frmCompanycmbCompKeyid_onLoadSuccess()
  		{
  	  	}
  			
  		function  frmCompanycmbCompKeyid_onSelect(record)
  		{
  	  		//alert(record.id);
  			processAjaxCalls("company_recall.comp","keyId="+record.id, "frmCompany_RecallsuccessCallback","frmCompany_RecallerrorCallback");
  		}
  		function frmCompany_successsCallback(result)
			{
  				jQuery("#cmbCompKeyid").combobox("clear");
  				//alert("frmCompany");
				reloadCombo("frmCompany","cmbCompKeyid","companyCombo.commonFilter");
			}	
  		
 		 function frmCompany_RecallsuccessCallback(result)
 		 { 
 	  		//alert("sucess");
 	  		jQuery("#cmbCompKeyid").combobox("setValue",result.compdata.CompKeyid);
 	  		  	   
 	  		jQuery("#txtCompName").val(result.compdata.CompName);
 	  		jQuery("#txtCompCode").val(result.compdata.CompCode);
 	  		jQuery("#txtCompAddress").val(result.compdata.CompAddress);
 		 }
 		
  		 function frmCompany_RecallerrorCallback(result)
  		 {
  			//alert("Error in callback");
  		 }
  		 function frmCompany_deleteSuccessCallback(result)
  		 {  
  			alert(result.successData.msg);
  		 }
  		 
</script>
	<form name="frmCompany" id="frmCompany" >
<div class="" style="height: 400px;padding-top: 50px;padding-left:480">
 
  								
	          					<div  class="easyui-paddingbfpx"><label >Company</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="cmbCompKeyid" name="cmbCompKeyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlCompanymst.compKeyid}"  "${requestScope.genTlCompanymstBean.disableCompKeyid == true ? ' disabled':''}"/></div>
	
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Name</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtCompName" name="txtCompName" type="text" class="easyui-text" maxlength="95"  style="width: 300px;" value="${requestScope.genTlCompanymst.compName}"  "${requestScope.genTlCompanymstBean.disableCompName == true ? ' disabled':''}"/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Code</label></div>
	          					<div  class="easyui-paddingbfpx"><input id="txtCompCode" name="txtCompCode" type="text" class="easyui-text" maxlength="11" style="width: 300px;" value="${requestScope.genTlCompanymst.compCode}"  "${requestScope.genTlCompanymstBean.disableCompCode == true ? ' disabled':''}"/></div>
	          					<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Address</label></div>
	          					<div  class="easyui-paddingbfpx"><textarea id="txtCompAddress" name="txtCompAddress"  style="width : 300px; height : 90px;resize:none"  cols="6" rows="5" maxlength="295"  "${requestScope.genTlCompanymstBean.disableCompAddress == true ? ' disabled':''}"/>>${requestScope.genTlCompanymst.compAddress}</textarea></div>
	          			</div>
	          	</div>
	          	
	          	<input type="hidden" id="mode" value="${requestScope.genTlCompanymstBean.formMode}" />
					</form> 
