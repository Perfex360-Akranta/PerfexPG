<script type="text/javascript">
	jQuery(document).ready(function() {
		setLoadFormCallBackFrmId("frmKaizenSumDrilldown");
		invokeAfterLoadFormCallBack();
		var actionPart = jQuery('#hiddenUrl').val();
		//viewGrid(actionPart,"?drillFlag=f");
		//alert("Improvmntsmry.jsp");
		var prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		if (prevDataUrl == null || prevDataUrl.length <= 0){
			//viewGrid("ImpSmry_input.impSmrRpt", "?q&");
			//alert("prevDataUrl:" +prevDataUrl);
			
			//viewGrid("ImpSmry_input.impSmrRpt", "");
		
		}else {
			//alert("prevDataUrl else:" +prevDataUrl);
			//viewGrid(unescape(prevDataUrl), "?q=1");
			viewGrid(unescape(prevDataUrl), "");
		}

	});
	jQuery('#btnViewSuggestion')
	.click(
			function() {
				var rowid = jQuery("#kznGrid").jqGrid('getGridParam','selrow');

				if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
					alert("No Data Selected");
					return false;
				}
				var rowData = jQuery("#kznGrid").jqGrid('getRowData',rowid);
				var compId = rowData.KEYIDFIELD1;
				var rowIds = jQuery("#kznGrid").getDataIDs();
				var parentId = jQuery("#kznGrid").jqGrid('getCell',	rowIds[0], 'KEYIDFIELD1');
				var celldata = compId.split("#");
				var filterStr = '';

				filterStr += "&flid=" + celldata[0];
				//alert(celldata[0]);
				//var datStr = "?q=1&";
				var rowids = jQuery("#hiddenStr").val();
				//alert("Row Id :" +rowids);
				var url = jQuery("#kznGrid").jqGrid('getGridParam',	'url');
				//alert("url:" + url);
				url = url.replace("ImpSmry_getData.impSmrRpt","ImpSmry_input.impSmrRpt");
				//alert("url:" + url);
				url = url + filterStr;
				//alert("url:" + url);
				url = escape(url);
				//alert("url:" + url);
				url = removeValueFromUrl(url, "flid");
				
				filterStr+=jQuery('#hdnFilterStr').val();
				filterStr+="&drillDown=Y";
				//alert("filterStr test:" + filterStr);
				navigateToNextForm("ImpSmryView_input.impSmrRpt?"	+ "filterStr=" + escape(filterStr), "Kaizen Suggestion",	null, {	"filterString" : url});
				//viewGrid(url,filterString);

			});
	jQuery('#btnViewImplemented')
	.click(
			function() {
				var rowid = jQuery("#kznGrid").jqGrid('getGridParam','selrow');

				if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
					alert("No Data Selected");
					return false;
				}
				var rowData = jQuery("#kznGrid").jqGrid('getRowData',rowid);
				var compId = rowData.KEYIDFIELD1;
				var rowIds = jQuery("#kznGrid").getDataIDs();
				var parentId = jQuery("#kznGrid").jqGrid('getCell',	rowIds[0], 'KEYIDFIELD1');
				var celldata = compId.split("#");
				var filterStr = '';

				filterStr += "&flid=" + celldata[0];
				//alert(celldata[0]);
				//var datStr = "?q=1&";
				var rowids = jQuery("#hiddenStr").val();
				//alert("Row Id :" +rowids);
				var url = jQuery("#kznGrid").jqGrid('getGridParam',	'url');
				//alert("url:" + url);
				url = url.replace("ImpSmry_getData.impSmrRpt","ImpSmry_input.impSmrRpt");
				//alert("url:" + url);
				url = url + filterStr;
				//alert("url:" + url);
				url = escape(url);
				//alert("url:" + url);
				url = removeValueFromUrl(url, "flid");
				
				filterStr+=jQuery('#hdnFilterStr').val();
				//alert("filterStr test:" + filterStr);
				navigateToNextForm("ImpSmrykaizen_input.impSmrRpt?"	+ "filterStr=" + escape(filterStr), "Implemented Kaizen",	null, {	"filterString" : url});
				//viewGrid(url,filterString);

			});


	
	jQuery('#subGroup')
			.click(
					function() {
						var rowid = jQuery("#kznGrid").jqGrid('getGridParam','selrow');

						if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
							alert("No Data Selected");
							return false;
						}
						var rowData = jQuery("#kznGrid").jqGrid('getRowData',rowid);
						var compId = rowData.KEYIDFIELD1;
						var rowIds = jQuery("#kznGrid").getDataIDs();
						var parentId = jQuery("#kznGrid").jqGrid('getCell',	rowIds[0], 'KEYIDFIELD1');
						var celldata = compId.split("#");
						var filterStr = '';

						/*if(compId.substr(0,3) == 'CMP')
							filterStr += "compId="+celldata[0];
						else if(parentId.substr(0,3) == 'LCN')
							filterStr += "locaId="+compId;	
						else if(compId.substr(0,3) == 'FCT')
							filterStr += "factId="+compId;	
						else if(compId.substr(0,3) == 'LIN')
							filterStr += "sectId="+compId;
						else if(compId.substr(0,3) == 'CEL')
							filterStr += "cellId="+compId;
						else if(compId.substr(0,3) == 'MCH')
							filterStr += "mchId="+compId;*/
						filterStr += "&subGrp=R";

						filterStr += "&flid=" + celldata[0];
						//alert(celldata[0]);
						var datStr = "?q=1&";
						var rowids = jQuery("#hiddenStr").val();
						//alert("Row Id :" +rowids);
						var url = jQuery("#kznGrid").jqGrid('getGridParam',	'url');
						//alert("url:" + url);
						url = url.replace("ImpSmry_getData.impSmrRpt","ImpSmry_input.impSmrRpt");
						//alert("url:" + url);
						url = url + filterStr;
						//alert("url:" + url);
						url = escape(url);
						//alert("url:" + url);
						url = removeValueFromUrl(url, "flid");
						
						filterStr+=jQuery('#hdnFilterStr').val();
						//alert("filterStr test:" + filterStr);
						navigateToNextForm("ImpSmrySubGrp_input.impSmrRpt?"	+ "filterStr=" + escape(filterStr), "SubGroup",	null, {	"filterString" : url});

					});
	function frmFilter_enableDisableSuccessCallBack() {
		//alert('inside');
		jQuery('input:checkbox[name=chkDatewise]').attr('checked', false);

	}
	function viewGrid(url, filterString) {
		//alert(1);
		if (validateFilterSelection(filterString)) {
			// alert("Test 2:" +filterString);
			 
			jQuery('#hdnFilterStr').val(filterString);
			var circle = getFilterValue(filterString, "cmbCircle");
			jQuery("#hiddencircle").val(circle);
			filterString += '&drillFlag=f&firstClick=Y';
			var tableCaption = "DrillDown (KZN) Report";
			processGridnew(url , filterString, "kznGrid", "pager", tableCaption,
					"doubleClickGrid", "", "ImprovementSmry_loadComplete");
			return true;
		}
		return false;
	}
	function frmKaizenSumDrilldown_afterLoadCallBack(){
		toggleCommonFilter();
		}
		
	function ImprovementSmry_loadComplete() {
		

		setDrillDownHeader("kznGrid_Company2", "kznGrid", "KEYIDFIELD1");
		setTotalRowCss('kznGrid');

		

	}
	function validateFilterSelection(filterString) {
		if (filterString == "?q=1&firstClick=Y"	|| filterString.substring(0, 4) == "?q=1")
			return true;
		
		if (jQuery('#chkDatewise').is(':checked') == true) {
			if (filterString.length > 0
					&& !checkFilterValueExist(filterString, "dtFromDate")) {
				alert("Select  FromDate");
				return false;
			}
			/*else{
				 
				jQuery('#hdnfromDate').val(jQuery('#dtefromDate').datebox('getValue'));
			}*/
			if (filterString.length > 0 && !checkFilterValueExist(filterString, "dtToDate")) {
				alert("Select  ToDate");
				return false;
			}
		}
		return true;
	}

	function kznGrid_onProcessGridBack() {
		//alert("kznGrid_onProcessGridBack");
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		setDrillProcesGridBack("kznGrid", "KEYIDFIELD1", keyfieldData);
		jQuery("#hdnFnlnKeyid").val("");

		
	}

	function doubleClickGrid(id) {
		
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		keyfieldData = setDrillDoubleClick("kznGrid", "KEYIDFIELD1", id,keyfieldData);
		//alert(keyfieldData);
		jQuery("#hdnFnlnKeyid").val(keyfieldData);

		
	}
	function getParamName(rowid) {
		if (rowid.substring(0, 3) == 'CMP')
			return 'cmbCompid';
		else if (rowid.substring(0, 3) == 'LCN')
			return 'cmbLocnid';
		else if (rowid.substring(0, 3) == 'FCT')
			return 'cmbFactid';
		else if (rowid.substring(0, 3) == 'LIN')
			return 'cmbSectid';
		else if (rowid.substring(0, 3) == 'CEL')
			return 'cmbCellid';
		else if (rowid.substring(0, 3) == 'MCH')
			return 'cmbMchid';
		else
			return null;
	}

	function frmFilter_enableDisableSuccessCallBack() {
		jQuery('input:checkbox[name=chkDatewise]').attr('checked', true);
		jQuery('input:checkbox[name=chkMonthwise]').attr('checked', false);

		jQuery('#chkDatewise').click(function() {
			if (jQuery('#chkDatewise').is(':checked') == false) {
				jQuery("#dtefromDate").datebox('disable');
				jQuery("#dtetoDate").datebox('disable');
				//alert("Select Datewise Checkbox");			
			}

		});

	}
</script>
<form id="frmKaizenSumDrilldown">
<div id="wrapperRpt">
	<div class="clear"></div>


	<span style=""> <input type="button" class="easyui-button"
		id="subGroup" value="Sub Group" />
	</span> <label class="notes" style="font-weight: bold; padding-left: 20px;">
		${requestScope.DrillDown}</label>
	<span style=""> <input type="button" class="easyui-button"
		id="btnViewSuggestion"  value="Suggestions" />
	<span style=""> <input type="button" class="easyui-button"
		id="btnViewImplemented"  value="Implemented Kaizen" />
	
	<table id="kznGrid"></table>
	<div id="pager"></div>
	<input type="hidden" id="hiddenStr" value="" /> <input type="hidden"
		id="hdnFnlnKeyid" /> <input type="hidden" id="hdnPrevDataUrl"
		name="hdnPrevDataUrl" value="${requestScope.filterStr}" /> <input
		type="hidden" id="hiddencircle" value="" />
		
	<input type="hidden" id="hdnFilterStr" name="hdnFilterStr" value=""/>
</div>