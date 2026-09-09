<script type="text/javascript">
jQuery(document).ready(function(){		
	var formId = 'frm' + '<%= request.getAttribute("transCode")%>';

	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	var enable = jQuery("#" + formId + " input[id=hdnWrkFowEnable]").val();
	

	var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();
	var transCode = jQuery("#" + formId + " input[id=hdnTransCode]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
	var refRoleId = jQuery("#" + formId + " input[id=hdnRefRoleId]").val();
	if(empId==undefined || empId=="undefined"){
		//alert("empId==null");
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
	
	
	var dataStr = "&empId="+empId+"&refId="+refId+"&refType="+refType+"&transCode=";
	dataStr += transCode+"&flId="+flId+"&refRoleId="+refRoleId +"&enable="+enable;
	processGridnew("dmcwrktrn_workflowapp_input.workflow",dataStr,gridId,"","","","",gridLoadComplete);	
});

function grdWorkFlowAppr_onComplete(jqGridId){

	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var value ="";
	var role ="";
	for( var i = 0; i < allRows.length;i++)
	{
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
	//alert("rowEdit="+rowEdit);
	var isrowedit = rowEdit.substring(1,2);
	//alert("isrowedit="+isrowedit);
	var formId = 'frm'+'<%= request.getAttribute("transCode") %>';
	//	var refId = jQuery("#hdnRefId").val();
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	var loginid=jQuery("#hdnLoginEmpId").val();
	var gridloginid = rowObject[3];
	var transCode = '<%= request.getAttribute("transCode") %>';
	//if( rowEdit == "Y" && refId.length>0){
	
		var gridId = options.gid;
		var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();
	if(loginid==gridloginid)
	{
        var rowId = options.rowId;
		var status=rowObject[10];
		if(status!="Accept"){
          if(status!="Submitted"){
              if(isrowedit=="Y"){
              return '<input type="checkbox" id="workflow_'+refType+'_'+rowId+'"  name="workflow_'+refType+'" onclick="if(this.checked){makeRowEditable(\''+ gridId  + '\',\''+ rowId+'\');checkClickevnt(\''+ rowId + '\',\''+  gridId  +'\');}else{restoreEdit(\''+ gridId  + '\',\''+ rowId+'\');}"; style="text-align:center;"/>';
		      }
          }
		}
		
	}
	return " ";		
}

function checkClickevnt(rowId,gridId){
	
	
	var status = jQuery("#"+gridId).jqGrid('getCell', rowId, 'selWrinStatus');
	var dateCtrl="dteWrinDate_" + gridId + "_"+rowId;
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
}

/*function grdWorkFlowApprbtnSaveRow_onClick(record){
			
}
*/

function  workFlowSubmit(record,invokeBfrCalbk){
	
	
	var transCode = record.gridId.replace("grdWork_",'') ;
	
	var selRowData =getGridRowData(record.gridId,record.rowId );
	//var transCode=jQuery("#hdnTransCode").val();
	
	var formId = 'frm'+transCode;
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	
	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
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
	var roleName =  jQuery("#"+record.gridId).jqGrid('getCell', record.rowId, 'txtRoleName'); // rowObj[0]["txtRoleName"];
	
	if( invokeBfrCalbk == undefined || invokeBfrCalbk != false  ){
		
		var args=[refId,empId,flId,refRoleId,status,roleName,record];
		var workFlowSuccessCallBack = transCode+"_beforeSubmit";	
		var retVal = dynamicFunctionCall(workFlowSuccessCallBack,args);
			
		if( retVal == false || retVal == "false" )
			return ;
	
	}
	
	saveForm(formId, "dmcwrktrn_workflowapp_save.workflow?workFlowData="+encodeURIComponent(selRowData) +"&transCode="+transCode+"&lastLevel="+lastLevel+"&nextRoleName="+encodeURIComponent(nextRoleName) +"&nextRoleId="+nextRoleId+"&nextEmp="+encodeURIComponent(nextEmp) );
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
		
	var retVal =  dynamicFunctionCall(acceptDateValidationEvents,args)	;
	
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
	
	jQuery("#grdWork_"+transCode).trigger("reloadGrid");
	args=[result];
	var workFlowSuccessCallBack = transCode+"_successCallback";	
	dynamicFunctionCall(workFlowSuccessCallBack,args);	

}

</script>
<form id="frm${requestScope.transCode}" name="frm${requestScope.transCode}" >
<!-- <div id="${requestScope.transCode}"> -->
<table id="grdWork_${requestScope.transCode}"></table>
<input type="hidden" id="hdnLoginEmpId" name="hdnLoginEmpId" value="${requestScope.loginempid}">
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

