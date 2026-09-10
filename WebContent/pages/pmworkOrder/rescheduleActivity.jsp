 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">	
jQuery.noConflict();
  jQuery(document).ready(function(){
	  initialiseForm('frmReschedule');
	  jQuery('#submitForm').val('frmReschedule'); // set the id of form to submit.indexOf('&cmbAssmbid=')--&cmbFactid=
	  var url = jQuery('#hiddenUrl').val();
	 
	 var month = url.substring(url.indexOf("nth="),url.indexOf("&weekNo="));
	  month = month.substring(4,month.length);
	  
	  var weekno = url.substring(url.indexOf("No="),url.indexOf("&acttype="));
	  weekno = weekno.substring(3,weekno.length);
	  var actType =url.substring(url.indexOf("pe="),url.indexOf("&navigateNext="));
	  actType = actType.substring(3,actType.length);
	  jQuery("#frmWeekNo").val(weekno);
	  jQuery('#weekNo').val("W"+weekno);
	  jQuery('#txtAct').val(weekno+" for "+month);
	 
	 // alert(url.substring(url.indexOf("MCH"),url.indexOf("&cmbSectid=")));
	  var machId =url.substring(url.indexOf("MCH"),url.indexOf("&cmbSectid="));
	  var sectId =url.substring(url.indexOf("LIN"),url.indexOf("&cmbCellid="));
	  var cellId =url.substring(url.indexOf("CEL"),url.indexOf("&dtFromMonth="));
	  fillComboBox("frmReschedule","cmbReschduleBy","employee.commonFilter" );
	  fillComboBox("frmReschedule","cmbMachineid","machineCombo.commonFilter" );
	  fillComboBox("frmReschedule","cmbsectionid","sectionCombo.commonFilter");
	  fillComboBox("frmReschedule","cmbcellid","cellCombo.commonFilter");
	  setFieldValue('cmbMachineid',machId,'frmReschedule');
	  setFieldValue('cmbsectionid',sectId,'frmReschedule');
	  setFieldValue('cmbcellid',cellId,'frmReschedule');
	  setFieldValue('cmbReschduleBy',jQuery('#userLogged').val(),'frmReschedule');
	  formatDateBox('dterescudledate','dd-MMM-yyyy');
	  fillDateBasedOnWeek('dterescudledate');
	  loadFunctionalLocation("reschduleFunLocation","functionalLoc.genmain","reschdulefunLocationValues","frmReschedule","&machId="+machId);
});
  function dterescudledate_onChange(date)
	{
		var selDay = date.substring(0,date.indexOf('-'));
		
		if(selDay >= 1 && selDay <=7)
			jQuery('#weekNo').val('W1');
		else if(selDay >= 8 && selDay <=14)
			jQuery('#weekNo').val('W2');
		else if(selDay >= 15 && selDay <=21)
			jQuery('#weekNo').val('W3');
		else if(selDay >= 22 && selDay <=31){
			jQuery('#weekNo').val('W4');
			
		}
	}
  function dterescudledate_onSelect(date)
	{
	 
	  var currentDate = getServerDateTime();
			
		if(date < currentDate)
		{					
			jQuery('#dterescudledate').datebox('clear');
			alert("Date cannot be lesser than current date");
		 }
	}
  function fillDateBasedOnWeek(fieldId)
  {
  	var weeKNO =  jQuery('#weekNo').val();
  	    weeKNO = weeKNO.substring(1);
  
  	    
  	var currentTime = new Date(srvTime());
  	var month = currentTime.getMonth();
  	var MOnth = currentTime.getMonth();
  	var day = currentTime.getDate();
  	var year = currentTime.getFullYear();	
  	
  	month = getMonthStringFromInt(month);
  			
  	var hours = currentTime.getHours();
  	var minutes = currentTime.getMinutes();
  	/***/
  
  	
	nxtmonth = new Date(currentTime.getFullYear(), currentTime.getMonth()+1, 1);
  	//alert(getMonthStringFromInt(nxtmonth));

  	 var NextDate= new Date(year, MOnth,day+1);
     var Ndate=NextDate.getMonth();
     
     var nxtMonth = getMonthStringFromInt(Ndate);
     
  	/***/
  	if (minutes < 10){
  		minutes = "0" + minutes;
  	}	
	var newDay = " ";
  	if(weeKNO == "1")
  		newDay = 8;
  	else if(weeKNO == "2")
  		newDay = 15;
  	else if(weeKNO == "3")
  		newDay = 22;
  	else if(weeKNO == "4"){
  		newDay = "01";
  		month = nxtMonth;
  	}

  	//newDay = LastDayOfMonth(year, MOnth);
  	if(fieldId.substring(0,3) == "dte")		
  		jQuery("#"+fieldId).datebox('setValue',newDay+'-'+month+'-'+year);		
  	else if(fieldId.substring(0,3) == "spn")		
  		displayText(fieldId,hours + ":" + minutes);//jQuery("#"+fieldId).val(hours + ":" + minutes);		
  	else
  		jQuery("#"+fieldId).val(adDay+'-'+month+'-'+year);
  }
  
	  
  jQuery('#btnrescancel').click(function(){
  closePopUpDialoge("loadReschedule");
  });
  jQuery('#btnresOk').click(function(){
	var reason = jQuery('#txtReason').val();
	var remarks= jQuery('#txtRemarks').val();
	 var url = jQuery('#hiddenUrl').val();
	 var filterString = url.substring(url.indexOf('?q=2'),url.indexOf('&navigateNext'));
	 var month = url.substring(url.indexOf("nth="),url.indexOf("&weekNo="));
	  month = month.substring(4,month.length);
	  var weeKNO =  jQuery('#weekNo').val();
	    weeKNO = weeKNO.substring(1);
	  var frmWeekNo =jQuery("#frmWeekNo").val(); 
	  	//alert(frmWeekNo);
	  	
	 filterString +="&cmbMchid="+ jQuery("#frmReschedule input[id='machine']").val();
	 filterString +="&fromMonth="+month;
	 filterString +="&reschudledate="+getFieldValue("dterescudledate","frmReschedule");
	 filterString +="&weekNum="+frmWeekNo; 
	 filterString +="&reason="+reason; 
	 filterString +="&remarks="+remarks; 
	 filterString +="&reschdWeekNo="+weeKNO; 
	 filterString +="&reschduleBy="+getFieldValue("cmbReschduleBy","frmReschedule");
	 filterString +="&reshdType=postponed";
	 filterString +="&assemblyId=" + getFilterValue( url+"&","cmbAssmbid");	 
	if(reason != " " && reason != null && reason != "" ){
			
			
			processAjaxCalls("reschedule_save.mpc",filterString,"rescheduleSuccess","rescheduleError");
			//saveForm("frmReschedule","reschedule_save.mpc"+filterString);
	}
	else
		alert("Enter Reason");
  });
  function rescheduleSuccess(result){
		//alert('sucess');
		alert(result.retmsg);
		closePopUpDialoge("loadReschedule");
		jQuery('#MPgrid').trigger("reloadGrid");
	  }
  function rescheduleError(){
	  alert('err');
}
  function LastDayOfMonth(Year, Month) {
	  var LastDate =  new Date( (new Date(Year, Month,1))-1 );
	  var day = LastDate.getDate();
	    return day;
	}
</script>
<form id="frmReschedule">
	<div id="maindiv" style="margin-left:2.5%">
		<table>
			<tr>
			 	 <td style="width: 100%" colspan='2'> 
			         <div  id="frmReschedule"  >
						<input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.plmTlGenmaintenance.gmntFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.plmTlGenmaintenance.gmntSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbLineid" value="${requestScope.plmTlGenmaintenance.gmntLineid}"  ></input>
						<input type="hidden" id="machine" name="cmbMachineid" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"></input>
						<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.plmTlGenmaintenance.gmntFlid}"></input>
					 </div>
					 <div id="reschduleFunLocation" style="padding-left: 0px;"></div>
				  </td>
			  </tr>
			<tr>
				<td valign='' style="padding-top:3px;">
					<div class="easyui-paddingbfpx "><label>Activities for Week</label><label class="mandatory-lbl" style="padding-left:24">Rescheduled By</label></div>
					<div>
					<input id="txtAct"  style="" disabled=disabled class="easyui-text" ></input>
					<span style="padding-left:20px;padding-left:10px\9;">
					  <input id="cmbReschduleBy" name="cmbReschduleBy" class="easyui-combobox"  style="width:170px;" value="" / >
					</span>
					</div><!--	
				<div class="easyui-paddingbfpx " style="">
		     		<label class="mandatory-lbl">Rescheduled By</label>
			     </div>
			     <div class="easyui-paddingbfpx" style="">
				      <input id="cmbReschduleBy" name="cmbReschduleBy" class="easyui-combobox"  style="width:270px;" value="" / >
				</div>
                <div  class="easyui-paddingbfpx">
                    <label>Section</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbsectionid" name="cmbsectionid" class="easyui-combobox"  style="width:300px;"  value="${requestScope.plmTlStandards.pmsdTradeid}" >                    
                </div>
                <div  class="easyui-paddingbfpx">
                   <label>Cell</label>
                </div> 
                <div class="easyui-paddingbfpx "> 
                    <input id="cmbcellid" name="cmbcellid" class="easyui-combobox"  style="width:300px;"  value="${requestScope.plmTlStandards.pmsdMachineid}"  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                   <label>Machine</label>
                </div> 
                <div class="easyui-paddingbfpx "> 
                    <input id="cmbMachineid" name="cmbMachineid" class="easyui-combobox"  style="width:300px;"  value="${requestScope.plmTlStandards.pmsdMachineid}"  >                    
                </div>  
                 -->
				<div  class="easyui-paddingbfpx " style="padding-top:3px;">
	                    <label class=" mandatory-lbl">Reason</label>                    
	                </div> 
	                <div class="">
	                    <textarea rows="2" cols="31" id="txtReason" name="txtReason" style=" ; width : 294px;">${requestScope.plmTlStandards.pmsdStandard}</textarea>
	                </div>
				</td>
				<td style="padding-left: 25px;padding-top:3px;" valign="top">
					<div class="easyui-paddingbfpx " ><label class="mandatory-lbl" >Reschedule Date</label>
			          <span style="padding-left: 79px;padding-left: 69px\9;"><label  class="">Reschedule Week</label></span></div>
			          <div>
			          <input id="dterescudledate" name="dterescudledate" class="easyui-datebox easyui-text" style="width:120px;" <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>></input>
			      
			          <span style="padding-left:55px;padding-left: 42px\9;"><input id="weekNo" type="text" style="border: 1px solid black; font-size: 10px;height: 20px;width:100px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent;" disabled="disabled" value=""></span></div>   
			           
					
	                <div  class="easyui-paddingbfpx " >
	                    <label>Remarks</label>                    
	                </div> 
	                <div class="">
	                    <textarea rows="2" cols="31" id="txtRemarks" name="txtRemarks" style=" ">${requestScope.plmTlStandards.pmsdStandard}</textarea>
	                </div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="center" style="padding-top:10px;">
				<input type="button" class="easyui-button" value="Ok" id="btnresOk"/>
				<span style="padding-left:10px;"><input type="button" class="easyui-button" id="btnrescancel" value="Cancel"/></span>
				</div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" value="${requestScope.user }" id="userLogged" name="userLogged"/>
	<input type="hidden" id="frmWeekNo" name ="frmWeekNo" />
</form>