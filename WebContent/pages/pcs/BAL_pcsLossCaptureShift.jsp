<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<script type="text/javascript">
	jQuery.noConflict();
 	jQuery(document).ready(function(){
	initialiseForm('frmPcsLoss');
	jQuery('#submitForm').val('frmPcsLoss');
	var shiftWise=jQuery('#hdnisShiftWise').val();
	
		var sectId = jQuery("#frmPcsLoss input[id='section']").val();	
				
		if(shiftWise=='Y'){
			jQuery('#spnlossHour').hide();
		/* 	 */
			jQuery("#spnLblHour").hide();
			jQuery("#spnprdHour").hide();
			jQuery("#lblHourL").hide();
			jQuery('#spnlblPrd').css('padding-left','70px');
			jQuery('#spnPrdcmb').css('padding-left','70px');
			jQuery('#spnlblMan').css('margin-left','5px');
			jQuery('#spnlblRnt').css('margin-left','10px');

		}
		else {
			jQuery('#spnprdHour').show();
			//jQuery('#selPprdHour').combobox('enable');
			jQuery('#spnlossHour').show();
			jQuery("#lblHourL").show();
			jQuery("#spnLblHour").show();


		}
//	jQuery('#lblLossReason').html('Loss Reason'); cmbPlosSectionid
	//disableField("frmPcsLoss", "cmbPlosMachineid");
   // readOnlyFields("cmbPlosMachineid");
	
	//jQuery('#cmbPlosMachineid').combobox('disable',true);
	//$('#dropDownId').attr('disabled', true);
	jQuery('#cmbPlosMachineid').combobox('disable');
	 jQuery('#cmbPlosMachineid').combobox('disable');
	  jQuery('#cmbPlosToolId').combobox('disable');
		 
	// jQuery('#btnSpareshReplaced').prop('disabled',true);
	 jQuery('#btnLink').prop('disabled',true);

		 jQuery('#btnLink').mouseover(function(){
		 jQuery('#btnLink').css("Title", "Loss Details");
	    });
		 jQuery("#lblAreaS").hide();
			jQuery("#inArea").hide();
	
	jQuery('#lblplosSource').hide();
	jQuery('#plosSourcediv').hide();
	  jQuery('#cmbPlosSource').combobox('disable');			   
		
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var hours = currentTime.getHours();
	if(hours < 10)
		{
			hours = "0" + hours;
		}
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}

	jQuery("#txtPprdBatchqty").val(0);
	jQuery("#txtPprdWeightPiece").val(0);
	
//	jQuery("#txtLossHourfromtime").val(hours);
//	jQuery("#txtLossMinutesfromtime").val(minutes);
	/* fillWithCurrentDate("spnPlosFromtime");
	fillWithCurrentDate("spnPlosTotime");
	fillWithCurrentTime("txtLossHourfromtime","txtLossMinutesfromtime");
	fillWithCurrentTime("txtLossHourtotime","txtLossMinutestotime"); */
	
	//sriram
	fillWithCurrentDate("spnPlosFromtime");
	fillWithCurrentDate("spnPlosTotime");
	jQuery("#txtLossHourfromtime").val(hours);
	jQuery("#txtLossMinutesfromtime").val(minutes);
	jQuery("#txtLossHourtotime").val(hours);
	jQuery("#txtLossMinutestotime").val(minutes);
	
	//fillComboBox("frmBDpcs","cmbbdmsMachineid","machineCombo.commonFilter?flid="+flId );	
	jQuery("#Pcsentrycompleted").hide();
	//jQuery("#Pcsentryincompleted").show();		
	disableField("frmPcsLoss", "txtPlosProdImpQty");
	disableField("frmPcsLoss", "chkPlosDurationSel");
	numericTextBox('txtPlosProdImpQty');
	var factId = "";//jQuery("#frmPcsCalendar input[id='factory']").val();
	var dataString = '?&factId='+factId+'&sectId='+jQuery("#frmPcsLoss input[id='section']").val();
	dataString += '&cellId='+jQuery("#frmPcsLoss input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
	
	/*setTimeout(function() {
		var flid="";
		flid=jQuery("#frmPcsLoss input[id='flid']").val();
		dataString +='?&flid='+flid;
		processAjaxCalls('txt_shift.Bbrdn',dataString,'getPCSShift','getPCSShiftErr');
	},10);
*/		



	jQuery("#rejectionGridbtn").hide();
//	jQuery("#rejectionGridbtn").show();
	fillComboBox("frmPcsLoss","cmbPlosCellid","cellCombo.commonFilter" );
	fillComboBox("frmPcsLoss","cmbPlosShiftid","shift.commonFilter?&frmRfilter=yes&factId="+factId);
	
	
	fillComboBox("frmPcsLoss","cmbPlosProdImpact","","",false);	
	//processGridnew("pcsproductionEntry_input.pcs","","pcsproductionGrid","pcsproductionPager","Production","","","pcsproducationGrid_successcallback");
	//processGridnew("pcsrejectionEntry_input.pcs","","pcsrejectionGrid","pcsrejectionPager","Rejection","","","pcsrejectionGrid_successcallback");
	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	fillComboBox("frmPcsLoss","cmbPlosMachineid","machineCombo.commonFilter?cellId="+cellId+"&flid="+flId );
	fillComboBox("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs?cellId="+cellId);
	fillComboBox("frmPcsLoss","cmbPprdProductid","combo_product.balpcs?cellId="+cellId+"&entryDate="+entryDate);
	fillComboBox("frmPcsLoss","cmbPprdProductmodel","combo_productModel.balpcs?cellId="+cellId);
	fillComboBox("frmPcsLoss","cmbPprdManpower","combo_Manpower.balpcs?cellId="+cellId);
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();			
	var ds = "?&sectId="+sectId+"&isQtyLoss=";
	
	
	fillComboBox("frmPcsLoss","cmbMainLoss","combo_Loss.balpcs"+ds);
	fillComboBox("frmPcsLoss","cmbPlosTradeid","trade.commonFilter");
	formatDateBox('dtePlosDate','dd-MMM-yyyy');
	formatDateBox('dtePlosFromdate','dd-MMM-yyyy');
	formatDateBox('dtePlosTodate','dd-MMM-yyyy');
	formatDateBox('dteShiftstartdate','dd-MMM-yyyy');
	formatDateBox('dteShiftenddate','dd-MMM-yyyy');
	//var flid = jQuery("#frmEmpPage input[id='flid']").val();
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var entryDate=jQuery("#dtePlosDate").datebox('getValue');
	var shift=jQuery("#cmbPlosShiftid").combobox('getValue');
	fillComboBox("frmPcsLoss","cmbSelPrdHourL","combo_LossEntHourno.balpcs?cellId="+cellId+"&entryDate="+entryDate+"&shift="+shift);//
	
	    //if (flid != null)
	    	
	    	if (flid != null && jQuery.trim(flid).length > 0)
        
   		loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.balpcs","pcsLossLocationnValues","frmPcsLoss","&flid="+flid);
    else
		loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.balpcs","pcsLossLocationnValues","frmPcsLoss","");
   // var sectId = jQuery("#frmPcsLoss input[id='section']").combobox("getValue");
    if(sectId == null || sectId == ' ' ){
			
        }
    else{
        }
    
   jQuery("#cmbPprdProductid").combobox({
		filter: function(q, row){
			var opts = jQuery(this).combobox('options');
			return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;	
			
			
		//	LoadPopUp("toolAlert","toolAlertsTommorow_input.alerts?button=N","","850px","250px","350px","150px","","TOOL CHANGE  PLAN","","",true);
					
		}
	});
   jQuery("#cmbPprdProductmodel").combobox({
		filter: function(q, row){
			var opts = jQuery(this).combobox('options');
			return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
		}
	});
   jQuery("#cmbMainLoss").combobox({
		filter: function(q, row){
			var opts = jQuery(this).combobox('options');
			return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
		}
	});
		
   jQuery('#selPprdHour').bind('change',function(){

		fillHourlyCalculations();
	});
   jQuery('#txtLossHourfromtime').bind('change',function(){

 		lossFromTimeMinutesValidations();
	});
   jQuery('#txtLossMinutesfromtime').bind('change',function(){

		lossFromTimeMinutesValidations();
	});
   jQuery('#txtLossHourtotime').bind('change',function(){

		lossToTimeMinutesValidations();
	});
   jQuery('#txtLossMinutestotime').bind('change',function(){
	
		lossToTimeMinutesValidations();
	});

    jQuery("#btnRejBreakup").click(function() {

		 var keyid = jQuery("#hdnPprdKeyid").val();
	
	  	 if(keyid != null && keyid != 'undefined' && keyid != undefined	&& keyid != "" && keyid.trim().length > 0) {
			   addNewGridRow('pcsrejectionGrid');
			 }
		 else
			 alert("1st Save productiondata");
		 
	 });
  
  
	 jQuery("#tabLossEntry").tabs({ onLoad:function(title){
		 jQuery('.tabs-panels').css('height','350');
		 }
	 });
	 var tabs = jQuery('#tabLossEntry').tabs('tabs');
	 //var x= jQuery("#tabLossEntry").tabs('select', tabs[0].panel('options').title);
		 jQuery("#tabLossEntry").click(function(){
			if(tabs[1].panel('options').title=="Loss Entry"){
				//disableField("frmPcsLoss","chkPprdShiftnoplan");
				//disableField("frmPcsLoss","chkPprdHourlynoplan");
				
				}
			else if(tabs[0].panel('options').title=="Production"){
					enableField("frmPcsLoss","chkPprdShiftnoplan");
					enableField("frmPcsLoss","chkPprdHourlynoplan");
				}
			else{
				//disableField("frmPcsLoss","chkPprdShiftnoplan");
				//disableField("frmPcsLoss","chkPprdHourlynoplan");
	
			}
	 
		});
		 
		
		// function comboFillWithTrade()
	//	 {
			/* var location = jQuery("#frmAbnormality input[id='location']").val();
			 var trade = jQuery("#cmbAbnmTradeid").combobox("getValue");
			 var cellId = jQuery("#frmAbnormality input[id='cell']").val();
			// var trade = getFieldValue("cmbAbnmTradeid");	 
			 var rep = jQuery('#chkAbnmRepOtheres:checked').val();
			 //jQuery("#cmbAbndResponsiblity").combobox('setValue',"");
			*/
					
		// }
	jQuery("#btnView").click(function(){

	
	//	var dataStr = "";
		var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
		var date = jQuery('#dtePlosDate').datebox('getValue');
		var shift = jQuery('#cmbPlosShiftid').combobox("getValue");
	
		if (cellId == "")
		 { alert("Select cell");		return ; 	}		
		if (date == "") {   alert("Select Date");		return ;	}
		if (shift == "") {	alert("Select Shift");		return ;	}	

		var calOrDat ="D";
		fnValidateDate(calOrDat, date,shift);
		fnValidateDateEntry();
		pcsviewHourlyResultGrid();
		//navigateToNextForm("OtherLossEntry_input.pcs" ,'Production Other Losses',null,null);

		//LoadPopUp("loadPCSResult", "OtherLossEntry_input.pcs?", true,"1154px","450px","5px","5px", "otherLoss_successCallBack","Production Losses",true);
	});
	//viewResultGrid();
	//spinnerKeyPress('spnPlosFromtime');
	//spinnerKeyPress('spnPlosTotime');

	var selDurationSel = jQuery("#chkPlosDurationSel").is(':checked');
			if(selDurationSel == true){
				jQuery("#cmbSelPrdHourL").combobox('setValue','-');
			}
			else if(selDurationSel == false) {
				}
			else{}
			//disableField("frmPcsLoss", "cmbMainLoss");
	enableFields("txtPlosLosstime");
    readOnlyFields("dtePlosFromdate");
    readOnlyFields("txtLossHourfromtime");
    readOnlyFields("txtLossMinutesfromtime");
    readOnlyFields("chkPlosTotime");
    readOnlyFields("dtePlosTodate");
    readOnlyFields("txtLossHourtotime");
    readOnlyFields("txtLossMinutestotime");	
	//readOnlyFields("txtPlosSource");
	
	jQuery('#chkPlosDurationSel').click(function(){
			var selDurationSel = jQuery("#chkPlosDurationSel").is(':checked');
			if(selDurationSel == true){
				jQuery('#cmbSelPrdHourL').combobox('disable');
				readOnlyFields("txtPlosLosstime");
				//jQuery('#cmbSelPrdHourL').val("");
				enableFields("dtePlosFromdate");
				enableFields("txtLossHourfromtime");
				enableFields("txtLossMinutesfromtime");
				enableFields("chkPlosTotime");
				enableFields("dtePlosTodate");
				enableFields("txtLossHourtotime");
				enableFields("txtLossMinutestotime");
				readOnlyFields("txtPlosLosstime");
				//jQuery("#cmbSelPrdHourL").combobox('setValue','-');
				setFieldValue('cmbSelPrdHourL','-');
				}
			else if(selDurationSel == false){
					jQuery('#cmbSelPrdHourL').combobox('enable');
					enableFields("txtPlosLosstime");
					jQuery('#cmbSelPrdHourL').combobox("disabled",false);
				}
			else{
				}
			
		});
	jQuery('#chkPlosTotime').click(function() {
		
		var toTime=jQuery("#chkPlosTotime").is(':checked');
		if(toTime == true )
			{
			jQuery("#lbltotime").removeClass("mandatory-lbl");
			jQuery("#dtePlosTodate").datebox('clear');
			jQuery('#spnPlosTotime').timespinner('clear');
			jQuery("txtLossHourtotime").val("");
			jQuery("txtLossMinutestotime").val("");
			jQuery("#txtPlosLosstime").val('');
			jQuery("#txtLossHourtotime").val('');
			jQuery("#txtLossMinutestotime").val('');
			
			}
			else
			{ 
				jQuery("#lbltotime").addClass("mandatory-lbl");
				fillWithCurrentDate("dtePlosTodate");
				fillWithCurrentDate("spnPlosTotime");
				jQuery("#txtLossHourtotime").val(currentTime.getHours());
				jQuery("#txtLossMinutestotime").val(currentTime.getMinutes());
					
			}	
	});	

	jQuery('#chkPprdShiftnoplan').click(function() {

		var shiftplan=jQuery("#chkPprdShiftnoplan").is(':checked');
		if(shiftplan == true )
			{
			  	jQuery("#chkPprdHourlynoplan").attr('checked', false);
			  	jQuery("#lblHour").removeClass("mandatory-lbl");
				jQuery("#selPprdHour").val('0');
				jQuery("#txtUnreptime").val('0');
				
			// 	jQuery("#lblPprdDuration").hide();
			//	jQuery("#txtPprdNoplanduration").val('');
			//	jQuery("#txtPprdNoplanduration").hide();
				applyNoplan();
			}
		else
			{
				removeNoplan();
				jQuery("#lblHour").addClass("mandatory-lbl");
				jQuery("#selPprdHour").val('');
			//	jQuery("#lblPprdDuration").hide();
			//	jQuery("#txtPprdNoplanduration").val('');
			//	jQuery("#txtPprdNoplanduration").hide();
			}
		
	});
	jQuery('#chkPprdHourlynoplan').click(function() {

		var hourplan=jQuery("#chkPprdHourlynoplan").is(':checked');
		if(hourplan == true )
			{
			//	applyNoplan();
				jQuery("#lblProduct").removeClass("mandatory-lbl");
				jQuery("#lblProductmodel").removeClass("mandatory-lbl");
				jQuery("#lblManpower").removeClass("mandatory-lbl");
				jQuery("#lblPrdqty").removeClass("mandatory-lbl");
				jQuery("#chkPprdShiftnoplan").attr('checked', false);
				jQuery("#lblHour").addClass("mandatory-lbl");
				jQuery("#lblPprdDuration").addClass("mandatory-lbl");
			//	jQuery("#txtPprdNoplanduration").show();
				jQuery("#txtPprdNoplanduration").val('');
				enableFields("txtPprdNoplanduration");	
				setFocusOnField("txtPprdNoplanduration");
			}
		else
			{
			//	removeNoplan();
			
			//	jQuery("#lblPprdDuration").hide();
				jQuery("#lblProduct").addClass("mandatory-lbl");
				jQuery("#lblProductmodel").addClass("mandatory-lbl");
				jQuery("#lblManpower").addClass("mandatory-lbl");
				jQuery("#lblPrdqty").addClass("mandatory-lbl");			
				jQuery("#lblPprdDuration").removeClass("mandatory-lbl");
				jQuery("#txtPprdNoplanduration").val('0');
			//	jQuery("#txtPprdNoplanduration").hide();
				readOnlyFields('txtPprdNoplanduration');
				setFocusOnField("txtPprdProducedqty");
			}
		
	});
	
	jQuery('#btnSave').click(function() {
		
//	alert("1"+jQuery("#txtPlosLosstime").val() );
		if(jQuery("#txtPlosLosstime").val() == "" || jQuery("#txtPlosLosstime").val() == "0" )
			{
				alert("Enter loss hours.");
				return false;
			}
		 var machineid= jQuery('#cmbPlosMachineid').combobox("getValue");
		 if(machineid==null || machineid==""){
				machineid="MCH";
			}
			else {
				machineid = machineid;
			}
		 var loss= jQuery('#cmbPlosLossid').combobox('getValue');
		 if( machineid =="MCH" && loss =="PLC/000044"  || machineid =="MCH" && loss =="PLC/000039" || machineid =="MCH" && loss =="PLC/000068" || machineid =="MCH" && loss =="PLC/000010" ){
				
				alert("Select Equipment");
				return false;
			}	
			else{
				}
	  	   

		//added on 09-jul-2015
		/*
		var selDurationSel = jQuery("#chkPlosDurationSel").is(':checked');
		if(selDurationSel == true) {
			var lossMins = jQuery("#txtPlosLosstime").val();
			fromdate = hourStartTime; //need to calculate
			todate = hourStartTime+lossMins;	 //nedd add function
		}*/
		     
		var losshours = jQuery('#txtPlosLosstime').val();
		if(losshours.startsWith("-"))
			{
				alert("Loss totime is less than loss from time. ");
				return false;
			}
		if (losshours.trim().length > 0 )
			{
					jQuery('#hdnUrl').val('');
					var formId = jQuery('#submitForm').val(); //defined in classic.jsp
					var plosKeyid = jQuery('#hdnPlosKeyid').val();
					var pldetailsId = jQuery('#hdnPldetailsId').val();
					var plrkKeyid = jQuery('#hdnPlrkKeyid').val();
					var availTime =jQuery("#txtAvailtime").val();
					var fromdate = jQuery('#dtePlosFromdate').datebox("getValue");
					var todate = jQuery('#dtePlosTodate').datebox("getValue");
					var shiftId = jQuery('#cmbPlosShiftid').combobox("getValue");
					var cmbSelPrdHourL =getFieldValue("cmbSelPrdHourL");
					var sourceName=jQuery('#txtPlosSource').val() ;
					var lossSource=jQuery('#cmbPlosSource').combobox("getValue");
					var shiftWise=jQuery('#hdnisShiftWise').val();
					var url = "frmPcsLoss_save.balpcs?&plosKeyid="+plosKeyid+"&pldetailsId="+pldetailsId+"&plrkKeyid="+plrkKeyid+"&fromdate="+fromdate+"&todate="+todate;
						url += "&Availtime="+availTime+"&shiftId="+shiftId+"&cmbSelPrdHourL="+cmbSelPrdHourL+"&machieid="+machineid+"&lossSource="+lossSource+"&sourceName="+sourceName+"&shiftWise="+shiftWise;
					//alert(url +" urll");
					if (plosKeyid =="" || plosKeyid ==" " || plosKeyid == 'undefined')
						url+="&saveMode=create";
					else
						url+="&saveMode=update";
		  saveForm(formId,url);
			}
	
		else
			{
				   alert("Enter Loss Hours");
			}
		});

	jQuery('#btnProductionSave').click(function() {
		productionDataSave();
	});

	jQuery("#btnRejSave").click(function() {

				 var pbrdKeyid = jQuery("#hdnPbrdKeyid").val();
				 var pprdKeyid = jQuery("#hdnPprdKeyid").val();
			
				jQuery('#hdnUrl').val("frmPcsLoss_rejectionsave.balpcs");
					 if(pprdKeyid != null && pprdKeyid != 'undefined' && pprdKeyid != undefined	&& pprdKeyid != "" && pprdKeyid.trim().length > 0) {

									var rejValid='';
									rejValid = rejectionGridMandatoryValidations();
									if(rejValid == false)
										{
										return false;
										}

									var formId = "frmPcsLoss";
									var ppbdKeyid = jQuery('#hdnPpbdKeyid').val();
									var url = "frmPcsLoss_rejectionsave.balpcs?&pprdKeyid="+pprdKeyid+"&ppbdKeyid="+ppbdKeyid;
									if (pbrdKeyid =="" || pbrdKeyid ==" " || pbrdKeyid == 'undefined')
										url+="&saveMode=create";
									else
										url+="&saveMode=update";

									saveForm(formId,url);
					 }
					 else
						 alert("1st Save productiondata");
		 });
				
	jQuery('#dtePlosDate').datebox({  	
		   
	   	onSelect:function(recordid)
			{
		   		var date = jQuery('#dtePlosDate').datebox('getValue');
	   			var shift = jQuery('#cmbPlosShiftid').combobox("getValue");
	   			var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
	   			var entryDate="";
	   			if (cellId==""){alert("Select cell");	return;	}
	   			if (date == "") {   alert("Select Date");		return ;	}
	   			if (shift == "") {	alert("Select Shift");		return ;	}
	   				

	   			clearAllFields();
	   			
	   			lossDateEvt("dtePlosDate");
	   			var todate = jQuery('#dtePlosDate').datebox('getValue');
	   			//alert(todate+"MM");
				entryDate=todate;
	   	/*		jQuery('#spnPlosFromtime').spinner('setValue',"00:00");
	   			jQuery('#spnPlosTotime').spinner('setValue',"00:00");

	   			setFieldValue("txtLossHourfromtime","00");
	   			setFieldValue("txtLossMinutesfromtime","00");
	   			setFieldValue("txtLossHourtotime","00");
	   			setFieldValue("txtLossMinutestotime","00");
		*/	   			
				
				setFieldValue("hdnSetFromToTime","1");
		
	   			setFieldValue('dtePlosTodate',todate);
	   			setFieldValue('dtePlosFromdate',todate);
	   			jQuery("#selPprdHour").val('');

	   			setFieldValue("hdnSetFromToTime","");
	   			fnLoadHourCombo();
	   			
	   			reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.balpcs?"+"cellId="+cellId+"&entryDate="+entryDate);

	   			setTimeout(function() {
	   				checkLastShiftProduct();
	   				
		   			viewProductionGrid();
		   			viewGrid();
		   			viewResultGrid();
		   			
	   			},100);
	   			
				var calOrDat ="D";
	   			fnValidateDate(calOrDat, date,shift);
	   			fnValidateDateEntry();

			} 
	});

	jQuery('#dtePlosFromdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			//lossDateEvt("dtePlosFromdate");
	   			//viewGrid();
			} 
	});
	jQuery('#btnLink').click(function(){
		var flId=jQuery("#frmPcsLoss input[id='flid']").val();
		var cellid=jQuery("#frmPcsLoss input[id='cell']").val();
		
		var shiftId=jQuery('#cmbPlosShiftid').combobox('getValue');
		var shifttext=jQuery('#cmbPlosShiftid').combobox('getText');
		var hourno=getFieldValue("selPprdHour");
		var lossdate = jQuery('#dtePlosDate').datebox("getValue");
		var availTime =jQuery('#txtAvailtime').val();
		var jhTime=jQuery("#txtPlosLosstime").val();
		var loss= jQuery('#cmbPlosLossid').combobox('getValue');

	/*	if(loss=="PLC/000049"||loss=="PLC/000083" ){				
					 var jhTime=jQuery("#txtPlosLosstime").val();	
					LoadPopUp("",'ShutDownLoss_input.pcs?Flid='+flId+'&cellid='+cellid+'&shiftId='+shiftId+'&shifttext='+shifttext+'&hourno='+hourno+'&lossdate='+lossdate+'&availtime='+availTime+'&jhTime='+jhTime+'&lossName=shutDown', true,"90%","500px","60px","20px", null, 'JH ENTRY',false, true,true);
				jaxCalls('getSubMainLossNo.pcs',"&subLossid="+record.id,'subLossMainIdSuccess','reasonLossIdError');	
				 }
		else
					LoadPopUp("",'speedLoss_input.pcs?Flid='+flId+'&cellid='+cellid+'&shiftId='+shiftId+'&shifttext='+shifttext+'&hourno='+hourno+'&lossdate='+lossdate+'&availtime='+availTime+'&jhTime='+jhTime+'&lossName=speed', true,"90%","500px","60px","20px", null, 'SPEED LOSS ENTRY',false, true,true);
				
		*/
		});
		
	jQuery('#dtePlosTodate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			//lossDateEvt("dtePlosTodate");
	   			//viewGrid();
			} 
	});
	
	spinnerKeyPress('spnPlosFromtime');
	spinnerKeyPress('spnPlosTotime');
	
/*	spinnerChange('spnPlosFromtime','fngetTotalLossTime');
	spinnerChange('spnPlosTotime','fngetTotalLossTime');
	
	spinnerUp('spnPlosFromtime','fngetTotalLossTime');
	spinnerUp('spnPlosTotime','fngetTotalLossTime');
	
	spinnerDown('spnPlosFromtime','fngetTotalLossTime');	
	spinnerDown('spnPlosTotime','fngetTotalLossTime');	
*/
/*	spinnerChange('spnPlosFromtime','lossStartEvt');
	spinnerChange('spnPlosTotime','lossEndEvt');
	spinnerUp('spnPlosFromtime','lossStartEvt');
	spinnerUp('spnPlosTotime','lossEndEvt');
	spinnerDown('spnPlosFromtime','lossStartEvt');	
	spinnerDown('spnPlosTotime','lossEndEvt');
*/		
	fillWithCurrentDate('dtePlosDate');
	//fillWithCurrentDate('dtePlosFromdate');
	//fillWithCurrentDate('dtePlosTodate');
});

jQuery("#txtPprdProducedqty").keyup(function(event){
    if(event.keyCode == 13){
    	setFocusOnField("txtPprdReworkqty");
    }
});
jQuery("#txtPprdReworkqty").keyup(function(event){
    if(event.keyCode == 13){
    	setFocusOnField("txtPprdRejectedqty");
     
    }
});
jQuery("#txtPprdRejectedqty").keyup(function(event){
    if(event.keyCode == 13){
    
        jQuery("#btnProductionSave").click();
        return false;
    }
});

jQuery('#btnLossDelete').click(function() {

	var formId = jQuery('#submitForm').val(); 
//	alert(formId);
	var rowId = jQuery("#pcslossGrid").jqGrid('getGridParam', 'selrow');
//		alert(rowId);
	if (rowId == null || rowId.length < 0) 
		{
			return false; 
		}		
	var rowData = jQuery("#pcslossGrid").jqGrid('getRowData',rowId);
	var PlrkKeyid =rowData.LossReasonId;
	jQuery('#hdnPldetailsId').val(rowData.PldetailsId);
	jQuery('#hdnPlosKeyid').val(rowData.Keyid);
	var Pldetailsid = jQuery('#hdnPldetailsId').val();
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();		
//	alert("Pldetailsid  "+Pldetailsid);
//	if (!msrNo==null && !msrNo=="" && !msrNo==" ") {
//		return ;}
	
	
	//jQuery('#hdnPlosKeyid').val('');
	var plmasterId = ""; //jQuery('#hdnPlmMasterId').val();
	var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
	var cellId = jQuery('#cmbPlosCellid').combobox('getValue');
	var lossVal = jQuery("#txtPlosLosstime").val();
	var entryDate = jQuery("#dtePlosDate").datebox('getValue');
	var shift = jQuery('#cmbPlosShiftid').combobox('getValue');
	var lossId = rowData.LossId;
	var plosKeyid = jQuery('#hdnPlosKeyid').val();
//	var url = "pcsLossEntryITC_delete.pcs" ;
	var url = "pcsLossEntryBajaj_delete.balpcs";
	url+= '?&PlrkKeyid='+PlrkKeyid+'&Pldetailsid='+Pldetailsid+'&factId='+factId+'&sectId='+sectId+'&cellId='+cellId+'&lossId='+lossId+'&lossVal='+lossVal;
	url+= '&plmasterId='+plmasterId+'&entryDate='+entryDate+'&shift='+shift+'&plosKeyid='+plosKeyid;		
//	alert(url);
	if(formId.length > 0 )
		saveForm(formId,url);
});

function fnLoadHourCombo(){
	var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
	var entryDate=jQuery('#dtePlosDate').datebox('getValue');
	var shiftId = jQuery('#cmbPlosShiftid').combobox("getValue");
	var ds = "&cellId="+cellId+"&entryDate="+entryDate+"&shiftId="+shiftId;	
	reloadCombo("frmPcsLoss","cmbSelPrdHourL","combo_LossEntHourno.balpcs?"+ds);
//	reloadCombo("frmPcsLoss","cmbPlosShiftid","shift.commonFilter?&shiftId="+shiftId);
//	reloadCombo("frmPcsLoss","cmbPlosShiftid","shift.commonFilter?&shiftId="+shiftId);
	checkPrdHrLNullVal();
	//jQuery("#cmbSelPrdHourL").combobox('setValue','-');
	//reloadCombo("frmPcsLoss","cmbPlosCellid","cellCombo.commonFilter?");
	//jQuery("#cmbSelPrdHourL").combobox('clear');
}
function pcsproducationGrid_successcallback(){

		var totalavailtime = jQuery("#txtAvailtime").val();
		//var noofhours=Number(totalavailtime)/Number(60);	
	//	noofhours=Math.ceil(hour);
	//	addHourValue =Number(noofhours) + Number(1) ;
		var noofHours = Number(jQuery('#selPprdHour > option').length);    	
		var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');//	row get data
		var gridAddHour = false;
		var prodtime = "0";
		var losstime = "0";
		var unreptime = jQuery("txtUnreptime").val();
	//	var losstime = jQuery("#txtLosstime").val();
		var hourValue = jQuery("#selPprdHour").val();
		var unreptimeHourly =Number("0"); // Number(jQuery("txtUnreptimehourly").val());
		var losstimeHourly = Number("0"); //Number(jQuery("#txtLosstimehourly").val());
		var noplandaurration = Number("0");
		var noplandaurrationHourly = Number("0");
		var shiftId = jQuery('#cmbPlosShiftid').combobox("getValue");
		var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
		var entryDate = jQuery('#dtePlosDate').datebox("getValue");
		var ds="cellId="+cellId+"&entryDate="+entryDate+"&shiftId="+shiftId;
		for(var i=1; i<= productionRow.length; i++)
		{
			var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);

				prodtime = Number(prodtime) + Number(rowData.Prodtimehourly);
				losstime = Number(losstime) + Number(rowData.Losstimehourly);
				noplandaurration = Number(noplandaurration) + Number(rowData.txtPprdNoplanduration);
		//		alert("prodtime"+prodtime +"  "+tempprodtime);
				if(rowData.selPprdHour == hourValue)
					{
						unreptimeHourly = Number(unreptimeHourly) +Number(rowData.Unreptimehourly);
						losstimeHourly = Number(losstimeHourly) +Number(rowData.Losstimehourly);
						noplandaurrationHourly = Number(noplandaurrationHourly) + Number(rowData.txtPprdNoplanduration);						
					}

				if(rowData.selPprdHour == noofHours)
				{
					
					gridAddHour =  true;
				}

		}
	
			unreptimeHourly = Number(unreptimeHourly).toFixed(2);
			losstimeHourly = Number(losstimeHourly).toFixed(2);
			
			unreptime = Number(unreptime).toFixed(2);
			prodtime = Number(prodtime).toFixed(2);
			losstime = Number(losstime).toFixed(2);
			
			unreptime = Number(totalavailtime) - Number(prodtime) - Number(losstime) - Number(noplandaurration);
			unreptime =  Number(unreptime).toFixed(2);
	//	}
			
			 if(jQuery('#chkPprdShiftnoplan').is(':checked')==true){				
				 unreptime = Number(unreptime).toFixed(2);
					prodtime = Number(prodtime).toFixed(2);
					losstime = Number(losstime).toFixed(2);					
					unreptime = Number(totalavailtime) - Number(prodtime) - Number(losstime) - Number(noplandaurration)-Number(totalavailtime);
					unreptime =  Number(unreptime).toFixed(2);
					unreptime =  Number(0).toFixed(2);
					
				// jQuery("txtUnreptimehourly").val(0);
				 }
				
		if(unreptimeHourly != 0 && unreptimeHourly != "0" && unreptimeHourly != 0.00 && unreptimeHourly != "0.00")
		{	jQuery("#txtUnreptimehourly").val(unreptimeHourly);
			jQuery("#txtLosstimehourly").val(losstimeHourly);
		}
//		alert("5 prodtime  "+prodtime+" losstime  "+losstime+" unreptime "+unreptime);
		jQuery("#txtProdtime").val(prodtime);
		jQuery("#txtUnreptime").val(unreptime);
		//alert('losstime :'+losstime);
		jQuery("#txtLosstime").val(losstime);
		
		if(gridAddHour)
			{
				totalavailtime =Number(jQuery("#hdnSftDuration").val()) + Number(60);
				jQuery("#txtAvailtime").val(totalavailtime);	
			}
	
		if(Number(unreptime) > Number(0.75))//&& jQuery("#chkPprdShiftnoplan").is(':checked')==true )
			{
			//alert(22);
				jQuery("#Pcsentryincompleted").show();
				jQuery("#Pcsentrycompleted").hide();
			}
		else
			{
				jQuery("#Pcsentrycompleted").show();
				jQuery("#Pcsentryincompleted").hide();
			}

		var rejQty=jQuery("#txtPprdRejectedqty").val();
		var rewQty=jQuery("#txtPprdReworkqty").val();
		if (rejQty!=0 || rewQty!=0){
					jQuery( "#pcsproductionGrid" ).setGridWidth(630);
					jQuery("#rejectionGridbtn").show();
					viewRejectionGrid();
					}
		else if(rejQty==0 && rejQty==0 )
		{
			jQuery( "#pcsproductionGrid" ).setGridWidth(1000);
			jQuery("#rejectionGridbtn").hide();
		}
		else
			{
				jQuery( "#pcsproductionGrid" ).setGridWidth(1000);
				jQuery("#rejectionGridbtn").hide();
			}
		
		 reloadCombo("frmPcsLoss","cmbSelPrdHourL","combo_LossEntHourno.balpcs?"+ds);
		 checkPrdHrLNullVal();
		 var rowID = 1;
		 var rowData1 = jQuery("#pcsproductionGrid").jqGrid('getRowData',rowID);
		 //var rowData2 = jQuery("#pcsproductionGrid").jqGrid('getRowData','8');
		 var da1=rowData1.Productid;
		// var rowData.Productid);
		// alert(da1+ 'product Id');
		 if(da1=='-' || da1=="-" || da1=='No plan'){
		//	 alert(da1+ 'product Id 1');
				jQuery('#chkPprdShiftnoplan').prop('checked', true);
				jQuery("#lblHour").removeClass("mandatory-lbl");
				jQuery("#selPprdHour").val('0');
				jQuery("#txtUnreptime").val('0');
				jQuery("#Pcsentrycompleted").show();
				jQuery("#Pcsentryincompleted").hide();
				
			 }
		 
		 /*
		var hourno = "1";
		
		var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
	//	alert(productionRow.length);
		if(productionRow.length != "0" || productionRow.length != '')
			{
	//		alert("In if");
				for (var i = 1; i <= productionRow.length; i++) {
				
	    			var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
					hourno = Number(rowData.selPprdHour);
				}
				if(hourno < noofHours)
					{
						hourno = Number(hourno) + Number(1);
					jQuery("#selPprdHour").val(hourno);
					}
				else
					jQuery("#selPprdHour").val('');
					
	 		}
		else
		{
			jQuery("#selPprdHour").val("1");
		}
		setFocusOnField('txtPprdProducedqty');  */
		//processAjaxCalls('getShiftStarEndTime.pcs',"&shiftId="+shiftId,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
}

function addRowsToEmptyGrid(noofhours)
{
	
	/*for(var j= 0;j<= noofhours; j++ )
	{
			addNewGridRow('pcsproductionGrid');
	}*/
//	alert("adding rows to emptyGrid");
	jQuery("#hdnAddrowtoGrid").val("Y");
	productionDataSave();	
};

function frmPcsLosscmbPlosCellid_onSelect(record){
	
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();
	
	var dataStr="&cellId="+record.id;
	var cellid=record.id;
	loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.balpcs","pcsLossLocationnValues","frmPcsLoss",dataStr);
	var shiftId = jQuery('#cmbPlosShiftid').combobox("getValue");
	var cellId = record.id;
	var entryDate = jQuery('#dtePlosDate').datebox("getValue");
	var ds = "&cellId="+cellId+"&entryDate="+entryDate+"&shiftId="+shiftId;	
//	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	reloadCombo("frmPcsLoss","cmbPprdProductmodel","combo_productModel.balpcs?cellId="+cellId+"&entryDate="+entryDate);
//	reloadCombo("frmPcsLoss","cmbMainLoss","combo_Loss.pcs");
	reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs?cellId="+cellId);
//	viewGrid();
 	fnLoadHourCombo();
 	//
 	/*var selDurationSel = jQuery("#chkPlosDurationSel").is(':checked');
	selDurationSel = true;
	if(selDurationSel == true)
		jQuery("#cmbSelPrdHourL").combobox('setValue','-');*/
//	var shift = jQuery('#cmbPlosShiftid').combobox('getValue');
		//var cellid=record.id;
	
		var flId=jQuery("#frmPcsLoss input[id='flid']").val();
		
		if(sectId=="SEC0000009" || sectId=="SEC0000015"){
			
			LoadPopUp("toolAlert","toolAlertsTommorow_input.alerts?button=N&flId="+flId,"","750px","300px","270px","200px","","TOOL CHANGE  PLAN","","",true);
			
			}
		if(cellid=="CEL0000089"){
			
			fillComboBox("frmPcsLoss","cmbPprdArea","combo_ProdArea.balpcs");
			//jQuery('#selPprdHour').is('disabled',true);
			jQuery("#cmbPprdManpower").combobox('disable');
			jQuery("#selPprdHour").prop("disabled", true);
			jQuery("#cmbPprdArea").prop('enabled',true);
			jQuery("#lblAreaS").show();
			jQuery("#inArea").show();
			
			}
		else{
		jQuery("#selPprdHour").prop("disabled", false);
		jQuery("#cmbPprdManpower").combobox('enabled',true);
		jQuery("#cmbPprdArea").prop('disabled',true);
		jQuery("#lblAreaS").hide();
		jQuery("#inArea").hide();
		
		//jQuery("#cmbPprdManpower").prop("disabled", false);
		}
		
	enableFields("chkPprdShiftnoplan");
	enableFields("chkPprdHourlynoplan");		
	viewResultGrid();
}

/*
function frmPcsLosscmbPprdArea_onSelect(record) 	{
	var prd=jQuery('#cmbPprdProductid').combobox('getValue');
	var area=jQuery('#cmbPprdArea').combobox('getValue');
	reloadCombo("frmPcsLoss","cmbPprdBatchqty","txt_PprdBatchqty.pcs?prd="+prd+"&area="+area);
} */
function frmPcsLosscmbPlosLossid_onSelect(record){
	var losLosstime=jQuery("txtPlosLosstime").val();
	//alert('losLosstime'+losLosstime);
		jQuery("#cmbPlosLossid").combobox('clear');
	
	reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs");
	
}

function frmPcsLosscmbPlosCellid_onLoadSuccess() 	{
	jQuery("#chkPprdShiftnoplan").attr('checked', false);
	jQuery("#chkPprdHourlynoplan").attr('checked', false);
}
function frmPcsLosscmbSelPrdHourL_onSelect(record){
	var shiftId = jQuery('#cmbPlosShiftid').combobox("getValue");
	var hourId = jQuery('#cmbSelPrdHourL').combobox("getValue");
	var ds="&shiftId="+shiftId+"&hourId="+hourId;
	jQuery("#chkPlosDurationSel").attr('checked', false);
	enableFields("txtPlosLosstime");
    readOnlyFields("dtePlosFromdate");
    readOnlyFields("txtLossHourfromtime");
    readOnlyFields("txtLossMinutesfromtime");
    readOnlyFields("chkPlosTotime");
    readOnlyFields("dtePlosTodate");
    readOnlyFields("txtLossHourtotime");
    readOnlyFields("txtLossMinutestotime");
    //alert(ds);
    //processAjaxCalls('getLossHourTime.pcs',ds,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
    showAllTime("Loss");
    lossMinutevalidate();
    /*jQuery("#txtLossHourfromtime").val('');
	jQuery("#txtLossMinutesfromtime").val('');
	jQuery("#txtLossHourtotime").val('');
	jQuery("#txtLossMinutestotime").val('');*/
   /* if(jQuery("#txtPlosLosstime").val()>60){
        alert("Minute exceeds limit for hour");
    }else{}*/
	
}
function checkPrdHrLNullVal(){
	var selPrdHourNull= getFieldValue("cmbSelPrdHourL"); //jQuery("#cmbSelPrdHourL").val();	
	if(selPrdHourNull==null || selPrdHourNull==' ' || selPrdHourNull==''){
			jQuery("#cmbSelPrdHourL").combobox('setValue','-');
		}
}
function lossMinutevalidate(){
	var lossHnoMin=Number(jQuery("#txtPlosLosstime").val());
	if(lossHnoMin>60){
			alert(" Minute value should not exceed for Hour ");
			setFocusOnField("txtPlosLosstime");
		}
		actualTimeCalculation();
}

function fnValidateDateEntry(){

	var currentDate = getServerDateTime();
	var selDate = convertStringToDate(jQuery('#dtePlosDate').datebox('getValue'));
	currentDate=currentDate.split('-');
	 selDate=selDate.split('-');
	 currentDate = new Date(currentDate[0], currentDate[1], currentDate[2]);
	 selDate = new Date(selDate[0], selDate[1], selDate[2]);
	var nTotalDiff = getTimeDifference (currentDate,selDate);
	var dateDiff = nTotalDiff.convtDays;
	currentDate_unixtime = parseInt(currentDate.getTime() / 1000);
	selDate_unixtime = parseInt(selDate.getTime() / 1000);
	var timeDifference = selDate_unixtime - currentDate_unixtime;
	timeDifference=timeDifference/24;
	if (timeDifference>10) {
		//alert(timeDifference +"ll");
		alert("You can Not Enter For more Than last 10 Days..");
		fillWithCurrentDate(dateField);	
		return ;
	}
	return;
}



function fnValidateDate(calOrDat, date,shift) {
	//var date = date;

	var currentDate = getServerDateTime();
	var selDate = convertStringToDate(date);
	var nTotalDiff = getTimeDifference (currentDate,selDate);
	var dateDiff = nTotalDiff.convtDays;
	
	if (dateDiff>0) {
		alert("Entry Date should be less than or equal to Current Date ");		
		return ;
	}
		
//	var allowDays = jQuery('#hdnAllowDays').val();
	var allowDays = "";
	
/*	var mode = jQuery('#hdnMode').val();
	if (mode=="create" && (-dateDiff) > allowDays ) {
		alert("Entry Date should be allow only " + allowDays + " days ");		
		return ;			 
	}		
*/	var dataStr = "";
	var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();
	var factId = jQuery("#frmPcsLoss input[id='factory']").val();
	
	
	dataStr+= "q=2&cellId="+cellId;
	dataStr+= "&date="+date;
	dataStr+= "&sectId="+sectId+"&factId="+factId;

	//send it same format don't change
	var condParam = "";
	condParam+= "CHKCELL=Y;SECTIONID="+sectId+";CELLID="+cellId+";DATE="+date+";SHIFT="+shift+";FACTID="+factId+";calOrDat="+calOrDat;
	condParam+= ";CHKMACHINE=N;MACHINEID=;PCSOPTION=;";

	//	alert(condParam);
	if (calOrDat=="C") 
		processAjaxCalls('getIsPcsEnabled.balpcs','?q=2&condParam='+condParam,"pcsEnabledRecallSuccess");
	else
	processAjaxCalls('getIsPcsEnabled.balpcs','?q=2&condParam='+condParam,"pcsEnableforShift");
}

function pcsEnableforShift(result)
{	
	if(result.enableShift == 'N')
	{
		alert("Cannot Enter for Future Shift");
	}
	else{
		
	if(result.isPcsEnabled == "Y"){

		var entryDate= jQuery('#dtePlosDate').datebox('getValue');
		var factId = jQuery("#frmPcsLoss input[id='location']").val();
	//	alert(factId +"  Factory Id");
		processAjaxCalls('getHolidayDates.balpcs',"?q=2&entryDate="+entryDate+"&factId="+factId,"holidaySuccess");
		
		setFocusOnField('txtPprdProducedqty');
		fillHourlyCalculations();

		setTimeout(function() {
			var hourno = "1";
			
			var noofHours = Number(jQuery('#selPprdHour > option').length);
			var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
			//alert("1  j "+productionRow.length+"  "+noofHours );
			if(productionRow.length != "0" || productionRow.length != '')
				{
				//alert(productionRow.length +"hourno");
					for (var i = 1; i <= productionRow.length; i++) {
						//alert(hourno +"hourno");
		    			var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
						hourno = Number(rowData.selPprdHour);
					}
				//	alert(hourno+ " 02 "+ (hourno < noofHours));
					if(Number(hourno) < Number(noofHours))
						{
							hourno = Number(hourno) + Number(1);
							jQuery("#selPprdHour").val(hourno);
						}
					else
						jQuery("#selPprdHour").val(' ');		
		 		}
			else
				jQuery("#selPprdHour").val('1');
				//alert(1236);
				//alert(jQuery("#selPprdHour").val());

			showAllTime("Production");
			
		},550);

		/*	var date = jQuery('#dteEntryDate').datebox('getValue');
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		var shift = jQuery('#cmbShiftid').combobox("getValue");
		
		dataStr+= "q=2&cellId="+cellId;
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;

		var sectId = jQuery("#frmPcsLoss input[id='section']").val();
		var factId =  "";// jQuery("#frmPcsCalendar input[id='factory']").val();
		var mchId = jQuery('#cmbPCSMachineid').combobox("getValue");
		var mode = jQuery('#hdnMode').val();
		
		
		//alert(mode);
		dataStr+= "&mchId="+mchId+"&sectId="+sectId+"&factId="+factId+"&mode="+mode+"&formAuto=false";
		//alert(dataStr);							
		var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode};			
		//navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);
	//	var url = jQuery("#hiddenUrl").val();
		/*if(url.indexOf('Test') >=0)
			navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);			
		else
			navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);*/	
	//	if(url.indexOf('Test') >=0)
		//	navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);
			//LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"96%","91%","2%","10px", "Pcs_View_Callback","PCS View",false,false);
	//	else
	//		navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);
			//LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"97%","91%","5%","13px", "Pcs_View_Callback","PCS View",false,false);
	}
	else
	{ 
		jQuery("#cmbPlosCellid").combobox('clear');
		alert("PCS is Disabled for this Cell");	
	}
	}
	
}
function holidaySuccess(result) {
	
		
		for(var i=0; i<result.holiData.length;i++){
		 var holidayFlag = result.holiData[i].holidayFlag;
		if(holidayFlag == 'W')
			{
				alert("Today is weekly off.");
			}
		if(holidayFlag == 'H')
			{
				alert("Today is Holiday.");
			}
		}		
}
function frmPcsLosscmbMainLoss_onSelect(record) 	{

	jQuery("#cmbPlosLossid").combobox('clear');
	
	var mainlossId = record.id;
		
//	reloadCombo("frmPcsLoss"=,"cmbPprdProductid","combo_product.pcs?prdModelId="+prdModelId);
	reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs?mainlossId="+mainlossId);
	//frmPcsLosscmbMainLoss_onClear();
}
function frmPcsLosscmbPlosLossid_onSelect(record) 	{

	jQuery("#cmbPlosLossid").combobox('clear');

	
	//var mainlossId = record.id;
//	reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.pcs?prdModelId="+prdModelId);
	reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs");
}


function frmPcsLosscmbPprdProductmodel_onSelect(record) 	{

	jQuery("#cmbPprdProductid").combobox('clear');

	var prdModelId = record.id;	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var entryDate = jQuery('#dtePlosDate').datebox('getValue');
	reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.balpcs?prdModelId="+prdModelId+"&cellId="+cellId+"&entryDate="+entryDate);
}
function frmPcsLosscmbMainLoss_onClear()
{
	 jQuery("#cmbPlosLossid").combobox('clear');
     reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs");
	
}

function frmPcsLosscmbPprdProductid_onSelect(record) 	{
	
	var cell = jQuery("#cmbPlosCellid").combobox('getValue');
	
   var hourdata =jQuery("#selPprdHour").val();
	if(hourdata =='' || hourdata == ' ' && cell !="CEL0000089")
		{
			alert("Select hour");
			jQuery("#cmbPprdProductid").combobox('clear');
			return false;
		}
	var cellvalue=jQuery("#cmbPlosCellid").combobox('getValue');
	if( cellvalue==null || cellvalue==''){
			alert("Select cell");
			jQuery("#cmbPprdProductid").combobox('clear');
			return false;
		}
	var availtimeHourly=getFieldValue("txtAvailtimehourly"); 
	
	if(availtimeHourly=="" || availtimeHourly==" "){
			alert("Enter Avail. time");
			setFocusOnField('txtAvailtimehourly');
			return false;
			}
	jQuery('#txtPprdCycletime').val("");
	var productid = record.id;
	var fromdate = jQuery('#dtePlosDate').datebox("getValue");
	var cellId=jQuery("#frmPcsLoss input[id='cell']").val();
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	setProductModel(productid);
	
	fillManpower(productid);
	fillcycleTime(productid);
	//	reloadCombo("frmPcsLoss","cmbPprdProductid","combo_Productname.pcs?cellId="+cellId);
//	reloadCombo("frmPcsLoss","cmbPprdProductmodel","combo_productModel.pcs?cellId="+cellId);
 //   viewRejectionGrid();
    viewProductionGrid();
 	jQuery('#txtPprdProducedqty').val('');
	jQuery('#txtPprdRejectedqty').val('');
	jQuery('#txtPprdReworkqty').val('');
	jQuery('#txtPprdRemarks').val('');

	getGridRowid(hourdata,productid,'');

	setTimeout(function() {
		showAllTime("Production");
	},500);
	
}

function frmPcsLosscmbPprdManpower_onSelect(record){
	var productid=record.id;
	fillcycleTime(productid);
	showAllTime("production");
	//addHourTimevalidtions();
	getGridRowid('','',record.id);
}

function fillcycleTime(productid) {
	
	
	var dataStr ="?";
	dataStr+= "&date="+jQuery("#dtePlosDate").datebox('getValue');	
	dataStr+= "&machineId="+jQuery("#hdnMachineId").val();
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();	
	dataStr+= "&cellId="+cellId+"&sectId="+sectId;		
	dataStr+= "&productId="+productid;
	processAjaxCalls("text_Cycletime.balpcs",dataStr,"cycleTimeIdSuccess","cycleTimeIdSuccessError");
	
}
function cycleTimeIdSuccess(result) {
	
	var cycleTime=result.text;
	jQuery('#txtPprdCycletime').val(cycleTime);
			
}
function fillManpower(productid) {
	jQuery("#cmbPprdManpower").combobox('clear');

	var dataStr ="?";
	dataStr+= "&date="+jQuery("#dtePlosDate").datebox('getValue');	
	var cellId=jQuery("#frmPcsLoss input[id='cell']").val();
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();	
	dataStr+= "&cellId="+cellId+"&sectId="+sectId;		
	dataStr+= "&productId="+productid;
	reloadCombo("frmPcsLoss","cmbPprdManpower","combo_Manpower.balpcs"+dataStr );
	
}


function frmPcsLosscmbPprdManpower_onLoadSuccess() 	{
     setComboDefaultValue("frmPcsLoss","cmbPprdManpower");			
	
}


function frmPcsLossdtePlosDate_onLoadSuccess() 	{

	
}
function setProductModel(id) {
//	jQuery("#cmbPprdProductmodel").combobox('clear');
	jQuery("#cmbPprdProductmodel").combobox('clear');
	var dataStr ="?";
	dataStr+= "&prdId="+id;
	
	processAjaxCalls("selectedProductModel.balpcs",dataStr,"modelIdSuccess","modelIdSuccessError");

}

function modelIdSuccess(result) {
	
	setFieldValue('cmbPprdProductmodel',result.modelKeyid,'frmPcsLoss');

}

jQuery("#cmbPprdProductmodel").combobox({onRequest:function(opts){		
	var cellId = jQuery("#cmbPlosCellid").combobox("getValue") ;
	var keyid = getFieldValue("cmbPprdProductmodel");
	 
	return "&cellId="+cellId;
}
});


function frmPcsLoss_beforeSubmit() { 
	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	if (cellId=="" || cellId==' ' || cellId=='undefined') {
		popupCommonErrorMsg(" Select Cell");
		return false;
	}
	
	 var hiddenurl=jQuery('#hdnUrl').val();

	 var Rejqty = jQuery("#txtPprdRejectedqty").val();
	 if(Rejqty =="" || Rejqty ==" ")
		 Rejqty = Number(0);
	 var Rewqty = jQuery("#txtPprdReworkqty").val();
	 if( Rewqty == "" || Rewqty == " ")
		 Rewqty = Number(0);
	 var total=Number(Rejqty) + Number(Rewqty);
	// alert("Rej "+Rejqty+" rew "+Rewqty+" total "+total);
	  var mstKeyid=jQuery('#hdnPprdKeyid').val();
 	  var rowId = jQuery('#hdnrowidbtn').val();
      var gridValue = getGridSelectArray("pcsrejectionGrid");
		if(hiddenurl == 'frmPcsLoss_rejectionsave.balpcs' && gridValue == '' )
		{
				alert("If want to save or update Rejection then atleast select a row in Rejction grid. ");
				return false;
		}
		else{
		  if(rowId.trim().length >= 0 && mstKeyid.trim().length != 0 && hiddenurl =='frmPcsLoss_rejectionsave.balpcs'){
			  		
			  var tempTotal='';
			  var tempRej='0';
			  var tempRew='0';
				var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
				var rowId = jQuery('#hdnrowidbtn').val();
			//	alert("Rejectrow :"+rejectRow.length);

			for (var i = 1; i <= rejectRow.length; i++) {
					var type='';
					var rowData = jQuery("#pcsrejectionGrid").jqGrid('getRowData',i);
					  type = jQuery('#cmbPrbdType_pcsrejectionGrid_'+i).val();
						
						if(type == undefined){
							   type = rowData.cmbPrbdType;
							if(type == 'Reject')
							{
								type = 'R';
							}
							else if(type == 'Rework')
							{ 
								type = 'W';
							}
						}	
						
						var gridRejQty=jQuery('#txtPrbdQuantity_pcsrejectionGrid_'+i).val();
						if(gridRejQty == undefined)
							{
								 gridRejQty = rowData.txtPrbdQuantity;
							}
				//		alert("Type "+type+", gridRejQty "+gridRejQty);
						if(type == 'R' || type == 'W'){

							if(type=='R')
							{
								tempRej=Number(tempRej) + Number(gridRejQty);
				//				alert("tempRej:"+tempRej);
							}
							if( type == 'W')
							{
								tempRew=Number(tempRew) + Number(gridRejQty);
					//			alert("tempRew:"+tempRew);
							}
							tempTotal = Number(tempTotal) + Number(gridRejQty);
					/*		alert("tempTotal:"+tempTotal);
							alert("tempRej:"+tempRej);
							alert("tempRew:"+tempRew);
							if( tempTotal == total )
							{
								if( Number(Rejqty) != Number(tempRej))
								{ 
									alert("Rejection Breakup Is not Correct");
									return false;
								}
								if(Number(Rewqty) != Number(tempRew))
								{
									alert("Rework Breakup Is not correct");
									return false;
								}
								
								var gridData = '&rejectionGridDetails=' + gridValue;
								console.log(gridData);
								return gridData;
							}*/
							if(i == rejectRow.length)
							{
								if( Number(Rejqty) != Number(tempRej))
								{ 
									alert("Actual Rej Qty is "+Rejqty+".But entered Rej Qty breakup is "+tempRej);
									return false;
								}
								if(Number(Rewqty) != Number(tempRew))
								{
									alert("Actual Rew Qty is "+Rewqty+"But entered Rew Qty breakup is "+tempRew);
									return false;
								}	
								
							}
						}
							
				}
				if( tempTotal == total )
				{
					
					if( Rejqty != tempRej)
					{ 
						alert("Actual Rej Qty is "+Rejqty+".But entered Rej Qty breakup is "+tempRej);
						return false;
					}
					if(Rewqty != tempRew)
					{	
						alert("Actual Rew Qty is "+Rewqty+"But entered Rew Qty breakup is "+tempRew);
						return false;
					}
					
					var gridData = '&rejectionGridDetails=' + gridValue;
					console.log(gridData);
					return gridData;
				}
			
			
			}		
			  
	    }
	    
	  		var gridData = '&rejectionGridDetails=' + gridValue;
		//	console.log(gridData);
			return gridData;
}


jQuery("#btnRejDelete").click(function() {

		removeRejectionRecord();
	});
jQuery("#btnProdDelete").click(function(){
	
	removeProductionRecord();
});	

function removeProductionRecord(){

	var productionKeyid=jQuery("#hdnPprdKeyid").val();
	var hour = jQuery("#selPprdHour").val();
	if(productionKeyid != null && productionKeyid != 'undefined' && productionKeyid != undefined && productionKeyid !='' && hour != "" && hour !=" " )
		{

		var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
			var dataStr ="?";
		//	var  r=confirm("It will remove rejection data too.");
			if(rejectRow.length == '0'  )	
			{
				var dtlkeyid = jQuery("#hdnPldetailsId").val();
				dataStr+="&dtlkeyId="+dtlkeyid;
				dataStr+="&keyid="+productionKeyid;
				dataStr+="&cellId="+jQuery("#frmPcsLoss input[id='cell']").val();	
				dataStr+="&entrydate="+jQuery('#dtePlosDate').datebox("getValue");
			//	alert("dataStr"+dataStr);
				processAjaxCalls("frmPcsLoss_productionDelete.balpcs",dataStr, 'removeProduction_successCallBack','removeProduction_errorCallBack');
				}
			else
				{
				alert("Delete rejection before deleting production.");
				return false;
				}
		}	
	else
		return false;
}
function removeProduction_successCallBack(result){

//	alert(result.successData);
//	jQuery("#hdnPprdKeyid").val('');
	jQuery("#selPprdHour").val('');
	jQuery("#pcsproductionGrid").trigger("reloadGrid");
	jQuery("#pcsrejectionGrid").trigger("reloadGrid");
	clearAllFields();
}

function removeProduction_errorCallBack() {
	alert("Production data removed");
}

function removeRejectionRecord(){

	var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
	//alert(rejectRow.length);
	for (var i = 0; i < rejectRow.length; i++) {
		var rowid = rejectRow[i];
			if(jQuery('#jqg_pcsrejectionGrid_' + rejectRow[i]).is(':checked') == true ) {

			
				var keyid = jQuery("#pcsrejectionGrid").jqGrid('getCell', rowid,"txtPrbdKeyid");

					    if (keyid != null && keyid != 'undefined' && keyid != undefined	&& keyid != "" && keyid.trim().length > 0) {
			    	 		var r = confirm("Do You Want To Delete?");
							if (r == true) {
								processAjaxCalls("frmPcsLoss_rejectionDelete.balpcs", "keyid="+ keyid, 'removeRejection_successCallBack','removeRejection_errorCallBack');
							}
							else {
							return false;
							}
				} else {
							var r = confirm("Do You Want To Remove Row?");
							if (r == true)
							jQuery("#pcsrejectionGrid").trigger("reloadGrid");
							else
							return false;
			           }
			}
	
	}

}


function removeRejection_successCallBack(result) {

	//alert(result.successData);
//	jQuery("#pcsrejectionGrid").trigger("reloadGrid");
	viewRejectionGrid();
	rejectionreworkqtyvalid();
	
}

function rejectionreworkqtyvalid()
{
	var type="";
	var rejqty="0";
	var reworkqty="0";
	
	var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
//	alert("rej"+rejectRow);
//	alert("rej"+rejectRow.length);
		for(var i=1; i<= rejectRow.length; i++)
		{
			var rowData = jQuery("#pcsrejectionGrid").jqGrid('getRowData',i);
			type = rowData.cmbPrbdType;
			if(type == "Rework")
			{
				reworkqty = Number(reworkqty) + Number(rowData.txtPrbdQuantity);
			}
			if(type == "Reject")
			{
				rejqty = Number(rejqty)+ Number(rowData.txtPrbdQuantity);
			}			
				
	}
	jQuery("#txtPprdReworkqty").val(reworkqty);
	jQuery("#txtPprdRejectedqty").val(rejqty);
	productionDataSave();
	
}
function removeRejection_errorCallBack() {
	alert("row not removed");
}

function productionDataSave(){

	
	var cellId = jQuery("#cmbPlosCellid").combobox("getValue") ;
	if (cellId.trim().length > 0 )
		{
		var shiftplan=jQuery("#chkPprdShiftnoplan").is(':checked');

		if(jQuery("#chkPprdShiftnoplan").is(':checked')==true)
			{
			
			var  r=confirm("Do you want to apply no plan for whole shift?");
				if(r == true)
					{
					var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
					
					var realRows = 0;
					for (var k = 0; k < productionRow.length; k++) {
					    var rd = jQuery("#pcsproductionGrid").jqGrid('getRowData', productionRow[k]);
					    var isTotal = Object.values(rd).some(function (v) {
					        return String(v).replace(/<[^>]*>/g, '').trim() === 'TOTAL';
					    });
					    if (!isTotal) realRows++;
					}
					
					if (realRows > 0)
				    {
				        alert("Production Log exists for this shift so you Can't apply Full shift no plan.");
				        return false;
				    }
			
		 			/* if(productionRow.length > 1)
			 			{
			 				alert("Production Log exists for this shift so you Can't apply Full shift no plan.");
							return false;
				 		} */
		 						
					}
				else 
					return false;	
			}
		var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
	
 		   	for (var i = 1; i <= productionRow.length; i++) {
		
    		var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);

				var shiftnoplan=rowData.PprdShiftnoplan;
				if(shiftnoplan == "Y")
				{
			
					var mstkeyid = jQuery("#hdnPlmMasterId").val();
		
					if(mstkeyid != null && mstkeyid != "undefined" && mstkeyid !="" && mstkeyid != " ")
					{
					
						var r = confirm("Do you want to remove whole shift no plan?");
						if(r == true)
							{
								var dataStr="?";
								dataStr+="&mstkeyId="+mstkeyid;
								dataStr+="&dtlkeyId="+jQuery("#hdnPldetailsId").val();
								dataStr+="&cellId="+jQuery("#frmPcsLoss input[id='cell']").val();	
								dataStr+="&entrydate="+jQuery('#dtePlosDate').datebox("getValue");
								processAjaxCalls("removeShiftNoplan.balpcs",dataStr,"removeShiftNoplanSuccessCallback","removeShiftNoplanSuccessCallbackError");

								return false;
							}
						else	
							return false;
					}
					else
						return false;
					
				}
				
  		   	}
 		   if(jQuery("#chkPprdHourlynoplan").is(':checked')==true)
			{
			  if(jQuery("#selPprdHour").val()=='' || jQuery("#selPprdHour").val() == ' ')
					{
						alert("Select No plan hour.");
						return false;
					}
				var noplanduration=jQuery("#txtPprdNoplanduration").val();
				if(noplanduration == '0' || noplanduration == '')
					{
						alert("Enter no plan minutes.");
						return false;
					}
				
			}
 		  if(jQuery("#chkPprdHourlynoplan").is(':checked')== false && jQuery("#chkPprdShiftnoplan").is(':checked') == false)
			{
 			  var hour=jQuery("#selPprdHour").val();
 			 var cellId = jQuery("#cmbPlosCellid").combobox("getValue") ;
				if(jQuery("#selPprdHour").val()==' ' || hour == '' && cellId != "CEL0000089" )
					{
						alert("Select Production hour.");
						return false;
					}
			}
  			
				jQuery('#hdnUrl').val('frmPcsLoss_productionsave.balpcs');
		        var flId=jQuery("#frmPcsLoss input[id='flid']").val();
				var formId = "frmPcsLoss";
				var pprdKeyid = jQuery('#hdnPprdKeyid').val();
				var shiftid=jQuery('#cmbPlosShiftid').combobox("getValue") ;
				var pldetailsId = jQuery('#hdnPldetailsId').val();
				var plmasterid = jQuery('#hdnPlmMasterId').val();
				var fromdate = jQuery('#dtePlosDate').datebox("getValue");
				var prodqtyValidation = productionqtyValidation();
					if( prodqtyValidation == false)
					return false;
				var batchqty=getFieldValue("txtPprdBatchQty");
				var weightpiece=getFieldValue("txtPprdWeightpiece");
				var area=getFieldValue("txtPprdArea");
				var shiftWise=jQuery('#hdnisShiftWise').val();
			//     alert(shiftWise +"shiftWise");
			//	alert("1  "+jQuery("#hdnAddrowtoGrid").val());
			//	var url = "frmPcsLoss_productionsave.pcs?&pprdKeyid="+pprdKeyid+"&flId="+flId+"&cellId="+cellId+"&fromdate="+fromdate+"&formId="+formId+"&shiftid="+shiftid+"&Pldetailsid="+pldetailsId+"&Plmasterid="+plmasterid+"&batchqty="+batchqty+"&weightpiece="+weightpiece"&area="+area;
										
				 var  url = "frmPcsLoss_productionsave.balpcs?&pprdKeyid="+pprdKeyid+"&flId="+flId+"&cellId="+cellId+"&fromdate="+fromdate+"&formId="+formId+"&shiftid="+shiftid+"&Pldetailsid="+pldetailsId+"&Plmasterid="+plmasterid+"&shiftWise="+shiftWise;
				//	url +="&Pldetailsid="+pldetailsId+"&Plmasterid="+plmasterid;
				if (pprdKeyid =="" || pprdKeyid ==" " || pprdKeyid == 'undefined')
					url+="&saveMode=create";
				else
					url+="&saveMode=update";
				saveForm(formId,url);
				
			}
	
		else
			{
			   alert("Select Cell");
			}
	
	
}

function viewResultGrid(){

			//var flid = jQuery("#frmPcsLoss input[id='flid']").val();			 
	        var date=jQuery('#dtePlosDate').datebox('getValue');
	    	var shift=jQuery('#cmbPlosShiftid').combobox('getValue');
			var factId = jQuery("#frmPcsLoss input[id='factory']").val();
			var sectId = jQuery("#frmPcsLoss input[id='section']").val();
			var cellId =jQuery('#cmbPlosCellid').combobox('getValue');
			
			var dataStr="";		
			 dataStr+= "&date="+date+"&shift="+shift+"&sectId="+sectId+"&cellId="+cellId;
			
		//	alert(dataStr);
		//	LoadPopUp("loadPCSResult", "pcsResult_input.pcs?&dataStr="+escape(dataStr), true,"480px","300px","50px","100px", "showResult_successCallBack","Production Log Results",true);				
			processGridnew('pcsResultGrid_input.balpcs',dataStr,"pcsresultGrid","pcsresultPager","Production Log - Results","pcsResultdblClick","","pcsResultGrid_loadComplete","pcsResultGridError");
			
}
function frmPcsLosscmbPlosLossreason_onSelect(record) { 
	processAjaxCalls('getReasonLossNo.balpcs',"&reasonId="+record.id,'reasonLossIdSuccess','reasonLossIdError');	
}

function frmPcsLosscmbPlosLossid_onSelect(record) { 
	var loss= jQuery('#cmbPlosLossid').combobox('getValue');
	//alert("......"+loss + "..");
	processAjaxCalls('getSubMainLossNo.pcs',"&subLossid="+record.id,'subLossMainIdSuccess','reasonLossIdError');
	var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	var cellid=jQuery("#frmPcsLoss input[id='cell']").val();
	
	var shiftId=jQuery('#cmbPlosShiftid').combobox('getValue');
	var shifttext=jQuery('#cmbPlosShiftid').combobox('getText');
	var hourno=getFieldValue("selPprdHour");
	var lossdate = jQuery('#dtePlosDate').datebox("getValue");
	var availTime =jQuery('#txtAvailtime').val();
	
		//var lossName= jQuery('#"cmbPlosLossid"').text();
		//alert("......"+availTime + ".."+hourno+"    "+lossdate);
		var lossName= jQuery('#cmbPlosLossid').combobox('getText');
		 if(loss=="PLC/000006"||loss=="PLC/000017"||loss=="PLC/000033"||lossName=="1.1-EQUIPMENT FAILURE (ELECTRICAL)"||lossName=="1.2-EQUIPMENT FAILURE (ELECTRONICS)"||lossName=="1.3-EQUIPMENT FAILURE (MECHANICAL)"){
			 LoadPopUp("",'Breakdownpcs_input.brdn?Flid='+flId+'&cellid='+cellid+'&shiftId='+shiftId+'&shifttext='+shifttext+'&hourno='+hourno+'&lossdate='+lossdate+'&availtime='+availTime, true,"90%","500px","60px","20px", null, 'Breakdown Analysis - Breakdown Link',false, true,true);
		 }
			  if(loss=="PLC/000044" || lossName=="5-MINOR STOPPAGE" || loss=="PLC/000039" || loss=="PLC/000068" || loss=="PLC/000010"){

				 jQuery('#cmbPlosMachineid').combobox('enable');
			   }
			  else
				  jQuery('#cmbPlosMachineid').combobox('disable');	
		 if(loss=='PLC/000039' || loss=='PLC/000068' || loss=='PLC/000010'|| loss=='PLC/000044' ){				 
					jQuery('#cmbPlosToolId').combobox('enable');	
				  }	
			  else{	   
			 		 jQuery('#cmbPlosToolId').combobox('disable');
			  }

			//////

			
		//	 alert(machineId+"machine id ")

			// fillComboBox("frmPcsLoss","cmbPlosToolId","combo_ToolId.balpcs?machineId="+machineId);
			///////
			

		/*	  function frmPcsLosscmbPlosMachineid_onSelect(record) {
		  alert(1);
				  var machineId=jQuery('#cmbPlosMachineid').combobox('getValue');
				  jQuery("#cmbPlosLossid").combobox('clear');
					
						alert(98+" ;;;;;;;  "+    machineId);
						reloadCombo("frmPcsLoss","cmbPlosToolId","combo_ToolId.pcs?machineId="+machineId);
	}		*/ 

		if(loss=="PLC/000008"){
			//jQuery('#cmbPlosMachineid').combobox('hide');
			jQuery('#plosequip').hide();
			jQuery('#lblplosMachine').hide();
			jQuery('#lblplosSource').show();
			jQuery('#plosSourcediv').show();
		//	jQuery("cmbPlosToolId").hide();			
			jQuery('#cmbPlosSource').combobox('enable');
			}
		else{
			jQuery('#plosequip').show();
			jQuery('#lblplosMachine').show();
		    jQuery('#lblplosSource').hide();
		    jQuery('#plosSourcediv').hide();
			jQuery('#cmbPlosSource').combobox('disable');
		}
	
	/*	if(loss=="PLC/000049"||loss=="PLC/000083" ){
			 jQuery('#btnLink').prop('disabled',false);	
			if(jQuery("#txtPlosLosstime").val() != "" || jQuery("#txtPlosLosstime").val() != "0" ){	
				 var jhTime=jQuery("#txtPlosLosstime").val();	
				LoadPopUp("",'ShutDownLoss_input.pcs?Flid='+flId+'&cellid='+cellid+'&shiftId='+shiftId+'&shifttext='+shifttext+'&hourno='+hourno+'&lossdate='+lossdate+'&availtime='+availTime+'&jhTime='+jhTime+'&lossName=shutDown', true,"90%","500px","60px","20px", null, 'JH ENTRY',false, true,true);
			}
			else{
					
				alert("Enter loss hours.");
			} 
	//processAjaxCalls('getSubMainLossNo.pcs',"&subLossid="+record.id,'subLossMainIdSuccess','reasonLossIdError');	
 }
		else{jQuery('#btnLink').prop('disabled',true);}*/
		 if(loss=="PLC/000045"){
			 jQuery('#btnLink').prop('disabled',false);
				LoadPopUp("",'speedLoss_input.balpcs?Flid='+flId+'&cellid='+cellid+'&shiftId='+shiftId+'&shifttext='+shifttext+'&hourno='+hourno+'&lossdate='+lossdate+'&availtime='+availTime+'&jhTime='+jhTime+'&lossName=speed', true,"90%","500px","60px","20px", null, 'SPEED LOSS ENTRY',false, true,true);
				
			 }
		 else jQuery('#btnLink').prop('disabled',true);
}
	
/*
function frmPcsLosscmbPlosLossid_onSelect(record) { 
	processAjaxCalls('getReasonLossNo.pcs',"&reasonId="+record.id,'reasonLossIdSuccess','reasonLossIdError');	
}*/
function frmPcsLosscmbPlosSource_onSelect(record) {
var source=jQuery('#cmbPlosSource').combobox('getValue');

if(source=='V'){

	readOnlyFields("txtPlosSource");
}
else{
enableFields("txtPlosSource");
}
}
function subLossMainIdSuccess(result) { 

	jQuery('#cmbMainLoss').combobox('clear');
	jQuery('#cmbMainLoss').combobox('setValue',result.lossId);
}
function reasonLossIdSuccess(result) {
	jQuery('#cmbMainLoss').combobox('setValue',result.lossId);
	var lossRes = "Loss Reason" + "  -  (UOM: " + result.lossUom + ")";
	jQuery('#lblLossReason').html(lossRes);
//	disableField("frmPcsLoss", "cmbMainLoss");
}

function frmPcsLoss_FuntLocHierarchy_SuccessCallBack(keyIds)
{	
	var dataString="";
	var factId=keyIds.sbuId;//factId
	var sectId = keyIds.sectId;	
	var cellId=keyIds.cellId;
	var flid=keyIds.flId;
	var entryDate=jQuery("#dtePlosDate").datebox('getValue');
	
//	jQuery("#frmPcsLoss input[id='flId']").val(fld);
	clearAllFields();
	
	// sbu -> factory id 
	/* var sbuVal = jQuery("#frmPcsLoss input[name='hdnsbu']").val();
	// Fallback by id, in case it's rendered as id instead of name in some cases
	if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
		sbuVal = jQuery("#hdnsbu").val();
	console.log("sbuVal from hdnsbu:", sbuVal);
	if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
		jQuery("#frmPcsLoss input[id='factId']").val(sbuVal);
		factId = sbuVal;
	} */
	
	reloadCombo("frmPcsLoss","cmbPlosCellid","cellCombo.commonFilter?sectionid="+sectId );
	if (keyIds.cellId!="null")
		setFieldValue('cmbPlosCellid',keyIds.cellId);
	//reloadCombo("frmPcsLoss","cmbPlosShiftid","shift.commonFilter?frmRfilter=yes&factId="+factId);
	reloadCombo("frmPcsLoss","cmbPlosShiftid","shift.commonFilter?frmRfilter=yes&factId="+factId);

	//reloadCombo("frmPcsLoss","cmbPlosMachineId","machineCombo.commonFilter?cellid="+cellId+"&flid="+flid);
	reloadCombo("frmPcsLoss","cmbPlosLossreason","combo_Phenomena.balpcs?cellId="+keyIds.cellId);
	reloadCombo("frmPcsLoss","cmbPlosLossid","combo_SubLoss.balpcs?cellId="+keyIds.cellId);
	reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.balpcs?factId="+keyIds.factId+"&cellId="+cellId+"&entryDate="+entryDate);
//	reloadCombo("frmPcsLoss","cmbPprdProductmodel","combo_productModel.pcs?factId="+keyIds.factId+"&cellId="+cellId);
	reloadCombo("frmPcsLoss","cmbPprdManpower","combo_Manpower.balpcs?cellId="+cellId);
	reloadCombo("frmPcsLoss","cmbPlosMachineid","machineCombo.commonFilter?cellId="+cellId);//+"&flid="+flId);
	//fillComboBox("frmPcsLoss","cmbEquipment","machineCombo.commonFilter?cellId="+cellId+"&flid="+flId );	
	
	
	if ((screen.width >= 1200)  && (screen.colorDepth>12))
		jQuery('#dispFunctionalLoc').css('width',550);
	else {
		jQuery('#dispFunctionalLoc').css('width',300);
		//jQuery('#txtPlosLossdescription').css('width',180);
		//jQuery('#cmbPlosLossreason').css('width',180);
		jQuery('#tdFnln').attr('colspan',5);
	}
	setFunctionalLocWidth('frmPcsLoss','550px');
	setFieldValue('cmbPlosProdImpact','N');

	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if(hours < 10)
		{
		 hours = "0" + hours;
		}
	if (minutes < 10){
		minutes = "0" + minutes;
	}	
	//dataString +='?&flid='+flid+'&fromTime='+hours + ':' + minutes;
	//processAjaxCalls('txt_shift.Bbrdn',dataString,'getPCSShift','getPCSShiftErr');
	jQuery('#cmbPlosShiftid').combobox({
    onLoadSuccess: function() {
        dataString +='?&flid='+flid+'&fromTime='+hours + ':' + minutes;
        processAjaxCalls('txt_shift.Bbrdn', dataString, 'getPCSShift', 'getPCSShiftErr');
    }
});
//	processAjaxCalls('getAllTime.pcs',dataString,'getPCSShift','getPCSShiftErr');
	
			
//	viewProductionGrid();
	viewGrid();
//	viewRejectionGrid();
	viewResultGrid();
	//showAllTime();
	
}


function showAllTime(tab) {
	
	var dataStr ="";
//	var machineId = jQuery('#hdnMachineId').val();
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	
	var plmasterId = getFieldValue("hdnPlmMasterId");//jQuery("#hdnPldetailsId").val();
//	var pldetailsId = jQuery('#hdnPldetailsId').val();
	hourId = jQuery("#selPprdHour").val();
	//length,prevObject,context,selector
	if (tab=="Loss")
		
    	hourId = getFieldValue("cmbSelPrdHourL"); //jQuery("#cmbSelPrdHourL").val();
    var shiftId = jQuery('#cmbPlosShiftid').combobox('getValue');
	var entryDate=jQuery("#dtePlosDate").datebox('getValue');
	var shiftWise=jQuery('#hdnisShiftWise').val();
//	alert("plmasterId    "+plmasterId+"      entryDate"+entryDate);
	
	dataStr+= "?&hourId="+hourId+"&cellId="+cellId+"&plmasterId="+plmasterId+"&entryDate="+entryDate+"&shiftId="+shiftId+"&shiftWise="+shiftWise;
   //	alert("datastr"+dataStr);
	processAjaxCalls("getAllTime.balpcs",dataStr,"getAllTimeRecallSuccess","getAllTimeRecallError");
}

function getAllTimeRecallSuccess(result)
{

	var unrephourly = parseFloat(result.unaccountedtime);
	var availtimehourly = parseFloat(result.availtime);
	var prodtimehourly = "0"; //Number(jQuery("#txtProdtimehourly").val());
	var plannedqty = Number("0");
	var cycTime = jQuery("#txtPprdCycletime").val();
	if (cycTime=='' || cycTime==' ')
		cycTime='0';
	var rnt = parseFloat(cycTime);
	prodtimehourly =parseFloat(result.productiontime);
	//alert(prodtimehourly);
	var losstimehourly = parseFloat(availtimehourly) - parseFloat(unrephourly) - parseFloat(prodtimehourly);
	
	losstimehourly =losstimehourly.toFixed(0);
	//alert('losstimehourly '+losstimehourly );
	setFocusOnField('txtPprdProducedqty');
	if (rnt=='NaN' || rnt==NaN || rnt==undefined || rnt=='undefined')
		rnt='0';

	if(result.unaccountedtime != "0" || result.productiontime != "0")
		{
			jQuery("#txtUnreptimehourly").val(unrephourly);
			jQuery("#txtAvailtimehourly").val(availtimehourly);
			jQuery("#txtLosstimehourly").val(losstimehourly);
			jQuery("#txtProdtimehourly").val(prodtimehourly);
			if (Number(rnt)==0){
				plannnedqty = 0;}
			else{
				plannnedqty = Number(availtimehourly) / Number(rnt); }
			//alert(Number(availtimehourly)+" to check infn "+Number(rnt));
			//alert(plannnedqty);
			plannnedqty = Number(plannnedqty).toFixed(2);
			setFieldValue("txtPlannedqty",plannnedqty);	
			//jQuery("#txtPlannedqty").val(plannnedqty);
		}
	else{
			var selecthourValue=getFieldValue("selPprdHour");
			if(selecthourValue != 9)
				jQuery("#txtAvailtimehourly").val(availtimehourly);
		
			var avltime = Number(jQuery("#txtAvailtimehourly").val());
			jQuery("#txtProdtimehourly").val("0");
			jQuery("#txtLosstimehourly").val("0");
			jQuery("#txtUnreptimehourly").val(avltime);
			if (Number(rnt)==0)
				plannnedqty = 0;
			else
				plannnedqty = Number(avltime) / Number(rnt);
			plannnedqty = Number(plannnedqty).toFixed(2);
			setFieldValue("txtPlannedqty",plannnedqty);
			//jQuery("#txtPlannedqty").val(plannnedqty);
		//	alert(jQuery("#txtUnreptimehourly").val());
		}
}
function checkLastShiftProduct() {

	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var factId = jQuery("#frmPcsLoss input[id='factory']").val();
	var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	var entryDate = jQuery("#dtePlosDate").datebox('getValue');
	var shift = jQuery('#cmbPlosShiftid').combobox('getValue');
	
	var dataStr = "?&factId="+factId+"&cellId="+cellId+"&entryDate="+entryDate+"&shift="+shift+"&flId="+flId;
//	alert("dataStr"+dataStr);
	processAjaxCalls("getLastShiftProduct.balpcs",dataStr,"prevProductRecallSuccess","prevProductRecallError");
}

function prevProductRecallSuccess(result) {
		//alert(rowData.productId);
		setFieldValue('cmbPprdProductid',rowData.productId);
		setFieldValue('cmbPprdProductmodel',rowData.productModelId);
		setFieldValue('cmbPprdManpower',rowData.manPower);
		setFieldValue('txtPprdCycletime',rowData.cycleTime);
		
}

/*
jQuery("#pcsproductionGrid").on('editField',function(event) {

	//	txtProducedqty_1
	//	alert("1");
		
});
*/
function pcsrejectionGrid_onBlur(rowid)
{
	
}

function pcsrejectionGrid_selectRow(rowid) {
	
	jQuery("#cmbPrbdEquipment_pcsrejectionGrid_"+rowid).combobox({onRequest:function(opts){

		var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	   	var cellId =jQuery('#cmbPlosCellid').combobox('getValue');
	   	return "cellid="+cellId+"&flid="+flId;
	}
		});
	jQuery('#hdnrowidbtn').val(rowid);
    	
	   var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	   	var cellId =jQuery('#cmbPlosCellid').combobox('getValue');
	    var id="cmbPrbdEquipment_pcsrejectionGrid_"+rowid;
        var url= "machineCombo.commonFilter?cellid="+cellId+"&flid="+flId;
     //   alert(id+"    "+url);
   		jQuery("#"+id).combobox('clear');
        reloadCombo("frmPcsLoss",id,url); 
		setFocusOnField('txtPprdProducedqty');

}
function pcsproductionGrid_click(rowid) {
	
}
/*
jQuery("#cmbFunctLocFact").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	//  var sbuId = getFieldValue('cmbFunctLocSBU');
	 return "compId="+compId+"&locnId="+locnId;//+"&combokey="+sbuId;
	}
});
*/

function lossDateEvt(dateField) { 
	var currentDate = getServerDateTime();
	var selDate=jQuery('#dtePlosDate').datebox("getValue");
	var lossDate = jQuery('#'+dateField).datebox("getValue");
	//alert('lossDate'+lossDate);
	if (dateField=="dtePlosFromdate")
		lossDate = lossDate + jQuery('#spnPlosFromtime').spinner('getValue');
	else
		lossDate = lossDate + jQuery('#spnPlosTotime').spinner('getValue');
	//alert(convertStringToDate(lossDate));////
	
	 var diffDate = new Date(currentDate-convertStringToDate(selDate));
	 var days = diffDate/1000/60/60/24;
	//alert(currentDate +" "+selDate+" diff "+days);
	//alert(days.toString().split(".")[0]);
	days=days.toString().split(".")[0];
	/////
	/*if(days>10)
	{
		alert("You Can Not Enter More than 10 Days Back Entry.");
		//popupCommonErrorMsg(" Should Not Exceed Current Date Time2");
		
		fillWithCurrentDate(dateField);
		setFocusOnField("dtePlosDate");
		
		
		/*if (dateField=="dtePlosDate") {
			fillWithCurrentDate("dtePlosDate");
			setFocusOnField("dtePlosDate");
		}
		else {
			fillWithCurrentDate("spnPlosTotime");
			setFocusOnField("dtePlosTodate");
		}
		*/
		/*	return false;
		}
	else{ }*/
	if(convertStringToDate(lossDate) > currentDate)
	{	
		
		popupCommonErrorMsg(" Should Not Exceed Current Date Time");
		fillWithCurrentDate(dateField);
		if (dateField=="dtePlosFromdate") {
			fillWithCurrentDate("spnPlosFromtime");
			setFocusOnField("dtePlosFromdate");
		}
		else {
			fillWithCurrentDate("spnPlosTotime");
			setFocusOnField("dtePlosTodate");
		}
		
			return false;
		}
	else if (dateField=="dtePlosTodate") {
		var fromDate = jQuery('#dtePlosFromdate').datebox("getValue");
		var Strtodate = jQuery('#dtePlosTodate').datebox("getValue");
		//alert( lossDate + fromDate );
		
		//alert(  'Mainlossdate' + Mainlossdate + Strtodate );
		var Mainlossdate =  jQuery('#dtePlosDate').datebox("getValue");			
		
		if (convertStringToDate(Mainlossdate) < convertStringToDate(Strtodate))
		{   
			//alert( ' Inside mainloss ');
			popupCommonErrorMsg(" Todate should not be Greater than loss entery date ");
			var todate = jQuery('#dtePlosDate').datebox('getValue');
   			setFieldValue('dtePlosTodate',todate);		
			return false;
		}
		
		if (convertStringToDate(Mainlossdate) > convertStringToDate(Strtodate))
		{   
			//alert( ' Inside mainloss ');
			popupCommonErrorMsg(" Todate should not be Lesser then loss entery date ");
			var todate = jQuery('#dtePlosDate').datebox('getValue');
   			setFieldValue('dtePlosTodate',todate);
			return false;
		}
		
		if(convertStringToDate(lossDate) < convertStringToDate(fromDate))
		{  
			//alert( ' Inside lossdate ');
			popupCommonErrorMsg(" Should Not Lessthan From Date");
			fillWithCurrentDate(dateField); 
			return false;
		}
		
		/*else {
			var fromTime = getFieldValue("spnPlosFromtime");
			var toTime = getFieldValue("spnPlosTotime");
			//alert('convertStringToDate(lossDate)'+convertStringToDate(lossDate));
			if(fromTime > toTime)
			{
				popupCommonErrorMsg(" Should Not Lessthan From Time");
				//setFieldValue("spnPlosTotime",fromTime);
				fillWithCurrentDate("spnPlosFromtime");
				fillWithCurrentDate("spnPlosTotime");
				//fillWithCurrentDate(dateField); 
				jQuery('#txLossHours').val("0");
				jQuery('#txtPlosLosstime').val("0");
		
				return false;
			} 
		}*/
	}
	else
	    clearValidationErrorMsg(dateField);	


}

function frmPcsLosscmbPlosShiftid_onSelect(record) {

	clearAllFields();
	
	var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
	var date = jQuery('#dtePlosDate').datebox('getValue');
	var shift = record.id;
	
	if (cellId == "") { alert("Select cell");		return ; 	}		
	if (date == "") {   alert("Select Date");		return ;	}
	if (shift == "") {	alert("Select Shift");		return ;	}	

	var calOrDat ="D";
	fnValidateDate(calOrDat, date,shift);
	
	fnLoadHourCombo();
	
	//alert("shift"+record.id);
	//processAjaxCalls('getShiftEndTime.pcs',"&shiftId=",'shiftStartTimeSuccess','shiftStartTimeError');
	processAjaxCalls('getShiftStarEndTime.balpcs',"&shiftId="+record.id,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
	//processAjaxCalls('getShiftStarEndTime.pcs',"&shiftId="+record.id,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
	
	setTimeout(function() {
		checkLastShiftProduct();
		viewProductionGrid();
		viewGrid();
		viewResultGrid();
	},100);
//	showAllTime();
}
 function clearAllFields()
 	{
		jQuery("#cmbPprdProductid").combobox('clear');
		jQuery("#cmbPprdProductmodel").combobox('clear');
		jQuery("#cmbPprdManpower").combobox('clear');
		jQuery('#txtPprdCycletime').val('');
		jQuery('#txtPprdProducedqty').val('');
		jQuery('#txtPprdNoplanduration').val('');
		jQuery('#txtPprdRejectedqty').val('');
		jQuery('#txtPprdReworkqty').val('');
		jQuery('#txtPprdRemarks').val('');

	}
 function clearQtyFields()
	{
	 	enableFields("txtPprdProducedqty");	
	   	totalavailtime = jQuery("#txtAvailtime").val();
	//	jQuery('#txtProdtimehourly').val('');
		jQuery('#txtPprdProducedqty').val('');
		jQuery('#txtPprdRejectedqty').val('');
		jQuery('#txtPprdReworkqty').val('');
		jQuery('#txtPprdRemarks').val('');
		jQuery('#txtPprdNoplanduration').val('0');

	}
function frmPcsLosscmbPlosProdImpact_onSelect(record) 	{
	if (record.id=="Y")
		enableFields("txtPlosProdImpQty");
	else {
		//enableFields("txtPlosProdImpQty");
		disableField("frmPcsLoss", "txtPlosProdImpQty");
		jQuery('#txtPlosProdImpQty').val('');
	}
		
}
/*
function lossStartEvt()
{  
	dtePlosFromdate_onSelect(null);
}

function lossEndEvt()
{
	dtePlosTodate_onSelect(null);
}
*/

function dtePlosFromdate_onSelect(date)
{
	//	compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');
		compareDates('dteShiftstartdate','spnShiftstarttime','dtePlosFromdate','spnPlosFromtime','Start Time should not be lesser than Shift start time');
		actualTimeCalculation();
}
function dtePlosFromdate_onChange(date)
	{
	//	compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');
		compareDates('dteShiftstartdate','spnShiftstarttime','dtePlosFromdate','spnPlosFromtime','Start Time should not be lesser than Shift start time');
		actualTimeCalculation();
	}

function dtePlosTodate_onSelect(date)
{
	compareDates('dtePlosFromdate','spnPlosFromtime','dtePlosTodate','spnPlosTotime','End Time should not be less than start time');
	actualTimeCalculation();
}
function dtePlosTodate_onChange(date)
{
	compareDates('dtePlosFromdate','spnPlosFromtime','dtePlosTodate','spnPlosTotime','End Time should not be less than start time');
	actualTimeCalculation();
}

function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg,toTimeHourId,toTimeMinutesId)
{
	if (getFieldValue("hdnSetFromToTime")=="1")
		return ;
  
   clearValidationErrorMsg(fromDateId);
   clearValidationErrorMsg(toDateId);
   
    var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
    var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
    var fDt = jQuery('#'+fromDateId).datebox('getValue');
    var fTi = jQuery('#'+fromTimeId).spinner('getValue');
    var tDt = jQuery('#'+toDateId).datebox('getValue') ;
    var tTi = jQuery('#'+toTimeId).spinner('getValue') ;
 //   alert(fromDate+"   "+toDate+"  "+fTi+"  "+ tTi);
    if( fDt == ""  || fTi == "" || tDt == ""  || tTi == "")
	    return ;
	    
 	var frDate = jQuery("#dteShiftstartdate").datebox('getValue') + jQuery("#spnShiftstarttime").spinner('getValue');
	//	alert("fromdate"+fromDate);
		var currentDate = getServerDateTime();
	
		
	if(convertStringToDate(toDate) > currentDate)
		{
	//		alert("1");
			showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
	//		alert("2 "+toDateId + " "+ toTimeId);
    	 	fillWithCurrentDate(toDateId);			
			fillWithCurrentDate(toTimeId);
   			displayText(toTimeHourId,currentDate.getHours());
   			displayText(toTimeMinutesId,currentDate.getMinutes());
			
			return ;

		}
	else if(convertStringToDate(frDate) > currentDate)
		{
//		alert("1");
			return;
		}
	else if( compareDateTime(toDate,fromDate) > 0 )
  	{
	  	//alert('fromDate'+fromDate);
    		showValidationErrorMsg(toDateId,errMsg);
   		 	displayText(toDateId,fDt);
   			displayText(toTimeId,fTi);
   			displayText(toTimeHourId,fTi.substring(0,2));
   			displayText(toTimeMinutesId,fTi.substring(3,5));
    		//fillWithCurrentDate(toDateId);			
			//fillWithCurrentDate(toTimeId);
			return ;
    }
	/*else if(toDateId == 'dtePlosFromdate')
    {
	//	alert("3");
        var lossEndDate = jQuery('#dtePlosTodate').datebox('getValue'); 
        var lossEndTime = jQuery('#spnPlosTotime').spinner('getValue');
    	    if(lossEndDate != null && lossEndDate != ' ' && lossEndDate != '')
			{
    	    	lossEndDate = lossEndDate + lossEndTime;
        			if( compareDateTime(lossEndDate,toDate) > 0 )
    			    {
    		        	showValidationErrorMsg('dtePlosFromdate','Loss Start Date Should not be greater than Loss end Date');
    		        	displayText(toDateId,fDt);
    		        	displayText(toTimeId,lossEndTime);
    		   			displayText(toTimeHourId,lossEndTime.substring(0,2));
    		   			displayText(toTimeMinutesId,lossEndTime.substring(3,5));

        		        return;
    				}
    		        else
    		        	clearValidationErrorMsg('dtePlosFromdate');
			}
	
    }
   */
	else if(toDateId == 'dtePlosTodate')
    {
	    
	    var ShiftEndDate = jQuery('#dteShiftenddate').datebox('getValue'); 
        var ShiftEndTime = jQuery('#spnShiftendtime').spinner('getValue');
    	    if(ShiftEndDate != null && ShiftEndDate != ' ' && ShiftEndDate != '')
			{
    	    	ShiftEndDate = ShiftEndDate + ShiftEndTime;
    	    //	alert("ShiftEndDate"+ShiftEndDate);
        			if( compareDateTime(ShiftEndDate,toDate) > 0 )
    			    {
    		        	showValidationErrorMsg('dtePlosTodate','Loss End Date Should not be greater than Shift End Date');
    		        	displayText(toDateId,fDt);
    		        	displayText(toTimeId,ShiftEndTime);
    		        	displayText(toTimeHourId,ShiftEndTime.substring(0,2));
    		   			displayText(toTimeMinutesId,ShiftEndTime.substring(3,5));
    		        	return;
    				}
    		        else
    		        	clearValidationErrorMsg('dtePlosTodate');
			}
	
    }
	else
    	clearValidationErrorMsg(toDateId);
}

function actualTimeCalculation()
{  
	var currentDate = getServerDateTime();
	var totallossminutes ="0";		
	var unreptime = "0";
	var availtime = "0";
	var prodtime ="0";			
	var selDurationSel = jQuery("#chkPlosDurationSel").is(':checked');		
//	alert(jQuery("#spnPlosFromtime").spinner('getValue'));
	
	if(selDurationSel == true)
	{	
		
		//if(jQuery("#spnPlosFromtime").spinner('getValue') != null && jQuery("#spnPlosTotime").spinner('getValue')!= null )				
		var lossFromtime = jQuery("#dtePlosFromdate").datebox('getValue') + jQuery("#spnPlosFromtime").spinner('getValue');
		var lossTotime = jQuery("#dtePlosTodate").datebox('getValue') + jQuery("#spnPlosTotime").spinner('getValue');
	//	alert("lossFrom "+lossFromtime+" losstotime "+lossTotime);
		var losstime=jQuery("txtPlosLosstime").val();
		actualMins = timeDifference(lossFromtime,lossTotime);
		totallossminutes = 	actualMins.minutes;
//		alert("actualMins"+totallossminutes);
		unreptime = jQuery("#txtUnreptime").val();
		availtime = jQuery("#txtAvailtime").val();
		prodtime = jQuery("#txtProdtime").val();
//		alert("1"+unreptime+ " 2 "+availtime+" 3 "+prodtime+" 4 "+totallossminutes);
		unreptime = Number(availtime) - Number(totallossminutes); //  - Number(prodtime);
		unreptime = Number(unreptime).toFixed(2);
		if(Number(availtime) < (Number(totallossminutes) ))  // + Number(prodtime)))
			{
				alert("Loss time can't exceed unresponded time.");
				return;
			}
		displayText('txtLosstime',totallossminutes);
		displayText('txtPlosLosstime',totallossminutes);
	//	displayText('txtUnreptime',unreptime);
												
	}
	else if (selDurationSel == false){
		var totallossminutes;
		totallossminutes = jQuery("#txtPlosLosstime").val();
		unreptime = jQuery("#txtUnreptimehourly").val();
		availtime = jQuery("#txtAvailtimehourly").val();
		prodtime = jQuery("#txtProdtimehourly").val();
		unreptime = Number(availtime) - Number(totallossminutes); //  - Number(prodtime);
		unreptime = Number(unreptime).toFixed(2);
		if((Number(totallossminutes)> unreptime))  // + Number(prodtime)))
			{
				alert("Loss time can't exceed unresponded time.");
				return;
			}
		//displayText('txtLosstimehourly',totallossminutes);
		//displayText('txtLosstimehourly',totallossminutes);
		}
	
	else{}
}

/*
function fngetTotalLossTime()
{
	if (lossDateEvt("dtePlosFromdate")==false)
		return;
	if (lossDateEvt("dtePlosTodate")==false)
		return;	

	var fromDate =jQuery('#dtePlosFromdate').datebox('getValue');
	var toDate =jQuery('#dtePlosTodate').datebox('getValue');
	var frTime =jQuery('#spnPlosFromtime').spinner('getValue');
	var toTime =jQuery('#spnPlosTotime').spinner('getValue');


	var actSftStartTime = jQuery('#hdnSftStartTime').val();
	var actSftEndTime = jQuery('#hdnSftToTime').val();
//	alert(frTime +" "+actSftStartTime);
	if(convertStringToDate(frTime) < convertStringToDate(actSftStartTime))
	{	
		jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
	}
	
//	alert("1 "+fromDate+" 2 "+toDate+" 3 "+frTime+" 4 "+toTime)
	var frDate = jQuery("#dtePlosFromdate").datebox('getValue') + jQuery("#spnPlosFromtime").spinner('getValue');
	var toDate = jQuery("#dtePlosTodate").datebox('getValue') + jQuery("#spnPlosTotime").spinner('getValue');

	actualMins = timeDifference(frDate,toDate);	
//	alert("actual"+actualMins.minutes);
	displayText('txtPlosLosstime',actualMins.minutes);										

	
}*/
/*
function fngetTotalLossTime()
{

	if (lossDateEvt("dtePlosFromdate")==false)
		return;
	if (lossDateEvt("dtePlosTodate")==false)
		return;
	
	
	var frTime =jQuery('#spnPlosFromtime').spinner('getValue');
	var toTime =jQuery('#spnPlosTotime').spinner('getValue');
	
	var mmssArr = frTime.split(':');
	if (mmssArr[0].length==1) 
		mmssArr[0] = "0" + mmssArr[0];
	if (mmssArr[1].length==1)
		mmssArr[1] = "0" + mmssArr[1];
	
	frTime = mmssArr[0] + ":" + mmssArr[1];
	mmssArr = toTime.split(':');
	if (mmssArr[0].length==1) 
		mmssArr[0] = "0" + mmssArr[0];
	if (mmssArr[1].length==1)
		mmssArr[1] = "0" + mmssArr[1];
	
	toTime = mmssArr[0] + ":" + mmssArr[1];
	
	var actSftStartTime = jQuery('#hdnSftStartTime').val();
	var actSftEndTime = jQuery('#hdnSftToTime').val();
	var fromdate = jQuery('#dtePlosFromdate').datebox("getValue");	
	fromdate = fromdate + jQuery('#spnPlosFromtime').spinner('getValue');
	//else
		//lossDate = lossDate + jQuery('#spnPlosTotime').spinner('getValue');
	alert("1 "+frTime+" "+actSftStartTime +"  "+fromdate+" ");
//	alert(convertStringToDate(frTime).toTimeString());
	if(convertStringToDate(frTime) < convertStringToDate(actSftStartTime))
	{ 
		alert("2");
		jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
		return false;
	}
	if(convertStringToDate(fromdate) < convertStringToDate(actSftStartTime))
	{ 
		jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
		alert("3");
		return false;
	}
	
		
//	/*	if (parseInt(frTime.replace(':','')) < parseInt(actSftStartTime.replace(':','')) ) { 
//		jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
//		return false;
//	}
//	
//	 if (parseInt(toTime.replace(':','')) > parseInt(actSftEndTime.replace(':','')) ) { 
//		jQuery('#spnPlosTotime').spinner('setValue',actSftEndTime);
//		return false;
//	} 
	
	var fromTime = jQuery('#dtePlosFromdate').datebox('getValue') +frTime;
	var toTime = jQuery('#dtePlosTodate').datebox('getValue') +toTime;
	
	//fromTime  = convertStringToDate(fromTime);
	//toTime  = convertStringToDate(toTime);
	
	var diffTime = timeDifference(fromTime, toTime);
	
	//alert(parseInt(diffTime.hours));
	if ( parseFloat(diffTime.days) < 0 && parseFloat(diffTime.hours) < 0) {	
		popupCommonErrorMsg(" Should Not Lessthan From Time");
		//setFieldValue("spnPlosTotime",getFieldValue("spnPlosFromtime"));
		fillWithCurrentDate("spnPlosFromtime");
		fillWithCurrentDate("spnPlosTotime");

		jQuery('#txLossHours').val("0");
		jQuery('#txtPlosLosstime').val("0"); 
		
		/* var midTime = jQuery('#dtePlosDate').datebox('getValue') + "23:59";
		var midFrom = jQuery('#dtePlosDate').datebox('getValue') + "00:00";
		var diffMidUp = timeDifference(fromTime, midTime);
		//alert(diffMidUp.hours);
		var diffMidFrom = timeDifference(midFrom, toTime);
		//alert(diffMidFrom.hours);
		var calTime =  parseFloat( diffMidFrom.hours) + parseFloat(diffMidUp.hours) + 1;
		calTime = calTime.toFixed(2);
		jQuery('#txLossHours').val(calTime);
		 */


/*	}
	else {
		
		var calTime = diffTime.hours;
		if (calTime<0) { 
			popupCommonErrorMsg(" Should Not Lessthan From Time");
			//setFieldValue("spnPlosTotime",getFieldValue("spnPlosFromtime"));
			fillWithCurrentDate("spnPlosFromtime");
			fillWithCurrentDate("spnPlosTotime");

			jQuery('#txLossHours').val("0");
			jQuery('#txtPlosLosstime').val("0");
		}
		else {
			
			var hrsss =diffTime.hours;
			hrsss= String(hrsss);
			var timArr = hrsss.split('.');
			hrsss = timArr[0];
			//alert(hrsss);
			var tmm = ((oDiff.minutes/60)-hrsss)*60  ;
			//alert(tmm);
			tmm= String(tmm);
			var tmmArr = tmm.split('.');
			tmm = tmmArr[0];
			calTime = hrsss +':' + tmm; 
		
			if(calTime == 'NaN:NaN')
			{
				jQuery('#txLossHours').val('');
				jQuery('#txtPlosLosstime').val('');
			}
			else
			{
				jQuery('#txLossHours').val(calTime);
				jQuery('#txtPlosLosstime').val(oDiff.minutes);
			}
			
		}
	}

}
*/
function  getPCSShift(record)
{
	
	jQuery('#cmbPlosShiftid').combobox('setValue',record.shift);
	viewProductionGrid();
	viewGrid();
	checkLastShiftProduct();
	processAjaxCalls('getShiftStarEndTime.balpcs',"&shiftId="+record.shift,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
//	showAllTime();
	var cellId = jQuery('#cmbPlosCellid').combobox("getValue");
	var date = jQuery('#dtePlosDate').datebox('getValue');
	var shift = record.shift;

	if (cellId == "")
		 { 	return  false;	}		
	if (date == "") {   alert("Select Date");		return ;	}
	if (shift == "") {	alert("Select Shift");		return ;	}	

	var calOrDat ="D";
	fnValidateDate(calOrDat, date,shift);
	
}
/*	
function shiftStartEndTimeSuccess(result) {
		//alert(result.endTime);.
		var availtime=result.totalShifttime;
		var hours='';
		var i='';
		var shiftStarttime = result.startTime;
		var shiftEndtime = result.endTime;
		var shiftStartHour = shiftStarttime.substring(0,2);
		var shiftStartMinutes = shiftStarttime.substring(3,5);
		var shiftEndHour = shiftEndtime.substring(0,2);
		var shiftEndMinutes = shiftEndtime.substring(3,5);
		var todate = jQuery('#dtePlosDate').datebox('getValue');
	//	alert(shiftStarttime+" "+shiftEndtime+"  "+shiftStartHour+" "+shiftStartMinutes);
		var dateTime = "01-Jan-2014 06:20:00";
		var startDatetime=result.startDate;
		var endDatetime = result.endDate;
		var endDate = new Date();
		
//		alert("endDate"+endDate);
		var startDate = new Date(todate.replace(/-/g, '/'));
		endDate.setDate(startDate.getDate()+1);
//		alert("endDate"+endDate);
	//	alert("endDate"+endDate +"   "+startDate);
		var date = endDate.getDate();
		var month = getMonthStringFromInt(endDate.getMonth());
		var year = endDate.getFullYear();
		endDate = date+"-"+month+"-"+year ;

		if(result.shiftCode != "1" && result.shiftCode == result.shiftOrder)
			{
				if(startDatetime < dateTime && endDatetime < dateTime)
				{
				//	In Shift 3rd
					setFieldValue('dteShiftstartdate',endDate);
					setFieldValue('dteShiftenddate',endDate);
					setFieldValue('dtePlosFromdate',endDate);
					setFieldValue('dtePlosTodate',endDate);
				
					readOnlyFields('dtePlosFromdate');
					readOnlyFields('dtePlosTodate');
				}
				else if(endDatetime > "01-Jan-2014 23:59:59")
				{
//					In Shift 2nd

					setFieldValue('dteShiftstartdate',todate);
					setFieldValue('dteShiftenddate',endDate);
					setFieldValue('dtePlosFromdate',todate);
					setFieldValue('dtePlosTodate',todate);
					enableFields("dtePlosTodate");
				}
			}
		else
			{
			//  In shift 1st and General
				
				setFieldValue('dteShiftstartdate',todate);
				setFieldValue('dteShiftenddate',todate);
				setFieldValue('dtePlosFromdate',todate);
				setFieldValue('dtePlosTodate',todate);
				readOnlyFields('dtePlosFromdate');
				readOnlyFields('dtePlosTodate');
			}
	
		setFieldValue('spnPlosFromtime',result.startTime);
		setFieldValue('spnPlosTotime',result.startTime);
		setFieldValue('spnShiftstarttime',result.startTime);
		setFieldValue('spnShiftendtime',result.endTime);
		setFieldValue('txtLossHourfromtime',shiftStartHour);
		setFieldValue('txtLossMinutesfromtime',shiftStartMinutes);
		setFieldValue('txtLossHourtotime',shiftStartHour);
		setFieldValue('txtLossMinutestotime',shiftStartMinutes);
		
	
		var fromDate = jQuery("#dteShiftstartdate").datebox('getValue') + jQuery("#spnShiftstarttime").spinner('getValue');
	
		var currentDate = getServerDateTime();
		if(convertStringToDate(fromDate) > currentDate)
		{
		
			alert("Can't enter for future Shift..!!");
			return false;
		}

		
	//	java.util.Date startTime =  simpleDatetimeFormat.parse(result.startDate);
	//	java.util.Date endTime =  simpleDatetimeFormat.parse(result.endDate);
	//	alert(startDatetime+" "+endDatetime);
			
	//	jQuery("#hdnSftStartTime").val(result.startTime);
	//	jQuery("#hdnSftToTime").val(result.endTime);
		
		readOnlyFields("dteShiftstartdate");
		readOnlyFields("dteShiftenddate");
		readOnlyFields('spnShiftstarttime');
		readOnlyFields('spnShiftendtime');
		
		jQuery("#txtAvailtime").val(availtime);
		jQuery("#txtUnreptime").val(availtime);
		jQuery("#txtProdtime").val("0");
		jQuery("#txtLosstime").val("0");
		jQuery("#hdnSftDuration").val(availtime);
		
		availtime = Number(availtime);
		hour=availtime/Number(60);
		hour=Math.ceil(hour);

		jQuery('#selPprdHour')
		    .find('option')
		    .remove()
		    .end()
		    .append('<option value=" "> </option>')
		    .val(' ');

		for (i = 1;  i <=hour ; i++) 
			{
					jQuery('#selPprdHour').append(jQuery('<option/>', { 
				        value: i,
				        text : i 
				    }));
			}
	
		jQuery('#selPprdHour').append(jQuery('<option/>', { 
	        value: i,
	        text : 'H+' 
	    }));
		
         
		//alert(jQuery('#hdnSftToTime').val());
		//alert('shiftStartEndTimeSuccess'+result.startTime);
		//alert(result.startTime);
	}

*/
	function shiftStartTimeSuccess(result) {
		//alert(result.endTime);
		//jQuery('#hdnSftStartTime').val(result.startTime);
		jQuery('#hdnSftToTime').val(result.endTime);
	//	alert(result.endTime);
		//alert(jQuery('#hdnSftToTime').val());
		//alert('shiftStartEndTimeSuccess'+result.startTime);
		//alert(result.startTime);
	}
var viewGridTimer = null; 
	function viewGrid() {
		
		clearTimeout(viewGridTimer);               // NEW
		viewGridTimer = setTimeout(function () {   // NEW
		
		var ds = "?&fromdate="+jQuery('#dtePlosFromdate').datebox('getValue');
		ds+="&shiftid="+jQuery('#cmbPlosShiftid').combobox('getValue');
		var shift=jQuery('#cmbPlosShiftid').combobox('getText');
		
		var flid = jQuery("#frmPcsLoss input[id='flid']").val();
		var todate = jQuery('#dtePlosDate').datebox('getValue');
		if(todate.length<11) 
			todate = '0'+todate;
		ds+="&flid="+flid;
		ds+="&todate="+todate;
		ds+="&shift="+shift;
		//actualTimeCalculation();
	//	alert("ds   "+ds);
	
		processGridnew("pcsLoss_input.balpcs",ds, "pcslossGrid", "pcslossPager","","pcsdblClick","","pcsloss_loadComplete");

	}, 150);                                   // NEW
	}
function viewRejectionGrid() {
			//alert("viewRejectionGrid");
	 //jQuery("#rejectionGridbtn").show();
	  var mstKeyid=jQuery('#hdnPprdKeyid').val();

	/*	var ds = "?&fromdate="+jQuery('#dtePlosFromdate').datebox('getValue');
		ds+="&shiftid="+jQuery('#cmbPlosShiftid').combobox('getValue');
		var flid = jQuery("#frmPcsLoss input[id='flid']").val();
		var todate = jQuery('#dtePlosDate').datebox('getValue');
		if(todate.length<11) 
			todate = '0'+todate;
		ds+="&flid="+flid;
*/
		var ds ="?&mstKeyid="+mstKeyid;
		
		//alert(ds);
		processGridnew("pcsrejectionEntry_input.balpcs",ds,"pcsrejectionGrid","","Rejection","","","pcsrejectionGrid_successcallback");
			
	}
	
	function viewProductionGrid() {
		
		var ds = "?&fromdate="+jQuery('#dtePlosDate').datebox('getValue');
		ds+="&shiftid="+jQuery('#cmbPlosShiftid').combobox('getValue');

		var flid = jQuery("#frmPcsLoss input[id='flid']").val();
		var cellId =jQuery("#frmPcsLoss input[id='cell']").val();
		var shiftWise=jQuery("#hdnisShiftWise").val();
//  	var todate = jQuery('#dtePlosTodate').datebox('getValue');
		var todate = jQuery('#dtePlosDate').datebox('getValue');
		if(todate.length<11) 
			todate = '0'+todate;
			ds+="&flid="+flid;
			ds+="&cellId="+cellId;
			ds+="&todate="+todate;
			ds+="&shiftWise="+shiftWise;
		processGridnew("pcsproductionEntry_input.balpcs",ds,"pcsproductionGrid","","Production","productionGriddblClick","","pcsproducationGrid_successcallback","productiionGridSelectRow");
	}
function pcsviewHourlyResultGrid() {
		
		var ds = "?q=2&fromdate="+jQuery('#dtePlosDate').datebox('getValue');
		ds+="&shiftid="+jQuery('#cmbPlosShiftid').combobox('getValue');

		var flid = jQuery("#frmPcsLoss input[id='flid']").val();
		var factId = jQuery("#frmPcsLoss input[id='factory']").val();
		
		var sectId = jQuery("#frmPcsLoss input[id='section']").val();

		var cellId =jQuery("#frmPcsLoss input[id='cell']").val();
	
//  	var todate = jQuery('#dtePlosTodate').datebox('getValue');
		var todate = jQuery('#dtePlosDate').datebox('getValue');
		if(todate.length<11) 
			todate = '0'+todate;
			ds+="&flid="+flid;
			ds+="&cellId="+cellId;
			ds+="&todate="+todate;
		//	jQuery("#hdnDataStr").val(dataString);
			processGridnew("pcsviewHourlyResultGrid_input.balpcs",ds,"pcsviewHourlyResultGrid","","View Result Production","pcsviewHourlyResultGriddblClick","","pcsviewHourlyResultGrid_successcallback","");

				
//			var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&shiftId="+shiftId+"&entryDate="+entryDate+"&cellId="+cellId+"&mchId="+mchId;
			//alert(dataString);		
			
	//		processAjaxCalls('productionGrid_getCol.pcs',dataString,'shift_OnSuccess','shift_OnError');


}
	

function prevProductRecallSuccess(result) {
		//alert(result.productId);
//		if (result.length>1) {
			setFieldValue('cmbPprdProductid',result.productId);
			setFieldValue('cmbPprdProductmodel',result.productModelId);
			setFieldValue('cmbPprdManpower',result.manPower);
			setFieldValue('txtPprdCycletime',result.cycleTime);
			
			/*
			var entryDate = jQuery("#dtedate").datebox('getValue');					
			
			fillCycleTime();			
			showAllTime();
			var dataStr = "&factId="+factId+"&prdModelId=&mchId="+mchId+"&entryDate="+entryDate;
			reloadCombo("frmPcsView","cmbproductid","combo_product.pcs?"+dataStr);			
			
			setTimeout(function() {jQuery('#cmbproductid').combobox('setValue',result.productId);},550);
			setTimeout(function() {productIdOnSelect(result.productId);	},550);
			//alert(result.wno);
			setTimeout(function() {jQuery('#txtwno').val(result.wno);},550);
							
			setProductModel(result.productId); */
					
//		}					
}

function pcsdblClick(rowid) { 

	//var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2); 		
	
	
	
	var rowData = jQuery("#pcslossGrid").jqGrid('getRowData',rowid);
//	alert(rowData.LossReasonId);
	
	jQuery('#hdnPlosKeyid').val(rowData.Keyid);
	jQuery('#hdnPlrkKeyid').val(rowData.LossReasonId);
	setFieldValue('dtePlosFromdate',rowData.FromDate);
	setFieldValue('spnPlosFromtime',rowData.FromTime);
	setFieldValue('txtLossHourfromtime',rowData.FromTime.substring(0,2));
	setFieldValue('txtLossMinutesfromtime',rowData.FromTime.substring(3,5));
	setFieldValue('dtePlosTodate',rowData.ToDate);
	setFieldValue('spnPlosTotime',rowData.ToTime);
	setFieldValue('txtLossHourtotime',rowData.ToTime.substring(0,2));
	setFieldValue('txtLossMinutestotime',rowData.ToTime.substring(3,5));
	setFieldValue('txtPlosLosstime',rowData.Minutes);
	setFieldValue('txtLossHours',rowData.Minutes);
	setFieldValue('cmbSelPrdHourL',rowData.HrNo);
	
	setFieldValue('cmbPlosLossid',rowData.LossId);
	setFieldValue('cmbMainLoss',rowData.LossParentId);
	//setFieldValue('cmbPlosProdImpact',rowData.Prodn.Impact);
	//setFieldValue('txtPlosProdImpQty',rowData.Prodn.Imp.Qty(OtherthenDowntime));
	
	setFieldValue('txtPlosRemarks',rowData.Remarks);
	setFieldValue('cmbPlosMachineid',rowData.Machineid);
	
	setFieldValue('txtPlosLossreason',rowData.LossReason);
	jQuery('#cmbPlosToolId').combobox('enable');
	setFieldValue('cmbPlosToolId',rowData.ToolId);
	
	//if (rowData.Prodn.Impact == "Y")
		if (rowData.ProdnImpact == "Y")//New
		enableFields("txtPlosProdImpQty");
	else
		//enableFields("txtPlosProdImpQty");
		disableField("frmPcsLoss", "txtPlosProdImpQty");
	
/*	
	var lossTime = jQuery("#pcslossGrid").jqGrid('getCell',id,7);
	var lossId = jQuery("#pcslossGrid").jqGrid('getCell',id,8);
	var lossReasonId = jQuery("#pcslossGrid").jqGrid('getCell',id,10);
	var lossDesc = jQuery("#pcslossGrid").jqGrid('getCell',id,12);
	var tradeId = jQuery("#pcslossGrid").jqGrid('getCell',id,13);	
//	var prodImpact = jQuery("#pcslossGrid").jqGrid('getCell',id,15);
//	var prodImpactQty = jQuery("#pcslossGrid").jqGrid('getCell',id,16);	
//	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2); 		
	setFieldValue('cmbPlosLossid',lossReasonId);
	setFieldValue('cmbMainLoss',lossId);
//	jQuery('#hdnPlosKeyid').val(plosKeyid);
	
//	setFieldValue('cmbPlosProdImpact',prodImpact);
	
//	if (prodImpact=="Y")
//		enableFields("txtPlosProdImpQty");
//	else
		//enableFields("txtPlosProdImpQty");
//		disableField("frmPcsLoss", "txtPlosProdImpQty");
	
//	jQuery('#txtPlosProdImpQty').val(prodImpactQty);
//	jQuery('#txLossHours').val(lossTime);
//	jQuery('#txtPlosLosstime').val(lossTime);
//	jQuery('#txtPlosLossdescription').val(lossDesc);
	
//	setFieldValue('cmbPlosLossreason',lossReasonId);
  
//	setFieldValue('cmbPlosTradeid',tradeId);
	
//	setFieldValue('cmbPlosProdImpact',prodImpact);
	
//	alert(FromDate + " a "+ ToDate +" b " +fromtime + " c "+totime);
	
//	setFieldValue('dtePlosFromdate',rowData.FromDate);
//	setFieldValue('spnPlosFromtime',frmTime);		
//	setFieldValue('dtePlosTodate',ToDate);
//	setFieldValue('spnPlosTotime',toTime);
	//setFocusOnField('spnPlosFromtime');
*/	
	fngetTotalLossTime();
}


function productionGriddblClick(rowid)
{
	
//	jQuery("#cmbPprdManpower").combobox('clear');
	
	//var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',rowid);
	//var keyid=rowData.PprdKeyid;
	var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData', rowid);
    //console.log("rowData", rowData);          // check the real column names here
    var keyid = rowData.PprdKeyid || rowData.keyId || rowData.keyid;
    
    jQuery("#hdnPprdKeyid").val(keyid || '');
	jQuery("#hdnProductid").val(rowData.Productid);
	jQuery("#hdnPprdKeyid").val(keyid);
	jQuery("#hdnPldetailsId").val(rowData.dtlkeyId);
	jQuery("#hdnPlmMasterId").val(rowData.mstkeyId);
	setFieldValue('selPprdHour',rowData.selPprdHour);
	setFieldValue('cmbPprdProductid',rowData.Productid);
	setFieldValue('cmbPprdProductmodel',rowData.Productmodelid);
	setFieldValue('cmbPprdManpower',rowData.Manpowerid);
	setFieldValue('txtPprdNoplanduration',rowData.txtPprdNoplanduration);
	setFieldValue('txtPprdCycletime',rowData.txtPprdCycletime);
	setFieldValue('txtPprdProducedqty',rowData.txtPprdProducedqty);
	setFieldValue('txtPprdReworkqty',rowData.txtPprdReworkqty);
	setFieldValue('txtPprdRejectedqty',rowData.txtPprdRejectedqty);
//	setFieldValue('txtPprdProducedqty',jQuery("#txtProducedqty_"+rowid).val());
//	setFieldValue('txtPprdReworkqty',jQuery("#txtReworkqty_"+rowid).val());
//	setFieldValue('txtPprdRejectedqty',jQuery("#txtRejectqty_"+rowid).val());
	setFieldValue('txtPprdRemarks',rowData.txtPprdRemarks);
	setFieldValue('txtAvailtimehourly',rowData.Availtimehourly);
	setFieldValue('txtProdtimehourly',rowData.Prodtimehourly);
	setFieldValue('txtLosstimehourly',rowData.Losstimehourly);
	setFieldValue('txtUnreptimehourly',rowData.Unreptimehourly);
	setFocusOnField('txtPprdProducedqty');
	
	 if(rowData.txtPprdReworkqty=='0' && rowData.txtPprdRejectedqty=='0')
	{
		jQuery( "#pcsproductionGrid" ).setGridWidth(1000);
		jQuery("#rejectionGridbtn").hide();
	}
	 else if(rowData.txtPprdReworkqty != '0' || rowData.txtPprdRejectedqty != '0' )
		{
		
			jQuery( "#pcsproductionGrid" ).setGridWidth(630);
			jQuery("#rejectionGridbtn").show();
			viewRejectionGrid();
		}
	 else{
		 	jQuery( "#pcsproductionGrid" ).setGridWidth(1000);
			jQuery("#rejectionGridbtn").hide();
		 }
		
}

function getGridRowid(hourvalue,productvalue,manpower){
	//		alert("hourvalue"+hourvalue+"productvalue"+productvalue+" m "+manpower);
	 		var hourdata='';
			var productiddata='';
			var tempCount='0';
			var tempProductCount ='0';
			var tempProductrowid ='';
			var temprowid='';	
			var clrKeyid='';
			var hdnmasterkeyid='';
			var manPowerid = '';
			var hournoplan = '';
			productiddata=productvalue;
	 		hourdata=hourvalue;
	 		manPowerid = manpower;
	 		 if(hourdata == '' )
		 		 {	
		 		 	hourdata =jQuery("#selPprdHour").val();
		 		
		 		 }
	 	
	 		 if(productiddata == '' ) 
	 			 {
	 		
	 				productiddata = jQuery("#cmbPprdProductid").combobox('getValue');
	 			 
	 			 }
 			 
	//		alert("hourdata "+hourdata+" productiddata "+productiddata+" manpower "+manPowerid);
	    	
  			var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
  		   	for (var i = 1; i <= productionRow.length; i++) {
  		
	    		var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
	    		var gridhourdata = rowData.selPprdHour;
	    		var gridproductiddata=rowData.Productid;
	    		var gridManpower = rowData.Manpowerid;
	    		var	gridhournoplan = rowData.PprdHourlynoplan;
	  // 			alert("gridhourdata "+gridhourdata+" gridProductid "+gridproductiddata+"gridManpower"+gridManpower);
			
				if(gridhourdata == hourdata && gridproductiddata == productiddata && gridManpower == manPowerid)
	    		{
		    	//	alert("1");
	//				productionGriddblClick(i);
    				return false;
	    		}
				if(gridhourdata == hourdata && gridproductiddata != productvalue && gridproductiddata != "-" && gridManpower != manPowerid && gridManpower != "-"  )
	    		{
		//			alert("2");
					clrKeyid ='clr';
					temprowid=i;
				}
	    		
				if(gridhourdata == hourdata &&  productvalue == '' )
					{
		//				alert("3");
						temprowid=i;
						tempCount=Number(tempCount) + Number(1);
					}
				if(gridhourdata == hourdata && gridproductiddata == productiddata && manpower == '')
				{
		//			alert("4");
					tempProductrowid=i;
					tempProductCount=Number(tempProductCount) + Number(1);
					hournoplan = gridhournoplan;
			//	  	alert("tempProductCount"+tempProductCount);
				}
				
  		   	}
		//	alert("afterfor");
  		     /** After for loop */  
				
  		    	if(tempCount == '1' &&  productvalue == '' && manpower == '' )
		    		{
		  //  			alert("5   "+temprowid);
		    			productionGriddblClick(temprowid);
		    			return false;
		    		}
  		    	
	    		if(tempCount != '1' && productvalue == '' && productionRow.length != 0 ) // && hournoplan !='Y'
		    		{
		//	    		alert("6  ");
		    			jQuery("#hdnPprdKeyid").val('');
		    			jQuery('#txtProdtimehourly').val('0');
		    			jQuery('#txtPprdNoplanduration').val('0');
		    			clearQtyFields();
		    			gridTimeHourlyFilling();
			    	}
	    	/*	if(tempCount != '1' && productvalue == '' && productionRow.length != 0 )  // && hournoplan !='N'
	    		{
	    			jQuery("#hdnPprdKeyid").val('');
	    			clearQtyFields();
		    	}
		    	*/
		    	if(tempProductCount == '1' && productvalue !='' && manpower =='' )
		   		{
		  //		  		alert("7  "+tempProductrowid);
	    				productionGriddblClick(tempProductrowid);
	    				return false;
	    			}
		    	if(tempProductCount != '1' && productvalue !='' && productionRow.length != 0 && manpower =='' )
		   		{
		 //   		alert("8  ");
		    		if(hournoplan == "N")
			    		{
		//    			alert("9");
			    			jQuery("#hdnPprdKeyid").val('');
			    			jQuery('#txtPprdNoplanduration').val('0');
			    		}
		    		
	    			clearQtyFields();
	    			gridTimeHourlyFilling();
	    		}
	  
	    		
}

function fillHourValueforDuplicateProduct(hdnmasterkeyid){
	var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
  		//	alert(""+productionRow.length);
  		   	for (var i = 1; i <= productionRow.length; i++) {
  		
	    		var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
	    	//	var gridhourdata = rowData.selPprdHour;
	    		if(hdnmasterkeyid == rowData.dtlkeyId)
		    		{
	    		//		displayText('txtAvailtimehourly',rowData.Unreptimehourly);
	    				displayText('txtUnreptimehourly',rowData.Unreptimehourly);
	    				displayText('txtProdtimehourly','0');
	    				
		    		}
  		   	}
	    	
}

function frmPcsLoss_successsCallback(result) 
{
	jQuery("#rejectionGridbtn").hide();
	jQuery('#hdnPldetailsId').val(result.pldetailsId);
	jQuery('#hdnPlmMasterId').val(result.plmasterId);
	jQuery('#hdnPprdKeyid').val(result.pprdKeyid);
	jQuery('#hdnPlosKeyid').val('');
	jQuery('#txtPlosProdImpQty').val('');
	jQuery('#txtPlosLossdescription').val('');
	jQuery('#txLossHours').val('0');
	jQuery('#txtPlosLosstime').val('0');
	jQuery('#lblLossReason').html('Loss Reason');
	jQuery('#cmbPlosLossid').combobox('clear');
	jQuery('#cmbPlosTradeid').combobox('clear');
	jQuery('#cmbMainLoss').combobox('clear');
	jQuery('#cmbPlosMachineid').combobox('clear');
	jQuery('#txtPlosLossreason').val('');
	jQuery('#txtPlosRemarks').val('');
	jQuery('#txtPlosSource').val('');
	jQuery('#cmbPlosSource').combobox('clear');
	jQuery('#cmbPlosToolId').combobox('clear');
	//jQuery("#chkPprdShiftnoplan").attr('checked', true);
	//jQuery('#txtPlosRemarks').combobox('clear');
	setFieldValue('cmbPlosProdImpact','N');
	disableField("frmPcsLoss", "txtPlosProdImpQty");
	if(result.Mode == "pcsLossCaptureSave")
	{
		  viewGrid();
		  var hour = jQuery("#selPprdHour").val();
		  hour = Number(hour) + Number(1);
		  jQuery("#selPprdHour").val(hour);
		  setFieldValue("txtProdtimehourly","0.00");
		  setFieldValue("txtUnreptimehourly","0.00");
		  var title = "Production";
			jQuery("#tabLossEntry").tabs('select',title);
			clearQtyFields();
	}
	 if(result.Mode == "productionSave" && jQuery("#chkPprdShiftnoplan").is(':checked')==true) 
	{
			//alert(78);
		   jQuery("#chkPprdShiftnoplan").attr('checked', true);
				   
	}
	 else
		
	   jQuery("#chkPprdShiftnoplan").attr('checked', false);
	   jQuery("#chkPprdHourlynoplan").attr('checked', false);
	   var hour1 = jQuery("#selPprdHour").val();
	   setFieldValue("cmbSelPrdHourL",hour1);
		
	/*	if(result.Mode == "productionSave")
	{
		 jQuery("#chkPprdShiftnoplan").attr('checked', true);
		 setFieldValue("txtProdtimehourly","0.00");
		  setFieldValue("txtUnreptimehourly","0.00");
			  alert("yes....");
		  /* jQuery("#chkPprdHourlynoplan").attr('checked', false);
		   var hour1 = jQuery("#selPprdHour").val();
		   setFieldValue("cmbSelPrdHourL",hour1); //
			//	 jQuery("#chkPprdShiftnoplan").attr('checked', false);
				   
	 } 
			  */
	//jQuery("#chkPprdShiftnoplan").attr('checked', false);
	viewResultGrid();
	viewProductionGrid();

	if(result.Mode == "rejectionSave")
	{
	var hour = jQuery("#selPprdHour").val();
	var unreptimehourly = Number(jQuery("#txtUnreptimehourly").val());
	
	if(Number(unreptimehourly) >= 1)
		{
		
		if(jQuery("#chkPprdShiftnoplan").is(':checked')==true){
			

			}
		else
			
			//alert("Enter "+unreptimehourly+" minutes loss for Hour no. "+hour);
			alert(" Record unreported minutes for Hour no. "+hour);
			var title = "Loss Entry";
			jQuery("#tabLossEntry").tabs('select',title);
			setFieldValue("cmbSelPrdHourL",hour);
			setFocusOnField('txtPlosLosstime');
			return false;
			
		}
	else{
			viewRejectionGrid();
			hour = Number(hour) + Number(1);
			jQuery("#selPprdHour").val(hour);
			clearQtyFields();
			fillHourlyCalculations();
			setFocusOnField('txtPprdProducedqty');
			return false;
		}
	}

	var rejection=result.rejection;
	var rework = result.rework;
	viewGrid();

	if(result.Mode == "productionSave")
	{
		if(rejection.length != "0" || rework.length != "0" )
		{	
			var type="";
			var rejqty="0";
			var reworkqty = "0";
			var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
			for(var i=1; i<= rejectRow.length; i++)
				{
					var rowData = jQuery("#pcsrejectionGrid").jqGrid('getRowData',i);
						type = rowData.cmbPrbdType;
					if( type == "Reject")
						{
							rejqty=Number(rejqty) + Number(rowData.txtPrbdQuantity);
						}
					if( type == "Rework" )
						{
							reworkqty=Number(reworkqty) + Number(rowData.txtPrbdQuantity);
						}
		}
		if( ( rejection>0 ||  rework>0 ) && (rejection != rejqty || rework != reworkqty))
		{
			var retval=confirm("Add Rejection Breakup");
   			if( retval == true )
     		   {
   				  addNewGridRow('pcsrejectionGrid');	
    		   }
			  else 
   		      {
       		    alert("If you will not Enter Breakup then it will make Rework and Rejection '0'.");
    	       	var val=confirm("Are you sure you don't want to enter breakup ??");
    	     	if(val == true)
        	    {
    	     		rejectionreworkqtyvalid();
        	    }
    	     	else
    	     		addNewGridRow('pcsrejectionGrid');
    		  }
		}
		else if((rejection=='' || rework=='') || (rejection=='0' && rework=='0'))
		{
			var curhourno =result.hourNo;
			var unreptimehourly = Number(jQuery("#txtUnreptimehourly").val());
			var shiftWise = jQuery('#hdnisShiftWise').val();
			if(Number(unreptimehourly) >= 1 )
			{
				
				if(jQuery("#chkPprdShiftnoplan").is(':checked')==false){
					

					}
				else{
					}
				//alert("Enter "+unreptimehourly+" minutes loss for Hour no. "+curhourno);
				if(shiftWise=='Y')
					alert(" Record unreported minutes ");

				else
				alert(" Record unreported minutes for Hour no. "+curhourno);
				var title = "Loss Entry";
				jQuery("#tabLossEntry").tabs('select',title);
				setFocusOnField('txtLossHourfromtime');
				return false;
			}
			else		
			{
				var hour = jQuery("#selPprdHour").val();
				hour = Number(hour) + Number(1);
				jQuery("#selPprdHour").val(hour);
				clearQtyFields();
				fillHourlyCalculations();
				setFocusOnField('txtPprdProducedqty');
				return false;
			}
		}
	}
	else
	{
		jQuery("#rejectionGridbtn").show();
		jQuery( "#pcsproductionGrid" ).setGridWidth(630);
		var hour = jQuery("#selPprdHour").val();
		hour = Number(hour) + Number(1);
		jQuery("#selPprdHour").val(hour);
		clearQtyFields();
		fillHourlyCalculations();
		setFocusOnField('txtPprdProducedqty');
		return false;
	}
}
				/*if(result.Mode == "productionSave")
				{
					var curhourno =result.hourNo;
					alert("4 "+curhourno);
					var unreptimehourly = Number(jQuery("#txtUnreptimehourly").val());
					alert(unreptimehourly);
				}*/
	else
		{
			return false;
		}

}
	
	function frmPcsLoss_exceptionCallback(result) {
           alert(result.tpmException);
	//	alert("Loss Column not mapped(Incorrect loss).");
	}
	

function pcsloss_loadComplete(){

	var allRow = jQuery("#pcslossGrid").jqGrid('getDataIDs');
	var pldetailsId = "";
		pldetailsId = jQuery("#pcslossGrid").jqGrid('getCell',1,1);
	if (allRow.length>0)
	
	var rowData = jQuery("#pcslossGrid").jqGrid('getRowData',1);
	
	pldetailsId = rowData.PldetailsId;
	if (pldetailsId == 'false')	
		pldetailsId='';
	
	//alert('3' + pldetailsId);
	jQuery('#hdnPldetailsId').val(pldetailsId);
	//alert(1);
	//fillComboBoxWithGrid("frmPcsLoss","cmbPlosTradeid_","trade.commonFilter");
		
	jQuery("#pcslossGrid td").css('word-wrap','break-word');	
	
	// New
	jQuery("#pcslossGrid").jqGrid('setGridWidth', 900, true);
	
	 /* jQuery("#lossNo_1").val(2);
	 jQuery("#lossNo_2").val(9.12);
	 jQuery("#lossNo_3").val(1.1);
	 jQuery("#lossNo_4").val(3);
	 jQuery("#lossNo_5").val(1.2);
	 jQuery("#lossDescription_1").val(2);
	 jQuery("#lossDescription_2").val(9.12);
	 jQuery("#lossDescription_3").val(1.1);
	 jQuery("#lossDescription_4").val(3);
	 jQuery("#lossDescription_5").val(1.2);
	 jQuery("#cmbPlosTradeid_5").combobox('setValue','TDE00800001');
	 jQuery("#cmbPlosTradeid_3").combobox('setValue','TDE00800005');
	 jQuery("#cmbPlosTradeid_2").combobox('setValue','TDE00800004');
	 jQuery("#cmbPlosTradeid_4").combobox('setValue','TDE00800003');
	 jQuery("#cmbPlosTradeid_1").combobox('setValue','TDE00800002');
	  */
	 
}

function prodLossSPnr()
{			
	//dtebdmsProdaccepdate_onSelect(getServerDateTime());				
}

 function FromspinerFormatter(id, options, rowObject){
	return '<input  id="spnpcsfrom_'+options.rowId+'" name="spnpcsfrom_'+options.rowId+'_'+options.pos+'" value="'+rowObject[0]+' " class=""  onblur=calculateTime("'+options.rowId+'","'+options.pos+'");  style="width: 40px;" ></span>';
}
function TospinerFormatter(id, options, rowObject){
	return '<input  id="spnpcsTo_'+options.rowId+'" name="spnpcsTo_'+options.rowId+'_'+options.pos+'" value="'+rowObject[1]+' " onblur=calculateTime("'+options.rowId+'","'+options.pos+'"); class=""   style="width: 40px;" ></span>';
}
function whywhyBtn(id, options, rowObject){
	return '<input type="button" id="btnwhywhy_'+options.rowId+'_'+options.pos+'" onclick=showWhyWhy("'+options.rowId+'"); name="btnActionplanGrid_'+options.rowId+'_'+options.pos+'"    style="width:80px;  height:23px;"   class="easyui-button" value="Why Why"/>';
}
function kaizenBtn(id, options, rowObject){
	return '<input type="button" id="btnkaizen_'+options.rowId+'_'+options.pos+'" name="btnActionplanGrid_'+options.rowId+'_'+options.pos+'" onclick="showwkaizen('+options.rowId+')"  style="width:80px;  height:23px;"   class="easyui-button" value="Kaizen"/>';
}

function selectLoss(id) {	
	var val=jQuery('#lossNo_'+id).val();
	if (val=="1.1") {
		var refDocId="";
		url = "workReq_input.work?&bookingMode=msrInsert&refDocId="+refDocId+"&selMachineId=MCH0002061";		
		var msg = " Do You want Open Maintenance Notification  ";		
		if(confirm(msg)) 
			navigateToNextForm(url,"Maintenance Service Request",null,null);
	}
}

function showWhyWhy(id){
	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,1);
	//alert(plosKeyid);
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();;
	var date = jQuery('#dtePlosDate').datebox('getValue');
	var problem = jQuery("#pcslossGrid").jqGrid('getCell',id,11) ;
	var lossDesc = jQuery("#pcslossGrid").jqGrid('getCell',id,12);
	if (lossDesc.trim().length>1)
		problem += " (" + lossDesc + ")";
	var refDocDate = jQuery('#dtePlosDate').datebox('getValue');
	//navigateToNextForm('whywhy_input.why?&whywhyRefDocID='+plosKeyid,'Why Why Analysis');
	var forwardData = {"flid":flid,"date":date,"mode":"create"};//"";
	var persistentData ={"flid":flid,"date":date,"mode":"update"};//"";
     openWhyWhy("divWhyWhy",false,plosKeyid,"PCS",flid, refDocDate, problem, "create",forwardData,persistentData);
}

function showwkaizen(id){
	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,1);
	//alert(plosKeyid);
	//navigateToNextForm('kaizen_input.kaizen?&kznKeyid=&kznStatus=PENDING&mode=modify&closeOn=true&filterButton=false','Kaizen');
	
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();;
	var date = jQuery('#dtePlosDate').datebox('getValue');
	//var proposedID = jQuery("#pcslossGrid").jqGrid('getCell',id,20);
	//var proposedID= jQuery("#pcslossGrid").jqGrid('getCell',id,"KaizenRefNo.");
	var proposedID= jQuery("#pcslossGrid").jqGrid('getCell',id,"KaizenRefNo");//New
	var persistentData = {"flid":flid,"cellId":cellId,"date":date,"mode":"create"};//"flid":flid
	var forwardData = {"flid":flid,"cellId":cellId,"date":date,"mode":"create"};//id.forwardData;//"flid":flid
	var dataStr ="?&refDocNo="+plosKeyid+"&flid="+flid+"&refDocType=LOSS&Keyid="+proposedID+"&AccSingle=N";
	navigateToNextForm("KaizenBankSuggestion_input.kznbnk"+dataStr,"Kaizen Suggestion",forwardData,persistentData);
}
function applyNoplan(){
	jQuery("#lblProduct").removeClass("mandatory-lbl");
	jQuery("#lblProductmodel").removeClass("mandatory-lbl");
	jQuery("#lblManpower").removeClass("mandatory-lbl");
//	jQuery("#lblHour").removeClass("mandatory-lbl");
	jQuery("#cmbPprdProductmodel").combobox('clear');
	jQuery("#cmbPprdProductid").combobox('clear');
	jQuery("#cmbPprdManpower").combobox('clear');
	jQuery("#txtPprdCycletime").val('0');
	jQuery("#txtPprdProducedqty").val('0');
	jQuery("#txtPprdReworkqty").val('0');
	jQuery("#txtPprdRejectedqty").val('0');
	jQuery("#txtPprdRemarks").val('');
	jQuery("#txtUnreptimehourly").val('0');
	jQuery("#chkPprdShiftnoplan").attr('checked', true);	
	//jQuery("#txtUnreptimehourly").attr('checked',true);
	
}
function removeNoplan(){

	jQuery("#lblProduct").addClass("mandatory-lbl");
	jQuery("#lblProductmodel").addClass("mandatory-lbl");
	jQuery("#lblManpower").addClass("mandatory-lbl");
//	jQuery("#lblHour").addClass("mandatory-lbl");
	jQuery("#txtPprdCycletime").val('');
	jQuery("#txtPprdProducedqty").val('');
	jQuery("#txtPprdReworkqty").val('');
	jQuery("#txtPprdRejectedqty").val('');
}

function removeShiftNoplanSuccessCallback(result)
{
	clearAllFields();
	jQuery("#selPprdHour").val("");
	jQuery("#hdnPldetailsId").val("");
	jQuery("#hdnPlmMasterId").val("");
	jQuery("#hdnPprdKeyid").val("");
	alert(result.successData);
	//alert("No plan removed successfully.");
	viewProductionGrid();
}

function removeShiftNoplanSuccessCallbackError(result)
{
	alert("Shift No plan not removed");
}

function productionqtyValidation(){
	
	var prodqty='0';
	var reworkqty='0';
	var rejectqty='0';
	var total='0';
	var prevhour = Number("0");
	var hourno = Number(jQuery("#selPprdHour").val());
	prevhour = Number(hourno) -Number(1);
	prodqty = jQuery("#txtPprdProducedqty").val();
	var shiftWise=jQuery("#hdnisShiftWise").val();
	//alert(shiftWise);
	reworkqty = jQuery("#txtPprdReworkqty").val();
	rejectqty = jQuery("#txtPprdRejectedqty").val();
	//alert("p "+prodqty+", R"+reworkqty+", rej"+rejectqty);
	total = Number(reworkqty) + Number(rejectqty);
	var unreptimehourly = Number(0);
	var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');//	row get data
		for(var i=1; i<= productionRow.length; i++)
		{
			var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
			if(rowData.selPprdHour == prevhour)
				{
				unreptimehourly = Number(unreptimehourly) + Number(rowData.Unreptimehourly);
				}
		}
	if(shiftWise=='N'){
		if(Number(unreptimehourly) >= 1)
		{
			alert("Enter "+unreptimehourly+" minutes loss for HOUR "+prevhour);
			var title = "Loss Entry";
			jQuery("#tabLossEntry").tabs('select',title);
			setFocusOnField('txtLossHourfromtime');
			return false;
		}
		
			
	}
	if(Number(prodqty) < Number(reworkqty))
		{
			alert("Rework Quantity couldn't exceed produced Quantity.");
			return false;
		}
	else if(Number(prodqty) < Number(rejectqty))
		{
			alert("Reject Quantity couldn't exceed produced Quantity.");
			return false;
		}
	else if(Number(prodqty) < total)
		{
			alert("Production Can't be less than Breakup");
			return false;
		}
	else 
		return true;
	
}

function rejectionGridMandatoryValidations()
{
	 var rowId = jQuery('#hdnrowidbtn').val();
	 var gridValue = getGridSelectArray("pcsrejectionGrid");
	
		if(rowId.trim().length <= 0 )
		{
			alert("If want to save or update Rejection then atleast select a row in Rejction grid. ");
			return false;
		}
		if(rowId.trim().length >= 0)
		{
			var reason='';
			var phenomena='';
			var equipment='';
			
				var rejectRow = jQuery("#pcsrejectionGrid").jqGrid('getDataIDs');//	row get data
				//var rowData = jQuery("#pcsrejectionGrid").jqGrid('getRowData',rowId);
				for (i= 1; i <= rejectRow.length; i++) {
			/*		var rowid = rejectRow[i];
				*/
					if(jQuery('#jqg_pcsrejectionGrid_' +i).is(':checked') == true )
					{
						type = jQuery('#cmbPrbdType_pcsrejectionGrid_'+i).val();
	
						if(type == 'R')
							{
						
								phenomena = jQuery('#cmbPrbdPhenomena_pcsrejectionGrid_'+i).combobox("getValue") ;
								equipment = jQuery('#cmbPrbdEquipment_pcsrejectionGrid_'+i).combobox("getValue") ;

					
								if(phenomena.trim().length == 0 && equipment.trim().length == 0)
								{
									alert("Select Phenomena and Equipment for Row No.  "+i);
									return false;
								}
								if(phenomena.trim().length == 0 )
								{
									alert("Select Phenomena for Row No.  "+i);
									return false;
								}
								if(equipment.trim().length == 0)
								{
									alert("Enter Equipment for Row No.  "+i);
									return false;
								}
								
						}
						if(type == 'W')
							{
								reason = jQuery('#txtPrbdRejectionreason_pcsrejectionGrid_'+i).val();
								if(reason == '' || reason.trim().length == 0 )
								{
									alert("Enter Rework Reason for Row No.  "+i);
									return false;
								}
							}
					}
				}
		}
}

function fillHourlyCalculations()
{
	var noofhours="";
	var totalavailtime = "";
	var hourlyavailtime = "";
	var selectHourValue = "";
	var addHourValue = "";
//	var incavailtime  = false; 
	var hourlynoplan =jQuery("#chkPprdHourlynoplan").is(':checked');
	var shiftnoplan = jQuery("#chkPprdShiftnoplan").is(':checked');
	//alert("hour"+hourlynoplan+"  "+"shift"+shiftnoplan);
		if(hourlynoplan == false && shiftnoplan == false)
		{
			totalavailtime = Number(jQuery("#txtAvailtime").val());
			selectHourValue=jQuery('#selPprdHour').val();
		 	hourlyavailtime=Number(totalavailtime)%Number(60);	
	    	noofhours=Number(totalavailtime)/Number(60);	
	    	noofhours=Math.ceil(hour); 
	    	addHourValue =Number(noofhours) + Number(1) ;    	
	     //   alert(selectHourValue +"     1236789");
			if(selectHourValue < noofhours )//&& selectHourValue != 'H+'
				{
					readOnlyFields("txtAvailtimehourly");
				//	disableField("frmPcsLoss", "txtAvailtimehourly");
					jQuery("#txtAvailtimehourly").val("60");
					jQuery("#txtUnreptimehourly").val("60");
				}
			else if(selectHourValue == noofhours )//&& selectHourValue != 'H+'
				{
					if(hourlyavailtime == "0")
					{
						readOnlyFields("txtAvailtimehourly");
						jQuery("#txtAvailtimehourly").val("60");
						jQuery("#txtUnreptimehourly").val("60");
					}
					else
						{
							readOnlyFields("txtAvailtimehourly");
							jQuery("#txtAvailtimehourly").val(hourlyavailtime);
							jQuery("#txtUnreptimehourly").val(hourlyavailtime);
						}
				}
			else
				{
					jQuery("#hdnAvailTime").val(totalavailtime);
					enableFields("txtAvailtimehourly");
					jQuery("#txtAvailtimehourly").val("");
					jQuery("#txtUnreptimehourly").val("");
				}
	  	}
		var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');//	row get data
		var gridAddHour = false;
		for(var i=1; i<= productionRow.length; i++)
			{
				var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);
				//	gridAddHour = rowData.selPprdHour;
				//	alert("1"+gridAddHour);
					if(rowData.selPprdHour == addHourValue)
						{
							gridAddHour =  true;
						}
					
			//	alert("gridAddHour"+gridAddHour);
			}
	
		//alert(1);
			if(selectHourValue == addHourValue && gridAddHour == false)
	   		{	
				jQuery("#hdnincAvailtime").val("Y");
				totalavailtime = jQuery("#txtAvailtime").val();
				totalavailtime=Number(totalavailtime); // + Number(60)
	  			jQuery("#txtAvailtime").val(totalavailtime);
	 			jQuery("#txtUnreptime").val(totalavailtime);
	  		
	   		}
			else if(jQuery("#hdnincAvailtime").val() == "Y" && gridAddHour == false )
	   		{	
				totalavailtime = jQuery("#txtAvailtime").val();
	 	   		totalavailtime=Number(totalavailtime);   // - Number(60)
	 	   		jQuery("#txtAvailtime").val(totalavailtime);
	 	  	 	jQuery("#txtUnreptime").val(totalavailtime);
				jQuery("#hdnincAvailtime").val("N");
	   		}
		//alert(2);
			getGridRowid(selectHourValue,'','');

			showAllTime("Production");
			
}

function gridTimeHourlyFilling()
{

	var hourValue = jQuery("#selPprdHour").val();
	var unreptimeHourlyGrid =Number("0"); // Number(jQuery("txtUnreptimehourly").val());
	var losstimeHourlyGrid = Number("0"); //Number(jQuery("#txtLosstimehourly").val());
	var productiontimeHourlyGrid = Number("0");
	var noplanDurationGrid = Number("0");
	var productionRow = jQuery("#").jqGrid('getDataIDs');//	row get data
	

	for(var i=1; i<= productionRow.length; i++)
	{
		var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);

		//		alert("prodtime"+prodtime +"  "+tempprodtime);
			if(rowData.selPprdHour == hourValue)
				{
					unreptimeHourlyGrid = Number(unreptimeHourlyGrid) + Number(rowData.Unreptimehourly);
					losstimeHourlyGrid = Number(losstimeHourlyGrid) +Number(rowData.Losstimehourly);
					productiontimeHourlyGrid = Number(productiontimeHourlyGrid) + Number(rowData.Prodtimehourly);
					noplanDurationGrid	= Number(noplanDurationGrid) + Number(rowData.txtPprdNoplanduration);						
				}
			
		}
		unreptimeHourlyGrid = Number(unreptimeHourlyGrid).toFixed(2);
		losstimeHourlyGrid = Number(losstimeHourlyGrid).toFixed(2);
		productiontimeHourlyGrid = Number(productiontimeHourlyGrid).toFixed(2);
		jQuery("#hdnUnrepTimeHourly").val(unreptimeHourlyGrid);
}


function fillAvailtimehourly()
{
	var cell = jQuery("#cmbPlosCellid").combobox('getValue');
	var hour = jQuery('#selPprdHour').val();
	var product = jQuery("#cmbPprdProductid").combobox('getValue');
	var manpower = jQuery("#cmbPprdManpower").combobox('getValue');
	var availtimehourly=getFieldValue("txtAvailtimehourly");
//	alert("1"+cell+" 2"+hour+" 3 "+product+" 4 "+manpower);
	if( cell == "undefined" || cell =="" )
		{
			alert("select cell");
		}
	if( hour == "undefined" || hour == " " || hour =="" && cell != "CEL0000089")
	{
		alert("select hour");
	}
	
	if( product == "undefined" || product == "" || product ==" ")
	{
		alert("select product");
	}
	if( manpower == "undefined" || manpower == "" || manpower == " ")
	{
		alert("select manpower");
	}
	if(availtimehourly == "" || availtimehourly == " "){
			alert("Enter 'Avail. Time' for additional hour");
			setFocusOnField("txtAvailtimehourly");
		}

		
	//	}	
	
	//	jQuery("#txtUnreptimehourly").val(unreptimeHourly);
	//	jQuery("#txtLosstimehourly").val(losstimeHourly);
//	alert("5  "+jQuery("#txtUnreptimehourly").val()+"   "+jQuery("#txtLosstimehourly").val());
/*	var hourValue = jQuery("#selPprdHour").val();
	var unreptimeHourlyGrid =Number("0"); // Number(jQuery("txtUnreptimehourly").val());
	var losstimeHourlyGrid = Number("0"); //Number(jQuery("#txtLosstimehourly").val());
	var productiontimeHourlyGrid = Number("0");
	var noplanDurationGrid = Number("0");
	var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');//	row get data
	
	for(var i=1; i<= productionRow.length; i++)
	{
		var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',i);

		//		alert("prodtime"+prodtime +"  "+tempprodtime);
			if(rowData.selPprdHour == hourValue)
				{
					unreptimeHourlyGrid = Number(unreptimeHourlyGrid) + Number(rowData.Unreptimehourly);
					losstimeHourlyGrid = Number(losstimeHourlyGrid) +Number(rowData.Losstimehourly);
					productiontimeHourlyGrid = Number(productiontimeHourlyGrid) + Number(rowData.Prodtimehourly);
					noplanDurationGrid	= Number(noplanDurationGrid) + Number(rowData.txtPprdNoplanduration);						
				}
			
		}
		unreptimeHourlyGrid = Number(unreptimeHourlyGrid).toFixed(2);
		losstimeHourlyGrid = Number(losstimeHourlyGrid).toFixed(2);
		productiontimeHourlyGrid = Number(productiontimeHourlyGrid).toFixed(2);
		jQuery("#hdnUnrepTimeHourly").val(productiontimeHourlyGrid);
	*/
	

//	gridTimeHourlyFilling();
		
	
	var producedqty = jQuery("#txtPprdProducedqty").val();
	var availtime = jQuery("#txtAvailtime").val();
	var cycletime = jQuery("#txtPprdCycletime").val();
	var unreportedtime = jQuery("#txtUnreptime").val();
	var productiontime = jQuery("#txtProdtime").val();
	var losstime = jQuery("#txtLosstime").val();
	var noplanduration = jQuery("#txtPprdNoplanduration").val();
	var productiontimeHourlyGrid =Number("0");
	var unreportedtimehourly = Number(jQuery("#txtUnreptimehourly").val());
	var losstimehourly = Number(jQuery("#txtLosstimehourly").val());
	var productiontimehourly =Number(jQuery("#txtProdtimehourly").val());
	var availtimehourly = Number(jQuery("#txtAvailtimehourly").val());
//	alert(jQuery("#hdnUnrepTimeHourly").val());
	if(jQuery("#hdnUnrepTimeHourly").val() != "" &&  jQuery("#hdnUnrepTimeHourly").val() != "0.00")
	{
				
	//	productiontimeHourlyGrid = Number(productiontimeHourlyGrid) + Number(jQuery("#hdnUnrepTimeHourly").val());
		availtimehourly = Number(jQuery("#hdnUnrepTimeHourly").val());
	}


		  productiontimehourly =  (Number(producedqty) * Number(cycletime));
			productiontimehourly = productiontimehourly.toFixed(2);
		//	alert("1 "+availtimehourly+"  2 "+productiontimehourly);
			
	//	availtimehourly = Number(availtimehourly) - Number(unreptimeHourlyGrid);
	
		unreportedtimehourly = Number(availtimehourly) - Number(losstimehourly)- Number(productiontimehourly)  - Number(noplanduration) ;  //  - Number(productiontimeHourlyGrid) 
		unreportedtimehourly = Number(unreportedtimehourly).toFixed(2);

		//alert('unreportedtimehourly'+unreportedtimehourly);
		jQuery("#txtAvailtimehourly").val(availtimehourly);
		jQuery("#txtProdtimehourly").val(productiontimehourly);
		jQuery("#txtUnreptimehourly").val(unreportedtimehourly);
		
		availtimehourly = Number(availtimehourly) - Number(noplanduration);
		if( Number(availtimehourly) < Number(productiontimehourly))
		{
			alert("Production time can't exceed unresponded time");
			return false;
		}
		if(unreportedtimehourly.startsWith("-"))
		{
			alert("Unresponded time can't be less than 'Zero'.");
			return false;
		}	
//	productiontime = Number(productiontime) + Number(productiontimehourly);
//	unreportedtime = Number(availtime) -Number(losstime)- Number(unreportedtime);
	
	//	jQuery("#txtProdtime").val(productiontime);
	//	jQuery("#txtUnreptime").val(unreportedtime);
	
}

function noplanAvailtimehourly()
{

	var cell = jQuery("#cmbPlosCellid").combobox('getValue');
	var hour = jQuery('#selPprdHour').val();
	//	alert("1"+cell+" 2"+hour+" 3 "+product+" 4 "+manpower);
	if( cell == "undefined" || cell =="" )
		{
			alert("select cell");
		}
	if( hour == "undefined" || hour == " " || hour =="" && cell !="CEL0000089")
	{
		alert("Select hour.");
	}
	
	var hourlynoplan =jQuery("#chkPprdHourlynoplan").is(':checked');
	if(hourlynoplan == false )
		{
			alert("Tick Noplan Checkbox");
		}
	var noplanduration = jQuery("#txtPprdNoplanduration").val();
	var availtimehourly =  jQuery("#txtAvailtimehourly").val();
	var unreportedtimehourly = jQuery("#txtUnreptimehourly").val();
	var productiontimehourly = jQuery("#txtProdtimehourly").val();
	var losstimehourly = jQuery("#txtLosstimehourly").val();
	
	unreportedtimehourly = Number(availtimehourly) - Number(losstimehourly)- Number(productiontimehourly) - Number(noplanduration);
	unreportedtimehourly = Number(unreportedtimehourly).toFixed(2);
	
	 if(Number(availtimehourly) < Number(noplanduration))
		{
			alert("No plan can't exceed unresponded time");
			jQuery("#txtPprdNoplanduration").val('');
			return false;
		}
	
	 jQuery("#txtUnreptimehourly").val(unreportedtimehourly);
		
}


function lossFromTimeMinutesValidations()
{
	var fromtime = "00"+":"+"00";
	var fromlosshour = Number(00); // jQuery("#txtLossHourfromtime").val();
	var fromlossminutes ="00";// jQuery("#txtLossMinutesfromtime").val();

	fromlosshour = Number(jQuery("#txtLossHourfromtime").val());
	fromlossminutes =Number(jQuery("#txtLossMinutesfromtime").val());
		
	if(fromlosshour == 0 )
		fromlosshour ="00";
	if(fromlosshour < 10 && fromlosshour != 0)
		fromlosshour ="0"+fromlosshour;
	if(Number(fromlosshour) > 23)
		{
			alert("From Hour can't exceed '23'");
			Number(jQuery("#txtLossHourfromtime").val(23));
			fromlosshour = jQuery("#txtLossHourfromtime").val();
			//return false;
		}
	if(fromlossminutes == 0)
		fromlossminutes ="00";
	if(fromlossminutes < 10 && fromlossminutes != 0 )
		fromlossminutes = "0"+fromlossminutes;
	if(fromlossminutes > 59 )
		{
			alert("From Minutes Can't Exceed '59'");
			Number(jQuery("#txtLossMinutesfromtime").val(59));
			fromlossminutes = jQuery("#txtLossMinutesfromtime").val();
			//return false;
		}
	
//		alert("from loss hour"+fromlosshour+"frommin "+fromlossminutes+" tohour "+tolosshour+" tolossminutes "+tolossminutes);
		fromtime = fromlosshour + ":" + fromlossminutes;
	
//		alert(" "+ fromtime);
		setFieldValue('spnPlosFromtime',fromtime);
		compareDates('dteShiftstartdate','spnShiftstarttime','dtePlosFromdate','spnPlosFromtime','Start Time should not be lesser than Shift start time','txtLossHourfromtime','txtLossMinutesfromtime');
		compareLossTimewithGridLossTime();
		
		
}
function compareLossTimewithGridLossTime(){

	
/*	var actuallossStartDate = jQuery("#dtePlosFromdate").datebox('getValue');
	var actaullossStartHour =jQuery("#txtLossHourfromtime").val();
	var actuallossStartMinutes = jQuery("#txtLossMinutesfromtime").val();
	var actuallossStartTime = actuallossStartDate+actaullossStartHour+":"+actuallossStartMinutes;
	
	*/
	var actuallossStartTime = jQuery("#dtePlosFromdate").datebox('getValue') + jQuery("#spnPlosFromtime").spinner('getValue');
	var actuallossEndTime = jQuery("#dtePlosTodate").datebox('getValue') + jQuery("#spnPlosTotime").spinner('getValue');
	/*
	var actuallossEndDate = jQuery("#dtePlosTodate").datebox('getValue');
	var actuallossEndHour = jQuery("#txtLossHourtotime").val();
	var actuallossEndMinutes = jQuery("#txtLossMinutestotime").val();
	var actuallossEndTime = actuallossEndDate+actuallossEndHour +":"+actuallossEndMinutes;
	*/
//	alert( actuallossStartTime+"   "+actuallossEndTime);
	jQuery("#txtPlosLosstime").val("");
	var lossgridRow = jQuery("#pcslossGrid").jqGrid('getDataIDs');
//	alert(lossgridRow.length);
	for (var i = 1; i <= lossgridRow.length; i++) {
	
		var rowData = jQuery("#pcslossGrid").jqGrid('getRowData',i);
		var gridLossStartDate = rowData.FromDate;
		var gridLossStartTime =  rowData.FromTime;
		var gridLossEndDate = rowData.ToDate;
		var gridLossEndTime = rowData.ToTime;

		var gridLossStart = gridLossStartDate+ gridLossStartTime;
		var gridLossEnd = gridLossEndDate + gridLossEndTime;
	//	alert(compareDateTime(gridLossEnd,actuallossStartTime) +"  "+compareDateTime(actuallossStartTime,gridLossStart));
	//	alert(compareDateTime(gridLossStart,actuallossStartTime) +"  "+compareDateTime(gridLossStart,actuallossEndTime));
		
		if(compareDateTime(gridLossStart,actuallossStartTime) < 0 && compareDateTime(gridLossStart,actuallossEndTime) > 0)
			{
				alert("Loss Entry exist for this time span ");
				return false;	
			}
	//	alert(compareDateTime(gridLossEnd,actuallossStartTime)+" "+ compareDateTime(actuallossStartTime,gridLossStart));
		else if( compareDateTime(gridLossEnd,actuallossStartTime) < 0 && compareDateTime(actuallossStartTime,gridLossStart) < 0 )
			{
				alert("Loss Entry exist for this time span ");
				return false;
			}
	//	alert(compareDateTime(actuallossEndTime,gridLossStart)+" "+ compareDateTime(actuallossEndTime,gridLossEnd));
		else if( compareDateTime(actuallossEndTime,gridLossStart) < 0 && compareDateTime(actuallossEndTime,gridLossEnd) > 0 )
			{
				alert("Loss Entry exist for this time span ");	
				return false;
			}
		
		}
	actualTimeCalculation();
	getLossStartEndHour();

}


function lossToTimeMinutesValidations(){

	var totime = "00"+":"+"00";
	var tolosshour ="00"; //jQuery("#txtLossHourtotime").val();
	var tolossminutes ="00";// jQuery("#txtLossMinutesfromtime").val();
	tolosshour =Number(jQuery("#txtLossHourtotime").val());
	tolossminutes =Number(jQuery("#txtLossMinutestotime").val());

	if(tolosshour == 0)
		tolosshour = "00";
	if(tolosshour < 10 && tolosshour != 0)
		tolosshour ="0"+tolosshour;
	if(Number(tolosshour) > 23)
		{
			alert("To Hour can't exceed '23'");
			Number(jQuery("#txtLossHourtotime").val(23));
			tolosshour = Number(jQuery("#txtLossHourtotime").val()); 
		//	return false;
		}
	
	if(tolossminutes == 0)
		tolossminutes = "00";

	if(tolossminutes < 10 && tolossminutes != 0 )
		tolossminutes = "0"+tolossminutes;
	if(tolossminutes > 59 )
		{
			Number(jQuery("#txtLossMinutestotime").val(59));
			tolossminutes =Number(jQuery("#txtLossMinutestotime").val());
			alert("To Minutes Can't Exceed '59'");
			
		//	return false;
		}	
	totime = tolosshour + ":" + tolossminutes;
	setFieldValue('spnPlosTotime',totime);
	compareDates('dtePlosFromdate','spnPlosFromtime','dtePlosTodate','spnPlosTotime','End Time should not be less than start time','txtLossHourtotime','txtLossMinutestotime');
//	compareDates('dteShiftstartdate','spnShiftstarttime','dtePlosFromdate','spnPlosFromtime','Start Time should not be lesser than Shift start time');
	compareLossTimewithGridLossTime();
	//actualTimeCalculation();
//	getLossStartEndHour();
}
function getLossStartEndHour()
{

	var shiftFromdatetime = jQuery("#dteShiftstartdate").datebox('getValue') + jQuery("#spnShiftstarttime").spinner('getValue');
	var lossFromdatetime = jQuery("#dtePlosFromdate").datebox('getValue') + jQuery("#spnPlosFromtime").spinner('getValue');
	var lossTodatetime = jQuery("#dtePlosTodate").datebox('getValue') + jQuery("#spnPlosTotime").spinner('getValue');
	//	alert("lossFrom "+shiftFromdatetime+" losstotime "+lossFromdatetime);
	actualStartMins = timeDifference(shiftFromdatetime,lossFromdatetime);
	startTotalminutesdifference = 	actualStartMins.minutes;
//	alert("t"+actualMins+"   "+totalminutesdifference);
	var startHour = '0';
	startHour = Number(startTotalminutesdifference)/Number(60);
	startHour = Math.ceil(startHour);
//	hour = Number(hour)+Number(1);
 
	var actualEndMins = timeDifference(shiftFromdatetime,lossTodatetime);
	endTotalminutesdifference = actualEndMins.minutes;
	var endHour = '0';
	endHour = Number(endTotalminutesdifference)/Number(60);
	endHour = Math.ceil(endHour);
//	alert(startHour+"  "+endHour);
	lossMinutesProductionGridValidation(startHour,endHour);
}

function lossMinutesProductionGridValidation(startHour,endHour)
{
	
	var gridProductiontime =0;
	var gridLosstime = 0;
	var gridUnrespondedtime = 0;
	var gridHour = 0;
	var actualLossMinutes = Number(jQuery("#txtPlosLosstime").val());
	var totalShiftAvailtime = Number(jQuery("#txtAvailtime").val());
	var shiftUnrespondedtime = Number(jQuery("#txtUnreptime").val());
//	alert("act"+actualLossMinutes);
	var starthr = Number(startHour);
/*	if(startHour == 0)
		startHour = "1";*/
	
//	var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');
//	alert(gridProductiontime+"  "+gridUnrespondedtime+" "+gridLosstime);
	var	hourlyavailtime=Number(totalShiftAvailtime)%Number(60);	
	var	noofhours=Number(totalShiftAvailtime)/Number(60);	
		noofhours=Math.ceil(hour);
		var hourRowId ="";	      	
		var productionRow = jQuery("#pcsproductionGrid").jqGrid('getDataIDs');		
	   	for(var i = startHour; i <= endHour; i++) {
		//	alert("i "+i);
			for(var j=1; j<=productionRow.length; j++)
				{
			//		alert("in inner for loop "+j);
					var rowhour = jQuery("#pcsproductionGrid").jqGrid('getRowData',j);
					if(i == rowhour.selPprdHour)
					{
					
						gridHour = rowhour.selPprdHour;
						hourRowId = j;
					}
				}
			var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',hourRowId);
	//		gridHour = rowData.selPprdHour;
	//		alert(gridHour+"  "+i +"   rowData.selPprdHour  "+rowData.selPprdHour );
			if(gridHour == i)
			{
	//			alert("In if");
					if(rowData.Prodtimehourly == undefined || rowData.Prodtimehourly == "undefined")
					{
//						alert("In if one ");
						gridProductiontime = Number(gridProductiontime);
					}
					else
						{
	//					alert("In else one ");
						gridProductiontime = Number(gridProductiontime) + Number(rowData.Prodtimehourly);
						}
					if(rowData.Losstimehourly == undefined || rowData.Losstimehourly == "undefined")
					{	
		//				alert("In if two ");
						gridLosstime = Number(gridLosstime) ;
					}
					else
						{
			//				alert("In else two ");
							gridLosstime = Number(gridLosstime) + rowData.Losstimehourly ;
						}
//				gridLosstime = Number(gridLosstime) + Number(0);
			

		//		var gridUnrespondedtime = rowData.Unreptimehourly;
//		alert("rowData.Unreptimehourly  "+rowData.Unreptimehourly);
					if(rowData.Unreptimehourly == undefined || rowData.Unreptimehourly == "undefined")
					{
//						alert("In if three ");
						gridUnrespondedtime = Number(AgridUnrespondedtime);
					}
					else
					gridUnrespondedtime = Number(gridUnrespondedtime) + Number(rowData.Unreptimehourly);
					
			//		alert(rowData.Prodtimehourly+"  "+gridProductiontime+"   "+gridUnrespondedtime+" "+gridLosstime);
			}
			else
			{
	//			alert("elseif");
				if(i == noofhours)
				{
					if(hourlyavailtime == "0")
					{
						gridUnrespondedtime = Number(gridUnrespondedtime) + Number(60);
					}
					else
						{
							gridUnrespondedtime = Number(gridUnrespondedtime) + Number(hourlyavailtime);
						}
				}
				else
				{
					gridUnrespondedtime = Number(gridUnrespondedtime) + Number(60);
				}
			}
			starthr = Number(starthr)+Number(1);
	   	}
		/*    After for loop */
	//	alert(gridUnrespondedtime +"  "+shiftUnrespondedtime)
	   	
		if(gridUnrespondedtime < actualLossMinutes)
		{
			alert("Loss time Can't exceed Selected time span Unresponded time. Unresponded time for this span is "+gridUnrespondedtime+". But you are entering loss for "+actualLossMinutes+".");
			return false;
			
		}
}

function shiftStartEndTimeSuccess(result) {
	//alert(result.endTime);.
	var availtime=result.totalShifttime;
	var hours='';
	var i='';
	var shiftStarttime = result.startTime;
	var shiftEndtime = result.endTime;
	var shiftStartHour = shiftStarttime.substring(0,2);
	var shiftStartMinutes = shiftStarttime.substring(3,5);
	var shiftEndHour = shiftEndtime.substring(0,2);
	var shiftEndMinutes = shiftEndtime.substring(3,5);
	var todate = jQuery('#dtePlosDate').datebox('getValue');
	//alert(shiftStarttime+" End "+shiftEndtime+" .. "+shiftStartHour+" "+shiftStartMinutes);
	var dateTime = "01-Jan-2014 06:20:00";
	
	var startDatetime=result.startDate;
	var endDatetime = result.endDate;
	var endDate = new Date();
	
//	alert("endDate"+endDate);
	var startDate = new Date(todate.replace(/-/g, '/'));
	endDate.setDate(startDate.getDate()+1);
//	alert("endDate"+endDate);
//	alert("endDate"+endDate +"   "+startDate);
	var date = endDate.getDate();
	//var month = getMonthStringFromInt(endDate.getMonth());
	var month = getMonthStringFromInt(startDate.getMonth());
	var year = endDate.getFullYear();
	endDate = date+"-"+month+"-"+year ;

		
	if(result.shiftCode != "1" && result.shiftCode == result.shiftOrder)
		{
		
			if(startDatetime < dateTime && endDatetime < dateTime)
			{
			//	In Shift 3rd
				setFieldValue('dteShiftstartdate',endDate);
				setFieldValue('dteShiftenddate',endDate);
				setFieldValue('dtePlosFromdate',endDate);//endDate);
				setFieldValue('dtePlosTodate',endDate);
			
				readOnlyFields('dtePlosFromdate');
				readOnlyFields('dtePlosTodate');
			}
			else if(endDatetime > "01-Jan-2014 23:59:59")
			{
//				In Shift 2nd

				setFieldValue('dteShiftstartdate',todate);
				setFieldValue('dteShiftenddate',endDate);
				setFieldValue('dtePlosFromdate',todate);
				setFieldValue('dtePlosTodate',todate);
				enableFields("dtePlosTodate");
			}
		}
	else
		{
		//  In shift 1st and General
			
			setFieldValue('dteShiftstartdate',todate);
			setFieldValue('dteShiftenddate',todate);
			setFieldValue('dtePlosFromdate',todate);
			setFieldValue('dtePlosTodate',todate);
			readOnlyFields('dtePlosFromdate');
			readOnlyFields('dtePlosTodate');
		}

	setFieldValue('spnPlosFromtime',result.startTime);
	setFieldValue('spnPlosTotime',result.endTime);
	setFieldValue('spnShiftstarttime',result.startTime);
	setFieldValue('spnShiftendtime',result.endTime);
	setFieldValue('txtLossHourfromtime',shiftStartHour);
	setFieldValue('txtLossMinutesfromtime',shiftStartMinutes);
	setFieldValue('txtLossHourtotime',shiftEndHour);
	setFieldValue('txtLossMinutestotime',shiftEndMinutes);
	

	var fromDate = jQuery("#dteShiftstartdate").datebox('getValue') + jQuery("#spnShiftstarttime").spinner('getValue');

	var currentDate = getServerDateTime();
//	alert("from date  "+fromDate+"  curretndate "+currentDate);
	if(convertStringToDate(fromDate) > currentDate)
	{
	
		alert("Can't enter for future Shift..!!");

		jQuery("#selPprdHour").val('');
		jQuery("#cmbSelPrdHourL").combobox('clear');
		/*/jQuery("#cmbPlosShiftid").combobox('clear');
		jQuery("#txtAvailtime").val('');
		jQuery("#txtUnreptime").val('');
		jQuery("#txtProdtime").val('');
		jQuery("#txtLosstime").val('');
		alert('fromdate > currentdate');
		jQuery("#selPprdHour").val('');
		jQuery("#txtAvailtimehourly").val('');
		jQuery("#txtUnreptimehourly").val('');
		jQuery("#txtProdtimehourly").val('');
		jQuery("#txtLosstimehourly").val('');
		*/
		
		return false;
	}
	
	
	readOnlyFields("dteShiftstartdate");
	readOnlyFields("dteShiftenddate");
	readOnlyFields('spnShiftstarttime');
	readOnlyFields('spnShiftendtime');
	
	jQuery("#txtAvailtime").val(availtime);
	jQuery("#txtUnreptime").val(availtime);;;;
	jQuery("#txtProdtime").val("0");
	jQuery("#txtLosstime").val("0");
	jQuery("#hdnSftDuration").val(availtime);
	
	availtime = Number(availtime);
	hour=availtime/Number(60);
	hour=Math.ceil(hour);

	var selHour=jQuery('#selPprdHour').val();
	if (selHour=='' || selHour==' ' || selHour==undefined || selHour==selHour) {
		jQuery('#selPprdHour')
		    .find('option')
		    .remove()
		    .end()
		    .append('<option value=" "> </option>')
		    .val(' ');
	
		for (i = 1;  i <=hour ; i++) 
			{
					jQuery('#selPprdHour').append(jQuery('<option/>', { 
				        value: i,
				        text : i 
				    }));
			}
	}
	jQuery('#selPprdHour').append(jQuery('<option/>', { 
        value: i,
        text : 'H+' 
    }));
	
}

/* function lossCmb(id, options, rowObject){
 return "<select id='lossDescription_"+options.rowId+"' style='width :116px;height:20px;'onchange=lossfunction(this.value,"+options.rowId+")  ><option> - </option><option value='2'>Set Up and Adj.	</option><option value='9.12'>JH Tag Removal	</option><option 1 value='1.1'>Machine Failure	</option><option 3 value='3'>Tool Change Loss</option><option value='1.2'>Process Failure	</option><option value='7.1'>Line Rejected Qty</option></select>";
}
function downTimeText(id, options, rowObject){
	return '<input type="text" id="txtdownTime_'+options.rowId+'"   name="txtdownTime_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[7]+' "  style="width:109px;  height:23px;text-transform:uppercase;text-align: center;"   class="easyui-textbox" />';
}
function proImpactText(id, options, rowObject){
	return "<select id='lossNo_"+options.rowId+"' onChange=selectLoss("+options.rowId+") style='width :72px;height:20px;'  onchange=tstfunction(this.value,"+options.rowId+")  > <option> - </option><option value='2'>2	</option> <option value='9.12'>9.12</option> <option 1 value='1.1'>1.1</option> <option 3 value='3'>3</option> <option value='1.2'>1.2</option> <option value='7.1'>7.1</option> </select>";
}
function lossReasonText(id, options, rowObject){
	return '<input type="text" id="txtdownTime_'+options.rowId+'"   name="txtdownTime_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[5]+' "  style="width:101px;  height:23px;text-transform:uppercase;"   class="easyui-textbox" />';
}
function TradeFormatter(id, options, rowObject){
	return '<input type="text" id="cmbPlosTradeid_'+options.rowId+'"   name="cmbPlosTradeid_'+options.rowId+'_'+options.pos+'"  value=" "  style="width:80px;  height:23px;text-transform:uppercase;"   class="easyui-textbox" />';
}
 */

 function getGridProducedqty(rowid,pos){

//	 alert("1 "+rowid+"   "+pos);
	 
}

function fillGridAvailtimehourly(rowid,pos)
{
//	alert(" 2 "+rowid+"   "+pos);

	var rowData = jQuery("#pcsproductionGrid").jqGrid('getRowData',rowid);

//	setFieldValue("")
	setFieldValue('txtAvailtimehourly',rowData.Availtimehourly);
	setFieldValue('txtProdtimehourly',rowData.Prodtimehourly);
	setFieldValue('txtLosstimehourly',rowData.Losstimehourly);
	setFieldValue('txtUnreptimehourly',rowData.Unreptimehourly);
	fillAvailtimehourly();
	
}
 
 function PCS_formatterTxtProducedqty(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="text" id="txtProducedqty_'+options.rowId+'"   name="txtProducedqty_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[9]+' "   oninput=fillGridAvailtimehourly("'+options.rowId+'","'+options.pos+'") style="width:40px;  height:23px;text-transform:uppercase; "   class="easyui-textbox" />';          	           	                      
	}

 function PCS_formatterTxtReworkqty(id, options, rowObject){

		var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="text" id="txtReworkqty_'+options.rowId+'"   name="txtReworkqty_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[10]+' "  onBlur=getGridProducedqty("'+options.rowId+'","'+options.pos+'"); style="width:40px;  height:23px;text-transform:uppercase;"   class="easyui-textbox" />';          	           	                      
	}
 function PCS_formatterTxtRejectqty(id, options, rowObject){

		var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="text" id="txtRejectqty_'+options.rowId+'"   name="txtRejectqty_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[11]+' "  style="width:40px;  height:23px;text-transform:uppercase;"   class="easyui-textbox" />';          	           	                      

	}

function addHourTimevalidtions(){

	var availtimehourly = Number("0");
	availtimehourly =Number(jQuery("#txtAvailtimehourly").val());
	jQuery("#txtUnreptimehourly").val(availtimehourly);	
	var avltime = Number(jQuery("#hdnAvailTime").val());
	var rnt = Number(getFieldValue("txtPprdCycletime"));
	var plannedQty=Number(jQuery("#txtPlannedqty").val());
	var hourno=getFieldValue("selPprdHour");
	
//	var unreptime = Number(jQuery("#txtUnreptime").val());
	//avltime = Number(avltime) + Number(availtimehourly);
	if(rnt==0)
		setFieldValue('txtPlannedqty',0);
	else
		plannedQty=  availtimehourly/rnt;
//	avltime = Number(avltime) + Number(availtimehourly);
	plannedQty=Number(plannedQty).toFixed(2);
	if(hourno==9)
	jQuery("#txtAvailtime").val(480+availtimehourly);
	//jQuery("#txtUnreptime").val(avltime);
	setFieldValue('txtPlannedqty',plannedQty);

	}

///kiran 
 jQuery(function() {



 jQuery("#cmbPprdArea").change(function () {
          //  var selectedText = $(this).find("option:selected").text();
          //  var selectedValue = $(this).val();
            alert("Selected Text: ");
        });
 });

jQuery('#cmbPprdArea').combobox({  	
		   
	   	onSelect:function(recordid)
			{
			alert('prdId OnSelect'+ recordid);
	   		//jQuery('#cmbPlosToolId').combobox('clear');
	   		var prdId=  jQuery('#cmbPprdProductid').combobox('getValue');
	   		var prdArea=  jQuery('#cmbPprdArea').combobox('getValue');
	   		
	   		alert(prdId +" ..... "+prdArea);	
			processAjaxCalls('txt_btQnty.balpcs',prdId,'','');
			
	   		//reloadCombo("frmPcsLoss","cmbPlosToolId","combo_ToolId.pcs?prdId="+prdId);
			//reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.pcs?"+"cellId="+cellId+"&entryDate="+entryDate);
			}

        });

jQuery('#cmbPlosMachineid').combobox({  	
		   
	   	onSelect:function(recordid)
			{
	   		jQuery('#cmbPlosToolId').combobox('clear');
	   		var machine=  jQuery('#cmbPlosMachineid').combobox('getValue');	
	   		reloadCombo("frmPcsLoss","cmbPlosToolId","combo_ToolId.balpcs?machineId="+machine);
			//reloadCombo("frmPcsLoss","cmbPprdProductid","combo_product.pcs?"+"cellId="+cellId+"&entryDate="+entryDate);
			}

        });
///
jQuery('#btnToolAlert').click(function(){
	var flId=jQuery("#frmPcsLoss input[id='flid']").val();
	LoadPopUp("toolAlert","toolAlertsTommorow_input.alerts?button=N&flId="+flId,"","850px","350px","330px","250px","","TOOL CHANGE  PLAN","","",true);
	
	alert(5 +flId);
	
});




/*

      var machine=  jQuery('#cmbPlosMachineid').combobox('getValue');
                  alert("....mmmmm       " +machine   +"     ,,,,,,,,       ");
                            if(machine==" " || machine==null){
	//machineId='MCH0000001';
	                       alert("....mmmmm" +machine   +"     ,,,,,,,,       ");
				
	fillComboBox("frmPcsLoss","cmbPlosToolId","combo_ToolId.pcs?machineId="+machine);
	//reloadCombo("frmPcsLoss","cmbPlosToolId","combo_ToolId.pcs?machineId="+machineId);
	}
      else{
	jQuery("#cmbPlosToolId").combobox('clear');
		alert(machine+" asdfrt ");
	reloadCombo("frmPcsLoss","cmbPlosToolId","combo_ToolId.pcs?machineId="+machine);

}


 
/* function ProductText(id,options,rowObject){
	return '<input type="text" id="txtdownTime_'+options.rowId+'"   name="txtdownTime_'+options.rowId+'_'+options.pos+'"  value="'+rowObject[8]+' "  style="width:109px;  height:23px;text-transform:uppercase;"   class="easyui-textbox" />';	
}
function calculateTime(rowId,colId){ 
	var fromTime = "spnpcsfrom_"+rowId ;
	var toTime = "spnpcsTo_"+rowId ; 
	var total =parseInt(jQuery("#"+fromTime).val())+parseInt(jQuery("#"+toTime).val()); 
	if(total>=0)
	jQuery("#pcslossGrid").jqGrid('setCell',rowId,"Hrs",total);
}
function enableTxtBx(rowid){
	 var txtVal = jQuery("#txtprodImpact_"+rowid).val();
	if( "Y" == txtVal)
		jQuery("#txtdownTime_"+rowid).removeAttr('disabled');
	else
		jQuery("#txtdownTime_"+rowid).attr('disabled','disabled');
}
 
function tstfunction(val,rowid){ 
	jQuery('#lossDescription_'+rowid).val(val);
}
function lossfunction(val,rowid){ 
	jQuery('#lossNo_'+rowid).val(val);
}
 */

</script>

<form id="frmPcsLoss"  name="frmPcsLoss">

<div id="wrapper">
<div class="panel layout-panel layout-panel-center" style="top: 0px; ">
<div class="main-cntborder" style="height:500px; width:1200px;">
<div style="padding-left:10px;margin-top:0px;">
<table  style="width: 100%;">
<tr style="width: 100%;">
<td id="tdFnln" colspan="4">
	<div  id="pcsLossLocnDivLayer" class="easyui-paddingbfpx" style=" ">
			<div  class="easyui-paddingbfpx" id="frmPcsLossFuntKeyIds">
					<%-- <input type="hidden" id="factory" name="cmbPlosFactoryid" value="${requestScope.factId}"  ></input> --%>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbPlosSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<!--  <input type="hidden" id="machine" name="cmbPlosMachineId" value="${requestScope.mchId}"></input>
					--><%-- <input type="hidden" id="flid" name="cmbPlosFlid" value="${requestScope.requestScope.flid}"  ></input> --%>
					        <input type="hidden" id="flid" name="cmbPlosFlid" value="${requestScope.flid}"></input>
				</div>
				<div style="margin-top:-10px">
					<span id="Pcsentrystatus" style="margin-left:450px; margin-top:10px">
							<label  class=""><b>PCS Entry Status </b></label>
					</span>
					<span id="Pcsentrycompleted" style="background-color:#A4BBFF;font-size:20px; border-style:solid;border-width: 1px; visibility: ">	
							<label style="margin-left:10px;font-size:20px;margin-right:10px" class="">Completed</label>
					</span>
					<span id="Pcsentryincompleted" style="background-color:#FF5959;font-size:20px; border-style:solid;border-width: 1px; visibility:">	
							<label  style="margin-left:10px; font-size:20px;margin-right:10px">Incomplete</label>
					</span>
				</div>	
				<div id="pcsLossLocation" style=" "></div>
			</div>
		</td>
		<td  style="padding-left:20px; ">
			<div>
				<span>
					<label class="mandatory-lbl">Cell</label>
					<label style="margin-left:180px;">Date</label>
 					<label style="margin-left:70px;">Shift</label>
				</span>
 				
 			</div>
 			<div>
 				<span>
		   			<input type="text" class="easyui-combobox"  id="cmbPlosCellid" name="cmbPlosCellid" value="${requestScope.cellId}" style="width: 203px;"/>
		   		</span>
		   		<span style="margin-left:2px;">
		   			<input  class="easyui-datebox" id="dtePlosDate" name="dtePlosDate" class="easyui-datebox"  style="width:96px;" value="${requestScope.date}"/>
		   		</span>
		   		<span>
		   			<input  class="easyui-combobox" id="cmbPlosShiftid"  name="cmbPlosShiftid"   style="width: 50px;"  value="${requestScope.shift}"/>
		   		</span>
		<!--   		<input type="button" class="easyui-button" id="btnView"	name="btnView" value="View" style="height: 25px; width:58px;" />     -->   
		   	</div>	
		   		<div style="margin-top:05px">
		   		<span style="margin-left:0px;">
					<label id="lblAvailtime" class=""><b>Avail. Time</b></label>
				</span>
			    <span style="margin-left:0px;">
			    	<input type="text" class="easyui-text" id="txtAvailtime" name="txtAvailtime" value="" readonly="readonly" style="width:30px;text-align: right;font-size:12px;background-color: #BCE0EA;"  maxlength="3" >
        		</span>
        		<span style="margin-left:0px;">
					<label id="lblProdtime" class=""><b>Prod. Time</b></label>
				</span>
			    <span style="margin-left:0px;">
			    	<input type="text" class="easyui-text" id="txtProdtime" name="txtProdtime" value="" readonly="readonly" style="width:40px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="5" >
        		</span>
        		<span style="margin-left:0px;">
					<label id="lblLosstime" class=""><b>Loss Time</b></label>
				</span>
			    <span style="margin-left:0px;">
			    	<input type="text" class="easyui-text" id="txtLosstime" name="txtLosstime" value="" readonly="readonly" style="width:35px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="50" >
        		</span>
        		<span style="margin-left:0px;">
					<label id="lblUnreptime" class=""><b>Unrep. Time</b></label>
				</span>
			    <span style="margin-left:0px;">
			    	<input type="text" class="easyui-text" id="txtUnreptime" name="txtUnreptime" value="" readonly="readonly" style="width:40px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="5" >
        		</span>
    	   	</div> 
		   	<div style="display:none">
		  <!-- 	<input type="text" class="easyui-combobox"  id="cmbPlosMachineId" name="cmbPlosMachineId" value="${requestScope.QtmTlIntrejectionmst.qirmMachineid}" style="width: 180px;"/>
		   	--></div>
		 </td>
	
</tr>
</table> 
<table>
					<tr>
						<td>
							 <div style="width:1143px;padding-left:11px;" class="sub-header" >
								<span>
									<label><b>Full Shift No Plan</b></label>
										<input type='checkbox' id='chkPprdShiftnoplan' style="width:15px"  class="easyui-checkbox" name='chkPprdShiftnoplan' value='Y'/>
								</span>
								<span style="margin-left:91px;">
					 				<label><b>Hourly No Plan</b></label>
        				 	  	 		<input type='checkbox' id='chkPprdHourlynoplan' style="width:15px"  class="easyui-checkbox" name='chkPprdHourlynoplan' value='Y'/>
			                	</span>
								<span style="margin-left:10px;">
									<label id="lblPprdDuration" class=""><b>No Plan Minutes</b></label>
								</span>
			                    <span style="margin-left:05px;">
			                    	<input type="text" class="easyui-text" id="txtPprdNoplanduration" name="txtPprdNoplanduration" value=""  readonly="readonly" style="width:67px;text-align: right;"  maxlength="5" oninput="noplanAvailtimehourly()">
        				  	 	</span>
        				  	 	<span style="margin-left:19px;">
												<label id="lblAvailtimehourly" class=""><b>Avail. Time</b></label>
											</span>
										    <span style="margin-left:0px;">
			    								<input type="text" class="easyui-text" id="txtAvailtimehourly" name="txtAvailtimehourly" value="" readonly="readonly" style="width:45px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="3" onchange="addHourTimevalidtions()">
							        		</span>
        									<span style="margin-left:10px;">
												<label id="lblProdtimehourly" class=""><b>Prod. Time</b></label>
											</span>
			    							<span style="margin-left:0px;">
			    								<input type="text" class="easyui-text" id="txtProdtimehourly" name="txtProdtimehourly" value="" readonly="readonly" style="width:45px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="50" >
        									</span>
        									<span style="margin-left:10px;">
												<label id="lblLosstimehourly" class=""><b>Loss Time</b></label>
											</span>
			    							<span style="margin-left:0px;">
			    								<input type="text" class="easyui-text" id="txtLosstimehourly" name="txtLosstimehourly" value="" readonly="readonly" style="width:45px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="50" >
        									</span>
        									<span style="margin-left:10px;">
												<label id="lblUnreptimehourly" class=""><b>Unrep. Time</b></label>
											</span>
			    							<span style="margin-left:0px;">
			    								<input type="text" class="easyui-text" id="txtUnreptimehourly" name="txtUnreptimehourly" value="" readonly="readonly" style="width:45px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="50" >
        									</span>
        						</div>
						</td>
					</tr>
				</table>
<div  id="tabLossEntry" class="easyui-tabs"  style="height:395px;width:1170px;padding-left:0px;margin-left:5px; float:left">
   		<div title="Production" style="padding:0px;">
				<div style="padding:0 0 0 0%;">
					<table width="99%"  align="center"> 
						<tr >
        					<td colspan="1" style = "width:300px;">
        				  	 	<div style="margin-top:-7px; width : 300px;"">
        				  		 	<span id="spnLblHour" style="margin-left:10px;"> 	<label class="mandatory-lbl" id="lblHour">Hour</label> </span> 
        				  		 	<span > <label style="margin-left:25px;" id="lblAreaS">Area</label> </span>							
        							
									<span id="spnlblPrd" style="padding-left:30px;"> 	<label class="mandatory-lbl" id="lblProduct">Product</label></span>
        				   		 </div>
        				  
        				  	 </td>
        			  	 	 <td id="spnprdMdl" colspan="1" style = "width:185px;">
        				  	   		<div style="margin-top:-7px;width : 185px;">
        				  	   			<span id="prdMdl" style="margin-left:33px;"> 	<label class="mandatory-lbl" id="lblProductmodel">Model</label></span>
					 	  	   		</div>
        			
        				  		</td>
        				  		<!-- 
        				  		<td style=" width : 77px;">
        							
        				  		</td>   -->
        				  		<td style=" width : 49px;">
        				  		<div style="margin-top:-7px; width : 50px;margin-left:20px;">
        							<span id="spnlblMan" style="margin-top:-7px;  width : 65px;">
        							 	<label class="mandatory-lbl" id="lblManpower">Man</label>
        							</span>
        						</div>
        				  		</td>     
        				  	   <td>	<span id="spnlblRnt" style="margin-left:10px;"><label id="" >RNT</label>
        				  				</span>
							  
        				   	    </td>
        				   	    
        				   	     <td>	<span style="padding-left:05px;">
        				   	            <input type="button" class="easyui-button" id="btnToolAlert" name="btnToolAlert"  value="Tool Change Alert"	style="height: 25px; width:px" />
        				  				</span>
							  
        				   	    </td>
        				   	      
        				    </tr>
        				    
        				    </table>
        				    
        				    <table width="99%"  align="center"> 
        				    <tr>
        				    <td colspan="4" style="width:725px;">
        				    		<div style="margin-top: -3px;">
        				    			<span id="spnprdHour" style="margin-left:10px;">
        				   	 	    			<select id='selPprdHour' name='selPprdHour'  class="" style="width :50px;margin-top:0px; height:23px;" oninput='fillHourlyCalculations' > 
								        	<!-- 		
								        			<option id='0' value='' > </option>
								    RNT	    		<option id='1' value='1' >1</option>
									        		<option id='2' value='2' >2</option>
									        		<option id='3' value='3' >3</option>
									        		<option id='4' value='4' >4</option>
									        		<option id='5' value='5' >5</option>
									        		<option id='6' value='6' >6</option>
									        		<option id='7' value='7' >7</option>
									        		<option id='8' value='8' >8</option>
									        		<option id='9' value='9'>H+</option>   -->
								      	  		</select>
							        		</span>
							        		<span id="inArea" style="margin-left:07px;">
											<input type="text" class="easyui-combobox"  id="cmbPprdArea" name="cmbPprdArea" value="${requestScope.PcsTlProductiondata.pprdManpower}"  style="width:50px; /* height:23px; */">
										<!--	<option value="R1">R1</option><option value="R2">R2</option></select> -->
	        									<!--  <input type="text"  class="easyui-combobox"  id="cmbPprdArea"   name="cmbPprdArea"  value="${requestScope.PcsTlProductiondata.pprdManpower}" style="width:50px;height:23px;text-align: right;" maxlength="100" >
	        								
	        								--></span>
							      			<span id="spnPrdcmb"style="margin-left:05px;">
							      				<input type="text"  class="easyui-combobox"  id="cmbPprdProductid"   name="cmbPprdProductid"  value="${requestScope.PcsTlProductiondata.pprdProductid}"  style="width:250px;margin-top: 2px; /* height:23px; */text-transform:uppercase;" />
							      			</span>
							      	<!-- 		<span id="err_cmbPprdProductid" class="tpm-errormsg" style="width:200px;"></span>  -->
	          		     					<span style="margin-left:10px">
        				  	   					<input type="text"  class="easyui-combobox"  id="cmbPprdProductmodel"   name="cmbPprdProductmodel"  value=""  style="width:165px; /* height:23px; */" />
        				  	   				</span>
											<!--<span id="err_cmbPprdProductmodel" class="tpm-errormsg"></span>-->
											
        				  	   				<span style="margin-left:07px;">
	        									<input type="text"  class="easyui-combobox"  id="cmbPprdManpower"   name="cmbPprdManpower"  value="${requestScope.PcsTlProductiondata.pprdManpower}" style="width:50px;/* height:23px; */text-align: right;" maxlength="100" >
	        								</span>
<!--	        								<span id="err_cmbPprdManpower" class="tpm-errormsg"></span> -->
											<span style="margin-left:05px;"><input type="text"  class="easyui-text"  id="txtPprdCycletime" name="txtPprdCycletime"  value="" readonly="readonly" style="width:48px;text-align: right;" maxlength="90" ></span>
											
       									</div>
        						 		<div style = "display:none">
											<span style="float:left; margin-left:0px;" id="err_cmbPprdProductid"  class="tpm-errormsg"> </span>
											<span style="float:left; margin-left:0px;" id="err_cmbPprdProductmodel"  class="tpm-errormsg"> </span>
											<span style="float:left; margin-left:0px;" id="err_cmbPprdManpower"  class="tpm-errormsg"> </span>
										</div>	
        					<!-- 	 		<div>
        						 			<span id="err_cmbPprdProductid" class="tpm-errormsg" style="width:200px;"></span>
        						 			<span id="err_cmbPprdProductmodel" class="tpm-errormsg" style="width:200px;"></span>
        						 			<span id="err_cmbPprdManpower" class="tpm-errormsg"></span>
        						 		</div>   -->
        						 		<div>
        						 		<!-- 	<label style="margin-left:10px;">Batch Qty</label>
        						 			<label style="margin-left:6px">Weight/piece</label>
        						 			<label style="margin-left:6px">Area</label>  -->
        						 			<span style="margin-left:10px;"> 	<label id="">Batch Qty </label></span>
        						 			<span style="margin-left:15px;"> 	<label id="">Weight/Piece</label></span>
											<span style="margin-left:10px;"> 	<label id="">Plan Qty</label></span>
        						 			<span style="margin-left:10px;"> 	<label class="mandatory-lbl" id="lblPrdqty">Prd Qty</label></span>
							    			<span style="margin-left:10px;"> 	<label id="">Rwk Qty</label></span>
							    			<span style="margin-left:8px;"> 	<label id="">Rej Qty</label></span>
							    			<span style="margin-left:15px;"> 	<label id="">Stopage(Min.)</label></span>
							    			<span style="margin-left:13px;"> 	<label id="">Charges</label></span>
							    			<span style="margin-left:23px;"> 	<label id="">Remarks</label></span>
        				    	
        						 		</div>
        						 		<div>
        						 		<!-- 	<span style="margin-left:10px"><input type="text" class="easyui-text" id="txtPprdBatchQty" name="txtPprdBatchQty" style="width:50px"/> </span>
        						 			<span style="margin-left:10px"><input type="text" class="easyui-text" id="txtPprdWeightpiece" name="txtPprdWeightpiece" style="width:50px"/></span>
        						 			<span style="margin-left:10px"><select id="txtPprdArea" name="txtPprdArea"><option>R1</option><option>R2</option></select></span>  -->
        						 			<span style="margin-left:10px;"><input type="text"  class="easyui-text"  id="txtPprdBatchqty"   name="txtPprdBatchqty"  value="" style="width:50px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="5" readonly="readonly" oninput="fillPlannedqtyhourly()"></span>
        						 			<span style="margin-left:15px;"><input type="text"  class="easyui-text"  id="txtPprdWeightPiece"   name="txtPprdWeightPiece"  value="" style="width:50px;text-align: right; background-color: #BCE0EA;maxlength="5" readonly="readonly" oninput="fillAvailtimehourly()"></span>
        				   	    			<span style="margin-left:33px;"><input type="text"  class="easyui-text"  id="txtPlannedqty"   name="txtPlannedqty"  value="" style="width:50px;text-align: right;font-size:12px;background-color: #BCE0EA;" maxlength="5" readonly="readonly" oninput="fillPlannedqtyhourly()"></span>
        						 			<span style="margin-left:15px;"><input type="text"  class="easyui-text"  id="txtPprdProducedqty"   name="txtPprdProducedqty"  value="" style="width:50px;text-align: right;" maxlength="5"  oninput="fillAvailtimehourly()"></span>
        				   	    			<span style="margin-left:05px;"><input type="text"  class="easyui-text"  id="txtPprdReworkqty"   name="txtPprdReworkqty"  value="" style="width:50px;text-align: right;" maxlength="5" oninput="productionqtyValidation()"></span>
        				  	 				<span style="margin-left:05px;"><input type="text"  class="easyui-text"  id="txtPprdRejectedqty"   name="txtPprdRejectedqty"  value="" style="width:50px;text-align: right;" maxlength="5" oninput="productionqtyValidation()"></span>
        				  	 				<span style="margin-left:05px;"><input type="text"  class="easyui-text"  id="txtPprdStopageMin"   name="txtPprdStopageMin"  value="" style="width:50px;text-align: right;" maxlength="5" oninput="productionqtyValidation()"></span>
        				  	 				<span style="margin-left:35px;"><input type="text"  class="easyui-text"  id="txtPprdChareges"   name="txtPprdChareges"  value="" style="width:60px;text-align: right;" maxlength="5" oninput="productionqtyValidation()"></span>
        				  	 				       				  	 				
        				  	 				<span style="margin-left:05px;"><input type="text"  class="easyui-text"  id="txtPprdRemarks"   name="txtPprdRemarks"  value="" style="width:200px;" maxlength="50" ></span>
        				   	    	 	 	<span style="margin-left:10px;"><input type="button" class="easyui-button" id="btnProductionSave"	name="btnProductionSave" value="Save" style=" height:23px; width:50px"/></span>
        				  		    	     <span style="margin-left:10px;"><input type="button" class="easyui-button" id="btnProdDelete" name="btnProdDelete"  value="Delete"	style="height: 25px; width:px" /></span>
        				  				</div>
        				  				<div>
        				  					<label class="mandatory-lbl"><b>Double click on row to enter rejection/rework entry</b></label>
        				  				</div>
        				  				<div style="margin-left:05px;">
        				  					<table id="pcsproductionGrid" width="550px;" "style=margin-left:10px;" ></table>
         		    						<div id="pcsproductionPager"></div>
        				  				</div>	
        				    		</td>
        				    		<td>
		        				    <div id="rejectionGridbtn"style="margin-top:10px ;">
		        				    				<span style="margin-left:204px;"><input type="button" class="easyui-button" value="Add Row" id="btnRejBreakup"	style="height: 25px;" /></span>
													<span><input type="button" class="easyui-button" value="Save Rejection" id="btnRejSave"	style="height: 25px;" /></span>
		        								   	<span><input type="button" class="easyui-button" value="Delete Rejection" id="btnRejDelete"	style="height: 25px;" /></span>
		        		    						<table id="pcsrejectionGrid" width="350px"></table>
		         		    						<div id="pcsrejectionPager"></div> 
		         		    					</div>
		         		    		</td>
        				    	</tr>
        				</table>
         		    </div>
    		</div>
   	 <div id ="tabLossEntry" title="Loss Entry" style="margin-left:0px;height:300px;">
   	 <div style="width:94%;_width:80%;padding-left:-50px;float:left;" class="sub-header" >
					<label><b>Loss Entry Details</b></label></div>
     	<div style="padding:10px 0 0 0%;">
		 	<table>
   				<tr>
   				<td colspan="3">
   					<div>
   						<span style="padding-left:05px;"><label id="lblHourL">Hour</label></span>
   						<span style="padding-left:25px;"><label class="mandatory-lbl">From Time</label> </span> 	
						<span style="padding-left:120px;"><label class="mandatory-lbl" id="lbltotime">To Time</label> </span>
					</div>
   				 	<div style="display: none;">  
						<span>
   							<input id="dteShiftstartdate" name="dteShiftstartdate"  class="easyui-datebox" value="${requestScope.date}" readonly="readonly" style="width: 95px;" />
						</span>
						<span class="spinner">	
							<input  id="spnShiftstarttime" name="spnShiftstarttime" class="easyui-timespinner spinner-text validatebox-text" readonly="readonly" value="" style="width: 60px;" >
						</span>
						<span>
							<input id="dteShiftenddate" name="dteShiftenddate" class="easyui-datebox" readonly="readonly" value="${requestScope.date}"  style="width: 95px;" />
						</span>
						<span class="spinner">
							<input  id="spnShiftendtime" name="spnShiftendtime" class="easyui-timespinner spinner-text validatebox-text" readonly="readonly" value="" style="width: 60px;" >
						</span>
					</div>
   					<div>
   					<span id="spnlossHour"  style="margin-left:05px;">
        				   <select id='cmbSelPrdHourL' name='cmbSelPrdHourL'  class="easyui-combobox"  style="width :43px;margin-top:0px; /* height:23px */;" oninput='fillHourlyCalculations' >
        				   						<!-- 	<option id='0' value='' > </option>
								    	    		<option id='1' value='1' >1</option>
									        		<option id='2' value='2' >2</option>
									        		<option id='3' value='3' >3</option>
									        		<option id='4' value='4' >4</option>
									        		<option id='5' value='5' >5</option>
									        		<option id='6' value='6' >6</option>
									        		<option id='7' value='7' >7</option>
									        		<option id='8' value='8' >8</option>
									        		<option id='9' value='9'>H+</option> --> 
        				   </select>
        				</span>
        				<span style="padding-left:05px;">
        					<input type="checkbox" id="chkPlosDurationSel" name="chkPlosDurationSel" style="width:15px" class="easyui-checkbox" value='Y'/>
        				</span>
   						<span style="padding-left:05px;">
   							<input class="easyui-datebox" id="dtePlosFromdate" name="dtePlosFromdate" class="easyui-datebox" value="${requestScope.date}"  style="width:75px;" />
						</span>
				  		<span style="display: none;" >  
				<!--		<span>   -->
				
							<input  id="spnPlosFromtime" name="spnPlosFromtime" class="easyui-timespinner spinner-text validatebox-text" value="" style="width: 50px;" >
						</span>
						<span>
							<input type="text" class="easyui-text" id="txtLossHourfromtime" name="txtLossHourfromtime"  value="" style="width:20px;text-align: right;" maxlength="2";   > <!--  oninput=lossFromTimeMinutesValidations()   -->
							<label><b>:</b></label>
							<input type="text" class="easyui-text" id="txtLossMinutesfromtime" name="txtLossMinutesfromtime"  value="" style="width:20px;text-align: right;" maxlength="2"   > <!--  oninput=lossFromTimeMinutesValidations()   -->
						</span>
						<span style="margin-left:0px">
						    <input type='checkbox' id='chkPlosTotime' style="width:15px"  class="easyui-checkbox" name='chkPlosTotime' value='Y'/>
							<input class="easyui-datebox"  id="dtePlosTodate" name="dtePlosTodate" class="easyui-datebox" value="${requestScope.date}" style=" width : 75px;" /></span>
					  	<span style="display: none;" > 
			<!--			<span>  -->
							<input  id="spnPlosTotime" name="spnPlosTotime" class="easyui-timespinner spinner-text validatebox-text"  value="" style="width: 50px;" >
						</span>
						<span>
							<input  type="text" class="easyui-text" id="txtLossHourtotime" name="txtLossHourtotime"  value="" style="width:20px;text-align: right;" maxlength="2" > <!--  oninput="lossToTimeMinutesValidations()"   -->
							<label><b>:</b></label>
							<input  type="text" class="easyui-text" id="txtLossMinutestotime" name="txtLossMinutestotime"  value="" style="width:20px;text-align: right;"maxlength="2" >  <!--  oninput="lossToTimeMinutesValidations()"   -->
						</span>
						<span id="err_spnPlosTotime" class="tpm-errormsg"></span>
						<span id="err_dtePlosTodate" class="tpm-errormsg"></span>
						<span id="err_spnPlosFromtime" class="tpm-errormsg"></span>
						<span id="err_dtePlosFromdate" class="tpm-errormsg"></span>
   					
					</div>
   					<div style="margin-top:-5">
   				     	<span style="padding-left:05px;"><label class="mandatory-lbl" id="lblSubLoss" >Sub Loss</label></span>
						<span style="padding-left:55px;visibility: hidden;"><label class="mandatory-lbl" id="lblLossReason" >Loss Reason</label></span> 	
			
   					</div>
   					<div>
   						<span style="padding-left:05px;">
							<input type="text"  class="easyui-combobox"  id="cmbPlosLossid"   name="cmbPlosLossid"  value=""  style="width:260px; /* height:23px; */text-transform:uppercase;" />
						</span>
						<span style="padding-left:5px;">
							<input type="button" class="easyui-button" id="btnLink" name="btnLink" value="..."  title="Loss Details" disabled="disabled" size="20px;">
						</span>
					</div>
   				</td>
   				<td ><div style="padding-left:20px; width:220px;">   						
   						 	<label>Minutes</label>
   						 	<!-- temporary commented 
							<span style="padding-left:47px;display: none;"><label>Prd.Impact</label></span>
							<span style="padding-left:11px;display: none;"><label>Prd.Imp.Qty(TON)</label></span>
   							-->
   							<span style="padding-left:20px;">
   								   <label>Quantity</label>
   								
   							</span>
   							<span style="padding-left:20px;">
   								   <label>Defect Source</label>
   								
   							</span>
   						</div>
   						
   						<div>
   							<span style="padding-left:30px;display: none;">
								<input type="text" class="easyui-text"  id="txLossHours"   name="txLossHours"  value=""  style="width:80px;text-align: right;" disabled="disabled" maxlength="5" >
							</span>
							<span style="padding-left:20px;">
								<input type="text" class="easyui-text"  id="txtPlosLosstime"   name="txtPlosLosstime"  value=""  style="width:50px;text-align: right;"  maxlength="5"  onchange="lossMinutevalidate()">
							</span>
							<span style="padding-left:15px;">
								<input type="text" class="easyui-text"  id="txtPlosLossQty"   name="txtPlosLossQty"  value=""  style="width:50px;text-align: right;" disabled="disabled" maxlength="5"  onchange="lossMinutevalidate()">
							</span>
							<!-- temporaaly commented 
							<span style="padding-left:28px; display: none;">
								<input type="text"  class="easyui-combobox"  id="cmbPlosTradeid"   name="cmbPlosTradeid"  value=""  style="width:170px; height:23px;text-transform:uppercase;" />
							</span>
						 	<span style="display: none;">
								<select id="cmbPlosProdImpact" name="cmbPlosProdImpact"  panelHeight="40px;" class="easyui-combobox" style="width :60px;height:20px;margin-left:10px" > 
									 <option value='Y'>YES</option>
									 <option value='N'>NO</option>
								</select>
							</span>-->
							
							<span style="padding-left:15px;">
								<!--  <input type="text" class="easyui-combo"  id="cmbPlosSource"   name="cmbPlosSource"  value=""  style="width:160px;text-align: right;"  ">
									--><select  class="easyui-combobox"id="cmbPlosSource" name="cmbPlosSource" class="easyui-combobox"  style="width:80px; /* height:20px; */"   <c:out value = "${ requestScope.abnormalityBean.disableForm == true ? ' disabled':''}"/> >  
                        	<option title="" value=""></option>
                        	<option title="V" value="V">Vendor</option>
                        	<option title="IN" value="IN">In House</option>
                        	
							</select>
							
							
							</span>
					
						<!--	<input type="text"  class="easyui-combobox"  id="cmbTool"   name="cmbTool"  value=""  style="width:170px; height:23px;text-transform:uppercase;padding-left:120px;" />
							
							 
							<span style="display: none;">
								<input type="text" class="easyui-text"  id="txtPlosProdImpQty"   name="txtPlosProdImpQty"  value=""  style="width:100px;text-align: right;margin-left:10px" maxlength="6" >
							</span>
							 -->
   						</div>
   						<div>
   							<span style="padding-left:0px;"><label>Loss Name</label> 	</span> 
   						</div>
   						<div>
   							<input type="text"  class="easyui-combobox"  id="cmbMainLoss"   name="cmbMainLoss"  value=""  style="width:268px; /* height:23px; */text-transform:uppercase;" />
						</div>
   				</td>
   				<td id="equipmenttd" style="padding-left:15px;">
   				<div>
   				   <label id="lblplosMachine" style="padding-left:0px;"> Equipment</label>
   				   <label id="lblplosSource" style="padding-left:0px;"> Source</label>
   				   </div>
   				   <div id="plosequip">
   				   	<input type="text"  class="easyui-combobox"  id="cmbPlosMachineid"   name="cmbPlosMachineid"  value=""  style="width:265px; /* height:23px; */text-transform:uppercase;padding-left:20px;" />
   				 	 
   				   </div>
   				   <div style="Margin-top:0px;">
   							<span style="padding-left:0px;"><label id="lblTool" >Tool</label> 	</span> <!-- class="mandatory-lbl" -->
   						</div>
   						<div>
   							<input type="text"  class="easyui-combobox"  id="cmbPlosToolId"   name="cmbPlosToolId"  value=""  style="width:268px; /* height:23px; */text-transform:uppercase;" />
						</div>
   				   <div id="plosSourcediv">
   				   <input type="text"  class="easyui-text"  id="txtPlosSource"   name="txtPlosSource"  value=""  style="width:265px; /* height:23px; */text-transform:uppercase;padding-left:20px;" />
   				</div>
   						<div style="Margin-top:0px;">
   							<span style="padding-left:0px;"><label id="lblLossReason" >Loss Reason</label> 	</span> <!-- class="mandatory-lbl" -->
   						</div>
   						<div>
   							<input type="text"  class="easyui-text"  id="txtPlosLossreason"   name="txtPlosLossreason"  value=""  style="width:268px; /* height:23px; */text-transform:uppercase;" />
						</div>
   				</td>
   				<td style="padding-left:15px;"> 
   				   <div style="margin-top:3px;"><label>Remarks (Optional)</label></div>
   					<div style="margin-top:2px;">
						
   						<textarea class="easyui-textarea"  id="txtPlosRemarks"   name="txtPlosRemarks"  value=""  style="width:200px;height:60px;"rows="2"; maxlength="500px" ></textarea>
						
   					</div>
   				</td>
   				</tr>
   				<tr>
   				<td > <div>
   				
   				</div></td><td></td>
   				<td style="padding-right:200px;">
   				<span style="margin-left:00px;">
							<input type="button" class="easyui-button" id="btnSave"	name="btnSave" value="Save" style=" width : 60px;height:20px;"/>
						</span>
						<span style="margin-left:10px;">
							<input type="button"  class="easyui-button" id="btnLossDelete"	name="btnLossDelete" value="Delete" style=" width : 50px;height:20px;"/> <!-- display: none; -->
						</span></td>
   				</tr>
   			</table>
 		</div>
 		<div style="padding-left:05px;margin-top:10px;">
 		 
 				<table id='pcslossGrid'>
    	       		<tr><td></td></tr>
    	       		</table>
				<div id="pcslossPager"></div>
		</div>
 		</div>
	  	<div title="Result" style="padding:0px;">
				<div style="width:94%;_width:80%;padding-left:-50px;float:left;" class="sub-header" >
						<label><b>Result Details</b></label></div>
					<div style="padding:20 0 0 2%;">
					
						<table width="98%"  align="Center"> 
        				   <tr>
        				  			 <td colspan="3">
        				            		<div> 
        				   						 <div style="margin-left:25px;"><table id="pcsresultGrid" width="450px"></table> </div>
				            					<div id="pcsresultPager"></div> 
				            				</div> 
        				     		 </td>
        				    </tr>
         		    	</table>
         		    </div>
         		 </div>
       <!--   	<div title="View Result" style="padding:0px;"> 
         			<div style="width:94%;_width:80%;padding-left:-50px;float:left;" class="sub-header" >
						<label><b>Result View Mode</b></label>
					</div>
         			<div style="padding:20 0 0 2%;">
         				<table width="98%"  align="Center"> 
        					<tr>
        				  		<td colspan="3">
        				        	<div> 
        				   				<div style="margin-left:25px;"><table id="pcsviewHourlyResultGrid" width="450px"></table> </div>
				            			<div id="pcsviewHourlyResultPager"></div> 
				            		</div> 
        				     	</td>
        				    </tr>
         		    	</table>
					</div>
         	</div>	 
         		  -->
         		 
</div>

</div>

</div>
<input type="hidden" id="hdnincAvailtime" name="hdnincAvailtime" value="" /> 
<input type="hidden" id="hdnrowidbtn" name="hdnrowidbtn" value="" />
<input type="hidden" id="hdnAddrowtoGrid" name="hdnAddrowtoGrid" value="" /> 

<input type="hidden" id="hdnSftStartTime" name="hdnSftStartTime" value="">
<input type="hidden" id="hdnSftToTime" name="hdnSftToTime" value="">
<input type="hidden" id="hdnSftDuration" name="hdnSftDuration" value="">
<input type="hidden" id="hdnPlosKeyid" name="hdnPlosKeyid" value="">
<input type="hidden" id="hdnPldetailsId" name="hdnPldetailsId" value="">
<input type="hidden" id="hdnPlmMasterId" name="hdnPlmMasterId"/>
<input type="hidden" id="hdnDetailTable" name="hdnDetailTable"/>
<input type="hidden" id="hdnPprdKeyid" name="hdnPprdKeyid" value="">
<input type="hidden" id="hdnPbrdKeyid" name="hdnPbrdKeyid" value="">
<input type="hidden" id="hdnPlrkKeyid" name="hdnPlrkKeyid" value="">
<input type="hidden" id="hdnUnrepTimeHourly" name="hdnUnrepTimeHourly" value="">
<input type="hidden" id="hdnAvailTime" name="hdnAvailTime" value="">

<input type="hidden" id="hdnProductid" name="hdnProductid" value="">

<input type="hidden" id="hdnUrl" name="hdnUrl" value="">
<input type="hidden" id="hdnSetFromToTime" name="hdnSetFromToTime" value="">
<input type="hidden" id="hdnisShiftWise" name="hdnisShiftWise" value="${requestScope.shiftwise}">

</div>
</div>
</form>