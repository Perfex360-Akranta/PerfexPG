<%-- Trade Role Link JSP --%>
<script>
 //jQuery(document).ready(function()
//{
    //initialiseForm('frmTradeRoleLink');
    //jQuery('#submitForm').val('frmTradeRoleLink');
    //fillComboBox("frmTradeRoleLink","cmbTrlTradeKeyid","trade_combo.roleteam");
    //fillComboBox("frmTradeRoleLink","cmbTrlRoleKeyid","role_combo.roleteam");
    //trlViewGrid("tradeRoleLink_input.roleteam","?q=2");
    //jQuery('#submitForm').val('frmTradeRoleLink');

    /* ── Add button click ── */
 /*   jQuery('#btnAddTradeRole').click(function()
    {
        var url = "tradeRoleLink_save.roleteam?q=2";
        saveForm("frmTradeRoleLink", url);
    });*/
    
//}); 

function initTradeRoleLinkTab()
{
    initialiseForm('frmTradeRoleLink');
    //jQuery('#frmTradeRoleLink #submitForm').val('frmTradeRoleLink');
    //jQuery('#trlSubmitForm').val('frmTradeRoleLink');
    fillComboBox("frmTradeRoleLink","cmbTrlTradeKeyid","trade_combo.roleteam");
    fillComboBox("frmTradeRoleLink","cmbTrlRoleKeyid","role_combo.roleteam");
    trlViewGrid("tradeRoleLink_input.roleteam","?q=2");
}

/* ── Trade onSelect ── */
function frmTradeRoleLinkCmbTrlTradeKeyid_onSelect(record)
{
    jQuery('#cmbTrlRoleKeyid').combobox('setValue', "");

    reloadCombo("frmTradeRoleLink","cmbTrlRoleKeyid",
        "role_combo.roleteam?tradeId=" + record.id);

    var dataString = "?q=2&tradeId=" + record.id;
    trlViewGrid("tradeRoleLink_input.roleteam", dataString);
}

/* ── Role onSelect ── */
function frmTradeRoleLinkCmbTrlRoleKeyid_onSelect(record)
{
    /* placeholder */
}

//added by priyanka 
/* ── Save success callback ── */
function tradeRoleLink_saveSuccessCallback(result)
{
    alert(result.successData.msg);
    jQuery('#cmbTrlTradeKeyid').combobox('setValue', "");
    jQuery('#cmbTrlRoleKeyid').combobox('setValue', "");
    reloadCombo("frmTradeRoleLink","cmbTrlRoleKeyid","role_combo.roleteam"); // added by priyanka 
    trlViewGrid("tradeRoleLink_input.roleteam","?q=2");
}

/* ── Save error callback ── */
function tradeRoleLink_saveErrorCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "Save failed. Please try again.");
}

// end 

/* ── Delete formatter ── */
function trlBtnDeleteFormatter(id, options, rowObject)
{
    var rowId = options.rowId;
    return '<input type="button" style="width:24px;" class="grdButton" value="" '
        + 'onclick="deleteData(\'' + rowId + '\');"/>';
}

/* ── Delete handler ── */
/* function deleteData(rowId)
{
    if (rowId == null || rowId < 0) { return; }

    var rowData = jQuery("#grdTradeRoleLink").jqGrid('getRowData', rowId);
    var tradeId = rowData.gtrl_tradeid;
    var roleId  = rowData.gtrl_roleid;

    if (!tradeId || !roleId) {
        popupCommonErrorMsg("Trade and Role are required to delete");
        return;
    }

    var confDelete = confirm("Do You Want to Delete?");
    if (!confDelete) { return; }

    processAjaxCalls(
        "roletrademapping_delete.roleteam",
        "getTrlTradeKeyid=" + tradeId + "&getTrlRoleKeyid=" + roleId,
        "tradeRoleLinkDelete_successCallback",
        "tradeRoleLinkDelete_errorCallback"
    );
} */



// added by priyanka
function frmTradeRoleLink_deleteSuccessCallback(result)
{
    var msg = (result.successData && result.successData.msg) || "Deleted Successfully";
    alert(msg);
    jQuery('#cmbTrlTradeKeyid').combobox('setValue', "");
    jQuery('#cmbTrlRoleKeyid').combobox('setValue', "");
    fillComboBox("frmTradeRoleLink","cmbTrlRoleKeyid","role_combo.roleteam");
    trlViewGrid("tradeRoleLink_input.roleteam","?q=2");
}

function tradeRoleLinkDelete_errorCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "Delete failed. Please try again.");
}

// end 

/* ── Before delete confirm ── */
function frmTradeRoleLink_beforeDelete()
{
	jQuery('#submitForm').val('frmTradeRoleLink'); 
    return confirm("Do you want to delete this record?");
}

/* ── Delete success callback ── */
//function frmTradeRoleLink_deleteSuccessCallback(result)
//{
  //  alert(result.successData.msg);
    //jQuery('#cmbTrlTradeKeyid').combobox('setValue', "");
    //jQuery('#cmbTrlRoleKeyid').combobox('setValue', "");
    //reloadCombo("frmTradeRoleLink","cmbTrlRoleKeyid","role_combo.roleteam");// added by priyanka
    //viewGrid("tradeRoleLink_input.roleteam","?q=2");
//}

/* ── Before submit validation ── */
function frmTradeRoleLink_beforeSubmit()
{
	jQuery('#submitForm').val('frmTradeRoleLink');  
	
    var tradeId = jQuery('#cmbTrlTradeKeyid').combobox('getValue');
    var roleId  = jQuery('#cmbTrlRoleKeyid').combobox('getValue');

    if (!tradeId || tradeId.trim() === "")
    {
        alert("Please select a Trade.");
        return false;
    }
    if (!roleId || roleId.trim() === "")
    {
        alert("Please select a Role.");
        return false;
    }
   
}

/* ── Save success callback ── */
function frmTradeRoleLink_successsCallback(result)
{
    alert(result.successData.msg);
    jQuery('#cmbTrlTradeKeyid').combobox('setValue', "");
    jQuery('#cmbTrlRoleKeyid').combobox('setValue', "");
    trlViewGrid("tradeRoleLink_input.roleteam","?q=2");
}

/* ── viewGrid ── */
/* function viewGrid(url, filterString)
{
    if (validateFilterSelection(filterString))
    {
        processGridnew(
            url, filterString,
            "grdTradeRoleLink", "grdTradeRoleLinkpager",
            "TradeRoleLinkGrid",
            "", "", "", "selectRowFunction", ""
        );
        return true;
    }
} */

function trlViewGrid(url, filterString)
{
    console.log(">>> trlViewGrid url=[" + url + "] filterString=[" + filterString + "]");
    if (trlValidateFilterSelection(filterString))
    {
        processGridnew(
            url, filterString,
            "grdTradeRoleLink", "grdTradeRoleLinkpager",
            "",
            "trlDoubleClickGrid",
            "",
            "loadComplete",
            "",
            ""
        );
        setTimeout(function () {
            jQuery("#grdTradeRoleLink").setGridWidth(
                jQuery("#grdTradeRoleLink").closest("td").width() - 10
            );
        }, 200);
        return true;
    }
}
// commented and added below by priyanka
/* ── Row double-click ── */
/* function doubleClickGrid(id)
{
    var rowData = jQuery("#grdTradeRoleLink").jqGrid('getRowData', id);
    var tradeId = rowData.gtrl_tradeid;
    var roleId  = rowData.gtrl_roleid;

    jQuery('#cmbTrlTradeKeyid').combobox('setValue', tradeId);

    reloadCombo("frmTradeRoleLink","cmbTrlRoleKeyid",
        "role_combo.roleteam?tradeId=" + tradeId,
        function(){
            jQuery('#cmbTrlRoleKeyid').combobox('setValue', roleId);
        }
    );
} */


function trlDoubleClickGrid(id)
{
    var rowData = jQuery("#grdTradeRoleLink").jqGrid('getRowData', id);
    var tradeId = rowData.gtrl_tradeid;
    var roleId  = rowData.gtrl_roleid;

    jQuery('#cmbTrlTradeKeyid').combobox('setValue', tradeId);

    jQuery('#cmbTrlRoleKeyid').combobox('reload',
        'role_combo.roleteam?tradeId=' + tradeId
    ).combobox({
        onLoadSuccess: function() {
            jQuery('#cmbTrlRoleKeyid').combobox('setValue', roleId);
        }
    });
}
// end

/* ── Filter validation ── */
function trlValidateFilterSelection(filterString)
{
    return true;
}

// added by priyanka on 28/07/2026
function frmTradeRoleLink_exceptionCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "An error occurred. Please try again.");
}
//end 
</script>

<form name="frmTradeRoleLink" id="frmTradeRoleLink" style="margin-top:15px;">

    <div style="margin-left:400px;">

        <%-- Trade combo --%>
        <div class="easyui-paddingbfpx">
            <label class="mandatory-lbl">Trade</label>
        </div>
        <div class="easyui-paddingbfpx" style="width:300px;">
            <input class="easyui-combobox"
                id="cmbTrlTradeKeyid"
                name="cmbTrlTradeKeyid"
                style="width:300px;"
                value=""/>
        </div>

        <%-- Role combo --%>
        <div class="easyui-paddingbfpx">
            <label class="mandatory-lbl">Role</label>
        </div>
        <div class="easyui-paddingbfpx" style="width:300px;">
            <input class="easyui-combobox"
                id="cmbTrlRoleKeyid"
                name="cmbTrlRoleKeyid"
                style="width:300px;"
                value=""/>
        </div>

        <%-- Add button --%>
        <!-- <div class="easyui-paddingbfpx" style="margin-top:8px;">
            <input id="btnAddTradeRole"
                name="btnAddTradeRole"
                type="button"
                class="easyui-button"
                value="Add"
                style="width:100px; height:25px;"/>
        </div> -->
        

    </div>

    <%-- Grid: Trade | Role columns --%>
    <table style="margin-left:390px; margin-top:10px;">
        <tr>
            <td>
                <table id="grdTradeRoleLink"></table>
                <div id="grdTradeRoleLinkpager"></div>
            </td>
        </tr>
    </table>

    <input type="hidden" id="mode"       name="mode"/>
    <!-- <input type="hidden" id="submitForm" name="submitForm"/> -->
    <input type="hidden" id="trlSubmitFormVal" name="submitForm" value="frmTradeRoleLink"/>

</form>