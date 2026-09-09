<script type="text/javascript">
	
	jQuery(document).ready(function(){

		jQuery('.LoadPopUpContent').css('overflow','hidden');
		jQuery('#loadLossPopUp').css('overflow','hidden');		
		
		initialiseForm('frmPcsLossEntry');
		jQuery('#submitForm').val('frmPcsLossEntry');
		numericTextBox('txtPlrkMinutes');
		numericTextBox('txtPlrkInstance');

		jQuery('#frmPcsLossEntry textarea').css('text-transform', 'uppercase');
		
		var parentId = jQuery('#hdnLossParentId').val();

		fillValueForQtyOrLoss();

		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		fillComboBox("frmPcsLossEntry","cmbPlrkLossid","combo_Loss.pcs?q=2&isQtyLoss="+isQtyLoss );

		fillLossEntryGrid();

		
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');		
		if  (lossId!="" && lossId != " ") {
			var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
			if (isQtyLoss =="Y")
				reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
			else				
				reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_Phenomena.pcs?q=2&lossId="+lossId);
		}
		else
			jQuery('#cmbPlrkLossid').combobox('clear');	

//ancilliary
		fillComboBox("frmPcsLossEntry","cmbWomsMchmid","machineCombo.commonFetch" );

		
		formatDateBox('dteFromDate','dd-MMM-yyyy');
		formatDateBox('dteTillDate','dd-MMM-yyyy');
		fillWithCurrentDate('dteFromDate');		
		fillWithCurrentDate('dteTillDate');

		var factId = jQuery("#frmPcsLossEntry input[id='factory']").val();		
		var sectionId = jQuery("#frmPcsLossEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsLossEntry input[id='cell']").val();
		var machId = jQuery("#frmPcsLossEntry input[id='machine']").val();							
  	    var dataStr = "&cmbfactoryid="+factId+"&cmbSectionid="+sectionId+"&cmbCellid="+cellId+"&cmbMachineid="+machId;	  					
		
		if (machId != null)
	   		loadFunctionalLocation("pcsLossEntryfunLocation","functionalLoc_LossEntry.pcs","pcsLossEntryfunLocationValues","frmPcsLossEntry","&machId="+machId);
	    else
		   loadFunctionalLocation("pcsLossEntryfunLocation","functionalLoc_LossEntry.pcs","pcsLossEntryfunLocationValues","frmPcsLossEntry",dataStr);

		jQuery('#divAncilliary').css('display','none');


//pending time
		var sectId = jQuery('#hdnPlrkSectionid').val();
		var entryDate = jQuery('#dteFromDate').datebox('getValue');
		//alert(sectId);				
		processAjaxCalls('getDetailTableName.pcs','?q=2&sectId='+sectId+"&entryDate="+entryDate,"detailTableSuccess");


		formatDateBox('dtePlrkDate','dd-MMM-yyyy');
		fillComboBox("frmPcsLossEntry","cmbPlrkMachineid","machineCombo.commonFilter" );		
 	    var factId = jQuery("#frmPcsLossEntry input[id='factory']").val();		  
 	    fillComboBox("frmPcsLossEntry","cmbPlrkShiftid","shift.commonFilter"+"?q=2&factId="+factId);

 	    fillComboBox("frmPcsLossEntry","cmbPlrksubgroupid","combo_product.pcs?q=2" );
		
		readOnlyFields('cmbPlrkShiftid');
		readOnlyFields('cmbPlrkMachineid');
		readOnlyFields('dtePlrkDate');
		readOnlyFields('txtTotalProduced');
		readOnlyFields('cmbPlrksubgroupid');		
		
	});

	jQuery("#cmbPlrkLossid").combobox({
		filter: function(q, row){
			var opts = jQuery(this).combobox('options');
						
			return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
		}
	});

	function detailTableSuccess(result)	{
		//alert(result.detailTableName);		
		jQuery('#hdnDetialTableName').val(result.detailTableName);
	}
	
	function pcs_MsrSelectFormatter(id, options, rowObject){
		var id = options.rowId;		
	  	return '<input id="chk_'+id +'" type="checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' class="easyui-button"  "/>';	  	
	}
		
	function pcsLossEntryGrid_loadComplete() {
		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsLossSuccess");
	}

	function getFieldsForPcsLossSuccess(result) {		
		if (result.value.indexOf("WO") != -1) {
			jQuery('#hdnWnoshow').val("true");
			jQuery('#lblWno').hide();
			jQuery('#spnWno').hide();	
			jQuery("#pcsLossEntryGrid").jqGrid('hideCol',"cmbPlrkWno");			
		}					
	}

	function loadFunctionalLocationHierarchy_successCallback() 	{
		  var factId = jQuery("#frmPcsLossEntry input[id='factory']").val();		  
		  fillComboBox("frmPcsLossEntry","cmbWomsMchmid","machineCombo.commonFetch");
	}
	
	function frmPcsLossEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{	
		reloadMachine("frmPcsLossEntry", "cmbWomsMchmid",keyIds.cellId,keyIds.sectId, keyIds.factId,keyIds.locnId,keyIds.compId,keyIds.machId);
		//alert(keyIds.machId);		
	}
	
	jQuery('#btnAncilLink').click(function() {
		disableField("frmPcsLossEntry","txtPlrkMinutes");
		disableField("frmPcsLossEntry","txtPlrkInstance");		
		jQuery('#txtPlrkMinutes').css('enabled','false');
		jQuery('#txtPlrkInstance').css('enabled','false');
		jQuery('#divLoss').hide();			
		jQuery('#divAncilliary').css('display','block');			
		fnFetch();		
	});
	
	jQuery('#btnFetch').click(function() {
		fnFetch();		
	});

	jQuery('#btnCalc').click(function() {
		//alert("hi");		
		jQuery('#divAncilliary').css('display','none');
		jQuery('#divLoss').show();
		var msrNo ="";

		var rowIds = jQuery("#pcsAncilliaryGrid").getDataIDs();		
		for(var i = 0; i<rowIds.length; i++) {
			var chkBox = jQuery("#pcsAncilliaryGrid input[id=chk_"+(i+1) +"]");			
			//alert(chkBox.is(':checked'));			
			if(chkBox.is(':checked') == true)  {
				//alert(jQuery("#pcsAncilliaryGrid").jqGrid('getCell',rowIds[i],2));
				if (msrNo=="" || msrNo==" ")					  
					msrNo = jQuery("#pcsAncilliaryGrid").jqGrid('getCell',rowIds[i],2);
				else
					msrNo += "," + jQuery("#pcsAncilliaryGrid").jqGrid('getCell',rowIds[i],2);				
			}			
		}
		//alert(msrNo);
		var dataStr = "?q=2&msrNos="+msrNo;
		processAjaxCalls('getMsrsDownTime.pcs',dataStr,"msrsDownTimeSuccess");		
	});

	
	function msrsDownTimeSuccess(result) {
		jQuery('#txtPlrkMinutes').val(result.downTime);
	}
	
	function fnFetch() {
		
		var COMPANYID = jQuery("#frmPcsLossEntry input[id='company']").val();
		var LOCATIONID = jQuery("#frmPcsLossEntry input[id='location']").val();
		var FACTORYID = jQuery("#frmPcsLossEntry input[id='factory']").val();		
		var SECTIONID = jQuery("#frmPcsLossEntry input[id='section']").val();
		var CELLID	 = jQuery("#frmPcsLossEntry input[id='cell']").val();
		var MACHINEID = jQuery("#frmPcsLossEntry input[id='machine']").val();

		var FROMDATE ="";
		var TODATE = "";
		var PLDETAILSID = jQuery('#txtPlrkPldetailid').val();

		if(jQuery('#chkEntry').is(':checked') == true)  {
			FROMDATE = jQuery('#dteFromDate').datebox('getValue');
			TODATE = jQuery('#dteTillDate').datebox('getValue');
		}		
		
		var condParam = "COMPANYID="+COMPANYID+";LOCATIONID="+LOCATIONID+";FACTORYID="+FACTORYID;
		condParam += ";SECTIONID="+SECTIONID+";CELLID="+CELLID+";MACHINEID="+MACHINEID;
		condParam += ";FROMDATE="+FROMDATE+";TODATE="+TODATE+"";
		condParam += ";PLDETAILSID="+PLDETAILSID+";";	

		var prodMchId = jQuery('#cmbPlrkMachineid').val();
		//alert("?q=2&condParam="+condParam+"&prodMchId="+prodMchId);
		processGridnew('pcsAncilliaryGrid_view.pcs',"?q=2&condParam="+condParam+"&prodMchId="+prodMchId,"pcsAncilliaryGrid","pcsAncilliaryPager","PCS Entry","pcsAncilliaryGrid_dblClick","","pcsAncilliaryGrid_loadComplete","pcsAncilliaryGridError");
	}

	function pcsAncilliaryGrid_loadComplete() {
		var rowIds = jQuery("#pcsAncilliaryGrid").getDataIDs();
		//alert(rowIds.length);
		for(var i = 0; i<rowIds.length; i++) {
			var tick = jQuery("#pcsAncilliaryGrid").jqGrid('getCell',rowIds[i],0);
		//	alert(tick);
		}			
	}
	
	function fillValueForQtyOrLoss()
	{		
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		if (isQtyLoss =="Y") {
			jQuery('#lblTimeOrQty').html("Loss Qty");			
			if (jQuery('#hdnWnoshow').val()=="") {
				jQuery('#lblWno').show();
				jQuery('#spnWno').show();
			}
			else {
				jQuery('#lblWno').hide();
				jQuery('#spnWno').hide();
			}
				
			
			jQuery('#divLblRej').show();
			jQuery('#divRejection').show();			
		}
		else {
			jQuery('#lblTimeOrQty').html("Loss Time");
			jQuery('#lblWno').hide();
			jQuery('#spnWno').hide();
			jQuery('#divLblRej').hide();
			jQuery('#divRejection').hide();
			jQuery('#cmbPlrkWno').combobox('clear');
			jQuery('#cmbPlrkCauseid').combobox('clear');
			jQuery('#cmbPlrkRootcauseid').combobox('clear');
		}		
	}
	
	jQuery('#btnLossClose').click(function() {
		closePopUpDialoge("loadLossPopUp");
	});
	
	jQuery('#btnLossClear').click(function() {				
		//alert("Clear");
		fnLossClear();
	});

	jQuery('#btnLossDelete').click(function() {

		var formId = jQuery('#submitForm').val(); 
		var rowId = jQuery("#pcsLossEntryGrid").jqGrid('getGridParam', 'selrow');
		if (rowId == null || rowId < 0) {	return; }		
		var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',rowId);

		if (rowData.cmbMsrno != "" && rowData.cmbMsrno != "{}" && rowData.cmbMsrno != " "  )
			return ;
		
		var lossId = rowData.cmbPlrkLossId;
		var msrNo =rowData.cmbMsrno;
		var lossVal = rowData.txtPlrkMinutes;
		var PlrkKeyid =rowData.txtPlrkKeyid;
		var Pldetailsid = jQuery('#txtPlrkPldetailid').val();
		var sectId = jQuery("#frmPcsLossEntry input[id='hdnPlrkSectionid']").val();

		if (!msrNo==null && !msrNo=="" && !msrNo==" ") {
			return ;}
		
		var url = "pcsLossEntry_delete.pcs" ;
		url+= '?q=2&PlrkKeyid='+PlrkKeyid+'&Pldetailsid='+Pldetailsid+'&sectId='+sectId+'&lossId='+lossId+'&lossVal='+lossVal;		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);
	});
	
	jQuery('#btnLossInsert').click(function() {		
		//alert(jQuery('#txaPlrkRemarks').val());
		
		var lossTime = jQuery('#txtPlrkMinutes').val();
		if (lossTime <= 0) { alert("Enter Produced Qty"); return ;}

		
		if (jQuery('#lblRemarks').html() == "Problem") { 
			if (jQuery('#txaPlrkRemarks').val() == "" || jQuery('#txaPlrkRemarks').val() ==" ") {
				alert("Enter Problem");
				return; 
			}
		}
		if (jQuery('#lblTimeOrQty').html() == "Loss Qty") {
			if (jQuery('#cmbPlrkCauseid').combobox('getValue')=="" || jQuery('#cmbPlrkCauseid').combobox('getValue')==" ") {
				alert("Select Cause");
				return; 
			}

			if (jQuery('#cmbPlrkRootcauseid').combobox('getValue')=="" || jQuery('#cmbPlrkRootcauseid').combobox('getValue')==" ") {
				alert("Select Root Cause");
				return; 
			}
			
			//alert(jQuery('#cmbPlrkWno').combobox('getText'));
			if (jQuery("#spnWno").is(":visible")) {
				if (jQuery('#cmbPlrkWno').combobox('getText')=="" || jQuery('#cmbPlrkWno').combobox('getText')==" ") {
					alert("Select Work Order No");
					return; 
				}
			}
		}
		
		if (jQuery('#lblTimeOrQty').html() != "Loss Qty") {	 
			var lossTime = jQuery('#txtPlrkMinutes').val();
			var pendTime = jQuery('#hdnPendingTime').val();
			var oldUsedTime = jQuery('#hdnOldUsedTime').val();

			if (oldUsedTime == null || oldUsedTime == "" || oldUsedTime == " ")
				oldUsedTime="0";
			if (pendTime == null || pendTime == "" || pendTime == " ")
				pendTime="0";
					
			var diffTime = (parseFloat(pendTime) + parseFloat(oldUsedTime)) - parseFloat(lossTime);
			//alert(diffTime);
			if (diffTime != null && diffTime < 0) {
				alert("Production Time can not be great than Available Time");
				return; 			
			}
		}
			
		var formId = jQuery('#submitForm').val();

		var url = "pcsLossEntry_save.pcs?q=2";
		
		if (jQuery('#hdnMode').val() == "Update")
			url+= '&saveMode=Update';
		else
			url+= '&saveMode=Save';
		//url += "&saveMode=New";

		var lossId = jQuery('#cmbPlrkLossid').combobox('getValue');
		var lossValue = jQuery('#txtPlrkMinutes').val();
		var Pldetailsid = jQuery('#txtPlrkPldetailid').val();
		var PlrkKeyid = jQuery('#hdnPlrkKeyid').val();
		
		url += "&PlrkKeyid="+PlrkKeyid+"&Pldetailsid="+Pldetailsid+"&lossId="+lossId+"&lossValue="+lossValue;
		
		//alert('formId'+formId+"-====url=="+url);		
		if(formId.length > 0 )
			saveForm(formId,url);		
	});

	
	function loadLossPopUp_onClose() {
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');		
		if  (lossId!="" && lossId != " ") {
			var msg ="Are you sure want to Close";
			if(confirm(msg) == false)				
				return false;
		}		
		if (jQuery("#loadPCSPopUp").is(":visible")) {
			jQuery('#submitForm').val('frmPcsEntry');
			getCalendarTime();
		}
		else {
			jQuery('#submitForm').val('frmPcsView');
			refreshForm();
		}
		return true;
	}

	function frmPcsLossEntry_successsCallback(result) {
		//alert("dskfj");
		fnLossClear();
		jQuery("#pcsLossEntryGrid").jqGrid().trigger("reloadGrid");
	}

	function fnLossClear() {		
		enableFields("cmbPlrkLossid");
		enableFields("cmbPlrkReasonid");
		jQuery('#cmbPlrkReasonid').combobox('clear');		
		jQuery('#cmbPlrkLossid').combobox('clear');
		jQuery('#txtPlrkMinutes').val('');
		jQuery('#txtPlrkInstance').val('');		
		//jQuery('#dtePlrkDate').datebox('clear');
		jQuery('#txaPlrkRemarks').val("");
		jQuery('#hdnMode').val("");
		jQuery('#hdnPlrkKeyid').val("");
		jQuery('#cmbPlrkWno').combobox('clear');
		jQuery('#cmbPlrkCauseid').combobox('clear');
		jQuery('#cmbPlrkRootcauseid').combobox('clear');
		jQuery('#btnAncilLink').css('display','none');
		jQuery('#txtPlrkMinutes').css('enabled','false');
		jQuery('#txtPlrkInstance').css('enabled','false');
		enableFields("txtPlrkMinutes");	
		enableFields("txtPlrkInstance");

		fnGetPendingTime();
		
		jQuery('#hdnOldUsedTime').val("0");
		jQuery('#hdnPendingTime').val("0");

	
		
	}

	function pcsLossEntryGridError()
	{		//alert("error");
	}
	

	function frmPcsLossEntrycmbPlrkLossid_onLoadSuccess() 	{
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		if (isQtyLoss =="Y")
			fillComboBox("frmPcsLossEntry","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
		else
			fillComboBox("frmPcsLossEntry","cmbPlrkReasonid","combo_Phenomena.pcs" );
	}	

	function pendingTimeSuccess(result) {
		//alert(result.pendingTime);
		jQuery('#hdnPendingTime').val(result.pendingTime);		
		var oldUsedTime = jQuery('#hdnOldUsedTime').val();
		var pendTime=jQuery('#hdnPendingTime').val();

		if (oldUsedTime == null || oldUsedTime == "" || oldUsedTime == " ")
			oldUsedTime="0";
		if (pendTime == null || pendTime == "" || pendTime == " ")
			pendTime="0";
		
		var totPend= (parseFloat(pendTime) + parseFloat(oldUsedTime));
		jQuery('#txtUnreportedTime1').val(totPend);
	}

	function frmPcsLossEntrycmbPlrkReasonid_onLoadSuccess() 	{
		var Pldetailsid = jQuery('#txtPlrkPldetailid').val();		
		var sectId = jQuery("#frmPcsLossEntry input[id='hdnPlrkSectionid']").val();
		var entryDate = jQuery('#dteFromDate').datebox('getValue');
		var dataStr = "q=2&Pldetailsid="+Pldetailsid+"&sectId="+sectId+"&entryDate="+entryDate;
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');
		if (lossId != "" && lossId != " ") 
			setComboDefaultValue("frmPcsLossEntry", "cmbPlrkReasonid");		

		fillComboBox("frmPcsLossEntry","cmbPlrkWno","combo_Wno.pcs?"+dataStr);
		
	}	

	function frmPcsLossEntrycmbPlrkWno_onLoadSuccess() 	{
		var dataStr = "?q=2&isQtyLoss=&parentId=" ;
		fillComboBox("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs"+dataStr);	
	}

	function frmPcsLossEntrycmbPlrkCauseid_onLoadSuccess() 	{
		fillComboBox("frmPcsLossEntry","cmbPlrkRootcauseid","combo_rootcause.pcs?q=2");
	}

	
	function frmPcsLossEntrycmbWomsMchmid_onLoadSuccess() 	{
		var machId = jQuery("#frmPcsLossEntry input[id='machine']").val();				
		setFieldValue('cmbWomsMchmid',machId);
	}	
	
	function frmPcsLossEntrycmbWomsMchmid_onSelect(record) 	{		
	  loadFunctionalLocation("pcsLossEntryfunLocation","functionalLoc_LossEntry.pcs","pcsLossEntryfunLocationValues","frmPcsLossEntry","&machId="+record.id);
	}	

	function frmPcsLossEntrycmbPlrksubgroupid_onLoadSuccess() 	{
		fnGetPendingTime();
	}
	function fillLossEntryGrid() {
		//var mchId = jQuery('#cmbPlrkMachineid').combobox('getValue');				
		var pldetailsid = jQuery('#txtPlrkPldetailid').val();
		processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid="+pldetailsid,"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
	}

	function pcsLossEntryGrid_dblClick(id)
	{		
		var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',id);
		//alert(rowData.cmbPlrkLossId);
		
		if (rowData.cmbMsrno != "-" && rowData.cmbMsrno != "" && rowData.cmbMsrno != "{}" && rowData.cmbMsrno != " "  )
			return ;
		if (rowData.cmbPlrkLossId != null )
			jQuery('#hdnMode').val("Update");
		else
			jQuery('#hdnMode').val("");

		jQuery('#hdnPlrkKeyid').val(rowData.txtPlrkKeyid);
		jQuery('#cmbPlrkLossid').combobox('setValue',rowData.cmbPlrkLossId);
		//frmPcsLossEntrycmbPlrkLossid_onSelect(rowData.cmbPlrkLossId);
		readOnlyFields("cmbPlrkLossid");
		readOnlyFields("cmbPlrkReasonid");		

		if (rowData.txtPlrkQty > 0) {
			jQuery('#txtPlrkMinutes').val(rowData.txtPlrkQty);
			jQuery('#hdnIsQtyLoss').val('Y');
		}		
		else {
			jQuery('#txtPlrkMinutes').val(rowData.txtPlrkMinutes);
			jQuery('#hdnIsQtyLoss').val('N');
		}
		jQuery('#txtPlrkInstance').val(rowData.txtPlrkInstance);

		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');
		if (isQtyLoss =="Y")
			reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
		else
			reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_Phenomena.pcs?q=2&lossId="+lossId);	
		
		var oldTime = jQuery('#txtPlrkMinutes').val() ;
				
		if (rowData.cmbPlrkProcessid  == null || rowData.cmbPlrkProcessid  == "" || rowData.cmbPlrkProcessid  == " ") 
			var parentId = rowData.cmbPlrkReasonid;
		else
			var parentId = rowData.cmbPlrkProcessid + "-" + rowData.cmbPlrkReasonid;
				
		var dataStr = "?q=2&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;
		reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs"+dataStr);		

		setTimeout(function() { jQuery('#hdnOldUsedTime').val(oldTime); },550);
		
	
		fillValueForQtyOrLoss();			
		
		jQuery('#txaPlrkRemarks').val(rowData.txaPlrkRemarks);		
		jQuery('#cmbPlrkReasonid').combobox('setValue',parentId);
			
		setFieldValue('cmbPlrkWno',rowData.cmbPlrkWno);		
		setTimeout(function() { jQuery('#cmbPlrkWno').combobox('setValue',rowData.cmbPlrkWno); },550);
		setTimeout(function() { jQuery('#cmbPlrkCauseid').combobox('setValue',rowData.cmbPlrkCauseid); },550);
		setTimeout(function() { setFieldValue('cmbPlrkRootcauseid',rowData.cmbPlrkRootcauseid); },550);
				
		//jQuery('#cmbPlrkRootcauseid').combobox('setValue',rowData.cmbPlrkRootcauseid);

		var lossName =jQuery('#cmbPlrkLossid').combobox('getText');
		if (lossName.substring(0,3)=="8.3") {			
			jQuery('#btnAncilLink').css('display','block');
			disableField("frmPcsLossEntry","txtPlrkMinutes");
		}

		setTimeout(function() { fnGetPendingTime(); },550);
	
		
		/*var pldetailsId = jQuery('#txtPlrkPldetailid').val();
		var detailTable = jQuery('#hdnDetialTableName').val();		
		var dataStr= "?q=2&detailTable="+detailTable+"&pldetailsId="+pldetailsId;
		processAjaxCalls('getPendingTime.pcs',dataStr,"pendingTimeSuccess");		
		*/
		
	}
	
	function  frmPcsLossEntrycmbPlrkLossid_onSelect(record) {

		jQuery('#cmbPlrkReasonid').combobox('clear');		
		jQuery('#hdnOldUsedTime').val("0");
		jQuery('#txtPlrkInstance').val("1");
		if (jQuery('#hdnMode').val()=="")
			checkAlreadyExist();
		processAjaxCalls('checkIsQtyLoss.pcs','?q=2&lossId='+record.id,"checkQtyOrLossSuccess");
//alert(record.text.substring(0,3));
		if (record.text.substring(0,3)=="8.3") {
			var msg ="Do you want to select MSR for Ancillary Equipment Failure?";
			if(confirm(msg) == false) {
				fnLossClear();				
				return;
			}
			
			disableField("frmPcsLossEntry","txtPlrkMinutes");
			jQuery('#divLoss').hide();	
					
			jQuery('#divAncilliary').css('display','block');			
			fnFetch();	
		}
		else
			jQuery('#txtPlrkMinutes').css('enabled','false');

		fnGetPendingTime();
	}

	function fnGetPendingTime() {
		var pldetailsId = jQuery('#txtPlrkPldetailid').val();
		var detailTable = jQuery('#hdnDetialTableName').val();
		//alert(detailTable);
		var dataStr= "?q=2&detailTable="+detailTable+"&pldetailsId="+pldetailsId;
		processAjaxCalls('getPendingTime.pcs',dataStr,"pendingTimeSuccess");		
	}


	function checkQtyOrLossSuccess(result) {
		if (result.isQtyLoss=="Y")
			jQuery('#hdnIsQtyLoss').val("Y");
		else
			jQuery('#hdnIsQtyLoss').val("N");
		
		fillValueForQtyOrLoss();
		
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');
		if (isQtyLoss =="Y")
			reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
		else							
			reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_Phenomena.pcs?q=2&lossId="+lossId);
		//check eqp loss 
		processAjaxCalls('checkEquipmentFailure.pcs','?q=2&lossId='+lossId,"chkEqpFailureSuccess");				
		//processAjaxCalls('checkQtyOrNumber.pcs','?q=2&lossId='+record.id,"chkQtyOrNumberSuccess");
	
		if (lossId != "" && lossId != " ") {						
			if (isQtyLoss =="Y") {			
				setTimeout(function() { setComboDefaultValue("frmPcsLossEntry", "cmbPlrkCauseid"); },550);								
				setTimeout(function() { setComboDefaultValue("frmPcsLossEntry", "cmbPlrkWno"); },1250);				
			}
		}
	}

	function chkEqpFailureSuccess(result) {
		//alert(result.isEF);
		if (result.isEF == "EF") 
			jQuery('#lblRemarks').html("Problem");
		else 
			jQuery('#lblRemarks').html("Remarks");
	}
	
	function  frmPcsLossEntrycmbPlrkReasonid_onSelect(record) {
		if (jQuery('#hdnMode').val()=="")
			checkAlreadyExist();		
		
		jQuery('#cmbPlrkCauseid').combobox('clear');
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		var parentId = record.id;		
		var dataStr = "?q=2&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;
		reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs"+dataStr);		
						
	}	
	
	function 	checkAlreadyExist() {		
		selectLossId = jQuery('#cmbPlrkLossid').combobox('getValue');
		selectProblemId = jQuery('#cmbPlrkReasonid').combobox('getValue');
		reccnt = jQuery("#pcsLossEntryGrid").getDataIDs().length;		
		var i;
		for (i=1; i <= reccnt; i++) {
			var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',i);			
			var LossId =rowData.cmbPlrkLossId;
			var ProblemId =rowData.cmbPlrkReasonid;
			//alert(selectLossId +"==" +LossId + "=" +selectProblemId + "=" + ProblemId);			
			if (selectLossId == LossId && selectProblemId == ProblemId) {
				pcsLossEntryGrid_dblClick(i);
			}
		}
	}

	  function frmPcsLossEntry_beforeSubmit()	  {
		  //alert("f");
		  var lossName =jQuery('#cmbPlrkLossid').combobox('getText');
		  //alert(lossName);
		  if (lossName.substring(0,3)=="8.3") { 
		  	var gridData = '&MsrValues='+getSelectdRows('pcsAncilliaryGrid','MsrSelect_ChkBox','MsrSelect_ChkBox');
		  	//alert(gridData);	      
			return gridData; 
		  }
	}	

		function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
			
			var allRows = jQuery("#pcsAncilliaryGrid").jqGrid('getRowData');			
			var jsonArrO='[';
			for( var i = 0; i < allRows.length;i++){
				var row = allRows[i];
				var value = row[ckeckForSelColName];
				var chkBox = jQuery("#pcsAncilliaryGrid input[id=chk_"+(i+1) +"]");			
				//alert(chkBox.is(':checked'));					
				if(chkBox.is(':checked') == true)  {
					jsonArrO += '{';		
					for(var colName in row) {						
						var cellValue = parseJqGridCellValue(row[colName]);
						//if(colName == 'txtmmlkmachineid')	{
							jsonArrO += '"'+colName +'":"' + cellValue+'"';
						//	}							
						jsonArrO +=  ",";												
					}					
					jsonArrO = jsonArrO.slice(0, -1);  
					jsonArrO +=  "},";
				} 				
			}					
			jsonArrO = jsonArrO.slice(0, -1) + "]";
			jsonArrO = (jsonArrO != ']'?jsonArrO:"");			
			return jsonArrO; 
		}  
		  
	
</script>

<div> <input type="hidden" id="hdnMode"></div>
<div> <input type="hidden" id="hdnPlrkKeyid"></div>

<div> <input type="hidden" id="hdnDetialTableName" name="hdnDetialTableName"></div>
<div> <input type="hidden" id="hdnPendingTime" name="hdnPendingTime"></div>

<div> <input type="hidden" id="hdnLossParentId" name="hdnLossParentId" value="${requestScope.lossParentId}"> </div>
<div> <input type="hidden" id="hdnIsQtyLoss" name="hdnIsQtyLoss" value="${requestScope.isQtyLoss}"> </div>
<div> <input type="hidden" id="hdnOldUsedTime" name="hdnOldUsedTime"></div>
<div> <input type="hidden" id="hdnWnoshow" name="hdnWnoshow"></div>

<form id="frmPcsLossEntry" >
<span id="divLoss" style=""> 
<table width="100%" height="95%">
<tr><td valign="top">
<div id="divMain" style="">

<!-- <div class="main-header" style="float: center;width: 98%">PCS Loss Entry</div>  -->
<div class="easyui-paddingbfpx" style="padding-right:10px; ">
	<div> 
		<input type="hidden" id="txtPlrkPldetailid" name="txtPlrkPldetailid" value="${requestScope.Pldetailsid}">		
		<input type="hidden" id="hdnPlrkFactoryid" name="cmbPlrkFactoryid" value="${requestScope.FactId}"></input>
		<input type="hidden" id="hdnPlrkSectionid" name="cmbPlrkSectionid" value="${requestScope.SectId}"  ></input>
		<input type="hidden" id="hdnPlrkCellid" name="cmbPlrkCellid" value="${requestScope.CellId}"  ></input>
		
	</div>	
	<table style="width:100%;">
	<tr><td style="width:70%;">
		<div style="margin-top:px;"><label class="mandatory-lbl">Loss</label>		
			<label style="padding-left: 220px;" class="mandatory-lbl">Phenomena</label>
			<label style="padding-left: 165px;" class="mandatory-lbl">Instance</label>		
			<label id="lblTimeOrQty" style="padding-left: 10px;" class="mandatory-lbl">Time/Qty</label>
			
		    <span style="padding-left: 30px;">
	    		<label style="font-family:sans-serif; ;font-size: 12px;font-weight: bold;">Unreported Time</label>
	    	</span>			
			
		</div>
		<div  style="margin-top:px;width: 100%" >
	<!--		<input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" class="easyui-combobox" value="${requestScope.lossId}" style="width: 235px;"/>-->
				<input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" class="easyui-combobox" value="" style="width: 230px;"/>
	    	<span id="spnAncilLink">
	   			<input type="button" id="btnAncilLink"  name="btnAncilLink" class="easyui-button" value="..." style="display: none;height: 20px;">
	   		</span>
	    	<span style="padding-left: 10px;">
				<input type="text" id="cmbPlrkReasonid" name="cmbPlrkReasonid" class="easyui-combobox" value="" style="width: 230px;"/>
		    </span>
		    <span style="padding-left: 10px;">
		    	<input type="text" class="easyui-text" id="txtPlrkInstance" name="txtPlrkInstance" style="width: 45px;text-align:right;"  value=""/>	    	
		    </span>	  	  	
		    <span style="padding-left: 10px;">
		    	<input type="text" class="easyui-text" id="txtPlrkMinutes" name="txtPlrkMinutes" style="width: 45px;text-align:right;"  value=""/>	    	
		    </span>

	    	<span style="padding-left: 50px">
	    		<input id="txtUnreportedTime1" name="txtUnreportedTime1" type="text" value="${requestScope.unreportTime}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:80px;height:20px;color:black;font-weight:bold;text-align:center; " />
	    	</span>	    	   
		    
		    
		</div>
		<div   style=" height : 10px;width:px;">
			<span style="float:left;" id="err_cmbPlrkLossid"  class="tpm-errormsg"> </span>
			<span style="float:left; padding-left:175px;" id="err_cmbPlrkReasonid"  class="tpm-errormsg"> </span>
			<span style="float:left; padding-left:180px;" id="err_txtPlrkMinutes"  class="tpm-errormsg"> </span>
		</div>			
		<div id="divLblRej" align="left" >
			<label id="lblCause" class="mandatory-lbl">Cause</label>
			<span style="padding-left: 210px;">
				<label id="lblCause" class="mandatory-lbl">Root Cause</label>
			</span>
			<label id="lblWno" style="padding-left: 180px;" class="mandatory-lbl" >Work Order No.</label>
		</div>
		
	   	<div id="divRejection" class="easyui-paddingbfpx" style="margin-top:px;vertical-align: center;" >				    
			<input type="text" id="cmbPlrkCauseid" name="cmbPlrkCauseid" class="easyui-combobox" value="" style="width: 230px;"/>
	    	<span style="padding-left: 10px;">
				<input type="text" id="cmbPlrkRootcauseid" name="cmbPlrkRootcauseid" class="easyui-combobox" value="" style="width: 230px;"/>
		    </span>
			<span id="spnWno" style="padding-left: 10px;">
		    	<input type="text" id="cmbPlrkWno" name="cmbPlrkWno" class="easyui-combobox" value="" style="width: 80px;"/>
		    </span>	    
	   	</div> 

	<div align="left" >		
		<label id="lblRemarks" class="mandatory-lbl">Remarks</label>		
	</div>
	<div class="easyui-paddingbfpx" style="margin-top:px;vertical-align: center;" >
	    <span style="padding-left: px;">
	    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" rows="2" style="height: 50px;width:350px;resize:none;" cols="" id="txaPlrkRemarks" name="txaPlrkRemarks"></textarea>
	    </span>	
		<span style="padding-left: 5px;vertical-align:8px;">	    
 			<input type="button" id="btnLossInsert" name="btnLossInsert" class="easyui-button" value="Insert" style="width: 50px;">
 		</span> 		
 		<span style="padding-left: 5px;vertical-align:8px;">
 			<input type="button" id="btnLossClear" name="btnLossClear" class="easyui-button" value="Clear" style="width: 50px;">
 		</span>
	  	<span style="padding-left:5px;vertical-align:8px;">
   			<input type="button" id="btnLossDelete" name="btnLossDelete" class="easyui-button" value="Delete" style="width: 50px;">
   		</span> 		
	  	<span style="padding-left:5px;vertical-align:8px;">
   			<input type="button" id="btnLossClose" name="btnLossClose" class="easyui-button" value="Close" style="width: 50px;">
   		</span>
	</div>
	   </td>
	   	<td style="width:30px;padding-left: 15px;">
	   		<div style="margin-top:px;">
	   			<label class="mandatory-lbl">Date</label>		
				<label style="padding-left: 80px;" class="mandatory-lbl">shift</label>				
			</div>

			<div  style="margin-top:px;width: 100%" >							
				<input style="width: 100px;" type="text" class="easyui-text" readonly="readonly" id="dtePlrkDate" name="dtePlrkDate" value="${requestScope.EntryDate}"  ></input>								
				<span style="padding-left: 10px;">									
					<input style=" width: 60px;" type="text" class="easyui-combobox"  id="cmbPlrkShiftid" name="cmbPlrkShiftid" value="${requestScope.Shift}"  ></input>
				</span>
			</div>			
			<div   style=" height : 10px;width:px;">
			</div>
			<div  style="margin-top:px;width: 100%" >							
				<label style="padding-left: 0px;" class="mandatory-lbl">Equipment</label>
			</div>
			<div  style="margin-top:px;width: 100%" >				
				<input type="text" id="cmbPlrkMachineid" name="cmbPlrkMachineid" class="easyui-combobox" value="${requestScope.MchId}" style="width: 230px;"/>				
			</div>			
			<div style="margin-top:px;">
	   			<label style="" class="mandatory-lbl">Product</label>		
				<label id="lblTimeOrQty" style="padding-left: 120px;" class="mandatory-lbl">Total Produced</label>
	   		</div>
	   		<div>
	   			<input style="width: 180px;" "type="text" class="easyui-combobox"  id="cmbPlrksubgroupid" name="cmbPlrksubgroupid" value="${requestScope.PrdId}" ></input>
	   			<span style="padding-left: 10px;">
	   				<input type="text" class="easyui-text" id="txtTotalProduced" name="txtTotalProduced" style="width: 40px;text-align:right;"  value="${requestScope.totalProduced}"/>
	   			</span>
	   		</div> 	
	   	</td></tr> 
   	</table> 

	<div style="" class="lossGriddDiv">			
			<table id="pcsLossEntryGrid" style="float: left: ;"></table>
	</div>
   	</div>
   	  </div>

   	</td>   
   	</tr></table>
  </span>
<div id="divAncilliary"  style="width:100%;display:none; float: left;vertical-align: top;max-height: 75%">
<div style="float:none;vertical-align: top; " >
<div class="main-header" >PCS Ancilliary</div>
	<div  class="easyui-paddingbfpx">
		<div  class="easyui-paddingbfpx" id="frmPcsLossEntryFuntKeyIds">
			<input type="hidden" id="factory" name="factory" value="${requestScope.factId}"  ></input>
			<input type="hidden" id="section" name="section" value="${requestScope.sectId}"  ></input>
			<input type="hidden" id="cell" name="cell" value="${requestScope.cellId}" ></input>
			<input type="hidden" id="machine" name="cmbWomsMchmid" value="${requestScope.mchId}"  ></input>
		</div>
		<div id="pcsLossEntryfunLocation" style="padding-left: px; width: 95%"  ></div>
	</div>		
	<div style="margin-top: 1px">		
		<label>Equipment</label>	
		<label style="padding-left: 240px;" >From Date</label>
		<label style="padding-left: 40px;" >Till Date</label>
	</div>	 
	 <div class="easyui-paddingbfpx" style="width:100%" >
    	<span style="padding-left: px;">
			<input type="text" id="cmbWomsMchmid" name="cmbWomsMchmid" class="easyui-combobox" value="${requestScope.cellId}" style="width: 250px;"/>						
	 	</span>
	 	<span style="padding-left: 5px;">
	 		<input id="chkDate" name="chkDate" type="checkbox"  value="Y"/>
	 	</span>
	 	<span style="padding-left: 10px;">
	    	<input id="dteFromDate" name="dteFromDate" class="easyui-text" readonly="readonly" required="true" style="width: 90px;" value="${requestScope.date}"/>&nbsp;
	    </span>	 
		<span style="padding-left: 10px%;">
	    	<input id="dteTillDate" name="dteTillDate" class="easyui-text" readonly="readonly" required="true" style="width: 90px;" value="${requestScope.date}"/>&nbsp;
	    </span>
	    <span style="padding-left: 10px;">
	    	<input type="button" id="btnFetch" name="btnFetch" class="easyui-button"  value="Fetch" style=""/>
	    </span>
	    <span style="padding-left: 10px;">
	    	<input type="button" id="btnCalc" name="btnCalc" class="easyui-button"  value="Apply" style=""/>
	    </span>	    
    </div>	

	<div style="" class="ancilliaryGridDiv">			
			<table id="pcsAncilliaryGrid" style="float: left: ;"></table>
	</div>
	
	<!--			
	<div style="padding-left:0px; float:left; ">
			<div style="float: left;padding-right: 0px;margin-top:5px;">
			<table id="pcsAncilliaryGrid" width="300px" style="float: center;"></table> </div>
		<div id="pcsAncilliaryPager" style="float: center;"></div>
	</div>			
</div>-->

</div>
</div>
</form>   
