<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPreviousDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)
	{	
		viewGrid(url,"&s=1");
	}
	else
	{
		viewGrid(unescape(prevDataUrl),"&s=1");
	}
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Mould Unloading";
		processGridnew(url,filterString,"mouldUnloadingGrid","mouldUnloadingPager","","dblclickMouldUnloading","","mouldUnloadingLoadComplete");
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
		return true;
}
function dblclickMouldUnloading(id)
{
	var rowData = jQuery("#mouldUnloadingGrid").jqGrid('getRowData',id);																								
	var docno = rowData.docno;	
	var WOID = rowData.workorderno;		
	var dataString = '?q=2&filterButton=false&docno='+docno+'&WOID='+WOID+'&closeOnSave=true';	
	navigateToNextForm('mouldUnloading_input.mld'+dataString,'Mould Unloading');
}

function mouldUnloadingLoadComplete()
{
	
	var unloadGridId = jQuery("#mouldUnloadingGrid").jqGrid('getDataIDs');
	 for(i=0;i<=unloadGridId.length;i++)	
	 {			
		 if(jQuery("#mouldUnloadingGrid").getCell(unloadGridId[i], 'status').trim().indexOf("Completed") >= 0)	
			jQuery("#mouldUnloadingGrid").jqGrid('setCell',unloadGridId[i],"docno","",'wo-Completed');
		 else if(jQuery("#mouldUnloadingGrid").getCell(unloadGridId[i], 'status').trim().indexOf("Pending") >= 0)	
			jQuery("#mouldUnloadingGrid").jqGrid('setCell',unloadGridId[i],"docno","",'wo-prodApproved');
	 }
}

function frmFilter_enableDisableSuccessCallBack()
{
	
	jQuery("#cboRelatedTo").val("MLD");
	readOnlyFields('cboRelatedTo');
	setTimeout(function() {enableFields('cmbMould');},550);
	
}

</script>
<form name="frmMouldUnload" id="frmMouldUnload" action="" method="post">
<table style="margin-top:2px;margin-left:70px">
	<tr>
		<td><span class="wo-priority wo-prodApproved" id="ClrProdAccep"></span></td>
		<td>&nbsp;</td>
		<td><label id="lblProdAccep" class="wo-LegendLabel">Pending</label></td>
		<td>&nbsp;&nbsp;</td>
		<td><span class="wo-priority wo-Completed" id="ClrCompleted"></span></td>
		<td>&nbsp;</td>
		<td><label class="wo-LegendLabel">Completed</label></td>					
		<td>&nbsp;&nbsp;&nbsp;</td>	
		<td colspan="14">
		<label style="color:dark brown;font-weight: bold">Double click to view the details</label>
		</td>	
	</tr>
</table>

<div id="wrapperRpt">
	<table id="mouldUnloadingGrid" ></table>
	<div id="mouldUnloadingPager"></div>
</div>
	<input type="hidden" id="hdnfield" name ="hdnfield" class="easyui-text" value=""/>
	<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdndrillValue" name ="hdndrillValue" class="easyui-text" value="${requestScope.drillId } "/>
</form>