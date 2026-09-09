 <script>
jQuery(document).ready(function(){
	initialiseForm('frmCustComplaintCount');
	var actionPart = jQuery('#hiddenUrl').val();
	viewGrid(actionPart,"?q=1");
	jQuery('#btnBarGraph').click(function(){
		    var rowid = jQuery("#CustComplaintountgrid").jqGrid('getGridParam','selrow');
			var url = "CustComplaintchartcount.compg";
			//alert("The URL"+url);
			showGraphData(url);
		});
	});




function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		
		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"CustComplaintountgrid","monthpager",tableCaption,"doubleClickGrid","","incidentcumGrid");
		return true;
	}
	
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}
function incidentcumcountgrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("CustComplaintountgrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
	
}
function doubleClickGrid(id){ 
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("CustComplaintountgrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function incidentcumGrid()
{
	setDrillDownHeader("CH0-0","CustComplaintountgrid","keyid");
	setTotalRowCss('incidentcountgrid');	
}
</script>
<form id="frmCustComplaintCount">
<div id="wrapperRpt">
	<div>
		<table style="width: 1021px;"><tr>
			
			<td >
				<span  style="margin-left:%;">
					<input id="btnBarGraph" class="easyui-button"  type="button" value="Bar Graph"/>		
				</span>
			</td>
			<td align="left">
				<span style="margin-top: 0px;">
					
				</span>
			</td>
		</tr></table>
		
	</div>
	<table id="CustComplaintountgrid" ></table>
	<div id="monthpager"></div>
</div>
<input type="hidden" id="hdnFnlnKeyid" />
</form>
	
	