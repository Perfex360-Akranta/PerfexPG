<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
	<script type="text/javascript">
	
	jQuery(document).ready(function(){
	initialiseForm('frmPcsRelated');
	
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }
	else	
		 jQuery('#Filter').css('height','415px');
	jQuery("#chkboxoccurence").attr("checked",false);
	jQuery("#chkboxtime").attr("checked",true);
	
	
	if(  !jQuery('#cmbRawMaterial').is(':disabled') )
		fillComboBox("frmFilter","cmbRawMaterial","rawMaterial.commonFilter");
	if(  !jQuery('#cmbLoss').is(':disabled') )
		fillComboBox("frmFilter","cmbLoss","loss.commonFilter?LossFrom=PCSLogConfig",true);
	if(  !jQuery('#cmbpcsprrod').is(':disabled') ){
	
		fillComboBox("frmPcsRelated","cmbpcsprrod","product.commonFilter");
	}
	if( !jQuery('#cmbshift').is(':disabled') )
		fillComboBox("frmQuality","cmbshift","shift.commonFilter?frmRfilter=true" );
	//jQuery("#cmbpcssubgrp").combobox("disable");

//	fillComboBox("frmPcsRelated","cmbJhStep","Combo_JhStep.eqp");
	});
	
	/*jQuery('#chktimechkbox').click(function(){		
 		if(jQuery('#chktimechkbox').is(':checked') == false)
		{
 			if(jQuery('#chkallchkbox').is(':checked') == false && jQuery('#chkoccurchkbox').is(':checked') == false)
 				jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
 			else
 				jQuery('input:checkbox[name=chktimechkbox]').attr('checked',false);
 			
 			jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	false);
 				
		}
 		else
 	 	{
			jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
			if(jQuery('#chkoccurchkbox').is(':checked') == true)
				jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	true);
 	 	}
 	 	
					
		//}
 	}); 		

	jQuery('#chkoccurchkbox').click(function(){
		if(jQuery('#chkoccurchkbox').is(':checked') == false)
		{
			if(jQuery('#chkallchkbox').is(':checked') == false && jQuery('#chktimechkbox').is(':checked') == false)
 				jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
 			else
 				jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',false);
 			
 			jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	false);
 				
		}
 		else
 	 	{
			jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
			if(jQuery('#chktimechkbox').is(':checked') == true)
				jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	true);
 	 	}	
	});*/
	jQuery('#chkboxall').click(function(){
		if(jQuery('#chkboxall').is(':checked') == true)
		{
			jQuery('input:checkbox[name=chkboxoee]').attr('checked',true);
			jQuery('input:checkbox[name=chkboxqr]').attr('checked',true);
			jQuery('input:checkbox[name=chkboxpr]').attr('checked',true);
			jQuery('input:checkbox[name=chkboxar]').attr('checked',true);
		}	
	
	});
	jQuery('#chkboxoee').click(function(){
		if(jQuery('#chkboxoee').is(':checked') == false)
		{
			jQuery('input:checkbox[name=chkboxall]').attr('checked',false);
			//jQuery('input:checkbox[name=chkboxqr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxpr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxar]').attr('checked',true);
		}			
	});
	jQuery('#chkboxqr').click(function(){
		if(jQuery('#chkboxqr').is(':checked') == false)
		{
			jQuery('input:checkbox[name=chkboxall]').attr('checked',false);
			//jQuery('input:checkbox[name=chkboxqr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxpr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxar]').attr('checked',true);
		}			
	});
	jQuery('#chkboxpr').click(function(){
		if(jQuery('#chkboxpr').is(':checked') == false)
		{
			jQuery('input:checkbox[name=chkboxall]').attr('checked',false);
			//jQuery('input:checkbox[name=chkboxqr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxpr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxar]').attr('checked',true);
		}			
	});
	jQuery('#chkboxar').click(function(){
		if(jQuery('#chkboxar').is(':checked') == false)
		{
			jQuery('input:checkbox[name=chkboxall]').attr('checked',false);
			//jQuery('input:checkbox[name=chkboxqr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxpr]').attr('checked',true);
			//jQuery('input:checkbox[name=chkboxar]').attr('checked',true);
		}			
	});
	
	function  frmPcsRelatedcmbpcsprrod_onSelect(record)
	{	
		jQuery("#cmbcause").combobox('clear');
		reloadCombo("frmPcsRelated","cmbpcsprrod","product.commonFilter");	
		
	}
	
	function getRelatedFilterValues()
	{
		var filterStr="";
		//alert(jQuery("#chkboxtime").val());
		var cmbpcssubgrp = jQuery("#cmbpcssubgrp").combobox("getValue");
		filterStr += "&cmbpcssubgrp="+cmbpcssubgrp;
		
		var cmbpcsprrod = jQuery("#cmbpcsprrod").combobox("getValue");
		filterStr += "&cmbpcsprrod="+cmbpcsprrod;
		
		var cmbRawMaterial = jQuery("#cmbRawMaterial").combobox("getValue");
		filterStr += "&rawMaterial="+cmbRawMaterial;
		 
		var cmbLoss = jQuery("#cmbLoss").combobox("getValue");
		filterStr += "&cmbLossid="+cmbLoss;
		
		var cmbshift = jQuery('#cmbshift').combobox('getValue');
		filterStr += "&cmbshift="+cmbshift;

		
		
		var cboLossRptType = jQuery('#cboReportType').val();
		if(cboLossRptType == "S")
			filterStr += "&cboLossRptType=SECT";
		else if(cboLossRptType == "M")
			filterStr += "&cboLossRptType=MCHM";
		else if(cboLossRptType == "C")
			filterStr += "&cboLossRptType=CELL";
		
		filterStr += "&chkboxef="+getChkBoxVal('chkboxef');
		filterStr += "&chktimechkbox="+getChkBoxVal('chktimechkbox');
		filterStr += "&chkboxdf="+getChkBoxVal('chkboxdf');
		filterStr += "&chkboxar="+getChkBoxVal('chkboxar');
		filterStr += "&chkboxpr="+getChkBoxVal('chkboxpr');
		filterStr += "&chkboxqr="+getChkBoxVal('chkboxqr');
		filterStr += "&chkboxoee="+getChkBoxVal('chkboxoee');
		filterStr += "&chkboxall="+getChkBoxVal('chkboxall');	
		
		
		var chkOcc = getChkBoxVal("chkboxoccurence");
		var chkTime = getChkBoxVal("chkboxtime");
		var chkboxWoNo = getChkBoxVal('chkboxWoNo');
		var chkProd = getChkBoxVal("chkboxProd");
		var chkShift = getChkBoxVal('chkboxShift');
		var chkboxPcsShift = getChkBoxVal('chkboxPcsShift');
		var chkboxPcsDate = getChkBoxVal('chkboxPcsDate');
		var chkboxMgrCal = getChkBoxVal('chkboxMgrCal');
		var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');
		
		filterStr += "&chkboxoccurence="+ (chkOcc == "1" || chkOcc == 1 ? 'Y':'N') ;
		filterStr += "&chkboxtime="+ (chkTime == "1" || chkTime == 1 ? 'Y':'N');
		filterStr += "&chkboxWoNo="+(chkboxWoNo=="1" || chkboxWoNo==1? 'Y':'N');
		filterStr += "&chkboxProd="+(chkProd=="1" || chkProd==1? 'Y':'N');
		filterStr += "&chkboxPcsShift="+(chkboxPcsShift=="1" || chkboxPcsShift==1? 'Y':'N');
		filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
		filterStr += "&chkboxMgrCal="+(chkboxMgrCal=="1" || chkboxMgrCal==1? 'Y':'N');
		filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
		
		filterStr += "&chkboxhour="+getChkBoxVal('chkboxhour');		
		filterStr += "&chkboxshift="+getChkBoxVal('chkboxshift');
		filterStr += "&chkboxday="+getChkBoxVal('chkboxday');
		filterStr += "&chkboxweek="+getChkBoxVal('chkboxweek');
		

		return filterStr;
	}
	function getChkBoxVal(Id) {		
		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}
	

	</script>
						<!--   PCS Reports Tab	-->
						
<form id="frmPcsRelated">
						<div title="PCS Reports" style="padding:10px;">
						
						  	   <div class="sub-header">Regular</div>
						  	   	<div align="left">
									<table><tr><td>
								   <div  class="easyui-paddingbfpx">
	                        			<label>Sub Group</label>                       
	                    		   </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="cmbpcssubgrp" name="cmbpcssubgrp" class="easyui-combobox"  style="width:250px" value=""  >                       
				                   </div>
				                   
				                   <div  class="easyui-paddingbfpx">
	                        			<label>Product</label>                       
	                    		   </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="cmbpcsprrod" name="cmbpcsprrod" class="easyui-combobox"  style="width:250px" value=""  >                       
				                   </div>
				                   </td>
				                   <td valign="top" style="padding-left:20px;">
	<!--			                   		/*<div  class="easyui-paddingbfpx">-->
	<!--                        					<label>JhStep</label>                       -->
	<!--                    		   		</div> -->
	<!--                    		   		 <div class="easyui-paddingbfpx"> -->
	<!--			                   				<input id="cmbJhStep" name="cmbJhStep" class="easyui-combobox"  style="width:350px" value=""  >-->
	<!--			                   		</div> */-->
	
									<div  class="easyui-paddingbfpx">
	                        			<label>RawMaterial Type</label>                       
	                    		   </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="cmbRawMaterial" name="cmbRawMaterial" class="easyui-combobox"  style="width:250px" value=""  >                       
				                   </div>
				                   <div  class="easyui-paddingbfpx">
	                        			<label>Loss</label>                       
	                    		   </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="cmbLoss" name="cmbLoss" class="easyui-combobox"  style="width:250px" value=""  >                       
				                   </div>
				                   </td>
				                   <td valign="top" style="padding-left:20px; display: none;">
				                   		 <div  class="easyui-paddingbfpx">
	                        				<label>Report Type</label>                       
	                    		   		</div> 
	                    		   		<div class="easyui-paddingbfpx"> 
					                   		<select id="cboReportType" class="easyui-combobox"  style="width:200px;padding-left:10px;">
												<option value="S">SECTION WISE</option>
												<option value="C">LINE WISE</option>
												<option value="M">MACHINE WISE</option>
											</select>
										</div>
				                   </td>
				                   </tr></table>
				                 </div>
			                    <div class="sub-header">Advance</div>
			                    <div align="left" style="padding-left:20px">
									<table>
										<tr>
											<td>
							                   <div  class="easyui-paddingbfpx">
				                  					 <label>View Option</label>
											   </div> 
											     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
				                  					  <input id="chkboxef" name="chkboxef" type="checkbox"/> <label>Entry Format</label>
				                  					   <span  style="margin-left: 2px;">  <input id="chkboxdf" name="chkboxdf" type="checkbox"/> <label>Detailed Format</label></span>
												</div>
								
												   <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
					                  					  <input id="chkboxar" name="chkboxar" type="checkbox" checked="checked" value="Y"/> <label>AR</label>
					                  					  <span  style="margin-left: 2px;">  <input id="chkboxpr" name="chkboxpr" type="checkbox" checked="checked" value="Y"/> <label>PR</label></span>
					                  					  <span  style="margin-left: 2px;">  <input id="chkboxqr" name="chkboxqr"  type="checkbox"  checked="checked" value="Y"/> <label>QR</label></span>
					                  					  <span  style="margin-left: 2px;">  <input id="chkboxoee" name="chkboxoee"  type="checkbox"  checked="checked" value="Y"/> <label>OEE</label></span>
					                  					  <span  style="margin-left: 2px;">  <input id="chkboxall" name="chkboxall"  type="checkbox" checked="checked"  value="Y"/> <label>All</label></span>
													</div>
								
													  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
					                  					  <input id="chkboxoccurence" name="chkboxoccurence" type="checkbox" /> <label>Occurence</label>
					                  					  <span  style="margin-left: 2px;">  <input id="chkboxtime" name="chkboxtime" type="checkbox"/> <label>Time</label></span>
					                  					 
					                  				 </div>
					                  				 <div  class="easyui-paddingbfpx">
					                        			<label>Shift</label>                       
					                    		  		 </div> 
								                    	<div class="easyui-paddingbfpx"> 
								                        <input id="cmbshift" name="cmbshift" class="easyui-combobox"  style="width:228px" value=""  >                       
								                   		</div>
                  				 				 </td>
                  				 
<!--                  				    <div align="right" style="padding-left:20px">  -->

								<td valign="top" style="padding-left:20px">
			                  				 <div  class="easyui-paddingbfpx">
			                  					 <label>PCS Option</label>
										     </div> 
										     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:400px;padding-left:5px;">
			                  					  <input id="chkboxhour" name="chkboxhour" type="checkbox"/> <label>Hour</label>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxshift" name="chkboxshift" type="checkbox"/> <label>Shift</label></span>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxday" name="chkboxday" type="checkbox"/> <label>Day</label></span>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxweek" name="chkboxweek" type="checkbox"/> <label>Week</label></span>
											 </div>
											  <div  class="easyui-paddingbfpx">
			                  					 <label>PCS Type</label>
										     </div> 
										     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:400px;padding-left:5px;">
			                  					  <input id="chkboxhour" name="chkboxhour" type="checkbox"/> <label>Hour</label>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxshift" name="chkboxshift" type="checkbox"/> <label>Shift</label></span>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxday" name="chkboxday" type="checkbox"/> <label>Day</label></span>
			                  					  <span  style="margin-left: 2px;">  <input id="chkboxweek" name="chkboxweek" type="checkbox"/> <label>Week</label></span>
			                  					  <span  style="margin-left: 2px;"> <input id="chkboxWoNo" name="chkboxWoNo" type="checkbox"  checked="checked"/><label>Include WorkOrder.No  </label></span>
											 </div>
								 
											 <div class="easyui-paddingbfpx" style="padding-left:5px;">						 	 
			
											 	<span style="margin-left: 2px;"><input id="chkboxProd" name="chkboxProd" type="checkbox" checked="checked"/><label style="padding-left: 5px;">Include Product  </label></span>
											 	<span style="margin-left: 2px;"><input id="chkboxMgrCal" name="chkboxMgrCal" type="checkbox" /><label style="padding-left: 5px;">Management Calculation</label></span>
											 	<span style="margin-left: 2px;"><input id="chkboxPcsShift" name="chkboxPcsShift" type="checkbox" /><label>Shift Wise</label></span>
											 	<span style="margin-left: 2px;"><input id="chkboxPcsDate" name="chkboxPcsDate" type="checkbox" /><label>Date Wise</label></span>
											 </div>
											 <div class="easyui-paddingbfpx">
											 <span style="margin-left: 3px;"><input id="chkRemoveBlank" name="chkRemoveBlank" type="checkbox" checked="checked"/><label>Remove Blanks</label></span>
			<!--								 	<span style="padding-left: 0px;"><input id="chkboxPcsShift" name="chkboxPcsShift" type="checkbox" /><label style="padding-left: 5px;">Shift Wise</label></span>-->
			<!--								 	<span style="padding-left: 10px;"><input id="chkboxPcsDate" name="chkboxPcsDate" type="checkbox" /><label style="padding-left: 5px;">Date Wise</label></span>-->
											 	
			
			<!--								 	<span style="padding-left: 10px;"><label>Include Product  </label><input id="chkboxProd" name="chkboxProd" type="checkbox" checked="checked"/></span>-->
											 	
											 	
			<!--								 	<br><span style="padding-left: 0px;"><label>Management Calculation</label><input id="chkboxMgrCal" name="chkboxMgrCal" type="checkbox" /></span>-->
											 	
			
											 </div>
								 		</td>
								 	</tr>
								 </table>
								 
							</div>
						</div>
						




</form>					