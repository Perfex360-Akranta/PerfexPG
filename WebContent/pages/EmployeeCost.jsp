<script type="text/javascript" src="js/jquery.easyui.min.js"></script> 

 <script type="text/javascript">	
		jQuery.noConflict();			
		
		jQuery(document).ready(function(){
		jQuery('#submitForm').val('frmEmployeeCost');		
		//fillComboBox("frmEmployeeCost","cmbempcEmployeeid","employee.commonFilter");
		
		var type = jQuery('#hdnType').val();
		if (type == "Employee") {
			jQuery('#lblEmployee').text('Employee');
			jQuery('#cmbdeptdesid').css('display','block');
			jQuery('#lbldeptdesid').css('display','block');	
			fillComboBox("frmEmployeeCost","cmbempcDepartment","combo_department.ec");
			fillComboBox("frmEmployeeCost","cmbempcDesignation","combo_designation.ec");
			//processGridnew('employeeCostReport_view.ec',"?q=2&type=Employee&deptId=&desId=&empId=EMPTY","EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}
		else if (type == "Utility") {
			jQuery('#lblEmployee').text('Utility');			
			jQuery('#cmbdeptdesid').css('display','none');
			jQuery('#lbldeptdesid').css('display','none');
			fillComboBox("frmEmployeeCost","cmbempcEmployeeid","combo_utilities.ec");			
			//processGridnew('employeeCostReport_view.ec',"?q=2&type=Utility&utilityId=EMPTY","EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}
		else if (type == "Contractor") {
			jQuery('#lblEmployee').text('Vendor');			
			jQuery('#cmbdeptdesid').css('display','none');
			jQuery('#lbldeptdesid').css('display','none');
			fillComboBox("frmEmployeeCost","cmbempcEmployeeid","combo_vendor.ec");			
			//processGridnew('employeeCostReport_view.ec',"?q=2&type=Contractor&contractorId=EMPTY","EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}

			
		var empId = jQuery('#cmbempcEmployeeid').combobox('getValue');

		if (!empId==null || !empId =="" || !empId == " ") { 
			if (type == "Employee") 
				processAjaxCalls("employeeCost_getEmpDeptDes.ec","?q=2&empId="+empId,"empIdRecallSuccess","empIdRecallError");			
			else
				fnView();
		}		
		
});

	jQuery('#btnView').click(function() {
		//alert("clickeddd");
		fnView();		
	});

	function fnView() {
		var type = jQuery('#hdnType').val();
		//alert(type);
		if (type == "Employee") 
		{
			var deptId = jQuery('#cmbempcDepartment').combobox('getValue');
			var desId = jQuery('#cmbempcDesignation').combobox('getValue');
			var empId = jQuery('#cmbempcEmployeeid').combobox('getValue');
			
			if (deptId==null || deptId == "" )	{
				alert("Select Department"); return; }
			if (desId==null || desId == "" )	{
				alert("Select Designation"); return;	}

			processGridnew('employeeCostReport_view.ec',"?q=2&type="+type+"&deptId="+deptId+"&desId="+desId+"&empId="+empId,"EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}
		else if (type == "Utility") {
			var utilityId = jQuery('#cmbempcEmployeeid').combobox('getValue');			
			//if (utilityId==null || utilityId == "" )	{
			//	alert("Select Utility"); return;	}
			processGridnew('employeeCostReport_view.ec',"?q=2&type="+type+"&utilityId="+utilityId,"EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}				
		else if (type == "Contractor") {
			var vendorId = jQuery('#cmbempcEmployeeid').combobox('getValue');			
			if (vendorId==null || vendorId == "" )	{
				alert("Select Vendor"); return;	}
			processGridnew('employeeCostReport_view.ec',"?q=2&type="+type+"&vendorId="+vendorId,"EmployeeCostGrid","EmployeeCostPager","","","","ecGridLoad","ecGridError");
		}				
		
	}

function btnFormatter(id, options, rowObject){
	var id = options.rowId;
  	return '<input type="button" class="easyui-button" value="Save" onclick="saveData(\''+id + '\');"/>';  	
}

function dteFormatter(cellValue, options, rowObject) {	
	var id = options.rowId;	
  	return '<input id=\'dte'+id + '\' class="easyui-datebox" onclick="selectDate(\'dte'+id + '\');" value="'+cellValue+'">';  	
  	var dteId = "dte"+id;
}


function selectDate(id) {
	//alert(id);
	var rowId = jQuery("#EmployeeCostGrid").jqGrid('getGridParam', 'selrow');
	var rowData = jQuery("#EmployeeCostGrid").jqGrid('getRowData',rowId);
	//alert(jQuery('#'+id).datebox('getValue'));
	formatDateBox(id,'dd-MMM-yyyy');
}

function saveData(id) { 
	//alert(id);
	
	var empId = jQuery('#cmbempcEmployeeid').combobox('getValue');

	if (empId==null || empId =="" || empId == " ") {
		var type = jQuery('#hdnType').val();
		if (type == "Employee") 
			alert("Select Employee" );		
		else if (type == "Utility") 
			alert("Select Utility" );
		else if (type == "Contractor") 
			alert("Select Contractor" );
		return;
	}
			

	var rowId = jQuery("#EmployeeCostGrid").jqGrid('getGridParam', 'selrow');
	var rowData = jQuery("#EmployeeCostGrid").jqGrid('getRowData',rowId);

	var empcId =rowData.empcKeyid;
	var empmId =rowData.empmKeyid;	
	var normalCost = rowData.empcCostperhour;
	var otCost = rowData.empcOtcostperhour;
	var callOutCost = rowData.empcCalloutcostperhour;

/*	var normCostId = rowId +"_empcCostperhour";
	var otCostId = rowId +"_empcOtcostperhour";
	var callOutCostId = rowId +"_empcCalloutcostperhour";	
	var normalCost = jQuery('#'+normCostId).val();
	var otCost = jQuery('#'+otCostId).val();
	var callOutCost = jQuery('#'+callOutCostId).val();
*/	
	
	var dteId = "dte"+rowId;
	var effectiveFrom = jQuery('#'+dteId).datebox('getValue');

	var dataStr = "&empcId="+empcId+"&empmId="+empmId+"&normalCost="+normalCost+"&otCost="+otCost+"&callOutCost="+callOutCost+"&effectiveFrom="+effectiveFrom;
	//alert(empcId +'- ' +empmId+'- ' +normalCost+'- ' +otCost+'- ' +callOutCost+'- ' +effectiveFrom);

	var formId = jQuery('#submitForm').val(); //defined in classic.jsp
	var url = "employeeCost_save.ec?q=2" ;//defined in classic.jsp
	
	if (normalCost == null || normalCost == " " || parseInt(normalCost) == "0" ) {
		alert("Enter Normal Cost");
			return ;
	}
	if (effectiveFrom == null || effectiveFrom == " " )	{
		alert("Select Effective From Date");
		return ;
	}

	var type = jQuery('#hdnType').val();	 

	url+= '&type='+type;
	
	if (type == "Contractor") {
		vendorId = jQuery('#cmbempcEmployeeid').combobox('getValue');
		url+= '&vendorId='+vendorId;
	}
	
	//alert(jQuery('#submitForm').val());
	
	if (empcId==null || empcId == " " )
		url+= '&saveMode=Save';
	else
		url+= '&saveMode=Update';	
	
	url+= dataStr;
	//alert(url);
	
	if(formId.length > 0 )		
		saveForm(formId,url);
}

function frmEmployeeCostcmbempcEmployeeid_onSelect(record)
{	
	//alert(record.id);
	var type = jQuery('#hdnType').val();
	if (type == "Employee") 
		processAjaxCalls("employeeCost_getEmpDeptDes.ec","?q=2&empId="+record.id,"empIdRecallSuccess","empIdRecallError");			
}

function empIdRecallSuccess(result)
{
	//alert(result.empDetails.deptId);
	jQuery('#cmbempcDepartment').combobox('setValue',result.empDetails.deptId);	
	jQuery('#cmbempcDesignation').combobox('setValue',result.empDetails.desId);
	jQuery('#cmbempcDepartment').combobox('disable');
	jQuery('#cmbempcDesignation').combobox('disable');
	fnView();
}
function empIdRecallError()
{alert("error");	}

function frmEmployeeCost_successsCallback(result)
{	
	//alert("succcccc");	
	jQuery("#EmployeeCostGrid").jqGrid().trigger("reloadGrid");	
}

function frmEmployeeCost_exceptionCallback(result)
{	
	//alert("erorrrrr");	
	alert(result.msg);
	//jQuery("#tblEmpCostEstGrid").jqGrid().trigger("reloadGrid");
}

	
function ecGridLoad()
{	//alert("loadeddddd");
	var rowCnt = jQuery("#EmployeeCostGrid").getGridParam("reccount");
	//jQuery('#lblNoofrecords').text("No of records processed : "+rowCnt);	
	if (parseInt(rowCnt) > 0 )
	{
		jQuery('#cmbempcEmployeeid').combobox('disable');
		jQuery('#cmbempcDepartment').combobox('disable');
		jQuery('#cmbempcDesignation').combobox('disable');		
	}
}
function ecGridError() {
	alert("error");
}

function  frmEmployeeCostcmbempcDepartment_onLoadSuccess() {	
	var deptId = jQuery('#cmbempcDepartment').combobox('getValue');
	var desId = jQuery('#cmbempcDesignation').combobox('getValue');	
	var url = "combo_getEmployee.ec?q=2&deptId="+deptId+"&desId="+desId;		
	fillComboBox("frmEmployeeCost","cmbempcEmployeeid",url);	
}

function  frmEmployeeCostcmbempcDepartment_onSelect(record) {
	var deptId = record.id;
	var desId = jQuery('#cmbempcDesignation').combobox('getValue');
	var url = "combo_getEmployee.ec?q=2&deptId="+deptId+"&desId="+desId;
	//alert( url);
	reloadCombo("frmEmployeeCost","cmbempcEmployeeid",url);	
}

function  frmEmployeeCostcmbempcDesignation_onSelect(record) {
	var deptId = jQuery('#cmbempcDepartment').combobox('getValue');
	var desId = record.id;
	var url = "combo_getEmployee.ec?q=2&deptId="+deptId+"&desId="+desId;	
	reloadCombo("frmEmployeeCost","cmbempcEmployeeid",url);
}

function employeeRecallSuccess(result)
{
	alert(result);
}
</script>	

<div> <input type="hidden" id="hdnType" value="${requestScope.Type}"></div>

<form id="frmEmployeeCost" name="frmEmployeeCost">
<div title="Employee Cost" style="padding:0px;" id="employeeCostEstDiv">	

<div style="float:left;margin-left: px;">
	<div class="easyui-paddingbfpx" style="float:left;margin-left: 5px;">
	   <span id="lbldeptdesid" style="display: none">      			
       		<span  style="margin-left: px;"><label class="mandatory-lbl">Department</label></span>
       		<span  style="margin-left: 240px;"><label class="mandatory-lbl">Designation</label></span>
       	</span>       	
      </div>
        <br>
        <div id="cmbdeptdesid" style="display: none">
	       <div style="float:left;margin-left: 5px;"> 
	           <input id="cmbempcDepartment" name="cmbempcDepartment" class="easyui-combobox"  style="width:300px;" value=""  >                                
	       </div>
	       <div style="float:left;margin-left: 10px;">
	           <input id="cmbempcDesignation" name="cmbempcDesignation" class="easyui-combobox"  style="width:300px;" value=""  >
	       </div>
	    </div>
     </div>
</div>  
   <div class="easyui-paddingbfpx" style="float:left;margin-left: px;margin-top:px">
    <div class="easyui-paddingbfpx" style="float:left;margin-left: 5px;">
	     <div style="float:left; margin-left: px;">
	     	<span  style="margin-left: 5px;"><label id="lblEmployee">Employee</label></span>
	     </div> 
	     <br>        
	       <div style="float:left;margin-left: 5px;"> 
	       <input id="cmbempcEmployeeid" name="cmbempcEmployeeid" class="easyui-combobox"  style="width:300px;" value="${requestScope.keyid}" >
	    </div>     
		<div style="float:left; margin-left: 10px;">
			<input type="button" id="btnView" name="btnView" class="easyui-button" value="View" />			
		</div>
	</div>
	</div>

	
	<div class="clear"></div>					
		<div class="floatleft" style="margin:1%;">
			<div><table id="EmployeeCostGrid" class="floatleft" width="400px" ></table> </div><!-- ****JqGrid**** -->
			<div id="EmployeeCostPager"></div> 
</div>
</form>