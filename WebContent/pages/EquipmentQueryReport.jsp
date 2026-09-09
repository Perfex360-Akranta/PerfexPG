
<script>
jQuery(document).ready(function(){	

	
	var FromDate=null;
	var ToDate = null;
	
	var url = jQuery('#hiddenUrl').val();
	var mode = "VIEW";
	
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	dataString += "&FORM_MODE="+mode + "&ViewClicked=Y";
	
	var filterStr = jQuery("#hdnFilterStr").val();
	
	if( filterStr != null && filterStr.length > 0 )
	{
		var dataString = jQuery("#hdnFilterStr").val();
		var tableCaption = "Equipment Query";
		processGridnew(url,dataString,"list","pager",tableCaption,"eqpDoubleClick");
	}
	else
		viewGrid(url,dataString);	
	
});

function frmEquipmentRelated_enableDisableSuccessCallBack()
{
	
}

function BtnFormatterParameter(id, options, rowObject)
{			
	var id=options.rowId;
	return '<input type="button" id="parm" class="easyui-button" value="..." style="height:20px; width:80px;" onclick="ParameterRpt(\''+id + '\');"/>';
}




function equip_Formatter(cellval, options, rowObject)
{		
	var colFlag = rowObject[3];	
	if(rowObject[4] == 'ListText')
	{				
		return cellval;
	}
	else if( rowObject[4] == 'ListNumber'){
	
			 return colFlag;	
	}	
	else if( rowObject[4] == 'ListDropdown'){
		//jQuery('#equipmentParameter').setCell(options.rowId, 'txtmplkDescription', '', { color: 'yellow' });
		
		var displayNames = rowObject[5];
		var comboDisp = displayNames.split(",");
		var codeVal =  rowObject[6];
		var comboVal = codeVal.split(",");
		//combobox = "<input id='combo_"+rowObject[0] +"' name='combo_"+rowObject[0] +"'> "; 
		var comboBox = "<select id='combo_"+rowObject[0].replace("/","") +"'  style='width:155px;' name='combo_"+rowObject[0].replace("/","") +"' class='easyui-combobox' value='Y'>";
		for( var i =0; i<comboDisp.length; i++ ){
			
			if(rowObject[3]== comboVal[i])
				comboBox += "<option selected='selected' value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
			else
				comboBox += "<option  value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
		} 
		comboBox += "</select>";		
		return comboBox;
		
	}else if( rowObject[4] == 'ListDate'){
		return '<input id="dteEqpParam_'+options.rowId+'" width="70px"  class="easyui-datebox" />';
		
	}else if( rowObject[4] == 'ListSelection'){
		return cellval;
	}		
}	

function equipLong_Formatter(cellval, options, rowObject)
{
	if(rowObject[4] == 'ListText')
	{
		if(cellval == '' || cellval == null)
			cellval = ' ';
		//jQuery('#equipmentParameter').editRow(options.rowId, true); 	
		return cellval;	
	}
	else if( rowObject[4] == 'ListNumber'){
		
		cellval=' ';
		 return cellval;	
	}	
	else if( rowObject[4] == 'ListDropdown'){
		 return ' ';	
	}
	else
		{
			//return '<span  style="background-color:#c0ffc0;" class="cellWithoutBackground">' + cellval + '</span>';
			return cellval;
		}
}



function ParameterRpt(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var keyid = rowData.KeyId;

	processGridnew("EquipmentParameter_input.eqp","?q=2&eqpId="+keyid,"equipmentParameter","Pager4","EquipmentParameter","","","");
	jQuery( "#paramDiv" ).show();
	jQuery( "#paramDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 360,
		width: 900		
	});	
	
}


function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		jQuery("#hdnFilterStr").val(filterString);
		var tableCaption = "Equipment Query";
		//processGridnew(url,filterString,"list","pager",tableCaption);
		processGridnew(url,filterString,"list","pager","","eqpDoubleClick");
		return true;
	}	
	return false;
}

function validateFilterSelection(filterString){
	
	return  true;
}

function eqpDoubleClick(id)
{	
	
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	var filter = jQuery("#hdnFilterStr").val();
	var jsonstr = '{"filter":"'+ filter+'"}';
	var perstData = jQuery.parseJSON(jsonstr);	
	navigateToNextForm('equipment_input.eqp'+'?keyId='+keyid+'&mode=VIEW'+'&filterButton=false',"Equipment Master",null,perstData);
	
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#chkDatewise").attr('checked',false);
	jQuery("#chkMonthwise").attr('checked',false);		
	
}

</script>
	<div id="wrapperRpt" style="margin-top: 10px;"> 
	<label>Double click to view Machine Details</label>
<table id="list" ></table>
<div id="pager"></div>
<div id="paramDiv" style="display:none;" title="param">
<table id="equipmentParameter" width="400px" style="float: left;"></table>
	</div>
	</div>
	<input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>
	
	