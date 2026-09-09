<script>
jQuery(document).ready(function(){//alert(1);
	initialiseForm('frmNewAttendaceReprt');
	formatDateBox('dteFromDate','dd-MMM-yyyy');
	formatDateBox('dteToDate','dd-MMM-yyyy');

	var type=jQuery('#hdntype').val();
    
    if(type.trim().length<=0){
       jQuery('#MeetingType').hide();
       jQuery('#Pillar').hide();
       jQuery('#View').hide();
     }
	
    
    setFieldValue("cmbMomaMeetingtype", "D");
    fillComboBox("frmNewAttendaceReprt","cmbMomaMeetingtype","MeetingAttType.mom","",false);
    fillComboBox("frmMom","cmbMomaPillar","pillar.commonFilter");
    
    readOnlyFields("cmbMomaPillar");
    
    //processGridnew("momattendancereport_input.mom","q=2","attendancereportgrid","pager","","docDoubleClick","","momattendanceOnCompleteload");

	var factId = jQuery("#frmNewAttendaceReprt input[id='factory']").val();
    var sectionId = jQuery("#frmNewAttendaceReprt input[id='section']").val();
    var cellId = jQuery("#frmNewAttendaceReprt input[id='cell']").val();
    var machId = jQuery("#frmNewAttendaceReprt input[id='machine']").val();
    var flId = jQuery("#frmNewAttendaceReprt input[id='flid']").val();
    var dataStr = "&factId=" + factId + "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId + "&flid=" + flId;
    
	loadFunctionalLocation("AtendnceReprtLocation", "functionalLoc.mom", "AtendnceReprtLocation", "frmNewAttendaceReprt",dataStr);
    //alert(2);
	setLoadFormCallBackFrmId("frmNewAttendaceReprt");
	invokeAfterLoadFormCallBack();
	
	jQuery("#btnview").click(function()
	 {
		
		var Mettingtype=jQuery("#cmbMomaMeetingtype").combobox("getText");
		var PillarId=getFieldValue("cmbMomaPillar", "frmNewAttendaceReprt");
		
		var filterString = jQuery('#hdnFilterString').val();
		
		viewGrid("newmomattendancereport_input.mom", filterString);

     }); 


});


function frmNewAttendaceReprtcmbMomaMeetingtype_onSelect(record)
{
	var filterString = jQuery('#hdnFilterString').val();
	
	var meetingtype = getFieldValue('cmbMomaMeetingtype');
	if(meetingtype=="P")
	{
	 enableFields("cmbMomaPillar");
	}else if(meetingtype=="D")
	{
		readOnlyFields("cmbMomaPillar");
		clearField("cmbMomaPillar");
		
	}else if(meetingtype=="PD")
	{
		readOnlyFields("cmbMomaPillar");
		clearField("cmbMomaPillar");
	}
}
function frmNewAttendaceReprt_FuntLocHierarchy_SuccessCallBack(result) {

	var cellId=result.cellId;
    var flId=result.flId;

	viewGrid("newmomattendancereport_input.mom",'?q=2&cellId=' + cellId+'&flid='+flId);
	          
}


function validateFilterSelection(filterString){
	return  true;
}


function viewGrid(url,filterString)
{     
	jQuery('#hdnFilterString').val(filterString);
   	if( validateFilterSelection(filterString))
	{   
		var flid = getFilterValue(filterString, 'flid');
		var FromDte = getFilterValue(filterString, "dtFromDate");
		var ToDte = getFilterValue(filterString, "dtToDate");
		//daydiff(FromDte,ToDte);
		var Frommonth = getFilterValue(filterString, "dtFromMonth");
		var Tomonth = getFilterValue(filterString, "dtToMonth");
	    if (Frommonth.trim().length==0 && Tomonth.trim().length==0  && FromDte.trim().length==0  && ToDte.trim().length==0){
		    //alert ("Select Date Monthwise Or Daywise");
		    /*toggleCommonFilter();
		    return false;*/
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
					//if (compareFromToDate(dtFromDate,dtToDate)==false)
						//return;
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
	   
		
	    var type=jQuery('#hdntype').val();
	    var meetingtype = getFieldValue('cmbMomaMeetingtype');
	   // alert("meetingtype"+meetingtype);
	    var Mettingtype=jQuery("#cmbMomaMeetingtype").combobox("getText");
	    //alert(meetingtype);
		var PillarId=getFieldValue("cmbMomaPillar", "frmNewAttendaceReprt");
		
	    if(type.trim().length>0){
	    	filterString += '&type='+type;
	    }else
	    {
	    	filterString += '&meetingtype=J';
	    }
	    	     
	    if(meetingtype=="D"||meetingtype=="PD")
	    	filterString += '&meetingtype='+meetingtype;
	    else if(meetingtype=="P" && PillarId=="TGT001"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT002"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT003"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT004"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT005"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT006"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT007"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="P" && PillarId=="TGT008"){
	    	filterString += '&meetingtype='+meetingtype;
	    	filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="UMC"){
	    	filterString += '&meetingtype='+meetingtype;
	    	//filterString += '&PillarId='+PillarId;
	    }
	    else if(meetingtype=="OGM"){
	    	filterString += '&meetingtype='+meetingtype;
	    	//filterString += '&PillarId='+PillarId;
	    }
		filterString += '&flid='+flid;	
		var flg=true;		
		//flg=filterMonthnDateDifference(filterString,30,1);
		//alert("flg:"+flg);
		if (flg==false){toggleCommonFilter();return false;}
		//alert(filterString);
		//alert("url>>>"+url);
		var tableCaption = "Mom Attendance Report";
		processGridnew(url,filterString,"newattendancereportgrid","pager",tableCaption);		
		return true; 
	}
	
	return false;
}


function getCommonFilterValues()
{
	
	var dataString="?q=2";

	if(jQuery("#company").length > 0){
	var cmbCompid = jQuery("#company").val();
	dataString = "&cmbCompid="+cmbCompid;
	}
	var cmbLocnid = jQuery("#location").val();
	dataString += "&cmbLocnid="+cmbLocnid;
	
	var cmbFactid = jQuery("#factory").val();
	dataString += "&cmbFactid="+cmbFactid;
	
	var cmbSbuid = jQuery("#sbu").val();
	dataString += "&cmbSbuid="+cmbSbuid;
	
	var cmbPbuid = jQuery("#pbu").val();
	dataString += "&cmbPbuid="+cmbPbuid;
	
	var cmbSectid = jQuery("#section").val();
	dataString += "&cmbSectid="+cmbSectid;

	var cmbCostCenter = jQuery("#cmbCostCenter").combobox('getValue');
	dataString += "&cmbCostCenter="+cmbCostCenter;
	
	var cmbCircle = jQuery("#cmbCircle").combobox('getValue');
	dataString += "&cmbCircle="+cmbCircle;
	
	var cmbCellid = jQuery("#cell").val();;
	dataString += "&cmbCellid="+cmbCellid;
	
	var cmbMchid = jQuery("#cmbEquipmentid").combobox("getValue");	
	dataString += "&cmbMchid="+cmbMchid;
	
	var flid = jQuery("#flid").val();;
	dataString += "&flid="+flid;
	
	
	var dtFromDate = jQuery('#dtefromDate').datebox('getValue');		
	dataString += "&dtFromDate="+dtFromDate;
	
	var dtToDate = jQuery('#dtetoDate').datebox('getValue');
	dataString += "&dtToDate="+dtToDate;
	
	var dtFromMonth = jQuery('#dtefromMonth').datebox('getValue');
	dataString += "&dtFromMonth="+dtFromMonth;
	
	var dtToMonth = jQuery('#dtetoMonth').datebox('getValue');
	dataString += "&dtToMonth="+dtToMonth;
	
	var year = jQuery('#dteyear').datebox('getValue');
	dataString += "&year="+year;

	var cmbAssmbid = jQuery("#cmbassembly").combobox("getValue");	
	dataString += "&cmbAssmbid="+cmbAssmbid;
	
	var cmbEqpGrpid = jQuery("#cmbeqpGroup").combobox("getValue");	
	dataString += "&cmbEqpGrpid="+cmbEqpGrpid;
	

	var cmbEqpSubGrp=jQuery("#cmbEqpSubGrp").combobox("getValue");
	dataString += "&cmbEqpSubGrp="+cmbEqpSubGrp;
	

	var cmbMchRnkid = jQuery("#cmbMachineRank").combobox("getValue");	
	dataString += "&cmbMchRnkid="+cmbMchRnkid;
	
	var cmbTradeid = jQuery("#cmbTrade").combobox("getValue");	
	dataString += "&cmbTradeid="+cmbTradeid;

	var chkSkipLine = getChkBoxVal("chkSkipLine");
	
	/*var cboRelatedTo = jQuery("#cboRelatedTo").val();
	dataString += "&cboRelatedTo="+cboRelatedTo;
	
	var cmbMould = jQuery("#cmbMould").combobox("getValue");	
	dataString += "&cmbMould="+cmbMould;*/
	
		
	dataString += "&chkMonthwise="+getChkBoxVal('chkMonthwise');
	dataString += "&chkDatewise="+getChkBoxVal('chkDatewise');
	dataString += "&skipLine="+ (chkSkipLine == "1" || chkSkipLine == 1 ? 'Y':'N') ;
	dataString += "&firstClick=Y";
	
	var parentId = getParentId();
	parentId = parentId.trim();


	var multipleval=jQuery("#checkedvalues").val();
	dataString += "&multipleval="+multipleval;

	var checkedtype=jQuery("#checkedtype").val();
	dataString += "&checkedtype="+checkedtype;
	
	if( parentId != null && parentId!= undefined && parentId != 'undefined' && parentId.length > 0 && parentId != "" )		
		dataString += "&parentId="+parentId;

	//alert(dataString);
	return dataString;
}
function daydiff(first, second) {
	 
	 var days = parseInt(second.substring(0,2)) - parseInt(first.substring(0,2));
	 //alert(" days :: "+((days+1)));
	 jQuery('#noofdays').val(days);
	 return days;
}

/*function validateFilterSelection(filterString){
	
	var type=jQuery('#hdntype').val();
    
	if(filterString=="?q=2")
		return true;

	if(getFilterValue(filterString, "flid") == "" && type.trim().length<=0){
		alert(" Select JH ");
		return false;
	}
	
	var Frommonth = getFilterValue(filterString, "dtFromMonth");
	alert("From month"+Frommonth);
	var Tomonth = getFilterValue(filterString, "dtToMonth");
	alert("To month"+Tomonth);
    return compareFromToOne(Frommonth,Tomonth,3);
	
}*/
           
function frmNewAttendaceReprt_afterLoadCallBack(){
	
		toggleCommonFilter();	
		
}

function setattendanceReport(id, options, rowObject) {
	if ( options.pos <= 11) 
		return id;
	
    if(id== "P"){ 
	     return "<div style='background-color:white;width:70px;text-align:center;'>&#10003;</div>" ;
 	}
	else if(id== "D"){ 
	     return "<div style='background-color:pink;width:70px;text-align:center;'>D</div>" ;
	}
	else if(id== "A"){ 
	     return "<div style='background-color:red;width:70px;text-align:center;'>A</div>" ;
	}
	else if(id== "W"){ 
         return "<div style='background-color:grey;width:70px;text-align:center;'>W</div>" ; 
	}
	else if(id== "L"){
	     return "<div  style='background-color:yellow;width:70px;text-align:center;'>L</div>" ;  
	}else {
		 return "<div  style='background-color:skyblue;width:70px;text-align:center;'>N</div>" ;
    }
}



function frmMomcmbMomsMeetingtype_onSelect(record)
{
	
 meetingemployeetype(record.id);
   
}


function meetingemployeetype(id){
	 
	
	var Mettingtype=id;
	var flid =jQuery("#frmMom input[id='flid']").val();
	var cellId = jQuery("#frmMom input[id='cell']").val();
	var momdate = getFieldValue("dteMomsDate", "frmMom");
	var keyid=jQuery('#txtMomsKeyid').val();
	//alert(" :: keyid :: "+keyid);
	
    if(Mettingtype=="P"){
    	jQuery('#pillarlblid').addClass("mandatory-lbl");
    	enableFields("cmbMomsPillarid");
	 }else{
	     jQuery('#pillarlblid').removeClass("mandatory-lbl");
		 readOnlyFields("cmbMomsPillarid");
		 clearField("cmbMomsPillarid"); 
	 }
    
	if(Mettingtype=="J")
	{
	  jQuery('#SafetyTalk').addClass("mandatory-lbl");
	}else
	  jQuery('#SafetyTalk').removeClass("mandatory-lbl");
	  if(Mettingtype=="D"){
	     meetingtype();
	  }
		
	if(keyid.trim().length<=0){
	  if(Mettingtype=="J")
		{
		   processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			
		}else if(Mettingtype=="D")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			
		}else if(Mettingtype=="FIP")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
		}
		else if(Mettingtype=="PD")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
		}else if(Mettingtype=="DEC"||Mettingtype=="CEC"||Mettingtype=="O")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
		else if(Mettingtype=="UMC")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=UMC"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
		}
		else if(Mettingtype=="OGM")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OGM"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
		}
	  
	  }else
		  {
		  
		  if(Mettingtype=="J")
			{
			   processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				
			}else if(Mettingtype=="D")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				
			}else if(Mettingtype=="FIP")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
			else if(Mettingtype=="PD")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
			else if(Mettingtype=="UMC")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=UMC"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
			else if(Mettingtype=="OGM")
			{
				processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OGM"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
		  }
}

</script>
<form name="frmNewAttendaceReprt" id="frmNewAttendaceReprt" action=" " method="post">
<div id="wrapperRpt">
<table>
	
	<tr>
	    <td>
			    <div id="MeetingType">
					<div>
					     <label>Meeting Type</label>
					</div>
					<div>
					     <input class="easyui-combobox" id="cmbMomaMeetingtype" name="cmbMomaMeetingtype"  style=" width : 160px;"  value="${requestScope.genTlMommst.momsMeetingtype}"/>
					</div>
				</div>
			</td>
	
            <td>
			    <div id="Pillar" style="padding-left:10px;">
					<div>
					     <label>Pillar</label>
					</div>
					<div>
					     <input class="easyui-combobox" id="cmbMomaPillar" name="cmbMomaPillar"  style=" width : 220px;"  value="" />
					</div>
				</div>
			</td>
	
	       <td>
			    <div id="View" style="margin-top:10px;padding-left:10px;">
					<div>
					     <input class="easyui-button" type="button" id="btnview" name="btnview"  style="width:60px;"  value="View" />
					</div>
				</div>
			</td>
		<td style="padding-left:0px;" colspan="8">
		    <div style="margin-left:20px;">
			    <div style="background-color: white;font-weight:bold;margin-left:-6px;margin-top:-10px;width:360px;">
				<label> Attendance %  = No. of Present / No. of Meetings * 100 </label>
				</div>
			
				<div style="padding-top:10px;width:560px;">
					<span style=" display:none;"><input type="checkbox" id="chkCat" name="chkshow" value="" /><label>Show only meeting planned days</label></span>
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
<div>
	<table id='newattendancereportgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
</div>
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="${requestScope.filterString}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>

</form>