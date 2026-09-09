<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmActionPlanDelete');
		jQuery('#submitForm').val('frmActionPlanDelete');
		formatDateBox('dteFromdate','dd-MMM-yyyy');
		formatDateBox('dteTodate','dd-MMM-yyyy');
		fillComboBox("frmActionPlanDelete","cmbActionPlanType","ActionPlanType_Combo.appm","",false);
		fillComboBox("frmActionPlanClosure","cmbDMT","sectionCombo.commonFilter");
		fillComboBox("frmActionPlanClosure","cmbJH","cellCombo.commonFilter");
		fillWithCurrentDate('dteUsrm_validtill');
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});
	

	function ActionPlanGrdbtnActionPlanDelete_onClick(result){	
	    var rowid=result.rowId;
		var btnid=result.btnId;
	    var ActionPlanKeyid=jQuery("#ActionPlanGrd").jqGrid('getCell',rowid,"AplmKeyid");
	  //  alert(ActionPlanKeyid);
	  var r=confirm("Are You Sure To Delete?");
	  if(r==true){
		    processAjaxCalls("ActionPlanDelete_Delete.appm","ActionPlanKeyid="+ActionPlanKeyid,'update_successCallBack','remove_errorCallBack'); 
	  }
	  else{
		   return false;
	  }
		}

	function update_successCallBack(result){
		alert(result.successData.msg);
		jQuery('#ActionPlanGrd').trigger("reloadGrid");
	}
	
	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			processGridnew(url, filterString, "ActionPlanGrd", "Pager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	

	jQuery("#btnViewAp").click(function() {	

		var Type=jQuery("#cmbActionPlanType").combobox("getValue");
		//alert("Type"+Type);
		var fromDate=jQuery("#dteFromdate").datebox('getValue');
		var toDate=jQuery("#dteTodate").datebox('getValue');
		var DMT=jQuery("#cmbDMT").combobox("getValue");
		var JH=jQuery("#cmbJH").combobox("getValue");
		processGridnew("ActionPlanDelete_input.appm","q=2&Type="+Type+"&fromDate="+fromDate+"&toDate="+toDate+"&DMT="+DMT+"&JH="+JH,"ActionPlanGrd","ActionPlanGrdPager","","","","");	
	 	
	});
	
	jQuery("#btnRefresh").click(function(){
		var url = jQuery('#hiddenUrl').val();
		processGridnew(url,"q=2","ActionPlanGrd", "Pager", "", "");
		jQuery("#dteFromdate").datebox("clear");
		jQuery("#dteTodate").datebox("clear");
		jQuery("#cmbActionPlanType").combobox("clear");
		jQuery("#cmbDMT").combobox("clear");
		jQuery("#cmbJH").combobox("clear");
	});
	
</script>
<form id="frmActionPlanDelete">
	<div id="wrapperRpt" style="margin-top: 10px; margin-left: 0px">

	<table>
	<tr>
	
	
			 <td>
		<div>
	<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>                       
	                 </div> 
			    <div style="margin-left:1px;margin-bottom:1px;">
			    
		        <input id="cmbDMT" name="cmbDMT" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			  </div>
			 </td>
			 
			 
			 	 <td>
			 <div>
	<label style="margin-left:20px;margin-bottom: 1px;">JH</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom:1px;">
			    
		        <input id="cmbJH" name="cmbJH" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			        </div>
			 </td>
	
	
	                     <td>
		                 <div  class="easyui-paddingbfpx">
                         <label style="margin-left:10px;margin-bottom:5px;">ActionPlan Type</label> 
						 </div> 
						 <div style="margin-left:10px;margin-bottom:5px;">
						 <input id="cmbActionPlanType" name="cmbActionPlanType" class="easyui-combobox"  style="width:140px">
						 </div>
						 </td>
	    <td>
		<div>
	  	<label style="margin-left:15px;margin-top:0px;">From Date</label>                       
	     </div> 
			    <div style="margin-left:15px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteFromdate" name="dteFromdate" value=""/>
			        </div>
			 </td>
			 
			 <td>
			    <label  style="margin-left:15px;margin-bottom:1px;">To Date</label>  
			    <div style="margin-left:15px;">
			         <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteTodate" name="dteTodate" value=""/>
			          </div>
			  </td>
			  <td>
				<div style="padding-left: 10px; vertical-align: top;margin-top:8px;"> 
				<input type="button" class="easyui-button" id="btnViewAp" name="btnViewAp" value="View" style="height: 25px;" />
			</div>
			</td>
			<td>
				<div style="padding-left:20px; vertical-align: top;margin-top:8px;"> 
				<input type="button" class="easyui-button" id="btnRefresh" name="btnRefresh" value="Refresh" style="height: 25px;" />
			</div>
			</td>
			  </tr>
			  </table>
		<table id="ActionPlanGrd"></table>
		<div id="Pager"></div>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
