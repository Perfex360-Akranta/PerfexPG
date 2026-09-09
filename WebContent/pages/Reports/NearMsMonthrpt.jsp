<script>
jQuery(document).ready(function(){
	initialiseForm('frmNearmissmonth');
	//alert("2");
	var actionPart = jQuery('#hiddenUrl').val();
	//alert("4" + actionPart);
	viewGrid(actionPart,"?q=1");
	//alert(viewGrid(actionPart,"?q=1"));
	 //processGridnew("NearMissReportMonth_getCol.nmr","q=2","nearmissmonthgrid","monthpager","","doubleclick");
	 
	jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#nearmissmonthgrid").jqGrid('getGridParam','selrow');
			var url = "chart.nmr";
			showGraphData(url);
		});
	
	});




function viewGrid(url,filterString)
{//alert("url " + url + " filterStng  " +  filterString);
	if( validateFilterSelection(filterString))
	{
		
		
		filterString += '&drillFlag=f&firstClick=Y';
		//alert(filterString);
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"nearmissmonthgrid","monthpager",tableCaption,"doubleClickGrid","","monthwiseGrid");
		return true;
	}
	
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}
function nearmissmonthgrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("nearmissmonthgrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
	
}
function doubleClickGrid(id){ 
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("nearmissmonthgrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function monthwiseGrid()
{
	setDrillDownHeader("CH0-0","nearmissmonthgrid","keyid");
	setTotalRowCss('nearmissmonthgrid');	
}
</script>
<form id="frmNearmissmonth">
<div id="wrapperRpt">
	<div>
		<table style="width: 1021px;"><tr>
			
			<td >
				<span  style="margin-left:%;">
					<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>		
				</span>
			</td>
			<td align="left">
				<span style="margin-top: 0px;">
					<label class="notes" style="font-weight: bold; "> Double Click on Company/Location/SBU/PBU/DMT/JH to Drilldown</label>
				</span>
			</td>
		</tr></table>
		
	</div>
	<table id="nearmissmonthgrid" ></table>
	<div id="monthpager"></div>
</div>
<input type="hidden" id="hdnFnlnKeyid" />

</form>
	
	