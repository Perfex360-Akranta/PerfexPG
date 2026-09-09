
<script type="text/javascript">
	jQuery(document).ready(function() {
		var actionPart = jQuery('#hiddenUrl').val();
		//viewGrid(actionPart, "");
		//viewGrid(actionPart, "?row=2");
		setLoadFormCallBackFrmId("frmQpointCumulativeReport");
		invokeAfterLoadFormCallBack();
		});
	function frmQpointCumulativeReport_afterLoadCallBack(){ 
		toggleCommonFilter();
		} 

	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
			jQuery("#hiddenRemoveBlank").val(removeBlank);

			filterString += '&drillFlag=f&firstClick=Y';
			jQuery('#hdnFilterString').val(filterString);
			var tableCaption = "Cummulative (opl) Report";
			processGridnew(url, filterString, "oplGrid", "pager", tableCaption,
					"oplGrid_doubleClickGrid", "", "oplcumulativeOnload");
			return true;
		}
	}

	function oplGrid_doubleClickGrid(id) {
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		keyfieldData = setDrillDoubleClick("oplGrid", "keyid2", id,
				keyfieldData);
		jQuery("#hdnFnlnKeyid").val(keyfieldData);
	}
	function oplcumulativeOnload() {
		 jQuery("#oplGrid").setGridParam({
             onCellSelect:function(id,cellidx,cellvalue) {
             
                     var colModel = jQuery("#oplGrid").jqGrid('getGridParam', 'colModel');
                     var dateval =  colModel[cellidx].name ;
                     dateval = dateval.substr(0,8);
                     //alert("dateval :" +dateval);
                     setFieldValue('hiddenselmonth',dateval);
             }
                     
             });
		setDrillDownHeader("jqgh_oplGrid_Company2", "oplGrid", "keyid2");
		setTotalRowCss('oplGrid');
		
		
	}
	
	function oplGrid_onProcessGridBack() {
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		setDrillProcesGridBack("oplGrid", "keyid2", keyfieldData);
		jQuery("#hdnFnlnKeyid").val("");
		
	}

	function validateFilterSelection(filterString) {

		return true;
	}

	jQuery('#btnGraph').click(function() {
		showGraphData("chart.qp");
	});
	
	jQuery('#btnData').click(function() {
	
		var rowid = jQuery("#oplGrid").jqGrid('getGridParam','selrow');
		
		if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
			alert("No Data Selected");
			return false;
		}
		//alert("Row Id:" + rowid);
		var rowData = jQuery("#oplGrid").jqGrid('getRowData',rowid);
		//alert("Row Data" +rowData);
		var compId = rowData.keyid2;
	
		var rowIds = jQuery("#oplGrid").getDataIDs();
	
		//alert("Row Ids :" + rowIds);
		
		var parentId = jQuery("#oplGrid").jqGrid('getCell',	rowIds[0], 'keyid2');
	
		var celldata = compId.split("#");
	
		var filterStr = '';
		
		if(celldata[0]  == null || celldata[0] == ' ' || celldata[0] == ''|| celldata[0] == undefined){
			alert("Select the Correct Cell to View The Data");
			return false;
		}
		
		filterStr += "&flid=" + celldata[0];
		//alert("celldata[0]: " +celldata[0]);
		
		var rowids = jQuery("#hiddenStr").val();
		var month= jQuery("#hiddenselmonth").val();
		//alert("hiddenselmonth :" +month);
	
		var url = jQuery("#oplGrid").jqGrid('getGridParam',	'url');
	
		url = url.replace("OplCummulative_getData.oplcum","OplCummulative_input.oplcum");
	
		url = url + filterStr;
	
		url = escape(url);
	
		url = removeValueFromUrl(url, "flid");
		
		filterStr+=jQuery('#hdnFilterString').val();
		if(month!="Company2"){
			filterStr += "&dtFromMonth=" + month + "&dtToMonth=" +month;
		}
	  //  alert("filterStr test:" + filterStr);
		navigateToNextForm("oplTransaction_input.oplcum?"	+ "filterStr=" + escape(filterStr), "OPL View",	null, {	"filterString" : url});
		
	});
</script>
<form>
	<div id="wrapperRpt" style="max-width: 1210px;">
		<table>
			<tr>
				<td>
					<div style="margin-top: -28px">
						<input type="button" class="easyui-button" value="Graph"
							id="btnGraph" name="btnGraph" />
					</div>
				</td>
				<td><label id="oplnotes" class="notes"
					style="font-weight: bold; margin-left: 10px; display: none;">${requestScope.oplCumMsg}</label>
				</td>
				<td>

				</td>
			</tr>
		</table>


		<div style="margin-top: -5px">
			<div id="divGraphContainer"></div>
			<table id="oplGrid"></table>
			<div id="pager"></div>
			
		</div>
		<input type="hidden" id="hiddenStr" value="" />
	</div>
	<input type="hidden" id="hdnFilterString" name="hdnFilterString"
		value="" /> <input type="hidden" id="hiddenRemoveBlank" value="" />
	<input type="hidden" id="hdnFnlnKeyid" />
	<input type="hidden" id="hiddenselmonth" value="asd"  />

</form>