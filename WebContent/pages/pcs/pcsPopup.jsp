<script type="text/javascript">
	
	jQuery(document).ready(function(){
		
		initialiseForm('frmPcsEntry');
		jQuery('#submitForm').val('frmPcsEntry');
	//	formatDateBox('dtePrlmEntrydate','dd-MMM-yyyy');
		
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var dataStr="?q=2&pcsEnabled=Y&factId="+factId+"&cellId="+cellId;				  
		fillComboBox("frmPcsEntry","cmbMachineAll","machineCombo.commonFilter"+dataStr  );
		
		//var dataStr = "&factId="+factId;
		//fillComboBox("frmPcsEntry","cmbProductmodel","combo_productModel.pcs?q=2"+dataStr );
	//	dataStr = "&factId="+factId+"&prdModelId=";	
		var mchId = jQuery('#hdnmachineid').val();		
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;
				
		fillComboBox("frmPcsEntry","cmbproductmodel","combo_productModel.pcs?q=2"+cond );
			
		var mchId = jQuery('#hdnmachineid').val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var dataStr = "&factId="+factId+"&prdModelId=+&mchId="+mchId+"&entryDate="+entryDate;		
		fillComboBox("frmPcsEntry","cmbproductid","combo_product.pcs?q=2"+dataStr );
		var dataStr ="?q=2&date=&machineId=&cellId=&productId=";
		fillComboBox("frmPcsEntry","cmbCycletimeCavity","combo_Cycletime.pcs"+dataStr );
		fillComboBox("frmPcsEntry","cmbCavityavailable","combo_Cavity.pcs"+dataStr );

		fillComboBox("frmPcsEntry","cmbrawmaterialtype","combo_rawtype.pcs" );		
		//fillComboBox("frmPcsEntry","hdnmachineid","machineCombo.commonFilter" );
		
		var dataString ="";
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		dataString+= "?q=2&sectId="+sectId+"&entryDate="+entryDate;
		
		//processAjaxCalls("getDetailTableName.pcs",dataString,"setDetailTableNameRecallSuccess","setDetailTableNameRecallError");
		//fillComboBox("frmPcsEntry","hdnmachineid","machineCombo.commonFilter" );
		
		/* for functionalLocation*/
		fillFuncLocation();
				
		checkMasterExists();
		//getCalendarTime();
		fillEntryGrid();

		numericTextBox('txtActualcycletime');
		numericTextBox('txtnoplaninmins');
		numericTextBox('txtproducedqty');		
		numericTextBox('txtCavityavailable');
		numericTextBox('txtCavityused');
		numericTextBox('txtweight');

		numericTextBox('txttrimmingqty');
		numericTextBox('txtexpansionqty');
		
		readOnlyFields("txtCavityavailable");
		readOnlyFields("txtcalendartime");
		readOnlyFields("txtplannedqty");
		
		disableField("frmPcsEntry","cmbMachineAll");
		//jQuery( '#divMchAll').hide();
		

		var plmasterId =jQuery('#hdnPlmMasterId').val();
		//alert(plmasterId);
		if (plmasterId=="" || plmasterId==" " || plmasterId==undefined)
			 checkLastShiftProduct();		

		readOnlyFields("txttrimmingqty");
		readOnlyFields("txtexpansionqty");

		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");
		
		jQuery('#hdnMode').val("");

		jQuery('#imgPrevious').click(function() {		
			var data = jQuery('#cmbMachineAll').combobox('getData');
			var id = jQuery('#cmbMachineAll').combobox('getValue');		
			for(var i=0;i<data.length;i++) {
				if (data[i].id == id & parseInt(i) >= 0 ) {	
					var next = data[i-1];
					jQuery('#cmbMachineAll').combobox('setValue',next.id);				
					jQuery("#frmPcsEntry input[id='machine']").val(next.id);
					jQuery('#hdnmachineid').val(next.id);

					fnClear();	
					fillFuncLocation();
					fillcmbProduct();
					fillEntryGrid();		
				}		
			}
		});
		
		jQuery('#imgNetx').click(function() {
			var data = jQuery('#cmbMachineAll').combobox('getData');
			var id = jQuery('#cmbMachineAll').combobox('getValue');		
			for(var i=0;i<data.length;i++) {
				if (data[i].id == id & parseInt(i+1) < data.length ) {	
					var next = data[i+1];
					jQuery('#cmbMachineAll').combobox('setValue',next.id);
					jQuery("#frmPcsEntry input[id='machine']").val(next.id);
					jQuery('#hdnmachineid').val(next.id);

					fnClear();
					fillFuncLocation();
					fillcmbProduct();
					fillEntryGrid();		
				}		
			}
		});

		
	});

	function showUnreportedTime() {
		getCalendarTime();
	}
	

	function fillFuncLocation() {
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var sectionId = jQuery("#frmPcsEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var machId = jQuery("#frmPcsEntry input[id='machine']").val();		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
		//alert(dataStr);		
		loadFunctionalLocation("prlmfunLocation","functionalLoc_Entry.pcs","prlmfunLocationValues","frmPcsEntry",dataStr);
		
	}
	
	
	function getFieldsForPcsSuccess(result) {		
		//alert(result.value.indexOf("EQ"));
		if (result.value.indexOf("EQ") != -1) {			
			jQuery('#spnExpansionLabel').css('display','none');
			jQuery('#spnExpansionQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtexpansionqty");
			
		}
		if (result.value.indexOf("TQ") != -1) {			
			jQuery('#spnTrimmingLabel').css('display','none');
			jQuery('#spnTrimmingQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txttrimmingqty");
		}
		if (result.value.indexOf("WO") != -1) {
			jQuery('#spnLabelWno').css('display','none');
			jQuery('#spntxtWno').css('display','none');			
			jQuery('#spncyctime').css('padding-left','145px');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtwno");			
		}
		if (result.value.indexOf("NOPLAN") != -1) {
			jQuery( '#spnNoPlan').hide();
		}		
				

		/*jQuery('#spnModel').css('display','none');
		jQuery('#spnLabelWno').css('display','none');
		jQuery('#spnProductid').css('display','none');
		jQuery('#spntxtWno').css('display','none');
		*/
	}

	jQuery("#cmbproductid").combobox({
		filter: function(q, row){
			var opts = jQuery(this).combobox('options');			
			return row[opts.textField].indexOf(q) >= 0;			
		}
	});


	
	function checkLastShiftProduct() {					
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var mchId = jQuery('#hdnmachineid').val();
		var entryDate = jQuery('#hdnPrlmEntrydate').val();
		var shift = jQuery('#hdnPrlmShiftid').val();		
		var dataStr = "?q=2&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&entryDate="+entryDate+"&shift="+shift;
		//alert(dataStr);
		processAjaxCalls("getLastShiftProduct.pcs",dataStr,"prevProductRecallSuccess","prevProductRecallError");
	}

	function prevProductRecallSuccess(result) {				
		if (result.productId.length>1) {			
			jQuery('#cmbproductid').combobox('setValue',result.productId);			
			jQuery('#txtwno').val(result.wno);			
			//getCalendarTime();
			fillCycleTime();	
			setProductModel(result.productId);		
		}					
	}
	/*function frmPcsEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setDetailTableName();
	}*/
		
	function checkMasterExists() {
		var dataStr ="";
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();	
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		dataStr+= "?q=2&entryDate="+entryDate+"&shift="+shift+"&cellId="+cellId;
		
		processAjaxCalls("getMasterKeyid.pcs",dataStr,"checkMasterIdRecallSuccess","checkMasterIdRecallError");
	}

	function getCalendarTime() {
		var dataStr ="";
		var machineId = jQuery('#hdnmachineid').val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var plmasterId =jQuery('#hdnPlmMasterId').val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();		
		dataStr+= "?q=2&sectId="+sectId+"&machineId="+machineId+"&plmasterId="+plmasterId+"&entryDate="+entryDate;
		//alert(dataStr);
		processAjaxCalls("getCalendarTime.pcs",dataStr,"getCalenderTimeRecallSuccess","getCalendarTimeRecallError");
		
	}

	function getCalenderTimeRecallSuccess(result) {
		//alert('cal'+result.calendarTime );		
		if (result.calendarTime > 480) 
			jQuery('#txtcalendartime').val(480);
		else if (result.calendarTime<=0)
			jQuery('#txtcalendartime').val(0);
		else
			jQuery('#txtcalendartime').val(result.calendarTime);
		//alert(result.calendarTime);

		var unreportTime =jQuery('#txtcalendartime').val();
		jQuery('#txtUnreportedTime').val(unreportTime);
		
	}
	
/*	function setDetailTableName() {
		var dataStr ="";
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		dataStr+= "?q=2&sectId="+sectId+"&entryDate="+entryDate;		
		processAjaxCalls("getDetailTableName.pcs",dataStr,"setDetailTableNameRecallSuccess","setDetailTableNameRecallError");
	}
*/
	function setDetailTableNameRecallSuccess(result) {
		//alert(result.detailTableName);
		jQuery('#hdnTableName').val(result.detailTableName);			
		
	}
	
	function pcsEntryGrid_dblClick(id)
	{
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',id);
				
		if (rowData.txtPldetailsid != null )   
			jQuery('#hdnMode').val("Update");			
		else
			jQuery('#hdnMode').val("");
		
		jQuery('#hdnPldetailsid').val(rowData.txtPldetailsid);
		jQuery('#hdnPtwokeyid').val(rowData.txtPtwokeyid);
		
		//jQuery('#cmbProductmodel').combobox('setValue',rowData.cmbProductmodel);			
		jQuery('#cmbproductid').combobox('setValue',rowData.cmbproductid);
		var dataStr="?q=2&prdId="+rowData.cmbproductid;
		processAjaxCalls("selectedProductModel.pcs",dataStr,"modelIdSuccess","modelIdSuccessError");		
		
		//readOnlyFields("cmbProductmodel");
		//getCalendarTime();
		setTimeout(function() {fillCycleTime();},550);
	//	readOnlyFields("cmbproductid");	
	//	readOnlyFields("txtwno");// commented by prasanth

		
		jQuery('#cmbCycletimeCavity').combobox('setValue',rowData.cmbCycletimeCavity);
		jQuery('#cmbrawmaterialtype').combobox('setValue',rowData.cmbrawmaterialtype);
		
		jQuery('#txtwno').val(rowData.txtwno);
		jQuery('#txtproducedqty').val(rowData.txtproducedqty);
		jQuery('#txtplannedqty').val(rowData.txtplannedqty);
		jQuery('#txtnoplaninmins').val(rowData.txtnoplaninmins);
		//jQuery('#txtcalendartime').val(rowData.txtcalendartime);
		jQuery('#txtCavityavailable').val(rowData.txtCavityavailable);				

		jQuery('#txttrimmingqty').val(rowData.txttrimmingqty);
		jQuery('#txtexpansionqty').val(rowData.txtexpansionqty);
		
		//jQuery('#txtCavityused').val(rowData.txtCavityused);	
		setTimeout(function() {jQuery('#txtCavityused').val(rowData.txtCavityused);},950);	
		//alert(rowData.txaRemarks);
		jQuery('#txaRemarks').val(rowData.txaRemarks);		
		jQuery('#txtweight').val(rowData.txtweight);

		setTimeout(function() {jQuery('#txtActualcycletime').val(rowData.txtActualcycletime);},950);
		
		
		var oldTime ="0";
		if (parseFloat(rowData.txtCavityused)=="0")
			oldTime ="0";
		else	
			oldTime = (parseFloat(rowData.txtproducedqty) * parseFloat(rowData.txtActualcycletime))
								 / parseFloat(rowData.txtCavityused);

		
		jQuery('#hdnOldTime').val(oldTime);
			
	}
	function checkMasterIdRecallSuccess(result)
	{		
		jQuery('#hdnPlmMasterId').val(result.Plmasterid);	
	}

	/*jQuery('#btnDelete').click(function() {

		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#pcsEntryGrid").jqGrid('getGridParam', 'selrow');
		if (rowId == null || rowId < 0) {	return; }		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',rowId);
		var pldetailsId =rowData.txtPldetailsid;
		var Plmasterid = jQuery('#hdnPlmMasterId').val();
		var workOrderNo =rowData.txtwno;				
		//var tableName = jQuery("#hdnTableName").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var url = "pcsEntry_delete.pcs" ;//defined in classic.jsp
		url+= "?q=2&sectId="+sectId+"&pldetailsId="+pldetailsId+"&workOrderNo="+workOrderNo+"&Plmasterid="+Plmasterid;		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);	
			
	});*/
jQuery('#btnDelete').click(function() {
		
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId =  jQuery("#pcsEntryGrid").jqGrid('getGridParam', 'selrow');
		
		if (rowId == null || rowId < 0) {
			 var idS = jQuery("#pcsEntryGrid").jqGrid('getDataIDs');	
			 var delFlag = true;	
			 for(i=0;i<idS.length;i++)	
			 {
				if(jQuery('#cbox_'+idS[i]).is(':checked') == true)
				{
					delFlag = false;
					rowId = idS[i];
				}
			 }			 		 
			 if(delFlag)
				return;
		 }		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',rowId);
		var pldetailsId =rowData.txtPldetailsid;
		var Plmasterid = jQuery('#hdnPlmMasterId').val();
		var workOrderNo =rowData.txtwno;				
		//var tableName = jQuery("#hdnTableName").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var machId = jQuery('#hdnmachineid').val();		
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var url = "pcsEntry_delete.pcs" ;//defined in classic.jsp
		url+= "?q=2&sectId="+sectId+"&pldetailsId="+pldetailsId+"&workOrderNo="+workOrderNo+"&Plmasterid="+Plmasterid+"&machId="+machId+"&entryDate="+entryDate;		
		//alert(url);
		if(formId.length > 0 )
		saveForm(formId,url);	
			
	});
	
	jQuery('#btnClose').click(function() {
		//jQuery('#loadPCSPopUp').hide();
		closePopUpDialoge("loadPCSPopUp");
		
	});
	
	jQuery('#btnClear').click(function() {
		fnClear();	
	});
	
	function fnClear() {
		 
		enableFields("cmbProductmodel");
		enableFields("cmbproductid");
		enableFields("txtwno");
		jQuery('#txtwno').attr('readonly',false);

		jQuery('#cmbrawmaterialtype').combobox('clear');

		jQuery('#cmbproductmodel').combobox('clear');
		jQuery('#cmbproductid').combobox('clear');
		jQuery('#cmbCycletimeCavity').combobox('clear');				
		jQuery('#cmbProductmodel').combobox('clear');
		jQuery('#cmbCycletimeCavity').combobox('clear');
		jQuery('#cmbPopdPlemployeeid').combobox('clear');
		
		jQuery('#txtPlrkMinutes').val('');
		jQuery('#txtproducedqty').val('');
		jQuery('#txtplannedqty').val('');
		jQuery('#txtnoplaninmins').val('');
		jQuery('#txtActualcycletime').val('');

		jQuery('#txtweight').val('');
		jQuery('#txtwno').val('');
		jQuery('#txtCavityavailable').val('');
		jQuery('#txtCavityused').val('');

		jQuery('#txttrimmingqty').val('');
		jQuery('#txtexpansionqty').val('');

		jQuery('#hdnOldTime').val('0');
		jQuery('#hdnPldetailsid').val('');		
		
		jQuery('#txaRemarks').val('');				
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var mchId = jQuery('#hdnmachineid').val();

		readOnlyFields("txttrimmingqty");
		readOnlyFields("txtexpansionqty");
		jQuery('#chkTrimming').attr('checked',false);
		jQuery('#chkExpansion').attr('checked',false);
		
		var mchId = jQuery('#hdnmachineid').val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;

		reloadCombo("frmPcsEntry","cmbproductmodel","combo_productModel.pcs?q=2"+cond );				
		
		fillcmbProduct();
		jQuery('#hdnMode').val("");

		getCalendarTime();
		
		/*var dataStr = "&factId="+factId;
		dataStr = "&factId="+factId+"&prdModelId=+&mchId="+mchId;				
		reloadCombo("frmPcsEntry","","combo_product.pcs?q=2"+dataStr );
		*/
	}

	jQuery('#chkNoPlan').click(function() {
		//getCalendarTime();	
	});

	jQuery('#chkTrimming').click(function() {
		if(jQuery('#chkTrimming').is(':checked')==true ) 
			enableFields("txttrimmingqty");
		else
			readOnlyFields("txttrimmingqty");
						
	});

	jQuery('#chkExpansion').click(function() {
		if(jQuery('#chkExpansion').is(':checked')==true ) 
			enableFields("txtexpansionqty");
		else
			readOnlyFields("txtexpansionqty");				
		
	});

	//jQuery('#btnNoPlanSave').click(function() {
	function noPlanSave() { 
		if(jQuery('#chkNoPlan').is(':checked')==false) return;		

	/*	var availTime = jQuery('#txtcalendartime').val();
		//alert(availTime);
		if (availTime!=480) {
			alert("Product exists"); return;
		} 			
*/
		var mchId = jQuery('#hdnmachineid').val();
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();	

		var dataStr = '?q=2&date='+date+'&shift='+shift+'&mchId='+mchId;
		processAjaxCalls('checkIsOpenMsr.pcs',dataStr,"openMsrSuccForNoPlan");	
	}
	//});

	function openMsrSuccForNoPlan(result) {
		if (result.msrData.isOpenMsr == "1") 
			alert("Open MSRs are found for the shift. No Plan Cannot save");
		else {
			var mchId = jQuery('#hdnmachineid').val();
			var date=jQuery('#hdnPrlmEntrydate').val();
			var shift=jQuery('#hdnPrlmShiftid').val();
			var sectId = jQuery("#frmPcsEntry input[id='section']").val();	
			var Plmasterid = jQuery('#hdnPlmMasterId').val();
				
			var dataStr = '?q=2&mchId='+mchId+'&date='+date+'&shift='+shift+'&sectId='+sectId+'&Plmasterid='+Plmasterid;
			dataStr += "&saveMode=Save"+"&type=NOPLAN&from=pcs";;
			//alert(dataStr);
			processAjaxCalls('noPlan_save.pcs',dataStr,"noPlanSaveSuccess");		
		}
	}
	
	function noPlanSaveSuccess(result) {
		alert(result.successData.msg);
		if (result.status=="success")
			closePopUpDialoge("loadPCSPopUp");

	}
	
	jQuery('#btnInsert').click(function() {		 
		if (jQuery('#hdnMode').val() == "" && checkAlreadyExist()==true) {
			return true;
		}		
		
		
		if(jQuery('#chkNoPlan').is(':checked')==true) {
			noPlanSave();	
			 return true;
		}		
		
		var prodQty = jQuery('#txtproducedqty').val();
		if (prodQty < 0) { alert("Enter Produced Qty"); return ;}
		
		var mchId = jQuery('#hdnmachineid').val();
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();		
		processAjaxCalls('checkIsOpenMsr.pcs','?q=2&date='+date+'&shift='+shift+'&mchId='+mchId,"CheckMsrSuccess");		
	});

	function CheckMsrSuccess(result) {		

		var isOpenMst = "N";
		if (result.msrData.isOpenMsr == "1") {
			var msg ="Open MSRs are found for the shift. Would you like to continue PCS entry?";
			if(confirm(msg) == false)				
				return;
			else
				isOpenMst="Y";
		}

		if (jQuery("#spntxtWno").is(":visible")) {			
			var wno=jQuery("#txtwno").val();			
			if  (wno=="" || wno== " " || wno==null) {
				alert("Enter Work Order No");
				return;
			}
		}

			
		var formId = jQuery('#submitForm').val();		
		var url = "pcsentry_save.pcs?q=2";
		
		if (jQuery('#hdnPlmMasterId').val()=="")
			url += "&saveMode=Save";
		else
			url += "&saveMode=Update";

		var Plmasterid = jQuery('#hdnPlmMasterId').val();
		var Pldetailsid = jQuery('#hdnPldetailsid').val();		
		var Ptwokeyid = jQuery('#hdnPtwokeyid').val();
		
/*		var prodQty = jQuery('#txtproducedqty').val();  
		var cavityUsed = jQuery('#txtCavityused').val();
		var actTime = jQuery('#txtActualcycletime').val();			
		var availTime = jQuery('#txtcalendartime').val();
//alert(availTime);
		var prodTime = (prodQty / cavityUsed) * actTime;		
		//alert(prodTime);
		if ( parseInt(prodTime) > parseInt(availTime)) {
			alert("Production Time can not be greater than Available Time");	
			return;
		}
*/

		var oldTime = jQuery('#hdnOldTime').val();
		if (oldTime=="" || oldTime==" ") oldTime="0";
		
		url += "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid+"&Ptwokeyid="+Ptwokeyid+"&oldTime="+oldTime;
		url += "&isOpenMst="+isOpenMst;		

		if(formId.length > 0 )
			saveForm(formId,url);		
	 }

	function frmPcsEntry_deleteSuccessCallback(result) {

		if (result.successData.keyId.substring(0,3) =="EMP")
			jQuery("#pcsEmployeeGrid").jqGrid().trigger("reloadGrid");
		else		
			jQuery("#pcsEntryGrid").jqGrid().trigger("reloadGrid");

		if (result.status =="Success_withMaster")
			jQuery('#hdnPlmMasterId').val("");
		
		fnClear();		
	}

	
	function frmPcsEntry_successsCallback(result) {		
		//jQuery('#cmbProductmodel').combobox('clear');				
		
		if (jQuery('#hdnPlmMasterId').val()=="" || jQuery('#hdnPlmMasterId').val()==" ")
			jQuery('#hdnPlmMasterId').val(result.keyId);

		//alert(result.keyId.substring(0,3));
		if (result.keyId.substring(0,3) =="EMP")
			jQuery("#pcsEmployeeGrid").jqGrid().trigger("reloadGrid");
		else
			jQuery("#pcsEntryGrid").jqGrid().trigger("reloadGrid");

	
		if (result.status =="Success_withMaster")
			jQuery('#hdnPlmMasterId').val("");
		
		fnClear();
		
	}

	/*function frmPcsEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('hdnmachineid',keyIds.machId);
		var factryid = keyIds.factId;
		reloadMachine("frmPcsEntry",'hdnmachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);

	}*/
	/*function frmPcsEntryhdnmachineid_onLoadSuccess() 	{
		fillcmbProductModel();	
		fillEntryGrid();						
	}*/
	/*function fillcmbProductModel() {		
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		reloadCombo("frmPcsEntry","cmbProductmodel","combo_productModel.pcs?q=2&factId="+factId );		
	}*/

	function fillEntryGrid() {		
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var mchId=jQuery('#hdnmachineid').val();
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();
		var dataStr = "?q=2&sectId="+sectId+"&mchId="+mchId+"&date="+date+"&shift="+shift;
		//alert('dataStr'+dataStr);
		processGridnew('pcsEntryGrid_view.pcs',dataStr,"pcsEntryGrid","pcsentryPager","PCS Entry","pcsEntryGrid_dblClick","","pcsEntryGrid_loadComplete","pcsEntryGridError");
		
	}
	function pcsEntryGrid_loadComplete() {
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsid =rowData.txtPldetailsid;				
		processGridnew('pcsEmployeeGrid_view.pcs','?q=2&pldetailsid='+pldetailsid,"pcsEmployeeGrid","pcsEmployeePager","PCS Employee","pcsEmployeeGrid_dblClick","","pcsEmployeeGrid_loadComplete","pcsEmpGridError");

		noPlanOptionEnable();

	//	jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtwno");
	}

	function noPlanOptionEnable() {
		var reccnt = jQuery("#pcsEntryGrid").getDataIDs().length;		
		if (reccnt =="0") {		
			enableFields("chkNoPlan");
			jQuery('#btnNoPlanSave').attr('display','block');	
		}
		else {
			readOnlyFields("chkNoPlan");
			jQuery('#btnNoPlanSave').attr('disabled','none');	
		}		
	}

	function pcsEmployeeGrid_loadComplete()	{
	}
	
	function frmPcsEntrycmbproductmodel_onLoadSuccess() 	{
		setComboDefaultValue("frmPcsEntry","cmbproductmodel");
		//fillcmbProduct();		
	}
	
	function fillcmbProduct() 	{
		//alert(1);
		var mchId = jQuery('#hdnmachineid').val();
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var prdModelId = jQuery('#cmbproductmodel').combobox('getValue');
		var entryDate=jQuery('#hdnPrlmEntrydate').val();		
		var dataStr = "&factId="+factId+"&prdModelId="+prdModelId+"&mchId="+mchId+"&entryDate="+entryDate;
		//alert(dataStr);	
		jQuery('#cmbproductid').combobox('clear');	
		reloadCombo("frmPcsEntry","cmbproductid","combo_product.pcs?q=2"+dataStr);
	}

	function frmPcsEntrycmbproductmodel_onSelect(record)	{
		//alert(record.id);
		fillcmbProduct();
	}
	
	function frmPcsEntrycmbproductid_onLoadSuccess() 	{
		setComboDefaultValue("frmPcsEntry","cmbproductid");
		
		/*fillComboBox("frmPcsEntry","cmbPrlmShiftid","combo_shift.brdn" );
		readOnlyFields("hdnmachineid");
		readOnlyFields("dtePrlmEntrydate");
		readOnlyFields("cmbPrlmShiftid");*/
		//fillEntryGrid();
		//fillComboBox("frmPcsEntry","cmbCellid1","cellCombo.commonFilter" );
		fillComboBox("frmPcsEntry","cmbShiftid1","shift.commonFilter?q=2&frmRfilter=yes"  );	
		
	}
	
/*	function frmPcsEntrycmbCellid1_onLoadSuccess() 	{
		fillComboBox("frmPcsEntry","cmbShiftid1","shift.commonFilter" );
	}
*/	
	function frmPcsEntrycmbPrlmShiftid_onLoadSuccess() 	{		
		fillCycleTime();		
		//setDetailTableName();
	}	

	function frmPcsEntrycmbproductid_onSelect(record)	{		
		jQuery('#cmbCycletimeCavity').combobox('clear');		
		jQuery('#txtActualcycletime').val('');
		jQuery('#txtCavityavailable').val('');
		jQuery('#txtCavityused').val('');
		//getCalendarTime();
		//getPldetailsId();
		fillCycleTime();

		setProductModel(record.id);		
	}

	function setProductModel(id) {
		var dataStr="?q=2&prdId="+id;
		processAjaxCalls("selectedProductModel.pcs",dataStr,"modelIdSuccess","modelIdSuccessError");
	}


	function modelIdSuccess(result) {
		jQuery('#cmbproductmodel').combobox('setValue',result.modelKeyid);		
	}
	

/*	jQuery('#txtwno').focusout(function() {
		//alert("lsot");		
//		checkAlreadyExist();
		//getCalendarTime();		
	});
*/

	function getPldetailsId() {		
		var selectPrdId = jQuery('#cmbproductid').combobox('getValue');
		var reccnt = jQuery("#pcsEntryGrid").getDataIDs().length;		
		var i;
		for (i=1; i <= reccnt; i++) {
			var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',i);			
			var PrdId =rowData.cmbproductid;			
			if (selectPrdId == PrdId ) {
				jQuery('#hdnPldetailsid').val(rowData.txtPldetailsid);
				fillCycleTime();
				jQuery('#cmbCycletimeCavity').combobox('setValue',rowData.cmbCycletimeCavity);				
				jQuery('#cmbCycletimeCavity').combobox('setValue',rowData.txtTheoriticalcycletime);
				jQuery('#txtTheoriticalcycletime').val(rowData.txtTheoriticalcycletime);				
				jQuery('#txtActualcycletime').val(rowData.txtActualcycletime);
				jQuery('#txtCavityavailable').val(rowData.txtCavityavailable);				
				jQuery('#txtCavityused').val(rowData.txtCavityused);

				readOnlyFields("cmbCycletimeCavity");
				readOnlyFields("txtTheoriticalcycletime");
				readOnlyFields("txtActualcycletime");
				readOnlyFields("txtCavityavailable");
				readOnlyFields("txtCavityused");				
				return ;
			}
			else {
				enableFields("txtActualcycletime");				
				enableFields("txtCavityused");
			}				
		}
		jQuery('#hdnPldetailsid').val("");
		jQuery('#hdnPtwokeyid').val("");
		jQuery('#hdnMode').val("");
		jQuery('#txtTheoriticalcycletime').val("");
		jQuery('#txtActualcycletime').val("");
		jQuery('#txtCavityavailable').val("");
		jQuery('#txtCavityused').val("");	
		fillCycleTime();
	}
	
	
	function checkAlreadyExist() {		
		var selectPrdId = jQuery('#cmbproductid').combobox('getValue');
		var typeWno = jQuery('#txtwno').val().toUpperCase();
		var reccnt = jQuery("#pcsEntryGrid").getDataIDs().length;		
		var i;
		jQuery('#hdnPtwokeyid').val("");
		jQuery('#hdnMode').val("");
		
		for (i=1; i <= reccnt; i++) {
			var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',i);			
			var PrdId =rowData.cmbproductid;
			var Wno =rowData.txtwno.toUpperCase();
			
			if (typeWno == null || typeWno=="" || typeWno==" ") typeWno="0";
			if (Wno == null || Wno=="" || Wno==" ") Wno="0";
						
			if (selectPrdId == PrdId && typeWno == Wno) {
				//pcsEntryGrid_dblClick(i);
				alert("Product and Work Order No. Already Exists. To update Double click the Data row");
				var countCols = jQuery('#pcsEntryGrid').jqGrid('getGridParam', 'colModel').length;
				var colIds  = jQuery('#pcsEntryGrid').jqGrid('getGridParam', 'colModel');
						
				for (var j=0;j<= countCols-1;j++)  {
					jQuery("#pcsEntryGrid").jqGrid('setCell',i,colIds[j].name,"",{'background-color':'#ffe1f0'});					
				}				
				return true;
			}			
		}
		//jQuery('#hdnPldetailsid').val("");

		return false;
	}
	
	function fillCycleTime() {
		//jQuery('#cmbCycletimeCavity').combobox('clear');
		var dataStr ="?q=2";
		dataStr+= "&date="+jQuery('#hdnPrlmEntrydate').val();		
		dataStr+= "&machineId="+jQuery('#hdnmachineid').val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();	
		dataStr+= "&cellId="+cellId+"&sectId="+sectId;		
		dataStr+= "&productId="+jQuery('#cmbproductid').combobox('getValue');
		//alert(dataStr);						
		reloadCombo("frmPcsEntry","cmbCycletimeCavity","combo_Cycletime.pcs"+dataStr );
	}
	
	function frmPcsEntrycmbCycletimeCavity_onLoadSuccess()	{
		setComboDefaultValue("frmPcsEntry","cmbCycletimeCavity");
	
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var dataStr ='?q=2&sectId='+sectId; 
		fillComboBox("frmPcsEntry","cmbPopdPlemployeeid","combo_Operator.pcs"+dataStr );

		showUnreportedTime();
	}

		
	function frmPcsEntrycmbCycletimeCavity_onSelect(record)	{		
		jQuery('#txtActualcycletime').val(record.text);
		jQuery('#txtTheoriticalcycletime').val(record.text);

		var cavRaw =  record.id.split('##');
		//alert(cavRaw[1]);
		jQuery('#txtCavityavailable').val(cavRaw[0]);		
		jQuery('#txtCavityused').val(cavRaw[0]);

		jQuery('#cmbrawmaterialtype').combobox('setValue',cavRaw[1]);
		
		//var actTime = jQuery('#txtcalendartime').val();
		var actTime;
		//alert(jQuery('#hdnOldTime').val());
		//alert(jQuery('#txtcalendartime').val());
		
		if (jQuery('#hdnMode').val() == "Update")
			actTime = (parseFloat(jQuery('#txtcalendartime').val()) +  parseFloat(jQuery('#hdnOldTime').val()));
		else
			actTime = parseFloat(jQuery('#txtcalendartime').val()) ;

		setTimeout(function() {jQuery('#txtcalendartime').val(actTime);},550);
		
		var cycleTime = record.text;
		var cavityAvail = record.id;

		var planQty = (parseFloat(actTime) / parseFloat(cycleTime)) * parseFloat(cavityAvail) ;
		//Math.round(planQty*100)/100
		//jQuery('#txtplannedqty').val(Math.round(parseInt(planQty)));
		jQuery('#txtplannedqty').val(planQty);
		//alert(planQty);
		
	}

	function actionFormatter(cellvalue, options, rowObject) {	
		var rowId = options.rowId;
		var formatStr  = '<input id="btn_'+rowId+'"' ;
		formatStr  += ' type="button" class="easyui-button"  align="left" value="..." style="height: 22px;" ';
		formatStr  += ' onClick=showSubLoss("btn_'+rowId+'") >'; 
		return formatStr;
	}

	function showSubLoss(id){
		
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var mchId = jQuery('#hdnmachineid').val();
		
		var rowId = id.split("_");
		
		if (rowId[1] == null || rowId[1] < 0) {	return; }		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',rowId[1]);
		var Pldetailsid =rowData.txtPldetailsid;
		var prdId = rowData.cmbproductid.trim();		
		var totalProduced = rowData.txtproducedqty;
		var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Pldetailsid="+Pldetailsid+"&lossParentId=&lossId="+"&totalProduced="+totalProduced;
			//alert(dataString);	
		LoadPopUp("loadLossPopUp", "pcsLossEntry_input.pcs"+dataString,  true,"98%","95%","1px","1px",  "showLoss_successCallBack","PCS Loss Entry", true);
		
	}

	function showLoss_successCallBack() {
		//alert("1");
	}

	jQuery('#btnAddEmp').click(function() {
		var formId = jQuery('#submitForm').val(); 
		var Plmasterid = jQuery('#hdnPlmMasterId').val();		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var Pldetailsid =rowData.txtPldetailsid;
		//alert(Pldetailsid);
		if (Pldetailsid == null || Pldetailsid=="" || Pldetailsid==" ") {
			alert("Proudct entry must to Enter Operator Details");
			jQuery('#cmbPopdPlemployeeid').combobox('clear');
			return;
		}			
		var url = "pcsEmployee_save.pcs" ;
		url+= "?q=2&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid;
		url += "&saveMode=Save";		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);		
	});


	jQuery('#btnDelEmp').click(function() {
		var formId = jQuery('#submitForm').val();
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsId =rowData.txtPldetailsid;
		//alert(Pldetailsid);
		var rowId = jQuery("#pcsEmployeeGrid").jqGrid('getGridParam', 'selrow');
		if (rowId == null || rowId < 0) {	return; }		
		var rowData = jQuery("#pcsEmployeeGrid").jqGrid('getRowData',rowId);
		var empId = rowData.cmbPopdPlemployeeid;
		//alert(empId);
		if (empId=="" || empId==" ") {
			alert("Select the Employee to delete");
			return;
		}
		var url = "pcsEmployee_delete.pcs" ;
		url+= "?q=2&empId="+empId+"&pldetailsId="+pldetailsId;				
		//alert(url);
		if(formId.length > 0 )
			deleteRecord(formId,url);		
	});

	function loadPCSPopUp_onClose() {
		var prdId =jQuery('#cmbproductid').combobox('getValue');		
		if  (prdId!="" && prdId != " " && prdId!=null) {
			var msg ="Are you sure want to Close";
			if(confirm(msg)) { 				
				jQuery('#submitForm').val('frmPcsView');
				refreshForm();					
				return true;
			}
			else
				return false;
		}
		jQuery('#submitForm').val('frmPcsView');		
		refreshForm();
		return true;
	}

	jQuery('#spnTrimmingLabel').mouseover(function() {
		
	});

</script>

<form id="frmPcsEntry" >
<div class="easyui-paddingbfpx" style="padding-right: 10px;height: 100%; width:99%" >
	<div> <input type="hidden" id="hdnOldTime" name="hdnOldTime"></div>
	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
<!--	<div> <input type="hidden" id="hdnPlmMasterId"></div>-->
	<div> <input type="hidden" id="hdnPldetailsid"></div>	
	<div> <input type="hidden" id="hdnPtwokeyid"></div>
	<input type="hidden" id="factory" name="cmbPrlmFactoryid" value="${requestScope.FactoryId}"></input>
	<input type="hidden" id="hdnmachineid" name="hdnmachineid" value="${requestScope.MchId}"  ></input>
	<input type="hidden" id="hdnPrlmEntrydate" name="hdnPrlmEntrydate" value="${requestScope.EntryDate}"  ></input>	
	<input type="hidden" id="hdnPrlmShiftid" name="hdnPrlmShiftid" value="${requestScope.Shift}"  ></input>
	<input type="hidden" id="cell" name="cmbPrlmCellid" value="${requestScope.CellId}"  ></input>
	<input type="hidden" id="section" name="cmbPrlmSectionid" value="${requestScope.SectionId}"  ></input>
	
	
<!-- 	<div class="main-header">PCS Entry</div>  -->
	<table width="100%" height="90%" align="center">
	<tr>
	<td width="75%" align="left" style="border: 1px;border-style: solid;">	
	<div style="margin-left: 1%;margin-top: 1%">
	<div >
	<table style="width:100%">
	<tr>
	<td style="width:60%">
		<div  class="easyui-paddingbfpx">
				<div  class="easyui-paddingbfpx" id="frmPcsCalendarFuntKeyIds">
					<input type="hidden" id="factory" name="factory" value="${requestScope.FactoryId}"  ></input>
					<input type="hidden" id="section" name="section" value="${requestScope.SectionId}"  ></input>
					<input type="hidden" id="cell" name="cell" value="${requestScope.CellId}" ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.MchId}"  ></input>
				</div>
				<div id="prlmfunLocation" style="padding-left: px;width:120%;" ></div>
		</div>
	</td>
<!--		<label class="mandatory-lbl">Line</label>-->
<!--		<span style="padding-left: 5px">-->
<!--			<input type="text" id="cmbCellid1" name="cmbCellid1" disabled class="easyui-combobox" value="${requestScope.CellId}" style="width: 180px;"/>-->
<!--		</span>-->
<!--							-->
	<td style="width:40%">
		<div id="divMchAll" class="pcsMchAll" >				
			<span id="spnPrev" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">
				<img id="imgPrevious" name="imgPrevious" src="images/Back-.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />
			</span>
			<span style="vertical-align:top;margin-left: -4px;padding-bottom: 0px;margin-top: 3px;">
				<input type="text" id="cmbMachineAll" name="cmbMachineAll" class="easyui-combobox" value="${requestScope.MchId}" style="width: 170px;"/>
			</span>
			<span id="spnNext" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">
				<img id="imgNetx" name="imgNext" src="images/Forward.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />
			</span>			
		</div>
		<div style="padding-left: 50px;">
		<label style="padding-left: 2px;" class="mandatory-lbl"> Date </label>
		<span style="padding-left: 70px">
			<label style="padding-left: 2px;" class="mandatory-lbl">Shift</label>
		</span>
		</div>
		<div style="padding-left: 48px;">
			<input id="txtLabelDate" name="txtLabelDate" type="text" value="${requestScope.EntryDate}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;text-align:center; " />
			<span style="padding-left: 10px">
				<input type="text" class="easyui-combobox" id="cmbShiftid1" name="cmbShiftid1" readonly="readonly" disabled="disabled" style="width: 85px;"  value="${requestScope.Shift}"/>
			</span>
			<span style="padding-left: 2px;">
				<input type="hidden" class="easyui-text" id="txtcalendartime" name="txtcalendartime" readonly="readonly"  style="width: 50px;"  value=""/>										
			</span>
		</div>							
		
					
<!--		<label style="padding-left: 5px;" class="mandatory-lbl">Avail.Time </label>-->		
<!--		<label style="padding-left: 40px;" class="mandatory-lbl"> Plan.Qty</label>		-->
		<span style="padding-left: 5px;">
			<input type="hidden" class="easyui-text" id="txtplannedqty" name="txtplannedqty" readonly="readonly"  style="width: 50px;"  value=""/>			
		</span>		
				
		</td>
		</tr>
		</table>
		
	</div>
	
	<div id ="pcLabel" style="margin-top: 0%">
			<span id="spnModel" class="prdModel" > <label > Model </label></span>
			<span class="mandatory-lbl prodName">Product Name</span>
			<span id="spnLabelWno" title="Work Order No" style="" class="mandatory-lbl woNo" >W.O.No.</span>			
			<span id="spncyctime"  class="mandatory-lbl cyctime"> Cycle Time</span>
			<span style="" title="Actual Cycle Time" class="mandatory-lbl acttym">Act.Time</span>		
			
	    	<span class="pcsunrepTimeLBLSpn">
	    		<label class="pcsunrepTimeLBL">Unreported Time</label>
	    	</span>			
	</div>

	<div class="easyui-paddingbfpx" >    	
		<span id="spnProductid" style="">
			<input type="text" id="cmbproductmodel" name="cmbproductmodel" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 110px;"/>
		</span>
		<span class="pcsfieldspan">
	    	<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 200px;"/>
	    </span>
	    
		<span id="spntxtWno" class="pcsfieldspan">
	    	<input type="text" id="txtwno" name="txtwno" class="easyui-text" value="" style="width: 60px;"/>
	    </span>
										
	    <span class="pcsfieldspan">
	    	<input type="text" id="cmbCycletimeCavity" name="cmbCycletimeCavity" class="easyui-combobox" value="" style="width: 60px;text-align:right;"/>	    	
	    </span>
	    <input type="hidden" id="txtTheoriticalcycletime" name="txtTheoriticalcycletime" class="easyui-combobox" value="" style="width: 50px;"/>
	    <span class="pcsfieldspan">
	    	<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 50px;text-align:right;"/>
	    </span>

	    <span class="pcsunrepTimeSpan">
	    	<input id="txtUnreportedTime" name="txtUnreportedTime" type="text" value="${requestScope.unreportTime}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:70px;height:20px;color:black;font-weight:bold;text-align:center; " />
	    </span>	    	   

    </div>

	<div id ="pcLabel" style="margin-top: 0%">
			<span style="" title="Cavity Available" class="mandatory-lbl cavtAvl">Cavity Avail.</span>
		<span style="" class="mandatory-lbl cavUsd">Cavity Used</span>						
<!--			<label style="padding-left: 5px;" class="mandatory-lbl"> No Plan </label>-->
		<span id="lblPrdQty" title="Produced Quantity" style="word-spacing: " class="mandatory-lbl prdQty"> Prod.Qty </span>
<!--			(Moulding Qty)-->
		
		<span id="spnTrimExapn"  style="padding-left: 8px;display: 1">
				
			<span id="spnTrimmingLabel" title="Trimming Qty" style="padding-right: 5px;" class="trimmingQty">
				<input type="checkbox" id="chkTrimming" name="chkTrimming" value="Y" > 
				<span> Trim.Qty</span>
			</span>
			
			<span id="spnExpansionLabel" title="Expansion Qty" style="padding-left: px;" class="expansionQty">
				<input type="checkbox" id="chkExpansion" name="chkExpansion" value="Y" > 
				<span>Exp.Qty</span>
			</span>
		</span>
		
		<span style="padding-left: 12px;">	
			<label > Raw Material Type</label>
		</span>
		<span class="pcsWt">	
			<label > Weight</label>
		</span>
					
		
	</div>
	
	<div class="easyui-paddingbfpx" >
	    <span style="padding-left: 0px;">
	    	<input type="text" id="txtCavityavailable" name="txtCavityavailable" class="easyui-text" value="" style="width: 60px;text-align:right;"/>
	    </span>
	    <span style="padding-left: 5px;">
	    	<input type="text" id="txtCavityused" name="txtCavityused" class="easyui-text" value="" style="width: 60px;text-align:right;"/>
	    </span>
	    <span style="padding-left: 5px;">
	    	<input type="hidden" class="easyui-text" id="txtnoplaninmins" name="txtnoplaninmins" style="width: 50px;"  value=""/>
	    </span>
		<span style="padding-left: 1px;">
	    	<input type="text" class="easyui-text" id="txtproducedqty" name="txtproducedqty" style="width: 50px;text-align:right;"  value=""/>
	    </span>
	    
		<span id="spnExpansionQty"  style="padding-left: 5px;display: none1">
			<input type="text" class="easyui-text" id="txttrimmingqty" name="txttrimmingqty" style="width: 50px;text-align:right;"  value=""/>	
		</span>	    

		<span id="spnTrimmingQty"  style="padding-left: 15px;display: none1">						
			<input type="text" class="easyui-text" id="txtexpansionqty" name="txtexpansionqty" style="width: 50px;text-align:right;"  value=""/>			
		</span>	    	    		
		<span style="padding-left: 10px;">	
			<input type="text" id="cmbrawmaterialtype" name="cmbrawmaterialtype" class="easyui-combobox" style="width: 120px;"/>
		</span>
		<span style="padding-left: 10px;">
			<input type="text" class="easyui-text" id="txtweight" name="txtweight" style="width: 50px;text-align:right;"  value=""/>			
		</span>


		
	</div>    
	    
    <!--    
	<div  style=" height : 0px;width:700px;">
		<span style="float:left; padding-left:25px;" id="err_cmbproductid" class="tpm-errormsg"> </span>
		<span style="float:left; padding-left:255px;" id="err_txtActualcycletime" class="tpm-errormsg"> </span>
		<span style="float:left; padding-left:60px;" id="err_txtproducedqty" class="tpm-errormsg"> </span>
	</div>-->
	
    <div class="easyui-paddingbfpx" style="margin-top:5px;">			
			<div style="padding-left: 0px;vertical-align:15px">
				<label > Remarks</label>
			</div>
			<span style="padding-left: 0px;">	
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txaRemarks" name="txaRemarks" style="width: 250px;height: 50px" rows="3" cols="1"></textarea>
			</span>
			<span style="vertical-align: 30px">
			</span>
			<span style="vertical-align: 30px;padding-left: 10px">			
				<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Insert" style="width: 50px;">
	    		<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;">
	    		<input type="button" id="btnDelete" name="btnDelete" class="easyui-button" value="Delete" style="width: 50px;">
	    		<input type="button" id="btnClose" name="btnClose" class="easyui-button" value="Close" style="width: 50px;">	    		
	    	</span>
	    	
			<span id="spnNoPlan" style="padding-left: 20px;vertical-align: 30px;">	
				<input type="checkbox" id="chkNoPlan" name="chkNoPlan" value="Y" >
				<label style="font-family:sans-serif; ;font-size: 14px;font-weight: bold;">No Plan</label>
			</span>
	    	
	    	</div>			
	</div>

	<div style="" class="pcsEntryDiv">			
			<table id="pcsEntryGrid" style="float: left: ;"></table>
	</div>	
<!--		<div id="pcsEntryPager"></div>-->
	</td>
			
	<td width ="%%" style="vertical-align:top;border: 1px;border-style: solid;margin-left: 5px" >
		<div align="center" >
			<label class="mandatory-lbl"> Operator Details</label>
		</div>			
		<div style="margin-top: 1%;">
			<label style="padding-left: 1px;" class="mandatory-lbl"> </label>
		</div>						
		<label style="padding-left: 4px;" class="mandatory-lbl"> Operator</label>		
		<div class="easyui-paddingbfpx" style="padding-left: 5px;">			
		   <input type="text" id="cmbPopdPlemployeeid" name="cmbPopdPlemployeeid" class="easyui-combobox" value="" style="width: 220px;"/>		    
		</div>
		<div align="left" style="margin-top:5px;">
		<div align="center" >			
   			<input type="button" id="btnAddEmp" name="btnAddEmp" class="easyui-button" value="Add" style="width: 50px;">
   			<input type="button" id="btnDelEmp" name="btnDelEmp" class="easyui-button" value="Delete" style="width: 50px;">
	   </div>
	   </div>
	<div align="left" style="" class="pcsEmployeeDiv">			
			<table id="pcsEmployeeGrid" style="float: left: ;"></table>
	</div>		   
	</td>
	</tr>
	</table>
</div>
</form>
