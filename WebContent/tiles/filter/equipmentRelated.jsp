<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<script type="text/javascript">
jQuery(document).ready(function(){
	
	 if(screen.width <= 1024){
		 jQuery('.easyui-combobox').css('width','286px');
		 jQuery('.easyui-datebox').css('width','120px');
		 jQuery('#tblAdvanced').css('width','80%');
		 jQuery('#cboCategorized').css('width','220px');
		 jQuery('#Filter').css('height','418px');
		 
	 }	
	initialiseForm('frmEquipmentRelated');
	//fillComboBox("frmEquipmentRelated","cmbMchmMachinerank","Combo_MachineRank.eqp");
	if(  !jQuery('#cmbMchmPurpose').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbMchmPurpose","Combo_Purpose.eqp");
	if(  !jQuery('#cmbMchmCategory').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbMchmCategory","Combo_Category.eqp");
	if(  !jQuery('#cmbMchmSubcategory').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbMchmSubcategory","Combo_SubCategory.eqp");
	if(  !jQuery('#cmbClisShiftid').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbClisShiftid","combo_clitShift.jhclit");	
	if(  !jQuery('#cmbMchmJhstep').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbMchmJhstep","Combo_JhStep.eqp");
	if(  !jQuery('#cmbsupervisor').is(':disabled') )
		fillComboBox("frmEquipmentRelated","cmbsupervisor","employee.commonFilter");

	formatDateBox('dteInsFromDate','dd-MMM-yyyy');
	formatDateBox('dteInsToDate','dd-MMM-yyyy');
	formatDateBox('dteamcFromDate','dd-MMM-yyyy');
	formatDateBox('dteamcToDate','dd-MMM-yyyy');
});
	function getRelatedFilterValues()
	{
	
		var filterStr="";
		
		var cmbJHStep = jQuery("#cmbMchmJhstep").combobox("getValue");
		filterStr += "&cmbJHStep="+cmbJHStep;
		
		
		/*var cmbMachineRankid = jQuery("#cmbMchmMachinerank").combobox("getValue");
		filterStr += "&cmbMachineRankid="+cmbMachineRankid;*/

		
		var cmbShiftid = jQuery("#cmbClisShiftid").combobox("getValue");
		filterStr += "&cmbShiftid="+cmbShiftid;
		
		
		var cmbSupervisorid = jQuery("#cmbsupervisor").combobox("getValue");
		filterStr += "&cmbSupervisorid="+cmbSupervisorid;
		
		
		var cmbPurposeid = jQuery("#cmbMchmPurpose").combobox("getValue");
		filterStr += "&cmbPurposeid="+cmbPurposeid;
		
		
		var cmbCategoryid = jQuery("#cmbMchmCategory").combobox("getValue");
		filterStr += "&cmbCategoryid="+cmbCategoryid;
		
		
		var cmbSubCategoryid = jQuery("#cmbMchmSubcategory").combobox("getValue");
		filterStr += "&cmbSubCategoryid="+cmbSubCategoryid;
	
		
		var dtInstallFromDate = jQuery("#dteInsFromDate").datebox("getValue");
		filterStr += "&dtInstallFromDate="+dtInstallFromDate;
		
		
		var dtInstallToDate = jQuery("#dteInsToDate").datebox("getValue");
		filterStr += "&dtInstallToDate="+dtInstallToDate;		
		
		
		var dtAmcFromDate = jQuery("#dteamcFromDate").datebox("getValue");
		filterStr += "&dtAmcFromDate="+dtAmcFromDate;
		
		var dtAmcToDate = jQuery("#dteamcToDate").datebox("getValue");
		filterStr += "&dtAmcToDate="+dtAmcToDate;
		
		var cmbCategorized = getFieldValue("cboCategorized");
		filterStr += "&cmbCategorized="+cmbCategorized;
		
		var txtAmcRenewal = jQuery("#txtAmcRenewal").val();
		filterStr += "&txtAmcRenewal="+txtAmcRenewal;
		
		var txtWarrantyExpires = jQuery("#txtWarrantyExpires").val();
	//	filterStr += "&WarrantyExpiresFromDate="+txtWarrantyExpires;
	//	filterStr += "&WarrantyExpiresToDate="+ToDate;
		filterStr += "&txtWarrantyExpires="+txtWarrantyExpires;		
		
		var actType = "";
		actType += "&BREAKDOWN="+getChkBoxVal('chkbreakdown');
		actType += "&PREVENTIVE="+getChkBoxVal('chkplan');
		actType += "&IMPROVEMENT="+getChkBoxVal('chkimprovement');
		actType += "&ABNORMALITY="+getChkBoxVal('chkAbnormality');
		actType += "&OPL="+getChkBoxVal('chkopl');
		actType += "&GENERAL="+getChkBoxVal('chkgenMaint');
		actType += "&KAIZEN="+getChkBoxVal('chkKaizen');
		actType += "&UNPLANNED="+getChkBoxVal('chkUnplanned');
		
		filterStr += "&actType="+actType;
		
		filterStr += "&gropByEqp=" + getChkBoxVal('chkEqpGrpBy'); 
		
		return filterStr;
	}

	
	 
	 function frmEquipmentRelatedcmbMchmCategory_onSelect(record)
	 {			
		 jQuery("#cmbMchmSubcategory").combobox('clear');	
		 reloadCombo("frmEquipmentRelated","cmbMchmSubcategory","Combo_SubCategory.eqp?catagory="+record.id);	 					
	}
	 function getChkBoxVal(Id) {		
			if(jQuery('#'+Id).is(':checked') == true)  		
				return 1;
			else
				return 0;
		}
		
</script>
	<form id= "frmEquipmentRelated" name = "frmEquipmentRelated">
					
			<div title="Equipment Related" >
				<div class="sub-header">Regular</div>
				<div style="margin-top: 1px; padding-left: 12%;" >
				<table>
					<tr>
						<td valign="top" >
							<div  class="easyui-paddingbfpx">
								<label>Aut.Maint.Step</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbMchmJhstep" name="cmbMchmJhstep" class="easyui-combobox" clear="false"  style="width:350px" value=""  >                       
							</div>
							<div  class="easyui-paddingbfpx">
								<label>Purpose</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								 <input id="cmbMchmPurpose" name="cmbMchmPurpose" class="easyui-combobox"  style="width:350px" value=""  >                       
							</div>
						</td>
						<td style="padding-left: 20px;">
							<div  class="easyui-paddingbfpx">
								<label>Shift</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbClisShiftid" name="cmbClisShiftid" class="easyui-combobox"  style="width:350px" value=""  >                       
							</div>
							<div  class="easyui-paddingbfpx">
								<label>Supervisor</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbsupervisor" name="cmbsupervisor" class="easyui-combobox"  style="width:350px" value=""  >                       
							</div>
						</td>
					</tr>
				</table>
			</div>
			
				<div class="sub-header">Advanced Filter Criteria</div>
					<div  style="margin-top: 0px; padding-left: 12%;">
						
				<table id="tblAdvanced" style="width:61%; ">
					<tr>
						<td valign="top">
							<div  class="easyui-paddingbfpx">
								<label>Category</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								<input id="cmbMchmCategory" name="cmbMchmCategory" class="easyui-combobox"  style="width:350px" value=""  >                       
							</div>
							<div  class="easyui-paddingbfpx">
								<label>Sub Category</label>                       
							</div> 
							<div class="easyui-paddingbfpx"> 
								 <input id="cmbMchmSubcategory" name="cmbMchmSubcategory" class="easyui-combobox"  style="width:350px" value=""  >                       
							</div>
							<div  class="easyui-paddingbfpx">
								<label>Ins From Date</label>
								<span  style="margin-left: 106px;"><label>To Date</label></span>
							</div> 
							<div class="easyui-paddingbfpx"> 
								 <input id="dteInsFromDate" clear="false"  class="easyui-datebox" clear:false style="width:160px;"/>
								 <span  style="margin-left: 26px; margin-left: 23px\9;"> 
								 <input id="dteInsToDate" clear="false"  class="easyui-datebox"  style="width:160px;"/>
								 </span> 
							</div>
						</td>
						<td valign="top" style="padding-left: 20px;">
							<div class="easyui-paddingbfpx">
									  <label>AMC From Date</label>
							</div> 
							<div class="easyui-paddingbfpx"> 
							  <input id="dteamcFromDate" class="easyui-datebox" clear="false"  style="width:170px;"/>
							</div>
							<div class="easyui-paddingbfpx">
								<label>AMC Renewal</label>  							                     
							</div> 
							<div class="easyui-paddingbfpx"> 
							 <input id="txtAmcRenewal" name="txtAmcRenewal" class="easyui-text"  style=" width : 113px;" value=""  ><label>(Months)</label>		
							</div>
							<div class="easyui-paddingbfpx" style=" padding-top: 5px; padding-top: 0px\9;"> 
									 <label>BD Categorized</label>                       
							</div> 
							<div class="easyui-paddingbfpx" > 
								<select id="cboCategorized" class="easyui-combobox" name="type" style="height : 23px; width : 170px;" required="true" >
									<option value=""> All</option>
									<option value="GOLD"> Gold</option>
									<option value="SILVER"> Silver</option>
									<option value="BRONZE"> Bronze</option>
									
								</select> 
								
							</div>
					    </td>
					    <td  valign="top" style="padding-left: 11px;">
					    <div class="easyui-paddingbfpx"> 
					    <label>To Date</label>
					    </div>
					   <div class="easyui-paddingbfpx"> 
					   <input id="dteamcToDate" class="easyui-datebox" clear="false"   style="width:170px;"/>
					    </div>
					    
					    <div class="easyui-paddingbfpx"> 
					    <label>Warranty Expires</label>
					    </div>
					   <div class="easyui-paddingbfpx"> 
					  <input id="txtWarrantyExpires" name="txtWarrantyExpires" class="easyui-text"  style="width:113px" value=""  ><label>(Months)</label>	
					  </div>
					    <div class="easyui-paddingbfpx" style="padding-top: 24px;"> 
					     <input id="chkEqpGrpBy" name="chkEqpGrpBy" value=""  type="checkbox"/><label>Group By Equipment</label>           
					     </div>
					    </td>
					</tr>
				</table>
						<div class="" style="width:95%; margin-top: 5px">
							<div style="padding-right:px;" id="divActivity">
							<label> <b>Activity Type:</b></label>
							<input id="chkbreakdown" name="chkbox" value="breakdown"  type="checkbox"/><label>BreakDown</label>
							<span><input id="chkplan" name="chkbox" value="planedMaintaince"  type="checkbox"/><label>Planned Maintaince</label></span>
							<span><input id="chkimprovement" name="chkbox" value="Improvement"  type="checkbox"/><label>Kaizen</label></span>
							<span><input id="chkAbnormality" name="chkbox" value="Abnormality"  type="checkbox"/><label>Abnormality</label></span>
							<span><input id="chkopl" name="chkbox" value="opl"  type="checkbox"/><label>One Point Lesson</label></span>
							<span><input id="chkgenMaint" name="chkbox" value="genMaint"  type="checkbox"/><label>Equipment Activity</label></span>
<!--							<span><input id="chkKaizen" name="chkbox" value="chkKaizen"  type="checkbox"/><label>Kaizen</label></span>-->
							<span><input id="chkUnplanned" name="chkbox" value="Unplanned"  type="checkbox"/><label>UnPlanned Maintaince</label></span>
							</div>
						</div>
					   
		</div>
		</div>
		
	</form>