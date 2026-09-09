<script type="text/javascript">
	jQuery(document).ready(
			function() {
				var actionPart = jQuery('#hiddenUrl').val();
				viewGrid(actionPart, "?q=1&firstClick=Y");

				jQuery('#btnGraph').click(
						function() {

							var rowid = jQuery("#internal").jqGrid(
									'getGridParam', 'selrow');

							if (rowid == null || rowid == '') {
								alert("Select the row to view the graph.");
							} else {

								var rowData = jQuery("#internal").jqGrid(
										'getRowData', rowid);

								var id = jQuery("#internal").getDataIDs();

								var selId = rowData.KEYID;

								var colm = jQuery("#internal").jqGrid(
										'getGridParam', 'colModel');
								var cellName = colm[3].name;

								var rowIds = jQuery("#internal").getDataIDs();
								var cellVal = jQuery('#internal').getCell(
										rowIds[rowid - 1], cellName);

								//alert(rowid);

								var url = "chart.irr?rowid=" + rowid;
								showGraphData(url);
							}

						});

			});

	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {

			filterString += '&drillFlag=f';
			filterString += '&firstClick=Y';
			filterString += '&parentId='
					+ getFilterValue(filterString, "cmbdefphen");
			filterString += '&processId='
					+ getFilterValue(filterString, "cmbprocess");
			var tableCaption = "DrillDown (JH) Report";
			processGridnew(url, filterString, "internal", "pager",
					tableCaption, "internal_doubleClickGrid", "",
					"load_Complete");

			return true;
		}
		return false;
	}

	function validateFilterSelection(filterString) {
		return true;
	}

	function internal_onProcessGridBack() {

		var url = jQuery('#hiddenUrl').val();
		var rowIds = jQuery("#internal").getDataIDs();

		var parentId = jQuery("#internal")
				.jqGrid('getCell', rowIds[4], 'KEYID');

		var filterData = "?";
		filterData += '&parentId=' + parentId + '&drillFlag=b'
				+ '&firstClick=N' + '&doubleClick=Y';
		//alert("filterData"+filterData);

		if (parentId != null && parentId.length > 0) {

			if (parentId.substr(0, 3) == 'FCT') {
				return;
			}
		}
		processGridnew(url, filterData, "internal", "pager", '',
				"internal_doubleClickGrid", "", "load_Complete");

	}

	function internal_doubleClickGrid(id) {

		var rowData = jQuery("#internal").jqGrid('getRowData', id);
		var selId = rowData.KEYID;
		jQuery("#hiddenDrillLvl").val(selId);
		try {
			fillDrilFunctlocCombo(selId);
		} catch (Exception) {
		}
		if (checkForZeroes("internal", id, 1)) {
			if (selId.substr(0, 3) != 'QCM' && selId.trim() != "") {
				var filterData = "?";

				filterData += '&parentId=' + selId + '&drillFlag=f'
						+ '&firstClick=N&';
				filterData += getParamName(selId) + "=" + selId;
				filterData += '&doubleClick=Y';

				var url = jQuery('#hiddenUrl').val();
				processGridnew(url, filterData, "internal", "pager", '',
						"internal_doubleClickGrid", "", "load_Complete");
			}
			if (selId.substr(0, 3) == 'QCM') {

			}
		} else
			alert("No Records to View");

	}

	function checkForZeroes(tableId, selId, colNo) {
		var Col = colNo + 1;

		while (jQuery("#" + tableId).jqGrid('getCell', selId, Col) != null) {
			if (jQuery("#" + tableId).jqGrid('getCell', selId, Col) != '0')
				return true;
			Col++;
		}
		return false;

	}

	/*

	function load_Complete()
	{
	
	setTotalRowCss('internal');
	var url = jQuery('#hiddenUrl').val();
	var rowIds = jQuery("#internal").getDataIDs();
	var parentId = jQuery("#internal").jqGrid('getCell', rowIds[4], 'KEYID');

	if(parentId.substr(0,3) != 'FCT')
		hideShowBack(true);
	else
		hideShowBack(false);
	
	
	
	var colm = jQuery("#internal").jqGrid ('getGridParam', 'colModel');	
	selId = colm[3].name;
	
	
	
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#internal').getCell(rowIds[i],"KEYID");
		cellVal = cellVal.substr(0,3);
		
		if(cellVal == "FCT" || cellVal == "PRS" || cellVal =="QPH" || cellVal == "QCM" )
			{
			for(var j=0;j<colm.length;j++){
				jQuery("#internal").jqGrid('setCell',rowIds[i],colm[j].name,"",{'color':'#000','font-weight':'bold','font-size':11});
			}
		}
		else 
			jQuery("#internal").jqGrid('setCell',rowIds[i],selId,"",{'color':'blue'});
		
	} 	
	
	} 
	 */

	function frmFilter_enableDisableSuccessCallBack() {

		jQuery("#chkMonthwise").attr('checked', true);
		enableFields("chkMonthwise");
		enableFields("chkDatewise");
	}
</script>
<form id="frmInternal">

	<div id="divGraphContainer"></div>


	<div id="wrapperRpt" style="margin-top: 15px;">
		<table>
			<tr>

				<td>
					<div style="margin-top: -16px">
						<input id="btnGraph" class="easyui-button" type="button"
							value="Graph" />
					</div>
				</td>

				<td>
					<div style="margin-top: -16px">
						<label class="notes" style="font-weight: bold;">
							</label>
					</div>
				</td>

			</tr>
			<tr>
				<td colspan="2">
					<div class="clear"></div>
					<table id="internal">
						<tr>
							<td></td>
						</tr>
					</table>
					<div id="pager"></div>
				</td>
			</tr>
		</table>


	</div>
	<input type="hidden" id="hiddenStr" value="${requestScope.drilldownMsg}" />
	 <input type="hidden" id="hiddenDrillLvl" value="" />
</form>