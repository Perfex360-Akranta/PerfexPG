<script type="text/javascript">
	
	jQuery(document).ready(function(){
	//alert(jQuery('#hdnQihbKeyid').val());
			initialiseForm('frmIntRejHourBreak');
		jQuery('#submitForm').val('frmIntRejHourBreak');		
		fillComboBox("frmIntRejHourBreak","cmbMachineNo","machineCombo.commonFilter" );
		checkQirmKeyid();			
		numericTextBox('txtQirmInspectionqty');
		formatDateBox('dteQirmInspectiondate','dd-MMM-yyyy');		
		readOnlyFields("txtQirmInspectionqty");
		fnFillProcessGrid();


		var inspectedQty = jQuery('#txtQirmInspectionqty').val();
		if (inspectedQty=="" || inspectedQty == " ") {
			jQuery('#txtQirmInspectionqty').val("0");
		}

		var acceptQty = jQuery('#txtQirmAcceptedqty').val();
		if (acceptQty=="" || acceptQty == " ") {
			jQuery('#txtQirmAcceptedqty').val("0");
		}

		var rejQty = jQuery('#txtQirmBacklogqty').val();
		if (rejQty=="" || rejQty == " ") {
			jQuery('#txtQirmBacklogqty').val("0");
		}

		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");

	});

	function getFieldsForPcsSuccess(result) {
		if (result.value.indexOf("EQ") != -1) {
			jQuery('#spnLblExpanQty').hide();
			jQuery('#spnExpanQty').hide();			
		}
	}	
	function checkQirmKeyid() {
		
			/*var Pldetailsid =jQuery('#hdnQirmPldetailid').val();
			var prdId = jQuery("#cmbQirmProductid").combobox('getValue');
			var dataStr="?q=2&QirmPldetailsid="+Pldetailsid+"&QirmProductid="+prdId;
			//alert(dataStr);
			processAjaxCalls('checkQirm.ire',dataStr,"checkQirmSuccess");*/
		
	}

	function checkQirmSuccess(result) {
	
		jQuery('#hdnQirmKeyid').val(result.QirmKeyid);
	}
	
	function frmIntRejHourBreakcmbMachineNo_onLoadSuccess() 	{		
		fillComboBox("frmIntRejHourBreak","cmbQirmInspectionid","employee.commonFilter" );
		
	}

	function frmIntRejHourBreakcmbQirmInspectionid_onLoadSuccess() 	{
		var factId = jQuery("#frmIntRejHourBreak input[id='factory']").val();
		fillComboBox("frmIntRejHourBreak","cmbQirmInspectedshiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");		
	}

	function frmIntRejHourBreakcmbQirmInspectedshiftid_onLoadSuccess() {		
		var factId = jQuery("#frmIntRejHourBreak input[id='factory']").val();
		var machId = jQuery('#cmbMachineNo').combobox('getValue');
		var entryDate = jQuery('#hdnQirmEntrydate').val();
		var dataStr = "&factId="+factId+"&prdModelId=+&mchId="+machId+"&entryDate="+entryDate;
		fillComboBox("frmIntRejHourBreak","cmbQirmProductid","combo_product.pcs?q=2"+dataStr);	
	}

/*	function frmIntRejHourBreakcmbQirmProductid_onLoadSuccess() {		
		fillComboBox("frmIntRejHourBreak","cmbProcessid","combo_process.ire");			
	}
	function frmIntRejHourBreakcmbProcessid_onLoadSuccess() {		
		var dataStr ="?q=2&processId="+jQuery('#cmbProcessid').combobox('getValue');
		fillComboBox("frmIntRejHourBreak","cmbPhenomenaid","combo_Phenomena.ire"+dataStr );			
	}
	function frmIntRejHourBreakcmbProcessid_onSelect(record) {
		var dataStr ="?q=2&processId="+jQuery('#cmbProcessid').combobox('getValue');
		reloadCombo("frmIntRejHourBreak","cmbPhenomenaid","combo_Phenomena.ire"+dataStr );
	}
*/	

	
	function fnFillProcessGrid() {				
		var dataString="?q=2";
		var qirmKeyid = jQuery('#hdnQirmKeyid').val();
		
		if(qirmKeyid=="" || qirmKeyid==" " || qirmKeyid=="{}")
			dataString+="&qirmKeyid=";
		else			
			dataString+="&qirmKeyid="+qirmKeyid;
		//alert(dataString);		
		processGridnew('intRejHourBreakGrid_view.ire',dataString,"intRejHourBreakGrid","intRejHourBreakPager","Internal Rejection Hourly Breakup","","","intRejHourBreakGrid_loadComplete","intRejHourBreakGridError");		
	}
	
	function intRejHourBreakGrid_loadComplete() {
		//alert("loaddd");		
		var rowIds = jQuery("#intRejHourBreakGrid").getDataIDs();		
		for(var id = 1; id<=rowIds.length; id++) {
			numericTextBox('txtQihbInspectedqty_'+id);
			jQuery('#txtQihbInspectedqty_'+id).css("text-align", "center");
			jQuery("#intRejHourBreakGrid").jqGrid('setCell',id,"txtHour","",{'font-size':12});
			jQuery("#intRejHourBreakGrid").jqGrid('setCell',id,"txtQihbAcceptedqty","",{'background-color':'#C7CEFD'});
			jQuery("#intRejHourBreakGrid").jqGrid('setCell',id,"txtQihbRejectedqty","",{'background-color':'#C7CEFD'});			
		}
		
		disableField("frmIntRejHourBreak","txtQihbInspectedqty_"+rowIds.length);
		
		jQuery("#intRejHourBreakGrid").jqGrid('setCell',rowIds.length,"txtQihbAcceptedqty","",{'background-color':'#C7CEFD'});
		jQuery("#intRejHourBreakGrid").jqGrid('setCell',rowIds.length,"txtQihbRejectedqty","",{'background-color':'#C7CEFD'});
		jQuery("#intRejHourBreakGrid").jqGrid('setCell',rowIds.length,"txtHour","",{'font-size':12,'font-weight':'bold'});
	}
	function intRejHourBreakGridError() {
		alert("Error");
	}

	function btnSaveFormatter(id, options, rowObject){
		var id = options.rowId;
	  	return '<input type="button" class="easyui-button" style="width:40px;" value="Save" onclick="saveData(\''+id + '\');"/>';  	
	}
	
	function saveData(id) {		
		if (checkInspectedDetail() == false)
			return;	

		var formId = jQuery('#submitForm').val();
		var rowData = jQuery("#intRejHourBreakGrid").jqGrid('getRowData',id);
		var QirmKeyid=jQuery('#hdnQirmKeyid').val();
		//var QirmKeyid=rowData.cmbQihdQtmKeyid;
		
		var QihbKeyid=rowData.cmbQihdKeyid;		 
		
		var inspectedQty=jQuery('#txtQihbInspectedqty_'+id).val();
		
 		if (!parseInt(inspectedQty)> 0 ) {
			alert("Enter Inspected Qty");
			return false;
		}

 		var totProduced=jQuery('#txtQirmTotalproduction').val(); 		
 		var expansionQty=jQuery('#txtQirmExpansionQty').val();
 		if (parseInt(expansionQty)>0 )
 			totProduced=expansionQty;
			
 		var totIns = "0";
 		totIns=parseInt(totIns);
 		var reccnt = jQuery("#intRejHourBreakGrid").getDataIDs().length; 		
 		for (var i=1;i<=reccnt-1;i++) {
 	 		var insQty=jQuery('#txtQihbInspectedqty_'+i).val();
 	 		if (insQty=="" || insQty==" ") insQty="0";
 			totIns += parseInt(insQty);	
 	 	}
	 	//alert(totIns);
 		if ((parseInt(totIns)) > parseInt(totProduced) ) {
 			if (parseInt(expansionQty)>0 )
				alert("Inspected Qty should be less than Expansion Qty");
 			else
 				alert("Inspected Qty should be less than Total Produced Qty");
			return false;
		}
		
		var QihbShifthour =rowData.txtHour.substring(0,1);

		var QihbAcceptedqty =rowData.txtQihbAcceptedqty.trim();
		var QihbRejectedqty =rowData.txtQihbRejectedqty.trim();
		var QihbBalanceqty = QihbAcceptedqty-QihbRejectedqty.trim();
		
		/*if(QihbRejectedqty != null && QihbRejectedqty != '' && QihbRejectedqty != ' ')
		{
			var qnty = parseInt(QihbRejectedqty);
			if(QihbAcceptedqty != null && QihbAcceptedqty != '' && QihbAcceptedqty != ' ')
			{
				qnty += parseInt(QihbAcceptedqty);
			}
			if (parseInt(inspectedQty)> parseInt(qnty) ) {
				alert("Inspected Qty Should not be greater than sum of Accepted Qty  and Rejection Qty");
				return false;
			}
		}*/
		var QihbTestingqty =rowData.txtQihbTestingqty.trim();
		var QihbMrbqty =rowData.txtQihbMrbqty.trim();
		var QihbQahold =rowData.txtQihbQahold.trim();
		
		var url = "internalHourlyBreak_save.ire?q=2" ;
		url+= "&saveMode=create";
		url+= "&QirmKeyid="+QirmKeyid+"&QihbKeyid="+QihbKeyid;		
		url+= "&inspectedQty="+inspectedQty+"&QihbShifthour="+QihbShifthour;
		url+= "&QihbAcceptedqty="+QihbAcceptedqty+"&QihbRejectedqty="+QihbRejectedqty;
		url+= "&QihbBalanceqty="+QihbBalanceqty+"&QihbTestingqty="+QihbTestingqty;
		url+= "&QihbMrbqty="+QihbMrbqty+"&QihbQahold="+QihbQahold;
		//alert(url);
		if(formId.length > 0 ) { 
			saveForm(formId,url);
			return true;
		}		
	}

	function frmIntRejHourBreak_successsCallback(result) {
		//alert(result.qirmId);	
		//var mode = result.successData.mode;	
		//jQuery("#intRejHourBreakGrid").jqGrid().trigger("reloadGrid");
		jQuery('#hdnQirmKeyid').val(result.qirmId);		
		jQuery('#hdnQihbKeyid').val(result.qihbId);
		
		var entryType=jQuery('#hdnEntryType').val();
		
		//if (! entryType=="" || entryType ==null) {
		if ( entryType != "" && entryType != null) {
			if(entryType == "TSP")
			{
				var cont=confirm("Do you want to Enter Breakup?");
				if (cont==true)	 
				{
					var referenceId = result.qirmId;
					var rejectionId = result.qihbId;
					var inspectedQty = result.inspectedQty;
					var QAHoldQty = result.QAHoldQty;
					var dataString = '?q=2';
					if ( referenceId != "" && referenceId != null)
						dataString += '&referenceId='+referenceId;
					if ( rejectionId != "" && rejectionId != null)
						dataString += '&rejectionId='+rejectionId;
					if ( inspectedQty != "" && inspectedQty != null)
						dataString += '&inspectedQty='+inspectedQty;
					if ( QAHoldQty != "" && QAHoldQty != null)
						dataString += '&QAHoldQty='+QAHoldQty;
				
					LoadPopUp("loadTestingScrap", "testingscrap_input.ire"+dataString,  true,"90%","80%","1px","1px",  "showBreakup_successCallBack","Testing Scrap Breakup", true,true);
				}
				else
					fnFillProcessGrid();
			}
			else{
				var cont=confirm("Do you want to Enter Rejection?");
				if (cont==true)	 
					fnLoadRejectionEntry(entryType);
				else
					fnFillProcessGrid();
			}
			
		}
		else
			fnFillProcessGrid();
	}
		
	function checkInspectedDetail() {		
		var inspectedBy = jQuery('#cmbQirmInspectionid').combobox('getValue');
		if (inspectedBy=="" || inspectedBy == " ") {
			alert("Select Inspected By");		return false;
		} 
		var inspectedDate = jQuery('#dteQirmInspectiondate').datebox('getValue');
		if (inspectedDate=="" || inspectedDate == " ") {
			alert("Select Inspected Date");		return false;
		}
		var inspectedShift = jQuery('#cmbQirmInspectedshiftid').combobox('getValue');
		if (inspectedShift=="" || inspectedShift== " ") {
			alert("Select Inspected Shift");		return false;
		} 		 			
		var inspectedQty = jQuery('#txtQirmInspectionqty').val();
		if (inspectedQty=="" || inspectedQty == " ") {
			alert("Enter Inspected Qty");		return false;
		}
		return true;
	}

	function loadIntRejEntryPopup_onClose() {
		//alert(1);
		refreshForm();
		return true;		
	}

	function intRejHourBreakGrid_keypress(keycode,iRow,iCol){
		//jQuery('#hdnEntryType').val('');
		// 122 - F11 , 119 - F8						
		if(keycode == 119 && iCol==7)
		{
			jQuery('#hdnEntryType').val('TSP');	
			saveData(iRow);				
		}
		if(keycode == 119 && iCol==8) {
			jQuery('#hdnEntryType').val('MRB');
			saveData(iRow);				
		}
		else if(keycode == 119 && iCol==9) {
			jQuery('#hdnEntryType').val('QAH');
			saveData(iRow);
		}
		

	}

	function fnLoadRejectionEntry(reportType) {
		
		var dataString="?q=2";
		
		var date=jQuery('#txtQirmProductiondate').val();				
		var shift=jQuery('#hdnQirmShiftid').val();
		
		var factId = jQuery("#frmIntRejHourBreak input[id='factory']").val();
		var sectId = jQuery("#frmIntRejHourBreak input[id='section']").val();
		var cellId = jQuery("#frmIntRejHourBreak input[id='cell']").val();
		var machId = jQuery('#cmbMachineNo').combobox('getValue');
		
		//var rowData = jQuery("#intRejViewGrid").jqGrid('getRowData',id);
		var Plmasterid = jQuery('#hdnQirmPlmasterid').val();		
		var Pldetailsid =jQuery('#hdnQirmPldetailid').val();		
		var QirmKeyid = jQuery('#hdnQirmKeyid').val();
		var rowId = jQuery("#intRejHourBreakGrid").jqGrid('getGridParam', 'selrow');
		var rowData = jQuery("#intRejHourBreakGrid").jqGrid('getRowData',rowId);
		var QihbKeyid=rowData.cmbQihdKeyid;
		//alert(QihbKeyid);		
		if (QihbKeyid.trim()=="" || QihbKeyid == null) {
			QihbKeyid = jQuery('#hdnQihbKeyid').val();
		}

		//alert('QirmKeyid'+QirmKeyid);
		//alert('QihbKeyid'+QihbKeyid); 
	
		//var acceptedQty = jQuery('#txtQirmAcceptedqty').val();
		//var rejectedQty = jQuery('#txtQirmBacklogqty').val();
		var acceptedQty = rowData.txtQihbAcceptedqty;
		var rejectedQty = rowData.txtQihbRejectedqty;
		var inspectedBy = jQuery('#cmbQirmInspectionid').combobox('getValue'); 		
		var inspectedDate =   jQuery('#dteQirmInspectiondate').datebox('getValue');
		var inspectedShift = jQuery('#cmbQirmInspectedshiftid').combobox('getValue');
		//var inspectedQty = jQuery('#txtQirmInspectionqty').val();
		var inspectedQty = jQuery('#txtQihbInspectedqty_'+rowId).val();
			
		
		//alert('Pldetailsid'+Pldetailsid);
		var prdId = jQuery("#cmbQirmProductid").combobox('getValue');
		var totalProduced =  jQuery('#txtQirmTotalproduction').val();
		
		var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&machId="+machId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid+"&QirmKeyid="+QirmKeyid;
		dataString+= "&totalProduced="+totalProduced+"&acceptedQty="+acceptedQty+"&rejectedQty="+rejectedQty;
		dataString+= "&inspectedBy="+inspectedBy+"&inspectedDate="+inspectedDate+"&inspectedShift="+inspectedShift;
		dataString+= "&inspectedQty="+inspectedQty;

		dataString+= "&QihbKeyid="+QihbKeyid;
			
		//alert(dataString);
		if (prdId == " " ||  prdId == "" ||  machId == "" ||  machId == " " )
			return;
		
		dataString+= "&reportLocation=";
		dataString+= "&reportType="+reportType+"&";
		//alert(dataString);
		//alert(reportType);
		//var persistentData = result.persistentData;
		//var persistentData = {"machId":machId,"PrdId":PrdId,"totalProduced":totalProduced,"EntryDate":EntryDate,"acceptedQty":acceptedQty,"rejectedQty":rejectedQty,"inspectedBy":inspectedBy,"inspectedDate":inspectedDate,"inspectedShift":inspectedShift,"inspectedQty":inspectedQty};
		var persistentData = {"QirmKeyid":QirmKeyid};
		var forwardData = {"QirmKeyid":QirmKeyid};		
		navigateToNextForm('intRejEntry_input.ire'+dataString,'Internal Rejection Entry',null,persistentData);
		
	}

	function txtInspectedQtyFormatter(id, options, rowObject){
		var id = options.rowId;		
		return '<input title="txtQihbInspectedqty_'+id +'" onfocus="gotFocuse('+id +')" value="'+rowObject[4]+'" id="txtQihbInspectedqty_'+id +'" name="txtQihbInspectedqty_'+id +'" style="border:0px;width:90px;" type="text" class="easyui-text"  "/>';
		
	}

	function gotFocuse(id) {		
		var oldQty=jQuery('#txtQihbInspectedqty_'+id).val().trim();
		if (oldQty=="" || oldQty==" ") oldQty="0";
		jQuery('#hdnOldInsQty').val(oldQty);		
	}
	

/*	function txtAcceptedqtyFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="txtQihbAcceptedqty_'+id +'" id="txtQihbAcceptedqty_'+id +'" name="txtQihbAcceptedqty_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbAcceptedqty_'+id +'");
	}

	function txtRejectedqtyFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="txtQihbRejectedqty_'+id +'" id="txtQihbRejectedqty_'+id +'" name="txtQihbRejectedqty_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbInspectedqty_'+id +'");
	}

	function txtTestingqtyFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="txtQihbTestingqty_'+id +'" id="txtQihbTestingqty_'+id +'" name="txtQihbTestingqty_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbTestingqty_'+id +'");
	}

	function txtMrbqtyFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="txtQihbMrbqty_'+id +'" id="txtQihbMrbqty_'+id +'" name="txtQihbMrbqty_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbMrbqty_'+id +'");
	}

	function txtInspectedQtyFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="Qty_'+id +'" id="txtQihbInspectedqty_'+id +'" name="txtQihbInspectedqty_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbInspectedqty_'+id +'");
	}

	function txtQaholdFormatter(id, options, rowObject){
		var id = options.rowId;
		return '<input title="txtQihbQahold_'+id +'" id="txtQihbQahold_'+id +'" name="txtQihbQahold_'+id +'" style="border:0px;width:120px;" type="text" class="easyui-text"  "/>';
		numericTextBox("txtQihbQahold_'+id +'");
	}
*/	
	
</script>

<form id="frmIntRejHourBreak" >
<div id="wrapper" style="width:100%">
<div class="easyui-paddingbfpx" style="padding-left: 10px;padding-top:10px; width:99%" >
	<div> <input type="hidden" id="hdnFormType" name="hdnFormType" value="${requestScope.reportLocation}"></div>
	<div> <input type="hidden" id="hdnQirmKeyid" name="hdnQirmKeyid" value="${requestScope.QirmKeyid}"></div>
	
		<div> <input type="hidden" id="hdnEntryType" name="hdnEntryType"></div>
		<div> <input type="hidden" id="hdnOldInsQty" name="hdnOldInsQty"></div>	
	
	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
	<div> <input type="hidden" id="hdnQihbKeyid" name="hdnQihbKeyid" value="${requestScope.QihbKeyid}"></div>
	<div> <input type="hidden" id="hdnQirmParentmasterid" name="hdnQirmParentmasterid" value="${requestScope.parentId}"></div>
	<div> <input type="hidden" id="hdnQirmLinkmasterid" name="hdnQirmLinkmasterid" value="${requestScope.linkId}"></div>
	<div> <input type="hidden" id="hdnQirmPlmasterid" name="hdnQirmPlmasterid" value="${requestScope.Plmasterid}"></div>
	<div> <input type="hidden" id="hdnQirmPldetailid" name="hdnQirmPldetailid" value="${requestScope.Pldetailsid}"></div>	
	<div> <input type="hidden" id="hdnPtwokeyid"></div>
	<input type="hidden" id="factory" name="hdnQirmFactoryid" value="${requestScope.factId}"></input>
	<input type="hidden" id="machine" name="hdnQirmMachineid" value="${requestScope.machId}"  ></input>
	<input type="hidden" id="hdnQirmEntrydate" name="hdnQirmEntrydate" value="${requestScope.date}"  ></input>	
	<input type="hidden" id="hdnQirmShiftid" name="hdnQirmShiftid" value="${requestScope.shift}"  ></input>
	<input type="hidden" id="cell" name="cmbQirmCellid" value="${requestScope.cellId}"  ></input>
	<input type="hidden" id="section" name="cmbQirmSectionid" value="${requestScope.sectId}"  ></input>
	
	<div style="margin-left: px;margin-top: px">	
	<div >
		<label class="mandatory-lbl">Machine No</label>
		<label class="mandatory-lbl" style="padding-left: 155px">Product</label>
		<label class="mandatory-lbl" style="padding-left: 104px; padding-left: 107px\9">Total Produced</label>
		<span id="spnLblExpanQty">
		<label class="mandatory-lbl" style="padding-left: 16px">Expansion Qty</label></span>
		<label class="mandatory-lbl" style="padding-left: 28px">Product Date</label>
		<label class="mandatory-lbl" style="padding-left: 27px">Accepted Qty</label>
		<label class="mandatory-lbl" style="padding-left: 24px">Rejected Qty</label>
	</div>
	<div >		
		<input type="text" id="cmbMachineNo" name="cmbMachineNo" disabled value="${requestScope.machId}" style="width: 210px;"/>					
		<span style="padding-left: 10px">
			<input id="cmbQirmProductid" name="cmbQirmProductid"  class="easyui-combobox" type="text" value="${requestScope.PrdId}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:140px;height:20px;color:black;font-weight:bold;text-align:center; " />
		</span>
		<span style="padding-left: 10px">		
			<input id="txtQirmTotalproduction" name="txtQirmTotalproduction" type="text" value="${requestScope.totalProduced}"  disabled="disabled" style="text-align:right;border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold; " />
		</span>
		<span id="spnExpanQty" style="padding-left: 10px">		
			<input id="txtQirmExpansionQty" name="txtQirmExpansionQty" type="text" value="${requestScope.expansionQty}"  disabled="disabled" style="text-align:right;border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold; " />
		</span>
		
		<span style="padding-left: 20px">
			<input type="text" id="txtQirmProductiondate" name="txtQirmProductiondate" readonly="readonly" disabled="disabled" style="border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;"  value="${requestScope.EntryDate}"/>
		</span>
		<span style="padding-left: 10px">
			<input type="text" id="txtQirmAcceptedqty" name="txtQirmAcceptedqty" readonly="readonly" disabled="disabled" style="text-align:right;border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;"  value="${requestScope.acceptedQty}"/>
		</span>			
		<span style="padding-left: 10px">
			<input type="text" id="txtQirmBacklogqty" name="txtQirmBacklogqty" readonly="readonly" disabled="disabled" style="text-align:right;border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;"  value="${requestScope.rejectedQty}"/>
		</span>		
	</div>	

<!--	--><div class="easyui-paddingbfpx" style="padding-top: 0px">
		<label class="mandatory-lbl">Inspected By</label>
		<label class="mandatory-lbl" style="padding-left: 149px">Inspected Date</label>
		<label class="mandatory-lbl" style="padding-left: 63px;padding-left: 65px\9">Inspected Shift</label>
		<label class="mandatory-lbl" style="padding-left:18px">Total Inspected Qty</label>		
	</div>
	
	<div >		 
		<input type="text" class="easyui-combobox" id="cmbQirmInspectionid" name="cmbQirmInspectionid" value="${requestScope.inspectedBy}" style="width: 210px;"/>					
		<span style="padding-left: 10px">
			<input class="easyui-text" id="dteQirmInspectiondate" name="dteQirmInspectiondate" type="text" value="${requestScope.inspectedDate}" style="width:140px;height:20px;color:black;" />
		</span>
		<span style="padding-left: 10px">
			<input type="text" class="easyui-combobox" id="cmbQirmInspectedshiftid" name="cmbQirmInspectedshiftid" readonly="readonly"  style="width: 90px;"  value="${requestScope.inspectedShift}"/>
		</span>
		<span style="padding-left: 12px;padding-left: 10px\9;">
			<input type="text"  class="easyui-text" id="txtQirmInspectionqty" name="txtQirmInspectionqty" style="width: 85px;text-align:right;"  value="${requestScope.inspectedQty}"/>
		</span>
	</div>	
	<div style="padding-top: 1%;">	
		<span style="padding-left: 10%;padding-top: 1%;">
			<input type="text" value="Press F8 on Testing Scrap /  QA Hold Qty to enter Phenomena Rejection Breakup" disabled="disabled" style="border:1px solid black; font-size:12px ; width:810px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
		</span>		
	</div>
	
	<div style="margin-left:-2%;margin-left:0%\9;" class="intRejHourBreakDiv" >			
			<table id="intRejHourBreakGrid"></table>
	</div>
	
	</div>
	</div>
	</div>
</form>