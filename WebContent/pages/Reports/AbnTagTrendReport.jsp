<!-- Written By: Dhanalakshmi.R-->
<script>
jQuery(document).ready(
function()
{		
	initialiseForm('frmAbnTagTrendRpt');	
	//setLoadFormCallBackFrmId("frmAbnTagTrendRpt");
	jQuery('#submitForm').val('frmAbnTagTrendRpt');
	var url = jQuery('#hiddenUrl').val();
	//viewGrid(url,"?q=2&firstClick=Y");
	setLoadFormCallBackFrmId("frmAbnTagTrendRpt");
	invokeAfterLoadFormCallBack();
	jQuery('#btnGraph').click(function()
	{
		var rowid = jQuery("#grdAbnTagTrend").jqGrid('getGridParam','selrow');
		var rowData = jQuery("#grdAbnTagTrend").jqGrid('getRowData',rowid);
		var selId = rowData.LEVELID;
		if(selId == null || selId ==""){
			alert("Select Data to View Graph");
			return false;
		}else
		{
			var url = "AbnTagTrendRpt_getChart.abnRpt?rowid="+rowid+"&chType=spline";
			showGraphData(url);	
		}
	});
	
	jQuery('#btnGraphBar').click(function()
			{
				var rowid = jQuery("#grdAbnTagTrend").jqGrid('getGridParam','selrow');
				var rowData = jQuery("#grdAbnTagTrend").jqGrid('getRowData',rowid);
				var selId = rowData.LEVELID;
				if(selId == null || selId ==""){
					alert("Select Data to View Graph");
					return false;
				}else
				{
					var url = "AbnTagTrendRpt_getChart.abnRpt?rowid="+rowid+"&chType=column";
					showGraphData(url);	
				}
			});
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f';
		var tableCaption = "DrillDown (Abnormality Tag Trend) Report";
		processGridnew(url,filterString,"grdAbnTagTrend","grdAbnTagTrendpager",tableCaption,"doubleClickGrid","","abnTagTrend_LoadComplete");
		return true;
	}
	return false;	
}
function validateFilterSelection(filterString)
{
	return true;
}
function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("grdAbnTagTrend","LEVELID",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function abnTagTrend_LoadComplete()
{
	setDrillDownHeader("CH1-0","grdAbnTagTrend","LEVELID");
	setTotalRowCss('grdAbnTagTrend');
}
function grdAbnTagTrend_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("grdAbnTagTrend","LEVELID",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	

function frmAbnTagTrendRpt_afterLoadCallBack(){
	
	toggleCommonFilter();	
	
}
</script>

<form name="frmAbnTagTrendRpt" method="post">

<div id="wrapperRpt" style="margin-top: 50px;margin-left:30px;;margin-top:30px;margin-top:0px\9">
<input id="btnGraph" class="easyui-button"  type="button" value="Line Graph" style="height:21px"/>
<span><input id="btnGraphBar" class="easyui-button"  type="button" value="Bar Graph" style="height:21px"/></span>
<table id="grdAbnTagTrend" border="1" rules="all" ></table>
<div id="grdAbnTagTrendpager"></div>
</div>
<input type="hidden" id="hdnFnlnKeyid" />
</form>