<script>
jQuery(document).ready(function(){
    initialiseForm('frmPhenomenaMapping');
    jQuery('#submitForm').val('frmPhenomenaMapping');
    readOnlyFields("txtPhnmKeyid");

    var frmmode = jQuery("#mode").val();

    var factId    = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='factory']").val();
    var sectionId = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='section']").val();
    var cellId    = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='cell']").val();
    var machId    = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='machine']").val();
    var flid      = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='flid']").val();

    var dataStr = "&factId=" + factId + "&sectionId=" + sectionId
                + "&cellId=" + cellId + "&machId=" + machId + "&flid=" + flid;

    loadFunctionalLocation(
        "PhenomenaFunLocation",
        "functionalLoc.mom",
        "PhenomenaFunLocationValues",
        "frmPhenomenaMapping",
        dataStr
    );

    fillComboBox("frmPhenomenaMapping", "cmbPhnmQphmKeyid", "defphen.commonFilter?defectMode=OTHERS");

    // Added by Gopi on may11
    var phenomenaReady = false;  // flag to prevent premature grid load

    jQuery("#cmbPhnmQphmKeyid").combobox({
        onChange: function(newVal) {
            if (!phenomenaReady) return;
            var flid = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='flid']").val();
            jQuery("#hdnCurrentQphmKeyid").val(newVal || "");  // ← store it
            loadPhenomenaDmtGrid(flid, newVal);
        }
    });
    
setTimeout(function() {
    phenomenaReady = true;  // allow onChange to fire only after init is done
}, 500);
    
    // ADD THIS (new code — did not exist before)
 jQuery("#cmbPhnmQphmKeyid").combobox({
    onChange: function(newVal) {
        var flid = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='flid']").val();
        loadPhenomenaDmtGrid(flid, newVal);
    }
});
    //---------------
   // loadPhenomenaDmtGrid("");
   //changes by gopi on may11
    

    if (frmmode === "view") {
        disableForm("frmPhenomenaMapping");
    }
});



// changes by Gopi on may 11

function frmPhenomenaMapping_FuntLocHierarchy_SuccessCallBack(result){
    setFunctionalLocWidth("frmPhenomenaMapping", "620px");
    var flid      = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='flid']").val();
    var qphmKeyid = jQuery("#cmbPhnmQphmKeyid").combobox("getValue");
    
    // Store it so future reloads use it
    jQuery("#hdnCurrentQphmKeyid").val(qphmKeyid || "");
    
    loadPhenomenaDmtGrid(flid, qphmKeyid);
}

//Changes by Gopi on may11

function loadPhenomenaDmtGrid(flid, qphmKeyid) {
    // Store current qphmKeyid so grid reload can use it
    jQuery("#hdnCurrentQphmKeyid").val(qphmKeyid || "");
    
    var encodedFlid    = encodeURIComponent(flid      || "");
    var encodedQphmKey = encodeURIComponent(qphmKeyid || "");
    var colUrl = "PhenomenaDmtMapping_input.phmfass?flid=" + encodedFlid
               + "&qphmKeyid=" + encodedQphmKey;
    processGridnew(colUrl, "&q=1", "PhenomenaMappingGrid", "PhenomenaMappingPager", "", "", "", "");
    setTimeout(function() {
        jQuery("#PhenomenaMappingGrid").setGridWidth(480);
    }, 500);
}


function checkboxFormatter(cellvalue, options, rowObject) {
    var checked = (cellvalue === 'Y') ? 'checked' : '';
    
    var sectKeyid = rowObject[1];  // "SEC0000020" — unique per row
    var sectFlid  = rowObject[3];  // "FNL000030524" — same for all rows (not useful for save)
    var sectName  = rowObject[2];  // "ENGG OFFICE"
    
    return '<input type="checkbox" ' + checked
         + ' data-sectKeyid="' + sectKeyid + '"'   // ← use sectKeyid for save
         + ' data-sectFlid="'  + sectFlid  + '"'   // ← keep for reference
         + ' onclick="onDmtSelect(this)" />';
}

function onDmtSelect(chk) {
    var isChecked  = jQuery(chk).is(':checked') ? 'Y' : 'N';
    var sectKeyid  = jQuery(chk).data("sectkeyid");
    var sectFlid   = jQuery(chk).data("sectflid");
    console.log("sectKeyid: " + sectKeyid + ", sectFlid: " + sectFlid + " = " + isChecked);
}
/* function frmPhenomenaMapping_beforeSubmit(){
    var mode = jQuery("#mode").val();
    if (mode === "view") {
        alert("Cannot save in view mode");
        return false;
    }

    var phenomena = jQuery("#cmbPhnmQphmKeyid").combobox("getValue");
    if (!phenomena || phenomena.length === 0) {
        popupCommonErrorMsg("Select the Phenomena");
        return false;
    }

    jQuery("input[name='selectedDmtSectKeyids']").remove();

    var checkedSectKeyids = [];
    jQuery("#PhenomenaMappingGrid input[type='checkbox']:checked").each(function() {
        var sectKeyid = jQuery(this).data("sectkeyid");
        if (sectKeyid && sectKeyid !== "undefined" && sectKeyid !== "") {
            checkedSectKeyids.push(sectKeyid);
        }
    });

    if (checkedSectKeyids.length === 0) {
        popupCommonErrorMsg("Select at least one DMT");
        return false;
    }

    checkedSectKeyids.forEach(function(sectKeyid) {
        jQuery("#frmPhenomenaMapping").append(
            '<input type="hidden" name="selectedDmtSectKeyids" value="' + sectKeyid + '" />'
        );
    });
} */



function frmPhenomenaMapping_beforeSubmit(){
    var mode = jQuery("#mode").val();
    if (mode === "view") {
        alert("Cannot save in view mode");
        return false;
    }

    var phenomena = jQuery("#cmbPhnmQphmKeyid").combobox("getValue");
    if (!phenomena || phenomena.length === 0) {
        popupCommonErrorMsg("Select the Phenomena");
        return false;
    }



    jQuery("input[name='selectedDmtSectKeyids']").remove();
    jQuery("input[name='deselectedDmtSectKeyids']").remove();

    var checkedSectKeyids   = [];
    var uncheckedSectKeyids = [];

    jQuery("#PhenomenaMappingGrid input[type='checkbox']").each(function() {
        var sectKeyid = jQuery(this).data("sectkeyid");
        if (!sectKeyid || sectKeyid === "undefined" || sectKeyid === "") return;

        if (jQuery(this).is(':checked')) {
            checkedSectKeyids.push(sectKeyid);
        } else {
            uncheckedSectKeyids.push(sectKeyid);
        }
    });

    
    checkedSectKeyids.forEach(function(sectKeyid) {
        jQuery("#frmPhenomenaMapping").append(
            '<input type="hidden" name="selectedDmtSectKeyids" value="' + sectKeyid + '" />'
        );
    });



    uncheckedSectKeyids.forEach(function(sectKeyid) {
        jQuery("#frmPhenomenaMapping").append(
            '<input type="hidden" name="deselectedDmtSectKeyids" value="' + sectKeyid + '" />'
        );
    });



    if (checkedSectKeyids.length === 0 && uncheckedSectKeyids.length === 0) {
        popupCommonErrorMsg("No DMT rows found");
        return false;
    }
}
function frmPhenomenaMapping_successsCallback(result){
    jQuery("#txtPhnmKeyid").val(result.successData.keyId);
    jQuery("#hdnkeyId").val(result.successData.keyId);
    //refreshForm();
  //  alert(result.successData.msg);
    closePopUpDialoge("DivPhenomenaMapping");
    
    
    var flid      = jQuery("#frmPhenomenaMappingFuntKeyIds input[id='flid']").val();
    var qphmKeyid = jQuery("#hdnCurrentQphmKeyid").val();
    loadPhenomenaDmtGrid(flid, qphmKeyid);
}

function frmPhenomenaMapping_deleteSuccessCallbacks(result){
    alert(result.successData.msg);
    jQuery("#PhenomenaMappingGrid").trigger("reloadGrid");
}
</script>

<form id="frmPhenomenaMapping" name="frmPhenomenaMapping">
<div id="wrapper">
<table width="100%">

   
    <tr>
        <td colspan="4">
            <div id="frmPhenomenaMappingFuntKeyIds">
                <input type="hidden" id="factory" name="cmbPhnmFactoryid"   value=" " />
                <input type="hidden" id="section" name="cmbPhnmSectionid"   value=" " />
                <input type="hidden" id="cell"    name="cmbPhnmCellid"      value=" " />
                <input type="hidden" id="machine" name="cmbPhnmEquipmentid" value=" " />
                <input type="hidden" id="flid"    name="cmbPhnmFlid"
                    value="${requestScope.phenomenaMapping.phnmSectFlid}" />
            </div>
            <div id="PhenomenaFunLocation" style="width:100%; margin-top:-5px;"></div>
        </td>
    </tr>

    <!-- ===== Row 2 : Phenomena combo ===== -->
    <tr>
        <td colspan="4">
            <div class="easyui-paddingbfpx" style="margin-top:10px;">
                <label class="mandatory-lbl">Phenomena </label>
            </div>
            <div class="easyui-paddingbfpx">
                <input type="text" class="easyui-combobox"
                    id="cmbPhnmQphmKeyid" name="cmbPhnmQphmKeyid"
                    maxlength="15"
                    style="width:320px; text-align:left;"
                    value="${requestScope.phenomenaMapping.phnmQphmKeyid}" />
            </div>
        </td>
    </tr>

    <!-- ===== Row 3 : DMT Grid ===== -->
    <tr>
        <td colspan="4" style="padding-top:14px;">
            <table id="PhenomenaMappingGrid"></table>
            <div id="PhenomenaMappingPager" style="margin-top:4px;"></div>
        </td>
    </tr>

</table>
</div>

<!-- Hidden fields -->
<input type="hidden" id="txtPhnmKeyid"      name="txtPhnmKeyid"
    value="${requestScope.phenomenaMapping.phnmKeyid}" />
<input type="hidden" id="mode"              name="mode"
    value="${requestScope.mode}" />
<input type="hidden" id="hdnkeyId"          name="hdnkeyId"
    value="${requestScope.keyId}" />
<input type="hidden" id="txtPhnmTempfield1" name="txtPhnmTempfield1"
    value="${requestScope.phenomenaMapping.phnmTempfield1}" />
<input type="hidden" id="txtPhnmTempfield2" name="txtPhnmTempfield2"
    value="${requestScope.phenomenaMapping.phnmTempfield2}" />
<input type="hidden" id="txtPhnmTempfield3" name="txtPhnmTempfield3"
    value="${requestScope.phenomenaMapping.phnmTempfield3}" />
    <input type="hidden" id="hdnCurrentQphmKeyid" name="hdnCurrentQphmKeyid" value="" />

</form>