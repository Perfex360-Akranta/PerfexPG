 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">

jQuery(document).ready(function () {
	
	var allClitImgPanels = jQuery("[id='clitToolImgPanel']");
    if (allClitImgPanels.length > 1) {
        allClitImgPanels.each(function (idx) {
            if (idx < allClitImgPanels.length - 1) {
                var $old = jQuery(this);
                if ($old.data("ui-dialog")) {
                    try { $old.dialog("destroy"); } catch (e) {}
                }
                $old.closest(".ui-dialog").remove();
                $old.remove();
            }
        });
    }
	
    initialiseForm("frmMultipleClitStd");
    jQuery("#submitForm").val("frmMultipleClitStd");
    
    var machId = "${requestScope.machId}";
    var flid   = "${requestScope.flid}";
    var frmMode = jQuery("#mode").val();  
    
    jQuery("#hdnClitMachId").val(machId);
    jQuery("#hdnClitFlid").val(flid);

    if (flid != null && flid.trim() != "" && flid.trim() != "null") {
        loadFunctionalLocation(
            "clitMulFunLocation",
            "functionalLoc.baljhclit",
            "clitMulFunLocationValues",
            "frmMultipleClitStd",
            "&machId=" + machId + "&flid=" + flid
        );
    } else{
    	 if (machId != null && machId.trim() != "" && machId.trim() != "null") {
    	        viewClitGrid(machId, flid);
    	    }	
    }
    
    fillComboBox("frmMultipleClitStd", "cmbMulClisMachineid",
            "machineCombo.commonFilter?machId=" + machId);
    
    if (frmMode == "view") {
        disableForm("frmMultipleClitStd");
        jQuery("#btnClitAdd").hide();
        jQuery("#btnClitDelete").hide();
        jQuery("#dlgClitImg").hide();
        jQuery("#btnClitImgClear").hide();
        jQuery("#cmbMulClisMachineid").combobox("disable");
    }
    jQuery("#cmbMulClisMachineid").combobox({
        onLoadSuccess: function() {
            jQuery("#cmbMulClisMachineid").combobox('setValue', machId);
            jQuery("#cmbMulClisMachineid").combobox('disable');
        }
    });
    
    console.log("Ready:", clitNewRowSeq);
    jQuery("#btnClitAdd").off("click").click(function () {
    	addClitRow();
    });
    jQuery("#btnClitDelete").off("click").click(function () {
        removeClitRecord();
    });
    
    imageUpload(jQuery("#dlgClitImg"), 'ImageUpload.commonFilter?', 'dlgClitImg',
            "imgClitToolused", "imgClitToolusedFilename", "300", "200");
    
//     jQuery("#clitToolImgPanel").window({
//         title: "Block Diagram Image",
//         modal: true,
//         width: 260,
//         closed: true,
//         onOpen: function () {
//             bringClitImgPanelToFront();
//         }
//     });
		jQuery("#clitToolImgPanel").window({
		    title: "Block Diagram Image",
		    modal: true,
		    width: 260,
		    closed: true,
		    onOpen: function () {
		        bringClitImgPanelToFront();
		        jQuery("#clitToolImgPanel").window('move', {
		            left: jQuery(window).width() - 550,
		            top: 70
		        });
		    }
		});
//     var maxIndex = 0;
//     jQuery(".popup-mask, .custom-popup,.filterpanel,.panel,.window").each(function(){
// 		var currIndex = parseInt(jQuery(this).css("z-index"), 10);
		
		
// 	    if(currIndex > maxIndex) {
// 	    	maxIndex = currIndex;
// 	    }
// 	});
    
//     jQuery("#clitToolImgPanel").dialog({
//         title: "Block Diagram Image",
//         modal: true,
//         width: 260,
//         autoOpen: false,
//         zIndex: maxIndex,
//         close: function () { clitImgTargetRow = null; }
//     });
    
    var existingClitImg = jQuery("#imgClitToolusedFilename").val();
    if (existingClitImg != null && existingClitImg.trim() != "") {
        jQuery("#imgClitToolused").attr('src', existingClitImg);
    }
    
//     jQuery("#btnClitImgClear").click(function () {
//         jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//         jQuery('#imgClitToolusedFilename').val('');
//     });

// jQuery("#btnClitImgClear").click(function () {
//     jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//     jQuery('#imgClitToolusedFilename').val('');
//     lastClitImgVal = '';

//     var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//     var checkedRows = [];
//     for (var i = 0; i < rows.length; i++) {
//         if (jQuery('#jqg_ClitMultipleGrd_' + rows[i]).is(':checked')) {
//             checkedRows.push(rows[i]);
//         }
//     }

//     for (var i = 0; i < checkedRows.length; i++) {
//         var rid = checkedRows[i];
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", "");
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", "");
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagImg');
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagTempPath');
//         delete clitBlockDiagStore[rid];
//     }
// });


// jQuery("#btnClitImgClear").click(function () {
//     jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//     jQuery('#imgClitToolusedFilename').val('');
//     lastClitImgVal = '';

//     if (clitImgTargetRow) {
//         var rid = clitImgTargetRow;
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", "");
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", "");
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagImg');
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagTempPath');
//         delete clitBlockDiagStore[rid];
//     }
// });

// function clearClitBlockDiagFromGrid(rid) {
//     jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", "");
//     jQuery("#ClitMultipleGrd").jqGrid('setRowData', rid, {
//         imgClitBlockdiagram: ""
//     });
//     jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagImg');
//     jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagTempPath');
//     delete clitBlockDiagStore[rid];
// }
function clearClitBlockDiagFromGrid(rid) {
    jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", "");

    var addImgHtml = txtFormatterBlockDiagramMulti("", null, null);
    jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", addImgHtml);

    jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagImg');
    jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").removeData('clitBlockDiagTempPath');
    delete clitBlockDiagStore[rid];
}

jQuery("#btnClitImgClear").off("click").click(function () {
    if (!clitImgTargetRow) {
        jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
        jQuery('#imgClitToolusedFilename').val('');
        lastClitImgVal = '';
        return;
    }

    var rid = clitImgTargetRow;
    var clisKeyid = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisKeyid");

    jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
    jQuery('#imgClitToolusedFilename').val('');
    lastClitImgVal = '';

    if (clisKeyid != null && clisKeyid.trim() != "" && clisKeyid.trim() != " ") {
        processAjaxCalls(
            "deleteClitBlockDiagImg.baljhclit",
            "clisKeyid=" + encodeURIComponent(clisKeyid)
                + "&imgType=" + encodeURIComponent("BLK")
                + "&refDoc="  + encodeURIComponent("CLI"),
            function (result) { clitDeleteImg_successCallBack(result, rid); },
            'clitDeleteImg_errorCallBack'
        );
    } else {
        clearClitBlockDiagFromGrid(rid);
    }
});




    
//     	 lastClitImgVal = jQuery("#imgClitToolusedFilename").val();

//     setInterval(function () {
//         var currentVal = jQuery("#imgClitToolusedFilename").val();
//         if (currentVal !== lastClitImgVal) {
//             lastClitImgVal = currentVal;
//             applyClitBlockDiagram(currentVal);
//         }
//     }, 500);

lastClitImgVal = jQuery("#imgClitToolusedFilename").val();

setInterval(function () {
    if (!jQuery("#multipleEntryDiv").is(":visible")) return;

    var currentVal = jQuery("#imgClitToolusedFilename").val();
    if (currentVal !== lastClitImgVal) {
        lastClitImgVal = currentVal;
        if (clitImgTargetRow && currentVal && currentVal.trim() != "") {
            applyClitBlockDiagramToRow(clitImgTargetRow, currentVal);
            jQuery("#clitToolImgPanel").window('close');
        }
    }
}, 500);

    
//     jQuery("#ClitMultipleGrd").on("click", "img.clitBlockThumb", function () {
//         var fullSrc = jQuery(this).attr("src");
//         var popupHtml = '<div style="text-align:center;padding:10px;">'
//                        + '<img src="' + fullSrc + '" style="max-width:600px;max-height:500px;" />'
//                        + '</div>';
//         jQuery('<div></div>').html(popupHtml).dialog({
//             title: "Block Diagram",
//             modal: true,
//             width: 650,
//             height: 550,
//             close: function () { jQuery(this).dialog('destroy').remove(); }
//         });
//     });

// 		jQuery("#ClitMultipleGrd").off("click", "button.clitMulBlockAddBtn", function () {
// 		    var rid = jQuery(this).closest("tr").attr("id");
// 		    openClitImgPanel(rid);
// 		});
		
// 		jQuery("#ClitMultipleGrd").off("dblclick", "img.clitMulBlockThumb", function () {
// 		    var rid = jQuery(this).closest("tr").attr("id");
// 		    openClitImgPanel(rid);
// 		});

	jQuery("#ClitMultipleGrd").off("click", "button.clitMulBlockAddBtn").on("click", "button.clitMulBlockAddBtn", function () {
	    var rid = jQuery(this).closest("tr").attr("id");
	    openClitImgPanel(rid);
	});
	
	jQuery("#ClitMultipleGrd").off("dblclick", "img.clitMulBlockThumb").on("dblclick", "img.clitMulBlockThumb", function () {
	    var rid = jQuery(this).closest("tr").attr("id");
	    openClitImgPanel(rid);
	});
	var clitFrmMode = jQuery("#hdnClitMode").val();
	if (clitFrmMode == "view") {
	    disableForm("frmMultipleClitStd");

	    jQuery("#btnClitAdd").hide();
	    jQuery("#btnClitDelete").hide();
	    jQuery("#dlgClitImg").hide();
	    jQuery("#btnClitImgClear").hide();

	    jQuery("#cmbMulClisMachineid").combobox("disable");

	    setTimeout(function () {
	        jQuery("#ClitMultipleGrd").jqGrid('setGridParam', { cellEdit: false });
	        jQuery("#ClitMultipleGrd").jqGrid('hideCol', 'cb');

	        // NEW — root cause fix: colModel-ஐலேயே pEditable false ஆக்குறது
	        var colModel = jQuery("#ClitMultipleGrd").jqGrid('getGridParam', 'colModel');
	        for (var i = 0; i < colModel.length; i++) {
	            if (colModel[i].pEditable) {
	                colModel[i].pEditable = false;
	            }
	        }

	        // Add Image / Tool Info / File Mgr போன்ற dynamic buttons-ஐயும் block பண்ணுறது
	        jQuery("#ClitMultipleGrd").find("button.clitMulBlockAddBtn").prop("disabled", true).css("pointer-events", "none");
	        jQuery("#ClitMultipleGrd").find("button, input[type=button]").prop("disabled", true);
	    }, 500);
	}
	});


// function applyClitBlockDiagram(tempImgPath) {
//     if (!tempImgPath || tempImgPath.trim() == "") return;

//     var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//     var checkedRows = [];
//     for (var i = 0; i < rows.length; i++) {
//         if (jQuery('#jqg_ClitMultipleGrd_' + rows[i]).is(':checked')) {
//             checkedRows.push(rows[i]);
//         }
//     }

//     if (checkedRows.length == 0) {
//         alert("Please check at least one Standard row before uploading an image.");
//         return;
//     }

//     for (var i = 0; i < checkedRows.length; i++) {
//         var rid = checkedRows[i];
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", tempImgPath);
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram",
//             '<img src="' + tempImgPath + '" class="clitBlockThumb" style="width:40px;height:30px;cursor:pointer;" />');
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg', tempImgPath);
//     }
// }

//og
// function applyClitBlockDiagram(tempImgPath) {
//     if (!tempImgPath || tempImgPath.trim() == "") return;

//     var bareFileName = tempImgPath.substring(tempImgPath.lastIndexOf('/') + 1);

//     var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//     var checkedRows = [];
//     for (var i = 0; i < rows.length; i++) {
//         if (jQuery('#jqg_ClitMultipleGrd_' + rows[i]).is(':checked')) {
//             checkedRows.push(rows[i]);
//         }
//     }

//     if (checkedRows.length == 0) {
//         alert("Please check at least one Standard row before uploading an image.");
//         return;
//     }

//     for (var i = 0; i < checkedRows.length; i++) {
//         var rid = checkedRows[i];
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", bareFileName);
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", tempImgPath);
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg', bareFileName);
        
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagTempPath', tempImgPath);
        
//         clitBlockDiagStore[rid] = { file: bareFileName, path: tempImgPath };
//     }
//     jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//     jQuery('#imgClitToolusedFilename').val('');
//     lastClitImgVal = '';
// }

function applyClitBlockDiagramToRow(rid, tempImgPath) {
    if (!tempImgPath || tempImgPath.trim() == "") return;

    var bareFileName = tempImgPath.substring(tempImgPath.lastIndexOf('/') + 1);

    jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", bareFileName);
    jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", tempImgPath);
    jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg', bareFileName);
    jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagTempPath', tempImgPath);
    clitBlockDiagStore[rid] = { file: bareFileName, path: tempImgPath };
}

// function clitDeleteImg_successCallBack(result) {
//     console.log("Delete image response:", result);
//     if (!result || result.success !== true) {
//         alert("Error deleting image: " + (result && result.error ? result.error : "Unknown error"));
//         return;
//     }
//     if (clitImgTargetRow) {
//         clearClitBlockDiagFromGrid(clitImgTargetRow);
//     }
// }
function clitDeleteImg_successCallBack(result, rid) {
    console.log("Delete image response:", result);
    if (!result || result.success !== true) {
        alert("Error deleting image: " + (result && result.error ? result.error : "Unknown error"));
        return;
    }
    if (rid) {
        clearClitBlockDiagFromGrid(rid);
    }
}	

function clitDeleteImg_errorCallBack(result) {
    alert("Error deleting image. Please try again.");
}

// function openClitImgPanel(rid) {
//     clitImgTargetRow = rid;

//     var existingImg = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisBlockDiagImg");
//     if (existingImg && existingImg.trim() != "") {
//         jQuery("#imgClitToolused").attr('src', "tmp/images/" + existingImg);
//     } else {
//         jQuery("#imgClitToolused").attr('src', 'images/tools.jpg');
//     }
//     jQuery("#imgClitToolusedFilename").val('');
//     lastClitImgVal = '';

//     if (!clitImgPanelDialogInit) {
//         jQuery("#clitToolImgPanel").dialog({
//             title: "Block Diagram Image",
//             modal: true,
//             width: 260,
//             autoOpen: false,
//             close: function () { clitImgTargetRow = null; }
//         });
//         clitImgPanelDialogInit = true;
//     }
//     jQuery("#clitToolImgPanel").dialog('open');
// }
// function openClitImgPanel(rid) {
//     clitImgTargetRow = rid;

//     var existingImg = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisBlockDiagImg");
//     if (existingImg && existingImg.trim() != "") {
//         jQuery("#imgClitToolused").attr('src', "tmp/images/" + existingImg);
//     } else {
//         jQuery("#imgClitToolused").attr('src', 'images/tools.jpg');
//     }
//     jQuery("#imgClitToolusedFilename").val('');
//     lastClitImgVal = '';

//     jQuery("#clitToolImgPanel").dialog('open');
// }

function bringClitImgPanelToFront() {
    var maxIndex = 0;
    jQuery(".popup-mask, .custom-popup, .filterpanel, .panel, .window").each(function () {
        var currIndex = parseInt(jQuery(this).css("z-index"), 10);
        if (!isNaN(currIndex) && currIndex > maxIndex) maxIndex = currIndex;
    });
    var targetZ = maxIndex + 1000;

    var windowEl = jQuery("#clitToolImgPanel").closest(".window").get(0);
    if (windowEl) {
        windowEl.style.setProperty("z-index", targetZ, "important");
    }
}


function openClitImgPanel(rid) {
	if (jQuery("#hdnClitMode").val() == "view") return;
	if (jQuery("#ClitMultipleGrd").length === 0 ||
        jQuery("#clitToolImgPanel").length === 0 ||
        !jQuery("#multipleEntryDiv").is(":visible")) {
        return;
    }

    var isRowChecked = jQuery('#jqg_ClitMultipleGrd_' + rid).is(':checked');
    if (!isRowChecked) {
        alert("Please check this row before adding a Block Diagram image.");
        return;
    }

    clitImgTargetRow = rid;

    var existingImg = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisBlockDiagImg");
    if (existingImg && existingImg.trim() != "") {
        jQuery("#imgClitToolused").attr('src', "tmp/images/" + existingImg);
    } else {
        jQuery("#imgClitToolused").attr('src', 'images/tools.jpg');
    }
    jQuery("#imgClitToolusedFilename").val('');
    lastClitImgVal = '';

    jQuery("#clitToolImgPanel").window('open'); 
}

// function applyClitBlockDiagram(tempImgPath) {
//     if (!tempImgPath || tempImgPath.trim() == "") return;

//     var bareFileName = tempImgPath.substring(tempImgPath.lastIndexOf('/') + 1);

//     var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//     var checkedRows = [];
//     for (var i = 0; i < rows.length; i++) {
//         if (jQuery('#jqg_ClitMultipleGrd_' + rows[i]).is(':checked')) {
            
//             var existingImg = jQuery("#ClitMultipleGrd").jqGrid('getCell', rows[i], "hdnClisBlockDiagImg");
//             if (!existingImg || existingImg.trim() == "" || existingImg.trim() == " ") {
//                 checkedRows.push(rows[i]);
//             }
//         }
//     }

//     if (checkedRows.length == 0) {
//         alert("All checked rows already have a Block Diagram image. Clear it first with \"-\" if you want to replace it.");
//         return;
//     }
//     for (var i = 0; i < checkedRows.length; i++) {
//         var rid = checkedRows[i];
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "hdnClisBlockDiagImg", bareFileName);
//         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram", tempImgPath);
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg', bareFileName);
//         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagTempPath', tempImgPath);
//         clitBlockDiagStore[rid] = { file: bareFileName, path: tempImgPath };
//     }

//     jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//     jQuery('#imgClitToolusedFilename').val('');
//     lastClitImgVal = '';
// }	


// function getClitStdDetailsCorrected() {
//     var gridval = getGridSelectArray('ClitMultipleGrd');
//     if (!gridval || gridval == "[]" || gridval == "") return gridval;

//     try {
//         var selArr = JSON.parse(gridval);
//         var rowIds = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');

//         for (var i = 0; i < selArr.length && i < rowIds.length; i++) {
//             var rid = rowIds[i];
//             var storedPath = jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg');
//             if (storedPath) {
//                 selArr[i].hdnClisBlockDiagImg = storedPath;
//             }
//         }

//         return JSON.stringify(selArr);
//     } catch (e) {
//         return gridval;
//     }
// }

// function getClitStdDetailsCorrected() {
//     var gridval = getGridSelectArray('ClitMultipleGrd');
//     if (!gridval || gridval == "[]" || gridval == "") return gridval;

//     try {
//         var selArr = JSON.parse(gridval);
//         var rowIds = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');

//         for (var i = 0; i < selArr.length && i < rowIds.length; i++) {
//             var rid = rowIds[i];
//             var storedPath = jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg');
//             var tempPath   = jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagTempPath');
//             if (storedPath) {
//                 selArr[i].hdnClisBlockDiagImg = storedPath;     
//             }
//             if (tempPath) {
//                 selArr[i].tempClisBlockDiagPath = tempPath;      
//             }
//             selArr[i].rowid = rid; 
//         }

//         return JSON.stringify(selArr);
//     } catch (e) {
//         return gridval;
//     }
// }
function getClitStdDetailsCorrected() {
    var gridval = getGridSelectArray('ClitMultipleGrd');
    if (!gridval || gridval == "[]" || gridval == "") return gridval;

    try {
        var selArr = JSON.parse(gridval);

        var allRowIds = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
        var checkedRowIds = [];
        for (var j = 0; j < allRowIds.length; j++) {
            if (jQuery('#jqg_ClitMultipleGrd_' + allRowIds[j]).is(':checked')) {
                checkedRowIds.push(allRowIds[j]);
            }
        }

        for (var i = 0; i < selArr.length && i < checkedRowIds.length; i++) {
            var rid = checkedRowIds[i];
            var storedPath = jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg');
            var tempPath   = jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagTempPath');
            var stored     = clitBlockDiagStore[rid];

            if (stored) {
                selArr[i].hdnClisBlockDiagImg = stored.file;
                selArr[i].tempClisBlockDiagPath = stored.path;
            } else if (storedPath) {
                selArr[i].hdnClisBlockDiagImg = storedPath;
                if (tempPath) selArr[i].tempClisBlockDiagPath = tempPath;
            }

            selArr[i].rowid = rid;
        }

        return JSON.stringify(selArr);
    } catch (e) {
        return gridval;
    }
}
/* function frmMultipleClitStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
    var url = jQuery("#hiddenUrl").val();
    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewClitGrid(url, "q=2&flid=" + keyIds.flId);
    else
        viewClitGrid(url, "q=2");
} */
/* function frmMultipleClitStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
	var machId = jQuery("#machine").val();
    var flid   = jQuery("#flid").val();
    
    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
    }
    
    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewClitGrid(machId, keyIds.flId);
    else
        viewClitGrid(machId, "");
}
 */
 
//mano
function frmMultipleClitStd_FuntLocHierarchy_SuccessCallBack(keyIds) {
    console.log("=== FuntLocHierarchy_SuccessCallBack FIRED ===");
    console.log("Full keyIds object:", JSON.stringify(keyIds));

    var machId = jQuery("#machine").val();
    var flid   = jQuery("#flid").val();

    if (keyIds.elementId != undefined && keyIds.elementId.trim().length > 0) {
        jQuery("#elementId").val(keyIds.elementId);
    }

    var sbuVal = (keyIds.sbuId != undefined && keyIds.sbuId != null) ? String(keyIds.sbuId).trim() : "";
    console.log("sbuVal computed:", sbuVal);

    if (sbuVal != "") {
        jQuery("#frmMultipleClitStd input[id='factory']").val(sbuVal);
        console.log("AFTER setting - #factory value is now:", jQuery("#frmMultipleClitStd input[id='factory']").val());
    } else {
        console.log("sbuVal was EMPTY, so #factory was never touched. Current #factory value:", jQuery("#frmMultipleClitStd input[id='factory']").val());
    }

    if (keyIds.flId != undefined && keyIds.flId.trim().length > 0)
        viewClitGrid(machId, keyIds.flId);
    else
        viewClitGrid(machId, "");
}

/* function viewClitGrid(machId, flid) {
    processGridnew(
        "multipleClitStd_input.baljhclit",
        "?machId=" + machId + "&flid=" + flid,
        "ClitMultipleGrd",
        "clitMulPager",
        "", "",
        "ClitMultipleGrd_selectRow"
    );
} */
/* function viewClitGrid(machId, flid, onLoadComplete) {
    processGridnew(
        "multipleClitStd_input.baljhclit",
        "?machId=" + machId + "&flid=" + flid,
        "ClitMultipleGrd",
        "clitMulPager",
        "", "",
        "ClitMultipleGrd_selectRow"
    );

   
    if (onLoadComplete) {
        setTimeout(function () {
            jQuery("#ClitMultipleGrd").jqGrid('setGridParam', {
                loadComplete: function () {
                    onLoadComplete();
                }
            });
        }, 200);
    }
} */
// function viewClitGrid(machId, flid, onLoadComplete) {
//     processGridnew(
//         "multipleClitStd_input.baljhclit",
//         "?machId=" + machId + "&flid=" + flid,
//         "ClitMultipleGrd",
//         "clitMulPager",
//         "", "",
//         "ClitMultipleGrd_selectRow"
//     );

//     setTimeout(function () {
//         jQuery("#ClitMultipleGrd").jqGrid('setGridParam', {
//             loadComplete: function () {
//                 var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//                 for (var i = 0; i < rows.length; i++) {
//                     var rid = rows[i];
//                     var imgPath = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisBlockDiagImg");
//                     if (imgPath && imgPath.trim() != "" && imgPath.trim() != " ") {
//                         jQuery("#ClitMultipleGrd").jqGrid('setCell', rid, "imgClitBlockdiagram",
//                             '<img src="' + imgPath + '" class="clitBlockThumb" style="width:40px;height:30px;cursor:pointer;" />');
//                         jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data('clitBlockDiagImg', imgPath);
//                     }
//                 }
//                 if (onLoadComplete) onLoadComplete();
//             }
//         });
//     }, 200);
// }

 function viewClitGrid(machId, flid, onLoadComplete) {
	 var mode = jQuery("#hdnClitMode").val();
	    var filterStr = "?machId=" + machId + "&flid=" + flid;
	    if (mode) {
	        filterStr += "&mode=" + mode;
	    }
    processGridnew(
        "multipleClitStd_input.baljhclit",
        "?machId=" + machId + "&flid=" + flid,
        "ClitMultipleGrd",
        "clitMulPager",
        "", "",
        "ClitMultipleGrd_selectRow",
        "ClitMultipleGrd_completeCallback"
        

    );
} 
// function viewClitGrid(machId, flid, onLoadComplete) {
//     processGridnew(
//         "multipleClitStd_input.baljhclit",
//         "?machId=" + machId + "&flid=" + flid,
//         "ClitMultipleGrd",
//         "clitMulPager",
//         "",                                // tableCaption
//         "",                                // doubleClickFunction
//         "",                                // tableHeaderSpanCallback
//         "ClitMultipleGrd_completeCallback",// onloadcompletecallback  <-- fixes thumbnails
//         "ClitMultipleGrd_selectRow"        // selectRowFunction        <-- fixes row-select binds
//     );
// }

function ClitMultipleGrd_completeCallback(result) {
    clitBlockDiagStore = {};
    var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
    for (var i = 0; i < rows.length; i++) {
        var rid = rows[i];
        var imgPath = jQuery("#ClitMultipleGrd").jqGrid('getCell', rid, "hdnClisBlockDiagImg");
        if (imgPath && imgPath.trim() != "" && imgPath.trim() != " ") {
            var imgSrc = "tmp/images/" + imgPath.trim();
            jQuery("#ClitMultipleGrd").jqGrid("setCell", rid, "imgClitBlockdiagram", imgSrc);
            jQuery("#ClitMultipleGrd").find("tr[id='" + rid + "']").data("clitBlockDiagImg", imgPath);
        }
    }

    // NEW — view mode-ல ovvorрு reload-லேயும் column hide + pEditable block
    if (jQuery("#hdnClitMode").val() == "view") {
        jQuery("#ClitMultipleGrd").jqGrid('hideCol', 'cb');

        var colModel = jQuery("#ClitMultipleGrd").jqGrid('getGridParam', 'colModel');
        for (var i = 0; i < colModel.length; i++) {
            if (colModel[i].pEditable) {
                colModel[i].pEditable = false;
            }
        }

        jQuery("#ClitMultipleGrd").find("button.clitMulBlockAddBtn").prop("disabled", true).css("pointer-events", "none");
        jQuery("#ClitMultipleGrd").find("button, input[type=button]").prop("disabled", true);
    }

    if (window.clitGrdExternalLoadComplete) {
        window.clitGrdExternalLoadComplete();
        window.clitGrdExternalLoadComplete = null;
    }
}

var clitNewRowSeq = 0;  
var lastClitImgVal = "";
var clitImgTargetRow = null;
var clitImgPanelDialogInit = false;
function addClitRow() {
	console.log("Before :", clitNewRowSeq, typeof clitNewRowSeq);
	console.log("After :", clitNewRowSeq);
    clitNewRowSeq++;
    var newRowId = "new" + clitNewRowSeq;
 
    var emptyItem = {
        hdnClisKeyid:                   " ",
        hdnMulClisTradeid:              " ",
        cmbMulClisTradeid:              " ",
        txtMulClisStandard:             " ",
        txtMulClisWhatactivity:         " ",
        hdnMulClisActivitytype:         " ",
        cmbMulClisActivitytype:         " ",
        hdnMulClisFrequencyunit:        " ",
        cmbMulClisFrequencyunit:        " ",
        hdnMulClisShiftid:              " ",
        cmbMulClisShiftid:              " ",
        txtMulClisHowmethod:            " ",
        dteClisNextduedate:             " ",
        txtMulClisTime:                 " ",
        chkMulClisIstoolsreq:           " ",
        btnToolpop:                     " ",
        txtMulClisBlockdiagram:         " ",
        hdnClisBlockDiagImg:            " ",
        txtMulClisWherelocation:        " ",
        txtMulClisShifttime:            " ",
        txtMulClisWhyifnotdone:         " ",
        txtMulClisCorrectiveaction:     " ",
        hdnMulClisDesigid:              " ",
        cmbMulClisResponsibilitydesgid: " ",
        hdnMulClisRespid:               " ",
        cmbMulClisResponsibilityid:     " ",
        hdnMulClisPreparid:             " ",
        cmbMulClisPreparedbyid:         " ",
        btnClitFilManage:               " "
    };
 
    jQuery("#ClitMultipleGrd").jqGrid('addRowData', newRowId, emptyItem, "first");
}

// function addClitRow(row) {
//     var emptyItem = {
//         hdnClisKeyid:                   " ",
//         hdnMulClisTradeid:              " ",
//         cmbMulClisTradeid:              " ",
//         txtMulClisStandard:             " ",
//         txtMulClisWhatactivity:         " ",
//         hdnMulClisActivitytype:         " ",
//         cmbMulClisActivitytype:         " ",
//         hdnMulClisFrequencyunit:        " ",
//         cmbMulClisFrequencyunit:        " ",
//         hdnMulClisShiftid:              " ",
//         cmbMulClisShiftid:              " ",
//         txtMulClisHowmethod:            " ",
//         dteClisNextduedate:             " ",
//         txtMulClisTime:                 " ",
//         chkMulClisIstoolsreq:           " ",
//         btnToolpop:                     " ",
//         txtMulClisBlockdiagram:         " ",
//         txtMulClisWherelocation:        " ",
//         txtMulClisShifttime:            " ",
//         txtMulClisWhyifnotdone:         " ",
//         txtMulClisCorrectiveaction:     " ",
//         hdnMulClisDesigid:              " ",
//         cmbMulClisResponsibilitydesgid: " ",
//         hdnMulClisRespid:               " ",
//         cmbMulClisResponsibilityid:     " ",
//         hdnMulClisPreparid:             " ",
//         cmbMulClisPreparedbyid:         " ",
//         btnClitFilManage:               " "
//     };
 
//     if (row == null || row == '' || parseInt(row) <= 0) {
//         jQuery("#ClitMultipleGrd").jqGrid('addRowData', 1, emptyItem);
//     } else {
//         var lastRow;
//         for (var i = 0; i < row.length; i++) lastRow = row[i];
//         jQuery("#ClitMultipleGrd").jqGrid('addRowData', parseInt(lastRow) + 1, emptyItem);
//     }
// }

var clitDeleteQueue = [];
 
function removeClitRecord() {
    var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
    var checkedRows = [];
 
    for (var i = 0; i < rows.length; i++) {
        if (jQuery('#jqg_ClitMultipleGrd_' + rows[i]).is(':checked')) {
            checkedRows.push(rows[i]);
        }
    }
 
    if (checkedRows.length == 0) {
        alert("Please select at least one row to delete.");
        return;
    }
 
    var localRowIds = [];
    clitDeleteQueue = [];
 
    for (var i = 0; i < checkedRows.length; i++) {
        var rowid = checkedRows[i];
        var keyid = jQuery("#ClitMultipleGrd").jqGrid('getCell', rowid, "hdnClisKeyid");
        if (keyid != null && keyid.trim().length > 0) {
            clitDeleteQueue.push(keyid);
        } else {
            localRowIds.push(rowid);
        }
    }
 
    if (!confirm("Do You Want To Delete Selected Row(s)?")) {
        clitDeleteQueue = [];
        return;
    }
 
    
    for (var i = 0; i < localRowIds.length; i++) {
        jQuery("#ClitMultipleGrd").jqGrid('delRowData', localRowIds[i]);
        delete clitBlockDiagStore[localRowIds[i]];
    }
 
 
    if (clitDeleteQueue.length > 0) {
        processAjaxCalls(
            "MultipleClitStd_remove.baljhclit",
            "keyid=" + clitDeleteQueue[0],
            'clitRemove_successCallBack',
            'clitRemove_errorCallBack'
        );
    }
}
 
function clitRemove_successCallBack(result) {
    clitDeleteQueue.shift();
    if (clitDeleteQueue.length > 0) {
        processAjaxCalls(
            "MultipleClitStd_remove.baljhclit",
            "keyid=" + clitDeleteQueue[0],
            'clitRemove_successCallBack',
            'clitRemove_errorCallBack'
        );
    } else {
    	clitBlockDiagStore = {}; 
        jQuery("#ClitMultipleGrd").trigger("reloadGrid");
    }
}
 
function clitRemove_errorCallBack(result) {
    popupCommonErrorMsg("Error while deleting record.");
}
// function removeClitRecord() {
//     var rows = jQuery("#ClitMultipleGrd").jqGrid('getDataIDs');
//     for (var i = 0; i < rows.length; i++) {
//         var rowid = rows[i];
//         if (jQuery('#jqg_ClitMultipleGrd_' + rowid).is(':checked')) {
//             var keyid = jQuery("#ClitMultipleGrd").jqGrid('getCell', rowid, "hdnClisKeyid");
//             if (keyid != null && keyid.trim().length > 0) {
//                 if (confirm("Do You Want To Delete?")) {
//                     processAjaxCalls(
//                         "MultipleClitStd_remove.baljhclit",
//                         "keyid=" + keyid,
//                         'clitRemove_successCallBack',
//                         'clitRemove_errorCallBack'
//                     );
//                 } else {
//                     return false;
//                 }
//             } else {
//                 if (confirm("Do You Want To Remove Row?"))
//                     jQuery("#ClitMultipleGrd").trigger("reloadGrid");
//                 else
//                     return false;
//             }
//         }
//     }
// }



//  function ClitMultipleGrd_selectRow(rowId) {
//     var jqGridId = "ClitMultipleGrd";

 
//     var detectedDateCtrl = "dteClisEffectivedate_" + jqGridId + "_" + rowId;
//     setTimeout(function () {
//         fillWithCurrentDate(detectedDateCtrl);
//     }, 550);

//     var nextDueDateCtrl = "dteClisNextduedate_" + jqGridId + "_" + rowId;
//     setTimeout(function () {
//         fillWithCurrentDatePlOne(nextDueDateCtrl);
//     }, 600);

   
//     jQuery("#cmbMulClisFrequencyunit_" + jqGridId + "_" + rowId).combobox({
//         onSelect: function (record) {
//             var lblShift = jQuery("#lblshift_" + jqGridId + "_" + rowId);
//             if (record.id == "S")
//                 lblShift.removeClass('mandatory-lbl');
//             else
//                 lblShift.addClass('mandatory-lbl');
//         }
//     });


//     jQuery("#chkMulClisIstoolsreq_" + jqGridId + "_" + rowId).click(function () {
//         var isChecked = jQuery(this).is(':checked');
//         var tooltreeBtn = jQuery("#btnToolpop_" + jqGridId + "_" + rowId);
//         if (isChecked) {
//             tooltreeBtn.attr('class', 'easyui-button').removeAttr('disabled');
//         } else {
//             tooltreeBtn.removeAttr('class').attr('disabled', 'disabled');
//         }
//     });


//     jQuery("#" + nextDueDateCtrl).datebox({
//         onSelect: function (date) {
//             isValidClitDate(nextDueDateCtrl, rowId, date);
//         }
//     });
// }
function ClitMultipleGrd_selectRow(rowId) {
	 if (jQuery("#hdnClitMode").val() == "view") return;  
	var jqGridId = "ClitMultipleGrd";

    var detectedDateCtrl = "dteClisEffectivedate_" + jqGridId + "_" + rowId;
    setTimeout(function () {
        fillWithCurrentDate(detectedDateCtrl);
    }, 550);

  
    var existingKeyId = jQuery("#ClitMultipleGrd").jqGrid('getCell', rowId, "hdnClisKeyid");
    if (existingKeyId == null || existingKeyId.trim() == "" || existingKeyId.trim() == " ") {
        var nextDueDateCtrl = "dteClisNextduedate_" + jqGridId + "_" + rowId;
        setTimeout(function () {
            fillWithCurrentDatePlOne(nextDueDateCtrl);
        }, 600);
    }

    jQuery("#cmbMulClisFrequencyunit_" + jqGridId + "_" + rowId).combobox({
        onSelect: function (record) {
            var lblShift = jQuery("#lblshift_" + jqGridId + "_" + rowId);
            if (record.id == "S")
                lblShift.removeClass('mandatory-lbl');
            else
                lblShift.addClass('mandatory-lbl');
        }
    });

    jQuery("#chkMulClisIstoolsreq_" + jqGridId + "_" + rowId).click(function () {
        var isChecked = jQuery(this).is(':checked');
        var tooltreeBtn = jQuery("#btnToolpop_" + jqGridId + "_" + rowId);
        if (isChecked) {
            tooltreeBtn.attr('class', 'easyui-button').removeAttr('disabled');
        } else {
            tooltreeBtn.removeAttr('class').attr('disabled', 'disabled');
        }
    });

    var nextDueDateCtrl = "dteClisNextduedate_" + jqGridId + "_" + rowId;
    jQuery("#" + nextDueDateCtrl).datebox({
        onSelect: function (date) {
            isValidClitDate(nextDueDateCtrl, rowId, date);
        }
    });
}

function isValidClitDate(dateCtrl, rowId, date) {
    var approvalDate = formatClitDate(date);
    var currentDate  = getServerDateTime();
    if (convertStringToDate(approvalDate) < currentDate) {
        alert('Next Due Date should not be less than Current Date');
        setTimeout(function () { fillWithCurrentDatePlOne(dateCtrl); }, 250);
        return false;
    }
}

function formatClitDate(date) {
    var newDate = new Date(date);
    var months  = ["Jan","Feb","Mar","Apr","May","Jun",
                   "Jul","Aug","Sep","Oct","Nov","Dec"];
    return String(newDate.getDate()).padStart(2,'0') + '-'
         + months[newDate.getMonth()] + '-'
         + newDate.getFullYear();
}


function ClitMultipleGrdbtnClitFilManage_onClick(result) {
    var rowid    = result.rowId;
    var refDocId = jQuery("#ClitMultipleGrd").jqGrid('getCell', rowid, "hdnClisKeyid");
    if (refDocId.length <= 1) {
        saveForm('frmMultipleClitStd',
            'MultipleClitStd_save.baljhclit?openfilemgr=openfilemgr&rowid=' + rowid);
    } else {
        fileManagerPopUp(refDocId, "CLI", "", "", "", "");
    }
}




// function validateClitMandatory(gridSelArr) {
//     var selArr = JSON.parse(gridSelArr);
//     for (var i = 0; i < selArr.length; i++) {

//         if (selArr[i].txtMulClisStandard.trim() == "") {
//             alert("Enter the Standard");
//             return false;
//         }
//         if (selArr[i].txtMulClisWhatactivity.trim() == "") {
//             alert("Enter the Item / Activity");
//             return false;
//         }
//         if (selArr[i].cmbMulClisActivitytype.trim() == "") {
//             alert("Select Classification");
//             return false;
//         }
//         if (selArr[i].cmbMulClisFrequencyunit.trim() == "") {
//             alert("Select Frequency (What)");
//             return false;
//         }
//         if (selArr[i].txtMulClisHowmethod.trim() == "") {
//             alert("Enter the Method");
//             return false;
//         }
//         if (selArr[i].dteClisNextduedate.trim() == "") {
//             alert("Select Next Due Date");
//             return false;
//         }
//         if (selArr[i].txtMulClisHowmuchduration.trim() == ""
//          || selArr[i].txtMulClisHowmuchduration.trim() == "0") {
//             alert("Enter valid Time (in Minutes), value must be greater than 0");
//             return false;
//         }
//     }
//     return true;
// }
function validateClitMandatory(gridSelArr) {
    var selArr = JSON.parse(gridSelArr);
    for (var i = 0; i < selArr.length; i++) {
 
        if (selArr[i].txtMulClisStandard.trim() == "") {
            alert("Enter the Standard");
            return false;
        }
        if (selArr[i].txtMulClisWhatactivity.trim() == "") {
            alert("Enter the Item / Activity");
            return false;
        }
        if (selArr[i].cmbMulClisActivitytype.trim() == "") {
            alert("Select Classification");
            return false;
        }
        if (selArr[i].cmbMulClisFrequencyunit.trim() == "") {
            alert("Select Frequency (What)");
            return false;
        }
        if (selArr[i].txtMulClisHowmethod.trim() == "") {
            alert("Enter the Method");
            return false;
        }
        if (selArr[i].dteClisNextduedate.trim() == "") {
            alert("Select Next Due Date");
            return false;
        }
        if (selArr[i].txtMulClisTime.trim() == ""
         || selArr[i].txtMulClisTime.trim() == "0") {
            alert("Enter valid Time (in Minutes), value must be greater than 0");
            return false;
        }
    }
    return true;
}

// function txtFormatterBlockDiagram(cellvalue, options, rowObject) {
// 	console.log("Formatter received:", cellvalue);
//     if (!cellvalue || cellvalue.trim() == "" || cellvalue.trim() == " ") return "";
//     return '<img src="' + cellvalue + '" class="clitBlockThumb" style="width:40px;height:30px;cursor:pointer;" />';
// }

// function txtFormatterBlockDiagram(cellvalue, options, rowObject) {
//     if (!cellvalue || cellvalue.trim() == "" || cellvalue.trim() == " ") {
//         return '<button type="button" class="clitBlockAddBtn" style="cursor:pointer;">Add Image</button>';
//     }
//     return '<img src="' + cellvalue + '" class="clitBlockThumb" style="width:40px;height:30px;cursor:pointer;" title="Double-click to change" />';
// }
function txtFormatterBlockDiagramMulti(cellvalue, options, rowObject) {
    if (!cellvalue || cellvalue.trim() == "" || cellvalue.trim() == " ") {
        return '<button type="button" class="clitMulBlockAddBtn" style="cursor:pointer;">Add Image</button>';
    }
    return '<img src="' + cellvalue + '" class="clitMulBlockThumb" style="width:40px;height:30px;cursor:pointer;" title="Double-click to change" />';
}
function ClitMultipleGrdbtnToolpop_onClick(result) {
    var rowid = result.rowId;
    // TODO: replace with your real tools-selection popup call, e.g.
    // toolsNameCodePopUp(rowid, 'ClitMultipleGrd');
    console.log("Tools Info button clicked for row " + rowid);
}


/* function frmMultipleClitStd_beforeSubmit() {
    var cellId  = jQuery("#frmMultipleClitStd input[id='cell']").val();
    var flId    = jQuery("#frmMultipleClitStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultipleClitStd input[id='section']").val();

    if (cellId == null || cellId == '') {
        alert("Please select Functional Location (JH Cell level)");
        return false;
    }

    var gridval = getGridSelectArray('ClitMultipleGrd');
    if (gridval != "") {
        if (validateClitMandatory(gridval))
            return 'clitStdDetails=' + encodeURIComponent(gridval)
                 + '&flId=' + flId
                 + '&sectionId=' + sectionId;
    }
    return false;
} */
function frmMultipleClitStd_beforeSubmit() {
	 console.log("beforeSubmit CALLED");
	 console.log("beforeSubmit CALLED");
	    console.log("#factory value AT SUBMIT TIME:", jQuery("#frmMultipleClitStd input[id='factory']").val());
	    console.log("Number of input[id=factory] elements found:", jQuery("#frmMultipleClitStd input[id='factory']").length);
	    var mode = jQuery("#hdnClitMode").val();
	    if (mode == "view") {
	        alert("Cannot save in view mode");
	        return false;
	    }
	    var cellId    = jQuery("#frmMultipleClitStd input[id='cell']").val();
    var flId      = jQuery("#frmMultipleClitStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultipleClitStd input[id='section']").val();
    var machId    = jQuery("#frmMultipleClitStd input[id='machine']").val();
    
   
    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return false;
    }

    var gridval = getClitStdDetailsCorrected();
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return false;
    }

    if (!validateClitMandatory(gridval)) return false; 
    
    jQuery("#hiddenUrl").val("MultipleClitStd_save.baljhclit");

    return 'clitStdDetails=' + encodeURIComponent(gridval)
    + '&flId='      + encodeURIComponent(flId)
    + '&sectionId=' + encodeURIComponent(sectionId)
    + '&machId='    + encodeURIComponent(machId)
    + '&cmbClisFactoryid=' + encodeURIComponent(jQuery("#frmMultipleClitStd input[id='factory']").val())
    + '&clitToolImage=' + encodeURIComponent(jQuery("#imgClitToolusedFilename").val());

}


/* function frmMultipleClitStd_successsCallback(result) {
    var url = jQuery("#hiddenUrl").val();
    jQuery("#ClitMultipleGrd").trigger("reloadGrid");
  //   viewClitGrid(url, "q=2"); 
  viewClitGrid(machId, flid);

    if (result.openfilemgr == true) {
        fileManagerPopUp(result.clisKeyid, "CLI", "", "", "", "");
    }
    
    
} */
// function clitSave_successCallBack(result) {
//     if (result.tpmException) {
//         alert(result.tpmException);
//         return;
//     }

//     var machId = "${requestScope.machId}";
//     var flid   = "${requestScope.flid}";

//     if (!machId || machId.trim() == "" || machId == "null") {
//         machId = jQuery("#hdnMachId").val();
//     }
//     if (!flid || flid.trim() == "" || flid == "null") {
//         flid = jQuery("#hdnFlid").val();
//     }

//     alert("Data Saved Successfully");
    
//     jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
//     jQuery('#imgClitToolusedFilename').val('');

//     viewClitGrid(machId, flid);

//     setTimeout(function () {
//         jQuery("#cmbMulClisMachineid").combobox('setValue', machId);
//         jQuery("#cmbMulClisMachineid").combobox('disable');
//     }, 2000);
// }
function clitSave_successCallBack(result) {
    if (result.tpmException) {
        alert(result.tpmException);
        return;
    }

    alert("Data Saved Successfully");

    jQuery('#imgClitToolused').attr('src', 'images/tools.jpg');
    jQuery('#imgClitToolusedFilename').val('');
    jQuery('#ClitMultipleGrd').trigger("reloadGrid");

    if (result.savedRows) {
        for (var i = 0; i < result.savedRows.length; i++) {
            var r = result.savedRows[i];
            if (!r.rowid) continue;

            if (r.hdnClisKeyid) {
                jQuery("#ClitMultipleGrd").jqGrid('setCell', r.rowid, "hdnClisKeyid", r.hdnClisKeyid);
            }
            if (r.hdnClisBlockDiagImg) {
            	var imgSrc = "tmp/images/" + r.hdnClisBlockDiagImg;
            	jQuery("#ClitMultipleGrd").jqGrid('setCell', r.rowid, "hdnClisBlockDiagImg", r.hdnClisBlockDiagImg);
            	jQuery("#ClitMultipleGrd").jqGrid('setCell', r.rowid, "imgClitBlockdiagram", imgSrc);
            	jQuery("#ClitMultipleGrd").find("tr[id='" + r.rowid + "']").data('clitBlockDiagImg', r.hdnClisBlockDiagImg);
            }
        }
    }
   
}

function clitSave_errorCallBack(result) {
    alert("Error saving data. Please try again.");
}

function popup_OnSaveForm() {
	console.log("=== popup_OnSaveForm CALLED ===");
    console.log("#factory value at this point:", jQuery("#factory").val());
    console.log("Number of #factory elements found:", jQuery("#factory").length);
    var mode = jQuery("#hdnClitMode").val();
    if (mode == "view") {
        alert("Cannot save in view mode");
        return false;
    }
	
	var cellId    = jQuery("#frmMultipleClitStd input[id='cell']").val();
    var flId      = jQuery("#frmMultipleClitStd input[id='flid']").val();
    var sectionId = jQuery("#frmMultipleClitStd input[id='section']").val();
    var machId    = jQuery("#frmMultipleClitStd input[id='machine']").val();
    var elementId = jQuery("#frmMultipleClitStd input[id='elementId']").val();

    if (cellId == null || cellId.trim() == '') {
        alert("Please select Functional Location (JH Cell level)");
        return;
    }

    var gridval = getClitStdDetailsCorrected();
    if (gridval == "" || gridval == "[]") {
        alert("Please add at least one row.");
        return;
    }

    if (!validateClitMandatory(gridval)) return;

    var params = "clitStdDetails=" + encodeURIComponent(gridval)
               + "&flId="          + encodeURIComponent(flId)
               + "&sectionId="     + encodeURIComponent(sectionId)
               + "&machId="        + encodeURIComponent(machId)
               + "&elementId="     + encodeURIComponent(elementId)
               + '&cmbClisFactoryid=' + encodeURIComponent(jQuery("#frmMultipleClitStd input[id='factory']").val())
               + "&cmbClisCellid="     + encodeURIComponent(jQuery("#cell").val())
               + "&cmbClisSectionid="  + encodeURIComponent(jQuery("#section").val())
               + "&cmbClisElementid="  + encodeURIComponent(elementId)
               + "&clitToolImage="     + encodeURIComponent(jQuery("#imgClitToolusedFilename").val());


    processAjaxCalls(
        "MultipleClitStd_save.baljhclit",
        params,
        'clitSave_successCallBack',
        'clitSave_errorCallBack'
    );
}

</script>


<form id="frmMultipleClitStd">

<div id="wrapperClitMul">

 
    <table style="width:1100px;">
        <tr style="height:50px; position:relative;">
            <td colspan="3" valign="top" style="left:80px; top:25px;">

                <%-- <div id="frmMultipleClitStdFuntKeyIds">

                   
                    <div style="float:left; padding-right:20px;">
                        <input type="hidden" id="factory" name="cmbClisFactoryid"
                               value="${requestScope.cliTlStandardFormBean.factory}"/>
                        <input type="hidden" id="section" name="cmbClisSectionid"
                               value="${requestScope.cliTlStandardFormBean.sectionId}"/>
                        <input type="hidden" id="cell"    name="cmbClisCellid"
                               value="${requestScope.cliTlStandardFormBean.cellId}"/>
                        <input type="hidden" id="machine" name="cmbClisMachineid1"
                               value="${requestScope.cliTlStandardFormBean.machineId}"/>
                        <input type="hidden" id="flid"    name="cmbClisFlid"
                               value="${requestScope.cliTlStandardFormBean.flid}"/>
                    </div>

                    
                    <div id="clitMulFunLocation" style="width:50%;"></div>
                    <table><tr>
                        <td><span id="err_clitMulFunLocation" class="tpm-errormsg"></span></td>
                    </tr></table>
                    
                    
						<div style="margin-top:8px;">
						    <label>Equipment</label>
						</div>
						<div class="easyui-paddingbfpx">
						    <input id="cmbMulClisMachineid"
						           name="cmbMulClisMachineid"
						           class="easyui-combobox"
						           style="width:350px;"
						           value=""/>
						</div>
						<div style="height:5px;"></div>

                    
                    <div style="margin-left:0px; margin-top:10px;">
                        <input type="button" class="easyui-button"
                               value="Add" id="btnClitAdd" style="height:25px;"/>
                    </div>
                    <div style="margin-left:55px; margin-top:-25px;">
                        <input type="button" class="easyui-button"
                               value="Delete" id="btnClitDelete" style="height:23px;"/>
                    </div>

                </div> --%>
                <div id="frmMultipleClitStdFuntKeyIds" style="width:100%; position:relative; min-height:160px;">

    <div style="float:left; padding-right:20px;">
        <input type="hidden" id="factory" name="cmbClisFactoryid"
               value="${requestScope.cliTlStandardFormBean.factory}"/>
        <input type="hidden" id="section" name="cmbClisSectionid"
               value="${requestScope.cliTlStandardFormBean.sectionId}"/>
        <input type="hidden" id="cell"    name="cmbClisCellid"
               value="${requestScope.cliTlStandardFormBean.cellId}"/>
        <input type="hidden" id="machine" name="cmbClisMachineid1"
               value="${requestScope.cliTlStandardFormBean.machineId}"/>
        <input type="hidden" id="flid"    name="cmbClisFlid"
               value="${requestScope.cliTlStandardFormBean.flid}"/>
    </div>


     <div id="clitMulFunLocation" style="width:84.3%; width:82%\9;"></div>

    
  <div id="clitToolImgPanel" style="text-align:center;">
    <img src="images/tools.jpg" id="imgClitToolused" name="imgClitToolused"
         width="100px" height="100px" style="width:130px; height:100px;"/>
    <div style="padding-top:6px;">
        <input type="button" class="easyui-button" id="dlgClitImg" name="dlgClitImg"
               value="+" style="width:29px;height:20px;"/>
        <input type="button" class="easyui-button" id="btnClitImgClear" name="btnClitImgClear"
               value="-" style="width:29px;height:20px;"/>
    </div>
</div>

    <div style="clear:both; height:0px;"></div>

    <table><tr>
        <td><span id="err_clitMulFunLocation" class="tpm-errormsg"></span></td>
    </tr></table>

    <div style="margin-top:8px;">
        <label>Equipment</label>
    </div>
    <div class="easyui-paddingbfpx">
        <input id="cmbMulClisMachineid"
               name="cmbMulClisMachineid"
               class="easyui-combobox"
               style="width:350px;"
               value=""/>
    </div>
    <div style="height:5px;"></div>

    <div style="margin-left:0px; margin-top:10px;">
        <input type="button" class="easyui-button" value="Add" id="btnClitAdd" style="height:25px;"/>
    </div>
    <div style="margin-left:55px; margin-top:-25px;">
        <input type="button" class="easyui-button" value="Delete" id="btnClitDelete" style="height:23px;"/>
    </div>

</div>

            </td>
        </tr>
    </table>

    
    <div style="margin-top:0px;  margin-right:160px;">
    <table id="ClitMultipleGrd"></table>
    <div id="clitMulPager"></div>
      </div>

</div>


<%-- <input type="hidden" id="hdnClitMode"    name="hdnClitMode"    value="${requestScope.mode}"/> --%>
<%-- <input type="hidden" id="hdnClitFrmType" name="hdnClitFrmType" value="${requestScope.frmType}"/> --%>
<%-- <input type="hidden" id="hdnClitFlid"    name="hdnClitFlid"    value="${requestScope.flid}"/> --%>
<%-- <input type="hidden" id="hdnClitKeyId"   name="hdnClitKeyId"   value="${requestScope.keyId}"/> --%>
<%-- <input type="hidden" id="hdnCurrentDate" name="hdnCurrentDate" value="${requestScope.currentDate}"/> --%>
<!-- <input type="hidden" id="hdnsavedClitKeyId" name="hdnsavedClitKeyId" value=""/> -->
<input type="hidden" id="elementId" name="cmbClisElementid" value="${requestScope.cliTlStandardFormBean.elementId}"/>

<input type="hidden" id="imgClitToolusedFilename" name="imgClitToolusedFilename"
       value="${requestScope.cliTlStandardFormBean.toolImage}" />

<input type="hidden" id="hdnMachId" name="cmbClisMachineid1"
       value="${requestScope.machId}"/>
<input type="hidden" id="hdnFlid"    name="cmbClisFlid"
       value="${requestScope.flid}"/>
      <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
      <input type="hidden" id="hdnClitMode" name="hdnClitMode" value="${requestScope.mode}"/>

</form>
