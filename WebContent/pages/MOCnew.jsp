
<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">	
var pssrCount="";
var initialApprovedPrimaryRoleMap = {};

var mocTeamInitialWorkflowLocked = "N";
var mocTeamExistingRoleMap = {};

jQuery(document).ready(function(){
	 initialiseForm('frmMocProject');
//	 alert('1236');
	 var flag="false";
	 jQuery('#submitForm').val('frmMocProject');
//	 alert('1236');
	//	var link ="?q=2&SuggestionFlid=FNL000000049&WhatifKey= &HazopKey= &Suggestion=MANAGEMENT OF CHANGE CHECKING&SuggestionId=KZBN002171&Status=RFC Initial Approval Pending&MocKeyid=MOC00000000215&Responsibility=EMP00001&psrmkey=PSR00000000051&filterButton=false";
		//processAjaxCalls("ChangeRequest_input.mocn",link,"sectionIdRecallSuccess","");

 var factId = jQuery("#frmMocProject input[id='factory']").val();
		var sectionId = jQuery("#frmMocProject input[id='section']").val();
		var cellId = jQuery("#frmMocProject input[id='cell']").val();
		var machId = jQuery("#frmMocProject input[id='machine']").val();
		
		var flid = jQuery("#frmMocProject input[id='flid']").val();
//		var flid = jQuery("#frmMocProject input[id='flid']").val();

		////////////////////////////////////////////alert("flid"+flid);
		var WhatifKey=jQuery("#hdnWhatifKey").val();
		var nature=jQuery("#hdnnature").val();
		var type=jQuery("#hdntype").val();
		//fillComboBox("frmMocProject","cmbRfcmtype","MocType.mocn");
		// fillComboBox("frmMocProject","cmbRfcmnature","MocNature.mocn");
		 fillComboBox("frmMocProject","cmbRfcmempid","employee.commonFilter");
		//  var FobmKeyid=jQuery("#txtFobmKeyid").val();
	   // ////////////////////////////////////////////////////alert("FobmKeyid:"+FobmKeyid);
	   fillComboBox("frmKaizensugg", "cmbMocItem", "mocitem.commonFilter");
	   
	   // vignesh 11 may 
	// vignesh 11 may - EXCEL UPLOAD CHANGES - START

		jQuery("#wtbtnUploadExcel").off("click.whatifexcel").on("click.whatifexcel", function () {

		    LoadPopUp(
		        "divWhatIfExcelPopup",
		        "WhatIfExcelPopup_input.mocn?q=2",
		        true,
		        "90%",
		        "82%",
		        "1%",
		        "1%",
		        "",
		        "",
		        false,
		        false
		    );

		});
	   
		jQuery("#hzbtnUploadExcel").off("click.hazopexcel").on("click.hazopexcel", function () {

		    LoadPopUp(
		        "divHazopExcelPopup",
		        "HazopExcelPopup_input.mocn?q=2",
		        true,
		        "90%",
		        "82%",
		        "1%",
		        "1%",
		        "",
		        "",
		        false,
		        false
		    );

		});
		
		function populateWhatIfGridFromExcel(rows) {

		    var gridId = "WhatifGrid";
		    var grid = jQuery("#" + gridId);

		    if (rows == null || rows.length === 0) {
		        alert("No Excel rows available.");
		        return false;
		    }

		    // Do NOT clear existing DB/grid rows.
		    // Existing WhatIf rows must remain, Excel rows must be added below them.
		    var existingIds = grid.jqGrid("getDataIDs");

		    var nextRowId = 1;

		    if (existingIds != null && existingIds.length > 0) {

		        for (var i = 0; i < existingIds.length; i++) {

		            var idVal = existingIds[i];

		            // If jqGrid row id is numeric, use max + 1.
		            if (!isNaN(idVal)) {
		                var num = parseInt(idVal, 10);
		                if (num >= nextRowId) {
		                    nextRowId = num + 1;
		                }
		            }
		        }

		        // If existing row ids are not numeric, start after row count.
		        if (nextRowId === 1) {
		            nextRowId = existingIds.length + 1;
		        }
		    }

		    for (var j = 0; j < rows.length; j++) {

		        var rowData = rows[j];

		        // New Excel rows must be inserted as new records.
		        // So do not carry any existing DB detail key.
		        rowData.hdnWifdKeyid = "";

		        var newRowId = nextRowId + j;

		        grid.jqGrid("addRowData", newRowId, rowData, "last");

		        // Set hidden key fields safely after adding row.
		        grid.jqGrid("setCell", newRowId, "txtWifdLikeHood1", rowData.txtWifdLikeHood1);
		        grid.jqGrid("setCell", newRowId, "txtWifdSeverity1", rowData.txtWifdSeverity1);
		        grid.jqGrid("setCell", newRowId, "Responsibiltyby", rowData.Responsibiltyby);
		        grid.jqGrid("setCell", newRowId, "statusdetail", rowData.statusdetail);

		        grid.jqGrid("setCell", newRowId, "txtWifdLikeHood2", rowData.txtWifdLikeHood2);
		        grid.jqGrid("setCell", newRowId, "txtWifdSeverity2", rowData.txtWifdSeverity2);
		    }

		    alert(rows.length + " Excel row(s) added below existing WhatIf rows.");
		}
		 
		function hazopExcelTrim(value) {

		    if (value == null || value === undefined) {
		        return "";
		    }

		    return jQuery.trim(String(value));
		}
		
		function hazopExcelIsBlank(value) {

		    value = hazopExcelTrim(value);

		    return value.length === 0
		            || value === "{}"
		            || value === "-"
		            || value.toLowerCase() === "null";
		}
		
		function extractHazopLookupKeyid(resultObj, keyCandidates) {

		    var keyid = "";
		    var obj = resultObj;

		    if (obj == null || obj === undefined) {
		        return "";
		    }

		    if (typeof obj === "string") {
		        try {
		            obj = jQuery.parseJSON(obj);
		        } catch (e) {
		            obj = { value: obj };
		        }
		    }

		    for (var i = 0; i < keyCandidates.length; i++) {
		        var key = keyCandidates[i];
		        var val = "";

		        if (obj[key] !== undefined && obj[key] !== null) {
		            val = hazopExcelTrim(obj[key]);
		        } else if (obj.successData && obj.successData[key] !== undefined && obj.successData[key] !== null) {
		            val = hazopExcelTrim(obj.successData[key]);
		        }

		        if (!hazopExcelIsBlank(val)) {
		            keyid = val;
		            break;
		        }
		    }

		    return keyid;
		}
		
		
		function lookupHazopMasterKeyid(actionUrl, postData, keyCandidates, fallbackValue, displayValue) {

		    var keyid = "";
		    var fallbackKey = hazopExcelTrim(fallbackValue);
		    var displayText = hazopExcelTrim(displayValue);

		    if (!hazopExcelIsBlank(fallbackKey)
		            && !hazopExcelIsBlank(displayText)
		            && fallbackKey.toLowerCase() === displayText.toLowerCase()) {
		        fallbackKey = "";
		    }

		    try {
		        jQuery.ajax({
		            type: "POST",
		            url: actionUrl,
		            data: postData,
		            dataType: "json",
		            async: false,
		            success: function(result) {
		                keyid = extractHazopLookupKeyid(result, keyCandidates);
		            },
		            error: function() {
		                keyid = "";
		            }
		        });
		    } catch (e) {
		        keyid = "";
		    }

		    // Provision/fallback: if lookup action is not yet available, use row keyid from parsed data.
		    if (hazopExcelIsBlank(keyid) && !hazopExcelIsBlank(fallbackKey)) {
		        keyid = fallbackKey;
		    }

		    return hazopExcelTrim(keyid);
		}
		
		
		
		function populateHazopGridFromExcel(jsonData) {

		    var gridId = "Hazopgrid";
		    var grid = jQuery("#" + gridId);
		    var rows = jsonData;

		    if (rows == null || rows.length === 0) {
		        return {
		            success: false,
		            errors: ["No Excel rows available."]
		        };
		    }

		    var validationErrors = [];
		    var validRows = [];

		    for (var i = 0; i < rows.length; i++) {

		        var rowData = jQuery.extend({}, rows[i]);
		        var rowNo = hazopExcelTrim(rowData.excelRowNo);

		        if (hazopExcelIsBlank(rowNo)) {
		            rowNo = String(i + 1);
		        }

		        var guideWord = hazopExcelTrim(rowData.cmbMohdGuideword);
		        var parameter = hazopExcelTrim(rowData.txtMohdParameter);
		        var deviation = hazopExcelTrim(rowData.txtMohdDeviation);
		        var causes = hazopExcelTrim(rowData.txtMohdCauses);
		        var consequence = hazopExcelTrim(rowData.txtMohdCosequeces);
		        var safeguards = hazopExcelTrim(rowData.txtMohdWithoutSafeGuards);
		        var likelihood = hazopExcelTrim(rowData.cmbMohdLikeHood1);
		        var severity = hazopExcelTrim(rowData.cmbMohdSeverity1);
		        var rpn = hazopExcelTrim(rowData.txtMohdRisk1);
		        var recommendations = hazopExcelTrim(rowData.txtMohdRecommentations);
		        var responsible = hazopExcelTrim(rowData.cmbMocrResponsibility);
		        var targetDate = hazopExcelTrim(rowData.dteMocrTargetDate);

		        var mandatoryChecks = [
		            { value: guideWord, label: "Guide Word (Column B)" },
		            { value: parameter, label: "Parameter (Column C)" },
		            { value: deviation, label: "Deviation (Column D)" },
		            { value: causes, label: "Causes (Column E)" },
		            { value: consequence, label: "Consequence (Column F)" },
		            { value: safeguards, label: "Present Safeguards (Column G)" },
		            { value: likelihood, label: "Likelihood (Column H)" },
		            { value: severity, label: "Severity (Column I)" },
		            { value: rpn, label: "RPN (Column J)" },
		            { value: recommendations, label: "Recommendations (Column K)" },
		            { value: responsible, label: "Responsible By (Column L)" },
		            { value: targetDate, label: "Target Date (Column M)" }
		        ];

		        var hasRowError = false;

		        for (var m = 0; m < mandatoryChecks.length; m++) {
		            if (hazopExcelIsBlank(mandatoryChecks[m].value)) {
		                validationErrors.push("Row " + rowNo + ": " + mandatoryChecks[m].label + " is mandatory");
		                hasRowError = true;
		            }
		        }

		        if (hasRowError) {
		            continue;
		        }

		        var guideWordKeyid = lookupHazopMasterKeyid(
		                "getGuideWordKeyidByName.mocn",
		                { guideWordName: guideWord },
		                ["mgwmKeyid", "mgwm_keyid", "keyid", "keyId", "value", "id"],
		                rowData.txtMohdGuideword,
		                guideWord
		        );

		        if (hazopExcelIsBlank(guideWordKeyid)) {
		            validationErrors.push("Row " + rowNo + ": Guide Word '" + guideWord + "' not found in master");
		            continue;
		        }

		        var likelihoodKeyid = lookupHazopMasterKeyid(
		                "getProbabilityKeyidByCode.mocn",
		                { probabilityCode: likelihood },
		                ["prbmKeyid", "prbm_keyid", "keyid", "keyId", "value", "id"],
		                rowData.txtMohdLikeHood1,
		                likelihood
		        );

		        if (hazopExcelIsBlank(likelihoodKeyid)) {
		            validationErrors.push("Row " + rowNo + ": Likelihood '" + likelihood + "' not found in master");
		            continue;
		        }

		        var severityKeyid = lookupHazopMasterKeyid(
		                "getSeverityKeyidByCode.mocn",
		                { severityCode: severity },
		                ["sivmKeyid", "sivm_keyid", "keyid", "keyId", "value", "id"],
		                rowData.txtMohdSeverity1,
		                severity
		        );

		        if (hazopExcelIsBlank(severityKeyid)) {
		            validationErrors.push("Row " + rowNo + ": Severity '" + severity + "' not found in master");
		            continue;
		        }

		        var responsibleKeyid = lookupHazopMasterKeyid(
		                "getResponsibleEmpKeyidByCode.mocn",
		                { empCode: responsible },
		                ["empmKeyid", "empm_keyid", "keyid", "keyId", "value", "id"],
		                rowData.Responsibiltyby,
		                responsible
		        );

		        if (hazopExcelIsBlank(responsibleKeyid)) {
		            validationErrors.push("Row " + rowNo + ": Responsible By '" + responsible + "' not found in master");
		            continue;
		        }

		        rowData.hdnMohdKeyid = "";
		        rowData.txtMohdGuideword = guideWordKeyid;
		        rowData.cmbMohdGuideword = guideWord;
		        rowData.txtMohdLikeHood1 = likelihoodKeyid;
		        rowData.cmbMohdLikeHood1 = likelihood;
		        rowData.txtMohdSeverity1 = severityKeyid;
		        rowData.cmbMohdSeverity1 = severity;
		        rowData.txtMohdRisk1 = rpn;
		        rowData.Responsibiltyby = responsibleKeyid;
		        rowData.cmbMocrResponsibility = responsible;
		        rowData.dteMocrTargetDate = targetDate;
		        rowData.cmbMocrStatus = hazopExcelTrim(rowData.cmbMocrStatus).length > 0 ? hazopExcelTrim(rowData.cmbMocrStatus) : "Pending";
		        rowData.statusdetail = hazopExcelTrim(rowData.statusdetail).length > 0 ? hazopExcelTrim(rowData.statusdetail) : "P";
		        rowData.txtMocrCompleteDate = hazopExcelTrim(rowData.txtMocrCompleteDate);
		        rowData.txtMocrActionplanId = hazopExcelTrim(rowData.txtMocrActionplanId);
		        rowData.txtMohdLikeHood2 = hazopExcelTrim(rowData.txtMohdLikeHood2);
		        rowData.txtMohdSeverity2 = hazopExcelTrim(rowData.txtMohdSeverity2);
		        rowData.txtMohdRisk2 = hazopExcelTrim(rowData.txtMohdRisk2);
		        rowData.txtMohdRemarks = hazopExcelTrim(rowData.txtMohdRemarks);

		        if (hazopExcelIsBlank(rowData.cmbMohdLikeHood2)) {
		            rowData.cmbMohdLikeHood2 = "";
		        }

		        if (hazopExcelIsBlank(rowData.cmbMohdSeverity2)) {
		            rowData.cmbMohdSeverity2 = "";
		        }

		        delete rowData.excelSno;
		        delete rowData.excelRowNo;
		        delete rowData.rowHasError;
		        delete rowData.errorMessage;

		        validRows.push(rowData);
		    }

		    if (validationErrors.length > 0) {
		        return {
		            success: false,
		            errors: validationErrors
		        };
		    }

		    var existingIds = grid.jqGrid("getDataIDs");
		    var nextRowId = 1;

		    if (existingIds != null && existingIds.length > 0) {

		        for (var k = 0; k < existingIds.length; k++) {

		            var idVal = existingIds[k];

		            if (!isNaN(idVal)) {
		                var num = parseInt(idVal, 10);
		                if (num >= nextRowId) {
		                    nextRowId = num + 1;
		                }
		            }
		        }

		        if (nextRowId === 1) {
		            nextRowId = existingIds.length + 1;
		        }
		    }

		    for (var j = 0; j < validRows.length; j++) {

		        var hazopRow = validRows[j];
		        var newRowId = nextRowId + j;

		        grid.jqGrid("addRowData", newRowId, hazopRow, "last");

		        grid.jqGrid("setCell", newRowId, "txtMohdGuideword", hazopRow.txtMohdGuideword);
		        grid.jqGrid("setCell", newRowId, "txtMohdLikeHood1", hazopRow.txtMohdLikeHood1);
		        grid.jqGrid("setCell", newRowId, "txtMohdSeverity1", hazopRow.txtMohdSeverity1);
		        grid.jqGrid("setCell", newRowId, "Responsibiltyby", hazopRow.Responsibiltyby);
		        grid.jqGrid("setCell", newRowId, "statusdetail", hazopRow.statusdetail);
		        grid.jqGrid("setCell", newRowId, "txtMohdLikeHood2", hazopRow.txtMohdLikeHood2);
		        grid.jqGrid("setCell", newRowId, "txtMohdSeverity2", hazopRow.txtMohdSeverity2);
		    }

		    alert(validRows.length + " Excel row(s) added below existing Hazop rows.");

		    return {
		        success: true,
		        errors: [],
		        addedRows: validRows.length
		    };
		}
		
		
		window.populateWhatIfGridFromExcel = populateWhatIfGridFromExcel;
		window.populateHazopGridFromExcel = populateHazopGridFromExcel;
		
		// vignesh 11 may - EXCEL UPLOAD CHANGES - END
	   /*
	   var capexVal = jQuery.trim(jQuery("#chckMocRfcmCapex").val());
   // alert("capexVal "  +  capexVal);
    if (capexVal === "Y") {
        jQuery("#capexYes").prop("checked", true);
        jQuery("#capexNo").prop("checked", false);
    } else if (capexVal === "N") {
        jQuery("#capexYes").prop("checked", false);
        jQuery("#capexNo").prop("checked", true);
    } */
    jQuery(document)
    .off("change.workflowRoleLock", "input[id^='jqg_TeamGrid_']")
    .on("change.workflowRoleLock", "input[id^='jqg_TeamGrid_']", function () {

        setTimeout(function () {
            applyMocTeamWorkflowRoleLock();
        }, 100);
    });
    
    jQuery(document)
    .off("change.initialApprovedPrimaryLock", "input[id^='jqg_TeamGrid_']")
    .on("change.initialApprovedPrimaryLock", "input[id^='jqg_TeamGrid_']", function () {

        var rowId = this.id.replace("jqg_TeamGrid_", "");

        // Let your existing checkbox logic finish first.
        // Then re-lock the primary approval combo if initial approval is already A.
        setTimeout(function () {
            reLockPrimaryApprovalIfInitialApproved(rowId);
        }, 100);
    });
    
 // When user clicks the Pillar Champion grid row
    jQuery(document)
        .off("click.pillarChampionRow", "#TeamGrid tr.jqgrow")
        .on("click.pillarChampionRow", "#TeamGrid tr.jqgrow", function () {

            var rowId = this.id;
            refreshPillarChampionComboIfNeeded(rowId);
        });

    // When user checks/unchecks TeamGrid checkbox
    jQuery(document)
        .off("change.pillarChampionCheck", "input[id^='jqg_TeamGrid_']")
        .on("change.pillarChampionCheck", "input[id^='jqg_TeamGrid_']", function () {

            var rowId = this.id.replace("jqg_TeamGrid_", "");
            refreshPillarChampionComboIfNeeded(rowId);
        });
    
    var capexVal = jQuery.trim(jQuery("#chckMocRfcmCapex").val());

  //  alert("DB CapEx value = " + capexVal);

    syncCapexToAllTabs(capexVal);
    jQuery("#txtNumberOfDays").on("input", function () {
        this.value = this.value.replace(/[^0-9]/g, '');
    });
    var noofdays = jQuery.trim(jQuery("#hdnMocRfcmNoofdays").val());

    jQuery("#txtNumberOfDays").val(noofdays);

    if (jQuery("#cboRfcmnature").val() === "Temporary") {
        jQuery("#txtNumberOfDays").removeAttr("disabled");
        jQuery("#txtNumberOfDays").css("background-color", "#ffffff");
    } else {
        jQuery("#txtNumberOfDays").attr("disabled", "disabled");
        jQuery("#txtNumberOfDays").css("background-color", "#f5f5f5");
    }
    
    function loadOthersNotesFromDB() {
        var othersText = jQuery.trim(jQuery("#hdnAdditionalNotes").val());

        if (othersText !== "" && othersText !== "{}" && othersText !== "-") {
            jQuery("#txtAdditionalNotes").val(othersText);
            jQuery("#txtAdditionalNotes").removeAttr("disabled");
            jQuery("#txtAdditionalNotes").css("background-color", "#fff");
        } else {
            jQuery("#txtAdditionalNotes").val("");
            jQuery("#txtAdditionalNotes").attr("disabled", "disabled");
        }
    }
    loadOthersNotesFromDB();

   // alert("Fetched No of Days = " + noofdays);
    // vignesh 11 may 
	    var responsibility = jQuery('#hdnInitiator').val();
            
	    var userid=jQuery("#hdnuserid").val();
	  
	   var mode= jQuery('#hdnmode').val();
	// //////////////////////alert("mode:::"+mode)
	   /*  if(mode.length<=0){
	    	////////////////////////////////////alert("IN If");
	    if(userid==responsibility){
	    	jQuery('#hdnmode').val("create")	
	  //  	jQuery('#hdnmode').val("view")	
	   // 	alert(" Only View Mode -- Module Under Development ")
	    }
	    else{
	    	jQuery('#hdnmode').val("view");
	    	 jQuery('#capexYes').prop('disabled', true);//ADD CAPEX DISABLE
			   jQuery('#capexNo').prop('disabled', true);//ADD CAPEX DISABLE
	    }
	    } */
	    
	    var mode = jQuery.trim("${requestScope.mode}");
	    var userid = jQuery.trim("${requestScope.userid}");
	    var responsibility = jQuery.trim("${requestScope.Responsibility}");

	    if (mode.length > 0) {
	        jQuery('#hdnmode').val(mode);
	    }
	    else {
	        if (userid == responsibility) {
	            jQuery('#hdnmode').val("create");
	        }
	        else {
	            jQuery('#hdnmode').val("view");
	        }
	    }

	    console.log("MOC Page Mode = " + jQuery('#hdnmode').val());
	    var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();
	
	if(pageMode == "view" || pageMode == "approval"){
		jQuery('#capexYes').prop('disabled', true);
		 jQuery('#capexNo').prop('disabled', true);
		disableField("frmMocProject","cboRfcmtype");
		disableField("frmMocProject","cboRfcmnature");
		
		setTimeout(function() {readOnlyFields('dteRfcmdate');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmdetail');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmtitle');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmdescription');},1250);
		 setTimeout(function() {readOnlyFields('txtWifmFacility');},1250);
		 setTimeout(function() {readOnlyFields('txtWifmTeam');},1250);
		 setTimeout(function() {readOnlyFields('dteWifmDate');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomFacility');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomTeam');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomPidno');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomNode');},1250);
		 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomDesignintent');},1250);
		 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
		 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
		 setTimeout(function() {readOnlyFields('chkSelectAllQuestions');},1250);
		 setTimeout(function() {readOnlyFields('txtNumberOfDays');},1250); //New addition
		 setTimeout(function() {readOnlyFields('txtAdditionalNotes');},1250); //New addition
		 disableUIButton("btnAddNewReccommend");
			disableUIButton("btnAddHazop");
			disableUIButton("btnAddwhatif");



	}
	   
	// ////////////////////////////////////////////////////alert("responsibility"+responsibility);
	 readOnlyFields('cmbRfcmempid');
	 setTimeout(function() {readOnlyFields('cmbRfcmempid');},1250);
	 setTimeout(function() {readOnlyFields('cmbRfcmjhid');},1250);
	 setTimeout(function() {readOnlyFields('cmbrfcmdmtid');},1250);
	   var SuggestionId=jQuery('#hdnSuggestionId').val();
	  

		
		disableField("frmMocProject", "cmbRfcmempid")
	
		formatDateBox('dteRasmDate','DD-MON-YYYY');
		formatDateBox('dteRfcmdate','dd-MMM-yyyy');
		formatDateBox('dtePsrmdate','dd-MMM-yyyy');
		formatDateBox('dteWifmDate','dd-MMM-yyyy');
		formatDateBox('dteHzomDate','dd-MMM-yyyy');
		
		var mocDateFromDB = jQuery.trim("${requestScope.mocDate}");

		if (mocDateFromDB != null 
		        && mocDateFromDB != "" 
		        && mocDateFromDB != "null" 
		        && mocDateFromDB != "{}") {

		    jQuery("#dteRfcmdate").datebox("setValue", mocDateFromDB);
		    jQuery("#dteRfcmdate").datebox("disable");

		} else {

		    fillWithCurrentDate('dteRfcmdate');
		}
		
		//fillWithCurrentDate('dteRfcmdate');
		fillWithCurrentDate('dtePsrmdate');
		fillWithCurrentDate('dteRasmDate');
		//fillWithCurrentDate('dteHazopDate');
		fillWithCurrentDate('dteHzomDate');
		fillWithCurrentDate('dteWifmDate');
		
		 fileManagerPopUp("","MOC","frmMocProject","btnFilManage","MocfileMgr"); 
		 //sriram
		
		 fileManagerPopUp("","MOC","frmMocProject","btnFilManage","WhatifFilemgr");
		 fileManagerPopUp("","MOC","frmMocProject","btnFilManage","HazopFilemgr");
		 
		 var SugFlid=jQuery('#hdnSuggestionFlid').val();
			////////////////////////////////////////////alert("SuggFlid"+SugFlid);
		var MocKeyid=jQuery("#txtRfcmKeyid").val();
		if(MocKeyid !=null){
			processAjaxCalls("WhatIfHazopRecommentationsCount.mocn","&MocKeyid="+MocKeyid,"WH_successcallback","WH_Errorcallback");
			
		processAjaxCalls("PSSRRecommentationsCount.mocn","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","");
		processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","");
		processAjaxCalls("HazopApprovalCount.mocn","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","");
		processAjaxCalls("FinalApprovalCount.mocn","&MocKeyid="+MocKeyid,"FinalApproval_successcallback","");
		processAjaxCalls("MOCClosureCheck.mocn","&MocKeyid="+MocKeyid,"MOCClosure_successcallback","");
		
		jQuery("#cboRfcmnature").val(nature);
		jQuery("#cboRfcmtype").val(type);
		
		//  var MOCStatus=jQuery("#hdnFinalcount").val();

		}
		
		jQuery("#cboRfcmnature").change(function() {
		    var selectedValue = jQuery(this).val();
		    if (selectedValue === "Temporary") {
		        jQuery("#txtNumberOfDays").removeAttr("disabled");
		        jQuery("#txtNumberOfDays").css("background-color", "#ffffff");
		    } else {
		        jQuery("#txtNumberOfDays").attr("disabled", "disabled");
		        jQuery("#txtNumberOfDays").val("");
		        jQuery("#txtNumberOfDays").css("background-color", "#f5f5f5");
		    }
		});
		jQuery("#cboRfcmnature").trigger('change');
			 if(flid !=null)
			    {	 
	//		alert("Loadfn" + flid);
			    	loadFunctionalLocation("NewMocfunLocation","functionalLoc.mocn","NewMOCfunLocationValues","frmMocProject","&flid="+flid);
			    	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
			    	//reloadCombo("frprocessAjaxmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
			 	   
			    }
			    else
			 	   {
			    	////////////////////////////////////////////////alert("ELSELoadfn");
			 	   loadFunctionalLocation("NewMocfunLocation","functionalLoc.mocn","NewMocfunLocationValues","frmMocProject","");
			 	   } 
		// loadFunctionalLocation("NewMocfunLocation","functionalLoc.mocn","NewMOCfunLocationValues","frmMocProject","");
     
     var flid = jQuery("#frmMocProject input[id='flid']").val();
     ////////////////////////////////////////////////alert("flid:::"+flid);
	// fillComboBox("frmMocProject","cmbRfcmtype","MocType.mocn");
	// fillComboBox("frmMocProject","cmbRfcmnature","MocNature.mocn");
	 fillComboBox("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter");
	 fillComboBox("frmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter");
	 fillComboBox("frmMocProject","cmbDmdmType","type_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmSubjectarea","subjectArea_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmCategory","category_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmOwner","employee.commonFilter");
	 fillComboBox("frmMocProject","cmbDmdmApprovedby","employee.commonFilter");
	 var responsibility = jQuery('#hdnResponsibility').val();
	 var mode=jQuery("#hdnmode").val();
	 
	 var initial =jQuery("#hdnInitialApproval").val();
	 var hazop=	jQuery("#hdnHazopApproval").val();


	if(initial=="Pending"||hazop=="Pending")
	{
	 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
	 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
	 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
	}
	// Enable Number of Days only when Nature of Change = Temporary
	 jQuery("#cboRfcmnature").change(function() {
    var selectedValue = jQuery(this).val();

    if (selectedValue === "Temporary") {
        jQuery("#txtNumberOfDays").removeAttr("disabled");
        jQuery("#txtNumberOfDays").css("background-color", "#ffffff");
    } else {
        jQuery("#txtNumberOfDays").attr("disabled", "disabled");
        jQuery("#txtNumberOfDays").val("");
        jQuery("#hdnMocRfcmNoofdays").val("");
        jQuery("#txtNumberOfDays").css("background-color", "#f5f5f5");
    }
});
	
	jQuery("#txtNumberOfDays").on("keyup change blur", function() {
	    jQuery("#txtNumberOfDays").val(jQuery.trim(jQuery(this).val()));
	});
	
}); // <-- this is the existing closing }); of document.ready
  // -- Moving inside document ready Vignesh
//  var initial =jQuery("#hdnInitialApproval").val();
//  var hazop=	jQuery("#hdnHazopApproval").val();


// if(initial=="Pending"||hazop=="Pending")
// {
//  setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
//  setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
//  setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
// }

function toggleFinalArea(textareaId, checkbox) {
    var check = document.getElementById(textareaId);
    check.disabled = !checkbox.checked;
    if (checkbox.checked) {
    	check.focus();
    } else {
    	check.value = '';
    }
}

function btnFilManage_click(){
    var documentNo =jQuery("#txtRfcmKeyid").val();
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#frmMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"MOC","","","","create");		
	} 
	else  
    {  
		 return false;
     }	
}
  
 
jQuery("#tabMocProject").tabs(
 	    {
 		onSelect : function(title)
 		 {
 			if(title=="MOC Workflow"){
 				 jQuery("#hdntitle").val("MOC Team");
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
			   //  processGridnew("MOCTeam_input.mocn","q=2&SrpmKeyid="+SrpmKeyid,"TeamGrid", "Teampagerid","","", "", "");
     
 				 processGridnew("MOCTeamSusscess_input.mocn","q=2&MocKeyid="+MocKeyid,"TeamGrid", "Teampagerid","","", "", "TeamloadComFunction");    

 			}
 			else if(title=="RequestforChange"){
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
 				//////////////////////////////////////////////alert("MOCKey"+MocKeyid);
 				 jQuery("#hdntitle").val("RequestforChange");
                 processGridnew("BasisofChange_input.mocn","q=2&MocKeyid="+MocKeyid,"BasisGrd","BasisGrdpager","","","","Basisgridcompletecallback");
            
 			
 			}
 			
 			//processGridnew("PssrChecklist_input.ehsb","?q=2&keyid="+keyid+"&userid="+userid,"QCGrid","pagerQcLog","","","");
 			else if(title=="Questionnaire")	{
 			//	////////////////////////////////////////////////////alert("IFCond")Questionnaire
 			 jQuery("#hdntitle").val("Questionnaire");
 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 			processGridnew("Questionaire_input.mocn","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","","QuestionnaireloadComFunction");	
 		 }
 			else if(title=="InitialApproval")	{
 				 jQuery("#hdntitle").val("InitialApproval");
 	 		var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 	
 	 			processGridnew("InitialApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"InitialGrid","pager","","docDoubleClick","","InitialloadComFunction");	 
 		
 	 	
 	 		}
 		
 			else if(title=="WhatIf"){
 	 			//	//////////////////////////////////////////////alert("WhatIf");
 	 			jQuery("#hdntitle").val("WhatIf");
 	 			 var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 	 			
 	 			 var initial =jQuery("#hdnInitialApproval").val();
 	 		   
 	 		      if(initial=="Pending"){
 	 		    	disableUIButton("btnAddwhatif");
 	 		    	disableField("frmMocProject","dteWifmDate");
 	 		    	disableField("frmMocProject","txtWifmTeam");
 	 		    	disableField("frmMocProject","txtWifmFacility");
 	 		    	 setTimeout(function() {readOnlyFields('txtWifmFacility');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtWifmTeam');},1250);
 	 				 
 	 		      }
 	 		      /*else{
 	 		    	  enableUIButton("btnAddwhatif");
 	 		    	  enableFields("dteWifmDate");
 	 		    	  enableFields("txtWifmTeam");
 	 		    	  enableFields("txtWifmFacility");
 	 		      }  */
 	 			//////////////////////////////////////////////alert(MocKeyid);
 	 			 processGridnew("WhatIFEntry_input.mocn","q=2&MocKeyid="+MocKeyid,"WhatifGrid","Whatifpager","Whatif", "","","WhatifDtlgridLoadComplete");		 
 	 			}
 	 			else if(title=="Hazop"){
 	 				jQuery("#hdntitle").val("Hazop");
 	 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val(); 	 	 			
 	 	 			 var initial =jQuery("#hdnInitialApproval").val();
 	 	 			var whatIfTeamValue = jQuery("#txtWifmTeam").val();//- ADDED CHANGE HERE
 	 	 			jQuery("#txtHzomTeam").val(whatIfTeamValue); //- ADDED CHANGE HERE
 	 	 		     // //////////////////////alert(initial);
 	 	 		      if(initial=="Pending"){
 	 	 		    	disableUIButton("btnAddHazop");
 	 	 		    	disableField("frmMocProject","dteHzomDate");
 	 	 		    	disableField("frmMocProject","txtHzomTeam");
 	 	 		    	disableField("frmMocProject","txtHzomFacility");
 	 	 		    	disableField("frmMocProject","txtHzomNode");
 	 	 		    	disableField("frmMocProject","txtHzomPidno");
 	 	 		    	disableField("frmMocProject","txtHzomDesignintent");
 	 	 		     setTimeout(function() {readOnlyFields('txtHzomFacility');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomTeam');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomNode');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomDesignintent');},1250);
 	 	 		      }
 	 	 		     /* else{  	 	 	

 	 	 		    	  enableUIButton("btnAddHazop");
 	 	 		    	  enableFields("dteHzomDate");
 	 	 		    	  enableFields("txtHzomTeam");
 	 	 		    	  enableFields("txtHzomFacility");
 	 	 		    	  enableFields("txtHzomNode");
 	 	 		    	  enableFields("txtHzomPidno");
 	 	 		    	  enableFields("txtHzomDesignintent");
 	 	 		      } */
 	 	 			//////////////////////////////////////////////alert(MocKeyid);
 	 	 			processGridnew("HazopEntry_input.mocn","q=2&MocKeyid="+MocKeyid,"Hazopgrid","Hazoppager","Hazop", "","","HazopDtlgridLoadComplete");		 
 	 	 	 	
 	 			}
 			
 	 	
 
 	 		
 	 		else if(title=="W/H Approval")	{
 	 			//	////////////////////////////////////////////////////alert("IFCond")
 	 			 jQuery("#hdntitle").val("W/H Approval");
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			processAjaxCalls("WhatIfHazopRecommentationsCount.mocn","&MocKeyid="+MocKeyid,"WH_successcallback","WH_Errorcallback");
 	 			processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 	 			
 	 	processGridnew("HazopApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"HazopApprovalGrid","pager","","","","HazoploadComFunction");	
 	 		//workFlow("divKznWorkFlow",false,"", "", "KZNBTS");
 	 		}
 			
else if(title=="PSSRchecklists"){
 	 			
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
 				
 				//Point No.3 - MOC details must be fetched from RFC tab in the PSSR tab. - Swetha
 				var mocDetailRfc = jQuery("#txtRfcmdetail").val();//- ADDED CHANGE HERE
 				jQuery("#txtPsrmmocdetail").val(mocDetailRfc);//- ADDED CHANGE HERE
 				//Point No.3 - MOC details must be fetched from RFC tab in the PSSR tab. - Swetha
 				
 				 jQuery("#hdntitle").val("Psschecklist");
 				processAjaxCalls("HazopApprovalCount.mocn","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","ApprovalCount_Errorcallback");
 				 var initial =jQuery("#hdnInitialApproval").val();
 				
 			 	 var hazop=	jQuery("#hdnHazopApproval").val();
 			 	var mode= jQuery('#hdnmode').val();
 			 	//////////alert(mode)
 			 	 if((initial=="Pending"||hazop=="Pending"||mode=="view")||( initial=="Closed" && hazop=="Pending" && mode=="view")){
 			 	
 			 		 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
 					 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
 					 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
 			 	 }
 	 			processGridnew("PssrChecklist_input.mocn","q=2&MocKeyid="+MocKeyid,"PSSRGrid","pager","","","","PssrGridloadComFunction"); 	
 	 		 }
 		
 	 		else if(title=="PSSRRecomnd")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("MOCReccommend");
 	 			processAjaxCalls("PSSRRecommentationsCount.mocn","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","");
 	 			processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","");
 	 			processAjaxCalls("HazopApprovalCount.mocn","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","");
 	 			var WHCount=jQuery("#hdnWHCount").val();
 	 	 		//alert("WHCount"+WHCount);
 	 	 		var initial =jQuery("#hdnInitialApproval").val();
 	 	 		//alert("initial"+initial);
 	 	 		var Hazop=jQuery("#hdnHazopApproval").val();
 	 	 		//alert("Hazop"+Hazop);
 	 	 		var PssrCount=jQuery('#hdnpssrCount').val();
 	 	 		//alert("PssrCount"+PssrCount);
 	 	 		/* if(initial=="Closed" && Hazop=="Closed" && WHCount=="create")
 	 	 		{
 	 	 			enableUIButton("btnAddNewReccommend");	
 	 	 		}
 	 	 		else{
//alert("else");
 	 	 			 disableUIButton("btnAddNewReccommend");
 	 	 		} */
 	 	 		var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//- ADDED CHANGE HERE
 	 	 		if(initial=="Closed" && Hazop=="Closed" && WHCount=="create" && !(pageMode == "view" || pageMode == "approval"))//- ADDED CHANGE HERE
 	 	 		{
 	 	 			enableUIButton("btnAddNewReccommend");	
 	 	 		}
 	 	 		else{
//alert("else");
 	 	 			 disableUIButton("btnAddNewReccommend");
 	 	 		}
 	 			
 	 		 processGridnew("PSSRRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","ReccommendloadComFunction");	
 	 		
 	 	}
 			else if(title=="W/H Reccommend")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("HazopReccommend");
 	 			 processGridnew("HazopRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	
 	 		 }
	else if(title=="MOCClosure")	{ 
 	 			
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("MOCCLosure");
 	 			processAjaxCalls("PSSRRecommentationsCount.mocn","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","PSSRRecomment_Errorcallback");
 	 			
  	 			processGridnew("MocClosure_input.mocn","q=2&MocKeyid="+MocKeyid,"MOCCGrid","pager","","","","MOCClosureloadComFunction");	
 	 		 } 
 	 		else if(title=="FinalApprovals")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();	
 processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 processAjaxCalls("HazopApprovalCount.mocn","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","ApprovalCount_Errorcallback");
 processAjaxCalls("MOCClosureCheck.mocn","&MocKeyid="+MocKeyid,"MOCClosure_successcallback","");
		 			
 	 			 jQuery("#hdntitle").val("FinalApprovals");
 	 			var MOCStatus=jQuery("#hdnFinalMaxApprovalStatus").val();
 	 			//////////////////////////alert("MOCStatusNew"+MOCStatus);
 	 			   if(MOCStatus=="Pending"){
 	 				 //  enableFields("chkMocClosed")
 	 				   jQuery("#chkMocClosed").attr("disabled",true).removeClass("ui-state-disabled");
 	 			   }
 	 			   else{
 	 				////////////////////////////////alert("Else");
 	 				   jQuery("#chkMocClosed").attr("disabled",false).removeClass("ui-state-disabled");
 	 			   }
 	 				var Status=jQuery("#hdnStatusClose").val();
 	 	 		//////////////////////alert("Status"+Status)
 	 	 			  if(Status=="MOC CLOSED"){
 	 	 				//  jQuery("#chkMocClosed").attr(":checked",true);
 	 	 				//	jQuery('#chkMocClosed').prop('checked', true);
 	 	 				//	 jQuery("#chkMocClosed").attr("disabled",true); 
 	 	 					jQuery("#chkMocClosed")
 	 	 				    .prop("checked", true)
 	 	 				    .prop("disabled", true);
 	 	 				
 	 	 				   }	
 	 	 		
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			jQuery("#hdntitle").val("FinalApprovals");
 	 		processGridnew("FinalApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"FinalApprovalGrid","pager","","docDoubleClick","","FinalloadComFunction");	
 	 	 	
 	 		}
 			
 		 	
 		 }
 		 });
 		 
 		 
function formatDate(date){
	let newDate = new Date(date);
		   

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }
 
function dteWifmDate_onSelect(date)
 {
	 var wifmDate = formatDate(date);
	var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");
	// if(wifmDate<mocDateStr)
		// vignesh -- 07sep2026
		 if (convertStringToDate(wifmDate) < convertStringToDate(mocDateStr))
	 {
		alert('What If date cannot be less than Moc Date"');
		setTimeout(function () { fillWithCurrentDate('dteWifmDate'); }, 200);
		 
	 }
	
	 
 }
 
function dteHzomDate_onSelect(date)
{
	 var hazomDate = formatDate(date);
	var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");
//	 if(hazomDate<mocDateStr) 
	// vignesh -- 07sep2026
		 if (convertStringToDate(hazomDate) < convertStringToDate(mocDateStr))
	 {
		 alert('Hazop Date cannot be less than Moc Date"');
			setTimeout(function () { fillWithCurrentDate('dteHzomDate'); }, 200);
	 }
	
	 
}
/*
function dtePsrmdate_onSelect(date)
{
	var pssrDate = formatDate(date);
	var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");
	 if(pssrDate<mocDateStr)
	 {
		 alert('PSSR date cannot be less than Moc Date"');
			setTimeout(function () { fillWithCurrentDate('dtePsrmdate'); }, 200);
	 }
	
}
 */ 
 // ------   vignesh 07 sep 2026 -----//
 function dtePsrmdate_onSelect(date)
 {
     var pssrDate = formatDate(date);
     var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");

     if (convertStringToDate(pssrDate) < convertStringToDate(mocDateStr))
     {
         alert('PSSR date cannot be less than MOC Date');

         setTimeout(function () {
             fillWithCurrentDate('dtePsrmdate');
         }, 200);

         return false;
     }
 }
 
 
 		 
 		 function PSSRRecomment_successcallback(result){
 			
 			 var MOCStatus=result.MocStatus;
 	//	//////////////////////alert("MOCStatus"+MOCStatus);
 			 
 			 if(MOCStatus<=0){
 				 jQuery('#hdnMocStatus').val(MOCStatus);
 			 }
 			
 			 
 	//	////////////////////////////////////////alert("Click");
 			var  pssrCount=result.pssrCount;
 			//////////////////////////alert(pssrCount);
 			 if(pssrCount>=1){
 				
 			
 				jQuery('#hdnpssrCount').val("view");	
 			 }
 			 else{
 				////////////////////////alert("Else");
 				//jQuery('#hdnmode').val("create");
 				jQuery('#hdnpssrCount').val("create");	
 			 }
 			
 			 
 		 }
 		 function WH_successcallback(result){
  			
 			 var MOCStatus=result.MocStatus;
 			//////////////////////////alert("MOCStatus"+MOCStatus);
 			 
 		
 			  WHCount=result.WHCount;
 			////////////////////alert(WHCount);
 			 if(WHCount>=1){
 				
 				//jQuery('#hdnmode').val("view");
 				jQuery('#hdnWHCount').val("view");	
 			 }
 			 else{
 			//	//////////////////////alert("Else");
 				//jQuery('#hdnmode').val("create");
 				jQuery('#hdnWHCount').val("create");	
 			 }
 			
 			 
 		 }
 		
 		 
 		function Approval_successcallback(result){
 			
	var  TotalCount=result.TotalApproval;
	if(typeof TotalCount=="undefined"||TotalCount==""){
		 Maxgroup=1; 
	   //////////////////////////////////////alert("In if"+Maxgroup)
	   jQuery('#hdncount').val(1);
	}
 		 	var	ApprovalCount=result.CompletedApproval;
 			////////////////////////////////////////alert("ApprovalCount"+ApprovalCount)
 		 			var MaxGroupNo=result.MaxGroupNo;
 		 	//	//////////////////////////////////////alert("MaxGroupNo"+MaxGroupNo);
 		 		var nextApprovalno=result.NextGroupNo;
 		 		//////////////////////////////////alert("nextApprovalno:::::::Initial"+nextApprovalno);
 		 		if(typeof nextApprovalno=="undefined"||nextApprovalno==""){
 		 		var InitialClosed="Closed";
 		 		
 		 		   jQuery('#hdnInitialApproval').val(InitialClosed);
 		 		}
 		 		else{
 		 			//////////////////////////////////alert("Else");
 		 			var InitialPending="Pending";
 		 			jQuery('#hdnInitialApproval').val(InitialPending);	
 		 		}
 		 		if(ApprovalCount < TotalCount){
 		 			Maxgroup= parseInt(MaxGroupNo)
 		 			////////////////////////////////////////alert("Maxgroup < IF"+Maxgroup)
 		 			jQuery('#hdncount').val(Maxgroup);	 
 		 		}
           // if(ApprovalCount==TotalCount)
 		 			//Maxgroup= parseInt(MaxGroupNo)+1; 
           if(nextApprovalno<=9){
	 			Maxgroup= nextApprovalno;
	 			
	 			////////////////////////////////////////alert("In if MaxGroup"+Maxgroup);
	 			jQuery('#hdncount').val(Maxgroup);
	 			}
 		 			////////////////////////////////////////alert("Maxgroup"+Maxgroup);
 		 		//	jQuery('#hdncount').val(Maxgroup);	 
 		 		 }	
 		
 		function HazopApproval_successcallback(result){
 			
 			var  TotalCount=result.HazopTotalApproval;
 			////////////////////alert("TotalCount"+TotalCount);
 			if(typeof TotalCount=="undefined"||TotalCount==""){
	 			 Maxgroup=1; 
	 		   //////////////////////////////////////alert("In if"+Maxgroup)
	 		   jQuery('#hdnHazopcount').val(Maxgroup);
	 		}
 	        var  ApprovalCount=result.HazopCompletedApproval;
 	   //////////////////alert("ApprovalCount"+ApprovalCount);
            var MaxGroupNo=result.HazopMaxGroupNo;
        ////////////////////alert("MaxGroupNo"+MaxGroupNo);
            var nextApprovalno=result.HazopNextGroupNo;
            //////////////////alert("nextApprovalno"+nextApprovalno);
            if(typeof nextApprovalno=="undefined"||nextApprovalno==""){
 		 		var HazopClosed="Closed";
 		 		
 		 		   jQuery('#hdnHazopApproval').val(HazopClosed);
 		 		}
 		 		else{
 		 			//////////////////alert("Else");
 		 			var HazopPending="Pending";
 		 			jQuery('#hdnHazopApproval').val(HazopPending);	
 		 		}
            if(ApprovalCount < TotalCount){
    	 
    	Maxgroup= parseInt(MaxGroupNo)
 	  jQuery('#hdnHazopcount').val(Maxgroup);	 
 		 		 		
                                        }
 	       //if(ApprovalCount==TotalCount)
 	    	   
 		 		 			
 		   if(nextApprovalno<=9){
 			 			Maxgroup= nextApprovalno;
 			 			////////////////////////////////////alert("Inse"+Maxgroup)
 			 			jQuery('#hdnHazopcount').val(Maxgroup);
 			 			}
 		
 		 		 		 }		 

 		function FinalApproval_successcallback(result){
 			
 			var  TotalCount=result.FinalTotalApproval;
 			var  MaxApprovalStatus=result.FinalMaxGroupStatus;
 			//////////////////////////alert("MaxApprovalStatus:::"+MaxApprovalStatus);
 			if(MaxApprovalStatus=='-'){
 				//////////////////////////alert("inside")
 				var FAStatus="Pending"
 				 jQuery('#hdnFinalMaxApprovalStatus').val(FAStatus);	
 			}
 			else{
 				var FAStatus="Completed"
 	 				 jQuery('#hdnFinalMaxApprovalStatus').val(FAStatus);	
 			}
 		 		 ////////////////////////////////////////alert("TotalCount"+TotalCount);
 		 		if(typeof TotalCount=="undefined"||TotalCount==""){
 		 			 Maxgroup=1; 
 		 		   //////////////////////////////////////alert("In if"+Maxgroup)
 		 		   jQuery('#hdnFinalcount').val(Maxgroup);
 		 		}
 		 		 	var	ApprovalCount=result.FinalCompletedApproval;
 		 			////////////////////////////////////////alert("ApprovalCount"+ApprovalCount)
 		 		 			var MaxGroupNo=result.FinalMaxGroupNo;
 		 		 	
 		 		 		var nextApprovalno=result.FinalNextGroupNo;
 		 		 	
 		 		 		if(ApprovalCount < TotalCount){
 		 		 			Maxgroup= parseInt(MaxGroupNo)
 		 		 			////////////////////////////////////////alert("Maxgroup < IF"+Maxgroup)
 		 		 			jQuery('#hdnFinalcount').val(Maxgroup);	 
 		 		 		}
 		           //if(ApprovalCount==TotalCount)
 		 		 			//Maxgroup= parseInt(MaxGroupNo)+1;
 		           if(nextApprovalno<=9){
 			 			Maxgroup= nextApprovalno;
 			 			//////////////////////////////////////alert("In if MaxGroup"+Maxgroup);
 			 			jQuery('#hdnFinalcount').val(Maxgroup);
 			 			}
 		 		 			////////////////////////////////////////alert("Maxgroup"+Maxgroup);
 		 		 		//	jQuery('#hdncount').val(Maxgroup);	 
 		 		 		 }		 

	function MOCClosure_successcallback(result){
 			
 		var  ClosureCount=result.ClosureCount;
 		////////alert("ClosureCount:::"+ClosureCount);
 		
 		 if(ClosureCount >= 1){
 		jQuery('#hdnClosurecount').val("create"); 
 		}
else{
 	    jQuery('#hdnClosurecount').val("view");
 		}
 		}	
 		
function addRowPssr(row)
{
	////////////////////////////////////////////////////alert("Inside")
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
			 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
			 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#PSSRReccomendGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
		 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
		 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#PSSRReccomendGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

//Query("#btnAddHazop").click(function(){
/* 	jQuery(document).on("click", "#btnAddHazop", function(){
		// alert("Working ✅");
	var row = jQuery("#Hazopgrid").jqGrid("getDataIDs");
	addRowHazop(row);
});	 */

	
	jQuery("#btnAddHazop").click(function(){
//		//////////////////////////////////////////////alert("Click");
		var row = jQuery("#Hazopgrid").jqGrid("getDataIDs");
		//alert("url"+row);
		addRowHazop(row);
	});
	
	jQuery("#btnUploadExcel").click(function(){	
		  var menuIdin=jQuery('#genmstmenuid').val();	
		  var fileUp=jQuery(".qq-upload-list").html();
		  if(!(fileUp.length>0)){
		  alert("Select File To Upload");
		  return false;
		 }
		 processAjaxCalls("file_save.mastertblconfig?genmstmenuid="+menuIdin,"","successcallback");
		});



/* function addRowHazop(row){

	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			 txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
			 cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
		jQuery("#Hazopgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
		 	cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
		jQuery("#Hazopgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
		//NewHazopGridLoadCom();
	 }
} */

	function addRowHazop(row){

		 if ( row == null || row == '' || parseInt(row) <= 0) {
			 var emptyItem =[{hdnmohmkeyid:" ",hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
				 txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
				 cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
			jQuery("#Hazopgrid").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++)
					lastRow = row[i];
			var emptyItem =[{hdnmohmkeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
				txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
			 	cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
			jQuery("#Hazopgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
			//NewHazopGridLoadCom();
		 }
	}

jQuery("#btnAddwhatif").click(function(){
//	//////////////////////////////////////////////alert("Click");
	var row = jQuery("#WhatifGrid").jqGrid("getDataIDs");
	addRowWhatIf(row);
});
/* function addRowWhatIf(row){
	
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
			 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
			 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
			 	cmbWifdSeverity2:" ",txtWifdRisk2:" ",txtWifdRemarks:" "}];
		jQuery("#WhatifGrid").jqGrid('addRowData',1, emptyItem[0]);
		//NewWhatifGridLoadCom();
	 }	
	 else
	 {
		// //////////////////////alert("Inside 1");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
		 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
		 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
		 	cmbWifdSeverity2:" ",txtWifdRisk2:" " ,txtWifdRemarks:" "}];
		  jQuery("#WhatifGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
      }
		} */
		
		
function addRowWhatIf(row){
	
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnWifmKeyid:" ",hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
			 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
			 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
			 	cmbWifdSeverity2:" ",txtWifdRisk2:" ",txtWifdRemarks:" "}];
		jQuery("#WhatifGrid").jqGrid('addRowData',1, emptyItem[0]);
		//NewWhatifGridLoadCom();
	 }	
	 else
	 {
		// //////////////////////alert("Inside 1");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnWifmKeyid:" ",hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
		 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
		 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
		 	cmbWifdSeverity2:" ",txtWifdRisk2:" " ,txtWifdRemarks:" "}];
		  jQuery("#WhatifGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
     }
		} 
		
function NewHazopGridLoadCom(){
	
    var row = jQuery("#Hazopgrid").jqGrid('getDataIDs');
 ////////////////////////alert(row);
	 var cm = jQuery("#Hazopgrid").jqGrid("getGridParam", "colModel");
	////////////////////////alert("inside "+row);
	 for(var i=0;i<row.length;i++)
	 {
		
			jQuery('#Hazopgrid').setSelection(row[i], true);
			 jQuery('input:checkbox[id=jqgh_Hazopgrid_'+row[i]+']').attr('checked',true);
			    jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','1');
		  }

	 }
/* function WhatifGrid_selectRow(rowId)
{
	var jqGridId="WhatifGrid";

	jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
		
} */

/* function WhatifGrid_selectRow(rowId)
{
	var jqGridId="WhatifGrid";
	// disableField("frmMocProject","cmbWifdRisk1_"+jqGridId+"_"+rowId);
	//disableField("frmMocProject","cmbWifdRisk2_"+jqGridId+"_"+rowId); 
	
	
	
	jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId).datebox({
	    
	    onSelect: function(date) {

	        var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");

	        // convert string to Date
	        var mocDate = new Date(mocDateStr);

	        // selected date
	        var selectedDate = date;

	        if (selectedDate < mocDate) {

	            alert("Target Date should not be less than MOC Date");

	            //jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId)
	                //.datebox("setValue", "");
	            
	            fillWithCurrentDate("dteMocrTargetDate_WhatifGrid_"+rowid);
	            

	            return false;
	        }
	    }
	});
	
	var userActionPlanResposiblityId = jQuery("#hdnuserid").val();
	
	//alert("mocedate"+mocDate);
	//alert("actionPlanTargetDate"+actionPlanTargetDate);
	
	var empId = jQuery("#cmbMocrResponsibility_"+jqGridId+"_"+rowId)
    .combobox('getValue');

	
	var status = jQuery("#cmbMocrStatus_WhatifGrid"+"_"+rowId).val();
	//alert("status "+status);
	/* Commenting for Action plan 08june2026
	if(status == "P")
	{
		jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId)
	    .prop("disabled", true);
		jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId)
	    .prop("disabled", true);
	
	}
jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
////////////////////////////////////////////////alert("onLoadSuccess:function");		

var prob=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
var sev=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);

var calps=parseInt(prob)*parseInt(sev);

}});

} */
	
	function WhatifGrid_selectRow(rowId)
	{
		var jqGridId="WhatifGrid";
		/* disableField("frmMocProject","cmbWifdRisk1_"+jqGridId+"_"+rowId);
		disableField("frmMocProject","cmbWifdRisk2_"+jqGridId+"_"+rowId); */
		
		
		
		//var rowData = jQuery("#WhatifGrid").jqGrid('getRowData', rowId);


		var rowData = jQuery("#WhatifGrid").jqGrid('getRowData', rowId);

		console.log("statusdetail =", rowData.statusdetail);
		console.log("rowData", rowData);
		
		jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId).datebox({
		    
		    onSelect: function(date) {

		        var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");

		        // convert string to Date
		        var mocDate = new Date(mocDateStr);

		        // selected date
		        var selectedDate = date;

		        if (selectedDate < mocDate) {

		            alert("Target Date should not be less than MOC Date");

		            //jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId)
		                //.datebox("setValue", "");
		            
		            fillWithCurrentDate("dteMocrTargetDate_WhatifGrid_"+rowid);
		            

		            return false;
		        }
		    }
		});
		
		var userActionPlanResposiblityId = jQuery("#hdnuserid").val();
		
		//alert("mocedate"+mocDate);
		//alert("actionPlanTargetDate"+actionPlanTargetDate);
		
		var empId = jQuery("#cmbMocrResponsibility_"+jqGridId+"_"+rowId)
	    .combobox('getValue');

		/* Commenting for Action plan 08june2026
		if(empId!=userActionPlanResposiblityId)
		{
			
			
			//jQuery("#cmbMocrStatus_Hazopgrid_" +rowid).combobox('setValue', '').combobox('disable');
			jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId)
		    .prop("disabled", true);
		}
		*/
		var rowData = jQuery("#WhatifGrid").jqGrid('getRowData', rowId);//CHANGES

		jQuery("#cmbMocrStatus_WhatifGrid_" + rowId)
		    .val(rowData.statusdetail);//CHANGES
		var status = jQuery("#cmbMocrStatus_WhatifGrid"+"_"+rowId).val();
		
		//alert("status "+status);
		/* Commenting for Action plan 08june2026
		if(status == "P")
		{
			jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId)
		    .prop("disabled", true);
			jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId)
		    .prop("disabled", true);
		
		}
		*/
		jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
			////////////////////////////////////////////////alert("onLoadSuccess:function");		
			
			var prob=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
			var sev=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
		
			var calps=parseInt(prob)*parseInt(sev);
			
		}});
			
	}  
	
	
	

/* function cmbWifdLikeHood1_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	calPS(rowId);
}
function cmbWifdSeverity1_WhatifGrid_onSelect(record,rowId){
	calPS(rowId);
}

function calPS(rowId){
	var prob=getFieldValue("cmbWifdLikeHood1_WhatifGrid_"+rowId);
	////////////////////////////////////////////////alert("prob"+prob);
	var sev=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	////////////////////////////////////////////////alert("sev"+sev);
	processAjaxCalls('getRiskLevel.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal_OnSuccess','riskVal_OnError');
} */

function cmbWifdLikeHood1_WhatifGrid_onSelect(record,rowId){		 

    var prob = record.id;

    var sev = jQuery("#cmbWifdSeverity1_WhatifGrid_"+rowId).val();

    calPS(prob, sev, rowId);
}

function cmbWifdSeverity1_WhatifGrid_onSelect(record,rowId){

    var sev = record.id;

    var prob = jQuery("#cmbWifdLikeHood1_WhatifGrid_"+rowId).val();

    calPS(prob, sev, rowId);
}

function calPS(prob, sev, rowId){

    console.log("prob", prob);
    console.log("sev", sev);

    processAjaxCalls(
        'getRiskLevel.mocn',
        'rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,
        'riskVal_OnSuccess',
        'riskVal_OnError'
    );
}
function riskVal_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	////////////////////////////////////////////////alert("the riskVal"+riskVal);
	var jqGridId="WhatifGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtWifdRisk1',riskVal);
    setFieldValue("#txtWifdRisk1_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
}

/* function cmbWifdLikeHood2_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callPS(rowId);
}
function cmbWifdSeverity2_WhatifGrid_onSelect(record,rowId){
	callPS(rowId);
}


function callPS(rowId){
	var jqGridId="WhatifGrid";
	
	var WhatifLikelyhood1=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(WhatifLikelyhood1)
	
	////////////////////////////////alert(WhatifSeverity1)
	var Whatiflikelyhood2=jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(Whatiflikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(Whatiflikelyhood2>=0){
	if((WhatifLikelyhood1>=Whatiflikelyhood2)){
		////////////////////////////////alert("IF");
	var prob=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
	processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
	}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Likelyhood & Severity should be Less than or Equal to Likelyhood & Severity of Without Safeguards  ");
	return false;	
	}
	}
	var WhatifSeverity1=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var WhatifSeverity2=jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(WhatifSeverity2>=0){
	if(WhatifSeverity1>=WhatifSeverity2){
		////////////////////////////////alert(" SevirityIF");
		var prob=getFieldValue("cmbWifdLikeHood2_WhatifGrid_"+rowId);
//////////////////////////////alert(prob)
		var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
//////////////////////////////alert(sev)
		processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
			
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & Likelyhood  should be Less than or Equal to severity & Likelyhood of Without Safeguards ");
			return false;	
			}

	}
	
	} */
	
/* function cmbWifdLikeHood2_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	//callPS(rowId);
	var prob = record.id;

    var sev = jQuery("#cmbWifdSeverity1_WhatifGrid_"+rowId).val();

    callPS(prob, sev, rowId);
	
}
function cmbWifdSeverity2_WhatifGrid_onSelect(record,rowId){
	//callPS(rowId);
	
	 var sev = record.id;

	    var prob = jQuery("#cmbWifdLikeHood1_WhatifGrid_"+rowId).val();

	    callPS(prob, sev, rowId);
}
 */
function cmbWifdLikeHood2_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	//callPS(rowId);
	//var prob = record.id;

    //var sev = jQuery("#cmbWifdSeverity2_WhatifGrid_"+rowId).val();
    
    var prob = record.id;
	var probVal = record.text;
    var sev = jQuery("#cmbWifdSeverity2_WhatifGrid_"+rowId).val();
    var sevVal = jQuery("#cmbWifdSeverity2_WhatifGrid_"+rowId).combobox('getText');

  //var sev = jQuery("#cmbWifdSeverity2_WhatifGrid_"+rowId).val();
    //callPS(prob, sev, rowId);
    //callPSNew(prob,probVal ,sev,sevVal, rowId);
    callPSNew(prob, probVal, sev, sevVal, rowId);


	
}
function cmbWifdSeverity2_WhatifGrid_onSelect(record,rowId){
	//callPS(rowId);
	
	 //var sev = record.id;

	    //var prob = jQuery("#cmbWifdLikeHood2_WhatifGrid_"+rowId).val();
	    
	    //console.log(record+"record");
	 var sev = record.id;
	 var sevVal = record.text;

	    var prob = jQuery("#cmbWifdLikeHood2_WhatifGrid_"+rowId).val();
	    
	    var probVal = jQuery("#cmbWifdLikeHood2_WhatifGrid_"+rowId).combobox('getText');

	    //callPS(prob, sev, rowId);

	    //callPS(prob, sev, rowId);
	    //callPSNew(prob,probVal ,sev,sevVal, rowId);
	    callPSNew(prob, probVal, sev, sevVal, rowId);


}
function callPSNew(prob,probVal ,sev,sevVal, rowId)
{
	var jqGridId="WhatifGrid";
	console.log(prob);
	console.log(sev);
	
	console.log(probVal);
	console.log(sevVal);
	
	var WhatifLikelyhood1=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
	//var WhatifLikelyhood1= jQuery("#cmbWifdLikeHood2_WhatifGrid_"+rowId).val();
	////////////////////////////////alert(WhatifLikelyhood1)
	
	////////////////////////////////alert(WhatifSeverity1)
	//var Whatiflikelyhood2=jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(Whatiflikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	
	//console.log(WhatifLikelyhood1);
	//console.log(Whatiflikelyhood2);
	
	
	if(probVal>=0){
	if((WhatifLikelyhood1>=probVal)){
		////////////////////////////////alert("IF");
	//var prob=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
	processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
	}
	else
	{
	////////////////////////////////alert("else");
	alert("Likelyhood & Severity should be Less than or Equal to Likelyhood & Severity of Without Safeguards  ");
	  setTimeout(function () {
          jQuery("#cmbWifdLikeHood2_WhatifGrid_" + rowId).combobox('setValue', '');
          jQuery("#cmbWifdLikeHood2_WhatifGrid_" + rowId).combobox('setText', '');
      }, 0);
	return false;	
	}
	}
	var WhatifSeverity1=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var WhatifSeverity2=jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(sevVal>=0){
	if(WhatifSeverity1>=sevVal){
		////////////////////////////////alert(" SevirityIF");
		//var prob=getFieldValue("cmbWifdLikeHood2_WhatifGrid_"+rowId);
//////////////////////////////alert(prob)
		//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
//////////////////////////////alert(sev)
		processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
			
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & Likelyhood  should be Less than or Equal to severity & Likelyhood of Without Safeguards ");
			setTimeout(function () {
		          jQuery("#cmbWifdSeverity2_WhatifGrid_" + rowId).combobox('setValue', '');
		          jQuery("#cmbWifdSeverity2_WhatifGrid_" + rowId).combobox('setText', '');
		      }, 0);
			return false;	
			}

	}
	
	}
	
// function callPSNew(prob,probVal ,sev,sevVal, rowId)
// {
// 	var jqGridId="WhatifGrid";
// 	console.log(prob);
// 	console.log(sev);
	
// 	console.log(probVal);
// 	console.log(sevVal);
	
// 	var WhatifLikelyhood1=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
// 	//var WhatifLikelyhood1= jQuery("#cmbWifdLikeHood2_WhatifGrid_"+rowId).val();
// 	////////////////////////////////alert(WhatifLikelyhood1)
	
// 	////////////////////////////////alert(WhatifSeverity1)
// 	//var Whatiflikelyhood2=jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
// 	////////////////////////////////alert(Whatiflikelyhood2);
	
// 	////////////////////////////////alert(WhatifSeverity2)
	
// 	//console.log(WhatifLikelyhood1);
// 	//console.log(Whatiflikelyhood2);
	
	
// 	if(probVal>=0){
// 	if((WhatifLikelyhood1>=probVal)){
// 		////////////////////////////////alert("IF");
// 	//var prob=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
// 	//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
// 	processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
// 	}
// 	else
// 	{
// 	////////////////////////////////alert("else");
// 	alert("Likelyhood & Severity should be Less than or Equal to Likelyhood & Severity of Without Safeguards  ");
// 	return false;	
// 	}
// 	}
// 	var WhatifSeverity1=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
// 	var WhatifSeverity2=jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
// 	if(sevVal>=0){
// 	if(WhatifSeverity1>=sevVal){
// 		////////////////////////////////alert(" SevirityIF");
// 		//var prob=getFieldValue("cmbWifdLikeHood2_WhatifGrid_"+rowId);
// //////////////////////////////alert(prob)
// 		//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
// //////////////////////////////alert(sev)
// 		processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
			
// 	} 
// 		 else
// 			{
// 			////////////////////////////////alert("else");
// 			popupCommonErrorMsg("Severity & Likelyhood  should be Less than or Equal to severity & Likelyhood of Without Safeguards ");
// 			return false;	
// 			}

// 	}
	
// 	}
function callPS(prob, sev, rowId){
	var jqGridId="WhatifGrid";
	console.log(prob);
	console.log(sev);
	var WhatifLikelyhood1=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(WhatifLikelyhood1)
	
	////////////////////////////////alert(WhatifSeverity1)
	var Whatiflikelyhood2=jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(Whatiflikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(Whatiflikelyhood2>=0){
	if((WhatifLikelyhood1>=Whatiflikelyhood2)){
		////////////////////////////////alert("IF");
	//var prob=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
	processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
	}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Likelyhood & Severity should be Less than or Equal to Likelyhood & Severity of Without Safeguards  ");
	return false;	
	}
	}
	var WhatifSeverity1=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var WhatifSeverity2=jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(WhatifSeverity2>=0){
	if(WhatifSeverity1>=WhatifSeverity2){
		////////////////////////////////alert(" SevirityIF");
		//var prob=getFieldValue("cmbWifdLikeHood2_WhatifGrid_"+rowId);
//////////////////////////////alert(prob)
		//var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
//////////////////////////////alert(sev)
		processAjaxCalls('getRiskLevel1.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
			
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & Likelyhood  should be Less than or Equal to severity & Likelyhood of Without Safeguards ");
			return false;	
			}

	}
	
	}
function riskVal1_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	//////////////////////////////alert("risk"+riskVal);
	var jqGridId="WhatifGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtWifdRisk2',riskVal);
	
   
	//var calps=parseInt(prob)*parseInt(sev);
    var WhatifRisk1=jQuery("#WhatifGrid").jqGrid('getCell',rowId,"txtWifdRisk1");
    var WhatifRisk2=jQuery("#WhatifGrid").jqGrid('getCell',rowId,"txtWifdRisk2");
    if(WhatifRisk1==WhatifRisk2){
    	//////////////////////////////alert("Risk value Should not greater that Risk Value Without Safeguard");
       // return false;
        jQuery("#txtWifdRisk2_"+jqGridId+"_"+rowId).val('');
        return false;
}
}

function QuestionaireGrd_selectRow(rowId)

{
	
	//var flag = jQuery(this).find('#QuestionaireGrd'+chkRfcqresponseY+' input[type=checkbox]').prop('checked');
	 var selected = this.jQuery.find(":checkbox").prop("checked");
	////////////////////////////////////////alert("flag"+selected)
    {
	////////////////////////////////////////alert("Inside")
	  }
	/* var Yes=getFieldValue("chkRfcqresponseY_QuestionaireGrd_"+rowId); */
	////////////////////////////////////////alert("Yes"+Yes);

}
/* function Hazopgrid_selectRow(rowId)
{
	var jqGridId="Hazopgrid";
	//disableField("frmMocProject","cmbMohdRisk1_"+jqGridId+"_"+rowId);
	//disableField("frmMocProject","cmbMohdRisk2_"+jqGridId+"_"+rowId);
	//var txt=1;
	jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
		
} */

/* function Hazopgrid_selectRow(rowId)
{
	var jqGridId="Hazopgrid";
	//disableField("frmMocProject","cmbMohdRisk1_"+jqGridId+"_"+rowId);
	//disableField("frmMocProject","cmbMohdRisk2_"+jqGridId+"_"+rowId);
	//var txt=1;
	
	
	jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId).datebox({
	    
	    onSelect: function(date) {

	        var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");

	        // convert string to Date
	        var mocDate = new Date(mocDateStr);

	        // selected date
	        var selectedDate = date;

	        if (selectedDate < mocDate) {

	            alert("Target Date should not be less than MOC Date");

	            //jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId)
	                //.datebox("setValue", "");
	            
	            fillWithCurrentDate("dteMocrTargetDate_WhatifGrid_"+rowid);
	            

	            return false;
	        }
	    }
	});
	
	var empId = jQuery("#cmbMocrResponsibility_"+jqGridId+"_"+rowId)
    .combobox('getValue');
	var userActionPlanResposiblityId = jQuery("#hdnuserid").val();
   
	jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
	
	
		
} */

function Hazopgrid_selectRow(rowId)
{
	var jqGridId="Hazopgrid";
	//disableField("frmMocProject","cmbMohdRisk1_"+jqGridId+"_"+rowId);
	//disableField("frmMocProject","cmbMohdRisk2_"+jqGridId+"_"+rowId);
	//var txt=1;
	
	
	jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId).datebox({
	    
	    onSelect: function(date) {

	        var mocDateStr = jQuery("#dteRfcmdate").datebox("getValue");

	        // convert string to Date
	        var mocDate = new Date(mocDateStr);

	        // selected date
	        var selectedDate = date;

	        if (selectedDate < mocDate) {

	            alert("Target Date should not be less than MOC Date");

	            //jQuery("#dteMocrTargetDate_" + jqGridId + "_" + rowId)
	                //.datebox("setValue", "");
	            
	            fillWithCurrentDate("dteMocrTargetDate_WhatifGrid_"+rowid);
	            

	            return false;
	        }
	    }
	});
	
	var empId = jQuery("#cmbMocrResponsibility_"+jqGridId+"_"+rowId)
    .combobox('getValue');
	var userActionPlanResposiblityId = jQuery("#hdnuserid").val();
   /* Commenting for Action plan 08june2026
	if(empId!=userActionPlanResposiblityId)
	{
		
		
		//jQuery("#cmbMocrStatus_Hazopgrid_" +rowid).combobox('setValue', '').combobox('disable');
		jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId)
	    .prop("disabled", true);
	}
	*/
	var rowData = jQuery("#Hazopgrid").jqGrid('getRowData', rowId);//CHANGES

	jQuery("#cmbMocrStatus_Hazopgrid_" + rowId)
	    .val(rowData.statusdetail);//CHANGES
	
	
	jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
	
	
		
}

/* function cmbMohdLikeHood1_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callHazopPS(rowId);
}
function cmbMohdSeverity1_Hazopgrid_onSelect(record,rowId){
	callHazopPS(rowId);
} */
/* function cmbRasdRisklevelid_WhatifGrid_onSelect(record,rowId){
	
} */
/* 
function callHazopPS(rowId){
	var prob=getFieldValue("cmbMohdLikeHood1_Hazopgrid_"+rowId);
	////////////////////////////////////////////////alert("prob"+prob);
	var sev=getFieldValue("cmbMohdSeverity1_Hazopgrid_"+rowId);
	////////////////////////////////////////////////alert("sev"+sev);
	processAjaxCalls('getRiskLevel2.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal2_OnSuccess','riskVal2_OnError');
} */

 function cmbMohdLikeHood1_Hazopgrid_onSelect(record,rowId){

    var prob = record.id;

    var sev = jQuery("#cmbMohdSeverity1_Hazopgrid_"+rowId).val();

    callHazopPS(prob, sev, rowId);
}

function cmbMohdSeverity1_Hazopgrid_onSelect(record,rowId){

    var sev = record.id;

    var prob = jQuery("#cmbMohdLikeHood1_Hazopgrid_"+rowId).val();

    callHazopPS(prob, sev, rowId);
} 



function callHazopPS(prob, sev, rowId){

    console.log("prob", prob);
    console.log("sev", sev);

    processAjaxCalls(
        'getRiskLevel2.mocn',
        'rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,
        'riskVal2_OnSuccess',
        'riskVal2_OnError'
    );
}
function riskVal2_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	var jqGridId="Hazopgrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtMohdRisk1',riskVal);
    setFieldValue("#txtMohdRisk1_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
}

/* function cmbMohdLikeHood2_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callHazop1PS(rowId);
}
function cmbMohdSeverity2_Hazopgrid_onSelect(record,rowId){
	callHazop1PS(rowId);
}

function callHazop1PS(rowId){
	

	var jqGridId="Hazopgrid";
	var HazopLikelyhood1=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
//////////////////////////////alert(HazopLikelyhood1);
	

	var HazopLikelyhood2=jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	//////////////////////////////alert(HazopLikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(HazopLikelyhood2>=0){
	if((HazopLikelyhood1>=HazopLikelyhood2)){
		////////////////////////////////alert("IF");
		var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');
}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Severity & Likelyhood should be Less than or Equal to Severity & Likelyhood of Without Safeguards  ");
	return false;	
	}
	}
	var HazopSeverity1=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopSeverity2=jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(HazopSeverity2>=0){
	if(HazopSeverity1>=HazopSeverity2){
		////////////////////////////////alert(" SevirityIF");
	var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');	
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & LikelyHood should be Less than or Equal to severity  & Likelyhood of Without Safeguards ");
			return false;	
			}
	}
 
 } */
 
/* function cmbMohdLikeHood2_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	//callHazop1PS(rowId);
	var prob = record.id;

    var sev = jQuery("#cmbMohdSeverity1_Hazopgrid_"+rowId).val();

    callHazop1PS(prob, sev, rowId);
}
function cmbMohdSeverity2_Hazopgrid_onSelect(record,rowId){
	//callHazop1PS(rowId);
	
	 var sev = record.id;

	    var prob = jQuery("#cmbMohdLikeHood1_Hazopgrid_"+rowId).val();

	    callHazop1PS(prob, sev, rowId);
} */
/*
function cmbMohdLikeHood2_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	//callHazop1PS(rowId);
	var prob = record.id;

    var sev = jQuery("#cmbMohdSeverity2_Hazopgrid_"+rowId).val();

    callHazop1PS(prob, sev, rowId);
}
function cmbMohdSeverity2_Hazopgrid_onSelect(record,rowId){
	//callHazop1PS(rowId);
	
	 var sev = record.id;

	    var prob = jQuery("#cmbMohdLikeHood2_Hazopgrid_"+rowId).val();

	    callHazop1PS(prob, sev, rowId);
}

*/
function cmbMohdLikeHood2_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	//callHazop1PS(rowId);
	var prob = record.id;
	var probVal = record.text;
	
    var sev = jQuery("#cmbMohdSeverity2_Hazopgrid_"+rowId).val();
    var sevVal = jQuery("#cmbMohdSeverity2_Hazopgrid_"+rowId).combobox('getText');
    
  //callHazop1PS(prob, sev, rowId);
    callHazop1PSNew(prob, probVal, sev, sevVal, rowId);
}
function cmbMohdSeverity2_Hazopgrid_onSelect(record,rowId){
	//callHazop1PS(rowId);
	
	 var sev = record.id;
	 var sevVal = record.text;

	    var prob = jQuery("#cmbMohdLikeHood2_Hazopgrid_"+rowId).val();
	    var probVal = jQuery("#cmbMohdLikeHood2_Hazopgrid_"+rowId).combobox('getText');

	    //callHazop1PS(prob, sev, rowId);
	    callHazop1PSNew(prob, probVal, sev, sevVal, rowId);
}
function callHazop1PSNew(prob,probVal ,sev,sevVal, rowId){
	

	var jqGridId="Hazopgrid";
	var HazopLikelyhood1=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
//////////////////////////////alert(HazopLikelyhood1);
	

	//var HazopLikelyhood2=jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	//////////////////////////////alert(HazopLikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(probVal>=0){
	if((HazopLikelyhood1>=probVal)){
		////////////////////////////////alert("IF");
		//var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		//var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');
}
	else
	{
	////////////////////////////////alert("else");
	alert("Severity & Likelyhood should be Less than or Equal to Severity & Likelyhood of Without Safeguards  ");
	setTimeout(function () {
        jQuery("#cmbMohdLikeHood2_Hazopgrid_" + rowId).combobox('setValue', '');
        jQuery("#cmbMohdLikeHood2_Hazopgrid_" + rowId).combobox('setText', '');
    }, 0);
	return false;	
	}
	}
	var HazopSeverity1=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopSeverity2=jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(sevVal>=0){
	if(HazopSeverity1>=sevVal){
		////////////////////////////////alert(" SevirityIF");
	//var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		//var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');	
	} 
		 else
			{
			////////////////////////////////alert("else");
			alert("Severity & LikelyHood should be Less than or Equal to severity  & Likelyhood of Without Safeguards ");
			setTimeout(function () {
		          jQuery("#cmbMohdSeverity2_Hazopgrid_" + rowId).combobox('setValue', '');
		          jQuery("#cmbMohdSeverity2_Hazopgrid_" + rowId).combobox('setText', '');
		      }, 0);
			return false;	
			}
	}
 
 }
function callHazop1PS(prob, sev, rowId){
	

	var jqGridId="Hazopgrid";
	var HazopLikelyhood1=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
//////////////////////////////alert(HazopLikelyhood1);
	

	var HazopLikelyhood2=jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	//////////////////////////////alert(HazopLikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(HazopLikelyhood2>=0){
	if((HazopLikelyhood1>=HazopLikelyhood2)){
		////////////////////////////////alert("IF");
		//var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		//var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');
}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Severity & Likelyhood should be Less than or Equal to Severity & Likelyhood of Without Safeguards  ");
	return false;	
	}
	}
	var HazopSeverity1=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopSeverity2=jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(HazopSeverity2>=0){
	if(HazopSeverity1>=HazopSeverity2){
		////////////////////////////////alert(" SevirityIF");
	//var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		//var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.mocn','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');	
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & LikelyHood should be Less than or Equal to severity  & Likelyhood of Without Safeguards ");
			return false;	
			}
	}
 
 }
function riskVal3_OnSuccess(result){	
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	////////////////////////////////////////////alert(riskVal);
	var jqGridId="Hazopgrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtMohdRisk2',riskVal);
    setFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
	//var Risk1=getFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId);
	var HazopRisk1=jQuery("#Hazopgrid").jqGrid('getCell',rowId,"txtMohdRisk1");
	//////////////////////////////////////////alert("Risk1Risk1"+Risk1);
	var HazopRisk2=jQuery("#Hazopgrid").jqGrid('getCell',rowId,"txtMohdRisk2");	
	//////////////////////////////////////////alert("Risk2"+Risk2);
    if(HazopRisk1==HazopRisk2){
    //////////////////////////////alert("Risk value Should not greater that Risk Value Without Safeguard");

    }
    
}
jQuery("#btnoExcelView").click(function(){

	//alert(1234);
	var MocKeyid=jQuery("#txtRfcmKeyid").val();
	var DMTId= jQuery("#cmbrfcmdmtid").combobox("getValue");
	
	var JHId=jQuery("#cmbRfcmjhid").combobox("getValue");
	
    var flid=jQuery('#hdnflid').val();
   //alert("flid"+flid);
    var Suggestion=jQuery("#txtKzbnKaizen").val();
 //alert("Suggestion  "+Suggestion);
    var SuggestionId=jQuery("#txtRfcmsuggestionid").val();
  //alert("SuggestionId"+SuggestionId);
    var MOCDate=jQuery("#dteRfcmdate").val();// datebox('getValue');
   //alert("MOCDate"+MOCDate);
   var Title=jQuery("#txtRfcmtitle").val();
    var Detail=jQuery("#txtRfcmdetail").val();
   //alert(Detail+"111111");
    var PCHange=jQuery("#txtRfcmdescription").val();
   //alert(PCHange+".....");
    var Type = jQuery("#cboRfcmtype").val();
  //alert("Type"+Type);
    var Nature = jQuery("#cboRfcmnature").val();
   // //alert("Nature"+Nature);
    var plrtype = getComboBoxText("cmbRfcmempid");
    var Initiator=plrtype.substring(0,plrtype.indexOf("-"));
    //alert(Initiator);
    var Facility=jQuery("#txtWifmFacility").val();
    var Team=jQuery("#txtWifmTeam").val();
    var WhatifDate=jQuery("#dteWifmDate").val();//datebox('getValue');
//alert(569);
var HazopFacility=jQuery("#txtHzomFacility").val();
var HazopTeam=jQuery("#txtHzomTeam").val();
var HazopNode=jQuery("#txtHzomNode").val();
var HazopDesign=jQuery("#txtHzomDesignintent").val();
//alert(17895);

var HazopDate=jQuery("#dteHzomDate").val();//datebox('getValue');
var pidNo=jQuery("#txtHzomPidno").val();

var PssrFacility=jQuery("#txtPsrmprocess").val();
var MOCDetails=jQuery("#txtPsrmmocdetail").val();
//alert(7895);
	window.open("MOCExcelView_Excelview.mocn?MocKeyid="+MocKeyid+"&DMTId="+DMTId+"&JHId="+JHId+"&flid="+flid+"&Suggestion="+Suggestion+"&MOCDate="+MOCDate+"&Title="+Title+"&Nature="+Nature+"&Initiator="+Initiator+"&SuggestionId="+SuggestionId+"&PCHange="+PCHange+"&Detail="+Detail+"&Type="+Type+"&WhatifDate="+WhatifDate+"&Team="+Team+"&Facility="+Facility+"&HazopFacility="+HazopFacility+"&HazopTeam="+HazopTeam+"&HazopNode="+HazopNode+"&HazopDesign="+HazopDesign+"&HazopDate="+HazopDate+"&pidNo="+pidNo+"&PssrFacility="+PssrFacility+"&MOCDetails="+MOCDetails);	
});	

jQuery("#wtifbtnodwnExcel").click(function(){
	alert("Use this Downloaded Excel Format to Fill Values and Upload");
    window.open("MOCWhatDwonloadExcel_downExcel.mocn");
});
	
jQuery("#hazbtnodwnExcel").click(function(){
	alert("Use this Downloaded Excel Format to Fill Values and Upload");
    window.open("MOChazofDwonloadExcel_downExcel.mocn");
});
	
function riskassessmentgrid_selectRow(rowId)
{
	var jqGridId="riskassessmentgrid";
	jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		//////////////////////////////////////////////////////alert("onLoadSuccess:function");		
		disableField("frmRiskAssessment","cmbRasdRisklevelid_"+jqGridId+"_"+rowId);
		var prob=jQuery("#cmbRasdProbablityid_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
		//////////////////////////////////////////////////////alert("prob"+prob+"sev"+sev);		
		//if(riskLev.trim()!="ACCEPTABLE")		
		//if(riskLevel.trim()!="RIL0000001");
		disableUIButton("btnactplan_"+jqGridId+"_"+rowId);	
		var calps=parseInt(prob)*parseInt(sev);
		//////////////////////////////////////////////////////alert(calps);
		if(parseInt(calps)>=3){
			enableUIButton("btnactplan_"+jqGridId+"_"+rowId);
		}	
	}});
	//
		
}
function cmbRasdProbablityid_riskassessmentgrid_onSelect(record,rowId){		 
	//////////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	calPS(rowId);
}
function cmbRasdSeviorityid_riskassessmentgrid_onSelect(record,rowId){
	calPS(rowId);
}
function cmbRasdRisklevelid_riskassessmentgrid_onSelect(record,rowId){
	
}
function cmbRasdControltypeid_riskassessmentgrid_onSelect(record,rowId){
	//////////////////////////////////////////////////////alert(rowId);
}
jQuery("#btnAddNewReccommend").click(function()
		 {
			////////////////////////////////////////////////////alert("Clicked")
			 
			 var row  = jQuery("#PSSRReccomendGrid").jqGrid('getDataIDs');
			////////////////////////////////////////////////////alert("ROW"+row);
			 var rowId= jQuery("#PSSRReccomendGrid").jqGrid('getRowData',row);
			 ////////////////////////////////////////////////////alert("rowId"+rowId);
			 addRowPssr(row);
		
			 
    });
    


function isValidDate(dateCtrl,ctrlRowId){

	var approvalDate = getFieldValue(dateCtrl);
	var currentDate = getServerDateTime();
	var hdndate=jQuery("#hdncurrDate").val();
	if(approvalDate!=hdndate)
		{
	var stringdate=convertStringToDate(approvalDate);
	
	if(convertStringToDate(approvalDate)== currentDate)
		{
		}

	if(convertStringToDate(hdndate) > convertStringToDate(approvalDate))
	{  
		if(stringdate==convertStringToDate(hdndate))
			{
			  clearValidationErrorMsg(dateCtrl);
		    	return false;
			}
		else{
		 popupCommonErrorMsg('Should Not Enter Past Date');
		fillWithCurrentDate(dateCtrl);
		return false;
		}
	}
}
}


function frmMocProjectcmbRfcmjhid_onSelect(record) 	{
	
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewMocfunLocation","functionalLoc.mocn","NewMOCfunLocationValues","frmMocProject",dataStr);
	
	var cellId = jQuery("#frmMocProject input[id='cell']").val();
	var flid = jQuery("#frmMocProject input[id='flid']").val();
	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?cellId="+cellId+"&flid="+flid);
	
}

function frmMocProjectcmbrfcmdmtid_onSelect(record) 	{
	
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewMocfunLocation","functionalLoc.mocn","NewMOCfunLocationValues","frmMocProject",dataStr);
	
	var sectId = jQuery("#frmMocProject input[id='sect']").val();
	var flid = jQuery("#frmMocProject input[id='flid']").val();
	reloadCombo("frmMocProject","cmbRfcmjhid","sectionCombo.commonFilter?sectId="+sectId+"&flid="+flid);
	
} 


  
function frmMocProject_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var factId = "";
var pbuId=keyIds.pbuId;
//////////////////////////////////////////////////////alert("pbuid"+pbuId);
	var sectId = keyIds.sectId;	
	////////////////////////////////////////////////////alert("sectId"+sectId)
	var cellId=keyIds.cellId;
	setFieldValue('cmbRfcmjhid',keyIds.cellId);
	setFieldValue('cmbrfcmdmtid',keyIds.sectId);
	readOnlyFields("cmbRfcmjhid");
	readOnlyFields("cmbrfcmdmtid");
	
	if (sectId != null){	
		//setFieldValue('cmbRfcmjhid',keyIds.cellId);
		//////////////////////////////////////////////////////alert("IF");
	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
}
	
	if (pbuId != null){	
		reloadCombo("frmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
	}

if (keyIds.cellId!="null"){	
	//////////////////////////////////////////////////////alert("In CellIf")
	setFieldValue('cmbRfcmjhid',keyIds.cellId);
	//	reloadCombo("frmMocProject","cmbFobdname","employeefield.ehsb?&cellId="+keyIds.cellId);
}
	setFunctionalLocWidth('frmFieldObservation','625px');
}




function HazopReccommendGridSavebutton_onClick(result){
	////////////////////////alert("click");
	var rowid=result.rowId;
	////////////////////////alert("rowid"+rowid);
	var btnid=result.btnId;
	////////////////////////alert("btnid"+btnid);
	var refDocId =jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrmasterid");
	
	var ReccomendId =jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrKeyid");
	////////////////////////alert("ReccomendId"+ReccomendId)
	
	var mainTask = jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
    var doctype = "MOCR";
    var Mstkeyid = jQuery('#hdnMocKeyId').val();
	
    var	ActionPlan = jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
    

	var detailid=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrmasterid");
	////////////////////////////////////alert("detail"+detailid);
	var RecKeyid=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrKeyid");
    var flid = jQuery("#frmMocProject input[id='flid']").val();
	var pasdate= getFieldValue("dteRfcmdate", "frmMocProject");
	
		 var keyid=jQuery('#hdnMocKeyId').val();
		 var Reccommendation=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
	 	
	 	 var Responsiblity=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowid).combobox("getValue");
	 
	 	 var targetDate=jQuery("#dteMocrTargetDate_HazopReccommendGrid_"+rowid).datebox('getValue');
	 	
	    var ActionPlanStatus=getFieldValue("cmbMocrStatus_HazopReccommendGrid_"+rowid);
	  //////////////alert(ActionPlanStatus);
	   var ActionPlanId=jQuery("#HazopReccommendGrid").jqGrid('getCell', rowid, "txtMocrActionplanId"); 
	    if(ActionPlanStatus=="C"){
	    	//////////////alert("inside");
	    	var currentDate = getCurrentDate();
	        var compdate = jQuery("#dteMocrTargetDate_HazopReccommendGrid_"+rowid).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
			
				popupCommonErrorMsg("Completed date can not be greater than current date");
				fillWithCurrentDate("dteMocrTargetDate_HazopReccommendGrid_"+rowid);
				return false;
			}

	    }	
	   
	   //   //////////////////////alert("ActionPlanId"+ActionPlanId);
 //var Reccommendconvert =  getGridSelectArray("HazopReccommendGrid");
//

var Reccommendconvert= convertJsonArrReccommend(rowid);
   // //////////////////////alert("Reccommendconvert"+Reccommendconvert); 
	var title= jQuery("#hdntitle").val("WhatIf");
 	 			 var MocKeyid=jQuery("#txtRfcmKeyid").val();
	
//	saveForm('frmMocProject','MocReccommendation_save.mocn?&rowid='+rowid+"&MocKeyid="+MocKeyid+"&Reccommendconvert="+Reccommendconvert+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype);
  // saveForm("frmMocProject","MocReccommendation_save.mocn?&MocKeyid="+MocKeyid+"&rowid="+rowid+"&Reccommendconvert="+Reccommendconvert+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype);		
   processAjaxCalls("MocReccommendation_save.mocn","&MocKeyid="+MocKeyid+"&rowid="+rowid+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype+"&flid="+flid+"&Reccommendconvert="+Reccommendconvert+"&ReccomendId="+ReccomendId,"Reccommendation_successCallBack","");	
			
}
function convertJsonArrReccommend(rowid){
	var allrow = jQuery("#HazopReccommendGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	var RecmdId="";
	var MocId="";
    var MasterId=""; 
    var Category=""; 
    var Reccommend="";
    var Responsibility="";
    var TargetDate="";
    var Status=""; 
 
            
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':disabled')==true)
			{
			continue;
			}
		
		if (jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':checked')&& rowno==rowid) {
			////////////////////////alert("inside"+rowid);
			jsonArrO += '{';
			RecmdId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrKeyid");
			MasterId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrmasterid");
			MocId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrMocid");
			TargetDate=getFieldValue("dteMocrTargetDate_HazopReccommendGrid_"+rowno);
			Category=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"txtMocrCategory");
			Reccommend=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"txtMocrrecmnd");
			Responsibility=getFieldValue("cmbMocrResponsibility_HazopReccommendGrid_"+rowno);
		 //  Responsiblity=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowno).combobox("getValue");
		
			
			Status=getFieldValue("cmbMocrStatus_HazopReccommendGrid_"+rowno);
			// //////////////////////alert("Status:"+Status);  
				  jsonArrO += '"hdnMocrKeyid":"' + RecmdId + '",';
				  jsonArrO += '"hdnMocrmasterid":"' + MasterId + '",';
				  jsonArrO += '"hdnMocrMocid":"' + MocId + '",';
				
				  jsonArrO += '"txtMocrCategory":"' + Category + '",';
				  jsonArrO += '"txtMocrrecmnd":"' + Reccommend + '",';
				  jsonArrO += '"cmbMocrResponsibility":"' + Responsibility + '",';
				  
				  jsonArrO += '"dtePtaskTargetdate":"' + TargetDate + '",';
				 
				  
				  jsonArrO += '"cmbMocrStatus":"' + Status+ '"},';
				
				
			   //  //////////////////////alert(jsonArrO);
		}
	}
	return '[' + jsonArrO.slice(0, -1) + ']';
}

function PSSRReccomendGridbtnPssrRecSave_onClick(result){
var rowid=result.rowId;
	
	var btnid=result.btnId;
	
	var refDocId =jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrmasterid");
	
	var ReccomendId =jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
	var mainTask = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
	////////////alert(mainTask)
    var doctype = "PSRR";
    var Mstkeyid = jQuery('#hdnMocKeyId').val();
	
    var	ActionPlan = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
    var detailid=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
	var RecKeyid=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
    var PssrCategory=getFieldValue("selPsrrCategory_PSSRReccomendGrid_"+rowid);
    var flid = jQuery("#frmMocProject input[id='flid']").val();
	var pasdate= getFieldValue("dteRfcmdate", "frmMocProject");
	 var keyid=jQuery('#hdnMocKeyId').val();
	 var Reccommendation=getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
	 var Responsiblity=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowid).combobox("getValue");
	 var targetDate=jQuery("#dtePsrrTargetDate_PSSRReccomendGrid_"+rowid).datebox('getValue');
	 var ActionPlanStatus=getFieldValue("cmbPsrrStatus_PSSRReccomendGrid_"+rowid);
	 var ActionPlanId=jQuery("#PSSRReccomendGrid").jqGrid('getCell', rowid, "txtMocrActionplanId"); 
	    if(ActionPlanStatus=="C"){
	    	////////////alert("inside");
	    	var currentDate = getCurrentDate();
	        var compdate = jQuery("#dtePsrrTargetDate_PSSRReccomendGrid_"+rowid).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
			
				popupCommonErrorMsg("Completed date can not be greater than current date");
				fillWithCurrentDate("dtePsrrTargetDate_PSSRReccomendGrid_"+rowid);
				return false;
			} 
			}
	    
var PSSRChkReccommendconvert= convertJsonArrPssrChkReccommend(rowid);
  ////////////alert("PSSRChkReccommendconvert"+PSSRChkReccommendconvert); 
	var title= jQuery("#hdntitle").val("WhatIf");
    var MocKeyid=jQuery("#txtRfcmKeyid").val();
	processAjaxCalls("MocPSSRChkReccommendation_save.mocn","&MocKeyid="+MocKeyid+"&rowid="+rowid+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype+"&flid="+flid+"&PSSRChkReccommendconvert="+PSSRChkReccommendconvert+"&ReccomendId="+ReccomendId,"PssrRecChkReccommendation_successCallBack","");	

}

 
 function convertJsonArrPssrChkReccommend(rowid){
		var allrow = jQuery("#PSSRReccomendGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		var RecmdId="";
		var MocId="";
	    var MasterId=""; 
	    var Category=""; 
	    var Reccommend="";
	    var Responsibility="";
	    var TargetDate="";
	    var Status=""; 
	        
		for ( var i=0; i<allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i)+1;
			if(jQuery('#jqg_PSSRReccomendGrid_'+rowno).is(':disabled')==true)
				{
				continue;
				}

			if (jQuery('#jqg_PSSRReccomendGrid_' + rowno).is(':checked')) {
				jsonArrO += '{';
				RecmdId=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"hdnPsrrKeyid");
				//////////////alert("RecmdId"+RecmdId);
				MasterId=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"hdnPsrrmasterid");
				//////////////alert("MasterId"+MasterId);
				TargetDate=getFieldValue("dtePsrrTargetDate_PSSRReccomendGrid_"+rowno);
				//////////////alert("TargetDate"+TargetDate);
				Category=getFieldValue("selPsrrCategory_PSSRReccomendGrid_"+rowno);
			//	////////////alert("Category"+Category);
				//Reccommend=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"txtPsrrrecmnd");
				Reccommend = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowno);
			//	////////////alert("Reccommend"+Reccommend);

				Responsibility=getFieldValue("cmbPssrResponsibility_PSSRReccomendGrid_"+rowno);
				//////////////alert("Responsibility"+Responsibility);
				Status=getFieldValue("cmbPsrrStatus_PSSRReccomendGrid_"+rowno);
				//Status=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"cmbPsrrStatus");
				//Status=jQuery("#cmbPsrrStatus_PSSRReccomendGrid_"+rowno).combobox("getValue");
			//	////////////alert("status"+Status);
					  jsonArrO += '"hdnPsrrKeyid":"' + RecmdId + '",';
					  jsonArrO += '"hdnPsrrmasterid":"' + MasterId + '",';
					//  jsonArrO += '"hdnMocrMocid":"' + MocId + '",';
					  jsonArrO += '"dtePsrrTargetDate":"' + TargetDate + '",';
					  jsonArrO += '"selPsrrCategory":"' + Category + '",';
					  jsonArrO += '"txtPsrrrecmnd":"' + Reccommend + '",';
					  jsonArrO += '"cmbPssrResponsibility":"' + Responsibility + '",';
					  jsonArrO += '"cmbPsrrStatus":"' + Status+ '"},';
					
					
				//    ////////////alert(jsonArrO);
			}
		}
		return '[' + jsonArrO.slice(0, -1) + ']';
	}


 
function Reccommendation_successCallBack(result) {
           //alert("Data Saved Successfully");
			var MocKeyid=jQuery("#hdnMocKeyId").val();
			 processGridnew("HazopRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	
		 	 		
}
 function PssrRecChkReccommendation_successCallBack(result) {
alert("Data Saved Successfully");
		var MocKeyid=jQuery("#hdnMocKeyId").val(); 
processGridnew("PSSRRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","ReccommendloadComFunction");	
	 	 		 		
}

jQuery("#chkSelectAllQuestions").click(function(){
	   show_winMask(1);
    fnSelectAllQuestions();
	   show_winMask(0);
});

function  fnSelectAllQuestions(){
		var row = jQuery("#QuestionaireGrd").jqGrid('getDataIDs');
		//////////////////////alert("all");
		for(var i=0;i<row.length;i++)
		 {
			if(jQuery("#chkSelectAllQuestions").is(':checked')== true){
				var QuestionaireKey = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");
				//////////////////////alert(QuestionaireKey);
				if(QuestionaireKey.length>0){
					var jqGridId="QuestionaireGrd";
					 //jQuery("#dteEtcaAddDate_"+jqGridId+"_"+row[i]).attr('readonly','readonly'); 
					  jQuery("#cmbYes_"+jqGridId+"_"+row).attr('disabled',true);
					}
				jQuery('#QuestionaireGrd').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('checked',true);
			 //   setFieldValue("cmbYes_QuestionaireGrd_"row[i],'Y');
			
   			
			 jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','1');
			 }
			 else {
				    jQuery('#QuestionaireGrd').setSelection(row[i], false);
				    jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('checked',false);
				    jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','0');
                 
			 }
		 }	
	}



	
function HazopReccommendGrid_selectRow(rowId){
	var dateCtrl="dteMocrTargetDate_HazopReccommendGrid_"+rowId;
	formatDateBox(dateCtrl,'dd-MMM-yyyy');
	//fillWithCurrentDate(dateCtrl);
		 var jqGridId="HazopReccommendGrid";
			var whrecomment=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnuserid").val();
		//	//////////////////////alert("Resp"+Resp);
			if(whrecomment==Resp)
			{
			   jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
	jQuery("#"+dateCtrl).datebox({  	   
		onSelect:function(recordid)
			{ 
			isValidTargetDate1(dateCtrl,rowId);    	
			} 
		}); 
	
	
	
	jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox({  	   
		onSelect:function(recordid)
			{ 
			 var jqGridId="HazopReccommendGrid";
			var whrecomment=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnResponsibility").val();
		//	//////////////////////alert("Resp"+Resp);
			if(whrecomment==Resp)
			{
			   jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
			} 
			
		});
	
	// ---vignesh07sep2026

	// -- Vignesh 07Sep2026
function isValidTargetDate(dateCtrl, ctrlRowId)
{
    var mocDateStr = jQuery.trim(
        jQuery("#dteRfcmdate").datebox('getValue') || ""
    );

    var targetDateStr = jQuery.trim(
        getFieldValue(dateCtrl) || ""
    );

    /*
     * Do not validate until both dates are available.
     */
    if (mocDateStr == "" || targetDateStr == "") {
        return true;
    }

    var mocDate = convertStringToDate(mocDateStr);
    var targetDate = convertStringToDate(targetDateStr);

    /*
     * Target Date can be same as or greater than MOC Date.
     * Only earlier date is invalid.
     */
    if (targetDate.getTime() < mocDate.getTime())
    {
        popupCommonErrorMsg(
            'Target Date cannot be less than MOC Date'
        );

        /*
         * Put back the minimum valid date.
         * Do NOT use current date here because MOC date itself
         * may sometimes be greater than current date.
         */
        jQuery("#" + dateCtrl)
            .datebox("setValue", mocDateStr);

        return false;
    }

    clearCommonErrorMsg();
    return true;
}
	function isValidTargetDate1(dateCtrl, ctrlRowId)
	{
	    var mocDateStr = jQuery.trim(
	        jQuery("#dteRfcmdate").datebox('getValue') || ""
	    );

	    var targetDateStr = jQuery.trim(
	        getFieldValue(dateCtrl) || ""
	    );

	    if (mocDateStr == "" || targetDateStr == "") {
	        return true;
	    }

	    var mocDate = convertStringToDate(mocDateStr);
	    var targetDate = convertStringToDate(targetDateStr);

	    if (targetDate.getTime() < mocDate.getTime())
	    {
	        popupCommonErrorMsg(
	            'Target Date cannot be less than MOC Date'
	        );

	        jQuery("#" + dateCtrl)
	            .datebox("setValue", mocDateStr);

	        return false;
	    }

	    clearCommonErrorMsg();
	    return true;
	}
	// ---vignesh07sep2026
	/*
	function isValidTargetDate1(dateCtrl,ctrlRowId){
		var sysdate=jQuery("#hdncurrentdate").val();
		var currdate=jQuery("#dteRfcmdate").datebox('getValue');
		var approvalDate = getFieldValue(dateCtrl);
		var currentDate = getServerDateTime();
	if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
			{	
			if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
				{
				popupCommonErrorMsg('Should Not Exceed the MOC Date');
				fillWithCurrentDate(dateCtrl);
				return false
			
		}
		
	}

	}
	*/
	
}


function PSSRReccomendGrid_selectRow(rowId)	{
		var dateCtrl="dtePsrrTargetDate_PSSRReccomendGrid_"+rowId;
		 var jqGridId="PSSRReccomendGrid";
			var PssrResp=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnuserid").val();
		//	//////////////////////alert("Resp"+Resp);
			if(PssrResp==Resp)
			{
			   jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
		
		// -- 07sep2026  vignesh
	//	formatDateBox(dateCtrl,'dd-MMM-yyyy');
		
setTimeout(function(){

	if(jQuery("#"+dateCtrl).length > 0)
	{
		var targetDate =
			jQuery("#"+dateCtrl).datebox("getValue");

		/*
		 * Only auto-fill when Target Date is blank.
		 * Existing dates are never overwritten.
		 */
		if(jQuery.trim(targetDate) == "")
		{
			fillWithCurrentDate(dateCtrl);
		}

		jQuery("#"+dateCtrl).datebox({
			onSelect:function(recordid)
			{
				isValidTargetDate(dateCtrl,rowId);
			}
		});
	}

}, 50);
//-- 07sep2026  vignesh
		
		//fillWithCurrentDate(dateCtrl);
		jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				isValidTargetDate(dateCtrl,rowId);    	
				} 
			}); 
		
		
/*
function isValidTargetDate(dateCtrl,ctrlRowId){
	var sysdate=jQuery("#hdncurrentdate").val();
	var currdate=jQuery("#dteRfcmdate").datebox('getValue');
	var approvalDate = getFieldValue(dateCtrl);
	var currentDate = getServerDateTime();
if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
		{	
		if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
			{
			popupCommonErrorMsg('Should Not Exceed the MOC Date');
			fillWithCurrentDate(dateCtrl);
			return false
		
	}
	
}

}
*/

jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox({  	   
	onSelect:function(recordid)
		{ 
		 var jqGridId="PSSRReccomendGrid";
		var PssrResp=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox('getValue');
	//	//////////////////////alert(PssrResp); 
		var Resp=jQuery("#hdnResponsibility").val();
	//	//////////////////////alert("Resp"+Resp);
		if(PssrResp==Resp)
		{
		   jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
		}
		
		else{
			jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
			
		}
		} 
		
	});

}


function validateMandtoryforWahtif(gridSelArr){
	var selArr = JSON.parse(gridSelArr);
	for(var i = 0;i<selArr.length;i++){
		if(selArr[i].txtWifdWithoutSafeGuards.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
		if(selArr[i].txtWifdRecommentations.trim()==""){
			popupCommonErrorMsg("Enter the Recommentations");
			return false;
		}	
	}
	return true;
	}

function validateMandtoryforHazop(gridSelArr){
	var selArr = JSON.parse(gridSelArr);
	for(var i = 0;i<selArr.length;i++){
		if(selArr[i].txtMohdWithoutSafeGuards.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
		if(selArr[i].txtMohdRecommentations.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
	}
	return true;
	}
function validateAndSetNoOfDays() {

    var nature = "";

    try {
        nature = jQuery.trim(jQuery("[name='Nature']").combobox("getText"));
    } catch (e) {
        nature = jQuery.trim(jQuery("[name='Nature']").val());
    }

    var noofdays = jQuery.trim(jQuery("#txtMocRfcmNoofdays").val());

    if (nature === "Temporary") {

        if (noofdays === "") {
            alert("Please enter Number of Days for Temporary MOC.");
            jQuery("#txtNumberOfDays").focus();
            return false;
        }

        if (!/^[0-9]{1,2}$/.test(noofdays)) {
            alert("Number of Days must be numeric and maximum 2 digits.");
            jQuery("#txtNumberOfDays").focus();
            return false;
        }

//         if (parseInt(noofdays, 999) <= 0) {
//             alert("Number of Days must be greater than 0 for Temporary MOC.");
//             jQuery("#txtMocRfcmNoofdays").focus();
//             return false;
//         }

    } else {
        noofdays = "0";
    }

    jQuery("#hdnMocRfcmNoofdays").val(noofdays);

    alert("No of Days before submit = " + jQuery("#hdnMocRfcmNoofdays").val());

    return true;
}

function getTeamCell(rowId, colName) {
    return jQuery.trim(jQuery("#TeamGrid").jqGrid("getCell", rowId, colName) || "");
}

function buildMandatoryTeamRow(rowId) {
    return {
        hdnMctcKeyid: getTeamCell(rowId, "hdnMctcKeyid"),
        hdnMctcmasterid: getTeamCell(rowId, "hdnMctcmasterid"),
        hdnMctcroleid: getTeamCell(rowId, "hdnMctcroleid"),
        txtRoleName: getTeamCell(rowId, "txtRoleName"),
        hdnMctcgroupno: getTeamCell(rowId, "hdnMctcgroupno"),

        chkMctcInitial: "Y",
        chkMctcHazop: "Y",
        chkMctcfinal: "Y",

        cmbAbnmDetectedby: getTeamCell(rowId, "cmbAbnmDetectedby"),
        hdnMctcempid: getTeamCell(rowId, "hdnMctcempid"),

        cmbMctcempid2: getTeamCell(rowId, "cmbMctcempid2"),
        hdnMctcempid2: getTeamCell(rowId, "hdnMctcempid2")
    };
}

function addOrReplaceTeamRow(teamArr, newRow) {
    for (var i = 0; i < teamArr.length; i++) {
        if (teamArr[i].hdnMctcroleid === newRow.hdnMctcroleid) {
            teamArr[i] = newRow;
            return;
        }
    }

    teamArr.push(newRow);
}
//-- vignes 14 may 
function addMandatoryWorkflowRowsToGridJson(gridval) {
    var teamArr = [];

    try {
        teamArr = JSON.parse(gridval);
    } catch (e) {
        teamArr = [];
    }

    var dmtLeaderRow = getTeamRowByRole("DMT Leader");
    var safetyManagerRow = getTeamRowByRole("Safety manager");

    if (dmtLeaderRow) {
        addOrReplaceTeamRow(teamArr, buildMandatoryTeamRow(dmtLeaderRow));
    }

    if (safetyManagerRow) {
        addOrReplaceTeamRow(teamArr, buildMandatoryTeamRow(safetyManagerRow));
    }

    return JSON.stringify(teamArr);
}

function frmMocProject_deleteSuccessCallback(result)
{
	navigateToPrevForm();
	  
}

 function validateNoNewWorkflowRoleAfterInitialApproval() {

	    if (mocTeamInitialWorkflowLocked !== "Y") {
	        return true;
	    }

	    var rows = jQuery("#TeamGrid").jqGrid("getDataIDs");

	    for (var i = 0; i < rows.length; i++) {

	        var rowId = rows[i];

	        var roleId = jQuery.trim(
	            jQuery("#TeamGrid").jqGrid("getCell", rowId, "hdnMctcroleid") || ""
	        );

	        var roleName = jQuery.trim(
	            jQuery("#TeamGrid").jqGrid("getCell", rowId, "txtRoleName") || ""
	        );

	        /*
	         * Existing DB role.
	         * Example: Initiator, DMT Leader, Safety Manager already present in moc_tl_roleconfigmst.
	         * Allow secondary approval update for these roles.
	         */
	        if (mocTeamExistingRoleMap[roleId] === "Y") {
	            continue;
	        }

	        /*
	         * New role not present in DB.
	         * Block only if user actively checks this new role.
	         */
	        var isChecked = jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]").is(":checked");

	        if (isChecked) {

	            popupCommonErrorMsg(
	                "Initial approval is already completed. New workflow role [" +
	                roleName +
	                "] cannot be added."
	            );

	            return false;
	        }

	        /*
	         * Important:
	         * If this is a new role and not checked, clear any old/stale hidden values.
	         * Do not throw error for stale hidden primary/secondary values.
	         */
	        clearNewWorkflowRoleValues(rowId);
	    }

	    return true;
	}
 function clearNewWorkflowRoleValues(rowId) {

	    if (!rowId) {
	        return;
	    }

	    var jqGridId = "TeamGrid";

	    // Force approval flags as N
	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcInitial", "N");
	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcHazop", "N");
	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcfinal", "N");

	    // Clear primary approval visible + hidden
	    try {
	        jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId)
	            .combobox("clear")
	            .combobox("setValue", "")
	            .combobox("setText", "")
	            .combobox("disable");
	    } catch (e) {}

	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbAbnmDetectedby", "");
	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", "");

	    // Clear secondary approval visible + hidden
	    try {
	        jQuery("#cmbMctcempid2_TeamGrid_" + rowId)
	            .combobox("clear")
	            .combobox("setValue", "")
	            .combobox("setText", "")
	            .combobox("disable");
	    } catch (e) {}

	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbMctcempid2", "");
	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", "");

	    // Uncheck row
	    jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]")
	        .prop("checked", false)
	        .attr("disabled", true);

	    jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", "0");

	    // Disable approval checkboxes
	    jQuery("#chkMctcInitial_" + jqGridId + "_" + rowId)
	        .prop("checked", false)
	        .attr("disabled", true);

	    jQuery("#chkMctcHazop_" + jqGridId + "_" + rowId)
	        .prop("checked", false)
	        .attr("disabled", true);

	    jQuery("#chkMctcfinal_" + jqGridId + "_" + rowId)
	        .prop("checked", false)
	        .attr("disabled", true);
	}
// -- vignes 14 may 
	 function frmMocProject_beforeSubmit(){
		 var title=jQuery("#hdntitle").val();
		 var Masterid=jQuery("#hdnSuggestionId").val();
	     var MocKeyid=jQuery("#hdnMocKeyId").val();
	//    alert("Moc Keid otside if"+MocKeyid);
	      var Nature=jQuery("#cboRfcmnature").val();
	      
		  var Initiator=jQuery("#cmbRfcmempid").combobox("getValue");
		  var Jh=jQuery("#cmbRfcmjhid").combobox("getValue");
		  var Dmt=jQuery("#cmbrfcmdmtid").combobox("getValue");
	      var MocType=jQuery("#cboRfcmtype").val();
		  var MOCTitle=jQuery("#txtRfcmtitle").val(); 
		  var Desc=jQuery("#txtRfcmdescription").val();
		  
		  var modeSave = jQuery("#hdnmode").val();
		  if(modeSave == "view")
		  {
			  alert("Cannot modify data in View Mode");
			  return false;
		  } 
		  
	 var Detail=jQuery("#txtRfcmdetail").val();
	 var capexVal = jQuery.trim(jQuery("#chckMocRfcmCapex").val());

	 if (capexVal !== "Y" && capexVal !== "N") {
	     alert("Please select CapEx Yes or No.");
	     return false;
	 }
	 
	 var noofdays = jQuery.trim(jQuery("#txtNumberOfDays").val());
	
	// alert("noofdays " + noofdays);
	if (Nature === "Temporary") {
    if (noofdays === "") {
        alert("No of days is mandatory for Temporary MOC.");
        jQuery("#txtNumberOfDays").focus();
        return false;
    }

    if (!/^[0-9]+$/.test(noofdays)) {
        alert("No of days must contain only numbers.");
        jQuery("#txtNumberOfDays").focus();
        return false;
    }
}
		else {
    noofdays = "";
} 
     var flid = jQuery("#frmMocProject input[id='flid']").val();
     var mocDate=jQuery("#dteRfcmdate").datebox("getValue");
     if(mocDate.length==0){
    	 popupCommonErrorMsg("Select The MOC Date");
    	 return false;
     }
	 //////////////////////////////////////////alert(flid);
if(title=="RequestforChange"){
		//////////////////////////////////////////////alert("Title::"+title);
		////////////////////////////////////////////////////alert("Request");
				//var MocKeyid=jQuery("#hdnMocKeyId").val();
				var MocKeyid=jQuery("#txtRfcmKeyid").val();
				 if (MOCTitle == null || MOCTitle.trim().length == 0) {
				 	    alert("Please enter MOC Title");
				 	    return false;
				 	}
				     if (MocType == null || MocType.length == 0) {
				    	    alert("Please select Type of Change	");
				    	    return false;
				    	}
				     if (Nature == null || Nature.trim().length == 0) {
				 	    alert("Please enter Nature of Change");
				 	    return false;
				 	}

				     if (Detail == null || Detail.trim().length == 0) {
				 	    alert("Please enter MOC Detail");
				 	    return false;
				 	}

				    	if (Desc == null || Desc.trim().length == 0) {
				    	    alert("Please enter Proposed change Description");
				    	    return false;
				    	}
	//////////////////////////////////////////////alert("MocKeyid"+MocKeyid);
		 var BasisDetails =convertJsonArr();
    // return "MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&title="+title+"&Nature="+Nature+"&MocType="+MocType+"&Initiator="+Initiator+"&Jh="+Jh+"&Dmt="+Dmt+"&MOCTitle="+MOCTitle+"&Desc="+Desc+"&Detail="+Detail+"&noofdays="+noofdays;	
		 // return "MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&title="+title+"&Nature="+Nature+"&MocType="+MocType+"&Initiator="+Initiator+"&Jh="+Jh+"&Dmt="+Dmt+"&MOCTitle="+MOCTitle+"&Desc="+Desc+"&Detail="+Detail+"&noofdays="+noofdays;
		 var othersNotes = jQuery.trim(jQuery("#txtAdditionalNotes").val());
		 return "MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&title="+title+"&Nature="+Nature+"&MocType="+MocType+"&Initiator="+Initiator+"&Jh="+Jh+"&Dmt="+Dmt+"&MOCTitle="+MOCTitle+"&Desc="+Desc+"&Detail="+Detail+"&noofdays="+noofdays+"&txtAdditionalNotes="+encodeURIComponent(othersNotes);	 
	
	}
/*else if(title=="InitialApproval"){
	// var Complete=jQuery("#chkMocClosed").val();
	 if (MOCTitle == null || MOCTitle.trim().length == 0) {
 	    alert("Please enter MOC Title");
 	    return false;
 	}
     if (MocType == null || MocType.length == 0) {
    	    alert("Please select Type of Change	");
    	    return false;
    	}
     if (Nature == null || Nature.trim().length == 0) {
 	    alert("Please enter Nature of Change");
 	    return false;
 	}

     if (Detail == null || Detail.trim().length == 0) {
 	    alert("Please enter MOC Detail");
 	    return false;
 	}

    	if (Desc == null || Desc.trim().length == 0) {
    	    alert("Please enter Proposed change Description");
    	    return false;
    	}
	}*/
	else if(title=="Questionnaire"){
		  var paramconvert =convertJsonArrQuestions();
		  var MocKeyid=jQuery("#txtRfcmKeyid").val();
//////////////////////alert(paramconvert);
		  return "MocKeyid="+MocKeyid+"&paramconvert="+paramconvert+"&title="+title;
	}
	
	else if(title=="MOCCLosure"){
		  var paramconvertClosure =convertJsonArrClosure();
		 //////////////////////////////////////////////alert("paramconvertClosure"+paramconvertClosure);
		  return "MocKeyid="+MocKeyid+"&paramconvertClosure="+paramconvertClosure+"&title="+title;
	}
	else if(title=="Psschecklist"){
var pssrconvert =  getGridSelectArray("PSSRGrid");
		////////////////////////////////////////////alert(pssrconvert);

					return "MocKeyid="+MocKeyid+"&pssrconvert="+pssrconvert+"&title="+title;
				
	}
	

	else if(title=="WhatIf"){
		var WhatifKeyId=jQuery("#hdnWhatifKey").val();
		
		var team=jQuery("#txtWifmTeam").val();
        if(team.trim().length==0){ //change here 
			alert(" Please Type Team before Saving");
			return false;
		}
		////////////////////alert(WhatifKeyId);
		 var mockeyid=jQuery("#txtRfcmKeyid").val();
         var kaizeid=jQuery("#txtRfcmsuggestionid").val();
         var gridWhatifval=getGridSelectArray('WhatifGrid');
         if(gridWhatifval !=""){
        	 if(validateMandtoryforWahtif(gridWhatifval))
        		 
       		  return '&whatifDetails='+gridWhatifval+"&mockeyid="+mockeyid+"&kaizeid="+kaizeid+"&title="+title+"&WhatifKeyId="+WhatifKeyId;

         }
         return false;
		  ////////////////////////////////////////////////alert(gridData);
	 	 //  if(gridWhatifval.trim().length>0)	
				//return gridData;
	}
	
	
	else if(title=="Hazop"){
		 var mockeyid=jQuery("#txtRfcmKeyid").val();
		 var HazopKeyId=jQuery("#hdnHazopKey").val();
         var kaizeid=jQuery("#txtRfcmsuggestionid").val();
		 var gridHazopval=getGridSelectArray('Hazopgrid');
		 
		 if(gridHazopval !=""){
		 
			 if(validateMandtoryforHazop(gridHazopval))
			 return '&hazopDetails='+gridHazopval+"&mockeyid="+mockeyid+"&kaizeid="+kaizeid+"&title="+title+"&HazopKeyId="+HazopKeyId;
	 	//    if(gridHazopval.trim().length>0)	
			//	return gridData;
		 }
		 return false;
	}
	
	else if(title=="FinalApprovals"){
		// var Complete=jQuery("#chkMocClosed").val();
		var MOCCompleted;
		if(jQuery("#chkMocClosed").is(':checked')==true){
			//////////////////////////alert("inside")
		MOCCompleted="Y";	
		return "MOCCompleted="+MOCCompleted+"&MocKeyid="+MocKeyid+"&title="+title;
		}
		
	}

	//else if(title=="MOC Team"){
	//var gridval=convertJsonArrTeam();
//var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
/* var Process = getFieldValue("cmbAbnmDetectedby_TeamGrid_"+8); 
//var ProcessCheck=jQuery('#MomAttcheckbox_'+ rowid +'_8').is(':checked');
var Mechanical=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+9);
var Electrical=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+10);
var Instrument=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+11);
var EHSHead=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+15);

if(typeof Process=="undefined"||Process==""){

      popupCommonErrorMsg("Select The Process Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Mechanical=="undefined"||Mechanical==""){

      popupCommonErrorMsg("Select The Mechanical Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Electrical=="undefined"||Electrical==""){

      popupCommonErrorMsg("Select The Electrical Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Instrument=="undefined"||Instrument==""){

      popupCommonErrorMsg("Select The Instrument Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof EHSHead=="undefined"||EHSHead==""){

      popupCommonErrorMsg("Select The EHSHead for Initial Approval");
     
      return false; 
} */
/* var gridval=convertJsonArrTeam();
//alert("Grid"+gridval); 
return "gridval="+gridval+"&title="+title+"&MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&flid="+flid;
	} */
	else if(title=="MOC Team"){
	    var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
	    var MocKeyid=jQuery("#txtRfcmKeyid").val();
	    
	 // Vignesh 04-Jun-2026
	    // Final safety before save:
	    // If CapEx is N, clear Pillar Champion and Sbu Head visible + hidden values.
	    clearCapexRestrictedRowsIfNo();
	    applyInitialApprovedPrimaryLocks();

	    // Re-apply workflow role lock before validation
	    applyMocTeamWorkflowRoleLock();
	    if (!validateNoNewWorkflowRoleAfterInitialApproval()) {
	        return false;
	    }
	    
	    var gridval = convertJsonArrTeam();
	    // Vignesh manually adding dmt leader and safety manager 14may
	    gridval = addMandatoryWorkflowRowsToGridJson(gridval);
	    
	    var SuggestionId = jQuery("#hdnSuggestionId").val();
	    var Suggestion = jQuery("#hdnSuggestion").val();
	    
	    // Vignesh 13-May-2026: Check secondary approval value before submit
	    try {
	        var teamArr = JSON.parse(gridval);
	        var msg = "";

	        for (var i = 0; i < teamArr.length; i++) {
	            msg += "Row " + (i + 1)
	                + " | Role: " + (teamArr[i].txtRoleName || "")
	                + " | Primary: " + (teamArr[i].hdnMctcempid || "")
	                + " | Secondary: " + (teamArr[i].hdnMctcempid2 || teamArr[i].cmbMctcempid2 || "")
	                + "\n";
	        }

	    //    alert("MOC Team Secondary Approval Values:\n" + msg);
	    } catch (e) {
	        alert("Error reading MOC Team grid JSON: " + e.message + "\n\n" + gridval);
	    }
	 // Vignesh 13-May-2026: Check secondary approval value before submit
	 //   alert("Moc Keid indisde if"+MocKeyid);
	    return "gridval=" + gridval + 
	           "&title=" + title + 
	           "&MocKeyid=" + MocKeyid + 
	           "&flid=" + flid + 
	           "&SuggestionId=" + SuggestionId + 
	           "&Suggestion=" + Suggestion;
	}
		 
	 }
	 
	 function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName){
			var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
			var rowid ="";
			var jsonArrO = '[';
			for ( i =0; i <Rowrow.length; i++) {
				rowid = Rowrow[i];
			  var isChecked =jQuery("#chkMctcInitial_TeamGrid_"+rowid).is(':checked');
		    }		
		}
	 //  07Sep2026 Vignesh -- PssrGridloadComFunction --//
	 function PssrGridloadComFunction(){

		    var row = jQuery("#PSSRGrid").jqGrid('getDataIDs');

		    var initial = jQuery("#hdnInitialApproval").val();
		    var hazop   = jQuery("#hdnHazopApproval").val();

		    var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();

		    /*
		     * IMPORTANT:
		     * Do not keep DB-saved rows in jqGrid selarrrow.
		     * We only want to SHOW them as checked + disabled.
		     */
		    jQuery("#PSSRGrid").jqGrid("resetSelection");

		    for(var i = 0; i < row.length; i++)
		    {
		        var rowId = row[i];

		        var detailsid = jQuery.trim(
		            jQuery("#PSSRGrid")
		                .jqGrid('getCell', rowId, "hdnPsrdmasterid") || ""
		        );

		        var chk = jQuery("#jqg_PSSRGrid_" + rowId);

		        /*
		         * Already saved in DB.
		         */
		        if(detailsid.length > 0)
		        {
		            /*
		             * VISUAL checked state only.
		             * Do NOT use setSelection().
		             */
		            chk.prop("checked", true);
		            chk.prop("disabled", true);

		            jQuery("#PSSRGrid")
		                .jqGrid('setCell', rowId, 'selctVal', '1');
		        }
		        else
		        {
		            chk.prop("checked", false);

		            jQuery("#PSSRGrid")
		                .jqGrid('setCell', rowId, 'selctVal', '0');

		            /*
		             * Unsaved rows are editable only when approvals permit it.
		             */
		            if(initial == "Pending" ||
		               hazop == "Pending" ||
		               pageMode == "view" ||
		               pageMode == "approval")
		            {
		                chk.prop("disabled", true);
		            }
		            else
		            {
		                chk.prop("disabled", false);
		            }
		        }
		    }
		}

	
	 	 /*

	 function PssrGridloadComFunction(){
			
		    var row = jQuery("#PSSRGrid").jqGrid('getDataIDs');
			 var cm = jQuery("#PSSRGrid").jqGrid("getGridParam", "colModel");
		 	 var initial =jQuery("#hdnInitialApproval").val();
		 	 var hazop=	jQuery("#hdnHazopApproval").val();
		 	 
		 	var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();
		 	 
////////////alert("initial"+initial);
////////////alert("hazop"+hazop);
	    	 if(initial=="Pending"||hazop=="Pending"){
	    		 for(var i=0;i<row.length;i++)	{
	    			 //////////////////////////////////alert("In If");
	    			 jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);  	 
	    		 
	    			 } 
	    		 
	    	 }
	    	 else
	    		 {
			 for(var i=0;i<row.length;i++)
			 {
				 var detailsid = jQuery("#PSSRGrid").jqGrid('getCell',row[i],"hdnPsrdmasterid");
					//var mode= jQuery('#hdnmode').val();
					var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();
					////////////////////////////////////////////alert("mode"+mode);
					//if(mode=="view"){
						if(pageMode == "view" || pageMode == "approval"){
						//disableGridSort("TeamGrid");
						jQuery('#PSSRGrid').setSelection(row[i], false);
						 jQuery('input:checkbox[id=jqgh_PSSRGrid_'+row[i]+']').attr('checked',false);
						   jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','0');
						  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
						   jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);
					}
				   if(detailsid.trim().length>0){ 
					    jQuery('#PSSRGrid').setSelection(row[i], false);
					    jQuery('input:checkbox[id=jqgh_PSSRGrid_'+row[i]+']').attr('checked',true);
					    jQuery("#PSSRGrid").jqGrid('setCell',row[i],'selctVal','1');
					    
					   jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);
				   }
			 }
			 }	 
		} }
	 */
	 function frmMocProject_successsCallback(result){
     var MocKeyid=result.successData.keyId;  
     //var MstKeyid=jQuery("#hdnMocKeyId").val();
     
    // jQuery("#hdnMocKeyId").val(MocKeyid);
    ////////////////////////////////////////////alert(MocKeyid);
    var momactnpln =result.successData.momactnpln;
    ////////////////////////////////////////////alert("momactnpln"+momactnpln);
    jQuery('#hdnactnmode').val(momactnpln);
    var rowid=result.RowId;
    jQuery('#hdnrowid').val(rowid);
     var MocSuggestionId=result.suggestionId;
     
     var MocKeyidClosure=result.MocKeyidClosure;
     //////////////////alert("MOC Closure Keyid"+MocKeyidClosure);
   //  va title=result.title;
    // //////////////////////////////////////////////alert(title);
   var WhatifKeyId=result.successData.WifmKeyid;
  // ////////////////////////////alert("WhatifKeyId"+WhatifKeyId);
   if(WhatifKeyId!=null){
	   jQuery("#hdnWhatifKey").val(WhatifKeyId);
   
   }
   else{
	   var WfKeyId=result.successData.WhatifKeyId;
	 //  ////////////////////////////alert("WhatifKeyId:::::::::::::"+WfKeyId);
	   jQuery("#hdnWhatifKey").val(WfKeyId);
   }
   
  // jQuery("#hdnWhatifKey").val(WhatifKeyId);
   
   var HzomKeyid=result.successData.HzomKeyid;
   ////////////////////////////alert("Hazop"+HzomKeyid);
   
   if(HzomKeyid!=null){
	   jQuery("#hdnHazopKey").val(HzomKeyid); 
   }
   else{
	   var HazopKey=result.successData.HazopKey;
	   ////////////////////////////alert("HAZOP KEY"+HazopKey);
	   jQuery("#hdnHazopKey").val(HazopKey); 
   }
   
    var filterString="&MocKeyid="+MocKeyid+"&MocSuggestionId="+MocSuggestionId;
	 jQuery("#txtRfcmKeyid").val(MocKeyid);
	 
	 /*
	  * MOC Completed save:
	  * DB is already updated to MOCA.
	  * Update the current page state immediately so F5 is not required.
	  */
	 if(jQuery("#hdntitle").val() == "FinalApprovals"
	         && jQuery("#chkMocClosed").is(":checked"))
	 {
	     jQuery("#hdnStatusClose").val("MOC CLOSED");

	     jQuery("#chkMocClosed")
	         .prop("checked", true)
	         .prop("disabled", true);
	 }
	 
	 //sriram
	// fileManagerPopUp(MocKeyid, "MOC", "", "", "WhatifFilemgr", "create");
	// fileManagerPopUp(MocKeyid, "MOC", "", "", "HazopFilemgr", "create");
	// var title=jQuery("#hdntitle").val();
	 //jQuery("#hdntitle").val(title);
	 //processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
	 
	 //Added for no referesh approval completion
	 processAjaxCalls("InitialApprovalCount.mocn","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
	 
	 processAjaxCalls("HazopApprovalCount.mocn","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","");
	 
	 processAjaxCalls("FinalApprovalCount.mocn","&MocKeyid="+MocKeyid,"FinalApproval_successcallback","");
	 
	 setTimeout(function () {

		    processGridnew(
		        "InitialApproval_input.mocn",
		        "q=2&MocKeyid=" + MocKeyid,
		        "InitialGrid",
		        "pager",
		        "RFC QUESTIONNAIRE",
		        "docDoubleClick",
		        "",
		        "InitialloadComFunction"
		    );

		    processGridnew(
		        "FinalApproval_input.mocn",
		        "q=2&MocKeyid=" + MocKeyid,
		        "FinalApprovalGrid",
		        "pager",
		        "",
		        "docDoubleClick",
		        "",
		        "FinalloadComFunction"
		    );

		    processGridnew(
		        "HazopApproval_input.mocn",
		        "q=2&MocKeyid=" + MocKeyid,
		        "HazopApprovalGrid",
		        "pager",
		        "",
		        "docDoubleClick",
		        "",
		        "HazoploadComFunction"
		    );

		}, 500);
	 

	 //Added for no referesh approval completion
		
	 
	 
	 processGridnew("BasisofChange_input.mocn","q=2&MocKeyid="+MocKeyid,"BasisGrd","BasisGrdpager","","","","Basisgridcompletecallback");
	 processGridnew("MOCTeamSusscess_input.mocn","q=2&MocKeyid="+MocKeyid,"TeamGrid", "Teampagerid","","", "", "TeamloadComFunction");    
     //processGridnew("InitialApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"InitialGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","InitialloadComFunction");	 
	 //processGridnew("FinalApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"FinalApprovalGrid","pager","","docDoubleClick","","FinalloadComFunction");	
     //processGridnew("HazopApproval_input.mocn","q=2&MocKeyid="+MocKeyid,"HazopApprovalGrid","pager","","docDoubleClick","","HazoploadComFunction");	
     processGridnew("WhatIFEntry_input.mocn","q=2&MocKeyid="+MocKeyid,"WhatifGrid","Whatifpager","Whatif", "","","");
     
     processGridnew("HazopEntry_input.mocn","q=2&MocKeyid="+MocKeyid,"Hazopgrid","Hazoppager","Hazop", "","","");
	 // adding new -- 07Sep2026
	 processGridnew("PssrChecklist_input.mocn","q=2&MocKeyid="+MocKeyid,"PSSRGrid","pager","","","","PssrGridloadComFunction"); 			
	// adding new -- 07Sep2026
     processGridnew("PSSRRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","Reccommendation","docDoubleClick","","ReccommendloadComFunction");	
	 processGridnew("MocClosure_input.mocn","q=2&MocKeyid="+MocKeyid,"MOCCGrid","pager","","","","MOCClosureloadComFunction");	
	 processGridnew("Questionaire_input.mocn","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","","QuestionnaireloadComFunction");	
	 processGridnew("HazopRecommendations_input.mocn","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	

	 }
	 
	 function WHconvertJsonArr(){
			var allrow = jQuery("#HazopReccommendGrid").jqGrid('getRowData');
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
				if(jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':disabled')==true)
					{
					continue;
					}
		           
		            
				
				if (jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':checked')) {
					
					jsonArrO += '{';
					for ( var colName in row) {
						if (row[colName].substring(0, 6) != '<input') {
							jsonArrO += '"' + colName + '":"' + row[colName] + '",';
						} else {
							var x = row[colName].indexOf("id=") + 4;
							var y = row[colName].substring(x);
							var z = y.indexOf('"');
							var cellId = y.substring(0, z);
							if (jQuery("#" + cellId).attr("type") == "checkbox"){
								val = jQuery('#' + cellId).is(':checked') ? 'Y'
										:'N';					  
							} else {
								val = jQuery('#' + cellId).val();
							}
							jsonArrO += '"' + colName + '":"' + val + '",';
						}
					}
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
	 
	  function HazopReccommendloadComFunction()
		{
		      
				var rowid=jQuery('#hdnrowid').val();
				var MstKeyid=jQuery("#hdnMocKeyId").val();
			
				 var row = jQuery("#HazopReccommendGrid").jqGrid('getDataIDs');
				    ////////////////////////////////////////////////////alert("row"+row);
					 var cm = jQuery("#HazopReccommendGrid").jqGrid("getGridParam", "colModel");
				 for(var i=0;i<row.length;i++)
					 {
						 var detailsid = jQuery("#HazopReccommendGrid").jqGrid('getCell',row[i],"hdnMocrKeyid");
						//mo //////////////////////alert("Keyid"+detailsid);
						 var status = jQuery("#HazopReccommendGrid").jqGrid('getCell',row[i],"cmbMocrStatus");	
					   if(detailsid.trim().length>0){ 
						
							    jQuery('#HazopReccommendGrid').setSelection(row[i], false);
							  
							    jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('disabled',false);
							    jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('checked',true);
							    jQuery("#HazopReccommendGrid").jqGrid('setCell',row[i],'selctVal','1');
						   }
					   if(status=="C"){ 
		    			   ////////////////////////////////////////////////alert("status");
		    			    jQuery('#HazopReccommendGrid').setSelection(row[i], false);
		    			   jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('disabled',true);
		    			   jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('checked',false);

		    	   }
					   

					 }		
		
			
	} 

	    /*
	    function ReccommendloadComFunction()
		{
		        var momactnpln=jQuery('#hdnactnmode').val();
				var rowid=jQuery('#hdnrowid').val();
				var MstKeyid=jQuery("#hdnMocKeyId").val();
			
				 var row = jQuery("#PSSRReccomendGrid").jqGrid('getDataIDs');
				    ////////////////////////////////////////////////////alert("row"+row);
					 var cm = jQuery("#PSSRReccomendGrid").jqGrid("getGridParam", "colModel");
					 ////////////////////////////////////////////////////alert("cm"+cm);
					// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
					  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
					 for(var i=0;i<row.length;i++)
					 {
						 var detailsid = jQuery("#PSSRReccomendGrid").jqGrid('getCell',row[i],"hdnPsrrmasterid");	
					//////////////////////////////////////////////alert("Detail:::"+detailsid);
						 var status = jQuery("#PSSRReccomendGrid").jqGrid('getCell',row[i], "cmbPsrrStatus");
						// //////////////////////alert(status)
						 if(status=="C"){ 
							 jQuery('#PSSRReccomendGrid').setSelection(row[i], false);
							    jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('checked',false);
							    jQuery("#PSSRReccomendGrid").jqGrid('setCell',row[i],'selctVal','0');
							    
							   jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('disabled',true);
							 
						 }
						 else if(detailsid.trim().length>0){ 
							 //  ////////////////////alert("inside PSSR");   
							    jQuery('#PSSRReccomendGrid').setSelection(row[i], false);
							    jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('checked',false);
							    jQuery("#PSSRReccomendGrid").jqGrid('setCell',row[i],'selctVal','1');
							    
							   jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('disabled',false);
						   }

					 }		
	
	}
	  */  
	  // Vignesh 07Sep2026
	  function ReccommendloadComFunction()
	  {
	      var grid = jQuery("#PSSRReccomendGrid");
	      var rows = grid.jqGrid('getDataIDs');

	      var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();

	      /*
	       * Important:
	       * PSSR Recommendation rows must always start UNCHECKED.
	       */
	      grid.jqGrid("resetSelection");

	      for (var i = 0; i < rows.length; i++)
	      {
	          var rowId = rows[i];

	          var status = jQuery.trim(
	              grid.jqGrid('getCell', rowId, "cmbPsrrStatus") || ""
	          );

	          var chk = jQuery("#jqg_PSSRReccomendGrid_" + rowId);

	          /*
	           * Always unchecked when grid loads.
	           */
	          chk.prop("checked", false);

	          grid.jqGrid(
	              'setCell',
	              rowId,
	              'selctVal',
	              '0'
	          );

	          /*
	           * Completed recommendation or view/approval mode
	           * must not be selectable.
	           */
	          if (status == "C" ||
	              status == "Completed" ||
	              pageMode == "view" ||
	              pageMode == "approval")
	          {
	              chk.prop("disabled", true);
	          }
	          else
	          {
	              chk.prop("disabled", false);
	          }
	      }
	      
	      setTimeout(function()
	    		  {
	    		 //     bindPSSRReccomendGridCheckboxEvents();
	    		  }, 100); 
	  }
	  // Vignesh 07Sep2026
	    function HazoploadComFunction(){
	    	
	  	  
	        var row = jQuery("#HazopApprovalGrid").jqGrid('getDataIDs');
	    	 var cm = jQuery("#HazopApprovalGrid").jqGrid("getGridParam", "colModel");
	    	 var initial =jQuery("#hdnInitialApproval").val();
	    	
	    		var WHCount=jQuery("#hdnWHCount").val();
	    	
	    	    var status = jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"selMctcHzaprovedStatus");	
	    		 var empId=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    	     var Count=jQuery("#hdnHazopcount").val();
	    	     var rolelevel=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");
	    			//////////////////////////////////////alert("rolelevel"+rolelevel);
	    	     var userid=jQuery('#hdnuserid').val();
	    	     
	    	 /* if(initial=="Pending"|| WHCount=="view"){
	    		 for(var i=0;i<row.length;i++)	{
	    		////////////////alert("In If");
	    			 jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);  	 
	    		 
	    			 } 
	    		 
	    	 } */
	    	 
	    	 var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	    	 if(initial=="Pending"|| WHCount=="view"||pageMode == "view" ){ //-ADDED CHANGES
	    		 for(var i=0;i<row.length;i++)	
	    		 
	    		 {
	    		////////////////alert("In If");
	    			 jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);  	 
	    		 
	    	} }
	    	
	    	 else {
	    	 
	    	 for(var i=0;i<row.length;i++)
	    	 {
	    		 var status = jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"selMctcHzaprovedStatus");	
	    		 var empId=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    	     var Count=jQuery("#hdnHazopcount").val();
	    	     var rolelevel=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");
	    			//////////////////////////////////////alert("rolelevel"+rolelevel);
	    	     var userid=jQuery('#hdnuserid').val();
	    /* 	     if(initial=="Pending"){
	    	    	
	    	    	  jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);  
	    	     }else{ */
	    		   if(status=="A"||status== "R"){ 
	    			   ////////////////////////////////////////////////alert("status");
	    			    jQuery('#HazopApprovalGrid').setSelection(row[i], false);
	    			   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);
	    	   }
	    	
	    		   else if (Count==rolelevel && empId==userid){
			//	alert("Inside");
					   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',false);
					   ////////////////////////////////////alert("after");   
				   }
	    		    else {
		 	    	//	alert("In else")
		 	    			   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);    
		 	    		   } 
	    		
	    	     }
	    	   
	    	 }	
	    	
	    	 }
	    

	    function FinalloadComFunction(){
	    	
	    	   
	        var row = jQuery("#FinalApprovalGrid").jqGrid('getDataIDs');
	    	 var cm = jQuery("#FinalApprovalGrid").jqGrid("getGridParam", "colModel");
			 var initial =jQuery("#hdnInitialApproval").val();
	 //  alert("initial:::"+initial);
	    	 var Hazop=jQuery("#hdnHazopApproval").val();
	//  //////alert("Hazop:::"+Hazop);
	    	var Closurecount=jQuery("#hdnClosurecount").val();
	 //  ////alert("Closurecount"+Closurecount);
	    	   if((initial=="Closed" && Hazop=="Pending" && Closurecount=="view")||(initial=="Pending" && Hazop=="Pending" && Closurecount=="view")||(initial=="Closed" && Hazop=="Closed" && Closurecount=="view")||(initial=="Closed" && Hazop=="Pending" && Closurecount=="view")){
	    		   for(var i=0;i<row.length;i++)
	  	    	 {
	    			   
	  	 ////alert("insidee::::");
	    	    	  jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);  
	    	     }
	    	   }
	    	   else{
	    	 for(var i=0;i<row.length;i++)
	    	 {
	    		 var status = jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"selMctcFaaprovedStatus");	
	    		 var empId=jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    		 var Count=jQuery("#hdnFinalcount").val();
	    		 var rolelevel=jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");	
	    		 var userid=jQuery('#hdnuserid').val();
	    
	    		   if(status=="A"||status== "E"){ 
	    			   ////////////////////////////////////////////////alert("status");
	    			    jQuery('#FinalApprovalGrid').setSelection(row[i], false);
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);
	    		 }
	    		   
	    		   else if (Count==rolelevel && empId==userid){
					  //////////////////////////////////////alert("Inside");
					   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',false);
					   
				   }
	    		 /*   else  if (empId==userid){
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',false);  
	    		   } */
	    		   else {
	    			////////////////////alert("In else")
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);    
	    		   }
		    	     }
	    	 }	 
	    }


function InitialloadComFunction(){
	
	 ////////////////////////////////////////////////alert("Load Complete") 
    var row = jQuery("#InitialGrid").jqGrid('getDataIDs');

	 var cm = jQuery("#InitialGrid").jqGrid("getGridParam", "colModel");
	// jQuery('input:checkbox[id=jqg_InitialGrid_'+row+']').attr('disabled',true);
	 for(var i=0;i<row.length;i++)
	 {
		 var status = jQuery("#InitialGrid").jqGrid('getCell',row[i],"selMctcInaprovedStatus");	
		 ////////////////////////////////////////////////alert(status);
		 var Count=jQuery("#hdncount").val();
		
		 var rolelevel=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtGroupNo");
	
	     var empId=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	
		 var userid=jQuery('#hdnuserid').val();
		 var rolelevel=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtGroupNo");
	//////////////////alert("rolelevel"+rolelevel);
		if(status=="A"||status== "R"){ 
			 
			    jQuery('#InitialGrid').setSelection(row[i], false);
			   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',true);
			
		   } 
	
		else if (Count==rolelevel && empId==userid){
				//////alert("elseif")
				   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',false);
				   ////////////////////////////////////alert("after");
				   
			   }
		else if(Count==null||Count==""){
				var Count=1;
				 if (Count==rolelevel && empId==userid){
						
					   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',false);
					   ////////////////////////////////////alert("after");
					   
				   }
			 }
		
		
	  else {
		  
			//////alert("if")
			 
			   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',true);    
		   }
	 }	 
}








function viewGrid(filterString){ 
	  processGridnew("Questionaire_input.ehsb",ds,"QuestionaireGrd","pager","RFC QUESTIONNAIRE","docDoubleClick","","loadComFunction");	
	  processGridnew("RiskAssesmentEntry_input.risk?keyid="+keyid,"&q=1","riskassessmentgrid","riskassessmentpager","Risk Assessment", "","","gridLoadComplete");		 
		
}



jQuery("#btnAddnew").click(function(){
	var row = jQuery("#PrjReview").jqGrid("getDataIDs");
	addPrjRow(row);
});

function addPrjRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnSrrdKeyid:" ",hdnSrrdSrpmKeyid:" ",dteSrrdRevdate:" ",cmbSrrdReviewedby:" ",txtSrrdAction:" ",cmbSrrdResponsiblity:" ",dteSrrdTargetdate:" ",
			 chkSrrdCompleted:" ",dteSrrdComdate:" " }];
	jQuery("#PrjReview").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		// ////////////////////////////////////////////////////alert("INSIDE THE ESLE");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		 var emptyItem =[{hdnSrrdKeyid:" ",hdnSrrdSrpmKeyid:" ",dteSrrdRevdate:" ",cmbSrrdReviewedby:" ",txtSrrdAction:" ",cmbSrrdResponsiblity:" ",dteSrrdTargetdate:" ",
	     chkSrrdCompleted:" ",dteSrrdComdate:" " }];
		jQuery("#PrjReview").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}



jQuery("#btnSaveQuestions").click(function() {
 	var modes=jQuery("#hdnmodes").val();
 	var MocKeyid=jQuery("#hdnMocKeyId").val();
//////////////////////////////////////////////////////alert("MocKeyid"+MocKeyid);

  var paramconvert =convertJsonArrQuestions();
 //////////////////////////////////////////////////////alert("Convert::"+paramconvert);
 processAjaxCalls("Questionaire_save.mocn","&paramconvert="+paramconvert+"&MocKeyid="+MocKeyid,"Questionaries_successCallBack","");	
// saveForm("frmMocProject","Questionaire_save.mocn?&paramconvert="+paramconvert+"&masterid="+masterid);
 });
 
function Questionaries_successCallBack(result) {
if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' ){
		popupCommonErrorMsg(result.tpmException);
	}
	else{
		var MocKeyid=jQuery("#hdnMocKeyId").val();
		processGridnew("Questionaire_input.mocn","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","",""," ");
	
	}
}
function setMandatoryApprovalRow(rowId, primaryEmpId) {
    var jqGridId = "TeamGrid";

    // 1. Keep Edit checkbox visually unchecked
    jQuery('#TeamGrid').setSelection(rowId, false);
    jQuery('input:checkbox[id=jqg_TeamGrid_' + rowId + ']')
        .prop('checked', false)
        .attr('disabled', false);

    // 2. But force this row to be saved in grid JSON
    jQuery("#TeamGrid").jqGrid('setCell', rowId, 'selctVal', '1');

    // 3. Set approval columns as Y Y Y
    jQuery("#TeamGrid").jqGrid('setCell', rowId, 'chkMctcInitial', 'Y');
    jQuery("#TeamGrid").jqGrid('setCell', rowId, 'chkMctcHazop', 'Y');
    jQuery("#TeamGrid").jqGrid('setCell', rowId, 'chkMctcfinal', 'Y');

    jQuery("#chkMctcInitial_" + jqGridId + "_" + rowId)
        .prop('checked', true)
        .removeAttr('disabled');

    jQuery("#chkMctcHazop_" + jqGridId + "_" + rowId)
        .prop('checked', true)
        .removeAttr('disabled');

    jQuery("#chkMctcfinal_" + jqGridId + "_" + rowId)
        .prop('checked', true)
        .removeAttr('disabled');

    // 4. Set primary approval employee
    if (primaryEmpId && primaryEmpId !== "{}" && primaryEmpId !== "-") {
        setFieldValue("cmbAbnmDetectedby_TeamGrid_" + rowId, primaryEmpId);
        jQuery("#TeamGrid").jqGrid('setCell', rowId, 'hdnMctcempid', primaryEmpId);
    }

    // 5. Enable primary combo
    jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId).combobox('enable');

    console.log("Mandatory approval row set: rowId=" + rowId + ", emp=" + primaryEmpId);
}
/*function getTeamRowByRole(roleName) {
    var rows = jQuery("#TeamGrid").jqGrid('getDataIDs');

    for (var i = 0; i < rows.length; i++) {
        var currentRole = jQuery.trim(
            jQuery("#TeamGrid").jqGrid("getCell", rows[i], "txtRoleName")
        );

        if (currentRole.toLowerCase() === roleName.toLowerCase()) {
            return rows[i];
        }
    }

    return null;
} */

function normalizeRoleName(value) {
    return jQuery.trim(value || "")
        .replace(/&nbsp;/g, " ")
        .replace(/\u00A0/g, " ")
        .replace(/\s+/g, " ")
        .toLowerCase();
}

function getTeamRowByRole(roleName) {
    var rows = jQuery("#TeamGrid").jqGrid('getDataIDs');
    var targetRole = normalizeRoleName(roleName);

    for (var i = 0; i < rows.length; i++) {
        var currentRole = normalizeRoleName(
            jQuery("#TeamGrid").jqGrid("getCell", rows[i], "txtRoleName")
        );

        if (currentRole === targetRole) {
            return rows[i];
        }
    }

    return null;
}

/* function setPrimaryApprovalValue(rowId, empId) {
    if (!rowId) return;

    empId = jQuery.trim(empId || "");

    if (empId !== "" && empId !== "{}" && empId !== "-") {
        setFieldValue("cmbAbnmDetectedby_TeamGrid_" + rowId, empId);
        jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", empId);
        jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId).combobox("enable");
    }
} */
function setPrimaryApprovalValue(rowId, empId) {

    if (!rowId) {
        return;
    }

    var lockedEmpId = getLockedPrimaryEmpIdForRow(rowId);

    if (isValidTeamValue(lockedEmpId)) {
        empId = lockedEmpId;
    }

    empId = jQuery.trim(empId || "");

    if (empId !== "" && empId !== "{}" && empId !== "-") {

        setFieldValue("cmbAbnmDetectedby_TeamGrid_" + rowId, empId);
        jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", empId);

        if (isValidTeamValue(lockedEmpId)) {
            disableInitialApprovedPrimaryCell(rowId, lockedEmpId);
        } else {
            jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId).combobox("enable");
        }
    }
}
function isValidTeamValue(val) {
    val = jQuery.trim(val || "");

    return val !== ""
        && val !== "{}"
        && val !== "-"
        && val.toLowerCase() !== "null"
        && val.toLowerCase() !== "undefined";
}

function getLockedPrimaryEmpIdForRow(rowId) {

    if (!rowId) {
        return "";
    }

    var roleId = jQuery.trim(
        jQuery("#TeamGrid").jqGrid("getCell", rowId, "hdnMctcroleid") || ""
    );

    if (roleId !== "" && initialApprovedPrimaryRoleMap[roleId]) {
        return initialApprovedPrimaryRoleMap[roleId];
    }

    return "";
}

function isPrimaryApprovalLocked(rowId) {
    return isValidTeamValue(getLockedPrimaryEmpIdForRow(rowId));
}

function disableInitialApprovedPrimaryCell(rowId, approvedEmpId) {

    if (!rowId) {
        return;
    }

    approvedEmpId = jQuery.trim(approvedEmpId || "");

    var comboId = "#cmbAbnmDetectedby_TeamGrid_" + rowId;
    var comboObj = jQuery(comboId);

    if (comboObj.length === 0) {
        setTimeout(function () {
            disableInitialApprovedPrimaryCell(rowId, approvedEmpId);
        }, 100);
        return;
    }

    var roleName = jQuery.trim(
        jQuery("#TeamGrid").jqGrid("getCell", rowId, "txtRoleName") || ""
    );

    // Keep approved DB employee fixed in visible combo and hidden cell
    if (isValidTeamValue(approvedEmpId)) {
        setFieldValue("cmbAbnmDetectedby_TeamGrid_" + rowId, approvedEmpId);
        jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", approvedEmpId);
    }

    // Disable only primary approval combo
    try {
        comboObj.combobox("disable");
    } catch (e) {}

    // Show message if user clicks disabled combo area
    comboObj.next("span.combo")
        .off("mousedown.initialApprovedLock")
        .on("mousedown.initialApprovedLock", function (e) {
            e.preventDefault();
            e.stopPropagation();

            popupCommonErrorMsg(
                "Initial approval is already completed for " + roleName +
                ". Primary Approval cannot be changed."
            );

            return false;
        });

    console.log(
        "Primary approval locked. rowId=" + rowId +
        ", roleName=" + roleName +
        ", empId=" + approvedEmpId
    );
}

function applyInitialApprovedPrimaryLocks() {

    var rows = jQuery("#TeamGrid").jqGrid("getDataIDs");

    if (!rows || rows.length === 0) {
        return;
    }

    for (var i = 0; i < rows.length; i++) {

        var rowId = rows[i];
        var lockedEmpId = getLockedPrimaryEmpIdForRow(rowId);

        if (isValidTeamValue(lockedEmpId)) {
            disableInitialApprovedPrimaryCell(rowId, lockedEmpId);
        }
    }
}

function loadInitialApprovedPrimaryLocks() {

    var MocKeyid = jQuery.trim(jQuery("#txtRfcmKeyid").val() || "");

    if (!isValidTeamValue(MocKeyid)) {
        return;
    }

    processAjaxCalls(
        "getInitialApprovedPrimaryRoles.mocn",
        "MocKeyid=" + encodeURIComponent(MocKeyid),
        "loadInitialApprovedPrimaryLocks_OnSuccess",
        "loadInitialApprovedPrimaryLocks_OnError"
    );
}
function reLockPrimaryApprovalIfInitialApproved(rowId) {

    if (!rowId) {
        return false;
    }

    var lockedEmpId = getLockedPrimaryEmpIdForRow(rowId);

    if (!isValidTeamValue(lockedEmpId)) {
        return false;
    }

    // Re-set approved employee
    setFieldValue("cmbAbnmDetectedby_TeamGrid_" + rowId, lockedEmpId);
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", lockedEmpId);

    // Re-disable primary approval combo
    try {
        jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId).combobox("disable");
    } catch (e) {}

    // Re-bind alert on disabled combo span
    var roleName = jQuery.trim(
        jQuery("#TeamGrid").jqGrid("getCell", rowId, "txtRoleName") || ""
    );

    jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId)
        .next("span.combo")
        .off("mousedown.initialApprovedLock")
        .on("mousedown.initialApprovedLock", function (e) {

            e.preventDefault();
            e.stopPropagation();

            popupCommonErrorMsg(
                "Initial approval is already completed for " + roleName +
                ". Primary Approval cannot be changed."
            );

            return false;
        });

    return true;
}
function loadInitialApprovedPrimaryLocks_OnSuccess(result) {

    initialApprovedPrimaryRoleMap = {};

    if (result == null || result.success === false || result.success === "false") {
        return;
    }

    var rows = result.rows;

    if (typeof rows === "string") {
        try {
            rows = JSON.parse(rows);
        } catch (e) {
            rows = [];
        }
    }

    if (rows == null) {
        rows = [];
    }

    for (var i = 0; i < rows.length; i++) {

        var roleId = jQuery.trim(rows[i].roleId || "");
        var empId  = jQuery.trim(rows[i].empId || "");

        if (isValidTeamValue(roleId) && isValidTeamValue(empId)) {
            initialApprovedPrimaryRoleMap[roleId] = empId;
        }
    }

    applyInitialApprovedPrimaryLocks();
}

function loadInitialApprovedPrimaryLocks_OnError(result) {
    console.log("Unable to load initial approved primary locks.");
}
//ADDED NEW 04 JUNE 2026 -- VIGNESH //
function clearCapexRestrictedTeamRow(rowId) {

    if (!rowId) {
        return;
    }

    var jqGridId = "TeamGrid";

    // 1. Uncheck and disable edit checkbox
    jQuery("#TeamGrid").setSelection(rowId, false);

    jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]")
        .prop("checked", false)
        .attr("disabled", true);

    // 2. Force row selection/save flag as 0
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", "0");

    // 3. Set approval flags as N
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcInitial", "N");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcHazop", "N");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcfinal", "N");

    jQuery("#chkMctcInitial_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    jQuery("#chkMctcHazop_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    jQuery("#chkMctcfinal_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    // 4. Clear visible comboboxes
    try {
        jQuery("#cmbAppTyepid_TeamGrid_" + rowId)
            .combobox("clear")
            .combobox("setValue", "")
            .combobox("setText", "")
            .combobox("disable");
    } catch (e) {}

    try {
        jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId)
            .combobox("clear")
            .combobox("setValue", "")
            .combobox("setText", "")
            .combobox("disable");
    } catch (e) {}

    try {
        jQuery("#cmbMctcempid2_TeamGrid_" + rowId)
            .combobox("clear")
            .combobox("setValue", "")
            .combobox("setText", "")
            .combobox("disable");
    } catch (e) {}

    // 5. MOST IMPORTANT:
    // Clear jqGrid visible + hidden cells.
    // convertJsonArrTeam() reads these cells, not only the visible combo text.
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbAppTyepid", "");

    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbAbnmDetectedby", "");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", "");

    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbMctcempid2", "");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", "");

    console.log("CapEx N cleared rowId=" + rowId);
}
function clearCapexRestrictedRowsIfNo() {

    var capexVal = jQuery.trim(jQuery("#chckMocRfcmCapex").val() || "");

    if (capexVal !== "N") {
        return;
    }

    var pillarRow = getTeamRowByRole("Pillar Champion");
    var sbuHeadRow = getTeamRowByRole("Sbu Head");

    clearCapexRestrictedTeamRow(pillarRow);
    clearCapexRestrictedTeamRow(sbuHeadRow);
}

//  ADDED NEW 04 JUNE 2026 -- VIGNESH //

function loadPillarChampionComboOnly(rowId) {

    if (!rowId) {
        console.log("Pillar Champion rowId empty.");
        return;
    }

    var sugflid = jQuery.trim(
        jQuery("#hdnSuggestionFlid").val()
        || jQuery("#Sugflid").val()
        || jQuery("#flid").val()
        || jQuery("#frmMocProject input[id='flid']").val()
        || ""
    );

    if (sugflid.length === 0 || sugflid === "null" || sugflid === "{}") {
        console.log("Pillar Champion combo not loaded. sugflid empty.");
        return;
    }

    var url = "pillarChampionEmployeeCombo.mocn?sugflid=" + encodeURIComponent(sugflid);

    setTimeout(function () {
        applyPillarChampionComboUrl(rowId, url);
    }, 300);
}
function applyPillarChampionComboUrl(rowId, url) {

    var comboId1 = "#cmbAbnmDetectedby_TeamGrid_" + rowId;
    var comboId2 = "#cmbMctcempid_TeamGrid_" + rowId;

    var comboObj = jQuery(comboId1);

    if (comboObj.length === 0) {
        comboObj = jQuery(comboId2);
    }

    if (comboObj.length === 0) {
        console.log("Pillar Champion combo not found. rowId=" + rowId);
        return;
    }

    console.log("Applying Pillar Champion filtered combo URL ::: " + url);

    // Store URL only once
    comboObj.data("pillarChampionUrl", url);
    comboObj.data("pillarChampionLoaded", "N");

    comboObj.combobox("clear");
    comboObj.combobox("loadData", []);
    comboObj.combobox("options").url = url;
    comboObj.combobox("enable");

    // Bind only once
    comboObj.combobox({
        onShowPanel: function () {

            var cb = jQuery(this);
            var pcUrl = cb.data("pillarChampionUrl");
            var alreadyLoaded = cb.data("pillarChampionLoaded");

            if (alreadyLoaded === "Y") {
                return;
            }

            cb.data("pillarChampionLoaded", "Y");
            cb.combobox("options").url = pcUrl;
            cb.combobox("reload", pcUrl);
        }
    });
}

function setMandatoryWorkflowApproval(rowId, empId) {
    if (!rowId) return;

    var jqGridId = "TeamGrid";

    // Keep Edit checkbox visually unchecked
    jQuery("#TeamGrid").setSelection(rowId, false);
    jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]")
        .prop("checked", false)
        .attr("disabled", false);

    // But mark row for save
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", "1");

    // Force approval values as Y Y Y
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcInitial", "Y");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcHazop", "Y");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcfinal", "Y");

    jQuery("#chkMctcInitial_" + jqGridId + "_" + rowId)
        .prop("checked", true)
        .removeAttr("disabled");

    jQuery("#chkMctcHazop_" + jqGridId + "_" + rowId)
        .prop("checked", true)
        .removeAttr("disabled");

    jQuery("#chkMctcfinal_" + jqGridId + "_" + rowId)
        .prop("checked", true)
        .removeAttr("disabled");

    // Set primary approval employee
    setPrimaryApprovalValue(rowId, empId);

    console.log("Mandatory row set rowId=" + rowId + ", empId=" + empId);
}

//Vignesh 23May2026
//Reload filtered Pillar Champion combo when that row is clicked/selected.
//This prevents employee.commonFilter from coming back.

function refreshPillarChampionComboIfNeeded(rowId) {

if (!rowId) {
   return;
}

var pillarChampRow = getTeamRowByRole("Pillar Champion");

if (!pillarChampRow) {
   return;
}

if (String(rowId) === String(pillarChampRow)) {
   setTimeout(function () {
       loadPillarChampionComboOnly(rowId);
   }, 100);
}
}

/* function bindTeamGridEditCheckboxEvents() {

    jQuery("input:checkbox[id^='jqg_TeamGrid_']")
        .off("click.teamGrid change.teamGrid")

        // Stop jqGrid row-click from eating the first checkbox click
        .on("click.teamGrid", function(e) {
            e.stopPropagation();
        })

        .on("change.teamGrid", function(e) {
            e.stopPropagation();

            var rowId = this.id.replace("jqg_TeamGrid_", "");
            var checked = jQuery(this).is(":checked");

            jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", checked ? "1" : "0");

            if (checked) {
                jQuery("#cmbAppTyepid_TeamGrid_" + rowId).combobox("setValue", "S");
            } else {
                jQuery("#cmbAppTyepid_TeamGrid_" + rowId).combobox("setValue", "");
            }
        });
}
 */
 
 function bindWhatIfGridEditCheckboxEvents() {

	    jQuery("input:checkbox[id^='jqg__']")
	        .off(".teamGridFirstClickFix")

	        // Important:
	        // mousedown must be stopped before jqGrid row/cell selection catches it.
	        .on("mousedown.teamGridFirstClickFix click.teamGridFirstClickFix keydown.teamGridFirstClickFix", function(e) {
	            e.stopPropagation();
	        })

	        .on("change.teamGridFirstClickFix", function(e) {

	            e.stopPropagation();

	            var rowId = this.id.replace("jqg_WhatifGrid_", "");
	            var checked = jQuery(this).is(":checked");
	            var jqGridId = "WhatifGrid";

	            // Update hidden save flag
	            jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", checked ? "1" : "0"); 
	            
	        });
 }
 
 function bindTeamGridEditCheckboxEvents() {

	    jQuery("input:checkbox[id^='jqg_TeamGrid_']")
	        .off(".teamGridFirstClickFix")

	        // Important:
	        // mousedown must be stopped before jqGrid row/cell selection catches it.
	        .on("mousedown.teamGridFirstClickFix click.teamGridFirstClickFix keydown.teamGridFirstClickFix", function(e) {
	            e.stopPropagation();
	        })

	        .on("change.teamGridFirstClickFix", function(e) {

	            e.stopPropagation();

	            var rowId = this.id.replace("jqg_TeamGrid_", "");
	            var checked = jQuery(this).is(":checked");
	            var jqGridId = "TeamGrid";

	            // Update hidden save flag
	            jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", checked ? "1" : "0");

	            if (checked) {

	                try {
	                    makeRowEditable(jqGridId, rowId);
	                } catch (ex) {}

	                try {
	                    jQuery("#cmbAppTyepid_" + jqGridId + "_" + rowId)
	                        .combobox("setValue", "S");
	                } catch (ex) {}

	            } else {

	                try {
	                    restoreEdit(jqGridId, rowId);
	                } catch (ex) {}

	                try {
	                    jQuery("#cmbAppTyepid_" + jqGridId + "_" + rowId)
	                        .combobox("setValue", "");
	                } catch (ex) {}
	            }

	            // Keep your existing custom row logic alive
	            try {
	                TeamGrid_selectRow(rowId);
	            } catch (ex) {}

	            // Re-apply your existing locks after checkbox logic
	            setTimeout(function () {
	                try { applyMocTeamWorkflowRoleLock(); } catch (ex) {}
	                try { reLockPrimaryApprovalIfInitialApproved(rowId); } catch (ex) {}
	                try { refreshPillarChampionComboIfNeeded(rowId); } catch (ex) {}
	            }, 100);
	        });
	}
 // -- vignesh 07sep2026
function bindPSSRReccomendGridCheckboxEvents()
{
    jQuery("input:checkbox[id^='jqg_PSSRReccomendGrid_']")
        .off(".pssrRecFirstClickFix")

        /*
         * Stop jqGrid from handling the checkbox click first.
         * This is the same pattern already working in TeamGrid.
         */
        .on(
            "mousedown.pssrRecFirstClickFix " +
            "click.pssrRecFirstClickFix " +
            "keydown.pssrRecFirstClickFix",
            function(e)
            {
                e.stopPropagation();
            }
        )

        .on(
            "change.pssrRecFirstClickFix",
            function(e)
            {
                e.stopPropagation();

                var rowId =
                    this.id.replace(
                        "jqg_PSSRReccomendGrid_",
                        ""
                    );

                var checked = jQuery(this).is(":checked");

                jQuery("#PSSRReccomendGrid")
                    .jqGrid(
                        "setCell",
                        rowId,
                        "selctVal",
                        checked ? "1" : "0"
                    );

                if(checked)
                {
                    /*
                     * Exactly ONE call to makeRowEditable.
                     */
                    try
                    {
                        makeRowEditable(
                            "PSSRReccomendGrid",
                            rowId
                        );
                    }
                    catch(ex){}

                    /*
                     * Run PSSR-specific logic only AFTER
                     * editors have been created.
                     */
                    try
                    {
                        PSSRReccomendGrid_selectRow(rowId);
                    }
                    catch(ex){}
                }
                else
                {
                    /*
                     * Existing values will now restore correctly
                     * because presrvCellValue was not overwritten.
                     */
                    try
                    {
                        restoreEdit(
                            "PSSRReccomendGrid",
                            rowId
                        );
                    }
                    catch(ex){}
                }
            }
        );
}
 // -- vignesh 07sep2026
 
 
 function bindTeamGridDropdownFirstClickFix() {

	    /*
	     * Fix native select dropdowns inside TeamGrid
	     */
	    jQuery("#TeamGrid")
	        .off("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select")
	        .on("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Fix EasyUI combobox original input inside TeamGrid
	     */
	    jQuery("#TeamGrid")
	        .off("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f")
	        .on("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Important:
	     * EasyUI combobox visible part is not the original input.
	     * User actually clicks span.combo / textbox / arrow.
	     */
	    jQuery("#TeamGrid")
	        .off("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow")
	        .on("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow", function (e) {
	            e.stopPropagation();
	        });
	}

 
 
 function bindWhatIfDropdownFirstClickFix() {

	    /*
	     * Fix native select dropdowns inside TeamGrid
	     */
	    jQuery("#WhatifGrid")
	        .off("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select")
	        .on("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Fix EasyUI combobox original input inside TeamGrid
	     */
	    jQuery("#WhatifGrid")
	        .off("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f")
	        .on("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Important:
	     * EasyUI combobox visible part is not the original input.
	     * User actually clicks span.combo / textbox / arrow.
	     */
	    jQuery("#WhatifGrid")
	        .off("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow")
	        .on("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow", function (e) {
	            e.stopPropagation();
	        });
	}
 
 function bindHazopDropdownFirstClickFix() {

	    /*
	     * Fix native select dropdowns inside TeamGrid
	     */
	    jQuery("#Hazopgrid")
	        .off("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select")
	        .on("mousedown.teamGridDropdownFix click.teamGridDropdownFix keydown.teamGridDropdownFix", "select", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Fix EasyUI combobox original input inside TeamGrid
	     */
	    jQuery("#Hazopgrid")
	        .off("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f")
	        .on("mousedown.teamGridComboFix click.teamGridComboFix keydown.teamGridComboFix", "input.easyui-combo, input.combo-f", function (e) {
	            e.stopPropagation();
	        });

	    /*
	     * Important:
	     * EasyUI combobox visible part is not the original input.
	     * User actually clicks span.combo / textbox / arrow.
	     */
	    jQuery("#Hazopgrid")
	        .off("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow")
	        .on("mousedown.teamGridComboSpanFix click.teamGridComboSpanFix keydown.teamGridComboSpanFix", "span.combo, span.combo input, span.combo .combo-arrow", function (e) {
	            e.stopPropagation();
	        });
	}
function TeamloadComFunction(){

    var row = jQuery("#TeamGrid").jqGrid('getDataIDs');
    ////////////////////////////////////////////////////alert("row"+row);
	 var cm = jQuery("#TeamGrid").jqGrid("getGridParam", "colModel");

	 
	 for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#TeamGrid").jqGrid('getCell',row[i],"hdnMctcKeyid");	
	//alert("Detail:::"+detailsid);
	var mode= jQuery('#hdnmode').val();
  ////////////////////////alert("mode"+mode);
var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
	if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
		//disableGridSort("TeamGrid");
		jQuery('#TeamGrid').setSelection(row[i], false);
		 jQuery('input:checkbox[id=jqgh_TeamGrid_'+row[i]+']').attr('checked',false);
		  // jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','0');
		  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
		   jQuery('input:checkbox[id=jqg_TeamGrid_'+row[i]+']').attr('disabled',true);
	}
	else if(mode=="create"||mode=="modify"){
		//var jqGridId="TeamGrid"; --- commented by sriram
/*  sriram start*/
/* for(var i = 0; i < row.length; i++) {
    var jqGridId = "TeamGrid";

    if (i === 7 || i === 8) {
        // DMT Leader and Safety Manager — keep Edit checked
        jQuery('#TeamGrid').setSelection(row[i], true);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').prop('checked', true);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
        jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '1');
    } else {
        // All other rows — uncheck Edit checkbox too
        jQuery('#TeamGrid').setSelection(row[i], false);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').prop('checked', false);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
        jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '0');
    }
} */

for (var i = 0; i < row.length; i++) {
    var jqGridId = "TeamGrid";
   
    //alert(row[i]);
    
    //alert("entered");
    
jQuery("#cmbMctcempid2_TeamGrid_"+row[i]).on(' click keydown', function(e){
								    e.stopPropagation();
								});
   // if (i === 2 || i === 5 || i === 6 || i === 7) 
    
    	if ( i === 5 || i === 6 ){
        // DMT Leader, Pillar Champion, Sbu Head, Safety Manager
        // — auto-check Edit and all approval checkboxes, set PRIMARY
   //     jQuery('#TeamGrid').setSelection(row[i], true);
   //     jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').prop('checked', true);
       // jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
       
         // Pillar Champion (i=3) and SBU Head (i=4) disabled until CapEx Yes
   /*  if (i === 3 || i === 4) {
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', true);
    } else {
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
    } */
  /*   if (i === 3 || i === 4) {
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', true);
        jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).attr('disabled', true);
        jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).attr('disabled', true);
        jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).attr('disabled', true);
        jQuery("#cmbAppTyepid_TeamGrid_" + row[i]).combobox('disable');
        jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[i]).combobox('disable'); */
        if (i === 5 || i === 6) {
            // UNCHECK + DISABLE by default
            jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', true).prop('checked', false);
            jQuery('#TeamGrid').setSelection(row[i], false);
            jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '0');

            jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).attr('disabled', true).prop('checked', false);
            jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).attr('disabled', true).prop('checked', false);
            jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).attr('disabled', true).prop('checked', false);

            // ✅ Use setTimeout to clear AFTER combobox is fully rendered
            (function(rowIndex) {
                setTimeout(function() {
                    jQuery("#cmbAppTyepid_TeamGrid_"      + row[rowIndex]).combobox('disable').combobox('setValue', '');
                    jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[rowIndex]).combobox('disable').combobox('setValue', '').combobox('setText', '');
                    jQuery("#cmbMctcempid2_TeamGrid_"    + row[rowIndex]).combobox('disable').combobox('setValue', '').combobox('setText', '');
                }, 100);
            })(i);

        } else {
            jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
        }
        jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '1');

       /*  jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).prop('checked', true);
        jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).removeAttr('disabled');

        jQuery("#chkMctcHazop_" + jqGridId + "_" + row[i]).prop('checked', true);
        jQuery("#chkMctcHazop_" + jqGridId + "_" + row[i]).removeAttr('disabled');

        jQuery("#chkMctcfinal_" + jqGridId + "_" + row[i]).prop('checked', true);
        jQuery("#chkMctcfinal_" + jqGridId + "_" + row[i]).removeAttr('disabled');

        jQuery("#cmbAppTyepid_TeamGrid_" + row[i]).combobox("setValue", "P"); */
        
       /*  jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).prop('checked', true);
        jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).prop('checked', true);
        jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).prop('checked', true);
 */
        // Only re-enable checkboxes for DMT Leader (i=2) and Safety Manager (i=5)
        // Pillar Champion (i=3) and SBU Head (i=4) stay disabled until CapEx Yes
      /*   if (i !== 3 && i !== 4) {
            jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).removeAttr('disabled');
            jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).removeAttr('disabled');
            jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).removeAttr('disabled');
        } */
        
        if (i !== 5 && i !== 6) {
            //jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '1');
           // jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[i]).combobox('setValue', '').combobox('disable');
           // Enable Name only for DMT Leader (i=2) and Safety Manager (i=5)
			if (i === 2 || i === 7) {
    jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[i]).combobox('enable');
    jQuery("#cmbMctcempid2_TeamGrid_"    + row[i]).combobox('enable');
} else {
    jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[i]).combobox('setValue', '').combobox('disable');
    jQuery("#cmbMctcempid2_TeamGrid_"    + row[i]).combobox('setValue', '').combobox('disable');
}
            jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).prop('checked', true).removeAttr('disabled');
            jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).prop('checked', true).removeAttr('disabled');
            jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).prop('checked', true).removeAttr('disabled');

         //   jQuery("#cmbAppTyepid_TeamGrid_" + row[i]).combobox("setValue", "P");
        }

      //  jQuery("#cmbAppTyepid_TeamGrid_" + row[i]).combobox("setValue", "P");

    } else {
        // All other rows — uncheck everything, leave Edit enabled for user
        jQuery('#TeamGrid').setSelection(row[i], false);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').prop('checked', false);
        jQuery('input:checkbox[id=jqg_TeamGrid_' + row[i] + ']').attr('disabled', false);
        jQuery("#TeamGrid").jqGrid('setCell', row[i], 'selctVal', '0');

        jQuery("#chkMctcInitial_" + jqGridId + "_" + row[i]).prop('checked', false);
        jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[i]).prop('checked', false);
        jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[i]).prop('checked', false);

        // Clear Approval Type for unchecked rows
        jQuery("#cmbAppTyepid_TeamGrid_" + row[i]).combobox("setValue", "");
    }
}
//bindTeamGridEditCheckboxEvents();
// rows NOT in [2,3,4,5] → set SECONDARY when user manually checks

for (var j = 0; j < row.length; j++) {
//	if (j === 2 || j === 5 || j === 6 || j === 7) continue;
	if ( j === 5 || j === 6 ) continue; // skip auto-checked rows
    (function(rowIndex) {
        jQuery(document).off('change', '#jqg_TeamGrid_' + row[rowIndex])
                        .on('change',  '#jqg_TeamGrid_' + row[rowIndex], function() {
            var jqGridId = "TeamGrid";
            if (jQuery(this).is(':checked')) {
                jQuery("#cmbAppTyepid_" + jqGridId + "_" + row[rowIndex])
                    .combobox("setValue", "S");
            } else {
                jQuery("#cmbAppTyepid_" + jqGridId + "_" + row[rowIndex])
                    .combobox("setValue", "");
            }
        });
    })(j);
}

//─────────────────────────────────────────────────────────────
		/* sriram end */
		var initiator = jQuery('#hdnResponsibility').val();

		var id=1;

		var flid = jQuery("#frmMocProject input[id='section']").val();
/* alert("flid"+flid); */
		/* if(flid=="SEC0000016"){
		//BCM05205
			var Process='BCM05205';
			var Mechanical='BCM05203';
			var Inst='BCM04984';
			var Electrical='BCM05215';
			var EHSHead='EMP13892';
			//var EHSHead='EMP00001';
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
		}

		else if(flid=="SEC0000014"){
			
			//BCM05205
				var id=1;
				var Process='BCM04938';
				var Mechanical='BCM05026';
				var Inst='BCM05078';
				var Electrical='BCM07043'; //BCM07043
				var EHSHead='EMP13892';
				//var EHSHead='EMP00001';
					setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
				disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			}
			
		else if(flid=="SEC0000017"){
			
			//BCM05205
				var id=1;
				var Process='BCM05051';
				var Mechanical='BCM05843';
				var Inst='BCM05184';
				var Electrical='BCM05178';
				var EHSHead='EMP13892';
				//var EHSHead='EMP00001';
					setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
				disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			}
			
			else
			{
				
				var id=1;
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);
		//jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);
			} */

		
		// Primary approval // to be checked Vignesh 14May2026
			var JHLeader=jQuery("#hdnJHleader").val();
			var DMTLeader=jQuery("#hdnDMTLeader").val();
			var PA=jQuery("#hdnProcessArea").val();
			var MA=jQuery("#hdnMechArea").val();
			var IA=jQuery("#hdnInstrumentArea").val();
			var CA=jQuery("#hdnCivilArea").val();
			//alert("Civil Area hidden value CA = [" + CA + "]");
			var EA=jQuery("#hdnElectricalArea").val();
			var PH=jQuery("#hdnPBUHead").val();
			
			//mano
			var PMMechChampion = jQuery("#hdnPMMechChampion").val();  
            var PMEIChampion   = jQuery("#hdnPMEIChampion").val();   
            var PillarChampion = jQuery("#hdnPillarChampion").val();
            var SafetyMgr      = jQuery("#hdnSafetyManager").val();
		
            
			var initiatorRow = getTeamRowByRole("Initiator");
			var jhLeaderRow = getTeamRowByRole("JH Leader");
            var dmtLeaderRow = getTeamRowByRole("DMT Leader");
            
            var pmmechChampRow = getTeamRowByRole("PM Mechanical Champion");
            var pmEIChampRow = getTeamRowByRole("PM E&I Champion");
            var pillarChampRow = getTeamRowByRole("Pillar Champion");
            var sbuHRow = getTeamRowByRole("Sbu Head");
            
			var safetyManagerRow = getTeamRowByRole("Safety manager");
			var processAreaRow = getTeamRowByRole("Operations Area Incharge");
			var mechAreaRow = getTeamRowByRole("Mechanical Area Incharge");
			var elecAreaRow = getTeamRowByRole("Electrical Area Incharge");
			var insAreaRow = getTeamRowByRole("Instrumentation Area Incharge");
		//	alert("insAreaRow Area row id = [" + insAreaRow + "] | CA = [" + CA + "]");
			var civiAreaRow = getTeamRowByRole("Civil  Area Incharge");
			
			//alert("Civil Area row id = [" + civiAreaRow + "] | CA = [" + CA + "]");
			
// JH Leader: only fetch primary approval, do not force Y Y Y
setPrimaryApprovalValue(initiatorRow, initiator);
setPrimaryApprovalValue(jhLeaderRow, JHLeader);
setPrimaryApprovalValue(pmmechChampRow, PMMechChampion);
setPrimaryApprovalValue(processAreaRow, PA);
setPrimaryApprovalValue(mechAreaRow, MA);
setPrimaryApprovalValue(elecAreaRow, EA);
setPrimaryApprovalValue(insAreaRow, IA);
setPrimaryApprovalValue(civiAreaRow, CA);

setPrimaryApprovalValue(pmmechChampRow, PMMechChampion);
setPrimaryApprovalValue(pmEIChampRow, PMEIChampion);
//setPrimaryApprovalValue(pillarChampRow, PillarChampion);
loadPillarChampionComboOnly(pillarChampRow);
setPrimaryApprovalValue(sbuHRow, PH);

// Mandatory rows: DMT Leader and Safety Manager
setMandatoryWorkflowApproval(dmtLeaderRow, DMTLeader);
setMandatoryWorkflowApproval(safetyManagerRow, SafetyMgr);//changed PA to SafetyMgr

// Secondary approval should load only from DB, not default employee
setSecondaryApprovalValue(dmtLeaderRow, DMTLeader);
setSecondaryApprovalValue(safetyManagerRow, SafetyMgr);//changed PA to SafetyMgr
							
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+2,JHLeader);
			//setFieldValue("cmbAbnmDetectedby_TeamGrid_"+3,PA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+4,MA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+5,EA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+6,IA);
			//setFieldValue("cmbAbnmDetectedby_TeamGrid_"+7,CA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,PH);
		//	setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,DMTLeader);
		   setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
		
		// ADD THESE LINES RIGHT AFTER:
		/*	Vignesh commenting for proper fetch from db
		setFieldValue("cmbMctcempid2_TeamGrid_"+2,  JHLeader);
		setFieldValue("cmbMctcempid2_TeamGrid_"+3,  PA);
		setFieldValue("cmbMctcempid2_TeamGrid_"+4,  MA);
		setFieldValue("cmbMctcempid2_TeamGrid_"+5,  EA);
		setFieldValue("cmbMctcempid2_TeamGrid_"+6,  IA);
		setFieldValue("cmbMctcempid2_TeamGrid_"+7,  CA);
		setFieldValue("cmbMctcempid2_TeamGrid_"+9,  PH);
		setFieldValue("cmbMctcempid2_TeamGrid_"+8,  DMTLeader);
		setFieldValue("cmbMctcempid2_TeamGrid_"+id, initiator); 
		*/
		setSecondaryApprovalValue(row[1], JHLeader);
		setSecondaryApprovalValue(row[2], PA);
		setSecondaryApprovalValue(row[3], MA);
		setSecondaryApprovalValue(row[4], EA);
		setSecondaryApprovalValue(row[5], IA);
		setSecondaryApprovalValue(row[6], CA);
		setSecondaryApprovalValue(row[8], PH);
		setSecondaryApprovalValue(row[7], DMTLeader);
		setSecondaryApprovalValue(row[0], initiator);
		
	/*	alert(
			    "Secondary approval fetch check:\n" +
			    "Row " + row[2] + " = " + jQuery("#TeamGrid").jqGrid("getCell", row[2], "hdnMctcempid2") + "\n" +
			    "Row " + row[7] + " = " + jQuery("#TeamGrid").jqGrid("getCell", row[7], "hdnMctcempid2")
			);
		
		alert(
			    "Mandatory workflow check:\n" +
			    "DMT Row = " + dmtLeaderRow +
			    " | Primary = " + jQuery("#TeamGrid").jqGrid("getCell", dmtLeaderRow, "hdnMctcempid") +
			    " | Initial = " + jQuery("#TeamGrid").jqGrid("getCell", dmtLeaderRow, "chkMctcInitial") +
			    " | Hazop = " + jQuery("#TeamGrid").jqGrid("getCell", dmtLeaderRow, "chkMctcHazop") +
			    " | Final = " + jQuery("#TeamGrid").jqGrid("getCell", dmtLeaderRow, "chkMctcfinal") +
			    "\nSafety Row = " + safetyManagerRow +
			    " | Primary = " + jQuery("#TeamGrid").jqGrid("getCell", safetyManagerRow, "hdnMctcempid") +
			    " | Initial = " + jQuery("#TeamGrid").jqGrid("getCell", safetyManagerRow, "chkMctcInitial") +
			    " | Hazop = " + jQuery("#TeamGrid").jqGrid("getCell", safetyManagerRow, "chkMctcHazop") +
			    " | Final = " + jQuery("#TeamGrid").jqGrid("getCell", safetyManagerRow, "chkMctcfinal")
			);*/
	
		disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
		/*
	   jQuery("#TeamGrid").jqGrid('setCell',row[0],'hdnMctcempid',initiator);
		jQuery("#TeamGrid").jqGrid('setCell',row[1],'hdnMctcempid',JHLeader);
		jQuery("#TeamGrid").jqGrid('setCell',row[2],'hdnMctcempid',PA);
		jQuery("#TeamGrid").jqGrid('setCell',row[3],'hdnMctcempid',MA);
		jQuery("#TeamGrid").jqGrid('setCell',row[4],'hdnMctcempid',EA);
		jQuery("#TeamGrid").jqGrid('setCell',row[5],'hdnMctcempid',IA);
		jQuery("#TeamGrid").jqGrid('setCell',row[6],'hdnMctcempid',CA);
		jQuery("#TeamGrid").jqGrid('setCell',row[7],'hdnMctcempid',DMTLeader);
		jQuery("#TeamGrid").jqGrid('setCell',row[8],'hdnMctcempid',PH);
		*/
			jQuery('#TeamGrid').setSelection(row[i], true);
			 jQuery('input:checkbox[id=jqgh_TeamGrid_'+row[i]+']').attr('checked',false);
			//    jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','1');
			   // jQuery('input:checkbox[name=chkMctcHazop]').attr('disabled',true);
			  /*  jQuery("#chkMctcHazop_"+jqGridId+"_"+row[1]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[2]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[3]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[4]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[5]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[6]).attr('disabled','disable');
			  // jQuery("#chkMctcHazop_"+jqGridId+"_"+row[8]).attr('disabled','disable');--commented by sriram
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[0]).attr('disabled','disable');
			    jQuery("#chkMctcfinal_"+jqGridId+"_"+row[1]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[2]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[3]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[4]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[5]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[6]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[0]).attr('disabled','disable');	
				//   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[8]).attr('disabled','disable');--commented by sriram
				//   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[9]).attr('disabled','disable');--commented by sriram
				 
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[0]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[1]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[2]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[3]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[4]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[5]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[6]).attr('checked',true);
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[7]).attr('checked',true);
		 		   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[7]).attr('checked',true); */
				   /* jQuery("#chkMctcHazop_"+jqGridId+"_"+row[9]).attr('checked',true);--commented by sriram
				  jQuery("#chkMctcInitial_"+jqGridId+"_"+row[7]).attr('disabled','disable');--commented by sriram
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[8]).attr('disabled','disable');--commented by sriram
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[9]).attr('disabled','disable'); */ //commented by sriram
				// Disable Hazop for rows NOT auto-checked (0=Initiator, 1=JH Leader, 6=Operations Area Incharge)
				  jQuery("#chkMctcHazop_"+jqGridId+"_"+row[0]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[1]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[3]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[4]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[8]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[9]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[10]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[11]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[12]).attr('disabled','disable');
jQuery("#chkMctcHazop_"+jqGridId+"_"+row[13]).attr('disabled','disable');

jQuery("#chkMctcfinal_"+jqGridId+"_"+row[0]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[1]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[3]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[4]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[8]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[9]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[10]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[11]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[12]).attr('disabled','disable');
jQuery("#chkMctcfinal_"+jqGridId+"_"+row[13]).attr('disabled','disable');

// Re-enable for auto-checked: DMT Leader(2) and Safety Manager(7)
//jQuery("#chkMctcHazop_"+jqGridId+"_"+row[2]).removeAttr('disabled');
//jQuery("#chkMctcHazop_"+jqGridId+"_"+row[7]).removeAttr('disabled');
//jQuery("#chkMctcfinal_"+jqGridId+"_"+row[2]).removeAttr('disabled');
//jQuery("#chkMctcfinal_"+jqGridId+"_"+row[7]).removeAttr('disabled');

				   
			   //  disableUIButton("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
			  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
			//   jQuery('input:checkbox[id=jqg_TeamGrid_'+row[i]+']').attr('disabled',false);
		   
	}

	 }	 
	//ADDED NEW 04 JUNE 2026 -- VIGNESH //
     clearCapexRestrictedRowsIfNo();
     loadInitialApprovedPrimaryLocks();
     
     loadMocTeamWorkflowRoleLock();
  // Swetha 20-Aug-2026
     // Fix TeamGrid edit checkbox first-click issue.
     // Must be called after TeamGrid rows are loaded/rendered.
     setTimeout(function () {
         bindTeamGridEditCheckboxEvents();
         bindTeamGridDropdownFirstClickFix();
     }, 200);
     

//end of function TeamloadComFunction
}
 function syncCapexToAllTabs(capexVal) {
	    capexVal = jQuery.trim(capexVal || '');

	    // Store actual value for save
	    jQuery("#chckMocRfcmCapex").val(capexVal);

	    // Workflow tab
	    jQuery("#capexYes").prop("checked", capexVal === "Y");
	    jQuery("#capexNo").prop("checked", capexVal === "N");

	    // Initial Approval tab
	    jQuery("#initCapexYes").prop("checked", capexVal === "Y");
	    jQuery("#initCapexNo").prop("checked", capexVal === "N");

	    // Later add these after renaming other tab IDs
	     jQuery("#whCapexYes").prop("checked", capexVal === "Y");
	     jQuery("#whCapexNo").prop("checked", capexVal === "N");

	    jQuery("#finalCapexYes").prop("checked", capexVal === "Y");
	     jQuery("#finalCapexNo").prop("checked", capexVal === "N");
	
 }
	 // -----vignesh 13 may 2026 for secondary apporval fetch 
/*
	 function setSecondaryApprovalValue(rowId, defaultEmpId) {

    var savedEmpId = jQuery.trim(
        jQuery("#TeamGrid").jqGrid("getCell", rowId, "hdnMctcempid2")
    );

    var finalEmpId = "";

    if (savedEmpId !== "" && savedEmpId !== "{}" && savedEmpId !== "-") {
        // Existing DB value
        finalEmpId = savedEmpId;
    } else {
        // Default value from hidden role logic
        finalEmpId = defaultEmpId;
    }

    setFieldValue("cmbMctcempid2_TeamGrid_" + rowId, finalEmpId);
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", finalEmpId);

    console.log("Secondary approval rowId=" + rowId + " value=" + finalEmpId);
} 
	 */
	 function setSecondaryApprovalValue(rowId, defaultEmpId) {

		    var savedEmpId = jQuery.trim(
		        jQuery("#TeamGrid").jqGrid("getCell", rowId, "hdnMctcempid2")
		    );

		    if (savedEmpId !== "" && savedEmpId !== "{}" && savedEmpId !== "-") {

		        // Existing DB value only
		        setFieldValue("cmbMctcempid2_TeamGrid_" + rowId, savedEmpId);
		        jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", savedEmpId);

		        console.log("Secondary approval loaded from DB rowId=" + rowId + " value=" + savedEmpId);

		    } else {

		        // No DB value means keep secondary approval empty
		        jQuery("#cmbMctcempid2_TeamGrid_" + rowId)
		            .combobox("setValue", "")
		            .combobox("setText", "");

		        jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbMctcempid2", "");
		        jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", "");

		        console.log("Secondary approval empty rowId=" + rowId);
		    }
		}
	 
	  // -----vignesh 13 may 2026 
function selectOnlyOne(clicked) {

	var isYes = (clicked.id === 'capexYes');

    var capexValue = '';

    if (isYes) {
        if (clicked.checked) {
            capexValue = "Y";
        } else {
            // If Yes is unchecked, automatically select No
            capexValue = "N";
        }
    } else {
        if (clicked.checked) {
            capexValue = "N";
        } else {
            // If No is unchecked, automatically select Yes
            capexValue = "Y";
        }
    }

    syncCapexToAllTabs(capexValue);

    alert("CapEx value = " + jQuery("#chckMocRfcmCapex").val());

    var row = jQuery("#TeamGrid").jqGrid('getDataIDs');
    if (!row || row.length === 0) return;

    var enable = (capexValue === 'Y');
    var jqGridId = "TeamGrid";

    jQuery.each([5, 6], function(_, idx) {
        if (!row[idx]) return;

        if (enable) {
            // ENABLE — just enable fields, keep existing values
            jQuery('input:checkbox[id=jqg_TeamGrid_' + row[idx] + ']').attr('disabled', false).prop('checked', true);
            jQuery('#TeamGrid').setSelection(row[idx], true);
            jQuery("#TeamGrid").jqGrid('setCell', row[idx], 'selctVal', '1');

            jQuery("#chkMctcInitial_" + jqGridId + "_" + row[idx]).attr('disabled', false).prop('checked', true);
            jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[idx]).attr('disabled', false).prop('checked', true);
            jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[idx]).attr('disabled', false).prop('checked', true);

          
         // ✅ Just ENABLE — do NOT clear values
            jQuery("#cmbAppTyepid_TeamGrid_"      + row[idx]).combobox('enable');
            jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[idx]).combobox('enable');
            jQuery("#cmbMctcempid2_TeamGrid_"     + row[idx]).combobox('enable');

        } else {
            // DISABLE — uncheck AND clear values
            jQuery('input:checkbox[id=jqg_TeamGrid_' + row[idx] + ']').attr('disabled', true).prop('checked', false);
            jQuery('#TeamGrid').setSelection(row[idx], false);
            jQuery("#TeamGrid").jqGrid('setCell', row[idx], 'selctVal', '0');

            jQuery("#chkMctcInitial_" + jqGridId + "_" + row[idx]).attr('disabled', true).prop('checked', false);
            jQuery("#chkMctcHazop_"   + jqGridId + "_" + row[idx]).attr('disabled', true).prop('checked', false);
            jQuery("#chkMctcfinal_"   + jqGridId + "_" + row[idx]).attr('disabled', true).prop('checked', false);

            // ✅ DISABLE AND CLEAR values
            jQuery("#cmbAppTyepid_TeamGrid_"      + row[idx]).combobox('disable').combobox('setValue', '');
            jQuery("#cmbAbnmDetectedby_TeamGrid_" + row[idx]).combobox('disable').combobox('setValue', '').combobox('setText', '');
            jQuery("#cmbMctcempid2_TeamGrid_"    + row[idx]).combobox('disable').combobox('setValue', '').combobox('setText', '');
            
          //ADDED NEW 04 JUNE 2026 -- VIGNESH //
            clearCapexRestrictedRowsIfNo();
        }
    });
}


/* function WhatifDtlgridLoadComplete(){	
    
    var mode= jQuery('#hdnmode').val();
    //////alert("View"+mode);
    var row = jQuery("#WhatifGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#WhatifGrid").jqGrid("getGridParam", "colModel");
	for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#WhatifGrid").jqGrid('getCell',row[i],"hdnWifmKeyid");	
		 var mode= jQuery('#hdnmode').val();
			////////alert("mode"+mode);
			var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
		if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
				jQuery('#WhatifGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('checked',false);
				 
				   jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('disabled',true);
			}
			else if(detailsid.trim().length>0){ 
			jQuery('#WhatifGrid').setSelection(row[i], false);
			 jQuery('input:checkbox[id=jqgh_WhatifGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#WhatifGrid").jqGrid('setCell',row[i],'selctVal','1');
		   }

	 }	 
}
 */
 
 function WhatifDtlgridLoadComplete(){	
    
    var mode= jQuery('#hdnmode').val();
    //////alert("View"+mode);
    var row = jQuery("#WhatifGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#WhatifGrid").jqGrid("getGridParam", "colModel");
	for(var i=0;i<row.length;i++)
	 {
		//WhatifGrid_hdnWifdKeyid 
		var detailsid = jQuery("#WhatifGrid").jqGrid('getCell',row[i],"hdnWifdKeyid");
		//WhatifGrid_hdnWifmKeyid
		var master = jQuery("#WhatifGrid").jqGrid('getCell',row[i],"hdnWifmKeyid");	
		
		//if(master != ""|| master != null){jQuery("#hdnWhatifKey").val(master);}
		
		 var mode= jQuery('#hdnmode').val();
			////////alert("mode"+mode);
			var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
		if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
				jQuery('#WhatifGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('checked',false);
				 
				   jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('disabled',true);
			}
			else if(detailsid.trim().length>0){ 
			//jQuery('#WhatifGrid').setSelection(row[i], false);
			jQuery("#MOCCGrid").jqGrid("resetSelection", row[i]);//ADDED CHANGE
			 //jQuery('input:checkbox[id=jqgh_WhatifGrid_'+row[i]+']').attr('checked',true);
			    //jQuery("#WhatifGrid").jqGrid('setCell',row[i],'selctVal','1');
		   }

	 }	 
	setTimeout(function () {
		bindWhatIfDropdownFirstClickFix();
		bindWhatIfGridEditCheckboxEvents();
    }, 200);
    

}
function HazopDtlgridLoadComplete(){
	
    var row = jQuery("#Hazopgrid").jqGrid('getDataIDs');
	 var cm = jQuery("#Hazopgrid").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#Hazopgrid").jqGrid('getCell',row[i],"hdnMohdMohmKeyid");	
		 var mode= jQuery('#hdnmode').val();
			////////////////////////alert("mode"+mode);
			var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
		if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
				//disableGridSort("TeamGrid");
				jQuery('#Hazopgrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_Hazopgrid_'+row[i]+']').attr('checked',false);
				   jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','0');
				   jQuery('input:checkbox[id=jqg_Hazopgrid_'+row[i]+']').attr('disabled',true);
			}
			else  if(detailsid.trim().length>0){ 
			jQuery('#Hazopgrid').setSelection(row[i], false);
			 jQuery('input:checkbox[id=jqgh_Hazopgrid_'+row[i]+']').attr('checked',true);
			    jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','1');
		   }

	 }	
			
		 setTimeout(function () {
			 bindHazopDropdownFirstClickFix();
		    }, 200);
}


 function QuestionnaireloadComFunction(){
    var mode=jQuery("#mode").val();
    var row = jQuery("#QuestionaireGrd").jqGrid('getDataIDs');
    ////////////////////////////////////////////////////alert("row"+row);
	 var cm = jQuery("#QuestionaireGrd").jqGrid("getGridParam", "colModel");
	 ////////////////////////////////////////////////////alert("cm"+cm);
	// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
	  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
	 for(var i=0;i<row.length;i++) 
	 {
		 var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
	  ////////////////////////////////////////////////////alert("Detail:::"+detailsid);
			var mode= jQuery('#hdnmode').val();
			////////////////////////alert("mode"+mode);
			
			var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
		if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
				//disableGridSort("TeamGrid");
				jQuery('#QuestionaireGrd').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqgh_QuestionaireGrd_'+row[i]+']').attr('checked',false);
				   jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','0');
				  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('disabled',true);
			}
			else if (mode=="create"||mode=="modify"){
				
				if(detailsid.trim().length>0){ 
			    jQuery('#QuestionaireGrd').setSelection(row[i], false);
			    jQuery('input:checkbox[id=jqgh_QuestionaireGrd_'+row[i]+']').attr('checked',true);
			    jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"txtRfcqQuestions");
			   jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('disabled',true);
		   }
			}
	 }	 
} 
 function MOCClosureloadComFunction(){
	  //  var mode=jQuery("#mode").val();
	    var row = jQuery("#MOCCGrid").jqGrid('getDataIDs');
	    ////////////////////////////////////////////////////alert("row"+row);
		 var cm = jQuery("#MOCCGrid").jqGrid("getGridParam", "colModel");
	
		var pssrCount=jQuery("#hdnpssrCount").val();
		   var WHCount=jQuery("#hdnWHCount").val();
	 		////////alert("WHCount"+WHCount);
	 		var initial =jQuery("#hdnInitialApproval").val();
	 		////////alert("initial"+initial);
	 		var Hazop=jQuery("#hdnHazopApproval").val();
	 		////////alert("Hazop"+Hazop);
	 		////////alert("PssrCount"+pssrCount);	
	 		  var MOCStatus=jQuery("#hdnMocStatus").val();
	 			//////////alert("MOCStatus"+MOCStatus);
	 			  var mode= jQuery('#hdnmode').val();
	 			////////alert("mode"+mode);
			////////////alert("pssrCount"+pssrCount);
		 for(var i=0;i<row.length;i++) 
		 {
			 var detailsid = jQuery("#MOCCGrid").jqGrid('getCell',row[i],"hdnRfccKeyid");	
		  ////////////////////////////////////////////////////alert("Detail:::"+detailsid);
			 if(detailsid.trim().length>0){ 
				    jQuery('#MOCCGrid').setSelection(row[i], false);
				    jQuery('input:checkbox[id=jqgh_MOCCGrid_'+row[i]+']').attr('checked',true);
				    jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','1');
				  //  jQuery("#MOCCGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);
			   }
			 else 	if(pssrCount=="view" && initial=="Pending" && Hazop=="Pending" && WHCount=="view" || mode=="view") {
		  		//////alert("if "); 
		  		jQuery('#MOCCGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
				   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
				
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);		
		  	}
			
		  	else if(pssrCount=="create" && mode=="create" && initial=="Closed" && Hazop=="Closed" && WHCount=="create"){
		  		////////alert("else if "); 
		  		//jQuery('#MOCCGrid').setSelection(row[i], false);//Commented this
		  		    jQuery("#MOCCGrid").jqGrid("resetSelection", row[i]);//ADDED CHANGE
				 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
				   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
				  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',false);
				   }
		  	
			   else{
					jQuery('#MOCCGrid').setSelection(row[i], false);
					 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
					   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
					
					   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);	
			   }

		 }	 
	}
  
 function Basisgridcompletecallback(){
	 
	    var row = jQuery("#BasisGrd").jqGrid('getDataIDs');
	    ////////////////////////////////////////////////////alert("row"+row);
		 var cm = jQuery("#BasisGrd").jqGrid("getGridParam", "colModel");
		 ////////////////////////////////////////////////////alert("cm"+cm);
		// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
		  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
		 for(var i=0;i<row.length;i++)
		 {
			 var detailsid = jQuery("#BasisGrd").jqGrid('getCell',row[i],"hdnRfcbrfcid");	
		//////////////////////////////////////////////alert("Detail:::"+detailsid);
				var mode= jQuery('#hdnmode').val();
				//////////////////////////////////////////alert("mode"+mode);
				var pageMode = jQuery.trim(jQuery("#hdnmode").val()).toLowerCase();//ADDED CHANGES
	//if(mode=="view"){
		
		if (pageMode == "view" || pageMode == "approval"){//ADDED CHANGES
					 jQuery('#BasisGrd').setSelection(row[i], false);
					 jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('checked',false);
					   jQuery("#BasisGrd").jqGrid('setCell',row[i],'selctVal','0');
					  //  jQuery("#TeamGrid") .jqGrid('getCell',row[i],"txtRfcqQuestions");
					   jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('disabled',true);
					   
				}
			  if(detailsid.trim().length>0){ 
				    jQuery('#BasisGrd').setSelection(row[i], true);
				    jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('checked',true);
				    jQuery("#BasisGrd").jqGrid('setCell',row[i],'selctVal','1'); 
				//    jQuery("#BasisGrd").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('disabled',false);
			   }

		 }	 
		 
		 // sriram
		    jQuery(document).on("change", "input[id^='jqg_BasisGrd_']", function() {
		        checkOthersSelection();
		    });
		    
 }

 //sriram 
 function checkOthersSelection() {
	    var othersChecked = false;
	    var rows = jQuery("#BasisGrd").jqGrid('getDataIDs');

	    for (var i = 0; i < rows.length; i++) {
	        if (jQuery('#jqg_BasisGrd_' + rows[i]).is(':checked')) {
	            var rowData = jQuery("#BasisGrd").jqGrid('getRowData', rows[i]);
	            for (var key in rowData) {
	                if (rowData[key] && rowData[key].toString().indexOf('Others') >= 0) {
	                    othersChecked = true;
	                    break;
	                }
	            }
	        }
	        if (othersChecked) break;
	    }

	    if (othersChecked) {
	        jQuery("#txtAdditionalNotes").removeAttr('disabled')
	                                    .css('background-color', '#fff');
	    } else {
	        jQuery("#txtAdditionalNotes").attr('disabled', 'disabled')
	                                    .val('')
	                                    .css('background-color', '');
	    }
	}

 function convertJsonArr(){
		var allrow = jQuery("#BasisGrd").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if(jQuery('#jqg_BasisGrd_' + rowno).is(':disabled')==true)
				{
				continue;
				}
	           
	            
			
			if (jQuery('#jqg_BasisGrd_' + rowno).is(':checked')) {
				
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO = jsonArrO.slice(0, -1) + '},';
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}
 
 

  function convertJsonArrQuestions(){
	var allrow = jQuery("#QuestionaireGrd").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_QuestionaireGrd_' + rowno).is(':disabled')==true)
			{
			continue;
			}
           
            
		
		if (jQuery('#jqg_QuestionaireGrd_' + rowno).is(':checked')) {
			
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + '},';
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}
  
 /*  function convertJsonArrClosure(){
		var allrow = jQuery("#MOCCGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if(jQuery('#jqg_MOCCGrid_' + rowno).is(':disabled')==true)
				{
				continue;
				}
	           
	            
			
			if (jQuery('#jqg_MOCCGrid_' + rowno).is(':checked')) {
				
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO = jsonArrO.slice(0, -1) + '},';
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	} */
  
  function convertJsonArrClosure(){
		var allrow = jQuery("#MOCCGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			var empid=getFieldValue("selRfccresponse_"+rowno);
			//////////////////////////////////////////////alert(empid);
			if (jQuery('#jqg_MOCCGrid_' + rowno).is(':checked')) {
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					/* 	jsonArrO += '"cmbMocrcmEmpid":"' + empid+'",';
						////////////////////////////////////////////////////alert("id:"+jsonArrO); */
					}
				}
				jsonArrO=jsonArrO.replace('&',',');
				
				//jsonArrO=jsonArrO.replace('undefined','Y');
				jsonArrO = jsonArrO.slice(0, -1) + '},';
				
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}


function convertJsonArrTeam(){
	var allrow = jQuery("#TeamGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		var empid=getFieldValue("selRfccresponse_"+rowno);
		//////////////////////////////////////////////alert(empid);
		if (jQuery('#jqg_TeamGrid_' + rowno).is(':checked')) {
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				/* 	jsonArrO += '"cmbMocrcmEmpid":"' + empid+'",';
					////////////////////////////////////////////////////alert("id:"+jsonArrO); */
				}
			}
			jsonArrO=jsonArrO.replace('&',',');
			
			//jsonArrO=jsonArrO.replace('undefined','Y');
			jsonArrO = jsonArrO.slice(0, -1) + '},';
			
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}


function convertJsonArrPssrCheckList(){
	var allrow = jQuery("#PSSRGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_PSSRGrid_' + rowno).is(':disabled')==true)
			{
			continue;
			}
           
            
		
		if (jQuery('#jqg_PSSRGrid_' + rowno).is(':checked')) {
			
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + '},';
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}
		
/* function InitialGrid_selectRow(id){
	

	//////////////////////////////////////////alert("Inside");
	 var detecteddateCtrl=jQuery("#InitialGrid").jqGrid('getCell', rowId,"dteAbnmDetectiondate");
	// var detecteddateCtrl="dteAbnmDetectiondate_AbnMutipleGrd_"+rowId;
	var detecteddateCtrl=jQuery("#dteMctcInaprovedDte_InitialGrid_"+id);
//////////////////////////////////////////alert(detecteddateCtrl);

	fillWithCurrentDate(detecteddateCtrl);
	
	//return "MomPillarId="+MomPillarId;
		

} */
function TeamGrid_selectRow(rowId){
/* var initiator = jQuery('#hdnResponsibility').val();

	var id=1;

	setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
	disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
	jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator); */
//jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);

}
function applyMocTeamWorkflowRoleLock() {

    if (mocTeamInitialWorkflowLocked !== "Y") {
        return;
    }

    var rows = jQuery("#TeamGrid").jqGrid("getDataIDs");

    if (!rows || rows.length === 0) {
        return;
    }

    for (var i = 0; i < rows.length; i++) {

        var rowId = rows[i];

        var roleId = jQuery.trim(
            jQuery("#TeamGrid").jqGrid("getCell", rowId, "hdnMctcroleid") || ""
        );

        var roleName = jQuery.trim(
            jQuery("#TeamGrid").jqGrid("getCell", rowId, "txtRoleName") || ""
        );

        // Existing role in DB is allowed to remain.
        if (isValidTeamValue(roleId) && mocTeamExistingRoleMap[roleId] === "Y") {
            continue;
        }

        // New role should not be selectable after initial approval completed.
        disableNewWorkflowRoleAfterInitialApproval(rowId, roleName);
    }
}
function disableNewWorkflowRoleAfterInitialApproval(rowId, roleName) {

    if (!rowId) {
        return;
    }

    var jqGridId = "TeamGrid";

    // Uncheck row
    jQuery("#TeamGrid").setSelection(rowId, false);

    jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]")
        .prop("checked", false)
        .attr("disabled", true);

    jQuery("#TeamGrid").jqGrid("setCell", rowId, "selctVal", "0");

    // Force approval flags as N
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcInitial", "N");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcHazop", "N");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "chkMctcfinal", "N");

    jQuery("#chkMctcInitial_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    jQuery("#chkMctcHazop_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    jQuery("#chkMctcfinal_" + jqGridId + "_" + rowId)
        .prop("checked", false)
        .attr("disabled", true);

    // Clear primary approval
    try {
        jQuery("#cmbAbnmDetectedby_TeamGrid_" + rowId)
            .combobox("clear")
            .combobox("setValue", "")
            .combobox("setText", "")
            .combobox("disable");
    } catch (e) {}

    // Clear secondary approval
    try {
        jQuery("#cmbMctcempid2_TeamGrid_" + rowId)
            .combobox("clear")
            .combobox("setValue", "")
            .combobox("setText", "")
            .combobox("disable");
    } catch (e) {}

    // Clear jqGrid hidden values also
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbAbnmDetectedby", "");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid", "");

    jQuery("#TeamGrid").jqGrid("setCell", rowId, "cmbMctcempid2", "");
    jQuery("#TeamGrid").jqGrid("setCell", rowId, "hdnMctcempid2", "");

    // Alert when user tries to click disabled row checkbox area
    jQuery("input:checkbox[id=jqg_TeamGrid_" + rowId + "]")
        .closest("td")
        .off("mousedown.workflowRoleLock")
        .on("mousedown.workflowRoleLock", function (e) {

            e.preventDefault();
            e.stopPropagation();

            popupCommonErrorMsg(
                "Initial approval is already completed. New workflow role [" +
                roleName +
                "] cannot be added."
            );

            return false;
        });

    console.log("New workflow role disabled after initial approval. rowId=" + rowId + ", role=" + roleName);
}
function loadMocTeamWorkflowRoleLock() {

    var MocKeyid = jQuery.trim(jQuery("#txtRfcmKeyid").val() || "");

    if (!isValidTeamValue(MocKeyid)) {
        return;
    }

    processAjaxCalls(
        "getMocTeamWorkflowRoleLock.mocn",
        "MocKeyid=" + encodeURIComponent(MocKeyid),
        "loadMocTeamWorkflowRoleLock_OnSuccess",
        "loadMocTeamWorkflowRoleLock_OnError"
    );
}

function loadMocTeamWorkflowRoleLock_OnSuccess(result) {

    mocTeamInitialWorkflowLocked = "N";
    mocTeamExistingRoleMap = {};

    if (result == null || result.success === false || result.success === "false") {
        return;
    }

    mocTeamInitialWorkflowLocked = jQuery.trim(result.isLocked || "N");

    var roles = result.roles;

    if (typeof roles === "string") {
        try {
            roles = JSON.parse(roles);
        } catch (e) {
            roles = [];
        }
    }

    if (roles == null) {
        roles = [];
    }

    for (var i = 0; i < roles.length; i++) {
        var roleId = jQuery.trim(roles[i].roleId || "");

        if (isValidTeamValue(roleId)) {
            mocTeamExistingRoleMap[roleId] = "Y";
        }
    }

    applyMocTeamWorkflowRoleLock();
}

function loadMocTeamWorkflowRoleLock_OnError(result) {
    console.log("Unable to load MOC Team workflow role lock.");
}

function InitialGrid_selectRow(rowId){
    var kaizenDate=jQuery("#InitialGrid").jqGrid('getCell', rowId,"dteMctcInaprovedDte"); 	
    var dateCtrl="dteMctcInaprovedDte_InitialGrid_"+rowId;	
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateInitial(dateCtrl,rowId);	
				} 
			}); 
			
}

function FinalApprovalGrid_selectRow(rowId){
	
	 //readOnlyFields(KzbndateCtrl);
	  
    var kaizenDate=jQuery("#FinalApprovalGrid").jqGrid('getCell', rowId,"dteMctcFaaprovedDte"); 	
  
    var dateCtrl="dteMctcFaaprovedDte_FinalApprovalGrid_"+rowId;	
   
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);
   
jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateFinal(dateCtrl,rowId);	
				} 
			}); 
			
}

function HazopApprovalGrid_selectRow(rowId){
    var kaizenDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte"); 	
    var dateCtrl="dteMctcHzaprovedDte_HazopApprovalGrid_"+rowId;	
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateHazop(dateCtrl,rowId);	
				} 
			}); 
			
}
function isValidDateInitial(dateCtrl,rowId){
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");

		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		
	}
}
function isValidDateHazop(dateCtrl,rowId){
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	
		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		
	}
}
function isValidDateFinal(dateCtrl,rowId){
	////////////////////////////////////////alert("Inside")
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#FinalApprovalGrid").jqGrid('getCell', rowId,"dteMctcFaaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	////////////////////////////////////////alert("Inside1")

		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	
}

function InitialGridbtnInitialSave_onClick(result){	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//////////////////////////////////////////////alert("button"+btnid)

	var keyid = jQuery("#InitialGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);

	var MocKeyId = jQuery("#InitialGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcInaprovedStatus_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcInaprovedDte_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcInaprovedRem_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
    var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	saveForm("frmMocProject","InitialApproval_save.mocn?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&Suggestion="+Suggestion+"&SuggestionNo="+SuggestionNo);	
	}
	
function HazopApprovalGridbtnHazopSave_onClick(result){	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//alert("button"+btnid);
   // alert("button"+btnId);
	var keyid = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);
	var MocKeyId = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcHzaprovedStatus_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcHzaprovedDte_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcHzaprovedRem_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
	var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	var PMMech=getFieldValue("selMctcHzAddPMMech_HazopApprovalGrid_"+rowid);
	var PMElect=getFieldValue("selMctcHzAddPMElect_HazopApprovalGrid_"+rowid);
    var PMInst=getFieldValue("selMctcHzAddPMInst_HazopApprovalGrid_"+rowid);
	saveForm("frmMocProject","HazopApproval_save.mocn?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&SuggestionNo="+SuggestionNo+"&Suggestion="+Suggestion+"&PMMech="+PMMech+"&PMElect="+PMElect+"&PMInst="+PMInst);	
	   	
	}

function FinalApprovalGridbtnFinalSave_onClick(result){
	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//////////////////////////////////////////////alert("button"+btnid)
	var keyid = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);
	var MocKeyId = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcFaaprovedStatus_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcFaaprovedDte_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcFaaprovedRem_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
    var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	saveForm("frmMocProject","FinalApproval_save.mocn?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&SuggestionNo="+SuggestionNo+"&Suggestion="+Suggestion);		
	
	}



	
jQuery("#btnDeletehatif").click(function()
	   	{ 
		   removeWhatifRecord();
		});
		
function removeWhatifRecord(keyid) {
	var Whatifrow = jQuery("#WhatifGrid").jqGrid('getDataIDs');//	row get data
	for (i = 0; i < Whatifrow.length; i++) {
    var rowid = Whatifrow[i];
    if (jQuery('#jqg_WhatifGrid_'+Whatifrow[i]).is(':checked') == true) {//////////////////////alert(1);
	    
		keyid = jQuery("#WhatifGrid").jqGrid('getCell', rowid, "hdnWifdKeyid");

         if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
			    var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("Whatif_remove.mocn", "keyid="+ keyid, 'removeWhatif_successCallBack','remove_errorCallBack');
				} else{
					return false;
				} 
           	}else {
                var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#WhatifGrid").trigger("reloadGrid");
				else
					return false;
			}
         }
       }	}
       
function removeWhatif_successCallBack(result) {
	////////////////////alert(result.successData);
	jQuery("#WhatifGrid").trigger("reloadGrid");
}


//vignesh 07sep2026

jQuery("#btnDelete").click(function()
		{
		    removePssrReccommendationRecord();
		});
	
function removePssrReccommendationRecord()
{
    var rows = jQuery("#PSSRReccomendGrid").jqGrid('getDataIDs');

    for (var i = 0; i < rows.length; i++)
    {
        var rowId = rows[i];

        if (jQuery('#jqg_PSSRReccomendGrid_' + rowId).is(':checked'))
        {
            var keyid = jQuery.trim(
                jQuery("#PSSRReccomendGrid")
                    .jqGrid('getCell', rowId, "hdnPsrrKeyid") || ""
            );

            /*
             * Existing DB record
             */
            if (keyid.length > 0)
            {
                var r = confirm("Do You Want To Delete?");

                if (r == true)
                {
                    processAjaxCalls(
                        "PSSRReccommendation_remove.mocn",
                        "keyid=" + keyid,
                        "removePssrReccommendation_successCallBack",
                        ""
                    );
                }
                else
                {
                    return false;
                }
            }

            /*
             * Newly added row, not saved yet.
             */
            else
            {
                var r = confirm("Do You Want To Remove Row?");

                if (r == true)
                {
                    jQuery("#PSSRReccomendGrid")
                        .jqGrid('delRowData', rowId);
                }
                else
                {
                    return false;
                }
            }
        }
    }
}
function removePssrReccommendation_successCallBack(result)
{
    var MocKeyid = jQuery("#hdnMocKeyId").val();

    processGridnew(
        "PSSRRecommendations_input.mocn",
        "q=2&MocKeyid=" + MocKeyid,
        "PSSRReccomendGrid",
        "pager",
        "RFC QUESTIONNAIRE",
        "docDoubleClick",
        "",
        "ReccommendloadComFunction"
    );
}

// vignesh 07sep2026

jQuery("#btnDeleteHazop").click(function()
	   	{ 
		   removeHazopRecord();
		});
		
function removeHazopRecord(keyid) {
	var Whatifrow = jQuery("#Hazopgrid").jqGrid('getDataIDs');//	row get data
	for (i = 0; i < Whatifrow.length; i++) {
    var rowid = Whatifrow[i];
    if (jQuery('#jqg_Hazopgrid_'+Whatifrow[i]).is(':checked') == true) {//////////////////////alert(1);
	    
		keyid = jQuery("#Hazopgrid").jqGrid('getCell', rowid, "hdnMohdKeyid");

         if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
			    var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("Hazop_remove.mocn", "keyid="+ keyid, 'removeHazop_successCallBack','remove_errorCallBack');
				} else{
					return false;
				} 
           	}else {
                var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#Hazopgrid").trigger("reloadGrid");
				else
					return false;
			}
         }
       }	}
       
function removeHazop_successCallBack(result) {
	////////////////////alert(result.successData);
	jQuery("#Hazopgrid").trigger("reloadGrid");
}
	
	// vIGNESH 12 MAY 2026 
	function allowOnlyNumbers(evt) {
    var ch = String.fromCharCode(evt.which || evt.keyCode);
    return /^[0-9]$/.test(ch);
}
	// vIGNESH 12 MAY 2026 
jQuery("#btnopenpfd").click(function(){
	
	//window.open("Usermanuals/App3.pdf");
    window.open().document.write('<embed src="Usermanuals/MOC Procedure.pdf" width="1300" height="700" alt="pdf" pluginspage="http://www.adobe.com/products/acrobat/readstep2.html"');
});



</script>
<form id="frmMocProject">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmMocProjectFuntKeyIds">
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbrfcmdmtid" value="${requestScope.mocRfcmst.rfcmdmtid}"></input>
					<input type="hidden" id="cell" name="cmbRfcmjhid" value="${requestScope.mocRfcmst.rfcmjhid}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="cmbRfcmflid" value="${requestScope.Kaizenbankmst.kzbnFlid}"></input>	
				</div>
			<!-- 	<Vignesh Adding for functional Location > -->
				
			 	<div id="NewMocfunLocation" style="width:123%;margin-top:-5px;margin-left:-35px">
	
<!-- 	<Vignesh Adding for functional Location > -->
			</td>
			 <td>
				  <div style="margin-left:30px; margin-top:-0px;"><label><b>Suggestion No</b></label></div>
				  <div style="margin-left:30px; margin-top:0%;">
				<%--   <input id="txtRfcmsuggestionid" name="txtRfcmsuggestionid" type="text" class="easyui-text" disabled="disabled" style="width:120px; height:25px;text-align:center; font-weight:bold" value="${requestScope.mocRfcmst.rfcmsuggestionid}"/> 
				 --%> 
				 <input type="text" class="easyui-text"  id="txtRfcmsuggestionid" name="txtRfcmsuggestionid" disabled="disabled" maxlength="10"  style=" width : 120px;height:25px; text-align:left;font-weight:bold" value="${requestScope.Kaizenbankmst.kzbnKeyid}"/>
					  
				  </div>
				  </td>
				  <td>
				  <div style="padding-left:5px;" ><label class="mandatory-lbl">Date</label></div>
				   <div style="width:350px;position:relative;padding-left:0px"> 
						      <input type="text" class="easyui-datebox"  id="dteRfcmdate" name="dteRfcmdate" maxlength="10"   style=" width : 100px;/* height:60px; */ text-align:left;" value="${requestScope.mocDate}"/>
						     	  </div>
				  </td>
				  
				  <td>
				   <div style="width:350px;position:relative;margin-left:-240px;margin-top:8px;"> 
				   <input type="button" class="easyui-button" value ="MOC Procedure" id="btnopenpfd" style="height:23px;"/>	
				  
				  	</div>
				  </td>
				  
			</tr>
			</table>
		<table>
		
	<tr>
	<td>
			
   <div  style="margin-left:-40px;margin-top:-0px;">
	<label class="mandatory-lbl" >Suggestion</label>		
    </div>		
	<div style="margin-left:-40px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtKzbnKaizen" name="txtKzbnKaizen" disabled="disabled" class="limit-length" style="width:683px;text-transform: uppercase; height :40px;font-weight:bold;" 
	 value="">${requestScope.Kaizenbankmst.kzbnKaizen}</textarea>	
	</div>
	</td>
	<!-- sriram -->
	<td>
        <div style="margin-left:10px;margin-top:0px;">
            <label style="font-weight:bold">MOC Item</label>
            <input type="text" class="easyui-combobox"  
                   id="cmbMocItem" name="cmbMocItem" 
                   disabled="disabled" maxlength="10"  
                   style="width:120px;/*height:25px;*/text-align:left;font-weight:bold "
                   value="${requestScope.Kaizenbankmst.kzbnMocitem}"/>
        </div>
    </td>
	<td>
	 <div style=" margin-left :30px;margin-top:0px;"><label>MOC No </label>
					   	<input type="text" class="easyui-text"  id="txtRfcmKeyid" name="txtRfcmKeyid" disabled="disabled" maxlength="10"  style=" width : 120px;height:25px; text-align:left;font-weight:bold" value="${requestScope.mocRfcmst.rfcmKeyid}"/>
					     
					   </div>
	</td>
	<td>
	  <div style="margin-left :0px;margin-top:-0px;"><label>Emergency No</label>
					   	<input type="text" class="easyui-text"  id="txtSusmDocno" name="txtSusmDocno" disabled="disabled"  maxlength="10"  style=" width : 100px;height:25px; text-align:left;" value="${requestScope.mocRfcmst.rfcmemergencyno}"/>
					     
					   </div> 
 <td>
				   <div style="width:350px;position:relative;margin-left:15px;margin-top:2px;"> 
				   <input type="button" class="easyui-button" value ="Excel View" id="btnoExcelView" style="height:25px;width:100px;"/>	
				  
				  	</div>
				  </td>
	</td>
	
 <!-- <td>
				   <div style="width:350px;position:relative;margin-left:15px;margin-top:2px;"> 
				   <input type="button" class="easyui-button" value ="Excel View" id="btnoExcelView" style="height:25px;width:100px;"/>	
				  
				  	</div>
				  </td> -->
    </tr>	 
	</table>
	</div>	
	
<div id="tabMocProject" class="easyui-tabs" style="height:500px;width:1500px;margin-top:5px;margin-left:5px; float:left;">
<div title="MOC Workflow">
<table>
 
  </table>
     <!-- sriram CapEx Checkbox -->
       <div style="margin-bottom:10px; margin-top:15px; margin-left:17px;">
    <label class="mandatory-lbl" style="margin-right:15px;"><b>CapEx:</b></label>

    <label>
        <input type="checkbox" id="capexYes" onclick="selectOnlyOne(this)"> Yes
    </label>

    <label style="margin-left:15px;">
        <input type="checkbox" id="capexNo" onclick="selectOnlyOne(this)"> No
    </label>
     <!-- This value will be submitted to backend -->
   
</div>
  
 
 
   <div style="margin-left:12px; margin-left:65px\9;margin-top:20px;">
	<table id="TeamGrid">
	<tr><td></td></tr>
	</table>
     <div id="Teampagerid"></div>
   </div>
   
  
</div>


<div title="RequestforChange">
 <table style="margin-left:8px;margin-left:2px\9;"> 
 <tr>
			<td valign="top" style=" width : 796px;">
				<div class="easyui-paddingbfpx">
						  			 
						  <span style="padding-left:0px;"><label class="mandatory-lbl">Name of Initiator</label></span>
						 <span style="padding-left:160px;"><label class="mandatory-lbl">Title</label></span>	
						 
						  <!-- <span style="padding-left:170px;"><label class="mandatory-lbl">JH Area</label></span>	
						  <span style="padding-left:210px;" ><label class="mandatory-lbl">Date</label></span>	 --> 			      
					</div>
					<div class="easyui-paddingbfpx" >	
						
					     
					      
					       <span style="position:relative;padding-left: 0px">
						       <input type="text" class="easyui-combobox"  id="cmbRfcmempid" name="cmbRfcmempid" maxlength="10"   style=" width : 240px;height:25px; text-align:left;" value="${requestScope.Kaizenbankmst.kzbnResponsibility}"/>
						       </span>
						     
					       </div>
					      
					       </td>
					   
		</tr>
					       
					       <tr>
					       <td>
					       	<div class="easyui-paddingbfpx">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Department</label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbrfcmdmtid" name="cmbrfcmdmtid"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmdmtid}" />
				
						    </span>
					       </div>
					       </td>
					         <td>
					       	<div class="easyui-paddingbfpx"style="margin-left:-530px;">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Type of Change</label></span>	</div>
					       
					    <%--    <div class="easyui-paddingbfpx"style="margin-left:-530px;" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmtype" name="cmbRfcmtype"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmtype}" />
				
						    </span>
					       </div> --%>
					       	<div class="easyui-paddingbfpx" style="margin-left:-530px;"> 
							<select id="cboRfcmtype" name="cboRfcmtype" style="width:240px;"  >
									<option value="FACILITY/PROCESS"> FACILITY/PROCESS</option>
									<option value="ORGANIZATIONAL"> ORGANIZATIONAL</option>
									<option value="PROCEDURAL/SYSTEM"> PROCEDURAL/SYSTEM</option>
									
							</select>
				        </div>
					       </td>
					       
					       </tr>
					       
					       <tr>
					       <td>
					       	<div class="easyui-paddingbfpx">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">JH Area</label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmjhid" name="cmbRfcmjhid"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmjhid}" />
				
						    </span>
					       </div>
					       </td>
					         <td>
					       	<div class="easyui-paddingbfpx"style="margin-left:-530px;">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Nature of Change</label></span>	</div>
					       
					   <%--     <div class="easyui-paddingbfpx"style="margin-left:-530px;" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmnature" name="cmbRfcmnature"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmnature}" />
				
						    </span>
					       </div> --%>
					       	<div class="easyui-paddingbfpx" style="margin-left:-530px;"> 
							<select id="cboRfcmnature" name="cboRfcmnature" style="width:100px;"  >
									<option value="Temporary"> Temporary</option>
									<option value="Permanent">Permanent</option>
									<option value="Emergency">Emergency</option>
									
							</select>
				        </div>
					       </td>
					       
					     
					       <td>
						    <div class="easyui-paddingbfpx" style="margin-left:-400px;">
						        <span style="padding-right:0px;">
						            <label class="mandatory-lbl">Number of Days</label>
						        </span>
						    </div>
						
						    <div class="easyui-paddingbfpx" style="margin-left:-400px;">
<%-- 						        <input type="text" class="easyui-text" id="txtNumberOfDays" name="txtNumberOfDays" style="width:100px;" maxlength="3" onkeypress="return allowOnlyNumbers(event);" value="${requestScope.mocRfcmst.rfcmNoofdays}"/> --%>
<input type="text" class="easyui-text" id="txtNumberOfDays" name="txtNumberOfDays" style="width:100px;" disabled="disabled" />
						    </div>
						</td>
					       </tr>
					       
					       
					       <tr>
					       		<td>
		 <div class="easyui-paddingbfpx" style="margin-top:-5px;"><label class="mandatory-lbl">MOC Detail</label></div>
					  <div>	
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="200"  style="width: 510px; height : 40px;text-transform: uppercase;" id="txtRfcmdetail" name="txtRfcmdetail" >${requestScope.mocRfcmst.rfcmdetail}</textarea>
					</div>
		</td>
					       </tr><tr>
					       	<td>
		 <div class="easyui-paddingbfpx"style="margin-left:-0px;"><label class="mandatory-lbl">Proposed Change Description & Justification</label>
					  <div>	
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="2000"  style="width: 510px; height :50px;text-transform: uppercase;" id="txtRfcmdescription" name="txtRfcmdescription" 	>${requestScope.mocRfcmst.rfcmdescription}</textarea>
					</div>
					</div>
		</td><td>
		
 		  <td style="padding-right:0px;">
			<div style="position: relative;margin-left:-250px; margin-top:-5px;">
			<span id="MocfileMgr" style="position: absolute;padding-right:5px;margin-top:0px;">
		</span></div>  
	    </td>
	    
	     
<!-- sriram -->
<td>
    <div class="easyui-paddingbfpx" style="margin-left:-110px; margin-top:2px;">
        <label><b>Others</b></label>

        <div style="margin-left:0px;">
            <textarea id="txtAdditionalNotes"
                      name="txtAdditionalNotes"
                      style="width:290px; height:35px;" disabled>
            </textarea>
        </div>
    </div>
</td>
	    
	    
					       </tr>
 	</table>
 	  <div style="position:relative;margin-left:278px;margin-top:-268px;">
						     <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="200"  style="width: 850px; height : 30px;text-transform: uppercase;" id="txtRfcmtitle" name="txtRfcmtitle" >${requestScope.mocRfcmst.rfcmtitle}</textarea>
					 </div>
					 
	<div style="margin-left:530px;margin-top:25px;">
	<table id="BasisGrd" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="BasisGrdpager"></div>
	 </div> 				 
					 

</div>

<div title="Questionnaire">
  

<div style="margin-left:14px; margin-top:10px;margin-left:65px\9;">
 <table> <tr> 	             
  <td style="margin-left:35px;padding-top:-40px;">
				 <div style="margin-left:35px; margin-top:11px;">			  
				 <input type="checkbox"  id="chkSelectAllQuestions" name="chkSelectAllQuestions"  value=""/>
				 <label id=""><b>Select All</b></label>	
				 </div>
				</td>
			</tr> </table>
<div class="QuestionaireGrddiv">
				<table id="QuestionaireGrd" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
     
   </div> 
   <div title="InitialApproval">
     <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
     <!-- sriram CapEx Checkbox -->
      
       <div style="margin-bottom:10px;">
    <label class="mandatory-lbl" style="margin-right:15px;"><b>CapEx:</b></label>

    <label>
        <input type="checkbox" id="initCapexYes" disabled> Yes
    </label>

    <label style="margin-left:15px;">
        <input type="checkbox" id="initCapexNo"  disabled> No
    </label>
    <!-- This value will be submitted to backend -->
   
    
</div>
    
<div class="InitialGrid">
				<table id="InitialGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
   </div>
  <div title="WhatIf">
   <table>
   <tr>
  <%--  <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtWifmFacility" name="txtWifmFacility" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.whatifMst.wifmFacility}</textarea>	
	</div>
   </td> --%>
     <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtWifmTeam" name="txtWifmTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.whatifMst.wifmTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:-250px;">
   <div><label class="mandatory-lbl">Date</label></div>
								<div >
									<span style="padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 120px;" id="dteWifmDate" name="dteWifmDate" 
								 													value="${requestScope.whatifMst.wifmDate}"/>
							      	</span>
								</div>
								</div>
   </td>
   <td style="padding-right:0px;">
    <div style="position: relative; margin-left:-120px; margin-top:-8px;">
        <span id="WhatifFilemgr" style="position: absolute; padding-right:5px; margin-top:0px;">
        </span>
    </div>
</td>

   </tr>
   <%--  <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtWifmTeam" name="txtWifmTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.whatifMst.wifmTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:850px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddwhatif" style="height:23px;"/>	
							</div></td>
	 <td>
   <div style="margin-left:-40px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Delete" id="btnDeletehatif" style="height:23px;"/>	
	</div></td>						
							
   </tr> --%>
   
   
  <!--  <tr>
   <td style="text-align:right; padding-right:5px; padding-top:5px">
     <td colspan="3" style="text-align:right; padding-right:5px; padding-top:5px;">
      <input type="button" class="easyui-button" value="Add Row" id="btnAddwhatif" style="height:23px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Delete" id="btnDeletehatif" style="height:23px;"/>
   </td>
</tr> -->

<!-- <tr>
   <td colspan="2" style="text-align:right; padding-left:100px; padding-top:5px;">
      <input type="button" class="easyui-button" value="Add Row" id="btnAddwhatif" style="height:23px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Delete" id="btnDeletehatif" style="height:23px;"/>
   </td>
   <td>
				   <div style="width:350px;position:relative;margin-left:15px;margin-top:2px;"> 
				   <input type="button" class="easyui-button" value ="Download Excel Format" id="wtifbtnodwnExcel" style="height:25px;width:150px;"/>	
				  
				  	</div>
				  </td>
   <td style="padding-top:5px; padding-left:15px;">
      <div id="fileUpload" style="display:inline-block; vertical-align:middle;"></div>
      <input type="button" id="wtbtnUploadExcel" class="easyui-button" name="btnUploadExcel"
             value="Upload Excel" style="height:23px; width:120px;"/>
   </td>
</tr> -->
<tr>
   <td colspan="4" style="text-align:left; padding-left:515px; padding-top:5px;">
      <input type="button" class="easyui-button" value="Add Row" id="btnAddwhatif" style="height:23px; margin-left:150px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Delete" id="btnDeletehatif" style="height:23px; margin-left:2px;"/>
      &nbsp;

      <input type="button" class="easyui-button" value="Download Excel Format" id="wtifbtnodwnExcel" style="height:23px; width:150px; margin-left:2px;"/>
      &nbsp;
      <div id="fileUpload" style="display:inline-block; vertical-align:middle; margin-left:2px;"></div>
      <input type="button" id="wtbtnUploadExcel" class="easyui-button" name="btnUploadExcel"
             value="Upload Excel" style="height:23px; width:120px; margin-left:2px;"/>
   </td>
</tr>

  </table> 
  <table><tr>
   <td colspan="3">
					<div>
						<table id="WhatifGrid" ></table> 
						<div id="Whatifpager"></div>
					</div>
				</td></tr>
   </table>
   </div>
   <div title="Hazop">
   <table >
   <tr>
  <%--  <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomFacility" name="txtHzomFacility" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;"  value="">${requestScope.hazopMst.hzomFacility}</textarea>	
	</div>
   </td> --%>
    <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomTeam" name="txtHzomTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:25px;">
   <div><label class="mandatory-lbl">Date</label></div>
								<div>
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 120px;" id="dteHzomDate" name="dteHzomDate" 
								 													value="${requestScope.hazopMst.hzomDate}"/>
							      	</span>
								</div></div>
   </td>
   <td>
    <div style=" margin-left :-160px;margin-top:0px;">
    <div>
    <label>P&ID No </label></div>
    <div>
					   	<input type="text" class="easyui-text"  id="txtHzomPidno" name="txtHzomPidno" maxlength="10"  style=" width : 160px;height:22px; text-align:left;" value="${requestScope.hazopMst.hzomPidno}"/>
					    </div> 
					   </div>
   </td>
   
  <!--  <td style="padding-right:0px;">
    <div style="position: relative; margin-left:-120px; margin-top:-8px;">
        <span id="HazopFilemgr" style="position: absolute; padding-right:5px; margin-top:0px;">
        </span>
    </div>
</td> -->
   </tr>
   <%--  <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomTeam" name="txtHzomTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:30px;;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddHazop" style="height:23px;"/>	
	</div></td>
	
	<td>
   <div style="margin-left:-125px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Delete" id="btnDeleteHazop" style="height:23px;"/>	
	</div></td>
	
   </tr> --%>
   <tr>
      <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Node</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomNode" name="txtHzomNode" class="limit-length" style="width:550px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomNode}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:-200px;">
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Design Intent</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomDesignintent" name="txtHzomDesignintent" class="limit-length" style="width:515px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomDesignintent}</textarea>	
	</div></div>
   </td>
   </tr>
  <%--   <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomTeam" name="txtHzomTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomTeam}</textarea>	
	</div>
   </td>
   abcdef
   <td>
   <div style="margin-left:30px;;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddHazop" style="height:23px;"/>	
	</div></td>
	
	<td>
   <div style="margin-left:-125px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Delete" id="btnDeleteHazop" style="height:23px;"/>	
	</div></td>
	
   </tr> --%>
   
  <!--  <tr>
   <td colspan="3" style="text-align:right; padding-right:5px; padding-top:5px;">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddHazop" style="height:23px;"/>	
	&nbsp;
	<input type="button" class="easyui-button" value ="Delete" id="btnDeleteHazop" style="height:23px;"/>	
	</td>
   </tr> -->
   
   
  <!--  <tr>
   <td colspan="2" style="text-align:right; padding-right:0px; padding-top:5px;">
      <input type="button" class="easyui-button" value="Add Row" id="btnAddHazop" style="height:23px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Delete" id="btnDeleteHazop" style="height:23px;"/>
   </td>
   <td style="padding-top:5px; padding-left:15px;">
      <div id="hazfileUpload" style="display:inline-block; vertical-align:middle;"></div>
      <input type="button" id="hazbtnUploadExcel" class="easyui-button" name="btnUploadExcel"
             value="Upload Excel" style="height:23px; width:120px;"/>
   </td>
</tr> -->

<tr>
   <td colspan="3" style="text-align:left; padding-left:580px; padding-top:5px;">
      <input type="button" class="easyui-button" value="Add Row" id="btnAddHazop" style="height:23px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Delete" id="btnDeleteHazop" style="height:23px; margin-left:2px;"/>
      &nbsp;
      <input type="button" class="easyui-button" value="Download Excel Format" id="hazbtnodwnExcel" style="height:23px; width:150px; margin-left:2px;"/>
      &nbsp;
<!--       <div id="hazfileUpload" style="display:inline-block; vertical-align:middle; margin-left:2px;"></div> -->
     
     
       <div id="fileUpload" style="display:inline-block; vertical-align:middle; margin-left:2px;"></div>       
      <input type="button" id="hzbtnUploadExcel" class="easyui-button" name="btnUploadExcel"
             value="Upload Excel" style="height:23px; width:120px; margin-left:2px;"/>
             
     &nbsp;
      <span id="HazopFilemgr" style="display:inline-block; vertical-align:middle; margin-left:2px;"></span>
</td>
</tr>
   
  </table> 
  <table><tr>
   <td>
					<div>
						<table id="Hazopgrid" ></table> 
						<div id="Hazoppager"></div>
					</div>
				</td></tr>
   </table> 

   </div>
   
   <!--  <div title="W/H Reccommend">
    <p style="color:red;margin-left:300px;margin-top:10px;"><b>Save can be done in Grid Itself.Top save can be avoided</b></p>
 <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="HazopReccommendGrid">
				<table id="HazopReccommendGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
 </div> -->
 
 <div title="W/H Approval">

 <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
  <div style="margin-bottom:10px;">
    <label class="mandatory-lbl" style="margin-right:15px;"><b>CapEx:</b></label>

    <label>
        <input type="checkbox" id="whCapexYes"  disabled> Yes
    </label>

    <label style="margin-left:15px;">
        <input type="checkbox" id="whCapexNo"  disabled> No
    </label>
</div>
<div class="HazopApprovalGrid">
				<table id="HazopApprovalGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
 
 
 
 </div>
  <div title="PSSRchecklists">
  <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
  <table>
  <tr>
   <%-- <td>
    <div  style="margin-left:-0px;margin-top:-20px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000"  id="txtPsrmprocess" name="txtPsrmprocess"  style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.mocPssrmst.psrmprocess}</textarea>	
	</div>
   </td> --%>
  <!-- <td> -->
  
  <td>
		  <div  style="margin-left:-0px;margin-top:-20px;">
	<label class="mandatory-lbl" >MOC Details</label>		
    </div>					     	   		
	<div style="margin-left:0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000"  id="txtPsrmmocdetail" name="txtPsrmmocdetail" style="width:832px;text-transform: uppercase; height :40px;" value="" >${requestScope.mocPssrmst.psrmmocdetail}</textarea>	
	</div>
						     	  </td>
 
   <td colspan="3" style="text-align:right; padding-right:2px; padding-top:-30px;">
   	   <div style="display:inline-block; text-align:left; margin-left:20px; margin-top:-20px;">
   	  <label class="mandatory-lbl">Date</label><br/>
				  <!--  <div style="width:350px;position:relative;margin-left:0px;margin-top:-0px;">  -->
						      <input type="text" class="easyui-text"  id="dtePsrmdate" name="dtePsrmdate" maxlength="10"   style=" width : 100px;height:20px; text-align:left;" value="${requestScope.mocPssrmst.psrmdate}"/>
						     	  </div> </td></tr>						     	 
						     	 <%--   <tr>
						     	  <td>
		  <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >MOC Details</label>		
    </div>					     	   		
	<div style="margin-left:0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000"  id="txtPsrmmocdetail" name="txtPsrmmocdetail" style="width:1125px;text-transform: uppercase; height :40px;" value="" >${requestScope.mocPssrmst.psrmmocdetail}</textarea>	
	</div>
						     	  </td>
						     	  </tr> --%>
   </table>
  
<div class="pssrGrddiv">
				<table id="PSSRGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
  </div>
   <div title="PSSRRecomnd">
      <div  style="margin-top: 0px;margin-left:1000px;">
    
	       <input type="button" class="easyui-button" value ="Add" id="btnAddNewReccommend" style="height:23px;margin-top:20px;"/>
	       <span style="padding-left:8px;">
	          <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;margin-top:20px;"/>
	       </span>
	      
     	</div>
     	<div class="QuestionaireGrddiv">
   <table id="PSSRReccomendGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div></div>
   
   </div>
   <div title="MOCClosure">
    <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="QuestionaireGrddiv">
				<table id="MOCCGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
   
   </div> 
<div title="FinalApprovals" >

<!-- <div class="FinalApprovalGrid"> -->
<div style="margin:15px;">
    <label class="mandatory-lbl" style="margin-right:22px;"><b>CapEx:</b></label>

    <label>
        <input type="checkbox" id="finalCapexYes"  disabled> Yes
    </label>

    <label style="margin-left:15px;">
        <input type="checkbox" id="finalCapexNo"  disabled> No
    </label>
</div>

<div class="FinalApprovalGrid" style="margin-left:20px;">
				<table id="FinalApprovalGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				<!--
				   <div style="margin-left:20px;margin-top:20px;">
 		<label style="color:green;"><b>MOC Completed?</b></label>
	<span><input id="chkMocClosed" name="chkMocClosed" type="checkbox" /></span>
	</div>  -->
	
<div style="margin-left:20px; margin-top:20px;">
    <table>
      <tr>
        <td>
          <input id="chkOption1" name="chkOption1" type="checkbox"
                 onchange="toggleFinalArea('txtOption1', this)" />
          <label for="chkOption1"><b>Process Hazard Analysis</b></label>
        </td>
        <td>&nbsp;&nbsp;&nbsp;</td>
        <td>
          <input id="chkOption2" name="chkOption2" type="checkbox"
                 onchange="toggleFinalArea('txtOption2', this)" />
          <label for="chkOption2"><b>Plant Safety Information</b></label>
        </td>
        <td>&nbsp;&nbsp;&nbsp;</td>
        <td>
          <input id="chkOption3" name="chkOption3" type="checkbox"
                 onchange="toggleFinalArea('txtOption3', this)" />
          <label for="chkOption3"><b>Asset Integrity</b></label>
        </td>
      </tr>
      <tr>
        <td>
          <textarea id="txtOption1" name="txtOption1"
                    rows="3" cols="30"
                    disabled="disabled"></textarea>
        </td>
        <td></td>
        <td>
          <textarea id="txtOption2" name="txtOption2"
                    rows="3" cols="30"
                    disabled="disabled"></textarea>
        </td>
        <td></td>
        <td>
          <textarea id="txtOption3" name="txtOption3"
                    rows="3" cols="30"
                    disabled="disabled"></textarea>
        </td>
      </tr>
    </table>
  </div>
  
  		   <div style="margin-left:20px;margin-top:20px;">
 		<label style="color:green;"><b>MOC Completed?</b></label>
	<span><input id="chkMocClosed" name="chkMocClosed" type="checkbox" /></span>
	</div>
				</div> 
				
	
	
	<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value="${requestScope.dcmTlDocumentmanager.dmdmFilename}"/>
	<input type="hidden" id="hdnDmdmKeyid" name="hdnDmdmKeyid"  />
	<input type="hidden" id="hdncreatedon" name="hdncreatedon"   />
<input type="hidden" id="hdnkeyid"  />
<input type="hidden" id="hdnfilename"  />
<input type="hidden" id="txtIsProjectteamLink" name="txtIsProjectteamLink" value=" "/>
<input type="hidden" id="hdnbuttonId" value="${requestScope.buttonId}"  />
<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />
<input type="hidden" id="hdnLoginUserid" name="hdnLoginUserid" value="${requestScope.loginUserid}"/>
<input type="hidden" id="hdnUserid" name="hdnUserid" value="${requestScope.userid}"  />
<input type="hidden" id="hdnFmgMode" value="${requestScope.fmgMode}"  />
</div> 
</div>
</div>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
 <input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
 <input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}">
 <input type="hidden" id="hdntitle" name="hdntitle" value="${requestScope.Title}"/>
 <input type="hidden" id="hdncurrDate" name="hdncurrDate" value="${requestScope.currDate}">
  <input type="hidden" id="hdnSuggestionId" name="hdnSuggestionId" value="${requestScope.SuggestionId}">
   <input type="hidden" id="hdnSuggestion" name="hdnSuggestion" value="${requestScope.Suggestion}">
    <input type="hidden" id="hdnSuggestionFlid" name="hdnSuggestionFlid" value="${requestScope.Sugflid}">
 	  <input type="hidden" id="hdnMocKeyId" name="hdnMocKeyId" value="${requestScope.mocRfcmst.rfcmKeyid}">
 	<input type="hidden" id="hdnResponsibility" name="hdnResponsibility" value="${requestScope.Responsibility}">
<input type="hidden" id="hdnInitiator" name="hdnInitiator" value="${requestScope.Kaizenbankmst.kzbnResponsibility}">
 	 <input type="hidden" id="hdnactnmode" name="hdnactnmode" value=""/>
 	 <input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.userid}">
 	   <input type="hidden" id="hdnrowid" name="hdnrowid" value=""/>
 	   <input type="hidden" id="hdncount" name="hdncount" value=""/>
 	     <input type="hidden" id="hdnMocStatus" name="hdnMocStatus" value=""/>
 	      <input type="hidden" id="hdnHazopcount" name="hdnHazopcount" value=""/>
 	        <input type="hidden" id="hdnInitialApproval" name="hdnInitialApproval" value=""/>
 	       <input type="hidden" id="hdnHazopApproval" name="hdnHazopApproval" value=""/>
 	     <input type="hidden" id="hdnStatusClose" name="hdnStatusClose" value="${requestScope.Status}"/>
 	       <input type="hidden" id="hdnFinalMaxApprovalStatus" name="hdnFinalMaxApprovalStatus" value=""/>
 	        <input type="hidden" id="hdnFinalcount" name="hdnFinalcount" value=""/>
 	       <input type="hidden" id="hdnWhatifKey" name="hdnWhatifKey" value="${requestScope.WhatifKey}">
 	       <input type="hidden" id="hdnHazopKey" name="hdnHazopKey" value="${requestScope.HazopKey}">
 	     <input type="hidden" id="hdnpssrCount" name="hdnpssrCount" value="${requestScope.pssrCount}"/>
 <input type="hidden" id="hdnWHCount" name="hdnWHCount" value="${requestScope.WHCount}"/>
<input type="hidden" id="hdnnature" name="hdnnature" value="${requestScope.nature}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>  
<input type="hidden" id="hdnClosurecount" name="hdnClosurecount" value=""> 
<input type="hidden" id="hdnJHleader" name="hdnJHleader" value="${requestScope.JHleader}"/> 
<input type="hidden" id="hdnDMTLeader" name="hdnDMTLeader" value="${requestScope.DMTLeader}"/>
<input type="hidden" id="hdnProcessArea" name="hdnProcessArea" value="${requestScope.ProcessArea}"/>
<input type="hidden" id="hdnMechArea" name="hdnMechArea" value="${requestScope.MechArea}"/>
<input type="hidden" id="hdnInstrumentArea" name="hdnInstrumentArea" value="${requestScope.InstrumentArea}"/>
<input type="hidden" id="hdnCivilArea" name="hdnCivilArea" value="${requestScope.CivilArea}"/>
<input type="hidden" id="hdnElectricalArea" name="hdnElectricalArea" value="${requestScope.ElectricalArea}"/>
<input type="hidden" id="hdnPBUHead" name="hdnPBUHead" value="${requestScope.PBUHead}"/>
<input type="hidden" id="hdnMOCDate" name="hdnMOCDate" value="${requestScope.mocDate}"/>

<input type="hidden" id="flid" name="flid" value="${requestScope.Sugflid}" />
<!-- mano -->
<input type="hidden" id="hdnPMMechChampion"  name="hdnPMMechChampion"  value="${requestScope.PMMechChampion}"/>
<input type="hidden" id="hdnPMEIChampion"    name="hdnPMEIChampion"    value="${requestScope.PMEIChampion}"/>
<input type="hidden" id="hdnPillarChampion"  name="hdnPillarChampion"  value="${requestScope.PillarChampion}"/>
<input type="hidden" id="hdnSafetyManager" name="hdnSafetyManager" value="${requestScope.SafetyManager}"/>

 <input type="hidden"
           id="chckMocRfcmCapex"
           name="chckMocRfcmCapex"
           value="${requestScope.mocRfcmst.rfcmCapex}" />
           
  <input type="hidden"
       id="hdnMocRfcmNoofdays"
       name="hdnMocRfcmNoofdays"
       value="${requestScope.mocRfcmst.rfcmNoofdays}" />
      
       <input type="hidden"
       id="hdnAdditionalNotes"
       name="hdnAdditionalNotes"
       value="${requestScope.othersNotes}" />
           
</form>