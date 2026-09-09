<!--Author : Manikandan-->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
jQuery(document).ready(function(){
	
	var url = jQuery('#hiddenUrl').val();
	var mode= getFilterValue(url+'&',"mode");
	initialiseForm("frmGenralMaintenance");
    jQuery('#frmGenralMaintenance .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmGenralMaintenance textarea').css('text-transform', 'uppercase');
    /*added on 21st jun*/
    var compId = getFieldValue('company','frmGenralMaintenance');
	var locnId = getFieldValue('location','frmGenralMaintenance');
	var factId = getFieldValue('factory','frmGenralMaintenance');
	var sectId = getFieldValue('section','frmGenralMaintenance');
	var cellId = getFieldValue('cell','frmGenralMaintenance');
	var machId = getFieldValue('machine','frmGenralMaintenance');
	if(machId != '' && machId != ' ' && machId != null ){
			
		fillComboBox("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
		
		}else
		fillComboBox("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter");
	
	/*readOnlyFields('dteGmntShiftdate');*/
	jQuery('#submitForm').val('frmGenralMaintenance'); // set the id of form to submit
	formatDateBox('dteGmntStartdate','dd-MMM-yyyy');
	formatDateBox('dteGmntWostartdate','dd-MMM-yyyy');
	formatDateBox('dteGmntOccureddate','dd-MMM-yyyy');
	formatDateBox('dteGmntShiftdate','dd-MMM-yyyy');
	formatDateBox('dteGmntBookeddate','dd-MMM-yyyy');
	formatDateBox('dteGmntWoenddate','dd-MMM-yyyy');
	formatDateBox('dteGmntTargetdate','dd-MMM-yyyy');
	readOnlyFields('cmbGmntKeyid');
	readOnlyFields('dteGmntBookeddate');
	readOnlyFields('dteGmntShiftdate');
	if(url == "generalMaintcreat_input.genmain"){
		
		//jQuery('#cmbGmntKeyid').combobox('disable');
		//fillWithCurrentDate('dteGmntOccureddate');//filling Current Date
		//fillWithCurrentDate('spnoccuredTime');//filling Current Time
		//fillWithCurrentDate('dteGmntShiftdate');
		fillWithCurrentDate('dteGmntTargetdate');
		
		fillWithCurrentDate('dteGmntBookeddate');
		
		}
	//jQuery('#dteGmntShiftdate').datebox('disable');
	//jQuery("#dteGmntOccureddate").datebox('disable');
	//jQuery("#spnoccuredTime").spinner('disable');
/*set the occured time and date to work start date and time*/
	if(mode != 'MODIFY' && mode != 'VIEW' ){
		
		var occDate = getFieldValue('dteGmntOccureddate'); 
	    var occTime = getFieldValue('spnoccuredTime');
	//	var wsDate  = setFieldValue("dteGmntWostartdate",occDate) ;
		//var wsTime  = displayText("spnWorkstartTime",occTime);
		readOnlyFields('cmbGmntReportedby');
		
	}
/*---End ---*/
	
	var relatedToValue=getFieldValue('cmbGmntRelatedto');
	
	 if(relatedToValue == "MCH"){
			jQuery("#fact_label").removeClass('mandatory-lbl');
		    jQuery("#Mouldid_label").removeClass('mandatory-lbl');
		    jQuery("#eqp_label").addClass('mandatory-lbl');
		    readOnlyFields('cmbGmntMouldid');
		    jQuery('#cmbGmntMouldid').combobox('setValue',"-");
		   
			}
			else{ 
				jQuery("#fact_label").addClass('mandatory-lbl');
			    jQuery("#Mouldid_label").addClass('mandatory-lbl');
			    jQuery("#eqp_label").removeClass('mandatory-lbl');
			    //alert(mode);
			    if(mode != 'VIEW' ){
			    	
			    	enableFields('cmbGmntMouldid');
			    }
			}

	var statuscmb = jQuery('#cmbGmntStatus').val();
	if(statuscmb == 'C')
			jQuery( "#completed" ).css('display','block');
	//jQuery( "#completed" ).hide("slide", { direction: "right" }, 1000);
	else 
		jQuery( "#completed" ).css('display','none');
	readOnlyFields('cmbGmntReportedby');
		readOnlyFields('dteGmntOccureddate');
		readOnlyFields('spnoccuredTime');
	/*calculate time difference*/
		spinnerChange('spnoccuredTime','occuredTimeEvt');
		spinnerChange('spnWorkstartTime','woStartEvt');
		spinnerChange('spnWorkendTime','woEndEvt');
		
		spinnerUp('spnoccuredTime','occuredTimeEvt');
		spinnerUp('spnWorkstartTime','woStartEvt');
		spinnerUp('spnWorkendTime','woEndEvt');
		
		spinnerDown('spnoccuredTime','occuredTimeEvt');
		spinnerDown('spnWorkstartTime','woStartEvt');
		spinnerDown('spnWorkendTime','woEndEvt');

		//alert(jQuery('#cmbGmntCompletedby').combobox('getValue').length);
		//setloginUserId('cmbGmntCompletedby');	
		readOnlyFields('txtGmntWorkhours',"");
		readOnlyFields('txtGmntDowntime',"");
		readOnlyFields('txtGmntResponsetime',"");

		/* for functionalLocation*/
		var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
		if(relatedTO == 'MLD')
			lodFuncLoc(relatedTO);
		else	
			lodFuncLoc('MCH');
		/*---------*/	
		var stausVal = getFieldValue('cmbGmntStatus','frmGenralMaintenance');
		if(stausVal != '' && stausVal != ' ' && stausVal != null)
			frmGenralMaintenancecmbGmntStatus_onSelect(stausVal);

		jQuery('#spansubLoss').hide();
		var menumode = jQuery('#hdnsetupandadj').val();
		if(menumode != '' && menumode != ' ' && menumode != null ){			
			setTimeout(function() {jQuery('#cmbGmntActivitytype').combobox('setValue','S');},1250);
			setTimeout(function() {jQuery('#cmbGmntActivitytype').combobox('setText','Setup And Adjustment');},1250);
			setTimeout(function() {readOnlyFields('cmbGmntActivitytype',"");},1250);
			//jQuery('#cmbGmntActivitytype').css('width','150px');
			jQuery('#spansubLoss').show();
			if(statuscmb != 'C')
				disableUIButton('btnsubLoss');
			//jQuery('#spansubLoss').css('display','block');
		}
});
jQuery('#btnsubLoss').click(function(){
	LoadPopUp("divSubLoss","subloss_input.genmain", true,"740px","400px","-55px","7%", "SubLossSNA_Callback","Sub Loss");
});
function divSubLoss_afterClose()
{
}


function lodFuncLoc(datStr){
	var compId = getFieldValue('company','frmGenralMaintenance');
	
	var locnId = getFieldValue('location','frmGenralMaintenance');
	
	var factId = getFieldValue('factory','frmGenralMaintenance');
	
	var sectId = getFieldValue('section','frmGenralMaintenance');
	
	var cellId = getFieldValue('cell','frmGenralMaintenance');
	
	var machId = getFieldValue('machine','frmGenralMaintenance');	
	
	var relTo = '';
	
	var dataStr = "&factId="+factId+"&sectionId="+sectId+"&cellId="+cellId +"&machId="+machId;
	
	//alert(dataStr);
	var url = jQuery('#hiddenUrl').val();
	
	var vurl = url.substring(0,url.indexOf('?'));
	
	
	if(datStr !=' ' && datStr !='' && datStr != null){
		if(vurl  == "generalMaint_modify.genmain"){
			relTo = "?relTo=MCH";
			reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&mchId="+machId);
		}
		else
			relTo = "?relTo="+datStr;
		
		if(datStr.substring(0,5) == "onsel")
		 dataStr += "&machId="+datStr.substring(5,datStr.length);
		 //alert(url.substring(url.indexOf('&vurl')+6,url.indexOf('&closeOnSave')));
			var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
		if("MLD" == relatedTO){
			//alert('sds');
				fillComboBox("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&relatedto=MLD");
		}
		else
			fillComboBox("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&relatedto=MCH");
		// alert(datStr.substring(5,datStr.length));
	}
	
	
	
		//alert(relTo+"----------"+datStr+"----------"+dataStr);	
		var flid = jQuery("#frmAbnormality input[id='flid']").val();
		dataStr += "&flid="+flid;
		loadFunctionalLocation("gmntfunLocation","functionalLoc.genmain"+relTo,"gmntfunLocationValues","frmGenralMaintenance",dataStr);
	
}
/*calculate time difference*/
						
  	function spinnerChange(spinnerId,callBackFunc)
	{       
		jQuery("#"+spinnerId).change(function(){	
			
			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'())');
			}
		});
 }
	function spinnerUp(spinnerId,callBackFunc)
	{
		
		jQuery("#"+spinnerId).spinner({
			onSpinUp:function(){
				if( typeof eval('('+callBackFunc +')') == 'function')
 			{
 				eval('( '+ callBackFunc +'())');
 			}
			}
			});
 }
	function spinnerDown(spinnerId,callBackFunc)
	{
		
		jQuery("#"+spinnerId).spinner({
			onSpinDown:function(){
				if( typeof eval('('+callBackFunc +')') == 'function')
 			{
 				eval('( '+ callBackFunc +'())');
 			}
			}
			});
 	}
	jQuery("#spnWorkstartTime").blur(function() {
		
		spinnerUp('#spnWorkstartTime');
		
		spinnerDown('#spnWorkstartTime');
		
		});
	jQuery("#spnWorkendTime").blur(function() {
		
		spinnerUp('#spnWorkendTime');
		
		spinnerDown('#spnWorkendTime');
		
		});

	
	function occuredTimeEvt()
	{   
	
		/*to get shift based on Time**/

		var fctid =  jQuery("#frmGenralMaintenance input[id='factory']").val();
		
		var time  = jQuery("#spnoccuredTime").spinner('getValue');
		
		fill_shift(fctid,"","",time);
		//alert("MachineActivity 51");
		dteGmntOccureddate_onSelect(new Date());
		
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		    // alert(wsDate+"----"+weDate);
		     if(weDate != null && weDate != ""){
				actualMins = timeDifference(wsDate,weDate);	
				displayText('txtGmntWorkhours',actualMins.minutes);										
				//actualMins = timeDifference(wsDate,weDate);	
				downTime = timeDifference(occDate,weDate);
				displayText('txtGmntDowntime',downTime.minutes);
		     }
		}
    }
    
	
	function woStartEvt()
	{
		dteGmntWostartdate_onSelect(new Date());
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtGmntResponsetime',respTime.minutes);
		}
		/*to get shift based on Time**/
		var fctid = getFieldValue('cmbGmntFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);
	}
	function woEndEvt()
	{    
		dteGmntWoenddate_onSelect(new Date());
		 							
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtGmntWorkhours',actualMins.minutes);										
			actualMins = timeDifference(wsDate,weDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime',downTime.minutes);
		}
		enableUIButton('btnsubLoss');
 }
	function dteGmntOccureddate_onSelect(date)
  	{
		
		var occDate = getFieldValue('dteGmntOccureddate');
		 
		setFieldValue("dteGmntShiftdate",occDate);
		
		compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime',' Occured Date should  be Greater than Work start Date ');
		
		//compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntOccureddate','spnoccuredTime',' Occured Date should  be Greater than Work start Date ');
    }

	function dteGmntWostartdate_onSelect(date)
  	{
				//fillWithCurrentDate('spnWorkstartTime');
		//compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntOccureddate','spnoccuredTime','Start Date should not be lesser than Allotted Date/time');
		compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime','Work start should be equal Greater than  occurred date');
		
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtGmntResponsetime',respTime.minutes);
		}
		/*to get shift based on Time**/
		var fctid = getFieldValue('cmbGmntFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);
	}
	function dteGmntWostartdate_onChange(date)
  	{
		//fillWithCurrentDate('spnWorkstartTime');
		//compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntOccureddate','spnoccuredTime','Start Date should not be lesser than Allotted Date/time');
		compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime','Work start should be equal Greater than  occurred date');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtGmntResponsetime',respTime.minutes);
		}
		/*to get shift based on Time**/
		var fctid = getFieldValue('cmbGmntFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);
	}
	function dteGmntWoenddate_onChange(date)
  	{
		compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtGmntWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime',downTime.minutes);
			
		}
    }	
	function dteGmntWoenddate_onSelect(date)
  	{
		compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtGmntWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime',downTime.minutes);
			
		}
		enableUIButton('btnsubLoss');
    }
	 function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
	    {
			var currentDate = new Date();
			var hours = currentDate.getHours();
			var minutes = currentDate.getMinutes();
			var tospinner =  jQuery('#'+toTimeId).spinner('getValue');
			if(tospinner != "" && tospinner != null){
				var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
				var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
			}
			else{
				var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
				fillWithCurrentDate(toTimeId);
				var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
				}
			/*if(convertStringToDate(toDate) > currentDate)
				{   
				//alert(currentDate);
				//alert(convertStringToDate(toDate));
					showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
		        	fillWithCurrentDate(toDateId);	
					fillWithCurrentDate(toTimeId);
				}*/
		 if( compareDateTime(toDate,fromDate) > 0 )
		    { //alert(toDate+"------------"+fromDate);
			    //alert(compareDateTime(toDate,fromDate));
	        	showValidationErrorMsg(toDateId,errMsg);
	        	//fillWithCurrentDate(toDateId);	
				//fillWithCurrentDate(toTimeId);
		    }
	        else
	        	clearValidationErrorMsg(toDateId);	
		}
	
/*end of calculate time diff
function frmGenralMaintenancecmbGmntFactoryid_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntSectionid","sectionCombo.commonFilter" );
}
function frmGenralMaintenancecmbGmntSectionid_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntLineid","cellCombo.commonFilter");
}	
function frmGenralMaintenancecmbGmntLineid_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter");
}*/

function frmGenralMaintenancecmbGmntMachineid_onLoadSuccess()
{ 
	fillComboBox("frmGenralMaintenance","cmbGmntRelatedto","relatedto.commonFilter");
	
}
function frmGenralMaintenancecmbGmntStationid_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntRelatedto","relatedto.commonFilter");
	
}
function frmGenralMaintenancecmbGmntRelatedto_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter");
	fillComboBox("frmGenralMaintenance","cmbGmntShift","combo_GmntShift.genmain");
	jQuery('#cmbGmntShift').combobox('disable');	
	readOnlyFields('cmbGmntRelatedto');
}
function frmGenralMaintenancecmbGmntMouldid_onLoadSuccess()
{
	//fillComboBox("frmGenralMaintenance","cmbGmntShift","combo_GmntShift.genmain");
	//jQuery('#cmbGmntShift').combobox('disable');	
}
function frmGenralMaintenancecmbGmntShift_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntActivitytype","combo_activitytype.genmain");
}
function frmGenralMaintenancecmbGmntActivitytype_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntTrade","combo_GmntTrade.genmain");
}
function frmGenralMaintenancecmbGmntTrade_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntMchcondition","combo_machinecondition.genmain");
}
function frmGenralMaintenancecmbGmntMchcondition_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntReportedby","combo_GmntReportedby.genmain");
}
function frmGenralMaintenancecmbGmntReportedby_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntStatus","combo_GmntStatus.genmain");
}
function frmGenralMaintenancecmbGmntStatus_onLoadSuccess()
{
	//fillComboBox("frmGenralMaintenance","cmbGmntCompletedby","combo_Gmntcompletedby.genmain");
	fillComboBox("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?q=2&dept=maintenance" );
}
function frmGenralMaintenancecmbGmntCompletedby_onLoadSuccess()
{
    	
	/*var url = jQuery('#hiddenUrl').val();//alert(url);
	var viewUrl = url.substring(64,89);
	if(viewUrl == 'generalMaint_view.genmain'){
		disableForm('frmGenralMaintenance');
	}*/
}
function  frmGenralMaintenancecmbGmntMachineid_onClear(record)
{
	jQuery('#linfrmGenralMaintenanceMachine').html(' ');
	jQuery("#frmGenralMaintenance input[id='machine']").val('');
	var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
	if(relatedTO == 'MLD')
		lodFuncLoc(relatedTO);
}
function  frmGenralMaintenancecmbGmntMachineid_onSelect(record)
{ 
	var relatedToValue=getFieldValue('cmbGmntRelatedto');
	if(relatedToValue == "MCH"){
		reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&mchId="+record.id);
		reloadCombo("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter?q=2&machineId="+record.id);
	}
	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbGmntLineid","cmbGmntSectionid","cmbGmntFactoryid","cmbComp");
	lodFuncLoc("onsel"+record.id);
	//loadFunctionalLocation("gmntfunLocation","functionalLoc.genmain","gmntfunLocationValues","frmGenralMaintenance","&machId="+record.id);
	var fctid = getFieldValue('cmbGmntFactoryid');
	var linid = getFieldValue('cmbGmntLineid');
	var secid = getFieldValue('cmbGmntSectionid');
	/*readOnlyFields('cmbGmntFactoryid');
	readOnlyFields('cmbGmntLineid');
	readOnlyFields('cmbGmntSectionid');*/
	readOnlyFields('cmbGmntMachineid');
	fill_shift(fctid,secid,linid,"");
}
function  frmGenralMaintenancecmbGmntLineid_onSelect(record)
{
	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbGmntSectionid","cmbGmntFactoryid");
	var lineid = record.id;
	fill_shift("","",lineid);
		
}
function  frmGenralMaintenancecmbGmntSectionid_onSelect(record)
{
	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbGmntFactoryid");
	var sectid = record.id;
	
	fill_shift("",sectid);	
}
function  frmGenralMaintenancecmbGmntFactoryid_onSelect(record)
{
	jQuery("#cmbGmntSectionid").combobox('clear');
	jQuery("#cmbGmntLineid").combobox('clear');
	jQuery("#cmbGmntMachineid").combobox('clear');
	reloadCombo("frmGenralMaintenance","cmbGmntSectionid","sectionCombo.commonFilter?factId="+record.id);
	reloadCombo("frmGenralMaintenance","cmbGmntLineid","cellCombo.commonFilter?factId="+record.id  );
	reloadCombo("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter?factId="+ record.id );
	/*var factryid = record.id;
	var factid = jQuery('#hdnFactid').val(factryid);
		("factory onload success"+factid );
	*/
}
function  frmGenralMaintenancecmbGmntRelatedto_onSelect(record)
{	
	clearField('cmbGmntStationid');
  if(record.id == "MCH"){
	jQuery("#fact_label").removeClass('mandatory-lbl');
    jQuery("#Mouldid_label").removeClass('mandatory-lbl');
    jQuery("#eqp_label").addClass('mandatory-lbl');
    readOnlyFields('cmbGmntMouldid');
    jQuery('#cmbGmntMouldid').combobox('setValue',"-");
    var machId=getFieldValue("cmbGmntMachineid", "frmGenralMaintenance");
    reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&mchId="+machId);
	reloadCombo("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter?q=2&machineId="+machId);
	}
	else{ 
		jQuery("#fact_label").addClass('mandatory-lbl');
	    jQuery("#Mouldid_label").addClass('mandatory-lbl');
	    jQuery("#eqp_label").removeClass('mandatory-lbl');
	    enableFields('cmbGmntMouldid');
	    reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?q=2&relatedto=MLD");
	    
	}
}
function  frmGenralMaintenancecmbGmntStatus_onSelect(record)
{
	if(record.id == "C" || record == "C"){
		jQuery( "#completed" ).css('display','block');
		jQuery( "#actionTaken").addClass('mandatory-lbl');
		//jQuery( "#completed" ).animate({marginLeft:'500px'},'slow'); 
	}
	else if(record.id == "P" || record == "P"){
		jQuery( "#actionTaken").removeClass('mandatory-lbl');
		jQuery( "#completed" ).css('display','none');
	}
}	

function frmGenralMaintenance_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFieldValue('cmbGmntMachineid',keyIds.machId);
		
	var factryid = keyIds.factId;
	jQuery("#cmbGmntShift").combobox('clear');
	fill_shift(factryid);
	
	if( keyIds.machId == null || keyIds.machId == 'null'){
		//alert('d');
		clearField('cmbGmntMachineid');
	}
	else{
		reloadMachine("frmGenralMaintenance",'cmbGmntMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		//fillComboBox("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter");
		/*added on 21st jun*/
		var machId = getFieldValue("cmbGmntMachineid","frmGenralMaintenance");
		var relatedto_val = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
		
		if(machId != ' ' && machId != '' && machId != null){
			if(relatedto_val!="MLD"){
				readOnlyFields('cmbGmntMouldid');/*added on 21-jun*/
			}	
			reloadCombo("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter?q=2&mchId="+machId);
		}
	}
	var url = jQuery('#hiddenUrl').val();
	var vurl = url.substring(63,90);
	if(vurl  == "generalMaint_modify.genmain"){
		readOnlyFields('cmbGmntFactoryid');
	   	readOnlyFields('cmbGmntLineid');
	   	readOnlyFields('cmbGmntSectionid');
	   	readOnlyFields('cmbGmntMachineid');   	
	   	}
	/*End*/
	
}
function fill_shift(factryid,sectid,lineid,time){

	var factryid =factryid;
	var sectid = sectid;
	var lineid =lineid;
	var time  = time;
	
	//alert(fctid+"--"+linid+"--"+secid);
	var fromTime = jQuery('#dteGmntBookeddate').val();	
		//alert("line  "+lineid);				
		var dataString = '?q=2&factId='+factryid+'&sectId=';
		    dataString += '&cellId=&fromTime='+time;	
		   // alert(dataString);
		processAjaxCalls('fill_shift.genmain',dataString,'getShift','getShiftErr');
	
}
function  getShift(record)
	{      
		jQuery("#cmbGmntShift").combobox('clear');
		jQuery('#cmbGmntShift').combobox('setValue',record.shift);               	
	}
jQuery('#back_genMain').click(function(){
	
	   jQuery("#GeneralMaintMain").load('generalMaint_input.genmain','', function(response, status, xhr) {
	   if (status == "error") {
	   var msg = "Sorry but there was an error: ";
	   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
	  }
	});
	  
	   
});
function frmGenralMaintenance_successsCallback(result)
{	
	var backTo = result.successData.backTo;
	var prevPage = result.BACKTO;
	if(backTo != null && backTo != ' ' && backTo != '')
	{
		if(backTo == "GMFromWOServlet")
		{
			if(prevPage != null && prevPage != ' ' && prevPage != '')
				popFormNavigation();
			 navigateToPrevForm();
		}
	}
}
function frmGenralMaintenance_beforeDelete()
{
	var delActivity = jQuery('#hdndelMAMode').val();				
	if(delActivity == 'Y')
	{
		alert('Activity Cannot be deleted');
		return false;
	}
	var delMsg = "Do You Want To Delete This Machine Activity ("+jQuery('#cmbGmntKeyid').combobox('getValue')+")";
	if(confirm(delMsg) == false)
	{
		return false;
	}
}
function frmGenralMaintenance_beforeSubmit()
{
	
	  return 'SetupandLoss='+convertGridToJSONArr('subLossGrid');
}

</script>
<form id="frmGenralMaintenance" name="frmGenralMaintenance">
<div id="wrapper" style="width:1250px;">
<div id="Genmaint" class="divbrdr">

<!--	<div style="float:right;"><input type="button"  value="BACK" id="back_genMain" class="easyui-button" /></div><br><br>-->
	<table width="92%" align=" " >
			 <tr>
			 	 <td style="width: 100%"> 
			         <div  id="frmGenralMaintenanceFuntKeyIds"  >
						<input type="hidden" id="factory" name="cmbGmntFactoryid" value="${requestScope.plmTlGenmaintenance.gmntFactoryid}"></input>
						<input type="hidden" id="section" name="cmbGmntSectionid" value="${requestScope.plmTlGenmaintenance.gmntSectionid}"></input>
						<input type="hidden" id="cell" name="cmbGmntLineid" value="${requestScope.plmTlGenmaintenance.gmntLineid}"></input>
						<input type="hidden" id="machine" name="cmbGmntMachineid" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"></input>
						<input type="hidden" id="flid" name="cmbGmntFlid" value="${requestScope.plmTlGenmaintenance.gmntFlid}"></input>
					 </div>
					 <div id="gmntfunLocation" style="padding-left: 0px;"></div>
				  </td>
				  </tr> 
				  
		<tr> 
				  <td  style="padding-left: 0px;">
				  <div>
					<label  class="mandatory-lbl">Related To</label>
	            	<span style="padding-left:80px;">
	            	<label id="Mouldid_label">Mould</label></span>
	            	<span style="padding-left:125px;">
	            	<label id="eqp_label" class="mandatory-lbl">Equipment</label></span>
		            </div>
		            <div>
		            	<input id="cmbGmntRelatedto" name="cmbGmntRelatedto" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  readonly="readonly" value="${requestScope.plmTlGenmaintenance.gmntRelatedto }" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
		<!--            <input id="gmntRelatedto" type="hidden" class="easyui-text" value="${requestScope.plmTlGenmaintenance.gmntRelatedto }"/>-->
		            	<span style="padding-left:5px;">
		             	<input id="cmbGmntMouldid" name="cmbGmntMouldid" class="easyui-combobox"  style="width:131px;text-transform:capitalize;" value="${requestScope.plmTlGenmaintenance.gmntMouldid}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
		             	</span>
		             	<span style="padding-left:24px;">
		             	<input id="cmbGmntMachineid" name="cmbGmntMachineid" class="easyui-combobox" onkeydown="" style="width:272px;" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"  <c:out value = "${ requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
		             	</span>
		             	<span id="err_cmbGmntRelatedto" class="tpm-errormsg" ></span>
		             	<span id="err_cmbGmntMouldid" class="tpm-errormsg" style="margin-left:13%;"></span>
		             	<span id="err_cmbGmntMachineid" class="tpm-errormsg" style="margin-left:28%;"></span>
					</div> 
				  </td>
				  <td style="width: 100%">
					<!--<div id="eqp_label" class="mandatory-lbl"><label >Equipment</label></div>
				        <div class="easyui-paddingbfpx">
				     	<input id="cmbGmntMachineid" name="cmbGmntMachineid" class="easyui-combobox" onkeydown="" style="width:272px;" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"  <c:out value = "${ requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
					</div>
				--></td>
			 	</tr> 			 
			 </table>
			 
			 <table> 
	          <tr width=" " >
	          	
<td style="padding-left: 0px;" valign="top"> 
	          	<div class="sub-header"><span style="">Main Information</span></div>
	           		<div><label>Document No</label>
		            <span style="padding-left:66px;"><label >Date</label></span></div>
		            <div class="easyui-paddingbfpx"  >
		            	<input id="cmbGmntKeyid" name="cmbGmntKeyid" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  value="${requestScope.plmTlGenmaintenance.gmntKeyid}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
		            	<span style="padding-left:5px;">
		            	<input class="easyui-datebox" clear = "false" id="dteGmntBookeddate" name="dteGmntBookeddate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input> 
						</span>
					</div> 
	               
					
					<div><label>Assembly/Station</label></div>
				    <div class="easyui-paddingbfpx" >
				       	<input id="cmbGmntStationid" name="cmbGmntStationid" class="easyui-combobox"  style="width:275px;" value="${requestScope.plmTlGenmaintenance.gmntStationid}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/> ></input>
				    </div>
			
			

			<div><label  class="mandatory-lbl">Shift Date</label>
            <span style="padding-left: 80px;"><label class="mandatory-lbl">Shift</label></span>
            </div>
<!--            <div  >-->
			<table>
			<tr>
            <td>	
            <input clear = "false"  id="dteGmntShiftdate" name="dteGmntShiftdate" class="easyui-datebox" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntShiftdate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
            </td>	
            <td>	
          <span style="padding-left:5px;">
            <input  id="cmbGmntShift" name="cmbGmntShift"  class="" style="width: 133px;" value="${requestScope.plmTlGenmaintenance.gmntShift}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
          </span>
            	  	</td>	
            	</tr>
          </table>  	
<!--			</div> -->
			
		<table  style="width:255px;">
           	<tr><td>	<span id="err_dteGmntShiftdate" class="tpm-errormsg"></span> </td>
           		<td><span id="err_cmbGmntShift" class="tpm-errormsg"></span>   </td></tr>            
		</table>
			
			<div>			
			<label class="mandatory-lbl">Occurred Date</label>
            	<span style="padding-left: 68px;"><label ></label></span>
            </div>
            
            <div class="easyui-paddingbfpx" >
            	<input clear = "false"  id="dteGmntOccureddate" name="dteGmntOccureddate" class="easyui-datebox" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntOccureddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
            	<span style="padding-left:5px;">
             	<span class="spinner">
             	<input id="spnoccuredTime" name="spnoccuredTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 70px;" value="${requestScope.generalMaintainanceBean.occuredTime}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
             	</span>
             	</span>
             	<span id="err_dteGmntOccureddate" class="tpm-errormsg"></span>
			</div> 
<!--			<div  style=" height : 23px;width:255px;">-->
<!--           		<span id="err_dteGmntOccureddate" class="tpm-errormsg" style="float:left;" ></span>-->
<!--           		-->
<!--			 </div>-->
			<div>
				<label >Actual Time</label>
            	<span style="padding-left: 70px;"><label >Down Time</label></span>
            </div>
            <div class="easyui-paddingbfpx" >
            	<input id="txtGmntWorkhours" name="txtGmntWorkhours" class="easyui-text" style="width:130px;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntWorkhours}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
            	<span style="padding-left:5px;">
             	<input id="txtGmntDowntime" name="txtGmntDowntime" class="easyui-text"  style="width:135px;text-transform:capitalize;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntDowntime}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
             	</span>
			</div> 
			
			<div>
			<label>Response Time</label>
			</div>
			<div class="easyui-paddingbfpx">
			<input id="txtGmntResponsetime" name="txtGmntResponsetime" class="easyui-text"  style="width:135px;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntResponsetime}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
			</div>					
</td>
	
	
	<td valign="top" style="padding-left:25px;" width="50%">
		
		<div class="sub-header"  ><span style="">Details For General Maintenance</span></div>
			<div style="float:left;margin-left:5px;">
				<div style="padding-top:5px">
					<label>Part Location</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="txtGmntPartlocation" name="txtGmntPartlocation" class="easyui-text"  style="width:255px;" value="${requestScope.plmTlGenmaintenance.gmntPartlocation}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
				</div>
				
				<div>
					<label class="mandatory-lbl">Activity Type</label>
				</div>
				
				<div class="easyui-paddingbfpx"  >
					<input id="cmbGmntActivitytype" name="cmbGmntActivitytype" class="easyui-combobox"  style="width:155px;text-transform:capitalize;"  readonly="readonly" value="${requestScope.plmTlGenmaintenance.gmntActivitytype }" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
					<span id="spansubLoss">
					<input type="button" value="Sub Loss" id="btnsubLoss" class="easyui-button" style="height:25px;"/ ></input>
					</span>
				</div>
				
				<div style="margin-top: 5px;">
					<label>Maintenance Section</label>
				</div>
				
				<div class="easyui-paddingbfpx">
					<input id="cmbGmntTrade" style="width:255px;" name="cmbGmntTrade" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntTrade}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
				</div>
<!--	modified		-->
				<div style="margin-top: 13px;" >
					<label >Machine Condition</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbGmntMchcondition" style="width:255px;" name="cmbGmntMchcondition" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntMchcondition}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
				</div>
			
				<div style="margin-top: 12px;">
					<label class="mandatory-lbl">Reported By</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbGmntReportedby" style="width:255px;" name="cmbGmntReportedby" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntReportedby}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
				</div>
				
				<div style="margin-top:1px;">
					<label class="mandatory-lbl">Target Date</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input clear = "false"  id="dteGmntTargetdate" name="dteGmntTargetdate" class="easyui-datebox" style="width:110px;" value="${requestScope.plmTlGenmaintenance.gmntTargetdate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
					<span id="err_dteGmntTargetdate" class="tpm-errormsg"></span>
				</div>

				
			</div>  <!-- end of float left -->
	<div style="float:right;margin-left:20px;" >
			
			<div  style="margin-top: 5px;">
				<label class="mandatory-lbl">Problem</label>
			</div>
			
	        <div class="easyui-paddingbfpx" >
	          	<textarea rows="2" style="width: 255px;height:65px;" cols="" id="txtGmntProblem" maxlength="500" name="txtGmntProblem" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntProblem}</textarea>
	        </div>
	          
			<div style="margin-top:5px;" >
				<label id="actionTaken" class="">Action Taken</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtGmntAction" name="txtGmntAction" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntAction}</textarea>
          	</div>
          	
          	<div style="margin-top:5px;">
          		<label>Root Cause</label>
          	</div>
          	
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtGmntRootcauseid" name="txtGmntRootcause" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
          	</div>
          	
			<div>
				<label>Counter Measure</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtGmntCountermeasure" name="txtGmntCountermeasure" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntCountermeasure}</textarea>
          	</div>

				
  </div>
	
</td>
	<td valign="top" style="padding-left:15px;">
				
			<div style="margin-top:40px;">
				<label class="mandatory-lbl">Status</label>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-bottom:15px;">
				<input id="cmbGmntStatus" name="cmbGmntStatus" class="easyui-text"  style="width:255px; " value="${requestScope.plmTlGenmaintenance.gmntStatus}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
			</div>
				
    <div id="completed" style="display:none;margin-top:50px;">
			
			<div class="sub-header">
				<span style="padding-left:5px;"><label>Completed Details</label></span>
			</div>
			
    <div style="padding-left:10px;">
				
			<div class="mandatory-lbl" style="font-size: 12;"><label>Completed By</label></div>
			 <div class="easyui-paddingbfpx">
			 	<input id="cmbGmntCompletedby" name="cmbGmntCompletedby" class="easyui-combobox"  style="width:255px;"  value="${requestScope.plmTlGenmaintenance.gmntCompletedby}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input> 
			 </div>
			 
			 <div>
			 	<label  class="mandatory-lbl">Work Start</label>		      	 
		     </div>
		     
            <div>
            	<input id="dteGmntWostartdate" name="dteGmntWostartdate"  clear = "false" class="easyui-datebox" style="width:120px;"   value="${requestScope.plmTlGenmaintenance.gmntWostartdate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
            	<span style="padding-left:5px;">
            	 <span class="spinner">
            	 <input  id="spnWorkstartTime" name="spnWorkstartTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workstartTime}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
            	 </span></span>
            	 <span id="err_dteGmntWostartdate" class="tpm-errormsg" ></span>
			</div> 
			
		 	<div>
		 		<label  class="mandatory-lbl">Work End</label>
<!--	            <span style="padding-left: 68px;"><label class="mandatory-lbl"></label></span>-->
	        </div>
	        
            <div>
            	<input id="dteGmntWoenddate" name="dteGmntWoenddate"  clear = "false" class="easyui-datebox" style="width:120px;"  value="${requestScope.plmTlGenmaintenance.gmntWoenddate}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
            	<span style="padding-left:5px;"></span>
                <span class="spinner">
                <input  id="spnWorkendTime" name="spnWorkendTime"   class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workendTime}"<c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
                </span>
                <span id="err_dteGmntWoenddate" class="tpm-errormsg"  ></span>
			</div> 
			
			<div><label>Remarks</label></div>
		          	<div class="easyui-paddingbfpx" >
		          		<textarea rows="2" style="width: 255px;" cols="" id="txtGmntRemarks" name="txtGmntRemarks" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRemarks}</textarea>
		   			 </div>			         		
			</div>	
	</div>
	
			</td>
		</tr>
	</table>
</div>
<input type="hidden" id="mode" value="${ requestScope.generalMaintainanceBean.formMode }"></input>
<input type="hidden" id="hdnFactid" name="hdnFactid"/></input>
<input type="hidden" id="hdnGmntRefdocid" name="hdnGmntRefdocid" value="${ requestScope.plmTlGenmaintenance.gmntRefdocid }"></input>
<input type="hidden" id="hdnsetupandadj" name ="hdnsetupandadj" class="easyui-text" value="${requestScope.menumode } "/></input>
<input type="hidden" id="hdnLossValues" name="hdnLossValues"/></input>
<input type="hidden" id="hdndelMAMode" name="hdndelMAMode" value="${requestScope.delActivity}"></input>
</div>
</form>