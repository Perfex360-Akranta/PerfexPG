<script type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"q=2");
});
	
function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString)){
		filterString +="&frmMode="+frmMode;
		processGridnew("LOPCView_input.lopc",filterString,"LOPCVIEWGrid","pager","","doubleClickGrid","","","","");
	    return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

/* function doubleClickGrid(rowid){
var rowData = jQuery("#LOPCVIEWGrid").jqGrid('getRowData',rowid);

 
var frmMode = jQuery('#hdnFrmMode').val();

if(frmMode=="View"){
	var keyid=rowData.WWBL_LOPCID;
	var	WWBLkeyid=rowData.WWBL_KEYID;
	var Status=rowData.WWBL_INVESTIGATION;
	alert("keyidcheck"+keyid);
//LoadPopUp("loadactionclosure","LOPCClosurepopup_input.lopc?q=2&keyid="+keyid,true,"60%","50%","30%","30%", "","LOPC Action Closure"," "," " );
//navigateToNextForm("WwblaView_input.wwbla?q=2&mode="+frmMode+"&keyid="+keyid);
navigateToNextForm("LOPCCreation_input.lopc?frmMode="+frmMode+"&keyid="+keyid+"&WWBLkeyid="+WWBLkeyid+"&Status="+Status);
return false;
}
else if(frmMode=="Report")
var	 keyid=rowData.WWBL_KEYID;

navigateToNextForm("WwblarptGrid_input.Wwbla?keyid="+keyid);
} */
function doubleClickGrid(rowid){
    var rowData = jQuery("#LOPCVIEWGrid").jqGrid('getRowData', rowid);
    
    // Debug: Check what data is actually available
    console.log("Row Data:", rowData);
    console.log("Available keys:", Object.keys(rowData));
    
    var frmMode = jQuery('#hdnFrmMode').val();
    
    if(frmMode == "View"){
        // Try different possible property names (check your grid column configuration)
        var keyid = rowData.WWBL_LOPCID || rowData.wwbl_lopcid || rowData.lopcid;
        var WWBLkeyid = rowData.WWBL_KEYID || rowData.wwbl_keyid || rowData.keyid;
        var Status = rowData.WWBL_INVESTIGATION || rowData.wwbl_investigation || rowData.investigation;
        
        // Validation before proceeding
        if(!keyid || keyid === 'undefined') {
            alert("Error: LOPC ID is missing or undefined");
            console.error("keyid is undefined. Available data:", rowData);
            return false;
        }
        
        if(!WWBLkeyid || WWBLkeyid === 'undefined') {
            alert("Error: WWBL Key ID is missing or undefined");
            console.error("WWBLkeyid is undefined. Available data:", rowData);
            return false;
        }
        
       // alert("keyidcheck: " + keyid);
        
        navigateToNextForm("LOPCCreation_input.lopc?frmMode=" + frmMode + 
                          "&keyid=" + keyid + 
                          "&WWBLkeyid=" + WWBLkeyid + 
                          "&Status=" + (Status || ''));
        return false;
    }
    else if(frmMode == "Report") {
        var keyid = rowData.WWBL_KEYID || rowData.wwbl_keyid || rowData.keyid;
        
        if(!keyid || keyid === 'undefined') {
            alert("Error: Key ID is missing");
            return false;
        }
        
        navigateToNextForm("WwblarptGrid_input.Wwbla?keyid=" + keyid);
    }
}

</script>
<form name="frmLOPCList" id="frmLOPCList" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:-20px;" >
<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="LOPCVIEWGrid"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
</form>