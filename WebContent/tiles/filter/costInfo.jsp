<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function getRelatedFilterValues()
	{
		var filterStr="";
		
		var cmbgrade = jQuery("#cmbgrade").combobox("getValue");
		filterStr += "&cmbgrade="+cmbgrade;

		var cmbemp = jQuery("#cmbemp").combobox("getValue");
		filterStr += "&cmbemp="+cmbemp;

		var cmbReportType = jQuery("#cmbReportType").combobox("getValue");
		filterStr += "&cmbReportType="+cmbReportType;

		
		return filterStr;
	}
	</script>
		<!--   Manpower Cost Tab	-->
						<div title="Cost Infomation" style="padding:10px;">
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        		<label>Grade</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbgrade" name="cmbgrade" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Employee</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbemp" name="cmbemp" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                  </td></tr></table></div>
			                   	<div class="sub-header">Advanced Filter Criteria</div>
			                    <div style="padding-left:60px;">
								<table><tr><td>
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Cost</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbfpx" > 
				                      <select id="cmbReportType" name="cmbReportType"  class="easyui-combobox" style="width:160px;" required="true">
				                      	<option value="ABN"> Abnormality</option>
										<option value="BDM"> BreakDown</option>
										<option value="MCA"> Maintenance Log</option>
										<option value="PMD"> Planned Maintenance</option>
										<option value="GEN"> General Maintenance</option>
									 </select> 
								</div>
								</td></tr></table></div>
						</div>
