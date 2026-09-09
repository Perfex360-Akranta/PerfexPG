<script type="text/javascript">

var popupHazopRows = [];
var popupHazopExcelUploaded = false;
var popupHazopInitialized = false;
var popupHazopInitTryCount = 0;

jQuery(function () {
    setTimeout(function () {
        initHazopExcelPopup();
    }, 500);
});


function initHazopExcelPopup() {

    if (popupHazopInitialized === true) {
        return false;
    }

    if (jQuery("#hazopExcelFile").length === 0 || jQuery("#HazopExcelPopupGrid").length === 0) {

        popupHazopInitTryCount++;

        if (popupHazopInitTryCount <= 10) {
            setTimeout(function () {
                initHazopExcelPopup();
            }, 300);
            return false;
        }

        alert("Hazop Excel popup elements not found.");
        return false;
    }

    popupHazopInitialized = true;

    if (jQuery("#frmHazopExcelPopup").length > 0) {
        initialiseForm("frmHazopExcelPopup");
    }

    setHazopExcelPopupTitle();
    initialiseHazopPopupUploader();
    createHazopPopupGrid([]);
    clearHazopPopupErrorArea();
    bindHazopPopupButtons();
}

function setHazopExcelPopupTitle() {

    try {
        if (jQuery("#divHazopExcelPopup").hasClass("ui-dialog-content")) {
            jQuery("#divHazopExcelPopup").dialog("option", "title", "HAZOP Excel Upload");
        }
    } catch (e) {
    }

    try {
        if (window.parent
                && window.parent.jQuery
                && window.parent.jQuery("#divHazopExcelPopup").hasClass("ui-dialog-content")) {
            window.parent.jQuery("#divHazopExcelPopup").dialog("option", "title", "HAZOP Excel Upload");
        }
    } catch (e) {
    }
}

function bindHazopPopupButtons() {

    jQuery("#btnClearHazopExcel").off("click.hazopclear").on("click.hazopclear", function () {

        jQuery("#frmHazopExcelPopup .qq-upload-list").html("");

        popupHazopExcelUploaded = false;
        popupHazopRows = [];

        clearHazopPopupErrorArea();
        clearHazopPopupGrid();

        return false;
    });


    jQuery("#btnUploadHazopExcel").off("click.hazopupload").on("click.hazopupload", function () {

        var fileText = jQuery.trim(jQuery("#frmHazopExcelPopup .qq-upload-list").text());

        if (fileText.length === 0) {
            alert("Please browse and select Excel file.");
            return false;
        }

        clearHazopPopupErrorArea();
        loadHazopExcelRowsIntoPopup();

        return false;
    });


    jQuery("#btnOkHazopExcel").off("click.hazopok").on("click.hazopok", function () {

        var gridId = "HazopExcelPopupGrid";
        var ids = jQuery("#" + gridId).jqGrid("getDataIDs");

        if (!ids || ids.length === 0) {
            alert("Please upload Excel and load rows first.");
            return false;
        }

        var errMsg = "";
        var rowValidationErrors = [];

        for (var i = 0; i < ids.length; i++) {
            var rowData = jQuery("#" + gridId).jqGrid("getRowData", ids[i]);

            if (rowData.rowHasError === "Y") {
                errMsg += rowData.errorMessage + "\n";
                if (jQuery.trim(rowData.errorMessage).length > 0) {
                    rowValidationErrors.push(rowData.errorMessage);
                }
            }
        }

        if (jQuery.trim(errMsg).length > 0) {
            showHazopPopupErrorArea(rowValidationErrors);
            return false;
        }

        var rows = getPopupHazopRows();
        var populateResult = null;

        if (typeof window.populateHazopGridFromExcel === "function") {
            populateResult = window.populateHazopGridFromExcel(rows);
        } else if (window.parent && typeof window.parent.populateHazopGridFromExcel === "function") {
            populateResult = window.parent.populateHazopGridFromExcel(rows);
        } else {
            alert("Parent Hazop grid function not found.");
            return false;
        }

        if (populateResult === false) {
            showHazopPopupErrorArea(["Unable to load Hazop Excel rows."]);
            return false;
        }

        if (populateResult != null
                && typeof populateResult === "object"
                && populateResult.success === false) {

            if (populateResult.errors && populateResult.errors.length > 0) {
                showHazopPopupErrorArea(populateResult.errors);
            } else {
                showHazopPopupErrorArea(["Unable to load Hazop Excel rows."]);
            }

            return false;
        }

        clearHazopPopupErrorArea();
        closeHazopExcelPopup();

        return false;
    });


    jQuery("#btnCloseHazopExcel").off("click.hazopclose").on("click.hazopclose", function () {
        closeHazopExcelPopup();
        return false;
    });
}

function clearHazopPopupErrorArea() {
    jQuery("#hazopExcelErrorList").html("");
    jQuery("#hazopExcelErrorArea").hide();
}

function showHazopPopupErrorArea(errors) {

    clearHazopPopupErrorArea();

    if (errors == null || errors.length === 0) {
        return false;
    }

    for (var i = 0; i < errors.length; i++) {
        var msg = jQuery.trim(String(errors[i]));

        if (msg.length === 0) {
            continue;
        }

        jQuery("<li/>").text(msg).appendTo("#hazopExcelErrorList");
    }

    if (jQuery("#hazopExcelErrorList li").length > 0) {
        jQuery("#hazopExcelErrorArea").show();
    }

    return false;
}

function getPopupHazopRows() {

    var gridId = "HazopExcelPopupGrid";
    var ids = jQuery("#" + gridId).jqGrid("getDataIDs");
    var rows = [];

    for (var i = 0; i < ids.length; i++) {

        var rowData = jQuery("#" + gridId).jqGrid("getRowData", ids[i]);

        delete rowData.rowHasError;
        delete rowData.errorMessage;

        delete rowData.cmbMohdGuidewordError;
        delete rowData.txtMohdParameterError;
        delete rowData.txtMohdDeviationError;
        delete rowData.txtMohdCausesError;
        delete rowData.txtMohdCosequecesError;
        delete rowData.txtMohdWithoutSafeGuardsError;
        delete rowData.cmbMohdLikeHood1Error;
        delete rowData.cmbMohdSeverity1Error;
        delete rowData.txtMohdRecommentationsError;
        delete rowData.cmbMocrResponsibilityError;
        delete rowData.dteMocrTargetDateError;

        rows.push(rowData);
    }

    return rows;
}

function closeHazopExcelPopup() {

    try {
        closePopUpDialoge("divHazopExcelPopup");
        return false;
    } catch (e) {
    }

    try {
        closePopUpDialog("divHazopExcelPopup");
        return false;
    } catch (e) {
    }

    try {
        jQuery("#divHazopExcelPopup").dialog("close");
        return false;
    } catch (e) {
    }

    jQuery("#divHazopExcelPopup").hide();
    return false;
}

function initialiseHazopPopupUploader() {

    new qq.FileUploader({
        element: document.getElementById("hazopExcelFile"),
        name: "Upload",
        uploadLabelName: "Browse",
        action: "file_upload.dcm",
        params: {},
        numFiles: 1,
        allowedExtensions: [],
        sizeLimit: 65242880,
        minSizeLimit: 1,
        debug: false,

        onSubmit: function(id, fileName) {
            popupHazopExcelUploaded = false;

            if (jQuery(".qq-upload-list").length > 0) {
                jQuery(".qq-upload-list").html("");
            }

            clearHazopPopupErrorArea();
            clearHazopPopupGrid();
        },

        onComplete: function(id, fileName, responseJSON) {
            popupHazopExcelUploaded = true;
            jQuery(".qq-upload-failed-text").hide();
        },

        onCancel: function(id, fileName) {
            popupHazopExcelUploaded = false;
        },

        messages: {
            sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
            minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
            emptyError: "{file} is empty, please select some other file."
        },

        showMessage: function(messages) {
            alert(messages);
        }
    });
}

function loadHazopExcelRowsIntoPopup() {

    jQuery.ajax({
        type: "POST",
        url: "hazop_excel_grid.mocn",
        dataType: "json",

        success: function(result) {

            if (result.success !== true && result.success !== "true") {
                alert(result.msg || "Excel data not found.");
                return false;
            }

            popupHazopRows = result.rows;
            createHazopPopupGrid(result.rows);
            clearHazopPopupErrorArea();
        },

        error: function(xhr, status, error) {
            alert("Error while reading Hazop Excel.");
        }
    });
}

function clearHazopPopupGrid() {

    if (jQuery("#HazopExcelPopupGrid")[0].grid) {
        jQuery("#HazopExcelPopupGrid").jqGrid("clearGridData");
    }
}

function createHazopPopupGrid(rows) {

    var gridId = "HazopExcelPopupGrid";

    if (jQuery("#" + gridId)[0].grid) {
        jQuery("#" + gridId).jqGrid("GridUnload");
    }

    jQuery("#" + gridId).jqGrid({
        datatype: "local",
        data: rows,
        rowNum: 1000,
        height: 300,
        width: 1250,
        shrinkToFit: false,
        rownumbers: true,
        viewrecords: true,
        pager: "#HazopExcelPopupPager",

        colNames: [
            "S.No",
            "Excel Row",
            "Error",
            "Error Message",

            "Key Id",
            "Guide Word Id",
            "Guide Word",
            "Parameter",
            "Deviation",
            "Causes",
            "Consequence",
            "Present Safeguards",

            "Likelihood Id",
            "Likelihood",
            "Severity Id",
            "Severity",
            "RPN",

            "Recommendations",
            "Responsibility",
            "Responsibility Id",
            "Target Date",

            "Status",
            "Status Detail",
            "Date Resolved",
            "Action Plan Id",

            "Revised Likelihood Id",
            "Revised Likelihood",
            "Revised Severity Id",
            "Revised Severity",
            "Revised RPN",
            "Remarks",

            "Guide Word Error",
            "Parameter Error",
            "Deviation Error",
            "Causes Error",
            "Consequence Error",
            "Safeguards Error",
            "Likelihood Error",
            "Severity Error",
            "Recommendations Error",
            "Responsibility Error",
            "Target Date Error"
        ],

        colModel: [
            { name: "excelSno", index: "excelSno", width: 60 },
            { name: "excelRowNo", index: "excelRowNo", width: 70 },
            { name: "rowHasError", index: "rowHasError", hidden: true },
            { name: "errorMessage", index: "errorMessage", width: 360 },

            { name: "hdnMohdKeyid", index: "hdnMohdKeyid", hidden: true },
            { name: "txtMohdGuideword", index: "txtMohdGuideword", hidden: true },
            { name: "cmbMohdGuideword", index: "cmbMohdGuideword", width: 150, editable: true },
            { name: "txtMohdParameter", index: "txtMohdParameter", width: 200, editable: true },
            { name: "txtMohdDeviation", index: "txtMohdDeviation", width: 200, editable: true },
            { name: "txtMohdCauses", index: "txtMohdCauses", width: 230, editable: true },
            { name: "txtMohdCosequeces", index: "txtMohdCosequeces", width: 230, editable: true },
            { name: "txtMohdWithoutSafeGuards", index: "txtMohdWithoutSafeGuards", width: 230, editable: true },

            { name: "txtMohdLikeHood1", index: "txtMohdLikeHood1", hidden: true },
            { name: "cmbMohdLikeHood1", index: "cmbMohdLikeHood1", width: 90, editable: true },
            { name: "txtMohdSeverity1", index: "txtMohdSeverity1", hidden: true },
            { name: "cmbMohdSeverity1", index: "cmbMohdSeverity1", width: 90, editable: true },
            { name: "txtMohdRisk1", index: "txtMohdRisk1", width: 70 },

            { name: "txtMohdRecommentations", index: "txtMohdRecommentations", width: 230, editable: true },
            { name: "cmbMocrResponsibility", index: "cmbMocrResponsibility", width: 170, editable: true },
            { name: "Responsibiltyby", index: "Responsibiltyby", hidden: true },
            { name: "dteMocrTargetDate", index: "dteMocrTargetDate", width: 110, editable: true },

            { name: "cmbMocrStatus", index: "cmbMocrStatus", width: 120 },
            { name: "statusdetail", index: "statusdetail", hidden: true },
            { name: "txtMocrCompleteDate", index: "txtMocrCompleteDate", width: 110 },
            { name: "txtMocrActionplanId", index: "txtMocrActionplanId", width: 120 },

            { name: "txtMohdLikeHood2", index: "txtMohdLikeHood2", hidden: true },
            { name: "cmbMohdLikeHood2", index: "cmbMohdLikeHood2", width: 110, editable: true },
            { name: "txtMohdSeverity2", index: "txtMohdSeverity2", hidden: true },
            { name: "cmbMohdSeverity2", index: "cmbMohdSeverity2", width: 110, editable: true },
            { name: "txtMohdRisk2", index: "txtMohdRisk2", width: 90 },
            { name: "txtMohdRemarks", index: "txtMohdRemarks", width: 220, editable: true },

            { name: "cmbMohdGuidewordError", index: "cmbMohdGuidewordError", hidden: true },
            { name: "txtMohdParameterError", index: "txtMohdParameterError", hidden: true },
            { name: "txtMohdDeviationError", index: "txtMohdDeviationError", hidden: true },
            { name: "txtMohdCausesError", index: "txtMohdCausesError", hidden: true },
            { name: "txtMohdCosequecesError", index: "txtMohdCosequecesError", hidden: true },
            { name: "txtMohdWithoutSafeGuardsError", index: "txtMohdWithoutSafeGuardsError", hidden: true },
            { name: "cmbMohdLikeHood1Error", index: "cmbMohdLikeHood1Error", hidden: true },
            { name: "cmbMohdSeverity1Error", index: "cmbMohdSeverity1Error", hidden: true },
            { name: "txtMohdRecommentationsError", index: "txtMohdRecommentationsError", hidden: true },
            { name: "cmbMocrResponsibilityError", index: "cmbMocrResponsibilityError", hidden: true },
            { name: "dteMocrTargetDateError", index: "dteMocrTargetDateError", hidden: true }
        ],

        loadComplete: function() {
            highlightHazopExcelErrors();
        }
    });
}

function highlightHazopExcelErrors() {

    var gridId = "HazopExcelPopupGrid";
    var ids = jQuery("#" + gridId).jqGrid("getDataIDs");

    for (var i = 0; i < ids.length; i++) {

        var rowId = ids[i];
        var rowData = jQuery("#" + gridId).jqGrid("getRowData", rowId);

        if (rowData.rowHasError === "Y") {

            jQuery("#" + rowId, "#" + gridId).css("background-color", "#fff2cc");
            setHazopPopupErrorCell(rowId, "errorMessage");

            if (rowData.cmbMohdGuidewordError === "Y") setHazopPopupErrorCell(rowId, "cmbMohdGuideword");
            if (rowData.txtMohdParameterError === "Y") setHazopPopupErrorCell(rowId, "txtMohdParameter");
            if (rowData.txtMohdDeviationError === "Y") setHazopPopupErrorCell(rowId, "txtMohdDeviation");
            if (rowData.txtMohdCausesError === "Y") setHazopPopupErrorCell(rowId, "txtMohdCauses");
            if (rowData.txtMohdCosequecesError === "Y") setHazopPopupErrorCell(rowId, "txtMohdCosequeces");
            if (rowData.txtMohdWithoutSafeGuardsError === "Y") setHazopPopupErrorCell(rowId, "txtMohdWithoutSafeGuards");
            if (rowData.cmbMohdLikeHood1Error === "Y") setHazopPopupErrorCell(rowId, "cmbMohdLikeHood1");
            if (rowData.cmbMohdSeverity1Error === "Y") setHazopPopupErrorCell(rowId, "cmbMohdSeverity1");
            if (rowData.txtMohdRecommentationsError === "Y") setHazopPopupErrorCell(rowId, "txtMohdRecommentations");
            if (rowData.cmbMocrResponsibilityError === "Y") setHazopPopupErrorCell(rowId, "cmbMocrResponsibility");
            if (rowData.dteMocrTargetDateError === "Y") setHazopPopupErrorCell(rowId, "dteMocrTargetDate");
        }
    }
}

function setHazopPopupErrorCell(rowId, colName) {
    jQuery("#HazopExcelPopupGrid").jqGrid(
        "setCell",
        rowId,
        colName,
        "",
        { "background-color": "#ffcccc" }
    );
}

</script>


<form id="frmHazopExcelPopup" name="frmHazopExcelPopup">

    <div style="margin:10px;">

        <div style="margin-bottom:8px; font-weight:bold;">HAZOP Excel Upload</div>

        <div style="margin-bottom:10px; text-align:right;">

            <div id="hazopExcelFile" style="display:inline-block; margin-right:10px;"></div>

            <input id="btnClearHazopExcel"
                   class="easyui-button"
                   type="button"
                   value="Clear"
                   style="width:80px;height:25px;" />

            <input id="btnUploadHazopExcel"
                   class="easyui-button"
                   type="button"
                   value="Upload"
                   style="width:90px;height:25px;margin-left:10px;" />

            <input id="btnOkHazopExcel"
                   class="easyui-button"
                   type="button"
                   value="OK"
                   style="width:80px;height:25px;margin-left:30px;" />

            <input id="btnCloseHazopExcel"
                   class="easyui-button"
                   type="button"
                   value="Close"
                   style="width:80px;height:25px;margin-left:10px;" />

        </div>

        <div id="hazopExcelErrorArea"
             style="display:none; margin-bottom:10px; border:1px solid #f0ad4e; background:#fff8e5; padding:8px;">
            <div style="color:#b94a48; font-weight:bold; margin-bottom:4px;">Validation Errors</div>
            <ul id="hazopExcelErrorList" style="margin:0; padding-left:18px; max-height:100px; overflow:auto;"></ul>
        </div>

        <table id="HazopExcelPopupGrid"></table>
        <div id="HazopExcelPopupPager"></div>

    </div>

</form>
