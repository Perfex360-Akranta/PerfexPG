<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">

jQuery(document).ready(function () {

    initialiseForm("frmMultiplePmStd");
    jQuery("#submitForm").val("frmMultiplePmStd");
    
    bindPmsdEditToggleDelegation(); 

    var machId = "${requestScope.machineId}";
    var flid   = "${requestScope.plmTlStandards.pmsdFlid}";
    var tradeId = "${requestScope.tradeId}"; 
    var costCtr = "${requestScope.costcenter}";
    

    jQuery("#hdnPmsdMachId").val(machId);
    jQuery("#hdnPmsdFlid").val(flid);
    jQuery("#hdnPmsdtradeId").val(tradeId);

    /* top-level Cost Center / Equipment combos (replaces the old per-row grid columns) */
    fillComboBox("frmMultiplePmStd", "cmbPmsdMulCostCenter", "costCenter.commonFilter");
    fillComboBox("frmMultiplePmStd", "cmbPmsdMulMachineid", "machineCombo.commonFilter");
    var firstTopMachId = jQuery("#hdnPmsdMachId").val();
   //var firstTopMachId  = jQuery("#hdnPmsdMachId").combobox("getValue");
   
   //alert("machine ****"+firstTopMachId);
    reloadCombo("frmMultiplePmStd", "cmbMulPmsdAssemblyid", "assembly.commonFilter?machineId="+firstTopMachId);

    if (machId != null && machId.trim() != "" && machId.trim() != "null") {
        loadFunctionalLocation(
            "pmsdMulFunLocation",
            "functionalLoc.prv",
            "pmsdMulFunLocationValues",
            "frmMultiplePmStd",
            "&machId=" + machId + "&flid=" + flid
        );
    }

    fillMachineHierarchy("machineHierarchy.commonFilter", machId,
        "cmbCell", "cmbSection", "cmbFactory", "", "", "cmbCostCenter");

    jQuery("#btnPmsdAdd").off("click").click(function () {
        addPmsdRow();
    });
    jQuery("#btnPmsdDelete").off("click").click(function () {
        removePmsdRecord();
    });
});

/* function 

(keyIds) {
    var machId = jQuery("#machine").val();
    var flid   = jQuery("#flid").val();

    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
    }

    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewPmsdGrid(machId, keyIds.flId);
    else
        viewPmsdGrid(machId, "");
}
 */
 //mano
 /* function frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
    console.log("=== frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack CALLED ===");
    console.log("[Debug] keyIds object:", keyIds);

    var machId = jQuery("#machine").val();
    var flid   = jQuery("#flid").val();

    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
    }

    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    console.log("[Debug] hdnsbu at callback time:", sbuVal);

    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
        console.log("[Debug] factory field set from callback to:", sbuVal);
    } else {
        console.warn("[Debug] callback found NO hdnsbu value at all");
    }

    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewPmsdGrid(machId, keyIds.flId);
    else
        viewPmsdGrid(machId, "");
} */
/* function frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
    console.log("=== frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack CALLED ===");
    console.log("[Debug] keyIds object:", keyIds);

    //var machId = jQuery("#machine").val();
     var machId  = jQuery("#hdnPmsdMachId").val();  
    var flid   = jQuery("#flid").val();
    var tradeId = jQuery("#hdnPmsdtradeId").val();   // NEW

    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
        console.log("[Debug] elementId set to:", keyIds.elementId);
    }
    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewPmsdGrid(machId, keyIds.flId, tradeId);
    else
        viewPmsdGrid(machId, "", tradeId);

    // mano - location id: try common key name variants returned by the
    // functional-location widget; confirm the correct one from the
    // console.log(keyIds) above and trim this down to just that one.
    var locVal = keyIds.locationId || keyIds.locnId || keyIds.locId || keyIds.location;
    if (locVal != undefined && String(locVal).trim().length > 0) {
        jQuery("#frmMultiplePmStd input[id='location']").val(locVal);
        console.log("[Debug] locationId set to:", locVal);
    } else {
        console.warn("[Debug] no locationId found on keyIds object - check console.log(keyIds) above for the correct key name");
    }

    // mano - sync SBU into factory field
    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    console.log("[Debug] hdnsbu at callback time:", sbuVal);

    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
        console.log("[Debug] factory field set from callback to:", sbuVal);
    } else {
        console.warn("[Debug] callback found NO hdnsbu value at all");
    }

    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewPmsdGrid(machId, keyIds.flId);
    else
        viewPmsdGrid(machId, "");
} */
/* function frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
    console.log("=== frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack CALLED ===");
    console.log("[Debug] keyIds object:", keyIds);

    var machId  = jQuery("#hdnPmsdMachId").val();
    var flid    = jQuery("#flid").val();
    var tradeId = jQuery("#hdnPmsdtradeId").val();

    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
        console.log("[Debug] elementId set to:", keyIds.elementId);
    }

    var locVal = keyIds.locationId || keyIds.locnId || keyIds.locId || keyIds.location;
    if (locVal != undefined && String(locVal).trim().length > 0) {
        jQuery("#frmMultiplePmStd input[id='location']").val(locVal);
        console.log("[Debug] locationId set to:", locVal);
    } else {
        console.warn("[Debug] no locationId found on keyIds object - check console.log(keyIds) above for the correct key name");
    }

    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    console.log("[Debug] hdnsbu at callback time:", sbuVal);

    if (sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if (sbuVal != null && sbuVal != '' && sbuVal != undefined) {
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
        console.log("[Debug] factory field set from callback to:", sbuVal);
    } else {
        console.warn("[Debug] callback found NO hdnsbu value at all");
    }

    // single call, always carries tradeId
    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewPmsdGrid(machId, keyIds.flId, tradeId);
    else
        viewPmsdGrid(machId, "", tradeId);
}
 */
 
 function frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
	    console.log("=== frmMultiplePmStd_FuntLocHierarchy_SuccessCallBack CALLED ===");
	    console.log("[Debug] keyIds object:", keyIds);

	    var machId  = jQuery("#hdnPmsdMachId").val();
	    var tradeId = jQuery("#hdnPmsdtradeId").val();

	    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
	        jQuery("#elementId").val(keyIds.elementId);
	        console.log("[Debug] elementId set to:", keyIds.elementId);
	    }

	    var locVal = keyIds.locationId || keyIds.locnId || keyIds.locId || keyIds.location;
	    if (locVal != undefined && String(locVal).trim().length > 0) {
	        jQuery("#frmMultiplePmStd input[id='location']").val(locVal);
	        console.log("[Debug] locationId set to:", locVal);
	    }

	    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
	    if (sbuVal == null || sbuVal == '' || sbuVal == undefined)
	        sbuVal = jQuery("#hdnsbu").val();

	    if (sbuVal != null && sbuVal != '' && sbuVal != undefined) {
	        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
	        console.log("[Debug] factory field set from callback to:", sbuVal);
	    }

	    // mano - do NOT forward flid to the grid fetch. #flid gets overwritten by
	    // loadFunctionalLocation() itself with a widget-resolved default leaf node
	    // for this machine (e.g. FNL000000001), which is not reliably the same
	    // flid that actual PMSD records were saved against (e.g. FNL000000321
	    // for MCH0000177). Filtering by machine + trade only avoids this false-empty
	    // result; getMultiplePmsdList() already falls back to "{}" (no flid filter)
	    // when flid is blank/invalid.
	    viewPmsdGrid(machId, "", tradeId);
	}
 /* function viewPmsdGrid(machId, flid) {
    processGridnew(
        "prvnt_mntncform_multiple_input.prv",
        "?machId=" + machId + "&flid=" + flid,
        "PmMultipleGrd",
        "pmMulPager",
        "", "",
        "PmMultipleGrd_selectRow",
        "PmMultipleGrd_completeCallback"
    );
} */
function viewPmsdGrid(machId, flid, tradeId) {
    var params = "?machId=" + encodeURIComponent(machId) + "&flid=" + encodeURIComponent(flid);
    if (tradeId != undefined && tradeId != null && tradeId.trim().length > 0) {
        params += "&tradeId=" + encodeURIComponent(tradeId);
    }
    processGridnew(
        "prvnt_mntncform_multiple_input.prv",
        params,
        "PmMultipleGrd",
        "pmMulPager",
        "", "",
        "PmMultipleGrd_selectRow",
        "PmMultipleGrd_completeCallback"
    );
}
/* Cache so hidden values can be restored when the checkbox is re-checked,
without ever exposing them in the visible cmb columns until then. */
/* Safely call an easyui-combobox method — never throws even if the widget
isn't initialized yet or its remote data hasn't finished loading. */
function safeComboboxCall(selector, method, arg) {
    try {
        var el = jQuery(selector);
        if (!el.length || !el.data('combobox')) return;
        if (arg !== undefined) el.combobox(method, arg);
        else el.combobox(method);
    } catch (e) {
        console.warn("[Combobox] " + method + " failed for " + selector, e);
    }
}

var pmsdRowValueCache = {};

function toggleRowValueDisplay(rowid, showValues) {
    var grid = jQuery("#PmMultipleGrd");
    var jqGridId = "PmMultipleGrd";
    var tradeSelector = "#cmbMulPmsdTradeid_" + jqGridId + "_" + rowid;
    var actSelector    = "#cmbMulPmsdActivitytype_" + jqGridId + "_" + rowid;

    if (showValues) {
        var cached  = pmsdRowValueCache[rowid] || {};
        var tradeId = cached.tradeId || grid.jqGrid('getCell', rowid, 'hdnMulPmsdTradeid');
        var actType = cached.actType || grid.jqGrid('getCell', rowid, 'hdnMulPmsdActivitytype');

        // safe: hdn columns are plain text, no widget bound to them
        grid.jqGrid('setCell', rowid, 'hdnMulPmsdTradeid', tradeId || '');
        grid.jqGrid('setCell', rowid, 'hdnMulPmsdActivitytype', actType || '');

        // do NOT setCell the cmb columns - update the live widget instead
        safeComboboxCall(tradeSelector, 'setValue', tradeId || '');
        safeComboboxCall(actSelector, 'setValue', actType || '');

        console.log("[ToggleDisplay] row " + rowid + " -> shown (trade:", tradeId, ", actType:", actType, ")");
    } else {
        pmsdRowValueCache[rowid] = {
            tradeId: grid.jqGrid('getCell', rowid, 'hdnMulPmsdTradeid'),
            actType: grid.jqGrid('getCell', rowid, 'hdnMulPmsdActivitytype')
        };

        grid.jqGrid('setCell', rowid, 'hdnMulPmsdTradeid', '');
        grid.jqGrid('setCell', rowid, 'hdnMulPmsdActivitytype', '');

        safeComboboxCall(tradeSelector, 'clear');
        safeComboboxCall(actSelector, 'clear');

        console.log("[ToggleDisplay] row " + rowid + " -> hidden");
    }
}
/* Delegated, one-time binding — works for rows that exist now AND rows added/reloaded later.
   No need to re-bind per row on every completeCallback. Bind this ONCE, e.g. in $(document).ready. */
function bindPmsdEditToggleDelegation() {
    jQuery(document)
        .off('click.pmsdEditToggle')
        .on('click.pmsdEditToggle', '#PmMultipleGrd input[id^="jqg_PmMultipleGrd_"]', function () {
            var rowid = jQuery(this).attr('id').replace('jqg_PmMultipleGrd_', '');
            var isChecked = jQuery(this).is(':checked');
            console.log("[EditToggle:delegated] click on row", rowid, "checked:", isChecked);
            toggleRowValueDisplay(rowid, isChecked);
        });
    console.log("[EditToggle:delegated] delegation bound on document for #PmMultipleGrd checkboxes");
}

function PmMultipleGrd_completeCallback(result) {
    // make sure every already-loaded row reflects the top-level Cost Center / Equipment
    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

    // small delay to guarantee the grid's row DOM (incl. checkboxes) has fully painted
    setTimeout(function () {
        var rows = jQuery("#PmMultipleGrd").jqGrid('getDataIDs');
        console.log("[completeCallback] rows found:", rows);

        for (var i = 0; i < rows.length; i++) {
            var rowid = rows[i];

            if (topMachId) {
                jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'hdnMulPmsdMachineid', topMachId);
                jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'cmbMulPmsdMachineid', topMachId);
            }
            if (topCostCtr) {
                jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'hdnMulPmsdCostcenterid', topCostCtr);
                jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'cmbMulPmsdCostcenterid', topCostCtr);
            }

            // hide Maint Section / Activity Type until this row's checkbox is checked
            var chk = jQuery('#jqg_PmMultipleGrd_' + rowid);
            console.log("[completeCallback] row", rowid, "-> checkbox element found:", chk.length, "checked:", chk.is(':checked'));
            toggleRowValueDisplay(rowid, chk.is(':checked'));
        }
    }, 0);
}
/* propagate the top-level Equipment selection down into every grid row */
/* function frmMultiplePmStdcmbPmsdMulMachineid_onSelect(record) {
    var rows = jQuery("#PmMultipleGrd").jqGrid('getDataIDs');
    for (var i = 0; i < rows.length; i++) {
        jQuery("#PmMultipleGrd").jqGrid('setCell', rows[i], 'hdnMulPmsdMachineid', record.id);
        jQuery("#PmMultipleGrd").jqGrid('setCell', rows[i], 'cmbMulPmsdMachineid', record.id);
    }
} */


function frmMultiplePmStdcmbPmsdMulMachineid_onSelect(record) {
	alert("mano");
	console.log(record);
	alert("record ",record.id);
    var rows = jQuery("#PmMultipleGrd").jqGrid('getDataIDs');
    for (var i = 0; i < rows.length; i++) {
        var rowid = rows[i];
        jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'hdnMulPmsdMachineid', record.id);
        jQuery("#PmMultipleGrd").jqGrid('setCell', rowid, 'cmbMulPmsdMachineid', record.id);

        // mano - FIX: keep each row's Assembly list in sync with the newly
        // selected Equipment, respecting that row's Others checkbox state
        var assmInput  = jQuery("#cmbMulPmsdAssemblyid_PmMultipleGrd_" + rowid);
        var othersChk  = jQuery("#chkMulPmsdOthers_PmMultipleGrd_" + rowid);
        if (assmInput.length) {
            assmInput.combobox('clear');
            if (othersChk.is(':checked')) {
                assmInput.combobox('reload', 'assembly.commonFilter');
            } else {
                assmInput.combobox('reload', 'assembly.commonFilter?machineId=' + record.id);
            }
        }
    }
}


/* propagate the top-level Cost Center selection down into every grid row */
function frmMultiplePmStdcmbPmsdMulCostCenter_onSelect(record) {
    var rows = jQuery("#PmMultipleGrd").jqGrid('getDataIDs');
    for (var i = 0; i < rows.length; i++) {
        jQuery("#PmMultipleGrd").jqGrid('setCell', rows[i], 'hdnMulPmsdCostcenterid', record.id);
        jQuery("#PmMultipleGrd").jqGrid('setCell', rows[i], 'cmbMulPmsdCostcenterid', record.id);
    }
}

var pmsdNewRowSeq = 0;

function addPmsdRow() {
    pmsdNewRowSeq++;
    var newRowId = "new" + pmsdNewRowSeq;

    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");
    var presetTradeId  = jQuery("#hdnPmsdtradeId").val();
    var presetActType  = jQuery("#hdnPmsdactivityType").val();
    var presetPreparedBy = jQuery("#hdnPmsdPreparedbyid").val(); 

    var emptyItem = {
        hdnPmsdKeyid:                   " ",
        hdnMulPmsdCostcenterid:         topCostCtr || " ",
        cmbMulPmsdCostcenterid:         topCostCtr || " ",
        hdnMulPmsdMachineid:            topMachId  || " ",
        cmbMulPmsdMachineid:            topMachId  || " ",
        hdnMulPmsdAssemblyid:           " ",
        cmbMulPmsdAssemblyid:           " ",
        chkMulPmsdOthers:               " ",
        hdnMulPmsdSubassemblyid:        " ",
        cmbMulPmsdSubassemblyid:        " ",
        cmbMulPmsdSource:               " ",
        hdnMulPmsdSupplierid:           " ",
        cmbMulPmsdSupplierid:           " ",
       /*  hdnMulPmsdTradeid:              " ",
        cmbMulPmsdTradeid:              " ",
        hdnMulPmsdActivitytype:         " ",
        cmbMulPmsdActivitytype:         " ", */
        hdnMulPmsdTradeid:              presetTradeId || " ",
        cmbMulPmsdTradeid:               " ",
        hdnMulPmsdActivitytype:         presetActType || " ",
        cmbMulPmsdActivitytype:          " ",
        hdnMulPmsdMachinecondition:     " ",
        cmbMulPmsdMachinecondition:     " ",
        txtMulPmsdActivitysub:          " ",
        hdnMulPmsdFrequencyunit:        " ",
        cmbMulPmsdFrequencyunit:        " ",
        txtMulPmsdFrequency:            " ",
        txtMulPmsdDuration:             " ",
        txtMulPmsdLocation:             " ",
        txtMulPmsdActivity:             " ",
        txtMulPmsdHowmethod:            " ",
        txtMulPmsdStandard:             " ",
        chkMulPmsdIssparesreq:          " ",
        btnPmsdSpares:                  " ",
        chkMulPmsdIstoolsreq:           " ",
        btnPmsdTools:                   " ",
        hdnMulPmsdPreparedbyid:          presetPreparedBy ||" ",
        cmbMulPmsdPreparedbyid:         " ",
        btnPmsdFilManage:               " "
    };

    jQuery("#PmMultipleGrd").jqGrid('addRowData', newRowId, emptyItem,"first");
}

var pmsdDeleteQueue = [];

function removePmsdRecord() {
    var rows = jQuery("#PmMultipleGrd").jqGrid('getDataIDs');
    var checkedRows = [];

    for (var i = 0; i < rows.length; i++) {
        if (jQuery('#jqg_PmMultipleGrd_' + rows[i]).is(':checked')) {
            checkedRows.push(rows[i]);
        }
    }

    if (checkedRows.length == 0) {
        alert("Please select at least one row to delete.");
        return;
    }

    if (!confirm("Do You Want To Delete Selected Row(s)?")) {
        return;
    }

    var localRowIds = [];
    pmsdDeleteQueue = [];

    for (var i = 0; i < checkedRows.length; i++) {
        var rowid = checkedRows[i];
        var keyid = jQuery("#PmMultipleGrd").jqGrid('getCell', rowid, "hdnPmsdKeyid");
        if (keyid != null && keyid.trim().length > 0) {
            pmsdDeleteQueue.push(keyid);
        } else {
            localRowIds.push(rowid);
        }
    }

    for (var i = 0; i < localRowIds.length; i++) {
        jQuery("#PmMultipleGrd").jqGrid('delRowData', localRowIds[i]);
    }

    if (pmsdDeleteQueue.length > 0) {
        processAjaxCalls(
            "MultiplePmStd_remove.prv",
            "keyid=" + pmsdDeleteQueue[0],
            'pmsdRemove_successCallBack',
            'pmsdRemove_errorCallBack'
        );
    }
}

function pmsdRemove_successCallBack(result) {
    pmsdDeleteQueue.shift();
    if (pmsdDeleteQueue.length > 0) {
        processAjaxCalls(
            "MultiplePmStd_remove.prv",
            "keyid=" + pmsdDeleteQueue[0],
            'pmsdRemove_successCallBack',
            'pmsdRemove_errorCallBack'
        );
    } else {
        jQuery("#PmMultipleGrd").trigger("reloadGrid");
    }
}

function pmsdRemove_errorCallBack(result) {
    popupCommonErrorMsg("Error while deleting record.");
}

/* function PmMultipleGrd_selectRow(rowId) {
    var jqGridId = "PmMultipleGrd";

    jQuery("#cmbMulPmsdFrequencyunit_" + jqGridId + "_" + rowId).combobox({
        onSelect: function (record) {
            // hook: adjust mandatory styling based on frequency unit if needed
        }
    });

    jQuery("#chkMulPmsdIstoolsreq_" + jqGridId + "_" + rowId).click(function () {
        var isChecked = jQuery(this).is(':checked');
        var toolsBtn = jQuery("#btnPmsdTools_" + jqGridId + "_" + rowId);
        if (isChecked) {
            toolsBtn.attr('class', 'easyui-button').removeAttr('disabled');
        } else {
            toolsBtn.removeAttr('class').attr('disabled', 'disabled');
        }
    });

    jQuery("#chkMulPmsdIssparesreq_" + jqGridId + "_" + rowId).click(function () {
        var isChecked = jQuery(this).is(':checked');
        var sparesBtn = jQuery("#btnPmsdSpares_" + jqGridId + "_" + rowId);
        if (isChecked) {
            sparesBtn.attr('class', 'easyui-button').removeAttr('disabled');
        } else {
            sparesBtn.removeAttr('class').attr('disabled', 'disabled');
        }
    });
} */


function PmMultipleGrdcmbMulPmsdTradeid_onSelect(record)
{
    var rowId = jQuery("#PmMultipleGrd").jqGrid('getGridParam','selrow');
    jQuery("#PmMultipleGrd").jqGrid('setCell', rowId, 'hdnMulPmsdTradeid', record.id);
}

function PmMultipleGrdcmbMulPmsdActivitytype_onSelect(record)
{
    var rowId = jQuery("#PmMultipleGrd").jqGrid('getGridParam','selrow');
    jQuery("#PmMultipleGrd").jqGrid('setCell', rowId, 'hdnMulPmsdActivitytype', record.id);
    jQuery("#cmbMulPmsdActivitytype_PmMultipleGrd_"+rowId).combobox('disable');
}
function PmMultipleGrd_selectRow(rowId) {
    console.log("=== PmMultipleGrd_selectRow START, rowId:", rowId);
    var jqGridId = "PmMultipleGrd";

    var assemblyInput = jQuery("#cmbMulPmsdAssemblyid_" + jqGridId + "_" + rowId);

    var sweMachine = jQuery("#cmbPmsdMulMachineid").combobox("getValue");

    console.log("[Assembly] element found:", assemblyInput.length);
    console.log("[Assembly] machineId:", sweMachine);

    setTimeout(function () {
        if (assemblyInput.length) {
            assemblyInput.combobox(
                'reload',
                'assembly.commonFilter?machineId=' + encodeURIComponent(sweMachine)
            );

            console.log(
                "[Assembly] reloaded with machineId:",
                sweMachine
            );
        }
    }, 1000);

    console.log("[FreqUnit] looking for #cmbMulPmsdFrequencyunit_" + jqGridId + "_" + rowId,
        jQuery("#cmbMulPmsdFrequencyunit_" + jqGridId + "_" + rowId).length, "element(s) found");
    /* jQuery("#cmbMulPmsdFrequencyunit_" + jqGridId + "_" + rowId).combobox({
        onSelect: function (record) {
            console.log("[FreqUnit] onSelect fired, record.id:", record.id);
            var freqInput = jQuery("#txtMulPmsdFrequency_" + jqGridId + "_" + rowId);
            console.log("[FreqUnit] freqInput found:", freqInput.length);
            if (record.id == 'Y' || record.id == 'M') {
                freqInput.attr('disabled', false);
                console.log("[FreqUnit] enabled frequency textbox");
            } else {
                freqInput.val("");
                freqInput.attr('disabled', 'disabled');
                console.log("[FreqUnit] cleared + disabled frequency textbox");
            }
        }
    }); */
    jQuery("#cmbMulPmsdFrequencyunit_" + jqGridId + "_" + rowId).combobox({
        onSelect: function (record) {
            console.log("[FreqUnit] onSelect fired, record.id:", record.id);
            var freqInput = jQuery("#txtMulPmsdFrequency_" + jqGridId + "_" + rowId);
            console.log("[FreqUnit] freqInput found:", freqInput.length);

            // always enabled now — no Y/M condition
            freqInput.attr('disabled', false);
            console.log("[FreqUnit] enabled frequency textbox");
        }
    });
    console.log("[FreqUnit] combobox() init call completed");

    var supplierInput = jQuery("#cmbMulPmsdSupplierid_" + jqGridId + "_" + rowId);
    console.log("[Supplier] supplierInput found:", supplierInput.length);

    /* ---- Source (native <select>) drives Supplier enable/disable ---- */
    var sourceEl = jQuery("#cmbMulPmsdSource_" + jqGridId + "_" + rowId);
    console.log("[Source] sourceEl found:", sourceEl.length);

    function applySupplierState(sourceVal) {
        if (sourceVal == 'E') {
            supplierInput.combobox('enable');
            console.log("[Supplier] enabled (source == E)");
        } else {
            supplierInput.combobox('clear');
            supplierInput.combobox('disable');
            console.log("[Supplier] cleared + disabled (source == " + sourceVal + ")");
        }
    }

    // set initial state for this row on load/select
    var currentSource = sourceEl.val();
    console.log("[Source] currentSource val() =", currentSource);
    applySupplierState(currentSource);

    // react to user changing the native <select>
    sourceEl.off('change').on('change', function () {
        var newVal = jQuery(this).val();
        console.log("[Source] change fired, val:", newVal);
        applySupplierState(newVal);
    });
    console.log("[Source] change handler bound");
    /* ------------------------------------------------------------------ */

    var othersChk = jQuery("#chkMulPmsdOthers_" + jqGridId + "_" + rowId);
    console.log("[Others] checkbox found:", othersChk.length);
    othersChk.click(function () {
        console.log("[Others] click fired, checked:", jQuery(this).is(':checked'));
        var assmInput = jQuery("#cmbMulPmsdAssemblyid_" + jqGridId + "_" + rowId);
        var subAssmInput = jQuery("#cmbMulPmsdSubassemblyid_" + jqGridId + "_" + rowId);   // added
        var topMachId = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
        console.log("[Others] assmInput found:", assmInput.length, "| topMachId:", topMachId);
        assmInput.combobox('clear');
        subAssmInput.combobox('clear');   // added
        if (jQuery(this).is(':checked')) {
            assmInput.combobox('reload', 'assembly.commonFilter?machineNotToShown=' + topMachId);
            console.log("[Others] reloaded assembly WITH machineNotToShown filter:", topMachId);
        } else {
            assmInput.combobox('reload', 'assembly.commonFilter?machineId=' + topMachId);
            console.log("[Others] reloaded assembly WITH machineId filter:", topMachId);
        }
    });

    /* ---- Assembly select/clear drives Subassembly reload (assmId + machineId) ---- */
    var assemblyInputRow = jQuery("#cmbMulPmsdAssemblyid_" + jqGridId + "_" + rowId);
    console.log("[Assembly] binding onSelect for subassembly reload, found:", assemblyInputRow.length);
    try {
        assemblyInputRow.combobox({
            onSelect: function (record) {
                console.log("[Assembly] onSelect fired, record.id:", record.id);
                var subAssmInput = jQuery("#cmbMulPmsdSubassemblyid_" + jqGridId + "_" + rowId);
                var topMachId = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
                console.log("[Assembly] subAssmInput found:", subAssmInput.length, "| machineId:", topMachId);

                subAssmInput.combobox('clear');
                subAssmInput.combobox(
                    'reload',
                    'Pmsd_SubassemblyId.prv?assmId=' + encodeURIComponent(record.id) +
                    '&machineId=' + encodeURIComponent(topMachId)
                );
                console.log("[Assembly] Subassembly reloaded with assmId:", record.id, "machineId:", topMachId);
            },
            onClear: function () {
                console.log("[Assembly] onClear fired");
                var subAssmInput = jQuery("#cmbMulPmsdSubassemblyid_" + jqGridId + "_" + rowId);
                var topMachId = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
                subAssmInput.combobox('clear');
                subAssmInput.combobox(
                    'reload',
                    'Pmsd_SubassemblyId.prv?machineId=' + encodeURIComponent(topMachId)
                );
            }
        });
        console.log("[Assembly] combobox() onSelect/onClear bound");
    } catch (e) {
        console.error("[Assembly] ERROR binding onSelect:", e);
    }
    /* ------------------------------------------------------------------ */

    var toolsChk = jQuery("#chkMulPmsdIstoolsreq_" + jqGridId + "_" + rowId);
    console.log("[Tools] checkbox found:", toolsChk.length);
    toolsChk.click(function () {
        var isChecked = jQuery(this).is(':checked');
        console.log("[Tools] click fired, checked:", isChecked);
        var toolsBtn = jQuery("#btnPmsdTools_" + jqGridId + "_" + rowId);
        console.log("[Tools] toolsBtn found:", toolsBtn.length);
        if (isChecked) {
            toolsBtn.attr('class', 'easyui-button').removeAttr('disabled');
        } else {
            toolsBtn.removeAttr('class').attr('disabled', 'disabled');
        }
    });

    console.log("[Trade] looking for #cmbMulPmsdTradeid_" + jqGridId + "_" + rowId,
        jQuery("#cmbMulPmsdTradeid_" + jqGridId + "_" + rowId).length, "element(s) found");
    try {
        jQuery("#cmbMulPmsdTradeid_" + jqGridId + "_" + rowId).combobox({
            onSelect: function (record) {
                console.log("[Trade] onSelect fired, record.id:", record.id);
            }
        });
        console.log("[Trade] combobox() init call completed");
    } catch (e) {
        console.error("[Trade] ERROR initializing combobox:", e);
    }

    console.log("[ActivityType] looking for #cmbMulPmsdActivitytype_" + jqGridId + "_" + rowId,
        jQuery("#cmbMulPmsdActivitytype_" + jqGridId + "_" + rowId).length, "element(s) found");
    try {
        jQuery("#cmbMulPmsdActivitytype_" + jqGridId + "_" + rowId).combobox({
            onSelect: function (record) {
                console.log("[ActivityType] onSelect fired, record.id:", record.id);
                jQuery("#cmbMulPmsdActivitytype_" + jqGridId + "_" + rowId).combobox('disable');
                console.log("[ActivityType] disabled after select");
            }
        });
        console.log("[ActivityType] combobox() init call completed");
    } catch (e) {
        console.error("[ActivityType] ERROR initializing combobox:", e);
    }

    console.log("[HeaderCheck] Trade formatterType:",
        jQuery("#jqgh_" + jqGridId + "_cmbMulPmsdTradeid").attr("formatterType"));
    console.log("[HeaderCheck] ActivityType formatterType:",
        jQuery("#jqgh_" + jqGridId + "_cmbMulPmsdActivitytype").attr("formatterType"));
    console.log("[HeaderCheck] Source formatterType (known working):",
        jQuery("#jqgh_" + jqGridId + "_cmbMulPmsdSource").attr("formatterType"));

    var sparesChk = jQuery("#chkMulPmsdIssparesreq_" + jqGridId + "_" + rowId);
    console.log("[Spares] checkbox found:", sparesChk.length);
    sparesChk.click(function () {
        var isChecked = jQuery(this).is(':checked');
        console.log("[Spares] click fired, checked:", isChecked);
        var sparesBtn = jQuery("#btnPmsdSpares_" + jqGridId + "_" + rowId);
        console.log("[Spares] sparesBtn found:", sparesBtn.length);
        if (isChecked) {
            sparesBtn.attr('class', 'easyui-button').removeAttr('disabled');
        } else {
            sparesBtn.removeAttr('class').attr('disabled', 'disabled');
        }
    });

    console.log("=== PmMultipleGrd_selectRow END, rowId:", rowId);
}
console.log(jQuery("#cmbMulPmsdTradeid_PmMultipleGrd_new1").combobox('panel'));
console.log(jQuery("#cmbMulPmsdTradeid_PmMultipleGrd_new1").combobox('options'));

othersChk.click(function () {
    console.log("[Others] click fired, checked:", jQuery(this).is(':checked'));
    var assmInput = jQuery("#cmbMulPmsdAssemblyid_" + jqGridId + "_" + rowId);
    var subAssmInput = jQuery("#cmbMulPmsdSubassemblyid_" + jqGridId + "_" + rowId);   // added
    var topMachId = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    console.log("[Others] assmInput found:", assmInput.length, "| topMachId:", topMachId);
    assmInput.combobox('clear');
    subAssmInput.combobox('clear');   // added
    if (jQuery(this).is(':checked')) {
        assmInput.combobox('reload', 'assembly.commonFilter?machineNotToShown=' + topMachId);
        console.log("[Others] reloaded assembly WITH machineNotToShown filter:", topMachId);
    } else {
        assmInput.combobox('reload', 'assembly.commonFilter?machineId=' + topMachId);
        console.log("[Others] reloaded assembly WITH machineId filter:", topMachId);
    }
});
function PmMultipleGrdbtnPmsdTools_onClick(result) {
    var rowid = result.rowId;
    // TODO: open the Tools tree popup for this row, e.g. toolpop(rowid, 'PmMultipleGrd');
    console.log("Tools Info button clicked for row " + rowid);
}

function PmMultipleGrdbtnPmsdSpares_onClick(result) {
    var rowid = result.rowId;
    // TODO: open the Spares popup for this row, e.g. sparepop(rowid, 'PmMultipleGrd');
    console.log("Spares Info button clicked for row " + rowid);
}

function PmMultipleGrdbtnPmsdFilManage_onClick(result) {
    var rowid    = result.rowId;
    var refDocId = jQuery("#PmMultipleGrd").jqGrid('getCell', rowid, "hdnPmsdKeyid");
    if (refDocId.length <= 1) {
        saveForm('frmMultiplePmStd',
            'MultiplePmStd_save.prv?openfilemgr=openfilemgr&rowid=' + rowid);
    } else {
        fileManagerPopUp(refDocId, "PMS", "", "", "", "");
    }
}

function validatePmsdMandatory(gridSelArr) {
    var selArr = JSON.parse(gridSelArr);
    for (var i = 0; i < selArr.length; i++) {

        if (selArr[i].cmbMulPmsdMachineid.trim() == "") {
            alert("Select Equipment");
            return false;
        }
        if (selArr[i].cmbMulPmsdAssemblyid.trim() == "") {
            alert("Select Assembly");
            return false;
        }
        if (selArr[i].cmbMulPmsdSource.trim() == "") {
            alert("Select Source");
            return false;
        }
        if (selArr[i].cmbMulPmsdTradeid.trim() == "") {
            alert("Select Maint Section");
            return false;
        }
        if (selArr[i].cmbMulPmsdActivitytype.trim() == "") {
            alert("Select Activity Type");
            return false;
        }
        if (selArr[i].cmbMulPmsdMachinecondition.trim() == "") {
            alert("Select Equipment Condition");
            return false;
        }
        if (selArr[i].cmbMulPmsdFrequencyunit.trim() == "") {
            alert("Select Frequency Unit");
            return false;
        }
        if (selArr[i].txtMulPmsdFrequency.trim() == "" || selArr[i].txtMulPmsdFrequency.trim() == "0") {
            alert("Enter valid Frequency, value must be greater than 0");
            return false;
        }
        if (selArr[i].txtMulPmsdActivity.trim() == "") {
            alert("Enter the Activity");
            return false;
        }
        if (selArr[i].txtMulPmsdHowmethod.trim() == "") {
            alert("Enter the Method");
            return false;
        }
    }
    return true;
}

/* function frmMultiplePmStd_beforeSubmit() {
    var cellId    = jQuery("#frmMultiplePmStd input[id='cell']").val();
    var flId      = jQuery("#frmMultiplePmStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultiplePmStd input[id='section']").val();
    var machId    = jQuery("#frmMultiplePmStd input[id='machine']").val();

    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return false;
    }

    if (topMachId == null || topMachId.trim() == '') {
        alert("Select Equipment");
        return false;
    }

    var gridval = getGridSelectArray('PmMultipleGrd');
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return false;
    }

    if (!validatePmsdMandatory(gridval)) return false;

    return 'pmsdStdDetails=' + encodeURIComponent(gridval)
         + '&flId='      + encodeURIComponent(flId)
         + '&sectionId=' + encodeURIComponent(sectionId)
         + '&machId='    + encodeURIComponent(machId)
         + '&costCenterId=' + encodeURIComponent(topCostCtr || '');
} */
/* function frmMultiplePmStd_beforeSubmit() {
    // guaranteed SBU sync right before save
    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
    }

    var cellId    = jQuery("#frmMultiplePmStd input[id='cell']").val();
    var flId      = jQuery("#frmMultiplePmStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultiplePmStd input[id='section']").val();
    var machId    = jQuery("#frmMultiplePmStd input[id='machine']").val();
    var factoryId = sbuVal || jQuery("#frmMultiplePmStd input[id='factory']").val() || '';   // mano - added

    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return false;
    }

    if (topMachId == null || topMachId.trim() == '') {
        alert("Select Equipment");
        return false;
    }

    var gridval = getGridSelectArray('PmMultipleGrd');
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return false;
    }

    if (!validatePmsdMandatory(gridval)) return false;

    return 'pmsdStdDetails=' + encodeURIComponent(gridval)
         + '&flId='      + encodeURIComponent(flId)
         + '&sectionId=' + encodeURIComponent(sectionId)
         + '&cellId='    + encodeURIComponent(cellId)        // mano - added, was missing
         + '&machId='    + encodeURIComponent(machId)
         + '&costCenterId=' + encodeURIComponent(topCostCtr || '')
         + '&factoryId=' + encodeURIComponent(factoryId);    // mano - added, was missing
} */
function frmMultiplePmStd_beforeSubmit() {
    // guaranteed SBU sync right before save
    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
    }

    var cellId     = jQuery("#frmMultiplePmStd input[id='cell']").val();
    var flId       = jQuery("#frmMultiplePmStd input[id='flid']").val();
    var sectionId  = jQuery("#frmMultiplePmStd input[id='section']").val();
    var machId     = jQuery("#frmMultiplePmStd input[id='machine']").val();
    var elementId  = jQuery("#frmMultiplePmStd input[id='elementId']").val();
    var locationId = jQuery("#frmMultiplePmStd input[id='location']").val() || '';   // mano - added
    var factoryId  = sbuVal || jQuery("#frmMultiplePmStd input[id='factory']").val() || '';

    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");
    pmsdLastMachId  = topMachId;
    pmsdLastCostCtr = topCostCtr;

    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return false;
    }

    if (topMachId == null || topMachId.trim() == '') {
        alert("Select Equipment");
        return false;
    }

    var gridval = getGridSelectArray('PmMultipleGrd');
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return false;
    }

    if (!validatePmsdMandatory(gridval)) return false;

    return 'pmsdStdDetails=' + encodeURIComponent(gridval)
         + '&flId='         + encodeURIComponent(flId)
         + '&sectionId='    + encodeURIComponent(sectionId)
         + '&cellId='       + encodeURIComponent(cellId)
         + '&machId='       + encodeURIComponent(machId)
         + '&elementId='    + encodeURIComponent(elementId)
         + '&locationId='   + encodeURIComponent(locationId)    // mano - added
         + '&costCenterId=' + encodeURIComponent(topCostCtr || '')
         + '&factoryId='    + encodeURIComponent(factoryId);
}
/* function pmsdSave_successCallBack(result) {
    if (result.tpmException) {
        alert(result.tpmException);
        return;
    }

    alert("Data Saved Successfully");

    if (result.savedRows) {
        for (var i = 0; i < result.savedRows.length; i++) {
            var r = result.savedRows[i];
            if (!r.rowid) continue;
            if (r.hdnPmsdKeyid) {
                jQuery("#PmMultipleGrd").jqGrid('setCell', r.rowid, "hdnPmsdKeyid", r.hdnPmsdKeyid);
            }
        }
    }
}
 */
 

	function frmMultiplePmStd_successsCallback(result) {
	// alert("m");
	    console.log("[pmsdSave_successCallBack] FIRED. typeof result:", typeof result, "| raw:", result);

	    try {
	        if (typeof result === "string") {
	            result = JSON.parse(result);
	        }

	        if (!result) {
	            console.error("[pmsdSave_successCallBack] result is null/undefined");
	            pmsdShowMessage("Error", "Data Not Saved", true);
	            return;
	        }

	        if (result.tpmException) {
	            console.log("[pmsdSave_successCallBack] tpmException:", result.tpmException);
	            pmsdShowMessage("Error", result.tpmException, true);
	            return;
	        }

	        // update keyids for newly inserted rows BEFORE reload/alert,
	        // so a failure here can't swallow either of them
	        if (result.details) {
	            for (var i = 0; i < result.details.length; i++) {
	                var r = result.details[i];
	                if (r && r.rowid && r.keyid) {
	                    jQuery("#PmMultipleGrd").jqGrid('setCell', r.rowid, "hdnPmsdKeyid", r.keyid);
	                }
	            }
	        } else if (result.savedRows) {
	            for (var j = 0; j < result.savedRows.length; j++) {
	                var rr = result.savedRows[j];
	                if (rr && rr.rowid && rr.hdnPmsdKeyid) {
	                    jQuery("#PmMultipleGrd").jqGrid('setCell', rr.rowid, "hdnPmsdKeyid", rr.hdnPmsdKeyid);
	                }
	            }
	        }

	        console.log("[pmsdSave_successCallBack] reloading grid...");
	        jQuery("#PmMultipleGrd").trigger("reloadGrid");
	        jQuery("#frmMultiplePmStd").trigger("reloadGrid");
	        if (pmsdLastMachId) {
	            jQuery("#cmbPmsdMulMachineid").combobox('setValue', pmsdLastMachId);
	        }
	        if (pmsdLastCostCtr) {
	            jQuery("#cmbPmsdMulCostCenter").combobox('setValue', pmsdLastCostCtr);
	        }

	        console.log("[pmsdSave_successCallBack] showing success message...");
	        pmsdShowMessage("Success", "Data Saved Successfully", false);

	    } catch (e) {
	        console.error("[pmsdSave_successCallBack] EXCEPTION:", e);
	        pmsdShowMessage("Error", "Data Not Saved", true);
	    }
	}

	function pmsdSave_errorCallBack(result) {
	    console.error("[pmsdSave_errorCallBack] FIRED. raw result:", result);
	    pmsdShowMessage("Error", "Data Not Saved", true);
	}
/* function popup_OnSavePmsdForm() {
    var cellId    = jQuery("#frmMultiplePmStd input[id='cell']").val();
    var flId      = jQuery("#frmMultiplePmStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultiplePmStd input[id='section']").val();
    var machId    = jQuery("#frmMultiplePmStd input[id='machine']").val();
    var elementId = jQuery("#frmMultiplePmStd input[id='elementId']").val();

    
    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
    }
    
    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return;
    }

    if (topMachId == null || topMachId.trim() == '') {
        alert("Select Equipment");
        return;
    }

    var gridval = getGridSelectArray('PmMultipleGrd');
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return;
    }

    if (!validatePmsdMandatory(gridval)) return;

    var params = "pmsdStdDetails=" + encodeURIComponent(gridval)
               + "&flId="          + encodeURIComponent(flId)
               + "&sectionId="     + encodeURIComponent(sectionId)
               + "&machId="        + encodeURIComponent(machId)
               + "&elementId="     + encodeURIComponent(elementId)
               + "&costCenterId="  + encodeURIComponent(topCostCtr || '');

    processAjaxCalls(
        "prvnt_mntncform_multiple_save.prv",
        params,
        'pmsdSave_successCallBack',
        'pmsdSave_errorCallBack'
    );
}
 */
 /* function popup_OnSavePmsdForm() {
	    console.log("=== popup_OnSavePmsdForm START ===");

	    var cellId    = jQuery("#frmMultiplePmStd input[id='cell']").val();
	    var flId      = jQuery("#frmMultiplePmStd input[id='flid']").val();
	    var sectionId = jQuery("#frmMultiplePmStd input[id='section']").val();
	    var machId    = jQuery("#frmMultiplePmStd input[id='machine']").val();
	    var elementId = jQuery("#frmMultiplePmStd input[id='elementId']").val();

	    console.log("[Debug] cellId:", cellId, "| flId:", flId, "| sectionId:", sectionId, "| machId:", machId);

	    // check ALL hdnsbu elements on the page (there might be duplicates)
	    var allHdnSbu = jQuery("input[name='hdnsbu']");
	    console.log("[Debug] Total input[name='hdnsbu'] elements found on page:", allHdnSbu.length);
	    allHdnSbu.each(function(idx){
	        console.log("[Debug] hdnsbu[" + idx + "] value:", jQuery(this).val(),
	                     "| closest form id:", jQuery(this).closest('form').attr('id'));
	    });

	    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
	    console.log("[Debug] sbuVal (scoped to #frmMultiplePmStd):", sbuVal);

	    if(sbuVal == null || sbuVal == '' || sbuVal == undefined) {
	        sbuVal = jQuery("#hdnsbu").val();
	        console.log("[Debug] fallback #hdnsbu (by id) value:", sbuVal);
	    }

	    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
	        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
	        console.log("[Debug] factory field SET to:", sbuVal);
	    } else {
	        console.warn("[Debug] sbuVal is EMPTY/NULL - factory field NOT updated. Current factory field value:",
	                      jQuery("#frmMultiplePmStd input[id='factory']").val());
	    }

	    var factoryId = sbuVal || jQuery("#frmMultiplePmStd input[id='factory']").val() || '';
	    console.log("[Debug] final factoryId to be sent:", factoryId);
	    console.log("[Debug] final cellId to be sent:", cellId);

	    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
	    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

	    if (cellId == null || cellId.trim() == '') {
	        alert("Please select Functional Location (JH Cell level)");
	        return;
	    }

	    if (topMachId == null || topMachId.trim() == '') {
	        alert("Select Equipment");
	        return;
	    }

	    var gridval = getGridSelectArray('PmMultipleGrd');
	    if (gridval == "" || gridval == "[]") {
	        alert("Please add at least one row.");
	        return;
	    }

	    if (!validatePmsdMandatory(gridval)) return;

	    var params = "pmsdStdDetails=" + encodeURIComponent(gridval)
	        + "&flId="          + encodeURIComponent(flId)
	        + "&sectionId="     + encodeURIComponent(sectionId)
	        + "&cellId="        + encodeURIComponent(cellId)
	        + "&machId="        + encodeURIComponent(machId)
	        + "&elementId="     + encodeURIComponent(elementId)
	        + "&costCenterId="  + encodeURIComponent(topCostCtr || '')
	        + "&factoryId="     + encodeURIComponent(factoryId);

	    console.log("[Debug] FINAL params string sent to processAjaxCalls:", params);
	    console.log("=== popup_OnSavePmsdForm END ===");

	    processAjaxCalls(
	        "prvnt_mntncform_multiple_save.prv",
	        params,
	        'pmsdSave_successCallBack',
	        'pmsdSave_errorCallBack'
	    );
	} */
	function popup_OnSavePmsdForm() {
	    console.log("=== popup_OnSavePmsdForm START ===");

	    var cellId     = jQuery("#frmMultiplePmStd input[id='cell']").val();
	    var flId       = jQuery("#frmMultiplePmStd input[id='flid']").val();
	    var sectionId  = jQuery("#frmMultiplePmStd input[id='section']").val();
	    var machId     = jQuery("#frmMultiplePmStd input[id='machine']").val();
	    var elementId  = jQuery("#frmMultiplePmStd input[id='elementId']").val();
	    var locationId = jQuery("#frmMultiplePmStd input[id='location']").val() || '';   // mano - added

	    console.log("[Debug] cellId:", cellId, "| flId:", flId, "| sectionId:", sectionId,
	                "| machId:", machId, "| elementId:", elementId, "| locationId:", locationId);

	    var sbuVal = jQuery("#frmMultiplePmStd input[name='hdnsbu']").val();
	    console.log("[Debug] sbuVal (scoped to #frmMultiplePmStd):", sbuVal);

	    if(sbuVal == null || sbuVal == '' || sbuVal == undefined) {
	        sbuVal = jQuery("#hdnsbu").val();
	        console.log("[Debug] fallback #hdnsbu (by id) value:", sbuVal);
	    }

	    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
	        jQuery("#frmMultiplePmStd input[id='factory']").val(sbuVal);
	        console.log("[Debug] factory field SET to:", sbuVal);
	    } else {
	        console.warn("[Debug] sbuVal is EMPTY/NULL - factory field NOT updated. Current factory field value:",
	                      jQuery("#frmMultiplePmStd input[id='factory']").val());
	    }

	    var factoryId = sbuVal || jQuery("#frmMultiplePmStd input[id='factory']").val() || '';
	    console.log("[Debug] final factoryId to be sent:", factoryId);

	    var topMachId  = jQuery("#cmbPmsdMulMachineid").combobox("getValue");
	    var topCostCtr = jQuery("#cmbPmsdMulCostCenter").combobox("getValue");

	    if (cellId == null || cellId.trim() == '') {
	        alert("Please select Functional Location (JH Cell level)");
	        return;
	    }

	    if (topMachId == null || topMachId.trim() == '') {
	        alert("Select Equipment");
	        return;
	    }

	    var gridval = getGridSelectArray('PmMultipleGrd');
	    if (gridval == "" || gridval == "[]") {
	        alert("Please add at least one row.");
	        return;
	    }

	    if (!validatePmsdMandatory(gridval)) return;

	    var params = "pmsdStdDetails=" + encodeURIComponent(gridval)
	        + "&flId="          + encodeURIComponent(flId)
	        + "&sectionId="     + encodeURIComponent(sectionId)
	        + "&cellId="        + encodeURIComponent(cellId)
	        + "&machId="        + encodeURIComponent(machId)
	        + "&elementId="     + encodeURIComponent(elementId)
	        + "&locationId="    + encodeURIComponent(locationId)   // mano - added
	        + "&costCenterId="  + encodeURIComponent(topCostCtr || '')
	        + "&factoryId="     + encodeURIComponent(factoryId);

	    console.log("[Debug] FINAL params string sent to processAjaxCalls:", params);
	    console.log("=== popup_OnSavePmsdForm END ===");

	    processAjaxCalls(
	        "prvnt_mntncform_multiple_save.prv",
	        params,
	        'frmMultiplePmStd_successsCallback',
	        'pmsdSave_errorCallBack'
	    );
	}
	
	
</script>

<form id="frmMultiplePmStd">
<div id="wrapperPmMul">

<table style="width:1100px;">
    <tr style="position:relative;">
        <td colspan="3" valign="top">
            <div id="frmMultiplePmStdFuntKeyIds" style="width:100%; position:relative;">

                <div style="float:left; padding-right:20px;">
                    <input type="hidden" id="factory" name="cmbPmsdFactoryid" value="${requestScope.plmTlStandards.pmsdFactoryid}"/>
                    <input type="hidden" id="section" name="cmbPmsdSectionid" value="${requestScope.plmTlStandards.pmsdSectionid}"/>
                    <input type="hidden" id="cell"    name="cmbPmsdCellid"    value="${requestScope.plmTlStandards.pmsdCellid}"/>
                    <input type="hidden" id="machine" name="cmbPmsdMachineid" value="${requestScope.plmTlStandards.pmsdMachineid}"/>
                    <input type="hidden" id="flid"    name="cmbPmsdFlid"      value="${requestScope.plmTlStandards.pmsdFlid}"/>
                    <input type="hidden" id="elementId" name="cmbPmsdElementid" value="${requestScope.plmTlStandards.pmsdElementid}"/>
                </div>

                <div id="pmsdMulFunLocation" style="width:84.3%; width:82%\9;"></div>

                <div style="clear:both; height:0px;"></div>

                <table><tr>
                    <td><span id="err_pmsdMulFunLocation" class="tpm-errormsg"></span></td>
                </tr></table>
            </div>
        </td>
    </tr>
</table>

<!-- Cost Center / Equipment now sit above General Information, same as the single-standard screen -->
<div style="clear:both;"></div>
<table style="margin-top:5px;">
    <tr>
        <td valign="top" style="width:27%;">
            <div><label>Cost Center</label></div>
            <div class="easyui-paddingbfpx">
                <input id="cmbPmsdMulCostCenter" name="cmbPmsdMulCostCenter"
                       class="easyui-combobox" style="width:230px;"
                       value="${requestScope.costcenter}">
            </div>
        </td>
        <td valign="top" style="width:27%;">
            <div><label>Equipment</label></div>
            <div class="easyui-paddingbfpx">
                <input id="cmbPmsdMulMachineid" name="cmbPmsdMulMachineid"
                       class="easyui-combobox" style="width:230px;"
                       value="${requestScope.machineId}">
            </div>
        </td>
    </tr>
</table>

<div class="sub-header" style="text-align: left;width:82%;float:left; height : 20px;">
    <span>General Information</span>
</div>
<div style="clear:both;"></div>

<div style="margin-left:0px; margin-top:10px;">
    <input type="button" class="easyui-button" value="Add" id="btnPmsdAdd" style="height:25px;"/>
    <input type="button" class="easyui-button" value="Delete" id="btnPmsdDelete" style="height:23px;margin-left:8px;"/>
</div>

<div style="margin-top:10px; margin-right:160px;">
    <table id="PmMultipleGrd"></table>
    <div id="pmMulPager"></div>
</div>

<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnPmsdMachId" name="hdnPmsdMachId" value="${requestScope.machineId}"/>
<input type="hidden" id="hdnPmsdFlid"   name="hdnPmsdFlid"   value="${requestScope.plmTlStandards.pmsdFlid}"/>
<input type="hidden" id="hdnPmsdKeyid"  name="hdnPmsdKeyid"  value="${requestScope.plmTlStandards.pmsdKeyid}"/>

<%-- Carried over from the single-entry pmFormInfo.jsp / openNewStandard flow so this
     screen still receives the preset context (assembly drill-down, trade, activity type)
     when reached via prvnt_mntncform_multiple_input.prv --%>
     
    <input type="hidden" id="location" name="cmbPmsdLocationid" value="${requestScope.plmTlStandards.pmsdLocationid}"/>
    <input type="hidden" id="factory" name="cmbPmsdFactoryid" value="${requestScope.plmTlStandards.pmsdFactoryid}"/>
    <input type="hidden" id="section" name="cmbPmsdSectionid" value="${requestScope.plmTlStandards.pmsdSectionid}"/>
    <input type="hidden" id="cell"    name="cmbPmsdCellid"    value="${requestScope.plmTlStandards.pmsdCellid}"/>
    <input type="hidden" id="machine" name="cmbPmsdMachineid" value="${requestScope.plmTlStandards.pmsdMachineid}"/>
    <input type="hidden" id="flid"    name="cmbPmsdFlid"      value="${requestScope.plmTlStandards.pmsdFlid}"/>
    <input type="hidden" id="elementId" name="cmbPmsdElementid" value="${requestScope.plmTlStandards.pmsdElementid}"/>
    <input type="hidden" value="${requestScope.assmId}" id="hdnAssmid" name="hdnAssmid"/>
    <input type="hidden" value="${requestScope.plmTlStandards.pmsdActivitytype}" id="hdnPmsdactivityType" name="hdnPmsdactivityType"/>
    <input type="hidden" value="${requestScope.plmTlStandards.pmsdTradeid}" id="hdnPmsdtradeId" name="hdnPmsdtradeId"/>
    <input type="hidden" value="${requestScope.costcenter}" id="hdnPmsdCostcenter" name="hdnPmsdCostcenter"/>
    <input type="hidden" id="hdnPmsdPreparedbyid" name="hdnPmsdPreparedbyid" value="${requestScope.plmTlStandards.pmsdPreparedbyid}"/>



</div>


</form>



			}