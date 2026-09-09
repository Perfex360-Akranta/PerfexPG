<!--Author : Suresh.K-->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript"><!--
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var mode= getFilterValue(url+'&',"mode");
	initialiseForm("frmMouldUnloading");
    jQuery('#frmMouldUnloading .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmMouldUnloading textarea').css('text-transform', 'uppercase');
   
    var compId = getFieldValue('company','frmMouldUnloading');
	var locnId = getFieldValue('location','frmMouldUnloading');
	var factId = getFieldValue('factory','frmMouldUnloading');
	var sectId = getFieldValue('section','frmMouldUnloading');
	var cellId = getFieldValue('cell','frmMouldUnloading');
	var machId = getFieldValue('machine','frmMouldUnloading');
	if(machId != '' && machId != ' ' && machId != null ){
		fillComboBox("frmMouldUnloading","cmbMunlMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
	}else
		fillComboBox("frmMouldUnloading","cmbMunlMachineid","machineCombo.commonFilter");
	
	
	jQuery('#submitForm').val('frmMouldUnloading'); 
	formatDateBox('dteAllocateddate','dd-MMM-yyyy');
	formatDateBox('dteMunlStartdate','dd-MMM-yyyy');
	formatDateBox('dteMunlWostartdate','dd-MMM-yyyy');
	//formatDateBox('dteMunlOccureddate','dd-MMM-yyyy');
	formatDateBox('dteMunlShiftdate','dd-MMM-yyyy');
	formatDateBox('dteMunlBookeddate','dd-MMM-yyyy');
	formatDateBox('dteMunlWoenddate','dd-MMM-yyyy');
	formatDateBox('dteMunlTargetdate','dd-MMM-yyyy');
	readOnlyFields('cmbMunlKeyid');
	readOnlyFields('dteMunlBookeddate');
	readOnlyFields('dteMunlShiftdate');

	readOnlyFields('dteAllocateddate');
	readOnlyFields('spnAllocatedTime');
	
	if(url == "generalMaintcreat_input.genmain"){
		fillWithCurrentDate('dteMunlTargetdate');
		fillWithCurrentDate('dteMunlBookeddate');
	}
	if(mode != 'MODIFY' && mode != 'VIEW' ){
		
		//var occDate = getFieldValue('dteMunlOccureddate'); 
	   // var occTime = getFieldValue('spnoccuredTime');
		readOnlyFields('cmbMunlReportedby');
	}
	
/*---End ---*/
	
	var relatedToValue=getFieldValue('cmbMunlRelatedto');
	 if(relatedToValue == "MCH"){
			jQuery("#fact_label").removeClass('mandatory-lbl');
		    jQuery("#Mouldid_label").removeClass('mandatory-lbl');
		    jQuery("#eqp_label").addClass('mandatory-lbl');
		    readOnlyFields('cmbMunlMouldid');
		    jQuery('#cmbMunlMouldid').combobox('setValue',"-");
		   
			}
			else{ 
				jQuery("#fact_label").addClass('mandatory-lbl');
			    jQuery("#Mouldid_label").addClass('mandatory-lbl');
			    jQuery("#eqp_label").removeClass('mandatory-lbl');
			    //alert(mode);
			    /*if(mode != 'VIEW' ){
			    	
			    	enableFields('cmbMunlMouldid');
			    }*/
			}
	
	var statuscmb = jQuery('#cmbMunlStatus').val();
	if(statuscmb == 'C')
			jQuery( "#completed" ).css('display','block');	
	else 
		jQuery( "#completed" ).css('display','none');
	
		readOnlyFields('cmbMunlReportedby');
		
		spinnerChange('spnWorkstartTime','woStartEvt');
		spinnerChange('spnWorkendTime','woEndEvt');
		
	
		spinnerUp('spnWorkstartTime','woStartEvt');
		spinnerUp('spnWorkendTime','woEndEvt');
		
	
		spinnerDown('spnWorkstartTime','woStartEvt');
		spinnerDown('spnWorkendTime','woEndEvt');

		
		readOnlyFields('txtMunlWorkhours',"");
		readOnlyFields('txtMunlDowntime',"");
		readOnlyFields('txtMunlResponsetime',"");

		/* for functionalLocation*/
		var relatedTO = getFieldValue("cmbMunlRelatedto","frmMouldUnloading");
		if(relatedTO == 'MLD')
			lodFuncLoc(relatedTO);
		else	
			lodFuncLoc('MCH');
		/*---------*/	
		var stausVal = getFieldValue('cmbMunlStatus','frmMouldUnloading');
		
		if(stausVal != '' && stausVal != ' ' && stausVal != null)
			frmMouldUnloadingcmbMunlStatus_onSelect(stausVal);

		jQuery('#spansubLoss').hide();
		/*var menumode = jQuery('#hdnsetupandadj').val();
		if(menumode != '' && menumode != ' ' && menumode != null ){			
			setTimeout(function() {jQuery('#cmbMunlActivitytype').combobox('setValue','S');},1250);
			setTimeout(function() {jQuery('#cmbMunlActivitytype').combobox('setText','Setup And Adjustment');},1250);
			setTimeout(function() {readOnlyFields('cmbMunlActivitytype',"");},1250);
			//jQuery('#cmbMunlActivitytype').css('width','150px');
			jQuery('#spansubLoss').show();
			if(statuscmb != 'C')
				disableUIButton('btnsubLoss');
			//jQuery('#spansubLoss').css('display','block');
		}*/
		setTimeout(function() {readOnlyFields('cmbMunlRelatedto');},1250);
		setTimeout(function() { readOnlyFields('cmbMunlMouldid');},1250);
		setTimeout(function() {fillCompletedDate();},1250);
		//var dataStr = "?q=2&mldId="+jQuery('#cmbMunlMouldid').combobox('getValue');		
		//fillComboBox("frmMouldUnloading","cmbMunlPhenid","combo_phenomena.brdn"+dataStr);
		//fillComboBox("frmMouldUnloading","cmbMunlPhenid","combo_phenomena.brdn?q=2");
});
function fillCompletedDate()
{
	var workStart = jQuery('#dteMunlWostartdate').datebox('getValue');
	var workEnd = jQuery('#dteMunlWoenddate').datebox('getValue');

	if(workStart == null  || workStart == '' || workStart == ' ')
	{
		fillWithCurrentDate('dteMunlWostartdate');
		fillWithCurrentDate('spnWorkstartTime');
	}
	if(workEnd == null  || workEnd == '' || workEnd == ' ')
	{
		fillWithCurrentDate('dteMunlWoenddate');
		fillWithCurrentDate('spnWorkendTime');
	}
}
jQuery('#btnsubLoss').click(function(){
	LoadPopUp("divSubLoss","subloss_input.genmain", true,"740px","400px","-55px","7%", "SubLossSNA_Callback","Sub Loss");
});
function frmMouldUnloading_errorCallback(){
	
}

function divSubLoss_afterClose()
{
	
}

function lodFuncLoc(datStr){
	var compId = getFieldValue('company','frmMouldUnloading');
	var locnId = getFieldValue('location','frmMouldUnloading');
	var factId = getFieldValue('factory','frmMouldUnloading');
	var sectId = getFieldValue('section','frmMouldUnloading');
	var cellId = getFieldValue('cell','frmMouldUnloading');
	var machId = getFieldValue('machine','frmMouldUnloading');	
	var relTo = '';
	var dataStr = "&factId="+factId+"&sectionId="+sectId+"&cellId="+cellId +"&machId="+machId;
	//alert(dataStr);
	var url = jQuery('#hiddenUrl').val();
	var vurl = url.substring(0,url.indexOf('?'));
	
	if(datStr !=' ' && datStr !='' && datStr != null){
		if(vurl  == "generalMaint_modify.genmain"){
			relTo = "?relTo=MCH";
			reloadCombo("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&mchId="+machId);
		}
		else
			relTo = "?relTo="+datStr;
		
		if(datStr.substring(0,5) == "onsel")
		 dataStr += "&machId="+datStr.substring(5,datStr.length);
		 //alert(url.substring(url.indexOf('&vurl')+6,url.indexOf('&closeOnSave')));
			var relatedTO = getFieldValue("cmbMunlRelatedto","frmMouldUnloading");
		if("MLD" == relatedTO){
			fillComboBox("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&relatedto=MLD");
		}
		else
			fillComboBox("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&relatedto=MCH");
	}
	loadFunctionalLocation("MunlfunLocation","functionalLoc.genmain"+relTo,"MunlfunLocationValues","frmMouldUnloading",dataStr);
	
}
/*calculate time difference*/
						
   
	
	
	function woStartEvt()
	{     
		dteMunlWostartdate_onSelect(new Date());
		
		/*if(jQuery("#spnWorkstartTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + '00:00';				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			alert(occDate + ' : '+wsDate);
			respTime =timeDifference(occDate,wsDate);
			alert(respTime.minutes);
			displayText('txtMunlResponsetime',respTime.minutes);
		}*/
		/*to get shift based on Time**/
		var fctid = getFieldValue('cmbMunlFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);
	}
	function woEndEvt()
	{    
		dteMunlWoenddate_onSelect(new Date());
		 							
		/*if(jQuery("#spnWorkstartTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + '00:00';				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteMunlWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtMunlWorkhours',actualMins.minutes);										
			actualMins = timeDifference(wsDate,weDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtMunlDowntime',downTime.minutes);
		}*/
		enableUIButton('btnsubLoss');
 }


	
	function dteMunlWostartdate_onSelect(date)
  	{
		//fillWithCurrentDate('spnWorkstartTime');
		compareDates('dteMunlBookeddate','','dteMunlWostartdate','spnWorkstartTime','Work start should be Equal or Greater than  Booked Date');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null)
		{		
			var occTime = jQuery("#hdnBookedTime").val().trim();	
			if(occTime == null || occTime == '' || occTime == ' ')
				occTime = 	'00:00';
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + occTime;				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteMunlWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtMunlResponsetime',respTime.minutes);
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtMunlWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtMunlDowntime',downTime.minutes);
		}
		/*to get shift based on Time**/
		var fctid = getFieldValue('cmbMunlFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);
	}
	function dteMunlWostartdate_onChange(date)
  	{
		//fillWithCurrentDate('spnWorkstartTime');
		
		//compareDates('dteMunlBookeddate','','dteMunlWostartdate','spnWorkstartTime','Work start should be equal or Greater than  Booked Date');
		/*if(jQuery("#spnWorkstartTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + '00:00';				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtMunlResponsetime',respTime.minutes);
		}*/
		/*to get shift based on Time**/
		/*var fctid = getFieldValue('cmbMunlFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		fill_shift(fctid,"","",time);*/
	}
	function dteMunlWoenddate_onChange(date)
  	{

		/*compareDates('dteMunlWostartdate','spnWorkstartTime','dteMunlWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null)
		{					
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + '00:00';				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteMunlWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtMunlWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtMunlDowntime',downTime.minutes);
			
		}*/
    }	
	function dteMunlWoenddate_onSelect(date)
  	{
		compareDates('dteMunlWostartdate','spnWorkstartTime','dteMunlWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null)
		{				
			var occTime = jQuery("#hdnBookedTime").val().trim();	
			if(occTime == null || occTime == '' || occTime == ' ')
				occTime = 	'00:00';	
			var occDate = jQuery("#dteMunlBookeddate").datebox('getValue') + occTime;				
			var wsDate = jQuery("#dteMunlWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteMunlWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			displayText('txtMunlWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtMunlDowntime',downTime.minutes);
			
		}
		enableUIButton('btnsubLoss');
    }
	 function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
	 {
		 
		   var timeFlag = '00:00';
		  
		   if(fromTimeId != '')
			   timeFlag = jQuery('#'+fromTimeId).spinner('getValue');
		  
			   
		   var fromDate = jQuery('#'+fromDateId).datebox('getValue') + timeFlag;
		   if(fromDateId == 'dteMunlBookeddate')
			   fromDate = jQuery('#hdnAllottedDateTime').val().trim();
		 
		   var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
		  
		   var currentDate = getServerDateTime();
		
		   if(convertStringToDate(toDate) > currentDate)
		   {
			  showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
		      fillWithCurrentDate(toDateId);			
			  fillWithCurrentDate(toTimeId);
		   }
		   else if( compareDateTime(toDate,fromDate) > 0 )
		   {
	          showValidationErrorMsg(toDateId,errMsg);
	          fillWithCurrentDate(toDateId);			
			  fillWithCurrentDate(toTimeId);
		   }
	       else
	          clearValidationErrorMsg(toDateId);	
	}
	
/*end of calculate time diff
function frmMouldUnloadingcmbMunlFactoryid_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlSectionid","sectionCombo.commonFilter" );
}
function frmMouldUnloadingcmbMunlSectionid_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlLineid","cellCombo.commonFilter");
}	
function frmMouldUnloadingcmbMunlLineid_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlMachineid","machineCombo.commonFilter");
}*/
function frmMouldUnloadingcmbMunlMachineid_onLoadSuccess()
{ 
	fillComboBox("frmMouldUnloading","cmbMunlRelatedto","relatedto.commonFilter");
	
}
function frmMouldUnloadingcmbMunlStationid_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlRelatedto","relatedto.commonFilter");
	
}
function frmMouldUnloadingcmbMunlRelatedto_onLoadSuccess()
{
	//alert('Related To Success');
	fillComboBox("frmMouldUnloading","cmbMunlMouldid","mould.commonFilter");
	fillComboBox("frmMouldUnloading","cmbMunlShift","combo_GmntShift.genmain");
	jQuery('#cmbMunlShift').combobox('disable');	
	readOnlyFields('cmbMunlRelatedto');
}
function frmMouldUnloadingcmbMunlMouldid_onLoadSuccess()
{
	//alert('Mould Success');
	//fillComboBox("frmMouldUnloading","cmbMunlShift","combo_GmntShift.genmain");
	//jQuery('#cmbMunlShift').combobox('disable');	
}
function frmMouldUnloadingcmbMunlShift_onLoadSuccess()
{
	//alert('Shift Success');
	fillComboBox("frmMouldUnloading","cmbMunlActivitytype","combo_activitytype.genmain?q=2&MldUnload=true");
}
function frmMouldUnloadingcmbMunlActivitytype_onLoadSuccess()
{
	readOnlyFields('cmbMunlActivitytype');
	fillComboBox("frmMouldUnloading","cmbMunlTrade","combo_GmntTrade.genmain");
}
function frmMouldUnloadingcmbMunlTrade_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlMchcondition","combo_machinecondition.genmain");
}
function frmMouldUnloadingcmbMunlMchcondition_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlReportedby","combo_GmntReportedby.genmain");
}
function frmMouldUnloadingcmbMunlReportedby_onLoadSuccess()
{
	fillComboBox("frmMouldUnloading","cmbMunlStatus","combo_GmntStatus.genmain");
}
function frmMouldUnloadingcmbMunlStatus_onLoadSuccess()
{
	//fillComboBox("frmMouldUnloading","cmbMunlCompletedby","combo_GmntShiftcompletedby.genmain");
	fillComboBox("frmMouldUnloading","cmbMunlCompletedby","employee.commonFilter?q=2&dept=maintenance" );
}
function frmMouldUnloadingcmbMunlCompletedby_onLoadSuccess()
{
	
	
	/*var url = jQuery('#hiddenUrl').val();//alert(url);
	var viewUrl = url.substring(64,89);
	if(viewUrl == 'generalMaint_view.genmain'){
		disableForm('frmMouldUnloading');
	}*/
}

function  frmMouldUnloadingcmbMunlMachineid_onClear(record)
{
	jQuery('#linfrmMouldUnloadingMachine').html(' ');
	jQuery("#frmMouldUnloading input[id='machine']").val('');
	var relatedTO = getFieldValue("cmbMunlRelatedto","frmMouldUnloading");
	if(relatedTO == 'MLD')
		lodFuncLoc(relatedTO);
}
function  frmMouldUnloadingcmbMunlMachineid_onSelect(record)
{
	var relatedToValue=getFieldValue('cmbMunlRelatedto');
	if(relatedToValue == "MCH"){
		reloadCombo("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&mchId="+record.id);
		reloadCombo("frmMouldUnloading","cmbMunlMouldid","mould.commonFilter?q=2&machineId="+record.id);
	}
	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbMunlLineid","cmbMunlSectionid","cmbMunlFactoryid","cmbComp");
	lodFuncLoc("onsel"+record.id);
	//loadFunctionalLocation("MunlfunLocation","functionalLoc.genmain","MunlfunLocationValues","frmMouldUnloading","&machId="+record.id);
	var fctid = getFieldValue('cmbMunlFactoryid');
	var linid = getFieldValue('cmbMunlLineid');
	var secid = getFieldValue('cmbMunlSectionid');
	/*readOnlyFields('cmbMunlFactoryid');
	readOnlyFields('cmbMunlLineid');
	readOnlyFields('cmbMunlSectionid');*/
	readOnlyFields('cmbMunlMachineid');
	fill_shift(fctid,secid,linid,"");
}
function  frmMouldUnloadingcmbMunlLineid_onSelect(record)
{
	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbMunlSectionid","cmbMunlFactoryid");
	var lineid = record.id;
	fill_shift("","",lineid);
		
}
function  frmMouldUnloadingcmbMunlSectionid_onSelect(record)
{
	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbMunlFactoryid");
	var sectid = record.id;
	
	fill_shift("",sectid);	
}
function  frmMouldUnloadingcmbMunlFactoryid_onSelect(record)
{
	jQuery("#cmbMunlSectionid").combobox('clear');
	jQuery("#cmbMunlLineid").combobox('clear');
	jQuery("#cmbMunlMachineid").combobox('clear');
	reloadCombo("frmMouldUnloading","cmbMunlSectionid","sectionCombo.commonFilter?factId="+record.id);
	reloadCombo("frmMouldUnloading","cmbMunlLineid","cellCombo.commonFilter?factId="+record.id  );
	reloadCombo("frmMouldUnloading","cmbMunlMachineid","machineCombo.commonFilter?factId="+ record.id );
	/*var factryid = record.id;
	var factid = jQuery('#hdnFactid').val(factryid);
		("factory onload success"+factid );
	*/
}
function  frmMouldUnloadingcmbMunlRelatedto_onSelect(record)
{	
	clearField('cmbMunlStationid');
  if(record.id == "MCH"){
	jQuery("#fact_label").removeClass('mandatory-lbl');
    jQuery("#Mouldid_label").removeClass('mandatory-lbl');
    jQuery("#eqp_label").addClass('mandatory-lbl');
    readOnlyFields('cmbMunlMouldid');
    jQuery('#cmbMunlMouldid').combobox('setValue',"-");
    var machId=getFieldValue("cmbMunlMachineid", "frmMouldUnloading");
    reloadCombo("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&mchId="+machId);
	reloadCombo("frmMouldUnloading","cmbMunlMouldid","mould.commonFilter?q=2&machineId="+machId);
	}
	else{ 
		jQuery("#fact_label").addClass('mandatory-lbl');
	    jQuery("#Mouldid_label").addClass('mandatory-lbl');
	    jQuery("#eqp_label").removeClass('mandatory-lbl');
	    enableFields('cmbMunlMouldid');
	    reloadCombo("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter?q=2&relatedto=MLD");
	    
	}
}	
function  frmMouldUnloadingcmbMunlStatus_onSelect(record)
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


function frmMouldUnloading_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFieldValue('cmbMunlMachineid',keyIds.machId);
		
	var factryid = keyIds.factId;
	jQuery("#cmbMunlShift").combobox('clear');
	fill_shift(factryid);
	
	if( keyIds.machId == null || keyIds.machId == 'null'){
		//alert('d');
		clearField('cmbMunlMachineid');
	}
	else{
		reloadMachine("frmMouldUnloading",'cmbMunlMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		//fillComboBox("frmMouldUnloading","cmbMunlStationid","assembly.commonFilter");
		/*added on 21st jun*/
		var machId = getFieldValue("cmbMunlMachineid","frmMouldUnloading");
		var relatedto_val = getFieldValue("cmbMunlRelatedto","frmMouldUnloading");
		
		if(machId != ' ' && machId != '' && machId != null){
			if(relatedto_val!="MLD"){
				readOnlyFields('cmbMunlMouldid');/*added on 21-jun*/
			}	
			reloadCombo("frmMouldUnloading","cmbMunlMouldid","mould.commonFilter?q=2&mchId="+machId);
		}
	}
	var url = jQuery('#hiddenUrl').val();
	var vurl = url.substring(63,90);
	if(vurl  == "generalMaint_modify.genmain"){
		readOnlyFields('cmbMunlFactoryid');
	   	readOnlyFields('cmbMunlLineid');
	   	readOnlyFields('cmbMunlSectionid');
	   	readOnlyFields('cmbMunlMachineid');   	
	   	}
	/*End*/
	
}
function fill_shift(factryid,sectid,lineid,time){
	
	var factryid =factryid;
	var sectid = sectid;
	var lineid =lineid;
	var time  = time;
	
	//alert(fctid+"--"+linid+"--"+secid);
	var fromTime = jQuery('#dteMunlBookeddate').val();	
		//alert("line  "+lineid);				
	var dataString = '?q=2&factId='+factryid+'&sectId=';
		dataString += '&cellId=&fromTime='+time;	
		   // alert(dataString);
	processAjaxCalls('fill_shift.genmain',dataString,'getShift','getShiftErr');
	
}
function  getShift(record)
	{      
		jQuery("#cmbMunlShift").combobox('clear');
		jQuery('#cmbMunlShift').combobox('setValue',record.shift);               	
	}
jQuery('#back_genMain').click(function(){
	
	   jQuery("#GeneralMaintMain").load('generalMaint_input.genmain','', function(response, status, xhr) {
	   if (status == "error") {
	   var msg = "Sorry but there was an error: ";
	   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
	  }
	});
	  
	   
});
function frmMouldUnloading_successsCallback(result)
{	
	var backTo = result.successData.backTo;
	var prevPage = result.BACKTO;
	if(backTo != null && backTo != ' ' && backTo != '')
	{
		if(backTo == "MUFromWOServlet")
		{
			if(prevPage != null && prevPage != ' ' && prevPage != '')
				popFormNavigation();
			 navigateToPrevForm();
		}
	}
}
function frmMouldUnloading_beforeDelete()
{
	var delActivity = jQuery('#hdndelMAMode').val();				
	if(delActivity == 'Y')
	{
		alert('Activity Cannot be deleted');
		return false;
	}
	var delMsg = "Do You Want To Delete This  ("+jQuery('#cmbMunlKeyid').combobox('getValue')+")";
	if(confirm(delMsg) == false)
	{
		return false;
	}
}


</script>
<form id="frmMouldUnloading" name="frmMouldUnloading">
<div id="wrapper" style="width:1250px;">
<div id="Genmaint" class="divbrdr">

<!--	<div style="float:right;"><input type="button"  value="BACK" id="back_genMain" class="easyui-button" /></div><br><br>-->
	<table width="92%" align="center" >
			 <tr>
			 	 <td style="width: 100%"> 
			         <div  id="frmMouldUnloadingFuntKeyIds"  >
						<input type="hidden" id="factory" name="cmbMunlFactoryid" value="${requestScope.mldTlMouldunloadmst.munlFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbMunlSectionid" value="${requestScope.mldTlMouldunloadmst.munlSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbMunlLineid" value="${requestScope.mldTlMouldunloadmst.munlLineid}"  ></input>
						<input type="hidden" id="machine" name="cmbMunlMachineid" value="${requestScope.mldTlMouldunloadmst.munlMachineid}"></input>
					 </div>
					 <div id="MunlfunLocation" style="padding-left: 0px;"></div>
				  </td>
				  </tr> 
				  
				  
				  <tr> 
				  <td  style="padding-left: 0px;">
				  <div  >
					<label  class="mandatory-lbl">Related To</label>
	            	<span style="padding-left:80px;"><label id="Mouldid_label">Mould</label></span>
	            	<span style="padding-left:125px;"><label id="eqp_label" class="mandatory-lbl">Equipment</label></span>
	            	</div>
		            <div  >
		            	<input id="cmbMunlRelatedto" name="cmbMunlRelatedto" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  readonly="readonly" value="${requestScope.mldTlMouldunloadmst.munlRelatedto }" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>
		<!--            	<input id="MunlRelatedto" type="hidden" class="easyui-text" value="${requestScope.mldTlMouldunloadmst.munlRelatedto }"/>-->
		            	<span style="padding-left:5px;">
		             	<input id="cmbMunlMouldid" name="cmbMunlMouldid" class="easyui-combobox"  style="width:131px;text-transform:capitalize;" value="${requestScope.mldTlMouldunloadmst.munlMouldid}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></span>
		             	
		             	<span style="padding-left:24px;"><input id="cmbMunlMachineid" name="cmbMunlMachineid" class="easyui-combobox" onkeydown="" style="width:272px;" value="${requestScope.mldTlMouldunloadmst.munlMachineid}"  <c:out value = "${ requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/></span>
		             	<span style="padding-left:5px;">
		             		<input type="text" class="easyui-text" id = "txtMldStatus" name="txtMldStatus"  tabindex = "-1" disabled value="${requestScope.mouldStatus}" style="width:150px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
		             	</span>
		             	<span id="err_cmbMunlRelatedto" class="tpm-errormsg" ></span>
		             	<span id="err_cmbMunlMouldid" class="tpm-errormsg" style="margin-left:13%;"></span>
		             	<span id="err_cmbMunlMachineid" class="tpm-errormsg" style="margin-left:28%;"></span>
					</div> 
				  </td>
				  <td style="width: 100%">
					<!--<div id="eqp_label" class="mandatory-lbl"><label >Equipment</label></div>
				        <div class="easyui-paddingbfpx">
				     	<input id="cmbMunlMachineid" name="cmbMunlMachineid" class="easyui-combobox" onkeydown="" style="width:272px;" value="${requestScope.mldTlMouldunloadmst.munlMachineid}"  <c:out value = "${ requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
					</div>
				--></td>
			 	</tr> 			 
			 </table>
			 
			 
			 <table> 
	          <tr width="" >
	          	
	          	<td style="padding-left: 0px;" valign="top"> 
	          	<div class="sub-header"><span style="">Main Information</span></div>
	           		<div ><label>Document No</label>
		            <span style="padding-left:61px;"><label >Date</label></span></div>
		            <div class="easyui-paddingbfpx"  >
		            	<input id="cmbMunlKeyid" name="cmbMunlKeyid" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  value="${requestScope.mldTlMouldunloadmst.munlKeyid}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
		            	<span style="padding-left:5px;">
		            	<input class="easyui-datebox" clear = "false" id="dteMunlBookeddate" name="dteMunlBookeddate" style="width:130px;" value="${requestScope.mldTlMouldunloadmst.munlBookeddate}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
						</span>
					</div> 
	                <div>
			 			<label>Received Date</label>		      	 
		     		</div>
		     
           			 <div>
		            	 <input id="dteAllocateddate" name="dteAllocateddate"  clear = "false" class="easyui-datebox" style="width:135px;"   value="${requestScope.mldTlMouldunloadmst.munlAllocateddate}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></input>
		            	 <span style="padding-left:5px;">
		            	 <span class="spinner"><input  id="spnAllocatedTime" name="spnAllocatedTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.allocatedDateTime}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></span></span>
		    		</div> 
			
					
					<div ><label class="mandatory-lbl">Assembly/Station</label></div>
				    <div class="easyui-paddingbfpx" >
				       	<input id="cmbMunlStationid" name="cmbMunlStationid" class="easyui-combobox"  style="width:275px;" value="${requestScope.mldTlMouldunloadmst.munlStationid}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/> >
				    </div>
				    <div ><label class="mandatory-lbl">Reason</label></div>
				    <div class="easyui-paddingbfpx" >
						<input id="txtMunlPhenid" name="txtMunlPhenid" value="${requestScope.mldTlMouldunloadmst.munlPhenid}"  class="easyui-text" style="width: 275px" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
						<span id="err_txtMunlPhenid" class="tpm-errormsg"></span>
					</div>
			

			<div  ><label  class="mandatory-lbl">Shift Date</label>
            	<span style="padding-left: 80px;"><label class="mandatory-lbl">Shift</label></span>
            </div>
<!--            <div  >-->
			<table>
			<tr>
            <td>	<input clear = "false"  id="dteMunlShiftdate" name="dteMunlShiftdate" class="easyui-datebox" style="width:130px;" value="${requestScope.mldTlMouldunloadmst.munlShiftdate}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></input>
            </td>	
            <td>	<span style="padding-left:5px;">
            		<input  id="cmbMunlShift" name="cmbMunlShift"  class="" style="width: 133px;" value="${requestScope.mldTlMouldunloadmst.munlShift}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>
            	  	</span>
            	  	</td>	
            	</tr>
          </table>  	
<!--			</div> -->
			
		<table  style="width:255px;">
           	<tr><td>	<span id="err_dteMunlShiftdate" class="tpm-errormsg"></span> </td>
           		<td><span id="err_cmbMunlShift" class="tpm-errormsg"></span>   </td></tr>            
		</table>
			

			<div  >
				<label >Actual Time</label>
            	<span style="padding-left: 70px;"><label >Down Time</label></span>
            </div>
            <div class="easyui-paddingbfpx" >
            	<input id="txtMunlWorkhours" name="txtMunlWorkhours" class="easyui-text" style="width:130px;text-align: right;" value="${requestScope.mldTlMouldunloadmst.munlWorkhours}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/>
            	<span style="padding-left:5px;">
             	<input id="txtMunlDowntime" name="txtMunlDowntime" class="easyui-text"  style="width:135px;text-transform:capitalize;text-align: right;" value="${requestScope.mldTlMouldunloadmst.munlDowntime}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></span>
			</div> 
			
			<div>
				<label>Response Time</label>
			</div>
			<div class="easyui-paddingbfpx">
				<input id="txtMunlResponsetime" name="txtMunlResponsetime" class="easyui-text"  style="width:135px;text-align: right;" value="${requestScope.mldTlMouldunloadmst.munlResponsetime}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>> 
			</div>					
	</td>
	
	
	<td valign="top" style="padding-left:25px;" width="50%">
		
		<div class="sub-header"  ><span style="">Details For Mould Unloading</span></div>
			<div style="float:left;margin-left:5px;">
				<div style="padding-top:5px">
					<label>Part Location</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="txtMunlPartlocation" name="txtMunlPartlocation" class="easyui-text"  style="width:255px;" value="${requestScope.mldTlMouldunloadmst.munlPartlocation}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>> 
				</div>
				
				<div>
					<label class="mandatory-lbl">Activity Type</label>
				</div>
				
				<div class="easyui-paddingbfpx"  >
					<input id="cmbMunlActivitytype" name="cmbMunlActivitytype" class="easyui-combobox"  style="width:155px;text-transform:capitalize;"  readonly="readonly" value="${requestScope.mldTlMouldunloadmst.munlActivitytype }" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>
					<span id="spansubLoss">
					<input type="button" value="Sub Loss" id="btnsubLoss" class="easyui-button" style="height:25px;"/ >
					</span>
				</div>
				
				<div style="margin-top: 5px;">
					<label>Maintenance Section</label>
				</div>
				
				<div class="easyui-paddingbfpx">
					<input id="cmbMunlTrade" style="width:255px;" name="cmbMunlTrade" class="easyui-combobox"  value="${requestScope.mldTlMouldunloadmst.munlTrade}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>> 
				</div>
<!--	modified		-->
				<div style="margin-top: 13px;" >
					<label >Machine Condition</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbMunlMchcondition" style="width:255px;" name="cmbMunlMchcondition" class="easyui-combobox"  value="${requestScope.mldTlMouldunloadmst.munlMchcondition}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>> 
				</div>
			
				<div style="margin-top: 12px;">
					<label class="mandatory-lbl">Reported By</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbMunlReportedby" style="width:255px;" name="cmbMunlReportedby" class="easyui-combobox"  value="${requestScope.mldTlMouldunloadmst.munlReportedby}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>> 
				</div>
				
				<div style="margin-top:1px;">
					<label class="mandatory-lbl">Target Date</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input clear = "false"  id="dteMunlTargetdate" name="dteMunlTargetdate" class="easyui-datebox" style="width:110px;" value="${requestScope.mldTlMouldunloadmst.munlTargetdate}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></input></span>
					<span id="err_dteMunlTargetdate" class="tpm-errormsg"  ></span>
				</div>

				
			</div><!-- end of float left -->
			<div style="float:right;margin-left:20px;" >
			
			<div  style="margin-top: 5px;">
				<label class="mandatory-lbl">Reason for Unloading</label>
			</div>
			
	        <div class="easyui-paddingbfpx" >
	          	<textarea rows="2" style="width: 255px;height:65px;" cols="" id="txtMunlReason" maxlength="500" name="txtMunlReason" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mldTlMouldunloadmst.munlReason}</textarea>
	        </div>
	          
          	
          	
			<div style="margin-top:5px;" >
				<label id="actionTaken" class="">Action Taken</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtMunlAction" name="txtMunlAction" maxlength="500" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mldTlMouldunloadmst.munlAction}</textarea>
          	</div>
          	<div style="margin-top:5px;">
          		<label>Root Cause</label>
          	</div>
          	
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtMunlRootcauseid" name="txtMunlRootcause" maxlength="500" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mldTlMouldunloadmst.munlRootcause}</textarea>
          	</div>
			<div  >
				<label>Counter Measure</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 255px;height:65px" cols="" id="txtMunlCountermeasure" name="txtMunlCountermeasure" maxlength="500" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mldTlMouldunloadmst.munlCountermeasure}</textarea>
          	</div>

				
		</div>
	
	</td>
	<td valign="top" style="padding-left:15px;">
				
			<div style="margin-top:40px;">
				<label class="mandatory-lbl">Status</label>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-bottom:15px;">
				<input id="cmbMunlStatus" name="cmbMunlStatus" class="easyui-text"  style="width:255px; " value="${requestScope.mldTlMouldunloadmst.munlStatus}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>
			</div>
				
			<div id="completed" style="display:none;margin-top:50px;">
			
			<div class="sub-header">
				<span style="padding-left:5px;">Completed Details</span>
			</div>
			
			<div style="padding-left:10px;">
				
			<div class="mandatory-lbl" style="font-size: 12;">Completed By</div>
			 <div class="easyui-paddingbfpx">
			 	<input id="cmbMunlCompletedby" name="cmbMunlCompletedby" class="easyui-combobox"  style="width:255px;"  value="${requestScope.mldTlMouldunloadmst.munlCompletedby}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>/> 
			 </div>
			 
			 <div>
			 	<label  class="mandatory-lbl">Work Start</label>		      	 
		     </div>
		     
            <div  >
            	<input id="dteMunlWostartdate" name="dteMunlWostartdate"  clear = "false" class="easyui-datebox" style="width:120px;"   value="${requestScope.mldTlMouldunloadmst.munlWostartdate}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></input>
            	<span style="padding-left:5px;">
            	 <span class="spinner"><input  id="spnWorkstartTime" name="spnWorkstartTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.MldUnloadBean.workstartTime}" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></span></span>
            	 <span id="err_dteMunlWostartdate" class="tpm-errormsg" ></span>
			</div> 
			
		 	<div>
		 		<label  class="mandatory-lbl">Work End</label>
<!--	            <span style="padding-left: 68px;"><label class="mandatory-lbl"></label></span>-->
	        </div>
	        
            <div  >
            	<input id="dteMunlWoenddate" name="dteMunlWoenddate"  clear = "false" class="easyui-datebox" style="width:120px;"  value="${requestScope.mldTlMouldunloadmst.munlWoenddate}"  <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></input>
            	<span style="padding-left:5px;"></span>
                <span class="spinner"><input  id="spnWorkendTime" name="spnWorkendTime"   class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.MldUnloadBean.workendTime}"<c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>></span>
                <span id="err_dteMunlWoenddate" class="tpm-errormsg"  ></span>
			</div> 
			
			<div><label>Remarks</label></div>
		          	<div class="easyui-paddingbfpx" >
		          		<textarea rows="2" style="width: 255px;" cols="" id="txtMunlRemarks" name="txtMunlRemarks" <c:out value = "${requestScope.MldUnloadBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mldTlMouldunloadmst.munlRemarks}</textarea>
		   			 </div>			         		
			</div>	
				</div>
			</td>
		</tr>
	</table>
</div>
<input type="hidden" id="mode" value="${ requestScope.MldUnloadBean.formMode }">
<input type="hidden" id="hdnBookedTime" value="${ requestScope.bookedTime}">
<input type="hidden" id="hdnAllottedDateTime" value="${ requestScope.allottedDateTime}">
<input type="hidden" id="hdnFactid" name="hdnFactid"/>
<input type="hidden" id="hdnMunlRefdocid" name="hdnMunlRefdocid" value="${ requestScope.mldTlMouldunloadmst.munlRefdocid }">
<!--<input type="hidden" id="hdnsetupandadj" name ="hdnsetupandadj" class="easyui-text" value="${requestScope.menumode } "/>-->
<input type="hidden" id="hdnLossValues" name="hdnLossValues"/>
<input type="hidden" id="hdndelMAMode" name="hdndelMAMode" value="${requestScope.delActivity}">
</div>
</form>