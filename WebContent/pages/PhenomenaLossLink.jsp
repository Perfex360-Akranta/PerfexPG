<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script>
jQuery(document).ready(function()
{
	initialiseForm('frmPhenomenaLossLink');
	fillComboBox("frmPhenomenaLossLink","cmbPlpmKeyid","phenomena_combo.pcsEnable");
	fillComboBox("frmPhenomenaLossLink","cmbPlpmMainloss","mainLoss_combo.pcsEnable");
	processGridnew("phenomenaFactory_input.pcsEnable","?q=2","grdphenomenaFactory","grdphenomenaFactorypager","FactoryGrid","","","","selectRowFunction","");
	viewGrid("phenomenaLoss_input.pcsEnable","?q=2");
	jQuery("#txtPlpmName").css('text-transform', 'uppercase');
	jQuery('#submitForm').val('frmPhenomenaLossLink');
});
function frmPhenomenaLossLinkcmbPlpmKeyid_onSelect(record)
{
	processAjaxCalls("getComboFillContent.pcsEnable","?&phenId="+record.id+"&from=onSelectPhen","combotextFill");
}
function frmPhenomenaLossLinkcmbPlpmMainloss_onSelect(record)
{
	processAjaxCalls("getComboFillContent.pcsEnable","?&lossID="+record.id+"&from=onSelectLoss","combotextFill");
	var dataString="?q=2";
	dataString+="&lossID="+record.id;
	viewGrid("phenomenaFactory_input.pcsEnable",dataString);		
}
function btnDeleteFormatter(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input type="button" style="width:24px;" class="grdButton"  value="" onclick="deleteData(\''+id + '\');"/>';  	
}


function deleteData(rowId)
{
	
	var formId = jQuery('#submitForm').val();
	if (rowId == null || rowId < 0) {	return; }		
	var rowData = jQuery("#grdphenomenaLoss").jqGrid('getRowData',rowId);		
	var phenId=rowData.phenId;
	var url = "phenomenaLoss_delete.pcsEnable?q=2" ;				
	url+= "&getPlpmKeyid="+phenId;
	var confDelte = confirm("Do You Want to Delete?");
	if (confDelte) 
	{
		if(formId.length > 0 )
			deleteRecord(formId,url);
	}
}

//------------- Added by Vigensh -----------//

function frmPhenomenaLoss_beforeDelete(){
	  return confirm("Do you want to delete this record?");
	}

	
//------------- Added by Vigensh -----------//
function frmPhenomenaLossLink_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	jQuery('#cmbPlpmMainloss').combobox('setValue',"");
	jQuery("#txtPlpmName").val("");
	jQuery("#cmbPlpmKeyid").combobox('setValue',"");
	jQuery("#grdphenomenaLoss").trigger('reloadGrid');
	jQuery("#grdphenomenaFactory").trigger('reloadGrid');
}

function getSelectdRows()
{
	var allRows = jQuery("#grdphenomenaFactory").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row["checkFactvalue"];
		if( value != null  &&  value.trim()  != "")
		{			
			if(value == '1')				      
			{
				jsonArrO += '{';
				for(var colName in row) 
				{
					var cellValue = parseJqGridCellValue(row[colName]);	
					if(colName == 'factkeyid')
					{
						jsonArrO += '"getPpflFactoryid":"' + cellValue+'"';
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
jQuery('#btnAddNext').click(function()
{
	var url = "PhenomenaFactoryLossLink_save.pcsEnable" ;
	url+= "?q=2";
	saveForm("frmPhenomenaLossLink",url);		
});	
function frmPhenomenaLossLink_beforeSubmit()
{
	var gridData = '&factoryList='+getSelectdRows();
	return gridData ; 
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		if(url=="phenomenaFactory_input.pcsEnable")
		{
			processGridnew(url,filterString,"grdphenomenaFactory","grdphenomenaFactorypager","FactoryGrid","","","loadComplete","selectRowFunction","");
	   		return true;
	   	}
		else if(url=="phenomenaLoss_input.pcsEnable")
		{
			processGridnew(url,filterString,"grdphenomenaLoss","grdphenomenaLosspager","PhenomenaGrid","doubleClickGrid","","","selectRowFunction","");
	   		return true;
		}			
	}	
} 
function doubleClickGrid(id)
{
	alert("entering double click "  +  id )
	var rowData = jQuery("#grdphenomenaLoss").jqGrid('getRowData',id);
	processAjaxCalls("getComboFillContent.pcsEnable","?&phenId="+rowData.phenId+"&from=dblClk","combotextFill");
}
function combotextFill(result)
{
	if(result.from=="onSelectPhen" || result.from=="dblClk")
	{
		jQuery('#cmbPlpmMainloss').combobox('setValue',"");
		jQuery("#txtPlpmName").val("");
		jQuery("#cmbPlpmKeyid").combobox('setValue',"");
		jQuery('#cmbPlpmMainloss').combobox('setValue',result.lossID);
		jQuery("#txtPlpmName").val(result.phenName);
		jQuery("#cmbPlpmKeyid").combobox('setValue',result.phenID);
		viewGrid("phenomenaFactory_input.pcsEnable","?q=2&phenId="+result.phenID);
	}
	if(result.from=="onSelectLoss")
	{
		var dataString="?q=2";
		if(result.lossID!=null||result.lossID!=""||result.lossID!=' '||result.lossID!=undefined)
			dataString+="&lossID="+result.lossID;
		jQuery("#cmbPlpmKeyid").combobox('setValue',"");
		jQuery("#txtPlpmName").val("");
		viewGrid("phenomenaLoss_input.pcsEnable",dataString);
	}
}
function validateFilterSelection(filterString)
{
	return true;
}
function selectRowFunction_selectAll(id,status)
{
	for(var i=0; i<id.length; i++)
	{
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
function selectRowFunction_selectRow(id)
{
	if(jQuery('#jqg_grdphenomenaFactory_'+id).is(':checked'))
		chkboxCheck(id);
	else
		chkboxUnCheck(id);
}
function chkboxCheck(rowId)
{
	jQuery("#grdphenomenaFactory").jqGrid('setCell',rowId,'checkFactvalue','1');	
}
function chkboxUnCheck(rowId)
{
	jQuery("#grdphenomenaFactory").jqGrid('setCell',rowId,'checkFactvalue','0');
}
function loadComplete()
{
	var allRows = jQuery("#grdphenomenaFactory").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i =0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row["checkFactvalue"];
		
		if( value != null  &&  value.trim()  != "")
		{	var j=i+1;
			if(value == '1')				
			{
				jQuery("#jqg_grdphenomenaFactory_"+j).attr('checked',true);
			}
			else if(value=='0')	
			{
				jQuery("#jqg_grdphenomenaFactory_"+j).attr('checked',false);
			}
		}
	}
}
function frmPhenomenaLossLink_successsCallback(result)
{
	jQuery("#grdphenomenaFactory").trigger('reloadGrid');
	jQuery("#grdphenomenaLoss").trigger('reloadGrid');
	reloadCombo("frmPhenomenaLossLink","cmbPlpmKeyid","phenomena_combo.pcsEnable");
	jQuery('#cmbPlpmKeyid').combobox('setValue', result.successData.mstKeyid);
}
</script>
<form name="frmPhenomenaLossLink" id="frmPhenomenaLossLink"  style="margin-top:15px;">
	<div style="margin-left:400px;">
	<div class="easyui-paddingbfpx"><label >Phenomena</label></div>
	<div class="easyui-paddingbfpx" style="width:300px;"><input class="easyui-combobox" id="cmbPlpmKeyid" name="cmbPlpmKeyid" style="width:300px;" value=""/></div>
	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Main Loss</label></div>
	<div class="easyui-paddingbfpx" style="width:300px;"><input class="easyui-combobox" id="cmbPlpmMainloss" name="cmbPlpmMainloss" style="width:300px;" value=""/></div>
	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Phenomena</label></div>
	<div class="easyui-paddingbfpx"><input class="easyui-text" id="txtPlpmName" name="txtPlpmName" style="width:300px;" value=""/></div>
	</div>
	<table style="margin-left:390px;"> 
		<tr >
			<td>
			   <div style="display:none;">
					<table id="grdphenomenaFactory" ></table>
					<div id="grdphenomenaFactorypager"></div>
				</div>
			</td>
			<td>
			    <div style="display:none;">
					<input id="btnAddNext" name="btnAddNext" type="button" class="easyui-button"  value="Add Next"  style=" width:100px; height:25px;"/>
				</div>
			</td>
			<td>
				<table id="grdphenomenaLoss" ></table>
				<div id="grdphenomenaLosspager"></div>
			</td>
		</tr>
	</table>
	<input type="hidden" id="mode" name="mode"/>	
	
</form>