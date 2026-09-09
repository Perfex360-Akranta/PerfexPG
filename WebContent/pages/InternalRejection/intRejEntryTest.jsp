<script type="text/javascript">
var glbQirmKeyid="";
jQuery(document).ready(function(){
		initialiseForm('frmIntRejEntry');
		jQuery('#submitForm').val('frmIntRejEntry');
		jQuery("#intRejEntryGrid").trigger("reloadGrid");
		fillComboBox("frmIntRejEntry","cmbQirmMachineid","machineCombo.commonFilter" );
		fillComboBox("frmIntRejEntry","cmbQirmCellid","cellCombo.commonFilter" );

		var flid = jQuery("#frmIntRejEntry input[id='flid']").val();		

		//fillComboBox("frmIntRejEntry","cmbQirmProductid","combo_product.ire");
		fillComboBox("frmIntRejEntry","cmbQirmProductid","comboGradeSpec.commonFilter?q&flid="+flid);
		
		fillComboBox("frmIntRejEntry","cmbQirmInspectedshiftid","shift.commonFilter"+"?");
        
		
		fillComboBox("frmIntRejEntry","cmbQirdProcessid","process.commonFilter?&flid="+flid);
		fillComboBox("frmIntRejEntry","cmbQirdSubprocessid","subprocess.commonFilter?&processId=1234");
		fillComboBox("frmIntRejEntry","cmbQirdPhenomenaid","defphen.commonFilter");
		fillComboBox("frmIntRejEntry","cmbQirdCauseid","qtmCause.commonFilter");
		 
		var dataStr="&flid="+flid;
		loadFunctionalLocation("intRejTestFunctionLocation", "functionalLoc_lossCapture.pcs","intRejTestFunctionLocationEmp", "frmIntRejEntry", dataStr);
		
	  	numericTextBox('txtQirdQuantity');

		//numericTextBox('txtQirmInspectionqty');
		formatDateBox('dteQirmInspectiondate','dd-MMM-yyyy');	
		//readOnlyFields("txtQirmInspectionqty");
		var QirmKeyid =jQuery('#hdnQirmKeyid').val();
		glbQirmKeyid = QirmKeyid.trim();
		var reportType =jQuery('#hdnReportType').val();		
		reportType="DIR";
		/* if (reportType!="DIR")
			jQuery('#filter').css('display','block');
 */ 
 var types= jQuery("#hdnTypes").val();
 //alert("types::"+types);

 

 if(types!="create")
     {
 	var date=getFieldValue("dteQirmInspectiondate");
 	
     var PreparedDate  = date.substring(0, 12);
     
	    setFieldValue("dteQirmInspectiondate",PreparedDate,"frmIntRejEntry");
     }

        
    else{
 		fillWithCurrentDate('dteQirmInspectiondate');
    }
		var acceptQty = jQuery('#txtQirmAcceptedqty').val();
		if (acceptQty=="" || acceptQty == " ") {
			jQuery('#txtQirmAcceptedqty').val("0");
		}

		var rejQty = jQuery('#txtQirmBacklogqty').val();
		if (rejQty=="" || rejQty == " ") {
			jQuery('#txtQirmBacklogqty').val("0");
		}

		processAjaxCalls('setFormActionMode.ire','?q&mode=create',"formModeSuccess");
		
		//fnFillProcessGrid();

		var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var hours = currentTime.getHours();
		var minutes = currentTime.getMinutes();
		
		if (minutes < 10){
			minutes = "0" + minutes;
		}	

		var factId = "";//jQuery("#frmPcsCalendar input[id='factory']").val();
		var dataString = '?q&factId='+factId+'&sectId='+jQuery("#frmIntRejEntry input[id='section']").val();
		dataString += '&cellId='+jQuery("#frmIntRejEntry input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
	
		setTimeout(function() {
			processAjaxCalls('txt_shift.brdn',dataString,'getIntCurrentShift','getIntCurrentShiftErr');
		},10);

		 jQuery('.panel-title').filter(function(){
			    return jQuery(this).parent('div').parent('div').hasClass('panel layout-panel layout-panel-center');
			}).text("Internal Rejection Entry");

	});

	jQuery('#btnSave').click(function(){
		
		var url = "internalRejection_save.ire?q&";
		url+= "&saveMode=create";
		
		var QirmKeyid = jQuery('#hdnQirmKeyid').val();
		var QirdKeyid = jQuery('#hdnQirdKeyid').val();
		var PlrkKeyid="";
		
		url+= "&saveMode=createYY&saveFrom=yy";
		url+= "&QirmKeyid="+QirmKeyid+"&QirdKeyid="+QirdKeyid+"&PlrkKeyid="+PlrkKeyid;
		//url+= "&Qird4mtype="+Qird4mtype+"&QirdType="+QirdType+"&QirdRemarks="+QirdRemarks;
		//url+= "&processId="+processId+"&phenId="+phenId+"&causeId="+causeId+"&inspectedQty="+inspectedQty+"&QirdWwmasterid="+yyId;//+'&cmbWwmsMachineid='+machId;
		//alert(url);
				
		var reportType = jQuery('#hdnReportType').val();
		var qihbKeyid = jQuery('#hdnQihbKeyid').val();	
		url+= "&reportType="+reportType;
		url+= "&qihbKeyid="+qihbKeyid;
		 var filterProcessId = jQuery('#cmbQirdProcessid').combobox('getValue');			 
		 var filterPhenomenaId = jQuery('#cmbQirdPhenomenaid').combobox('getValue');
		url+= "&filterProcessId="+filterProcessId+"&filterPhenomenaId="+filterPhenomenaId;
		var formId = jQuery('#submitForm').val();
		//alert("formId"+formId);
		
		if(formId.length > 0 ) {
			jQuery('#hdnSaveMode').val('DIRECT');
			saveForm(formId,url);
		}
	});
	
	jQuery('#btnView').click(function(){
		
		if (fnCommonValidation()==false)
			return false;
			
		fnFillProcessGrid();
	});
	
	function fnCommonValidation() {

		var cellId = jQuery("#frmIntRejEntry input[id='cell']").val();
		if(cellId =="" || cellId ==" ") {
			popupCommonErrorMsg(" Select JH ");
			return false;
		} 
		var date = jQuery('#dteQirmInspectiondate').datebox('getValue');
		if(date =="" || date ==" ") {
			popupCommonErrorMsg(" Select Date ");
			return false;
		}
		var shiftId = jQuery('#cmbQirmInspectedshiftid').combobox('getValue');
		if(shiftId =="" || shiftId ==" ") {
			popupCommonErrorMsg(" Select Shift ");
			return false;
		}
		return true;
	}


	jQuery('#btnAdd').click(function(){
	 
		var row  = jQuery("#intRejEntryGrid").jqGrid('getDataIDs');
		
		
		if (fnCommonValidation()==false)
			return false;


		var row  = jQuery("#intRejEntryGrid").jqGrid('getDataIDs');		
		
		if (row.length == 0) {  
			setTimeout(function() {fnFillProcessGrid();},250);
			setTimeout(function() {addRow(row);},350);						     
		}
		else
			addRow(row);
		
		//addNewGridRow('intRejEntryGrid');
	});


	jQuery('#btnDelete').click(function(){
		 
		var row  = jQuery("#intRejEntryGrid").jqGrid('getDataIDs');
		
		if (fnCommonValidation()==false)
			return false;
		
	});
	
	function  getIntCurrentShift(record)
	{
		jQuery('#cmbQirmInspectedshiftid').combobox('setValue',record.shift);
	}
	function frmIntRejEntrycmbQirdProcessid_onLoadSuccess() 	{
		setTimeout(function() {fnFillProcessGrid();},250);
	}
	
	function frmIntRejEntrycmbQirmCellid_onSelect(record) 	{
		jQuery('#cmbQirmMachineid').combobox('clear');
		var dataStr="&cellId="+record.id;
		loadFunctionalLocation("intRejTestFunctionLocation", "functionalLoc_lossCapture.pcs","intRejTestFunctionLocationEmp", "frmIntRejEntry", dataStr);
		
		/* var cellId = result.cellId;
		reloadCombo("frmIntRejEntry","cmbQirdProcessid","process.commonFilter?cellId="+cellId);
 */
			
	}
	
	function frmIntRejEntrycmbQirdProcessid_onSelect(record) 	{
		//jQuery('#cmbQirdSubprocessid').combobox('clear');
		reloadCombo("frmIntRejEntry","cmbQirdSubprocessid","subprocess.commonFilter?&processId="+record.id);
	}
	
	function frmIntRejEntrycmbQirdSubprocessid_onSelect(record) 	{
		//jQuery('#cmbQirdPhenomenaid').combobox('clear');
		reloadCombo("frmIntRejEntry","cmbQirdPhenomenaid","defphen.commonFilter");
	}
	
	function frmIntRejEntrycmbQirdPhenomenaid_onSelect(record) 	{
		//jQuery('#cmbQirdCauseid').combobox('clear');
		reloadCombo("frmIntRejEntry","cmbQirdCauseid","qtmCause.commonFilter?");
	}

	function frmIntRejEntrycmbQirmProductid_onSelect(record) 	{
		setTimeout(function() {fnFillProcessGrid();},250);	
	}

	function dteQirmInspectiondate_onChange(date) 	{
		setTimeout(function() {fnFillProcessGrid();},250);
	}

	function frmIntRejEntrycmbQirmInspectedshiftid_onSelect(record) 	{
		setTimeout(function() {fnFillProcessGrid();},250);
	}

	function frmIntRejEntrycmbQirmMachineid_onSelect(record) 	{
		var dataStr="&machId="+record.id;
		loadFunctionalLocation("intRejTestFunctionLocation", "functionalLoc_lossCapture.pcs","intRejTestFunctionLocationEmp", "frmIntRejEntry", dataStr);

		
	}

	function addRow(row)
	{
			 if ( row == null || row == '' || parseInt(row) <= 0) {
			 	var emptyItem =[{txtQirdMasterid:" ",txtQirdKeyid:" ",txtPLRKKEYID:" ",txtQirdProcessid:" ",txtQirdProcess:" ",txtQirdSubprocessid:" ",txtQirdSubprocess:" ",txtQirdPhenomenaid:" ",txtQirdPhenomena:" ",txtQirdCauseid:" ",txtQirdCause:" ",txtQirdQuantity:" ",txtQird4mtype:" ",txtQirdType:" ",txtQirdRemarks:" ",txtWnyWny:" ",txtselecttype:" "}];
				jQuery("#intRejEntryGrid").jqGrid('addRowData',1, emptyItem[0]);
			 }	
			 else
			 {
				for(var i=0;i<row.length;i++)
						lastRow = row[i];
				var emptyItem =[{txtQirdMasterid:" ",txtQirdKeyid:" ",txtPLRKKEYID:" ",txtQirdProcessid:" ",txtQirdProcess:" ",txtQirdSubprocessid:" ",txtQirdSubprocess:" ",txtQirdPhenomenaid:" ",txtQirdPhenomena:" ",txtQirdCauseid:" ",txtQirdCause:" ",txtQirdQuantity:" ",txtQird4mtype:" ",txtQirdType:" ",txtQirdRemarks:" ",txtWnyWny:" ",txtselecttype:" "}];
				jQuery("#intRejEntryGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
			 }
	}
	


function frmIntRejEntry_FuntLocHierarchy_SuccessCallBack(result){
	
	jQuery('#hdnSaveMode').val('');

	reloadCombo("frmIntRejEntry","cmbQirmProductid","comboGradeSpec.commonFilter?q&flid="+result.flId );
	
	var QmKeyid = jQuery('#hdnQirmKeyid').val();

	var sectId = result.sectId;
	//alert(sectId );	
	reloadCombo("frmIntRejEntry","cmbQirmCellid","cellCombo.commonFilter?&sectId="+sectId );
	
	if (QmKeyid == null || QmKeyid == '' || QmKeyid == ' ' )
	{
		var dataStr = "?&pcsEnabled=Y&factId="+"&cellId="+result.cellId;		
		reloadCombo("frmIntRejEntry","cmbQirmMachineid","machineCombo.commonFilter"+dataStr  );
	
		//var factId = "";		
		
		if (result.cellId!=null)
			setFieldValue('cmbQirmCellid',result.cellId);
		
		if (result.machId!=null)
			setFieldValue('cmbQirmMachineid',result.machId);
	}

	/* var date = jQuery('#dteQirmInspectiondate').datebox('getValue');
	dataStr = "&factId="+"&prdModelId=&mchId="+result.machId+"&entryDate="+date;
	reloadCombo("frmIntRejEntry","cmbQirmProductid","combo_product.pcs?"+dataStr);
	 */
	 
	fnFillProcessGrid();

	 if ((screen.width >= 1200)  && (screen.colorDepth>12))
		 jQuery('#dispFunctionalLoc').css('width',800);
	 else {
		 jQuery('#txaQirdRemarks').css('width',100);
		 jQuery('#txtQirdQuantity').css('width',30);
		 jQuery('#cmbQirdCauseid').css('width',80);
		 jQuery('#cmbQird4mtype').css('width',60);
	 }
	 
	var cellId = result.cellId;
	 reloadCombo("frmIntRejEntry","cmbQirdProcessid","process.commonFilter?cellId="+cellId+'&flid='+result.flId);
}

	function frmIntRejEntrycmbPhenomenaid_onSelect(record) {
		//fnFillProcessGrid();
	}
	
	
function fnFillProcessGrid() {
		
		var QirmKeyid=jQuery('#hdnQirmKeyid').val();
		
		var dataString="QirmKeyid="+QirmKeyid;
		var date = jQuery('#dteQirmInspectiondate').datebox('getValue');
		var shiftId = jQuery('#cmbQirmInspectedshiftid').combobox('getValue');
		var machId = jQuery('#cmbQirmMachineid').combobox('getValue');
		var prdId = jQuery('#cmbQirmProductid').combobox('getValue');
		var QirmKeyid=jQuery('#hdnQirmKeyidEntry').val();
		var processId = jQuery('#cmbQirdProcessid').combobox('getValue');
		var phenId =jQuery('#cmbQirdPhenomenaid').combobox('getValue');		
		var masterId =jQuery('#hdnQirmPlmasterid').val();
		var pldetailsId =jQuery('#hdnQirmPldetailid').val();
		var flid = jQuery("#frmIntRejEntry input[id='flid']").val();
		
			if(masterId=="" || masterId==" ") masterId="{}";
		
		dataString+= "PLDETAILSID="+pldetailsId+";MACHINEID="+machId+";DATE="+date+";MASTERID="+masterId+";ENTRYTYPE=D";
		dataString+= ";SHIFTID="+shiftId;
		dataString+= ";FLID="+flid;
		dataString+=";PROCESSID="+processId+";PHENOMENAID="+phenId;
		dataString+= ";PRODUCTID="+prdId;
		var reportType =jQuery('#hdnReportType').val();
		reportType="DIR";
		//reportType = (reportType === 'DIR') ? 'D' : reportType;
		dataString+= ";REPORTYPE="+reportType;
		dataString+= ";QIRMKEYID="+QirmKeyid;
		var qihbKeyid = jQuery('#hdnQihbKeyid').val();
		dataString+= ";QIHBKEYID="+qihbKeyid+";";
		//alert(dataString);
		dataString = encodeURIComponent(dataString);
		processGridnew('intRejEntryTestGrid_view.ire?condParam=',dataString,"intRejEntryGrid","intRejEntryPager","Internal Rejection Entry","intRejEntrydblClick","","intRejEntryGrid_loadComplete","intRejEntryGridError");		
		jQuery('#hdnSaveMode').val('');
		}
	


	function intRejEntrydblClick(id) { 
		
		//var row  = jQuery("#intRejEntryGrid").jqGrid('getDataIDs');
		
		var qirmKeyid = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdMasterid');
		var qirdKeyid = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdKeyid');
		var processId = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdProcessid');
		var subprocessId = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdSubprocessid');
		var phenId = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdPhenomenaid');
		var causeId = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdCauseid');
		
		var qty = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdQuantity');
		var type4m = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQird4mtype');
		var type = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdType');
		var remakrs = jQuery("#intRejEntryGrid").jqGrid('getCell',id,'txtQirdRemarks');
		
		jQuery('#hdnQirmKeyid').val(qirmKeyid);
		jQuery('#hdnQirdKeyid').val(qirdKeyid);
		
		jQuery('#cmbQirdProcessid').combobox('setValue',processId);
		jQuery('#cmbQirdSubprocessid').combobox('setValue',subprocessId);
		jQuery('#cmbQirdPhenomenaid').combobox('setValue',phenId);
		jQuery('#cmbQirdCauseid').combobox('setValue',causeId);
		
		jQuery('#cmbQird4mtype').combobox('setValue',type4m);
		jQuery('#cmbQirdType').combobox('setValue',type);
		
		jQuery('#txtQirdQuantity').val(qty);
		jQuery('#txaQirdRemarks').val(remakrs);
		
	
	}
	
	function intRejEntryGrid_loadComplete() {

		var row  = jQuery("#intRejEntryGrid").jqGrid('getDataIDs');
		var keyid = "";
		if (row.length >0) 
			keyid = jQuery("#intRejEntryGrid").jqGrid('getCell',1,2);
		//alert(keyid);
		jQuery('#hdnQirmKeyid').val(keyid);
	}

	function isHourlyRecallSuccess(result) {		
		if (result.value=="Y")		
			jQuery("#intRejEntryGrid").jqGrid('hideCol',"btnSave");
	}
						
	
	function intRejEntryGridError() {
		alert("Error");
	}

	/*function frmIntRejEntry_beforeSubmit()
	{   
		//var url = "internalRejection_save.ire?q&";
		var formId = jQuery('#submitForm').val();
		alert("formId"+formId);
		/*if(formId.length > 0 ) {
			alert("formId="+formId);
			jQuery('#hdnSaveMode').val('DIRECT');
			saveForm(formId,url);
		}
		var saveMode = jQuery('#hdnSaveMode').val('DIRECT');
		alert("saveMode"+saveMode);
		if (saveMode =="DIRECT")
			return true;
		else
		{
			var sectId = jQuery("#frmIntRejEntry input[id='section']").val();
			if (sectId==''  || sectId==' ') {
				popupCommonErrorMsg("Select DMT ");
				return false;
			}
			var gridArray = getGridSelectArray('intRejEntryGrid') ;
			//alert(gridArray);
			if (gridArray=='' || gridArray==' ')
				return false;
			
		  var Griddata ="&griddata="+ gridArray;	
		    //alert(Griddata);	
		    return Griddata;	
		}	
	
	}*/
	function frmIntRejEntry_beforeSubmit(){
		  
		  var qirmKeyid = jQuery('#hdnQirmKeyid').val();
		     var qirdKeyid = jQuery('#hdnQirdKeyid').val();
		  var datastring="&QirmKeyid="+qirmKeyid+"&QirdKeyid="+qirdKeyid;
		  return datastring;


		  }

	function grdBtnClick(id, rowId) {
		var keyid = jQuery("#intRejEntryGrid").jqGrid('getCell',rowId,3);
		var flid = jQuery("#frmIntRejEntry input[id='flid']").val();
		//var problem = jQuery("#txtQirdRemarks_intRejEntryGrid_"+rowId).val();
		var problem = jQuery("#intRejEntryGrid").jqGrid('getCell',rowId,"txtQirdRemarks");
		if (problem=="" || problem==" ")
			problem =  jQuery("#intRejEntryGrid").jqGrid('getCell',rowId,"txtQirdPhenomena");
			//problem = jQuery('#txtQirdPhenomena_intRejEntryGrid_'+rowId).combobox('getText');
		var refDocDate = jQuery('#dteQirmInspectiondate').datebox('getValue');
		openWhyWhy("divWhyWhy",false,keyid,"INR",flid, refDocDate, problem, "create");
		 //navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );
	}
	
	
	function frmIntRejEntry_successsCallback(result) {	
	
		var mode = result.openForm;
		 if (jQuery('#hdnQirmKeyidEntry').val()=="" || jQuery('#hdnQirmKeyidEntry').val()==" ")
			// alert("result key id "+result.keyid);	
		 	console.log("Key Id after save "+result.keyid);
			 jQuery('#hdnQirmKeyidEntry').val(result.keyId);											
			
		if(mode != null && mode != ' ' && mode != '' && mode == 'YY')
		{
			 var persistentData = result.persistentData;
			 var forwardData = result.forwardData;

			 var processId = jQuery('#cmbProcessid').combobox('getValue');			 
			 var phenomenaId = jQuery('#cmbPhenomenaid').combobox('getValue');
			 //persistentData={"processId":processId,"phenomenaId":phenomenaId};
			 //alert(Object.keys(persistentData));					 
			 //var persistentData = {"processId":processId,"phenomenaId":phenomenaId};
			 
			// var forwardData = {"processId":processId,"phenomenaId":phenomenaId};
			 
			 navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );			 
		}
		else
			{
			//jQuery("#intRejEntryGrid").jqGrid().trigger("reloadGrid");
			setTimeout(function() {fnFillProcessGrid();},250);
			}
		
		jQuery('#hdnQirdKeyid').val('');
		
		jQuery('#cmbQirdProcessid').combobox('clear');
		jQuery('#cmbQirdSubprocessid').combobox('clear');
		jQuery('#cmbQirdPhenomenaid').combobox('clear');
		jQuery('#cmbQirdCauseid').combobox('clear');
		
		jQuery('#cmbQird4mtype').combobox('clear');
		jQuery('#cmbQirdType').combobox('clear');
		
		jQuery('#txtQirdQuantity').val('');
		jQuery('#txaQirdRemarks').val('');
	
		setFocusOnField('cmbQirdProcessid');
        var savemode=jQuery('#hdnSaveMode').val();
		//alert("savemode"+savemode);
		
		if(savemode!="DIRECT"){
			setTimeout(function(){
			 navigateToPrevForm();
			 },1000);	 
				
			
			}   
		
		jQuery('#hdnSaveMode').val('');
	}
		
	

	function loadIntRejEntryPopup_onClose() {
		refreshForm();
		return true;		
	}
</script>

<form id="frmIntRejEntry" >
<div id="" style="width:100%;padding: 10px;">
<div class="easyui-paddingbfpx" style="padding-left: 10px;padding-top:0px; width:99%" >
		<div id="divIntRejFuncLoc" style="width: 537px; margin-left:4px;">
			<input type="hidden" id="location" name="cmbQirmLocationid"	value="" /> 
			<input type="hidden" id="factory" name="cmbQirmFactoryid"	value=""/>
			<input type="hidden" id="section" name="cmbQirmSectionid"	value=""/>
			<input type="hidden" id="cell" name="cmbQirmCellid"	value=""/>
			<input type="hidden" id="machine" name="cmbQirmMachine" value=""/>
			<input type="hidden" id="flid" name="cmbQirmFlid" value="${requestScope.QtmTlIntrejectionmst.qirmFlid}"/>
			<div id="intRejTestFunctionLocation" style="width: 100%; "></div>
			<span id="err_intRejTestFunctionLocation" class="tpm-errormsg"> </span>
		</div>
<div> 

<input type="hidden" id="hdnReportType" name="hdnReportType" value="${requestScope.reportType}"></div>
	<div> <input type="hidden" id="hdnOldQty" name="hdnOldQty"></div>
	<input type="hidden" id="mode" name="mode" >
	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
	<%-- <div> <input type="hidden" id="hdnQirmKeyidEntry" name="hdnQirmKeyidEntry" value="${requestScope.QirmKeyid}"></div> --%>
	<div> <input type="hidden" id="hdnQirmPlmasterid" name="hdnQirmPlmasterid" value="${requestScope.Plmasterid}"></div>
	<div> <input type="hidden" id="hdnQirmPldetailid" name="hdnQirmPldetailid" value="${requestScope.Pldetailsid}"></div>	
	<div> <input type="hidden" id="hdnPtwokeyid"></div>
	
	<input type="hidden" id="factory" name="hdnQirmFactoryid" value="${requestScope.factId}"></input>
	<input type="hidden" id="machine" name="hdnQirmMachineid" value="${requestScope.machId}"  ></input>
	<input type="hidden" id="hdnQirmEntrydate" name="hdnQirmEntrydate" value="${requestScope.date}"  ></input>	
	<input type="hidden" id="hdnQirmShiftid" name="hdnQirmShiftid" value="${requestScope.shift}"  ></input>
	<input type="hidden" id="cell" name="cmbQirmCellid" value="${requestScope.cellId}"  ></input>
	<input type="hidden" id="section" name="cmbQirmSectionid" value="${requestScope.sectId}"  ></input>
	
	<input type="hidden" id="hdnQihbKeyid" name="hdnQihbKeyid" value="${requestScope.QihbKeyid}"  ></input>
	
	<div style="margin-left: px;margin-top: px">	
	
	<div >
		<label >JH </label>
		<label style="padding-left: 210px">Equipment</label>
		<label class="mandatory-lbl" style="padding-left: 170px">Grade Specification</label>
		<label class="mandatory-lbl" style="padding-left: 145px">Date</label>
		<label class="mandatory-lbl" style="padding-left: 75px;">Shift</label>
		
	</div>
	<!-- <div>
	 	<img src="/image?root=/images&file=/ARC Welding Machine.jpg&width=270&height=100"> 
		<img src="/imageResizer?file=/images/check.JPG&width=270&height=100 ">
	</div> 
	-->
	
	<div >
		<input type="text" class="easyui-combobox"  id="cmbQirmCellid" name="cmbQirmCellid" value="${requestScope.QtmTlIntrejectionmst.qirmCellid}" style="width: 220px;"/>
		<span style="padding-left: 10px">
			<input type="text" class="easyui-combobox"  id="cmbQirmMachineid" name="cmbQirmMachineid" value="${requestScope.QtmTlIntrejectionmst.qirmMachineid}" style="width: 220px;"/>
		</span>					
		<span style="padding-left: 10px">
			<input id="cmbQirmProductid" name="cmbQirmProductid"  class="easyui-combobox" type="text" value="${requestScope.QtmTlIntrejectionmst.qirmProductid}" style="border:1px solid black; font-size:10px ; width:250px;/* height:20px; */color:black;font-weight:bold;text-align:center; " />
		</span>
		<span style="padding-left: 10px">
			<input class="easyui-text" id="dteQirmInspectiondate" name="dteQirmInspectiondate" type="text" value="${requestScope.QtmTlIntrejectionmst.qirmInspectiondate}" style="width:100px;height:20px;color:black;" />
		</span>
		<span style="padding-left: 10px">
			<input type="text" class="easyui-combobox" id="cmbQirmInspectedshiftid" name="cmbQirmInspectedshiftid" readonly="readonly"  style="width: 80px;"  value="${requestScope.QtmTlIntrejectionmst.qirmInspectedshiftid}"/>
		</span>
		<span style="padding-left: 10px">
			<input type="button" value="View"  id="btnView" class="easyui-button">
		</span>
		
		<span style="padding-left: 10px;display: none;">
			<input type="button" value="Add"  id="btnAdd" class="easyui-button" >
		</span>
		<!-- <span style="padding-left: 10px">
			<input type="button" value="Delete"  id="btnDelete" class="easyui-button" >
		</span>
 -->
	</div>	


	<div id="filter" style="display: block;width: 105%;">
		<div class="easyui-paddingbfpx" style="padding-top: 0px">
			<label class="mandatory-lbl">Process</label>
			<label class="mandatory-lbl" style="padding-left: 115px">Sub-Process</label>
			<label class="mandatory-lbl" style="padding-left: 75px">Phenomena</label>
			<label style="padding-left: 95px">Cause</label>
			<label class="mandatory-lbl" style="padding-left: 115px">Qty</label>
			<label class="mandatory-lbl" style="padding-left: 40px">4m Type</label>
			<label class="mandatory-lbl" style="padding-left: 60px">Type</label>
			<label style="padding-left: 25px">Remarks</label>
		</div>
		
		<!-- var emptyItem =[{txtQirdMasterid:" ",txtQirdKeyid:" ",txtPLRKKEYID:" ",txtQirdProcessid:" ",txtQirdProcess:" ",txtQirdSubprocessid:" ",txtQirdSubprocess:" ",txtQirdPhenomenaid:" ",txtQirdPhenomena:" ",txtQirdCauseid:" ",txtQirdCause:" ",txtQirdQuantity:" ",txtQird4mtype:" ",txtQirdType:" ",txtQirdRemarks:" ",txtWnyWny:" ",txtselecttype:" "}]; -->
		<div >		 
			<span style="padding-left: 0px;vertical-align:top;">
				<input type="text" class="easyui-combobox" id="cmbQirdProcessid" name="cmbQirdProcessid" value="${requestScope.processId}" style="width: 160px;"/>
			</span>					
			<span style="padding-left: 10px;vertical-align:top;">
				<input class="easyui-combo" id="cmbQirdSubprocessid" name="cmbQirdSubprocessid" type="text" value="${requestScope.subprocessId}" style="width:150px;" />
			</span>
			<span style="padding-left: 10px;vertical-align:top;">
				<input class="easyui-combo" id="cmbQirdPhenomenaid" name="cmbQirdPhenomenaid" type="text" value="${requestScope.phenomenaId}" style="width:150px;" />
			</span>
				
			<span style="padding-left: 10px;vertical-align:top;">
				<input class="easyui-combo" id="cmbQirdCauseid" name="cmbQirdCauseid" type="text" value="${requestScope.causeId}" style="width:150px;" />
			</span>
			
			<span style="padding-left: 10px;vertical-align:top;">
				<input class="easyui-text" id="txtQirdQuantity" name="txtQirdQuantity" type="text" value="${requestScope.qirdQty}" style="width:50px;" />
			</span>
			<span style="padding-left: 10px;vertical-align:top;">
				<select   id='cmbQird4mtype' style='width:100px;' name='cmbQird4mtype'class='easyui-combobox'>
					<option  value=' ' selected="selected">  </option>
					<option  value='MAN' >MAN</option>
					<option  value='METHOD'>METHOD</option>
					<option  value='MATERIAL' >MATERIAL</option>
					<option  value='MACHINE' >MACHINE</option>
				</select>
			</span>
			
			<span style="padding-left: 10px;vertical-align:top;">
				<select   id='cmbQirdType' style='width:50px;' name='cmdQirdType'class='easyui-combobox'>
					<option  value=' '>  </option>
					<option  value='A' >A</option>
					<option  value='B'>B</option>
					<option  value='C' >C</option>
				</select>
			</span>
			
			<span style="padding-left: 10px;vertical-align:top;">
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txaQirdRemarks" name="txaQirdRemarks" maxlength="495" style="width: 200px;height: 40px" rows="4" cols="1"></textarea>
			</span>
			<span style="padding-left: 10px">
				<input type="button" value="Save"  id="btnSave" class="easyui-button" >
			</span>
				
			
		</div>	
	</div>
	
	<div style="" class="internalRejectionEntryDiv">			
			<table id="intRejEntryGrid" style="float: left: ;"></table>
	</div>	
	
	</div>
	</div>
	

</div>
<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnQirmKeyid" name="hdnQirmKeyid" value="${requestScope.QtmTlIntrejectionmst.qirmKeyid}"/>
<input type="hidden" id="hdnQirdKeyid" name="hdnQirdKeyid" value=""/>
<input type="hidden" id="hdnSaveMode" name="hdnSaveMode" value=""/>
<input type="hidden" id="hdnTypes" name="hdnTypes" value="${requestScope.types}"/>

</form>