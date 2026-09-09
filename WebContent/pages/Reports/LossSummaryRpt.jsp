<!--Author Mr.T.Karthick-->
<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?drillFlag=f");
	
	//setLoadFormCallBackFrmId("frmLossSummaryRptRpt");
	
//alert(actionPart);
});
/*function frmLossSummaryRptRpt_afterLoadCallBack(){	
	toggleCommonFilter();		
}*/


function lossGrid_loadComplete()
{	
	
	//for display and hide back button
	var rowIds = jQuery("#lossGrid").getDataIDs();
	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#lossGrid").jqGrid('getCell', rowIds[0], 'LossId');					
			
			hideShowBack(true);
			if(parentId.substr("LOS"))
				hideShowBack(false);
			
		}
	
	
	var row = jQuery("#lossGrid").jqGrid('getDataIDs');		
	jQuery("#lossGrid").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	jQuery("#lossGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
	{
		//lossGrid_doubleClickGrid(rowid,iCol);
		jQuery('#rowId').val(rowid);
		jQuery('#iCol').val(iCol);
				
	} });
		
		var grid = jQuery('#lossGrid');
		var sel_id = row.length;		
		var CellData = grid.jqGrid('getCell',sel_id,'Loss/Area');		
		if(CellData.trim()=='TOTAL')
			setTotalRowCss('lossGrid');			
}
	
function viewGrid(url,filterString)
{		
	if(filterString=="?"){		
		processGridnew(url,filterString,"lossGrid","pager",tableCaption,"lossGrid_doubleClickGrid","","lossGrid_loadComplete");
		return true;
	}
	else //if( validateFilterSelection(filterString))
	{
		var tableCaption = "lossGrid Report";
		processGridnew(url,filterString,"lossGrid","pager",tableCaption,"lossGrid_doubleClickGrid","","lossGrid_loadComplete");
		return true;
	}
	return false;	
}
function frmFilter_enableDisableSuccessCallBack()
{
	//alert('inside');
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	
}

function validateFilterSelection(filterString){
	//alert(filterString);
	if(filterString == "?" || filterString.substring(0,1) == "?")
		return true;
 		/*if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false))
		{
			alert("Select Datewise Checkbox");
			return false;
		}*/
		if(jQuery('#chkDatewise').is(':checked') == true){
			if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
			{
				alert("Select  FromDate");
				return false;
			}
			/*else{
				 
				jQuery('#hdnfromDate').val(jQuery('#dtefromDate').datebox('getValue'));
			}*/
			if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
			{
				alert("Select  ToDate");
				return false;
			}
		}
	return  true;
}
function lossGrid_doubleClickGrid(id,iCol,colVal){ 
	var id =jQuery('#rowId').val();
	var iCol = jQuery('#iCol').val();
	var actionPart = document.getElementById('hiddenUrl').value;
	var rowData = jQuery("#lossGrid").jqGrid('getRowData',id);	
	var grid = jQuery('#lossGrid');
	var sel_id = grid.jqGrid('getGridParam', 'selrow');
	var CellData = grid.jqGrid('getCell', sel_id,iCol );
	var keyid = rowData.LossId;	
	if(iCol>2 && iCol !=8  )
	{
		
	  if(keyid==null || keyid.trim()=='')
		return false;
	 
	 else if(CellData=='0')
		{
		alert("No Data Exist!");
		return false;
		}
	 		
		var colId='';
			
		 if(iCol==3)
			colId='IMR';
		else if(iCol==4)
			colId='IMC';
		else if(iCol==5)
			colId='POH';
		else if(iCol==6)
			colId='HOD';
		else if(iCol==7)
			colId='PEH';			
		
			if(keyid.substring(0,3) != 'MMA')
			{			
				var dataString = '?LossId='+keyid+'&colId='+colId;			
				viewGrid(actionPart,dataString);			
			}
	}
				
}

function lossGrid_onProcessGridBack(){		
		
		var actionPart = jQuery("#hiddenUrl").val();//document.getElementById('hiddenUrl').value;					
		var backVal=true;
		var rowid=jQuery("#lossGrid").getDataIDs()[0];
		var rowData = jQuery("#lossGrid").jqGrid('getRowData',rowid);
		var keyid = rowData.kaizenId;			
		if(keyid.substring(0,3) != 'TGT')
		{				
			var dataString = '?q=2&backVal='+backVal;
			jQuery("#lossGrid").GridUnload();		
			viewGrid(actionPart,dataString);
		}
		else			
			viewGrid(actionPart,"?");
		
 }
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);

	jQuery('#chkDatewise').click(function(){
 		if(jQuery('#chkDatewise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}

</script>
<form name="frmLossSummaryRptRpt">
<div id="wrapperRpt">
<div class="clear"></div>
<div style="margin-top: -28px">
<label class="notes" style="font-weight: bold; padding-left:1px;"> ${requestScope.doubleClick}</label></div>
<table id="lossGrid" >
</table>
<div id="pager"></div>
</div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="rowId" value="" />
<input type="hidden" id="iCol" value="" />
</form>	