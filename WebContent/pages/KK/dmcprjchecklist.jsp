<script type="text/javascript">	
	jQuery(document).ready(function() {
		initialiseForm('frmChekList');
	
		var projectId  =  jQuery("#hdnChkProjectId").val();

		var enableIn = jQuery("#hdnEnableInclude").val();
		var enableOk = jQuery("#hdnEnableOk").val();
		
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);
		jQuery("#tabCheckList").tabs('select',cTitle);
			
		loadProjectCheckList("grdDefine","D",projectId);
		loadProjectCheckList("grdMeasure","M",projectId);
		loadProjectCheckList("grdAnalyse","A",projectId);
		loadProjectCheckList("grdImprove","I",projectId);
		loadProjectCheckList("grdControl","C",projectId);

		if( enableIn == "Y" || enableOk == "Y")
			enableUIButton("btnSaveCheckList_"+cTitle);		
		
		jQuery("input[id^=btnSaveCheckList_]").click(function(){
			
			saveCheckList("N","D");
			
		});

		jQuery("#btnDefineActionPlan").click(function(){

			var cStage =  jQuery("#hdnCLCurrentStage").val();
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
    		var mode = "view";
    		if(cStage == "D" && (enableIn == "Y" || enableOk == "Y"))	
				mode = "create";		
			actionPlanCheckList("Define","Define",mode);
		});
			
		jQuery("#btnMeasureActionPlan").click(function(){

			var cStage =  jQuery("#hdnCLCurrentStage").val();
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
			
    		var mode = "view";
			if(cStage == "M" && (enableIn == "Y" || enableOk == "Y"))	
				mode = "create";		
			actionPlanCheckList("Measure","Measure",mode);
		});

		jQuery("#btnAnalyseActionPlan").click(function(){

			var cStage =  jQuery("#hdnCLCurrentStage").val();
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
			
    		var mode = "view";
			if(cStage == "A" && (enableIn == "Y" || enableOk == "Y"))	
				mode = "create";		
			actionPlanCheckList("Analyse","Analyse",mode);
		});

		jQuery("#btnImproveActionPlan").click(function(){

			var cStage =  jQuery("#hdnCLCurrentStage").val();
    		var mode = "view";
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
    		
			if(cStage == "I" && (enableIn == "Y" || enableOk == "Y"))	
				mode = "create";		
			actionPlanCheckList("Improve","Improve",mode);
		});
		

		jQuery("#btnControlActionPlan").click(function(){

			var cStage =  jQuery("#hdnCLCurrentStage").val();
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
			
    		var mode = "view";
			if(cStage == "C" && (enableIn == "Y" || enableOk == "Y"))	
				mode = "create";		
			actionPlanCheckList("Control","Control",mode);
		});

		
	});

	function enableCurrentStage(){
		
	}
	
	function actionPlanCheckList(stage,mainTask,mode){

		var keyid = jQuery("#hdnChkProjectId").val();
		var flid = jQuery("#hdnChkListFlid").val() ;
		var actDate = getFieldValue("dteKzpmStartdate");
		openActionPlan("divCheckListActioPlan",keyid,stage,flid,mainTask,keyid,actDate,mode);

	}
	

	function saveCheckList(checkSaved,status,record){
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);

		var gridId = "grd"+cTitle;
		var workFlowStr ="";
		if( record != undefined && record != null){
			workFlowStr = "&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId;
		}
		
		var enableIn = jQuery("#hdnEnableInclude").val();
		var enableOk = jQuery("#hdnEnableOk").val();
		if( enableIn == "Y"  && checkSaved == "N" ){
			if(! validateAtLeastOneChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNo","chkPcllIncludeNotAp"))
			{
				alert("No checklist is selected to save");
				return;
			}	
		}
		else if( enableIn == "Y"  && checkSaved == "Y" ){
			/*if( ! checkCheckListMapped(gridId) ){
				//alert(" Save Check List in " + cTitle + " Stage "  );
				jQuery("#tabCheckList").tabs('select',cTitle);
				return false;
			}
			*/
			var apprMode = "";
			if( status != "D" )
				apprMode = "S";
			
			var isAllYes = validateAllChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNotAp") ;
			if( ((status == "A" && isAllYes ) || status == "R" || status == "E" ) && ! checkDataChanged(gridId) ){
				//alert(" Save Check List in " + cTitle + " Stage "  );
				jQuery("#tabCheckList").tabs('select',cTitle);
				var conf = confirm(" Checklists are modified in " + cTitle + " Stage! Click Ok to save"   );
				if(conf ){
				
					var jsonStr = buildCheckListJSONArray(gridId);
					if(jsonStr != ""){
						processAjaxCalls("prjCheckList_save.prpo","checkListArr="+jsonStr + "&status="+status+"&smsg=false&isAllYes="+isAllYes+"&apprMode="+apprMode +workFlowStr,"checkListLink_susccessCallback","checkListLink_errorCallback","json","checkListSave");	
					}
					return null; // CHANGED NULL TO FALSE
				}
				return false;
			}
			else if( ! isAllYes ){//!validateAllChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNotAp") )
				return false;
			}		
		}
		
		/*else if( enableIn == "Y" ){
			if( ! validateAllChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNotAp") )
				return false;
		  	/*if( ! validateAllChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNo","chkPcllIncludeNotAp"))
		  	{
			  	alert("Select All checkList either Yes or No or Not Applicable");
			 	return false;
		  	}*/  	
		//}*/
		if( enableOk == "Y" ){

			if( status == "A" && ! validateAllChecked(gridId,"chkPcllVerifiedstatusOk" )){
			 	alert("Select All checkpoints 'Ok' ");
		 		return false;
		   	}
			else if( status != "A" && ! validateAllChecked(gridId,"chkPcllVerifiedstatusOk","chkPcllVerifiedstatusNotOk")){
			 	alert("Select All Checkpoints either 'Ok' or 'NotOk'");
		 		return false;
		   	}
		/*   	if( checkDataChanged(gridId)){
		   		alert(" Save Check List in " + cTitle + " Stage "  );
				jQuery("#tabCheckList").tabs('select',cTitle);
				return false;  	
			}*/

		   	if( status == "A" && ! validateAllOk(gridId,"chkPcllVerifiedstatusOk")){
		   		alert(" Can not accept with out completing all points ");
			   	return false;
			}	
		   	else if( status == "R" && ! checkDataChanged(gridId) ){
		   		alert(" Save Check List in " + cTitle + " Stage "  );
				jQuery("#tabCheckList").tabs('select',cTitle);
				return false;
			}	
		}
		if( (status =="A" || status == "R" || status == "E" ) && checkSaved != "N"  && ! checkDataChanged(gridId) ){
			//alert(" Save Check List in " + cTitle + " Stage "  );
			jQuery("#tabCheckList").tabs('select',cTitle);
			var conf = confirm(" Checkpoints are modified in " + cTitle + " Stage! Click Ok to save checklist"   );
			if(conf ){

				var apprMode =  (enableIn == "Y" ? "S" : (enableOk == "Y" ? "A" : "-"));
				if(status == "D" )
					apprMode = "";
				var jsonStr = buildCheckListJSONArray(gridId);
		//		alert(jsonStr);
				if(jsonStr != ""){
					processAjaxCalls("prjCheckList_save.prpo","checkListArr="+jsonStr + "&status="+status+"&smsg=false&apprMode="+apprMode+workFlowStr,"checkListLink_susccessCallback","checkListLink_errorCallback","json","checkListSave");	
				}
				return null;
			}
			return false;
		}
		if( checkSaved != "Y" && checkSaved != "A" && checkSaved != "R"){
			var jsonStr = buildCheckListJSONArray(gridId);
			if(jsonStr != ""){
				processAjaxCalls("prjCheckList_save.prpo","checkListArr="+jsonStr,"checkListLink_susccessCallback","checkListLink_errorCallback","json","checkListSave");	
			}
		}
		else 
			return true;
	}
	function checkCheckListMapped(gridId){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			var rowId = rowIds[ind];
			var keyid = jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnPcllKeyid");
			if(keyid == undefined || keyid == "" || keyid.trim().length <= 0  ){
				return false;	
			}
		}	
		return true;
	}	

	
	function checkListLink_susccessCallback(result){
		if( result.smsg != "false" )
			alert(result.msg);
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);

		var gridId = "grd"+cTitle;
		jQuery("#"+gridId).trigger("reloadGrid");

		var  onsuccessCallBack = cTitle +"_CheckList_SaveSucsessCalback";
		
		var args = [result,cStage];
  	  	dynamicFunctionCall(onsuccessCallBack, args);				
	}
	function getCheckListTitle(stage){
		var title ='';
		if(stage == "D")
			title ='Define';
		else if(stage == "M")
			title ='Measure';
		else if(stage == "A")
			title ='Analyse';
		else if(stage == "I")
			title ='Improve';
		else if(stage == "C")
			title ='Control';
	
		return title;
				
	}
	function loadProjectCheckList(gridId,stage,projectId){
		processGridnew("prjCheckList_input.prpo","stage="+stage+"&projectId="+projectId,gridId,"","","","","prjcheckListLoadComplete");
	}
	
	/*function prjcheckListLoadComplete(gridId){
		
		jQuery('input[id^=btnCklfileMangr_'+gridId +']').click(function(){
			var rowId = this.id.replace('btnCklfileMangr_'+gridId + '_', '');
			
			var documentNo = jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnPcllKeyid");

			if( documentNo.trim().length <= 0 || documentNo == undefined ){
				alert("Save check list before adding files");
				return ;
			}
			var checkList = jQuery('#'+gridId).jqGrid().getCell(rowId,"txtPcllChecklist");
			var cStage =  jQuery("#hdnCLCurrentStage").val();
			var cTitle = getCheckListTitle(cStage);
			var projectId  =  jQuery("#hdnChkProjectId").val();
			var frMode = "view" ;
			if( gridId == "grd"+cTitle ){
				frMode ="create";
			}		
			fileManagerPopUp(documentNo,"PRJCHK",null,this.id,"",frMode,projectId + "-" + cTitle,  escape(checkList));
			
			
			
		});		
	}	
*/
function prjcheckListLoadComplete(gridId){
	//alert("loadcompleted");
	//alert("gridId>>"+gridId);
	var cStage =  jQuery("#hdnCLCurrentStage").val();
	var cTitle = getCheckListTitle(cStage);
	var projectId  =  jQuery("#hdnChkProjectId").val();
	var enableIn = jQuery("#hdnEnableInclude").val();
	//alert("enableIn>>>"+enableIn);
	var enableOk = jQuery("#hdnEnableOk").val();
//	alert("enableOk>>>"+enableOk);
	
	if(enableIn=="Y")
{
        if(! validateAtLeastOneChecked(gridId,"chkPcllIncludeYes","chkPcllIncludeNo","chkPcllIncludeNotAp"))
 {              validateAllCheckedDefault("grd"+cTitle,"chkPcllIncludeYes","chkPcllIncludeNotAp") ;
           }
 }
	
	

	if(enableOk=="Y")
	{
	if(! validateAtLeastOneChecked(gridId,"chkPcllVerifiedstatusOk","chkPcllVerifiedstatusNotOk",""))
	{
	validateAllCheckedDefault("grd"+cTitle,"chkPcllVerifiedstatusOk","chkPcllVerifiedstatusNotOk") ;
	}

	}		

	
	jQuery('input[id^=btnCklfileMangr_'+gridId +']').click(function(){
		var rowId = this.id.replace('btnCklfileMangr_'+gridId + '_', '');
		
		var documentNo = jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnPcllKeyid");

		if( documentNo.trim().length <= 0 || documentNo == undefined ){
			alert("Save check list before adding files");
			return ;
		}
		var checkList = jQuery('#'+gridId).jqGrid().getCell(rowId,"txtPcllChecklist");
		
	
		var frMode = "view" ;
		if( gridId == "grd"+cTitle ){
			frMode ="create";
		}		
		fileManagerPopUp(documentNo,"PRJCHK",null,this.id,"",frMode,projectId + "-" + cTitle,  escape(checkList));
		
		
		
	});		
}	


function validateAllCheckedDefault(gridId,columnName1,columnName2,columnName3){
	//alert("gridId>>"+gridId);
	var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
	//alert("rowIds>>>"+rowIds.length);
	for( var ind=0;ind<rowIds.length;ind++){
		
		var rowId = rowIds[ind];
		//alert("rowId>>>"+rowId);
		jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).attr('checked',true); 
	}
	return true;	
}
	
		
	function checkListFileManagerFormatter(cellValue,options,rowObject){
		var gridId = options.gid;
		var rowId= options.rowId;
		
		var disable ="";//" disabled='disabled'";
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);
		if( gridId == "grd"+cTitle ){
			disable ="";
		}	
		var style="";
		try{
		if( cellValue != undefined && cellValue != "" && parseInt( cellValue,10) > 0)
			style = "background-color:#a7cb00; color:#f00;";
		}catch(Exception){
		}	
		var fieldID = 'btnCklfileMangr_'+gridId+'_'+rowId;
		return '<input id="'+fieldID+'" title="File Attached:'+cellValue +'"  name="'+fieldID+'" class="easyui-button" value="..." type="button" ' + disable + ' style="cursor: pointer;width:30px;height:25px;text-align:center;'+style+'"/>';
	}
	function checkListFormatter(cellValue,options,rowObject){
		var gridId = options.gid;
		var rowId= options.rowId;
		var colName = options.colModel.name;
		
		var disable = " disabled='disabled'";
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);
		if( gridId == "grd"+cTitle ){
			var enableIn = jQuery("#hdnEnableInclude").val();
			var enableOk = jQuery("#hdnEnableOk").val();
			if( (colName == "chkPcllIncludeYes" || colName == "chkPcllIncludeNo" || colName == "chkPcllIncludeNotAp")  && enableIn == 'Y' )
				disable ="";
			else if( (colName == "chkPcllVerifiedstatusOk" ||colName == "chkPcllVerifiedstatusNotOk") && enableOk == 'Y' )
				disable ="";
				
		} 
		var fieldID =colName+"_" + gridId+"_"+rowId ;
		var checked ="";
	
		/*if( (cellValue=="Y" && colName == "chkPcllIncludeYes") || 
				(cellValue=="N" && colName == "chkPcllIncludeNo") ||
				 (cellValue=="Y" && colName == "chkPcllVerifiedstatusOk") ||
				 (cellValue=="N" && colName == "chkPcllVerifiedstatusNotOk") )*/
		if( cellValue=="Y"	)	 
			checked ='checked="checked"';
		return '<input id="'+fieldID+'" '+checked +' name="'+fieldID+'" value="Y" type="checkbox" ' + disable + ' onclick="if(this.checked){toggleCheckListCheckBox(\''+ rowId + '\',\''+ gridId +'\',\''+ colName +'\' );}"   style="text-align:center;"/>';
	}
	
	function toggleCheckListCheckBox(rowId,gridId,fieldName){
		if( fieldName == "chkPcllIncludeYes" ){
			toggleCheckBoxesChecklist(rowId,gridId,fieldName,"chkPcllIncludeNo","chkPcllIncludeNotAp");
		}
		else if( fieldName == "chkPcllIncludeNo" ){
			toggleCheckBoxesChecklist(rowId,gridId,fieldName,"chkPcllIncludeYes","chkPcllIncludeNotAp");	
		}
		else if( fieldName == "chkPcllIncludeNotAp" ){
			toggleCheckBoxesChecklist(rowId,gridId,fieldName,"chkPcllIncludeYes","chkPcllIncludeNo");	
		}
		else if( fieldName == "chkPcllVerifiedstatusOk" ){
			toggleCheckBoxesChecklist(rowId,gridId,fieldName,"chkPcllVerifiedstatusNotOk");	
		}
		else if( fieldName == "chkPcllVerifiedstatusNotOk" ){
			toggleCheckBoxesChecklist(rowId,gridId,fieldName,"chkPcllVerifiedstatusOk");	
		}	
	}
	function toggleCheckBoxesChecklist(rowId,gridId,checkdCheckBox,uncheckdCheckBox1,uncheckdCheckBox2){
		if( jQuery('#'+ checkdCheckBox +"_" + gridId +'_'+rowId).is(':checked') ){
			jQuery('#'+ uncheckdCheckBox1 +'_'+gridId+'_'+rowId).attr('checked', false);
			if( uncheckdCheckBox2 != undefined && uncheckdCheckBox2 != "" ) 
				jQuery('#'+ uncheckdCheckBox2 +'_'+gridId+'_'+rowId).attr('checked', false);
		}	
	}	
	
	function validateAllChecked(gridId,columnName1,columnName2,columnName3){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			
			var rowId = rowIds[ind];
			if( columnName2 == undefined || columnName2 == "" || columnName2 == null){
				if( ! jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') )
					return false;
						
			}
			else if( columnName3 == undefined || columnName3 == "" || columnName3 == null ){
				if( ! jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') 
						&& ! jQuery('#'+ columnName2 +"_" + gridId +'_'+rowId).is(':checked')){
					return false;
				}
			}
			else{
				if( ! jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') 
						&& ! jQuery('#'+ columnName2 +"_" + gridId +'_'+rowId).is(':checked')
						&& ( ! jQuery('#'+ columnName3 +"_" + gridId +'_'+rowId).is(':checked') )  ){
					return false;
				}
			}
		}
		return true;	
	}
	function validateAllOk(gridId,columnName1){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			var rowId = rowIds[ind];

			if( ! jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') &&
					 ! jQuery('#chkPcllIncludeNotAp_' + gridId +'_'+rowId).is(':checked')){
				return false;
			}
			
		}
		return true;	
	}

	function validateAtLeastOneChecked(gridId,columnName1,columnName2,columnName3){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			
			var rowId = rowIds[ind];
			if( columnName2 == undefined || columnName2 == "" || columnName2 == null){
				if(  jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') )
					return true;
						
			}
			else if( columnName3 == undefined || columnName3 == "" || columnName3 == null ){
				if(  jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') 
						||  jQuery('#'+ columnName2 +"_" + gridId +'_'+rowId).is(':checked')){
					return true;
				}
			}
			else{
				if(  jQuery('#'+ columnName1 +"_" + gridId +'_'+rowId).is(':checked') 
						|| jQuery('#'+ columnName2 +"_" + gridId +'_'+rowId).is(':checked')
						|| (  jQuery('#'+ columnName3 +"_" + gridId +'_'+rowId).is(':checked') )  ){
					return true;
				}
			}
		}
		return false;	
	}
	
	function checkDataChanged(gridId){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			var rowId = rowIds[ind];
			var curVal =  getCurrentCheckedValue(gridId,rowId);
			var oldVal =  jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnChanged");
			if( curVal != oldVal )
				return false;
			
		}
		return true;	
	}
	function getCurrentCheckedValue(gridId,rowId){
		var val = "-";
		if( jQuery('#chkPcllIncludeYes_' + gridId +'_'+rowId).is(':checked'))
			val = "Y";
		else if( jQuery('#chkPcllIncludeNo_' + gridId +'_'+rowId).is(':checked'))
			val = "N";
		else if( jQuery('#chkPcllIncludeNotAp_' + gridId +'_'+rowId).is(':checked'))
			val = "X";
		
		if( jQuery('#chkPcllVerifiedstatusOk_' + gridId +'_'+rowId).is(':checked'))
			val += "Y";
		else if( jQuery('#chkPcllVerifiedstatusNotOk_' + gridId +'_'+rowId).is(':checked'))
			val += "N";
		else 
			val += "X";

		return val;
						
	}
	function validateApprovalChecked(gridId){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		for( var ind=0;ind<rowIds.length;ind++){
			var rowId = rowIds[ind];
			if( ! jQuery('#chkPcllVerifiedstatusNotOk_' + gridId +'_'+rowId).is(':checked')  ){
				return false;
			}
		}
		return true;	
	}

	function validateCheckListApproval(){
		var cStage =  jQuery("#hdnCLCurrentStage").val();
		var cTitle = getCheckListTitle(cStage);

		var gridId = "grd"+cTitle;
		if( ! validateApprovalChecked(gridId))
			return false;
		return true;
	}
	
	function buildCheckListJSONArray(gridId){
		var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');
		var projectId = jQuery("#hdnChkProjectId").val();
		var verifiedBy = jQuery("#hdnChkVerifiedBy").val();
		
		var jsonArrStr ="[";
		for( var ind=0;ind<rowIds.length;ind++){
			var rowId = rowIds[ind];
			var keyid = jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnPcllKeyid");
			var chkListId = jQuery('#'+gridId).jqGrid().getCell(rowId,"hdnPcllChecklistid");
			var includ = "-";
			var verified = "X";
			 
			if( jQuery('#chkPcllIncludeYes_' + gridId +'_'+rowId).is(':checked') )
				includ = "Y";
			else if(jQuery('#chkPcllIncludeNo_' + gridId +'_'+rowId).is(':checked'))
				includ = "N";
			else if(jQuery('#chkPcllIncludeNotAp_' + gridId +'_'+rowId).is(':checked'))
				includ = "X";
			
			if( jQuery('#chkPcllVerifiedstatusOk_' + gridId +'_'+rowId).is(':checked') )
				verified = "Y";
			else if( jQuery('#chkPcllVerifiedstatusNotOk_' + gridId +'_'+rowId).is(':checked') )
				verified = "N";

			//if( includ != "-"){
				jsonArrStr += '{"hdnPcllKeyid":"'+keyid +'","hdnPcllProjectid":"'+projectId+'",';
				jsonArrStr += '"hdnPcllChecklistid":"'+chkListId+'","chkPcllInclude":"'+includ+'",';
				jsonArrStr += '"hdnPcllVerifiedby":"'+verifiedBy+'","chkPcllVerifiedstatus":"'+verified+'"},';
			//}
		}	
		jsonArrStr = jsonArrStr.slice(0, -1) + "]";
		return jsonArrStr; 		
	}



</script>
<form id="frmChekList" name="frmChekList">

<div id="ttCheckList" style="width :1000px;width : 1000px\9; height : 200px;height : 220px\9;">
	<div id="tabCheckList" border="false" class="easyui-tabs" fit="true" plain="true" style="height : 350px\9;" align="-20px 0 0 0 0"; tabindex="0">
		<div id="divDefine" title="Define" style="padding:10px;width:102.6%\9" tabindex="0">
			<input type="button" class="easyui-button"  value="Action Plan" id="btnDefineActionPlan" style="height: 22px;width:70px;"/>
			<input id="btnSaveCheckList_Define" type="button"  class="easyui-button"  value="Save" style="height: 22px;width:70px;" disabled="disabled"/>
			<table id="grdDefine" style="width:100%"><tr><td/></tr></table>
			<div id="pgrDefine"></div>
		</div>
		<div id="divMeasure" title="Measure" style="padding:10px;width:102.6%\9" tabindex="0">
			<input type="button" class="easyui-button"  value="Action Plan" id="btnMeasureActionPlan" style="height: 22px;width:70px;"/>
			<input id="btnSaveCheckList_Measure" type="button"  class="easyui-button"  value="Save" style="height: 22px;width:70px;" disabled="disabled" />
			<table id="grdMeasure" style="width:100%"><tr><td/></tr></table>
			<div id="pgrMeasure"></div>
		</div>
		<div id="divAnalyse" title="Analyse" style="padding:10px;width:102.6%\9" tabindex="0">
			<input type="button" class="easyui-button"  value="Action Plan" id="btnAnalyseActionPlan" style="height: 22px;width:70px;"/>
			<input id="btnSaveCheckList_Analyse" type="button"  class="easyui-button"  value="Save" style="height: 22px;width:70px;" disabled="disabled" />
			<table id="grdAnalyse" style="width:100%"><tr><td/></tr></table>
			<div id="pgrAnalyse"></div>
		</div>
		<div id="divImprove" title="Improve" style="padding:10px;width:102.6%\9" tabindex="0">
			<input type="button" class="easyui-button"  value="Action Plan" id="btnImproveActionPlan" style="height: 22px;width:70px;"/>
			<input id="btnSaveCheckList_Improve" type="button"  class="easyui-button"  value="Save" style="height: 22px;width:70px;" disabled="disabled" />
			<table id="grdImprove" style="width:100%"><tr><td/></tr></table>
			<div id="pgrImprove"></div>
		</div>
		<div id="divControl" title="Control" style="padding:10px;width:102.6%\9" tabindex="0">
			<input type="button" class="easyui-button"  value="Action Plan" id="btnControlActionPlan" style="height: 22px;width:70px;" />
			<input id="btnSaveCheckList_Control" type="button"  class="easyui-button"  value="Save" style="height: 22px;width:70px;" disabled="disabled" />
			<table id="grdControl" style="width:100%"><tr><td/></tr></table>
			<div id="pgrControl"></div>
		</div>
	</div>
	
</div>
		
<input type="hidden" id="hdnChkProjectId" name="hdnChkProjectId" value="${requestScope.projectId}"/>
<input type="hidden" id="hdnCLCurrentStage" name="hdnCLCurrentStage" value="${requestScope.currentStage}"/>
<input type="hidden" id="hdnEnableInclude" name="hdnEnableInclude" value="${requestScope.enableInclude}"/>
<input type="hidden" id="hdnEnableOk" name="hdnEnableOk" value="${requestScope.enableOk}"/>
<input type="hidden" id="hdnChkVerifiedBy" name="hdnChkVerifiedBy" value="${requestScope.verifiedBy}"/>
<input type="hidden" id="hdnChkListFlid" name="hdnChkListFlid" value="${requestScope.flid}"/>		
</form>   