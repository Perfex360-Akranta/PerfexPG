<script type="text/javascript">

jQuery.noConflict();

var clitEqpImagesFromServer = [
	<%
	java.util.List<?> clitEqpImagesList = (java.util.List<?>) request.getAttribute("clitEqpImages");
	String clitImgPathPrefix = "tmp/images/";
// 	String clitImgPathPrefix = (String) request.getAttribute("imagePath");
	if (clitEqpImagesList != null) {
	    boolean clitEqpFirstWritten = false;
	    for (int i = 0; i < clitEqpImagesList.size(); i++) {
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

jQuery(document).ready(function()
		{

		jQuery('#filemgr').	css('display','none');
		
			jQuery("#vwebtn").css('display','none');
			jQuery('#addMachine').css('display','none');
			jQuery('#firstGrid').css('display','none');
			jQuery('#secondGrid').css('display','none');
			jQuery("#thridGrid").css('display','block');
				
			
			var url =	jQuery('#hdnUrl').val();
			jQuery('#addInfo').css('display','none');
			
			var gridMchId = jQuery("#hdnMchId").val();
var isClitCreationPageGrid = (url == "jhClit_input.baljhclit" || url.substring(0,19)== "jhClit_input.baljhclit");
var frmNameNow = jQuery('#hdnFrmName').val();
var isCreationByTitle = (frmNameNow == "CLTI Standard Creation");


if((isClitCreationPageGrid || isCreationByTitle) && gridMchId != null && gridMchId != "" && gridMchId.trim() != "")
{
	jQuery("#newstndard").css('display','block');
	jQuery('#addImageDiv').css('display','block');

	jQuery('#hdnUrl').val('jhClit_input.baljhclit');
}
				
			var bdmMode = jQuery('#bdmHdnM').val();
			if(bdmMode != " " && bdmMode != 'undefined' && bdmMode !="" && bdmMode !="null"){
				
				jQuery("#newstndard").css('display','block');
			}
			jQuery('#cmbClisFactoryid').combobox("disable");
			jQuery('#cmbClisSectionid').combobox("disable");
			jQuery('#cmbClisCellid').combobox("disable");
			jQuery('#cmbClisMachineid').combobox("disable");
			
	var mchID = jQuery("#hdnMchId").val();

	
	if (mchID != null && mchID.trim() != "") {
	    clitLoadEqpImagesFromServer();
	}


		var filterData ="&mchID="+mchID ;
		 filterData +="&grid="+jQuery("#hdngrid").val();
		filterData +="&flid="+jQuery("#hdnflid").val();
		//processGridnew("jhClit_input.baljhclit?",filterData,"clistdGrid","pager","","dblclick");
		processGridnew("jhClit_input.baljhclit?",filterData,"clistdGrid","pager","","dblclick");
		
		
		
		
		 var pageMode = jQuery("#hdnPageMode").val();

		    if (pageMode == "view") {
		        // Disable the +/- buttons for all 3 image panels
		        jQuery(".imgZoomBtn, .clitImgIncBtn, .clitImgDecBtn")
		            .prop("disabled", true)
		            .css({ "pointer-events": "none", "opacity": "0.5", "cursor": "not-allowed" });
		    }
	});
function txtFormatterClitViewBtn(cellvalue, options, rowObject)
{
    return '<button type="button" class="clitViewBtn" onclick="viewMultipleEntry(\'' + options.rowId + '\')">View</button>';
}
function viewMultipleEntry(id)
{
    var mchId = jQuery("#hdnMchId").val();
    var flid  = jQuery("#hdnflid").val();

    LoadPopUp(
        "multipleEntryDiv",
        "multipleEntry_input.baljhclit?q=2&machId=" + mchId + "&flid=" + flid + "&mode=view",
        true,
        "90%",
        "85%",
        "3%",
        "5%",
        "",
        "View CLTI Standards",
        "",
        true
    );
}
		
/* function dblclick(id)
{
    var rowData = jQuery("#clistdGrid").jqGrid('getRowData', id);
    var stdId   = rowData.cliid;
    var mchId   = jQuery("#hdnMchId").val();
    var flid    = jQuery("#hdnflid").val();

    LoadPopUp(
        "multipleEntryDiv",
        "multipleEntry_input.baljhclit?q=2&machId=" + mchId + "&flid=" + flid + "&mode=modify",
        true,
        "90%",
        "85%",
        "3%",
        "5%",
        "",
        "Multiple CLTI Standard Entry",
        "",
        true
    );
}
 */
 function dblclick(id)
 {
     var rowData = jQuery("#clistdGrid").jqGrid('getRowData', id);
     var stdId   = rowData.cliid;
     var mchId   = jQuery("#hdnMchId").val();
     var flid    = jQuery("#hdnflid").val();

     var pageMode = jQuery("#hdnPageMode").val();
     var entryMode = (pageMode == "view") ? "view" : "modify";   // default modify

     LoadPopUp(
         "multipleEntryDiv",
         "multipleEntry_input.baljhclit?q=2&machId=" + mchId + "&flid=" + flid + "&mode=" + entryMode,
         true,
         "90%",
         "85%",
         "3%",
         "5%",
         "",
         (entryMode == "view") ? "View CLTI Standards" : "Multiple CLTI Standard Entry",
         "",
         true
     );
 }
</script>
<style>
#showEqpArea{
line-height: 5px;
width:43.3%;
margin-left:60px;
border-top:solid 1px #F3F6FB;
}

</style>
<form name="frmjhclitactivitygrid" id="frmjhclitactivitygrid" action="" method="post">
<div id="stdgrid" style="padding-top:0px;margin-left:0px;" >
<input type=hidden id="third" class="easyui-text" value="c"/>
<input type=hidden id="hdnMchId" name="hdnMchId" class="easyui-text" value="${requestScope.machineId}"/>
<input type=hidden id="hdngrid" name="hdngrid" class="easyui-text" value="${requestScope.grid}"/>
<input type=hidden id="hdnflid" name="hdnflid" class="easyui-text" value="${requestScope.flid}"/>
<input type="hidden" id="hdnPageMode" name="hdnPageMode" value="${requestScope.mode}"/>
<table id="clistdGrid" ></table>
<div id="pager"></div>
</div>
<div id="Loadjhclitgrid4">

</div>

</form>
