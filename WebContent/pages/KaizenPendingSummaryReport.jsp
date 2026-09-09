<script type="text/javascript">
	jQuery(document).ready(function() {
		
		setLoadFormCallBackFrmId("frmKaizenPendgSumDrilldown");
		invokeAfterLoadFormCallBack();
	
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var filterString = '?=2&drillFlag=f&firstClick=Y';
		var tableCaption = "DrillDown (KZN) Report";
		processGridnew("kaizenpendingreport_input.krsr" , filterString, "pndKznGrid", "pager", tableCaption,
				"doubleClickGrid", "", "pndKznGrid_loadComplete");

	});
	
	

	
	

	function viewGrid(url,filterString)
	{	
		if( validateFilterSelection(filterString))
		{
			
			filterString+="&drillFlag=f&firstClick=N";	
		//	alert("123  "+filterString);
			var tableCaption = "Kaizen Drill Down";
			processGridnew("kaizenpendingreport_input.krsr" , filterString, "pndKznGrid", "pager", tableCaption,
					"doubleClickGrid", "", "pndKznGrid_loadComplete","");
			//function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback,selectRowFunction ){

			return true;
		}
		return false;
	}
function frmKaizenPendgSumDrilldown_afterLoadCallBack(){
		toggleCommonFilter();
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

	function pndKznGrid_onProcessGridBack() {
		("kznGrid_onProcessGridBack");
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		setDrillProcesGridBack("pndKznGrid", "DISPKEYID1", keyfieldData);
		jQuery("#hdnFnlnKeyid").val("");

		
	}

 	/*
 	function doubleClickGrid(id) {
		alert(12);
		var keyfieldData = jQuery("#hdnFnlnKeyid").val();
		alert(13 +" keyfieldData "+keyfieldData);
		var flid = jQuery('#pndKznGrid').jqGrid ('getCell', id, 'KEYIDFIELD1');

	flid=flid.substr(0,12);
	alert(flid);
		keyfieldData = setDrillDoubleClick("pndKznGrid", "KEYIDFIELD1", id,keyfieldData);
		alert(keyfieldData);
		jQuery("#hdnFnlnKeyid").val(keyfieldData);

		
	} */
	
	
	function doubleClickGrid(id){ 
		
	//alert( 123);
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	var rowId=jQuery('#pndKznGrid').jqGrid("getGridParam", 'selrow');
	//alert(rowId);
	var keyid = jQuery('#pndKznGrid').jqGrid ('getCell', rowId, 'DISPKEYID1');
	var flid = jQuery('#pndKznGrid').jqGrid ('getCell', rowId, 'Flid4');
  //  alert(flid);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
	//var celValue = jQuery('#pndKznGrid').jqGrid('getCell', rowId, 'KEYID2');
	//var cellId = celValue.substr(0,);
	
	var url="kaizenpendingreport_input.krsr";
	//var filterData="?q=2&flid="+flid.substr(0,12);
	//filterString+="&drillFlag=f&firstClick=N";	

	var filterString = "?q=2&drillFlag=f&firstClick=N";
	 filterString += getAllFilterValues()  ;
  //  filterString += "?q=2&drillFlag=f&firstClick=Y";
	filterString += "&flids="+flid.substr(0,12)+"&Keyid="+keyid;
	//alert(filterString);
	
	if(keyid.substr(0,3)=="CEL"){
		url="kaizenpendingreportDtl_input.krsr";
		processGridnew(url,filterString+"&dtl=DTL","pndKznGrid","pager","","","","pndKznGrid_loadComplete");	

	}
	else{
	processGridnew(url,filterString,"pndKznGrid","pager","","doubleClickGrid","","pndKznGrid_loadComplete");	
	}

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
<form id="frmKaizenPendgSumDrilldown">
<div id="wrapperRpt">
	<div class="clear"></div>

<label class="notes" style="font-weight: bold; padding-left: 20px;">
		${requestScope.DrillDown}</label>

	
	<table id="pndKznGrid"></table>
	<div id="pager"></div>
	
	<input type="hidden" id="hiddenStr" value="" />
	 <input type="hidden"	id="hdnFnlnKeyid" /> 
		<input type="hidden" id="hdnPrevDataUrl"
		name="hdnPrevDataUrl" value="${requestScope.filterStr}" /> <input
		type="hidden" id="hiddencircle" value="" />
		
	<input type="hidden" id="hdnFilterStr" name="hdnFilterStr" value=""/>
</div>
</form>