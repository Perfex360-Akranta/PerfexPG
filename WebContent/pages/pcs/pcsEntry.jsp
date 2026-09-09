<script type="text/javascript">
	
	jQuery(document).ready(function(){
		//alert("fhfh");
		initialiseForm('frmPcsEntry');
		jQuery('#submitForm').val('frmPcsEntry');

		formatDateBox('dtePrlmEntrydate','dd-MMM-yyyy');
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var dataStr = "&factId="+factId;
		fillComboBox("frmPcsEntry","cmbProductmodel","combo_productModel.pcs?q=2"+dataStr );
		dataStr = "&factId="+factId+"&prdModelId=";
		fillComboBox("frmPcsEntry","cmbproductid","combo_product.pcs?q=2"+dataStr );
		var dataStr ="?q=2&date=&machineId=&cellId=&productId=";
		fillComboBox("frmPcsEntry","cmbTheoriticalcycletime","combo_Cycletime.pcs"+dataStr );
				
		fillComboBox("frmPcsEntry","cmbmachineid","machineCombo.commonFilter" );
		
		/* for functionalLocation*/
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var sectionId = jQuery("#frmPcsEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var machId = jQuery("#frmPcsEntry input[id='machine']").val();		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
		//alert(dataStr);
		loadFunctionalLocation("prlmfunLocation","functionalLoc_Entry.pcs","prlmfunLocationValues","frmPcsEntry",dataStr);

		//var dataStr = "?q=2&mchId=&date=&shift=";
		//processGridnew('pcsEntryGrid_view.pcs',dataStr,"pcsEntryGrid","pcsentryPager","PCS Entry","pcsentrydblClick","","pcsEntryGrid_loadComplete","pcsEntryGridError");
		
		checkMasterExists();
	});

	function frmPcsEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		//alert("func");
		setDetailTableName();
	}
		
	function checkMasterExists() {
		var dataStr ="";
		var entryDate=jQuery('#dtePrlmEntrydate').datebox('getValue');
		var shift=jQuery('#cmbPrlmShiftid').combobox('getValue');		
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		dataStr+= "?q=2&entryDate="+entryDate+"&shift="+shift+"&cellId="+cellId;
		processAjaxCalls("getMasterKeyid.pcs",dataStr,"checkMasterIdRecallSuccess","checkMasterIdRecallError");
	}

	function setDetailTableName() {
		var dataStr ="";
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		dataStr+= "?q=2&sectId="+sectId;
		//alert(dataStr);		
		processAjaxCalls("getDetailTableName.pcs",dataStr,"setDetailTableNameRecallSuccess","setDetailTableNameRecallError");
	}

	function setDetailTableNameRecallSuccess(result) {
		//alert(result.detailTableName);
		jQuery('#hdnTableName').val(result.detailTableName);	
	}
	
	function pcsEntryGrid_dblClick(id)
	{		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',id);				
		if (rowData.cmbPlrkLossId != null )
			jQuery('#hdnMode').val("Update");
		else
			jQuery('#hdnMode').val("");

		jQuery('#hdnPldetailsid').val(rowData.txtPldetailsid);
		jQuery('#cmbProductmodel').combobox('setValue',rowData.cmbProductmodel);			
		jQuery('#cmbproductid').combobox('setValue',rowData.cmbproductid);
		readOnlyFields("cmbProductmodel");
		readOnlyFields("cmbproductid");		
		jQuery('#txtActualcycletime').val(rowData.txtActualTime);
		jQuery('#txtProducedqty').val(rowData.txtProducedQty);
	}
	
	function checkMasterIdRecallSuccess(result)
	{
		//alert(result.Plmasterid);
		jQuery('#hdnPlmasterid').val(result.Plmasterid);	
	}

	jQuery('#btnDelete').click(function() {
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#pcsEntryGrid").jqGrid('getGridParam', 'selrow');
		if (rowId == null || rowId < 0) {	return; }		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',rowId);
		var pldetailsId =rowData.txtPldetailsid;		
		var tableName = jQuery("#hdnTableName").val();
				
		var url = "pcsEntry_delete.pcs" ;//defined in classic.jsp
		url+= '?q=2&pldetailsId='+pldetailsId+"&tableName="+tableName;		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);	
	});
	
	jQuery('#btnClear').click(function() {				
		//alert("Clear");
		//jQuery('#cmbmachineid').combobox('clear');
		enableFields("cmbProductmodel");
		enableFields("cmbproductid");
		jQuery('#cmbproductid').combobox('clear');
		jQuery('#cmbtheoriticalcycletime').combobox('clear');				
		jQuery('#cmbPlrkReasonid').combobox('clear');		
		jQuery('#cmbPlrkLossid').combobox('clear');
		jQuery('#cmbProductmodel').combobox('clear');
		jQuery('#cmbTheoriticalcycletime').combobox('clear');
		
		jQuery('#txtProductcode').val('');		
		jQuery('#txtPlrkMinutes').val('');
		jQuery('#txtProducedqty').val('');
		jQuery('#txtNoplaninmins').val('');
		jQuery('#txtActualcycletime').val('');
				
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var dataStr = "&factId="+factId;
		reloadCombo("frmPcsEntry","cmbProductmodel","combo_productModel.pcs?q=2"+dataStr );
		dataStr = "&factId="+factId+"&prdModelId=";
		reloadCombo("frmPcsEntry","cmbproductid","combo_product.pcs?q=2"+dataStr );
		//jQuery('#hdnPlmasterid').val('');
		//jQuery('#dtePrlmEntrydate').datebox('clear');		
	});

	jQuery('#btnInsert').click(function() {
		
		var formId = jQuery('#submitForm').val();		
		var url = "pcsentry_save.pcs?q=2";

		if (jQuery('#hdnPlmasterid').val()=="")
			url += "&saveMode=Save";
		else
			url += "&saveMode=Update";

		/*var lossId = jQuery('#cmbPlrkLossid').combobox('getValue');
		var lossValue = jQuery('#txtPlrkMinutes').val();
		url += "&lossId="+lossId+"&lossValue="+lossValue; */
		var Plmasterid = jQuery('#hdnPlmasterid').val();
		var Pldetailsid = jQuery('#hdnPldetailsid').val();		
		url += "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid;		

		if(formId.length > 0 )
			saveForm(formId,url);		
	});

	function frmPcsEntry_successsCallback(result) {
		
		jQuery('#hdnPldetailsid').val(''); //Added By Prasanth		
		jQuery('#cmbProductmodel').combobox('clear');
		jQuery('#cmbproductid').combobox('clear');
		jQuery('#cmbTheoriticalcycletime').combobox('clear');
		jQuery('#txtActualcycletime').val('');
		jQuery('#txtProducedqty').val('');
		jQuery("#pcsEntryGrid").jqGrid().trigger("reloadGrid");
		
	}
	
	function frmPcsEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('cmbmachineid',keyIds.machId);
		var factryid = keyIds.factId;
		reloadMachine("frmPcsEntry",'cmbmachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	}

	function pcsEntryGrid_loadComplete() {
		//alert("loadedd");
	}
	
	function pcsEntryGridError()
	{		//alert("error");
	}
	
	function frmPcsEntrycmbmachineid_onLoadSuccess() 	{
		fillcmbProductModel();	
		fillEntryGrid();						
	}
	
	/*function frmPcsEntrycmbmachineid_onSelect(record) 	{
			//alert(record.id);
			fillcmbProductModel();
			//fillEntryGrid(record.id);
	}*/
	
	function fillcmbProductModel() {		
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		reloadCombo("frmPcsEntry","cmbProductmodel","combo_productModel.pcs?q=2&factId="+factId );		
	}

	function fillEntryGrid() {		
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var mchId=jQuery('#cmbmachineid').combobox('getValue');
		var date=jQuery('#dtePrlmEntrydate').datebox('getValue');
		var shift=jQuery('#cmbPrlmShiftid').combobox('getValue');
		var dataStr = "?q=2&sectId="+sectId+"&mchId="+mchId+"&date="+date+"&shift="+shift;
		//alert('dataStr'+dataStr);
		processGridnew('pcsEntryGrid_view.pcs',dataStr,"pcsEntryGrid","pcsentryPager","PCS Entry","pcsEntryGrid_dblClick","","pcsEntryGrid_loadComplete","pcsEntryGridError");
	}
	
	function frmPcsEntrycmbProductmodel_onLoadSuccess() 	{		
		fillcmbProduct();		
	}
	
	function frmPcsEntrycmbProductmodel_onSelect(record)	{
		fillcmbProduct();
	}
	
	function fillcmbProduct() 	{
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var prdModelId = jQuery('#cmbProductmodel').combobox('getValue');
		//alert(factId + "-" + prdModelId);		
		reloadCombo("frmPcsEntry","cmbproductid","combo_product.pcs?q=2&factId="+factId+"&prdModelId="+prdModelId);
	}
		
	function frmPcsEntrycmbproductid_onLoadSuccess() 	{
		fillComboBox("frmPcsEntry","cmbPrlmShiftid","combo_shift.brdn" );
		readOnlyFields("cmbmachineid");
		readOnlyFields("dtePrlmEntrydate");
		readOnlyFields("cmbPrlmShiftid");
		setComboDefaultValue("frmPcsEntry","cmbproductid");		
		//jQuery('#cmbproductid').combobox('getData').length
		/*var data = jQuery('#cmbproductid').combobox('getData');
			if (data.length ==1) {	
				var first = data[0];		
				jQuery('#cmbproductid').combobox('setValue',first.id);
		}*/
	}
	
	function frmPcsEntrycmbPrlmShiftid_onLoadSuccess() 	{		
		fillCycleTime();		
		setDetailTableName();
	}	

	function frmPcsEntrycmbproductid_onSelect(record)	{						
		fillCycleTime();
		checkAlreadyExist();
	}

	function checkAlreadyExist() {		
		selectPrdId = jQuery('#cmbproductid').combobox('getValue');
		reccnt = jQuery("#pcsEntryGrid").getDataIDs().length;		
		var i;
		for (i=1; i <= reccnt; i++) {
			var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',i);			
			var PrdId =rowData.cmbproductid;
			if (selectPrdId == PrdId ) {
				pcsEntryGrid_dblClick(i);
				return ;
			}
		}
	}
	function fillCycleTime() {
		//jQuery('#cmbTheoriticalcycletime').combobox('clear');
		var dataStr ="?q=2";
		dataStr+= "&date="+jQuery('#dtePrlmEntrydate').datebox('getValue');		
		dataStr+= "&machineId="+jQuery('#cmbmachineid').combobox('getValue');
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		dataStr+= "&cellId="+cellId;		
		dataStr+= "&productId="+jQuery('#cmbproductid').combobox('getValue');						
		reloadCombo("frmPcsEntry","cmbTheoriticalcycletime","combo_Cycletime.pcs"+dataStr );
	}
	
	function frmPcsEntrycmbTheoriticalcycletime_onLoadSuccess()	{
		setComboDefaultValue("frmPcsEntry","cmbTheoriticalcycletime");
	}
		
	function frmPcsEntrycmbTheoriticalcycletime_onSelect(record)	{		
		jQuery('#txtActualcycletime').val(record.text);
		var actTime = jQuery('#txtCalendartime').val();
		var planQty= parseFloat(actTime) / parseFloat(record.text) ;		
		jQuery('#txtPlannedqty').val(parseInt(planQty)); 
	}

	function actionFormatter(cellvalue, options, rowObject) {	
		var rowId = options.rowId;
		var formatStr  = '<input id="btn_'+rowId+'"' ;
		formatStr  += ' type="button" class="easyui-button"  align="left" value="..." style="height: 22px;" ';
		formatStr  += ' onClick=showSubLoss("btn_'+rowId+'") >'; 
		return formatStr;
	}

	function showSubLoss(id){
		//alert(id);		
		var date=jQuery('#dtePrlmEntrydate').datebox('getValue');
		var shift=jQuery('#cmbPrlmShiftid').combobox('getValue');
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var mchId = jQuery('#cmbmachineid').combobox('getValue');
		//var prdId = jQuery('#cmbproductid').combobox('getValue');						
		//var rowId = jQuery("#pcsEntryGrid").jqGrid('getGridParam', 'selrow');
		var rowId = id.split("_");
		//alert(rowId[1]);
		if (rowId[1] == null || rowId[1] < 0) {	return; }		
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',rowId[1]);
		var Pldetailsid =rowData.txtPldetailsid;
		var prdId = rowData.cmbproductid;		
		var dataString = "?q=2&cellId="+cellId+"&mchId="+mchId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Pldetailsid="+Pldetailsid;
		//alert(dataString);
		//subFormPop("pcsLossEntry_input.pcs",90,100,450,1150,"PCS-Loss-Entry",dataString);		
		showLoss(dataString);
	}

	function showLoss(dataStr) {
		alert(dataStr);
		var display = jQuery("#divGraphContainer").css('display');
		//jQuery("#divGraphContainer").addClass("custom-popup");
		jQuery('body').append('<div  id="dialog-mask"></div>');
		jQuery('#dialog-mask').fadeIn(100);
		if(display == 'none' )
			LoadForm("divGraphContainer","","pcsLossEntry_input.pcs"+dataStr,"", "showLoss_successCallBack");
	}

	function showLoss_successCallBack() {
	}
	
</script>

<div> <input type="hidden" id="hdnTableName"></div>
<div> <input type="hidden" id="hdnPlmasterid"></div>
<div> <input type="hidden" id="hdnPldetailsid"></div>
<form id="frmPcsEntry" >
<div id="wrapper">
<div class="easyui-paddingbfpx" style="margin-top:px;" >
	<div class="easyui-paddingbfpx" style="margin-top:px;" >
		<div  id="frmPcsEntryFuntKeyIds"  >			
			<input type="hidden" id="factory" name="cmbPrlmFactoryid" value="${requestScope.FactoryId}"  ></input>
			<input type="hidden" id="section" name="cmbPrlmSectionid" value="${requestScope.SectionId}"  ></input>
			<input type="hidden" id="cell" name="cmbPrlmCellid" value="${requestScope.CellId}"  ></input>
			<input type="hidden" id="machine" name="cmbmachineidhdn" value="${requestScope.MchId}"  ></input>
		</div>			
		<div id="prlmfunLocation" style="padding-left: px;"></div>
	</div>		
	<div style="margin-top:px;"><label class="mandatory-lbl">Machine</label>
		<label style="padding-left: 260px;" class="mandatory-lbl">Date</label>
		<label style="padding-left: 90px;" class="mandatory-lbl">Shift</label>
		<label style="padding-left: 120px;" class="mandatory-lbl">Calendar Time</label>
		<label style="padding-left: 40px;" class="mandatory-lbl">Planned Qty </label>
		<label style="padding-left: 30px;" class="mandatory-lbl">No Plan / No Demand </label>		
	</div>
	
    <div class="easyui-paddingbfpx" style="margin-top:px;" >
		<input type="text" id="cmbmachineid" name="cmbmachineid" class="easyui-combobox" value="${requestScope.MchId}"  style="width: 265px;" />
		<input type="button" class="easyui-button"  value="..." style="height: 22px;"/>				
		<span style="padding-left: 15px;">
	    	<input id="dtePrlmEntrydate" name="dtePrlmEntrydate" class="easyui-text" readonly="readonly" style="width: 100px;" value="${requestScope.EntryDate}" />
	    </span>
    	<span style="padding-left: 15px;">
	    	<input type="text" class="easyui-combobox" id="cmbPrlmShiftid" name="cmbPrlmShiftid" readonly="readonly"  style="width: 125px;"  value="${requestScope.Shift}"/>
	    </span>	    
		<span style="padding-left: 30px;" >
			<input type="text" class="easyui-text" id="txtCalendartime" name="txtCalendartime" readonly="readonly"  style="width: 80px;"  value="480"/>
		</span>
		<span style="padding-left: 30px;" > 
			<input type="text" class="easyui-text" id="txtPlannedqty" name="txtPlannedqty" readonly="readonly"  style="width: 80px;"  value=""/>
		</span>
		<span style="padding-left: 30px;" > 
			<input type="text" class="easyui-text" id="txtNoplaninmins" name="txtNoplaninmins" style="width: 80px;"  value=""/>
		</span>
		<span id="err_cmbmachineid" class="tpm-errormsg"> </span>	           	
    </div>
</div>
<div class="easyui-paddingbfpx" style="margin-top:px;" >
	<div style="margin-top:px;"><label >Product Model</label>
		<label style="padding-left: 130px;" class="mandatory-lbl">Product Name</label>
		<label style="padding-left: 205px;" class="mandatory-lbl">Cycle Time</label>
		<label style="padding-left: 125px;" class="mandatory-lbl">Actual Time</label>
		<label style="padding-left: 50px;" class="mandatory-lbl"> Produced Qty</label>		
	</div>
	<div class="easyui-paddingbfpx" style="margin-top:px;" >    	
	    	<input type="text" class="easyui-combobox" id="cmbProductmodel" name="cmbProductmodel" readonly="readonly"  style="width: 185px;"  value=""/>
		<span style="padding-left: 15px;">
			<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 250px;"/>
			<input type="button" class="easyui-button"  value="..." style="height: 22px;"/>				
		</span>
		<span style="padding-left: 15px;">
			<input type="text" id="cmbTheoriticalcycletime" name="cmbTheoriticalcycletime" class="easyui-combobox" value="" style="width: 165px;"/>							
		</span>
		<span style="padding-left: 15px;">
			<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 105px;"/>
		</span>
		<span style="padding-left: 15px;">
	    	<input type="text" class="easyui-text" id="txtProducedqty" name="txtProducedqty" style="width: 100px;"  value=""/>
	    </span>
	    <span id="err_cmbproductid" class="tpm-errormsg"> </span>       	
	    <span id="err_txtProducedqty" class="tpm-errormsg"> </span>
    </div>
</div>

<!-- <div class="easyui-paddingbfpx" style="margin-top:px;" 
		<div style="margin-top:px;"><label class="mandatory-lbl">Loss</label>		
		<label style="padding-left: 260px;" class="mandatory-lbl">Phenomena</label>
		<label style="padding-left: 210px;" class="mandatory-lbl">Time/Qty</label>
		<label style="padding-left: 90px;" >Remarks</label>
	</div>
	<div class="easyui-paddingbfpx" style="margin-top:px;" >
		<input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" class="easyui-combobox" value="" style="width: 265px;"/>
	    
    	<span style="padding-left: 15px;">
			<input type="text" id="cmbPlrkReasonid" name="cmbPlrkReasonid" class="easyui-combobox" value="" style="width: 265px;"/>
	    </span>
	    <span style="padding-left: 15px;">
	    	<input type="text" class="easyui-text" id="txtPlrkMinutes" name="txtPlrkMinutes" style="width: 100px;"  value=""/>
	    </span>
	    <span style="padding-left: 15px;">
	    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txaPlrkRemarks" name="txaPlrkRemarks" style="width: 280px;height: 50px" rows="2" cols="2"></textarea>
		</span>
		<div  style=" height : 20px;width:885px;">
	    	<span id="err_cmbActualcycletime" class="tpm-errormsg"> </span>       	
		    <span style="padding-left:180px;float:left;"; id="err_cmbPlrkLossid" class="tpm-errormsg"> </span>
		    <span style="padding-left:460px;float:left;"; id="err_cmbPlrkReasonid" class="tpm-errormsg"> </span>		
		</div> 	    
    </div>
>-->
    
    <div style="padding-left:px;" align="center">
    	<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Insert" style="width: 80px;">
    	<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 80px;">
    	<input type="button" id="btnDelete" name="btnDelete" class="easyui-button" value="Delete" style="width: 80px;">
    </div>
</div>
	<div style="padding-left:20px;" align="left">
			<div style="float: center;padding-right: 40px;margin-top:5px;">
			<table id="pcsEntryGrid" width="400px" style="float: center;"></table> </div>
		<div id="pcsEntryPager" style="float: center;"></div>
	</div>
</form>

<div id="wrapperRpt">
	<div id="divGraphContainer" style="padding-left: px; height:500px"  ></div>
</div>   
