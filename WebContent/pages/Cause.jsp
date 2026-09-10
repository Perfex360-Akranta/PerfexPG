<script type="text/javascript">

jQuery(document).ready(function () {

    initialiseForm('frmCause');
    jQuery('#submitForm').val('frmCause');
    
    if (!window.__causeOrigCaptured) {
        window.__causeOrigPopupDelete = window.popup_OnDeleteForm;
        window.__causeOrigCaptured = true;
    }

    fillComboBox("frmCause", "cmbbphmKeyid", "phenomena.commonFilter");
    fillComboBox("frmCause", "cmbbcsmKeyid", "cause.commonFilter");

    jQuery('#frmCause .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmCause .easyui-combobox').css('text-transform', 'uppercase');
    jQuery('#frmCause textarea').css('text-transform', 'uppercase');
    
    window.popup_OnDeleteForm = function () {
        var causeId = jQuery('#cmbbcsmKeyid').combobox('getValue');
        if (!causeId || causeId.trim().length === 0) {
            alert('Select a Cause to delete');
            return false;
        }
        processAjaxCalls(
            'Cause_delete.pcl',
            'cmbbcsmKeyid=' + causeId,
            'frmCause_deleteSuccessCallback',
            'frmCause_deleteErrorCallback'
        );
    };
    
    window.__restoreCauseContext = function () {
        if (typeof window.__causeOrigPopupDelete === 'function') {
            window.popup_OnDeleteForm = window.__causeOrigPopupDelete;
        }
        window.__causeOrigCaptured = false;
    };
});

/* function popup_OnSaveForm() {
    saveForm('frmCause', 'Cause_save.pcl');
} */

function frmCause_successsCallback(result) {
	jQuery("#cmbbcsmKeyid").combobox("clear");
	reloadCombo("frmCause","cmbbcsmKeyid","cause.commonFilter");
	if (window.__restoreCauseContext) window.__restoreCauseContext();
	jQuery('#submitForm').val('frmBDMaster');
	setSubmitFormUrl('Breakdown_save.Bbrdn');
	closePopUpDialoge("divCauseLink");
}

function frmCause_errorCallback(result) {
    console.log("Cause save error", result);
}

/* ================= GRID LOAD ================= */

var _causeGridLoaded = false;

function loadCauseGrid() {

    if (_causeGridLoaded) return;
    _causeGridLoaded = true;

    //var phenId = "";
    phenId = jQuery("#cmbbphmKeyid").combobox('getValue');

    try {
        phenId = jQuery("#cmbbphmKeyid").combobox('getValue');
    } catch (e) {
        phenId = "";
    }

    var params = "&q=1";

    if (phenId && phenId.trim().length > 0) {
        params += "&phenId=" + phenId;
    }

    processGridnew(
        "cause_input.pcl",
        params,
        "CauseListGrid",
        "CauseListPager",
        "",
        "doubleClickCauseGrid",
        "",
        "applyCauseGridScroll"
    );

    setTimeout(function () {

        jQuery("#CauseListGrid").jqGrid('setGridParam', { height: 300 });

        jQuery("#CauseListGrid").closest(".ui-jqgrid-view")
            .find(".ui-jqgrid-bdiv")
            .css({
                "height": "300px",
                "overflow-y": "auto",
                "overflow-x": "hidden"
            });

        jQuery("#CauseListGrid").setGridWidth(
            jQuery("#CauseListGrid").closest("div").width() - 10
        );

    }, 100);
}

function frmCausecmbbphmKeyid_onSelect(record) {

    if (jQuery.data(jQuery("#CauseListGrid")[0], 'jqGrid')) {
        jQuery("#CauseListGrid").jqGrid('GridDestroy');
        jQuery("#CauseListGrid").empty();
    }

    _causeGridLoaded = false;
    loadCauseGrid();
}

function frmCausecmbbphmKeyid_onLoadSuccess() {
	var phenId = '${requestScope.phenId}';
    if (phenId && phenId !== '' && phenId !== '{}') {
        jQuery('#cmbbphmKeyid').combobox('setValue', phenId);
    }
    loadCauseGrid();
}

/* ================= DOUBLE CLICK GRID ================= */

function doubleClickCauseGrid(rowid) {

    var keyid = jQuery("#CauseListGrid").jqGrid('getCell', rowid, 'bcsm_keyid');

    if (!keyid || keyid.trim().length === 0) {
        var rowData = jQuery("#CauseListGrid").jqGrid('getRowData', rowid);
        keyid = rowData['bcsm_keyid'] || "";
    }

    if (!keyid || keyid.trim().length === 0) {
        console.log("No cause keyid found");
        return;
    }

    processAjaxCalls(
        "cause_recall.pcl",
        "keyId=" + keyid,
        "frmCause_recallSuccessCallback",
        "frmCause_errorCallback"
    );
}

function frmCause_recallSuccessCallback(result) {
	jQuery("#cmbbcsmKeyid").combobox('setValue', result.causeData.BcsmKeyid);
    jQuery("#txtCauseName").val(result.causeData.BcsmName);
    jQuery("#txtCauseRemarks").val(result.causeData.BcsmRemarks);
}

function clearCauseForm() {

    jQuery("#cmbbcsmKeyid").combobox('clear');
    jQuery("#txtCauseName").val('');
    jQuery("#txtCauseRemarks").val('');
}

function clearCausePhenFilter() {
    jQuery("#cmbbphmKeyid").combobox('clear');   // clear the Phenomena filter
    clearCauseForm();                             // keep clearing the right-side form too
    refreshCauseGrid();                           // reload grid with phenId empty -> shows all
}

function refreshCauseGrid() {

    if (jQuery.data(jQuery("#CauseListGrid")[0], 'jqGrid')) {
        jQuery("#CauseListGrid").jqGrid('GridDestroy');
        jQuery("#CauseListGrid").empty();
    }

    _causeGridLoaded = false;
    loadCauseGrid();
}

function frmCause_deleteSuccessCallback(result) {

    if (result.successData && result.successData.msg) {
        alert(result.successData.msg);
    }
    if (window.__restoreCauseContext) window.__restoreCauseContext();
    clearCauseForm();
    refreshCauseGrid();
}
function divCauseLink_onClose(){
    if (window.__restoreCauseContext) window.__restoreCauseContext();
    return true;
}

function frmCause_deleteErrorCallback(result) {

    if (result.tpmException) {
        alert(result.tpmException);
    } else {
        alert("Delete failed");
    }
}

</script>

<form name="frmCause" id="frmCause">

    <div id="wrapper">

        <table width="100%">
            <tr>

                <!-- LEFT SIDE : PHENOMENA + GRID -->
                <td width="55%" valign="top">

                    <div style="padding:10px 12px;">

                        <div class="easyui-paddingbfpx">
                            <label class="mandatory-lbl">Phenomena</label>
                        </div>

                        <div class="easyui-paddingbfpx">
                            <input id="cmbbphmKeyid"
                                   name="cmbbphmKeyid"
                                   type="text"
                                   class="easyui-combobox"
                                   style="width:300px;"
                                   value="${requestScope.phenId}" />

                            <input type="button"
                                   class="easyui-button"
                                   value="view"
                                   style="height:22px; width:40px;"
                                   onclick="refreshCauseGrid();" />
                            <input type="button"
                                   class="easyui-button"
                                   value="Clear"
                                   style="height:22px; width:40px;"
                                   onclick="clearCausePhenFilter();" />

                        </div>

                        <div style="padding-top:12px; height:350px;">
                            <table id="CauseListGrid"></table>
                            <div id="CauseListPager" style="margin-top:4px;"></div>
                        </div>

                    </div>

                </td>

                <!-- RIGHT SIDE : CAUSE FORM -->
                <td width="45%" valign="top">

                    <div class="easyui-paddingbfpx"
                         style="padding-top:10px; padding-left:20px;">

                        <input type="hidden" id="hdnInactive" name="hdnInactive" />

                        <div class="easyui-paddingbfpx">
                            <label>Cause</label>
                        </div>

                        <div class="easyui-paddingbfpx">
                            <input id="cmbbcsmKeyid"
                                   name="cmbbcsmKeyid"
                                   type="text"
                                   class="easyui-combobox"
                                   style="width:300px;"
                                   value="${requestScope.phenCauseBean.bcsmKeyid}" />
                        </div>

                        <div class="easyui-paddingbfpx">
                            <label class="mandatory-lbl">Name</label>
                        </div>

                        <div class="easyui-paddingbfpx">
                            <input id="txtCauseName"
                                   name="txtCauseName"
                                   type="text"
                                   class="easyui-text"
                                   maxlength="49"
                                   style="width:300px;"
                                   value="${requestScope.phenCauseBean.bcsmName}" />
                        </div>

                        <div class="easyui-paddingbfpx">
                            <label>Remarks</label>
                        </div>

                        <div class="easyui-paddingbfpx">
                            <textarea id="txtCauseRemarks"
                                      name="txtCauseRemarks"
                                      cols="34"
                                      rows="5"
                                      maxlength="95">${requestScope.phenCauseBean.bcsmRemarks}</textarea>
                        </div>

                    </div>

                </td>

            </tr>
        </table>

    </div>


<input id="mode"
       name="mode"
       value="${requestScope.phenCauseBean.formMode}"
       type="hidden" />

</form>