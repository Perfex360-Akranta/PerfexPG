<script type="text/javascript">
jQuery(function () {
  setLoadFormCallBackFrmId("frmOplCumulativeReport");
  invokeAfterLoadFormCallBack();
});

function frmOplCumulativeReport_afterLoadCallBack(){ 
  toggleCommonFilter();
}

function viewGrid(url, filterString) {
  if (validateFilterSelection(filterString)) {
    jQuery("#hiddenRemoveBlank").val(getFilterValue(filterString, "chkRemoveBlank"));
    filterString += '&drillFlag=f&firstClick=Y';
    jQuery('#hdnFilterString').val(filterString);
    processGridnew(url, filterString, "oplGrid", "pager", "Cummulative (opl) Report",
      "oplGrid_doubleClickGrid", "", "oplcumulativeOnload");
    return true;
  }
}

function oplGrid_doubleClickGrid(id) {
  var keyfieldData = jQuery("#hdnFnlnKeyid").val();
  keyfieldData = setDrillDoubleClick("oplGrid", "keyid2", id, keyfieldData);
  jQuery("#hdnFnlnKeyid").val(keyfieldData);
}

function oplcumulativeOnload() {
  jQuery("#oplGrid").setGridParam({
    onCellSelect:function(id,cellidx){
      var colModel = jQuery("#oplGrid").jqGrid('getGridParam', 'colModel');
      var name = (colModel[cellidx].name || "");
      setFieldValue('hiddenselmonth', name.substr(0,8));
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

function validateFilterSelection(){ return true; }

/* === Graph click (jQuery 1.6.4 compatible) === */
jQuery('#btnGraph').unbind('click').bind('click', function () {
  var action = "chart.oplcum";
  var qs = (jQuery('#hdnFilterString').val() || "").replace(/^[?&]/,'');
  if (typeof showGraphData === 'function') {
    // pass URL with existing filter string if present
    showGraphData(qs ? (action + '?' + qs) : action);
  } else {
    // fallback: navigate directly if framework helper isn't available
    window.location.href = qs ? (action + '?' + qs) : action;
  }
});


/* === View Data click (jQuery 1.6.4 compatible) === */
jQuery('#btnData').unbind('click').bind('click', function () {
  // 1) Must have a selected row
  var rowid = jQuery("#oplGrid").jqGrid('getGridParam','selrow');
  if (!rowid) { alert("No Data Selected"); return; }

  // 2) Get FLID from selected row (keyid2)
  var compId   = (jQuery("#oplGrid").jqGrid('getRowData',rowid).keyid2 || "");
  var celldata = compId.split("#");
  if (!celldata[0]) { alert("Select the Correct Cell to View The Data"); return; }
  var flid = celldata[0];

  // 3) Base filter string saved earlier
  var base = (jQuery('#hdnFilterString').val() || "").replace(/^[?&]/, "");

  // Remove any old month/date/drill params inline (no helpers)
  (function(){
    var s = "&" + base;
    var names = [
      "dtFromMonth","dtToMonth","FROMMONTH","TOMONTH","fromMonth","toMonth","month","selMonth",
      "dtFromDate","dtToDate","FROMDATE","TODATE","opFromDt","opTillDt","drillFlag","firstClick","chkMonthwise"
    ];
    for (var i=0;i<names.length;i++){
      var re = new RegExp("([&])" + names[i] + "=[^&]*","gi");
      s = s.replace(re,"$1");
    }
    base = s.replace(/^&/,"").replace(/&&+/g,"&").replace(/[&?]$/,"");
  })();

  // 4) Get month header captured by onCellSelect
  var month = (jQuery("#hiddenselmonth").val() || "").trim();

  if (month && month !== "Company2") {
    // Normalize to Mon-YYYY (e.g., Jun-2023)
    var mUp = month.replace("/", "-").toUpperCase();
    var p   = mUp.split("-");
    var map = {JAN:"Jan",FEB:"Feb",MAR:"Mar",APR:"Apr",MAY:"May",JUN:"Jun",JUL:"Jul",AUG:"Aug",SEP:"Sep",OCT:"Oct",NOV:"Nov",DEC:"Dec"};
    var mon = map[(p[0]||"").substr(0,3)] || "Jan";
    var yr  = (p[1]||"1970"); if (yr.length === 2) yr = "20" + yr;
    var monNorm = mon + "-" + yr;

    // Compute DD-Mon-YYYY start/end for that month
    var idxMap = {Jan:0,Feb:1,Mar:2,Apr:3,May:4,Jun:5,Jul:6,Aug:7,Sep:8,Oct:9,Nov:10,Dec:11};
    var mi     = idxMap[mon] || 0;
    var yyyy   = parseInt(yr,10) || 1970;
    var dS     = new Date(yyyy, mi, 1);
    var dE     = new Date(yyyy, mi+1, 0);
    var M      = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    function fmt(d){ return ("0"+d.getDate()).slice(-2)+"-"+M[d.getMonth()]+"-"+d.getFullYear(); }
    var fromDate = fmt(dS);
    var toDate   = fmt(dE);

    // Append the exact params your backend expects
    var s = "&" + base;
    if (s.length && s.charAt(s.length-1) !== "&") s += "&";
    s += "chkMonthwise=1&drillFlag=t&firstClick=Y";
    s += "&dtFromMonth="+encodeURIComponent(monNorm)+"&dtToMonth="+encodeURIComponent(monNorm);
    s += "&FROMMONTH="+encodeURIComponent(monNorm)+"&TOMONTH="+encodeURIComponent(monNorm);
    s += "&opFromDt="+encodeURIComponent(fromDate)+"&opTillDt="+encodeURIComponent(toDate);
    s += "&dtFromDate="+encodeURIComponent(fromDate)+"&dtToDate="+encodeURIComponent(toDate);
    s += "&FROMDATE="+encodeURIComponent(fromDate)+"&TODATE="+encodeURIComponent(toDate);
    base = s.replace(/^&/,"");
  } else {
    // Ensure drill flags even without a month header click
    var s2 = "&" + base;
    s2 = s2.replace(/([&])drillFlag=[^&]*/gi,"$1").replace(/([&])firstClick=[^&]*/gi,"$1");
    if (s2.length && s2.charAt(s2.length-1) !== "&") s2 += "&";
    s2 += "drillFlag=t&firstClick=Y";
    base = s2.replace(/^&/,"");
  }

  // 5) Final filter string
  var filterStr = "&flid=" + flid + (base ? "&" + base.replace(/^[&?]/,"") : "");
  filterStr = filterStr.replace(/&{2,}/g,"&");

  // 6) Build companion filterString for input form
  var url = jQuery("#oplGrid").jqGrid('getGridParam','url') || "";
  url = url.replace("OplCummulative_getData.oplcum","OplCummulative_input.oplcum");
  url = url.replace(/([?&])flid=[^&]*/gi,"$1").replace(/[?&]$/,"");
  url += (url.indexOf('?')>=0 ? '&' : '?') + "flid=" + encodeURIComponent(flid);

  // 7) Navigate (use existing framework; fallback to location)
  var target = "oplTransaction_input.oplcum?filterStr=" + escape(filterStr);
  try {
    if (typeof navigateToNextForm === 'function') {
      navigateToNextForm(target, "OPL View", null, {"filterString": url});
    } else {
      window.location.href = target;
    }
  } catch (e) {
    window.location.href = target;
  }
});
</script>



<form id="frmOplCumulativeReport">
  <div id="wrapperRpt" style="max-width: 1210px;">
    <table>
      <tr>
        <td>
          <div style="margin-top: -28px">
            <input type="button" class="easyui-button" value="Graph" id="btnGraph" name="btnGraph" />
          </div>
        </td>
        <td>
          <label id="oplnotes" class="notes" style="font-weight: bold; margin-left: 10px; display: none;">
            ${requestScope.oplCumMsg}
          </label>
        </td>
        <td>
          <div style="margin-top: -28px">
            <input type="button" class="easyui-button" value="View Data" id="btnData" name="btnData" />
            <label class="notes" style="font-weight: bold;">Click The Cell To View Data</label>
          </div>
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

  <input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
  <input type="hidden" id="hiddenRemoveBlank" value="" />
  <input type="hidden" id="hdnFnlnKeyid" />
  <input type="hidden" id="hiddenselmonth" value="asd" />
</form>
