
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	//alert(url);
	 var mode=jQuery('#hdnmode').val();

	 var formType = jQuery("#hdnFormType").val();
     if (formType=='' || formType==' '){
		formType="create";
		jQuery('#btnNew').show();
	 }
	 else
		jQuery('#btnNew').hide();

     if (mode=='view'){
		 jQuery('#btnNew').hide();
	 }


	viewGrid(url,"?q=2");

	jQuery("#btnNew").click(function(){
		navigateToNextForm("hseinspecEntry_input.hseinsp");
	});
});
	
function viewGrid(url,dataString)
{
	 var formType = jQuery("#hdnFormType").val();
	 var mode=jQuery('#hdnmode').val();
		if (formType=='' || formType==' '){
				formType="create";
		}
		if(mode=='view')
			mode="VIEW";
		
		dataString+="&formType="+formType+"&mode="+mode;
		if(validateFilterSelection(dataString)){

			var tableCaption = "Inspection List";
			//alert(url);
			processGridnew(url,dataString,"inspectionListgrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
		    return true;
		    
			}
	
}

function validateFilterSelection(filterString){
	
	return true;
}

function doubleClickGrid(id) 
{ 
	
	var rowData = jQuery("#inspectionListgrid").jqGrid('getRowData',id);
	var keyid = rowData.keyid;
	//alert(keyid);
	var mode="MODIFY";
	var mode=jQuery('#hdnmode').val();
	if(mode=='view')
		mode="VIEW";
	var formType = jQuery("#hdnFormType").val();
	var ds ="?keyid="+keyid+"&mode="+mode+"&formType="+formType;
	navigateToNextForm("hseinspecEntry_input.hseinsp"+ds);
}
</script>
<form name="frmInspectionList" id="frmInspectionList" >
<div id="wrapperRpt"  >
	<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
	<!-- 	<input type="button" class="easyui-button" id = "btnNew"  value = "New" /> -->
		<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="inspectionListgrid"></table>
	<div id="pager"></div>
</div>
<input type="hidden" class="easyui-button" id="hdnmode"	name="hdnmode" value="${requestScope.mode}" />
</form>
<input type="hidden" class="easyui-button" id="hdnFormType"	name="hdnFormType" value="${requestScope.nearmiss}" />