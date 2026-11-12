<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    // Read parameters safely
    String capaId = request.getParameter("CapaId");
    if(capaId == null) capaId = (String)request.getAttribute("CapaId");

    String popup = request.getParameter("popup");
    String mode  = request.getParameter("mode");
    if(mode == null) mode = (String)request.getAttribute("mode");
    if(mode == null) mode = "VIEW";

    // ✅ Force VIEW mode in popup always
    if("Y".equalsIgnoreCase(popup)) {
        mode = "VIEW";
    }

    Object loginUserObj  = request.getAttribute("LoginUser");
    Object statusObj     = request.getAttribute("Status");
    Object targetFlidObj = request.getAttribute("TargetFlid");

    String loginUser  = (loginUserObj  == null) ? "" : String.valueOf(loginUserObj);
    String status     = (statusObj     == null) ? "" : String.valueOf(statusObj);
    String targetFlid = (targetFlidObj == null) ? "" : String.valueOf(targetFlidObj);
%>

<!-- ✅ Keep the form id as frmCAPA (many utilities expect it) -->
<form id="frmCAPA" name="frmCAPA">
    <input type="hidden" id="hdnCapaId"   name="CapaId"     value="<%= (capaId==null) ? "" : capaId %>" />
    <input type="hidden" id="hdnMode"     name="mode"       value="<%= mode %>" />
    <input type="hidden" id="hdnPopup"    name="popup"      value="<%= (popup==null) ? "" : popup %>" />
    <input type="hidden" id="hdnLoginUser" name="LoginUser" value="<%= loginUser %>" />
    <input type="hidden" id="hdnStatus"    name="Status"    value="<%= status %>" />
    <input type="hidden" id="hdnTargetFlid" name="TargetFlid" value="<%= targetFlid %>" />
</form>

<div style="padding:6px;">
    <!-- ✅ GRID ONLY -->
    <table id="CAPAGrid"></table>
    <div id="CAPApager"></div>
</div>

<script type="text/javascript">
/**
 * ✅ Popup version: loads ONLY the CAPA Action grid (no CAPA header/form, no combo initialization).
 * It assumes common JS (jQuery, jqGrid, processGridnew) is already available on the parent page.
 */
jQuery(function () {
    var capaId = jQuery("#hdnCapaId").val();
    if (!capaId) { return; }

    // This is the same base action CAPA.jsp uses for the action grid.
    // Your framework will call CustomerComplaint_getCol.ncapa and CustomerComplaint_getData.ncapa internally.
    var cond = "q=2&CapaId=" + encodeURIComponent(capaId) + "&popup=Y&mode=VIEW";

    if (typeof processGridnew === "function") {
        processGridnew("CustomerComplaint_input.ncapa", cond, "CAPAGrid", "CAPApager", "", "", "", "CAPAloadComFunction_Popup");
    } else {
        // fallback (still avoids crashing the popup)
        console.log("processGridnew() not found in popup context");
    }
});

function CAPAloadComFunction_Popup() {
    var $grid = jQuery("#CAPAGrid");

    // 1) Disable editing in any way
    try { $grid.jqGrid("setGridParam", { cellEdit: false, onSelectRow: function(){ return false; } }); } catch (e) {}

    // 2) Hide common action/edit columns safely
    try {
        var cm = $grid.jqGrid("getGridParam", "colModel") || [];
        for (var i = 0; i < cm.length; i++) {
            var name = (cm[i].name || "");
            var low  = name.toLowerCase();

            var isActionsFormatter = (cm[i].formatter && ("" + cm[i].formatter).toLowerCase() === "actions");
            var looksLikeActionCol = (low === "act" || low.indexOf("edit") >= 0 || low.indexOf("action") >= 0);

            if (isActionsFormatter || looksLikeActionCol) {
                try { $grid.jqGrid("hideCol", name); } catch (e1) {}
            }
        }
    } catch (e2) {}

    // 3) Remove/disable multiselect checkboxes (if the grid was built with multiselect)
    try {
        jQuery("#cb_CAPAGrid").hide(); // header checkbox
        jQuery("input[id^='jqg_CAPAGrid_']").prop("checked", false).prop("disabled", true);
    } catch (e3) {}

    // 4) Hide pager buttons (add/edit/del) if your framework added them
    try {
        jQuery("#CAPApager").find(".ui-pg-button").hide();
    } catch (e4) {}
}
</script>
