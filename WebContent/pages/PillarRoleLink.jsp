<%-- Pillar Role Link JSP --%>
<script>
 //jQuery(document).ready(function()
//{
    //initialiseForm('frmPillarRoleLink');
    //jQuery('#submitForm').val('frmPillarRoleLink');
    //fillComboBox("frmPillarRoleLink","cmbPrlPillarKeyid","pillar_combo.roleteam");
    //fillComboBox("frmPillarRoleLink","cmbPrlRoleKeyid","role_combo.roleteam");
    //prlViewGrid("rolepillarmapping_input.roleteam","?q=2");
    //jQuery('#submitForm').val('frmTradeRoleLink');

    /* ── Add button click ── */
 /*   jQuery('#btnAddTradeRole').click(function()
    {
        var url = "tradeRoleLink_save.roleteam?q=2";
        saveForm("frmTradeRoleLink", url);
    });*/
    
//});
 
function initPillarRoleLinkTab()
{
    initialiseForm('frmPillarRoleLink');
    //jQuery('#frmPillarRoleLink #submitForm').val('frmPillarRoleLink');
    //jQuery('#prlSubmitForm').val('frmPillarRoleLink');
    fillComboBox("frmPillarRoleLink","cmbPrlPillarKeyid","pillar_combo.roleteam");
    fillComboBox("frmPillarRoleLink","cmbPrlRoleKeyid","role_combo.roleteam");
    prlViewGrid("rolepillarmapping_input.roleteam","?q=2");
}

/* ── Pillar onSelect ── */
function frmPillarRoleLinkCmbPrlPillarKeyid_onSelect(record)
{
    jQuery('#cmbPrlRoleKeyid').combobox('setValue', "");

    reloadCombo("frmPillarRoleLink","cmbPrlRoleKeyid",
        "role_combo.roleteam?pillarId=" + record.id);

    var dataString = "?q=2&pillarId=" + record.id;
    prlViewGrid("rolepillarmapping_input.roleteam", dataString);
}

/* ── Role onSelect ── */
function frmPillarRoleLinkCmbPrlRoleKeyid_onSelect(record)
{
    /* placeholder */
}

//added by priyanka 
/* ── Save success callback ── */
function pillarRoleLink_saveSuccessCallback(result)
{
    alert(result.successData.msg);
    jQuery('#cmbPrlPillarKeyid').combobox('setValue', "");
    jQuery('#cmbPrlRoleKeyid').combobox('setValue', "");
    reloadCombo("frmPillarRoleLink","cmbPrlRoleKeyid","role_combo.roleteam"); // added by priyanka 
    prlViewGrid("rolepillarmapping_input.roleteam","?q=2");
}

/* ── Save error callback ── */
function pillarRoleLink_saveErrorCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "Save failed. Please try again.");
}

// end 

/* ── Delete formatter ── */
function prlBtnDeleteFormatter(id, options, rowObject)
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
function frmPillarRoleLink_deleteSuccessCallback(result)
{
    var msg = (result.successData && result.successData.msg) || "Deleted Successfully";
    alert(msg);
    jQuery('#cmbPrlPillarKeyid').combobox('setValue', "");
    jQuery('#cmbPrlRoleKeyid').combobox('setValue', "");
    fillComboBox("frmPillarRoleLink","cmbPrlRoleKeyid","role_combo.roleteam");
    prlViewGrid("rolepillarmapping_input.roleteam","?q=2");
}

function pillarRoleLinkDelete_errorCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "Delete failed. Please try again.");
}

// end 

/* ── Before delete confirm ── */
function frmPillarRoleLink_beforeDelete()
{
	jQuery('#submitForm').val('frmPillarRoleLink');
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
function frmPillarRoleLink_beforeSubmit()
{
	jQuery('#submitForm').val('frmPillarRoleLink');
    var pillarId = jQuery('#cmbPrlPillarKeyid').combobox('getValue');
    var roleId  = jQuery('#cmbPrlRoleKeyid').combobox('getValue');

    if (!pillarId || pillarId.trim() === "")
    {
        alert("Please select a Pillar.");
        return false;
    }
    if (!roleId || roleId.trim() === "")
    {
        alert("Please select a Role.");
        return false;
    }
   
}

/* ── Save success callback ── */
function frmPillarRoleLink_successsCallback(result)
{
    alert(result.successData.msg);
    jQuery('#cmbPrlPillarKeyid').combobox('setValue', "");
    jQuery('#cmbPrlRoleKeyid').combobox('setValue', "");
    prlViewGrid("rolepillarmapping_input.roleteam","?q=2");
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

function prlViewGrid(url, filterString)
{
    console.log(">>> prlViewGrid url=[" + url + "] filterString=[" + filterString + "]");
    if (prlValidateFilterSelection(filterString))
    {
        processGridnew(
            url, filterString,
            "grdPillarRoleLink", "grdPillarRoleLinkpager",
            "",
            "prlDoubleClickGrid",
            "",
            "loadComplete",
            "",
            ""
        );
        setTimeout(function () {
            jQuery("#grdPillarRoleLink").setGridWidth(
                jQuery("#grdPillarRoleLink").closest("td").width() - 10
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


function prlDoubleClickGrid(id)
{
    var rowData = jQuery("#grdPillarRoleLink").jqGrid('getRowData', id);
    var pillarId = rowData.yyrl_pillarid;
    var roleId  = rowData.yyrl_roleid;

    jQuery('#cmbPrlPillarKeyid').combobox('setValue', pillarId);

    jQuery('#cmbPrlRoleKeyid').combobox('reload',
        'role_combo.roleteam?pillarId=' + pillarId
    ).combobox({
        onLoadSuccess: function() {
            jQuery('#cmbPrlRoleKeyid').combobox('setValue', roleId);
        }
    });
}
// end

/* ── Filter validation ── */
function prlValidateFilterSelection(filterString)
{
    return true;
}

//added by priyanka on 28/07/2026
function frmPillarRoleLink_exceptionCallback(result)
{
    popupCommonErrorMsg(result.tpmException || "An error occurred. Please try again.");
}
// end
</script>

<form name="frmPillarRoleLink" id="frmPillarRoleLink" style="margin-top:15px;">

    <div style="margin-left:400px;">

        <%-- Pillar combo --%>
        <div class="easyui-paddingbfpx">
            <label class="mandatory-lbl">Pillar</label>
        </div>
        <div class="easyui-paddingbfpx" style="width:300px;">
            <input class="easyui-combobox"
                id="cmbPrlPillarKeyid"
                name="cmbPrlPillarKeyid"
                style="width:300px;"
                value=""/>
        </div>

        <%-- Role combo --%>
        <div class="easyui-paddingbfpx">
            <label class="mandatory-lbl">Role</label>
        </div>
        <div class="easyui-paddingbfpx" style="width:300px;">
            <input class="easyui-combobox"
                id="cmbPrlRoleKeyid"
                name="cmbPrlRoleKeyid"
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

    <%-- Grid: Pillar | Role columns --%>
    <table style="margin-left:390px; margin-top:10px;">
        <tr>
            <td>
                <table id="grdPillarRoleLink"></table>
                <div id="grdPillarRoleLinkpager"></div>
            </td>
        </tr>
    </table>

    <input type="hidden" id="mode"       name="mode"/>
    <!-- <input type="hidden" id="submitForm" name="submitForm"/> -->
    <input type="hidden" id="prlSubmitFormVal" name="submitForm" value="frmPillarRoleLink"/>

</form>