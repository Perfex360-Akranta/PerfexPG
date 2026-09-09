<script type="text/javascript">

jQuery(document).ready(function(){		
	var formId = 'frm' + '<%= request.getAttribute("transCode")%>';
	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	//alert(empId);
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	//alert(refId);
	var enable = jQuery("#" + formId + " input[id=hdnWrkFowEnable]").val();
	var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();
	//alert(refType);
	var transCode = jQuery("#" + formId + " input[id=hdnTransCode]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
	//alert(flId);
	var refRoleId = jQuery("#" + formId + " input[id=hdnRefRoleId]").val();
	//alert(refRoleId);
	if(empId==undefined || empId=="undefined"){
	//	alert("empId==null");
		empId="";
	}
	//alert(flId);
	
	var gridId = "grdWork_"+'<%= request.getAttribute("transCode")%>';
	
	var functionName = gridId+"btnSaveRow_onClick";
	window[functionName] = function(record) {  workFlowSubmit(record); };
	
	var succssClBkFName = formId+"_successsCallback";
	
	window[succssClBkFName] = function(result) { frmWorkFlowApp_successsCallback(result); };

	var gridLoadComplete = gridId+"_workflowOnComplete";
	
	window[gridLoadComplete] = function(jqGridId) {
		 
		grdWorkFlowAppr_onComplete(jqGridId); 
	};
	
	
	var dataStr = "q=2&empId="+empId+"&refId="+refId+"&refType="+refType+"&transCode=";
	dataStr += transCode+"&flId="+flId+"&refRoleId="+refRoleId +"&enable="+enable;
	processGridnew("wrktrn_workflowapp_input.workflow",dataStr,gridId,"","","","",gridLoadComplete);	



});

function grdWorkFlowAppr_onComplete(jqGridId){

	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var value ="";
	var role ="";
	for( var i = 0; i < allRows.length;i++)
	{
		//alert(1234);
		var row = allRows[i];
		var enableRow = row["enableRow"];
		if( enableRow == "Y" ){
			 value += row["hdnWrinEmployeeId"]+",";
			 role = row["txtRoleName"];
		}
	}
	
	var transCode = jqGridId.replace("grdWork_",""); 
	 /*jQuery("#hdnTransCode").val();*/
	var loadCompleteCallBack = 	transCode+"_workFlowLoaded";

	var args = [ value,role];	
	dynamicFunctionCall(loadCompleteCallBack,args)	;	
}


function bindCheckBoxForEdit(cellValue, options, rowObject){
	var rowEdit = rowObject[4];
	var formId = 'frm'+'<%= request.getAttribute("transCode") %>';
	//	var refId = jQuery("#hdnRefId").val();
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	if( rowEdit == "Y" && refId.length>0){
		var gridId = options.gid;
		var rowId= options.rowId;
		var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();
		//alert("refType:"+refType);
		return '<input type="checkbox" id="workflow_'+refType+'_'+rowId+'"  name="workflow_'+refType+'" onclick=chekBoxCheck("'+refType+'","'+rowId+'","'+gridId+'"); style="text-align:center;"/>';
	
	<!--		return '<input type="checkbox" id="workflow_'+refType+'_'+rowId+'"  name="workflow_'+refType+'" onclick="if(this.checked){ alert(\' Finance sheet is checked?\');makeRowEditable(\''+ gridId  + '\',\''+ rowId+'\');checkClickevnt(\''+ rowId + '\',\''+  gridId  +'\');}else{restoreEdit(\''+ gridId  + '\',\''+ rowId+'\');}"; style="text-align:center;"/>';
-->
}
	return " ";		
}
function chekBoxCheck(refType,rowId,gridId){	
		if(gridId=='grdWork_BTSG5L' || gridId=='grdWork_BTSG1C'){ //}&& (jQuery('#workflow_'+refType+'_'+rowId+'').is('checked')==true)){
				if(jQuery('#workflow_'+refType+'_'+rowId+'').is('checked')==true){
				restoreEdit( gridId  , rowId);
			}
			else if(jQuery('#workflow_'+refType+'_'+rowId+'').is('checked')==false){
			var yes=confirm("Are you checked Finance Sheet?");
			//alert(' Finance sheet is checked?');
			if(yes){
			makeRowEditable(gridId , rowId);
		    checkClickevnt( rowId , gridId );
			}
		
			else{
				
				jQuery('#workflow_'+refType+'_'+rowId+'').attr('checked', false); 
				restoreEdit( gridId  , rowId);
				setFocusOnField("btnfilemgr");
			}
			}
		}
		else{
			//alert(000001);
			//if(jQuery('#workflow_'+refType+'_'+rowId+'').is('checked',true)){
				//alert(123);
				makeRowEditable(gridId , rowId);
				checkClickevnt( rowId , gridId );
			//}
		}
		/* /* else{alert(1234567);
			restoreEdit( gridId  , rowId);
		} */ 
	}

function checkClickevnt(rowId,gridId){
	
	
	var status = jQuery("#"+gridId).jqGrid('getCell', rowId, 'selWrinStatus');
	var dateCtrl="dteWrinDate_" + gridId + "_"+rowId;
//	var remarks="txtWrinRemarks_" + gridId + "_"+rowId;
	var approvalDate = getFieldValue(dateCtrl);
	if(approvalDate.trim().length == 0 || status.trim().toUpperCase() == "REWORK"){
		//alert(dateCtrl);
		//var currentTime = new Date();
		//alert(currentTime);
		/*var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var month = currentTime.getMonth();	
		
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
			month = getMonthStringFromInt(month);		
		var hours = currentTime.getHours();
		var minutes = currentTime.getMinutes();
		if (minutes < 10){
			minutes = "0" + minutes;
		}*/
		setTimeout(function(){
			//setFieldValue(dateCtrl, day+'-'+month+'-'+year);
			fillWithCurrentDate(dateCtrl);
			},550);
				
		//jQuery("#"+dateCtrl).datebox("setValue",day+'-'+month+'-'+year);
		//alert(dateCtrl);
		//jQuery('[name='+dateCtrl+']').val(day+'-'+month+'-'+year);
		//fillWithCurrentDate(dateCtrl);
	}

	jQuery("#"+dateCtrl).datebox({  	   
	onSelect:function(recordid){
			
			isValidDate(dateCtrl,rowId,gridId);
		} 
	});
	
  /* 	jQuery("#"+remarks).val({  	   
		onkeypress:function(recordid){
				
			isString(remarks,rowId,gridId);
			} 
		});   */
}

/*function grdWorkFlowApprbtnSaveRow_onClick(record){
			
}
*/
/* function isString(remarks,ctrlRowId,gridId){
	var remarks="txtWrinRemarks_" + gridId + "_"+ctrlRowId;
if(/^[a-zA-Z0-9- ]*$/.test(remarks) == false) {
    alert('Special Characters are not Allowed At '+ctrlRowId);
}
} */
function  workFlowSubmit(record,invokeBfrCalbk){
	var transCode = record.gridId.replace("grdWork_",'') ;
	var selRowData =getGridRowData(record.gridId,record.rowId );
	//var transCode=jQuery("#hdnTransCode").val();
	
	var formId = 'frm'+transCode;
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
	//alert("The flId"+flId);
	var refRoleId = jQuery("#" + formId + " input[id=hdnRefRoleId]").val();
	var rowObj = jQuery.parseJSON( selRowData );	
	//alert(rowObj[0]["selWrinStatus"]);
	var lastLevel = isLastApproval(record) == true?"Y":"N";
	var nexLevel = getNextRoleName(record.gridId,record.rowId);

	var nextRoleName ="";
	var nextRoleId ="";
	var nextEmp = "";
	if( nexLevel != "")
	{
		nextRoleName = nexLevel[0];
		nextRoleId = nexLevel[1];
		nextEmp = nexLevel[2];
	}	
	//alert(Object.keys(rowObj[0]));
	var status = rowObj[0]["selWrinStatus"];
	var roleName =  jQuery("#"+record.gridId).jqGrid('getCell', record.rowId, 'txtRoleName').trim(); // rowObj[0]["txtRoleName"];
//	alert(roleName);
    var remarks=jQuery("#"+record.gridId).jqGrid('getCell', record.rowId, 'txtWrinRemarks').trim();
	//alert(remarks +"remarksremarks");

	if( invokeBfrCalbk == undefined || invokeBfrCalbk != false  ){
		
		var args=[refId,empId,flId,refRoleId,status,roleName,record];
		var workFlowSuccessCallBack = transCode+"_beforeSubmit";	
		var retVal = dynamicFunctionCall(workFlowSuccessCallBack,args);
			
		if( retVal == false || retVal == "false" )
			return ;
	}
	
	var dataStr="workFlowData="+encodeURIComponent(selRowData)+"&transCode="+transCode+"&lastLevel="+encodeURIComponent(lastLevel)+"&nextRoleName="+nextRoleName +"&nextRoleId="+nextRoleId+"&nextEmp="+encodeURIComponent(nextEmp) ;
 //alert(dataStr +"....");
	saveForm(formId, "wrktrn_workflowapp_save.workflow?workFlowData="+encodeURIComponent(selRowData)+"&transCode="+transCode+"&lastLevel="+lastLevel+"&nextRoleName="+nextRoleName+"&nextRoleId="+nextRoleId+"&nextEmp="+nextEmp);
 if(transCode=="KNWWHYAPPR"){
	 processAjaxCalls("KnowWhyapproval_save.KnowWhy?q=2","&refId="+refId,"","");
	return false;
	 }
 
 if(transCode=="MANAGEAPPR"){
	 processAjaxCalls("ManagementApproval_save.Manage?q=2","&roleName="+roleName+"&refId="+refId,"","");
	 return false;
	 }
	 
 else if(transCode=="FIPRODEF"||transCode=="FIPRODEFGE1C"||transCode=="FIPRODEFGE5L"||transCode=="FIPROCLO"||transCode=="FIPROCLOGE1C"||transCode=="FIPROCLOGE5L"||transCode=="FIPROMEA"||transCode=="FIPROANA"||transCode=="FIPROIMP"||transCode=="FIPROCON")
	 {
	 processAjaxCalls("projectsprotoview_Approvals.prpo?q=2","&roleName="+roleName+"&refId="+refId+"&workFlowData="+selRowData +"&nxtrole="+nextRoleId+"&trns="+transCode+"&lstlvl="+lastLevel+"&flId="+flId+"&roleid="+refRoleId+"&status="+status,"","");
	 return false;
	 }
}


function getNextRoleName(jqGridId,rowId){
	var allRows = jQuery("#"+jqGridId).jqGrid("getDataIDs");
	var lastAppDate = "";
	var next = false;
	var roleName = "";
	var prevRoleName ="";
	var retArr = [];
	var roleId = "";
	var empId = "";
	if( allRows.length>0){
		for( var i = 0 ; i< allRows.length; i++){
			if( next){
				roleName = jQuery("#"+jqGridId).jqGrid('getCell', allRows[i], 'txtRoleName');
				roleId = jQuery("#"+jqGridId).jqGrid('getCell', allRows[i], 'hdnWrinRoleId');
				empId = jQuery("#"+jqGridId).jqGrid('getCell', allRows[i], 'hdnWrinEmployeeId');
				if(roleName != prevRoleName)
				{
					retArr.push([roleName]);
					retArr.push([roleId]);
					retArr.push([empId]);
					return retArr; 
				}
			}
			if( allRows[i]== rowId ){
				next = true;
				prevRoleName = jQuery("#"+jqGridId).jqGrid('getCell', allRows[i], 'txtRoleName');
			}
		}
	}	
	return "";
}

function isLastApproval(record){
	var jqGridId = record.gridId;
	var rowIds = jQuery("#"+jqGridId).jqGrid("getDataIDs");
	var curRoleId = jQuery("#"+jqGridId).jqGrid('getCell', record.rowId, 'hdnWrinRoleId');
	var lastRole  = jQuery("#"+jqGridId).jqGrid('getCell', rowIds[rowIds.length-1], 'hdnWrinRoleId');
	
	return lastRole ==  curRoleId ? true : false;
		
}

function isValidDate(dateCtrl,ctrlRowId,gridId){
	var approvalDate = getFieldValue(dateCtrl);
	//var transCode = jQuery("#hdnTransCode").val();
	var transCode = gridId.replace("grdWork_",'');         
	var formId = 'frm'+transCode;
	var jqGridId= gridId;//"grdWork_"+transCode;
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	if (approvalDate=="undefined" || approvalDate=="" || approvalDate==" ") {  
	 if(convertStringToDate(approvalDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	}
	else{
		if(convertStringToDate(approvalDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
	}
	var minDate = jQuery("#" + formId + " input[id=hdnCompareMinDate]").val();
	var maxDate = jQuery("#" + formId + " input[id=hdnCompareMaxDate]").val();
	//alert("minDate:"+minDate+",maxDate:"+maxDate);
	if (minDate!= undefined && minDate!="" && minDate!=" ") {
		if(convertStringToDate(approvalDate) < convertStringToDate(minDate))
		{
			alert('Should Not Less Than '+minDate);
			fillWithCurrentDate(dateCtrl);
			return false;
		}
	}
	if (maxDate!= undefined && maxDate!="" && maxDate!=" ") {
		if(convertStringToDate(approvalDate) > convertStringToDate(maxDate))
		{
			alert('Should Not Exceed '+maxDate);
			fillWithCurrentDate(dateCtrl);
			return false;
		}
	}
	var allRows = jQuery("#"+jqGridId).jqGrid("getRowData");
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;	
			if(parseInt(ctrlRowId)>parseInt(rowId)){
				var prevApprovalDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteWrinDate');			
				prevApprovalDate=prevApprovalDate.replace(" 00:00:00","").trim();		
				//alert('prevApprovalDate:'+prevApprovalDate);	
				//alert('approvalDate:'+approvalDate);	
				if(convertStringToDate(approvalDate) < convertStringToDate(prevApprovalDate))
				{
					alert('Should Not Less than Previous Approval Date');
					fillWithCurrentDate(dateCtrl);
					return false;
				}
			}
		}
	}

	var acceptDateValidationEvents = 	transCode+"_AcceptDateValidation";
	
	var args = [ approvalDate,transCode];
		
	var retVal =  dynamicFunctionCall(acceptDateValidationEvents,args);
	
	if( retVal == false)
	{
		fillWithCurrentDate(dateCtrl);
	}
	
}

function getLastApprovedDate(transCode){
	var jqGridId ="grdWork_"+transCode;

	var allRows = jQuery("#"+jqGridId).jqGrid("getDataIDs");
	var lastAppDate = "";
	if( allRows.length>0){
		for( var i = 0 ; i< allRows.length; i++){
			var appDate = jQuery("#"+jqGridId).jqGrid('getCell', allRows[i], 'dteWrinDate');			
			if( appDate != undefined &&  appDate.trim().length > 0 )
				lastAppDate = appDate;		
			
		}
	}
	return lastAppDate;
}

function frmWorkFlowApp_successsCallback(result){

	var args = null;// [ result.successData. ];
	var transCode = result.transCode;
	var formId = 'frm'+transCode;
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
		if(transCode=="KNWWHYAPPR"){
		 processAjaxCalls("KnowWhyapproval_save.KnowWhy?q=2","&refId="+refId,"","");
		 //return false;
		 }
	jQuery("#grdWork_"+transCode).trigger("reloadGrid");
	args=[result];
	var workFlowSuccessCallBack = transCode+"_successCallback";	
	//alert(workFlowSuccessCallBack +" workFlowSuccessCallBack ");
	dynamicFunctionCall(workFlowSuccessCallBack,args);	//FIPRODEFGE5L
	if(transCode == "KZNBTS" ||transCode == "BTSL5L"||transCode == "BTSSAFETY" || transCode == "BTSG1C"||transCode == "BTSG5L" ||transCode=="BTSNOSAVIN" ){
		
		var GridRefreshCallback = "GridRefresh_Callback";	
		
		dynamicFunctionCall(GridRefreshCallback,args);
		/* closePopUpDialoge("KaizenApp");
		jQuery("#kaizenApproval").trigger("reloadGrid"); */
	}else if(transCode == "OPLAPPROVE" ){
	    //  alert("Inside oplapprove");
  
// OPL popup already closed by OPLAPPROVE_successCallback
// Fetch updated row and highlight it in oplGrid
processAjaxCalls("OplRowUpdate_getData.opl","&keyId=" + refId,"OPLAPPROVE_RowUpdateCallback","");

  }else{
		navigateToPrevForm();
	}
	
}
</script>
<form id="frm${requestScope.transCode}" name="frm${requestScope.transCode}" >
<!-- <div id="${requestScope.transCode}"> -->
<table id="grdWork_${requestScope.transCode}"></table>
<input type="hidden" id="hdnEmployee" value="${requestScope.empId}">
<input type="hidden" id="hdnRefId" name="hdnRefId" value="${requestScope.refId}">
<input type="hidden" id="hdnRefType" name="hdnRefType" value="${requestScope.refType}">
<input type="hidden" id="hdnTransCode" name="hdnTransCode" value="${requestScope.transCode}">
<input type="hidden" id="hdnFlId" name="hdnFlId" value="${requestScope.flId}">
<input type="hidden" id="hdnCompareMaxDate" name="hdnCompareMaxDate" value="${requestScope.maxDate}">
<input type="hidden" id="hdnCompareMinDate" name="hdnCompareMinDate" value="${requestScope.minDate}">
<input type="hidden" id="hdnRefRoleId" name="hdnRefRoleId" value="${requestScope.refRoleId}">
<input type="hidden" id="hdnWrkFowEnable" name="hdnWrkFowEnable" value="${requestScope.enable}">
<!-- </div> -->
</form>

