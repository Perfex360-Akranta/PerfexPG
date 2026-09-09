<script type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
jQuery(document).ready(function(){
	var roleid=jQuery("#hdnroleId").val();
	var roleName=jQuery("#hdnroleName").val();
	
	
	
		
	if(roleName=="JH LEADER"&&roleid=="AROL0006"){

		var url = jQuery('#hiddenUrl').val();
		viewGrid(url,"q=2");
	   return true;
		}	
	
	else{

      popupCommonErrorMsg("Select JH Leader Role");
      setTimeout(function() {
    	            navigateToPrevForm();
      }, 3000);
      
      
      return false;
	}	
	
});
	
function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString)){
		filterString +="&frmMode="+frmMode;
		processGridnew("LOPCModification_input.lopc",filterString,"lopcModifyGrd","pager","","doubleClickGrid","","","","");
	    return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleClickGrid(rowid){
	alert(123);
var rowData = jQuery("#lopcModifyGrd").jqGrid('getRowData',rowid);
  var problem = rowData.WWBL_MECHANISM;
  var keyid=rowData.LOEM_KEYID;
  var lopckeyid=rowData.LOEM_KEYID;
  var LopcDesc=rowData.LOEM_DESCNEARMISS;
  var Flid=rowData.LOEM_FLNID;
  var Tier=rowData.LOCY_NAME;
  var EmployeeId=rowData.EmployeeId;
  var WWBLkeyid=rowData.WWBL_KEYID;
   alert(WWBLkeyid +" WWBLkeyid");
   alert("problem "+problem);
var frmMode = jQuery('#hdnFrmMode').val();
alert(frmMode+" mode");
if(frmMode=="modify"){
	//navigateToNextForm("LOPCCreation_input.lopc?q=2&mode="+frmMode+"&keyid="+keyid);
	LoadPopUp(
	        "LOPCModify",
	        "LOPCCreation_input.lopc?keyid="+keyid+"&LopcId="+keyid+"&mode="+frmMode+"&filterButton=false&closeOnSave=true",
	        true,"95%","90%","3%","1%","popup_callback()","LOPC Modify","",true,true
	    );
}
else if(frmMode=="invest"){
	alert(" In side If 1");

	//alert(WWBLkeyid.length);undefined
	if(!WWBLkeyid || WWBLkeyid.length === 0){
		alert(" In side If 2");

	navigateToNextForm("wwbla_input.wwbla?q=2&mode="+frmMode+"&lopckeyid="+lopckeyid+"&WWBLkeyid="+WWBLkeyid+"&LopcDesc="+LopcDesc+"&Flid="+Flid+"&Tier="+Tier+"&EmployeeId="+EmployeeId+"&mechanism="+problem);
}
	else{
		alert(" In side else");
	var keyid=rowData.WWBL_KEYID;
	
	 	//	navigateToNextForm("WwblaView_input.wwbla?q=2&frmMode="+frmMode+"&keyid="+keyid);
		///* navigateToNextForm("LOPCCreation_input.lopc?frmMode="+frmMode+"&keyid="+keyid+"&WWBLkeyid="+WWBLkeyid); */
	navigateToNextForm("wwbla_input.wwbla?q=2&mode="+frmMode+"&lopckeyid="+lopckeyid+"&WWBLkeyid="+WWBLkeyid+"&LopcDesc="+LopcDesc+"&Flid="+Flid+"&Tier="+Tier+"&EmployeeId="+EmployeeId+"&mechanism="+problem);
	
	}
}
}

</script>
<form name="frmLOPCList" id="frmLOPCList" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:-20px;" >
<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="lopcModifyGrd"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
<input type="hidden" id='hdnroleName' value="${requestScope.rolename}"/>
<input type="hidden" id='hdnroleId' value="${requestScope.rolekeyid}"/>


</form>