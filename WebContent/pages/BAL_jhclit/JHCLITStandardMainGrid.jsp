<script type="text/javascript">
 
 var clitEqpImageSlots = [null, null, null];
 var lastClitEqpImgVal = ["", "", ""];
 var clitIsCreationPage = false;
 
 var clitEqpImagesFromServer = [
	 <%
	 java.util.List<?> clitEqpImagesList = (java.util.List<?>) request.getAttribute("clitEqpImages");
	 String clitImgPathPrefix = "tmp/images/";
// 	 String clitImgPathPrefix = (String) request.getAttribute("imagePath");
	 if (clitEqpImagesList != null) {
	     int clitEqpImgCount = clitEqpImagesList.size();
	     boolean clitEqpFirstWritten = false;
	     for (int i = 0; i < clitEqpImgCount; i++) {
	         Object imgObj = clitEqpImagesList.get(i);
	         try {
	             String docType = (String) imgObj.getClass().getMethod("getImflRefdoctype").invoke(imgObj);
	             String fileName = (String) imgObj.getClass().getMethod("getImflFilename").invoke(imgObj);
	             int panelNo = Integer.parseInt(docType.substring(2));
	             if (clitEqpFirstWritten) {
	     %>,<%
	             }
	             clitEqpFirstWritten = true;
	     %>
	     { panelNo: <%= panelNo %>, imgPath: "<%= clitImgPathPrefix %><%= fileName %>?t=<%= System.currentTimeMillis() %>" }
	     <%
	         } catch (Exception e) {
	             // skip this entry if reflection fails (missing getter, bad docType, etc.)
	         }
	     }
	 }
	 %>
	 ];
 
 function clitLoadEqpImagesFromServer() {
	    clitResetEqpImagePanels();
	    for (var i = 0; i < clitEqpImagesFromServer.length; i++) {
	        var img = clitEqpImagesFromServer[i];
	        if (img.panelNo >= 1 && img.panelNo <= 3 && img.imgPath) {
	            applyClitEqpImageToPanel(img.panelNo, img.imgPath);
	            lastClitEqpImgVal[img.panelNo - 1] = img.imgPath;
	        }
	    }
	}


	jQuery(document).ready(function(){
	
		//alert("JHCLIT");
		jQuery('#frmJhClitStd .easyui-combobox').css('text-transform', 'uppercase');
			
		initialiseForm('frmJhClitStd');
		jQuery('#submitForm').val('frmJhClitStd'); 
		var url = jQuery('#hiddenUrl').val();
		var fromPage = jQuery('#hdnFromPage').val();
		clitSetEqpImgBtnState(clitIsViewMode());
		
		
		
		
		var isClitCreationPage = (url == "jhClit_input.baljhclit" || url.substring(0,19) == "jhClit_input.baljhclit");
		clitIsCreationPage = isClitCreationPage;
		
		if(url == 'jhClit_input.baljhclit')
			jQuery('#hdnFrmName').val("CLTI Standard Creation");
		
		else if(url == 'jhClitModification_input.baljhclit')
			 jQuery('#hdnFrmName').val("CLTI Standard Modification");
		else if(url == 'jhClitview_input.baljhclit')
			jQuery('#hdnFrmName').val("CLTI Standard View");
		//hari added
		else if(url== 'jhClitview_input.baljhclit'){
			jQuery('#hdnFrmName').val("CLTI Standard View");
	    jQuery("#multipleEntryDiv").hide();
		}
		jQuery('#hdnUrl').val(url);
		jQuery("#newstndard").css('display','none');
		jQuery('#addInfo').css('display','none');
		//jQuery('#filemgr').css('display','none');
		jQuery('#addMachine').css('display','none');
// 		jQuery('#addImageDiv').css('display','none');
		jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');

			
				fillComboBox("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter");
				
				var machineHirerachyId = null;
			
				var clismchId = getFieldValue('cmbClisMachineid');//jQuery("#cmbClisMachineid").combobox("getValue");//
				//alert('clismchId :'+clismchId );
				
				
// 				var mach = jQuery("#hdnMachId").val();
// 				if(mach!=null && mach!='' && mach!=' '){
// 					setFieldValue('cmbClisMachineid',mach);
					
// 					loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+mach);
				
// 				}
				
				//20jul1
				var mach = jQuery("#hdnMachId").val();
				if(mach!=null && mach!='' && mach!=' '){
					setFieldValue('cmbClisMachineid',mach);
					
					loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+mach);
					
					jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
					clitLoadEqpImagesFromServer();
// 					jQuery('#addImageDiv').css('display','flex');
// 					clitLoadEqpImagesFromServer();
// 					clitResetEqpImagePanels();
// 					clitFetchEqpImages(mach);
				}
				 
				 
				
// 				if(clismchId != null && clismchId != "")
// 				{
// 					jQuery('#hien').val(clismchId);
					
// 					//viewbtncall(clismchId);
// 					loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+clismchId);
// 					readOnlyFields('cmbClisMachineid');
// 					var frmName = jQuery('#hdnFrmName').val();
// 					navigateToNextForm("jhClit_mcharea.baljhclit?q=2&loadContentDivId=jhclitgrid2&preLoadContentDivId=preloadDIVid2&mchId="+clismchId+"&isHidePrevForm=false",frmName);
// 					//LoadForm("jhclitgrid1","preloadDIVid2","jhClit_mcharea.baljhclit","dispErr","","jhclitmcharea_errorCallBack");
// 					popFormNavigation();
// 				}
				if(clismchId != null && clismchId != "")
				{
					jQuery('#hien').val(clismchId);
					
					//viewbtncall(clismchId);
					loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+clismchId);
					readOnlyFields('cmbClisMachineid');
					
					jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
					clitLoadEqpImagesFromServer();
					/* jQuery('#addImageDiv').css('display','flex');
					clitLoadEqpImagesFromServer(); */
// 					clitResetEqpImagePanels();
// 					clitFetchEqpImages(clismchId);
					
					var frmName = jQuery('#hdnFrmName').val();
					navigateToNextForm("jhClit_mcharea.baljhclit?q=2&loadContentDivId=jhclitgrid2&preLoadContentDivId=preloadDIVid2&mchId="+clismchId+"&isHidePrevForm=false",frmName);
					//LoadForm("jhclitgrid1","preloadDIVid2","jhClit_mcharea.baljhclit","dispErr","","jhclitmcharea_errorCallBack");
					popFormNavigation();
				}
				else{
					enableFields('cmbClisMachineid');
					
					if(fromPage=="pcs") {
						var pcsUrl = "jhClit_frm.baljhclit?q=2&stdId=CLI130000004&loadContentDivId=Loadjhclitfrm&preLoadContentDivId=preloadDIVid4";
						LoadForm("jhclitgrid1","preloadDIVid","jhClit_frm.baljhclit","dispErr","","jhclitcountgrid_errorCallBack");
					}
					else					
						LoadForm("jhclitgrid1","preloadDIVid","grid_jhclitcount.baljhclit","dispErr","","jhclitcountgrid_errorCallBack");
					/* for functionalLocation*/
					
					var factId = jQuery("#frmJhClitStd input[id='factory']").val();
					var sectionId = jQuery("#frmJhClitStd input[id='section']").val();
					var cellId = jQuery("#frmJhClitStd input[id='cell']").val();
					var machId = jQuery("#frmJhClitStd input[id='machine']").val();
					var flid = jQuery("#frmJhClitStd input[id='flid']").val();
					
					var currFlid = jQuery("#hdnJHFlid").val();
					if(currFlid != null || currFlid != "")
						flid = currFlid;
					
					var mach = jQuery("#hdnMachId").val();
					if(mach!=null && mach!='' && mach!=' ')
						machId = mach;
						
					var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
					//alert(dataStr);
					loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd",dataStr);
					
				/*---------*/
				}
				
				jQuery('#btnAddMachine').click(function(){

				multiSelectSavePop("addmachine_input.admch","","addmach","","tick,txtmcamname,txtmcamcode","true","","multiSelectSaveOk_Callback");
					
				});
		 	
		var chkMachineId = getFieldValue('cmbClisMachineid');
			//if(chkMachineId != " " && chkMachineId !="" && chkMachineId != null )
			//	{}
			//else
				//alert("Select Equipment");
			
			
					if(isClitCreationPage)
					{
					    jQuery("#multipleEntryDiv").show();
					}
					else
					{
						jQuery("#multipleEntryDiv").hide();
					    jQuery("#addImageDiv, #clitEqpImgPanelWrap").css('display','none');
// 						jQuery("#multipleEntryDiv").hide();
// 					    jQuery("#addImageDiv, #clitEqpImgPanelWrap").css('display','none');
// 					    jQuery("#multipleEntryDiv").hide();
// 					    jQuery("#addImageDiv").css('display','none');
					}
					
					

// jQuery("#btnMultipleEntry").click(function () {
//     var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
//     var flid     = jQuery("#frmJhClitStd input[id='flid']").val();

//     if (selMchId == null || selMchId.trim() == "") {
//         alert("Please select Equipment before opening Multiple Entry.");
//         return false;
//     }

    
//     navigateToNextForm(
//         "multipleEntry_input.baljhclit?q=2&machId=" + selMchId + "&flid=" + flid,
//         "Multiple CLTI Standard Entry"
//     );
// });;
jQuery("#btnMultipleEntry").click(function () {
    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
    var flid     = jQuery("#frmJhClitStd input[id='flid']").val();

    if (selMchId == null || selMchId.trim() == "") {
        alert("Please select Equipment before opening Multiple Entry.");
        return false;
    }
    
//divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname
    LoadPopUp(
        "multipleEntryDiv",
        "multipleEntry_input.baljhclit?q=2&machId=" + selMchId + "&flid=" + flid,
        true,
        "90%",
        "82%",
        "1%",
        "1%",
        "",
        "Multiple CLTI Standard Entry",
        "",
        true
    );
});

imageUpload(jQuery("#dlgClitEqpImg1"), 'ImageUpload.commonFilter?', 'dlgClitEqpImg1',
        "imgClitEqpPreview1", "imgClitEqpFilename1", "300", "200");
imageUpload(jQuery("#dlgClitEqpImg2"), 'ImageUpload.commonFilter?', 'dlgClitEqpImg2',
        "imgClitEqpPreview2", "imgClitEqpFilename2", "300", "200");
imageUpload(jQuery("#dlgClitEqpImg3"), 'ImageUpload.commonFilter?', 'dlgClitEqpImg3',
        "imgClitEqpPreview3", "imgClitEqpFilename3", "300", "200");

// jQuery("#btnAddClitEqpImage").click(function () {
//     var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
//     if (selMchId == null || selMchId.trim() == "") {
//         alert("Please select Equipment before adding an image.");
//         return false;
//     }

//     var emptySlot = clitEqpImageSlots.indexOf(null);
//     if (emptySlot === -1) {
//         alert("Maximum of 3 images allowed.");
//         return false;
//     }

//     var panelNum = emptySlot + 1;
//     jQuery("#clitEqpImgPanel" + panelNum).css('display', 'inline-block');
//     jQuery("#dlgClitEqpImg" + panelNum).click();
// });


// setInterval(function () {
//     var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
//     if (selMchId != null && selMchId.trim() != "" && jQuery("#newstndard").is(":visible")) {
//         jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
//     } else {
//         jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');
//     }

//     if (!jQuery("#addImageDiv").is(":visible")) return;

//     for (var p = 1; p <= 3; p++) {
//         var currentVal = jQuery("#imgClitEqpFilename" + p).val();
//         if (currentVal !== lastClitEqpImgVal[p - 1]) {
//             lastClitEqpImgVal[p - 1] = currentVal;
//             if (currentVal && currentVal.trim() != "") {
//                 applyClitEqpImageToPanel(p, currentVal);
//             }
//         }
//     }
// }, 500);
/* setInterval(function () {
    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
    if (selMchId != null && selMchId.trim() != "" && jQuery("#newstndard").is(":visible")) {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
    } else {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');
    }

    if (!jQuery("#addImageDiv").is(":visible")) return;

    for (var p = 1; p <= 3; p++) {
        var currentVal = jQuery("#imgClitEqpFilename" + p).val();
        if (currentVal !== lastClitEqpImgVal[p - 1]) {
            lastClitEqpImgVal[p - 1] = currentVal;
            if (currentVal && currentVal.trim() != "") {
                applyClitEqpImageToPanel(p, currentVal);
                clitEqpAutoSaveImage(p, currentVal);
            }
        }
    }
}, 500); */
//mano
setInterval(function () {
    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
    if (selMchId != null && selMchId.trim() != "" && jQuery("#newstndard").is(":visible")) {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
    } else {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');
    }

    clitSetEqpImgBtnState(clitIsViewMode()); // NEW

    if (!jQuery("#addImageDiv").is(":visible")) return;

    for (var p = 1; p <= 3; p++) {
        var currentVal = jQuery("#imgClitEqpFilename" + p).val();
        if (currentVal !== lastClitEqpImgVal[p - 1]) {
            lastClitEqpImgVal[p - 1] = currentVal;
            if (currentVal && currentVal.trim() != "") {
                applyClitEqpImageToPanel(p, currentVal);
                clitEqpAutoSaveImage(p, currentVal);
            }
        }
    }
}, 500);



// setInterval(function () {
//     if (!jQuery("#addImageDiv").is(":visible")) return;

//     for (var p = 1; p <= 3; p++) {
//         var currentVal = jQuery("#imgClitEqpFilename" + p).val();
//         if (currentVal !== lastClitEqpImgVal[p - 1]) {
//             lastClitEqpImgVal[p - 1] = currentVal;
//             if (currentVal && currentVal.trim() != "") {
//                 applyClitEqpImageToPanel(p, currentVal);
//             }
//         }
//     }
// }, 500);

jQuery("#btnClitEqpImgClear1").click(function () { clitEqpDeleteImage(1); });
jQuery("#btnClitEqpImgClear2").click(function () { clitEqpDeleteImage(2); });
jQuery("#btnClitEqpImgClear3").click(function () { clitEqpDeleteImage(3); });

// jQuery("#btnSaveClitEqpImage").click(function () {
//     var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
//     if (selMchId == null || selMchId.trim() == "") {
//         alert("Please select Equipment before saving images.");
//         return false;
//     }

//     var hasAnyImage = false;
//     var dataStr = "clisKeyid=" + encodeURIComponent(selMchId) + "&refDoc=" + encodeURIComponent("CLI");

//     for (var p = 1; p <= 3; p++) {
//         var imgVal = clitEqpImageSlots[p - 1];
//         dataStr += "&img" + p + "Path=" + encodeURIComponent(imgVal ? imgVal : "");
//     }

//     for (var p = 1; p <= 3; p++) {
//         if (clitEqpImageSlots[p - 1]) { hasAnyImage = true; break; }
//     }

//     if (!hasAnyImage) {
//         alert("Please add at least one image before saving.");
//         return false;
//     }

//     processAjaxCalls(
//         "saveClitEqpImage.baljhclit",
//         dataStr,
//         'clitEqpSaveImg_successCallBack',
//         'clitEqpSaveImg_errorCallBack'
//     );
// });

/* ---- double-click to enlarge equipment thumbnails ---- */
jQuery("#imgClitEqpPreview1").dblclick(function(){ clitEqpOpenImagePopup(1); });
jQuery("#imgClitEqpPreview2").dblclick(function(){ clitEqpOpenImagePopup(2); });
jQuery("#imgClitEqpPreview3").dblclick(function(){ clitEqpOpenImagePopup(3); });

jQuery("#btnClitEqpImgPopupClose").click(function(){
    jQuery("#clitEqpImgPopupOverlay").css('display','none');
});

jQuery("#clitEqpImgPopupOverlay").click(function(e){
    if (e.target.id === "clitEqpImgPopupOverlay") {
        jQuery("#clitEqpImgPopupOverlay").css('display','none');
    }
});


					
					
});
	
	function mulClit_errorCallBack() {}
	
	function clitEqpSaveImg_successCallBack(result) {
	    if (!result || result.success !== true) {
	        alert("Error saving images: " + (result && result.error ? result.error : "Unknown error"));
	        return;
	    }
	    alert("Images saved successfully.");
	}

	function clitEqpSaveImg_errorCallBack(result) {
	    alert("Error saving images. Please try again.");
	}

	
/* function clitRefreshAddImageVisibility(){
		
		jQuery('#hdnUrl').val('jhClit_input.baljhclit');
		jQuery('#hdnFrmName').val('CLTI Standard Creation');

		var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
		if (selMchId != null && selMchId.trim() != "" && jQuery("#newstndard").is(":visible")) {
			jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
		} else {
			jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');
		}
	} */
	//mano
	function clitRefreshAddImageVisibility(){

    jQuery('#hdnUrl').val('jhClit_input.baljhclit');
    jQuery('#hdnFrmName').val('CLTI Standard Creation');

    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
    if (selMchId != null && selMchId.trim() != "" && jQuery("#newstndard").is(":visible")) {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','flex');
    } else {
        jQuery('#addImageDiv, #clitEqpImgPanelWrap').css('display','none');
    }

    clitSetEqpImgBtnState(clitIsViewMode());
}
	
	
// 	function clitRefreshAddImageVisibility(){
		
// 		jQuery('#hdnUrl').val('jhClit_input.baljhclit');
// 		jQuery('#hdnFrmName').val('CLTI Standard Creation');
// 	}

	//21jul
// 	function applyClitEqpImageToPanel(panelNum, imgPath) {
// 	    var slotIndex = panelNum - 1;
// 	    clitEqpImageSlots[slotIndex] = imgPath;
// 	    jQuery("#imgClitEqpPreview" + panelNum).attr('src', imgPath);
// 	    jQuery("#clitEqpImgPanel" + panelNum).css('display', 'inline-block');
// 	}
		function applyClitEqpImageToPanel(panelNum, imgPath) {
		    var slotIndex = panelNum - 1;
		    var cleanPath = imgPath.split('?')[0];   
		    clitEqpImageSlots[slotIndex] = cleanPath;
		    jQuery("#imgClitEqpPreview" + panelNum).attr('src', imgPath);  
		    jQuery("#clitEqpImgPanel" + panelNum).css('display', 'inline-block');
		}
		
		
		function clitEqpAutoSaveImage(panelNum, imgPath) {
		    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
		    if (selMchId == null || selMchId.trim() == "") {
		        alert("Please select Equipment before adding an image.");
		        return;
		    }

		    var cleanPath = imgPath.split('?')[0];
		    var dataStr = "clisKeyid=" + encodeURIComponent(selMchId) + "&refDoc=" + encodeURIComponent("CLI");
		    for (var p = 1; p <= 3; p++) {
		        dataStr += "&img" + p + "Path=" + encodeURIComponent(p === panelNum ? cleanPath : "");
		    }

		    processAjaxCalls(
		        "saveClitEqpImage.baljhclit",
		        dataStr,
		        'clitEqpSaveImg_successCallBack',
		        'clitEqpSaveImg_errorCallBack'
		    );
		}
		

	function clitEqpOpenImagePopup(panelNum) {
	    var src = jQuery("#imgClitEqpPreview" + panelNum).attr('src');
	    if (!src || src.indexOf('tools.jpg') !== -1) return; // no real image loaded
	    jQuery("#clitEqpImgPopupImg").attr('src', src);
	    jQuery("#clitEqpImgPopupOverlay").css('display','block');
	}
		//mano
		function clitIsViewMode() {
    var url = jQuery('#hdnUrl').val();
    var frmName = (jQuery('#hdnFrmName').val() || '').toLowerCase();
    var modeVals = [
        jQuery('#mode').val(),
        jQuery('#modeHdn').val(),
        jQuery('#fmodeHdn').val()
    ];

    if (url == 'jhClitview_input.baljhclit') return true;
    if (frmName.indexOf('view') !== -1) return true;

    for (var i = 0; i < modeVals.length; i++) {
        if (modeVals[i] && modeVals[i].toString().toLowerCase() == 'view') return true;
    }
    return false;
}

function clitSetEqpImgBtnState(disable) {
    for (var p = 1; p <= 3; p++) {
        var addBtn   = jQuery('#dlgClitEqpImg' + p);
        var clearBtn = jQuery('#btnClitEqpImgClear' + p);

        [addBtn, clearBtn].forEach(function (btn) {
            if (btn.data('linkbutton')) {
                btn.linkbutton(disable ? 'disable' : 'enable');
            } else {
                btn.prop('disabled', disable)
                   .css({
                       'opacity': disable ? 0.5 : 1,
                       'cursor': disable ? 'not-allowed' : 'pointer'
                   });
            }
        });
    }
}
//21jul
// 	function clitEqpDeleteImage(panelNum) {
// 	    if (!confirm('Remove this image?')) return;
// 	    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
// 	    processAjaxCalls(
// 	        "deleteClitBlockDiagImg.baljhclit",
// 	        "clisKeyid=" + encodeURIComponent(selMchId)
// 	            + "&imgType=" + encodeURIComponent("EQP")
// 	            + "&refDoc="  + encodeURIComponent("CLI")
// 	            + "&panelNo=" + panelNum,
// 	        function (result) { clitEqpDeleteImg_successCallBack(result, panelNum); },
// 	        'clitEqpDeleteImg_errorCallBack'
// 	    );
// 	}
// 			function clitEqpDeleteImage(panelNum) {
// 			    if (!confirm('Remove this image?')) return;
// 			    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
// 			    var docType = "EQ" + panelNum;  
// 			    processAjaxCalls(
// 			        "deleteClitBlockDiagImg.baljhclit",
// 			        "clisKeyid=" + encodeURIComponent(selMchId)
// 			            + "&imgType=" + encodeURIComponent("EQP")
// 			            + "&refDoc="  + encodeURIComponent(docType)
// 			            + "&panelNo=" + panelNum,
// 			        function (result) { clitEqpDeleteImg_successCallBack(result, panelNum); },
// 			        'clitEqpDeleteImg_errorCallBack'
// 			    );
// 			}
//21jul
// 	function clitEqpDeleteImg_successCallBack(result, panelNum) {
// 	    if (!result || result.success !== true) {
// 	        alert("Error deleting image: " + (result && result.error ? result.error : "Unknown error"));
// 	        return;
// 	    }
// 	    var slotIndex = panelNum - 1;
// 	    clitEqpImageSlots[slotIndex] = null;
// 	    lastClitEqpImgVal[slotIndex] = "";
// 	    jQuery("#imgClitEqpPreview" + panelNum).attr('src', 'images/tools.jpg');
// 	    jQuery("#imgClitEqpFilename" + panelNum).val('');
// 	    jQuery("#clitEqpImgPanel" + panelNum).css('display', 'none');
// 	}
var clitEqpDeletePanelNum = null;

function clitEqpDeleteImage(panelNum) {
    if (!confirm('Remove this image?')) return;
    var selMchId = jQuery("#cmbClisMachineid").combobox("getValue");
    var docType = "EQ" + panelNum;

    clitEqpDeletePanelNum = panelNum;

    processAjaxCalls(
        "deleteClitBlockDiagImg.baljhclit",
        "clisKeyid=" + encodeURIComponent(selMchId)
            + "&imgType=" + encodeURIComponent("EQP")
            + "&refDoc="  + encodeURIComponent(docType)
            + "&panelNo=" + panelNum,
        'clitEqpDeleteImg_successCallBack',
        'clitEqpDeleteImg_errorCallBack'
    );
}



// function clitEqpDeleteImg_successCallBack(result) {
//     if (!result || result.success !== true) {
//         alert("Error deleting image: " + (result && result.error ? result.error : "Unknown error"));
//         return;
//     }

//     var panelNum = clitEqpDeletePanelNum;
//     var slotIndex = panelNum - 1;
//     clitEqpImageSlots[slotIndex] = null;
//     lastClitEqpImgVal[slotIndex] = "";
//     jQuery("#imgClitEqpPreview" + panelNum).attr('src', 'images/tools.jpg');
//     jQuery("#imgClitEqpFilename" + panelNum).val('');
//     jQuery("#clitEqpImgPanel" + panelNum).css('display', 'none');

//     alert("Image deleted successfully.");
// }
function clitEqpDeleteImg_successCallBack(result) {
    if (!result || result.success !== true) {
        alert("Error deleting image: " + (result && result.error ? result.error : "Unknown error"));
        return;
    }

    var panelNum = clitEqpDeletePanelNum;
    var slotIndex = panelNum - 1;
    clitEqpImageSlots[slotIndex] = null;
    lastClitEqpImgVal[slotIndex] = "";
    jQuery("#imgClitEqpPreview" + panelNum).attr('src', 'images/tools.jpg');
    jQuery("#imgClitEqpFilename" + panelNum).val('');
    jQuery("#clitEqpImgPanel" + panelNum).css('display', 'inline-block');

    alert("Image deleted successfully.");
}




	function clitEqpDeleteImg_errorCallBack(result) {
	    alert("Error deleting image. Please try again.");
	}
	
	function clitResetEqpImagePanels() {
	    for (var p = 1; p <= 3; p++) {
	        clitEqpImageSlots[p - 1] = null;
	        lastClitEqpImgVal[p - 1] = "";
	        jQuery("#imgClitEqpPreview" + p).attr('src', 'images/tools.jpg');
	        jQuery("#imgClitEqpFilename" + p).val('');
	        jQuery("#clitEqpImgPanel" + p).css('display', 'inline-block');
	    }
	}


	function clitFetchEqpImages(mchId) {
	    if (mchId == null || mchId.trim() == "") return;

	    processAjaxCalls(
	        "getClitEqpImages.baljhclit",
	        "mchId=" + encodeURIComponent(mchId),
	        'clitFetchEqpImages_successCallBack',
	        'clitFetchEqpImages_errorCallBack'
	    );
	}
	
	function clitFetchEqpImages_successCallBack(result) {
	    if (!result || !result.images) return;
	    for (var i = 0; i < result.images.length; i++) {
	        var img = result.images[i];
	        if (img.panelNo >= 1 && img.panelNo <= 3 && img.imgPath) {
	            applyClitEqpImageToPanel(img.panelNo, img.imgPath);
	            lastClitEqpImgVal[img.panelNo - 1] = img.imgPath;
	        }
	    }
	    clitRefreshAddImageVisibility();
	}

// 	function clitFetchEqpImages_successCallBack(result) {
// 	    if (!result || !result.images) return;
// 	    for (var i = 0; i < result.images.length; i++) {
// 	        var img = result.images[i];
// 	        if (img.panelNo >= 1 && img.panelNo <= 3 && img.imgPath) {
// 	            applyClitEqpImageToPanel(img.panelNo, img.imgPath);
// 	            lastClitEqpImgVal[img.panelNo - 1] = img.imgPath;
// 	        }
// 	    }
// 	}

	function clitFetchEqpImages_errorCallBack(result) {
	   
	}
	
	
	jQuery("#cmbClisMachineid").combobox({onRequest:function(opts){		
		var cellId = jQuery("#frmJhClitStd input[id='machine']").val();		
		return "&combokey="+cellId+"&machId="+cellId; 		
		}
	});
	
	function dispErr(){}
	function jhclitcountgrid_errorCallBack(){}

	function  frmJhClitStdcmbClisFactoryid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisSectionid","sectionCombo.commonFilter" );
	}
	function  frmJhClitStdcmbClisSectionid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter");
	}
	function  frmJhClitStdcmbClisCellid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter");
	}
	/* function  frmJhClitStdcmbClisMachineid_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisFactoryid","factroyCombo.commonFilter" );		
	} */
	//mano
	function  frmJhClitStdcmbClisMachineid_onLoadSuccess()
{
    var mach = jQuery("#hdnMachId").val();
    var selmchId = jQuery("#hien").val();
    var combokeyVal = mach || selmchId;

    if (combokeyVal != null && combokeyVal.trim() != "" && combokeyVal.trim() != " ")
    {
        jQuery("#cmbClisMachineid").combobox('setValue', combokeyVal);
    }
}
	
	
	function setLoadFunctionLocation(mchId) { 
		
	}
	
	function  frmJhClitStdcmbClisFactoryid_onSelect(record)
	{
	
		jQuery("#cmbClisSectionid").combobox('clear');
		jQuery("#cmbClisCellid").combobox('clear');
// 		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisSectionid","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter?factId="+record.id  );
// 		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?factId="+ record.id );
	}
	   function  frmJhClitStdcmbClisSectionid_onSelect(record){
	    	  
	    	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbClisFactoryid");
		 }

	    function  frmJhClitStdcmbClisCellid_onSelect(record){
	    		
	    	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbClisSectionid","cmbClisFactoryid");
		 }
	/*function  ffrmJhClitStdcmbClisSectionid_onSelect(record)
	{
		jQuery("#cmbClisCellid").combobox('clear');
		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter?sectId="+record.id  );
		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?sectId="+ record.id );
	}
	
	function  frmJhClitStdcmbClisCellid_onSelect(record)
	{
		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?cellId="+ record.id );
	}*/
	
	//20jul3
// 	function  frmJhClitStdcmbClisMachineid_onSelect(record)
// 	{
// 		//filcmbbox(record.id);
// 		loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+record.id);
// 		clitRefreshAddImageVisibility();
// 	}
	
	
// 	function  frmJhClitStdcmbClisMachineid_onSelect(record)
// 	{
// 		//filcmbbox(record.id);
// 		loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+record.id);
// 		clitRefreshAddImageVisibility();
// 		clitResetEqpImagePanels();
// 		clitFetchEqpImages(record.id);
// 	}
	
	function  frmJhClitStdcmbClisMachineid_onSelect(record)
	{
		//filcmbbox(record.id);
		loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+record.id);
		clitRefreshAddImageVisibility();
		clitResetEqpImagePanels();
		clitFetchEqpImages(record.id);
		setTimeout(clitRefreshAddImageVisibility, 800);
	}
	
 	function filcmbbox(machineId)
 	{
		processAjaxCalls('jhClit_fillcombo.baljhclit','q=2&eqpId='+machineId,'frmSuccess','frmError');
	 
	}
//     function frmJhClitStd_FuntLocHierarchy_SuccessCallBack(keyIds)
// 	{
    	
// 		setFieldValue('cmbCliscellid',keyIds.cellId);
// 		if (getFieldValue("cmbClisMachineid").trim().length==0)
// 			setFieldValue('cmbClisMachineid',keyIds.machId);
		
// 		var mach = jQuery("#hdnMachId").val();
// 		if(mach!=null && mach!='' && mach!=' ')
// 			setFieldValue('cmbClisMachineid',mach);
		
// 		//jQuery('#clisFactID').val(keyIds.factId);
// 		//alert('reload'+keyIds.machId);
// 		//jQuery("#cmbAbnmEquipmentid").combobox("disable");
// 		//var cellId = jQuery("#cell").val();
		
// 		if(keyIds.machId!=null || keyIds.machId.trim() != 'undefined' || keyIds.machId.trim() != undefined ){
			
// 			reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?combokey="+ keyIds.machId );	
// 		}
// 		else
// 			reloadMachine("frmJhClitStd",'cmbClisMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
// 		reloadCombo("frmJhClitStd","cmbClisShiftid","combo_clitShift.baljhclit?factId ="+ keyIds.factId );
// 		//reloadCombo("frmJhClitStd","cmbCostcenter","costCenter.commonFilter?cellId="+keyIds.cellId  );
// 		//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?compId="+keyIds.compId +'&factId='+keyIds.factId+'&sectId='+keyIds.sectId+"&cellId="+keyIds.cellId);
		
		
// 	}
function frmJhClitStd_FuntLocHierarchy_SuccessCallBack(keyIds)
{
    setFieldValue('cmbCliscellid', keyIds.cellId);

    if (getFieldValue("cmbClisMachineid").trim().length == 0)
        setFieldValue('cmbClisMachineid', keyIds.machId);

    var mach = jQuery("#hdnMachId").val();
    if (mach != null && mach != '' && mach != ' ')
        setFieldValue('cmbClisMachineid', mach);

    if (keyIds.machId != null &&
        keyIds.machId != '' &&
        keyIds.machId.trim() != 'undefined')
    {
        reloadCombo(
            "frmJhClitStd",
            "cmbClisMachineid",
            "machineCombo.commonFilter?combokey=" + keyIds.machId
        );
        clitRefreshAddImageVisibility();
    }
    else
    {
        reloadMachine(
            "frmJhClitStd",
            'cmbClisMachineid',
            keyIds.cellId,
            keyIds.sectId,
            keyIds.sbuId,   // Changed from factId
            keyIds.locnId,
            keyIds.compId
        );
    }

    reloadCombo(
        "frmJhClitStd",
        "cmbClisShiftid",
        "combo_clitShift.baljhclit?sbuId=" + keyIds.sbuId  // Changed from factId
    );
}

    //for click view button
   /*  function viewbtncall()
    {	
    	
    	jQuery('#jhclitgrid1').empty();
    	jQuery('#jhclitgrid1').html("");
		
		//alert(mchId);
		var selmchId = jQuery("#cmbClisMachineid").combobox("getValue");
		var machineID =  jQuery('#hien').val();
		//alert("j:  "+machineID);
		var flid =jQuery("#frmJhClitStd input[id='flid']").val();
		//alert(flid);
		
		/*if(machineID == "" || machineID == null){
			 machineID = bdmMachineId;	
			}*/
		 /* if( selmchId  == null ||selmchId == "") 
        	{
			 jQuery('#hien').val(" ");
        	}
		 
			
    		jQuery("#jhclitgrid1").load('jhClit_clickview.baljhclit',"?&flid="+flid+"&machineID="+selmchId, function(response, status, xhr) {
			   if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    alert(msg);
			   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			  }
			   if (status == "success") {
					//alert("sucess   view");
				   }
			});	
    } */ 
    //mano
    function viewbtncall()
{	
    jQuery('#jhclitgrid1').empty();
    jQuery('#jhclitgrid1').html("");

    var selmchId = jQuery("#cmbClisMachineid").combobox("getValue");
    var flid = jQuery("#frmJhClitStd input[id='flid']").val();

    if( selmchId == null || selmchId == "" ) 
    {
        jQuery('#hien').val(" ");
    }
    else
    {
        jQuery('#hien').val(selmchId);   // <-- FIX: keep #hien in sync with the actual selection
    }

    jQuery("#jhclitgrid1").load('jhClit_clickview.baljhclit',"?&flid="+flid+"&machineID="+selmchId, function(response, status, xhr) {
       if (status == "error") {
        var msg = "Sorry but there was an error: ";
        alert(msg);
       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
      }
       if (status == "success") {
            //alert("sucess   view");
           }
    });	
}
	function frmSuccess(result)
	{
	 	//alert("dfd"+Object.keys(result));
		//jQuery("#cmbClisFactoryid").combobox('setValue',result.machineHirerachy.factory);
		//jQuery("#cmbClisSectionid").combobox('setValue',result.machineHirerachy.section);
		//jQuery("#cmbClisCellid").combobox('setValue',result.machineHirerachy.cell);
	}
	function frmError(result)
	{
		alert('Err');
	}
	function back_div()
	{
		var chkfp = jQuery('#fstGrd').val();
		  if(chkfp != "1a")
		  {
			jQuery('#hdnUrl').val('jhClit_input.baljhclit');
			jQuery('#hdnFrmName').val('CLTI Standard Creation');
			navigateToPrevForm();
		  }
		  else
			  alert('No Previous Pages');
	}

/***/
 jQuery("#cmbClisFactoryid").keydown(function() {
	
     var newVal = $("#cmbClisFactoryid").val();
     var quantityRegexp = /^(0|[1-9]+[0-9]*)$/;

     // success
     if (quantityRegexp.test(newVal)) {
         oldVal = newVal;
         // hide error
         jQuery("#cmbClisFactoryid_error").hide();
     }

     // else failure
     else {
         jQuery("#cmbClisFactoryid").val(oldVal);
         // display error message
         jQuery("#cmbClisFactoryid_error").show();
     }
 });

 

/***/
</script>


<form name="frmJhClitStd" id="frmJhClitStd" action="" method="post">
<div id="wrapper" >


	<div id="JHCLIT" class="divbrdr"  style="margin-top:-10px;margin-top:-4px\9;margin-left:-3%;width:80%;">

	<table width="100%"  >  
			 <tr>
			 	<td colspan='3'>
			 	<div  id="frmJhClitStdFuntKeyIds">
				<input type="hidden" id="factory" name="cmbClisFactoryid" value="${requestScope.cliTlStandards.clisFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbClisSectionid" value="${requestScope.cliTlStandards.clisSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbClisCellid" value="${requestScope.cliTlStandards.clisCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbCliMachineid" value="${requestScope.cliTlStandards.clisMachineid}"  ></input>
				<input type="hidden" id="flid" name="cmbClisFlid" value="${requestScope.cliTlStandards.clisFlid}" ></input>
				<input type="hidden" id="elementId" name="cmbClisElementid" value="${requestScope.cliTlStandards.clisElementid}" ></input>
				
				</div>
			 			<div style="display:flex;align-items:flex-start;flex-wrap:nowrap;width:100%;">
			 				<div id="clisfunLocation" style="margin-left: 60px;flex:1 1 auto;min-width:0;overflow:visible;white-space:normal;word-break:break-word;"></div>

			 				<div id="clitEqpImgPanelWrap" style="display:none;align-items:flex-end;gap:14px;flex-wrap:nowrap;flex-shrink:0;margin-left:40px; margin-top:-11px;">
			 					<span id="clitEqpImgPanel1" style="display:inline-block;text-align:center;flex-shrink:0;">
			 						<img id="imgClitEqpPreview1" name="imgClitEqpPreview1" src="images/tools.jpg" title="Double click to zoom in" style="width:54px;height:54px;border:1px solid #ccc;display:block;cursor:pointer;"/>
			 						<div style="margin-top:4px;">
			 							<input type="button" class="easyui-button" id="dlgClitEqpImg1" name="dlgClitEqpImg1" title="add&save" value="+" style="width:22px;height:18px;"/>
			 							<input type="button" class="easyui-button" id="btnClitEqpImgClear1" name="btnClitEqpImgClear1" title="delete" value="-" style="width:22px;height:18px;"/>
			 						</div>
			 					</span>

			 					<span id="clitEqpImgPanel2" style="display:inline-block;text-align:center;flex-shrink:0;">
			 						<img id="imgClitEqpPreview2" name="imgClitEqpPreview2" src="images/tools.jpg" title="Double click to zoom in" style="width:54px;height:54px;border:1px solid #ccc;display:block;cursor:pointer;"/>
			 						<div style="margin-top:4px;">
			 							<input type="button" class="easyui-button" id="dlgClitEqpImg2" name="dlgClitEqpImg2" title="add&save" value="+" style="width:22px;height:18px;"/>
			 							<input type="button" class="easyui-button" id="btnClitEqpImgClear2" name="btnClitEqpImgClear2" title="delete" value="-" style="width:22px;height:18px;"/>
			 						</div>
			 					</span>

			 					<span id="clitEqpImgPanel3" style="display:inline-block;text-align:center;flex-shrink:0;">
			 						<img id="imgClitEqpPreview3" name="imgClitEqpPreview3" src="images/tools.jpg" title="Double click to zoom in" style="width:54px;height:54px;border:1px solid #ccc;display:block;cursor:pointer;"/>
			 						<div style="margin-top:4px;">
			 							<input type="button" class="easyui-button" id="dlgClitEqpImg3" name="dlgClitEqpImg3" title="add&save" value="+" style="width:22px;height:18px;"/>
			 							<input type="button" class="easyui-button" id="btnClitEqpImgClear3" name="btnClitEqpImgClear3" title="delete" value="-" style="width:22px;height:18px;"/>
			 						</div>
			 					</span>
			 				</div>
			 			</div>
			 	</td>
			 </tr>
			 <tr>
			 	<td colspan="3">
			 		<div style="display:flex;align-items:center;flex-wrap:wrap;gap:30px;">
			 			<div style="margin-top:10px;flex-shrink:0;">
			 			<div style="margin-left: 60px;" ><label>Equipment</label></div>
		               	<div class="easyui-paddingbfpx" style="margin-left: 60px;width:270px;">
		               	<input id="cmbClisMachineid" name="cmbClisMachineid" class="easyui-combobox" style="width:255px;"  >
					</div>
					</div>

					<div style="display:flex;align-items:center;gap:10px;flex-wrap:wrap;margin-left:30px;">
						<div id="addMachine">
							<input type="button" class="easyui-button" id="btnAddMachine" value="ADD MACHINE AREA" style="width:145px;height:28px;line-height:28px;margin:0;"/>
						</div>
						<div id="addInfo">
							<input type="button" class="easyui-button" id="addInfo" value="ADDITIONAL INFORMATION" style="width:170px;height:28px;line-height:28px;margin:0;" />
						</div>
						<div id="filemgr">
	<!--						  <input class="easyui-button" id="btnClitFilManage" type="button" value="File Manager" style=" ">-->
						</div>
						<div id="newstndard" style="display:flex;align-items:center;gap:10px;">
							<input type="button" class="easyui-button" value="Multiple Entry" id="btnMultipleEntry" style="height:28px;line-height:28px;margin:0;"/>
							<!-- <input type="button" class="easyui-button" value="New Standard" id="newstd" style="height:28px;line-height:28px;margin:0;"/> -->
						</div>
						<input type="button" class="easyui-button" value="VIEW" id="vwebtn" onclick='viewbtncall();' style="height:28px;line-height:28px;margin:0;"/>
						<input type="button" class="easyui-button" value="BACK" id="bckbtn" onclick='back_div();' style="height:28px;line-height:28px;margin:0;"/>

						<div id="addImageDiv" style="display:none;align-items:center;gap:10px;flex-shrink:0;">
<!-- 							<input type="button" class="easyui-button" id="btnAddClitEqpImage" value="Add Image" style="width:100px;height:28px;line-height:28px;margin:0;"/> -->
<!-- 							<input type="button" class="easyui-button" id="btnSaveClitEqpImage" value="Save Image" style="width:100px;height:28px;line-height:28px;margin:0;"/> -->
						</div>
					</div>
					</div>

					<input type="hidden" id="imgClitEqpFilename1" name="imgClitEqpFilename1" value=""/>
					<input type="hidden" id="imgClitEqpFilename2" name="imgClitEqpFilename2" value=""/>
					<input type="hidden" id="imgClitEqpFilename3" name="imgClitEqpFilename3" value=""/>
			 	</td>
	</tr>
	
</table>
	<input type="hidden" id="hien" name="hien" class="easyui-text"  style="width:155px;" value=""  >
   	<input type="hidden" id="txtClisAssemblyid" name="txtClisAssemblyid" class="easyui-text" style="width:155px;" />
   	<input type="hidden" id="txtClisKeyid" name="txtClisKeyid" class="easyui-text" style="width:155px;" />
   	<input type="hidden" id="rcdid" class="easyui-text" style="width:155px;" />

	<!-- image enlarge popup -->
	<div id="clitEqpImgPopupOverlay" style="display:none;position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.7);z-index:99999;">
		<div style="position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);text-align:center;">
<!-- 			<img id="clitEqpImgPopupImg" src="" style="max-width:150vw;max-height:148vh;border:3px solid #fff;"/> -->
				<img id="clitEqpImgPopupImg" src="" style="height:40vh;width:auto;max-width:62vw;border:3px solid #fff;"/>
			<div style="margin-top:10px;">
				<input type="button" class="easyui-button" id="btnClitEqpImgPopupClose" value="Close" style="width:80px;"/>
			</div>
		</div>
	</div>
</div>
<div  style="margin-left:25px;margin-right:20px;width: 965px; "><hr></div>
<div style="padding-left:30px;">
<div id="firstGrid"   style="display:none">Double Click on the data row to view Equipment Area</div>
<div id="secondGrid"  style="display:none">Double Click on the Machine Area name to view the Activities</div>
<div id="thridGrid"   style="display:none;margin-right:50px; margin-left:-6px;">Double Click on the Activity to edit/view the standard</div>

</div>
<div id="preloadDIVid1"></div>
<div id="preloadDIVid2"></div>
<div id="preloadDIVid3"></div>
<div id="preloadDIVid4"></div>
<div id="Loadjhclitgrid1"></div>
<div>
<div id="jhclitgrid1" class="divbrdr" style="width:80%;float:left;margin-left:2%;"></div>
<div id="jhclitgrid2" class="divbrdr" style="width:80%;margin-left:-3%;"></div>
<div id="jhclitgrid3" class="divbrdr" style="width:80%;margin-left:2%;"></div>
<div id="Loadjhclitfrm" class="divbrdr" style="width:100%;margin-left:-3%;"></div>
</div>
<div id="multipleEntryDiv"></div> 

<input type="hidden" id ="hdnUrl"/>

<input type="hidden" id="mode" value="${ requestScope.generalMaintainanceBean.formMode }">
</div>

<table id="clitCntGrid" ></table>
<div id="pager"></div>

<div id="Loadjhclitgrid2">

</div>


</form>

<div id="selInactiveDate" class="divdelContainer " style="display:none;">
	<center>
	<div style="margin-top:15%;" style="">
	<label style="padding-right: 10px;">Inactivated Date </label>
	<input class="easyui-datebox easyui-text" id="dteClisInactivateddate" name="dteClisInactivateddate" style="width:150px;" ></input>
	<input type="button" class="easyui-button" id="btnOk" value="OK"/>
	</div>
</center>
</div>


<input type="hidden" id="modeHdn" name="modeHdn" value="${requestScope.formMode}"/>
<input type="hidden" id="fmodeHdn" name="fmodeHdn" value="${requestScope.fMode}"/>
<input type="hidden" id="bdmHdnM"  name="bdmHdnM" value="${requestScope.bdmmode}"/>
<input type="hidden" id="clisFactID"  name="clisFactID" value=""/>
<input type="hidden" id="hdnFrmName"  name="hdnFrmName" />
<input type="hidden" id="hdnFromPage"  name="hdnFromPage" value="${requestScope.fromPage}"/>
<input type="hidden" id="hdnMachId"  name="hdnMachId" value="${requestScope.machineID}"/>