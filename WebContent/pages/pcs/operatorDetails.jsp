<script type="text/javascript">
	jQuery(document).ready(function(){
		initialiseForm('frmOperatorDetails');
		jQuery('#submitForm').val('frmOperatorDetails');

		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var dataStr ='?q=2&sectId='+sectId;	
		fillComboBox("frmPcsEntry","cmbPopdPlemployeeid","combo_Operator.pcs"+dataStr );

		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsid =rowData.txtPldetailsid;		
		processGridnew('pcsEmployeeGrid_view.pcs','?q=2&pldetailsid='+pldetailsid,"pcsEmployeeGrid","pcsEmployeePager","PCS Employee","pcsEmployeeGrid_dblClick","","pcsEmployeeGrid_loadComplete","pcsEmpGridError");
	});
	jQuery('#btnAddEmp').click(function() {
		var formId = jQuery('#submitForm').val(); 
		var Plmasterid = jQuery('#hdnPlmMasterId').val();		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var Pldetailsid =rowData.txtPldetailsid;
		//alert(Pldetailsid);
		if (Pldetailsid == null || Pldetailsid=="" || Pldetailsid==" ") {
			alert("Proudct entry must to Enter Operator Details");
			jQuery('#cmbPopdPlemployeeid').combobox('clear');
			return;
		}			
		var url = "pcsEmployee_save.pcs" ;
		url+= "?q=2&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid;
		url += "&saveMode=Save";		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);		
	});


	jQuery('#btnDelEmp').click(function() {
		var formId = jQuery('#submitForm').val();
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsId =rowData.txtPldetailsid;
		//alert(Pldetailsid);
		var rowId = jQuery("#pcsEmployeeGrid").jqGrid('getGridParam', 'selrow');
		if (rowId == null || rowId < 0) {	return; }		
		var rowData = jQuery("#pcsEmployeeGrid").jqGrid('getRowData',rowId);
		var empId = rowData.cmbPopdPlemployeeid;
		//alert(empId);
		if (empId=="" || empId==" ") {
			alert("Select the Employee to delete");
			return;
		}
		var url = "pcsEmployee_delete.pcs" ;
		url+= "?q=2&empId="+empId+"&pldetailsId="+pldetailsId;				
		//alert(url);
		if(formId.length > 0 )
			deleteRecord(formId,url);		
	});
	function pcsEmployeeGrid_loadComplete()	{
	}
	function frmOperatorDetails_successsCallback(result) {	
		jQuery('#cmbPopdPlemployeeid').combobox('clear');	
		jQuery("#pcsEmployeeGrid").jqGrid().trigger("reloadGrid");
	}
	function frmOperatorDetails_deleteSuccessCallback(result) {
		jQuery('#cmbPopdPlemployeeid').combobox('clear');
		jQuery("#pcsEmployeeGrid").jqGrid().trigger("reloadGrid");
	}
</script>
	<form id="frmOperatorDetails" >
				
	<label style="padding-left: 10px;" class="mandatory-lbl"> Operator</label>		
	<div class="easyui-paddingbfpx" style="padding-left: 5px;">			
		 <input type="text" id="cmbPopdPlemployeeid" name="cmbPopdPlemployeeid" class="easyui-combobox" value="" style="width: 220px;"/>
		 <span style="margin-left:5px;">
		 	<input type="button" id="btnAddEmp" name="btnAddEmp" class="easyui-button" value="Add" style="width: 50px;">
	   		<input type="button" id="btnDelEmp" name="btnDelEmp" class="easyui-button" value="Delete" style="width: 50px;">
	   	</span>		    
	</div>
	
	<div align="left" style="float:left;padding-left: 5px;" class="pcsEmployeeDiv">			
			<table id="pcsEmployeeGrid" style="float: left: ;"></table>
	</div>		
	</form>   