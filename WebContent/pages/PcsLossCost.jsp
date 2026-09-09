<!--
	Created By:Dhanalakshmi.R
	Date:5.1.13
-->
<script type="text/javascript">
jQuery(document).ready(
		
function()
{   //alert(1);
	initialiseForm('frmpcsLossCst');	
	setLoadFormCallBackFrmId("frmpcsLossCst");
	invokeAfterLoadFormCallBack();
	jQuery('#submitForm').val('frmpcsLossCst');

	 var btnName = jQuery("#hdnBtnName").val();
		//alert("btnName"+btnName);
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function(){
			
			processAjaxCalls("openFile.file?fileName=Loss-Cost.xls", "", "", "", "", "viewTemplate");		

		});
		//viewGrid("PcsLossCostDD_input.lossCst","cmbCompid=CMP0000001&cmbLocnid=LCN0000001&cmbFactid=&cmbSbuid=SBU0000001&cmbPbuid=PBU0000001&cmbSectid=LIN0000023&parentId=LIN0000023&BREAKUP=");
		
});

function viewGrid(url,filterString)
{
	//alert(url +'-----'+filterString);
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y&parentId=LIN0000023';
		var tableCaption = "DrillDown (PCSLossCost) Report";
		processGridnew(url,filterString,"pcsLossCstGrid","pcsLossCstGridpager",tableCaption,"doubleClickGrid","","lostCost_LoadComplete");
		return true;
	}
	return false;
		
}
function validateFilterSelection(filterString)
{
	 if( ! checkFilterValueExist(filterString,"cmbCompid"))
	 {
		if(filterString==null || filterString==''||filterString=="")
		{}
		else
		{
			alert("Select Functional Location");
			return false;}
		}
	return true;
}
/*function doubleClickGrid(id)
{ 
	var rowData = jQuery("#pcsLossCstGrid").jqGrid('getRowData',id);
	var selId = rowData.getLcpmElementid;	
	jQuery("#hiddenStr").val(selId);
	if(selId.substr(0,3) == 'MCH'){}
	else 
		//if(jQuery("#pcsLossCst_checkbox_"+id).is(':checked'))
	{
		var keyid=rowData.getLcpmKeyid;
		if(keyid.trim().length<=0)
		{
			saveForm("frmpcsLossCst","PcsLossCostDD_save.lossCst");
		}
		var filterData ="?";		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		//alert(selId);
		var url = jQuery('#hiddenUrl').val();
		processGridnew(url,filterData,"pcsLossCstGrid","pcsLossCstGridpager","","doubleClickGrid","","lostCost_LoadComplete");
	}
	//else
	//	alert('Save Department To Drill Down');	
}*/
function frmpcsLossCst_afterLoadCallBack()
{
	toggleCommonFilter();
}
function lostCost_LoadComplete()
{ 
	//closePopUpDialoge("filterShowHide");
	var allRows = jQuery("#pcsLossCstGrid").jqGrid('getDataIDs');
	var cm = jQuery("#pcsLossCstGrid").jqGrid("getGridParam", "colModel");
	var parentId = jQuery("#pcsLossCstGrid").jqGrid('getCell', allRows[0], 'getLcpmElementid');
	if(parentId.substr(0,3) == 'LCN')			
		hideShowBack(false);	
	else
		hideShowBack(true);	
	for(var i=1;i<=allRows.length;i++)
	{
		var id='dteEffDate_'+i;
		var txtId='txtCstPerMin_'+i;
		formatDateBox(id,'dd-MMM-yyyy');	
		numericTextBox(txtId);
	}
}
function pcsLossCstGrid_onProcessGridBack()
{
	var url = jQuery('#hiddenUrl').val();
	var rowIds = jQuery("#pcsLossCstGrid").getDataIDs();
	var parentId = jQuery("#pcsLossCstGrid").jqGrid('getCell', rowIds[0], 'getLcpmElementid');			
	if(parentId.substr(0,3) != 'LCN')
	{		
		var dataString = '?&drillFlag=b';//+'&parentId='+parentId;		
		processGridnew(url,dataString,"pcsLossCstGrid","pcsLossCstGridpager","","doubleClickGrid","","lostCost_LoadComplete");
	}			
}	
function chkbox_PcsLossCost(id, options, rowObject)
{ 
	var rowId = options.rowId;
	return '<input id="pcsLossCst_checkbox_'+rowId+'"  name="pcsLossCst_checkbox" '+ (rowObject[4]=="N" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function datebox_effectiveDate(id,options,rowObject)
{
	var rowId = options.rowId;     
	var id='dteEffDate_'+rowId;
	var value="";
	if (rowObject[4]=="N")
		value=rowObject[6];
	return '<input id="'+id+'" name="'+id+'" type="text" class="easyui-datebox"  style="width:203px;height:25px;" value="'+value+'"/>';
	}
function textbox_costPerMinute(id,options,rowObject)
{
	var rowId = options.rowId;
	var value="";
	if (rowObject[4]=="N")
		value=rowObject[7];
	var id='txtCstPerMin_'+rowId;
	return '<input id="'+id+'" name="'+id+'" type="text" class="easyui-text"  style="width:203px;text-align:right;height:23px;" value="'+value+'"/>';
	}
function chkboxCheck(rowId)
{
	jQuery("#pcsLossCstGrid").setCell(rowId, "checkPcsLossCost","N");
}
function chkboxUnCheck(rowId)
{
	jQuery("#pcsLossCstGrid").setCell(rowId, "checkPcsLossCost","Y");
}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#pcsLossCstGrid").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		if( value != null  &&  value.trim()  != "")
		{			
			if(value == '1')				
			{
				jsonArrO += '{';
				for(var colName in row) 
				{
					var cellValue = parseJqGridCellValue(row[colName]);	
					if(colName == 'KEYID')
					{
						jsonArrO += '"getLcpmElementid":"' + cellValue+'"';
						jsonArrO +=',';
					}
					else if(colName == 'effectiveDate')
					{
						jsonArrO += '"getLcpmFromdate":"' + cellValue+'"';
						jsonArrO +=',';
					}
					else if(colName=='costPerMinute')
					{
						if(cellValue.length<=7 && cellValue.indexOf(".")==-1)
						{ 
							jsonArrO += '"getLcpmCostperminute":"' + cellValue+'"';						
						}
						else if(cellValue.indexOf(".")!=-1)
						{
							var precision=cellValue.substring(cellValue.indexOf(".")+1,cellValue.length);
							if(precision.length>2)
							{
								throwAlertcostperminute(i);											
							}
							else
							{
								jsonArrO += '"getLcpmCostperminute":"' + cellValue+'"';				
							}	
						}
						else
						{
							throwAlertcostperminute(i);
						}										
				 	}
			    }
				jsonArrO +=  "},";					
		}
	  }
   }
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}
function throwAlertcostperminute(i)
{
	alert('Cost Per Minute Should Have 7 Digits Maximum & 2 Precisions');
	var index=i+1;
	var textBoxName= "txtCstPerMin_"+index;
	jQuery("#"+textBoxName).val("");
}

/*function frmpcsLossCst_beforeSubmit() {
	
    var save=jQuery('#hdnsavebtn').val();
    alert(save);
	if((save.trim().length== "0" || save!= "Y")){
    var gridData = '&pcslossDetails=' + getGridSelectArray("pcsLossCstGrid");  
    alert("gridData    "+ gridData );
	return gridData;
	}
}*/

function frmpcsLossCst_beforeSubmit()
{
	//var reqColsArr=new Array("getLcpmElementid","getLcpmFromdate","getLcpmCostperminute","getLcpmKeyid","checkPcsLossCost");
	//var gridData = '&selectedrowIDs='+JqGridToJsonSelRowsReqCols('pcsLossCstGrid','checkPcsLossCost',reqColsArr);
	var jsonData =  getGridSelectArray("pcsLossCstGrid");
	var gridData =  '&selectedrowIDs='+jsonData;
	//alert(gridData);
	if(jsonData.length<2){
		alert("Select Data");
		return false;
	}else
		return gridData;
}
function frmpcsLossCst_successsCallback()
{
	jQuery("#pcsLossCstGrid").trigger("reloadGrid");
}

</script>
<form id="frmpcsLossCst" name="frmpcsLossCst" >
<div id="wrapperRpt" >
<!--<div class="clear"></div>	-->
<!--<table>
<tr>
<td>
<label class="notes" style="font-weight: bold; padding-left:36px;"> ${requestScope.DrillDown}</label>
</td>
<td style='padding-left:10px;'><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 140px;height:21px;"/></td>
</tr>
</table>-->
<div style="padding-left:35px;">
<table id="pcsLossCstGrid" ></table>
<div id="pcsLossCstGridpager"></div>
</div>
</div>
<input type="hidden" id="mode" name="mode"/>
  <input type="hidden" id="hdnsavebtn" name="hdnsavebtn"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />

</form>

