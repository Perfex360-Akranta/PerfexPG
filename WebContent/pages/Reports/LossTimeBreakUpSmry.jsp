<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=2&cboLossRptType=SECT&q=1");

	jQuery('#btnGraph').click(function(){
		
//			var rowid = jQuery("#LossTimeGrid").jqGrid('getGridParam','selrow');

//			var url = "chart.bdmprto?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			var rptType = jQuery('#cboReportType').val();
			
			showGraphData("chart.pcsrpt?");
		});
	
	
});


function LossTimeGrid_loadComplete()
{	
		
	
}
function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		/*if(rptType == 'undefined' ||rptType == undefined){
			
			rptType = getFieldValue('cboReportType');
			//alert("getfiel  "+rptType);
		}*/
		
		filterString += '&drillFlag=f';
		jQuery.cookie("filterString",filterString.substring(0,filterString.length-9));
		var tableCaption = "Defect Summary";
		
		processGridnew(url,filterString,"LossTimeGrid","pager",tableCaption,"","","LossTimeGrid_loadComplete");
		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){

	var reportType = getFilterValue(filterString,"cboLossRptType");	
	if(reportType == "MCHM" || reportType == "CELL")
		{
			if( ! checkFilterValueExist(filterString,"cmbSectid"))
			{
				alert("Select Section");
				return false;
			}	
		}
	return  true;
}

/*function openGrid(){
	var rptType = jQuery('#cboReportType').val();
	
	var actionPart = jQuery('#hiddenUrl').val();
	var filtStr=jQuery.cookie("filterString");
	
	
	
		viewGrid(actionPart,filtStr );	
}	*/		
					
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#chkDatewise").attr("checked",true);
	jQuery("#chkMonthwise").attr("checked",false);
}

</script>

<div id="wrapperRpt">
<!--<select id="cboReportType" class="easyui-combobox" onchange="openGrid();" style="width:200px;padding-left:10px;">
	<option value="S">SECTION WISE</option>
	<option value="C">LINE WISE</option>
	<option value="M">MACHINE WISE</option>
</select>
-->
<div style="margin-left:-46px;margin-top: -28px">
<span style="padding-left: 50px;">
	<input  class="easyui-button" type="button" name="btnGraph" style="width:54px;" id="btnGraph" value="Graph"/>
</span>
</div>
	<table id="LossTimeGrid" ></table>
	<div id="pager"></div>
</div>