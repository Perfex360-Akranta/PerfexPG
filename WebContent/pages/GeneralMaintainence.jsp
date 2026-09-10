<!--Author : Manikandan-->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">
var gmntCostInfoMode = null; 
jQuery(document).ready(function(){
  	
	initialiseForm("frmGenralMaintenance");
	jQuery('#submitForm').val('frmGenralMaintenance');
	
	
	jQuery('#btnGmntEstimation').click(function(){
		alert(1234);
var wsDate = jQuery('#dteGmntWostartdate').datebox('getValue');
var wsTime = jQuery('#spnWorkstartTime').spinner('getValue');
var weDate = jQuery('#dteGmntWoenddate').datebox('getValue');
var weTime = jQuery('#spnWorkendTime').spinner('getValue');

var woStartFilled = (wsDate != null && wsDate != '' && wsTime != null && wsTime != '');
var woEndFilled    = (weDate != null && weDate != '' && weTime != null && weTime != '');

if(woStartFilled == true && woEndFilled == true){
    gmntCostInfoMode = "estimate";   // no window. prefix needed
    saveForm('frmGenralMaintenance','costinfo.balgenmain?q=2&repType=Estimate&refDocType=GEN');
}
else{
    alert('Select Work Start and Work End');
}
});

jQuery('#btnGmntActual').click(function(){
gmntCostInfoMode = "actual";
saveForm('frmGenralMaintenance','costinfo.balgenmain?q=2&repType=Actual&refDocType=GEN');
});
	//mano end 
	
	
	// set the id of form to submit
	
	//processGridnew("sapInfo_input.genmain","sapInfoGrid","sapinfopager","","dbl","","loadSuccess");
	var checkY=null;
    jQuery('#frmGenralMaintenance .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmGenralMaintenance textarea').css('text-transform', 'uppercase');
    /*added on 21st jun*/
   
    var sapsts=jQuery('#hdnErrppostStatus').val();
    var gensts=jQuery('#cmbGmntStatus').combobox('getValue');
    var statusSap=null;
   // alert(sapsts+"nnnnnnn"+gensts);
  //  alert(sapsts+"nnnnnnn");
    if(sapsts=="C"&&gensts=="C"){
        statusSap="Completed in SAP";
    }
    else if(sapsts=="X"&&gensts=="C"){
    	statusSap="Completed in Perfex Not in SAP";
	//alert(statusSap);
    }
    jQuery('#txtErrppostStatus').val(statusSap);
	//*/
    var url = jQuery('#hiddenUrl').val();
    
	var mode= getFilterValue(url+'&',"mode");
	//setFieldValue('ldmode',mode);
	var md=jQuery('#ldmode').val();
	//alert(md);
    var compId = getFieldValue('company','frmGenralMaintenance');
	var locnId = getFieldValue('location','frmGenralMaintenance');
	var factId = getFieldValue('factory','frmGenralMaintenance');
	var sectId = getFieldValue('section','frmGenralMaintenance');
	var cellId = getFieldValue('cell','frmGenralMaintenance');
	var machId = getFieldValue('machine','frmGenralMaintenance');
	var activity = jQuery('input[id=hdnTmpActType]').val();

	
	
	if(machId != '' && machId != ' ' && machId != null ){
			
		fillComboBox("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
		
		}else
		fillComboBox("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter");

	fillComboBox("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?relatedto=MCH");
	/*readOnlyFields('dteGmntShiftdate');*/
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
	//	fillWithCurrentDate('dteGmntShiftdate');
	//	fillWithCurrentDate("dteGmntOccureddate");
	//fillWithCurrentDate("spnoccuredTime");
	var keyId=getFieldValue('cmbGmntKeyid');
	var vurl = url.substring(0,url.indexOf('?'));
		if(vurl == "generalMaintcreat_input.balgenmain"&&keyId==""||keyId==null){
			
		//jQuery('#cmbGmntKeyid').combobox('disable');
		fillWithCurrentDate('dteGmntShiftdate');
		//fillWithCurrentDate('dteGmntOccureddate');//filling Current Date
		//fillWithCurrentDate('spnoccuredTime');//filling Current Time
		fillWithCurrentDate('dteGmntTargetdate');
		fillWithCurrentDate('dteGmntBookeddate');
		//fillWithCurrentDate('dteGmntWostartdate');
		//fillWithCurrentDate('dteGmntWoenddate');
		// Occured Date + Time ku konjam delay kudukanum,
	    // illana timespinner widget ready aagi irukkathu, adhanala time fill aagathu
	    setTimeout(function () {
	        fillWithCurrentDate('dteGmntOccureddate');
	        fillWithCurrentDate('spnoccuredTime');

	        var fctid = getFieldValue('cmbGmntFactoryid');
	        var time  = jQuery("#spnoccuredTime").spinner('getValue');
	        txt_shift(fctid,"","",time);
	    }, 500);
	}
		
		
		else{
			jQuery('#dteGmntShiftdate').val(jQuery('#hdndteGmntShiftdate').val());
			jQuery('#dteGmntOccureddate').val(jQuery('#hdndteGmntOccureddate').val());
			jQuery('#spnoccuredTime').val(jQuery('#hdnspnoccuredTime').val());//filling Current Time
			jQuery('#dteGmntTargetdate').val(jQuery('#hdndteGmntTargetdate').val());
			jQuery('#dteGmntBookeddate').val(jQuery('#hdndteGmntBookeddate').val());
			jQuery('#dteGmntWostartdate').val(jQuery('#hdndteGmntWostartdate').val());
			jQuery('#dteGmntWoenddate').val(jQuery('#hdndteGmntWoenddate').val());
			}
		var sts=jQuery('#hdnStatus').val();
		//alert(sts+"       stst");
		if(sts=="C"){
			//alert(2);
		jQuery("#chkbdSubmittoSap").prop('disabled', false);
		}
		else{			
		//jQuery("#chkbdSubmittoSap").prop('disabled',false);
		jQuery("#chkbdSubmittoSap").prop('disabled',true);			
		}
		
	//jQuery('#dteGmntShiftdate').datebox('disable');
	//jQuery("#dteGmntOccureddate").datebox('disable');
	//jQuery("#spnoccuredTime").spinner('disable');
	/*set the occured time and date to work start date and time*/
	if(mode != 'modify' && mode != 'view' ){
		
		var occDate = getFieldValue('dteGmntOccureddate'); 
	    var occTime = getFieldValue('spnoccuredTime');
	  //  var shift=getFieldValue('cmbGmntShift');
	//	var wsDate  = setFieldValue("dteGmntWostartdate",occDate) ;
		//var wsTime  = displayText("spnWorkstartTime",occTime);
		readOnlyFields('cmbGmntReportedby');
		
		//disableField("#frmGenralMaintenance","cmbGmntMachineid");
		
	}
	else{
		//alert("IN"+mode)
		readOnlyFields('cmbGmntMachineid');
	//	readOnlyFields('frmGenralMaintenancegmntfunLocationValues');
		
		//readOnlyFields('dispFunctionalLoc');
		}

	
	jQuery("#cmbGmntActivitytype").combobox("setValue",activity);
	
/*---End ---*/
	
	/* var relatedToValue=getFieldValue('cmbGmntRelatedto');
	
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
		
*/
		
		
		
		var spares=jQuery('#hdnSpares').val();
		
		
		/* if(spares=="Y"){
		jQuery('#chkIssparesY').attr('checked',true);
		 checkY="Y";
			}
		else if(spares=="N"){
			jQuery('#chkIssparesN').attr('checked',true);
			}
		else if(spares=="W"){
			jQuery('#chkIssparesW').attr('checked',true);
		} */
		if(spares == "Y"){
		    jQuery('#chkIssparesY').prop('checked', true);
		    jQuery('#chkIssparesN').prop('checked', false);
		    jQuery('#chkIssparesW').prop('checked', false);
		    jQuery('#lblspareid').addClass('mandatory-lbl');
		    checkY = "Y";
		}
		else if(spares == "N"){
		    jQuery('#chkIssparesY').prop('checked', false);
		    jQuery('#chkIssparesN').prop('checked', true);
		    jQuery('#chkIssparesW').prop('checked', false);
		    checkY = "N";
		}
		else if(spares == "W"){
		    jQuery('#chkIssparesY').prop('checked', false);
		    jQuery('#chkIssparesN').prop('checked', false);
		    jQuery('#chkIssparesW').prop('checked', true);
		    checkY = "W";
		}
		jQuery('#chkIssparesY, #chkIssparesN, #chkIssparesW')
	    .off('click')                       // remove any previously bound handlers (duplicates/leftovers)
	    .on('click', function(){
	        var clickedId = jQuery(this).attr('id');

	        // force exclusivity: only the clicked one stays checked
	        jQuery('#chkIssparesY').prop('checked', clickedId === 'chkIssparesY');
	        jQuery('#chkIssparesN').prop('checked', clickedId === 'chkIssparesN');
	        jQuery('#chkIssparesW').prop('checked', clickedId === 'chkIssparesW');

	        if(clickedId === 'chkIssparesY'){
	            jQuery('#lblspareid').addClass('mandatory-lbl');
	            checkY = 'Y';
	        }
	        else if(clickedId === 'chkIssparesN'){
	            jQuery('#lblspareid').removeClass('mandatory-lbl');
	            jQuery('#cmbGmntSpareid').combobox && jQuery('#cmbGmntSpareid').combobox('clear'); // adjust to GM's actual spare combo id if different
	            checkY = 'N';
	        }
	        else if(clickedId === 'chkIssparesW'){
	            jQuery('#lblspareid').removeClass('mandatory-lbl');
	            checkY = 'W';
	        }
	    });
	var statuscmb = jQuery('#cmbGmntStatus').val();
	if(statuscmb == 'C'){
			jQuery( "#completed" ).css('display','block');
			
	//jQuery( "#completed" ).hide("slide", { direction: "right" }, 1000);
	}
	else 
		
		jQuery( "#completed" ).css('display','none');
	readOnlyFields('cmbGmntReportedby');
		//readOnlyFields('dteGmntOccureddate');
		///readOnlyFields('spnoccuredTime');
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

		readOnlyFields('cmbGmntShift',"");
		/* for functionalLocation*/
		/*var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
		if(relatedTO == 'MLD')
			lodFuncLoc(relatedTO);
		else	
			lodFuncLoc('MCH');
		/*---------*/	
		lodFuncLoc('MCH');
		// ***************************Fresh create mode la NaN varadha thadukka
		displayText('txtGmntWorkhours', 0);
		displayText('txtGmntDowntime', 0);
		displayText('txtGmntResponsetime', 0);
		
		var stausVal = getFieldValue('cmbGmntStatus','frmGenralMaintenance');
		if(stausVal != '' && stausVal != ' ' && stausVal != null)
			frmGenralMaintenancecmbGmntStatus_onSelect(stausVal);

		jQuery('#spansubLoss').hide();
		var menumode = jQuery('#hdnsetupandadj').val();
		if(menumode != '' && menumode != ' ' && menumode != null ){		

			setTimeout(function() {
				jQuery('#cmbGmntActivitytype').combobox('setValue','S');},1250);
			setTimeout(function() {jQuery('#cmbGmntActivitytype').combobox('setText','Setup And Adjustment');},1250);
			setTimeout(function() {readOnlyFields('cmbGmntActivitytype',"");},1250);
			//jQuery('#cmbGmntActivitytype').css('width','150px');
			jQuery('#spansubLoss').show();
			if(statuscmb != 'C')
				disableUIButton('btnsubLoss');
			//jQuery('#spansubLoss').css('display','block');
		}
		fillComboBox("frmGenralMaintenance","cmbGmntShift","combo_GmntShift.balgenmain");
		fillComboBox("frmGenralMaintenance","cmbGmntOrederType","sapOrderType.sapinfo?docType=GEN");
		//var fctid = getFieldValue('cmbGmntFactoryid');
		//var time  = jQuery("#spnoccuredTime").spinner('getValue');
		//txt_shift(fctid,"","",time);
		setTimeout(function(){
        var fctid = getFieldValue('cmbGmntFactoryid');
        var time  = jQuery("#spnoccuredTime").spinner('getValue');
        txt_shift(fctid,"","",time);
        }, 1000);

		jQuery("#chkGenOtherAssm").click(function(){
			if(jQuery("#chkGenOtherAssm").is("checked")){
				reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter" );			
			}
			else{
				var machId = jQuery("#frmGenralMaintenance input[id='machine']").val();
				reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?machineId="+ machId );
			}
		});
		//mano start
		
		
var ldModeCheck = jQuery('#ldmode').val();
if(ldModeCheck == 'view' || ldModeCheck == 'VIEW'){
    disableForm('frmGenralMaintenance');
}



	});
		jQuery('#btnsubLoss').click(function(){
		LoadPopUp("divSubLoss","subloss_input.balgenmain", true,"740px","400px","-55px","7%", "SubLossSNA_Callback","Sub Loss");
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
		if(vurl  == "generalMaint_modify.balgenmain"){
			relTo = "?relTo=MCH";
			reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?mchId="+machId);
			reloadCombo("frmGenralMaintenance","cmbGmntOrederType","sapOrderType.sapinfo?docType=GEN");
		}
		else
			relTo = "?relTo="+datStr;

		
		if(datStr.substring(0,5) == "onsel"){
			dataStr = removeValueFromUrl(dataStr,"machId"); 
		 	dataStr += "&machId="+datStr.substring(5,datStr.length);
		}	
		 //alert(url.substring(url.indexOf('&vurl')+6,url.indexOf('&closeOnSave')));
		/*	var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
		if("MLD" == relatedTO){
			//alert('sds');
				fillComboBox("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?relatedto=MLD");
		}
		else*/
			
		// alert(datStr.substring(5,datStr.length));
	}
	
	
	
		//alert(relTo+"----------"+datStr+"----------"+dataStr);	
		//var flid = jQuery("#frmGenralMaintenance input[id='flid']").val();
		//dataStr += "&flid="+flid;
		loadFunctionalLocation("gmntfunLocation","functionalLoc.balgenmain"+relTo,"gmntfunLocationValues","frmGenralMaintenance",dataStr);
	
	}
		//var status =jQuery('#cmbGmntStatus').combobox('getValue');
		var status = jQuery('#cmbGmntStatus').val();
			if(status=="P"||status==null){		
				jQuery('#chkbdSubmittoSap').prop('disabled',true);
				
			}
		else
			jQuery('#chkbdSubmittoSap').prop('disabled',true);
		
		function sapInfoComplete(){}
		function sapInfoErr(){}
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

	
	/*function occuredTimeEvt()
	{   
	
		/*to get shift based on Time*

		var fctid =  jQuery("#frmGenralMaintenance input[id='factory']").val();
		
		var time  = jQuery("#spnoccuredTime").spinner('getValue');
		txt_shift(fctid,"","",time);
		//alert("MachineActivity 51");
		//dteGmntOccureddate_onSelect(new Date());
		
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
    }*/
/*     function occuredTimeEvt()
    {   
        var fctid =  jQuery("#frmGenralMaintenance input[id='factory']").val();
        //var time  = jQuery("#spnOccuredTime").spinner('getValue');
        var time  = jQuery("#spnoccuredTime").spinner('getValue');
        txt_shift(fctid,"","",time);

        var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');
        var weDateVal = jQuery("#dteGmntWoenddate").datebox('getValue');

        //if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnOccuredTime").spinner('getValue') != null
            //&& wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
        	if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null
                && wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
        {					
            //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnOccuredTime").spinner('getValue');				
            //var wsDate = wsDateVal + jQuery("#spnWorkstartTime").spinner('getValue');
            //var weDate = weDateVal + jQuery("#spnWorkendTime").spinner('getValue');
            var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
            var wsDate = wsDateVal + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
            var weDate = weDateVal + ' ' + jQuery("#spnWorkendTime").spinner('getValue');
            
             if(weDate != null && weDate != ""){
                actualMins = timeDifference(wsDate,weDate);	
                //displayText('txtGmntWorkhours', Math.max(0, Math.round(actualMins.minutes)));										
                //downTime = timeDifference(occDate,weDate);
                //displayText('txtGmntDowntime', Math.max(0, Math.round(downTime.minutes)));
                displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
                downTime = timeDifference(occDate,weDate);
                displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
             }
        }
        else{
            displayText('txtGmntWorkhours', 0);
            displayText('txtGmntDowntime', 0);
        }
    } */
    
    function occuredTimeEvt()
    {   
        if(!validateOccurredDateTime())
            return;

        var fctid =  jQuery("#frmGenralMaintenance input[id='factory']").val();
        //var time  = jQuery("#spnOccuredTime").spinner('getValue');
        var time  = jQuery("#spnoccuredTime").spinner('getValue');
        txt_shift(fctid,"","",time);

        var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');
        var weDateVal = jQuery("#dteGmntWoenddate").datebox('getValue');

        //if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnOccuredTime").spinner('getValue') != null
            //&& wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
            if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null
                && wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
        {					
            //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnOccuredTime").spinner('getValue');				
            //var wsDate = wsDateVal + jQuery("#spnWorkstartTime").spinner('getValue');
            //var weDate = weDateVal + jQuery("#spnWorkendTime").spinner('getValue');
            var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
            var wsDate = wsDateVal + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
            var weDate = weDateVal + ' ' + jQuery("#spnWorkendTime").spinner('getValue');
            
            if(weDate != null && weDate != ""){
                actualMins = timeDifference(wsDate,weDate);	
                displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										

                downTime = timeDifference(occDate,weDate);
                displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));

                // FIX: Response Time (Occurred -> Work Start) 
                respTime = timeDifference(occDate,wsDate);
                displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
             }
        }
        else{
            displayText('txtGmntWorkhours', 0);
            displayText('txtGmntDowntime', 0);
        }
    }
	
	/*function woStartEvt()
	{
		dteGmntWostartdate_onSelect(new Date());
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{				
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			displayText('txtGmntResponsetime',respTime.minutes);
		}
		//to get shift based on Time*
		var fctid = getFieldValue('cmbGmntFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
		//txt_shift(fctid,"","",time);
	}*/
	function woStartEvt()
	{
	    if(!validateNotFutureDateTime('dteGmntWostartdate','spnWorkstartTime'))
	        return;
	    dteGmntWostartdate_onSelect(); 
	    //dteGmntWostartdate_onSelect(new Date());
	    var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');

	    if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null
	        && wsDateVal != null && wsDateVal != '')
	    {
	        var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
	        var wsDate = wsDateVal + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
	        respTime = timeDifference(occDate,wsDate);
	        displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
	    }
	    else{
	        displayText('txtGmntResponsetime', 0);
	    }

	    var fctid = getFieldValue('cmbGmntFactoryid');
	    var time  = jQuery("#spnWorkstartTime").spinner('getValue');
	}
	/*function woStartEvt()
	{
	    dteGmntWostartdate_onSelect(new Date());
	    var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');

	   // if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnOccuredTime").spinner('getValue') != null
	        //&& wsDateVal != null && wsDateVal != '')
		   if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null
               && wsDateVal != null && wsDateVal != '')
	    {				
	        //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnOccuredTime").spinner('getValue');				
	        //var wsDate = wsDateVal + jQuery("#spnWorkstartTime").spinner('getValue');
	        //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
	        // var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
	        var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
             var wsDate = wsDateVal + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
	        respTime =timeDifference(occDate,wsDate);
	        //displayText('txtGmntResponsetime', Math.max(0, Math.round(respTime.minutes)));
	        displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
	    }
	    else{
	        displayText('txtGmntResponsetime', 0);
	    }

	    var fctid = getFieldValue('cmbGmntFactoryid');
	    var time  = jQuery("#spnWorkstartTime").spinner('getValue');
	}*/
	/*function woEndEvt()
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
 }*/
 function woEndEvt()
 {
     if(!validateNotFutureDateTime('dteGmntWoenddate','spnWorkendTime'))
         return;
     dteGmntWoenddate_onSelect();
    // dteGmntWoenddate_onSelect(new Date());

     var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');
     var weDateVal = jQuery("#dteGmntWoenddate").datebox('getValue');

     if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null
         && wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
     {
         var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');
         var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
         var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');

         actualMins = timeDifference(wsDate,weDate);
         displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));
         downTime = timeDifference(occDate,weDate);
         displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
         respTime = timeDifference(occDate,wsDate);
         displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
     }
     else{
         displayText('txtGmntWorkhours', 0);
         displayText('txtGmntDowntime', 0);
     }
     enableUIButton('btnsubLoss');
 }
 /*function woEndEvt()
 {    
     dteGmntWoenddate_onSelect(new Date());

     var wsDateVal = jQuery("#dteGmntWostartdate").datebox('getValue');
     var weDateVal = jQuery("#dteGmntWoenddate").datebox('getValue');

     //if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnOccuredTime").spinner('getValue') != null
        // && wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
    	 if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null
                && wsDateVal != null && wsDateVal != '' && weDateVal != null && weDateVal != '')
     {					
         //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnOccuredTime").spinner('getValue');				
         //var wsDate = wsDateVal + jQuery("#spnWorkstartTime").spinner('getValue');
         //var weDate = weDateVal + jQuery("#spnWorkendTime").spinner('getValue');
         //var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
         //var wsDate = wsDateVal + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
        // var weDate = weDateVal + ' ' + jQuery("#spnWorkendTime").spinner('getValue');
           var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');
           var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
           var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
          
         actualMins = timeDifference(wsDate,weDate);	
         //displayText('txtGmntWorkhours', Math.max(0, Math.round(actualMins.minutes)));										
         //downTime = timeDifference(occDate,weDate);
         //displayText('txtGmntDowntime', Math.max(0, Math.round(downTime.minutes)));
         displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
         downTime = timeDifference(occDate,weDate);
         displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
     }
     else{
         displayText('txtGmntWorkhours', 0);
         displayText('txtGmntDowntime', 0);
     }
     enableUIButton('btnsubLoss');
 }*/
 
 function dteGmntWoenddate_onSelect(date)
 {
     if(!validateNotFutureDateTime('dteGmntWoenddate','spnWorkendTime', date))
         return;

     checkFutureDate('dteGmntWoenddate', date);
     compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');

     if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
     {
         var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
         var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
         var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + ' ' + jQuery("#spnWorkendTime").spinner('getValue');

         actualMins = timeDifference(wsDate,weDate);
         displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));

         respTime = timeDifference(occDate,wsDate);
         displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));

         downTime = timeDifference(occDate,weDate);
         displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
     }
     enableUIButton('btnsubLoss');
 }
	/* function dteGmntOccureddate_onSelect(date)
  	{
		checkFutureDate('dteGmntOccureddate');
		
		var occDate = getFieldValue('dteGmntOccureddate');
		 
		setFieldValue("dteGmntShiftdate",occDate);
		
		compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime',' Occured Date should  be Greater than Work start Date ');
		
		//compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntOccureddate','spnoccuredTime',' Occured Date should  be Greater than Work start Date ');
    }
 */
 //mano
 function dteGmntOccureddate_onSelect(date)
{
    if(!validateOccurredDateTime(date))
        return;

    checkFutureDate('dteGmntOccureddate', date);

    var occDate = getFieldValue('dteGmntOccureddate');
     
    setFieldValue("dteGmntShiftdate",occDate);
    
    compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime',' Occured Date should  be Greater than Work start Date ');
}
	/*function dteGmntWostartdate_onSelect(date)
  	{
		checkFutureDate('dteGmntWostartdate');
		//fillWithCurrentDate('spnWorkstartTime');
		//compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntOccureddate','spnoccuredTime','Start Date should not be lesser than Allotted Date/time');
		compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime','Work start should be equal Greater than  occurred date');
		
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{				
			//var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			//var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
            var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
			respTime =timeDifference(occDate,wsDate);
			//displayText('txtGmntResponsetime',respTime.minutes);
			displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
		}
		//to get shift based on Time/
		var fctid = getFieldValue('cmbGmntFactoryid');
		var time  = jQuery("#spnWorkstartTime").spinner('getValue');
	//	txt_shift(fctid,"","",time);
	}*/
	function dteGmntWostartdate_onSelect(date)
	{
	    if(!validateNotFutureDateTime('dteGmntWostartdate','spnWorkstartTime', date))
	        return;

	    checkFutureDate('dteGmntWostartdate');
	    compareDates('dteGmntOccureddate','spnoccuredTime','dteGmntWostartdate','spnWorkstartTime','Work start should be equal Greater than  occurred date');

	    if(jQuery("#spnWorkstartTime").spinner('getValue') != null  && jQuery("#spnoccuredTime").spinner('getValue') != null)
	    {
	        var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');
	        var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
	        respTime = timeDifference(occDate,wsDate);
	        displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));
	    }

	    var fctid = getFieldValue('cmbGmntFactoryid');
	    var time  = jQuery("#spnWorkstartTime").spinner('getValue');
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
	//	txt_shift(fctid,"","",time);
	}
	//mano start
	
	function dteGmntWoenddate_onChange(date)
  	{
		compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			//var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			//var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			//var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
            var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
            var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + ' ' + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			/*displayText('txtGmntWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime',downTime.minutes);*/
			displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
			respTime = timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
			
		}
    }	
	/* function dteGmntWoenddate_onSelect(date)
  	{    
		checkFutureDate('dteGmntWoenddate');
		compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
		if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
		{					
			var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
			var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
			var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
		     
			actualMins = timeDifference(wsDate,weDate);	
			/*displayText('txtGmntWorkhours',actualMins.minutes);										
			respTime =timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime',downTime.minutes);*/
			/* displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
			respTime = timeDifference(occDate,wsDate);	
			downTime = timeDifference(occDate,weDate);
			displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
			
		}
		enableUIButton('btnsubLoss');
    } */ 
    
    //mano
    /*function dteGmntWoenddate_onSelect(date)
{    
    checkFutureDate('dteGmntWoenddate', date);
    compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
    if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
    {					
        var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + jQuery("#spnoccuredTime").spinner('getValue');				
        var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + jQuery("#spnWorkstartTime").spinner('getValue');
        var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + jQuery("#spnWorkendTime").spinner('getValue');
         
        actualMins = timeDifference(wsDate,weDate);	
        displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
        respTime = timeDifference(occDate,wsDate);	
        downTime = timeDifference(occDate,weDate);
        displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
        
    }
    enableUIButton('btnsubLoss');
}*/
function dteGmntWoenddate_onSelect(date)
{    
    checkFutureDate('dteGmntWoenddate', date);
    compareDates('dteGmntWostartdate','spnWorkstartTime','dteGmntWoenddate','spnWorkendTime','Work end should be equal to or greater than workstart');
    if(jQuery("#spnWorkstartTime").spinner('getValue') != null && jQuery("#spnWorkendTime").spinner('getValue')!= null && jQuery("#spnoccuredTime").spinner('getValue') != null)
    {					
        var occDate = jQuery("#dteGmntOccureddate").datebox('getValue') + ' ' + jQuery("#spnoccuredTime").spinner('getValue');				
        var wsDate = jQuery("#dteGmntWostartdate").datebox('getValue') + ' ' + jQuery("#spnWorkstartTime").spinner('getValue');
        var weDate = jQuery("#dteGmntWoenddate").datebox('getValue') + ' ' + jQuery("#spnWorkendTime").spinner('getValue');
        
         
        actualMins = timeDifference(wsDate,weDate);	
        displayText('txtGmntWorkhours', (isNaN(actualMins.minutes) ? 0 : Math.max(0, Math.round(actualMins.minutes))));										
        
        respTime = timeDifference(occDate,wsDate);	
        displayText('txtGmntResponsetime', (isNaN(respTime.minutes) ? 0 : Math.max(0, Math.round(respTime.minutes))));

        downTime = timeDifference(occDate,weDate);
        displayText('txtGmntDowntime', (isNaN(downTime.minutes) ? 0 : Math.max(0, Math.round(downTime.minutes))));
    }
    enableUIButton('btnsubLoss');
}

	/* function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
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
		/* if( compareDateTime(toDate,fromDate) > 0 )
		    { //alert(toDate+"------------"+fromDate);
			    //alert(compareDateTime(toDate,fromDate));
			    alert(errMsg);
	        	showValidationErrorMsg(toDateId,errMsg);
	        	//fillWithCurrentDate(toDateId);	
				//fillWithCurrentDate(toTimeId);
		    }
	        else
	        	clearValidationErrorMsg(toDateId);	
		}*/
		/* function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
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
		 if( compareDateTime(toDate,fromDate) > 0 )
		    {
			    alert(errMsg);
	        	showValidationErrorMsg(toDateId,errMsg);

	        	setTimeout(function(){
	        	    jQuery('#'+toDateId).datebox('hidePanel');
	        	    jQuery('#'+toDateId).datebox('setValue', jQuery('#'+fromDateId).datebox('getValue'));
	        	    jQuery('#'+toTimeId).spinner('setValue', jQuery('#'+fromTimeId).spinner('getValue'));
	        	    clearValidationErrorMsg(toDateId);
	        	}, 300);
		    }
	        else
	        	clearValidationErrorMsg(toDateId);	
		} */
		function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
		{
		   clearValidationErrorMsg(fromDateId);
		   clearValidationErrorMsg(toDateId);
		    var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
		    var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
		    var fDt = jQuery('#'+fromDateId).datebox('getValue');
		    var fTi = jQuery('#'+fromTimeId).spinner('getValue');
		    var tDt = jQuery('#'+toDateId).datebox('getValue') ;
		    var tTi = jQuery('#'+toTimeId).spinner('getValue') ;
		    if( fDt == ""  || fTi == "" || tDt == ""  || tTi == "")
			    return ;

			var currentDate = getServerDateTime();
			if(convertStringToDate(toDate) > currentDate)
				{
					showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
		        	fillWithCurrentDate(toDateId);			
					fillWithCurrentDate(toTimeId);
					return ;
				}
			else if( compareDateTime(toDate,fromDate) > 0 )
		    {
		        // mano: pop the same alert box as the General Maintenance form,
		        // but only for Work Start / Work End - other date pairs stay
		        // inline-only as before
		        if(toDateId == 'dtebdmsWostarttime' || toDateId == 'dtebdmsWoendtime'){
		            alert(errMsg);
		        }
		    	showValidationErrorMsg(toDateId,errMsg);
		    	displayText(toDateId,fDt);
		    	displayText(toTimeId,fTi);
				return ;
		    }
		    else if(toDateId == 'dtebdmsWostarttime')
		    {
			    var woEndDate = jQuery('#dtebdmsWoendtime').datebox('getValue'); 
			    var woEndTime = jQuery('#spnbdmsWoend').spinner('getValue');
			    if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
			    {
				    if(woEndDate != null && woEndDate != ' ' && woEndDate != '')
					{
			    			woEndDate = woEndDate + woEndTime;
			    			if( compareDateTime(woEndDate,toDate) > 0 )
					    {
				        	showValidationErrorMsg('dtebdmsWostarttime','Work Start Date Should not be greater than Work end Date');
						}
				        else
				        	clearValidationErrorMsg('dtebdmsWostarttime');
					}
			    }
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
	//fillComboBox("frmGenralMaintenance","cmbGmntRelatedto","relatedto.commonFilter");
	
}
function frmGenralMaintenancecmbGmntStationid_onLoadSuccess()
{
	//fillComboBox("frmGenralMaintenance","cmbGmntRelatedto","relatedto.commonFilter");
	
}
function frmGenralMaintenancecmbGmntRelatedto_onLoadSuccess()
{
	//fillComboBox("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter");
	fillComboBox("frmGenralMaintenance","cmbGmntShift","combo_GmntShift.balgenmain");
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
	fillComboBox("frmGenralMaintenance","cmbGmntTrade","combo_GmntTrade.balgenmain");
	//fillComboBox("frmGenralMaintenance","cmbGmntActivitytype","combo_activitytype.genmain");
}
function frmGenralMaintenancecmbGmntActivitytype_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntTrade","combo_GmntTrade.balgenmain");
}
function frmGenralMaintenancecmbGmntTrade_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntMchcondition","combo_machinecondition.balgenmain");
}
function frmGenralMaintenancecmbGmntMchcondition_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntReportedby","combo_GmntReportedby.balgenmain");
}
function frmGenralMaintenancecmbGmntReportedby_onLoadSuccess()
{
	fillComboBox("frmGenralMaintenance","cmbGmntStatus","combo_GmntStatus.balgenmain");
}
/* function frmGenralMaintenancecmbGmntStatus_onLoadSuccess()
{
	var secid = getFieldValue('cmbGmntSectionid');
	//fillComboBox("frmGenralMaintenance","cmbGmntCompletedby","combo_Gmntcompletedby.genmain");
	fillComboBox("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?&sectId="+secid );
} */

function frmGenralMaintenancecmbGmntStatus_onLoadSuccess()
{
    var secid = getFieldValue('cmbGmntSectionid');
    fillComboBox("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?&sectId="+secid );

   
    var savedStatus = jQuery('#hdngmntStatus').val();
    if(savedStatus != null && savedStatus != '' && savedStatus != ' '){
        jQuery('#cmbGmntStatus').combobox('setValue', savedStatus);
        frmGenralMaintenancecmbGmntStatus_onSelect(savedStatus);
    }
}
function frmGenralMaintenancecmbGmntCompletedby_onLoadSuccess()
{
    	
	/*var url = jQuery('#hiddenUrl').val();//alert(url);
	var viewUrl = url.substring(64,89);
	if(viewUrl == 'generalMaint_view.genmain'){
		disableForm('frmGenralMaintenance');
	}*/
}
/*function  frmGenralMaintenancecmbGmntMachineid_onClear(record)
{
	jQuery('#linfrmGenralMaintenanceMachine').html(' ');
	jQuery("#frmGenralMaintenance input[id='machine']").val('');
	/*var relatedTO = getFieldValue("cmbGmntRelatedto","frmGenralMaintenance");
	if(relatedTO == 'MLD')
		lodFuncLoc(relatedTO); */
//}
function  frmGenralMaintenancecmbGmntMachineid_onSelect(record)
{ 
	/*var relatedToValue=getFieldValue('cmbGmntRelatedto');
	if(relatedToValue == "MCH"){ */
		reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?machineId="+record.id);
	//	reloadCombo("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter?machineId="+record.id);
	//}
	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbGmntLineid","cmbGmntSectionid","cmbGmntFactoryid","cmbComp");
	lodFuncLoc("onsel"+record.id);
	//loadFunctionalLocation("gmntfunLocation","functionalLoc.genmain","gmntfunLocationValues","frmGenralMaintenance","&machId="+record.id);
	var fctid = getFieldValue('cmbGmntFactoryid');
	var linid = getFieldValue('cmbGmntLineid');
	var secid = getFieldValue('cmbGmntSectionid');
	/*readOnlyFields('cmbGmntFactoryid');
	readOnlyFields('cmbGmntLineid');
	readOnlyFields('cmbGmntSectionid');*/
//	readOnlyFields('cmbGmntMachineid');
	//fill_shift(fctid,secid,linid,"");
}
function  frmGenralMaintenancecmbGmntLineid_onSelect(record)
{
	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbGmntSectionid","cmbGmntFactoryid");
	var lineid = record.id;
	txt_shift("","",lineid);
		
}
function  frmGenralMaintenancecmbGmntSectionid_onSelect(record)
{
	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbGmntFactoryid");
	var sectid = record.id;
	
	txt_shift("",sectid);	
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
    reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?mchId="+machId);
	reloadCombo("frmGenralMaintenance","cmbGmntMouldid","mould.commonFilter?machineId="+machId);
	}
	else{ 
		jQuery("#fact_label").addClass('mandatory-lbl');
	    jQuery("#Mouldid_label").addClass('mandatory-lbl');
	    jQuery("#eqp_label").removeClass('mandatory-lbl');
	    enableFields('cmbGmntMouldid');
	    reloadCombo("frmGenralMaintenance","cmbGmntStationid","assembly.commonFilter?relatedto=MLD");
	    
	}
}
/*function  frmGenralMaintenancecmbGmntStatus_onSelect(record)
{
	if(record.id == "C" || record == "C"){
		jQuery( "#completed" ).css('display','block');
		jQuery( "#actionTaken").addClass('mandatory-lbl');
		jQuery("#chkbdSubmittoSap").prop('disabled',false);
		//jQuery( "#completed" ).animate({marginLeft:'500px'},'slow'); 
	}
	else if(record.id == "P" || record == "P"){
		jQuery( "#actionTaken").removeClass('mandatory-lbl');
		jQuery( "#completed" ).css('display','none');
		//jQuery("#chkbdSubmittoSap").prop('disabled',true);
		}
	
	}
	*/
	/*function  frmGenralMaintenancecmbGmntStatus_onSelect(record)
	{
		if(record.id == "C" || record == "C"){
			jQuery( "#completed" ).css('display','block');
			jQuery( "#actionTaken").addClass('mandatory-lbl');
			jQuery("#chkbdSubmittoSap").prop('disabled',false);
			fillWithCurrentDate('spnWorkstartTime');
			fillWithCurrentDate('spnWorkendTime');
		}
		else if(record.id == "P" || record == "P"){
			jQuery( "#actionTaken").removeClass('mandatory-lbl');
			jQuery( "#completed" ).css('display','none');

			// ---- NEW: Pending aana 3 fields um 0 ku reset pannidum ----
			displayText('txtGmntWorkhours', 0);
			displayText('txtGmntDowntime', 0);
			displayText('txtGmntResponsetime', 0);
			}
		
		}*/
		/* function frmGenralMaintenancecmbGmntStatus_onSelect(record)
		{
		    if(record.id == "C" || record == "C"){
		        jQuery( "#completed" ).css('display','block');
		        jQuery( "#actionTaken").addClass('mandatory-lbl');
		        jQuery("#chkbdSubmittoSap").prop('disabled',false);

		        // Modify/View mode la already saved Work Start/End irundha,
		        // adha current time vachi overwrite pannadha - value irundha mattum skip
		        var wsVal     = jQuery('#dteGmntWostartdate').datebox('getValue');
		        var weVal     = jQuery('#dteGmntWoenddate').datebox('getValue');
		        var wsTimeVal = jQuery('#spnWorkstartTime').spinner('getValue');
		        var weTimeVal = jQuery('#spnWorkendTime').spinner('getValue');

		        if(wsVal == null || wsVal == '' || wsTimeVal == null || wsTimeVal == ''){
		            fillWithCurrentDate('spnWorkstartTime');
		            // NEW: also fill the date box, only if it's actually blank
		            if(wsVal == null || wsVal == ''){
		                fillWithCurrentDate('dteGmntWostartdate');
		            }
		        }
		        if(weVal == null || weVal == '' || weTimeVal == null || weTimeVal == ''){
		            fillWithCurrentDate('spnWorkendTime');
		            // NEW: also fill the date box, only if it's actually blank
		            if(weVal == null || weVal == ''){
		                fillWithCurrentDate('dteGmntWoenddate');
		            }
		        }
		    }
		    else if(record.id == "P" || record == "P"){
		        jQuery( "#actionTaken").removeClass('mandatory-lbl');
		        jQuery( "#completed" ).css('display','none');

		        displayText('txtGmntWorkhours', 0);
		        displayText('txtGmntDowntime', 0);
		        displayText('txtGmntResponsetime', 0);
		    }
		} */
		//mano
		function frmGenralMaintenancecmbGmntStatus_onSelect(record)
{
			
			console.log(record);
    if(record.id == "C" || record == "C"){
        jQuery( "#completed" ).css('display','block');
        jQuery( "#actionTaken").addClass('mandatory-lbl');
        jQuery("#chkbdSubmittoSap").prop('disabled',false);

        var wsDateVal = jQuery('#dteGmntWostartdate').datebox('getValue');
        var wsTimeVal = jQuery('#spnWorkstartTime').spinner('getValue');
        var weDateVal = jQuery('#dteGmntWoenddate').datebox('getValue');
        var weTimeVal = jQuery('#spnWorkendTime').spinner('getValue');

        var wsComplete = (wsDateVal != null && wsDateVal != '' && wsTimeVal != null && wsTimeVal != '');
        var weComplete = (weDateVal != null && weDateVal != '' && weTimeVal != null && weTimeVal != '');

        // Refill date+time TOGETHER, only when the pair isn't already fully
        // saved. Refilling just the missing half (e.g. only the date) while
        // leaving a stale saved time behind produces a mismatched date/time
        // pair -- which is what made Work End show as earlier than Work Start
        // and zeroed out Actual Time / Down Time above.
        if(!wsComplete){
            fillWithCurrentDate('dteGmntWostartdate');
            fillWithCurrentDate('spnWorkstartTime');
        }
        if(!weComplete){
            fillWithCurrentDate('dteGmntWoenddate');
            fillWithCurrentDate('spnWorkendTime');
        }

        // Give the datebox/timespinner widgets time to actually commit the
        // new values (same reasoning as the 500ms delay used elsewhere in
        // this file for the Occurred Date/Time autofill on page load) before
        // reading them back to recalc.
        setTimeout(function(){
            woStartEvt();
            woEndEvt();
        }, 500);
    }
    else if(record.id == "P" || record == "P"){
        jQuery( "#actionTaken").removeClass('mandatory-lbl');
        jQuery( "#completed" ).css('display','none');

        displayText('txtGmntWorkhours', 0);
        displayText('txtGmntDowntime', 0);
        displayText('txtGmntResponsetime', 0);
    }
}
		
		/*
		if(record.id == "C" || record == "C"){
		    jQuery( "#completed" ).css('display','block');
		    jQuery( "#actionTaken").addClass('mandatory-lbl');
		    jQuery("#chkbdSubmittoSap").prop('disabled',false);
		    fillWithCurrentDate('spnWorkstartTime');
		    fillWithCurrentDate('spnWorkendTime');
		    // Work Start Date blank ah irundha current date fill pannu
		    if(jQuery('#dteGmntWostartdate').datebox('getValue') == '' || jQuery('#dteGmntWostartdate').datebox('getValue') == null){
		        fillWithCurrentDate('dteGmntWostartdate');
		    }
		    // Work End Date blank ah irundha current date fill pannu
		    if(jQuery('#dteGmntWoenddate').datebox('getValue') == '' || jQuery('#dteGmntWoenddate').datebox('getValue') == null){
		        fillWithCurrentDate('dteGmntWoenddate');
		    }
		}*/


/* jQuery('#chkIssparesY').click(function() {
		jQuery('input:checkbox[name=chkIssparesY]').attr('checked',true);
		jQuery("#lblspareid").addClass("mandatory-lbl");
		
		jQuery('input:checkbox[name=chkIssparesN]').attr('checked',false);
		jQuery('input:checkbox[name=chkIssparesW]').attr('checked',false);
		checkY="Y";
		
		
		
	});
	
	jQuery('#chkIssparesN').click(function() {
		jQuery("#lblspareid").removeClass("mandatory-lbl");
		jQuery('input:checkbox[name=chkIssparesN]').attr('checked',true);
		jQuery('input:checkbox[name=chkIssparesY]').attr('checked',false);
		jQuery('input:checkbox[name=chkIssparesW]').attr('checked',false);
		jQuery('#cmbbdmsSpareid').combobox('clear');
		checkY="N";
		
	});
	jQuery('#chkIssparesW').click(function() {
		jQuery("#lblspareid").removeClass("mandatory-lbl");
		jQuery('input:checkbox[name=chkIssparesW]').attr('checked',true);
		jQuery('input:checkbox[name=chkIssparesY]').attr('checked',false);
		jQuery('input:checkbox[name=chkIssparesN]').attr('checked',false);
		checkY="W";
	}); */
	jQuery('#chkIssparesY').click(function() {
		jQuery('input:checkbox[name=chkIssparesY]').prop("checked","checked");
		jQuery("#lblspareid").addClass("mandatory-lbl");

		jQuery('input:checkbox[name=chkIssparesN]').prop("checked",false);
		jQuery('input:checkbox[name=chkIssparesW]').prop("checked",false);
		checkY="Y";
	});

	jQuery('#chkIssparesN').click(function() {
		jQuery("#lblspareid").removeClass("mandatory-lbl");
		jQuery('input:checkbox[name=chkIssparesN]').prop("checked","checked");
		jQuery('input:checkbox[name=chkIssparesY]').prop("checked",false);
		jQuery('input:checkbox[name=chkIssparesW]').prop("checked",false);
		jQuery('#cmbbdmsSpareid').combobox('clear');
		checkY="N";
	});

	jQuery('#chkIssparesW').click(function() {
		jQuery("#lblspareid").removeClass("mandatory-lbl");
		jQuery('input:checkbox[name=chkIssparesW]').prop("checked","checked");
		jQuery('input:checkbox[name=chkIssparesY]').prop("checked",false);
		jQuery('input:checkbox[name=chkIssparesN]').prop("checked",false);
		checkY="W";
	});
	
	var genKeyId=jQuery('#cmbGmntKeyid').combobox('getValue');
	//var machine=jQuery('#cmbGmntKeyid').text();
	var machine =jQuery('#cmbGmntMachineid').combobox('getText');
	//alert("......"+machine);
	//var genKeyid=jQuery('#cmbGmntKeyid').combobox('getValue');
	

		
/* function frmGenralMaintenance_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var ldmode=jQuery('#ldmode').val();
	
				
				if(ldmode =='modify' || ldmode == 'view')
				{	
					jQuery('#gmntfunLocation').append('<div id="divhide1" style="position:absolute;top:10px;left:20px;width:100%;z-index:2;opacity:0.4;height:20%;"> </div>');
					readOnlyFields('cmbGmntMachineid');
					
			  	}
				//alert(keyIds.sectId);
				 reloadCombo("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?&sectId="+keyIds.sectId);
				 reloadCombo("frmGenralMaintenance","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId );
	var factryid = keyIds.factId;
	 fill_shift(factryid);
	
	
	
} */
/* function frmGenralMaintenance_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var ldmode=jQuery('#ldmode').val();

	if(ldmode =='modify' || ldmode == 'view')
	{
		jQuery('#gmntfunLocation').append('<div id="divhide1" style="position:absolute;top:10px;left:20px;width:100%;z-index:2;opacity:0.4;height:20%;"> </div>');
		readOnlyFields('cmbGmntMachineid');
	}

	// The functional-location widget always renders the resolved SBU value
	// into a field literally named "hdnsbu" Ã¢ÂÂ confirmed via Network tab payload.
	var sbuVal = jQuery("#frmGenralMaintenance input[name='hdnsbu']").val();

	// Fallback by id, in case it's rendered as id instead of name in some cases
	if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
		sbuVal = jQuery("#hdnsbu").val();

	console.log("sbuVal from hdnsbu:", sbuVal);

	if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
		jQuery("#frmGenralMaintenance input[id='factory']").val(sbuVal);
	}

	reloadCombo("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?&sectId="+keyIds.sectId);
	reloadCombo("frmGenralMaintenance","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId );

	var factryid = (sbuVal != null && sbuVal != '') ? sbuVal : keyIds.factId;
	fill_shift(factryid);
} */
function frmGenralMaintenance_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var ldmode=jQuery('#ldmode').val();

	if(ldmode =='modify' || ldmode == 'view')
	{
		//mano
		//jQuery('#gmntfunLocation').append('<div id="divhide1" style="position:absolute;top:10px;left:20px;width:100%;z-index:2;opacity:0.4;height:20%;"> </div>');
		readOnlyFields('cmbGmntMachineid');
	}
	else
	{
		// FIX: filter machine combo by cell AND functional location (flid),
		// same as WhyWhy Analysis does — previously only cellId was ever used,
		// so machines outside the selected JH but still in the cell were shown.
		if (getFieldValue("cmbGmntMachineid").length==0) {
			reloadCombo("frmGenralMaintenance","cmbGmntMachineid","machineCombo.commonFilter?cellId="+keyIds.cellId+"&flid="+keyIds.flId);
		}
	}

	// The functional-location widget always renders the resolved SBU value
	// into a field literally named "hdnsbu" — confirmed via Network tab payload.
	var sbuVal = jQuery("#frmGenralMaintenance input[name='hdnsbu']").val();

	// Fallback by id, in case it's rendered as id instead of name in some cases
	if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
		sbuVal = jQuery("#hdnsbu").val();

	console.log("sbuVal from hdnsbu:", sbuVal);

	if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
		jQuery("#frmGenralMaintenance input[id='factory']").val(sbuVal);
	}

	reloadCombo("frmGenralMaintenance","cmbGmntCompletedby","employee.commonFilter?&sectId="+keyIds.sectId);
	reloadCombo("frmGenralMaintenance","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId );

	var factryid = (sbuVal != null && sbuVal != '') ? sbuVal : keyIds.factId;
	fill_shift(factryid);
}
function txt_shift(factryid,sectid,lineid,time){

	var factryid =factryid;
	var sectid = sectid;
	var lineid =lineid;
	var time  = time;
	
	//alert(fctid+"--"+linid+"--"+secid);
	var fromTime = jQuery('#dteGmntBookeddate').val();	
		//alert("line  "+lineid);				
		var flid = jQuery("#frmGenralMaintenance input[id='flid']").val();

		var dataString = 'factId='+factryid+'&sectId=&flId='+flid;
		    dataString += '&cellId=&fromTime='+time;	
		   // alert(dataString);
		processAjaxCalls('txt_shift.Bbrdn',dataString,'getShift','getShiftErr');
	
}
function  getShift(record)
	{  
		jQuery('#cmbGmntShift').combobox('setValue',record.shift);
		if(record.shift == undefined || record.shift == ""){
			alert("Unallocated Shift time");
		}
	            	
	}
jQuery('#back_genMain').click(function(){
	
	   jQuery("#GeneralMaintMain").load('generalMaint_input.balgenmain','', function(response, status, xhr) {
	   if (status == "error") {
	   var msg = "Sorry but there was an error: ";
	   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
	  }
	});
	  
	   
});
/*fillComboBox("frmGenralMaintenance","cmbGmntOrederType","sapOrderType.sapinfo?docType=GEN");
		var odr=getFieldValue('cmbGmntOrederType');		
		var mode=jQuery('#ldmode').val();	
		
if(mode=="modify" || mode=="MODIFY"|| mode=="view"){	
	
	//alert("machineId"+jQuery('#cmbGmntMachineid').val());
	jQuery('#cmbGmntOrederType').combobox('setValue',odr);
    readOnlyFields('cmbGmntOrederType');

	
	
}
else{
		jQuery('#cmbGmntOrederType').combobox('setValue',jQuery('#hdnorderType').val());
		readOnlyFields('cmbGmntOrederType');
}*/
fillComboBox("frmGenralMaintenance","cmbGmntOrederType","sapOrderType.sapinfo?docType=GEN");

/*function frmGenralMaintenancecmbGmntOrederType_onLoadSuccess()
{
    var mode = jQuery('#ldmode').val();
    var savedValue = jQuery('#hdnorderType').val(); //
    
    
    
    if(mode=="modify" || mode=="MODIFY" || mode=="view"){
        jQuery('#cmbGmntOrederType').combobox('setValue', savedValue); // saved value set
    }
    else{
        jQuery('#cmbGmntOrederType').combobox('setValue', savedValue);
        
    }
    readOnlyFields('cmbGmntOrederType');
    
} */
function frmGenralMaintenancecmbGmntOrederType_onLoadSuccess()
{
    var mode = jQuery('#ldmode').val();
    var savedValue = jQuery('#hdnorderType').val();
  
    
    // hdnorderType empty na (modify/view mode la backend miss pannirundha),
    // actual saved order type field value ah fallback ah use pannunga
    if(savedValue == null || savedValue == '' || savedValue == ' ' || savedValue == undefined){
        savedValue = jQuery("#frmGenralMaintenance input[name='cmbGmntOrederType']").attr('value');
    }
    
    jQuery('#cmbGmntOrederType').combobox('setValue', savedValue);
    readOnlyFields('cmbGmntOrederType');
}
//mano 
function gmntToPgTimestamp(dateStr, timeStr)
{
    if(dateStr == null || dateStr == '' || timeStr == null || timeStr == '')
        return '';

    var months = {"Jan":"01","Feb":"02","Mar":"03","Apr":"04","May":"05","Jun":"06",
                  "Jul":"07","Aug":"08","Sep":"09","Oct":"10","Nov":"11","Dec":"12"};

    var parts = dateStr.split('-');
    if(parts.length != 3) return '';

    var dd = parts[0];
    var mm = months[parts[1]];
    var yyyy = parts[2];

    if(mm == undefined) return '';

    var timeParts = timeStr.split(':');
    var hh = timeParts[0];
    var min = timeParts[1];
    var ss = (timeParts.length > 2) ? timeParts[2] : '00';

    if(hh.length < 2) hh = '0'+hh;
    if(min.length < 2) min = '0'+min;

    return yyyy + '-' + mm + '-' + dd + 'T' + hh + ':' + min + ':' + ss;
}
	

/* function frmGenralMaintenance_successsCallback(result)
{	
	console.log("GMNT save result:", result);

	if(gmntCostInfoMode == "estimate" || gmntCostInfoMode == "actual")
	{
	    var thisMode = gmntCostInfoMode;
	    gmntCostInfoMode = null;

	    var formTitle = (thisMode == "estimate") ? "Cost Information - Estimation" : "Cost Information - Actual";

	    var gmKeyId = (result.successData && result.successData.keyId) ? result.successData.keyId : jQuery('#cmbGmntKeyid').combobox('getValue');

	    var factId    = jQuery("#frmGenralMaintenance input[id='factory']").val();
	    var sectionId = jQuery("#frmGenralMaintenance input[id='section']").val();
	    var cellId    = jQuery("#frmGenralMaintenance input[id='cell']").val();
	    var machId    = jQuery("#frmGenralMaintenance input[id='machine']").val();
	    var flid      = jQuery("#frmGenralMaintenance input[id='flid']").val();
	    var assmId    = jQuery('#cmbGmntStationid').combobox('getValue');
	    var tradeId   = jQuery('#cmbGmntTrade').combobox('getValue');

	    var problem = jQuery("#txtGmntProblem").val();
	    var measure = jQuery("#txtGmntCountermeasure").val();
	    var downTime = jQuery("#txtGmntDowntime").val();

	    var entryDateRaw = jQuery('#dteGmntBookeddate').datebox('getValue');
	    var entryDate = gmntToPgTimestamp(entryDateRaw, '00:00');   // FIXED: was raw dd-MMM-yyyy string

	    var wsDate = jQuery('#dteGmntWostartdate').datebox('getValue');
	    var wsTime = jQuery('#spnWorkstartTime').spinner('getValue');
	    var weDate = jQuery('#dteGmntWoenddate').datebox('getValue');
	    var weTime = jQuery('#spnWorkendTime').spinner('getValue');

	    var startTime = gmntToPgTimestamp(wsDate, wsTime);   // FIXED: now yyyy-MM-ddTHH:mm:ss
	    var endTime   = gmntToPgTimestamp(weDate, weTime);   // FIXED

	    var fData = "formType=" + (thisMode == "estimate" ? "Estimate" : "Actual");
	    fData += "&DocType=GEN&DocNo=" + gmKeyId;
	    fData += "&FactoryId=" + factId;
	    fData += "&SectionId=" + sectionId;
	    fData += "&CellId=" + cellId;
	    fData += "&MachineId=" + machId;
	    fData += "&AssemblyId=" + assmId;
	    fData += "&PhenomenaId=";
	    fData += "&CauseId=";
	    fData += "&TradeId=" + tradeId;
	    fData += "&Problem=" + escape(problem);
	    fData += "&Measure=" + escape(measure);
	    fData += "&StartTime=" + escape(startTime);
	    fData += "&EndTime=" + escape(endTime);
	    fData += "&EntryDate=" + escape(entryDate);
	    fData += "&DownTime=" + downTime;

	    LoadPopUp("divGmntCostinfo", 'costSummary_input.crt?'+fData, true, "95%","90%","10px", null, null, formTitle, false, true, true);
	    return;
	}
	// ---- END ----
	 if(jQuery('#cmbGmntKeyid').val()!=""){
		 
		 if( jQuery('#chkbdSubmittoSap').is(':checked') == true){
 			 var qty=null;


 var allRows = jQuery('#sapInfoGrid').jqGrid('getRowData');							
	for( var i = 1; i <= allRows.length;i++){
		
	 qty=jQuery('#txtSspmQuantity_'+i).val();						 
	
	}
	//alert("Quantity"+qty);
	if(qty>0){
		 var sapMsg = "Do You Want To Submit To SAP ";// This Breakdown ("+jQuery('#cmbbdmsKeyid').combobox('getValue')+")";
		 
		 if(confirm(sapMsg) == false)
		{
				return false;
		}
		 else {				
				
			   show_winMask(1);
			   jQuery(".save-loading").html("Please wait while submitting...").show();
				
			//var bdKeyId=jQuery('cmbbdmsKeyid').combobox('getValue');
								 
		 	submitToSap("General Maintenance", "GEN_MAINTENANCE_EQUIPMENT", result.successData.keyId);
		 	/*setTimeout(function() {
		 	var bdKeyId =getFieldValue("cmbbdmsKeyid","frmBDMaster");
			jQuery('#txtSapOrderNo').val(bdKeyId);
		 	//processAjaxCalls("updateTransaction.brdn","tranId="+result.successData.keyId+"&status="+jQuery("#txtErrppostStatus").val(),"updtBdsatatus_successcalbk","updatestatus_errorcalbk",null,result.transId,true);
		 	},60000);*/
		 	
		 	//mano contue the comment
	 		/*  jQuery(".save-loading").hide();
	 		   show_winMask(0);
				
		 }


		 }
			else {alert('Enetr the Quantity'); 			 	 	
			
		}
		
		}
	}
	 else
	 if( result.successData.keyId != undefined  ){
		
	 	jQuery("#cmbGmntKeyid").combobox('setValue', result.successData.keyId);	
	//reloadForm('frmGenralMaintenance');
	//if(mode=="create"){
		
	 	checkSave();
	
 	
//	}
	//else		
	
	
	setTimeout(function(){
		 if( jQuery('#chkbdSubmittoSap').is(':checked') == true){
			 			 var qty=null;
			
			
			 var allRows = jQuery('#sapInfoGrid').jqGrid('getRowData');							
				for( var i = 1; i <= allRows.length;i++){
					
				 qty=jQuery('#txtSspmQuantity_'+i).val();						 
				
				}
				//alert("Quantity"+qty);
				if(qty>0){
					 var sapMsg = "Do You Want To Submit To SAP ";// This Breakdown ("+jQuery('#cmbbdmsKeyid').combobox('getValue')+")";
					 
					 if(confirm(sapMsg) == false)
					{
							return false;
					}
					 else {				
							
						   show_winMask(1);
						   jQuery(".save-loading").html("Please wait while submitting...").show();
							
						//var bdKeyId=jQuery('cmbbdmsKeyid').combobox('getValue');
											 
					 	submitToSap("General Maintenance", "GEN_MAINTENANCE_EQUIPMENT", result.successData.keyId);
					 	/*setTimeout(function() {
					 	var bdKeyId =getFieldValue("cmbbdmsKeyid","frmBDMaster");
						jQuery('#txtSapOrderNo').val(bdKeyId);
					 	//processAjaxCalls("updateTransaction.brdn","tranId="+result.successData.keyId+"&status="+jQuery("#txtErrppostStatus").val(),"updtBdsatatus_successcalbk","updatestatus_errorcalbk",null,result.transId,true);
					 	},60000);*/
				 		/*  jQuery(".save-loading").hide();
				 		   show_winMask(0);
							
					 }
			
			
					 }
						else {alert('Enetr the Quantity'); 			 	 	
						
					}
				}
		 
	},1500);						
	 	//setTimeout(function() {checkSave();},1250);
	/* 	
	var backTo = result.successData.backTo;
	var prevPage = result.BACKTO;
	if(backTo != null && backTo != ' ' && backTo != '')
	{
		if(backTo == "GMFromWOServlet")
		{
			if(prevPage != null && prevPage != ' ' && prevPage != ''){}
				popFormNavigation();
			 navigateToPrevForm();
		}
	}*/
	
	/*  } 
	
}   */ 
//mano 
function frmGenralMaintenance_successsCallback(result)
{	
	console.log("GMNT save result:", result);

	if(gmntCostInfoMode == "estimate" || gmntCostInfoMode == "actual")
	{
	    var thisMode = gmntCostInfoMode;
	    gmntCostInfoMode = null;

	    var formTitle = (thisMode == "estimate") ? "Cost Information - Estimation" : "Cost Information - Actual";

	    var gmKeyId = (result.successData && result.successData.keyId) ? result.successData.keyId : jQuery('#cmbGmntKeyid').combobox('getValue');

	    var factId    = jQuery("#frmGenralMaintenance input[id='factory']").val();
	    var sectionId = jQuery("#frmGenralMaintenance input[id='section']").val();
	    var cellId    = jQuery("#frmGenralMaintenance input[id='cell']").val();
	    var machId    = jQuery("#frmGenralMaintenance input[id='machine']").val();
	    var flid      = jQuery("#frmGenralMaintenance input[id='flid']").val();
	    var assmId    = jQuery('#cmbGmntStationid').combobox('getValue');
	    var tradeId   = jQuery('#cmbGmntTrade').combobox('getValue');

	    var problem = jQuery("#txtGmntProblem").val();
	    var measure = jQuery("#txtGmntCountermeasure").val();
	    var downTime = jQuery("#txtGmntDowntime").val();

	    var entryDateRaw = jQuery('#dteGmntBookeddate').datebox('getValue');
	    var entryDate = gmntToPgTimestamp(entryDateRaw, '00:00');

	    var wsDate = jQuery('#dteGmntWostartdate').datebox('getValue');
	    var wsTime = jQuery('#spnWorkstartTime').spinner('getValue');
	    var weDate = jQuery('#dteGmntWoenddate').datebox('getValue');
	    var weTime = jQuery('#spnWorkendTime').spinner('getValue');

	    var startTime = gmntToPgTimestamp(wsDate, wsTime);
	    var endTime   = gmntToPgTimestamp(weDate, weTime);

	    var fData = "formType=" + (thisMode == "estimate" ? "Estimate" : "Actual");
	    fData += "&DocType=GEN&DocNo=" + gmKeyId;
	    fData += "&FactoryId=" + factId;
	    fData += "&SectionId=" + sectionId;
	    fData += "&CellId=" + cellId;
	    fData += "&MachineId=" + machId;
	    fData += "&AssemblyId=" + assmId;
	    fData += "&PhenomenaId=";
	    fData += "&CauseId=";
	    fData += "&TradeId=" + tradeId;
	    fData += "&Problem=" + escape(problem);
	    fData += "&Measure=" + escape(measure);
	    fData += "&StartTime=" + escape(startTime);
	    fData += "&EndTime=" + escape(endTime);
	    fData += "&EntryDate=" + escape(entryDate);
	    fData += "&DownTime=" + downTime;

	    LoadPopUp("divGmntCostinfo", 'costSummary_input.crt?'+fData, true, "95%","90%","10px", null, null, formTitle, false, true, true);
	    return;
	}
	// ---- END ----

	var saveMsg = (result.successData && result.successData.msg) ? String(result.successData.msg).toLowerCase() : "";
	var saveSuccess = saveMsg.indexOf("success") !== -1;

	var existingKeyId = jQuery('#cmbGmntKeyid').combobox('getValue');
	var keyIdForSap = (result.successData && result.successData.keyId) ? result.successData.keyId : existingKeyId;

	console.log("saveSuccess:", saveSuccess, " | existingKeyId:", existingKeyId, " | keyIdForSap:", keyIdForSap);

	 if(existingKeyId != ""){
		 if( jQuery('#chkbdSubmittoSap').is(':checked') == true){
 			 var qty=null;

			 var allRows = jQuery('#sapInfoGrid').jqGrid('getRowData');							
			for( var i = 1; i <= allRows.length;i++){
				
			 qty=jQuery('#txtSspmQuantity_'+i).val();						 
			
			}
			if(qty>0){
				 var sapMsg = "Do You Want To Submit To SAP ";
				 
				 if(confirm(sapMsg) == false)
				{
						return false;
				}
				 else {				
						
					   show_winMask(1);
					   jQuery(".save-loading").html("Please wait while submitting...").show();
						
					submitToSap("General Maintenance", "GEN_MAINTENANCE_EQUIPMENT", keyIdForSap);

				 		 jQuery(".save-loading").hide();
				 		   show_winMask(0);
						
				 }


				 }
					else {alert('Enetr the Quantity'); 			 	 	
					
				}
			
			}
			else{
				if(saveSuccess){
					console.log("BRANCH: modify-mode, SAP not requested, save success -> clearForm NOW");
					clearForm('frmGenralMaintenance');

					// FIX: clearForm() only clears visible widgets, not the
					// raw hidden factory/section/cell/machine/flid inputs.
					// lodFuncLoc() reads THOSE hidden values to build the
					// breadcrumb, so if we don't reset them first it just
					// rebuilds the same old breadcrumb.
					jQuery("#frmGenralMaintenance input[id='factory']").val('');
					jQuery("#frmGenralMaintenance input[id='section']").val('');
					jQuery("#frmGenralMaintenance input[id='cell']").val('');
					jQuery("#frmGenralMaintenance input[id='machine']").val('');
					jQuery("#frmGenralMaintenance input[id='flid']").val('');
					jQuery("#frmGenralMaintenance input[id='elementId']").val('');

					lodFuncLoc('MCH');
				}
			}
	}
	 else{
		 if(saveSuccess){
			 console.log("BRANCH: create-mode, save success");

			 if(result.successData && result.successData.keyId){
			 	jQuery("#cmbGmntKeyid").combobox('setValue', result.successData.keyId);	
			 	checkSave();
			 }

			setTimeout(function(){
				 if( jQuery('#chkbdSubmittoSap').is(':checked') == true){
					 var qty=null;
					
					 var allRows = jQuery('#sapInfoGrid').jqGrid('getRowData');							
						for( var i = 1; i <= allRows.length;i++){
							
						 qty=jQuery('#txtSspmQuantity_'+i).val();						 
						
						}
						if(qty>0){
							 var sapMsg = "Do You Want To Submit To SAP ";
							 
							 if(confirm(sapMsg) == false)
							{
									return false;
							}
							 else {				
									
								   show_winMask(1);
								   jQuery(".save-loading").html("Please wait while submitting...").show();
									
							 	submitToSap("General Maintenance", "GEN_MAINTENANCE_EQUIPMENT", keyIdForSap);

						 		 jQuery(".save-loading").hide();
						 		   show_winMask(0);
									
							 }
					
					
							 }
								else {alert('Enetr the Quantity'); 			 	 	
								
							}
						}
				 else{
					 console.log("BRANCH: create-mode, SAP not requested -> clearForm NOW");
					 clearForm('frmGenralMaintenance');

					 // FIX: same reasoning as the modify-mode branch above.
					 jQuery("#frmGenralMaintenance input[id='factory']").val('');
					 jQuery("#frmGenralMaintenance input[id='section']").val('');
					 jQuery("#frmGenralMaintenance input[id='cell']").val('');
					 jQuery("#frmGenralMaintenance input[id='machine']").val('');
					 jQuery("#frmGenralMaintenance input[id='flid']").val('');
					 jQuery("#frmGenralMaintenance input[id='elementId']").val('');

					 lodFuncLoc('MCH');
				 }
			},1500);						
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
	/*function frmGenralMaintenance_beforeSubmit()
		{
	
	 		 return 'SetupandLoss='+convertGridToJSONArr('subLossGrid');
		}*/
		/*function checkFutureDate(dateboxId)
		{
		    var val = jQuery('#'+dateboxId).datebox('getValue');
		    if(val == null || jQuery.trim(val) == ''){
		        return true;
		    }

		    var today = new Date();
		    today.setHours(0,0,0,0);

		    var selDate = new Date(val);
		    selDate.setHours(0,0,0,0);

		    if(selDate > today){
		        var d = new Date();
		        var dd = d.getDate();
		        var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		        var mm = months[d.getMonth()];
		        var yyyy = d.getFullYear();
		        jQuery('#'+dateboxId).datebox('setValue', dd+"-"+mm+"-"+yyyy);

		        clearValidationErrorMsg(dateboxId);   // <-- reset aana udane message clear pannidum

		        return true;
		    }
		    else{
		        clearValidationErrorMsg(dateboxId);
		        return true;
		    }
		}*/
		function parseDDMMMYYYY(val)
		{
		    if(val == null || jQuery.trim(val) == ''){
		        return null;
		    }
		    var parts = val.split('-');
		    if(parts.length != 3){
		        return null;
		    }
		    var months = {"Jan":0,"Feb":1,"Mar":2,"Apr":3,"May":4,"Jun":5,"Jul":6,"Aug":7,"Sep":8,"Oct":9,"Nov":10,"Dec":11};
		    var dd = parseInt(parts[0], 10);
		    var mm = months[parts[1]];
		    var yyyy = parseInt(parts[2], 10);

		    if(isNaN(dd) || mm == undefined || isNaN(yyyy)){
		        return null;
		    }
		    return new Date(yyyy, mm, dd);
		}

		function checkFutureDate(dateboxId, selDateObj)
		{
		    var today = new Date();
		    today.setHours(0,0,0,0);

		    var selDate;

		    if(selDateObj != null && selDateObj instanceof Date && !isNaN(selDateObj.getTime())){
		        // onSelect callback la irundhu neraiya kedaikura Date object ah use pannurom
		        // idhu dhaan race condition ah thadukkum
		        selDate = new Date(selDateObj.getTime());
		    }
		    else{
		        var val = jQuery('#'+dateboxId).datebox('getValue');
		        if(val == null || jQuery.trim(val) == ''){
		            return true;
		        }
		        selDate = parseDDMMMYYYY(val);
		        if(selDate == null){
		            clearValidationErrorMsg(dateboxId);
		            return true;
		        }
		    }

		    selDate.setHours(0,0,0,0);

		    if(selDate > today){
		        showValidationErrorMsg(dateboxId, 'Future date is not allowed');

		        setTimeout(function(){
		            var d = new Date();
		            var dd = d.getDate();
		            var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		            var mm = months[d.getMonth()];
		            var yyyy = d.getFullYear();
		            jQuery('#'+dateboxId).datebox('setValue', dd+"-"+mm+"-"+yyyy);
		        }, 1000);

		        return false;
		    }
		    else{
		        clearValidationErrorMsg(dateboxId);
		        return true;
		    }
		}

		//mano
	/* function validateOccurredDateTime()
{
    clearValidationErrorMsg('dteGmntOccureddate');

    var occDateVal = jQuery('#dteGmntOccureddate').datebox('getValue');
    var occTimeVal = jQuery('#spnoccuredTime').spinner('getValue');

    if(occDateVal == '' || occDateVal == null || occTimeVal == '' || occTimeVal == null)
        return true; // nothing to validate yet

    var occDate = parseDDMMMYYYY(occDateVal);   // reuse the file's own parser, no external dependency
    if(occDate == null)
        return true; // can't parse yet, don't block

    var timeParts = occTimeVal.split(':');
    var occHour = parseInt(timeParts[0], 10);
    var occMin  = parseInt(timeParts[1], 10);
    if(isNaN(occHour) || isNaN(occMin))
        return true;

    occDate.setHours(occHour, occMin, 0, 0);

    var now = new Date();   // client "now" - same source checkFutureDate/compareDates already use here

    if(occDate > now)
    {
        alert('Occurred Date/Time cannot be a future date/time');
        showValidationErrorMsg('dteGmntOccureddate','Should Not Exceed Current Date/Time');

        // Retain current date/time instead of the future value picked
        fillWithCurrentDate('dteGmntOccureddate');
        fillWithCurrentDate('spnoccuredTime');

        return false;
    }
    return true;
}
	 */	
	 //mano
	 // Reusable: Work Start & Work End - future date/time block pannum
// (same pattern as validateOccurredDateTime, but parameterized)
function validateNotFutureDateTime(dateboxId, spinnerId, selDateObj)
{
    clearValidationErrorMsg(dateboxId);

    var selDate;

    if(selDateObj != null && selDateObj instanceof Date && !isNaN(selDateObj.getTime())){
        selDate = new Date(selDateObj.getTime());
    }
    else{
        var dateVal = jQuery('#'+dateboxId).datebox('getValue');
        if(dateVal == '' || dateVal == null)
            return true; // innum date select pannala, block panna vendaam
        selDate = parseDDMMMYYYY(dateVal);
        if(selDate == null)
            return true;
    }

    var timeVal = jQuery('#'+spinnerId).spinner('getValue');
    if(timeVal == '' || timeVal == null)
        return true;

    var timeParts = timeVal.split(':');
    var hh = parseInt(timeParts[0], 10);
    var mm = parseInt(timeParts[1], 10);
    if(isNaN(hh) || isNaN(mm))
        return true;

    selDate.setHours(hh, mm, 0, 0);

    var now = new Date();

    if(selDate > now)
    {
        alert('Date/Time cannot be a future date/time');
        showValidationErrorMsg(dateboxId, 'Should Not Exceed Current Date/Time');

        // panel close aagi mudika konjam neram kudukanum (Occurred Date-la
        // use panna reason ithan) - illana easyUI internal state break aagum
        setTimeout(function(){
            jQuery('#'+dateboxId).datebox('hidePanel');
            fillWithCurrentDate(dateboxId);
            fillWithCurrentDate(spinnerId);
            clearValidationErrorMsg(dateboxId);
        }, 300);

        return false;
    }
    return true;
}
	function validateOccurredDateTime(selDateObj)
{
    clearValidationErrorMsg('dteGmntOccureddate');

    var occDate;

    if(selDateObj != null && selDateObj instanceof Date && !isNaN(selDateObj.getTime())){
        // Date object passed straight from the datebox onSelect callback --
        // avoids the race condition where datebox('getValue') hasn't been
        // updated internally yet at the moment onSelect fires.
        occDate = new Date(selDateObj.getTime());
    }
    else{
        var occDateVal = jQuery('#dteGmntOccureddate').datebox('getValue');
        if(occDateVal == '' || occDateVal == null)
            return true; // nothing to validate yet
        occDate = parseDDMMMYYYY(occDateVal);
        if(occDate == null)
            return true;
    }

    var occTimeVal = jQuery('#spnoccuredTime').spinner('getValue');
    if(occTimeVal == '' || occTimeVal == null)
        return true;

    var timeParts = occTimeVal.split(':');
    var occHour = parseInt(timeParts[0], 10);
    var occMin  = parseInt(timeParts[1], 10);
    if(isNaN(occHour) || isNaN(occMin))
        return true;

    occDate.setHours(occHour, occMin, 0, 0);

    var now = new Date();

    if(occDate > now)
    {
        alert('Occurred Date/Time cannot be a future date/time');
        showValidationErrorMsg('dteGmntOccureddate','Should Not Exceed Current Date/Time');

        // Defer the reset -- the datebox panel is still mid-close right now, so
        // setting its value synchronously inside its own onSelect callback breaks
        // easyUI's internal combo state (same reason checkFutureDate() defers below).
        setTimeout(function(){
            jQuery('#dteGmntOccureddate').datebox('hidePanel');
            fillWithCurrentDate('dteGmntOccureddate');
            fillWithCurrentDate('spnoccuredTime');
            clearValidationErrorMsg('dteGmntOccureddate');
        }, 300);

        return false;
    }
    return true;
}
		
		function frmGenralMaintenance_beforeSubmit()
		{
		    var machId = getFieldValue('cmbGmntMachineid','frmGenralMaintenance');
		    if(machId == null || machId == '' || machId == ' '){
		        popupCommonErrorMsg("Select the Equipment");
		        return false;
		    }

		    var orderType = jQuery('#cmbGmntOrederType').combobox('getValue');
		    if(orderType == null || orderType.length == 0){
		        popupCommonErrorMsg("Select the Order Type");
		        return false;
		    }

		    var shiftDate = getFieldValue('dteGmntShiftdate','frmGenralMaintenance');
		    if(shiftDate == null || shiftDate == '' || shiftDate == ' '){
		        popupCommonErrorMsg("Select the Shift Date");
		        return false;
		    }

		    var shift = jQuery('#cmbGmntShift').combobox('getValue');
		    if(shift == null || shift.length == 0){
		        popupCommonErrorMsg("Select the Shift");
		        return false;
		    }

		    var occDate = getFieldValue('dteGmntOccureddate','frmGenralMaintenance');
		    if(occDate == null || occDate == '' || occDate == ' '){
		        popupCommonErrorMsg("Select the Occurred Date");
		        return false;
		    }

		    var activityType = jQuery('#cmbGmntActivitytype').combobox('getValue');
		    if(activityType == null || activityType.length == 0){
		        popupCommonErrorMsg("Select the Activity Type");
		        return false;
		    }

		    var trade = jQuery('#cmbGmntTrade').combobox('getValue');
		    if(trade == null || trade.length == 0){
		        popupCommonErrorMsg("Select the Maintenance Section");
		        return false;
		    }

		    var reportedBy = jQuery('#cmbGmntReportedby').combobox('getValue');
		    if(reportedBy == null || reportedBy.length == 0){
		        popupCommonErrorMsg("Select the Reported By");
		        return false;
		    }

		    var sparesY = jQuery('#chkIssparesY').is(':checked');
		    var sparesN = jQuery('#chkIssparesN').is(':checked');
		    var sparesW = jQuery('#chkIssparesW').is(':checked');
		    if(!sparesY && !sparesN && !sparesW){
		        popupCommonErrorMsg("Select the Spare Replaced");
		        return false;
		    }

		    var problem = jQuery('#txtGmntProblem').val();
		    if(problem == null || jQuery.trim(problem).length == 0){
		        popupCommonErrorMsg("Enter the Problem");
		        return false;
		    }

		    var status = jQuery('#cmbGmntStatus').combobox('getValue');
		    if(status == null || status.length == 0){
		        popupCommonErrorMsg("Select the Status");
		        return false;
		    }

		    var priority = jQuery('#cbobdmsPriority').combobox('getValue');
		    if(priority == null || priority.length == 0){
		        popupCommonErrorMsg("Select the Priority");
		        return false;
		    }

		    // Status "Completed" aana, Completed section fields mandatory
		    if(status == 'C'){
		        var completedBy = jQuery('#cmbGmntCompletedby').combobox('getValue');
		        if(completedBy == null || completedBy.length == 0){
		            popupCommonErrorMsg("Select the Completed By");
		            return false;
		        }

		        var woStart = getFieldValue('dteGmntWostartdate','frmGenralMaintenance');
		        if(woStart == null || woStart == '' || woStart == ' '){
		            popupCommonErrorMsg("Select the Work Start Date");
		            return false;
		        }

		        var woEnd = getFieldValue('dteGmntWoenddate','frmGenralMaintenance');
		        if(woEnd == null || woEnd == '' || woEnd == ' '){
		            popupCommonErrorMsg("Select the Work End Date");
		            return false;
		        }
		    }

		    return 'SetupandLoss='+convertGridToJSONArr('subLossGrid');
		}

	function sapInfoComplete(){}
	function sapInfoErr(){}
	var checkY=jQuery('#hdnSpares').val();;
		var genKeyId=jQuery('#cmbGmntKeyid').combobox('getValue');
		var sapsts=jQuery('#hdnErrppostStatus').val();
//var machine=jQuery('#cmbGmntKeyid').text();
		
		
   		 if(genKeyId==null||genKeyId==""){
	//alert(123);
	
		function checkSave(){
		
				var genKeyid=jQuery('#cmbGmntKeyid').combobox('getValue');
				var machine =jQuery('#cmbGmntMachineid').combobox('getText');
				
				if(genKeyid!=null||genKeyid!=undefined||genKeyid!=""){

					//	alert(genKeyid+"KeyId in If");
				jQuery("#tabGenMain").tabs({ onSelect:function(title){  
				if(title == "SAP Information")
					{
					//	alert("tab function Title"); 
					
					LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.balgenmain?keyId="+genKeyid+"&isSpares="+checkY+"&refDocType=GEN&machine="+machine+"&sapsts="+sapsts,"","sapInfoComplete","sapInfoErr");//?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");

					}
				}
			});
		}
		
	}			
}
else 
	{
	
	LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.balgenmain?keyId="+genKeyId+"&isSpares="+checkY+"&refDocType=GEN&machine="+machine+"&sapsts="+sapsts,"sapInfoComplete","sapInfoErr");//?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");
	
	}

/*	if(jQuery('#chkIssparesY').is(':checked') == true){
		jQuery('#sapInfo').show();
		}
		else
		jQuery('#sapInfo').hide();
		/*var gmKeyid=jQuery('#cmbGmntKeyid').val();
		var mode="";
		var flid = jQuery("#frmBDMaster input[id='flid']").val();
		var section = jQuery('#linfrmBDMasterSection > u > b').html();
		var cell = jQuery('#linfrmBDMasterCell > u > b').html();
		//var woid =jQuery('#').val();		
		
	//	LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.brdn?formMode="+mode+"&flid="+flid+"&bdkeyId="+gmKeyid+"&refDocType=GEN&functionalloc="+cell+"&costCenter="+costCenter,"","sapInfoComplete","sapInfoErr");
		

		}
	function sapInfoComplete(){}
	function sapInfoErr(){}*/

	/* jQuery('#chkIssparesY').toggle(function() {
		jQuery('#sapInfo').hide()
		}, function() {
			jQuery('#sapInfo').show()
		});
	if(jQuery('#chkIssparesY').attr('checked',true)){
		jQuery('#sapInfo').show();
		}
	else
		jQuery('#sapInfo').hide();*/

		var sapsts=jQuery('#hdnErrppostStatus').val();
		var status=jQuery('#hdngmntStatus').val();
		if(sapsts=="C" && status=="C"){
			jQuery("#chkbdSubmittoSap").attr('checked', true);
			//jQuery('#chkbdSubmittoSap').attr('checked',true);
			disableForm('frmGenralMaintenance');
			jQuery('#cmbGmntTrade').combobox('disable');
			//readOnlyFields('cmbGmntTrade');
			//readOnlyFields('cmbGmntOrederType');
				
			}
		var status=jQuery('#hdngmntStatus').val();		
			if(status=="C"){
				jQuery("#chkbdSubmittoSap").prop('disabled', false);
				}
			var ldModeCheck = jQuery('#ldmode').val();
			if(ldModeCheck == 'view' || ldModeCheck == 'VIEW'){
			    disableForm('frmGenralMaintenance');
			}


		</script>
<form id="frmGenralMaintenance" name="frmGenralMaintenance">
	<div id="wrapper" style="width:100%;">
		<div id="Genmaint" class="divbrdr">

<!--	<div style="float:right;"><input type="button"  value="BACK" id="back_genMain" class="easyui-button" /></div><br><br>-->
		<table width="100%">
			 <tr>
			 	 <td > 
			         <div  id="frmGenralMaintenanceFuntKeyIds"  >
						<input type="hidden" id="factory" name="cmbGmntFactoryid" value="${requestScope.plmTlGenmaintenance.gmntFactoryid}"></input>
						<input type="hidden" id="section" name="cmbGmntSectionid" value="${requestScope.plmTlGenmaintenance.gmntSectionid}"></input>
						<input type="hidden" id="cell" name="cmbGmntLineid" value="${requestScope.plmTlGenmaintenance.gmntLineid}"></input>
						<input type="hidden" id="machine" name="cmbGmntMachineid" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"></input>
						<input type="hidden" id="flid" name="cmbGmntFlid" value="${requestScope.plmTlGenmaintenance.gmntFlid}"></input>
						<input type="hidden" id="elementId" name="cmbGmntElementid" value="${requestScope.plmTlGenmaintenance.gmntElementid}"></input>
					 </div>
					 <div id="gmntfunLocation" style="padding-left: 0px;"></div>
					 <div style="float:right; vertical-align:middle;">
				 	<input id="btnGmntEstimation" name="btnGmntEstimation" type="button" class="easyui-button"
				 	       value="Resource Estimation" style="width:148px; height:28px;"/>
				 	<input id="btnGmntActual" name="btnGmntActual" type="button" class="easyui-button"
				 	       value="Cost Information" style="width:148px; height:28px;"/>
				 </div>
				  </td>
				  </tr> 
				  
				<tr>  
				  <td  style="padding-left: 0px;">
				 <!-- 
					<label  class="mandatory-lbl">Related To</label>
	            	<span style="padding-left:80px;">
	            	<label id="Mouldid_label">Mould</label></span>
	            	<span style="padding-left:125px;">
	            	 -->
	            	<label id="eqp_label">Equipment</label>
	            	
					 <label style="padding-left:280px; " class="mandatory-lbl">Order Type</label>
					 	
					 	<b><label style="padding-left:260px; " class="">SAP Number</label></b>
					 	
					 	<b><label style="padding-left:200px; " class="">SAP Status</label></b>
					 	</td>
					
			 	</tr> 
	            	
	            	<!--  </span> -->
		            
		            	<!-- <input id="cmbGmntRelatedto" name="cmbGmntRelatedto" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  readonly="readonly" value="${requestScope.plmTlGenmaintenance.gmntRelatedto }" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
		
		            	<span style="padding-left:5px;">
		             	<input id="cmbGmntMouldid" name="cmbGmntMouldid" class="easyui-combobox"  style="width:131px;text-transform:capitalize;" value="${requestScope.plmTlGenmaintenance.gmntMouldid}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
		             	</span>
		             	<span style="padding-left:24px;">
		             	 -->
		             	 <tr>
		             	 <td>
							<span>
		             	<input id="cmbGmntMachineid" name="cmbGmntMachineid" class="easyui-combobox" onkeydown="" style="width:280px;" value="${requestScope.plmTlGenmaintenance.gmntMachineid}"  value = "${ requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
		             	</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		             	<input  class="easyui-combobox"  id="cmbGmntOrederType" name="cmbGmntOrederType" style="padding-left: 200px;width:250px;" value="${requestScope.plmTlGenmaintenance.gmntOrderType}"  ${ requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}/></input>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  id="txtErrppostNo" name="txtErrppostNo" value="${requestScope.plmTlGenmaintenance.errppostNo}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;width:190px;background-color:#FCF9B0;font-weight:bold;color:#FF0000;text-align:center;"></input>
		             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  id="txtErrppostStatus" name="txtErrppostStatus" value="${requestScope.plmTlGenmaintenance.errppostStatus}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;width:190px;background-color:#FCF9B0;font-weight:bold;text-align:center;color:#FF0000;"></input>
		             	</td>
			 	</tr> 			 
			 </table>
			 
		<table style="width:100%";> 
	       <tr>	          	
				<td style="padding-left: 0px;" valign="top"> 
	          	<div class="sub-header"><span style="">Main Information</span></div>
	           		<div><label>Document No</label>
		            <span style="padding-left:66px;"><label >Date</label></span></div>
		<%--             <div class="easyui-paddingbfpx"  >
		            	<input id="cmbGmntKeyid" name="cmbGmntKeyid" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  value="${requestScope.plmTlGenmaintenance.gmntKeyid}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
		            	<span style="padding-left:5px;">
		            	<input class="easyui-datebox" clear ="false" id="dteGmntBookeddate" name="dteGmntBookeddate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
						</span>
					</div> 
	               
 --%>	
 <table>
	<tr>
		<td>
			<input id="cmbGmntKeyid" name="cmbGmntKeyid" class="easyui-combobox" style="width:135px;text-transform:capitalize;" value="${requestScope.plmTlGenmaintenance.gmntKeyid}" value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
		</td>
		<td>
			<span style="padding-left:5px;">
			<input class="easyui-datebox" clear="false" id="dteGmntBookeddate" name="dteGmntBookeddate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
			</span>
		</td>
	</tr>
</table>				
					<div><label>Assembly/Station</label>
					<input type="checkbox" id="chkGenOtherAssm" style="margin-left:25px" value="Y"/>  <label style="margin-left:3px">Others</label>
					</div>
				    <div class="easyui-paddingbfpx" >
				       	<input id="cmbGmntStationid" name="cmbGmntStationid" class="easyui-combobox"  style="width:275px;" value="${requestScope.plmTlGenmaintenance.gmntStationid}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/> </input>
				    </div>

			<div><label  class="mandatory-lbl">Shift Date</label>
            <span style="padding-left: 80px;"><label class="mandatory-lbl">Shift</label></span>
            </div>
<!--            <div  >-->
		<table>
			<tr>
            <td>	
            <input clear = "false"  id="dteGmntShiftdate" name="dteGmntShiftdate" class="easyui-datebox" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntShiftdate}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            </td>	
            <td>	
          <span style="padding-left:5px;">
            <input  id="cmbGmntShift" name="cmbGmntShift"  readonly="readonly"" class="" style="width: 133px;" value="${requestScope.plmTlGenmaintenance.gmntShift}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
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
            	<input clear = "false"  id="dteGmntOccureddate" name="dteGmntOccureddate" class="easyui-datebox" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntOccureddate}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            	<span style="padding-left:5px;">
             	<span class="spinner">
             	<input id="spnoccuredTime" name="spnoccuredTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 70px;" value="${requestScope.generalMaintainanceBean.occuredTime}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
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
            	<input id="txtGmntWorkhours" name="txtGmntWorkhours" class="easyui-text" style="width:130px;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntWorkhours}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            	<span style="padding-left:5px;">
             	<input id="txtGmntDowntime" name="txtGmntDowntime" class="easyui-text"  style="width:135px;text-transform:capitalize;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntDowntime}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
             	</span>
			</div> 
			
			<div>
			<label>Response Time</label>
			</div>
			<div class="easyui-paddingbfpx">
			<input id="txtGmntResponsetime" name="txtGmntResponsetime" class="easyui-text"  style="width:135px;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntResponsetime}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
			
				<span style="padding-left:15px;" ><input type="checkbox" id="chkbdSubmittoSap" name="chkbdSubmittoSap"  value="Y"/><label style="padding-left:5px; font-weight:bold;">Submit To SAP</label></span>
			</div>						
		</td>
	
	
	<td valign="top" style="padding-left:25px;" >
		
		<div class="sub-header"  ><span >Details For General Maintenance</span></div>
			<div style="float:left;margin-left:5px;">
				<div style="padding-top:5px">
					<label>Part Location</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="txtGmntPartlocation" name="txtGmntPartlocation" class="easyui-text"  style="width:245px;" value="${requestScope.plmTlGenmaintenance.gmntPartlocation}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
				</div>
				
				<div>
					<label class="mandatory-lbl">Activity Type</label>
				</div>
				
				<div class="easyui-paddingbfpx"  >
					<select id="cmbGmntActivitytype" name="cmbGmntActivitytype" class="easyui-combobox"  style="width:245px;text-transform:capitalize;"  value="${requestScope.plmTlGenmaintenance.gmntActivitytype}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">
						<option value=""></option>
						<option value="O">OTHERS</option>
						<option value="U">UNSCHEDULED(WITHOUT WO)</option>
						<option value="M">EQUIPMENT UNDER TRIAL</option>
					</select>
					<span id="spansubLoss">
					<input type="button" value="Sub Loss" id="btnsubLoss" class="easyui-button" style="height:25px;"/ ></input>
					</span>
				</div>
				
				<div style="margin-top: 5px;">
					<label class="mandatory-lbl" >Maintenance Section</label>
				</div>
				
				<div class="easyui-paddingbfpx">
					<input id="cmbGmntTrade" style="width:245px;" name="cmbGmntTrade" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntTrade}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
				</div>
<!--	modified		-->
				<div style="margin-top: 13px;" >
					<label >Machine Condition</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbGmntMchcondition" style="width:245px;" name="cmbGmntMchcondition" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntMchcondition}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
				</div>
			
				<div style="margin-top: 12px;">
					<label class="mandatory-lbl">Reported By</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input id="cmbGmntReportedby" style="width:245px;" name="cmbGmntReportedby" class="easyui-combobox"  value="${requestScope.plmTlGenmaintenance.gmntReportedby}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
				</div>	
				<div>
				<label id='lblSpareReplaced' class="mandatory-lbl">Spare Replaced</label>
			</div>
				<div class="bdSparesReplaced"  style="  width : 260px;height:10px;_height:6px;">
				<div style="margin-top:5px;padding-left:4px;">
					 <span style="margin-left: 25px"><input type="checkbox" id="chkIssparesY" name="chkIssparesY" value="Y"  value = "${requestScope.bdFormBean.disableIssparesY == true ? ' disabled':''}"/><label style="padding-left:5px;">Yes</label></span>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesN" name="chkIssparesN" value="N"  value = "${requestScope.bdFormBean.disableIssparesN == true ? ' disabled':''}"/><label style="padding-left:5px;">No</label></span>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesW" name="chkIssparesW" value="W"  value = "${requestScope.bdFormBean.disableIssparesW == true ? ' disabled':''}"/><label style="padding-left:5px;">Waiting</label></span>
					 <input id="sparesFlag" name="sparesFlag" type="text" style="display:none;" value="${requestScope.bdmTlDtl.bdanIssparesreplaced}"/>
					 
				</div>
					
			</div>
			

				
			</div>  <!-- end of float left --></td>
			<td>
		<div style="margin-left:20px;" >
			
			<div  style="margin-top: 30px;">
				<label class="mandatory-lbl">Problem</label>
			</div>
			
	        <div class="easyui-paddingbfpx" >
	          	<textarea rows="2" style="width: 245px;height:50px;" cols="" id="txtGmntProblem" maxlength="500" name="txtGmntProblem"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">${requestScope.plmTlGenmaintenance.gmntProblem}</textarea>
	        </div>
	          
			<div style="margin-top:5px;" >
				<label id="actionTaken" class="">Action Taken</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 245px;height:50px" cols="" id="txtGmntAction" name="txtGmntAction" maxlength="500"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">${requestScope.plmTlGenmaintenance.gmntAction}</textarea>
          	</div>
          	
          	<div style="margin-top:5px;">
          		<label>Root Cause</label>
          	</div>
          	
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 245px;height:50px" cols="" id="txtGmntRootcauseid" name="txtGmntRootcause" maxlength="500"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
          	</div>
          	
			<div>
				<label>Counter Measure</label>
			</div>
			
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="width: 245px;height:50px" cols="" id="txtGmntCountermeasure" name="txtGmntCountermeasure" maxlength="500"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">${requestScope.plmTlGenmaintenance.gmntCountermeasure}</textarea>
          	</div>

				
  </div>
	
</td>
	<td valign="top" style="padding-left:20px;">
				
			<div style="margin-top:40px;">
				<label class="mandatory-lbl">Status</label>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-bottom:15px;">
				<input id="cmbGmntStatus" name="cmbGmntStatus" class="easyui-text"  style="width:125px; " value="${requestScope.plmTlGenmaintenance.gmntStatus}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
			</div>
			<div style="margin-top:-05px;">
			
					<label >Target Date</label>
				</div>
				<div class="easyui-paddingbfpx" >
					<input clear = "false"  id="dteGmntTargetdate" name="dteGmntTargetdate" class="easyui-datebox" style="width:100px;" value="${requestScope.plmTlGenmaintenance.gmntTargetdate}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
					<span id="err_dteGmntTargetdate" class="tpm-errormsg"></span>
				</div>
				<div style="margin-top:-05px;">
			     <label style="" class="mandatory-lbl" id="lblPriority">Priority</label>
			   </div>
			   <span style="vertical-align:3px;">
					<select id="cbobdmsPriority" class="easyui-combobox" name="cbobdmsPriority" value="${requestScope.bdmTlMst.bdmsPriority}"  style="height: 22px;width:95px;font-size:9px;"  value = "${requestScope.bdFormBean.disablebdmsPriority == true ? ' disabled':''}">					
							<option value="V"> VERY HIGH</option>
							<option value="H"> HIGH</option>
							<option value="M"> MEDIUM</option>
							<option value="L"> LOW</option>
					</select> 				
				</span>
			<!-- 	mano -->
			<!-- <div style="padding-top:10px;">
    <input id="btnGmntEstimation" name="btnGmntEstimation" type="button" class="easyui-button"
           value="Resource Estimation" style="width:148px; height:30px;"/>
    <input id="btnGmntActual" name="btnGmntActual" type="button" class="easyui-button"
           value="Cost Information" style="width:148px; height:30px;"/>
</div> -->
			<!--  <div style="margin-top:10px">
		  		 <b><label style=";color:#FF0000;font-weight:bold;">SAP Status</label></b>
		  		 </div>
		  		 <div>
		  			<input type="text"  id="txtErrppostStatus" name="ErrppostStatus" value="${requestScope.Status}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;width:190px;background-color:#FCF9B0;font-weight:bold;text-align:left;color:#FF0000;">
			</div>-->
		</td>	
 <td valign="top" style="padding-left:0px;">	
    <div id="completed" style="display:none;margin-top:5px;">
			
			<div class="sub-header" >
				<span style="padding-left:5px;"><label>Completed Details</label></span>
			</div>
			
    <div style="padding-left:1px;">
				
			<div class="mandatory-lbl" style="font-size: 12;"><label>Completed By</label></div>
			 <div class="easyui-paddingbfpx">
			 	<input id="cmbGmntCompletedby" name="cmbGmntCompletedby" class="easyui-combobox"  style="width:190px;"  value="${requestScope.plmTlGenmaintenance.gmntCompletedby}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input> 
			 </div>
			 
			 <div>
			 	<label  class="mandatory-lbl">Work Start</label>		      	 
		     </div>
		     
            <div>
            	<input id="dteGmntWostartdate" name="dteGmntWostartdate"  clear = "false" class="easyui-datebox" style="width:120px;"   value="${requestScope.plmTlGenmaintenance.gmntWostartdate}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            	<span style="padding-left:5px;">
            	 <span class="spinner">
            	 <input  id="spnWorkstartTime" name="spnWorkstartTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workstartTime}"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            	 </span></span>
            	 <span id="err_dteGmntWostartdate" class="tpm-errormsg" ></span>
			</div> 
			
		 	<div>
		 		<label  class="mandatory-lbl">Work End</label>
<!--	            <span style="padding-left: 68px;"><label class="mandatory-lbl"></label></span>-->
	        </div>
	        
            <div>
            	<input id="dteGmntWoenddate" name="dteGmntWoenddate"  clear = "false" class="easyui-datebox" style="width:120px;"  value="${requestScope.plmTlGenmaintenance.gmntWoenddate}"   value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
            	<span style="padding-left:5px;"></span>
                <span class="spinner">
                <input  id="spnWorkendTime" name="spnWorkendTime"   class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workendTime}" value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/></input>
                </span>
                <span id="err_dteGmntWoenddate" class="tpm-errormsg"  ></span>
			</div> 
			
			<div><label>Remarks</label></div>
		          	<div class="easyui-paddingbfpx" >
		          		<textarea rows="2" style="width: 200px;" cols="" id="txtGmntRemarks" name="txtGmntRemarks"  value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}">${requestScope.plmTlGenmaintenance.gmntRemarks}</textarea>
		   			 </div>			         		
			</div>	
	</div>
	
			</td>
	  <!-- 	<td valign="top" style="margin-left:25px;">
				
			<div style="margin-top:40px;">
			
					<label >Target Date</label>
				</div>
				
				<div class="easyui-paddingbfpx" >
					<input clear = "false"  id="dteGmntTargetdate" name="dteGmntTargetdate" class="easyui-datebox" style="width:100px;" value="${requestScope.plmTlGenmaintenance.gmntTargetdate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input>
					<span id="err_dteGmntTargetdate" class="tpm-errormsg"></span>
				</div>
				</td> -->  
		</tr>
		
	</table>
	
				
	<!--  
	<div>
		<label class="mandatory-lbl">Spares Replaced</label>
	</div>
	<!-- <div class="bdSparesReplaced"  style="  width : 294px;height:10px;_height:6px;">
			<div style="margin-top:5px;padding-left:4px;"> -->
				<!--	<input style="margin-left: 35px" type="checkbox" id="chkIssparesY" name="chkIssparesY" value="Y" <c:out value = "${requestScope.bdFormBean.disableIssparesY == true ? ' disabled':''}"/>/><label style="padding-left:5px;">Yes</label>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesN" name="chkIssparesN" value="N" <c:out value = "${requestScope.bdFormBean.disableIssparesN == true ? ' disabled':''}"/>/><label style="padding-left:5px;">No</label></span>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesW" name="chkIssparesW" value="W" <c:out value = "${requestScope.bdFormBean.disableIssparesW == true ? ' disabled':''}"/>/><label style="padding-left:5px;">Waiting</label></span>
					 <input id="sparesFlag" name="sparesFlag" type="text" style="display:none;" value="${requestScope.bdmTlDtl.bdanIssparesreplaced}"/>
					<span style="margin-left: 150px"><input type="checkbox" id="tecCompl" name="tecCompl"  <c:out value = "${requestScope.bdFormBean.disableIssparesW == true ? ' disabled':''}"/>/><label style="padding-left:5px;">Tech.Completion</label></span>
					--> 
					 <!--<input type="button" id="btnSapCheck" class="easyui-button" value="..." style="height: 22px;margin-top:-24px;margin-left: 25px"/>
			<!--   	<span style="margin-left: 150px"><input  type="button" id="btnSapDtls" Name="btnSapDtls" value=" SAP DETAILS" class="easyui-button"/></span>-->
	<div id="sapInfo">
		<div id="tabGenMain"  class="easyui-tabs" style="width:1000px;margin-top:20px;">
		 <!-- <div title=""></div>-->
	   <div  id="SAPInformation" title="SAP Information" class="easyui-tabs" style="width:1000px;margin-top:20px; style="padding:4px;"> <!-- Work Details Div -->
		<!-- 
		<div id="Header">
			<table> 
		
				<tr><td><label class="mandatory-lbl">Order Type</label></td><td>
						<input  class="easyui-combobox" id="cmbOredertype" name="cmbOredertype" style="width:140px;" value="${requestScope.orderType}" /> </Td><Td><label> SAP Cost Center</label></Td><td><input tuye="text" class="easyui-text" name="txtSapCostcenter" id="txtSapCostCenter" size="30" value="${requestScope.BDFormBean.bphmSectionid}"></td><Td><label>No.Of Capacities Required</label></Td><Td><input type="text" class="easyui-text" id="txtNoCapacities" size="30" class="easyui-text" />
				</td></tr>
				<tr></tr>	
				<tr><td><label >Work Center</label></td><td><input type="text" class="easyui-text" id="txtWorkCenter" name="txtWorkCenter" size="30"></Td><Td><label> SAP Functional Location</label></Td><Td><input type="text" id="txtSapFunLocation" name="txtSapFunLocation" size="30"  class="easyui-text" value="${requestScope.funLoc}"></Td><Td><label>Normal Duration Of the Activity</label></Td><Td><input type="text"  id="txtNormalDtActivity" size="30" class="easyui-text"/></td></tr>
			</table>
 	
	<div style="margin-top:20px">
		<label> No.Of Spares Count:          No.Of Quantities</label>
		<div id="buttons" style="padding-left:600px;margin-top:-20px">
			<input type="button" id="btnSaveInfo" class="easyui-button" name="btnSaveInfo" value="Save Info"  >

			<input type="button" id="btnRefreshSapInfo" class="easyui-button" name="btnRefreshSapInfo" value="Refresh SAP Info" style="pading-left:10px;" >
			<input type="button" id="btnSpareshReplaced" class="easyui-button" name="btnSpareshReplaced" value="Spares Information" style="pading-left:10px;">
		</div>
	</div>-->
	<div id="prevloadSapInfo" class="loading"></div>
			<div id="loadSapInfo">
			
			</div>	
</div>
	<!-- <div style="width:100%">  
		<table id="sapInfoGrid" ><tr><td></td></tr></table>
	</div>
	<div id="sapinfopager"></div>
 -->
		<input type="hidden" id="txtExistwoid" name="txtExistwoid" value="${requestScope.existWoId }"/>
		<input type="hidden" id="txtRefDocId" name="txtRefDocId" value="${requestScope.refdocId }"/>	 
 		<input type="hidden" id="txtSparse" name="txtSparse" value="${requestScope.spares}"/>
 		<input type="hidden" id="hdnbdkeyId" name="hdnbdkeyId" value="${requestScope.bdkeyid}"/>
 	 	<input type="hidden" id="hdnbdorederType" name="hdnbdorederType" value="${requestScope.orderType}"/>
 	

<!--	        <div class="floatleft" style="padding-right: 30px;">&nbsp;</div>	-->
	    <!--   <div class="floatleft" style="">-->
		<!--	<div id="loadSparesReplaced"></div>
			<label> Sap Information</label>-->
	</div> 
 
<!--  
<div class="sub-header" style="pading-left:35px;width:80%">
				<span style="padding-left:5px;"><label>SAP Details</label></span>

			<div id="prevloadSapInfo"></div>
			<div id="loadSapInfo">
			
			</div>	
</div> 
<div> 				
<Table width="100%">
<Tr><Td> <label  style="font-weight:Strong;">SAP Status</label></Td><Td><input  class="easyui-text" id="txtSapStatus" name="txtSapStatus" size="30"VALUE="" ></Td><Td><label> SAP Cost Center</label></Td><td><input tuye="text" class="easyui-text" name="txtSapCostcenter" id="txtSapCostCenter" size="30" value="${requestScope.BDFormBean.bphmSectionid}"></td><Td><label>No.Of Capacities Required</label></Td><Td><input type="text" class="easyui-text" id="txtNoCapacities" size="30" class="easyui-text"></Td></Tr>
<Tr></Tr>	
<Tr><Td><label >Work Center</label></Td><Td><input type="text" class="easyui-text" id="txtWorkCenter" name="txtWorkCenter" size="30"></Td><Td><label> SAP Functional Location</label></Td><Td><input type="text" id="txtSapFunLocation" name="txtSapFunLocation" size="30"  class="easyui-text" value="${requestScope.funLoc}"></Td><Td><label>Normal Duration Of the Activity</label></Td><Td><input type="text" id="txtNormalDtActivity" size="30" class="easyui-text"></Td></Tr>
</Table>

<div style="margin-top:20px">
<lable>No.Of Spares Count:<lable><lable>No.Of Quantities</lable>

<div id="buttons" style="padding-left:750px;margin-top:-20px">
<input type="button" id="btnRefreshSapInfo" class="easyui-button" name="btnRefreshSapInfo" value="Refresh SAP Info"  >
<input type="button" id="btnSpareshReplaced" class="easyui-button" name="btnSpareshReplaced" value="Spares Information" style="pading-left:10px;">
</div>
</div>
</div>
<div> 
<table id="sapInfoGrid"></table>
</div>
<div id="sapinfopager"></div>


 	<input type="hidden" id="txtSparse" name="txtSparse" value="${requestScope.spares}"/>
-->
			</div>
		
	</div>
	    <input type="hidden" id="hdndteGmntShiftdate" name="hdndteGmntShiftdate" class="easyui-datebox" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntShiftdate}"></input>
		<input type="hidden" id="hdndteGmntWoenddate" name="hdndteGmntWoenddate"  clear = "false" class="easyui-datebox" style="width:120px;"  value="${requestScope.plmTlGenmaintenance.gmntWoenddate}"></input>
        <input type="hidden" id="hdnspnWorkendTime" name="hdnspnWorkendTime"   class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workendTime}"></input>
	    <input type="hidden" id="hdndteGmntWostartdate" name="hdndteGmntWostartdate"  clear = "false" class="easyui-datebox" style="width:120px;"   value="${requestScope.plmTlGenmaintenance.gmntWostartdate}"/></input>
	    <input type="hidden" id="hdnspnWorkstartTime" name="hdnspnWorkstartTime"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="${requestScope.generalMaintainanceBean.workstartTime}" /></input>
		<input type="hidden" id="hdndteGmntTargetdate" name="hdndteGmntTargetdate" class="easyui-datebox" style="width:100px;" value="${requestScope.plmTlGenmaintenance.gmntTargetdate}" ></input>
		<input type="hidden" id="mode" value="${ requestScope.generalMaintainanceBean.formMode }"></input>
		<input type="hidden" id="ldmode" value="${requestScope.mode}"></input>
		<input type="hidden" id="hdnFactid" name="hdnFactid"/></input>
		<input type="hidden" id="hdnTmpActType" value="${requestScope.plmTlGenmaintenance.gmntActivitytype}"/>
		<input type="hidden" id="hdnGmntRefdocid" name="hdnGmntRefdocid" value="${ requestScope.plmTlGenmaintenance.gmntRefdocid }"></input>
		<input type="hidden" id="hdnsetupandadj" name ="hdnsetupandadj" class="easyui-text" value="${requestScope.menumode } "/></input>
		<input type="hidden" id="hdnLossValues" name="hdnLossValues"/></input>
		<input type="hidden" id="hdnstats" name="hdnstats" value="${requestScope.stats}"/></input>
		<input type="hidden" id="hdndelMAMode" name="hdndelMAMode" value="${requestScope.delActivity}"></input>
		<input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}"></input>
		<input type="hidden" id="hdnorderType" name="hdnorderType" value="${requestScope.orderType}"></input>
		<input type="hidden" id="hiddenUrl" name="hiddenUrl" value="${requestScope.url}"></input>
		<input type="hidden" id="hdnStatus" name="hdnStatus" value="${requestScope.stats}" />
		<input type="hidden"  id="hdnErrppostStatus" name="hdnErrppostStatus" value="${requestScope.plmTlGenmaintenance.errppostStatus}"/></input>
		<input type="hidden"  id="hdnSpares" name="hdnSpares" value="${requestScope.plmTlGenmaintenance.isSpares}"/></input>
		<%-- <input type="hidden"  id="hdngmntStatus" name="hdngmntStatus" value="${requestScope.plmTlGenmaintenance.gmntStatus}"/></input> --%>
		    <input type="hidden" id="hdngmntStatus" name="origGmntStatusSnapshot" value="${requestScope.plmTlGenmaintenance.gmntStatus}"/>         	
		
	</div>
</div>

</form>