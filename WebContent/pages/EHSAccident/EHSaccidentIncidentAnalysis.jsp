<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	
	jQuery(document).ready(function(){	
		initialiseForm('frmAccidentIncident');	    	  
	    jQuery('#submitForm').val('frmAccidentIncident'); 
	 
	 
		
		formatDateBox('dtesinmAnalysedDate','dd-MMM-yyyy');
		fillComboBox("frmAccidentIncident","cmbsinmAccidentType","IncidentTypeCombo.accIncRpt");
		fillComboBox("frmAccidentIncident","cmbsinmAnalysedShift","combo_shift.brdn");
		fillComboBox("frmAccidentIncident","cmbsinmAnalysedBy","employee.commonFilter");
		fillComboBox("frmAccidentIncident","cmbsinmExactType","IncidentTypeCombo.accIncRpt");
		formatDateBox('dtesinmAnalysedDate','dd-MMM-yyyy');	
		spinnerKeyPress('spnsinmAnalysedtime');			
		spinnerChange('spnsinmAnalysedtime','analysedTimeSpnEvt');
		spinnerUp('spnsinmAnalysedtime','analysedTimeSpnEvt');
		spinnerDown('spnsinmAnalysedtime','analysedTimeSpnEvt');
		//alert("123"+jQuery("#hdntime").val());
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		//fillWithCurrentDate('dtesinmAnalysedDate');		
	//	alert("spnsinmAnalysedtime"+jQuery('#spnsinmAnalysedtime').spinner('getValue'));

			var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
			fillAnalysisShift();
			if(analysedDate != null && analysedDate != '' && analysedDate != ' ')
			{
				//setDatenTimeFlag('dtesinmAnalysedDate','spnsinmAnalysedtime');
				
			}
			
	 
	  		 disableUIButton('btnYYAccident');
	  		
	  	   var rootcause = jQuery('#hdnrootcause').val();
		   if(rootcause=="A")
			   {
			   jQuery('#chbUnsafeAct').attr('checked',true);
			   }
		   
		   if(rootcause=="C")
		   {
		   jQuery('#chbUnsafeCondn').attr('checked',true);
		   }


				
	});	
	function analysedTimeSpnEvt()
	{
		var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		var analysedTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
		
		//compareDates('dtesincReportedDate','spnsincReportedtime','dtesinmAnalysedDate','spnsincAnalysedtime','Analysed Date should not be lesser than Reported Date');
		if(analysedTime.indexOf(':') == 1)
			analysedTime = '0'+analysedTime;
		var dataString = '?q=2&';
	    	dataString += 'fromTime='+analysedTime;
	    	//alert(dataString);
		processAjaxCalls('txt_shift.brdn',dataString,'getAnalysedShift','getAnalysedShiftErr');	
	}
	
	function  getAnalysedShift(record)
	{       
		//alert("record.shift::"+record.shift);
		jQuery('#cmbsinmAnalysedShift').combobox('setValue',record.shift);     
// 		readOnlyFields('cmbsinmAnalysedShift');           	
	}
	function fillDefaultShift()
	{
		var fromTime = jQuery('#spnsinmOccurredtime').spinner('getValue');
		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&fromTime='+fromTime;
		processAjaxCalls('txt_shift.brdn',dataString,'fillShift','fillShiftErr');
	}
	function fillAnalysisShift()
	{
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		var fromTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
	//	alert("fromTime"+fromTime);
		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&fromTime='+fromTime;
	//	alert("dataString::"+dataString)
		processAjaxCalls('txt_shift.brdn',dataString,'getAnalysedShift','getAnalysedShiftErr');
	}
	function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
	{
		var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
	    var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
		var currentDate = getServerDateTime();
		if(convertStringToDate(toDate) > currentDate)
		{
			showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
			jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
			jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
		}
		else if( compareDateTime(toDate,fromDate) > 0 )
	    {
	    	showValidationErrorMsg(toDateId,errMsg);
	    	jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
			jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
	    }
	    else
	    	clearValidationErrorMsg(toDateId);	
	}
	function setDatenTimeFlag(dateId,timeId)
	{
		var dateFlag = jQuery('#'+dateId).datebox('getValue');
		//alert("dateFlag"+dateFlag);
		
		if(dateFlag != null && dateFlag != '' && dateFlag != ' ')
		{ 
			
			if(dateFlag == '01-Jan-1801 00:00:00')
			{
				if(dateId == 'dtesinmAnalysedDate')
				{
					//fillWithCurrentDate('dtesinmAnalysedDate');
					//fillWithCurrentDate('spnsinmAnalysedtime');
					fillAnalysisShift();
				}
				else
					jQuery('#'+dateId).datebox('clear');
			}
			else if(dateFlag == '31-Dec-2100 00:00:00')
				jQuery('#'+dateId).datebox('clear');			
			else
			{
				jQuery('#'+timeId).spinner('setValue',dateFlag.substring(dateFlag.indexOf(' ')+1,dateFlag.indexOf(' ')+6));
				jQuery('#'+dateId).datebox('setValue',dateFlag.substring(0,dateFlag.indexOf(' ')));
			}
		}
		
	}

	
	
	
	jQuery("#btnInvReport").click(function(){
			var documentNo =jQuery("#txtsinmKeyid").val();
	 		
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"INC","","","");
				
			}
			else
	 			alert("Incident No. should be selected to view FileManager");
			
		});

	

	jQuery("#txtsinmExactDesc").keypress(function(){
		 var tval = jQuery('#txtsinmExactDesc').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=1000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmIdea").keypress(function(){
		 var tval = jQuery('#txtsinmIdea').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmRootcause").keypress(function(){
		 var tval = jQuery('#txtsinmRootcause').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	

	
	jQuery("#btnYYAccident").click(function(){
		//saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?q=2&saveMode=YY');
		var refDocDate = jQuery('#hdnreportdate').val();
 		var refDocId = jQuery('#hdnkeyid').val();
 		var problem = jQuery('#hdnAccdesc').val();
 		var yyArea = jQuery("#hdnarea").val();
 		var flId= jQuery("#hdnflid").val();
 		//jQuery("#hdnYYArea").val(yyArea);
 		var attendby = jQuery('#hdnanalysedby').val();
 		var pillar = "EHS";
 		//alert('flId' + flId);
 		 openWhyWhy("",false,refDocId,"IRE",flId, refDocDate, problem, "create",null,null,attendby,yyArea,pillar);
	});
	jQuery('#chbUnsafeAct').click(function() {
		jQuery('#chbUnsafeAct').attr('checked',true);
		jQuery('#chbUnsafeCondn').attr('checked',false);
		jQuery('#hdnsinmRootcausedueto').val('A');		
	});
	jQuery('#chbUnsafeCondn').click(function() {
		jQuery('#chbUnsafeCondn').attr('checked',true);
		jQuery('#chbUnsafeAct').attr('checked',false);
		jQuery('#hdnsinmRootcausedueto').val('C');	
	});


	
	jQuery("#btnexcelview").click(function(){
		//alert("CLICK EXCEL");
        var incKeyid= jQuery('#hdnkeyid').val();
        //alert("Keyid"+incKeyid);
        var flid = jQuery("#hdnflid").val();
     //    alert("The Flid"+flid);
         if(incKeyid != null && incKeyid.length >0){
             window.open("Accincident_Excelview.ehsaccIncRpt?incKeyid="+incKeyid+"&flid="+flid);
         }
         else
        	 {
        	 return false;
        	 }
    });

	jQuery("#btncrtView").click(function(){
	    var keyid=jQuery("#hdnkeyid").val();
	   alert("keyid::"+keyid);
	 	var flid = jQuery("#hdnflid").val();
	 	var type=jQuery("hdntype").val();
	 	var Rolename=jQuery("hdnrolename").val();
	 	if(type=="ATY00006"&&Rolename=="SHIFT INCHARGE")
	 		{
	 		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	        }
	 	else if(type=="ATY00006"&&Rolename=="EHS PILLAR CHAMPION")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	 	else if(type=="ATY00007"&&Rolename=="EHS PILLAR CHAMPION")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	 	else if(type=="ATY00007"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	 	else if(type=="ATY00008"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	 	else if(type=="ATY00013"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00013"&&Rolename=="PBU HEAD")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00011"&&Rolename=="EHS PILLAR CHAMPION")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00012"&&Rolename=="EHS PILLAR CHAMPION")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00011"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00012"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00010"&&Rolename=="SHIFT INCHARGE")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00010"&&Rolename=="FIRE OFFICER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	 	
	    else if(type=="ATY00014"&&Rolename=="FIRE OFFICER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00014"&&Rolename=="UNIT EHS MANAGER")
			{
			LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	    }
	    else if(type=="ATY00008"&&Rolename=="UNIT GM OPERATION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	    else if(type=="ATY00013"&&Rolename=="UNIT GM OPERATION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	    else if(type=="ATY00011"&&Rolename=="UNIT GM OPERATION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	 	
	    else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	 	
	    else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	 	
	    else if(type=="ATY00014"&&Rolename=="FIRE MARSHAL")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	 	
	    else if(type=="ATY00010"&&Rolename=="FIRE MARSHAL")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
	}
	 	
	    else{
	    	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",false,true );
	        
	    }
		
	});



	jQuery("#btnsave").click(function(){
		//navigateToNextForm('safteyincident_save.ehsaccIncRpt?q=2&bookingMode=Booking','Incident Reporting');
		//saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?incId='+result.successData.keyId);
		var keyid=jQuery("#hdnkeyid").val();
		//alert("keyid"+keyid);
		//var chko=jQuery("#chbUnsafeAct").val();
		var chkact = ( jQuery("#chbUnsafeAct").is(':checked') ) ? "Y": "N";
		
		var chkcon = ( jQuery("#chbUnsafeCondn").is(':checked') ) ? "Y": "N";
		//var chks=jQuery("#chbUnsafeCondn").val();
		var time=jQuery('#spnsinmAnalysedtime').spinner('getValue');
		var analysedtime=jQuery("#dtesinmAnalysedDate").datebox('getValue');
		var shift=jQuery("#cmbsinmAnalysedShift").combobox('getValue');
		var analyseby=jQuery("#cmbsinmAnalysedBy").combobox('getValue');
		var exacttype=jQuery("#cmbsinmExactType").combobox('getValue');
		if(exacttype.length==0)
			{
			   popupCommonErrorMsg("Select Accident type");
			   return false;
			}
		
		if(exacttype.length==0)
		{
		   popupCommonErrorMsg("Select Invesigated By ");
		   return false;
		}
		
		if(analysedtime.length==0&&time.length==0)
			{
			 popupCommonErrorMsg("Select Analaysed Date & Time ");
			   return false;
			}
		
		if(shift.length==0)
			{
			 popupCommonErrorMsg("Select Shift");
			   return false;
			}
		var exactdesc=jQuery("#txtsinmExactDesc").val();
		var invest=jQuery("#txtsinmIdea").val();
		var recom=jQuery("#txtsinmRootcause").val();
		var remarks=jQuery("#txtsinminvsremarks").val();
		//alert("outside");
		navigateToNextForm('safteyincidentanalysis_save.ehsaccIncRpt?q=2&keyid='+keyid+"&time="+analysedtime
				+"&shift="+shift+"&analyseby="+analyseby+"&extype="+exacttype+"&exdesc="+exactdesc+"&inv="+invest+"&rec="+recom
				+"&chkact="+chkact+"&chkcon="+chkcon+"&rmrks="+remarks+"&t1="+time
				);
		closePopUpDialoge("IncidentCA");
		
		setTimeout(function() {
	    	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
	    	},1000);	
		
		
	});	
		
	
	

	

			
</script>

<form id="frmAccidentIncident" >
	<div id="wrapper">
			
			

		<table>
			
			<tr>
			  <td valign="top" style="width:40px;margin-left:0px; height:0px;" class="easyui-paddingbfx">
			  			<label class="lblaccIncidentAnalysisdate mandatory-lbl" >Analysis Date Time</label>	
			  			<input id="dtesinmAnalysedDate"  name="dtesinmAnalysedDate" clear="false" class="easyui-datebox" value="${requestScope.analysedate}"  style="width: 202px;" value = "${requestScope.sheAccIncBean.disableAnalysedDate == true ? ' disabled':''}"/>
			  		<span class="spinner" style=margin-left:210px;margin-top:-30px;>
			  		<input id="spnsinmAnalysedtime" name="spnsinmAnalysedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.sheAccIncBean.sinmAnalysedtime}"  style="width: 60px;"  value = "${requestScope.sheAccIncBean.disableAnalysedtime == true ? ' disabled':''}">
			  		</span>			      
			  	    </td>		
	
			  			<td valign="top" style="width:40px;margin-left:0px; height:0px;" class="easyui-paddingbfx">
			  			 <div style="width : 61px; margin-left:55px;margin-top:-5px; align : top ;padding-left:0px; height: 0px">			
						<label class=" mandatory-lbl" style="padding-left:5px;" >Shift</label>
						<input id="cmbsinmAnalysedShift" name="cmbsinmAnalysedShift" class="easyui-combobox"  style="width:270px;"  value="${requestScope.analyseshift}" value = "${requestScope.sheAccIncBean.disableAnalysedShift == true ? ' disabled':''}"/>	
						</div>	
						</td>
						 
				       <td valign="top" style="width: 200px; margin-left:200px;">
						<div  style="margin-left:130px; margin-top:-5px;" class=""><label class=" mandatory-lbl" style="padding-left:90px;">Investigated By</label></div>
     			  	   	<div  style="margin-left:220px;"class=""><input id="cmbsinmAnalysedBy" name="cmbsinmAnalysedBy" class="easyui-combobox"  style="width:270px;"  value="${requestScope.analyseby}"  value = "${requestScope.sheAccIncBean.disableAnalysedBy == true ? ' disabled':''}"/></div>
			
			  		    </td>
			<td>
 <div style="width : 61px; margin-left:50px;margin-top:-8px; align : top ;padding-left:0px; height: 0px">			
<input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:22px;display:;" value="Excel View" />
</div>  
 </td>  				
	
   </tr>                    
</table>
			
			
			<table >		  	
			  	
		
		 <tr>
         <td style="width: 229px">
         <div style="margin-left:0px;margin-top:0px;padding-left:px; width: 200px"></div>
          <div style="margin-left:0px;"><label class="mandatory-lbl">Accident Type</label></div>	 
		<input id="cmbsinmExactType" name="cmbsinmExactType" class="easyui-combobox"  style="width:270px;"  value="${requestScope.exctype}"  value = "${requestScope.sheAccIncBean.disableExactType == true ? ' disabled':''}"/>			 
	    </td>
	  
	 <td> 
	<div style="margin-left:-145px;"><label class="lblaccRootcausedueto">Root Cause Due To</label></div>
	<div style="margin-left:70px; margin-top:13px;"><label>Unsafe Act</label></div>	
	<div style="margin-left:55px; margin-top:-13px;"><input type="checkbox" id="chbUnsafeAct" name="chbUnsafeAct" value = "${requestScope.sheAccIncBean.disableUnsafeAct == true ? ' disabled':''}"/> 
	</div>
	<div style="margin-left:160px; margin-top:-13px;"><label>Unsafe Condition</label></div>
	<div style="margin-left:140px; margin-top:-13px;"><input type="checkbox" id="chbUnsafeCondn" name="chbUnsafeCondn"  value = "${requestScope.sheAccIncBean.disableUnsafeCondn == true ? ' disabled':''}"/> 
	</div>
  
  </td>
  	  
  <td>
  <div style="margin-left:80px;"><label>Description of Accident/Incident</label></div>
  <div style="margin-left:80px; margin-top:-1px;">
  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmExactDesc" name="txtsinmExactDesc" maxlength="500" style="resize:none;width:270px;height:75px;" value = "${requestScope.sheAccIncBean.disableExactDesc == true ? ' disabled':''}">${requestScope.accddesc}</textarea> 
  </div> 	
  </td>
  
  
  <td>
  <div style="margin-left:52px; margin-top:-1px;">
 <input type="button" id="btncrtView" name="btncrtView" class="easyui-button" style="width:80px;height:22px;display:;" value="View" />
</div>
</td>  
</tr>
	</table>
	
	
	<table>
	<tr>
	
	<td>
		
   <div style="margin-left:1px;"><label>Investigation</label></div>			
    <div style="margin-left:1px; margin-top:4px;">
   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmIdea" name="txtsinmIdea"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableIdea == true ? ' disabled':''}">${requestScope.invest}</textarea>
   </div>		
</td> 
		
<td>	
<div style="margin-left:51px;"><label >Recommended Corrective Action</label></div>		
 <div style="margin-left:50px; margin-top:-1px;">
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmRootcause" name="txtsinmRootcause"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableRootcause == true ? ' disabled':''}">${requestScope.recom}</textarea>	
</div>
</td>
<td>
<div style="margin-left:15px;"><label>Remarks</label></div>
<div style="margin-left:15px; margin-top:-1px;">
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmRemarks" name="txtsinmRemarks"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableRootcause == true ? ' disabled':''}">${requestScope.recom}</textarea>	
</div>
</td>	
		
			
</tr>		
</table>
			
			  		
			  		
			  		
			  		
			  		
			  		<div style="padding-left:30px;">
			  			<span id="err_dtesinmAnalysedDate" class="tpm-errormsg"></span>
			  			<span id="err_cmbsinmAnalysedShift" class="tpm-errormsg analysedShiftaccErrMsg"></span>
			  			<span id="err_cmbsinmAnalysedBy" class="tpm-errormsg analysedByaccErrMsg"></span>
			  		</div>
			  
			  		
			  
			<div style="margin-left:440px;margin-top:3%;">
			<input type="button" class="easyui-button" value ="Submit" id="btnsave" style="height:20px; width:55px;"/>
			  		
			</div>

</div>
</div>
	
	
		
		
		
<input type="hidden" id="imgPresentImgFilename" name="imgPresentImgFilename" value="" />
<input type="hidden" id="imgAfterImgFilename" name="imgAfterImgFilename" value="" />
<input type="hidden" id="hdnstatus" name="hdnstatus" value="${requestScope.sheTlIncidentMst.sinmStatus}" />
<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.KEYID}" />
<input type="hidden" id="hdnrootcause" name="hdnrootcause" value="${requestScope.rootcause}" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"/>
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>
<input type="hidden" id="hdntime" name="hdntime" value="${requestScope.time}"/>

	
	<input type="hidden" id="hdnCheckFlag" value="N" />
</form>
<input type="hidden" id="hdnYYArea" name="hdnYYArea" value="" />