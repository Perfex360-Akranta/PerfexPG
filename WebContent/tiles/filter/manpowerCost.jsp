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

		var cmbcost = jQuery("#cost").combobox("getValue");
		filterStr += "&cmbcost="+cmbcost;

		
		return filterStr;
	}
	</script>
		<!--   Manpower Cost Tab	-->
						<div title="Manpower Cost" style="padding:10px;">
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
			                     <div class="easyui-paddingbfpx"> 
				                      <select id="cost" class="easyui-combobox" name="cost" style="width:160px;" required="true">
												<option value="OC"> Other Cost</option>
												<option value="HC"> Holiday Cost</option>
												<option value="NC"> Normal Cost</option>
												<option value="A"> All</option>
									 </select> 
								</div>
								</td></tr></table></div>
						</div>
