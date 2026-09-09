<script type="text/javascript">
		jQuery(document).ready(function(){		
		/*var pcsZVal = jQuery('#loadPCSPopUp').css('z-index');				
		if(pcsZVal != null && pcsZVal != '' && pcsZVal != ' ')
			jQuery('.layout-panel-south').css('z-index',parseInt(pcsZVal)+1);
		else
			jQuery('.layout-panel-south').css('z-index',1012);
		*/
		initialiseForm('frmPcsEntry');
		jQuery('#submitForm').val('frmPcsEntry');		
		fillComboBox("frmPcsLossEntry","cmbPlrkLossid","combo_Loss.pcs?q=2&isQtyLoss="+jQuery('#hdnIsQtyLoss').val());
		fillComboBox("frmPcsLossEntry","cmbPlrkReasonid","combo_Phenomena.pcs?q=2");		
		fillComboBox("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs");
		fillComboBox("frmPcsLossEntry","cmbPlrkRootcauseid","combo_rootcause.pcs?q=2");
		disableLossFields();
		fillValueForQtyOrLoss();
		jQuery('#frmPcsLossEntry textarea').css('text-transform', 'uppercase');
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

		jQuery("#cmbPlrkLossid").combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});		
	
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
		
		processAjaxCalls("getDetailTableName.pcs",dataString,"setDetailTableNameRecallSuccess","setDetailTableNameRecallError");
		
		//fillComboBox("frmPcsEntry","hdnmachineid","machineCombo.commonFilter" );		
		/* for functionalLocation*/
		fillFuncLocation();				
		checkMasterExists();
		//getCalendarTime();
		fillEntryGrid();		
		setTimeout(function() {fillLossEntryGrid('');},4000);   
		
		numericTextBox('txtActualcycletime');
		numericTextBox('txtnoplaninmins');
		numericTextBox('txtproducedqty');		
		numericTextBox('txtCavityavailable');
		numericTextBox('txtCavityused');
		numericTextBox('txtweight');
		
		numericTextBox('txtRejQty');
		numericTextBox('txttrimmingqty');
		numericTextBox('txtexpansionqty');
		disableField("frmPcsEntry","txtCavityavailable");
		disableField("frmPcsEntry","txtcalendartime");
		disableField("frmPcsEntry","txtplannedqty");
		/*readOnlyFields("txtCavityavailable");
		readOnlyFields("txtcalendartime");
		readOnlyFields("txtplannedqty");*/
		
		disableField("frmPcsEntry","cmbMachineAll");
		//jQuery( '#divMchAll').hide();
		var plmasterId =jQuery('#hdnPlmMasterId').val();
		//var pldetailsId =jQuery('#hdnPldetailsid').val();		
		var rowIds = jQuery("#pcsEntryGrid").getDataIDs();
		if(rowIds.length<=0)
			setTimeout(function() {checkLastShiftProduct();},1250);
		//if(pldetailsId=="" || pldetailsId==" " || pldetailsId==undefined)			  
		//else
			//alert('Last Shift');
	
		//disableField("frmPcsEntry","txttrimmingqty");
		//disableField("frmPcsEntry","txtexpansionqty");
		//readOnlyFields("txttrimmingqty");
		//readOnlyFields("txtexpansionqty");
		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");		
		setFocusOnField('txtproducedqty');
		setTimeout(function() {getRejectionQty();},5000);
		jQuery('#hdnMode').val("");
		jQuery('#btnOperatordtls').click(function() {
			LoadPopUp("loadOpertorDetails", "pcsOperatordtls_input.pcs?q=2", true,"350px","300px","100px","200px", "showResult_successCallBack","Operator Details",true);
		});
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
					fnLossClear();	
					jQuery('#hdnPlrkPldetailid').val('');
					setTimeout(function() {fillLossEntryGrid('');},4000);   
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
					fnLossClear();	
					jQuery('#hdnPlrkPldetailid').val('');
					setTimeout(function() {fillLossEntryGrid('');},4000);   
				}		
			}
		});

		
		jQuery('#cmbPlrkRootcauseid').combobox({		
			keyHandler: {
				enter: function(){
					InsertSubLossForProduct();
					enableFields('txaPlrkRemarks');
					enableUIButton('btnLossInsert');
					enableUIButton('btnLossClear');
					enableUIButton('btnLossDelete');
				},
				down: function(){
					var downHandler = jQuery.fn.combobox.defaults.keyHandler.down;
					downHandler.call(this);
				},
				up: function(){
					var upHandler = jQuery.fn.combobox.defaults.keyHandler.up;
					upHandler.call(this);
				}
			}			
		});
		jQuery("#txtproducedqty").keypress(function(e){ 		
			if(e.keyCode == 113)
			{
				InsertLossEntry('');
			}
			else if(e.keyCode == 13)
			{
				InsertLossEntry('N');
			}
			/*else if(e.keyCode == 9)
			{
				var tq = jQuery("#txttrimmingqty").attr("readonly");
				alert(tq);
				//jQuery("#chkTrimming").focus();	
				//if(tq == "readonly")
				//	setFocusOnField('chkTrimming');	
					
			}*/
		});
		jQuery("#txttrimmingqty").keypress(function(e){ 		
			if(e.keyCode == 113)
			{
				InsertLossEntry('');
			}
			else if(e.keyCode == 13)
			{
				InsertLossEntry('N');
			}
		});
		jQuery("#txtexpansionqty").keypress(function(e){ 		
			if(e.keyCode == 113)
			{
				InsertLossEntry('');
			}
			else if(e.keyCode == 13)
			{
				InsertLossEntry('N');
			}
		});
		jQuery('#btnInsert').click(function() {		 
			InsertLossEntry('');
		});
		jQuery('#btnEnterLoss').click(function() {		 
			InsertLossEntry('N');
		});

		jQuery("#txtPlrkInstance").change(function(){ 
			enableFields('txtPlrkMinutes');
			//setFocusOnField('txtPlrkMinutes');
		});
		jQuery("#txtPlrkMinutes").keypress(function(e){ 
			var causeFlag = jQuery('#causeLossPCS').css('display');		
			enableUIButton('btnLossInsert');
			enableUIButton('btnLossClear');
			enableUIButton('btnLossDelete');
			if(causeFlag == 'none')
			{
				if(e.keyCode == 9 || e.keyCode == 13)
				{
					InsertSubLossForProduct();
					enableFields('cmbPlrkRootcauseid');
					enableFields('txaPlrkRemarks');
					enableUIButton('btnLossInsert');
					enableUIButton('btnLossClear');
					enableUIButton('btnLossDelete');
				}
			}
		});
		jQuery("#txtPlrkMinutes").change(function(){ 
			enableFields('cmbPlrkRootcauseid');
			var causeFlag = jQuery('#causeLossPCS').css('display');		
			if(causeFlag == 'none')
			{
				enableFields('txaPlrkRemarks');
				enableUIButton('btnLossInsert');
				enableUIButton('btnLossClear');
				enableUIButton('btnLossDelete');	
			}
			else
			{
				enableFields('cmbPlrkCauseid');
				setFocusOnField('cmbPlrkCauseid');	
			}
		});

		jQuery('#btnLossInsert').click(function() {
			InsertSubLossForProduct();
		});
		jQuery('#btnLossClear').click(function() {
			fnLossClear();
			enableFields('cmbPlrkLossid');
			enableFields('cmbPlrkReasonid');
			enableFields('txtPlrkInstance');
			enableFields('txtPlrkMinutes');
			enableFields('cmbPlrkCauseid');
			enableFields('cmbPlrkRootcauseid');
			enableFields('txaPlrkRemarks');
			enableUIButton('btnLossInsert');
			enableUIButton('btnLossClear');
			enableUIButton('btnLossDelete');			
		});
		
		jQuery('#btnLossDelete').click(function() {

			var formId = 'frmPcsLossEntry'; 
			var rowId = jQuery("#pcsLossEntryGrid").jqGrid('getGridParam', 'selrow');
			if (rowId == null || rowId < 0) {	return; }		
			var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',rowId);
			if (rowData.cmbMsrno != "" && rowData.cmbMsrno != "{}" && rowData.cmbMsrno != " "  )
				return ;		
			var lossId = rowData.cmbPlrkLossId;
			var msrNo =rowData.cmbMsrno;
			var lossVal = rowData.txtPlrkMinutes;
			var PlrkKeyid =rowData.txtPlrkKeyid;
			var Pldetailsid = jQuery('#hdnPlrkPldetailid').val();
			var sectId = jQuery("#frmPcsEntry input[id='section']").val();
			if (!msrNo==null && !msrNo=="" && !msrNo==" ") {
				return ;}
			
			var url = "pcsLossEntry_delete.pcs" ;
			url+= '?q=2&PlrkKeyid='+PlrkKeyid+'&Pldetailsid='+Pldetailsid+'&sectId='+sectId+'&lossId='+lossId+'&lossVal='+lossVal;		
			
			if(formId.length > 0 )
				saveForm(formId,url);
		});

		jQuery("#cmbproductid").combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');			
				return row[opts.textField].indexOf(q) >= 0;			
			}
		});

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
		

		jQuery('#chkNoPlan').click(function() {
			//getCalendarTime();	
		});

		jQuery('#chkTrimming').click(function() {
			if(jQuery('#chkTrimming').is(':checked')==true ) 
				enableFields("txttrimmingqty");
			else
				disableField("frmPcsEntry","txttrimmingqty");
				//readOnlyFields("txttrimmingqty");
		});

		jQuery('#chkExpansion').click(function() {
			if(jQuery('#chkExpansion').is(':checked')==true ) 
				enableFields("txtexpansionqty");
			else
				disableField("frmPcsEntry","txtexpansionqty");
				//readOnlyFields("txtexpansionqty");				
		});
		
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

			
		
		readOnlyFields('cmbPlrkLossid');
});

	function getRejectionQty()
	{
		
		var flag = jQuery('#spnRejQty').css('display');	
	
		if(flag.indexOf('none') < 0)
		{
			var lossEntryGrid = jQuery("#pcsLossEntryGrid").jqGrid('getDataIDs');
			var rejQty = jQuery("#txtRejQty").val();
			var q = 0;		
			if(lossEntryGrid.length >0)
			{
				for(i=0;i<lossEntryGrid.length;i++)	
		 		{
			 		var lossName = jQuery("#pcsLossEntryGrid").getCell(lossEntryGrid[i], 'txtLossName').trim();
			 		if(lossName.indexOf('Reject')>0)
				 	{
			 			var qty = jQuery("#pcsLossEntryGrid").getCell(lossEntryGrid[i], 'txtPlrkQty').trim();			    			    		
			    		if(qty != null && qty != '' && qty != ' ')
				    		q = parseInt(q) + parseInt(qty);
				 	}		
			  	}	 	
				
				jQuery("#txtRejQty").val(q);
			}
		}
		
	}
/* To disable Loss Entry field On Load */
	function disableLossFields()
	{
		   numericTextBox('txtPlrkMinutes');
		   numericTextBox('txtPlrkInstance');
		   readOnlyFields('cmbPlrkLossid');				
		   readOnlyFields('cmbPlrkReasonid');
		   disableField('frmPcsLossEntry', 'txtPlrkInstance');
		   disableField('frmPcsLossEntry', 'txtPlrkMinutes');
		   //readOnlyFields('txtPlrkInstance'); 
		  // readOnlyFields('txtPlrkMinutes');
		   readOnlyFields('cmbPlrkCauseid');
		   readOnlyFields('cmbPlrkRootcauseid');
		   disableField('frmPcsLossEntry', 'txaPlrkRemarks');
		  // readOnlyFields('txaPlrkRemarks');	
		   disableUIButton('btnLossInsert');
		   disableUIButton('btnLossClear');
		   disableUIButton('btnLossDelete');		  	
	}
	function fnGetPendingTime() {		
		var pldetailsId = jQuery('#hdnPlrkPldetailid').val();		
		var detailTable = jQuery('#hdnTableName').val();	
		var dataStr= "?q=2&detailTable="+detailTable+"&pldetailsId="+pldetailsId;	
		
		processAjaxCalls('getPendingTime.pcs',dataStr,"pendingTimeSuccess");		
	}
	function pendingTimeSuccess(result) {
		jQuery('#hdnPendingTime').val(result.pendingTime);		
		var oldUsedTime = jQuery('#hdnOldUsedTime').val();
		var pendTime=jQuery('#hdnPendingTime').val();

		if (oldUsedTime == null || oldUsedTime == "" || oldUsedTime == " ")
			oldUsedTime="0";
		if (pendTime == null || pendTime == "" || pendTime == " ")
			pendTime="0";
		
		var totalPend= (parseFloat(pendTime) + parseFloat(oldUsedTime));
	
		if(parseFloat(totalPend)<0)
			jQuery('#txtUnreportedTime').val('0');
		else
			jQuery('#txtUnreportedTime').val(totalPend);
		
	}
	
	function  frmPcsLossEntrycmbPlrkLossid_onSelect(record) {
		enableFields('cmbPlrkReasonid');
		jQuery('#cmbPlrkReasonid').combobox('clear');		
		jQuery('#hdnOldUsedTime').val("0");
		jQuery('#txtPlrkInstance').val("1");
		//setFocusOnField('cmbPlrkReasonid');	
		/*if (jQuery('#hdnMode').val()=="")
			checkAlreadyExist();*/		
		processAjaxCalls('checkIsQtyLoss.pcs','?q=2&lossId='+record.id,"checkQtyOrLossSuccess");
		
		if (record.text.substring(0,3)=="8.3") {
			/*var msg ="Do you want to select MSR for Ancillary Equipment Failure?";
			if(confirm(msg) == false) {
				fnLossClear();				
				return;
			}*/			
			disableField("frmPcsLossEntry","txtPlrkMinutes");
			//jQuery('#divLoss').css('display','none');			
			//jQuery('#divAncilliary').css('display','block');			
			//fnFetch();	
		}
		else
			jQuery('#txtPlrkMinutes').css('enabled','false');
		setTimeout(function() {fnGetPendingTime();},550);
	}
	function frmPcsLossEntrycmbPlrkReasonid_onSelect(record) {
		enableFields('txtPlrkInstance');
		//setFocusOnField('txtPlrkInstance');
		var instance = jQuery('#txtPlrkInstance').val();
		if(instance != null && instance != '' && instance != ' ')
			enableFields('txtPlrkMinutes');			
		jQuery('#cmbPlrkCauseid').combobox('clear');
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		var parentId = record.id;		
		var dataStr = "?q=2&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;
		var lossName = jQuery("#cmbPlrkLossid").combobox('getText');		
		if(lossName.substring(0,3)=="7.1")
			reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs"+dataStr);
		else
			reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_pcsLossCause.pcs"+dataStr);	
					
	}
	function frmPcsLossEntrycmbPlrkReasonid_onLoadSuccess() 	{		
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');
		if (lossId != "" && lossId != " ") 
			setComboDefaultValue("frmPcsLossEntry", "cmbPlrkReasonid");		
	}	
	function  frmPcsLossEntrycmbPlrkCauseid_onSelect(record) {
		enableFields('cmbPlrkRootcauseid');

		var lossName = jQuery("#cmbPlrkLossid").combobox('getText');		
		//if(lossName.substring(0,3)=="7.1")
		{
			enableUIButton('btnLossInsert');
			enableUIButton('btnLossClear');
			enableUIButton('btnLossDelete');
		}
		//setFocusOnField('cmbPlrkRootcauseid');	
	}
	/*function  cmbPlrkRootcauseid_onEnterKeyPress(){
		InsertSubLossForProduct();
		enableFields('txaPlrkRemarks');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');
	}*/
	function  frmPcsLossEntrycmbPlrkRootcauseid_onSelect(record) {
		enableFields('txaPlrkRemarks');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');	
	}
	function InsertSubLossForProduct()
	{
		var lossTime = jQuery('#txtPlrkMinutes').val();
		var wno = jQuery('#txtwno').val();
		var lossId = jQuery('#cmbPlrkLossid').combobox('getText');
		var mode = jQuery('#hdnLossMode').val();
		
		/*if(lossId != null && lossId != '' && lossId != ' ')
		{
			if (lossTime <= 0) 
			{ 
				alert("Enter Loss Time");
			    return ;
			}
		}
		else*/
		if(lossId == null || lossId == '' || lossId == ' ')			
		{
			alert('Select Loss');
			setTimeout(function() {setFocusOnField('cmbPlrkLossid');},550); 
			jQuery('#cmbPlrkLossid').combobox('clear');
			return;
		}
		var phenom = jQuery('#cmbPlrkReasonid').combobox('getText');
		if(phenom == null || phenom == '' || phenom == ' ')
		{
			alert('Select Phenomena');
			setTimeout(function() {setFocusOnField('cmbPlrkReasonid');},550); 
			jQuery('#cmbPlrkReasonid').combobox('clear');
			return;
		}
		var ins = jQuery('#txtPlrkInstance').val();
		if(ins == null || ins == '' || ins == ' ')
		{
			alert('Enter Instance');
			setTimeout(function() {setFocusOnField('txtPlrkInstance');},550); 
			return;
		}
		var mins = jQuery('#txtPlrkMinutes').val();
		var minsLbl = jQuery('#lblTimeOrQty').html();		
		if(mins == null || mins == '' || mins == ' ' )
		{
			alert('Enter '+ minsLbl);
			setTimeout(function() {setFocusOnField('txtPlrkMinutes');},550); 
			return;
		}
		else
		{
			if(minsLbl.indexOf('Time') >=0)
			{
				wno = '';
				if (lossTime <= 0) 
				{ 
					alert("Enter Loss Time");					
					setTimeout(function() {setFocusOnField('txtPlrkMinutes');},550); 
					return;
				}
			}
			else if(minsLbl.indexOf('Qty') >=0)
			{
				if( (parseInt(mins,10) <= 0) )
				{
					alert(minsLbl + ' can not be zero' );
					return;
				}
				var qtyFlag = jQuery('#hdnUpdateLossQtyFlag').val();
				jQuery('#hdnUpdateLossQtyFlag').val('');
			    var idS = jQuery("#pcsLossEntryGrid").jqGrid('getDataIDs');		
			    var s=0;
				for(i=0;i<idS.length;i++)	
				{
					if(qtyFlag != null && qtyFlag != '' && qtyFlag != ' ') 
					{
						if(qtyFlag != idS[i])
						{
						 var qty = jQuery("#pcsLossEntryGrid").getCell(idS[i], 'txtPlrkQty');
						 if(qty != null && qty != '' && qty != ' ')
							s = parseFloat(s) + parseFloat(qty);
						}
					}
					else
					{
						 var qty = jQuery("#pcsLossEntryGrid").getCell(idS[i], 'txtPlrkQty');
						 if(qty != null && qty != '' && qty != ' ')
							s = parseFloat(s) + parseFloat(qty);
					}
				}
			
				mins = parseFloat(s) + parseFloat(mins);
				
				var producedQty = jQuery('#txtproducedqty').val();				
				if(parseFloat(mins)>parseFloat(producedQty))
				{
					alert("Rejection Breakup Should not Exceed Produced Quantity");
					jQuery('#txtPlrkMinutes').val('');
					setTimeout(function() {setFocusOnField('txtPlrkMinutes');},550); 
					return;
				}
				
				var rejQty = jQuery('#txtRejQty').val();
				if(rejQty != null && rejQty != '' && rejQty != ' ')
				{
					if(parseFloat(mins)>parseFloat(rejQty))
					{
						alert("Rejection Breakup Should not Exceed Rejected Quantity");
						jQuery('#txtPlrkMinutes').val('');
						setTimeout(function() {setFocusOnField('txtPlrkMinutes');},550); 
						return;
					}
				}
			}
		}
		
		if (jQuery('#lblRemarks').html() == "Problem") { 
			if (jQuery('#txaPlrkRemarks').val() == "" || jQuery('#txaPlrkRemarks').val() ==" ") {
				alert("Enter Problem");
				return; 
			}
		}
		if (jQuery('#lblTimeOrQty').html() == "Loss Qty") {
			/*if (jQuery('#cmbPlrkCauseid').combobox('getValue')=="" || jQuery('#cmbPlrkCauseid').combobox('getValue')==" ") {
				alert("Select Cause");
				return; 
			}*/

			if (jQuery('#cmbPlrkRootcauseid').combobox('getValue')=="" || jQuery('#cmbPlrkRootcauseid').combobox('getValue')==" ") {
				alert("Select Root Cause");
				return; 
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
			
		var formId = 'frmPcsLossEntry';
		var url = "pcsLossEntry_save.pcs?q=2";		
		if (jQuery('#hdnLossMode').val() == "Update")
			url+= '&saveMode=Update';
		else
			url+= '&saveMode=Save';		

		var lossId = jQuery('#cmbPlrkLossid').combobox('getValue');
		var lossValue = jQuery('#txtPlrkMinutes').val();
		var Pldetailsid = jQuery('#hdnPlrkPldetailid').val();
		var PlrkKeyid = jQuery('#hdnPlrkKeyid').val();
		//var date = jQuery('#txtLabelDate').val();
		url += "&PlrkKeyid="+PlrkKeyid+"&Pldetailsid="+Pldetailsid+"&lossId="+lossId+"&lossValue="+lossValue;
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsEntry input[id='cell']").val();
		var mchId = jQuery('#hdnmachineid').val();

		 var cause = jQuery('#cmbPlrkCauseid').combobox('getValue');
		 
		 
		url += "&txtPlrkDate="+date+"&txtPlrkShiftid="+shift+"&txtPlrkFactoryid="+factId+"&txtPlrkSectionid="+sectId+"&txtPlrkCellid="+cellId+"&txtPlrkMachineid="+mchId+'&txtPlrkWno='+wno;
		if(formId.length > 0 )
			saveForm(formId,url);		
	}
	function frmPcsLossEntry_successsCallback(result) {		
		
		processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid="+jQuery('#hdnPlrkPldetailid').val(),"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","","pcsLossEntryGridError");
		//jQuery("#pcsLossEntryGrid").jqGrid().trigger("reloadGrid");
		fnLossClear();
		//fnClear();	
		enableFields('cmbPlrkLossid');
		setFocusOnField('cmbPlrkLossid');
		setTimeout(function() { fnGetPendingTime(); },550);
	}

	function fnLossClear() {		
		
		jQuery('#cmbPlrkReasonid').combobox('clear');		
		jQuery('#cmbPlrkLossid').combobox('clear');
		jQuery('#txtPlrkMinutes').val('');
		jQuery('#txtPlrkInstance').val('');		
		jQuery('#txaPlrkRemarks').val('');
		jQuery('#hdnMode').val("");
		jQuery('#hdnPlrkKeyid').val("");		
		jQuery('#cmbPlrkCauseid').combobox('clear');
		jQuery('#cmbPlrkRootcauseid').combobox('clear');
		//jQuery('#btnAncilLink').css('display','none');
		disableLossFields();

		fnGetPendingTime();
		
		jQuery('#hdnOldUsedTime').val("0");
		jQuery('#hdnPendingTime').val("0");
		jQuery('#hdnLossMode').val("");
		
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
		if (lossId != "" && lossId != " ") {						
			if (isQtyLoss =="Y") {			
				setTimeout(function() { setComboDefaultValue("frmPcsLossEntry", "cmbPlrkCauseid"); },550);								
				//setTimeout(function() { setComboDefaultValue("frmPcsLossEntry", "cmbPlrkWno"); },1250);				
			}
		}
	}
	
	function fillValueForQtyOrLoss()
	{		
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		if (isQtyLoss =="Y") {
			jQuery('#lblTimeOrQty').html("Loss Qty");			
			jQuery('#lblCause').show();
			jQuery('#lblRootCause').show();	
			jQuery('#causeLossPCS').show();
			jQuery('#rootcauseLossPCS').show();			
		}
		else {
			jQuery('#lblTimeOrQty').html("Loss Time");
			//jQuery('#lblCause').hide();
			jQuery('#lblRootCause').hide();	
			//jQuery('#causeLossPCS').hide();
			jQuery('#rootcauseLossPCS').hide();		
			jQuery('#cmbPlrkWno').combobox('clear');
			jQuery('#cmbPlrkCauseid').combobox('clear');
			jQuery('#cmbPlrkRootcauseid').combobox('clear');
		}		
	}

	function pcs_EntryCBFormatter(cellvalue, options, rowObject)
	{
		var id = options.rowId;
		
		var flag = rowObject[0];
		if(id == '1')
	  		return '<input  type="checkbox" id="cbox_'+id+'" checked onclick="if(this.checked){selectSubloss(\''+id + '\');}else{unselectSubloss(\''+id + '\');}"/>';
	  	else
	  		return '<input  type="checkbox"  id="cbox_'+id+'" onclick="if(this.checked){selectSubloss(\''+id + '\');}else{unselectSubloss(\''+id + '\');}"/>';
	}
	function selectSubloss(id)
	{
		 var idS = jQuery("#pcsEntryGrid").jqGrid('getDataIDs');		
		 for(i=0;i<idS.length;i++)	
		 {
			 if(idS[i] != id)
			 	jQuery('#cbox_'+idS[i]).attr('checked',false);
		 }
		pcsEntryGrid_dblClick(id);
		//fnLossClear();
		//enableFields('cmbPlrkLossid');
		//var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',id);
		//var pldetailsid =rowData.txtPldetailsid;
		//jQuery('#hdnPlrkPldetailid').val(pldetailsid);
		setFocusOnField('cmbPlrkLossid');
		
		/*var inputs = jQuery('#frmPcsLossEntry :input[type!=hidden]');
		var i=0;	 
	    inputs.each(function() {
	   
	       if(i==1)
		   {
	    	 if(inputs.hasClass('combo-text validatebox-text'))
	    	  	 inputs.focus();	    	  
		   }
	       i++;
	    });*/
		
		/*jQuery(inputs).each(function () {
			controlId = this.id;
			jQuery('#'_).find('li').each(function(){	
		});*/
		//jQuery("#frmPcsLossEntry:first *:input[type!=hidden]:first").focus();
		//processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid="+pldetailsid,"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
	}
	function unselectSubloss(id)
	{
		processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid=","pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
		fnLossClear();
		fnClear();
	}
	function pcsLossEntryGrid_dblClick(id)
	{		
		var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',id);
		if (rowData.cmbMsrno != "-" && rowData.cmbMsrno != "" && rowData.cmbMsrno != "{}" && rowData.cmbMsrno != " "  )
			return ;
		
		if (rowData.cmbPlrkLossId != null )
			jQuery('#hdnLossMode').val("Update");
		else
			jQuery('#hdnLossMode').val("");
		
		jQuery('#hdnPlrkKeyid').val(rowData.txtPlrkKeyid);
		jQuery('#hdnUpdateLossQtyFlag').val(id);
		jQuery('#cmbPlrkLossid').combobox('setValue',rowData.cmbPlrkLossId);
		readOnlyFields("cmbPlrkLossid");
		//readOnlyFields("cmbPlrkReasonid");		
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
		var lossName = jQuery("#cmbPlrkLossid").combobox("getText");
		if(lossName.substring(0,3)=="7.1")
			reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_cause.pcs"+dataStr);
		else		
			reloadCombo("frmPcsLossEntry","cmbPlrkCauseid","combo_pcsLossCause.pcs"+dataStr);	
		
		setTimeout(function() { jQuery('#hdnOldUsedTime').val(oldTime); },550);
		fillValueForQtyOrLoss();		
		jQuery('#txaPlrkRemarks').val(rowData.txaPlrkRemarks);		
		jQuery('#cmbPlrkReasonid').combobox('setValue',parentId);			
		//setFieldValue('cmbPlrkWno',rowData.cmbPlrkWno);		
		//setTimeout(function() { jQuery('#cmbPlrkWno').combobox('setValue',rowData.cmbPlrkWno); },550);
		setTimeout(function() { jQuery('#cmbPlrkCauseid').combobox('setValue',rowData.cmbPlrkCauseid); },550);
		setTimeout(function() { setFieldValue('cmbPlrkRootcauseid',rowData.cmbPlrkRootcauseid); },550);
		var lossName =jQuery('#cmbPlrkLossid').combobox('getText');
		if (lossName.substring(0,3)=="8.3") {			
			//jQuery('#btnAncilLink').css('display','block');
			disableField("frmPcsLossEntry","txtPlrkMinutes");
		}
		else
			enableFields('txtPlrkMinutes');
		if(isQtyLoss != 'Y')
			setTimeout(function() { fnGetPendingTime(); },550);
	
		enableFields("cmbPlrkReasonid");
		enableFields('txtPlrkInstance');
		enableFields('cmbPlrkCauseid');
		enableFields('cmbPlrkRootcauseid');
		enableFields('txaPlrkRemarks');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');
		setTimeout(function() { setFocusOnField('cmbPlrkReasonid'); },550);
	}
	
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
		loadFunctionalLocation("prlmfunLocation","functionalLocation_Entry.pcs","prlmfunLocationValues","frmPcsEntry",dataStr);
		
	}
	
	
	function getFieldsForPcsSuccess(result) {		
		//alert(result.value.indexOf("EQ"));
		if (result.value.indexOf("EQ") != -1) {			
			jQuery('#spnExpansionLabel').css('display','none');
			jQuery('#spnExpansionQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtexpansionqty");
			jQuery('#spnRejQty').css('display','none');	
			jQuery('#lblRejQty').css('display','none');	
		}
		if (result.value.indexOf("TQ") != -1) {			
			jQuery('#spnTrimmingLabel').css('display','none');
			jQuery('#spnTrimmingQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txttrimmingqty");
			jQuery('#spnRejQty').css('display','none');	
			jQuery('#lblRejQty').css('display','none');	
		}
		if (result.value.indexOf("WO") != -1) {
			jQuery('#spnLabelWno').css('display','none');
			jQuery('#spntxtWno').css('display','none');	
			jQuery('#spnBackLogQty').css('display','none');	
			jQuery('#lblBackLog').css('display','none');		
			jQuery('#spncyctime').css('padding-left','145px');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtwno");			
		}
		if (result.value.indexOf("NOPLAN") != -1) {
			jQuery( '#spnNoPlan').hide();
			jQuery('#causeLossPCS').css('display','none');	
			jQuery('#lblCause').css('display','none');	
					
		}		
		/*jQuery('#spnModel').css('display','none');
		jQuery('#spnLabelWno').css('display','none');
		jQuery('#spnProductid').css('display','none');
		jQuery('#spntxtWno').css('display','none');
		*/
	}

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
		var sectId = jQuery("#frmPcsEntry input[id='section']").val();
		dataStr+= "?q=2&entryDate="+entryDate+"&shift="+shift+"&cellId="+cellId+"&sectId="+sectId;
		
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

		
		setTimeout(function() {jQuery('#cmbCycletimeCavity').combobox('setValue',rowData.txtTheoriticalcycletime);},2500);
		jQuery('#cmbrawmaterialtype').combobox('setValue',rowData.cmbrawmaterialtype);
		
		jQuery('#txtwno').val(rowData.txtwno);
		jQuery('#txtproducedqty').val(rowData.txtproducedqty);
		jQuery('#txtplannedqty').val(rowData.txtplannedqty);
		jQuery('#txtnoplaninmins').val(rowData.txtnoplaninmins);
		//jQuery('#txtcalendartime').val(rowData.txtcalendartime);
		jQuery('#txtCavityavailable').val(rowData.txtCavityavailable);
		jQuery('#txtbacklogqty').val(rowData.txtbacklogqty); 				

		jQuery('#txttrimmingqty').val(rowData.txttrimmingqty);
		jQuery('#txtexpansionqty').val(rowData.txtexpansionqty);
		//jQuery('input:checkbox[name=chkTrimming]').attr('checked',true);
		//jQuery('input:checkbox[name=chkExpansion]').attr('checked',true);		
		//enableFields("chkTrimming");
		//enableFields("chkExpansion");
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
		
		jQuery('#cbox_'+id).attr('checked',true);
		 var idS = jQuery("#pcsEntryGrid").jqGrid('getDataIDs');		
		 for(i=0;i<idS.length;i++)	
		 {
			 if(idS[i] != id)
			 	jQuery('#cbox_'+idS[i]).attr('checked',false);
		 }
		 
		 fnLossClear();
		 var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',id);
		 var pldetailsid =rowData.txtPldetailsid;
		 jQuery('#hdnPlrkPldetailid').val(pldetailsid);
		 enableFields('cmbPlrkLossid');
		 processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid="+pldetailsid,"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
			
	}
	function checkMasterIdRecallSuccess(result)
	{		
		jQuery('#hdnPlmMasterId').val(result.Plmasterid);
		var expQtyMandFlag = result.ExpQtyMandFlag;		
		if(expQtyMandFlag != null || expQtyMandFlag != '' || expQtyMandFlag != ' ')	
		{
			if(expQtyMandFlag != 'Y')
			{
				makeMandatory();
			}
		}
		else
			makeMandatory();
	}
	function makeMandatory()
	{
		jQuery('input:checkbox[name=chkTrimming]').attr('checked',true);
		jQuery('input:checkbox[name=chkExpansion]').attr('checked',true);
		jQuery("#spnTrimmingLabel").addClass('mandatory-lbl');	
		jQuery("#spnExpansionLabel").addClass('mandatory-lbl');					
		disableField("frmPcsEntry","chkTrimming");
		disableField("frmPcsEntry","chkExpansion");
	}

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
		//disableField("frmPcsEntry","txttrimmingqty");
		//disableField("frmPcsEntry","txtexpansionqty");
		//readOnlyFields("txttrimmingqty");
		//readOnlyFields("txtexpansionqty");
		jQuery('#chkTrimming').attr('checked',true);
		jQuery('#chkExpansion').attr('checked',true);
		
		var mchId = jQuery('#hdnmachineid').val();
		var entryDate=jQuery('#hdnPrlmEntrydate').val();
		var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;

		reloadCombo("frmPcsEntry","cmbproductmodel","combo_productModel.pcs?q=2"+cond );				
		
		fillcmbProduct();
		jQuery('#hdnMode').val("");
		//jQuery('#hdnPlmMasterId').val("");
		getCalendarTime();		
		/*var dataStr = "&factId="+factId;
		dataStr = "&factId="+factId+"&prdModelId=+&mchId="+mchId;				
		reloadCombo("frmPcsEntry","","combo_product.pcs?q=2"+dataStr );
		*/
	}


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
	function InsertLossEntry(isClear)
	{
		if (jQuery('#hdnMode').val() == "" && checkAlreadyExist()==true) {
			return true;
		}				
		if(jQuery('#chkNoPlan').is(':checked')==true) {
			noPlanSave();	
			return true;
		}		
		
		var prodQty = jQuery('#txtproducedqty').val();
		if (prodQty < 0) { alert("Enter Produced Qty"); return ;}
		var rejQty = jQuery('#txtRejQty').val();
		
		if (rejQty == null || rejQty == '' || rejQty == ' ') 
		{ 
			var lblRejQty = jQuery('#spnRejQty').css('display');
			if(lblRejQty != 'none')
			{
				alert("Enter Rejection Qty"); 
				return ;
			}
		}
		else
		{
			if (prodQty != null || prodQty != '' || prodQty != ' ') 
			{ 
				if(parseInt(rejQty)>parseInt(prodQty))
				{
					alert("Rejection Qty should Not exceed Produced Qty"); 
					return ;
				}
			}
		}
		
		var mchId = jQuery('#hdnmachineid').val();
		var date=jQuery('#hdnPrlmEntrydate').val();
		var shift=jQuery('#hdnPrlmShiftid').val();		
		var ds = '?q=2&date='+date+'&shift='+shift+'&mchId='+mchId;
		if(isClear != null && isClear != '' && isClear != ' ')
			ds += '&isClear='+isClear;		
		processAjaxCalls('checkIsOpenMsr.pcs',ds,"CheckMsrSuccess");
	}

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

		
		var lossName = jQuery("#cmbPlrkLossid").combobox('getText');		
		//
		url += "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid+"&Ptwokeyid="+Ptwokeyid+"&oldTime="+oldTime;
		url += "&isOpenMst="+isOpenMst;	
		
		if(result.isClear != null && result.isClear != '' && result.isClear != ' ')
			url += "&isClear="+result.isClear;		
		
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

	function frmPcsEntry_exceptionCallback(result) {
		var pcsZVal = jQuery('#loadPCSPopUp').css('z-index');	
		
		if(pcsZVal != null && pcsZVal != '' && pcsZVal != ' ')
			jQuery('.layout-panel-south').css('z-index',parseInt(pcsZVal)+1);
		else
			jQuery('.layout-panel-south').css('z-index',1012);
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
		jQuery('#hdnPlrkPldetailid').val(result.dtlKeyId);
		//jQuery('#hdnPlrkPldetailid').val('');
		
		setTimeout(function() {fillLossEntryGrid(result.isClear);},2500);   
		fnLossClear();
		var rejQty = jQuery('#txtRejQty').val();
		var lineRejection = false;
		if(rejQty != null && rejQty != '' && rejQty != ' ')
			lineRejection = true;
		if(result.isClear != null && result.isClear != '' && result.isClear != ' ' && result.isClear == 'Y')
		{
			if(lineRejection)
			{
				enableFields('cmbPlrkLossid');
				//setTimeout(function() {getDetailsID();},1250);
				setFocusOnField('cmbPlrkLossid');
				processAjaxCalls('getLineRejectionLoss.pcs','?q=2',"LineRejQtySuccess");				
			}
			else
			{
				setTimeout(function() {jQuery('#cbox_1').attr('checked',false);},1250);
				setFocusOnField('cmbproductid');	
				fnClear();
				jQuery('#hdnPlrkPldetailid').val('');
			}
		}		
		else
		{
			enableFields('cmbPlrkLossid');
			//setTimeout(function() {getDetailsID();},1250);
			setFocusOnField('cmbPlrkLossid');		
			if(lineRejection)		
			{
				processAjaxCalls('getLineRejectionLoss.pcs','?q=2',"LineRejQtySuccess");	
			}		
		}
		
	}
	function LineRejQtySuccess(result)
	{
		if(result.loss != '')
			jQuery('#cmbPlrkLossid').combobox('setValue',result.loss);
		enableFields('cmbPlrkReasonid');
		reloadCombo("frmPcsLossEntry","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );														
		jQuery('#lblTimeOrQty').html("Loss Qty");			
		jQuery('#lblCause').show();
		jQuery('#lblRootCause').show();	
		jQuery('#causeLossPCS').show();
		jQuery('#rootcauseLossPCS').show();		
		setTimeout(function() { setComboDefaultValue("frmPcsLossEntry", "cmbPlrkCauseid"); },550);		
	}
	function getDetailsID()
	{
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsid =rowData.txtPldetailsid;			
		jQuery('#hdnPlrkPldetailid').val(pldetailsid);	
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
		processGridnew('pcsEntryGridTest_view.pcs',dataStr,"pcsEntryGrid","pcsentryPager","PCS Entry","pcsEntryGrid_dblClick","","pcsEntryGrid_loadComplete","pcsEntryGridError");
		
	}
	function fillLossEntryGrid(isClear)
	{

		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsid =jQuery('#hdnPlrkPldetailid').val();
		
		if(rowData.txtPldetailsid != null && rowData.txtPldetailsid != '' && rowData.txtPldetailsid != ' ')
		{
			if(isClear != 'Y')
			{
				pldetailsid = rowData.txtPldetailsid;
				pcsEntryGrid_dblClick(1);
				enableFields('cmbPlrkLossid');		
				jQuery('#hdnPlrkPldetailid').val(pldetailsid);	
			}
		}	
		else	
			processGridnew('pcsLossEntryGrid_view.pcs',"?q=2&pldetailsid="+pldetailsid,"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
	}
	function pcsEntryGrid_loadComplete() {
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
		var pldetailsid =rowData.txtPldetailsid;
		pcsEntryGrid_dblClick(1);
		//jQuery('#hdnPlrkPldetailid').val(pldetailsid);				
		//processGridnew('pcsEmployeeGrid_view.pcs','?q=2&pldetailsid='+pldetailsid,"pcsEmployeeGrid","pcsEmployeePager","PCS Employee","pcsEmployeeGrid_dblClick","","pcsEmployeeGrid_loadComplete","pcsEmpGridError");
		noPlanOptionEnable();
		jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtwno");

		//setTimeout(function() {fillLossEntryGrid('');},4000);
		//setTimeout(function() {getRejectionQty();},5000);
	}

	function noPlanOptionEnable() {
		var reccnt = jQuery("#pcsEntryGrid").getDataIDs().length;		
		if (reccnt =="0") {		
			enableFields("chkNoPlan");
			jQuery('#btnNoPlanSave').attr('display','block');	
		}
		else {
			disableField("frmPcsEntry","chkNoPlan");//readOnlyFields("chkNoPlan");
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
	function frmPcsEntrycmbrawmaterialtype_onSelect(record) 	{
		var factId = jQuery("#frmPcsEntry input[id='factory']").val();
		var mchId = jQuery("#frmPcsEntry input[id='machine']").val();
		var prdModelId = jQuery('#cmbproductmodel').combobox('getValue');
		var entryDate=jQuery('#hdnPrlmEntrydate').val();	
		var dataStr = "&factId="+factId+"&prdModelId=&mchId="+mchId+"&entryDate="+entryDate+"&rawMaterial="+record.id;	
		//jQuery('#cmbproductid').combobox('clear');	
		//reloadCombo("frmPcsEntry","cmbproductid","combo_product.pcs?q=2"+dataStr );
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
		//jQuery('#cmbrawmaterialtype').combobox('clear');	
		//var productId = jQuery('#cmbproductid').combobox('getValue');
		//if(productId != null && productId != '' && productId != ' ')
			//reloadCombo("frmPcsEntry","cmbrawmaterialtype","combo_rawtype.pcs?q=2&productId="+productId);	
		
	}
	
/*	function frmPcsEntrycmbCellid1_onLoadSuccess() 	{
		fillComboBox("frmPcsEntry","cmbShiftid1","shift.commonFilter" );
	}
*/	
	function frmPcsEntrycmbPrlmShiftid_onLoadSuccess() 	{		
		fillCycleTime();		
		setDetailTableName();
	}	

	function frmPcsEntrycmbproductid_onSelect(record)	{		
		jQuery('#cmbCycletimeCavity').combobox('clear');		
		jQuery('#txtActualcycletime').val('');
		jQuery('#txtCavityavailable').val('');
		jQuery('#txtCavityused').val('');
		//getCalendarTime();
		//getPldetailsId();
		fillCycleTime();
		jQuery('#cmbrawmaterialtype').combobox('clear');	
		reloadCombo("frmPcsEntry","cmbrawmaterialtype","combo_rawtype.pcs?q=2&productId="+record.id);	
		//setComboDefaultValue("frmPcsEntry","cmbrawmaterialtype");
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
				disableField("frmPcsEntry","cmbCycletimeCavity");
				disableField("frmPcsEntry","txtTheoriticalcycletime");
				disableField("frmPcsEntry","txtActualcycletime");
				disableField("frmPcsEntry","txtCavityavailable");
				disableField("frmPcsEntry","txtCavityused");
				/*readOnlyFields("cmbCycletimeCavity");
				readOnlyFields("txtTheoriticalcycletime");
				readOnlyFields("txtActualcycletime");
				readOnlyFields("txtCavityavailable");
				readOnlyFields("txtCavityused");	*/			
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
		
		/*for (i=1; i <= reccnt; i++) {
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
		}*/
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

	function loadPCSPopUp_onClose() {
		
		var lossEntryGrid = jQuery("#pcsLossEntryGrid").jqGrid('getDataIDs');
		var rejQty = jQuery("#txtRejQty").val();
		var msg ="Are you sure want to Close";		
		var prdId =jQuery('#cmbproductid').combobox('getValue');
		var layoutVal = jQuery('#hdnLayoutVal').val();			
		if(rejQty != null && rejQty != '' && rejQty != ' ')
		{
			var q = 0;		
			if(lossEntryGrid.length >0)
			{
				for(i=0;i<lossEntryGrid.length;i++)	
		 		{
			 		var lossName = jQuery("#pcsLossEntryGrid").getCell(lossEntryGrid[i], 'txtLossName').trim();
			 		if(lossName.indexOf('Rejected ')>0)
				 	{
			 			var qty = jQuery("#pcsLossEntryGrid").getCell(lossEntryGrid[i], 'txtPlrkQty').trim();			    			    		
			    		if(qty != null && qty != '' && qty != ' ')
				    		q = parseInt(q) + parseInt(qty);
				 	}		
			  	}	 		
				if(parseInt(rejQty) > q)
				{
					msg = "Rejection Qty is not matching with Rejection Breakup."+msg;
				}
				
			}
			else
			{
				msg = "Rejection Breakup has not Entered."+msg;
			}			
			if  (prdId!="" && prdId != " " && prdId!=null) {
				//var msg ="Are you sure want to Close";
				//var a = jQuery.prompt(msg,{ buttons: { Ok: true, Cancel: false }, focus: 1 });
				 /* jQuery( "#dialog-confirm" ).dialog({
						resizable: false,
						height:140,
						modal: true,
						buttons: {
						"Delete all items": function() {
						$( this ).dialog( "close" );
						},
						Cancel: function() {
						$( this ).dialog( "close" );
						}
						}
				  });*/
				if(confirm(msg)) {
				//if(a){ 				
					jQuery('#submitForm').val('frmPcsView');
					getTotalResultSuccess('');
					jQuery('.layout-panel-south').css('z-index',0);
					return true;
				}
				else
					return false;
			}
			else
			{
				jQuery('#submitForm').val('frmPcsView');		
				getTotalResultSuccess('');
				jQuery('.layout-panel-south').css('z-index',0);
				return true;
			}
				
		}
		else
		{
			//var prdId =jQuery('#cmbproductid').combobox('getValue');
			//var layoutVal = jQuery('#hdnLayoutVal').val();	
			if  (prdId!="" && prdId != " " && prdId!=null) {
				//var msg ="Are you sure want to Close";
				if(confirm(msg)) { 				
					jQuery('#submitForm').val('frmPcsView');
					getTotalResultSuccess('');
					jQuery('.layout-panel-south').css('z-index',0);
					return true;
				}
				else
					return false;
			}
			else
			{
				jQuery('#submitForm').val('frmPcsView');		
				getTotalResultSuccess('');
				jQuery('.layout-panel-south').css('z-index',0);
				return true;
			}
		}
	}
	function loadPcsPopToClose()
	{
		
	}
	
	jQuery('#spnTrimmingLabel').mouseover(function() {
		
	});
	/*function showPop_successCallBack()
	{
		jQuery('#loadPCSPopUp').css('margin-top','6%');
		
	}*/
	
	jQuery("#txtproducedqty").change(function (){
		backLogQty();
	});
	jQuery("#txtRejQty").change(function (){
		backLogQty();
	});
	jQuery("#txtexpansionqty").change(function (){
		backLogQty();
	});
function backLogQty()
{
	var prodQty = jQuery("#txtproducedqty").val();
	var rejQty = jQuery("#txtRejQty").val();
	var expaQty = jQuery("#txtexpansionqty").val();
	
	if(prodQty=="")
		prodQty=0;
	if(rejQty=="")
		rejQty=0;
	if(expaQty=="")
		expaQty=0;
	
	 var z=parseInt(prodQty)-parseInt(rejQty)-parseInt(expaQty);			 
	 if(prodQty=="" && rejQty=="" && expaQty=="")
			z=0;
	
	jQuery("#txtbacklogqty").val(z);
}
</script>

<form id="frmPcsEntry" >
<input type="hidden" id="hdnLayoutVal"/>
<div class="easyui-paddingbfpx" style="width:100%;border: 1px;border-style: solid;" >
	<div> <input type="hidden" id="hdnOldTime" name="hdnOldTime"></div>
	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
<!--	<div> <input type="hidden" id="hdnPlmMasterId"></div>-->
	<div> <input type="hidden" id="hdnPldetailsid"></div>	
	<div> <input type="hidden" id="hdnPtwokeyid"></div>
	<input type="hidden" id="hdnUpdateLossQtyFlag">
	

	
	<input type="hidden" id="factory" name="cmbPrlmFactoryid" value="${requestScope.FactoryId}"></input>
	<input type="hidden" id="hdnmachineid" name="hdnmachineid" value="${requestScope.MchId}"  ></input>
	<input type="hidden" id="hdnPrlmEntrydate" name="hdnPrlmEntrydate" value="${requestScope.EntryDate}"  ></input>	
	<input type="hidden" id="hdnPrlmShiftid" name="hdnPrlmShiftid" value="${requestScope.Shift}"  ></input>
	<input type="hidden" id="cell" name="cmbPrlmCellid" value="${requestScope.CellId}"  ></input>
	<input type="hidden" id="section" name="cmbPrlmSectionid" value="${requestScope.SectionId}"  ></input>
	
<!--	<div id="dialog-confirm" title="">-->
<!--		<p>-->
<!--			<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>-->
<!--			<label id="pcsWarning"></label>-->
<!--			<div>-->
<!--				<input type="button" class="easyui-button" id="pcsWarninkOk" value="OK"/>-->
<!--				<input type="button" class="easyui-button" id="pcsWarninkCancel" value="Cancel"/>-->
<!--			</div>-->
<!--		</p>-->
<!--	</div>-->
<!-- 	<div class="main-header">PCS Entry</div>  -->
<!--	<table width="100%" height="90%" align="center">-->
<!--	<tr>-->
<!--	<td width="100%" align="left" style="border: 1px;border-style: solid;">	-->
<!--	<div style="margin-left: 1%;margin-top:-5;">-->
	<div class="pcsPopUp-firstdiv">	
	<table class="pcsPopUp-firstdivtable">
	<tr>
<!--		<td style="width:40%" valign="top">-->
		<td class="pcsPopUp-fltd" valign="top">
		<div  class="easyui-paddingbfpx">
				<div  class="easyui-paddingbfpx" id="frmPcsCalendarFuntKeyIds">
					<input type="hidden" id="factory" name="factory" value="${requestScope.FactoryId}"  ></input>
					<input type="hidden" id="section" name="section" value="${requestScope.SectionId}"  ></input>
					<input type="hidden" id="cell" name="cell" value="${requestScope.CellId}" ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.MchId}"  ></input>
				</div>
<!--				<div id="prlmfunLocation" style="padding-left: px;width:120%;" ></div>-->
				<div id="prlmfunLocation" class="pcsPopUp-funLocn"></div>
		</div>
	</td>
<!--	<td style="width:25%">-->
	<td class="pcsPopUp-dateshifttd">
<!--		<div style="padding-left: 50px;margin-top:-20px;">-->
		 <div class="pcsPopUp-dateshiftlabeldiv">
<!--			<label style="padding-left: 2px;" class="mandatory-lbl"> Date </label>-->
			<label class="mandatory-lbl pcsPopUp-lbldate"> Date </label>
<!--			<span style="padding-left: 70px">-->
<!--				<label style="padding-left: 2px;" class="mandatory-lbl">Shift</label>-->
<!--			</span>-->
			<span class="pcsPopUp-spanlblShift">
				<label class="mandatory-lbl pcsPopUp-lbldate">Shift</label>
			</span>
		</div>
<!--		<div style="padding-left: 50px;">-->
<!--			<input id="txtLabelDate" name="txtLabelDate" type="text" value="${requestScope.EntryDate}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;text-align:center; " />-->
<!--			<span style="padding-left: 10px">-->
<!--				<input type="text" class="easyui-combobox" id="cmbShiftid1" name="cmbShiftid1" readonly="readonly" disabled="disabled" style="width: 85px;"  value="${requestScope.Shift}"/>-->
<!--			</span>-->
<!--			<span style="padding-left: 2px;">-->
<!--				<input type="hidden" class="easyui-text" id="txtcalendartime" name="txtcalendartime" readonly="readonly"  style="width: 50px;"  value=""/>										-->
<!--			</span>-->
<!--		</div>		-->
	    <div class="pcsPopUp-dateshiftdiv">
			<input id="txtLabelDate" name="txtLabelDate" type="text" value="${requestScope.EntryDate}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;text-align:center; " />
			<span class="pcsPopUp-spanShift">
				<input type="text" class="easyui-combobox" id="cmbShiftid1" name="cmbShiftid1" readonly="readonly" disabled="disabled" style="width: 85px;"  value="${requestScope.Shift}"/>
			</span>
			<span class="pcsPopUp-lbldate">
				<input type="hidden" class="easyui-text" id="txtcalendartime" name="txtcalendartime" readonly="readonly"  style="width: 50px;"  value=""/>										
			</span>
		</div>		
	</td>
<!--		<label class="mandatory-lbl">Line</label>-->
<!--		<span style="padding-left: 5px">-->
<!--			<input type="text" id="cmbCellid1" name="cmbCellid1" disabled class="easyui-combobox" value="${requestScope.CellId}" style="width: 180px;"/>-->
<!--		</span>-->
<!--							-->
<!--	<td style="width:35%" valign="top">-->
	<td class="pcsPopUp-eqpmntTD" valign="top">
<!--		<div style="padding-left: 50px;margin-top:5px;">-->
<!--			<label style="padding-left: 2px;"> Equipment </label>-->
<!--		</div>-->
<!--		<div id="divMchAll" style="padding-left: 30px;margin-top:5px;" >				-->
<!--			<span id="spnPrev" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">-->
<!--				<img id="imgPrevious" name="imgPrevious" src="images/Back-.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />-->
<!--			</span>-->
<!--			<span style="vertical-align:top;margin-left: -4px;padding-bottom: 0px;margin-top: 3px;">-->
<!--				<input type="text" id="cmbMachineAll" name="cmbMachineAll" class="easyui-combobox" value="${requestScope.MchId}" style="width: 220px;"/>-->
<!--			</span>-->
<!--			<span id="spnNext" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">-->
<!--				<img id="imgNetx" name="imgNext" src="images/Forward.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />-->
<!--			</span>-->
<!--		</div>-->
		<div class="pcsPopUp-lbleqpmntdiv">
			<label class="pcsPopUp-lbldate"> Equipment </label>
		</div>
		<div id="divMchAll" class="pcsPopUp-eqpmntdiv" >				
			<span id="spnPrev" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">
				<img id="imgPrevious" name="imgPrevious" src="images/Back-.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />
			</span>
			<span style="vertical-align:top;margin-left: -4px;padding-bottom: 0px;margin-top: 3px;">
				<input type="text" id="cmbMachineAll" name="cmbMachineAll" class="easyui-combobox" value="${requestScope.MchId}" style="width: 220px;"/>
			</span>
			<span id="spnNext" style="vertical-align:top;margin-left: -3px;margin-top: -2px;">
				<img id="imgNetx" name="imgNext" src="images/Forward.png" style="margin-top: -2px;width:30px;height:25px;cursor: pointer;" />
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
	
	<div id ="pcLabel" class="pcsPopUp-pcsFieldslbldiv">
		<span id="spnModel" class="pcsPopUp-prdModel" > <label > Model </label></span>
		<span class="mandatory-lbl pcsPopUp-prodName">Product Name</span>
		<span id="spnLabelWno" title="Work Order No" style="" class="mandatory-lbl pcsPopUp-woNo" >W.O.No.</span>			
		<span id="spncyctime"  class="mandatory-lbl pcsPopUp-cyctime"> Cycle Time</span>
		<span style="" title="Actual Cycle Time" class="mandatory-lbl pcsPopUp-acttym">Act.Time</span>		
	
	    <span   class="mandatory-lbl pcsPopUp-cavtAvl">Cav. Avl.</span>
		<span  class="mandatory-lbl pcsPopUp-cavUsd">Cav. Used</span>						
<!--			<label style="padding-left: 5px;" class="mandatory-lbl"> No Plan </label>-->
		<span id="lblPrdQty" title="Press F2 or Enter to Save" style="word-spacing: " class="mandatory-lbl pcsPopUp-prdQty"> Prod.Qty </span>
		<span id="lblRejQty" class="mandatory-lbl pcsPopUp-prdQty"> Rej.Qty </span>
<!--			(Moulding Qty)-->
		<span id="spnTrimExapn"  style="display: 1">
		<span id="spnTrimmingLabel" title="Trimming Qty"  class="pcsPopUp-trimmingQty">
			<input type="checkbox" id="chkTrimming" name="chkTrimming" value="Y" > 
			<span> Trim.Qty</span>
		</span>
		<span id="spnExpansionLabel" title="Expansion Qty" style="padding-left: px;" class="pcsPopUp-expansionQty">
			<input type="checkbox" id="chkExpansion" name="chkExpansion" value="Y" > 
			<span>Exp.Qty</span>
			
		</span>
		<span id="lblBackLog"  style="display:none1" title="BackLog.Qty" class="pcsPopUp-acttym">BackLog.Qty</span>
		
		</span>
		<span class="pcsPopUp-lbldate">	
			<label > Raw Material Type</label>
		</span>
<!--		<span style="padding-left: 23px;">	-->
		<span class="pcsPopUp-lblwt">
			<label > Weight</label>
		</span>
	</div>
<!--style="margin-left: 1%;"-->
	<div class="easyui-paddingbfpx pcsPopUp-pcsFieldsdiv">    	
<!--		<span id="spnProductid" style="">-->
<!--			<input type="text" id="cmbproductmodel" name="cmbproductmodel" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 120px;"/>-->
<!--		</span>-->
<!--		<span style="padding-left: 4px;">-->
<!--	    	<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 200px;"/>-->
<!--	    </span>-->
<!--	    -->
<!--		<span id="spntxtWno" style="padding-left: 4px;">-->
<!--	    	<input type="text" id="txtwno" name="txtwno" class="easyui-text" value="" style="width: 70px;"/>-->
<!--	    </span>-->
<!--										-->
<!--	    <span style="padding-left: 4px;">-->
<!--	    	<input type="text" id="cmbCycletimeCavity" name="cmbCycletimeCavity" class="easyui-combobox" value="" style="width: 60px;text-align:right;"/>	    	-->
<!--	    </span>-->
<!--	    <input type="hidden" id="txtTheoriticalcycletime" name="txtTheoriticalcycletime" class="easyui-combobox" value="" style="width: 60px;"/>-->
<!--	    <span style="padding-left: 4px;">-->
<!--	    	<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 50px;text-align:right;"/>-->
<!--	    </span>-->
<!---->
<!--    	-->
<!--	    	-->
<!--	    <span style="padding-left: 15px;">-->
<!--	    	<input type="text" id="txtCavityavailable" name="txtCavityavailable" class="easyui-text" value="" style="width: 50px;text-align:right;"/>-->
<!--	    </span>-->
<!--	    <span style="padding-left: 4px;">-->
<!--	    	<input type="text" id="txtCavityused" name="txtCavityused" class="easyui-text" value="" style="width: 50px;text-align:right;"/>-->
<!--	    </span>-->
<!--	    <span style="padding-left: 4px;">-->
<!--	    	<input type="hidden" class="easyui-text" id="txtnoplaninmins" name="txtnoplaninmins" style="width: 50px;"  value=""/>-->
<!--	    </span>-->
<!--		<span style="padding-left: 14px;">-->
<!--	    	<input type="text" class="easyui-text" id="txtproducedqty" name="txtproducedqty" style="width: 50px;text-align:right;"  value=""/>-->
<!--	    </span>-->
<!--	    -->
<!--		<span id="spnExpansionQty"  style="padding-left: 4px;display: none1">-->
<!--			<input type="text" class="easyui-text" id="txttrimmingqty" name="txttrimmingqty" style="width: 50px;text-align:right;"  value=""/>	-->
<!--		</span>	    -->
<!---->
<!--		<span id="spnTrimmingQty"  style="padding-left: 4px;display: none1">						-->
<!--			<input type="text" class="easyui-text" id="txtexpansionqty" name="txtexpansionqty" style="width: 50px;text-align:right;"  value=""/>			-->
<!--		</span>	    	    		-->
<!--		<span style="padding-left: 4px;">	-->
<!--			<input type="text" id="cmbrawmaterialtype" name="cmbrawmaterialtype" class="easyui-combobox" style="width: 120px;"/>-->
<!--		</span>-->
<!--		<span style="padding-left: 4px;">-->
<!--			<input type="text" class="easyui-text" id="txtweight" name="txtweight" style="width: 50px;text-align:right;"  value=""/>			-->
<!--		</span>-->
	    <span id="spnProductid" style="">
			<input type="text" id="cmbproductmodel" name="cmbproductmodel" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 120px;"/>
		</span>
		<span class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 200px;"/>
	    </span>
	    
		<span id="spntxtWno" class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" id="txtwno" name="txtwno" class="easyui-text" value="" style="width: 70px;"/>
	    </span>
										
	    <span class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" id="cmbCycletimeCavity" name="cmbCycletimeCavity" class="easyui-combobox" value="" style="width: 60px;text-align:right;"/>	    	
	    </span>
	    <input type="hidden" id="txtTheoriticalcycletime" name="txtTheoriticalcycletime" class="easyui-combobox" value="" style="width: 60px;"/>
	    <span class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 40px;text-align:right;"/>
	    </span>

    	
	    	
	    <span class="pcsPopUp-cavityAvailable">
	    	<input type="text" id="txtCavityavailable" name="txtCavityavailable" class="easyui-text" value="" style="width: 40px;text-align:right;"/>
	    </span>
	    <span class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" id="txtCavityused" name="txtCavityused" class="easyui-text" value="" style="width: 50px;text-align:right;"/>
	    </span>
	    <span class="pcsPopUp-pcsFieldsspan">
	    	<input type="hidden" class="easyui-text" id="txtnoplaninmins" name="txtnoplaninmins" style="width: 50px;"  value=""/>
	    </span>
		<span class="pcsPopUp-producedQuantity">
	    	<input type="text" class="easyui-text" title="Press F2 or Enter to Save" id="txtproducedqty" name="txtproducedqty" style="width: 50px;text-align:right;"  value=""/>
	    </span>
	    <span id="spnRejQty" class="pcsPopUp-pcsFieldsspan">
	    	<input type="text" class="easyui-text" id="txtRejQty" name="txtRejQty" style="width: 50px;text-align:right;"  value=""/>
	    </span>
		<span id="spnExpansionQty"  class="pcsPopUp-pcsFieldsspan" style="display: none1">
			<input type="text" class="easyui-text" id="txttrimmingqty" name="txttrimmingqty" style="width: 50px;text-align:right;"  value=""/>	
		</span>	    

		<span id="spnTrimmingQty"  class="pcsPopUp-pcsFieldsspan" style="display: none1">						
			<input type="text" class="easyui-text" id="txtexpansionqty" name="txtexpansionqty" style="width: 50px;text-align:right;"  value=""/>			
		</span>	 
		<span id="spnBackLogQty"  class="pcsPopUp-pcsFieldsspan" style="display: none1">						
			<input type="text" class="easyui-text" id="txtbacklogqty" name="txtbacklogqty" style="width: 50px;text-align:right;" disabled="disabled"  value=""/>			
		</span>	    		
		<span class="pcsPopUp-pcsFieldsspan">	
			<input type="text" id="cmbrawmaterialtype" name="cmbrawmaterialtype" class="easyui-combobox" style="width: 120px;"/>
		</span>
		<span class="pcsPopUp-pcsFieldsspan">
			<input type="text" class="easyui-text" id="txtweight" name="txtweight" style="width: 50px;text-align:right;"  value=""/>			
		</span>	  
					

    </div>

<!--	<div id ="pcLabel" style="margin-top: 0%">-->
<!--		-->
<!--		-->
<!--	</div>-->
<!--	-->
<!--	<div class="easyui-paddingbfpx" >-->
<!--	  -->
<!---->
<!--		-->
<!--	</div>    -->
	    
    <!--    
	<div  style=" height : 0px;width:700px;">
		<span style="float:left; padding-left:25px;" id="err_cmbproductid" class="tpm-errormsg"> </span>
		<span style="float:left; padding-left:255px;" id="err_txtActualcycletime" class="tpm-errormsg"> </span>
		<span style="float:left; padding-left:60px;" id="err_txtproducedqty" class="tpm-errormsg"> </span>
	</div>-->
	
<!--   		 	<div style="margin-top:0px;margin-left:1%;">			-->
<!--				<label > Remarks</label>-->
<!--			</div>-->
<!--			<div style="margin-left:1%;">-->
<!--				<span style="padding-left: 0px;">	-->
<!--					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txaRemarks" name="txaRemarks" style="width: 250px;height: 30px" rows="3" cols="1"></textarea>-->
<!--				</span>-->
<!--				<span style="padding-left: 10px;vertical-align: 3px;">			-->
<!--					<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Insert" style="width: 50px;height:20px;">-->
<!--					<input type="button" id="btnEnterLoss" name="btnEnterLoss" class="easyui-button" value="Enter Loss" style="height:20px;"/>-->
<!--		    		<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">-->
<!--		    		<input type="button" id="btnDelete" name="btnDelete" class="easyui-button" value="Delete" style="width: 50px;height:20px;">-->
<!--		    		<input type="button" id="btnClose" name="btnClose" class="easyui-button" value="Close" style="width: 50px;height:20px;">-->
<!--		    		<input type="button" id="btnOperatordtls" name="btnOperatordtls" class="easyui-button" value="Operator Details" style="height:20px;"/>-->
<!--		    	</span>-->
<!--	    		-->
<!--    			<div style="margin-left:750px;margin-top:-35px;">-->
<!--	    			<label style="font-family:sans-serif; ;font-size: 11px;font-weight: bold;">Unreported Time</label>-->
<!--	    			<div>-->
<!--	    				<input id="txtUnreportedTime" name="txtUnreportedTime" type="text" value="${requestScope.unreportTime}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:80px;height:20px;color:black;font-weight:bold;text-align:center; " />-->
<!--	    			</div>-->
<!--    			</div>-->
<!--	    		<span id="spnNoPlan" style="padding-left: 20px;vertical-align: 3px;">	-->
<!--					<input type="checkbox" id="chkNoPlan" name="chkNoPlan" value="Y" >-->
<!--					<label style="font-family:sans-serif; ;font-size: 14px;font-weight: bold;">No Plan</label>-->
<!--				</span>-->
<!--			</div>	-->
			<div class="pcsPopUp-pcsFieldsdiv">			
				<label > Remarks</label>
			</div>
			<div class="pcsPopUp-pcsFieldsdiv">	
				<span style="padding-left: 0px;">	
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txaRemarks" name="txaRemarks" style="width: 250px;height: 30px" rows="3" cols="1"></textarea>
				</span>
				<span class="pcsPopUp-pcsentrybtnsspan">			
					<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Insert" style="width: 50px;height:20px;">
					<input type="button" id="btnEnterLoss" name="btnEnterLoss" class="easyui-button" value="Enter Loss" style="height:20px;"/>
		    		<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">
		    		<input type="button" id="btnDelete" name="btnDelete" class="easyui-button" value="Delete" style="width: 50px;height:20px;">
		    		<input type="button" id="btnClose" name="btnClose" class="easyui-button" value="Close" style="width: 50px;height:20px;">
		    		<input type="button" id="btnOperatordtls" name="btnOperatordtls" class="easyui-button" value="Operator Details" style="height:20px;"/>
		    	</span>
	    		
    			<div class="pcsPopUp-UNREPORTEDTIME">
	    			<label style="font-family:sans-serif; ;font-size: 11px;font-weight: bold;">Unreported Time</label>
	    			<div>
	    				<input id="txtUnreportedTime" name="txtUnreportedTime" type="text" value="${requestScope.unreportTime}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:80px;height:20px;color:black;font-weight:bold;text-align:center; " />
	    				<span id="spnNoPlan" class="pcsPopUp-noplanspan">	
							<input type="checkbox" id="chkNoPlan" name="chkNoPlan" value="Y" >
							<label style="font-family:sans-serif; ;font-size: 14px;font-weight: bold;">No Plan</label>
						</span>
	    			</div>
    			</div>
	    		
			</div>	
	    		
	 <div>		
	 <table>
	 <tr>
	 <td>

	<div class="pcsPopUp-divpcsEntryGrid">
		<div class="pcsEntryDiv" style="float: left;">			
				<table id="pcsEntryGrid" style="float: left;"></table>
		</div>	
	</div>
	</td>
	</tr>	
	</table>
</form>
<form id="frmPcsLossEntry">
	<table>
	<tr>
	<td>
	<input type="hidden" id="hdnOldUsedTime" name="hdnOldUsedTime">
	<input type="hidden" id="hdnWnoshow" name="hdnWnoshow">
	<input type="hidden" id="hdnIsQtyLoss" name="hdnIsQtyLoss" value="${requestScope.isQtyLoss}">	
	<input type="hidden" id="hdnPendingTime" name="hdnPendingTime">
	<input type="hidden" id="hdnLossParentId" name="hdnLossParentId" value="${requestScope.lossParentId}">
	<input type="hidden" id="hdnPlrkKeyid">
	<input type="hidden" id="hdnPlrkPldetailid" name="hdnPlrkPldetailid">
	<div> <input type="hidden" id="hdnLossMode"></div>
<!--		<div id="pcsEntryPager"></div>-->
<div  class="sub-header" style="height:12px;">Loss Details  <label style="float:right;font-size: 10;">Press Tab or Enter on Loss Time to Enter Loss</label></div>
	<div style="margin-left:1%;margin-top:2px;">
		<label class="mandatory-lbl">Loss</label>		
		<label style="padding-left: 213px;" class="mandatory-lbl">Phenomena</label>
		<label style="padding-left: 172px;" class="mandatory-lbl">Instance</label>		
		<label id="lblTimeOrQty" style="padding-left: 6px;" class="mandatory-lbl">Time/Qty</label>
		<label id="lblCause"  style="padding-left: 4px;">Cause</label>
		<label id="lblRootCause" class="mandatory-lbl" style="padding-left: 204px;">Root Cause</label>
	</div>
	<div style="margin-left:1%;" id="divLE">
			<input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" onKeyPress="return checkIt(event)" class="easyui-combobox" value="" style="width: 230px;"/>
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
		     <span id="causeLossPCS" style="padding-left: 10px;">
		    	<input type="text" id="cmbPlrkCauseid" name="cmbPlrkCauseid" Clear="false" class="easyui-combobox" value="" style="width: 230px;" />
		    </span>
	    	<span id="rootcauseLossPCS" style="padding-left: 10px;">
				<input type="text" id="cmbPlrkRootcauseid" name="cmbPlrkRootcauseid" class="easyui-combobox" value="" style="width: 230px;"/>
		    </span>
	</div>
	<div style="margin-left:1%;margin-top:2px;">
		<label id="lblRemarks">Remarks</label>		
	</div>
	<div style="margin-left:1%;">
		 <span style="padding-left: px;">
	    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" rows="2" style="height: 30px;width:250px;resize:none;" cols="" id="txaPlrkRemarks" name="txaPlrkRemarks"></textarea>
	    </span>	
		<span style="padding-left: 5px;vertical-align:8px;">	    
 			<input type="button" id="btnLossInsert" name="btnLossInsert" class="easyui-button" value="Insert" style="width: 50px;height:20px;">
 		</span> 		
 		<span style="padding-left: 5px;vertical-align:8px;">
 			<input type="button" id="btnLossClear" name="btnLossClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">
 		</span>
	  	<span style="padding-left:5px;vertical-align:8px;">
   			<input type="button" id="btnLossDelete" name="btnLossDelete" class="easyui-button" value="Delete" style="width: 50px;height:20px;">
   		</span> 		
<!--	  	<span style="padding-left:5px;vertical-align:8px;">-->
<!--   			<input type="button" id="btnLossClose" name="btnLossClose" class="easyui-button" value="Close" style="width: 50px;">-->
<!--   		</span>-->
	</div>
	</td>
	</tr>
	<tr>
	<td>
	
	<div style="margin-left:1%;padding-right:50px;">
		<div  style="float:left;">			
				<table id="pcsLossEntryGrid" style="float:left;"></table>
		</div>	
	</div>
	
	</td>
	</tr></table>
	</div>
<!--	</td>-->
			
<!--	<td width ="%%" style="vertical-align:top;border: 1px;border-style: solid;margin-left: 5px" >-->
<!--		<div align="center" >-->
<!--			<label class="mandatory-lbl"> Operator Details</label>-->
<!--		</div>			-->
<!--		<div style="margin-top: 1%;">-->
<!--			<label style="padding-left: 1px;" class="mandatory-lbl"> </label>-->
<!--		</div>						-->
<!--		<label style="padding-left: 10px;" class="mandatory-lbl"> Operator</label>		-->
<!--		<div class="easyui-paddingbfpx" style="padding-left: 5px;">			-->
<!--		   <input type="text" id="cmbPopdPlemployeeid" name="cmbPopdPlemployeeid" class="easyui-combobox" value="" style="width: 220px;"/>		    -->
<!--		</div>-->
<!--		<div align="left" style="margin-top:5px;">-->
<!--		<div align="center" >			-->
<!--   			<input type="button" id="btnAddEmp" name="btnAddEmp" class="easyui-button" value="Add" style="width: 50px;">-->
<!--   			<input type="button" id="btnDelEmp" name="btnDelEmp" class="easyui-button" value="Delete" style="width: 50px;">-->
<!--	   </div>-->
<!--	   </div>-->
<!--	<div align="left" style="" class="pcsEmployeeDiv">			-->
<!--			<table id="pcsEmployeeGrid" style="float: left: ;"></table>-->
<!--	</div>		   -->
<!--	</td>-->
<!--	</tr>-->
<!--	</table>-->
</div>
</form>

