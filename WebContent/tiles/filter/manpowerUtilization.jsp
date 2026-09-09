<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function getRelatedFilterValues()
	{
		var filterStr="";
		
		var cmbteam = jQuery("#cmbteam").combobox("getValue");
		filterStr += "&cmbteam="+cmbteam;

		var cmbdept = jQuery("#cmbdept").combobox("getValue");
		filterStr += "&cmbdept="+cmbdept;

		var cmbtranstyp = jQuery("#transtyp").combobox("getValue");
		filterStr += "&cmbtranstyp="+cmbtranstyp;

		var chkminschkbox = jQuery("#minschkbox").val();
		filterStr += "&chkminschkbox="+chkminschkbox;

		var chkhrschkbox = jQuery("#hrschkbox").val();
		filterStr += "&chkhrschkbox="+chkhrschkbox;

		var chkplanchkbox = jQuery("#planchkbox").val();
		filterStr += "&chkplanchkbox="+chkplanchkbox;

		var chkactualchkbox = jQuery("#actualchkbox").val();
		filterStr += "&chkactualchkbox="+chkactualchkbox;

		
		return filterStr;
	}
	</script>
		
			<!--   Manpower Utilization Tab	-->
						<div title="Manpower Utilization" style="padding:10px;">
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        			<label>Team</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbteam" name="cmbteam" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Department</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdept" name="cmbdept" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   <div class="sub-header">Advance Filter Criteria</div>
			                   <div align="center">
								<table><tr><td>
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Transaction Type</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbtpx"> 
				                      <select id="transtyp" class="easyui-combobox" name="cost" style="width:160px;" required="true">
												<option value="BD"> Breakdown</option>
												<option value="GM"> General Maintenance</option>
												<option value="PM"> Planned Maintenance</option>
												<option value="A"> All</option>
									 </select> 
								</div>
								
								 <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="minschkbox" type="checkbox"/> <label>Minutes</label>
                   					  <span  style="margin-left: 2px;">  <input id="hrschkbox" type="checkbox"/> <label>Hours</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="planchkbox" type="checkbox"/> <label>Plan</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="actualchkbox" type="checkbox"/> <label>Actual</label></span>
                    		    </div> 
			                   </td></tr></table></div>
						</div>
						
					