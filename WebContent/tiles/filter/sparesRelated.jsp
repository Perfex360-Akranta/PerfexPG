	
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmSparesRelated');
	if(screen.width <= 1024){
		 //jQuery('.easyui-combobox').css('width','286px');
		 jQuery('.easyui-datebox').css('width','120px');
		 jQuery('#tblAdvanced').css('padding-left','0px');
		 jQuery('#txtspareDescn').css('width','133px');
		 jQuery('#advaDiv').css('padding-left','14px');
		 jQuery('#shelfLbl').css('margin-left','139px');
		 jQuery('#prdGroup').css('margin-left',' 128px');
		 jQuery('#Filter').css('height','418px');
	 }	
	if(  !jQuery('#cmbsparePartNo').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbsparePartNo","combo_partno.sprmst");
	if(  !jQuery('#cmbcriticality').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbcriticality","combo_criticality.sprmst");
	if(  !jQuery('#cmbclassifcn').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbclassifcn","combo_classification.sprmst");	
	if(  !jQuery('#cmbspqcategory').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbspqcategory","combo_category.sprmst");
	if(  !jQuery('#cmbspqSubCat').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbspqSubCat","combo_subcategory.sprmst");
	if(  !jQuery('#cmbsprUom').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbsprUom","uomCombo.commonFilter");
	if(  !jQuery('#cmbsprMake').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbsprMake","combo_make.sprmst");
	if(  !jQuery('#cmbsprModel').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbsprModel","combo_model.sprmst");
	if(  !jQuery('#cmbSupplier').is(':disabled') )
		fillComboBox("frmSparesRelated","cmbSupplier","combo_supplier.sprmst");
	if(  !jQuery('#cmbJhStep').is(':disabled') )
		fillComboBox("frmPMRelated","cmbJhStep","Combo_JhStep.eqp");	
	setTimeout(function() {
		if(  jQuery('#chkSectionwise').is(':disabled'))
			{jQuery("#hdnCheckVal").val(true);}},1750);
	

});

	function getRelatedFilterValues()
	{
		var filterStr = "";
		
		var cmbsparePartNo = jQuery("#cmbsparePartNo").combobox("getValue");
		filterStr += "&cmbsparePartNo="+cmbsparePartNo;

		var txtspareDescn = jQuery("#txtspareDescn").val();
		filterStr += "&cmbspareDescn="+txtspareDescn.toUpperCase();
		
		var cboOptions = jQuery("#cboOptions").val();
		filterStr += "&cmboptions="+cboOptions;

		var cmbcriticality = jQuery("#cmbcriticality").combobox("getValue");
		filterStr += "&cmbcriticality="+cmbcriticality;
		
		var cmbclassifcn = jQuery("#cmbclassifcn").combobox("getValue");
		filterStr += "&cmbclassifcn="+cmbclassifcn;

		var cmbspqcategory = jQuery("#cmbspqcategory").combobox("getValue");
		filterStr += "&cmbspqcategory="+cmbspqcategory;

		var cmbspqsubcat = jQuery("#cmbspqSubCat").combobox("getValue");
		filterStr += "&cmbspqsubcat="+cmbspqsubcat;
	
		var cmbuom = jQuery("#cmbsprUom").combobox("getValue");
		filterStr += "&cmbuom="+cmbuom;

		var cmbmake = jQuery("#cmbsprMake").combobox("getValue");
		filterStr += "&cmbmake="+cmbmake;

		var cmbmodel = jQuery("#cmbsprModel").combobox("getValue");
		filterStr += "&cmbmodel="+cmbmodel;
		
		var cmbsource = jQuery("#cboSource").val();
		filterStr += "&cmbsources="+cmbsource;
		
		var cmbabcClass = jQuery("#cboAbcClass").val();
		filterStr += "&cmbabcClass="+cmbabcClass;
	
		var cmbspqType = jQuery("#cboSpqType").val();
		filterStr += "&cmbspqType="+cmbspqType;

		var cmbmachineSpec = jQuery("#cboMachineSpec").val();
		filterStr += "&cmbmachineSpec="+cmbmachineSpec;

		var cmbshelfLifeunt = jQuery("#cboShelfLifeunt").val();
		filterStr += "&cmbshelfLifeunt="+cmbshelfLifeunt;

		var cmbshelfLifeitem = jQuery("#cboShelfLifeItem").val();
		filterStr += "&cmbshelfLifeitem="+cmbshelfLifeitem;
		
		var cmbfreq = jQuery("#cboFreq").val();
		filterStr += "&cmbfreq="+cmbfreq;
		
		var cmbpmjobtype = jQuery("#cboPmjobtype").val();
		filterStr += "&cmbpmjobtype="+cmbpmjobtype;
		
		var cmbSupplier = jQuery("#cmbSupplier").combobox("getValue");
		filterStr += "&cmbSupplier="+cmbSupplier;
		
		var cmbprodcngroup = jQuery("#cmbprodcngroup").combobox("getValue");
		filterStr += "&cmbprodcngroup="+cmbprodcngroup;
		
		var cmbJhStep = jQuery("#cmbJhStep").combobox("getValue");
		filterStr += "&cmbJhStep="+cmbJhStep;

		var cboActivityType = jQuery("#cboActivityType").val();
		filterStr += "&cboActivityType="+cboActivityType;
		
		
			 //var sat = jQuery('#chkActwise:checked').val();
			 if(jQuery('#chkSummary:checked').val()=="on"){
				jQuery('#btnGraph').attr('disabled',false);				
				jQuery('#btnTarget').attr('disabled',false);
				jQuery('#btnGraph').addClass('easyui-button');
				jQuery('#btnTarget').addClass('easyui-button');				
			}
			else {
 			  	jQuery('#btnGraph').attr('disabled',true);
			 	jQuery('#btnTarget').attr('disabled',true);
				jQuery('#btnGraph').removeClass('easyui-button');
				jQuery('#btnTarget').removeClass('easyui-button');
			 	
			}					 
			
			 if(jQuery('#chkActwise:checked').val()=="on"){
				 var sparesChkBox = "ACTWISE";	
				 
			 }
			 
			 else if(jQuery('#chkActType:checked').val()=="on"){
				 sparesChkBox = "ACTTYPE";	
			
			 }
			 else if(jQuery('#chkSummary:checked').val()=="on"){
				 sparesChkBox = "SUMMARY";	
					 
			 }
			 else if(jQuery('#chkMonthwise:checked').val()=="on"){
				 sparesChkBox = "MONTHWISE";	
			
			 }
			
			 filterStr += "&grpBy="+sparesChkBox;
			 filterStr += "&groupBy="+sparesChkBox;
			 
		
	
		
		/*	
		var sparesChkBox = "";
		if(jQuery("#awisechkbox").attr('checked') == 'checked')
			sparesChkBox = "ACTWISE";
		if(jQuery("#atypechkbox").attr('checked') == 'checked')
			sparesChkBox = "ACTTYPE";
		if(jQuery("#mwisechkbox").attr('checked') == 'checked')
			sparesChkBox = "MONTHWISE";
		if(jQuery("#summarychkbox").attr('checked') == 'checked')
			sparesChkBox = "SUMMARY";*/
		//filterStr += "&chbval="+sparesChkBox;
	
		if(jQuery('#eqpwisechkbox').is(':checked')==true){
			filterStr += "&chkeqpwisechkbox=checked";
		}
		else if(jQuery('#sprwisechkbox').is(':checked')==true){
			filterStr += "&chksprwisechkbox=checked";
		}

		if(jQuery('#chkSectionwise').is(':checked')==true)
			filterStr += "&chkSectionwise=true";
		if(jQuery('#chkCellwise').is(':checked')==true)
			filterStr += "&chkCellwise=true";
		if(jQuery('#chkMchwise').is(':checked')==true)
			filterStr += "&chkMchwise=true";
		
		return filterStr;
	}
	
	function getChkBoxVal(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}


	
	
	jQuery('input[type=checkbox]').click( function()
	{
		 if(jQuery('#sprwisechkbox').is(':checked')==true)	
				enableDisableFilters("SpareReportSprWise_input.sprQuery");
		 //else
			 //enableDisableFilters("SpareReport_input.sprQuery");
		 	 
	
	});

	function displayColmn(val)
	{
		if(jQuery("#hdnCheckVal").val()=="true")
			val=true;
		
		 jQuery("#chkSectionwise").attr({"disabled":val});
		 jQuery("#chkCellwise").attr({"disabled":val});
		 jQuery("#chkMchwise").attr({"disabled":val});
	}

	jQuery('#btnClear').click( function()
	{
		if(jQuery('#sprwisechkbox').is(':checked')==true)	
			jQuery('#sprwisechkbox').attr({'checked':true});
		else
			jQuery('#eqpwisechkbox').attr({'checked':true});
	});

	jQuery('.clsMnthWise').click( function()
	{
		jQuery("#chkActType").attr({"checked":false});				
		jQuery("#chkActwise").attr({"checked":false});	
		jQuery("#chkSummary").attr({"checked":false});	
		displayColmn(false);
	});

	jQuery('#chkActType').click( function()
	{
		jQuery("#chkActwise").attr({"checked":false});				
		jQuery("#chkSummary").attr({"checked":false});	
		jQuery(".clsMnthWise").attr({"checked":false});
		displayColmn(false);
	});
	
	jQuery('#chkActwise').click( function()
	{
		jQuery("#chkActType").attr({"checked":false});				
		jQuery("#chkSummary").attr({"checked":false});	
		jQuery(".clsMnthWise").attr({"checked":false});	
		displayColmn(false);
	});
	
	jQuery('#chkSummary').click( function()
	{
		jQuery("#chkActType").attr({"checked":false});				
		jQuery("#chkActwise").attr({"checked":false});	
		jQuery(".clsMnthWise").attr({"checked":false});	
		jQuery("#chkSectionwise").attr({"checked":false});
		jQuery("#chkCellwise").attr({"checked":false});
		jQuery("#chkMchwise").attr({"checked":false});
		displayColmn(true);
	});
	
	
	</script>
	
	<form id="frmSparesRelated">
							<div class="testdiv"></div>
		<div style="height:480px;">
							<div title="Spare Parts Query" style="padding:10px;">
						
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td style="padding-left:10px;">
							   <div  class="easyui-paddingbfpx">
                        			<label>Spare Part No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsparePartNo" name="cmbsparePartNo" class="easyui-combobox"  style="width:250px" value=""  />                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<span style="padding-right: 50px;"><label>Spare Description</label></span>        
                        			<span><label>Options</label></span>               
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input type="text" id="txtspareDescn" name="txtspareDescn" class="easyui-text"  style="width:147px" value=""  />                       
			                  		 <select id="cboOptions" class="easyui-combobox" name="cboOptions" style="width:100px;" >
													 <option value="Begins">Begins With</option>
													<option value="Contains">Contains</option>
													<option value="Ends">Ends With</option>
									 </select> 
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Criticality</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcriticality" name="cmbcriticality" class="easyui-combobox"  style="width:250px" value="" / >                       
			                   </div>
			                   </td>
			                   <td style="padding-left:20px;">
			                    <div  class="easyui-paddingbfpx">
                        			<label>Classification</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbclassifcn" name="cmbclassifcn" class="easyui-combobox"  style="width:250px" value=""  />                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbspqcategory" name="cmbspqcategory" class="easyui-combobox"  style="width:250px" value="" / >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Sub Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbspqSubCat" name="cmbspqSubCat" class="easyui-combobox"  style="width:250px" value=""  />                       
			                   </div>
			                   </td>
			                   <td style="padding-left:20px;">
			                    <div  class="easyui-paddingbfpx">
                        			<label>UOM</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsprUom" name="cmbsprUom" class="easyui-combobox"  style="width:250px" value="" / >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Make</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsprMake" name="cmbsprMake" class="easyui-combobox"  style="width:250px" value=""  />                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Model</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsprModel" name="cmbsprModel" class="easyui-combobox"  style="width:250px" value=""  />                       
			                   </div>
			                   </td></tr></table></div>
			                   	<div class="sub-header">Advanced Filter Criteria</div>
			                   	<div id="advaDiv" align="center" style="padding-left:55px;">
								<table id="tblAdvanced" align="left" style="padding-left:40px;"><tr><td valign="top">
			                    <div  class="easyui-paddingbfpx">
                  					   <span style="padding-right: 145px;"><label>Source</label></span>
                  					   <span  style="padding-right: 145px;"><label>Type</label></span>
                  					     <span style="padding-right: 90px;"><label>Machine Specific</label></span>
                  					        <span style="padding-right: 145px;"><label>Frequency</label></span>
                    			</div> 
			                    <div class="easyui-paddingbfpx"> 
			                         <span> 
				                       <select id="cboSource" class="easyui-combobox" name="cboSource" style="width:160px;" >
													 <option value=""> All</option>
													<option value="L"> Local</option>
													<option value="I"> Import</option>
									   </select> 
			                        </span>
			                        <span style="margin-left: 25px;">
			                            <select id="cboSpqType" class="easyui-combobox" name="cboSpqType" style="width:160px;" >
												<option> All</option>
												<option value="D"> Direct</option>
												<option value="R"> Reusable</option>
										
									 </select> 
			                        </span> 
			                        
			                        <span style="margin-left: 5px;"> 
				                       <select id="cboMachineSpec" class="easyui-combobox" name="cboMachineSpec" style="width:160px;" >
													<option >All</option>
													<option value="Y"> Yes</option>
													<option value="N"> No</option>
									   </select> 
			                        </span> 
			                         <span style="margin-left: 25px;">
			                        	<select id="cboFreq" name="cboFreq" style="width:160px;" >
														<option value="">All</option>
														<option value="A"> Above Monthly</option>
														<option value="B"> Monthly and Below</option>													
										</select> 
			                        </span>
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>ABC Class</label>
                   					   <span id="shelfLbl" style="margin-left: 121px;"><label>Shelf Life Item</label></span>
                   					    <span  style="margin-left: 101px;"><label>Shelf Life Unit</label> </span>
                    			  </div> 
			                      <div class="easyui-paddingbfpx"> 
			                         <select id="cboAbcClass" class="easyui-combobox" name="cboAbcClass" style="width:160px;" >
												<option > All</option>
												<option value="A"> A</option>
												<option value="B"> B</option>
												<option value="C"> C</option>
											
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cboShelfLifeItem" class="easyui-combobox" name="cboShelfLifeItem" style="width:160px;" >
													<option>All</option>
													<option value="Y"> Yes</option>
													<option value="N"> No</option>
											
									   </select> 
			                        </span> 
			                        
			                         <span  style="margin-left: 5px;"> 
			                         	 <select id="cboShelfLifeunt" class="easyui-combobox" name="cboShelfLifeunt" style="width:160px;" >
												<option > All</option>
												<option value="D"> Day</option>
												<option value="Y"> Year</option>
												<option value="M"> Month</option>
												<option value="N"> None</option>
										 </select> 
									 </span>
									 
									 <div class="sub-cntborder floatright" style="padding-right: 25px;">
                    		      			<div id="divsprEqpWise"  class="easyui-paddingbfpx" >
		                  					  <input id="eqpwisechkbox" type="checkbox" onclick="validate_chkbxrpt('0')" checked="checked"/> 
		                  					  <label>Equipment Wise</label>
		                   					  <span  style="margin-left: 2px;">  
		                   					  <input id="sprwisechkbox" type="checkbox" onclick="validate_chkbxrpt('1')"/> 
		                   					  <label>Spare Wise</label></span>
                    		   	 		 </div>
                    		   		 </div>
			                         
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                        			<label>Supplier</label>   
                        			 <span  style="margin-left: 141px;"><label>JH Step</label></span>  
                        			 <span id="prdGroup" style="margin-left: 121px;"><label>Production Group</label></span>   
                        			 <span  style="margin-left: 81px;"><label>Report Type</label></span>                   
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbSupplier" name="cmbSupplier" class="easyui-combobox"  style="width:160px" value=""  />
			                       
			                  		<span  style="margin-left: 25px;"> 
			                       	   <input id="cmbJhStep" name="cmbJhStep" class="easyui-combobox"  style="width:160px" value=""  >
			                        </span>  
			                        <span  style="margin-left: 5px;"> 
			                           <input id="cmbprodcngroup" name="cmbprodcngroup" class="easyui-combobox"   style="width:160px" value=""  >  
			                        </span> 
			                        
			                        <span id="divactType">
                    		    		<span class="sub-cntborder" style="width: 150px;">
                    		      			<span  class="easyui-paddingbfpx" >
                  							   <input id="chkActwise" name="chkBox" type="checkbox" />
                  							   <label>Activity Wise</label>
                  					  
                   					 	 	 <span  style="margin-left: 2px;">  
 											  <input class="clsMnthWise" id="chkMonthwise" name="chkBox" type="checkbox" />
                   							   <label>Month Wise</label></span>
                    		   		      </span>
                    		   	  		<span  class="easyui-paddingbfpx" > 
                  					 		<input id="chkActType" name="chkBox" type="checkbox"  />
                  					    	<label>Activity Type</label>
                  					  
                  					  	 	<span  style="margin-left: 2px;">  
 										 	<input id="chkSummary" name="chkBox" type="checkbox" />
                   					 	 	<label>Summary</label></span>
                    		   		   </span>
                    		    	</span>
                    		    </span>                     
			                   </div>  
			                    <div  class="easyui-paddingbfpx">
                  					 <label>Activity Type</label>
                  					  <span  style="margin-left: 121px;"><label>Job Type</label></span> 
                  					  <span  style="margin-left: 121px;"><label>Display in Columns</label></span> 
                  					   
							   </div> 
			                     <div class="easyui-paddingbfpx"> 
			                         <select id="cboActivityType" class="easyui-combobox" name="cboActivityType" style="width:160px;" >
												<option value=""> All</option>
												<option value="PRM"> Preventive Maintenance</option>
												<option value="BDM"> Break Down </option>
												<option value="MA"> Machine Activity</option>
												<option value="UN"> Unplanned</option>
									 </select> 
									 <span  style="margin-left: 25px;"> 
									 <select id="cboPmjobtype" class="easyui-combobox" name="cboPmjobtype" style="width:160px;" >
												<option value=""> All</option>
												<option value="CBM"> Condition Based Maintenance</option>
												<option value="TBM"> Time Based Maintenance</option>
												<option value="PRM"> Preventive Maintenance</option>
												<!-- <option value="MBM"> Meter Based Maintenance</option>
												<option value="RBM">Run Based Maintenance</option>
												<option value="CAL"> Calibration</option>
												<option value="SDM"> Shutdown Maintenance</option> -->
												
									 </select> 
									 </span>
									 
									 <span class="sub-cntborder">
		                    		      <span  class="easyui-paddingbfpx" >
		                  					  <input id="chkSectionwise" type="checkbox" /> 
		                  					  <label>Section</label>
		                   					  <span  style="margin-left: 2px;">  
		                   					  <input id="chkCellwise" type="checkbox" /> 
		                   					  <label>Cell</label></span>
		                   					  <span  style="margin-left: 2px;">  
		                   					  <input id="chkMchwise" type="checkbox" /> 
		                   					  <label>Equipment</label></span>
		                   					  <input type="hidden" id="hdnCheckVal" value=""/>
		                    		   	 </span>
                    		    	</span> 
								
							  </div>   
							  
			                
			                     </td>
			                  
                    		    
                    		    </tr></table></div>
                    		    </div>
                    </div>		    
     </form>