<script type="text/javascript">

		jQuery.noConflict();
		jQuery(document).ready(function(){
			jQuery('#submitForm').val('frmEquipmentCircle');
			var url = jQuery('#hiddenUrl').val();
			var mode = "VIEW";
			var dataString ="";
			
			
			viewGrid(url,"?q=");										
		});

		function viewGrid(url,filterString)
		{		
			var mchId = jQuery("#hdnMachineId").val();
			filterString += "&active=Y";
			filterString += "&keyid="+mchId;
			
			processGridnew("circleGrid_getCol.eqp",filterString+'&closeOnSave=true',"eqpCircleGrd","pagereqp","","eqpDoubleClick","","loadComplete");			
			return true;	
		}

		function loadComplete()
		{
			
		}
		function eqpDoubleClick(id)
		{	
			var rowData = jQuery("#eqpCircleGrd").jqGrid('getRowData',id);
			var keyid = rowData.MCHM_KEYID;
			//navigateToNextForm('equipment_input.eqp'+'?keyId='+keyid+'&eqpMode=update',"Equipment Master");
		}
		
		
		function cboxFormatter(id, options, rowObject)
		 {					
			var rowId = options.rowId;	
			if(rowObject[1]=="1")
				{
				chkboxCheck(rowId);
				}
			return '<input id="catg_checkbox" name="catg_checkbox" '+ (rowObject[1]=="1" ? 'checked':'0') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
		 }
				
		function chkboxCheck(rowId)
		{
			jQuery("#eqpCircleGrd").jqGrid('setCell',rowId,'ChkVal','1');		
			
		}
		function chkboxUnCheck(rowId){
			jQuery("#eqpCircleGrd").jqGrid('setCell',rowId,'ChkVal','0');	
		}
			
		jQuery("#btnSave").click(function(){
			
			var mchId=jQuery('#hdnMachineId').val();	
			
			saveForm('frmEquipmentCircle','Circle_save.eqp?mchId='+mchId); 
		});
					
		function frmEquipmentCircle_beforeSubmit(){
			
			var gridData = '&eqpCircle='+getSelectdRows('eqpCircleGrd','select','ChkVal');
			return gridData; 	
			
		}
		
		function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
			
			var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
			var jsonArrO='[';
			for( var i = 0; i < allRows.length;i++){
				var row = allRows[i];
				var value = row[ckeckForSelColName];		
				if( value != null  &&  value.trim()  != ""){			
					if(value == '1')				
					{
						jsonArrO += '{';
					for(var colName in row) {
						var cellValue = parseJqGridCellValue(row[colName]);
					
							if(colName == 'txtMclkCircleid')								
								jsonArrO += '"'+colName +'":"' + cellValue+'"';
							
					}
					jsonArrO +=  "},";
					
				}
			} 
			}
			//jsonArrO = jsonArrO.slice(0, -1) ;
			jsonArrO = jsonArrO.slice(0, -1) + "]";
			jsonArrO = (jsonArrO != ']'?jsonArrO:"");
			
			return jsonArrO; 
		}
</script>
<form id="frmEquipmentCircle">

<div >

	<div class="clear"></div>

<table id="eqpCircleGrd" ></table>
<div id="pagereqp"></div>
</div>
<div align="center" style="padding-top:5px;">
<input type="button" class="easyui-button" id="btnSave"  name="btnSave" value="Save" />
</div>
<input type="hidden" id="hdnMachineId" name="hdnMachineId" value="${requestScope.machineId}"/>
<input type="hidden" id="mode" name="mode" value="" />	
</form>

