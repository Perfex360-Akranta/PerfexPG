<!-- <script type="text/javascript" src="js/jquery.easyui.min.js"></script> -->
  <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 --%>

 <script type="text/javascript">	

   jQuery(document).ready(function(){
	   //jQuery("#frmBDMaster select[id='cmbbdmsRelatedto']").attr('disabled',"disabled");
       initialiseForm('frmBDMaster');	    	  
       jQuery('#submitForm').val('frmBDMaster');
       var viewModeFlag = jQuery('#mode').val();
       if(jQuery('#mode').val() == 'view')
       {
           setTimeout(function(){
               disableForm('frmBDMaster');
               jQuery('#forNewpp').css('display','none');
           }, 1000);
       }
       // set the id of form to submit  
       /*Added By Roopa---*/
       //jQuery( "#tabbrkDown > ul li:nth-child(4)" ).css("display","none");
       //jQuery('input:checkbox[name=chkIssparesN]').attr('checked',true);
 	   var sapNo=jQuery("#txtbdmsSapNo").val();	
	   sapNo=sapNo.replace("BDM", "SAP");	 
	   jQuery("#txtbdmsSapNo").val(sapNo);
	   /*------*/
	   var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
	   var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
	   var spareId=jQuery('#cmbbdmsSpareid').combobox('getValue');
	   var factsp = jQuery("#frmBDMaster input[id='factory']").val();
	   
	   //var  phenId =  getFieldValue("cmbbdmsFinalphenomena").combobox('getValue');
 	 //  fillComboBox("frmBDMaster","txtbdmsRepeatedbdno","combo_repeatedbdno.Bbrdn");
 	 jQuery('#cmbbdanCostcentre').combobox('disable');
 	   fillComboBox("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter" );
 	  
 	   fillComboBox("frmBDMaster","cmbbdanFailuretype","combo_failtype.Bbrdn");
 	   fillComboBox("frmBDMaster","cmbbdanClassificationid","combo_bdclfcn.Bbrdn");
 	   //fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?assmId="+assmId+"&mchId="+mchId);
 	  					readOnlyFields('cmbbdmsShiftid');	       	

 	   fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn");
 	  // fillComboBox("frmBDMaster","cbobdmsActivity","combo_activity.Bbrdn");
 	   fillComboBox("frmBDMaster","cmbbdmsBookedtrade","combo_trade.Bbrdn" );
 	   fillComboBox("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?~&machineId=cmbbdmsMachineid~");
 	  var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
 	 var assemId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
 	   //fillComboBox("frmBDMaster","cmbbdmsSubassemblyid","combo_subassmbly.Bbrdn?~&assmId=cmbbdmsAssemblyid~&machineId=cmbbdmsMachineid~");
 	   fillComboBox("frmBDMaster","cmbbdmsSubassemblyid","combo_subassmbly.Bbrdn?machineId="+mchId+"&assmId="+assemId);
 	   
 	   fillComboBox("frmBDMaster","cmbbdmsSpareid","combo_spare.Bbrdn?~&factId=factsp~");

  	   fillComboBox("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?~&phenId=cmbbdmsFinalphenomena~&assmId=cmbbdmsAssemblyid~");
 	   //fillComboBox("frmBDMaster","cmbbdmsProcessId","process.commonFilter" );

 	    
 	   setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsBookedby","employee.commonFilter");},1250);
 	   setTimeout(function() {fillComboBox("frmBDMaster","cmbbdanCompletedby","employee.commonFilter"); },5250);
 	   
 	   fillComboBox("frmBDMaster","cmbbdanCompletedby","employee.commonFilter"); 
 	   
 	   
 	   
 	   
 	   
 	 
	  //fillComboBox("frmBDMaster","cmbbdanCompletedby","employee.commonFilter?c=Y");
 	//9999  fillComboBox("frmBDMaster","cmbbdmsShiftid","combo_shift.Bbrdn" ); 
// 	   setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsStandbyequipment","machineCombo.commonFilter?~&machineNotToShown=cmbbdmsMachineid~");},1250);

      jQuery("#btnSapCheck").click(function(){
          var plantName = jQuery('#linfrmBDMasterFactory > u > b').html();
          var fact = jQuery("#frmBDMaster input[id='factory']").val();
		  if( plantName == null){
			  alert("Select Factory");
			  return ;
		  }	  	
		  var str = "plantName=" + escape(plantName.trim()) +"&factId="+fact;

    	  LoadPopUp("divSAPSpares","Sapstackinformation_input.sapinfo?"+str, true,"76%","95%","-10px","2%", "","Spares");
       }); 
 	  /* jQuery("#cmbbdmsFinalphenomena").combobox({onRequest:function( ){
 	   	 	 //var  bdmsMould =  getFieldValue("cmbbdmsMould");
 	   	 	  var assmId = "";
			//var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
			//if(jQuery('#chkIsAssmWise').is(':checked') == true)
				//assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
		    var  retsttr = "";// "assmId="+assmId+"&mchId="+mchId ;		
 			return retsttr ;//""mldId="+bdmsMould ;
 	  	}}); *///comented by priyanka 
 	  	jQuery("#cmbbdmsFinalphenomena").combobox({onRequest:function( ){
	   	 	 //var  bdmsMould =  getFieldValue("cmbbdmsMould");
	   	 	  //var assmId = "";
			//var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
			//if(jQuery('#chkIsAssmWise').is(':checked') == true)
				//assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
		    //var  retsttr = "";// "assmId="+assmId+"&mchId="+mchId ;	
		     var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
	  		 var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
	  		 return "mchId=" + mchId + "&assmId=" + assmId;
	  		 //var retsttr = "assmId="+assmId+"&mchId="+mchId;
			 //return retsttr ;//""mldId="+bdmsMould ;
	  	}});
 	  	jQuery('#chbReporteddate').click(function() {
 	  		if(jQuery('#mode').val() == 'view') return;

 	  		if(jQuery(this).is(':checked') == true)
 	  		{
 	  			enableFields('dtebdmsReporteddate');
 	  			enableFields('spnbdmsReportedtime');
 	  		}
 	  		else
 	  		{
 	  			readOnlyFields('dtebdmsReporteddate');
 	  			readOnlyFields('spnbdmsReportedtime');
 	  		}
 	  	});
 	 jQuery("#cmbbdmsFinalcause").combobox({onRequest:function( ){
	   	 	var  phenId =  getFieldValue("cmbbdmsFinalphenomena"); 
	   	     var  assmId =  getFieldValue("cmbbdmsAssemblyid");
	   	     //priyanka 
	   	     if(jQuery('#chkOtherCause').is(':checked') == true)
	          //assmId = "";
	   	  		return "assmId=" + assmId;
	   	  		//end 
			return "phenId="+phenId+"&assmId="+assmId ;
	  	}});
 	jQuery("#cmbbdmsAssemblyid").combobox({onRequest:function( ){
   	 	 //var  bdmsMould =  getFieldValue("cmbbdmsMould");
   	 	  
		var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
		if(jQuery('#chkOtherAssm').is(':checked') == true)
			return "&machineNotToShown=" + mchId;
	    return "&machineId=" + mchId;
	    // end
  	}});
  	
 	   setTimeout(function() {
 	   
 		  if(jQuery('#mode').val() == 'view') return;	   
 		   if(jQuery("#chkbdmsIsstandby").is(':checked') == true)
		{ 
			enableFields("cmbbdmsStandbyequipment");
			enableFields("txtbdmsBreakdowntime");
			 
		}
		else{
			readOnlyFields("cmbbdmsStandbyequipment");
			readOnlyFields("txtbdmsBreakdowntime");
			jQuery("#txtbdmsBreakdowntime").val(jQuery("#txtBDDownTime").val());
		}
 	 },1250);

 	 
 	  /* setTimeout(function() { fillComboBox("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter" );},1250);     
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdanCostcentre","costCenter.commonFilter" );},1250);       
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsMould","combo_mould.Bbrdn" );},1250);
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsShiftid","combo_shift.Bbrdn" );},1250);
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsBookedby","employee.commonFilter");},1250);
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdanCompletedby","employee.commonFilter"); },1250);
       setTimeout(function() { fillComboBox("frmBDMaster","cmbbdmsSubassemblyid","subassemblyCombo.commonFilter");},1250); 
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsBookedtrade","combo_trade.Bbrdn" );},1250);        
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsSpareid","spareCombo.commonFilter");},1250);  
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsAlarmdescription","combo_alarm.Bbrdn");},1250);   
       setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsStandbyequipment","machineCombo.commonFilter");},1250);   
       */
       var emrNo = jQuery('#cmbbdmsKeyid').combobox('getValue');
      
	   if(emrNo != null && emrNo.substring(0,1)=='U')
		   jQuery('#activityLbl').html('Repeat Activity');
 	   setTimeout(function() {readOnlyFields('txtbdmsRepeatedbdno');},1250);         
 	  jQuery('#cmbbdmsKeyid').combobox('setValue',emrNo);
       formatDateBox('dtebdmsReporteddate','dd-MMM-yyyy');
       //formatDateBox('dtebdmsReceiveddate','dd-MMM-yyyy');
       formatDateBox('dtebdmsWostarttime','dd-MMM-yyyy');
       formatDateBox('dtebdmsWoendtime','dd-MMM-yyyy');
       formatDateBox('dtebdmsProdaccepdate','dd-MMM-yyyy');
              
       formatDateBox('dtebdmsCompleteddate','dd-MMM-yyyy');
       numericTextBox('txtbdmsBreaktime'); 
       //alert(jQuery("#relatedToCMB").val());
//	   if( jQuery("#relatedToCMB").val() == "MCH")	       
	   setTimeout(function() {readOnlyFields('cmbbdmsMould');},1250); 
	   readOnlyFields('txabdanRemarks');				
	   readOnlyFields('dtebdmsProdaccepdate');
	   readOnlyFields('spnbdmsProdacceptime'); 
	   readOnlyFields('chbbdmsWoprodaccepflag');
	   readOnlyFields('txtbdanFinalaction');
	   jQuery("#txtbdanFinalaction").css('background-color', '#D1E2FD');
	   //readOnlyFields('cmbbdmsSubassemblyid');
	   jQuery('#cmbbdmsKeyid').combobox('setValue',emrNo);
	    var completedBY = jQuery('#cmbbdanCompletedby').combobox('getValue');
	   
	 /*  if(completedBY !=null && completedBY != '' && completedBY != ' ')
		  	readOnlyFields('cmbbdanCompletedby');
		if(jQuery("#dtebdmsCompleteddate").val().length == 0)
		  		readOnlyFields("chkChkCompletedBy"); cOMMENTED ON 18_09_15*/ 
	//   			readOnlyFields('chkChkCompletedBy');
	   			//enableDisableCompletdFields(false);
	   //jQuery('#txabdmsProblemdescription').attr('readonly','readonly');
	   //jQuery("#txabdmsProblemdescription").css('background-color', '#ece9d8');
	   //jQuery("#cbobdanProblemseverity option").css('width','85px');
	  
      // alert(mchId.charAt(1));
      
	   if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val() !=''&& jQuery("#relatedToCMB").val() !=' ')
	   {		  
		   jQuery("#cmbbdmsRelatedto").combobox('setValue',jQuery("#relatedToCMB").val());		  
	   }				
	   
	   if(jQuery("#sparesFlag").val() != null)
			sparesCheckSel(jQuery("#sparesFlag").val());	
	
	   /*if(jQuery("#phenName").val() == 'ND')
	   {
		   jQuery('#forNewpp').css('display','block');//jQuery('#btnNewPP').css('display','block');
		   alert("This Breakdown has Undefined Phenomena\n Please define PP");
	   }						
	  */
	   if( jQuery("#cmbbdmsKeyid").combobox('getValue') == null || jQuery("#cmbbdmsKeyid").combobox('getValue') =='')
       {
	        bdSaveMode();		   
       }
	   else
	   {
			 bdUpdateMode();
	   }
	    setDatenTimeInCheckBoxSelect('chbbdmsWostartflag','dtebdmsWostarttime','spnbdmsWostart','dtebdmsReporteddate','spnbdmsReportedtime');
	    setDatenTimeInCheckBoxSelect('chbbdmsWoendflag','dtebdmsWoendtime','spnbdmsWoend','dtebdmsWostarttime','spnbdmsWostart');
	    readOnlyFields('cmbbdmsKeyid');
   	    if( jQuery("#chbbdmsWoendflag").is(":checked") == true &&  jQuery("#chbbdmsWoprodaccepflag").is(":checked") == false){
   	    	enableFields("chbbdmsWoprodaccepflag");
   	    }	

   	    //we are going to make changes here..
	    var factId = jQuery("#frmBDMaster input[id='factory']").val();
	    var sectionId = jQuery("#frmBDMaster input[id='section']").val();
	    var cellId = jQuery("#frmBDMaster input[id='cell']").val();
	    var machId = jQuery("#frmBDMaster input[id='machine']").val();
	    var flid = jQuery("#frmBDMaster input[id='flid']").val();
	    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					

		 loadFunctionalLocation("bdmsfunLocation","functionalLoc.Bbrdn","bdmsfunLocationValues","frmBDMaster",dataStr);
	    var fromTime = jQuery('#spnbdmsReportedtime').spinner('getValue');							
	    var dataString = '?q=2&factId='+factId+'&sectId='+sectionId;
		  dataString += '&cellId='+cellId+'&fromTime='+fromTime+"&flid="+flid;	
		  						
	   processAjaxCalls('txt_shift.Bbrdn',dataString,'getShift','getShiftErr');
	  

	   jQuery('#frmBDMaster .easyui-text').css('text-transform', 'uppercase');
       jQuery('#frmBDMaster textarea').css('text-transform', 'uppercase');			
//       spinnerKeyPress('spnbdmsReceivedtime');
       spinnerKeyPress('spnbdmsWostart');
       spinnerKeyPress('spnbdmsWoend');
       spinnerKeyPress('spnbdmsProdacceptime');
       spinnerKeyPress('spnbdmsReportedtime');	
       spinnerKeyPress('spnbdmsCompletedtime');		
	   //spinnerChange('spnbdmsReceivedtime','receivedTimeEvt');
       spinnerChange('spnbdmsWostart','woStartEvt');
       spinnerChange('spnbdmsWoend','woEndEvt');
       spinnerChange('spnbdmsProdacceptime','prodAcceptimeEvt');
       spinnerChange('spnbdmsReportedtime','setShiftEvt');
       spinnerChange('spnbdmsCompletedtime','CompletedBy');
       //spinnerUp('spnbdmsReceivedtime','receivedTimeEvt');
       spinnerUp('spnbdmsWostart','woStartEvt');
       spinnerUp('spnbdmsWoend','woEndEvt');
       spinnerUp('spnbdmsProdacceptime','prodAcceptimeEvt');
       spinnerUp('spnbdmsReportedtime','setShiftEvt');
       spinnerUp('spnbdmsCompletedtime','CompletedBy');
       //spinnerDown('spnbdmsReceivedtime','receivedTimeEvt');
       spinnerDown('spnbdmsWostart','woStartEvt');
       spinnerDown('spnbdmsWoend','woEndEvt');
       spinnerDown('spnbdmsProdacceptime','prodAcceptimeEvt');
       spinnerDown('spnbdmsReportedtime','setShiftEvt');
       spinnerDown('spnbdmsCompletedtime','CompletedBy');
		jQuery("#btnbdnSumitSAP").click(function(){
			var woId = jQuery('#hdnwoid').val();
			processAjaxCalls('submitToSAP_input.work',"&woKeyId="+woId+"&transCode=MaintenanceOrder",'getSAPbdMsg','getSAPbdErr');
		});       	
		
       //jQuery('#tabbrkDown .tabs-panels').css('height','100%');			
	   jQuery("#txtbdmsBreaktime").change(function(){ 															
		  if(jQuery("#txtbdmsBreaktime").val() != ''  && jQuery("#txtbdmsBreaktime").val() != null )
		  {
			  if(jQuery("#txtbdmsDowntime").val() != ''  && jQuery("#txtbdmsDowntime").val() != null )
			  {
				  var downTime = parseInt(jQuery("#txtbdmsDowntime").val()) - parseInt(jQuery("#txtbdmsBreaktime").val());
				  if(parseInt(downTime) < 0)
				  {
					  jQuery("#txtbdmsBreaktime").val('');
					  jQuery("#txtbdmsDowntime").trigger("change");
				  }
				  else
				  {
					displayText('txtbdmsDowntime',downTime);
					jQuery("#txtbdmsDowntime").trigger("change");
				  }
			  }
		  }	
		  
	   });
							
		jQuery("#btnPhenomena").click(function(){																
			var combinedId = jQuery("#frmBDMaster input[id='elementId']").val(); //jQuery("#factory").val()+"-"+jQuery("#section").val()+"-"+jQuery("#cell").val()+"-"+jQuery("#machine").val();//jQuery("#cmbbdmsFactoryid").combobox('getValue')+"-"+jQuery("#cmbbdmsSectionid").combobox('getValue')+"-"+jQuery("#cmbbdmsCellid").combobox('getValue')+"-"+jQuery("#cmbbdmsMachineid").combobox('getValue');
			var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
			var phenId = jQuery("#cmbbdmsFinalphenomena").combobox('getValue');			
			var causeId = jQuery("#cmbbdmsFinalcause").combobox('getValue');
			
			//var dataString='&combinedId='+combinedId+'&assmId='+assmId+'&phenId='+phenId+'&causeId='+causeId ;
			var dataString = '?combinedId='+combinedId+'&assmId='+assmId+'&phenId='+phenId+'&causeId='+causeId;
			
			//if (assmId != "")	
				if (assmId != null && assmId != '' && assmId != ' ')
			{
				if(jQuery("#cmbbdmsFinalphenomena").combobox('getText') != 'NOT DEFINED')													
					//subFormPop("PhenCause_input.pcl",40,120,450,950,"Phenomena and Cause",dataString);
					LoadPopUp("divPhenLink", 'PhenCause_input.pcl'+dataString, true, "90%", "500px", "60px", "20px", null, 'Breakdown Analysis - Phenomena and Cause', false, true, true);
				else
					alert("This is Undefined Phenomena");
			}
			else
				alert("Select Assembly");
		});
		
		jQuery("#btnEqpLink").click(function(){	
			var url = 	'equipment_input.eqp?q=2&closeOnSave=true';
			var keyId= jQuery('#cmbbdmsMachineid').combobox('getValue');
			if(keyId != null && keyId != ' ' && keyId != '')
				url += '&keyId='+keyId+'&lockFields=KEYID,SECTION,FACTORY,CELL';
				
			openMasterForm(url,frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});
		
		jQuery("#btnSpareLink").click(function(){	
			jQuery('#hdnlinkMode').val('Spare');	
			var url = 	'SparesMaster_input.sprmst?q=2&&closeOnSave=true';
			var keyId= jQuery('#cmbbdmsSpareid').combobox('getValue');
			if(keyId != null && keyId != ' ' && keyId != '')
				url += '&keyId='+keyId+'&lockFields=KEYID';					
			openMasterForm(url,frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});
		jQuery("#btnFailureTypeLink").click(function(){		
			jQuery('#hdnlinkMode').val('FailType');		
			openMasterForm('FailureTypeMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});
		jQuery("#btnClassifcnLink").click(function(){	
			jQuery('#hdnlinkMode').val('Classifcn');			
			openMasterForm('BDClassifcnMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});
		/* jQuery("#btnAssmLink").click(function(){	
			jQuery('#hdnlinkMode').val('Assm');	
			var ds = '?q=2&closeOnSave=true';
			var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
			if(mchId != null && mchId != ' ' && mchId != '')
				//priyankads += '&machId='+mchId.replace('_','/')+'&'+frmMode.lockFields+'=&refreshFlag=Y';//AssmMst_link.gnms
				ds += '&machId=' + mchId.replace('_','/') + '&' + frmMode.lockFields + '=';
			//openMasterForm('assembly_input.asb'+ds,frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
			//var height = jQuery('.layout-panel-center').css('height');
			//var top = jQuery('.layout-panel-center').css('top');
			LoadPopUp("divAsmblyLink", 'assembly_input.asb'+ds, true, "90%", "500px", "60px", "20px", null, 'Breakdown Analysis - Assembly Link', false, true, true);
			//priyankaLoadPopUp("divAsmblyLink", 'assembly_input.asb'+ds, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Assembly Link',false, true,true);
		}); */
		jQuery("#btnAssmLink").click(function(){	
		    jQuery('#hdnlinkMode').val('Assm');
		    
		    // Check if equipment is selected first
		    var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
		    if(mchId == null || mchId == '' || mchId == ' '){
		        alert('Select Equipment');
		        return;
		    }
		    
		    var ds = '?q=2&closeOnSave=true';
		    ds += '&machId=' + mchId.replace('_','/') + '&' + frmMode.lockFields + '=';
		    LoadPopUp("divAsmblyLink", 'assembly_input.asb'+ds, true, "90%", "500px", "60px", "20px", null, 'Breakdown Analysis - Assembly Link', false, true, true);
		});
//priyanka
jQuery("#btnMaintSectionLink").click(function(){
			jQuery('#hdnlinkMode').val('Trdm');
			var ds = '?q=2&closeOnSave=true';
			window.popup_OnDeleteForm = function(){
		        jQuery("#ImgDelete").trigger("click");
		    };
			//LoadPopUp("divMaintSectionLink", 'trade_input.pcl'+ds, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Maint. Section Link',false, true,true);
			openMasterForm('loadmst_grid.gnms?q=2&menuCaption=MaintenanceSection&menuName=MNUMASTRADE&isMMC=Y&loadFormArg=MNUMASTRADE%3F',frmMode.create,'frmBDMaster','Breakdown Analysis - Maint. Section Link','mstFrm');
		});
		/* jQuery("#btnSubAssmLink").click(function(){	
			jQuery('#hdnlinkMode').val('Assm');	
			var ds = '?q=2&closeOnSave=true';
			var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
			var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
			if(mchId != null && mchId != ' ' && mchId != ''){
				if(assmId != null && assmId != ' ' && assmId != '')
					ds += '&machId=' + mchId.replace('_','/') + '&' + frmMode.lockFields + '='+ '&assmId=' + assmId.replace('_','/');
				//priyankads += '&machId='+mchId.replace('_','/')+'&'+frmMode.lockFields+'=&refreshFlag=Y&assmId='+assmId.replace('_','/')+'';//AssmMst_link.gnms

			}//openMasterForm('assembly_input.asb'+ds,frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
			//var height = jQuery('.layout-panel-center').css('height');
			//var top = jQuery('.layout-panel-center').css('top');
			LoadPopUp("divSubAsmblyLink", 'subassembly_input.subasm'+ds, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Sub Assembly Link',false, true,true);
			//priyankaLoadPopUp("divSubAsmblyLink", 'subassembly_input.subasm'+ds, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Sub Assembly Link',false, true,true);
		}); */
		//PRIYANKA 
		jQuery("#btnSubAssmLink").click(function(){	
    jQuery('#hdnlinkMode').val('Assm');

    var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
    var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');

    // Check Equipment first
    if(mchId == null || mchId == '' || mchId == ' '){
        alert('Select Equipment');
        return;
    }
    // Check Assembly next
    if(assmId == null || assmId == '' || assmId == ' '){
        alert('Select Assembly');
        return;
    }

    var ds = '?q=2&closeOnSave=true';
    ds += '&machId=' + mchId.replace('_','/') + '&' + frmMode.lockFields + '=' + '&assmId=' + assmId.replace('_','/');
    LoadPopUp("divSubAsmblyLink", 'subassembly_input.subasm'+ds, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Sub Assembly Link',false, true,true);
});
		
		jQuery("#btnAlarmLink").click(function(){
			jQuery('#hdnlinkMode').val('Alarm');			
			openMasterForm('AlarmMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});	
		jQuery("#btnCauseLink").click(function(){
			 var elementId = jQuery("#frmBDMaster input[id='elementId']").val();
			var combinedId = jQuery("#factory").val()+"-"+jQuery("#section").val()+"-"+jQuery("#cell").val()+"-"+jQuery("#machine").val();
			var id=jQuery("#cmbbdmsMachineid").val()+"-";
			//var combinedId =jQuery("#cmbbdmsFactoryid").combobox('getValue')+"-"+jQuery("#cmbbdmsSectionid").combobox('getValue')+"-"+jQuery("#cmbbdmsCellid").combobox('getValue')+"-"+jQuery("#cmbbdmsMachineid").combobox('getValue');
			var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
			var phenId = jQuery("#cmbbdmsFinalphenomena").combobox('getValue');			
			var causeId = jQuery("#cmbbdmsFinalcause").combobox('getValue');
			var dataString = '?combinedId='+elementId+'&assmId='+assmId+'&phenId='+phenId+'&causeId='+causeId+'&linkBD=Cause';
			//var dataString='&combinedId='+elementId+'&assmId='+assmId+'&phenId='+phenId+'&causeId='+causeId+'&linkBD=Cause';
			//priyankaif (phenId != "")	
			if (phenId != null && phenId != '' && phenId != ' ')
				//priyankasubFormPop("PhenCause_input.pcl",40,120,450,950,"Phenomena and Cause",dataString);
				LoadPopUp("divCauseLink","cause_input.pcl" + dataString,true,"90%","500px","60px","20px",null,"Phenomena and Cause",false,true,true);
			else
				alert("Select Phenomena");
			
			//jQuery('#hdnlinkMode').val('Cause');			
			//openMasterForm('CauseMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});		

		/* jQuery("#btnMultipleResp").click(function(){
			 var emrNo = jQuery("#cmbbdmsKeyid").combobox('getValue');
			 var flid = jQuery("#frmBDMaster input[id='flid']").val();	
			 var machId = jQuery("#frmBDMaster input[id='machine']").val();
			var dataString='&emrNo='+emrNo+'&machId='+machId;
			var already=jQuery("#hdnalready").val();
			if (machId != "")		
				//subFormPop("BreakdownMultipleResp_input.Bbrdn",40,80,530,550,"Multiple Responsibility",dataString);
				if(already == machId)
					jQuery("#loadMultiple").show();
				else
					{
						LoadPopUp("loadMultiple", "BreakdownMultipleResp_input.Bbrdn?"+dataString, true,"550px","600px","-1%","7%", "bdMultiResult_successCallBack","Multiple Responsibilty");
						jQuery("#hdnalready").val(machId);
					}
			else
				alert("Select Equipment");
			
			//jQuery('#hdnlinkMode').val('Cause');			
			//openMasterForm('CauseMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
		});	 */
		//priyanka 
		
		
		jQuery("#btnMultipleResp").click(function () {

		    var emrNo = jQuery("#cmbbdmsKeyid").combobox("getValue");
		    var machId = jQuery("#frmBDMaster input[id='machine']").val();

		    if (machId == null || jQuery.trim(machId) === "") {
		        alert("Select Equipment");
		        return false;
		    }

		    var dataString =
		        "emrNo=" + encodeURIComponent(emrNo) +
		        "&machId=" + encodeURIComponent(machId);

		    LoadPopUp(
		        "loadMultiple",
		        "BreakdownMultipleResp_input.Bbrdn?" + dataString,
		        true,
		        "550px",
		        "600px",
		        "-1%",
		        "7%",
		        "bdMultiResult_successCallBack",
		        "Multiple Responsibility"
		    );

		    return false;
		});
		
					
		jQuery("#btnNewPP").click(function(){			
			var dataString = '?q=2&unitId='+jQuery("#factory").val()+'&sectionId='+jQuery("#cell").val()+'&subUnitId='+jQuery("#sectionid").val();
				dataString += '&eqpId='+jQuery("#cmbbdmsMachineid").combobox('getValue')+'&AssmId='+jQuery("#cmbbdmsAssemblyid").combobox('getValue');
				dataString += '&woStart='+jQuery("#spnbdmsWostart").spinner('getValue')+'&woEnd='+jQuery("#spnbdmsWoend").spinner('getValue');
				dataString += '&bookedTime='+jQuery("#downTimeMins").val()+'&bdNo='+jQuery("#cmbbdmsKeyid").combobox('getValue')+'&bdDate='+jQuery("#txtbdmsEntrydate").val();
			saveForm('frmBDMaster','Breakdown_unphn.Bbrdn');
		});						//navigateToNextForm('undefined_input.upm'+dataString,'Undefined Phenomena');
										
		jQuery("#btnYYLink").click(function(){	
			if(jQuery('#txtbdanFinalaction').val() != null)
			{
				if(jQuery("#cmbbdmsFinalphenomena").combobox('getText') != 'NOT DEFINED')													
					saveForm('frmBDMaster','Breakdown_yy.Bbrdn?q=2&finalAction='+jQuery('#txtbdanFinalaction').val());	
				else
					alert("This is Undefined Phenomena");	
			}	
			else
				alert("Enter Final Action");								
		});
		var status =jQuery('#hdnBdmsStatus').val();
		if(status=="C"){
			displayText('txtbdanErppoststatus','COMPLETED');	
			jQuery('#chkbdSubmittoSap').prop('disabled',false);
			}
		else
		 	jQuery('#chkbdSubmittoSap').prop('disabled',true);
		/*jQuery("#btnInsert").click(function(){
								
			if(jQuery("#txawcmlCommunicationtext").val() == null || jQuery("#txawcmlCommunicationtext").val() == '')
				showValidationErrorMsg('txawcmlCommunicationtext','Enter Communication Text');
			else
			{
				clearValidationErrorMsg('txawcmlCommunicationtext');
				var dataString = '?q=2&comTxt='+jQuery("#txawcmlCommunicationtext").val()+'&comBy='+jQuery("#cmbbdmsBookedby").combobox('getValue')+'&bdNo='+jQuery("#cmbbdmsKeyid").combobox('getValue');
				
				processAjaxCalls('comm_save.Bbrdn',dataString,'getCom','getComErr');
			}
		});*/
		
		jQuery("#btnComm").click(function(){								
				navigateToNextForm('commtxt_view.Bbrdn?q=2&bdId='+jQuery('#cmbbdmsKeyid').combobox('getValue')+'&commFlag=yes','Communication');	
		});

		/* jQuery('#chkIssparesY').click(function() {
				jQuery('input:checkbox[name=chkIssparesY]').attr('checked',true);
				//jQuery("#lblspareid").addClass("mandatory-lbl");
				
				jQuery('input:checkbox[name=chkIssparesN]').attr('checked',false);
				jQuery('input:checkbox[name=chkIssparesW]').attr('checked',false);
		});
		jQuery('#chkIssparesN').click(function() {
				jQuery("#lblspareid").removeClass("mandatory-lbl");
				jQuery('input:checkbox[name=chkIssparesN]').attr('checked',true);
				jQuery('input:checkbox[name=chkIssparesY]').attr('checked',false);
				jQuery('input:checkbox[name=chkIssparesW]').attr('checked',false);
				jQuery('#cmbbdmsSpareid').combobox('clear');
		});
		jQuery('#chkIssparesW').click(function() {
			jQuery("#lblspareid").removeClass("mandatory-lbl");
			jQuery('input:checkbox[name=chkIssparesW]').attr('checked',true);
			jQuery('input:checkbox[name=chkIssparesY]').attr('checked',false);
			jQuery('input:checkbox[name=chkIssparesN]').attr('checked',false);
		});
 */
 jQuery('#chkIssparesY').click(function() {
	    jQuery('input:checkbox[name=chkIssparesY]').prop("checked","checked");
	    jQuery('input:checkbox[name=chkIssparesN]').prop("checked",false);
	    jQuery('input:checkbox[name=chkIssparesW]').prop("checked",false);
	});
	jQuery('#chkIssparesN').click(function() {
	    jQuery("#lblspareid").removeClass("mandatory-lbl");
	    jQuery('input:checkbox[name=chkIssparesN]').prop("checked","checked");
	    jQuery('input:checkbox[name=chkIssparesY]').prop("checked",false);
	    jQuery('input:checkbox[name=chkIssparesW]').prop("checked",false);
	    jQuery('#cmbbdmsSpareid').combobox('clear');
	});
	jQuery('#chkIssparesW').click(function() {
	    jQuery("#lblspareid").removeClass("mandatory-lbl");
	    jQuery('input:checkbox[name=chkIssparesW]').prop("checked","checked");
	    jQuery('input:checkbox[name=chkIssparesY]').prop("checked",false);
	    jQuery('input:checkbox[name=chkIssparesN]').prop("checked",false);
	});
		/*jQuery('#chbbdmsWoendflag').click(function() {
			if(jQuery('#chbbdmsWoendflag').is(':checked') == true){
				
				enableFields("chbbdmsWoprodaccepflag");
				enableFields("chkChkCompletedBy");
			}	
			else
			{
				readOnlyFields("chkChkCompletedBy");
				readOnlyFields("chbbdmsWoprodaccepflag");
				readOnlyFields("dtebdmsProdaccepdate");
				readOnlyFields("spnbdmsProdacceptime");
				jQuery("#chkChkCompletedBy").attr("checked",false);
				jQuery("#chbbdmsWoprodaccepflag").attr("checked",false);
				readOnlyFields('chkChkCompletedBy');
				displayText('dtebdmsProdaccepdate',"");
				displayText('spnbdmsProdacceptime',"");
				enableDisableCompletdFields(false); 
				jQuery('#txtbdmsActualworktime').val('');
				jQuery('#txtbdmsDowntime').val('');
				jQuery("#txtbdmsDowntime").trigger("change");
			}
				
		});*/
	
		
		/* jQuery('#btnEstimation').click(function() {   
			alert(1234);
			if(jQuery('#chbbdmsWostartflag').is(':checked') == true && jQuery('#chbbdmsWoendflag').is(':checked') == true)
				{     
					if( jQuery("#txtbdanFinalaction").val() == null &&  jQuery("#txtbdanFinalaction").val() == '')  	    				
        			 	saveForm('frmBDMaster','costinfo.Bbrdn?q=2&repType=Estimate'); 
				}        	        							
        });  */
        jQuery('#btnEstimation').click(function() {
            console.log('--- btnEstimation clicked ---');

            var woStartChecked = jQuery('#chbbdmsWostartflag').is(':checked');
            var woEndChecked    = jQuery('#chbbdmsWoendflag').is(':checked');

            console.log('Work Start checked:', woStartChecked);
            console.log('Work End checked:', woEndChecked);

            if (woStartChecked == true && woEndChecked == true) {
                console.log('Calling saveForm for Estimate');
                saveForm('frmBDMaster', 'costinfo.Bbrdn?q=2&repType=Estimate');
            } else {
                console.log('Blocked: Work Start/End not both checked');
                alert('Select Work Start and Work End');
            }
        }); 
        
        
        
        /* jQuery('#btnEstimation').click(function() {   
            if(jQuery('#chbbdmsWostartflag').is(':checked') == true && jQuery('#chbbdmsWoendflag').is(':checked') == true)
            {     
                saveForm('frmBDMaster','costinfo.Bbrdn?q=2&repType=Estimate'); 
            }
            else
            {
                alert('Select Work Start and Work End');
            }        	        							
        }); */
		/* jQuery('#btnActual').click(function() {
        		saveForm('frmBDMaster','costSummary_input.crt?q=2&repType=Actual');  
        		alert(123);
        });	 */	 
        //mano
        jQuery('#btnActual').click(function() {
    saveForm('frmBDMaster', 'costinfo.Bbrdn?q=2&repType=Actual');
});
		jQuery('#btnDTRefresh').click(function() {
			showDowntime();
		});
		jQuery('#chkIsAssmWise').change(function() {
			//alert('KK '+jQuery('#chkIsAssmWise').is(':checked'));
			var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
			var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
			if(jQuery('#chkIsAssmWise').is(':checked') == true)
			{				
				if(assmId != null && assmId != '' && assmId != ' ')
				{
					jQuery('#cmbbdmsFinalphenomena').combobox('clear');
					//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+assmId+"&mchId="+mchId);
					
				}
				else
				{
					jQuery('#cmbbdmsFinalphenomena').combobox('clear');
					jQuery('#chkIsAssmWise').attr('checked',false);
					alert('Select Assembly');
					//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&mchId="+mchId);
				}					
			}
			else
			{
				/*var dataStr = "?q=2&mchId="+mchId;
				jQuery('#cmbbdmsFinalphenomena').combobox('clear');
				if(jQuery('#cmbbdmsRelatedto').combobox('getValue') == "MLD")
				{
					var mldId = jQuery('#cmbbdmsMould').combobox('getValue');
					if(mldId != null && mldId != '' && mldId != ' ')
						dataStr = "?q=2&mldId="+mldId;
				}*/
				//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?mchId="+mchId);
			}
		});
		if(!jQuery('#chkbdmsProductionstop').is(':checked') )
		{
			 	 
		}
		jQuery("#tabbrkDown").tabs({ onSelect:function(title){     					
		//jQuery(".tabs-title").bind("click", function(event){
				//if(jQuery(this).text() == "Downtime Breakup")
				var mode=jQuery('#mode').val();
				if(title == "Downtime Breakup" )
				{
				   if(jQuery('#downTime').val() == null || jQuery('#downTime').val() =='' || jQuery('#downTime').val() =='0')
				   { 
					   showDowntime();
				   }
			    }
	  			//if(jQuery(this).text() == "Communication")
	  			
	  			else if(title == "Communication" && jQuery('#loadComText').html() == ""  )
				{
	  				var erpState=jQuery('#hdnErpStatus').val();
	  				if(erpState=="C"){
						alert("Not Allowed");
		  				}
	  				else
						LoadForm("loadComText","prevloadComText","comm_Text.Bbrdn?mode=bd&formMode="+mode,"","communicationText","communicationTextErr");
					   //processGridnew('comm_view.Bbrdn','?q=2&bdId='+jQuery('#cmbbdmsKeyid').combobox('getValue')+'&commFlag=no',"CommnGrid","CommnPager","","","","","");
				}
				//if(jQuery(this).text() == "WHY WHY Analysis")
				else if(title == "WHY WHY Analysis" )
				{
					var bdKey = jQuery('#cmbbdmsKeyid').combobox('getValue');
					//alert( jQuery('#cmbbdmsFactoryid').combobox('getValue'));
					LoadForm("loadYY","prevloadYY","why_why.Bbrdn?mode=bd&bdKey="+bdKey+'&formMode='+mode,"",null,"YYErr");
				   	   //processGridnew('yy_view.Bbrdn','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"yyGrid","yyPager","","","","yyComplete","");
					   // processGridnew('rootcause_view.Bbrdn','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"rootCauseGrid","rootCausePager","","","","RootCauseComplete","");
				}
				//if(jQuery(this).text() == "Cost Info")
				else if(title == "Cost Info" )
				{
				//	if(jQuery('#chbbdmsWostartflag').is(':checked') == false && jQuery('#chbbdmsWoendflag').is(':checked') == false)
					//	jQuery('#estBtnShow').css('display','none');
					if(mode != null && mode != '' && mode != ' ')
					{
						if(mode=='view')
						{
						 disableUIButton('btnEstimation');	
						 disableUIButton('btnActual');	
						}
					}
					var woId=jQuery('#cmbbdmsKeyid').val();										
					processGridnew('costSummary_view.crt',"?q=2&woId="+woId,"costSumryGrid","costSumryPager","","","","costGridLoad","costGridError");
					  // processGridnew('empCostActual_view.crt',"?q=2&formName=empCost&woId="+ woId,"tblEmpCostActGrid","empCostActPager","","tblEmpCostActGrid_dblClick","","costGridLoad","");
				}
				else if(title == "SAP Information")
				{
					var erpState=jQuery('#hdnErpStatus').val();
					var bdKeyid=jQuery('#cmbbdmsKeyid').val();					
					var woId = jQuery('#hdnwoid').val();
					if(jQuery('#chkIssparesY').is(':checked') == true){
						 var isSpres="Y";
					}
					else {
						var isSpres="N";
					}
					var bdkeyId=jQuery('#cmbbdmsKeyid').val();
					var costCenter=jQuery('#cmbbdanCostcentre').combobox('getValue');
					
					var machine =jQuery('#cmbbdmsMachineid').combobox('getText');
					//alert (machine);
					var priortiy = jQuery('#hdnPriority').val();
					var flid = jQuery("#frmBDMaster input[id='flid']").val();
					var section = jQuery('#linfrmBDMasterSection > u > b').html();
					var cell = jQuery('#linfrmBDMasterCell > u > b').html();
					
					var mode = "";
					var yyno=jQuery('#txtbdmsWno').val();
					//alert(yyno+'----Wo number');
					
					var bdkeyId=jQuery('#cmbbdmsKeyid').combobox('getValue');
					//saveForm('frmBDMaster','Breakdown_save.Bbrdn');
					if(bdkeyId == null || bdkeyId ==""){
						frmBDMaster_beforeSubmit();						
						saveForm('frmBDMaster','Breakdown_save.Bbrdn');	
																
						var woId = jQuery('#hdnwoid').val();
						if(jQuery('#chkIssparesY').is(':checked') == true){
							 var isSpres="Y";
						}
						else {
							var isSpres="N";
						}
						var bdkeyId=jQuery('#cmbbdmsKeyid').val();
						var costCenter=jQuery('#cmbbdanCostcentre').combobox('getValue');						
						var machine =jQuery('#cmbbdmsMachineid').combobox('getText');						
						var priortiy = jQuery('#hdnPriority').val();
						var flid = jQuery("#frmBDMaster input[id='flid']").val();
						var section = jQuery('#linfrmBDMasterSection > u > b').html();
						var cell = jQuery('#linfrmBDMasterCell > u > b').html();
						
						var mode = "";
						var yyno=jQuery('#txtbdmsWno').val();						
						setTimeout(function(){ 						
						var bdkeyId=jQuery('#cmbbdmsKeyid').combobox('getValue');
						//alert(bdkeyId+"BD KeyId.......");
						if(bdkeyId != null || bdkeyId !=""){
							var bdkeyId=bdkeyId;
							//alert(bdkeyId+"....in  if condition");
							LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.Bbrdn?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");
						}
						}, 6500);
						}
					
					//if(erpState=="C"){
					else if(bdkeyId != null && bdkeyId!=""){
						frmBDMaster_beforeSubmit();						
						
						LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.Bbrdn?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");						
					}
					else if(bdkeyId != null && erpState=="C"){
						alert("Not Allowed");
						
					}
				}
				
				 
				else if(title == "Spares Replaced")
				{
					var bdKeyid=jQuery('#cmbbdmsKeyid').val();
					var woId = jQuery('#hdnwoid').val();
					
					var machineid=getFieldValue("cmbbdmsMachineid");
					var mode = "";
					LoadForm("loadSparesReplaced","prevloadSparesReplaced","sparesReplaced_input.Bbrdn?&woId="+woId ,"","sapInfoComplete","sapInfoErr");
					/*
					if(jQuery('#chkbdmsProductionstop').is(':checked') )
						LoadForm("loadSparesReplaced","prevloadSparesReplaced","sparesReplaced_input.Bbrdn?&woId="+woId ,"","sapInfoComplete","sapInfoErr");
					else{ 
						 
						var tab = jQuery('#tabbrkDown').tabs('getSelected');
						var index = jQuery('#tabbrkDown').tabs('getTabIndex',tab);
						//jQuery('#tabbrkDown').tabs('disableTab', '6');
						//alert("index "+index);
						 jQuery('#tabbrkDown').tabs('select', index);
						 return ;
					}*/
				}
				
				else if(title == "External Service")
				{
					/*var bdKeyid=jQuery('#cmbbdmsKeyid').val();
					var flid = jQuery("#frmBDMaster input[id='flid']").val(); 
					var shift = jQuery('#cmbbdmsShiftid').combobox('getValue');
					var date = jQuery("#dtebdmsReporteddate").datebox('getValue');
					var mchId = jQuery("#cmbbdmsMachineid").combobox('getValue');
					var datStr = "&formMode=ExtService&bdKeyid="+bdKeyid+"&flid="+flid+"&shift="+shift+"&date="+date+"&mchId="+mchId;
					LoadForm("loadExtService","prevloadExtService","externalService_input.Bbrdn?"+datStr,"externalServiceComplete","externalServiceErr");*/
					jQuery('#loadExtRepair').html(' ');
					openExternal("ExtService","loadExtService");
					 
				}
				else if(title == "External Repair" )
				{
					/*var woId=jQuery('#cmbbdmsKeyid').val();
					LoadForm("loadExtRepair","prevloadExtRepair","externalRepair_input.Bbrdn?formMode=ExtRepair","","externalRepairComplete","externalRepairErr");*/
					jQuery('#loadExtService').html(' ');
					openExternal("ExtRepair","loadExtRepair");
					 
				}
			}
			});
			
			fileManagerPopUp("","bdn","frmBDMaster","btnFilManage","bdnFilemgr");

		    /*if(jQuery('#txtFormmode').val().substring(0,13) == "view")
			{
				disableForm('frmBDMaster');
				jQuery('#forNewPP').css('display','none');
			}*/

       // var enable = false;
		   
		 if( jQuery("#hdnBdmsStatus").val() =="C" && jQuery("#dtebdmsCompleteddate").val().length > 0 )
		 {
			 jQuery("#chkChkCompletedBy").attr('checked',true);
			 //readOnlyFields("hdnBdmsStatus");
			 disableUIButton('btnbdnActionplan');	
		//	 enable =false; 	
		 }
		 
		 //enableDisableCompletdFields(enable);
		 var btnClicked = jQuery('#hdnSprOkBtnClick').val();
		// alert('Button Clicked'+btnClicked);
		 if(btnClicked=="Y"){ 
			// alert('Button Clicked'+btnClicked);
			 jQuery("#sapInfoGrid").trigger("reloadGrid");
			 }	 	
		jQuery("#btnbdnWhyWhy").click(function(){
		//alert(jQuery("#mode").val() );
		   //if(jQuery("#mode").val() != null && jQuery("#mode").val() != '' && jQuery("#mode").val() != ' ')
			//	{
					var finalAction = jQuery('#txtbdanFinalaction').val();
					var phenomena = '';
					var formName = ''; 
					var EMRNo =jQuery('#cmbbdmsKeyid').combobox('getValue');
				    var countermeasure = jQuery('#txabdanCountermeasure').val();
					
				 	if( countermeasure.trim().length == 0 && finalAction.trim().length == 0 ){
						alert('Enter Final action and Countermeasure details');
						jQuery('#txabdanCountermeasure').focus();
						return;
					}	
					if(countermeasure.trim().length == 0){
						alert('Enter Countermeasure');
						jQuery('#txabdanCountermeasure').focus();
						return;
					}	
					else if(finalAction.trim().length == 0) {
						alert('Enter Final action');
						jQuery('#txtbdanFinalaction').focus();
						return;
					}	
					
	 
					if( EMRNo == null || EMRNo.length == 0 || EMRNo  == ''  )
					{
						finalAction = jQuery('#txtbdanFinalaction').val();
						phenomena = jQuery("#cmbbdmsFinalphenomena").combobox('getText');
						
						//if(finalAction != null && finalAction != '' && finalAction != ' ')
						//{
							//if(phenomena != 'NOT DEFINED')		{	
							 								
								saveForm('frmBDMaster','Breakdown_yy.Bbrdn?q=2&finalAction='+finalAction);	
						/*	}
							else
								alert("This is Undefined Phenomena");
								*/	
						//}	
						//else
							//alert("Enter Final Action");
					}
					else
					{	
						funWhyWhyAnalysis(EMRNo);
					}
		   

					   
	});
function funWhyWhyAnalysis(EMRNo)
{
	var refDocId =EMRNo;// jQuery('#txtwomsActivityid').val();
	 var yyNO = ""; //jQuery('#txtbdanWwno').val();
	 var womsKey = EMRNo ;//jQuery('#txtwomsKeyid').val();
	 var formType = null;
	 var forwardData = null;
	 var countermeasure = jQuery('#txabdanCountermeasure').val();
	 if(refDocId != null && refDocId != '' && refDocId != ' ')
	 {
		 if(refDocId.substring(0,2) == 'BD' || refDocId.substring(0,2) == 'PB'|| refDocId.substring(0,2) == 'BP')
			 formType = 'BD';
		 else if(refDocId.substring(0,2) == 'AB')
			 formType = 'ABN';
		 else
			 formType = 'GM';
	 }
	 var persistentData = null;
	 var forwardData = {'whywhyRefDocID':refDocId,'txtformType':formType,'womsKey':womsKey};
	 var names = Object.keys(forwardData);
	 	var fData ="";
		for(var i=0;i<names.length;i++ )
		{
			
			fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
		}
		var flid = jQuery("#frmBDMaster input[id='flid']").val();
		fData += "&flid="+flid +"&flId="+flid +"&refdocid="+refDocId+"&problem=" + escape(jQuery("#txtbdmsProblemdescription").val());	
	//var mode = "";
	//if( jQuery("#hdnBdmsStatus").val() =="C")
		//mode ="view";
	//fData += "&yymode="+mode;	
	var finalAction = jQuery('#txtbdanFinalaction').val();
	var immediateAction=jQuery("#txtbdmsImmediateaction").val();
	var reportdte= jQuery('#dtebdmsReporteddate').datebox('getValue');//jQuery("#dtebdmsReporteddate").val();
	//jQuery('#dtebdmsReporteddate').datebox('getValue')
	
	var reporttime=jQuery("#spnbdmsReportedtime").val();
	fData += "&finalAction="+escape(finalAction)+"&immediateAction="+escape(immediateAction)+"&rptdte="+reportdte+"&rpttime="+reporttime+"&countermeasure="+escape(countermeasure);	
	alert(fData);
	 LoadPopUp("divBDWhywhy", 'whywhyanalysismodify_input.balwhy?'+fData, true,"96%","91%","3px",null, null, "Why Why Analysis",false, true,true);
	 //navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData ); 
	//saveForm('frmWorkOrder','bdCompletion_save.work?q=2');
}

	jQuery("#btnbdmsRepeatedbdno").click(function (){
		var machineId = getFieldValue("cmbbdmsMachineid");
		if( machineId.trim().length ==0 ){
			alert("Select Machine");
			return ;
		}	
		LoadPopUp("divRepeatedBd", "repeatedBD_input.Bbrdn?machineId="+machineId, true, "1030px", "500px", "10px", "10px", "", "Repeated Breakdown", false, false);
	});

	
	jQuery("#txtbdmsDowntime").change(function(){
		
		if( parseInt( jQuery(this).val(),10) <= 10 )	{
			jQuery("#cbobdmsActivity").val("M");
		}
		else
			jQuery("#cbobdmsActivity").val("B");
	});	

	jQuery("#chkChkCompletedBy").click(function(){

		enableDisableCompletdFields(jQuery("#chkChkCompletedBy").is(':checked'));
			
	});
	var stutus=jQuery('txtbdanErppoststatus').val();
	//alert("activity" +hdncbobdmsActivity);
	//jQuery("#chkOtherAssm").click(function(){
		//if(jQuery("#chkOtherAssm").is("checked")){
			//reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter" );			
		//}
		//else{
			//var machId = jQuery("#frmBDMaster input[id='machine']").val();
			//reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ machId );
		//}
	//});
	jQuery("#chkOtherAssm").click(function(){
		
		var machId = jQuery("#cmbbdmsMachineid").combobox('getValue');
		
		jQuery("#cmbbdmsAssemblyid").combobox('clear');
	    jQuery("#cmbbdmsSubassemblyid").combobox('clear');
	    jQuery("#cmbbdmsFinalphenomena").combobox('clear');
	    jQuery("#cmbbdmsFinalcause").combobox('clear');
	
		if(jQuery("#chkOtherAssm").is(":checked")){
			//reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter" );		
			reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineNotToShown=" + machId);
		}
		else{
			//var machId = jQuery("#frmBDMaster input[id='machine']").val();
			reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ machId );
		}
	});
	//priyanka
	jQuery("#chkOtherCause").click(function(){
	    var phenId = jQuery("#cmbbdmsFinalphenomena").combobox('getValue');
	    var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
	    
	    if(assmId == null || assmId == '' || assmId == ' '){
	        alert('Select Assembly');
	        jQuery("#chkOtherCause").prop('checked', false);
	        return;
	    }
	    if(jQuery("#chkOtherCause").is(":checked")){
	        //reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId="+phenId );
	    	//reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn" );
	    	reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?assmId=" + assmId);
	    }
	    else{
	        var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
	        //reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId="+phenId+"&assmId="+assmId );
	        reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId=" + phenId + "&assmId=" + assmId);
	    }
	});
	  //mano
	   
 	   
// ===== TEMPORARY BYPASS - Remove after testing =====

/* jQuery("#btnbdnWhyWhy").off("click").on("click", function(){
    var EMRNo = jQuery('#cmbbdmsKeyid').combobox('getValue');
    if(EMRNo != null && EMRNo != ''){
        funWhyWhyAnalysis(EMRNo);
        
        
        
        
    } else {
        var flid = jQuery("#frmBDMaster input[id='flid']").val();
        var finalAction = jQuery('#txtbdanFinalaction').val() || '';
        var immediateAction = jQuery("#txtbdmsImmediateaction").val() || '';
        var countermeasure = jQuery('#txabdanCountermeasure').val() || '';
        var problem = jQuery("#txtbdmsProblemdescription").val() || '';
        var reportdte = jQuery('#dtebdmsReporteddate').datebox('getValue');
        var reporttime = jQuery("#spnbdmsReportedtime").val() || '';
        var fData = "whywhyRefDocID=TEMP&txtformType=BD&womsKey=TEMP";
        fData += "&flid="+flid+"&flId="+flid+"&refdocid=TEMP";
        fData += "&problem="+escape(problem);
        fData += "&finalAction="+escape(finalAction);
        fData += "&immediateAction="+escape(immediateAction);
        fData += "&rptdte="+reportdte+"&rpttime="+reporttime;
        fData += "&countermeasure="+escape(countermeasure);
        LoadPopUp("divBDWhywhy", 'whywhyanalysismodify_input.balwhy?'+fData, true,"96%","91%","3px",null, null, "Why Why Analysis",false, true,true);
    }
}); 

jQuery("#btnEstimation").off("click").on("click", function(){
    var EMRNo = jQuery('#cmbbdmsKeyid').combobox('getValue');
    var flid = jQuery("#frmBDMaster input[id='flid']").val();
    var factId = jQuery("#frmBDMaster input[id='factory']").val();
    var machId = jQuery("#frmBDMaster input[id='machine']").val();
    var problem = jQuery("#txtbdmsProblemdescription").val() || '';
    var docNo = (EMRNo != null && EMRNo != '') ? EMRNo : 'TEMP';
    var fData = "formType=Estimate&DocType=BDM&DocNo="+docNo;
    fData += "&FactoryId="+factId+"&MachineId="+machId;
    fData += "&flid="+flid+"&flId="+flid;
    fData += "&Problem="+escape(problem);
    LoadPopUp("divBDCostinfo", 'costSummary_input.crt?'+fData, true,"95%","90%","10px",null, null, "Cost Information - Estimation",false, true,true);
});

jQuery("#btnActual").off("click").on("click", function(){
    var EMRNo = jQuery('#cmbbdmsKeyid').combobox('getValue');
    var flid = jQuery("#frmBDMaster input[id='flid']").val();
    var factId = jQuery("#frmBDMaster input[id='factory']").val();
    var machId = jQuery("#frmBDMaster input[id='machine']").val();
    var problem = jQuery("#txtbdmsProblemdescription").val() || '';
    var docNo = (EMRNo != null && EMRNo != '') ? EMRNo : 'TEMP';
    var fData = "formType=Actual&DocType=BDM&DocNo="+docNo;
    fData += "&FactoryId="+factId+"&MachineId="+machId;
    fData += "&flid="+flid+"&flId="+flid;
    fData += "&Problem="+escape(problem);
    LoadPopUp("divBDCostinfo", 'costSummary_input.crt?'+fData, true,"95%","90%","10px",null, null, "Cost Information - Actual",false, true,true);
});

jQuery("#btnbdnActionplan").off("click").on("click", function(){
    var bdnkeyid = jQuery('#cmbbdmsKeyid').combobox('getValue');
    var flid = jQuery("#frmBDMaster input[id='flid']").val();
    var mainTask = jQuery("#txtbdmsProblemdescription").val() || '';
    var keyid = (bdnkeyid != null && bdnkeyid != '') ? bdnkeyid : 'TEMP';
    openActionPlan("bdnActionPlan", keyid, "BDN", flid, mainTask, "");
}); 
 */
// ===== END TEMPORARY BYPASS =====
});		

function divAsmblyLink_onClose(){
	var machId = jQuery("#frmBDMaster input[id='machine']").val();
	
	reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ machId );
	return true;
	
}
//priyanka
function divCauseLink_onClose(){
	var phenId = jQuery("#cmbbdmsFinalphenomena").combobox('getValue');
	var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
	var ds;
	if(jQuery('#chkOtherCause').is(':checked') == true)
		//assmId = "";
		ds = "assmId=" + assmId;
	else
	    ds = "phenId=" + phenId + "&assmId=" + assmId;	

	//reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId="+phenId+"&assmId="+assmId);
	reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?"+ds);
	return true;
}

   function enableDisableCompletdFields(enable){
	   if(jQuery('#mode').val() == 'view') return;
	   if( enable ){
			enableFields("cmbbdanCompletedby");
			enableFields('dtebdmsCompleteddate');
			enableFields('spnbdmsCompletedtime');
			jQuery('#hdnBdmsStatus').val("C");
			enableFields("chkbdSubmittoSap");
			enableFields("chkbdEntryPcs");

			var endDt = jQuery('#dtebdmsWoendtime').datebox('getValue');
			var endTime = jQuery('#spnbdmsWoend').spinner('getValue');
			var prdStDt = jQuery('#dtebdmsProdaccepdate').datebox('getValue');
			var prdStTime = jQuery('#spnbdmsProdacceptime').spinner('getValue');

			if(prdStDt.trim() == "")
			{
				prdStDt = endDt ;
				prdStTime = endTime;
				displayText('dtebdmsProdaccepdate',prdStDt);
				displayText('spnbdmsProdacceptime',prdStTime);
			}
			displayText('dtebdmsCompleteddate',prdStDt);
			displayText('spnbdmsCompletedtime',prdStTime);
		}	
		else{
			jQuery('#hdnBdmsStatus').val("X");
			readOnlyFields('cmbbdanCompletedby');
			readOnlyFields('dtebdmsCompleteddate');
			readOnlyFields('spnbdmsCompletedtime');
			readOnlyFields("chkbdSubmittoSap");
			readOnlyFields("chkbdEntryPcs");
			displayText('dtebdmsCompleteddate',"");
			displayText('spnbdmsCompletedtime',"");
		}
	   mandatoryLblForCompletion(enable);
	}
	function mandatoryLblForCompletion(isCompletion){
		if( isCompletion){
			jQuery('#lblSpareReplaced,#lblBDClassfcn,#lblAnalysedBy,#lblCompleteddate,#lblFinalAction').addClass('mandatory-lbl');
		}
		else
			jQuery('#lblSpareReplaced,#lblBDClassfcn,#lblAnalysedBy,#lblCompleteddate,#lblFinalAction').removeClass('mandatory-lbl');
		
	}		
   function getSAPbdMsg(result){
		alert(result.responseText);
	  
	}
	function getSAPbdErr(result){
		alert(result.responseText);
		  
	}
	function openExternal(mode,loadFormId){
		var bdKeyid=jQuery('#cmbbdmsKeyid').val();
		var flid = jQuery("#frmBDMaster input[id='flid']").val(); 
		var shift = jQuery('#cmbbdmsShiftid').combobox('getValue');
		var date = jQuery("#dtebdmsReporteddate").datebox('getValue');
		var mchId = jQuery("#cmbbdmsMachineid").combobox('getValue');
		var extKeyid = jQuery('#hdnExtServiceID').val();
		var woId = jQuery('#hdnwoid').val();
		
		var datStr = "&formMode="+mode+"&bdKeyid="+bdKeyid+"&flid="+flid+"&shift="+shift+"&date="+date+"&mchId="+mchId+"&extKeyid="+extKeyid+"&woId="+woId;
		LoadForm(loadFormId,"prevloadExtService","externalService_input.Bbrdn?"+datStr,"externalServiceComplete","externalServiceErr");
	}
	function sapInfoComplete(){}
	function sapInfoErr(){}
	function externalServiceComplete(){}
	function externalServiceErr(){}
	function externalRepairComplete(){}
	function externalRepairErr(){}
	
	//jQuery("#btnFilManage").click(function(){
	function btnFilManage_click(){
        
	    var documentNo =jQuery("#cmbbdmsKeyid").combobox("getValue");
	    //alert(" documentNo:::1234::: "+documentNo);
		if(documentNo != null && documentNo != ''){
			
				
			
		
			fileManagerPopUp(documentNo,"BDN","","","");
				
		}	
		 else
		    {
			    
				 saveForm('frmBDMaster','Breakdown_save.Bbrdn?filemanager=filemanager');
     		}	
 }
	/*	function btnFilManage_click(){
			
		    var documentNo =jQuery('#hdnbdnkeyID').val();
			//alert("documentNo"+documentNo);
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"BDN","","","");
			}
			
	 		}
	 		 //});*/
		/*jQuery("#bdnFilemgr").click(function(){
			 var documentNo =jQuery('#hdnbdnkeyID').val();
		    LoadPopUp("fileManagerDivId","file_input.file"+dataStr,true,"70%","92%","1%","14%","","File Manager","","",true,"setFileManagerdimension");
		    
			fileManagerPopUp(documentNo,"bdn","frmBDMaster","bdnFilemgr","","","");
			
			//processGridnew("file_input.file","?q=2","CommnGrid","","File Manager","","","");
		});*/
		//function btnbdnActionplan_click(){
		jQuery("#btnbdnActionplan").click(function(){
			// var bdnkeyid =jQuery('#hdnbdnkeyID').val();
			 var bdnkeyid = jQuery('#cmbbdmsKeyid').combobox('getValue');
			
			 var flid = jQuery("#frmBDMaster input[id='flid']").val();
			 var elementId=jQuery("#elementId").val();
			 var mainTask=jQuery("#txtbdmsProblemdescription").val();
			 //var datStr = "&elementId="+elementId+"&flid="+flid;
             //alert("Inside Breakdown form click :: bdnkeyid"+bdnkeyid);
			 if(bdnkeyid != null && bdnkeyid != '')
						 openActionPlan("bdnActionPlan",bdnkeyid,"BDN",flid,mainTask,"");
			 else
				 alert('Breakdown details is not saved');
		});
		 //}
  
   jQuery("#cmbbdmsRelatedto").combobox({
		onSelect:function(recordid){			
			
			if(jQuery('#mode').val() == 'view') return;		
			if(recordid.id == 'MLD')
			{		
				jQuery("#lblMld").addClass('mandatory-lbl');	
				enableFields('cmbbdmsMould');
				var mchId = jQuery("#cmbbdmsMachineid").combobox('getValue');
				if(mchId != null && mchId != ' ' && mchId !='')
				{
					jQuery('#cmbbdmsMould').combobox('clear');
					reloadCombo("frmBDMaster","cmbbdmsMould","combo_mould.Bbrdn?q=2&mchId="+mchId );
				}
			}
			if(recordid.id == 'MCH')
			{				
				jQuery("#lblMld").removeClass('mandatory-lbl');
				jQuery("#cmbbdmsMould").combobox('clear');
				readOnlyFields('cmbbdmsMould'); 	
				var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
				var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
				jQuery('#cmbbdmsFinalphenomena').combobox('clear');
			
				if(jQuery('#chkIsAssmWise').is(':checked') != true)
				{
					
					var dataStr = "?q=2&mchId="+mchId;					
					//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn"+dataStr);
				}	
				else
				{
					var dataStr = "?q=2&assmId="+assmId+"&mchId="+mchId;					
					//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn"+dataStr);
				}
			}
			
		}
   
   });     	
	/* function dateFiledFill() { 
		fillWithCurrentDate('dtebdmsReporteddate');
		fillWithCurrentDate('dtebdmsReceiveddate');
		fillWithCurrentDate('dtebdmsWostarttime');
		fillWithCurrentDate('dtebdmsWoendtime');
		fillWithCurrentDate('dtebdmsProdaccepdate');
		fillWithCurrentDate('spnbdmsReportedtime');
		fillWithCurrentDate('spnbdmsReceivedtime');
		fillWithCurrentDate('spnbdmsWostart');
		fillWithCurrentDate('spnbdmsWoend');
		fillWithCurrentDate('spnbdmsProdacceptime');
		fillWithCurrentDate('txtbdmsEntrydate');
	}
	 */
	 
	 function dateFiledFill() { 
		    fillWithCurrentDate('dtebdmsReporteddate');
		    fillWithCurrentDate('dtebdmsReceiveddate');
		    fillWithCurrentDate('dtebdmsWostarttime');
		    fillWithCurrentDate('dtebdmsWoendtime');
		    fillWithCurrentDate('dtebdmsProdaccepdate');
		    fillWithCurrentDate('spnbdmsReportedtime');
		    fillWithCurrentDate('spnbdmsReceivedtime');
		    fillWithCurrentDate('spnbdmsWostart');
		    fillWithCurrentDate('spnbdmsWoend');
		    fillWithCurrentDate('spnbdmsProdacceptime');
		    fillWithCurrentDate('txtbdmsEntrydate');

		    // Defer padding slightly so it never runs before the easyUI
		    // timespinner widgets have finished initializing, and so a
		    // failure here can never block the rest of document.ready().
		    setTimeout(function(){
		        padSpinnerHour('spnbdmsReportedtime');
		        padSpinnerHour('spnbdmsReceivedtime');
		        padSpinnerHour('spnbdmsWostart');
		        padSpinnerHour('spnbdmsWoend');
		        padSpinnerHour('spnbdmsProdacceptime');
		    }, 0);
		}
/*	function frmBDMastercmbbdmsMachineid_onLoad(){
		if( jQuery("#hdncmbbdmsMachineid").val() == "true" )
		//	readOnlyFields('cmbbdmsMachineid'); CHANGED By Kiran Request BY S.C.Mahajan 3Wh M/c. 09-12-2019
		
		enableFields('cmbbdmsMachineid');
		else
			enableFields('cmbbdmsMachineid');
					
	}
	
	*/
	function costGridLoad()
	{				
	}
	function frmBDMastercmbbdmsMould_onLoadSuccess()
	{
		if( jQuery("#hdncmbbdmsoulddis").val() == "true" )
			readOnlyFields('cmbbdmsMould');
		else
			enableFields('cmbbdmsMould');
				
	}
	function frmBDMastercmbbdmsMould_onSelect(record)
	{
		if(jQuery('#chkIsAssmWise').is(':checked') != true)
		{
			
			var dataStr = "?&mldId="+record.id;
			jQuery('#cmbbdmsFinalphenomena').combobox('clear');
			//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn"+dataStr);
		}
	}
	function frmBDMastercmbbdanCostcentre_onLoadSuccess()
	{
		var costctrId = jQuery('#cmbbdanCostcentre').combobox('getValue');
		
		if(costctrId != null && costctrId != '' && costctrId != ' ')
			readOnlyFields('cmbbdanCostcentre');
	}
	
	function frmBDMastercmbbdanCostcentre_onSelect(record)
	{	
				//processAjaxCalls("costcenter_fillcombo.Bbrdn","?q=2&costCenterId="+record.id,"costCenterIdRecallSuccess","costCenterIdRecallError");
	}			//reloadCombo("frmBDMaster","cmbbdmsCellid","cellCombo.commonFilter?costCentreId="+record.id );
	function costCenterIdRecallSuccess(result)	{	
				
			displayText('cmbbdmsFactoryid',result.costCenterHierarchy.factory);
          	displayText('cmbbdmsSectionid',result.costCenterHierarchy.section);
          	displayText('cmbbdmsCellid',result.costCenterHierarchy.cell);          	 	          		
      //    	jQuery('#cmbbdmsFactoryid').combobox('disable');
         // 	jQuery('#cmbbdmsSectionid').combobox('disable');
        // 	jQuery('#cmbbdmsCellid').combobox('disable');
	}
	function costCenterIdRecallError()	{
			alert("error");	
	}
	function frmBDMastercmbbdmsShiftid_onLoadSuccess()
	{
		readOnlyFields('cmbbdmsShiftid');
	}
	function frmBDMastercmbbdmsBookedby_onLoadSuccess()
	{
		 var bookedBY = jQuery('#cmbbdmsBookedby').combobox('getValue');
		 if(bookedBY!=null && bookedBY != '' && bookedBY != ' ')	 
		 	readOnlyFields('cmbbdmsBookedby');     						
	}
	function frmBDMastercmbbdanCompletedby_onLoadSuccess()
	{	
		 var completedBY = jQuery('#cmbbdanCompletedby').combobox('getValue');
		 if(completedBY!=null && completedBY != '' && completedBY != ' ')	 
		 setTimeOut(function(){	readOnlyFields('cmbbdanCompletedby');},20000);
		//fillComboBox("frmBDMaster","cmbbdmsBookedtrade","combo_trade.Bbrdn" );
		
		//fillComboBox("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter");
	}
	function frmBDMastercmbbdmsAssemblyid_onLoadSuccess()
	{
		var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
		var assId= jQuery('#cmbbdmsAssemblyid').combobox('getValue');
	//	if(mchId != null && mchId != '' && mchId != ' ')
		//	readOnlyFields('cmbbdmsMachineid'); commented By Kiran 09-12-2019
		
		//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+record.id+"&mchId="+mchId);
		
		
	}
	
	/*function frmBDMastercmbbdmsAssemblyid_onLoadSuccess()
	{		
		//var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
		//var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
		//alert(mchId);
    	//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+record.id+"&mchId="+mchId);
		//if(assmId != null && assmId != '' && assmId != ' ')
		 //fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+record.id+"&mchId="+mchId);
	}*/
	function frmBDMastercmbbdmsSpareid_onLoadSuccess()
	{			
		//fillComboBox("frmBDMaster","cmbbdmsBookedtrade","combo_trade.Bbrdn" );		
	}	
	function frmBDMastercmbbdmsSubassemblyid_onLoadSuccess(){
		
		//readOnlyFields('cmbbdmsSubassemblyid');	
		
	}
	function frmBDMastercmbbdmsBookedtrade_onLoadSuccess()
    {			
	    var dataString = '?q=2';
	    var relatedTo = jQuery('#relatedToCMB').val();	    
	    if(relatedTo != null && relatedTo != '' && relatedTo != ' ')
	    	dataString += '&relatedTo='+relatedTo;
    	
		//fillComboBox("frmBDMaster","cmbbdanFailuretype","combo_failtype.Bbrdn"+dataString);
    }
	function frmBDMastercmbbdmsAlarmdescription_onLoadSuccess()
    {
		
    }
	function frmBDMastercmbbdanFailuretype_onLoadSuccess()
    {
		//fillComboBox("frmBDMaster","cmbbdanClassificationid","combo_bdclfcn.Bbrdn");
		/*var failureType=jQuery('#cmbbdanFailuretype').combobox('getText');
		       		if( failureType.trim().toUpperCase() == 'OTHERS-O' )
			       		enableFields("txtbdanOtherFailuretype");
			        else
			        {
			        	jQuery('#txtbdanOtherFailuretype').val('');
		       			readOnlyFields("txtbdanOtherFailuretype");
			        }*/
    }
	function frmBDMastercmbbdanClassificationid_onLoadSuccess()
    {
	
    }
	function frmBDMastercmbbdmsFinalphenomena_onLoadSuccess()
    {
		if( jQuery("#cmbbdmsKeyid").combobox('getValue') == null || jQuery("#cmbbdmsKeyid").combobox('getValue') =='')
		{
			//alert(jQuery('#cmbbdmsAssemblyid').combobox('getValue'));
			if( jQuery("#cmbbdmsAssemblyid").combobox('getValue') == null || jQuery("#cmbbdmsAssemblyid").combobox('getValue') =='')
			{
				//readOnlyFields('cmbbdmsFinalphenomena');
			}
		}
		//jQuery('#cmbbdmsFinalcause').combobox('clear');
		var ds = "";
		if( jQuery("#cmbbdmsFinalphenomena").combobox('getValue') != null && jQuery("#cmbbdmsFinalphenomena").combobox('getValue') !='' && jQuery("#cmbbdmsFinalphenomena").combobox('getValue') != ' ')
			 ds += "&phenId="+jQuery('#cmbbdmsFinalphenomena').combobox('getValue')+"&assmId="+jQuery('#cmbbdmsAssemblyid').combobox('getValue');
		    reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn"+ds);

		   /* var finalPhenomena=jQuery('#cmbbdmsFinalphenomena').combobox('getText');
       		if( finalPhenomena.trim().toUpperCase() == 'OTHERS')
           		enableFields("txtbdmsOtherPhenomena");
	        else 
		    {
	        	jQuery('#txtbdmsOtherPhenomena').val('');
       			readOnlyFields("txtbdmsOtherPhenomena");
		    }*/
	}
	
	function frmBDMastercmbbdmsFinalcause_onLoadSuccess()
    {
		//mano
		if(jQuery('#mode').val() == 'view') return;
		//
		var EMRNo =jQuery('#cmbbdmsKeyid').combobox('getValue');
		if (EMRNo != null && EMRNo !='') {					
      		jQuery('#cmbbdmsMachineid').combobox('enable');
      		jQuery('#cmbbdmsCellid').combobox('enable');
      		//if(jQuery('#cmbbdanCostcentre').combobox('getValue') != null && jQuery('#cmbbdanCostcentre').combobox('getValue') != '')
          	//{
              
      			//jQuery('#cmbbdanCostcentre').combobox('enable');
          	//}
      		jQuery('#cmbbdmsSectionid').combobox('enable');   
            jQuery('#cmbbdmsFactoryid').combobox('enable');    				
           // fnEnableDisCommon();
        }
		//	
	}
	/*function frmBDMastercmbbdmsFinalphenomena_onSelect()
    {
		reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?q=2&phenId="+jQuery('#cmbbdmsFinalphenomena').combobox('getValue'));
		jQuery('#cmbbdmsFinalcause').combobox('clear');
    }*/
    function frmBDMastercmbbdmsSpareid_onSelect()
    {
        var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
        if(mchId == null || mchId == ' ' || mchId == '')
        {
        	jQuery('#cmbbdmsSpareid').combobox('clear');
        	alert('Select Machine');
        }
    }
	function frmBDMastercmbbdmsFinalcause_onSelect()
    {
	        phenId = jQuery('#cmbbdmsFinalphenomena').combobox('getValue');	      		
	      	if (phenId == null || phenId =='') {
		     	alert("Select Phenomena"); 		
		    	jQuery('#cmbbdmsFinalcause').combobox('clear');		      	
			}
	}
    function frmBDMastercmbbdmsSubassemblyid_onSelect()
    {
        	
	      	assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');	      		
	      	if (assmId == null || assmId =='') {
		     	alert("Select Assembly"); 		
		     	jQuery('#cmbbdmsSubassemblyid').combobox('clear');		      	
	      	}
    }

    function frmBDMastercmbbdmsAssemblyid_onSelect(record)
	{
    		//alert(1221);
    		if(jQuery('#mode').val() == 'view') return;
    	jQuery('#cmbbdmsFinalphenomena').combobox('clear');
    //	jQuery('#cmbbdmsAssemblyid').combobox('clear');
   		//var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
   		var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
   	//	if(jQuery('#chkIsAssmWise').is(':checked') == true)
    	//	reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?assmId="+record.id+"&mchId="+mchId);
   		//else
   			//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&mchId="+mchId);
    //	reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?q=2&assmId="+jQuery('#cmbbdmsAssemblyid').combobox('getValue'));
    //	jQuery('#txtbdmsRepeatedbdno').combobox('clear');
    	jQuery('#cmbbdmsSpareid').combobox('clear');
    	jQuery('#txtbdanFinalaction').val('');
    	 jQuery('#chbbdmsRepeatedbdflag').attr('checked',false);	
    	var reportedDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');					
	//	reloadCombo("frmBDMaster","txtbdmsRepeatedbdno","combo_repeatedbdno.Bbrdn?q=2&assmId="+record.id+'&eqpID='+mchId+'&repDate='+reportedDate);
		reloadCombo("frmBDMaster","cmbbdmsSpareid","combo_spare.Bbrdn?assmId="+record.id);
		reloadCombo("frmBDMaster","cmbbdmsSubassemblyid","combo_subassmbly.Bbrdn?assmId="+record.id+"&machineId="+mchId);
		addMandClass('lblPriority');
		addMandClass('lblMaintSect');
		addMandClass('lblFailType');
		addMandClass('lblPhen');
		//addMandClass('lblBDClassfcn');
		addMandClass('lblBookedBy');
		//addMandClass('lblAnalysedBy');
		setTimeout(function() {enableFields('cmbbdmsFinalphenomena');},1250);
		jQuery("#txabdanRemarks").css('background-color', '#D1E2FD');					
		readOnlyFields('txabdanRemarks');
		removeMandClass('lblcomText');	

	}
    function frmBDMastertxtbdmsRepeatedbdno_onSelect(record)
	{
		jQuery('#txtbdanFinalaction').val('REPEAT BREAKDOWN');
		jQuery("#txabdanRemarks").css('background-color', '#FFFFFF');
		//var x =;
		enableFields('txabdanRemarks');
		addMandClass('lblcomText');
		removeMandClass('lblPriority');
		removeMandClass('lblMaintSect');
		removeMandClass('lblFailType');
		removeMandClass('lblPhen');
		removeMandClass('lblBDClassfcn');
		removeMandClass('lblBookedBy');
		removeMandClass('lblAnalysedBy');
		
	}
	function frmBDMastercmbbdmsFactoryid_onSelect(record)
    {
			jQuery("#cmbbdmsSectionid").combobox('clear');
			jQuery("#cmbbdmsCellid").combobox('clear');
			jQuery("#cmbbdmsMachineid").combobox('clear');
			reloadCombo("frmBDMaster","cmbbdmsSectionid","sectionCombo.commonFilter?factId="+record.id);
			reloadCombo("frmBDMaster","cmbbdmsCellid","cellCombo.commonFilter?factId="+record.id  );
			//reloadCombo("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter?factId="+ record.id );
			//reloadCombo("frmBDMaster","cmbbdmsMachineid","costCenter.commonFilter?factId="+ record.id );
    }
	function frmBDMastercmbbdmsMachineid_onSelect(record)
    {	
		   // the loadfunctional location is commented by 
		//loadFunctionalLocation("bdmsfunLocation","functionalLoc.Bbrdn","bdmsfunLocationValues","frmBDMaster","&machId="+record.id);
		   loadFunctionalLocation("bdmsfunLocation","functionalLoc.Bbrdn","bdmsfunLocationValues","frmBDMaster","&machId="+record.id);
		    //setFieldValue();
		    setFieldValue("cmbbdmsAssemblyid"," ");
		    setFieldValue("cmbbdmsFinalphenomena"," ");
            //	fnEnableDisCommon(); 
          	//processAjaxCalls("machine_fillcombo.Bbrdn","?q=2&eqpId="+record.id,"eqpIdRecallSuccess","eqpIdRecallError");	
          	
    }
    function  frmBDMastercmbbdmsMachineid_onClear(record)
    {          		
            jQuery('#cmbbdmsFactoryid').combobox('enable');
      		jQuery('#cmbbdmsSectionid').combobox('enable');
      		jQuery('#cmbbdanCostcentre').combobox('enable');				
      		jQuery('#cmbbdmsCellid').combobox('enable');	
    }
	function  frmBDMastercmbbdmsSectionid_onSelect(record)
    {
         	jQuery("#cmbbdmsCellid").combobox('clear');
         	jQuery("#cmbbdmsMachineid").combobox('clear');
         	reloadCombo("frmBDMaster","cmbbdmsCellid","cellCombo.commonFilter?sectId="+record.id  );
         	reloadCombo("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter?sectId="+ record.id );
			processAjaxCalls("sectionHierarchy.commonFilter","?q=2&sectionId="+record.id,"sectionIdRecallSuccess","sectionIdRecallError");
	}
	function  frmBDMastercmbbdmsSectionid_onClear()
    {
         	jQuery('#cmbbdmsFactoryid').combobox('enable');
    } 	
	function  frmBDMastercmbbdmsCellid_onSelect(record)
    {
       		jQuery("#cmbbdmsMachineid").combobox('clear');
   			//reloadCombo("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter?cellId="+ record.id );
			processAjaxCalls("cellHierarchy.commonFilter","?q=2&cellId="+record.id,"cellIdRecallSuccess","cellIdRecallError");
    }

    function  frmBDMastercmbbdmsCellid_onClear(record)
    {
    		jQuery('#cmbbdmsFactoryid').combobox('enable');
     		jQuery('#cmbbdmsSectionid').combobox('enable');              	
    }
    function eqpIdRecallSuccess(result)
    {	 
    	
            displayText('cmbbdmsFactoryid',result.machineHirerachy.factory);
          	displayText('cmbbdmsSectionid',result.machineHirerachy.section);
          	displayText('cmbbdanCostcentre',result.machineHirerachy.costcenterid);
          	displayText('cmbbdmsCellid',result.machineHirerachy.cell);
          	jQuery('#cmbbdmsFactoryid').combobox('disable');
          	jQuery('#cmbbdmsSectionid').combobox('disable');
          	jQuery('#cmbbdanCostcentre').combobox('disable');				
          	jQuery('#cmbbdmsCellid').combobox('disable');
			//fnEnableDisCommon(); 
	}

	function fnEnableDisCommon() {	
		 jQuery('#chbReporteddate').attr('checked',true);
		 if(jQuery('#txtbdmsWno').val() == null || jQuery('#txtbdmsWno').val() == '' || jQuery('#txtbdmsWno').val() == ' ')
		 {
			 enableFields('dtebdmsReporteddate');
			 enableFields('spnbdmsReportedtime');
			
		 }

		 enableFieldsInCheckBoxSelect('chbReporteddate','dtebdmsReporteddate');
		 enableFieldsInCheckBoxSelect('chbReporteddate','spnbdmsReportedtime');
		/// enableFieldsInCheckBoxSelect('chbbdmsWoallottedflag','dtebdmsReceiveddate');
		// enableFieldsInCheckBoxSelect('chbbdmsWoallottedflag','spnbdmsReceivedtime');
		 enableFieldsInCheckBoxSelect('chbbdmsWostartflag','dtebdmsWostarttime');
		 enableFieldsInCheckBoxSelect('chbbdmsWostartflag','spnbdmsWostart');
		 enableFieldsInCheckBoxSelect('chbbdmsWoendflag','dtebdmsWoendtime');
		 enableFieldsInCheckBoxSelect('chbbdmsWoprodaccepflag','dtebdmsProdaccepdate');
		 enableFieldsInCheckBoxSelect('chbbdmsWoendflag','spnbdmsWoend');
	 	 enableFieldsInCheckBoxSelect('chbbdmsWoprodaccepflag','spnbdmsProdacceptime');
		// enableFieldsInCheckBoxSelect('chbbdmsWoendflag','txtbdanFinalaction');
		if(jQuery('#cmbbdmsFinalphenomena').combobox('getText') != 'NOT DEFINED')
		{
			 enableFieldsInCheckBoxSelect('chbbdmsWoendflag','txabdanCountermeasure');	
			 enableFieldsInCheckBoxSelect('chbbdmsWoendflag','txtbdanFinalaction');
			 changeFieldColor('txabdanCountermeasure','chbbdmsWoendflag');
		}		
		// changeFieldColor('txtbdanFinalaction','chbbdmsWoendflag');
								
//		 setFieldInCheckBoxSelect('chbbdmsWoallottedflag','txtbdanErppoststatus','Allotted','Booked','','','dtebdmsReceiveddate','spnbdmsReceivedtime');			
//		 setFieldInCheckBoxSelect('chbbdmsWostartflag','txtbdanErppoststatus','Work In Progress','Allotted','chbbdmsWoallottedflag','Booked','dtebdmsWostarttime','spnbdmsWostart');
//		 setFieldInCheckBoxSelect('chbbdmsWoendflag','txtbdanErppoststatus','Completed','Work In Progress','chbbdmsWoallottedflag','Booked','dtebdmsWoendtime','spnbdmsWoend','chbbdmsWostartflag');
//		 setFieldInCheckBoxSelect('chbbdmsWoprodaccepflag','','','','','','dtebdmsProdaccepdate','spnbdmsProdacceptime','chbbdmsWoendflag');
//		 setDatenTimeInCheckBoxSelect('chbbdmsWoallottedflag','dtebdmsReceiveddate','spnbdmsReceivedtime','dtebdmsReporteddate','spnbdmsReportedtime');
//		 setDatenTimeInCheckBoxSelect('chbbdmsWostartflag','dtebdmsWostarttime','spnbdmsWostart','dtebdmsReceiveddate','spnbdmsReceivedtime');
//		 setDatenTimeInCheckBoxSelect('chbbdmsWoendflag','dtebdmsWoendtime','spnbdmsWoend','dtebdmsWostarttime','spnbdmsWostart');
//		 setDatenTimeInCheckBoxSelect('chbbdmsWoprodaccepflag','dtebdmsProdaccepdate','spnbdmsProdacceptime','dtebdmsWoendtime','spnbdmsWoend');
		
		/*  var fromTime = jQuery('#spnbdmsReportedtime').spinner('getValue');
		 var flid = jQuery("#frmBDMaster input[id='flid']").val();	
		 var dataString = '?q=2&factId='+jQuery('#factory').val()+'&sectId='+jQuery('#section').val();
		     dataString += '&cellId='+jQuery('#cell').val()+'&fromTime='+fromTime+"&flid="+flid;	
			 //alert(dataString);
		//var dataString = '?q=2&factId='+result.machineHirerachy.factory+'&sectId='+result.machineHirerachy.section;
			//dataString += '&cellId='+result.machineHirerachy.cell+'&fromTime='+fromTime; */
							
		// processAjaxCalls('txt_shift.Bbrdn',dataString,'getShift','getShiftErr');
		var fromTime = jQuery('#spnbdmsReportedtime').spinner('getValue');
		if(fromTime == null || fromTime == '' || fromTime == undefined){
		    var now = new Date();
		    fromTime = ('0'+now.getHours()).slice(-2) + ':' + ('0'+now.getMinutes()).slice(-2);
		}
		var dataString = '?q=2&factId='+factId+'&sectId='+sectionId;
		    dataString += '&cellId='+cellId+'&fromTime='+fromTime+"&flid="+flid;
		processAjaxCalls('txt_shift.Bbrdn',dataString,'getShift','getShiftErr');	
	 }
	 function setFieldInCheckBoxSelect(chbId,fieldId,chbTrueTxt,chbFalseTxt,chkChbId,chbFalseTxt2,dateField,timeField,prevChkId)
	 {
		 var prevChkbox = (prevChkId!=null&&prevChkId!='')?prevChkId:chkChbId;		
		 jQuery('#'+chbId).click(function() {
			var setText = null;
			if(chkChbId != null && chkChbId != '')
				setText = jQuery('#'+chkChbId).is(':checked') == true?chbFalseTxt:chbFalseTxt2;
			else
				setText = chbFalseTxt;
				 		
			if(jQuery('#'+chbId).is(':checked') == true)
			{			
				if(prevChkbox != null && prevChkbox != '')
				{					
					if(jQuery('#'+prevChkbox).is(':checked') == false)
					{
					   jQuery('#'+chbId).attr('checked',false);					  
					   readOnlyFields(dateField);
					   readOnlyFields(timeField);
					}
					else
					{
						if(chbId == 'chbbdmsWoendflag')
						{
							 jQuery('#lblFinalAction').addClass('mandatory-lbl');
							 jQuery("#txtbdanFinalaction").attr('readonly',false); 
							// jQuery('#estBtnShow').css('display','none');
						}
						
						displayText(fieldId,chbTrueTxt);
					}
				}
				else
					{
				
					
						displayText(fieldId,chbTrueTxt);
					}
			}
			else
				{	
					if(chbId == 'chbbdmsWoendflag')
					{
				 		jQuery('#lblFinalAction').removeClass('mandatory-lbl');
				 		jQuery("#txtbdanFinalaction").val('');
						jQuery("#txtbdanFinalaction").attr('readonly','readonly'); 
					}
					displayText(fieldId,setText);	
				}				
		});
	  }
	/*  function setDatenTimeInCheckBoxSelect(chbId,setDateF,setTimeF,getDateF,getTimeF)
	 {
		 
			jQuery('#'+chbId).click(function() {	
				  //if( jQuery("#cmbbdmsKeyid").combobox('getValue') == null || jQuery("#cmbbdmsKeyid").combobox('getValue') =='')
			       //{

				if(jQuery(this).attr("id") == "chbbdmsWoendflag" ){
			    	if(jQuery('#chbbdmsWoendflag').is(':checked') == true){
					//	actualTimeCalculation();
						enableFields("chbbdmsWoprodaccepflag");
						enableFields("chkChkCompletedBy");
						//enableDisableCompletdFields(true);
					}	
					else
					{
						readOnlyFields("chkChkCompletedBy");
						readOnlyFields("chbbdmsWoprodaccepflag");
						readOnlyFields("dtebdmsProdaccepdate");
						readOnlyFields("spnbdmsProdacceptime");
						jQuery("#chkChkCompletedBy").attr("checked",false);
						jQuery("#chbbdmsWoprodaccepflag").attr("checked",false);
						readOnlyFields('chkChkCompletedBy');
						displayText('dtebdmsProdaccepdate',"");
						displayText('spnbdmsProdacceptime',"");
						enableDisableCompletdFields(false); 
						jQuery('#txtbdmsActualworktime').val('');
						jQuery('#txtbdmsDowntime').val('');
						jQuery("#txtbdmsDowntime").trigger("change");
					}
				 }  
			       
			     if(jQuery(this).is(':checked') == true)
				 {		


					   	displayText(setDateF,jQuery('#'+getDateF).datebox('getValue'));
					   	displayText(setTimeF,jQuery('#'+getTimeF).spinner('getValue'));
					   	if(jQuery(this).attr("id") == 'chbbdmsWoendflag')
					   		actualTimeCalculation();
					   	else if(jQuery(this).attr("id") == 'chbbdmsWostartflag')
						{
							if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
			        			actualTimeCalculation();
						}
					   	
				 } */
				 
			  /*    else
				 {
					 jQuery('#'+setDateF).datebox('clear');
					 jQuery('#'+setTimeF).spinner('clear');
					 if(jQuery(this).attr("id") == 'chbbdmsWoendflag' || jQuery(this).attr("id") == 'chbbdmsWostartflag')
					 {
						jQuery('#txtbdmsActualworktime').val('');
						jQuery('#txtbdmsDowntime').val('');
					 }
				 }
				 	
			      // }
			 });
	}
 */
 function setDatenTimeInCheckBoxSelect(chbId,setDateF,setTimeF,getDateF,getTimeF)
 {
 	jQuery('#'+chbId).click(function() {	

 		if(jQuery('#mode').val() == 'view') return;

 		// ---- FIX: enable/disable the date & time fields based on checkbox state ----
 		if(jQuery(this).is(':checked') == true)
 		{
 			enableFields(setDateF);
 			enableFields(setTimeF);
 		}
 		else
 		{
 			readOnlyFields(setDateF);
 			readOnlyFields(setTimeF);
 		}
 		// ------------------------------------------------------------------------

 		if(jQuery(this).attr("id") == "chbbdmsWoendflag" ){
 			if(jQuery('#chbbdmsWoendflag').is(':checked') == true){
 				enableFields("chbbdmsWoprodaccepflag");
 				enableFields("chkChkCompletedBy");
 			}	
 			else
 			{
 				readOnlyFields("chkChkCompletedBy");
 				readOnlyFields("chbbdmsWoprodaccepflag");
 				readOnlyFields("dtebdmsProdaccepdate");
 				readOnlyFields("spnbdmsProdacceptime");
 				jQuery("#chkChkCompletedBy").attr("checked",false);
 				jQuery("#chbbdmsWoprodaccepflag").attr("checked",false);
 				readOnlyFields('chkChkCompletedBy');
 				displayText('dtebdmsProdaccepdate',"");
 				displayText('spnbdmsProdacceptime',"");
 				enableDisableCompletdFields(false); 
 				jQuery('#txtbdmsActualworktime').val('');
 				jQuery('#txtbdmsDowntime').val('');
 				jQuery("#txtbdmsDowntime").trigger("change");
 			}
 		 }  
 	       
 	     if(jQuery(this).is(':checked') == true)
 		 {		
 			   	displayText(setDateF,jQuery('#'+getDateF).datebox('getValue'));
 			   	displayText(setTimeF,jQuery('#'+getTimeF).spinner('getValue'));
 			   	if(jQuery(this).attr("id") == 'chbbdmsWoendflag')
 			   		actualTimeCalculation();
 			   	else if(jQuery(this).attr("id") == 'chbbdmsWostartflag')
 				{
 					if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
 	        			actualTimeCalculation();
 				}
 			   	
 		 }
 	     else
 		 {
 			 jQuery('#'+setDateF).datebox('clear');
 			 jQuery('#'+setTimeF).spinner('clear');
 			 if(jQuery(this).attr("id") == 'chbbdmsWoendflag' || jQuery(this).attr("id") == 'chbbdmsWostartflag')
 			 {
 				jQuery('#txtbdmsActualworktime').val('');
 				jQuery('#txtbdmsDowntime').val('');
 			 }
 		 }
 	 });
 }
	function enableBDFieldsInCheckBoxSelect(chbId,chbId2)
	{
			jQuery('#'+chbId).click(function() {
			 		//alert(jQuery('#'+chbId).is(':checked'));
			 	if(jQuery('#'+chbId).is(':checked') == true)
				{
			 			if(jQuery('#'+chbId2).is(':checked') == true)
					 	{
			 				return true;
					 	}
			 			else
				 			return false;
				 	}
			 		
			 	});
	}


	function enableRepeatedBDInCheckBoxSelect(chbId,btnId)
	{
			jQuery('#'+chbId).click(function() {			 		
				
				if(jQuery('#mode').val() == 'view') return;
				if(jQuery('#'+chbId).is(':checked') == true)
				{					
					if(jQuery('#cmbbdmsAssemblyid').combobox('getValue') != null && jQuery('#cmbbdmsAssemblyid').combobox('getValue') != '')
					{
						var reportedDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');
						var idFlag = jQuery("#cmbbdmsKeyid").combobox('getValue');
						var dataStr = "?q=2&assmId="+jQuery('#cmbbdmsAssemblyid').combobox('getValue')+'&eqpID='+jQuery('#cmbbdmsMachineid').combobox('getValue')+'&repDate='+reportedDate;
						if(idFlag.substring(0,1) == 'U')
							dataStr += '&idFlag=UPM';				
						//reloadCombo("frmBDMaster",cmbId,"combo_repeatedbdno.Bbrdn"+dataStr);
						//enableFields(cmbId);
						enableUIButton(btnId);
						//if(jQuery('#txtbdanFinalaction').val() !=null || jQuery('#txtbdanFinalaction').val() != '' || jQuery('#txtbdanFinalaction').val() != ' ')
						  //jQuery('#txtbdanFinalaction').val('REPEAT BREAKDOWN');
					}
					else
					{
						jQuery('#'+chbId).attr('checked',false);
						alert('Select Assembly');
					}
				}
				else
				{		
					//jQuery('#'+cmbId).combobox('clear');
					addMandClass('lblPriority');
					addMandClass('lblMaintSect');
					addMandClass('lblFailType');
					addMandClass('lblPhen');
				//	addMandClass('lblBDClassfcn');
					addMandClass('lblBookedBy');
				//	addMandClass('lblAnalysedBy');
					jQuery('#txtbdanFinalaction').val('');					
					//readOnlyFields(cmbId);
					disableUIButton(btnId);
					jQuery("#txabdanRemarks").css('background-color', '#D1E2FD');					
					readOnlyFields('txabdanRemarks');
					removeMandClass('lblcomText');	
				}
						 		
			 	});
	}

	 function addMandClass(lblId)
	 {
		 jQuery('#'+lblId).addClass('mandatory-lbl');
	 }

	 function removeMandClass(lblId)
	 {
		 jQuery('#'+lblId).removeClass('mandatory-lbl');
	 }
          	 function sectionIdRecallSuccess(result)
          	 {	 
          		
	          		displayText('cmbbdmsFactoryid',result.sectionHierarchy.factory);
	          		jQuery('#cmbbdmsFactoryid').combobox('disable');
        		
          	 }

          	 function cellIdRecallSuccess(result)
          	 {
          		   	displayText('cmbbdmsFactoryid',result.cellHierarchy.factory);
	          	   	displayText('cmbbdmsSectionid',result.cellHierarchy.section);
	          	 	displayText('cmbbdanCostcentre',result.cellHierarchy.costcentre);          	 	          		
	          		jQuery('#cmbbdmsFactoryid').combobox('disable');
	          		jQuery('#cmbbdmsSectionid').combobox('disable');
	          		jQuery('#cmbbdanCostcentre').combobox('disable');
        		
             }
          	
 			
          	function  getShift(record)
          	{  
          		//var shift = getFieldValue("cmbbdmsShiftid");
              	//alert(shift.trim().length+" >><< "+record.shift);
              	 //alert(1111);
              	/* if(shift.trim().length==0)
              		setFieldValue('cmbbdmsShiftid',record.shift,"frmBDMaster");
              	 */ 
              	reloadCombo("frmBDMaster","cmbbdmsShiftid","combo_shift.Bbrdn" );
				jQuery('#cmbbdmsShiftid').combobox('setValue',record.shift); 
 				 
 				if( record.shift == undefined || record.shift == "" )  
 	 				alert("Unallocated Shift time!");
				 //readOnlyFields('cmbbdmsShiftid');           	
            }
     
			/* function  frmBDMastercmbbdmsFinalphenomena_onSelect(record)
          	{
	          	var assmid = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
	          //	if(assmid != null && assmid != '' && assmid != ' ')
		       // {
		      /* var finalPhenomena=jQuery('#cmbbdmsFinalphenomena').combobox('getText');
		       		if( finalPhenomena == 'OTHERS' )
			       		enableFields("txtbdmsOtherPhenomena");
			        else 
				    {
					    jQuery('#txtbdmsOtherPhenomena').val('');
		       			readOnlyFields("txtbdmsOtherPhenomena");
				    }*/
		          	/* if(jQuery('#cmbbdmsFinalphenomena').combobox('getText') == 'NOT DEFINED')
			        {
		          			jQuery('#phenName').val('Undefined');	          			
		          			readOnlyFields('txabdanCountermeasure');	
		          			 readOnlyFields('txabdanRootcause');
		          			jQuery("#txabdanCountermeasure").css('background-color', '#D1E2FD');
		          			jQuery("#txabdanRootcause").css('background-color', '#D1E2FD');		
		          			
			        }
		          	else
			        {
		          			//enableFields('txabdanCountermeasure');
		          			enableFields('txabdanRootcause');
		          			//jQuery("#txabdanCountermeasure").css('background-color', '#FFFFFF');
		          			jQuery("#txabdanRootcause").css('background-color', '#FFFFFF');
			        }
		          	jQuery('#cmbbdmsFinalcause').combobox('clear');
		          	reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId="+record.id );
	         		
	         		/* }
	          	else
		        {
		        	alert('Select Assembly');
         		  jQuery('#cmbbdmsFinalphenomena').combobox('clear');
		        }*/
          	//} */ */
          	//the above one is commented by priyannka the below added by priyanka
          	function  frmBDMastercmbbdmsFinalphenomena_onSelect(record)
          	{
	          	var assmid = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
	          //	if(assmid != null && assmid != '' && assmid != ' ')
		       // {
		      /* var finalPhenomena=jQuery('#cmbbdmsFinalphenomena').combobox('getText');
		       		if( finalPhenomena == 'OTHERS' )
			       		enableFields("txtbdmsOtherPhenomena");
			        else 
				    {
					    jQuery('#txtbdmsOtherPhenomena').val('');
		       			readOnlyFields("txtbdmsOtherPhenomena");
				    }*/
				    // added by priyanka 0n 09/07/2026
				    if(assmid == null || assmid == '' || assmid == ' ')
    				{
       	 				alert('Select Assembly');
        				jQuery('#cmbbdmsFinalphenomena').combobox('clear');
        				jQuery('#cmbbdmsFinalcause').combobox('clear');
        				return;
    				}
				    jQuery('#cmbbdmsFinalcause').combobox('clear');
				    //var ds = "phenId="+record.id;
				    var ds;
				    if(jQuery('#chkOtherCause').is(':checked') == true)
				        //ds += "&assmId="+assmid;
				    	ds = "assmId=" + assmid;
				    else
				    	ds = "phenId=" + record.id + "&assmId=" + assmid;	
				    reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?"+ds );
				    // end 
				    
		          	if(jQuery('#cmbbdmsFinalphenomena').combobox('getText') == 'NOT DEFINED')
			        {
		          			jQuery('#phenName').val('Undefined');	          			
		          			readOnlyFields('txabdanCountermeasure');	
		          			 readOnlyFields('txabdanRootcause');
		          			jQuery("#txabdanCountermeasure").css('background-color', '#D1E2FD');
		          			jQuery("#txabdanRootcause").css('background-color', '#D1E2FD');		
		          			
			        }
		          	else
			        {
		          			//enableFields('txabdanCountermeasure');
		          			enableFields('txabdanRootcause');
		          			//jQuery("#txabdanCountermeasure").css('background-color', '#FFFFFF');
		          			jQuery("#txabdanRootcause").css('background-color', '#FFFFFF');
			        }
				    // commented by priyanka on 11/07/2026
		          	//jQuery('#cmbbdmsFinalcause').combobox('clear');
		          	//reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn?phenId="+record.id );
	         		// end 
	         		/* }
	          	else
		        {
		        	alert('Select Assembly');
         		  jQuery('#cmbbdmsFinalphenomena').combobox('clear');
		        }*/
          	}
			function  frmBDMastercmbbdanFailuretype_onSelect(record)
          	{
	          /* var failureType=jQuery('#cmbbdanFailuretype').combobox('getText');
		       		if( failureType == 'OTHERS-O' )
			       		enableFields("txtbdanOtherFailuretype");
			        else
			        {
			        	jQuery('#txtbdanOtherFailuretype').val('');
		       			readOnlyFields("txtbdanOtherFailuretype");
			        }*/
	      	}
          
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

        	function setShiftEvt()
        	{          
				if( jQuery("#chbbdmsWoprodaccepflag").is(":checked") == true )
        			prodAcceptimeEvt();
				if( jQuery("#chbbdmsWostartflag").is(":checked") == true )
					woStartEvt();
        		  	   		
				var fromTime = jQuery('#spnbdmsReportedtime').spinner('getValue');
				//var factId = jQuery('#cmbbdmsFactoryid').combobox('getValue');
				//var sectId = jQuery('#cmbbdmsSectionid').combobox('getValue');
				//var cellId = jQuery('#cmbbdmsCellid').combobox('getValue');
				var factId = jQuery('#factory').val();
				var sectId = jQuery('#section').val();
				var cellId = jQuery('#cell').val();
				var flid = jQuery("#frmBDMaster input[id='flid']").val();
				var dataString = '?q=2&factId='+factId+'&sectId='+sectId;
				    dataString += '&cellId='+cellId+'&fromTime='+fromTime+"&flid="+flid;
				//alert('setShift : '+dataString);    				    				
				processAjaxCalls('txt_shift.Bbrdn',dataString,'getShift','getShiftErr');
		    }
          	
        	function receivedTimeEvt()
        	{            	   		
				dtebdmsReceiveddate_onSelect(null);
            }
        	function woStartEvt()
        	{        		
        		dtebdmsWostarttime_onSelect(null);
			}
        	function woEndEvt()
        	{        	
        		dtebdmsWoendtime_onSelect(getServerDateTime());								
				if(jQuery("#spnbdmsWostart").spinner('getValue') != null && jQuery("#spnbdmsWoend").spinner('getValue')!= null && jQuery("#spnbdmsReportedtime").spinner('getValue') != null)
				{					
					var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');				
					var wsDate = jQuery("#dtebdmsWostarttime").datebox('getValue') + jQuery("#spnbdmsWostart").spinner('getValue');
					var weDate = jQuery("#dtebdmsWoendtime").datebox('getValue') + jQuery("#spnbdmsWoend").spinner('getValue');
				
					actualMins = timeDifference(wsDate,weDate);	
					displayText('txtbdmsActualworktime',actualMins.minutes);										

					downTime = timeDifference(repDate,weDate);
					displayText('txtbdmsDowntime',downTime.minutes);								
				}
            }
			function prodAcceptimeEvt()
			{			
				dtebdmsProdaccepdate_onSelect(null);				
			}
			function completedBy()
			{
				dtebdmsCompleteddate_onSelect(null);
			}
          	function dtebdmsReceiveddate_onSelect(date)
          	{              	
				compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsReceiveddate','spnbdmsReceivedtime','Allotted Date should not be lesser than Occured Date');
		    }
        	function dtebdmsReceiveddate_onChange(date)
          	{     
        		//compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsReceiveddate','spnbdmsReceivedtime','Allotted Date should not be lesser than Occured Date');         	
        		//dtebdmsReceiveddate_onSelect(date);
		    }
		    

          /* 	function dtebdmsReporteddate_onSelect(date) 	
          	{
          		 var occrDt = jQuery('#dtebdmsReporteddate').datebox('getValue');				 
				 displayText('txtbdmsEntrydate',occrDt);
			}
 */
 function dtebdmsReporteddate_onSelect(date) 	
 {
     if(!validateOccurredDateTime())
         return;
     var occrDt = jQuery('#dtebdmsReporteddate').datebox('getValue');				 
     displayText('txtbdmsEntrydate',occrDt);
 }
          	/* function dtebdmsReporteddate_onChange(date) 	
          	{
          		 var occrDt = jQuery('#dtebdmsReporteddate').datebox('getValue');				 
				 displayText('txtbdmsEntrydate',occrDt);
				 //dtebdmsReporteddate_onSelect(date);
			}		 */  
			
			function dtebdmsReporteddate_onChange(date) 	
			{
			    if(!validateOccurredDateTime())
			        return;
			    var occrDt = jQuery('#dtebdmsReporteddate').datebox('getValue');				 
			    displayText('txtbdmsEntrydate',occrDt);
			}
        	/* function dtebdmsWostarttime_onSelect(date)
          	{
        		if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
        		{	
            		compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');
				
        			actualTimeCalculation();
        		}	
			} */
			//mano
			function dtebdmsWostarttime_onSelect(date)
{
    compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');

    if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
    {	
        actualTimeCalculation();
    }	
}
        	/* function dtebdmsWostarttime_onChange(date)
          	{
        		if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
        		{	compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');
        		
        			actualTimeCalculation();
        		}	
        		//dtebdmsWostarttime_onSelect(date);
			} */
			function dtebdmsWostarttime_onChange(date)
			{
			    compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');

			    if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
			    {
			        actualTimeCalculation();
			    }	
			}

        	function dtebdmsWoendtime_onSelect(date)
          	{
              
        		compareDates('dtebdmsWostarttime','spnbdmsWostart','dtebdmsWoendtime','spnbdmsWoend','Work End should not be lesser than Work Start');
        		actualTimeCalculation();
        		
		    }
        	function dtebdmsWoendtime_onChange(date)
          	{
              
        		compareDates('dtebdmsWostarttime','spnbdmsWostart','dtebdmsWoendtime','spnbdmsWoend','Work End should not be lesser than Work Start');
        		actualTimeCalculation();
        		
		    }
        	function dtebdmsCompleteddate_onSelect(date)
          	{
            //  alert('j');
        		compareDates('dtebdmsCompleteddate','spnbdmsCompletedtime','dtebdmsWoendtime','spnbdmsWoend','Completed date should not be lesser than Work End');
        		
		    }
        	function validateOccurredDateTime()
        	{
        	    clearValidationErrorMsg('dtebdmsReporteddate');

        	    var occDate = jQuery('#dtebdmsReporteddate').datebox('getValue');
        	    var occTime = getPaddedSpinnerValue('spnbdmsReportedtime');

        	    if(occDate == '' || occDate == null || occTime == '' || occTime == null)
        	        return true; // nothing to validate yet

        	    var occDateTime = occDate + occTime;
        	    var currentDate = getServerDateTime();

        	    if(convertStringToDate(occDateTime) > currentDate)
        	    {
        	        alert('Occurred Date/Time cannot be a future date/time');
        	        showValidationErrorMsg('dtebdmsReporteddate','Should Not Exceed Current Date/Time');

        	        // Retain current date/time instead of the future value picked
        	        fillWithCurrentDate('dtebdmsReporteddate');
        	        fillWithCurrentDate('spnbdmsReportedtime');

        	        var occrDt = jQuery('#dtebdmsReporteddate').datebox('getValue');
        	        displayText('txtbdmsEntrydate', occrDt);

        	        return false;
        	    }
        	    return true;
        	}
        	
        	function dtebdmsCompleteddate_onChange(date)
          	{
             // alert('n');
        		compareDates('dtebdmsCompleteddate','spnbdmsCompletedtime','dtebdmsWoendtime','spnbdmsWoend','Completed date should not be lesser than Work End');
        	}

        	function dtebdmsProdaccepdate_onSelect(date)
          	{
              	
        		compareDates('dtebdmsWoendtime','spnbdmsWoend','dtebdmsProdaccepdate','spnbdmsProdacceptime','Production Date should not be lesser than Work End Date');
        		downTimeCalculation();
        						
		    }
        	function dtebdmsProdaccepdate_onChange(date)
          	{
              	
        		compareDates('dtebdmsWoendtime','spnbdmsWoend','dtebdmsProdaccepdate','spnbdmsProdacceptime','Production Date should not be lesser than Work End Date');

        		downTimeCalculation();
        		//dtebdmsProdaccepdate_onSelect(date);				
		    }

		    function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
		    {
			   //alert('KK');
			   clearValidationErrorMsg(fromDateId);
			   clearValidationErrorMsg(toDateId);
			    var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
			    var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
			    var fDt = jQuery('#'+fromDateId).datebox('getValue');
			    var fTi = jQuery('#'+fromTimeId).spinner('getValue');
			    var tDt = jQuery('#'+toDateId).datebox('getValue') ;
			    var tTi = jQuery('#'+toTimeId).spinner('getValue') ;
			  //  alert(fromDate+"   "+toDate+"  "+fTi+"  "+ tTi);
			    if( fDt == ""  || fTi == "" || tDt == ""  || tTi == "")
				    return ;
				    
			 	//alert(fromDate + ' : '+toDate);
				var currentDate = getServerDateTime();
				//alert();
				if(convertStringToDate(toDate) > currentDate)
					{
						showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
			        	fillWithCurrentDate(toDateId);			
						fillWithCurrentDate(toTimeId);
						return ;

					}
				else if( compareDateTime(toDate,fromDate) > 0 )
			    {
		        	showValidationErrorMsg(toDateId,errMsg);
		        	displayText(toDateId,fDt);
		        	displayText(toTimeId,fTi);
		        	//fillWithCurrentDate(toDateId);			
					//fillWithCurrentDate(toTimeId);
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

			/* function actualTimeCalculation()
			{
				if(jQuery("#spnbdmsWostart").spinner('getValue') != null && jQuery("#spnbdmsWoend").spinner('getValue')!= null && jQuery("#spnbdmsReportedtime").spinner('getValue') != null)
				{					
					var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');				
					var wsDate = jQuery("#dtebdmsWostarttime").datebox('getValue') + jQuery("#spnbdmsWostart").spinner('getValue');
					var weDate = jQuery("#dtebdmsWoendtime").datebox('getValue') + jQuery("#spnbdmsWoend").spinner('getValue');
					
					actualMins = timeDifference(wsDate,weDate);	
					if(parseInt(actualMins.minutes,10)>=0)
					displayText('txtbdmsActualworktime',actualMins.minutes);														
					var downTime = 0;
					var breakTime = jQuery("#txtbdmsBreaktime").val();
					mins = timeDifference(repDate,weDate);	
					if(parseInt(mins.minutes,10)>=0)					
						 downTime = mins.minutes;//displayText('txtbdmsDowntime',mins.minutes);
						 
					 if(breakTime != ''  && breakTime != null )
					 {
						 if(downTime != ''  && downTime != null )
						 {
						 	downTime = parseInt(downTime,10) - parseInt(breakTime,10);
						 }
						 
						  if(parseInt(downTime,10) < 0)
						  {
							  jQuery("#txtbdmsBreaktime").val('');
						  }
					  }

					 if(downTime != ''  && downTime != null && downTime != 'NaN'){
					 	displayText('txtbdmsDowntime',downTime);
					 	jQuery("#txtbdmsDowntime").trigger("change");
					 }	

					//downTime = timeDifference(repDate,weDate);
					//if(parseInt(downTime.minutes)>=0)
						//displayText('txtbdmsDowntime',downTime.minutes);								
				}
			}
			 */
			 function getPaddedSpinnerValue(spinnerId)
			 {
			     var val = jQuery('#'+spinnerId).spinner('getValue');
			     if(val && val.indexOf(':') == 1){     // e.g. "8:06" -> "08:06"
			         val = '0' + val;
			     }
			     return val;
			 }
		/* 	 function actualTimeCalculation()
			 {
			     if(jQuery("#spnbdmsWostart").spinner('getValue') != null && jQuery("#spnbdmsWoend").spinner('getValue')!= null && jQuery("#spnbdmsReportedtime").spinner('getValue') != null)
			     {					
			         var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');				
			         var wsDate = jQuery("#dtebdmsWostarttime").datebox('getValue') + jQuery("#spnbdmsWostart").spinner('getValue');
			         var weDate = jQuery("#dtebdmsWoendtime").datebox('getValue') + jQuery("#spnbdmsWoend").spinner('getValue');
			         
			         actualMins = timeDifference(wsDate,weDate);	
			         if(parseInt(actualMins.minutes,10)>=0)
			         displayText('txtbdmsActualworktime',actualMins.minutes);														
			         var downTime = 0;
			         var breakTime = jQuery("#txtbdmsBreaktime").val();

			         // Guard against a negative or non-numeric break time --
			         // subtracting a negative value silently INFLATES downtime
			         // instead of reducing it.
			         var breakMins = parseInt(breakTime, 10);
			         if(isNaN(breakMins) || breakMins < 0){
			             breakMins = 0;
			             jQuery("#txtbdmsBreaktime").val('');
			         }

			         mins = timeDifference(repDate,weDate);	
			         if(parseInt(mins.minutes,10)>=0)					
			              downTime = mins.minutes;
			              
			          downTime = parseInt(downTime,10) - breakMins;
			          if(parseInt(downTime,10) < 0)
			          {
			               jQuery("#txtbdmsBreaktime").val('');
			          }

			          if(downTime != ''  && downTime != null && downTime != 'NaN'){
			             displayText('txtbdmsDowntime',downTime);
			             jQuery("#txtbdmsDowntime").trigger("change");
			          }	
			     }
			 }
			function downTimeCalculation()
			{
				if(jQuery("#spnbdmsReportedtime").spinner('getValue') != null && jQuery("#spnbdmsProdacceptime").spinner('getValue')!= null && jQuery("#spnbdmsWoend").spinner('getValue')!= null)
				{		
					var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');
					var prodAcceptedDate = jQuery("#dtebdmsProdaccepdate").datebox('getValue') + jQuery("#spnbdmsProdacceptime").spinner('getValue');
					var breakTime = jQuery("#txtbdmsBreaktime").val();
					var downTime = 0;
					mins = timeDifference(repDate,prodAcceptedDate);	
					if(parseInt(mins.minutes)>=0)					
						 downTime = mins.minutes;//displayText('txtbdmsDowntime',mins.minutes);
						 
					 if(breakTime != ''  && breakTime != null )
					 {
						 downTime = parseInt(downTime,10) - parseInt(breakTime,10);
						
						  if(parseInt(downTime,10) < 0)
						  {
							  jQuery("#txtbdmsBreaktime").val('');
							  jQuery("#txtbdmsDowntime").trigger("change");
						  }
					  }
					 
					 if(downTime != ''  && downTime != null && downTime != 'NAN'){
					 	displayText('txtbdmsDowntime',downTime);
					 	jQuery("#txtbdmsDowntime").trigger("change");
					 }	
					
				}
			}
 */
 function actualTimeCalculation()
 {
     if(jQuery("#spnbdmsWostart").spinner('getValue') != null && jQuery("#spnbdmsWoend").spinner('getValue')!= null && jQuery("#spnbdmsReportedtime").spinner('getValue') != null)
     {					
         var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + getPaddedSpinnerValue('spnbdmsReportedtime');				
         var wsDate = jQuery("#dtebdmsWostarttime").datebox('getValue') + getPaddedSpinnerValue('spnbdmsWostart');
         var weDate = jQuery("#dtebdmsWoendtime").datebox('getValue') + getPaddedSpinnerValue('spnbdmsWoend');
         
         actualMins = timeDifference(wsDate,weDate);	
         if(parseInt(actualMins.minutes,10)>=0)
         displayText('txtbdmsActualworktime',actualMins.minutes);														
         var downTime = 0;
         var breakTime = jQuery("#txtbdmsBreaktime").val();
         var breakMins = parseInt(breakTime, 10);
         if(isNaN(breakMins) || breakMins < 0){
             breakMins = 0;
             jQuery("#txtbdmsBreaktime").val('');
         }

         mins = timeDifference(repDate,weDate);	
         if(parseInt(mins.minutes,10)>=0)					
              downTime = mins.minutes;
              
          downTime = parseInt(downTime,10) - breakMins;
          if(parseInt(downTime,10) < 0)
          {
               jQuery("#txtbdmsBreaktime").val('');
          }

          if(downTime != ''  && downTime != null && downTime != 'NaN'){
             displayText('txtbdmsDowntime',downTime);
             jQuery("#txtbdmsDowntime").trigger("change");
          }	
     }
 }

 function downTimeCalculation()
 {
     if(jQuery("#spnbdmsReportedtime").spinner('getValue') != null && jQuery("#spnbdmsProdacceptime").spinner('getValue')!= null && jQuery("#spnbdmsWoend").spinner('getValue')!= null)
     {		
         var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + getPaddedSpinnerValue('spnbdmsReportedtime');
         var prodAcceptedDate = jQuery("#dtebdmsProdaccepdate").datebox('getValue') + getPaddedSpinnerValue('spnbdmsProdacceptime');
         var breakTime = jQuery("#txtbdmsBreaktime").val();
         var breakMins = parseInt(breakTime, 10);
         if(isNaN(breakMins) || breakMins < 0){
             breakMins = 0;
             jQuery("#txtbdmsBreaktime").val('');
         }
         var downTime = 0;
         mins = timeDifference(repDate,prodAcceptedDate);	
         if(parseInt(mins.minutes)>=0)					
              downTime = mins.minutes;

          downTime = parseInt(downTime,10) - breakMins;
          if(parseInt(downTime,10) < 0)
          {
               jQuery("#txtbdmsBreaktime").val('');
               jQuery("#txtbdmsDowntime").trigger("change");
          }
          
          if(downTime != ''  && downTime != null && downTime != 'NAN'){
             displayText('txtbdmsDowntime',downTime);
             jQuery("#txtbdmsDowntime").trigger("change");
          }	
     }
 }
			 function downTimeGrid(){	

				fillDwnTimeFields();								 	  
		    	/*jQuery("#DwnTmeBrkupGrid").setGridParam({
			    		
						afterSaveCell : function(rowid,name,val,iRow,iCol) 
				    	{ 					    	
							if(name == 'txtbdssNoplantime')
					    	{						    	
					    		var downTime = jQuery("#DwnTmeBrkupGrid").jqGrid('getCell',rowid,iCol+1);
					    		var maxTime = jQuery("#DwnTmeBrkupGrid").jqGrid('getCell',rowid,iCol+3);					    	
						    	var x = parseInt(val)+parseInt(downTime);
						    	if(parseInt(x) > parseInt(maxTime))
							    {
								    alert('Should Not Exceed max Allowed Time');
								    jQuery("#DwnTmeBrkupGrid").jqGrid('setRowData',rowid,{txtbdssNoplantime:'0'});
							    }
						    	else
							    {
									jQuery("#DwnTmeBrkupGrid").jqGrid('setRowData',rowid,{txtTotalDownTime:x});
					    	    	jQuery("#DwnTmeBrkupGrid").jqGrid('setCell',rowid,"txtTotalDownTime","",{'background-color':'#2f6fd3','color':'#ffffff','font-weight':'bold'});
					    	    	fillDwnTimeFields();
							    }
					    	}
				    		if(name == 'txtbdssDowntime')
					    	{
					    		var noPlan = jQuery("#DwnTmeBrkupGrid").jqGrid('getCell',rowid,iCol-1);
					    		var maxTime = jQuery("#DwnTmeBrkupGrid").jqGrid('getCell',rowid,iCol+2);
						    	var x = parseInt(val)+parseInt(noPlan);
						    	if(parseInt(x)>parseInt(maxTime))
							    {
						    		alert('Should Not Exceed max Allowed Time');
						    		jQuery("#DwnTmeBrkupGrid").jqGrid('setRowData',rowid,{txtbdssDowntime:'0'});
							    }
						    	else
							    {
									jQuery("#DwnTmeBrkupGrid").jqGrid('setRowData',rowid,{txtTotalDownTime: x });
					    	  	    jQuery("#DwnTmeBrkupGrid").jqGrid('setCell',rowid,"txtTotalDownTime","",{'background-color':'2f6fd3','color':'#ffffff','font-weight':'bold'});
					    	  	    fillDwnTimeFields();
							    }
					    	    
					    	}
					    }
		    	
		    	});*/
		    }

			jQuery('#chbbdmsWoprodaccepflag').click(function() {				 
				 var endDt = jQuery('#dtebdmsWoendtime').datebox('getValue');
				 var endTime = jQuery('#spnbdmsWoend').spinner('getValue');
				 
				 if(jQuery("#chbbdmsWoprodaccepflag").is(':checked') == true ){
				 	enableFields("chkChkCompletedBy");
				 	enableFields("dtebdmsProdaccepdate");
				 	enableFields("spnbdmsProdacceptime");
				 	//jQuery("#chkChkCompletedBy").attr("checked",true);
				 	displayText('dtebdmsProdaccepdate',endDt);
					displayText('spnbdmsProdacceptime',endTime);	
				 }	
				 else{
					jQuery("#chkChkCompletedBy").attr("checked",false);
					//readOnlyFields('chkChkCompletedBy');
					//enableDisableCompletdFields(false); 
					displayText('dtebdmsProdaccepdate',"");
					 displayText('spnbdmsProdacceptime',"");
					readOnlyFields('dtebdmsProdaccepdate');
					readOnlyFields('spnbdmsProdacceptime');
				 }	
				 prodAcceptimeEvt();
			});

			function fillDwnTimeFields()
			{
				 var downTimeId = jQuery("#DwnTmeBrkupGrid").jqGrid('getDataIDs');
				 var countDT = 0;
				 var countTDT = 0;
				 var countMAT = 0;
				 var countNPT = 0;
				 for(i=1;i<=downTimeId.length;i++)	
				 {			 
					 countDT += parseInt(jQuery("#DwnTmeBrkupGrid").getCell(i, 'txtbdssDowntime'));					 
					 countTDT += parseInt(jQuery("#DwnTmeBrkupGrid").getCell(i, 'txtTotalDownTime'));
					 countMAT += parseInt(jQuery("#DwnTmeBrkupGrid").getCell(i, 'txtMaxallowedtime'));
					 countNPT += parseInt(jQuery("#DwnTmeBrkupGrid").getCell(i, 'txtbdssNoplantime'));					
				 }	
				if(countMAT != null || countDT != null || countNPT != null)
				{
					 jQuery("#downTime").val(countMAT);
					 jQuery("#breakupTime").val(countDT);
					 jQuery("#balTime").val(countMAT-countDT);
					 jQuery("#downTimeMins").val(countDT);
					 jQuery("#noPlanMins").val(countNPT);
				}
				
			}
			function showDowntime()
			{
				   var fromDate = jQuery('#dtebdmsReporteddate').datebox('getValue') + ' '+jQuery('#spnbdmsReportedtime').spinner('getValue');
				   var toDate = jQuery('#chbbdmsWoprodaccepflag').is(':checked') == true?jQuery('#dtebdmsProdaccepdate').datebox('getValue') + ' '+jQuery('#spnbdmsProdacceptime').spinner('getValue'):jQuery('#dtebdmsWoendtime').datebox('getValue') + ' '+jQuery('#spnbdmsWoend').spinner('getValue');
				   var downTimedata = '?q=2&factId='+jQuery('#factory').val()+'&fromTime='+fromDate+'&toTime='+toDate;								
				   processGridnew('downtime_view.Bbrdn',downTimedata,"DwnTmeBrkupGrid","DwnTmeBrkupPager","","","","downTimeGrid","");
			}
		/*	function getCom(result)
			{	
			
				alert(result.successData.msg);				
				jQuery("#CommnGrid").trigger("reloadGrid");
				jQuery("#txawcmlCommunicationtext").val(''); 
			}*/

			function changeFieldColor(fieldId,chbId)
			{
				jQuery('#'+chbId).click(function() {
					if(jQuery('#'+chbId).is(':checked') == true)
						jQuery("#"+fieldId).css('background-color', '#FFFFFF');
					else
						jQuery("#"+fieldId).css('background-color', '#D1E2FD');
				});
			}
			function frmBDMaster_beforeDelete()
			{
				var delActivity = jQuery('#hdndelBDMode').val();				
				if(delActivity == 'Y')
				{
					alert('Activity Cannot be deleted');
					return false;
				}
					
				var delMsg = "Do You Want To Delete This Breakdown ("+jQuery('#cmbbdmsKeyid').combobox('getValue')+")";
				if(confirm(delMsg) == false)
				{
							return false;
				}
			}
			function frmBDMaster_deleteSuccessCallback(result)
			{
				alert(result.successData.msg);
				navigateToPrevForm();
			}

			 function frmBDMaster_successsCallback(result)
			 {
				 var mode = result.formMode;	
				 var phenFlag = result.PhenomenaFlag;
				 var filemanager =result.successData.filemanager;
				
				 
				 jQuery("#txtbdmsWno").val( result.successData.womsKeyid);
				 
				 if( result.successData.keyId != undefined )
				 	jQuery("#cmbbdmsKeyid").combobox('setValue', result.successData.keyId);	
				 
				 if( jQuery("#chkChkCompletedBy").is(':checked') == true)
					 jQuery("#hdnBdmsStatus").val("C");		
				
				 if( jQuery("#chkbdSubmittoSap").is(':checked') == true){
					 var qty=null;
					
					 var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');							
						for( var i = 1; i <= allRows.length;i++){
							
						 qty=jQuery('#txtSspmQuantity_'+i).val();						 
						
						}
						if(qty>=1){
							 var sapMsg = "Do You Want To Submit To SAP ";// This Breakdown ("+jQuery('#cmbbdmsKeyid').combobox('getValue')+")";
							 
							 if(confirm(sapMsg) == false)
							{
									return false;
							}
							 else {				
									
								   show_winMask(1);
								   jQuery(".save-loading").html("Please wait while submitting...").show();
									
								//var bdKeyId=jQuery('cmbbdmsKeyid').combobox('getValue');
													 
							 	submitToSap("Breakdown", "BD_ORDER_CREATE", result.successData.keyId);
							 	/*setTimeout(function() {
							 	var bdKeyId =getFieldValue("cmbbdmsKeyid","frmBDMaster");
								jQuery('#txtSapOrderNo').val(bdKeyId);
							 	//processAjaxCalls("updateTransaction.Bbrdn","tranId="+result.successData.keyId+"&status="+jQuery("#txtErrppostStatus").val(),"updtBdsatatus_successcalbk","updatestatus_errorcalbk",null,result.transId,true);
							 	},60000);*/
						 		 jQuery(".save-loading").hide();
						 		   show_winMask(0);
									
							 }	
					
							 }
								else {alert('Enetr the Quantity'); 			 	 	
								
							}
						}						
						
				
				 	// var sta= jQuery("#grdSAPSubmitQueue").jqGrid().getCell(1,"txtStatus");

					 //	alert('test////////'+sta);
					 	/*
					 	setTimeout(function() {	
				 		//postSAPSubmitQueue_successcalbk(result);
				 		alert('Hiiii');
				 		 var sta= jQuery("#grdSAPSubmitQueue").jqGrid().getCell(1,"txtStatus");
						 var TranId=jQuery("#grdSAPSubmitQueue").jqGrid().getCell(1,"txtTransId");
						 alert('Hiiii  '+TranId);
							if(sta==true){
								alert('result success');
								}
							else 
								alert('result failled');
					 	alert('Hi'+sta);
				 		//saveForm('frmBDMaster','Breakdown_unsap.Bbrdn');//var res=result;	
				 		var str=res.toString();
				 		alert(res+"result");
				 		alert(str+"  String");		 	
					 	if(result.exception==true ){
					 	alert('if process'+ result.exception);
						  var sta= jQuery("#grdSAPSubmitQueue").jqGrid('getCell', rowId, 'txtStatus');//.'getCell', rowId, 'Tax'getCell(result.rowId,"txtStatus");
							alert('Status    '+sta);
						 
					   }
					   else{
						  // jQuery("#btnSapQAction_"+result.errMsg.rowId).addClass("blink-success");
						var status= jQuery("#grdSAPSubmitQueue").jqGrid().getCell(result.rowId,"txtStatus");//,"Success");
							alert("Status...From Sap    "+status);
						 var procee=  jQuery("#grdSAPSubmitQueue").jqGrid().getCell(result.rowId,"txtTransId");//,result.msg);
						 alert("processId...From Sap    "+procee);

						   //jQuery("#btnSapQAction_"+result.rowId).val('Submitted');
					   }		 	
				 	//getSapData(status,transactionId){
				 	//jQuery('frmBDmaster').attr('readOnly', true);*/
				 
				 	//;},50550);
				 	
				 	//jQuery('frmBDmaster').setReadOnly(true);
				 	//jQuery('#frmBDmaster').prop('disabled',true); 
			/*	 if(phenFlag != null && phenFlag == "ND")()
				 {
					
					 var msg = "This Breakdown has Undefined Phenomena\nDo You want to raise the proposal";					
					 if(confirm(msg) == true)
					 {						  
						  var persistentData = result.persistentData;
						  var forwardData = result.forwardData;
						 // navigateToNextForm('undefined_input.upm','Undefined Phenomena',forwardData,persistentData );
							 var names = Object.keys(forwardData);
							 var fData ="";
							for(var i=0;i<names.length;i++ )
							{
								
								fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
							}
							 //navigateToNextForm('undefined_input.upm','Undefined Phenomena',forwardData,persistentData );
							 LoadPopUp("divBDUndefineph", 'undefined_input.upm?'+fData, true,null,"100%","10px",null, null, "Undefined Phenomena",false, true,true);
					 }
					 else
					 {
						 if( mode != null && mode == "undefinedPhn")
						 {
							 var persistentData = result.persistentData;
							 var forwardData = result.forwardData;	
							 var names = Object.keys(forwardData);
							 var fData ="";
							for(var i=0;i<names.length;i++ )
							{
								
								fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
							}
							 //navigateToNextForm('undefined_input.upm','Undefined Phenomena',forwardData,persistentData );
							 LoadPopUp("divBDUndefineph", 'undefined_input.upm?'+fData, true,null,"100%","10px",null, null, "Undefined Phenomena",false, true,true);
						 }
						 else if(mode != null && mode == "yy")	
						 {
							
//							 var persistentData = result.persistentData;
							 var forwardData = result.forwardData;
							 var names = Object.keys(forwardData);
							 var fData ="";
								for(var i=0;i<names.length;i++ )
								{
									
									fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
								}
							 LoadPopUp("divBDWhywhy", 'whywhyanalysismodify_input.why?'+fData, true,null,"100%","10px",null, null, "Why Why Analysis",false, true,true);											
							 //navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );
						 } 
						 else if(mode != null && (mode == "estimate" || mode == "actual"))	
						 {
							 var persistentData = result.persistentData;
							 var forwardData = result.forwardData;	
							 var formTitle;
							 if (mode == "estimate")
								 formTitle = "Cost Information - Estimation";
							else
								 formTitle = "Cost Information - Actual";

							 names = Object.keys(forwardData);
							 var fData ="";
								for(var i=0;i<names.length;i++ )
								{
									
									fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
								}
							// navigateToNextForm('costSummary_input.crt',formTitle,forwardData,persistentData );
							 LoadPopUp("divBDCostinfo", 'costSummary_input.crt?'+fData, true,null,"100%","10px",null, null, formTitle,false, true,true);
						 } 
						 else				 
							 navigateToPrevForm();// dateFiledFill();
					 }
			     }
				 else
				 {
					*/ 
					//mano has commented 
					//LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.Bbrdn?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");
					
					if(filemanager == true ){
						 var keyid = result.successData.keyId;
						 fileManagerPopUp(keyid,"BDM","","","");

					 }
					
					
					else if( mode != null && mode == "undefinedPhn")
					 {
						 var persistentData = result.persistentData;
						 var forwardData = result.forwardData;	
						 names = Object.keys(forwardData);
						 var fData ="";
							for(var i=0;i<names.length;i++ )
							{
								
								fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
							}
						 LoadPopUp("divBDUndefineph", 'undefined_input.upm?'+fData, true,null,"90%","10px",null, null, "Undefined Phenomena",false, true,true);
						 //navigateToNextForm('undefined_input.upm','Undefined Phenomena',forwardData,persistentData );
					 }
					 else if(mode != null && mode == "yy")	
					 {
						
						 var persistentData = result.persistentData;
						 var forwardData = result.forwardData;	
						 var names = Object.keys(forwardData);
						 var fData ="";
							for(var i=0;i<names.length;i++ )
							{
								
								fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
							}
							var repeatedBd = "N";
							if( jQuery('#chbbdmsRepeatedbdflag').is(':checked') )
								repeatedBd = "Y";
							var flid = jQuery("#frmBDMaster input[id='flid']").val();
							fData += "&flid="+flid +"&flId="+flid + "&problem=" +escape( jQuery("#txtbdmsProblemdescription").val());
							//fData += "&repeatedYYid=" + jQuery("#hdnBdmsRepeatedbdWhWhyNo").val() + "&repeatedBd="+repeatedBd;
							var mode = "";
						if( jQuery("#hdnBdmsStatus").val() =="C")
							mode ="view";
						fData += "&yymode="+mode;
						var finalAction = jQuery('#txtbdanFinalaction').val();
						var immediateAction=jQuery("#txtbdmsImmediateaction").val();
						fData += "&finalAction="+escape(finalAction)+"&immediateAction="+escape(immediateAction);
						 LoadPopUp("divBDWhywhy", 'whywhyanalysismodify_input.balwhy?'+fData, true,"95%","90%","10px",null, null, "Why Why Analysis",false, true,true);				
	//					 navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );
					 } 
					 else if(mode != null && (mode == "estimate" || mode == "actual"))	
					 {
						 var persistentData = result.persistentData;
						 var forwardData = result.forwardData;	
						 var formTitle;
						 if (mode == "estimate")
							 formTitle = "Cost Information - Estimation";
						else
							 formTitle = "Cost Information - Actual";


						 var names = Object.keys(forwardData);
						 var fData ="";
							for(var i=0;i<names.length;i++ )
							{
								
								fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
							}
						// navigateToNextForm('costSummary_input.crt',formTitle,forwardData,persistentData );
						 LoadPopUp("divBDCostinfo", 'costSummary_input.crt?'+fData, true,"95%","90%","10px",null, null, formTitle,false, true,true);
						
	//					 navigateToNextForm('costSummary_input.crt',formTitle,forwardData,persistentData );
					 } 
					 else	
						 {	
							 var backTo = result.BACKTO;
							
							 if(backTo != null && backTo != '' && backTo != ' ')
						 		popFormNavigation();
							//popFormNavigation();		 
							//navigateToPrevForm();// dateFiledFill();
						 }
					 jQuery("#btnFilManage").click(function(){
						  
						    var documentNo =result.successData.bdmsKeyId;
							
							if(documentNo != null && documentNo != ''){
								fileManagerPopUp(documentNo,"BDM","","","");
							}
					 });
			 }
				 
			function frmBDMaster_exceptionCallback() {
				//alert("error");
			}
			
          	function frmBDMaster_phencauseLinkCallBack(result)
          	{
          	  var dString = "";              
              if(jQuery('#cmbbdmsAssemblyid').combobox('getValue') != null && jQuery('#cmbbdmsAssemblyid').combobox('getValue') != '' && jQuery('#cmbbdmsAssemblyid').combobox('getValue') != ' ')
           	      dString += "&assmId="+jQuery('#cmbbdmsAssemblyid').combobox('getValue');
              	//reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn"+dString);              	
              	reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn"+dString );              
            }

			/* function sparesCheckSel(sparesFlag)
			{
				if(sparesFlag == 'Y')
				{
					jQuery('#chkIssparesY').attr('checked',true);
					jQuery('#chkIssparesN').attr('checked',false);
					jQuery('#chkIssparesW').attr('checked',false);
				}
				else if(sparesFlag == 'N')
				{
					jQuery('#chkIssparesY').attr('checked',false);
					jQuery('#chkIssparesN').attr('checked',true);
					jQuery('#chkIssparesW').attr('checked',false);
				}
				else if(sparesFlag == 'W')
				{
					jQuery('#chkIssparesY').attr('checked',false);
					jQuery('#chkIssparesN').attr('checked',false);
					jQuery('#chkIssparesW').attr('checked',true);
				}
				else
				{
					jQuery('#chkIssparesY').attr('checked',false);
					jQuery('#chkIssparesN').attr('checked',true);
					jQuery('#chkIssparesW').attr('checked',false);
				}
			} */
			//mano
			function sparesCheckSel(sparesFlag)
{
    // reset all first, same as abnormality style
    jQuery('#chkIssparesY').prop('checked', false);
    jQuery('#chkIssparesN').prop('checked', false);
    jQuery('#chkIssparesW').prop('checked', false);

    if(sparesFlag == 'Y')
    {
        jQuery('#chkIssparesY').prop("checked","checked");
    }
    else if(sparesFlag == 'N')
    {
        jQuery('#chkIssparesN').prop("checked","checked");
    }
    else if(sparesFlag == 'W')
    {
        jQuery('#chkIssparesW').prop("checked","checked");
    }
    else
    {
        // default fallback same as before
        jQuery('#chkIssparesN').prop("checked","checked");
    }
}

			/*function actionFormatterAM(cellvalue, options, rowObject) 
			{
				var rowId = options.rowId;
				var formatStr  = '<span id="am_'+rowId+'"' ;
					formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
					formatStr  +=  '</span>';
				return formatStr;	
			}
			function actionFormatterPM(cellvalue, options, rowObject) 
			{
				var rowId = options.rowId;
				var formatStr  = '<span id="pm_'+rowId+'"' ;
					formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
					formatStr  +=  '</span>';
				return formatStr;
			}
			function actionFormatterCI(cellvalue, options, rowObject) 
			{
				var rowId = options.rowId;
				var formatStr  = '<span id="ci_'+rowId+'"' ;
					formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
				    formatStr  +=  '</span>';
				return formatStr;
			}
			function actionFormatterET(cellvalue, options, rowObject) 
			{
				var rowId = options.rowId;
				var formatStr  = '<span id="et_'+rowId+'"' ;
					formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
					formatStr  +=  '</span>';
				return formatStr;
			}
			function selectedRC(id) 
			{
				
				var formatStr  = '<span id="selectedRC"' ;
					formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
					formatStr  +=  '</span>';
				return formatStr;
			}*/
			/*function yyComplete()
			{
			
				if(jQuery('#txtbdanWwno').val() != null && jQuery('#txtbdanWwno').val() != '' && jQuery('#txtbdanWwno').val() != ' ')
					processAjaxCalls('setrc_values.Bbrdn','?q=2&yyNO='+jQuery('#txtbdanWwno').val(),'setRCSuccess');
				else
					processGridnew('rootcause_view.Bbrdn','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"rootCauseGrid","rootCausePager","","","","RootCauseComplete","");
			}
			function setRCSuccess(result)
			{
				//if(result.rcId != null)
				jQuery("#hdnRCID").val(result.rcId);
				jQuery("#hdnIsJh").val(result.isJH);
				jQuery("#hdnIsPM").val(result.isPM);
				jQuery("#hdnIsCI").val(result.isCI);
				jQuery("#hdnIsET").val(result.isET);
				processGridnew('rootcause_view.Bbrdn','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"rootCauseGrid","rootCausePager","","","","RootCauseComplete","");
			}*/
			function classificn(result)
			{				
				if(result.isJH == 'Y')
					jQuery("#pillar").val('JH');
				else if(result.isPM == 'Y')
					jQuery("#pillar").val('PM');
				else if(result.isCI == 'Y')
					jQuery("#pillar").val('KK');
				if(result.isET == 'Y')
					jQuery("#pillar").val('ET');		
				
				processAjaxCalls('check_BDclassfcn.Bbrdn','?q=2&pillar='+jQuery("#pillar").val()+'&classificationId='+jQuery('#cmbbdanClassificationid').combobox('getValue'),'checkClassification');
				
			}
			function checkClassification(result)
			{
				
				if(result.msg != null && result.msg != '')
				{
					alert(result.msg);
					jQuery('#cmbbdanClassificationid').combobox('clear');
					reloadCombo("frmBDMaster","cmbbdanClassificationid","combo_bdclfcn.Bbrdn?q=2&pillar="+jQuery("#pillar").val());
				}
			}
			
			function frmBDMaster_beforeSubmit()
			
			{
				var mode = jQuery('#mode').val();
				if(mode == 'view')
				{
					alert("This Breakdown is open in View mode. Changes cannot be saved.");
					return false;
				}
				
				var machId = jQuery("#cmbbdmsMachineid").combobox('getValue');
				if(machId == null || jQuery.trim(machId) == ''){
					alert('Please select the Equipment');
					return false;
				}

				var problemDesc = jQuery("#txtbdmsProblemdescription").val();
				if(problemDesc == null || jQuery.trim(problemDesc) == ''){
					alert('Please enter the Problem Description');
					jQuery("#txtbdmsProblemdescription").focus();
					return false;
				}

				var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
				if(assmId == null || jQuery.trim(assmId) == ''){
					alert('Please select the Assembly/Station');
					return false;
				}

				var phenId = jQuery("#cmbbdmsFinalphenomena").combobox('getValue');
				if(phenId == null || jQuery.trim(phenId) == ''){
					alert('Please select the Physical Phenomena');
					return false;
				}

				var failType = jQuery("#cmbbdanFailuretype").combobox('getValue');
				if(failType == null || jQuery.trim(failType) == ''){
					alert('Please select the Failure Phenomena');
					return false;
				}

				var priority = jQuery("#cbobdmsPriority").val();
				if(priority == null || jQuery.trim(priority) == ''){
					alert('Please select the Priority');
					return false;
				}

				var maintSect = jQuery("#cmbbdmsBookedtrade").combobox('getValue');
				if(maintSect == null || jQuery.trim(maintSect) == ''){
					alert('Please select the Maint. Section');
					return false;
				}

				var bookedBy = jQuery("#cmbbdmsBookedby").combobox('getValue');
				if(bookedBy == null || jQuery.trim(bookedBy) == ''){
					alert('Please select the Booked By');
					return false;
				}

				// Mould is only mandatory when "Related To" = MLD
				if(jQuery('#cmbbdmsRelatedto').combobox('getValue') == 'MLD'){
					var mldId = jQuery('#cmbbdmsMould').combobox('getValue');
					if(mldId == null || jQuery.trim(mldId) == ''){
						alert('Please select the Mould');
						return false;
					}
				}			
				if( jQuery('#chkChkCompletedBy').is(':checked') == true  ){
					if(jQuery('#chkIssparesY').is(':checked') == false && jQuery('#chkIssparesN').is(':checked') == false && jQuery('#chkIssparesW').is(':checked') == false)
					{
						if(jQuery('#txtbdmsRepeatedbdno').val() == null || jQuery('#txtbdmsRepeatedbdno').val() == '' || jQuery('#txtbdmsRepeatedbdno').val() == ' ')
						{
							alert('Select Spare Replaced');
							return false;
						}
					}
					else if(jQuery('#chkIssparesW').is(':checked') == true ){
						alert( "Can not complete Breakdown while waiting for Spare");
						return false;
					}
					/*else if(jQuery('#chkIssparesY').is(':checked') == true && jQuery("#cmbbdmsSpareid").combobox("getValue") == "" ){
						alert( "Select Part No. and Name");
						return false;
					}*/
						
				}
				
				
				/*if(jQuery('#chkIssparesY').is(':checked') == true)
				{
					jQuery('#chkIssparesN').attr('checked',true);
					jQuery('#chkIssparesY').attr('checked',false);
					jQuery('#chkIssparesW').attr('checked',false);
				}*/
				
					
				if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
				{
					 	var fromDate = jQuery('#dtebdmsWostarttime').datebox('getValue') + jQuery('#spnbdmsWostart').spinner('getValue');
					    var toDate = jQuery('#dtebdmsWoendtime').datebox('getValue') + jQuery('#spnbdmsWoend').spinner('getValue');
					    
					    
						if( compareDateTime(toDate,fromDate) > 0 )
					    {
							div_err();	
							jQuery('#dispErr').html('Work Start Date Should not be lesser than Work End date');	
							return false;			        	
					    }
				        else
				        	clearValidationErrorMessages('frmBDMaster','dispErr');
					    
					   if(jQuery("#chkChkCompletedBy").is(':checked') && (jQuery('#txtbdanFinalaction').val() == null || jQuery('#txtbdanFinalaction').val() == '' || jQuery('#txtbdanFinalaction').val() == ' '))
						{
						
							div_err();							
							jQuery('#dispErr').html('Enter Final Action');
							return false;
						}
					    else
						{
							clearValidationErrorMessages('frmBDMaster','dispErr');
						
						}
						
						/*if(jQuery("#dtebdmsCompleteddate").val().length == 0 )
						{
							div_err();	
							jQuery('#dispErr').html('Select completed By');
							return false;
						}
						 else
								clearValidationErrorMessages('frmBDMaster','dispErr');*/
						
				}
				if(jQuery('#chbbdmsWoprodaccepflag').is(':checked') == true)
				{
					var fromDate = jQuery('#dtebdmsWoendtime').datebox('getValue') + jQuery('#spnbdmsWoend').spinner('getValue');
				    var toDate = jQuery('#dtebdmsProdaccepdate').datebox('getValue') + jQuery('#spnbdmsProdacceptime').spinner('getValue');
				    
				    
					if( compareDateTime(toDate,fromDate) > 0 )
				    {
						div_err();	
						jQuery('#dispErr').html('Work End Date Should not be greater than Prod.Acceptance date');	
						return false;			        	
				    }
			        else
			        	clearValidationErrorMessages('frmBDMaster','dispErr');	

					
		        	
					if(jQuery('#chkChkCompletedBy').is(':checked') == false)
					{
						div_err();	
						jQuery('#dispErr').html('Select completed By');
						return false;
					}
					 else{
							clearValidationErrorMessages('frmBDMaster','dispErr');
							
							var fromDatePro = jQuery('#dtebdmsProdaccepdate').datebox('getValue') + jQuery('#spnbdmsProdacceptime').spinner('getValue');
						    var toDateCom = jQuery('#dtebdmsCompleteddate').datebox('getValue') + jQuery('#spnbdmsCompletedtime').spinner('getValue');
						    				    
							if( compareDateTime(toDateCom,fromDatePro) > 0 )
						    {
								div_err();	
								jQuery('#dispErr').html('Completed By Date Should not be greater than Prod.Acceptance date');	
								return false;			        	
						    }
					        else
					        	clearValidationErrorMessages('frmBDMaster','dispErr');
					 }			    
				 
				}
				if(jQuery('#chbbdmsRepeatedbdflag').is(':checked') == true)
				{
					var repeatedBD = jQuery('#txtbdmsRepeatedbdno').val();
					if(repeatedBD != null && repeatedBD != '' && repeatedBD != ' ')
					{
						if(jQuery('#txabdanRemarks').val() == null || jQuery('#txabdanRemarks').val() == '')
						{
						
							//div_err();							
							//jQuery('#dispErr').html('Enter Remarks ');
							//return false;
						}
						else
						{
							clearValidationErrorMessages('frmBDMaster','dispErr');
						
						}
					}
				}
				
				var otherphn = jQuery('#cmbbdmsFinalphenomena').combobox('getText');
				var otherFail = jQuery('#cmbbdanFailuretype').combobox('getText');
				//var hdnMultiResp=jQuery("#hdnMultiresp");
				return '&chkOtherPhenomena='+otherphn.trim().toUpperCase()+"&chkFailure=" + otherFail.trim().toUpperCase();
			}

			/* function frmBDMaster_FuntLocHierarchy_SuccessCallBack(keyIds)
			{
				setFunctionalLocWidth("frmBDMaster","620px");
				//var fact=keyIds.factId;
				//commented by mano start
				//if(keyIds.factId != undefined && keyIds.factId != null && keyIds.factId != '')
					//reloadCombo("frmBDMaster","cmbbdmsSpareid","combo_spare.Bbrdn?factId="+keyIds.factId);
				//commented by mano end
				
				//	setFieldValue('cmbbdmsMachineid',keyIds.machId);						
				//reloadMachine("frmBDMaster",'cmbbdmsMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
				//if(jQuery('#cmbbdmsKeyid').combobox('getValue')== null || jQuery('#cmbbdmsKeyid').combobox('getValue') == '')
				//jQuery('#cmbbdanCostcentre').combobox('clear');
				if(keyIds.machId != undefined && keyIds.machId != null && keyIds.machId != '')
				{	
					///reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?mchId="+keyIds.machId);	
					
					reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId );
					fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","");	
					//	checkCostCenter();
					//var mouldId = jQuery('#cmbbdmsMould').combobox('getValue');
					//MKjQuery('#cmbbdmsAssemblyid').combobox("clear");
					//if(mouldId != null && mouldId != ' ' && mouldId != '')

				//	     {//	fillComboBox("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?q=2&relatedto=MLD");

				//	     }else
				//		{
					//	fillComboBox("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?q=2&machineId="+ keyIds.machId);
					//reloadCombo("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?q=2&machineId="+ keyIds.machId );		 
					//jQuery('#cmbbdanCostcentre').combobox("disable");
					//jQuery('#btnfrmBDMastermainFunLoc').css('display','none');
										
					fnEnableDisCommon(); 
				//	}
				}
				reloadCombo("frmBDMaster","cmbbdanCostcentre","costCenter.commonFilter?cellId="+keyIds.cellId);
				var mode=jQuery('#mode').val();
				
				//alert(mode+"  "+jQuery('#txtFormmode').val());
				if(mode =='complete' || mode == 'view')
				{
					jQuery('#bdmsfunLocation').append('<div id="divhide1" style="position:absolute;top:50px;left:20px;width:50%;z-index:2;opacity:0.4;height:20%;"> </div>');
				//	jQuery('#bdmsfunLocation').('enable');
					//readOnlyFields('cmbbdmsMachineid');
			  	}
				else if(mode == 'create')
					enableFields("chkChkCompletedBy");
				
			  	if(jQuery("#dtebdmsCompleteddate").val().length > 0)
					readOnlyFields("chkChkCompletedBy");
				/*else
				{
					alert(jQuery("#dtebdmsCompleteddate").val());
					readOnlyFields("chkChkCompletedBy");
				}*/
				//jQuery('#dispFunctionalLoc').css('width','98%');
				//alert(jQuery('#mode').val()+ "ALert" + frmMode.create);
			//} */
			function frmBDMaster_FuntLocHierarchy_SuccessCallBack(keyIds)
{
    setFunctionalLocWidth("frmBDMaster","620px");

    // Read sbu value from the hidden field the widget actually populated
    var sbuVal = jQuery("#frmBDMaster input[name='hdnsbu']").val();
    
    // If that doesn't work, try by id
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    console.log("sbuVal from hdnsbu:", sbuVal);

    // Push it into the factory hidden field so servlet picks it up
    if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
        jQuery("#frmBDMaster input[id='factory']").val(sbuVal);
        reloadCombo("frmBDMaster","cmbbdmsSpareid","combo_spare.Bbrdn?factId="+sbuVal);
    }

    if(keyIds.machId != undefined && keyIds.machId != null && keyIds.machId != '')
    {	
        reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId);
        fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","","","");	
        fnEnableDisCommon(); 
    }

    reloadCombo("frmBDMaster","cmbbdanCostcentre","costCenter.commonFilter?cellId="+keyIds.cellId);
    
    var mode=jQuery('#mode').val();
    if(mode =='complete' || mode == 'view')
    {
        jQuery('#bdmsfunLocation').append('<div id="divhide1" style="position:absolute;top:50px;left:20px;width:50%;z-index:2;opacity:0.4;height:20%;"> </div>');
    }
    else if(mode == 'create')
        enableFields("chkChkCompletedBy");
    
    if(jQuery("#dtebdmsCompleteddate").val().length > 0)
        readOnlyFields("chkChkCompletedBy");
}
			if(jQuery('#chkbdSubmittoSap').is(':checked')==true){
			var bdKeyId=jQuery('cmbbdmsKeyid').combobox('getValue');
			//alert(" bdKeyId :: "+bdKeyId);
			jQuery('#txtSapOrderNo').val(bdKeyId);
			setTimeout(function() {readOnlyFields('txtSapOrderNo');},1250);
			

				}
			function checkCostCenter()
			{
				if(jQuery('#cmbbdanCostcentre').combobox('getValue') != null && jQuery('#cmbbdanCostcentre').combobox('getValue') != '' && jQuery('#cmbbdanCostcentre').combobox('getValue') != ' ')
				{
					return true;
				}
				else
				{
					('Cost Center is not loaded.Refresh Equipment');
					return;
				}
			}
			function communicationText()
			{
				processGridnew('comm_view.Bbrdn','?q=2&bdId='+jQuery('#txtbdmsWno').val()+'&commFlag=no',"CommnGrid","","","","","commComplete","");//jQuery('#cmbbdmsKeyid').combobox('getValue')
			}
			
			function YY()
			{				
				var bdanNo = jQuery('#bdanNo').val();
				var dataString = '?q=2';
				if(bdanNo != null && bdanNo != '' && bdanNo != ' ')
				{
					jQuery('#txtbdanWwno').val(bdanNo);
					dataString += '&wwNo='+bdanNo;					
				}			
				
				processGridnew('yy_view.Bbrdn',dataString,"yyGrid","","","","","yyComplete","");
			}


			function frmBDMaster_beforeCloseCurrentForm()
			{
				var dataString = '?q=2';
			    var relatedTo = jQuery('#relatedToCMB').val();	    
			    if(relatedTo != null && relatedTo != '' && relatedTo != ' ')
			    	dataString += '&relatedTo='+relatedTo;
		    
				var linkMode = jQuery('#hdnlinkMode').val();
				var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
				var assmId =  jQuery("#cmbbdmsAssemblyid").combobox('getValue');
				 
				if(linkMode == 'Alarm')
					reloadCombo("frmBDMaster","cmbbdmsAlarmdescription","combo_alarm.Bbrdn");
				else if(linkMode == 'Assm'){ 
				 
					reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?q=2&machineId="+ mchId);
				}
				else if(linkMode == 'FailType')
					reloadCombo("frmBDMaster","cmbbdanFailuretype","combo_failtype.Bbrdn"+dataString);
				else if(linkMode == 'Classifcn')
					reloadCombo("frmBDMaster","cmbbdanClassificationid","combo_bdclfcn.Bbrdn?~&pillar=pillar~");
				else if(linkMode == 'Spare')
					reloadCombo("frmBDMaster","cmbbdmsSpareid","combo_spare.Bbrdn?assmId="+assmId);
				else if(linkMode == 'Cause')
					reloadCombo("frmBDMaster","cmbbdmsFinalcause","combo_cause.Bbrdn");
					
			}

			function enableCheckBoxOnMod(hdnVal,chbId)
			{
				
			   if(jQuery("#"+hdnVal).val() != null)
			   {
					if(jQuery("#"+hdnVal).val() == 'Y')
						jQuery('#'+chbId).attr('checked',true);				  
			   }
			}
			function enableDisableFieldsBasedCHBVal(chbId,dateId,spinnerId)
			{
				//mano
				if(jQuery('#mode').val() == 'view') return;
				//
				if(jQuery('#'+chbId).is(':checked') == true)
				{	
					if(jQuery("#hdnBdmsStatus").val() != "C" ){				
						enableFields(dateId);
						enableFields(spinnerId);
					}
					if(chbId == 'chbbdmsWoendflag')
					{
						jQuery('#lblFinalAction').addClass('mandatory-lbl');
						enableFields('txabdanCountermeasure');
						enableFields('txtbdanFinalaction');
						jQuery("#txabdanCountermeasure").css('background-color', '#FFFFFF');
						jQuery("#txtbdanFinalaction").css('background-color', '#FFFFFF');
					}
				}
				else
				{			
						
					readOnlyFields(dateId);
					readOnlyFields(spinnerId);
				}
			}

			var erpState=jQuery('#hdnErpStatus').val();
			if(erpState=="C"){
				
				jQuery('#chkbdSubmittoSap').attr('checked',true);
				disableForm('frmBDMaster');	
				enableFields('txtbdanFinalaction');	
				jQuery('#btnbdnWhyWhy').prop('disabled',false);//hide
				//jQuery('btnbdnWhyWhy').enable;
				readOnlyFields('chkbdSubmittoSap');
				jQuery('SAP Information').prop('disabled',true);
				
				}
	
			
			function disableFieldsBasedOnWO()
			{
				readOnlyFields('chbReporteddate');
				readOnlyFields('dtebdmsReporteddate');
				readOnlyFields('spnbdmsReportedtime');
				readOnlyFields('chbbdmsWoallottedflag');
				readOnlyFields('dtebdmsReceiveddate');
				readOnlyFields('spnbdmsReceivedtime');
			}
	/* 		function bdSaveMode()
			{
				 	//setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?~&assmId=cmbbdmsAssemblyid~&mchId=cmbbdmsMachineid~");},1250);
					readOnlyFields('cmbbdmsShiftid');	       	
					//jQuery('#lblworkOrderNum').css('display','none');
		          	//jQuery('#workOrderNum').hide();
					readOnlyFields('dtebdmsReporteddate');
					readOnlyFields('spnbdmsReportedtime');
					displayText('txtbdanErppoststatus','BOOKING');	
					readOnlyFields('dtebdmsReceiveddate');
				    readOnlyFields('dtebdmsWostarttime');
				    readOnlyFields('dtebdmsWoendtime');	      					
					readOnlyFields('spnbdmsReceivedtime');
				    readOnlyFields('spnbdmsWostart');
				    readOnlyFields('spnbdmsWoend');		   	
					dateFiledFill();
					enableRepeatedBDInCheckBoxSelect('chbbdmsRepeatedbdflag','btnbdmsRepeatedbdno');
			} */
			function bdSaveMode()
			{
			    readOnlyFields('cmbbdmsShiftid');	       	

			    // Fill current date/time BEFORE locking the fields read-only,
			    // otherwise the value never renders into the disabled spinner.
			    dateFiledFill();

			    readOnlyFields('dtebdmsReporteddate');
			    readOnlyFields('spnbdmsReportedtime');
			    displayText('txtbdanErppoststatus','BOOKING');	
			    readOnlyFields('dtebdmsReceiveddate');
			    readOnlyFields('dtebdmsWostarttime');
			    readOnlyFields('dtebdmsWoendtime');	      					
			    readOnlyFields('spnbdmsReceivedtime');
			    readOnlyFields('spnbdmsWostart');
			    readOnlyFields('spnbdmsWoend');		   	
			    enableRepeatedBDInCheckBoxSelect('chbbdmsRepeatedbdflag','btnbdmsRepeatedbdno');
			}
			function bdUpdateMode()
			{
				
			if(frmMode.openTab  != null && frmMode.openTab != '' && frmMode.openTab != ' ')
				{
			   		if(frmMode.openTab == 'OpenYY')
				   	{
            			jQuery('#tabbrkDown').tabs('select', 'WHY WHY Analysis');
			   			frmMode.openTab = null;
					   	
				   	}
				}

				var assmId = jQuery("#cmbbdmsAssemblyid").combobox('getValue');
				var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
				var mldId= jQuery('#cmbbdmsMould').combobox('getValue');
				if(mldId != null && mldId != '' && mldId != ' ')
					{
					//fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn ");//&mldId="+mldId);
					}
			else
				{
					if(jQuery('#chkIsAssmWise').is(':checked') == true)
						{
					//fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+assmId+"&mchId="+mchId);
						}else
							{
			   			//fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&mchId="+mchId);
							}
						}
	   			
			
				
				
			 	//setTimeout(function() {fillComboBox("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.Bbrdn?q=2&assmId="+assmId+'&mchId='+mchId);},1250);
			  // jQuery('#chbReporteddate').attr('checked',true);
			  // jQuery('#chbbdmsWoallottedflag').attr('checked',true);
			  // jQuery('#chbbdmsWostartflag').attr('checked',true);
			   //jQuery('#chbbdmsWoendflag').attr('checked',true);
			  // jQuery('#chbbdmsWoprodaccepflag').attr('checked',true);
			  // jQuery('#lblFinalAction').addClass('mandatory-lbl');
			  // readOnlyFields('dtebdmsProdaccepdate');
			  // readOnlyFields('spnbdmsProdacceptime');
			  // readOnlyFields('chbbdmsWoprodaccepflag');
			  // enableFields('dtebdmsReceiveddate');
			  // enableFields('dtebdmsWostarttime');
			  // enableFields('dtebdmsWoendtime');
			  // enableFields('dtebdmsProdaccepdate');
			 //  enableFields('spnbdmsReceivedtime');
			 //  enableFields('spnbdmsWostart');
			 //  enableFields('spnbdmsWoend');
			  // enableFields('spnbdmsProdacceptime');
			//   enableFields('txabdanCountermeasure');
			   //jQuery("#txabdanCountermeasure").css('background-color', '#FFFFFF');
			   //(jQuery('#hdnRepBDFlag').val());		  
			    enableCheckBoxOnMod('hdnOccuredFlag','chbbdmsWoallottedflag');
			    enableCheckBoxOnMod('hdnwostFlag','chbbdmsWostartflag');
			    enableCheckBoxOnMod('hdnwoendFlag','chbbdmsWoendflag');
			    enableCheckBoxOnMod('hdnProdstFlag','chbbdmsWoprodaccepflag');
			    var mode = jQuery('#mode').val();		
			  
			    if(mode != null && mode != '' && mode != ' ')
				{
					if(mode!='view')
					{
						
			    		enableDisableFieldsBasedCHBVal('chbbdmsWoallottedflag','dtebdmsReceiveddate','spnbdmsReceivedtime');
						enableDisableFieldsBasedCHBVal('chbbdmsWostartflag','dtebdmsWostarttime','spnbdmsWostart');
						enableDisableFieldsBasedCHBVal('chbbdmsWoendflag','dtebdmsWoendtime','spnbdmsWoend');
						
					}
				}
			    else
				    {
			    		enableDisableFieldsBasedCHBVal('chbbdmsWoallottedflag','dtebdmsReceiveddate','spnbdmsReceivedtime');
						enableDisableFieldsBasedCHBVal('chbbdmsWostartflag','dtebdmsWostarttime','spnbdmsWostart');
						enableDisableFieldsBasedCHBVal('chbbdmsWoendflag','dtebdmsWoendtime','spnbdmsWoend');
				    }
			    
			    //enableDisableFieldsBasedCHBVal('chbbdmsWoprodaccepflag','dtebdmsProdaccepdate','spnbdmsProdacceptime');
			    if(jQuery('#txtbdmsWno').val() != null && jQuery('#txtbdmsWno').val() != '' && jQuery('#txtbdmsWno').val() != ' ')
				{
			    	
					 disableFieldsBasedOnWO();
				}
				//jQuery('#cbobdanProblemseverity').combobox('setValue',jQuery('#hdnProblemseverity').val());
				jQuery('#cbobdmsActivity').val(jQuery('#hdncbobdmsActivity').val());
				jQuery('#cbobdanProblemseverity').val(jQuery('#hdnProblemseverity').val());
				
			    jQuery('#cbobdmsPriority').val(jQuery('#hdnPriority').val());
			    
			   if(jQuery('#hdnRepBDFlag').val() =='Y')	
			    {		   
	   			    jQuery('#chbbdmsRepeatedbdflag').attr('checked',true);		
				    readOnlyFields('chbbdmsRepeatedbdflag');
				    jQuery("#txabdanRemarks").css('background-color', '#FFFFFF');
					enableFields('txabdanRemarks');
				    addMandClass('lblcomText');
				    removeMandClass('lblPriority');
					removeMandClass('lblMaintSect');
					removeMandClass('lblFailType');
					removeMandClass('lblPhen');
					removeMandClass('lblBDClassfcn');
					removeMandClass('lblBookedBy');
					removeMandClass('lblAnalysedBy');
				}
			 // alert(jQuery('#txtbdanWwno').val());
			    if(jQuery('#txtbdanWwno').val() != null && jQuery('#txtbdanWwno').val() != '' && jQuery('#txtbdanWwno').val() != ' ')
					processAjaxCalls('setrc_values.Bbrdn','?q=2&yyNO='+jQuery('#txtbdanWwno').val(),'classificn');

			  //  enableRepeatedBDInCheckBoxSelect('chbbdmsRepeatedbdflag','txtbdmsRepeatedbdno');
				var mould = jQuery('#cmbbdmsMould').combobox('getValue');

				 // if(jQuery("#frmBDMaster input[id='relatedToCMB']").val() == 'MLD')
				 //readOnlyFields('cmbbdmsRelatedto');
				// jQuery("#frmBDMaster cmbbdmsRelatedto").attr('disabled',"disabled");
				 
			    //if(mould != null &&  mould != '' && mould  != ' ')
			    	//jQuery('#cmbbdmsRelatedto').combobox('disable');
			}

			
				jQuery("#chkbdmsIsstandby").click(function(){ 
					
					if(jQuery('#mode').val() == 'view') return;
					if(jQuery("#chkbdmsIsstandby").is(':checked') == true)
					{ 
						enableFields("cmbbdmsStandbyequipment");
						enableFields("txtbdmsBreakdowntime");
						jQuery("#txtbdmsBreakdowntime").val("");
					}
					else{
						readOnlyFields("cmbbdmsStandbyequipment");
						readOnlyFields("txtbdmsBreakdowntime");
						jQuery("#txtbdmsBreakdowntime").val(jQuery("#txtBDDownTime").val());
					}
					});
				function divSAPSpares_onClose() {
					jQuery('#sapInfoGrid').trigger('reloadGrid');
					return true;
				}
				
			
			
    	</script>	

<form name="frmBDMaster" id="frmBDMaster" method="post">

<div>
<div id="wrapper">

	<input type="text" style="display:none" id="txtFormmode" name="txtFormmode" value="${requestScope.formMode}"/>
	<input id="hdnRecTime" name="hdnRecTime" style="display:none;"/>
	<input id="hdnwsTime" name="hdnwsTime" style="display:none;"/>
	<input id="hdnweTime" name="hdnweTime" style="display:none;"/>
	<input id="hdnProdAccTime" name="hdnProdAccTime" style="display:none;"/>
	<input id="hdnbdanKeyid" name="hdnbdanKeyid" value="${requestScope.bdmTlDtl.bdanKeyid}" style="display:none;"/>
	<input type="hidden" id="hdnRepBDFlag" name="hdnRepBDFlag" value="${requestScope.bdmTlMst.bdmsRepeatedbdflag}">
	<input type="hidden" id="mode" name="mode" value="${requestScope.formMode}">
	<input type="hidden" id="pillar" name="pillar">
	<input type="hidden" id="phenName" name="phenName" value="${requestScope.bdFormBean.bdmsphenName}">
	<input type="hidden" id="hdnlinkMode" name="hdnlinkMode">
	<input type="hidden" id="hdndelBDMode" name="hdndelBDMode" value="${requestScope.delActivity}">
	<input type="hidden" id="" name="value="${requestScope.WorkOrderBDID}"/>
<!--<div class="floatleft" style="padding-right: 10px">&nbsp;</div>-->
		<div class="floatleft" >
		
			<label >EMR No</label> <!-- id="cmbbdmsKeyid" name="cmbbdmsKeyid" -->
			<input class="easyui-text" type="text"  style="width: 150px;" id="cmbbdmsKeyid" name="cmbbdmsKeyid" value="${requestScope.bdmTlMst.bdmsKeyid}" value = "${requestScope.bdFormBean.disablebdmsKeyid == true ? ' disabled':''}"/>
			<label id="lblworkOrderNum">Work Order No</label>
			<input type="text" id="txtbdmsWno" name="txtbdmsWno" value="${requestScope.bdmTlMst.bdmsWno}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;background-color:#D1E2FD;font-weight:bold;text-align:center;color:#245edc;" value = "${requestScope.bdFormBean.disablebdmsWno == true ? ' disabled':''}"/>
			<label class="">Date</label>
			<input id="txtbdmsEntrydate" name="txtbdmsEntrydate" class="easyui-text" readonly="readonly" required="true" style="width: 80px;" value="${requestScope.bdmTlMst.bdmsEntrydate}"  value = "${requestScope.bdFormBean.disablebdmsEntrydate == true ? ' disabled':''}"/> &nbsp;
			<label  class="mandatory-lbl">Shift</label>
			<input   class="easyui-combobox" id="cmbbdmsShiftid" name="cmbbdmsShiftid" readonly="readonly"  style="width: 55px;"  value="${requestScope.bdmTlMst.bdmsShiftid}"  value = "${requestScope.bdFormBean.disablebdmsShiftid == true ? ' disabled':''}"/>
			<input type="text" id="txtbdanErppoststatus" name="txtbdanErppoststatus" value="${requestScope.bdmTlDtl.bdanErppoststatus}" class="easyui-text noFocus"   tabindex = "-1" disabled  style="margin-left:45px;width:280px;height:25px;background-color:#D1E2FD;font-weight:bold;text-align:center;color:#245edc; "  value = "${requestScope.bdFormBean.disablebdanErppoststatus == true ? ' disabled':''}"/>
			<!--  <label id="lblworkOrderNum" style="margin-left:64px;">SAP No</label>-->
			
			<div class="easyui-paddingbfpx" style="position:relative;">
			
			<div id="workOrderNum">
			 	
			</div>
			<div style="margin-top:-21px;padding-left:478px;display:none"><input id="txtbdmsSapNo" type="text" style="width: 150px;"  class="easyui-text" value="${requestScope.bdmTlMst.bdmsKeyid}" disabled/></div>
			<div  style="padding-left: 504px;margin-to: -25px;">
<!--			<img id="" src="images/filemanager1.png"/>-->
<!--			<img id="" src="images/hist3.png"/>-->
			<span id="forNewpp" style="display:none;">
<!--				<img id="" src="images/newpropasal1.png"/>-->
					<input type="button" id="btnNewPP" class="easyui-button" value="New PP Proposal" style="width: 120px;height: 22px;"/>
			</span>			
		   </div>
		   <div id="newPP" style="float: right;margin-top: -25px;"></div>
<!--			<div id="forNewPP" style="display:none;padding-left: 800px;"><input type="button" id="btnNewPP" class="easyui-button" value="New PP Proposal" style="width: 120px;height: 25px;"/></div>-->
		   
	    </div>
		<div id="frmBDMasterFuntKeyIds">
						<input type="hidden" id="factory" name="cmbbdmsFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbbdmsSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbbdmsCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
						<input type="hidden" id="machine" name="cmbbdmsMachineidhdn" value="${requestScope.bdmTlMst.bdmsMachineid}"></input>
						<input type="hidden" id="flid" name="cmbbdmsFlid" value="${requestScope.bdmTlMst.bdmsFlid}"></input>
						<input type="hidden" id="elementId" name="cmbbdmsElementid" value="${requestScope.bdmTlMst.bdmsElementid}"></input>
		</div>
		<div>
		<div id="bdmsfunLocation" style="margin-top:20px"></div>
		<div style="float:right;margin-top:-25px;width:300px;margin-right:80px">
		  		<span>
		  		<input type="button" id="btnbdnActionplan" name="btnbdnActionplan" class="easyui-button" style="width:150px;height:25px" value="Action Plan" />
		    	<span  id="bdnFilemgr" style="vertical-align:top; float:right;width:140px;margin-left:10px;"> </span> 
		  		
		  		</span>
			
		</div>
		</div>
		<div class="floatleft easyui-paddingbfpx" style="margin-top:10px;" >		    
			
		    
		    <div><label class="mandatory-lbl">Equipment</label></div>
			<div class="easyui-paddingbfpx" style="padding-bottom: 0px;padding-right:10px;"> 
				<input type="text" id="cmbbdmsMachineid" name="cmbbdmsMachineid" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsMachineid}" style="width: 300px;"  value = "${requestScope.bdFormBean.disablebdmsMachineid == true ? ' disabled':''}"/>
				<input type="hidden" id="hdncmbbdmsMachineid" value="${requestScope.bdFormBean.disablebdmsMachineid}" />
			<!-- 	<input type="button" class="easyui-button"  id="btnEqpLink" value="..." style="height: 22px;"/> -->
				<span id="err_cmbbdmsMachineid" class="tpm-errormsg"></span>
			</div>
			<div style="margin-top:3px; display: none;">
			<label>Production Stop</label>
			<label style="margin-left:10px;">Process</label>
			<span style="margin-left:2%px;"><input type="checkbox" /></span>
			</div>
			<div class="easyui-paddingbfpx" style="display: none;">
			<input type='checkbox' id='chkbdmsProductionstop' style="width:80px" name='chkbdmsProductionstop' value='Y'  value = "${requestScope.bdmTlMst.bdmsProductionstop == 'Y' ? 'checked':''}"/>
			<span  style="margin-left:16px">			
			<input   id="cmbbdmsProcessId" name="cmbbdmsProcessId" class="easyui-combobox" style="width: 200px;" />
			</span>
<%-- 			 <input  id="cmbbdmsProcessId" name="cmbbdmsProcessId" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsProcessId}" style="width: 265px;" <c:out value = "${requestScope.bdFormBean.disableProcess == true ? ' disabled':''}"/>/> --%>
				<!-- <select id="cmbProcess" name="cmbProcess" style="width: 294px;"  >
					<option> </option>
					<option>process 1</option>
					<option>process 2</option>
					<option>process 3</option>
					<option>process 4</option>
					<option>process 5</option>
				</select> -->
			</div>
			<div style="margin-top: 7px" ><label>Cost Center</label></div>
		    <div class="easyui-paddingbfpx">
		    <input id="cmbbdanCostcentre" name="cmbbdanCostcentre"  class="easyui-combobox" value="${requestScope.bdmTlDtl.bdanCostcentre}" style="width: 300px;"  value = "${requestScope.bdFormBean.disablebdanCostcentre == true ? ' disabled':''}"/>
		    </div>
		    <div class=""><label class="mandatory-lbl" id="lblBookedBy">Booked By</label>
			<div class="easyui-paddingbfpx"><input id="cmbbdmsBookedby" name="cmbbdmsBookedby" style="width:300px;" value="${requestScope.bdmTlMst.bdmsBookedby}" class="easyui-combobox" style="width:304px"  value = "${requestScope.bdFormBean.disablebdmsBookedby == true ? ' disabled':''}"/>
			
			</div>
			</div> 	
	   </div>
	    <div class="floatleft easyui-paddingbfpx"  style="margin-left:20px;margin-top: 10px">
			<!-- <div><label id="lblcomText">Communication Text</label></div>
			<div class=""><textarea  id="txabdanRemarks" name="txabdanRemarks" style="resize:none;width:260px;height:70px" <c:out value = "${requestScope.bdFormBean.disablebdmsRemarks == true ? ' disabled':''}"/>>${requestScope.bdmTlDtl.bdanRemarks}</textarea>
			</div>
			 -->
			 <div class="mandatory-lbl">
			   <label>Problem Description</label>
		   </div>
		   <div class="easyui-paddingbfpx">
		  	  <textarea id="txtbdmsProblemdescription" name="txtbdmsProblemdescription"  maxlength="590" style="resize:none;width:304px;height:60px"  value = "${requestScope.bdFormBean.disablebdmsProblemdescription == true ? ' disabled':''}"/>${requestScope.bdmTlMst.bdmsProblemdescription}</textarea>
		  </div>
				<div style="width:300px;padding-top:15px">
		  		<input type='checkbox' disabled="disabled" id='chkbdSubmittoSap' style="width:30px" value='Y' />
		  		<label style="font-weight:bold;">Submit to SAP </label>
		  		
		  		<input type='checkbox' disabled="disabled" id='chkbdEntryPcs' style="width:30px;margin-left: 25px" value='Y' />
		  		<label style="font-weight:bold;">Create Entry in PCS </label>	
		  	
			</div>
			<div style="margin-top:13px;padding-left:150px;"><b><label style="color:#FF0000;font-weight:bold;">SAP Order No.</label></b></div>
			<div style="padding-left:120px;"><input type="text" name="txtSapOrderNo" id="txtSapOrderNo" value="${requestScope.bdmTlDtl.bdanErpnumber}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;width:150px;background-color:#FCF9B0;font-weight:bold;text-align:center;font-color:#FF0000;" />&nbsp;&nbsp;&nbsp;</div>
<!--	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Line</label></div>-->
<!--	<div class="easyui-paddingbfpx"><input id="cmbbdmsCellid" name="cmbbdmsCellid"  class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsCellid}" style="width: 300px;"/></div>-->
	   </div>
	   
		<div class="floatleft easyui-paddingbfpx" style="margin-left:20px;margin-top: 10px;width:290px">
		 	<div style="display:none;">
		 		<label class="mandatory-lbl" style="margin-l">Related To</label>
		 		<label id="lblMld" style="padding-left:50px ">Mould</label>
		 	</div>
		 	<div class="easyui-paddingbfpx"  style="width:100%; float:left;display:none;">
			 	<select id="cmbbdmsRelatedto" class="easyui-combobox" name="cmbbdmsRelatedto"   style="height:22px;width:95px;"   " value = "${requestScope.bdFormBean.disablebdmsRelatedto == true ? ' disabled':''}"/>
								<option value="MCH">MACHINE </option>
								<option value="MLD">MOULD</option>							
				</select> 
				<input id="relatedToCMB" name="relatedToCMB" type="hidden" value="${requestScope.bdmTlMst.bdmsRelatedto}"/>
				<span style="padding-left:10px;" style="" >
					<input type="text" id="cmbbdmsMould" name="cmbbdmsMould" class="easyui-combobox"  value="${requestScope.bdmTlMst.bdmsMould}"  style="width: 150px;"  value = "${requestScope.bdFormBean.disablebdmsMould == true ? ' disabled':''}"/>
					<input type="hidden" id="hdncmbbdmsoulddis" value ="${requestScope.bdFormBean.disablebdmsMould}" />
				</span>
			
			</div>
			
		<div>
		<label style="" class="mandatory-lbl" id="lblPriority">Priority</label>
		<label style="margin-left:50px" id="activityLbl">Repeat BD</label>
		<label >Reference#</label></div>
			<div class="easyui-paddingbfpx" style="width:100%; float:left;">
				<span style="vertical-align:3px;">
					<select id="cbobdmsPriority" class="easyui-combobox" name="cbobdmsPriority" value="${requestScope.bdmTlMst.bdmsPriority}"  style="height: 22px;width:95px;font-size:9px;"  value = "${requestScope.bdFormBean.disablebdmsPriority == true ? ' disabled':''}"/>				
							<option value="V"> VERY HIGH</option>
							<option value="H"> HIGH</option>
							<option value="M"> MEDIUM</option>
							<option value="L"> LOW</option>
					</select> 				
				</span>
				
				<input style="width:20px;cursor:pointer" type="checkbox" id="chbbdmsRepeatedbdflag" name="chbbdmsRepeatedbdflag" value="Y"  value = "${requestScope.bdFormBean.disablebdmsRepeatedbdflag == true ? ' disabled':''}"/>
				<span style="margin-top:-4px;">
				
				<input  id="txtbdmsRepeatedbdno" name="txtbdmsRepeatedbdno" class="easyui-text" value="${requestScope.bdmTlMst.bdmsRepeatedbdno}" style="width: 130px;" disabled="disabled"/>
				<input  id="btnbdmsRepeatedbdno" title="Click Here to view Repeated Breakdown Details" disabled="disabled" class="easyui-button" style="width:30px;height:25px" value="..." value = "${requestScope.bdFormBean.disablebdmsRepeatedbdno == true ? ' disabled':''}"/> 
				</span>
			</div>
			<!--<div  id="" style="float:right;margin-right:15%;margin-right:50px\9;width:80px;">
		  		
			</div> 
		    
			-->
			<!--<div  id="" style="float:right;margin-right:15%;margin-right:50px\9;width:80px;">
			</div>-->
			<div style="width:300px;" class="easyui-paddingbfpx">
			<!-- <input type="button" id="btnbdnValidate" name="btnbdnValidate" class="easyui-button" style="height:25px;width:140px;width:110px\9;" value="Validate" />
		  		 <input type="button" id="btnbdnSumitSAP" name="btnbdnSumitSAP" class="easyui-button" style="height:25px;width:120px;width:110px\9;" value="Submit To SAP" />
		  	 -->
		  	 	 <input type="button" id="btnbdnWhyWhy" class="easyui-button" style="height:35px;width:300px;width:210px\9;" value="Why Why Analysis" />
		  		 <input id="btnEstimation" name="btnEstimation" type="button" class="easyui-button"  value="Resource Estimation"  style="width:148px; height:30px;"/></span>
 				 <input id="btnActual" name="btnActual" type="button" class="easyui-button"  value="Cost Information"  style="width:148px; height:30px;"/>
		  	</div>	
		  	
			<div style="margin-top:10px">
		  		 <b><label style="padding-left:80px;color:#FF0000;font-weight:bold;">SAP Status</label></b>
		  			<input type="text" size="18" height="15px" id="txtErrppostStatus" name="ErrppostStatus" value="${requestScope.Status}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;width:250px;background-color:#FCF9B0;font-weight:bold;text-align:left;color:#FF0000;" style="padding-left:10px;">
<!--				<input type="button" id="btnbdmsFilemanager" class="easyui-button" value="File Manager" style="width: 120px;height: 22px;"/>-->
<!--				<input type="button" class="easyui-button" id="viewPreviousBd" name="viewPreviousBd" value="View Previous BD" style="height: 22px;" />-->
  		  </div>  
<!--			<div></div>-->
<!--		 	<div class="easyui-paddingbfpx">-->
<!--			 			-->
<!--			</div>-->
<!--			<div class="easyui-paddingbfpx" style="margin-top: 15px;"><input type="text" id="txtbdanErppoststatus" name="txtbdanErppoststatus" value="${requestScope.bdmTlDtl.bdanErppoststatus}" class="easyui-text noFocus"  readonly="readonly" value="" style="width:285px;height:30px;background-color:#D1E2FD;font-weight:bold;text-align:center; "/></div>-->
<!--			<div><label style="padding-right:165px">&nbsp;</label><label>Reference#</label></div>-->
<!--			<div class="easyui-paddingbfpx">-->
<!--				<span style="padding-right: 40px;">	<input type="checkbox"/>&nbsp;&nbsp;<label>Repeat Breakdown</label></span>-->
<!--				<span><input type="text" id="txtbdmsRepeatedbdno" name="txtbdmsRepeatedbdno" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsRepeatedbdno}" style="width: 120px;"/></span>-->
<!--			</div>		-->
		</div>
	        

<!--</div>	<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Factory</label></div>-->
<!--	<div class="easyui-paddingbfpx"><input  class="easyui-combobox" id="cmbbdmsFactoryid" name="cmbbdmsFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}" style="width: 300px;"/></div>-->
<!--	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Section</label></div>-->
<!--<div id="BrkDwnTab" style="width:1040px; " >	<div class="easyui-paddingbfpx"><input  class="easyui-combobox" id="cmbbdmsSectionid" name="cmbbdmsSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}" style="width: 300px;"/></div>-->

  
	<div class="clear"></div>

	<!-- *******Tabs*********** -->	
		<div style="align:right;">
			
  		</div>
	<div id="tabbrkDown" class="easyui-tabs"  style="width:1040px;">
					  
	   <div title="Work Details" style="padding:4px;">  <!-- Work Details Div -->
<!--	        <div class="floatleft" style="padding-right: 30px;">&nbsp;</div>	-->
	     <div class="floatleft" style="">
<!--			<div><label class="mandatory-lbl">Equipment</label></div>-->
<!--			<div class="easyui-paddingbfpx" style="padding-bottom: 0px;padding-right:10px;"> -->
<!--				<input type="text" id="cmbbdmsMachineid" name="cmbbdmsMachineid" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsMachineid}" style="width: 380px;"/>-->
<!--				<input type="button" class="easyui-button"  value="..." style="height: 22px;"/>-->
<!--				<span id="err_cmbbdmsMachineid" class="tpm-errormsg"></span>-->
<!--			</div>-->
			   <div>
	<!--			<span style="padding-right: 10px;">&nbsp;</span>-->
					<label class="mandatory-lbl" style="padding-left:22px;_padding-left:28px;">Occurred On</label>
					<label  style="padding-left:102px;padding-left:105px;">Work Time(Mins)</label>
			  </div>
			  
			  <div class="easyui-paddingbfpx">	
	<!--			<span style="padding-right: 10px;">&nbsp;</span>-->
				<span style="padding-right:0px"><input type="checkbox" id="chbReporteddate"  name="chbReporteddate"  value = "${requestScope.bdFormBean.disableReporteddate == true ? ' disabled':''}"/></span>
				<span style="padding-left: 5px;"><input id="dtebdmsReporteddate"  name="dtebdmsReporteddate" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsReporteddate}"  style="width: 100px;"  value = "${requestScope.bdFormBean.disablebdmsReporteddate == true ? ' disabled':''}"/> </span>
				<span class="spinner"><input  id="spnbdmsReportedtime" name="spnbdmsReportedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.bdFormBean.bdmsReportedtime}"  style="width: 60px;"  value = "${requestScope.bdFormBean.disablebdmsReportedtime == true ? ' disabled':''}"/></span>
				
				<span style="padding-left:10px"><input id="txtbdmsActualworktime" name="txtbdmsActualworktime" value="${requestScope.bdmTlMst.bdmsActualworktime}" type="text" class="easyui-text"  readonly="readonly" style="height:22px;width:95px;"  value = "${requestScope.bdFormBean.disablebdmsActualworktime == true ? ' disabled':''}"/></span>
			 </div>
			
		
			<div> <label class="mandatory-lbl">Assembly/Station</label>
			<input type="checkbox" id="chkOtherAssm" style="margin-left:25px" value="Y"/>  <label style="margin-left:3px">Others</label>
			</div>
			<div class="easyui-paddingbfpx">
				<input id="cmbbdmsAssemblyid" name="cmbbdmsAssemblyid" value="${requestScope.bdmTlMst.bdmsAssemblyid}" title="Select Equipment" class="easyui-combobox" style="width: 264px"  value = "${requestScope.bdFormBean.disablebdmsAssemblyid == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button" id="btnAssmLink" value="..." style="height: 22px;" />
				<span id="err_cmbbdmsAssemblyid" class="tpm-errormsg"></span>
			</div>
			<div><label>Sub Assembly</label></div>
			<div style="">
				<input id="cmbbdmsSubassemblyid" name="cmbbdmsSubassemblyid" value="${requestScope.bdmTlMst.bdmsSubassemblyid}" class="easyui-combobox" style="width: 264px"  value = "${requestScope.bdFormBean.disablebdmsSubassemblyid == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button" id="btnSubAssmLink" value="..." style="height: 22px;"/>
			</div>
			<div class="bdFailType"><label>Part Location</label></div>
			<div class="easyui-paddingbfpx">
				<input id="txtbdmsPartlocationid" name="txtbdmsPartlocationid"  value="${requestScope.bdmTlMst.bdmsPartlocationid}" class="easyui-text"  style="width: 294px" value = "${requestScope.bdFormBean.disablebdmsSubassemblyid == true ? ' disabled':''}"/>
			</div>
			<div>
				<label id='lblSpareReplaced'>Spare Replaced</label>
			</div>
			<div class="bdSparesReplaced"  style="  width : 294px;height:10px;_height:6px;">
				<div style="margin-top:5px;padding-left:4px;">
					 <span style="margin-left: 25px"><input type="checkbox" id="chkIssparesY" name="chkIssparesY" value="Y"  value = "${requestScope.bdFormBean.disableIssparesY == true ? ' disabled':''}"/><label style="padding-left:5px;">Yes</label></span>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesN" name="chkIssparesN" value="N"  value = "${requestScope.bdFormBean.disableIssparesN == true ? ' disabled':''}"/><label style="padding-left:5px;">No</label></span>
					 <span style="margin-left: 35px"><input type="checkbox" id="chkIssparesW" name="chkIssparesW" value="W"  value = "${requestScope.bdFormBean.disableIssparesW == true ? ' disabled':''}"/><label style="padding-left:5px;">Waiting</label></span>
					 <input id="sparesFlag" name="sparesFlag" type="text" style="display:none;" value="${requestScope.bdmTlDtl.bdanIssparesreplaced}"/>
					 <!--  <input type="button" id="btnSapCheck" class="easyui-button" value="..." style="height: 22px;margin-top:-24px;margin-left: 25px"/>-->
				</div>
			</div>
				<div class="bdSpareCombo"><label id="lblspareid">Part No. and Name</label></div>
			<div style="padding-bottom:15px;">
				<input id="cmbbdmsSpareid" name="cmbbdmsSpareid"  value="${requestScope.bdmTlMst.bdmsSpareid}" class="easyui-combobox" style="width: 294px"  value = "${requestScope.bdFormBean.disablebdmsSpareid == true ? ' disabled':''}"/>
<!--				<input type="button" class="easyui-button" id="btnSpareLink" value="..." style="height: 22px;"/>-->
			</div>				
			
				
			<div>
				<label class="mandatory-lbl" id="lblPhen">Physical Phenomena</label>
				<!--  <span style="padding-left:85px; display:none ">
					<input type="checkbox" id="chkIsAssmWise" style="margin-left:25px" name="chkIsAssmWise" value="Y"/>  <label style="margin-left:3px">Assembly Wise</label>
				</span> -->
			</div>
						
			<%-- <div class="easyui-paddingbfpx">
				<input id="cmbbdmsFinalphenomena" name="cmbbdmsFinalphenomena" value="${requestScope.bdmTlMst.bdmsBookedphenomena}"  class="easyui-combobox" style="width: 294px"  value = "${requestScope.bdFormBean.disablebdmsFinalphenomena == true ? ' disabled':''}"/>
				<!-- <input type="button" class="easyui-button"  id="btnPhenomena" value="..." style="height: 22px;"/> -->
				<span id="err_cmbbdmsFinalphenomena" class="tpm-errormsg"></span>
			</div> --%>
			<!-- priyanka -->
			<div class="easyui-paddingbfpx">
				<input id="cmbbdmsFinalphenomena" name="cmbbdmsFinalphenomena" value="${requestScope.bdmTlMst.bdmsBookedphenomena}"  class="easyui-combobox" style="width: 264px"  value = "${requestScope.bdFormBean.disablebdmsFinalphenomena == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button"  id="btnPhenomena" value="..." style="height: 22px;"/>
				<span id="err_cmbbdmsFinalphenomena" class="tpm-errormsg"></span>
			</div>
			<div id="divOtherPhenomena">
			<div class="easyui-paddingbfpx">
				<input id="txtbdmsOtherPhenomena" name="txtbdmsOtherPhenomena" maxlength="140" value="${requestScope.bdmTlMst.bdmsOtherPhenomena}" class="easyui-text"  style="width: 294px"  value = "${requestScope.bdFormBean.disablebdmsSubassemblyid == true ? ' disabled':''}"/>
				<span id="err_txtbdmsOtherPhenomena" class="tpm-errormsg"></span>
			</div>
			</div>
			
			
			<div class="bdFailType"><label class="mandatory-lbl" id="lblFailType">Failure Phenomena</label>
			</div>
			<%-- <div class="easyui-paddingbfpx">
				<input id="cmbbdanFailuretype" name="cmbbdanFailuretype" value="${requestScope.bdmTlDtl.bdanFailuretype}" class="easyui-combobox" style="width: 294px"  value = "${requestScope.bdFormBean.disableBdanFailuretype == true ? ' disabled':''}"/>
<!--				<input type="button" class="easyui-button" id="btnFailureTypeLink" value="..." style="height: 22px;"/>-->
				<span id="err_cmbbdanFailuretype" class="tpm-errormsg"></span>
			</div> --%>
			<!-- priyanka -->
			<div class="easyui-paddingbfpx">
				<input id="cmbbdanFailuretype" name="cmbbdanFailuretype" value="${requestScope.bdmTlDtl.bdanFailuretype}" class="easyui-combobox" style="width: 264px"  value = "${requestScope.bdFormBean.disableBdanFailuretype == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button" id="btnFailureTypeLink" value="..." style="height: 22px;"/>
				<span id="err_cmbbdanFailuretype" class="tpm-errormsg"></span>
			</div>
			<div id="divOtherFailure">
			<div class="easyui-paddingbfpx">
				<input id="txtbdanOtherFailuretype" name="txtbdanOtherFailuretype"  maxlength="140" value="${requestScope.bdmTlDtl.bdanOtherFailuretype}" class="easyui-text"  style="width: 294px"  value = "${requestScope.bdFormBean.disablebdmsSubassemblyid == true ? ' disabled':''}"/>
			</div>
			</div>
			  <div><label id="lblImmediateaction">Immediate Action</label></div>
			<div class="easyui-paddingbfpx"><textarea id="txtbdmsImmediateaction" name="txtbdmsImmediateaction"  maxlength="490" style="resize:none;width:294px;height:70px;"  value = "${requestScope.bdFormBean.disablebdanFinalaction == true ? ' disabled':''}"/>${requestScope.bdmTlMst.bdmsImmediateaction}</textarea>
				<span id="err_txtbdmsImmediateaction" class="tpm-errormsg"></span>
		   </div>
		   	<div><label class="mandatory-lbl" id="lblMaintSect">Maint. Section</label></div>
			<div class="easyui-paddingbfpx"><input  id="cmbbdmsBookedtrade" name="cmbbdmsBookedtrade" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsBookedtrade}" style="width: 294px"  value = "${requestScope.bdFormBean.disablebdmsBookedtrade == true ? ' disabled':''}"/></div>
			
	 </div>
	  <div class="floatleft bdTabContent2" style="width:350px">
	  
	   		 <div  style="padding-left:22px;_padding-left:28px;">
	
				<label >Work Start</label>
				<label style="padding-left:113px;_padding-left:113px;">End</label>
				
			</div>
			<div class="easyui-paddingbfpx">
				<span style="padding-right:5px">
				<input type="checkbox" id="chbbdmsWostartflag" name="chbbdmsWostartflag" value="Y"  value = "${requestScope.bdFormBean.disablebdmsWostart == true ? ' disabled':''}"/>
				</span>
				<span>
				<input id="dtebdmsWostarttime" name="dtebdmsWostarttime" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsWostarttime}"  style="width: 85px;"  value = "${requestScope.bdFormBean.disablebdmsWostart == true ? ' disabled':''}"/>
				</span>
				<span class="spinner">
				<input  id="spnbdmsWostart" name="spnbdmsWostart" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.bdFormBean.bdmsWostart}" min="00:00" showseconds="false" style="width: 60px;"  value = "${requestScope.bdFormBean.disableWostarttime == true ? ' disabled':''}"/>
				</span>
				<input type="hidden" id="hdnwostFlag" name="hdnwostFlag" value="${requestScope.bdmTlMst.bdmsWostartflag}"/>	
				
				<span style="">
				<input type="checkbox" id="chbbdmsWoendflag" name="chbbdmsWoendflag" value="Y"  value = "${requestScope.bdFormBean.disablebdmsWoend == true ? ' disabled':''}"/>
				<span>
				<input id="dtebdmsWoendtime" name="dtebdmsWoendtime" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsWoendtime}" style="width: 85px;"  value = "${requestScope.bdFormBean.disablebdmsWoend == true ? ' disabled':''}"/></span>
				<span class="spinner">
				<input  id="spnbdmsWoend" name="spnbdmsWoend" class="easyui-timespinner spinner-text validatebox-text" min="00:00" value="${requestScope.bdFormBean.bdmsWoend}" showseconds="false" style="width: 60px;"  value = "${requestScope.bdFormBean.disablebdmsWoendtime == true ? ' disabled':''}"/></span>
				
				<input type="hidden" id="hdnwoendFlag" name="hdnwoendFlag" value="${requestScope.bdmTlMst.bdmsWoendflag}"/>
				<input type="hidden" id="hdnProblemseverity" name="hdnProblemseverity" value="${requestScope.bdmTlDtl.bdanProblemseverity}"/>
				
				</span>
				<span id="err_dtebdmsWostarttime" class="tpm-errormsg">
				</span>
				<span id="err_dtebdmsWoendtime" class="tpm-errormsg"></span>
			</div>
			

			<div style="padding-left:22px;_padding-left:28px;">
				<label>Prod. Started</label>
				<label style="padding-left:92px;_padding-left:95px;">Break</label>				
			</div>
			<div class="easyui-paddingbfpx">
				<span style="padding-right:5px"><input type="checkbox" value="Y" id="chbbdmsWoprodaccepflag" name="chbbdmsWoprodaccepflag"  value = "${requestScope.bdFormBean.disablebdmsProdaccepdate == true ? ' disabled':''}"/></span>
				<span><input id="dtebdmsProdaccepdate" name="dtebdmsProdaccepdate" clear="false" value="${requestScope.bdmTlMst.bdmsProdaccepdate}" class="easyui-datebox"  style="width: 85px;"  value = "${requestScope.bdFormBean.disablebdmsProdaccepdate == true ? ' disabled':''}"/> </span>
				<span class="spinner"><input  id="spnbdmsProdacceptime" name="spnbdmsProdacceptime" value="${requestScope.bdFormBean.bdmsProdacceptime}" class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;"  value = "${requestScope.bdFormBean.disablebdmsProdacceptime == true ? ' disabled':''}"/></span>
				
				<input type="hidden" id="hdnProdstFlag" name="hdnProdstFlag" value="${requestScope.bdmTlMst.bdmsWoprodaccepflag}"/>
				<input type="hidden" id="hdnPriority" name="hdnPriority" value="${requestScope.bdmTlMst.bdmsPriority}"/>
			
				<span style="padding-left: 13px"><input type="text" id="txtbdmsBreaktime" name="txtbdmsBreaktime" value="${requestScope.bdmTlMst.bdmsBreaktime}" class="easyui-text"  style="height: 22px;width:155px;"  value = "${requestScope.bdFormBean.disablebdmsBreaktime == true ? ' disabled':''}"/></span>
				<span id="err_dtebdmsProdaccepdate" class="tpm-errormsg"></span>
			</div>
			<div class="bdRootcauseTxtArea"><label>Root Cause</label></div>
		    <div class="easyui-paddingbfpx"><textarea id="txabdanRootcause" name="txabdanRootcause"  maxlength="490" style="resize:none;width:340px;height:70px;"  value = "${requestScope.bdFormBean.disablebdanRootcause == true ? ' disabled':''}"/>${requestScope.bdmTlDtl.bdanRootcause}</textarea></div>
		    
			<div style="padding-top:3px;"><label class="" id="lblBDClassfcn">Root Cause Classification</label></div>
			<div class="easyui-paddingbfpx">
				<input id="cmbbdanClassificationid" name="cmbbdanClassificationid" value="${requestScope.bdmTlDtl.bdanClassificationid}" class="easyui-combobox" style="width: 310px"  value = "${requestScope.bdFormBean.disablebdanClassificationid == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button"  id="btnClassifcnLink" value="..." style="height: 22px;"/>
				<span id="err_cmbbdanClassificationid" class="tpm-errormsg"></span>
			</div>
	
			<div class=""><label>Reason For Problem</label></div>
		    <div class="easyui-paddingbfpx">
		    <input id="txtbdmsProblemreason" name="txtbdmsProblemreason"  value="${requestScope.bdmTlMst.bdmsProblemreason}" class="easyui-text"  style="width: 340px"  value = ""/>
		   
		    </div>
		    
			<div class="bdCounterMsrTxtArea"><label>Countermeasure</label></div>
			  <div class="easyui-paddingbfpx"><textarea id="txabdanCountermeasure" name="txabdanCountermeasure" maxlength="490" readonly="readonly"  style="resize:none;background-color:#D1E2FD;width:340px;height:70px;"  value = "${requestScope.bdFormBean.disablebdanCountermeasure == true ? ' disabled':''}"/>${requestScope.bdmTlDtl.bdanCountermeasure}</textarea></div>
		<div><label id="lblFinalAction">Final Action Taken</label></div>
			<div class="easyui-paddingbfpx"><textarea id="txtbdanFinalaction" name="txtbdanFinalaction" maxlength="490"  style="resize:none;width:340px;height:70px;"  value = "${requestScope.bdFormBean.disablebdanFinalaction == true ? ' disabled':''}"/>${requestScope.bdmTlDtl.bdanFinalaction}</textarea>
				<span id="err_txtbdanFinalaction" class="tpm-errormsg"></span>
		   </div>			  
		   <div><label class="mandatory-lbl" id="lblAnalysedBy">Completed By</label>
		   <label class="mandatory-lbl" style="margin-left:100px" id="lblCompleteddate">Date</label></div>
			<div style="">
			<span>
			<input type="checkbox" id="chkChkCompletedBy" style="width: 10px" name="chkChkCompletedBy" value="Y"  value = "${requestScope.bdFormBean.disablebdanCompletedby == true ? ' disabled':''  }"/>
			</span>
			<input id="cmbbdanCompletedby" readonly="readonly" name="cmbbdanCompletedby" value="${requestScope.bdmTlDtl.bdanCompletedby}" class="easyui-combo" style="width:155px"  value = "${requestScope.bdFormBean.disablebdanCompletedby == true ? ' disabled':''}"/>
				<span style="margin-left:1px">
			<input id="dtebdmsCompleteddate" readonly="readonly" name="dtebdmsCompleteddate" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsCompleteddate}"  style="width:85px;" /> 
			 </span> 
 			 <span class="spinner"> 
 			 <input  id="spnbdmsCompletedtime" name="spnbdmsCompletedtime" value="${requestScope.bdFormBean.bdmsCompletedtime}" class="easyui-timespinner spinner-text validatebox-text" min="00:00" readonly="readonly" showseconds="false" style="width: 52px;"  value = "${requestScope.bdFormBean.disablebdmsProdacceptime == true ? ' disabled':''}"/> </span> 
			 <input type="button" class="easyui-button" id="btnMultipleResp" value="..." style="height: 22px;width:22px;" />
			
			<span id="err_cmbbdanCompletedby" class="tpm-errormsg"></span>
			<span id="err_dtebdmsCompleteddate" class="tpm-errormsg"></span>
			
			</div>
			<div>
			
			</div>
		
				</div>
		
		
		 <div class="bdTabContent3" style="width:300px;float:right;margin-right:20px ">
		 <div class="sub-header"> Additional Informations  </div>
		 <div> <label style="">Activity</label><label style="margin-left:130px">Down Time</label></div>
		 <div class="easyui-paddingbfpx">
		 <span style="vertical-align:6px;" >
					<select id="cbobdmsActivity"  name="cbobdmsActivity"   readonly="readonly"  value="${requestScope.bdmTlMst.bdmsActivity}"  style="height: 22px;width:160px;"  value = "${requestScope.bdFormBean.disablebdanProblemseverity == true ? ' disabled':''}"/>
							<option value="N"> </option>
							<option title="Breakdown" value="B">Breakdown</option>
							<option title="Minorstopage" value="M">Minor Stopage</option>
					</select> 			
		
		<input id="txtbdmsDowntime" name="txtbdmsDowntime" value="${requestScope.bdmTlMst.bdmsDowntime}"  type="text" class="easyui-text"  readonly="readonly" style="height:22px;width:95px;margin-top:-5px"  value = "${requestScope.bdFormBean.disablebdmsDowntime == true ? ' disabled':''}"/>
		</span>
		</div>
		 <div> <label style="">Problem Severity</label></div>
		 <div class="easyui-paddingbfpx">
		 <span style="">
					<select id="cbobdanProblemseverity"  name="cbobdanProblemseverity"  value="${requestScope.bdmTlDtl.bdanProblemseverity}"  style="height: 22px;width:160px;font-size:9px;"  value = "${requestScope.bdFormBean.disablebdanProblemseverity == true ? ' disabled':''}"/>
							<option value="N"> </option>
							<option title="LINE STOPPER" value="L"> LINE STOPPER</option>
							<option title="URGENT" value="U"> URGENT</option>
							<option title="REQUIRES ATTENTION" value="R"> REQUIRES ATTENTION</option>
							<option title="HEALTH AND SAFETY" value="H"> HEALTH AND SAFETY</option>
					</select> 			
				</span>
		 	</div>	 
		 	<!-- priyanka -->
		 	<!-- <div><label>Cause</label></div> -->
		 	<div><label>Cause</label>
		 	<input type="checkbox" id="chkOtherCause" style="margin-left:140px" name="chkOtherCause" value="Y"/>
    		<label style="margin-left:3px">Others</label>
		 	</div>
		 	<!-- end -->
			<div class="easyui-paddingbfpx">
				<input id="cmbbdmsFinalcause" name="cmbbdmsFinalcause"  value="${requestScope.bdmTlMst.bdmsBookedcause}"  class="easyui-combobox" style="width: 264px"  value = "${requestScope.bdFormBean.disablebdmsFinalcause == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button" id="btnCauseLink" value="..." style="height: 22px;"/>
			</div> 
		 	
			<div><label>Alarm#</label></div>
			<div style="padding-bottom:15px;">
				<input  id="cmbbdmsAlarmdescription" name="cmbbdmsAlarmdescription"  value="${requestScope.bdmTlMst.bdmsAlarmdescription}" class="easyui-combobox" style="width: 264px"  = "${requestScope.bdFormBean.disablebdmsAlarmdescription == true ? ' disabled':''}"/>
				<input type="button" class="easyui-button" id="btnAlarmLink"  value="..." style="height: 22px;display:none" />
			</div>

			
			<div >
				
								
			</div>
			<div style="padding-top:10px;">
			<label>StandBy</label>&nbsp;&nbsp;
			<label> Equipment</label></div>
			<div class="easyui-paddingbfpx">
				<input type="checkbox" id="chkbdmsIsstandby" name="chkbdmsIsstandby" value="Y"  value = "${requestScope.bdmTlMst.bdmsIsstandby == 'Y' ? ' checked':' '}"/> 
				<input type="text" id="cmbbdmsStandbyequipment" name="cmbbdmsStandbyequipment" 
				class="easyui-combobox"  style="width: 280px;"
				  ${requestScope.bdFormBean.disablebdmsMachineid == true ? ' disabled':''}
				  value = "${requestScope.bdmTlMst.bdmsStandbyequipment }"/>
			</div>
			
		     
			 <div class="bdRootcauseTxtArea"><label>Remarks</label></div>
			  <div class="easyui-paddingbfpx"><textarea id="txabdmsRemarks" name="txabdmsRemarks"  maxlength="490" style="resize:none;width:294px;height:70px;"  value = "${requestScope.bdFormBean.disablebdmsRemarks == true ? ' disabled':''}"/>${requestScope.bdmTlMst.bdmsRemarks}</textarea></div>
			<div style="padding-top:40px;_padding-top:40px;display:none">
				<label>Down Time</label>
				<span style="padding-left:40px;_padding-left:40px;"><label>BreakDown Time</label></span>
			</div>
			<div>
				<input type="text" id="txtbdmsBreakdowntime" name="txtbdmsBreakdowntime" class="easyui-text" style="width:80px;display:none" disabled="disabled" value="${requestScope.bdmTlMst.bdmsBreakdowntime}">
				<span style="padding-left:20px;_padding-left:20px;">
					<input type="text" id="txtBDDownTime" name="txtBDDownTime" class="easyui-text" style="width:80px;display:none" disabled="disabled" value="${requestScope.bdmTlMst.bdmsDowntime}">
				</span>			
			</div>
		   

			
	     </div><!-- END Of Left Side -->
	 </div>  
	 
  <!--   <div title="WHY WHY Analysis"  style="overflow:auto;padding:4px;">  
     <div id="prevloadYY" class="loading"></div>
	 <div id="loadYY"></div>
	 <input type="hidden" id="bdanNo" name="bdanNo" value="${requestScope.bdmTlDtl.bdanWwno}"/>
	 <input type="hidden" id="txtbdanWwno" name="txtbdanWwno" value="${requestScope.bdmTlDtl.bdanWwno}"/>
	 <input type="hidden" id="yyModeField" name="yyModeField" value="${requestScope.whywhyMode}"/> 
	 </div> -->  <!-- *******End of Why Why Div********* -->
	   
        <!--<div title="Downtime Breakup"  style="padding:4px;" >  Downtime Div 
 				
          		<div style="float:left;padding-right: 5px">
          			<table id="DwnTmeBrkupGrid" width="400px" style="float: left;"></table> 
          		</div>
				<div id="DwnTmeBrkupPager"></div>  
				<div class="floatleft easyui-paddingbfpx" style="padding-top:5px;">
				<div class="easyui-paddingbfpx">	<input type="button" id="btnDTRefresh" class="easyui-button"  value="Refresh" style="width:140px;height: 22px"/></div>	
				<div><label>Downtime</label></div>
				<div class="easyui-paddingbfpx"><input type="text" id="downTime" name="downTime" class="easyui-text" style="width: 150px;" readonly="readonly"/></div>
				<div><label>Breakup Time</label></div>
				<div class="easyui-paddingbfpx">	<input type="text" class="easyui-text" id="breakupTime" name="breakupTime" style="width: 150px;" readonly="readonly"/></div>
				<div><label>Balance Time</label></div>
				<div class="easyui-paddingbfpx">	<input type="text" class="easyui-text" id="balTime" name="balTime" readonly="readonly" style="width: 150px;"/></div>
				<div class="easyui-paddingbfpx"><ul>
  						<li ><img alt="" src="images/circle.gif" style="size: 10px"><label>Holiday</label></li>
 						 <li> <img alt="" src="images/circle.gif" style="size: 10px"><label>Time Mismatch</label> </li>
 				  	</ul>
				</div >
				<div><label>No Plan Mins</label></div>
				<div class="easyui-paddingbfpx">	<input type="text" class="easyui-text" style="width: 150px;" id="noPlanMins" name="noPlanMins" readonly="readonly"/></div>
				<div><label>Downtime Mins</label></div>
				<div class="easyui-paddingbfpx">	<input type="text" class="easyui-text" style="width: 150px;" id="downTimeMins" name="downTimeMins" readonly="readonly" /></div>
						 
			</div> Right Side Div 
			
   	 </div> **********End Of Downtime Div******** -->
   	 <div title="Communication"  style="padding:4px;">  <!-- Communication Div -->
	    	  <div id="prevloadComText" class="loading"></div>
				<div id="loadComText"></div>
						<!--            <div class="floatleft" style="padding-right: 20px;">&nbsp;</div>		      -->
						<!--           	<div class="floatleft">-->
						<!--            	<div><label>Communication</label></div>-->
						<!--            	<div class="easyui-paddingbfpx">-->
						<!--					<span style="padding-right: 20px">-->
						<!--            			<textarea style="width : 620px; height : 70px;resize:none;" name="txawcmlCommunicationtext" id="txawcmlCommunicationtext" <c:out value = "${requestScope.bdFormBean.disablebdmsEntrydate == true ? ' disabled':''}"/>></textarea>-->
						<!--            		</span>  	-->
						<!--					<span style="padding-right: 12px"><input type="button" value="Insert" id="btnInsert" class="easyui-button"  style="width:140px;height: 22px;"/></span>-->
						<!--            		<span><input type="button" class="easyui-button"  id="btnComm" value="Communication" style="width:100px;height: 25px;"/></span>-->
						<!--            		<span id="err_txawcmlCommunicationtext" class="tpm-errormsg"></span>-->
						<!--            	</div>-->
						<!--		    </div>-->
						<!--            <div class="clear"></div>-->
						<!--             <div>-->
		  		<div>
	    		<table id="CommnGrid"><tr><td></td></tr></table> 
			  </div>
				<!--				 <div id="CommnPager"></div> 	-->
				<!--			 </div>-->
		</div> <!-- End of Communication tab  -->  
	   <!-- <div title="Cost Info"   style="padding:4px;"> 
			<div style="padding-left:0px;">
				<div class="floatright" style="">
				<span id="estBtnShow">
					<input id="btnEstimation" name="btnEstimation" type="button" class="easyui-button"  value="Resource Estimation"  style="width:140px; height:22px;"/></span>
					<input id="btnActual" name="btnActual" type="button" class="easyui-button"  value="Cost Information"  style="width:140px; height:22px;"/>	
				</div>
			
	          	<div class="floatleft"><table id="costSumryGrid" width="400px"></table></div> 
	
				<div class="clear"></div>
				<div class="floatleft"><table id="tblEmpCostActGrid" width="400px"></table> </div>
				<div id="empCostActPager" style="float: left;"></div>   
		    </div>
	   </div> --> <!-- ******End of Cost info Tab********* -->
		
 
		<div title="SAP Information"  class="hidetabs tabs-header"  style="padding:4px;height:auto">  
			<div id="prevloadSapInfo" class="loading"></div>
			<div id="loadSapInfo">
			
			</div>	
			
	   </div> 
	    <!--  <div title="Spares Replaced" class="hidetabs tabs-header"   style="padding:4px;height:auto"> 
			<div id="prevloadSparesReplaced" class="loading"></div>
			<div id="loadSparesReplaced"></div>
	   </div> 
	   <div title="External Service" class="hidetabs tabs-header"   style="padding:4px;display: none"> 
			 <div id="prevloadExtService" class="loading"></div> 
			<div id="loadExtService"></div>
	   </div> 
	   <div title="External Repair" class="hidetabs"   style="padding:4px;display: none"> 
	   		<div id="prevloadExtRepair" class="loading"></div>
			<div id="loadExtRepair"></div>			
	   </div>  -->
	  
 
	 </div>
  </div>
</div>
</div>
 <input type="hidden" id="hdnbdnkeyID" name="hdnabnkeyID" value="${requestScope.keyid}"/>
 <input type="hidden" id="hdnExtServiceID" name="hdnExtServiceID" value="${requestScope.bdmTlMst.externalserviceId}"/>
 <input type="hidden" id="hdncbobdmsActivity" name="hdncbobdmsActivity" value="${requestScope.bdmTlMst.bdmsActivity}"/>
 <input type="hidden" id="hdnBdanWwrequired" name="hdnBdanWwrequired" value="${requestScope.bdmTlDtl.bdanWwrequired}"/>
 <input type="hidden" id="hdnBdanWwno" name="hdnBdanWwno" value="${requestScope.bdmTlDtl.bdanWwno}"/>
 <input type="hidden" id="hdnBdanErpnumber" name="hdnBdanErpnumber" value="0"/>
 <input type="hidden" id="hdnBdmsRepeatedbdWhWhyNo" name="hdnBdmsRepeatedbdWhWhyNo" value=""/>
 <input type="hidden" id="hdnBdmsStatus" name="hdnBdmsStatus" value="${requestScope.bdmTlMst.bdmsStatus}" />
 <input type="hidden" id="hdnMultiresp" name="hdnMultiresp" value=""/> 
 <input type="hidden" id="hdnalready" name="hdnalready" value=""/>
 <input type="hidden" id="url" name="url" value="db"/>
 <input type="hidden" id="hdnErpStatus" name="hdnErpStatus" value="${requestScope.erpStatus}"/>
 <input type="hidden" id="mode" name="mode" value="${requestScope.formMode}">
 
</form>