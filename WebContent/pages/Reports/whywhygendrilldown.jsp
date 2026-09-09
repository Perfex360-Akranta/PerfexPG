	
<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();		
	viewGrid(url,"fetch=true");
	jQuery('#btnGraph').click(function(){
		showGraphData("whywhyRptGenDrill_chart.why");
	});

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		jQuery('#hdnFilterString').val(filterString);
		var tableCaption = "Cummulative Report";
		processGridnew(url,filterString,"grdWhywhydrill","pager",tableCaption,"grdWhywhydrill_doubleClickGrid","","oplcumulativeOnload");
		return true;
	}
}

function grdWhywhydrill_doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("grdWhywhydrill","keyid2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function oplcumulativeOnload()
{
	setDrillDownHeader("jqgh_grdWhywhydrill_FLLOC","grdWhywhydrill","keyid2");
	setTotalRowCss('grdWhywhydrill');
}
function grdWhywhydrill_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("grdWhywhydrill","keyid2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}
 function validateFilterSelection(filterString)
 {
	
	return  true;
}


</script>
<form>
 <div id="wrapperRpt" style="max-width: 1210px;">
 <table>
 <tr>
 <td >
 <div style="margin-top: -28px">
<input type="button" class="easyui-button" value="Graph" id="btnGraph" name="btnGraph" />
</div>
</td>
<td>
<label id="oplnotes" class="notes" style="font-weight: bold; margin-left:10px; display: none;">${requestScope.oplCumMsg}</label>
</td>
</tr>
</table>

	
<div style="margin-top: -5px">
	<div id="divGraphContainer" ></div>	
	<table id="grdWhywhydrill" ></table>
	<div id="pager"></div>
	</div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hdnFnlnKeyid" />

</form>