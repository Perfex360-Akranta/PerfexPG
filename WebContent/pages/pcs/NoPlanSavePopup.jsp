<script type="text/javascript">
jQuery(document).ready(function(){	
	initialiseForm("frmpcsNoPlanSave");
	formatDateBox('dteFromTime','dd-MMM-yyyy');
	formatDateBox('dteToTime','dd-MMM-yyyy');
	var fromDate=jQuery("#hdnFromDatePopup").val();
	var toDate=jQuery("#hdnToDatePopup").val();
	jQuery("#dteFromTime").datebox('setValue',fromDate);
	jQuery("#dteToTime").datebox('setValue',toDate);
	var fromTime=jQuery("#hdnFromTime").val();
	var toTime=jQuery("#hdnToTime").val();
	jQuery("#spnFromTime").spinner('setValue',fromTime);
	jQuery("#spnToTime").spinner('setValue',toTime);
	jQuery("#txtDuration").val(jQuery("#hdnDuration").val());
	numericTextBox('txtDuration');
	var partNoplanSatus=jQuery("#hdnpartialNoplanSatus").val();
	if(partNoplanSatus==null ||partNoplanSatus=='undefined' ||partNoplanSatus=="" ||partNoplanSatus==' ')
	{
		disableField('frmpcsNoPlanSave','btnPCSView');
		//jQuery('#btnPCSView').attr("disabled", true);
	}
	else 
		enableFields("btnPCSView");
		//jQuery('#btnPCSView').attr("disabled", false);
		
	
});
jQuery("#spnFromTime").change(function(){	
	durationTimeEvt();
});
jQuery("#spnFromTime").spinner({
	onSpinUp:function()
	{
		durationTimeEvt();
	}
});
jQuery("#spnFromTime").spinner({
	onSpinDown:function()
	{
		durationTimeEvt();
	}
});
jQuery("#spnToTime").change(function(){	
	durationTimeEvt();
});
jQuery("#spnToTime").spinner({
	onSpinUp:function()
	{
		durationTimeEvt();
	}
});
jQuery("#spnToTime").spinner({
	onSpinDown:function()
	{
		durationTimeEvt();
	}
});
jQuery("#btnPCSView").click(function()
{
	/*var date= jQuery("#hdnDate").val();
	var facId=jQuery("#hdnFactId").val();
	var cellId=jQuery("#hdnCellId").val();
	var secId=jQuery("#hdnSectionId").val();
	var shift=jQuery("#hdnShiftPopup").val();
	var formLock="lock";
	var url = jQuery("#pcs").jqGrid('getGridParam', 'url');
	url = url.replace("PcsnoPlanEntryRpt_getData.pcscomp","PcsnoPlanEntryRpt_input.pcscomp");
	url = escape(url);
	var fo = {"filterString":url};
	var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":shift,"date":date,"mode":"view","formLock":formLock};*/
	closePopUpDialoge("divNoPlanSave",false);

});
function divNoPlanSave_onClose(){
	return true;
}

jQuery("#txtDuration").change(function()
{	
	var frmTime=jQuery("#dteFromTime").datebox('getValue')+jQuery("#spnFromTime").spinner('getValue');
	var duration=jQuery("#txtDuration").val();
	if(duration!=null && duration!=undefined && duration!="" && !isNaN(duration) )
	{
		var sftEndTime=jQuery("#hdnSftEndtime").val();
		var sftEndTimeS=jQuery("#hdnSftEndtimeS").val();
		var datTime=addMinutesTodateTime(frmTime,duration);
		var datTime1=getSpinTimeFormatted(datTime);
		jQuery("#spnToTime").spinner('setValue',datTime1);
		jQuery("#dteToTime").datebox('setValue',getDateFromDateTime(datTime));
	}
	else
	{
		jQuery("#txtDuration").val("");
		durationTimeEvt();
	}			
});
jQuery("#spnFromTime").blur(function() {
	var time  = jQuery("#dteFromTime").datebox('getValue')+jQuery("#spnFromTime").spinner('getValue');
	var shiftTime=jQuery("#dteFromTime").datebox('getValue') ;
	var sftStrtTime=jQuery("#hdnSftStrttime").val();
	var sftEndTime=jQuery("#hdnSftEndtime").val();
	if(compareDateTime(time,sftStrtTime) ==1 || compareDateTime(sftEndTime,time)==1)
	{
		alert('From Time Should Not Less Than Shift Start Time');
		jQuery("#spnFromTime").spinner('setValue',sftStrtTime.substring(11,17));
		jQuery("#dteFromTime").datebox('setValue',sftStrtTime.substring(0,11));
		durationTimeEvt();
		return false;
	}
});
jQuery("#spnToTime").blur(function() {
	var totime  = jQuery("#dteToTime").datebox('getValue')+jQuery("#spnToTime").spinner('getValue');
	var sftStrtTime=jQuery("#hdnSftStrttime").val();
	var sftEndTime=jQuery("#hdnSftEndtime").val();
	if(compareDateTime(totime,sftStrtTime)==1 || compareDateTime(sftEndTime,totime)==1)
	{
		alert('To Time Should Not Greater Than Shift End Time');
		jQuery("#spnToTime").spinner('setValue',sftEndTime.substring(11,17));
		jQuery("#dteToTime").datebox('setValue',sftEndTime.substring(0,11));
		durationTimeEvt();
		return false;
	}	
});
function durationTimeEvt(){
	var fromTime  = jQuery("#spnFromTime").spinner('getValue');
	var toTime=jQuery("#spnToTime").spinner('getValue');
	var fromDate=jQuery("#dteFromTime").datebox('getValue');
	var toDate= jQuery("#dteToTime").datebox('getValue');
	if(fromDate!=null && toDate!=null && fromDate.length>0 && toDate.length>0 && fromTime!= null && toTime!=null && fromTime.length>0 && toTime.length>0 )
	{	
		actualMins = timeDifference(fromDate+fromTime,toDate+toTime);
		if(isNaN(actualMins.minutes))
		{
			displayText('txtDuration',"");
		}
		else
		{
			displayText('txtDuration',actualMins.minutes+1);
		}		
	}
	else
	{
		displayText('txtDuration',"");
	}
}
jQuery("#btnNoPlanSave").click(function(){
	noPlanSaveinPopup();
	});
function getSpinTimeFormatted(datTime)
{
	var toTime=getTimeFromDateTime(datTime);
	var hour=toTime.substring(0,toTime.indexOf(":"));
	var min=toTime.substring(toTime.indexOf(":")+1,toTime.length);
	if(hour.length==1)
	hour='0'+hour;
	if(min.length==1)
	min='0'+min;
	toTime=hour+":"+min;
	return toTime;
}
jQuery("#frmpcsNoPlanSave").keypress
(
	function(e)
	{	
		if(e.keyCode==113)
			noPlanSaveinPopup();
	}
);
function noPlanSaveinPopup()
{
	var date=jQuery("#hdnDatePopup").val();
	var mchId=jQuery("#hdnMchIdPopup").val();
	var shift=jQuery("#hdnShiftPopup").val();
	var fromDate;
	var toDate;
	var duration;
	if(jQuery("#chkDeleteNoPlan").is(":checked")==true)
	{
		fromDate =jQuery("#hdnFromDatePopup").val() +" "+ jQuery("#hdnFromTime").val();
		toDate =jQuery("#hdnToDatePopup").val() +" "+ jQuery("#hdnToTime").val();
		duration=jQuery("#hdnDuration").val();
	}
	else
	{
		fromDate = jQuery("#dteFromTime").datebox('getValue') +" "+ jQuery("#spnFromTime").spinner('getValue');
		toDate = jQuery("#dteToTime").datebox('getValue') +" "+ jQuery("#spnToTime").spinner('getValue');
		duration=jQuery("#txtDuration").val();
		var time  = jQuery("#dteFromTime").datebox('getValue') +jQuery("#spnFromTime").spinner('getValue');
		var totime  = jQuery("#dteToTime").datebox('getValue') +jQuery("#spnToTime").spinner('getValue');
		var sftStrtTime=jQuery("#hdnSftStrttime").val();
		var sftEndTime=jQuery("#hdnSftEndtime").val();
		if(compareDateTime(time,sftStrtTime) ==-1 || compareDateTime(sftEndTime,time)==-1)
		{
			if(compareDateTime(totime,sftStrtTime)==1 || compareDateTime(sftEndTime,totime)==1)
			{
				alert('To Time Should Not Greater Than Shift End Time');
				jQuery("#spnToTime").spinner('setValue',sftEndTime.substring(11,17));
				jQuery("#dteToTime").datebox('setValue',sftEndTime.substring(0,11));
				durationTimeEvt();
				return false;
			}
		}
		else
		{
			alert('From Time Should Not Less Than Shift Start Time');
			jQuery("#spnFromTime").spinner('setValue',sftStrtTime.substring(11,17));
			jQuery("#dteFromTime").datebox('setValue',sftStrtTime.substring(0,11));
			durationTimeEvt();
			return false;
		}
		if(parseInt(duration)==0 || duration==null || duration=="" || duration==' ')
		{
			//alert(partNoPlan);
			alert('Duration Cannot Be Zero or Empty');
			durationTimeEvt();
			return false;	
		}
	}
	var dataStr = '?q=2&from=noPlanEntry&mchId='+mchId+'&date='+date+'&shift='+shift;
	dataStr += "&saveMode=Save";
	dataStr += "&fromDate="+fromDate+"&toDate="+toDate+"&noplanDuration="+duration;
	dataStr += "&chkDeleteNoPlan="+getChkBoxVal("chkDeleteNoPlan");
	processAjaxCalls('noPlan_save.pcs',dataStr,"noPlanSaveSuccess");	
}
</script>

<form id="frmpcsNoPlanSave" name="frmpcsNoPlanSave">
<div style="margin-left:15px;" ><label><b>Shift Time</b> :${requestScope.ShiftstrtTimeS}<b> &nbsp;to&nbsp; </b> ${requestScope.ShiftEndTimeS}</label></div>
<div style="margin-top:20px;">
<div style="margin-left:15px;">
<label id="FromTime" style="margin-left:15px;" class="mandatory-lbl">From Time</label>
<span style="margin-left:20px;width:85px;"><input  type="text" style="width:85px;" id="dteFromTime" class="easyui-datebox" /></span>
<span class="spinner"><input  id="spnFromTime" name="spnFromTime" value="" class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" /></span>
</div>	
<div style="margin-left:15px;margin-top:15px;">
<label id="ToTime" style="margin-left:15px;" class="mandatory-lbl">To Time</label>
<span style="margin-left:36px;width:85px;"><input style="margin-left:10px;width:85px;" type="text" id="dteToTime" class="easyui-datebox"/></span>
<span class="spinner"><input  id="spnToTime" name="spnToTime" value="" class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" /></span>
</div>
<div style="margin-left:15px;margin-top:15px;">
<label id="Duration" style="margin-left:15px;" class="mandatory-lbl">Duration</label>
<span style="margin-left:22px;"><input style="margin-left:10px;width:115px;width:100px\9;" type="text" id="txtDuration" class="easyui-text" /></span>
<span style="margin-left:5px"><input type="checkbox" id="chkDeleteNoPlan" name="chkDeleteNoPlan"/><label id="delete" style="margin-left:5px;">Delete</label></span>
</div>

<div style="margin-left:60px;margin-top:15px;">
<input type="button" id="btnNoPlanSave" style="height:20px;" class="easyui-button" value="Ok"/>
<input type="button" id="btnPCSView" style="height:20px;margin-left:20px;" class="easyui-button" value="View PCS"/>
</div>
</div>
<input type="hidden" id="hdnFromDatePopup" value="${requestScope.fromDate}"/>
<input type="hidden" id="hdnToDatePopup" value="${requestScope.toDate}"/>
<input type="hidden" id="hdnFromTime" value="${requestScope.fromTime}"/>
<input type="hidden" id="hdnToTime" value="${requestScope.toTime}"/>
<input type="hidden" id="hdnMchIdPopup" value="${requestScope.mchId}"/>
<input type="hidden" id="hdnShiftPopup" value="${requestScope.shift}"/>
<input type="hidden" id="hdnDatePopup" value="${requestScope.date}"/>
<input type="hidden" id="hdnDuration" value="${requestScope.duration}"/>
<input type="hidden" id="hdnSftStrttime" value="${requestScope.ShiftstrtTime }"/>
<input type="hidden" id="hdnSftEndtime" value="${requestScope.ShiftEndTime}"/>
<input type="hidden" id="hdnSftStrttimeS" value="${requestScope.ShiftstrtTimeS }"/>
<input type="hidden" id="hdnSftEndtimeS" value="${requestScope.ShiftEndTimeS}"/>
<input type="hidden" id="hdnpartialNoplanSatus" value="${requestScope.partialNoplanSatus}"/>
</form>