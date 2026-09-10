<%-- BAL_SubAssembly.jsp --%>
<script type="text/javascript">
	var _sbamGridLoaded = false;
    jQuery(document).ready(function(){
        initialiseForm('frmSubAssembly');
        
        //parentSubmitForm = jQuery('#submitForm').val();
        //parentSubmitUrl  = getSubmitFormUrl();
        jQuery('#submitForm').val('frmSubAssembly');
        setSubmitFormUrl('subassembly_save.subasm');
        
         /* window.popup_OnDeleteForm = function () {
            var keyId = jQuery('#cmbSbamKeyid').combobox('getValue');
            if (!keyId || keyId.trim().length === 0) {
                alert('Select a Sub Assembly to delete');
                return false;
            }
            deleteRecord('frmSubAssembly', 'subassembly_delete.subasm');
        };  */
        
        if (!window.__subAssemblyOrigCaptured) {
            window.__subAssemblyOrigPopupDelete = window.popup_OnDeleteForm;
            window.__subAssemblyOrigCaptured = true;
        }

        window.popup_OnDeleteForm = function () {
            var keyId = jQuery('#cmbSbamKeyid').combobox('getValue');
            if (!keyId || keyId.trim().length === 0) {
                alert('Select a Sub Assembly to delete');
                return false;
            }
            var t = confirm("Are you sure you want to delete this record?");
            if (t) {
                saveForm("frmSubAssembly","subassembly_delete.subasm","");
            }
        };

        window.__restoreSubAssemblyContext = function () {
            if (typeof window.__subAssemblyOrigPopupDelete === 'function') {
                window.popup_OnDeleteForm = window.__subAssemblyOrigPopupDelete;
            }
            window.__subAssemblyOrigCaptured = false;
        };
        
        //fillComboBox("frmSubAssembly","cmbSbamKeyid","subassembly.commonFilter");
        fillComboBox("frmSubAssembly","cmbSbamKeyid","combo_subassmbly.Bbrdn");
        fillComboBox("frmSubAssembly","cmbSbamMachineid","machineCombo.commonFilter");
        fillComboBox("frmSubAssembly","cmbSbamAssemblyid","assembly.commonFilter");
        jQuery('#frmSubAssembly .easyui-text').css('text-transform', 'uppercase');
        jQuery('#frmSubAssembly .easyui-combobox').css('text-transform', 'uppercase');
        jQuery('#frmSubAssembly textarea').css('text-transform', 'uppercase');
        
     // Override delete button to show confirm first
        /* setTimeout(function(){
            jQuery('#btnDelete').unbind('click').bind('click', function(){
            	var keyId = jQuery('#cmbSbamKeyid').combobox('getValue');
                if (!keyId || keyId.trim().length === 0) {
                    alert('Select a Sub Assembly to delete');
                    return false;
                }
                var t = confirm("Are you sure you want to delete this record?");
                if(t){
                    saveForm("frmSubAssembly","subassembly_delete.subasm","");
                }
            });
        }, 500); */
    });

 //  Assembly combobox loaded - trigger grid (once only) 
    function frmSubAssemblycmbSbamAssemblyid_onLoadSuccess() {
    // Only pre-select assembly value — do NOT load grid here
    // Grid load is handled by frmSubAssemblycmbSbamMachineid_onLoadSuccess
    var assmId = jQuery("#hdnAssemblyId").val();
    if (assmId && assmId.trim().length > 0) {
        jQuery("#cmbSbamAssemblyid").combobox('setValue', assmId);
        var data = jQuery("#cmbSbamAssemblyid").combobox('getData');
        for (var i = 0; i < data.length; i++) {
            if (data[i].id == assmId) {
                jQuery("#cmbSbamAssemblyid").combobox('setText', data[i].text);
                break;
            }
        }
    }
    
}


 //  Assembly combobox select - reload grid filtered by assembly 
    /* function frmSubAssemblycmbSbamAssemblyid_onSelect(record) {
        console.log("Selected Assembly : " + record.id);
        jQuery("#cmbSbamAssemblyid").combobox('setValue', record.id);
    } */
    
    function frmSubAssemblycmbSbamAssemblyid_onSelect(record) {
        console.log("Selected Assembly : " + record.id);
        jQuery("#cmbSbamAssemblyid").combobox('setValue', record.id);

        if (jQuery.data(jQuery("#SbamListGrid")[0], 'jqGrid')) {
            jQuery("#SbamListGrid").jqGrid('GridDestroy');
            jQuery("#SbamListGrid").empty();
        }
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    }

    function viewMachineFilter() {
        if (jQuery.data(jQuery("#SbamListGrid")[0], 'jqGrid')) {
            jQuery("#SbamListGrid").jqGrid('GridDestroy');
            jQuery("#SbamListGrid").empty();
        }
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    }

    function clearMachineFilter() {
        jQuery("#cmbSbamMachineid").combobox('clear');
        jQuery("#cmbSbamAssemblyid").combobox('clear');
        jQuery("#cmbSbamKeyid").combobox('clear');
        jQuery("#txtSbamName").val('');
        jQuery("#txtSbamDescription").val('');
        jQuery("#txtSbamRemarks").val('');
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    }
 
 //  SubAssembly combobox loaded 
    function frmSubAssemblycmbSbamKeyid_onLoadSuccess() {
    }
 
 //  SubAssembly combobox select - recall record 
    function frmSubAssemblycmbSbamKeyid_onSelect(record) {
        processAjaxCalls("subassembly_recall.subasm", "keyId=" + record.id,
            "frmSubAssembly_successCallback", "frmSubAssembly_errorCallback");
    }
 

 //  Save success - close popup 
    function frmSubAssembly_successsCallback(result) {
        jQuery("#cmbSbamKeyid").combobox("clear");
        //reloadCombo("frmSubAssembly","cmbSbamKeyid","subassembly.commonFilter");
        //jQuery('#submitForm').val(parentSubmitForm);
    	//setSubmitFormUrl(parentSubmitUrl);
        reloadCombo("frmSubAssembly","cmbSbamKeyid","combo_subassmbly.Bbrdn");
        if (window.__restoreSubAssemblyContext) window.__restoreSubAssemblyContext();
        jQuery('#submitForm').val('frmBDMaster');
        setSubmitFormUrl('Breakdown_save.Bbrdn');
        closePopUpDialoge("divSubAsmblyLink");
    }

 //  Delete success - reload combo + grid 
     function frmSubAssembly_deleteSuccessCallback() {
        //reloadCombo("frmSubAssembly","cmbSbamKeyid","subassembly.commonFilter");
        reloadCombo("frmSubAssembly","cmbSbamKeyid","combo_subassmbly.Bbrdn");
        if (window.__restoreSubAssemblyContext) window.__restoreSubAssemblyContext();
        var t = confirm("Record is refered, can not delete! Do you want to make inactive?");
        if(t) {
            checkConfirmSubAssm();
        }
        closePopUpDialoge("divSubAsmblyLink");
    } 
    
    function checkConfirmSubAssm() {
        jQuery('#hdnInactive').val("Inactive");
        saveForm("frmSubAssembly","subassembly_delete.subasm","");
    }

 //  Recall success - populate fields 
    function frmSubAssembly_successCallback(result) {
	  
	 	console.log(result);
	 	
        jQuery("#cmbSbamKeyid").combobox('setValue', result.sbamdata.SbamKeyid);
        jQuery("#cmbSbamMachineid").combobox('setValue', result.machineId);        
        jQuery("#cmbSbamAssemblyid").combobox('setValue', result.sbamdata.SbamAssemblyid);
        jQuery("#txtSbamName").val(result.sbamdata.SbamName);
        jQuery("#txtSbamDescription").val(result.sbamdata.SbamDescription);
        jQuery("#txtSbamRemarks").val(result.sbamdata.SbamRemarks);
    }

    function frmSubAssembly_errorCallback(result) {
    }
    
    function confirmDeleteSubAssm() {
        var t = confirm("Are you sure you want to delete this record?");
        if (t) {
            saveForm("frmSubAssembly", "subassembly_delete.subasm", "");
        }
    }
    
 //  Double click grid row - recall record by keyid 
    /* function grdSbamList_doubleClick_CallBack(rowId) {
        var keyid = jQuery("#SbamListGrid").jqGrid('getCell', rowId, 'sbam_keyid');

        if (!keyid || keyid.trim().length === 0) {
            var rowData = jQuery("#SbamListGrid").jqGrid('getRowData', rowId);
            keyid = rowData['sbam_keyid'] || "";
        }

        if (!keyid || keyid.trim().length === 0) {
            console.log("No sbam_keyid found in row");
            return;
        }

        console.log("doubleClickSbamGrid keyid=[" + keyid + "]");

        processAjaxCalls(
            "subassembly_recall.subasm",
            "keyId=" + keyid,
            "frmSubAssembly_successCallback",
            "frmSubAssembly_errorCallback"
        );
    } */
    
    function grdSbamList_doubleClick_CallBack(rowId) {
        var url = 'subassembly_recall.subasm?keyid=' + rowId;
        processAjaxCalls(url, "", "frmSubAssembly_successCallback", "frmSubAssembly_errorCallback");
    }
 
 //  Clear assembly filter - reload grid unfiltered
    function clearAssemblyFilter() {
        jQuery("#cmbSbamAssemblyid").combobox('clear');
        jQuery("#cmbSbamKeyid").combobox('clear');
        jQuery("#txtSbamName").val('');
        jQuery("#txtSbamDescription").val('');
        jQuery("#txtSbamRemarks").val('');
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    }
 
 //  View button - destroy and reload grid 
    function viewAssemblyFilter() {
        if (jQuery.data(jQuery("#SbamListGrid")[0], 'jqGrid')) {
            jQuery("#SbamListGrid").jqGrid('GridDestroy');
            jQuery("#SbamListGrid").empty();
        }
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    }
 
 // DOUBLE CLICK - populate form from grid row
/*     function doubleClickSbamGrid(rowid) {
    console.log("=== doubleClickSbamGrid rowid=[" + rowid + "] ===");

    var keyid = jQuery("#SbamListGrid").jqGrid('getCell', rowid, 'sbam_keyid');

    if (!keyid || keyid.trim().length === 0) {
        var rowData = jQuery("#SbamListGrid").jqGrid('getRowData', rowid);
        keyid = rowData['sbam_keyid'] || "";
    }

    if (!keyid || keyid.trim().length === 0) {
        console.log("No sbam_keyid found");
        return;
    }

    console.log("doubleClickSbamGrid keyid=[" + keyid + "]");

    processAjaxCalls(
        "subassembly_recall.subasm",
        "keyId=" + keyid,
        "frmSubAssembly_successCallback",
        "frmSubAssembly_errorCallback"
    );
} */
 
    /* function doubleClickSbamGrid(rowid) {
        console.log("=== doubleClickSbamGrid rowid=[" + rowid + "] ===");
        
        var url = 'subassembly_recall.subasm?keyId=' + rowid;
        processAjaxCalls(url, "", "frmSubAssembly_successCallback", "frmSubAssembly_errorCallback");
    } */
    
    function doubleClickSbamGrid(rowid) {
        console.log("=== doubleClickSbamGrid rowid=[" + rowid + "] ===");

        // Get all row data and extract sbam_keyid from hidden field
        var rowData = jQuery("#SbamListGrid").jqGrid('getRowData', rowid);
        console.log("rowData = " + JSON.stringify(rowData));

        var keyid = rowData['sbam_keyid'] || rowData['SBAM_KEYID'] || "";

        if (!keyid || keyid.trim().length === 0) {
            console.log("No sbam_keyid found, using rowid directly");
            keyid = rowid;
        }

        console.log("doubleClickSbamGrid keyid=[" + keyid + "]");

        processAjaxCalls(
            "subassembly_recall.subasm",
            "keyId=" + keyid,
            "frmSubAssembly_successCallback",
            "frmSubAssembly_errorCallback"
        );
    }
    
 // Machine loaded → pre-populate from hidden field, then load assembly filtered
    function frmSubAssemblycmbSbamMachineid_onLoadSuccess() {
    	//if (_sbamGridLoaded) return;
    	
        var machId = jQuery("#hdnMachineId").val();
        if (machId && machId.trim().length > 0) {
            jQuery("#cmbSbamMachineid").combobox('setValue', machId);
            var data = jQuery("#cmbSbamMachineid").combobox('getData');
            for (var i = 0; i < data.length; i++) {
                if (data[i].id == machId) {
                    jQuery("#cmbSbamMachineid").combobox('setText', data[i].text);
                    break;
                }
            }
            /* // Reload assembly filtered by machine
            reloadCombo("frmSubAssembly", "cmbSbamAssemblyid",
                "assembly.commonFilter?machineId=" + machId); */
                if (!_sbamGridLoaded) {
            		reloadSbamAssemblyCombo(machId);
                }
        }
     else {
    	 if (!_sbamGridLoaded) {
        loadSubAssemblyGrid();
    	 }
    }
 }
 
    function reloadSbamAssemblyCombo(machineId) {
        var preSelectAssmId = jQuery("#hdnAssemblyId").val();

        jQuery("#cmbSbamAssemblyid").combobox({
            url: "assembly.commonFilter?machineId=" + machineId,
            valueField: 'id',
            textField: 'text',
            onLoadSuccess: function() {
                if (preSelectAssmId && preSelectAssmId.trim().length > 0) {
                    jQuery("#cmbSbamAssemblyid").combobox('setValue', preSelectAssmId);
                    var data = jQuery("#cmbSbamAssemblyid").combobox('getData');
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == preSelectAssmId) {
                            jQuery("#cmbSbamAssemblyid").combobox('setText', data[i].text);
                            break;
                        }
                    }
                }
                loadSubAssemblyGrid();
            }
        });
    }
 
 // Machine selected → reload assembly filtered, clear subassembly grid
    function frmSubAssemblycmbSbamMachineid_onSelect(record) {
        console.log("Selected Machine : " + record.id);
        jQuery("#cmbSbamAssemblyid").combobox('clear');
        _sbamGridLoaded = false;

        // Reload assembly combo filtered by selected machine
        reloadCombo("frmSubAssembly", "cmbSbamAssemblyid",
            "assembly.commonFilter?machineId=" + record.id);

        loadSubAssemblyGrid();
    }

    /* function clearAssemblyFilter() {
        jQuery("#cmbSbamAssemblyid").combobox('clear');

        jQuery("#cmbSbamKeyid").combobox('clear');
        jQuery("#txtSbamName").val('');
        jQuery("#txtSbamDescription").val('');
        jQuery("#txtSbamRemarks").val('');
        _sbamGridLoaded = false;
        loadSubAssemblyGrid();
    } */
 
 //  GRID LOAD 
    function loadSubAssemblyGrid() {
        if (_sbamGridLoaded) return;
        _sbamGridLoaded = true;

        var assemblyId = "";
        var machineId = "";
        try {
            assemblyId = jQuery("#cmbSbamAssemblyid").combobox('getValue');
            machineId  = jQuery("#cmbSbamMachineid").combobox('getValue');
        } catch(e) {
            assemblyId = jQuery("#hdnAssemblyId").val() || "";
        }

        var params = "&q=1";
        if (assemblyId && assemblyId.trim().length > 0) {
            params += "&assemblyId=" + assemblyId;
        }
        
        if (machineId && machineId.trim().length > 0) {
            params += "&machineId=" + machineId;
        }

        processGridnew(
            "subassembly_input.subasm",
            params,
            "SbamListGrid",
            "SbamListPager",
            "",
            "doubleClickSbamGrid",
            "",
            "applySbamGridScroll"
        );

        setTimeout(function () {
            jQuery("#SbamListGrid").jqGrid('setGridParam', {height: 350});
            jQuery("#SbamListGrid").closest(".ui-jqgrid-view")
                .find(".ui-jqgrid-bdiv")
                .css({
                    "height"    : "350px",
                    "overflow-y": "auto",
                    "overflow-x": "hidden"
                });
            jQuery("#SbamListGrid").setGridWidth(
                jQuery("#SbamListGrid").closest("div").width() - 10
            );
        }, 100);
    }
</script>


<form name="frmSubAssembly" id="frmSubAssembly">
<div id="wrapper">
    <table width="100%">
    
    <tr>
    <td colspan="2" style="padding: 8px 12px 4px 12px;">
        <label class="mandatory-lbl">Equipment</label>&nbsp;
        <input id="cmbSbamMachineid"
               name="cmbSbamMachineid"
               type="text"
               class="easyui-combobox"
               style="width: 300px;"/>
    	</td>
	</tr>

        <%-- Assembly filter row (mirrors Equipment filter row in Assembly JSP) --%>
        <tr>
            <td colspan="2" style="padding: 8px 12px 4px 12px;">
                <label class="mandatory-lbl">Assembly</label>&nbsp;
                <input id="cmbSbamAssemblyid"
                       name="cmbSbamAssemblyid"
                       type="text"
                       class="easyui-combobox"
                       style="width: 300px;"/>
                <input type="button" class="easyui-button" value="view"
                       style="height:22px; width:40px;"
                       onclick="viewAssemblyFilter();"/>
                <input type="button" class="easyui-button" value="clear"
                       style="height:22px; width:40px;"
                       onclick="clearAssemblyFilter();"/>
            </td>
        </tr>

        <tr>
            <%-- LEFT: Sub Assembly List Grid --%>
            <td width="55%" valign="top">
                <div style="padding: 10px 12px; height: 400px;">
                    <table id="SbamListGrid"></table>
                    <div id="SbamListPager" style="margin-top:4px;"></div>
                </div>
            </td>

            <%-- RIGHT: Sub Assembly Form Fields --%>
            <td width="45%" valign="top">
                <div class="easyui-paddingbfpx" style="padding-top:10px; padding-left:20px;">

                    <input type="hidden" id="hdnInactive" name="hdnInactive"/>

                    <div><label>Sub Assembly</label></div>
                    <div class="easyui-paddingbfpx">
                        <input id="cmbSbamKeyid" name="cmbSbamKeyid" type="text"
                               class="easyui-combobox" style="width:300px;"
                               value="${requestScope.genTlSubAssemblymst.sbamKeyid}"/>
                    </div>

                    <div class="easyui-paddingbfpx">
                        <label class="mandatory-lbl">Name</label>
                    </div>
                    <div class="easyui-paddingbfpx">
                        <input id="txtSbamName" name="txtSbamName" type="text"
                               class="easyui-text" maxlength="49" style="width:300px;"
                               value="${requestScope.genTlSubAssemblymst.sbamName}"/>
                    </div>

                    <div class="easyui-paddingbfpx"><label>Description</label></div>
                    <div class="easyui-paddingbfpx">
                        <textarea id="txtSbamDescription" name="txtSbamDescription"
                                  cols="34" rows="5" maxlength="95"
                        >${requestScope.genTlSubAssemblymst.sbamDescription}</textarea>
                    </div>

                    <div class="easyui-paddingbfpx"><label>Remarks</label></div>
                    <div class="easyui-paddingbfpx">
                        <textarea id="txtSbamRemarks" name="txtSbamRemarks"
                                  cols="34" rows="5" maxlength="95"
                        >${requestScope.genTlSubAssemblymst.sbamRemarks}</textarea>
                    </div>

                </div>
            </td>
        </tr>

    </table>
</div>

<input id="hdnAssemblyId" name="hdnAssemblyId"
       value="${requestScope.genTlSubAssemblymstBean.assemblyId}" type="hidden"/>
<input id="mode" name="mode"
       value="${requestScope.genTlSubAssemblymstBean.formMode}" type="hidden"/>
<input id="hdnMachineId" name="hdnMachineId"
       value="${requestScope.genTlSubAssemblymstBean.machineId}" type="hidden"/>       
</form>