<script type="text/javascript">	
	jQuery(document).ready(function(){		
		initialiseForm('frmProdPlanEntry');
		jQuery('#submitForm').val('frmProdPlanEntry');
		jQuery('#frmProdPlanEntry .easyui-text').css('text-transform', 'uppercase');
		formatDateBox('dtePrplPlandate','dd-MMM-yyyy');
		fillComboBox("frmProdPlanEntry","cmbPrplMachineid","machineCombo.commonFilter" );
		fillComboBox("frmProdPlanEntry","cmbPrplProductid","combo_product.pcs?q=2");
		fillComboBox("frmProdPlanEntry","cmbPrplPlannedby","employee.commonFilter"); 
		readOnlyFields('cmbPrplPlannedby');
	    var factId = jQuery("#frmProdPlanEntry input[id='factory']").val();
	    var sectionId = jQuery("#frmProdPlanEntry input[id='section']").val();
	    var cellId = jQuery("#frmProdPlanEntry input[id='cell']").val();
	    var machId = jQuery("#frmProdPlanEntry input[id='machine']").val();
	    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
	    loadFunctionalLocation("prplfunLocation","functionalLoc.plnconfig","prplfunLocationValues","frmProdPlanEntry",dataStr);
	    processGridnew('planEntry_view.plnconfig',"?q=2","prodPlanGrid","prodPlanPager","","","","prodPlanLoad","prodPlanError");
	});	
	jQuery('#btnView').click(function(){
		var machineId = jQuery('#cmbPrplMachineid').combobox('getValue');
		var productId = jQuery('#cmbPrplProductid').combobox('getValue');
		var planDate = jQuery('#dtePrplPlandate').datebox('getValue');
		var ds = '?q=2';
		if(machineId != null && machineId != '' && machineId != ' ')
			ds += '&machineId='+machineId;
		else
		{
			alert('Select Equipment');
			return false;
		}
		if(productId != null && productId != '' && productId != ' ')
			ds += '&productId='+productId;
		if(planDate != null && planDate != '' && planDate != ' ')
		{
			ds += '&planDate='+planDate;
			disableField("frmProdPlanEntry", "dtePrplPlandate");
		}
		else
		{
			alert('Select Plan Date');
			return false;
		}
		disableField("frmProdPlanEntry", "cmbPrplMachineid");
		disableField("frmProdPlanEntry", "cmbPrplProductid");		
		disableField("frmProdPlanEntry", "cmbPrplPlannedby");
		processGridnew('planEntry_view.plnconfig',ds,"prodPlanGrid","prodPlanPager","","","","prodPlanLoad","prodPlanError");
	});
	jQuery('#btnClear').click(function(){
		enableFields("cmbPrplMachineid");
		enableFields("cmbPrplProductid");
		enableFields("dtePrplPlandate");
	});
	function  frmProdPlanEntrycmbPrplMachineid_onSelect(record)
    {	
		loadFunctionalLocation("prplfunLocation","functionalLoc.plnconfig","prplfunLocationValues","frmProdPlanEntry","&machId="+record.id);
    }
	function dtePrplPlandate_onSelect(date)
    {	    
		fillProduct();
    }
	function frmProdPlanEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('cmbPrplMachineid',keyIds.machId);						
		reloadMachine("frmProdPlanEntry",'cmbPrplMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		fillProduct();
	}
	function fillProduct(){		
		var mchId = jQuery('#cmbPrplMachineid').combobox('getValue');
		var factId = jQuery("#frmProdPlanEntry input[id='factory']").val();		
		var entryDate = jQuery('#dtePrplPlandate').datebox('getValue');		
		var dataStr = "&factId="+factId+"&mchId="+mchId;
		if(entryDate != null && entryDate != '' && entryDate != ' ')	
			dataStr += "&entryDate="+entryDate;
		jQuery('#cmbPrplProductid').combobox('clear');	
		reloadCombo("frmProdPlanEntry","cmbPrplProductid","combo_product.pcs?q=2"+dataStr);
		
	}
	function prodPlanLoad()
	{
		var rowIds = jQuery("#prodPlanGrid").getDataIDs();		
		for(var id = 1; id<=rowIds.length; id++) {		
			var rowData = jQuery("#prodPlanGrid").jqGrid('getRowData',id);
			jQuery("#prodPlanGrid").jqGrid('setCell',id,"txtPrplPlanqty","","",{'title':''});
			jQuery("#prodPlanGrid").jqGrid('setCell',id,"txtPrplRevisionno","","",{'title':''});
		}
	}
	function frmProdPlanEntry_beforeSubmit()
	{
		
		return 'prodPlanGrid='+convertProdPlanGridToJSONArr('prodPlanGrid');
	}
	function frmProdPlanEntry_successsCallback(result)
	{
		jQuery("#prodPlanGrid").trigger("reloadGrid");
	}
	function PlanQtyFormatter(id, options, rowObject){
		var id = options.rowId;		
		return '<input  onfocus="gotFocuse(this.id)" maxlength="8" value="'+rowObject[3]+'" id="txtPrplPlanqty_'+id +'" name="txtPrplPlanqty_'+id +'" style="border:0px;" type="text" class="easyui-text"  "/>';
	}
	function RevisionNoFormatter(id, options, rowObject){
		var id = options.rowId;		
		return '<input  onfocus="gotFocuse(this.id)" maxlength="20" value="'+rowObject[4]+'" id="txtPrplRevisionno_'+id +'" name="txtPrplRevisionno_'+id +'" style="border:0px;width:400px;" type="text" class="easyui-text"  "/>';
		
	}
	function gotFocuse(id) {
		if(id.indexOf('Revisionno')>0)
			jQuery('#'+id).css('text-transform', 'uppercase');
		else	
			numericTextBox(id);
	}
	function convertProdPlanGridToJSONArr(jqGridId){
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
		var jsonArrO='[';	
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			var flag = true;
			var revNo = '';
			var planQty = '';	
			if(row['txtPrplRevisionno'].substring(0,6)!='<input' )
			{
				revNo = row['txtPrplRevisionno'];
			}
			else
			{
				var revId=row['txtPrplRevisionno'].indexOf("id=")+4;
				var revVal=row['txtPrplRevisionno'].substring(revId);
				var revFlag = revVal.indexOf('"');		
				revNo = jQuery('#'+revVal.substring(0,revFlag)).val();
			}
			if(row['txtPrplPlanqty'].substring(0,6)!='<input' )
			{
				planQty = row['txtPrplPlanqty'];
			}
			else
			{
				var planId=row['txtPrplPlanqty'].indexOf("id=")+4;
				var planVal=row['txtPrplPlanqty'].substring(planId);
				var planFlag = planVal.indexOf('"');		
				planQty = jQuery('#'+planVal.substring(0,planFlag)).val();
			}
		
			if((revNo == '' || revNo == ' ') && (planQty == '' || planQty == ' '))
				flag = false; 
			//if(planQty == '' || planQty == ' ')
				//flag = false;
			//if(planQty == '0')
				//flag = false;
			if(flag)
			{
				jsonArrO += '{';
				for(var colName in row) {
					if(row[colName].substring(0,6)!='<input' && row[colName].substring(0,7)!='<select' && row[colName].substring(0,9)!='<textarea')
					{
						jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
					}			
					else
					{
						var x=row[colName].indexOf("id=")+4;
						var y=row[colName].substring(x);
						var z = y.indexOf('"');		
						var val = jQuery('#'+y.substring(0,z)).val();
						if(val == 'undefined')	
							val = '';		
						jsonArrO += '"'+colName +'":"' + val +'",';							
					}	
				}
				jsonArrO = jsonArrO.slice(0, -1) + "},"; 
			}
		}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
		return jsonArrO; 
	}
</script>
<form id="frmProdPlanEntry" >
<input type="hidden" id="mode">

		<div  class="easyui-paddingbfpx" style="padding-left:125px;">
				<div  class="easyui-paddingbfpx" id="frmPcsCalendarFuntKeyIds">
					<input type="hidden" id="factory" name="cmbPrplFactoryid" value="${requestScope.FactoryId}"  ></input>
					<input type="hidden" id="section" name="cmbPrplSectionid" value="${requestScope.SectionId}"  ></input>
					<input type="hidden" id="cell" name="cmbPrplCellid" value="${requestScope.CellId}" ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.MchId}"  ></input>
				</div>
				<div id="prplfunLocation" style="padding-left: px;width:108%;" ></div>
		</div>

		<div style="padding-left:125px;">
			<span> <label class="mandatory-lbl">Equipment </label></span>
			<span class="planEntryLblProduct"><label >Product </label></span>
			<span class="planEntryLblPlandate"> <label class="mandatory-lbl">Plan Date </label></span>
			<span class="planEntryLblPlannedby"><label>Planned By </label></span>
		</div>
		<div class="easyui-paddingbfpx" style="padding-left:125px;">    	
			<span style="padding-left:1px;">
				<input  id="cmbPrplMachineid" name="cmbPrplMachineid" class="easyui-combobox"  style="width: 230px;"/>
			</span>
			<span style="padding-left:1px;">
		    	<input  id="cmbPrplProductid" name="cmbPrplProductid" class="easyui-combobox" style="width: 230px;"/>
		    </span>
		    <span  style="padding-left:1px;">
				<input id="dtePrplPlandate" name="dtePrplPlandate" class="easyui-datebox"  style="width:85px;"/>
			</span>
			<span style="padding-left:1px;">
		    	<input id="cmbPrplPlannedby" name="cmbPrplPlannedby" class="easyui-combobox"  style="width: 230px;" value="${requestScope.pcsTlProductionplan.prplPlannedby}"/>
		    </span>
		    <span style="padding-left:1px;">
		    	<input type="button" id="btnView" name="btnView" class="easyui-button" value="View" style="width: 50px;height:20px;">
		    </span>
		    <span style="padding-left:1px;">
		    	<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">
		    </span>
		</div>	
		<div class="easyui-paddingbfpx" style="padding-left:125px;">    
			<span id="err_cmbPrplMachineid" class="tpm-errormsg"></span>
			<span id="err_dtePrplPlandate" class="tpm-errormsg" style="padding-left:468px;"></span>	
		</div>		
		<div  style="padding-left:125px;float:left;" class="prodPlanDiv">			
				<table id="prodPlanGrid" style="float: left;"></table>
				<div id="prodPlanPager"></div>
		</div>	

</form>   
