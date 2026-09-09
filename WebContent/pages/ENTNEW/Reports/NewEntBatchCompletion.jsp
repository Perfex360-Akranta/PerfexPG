<script type="text/javascript">
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPreviousDataUrl').val();
	initialiseForm("frmBatchCompletion");
	 //jQuery('#submitForm').val('frmBatchCompletion'); // set the id of form to submit
	var keyid= jQuery('#hdnKeyid').val();
	//fillComboBox("frmBatchCompletion","cmbBcomProgKeyid","progKey_fillcombo.prgEnt?&keyId="+keyid);
	//fillComboBox("frmBatchCompletion","cmbBcomEntryBy","employee.commonFilter" );
	
	//formatDateBox('dteBcomYear','yyyy');
	/*jQuery('#dteBcomYear').datebox({  
	   formatter: function(date){ return date.getFullYear(); }  
	});  */
	//jQuery('#frmBatchCompletion textarea').css('text-transform', 'uppercase');
	if( prevDataUrl == null || prevDataUrl.length <=0)
	{
		viewGrid(url,"firstClick=Y");
	}
	
	else
	{
		viewGrid(unescape(prevDataUrl),"firstClick=Y");
	}
	  
	///processGridnew("batchCompletion_input.btchCompln",'?q=2',"batchComplnGrid","batchComplnPager","","batchComplnGrid_dblclick","","batchCompleteCallback");
});
jQuery('#btnViewBatchComp').click(function(){
	var progId = jQuery('#cmbBcomProgKeyid').combobox('getValue');
	var year = jQuery('#dteBcomYear').datebox('getValue');
	var dataStr = '?q=2';
	if(progId != null && progId != ' ' && progId != '')
		dataStr += '&progKeyId='+progId;		
	if(year != null && year != ' ' && year != '')
		dataStr += '&progYear='+year;			 	 
	processGridnew("NewbatchCompletion_input.newentRpt",dataStr,"batchComplnGrid","batchComplnPager","","batchComplnGrid_dblclick","","batchCompleteCallback");
});
jQuery( "#imgCloseReason" ).click(function() {
	closePopUp();	
});


function viewGrid(url,filterString)
{
	var mode = jQuery('#hdnMode').val();
	if(mode=='' || mode=='' )
		mode="CREATE";
	if( validateFilterSelection(filterString))
	{
		//url = url.replace("_getData.brdn","_view.brdn");
		//if(url.indexOf('UPM') >=0 || url.indexOf('unplanned')>=0)
			//filterString += '&maintMode=upm';
		jQuery("#hdnFilterString").val(filterString);
		url+="&";
		processGridnew(url,filterString,"batchComplnGrid","batchComplnPager","","batchComplnGrid_dblclick","","batchCompleteCallback");
		return true;	
	}
	return false;	
}
function validateFilterSelection(filterString){
	return true;
}
function frmBatchCompletioncmbBcomProgKeyid_onLoadSuccess(){
}
function frmBatchCompletioncmbBcomEntryBy_onLoadSuccess(){
	
	//var user = jQuery('#hdnUser').val();	
	//jQuery('#cmbBcomEntryBy').combobox('setValue',user);
	readOnlyFields('cmbBcomEntryBy');
}
	
function batchComplnGrid_dblclick(id){	
}
function batchCompleteCallback(id){
	
	/*if(screen.width >=1250)
	{
		jQuery("#batchComplnGrid").setGridWidth(1035);
	}*/
	var mode = jQuery('#hdnMode').val();
	 var row = jQuery("#batchComplnGrid").jqGrid('getDataIDs');
	
	 var cm = jQuery("#batchComplnGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<=row.length;i++)
	 {
		 
			  var Status = jQuery("#batchComplnGrid").jqGrid('getCell',row[i],"txtBcomStatus");	
			  if(Status == 'COMPLETED'){
				  
				  	jQuery("#batchComplnGrid").jqGrid('setCell',row[i],"cancelBatch_Compln","",{'background-color':'#C1C1C1'});
			  		jQuery('#remov_'+row[i]).attr('disabled',"disabled");
			  		
    	 		}
	 }
	 
	 var progId = jQuery('#cmbBcomProgKeyid').combobox('getValue'); 	
		if(progId != null && progId != ' ' && progId != '')
		{
			readOnlyFields('cmbBcomProgKeyid');
			readOnlyFields('dteBcomYear');
		}
}

function frmBatchCompletion_beforeSubmit(){
	
}
function button_cancelBatch(id, options, rowObject)
{					
	var rowId = options.rowId;	
	var statusFlag = rowObject[13];
	if(statusFlag == 'C')	
		return '<input type="button" id="remov_'+rowId+'" class="grdButton ui-state-disabled"/>';	
	else
		return '<input type="button" id="remov_'+rowId+'" class="grdButton" style="margin-top:1px;" value="" onclick="cancelBatch(\''+rowId + '\');"/>';
		
}
function button_SaveBatch(id, options, rowObject)
{					
	var rowId = options.rowId;		
	var formatStr  = '<input id="save_'+rowId+'"' ;
		formatStr  += 'type="button" class="easyui-button" style="height:20px;" value="..." onclick="saveBatch(\''+rowId + '\');">';// tick 
		formatStr  +=  '</span>';
	return formatStr;	
	
}
function button_SaveBudget(id, options, rowObject)
{					
	var rowId = options.rowId;	
	var statusFlag = rowObject[13];

	if(statusFlag == 'C')
	{		
		var formatStr  = '<input id="budget_'+rowId+'"' ;
			formatStr  += 'type="button" class="easyui-button" style="height:20px;" value="..." onclick="saveBudget(\''+rowId + '\');">';// tick 
			formatStr  +=  '</span>';
		return formatStr;	
	}
	else
	{
		var formatStr  = '<input id="budget_'+rowId+'"' ;
			formatStr  += 'type="button" class="easyui-button ui-state-disabled" style="height:20px;" value="...">';// tick 
			formatStr  +=  '</span>';
		return formatStr;	
	}

}
function saveBudget(rowId)
{
	var rowData = jQuery("#batchComplnGrid").jqGrid('getRowData',rowId);
	var batchId = rowData.BATCHID;
	var fromDate = rowData.FROMDATE;
	var training = rowData.FUNCTIONLOCATION;
	var programId = rowData.PROGID;
	var dataStr = '?q=2';
	if( batchId != null && batchId.length > 0  )
		dataStr += '&batchId='+batchId;
	if( fromDate != null && fromDate.length > 0  )
		dataStr += '&fromDate='+fromDate;
	if( programId != null && programId.length > 0  )
		dataStr += '&programId='+programId;
	if( training != null && training.length > 0  )
	{
		dataStr += '&training='+escape(training);
	}	
	LoadPopUp("divShowSaveBudget", "saveBudget_input.btchCompln"+dataStr, true,"70%","85%","0px","20%", "saveBudget_callback","Expense-Actual");
}
function cancelBatch(rowId){
	jQuery('#hdnStatus').val('');
	jQuery('#hdnStatusFlag').val('');
	jQuery('#hdnCancelRowId').val('');
	var rowData = jQuery("#batchComplnGrid").jqGrid('getRowData',rowId);
	jQuery('#hdnStatus').val('D');
	jQuery('#hdnStatusFlag').val('');
	jQuery('#hdnCancelRowId').val(rowId);
	//openPopUp("Cancelled By","Cancelled Date");
	var ds='?q=2';
	LoadPopUp("divShowBatchCompletion", "batchComp_input.btchCompln"+ds, true,"30%","40%","20%","20%", "batchComp_callback","Cancel");		
}

function saveBatch(rowId){
	jQuery('#hdnStatus').val('');
	jQuery('#hdnStatusFlag').val('');
	jQuery('#hdnCancelRowId').val('');
	var rowData = jQuery("#batchComplnGrid").jqGrid('getRowData',rowId);
	var status = rowData.STATUS;
	var batchid = rowData.BATCHID;
	//alert(batchid);
	var stat = rowData.STATUSFLAG;
	var row = jQuery("#batchComplnGrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++)
	{
		var statusFlag = jQuery("#batchComplnGrid").jqGrid('getCell',row[i],'STATUSFLAG');
		if(statusFlag != status)
		{
			statusFlag = "P";
			break;
		}
	}
	jQuery('#hdnStatus').val('C');
	jQuery('#hdnStatusFlag').val(statusFlag);
	jQuery('#hdnCancelRowId').val(rowId);
	var ds='?q=2';
	if(stat == 'C')
		ds += '&status=C&batchid='+batchid;
	else
		ds += '&batchid='+batchid;
	//alert(ds);
	LoadPopUp("divShowBatchCompletion", "batchComp_input.btchCompln"+ds, true,"30%","40%","20%","20%", "batchComp_callback","Complete");
	/*if(stat == 'C')
	 openPopUp("Completed By","Completed Date","disableOK");
	else
	 openPopUp("Completed By","Completed Date");*/
	//insertBatchCompln(rowId);
	
}
function divShowBatchCompletion_onClose()
{
	/*var filterString = jQuery("#hdnFilterString").val(); 
	var url = jQuery('#hiddenUrl').val();
	processGridnew(url,filterString,"batchComplnGrid","batchComplnPager","","batchComplnGrid_dblclick","","batchCompleteCallback");
	return true;*/
	jQuery("#batchComplnGrid").trigger("reloadGrid");
	//processGridnew("batchCompletion_input.btchCompln",'?q=2',"batchComplnGrid","batchComplnPager","","batchComplnGrid_dblclick","","batchCompleteCallback");	
	return true;	
}
function batchComp_callback()
{
	var status = jQuery('#hdnStatus').val();
	
	if(status !=null && status != ' ' && status != '')
	{
		if(status == 'C')
		{
			 jQuery( '#lblBy').html('Completed By');
			 jQuery( '#lblDate').html('Completed Date');
		}
		else
		{
			 jQuery( '#lblBy').html('Cancelled By');
			 jQuery( '#lblDate').html('Cancelled Date');
		}
	}
}
function batchCompln_successCallBack(result){
	show_winMask(0);

	if( result.exception )
	{	
		var validMsgs = result.messages;
		var validTpmMsgs ="";
		for(var i = 0; i < validMsgs.length;i++)
		{	
    		jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
    		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
    		{	
    			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") )
    				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+ validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
    			else	
    				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
    		}	
    		jQuery('#err_'+validMsgs[i][0] ).css("display","block");
    		jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
    		
    		validTpmMsgs += validMsgs[i][1] +",";
    		
		}
		jQuery('#dispErr').html('<h5> ' + validTpmMsgs +'</h5>');
		div_err();
	}
	else if( result.tpmException ){ 		
		jQuery('#dispErr').html('<h5> ' +result.tpmException +'</h5>');
		div_err();			
	}	
	else
	{
		var msg =  result.successData.msg;
		if(msg != null && msg != ' ' && msg != '')
			alert(msg);

		jQuery("#batchComplnGrid").trigger("reloadGrid");
		jQuery('#mstfrm_div').removeClass('popup-mask');	
		jQuery('#divShowBatchCompletionPopupMask').removeClass('popup-mask');
		jQuery( '#divShowBatchCompletionPopupMask' ).removeClass('custom-popup');
		jQuery( '#divShowBatchCompletion').hide();
		closePopUp('divShowBatchCompletion');
		
		//closePopUpDialoge('divShowBatchCompletion');
		
		var rowId = result.rowId;
		if(rowId != null && rowId != '' && rowId != ' ')
		{
			if(confirm('Do You Want to Enter Budget?') == true)
				saveBudget(rowId);
		}
	}
	enableUIButton('btnReason');
	//jQuery('#imgMailDiv').css('display','none');
}

function batchCompln_errorCallBack(result){
	alert(result.tpmException);
}
function insertBatchCompln(rowId)
{
	if(completeDateValidation()==false)
		return false;
	
	var rowData = jQuery("#batchComplnGrid").jqGrid('getRowData',rowId);	
	var dataString = '?q=2&rowId='+rowId;
	var keyId =rowData.BATCHID;	
	var fromDate = rowData.FROMDATE;
	var endDate = rowData.TILLDATE;
	var progId = rowData.PROGID;
	//var status = rowData.txtBcomStatus;
	var status = jQuery('#hdnStatus').val();
	var statusFlag = jQuery('#hdnStatusFlag').val();
	//var progId = jQuery('#cmbBcomProgKeyid').combobox('getValue');
	var entryBy = jQuery('#cmbBcomEntryBy').combobox('getValue');
	var entryDate = jQuery('#dteBcomEntryDate').combobox('getValue');
	var remarks = jQuery('#txtBcomRemarks').val();
	//alert(fromDate);
	if( fromDate != null && fromDate.length > 0  )
		dataString += '&txtBcomStartDate='+fromDate;
	if( endDate != null && endDate.length > 0  )
		dataString += '&txtBcomEndDate='+endDate;
	if( keyId != null && keyId.length > 0  )
		dataString += "&cmbBcomBachKeyid="+keyId;
	if( progId != null && progId.length > 0  )	
		dataString += '&cmbBcomProgKeyid='+progId;	
	if (status != null && status.length > 0  )
		dataString += '&cmbBcomStatus='+status;
	if (statusFlag != null && statusFlag.length > 0  )	
		dataString += '&statusFlag='+statusFlag;
	if (entryBy != null && entryBy.length > 0  )	
		dataString += '&cmbBcomEntryBy='+entryBy;
	if (entryDate != null && entryDate.length > 0  )	
		dataString += '&dteBcomEntryDate='+entryDate;
	if (remarks != null && remarks.length > 0  )	
		dataString += '&txtBcomRemarks='+remarks;
	if(jQuery('#chbMail').is(':checked') == true)
	{
		dataString += '&chkMail=Y';
		disableUIButton('btnReason');
		//jQuery('#imgMailDiv').css('display','block');
	}
	
	processAjaxCalls("batchCompletion_save.btchCompln", dataString, 'batchCompln_successCallBack','batchCompln_errorCallBack');
	//jQuery("#batchComplnGrid").delRowData(rowId);	
}

function completeDateValidation() {
	var currentDate = getServerDateTime();
	var completedDate = jQuery('#dteBcomEntryDate').datebox("getValue");
	var detectionDate = jQuery('#hdnBachFromdate').val();
	if(convertStringToDate(completedDate) > currentDate)
	{
		alert('Should Not Exceed Current Date');
		fillWithCurrentDate('dteBcomEntryDate');
		return false;
	}
	else if(convertStringToDate(completedDate) < convertStringToDate(detectionDate))
	{
		alert('Should Not Less than Batch Start Date');
		fillWithCurrentDate('dteBcomEntryDate');
		return false;
	}
	else
	    clearValidationErrorMsg('dteBcomEntryDate');
}


/*function openPopUp(by,date,disableOK)
{
	
	jQuery('#mstfrm_div').addClass('popup-mask');
	jQuery('#mstfrm_div').css('z-index',5);
	jQuery('#mstfrm_div').show();
	jQuery('#dlgReason').addClass('custom-popup');			
    jQuery( '#dlgReason' ).show();
    jQuery( '#lblBy').html(by);
    jQuery( '#lblDate').html(date);
    jQuery( '#dlgReason' ).css('border','1px solid #2364CA');
    jQuery( '#dlgReason' ).css('z-index',100);     
    jQuery( '#dlgReason' ).css('width',350);
	jQuery( '#dlgReason' ).css('height',300);
	
	formatDateBox('dteBcomEntryDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteBcomEntryDate');
	readOnlyFields('dteBcomEntryDate');

	var status = jQuery('#hdnStatusFlag').val();
	if(status != null && status.length > 0  )
		jQuery('#lblRemarks').removeClass('mandatory-lbl');	
	else
		jQuery('#lblRemarks').addClass('mandatory-lbl');
	
	if(disableOK == 'disableOK')
		disableUIButton('btnReason');
	else
		enableUIButton('btnReason');

	
}
function closePopUp()
{
	enableUIButton('btnReason');
	jQuery('#err_txtBcomRemarks').html('');
	 jQuery('#txtBcomRemarks').val('');
	 jQuery('#dlgReason').hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery('#dlgReason').removeClass('custom-popup');	
}*/
</script>
<form id='frmBatchCompletion' name="frmBatchCompletion">
<div id="wrapper">
<div style="float:left;">
	<table id="batchComplnGrid"><tr><td></td></tr></table>
	<div id="batchComplnPager"></div>
</div>
</div>
<!--	<div id="" style="margin-top:1%">-->
<!--	<div>-->
<!--<div class="main-cntborder">-->
<!--			<table width="" style="margin-left: 6%; width : 280px; height : 224px;" align="center" cellpadding="5px;">-->
<!--			<tr>-->
<!--				<td valign="top"  colspan=""  > -->
<!--					<div><label>Program</label></div>-->
<!--					<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:266px;" id="cmbBcomProgKeyid" name="cmbBcomProgKeyid" value="${requestScope.entBatchcompletion.bcomProgKeyid }"/></div>-->
<!--					<div><label>Year</label></div>-->
<!--					<div style="" class="easyui-paddingbfpx"><input class="easyui-datebox" style="width:166px;" id="dteBcomYear" name="dteBcomYear" value=""/></div>-->
<!--					<div style="margin: 0px;float: right;" class="easyui-paddingbfpx"><input type="button" class="easyui-button" style=" width : 50px;" id="btnViewBatchComp" name="btnViewBatchComp" value="View"/></div>-->
<!--					<div style="margin-top:4%">-->
<!--						<table id="batchComplnGrid"><tr><td></td></tr></table>-->
<!--						<div id="batchComplnPager"></div>-->
<!--					</div>-->
<!--				</td>						-->
<!--			</tr>					-->
<!--			</table>-->
<!--	</div>	-->
<!--	</div>-->
	
<!--	<div id="dlgReason" class="flPopUpBox">-->
<!--		  <div id="titleReason" class="fl-popUpHeader" style="width:366px;margin-left:-10px;">-->
<!--			   	<label id="lblReason" style="margin-left:1px;font-size:11px;"></label> -->
<!--			 	 <img id="imgCloseReason" src="images/cancel.png" style="float:right;"/>-->
<!--		 </div>-->
<!--		 <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">-->
<!--				<label id="lblBy"></label>                       -->
<!--		</div> -->
<!--		<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--			    <input class="easyui-combobox" style="width:304px;" id="cmbBcomEntryBy" name="cmbBcomEntryBy" value="${requestScope.User }"/>-->
<!--		</div>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">-->
<!--				<label id="lblDate"></label>                       -->
<!--		</div> -->
<!--		<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--		 	<input class="easyui-datebox" style="width:304px;" id="dteBcomEntryDate" name="dteBcomEntryDate"/>-->
<!--		</div>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">-->
<!--				 <label id="lblRemarks">Remarks</label>                       -->
<!--		</div> -->
<!--		<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--		 		<textarea id="txtBcomRemarks" name="txtBcomRemarks"  style="resize:none;width:304px;height:70px"></textarea>-->
<!--		 </div>-->
<!--		 <div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--		 	 <label> Send Feedback </label>		 	-->
<!--		 	 <input type="checkbox" id="chbMail" name="chbMail" value="Y"/>-->
<!--		 	  <span style="padding-left:3%">-->
<!--				 <input type="button" class="easyui-button" style=" width : 50px;" id="btnReason" name="btnReason" value="OK"/>-->
<!--			</span>-->
<!--		</div>-->
<!--		<div id="imgMailDiv" style="display:none">-->
<!--		<img id="sendmailImg" name="sendmailImg" src="images/birdmail.gif"/>-->
<!--		</div>-->
<!--	</div>-->
				 
	<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.progKeyId }"/>
	<input type="hidden" id="hdnUser" name="hdnUser" value="${requestScope.User }"/>
	<input type="hidden" id="hdnCancelRowId" name="hdnCancelRowId"/>
	<input type="hidden" id="hdnStatusFlag" name="hdnStatusFlag"/>
	<input type="hidden" id="hdnStatus" name="hdnStatus"/>		
	<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}"  />
	<input type="hidden" id="hdnFilterString" name="hdnFilterString"/>
	<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" /> 	
</form>

