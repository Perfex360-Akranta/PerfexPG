<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function getRelatedFilterValues()
	{
		var filterStr="";
		
		var cmbrefno = jQuery("#cmbrefno").combobox("getValue");
		filterStr += "&cmbrefno="+cmbrefno;

		var cmbdetectedby = jQuery("#cmbdetectedby").combobox("getValue");
		filterStr += "&cmbdetectedby="+cmbdetectedby;

		var dtdFromDate = jQuery("#dtdFromDate").datebox("getValue");
		filterStr += "&dtdFromDate="+dtdFromDate;

		var dtdToDate = jQuery("#dtdToDate").datebox("getValue");
		filterStr += "&dtdToDate="+dtdToDate;

		var dtdFromDate = jQuery("#dtdFromDate").datebox("getValue");
		filterStr += "&dtdFromDate="+dtdFromDate;

		var dttgtFromDate = jQuery("#tgtFromDate").datebox("getValue");
		filterStr += "&dttgtFromDate="+dttgtFromDate;

		var dttgtToDate = jQuery("#tgtToDate").val();
		filterStr += "&dttgtToDate="+dttgtToDate;

		var dtcompltdFromDate = jQuery("#compltdFromDate").datebox("getValue");
		filterStr += "&dtcompltdFromDate="+dtcompltdFromDate;

		var dtcompltdToDate = jQuery("#compltdToDate").val();
		filterStr += "&dtcompltdToDate="+dtcompltdToDate;


		return filterStr;
	}
	</script>
		<!--   Action Plan Tab	-->
						<div title="Action Plan" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Refernce No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbrefno" name="cmbrefno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Detected By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdetectedby" name="cmbdetectedby" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Detected From Date</label>
                   					   <span  style="margin-left: 60px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="dtdFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="dtdToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Target From Date</label>
                   					   <span  style="margin-left: 77px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="tgtFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="tgtToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Completed From Date</label>
                   					   <span  style="margin-left: 47px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="compltdFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="compltdToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     </td></tr></table></div>
						</div>
				