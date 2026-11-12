<script>
jQuery(document).ready(function(){
	initialiseForm('frmRiskAssessment');
	jQuery('#submitForm').val('frmRiskAssessment');	
	var url = jQuery('#hiddenUrl').val();
	fillComboBox("frmRiskAssessment","cmbRasmPreparedby","employee.commonFilter" );
	formatDateBox('dteRasmDate','dd-MMM-yyyy');
	//fillWithCurrentDate('dteRasmDate'); 
	var flId = jQuery("#frmRiskAssessment input[id='flid']").val();	 
	if(flId.length<=0){
		loadFunctionalLocation("RskcFunctionalLoc","functionalLoc.risk","RskcFunctionalLocValues","frmRiskAssessment","&flid=");	
  	}else{
		 loadFunctionalLocation("RskcFunctionalLoc","functionalLoc.risk","RskcFunctionalLocValues","frmRiskAssessment","&flid="+flId);	
  	}
  	//disableField('frmAuditCreation','cmbJhamAudittype');
	processGridnew(url,"&q=1","riskassessmentgrid","riskassessmentpager","Risk Assessment", "","","gridLoadComplete");	
	//var keyid1 = jQuery("#txtRasmKeyid").val();
	//alert("keyid"+keyid);
	//if(keyid1.length==0)
	//	{
	
	//	jQuery("#btnAddManage").attr("disabled", true).addClass("ui-state-disabled"); 
	//}

});
jQuery("#btnAddNew").click(function(){
	var row = jQuery("#riskassessmentgrid").jqGrid("getDataIDs");
	addRow(row);
});


jQuery("#btnAddManage").click(function(){
	jQuery("#btnAddManage").attr("disabled", true).removeClass("ui-state-disabled"); 
	var keyid = jQuery("#txtRasmKeyid").val();
	if(keyid.length!=0)
	{	
	navigateToNextForm("ManagementProgramEntry_input.Manage?keyid="+keyid);
	}
	else{
		popupCommonErrorMsg("Save the RiskAssessment");
	}

});
function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
			 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
			 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#riskassessmentgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
		 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
		 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#riskassessmentgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}
function gridLoadComplete(){	
	var row = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	var rowIds = jQuery("#jhNewAudit").getDataIDs();
	var parameterId =  jQuery("#jhNewAudit").jqGrid('getCell', rowIds[0], 'parameterid');
	jQuery('#hdnParamId').val(parameterId);	
	var row=0;	
	jQuery("#jhNewAudit").setCell(rowIds[0], '', 'parameterid');	 
	 var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	 
	 for(i=1;i<=jhNewAudit.length;i++)	
	 {		 
		var rowId=parseInt(i);
		var riskLev=jQuery("#cmbRasdRisklevelid_riskassessmentgrid_"+rowId).combobox('getText');	
		if(riskLev.trim()!="ACCEPTABLE")	
			enableUIButton("btnactplan_riskassessmentgrid_"+rowId);	
		else
			disableUIButton("btnactplan_riskassessmentgrid_"+rowId);
	 }	
	
}
function riskassessmentgridbtnactplan_onClick(result){	
	var rowid=result.rowId;
	//alert(" rowid :: "+rowid);
	var btnid=result.btnId;	
	var refDocId = jQuery("#riskassessmentgrid").jqGrid('getCell', rowid,"hdnRasdKeyid");
	var mainTask = getFieldValue("txtRasdActivity_riskassessmentgrid_"+rowid);
	var docType = "RISK";
	var flid = jQuery("#frmRiskAssessment input[id='flid']").val(); 
	var keyid = jQuery("#txtRasmKeyid").val();
	/*if(refDocId.trim().length<=0){
		alert("Save Risk Assessment");
		return false;
	}*/
	if(refDocId.trim().length>0)
		openActionPlan("divRiskActionplan",keyid,docType,flid,mainTask,refDocId);
	else
		saveForm("frmRiskAssessment","RiskAssesmentEntry_save.risk?actplan=true&rowid="+rowid,"");
}
function frmRiskAssessment_beforeDelete()
{  
	var keyid= jQuery("#txtRasmKeyid").val();
	
	if(keyid.length>0){	
		var r =confirm("Are you sure to Delete?");
		if(r){	
			/*var jqGridId="riskassessmentgrid";
			var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
			var flg =false;
			var jsonArrO='[';
			var errText="";
			for( var i = 0; i < allRows.length;i++){
				var row = allRows[i];
				var rowId=parseInt(i)+1;	
				var keyid=parseJqGridCellValue(row["hdnRasdKeyid"]);
				if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true))
				{			
					flg=true;
					//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
					if(flg==true){jsonArrO += ",";}
					jsonArrO += "'"+keyid+"'";
				}
				
			}	
			jsonArrO = jsonArrO.slice(0, -1) + "]";
			jsonArrO = (jsonArrO != ']'?jsonArrO:"");
			alert("jsonArrO:"+jsonArrO);
			if(flg==false){
				setTimeout(function() {
					showCommonErrorMsg("Select Atleast One Details");
				}, 200);
				div_err();
				return false;
			}
			var gridData  = '&riskdetails='+jsonArrO;
		    //alert(gridData);
		    if(jsonArrO.trim().length>0)	
				return gridData;
		    else 
		        return false;	*/
		}
		else
			return false;	
	}
	else
		return false;
}
function frmRiskAssessment_beforeSubmit()
{	
	var keyid= jQuery("#txtRasmKeyid").val();
	 var cellId = jQuery("#frmRiskAssessment input[id='cell']").val();
	// alert("cellId"+cellId);
	// alert("cellId"+cellId.length);
	 if(cellId.length==0)
		 {
		   popupCommonErrorMsg("Select JH");
		   return false;
		 }
	
	if(keyid.trim().length>0){
		var r =confirm("Data have been changed, Do you want to proceed?");
		if(r){			
		}
		else
			return false;
	}	
	
	var gridval=getGridSelectArray('riskassessmentgrid');  
    var gridData  = '&riskdetails='+gridval;
    //alert(gridData);
    if(gridval.trim().length>0)	
		return gridData;
    else 
        return false;
		
}

function riskassessmentgrid_selectRow(rowId)
{
	var jqGridId="riskassessmentgrid";
	jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		//alert("onLoadSuccess:function");		
		disableField("frmRiskAssessment","cmbRasdRisklevelid_"+jqGridId+"_"+rowId);
		var prob=jQuery("#cmbRasdProbablityid_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
		//alert("prob"+prob+"sev"+sev);		
		//if(riskLev.trim()!="ACCEPTABLE")		
		//if(riskLevel.trim()!="RIL0000001");
		disableUIButton("btnactplan_"+jqGridId+"_"+rowId);	
		var calps=parseInt(prob)*parseInt(sev);
		//alert(calps);
		if(parseInt(calps)>=3){
			enableUIButton("btnactplan_"+jqGridId+"_"+rowId);
		}	
	}});
	//
		
}

function cmbRasdProbablityid_riskassessmentgrid_onSelect(record,rowId){		 
	//alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	calPS(rowId);
}
function cmbRasdSeviorityid_riskassessmentgrid_onSelect(record,rowId){
	calPS(rowId);
}
function cmbRasdRisklevelid_riskassessmentgrid_onSelect(record,rowId){
	
}
function cmbRasdControltypeid_riskassessmentgrid_onSelect(record,rowId){
	//alert(rowId);
}
function calPS(rowId){
	var prob=getFieldValue("cmbRasdProbablityid_riskassessmentgrid_"+rowId);
	var sev=getFieldValue("cmbRasdSeviorityid_riskassessmentgrid_"+rowId);
	processAjaxCalls('getRiskLevel.risk','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal_OnSuccess','riskVal_OnError');
}
function riskVal_OnSuccess(result){	
	var rowId=result.rowId;
	var riskLevel=result.riskLevel;
	var riskVal=result.riskVal;
	//alert('rowId'+rowId);
	//alert('riskLevel'+riskLevel);	
	//alert('riskVal'+riskVal);
	var jqGridId="riskassessmentgrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtRasdRiskval',riskVal);
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtRasdRisklevelid',riskLevel);
	var prob=jQuery("#cmbRasdProbablityid_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
	var sev=jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	setFieldValue("cmbRasdRisklevelid_"+jqGridId+"_"+rowId,riskLevel);	
	//if(riskLev.trim()!="ACCEPTABLE")		
	//if(riskLevel.trim()!="RIL0000001");	
	//alert("prob"+prob+"sev"+sev);
	disableUIButton("btnactplan_"+jqGridId+"_"+rowId);
	var calps=parseInt(prob)*parseInt(sev);
	if(parseInt(calps)>=3){
		enableUIButton("btnactplan_"+jqGridId+"_"+rowId);
	}
}
function divRiskActionplan_onClose(){
	jQuery("#riskassessmentgrid").trigger("reloadGrid");
	return true;
}
function frmRiskAssessment_successsCallback(result)
{
	//jQuery("#btnAddManage").attr("disabled", true).removeClass("ui-state-disabled");  
	var actplan=result.successData.actplan;
	 //alert('actplan'+actplan);
	 if(actplan==true){
		var rowId=result.successData.rowid;
		//alert(" ssss :: "+rowId);
		var refDocId=result.successData.dtlkeyid;
		var keyid=result.successData.keyId;		
		//var activity=result.successData.activity;
		jQuery("#txtRasmKeyid").val(keyid);
        //alert(" activity :: "+activity);
        
        processGridnew("RiskAssesmentEntry_input.risk?keyid="+keyid,"&q=1","riskassessmentgrid","riskassessmentpager","Risk Assessment", "","","gridLoadComplete");		 
		jQuery("#riskassessmentgrid").trigger("reloadGrid"); 
		jQuery("#txtRasmKeyid").val(keyid);

		setTimeout(function() {
        var mainTask = jQuery("#riskassessmentgrid").jqGrid('getCell',rowId, "txtRasdActivity");
       	//alert(" mainTask :: "+mainTask);
		/*alert('rowId'+rowId);
		 alert('refDocId'+refDocId);
		 alert('keyid'+keyid);*/
		setFieldValue("txtRasmKeyid",keyid);
		//var mainTask = getFieldValue("txtRasdActivity_riskassessmentgrid_"+rowId);
		//alert(mainTask);
		var docType = "RISK";
		var flid = jQuery("#frmRiskAssessment input[id='flid']").val();	
		if(refDocId.trim().length>0)
			openActionPlan("divRiskActionplan",keyid,docType,flid,mainTask,refDocId);
		},550);
			  	
 	}	
 	else{  
		if (jQuery("#txtRasmKeyid").val().trim().length>0){
			jQuery("#riskassessmentgrid").trigger("reloadGrid");
		}  
		else{		
			//clearFields();
		}
		var keyid=result.successData.keyId;	
		//refreshForm();
		 processGridnew("RiskAssesmentEntry_input.risk?keyid="+keyid,"&q=1","riskassessmentgrid","riskassessmentpager","Risk Assessment", "","","gridLoadComplete");		 
		//navigateToNextForm("RiskAssesmentEntry_input.risk?keyid="+keyid);		
 	}
} 

function frmRiskAssessment_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearFields();
}

function clearFields(){
	setFieldValue("txtRasmTitle","");
	setFieldValue("txtRasmArea","");
	jQuery("#riskassessmentgrid").clearGridData();
 }
</script>
<form id="frmRiskAssessment">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3">
					<div id="frmRiskAssessmentFuntKeyIds">
						<input type="hidden" id="company" name="cmbRkaccompany" value=""></input>
						<input type="hidden" id="factory" name="cmbRkacFactoryid" value=""></input>
						<input type="hidden" id="section" name="cmbRkacSectionid" value=""></input>
						<input type="hidden" id="cell" name="cmbRkacCellid" value=""></input>
						<input type="hidden" id="machine" name="cmbRkacMachineid" value=""></input>
						<input type="hidden" id="flid" name="cmbRasmFlid" value="${requestScope.sheTlRiskassessmentmst.rasmFlid}"></input>
						<input type="hidden" id="elementId" name="cmbelementid" value="${requestScope.sheTlRiskassessmentmst.elementid}"></input>
			  		</div>
			 		<div id="RskcFunctionalLoc"  align="left"></div>	
				</td>
			</tr>
			<tr>
				<td width="25%">
					<div><label class="mandatory-lbl">Area</label></div>
					<div>
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtRasmArea" name="txtRasmArea" style="resize:none;width:265px;" maxlength="100"  
																		value="${requestScope.sheTlRiskassessmentmst.rasmArea}" >${requestScope.sheTlRiskassessmentmst.rasmArea}</textarea>
					</div>
				</td>
				<td width="25%">
					<div><label class="mandatory-lbl">Title</label></div>
					<div>
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtRasmTitle" name="txtRasmTitle" style="resize:none;width:265px;" maxlength="100" 
																		value="${requestScope.sheTlRiskassessmentmst.rasmTitle}">${requestScope.sheTlRiskassessmentmst.rasmTitle}</textarea>
					</div>
				</td>
				<td width="90%">
					<table>
						<tr>
							<td>
								<div><label class="mandatory-lbl">Prepared By</label></div>
								<div>
									<input id="cmbRasmPreparedby" name="cmbRasmPreparedby" class="easyui-combobox"  style="width: 265px;"
																					value="${requestScope.sheTlRiskassessmentmst.rasmPreparedby}"/>
								</div>
							</td>
							<td></td>
							<td>
							<div style="margin-left:-128%;margin-bottom: 1px;padding-right:160px;padding-left:-10px">
								<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:23px;"/>	
							</div>
							</td>
						</tr>
						<tr>
							<td>
								<div><label class="mandatory-lbl">Date</label></div>
								<div>
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 87px;" id="dteRasmDate" name="dteRasmDate" 
								 													value="${requestScope.sheTlRiskassessmentmst.rasmDate}"/>
							      	</span>
								</div>
							</td>
							<!--<td align="right">
							 <div style="margin-left:25%;margin-bottom: 1px;padding-right:0px;">
								<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:23px;"/>	
							</div>
							</td>
							
							-->
							<!--  <td align="right">-->
							<td 	padding: 15px>
							 <div style="margin-left:5%;margin-bottom: 1px;padding-right:160px;padding-left:-10px">
								<input type="button" class="easyui-button" value ="Management Program" id="btnAddManage" style="height:23px;"/>	
							
							</div></td>
						</tr>
					</table>
				</td>
			</tr>			
			<tr>
				<td colspan="3">
					<div>
						<table id="riskassessmentgrid" ></table> 
						<div id="riskassessmentpager"></div>
					</div>
				</td>
			</tr>
		</table>
		<input type="hidden" id="txtRasmKeyid" name="txtRasmKeyid" value="${requestScope.sheTlRiskassessmentmst.rasmKeyid}"/> 		
		<input type="hidden" id="mode" name="mode" value="create"/>
	</div>
</form>