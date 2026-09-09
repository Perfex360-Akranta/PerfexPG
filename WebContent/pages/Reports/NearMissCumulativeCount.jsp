
<script type="text/javascript">
	jQuery(document).ready(function() {
		var actionPart = jQuery('#hiddenUrl').val();
		viewGrid(actionPart, "");
		//viewGrid(actionPart, "?row=2");
	//	setLoadFormCallBackFrmId("frmQpointCumulativeReport");
		});
/* 	function frmQpointCumulativeReport_afterLoadCallBack(){ 
		toggleCommonFilter();
		}  */

	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
			jQuery("#hiddenRemoveBlank").val(removeBlank);

			filterString += '&drillFlag=f&firstClick=Y';
			jQuery('#hdnFilterString').val(filterString);
			var tableCaption = "Cummulative (Near Miss) Report";
			processGridnew(url, filterString, "NearmisscumGrid", "pager", tableCaption,
					"", "", "NearmisscumulativeOnload");
			return true;
		}
	}

	function NearmisscumGrid_doubleClickGrid(id) {
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		keyfieldData = setDrillDoubleClick("NearmisscumGrid", "keyid2", id,
				keyfieldData);
		jQuery("#hdnFnlnKeyid").val(keyfieldData);
	}
	function NearmisscumulativeOnload() {
		 jQuery("#NearmisscumGrid").setGridParam({
             onCellSelect:function(id,cellidx,cellvalue) {
             
                     var colModel = jQuery("#NearmisscumGrid").jqGrid('getGridParam', 'colModel');
                     var dateval =  colModel[cellidx].name ;
                     dateval = dateval.substr(0,8);
                     //alert("dateval :" +dateval);
                     setFieldValue('hiddenselmonth',dateval);
             }
                     
             });
		setDrillDownHeader("jqgh_NearmisscumGrid_Company2", "oplGrid", "keyid2");
		setTotalRowCss('NearmisscumGrid');
		
		
	}
	
	function NearmisscumGrid_onProcessGridBack() {
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		setDrillProcesGridBack("NearmisscumGrid", "keyid2", keyfieldData);
		jQuery("#hdnFnlnKeyid").val("");
		
	}

	function validateFilterSelection(filterString) {

		return true;
	}

	jQuery('#btnGraph').click(function() {
		showGraphData("NearmissCumchart.nnrm");
	});
	
	jQuery('#btnData').click(function() {
	
		var rowid = jQuery("#NearmisscumGrid").jqGrid('getGridParam','selrow');
		
		if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
			alert("No Data Selected");
			return false;
		}
		//alert("Row Id:" + rowid);
		var rowData = jQuery("#NearmisscumGrid").jqGrid('getRowData',rowid);
		//alert("Row Data" +rowData);
		var compId = rowData.keyid2;
	
		var rowIds = jQuery("#NearmisscumGrid").getDataIDs();
	
		//alert("Row Ids :" + rowIds);
		
		var parentId = jQuery("#NearmisscumGrid").jqGrid('getCell',	rowIds[0], 'keyid2');
	
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
	
		var url = jQuery("#NearmisscumGrid").jqGrid('getGridParam',	'url');
	
		url = url.replace("NewNearMissCumCount_getData.nnrm","NewNearMissCumCount_input.nnrm");
	
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
						<input type="button" class="easyui-button" value="Line Graph"
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
			<table id="NearmisscumGrid"></table>
			<div id="pager"></div>
			
		</div>
		<input type="hidden" id="hiddenStr" value="" />
	</div>
	<input type="hidden" id="hdnFilterString" name="hdnFilterString"
		value="" /> <input type="hidden" id="hiddenRemoveBlank" value="" />
	<input type="hidden" id="hdnFnlnKeyid" />
	<input type="hidden" id="hiddenselmonth" value="asd"  />

</form>