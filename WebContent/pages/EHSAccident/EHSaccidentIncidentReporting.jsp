<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	
	jQuery(document).ready(function(){	
		initialiseForm('frmAccidentIncident');	    	  
	    jQuery('#submitForm').val('frmAccidentIncident'); 
	    var mode = jQuery('#mode').val();
	 
//	  alert("mode"+mode);
	    
	   // jQuery("#yyTab").hide();
	    jQuery('#frmAccidentIncident .easyui-text').css('text-transform', 'uppercase');
	    jQuery('#frmAccidentIncident textarea').css('text-transform', 'uppercase');	
	   
		formatDateBox('dtesinmOccurredDate','dd-MMM-yyyy');
		formatDateBox('dtesinmReportedDate','dd-MMM-yyyy');
		fillComboBox("frmAccidentIncident","cmbsinmOccurredShift","combo_shift.brdn" );
		fillComboBox("frmAccidentIncident","cmbsinmReportedShift","combo_shift.brdn" );	
		fillComboBox("frmAccidentIncident","cmbsinmDepartment","sectionCombo.commonFilter" );
		fillComboBox("frmAccidentIncident","cmbsinmReportedBy","employee.commonFilter");
		fillComboBox("frmAccidentIncident","cmbsinmAccidentType","IncidentTypeCombo.ehsaccIncRpt");
	//	fillComboBox("frmAccidentIncident","cmbsinmAnalysedShift","combo_shift.brdn");
	//	fillComboBox("frmAccidentIncident","cmbsinmAnalysedBy","employee.commonFilter");
		//fillComboBox("frmAccidentIncident","cmbsinmExactType","IncidentTypeCombo.ehsaccIncRpt");

		// imageUpload(jQuery( "#dlgImgPresent" ),'ImageUpload.commonFilter','dlgImgPresent',"imgPresentimage","imgPresentImgFilename");
		// imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgAfterimage","imgAfterImgFilename");
		//fillWithCurrentDateIncident('dtesinmOccurredDate');
		//fillWithCurrentDateIncident('dtesinmReportedDate');
		fillWithCurrentDate('spnsinmOccurredtime');
		fillWithCurrentDate('spnsinmReportedtime');
		
		
		
		// formatDateBox('dtesinmOccurredDate','dd-MMM-yyyy');
		 //formatDateBox('dtesinmReportedDate','dd-MMM-yyyy');
		
		numericTextBox('txtsinmDirectcost');
		//alert("The Mode"+mode);
	    if(mode=='Create')
		{   
			var keyId =jQuery('#hdnsinmKeyid').val();
			//alert("keyId"+keyId);
	  		if(keyId == null || keyId == ' ' || keyId == '')
		  	{
	  			//disableUIButton('btnFileMgrSafety');
		  		fillWithCurrentDate('dtesinmOccurredDate');
				fillWithCurrentDate('dtesinmReportedDate');
				fillWithCurrentDate('spnsinmOccurredtime');
				fillWithCurrentDate('spnsinmReportedtime');
				alert("entered")
				var now = new Date();
				var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();

				jQuery('#spnsinmOccurredtime').timespinner('setValue', time);
				jQuery('#spnsinmReportedtime').timespinner('setValue', time);
			
				fillDefaultShift();
		  	}
	  		else
		  		{
	  				  			setDatenTimeFlag('dtesinmOccurredDate','spnsinmOccurredtime');
		
				
				setDatenTimeFlag('dtesinmReportedDate','spnsinmReportedtime');
		  		}
	  		spinnerKeyPress('spnsinmOccurredtime');
			spinnerKeyPress('spnsinmReportedtime');
			spinnerChange('spnsinmOccurredtime','occuredTimeSpnEvt');
			spinnerUp('spnsinmOccurredtime','occuredTimeSpnEvt');
			spinnerDown('spnsinmOccurredtime','occuredTimeSpnEvt');
			spinnerChange('spnsinmReportedtime','reportedTimeSpnEvt');
			spinnerUp('spnsinmReportedtime','reportedTimeSpnEvt');
			spinnerDown('spnsinmReportedtime','reportedTimeSpnEvt');
		
	    }
	    else{
	    	var reportedTime = jQuery('#hdnReportTime').val();
	    	var occuredTime = jQuery('#hdnOccurTime').val();
  			
	    	
	    	jQuery('#spnsinmOccurredtime').timespinner('setValue', occuredTime);
			jQuery('#spnsinmReportedtime').timespinner('setValue', reportedTime);
		

	    	//alert("insid ethe else");
	    	//jQuery("#btnExcelView").attr("disabled",false).removeClass("ui-state-disabled");
	    	//jQuery("#btnFileMgrSafety").hide();
	    }
	    //setDatenTimeFlag('dtesinmReportedDate','spnsinmReportedtime');
	    //setDatenTimeFlag('dtesinmOccurredDate','spnsinmOccurredtime');
	

		var keyid = jQuery('#txtsinmKeyid').val();
		//alert("keyid::::::!@!#!@!2"+keyid);
		var locnId = jQuery("#frmAccidentIncident input[id='location']").val();
		var factId = jQuery("#frmAccidentIncident input[id='factory']").val();
		var sectionId = jQuery("#frmAccidentIncident input[id='section']").val();
		var cellId = jQuery("#frmAccidentIncident input[id='cell']").val();
		var machId = jQuery("#frmAccidentIncident input[id='machine']").val();
		var flid = jQuery("#frmAccidentIncident input[id='flid']").val();
      var dataStr = "&locnId="+locnId+"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid+"&disable=N";
		
		var keyid = jQuery('#txtsinmKeyid').val();
		//alert("keyid===="+keyid.length);
		
		 if(keyid==null)
			{
			//alert("in side the if");
			loadActionGrid("empdtlsGrid","pager2","colModelEmployeeDtls","SheEmployeeAdd_input.ehsaccIncRpt","","txtsinmKeyid");
			
			}
		else{
			//alert("INSIDE THE EMPLOYEE");
			//processGridnew("SheEmployeeAdd_input.ehsaccIncRpt","q=2&incidentKey="+keyid,"empdtlsGrid","pager2","","","");
			loadActionGrid("empdtlsGrid","pager2","colModelEmployeeDtls","SheEmployeeAdd_input.ehsaccIncRpt"," ","hdnsinmKeyid");

		}
		loadFunctionalLocation("sinmfunLocation","functionalLoc.ehsaccIncRpt","sinmfunLocationValues","frmAccidentIncident",dataStr);
		

		 fileManagerPopUp(jQuery("#txtsinmKeyid").val(),"INC","frmAccidentIncident","btnFileMgrSafety","FileMgrSafety");

		 if(screen.width <= 1024){
				jQuery('#actionPlanSubHdr').css('width','85%');
			}
			else if(screen.width <= 1280){
				jQuery('#actionPlanSubHdr').css('width','91%');
			}	
			else{
				jQuery('#actionPlanSubHdr').css('width','99%');
			}

		 var status = getFieldValue('hdnstatus');
			//alert('status' + status);
			if(status  == 'C')
			{
				//setFieldValue('cmbsinmStatus' ,"C");
				jQuery('#cbosinmStatus').val("C");
			}else
			{
				jQuery('#cbosinmStatus').val("P");
			}


				
	});	
	
	
	jQuery("#btnRiskAsses").click(function(){
		var keyid = jQuery('#txtsinmKeyid').val();
		//alert(keyid);
		//alert(keyid.length);
		if(keyid.length==0 )
		{
			popupCommonErrorMsg("Please Save Incident Recording");
			return false;
		}
		else{
			navigateToNextForm("RiskAssesment_view.risk");
		}
		
		
	}
    );
	
	jQuery("#txtsinmAccidentDesc").keypress(function(){
		 var tval = jQuery('#txtsinmAccidentDesc').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=1000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmImmediateAction").keypress(function(){
		 var tval = jQuery('#txtsinmImmediateAction').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmCarecommended").keypress(function(){
		 var tval = jQuery('#txtsinmCarecommended').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	
	
	
	jQuery("#btnFileMgrSafety").click(function(){
		 var mode = jQuery('#mode').val();
		 if(mode=="View")
			 {
			    return false;
			    
			 }
		 else{
			var documentNo =jQuery("#txtsinmKeyid").val();
	 		
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"INC","","","");
				
			}
			else
	 			alert("Incident No. should be selected to view FileManager");
		 }
			
		});
	jQuery("#imgAddEmp").click(function(){
		
		 var mode = jQuery('#mode').val();
	
		 if(mode=="View")
			 {
			    return false;
			    
			 }
		 else{
		jQuery("#hdnCheckFlag").val("Y");
		
		var val1 =  validation('cmbsinmAccidentType',"Select Injury Category");
		var val2 =  validation('txtsinmAccidentDesc',"Enter Description");
		var val3 = validation('txtsinmArea',"Enter Area");
		
		if(val1== false || val2==false || val3==false){
			return false;
		}
		else
			{				
				var dataString = '?q=2';
				var IncId = jQuery('#hdnsinmKeyid').val();
				
				var cellId = jQuery("#frmAccidentIncident input[id='cell']").val();
				  if(cellId.length==0)
					  {
					     popupCommonErrorMsg("Select JH");
					     return false;
					  }
				//alert("IncId:::"+IncId);
			/*	if(IncId.length==0)
					{
					saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?q=2&saveMode=ADDEMP');
					}*/
				if(IncId != null && IncId != '' && IncId != ' ')
				{	
						dataString += '&incdtKeyId='+IncId;
				}			
				LoadPopUp("divAddEmployee","SheEmployeeAdd_input.ehsaccIncRpt"+dataString, true,"920px","600px","-10px","10%", "multiSelectOk_Callback","Incident Employee");
			
			}
			 }
});
	
	
	function divAddEmployee_afterClose()
	{
		//alert(1);
		//jQuery("#empdtlsGrid").trigger("reloadGrid");
		
		loadActionGrid("empdtlsGrid","pager2","colModelEmployeeDtls","SheEmployeeAdd_input.ehsaccIncRpt","hdnsinmKeyid","hdnsinmKeyid");
	}
	jQuery("#imgAddActionPlan").click(function(){		
		var flid = jQuery("#frmAccidentIncident input[id='flid']").val();
		var keyid = jQuery('#txtsinmKeyid').val();
		var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
		
		//LoadPopUp("divAddActionPlan","SheActionPlanAdd_input.accIncRpt", true,"85%","80%","10%","7%", "ADDACTIONPLAN_Callback","Action Plan");
		openActionPlan("divAddActionPlan",keyid,"AIR",flid,"",keyid,analysedDate,"");
		//openActionPlan("abnActionPlan",keyid,"abn",flid,mainTask,keyid,abnDetectDate,apMode);
	});
	
	function divAddActionPlan_afterClose()
	{
		
		jQuery("#actionPlanGrid").trigger("reloadGrid");
		
	}
	
	jQuery("#btnYYAccident").click(function(){
		saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?q=2&saveMode=YY');
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
	function button_AddEmp(id, options, rowObject)
	{					
		var rowId = options.rowId;
		return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
	}
/////////////////////////////////before hidding/////////////////
	function AddEmpPopUp()
	{					
		LoadPopUp("divAddEmployee","SheEmployeeAdd_input.ehsaccIncRpt", true,"400px","300px","0px","20%", "multiSelectOk_Callback","Incident Employee");
	}
////////////////now i am opened/////////////	
	function removeOperator(rowId){	
	   // alert("rowId"+rowId);
	   
		var rowData = jQuery("#empdtlsGrid").jqGrid('getRowData',rowId);
		var keyId = rowData.txtSiemKeyid;
		//alert("keyid"+keyId);
		var conFdelete = confirm("Do  To Delete");
		alert(conFdelete);
		if(conFdelete){
			if( keyId != null && keyId.length > 0  ){	
				processAjaxCalls("SheEmployeeAdd_Delete.ehsaccIncRpt","&incEmpId="+keyId+"&rowId="+rowId , 'IncEmpDelete_successCallBack','IncEmpDelete_errorCallBack');
				//jQuery("#batchGrid").delRowData(rowId);
			}
		}
			 
	}
	
	function IncEmpDelete_successCallBack(result){
		if(result.tpmException){
			var rowId=result.rowId;
			alert(result.tpmException);
			//var rowData = jQuery("#batchGrid").jqGrid('getRowData',rowId);
			SetTimeout(function()
			{
			jQuery("#empdtlsGrid").delRowData(rowId);
			},1000);
			
		}
	}
	function validation(fieldId,msg)
	{
		
		var value = getFieldValue(fieldId);
		
		if(value == '' || value == null || value == ' '){
			showValidationErrorMsg(fieldId,msg);
			return false;
		}
		else
			{
			clearValidationErrorMsg(fieldId);
			}
	}


	function IncEmpDelete_successCallBack(result){
		if(result.tpmException){
			var rowId=result.rowId;
			alert(result.tpmException);
			//var rowData = jQuery("#batchGrid").jqGrid('getRowData',rowId);
			jQuery("#empdtlsGrid").delRowData(rowId);
			
		}
	}

	jQuery( "#btnImgPresentClear" ).click(function() {
		jQuery('#imgPresentimage').attr('src', "");
		jQuery('#imgPresentImgFilename').val("");

		});

	jQuery( "#btnImgAfterClear" ).click(function() {
		jQuery('#imgAfterimage').attr('src', "");
		jQuery('#imgAfterImgFilename').val("");

		});
	
	function button_delEmp(id, options, rowObject)
	{					
		var rowId = options.rowId;
		return '<input type="button" id="remov" class="grdButton" value="" onclick="removeEmp(\''+rowId + '\');"/>';
	}
	function removeEmp(rowId){	
		 var mode = jQuery('#mode').val();
		 if(mode=="View")
		 {
		   return false;
		   
		 }else
			 {
	 	var rowData = jQuery("#empdtlsGrid").jqGrid('getRowData',rowId);
		var keyId = rowData.txtSineKeyid;
		
		//alert("keyid"+keyId);
		var conFdelete = confirm("Do You Want To Delete");
	//	alert("keyid"+conFdelete);
		if(conFdelete){
			if( keyId != null && keyId.length > 0  ){	
				processAjaxCalls("SheEmployeeAdd_Delete.ehsaccIncRpt","&incEmpId="+keyId+"&rowId="+rowId , 'empDelete_successCallBack','empDelete_errorCallBack');
				//jQuery("#batchGrid").delRowData(rowId);
			}
			 setTimeout(function() {
				  	jQuery("#empdtlsGrid").trigger("reloadGrid");
				  	},200);
		}
			 }
	}
	function empDelete_successCallBack(result){
		if(result.tpmException){
			var rowId=result.rowId;
			alert(result.tpmException);
			//var rowData = jQuery("#batchGrid").jqGrid('getRowData',rowId);
			jQuery("#empGrid").delRowData(rowId);	
			jQuery("#empdtlsGrid").delRowData(rowId);		
		}
	}
	/*function divAddActionPlan_onClose()
	{
		//loadActionGrid("actionPlanGrid","actionPlanPager","colModelActionPlan");
		//return true;
	}*/
	function ADDACTIONPLAN_Callback()
	{
		 var mode = jQuery('#mode').val();
		 
		if(mode == 'view')
		{
			readOnlyFields('txtSacpDescription');		
			readOnlyFields('cmbSacpResponsiblity');	
			readOnlyFields('cmbSacpCompletedBy');		
			readOnlyFields('txtSacpRespDept');				
			readOnlyFields('txtSacpRemarks');	
			readOnlyFields('dteSacpTargetDate');		
			readOnlyFields('dteSacpCompletedDate');	
			disableUIButton('btnAddActionPlan');
			disableUIButton('btnClearActionPlan');
				
		}
	}
	function btn_AddActionPlan(id, options, rowObject)
	{					
		var rowId = options.rowId;		
		return '<input type="button" id="remov" class="grdButton" value="" onclick="removeActionPlan(\''+rowId + '\');"/>';
	}	
	function removeActionPlan(rowId){	
	    
		var rowData = jQuery("#actionPlanGrid").jqGrid('getRowData',rowId);
		var keyId = rowData.txtscapKeyid;
		var conFdelete = confirm("Do You Want To Delete");
		if(conFdelete){
			if( keyId != null && keyId.length > 0  ){	
				processAjaxCalls("ActionPlan_Delete.accIncRpt","&keyId="+keyId+"&rowId="+rowId , 'ActionPlanDelete_successCallBack','ActionPlanDelete_errorCallBack');
				
			}
		}
	}
	function ActionPlanDelete_successCallBack(result){
		if(result.tpmException){
			var rowId=result.rowId;
			alert(result.tpmException);	
			jQuery("#actionPlanGridnew").trigger("reloadGrid");
			//jQuery("#actionPlanGrid").trigger("reloadGrid");		
			//jQuery("#actionPlanGrid").delRowData(rowId);
		}
	}
	function btn_AddActionPlanPopUp(id, options, rowObject)
	{					
		var rowId = options.rowId;
		
		return '<input type="button" id="remov" class="grdButton" value="" onclick="removeAddActionPlanPopUp(\''+rowId + '\');"/>';
	}	
	function removeAddActionPlanPopUp(rowId){	
	    
		var rowData = jQuery("#actionPlanGridnew").jqGrid('getRowData',rowId);
		var keyId = rowData.txtscapKeyid;
		var conFdelete = confirm("Do You Want To Delete");
		if(conFdelete){
			if( keyId != null && keyId.length > 0  ){	
				processAjaxCalls("ActionPlan_Delete.accIncRpt","&keyId="+keyId+"&rowId="+rowId , 'ActionPlanPopUpDelete_successCallBack','ActionPlanDelete_errorCallBack');
				
			}
		}
	}
	function ActionPlanPopUpDelete_successCallBack(result){
		if(result.tpmException){
			var rowId=result.rowId;
			alert(result.tpmException);	
			jQuery("#actionPlanGridnew").trigger("reloadGrid");
			//jQuery("#actionPlanGrid").trigger("reloadGrid");		
			//jQuery("#actionPlanGridnew").delRowData(rowId);
		}
	}
	
	//function divAddEmployee_onClose()
	//{	
		//jQuery('#empdtlsGrid').jqGrid('GridUnload');

		//loadActionGrid("empdtlsGrid","pager2","colModelEmployeeDtls","SheEmployeeAdd_input.accIncRpt","","hdnsinmKeyid");
		//return true;
	//}
	function ADDEMP_Callback()
	{
		
	}
	function empdblClk(id)
	{//alert(1);
		var rowData = jQuery("#empGrid").jqGrid('getRowData',id);	
		var selId = rowData.txtSiemKeyid;
		var dataString = "keyId="+	selId;
		processAjaxCalls("SheEmployeeAdd_reCall.ehsaccIncRpt" ,dataString, "Recall_successsCallback","Recall_errorCallback");
		
	}
	
	function Recall_successsCallback(result)
	{
	
		jQuery('#cmbSiemEmpmKeyid').combobox('setValue',result.employeeData.SiemEmpmKeyid); 
		jQuery('#cmbSiemInjuryModeid').combobox('setValue',result.employeeData.SiemInjuryModeid); 
		jQuery('#cmbSiemInjuryTypeid').combobox('setValue',result.employeeData.SiemInjuryTypeid); 
		jQuery('#cmbSiemBodypart').combobox('setValue',result.employeeData.SiemBodypart); 
		jQuery("#hdnSiemKeyid").val(result.employeeData.SiemKeyid);
		jQuery("#txtSiemCompany").val(result.employeeData.SiemCompany);
		jQuery("#hdnSiemSincKeyid").val(result.employeeData.SiemSincKeyid);
		
		jQuery("#txtSiemMode").val(result.employeeData.SiemMode);
		jQuery("#txtSiemType").val(result.employeeData.SiemType);
		jQuery("#txtSiemLocation").val(result.employeeData.SiemLocation);
			
	}
	function ActionGrd_doubleClickGrid(id)
	{
		var rowData = jQuery("#actionPlanGridnew").jqGrid('getRowData',id);	
		var selId = rowData.txtscapKeyid;
		var dataString = "keyId="+	selId;		
		processAjaxCalls("SheActionPlan_reCall.accIncRpt" ,dataString, "ActionPlanRecall_successsCallback","ActionPlanRecall_errorCallback");
	}
	function ActionPlanRecall_successsCallback(result)
	{
		var status = result.actionPlanData.SacpStatus;
		
		 if(status != null && status != '' && status != ' ')
		 {
			 if(status == 'C')
			 {
				 jQuery('#cmbSacpStatus').combobox('setValue','C');
				 enableFields('cmbSacpCompletedBy');		
				 enableFields('dteSacpCompletedDate');	
					
			 }
		 }
		jQuery('#txtSacpDescription').val(result.actionPlanData.SacpDescription);
		jQuery('#cmbSacpResponsiblity').combobox('setValue',result.actionPlanData.SacpResponsiblity);
		jQuery('#cmbSacpCompletedBy').combobox('setValue',result.actionPlanData.SacpCompletedBy);
		jQuery('#txtSacpRespDept').val(result.actionPlanData.SacpRespDept);
		jQuery('#txtSacpRemarks').val(result.actionPlanData.SacpRemarks);
		jQuery('#hdnSacpKeyid').val(result.actionPlanData.SacpKeyid);
		jQuery('#dteSacpTargetDate').datebox('setValue',result.actionPlanData.SacpTargetDate);
		jQuery('#dteSacpCompletedDate').datebox('setValue',result.actionPlanData.SacpCompletedDate);
	}
	function loadActionGrid(tableName,pagerName,gridType,url,callBack,hdnId,dblClk)
	{
		var ds = '';
		var IncKey = jQuery('#'+hdnId).val();
		if(IncKey != null && IncKey != '' && IncKey != ' ')
			ds = '?q=2&incidentKey='+IncKey;
		if(gridType != null && gridType != '' && gridType != ' ')
			ds += '&gridType='+gridType;
	
		processGridnew(url,ds,tableName,pagerName,"",callBack,dblClk);
	}
	function actionFormatterSave(cellvalue, options, rowObject) 
	{
	}
	function accidentCMComplete()
	{
		if(screen.width >=1250)
		{
			jQuery( "#accidentCounterMsrGrid" ).setGridWidth(300);
			jQuery( "#accidentCounterMsrGrid" ).setGridHeight(70);
		}
		else
		{
			jQuery( "#accidentCounterMsrGrid" ).setGridWidth(300);
			jQuery( "#accidentCounterMsrGrid" ).setGridHeight(70);
		}
	}
	function setRootcausedueto()
	{
		var dueTo = jQuery( "#hdnsinmRootcausedueto").val();
		if(dueTo != null && dueTo !='' && dueTo != ' ' && dueTo != '-')
		{
			if(dueTo == 'A')
				jQuery('#chbUnsafeAct').attr('checked',true);
			else if(dueTo == 'C')
				jQuery('#chbUnsafeCondn').attr('checked',true);
		}
	}
	function dtesinmOccurredDate_onChange(date)
	{  
		var currentDate = getServerDateTime();
		if(date > currentDate)
		{
			showValidationErrorMsg('dtesinmOccurredDate','Should Not Exceed Current Date/Time');
		}
	    else
		    clearValidationErrorMsg('dtesinmOccurredDate');	
		
		
		if(jQuery('#hdnsinmKeyid').val() == null || jQuery('#hdnsinmKeyid').val() == '' || jQuery('#hdnsinmKeyid').val() == ' ')
		{
			jQuery('#dtesinmReportedDate').datebox('setValue',date);
			jQuery('#spnsinmReportedtime').spinner('setValue',jQuery('#spnsinmOccurredtime').spinner('getValue'));
		}
	}
	function dtesinmOccurredDate_onSelect(date)
	{ 
		var occuredDate = jQuery('#dtesinmOccurredDate').datebox('getValue');
		var occuredTime = jQuery('#spnsinmOccurredtime').spinner('getValue');
		var toDate = occuredDate + occuredTime;		
		var currentDate = getServerDateTime();
		
		if(convertStringToDate(toDate) > currentDate)
		{
			showValidationErrorMsg('dtesinmOccurredDate','Should Not Exceed Current Date/Time');
		}
		else
		    clearValidationErrorMsg('dtesinmOccurredDate');	
	
		jQuery('#dtesinmReportedDate').datebox('setValue',occuredDate);
		jQuery('#spnsinmReportedtime').spinner('setValue',occuredTime);
	}
	function dtesinmReportedDate_onSelect(date)
	{              	
		compareDates('dtesinmOccurredDate','spnsinmOccurredtime','dtesinmReportedDate','spnsinmReportedtime','Reported Date should not be lesser than Occured Date');
	}
	function dtesinmAnalysedDate_onSelect(date)
	{              	
		compareDates('dtesinmReportedDate','spnsinmReportedtime','dtesinmAnalysedDate','spnsinmAnalysedtime','Analysed Date should not be lesser than Reported Date');
	}
	function occuredTimeSpnEvt()
	{
		var shiftDate = jQuery('#dtesinmOccurredDate').datebox('getValue');
		var fromTime = jQuery('#spnsinmOccurredtime').spinner('getValue');
		var toDate = shiftDate + fromTime;
		var currentDate = getServerDateTime();
			
		if(convertStringToDate(toDate) > currentDate)
		{
			showValidationErrorMsg('dtesinmOccurredDate','Should Not Exceed Current Date/Time');
		}
		 else
		    clearValidationErrorMsg('dtesinmOccurredDate');	
		

		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&factId='+jQuery('#factory').val()+'&sectId='+jQuery('#section').val();
	    dataString += '&cellId='+jQuery('#cell').val()+'&flid='+jQuery('#flid').val()+'&fromTime='+fromTime;
	    	
		processAjaxCalls('txt_shift.brdn',dataString,'getShift','getShiftErr');
		
		jQuery('#dtesinmReportedDate').datebox('setValue',shiftDate);
		jQuery('#spnsinmReportedtime').spinner('setValue',fromTime);
		
	}
	function reportedTimeSpnEvt()
	{
		var repDate = jQuery('#dtesinmReportedDate').datebox('getValue');
		var repTime = jQuery('#spnsinmReportedtime').spinner('getValue');
		//alert
		
		compareDates('dtesinmOccurredDate','spnsinmOccurredtime','dtesinmReportedDate','spnsinmReportedtime','Reported Date should not be lesser than Occured Date');
		if(repTime.indexOf(':') == 1)
			repTime = '0'+repTime;
		var dataString = '?q=2&factId='+jQuery('#factory').val()+'&sectId='+jQuery('#section').val();
	    dataString += '&cellId='+jQuery('#cell').val()+'&flid='+jQuery('#flid').val()+'&fromTime='+repTime;
	    	
		processAjaxCalls('txt_shift.brdn',dataString,'getRepShift','getShiftErr');	
		
	}
	
	function analysedTimeSpnEvt()
	{
		var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
		var analysedTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
		
		compareDates('dtesinmReportedDate','spnsinmReportedtime','dtesinmAnalysedDate','spnsinmAnalysedtime','Analysed Date should not be lesser than Reported Date');
		if(analysedTime.indexOf(':') == 1)
			analysedTime = '0'+analysedTime;
		var dataString = '?q=2&';
	    	dataString += 'fromTime='+analysedTime;
	    	//alert(dataString);
		processAjaxCalls('txt_shift.brdn',dataString,'getAnalysedShift','getAnalysedShiftErr');	
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
		var fromTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&fromTime='+fromTime;
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
		
		if(dateFlag != null && dateFlag != '' && dateFlag != ' ')
		{ 
			
			if(dateFlag == '01-Jan-1801 00:00:00')
			{
				if(dateId == 'dtesinmAnalysedDate')
				{
					fillWithCurrentDate('dtesinmAnalysedDate');
					fillWithCurrentDate('spnsinmAnalysedtime');
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
	function frmAccidentIncident_deleteExceptionCallback(result)
	{
		
	}
	function frmAccidentIncident_beforeSubmit()
	{		
		alert("beforesubmit")
		 var mode = jQuery('#mode').val();
		// alert("mode in before submit"+mode);
		 if(mode=="View")
			 {
			   return false;
			   
			 }else
				 {
		var cellId = jQuery("#frmAccidentIncident input[id='cell']").val();
		  if(cellId.length==0)
			  {
			     popupCommonErrorMsg("Select JH");
			     return false;
			  }
		
		  var rowData = jQuery("#empGrid").jqGrid('getRowData');
		 /* if(rowData.length==0)
		  {
				 alert("Save EMployee");
				 return false;
			 }*/
		  
		var keyid=jQuery("#txtsinmKeyid").val();
		// alert("chkflag"+jQuery("#hdnCheckFlag").val());
		var flag = jQuery("#hdnCheckFlag").val();
		// alert("chkflag"+jQuery("#hdnCheckFlag").val());
		var rowId = jQuery("#empdtlsGrid").jqGrid('getDataIDs');
		
		if(keyid != '' || keyid != '' || keyid.length<1)
		{
			if(flag=="N"){
				 if(rowId.length<1){
				alert("Select Employee..");
				return false;
				 }
			}			
		}		
		
		return 'txtsinmNoofemployees='+rowId.length;
			 }
		 
	}
	
	function frmAccidentIncident_beforeDelete()
	 {		 	
		 var mode = jQuery('#mode').val();
	//	 alert("Mode in View"+mode);
		// alert("Mode in View"+mode.length);
		
		 if(mode=="View")
		 {
		   return false;
		   
		 }else
			 {
		var delMsg = "Do You Want To Delete this Record ?";
		var keyid=jQuery("#hdnsinmKeyid").val();
		//alert("keyid::::"+keyid);
		if(confirm(delMsg) == false)
		{
				return false;
		}
		else{
			
			processAjaxCalls("safteyincident_delete.ehsaccIncRpt","?q=2&keyid="+keyid,"DeleteRecallSuccess","DeleteRecallError");
		}
			 }
		 
	 }

	
	function frmAccidentIncident_deleteSuccessCallback(result)
	{
		//alert(1);
		alert("Data Deleted Successfully!");
		navigateToPrevForm();
	}
	
	function frmAccidentIncident_errorCallback(result)
	{
	
		//alert("result.successData.keyId::"+result.succssMsg.keyid);
		//alert("it is in error callback next line result");
		//saveForm('frmIncidentEmpPopUp','SheEmployeeAdd_Save.ehsaccIncRpt?incId='+result.successData.keyid);
	}

	function frmAccidentIncident_exceptionCallback(msg) {
		//alert(123456+" in exception callback");
		
		//alert("msg.keyId"+msg.keyid);
		
		if(msg.keyId != null)
			dataString += '&incdtKeyId='+msg.keyid;
		
		//alert("emp save time :"+dataString);
		
		saveForm('frmIncidentEmpPopUp','SheEmployeeAdd_Save.ehsaccIncRpt?incId='+msg.keyid);
		//LoadPopUp("divAddEmployee","SheEmployeeAdd_input.accIncRpt"+dataString, true,"900px","500px","0px","10%", "multiSelectOk_Callback","Incident Employee");
	}
	
	function frmAccidentIncident_successsCallback(result)
	{
		 var mode = jQuery('#mode').val();
		// if(mode=="Modify")
			// {
		
		 closePopUpDialoge("loadAccidentModify");
		//	 }
		
		if(result.afterSave != null)
		{
			if( result.afterSave == "openPop")
			{
				var dataString = '?q=2';
				if(result.successData.keyid != null)
					dataString += '&incdtKeyId='+result.successData.keyid;
				//alert("emp save time :"+dataString);
				
				var msg=result.successData.glblmsg;
				alert(msg);
				saveForm('frmIncidentEmpPopUp','SheEmployeeAdd_Save.ehsaccIncRpt?incId='+result.successData.keyid);
			
				//LoadPopUp("divAddEmployee","SheEmployeeAdd_input.accIncRpt"+dataString, true,"900px","500px","0px","10%", "multiSelectOk_Callback","Incident Employee");
			}
			else if( result.afterSave == "YY")
			{
				/* var persistentData = result.persistentData;
				 var forwardData = result.forwardData;											
				 navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );
				*/  
				 
					//var flId = jQuery("#flid").val();
					var flId = jQuery("#frmAccidentIncident input[id='flid']").val();
			 		var refDocDate = jQuery('#dtesinmReportedDate').datebox("getValue");
			 		var refDocId = jQuery('#txtsinmKeyid').val();
			 		var problem = jQuery('#txtsinmAccidentDesc').val();
			 		var yyArea = jQuery("#txtsinmArea").val();
			 		jQuery("#hdnYYArea").val(yyArea);
			 		var attendby = getFieldValue("cmbsinmAnalysedBy");
			 		var pillar = "EHS";
			 		//alert('flId' + flId);
			 		 openWhyWhy("",false,refDocId,"IRE",flId, refDocDate, problem, "create",null,null,attendby,yyArea,pillar);
			}
			else
				navigateToPrevForm();
		}
		else
		{
			
			if(confirm("Do You Want to Open File Manager?") == true)
			{
				var documentNo =result.successData.keyId;	
				
				if(documentNo != null && documentNo != ''){
					fileManagerPopUp(documentNo,"INC","","","");
				}
			}
			else
				navigateToPrevForm();
		}
	}
function btnFileMgrSafety_click(){
     
	 var mode = jQuery('#mode').val();
	 if(mode=="View")
	 {
	   return false;
	   
	 }else
		 {
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"INC","","","");
		
	}
	else
			alert("Incident No. should be selected to view FileManager");
		 }
}
	function  getShift(record)
	{       
		jQuery('#cmbsinmOccurredShift').combobox('setValue',record.shift); 
		jQuery('#cmbsinmReportedShift').combobox('setValue',record.shift);     
// 		readOnlyFields('cmbsinmOccurredShift');           	
	}
	function  getRepShift(record)
	{       
		jQuery('#cmbsinmReportedShift').combobox('setValue',record.shift);     
// 		readOnlyFields('cmbsinmReportedShift');           	
	}   
	function  getAnalysedShift(record)
	{       
		jQuery('#cmbsinmAnalysedShift').combobox('setValue',record.shift);     
// 		readOnlyFields('cmbsinmAnalysedShift');           	
	}
	function fillShift(record)
	{
		jQuery('#cmbsinmOccurredShift').combobox('setValue',record.shift); 
		jQuery('#cmbsinmReportedShift').combobox('setValue',record.shift);     
// 		readOnlyFields('cmbsinmOccurredShift');  
// 		readOnlyFields('cmbsinmReportedShift');        
	}	
	
	function frmAccidentIncident_FuntLocHierarchy_SuccessCallBack(keyIds)
	{		
		//alert(keyIds.sectId);
		setFieldValue("cmbsinmDepartment",keyIds.sectId);
		
// 		readOnlyFields('cmbsinmDepartment');	
	}
	
	jQuery("#btnExcelView").click(function(){
		
        var incKeyid= jQuery('#txtsinmKeyid').val();
        //alert("Keyid"+incKeyid);
        var flid = jQuery("#frmAccidentIncident input[id='flid']").val();
    //    var docno=jQuery('#hdndocumentno').val();
    //    alert("The docno"+docno);
         if(incKeyid != null && incKeyid.length >0){
             window.open("Accincident_Excelview.ehsaccIncRpt?incKeyid="+incKeyid+"&flid="+flid);
         }
         else
        	 {
        	 return false;
        	 }
    });
			
</script>

<form id="frmAccidentIncident" >
	<div id="wrapper">
	
			
 		<!-- 	<div title="Incident Reporting" style="padding:opx;height: 500px; margin-left:0px;">  Feedback Div -->
 		<!-- 	<div class="incidentBody" style="padding:opx;height: 500px; margin-left:10px;"> -->
					<table>
			<tr>
			<td>
			<div id="frmAccidentIncidentFuntKeyIds" style="width:500px;">
						     			<input type="hidden" id="location" name="cmbsinmLocnKeyid" value="${requestScope.sheTlIncidentMst.sinmLocnKeyid}"  ></input>
										<input type="hidden" id="factory" name="cmbsinmFactKeyid" value="${requestScope.sheTlIncidentMst.sinmFactKeyid}"  ></input>
										<input type="hidden" id="section" name="cmbsinmSectKeyid" value="${requestScope.sheTlIncidentMst.sinmSectKeyid}"  ></input>
										<input type="hidden" id="cell" name="cmbsinmCellKeyid" value="${requestScope.sheTlIncidentMst.sinmCellKeyid}" ></input>
										<input type="hidden" id="machine" name="cmbsinmMachKeyid" value="${requestScope.sheTlIncidentMst.sinmMachKeyid}"  ></input>
										<input type="hidden" id="flid" name="cmbsinmFlid" value="${requestScope.sheTlIncidentMst.sinmFlid}"  ></input>
										<input type="hidden" id="elementId" name="cmbsinmElementid" value="${requestScope.sheTlIncidentMst.sinmElementid}"  ></input>
						</div>		</td>
						
						<tr>				
						<td>	<div id="sinmfunLocation" style="width:550px;"></div></td>
						<td>
							<div style="margin-left:270px;margin-top:14px;">	
							<%-- <label>Document No</label><br/>
			<input type="text" class="easyui-text" id="txtsinmKeyid" name="txtsinmKeyid" style="width:105px;padding-left:40px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;" tabindex = "-1" disabled value="${requestScope.sheTlIncidentMst.sinmKeyid}"> --%>
			</div>
						
						</td>
						<td>
						<div id="FileMgrSafety" style="position:absolute;margin-top:0px; padding-left:0px;"></div>
			
			</div>
						</td>
		
		</tr>
		
			<tr>
			<td colspan="2" style=" width:900px;">
			<div><label class="mandatory-lbl">Incident/Accident Category</label><br/>
		
			<input id="cmbsinmAccidentType" name="cmbsinmAccidentType" class="easyui-combobox"  style="width:315px;" value="${requestScope.sheTlIncidentMst.sinmAccidentType}"   value = "${requestScope.sheAccIncBean.disableAccidentType == true ? ' disabled':''}" /> 
			<input type="text" class="easyui-text" id="txtsinmKeyid" name="txtsinmKeyid" style="width:150px;padding-left:40px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;" tabindex = "-1" disabled value="${requestScope.sheTlIncidentMst.sinmKeyid}">
			<span>
			  <input type="button" class="easyui-button" id="btnRiskAsses" name="btnRiskAsses" value="Risk Assessment" style="height:30px;width:110px;"/>
 	
			</span>
			<span>
			
		    <input type="button" class="easyui-button" id="btnExcelView" name="btnExcelView" value="Excel View" style="height:30px;width:70px;"/>
 		  
			
			</span>
			
			
		
		<!-- 	<div id="FileMgrSafety" style="position:absolute;margin-top:-28px; padding-left:835px;"></div>
 		    <div  style="position:absolute;margin-top:-28px; padding-left:755px;">
 		    <input type="button" class="easyui-button" id="btnExcelView" name="btnExcelView" value="Excel View" style="height:30px;width:70px;"/>
 		    </div>
 		  <div  style="position:absolute;margin-top:-28px; padding-left:634px;">
 		  <input type="button" class="easyui -button" id="btnRiskAsses" name="btnRiskAsses" value="Risk Assessment" style="height:30px;width:110px;"/>
 		  </div> -->
			
			</td>
			</tr>
			</table>	
			
			
			<table width="90%">
			<tr><td colspan="2"> <div class="sub-header" style="text-align: left;margin-top:10px;"><span>When and Where</span></div></td></tr>
				<tr>
					<td valign="top">					
						<div>
							
							
							<div>
								<label class="mandatory-lbl">Occurred Date Time</label>
								<label class="lblaccIncidentshift mandatory-lbl">Shift</label>
							</div>
							<div style="padding-bottom:0px;" >	
								<span style="margin-left:2px;">
									<input id="dtesinmOccurredDate"  name="dtesinmOccurredDate" clear="false" class="easyui-datebox" value="${requestScope.sheTlIncidentMst.sinmOccurredDate}"  style="width: 100px;"   value = "${requestScope.sheAccIncBean.disableOccurredDate == true ? ' disabled':''}"/>
									<span class="spinner"><input  id="spnsinmOccurredtime" name="spnsinmOccurredtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.sheAccIncBean.sinmOccurredtime}"  style="width: 60px;"   value = "${requestScope.sheAccIncBean.disableOccurredtime == true ? ' disabled':''}" ></span>
						<%-- 		<span class="spinner"><input  id="spnsinmOccurredtime" name="spnsinmOccurredtime"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;"  <c:out value = "${requestScope.sheAccIncBean.disableOccurredtime == true ? ' disabled':''}"/> ></span>  --%>
								</span>
								
								<span style="padding-left: 24px;padding-left: 20px\9;">
									<input id="cmbsinmOccurredShift" name="cmbsinmOccurredShift" class="easyui-combobox"  style="width:120px;width:118px\9;"  value="${requestScope.sheTlIncidentMst.sinmOccurredShift}"   value = "${requestScope.sheAccIncBean.disableOccurredShift == true ? ' disabled':''}"/> 
								</span>
							</div>
							<div>
								<span id="err_dtesinmOccurredDate" class="tpm-errormsg"></span>
							</div>
							<div><label class="mandatory-lbl">Area</label></div>
							<div style="padding-bottom:0px;" >	
								<span style="margin-left:2px;">
									<input id="txtsinmArea" type="text" class="easyui-text" name="txtsinmArea" style="width:315px;width:312px\9;" value="${requestScope.sheTlIncidentMst.sinmArea}"  value = "${requestScope.sheAccIncBean.disableArea == true ? ' disabled':''}"/>
								</span>
							</div>
							
							<%-- <div ><label class="mandatory-lbl">Incident/Accident Category</label></div>
							<span>
								<input id="cmbsinmAccidentType" name="cmbsinmAccidentType" class="easyui-combobox"  style="width:315px;" value="${requestScope.sheTlIncidentMst.sinmAccidentType}"  <c:out value = "${requestScope.sheAccIncBean.disableAccidentType == true ? ' disabled':''}"/> /> 
							</span> --%>
							
						    <div style="display:none;">
							<div><label >Department</label></div>
							<span>
								<input id="cmbsinmDepartment" name="cmbsinmDepartment" class="easyui-combobox"  style="width:315px;"   
<%-- 								value="${requestScope.sheTlIncidentMst.sinmDepartment}"  --%>
								 value = "${requestScope.sheAccIncBean.disableDepartment == true ? ' disabled':''}"/>
							</span>
                                              

							</div>
						</div>
						<div style="height:15px;">
							
						</div>
						
					</td>
					<td valign="top" style="padding-right:147px; padding-bottom: 10px">
						
							<div ><label class="mandatory-lbl">Reported Date Time</label>
							<label class="lblaccIncidentRepshift mandatory-lbl">Shift</label>
							 <label class="" style=margin-left:108px;margin-top:-5px;>Witness 1</label></div>
                            
							<div style="margin-top:-5px;" >	
								
									<input id="dtesinmReportedDate"  name="dtesinmReportedDate" clear="false" class="easyui-datebox" value="${requestScope.sheTlIncidentMst.sinmReportedDate}"  style="width: 100px;padding-bottom:-10px"  <%--  value = "${requestScope.sheAccIncBean.disableReportedDate == true ? ' disabled':''} --%>"/>
									<span class="spinner"><input  id="spnsinmReportedtime" name="spnsinmReportedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.sheAccIncBean.sinmReportedtime}"  style="width: 60px;"  value = "${requestScope.sheAccIncBean.disableReportedtime == true ? ' disabled':''}"/></span>
								
								
								<span style="padding-left: 24px;padding-left: 20px\9;">
									<input id="cmbsinmReportedShift" name="cmbsinmReportedShift" class="easyui-combobox"  style="width:120px;width:118px\9;"  value="${requestScope.sheTlIncidentMst.sinmReportedShift}"  value = "${requestScope.sheAccIncBean.disableReportedShift == true ? ' disabled':''}"/> 
								</span>
								        <span style="padding-left: 24px;padding-top:105px;">
									<%-- <input id="txtWitnessOne" name="txtWitnessOne" class="easyui-text" style="width:250px;width:250px\9;"  value="${requestScope.sheTlIncidentMst.sinmReportedShift}" <c:out value = "${requestScope.sheAccIncBean.disableReportedShift == true ? ' disabled':''}"/>/> 
							 --%>	
							 <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmWitness1" name="txtsinmWitness1" maxlength="3000" style="resize:none;width:200px;height:25px;"  value = "${requestScope.sheAccIncBean.disableImmediateAction == true ? ' disabled':''}">${requestScope.sheTlIncidentMst.sinmWitness1}</textarea>
									
									
							 
							 </span>
							</div>
							<div>
								<span id="err_dtesinmReportedDate" class="tpm-errormsg"></span>
							</div>
							
							<div><label class="mandatory-lbl">Reported By</label></div>
                            <div><label class="" style=margin-left:342px;>Witness 2</label></div>
							<div style="padding-bottom:0px; margin-top:-18px;">	
								<span style="margin-left:2px;">
									<input id="cmbsinmReportedBy" type="text" class="easyui-combobox" name="cmbsinmReportedBy" style="width:315px;width:312px\9;" value="${requestScope.sheTlIncidentMst.sinmReportedBy}"  value = "${requestScope.sheAccIncBean.disableReportedBy == true ? ' disabled':''}">
								</span>
                                                     <span style="margin-left:20px;margin-top:15px;">
								
							  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmWitness2" name="txtsinmWitness2" maxlength="3000" style="resize:none;width:200px;height:25px;"  value = "${requestScope.sheAccIncBean.disableSinmWitness2 == true ? ' disabled':''}">${requestScope.sheTlIncidentMst.sinmWitness2}</textarea>
							
							 </span>


							</div>
						<%-- 	<div><label >Division</label></div>
							<div style="padding-bottom:0px;" >	
								<span style="margin-left:2px;">
									<input id="txtsinmDivision" type="text" class="easyui-text" name="txtsinmDivision" style="width:315px;" value="${requestScope.sheTlIncidentMst.sinmDivision}" <c:out value = "${requestScope.sheAccIncBean.disableDivision == true ? ' disabled':''}"/>>
								</span>
							</div> --%>
							
							<div>
								<label>Loss Property</label>
								<label  class="lblaccIncidentDirectCost" style="margin-left:23px;">Direct Cost(Rs)</label>
							</div>
							<div style="padding-bottom:0px;" >	
								<span style="margin-left:2px;">
									<input id="txtsinmLossproperty" type="text" class="easyui-text" name="txtsinmLossproperty" maxlength="100" style="width:200px;" value="${requestScope.sheTlIncidentMst.sinmLossproperty}"  value = "${requestScope.sheAccIncBean.disableLossproperty == true ? ' disabled':''}">
								</span>
								<span style="margin-left:15px;">
										<input id="txtsinmDirectcost" type="text" class="easyui-text" name="txtsinmDirectcost" maxlength="15" style="width:100px;width:118px\9;" value="${requestScope.sheTlIncidentMst.sinmDirectcost}"  value = "${requestScope.sheAccIncBean.disableDirectcost == true ? ' disabled':''}">
								</span>
							</div>
						</div>
						
					</td>
				</tr>
				<tr>
					<td colspan="2">
				
						<table>
							<tr>
								<td valign="top">
									<div class="sub-header"  style="text-align: left;"><span>Accident/Incident Details</span></div>
									<div><label class="mandatory-lbl">Description of Accident/ Incident</label></div>
									<div style="padding-bottom:0px;" >	
									<span style="margin-left:2px;">
										<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmAccidentDesc" name="txtsinmAccidentDesc"  maxlength="1000" style="resize:none;width:317px;height:75px;"  value = "${requestScope.sheAccIncBean.disableAccidentDesc == true ? ' disabled':''}">${requestScope.sheTlIncidentMst.sinmAccidentDesc}</textarea>
									</span>
									</div>
									<div ><label>Immediate Action taken by Reporter</label></div>
									<div style="padding-bottom:0px;" >	
										<span style="margin-left:2px;">
											<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmImmediateAction" name="txtsinmImmediateAction" maxlength="3000" style="resize:none;width:317px;height:75px;"  value = "${requestScope.sheAccIncBean.disableImmediateAction == true ? ' disabled':''}">${requestScope.sheTlIncidentMst.sinmImmediateAction}</textarea>
										</span>
									</div>
									<div ><label>Recommendations from Reporter</label></div>
									<div style="padding-bottom:0px;" >	
										<span style="margin-left:2px;">
											<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmCarecommended" name="txtsinmCarecommended" maxlength="33000" style="resize:none;width:317px;height:75px;"  value = "${requestScope.sheAccIncBean.disableRecommendations == true ? ' disabled':''}">${requestScope.sheTlIncidentMst.sinmCarecommended}</textarea>
										</span>
									</div>			
								</td>
								<td>
									<div style="padding-left:27px;">
									<div class="sub-header" style="text-align: left; position:relative;"><span>Employee Details</span>
										<span id="rolbtn" style="position:absolute;right:0px;top:-1px\9;">
										
												<img  class="" style="cursor: pointer;z-index:210;margin-top:-3" src="images/addbtsub.png" title="Add Role" alt="" id="imgAddEmp">
										</span>
									</div>
									<div >		
										<table align="left" id="empdtlsGrid" >
											<tr><td><td/></tr>
										</table>
										<div id="pager2"></div>
									</div>	
									</div>
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
		</div>		
			<input type="hidden" id="mode" name="mode" value="${requestScope.formMode}">
			<input type="hidden" id="hdnBookingMode" name="hdnBookingMode" value="${requestScope.bookingMode}">
			<input type="hidden" id="hdnsinmKeyid" name="hdnsinmKeyid" value="${requestScope.sheTlIncidentMst.sinmKeyid}">
			<input type="hidden" id="hdnIncidentId" name="hdnIncidentId"/>
			<input type="hidden" id="hdnOpenPop" name="hdnOpenPop" value="${requestScope.openPop}"/>
			<input type="hidden" id="hdndocumentno" name="hdndocumentno" value="${requestScope.DcmTlDocumentmanager.DmdmDescription}" ></input>
			<input type="hidden" id="hdndocumentno" name="hdndocumentno" value="${requestScope.DcmTlDocumentmanager.DmdmDescription}" ></input>
			<input type="hidden" id="hdnOccurTime" name="hdnOccurTime" value="${requestScope.occurtime}" ></input>
			<input type="hidden" id="hdnReportTime" name="hdnReportTime" value="${requestScope.prepretime}" ></input>			
			</div>	
	<input type="hidden" id="hdnCheckFlag" value="N" />
	
</form>