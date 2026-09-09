
<script>
jQuery(document).ready(function(){
	initialiseForm('frmSimplifiedKaizenapproval');
	var url = jQuery('#hiddenUrl').val();
    jQuery("#submitForm").val("frmSimplifiedKaizenapproval");
 	var formId = 'frm' + '<%= request.getAttribute("transCode")%>';
	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	var enable = jQuery("#" + formId + " input[id=hdnWrkFowEnable]").val();
	var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();  
	var transCode = jQuery("#" + formId + " input[id=hdnTransCode]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
	var refRoleId = jQuery("#" + formId + " input[id=hdnRefRoleId]").val();
	var gridId = "MultipleapproveGrd_"+'<%= request.getAttribute("transCode")%>';     
    viewGrid(url,"q=2");
    
});


function viewGrid(url,filterString)
{	
	var location=jQuery('#hdnLocation').val();
	var userRole=jQuery("#hdnrolename").val();
	//alert(location +"    "+userRole);
	if(userRole=="DMT LEADER" && location=="LCN0000005"){
	
		processGridnew(url,filterString,"MultipleapproveGrd","Multipleapprovepages","","","");
		return true;	
	}
	else if(userRole=="JH LEADER"){
		processGridnew(url,filterString,"MultipleapproveGrd","Multipleapprovepages","","","");
		return true;	
	}
	
	
	else{
      popupCommonErrorMsg("Select JH Leader Role");
      return false;
	}	
}

function frmSimplifiedKaizenapproval_beforeSubmit(){
	var gridval=getGridSelectArray('MultipleapproveGrd');
	var gridData='&paramJsonArrConvert='+gridval;
	if(gridval.trim().length>0)	
		return gridData;
	    else 
	    saveForm("frmSimplifiedKaizenapproval","SimplifiedKaizenApproval_save.kaizen?");
	    return false;
}



function frmSimplifiedKaizenapproval_successsCallback(result){
  jQuery("#MultipleapproveGrd").trigger("reloadGrid");
}

function MultipleapproveGrd_selectRow(rowId){
    var kaizenDate=jQuery("#MultipleapproveGrd").jqGrid('getCell', rowId,"dteKznmDate"); 	
    var dateCtrl="dteWrinDate_MultipleapproveGrd_"+rowId;	
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDate(dateCtrl,rowId);	
				} 
			}); 
			
}

function isValidDate(dateCtrl,rowId){
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#MultipleapproveGrd").jqGrid('getCell', rowId,"dteKznmDate");
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
	
 	//var minDate = jQuery("#" + formId + " input[id=hdnCompareMinDate]").val();
	
 //	var maxDate = jQuery("#" + formId + " input[id=hdnCompareMaxDate]").val();
	
	//alert("minDate:"+minDate+",maxDate:"+maxDate);
	if (minDate!= undefined && minDate!="" && minDate!=" ") {
		if(convertStringToDate(approvalDate) < convertStringToDate(minDate))
		{
			alert('Should Not Less Than '+minDate);
			fillWithCurrentDate(dateCtrl);
			return false;
		}
	}
	
/* 	if (maxDate!= undefined && maxDate!="" && maxDate!=" ") {
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
	} */
	
}

</script>

<form id="frmSimplifiedKaizenapproval">
<div id='wrapperRpt' >
<div style="margin-top: -18px">
<table  id='MultipleapproveGrd' >
			<tr>
				<td ></td>
			</tr>
		</table>
		<div id='Multipleapprovepages'></div>
		</div>
</div>
<input type="hidden" id="hdnLocation" name="hdnLocation" value="${requestScope.location}">

<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="mode" name="mode" value="create"/>
<input type="hidden" id="hdnEmployee" value="${requestScope.empId}">
<input type="hidden" id="hdnRefId" name="hdnRefId" value="${requestScope.refId}">
<input type="hidden" id="hdnRefType" name="hdnRefType" value="${requestScope.refType}">
<input type="hidden" id="hdnTransCode" name="hdnTransCode" value="${requestScope.transCode}">
<input type="hidden" id="hdnloginuser" name="hdnloginuser" value="${requestScope.loginuser}">
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}">
</form>