<script type="text/javascript">
jQuery(document).ready(function(){
	
	
	jQuery('#submitForm').val('frmSafety');
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }	
	initialiseForm('frmSafety');	
	//formatDateBox('dteincdntFromDate','dd-MMM-yyyy');
	//formatDateBox('dteincdntdToDate','dd-MMM-yyyy');
	//fillComboBox("frmSafety","cmbincidenttype","IncidentTypeCombo.accIncRpt");//
	fillComboBox("frmSafety","cmbincidentno","accidentNo.commonFilter");
	if(  !jQuery('#cmbemployee').is(':disabled') )
		fillComboBox("frmSafety","cmbemployee","employee.commonFilter" );	

	if(  !jQuery('#cmbbodyPart').is(':disabled') )
		fillComboBox("frmSafety","cmbbodyPart","bodypart.commonFilter" );	

	if(  !jQuery('#cmbinjuryType').is(':disabled') )
		fillComboBox("frmSafety","cmbinjuryType","injurytype.commonFilter" );	


	jQuery('#chkminbox').click(function(){		
 		if(jQuery('#chkminbox').is(':checked') == false && jQuery('#chkmajbox').is(':checked') == false)
		{ 	
 			jQuery('input:checkbox[name=chkmajbox]').attr('checked',true);			
 		 				
		} 		
	}); 	
	jQuery('#chkmajbox').click(function(){		
 		if(jQuery('#chkmajbox').is(':checked') == false && jQuery('#chkminbox').is(':checked') == false)
		{ 	
 			
 			jQuery('input:checkbox[name=chkminbox]').attr('checked',true);			
 		 				
		} 		
	}); 


	
});
	function getRelatedFilterValues()
	{
		var filterStr='';
		
		var cmbtagno = jQuery("#cmbtagno").combobox("getValue");
		filterStr += "&cmbtagno="+cmbtagno;

		var cmbsafetysubtype = jQuery("#cmbsafetysubtype").combobox("getValue");
		filterStr += "&cmbsafetysubtype="+cmbsafetysubtype;

		var cmbincidentno = jQuery("#cmbincidentno").combobox("getValue");
		filterStr += "&cmbincidentno="+cmbincidentno;

		/*var cmbincidenttype = jQuery("#cmbincidenttype").combobox("getValue");
		filterStr += "&cmbincidenttype="+cmbincidenttype;*/

		var cmbemployee = jQuery("#cmbemployee").combobox("getValue");
		filterStr += "&cmbemployee="+cmbemployee;

		var cmbbodyPart = jQuery("#cmbbodyPart").combobox("getValue");	
		
		filterStr += "&cmbbodyPart="+cmbbodyPart;
		
		
		var cmbinjuryType = jQuery("#cmbinjuryType").combobox("getValue");
		filterStr += "&cmbinjuryType="+cmbinjuryType;

		
		//var dteincdntFromDate = jQuery("#dteincdntFromDate").datebox("getValue");
		//filterStr += "&dteincdntFromDate="+dteincdntFromDate;
		
		//var dteincdntdToDate = jQuery("#dteincdntdToDate").datebox("getValue");
		//filterStr += "&dteincdntdToDate="+dteincdntdToDate;
	
		var cboimprovmnt = jQuery("#cboimprovmnt").val();
		filterStr += "&cboimprovmnt="+cboimprovmnt;
		
		var cbopriority = jQuery("#cbopriority").val();
		filterStr += "&cbopriority="+cbopriority;

		var cbosafetytype = jQuery("#cbosafetytype").val();
		filterStr += "&cbosafetytype="+cbosafetytype;

		var cborelatedto = jQuery("#cborelatedto").val();
		filterStr += "&cborelatedto="+cborelatedto;

		var cboreptype = jQuery("#cboreptype").val();
		filterStr += "&cboreptype="+cboreptype;

		var cboworkarea = jQuery("#cboworkarea").val();
		filterStr += "&cboworkarea="+cboworkarea;

		var cboStatus = jQuery("#cboStatus").val();
		filterStr += "&cboStatus="+cboStatus;
		
		//var chkinstancechkbox = jQuery("#chkinstancechkbox").attr('checked');
		filterStr += "&chkinstancechkbox="+getChkBoxVal('chkinstancechkbox');

		//var chkmdlchkbox = jQuery("#chkmdlchkbox").attr('checked');
		filterStr += "&chkmdlchkbox="+getChkBoxVal('chkmdlchkbox');


		if(  !jQuery('#chkojtbox').is(':disabled') )
			filterStr += "&chkojtbox="+getChkBoxVal('chkojtbox');	
		if(  !jQuery('#chkpybox').is(':disabled') )
			filterStr += "&chkpybox="+getChkBoxVal('chkpybox');	
		if(  !jQuery('#chkkkbox').is(':disabled') )
			filterStr += "&chkkkbox="+getChkBoxVal('chkkkbox');	
		if(  !jQuery('#chketbox').is(':disabled') )
			filterStr += "&chketbox="+getChkBoxVal('chketbox');
		if(  !jQuery('#chkminbox').is(':disabled') )
			filterStr += "&chkminbox="+getChkBoxVal('chkminbox');
		if(  !jQuery('#chkmajbox').is(':disabled') )
			filterStr += "&chkmajbox="+getChkBoxVal('chkmajbox');
		
		var chkTot = getChkBoxVal("chkTot");
		filterStr += "&chkTot="+ (chkTot == "1" || chkTot == 1 ? 'Y':'N');
		//alert("filterStr"+filterStr);
		return filterStr;
	}

function getChkBoxVal(Id) {		
		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}
	
	</script>
	<form id="frmSafety" name="frmSafety">
						<!--   Safety Tab	-->
						<div title="Safety" style="padding:10px;">
						
							   <div class="sub-header">Regular Filter</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Tag No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbtagno" name="cmbtagno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Sub Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsafetysubtype" name="cmbsafetysubtype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Incident No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbincidentno" name="cmbincidentno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                     <div  class="easyui-paddingbfpx">
                  					  <label>Employee</label>                   					  
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="cmbemployee" name="cmbemployee" class="easyui-combobox"  style="width:350px" value=""  >			                        
			                     </div>
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Status</label>
                  					  
                   					  
                    			</div> 
			                      <div class="easyui-paddingbfpx"> 
			                         <select id="cboStatus" class="easyui-combobox" name="cboStatus" style="width:160px;" required="true">
													<option value="P"> Pending</option>
													<option value="C">Completed</option>													
									   </select> 
									   
									   <span style="margin-left: 7.5%; margin-left: 2%\9;">
									   	<input id="chkTot"  name="chkTot" type="checkbox" value="N"/>
									   	<label>Total</label> 
									   </span>			                        
			                     </div>
			                   </td>
			                   <td valign="top" style="padding-left: 20px;">
<!--			                   <div  class="easyui-paddingbfpx">-->
<!--                        			<label>Incident Type</label>                       -->
<!--                    		   </div> -->
<!--			                    <div class="easyui-paddingbfpx"> -->
<!--			                        <input id="cmbincidenttype" name="cmbincidenttype" class="easyui-combobox"  style="width:350px" value=""  >                       -->
<!--			                   </div>-->
			                   

			                     <div  class="easyui-paddingbfpx">
                  					  <label>Injury Mode</label>
                   					  
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="cmbinjuryType" name="cmbinjuryType" class="easyui-combobox"  style="width:350px" value=""  >			                        
			                     </div>
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Body Part</label>                   					  
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="cmbbodyPart" name="cmbbodyPart" class="easyui-combobox"  style="width:350px" value=""  >			                        
			                     </div>
			                      <br>
			                       
			                     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px; ">
                  					  <input id="chkojtbox"  name="chkojtbox" type="checkbox" checked="checked" value="Y"/> <label>OJT</label>
                  					  <span  style="margin-left: 8%;">  <input id="chkpybox"  name="chkpybox" type="checkbox"   checked="checked" value="Y"/> <label>POKAYOKE</label></span>
                  					  <span  style="margin-left: 8%;">  <input id="chkkkbox"  name="chkkkbox"type="checkbox"   checked="checked" value="Y"/> <label>KZN</label></span>
                  					  <span  style="margin-left: 10%;">  <input id="chketbox"  name="chketbox"type="checkbox"    checked="checked" value="Y"/> <label>OPL</label></span>
<!--                  					  <span  style="margin-left: 2px;">  <input id="chk4mbox"  name="chk4mbox" type="checkbox"    checked="checked" value="Y"/> <label>4M </label></span>-->
<!--                  					  <span  style="margin-left: 2px;">  <input id="chkimpdonebox"  name="chkimpdonebox" type="checkbox"  value="Y"/> <label>Improvements Done </label></span>-->
                  			      </div>
                  			      <br>
                  			      <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px; padding-top: 5px;">
                  			      <input id="chkminbox"  name="chkminbox" type="checkbox" checked="checked"  value="Y"/> <label>MINOR</label>
                  			      <span  style="margin-left: 3.3%;"> <input id="chkmajbox"  name="chkmajbox" type="checkbox" checked="checked" value="Y"/> <label>MAJOR</label></span>
                  			      </div>
			                     </td></tr></table></div>
			                     
			                      <div class="sub-header">Advanced Filter Criteria</div>
			                      	<div align="center">
								<table><tr><td>
			                      <div  class="easyui-paddingbfpx">
                  					 <label>Improvement</label>
                   					 <span  style="margin-left: 112px;"><label>Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="cboimprovmnt" class="easyui-combobox" name="improvmnt" style="width:160px;" required="true">
												<option value="CIT"> CIT</option>
												<option value="OIT"> OIT</option>
												<option value="MP"> MP</option>
												<option value="MI"> MI</option>
												<option value="ALL"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cbosafetytype" class="easyui-combobox" name="safetytype" style="width:160px;" required="true">
													<option value="TW"> Type wise</option>
													<option value="STW"> Sub Type Wise</option>
													<option value="CW"> Category Wise</option>
													<option value="IW"> Impact Wise</option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      <div  class="easyui-paddingbfpx">
                  					 <label>Priority</label>
                   					 <span  style="margin-left: 147px;"><label>Related To</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="cbopriority" class="easyui-combobox" name="priority" style="width:160px;" required="true">
												<option value="LOW"> Low</option>
												<option value="MED"> Medium</option>
												<option value="HIGH"> High</option>
												<option value="ALL"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cborelatedto" class="easyui-combobox" name="relatedto" style="width:160px;" required="true">
													<option value="S"> Safety</option>
													<option value="E">Environment</option>
													<option value="H"> Health</option>
													<option value="A"> All</option>
									   </select> 
			                        </span> 
			                      </div>
			                      </td>
			                      <td style="padding-left: 20px; padding-top: 1.6%;">
			                      <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkinstancechkbox" type="checkbox"/> <label>Instance</label>
                  					  <span  style="margin-left: 32.2%; margin-left: 29%\9;">  <input id="chkmdlchkbox" type="checkbox"/> <label>Man Days lost</label></span>
								  </div>
								  
								  <div  class="easyui-paddingbfpx" >
                  					 <label>Report Type</label>
                   					 <span  style="margin-left: 119px;"><label>Work Area/Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx" > 
			                         <select id="cboreptype" class="easyui-combobox" name="reptype" style="width:160px;" required="true">
												<option value="INJ"> Injury</option>
												<option value="RSN">Reason</option>
												<option value="INCTYPE">Incident Type</option>
												<option value="INCCGRY">Incident Category</option>
												<option value="BP"> Body Part</option>
												<option value="RT"> Related To</option>
												<option value="SSA"> Substandard Action</option>
												<option value="SSC"> Substandard Condition</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cboworkarea" class="easyui-combobox" name="workarea" style="width:160px;" required="true">
													<option value="WA"> Work Area</option>
													<option value="T">Type</option>
													<option value="HV"> Health Visit</option>
													<option value="A"> All</option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      </td>
<!--			                       <td style="padding-left: 20px;">-->
<!--			                         <select id="cboStatus" class="easyui-combobox" name="cboStatus" style="width:160px;" required="true">-->
<!--													<option value="P"> Pending</option>-->
<!--													<option value="C">Completed</option>													-->
<!--									   </select> -->
<!--			                       </td>-->
			                      </tr></table></div>
						</div>
						<br>
						<br>
						</form>
						
