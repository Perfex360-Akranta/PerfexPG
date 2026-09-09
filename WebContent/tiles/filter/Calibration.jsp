<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	function getRelatedFilterValues()
	{
		var filterStr;
		
		var cmbimte = jQuery("#cmbimte").combobox("getValue");
		filterStr = "&cmbimte="+cmbimte;

		var cmbslno = jQuery("#cmbslno").combobox("getValue");
		filterStr = "&cmbslno="+cmbslno;

		var dtcalibFromDate = jQuery("#calibFromDate").datebox("getValue");
		filterStr += "&dtcalibFromDate="+dtcalibFromDate;
		
		var dtcalibToDate = jQuery("#calibToDate").datebox("getValue");
		filterStr += "&dtcalibToDate="+dtcalibToDate;
		
		var cmbgauge = jQuery("#cmbgauge").combobox("getValue");
		filterStr = "&cmbgauge="+cmbgauge;

		var cmbparameter = jQuery("#cmbparameter").combobox("getValue");
		filterStr = "&cmbparameter="+cmbparameter;

		var cmbexternalagencies = jQuery("#cmbexternalagencies").combobox("getValue");
		filterStr = "&cmbexternalagencies="+cmbexternalagencies;

		var cmbmethod = jQuery("#cmbmethod").combobox("getValue");
		filterStr = "&cmbmethod="+cmbmethod;

		var cmbdecision = jQuery("#cmbdecision").combobox("getValue");
		filterStr = "&cmbdecision="+cmbdecision;

		var cmbcompltdby = jQuery("#cmbcompltdby").combobox("getValue");
		filterStr = "&cmbcompltdby="+cmbcompltdby;

		var cmbissuedto = jQuery("#issuedto").combobox("getValue");
		filterStr = "&cmbissuedto="+cmbissuedto;

		var cmbissuedtocalib = jQuery("#issuedtocalib").combobox("getValue");
		filterStr = "&cmbissuedtocalib="+cmbissuedtocalib;

		var cmbservicetype = jQuery("#servicetype").combobox("getValue");
		filterStr = "&cmbservicetype="+cmbservicetype;

		var chkexportchkbox = jQuery("#exportchkbox").val();
		filterStr = "&chkexportchkbox="+chkexportchkbox;
		

		return filterStr;
	}
	</script>
						<!--   Calibration Tab	-->
						<div title="Calibration" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>IMTE </label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbimte" name="cmbimte" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>SlNo</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbslno" name="cmbslno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Calib From Date</label>
                   					 <span  style="margin-left: 85px;">To Date</span>
                    		   </div> 
			                   <div class="easyui-paddingbfpx"> 
			                        <input id="calibFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="calibToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                  </div>
			                  
							   <div  class="easyui-paddingbfpx">
                        			<label>Gauge </label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbgauge" name="cmbgauge" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Parameter</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbparameter" name="cmbparameter" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   </td>
			                   <td valign="top" style="padding-left:20px;">
								<div  class="easyui-paddingbfpx">
                        			<label>External Agencies </label>                       
                    		   	</div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbexternalagencies" name="cmbexternalagencies" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Method</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbmethod" name="cmbmethod" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
							    <div  class="easyui-paddingbfpx">
                        			<label>Decision </label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdecision" name="cmbdecision" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Completed By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcompltdby" name="cmbcompltdby" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                    <div class="sub-header">Advanced Filter Criteria</div>
			                   	<div align="" style="padding-left:250px;">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Issued To</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="issuedto" class="easyui-combobox" name="issuedto" style="width:160px;" required="true">
													<option value="L">Line </option>
													<option value="V">Vendor </option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                         <input id="exportchkbox" type="checkbox"/> <label>Export to HTML</label>
			                        </span> 
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                  					 <label>Issued To</label>
                  					  <span  style="margin-left: 125px;">Service Type</span>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="issuedtocalib" class="easyui-combobox" name="issuedtocalib" style="width:160px;" required="true">
													<option value="L">Line </option>
													<option value="V">Vendor </option>
													<option value="B">Broken</option>
													<option value="LO">Lost</option>
													<option value="GS"> Gauges Stores</option>
													<option value="S"> Scrap</option>
													<option value="OC"> Out of Calib</option>
													<option value="AC"> Associate Office</option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                          <select id="servicetype" class="easyui-combobox" name="servicetype" style="width:160px;" required="true">
													<option value="CR">Calibration Request </option>
													<option value="SR">Service Request </option>
													<option value="CRET">Calibration Return</option>
													<option value="SRET"> Service Return</option>
									      </select> 
			                        </span> 
			                     </div>
			                   </td></tr></table></div>
						</div>
						