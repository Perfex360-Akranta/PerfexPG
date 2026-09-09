<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<script type="text/javascript">
/*jQuery("#imgPrevtoProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#hdnProdAccView').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgPrevtoProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#hdnProdAccView').val(woConst.hide);
			jQuery('#prodAcceptanceView').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgPrevtoProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#hdnProdAccView').val(woConst.show);
			jQuery('#prodAcceptanceView').hide();
		}
	}
	else
		{
			LoadForm("loadWOReqProdAcceptance","prevloadWOReqProdAcceptance","work_req.work?mode="+frmMode.view,"","workOrderReq","workOrderReqErr");
			LoadForm("loadWOAppProdAcceptance","prevloadWOAppProdAcceptance","work_approval.work?mode="+frmMode.view,"","workOrderCreation","workOrderCreationErr");
			LoadForm("loadWOCreatProdAcceptance","prevloadWOCreatProdAcceptance","work_creation.work?mode="+frmMode.view,"","workOrderAcceptance","workOrderAcceptanceErr");
			LoadForm("loadWOAcc","prevloadWOAcc","work_acceptance.work?mode="+frmMode.view,"","workOrderAllocation","workOrderAllocationErr");
			LoadForm("loadWOAllocation","prevloadWOAllocation","work_allocation.work?mode="+frmMode.view,"","workOrderCompletion","workOrderCompletionErr");
			LoadForm("loadWorkOrderCompletion","prevloadWorkOrderCompletion","work_completion.work?mode="+frmMode.view,"","productionAcceptance","productionAcceptanceErr");
		}
});*/
jQuery("#imgWOProdAccShow").click(function(){
	var showMode = jQuery('#imgWOProdAccShow').attr('src');
	var showModeArr = showMode.split('/');
	var ShowVal;
	var HideVal= showModeArr[1].indexOf('hide');
	var ModeSize= showModeArr.length;
	if (isIE())
		{
			if(showMode.indexOf('show')!= -1){
				if(ModeSize>2)
					ShowVal =showModeArr[5].indexOf('show');
				else
					ShowVal =showModeArr[1].indexOf('show');	
			}
		}
	else{
			ShowVal =showModeArr[1].indexOf('show');
		}
	if(showMode != null && showMode !=" " && showMode != "")
	{
		if(ShowVal>0)
		{
			jQuery('#imgWOProdAccShow').attr('src','images/wohide.png');			
			jQuery('#showProdAccMode').show();
		}
		if(HideVal>0)
		{
			jQuery('#imgWOProdAccShow').attr('src','images/woshow.png');			
			jQuery('#showProdAccMode').hide();
		}
	}
});
jQuery("#imgWOReqinProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#viewrequestPageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOReqinProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#viewrequestPageFlag').val(woConst.hide);
			jQuery('#loadWOReqProdAcceptance').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOReqinProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#viewrequestPageFlag').val(woConst.show);
			jQuery('#loadWOReqProdAcceptance').hide();
		}
	}
	else
		{
			jQuery('#loadWOReqProdAcceptance').addClass('workOrderView');
			LoadForm("loadWOReqProdAcceptance","prevloadWOReqProdAcceptance","work_req.work?mode="+frmMode.view,"","workOrderReq","workOrderReqErr");
		}
});

jQuery("#imgWOAppinProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#viewApprovalPageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOAppinProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#viewApprovalPageFlag').val(woConst.hide);
			jQuery('#loadWOAppProdAcceptance').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOAppinProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#viewApprovalPageFlag').val(woConst.show);
			jQuery('#loadWOAppProdAcceptance').hide();
		}
	}
	else
		{
			jQuery('#loadWOAppProdAcceptance').addClass('workOrderView');
			LoadForm("loadWOAppProdAcceptance","prevloadWOAppProdAcceptance","work_approval.work?mode="+frmMode.view,"","workOrderCreation","workOrderCreationErr");
		}
});
jQuery("#imgWOCreationinProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#viewCreationPageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOCreationinProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#viewCreationPageFlag').val(woConst.hide);
			jQuery('#loadWOCreatProdAcceptance').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOCreationinProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#viewCreationPageFlag').val(woConst.show);
			jQuery('#loadWOCreatProdAcceptance').hide();
		}
	}
	else
		{
			jQuery('#loadWOCreatProdAcceptance').addClass('workOrderView');
			LoadForm("loadWOCreatProdAcceptance","prevloadWOCreatProdAcceptance","work_creation.work?mode="+frmMode.view,"","workOrderAcceptance","workOrderAcceptanceErr");
		}
});

jQuery("#imgWOAcceptanceinProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#viewAcceptancePageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOAcceptanceinProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#viewAcceptancePageFlag').val(woConst.hide);
			jQuery('#loadWOAcc').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOAcceptanceinProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#viewAcceptancePageFlag').val(woConst.show);
			jQuery('#loadWOAcc').hide();
		}
	}
	else
		{
			jQuery('#loadWOAcc').addClass('workOrderView');
			LoadForm("loadWOAcc","prevloadWOAcc","work_acceptance.work?mode="+frmMode.view,"","workOrderAllocation","workOrderAllocationErr");
		}
});
jQuery("#imgWOAllocationinProdAcceptanceView").click(function(){
	var requestPageFlag = jQuery('#viewAllocationPageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOAllocationinProdAcceptanceView').attr('src','images/wominus.png');
			jQuery('#viewAllocationPageFlag').val(woConst.hide);
			jQuery('#loadWOAllocation').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOAllocationinProdAcceptanceView').attr('src','images/woplus.png');
			jQuery('#viewAllocationPageFlag').val(woConst.show);
			jQuery('#loadWOAllocation').hide();
		}
	}
	else
		{
			jQuery('#loadWOAllocation').addClass('workOrderView');
			LoadForm("loadWOAllocation","prevloadWOAllocation","work_allocation.work?mode="+frmMode.view,"","workOrderCompletion","workOrderCompletionErr");
		}
});
jQuery("#imgWOCompletionView").click(function(){
	var requestPageFlag = jQuery('#viewCompletionPageFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOCompletionView').attr('src','images/wominus.png');
			jQuery('#viewCompletionPageFlag').val(woConst.hide);
			jQuery('#loadWorkOrderCompletion').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOCompletionView').attr('src','images/woplus.png');
			jQuery('#viewCompletionPageFlag').val(woConst.show);
			jQuery('#loadWorkOrderCompletion').hide();
		}
	}
	else
		{
			jQuery('#loadWorkOrderCompletion').addClass('workOrderView');
			LoadForm("loadWorkOrderCompletion","prevloadWorkOrderCompletion","work_completion.work?mode="+frmMode.view,"","productionAcceptance","productionAcceptanceErr");
		}
});
jQuery("#imgWOProdAccComTxt").click(function(){
	var requestPageFlag = jQuery('#viewProdAccCommFlag').val();
	if(requestPageFlag != null && requestPageFlag !=" " && requestPageFlag != "")
	{
		if(requestPageFlag == woConst.show)
		{
			jQuery('#imgWOProdAccComTxt').attr('src','images/wominus.png');
			jQuery('#viewProdAccCommFlag').val(woConst.hide);
			jQuery('#loadComTxtProdAcc').show();
		}
		if(requestPageFlag == woConst.hide)
		{
			jQuery('#imgWOProdAccComTxt').attr('src','images/woplus.png');
			jQuery('#viewProdAccCommFlag').val(woConst.show);
			jQuery('#loadComTxtProdAcc').hide();
		}
	}
	else
		{
			LoadForm("loadComTxtProdAcc","prevloadComTxtProdAcc","comm_Text.brdn?mode=ProdAcc","","communicationText","communicationTextErr");
		}
});
function setDatenTimeInProdAcceptance()
{ 
	jQuery('#chkwomsProductionstartflag').click(function() {
		if(jQuery('#chkwomsProductionstartflag').is(':checked') == true)
		{	
			fillWithCurrentDate('dtewomsProductionstartdate');
			fillWithCurrentDate('spnwomsProductionstarttime');
			/*if(jQuery('#dteCompleteddateflag').datebox('getValue') != null && jQuery('#dteCompleteddateflag').datebox('getValue') != '' && jQuery('#dteCompleteddateflag').datebox('getValue') != ' ')	
			{
				displayText('dtewomsProductionstartdate',jQuery('#dteCompleteddateflag').datebox('getValue'));
 		 		displayText('spnwomsProductionstarttime',jQuery('#spnCompletedtimeflag').spinner('getValue'));	
			}
			else
			{
				if(jQuery('#dteWStdateflag').datebox('getValue') != null && jQuery('#dteWStdateflag').datebox('getValue') != '' && jQuery('#dteWStdateflag').datebox('getValue') != ' ')	
				{
					displayText('dtewomsProductionstartdate',jQuery('#dteWStdateflag').datebox('getValue'));
 		 			displayText('spnwomsProductionstarttime',jQuery('#spnWSttimeflag').spinner('getValue'));	
				}
				else
				{
					if(jQuery('#dteAllotdateflag').datebox('getValue') != null && jQuery('#dteAllotdateflag').datebox('getValue') != '' && jQuery('#dteAllotdateflag').datebox('getValue') != ' ')	
					{
						displayText('dtewomsProductionstartdate',jQuery('#dteAllotdateflag').datebox('getValue'));
	 		 			displayText('spnwomsProductionstarttime',jQuery('#spnAllottimeflag').spinner('getValue'));	
					}
					else
					{
						fillWithCurrentDate('dtewomsProductionstartdate');
						fillWithCurrentDate('spnwomsProductionstarttime');
					}
				}
			}*/
		}
		else
			{
				jQuery('#dtewomsProductionstartdate').datebox('setValue','');
				jQuery('#spnwomsProductionstarttime').spinner('setValue','');
				//fillWithCurrentDate('spnwomsProductionstarttime');
			}
	});
}
function compareDatesInProdAcceptance()
{	
    var prodAccDate = jQuery('#dtewomsProductionstartdate').datebox('getValue') + jQuery('#spnwomsProductionstarttime').spinner('getValue');
    var CompletedDate = jQuery('#dteCompleteddateflag').datebox('getValue') + jQuery('#spnCompletedtimeflag').spinner('getValue');
    var startDate = jQuery('#dteWStdateflag').datebox('getValue') + jQuery('#spnWSttimeflag').spinner('getValue');
    var allottedDate = jQuery('#dteAllotdateflag').datebox('getValue') + jQuery('#spnAllottimeflag').spinner('getValue');
	var currentDate = getServerDateTime();	
	
	if(convertStringToDate(prodAccDate) > currentDate)
	{
		showValidationErrorMsg('dtewomsProductionstartdate','Should Not Exceed Current Date/Time');		
        fillWithCurrentDate('dtewomsProductionstartdate');			
		fillWithCurrentDate('spnwomsProductionstarttime');
	}
	else if(jQuery('#dteCompleteddateflag').datebox('getValue') != null && jQuery('#dteCompleteddateflag').datebox('getValue') != '' && jQuery('#dteCompleteddateflag').datebox('getValue') != ' ')	
	{
		if( compareDateTime(prodAccDate,CompletedDate) > 0 )
	    {
	    	showValidationErrorMsg('dtewomsProductionstartdate','Production Acceptance Date should not be lesser than Work End Date');
	    	displayText('dtewomsProductionstartdate',jQuery('#dteCompleteddateflag').datebox('getValue'));
		 	displayText('spnwomsProductionstarttime',jQuery('#spnCompletedtimeflag').spinner('getValue'));	
	    }
	    else
			 clearValidationErrorMsg('dtewomsProductionstartdate');
	}
	else if(jQuery('#dteWStdateflag').datebox('getValue') != null && jQuery('#dteWStdateflag').datebox('getValue') != '' && jQuery('#dteWStdateflag').datebox('getValue') != ' ')	
	{
		if( compareDateTime(prodAccDate,startDate) > 0 )
	    {
			showValidationErrorMsg('dtewomsProductionstartdate','Production Acceptance Date should not be lesser than Work Start Date');
			displayText('dtewomsProductionstartdate',jQuery('#dteWStdateflag').datebox('getValue'));
	 		displayText('spnwomsProductionstarttime',jQuery('#spnWSttimeflag').spinner('getValue'));
	    }
		 else
			    clearValidationErrorMsg('dtewomsProductionstartdate');
	}
	else if(jQuery('#dteAllotdateflag').datebox('getValue') != null && jQuery('#dteAllotdateflag').datebox('getValue') != '' && jQuery('#dteAllotdateflag').datebox('getValue') != ' ')	
	{
		if( compareDateTime(prodAccDate,allottedDate) > 0 )
	    {
		   
			showValidationErrorMsg('dtewomsProductionstartdate','Production Acceptance Date should not be lesser than Allotted Date');
			displayText('dtewomsProductionstartdate',jQuery('#dteAllotdateflag').datebox('getValue'));
	 		displayText('spnwomsProductionstarttime',jQuery('#spnAllottimeflag').spinner('getValue'));
	    }
		 else
			    clearValidationErrorMsg('dtewomsProductionstartdate');
	}
    else
	    clearValidationErrorMsg('dtewomsProductionstartdate');	
}
</script>
<div id="wrapper" style="width:100%;margin-left:-1%;margin-left:-0%\9;">
<table border="0" align="center" width="100%" style="position:relative;">
		<tr><td colspan="3">
			<div id="communicationTextProdAcc">
				<div class="wo-header" style="width:99%;width:99%\9;">
					 <span style="vertical-align:1px;"><img id="imgWOProdAccComTxt" name="imgWOProdAccComTxt" src="images/woplus.png" style="height:15px;"/></span>
					 <span style="vertical-align:3px;">Communication Text</span>
				</div>
				<div id="prevloadComTxtProdAcc" class="loading"></div>
				<div id="loadComTxtProdAcc"></div>
			</div>
		</td></tr>
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		
<!--			<tr>-->
<!--				<td colspan="3">-->
<!--					<div class="wo-header" style="width:75%;">-->
<!--						 <span style="vertical-align:3px;"><img id="imgPrevtoProdAcceptanceView" name="imgPrevtoProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>-->
<!--					</div>-->
<!--					<div id="prodAcceptanceView">-->
<!--						<div id="prevloadWOReqProdAcceptance" class="loading"></div>-->
<!--						<div id="loadWOReqProdAcceptance"></div>-->
<!--					    <div id="prevloadWOAppProdAcceptance" class="loading"></div>-->
<!--					    <div id="loadWOAppProdAcceptance"></div>-->
<!--					    <div id="prevloadWOCreatProdAcceptance" class="loading"></div>-->
<!--				    	<div id="loadWOCreatProdAcceptance"></div>-->
<!--				    	<div id="prevloadWOAcc" class="loading"></div>-->
<!--					    <div id="loadWOAcc"></div>-->
<!--					    <div id="prevloadWOAllocation" class="loading"></div>-->
<!--						<div id="loadWOAllocation"></div>-->
<!--						<div id="prevloadWorkOrderCompletion" class="loading"></div>-->
<!--						<div id="loadWorkOrderCompletion"></div>-->
<!--					</div>-->
<!--				</td>-->
<!--		   </tr>-->
		<tr style="width:90%">
			<td colspan="3">
			<img id="imgWOProdAccShow" name="imgWOProdAccShow" src="images/woshow.png" style="height:15px;position:absolute;left:-3%;"/>
			<div id="showProdAccMode">
			<div id="reqInProAccHeader">
<!--				<div class="wo-header" style="width:75%;">-->
					 <span style="vertical-align:1px;"><img id="imgWOReqinProdAcceptanceView" name="imgWOReqinProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>
					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Request Details</font></span>
<!--				</div>-->
				<div id="prevloadWOReqProdAcceptance" class="loading"></div>
				<div id="loadWOReqProdAcceptance" style="width:100%;width:95%\9"></div>
			</div>
<!--			<div id="appInProAccHeader">-->
<!--				<div class="wo-header" style="width:75%;">-->
<!--					 <span style="vertical-align:1px;"><img id="imgWOAppinProdAcceptanceView" name="imgWOAppinProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>-->
<!--					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Approved Request</font></span>-->
<!--				</div>-->
<!--				<div id="prevloadWOAppProdAcceptance" class="loading"></div>-->
<!--				<div id="loadWOAppProdAcceptance"></div>-->
<!--				</div>-->
<!--				<div id="creatInProAccHeader">-->
<!--				<div class="wo-header" style="width:75%;">-->
<!--					 <span style="vertical-align:1px;"><img id="imgWOCreationinProdAcceptanceView" name="imgWOCreationinProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>-->
<!--					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Creation</font></span>-->
<!--				</div>-->
<!--				<div id="prevloadWOCreatProdAcceptance" class="loading"></div>-->
<!--				<div id="loadWOCreatProdAcceptance"></div>-->
<!--				</div>-->
<!--				<div id="creatAccInProAccHeader">-->
<!--				<div class="wo-header" style="width:75%;">-->
<!--					 <span style="vertical-align:1px;"><img id="imgWOAcceptanceinProdAcceptanceView" name="imgWOAcceptanceinProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>-->
<!--					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Accepted Schedule</font></span>-->
<!--				</div>-->
<!--				<div id="prevloadWOAcc" class="loading"></div>-->
<!--				<div id="loadWOAcc"></div>-->
<!--				</div>-->
				<div id="allocInProAccHeader">
<!--				<div class="wo-header" style="width:75%;">-->
					 <span style="vertical-align:1px;"><img id="imgWOAllocationinProdAcceptanceView" name="imgWOAllocationinProdAcceptanceView" src="images/woplus.png" style="height:15px;"/></span>
					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Allotted Work</font></span>
<!--				</div>-->
				<div id="prevloadWOAllocation" class="loading"></div>
				<div id="loadWOAllocation" style="width:100%;width:95%\9"></div>
				</div>
				</div>
				<div id="compInProAccHeader">
<!--				<div class="wo-header" style="width:75%;">-->
					 <span style="vertical-align:1px;"><img id="imgWOCompletionView" name="imgWOCompletionView" src="images/woplus.png" style="height:15px;"/></span>
					 <span style="vertical-align:3px;"><font style="font-weight: bold;color: #15428B;" size="2px">Completed Work</font></span>
<!--				</div>-->
				<div id="prevloadWorkOrderCompletion" class="loading"></div>
				<div id="loadWorkOrderCompletion" style="width:100%;width:95%\9"></div>
				</div>
				
				
			</td>
		</tr>
		
		<tr>
			<td colspan = "3" width="75%" style="padding-left:15px;">
			    <div class="sub-header" style="width:99%;width:99%\9;" id="prodAccHeader">Production Acceptance</div>			
			    <div>
					<label class="mandatory-lbl">By </label>
					<label class="mandatory-lbl wo-lblProdAcceptDate">Date </label>
			   </div>
				<div class="easyui-paddingbfpx">
					<input  id="cmbwomsProductionby" name="cmbwomsProductionby" class="easyui-combobox"  value="${requestScope.womTlWomst.womsProductionby}"  style="width: 265px;" <c:out value = "${requestScope.woFormBean.disableProductionby == true ? ' disabled':''}"/>/>
					 <span class="wo-spanStatus">
					     <input id="chkwomsProductionstartflag" name="chkwomsProductionstartflag" type="checkbox" value="Y" <c:out value = "${requestScope.woFormBean.disableProductionstartflag == true ? ' disabled':''}"/>/>
				     	 <input id="dtewomsProductionstartdate"  name="dtewomsProductionstartdate" clear="false" class="easyui-datebox" value="${requestScope.womTlWomst.womsProductionstartdate}"  style="width: 166px;" <c:out value = "${requestScope.woFormBean.disableProductionstartdate == true ? ' disabled':''}"/>/>
				    	 <span class="spinner"><input  id="spnwomsProductionstarttime" name="spnwomsProductionstarttime" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.woFormBean.womsProductionstarttime}"  style="width: 80px;" <c:out value = "${requestScope.woFormBean.disableProductionstarttime == true ? ' disabled':''}"/>></span>
				    	 <input type="hidden"  name="hdnProductionstartflag" id="hdnProductionstartflag"  value="${requestScope.womTlWomst.womsProductionstartflag}" />
				   </span>
					<span id="err_cmbwomsProductionby" class="tpm-errormsg"></span>
					<span id="err_dtewomsProductionstartdate" class="tpm-errormsg" style="padding-left:30%"></span>
			  </div>
			  <div>
			 	    <label>Remarks</label>	
<!--			 	    <label style="padding-left:245px;">Work Permit Sign Off</label>			  		-->
			  </div>
			  <div class="easyui-paddingbfpx">
						<textarea  id="txtwomsProductionremarks" name="txtwomsProductionremarks"   maxlength="600" style="width: 265px;" <c:out value = "${requestScope.woFormBean.disableProductionremarks == true ? ' disabled':''}"/>>${requestScope.womTlWomst.womsProductionremarks}</textarea>
<!--						<span style="padding-left:3%;vertical-align:40px;">-->
<!--				     		<input id="chbwomsSafetypermitsignoff" name="chbwomsSafetypermitsignoff" type="checkbox" value="Y" <c:out value = "${requestScope.woFormBean.disableSafetypermitsignoff == true ? ' disabled':''}"/>/>-->
<!--				     		<input type="text" class="easyui-text" id = "txtPermitnumber" name="txtPermitnumber"  tabindex = "-1" disabled  style="width:265px;" />-->
<!--				     		<input type="hidden"  name="hdnSafetypermitsignoff" id="hdnSafetypermitsignoff"  value="${requestScope.womTlWomst.womsSafetypermitsignoff}" />-->
<!--				     	</span>-->
						<span id="err_txtwomsProductionremarks" class="tpm-errormsg"></span>
			  </div>
			   <div style="display:none">
			  			<input id="dteAllotdateflag"  name="dteAllotdateflag" clear="false" class="easyui-datebox" value="${requestScope.womTlWomst.womsAllotteddate}" required="true" style="width: 180px;" />
				    	<span class="spinner"><input  id="spnAllottimeflag" name="spnAllottimeflag" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.woFormBean.womsAllottedtime}"  style="width: 80px;" ></span>
				    	<input id="dteWStdateflag"  name="dteWStdateflag" clear="false" class="easyui-datebox" value="${requestScope.womTlWomst.womsWorkstartdate}" required="true" style="width: 180px;" />
				    	<span class="spinner"><input  id="spnWSttimeflag" name="spnWSttimeflag" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.woFormBean.womsWorkstarttime}"  style="width: 80px;" ></span>
				    	<input id="dteCompleteddateflag"  name="dteCompleteddateflag" clear="false" class="easyui-datebox" value="${requestScope.womTlWomst.womsWorkenddate}" required="true" style="width: 180px;" />
				    	<span class="spinner"><input  id="spnCompletedtimeflag" name="spnCompletedtimeflag" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.woFormBean.womsWorkendtime}"  style="width: 80px;" ></span>
			  </div>

			</td>
		</tr>
</table>
<input type="hidden" id="hdnProdAccView" name="hdnProdAccView"/>
<input type="hidden" id="viewProdAccPageFlag" name="viewProdAccPageFlag" value=""/>
<input type="hidden" id="viewProdAccCommFlag" name="viewProdAccCommFlag" value=""/>
<input type="hidden" id="woProdAccMode" name="woProdAccMode" value="${requestScope.woMode}"/>

</div>