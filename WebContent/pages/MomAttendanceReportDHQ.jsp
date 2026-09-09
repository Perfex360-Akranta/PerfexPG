<script>
jQuery(document).ready(function(){//alert(1);
	initialiseForm('frmAttendaceReprt');
	formatDateBox('dteFromDate','dd-MMM-yyyy');
	formatDateBox('dteToDate','dd-MMM-yyyy');

	var type=jQuery('#hdntype').val();
    
    if(type.trim().length<=0){
       jQuery('#MeetingType').hide();
       jQuery('#Pillar').hide();
       jQuery('#View').hide();
     }
	
    
    setFieldValue("cmbMomaMeetingtype", "D");
    fillComboBox("frmAttendaceReprt","cmbMomaMeetingtype","MeetingAttType.mom","",false);
    fillComboBox("frmMom","cmbMomaPillar","pillar.commonFilter");
    
    readOnlyFields("cmbMomaPillar");
    
    //processGridnew("momattendancereportDHQ_input.mom","q=2","attendancereportgrid","pager","","docDoubleClick","","momattendanceOnCompleteload");

	var factId = jQuery("#frmAttendaceReprt input[id='factory']").val();
    var sectionId = jQuery("#frmAttendaceReprt input[id='section']").val();
    var cellId = jQuery("#frmAttendaceReprt input[id='cell']").val();
    var machId = jQuery("#frmAttendaceReprt input[id='machine']").val();
    var flId = jQuery("#frmAttendaceReprt input[id='flid']").val();
    var dataStr = "&factId=" + factId + "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId + "&flid=" + flId;
    
	loadFunctionalLocation("AtendnceReprtLocation", "functionalLoc.mom", "AtendnceReprtLocation", "frmAttendaceReprt",dataStr);
    //alert(2);
	setLoadFormCallBackFrmId("frmAttendaceReprt");
	invokeAfterLoadFormCallBack();
	
	jQuery("#btnview").click(function()
	 {
		
		var Mettingtype=jQuery("#cmbMomaMeetingtype").combobox("getText");
		var PillarId=getFieldValue("cmbMomaPillar", "frmAttendaceReprt");
		
		var filterString = jQuery('#hdnFilterString').val();
		
		viewGrid("momattendancereportDHQ_input.mom", filterString);
		
       /* if(Mettingtype=="J")
		{
		   processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			
		}else if(Mettingtype=="D")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			
		}else if(Mettingtype=="PD")
		{
			processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
		}*/

     }); 


});

/*
function frmFilter_enableDisableSuccessCallBack()
{alert(1);
	fillWithCurrentMonth("dtefromMonth");
	fillWithCurrentMonth("dtetoMonth");
}
*/

function frmAttendaceReprtcmbMomaMeetingtype_onSelect(record)
{
	var filterString = jQuery('#hdnFilterString').val();
	//alert(" filterString :: 12 "+filterString);
	//viewGrid ("momattendancereportDHQ_input.mom", filterString);
	
	
	
	//var meetingtype = getFieldValue('cmbMomaMeetingtype');
	var meetingtype = record.id;
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
	
	//var flId = jQuery("#frmAttendaceReprt input[id='flid']").val();
    //processGridnew("momattendancereport_input.mom","?q=2&meetingtype="+record.id,"attendancereportgrid","pager","","docDoubleClick","","momattendanceOnCompleteload");

}
function frmAttendaceReprt_FuntLocHierarchy_SuccessCallBack(result) {

	var cellId=result.cellId;
    var flId=result.flId;

	viewGrid("momattendancereportDHQ_input.mom",'?q=2&cellId=' + cellId+'&flid='+flId);
	          
}

function viewGrid(url,filterString)
{     
	jQuery('#hdnFilterString').val(filterString);
   	if( validateFilterSelection(filterString))
	{   
		var flid = getFilterValue(filterString, 'flid');
		var FromDte = getFilterValue(filterString, "dtFromDate");
		var ToDte = getFilterValue(filterString, "dtToDate");
		daydiff(FromDte,ToDte);
		var Frommonth = getFilterValue(filterString, "dtFromMonth");
		var Tomonth = getFilterValue(filterString, "dtToMonth");
	    //alert(Frommonth+Tomonth);
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
						//else if (compareFromToMonth(dtFromMonth,dtToMonth)==false)
						//	return;
			 			
					}
			var breakup = jQuery('#hdnbreakup').val();
			var datastring = getCommonFilterValues();
			
			filterString += datastring+"&BREAKUP="+breakup;
		}
	   
		
	    var type=jQuery('#hdntype').val();
	    var meetingtype = getFieldValue('cmbMomaMeetingtype');
	    
	    var Mettingtype=jQuery("#cmbMomaMeetingtype").combobox("getText");
		var PillarId=getFieldValue("cmbMomaPillar", "frmAttendaceReprt");
		
	    //if(type.trim().length>0){
	    //	filterString += '&type='+type;
	   // }else
	   // {
	    //	filterString += '&meetingtype=J';
	   // }
	    	     
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
		filterString += '&flid='+flid;	
		var flg=true;		
		flg=filterMonthnDateDifference(filterString,30,1);
		//alert("flg:"+flg);
		if (flg==false){toggleCommonFilter();return false;}
		//alert(filterString);
		var tableCaption = "Mom Attendance Report";
		processGridnew(url,filterString,"attendancereportgrid","pager",tableCaption);		
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
	
	if(cmbSectid && cmbSectid != "0" && (!cmbCellid || cmbCellid == "0"))
	{ 
		jQuery("#cmbMomaMeetingtype").combobox("setValue", "D");
	}
	if(cmbSectid && cmbSectid != "0" && cmbCellid && cmbCellid != "0")
	{
		jQuery("#cmbMomaMeetingtype").combobox("setValue", "J");
	}

	//alert(dataString);
	return dataString;
}
function daydiff(first, second) {
	 
	 var days = parseInt(second.substring(0,2)) - parseInt(first.substring(0,2));
	 //alert(" days :: "+((days+1)));
	 jQuery('#noofdays').val(days);
	 return days;
}

function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
	
	var type=jQuery('#hdntype').val();
    
	if(filterString=="?q=2")
		return true;

	if(getFilterValue(filterString, "flid") == "" && type.trim().length<=0){
		alert(" Select JH ");
		return false;
	}
	/*if(getFilterValue(filterString, "dtFromDate") == "" ){
		alert(" Select From Date ");
		return false;
	}
	if(getFilterValue(filterString, "dtToDate") == "" ){
		alert(" Select To Date ");
		return false;
	}*/
	
	var Frommonth = getFilterValue(filterString, "dtFromMonth");
	var Tomonth = getFilterValue(filterString, "dtToMonth");
	
    
    return compareFromToMonth(Frommonth,Tomonth,1);
	
}
           
function frmAttendaceReprt_afterLoadCallBack(){
	
		toggleCommonFilter();	
		
}

function setattendanceReport(id, options, rowObject) {
	if ( options.pos <= 10) 
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
		  }
}


/*function momattendanceOnCompleteload() {//alert(1);

	 var row = jQuery("#attendancereportgrid").jqGrid('getDataIDs');
	 var cm = jQuery("#attendancereportgrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		
		 for(var j=5;j<cm.length;j++)
   		 {  
	   		   
	    	   var zeroVal = jQuery("#attendancereportgrid").jqGrid('getCell',row[i],cm[j].name);
	    	 
			   if(zeroVal=='10'){
					jQuery("#attendancereportgrid").setCell(row[i], cm[j].name.trim(), "&#10003;",{'background-color':'white'});
	   	 	   }
			   else if(zeroVal=='12'){
					jQuery("#attendancereportgrid").setCell(row[i], cm[j].name.trim(), "A",{'background-color':'red'});	
	   	 	   }
			   else if(zeroVal==' '){
					jQuery("#attendancereportgrid").setCell(row[i], cm[j].name.trim(), " ",{'background-color':'grey'});	
			   }
			   else if(zeroVal=='14'){
					jQuery("#attendancereportgrid").setCell(row[i], cm[j].name.trim(), "L",{'background-color':'YELLOW'});	
			   }	
			   else if(zeroVal=='15'){
					jQuery("#attendancereportgrid").setCell(row[i], cm[j].name.trim(), "N",{'background-color':'#AFD6FE'});	
			   }		
   	 	  }
	 }
	 
}*/

</script>
<form name="frmAttendaceReprt" id="frmAttendaceReprt" action=" " method="post">
<div id="wrapperRpt">
<!--<table>-->
<!--  <tr>-->
<!--  <td colspan="3">-->
<!--  <div> <div id="frmmomFuntKeyIds">-->
<!--			<input type="hidden" id="factory" name="cmbmomfactory" value=""></input> -->
<!--			<input type="hidden" id="section" name="cmbmomsection" value=""></input> -->
<!--			<input type="hidden" id="cell"    name="cmbmomcell" value=""></input> -->
<!--			<input type="hidden" id="machine" name="cmbmommachine" value=""></input>-->
<!--			<input type="hidden" id="flid"    name="cmbmomFlid" value=" "></input>-->
<!--	</div>-->
<!--	<div  class="easyui-paddingbfpx" id="AtendnceReprtLocation" style="width:820px;"></div>-->
<!---->
<!--  </div>-->
<!--  </td>-->
<!--  -->
<!--	  </tr>-->
<!--	  </table>-->

<table>
	
	<tr>
	    <td>
			    <div id="MeetingType">
					<div>
					     <label>Meeting Type</label>
					</div>
					<div>
					     <input class="easyui-combobox" id="cmbMomaMeetingtype" name="cmbMomaMeetingtype"  style=" width : 160px;"  value="" />
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
	<table id='attendancereportgrid'>
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