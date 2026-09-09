<script>

jQuery(document).ready(function()
{

	initialiseForm('frmLossJHLink');
	/* fillComboBox("frmLossJHLink","cmbPlpmKeyid","phenomena_combo.pcsEnable");
	fillComboBox("frmLossJHLink","cmbPlflParameterid","mainLoss_combo.pcsEnable");
	 */
	processGridnew("lossJH_input.pcsEnable","?q=2&lossID=1","JHGrid","JHGridpager","JHGrid","","","loadComplete","","");
	
	viewGrid("lossParameters_input.pcsEnable","?q=2");
	
	/* jQuery("#txtPlpmName").css('text-transform', 'uppercase'); */
	jQuery('#submitForm').val('frmLossJHLink');
	
	
});


function frmLossJHLink_deleteSuccessCallback(result)
{
	jQuery('#cmbPlflParameterid').combobox('setValue',"");
	jQuery("#txtPlpmName").val("");
	jQuery("#cmbPlpmKeyid").combobox('setValue',"");
	jQuery("#lossGrid").trigger('reloadGrid');
	jQuery("#JHGrid").trigger('reloadGrid');
}	

function getSelectdRows()
{
	var allRows = jQuery("#lossGrid").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		
		var value = row["checkLossvalue"];
		if( value != null  &&  value.trim()  != "")
		{			
			jsonArrO += '{';
			
			var lossId = row["lossId"];	
			var plflId = row["plflId"];
			var cellId = jQuery('#hdnPlflCellid').val();
			
			jsonArrO += ' "txtPlflKeyid":"' + plflId +'","txtPlflParameterid":"' + lossId +'","txtPlflCellid":"' + cellId +'", "txtPlflTempfield2":"' + value +'" ';
			
			jsonArrO +=  "},";							
					
		} 
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert(jsonArrO);
	return jsonArrO; 	
} 

function frmLossJHLink_beforeSubmit()
{
	var gridData = '&jhLossList='+getSelectdRows();
	return gridData ; 
}

function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		if(url=="lossJH_input.pcsEnable")
		{
			processGridnew(url,filterString,"JHGrid","JHGridpager","JHGrid","","","loadComplete","","");
	   		return true;
	   	}
		else if(url=="lossParameters_input.pcsEnable")
		{

			processGridnew(url,filterString,"lossGrid","lossGridpager","PhenomenaGrid","doubleClickGrid","","lossGrid_LoadCompleted","selectRowFun");
	   		return true;
		}			
	}	
} 

function validateFilterSelection(filterString)
{
	return true;
}


function lossGrid_LoadCompleted(result) {		

	var allRows = jQuery("#lossGrid").jqGrid('getRowData');
	
	for( var i =0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row["checkLossvalue"];
		
		if( value != null  &&  value.trim()  != "")
		{	var j=i+1;
			if(value == '1')				
			{
				jQuery("#jqg_lossGrid_"+j).attr('checked',true);
			}
			else if(value=='0')	
			{
				jQuery("#jqg_lossGrid_"+j).attr('checked',false);
			}
		}
	}

	
}

function doubleClickGrid(id)
{
	var rowData = jQuery("#lossGrid").jqGrid('getRowData',id);
	processAjaxCalls("getComboFillContent.pcsEnable","?&phenId="+rowData.phenId+"&from=dblClk","combotextFill");
}


function lossGrid_selectAll(id,status)
{

	for(var i=0; i<id.length; i++)
	{
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}

function lossGrid_selectRow(id)
{
	if(jQuery('#jqg_lossGrid_'+id).is(':checked'))
		chkboxCheck(id);
	else
		chkboxUnCheck(id);
}

function chkboxCheck(rowId)
{
	jQuery("#lossGrid").jqGrid('setCell',rowId,'checkLossvalue','1');
}
function chkboxUnCheck(rowId)
{
	jQuery("#lossGrid").jqGrid('setCell',rowId,'checkLossvalue','0');
}

function loadComplete()
{		
	jQuery("#JHGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			selectJH(id);
		}
	});

	setTimeout(function() {selectJH(1);},550);
}

function selectJH(id) {
	var jhId= jQuery("#JHGrid").jqGrid('getCell',id,"jhkeyid");
	jQuery('#hdnPlflCellid').val(jhId);
	var dataString="?q=2";
	dataString+="&jhId="+jhId;
	viewGrid("lossParameters_input.pcsEnable",dataString);		

}

function frmLossJHLink_successsCallback(result)
{
	jQuery("#JHGrid").trigger('reloadGrid');
	//jQuery("#lossGrid").trigger('reloadGrid');
	reloadCombo("frmLossJHLink","cmbPlpmKeyid","phenomena_combo.pcsEnable");
	jQuery('#cmbPlpmKeyid').combobox('setValue', result.successData.mstKeyid);
	
	selectJH(1);
}
</script>
<form name="frmLossJHLink" id="frmLossJHLink"  style="margin-top:15px;">
	<!-- 
	<div style="margin-left:507px;">
		<div class="easyui-paddingbfpx"><label >Phenomena</label></div>
		<div class="easyui-paddingbfpx" style="width:300px;"><input class="easyui-combobox" id="cmbPlpmKeyid" name="cmbPlpmKeyid" style="width:300px;" value=""/></div>
		<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Main Loss</label></div>
		<div class="easyui-paddingbfpx" style="width:300px;"><input class="easyui-combobox" id="cmbPlflParameterid" name="cmbPlflParameterid" style="width:300px;" value=""/></div>
		<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Phenomena</label></div>
		<div class="easyui-paddingbfpx"><input class="easyui-text" id="txtPlpmName" name="txtPlpmName" style="width:300px;" value=""/></div>
	</div>
	 -->
	 
	<table style="margin-left:150px;"> 
		<tr >

			<td style="padding-left: 20px;">
				<table id="JHGrid" ></table>
				<div id="JHGridpager"></div>
			</td>

			<td>
				<table id="lossGrid" ></table>
				<div id="lossGridpager"></div>
			</td>
			
			
		</tr>
	</table>
	<input type="hidden" id="mode" name="mode"/>	
	<input type="hidden" id="hdnPlflCellid" name="hdnPlflCellid"/>
	
</form>