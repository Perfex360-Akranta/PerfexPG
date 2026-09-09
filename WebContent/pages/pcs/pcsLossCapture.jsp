<script>

 // - addded by vignesh --- //
 function parseQuery() {
  var q = {};
  var s = window.location.search;
  if (s && s.indexOf('?') >= 0) {
    s.substring(1).split('&').forEach(function(kv) {
      if (!kv) return;
      var p = kv.split('=');
      var k = decodeURIComponent(p[0] || '').toLowerCase();
      var v = decodeURIComponent(p[1] || '');
      q[k] = v;
    });
  }
  return q;
}

// 7-Sep-2025 -> 07-Sep-2025 (EasyUI likes dd-MMM-yyyy)
function normalizeDMY(s) {
  if (!s) return s;
  s = s.trim();
  var m = s.match(/^(\d{1,2})[-\/ ]([A-Za-z]{3})[-\/ ](\d{4})$/);
  if (!m) return s;
  var d = m[1]; if (d.length < 2) d = '0' + d;
  var mon = m[2].substr(0,1).toUpperCase() + m[2].substr(1).toLowerCase();
  return d + '-' + mon + '-' + m[3];
}

 
  // - addded by vignesh --- //
jQuery(document).ready(function(){

	initialiseForm('frmPcsLoss');
	jQuery('#submitForm').val('frmPcsLoss');

	jQuery('#lblLossReason').html('Loss Reason');
	var mode=jQuery("#hdnMode").val();
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	
	if (minutes < 10){
		minutes = "0" + minutes;
	}	
    if(mode=="MODIFY"){
		//alert(mode);
		var flid=jQuery("#hdnFlid").val();
		//var lossdate=jQuery("#hdnLossDate").val();
		//alert(lossdate);
		setFieldValue("flid",flid);
	
		//setFieldValue('dtePlosDate',lossdate);
        }
	disableField("frmPcsLoss", "txtPlosProdImpQty");
	numericTextBox('txtPlosProdImpQty');
	
	var factId = "";//jQuery("#frmPcsCalendar input[id='factory']").val();
	var dataString = '?&factId='+factId+'&sectId='+jQuery("#frmPcsLoss input[id='section']").val();
	dataString += '&cellId='+jQuery("#frmPcsLoss input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
	
	setTimeout(function() {
		processAjaxCalls('txt_shift.brdn',dataString,'getPCSShift','getPCSShiftErr');
	},10);

	fillComboBox("frmPcsLoss","cmbPlosCellid","cellCombo.commonFilter" );	
	fillComboBox("frmPcsLoss","cmbPlosProdImpact","","",false);	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	fillComboBox("frmPcsLoss","cmbPlosLossreason","combo_Phenomena.pcs?cellId="+cellId+"&flid="+flid);
	
	
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();			
	var ds = "?&sectId="+sectId+"&isQtyLoss=";
	//fillComboBox("frmPcsLoss","cmbPlosLossid","combo_LossNo.pcs"+ds);
	fillComboBox("frmPcsLoss","cmbPlosLossid","combo_Loss.pcs"+ds);
	fillComboBox("frmPcsLoss","cmbPlosEquipment","comboEquipment.pcs");
	fillComboBox("frmPcsLoss","cmbPlosTradeid","trade.commonFilter");
	var ds = "&sectId="+sectId+"&cellId="+cellId;
	fillComboBox("frmPcsLoss","cmbDetectedBy","employee.commonFilter");
	formatDateBox('dtePlosDate','dd-MMM-yyyy');
	formatDateBox('dtePlosFromdate','dd-MMM-yyyy');
	formatDateBox('dtePlosTodate','dd-MMM-yyyy');

	
	// ------- vignesh 
	
	
     var q = parseQuery();
     var mode = jQuery("#hdnMode").val();         // 
     var hasQueryDates = !!(q.fromdate || q.todate || q.date);
	
	
	// ------- vignesh 
	
	  // uncomment from here--
  // var flid = jQuery("#frmEmpPage input[id='flid']").val();
   var flidgrid=jQuery("#hdnFlid").val();
   //alert(flidgrid);
   
   if (flid != null)
   	   loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmPcsLoss","&flid="+flid);
//   else if(flidgrid !=null){
//	   alert(flidgrid);
//	   var data="&flid="+flidgrid;
//	   loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmPcsLoss",data);
 //  }
   else
	   loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmPcsLoss","");
	  //  --- uncomment from here
	//processGridnew("pcsLoss_input.base","?", "pcslossGrid", "pcslossPager","","","","pcsloss_loadComplete");
	
	
	
	
// 	var flid = (jQuery("#frmEmpPage input[id='flid']").val() || '').trim();
// if (flid.length > 0) {
//   loadFunctionalLocation(
//     "pcsLossLocation",
//     "functionalLoc_lossCapture.pcs",
//     "pcsLossLocationnValues",
//     "frmPcsLoss",
//     "&flid=" + encodeURIComponent(flid)
//   );
// } else {
//   // Let backend choose the correct flid for this user
//   loadFunctionalLocation(
//     "pcsLossLocation",
//     "functionalLoc_lossCapture.pcs",
//     "pcsLossLocationnValues",
//     "frmPcsLoss",
//     ""
//   );
// }
	
	
	
	
	
	
	
	
	jQuery("#btnView").click(function(){
		//navigateToNextForm("OtherLossEntry_input.pcs" ,'Production Other Losses',null,null);
		viewGrid();
		//LoadPopUp("loadPCSResult", "OtherLossEntry_input.pcs?", true,"1154px","450px","5px","5px", "otherLoss_successCallBack","Production Losses",true);
	});

	
	
	
	
	//spinnerKeyPress('spnPlosFromtime');
	//spinnerKeyPress('spnPlosTotime');
	setTimeout(function(){ fillWithCurrentDate("spnPlosFromtime");},500);
	setTimeout(function(){ fillWithCurrentDate("spnPlosTotime")},500);

	disableField("frmPcsLoss", "cmbPlosLossid");
	
	
	 jQuery('#btnSave').click(function() {

         var plosKeyid  = jQuery('#hdnPlosKeyid').val();
         var fromdate   = jQuery('#dtePlosFromdate').datebox("getValue");

         
         fromdate = normalizeDMY(fromdate || '');

         var months = {Jan:0,Feb:1,Mar:2,Apr:3,May:4,Jun:5,Jul:6,Aug:7,Sep:8,Oct:9,Nov:10,Dec:11};
         var p = (fromdate || '').match(/^(\d{2})-([A-Za-z]{3})-(\d{4})$/);

         if (!p) {
             alert("Invalid From Date. Please select a valid date.");
             return false;
         }

         var recDate = new Date(parseInt(p[3], 10), months[p[2]], parseInt(p[1], 10));
         var today   = new Date(srvTime());
         today.setHours(0, 0, 0, 0);
         var diffDays = Math.floor((today - recDate) / 86400000);

         
         if (!plosKeyid || plosKeyid.trim() === '' || plosKeyid === 'undefined') {
             if (diffDays > 7) {
                 alert("Cannot add a new record with a date older than 7 days.");
                 return false;
             }
         }
         
         else {
             if (diffDays > 30) {
                 alert("Cannot modify a record older than 30 days.");
                 return false;
             }
         }

         if (fngetTotalLossTime() == false)
             return false;

         var losshours = jQuery('#txtPlosLosstime').val();
         if (losshours.trim().length > 0) {
             var formId      = jQuery('#submitForm').val();
             var pldetailsId = jQuery('#hdnPldetailsId').val();
             var todate      = jQuery('#dtePlosTodate').datebox("getValue");
             var enteredBy   = jQuery('#cmbDetectedBy').combobox("getValue");
             var url = "frmPcsLoss_save.pcs?&plosKeyid="+plosKeyid+"&pldetailsId="+pldetailsId
                       +"&fromdate="+fromdate+"&todate="+todate+"&enteredBy="+enteredBy;
             if (!plosKeyid || plosKeyid.trim() === '' || plosKeyid === 'undefined')
                 url += "&saveMode=create";
             else
                 url += "&saveMode=update";
             saveForm(formId, url);
         } else {
             alert("Enter Loss Hours");
         }
     });

	
// 	jQuery('#dtePlosDate').datebox({  	   
// 	   	onSelect:function(recordid)
// 			{
				
// 	   			lossDateEvt("dtePlosDate");
// 	   			//-- Commented by vignesh
// 	   		//	viewGrid();
// 	   			var todate = jQuery('#dtePlosDate').datebox('getValue');
// 	   			setFieldValue('dtePlosTodate',todate);
// 	   			setFieldValue('dtePlosFromdate',todate);
// 			} 
// 	});
	
	jQuery('#dtePlosDate').datebox({  	   
	   	onSelect:function(recordid)
			{
				
	   			lossDateEvt("dtePlosDate",recordid);
	   			//-- Commented by vignesh
	   		//	viewGrid();
	   		//	var todate = jQuery('#dtePlosDate').datebox('getValue');
	   		//	setFieldValue('dtePlosTodate',todate);
	   		//	setFieldValue('dtePlosFromdate',todate);
	   			//var picked =  FormatDatefromISD(recordid);// jQuery('#dtePlosDate').datebox("getValue");
	   			//jQuery('#dtePlosFromdate').datebox('setValue', picked);
	   			//jQuery('#dtePlosTodate').datebox('setValue',  picked);
	   			

			} 
	});
	
	

	jQuery('#dtePlosFromdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			//lossDateEvt("dtePlosFromdate");
	   			//viewGrid();
			} 
	});

	jQuery('#dtePlosTodate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			//lossDateEvt("dtePlosTodate");
	   			//viewGrid();
			} 
	});

	spinnerKeyPress('spnPlosFromtime');
	spinnerChange('spnPlosFromtime','fngetTotalLossTime');
	spinnerUp('spnPlosFromtime','fngetTotalLossTime');
	spinnerDown('spnPlosFromtime','fngetTotalLossTime');	

	spinnerKeyPress('spnPlosTotime');
	spinnerChange('spnPlosTotime','fngetTotalLossTime');
	spinnerUp('spnPlosTotime','fngetTotalLossTime');
	spinnerDown('spnPlosTotime','fngetTotalLossTime');	

	fillWithCurrentDate('dtePlosDate');

	// --- vignesh 
	
	//fillWithCurrentDate('dtePlosFromdate'); -- commenting
	//fillWithCurrentDate('dtePlosTodate');  --  commenting
	
		// Range defaults only if we don't have dates from URL and it's not MODIFY.
if (!(mode === 'MODIFY' || hasQueryDates)) {
  fillWithCurrentDate('dtePlosFromdate');
  fillWithCurrentDate('dtePlosTodate');
}
	
if (hasQueryDates) {
	  var fd = normalizeDMY(q.fromdate || q.date || '');
	  var td = normalizeDMY(q.todate   || q.date || fd);

	  // Push into EasyUI (important: use datebox('setValue', ...) not setFieldValue)
	  jQuery('#dtePlosFromdate').datebox('setValue', fd || '');
	  jQuery('#dtePlosTodate').datebox('setValue',   td || '');

	  // Optional: mirror single date so UI looks consistent
	  jQuery('#dtePlosDate').datebox('setValue', td || fd || '');
	}


	// --- vignesh --------------------------------------------// 
	
	
	
	
	

});

jQuery('#btnLossDelete').click(function() {
	var formId = jQuery('#submitForm').val();
        var Pldetailsid = jQuery('#hdnPldetailsId').val();
	var PlrkKeyid=jQuery('#hdnPlosKeyid').val();
	var sectId = jQuery("#frmPcsLoss input[id='section']").val();		
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var entryDate = jQuery("#dtePlosDate").datebox('getValue');
	var dataString = '?&PlrkKeyid='+PlrkKeyid+'&Pldetailsid='+Pldetailsid+'&sectId='+sectId;
	var yyid=jQuery('#hdnyyid').val();
	var kznid=jQuery('#hdnkznid').val();
	var actplnid=jQuery('#hdnactplnid').val();
//alert(yyid +"kznid"+kznid+"actplnid"+actplnid);
	if(yyid.trim().length>0){
		popupCommonErrorMsg(" This Loss Entry has WHY-WHY Analysis ");
    	return false;
	}else if(kznid.trim().length>0){
		popupCommonErrorMsg(" This Loss Entry has Kaizen ");
    	return false;
	}
	else if(actplnid.trim().length>0){
		popupCommonErrorMsg(" This Loss Entry has Action plan ");
    	return false;
	}
	
	if(formId.length > 0)
		processAjaxCalls("pcsLossEntryITC_delete.pcs",dataString,"remove_successCallBack","");

});


function remove_successCallBack(result) {
	alert(result.successData.msg);
	jQuery("#pcslossGrid").trigger("reloadGrid");
}

function frmPcsLosscmbPlosCellid_onSelect(record) 	{
	
	var dataStr="&cellId="+record.id;
	loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmPcsLoss",dataStr);
	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	reloadCombo("frmPcsLoss","cmbPlosLossreason","combo_Phenomena.pcs?cellId="+cellId+"&flid="+flid);
}


function frmPcsLoss_beforeSubmit() { 
	
	var cellId = jQuery("#frmPcsLoss input[id='cell']").val();
	if (cellId=="" || cellId==' ' || cellId=='undefined') {
		popupCommonErrorMsg(" Select JH");
		return false;
	}
	
	var losshour = getFieldValue('txLossHours');
	
	losshour = losshour.replace(':','');
	
	if(losshour <= 0 )
	{ 
		popupCommonErrorMsg(" Enter loss hours ");
		//alert('No Loss hours to save');
		return false;
	}
	
	
	
}

function frmPcsLosscmbPlosLossreason_onSelect(record) { 
	processAjaxCalls('getReasonLossNo.pcs',"&reasonId="+record.id,'reasonLossIdSuccess','reasonLossIdError');	
}

function reasonLossIdSuccess(result) { 
	jQuery('#cmbPlosLossid').combobox('setValue',result.lossId);
	var lossRes = "Loss Reason" + "  -  (UOM: " + result.lossUom + ")";
	jQuery('#lblLossReason').html(lossRes);
	disableField("frmPcsLoss", "cmbPlosLossid");
}
 
 // ----------------- Vignesh  ---------------------//
 
function frmPcsLoss_FuntLocHierarchy_SuccessCallBack(keyIds)
{	
	
	var factId = "";
	var sectId = keyIds.sectId;	
	var mode=jQuery("#hdnMode").val();	
	if(mode=="MODIFY"){
		var lossdate=jQuery("#hdnLossDate").val();
		//alert(lossdate);
		setFieldValue('dtePlosDate',lossdate);
		}
	reloadCombo("frmPcsLoss","cmbPlosCellid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);

	if (keyIds.cellId!="null")
		setFieldValue('cmbPlosCellid',keyIds.cellId);
	reloadCombo("frmPcsLoss","cmbPlosLossreason","combo_Phenomena.pcs?cellId="+keyIds.cellId+"&flid="+keyIds.flId);
   
	viewGrid();
	
	if ((screen.width >= 1200)  && (screen.colorDepth>12))
		jQuery('#dispFunctionalLoc').css('width',400);
	else {
		jQuery('#dispFunctionalLoc').css('width',300);
		//jQuery('#txtPlosLossdescription').css('width',180);
		//jQuery('#cmbPlosLossreason').css('width',180);
		jQuery('#tdFnln').attr('colspan',5);
	}
	setFunctionalLocWidth('frmPcsLoss','600px');
	
	setFieldValue('cmbPlosProdImpact','N');

}
 



 


 // ----------------- Vignesh  ---------------------//
// ----------------- Vignesh  ---------------------//

function FormatDatefromISD(date){
	let newDate = new Date(date);

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }

function lossDateEvt(dateField,date) { 
    
    var currentDate = getServerDateTime();
    var lossDate = FormatDatefromISD(date) ;//jQuery('#'+dateField).datebox("getValue");
       // AFTER:
//  var lossDate = (date !== undefined && date !== null) ? FormatDatefromISD(date) : jQuery('#'+dateField).datebox("getValue");
//    alert('lossDate'+lossDate);
    if (dateField=="dtePlosFromdate")
            lossDate = lossDate + jQuery('#spnPlosFromtime').spinner('getValue');
    else
            lossDate = lossDate + jQuery('#spnPlosTotime').spinner('getValue');
    
    
//    alert('lossDate After '+lossDate);
    //alert(convertStringToDate(lossDate));
    if(convertStringToDate(lossDate) > currentDate)
    {
            popupCommonErrorMsg(" Should Not Exceed Current Date Time");
            //fillWithCurrentDate(dateField);
         setTimeout(function(){ fillWithCurrentDate(dateField);},500);

      if(dateField=="dtePlosDate") {

                setTimeout(function(){ fillWithCurrentDate("dtePlosFromdate");},500);
         setTimeout(function(){ fillWithCurrentDate("dtePlosTodate");},500);
                    fillWithCurrentDate("spnPlosFromtime");
                    setFocusOnField("dtePlosdate");
            }else if (dateField=="dtePlosFromdate") {
                setTimeout(function(){ fillWithCurrentDate("spnPlosFromtime");},500);
                    //fillWithCurrentDate("spnPlosFromtime");
                    setFocusOnField("dtePlosFromdate");
            }
            else {
                
                    setTimeout(function(){ fillWithCurrentDate("spnPlosTotime");},250);

                //fillWithCurrentDate("spnPlosTotime");
                    setFocusOnField("dtePlosTodate");
            }
            
            return false;
    }else if (dateField=="dtePlosTodate") {
            var fromDate = jQuery('#dtePlosFromdate').datebox("getValue");
            var Strtodate = jQuery('#dtePlosTodate').datebox("getValue");
    //        alert( lossDate + fromDate );
            
    //        alert(  'Mainlossdate' + Mainlossdate + Strtodate );
            var Mainlossdate =  jQuery('#dtePlosDate').datebox("getValue");
                    
            
            if (convertStringToDate(Mainlossdate) < convertStringToDate(Strtodate))
            {   
                    //alert( ' Inside mainloss ');
                    popupCommonErrorMsg(" Todate should not be Greater then loss entery date ");
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
                    popupCommonErrorMsg(" Should Not Less than From Date");
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
    }else if (dateField=="dtePlosDate") {
var picked = FormatDatefromISD(date)
jQuery('#dtePlosFromdate').datebox('setValue', picked);
jQuery('#dtePlosTodate').datebox('setValue',  picked);
viewGrid();
clearValidationErrorMsg(dateField);
    }else
        clearValidationErrorMsg(dateField);        


}

function frmPcsLosscmbPlosShiftid_onSelect(record) {
	//viewGrid();
	//processAjaxCalls('getShiftEndTime.pcs',"&shiftId=",'shiftStartTimeSuccess','shiftStartTimeError');
//---///	
	//processAjaxCalls('getShiftStarEndTime.pcs',"&shiftId="+record.id,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
	//processAjaxCalls('getShiftStarEndTime.pcs',"&shiftId="+record.id,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
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
	
	if(convertStringToDate(fromdate) < convertStringToDate(actSftStartTime))
	{   jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
		return false;
	}
	
		
/*	if (parseInt(frTime.replace(':','')) < parseInt(actSftStartTime.replace(':','')) ) { 
		jQuery('#spnPlosFromtime').spinner('setValue',actSftStartTime);
		return false;
	}
	
	/* if (parseInt(toTime.replace(':','')) > parseInt(actSftEndTime.replace(':','')) ) { 
		jQuery('#spnPlosTotime').spinner('setValue',actSftEndTime);
		return false;
	} */
	
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
	}
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
/* 			var tim1=getFieldValue("spnPlosFromtime");
			var tim2=getFieldValue("spnPlosTotime");
			var ary1=tim1.split(':'),ary2=tim2.split(':');
			var minsdiff=parseInt(ary2[0],10)*60+parseInt(ary2[1],10)-parseInt(ary1[0],10)*60-parseInt(ary1[1],10);
			//alert('oDiff.minutes'+oDiff.minutes);
			calTime = String(100+Math.floor(calTime/60)).substr(1);
			//alert('calTime2'+calTime);
			var mins =String(100+minsdiff%60).substr(1);
			calTime = calTime +':'+mins;
 */			
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
//	viewGrid();
}

function  getPCSShift(record)
{
	jQuery('#cmbPlosShiftid').combobox('setValue',record.shift);
	//viewGrid();	
	//processAjaxCalls('getShiftStarEndTime.pcs',"&shiftId="+record.shift,'shiftStartEndTimeSuccess','shiftStartEndTimeError');
}

	
	/*function shiftStartEndTimeSuccess(result) {
		//alert(result.endTime);
		jQuery('#hdnSftStartTime').val(result.startTime);
		jQuery('#hdnSftToTime').val(result.endTime);
		//alert(jQuery('#hdnSftToTime').val());
		//alert('shiftStartEndTimeSuccess'+result.startTime);
		//alert(result.startTime);
	}*/
	function shiftStartTimeSuccess(result) {
		//alert(result.endTime);
		//jQuery('#hdnSftStartTime').val(result.startTime);
		jQuery('#hdnSftToTime').val(result.endTime);
	//	alert(result.endTime);
		//alert(jQuery('#hdnSftToTime').val());
		//alert('shiftStartEndTimeSuccess'+result.startTime);
		//alert(result.startTime);
	}
	 
	//------- - Vignesh ---------------------------------------------------------------------------//
	
// 	function viewGrid() {
		
// 		var ds = "?&fromdate="+jQuery('#dtePlosFromdate').datebox('getValue');
// 		ds+="&shiftid="+jQuery('#cmbPlosShiftid').combobox('getValue');

// 		var flid = jQuery("#frmPcsLoss input[id='flid']").val();
// 	//	var todate = jQuery('#dtePlosTodate').datebox('getValue');
// 		 // chnaging to dtePlosDate ---  dtePlosTodate -- 
// 		var todate = jQuery('#dtePlosDate').datebox('getValue');
// 		if(todate.length<11) 
// 			todate = '0'+todate;
// 		ds+="&flid="+flid;
// 		ds+="&todate="+todate;
		
// 		alert(" full url " + ds);
		
// 		processGridnew("pcsLoss_input.pcs",ds, "pcslossGrid", "pcslossPager","","pcsdblClick","","pcsloss_loadComplete");

// 	}
	function getFlid() {
  return (
    (jQuery("#frmPcsLoss input[id='flid']").val() || '').trim() ||
    (jQuery("#frmEmpPage input[id='flid']").val() || '').trim() ||
    (jQuery("#hdnFlid").val() || '').trim()
  );
}

// In viewGrid():


function viewGrid() {
  var fromdate = jQuery('#dtePlosFromdate').datebox('getValue');
  var todate   = jQuery('#dtePlosTodate').datebox('getValue');

  // Robust zero-pad for day if user typed 7-Sep-2025
  fromdate = normalizeDMY(fromdate || '');
  todate   = normalizeDMY(todate   || '');

  //var flid  = jQuery("#frmPcsLoss input[id='flid']").val();
  var flid = getFlid();
  var shift = jQuery('#cmbPlosShiftid').combobox('getValue');

  var ds = "?&fromdate=" + encodeURIComponent(fromdate) +
           "&shiftid="   + encodeURIComponent(shift) +
           "&flid="      + encodeURIComponent(flid) +
           "&todate="    + encodeURIComponent(todate);

  // debug
  // alert(" full url " + ds);

  processGridnew("pcsLoss_input.pcs", ds, "pcslossGrid", "pcslossPager", "", "pcsdblClick", "", "pcsloss_loadComplete");
}

// function viewGrid() {
//   var fromdate = jQuery('#dtePlosFromdate').datebox('getValue');
//   var todate   = jQuery('#dtePlosTodate').datebox('getValue');

//   // Zero-pad day if needed (7-Sep-2025 -> 07-Sep-2025)
//   fromdate = normalizeDMY(fromdate || '');
//   todate   = normalizeDMY(todate   || '');

//   var shift = jQuery('#cmbPlosShiftid').combobox('getValue') || '';
//   var flid  = (jQuery("#frmPcsLoss input[id='flid']").val() || '').trim();
//   var mode  = jQuery("#hdnMode").val() || '';           // <— added

//   // Build URL WITHOUT flid first
//   var ds = "?&fromdate=" + encodeURIComponent(fromdate) +
//            "&shiftid="   + encodeURIComponent(shift) +
//            "&todate="    + encodeURIComponent(todate);

//   // Append flid ONLY when modifying and it has a value
//   if (mode === 'MODIFY' && flid) {
//     ds += "&flid=" + encodeURIComponent(flid);
//   }

//   alert(" full url " + ds);
//   processGridnew("pcsLoss_input.pcs", ds, "pcslossGrid", "pcslossPager", "", "pcsdblClick", "", "pcsloss_loadComplete");
// }

// 	function viewGrid() {
//   var fromdate = jQuery('#dtePlosFromdate').datebox('getValue');
//   var todate   = jQuery('#dtePlosTodate').datebox('getValue');

//   fromdate = normalizeDMY(fromdate || '');
//   todate   = normalizeDMY(todate   || '');

//   var mode = jQuery("#hdnMode").val() || '';

//   // ✅ pick the right FLID and avoid server session fallback
//   var flid = jQuery.trim(
//       mode === 'MODIFY'
//         ? (jQuery('#hdnFlid').val() ||
//            jQuery("#frmPcsLoss input[id='flid']").val())
//         : (jQuery("#frmPcsLoss input[id='flid']").val() ||
//            jQuery("#frmEmpPage input[id='flid']").val())
//   ) || '-';  // send '-' instead of empty to block session fallback

//   var shift = jQuery('#cmbPlosShiftid').combobox('getValue');

//   var ds = "?&fromdate=" + encodeURIComponent(fromdate) +
//            "&shiftid="   + encodeURIComponent(shift) +
//            "&flid="      + encodeURIComponent(flid) +
//            "&todate="    + encodeURIComponent(todate);

//   // debug
//    alert(" full url " + ds);

//   processGridnew("pcsLoss_input.pcs", ds, "pcslossGrid", "pcslossPager", "", "pcsdblClick", "", "pcsloss_loadComplete");
// }

	//------- - Vignesh ---------------------------------------------------------------------------//
	

	
	function pcsdblClick(id){ 
	//	alert(id);
		var FromDate = jQuery("#pcslossGrid").jqGrid('getCell',id,3);
	//	alert("FromDate"+FromDate);
		var ToDate = jQuery("#pcslossGrid").jqGrid('getCell',id,5);
		
		var frmTime = jQuery("#pcslossGrid").jqGrid('getCell',id,4);
	//	alert("frmTime"+frmTime);
		var toTime = jQuery("#pcslossGrid").jqGrid('getCell',id,6);
	//	alert("toTime"+toTime);
		var lossTime = jQuery("#pcslossGrid").jqGrid('getCell',id,7);
	//	alert("lossTime"+lossTime);
		
		var lossId = jQuery("#pcslossGrid").jqGrid('getCell',id,8);
		var lossReasonId = jQuery("#pcslossGrid").jqGrid('getCell',id,10);
		var lossDesc = jQuery("#pcslossGrid").jqGrid('getCell',id,12);
		var tradeId = jQuery("#pcslossGrid").jqGrid('getCell',id,13);	
		var prodImpact = jQuery("#pcslossGrid").jqGrid('getCell',id,15);
		var prodImpactQty = jQuery("#pcslossGrid").jqGrid('getCell',id,16);	
		var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2); 		
		var detailid = jQuery("#pcslossGrid").jqGrid('getCell',id,1);
		var yyid = jQuery("#pcslossGrid").jqGrid('getCell',id,20);
		var kznid = jQuery("#pcslossGrid").jqGrid('getCell',id,22);
		var actplnid = jQuery("#pcslossGrid").jqGrid('getCell',id,24);
        var equId=jQuery("#pcslossGrid").jqGrid('getCell',id,17);
        var detectedby=jQuery("#pcslossGrid").jqGrid('getCell',id,18);
        var mchId=jQuery("#pcslossGrid").jqGrid('getCell',id,25);
//alert(yyid+" "+kznid +"kznid"+actplnid);
        var EmpId=jQuery("#pcslossGrid").jqGrid('getCell',id,26);

		
		jQuery('#hdnPlosKeyid').val(plosKeyid);
		jQuery('#hdnyyid').val(yyid);
		jQuery('#hdnkznid').val(kznid);
		jQuery('#hdnactplnid').val(actplnid);
		setFieldValue('cmbPlosProdImpact',prodImpact);
		setFieldValue('cmbDetectedBy',EmpId);
		//setFieldValue('cmbDetectedBy',EmpId);

		if (prodImpact=="Y")
			enableFields("txtPlosProdImpQty");
		else
			//enableFields("txtPlosProdImpQty");
			disableField("frmPcsLoss", "txtPlosProdImpQty");
		
		jQuery('#txtPlosProdImpQty').val(prodImpactQty);
		jQuery('#txLossHours').val(lossTime);
		jQuery('#txtPlosLosstime').val(lossTime);
		jQuery('#txtPlosLossdescription').val(lossDesc);	
		setFieldValue('cmbPlosLossreason',lossReasonId);
		setFieldValue('cmbPlosTradeid',tradeId);
		setFieldValue('cmbPlosLossid',lossId);
		setFieldValue('cmbPlosProdImpact',prodImpact);
		setFieldValue('cmbPlosEquipment',mchId);		
		setFieldValue('dtePlosFromdate',FromDate);
		//setFieldValue('spnPlosFromtime',frmTime);	
		jQuery('#spnPlosFromtime').spinner('setValue',frmTime);
		setFieldValue('dtePlosTodate',ToDate);
		//setFieldValue('spnPlosTotime',toTime);
		jQuery('#spnPlosTotime').spinner('setValue',toTime);
		//setFocusOnField('spnPlosFromtime');
		fngetTotalLossTime();
	}
		
	function frmPcsLoss_successsCallback(result) {
		jQuery('#hdnPldetailsId').val(result.pldetailsId);
		jQuery('#hdnPlosKeyid').val('');
		//alert( '2' + result.pldetailsId);
		jQuery('#txtPlosProdImpQty').val('');
		jQuery('#txtPlosLossdescription').val('');
		jQuery('#txLossHours').val('0');
		jQuery('#txtPlosLosstime').val('0');
		
		jQuery('#lblLossReason').html('Loss Reason');
		
		fillWithCurrentDate("spnPlosFromtime");
		fillWithCurrentDate("spnPlosTotime");

		jQuery('#cmbPlosLossreason').combobox('clear');
		jQuery('#cmbPlosTradeid').combobox('clear');
		jQuery('#cmbPlosLossid').combobox('clear');
		setFieldValue('cmbPlosProdImpact','N');
		disableField("frmPcsLoss", "txtPlosProdImpQty");
		
		setFocusOnField('spnPlosFromtime');
				
		viewGrid();
	}
	
	function frmPcsLoss_exceptionCallback(result) {
		//viewGrid();
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
	  
	if ((jQuery("#hdnMode").val() || '') !== 'MODIFY') setFieldValue('flid','');

	  
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
function actionplanBtn(id, options, rowObject){
	return '<input type="button" id="btnactionplan_'+options.rowId+'_'+options.pos+'" name="btnActionplanGrid_'+options.rowId+'_'+options.pos+'" onclick="showwactionplan('+options.rowId+')"  style="width:80px;  height:23px;"   class="easyui-button" value="Action plan"/>';
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
	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2);
	//alert(plosKeyid);
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var problem = jQuery("#pcslossGrid").jqGrid('getCell',id,11) ;
	var lossDesc = jQuery("#pcslossGrid").jqGrid('getCell',id,12);
	if (lossDesc.trim().length>1)
		problem += " (" + lossDesc + ")";
	var refDocDate = jQuery('#dtePlosDate').datebox('getValue');
	//navigateToNextForm('whywhy_input.why?&whywhyRefDocID='+plosKeyid,'Why Why Analysis');
	var forwardData = "";
	var persistentData ="";
	
	openWhyWhy("divWhyWhy",false,plosKeyid,"PCS",flid, refDocDate, problem, "create");
}

function showwkaizen(id){
	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2);
	//alert(plosKeyid);
	//navigateToNextForm('kaizen_input.kaizen?&kznKeyid=&kznStatus=PENDING&mode=modify&closeOnSave=true&filterButton=false','Kaizen');
	
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var cell = jQuery("#frmPcsLoss input[id='cell']").val();;
	var date = jQuery('#dtePlosDate').datebox('getValue');// ---changing 20- 22 vignesh
	var proposedID = jQuery("#pcslossGrid").jqGrid('getCell',id,22);
	var persistentData = {"flid":flid,"flid":flid,"date":date,"mode":"create"};
	var forwardData = {"flid":flid,"flid":flid,"date":date,"mode":"create"};;
	//alert(proposedID);
	var dataStr ="?&refDocNo="+plosKeyid+"&flid="+flid+"&refDocType=LOSS&Keyid="+proposedID+"&AccSingle=N";
	navigateToNextForm("KaizenBankSuggestion_input.kznbnk"+dataStr,"Kaizen Suggestion",forwardData,persistentData);
}

function showwactionplan(id){
	var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2);
	var flid = jQuery("#frmPcsLoss input[id='flid']").val();
	var date = jQuery('#dtePlosDate').datebox('getValue');
	var proposedID = jQuery("#pcslossGrid").jqGrid('getCell',id,20);
	var mainTask = jQuery("#pcslossGrid").jqGrid('getCell',id,"LossDescription");
	
	openActionPlan("Actionplane",plosKeyid,"LOSS",flid,mainTask,plosKeyid,date);
}

function Actionplane_onClose(){
	jQuery("#pcslossGrid").trigger("reloadGrid");
    return true;
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

<form id="frmPcsLoss">
<div style="padding-left:10px;padding-top:10px;">
<table  style="width: 99%;">
<tr style="width: 98%;">
<td id="tdFnln" style="width: 50%;">
	<div  id="pcsLossLocnDivLayer" class="easyui-paddingbfpx" style=" ">
			<div  class="easyui-paddingbfpx" id="frmPcsLossFuntKeyIds">
					<%-- <input type="hidden" id="factory" name="cmbPlosFactoryid" value="${requestScope.factId}"  ></input> --%>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbPlosSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbPlosMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbPlosFlid" value="${requestScope.flid}"  ></input>
				</div>
				<div id="pcsLossLocation" style=" "></div>
			</div>
</td>
<td  style="padding-left:10px; margin-left:10px; width: 20%;">
 		<label class="mandatory-lbl"> JH </label>
 		<div>
	   	<input type="text" class="easyui-combobox"  id="cmbPlosCellid" name="cmbPlosCellid" value="${requestScope.QtmTlIntrejectionmst.qirmCellid}" style="width: 240px; margin-left: -90px;"/>
	   	</div>
 </td>




 <td style="width: 10%;padding-left:10px; margin-left:13px;">
 		<label> Date </label>
 		<div style="width: 15%;" >
	   	<input id="dtePlosDate" name="dtePlosDate" class="easyui-datebox"  style="width: 90px;" value="${requestScope.date}"/>
	   	</div>
 </td>
 
 <td style="display: none;">
 	<label> Shift </label>
    	<div>
	    	<input  class="easyui-combobox" id="cmbPlosShiftid"  name="cmbPlosShiftid" readonly="readonly"  style="width: 50px;"  value="${requestScope.shift}"/>
	    </div>
</td>
<td valign="left" style="padding-top:13px; padding-left:10px; margin-left:-10px;" colspan="2">	
	  <input type="button" class="easyui-button" id="btnView"	name="btnView" value="View" style="width : 60px;height:22px;"/>
</td>
</tr>
</table>

<div>
	<span style="padding-left:0px;"> 	<label class="mandatory-lbl"> From Time  </label> </span> 	
	<span style="padding-left:84px;"> 	<label class="mandatory-lbl"> To Time  </label> 	</span>
	<span style="padding-left:135px;"> 	<label> Hours  </label> 	</span>
	<span style="padding-left:55px;"> 	<label class="mandatory-lbl" id="lblLossReason" > Loss Reason  </label></span> 	
	<span style="padding-left:135px;"> 	<label> Loss Name  </label> 	</span>
	<span style="padding-left:190px;"> 	<label>Equipment</label></span>
</div>
<div>
	<span style="padding-left:0px;">
		<input id="dtePlosFromdate" name="dtePlosFromdate" class="easyui-datebox"   style="width: 90px;" value="${requestScope.date}"/>
		<input  id="spnPlosFromtime" name="spnPlosFromtime"  onblur="fngetTotalLossTime()" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /> 
	</span>
	<span style="">
		<input id="dtePlosTodate" name="dtePlosTodate" class="easyui-datebox"   style="width: 90px;" value="${requestScope.date}"/>
		<input  id="spnPlosTotime" name="spnPlosTotime"  onblur="fngetTotalLossTime()" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /> 
	</span>
	<span style="padding-left:30px;display: none;">
		<input type="text" class="easyui-text"  id="txtPlosLosstime"   name="txtPlosLosstime"  value=""  style="width:80px;text-align: right;" readonly="readonly" maxlength="5" >
	</span>
	<span style="padding-left:30px;">
		<input type="text" class="easyui-text"  id="txLossHours"   name="txLossHours"  value=""  style="width:80px;text-align: right;" readonly="readonly" maxlength="5" >
	</span>
	<span style="padding-left:15px;">
		<input type="text"  class="easyui-combobox"  id="cmbPlosLossreason"   name="cmbPlosLossreason"  value=""  style="width:200px; height:22px;text-transform:uppercase;" />
	</span>
	<span style="padding-left:15px;">
		<input type="text"  class="easyui-combobox"  id="cmbPlosLossid"   name="cmbPlosLossid"  value=""  style="width:240px; height:22px;text-transform:uppercase;" />
	</span>
	
	<span style="padding-left:15px;">
	<input type="text"  class="easyui-combobox"  id="cmbPlosEquipment"   name="cmbPlosEquipment"  value="" style="width:140px; /* height:12px */;text-transform:uppercase;" />
	</span>
	
</div>

<div>
	<span style="padding-left:0px;">
 		<label > Loss Description </label> 	
	</span>
	<span style="padding-left:245px;">
 		<label class="mandatory-lbl"> Trade  </label> 
	</span>
	<span style="padding-left:140px;">
 		<label> Prd.Impact  </label> 	
	</span>
	<span style="padding-left:10px;">
 		<label> Prd.Imp.Qty (TON)  </label> 	
	</span>
		
<span style="padding-left:10px;">
 		<label class="mandatory-lbl">Detected By</label>
 		</span>

<div>
	<input type="text" class="easyui-text"  id="txtPlosLossdescription"   name="txtPlosLossdescription"  value=""  onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" style="width:310px;" maxlength="500" >
	<span style="padding-left:28px;">
	<input type="text"  class="easyui-combobox"  id="cmbPlosTradeid"   name="cmbPlosTradeid"  value=""  style="width:170px; /* height:22px; */ text-transform:uppercase;" />
	</span>
	<span style="padding-left:10px;">
		<select id='cmbPlosProdImpact' name='cmbPlosProdImpact'  panelHeight=60px; class="easyui-combobox" style='width :60px;/* height:20px; */' > 
			 <option value='Y'>YES</option>
			 <option value='N'>NO</option>
		</select>
	</span>

	<span style="padding-left:10px;">
		<input type="text" class="easyui-text"  id="txtPlosProdImpQty"   name="txtPlosProdImpQty"  value=""  style="width:100px;text-align: right;" maxlength="6" >
</span>
 		<span style="padding-left:10px;padding-top:0px; ">
	   	<input type="text" class="easyui-combobox" style="width:250px;" id="cmbDetectedBy" name="cmbDetectedBy"/>
	   	</span>
 

<span style="padding-left:10px;">
		<input type="button" class="easyui-button" id="btnSave"	name="btnSave" value="Save" style=" width : 50px;height:20px;"/>
	
		<input type="button" class="easyui-button" id="btnLossDelete"	name="btnLossDelete" value="Delete" style="margin-left:10px; width : 50px;height:20px;"/>
	</span>
	
</div>
<div style="margin-top:10px;">
<table id='pcslossGrid'>
<tr><td></td></tr>
</table>
<div id="pcslossPager"></div>
</div>
</div>
<input type="hidden" id="hdnSftStartTime" name="hdnSftStartTime" value="">
<input type="hidden" id="hdnSftToTime" name="hdnSftToTime" value="">
<input type="hidden" id="hdnPlosKeyid" name="hdnPlosKeyid" value="">
<input type="hidden" id="hdnPldetailsId" name="hdnPldetailsId" value="">
<input type="hidden" id="hdnFlid" name="hdnFlid" value="${requestScope.flid}">
<input type="hidden" id="hdnLossDate" name="hdnLossDate" value="${requestScope.date}">
<input type="hidden" id="hdnToDate" name="hdnToDate" value="${requestScope.date}">
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}">
<input type="hidden" id="hdnyyid" name="hdnyyid" value="">
<input type="hidden" id="hdnkznid" name="hdnkznid" value="">
<input type="hidden" id="hdnactplnid" name="hdnactplnid" value="">
</div>
</form>