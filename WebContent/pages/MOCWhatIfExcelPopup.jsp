<script type="text/javascript">

var popupWhatIfRows = [];
var popupExcelUploaded = false;
var popupWhatIfInitialized = false;
var popupInitTryCount = 0;

jQuery(function () {
    setTimeout(function () {
        initWhatIfExcelPopup();
    }, 500);
});


function initWhatIfExcelPopup() {

    if (popupWhatIfInitialized === true) {
        return false;
    }

    // Wait until popup HTML elements are actually available
    if (jQuery("#popupWhatIfFileUpload").length === 0 || jQuery("#WhatIfExcelPopupGrid").length === 0) {

        popupInitTryCount++;

        if (popupInitTryCount <= 10) {
            setTimeout(function () {
                initWhatIfExcelPopup();
            }, 300);
            return false;
        }

        alert("WhatIf Excel popup elements not found.");
        return false;
    }

    popupWhatIfInitialized = true;

    if (jQuery("#frmWhatIfExcelPopup").length > 0) {
        initialiseForm("frmWhatIfExcelPopup");
    }

    initialiseWhatIfPopupUploader();

    createWhatIfPopupGrid([]);
    
    bindWhatIfPopupButtons();
}
function bindWhatIfPopupButtons() {

    // Clear button
    jQuery("#popupBtnClearExcel").off("click.whatifclear").on("click.whatifclear", function () {

        jQuery("#frmWhatIfExcelPopup .qq-upload-list").html("");

        popupExcelUploaded = false;
        popupWhatIfRows = [];

        clearPopupGrid();

        return false;
    });


    // Upload button
    jQuery("#popupBtnUploadExcel").off("click.whatifupload").on("click.whatifupload", function () {

        var fileText = jQuery.trim(jQuery("#frmWhatIfExcelPopup .qq-upload-list").text());

        if (fileText.length === 0) {
            alert("Please browse and select Excel file.");
            return false;
        }

        loadWhatIfExcelRowsIntoPopup();

        return false;
    });


    // OK button
    jQuery("#btnOkWhatIfExcel").off("click.whatifok").on("click.whatifok", function () {

        var gridId = "WhatIfExcelPopupGrid";
        var ids = jQuery("#" + gridId).jqGrid("getDataIDs");

        if (!ids || ids.length === 0) {
            alert("Please upload Excel and load rows first.");
            return false;
        }

        var errMsg = "";

        for (var i = 0; i < ids.length; i++) {
            var rowData = jQuery("#" + gridId).jqGrid("getRowData", ids[i]);

            if (rowData.rowHasError === "Y") {
                errMsg += rowData.errorMessage + "\n";
            }
        }

        if (jQuery.trim(errMsg).length > 0) {
            alert("Please correct the following Excel errors before loading to WhatIf grid:\n\n" + errMsg);
            return false;
        }

        var rows = getPopupWhatIfRows();

        if (typeof window.populateWhatIfGridFromExcel === "function") {
            window.populateWhatIfGridFromExcel(rows);
        }
        else if (window.parent && typeof window.parent.populateWhatIfGridFromExcel === "function") {
            window.parent.populateWhatIfGridFromExcel(rows);
        }
        else {
            alert("Parent WhatIf grid function not found.");
            return false;
        }

        closeWhatIfExcelPopup();

        return false;
    });


    // Close button
    jQuery("#btnCloseWhatIfExcel").off("click.whatifclose").on("click.whatifclose", function () {
        closeWhatIfExcelPopup();
        return false;
    });
}

function getPopupWhatIfRows() {

    var gridId = "WhatIfExcelPopupGrid";
    var ids = jQuery("#" + gridId).jqGrid("getDataIDs");

    var rows = [];

    for (var i = 0; i < ids.length; i++) {

        var rowData = jQuery("#" + gridId).jqGrid("getRowData", ids[i]);

        delete rowData.excelSno;
        delete rowData.excelRowNo;
        delete rowData.rowHasError;
        delete rowData.errorMessage;

        delete rowData.txtWifdWhatIfError;
        delete rowData.txtWifdCausesError;
        delete rowData.txtWifdCosequecesError;
        delete rowData.txtWifdWithoutSafeGuardsError;
        delete rowData.cmbWifdLikeHood1Error;
        delete rowData.cmbWifdSeverity1Error;
        delete rowData.txtWifdRecommentationsError;
        delete rowData.cmbMocrResponsibilityError;
        delete rowData.dteMocrTargetDateError;

        rows.push(rowData);
    }

    return rows;
}
function closeWhatIfExcelPopup() {

    try {
        closePopUpDialoge("divWhatIfExcelPopup");
        return false;
    } catch (e) {
        // fallback
    }

    try {
        closePopUpDialog("divWhatIfExcelPopup");
        return false;
    } catch (e) {
        // fallback
    }

    try {
        jQuery("#divWhatIfExcelPopup").dialog("close");
        return false;
    } catch (e) {
        // fallback
    }

    jQuery("#divWhatIfExcelPopup").hide();

    return false;
}
function initialiseWhatIfPopupUploader() {

    new qq.FileUploader({
        element: document.getElementById("popupWhatIfFileUpload"),
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
            popupExcelUploaded = false;

            if (jQuery(".qq-upload-list").length > 0) {
                jQuery(".qq-upload-list").html("");
            }

            clearPopupGrid();
        },

        onComplete: function(id, fileName, responseJSON) {
            popupExcelUploaded = true;
            jQuery(".qq-upload-failed-text").hide();
        },

        onCancel: function(id, fileName) {
            popupExcelUploaded = false;
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

jQuery(document).on("click", "#popupBtnClearExcel", function () {

    jQuery(".qq-upload-list").html("");
    popupExcelUploaded = false;
    popupWhatIfRows = [];

    clearPopupGrid();
});
/*
jQuery(document).on("click", "#popupBtnUploadExcel", function () {

    var fileText = jQuery.trim(jQuery(".qq-upload-list").text());

    if (fileText.length === 0) {
        alert("Please browse and select Excel file.");
        return false;
    }

    loadWhatIfExcelRowsIntoPopup();
}); 
*/

function loadWhatIfExcelRowsIntoPopup() {

    jQuery.ajax({
        type: "POST",
        url: "whatif_excel_grid.mocn",
        dataType: "json",

        success: function(result) {

            if (result.success !== true && result.success !== "true") {
                alert(result.msg || "Excel data not found.");
                return false;
            }

            popupWhatIfRows = result.rows;

            createWhatIfPopupGrid(result.rows);

            if (result.hasErrors === true || result.hasErrors === "true") {
        //        alert("Excel has validation errors:\n\n" + result.msg);
            }
        },

        error: function(xhr, status, error) {
            alert("Error while reading WhatIf Excel.");
        }
    });
}

function clearPopupGrid() {

    if (jQuery("#WhatIfExcelPopupGrid")[0].grid) {
        jQuery("#WhatIfExcelPopupGrid").jqGrid("clearGridData");
    }
}

function createWhatIfPopupGrid(rows) {

    var gridId = "WhatIfExcelPopupGrid";

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
        pager: "#WhatIfExcelPopupPager",

        colNames: [
            "S.No",
            "Excel Row",
            "Error",
            "Error Message",

            "Key Id",
            "Whatif",
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
            "TargetDate",

            "Status",
            "Status Detail",
            "DateResolved",
            "Action PlanId",

            "Revised Likelihood Id",
            "Revised Likelihood",
            "Revised Severity Id",
            "Revised Severity",
            "Revised RPN",
            "Remarks",

            "Whatif Error",
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
            { name: "errorMessage", index: "errorMessage", width: 350 },

            { name: "hdnWifdKeyid", index: "hdnWifdKeyid", hidden: true },

            { name: "txtWifdWhatIf", index: "txtWifdWhatIf", width: 250, editable: true },
            { name: "txtWifdCauses", index: "txtWifdCauses", width: 250, editable: true },
            { name: "txtWifdCosequeces", index: "txtWifdCosequeces", width: 250, editable: true },
            { name: "txtWifdWithoutSafeGuards", index: "txtWifdWithoutSafeGuards", width: 250, editable: true },

            { name: "txtWifdLikeHood1", index: "txtWifdLikeHood1", hidden: true },
            { name: "cmbWifdLikeHood1", index: "cmbWifdLikeHood1", width: 80, editable: true },

            { name: "txtWifdSeverity1", index: "txtWifdSeverity1", hidden: true },
            { name: "cmbWifdSeverity1", index: "cmbWifdSeverity1", width: 80, editable: true },

            { name: "txtWifdRisk1", index: "txtWifdRisk1", width: 60 },

            { name: "txtWifdRecommentations", index: "txtWifdRecommentations", width: 250, editable: true },

            { name: "cmbMocrResponsibility", index: "cmbMocrResponsibility", width: 160, editable: true },
            { name: "Responsibiltyby", index: "Responsibiltyby", hidden: true },

            { name: "dteMocrTargetDate", index: "dteMocrTargetDate", width: 100, editable: true },

            { name: "cmbMocrStatus", index: "cmbMocrStatus", width: 110 },
            { name: "statusdetail", index: "statusdetail", hidden: true },

            { name: "txtMocrCompleteDate", index: "txtMocrCompleteDate", width: 100 },
            { name: "txtMocrActionplanId", index: "txtMocrActionplanId", width: 100 },

            { name: "txtWifdLikeHood2", index: "txtWifdLikeHood2", hidden: true },
            { name: "cmbWifdLikeHood2", index: "cmbWifdLikeHood2", width: 80, editable: true },

            { name: "txtWifdSeverity2", index: "txtWifdSeverity2", hidden: true },
            { name: "cmbWifdSeverity2", index: "cmbWifdSeverity2", width: 80, editable: true },

            { name: "txtWifdRisk2", index: "txtWifdRisk2", width: 60 },
            { name: "txtWifdRemarks", index: "txtWifdRemarks", width: 250, editable: true },

            { name: "txtWifdWhatIfError", index: "txtWifdWhatIfError", hidden: true },
            { name: "txtWifdCausesError", index: "txtWifdCausesError", hidden: true },
            { name: "txtWifdCosequecesError", index: "txtWifdCosequecesError", hidden: true },
            { name: "txtWifdWithoutSafeGuardsError", index: "txtWifdWithoutSafeGuardsError", hidden: true },
            { name: "cmbWifdLikeHood1Error", index: "cmbWifdLikeHood1Error", hidden: true },
            { name: "cmbWifdSeverity1Error", index: "cmbWifdSeverity1Error", hidden: true },
            { name: "txtWifdRecommentationsError", index: "txtWifdRecommentationsError", hidden: true },
            { name: "cmbMocrResponsibilityError", index: "cmbMocrResponsibilityError", hidden: true },
            { name: "dteMocrTargetDateError", index: "dteMocrTargetDateError", hidden: true }
        ],

        loadComplete: function() {
            highlightWhatIfExcelErrors();
        }
    });
}

function highlightWhatIfExcelErrors() {

    var gridId = "WhatIfExcelPopupGrid";
    var ids = jQuery("#" + gridId).jqGrid("getDataIDs");

    for (var i = 0; i < ids.length; i++) {

        var rowId = ids[i];
        var rowData = jQuery("#" + gridId).jqGrid("getRowData", rowId);

        if (rowData.rowHasError === "Y") {

            jQuery("#" + rowId, "#" + gridId).css("background-color", "#fff2cc");

            setPopupErrorCell(rowId, "errorMessage");

            if (rowData.txtWifdWhatIfError === "Y") setPopupErrorCell(rowId, "txtWifdWhatIf");
            if (rowData.txtWifdCausesError === "Y") setPopupErrorCell(rowId, "txtWifdCauses");
            if (rowData.txtWifdCosequecesError === "Y") setPopupErrorCell(rowId, "txtWifdCosequeces");
            if (rowData.txtWifdWithoutSafeGuardsError === "Y") setPopupErrorCell(rowId, "txtWifdWithoutSafeGuards");
            if (rowData.cmbWifdLikeHood1Error === "Y") setPopupErrorCell(rowId, "cmbWifdLikeHood1");
            if (rowData.cmbWifdSeverity1Error === "Y") setPopupErrorCell(rowId, "cmbWifdSeverity1");
            if (rowData.txtWifdRecommentationsError === "Y") setPopupErrorCell(rowId, "txtWifdRecommentations");
            if (rowData.cmbMocrResponsibilityError === "Y") setPopupErrorCell(rowId, "cmbMocrResponsibility");
            if (rowData.dteMocrTargetDateError === "Y") setPopupErrorCell(rowId, "dteMocrTargetDate");
        }
    }
}

function setPopupErrorCell(rowId, colName) {
    jQuery("#WhatIfExcelPopupGrid").jqGrid(
        "setCell",
        rowId,
        colName,
        "",
        { "background-color": "#ffcccc" }
    );
}

</script>


<form id="frmWhatIfExcelPopup" name="frmWhatIfExcelPopup">

  <div style="margin:10px;">

    <div style="margin-bottom:10px; text-align:right;">

        <div id="popupWhatIfFileUpload"
             style="display:inline-block; margin-right:10px;"></div>

        <input id="popupBtnClearExcel"
               class="easyui-button"
               type="button"
               value="Clear"
               style="width:80px;height:25px;" />

        <input id="popupBtnUploadExcel"
               class="easyui-button"
               type="button"
               value="Upload"
               style="width:90px;height:25px;margin-left:10px;" />

        <input id="btnOkWhatIfExcel"
               class="easyui-button"
               type="button"
               value="OK"
               style="width:80px;height:25px;margin-left:30px;" />

        <input id="btnCloseWhatIfExcel"
               class="easyui-button"
               type="button"
               value="Close"
               style="width:80px;height:25px;margin-left:10px;" />

    </div>

    <table id="WhatIfExcelPopupGrid"></table>
    <div id="WhatIfExcelPopupPager"></div>

</div>

</form>
