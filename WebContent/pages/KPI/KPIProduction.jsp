<!--
Created By:Dhanalakshmi.R
Date:8.12.12
-->
<script type="text/javascript">
jQuery(document).ready(function()
		{	
		  	initialiseForm('frmKPIProduction');	
		  	jQuery('#submitForm').val('frmKPIProduction'); 
		  	fillComboBox("frmKPIProduction","cmbKidlIndicatorid","kpiIndicatorcmb.keyPerInd");
		  	loadFunctionalLocation("KPIProdfunLocation","functionalLoc.keyPerInd","KPIProdfunLocationValues","frmKPIProduction","");
		  	
		});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption="KPIProduction";
		processGridnew(url,filterString,"grdKPIProduction","grdKPIProductionPager",tableCaption,"","","loadComplete");
		 //jQuery('.ui-jqgrid-labels').toggleClass('white-colr');
		return true;
	}	
}
function validateFilterSelection(filterString){
	return  true;
}
function loadComplete()
{
	jQuery("tr.ui-jqgrid-labels").hide();
	jQuery("#grdKPIProduction thead").children(":first").toggleClass('remove_border');
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
	var i=1;
	for(var i=1;i<=allRows.length;i++)
	{
		for(var j=2;j<=cm.length-1;j+=2)
		{
			if(cm[j].name=="factoryPillar_checkbox"+j)
			{
				var status=jQuery("#factoryPillar_checkbox"+i+j).attr('checked');
				if(status=="checked")
				{
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue'+(j+1),'1');	
				}
				else
				{
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue'+(j+1),'0');
				}
			}
		}
	}
}
function frmKPIProduction_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var url = jQuery('#hiddenUrl').val();
	var compId=keyIds.compId;
	var factId=keyIds.factId;
	var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');
	var dataString = "?q=1"; 
	if(keyIds.compId==undefined ||keyIds.compId==''||keyIds.compId=="")
	compId='CMP001';
	dataString+="&compId="+compId;
	jQuery("#hdnCmpy").val(compId);
	var pillCode=jQuery("#hdnPillCode").val();
	if(keyIds.factId==undefined ||keyIds.factId==''||keyIds.factId==""){}
	else
	{
		jQuery("#hdnFact").val(factId);
		dataString+="&factId="+factId;
	}
	if(indicId==undefined ||indicId==''||indicId==""){}
	else
		dataString+="&indicatorId="+indicId;
	if(pillCode==undefined ||pillCode==''||pillCode==""){}
	else
		dataString+="&pillCode="+pillCode;
	viewGrid(url,dataString);
}
function chkbox_factoryPillar(id, options, rowObject)
{ 
	var rowId = options.rowId;
	var pos=options.pos;
	var position = new Array();
	var rowValue=new Array();
	var k=2;
	var l=2;
	for(var i=0;i<rowObject.length-3;i++)
		{
		position[i]=k;
		rowValue[i]=l;
		k+=2;
		l+=1;
		}
	for(var i=0;i<rowObject.length-3;i++)
	{
		if(pos==position[i])
			return '<input id="factoryPillar_checkbox'+rowId+pos+'" name="factoryPillar_checkbox" '+ (rowObject[rowValue[i]]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck(\''+ rowId +'\',\''+pos+'\')}"/>';
			
	}
}
function frmKPIProductioncmbKidlIndicatorid_onSelect(record)
	{
		var url = jQuery('#hiddenUrl').val();
		var indId=record.id;
		var factId=jQuery("#hdnFact").val();
		var compId=jQuery("#hdnCmpy").val();
		var dataString = "?q=1"; 
		dataString+="&indicatorId="+indId;
		if(factId==undefined ||factId==''||factId==""){}
		else
			dataString+="&factId="+factId;
		if(compId==undefined ||compId==''||compId==""){}
		else
			dataString+="&compId="+compId;
		var pillCode=jQuery("#hdnPillCode").val();
		if(pillCode==undefined ||pillCode==''||pillCode==""){}
		else
			dataString+="&pillCode="+pillCode;
		viewGrid(url,dataString);
	}
function chkboxCheck(rowId,pos)
{
	var position=parseInt(pos)+1;
	jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue'+position,'1');
}
function chkboxUnCheck(rowId,pos)
{
	var position=parseInt(pos)+1;
	jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue'+position,'0');
}
function getSelectdRowsPillFact(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	var jsonArrO='[';
	for( var k = 0; k < allRows.length;k++)
	{
		var row = allRows[k];
		var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
		var colLength=cm.length;
		var i=3;	
		while(i<=colLength)
		{
			var ckeckForSelColNameNew=ckeckForSelColName+i;
			var	checkBoxColNameNew=checkBoxColName+(i-1);
			var value = row[ckeckForSelColNameNew];
			if( value != null  &&  value.trim()  != "")
			{			
				if(value == '1')				
				{
					jsonArrO += '{';
					for(var colName in row)
					{
						if(colName == checkBoxColNameNew)
						{	
							var factId=jQuery('th#grdKPIProduction_'+checkBoxColNameNew).text();
							jsonArrO +='KPIKidlDeptid:"'+factId.trim()+'"';
						}
					}
					jsonArrO += ",";
					for(var colName in row)
					{
						var cellValue = parseJqGridCellValue(row[colName]);	
						if(colName == 'indicatorID')
						{
							jsonArrO +='KPIKidlIndicatorid:"'+cellValue+'"';
						}
					}
					jsonArrO +=  "},";
				}
			} 
		i=i+2;
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
} 
function frmKPIProduction_beforeSubmit()
{
	  var gridData = '&selectedPillFactIDs='+getSelectdRowsPillFact('grdKPIProduction','factoryPillar_checkbox','checkPillarvalue');
	  var check=gridData.substr(22);
	  if(check==null||check==""||check=='')
		 { alert('No Indicators Selected');return false;}
		var indicatorId=jQuery("#cmbKidlIndicatorid").combobox('getValue');
		if(indicatorId==null||indicatorId==""||indicatorId==''){}
		else
	 	 gridData+='&indicatorId='+indicatorId;
	 	 var factId=jQuery("#hdnFact").val();
	 	 if(factId==null||factId==""||factId==''){}
	 	 else
	 	 gridData+='&factId='+factId;
	  return gridData ; 
}
function frmKPIProduction_successsCallback(result)
{
	jQuery("#grdKPIProduction").trigger("reloadGrid");
	jQuery("#hdnPillCode").val(result.successData.pillCode);
}
</script>
<form id="frmKPIProduction" >
<div  id="frmKPIProductionFuntKeyIds"  >
			<input type="hidden" id="location" name="cmbKPILocationid" value=""/>
			<input type="hidden" id="factory" name="cmbKPIFactoryid" value="" / >
			<input type="hidden" id="section" name="cmbKPISectionid" value="" / >
			<input type="hidden" id="cell" name="cmbKPICellid" value="" / >
			<input type="hidden" id="machine" name="cmbKPIMachine" value="" / >
			<div id="KPIProdfunLocation" style="width:994px;margin-left:170px;"></div>
			<div class="clear"></div>
		</div>
<div style="margin-left:170px;margin-top:5px;">
<label>Indicators</label></div>
<div style="margin-left:170px;margin-top:5px;">
<input id="cmbKidlIndicatorid" name="cmbKidlIndicatorid" type="text" class="easyui-combobox" style="width: 350px;" value="" />
</div>
<div id="wrapperRpt" style="margin-top:20px;margin-left:168px;" >
	<table id="grdKPIProduction" ></table>
	<div id="grdKPIProductionPager"></div></div>
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnFact" />
	<input type="hidden" id="hdnCmpy"/>	
	<input type="hidden" id="hdnPillCode" value="${requestScope.pillarcode }"/>
</form>