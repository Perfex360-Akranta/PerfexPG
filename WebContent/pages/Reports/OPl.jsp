<script type="text/javascript">
jQuery(document).ready(function () {

  // Ensure the framework calls Excelview_afterLoadCallBack()
  setLoadFormCallBackFrmId("Excelview");
  invokeAfterLoadFormCallBack();

  // Delegated dbl-click fallback: always works even if grid is reloaded
  jQuery(document)
    .off('dblclick.opl', '#list tr.jqgrow')
    .on('dblclick.opl', '#list tr.jqgrow', function () {
      var id = this.id;
      if (id) doubleClickGrid(id);
    });

  // (If you need an immediate load somewhere else, call viewGrid there)
});

// Called by the framework after the form finishes loading
function Excelview_afterLoadCallBack() {
  toggleCommonFilter();

  // If processGridnew didn't wire ondblClickRow, wire it here
  if (jQuery("#list").data("jqGrid")) {
    jQuery("#list").jqGrid('setGridParam', {
      ondblClickRow: function (id) { doubleClickGrid(id); }
    });
  }
}

function viewGrid(url, filterString) {
  if (validateFilterSelection(filterString)) {
    var tableCaption = "OPL Report";
    // IMPORTANT: pass exact function name without trailing space
    processGridnew(url, filterString, "list", "pager", tableCaption, "doubleClickGrid");
    return true;
  }
  return false;
}

function validateFilterSelection(filterString) {
  if (filterString == "?q=") return true;

  if (getFilterValue(filterString, "flid") == null ||
      getFilterValue(filterString, "flid") == " " ||
      getFilterValue(filterString, "flid") == "") {
    alert("Select JH");
    return false;
  }

  var actionPart = jQuery('#hiddenUrl').val();
  if (actionPart.indexOf('filter') < 0) {
    if (jQuery('#chkDatewise').is(':checked') == false &&
        jQuery('#chkMonthwise').is(':checked') == false) {
      alert("Select Date or Month ");
      return false;
    }
  }
  return true;
}

function doubleClickGrid(id) {
  var rowData = jQuery("#list").jqGrid('getRowData', id);
  var opl = rowData.TXTOPLNO || rowData.OPLNO ||
            jQuery("#list").jqGrid('getCell', id, "TXTOPLNO") ||
            jQuery("#list").jqGrid('getCell', id, "OPLNO");

  if (!opl) {
    alert("OPL number not found in the selected row.");
    return;
  }

  var flid = jQuery('#hdnLoginFlid').val() || '';
  var url = "OplReport_Excelview.oplrpt?oplId=" + encodeURIComponent(opl) +
            "&flid=" + encodeURIComponent(flid) + "&type=other";
  window.open(url, "_blank"); // open in new tab/window
}

function frmFilter_enableDisableSuccessCallBack() {
  // (left intentionally as-is; no changes needed)
}
</script>

<form id="Excelview" method="post" action="OplReport_view.oplrpt">
  <div id="wrapperRpt">
    <label class="notes" style="font-weight: bold; padding-left:0px;">
      ${requestScope.excelView}
    </label>
    <table id="list"></table>
    <div id="pager"></div>
    <input type="hidden" id="oplId"/>
  </div>
</form>
