<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
jQuery(document).ready(function(){
	var circleId=jQuery("#circleId").val();
	jQuery('#submitForm').val('frmAddMchnLink');
	//alert(circleId);
	processGridnew("AddMchnLink_input.circleMst","?q=2&cmbCrcmKeyid="+circleId+"&fromCircle=false&afterclose=frmMchn","grdAddMchnlink","grdAddMchnlinkPager","","","","loadComplete");
	//alert(jQuery("#hdnTemp").val());
});
function chkbox_Machine(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	//alert(rowObject[2]);
	return '<input id="machine_checkbox" name="machine_checkbox" '+ (rowObject[2]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{
	jQuery("#grdAddMchnlink").jqGrid('setCell',rowId,'checkmachnvalue','1');	
}
function chkboxUnCheck(rowId)
{
	jQuery("#grdAddMchnlink").jqGrid('setCell',rowId,'checkmachnvalue','0');
}
function getSelectdRowsMcl(jqGridId,checkBoxColName,ckeckForSelColName){
	//alert('comin');
	var allRows = jQuery("#grdAddMchnlink").jqGrid('getRowData');
	//alert(ckeckForSelColName);
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		//alert(allRows.length);
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		//alert(value);	
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
					jsonArrO += '{';
				for(var colName in row) {
					//alert('colName:'+colName);
					var cellValue = parseJqGridCellValue(row[colName]);	
					//alert("cellValue:"+cellValue);
						if(colName == 'cmbMclkMachineid')
							{
							//alert('ture');
							jsonArrO += '"'+colName +'":"' + cellValue+'"';
							//alert("cellValue1:"+cellValue);
							}
				}
				jsonArrO += ",";
				for(var colName in row) {
					var cellValue = parseJqGridCellValue(row[colName]);	
						if(colName == 'cmbMclkMachineid')
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

function frmAddMchnLink_beforeSubmit()
{
	//alert('b4 sumit');
	  var gridData = '&MclMachineid='+getSelectdRowsMcl('grdAddMchnlink','machine_checkbox','checkmachnvalue');
	 
	 
	  return gridData ; 
}
function loadComplete()
{
	//alert('load cmplt');
	jQuery('.ui-paging-info').css('font-size','12px');
	}
	function frmAddMchnLink_successsCallback()
	{
		jQuery("#grdAddMchnlink").trigger("reloadGrid");
		}
</script>
<form id="frmAddMchnLink">

    
	<div style="padding-top:20px;"  >			
		<table id="grdAddMchnlink" ></table>
			<div id="grdAddMchnlinkPager" >	</div> 
			
	</div>
	
		 
<input type="hidden" id="circleId" value="${requestScope.circleId}"/>
<input type="hidden" id="mode" name="mode" value=""/>

</form>
