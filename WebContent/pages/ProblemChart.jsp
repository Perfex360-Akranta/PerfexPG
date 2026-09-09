
<script>
jQuery(document).ready(function(){
	initialiseForm('frmPbmChart');
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		processAjaxCalls("openFile.file?fileName=P Chart.xls", "", "", "", "", "new");					
	});
	
	jQuery('#btnAdd').click(function(){
		var row  = jQuery("#problemgrid").jqGrid('getDataIDs');		
		addRow(row);
	});
	
	jQuery('#btnPCNextStep').click(function(){		 
		saveForm("frmPbmChart","problemChart_save.prch");
	});
	
	var qamatrixId= jQuery('#hdnQamatrixId').val();
	processGridnew("problemchart_input.prch","q=2&procesid="+procesid+"&qamatrixId="+qamatrixId,"problemgrid","pager","","","Load_Complete","");
//	fillComboBox('frmPbmChart','cmbResponsible', 'employeeFilter.commonFilter');
//	fillComboBox('frmPbmChart','cmbProduct', 'comboProduct.commonFilter');
	var mainForm = jQuery('#txtMainform').val();
	if(mainForm != true){
		jQuery(".chart").attr("id","wrapper");
		}
	if(screen.width <= 1024){
		
		jQuery('#problemgrid').css('float','left');
        
    	}	
   	else{
   		
   	}
	//fileManagerPopUp("","tenStep","frmPbmChart","btnFilManage","tenStepFilemgr");
});



function Load_Complete () {
	 if (glbProcessMode=="view")
		 disableForm("frmPbmChart");
}

function addRow(row)
{
	 var prblmData =	getGridSelectArray("problemgrid");
	 //alert(prblmData);
	//var qammKeyid = glbQamatrixId;
	var processId = jQuery('#hdnProcessid').val();

	var selectRow = jQuery("#problemgrid").jqGrid('getGridParam', 'selrow');
	var rowData = jQuery("#problemgrid").jqGrid('getRowData',selectRow);
	
	var subProcessName = rowData.SUBPROCESS;
	var prdInput = rowData.txtQpchProdinput;
	var problem = rowData.txtQpchProblem;
	var defectId = rowData.txtQpchDefectmode;
	var defectName = rowData.defectmode;
	var qfmiKeyid = rowData.txtQpchQfmiKeyid;
	//alert(qfmiKeyid);
	//selectRow = row[i];
	//alert(Object.keys(rowData));
	if (parseInt(selectRow)>0) {
	 	for(var i=0;i<row.length;i++)
			selectRow = row[i];
	 		
		//var emptyItem =[{"txtQpchQfmiKeyid":qfmiKeyid,"txtQpchQammKeyid":glbQamatrixId,"txtQpchKeyid":" ","txtQpchProcessid":processId,"processname":processName,"productinput":" ","defectmode":defectName, "txtQpchProblem":" ","txtQpchInvestechnique":" ","txtQpchInvesresult":" ","txtQpchSuggestaction":" ","txtQpchResponsibile":" ","txtQpchImplementation":"","txtQpchImpact":" ","txtQpchPriority":" ","txtQpchDefectmode":defectId}];
		//'[{"txtQpchQfmiKeyid":"QFMI000144","txtQpchQammKeyid":"QAMM000277","txtQpchKeyid":"QPCH000150","txtQpchProcessid":"PRS0000022","processname":"FH123%20PROCESS%20B","txtQpchProdinput":"MAN","defectmode":"CAMBOUR%20CREASE","txtQpchProblem":"msdkskmsdkk","txtQpchInvestechnique":"kdfjksdksd","txtQpchInvesresult":"knskdnsm%20djsdksndjb","txtQpchSuggestaction":"jksndksndkn%20djn","Responsibile":"BCM01917","txtQpchResponsibile":"BCM01917","txtQpchImplementation":"E","txtQpchImpact":"L","txtQpchPriority":"P2","txtQpchDefectmode":"DEFM000343"}]
		var emptyItem = [{"txtQpchQfmiKeyid":qfmiKeyid,"txtQpchQammKeyid":glbQamatrixId,"txtQpchKeyid":" ","txtQpchSubprocessid":processId,"SUBPROCESS":subProcessName,"txtQpchProdinput":prdInput,"defectmode":defectName,"txtQpchProblem":problem,"txtQpchInvestechnique":" ","txtQpchInvesresult":" ","txtQpchSuggestaction":" ","Responsibile":" ","txtQpchResponsibile":"","txtQpchResponsibile":" ","txtQpchImplementation":" ","txtQpchImpact":" ","txtQpchPriority":" ","txtQpchDefectmode":defectId}];
		jQuery("#problemgrid").jqGrid('addRowData',parseInt(selectRow)+1, emptyItem[0]);
		
		var rowId = parseInt(selectRow)+1;
		//jQuery('#jqg_problemgrid_'+parseInt(rowId)).attr('checked',true);
		//makeRowEditable("problemgrid",rowId);
		jQuery("#problemgrid").setSelection(rowId);
		setFocusOnField("txtQpchInvestechnique_problemgrid_"+rowId);
		
	}
	else
		popupCommonErrorMsg("Select Row to Copy and Pase");
}

function btnFilManage_click(){
    
    var documentNo = "1"; //for prtoType use Only

	//if(documentNo != null && documentNo != ''){
		//fileManagerPopUp("","TenStep","","","");
	//}
}

function ImplxFormatter(id, options, rowObject) {
	var id = options.rowId;
	return '<select id="txtImpl_'+options.rowId+'" style="width:100px;  height:22px;" onchange =myFunction("'+id+'"); class="easyui-text" ><option value=" "></option><option value="E">Easy</option><option value="D">Diffcult</option></select>';

}
function ImpacFormatter(id, options, rowObject) {
	var id = options.rowId;
	return '<select id="txtImpa_'+options.rowId+'" style="width:70px;  height:22px;"  onchange =myFunction("'+id+'"); class="easyui-text" ><option value=" "></option><option value="L">Low</option><option value="H">High</option></select>';

}
function problemgrid_selectRow(id){
	setPriority(id);
	jQuery("#txtQpchImplementation_problemgrid_"+id).attr('onchange','setPriority("'+id+'")'); 
	jQuery("#txtQpchImpact_problemgrid_"+id).attr('onchange','setPriority("'+id+'")'); 
	jQuery("#txtQpchPriority_problemgrid_"+id).attr('disabled','disabled');
	jQuery("#txtQpchDefectmode").attr('disabled','disabled');

	var fieldID =  "_problemgrid_"+id ;

	jQuery("#Responsibile"+fieldID).combobox({onRequest:function(){
		
		var flid = jQuery('#hdnTenStepFlid').val();
		var retStr = "flid="+flid +"&childFlids=Y";
		return retStr;
		
	}});
}

function setPriority(id){ 
	var implementation = jQuery("select[id='txtQpchImplementation_problemgrid_"+id+"'] option:selected").val();
	var impact = jQuery("select[id='txtQpchImpact_problemgrid_"+id+"'] option:selected").val();
	if("E" == implementation && "L" == impact  ){
		jQuery("#txtQpchPriority_problemgrid_"+id).val("P2");
	}
	else if("E" == implementation && "H" == impact  ){
		jQuery("#txtQpchPriority_problemgrid_"+id).val("P1");
		}
	else if("D" == implementation && "H" == impact  ){
		jQuery("#txtQpchPriority_problemgrid_"+id).val("P3");
		}
	else if("D" == implementation && "L" == impact  ){
	  jQuery("#txtQpchPriority_problemgrid_"+id).val("P4");
	}
	
}
	function ProiorityFormatter(id, options, rowObject) {
		return '<input id="txtPriority_'+options.rowId+'" style="width:50px;  height:22px;"  value="" class="easyui-text" />';
	}
function frmPbmChart_beforeSubmit(){
	if (fnGlbSaveMode()==false) {
		return false;
	}
	var problemChartdata = getGridSelectArray("problemgrid");
	//alert(problemChartdata);
	return "&problemChartdata="+problemChartdata;
}
function frmPbmChart_successsCallback(result){
	jQuery("#problemgrid").trigger("reloadGrid");
/*	var defectid = jQuery('#problemgrid').jqGrid('getCell', 1,"txtQpchDefectmode");
	//problemchart_input.prch
	jQuery('#hdnTenStepUrl').val("IIMatrix_input.impac?&defectid="+defectid+"&");
	jQuery('#nxtStepId').val("liiimatrix");
	jQuery("#val").text("Step 4 : Impact & Implementation Matrix");
	LoadForm("divSteps","","IIMatrix_input.impac?&q=2&mainForm=true&defectid="+defectid);
	openTenSteps();
*/	//navigateToNextForm("tenstepsLink_input.tsdi","Problem Chart",null,{"filterString":"problemchart_input.prch"});
}
</script>

<form id="frmPbmChart" name="frmPbmChart" action="" method="post">
<div class="" style="width: 95%;padding: 10px;">
<div >
		
<!-- <table>
<tr>
<td>
<div class="easyui-paddingbfpx"><label>Process</label></div>
		<div class="easyui-paddingbfpx">
			<input type="text" id="txtProcess" name="txtProcess" maxlength="30" style="width:255px; height:21px;" class="easyui-text" />
		</div>
		
			<div class="easyui-paddingbfpx"><label>Defect Mode</label></div>
			<div class="easyui-paddingbfpx">
				<input type="text" id="txtMode" name="txtMode" maxlength="30" style="width:255px; height:21px;" class="easyui-text" />
			</div>
		
		
				<div class="easyui-paddingbfpx"><label>Results</label></div>
				<div class="easyui-paddingbfpx">
					<textarea rows="2" cols="80" id="txtResults" name="txtResults" maxlength="100" style="width:255px;"></textarea> 
				</div>
		
</td>
<td style="padding-left: 10px;">
				<div class="easyui-paddingbfpx"><label>Product Input</label></div>
				<div class="easyui-paddingbfpx" >
						<input id="cmbProduct" name="cmbProduct"  class="easyui-text" style="width:255px; height:21px;"/>
				</div>
				
						<div class="easyui-paddingbfpx"><label>Technique</label></div>
						<div class="easyui-paddingbfpx">
							<input type="text" id="txtTechnique" name="txtTechnique" maxlength="30" style="width:255px; height:21px;" class="easyui-text" />
						</div>
				
			
					<div class="easyui-paddingbfpx"><label>Action</label></div>
					<div class="easyui-paddingbfpx">
						<textarea rows="2" cols="80" id="txtAction" name="txtAction" maxlength="100" style="width:255px;"></textarea>
				   
			</div>
</td>
<td valign="top"  style="padding-left:10px;">
					<div class="easyui-paddingbfpx"><label>Problem</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="txtProblem" name="txtProblem" maxlength="30" style="width:255px; height:21px;" class="easyui-text" />
					</div>
					
						<div class="easyui-paddingbfpx"><label>Responsible</label></div>
						<div class="easyui-paddingbfpx">
							<input type="text" id="cmbResponsible" name="cmbResponsible" maxlength="30" style="width:255px; height:21px;" class="easyui-text" />
					</div>
<div style="padding-top:40px;">
<div><input type="button" id="btnIns" name="btnIns" value="Insert" class="easyui-button" style=" height:25px;"/>
<span style="padding-left:10px;"><input type="button" id="btndel" name="btndel" class="easyui-button" value="Delete" style=" height:25px;"/></span>
</div>
</div>
<div style="position:relative;">
			 <span  id="tenStepFilemgr" style="position:absolute;right:0px;right:30px\9;top:-30px;">
             </span> 
        </div>
</td>
<td valign="top" style="padding-top:19px; padding-left:10px;">
<div><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>

</td>
</tr>
</table>
 -->
 <input type="button" class="easyui-button" value="Save " id ="btnPCNextStep"/>
 <span style="padding-left: 100px;">
 	<input type="button" class="easyui-button" value="Copy Row" id ="btnAdd"/>
 </span>
<br> 
 <div  style="float:left;" >
<table id="problemgrid">
</table>
<div id="pager"></div>
<input type="hidden" name = "txtMainform" id ="txtMainform" value="${requestScopt.mainForm }"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>
</div>
</div>
</form>


