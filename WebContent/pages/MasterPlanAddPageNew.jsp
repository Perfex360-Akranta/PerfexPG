
<script type="text/javascript">	
jQuery(document).ready(function(){		 
	jQuery('#submitForm').val('frmMPAddChild'); 
	initialiseForm("frmMPAddChild");
	jQuery('#frmMPAddChild .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmMPAddChild textarea').css('text-transform', 'uppercase');	
    jQuery('#btnDelActivity').hide();
    jQuery('#hdnClearPrevSession').val('');
    numericTextBox('txtMsidSortno');
    var title = jQuery('#planHead').html();
	if(title != null  &&  title !="" && title !=" ")
	{
		if(title.indexOf('Activity')>=0)
		{
			jQuery('#planDateDiv').css('display','block');
			formatDateBox('dteMilestnFromDt','dd-MMM-yyyy');
			formatDateBox('dteMilestnToDt','dd-MMM-yyyy');
			readOnlyFields('dteMilestnFromDt');		
			readOnlyFields('dteMilestnToDt');			
		}
		if(title.indexOf('Modify')>=0)
			  jQuery('#btnDelActivity').show();
	}
  
});

function dteMilestnToDt_onSelect(record) {
	completedToDateEvt();
} 

function completedToDateEvt()
{
	
	var MilestnefromDate = jQuery('#dteMilestnFromDt').datebox("getValue");
	var MilestnetoDate = jQuery('#dteMilestnToDt').datebox("getValue");
    
	if(convertStringToDate(MilestnefromDate)> convertStringToDate(MilestnetoDate))
	{
		alert('ToDate should not be less than from date ');
		fillWithCurrentDate('dteMilestnToDt');
	}
}


function dteMilestnFromDt_onSelect(record) {
	completedFromDateEvt();
} 

function completedFromDateEvt()
{
	var MilestnefromDate = jQuery('#dteMilestnFromDt').datebox("getValue");
	var MilestnetoDate = jQuery('#dteMilestnToDt').datebox("getValue");
	
	if(MilestnetoDate.trim().length>0){
		
		if(convertStringToDate(MilestnefromDate)> convertStringToDate(MilestnetoDate))
		{
			alert('Fromdate should not be greater than to date ');
			jQuery("#dteMilestnFromDt").datebox("setValue",MilestnetoDate);
		}
    }

}

jQuery("#btnClearActivity").click(function(){
	jQuery('#txtMsidName').val('');
	jQuery('#txtMsidCode').val('');
	jQuery('#txtMsidRemarks').val('');
	jQuery('#txtMsidSortno').val('');
	var title = jQuery('#planHead').html();
	if(title.indexOf('Activity')>=0)
	{
		jQuery('#dteMilestnFromDt').datebox('clear');
		jQuery('#dteMilestnToDt').datebox('clear');
		readOnlyFields('dteMilestnFromDt');		
		readOnlyFields('dteMilestnToDt');	
		jQuery('#chbEnableDate').attr('checked',false);
	}
	title = title.replace('Modify','Add');
	jQuery('#planHead').html(title);
	var level = jQuery('#hdnMsidLevel').val();
	
	var flag = '0';
	var pId = jQuery('#hdnParentid').val();
	if(level != null && level != '' && level != ' ')
	{
		if(level == '3')
			flag = '2';
		else if(level == '2')
			flag = '1';
		else
			pId = 'MP001';
		jQuery('#hdnMsidParentid').val(pId);
		jQuery('#hdnMsidLevel').val(flag);
		jQuery('#hdnClearPrevSession').val('Y');
			
	}
	
});
jQuery("#btnAddActivity").click(function(){
	
	var catName = jQuery('#txtMsidName').val();	

 	if(catName != null  &&  catName !="" && catName !=" " &&catName !="null"){
	   	
	   // jQuery("#preLodDiv").css('display','block');
		//jQuery("#preLodDiv").addClass("tpm-loading");
		//show_winMask(1);
		saveForm('frmMPAddChild','addactivity_save.conf?q=2');		
	}
});
jQuery("#btnDelActivity").click(function(){
	var id = jQuery("#hdnMsidParentid").val();
	var dispCode = jQuery("#txtMsidName").val();	
	var cellId = jQuery('#hdnMspiCellid').val();
	var parent = jQuery('#hdnParentid').val();
	var level = jQuery('#hdnMsidLevel').val();
    if(confirm("Do You Want To Delete?") == true)
	{
     processAjaxCalls("node_del.conf",'?q=2&originalId='+id+'&cellId='+cellId+'&parent='+parent+'&level='+level,'delIndicatorsSuccess','delIndicatorsFailure');
	}  
});
jQuery('#chbEnableDate').click(function() {
	if(jQuery('#chbEnableDate').is(':checked') == true)
	{
		enableFields('dteMilestnFromDt');		
		enableFields('dteMilestnToDt');		
	}
	else
	{
		readOnlyFields('dteMilestnFromDt');		
		readOnlyFields('dteMilestnToDt');
	}
});




 /*function dteMilestnFromDt_onSelect(date){		//date	
	var currentDate = getServerDateTime();

	if( date > currentDate)
	{					
		jQuery('#dteMilestnFromDt').datebox('clear');
		showValidationErrorMsg('dteMilestnFromDt','Should Not Exceed Current Date');	
	}
	else
		clearValidationErrorMsg('dteMilestnFromDt');	
}*/


</script>
<form id="frmMPAddChild" name="frmMPAddChild">
<div style="padding-left:10px;">
		<div class="sub-header" id="planHead" style="width:304px;">${requestScope.title}</div>
		<div>
			<label class="mandatory-lbl">Name</label>
		</div>
		<div class="easyui-paddingbfpx">
			 <input id="txtMsidName" name="txtMsidName" class="easyui-text" type="text" maxlength="250" style="width:304px;"  value="${requestScope.mspIndicators.msidName}"/>	
		 </div>	 
		 <div>
			<label>Code</label>
		</div>
		<div class="easyui-paddingbfpx">
			 <input id="txtMsidCode" name="txtMsidCode" class="easyui-text" type="text" maxlength="15" style="width:304px;"  readonly  = "readonly" value="${requestScope.mspIndicators.msidCode}"/>	
		 </div>	 
		  <div>
			<label class="mandatory-lbl">Sort No.</label>
		</div>
		<div class="easyui-paddingbfpx">
			 <input id="txtMsidSortno" name="txtMsidSortno" class="easyui-text" type="text" maxlength="4" style="width:304px;"  value="${requestScope.mspIndicators.msidSortno}"/>	
		 </div>	 
		 <div>
			<label>Remarks</label>
		</div>
		  <div class="easyui-paddingbfpx">
		  	  <textarea id="txtMsidRemarks" name="txtMsidRemarks"  style="resize:none;width:304px;height:70px" maxlength="490">${requestScope.mspIndicators.msidRemarks}</textarea>
		  </div>
		  <div id="planDateDiv" style="display:none">
			  <div>	
			  		<span style="padding-left:17px;"> <label>Plan From Date </label></span>
					<span style="padding-left:42px;"><label>Plan To Date </label></span>	
			 </div>
			 <div>
			 	 <span  style="padding-left:1px;">
			 	 	<input id="chbEnableDate" name="chbEnableDate" type="checkbox"  value="Y" />
					<input id="dteMilestnFromDt" name="dteMilestnFromDt" class="easyui-datebox"  style="width:100px;" value="${requestScope.planFrmdate}"/>
						
				</span>
				<span  style="padding-left:1px;">
					<input id="dteMilestnToDt" name="dteMilestnToDt" class="easyui-datebox"  style="width:100px;" value="${requestScope.planTodate}"/>
					
				</span>
			 </div>
		 </div>
		 <tr>
		<td>
		<span id="err_dteMilestnFromDt" class="tpm-errormsg"></span>
		</td>
		<td>
		</td>
		<td>
		 <span id="err_dteMilestnToDt" class="tpm-errormsg"></span>
		</td>
		</tr>

		<div style="padding-left:35%;margin-top:10px;">
			<input id="btnAddActivity" class="easyui-button" name="btnAddActivity"  type="button" value="Add" style="height:20px;"  />
			<input id="btnDelActivity" class="easyui-button" name="btnDelActivity"  type="button" value="Delete" style="height:20px;"  />
			<input id="btnClearActivity" class="easyui-button" name="btnClearActivity"  type="button" value="Clear" style="height:20px;"  />
		</div>
<!--		<input type="hidden" id="hdnMsidMspikeyid" name="hdnMsidMspikeyid" value="${requestScope.key}"/>-->
		<input type="hidden" id="hdnMspiTitle" name="hdnMspiTitle" value="${requestScope.mstTitle}"/>
		<input type="hidden" id="hdnMspiPillar" name="hdnMspiPillar" value="${requestScope.pillar}"/>
		<input type="hidden" id="hdnMsidLevel" name="hdnMsidLevel" value="${requestScope.levelNo}"/>		
		<input type="hidden" id="hdnMsidParentid" name="hdnMsidParentid" value="${requestScope.nodeId}"/>
		<input type="hidden" id="hdnMspiKeyid" name="hdnMspiKeyid" value="${requestScope.key}"/>
		<input type="hidden" id="hdnMspiFactoryid" name="hdnMspiFactoryid" value="${requestScope.factId}"/>
		<input type="hidden" id="hdnMspiSectionid" name="hdnMspiSectionid" value="${requestScope.sectId}"/>
		<input type="hidden" id="hdnMspiCellid" name="hdnMspiCellid" value="${requestScope.cellId}"/>	
		<input type="hidden" id="hdnParentid" name="hdnParentid" value="${requestScope.parentId}"/>	
			
		<input type="hidden" id="hdnClearPrevSession" name="hdnClearPrevSession"/>
</div>
</form>