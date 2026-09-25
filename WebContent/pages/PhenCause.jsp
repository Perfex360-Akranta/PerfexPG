<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>

<script type="text/javascript">
    jQuery(document).ready(function(){
    	
        initialiseForm('frmPhenCause');
        var parentSubmitForm = jQuery('#submitForm').val();
        var parentSubmitUrl  = getSubmitFormUrl();
        jQuery('#submitForm').val('frmPhenCause');
        setSubmitFormUrl('PhenCause_save.pcl');
        
        if (!window.__phenCauseOrigCaptured) {
            window.__phenCauseOrigPopupSave = window.popup_OnSaveForm;
            window.__phenCauseOrigCaptured = true;
        }

        window.popup_OnSaveForm = function () {
            if (frmPhenCause_beforeSubmit() === false) return false;
            saveForm('frmPhenCause', 'PhenCause_save.pcl');
        };
        
        window.popup_OnDeleteForm = function () {
            var phenId = jQuery('#cmbbphmKeyid').combobox('getValue');
            if (!phenId || phenId.trim().length === 0) {
                alert('Select a Phenomena to delete');
                return false;
            }
            processAjaxCalls(
                'PhenCause_delete.pcl',
                'cmbbphmKeyid=' + phenId, 
                'frmPhenCause_deleteSuccessCallback',
                'frmPhenCause_errorCallback'
            );
            
        };

        /* window.__restorePhenCauseContext = function () {
            jQuery('#submitForm').val(parentSubmitForm);
            setSubmitFormUrl(parentSubmitUrl);
            window.popup_OnSaveForm = window.__phenCauseOrigPopupSave;
            window.popup_OnDeleteForm = window.__phenCauseOrigPopupDelete;
            window.__phenCauseOrigCaptured = false;
        }; */
        
        window.__restorePhenCauseContext = function () {
            jQuery('#submitForm').val(parentSubmitForm);
            setSubmitFormUrl(parentSubmitUrl);
            window.popup_OnSaveForm   = window.__phenCauseOrigPopupSave;
            if (typeof window.__phenCauseOrigPopupDelete === 'function') {
                window.popup_OnDeleteForm = window.__phenCauseOrigPopupDelete;
            }
            window.__phenCauseOrigCaptured = false;
        };
       // jQuery('#frmPhenCause').attr('action', 'PhenCause_save.pcl');
        
     // Store parent submit form only once
        //if (!jQuery('#hdnReturnsubmitUrl').val()) {
          //  jQuery('#hdnReturnsubmitUrl').val(jQuery('#submitForm').val());
        //}
		 var parentSubmitForm = jQuery('#submitForm').val();
    	 var parentSubmitUrl = getSubmitFormUrl();

    jQuery('#submitForm').val('frmPhenCause');
    setSubmitFormUrl('PhenCause_save.pcl');
        // Only this popup should submit frmPhenCause
        //jQuery('#submitForm').val('frmBDMaster');

//        jQuery('#subformPopUpId')
  //          .off('dialogclose.phenCauseFix')
    //        .on('dialogclose.phenCauseFix', function(){
      //          jQuery('#submitForm').val(jQuery('#hdnReturnsubmitUrl').val());
        //    });
        
        fillComboBox("frmPhenCause", "cmbbphmAssemblyid", "combo_Assembly.pcl");
        fillComboBox("frmPhenCause", "cmbbphmKeyid", "combo_phenomena.Bbrdn");
        
        // added by priyanka 09/07/2026
        jQuery("#cmbbphmKeyid").combobox({
            onRequest: function(){
                var assmId = jQuery('#cmbbphmAssemblyid').combobox('getValue');
                var mchId = '';
                try {
                    mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
                } catch(e) {
                    mchId = '';
                }
                var ds = "";
                if (assmId && assmId.trim().length > 0) {
                    ds += "assmId=" + assmId;
                }
                if (mchId && mchId.trim().length > 0) {
                    ds += (ds ? "&" : "") + "mchId=" + mchId;
                }
                return ds;
            }
        }); 
        // end 

        /* jQuery('input:checkbox[name=chkExiPhen]').attr('checked', true);
        readOnlyFields('txtbphmPhenomenaname');
        readOnlyFields('txtbphmRemarks');
        jQuery('#txtbphmPhenomenaname').css('background-color', '#D1E2FD');
        jQuery('#txtbphmRemarks').css('background-color', '#D1E2FD'); */

        jQuery('#frmPhenCause .easyui-text').css('text-transform', 'uppercase');
        jQuery('#frmPhenCause textarea').css('text-transform', 'uppercase');
    });

    /* jQuery('#chkExiPhen').click(function(){
        jQuery('input:checkbox[name=chkExiPhen]').attr('checked', true);
        jQuery('input:checkbox[name=chkNewPhen]').attr('checked', false);
        jQuery('#cmbbphmKeyid').combobox('enable');
        readOnlyFields('txtbphmPhenomenaname');
        readOnlyFields('txtbphmRemarks');
        jQuery('#txtbphmPhenomenaname').css('background-color', '#D1E2FD').val('');
        jQuery('#txtbphmRemarks').css('background-color', '#D1E2FD').val('');
    }); */

    /* jQuery('#chkNewPhen').click(function(){
        jQuery('input:checkbox[name=chkNewPhen]').attr('checked', true);
        jQuery('input:checkbox[name=chkExiPhen]').attr('checked', false);
        jQuery('#cmbbphmKeyid').combobox('clear');
        jQuery('#cmbbphmKeyid').combobox('disable');
        jQuery('#txtbphmPhenomenaname').removeAttr('readonly').css('background-color', '#fff').val('');
        jQuery('#txtbphmRemarks').removeAttr('readonly').css('background-color', '#fff').val('');
    }); */

    function frmPhenCausecmbbphmAssemblyid_onLoadSuccess() {
        var assmId = '${requestScope.assmId}';
        var mchId = '';

        try {
            mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
        } catch(e) {
            mchId = '';
        }

        if (assmId && assmId !== '' && assmId !== '{}') {
            jQuery('#cmbbphmAssemblyid').combobox('setValue', assmId);
            jQuery('#hdnAssemblyId').val(assmId);
        }

        loadPhenomenaCombo();
        loadPhenomenaGrid();
    }

    function frmPhenCausecmbbphmAssemblyid_onSelect(record) {
        jQuery('#hdnAssemblyId').val(record.id);
        clearPhenomenaForm();
        loadPhenomenaCombo();
    }

    function loadPhenomenaCombo() {
        var assmId = jQuery('#cmbbphmAssemblyid').combobox('getValue');
        var mchId = '';
        try {
            mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
        } catch(e) {
            mchId = '';
        }

        var ds = '?q=';
        if (assmId && assmId.trim().length > 0) {
            ds += '&assmId=' + assmId;
        }
        if (mchId && mchId.trim().length > 0) {
            ds += '&mchId=' + mchId;
        }

        jQuery('#cmbbphmKeyid').combobox('clear');
        reloadCombo('frmPhenCause', 'cmbbphmKeyid', 'combo_phenomena.Bbrdn' + ds);
    }

    function frmPhenCausecmbbphmKeyid_onSelect(record) {
        if (!record || !record.id) return;
        processAjaxCalls(
            'phenomena_recall.pcl',
            'keyId=' + record.id,
            'phenomenaRecallSuccess',
            'frmPhenCause_errorCallback'
        );
    }

    function phenomenaRecallSuccess(result) {
        jQuery('#cmbbphmKeyid').combobox('setValue', result.phenData.BphmKeyid);
        jQuery('#cmbbphmKeyid').combobox('setText', result.phenData.BphmPhenomenaname);
        jQuery('#txtbphmPhenomenaname').val(result.phenData.BphmPhenomenaname);
        jQuery('#txtbphmRemarks').val(result.phenData.BphmRemarks);
    }

    var _phenGridLoaded = false;

    function loadPhenomenaGrid() {
        if (_phenGridLoaded) return;
        _phenGridLoaded = true;

        var assmId = jQuery('#cmbbphmAssemblyid').combobox('getValue');
        var mchId = '';
        try {
            mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
        } catch(e) {
            mchId = '';
        }

        var params = '&q=1';
        if (assmId && assmId.trim().length > 0) {
            params += '&assmId=' + assmId;
        }
        if (mchId && mchId.trim().length > 0) {
            params += '&mchId=' + mchId;
        }

        processGridnew(
            'phenomena_input.pcl',
            params,
            'PhenListGrid',
            'PhenListPager',
            '',
            'doubleClickPhenGrid',
            '',
            'applyPhenGridScroll'
        );

        setTimeout(function(){
            jQuery('#PhenListGrid').jqGrid('setGridParam', {height: 350});
            jQuery('#PhenListGrid').closest('.ui-jqgrid-view')
                .find('.ui-jqgrid-bdiv')
                .css({
                    'height': '350px',
                    'overflow-y': 'auto',
                    'overflow-x': 'hidden'
                });
            jQuery('#PhenListGrid').setGridWidth(
                jQuery('#PhenListGrid').closest('div').width() - 10
            );
        }, 50);
    }

    function doubleClickPhenGrid(rowid) {
        var keyid = jQuery('#PhenListGrid').jqGrid('getCell', rowid, 'bphm_keyid');

        if (!keyid || keyid.trim().length === 0) {
            keyid = jQuery('#PhenListGrid').jqGrid('getCell', rowid, 'BPHM_KEYID');
        }
        if (!keyid || keyid.trim().length === 0) {
            keyid = jQuery('#PhenListGrid').jqGrid('getCell', rowid, 'pct_orginalid');
        }
        if (!keyid || keyid.trim().length === 0) {
            var rowData = jQuery('#PhenListGrid').jqGrid('getRowData', rowid);
            keyid = rowData['bphm_keyid'] || rowData['BPHM_KEYID'] || rowData['pct_orginalid'] || rowData['PCT_ORGINALID'] || rowid;
        }

        if (!keyid || keyid.trim().length === 0) return;

        jQuery('#cmbbphmKeyid').combobox('setValue', keyid);
        processAjaxCalls(
            'phenomena_recall.pcl',
            'keyId=' + keyid,
            'phenomenaRecallSuccess',
            'frmPhenCause_errorCallback'
        );
    }

    function viewPhenAssemblyFilter() {
        if (jQuery.data(jQuery('#PhenListGrid')[0], 'jqGrid')) {
            jQuery('#PhenListGrid').jqGrid('GridDestroy');
            jQuery('#PhenListGrid').empty();
        }
        _phenGridLoaded = false;
        loadPhenomenaCombo();
        loadPhenomenaGrid();
    }

    function clearPhenAssemblyFilter() {
        jQuery('#cmbbphmAssemblyid').combobox('clear');
        jQuery('#hdnAssemblyId').val('');
        clearPhenomenaForm();
        viewPhenAssemblyFilter();
    }

    function clearPhenomenaForm() {
        jQuery('#cmbbphmKeyid').combobox('clear');
        jQuery('#txtbphmPhenomenaname').val('');
        jQuery('#txtbphmRemarks').val('');
    }

    // function frmPhenCause_successsCallback(result) {
    	//var phenId = result.successData.keyId;
        //var returnString = 'phenId=' + jQuery('#cmbbphmKeyid').combobox('getValue');
        //jQuery('#hdnReturnVal').val(returnString);

        //var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
        //var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');

        //jQuery('#cmbbdmsFinalphenomena').combobox('clear');
        
         //if (assmId != null && assmId !== '' && assmId !== ' ') {
           // reloadCombo('frmBDMaster', 'cmbbdmsFinalphenomena', 'combo_phenomena.brdn?q=&assmId=' + assmId + '&mchId=' + mchId);
        //} else {
          //  reloadCombo('frmBDMaster', 'cmbbdmsFinalphenomena', 'combo_phenomena.brdn?q=&mchId=' + mchId);
        //} 

        //reloadCombo('frmBDMaster', 'cmbbdmsFinalphenomena', comboUrl, function () {
            // set the value only after the combo has finished repopulating
          //  jQuery('#cmbbdmsFinalphenomena').combobox('setValue', phenId);
        //});

        //jQuery('#submitForm').val(jQuery('#hdnReturnsubmitUrl').val());
        //jQuery('#subformPopUpId').dialog('close');
        //closePopUpDialoge('divPhenLink');
    //} 
    
    /* function frmPhenCause_successsCallback(result) {

        var phenId = '';
        if (result && result.successData && result.successData.keyId) {
            phenId = result.successData.keyId;
        } else {
            phenId = jQuery('#cmbbphmKeyid').combobox('getValue');
        }

        var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
        var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');

        var comboUrl = 'combo_phenomena.brdn?q=';
        if (assmId != null && assmId !== '' && assmId !== ' ') {
            comboUrl += '&assmId=' + assmId;
        }
        if (mchId != null && mchId !== '' && mchId !== ' ') {
            comboUrl += '&mchId=' + mchId;
        }

        jQuery('#hdnReturnVal').val('phenId=' + phenId);

        jQuery('#cmbbdmsFinalphenomena').combobox('clear');

        //reloadCombo('frmBDMaster', 'cmbbdmsFinalphenomena', comboUrl);

        setTimeout(function () {
            jQuery('#cmbbdmsFinalphenomena').combobox('setValue', phenId);
        }, 500);

        jQuery('#submitForm').val(jQuery('#hdnReturnsubmitUrl').val());
        jQuery('#subformPopUpId').dialog('close');
        closePopUpDialoge('divPhenLink');
    } */
    
    function frmPhenCause_successsCallback(result) {
    	if (window.__restorePhenCauseContext) window.__restorePhenCauseContext();
        closePopUpDialoge("divPhenLink");
    }

    function frmPhenCause_deleteSuccessCallback(result) {
        alert(result.successData.msg);
        reloadCombo('frmPhenCause', 'cmbbphmKeyid', 'combo_phenomena.Bbrdn');
        closePopUpDialoge('divPhenLink');
        jQuery('#subformPopUpId').dialog('close');
    }

    /* function frmPhenCause_beforeSubmit() {
    	
    	jQuery('#phenId').val(jQuery('#cmbbphmKeyid').combobox('getValue'));
    	jQuery('#phenName').val(jQuery('#txtbphmPhenomenaname').val());
    	jQuery('#remarks').val(jQuery('#txtbphmRemarks').val());
    	var phenId = jQuery('#cmbbphmKeyid').combobox('getValue');
        var phenName = jQuery('#txtbphmPhenomenaname').val();
        var remarks = jQuery('#txtbphmRemarks').val();
        //if (jQuery('#chkExiPhen').is(':checked') === true) {
            if (jQuery('#cmbbphmKeyid').combobox('getValue') === '') {
                alert('Select Phenomena');
                return 0;
            }
        //} 
        //else {
            if (jQuery('#txtbphmPhenomenaname').val() === '') {
                alert('Type Phenomena Name');
                return 0;
            }
            jQuery('#phenId').val(phenId);
            jQuery('#phenName').val(phenName);
            jQuery('#remarks').val(remarks);
            jQuery('#assmId').val(assmId);
            jQuery('#hdnAssemblyId').val(assmId);
            
            return true;
       // }
    } */
    
    /*  function popup_OnSaveForm(){
			
			saveForm('frmPhenCause','PhenCause_save.pcl');
			    
		} */ 
    function frmPhenCause_beforeSubmit() {
	
    	console.log("frmPhenCause_beforeSubmit called");
    	//jQuery('#submitForm').val('frmPhenCause');
        var phenId = jQuery('#cmbbphmKeyid').combobox('getValue');
        var phenName = jQuery.trim(jQuery('#txtbphmPhenomenaname').val());
        //var remarks = jQuery('#txtbphmRemarks').val();
        var assmId = jQuery('#cmbbphmAssemblyid').combobox('getValue');

        if (phenName === '') {
            alert('Type Phenomena Name');
            return false;
        }
			
        //jQuery('#phenId').val(jQuery('#cmbbphmKeyid').combobox('getValue'));
        //jQuery('#assmId').val(jQuery('#cmbbphmAssemblyid').combobox('getValue'));
        //jQuery('#hdnAssemblyId').val(jQuery('#cmbbphmAssemblyid').combobox('getValue'));

        jQuery('#phenId').val(phenId);
        //jQuery('#phenName').val(phenName);
        //jQuery('#remarks').val(remarks);
        jQuery('#assmId').val(assmId);
        jQuery('#hdnAssemblyId').val(assmId);

        return true;
    }

    function frmPhenCause_errorCallback(result) {
        alert('Error in callback');
    }
    
    function divPhenLink_onClose(){
        if (window.__restorePhenCauseContext) window.__restorePhenCauseContext();
        return true;
    }
</script>

<form name="frmPhenCause" id="frmPhenCause">
    <div id="wrapper">
        <table width="100%">
            <tr>
                <td colspan="2" style="padding: 8px 12px 4px 12px;">
                    <label class="mandatory-lbl">Assembly</label>&nbsp;
                    <input id="cmbbphmAssemblyid"
                           name="cmbbphmAssemblyid"
                           type="text"
                           class="easyui-combobox"
                           style="width:300px;"
                           value="${requestScope.assmId}" />
                    <input type="button" class="easyui-button" value="view"
                           style="height:22px; width:40px;"
                           onclick="viewPhenAssemblyFilter();" />
                    <input type="button" class="easyui-button" value="clear"
                           style="height:22px; width:40px;"
                           onclick="clearPhenAssemblyFilter();" />
                </td>
            </tr>
            <tr>
                <td width="55%" valign="top">
                    <div style="padding:10px 12px; height:400px;">
                        <table id="PhenListGrid"></table>
                        <div id="PhenListPager" style="margin-top:4px;"></div>
                    </div>
                </td>

                <td width="45%" valign="top">
                    <div class="easyui-paddingbfpx" style="padding-top:10px; padding-left:20px;">
                        <input type="hidden" id="hdnInactive" name="hdnInactive" />

                        <!-- <div>
                            <input type="checkbox" id="chkExiPhen" name="chkExiPhen" />
                            <span style="margin-left:2px;"><label>Existing Phenomena</label></span>
                            <input type="checkbox" id="chkNewPhen" name="chkNewPhen" />
                            <span style="margin-left:2px;"><label>New Phenomena</label></span>
                        </div> -->

                        <div class="easyui-paddingbfpx"><label>Phenomena</label></div>
                        <div class="easyui-paddingbfpx">
                            <input id="cmbbphmKeyid"
                                   name="cmbbphmKeyid"
                                   type="text"
                                   class="easyui-combobox"
                                   style="width:300px;"
                                   value="${requestScope.phenId}" />
                        </div>

                        <div class="easyui-paddingbfpx"><label class="mandatory-lbl">Name</label></div>
                        <div class="easyui-paddingbfpx">
                            <input id="txtbphmPhenomenaname"
                                   name="phenName"
                                   type="text"
                                   class="easyui-text"
                                   maxlength="49"
                                   style="width:300px;" />
                        </div>

                        <div class="easyui-paddingbfpx"><label>Remarks</label></div>
                        <div class="easyui-paddingbfpx">
                            <textarea id="txtbphmRemarks"
                                      name="remarks"
                                      cols="34"
                                      rows="5"
                                      maxlength="95"></textarea>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    
    <input type="hidden" id="phenId" name="phenId" />
	<!-- <input type="hidden" id="phenName" name="phenName" />
	<input type="hidden" id="remarks" name="remarks" /> -->

	<input type="hidden" id="assmId" name="assmId" value="${requestScope.assmId}" />
    <input type="hidden" id="hdnCombinedId" name="hdnCombinedId" value="${requestScope.combinedId}" />
    <input type="hidden" id="hdnLinkBd" name="hdnLinkBd" value="${requestScope.linkBd}" />
    <input type="hidden" id="hdnAssemblyId" name="bphmAssemblyid" value="${requestScope.assmId}" />

    <!-- Keep these hidden so old save code will not fail if backend still reads cause fields. -->
    <input type="hidden" id="chkbcsmIscausedefined" name="chkbcsmIscausedefined" value="Y" />
    <input type="hidden" id="cmbbcsmKeyid" name="cmbbcsmKeyid" value="" />
    <input type="hidden" id="txtbcsmName" name="txtbcsmName" value="" />
    <input type="hidden" id="txtbcsmRemarks" name="txtbcsmRemarks" value="" />
</form>
