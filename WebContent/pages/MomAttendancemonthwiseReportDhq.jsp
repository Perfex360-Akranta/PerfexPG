<script>
jQuery(document).ready(function(){//alert(1);
   initialiseForm('frmAttendacemnthwseReprt');
   //toggleCommonFilter();
    setFieldValue("cmbMomaMthMeetingtype", "J");
    fillComboBox("frmAttendacemnthwseReprt","cmbMomaMthMeetingtype","MeetingAttType.mom?&type=monthwse","",false);
    fillComboBox("frmAttendacemnthwseReprt","cmbMomaMthPillar","pillar.commonFilter");
    readOnlyFields("cmbMomaMthPillar");
    setLoadFormCallBackFrmId("frmAttendacemnthwseReprt");
    invokeAfterLoadFormCallBack();

    var url = jQuery('#hiddenUrl').val();
    viewGrid(url,filterString);
    
});


jQuery("#btnmonthview").click(function()
 {
   	var filterString = jQuery('#hdnFilterString').val();
   	var url = jQuery('#hiddenUrl').val();
   	viewGrid(url,filterString);
 }); 
	     
function frmAttendacemnthwseReprtcmbMomaMthMeetingtype_onSelect(record)
{
	
	var meetingtype = record.id;

	if(meetingtype=="P")
	{
	 enableFields("cmbMomaMthPillar");
	}else if(meetingtype=="D")
	{
		readOnlyFields("cmbMomaMthPillar");
		clearField("cmbMomaMthPillar");
		
	}else if(meetingtype=="PD")
	{
		readOnlyFields("cmbMomaMthPillar");
		clearField("cmbMomaMthPillar");
	}
	
}

function frmAttendacemnthwseReprt_afterLoadCallBack(){
	toggleCommonFilter();	
}
function viewGrid(url,filterString)
{     
	jQuery('#hdnFilterString').val(filterString);
	if( validateFilterSelection(filterString))
	{   
		var flid = getFilterValue(filterString, 'flid');
		//var cellid = getFilterValue(filterString, 'cmbCellid');
		//var flid = getFilterValue(filterString, 'cmbSectid');
		jQuery('#hdnflid').val(flid);
		var FromDte = getFilterValue(filterString, "dtFromDate");
		var ToDte = getFilterValue(filterString, "dtToDate");
		var Frommonth = getFilterValue(filterString, "dtFromMonth");
		var Tomonth = getFilterValue(filterString, "dtToMonth");
		if (Frommonth.trim().length==0 && Tomonth.trim().length==0  && FromDte.trim().length==0  && ToDte.trim().length==0){
		    return false;
			var dtFromDate = jQuery('#dtefromDate').datebox('getValue');
			var dtToDate = jQuery('#dtetoDate').datebox('getValue');
			var dtFromMonth = jQuery('#dtefromMonth').datebox('getValue');
			var disFromDate = jQuery('#chkMonthwise').is(':disabled');
			var dtToMonth = jQuery('#dtetoMonth').datebox('getValue');
			
				if(jQuery('#chkDatewise').is(':checked') == true)
				{
					var fromDate = dtFromDate;
					var	ToDate = dtToDate;		
					if(fromDate == '' && !jQuery('#dtefromDate').is(':disabled'))
					{
						alert("Select From Date");
						return;
					}
					else if(ToDate == '' && !jQuery('#dtetoDate').is(':disabled'))
					{
						alert("Select To Date");
						return;
					}
					else if( compareDate( fromDate,ToDate) == -1 && !jQuery('#dtetoDate').is(':disabled'))
					{	
						alert('To Date can not be less than From Date');
						return ;
					}
				}
					if(jQuery('#chkMonthwise').is(':checked') == true){
						
						var fromMonth = "01-"+dtFromMonth;
						var	ToMonth = "01-"+dtToMonth;		
						if(dtFromMonth == '' && !jQuery('#dtefromMonth').is(':disabled'))
						{
							alert("Select From Month");
							return;
						}
						else if(dtToMonth == '' && !jQuery('#dtetoMonth').is(':disabled'))
						{
							alert("Select To Month");
							return;
						}
						else if( compareDate( fromMonth,ToMonth) == -1)
						{	
							 if( !jQuery('#dtefromMonth').is(':disabled') && !jQuery('#dtetoMonth').is(':disabled')  ){
								alert('To Month can not be less than From Month');
								return ;
							 }
						}
					}
			var breakup = jQuery('#hdnbreakup').val();
			var datastring = getCommonFilterValues();
			filterString += datastring+"&BREAKUP="+breakup;
		}
		
		//var cellid = getFilterValue(filterString, 'cmbCellid');
		//var sectid = getFilterValue(filterString, 'cmbSectid');
		var meetingtype = getFieldValue('cmbMomaMthMeetingtype');
		var pillarId=getFieldValue("cmbMomaMthPillar","frmAttendacemnthwseReprt");
		//if(cellid.trim().length>0)
		    //filterString +="&type=J";
		//else if(sectid.trim().length>0)
			//filterString +="&type=D";
		//else{
			if(meetingtype=="J"||meetingtype=="D"||meetingtype=="PD")
		    	filterString += '&type='+meetingtype;
		    else if(meetingtype=="P" && pillarId.trim().length>0){
		    	filterString += '&type='+meetingtype;
		    	filterString += '&pillarId='+pillarId;
		    }
	    //}
		
		filterString += '&flid='+flid;	
	    var tableCaption = "Mom Attendance Month Wise Report";
		processGridnew(url,filterString,"attendancemonthwisereportgrid","pagermonthwise",tableCaption,"docDoubleClick","","loadComFunction");		
		return true; 
	}
	
	return false;
}

function loadComFunction(id){
	jQuery("#attendancemonthwisereportgrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
		jQuery('#getiCol').val(iCol);	
	}});
}

/*function docDoubleClick(id)
{	
	
	var rowData = jQuery("#attendancemonthwisereportgrid").jqGrid('getRowData',id);
	var iCol = jQuery('#getiCol').val();
	var colm = jQuery("#attendancemonthwisereportgrid").jqGrid ('getGridParam','colModel');
	var month = colm[iCol].name;
	var flid=jQuery('#hdnflid').val();
	
    var ds ='?types=JH&month='+month+'&flid='+flid;

    navigateToNextForm('momattendancereport_input.mom'+ds,"JH Mom Attendance Report");
		
	//jQuery('#frmAttendacemnthwseReprt').hide();
	//navigateToNextForm('momattendancereport_input.mom',"JH Mom Attendance Report",null,persistentData);
	
}
*/

function validateFilterSelection(filterString){
	return true;
}

</script>

<form name="frmAttendacemnthwseReprt" id="frmAttendacemnthwseReprt" action=" " method="post">

<table>
	<tr>
	<td>
	    <div style="padding-left:40px;" id="MeetingType">
			<div>
			     <label>Meeting Type</label>
			</div>
			<div>
			     <input class="easyui-combobox" id="cmbMomaMthMeetingtype" name="cmbMomaMthMeetingtype"  style=" width : 160px;"  value="" />
			</div>
		</div>
	</td>
    <td>
	    <div id="Pillar" style="padding-left:10px;">
			<div>
			     <label>Pillar</label>
			</div>
			<div>
			     <input class="easyui-combobox" id="cmbMomaMthPillar" name="cmbMomaMthPillar"  style=" width : 220px;"  value="" />
			</div>
		</div>
	</td>
	
	     <td>
	    <div id="View" style="margin-top:10px;padding-left:10px;">
			<div>
			     <input class="easyui-button" type="button" id="btnmonthview" name="btnmonthview"  style="width:60px;"  value="View" />
			</div>
		</div>
	</td>
	<td style="padding-left:0px;display:none;" colspan="8">
	    <div style="margin-left:40px;">
		    <div style="background-color: white;font-weight:bold;margin-left:-6px;margin-top:10px;width:360px;">
			<label> Attendance %  = No. of Present / No. of Meetings * 100 </label>
			</div>
		
			<div style="padding-top:10px;width:560px;">
				<span style="display:none;"><input type="checkbox" id="chkCat" name="chkshow" value="" /><label>Show only meeting planned days</label></span>
				<span style="padding-left:0px;"><input type="text" id='Present' style="background-color: white;text-align: center;width:20px;height:20px;text-align: center;" value="&#10003;" disabled="disabled" /><label style="padding-left:10px;">Present</label></span>
				<span style="padding-left:10px;"><input type="text" id='Weekly' style="background-color: grey;width:20px;height:20px;text-align: center;" value="W" disabled="disabled" /><label style="padding-left:10px;">Weekly Off</label></span>
				<span style="padding-left:10px;"><input type="text" id='Absent' style="background-color: red;width:20px;height:20px;text-align: center;" value="A"  disabled="disabled"/><label style="padding-left:10px;">Absent</label></span>
				<span style="padding-left:10px;"><input type="text" id='Leave' style="background-color: yellow;width:20px;height:20px;text-align: center;" value="L" disabled="disabled"/><label style="padding-left:10px;">Leave</label></span>
				<span style="padding-left:10px;"><input type="text" id='Not' style="background-color: #AFD6FE;width:20px;height:20px;text-align: center;" value="N" disabled="disabled" /><label style="padding-left:10px;">Not Planned</label></span>
				<span style="padding-left:10px;"><input type="text" id='On' style="background-color: pink;width:20px;height:20px;text-align: center;" value="D" disabled="disabled" /><label style="padding-left:10px;">On-Duty</label></span>
			</div>
		</div>	
	</td>
	</tr>
</table> 
<div style="margin-left:40px;">
	<table id='attendancemonthwisereportgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagermonthwise'></div>
</div>
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="${requestScope.filterString}"/>
<input type="hidden" id="getiCol" name="getiCol" value=""/>
<input type="hidden" id="hdnflid" name="hdnflid" value=""/>
</form>