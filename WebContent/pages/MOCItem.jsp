<script>
var selectedMocItemKeyid = "";

if (typeof REPLACE_RULES === 'undefined') {
    var REPLACE_RULES = [];
}

jQuery(document).ready(function(){

    initialiseForm('frmMocItemMapping');
    jQuery('#submitForm').val('frmMocItemMapping');
    readOnlyFields("txtMocItemKeyid");

    var frmmode = jQuery("#mode").val();

    var factId    = jQuery("#frmMocItemMappingFuntKeyIds input[id='factory']").val() || "";
    var sectionId = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";
    var cellId    = jQuery("#frmMocItemMappingFuntKeyIds input[id='cell']").val()    || "";
    var machId    = jQuery("#frmMocItemMappingFuntKeyIds input[id='machine']").val() || "";
    var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";

    console.log("=== document.ready === flid=[" + flid + "]");

    var dataStr = "&factId="    + factId
                + "&sectionId=" + sectionId
                + "&cellId="    + cellId
                + "&machId="    + machId
                + "&flid="      + flid;

    loadFunctionalLocation(
        "MocItemFunLocation",
        "functionalLoc.smoc",
        "MocItemFunLocationValues",
        "frmMocItemMapping",
        dataStr
    );

    if (frmmode === "view") {
        disableForm("frmMocItemMapping");
    }

    jQuery('#btnNew').click(function() {
        clearMocItemForm();
    });

    /* setTimeout(function(){
        var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";
        var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";
        console.log("setTimeout grid load flid=[" + flid + "] sectionid=[" + sectionid + "]");
        loadMocItemGrid(flid, sectionid);
    }, 1500); */

});


// ============================================================
// FUNCTIONAL LOCATION CALLBACK
// ============================================================
function frmMocItemMapping_FuntLocHierarchy_SuccessCallBack(result) {

    console.log("=== FuntLocHierarchy_SuccessCallBack ===");
    console.log("result:", result);

    setFunctionalLocWidth("frmMocItemMapping", "620px");

    // Always read flid and sectionid from hidden fields — same pattern as WhyWhy
    var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || result.flId   || "";
    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || result.sectId || "";

    console.log("FL callback — hidden field flid=[" + flid + "] sectionid=[" + sectionid + "]");

    // Read keyid and item name directly from form fields (set before FL reload in getById callback)
    var keyid    = jQuery("#txtMocItemKeyid").val() || "";
    var itemName = jQuery("#txtMocItemName").val()  || "";

    if (keyid && keyid.trim().length > 0) {
        // Editing an existing record — fields already set in getById callback, just confirm mode
        jQuery("#hdnkeyId").val(keyid);
        selectedMocItemKeyid = keyid.trim();
        jQuery("#mode").val("edit");
        console.log("✅ FL callback confirmed edit keyid=[" + keyid + "] name=[" + itemName + "]");
    } /* else {
        // New record or cleared form — track page-level FL for grid reload
        window._pageFlid      = flid;
        window._pageSectionid = sectionid;
        console.log("✅ Page FL updated flid=[" + flid + "] sectionid=[" + sectionid + "]");
    } */

    console.log("Loading grid with flid=[" + flid + "] sectionid=[" + sectionid + "]");
    loadMocItemGrid(flid, sectionid);
}


// ============================================================
// GRID LOAD
// ============================================================
function loadMocItemGrid(flid, sectionid) {

    console.log(">>> loadMocItemGrid flid=[" + flid + "] sectionid=[" + sectionid + "]");

    var encodedFlid      = encodeURIComponent(flid      || "");
    var encodedSectionid = encodeURIComponent(sectionid || "");

    var colUrl  = "MocItem_input.smoc?flid=" + encodedFlid
                + "&sectionid=" + encodedSectionid;

    var dataStr = "&flid=" + encodedFlid
                + "&sectionid=" + encodedSectionid;

    console.log("colUrl=[" + colUrl + "] dataStr=[" + dataStr + "]");

    processGridnew(
        colUrl,
        dataStr,
        "MocItemListGrid",
        "MocItemListPager",
        "",
        "doubleClickMocItemGrid",
        "",
        "loadComplete",
        "",
        ""
    );

    setTimeout(function () {
        jQuery("#MocItemListGrid").setGridWidth(
            jQuery("#MocItemListGrid").closest("td").width() - 10
        );
    }, 200);
}


// ============================================================
// DOUBLE CLICK — EDIT ROW
// ============================================================
function doubleClickMocItemGrid(rowid) {

    console.log("=== doubleClickMocItemGrid rowid=[" + rowid + "] ===");

    var keyid = jQuery("#MocItemListGrid").jqGrid('getCell', rowid, 'moc_itm_keyid');
    console.log("getCell(moc_itm_keyid):", keyid);

    if (!keyid || keyid.trim().length === 0) {
        keyid = jQuery("#MocItemListGrid").jqGrid('getCell', rowid, 0);
        console.log("getCell(0):", keyid);
    }

    if (!keyid || keyid.trim().length === 0) {
        var rowData = jQuery("#MocItemListGrid").jqGrid('getRowData', rowid);
        console.log("rowData keys:", JSON.stringify(rowData));
        keyid = rowData['moc_itm_keyid']
             || rowData['Key ID']
             || rowData['KEYID']
             || rowData[0]
             || "";
        console.log("keyid from rowData:", keyid);
    }

    if (!keyid || keyid.trim().length === 0) {
        console.log("❌ No keyid found in row");
        popupCommonErrorMsg("Could not get record ID. Check console.");
        return;
    }

    selectedMocItemKeyid = keyid;
    console.log("✅ selectedMocItemKeyid stored:", selectedMocItemKeyid);

    jQuery("#txtMocItemKeyid").val(keyid);
    jQuery("#hdnkeyId").val(keyid);
    jQuery("#mode").val("edit");

    processAjaxCalls(
        "MocItem_getById.smoc",
        "keyid=" + keyid,
        "mocItemGetById_successCallback",
        "mocItemGetById_errorCallback"
    );
}


// ============================================================
// GET BY ID CALLBACK
// ============================================================
function mocItemGetById_successCallback(result) {

    console.log("=== mocItemGetById_successCallback ===");
    console.log("result:", JSON.stringify(result));

    var success = result.success || (result.data && result.data.mocItmKeyid);
    var data    = result.data || result;

    if (!success || !data || !data.mocItmKeyid) {
        popupCommonErrorMsg(result.msg || "Record not found");
        return;
    }

    selectedMocItemKeyid = data.mocItmKeyid.trim();

    // ✅ Set all form fields AND hidden fields BEFORE calling loadFunctionalLocation
    // FL callback will find these in the hidden fields — no pending globals needed
    jQuery("#txtMocItemKeyid").val(data.mocItmKeyid);
    jQuery("#hdnkeyId").val(data.mocItmKeyid);
    jQuery("#txtMocItemName").val(data.mocItmItem || "");
    jQuery("#mode").val("edit");

    // ✅ Write flid and sectionid into hidden fields so FL callback reads them back correctly
    jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val(data.mocItmFlid    || "");
    jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val(data.mocItmSectId || "");

    console.log("Set hidden flid=[" + data.mocItmFlid + "] section=[" + data.mocItmSectId + "]");
    console.log("Set txtMocItemKeyid=[" + data.mocItmKeyid + "] txtMocItemName=[" + data.mocItmItem + "]");

    if (data.mocItmFlid) {

        var dataStr = "&flid="      + encodeURIComponent(data.mocItmFlid)
                    + "&factId="    + ""
                    + "&sectionId=" + encodeURIComponent(data.mocItmSectId || "")
                    + "&cellId="    + ""
                    + "&machId="    + "";

        loadFunctionalLocation(
            "MocItemFunLocation",
            "functionalLoc.smoc",
            "MocItemFunLocationValues",
            "frmMocItemMapping",
            dataStr
        );

    } else {
        console.warn("mocItmFlid is empty — cannot reload FL hierarchy");
    }

    jQuery("#txtMocItemName").focus();
}

function mocItemGetById_errorCallback(result) {
    popupCommonErrorMsg("Failed to load record");
}


// ============================================================
// BEFORE SUBMIT VALIDATION
// ============================================================
function frmMocItemMapping_beforeSubmit() {

    var mode   = jQuery("#mode").val();
    var keyid  = jQuery("#txtMocItemKeyid").val();
    var hdnKey = jQuery("#hdnkeyId").val();

    console.log("=== beforeSubmit ===");
    console.log("mode=[" + mode + "]");
    console.log("txtMocItemKeyid=[" + keyid + "]");
    console.log("hdnkeyId=[" + hdnKey + "]");
    console.log("selectedMocItemKeyid=[" + selectedMocItemKeyid + "]");

    if (mode === "view") {
        alert("Cannot save in view mode");
        return false;
    }

    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val();

    if (!sectionid || sectionid.trim().length === 0) {
        popupCommonErrorMsg("Please select a DMT (Functional Location) before saving");
        return false;
    }

    var mocItemName = jQuery("#txtMocItemName").val();

    if (!mocItemName || mocItemName.trim().length === 0) {
        popupCommonErrorMsg("Please enter MOC Item Name");
        return false;
    }

    // Safety net: if hidden field was cleared by FL reload, restore from selectedMocItemKeyid
    if ((!keyid || keyid.trim().length === 0) && selectedMocItemKeyid) {
        console.warn("txtMocItemKeyid was empty — restoring from selectedMocItemKeyid");
        jQuery("#txtMocItemKeyid").val(selectedMocItemKeyid);
        jQuery("#hdnkeyId").val(selectedMocItemKeyid);
    }

    return true;
}


// ============================================================
// SAVE SUCCESS
// ============================================================
function frmMocItemMapping_successsCallback(result) {

    console.log("=== successsCallback fired ===");
    console.log("result:", result);

    // Read FL from hidden fields — same as WhyWhy pattern
    /* var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || window._pageFlid      || "";
    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || window._pageSectionid || "";
 */
 
	 var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";
 	 var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";	
 
    console.log("flid=[" + flid + "] sectionid=[" + sectionid + "]");

    clearMocItemForm();
    loadMocItemGrid(flid, sectionid);

    var msg = (result.successData && result.successData.msg) || "Saved Successfully";
}


// ============================================================
// DELETE
// ============================================================
function frmMocItemMapping_beforeDelete(result) {

    console.log("=== frmMocItemMapping_beforeDelete ===");
    console.log("selectedMocItemKeyid:", selectedMocItemKeyid);
    console.log("txtMocItemKeyid:", jQuery("#txtMocItemKeyid").val());

    var keyid = selectedMocItemKeyid
             || jQuery("#txtMocItemKeyid").val()
             || "";

    keyid = keyid.trim();
    console.log("Final keyid for delete:", keyid);

    if (!keyid || keyid.trim().length === 0) {
        popupCommonErrorMsg("Please double-click a row from the grid to select before deleting");
        return false;
    }

    var r = confirm("Do you want to delete? Click OK to continue");
    if (!r) return false;

    console.log("Calling MocItem_delete.smoc keyid=[" + keyid + "]");

    processAjaxCalls(
        "MocItem_delete.smoc",
        "keyid=" + keyid,
        "mocItemDelete_successCallback",
        "mocItemDelete_errorCallback"
    );

    return false;
}

function mocItemDelete_successCallback(result) {

    console.log("=== mocItemDelete_successCallback ===");
    console.log("result:", JSON.stringify(result));

    var msg = (result.successData && result.successData.msg)
           || result.msg
           || "Deleted Successfully";

    // Read from hidden fields before clearing
    /* var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || window._pageFlid      || "";
    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || window._pageSectionid || "";
     */
    var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";
    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";

    console.log("delete reload flid=[" + flid + "] sectionid=[" + sectionid + "]");

    clearMocItemForm();
    loadMocItemGrid(flid, sectionid);
    alert(msg);
}

function mocItemDelete_errorCallback(result) {
    console.log("mocItemDelete_errorCallback:", JSON.stringify(result));
    popupCommonErrorMsg("Delete failed. Please try again.");
}

function frmMocItemMapping_deleteSuccessCallbacks(result) {

    console.log("=== frmMocItemMapping_deleteSuccessCallbacks ===");
    var msg = (result.successData && result.successData.msg) || "Deleted Successfully";

    var flid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";
    var sectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";

    loadMocItemGrid(flid, sectionid);
    alert(msg);
}


// ============================================================
// CLEAR FORM
// ============================================================
function clearMocItemForm() {
    jQuery("#txtMocItemName").val("");
    jQuery("#txtMocItemKeyid").val("");
    jQuery("#hdnkeyId").val("");
    jQuery("#mode").val("create");
    selectedMocItemKeyid = "";

    // Restore FL display to page-level selection using hidden fields
    /* var pageFlid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || window._pageFlid      || "";
    var pageSectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || window._pageSectionid || "";
     */
    var pageFlid      = jQuery("#frmMocItemMappingFuntKeyIds input[id='flid']").val()    || "";
    var pageSectionid = jQuery("#frmMocItemMappingFuntKeyIds input[id='section']").val() || "";

    if (pageFlid) {
        var dataStr = "&flid="      + encodeURIComponent(pageFlid)
                    + "&factId="    + ""
                    + "&sectionId=" + encodeURIComponent(pageSectionid)
                    + "&cellId="    + ""
                    + "&machId="    + "";

        loadFunctionalLocation(
            "MocItemFunLocation",
            "functionalLoc.smoc",
            "MocItemFunLocationValues",
            "frmMocItemMapping",
            dataStr
        );
    }

    jQuery("#txtMocItemName").focus();
    console.log("Form cleared");
}

</script>


<form id="frmMocItemMapping" name="frmMocItemMapping">

    <div id="wrapper">

        <table width="100%">

            <tr>
                <td colspan="4">

                    <div id="frmMocItemMappingFuntKeyIds">

                        <input
                                type="hidden"
                                id="factory"
                                name="smocFactoryid"
                                value=" "
                        />

                        <input
                                type="hidden"
                                id="section"
                                name="smocSectionid"
                                value=" "
                        />

                        <input
                                type="hidden"
                                id="cell"
                                name="smocCellid"
                                value=" "
                        />

                        <input
                                type="hidden"
                                id="machine"
                                name="smocEquipmentid"
                                value=" "
                        />

                        <input
                                type="hidden"
                                id="flid"
                                name="smocFlid"
                                value="${requestScope.MocItemMapping.phnmSectFlid}"
                        />

                    </div>

                    <div
                            id="MocItemFunLocation"
                            style="width:100%; margin-top:-5px;"
                    ></div>

                </td>
            </tr>


            <!-- Moc item name -->
            <tr>

                <td valign="top" style="padding-left:6px;padding-top:16px;">

                    <div style="margin-left:10px;">

                        <label
                                id="lblMocItemName"
                                class="mandatory-lbl"
                        >
                            MOC Item Name
                        </label>

                    </div>

                    <%-- <textarea
                            rows="3"
                            cols="17"
                            id="txtMocItemName"
                            maxlength="100"
                            name="txtMocItemName"
                            style="height:30px; margin-left:5px; width:320px;"
                            required="required"
                    >${requestScope.mocItemName}</textarea> --%>
                    
                    <input
        				type="text"
        				id="txtMocItemName"
        				maxlength="100"
        				name="txtMocItemName"
        				class="easyui-text"
        				style=" width : 320px;"
        				value="${requestScope.mocItemName}"/>

                </td>

            </tr>


            <!-- MOC Item List Grid -->
            <tr>

                <td colspan="4" style="padding-top:10px;">

                    <table id="MocItemListGrid"></table>

                    <div
                            id="MocItemListPager"
                            style="margin-top:4px;"
                    ></div>

                </td>

            </tr>


            <!-- DMT Grid -->
            <tr>

                <td colspan="4" style="padding-top:14px;">

                    <table id="MocItemMappingGrid"></table>

                    <div
                            id="MocItemMappingPager"
                            style="margin-top:4px;"
                    ></div>

                </td>

            </tr>

        </table>

    </div>


    <!-- Hidden fields -->

    <input
            type="hidden"
            id="txtMocItemKeyid"
            name="txtMocItemKeyid"
            value="${requestScope.mocItemMapping.phnmKeyid}"/>

    <input
            type="hidden"
            id="mode"
            name="mode"
            value="${requestScope.mode}"/>

    <input
            type="hidden"
            id="hdnkeyId"
            name="hdnkeyId"
            value="${requestScope.keyId}"/>

    <input
            type="hidden"
            id="txtSmocTempfield1"
            name="txtSmocTempfield1"
            value="${requestScope.mocItemMapping.phnmTempfield1}"/>

    <input
            type="hidden"
            id="txtSmocTempfield2"
            name="txtSmocTempfield2"
            value="${requestScope.mocItemMapping.phnmTempfield2}"/>

    <input
            type="hidden"
            id="txtSmocTempfield3"
            name="txtSmocTempfield3"
            value="${requestScope.mocItemMapping.phnmTempfield3}"/>

    <input
            type="hidden"
            id="hdnMocItemKeyid"
            name="hdnMocItemKeyid"
            value=""/>

</form>