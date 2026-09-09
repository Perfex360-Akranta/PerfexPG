<script>

// added by priyanka 
function kapWorkflowCheckboxFormatter(cellValue, options, rowObject) {
    var rowId = options.rowId;

    return '<input type="checkbox"'
        + ' id="chkKapEdit_' + rowId + '"'
        + ' class="kapEditChk"'
        + ' data-rowid="' + rowId + '"'
        + ' disabled="disabled"'
        + ' />';
}
// end
jQuery(document).ready(function () {

    var empId = jQuery.trim(
        jQuery('#hdnPopupEmpId').val() || ""
    );

    var refId = jQuery.trim(
        jQuery('#hdnPopupKznKeyid').val() || 
        jQuery('#hdnPopupRefId').val() ||
        ""
    );

    var refType = jQuery.trim(
        jQuery('#hdnPopupRefType').val() || ""
    );

    var transCode = jQuery.trim(
        jQuery('#hdnPopupTransCode').val() || ""
    );

    var flId = jQuery.trim(
        jQuery('#hdnPopupFlId').val() || ""
    );

    var refRoleId = jQuery("#hdnUserRole").val();
    var enable = jQuery.trim(
        jQuery('#hdnPopupEnable').val() || "N"
    );

    console.log("empId:", empId);
    console.log("refId:", refId);
    console.log("refType:", refType);
    console.log("transCode:", transCode);
    console.log("flId:", flId);
    console.log("refRoleId:", refRoleId);
    console.log("enable:", enable);

    if (refId === "") {
        alert("Kaizen reference ID is empty");
        return;
    }

    if (transCode === "") {
        alert("Workflow transaction code is empty");
        return;
    }

    var dataStr =
        "q=2" +
        "&empId=" + encodeURIComponent(empId) +
        "&refId=" + encodeURIComponent(refId) +
        "&refType=" + encodeURIComponent(refType) +
        "&transCode=" + encodeURIComponent(transCode) +
        "&flId=" + encodeURIComponent(flId) +
        "&refRoleId=" + encodeURIComponent(refRoleId) +
        "&enable=" + encodeURIComponent(enable);

    console.log("Kaizen Approval Delete payload:",dataStr);

    processGridnew(
        "KaizenApprovalWorkFlowInfo_input.appm",
        dataStr,
        "kapWorkFlowGrid",
        "kapWorkFlowPager",
        "",
        "",
        "",
        "kapWorkFlowGridComplete"
    );
});



// added by priyanka changed here on 06/08/2026

function kapWorkFlowGridComplete(jqGridId) {
    var gridId = jqGridId || "kapWorkFlowGrid";
    var $grid = jQuery("#" + gridId);
    var rowIds = $grid.jqGrid("getDataIDs");
    var eligibleRowIds = [];

    jQuery(".kapEditChk")
        .prop("checked", false)
        .prop("disabled", true);

    for (var i = 0; i < rowIds.length; i++) {
        var rowId = String(rowIds[i]);

        var status = jQuery.trim(
            $grid.jqGrid("getCell", rowId, "selWrinStatus") || ""
        ).toUpperCase();

        var wrinKeyId = jQuery.trim(
            $grid.jqGrid("getCell", rowId, "hdnWrinKeyid") || ""
        );

        var isAccepted =
            status === "ACCEPTED" ||
            status === "A";

        if (isAccepted && wrinKeyId !== "") {
            eligibleRowIds.push(rowId);

            //jQuery("#chkKapEdit_" + rowId)
                //.prop("checked", false)
                //.prop("disabled", false);
        }
    }
    
    if (eligibleRowIds.length > 0) {

        var bottomRowId = eligibleRowIds[eligibleRowIds.length - 1];

        jQuery("#chkKapEdit_" + bottomRowId)
            .prop("disabled", false);
    }

    console.log("Eligible accepted rows:", eligibleRowIds);

    jQuery("#btnDeleteSelectedWorkflow")
        .data("eligibleRows", eligibleRowIds)
        .prop("disabled", eligibleRowIds.length === 0)
        .toggleClass(
            "ui-state-disabled",
            eligibleRowIds.length === 0
        );

    jQuery(document)
        .off("change.kapSelected", ".kapEditChk")
        .on("change.kapSelected", ".kapEditChk", function () {

            var currentRowId = String(jQuery(this).data("rowid"));
            var currentIndex = eligibleRowIds.indexOf(currentRowId);

            if (currentIndex == -1) {
                return;
            }

            if (jQuery(this).is(":checked")) {

                // Enable the Accepted row above
                if (currentIndex > 0) {

                    var previousRow = eligibleRowIds[currentIndex - 1];

                    jQuery("#chkKapEdit_" + previousRow)
                        .prop("disabled", false);
                }

            } else {

                // Disable every Accepted row above
                for (var i = currentIndex - 1; i >= 0; i--) {

                    jQuery("#chkKapEdit_" + eligibleRowIds[i])
                        .prop("checked", false)
                        .prop("disabled", true);
                }
            }

            var checkedCount = jQuery(".kapEditChk:checked").length;

            jQuery("#btnDeleteSelectedWorkflow")
                .prop("disabled", checkedCount === 0)
                .toggleClass("ui-state-disabled", checkedCount === 0);
        });
}

// added here on 06/08/2026

jQuery(document)
.off("click.kapWorkflowDelete", "#btnDeleteSelectedWorkflow")
.on("click.kapWorkflowDelete", "#btnDeleteSelectedWorkflow", function () {
    if (jQuery(this).prop("disabled")) {
        return;
    }

    var gridId = "kapWorkFlowGrid";
    var $grid = jQuery("#" + gridId);
    var eligibleRowIds = jQuery(this).data("eligibleRows") || [];
    var wrinKeyIds = [];

    for (var i = 0; i < eligibleRowIds.length; i++) {
        var rowId = String(eligibleRowIds[i]);

        if (jQuery("#chkKapEdit_" + rowId).is(":checked")) {
            var wrinKeyId = jQuery.trim(
                $grid.jqGrid("getCell", rowId, "hdnWrinKeyid") || ""
            );

            if (wrinKeyId !== "") {
                wrinKeyIds.push(wrinKeyId);
            }
        }
    }

    if (wrinKeyIds.length === 0) {
        alert("Select at least one accepted workflow row.");
        return;
    }

    var refId = jQuery.trim(
        jQuery("#hdnPopupRefId").val() ||
        jQuery("#hdnPopupKznKeyid").val() ||
        ""
    );

    if (refId === "") {
        alert("Kaizen reference ID is empty.");
        return;
    }

    if (!confirm("Are you sure you want to revoke the selected workflow approval?")) {
        return;
    }

    var requestData =
        "wrinKeyids=" + encodeURIComponent(wrinKeyIds.join(",")) +
        "&refId=" + encodeURIComponent(refId);

    processAjaxCalls(
        "KaizenApprovalWorkFlow_Delete.appm",
        requestData,
        "kapWfDeleteSuccess",
        "kapWfDeleteError"
    );
});

// end here

function kapWfDeleteSuccess(result){
	//alert(result.successData.msg);
	
	var message =result &&result.successData &&result.successData.msg? result.successData.msg: "Workflow deleted successfully";

    alert(message);

    jQuery("#btnDeleteSelectedWorkflow")
        .prop("disabled", true)
        .addClass("ui-state-disabled");
	jQuery('#kapWorkFlowGrid').trigger("reloadGrid");
	jQuery("#KaizenApprovalDeleteGrd").trigger("reloadGrid");
	
	closePopUpDialoge("DivKapWorkFlowPopup");
}



function kapWfDeleteError(){
	alert("Unable to delete this workflow row");
}
// end

//function kapPopupWorkFlowInfoError(){
	//alert("Unable to load workflow details for the selected Kaizen No");
//}

// added by priyanka 
//function updateWorkflowDeleteButton(rowIds) {

    //var allChecked =rowIds.length > 0;

    //for (var i = 0; i < rowIds.length; i++) {
	//var rowId = String(rowIds[i]);
	//if (!jQuery("#chkKapEdit_" + rowId).is(":checked")) {
            //allChecked = false;
            //break;
        //}
    //}

    //jQuery("#btnDeleteSelectedWorkflow")
        //.prop("disabled", !allChecked)
        //.toggleClass("ui-state-disabled",!allChecked);
//}
// end




</script>
<!-- commented and added  -->
<!-- <div id="divKapWorkFlowPopup" style="margin-top:5px;padding:10px;">
</div> -->
<!-- <table id="kapWorkFlowGrid" style="width:100%"><tr><td/></tr></table> -->
<div style="width:100%; padding:10px; box-sizing:border-box;">
<!-- added here by priyanka  -->
<div style="padding:8px 10px; font-weight:bold;">
    Kaizen No :
    <span id="lblPopupKaizenNo">
        ${requestScope.refId}
    </span>
</div>

<!-- <div style="width:100%; padding:10px; box-sizing:border-box;"> -->
    <table id="kapWorkFlowGrid"></table>
    <div id="kapWorkFlowPager"></div>
    <!-- added here -->
    <div style="margin-top:12px; text-align:center;">
        <input
            type="button"
            id="btnDeleteSelectedWorkflow"
            class="easyui-button"
            value="Delete Workflow"
            disabled="disabled"
            style="width:140px; height:28px;"
        />
    </div>
    <!-- end -->
</div>
<!-- end -->
<input type="hidden" id="hdnPopupKznKeyid" value="${requestScope.kznKeyid}"/>


<input type="hidden"
       id="hdnPopupEmpId"
       value="${requestScope.empId}"/>

<input type="hidden"
       id="hdnPopupRefId"
       value="${requestScope.kznKeyid}"/>

<input type="hidden"
       id="hdnPopupRefType"
       value="${requestScope.refType}"/>

<input type="hidden"
       id="hdnPopupTransCode"
       value="${requestScope.transCode}"/>

<input type="hidden"
       id="hdnPopupFlId"
       value="${requestScope.flId}"/>

<input type="hidden"
       id="hdnPopupRefRoleId"
       value="${requestScope.refRoleId}"/>

<input type="hidden"
       id="hdnPopupEnable"
       value="${requestScope.enable}"/>