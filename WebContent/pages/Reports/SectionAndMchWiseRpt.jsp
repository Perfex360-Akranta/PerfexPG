<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=2&cboLossRptType=SECT&q=1");

	jQuery('#btnGraph').click(function(){
		showGraphData("defectChart.pcsrpt?");
	});
});


function workOrderGrid_loadComplete()
{	
		
	
}
function viewGrid(url,filterString,rptType)
{
	if( validateFilterSelection(filterString))
	{
		
		
		filterString += '&drillFlag=f';
		jQuery.cookie("filterString",filterString.substring(0,filterString.length-9));
		var tableCaption = "Defect Summary";
		
		processGridnew(url,filterString,"workOrderGrid","pager",tableCaption,"","","workOrderGrid_loadComplete");
		
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

function openGrid(){
	var rptType = jQuery('#cboReportType').val();
	
	var actionPart = jQuery('#hiddenUrl').val();
	var filtStr=jQuery.cookie("filterString");
	
//	alert(rptType+" --  "+firstClick.trim().length+" --  "+filtStr); 	
	
		viewGrid(actionPart,filtStr,rptType );
	
	
}			
					


</script>

<div id="wrapperRpt">
<!--<select id="cboReportType" class="easyui-combobox" onchange="openGrid();" style="width:200px;padding-left:10px;">
	<option value="S">SECTION WISE</option>
	<option value="C">Line WISE</option>
	<option value="M">MACHINE WISE</option>
</select>
	-->
	<div style="margin-top: -28px" >
	<input  class="easyui-button" type="button" name="btnGraph" id="btnGraph" value="Graph" style="width:60px;"/>
	</div>
	<table id="workOrderGrid" ></table>
	<div id="pager"></div>
</div>