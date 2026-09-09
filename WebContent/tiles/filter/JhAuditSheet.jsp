<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmAudit');

	initialiseForm('frmAudit');	
	//formatDateBox('dteAuditFromDate','dd-MMM-yyyy');
	//formatDateBox('dteAuditToDate','dd-MMM-yyyy');
	if(  !jQuery('#cmbAuditorName').is(':disabled') )
		fillComboBox("frmAudit","cmbAuditorName","AuditorName.commonFilter" );	
	if(  !jQuery('#cmbAuditorLevel').is(':disabled') )
		fillComboBox("frmAudit","cmbAuditorLevel","AuditorLevel.commonFilter" );
	
});
	function getRelatedFilterValues()
	{
		var filterStr='';
		
		var cmbtagno = jQuery("#cmbAuditorName").combobox("getValue");
		filterStr += "&cmbAuditorName="+cmbtagno;
		
		var cmbAuditorLevel = jQuery("#cmbAuditorLevel").datebox("getValue");
		filterStr += "&cmbAuditorLevel="+cmbAuditorLevel;
		
		//var dteAuditToDate = jQuery("#dteAuditToDate").datebox("getValue");
		//filterStr += "&dteAuditToDate="+dteAuditToDate;
	
		var cboType = jQuery("#cboType").val();
		filterStr += "&cboType="+cboType;
		
		var cboStatus = jQuery("#cboStatus").val();
		filterStr += "&cboStatus="+cboStatus;
		
		
		//alert("filterStr"+filterStr);
		return filterStr;
	}
	</script>
	<form id="frmAudit" name="frmAudit">
						<!--   Safety Tab	-->
						<div title="Audit" style="padding:10px;">
						
							   <div class="sub-header">Regular Filter</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Auditor Name</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbAuditorName" name="cmbAuditorName" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                    <div  class="easyui-paddingbfpx">
                        			<label>Auditor Level</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbAuditorLevel" name="cmbAuditorLevel" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   <div  class="easyui-paddingbfpx">
                        			<label>Audit Type</label>                       
                    		   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                    <select id="cboType" class="easyui-combobox" name="cboType" style="width:160px;" required="true">
													<option value="I"> Internal</option>
													<option value="E">External</option>													
									   </select> 
			                   </div>
			                   
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Status</label>
                   				 </div>  
                   				 <div class="easyui-paddingbfpx"> 
			                         <select id="cboStatus" class="easyui-combobox" name="cboStatus" style="width:160px;" required="true">
													<option value="P">Pass</option>
													<option value="F">Fail</option>													
									   </select> 			                        
			                   </div>
<!--			                      <div  class="easyui-paddingbfpx">-->
<!--                        					<label>Audit From</label>-->
<!--                        					<span style="margin-left: 97px;"><label>To</label></span>                       -->
<!--                    		  			 </div>-->
<!--			                     <div class="easyui-paddingbfpx"> -->
<!--			                     -->
<!--			                     <input id="dteAuditFromDate" name="dteAuditFromDate" class="easyui-datebox"  style="width:100px" value=""  >-->
<!--												<span class="spinner"><input id="spnAuditfrm" name="spnAuditfrm"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>-->
<!--											<span style="margin-left: 10px;">-->
<!--			                        			<input id="dteAuditToDate" name="dteAuditToDate" class="easyui-datebox"  style="width:100px" value=""  >-->
<!--			                        			<span class="spinner"><input id="spnAuditto" name="spnAuditto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>-->
<!--			                        		</span>-->
<!--			                        		</div>-->
			                   </td>
			                   <td valign="top" style="padding-left: 20px;">
						     
			                     </td></tr></table></div>
			                     
						</div>
						</form>
						
