<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
jQuery(document).ready(function(){	
	initialiseForm("frmJHClitFilter");
	fillComboBox("frmBD","cmbprodcngroup","productiongrpp.commonFilter" );
	fillComboBox("frmEquipmentRelated","cmbMchmJhstep","Combo_JhStep.eqp");
	numericTextBox('txtTo');
	numericTextBox('txtFrom');
	jQuery("#txtTo").hide();
});
	function getRelatedFilterValues()
	{
		var filterStr;
			
		var cmbjhfreq = jQuery("#cbojhfreq").val();			
		filterStr = "&cmbjhfreq="+cmbjhfreq;
	
		var cmbjhduration = jQuery("#cboJhduration").val();		
		filterStr += "&cmbjhduration="+cmbjhduration;	
		
		var cmbprodcngroup = jQuery("#cmbprodcngroup").combobox("getValue");
		filterStr += "&cmbprodcngroup="+cmbprodcngroup;

		var cmbJHStep = jQuery("#cmbMchmJhstep").combobox("getValue");
		filterStr += "&cmbJHStep="+cmbJHStep;	
		
		var txtDurFrom = jQuery("#txtDurFrom").val();
		filterStr += "&txtDurFrom="+txtDurFrom;
	
		var txtDurTo = jQuery("#txtDurTo").val();
		filterStr += "&txtDurTo="+txtDurTo;
			
		return filterStr;
	}


	jQuery("#cboJhduration").change(function() {
		// assign the value to a variable, so you can test to see if it is working
		var selectVal = jQuery("#cboJhduration :selected").val();

		
		if(selectVal==6){
			jQuery("#txtDurFrom").val(''); 
			jQuery("#txtDurTo").val('');
			jQuery("#txtTo").show();
			
		}
		
		else{
			jQuery("#txtDurFrom").val(''); 
			jQuery("#txtDurTo").val('');
			jQuery("#txtTo").hide();
		}
		//alert(selectVal);
		
		});

	</script>
<form id="frmJHClitFilter" name="frmJHClitFilter">
						<!--   JH (CLIT) Standard Report Tab	-->
						<div title="JH (CLIT) Standard Report" style="padding:10px;">
						
						    <div class="sub-header">Advanced Filter Criteria</div>
			                     	<div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Frequency</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="cbojhfreq" class="easyui-combobox" name="cbojhfreq" style="width:160px;" required="true">
												<option value="X"> Day and Above</option>
												<option value="S"> Shift Wise</option>
									 </select> 
							  </div>
							  
							    <div  class="easyui-paddingbfpx">
                  					 <label>Duration</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="cboJhduration" class="easyui-combobox" name="cboJhduration" style="width:160px;" required="true">
												<option value="0">  </option>
												<option value="1"> = </option>
												<option value="2"> > </option>
												<option value="3"> < </option>
												<option value="4"> >= </option>
												<option value="5"> <= </option>
												<option value="6">Between </option>
									 </select> 
									 
									 <span id="txtFrom" style="margin-left: 20px;"><input type="text" class="easyui-text" id="txtDurFrom" maxlength="3" name="txtDurFrom" style="width:50px" value=""/></span>
									 <span id="txtTo" style="margin-left: 20px;"><input type="text" class="easyui-text" id="txtDurTo" maxlength="3" name="txtDurTo" style="width:50px" value=""/></span>
							  </div>
							  
							  
					                   
					                   
					                   <div  class="easyui-paddingbfpx">
								<label>Aut.Maint.Step</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbMchmJhstep" name="cmbMchmJhstep" class="easyui-combobox" clear="false"  style="width:350px" value=""  />                       
							</div>
							
							
							 <div  class="easyui-paddingbfpx">
								<label>Production Group</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbprodcngroup" name="cmbprodcngroup" class="easyui-combobox" clear="false"  style="width:350px" value="" />                       
							</div>
							
							  </td></tr></table></div>
						</div>
						
</form>