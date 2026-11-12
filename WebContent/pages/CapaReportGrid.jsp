<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script>
jQuery(document).ready(function(){
		initialiseForm('frmCapaApproval');
		jQuery('#submitForm').val('frmCapaApproval');

		setLoadFormCallBackFrmId("frmCapaApproval");
		var url =jQuery('#hiddenUrl').val();
	//	////alert("URL:::"+url);
	
		var mode=jQuery("#mode").val();
	    viewGrid(url,"&q=2");	
       
	
});


function viewGrid(url,filterString)
{ 
	if( validateFilterSelection(filterString))
	{ 
		
		processGridnew("CapaReport_input.ncapa",filterString,"CapaApprovalGrid","pager","","CAPADoubleClick","");
        return true;
}
 return false;	
}


function CAPADoubleClick(id)
{	
	//alert("inside");

	//var LoginUser=Query("#hdnLoginUser").val();
	var rowData=jQuery("#CapaApprovalGrid").jqGrid('getRowData',id);	
	var CapaId=rowData.QCAP_KEYID;
	//alert("CapaId>>>>"+CapaId);
	 var LoginUser=jQuery("#hdnLoginUser").val();
	//alert("LoinUser"+LoginUser); 
	var mode=jQuery("#mode").val();
	//alert("//alert"+mode);
	var LoginDMTUser=rowData.DmtLeaderId;
	var Status=rowData.QCWF_STATUS;
	var TargetJH=rowData.CELL_KEYID
	//alert("LoginDMTUser"+LoginDMTUser)
   navigateToNextForm("CustomerComplaint_input.ncapa?q=2&CapaId="+CapaId+"&LoginUser="+LoginUser+"&LoginDMTUser="+LoginDMTUser+"&mode="+mode+"&Status="+Status+"&TargetJH="+TargetJH+"&filterButton=false","CAPAApproval");
	    
}

function btnformatter(cellValue, options, rowObject){
    var rowId = options.rowId; // ✅ jqGrid gives rowId here
    return '<input type="button" class="easyui-button" value="..." ' +
           'style="height:20px;width:72px;" onclick="openCapaPopup(' + rowId + ');" />';
}

function openCapaPopup(rowId){
    var gridId = "CapaApprovalGrid";

    // ✅ Try both cases (your grid may use upper/lower column names)
    var capaId = jQuery("#" + gridId).jqGrid('getCell', rowId, "QCAP_KEYID");
    if(!capaId) capaId = jQuery("#" + gridId).jqGrid('getCell', rowId, "qcap_keyid");

    if(!capaId){
        var rowData = jQuery("#" + gridId).jqGrid('getRowData', rowId);
        capaId = rowData.QCAP_KEYID || rowData.qcap_keyid || rowData.KEYID || rowData.keyid;
    }

    if(!capaId){
        alert("CAPA Id not found for selected row.");
        return;
    }

    // ✅ IMPORTANT: open your existing .ncapa page (no direct JSP → no 404)
    var url = "CustomerComplaintReport_input.ncapa?q=2&CapaId=" + encodeURIComponent(capaId) +
              "&popup=Y&mode=VIEW";

    LoadPopUp("divCapaPopUp", url, true, "1170px","590px","6px","5%","","CAPA","",true);
    
    
}








function validateFilterSelection(filterString){
return  true;
}


</script>
<form name="frmCapaApproval" id='frmCapaApproval'>

<div id="wrapperRpt"style= margin-top:2px;>
<table>
      <tr>
           <td style="float:left;">
                     </td>
           
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="impVscomp" ></table>
	                <div id="pager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	

    <div style="margin-left:10px; margin-left:65px\9;margin-top:5px;">
       <table  id='CapaApprovalGrid'>
			<tr>
			<td>
			<div style="margin-top: -26px"></div>
			</td>
			</tr>
		</table>
<div id="pager"></div>
</div>



<input type="hidden" id="mode" name="mode"  value="${requestScope.mode}"/> 
<input type="hidden" id="hdnLoginUser" name="hdnLoginUser"  value="${requestScope.LoginUser}"/> 

<div id="divCapaPopUp"></div>

</form>
