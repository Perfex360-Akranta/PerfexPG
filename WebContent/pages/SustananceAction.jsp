
<script type="text/javascript">
jQuery(document).ready(function(){
	
	initialiseForm('frmSusAct');
	
	var qamatrixId =  jQuery('#hdnQamatrixId').val();
	processGridnew("sustananceAction_input.impac","?q=2&qamatrixId="+qamatrixId,"sustanaceActGrid","pager","","","","Load_Complete");


	jQuery('#btnSave').click(function(){		 
		saveForm("frmSusAct","sustanaceAction_save.prch");
	});
	
});

function sustanaceActGrid_selectRow(rowId){

	
	var fliedId = "txtResponsible_sustanaceActGrid_" +rowId;
	
	//reloadCombo("frmProject","cmbKzpmProjectchamp","employee.commonFilter?isPbuHead=Y&flid="+result.flId+"&loginEmpshow=false");

	jQuery("#"+fliedId).combobox({onRequest:function(){
		
		var flid = jQuery('#hdnTenStepFlid').val();
		var retStr = "flid="+flid +"&childFlids=Y";

		return retStr;
		
	}});	
}

function frmSusAct_successsCallback(result){
	jQuery("#sustanaceActGrid").jqGrid().trigger("reloadGrid");
	
	jQuery('#hdnTenStepUrl').val("tenstepsdesign_input.tsdi?&frmMode=LastScreen&");
	 jQuery('#nxtStepId').val("liFinalReview");
	 jQuery("#val").text("Final Review");
	 LoadForm("divSteps","","tenstepsdesign_input.tsdi?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid);
	 openTenSteps();
}

function frmSusAct_beforeSubmit(){
	var sustanaceActdata = getGridSelectArray("sustanaceActGrid");
	var rowObj = jQuery.parseJSON( sustanaceActdata );	
	//alert(Object.keys(rowObj[0]));
	for(var i=0; i<rowObj.length;i++) {
		var susactType = rowObj[i].txtQtsaSusactType;
		var actType = rowObj[i].txtQtsaActivityType;
		if (susactType=="PM") {
			if ( actType=='' || actType=="-" || actType==' ') {
				popupCommonErrorMsg("Select Activity Type for row " + (parseInt(i)+parseInt(1)));
				return false;
			}
		}
	}
	if( sustanaceActdata != "" )//alert(sustanaceActdata);
		return "&sustanaceActdata="+sustanaceActdata;
	return false;	
}

function Load_Complete() {
	//fillComboBoxWithGrid("frmSusAct", "cmbSta_", "comboStatus.commonFilter");
	//fillComboBoxWithGrid("frmSusAct", "cmbRes_", "employee.commonFilter");

	if (glbProcessMode=="view"){
		 disableForm("frmSusAct");
	}else{
		
		jQuery("input:checkbox[id=^jqg_sustanaceActGrid]").trigger("click");
		jQuery("input:checkbox[id=^jqg_sustanaceActGrid]").attr("checked","checked");
	}	 
}

</script>

<form id="frmSusAct" name="frmSusAct" action="" method="post">
<div style="padding: 1px;"> 
<span style="padding-left: 30px;"></span><input type="button" class="easyui-button" value="Save" id ="btnSave"/>
<div id="action" >
<table id="sustanaceActGrid">
</table>
<div id="pager"></div>
</div><input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
<input type="hidden" name = "txtMainform" id ="txtMainform" value="${requestScopt.mainForm }"/>
</div>
</form>
