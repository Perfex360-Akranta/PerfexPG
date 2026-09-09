<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
jQuery(document).ready(function(){
	var circleId=jQuery("#circleId").val();
	//alert(circleId);
	jQuery('#submitForm').val('frmAddEmpLink');
	processGridnew("AddEmpLink_input.circleMst","?q=2&cmbCrcmKeyid="+circleId+"&fromCircle=false&afterclose=frmempm","grdAddEmplink","grdAddEmplinkPager","","","","loadComplete");
	//alert(jQuery("#hdnTemp").val());
});
function chkbox_Employee(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	//alert(rowObject[2]);
	
	return '<input id="employee_checkbox" name="employee_checkbox" '+ (rowObject[2]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{

	jQuery("#grdAddEmplink").jqGrid('setCell',rowId,'checkempvalue','1');	
}
function chkboxUnCheck(rowId)
{

	jQuery("#grdAddEmplink").jqGrid('setCell',rowId,'checkempvalue','0');
}

function getSelectdRowsMch(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#grdAddEmplink").jqGrid('getRowData');
	//alert(allRows);
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		//alert(value);	
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
					jsonArrO += '{';
				for(var colName in row) {
					//alert(colName);
					var cellValue = parseJqGridCellValue(row[colName]);	
						if(colName == 'cmbEcrlEmpmKeyid')
							{
							jsonArrO += '"'+colName +'":"' + cellValue+'"';
							}
				}
				jsonArrO += ",";
				for(var colName in row) {
					var cellValue = parseJqGridCellValue(row[colName]);	
						if(colName == 'cmbEcrlEmpmKeyid')
							{
							jsonArrO += '"'+colName +'":"' + cellValue+'"';
							}
						
				}
				jsonArrO +=  "},";
				
			}
			
		} 
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
//alert(jsonArrO);
	return jsonArrO; 
	
} 

function frmAddEmpLink_beforeSubmit()
{
	//alert('b4 sumit');
	  var gridData = '&empId='+getSelectdRowsMch('grdAddEmplink','employee_checkbox','checkempvalue');
	 
	 
	  return gridData ; 
}
function loadComplete()
{
	//alert('load cmplt');
	jQuery('.ui-paging-info').css('font-size','12px');
	}
	function frmAddEmpLink_successsCallback()
	{

		jQuery("#grdAddEmplink").trigger("reloadGrid");
		}
</script>
<form id="frmAddEmpLink">

    
	<div style="padding-top:20px;"  >			
		<table id="grdAddEmplink" ></table>
			<div id="grdAddEmplinkPager" >	</div> 
			
	</div>
	
		 
<input type="hidden" id="circleId" value="${requestScope.circleId}"/>
<input type="hidden" id="mode" name="mode" value=""/>

</form>
