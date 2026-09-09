<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmNearMissDelete');
		jQuery('#submitForm').val('frmNearMissDelete');
		formatDateBox('dteFromdate','dd-MMM-yyyy');
		formatDateBox('dteTodate','dd-MMM-yyyy');
		fillComboBox('frmNearMissDelete','cmbNmrtSeveritypotentialid', 'comboSeverity.commonFilter');
		fillComboBox("frmNearMissDelete","cmbDMT","sectionCombo.commonFilter");
		fillComboBox("frmNearMissDelete","cmbJH","cellCombo.commonFilter");
		fillWithCurrentDate('dteUsrm_validtill');
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});

	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			processGridnew(url, filterString, "NearMissDeleteGrd", "Pager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	

	function NearMissDeleteGrdbtnNearMissDelete_onClick(result){	
	    var rowid=result.rowId;
		var btnid=result.btnId;
	    var NearMissKeyid=jQuery("#NearMissDeleteGrd").jqGrid('getCell',rowid,"NmrnKeyid");
	    //alert(LossKeyid);
	    
	    var r=confirm("Are You Sure Want To Delete?");
	    if(r==true)
	    {
	    	
		    processAjaxCalls("NearMissDelete_Delete.appm","NearMissKeyid="+NearMissKeyid,'update_successCallBack','remove_errorCallBack');
	    	
	   } 
	    else{
	    	
	    	return false;
	    } 
	    
		}

	function update_successCallBack(result){
		alert(result.successData.msg);
		jQuery('#NearMissDeleteGrd').trigger("reloadGrid");
	}


	jQuery("#btnview").click(function() {	
		var Type=jQuery("#cmbNmrtSeveritypotentialid").combobox("getValue");
		//alert("Type"+Type);
		var fromDate=jQuery("#dteFromdate").datebox('getValue');
		var toDate=jQuery("#dteTodate").datebox('getValue');
		var DMT=jQuery("#cmbDMT").combobox("getValue");
		var JH=jQuery("#cmbJH").combobox("getValue");
		processGridnew("NearMissDelete_input.appm","q=2&Type="+Type+"&fromDate="+fromDate+"&toDate="+toDate+"&DMT="+DMT+"&JH="+JH,"NearMissDeleteGrd","NearMissDeleteGrdPager","","","","");	
	});
	
	

	jQuery("#btnRefresh").click(function(){
		var url=jQuery('#hiddenUrl').val();
		processGridnew(url,"q=2","NearMissDeleteGrd", "Pager", "", "");
		jQuery("#dteFromdate").datebox("clear");
		jQuery("#dteTodate").datebox("clear");
		jQuery("#cmbDMT").combobox("clear");
		jQuery("#cmbJH").combobox("clear");
	});

	
</script>
<form id="frmNearMissDelete">
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
						      <label style="margin-left:10px;margin-bottom:5px;">NearMiss Type</label> 
						                </div> 
						     <div style="margin-left:10px;margin-bottom:5px;">
						           <input id="cmbNmrtSeveritypotentialid" name="cmbNmrtSeveritypotentialid" class="easyui-combobox"  style="width:120px">
						                </div>
						                </td>
	    <td>
		<div>
	  	<label style="margin-left:0px;margin-top:0px;">From Date</label>                       
	     </div> 
			    <div style="margin-left:0px;">
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
				<input type="button" class="easyui-button" id="btnview" name="btnview" value="View" style="height: 25px;" />
			</div>
			</td>
			  <td>
				<div style="padding-left:20px; vertical-align: top;margin-top:8px;"> 
				<input type="button" class="easyui-button" id="btnRefresh" name="btnRefresh" value="Refresh" style="height: 25px;" />
			</div>
			</td>
			
			  </tr>
			  </table>
		<table id="NearMissDeleteGrd"></table>
		<div id="Pager"></div>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
