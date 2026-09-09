<script type="text/javascript">
	
	jQuery(document).ready(function(){

		initialiseForm('frmPcsView');
		jQuery('#submitForm').val('frmPcsView');
		jQuery( "#divLossEntryNew" ).hide();
		jQuery( "#pcsEntryLayericon" ).hide();
		
		//jQuery('#hdnFormLock').val("lock");				
		fillComboBox("frmPcsView","cmbCellidSelected","cellCombo.commonFilter" );
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		fillComboBox("frmPcsView","cmbMachineSelected","machineCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId  );
		formatDateBox('dtedate','dd-MMM-yyyy');
		//fillWithCurrentDate('dtedate');
		//numericTextBox('txtProducedqty');
		//numericTextBox('txtTime');
		//numericTextBox('txtNoplan');

	   var factId = jQuery("#frmPcsView input[id='factory']").val();	   
	   var sectionId = jQuery("#frmPcsView input[id='section']").val();
	   var cellId = jQuery("#frmPcsView input[id='cell']").val();
	   var machId = jQuery("#frmPcsView input[id='machine']").val();
							
	   var dataStr = "&cmbPrlmFactoryid="+factId+"&cmbPrlmSectionid="+sectionId+"&cmbPrlmCellid="+cellId+"&cmbPrlmMachineid="+machId;	  					

	   if (cellId != null)
	   	   loadFunctionalLocation("pcsefunLocation","functionalLoc_Calendar.pcs","pcsefunLocationnValues","frmPcsView","&cellId="+cellId+"&machId="+machId);
	   else
		   loadFunctionalLocation("pcsefunLocation","functionalLoc_Calendar.pcs","pcsefunLocationValues","frmPcsView",dataStr);


	   setTimeout(function() {fnGetTotalResultArray();},550);	   
	   
		var entryDate=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		processAjaxCalls('getMasterKeyid.pcs','?q=2&entryDate='+entryDate+'&shift='+shift+'&cellId='+cellId,"getMasterIdSuccess");

		processAjaxCalls('getDetailTableName.pcs','?q=2&sectId='+sectionId+"&entryDate="+entryDate,"detailTableRecallSuccess");
			
		//jQuery("#divRemarks").dialog("close");
		var mode = jQuery('#hdnMode').val();
		//alert(mode);
		processAjaxCalls('setFormActionMode.pcs','?q=2&mode='+mode,"formModeSuccess");		

/*		 jQuery("#btnfrmPcsViewmainFunLoc").click(function() {
			var formLock = jQuery('#hdnFormLock').val();
			if (formLock=="lock")  
			 	jQuery("#functLocHierarPopupId").dialog('close'); 
		});
			 	
		 jQuery('#dispFunctionalLoc a').click(function() {
			 var formLock = jQuery('#hdnFormLock').val();
				if (formLock=="lock")  
				 jQuery("#functLocHierarPopupId").dialog('close'); 
		});	
*/			
		
	});

	jQuery('#btnView').click(function() {		

		
		fnGetTotalResultArray();
		disableUIButton('btnView');
		/*readOnlyFields('cmbCellidSelected');
		readOnlyFields('dtedate');
		readOnlyFields('cmbShiftidSelected');
		readOnlyFields('cmbMachineSelected');
		*/
	});


	function openRemarksDialog(){
		jQuery('#mstfrm_div').addClass('popup-mask');	
		jQuery('#mstfrm_div').show();
		jQuery('#divRemarks').addClass('custom-popup');		
		jQuery('#divRemarks').show();		  
		jQuery('#divRemarks').css('border','1px solid #F1F5FB');
		jQuery('#divRemarks').css('z-index',100); 
	}

	jQuery( "#imgRemarks" ).click(function() {		
		closeRemarksDialog('divRemarks');
	});
	jQuery( "#btnCancel" ).click(function() {		
		closeRemarksDialog('divRemarks');
	});

	function closeRemarksDialog(dlgId)
	{
		 jQuery( '#'+dlgId ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}
			
		
	function fnGetTotalResultArray() {

		var entryDate=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		processAjaxCalls('getMasterKeyid.pcs','?q=2&entryDate='+entryDate+'&shift='+shift+'&cellId='+cellId,"getMasterIdSuccess");
		
        var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		//alert(factId);
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();		
		var dataStr1="";
		dataStr1+= "q=2&date="+date+"&shift="+shift+"&sectId="+sectId+"&cellId="+cellId+"&mchId=";
 	    processAjaxCalls('getTotalResult.pcs',dataStr1,"getTotalResultSuccess");	

 	   	
	}
	
	function getTotalResultSuccess(result) {
		
	    var entryDate=jQuery('#dtedate').datebox('getValue');
        var date=jQuery('#dtedate').datebox('getValue');
		var shiftId=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		//var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var cellId = jQuery('#cmbCellidSelected').combobox('getValue');	
		var mchId = jQuery('#cmbMachineSelected').combobox('getValue');
				
		var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&shiftId="+shiftId+"&entryDate="+entryDate+"&cellId="+cellId+"&mchId="+mchId;
		//alert(dataString);		
		jQuery("#hdnDataStr").val(dataString);
		processAjaxCalls('productionGrid_getCol.pcs',dataString,'shift_OnSuccess','shift_OnError');
		
		
	}
	
	function detailTableRecallSuccess(result)	{
		//alert(result.detailTableName);
		jQuery('#hdnDetailTable').val(result.detailTableName);
	}
function shift_OnSuccess(result)
{
	enableUIButton('btnView');	
	if(result.tpmException!=undefined || result.tpmException!=null)
	{
		alert(result.tpmException);
		jQuery(".save-loadinggrid").css("display", "none");
		//jQuery(".window-maskgrid").css("display", "block");
		jQuery('#modal_div1').removeClass('window-maskgrid');
		return false;
	}
	else
	{
		var dataString = jQuery("#hdnDataStr").val();
		processGridnew('productionGrid_view.pcs',dataString,"productionGrid","productionPager","PCS Entry","productiondblClick","","ProductionGrid_loadComplete","productionGridError");
	}
	
	
}
function shift_OnError(result)
{
	alert("err..."+Object.keys(result));
}
	function getMasterIdSuccess(result) {			
		jQuery('#hdnPlmMasterId').val(result.Plmasterid);		
	}
	
	jQuery('#btnResult').click(function() {
		//alert("Result");
		
		if (jQuery('#hdnPlmMasterId').val()=="") {
			alert("To view the Results, Save the PCS Entry");
			return ;
		}
			 
        var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery('#hdnMachineId').val();
		var dataStr="";		
		
		if (mchId=="" || mchId==" ") {
			//alert("Select the Machine Column");
			//return ;
			mchId ="";
		}
		
		dataStr+= "q=2&date="+date+"&shift="+shift+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId;
		//alert(dataStr);
		LoadPopUp("loadPCSResult", "pcsResult_input.pcs?q=2&dataStr="+escape(dataStr), true,"600px","300px","100px","200px", "showResult_successCallBack","PCS Result",true);				
	}); 

	function showResult_successCallBack() {
	}
	

	function fnView() {  
		var dataString = "?q=2&factId="+jQuery("#frmPcsView input[id='factory']").val()+"&sectId="+jQuery("#frmPcsView input[id='section']").val()+"&shiftId="+jQuery('#cmbShiftidSelected').combobox('getValue')+"&entryDate="+jQuery('#dtedate').datebox('getValue')+"&cellId="+jQuery('#cmbCellidSelected').combobox('getValue');
		//alert(dataString);
		fnGetTotalResultArray();
		//processGridnew('productionGrid_view.pcs',dataString,"productionGrid","productionPager","PCS Entry","productiondblClick","","ProductionGrid_loadComplete","productionGridError");
	}
	
	function productionGrid_keypress(keycode,iRow,iCol){
		var formLock = jQuery('#hdnFormLock').val();
		if(keycode == 119 && formLock!="lock")
		{
			var popUpFlag = jQuery("#productionGrid").jqGrid('getCell',iRow,11);			
			if(popUpFlag == 'Part No.' || popUpFlag == 'Part Name' || popUpFlag == 'Product Model')
			{				
				var dataStr = "";
				var cellId = jQuery('#cmbCellidSelected').combobox("getValue");
				var mchId = jQuery('#hdnMachineId').val();				
				var date = jQuery('#dtedate').datebox('getValue');				
				var shift = jQuery('#cmbShiftidSelected').combobox("getValue");				
				//var sectId = jQuery('#section').val();
			
				if (cellId == "") {
					alert("Select Cell");		return ;
				}
				if (date == "") {
					alert("Select Date");		return ;
				}
				if (shift == "") {
					alert("Select Shift");		return ;
				}
				if (mchId == "" || mchId == " "|| mchId == null) {
					return ;
				}
               /*				
				dataStr+= "q=2&cellId="+cellId;
				dataStr+= "&date="+date;
				dataStr+= "&shift="+shift;
				dataStr+= "&mchId="+mchId;				 

				if(prdId != null && prdId != ' ' && prdId != '')
					dataStr+= "&prdId="+prdId;
				
				//var factId = jQuery('#factory').val();
				var sectId = jQuery("#frmPcsView input[id='section']").val();
				var factId = jQuery("#frmPcsView input[id='factory']").val();
				//alert("dataStr"+dataStr);
				dataStr+= "&sectId="+sectId+"&factId="+factId;
				//alert(dataStr);
				LoadPopUp("loadPCSPopUp", "pcsPopEntry_input.pcs?"+dataStr, true,"95%","90%","10px","10px", "showPop_successCallBack");
              */
				fnCheckIsOpenMsr();
				
			}
			else
			{				 
				//alert(cellidx);	alert(cellvalue);								
				var lossParentId = jQuery("#productionGrid").jqGrid('getCell',iRow,13);				
				var lossId = jQuery("#productionGrid").jqGrid('getCell',iRow,0);
				
				if (lossParentId == lossId) {
					lossParentId = "";
					var LNO = jQuery("#productionGrid").jqGrid('getCell',iRow,10);
					//alert(LNO);
					if (LNO =="" || LNO ==" ") 	return;
				}
				var Pldetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
				var prdId = jQuery("#productionGrid").jqGrid('getCell',3,iCol);
				if (prdId == " " ||  prdId == "" ||  Pldetailsid == "" ||  Pldetailsid == " " ) {				
					//return;
					var mchId = jQuery('#hdnMachineId').val();
					var date = jQuery('#dtedate').datebox('getValue');
					var shift = jQuery('#cmbShiftidSelected').combobox("getValue");
					processAjaxCalls('checkIsOpenMsr.pcs','?q=2&date='+date+'&shift='+shift+'&mchId='+mchId,"openMsrSuccForAutoSave");	
				}
				else {								
					fnLoadPcsEntryScreen(iRow,iCol,Pldetailsid,prdId );
				}
			}													
		}		
	}
	
	function fnLoadPcsEntryScreen(iRow, iCol,Pldetailsid,prdId  ) {		
		var lossParentId = jQuery("#productionGrid").jqGrid('getCell',iRow,13);				
		var lossId = jQuery("#productionGrid").jqGrid('getCell',iRow,0);
		var date=jQuery('#dtedate').datebox('getValue');				
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery("#productionGrid").jqGrid('getCell',1,iCol);
		//var Pldetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
		//var prdId = jQuery("#productionGrid").jqGrid('getCell',3,iCol);
		var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Pldetailsid="+Pldetailsid+"&lossParentId="+lossParentId+"&lossId="+lossId;		
		var isQtyLoss;
		if (jQuery("#productionGrid").jqGrid('getCell',iRow,12) == "Nos")					
			isQtyLoss = "Y";
		else
			isQtyLoss = "N";

		var totalProduced = jQuery("#productionGrid").jqGrid('getCell',17,iCol);		
		dataString+= "&isQtyLoss="+isQtyLoss+"&totalProduced="+totalProduced;
		
		LoadPopUp("loadLossPopUp", "pcsLossEntry_input.pcs"+dataString, true,"98%","98%","13%","2px", "showLoss_successCallBack","PCS Loss Entry",true);
	}

	function openMsrSuccForAutoSave(result) {
		if (result.msrData.isOpenMsr == "1") 
			alert("Open MSRs are found for the shift.");
		else {
			var mchId = jQuery('#hdnMachineId').val();
			var date = jQuery('#dtedate').datebox('getValue');
			var shift = jQuery('#cmbShiftidSelected').combobox("getValue");
			var dataStr = '?q=2&mchId='+mchId+'&date='+date+'&shift='+shift;
			dataStr += "&saveMode=Save"+"&type=AUTO";
			//alert(dataStr);
			processAjaxCalls('noPlan_save.pcs',dataStr,"autoSaveSaveSuccess");		
		}
	}
	function autoSaveSaveSuccess(result) {
		var Pldetailsid=result.keyId;
		var prdId="-";
		var iRow = jQuery("#productionGrid").jqGrid('getGridParam', 'selrow');
		var iCol =jQuery('#hdnSelectCell').val();
		fnLoadPcsEntryScreen(iRow,iCol,Pldetailsid,prdId );
	}
	
	function ProductionGrid_loadComplete(result){
				
		//	jQuery( "#productionGrid" ).setGridHeight('60%');		
		//jQuery('#productionGrid').setGridParam({cellEdit:true});

		var dateBox = jQuery(':input[id^="dteEqpParam_"]');				
		//jQuery("#productionGrid").jqGrid('sortableRows', {disabled: true});
		jQuery("#productionGrid").jqGrid('sortableRows', { items: '.jqgrow:not(.unsortable)'});
		jQuery(dateBox).each(function () {
			formatDateBox(this.id,'DD-MMM-YYYY');
		});	

		var combo =jQuery(':input[id^="cmbo_"]');

		jQuery(combo).each(function () {			
			initialiseComboBox(this.id);
		});
		
		var rowIds = jQuery("#productionGrid").getDataIDs();
		var countCols = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames').length;
		var colIds  = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames');
		
		
		//alert(rowIds.length);
		for(var i = 0; i<rowIds.length; i++)
			{
				var issLossFlag = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],21);
				var hdnFlag = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],19);
				var colorId = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],18);
				var LNO = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],10);
				var PARAMETERS = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],11);
				var  uom = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],12);				
				if (i == 19) { 
					for (j=23;j<= countCols-1;j++) {
						//var prQty = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],j);						
						//alert('co11l='+colIds[j]+j);
						jQuery("#productionGrid").jqGrid('setCell',i+1,colIds[j]+j,'',{'color':'black','font-size': '11','font-weight' : 'bold'} );
					}
				}
			
				if(PARAMETERS.indexOf('No Plan')>=0)
				{
					for (j=23;j<= countCols-1;j++) {
					if(colIds[j] != 'TOTAL')
					{
						var noPlanMins = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],j);
						if(noPlanMins.indexOf('480')>=0)
						{
							var flag = 0;
							while(flag<rowIds.length)
							{
								jQuery("#productionGrid").jqGrid('setCell',flag+1,colIds[j]+j,'',{'background-color':'#b7b7b7'});
								flag++;
							}
						}	
					}
					}
				}
				var dataType = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],15);
				var lossId = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],0);
				var lossParentId = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],13);				
				
				if (lossParentId == lossId) {

					fnShowHideSubLoss(i+1);
					
					jQuery("#productionGrid").jqGrid('setCell',i+1,"LNO10","",{'font-size': '11','font-weight' : 'bold'});
					jQuery("#productionGrid").jqGrid('setCell',i+1,"PARAMETERS11","",{'font-size': '11','font-weight' : 'bold'});
					jQuery("#productionGrid").jqGrid('setCell',i+1,"UOM12","",{'font-size': '11','font-weight' : 'bold'});
				}
					
				
				//ideJqGridRow("productionGrid",parseInt(i+1));
				//alert(jQuery("#"+(i+1)).val());
				/*if(issLossFlag== 'S' || hdnFlag== 'Y') {					
					jQuery("#"+(i+1)).hide();					
				}*/
				hideJqGridRow('productionGrid', '1');
				hideJqGridRow('productionGrid', '2');
				hideJqGridRow('productionGrid', '3');
				hideJqGridRow('productionGrid', '4');
				
				if(hdnFlag== 'Y') {					
					//if(issLossFlag != 'S')
					//jQuery("#productionGrid tr[id="+(i+1) +"]").hide();	
				}
			
				if(colorId >  0) {				
					colorId = parseInt(colorId).toString(16);
					//alert(colorId);		
					jQuery("#productionGrid").jqGrid('setCell',i+1,"LNO10","",{'background-color':'#'+colorId});	
					jQuery("#productionGrid").jqGrid('setCell',i+1,"PARAMETERS11","",{'background-color':'#'+colorId});	
					jQuery("#productionGrid").jqGrid('setCell',i+1,"UOM12","",{'background-color':'#'+colorId});	

					if (i ==  16 ||i ==  14 ||i == 12 || i == 11) 							 
						for (j=23;j<= countCols-1;j++) { 
							jQuery("#productionGrid").jqGrid('setCell',i+1,colIds[j]+j,"",{'background-color':'#'+colorId});				
						}					
				}

				if(dataType== 'I')
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff'});
				if(dataType== 'N'){
					//jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"txtmplkTempfield1","",{'background-color':'red'});
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff'});
					 // jQuery("#productionGrid").jqGrid('setRowData',rowIds[i],{txtmplkTempfield1:description});
				}
				if(dataType== 'S') {
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff'});
				}

				if (i ==  7)
					for (j=23;j<= countCols-1;j++)  {
						jQuery("#productionGrid").jqGrid('setCell',i,colIds[j]+j,"",{'font-size': '11','font-weight' : 'bold','color':'red'});
					}
				
			}

		jQuery("#productionGrid").setGridParam({

			/*beforeShowSearch:function(form){
			    form.keydown(function(e) {
				    alert(e.which);
		    	});
			},*/			
			
			onCellSelect:function(id,cellidx,cellvalue) {  
			//alert("cell select1ttt"+id);
				jQuery('#hdnMachineId').val(jQuery("#productionGrid").jqGrid('getCell',1,cellidx));
				jQuery('#hdnSelectCell').val(cellidx);
				var formLock = jQuery('#hdnFormLock').val();								
				if(cellidx == 11 && formLock!="lock")					 
					fnShowHideSubLoss(id);
			}
		});	

		var formLock = jQuery('#hdnFormLock').val();
		if (formLock=="lock") {
			disableForm("frmPcsView");
			disableField("cmbShiftidSelected");
			//disableField('frmPcsView','btnfrmPcsViewmainFunLoc');
					
		}
		
	}

	function fnShowHideSubLoss(id) {

		var toHide = jQuery("#productionGrid").jqGrid('getCell',id,21);
		
		if(toHide == 'M')
		{
			var i = parseInt(id)+parseInt(1);
									
			while( jQuery("#productionGrid").jqGrid('getCell',i,21) == 'S')
			{
				//alert(jQuery("#productionGrid tr[id="+i +"]").is(":visible"));
				//if (jQuery("#"+i).css('display') == 'none')
				if (!jQuery("#productionGrid tr[id="+i +"]").is(":visible"))  
				{
					//alert(jQuery("#productionGrid").jqGrid('getCell',id,11));
					jQuery("#productionGrid").jqGrid('setCell',id,"PARAMETERS11",jQuery("#productionGrid").jqGrid('getCell',id,11).replace('(+)','(-)'));
					jQuery("#productionGrid tr[id="+i +"]").show();
				}
				else
				{
					//alert('Hide '+jQuery("#productionGrid").jqGrid('getCell',id,11));
					jQuery("#productionGrid").jqGrid('setCell',id,"PARAMETERS11",jQuery("#productionGrid").jqGrid('getCell',id,11).replace('(-)','(+)'));
					jQuery("#productionGrid tr[id="+i +"]").hide();
					//jQuery("#productionGrid tr[id="+i +"]").css("display","none");
				}							
				i++;
			}
		}
	}

	function productiondblClick(id)
	{	
		var formLock = jQuery('#hdnFormLock').val();
		if(formLock=="lock")	return;
			
		var dataStr = "";
		var cellId = jQuery('#cmbCellidSelected').combobox("getValue");
		var mchId = jQuery('#hdnMachineId').val(); 
		var prdId = "";		
		var date = jQuery('#dtedate').datebox('getValue');
		var shift = jQuery('#cmbShiftidSelected').combobox("getValue");
		//var sectId = jQuery('#section').val();
	
		if (cellId == "") {
			alert("Select Cell");		return ;
		}
		if (date == "") {
			alert("Select Date");		return ;
		}
		if (shift == "") {
			alert("Select Shift");		return ;
		}
		if (mchId == "" || mchId == " "|| mchId == null) {
			return ;
		}
		
		fnCheckIsOpenMsr();	
	
	}

	function fnCheckIsOpenMsr() {
		var mchId = jQuery('#hdnMachineId').val();
        var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		
		processAjaxCalls('checkIsOpenMsr.pcs','?q=2&date='+date+'&shift='+shift+'&mchId='+mchId,"CheckIsOpenMsrSuccess");
			
	}

	function CheckIsOpenMsrSuccess(result) {
		
		if (result.msrData.isOpenMsr == "1") {
			var msg ="Open MSRs are found for the shift. Would you like to continue PCS entry?";
			if(confirm(msg) == false)				
				return;
		}
		
		var dataStr = "";
		var cellId = jQuery('#cmbCellidSelected').combobox("getValue");
		var mchId = jQuery('#hdnMachineId').val();				
		var date = jQuery('#dtedate').datebox('getValue');				
		var shift = jQuery('#cmbShiftidSelected').combobox("getValue");				
				
		dataStr+= "q=2&cellId="+cellId;
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;
		dataStr+= "&mchId="+mchId;
		//if(prdId != null && prdId != ' ' && prdId != '')
		//	dataStr+= "&prdId="+prdId;		
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var factId = jQuery("#frmPcsView input[id='factory']").val();

		dataStr+= "&sectId="+sectId+"&factId="+factId;
		var url = jQuery("#hiddenUrl").val();
		
		if(url.indexOf('Test') >=0)
		{
			
			jQuery("#divLossEntryNew").slideDown("slow");//jQuery('#divLossEntryNew').show();	
			jQuery( "#pcsEntryLayericon" ).show();
			jQuery("#productionGrid").setGridWidth(500);
			jQuery("#pcsFunLocnDivLayer").css('width','100%');
			//LoadPopUp("loadPCSPopUp", "pcsPopTestEntry_input.pcs?"+dataStr, true,"94%","80%","1px","1px", "showPop_successCallBack","PCS Entry",false);
			//jQuery('#loadPCSPopUp').css('margin-top','6%');
		}
		else
		{
			// jQuery( dlgId ).show();				
			jQuery("#divLossEntryNew").slideDown("slow");//jQuery('#divLossEntryNew').show();
			jQuery( "#pcsEntryLayericon" ).show();	
			jQuery("#productionGrid").setGridWidth(500);
			jQuery("#pcsFunLocnDivLayer").css('width','100%');
			//LoadPopUp("loadPCSPopUp", "pcsPopEntry_input.pcs?"+dataStr, true,"97%","96%","1px","1px", "showPop_successCallBack","PCS Entry",true);
		}
	}
	
	jQuery( "#imgClosePcsEntry" ).click(function() {
		 jQuery( '#divLossEntryNew' ).hide();
		 jQuery( "#productionGrid" ).setGridWidth(800);
	});
	jQuery( "#hideEntry" ).click(function() {
		jQuery("#divLossEntryNew").slideUp("fast");//jQuery( '#divLossEntryNew' ).hide();
		jQuery( "#productionGrid" ).setGridWidth(1000);
	});
	jQuery( "#showEntry" ).click(function() {
		jQuery("#divLossEntryNew").slideDown("slow");	
		jQuery("#productionGrid").setGridWidth(500);
	});
	function showLoss_successCallBack(result) {
		
	}	
	function productionGridError(result)
	{
		//alert(result);
		alert("error");
	}
	
	function pcs_Formatter(cellval, options, rowObject)
	{	
		var colFlag = rowObject[3];
		
		if(rowObject[15] == 'I')
		{		
			//return '<span  style="background-color:#c0ffc0;" class="cellWithoutBackground">' + cellval + '</span>';
			return cellval;
		}
		else if( rowObject[15] == 'F'){
		
				 return colFlag;	
		}	
		else if( rowObject[15] == 'S'){
			
			var displayNames = rowObject[5];
			var comboDisp = displayNames.split(",");
			var codeVal =  rowObject[6];
			var comboVal = codeVal.split(",");
			var comboBox = "<select id='combo_"+rowObject[0].replace("/","") +"'  style='width:155px;' name='combo_"+rowObject[0].replace("/","") +"' class='easyui-combobox' value='Y'>";
			for( var i =0; i<comboDisp.length; i++ ){
				
				if(rowObject[3]== comboVal[i])
					comboBox += "<option d='selected' value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
				else
					comboBox += "<option  value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
			} 
			comboBox += "</select>";		
			return comboBox;
			
		}else if( rowObject[4] == 'ListDate'){
			return '<input id="dteEqpParam_'+options.rowId+'" width="70px"  class="easyui-datebox" />';
			
		}else if( rowObject[4] == 'ListSelection'){
			return cellval;
		}		
	}	 	

	function frmPcsViewcmbCellidSelected_onLoadSuccess() 	{		
		fillComboBox("frmPcsView","cmbShiftidSelected","shift.commonFilter?q=2&frmRfilter=yes" );		
	}

	function clearGrid() {
		var dataString = "?q=2&factId=&sectId=&shiftId=&entryDate=&cellId=";				
		processGridnew('productionGrid_view.pcs',dataString,"productionGrid","productionPager","PCS Entry","productiondblClick","","ProductionGrid_loadComplete","productionGridError");		
	}
	
	function dtedate_onChange(date) 	{		
		//clearGrid();
		jQuery("#productionGrid").jqGrid("clearGridData", true);
	}

	function dtedate_onSelect(date) 	{		
		//clearGrid();
		jQuery("#productionGrid").jqGrid("clearGridData", true);
	}
	
	function frmPcsViewcmbShiftidSelected_onSelect(record) 	{
		//clearGrid();
		jQuery("#productionGrid").jqGrid("clearGridData", true);
	}	

	
	function frmPcsViewcmbShiftidSelected_onLoadSuccess() 	{		
		/*readOnlyFields('cmbCellidSelected');
		readOnlyFields('dtedate');
		readOnlyFields('cmbShiftidSelected');
		*/
	}

	function frmPcsViewcmbMachineSelected_onSelect(record) 	{		
		clearGrid();
		loadFunctionalLocation("pcsefunLocation","functionalLoc_Entry.pcs","pcsefunLocationnValues","frmPcsView","&machId="+record.id);
	}
	
	function frmPcsViewcmbCellidSelected_onSelect(record) 	{
		jQuery('#cmbMachineSelected').combobox('clear');		
		clearGrid();
		loadFunctionalLocation("pcsefunLocation","functionalLoc_Entry.pcs","pcsefunLocationnValues","frmPcsView","&cellId="+record.id);
		fnGetTotalResultArray();
	}
	
	function frmPcsView_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		jQuery('#cmbShiftid').combobox('clear');
		var factId = jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();		
		reloadCombo("frmPcsView","cmbCellidSelected","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId+"&sectId="+sectId );
			  
		reloadCombo("frmPcsView","cmbShiftidSelected","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");

		var cellId = jQuery("#frmPcsView input[id='cell']").val();		
		var dataStr = "?q=2&pcsEnabled=Y&factId="+factId+"&cellId="+cellId;		
		reloadCombo("frmPcsView","cmbMachineSelected","machineCombo.commonFilter"+dataStr  );
		 if(screen.width >= 1300)		
		 {
			jQuery('#dispFunctionalLoc').css('width','84%');
		 }
		 else
			jQuery('#dispFunctionalLoc').css('width','102%');
		if (keyIds.cellId!="null")
			setFieldValue('cmbCellidSelected',keyIds.cellId);
		if (keyIds.machId!="null")
		setFieldValue('cmbMachineSelected',keyIds.machId);


		
	}
/*	function loadPCSPopUp_onClose() {
		alert("close");
		fnView();
		return true;
	}
	
	function loadLossPopUp_onClose() {
		alert(jQuery('#loadPCSPopUp:visible').is(':visible'));
		if(! jQuery('#loadPCSPopUp:visible').is(':visible') ) 
			fnView();
		return true;
	}
*/
	jQuery('#btnRemarks').click(function() {

		var iCol = jQuery('#hdnSelectCell').val();				
		var Pldetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
		var	rowCnt = jQuery("#productionGrid").getGridParam("reccount");
		var remarks =jQuery("#productionGrid").jqGrid('getCell',rowCnt,iCol);
		//alert(remarks);
		jQuery('#txaPcsRemarks').val(remarks);
		if (Pldetailsid == "" || Pldetailsid == " ") {			
			alert("To Entry the Remarks, Save the PCS Entry for Machine");			
			return;
		}		
		openRemarksDialog();
		/*jQuery( "#divRemarks" ).show();
		jQuery( "#divRemarks" ).dialog({
			autoOpen: false,
			modal: true,
			height: 250,
			width: 380		
		});*/				
});

	jQuery('#btnOk').click(function() {			
		var iCol = jQuery('#hdnSelectCell').val();				
		var plDetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
		var remarks = jQuery('#txaPcsRemarks').val();
		var detailTable = jQuery('#hdnDetailTable').val();
	//	alert(remarks);
		processAjaxCalls('updatePldRemarks.pcs','?q=2&detailTable='+detailTable+'&plDetailsid='+plDetailsid+'&remarks='+remarks,"updtRemarkSuccess");				
	});

	function updtRemarkSuccess(result) {
		//alert(result.updtFlg);
		if (result.updtFlg == "1" ) {			
			jQuery('#txaPcsRemarks').val("");
			//jQuery("#divRemarks").dialog("close");
			closeRemarksDialog('divRemarks');
			refreshForm();
		}
	}

	function refresh_SuccessCalBack() {
		fnView();
	}
		
	/*jQuery('#btnCancel').click(function() {
		//alert("cancel");		
		jQuery('#txaPcsRemarks').val("");
		jQuery("#divRemarks").dialog("close");
	});*/
		
</script>

<form id="frmPcsView" >
<input type="hidden" id="hdnSelectCell" name="hdnSelectCell"/>
<input type="hidden" id="hdnPlmMasterId" name="hdnPlmMasterId"/>
<input type="hidden" id="hdnDetailTable" name="hdnDetailTable"/>
<input type="hidden" id="hdnMachineId" name="hdnMachineId"/>
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />
<input type="hidden" id="hdnFormLock" name="hdnFormLock" value="${requestScope.formLock}" />

<!--<div class="easyui-paddingbfpx" style="height: 350px;" >-->
<div style="width:90%;margin-left:5%; ">
	<table style="width:90%;">
	<tr>
	<td>
		<div  id="pcsFunLocnDivLayer" class="easyui-paddingbfpx" style="width:122%">
			<div  class="easyui-paddingbfpx" id="frmPcsViewFuntKeyIds">
				<input type="hidden" id="factory" name="cmbPrlmFactoryid" value="${requestScope.factId}"  ></input>
				<input type="hidden" id="section" name="cmbPrlmSectionid" value="${requestScope.sectId}"  ></input>
				<input type="hidden" id="cell" name="cmbPrlmCellid" value="${requestScope.cellId}" ></input>
				<input type="hidden" id="machine" name="cmbPrlmMachineid" value="${requestScope.mchId}"  ></input>
			</div>
			<div id="pcsefunLocation"></div>
		</div>	
	</td >
	<td style="padding-left:10%;">
		<div style="float:right;padding-left:10%;" id="pcsEntryLayericon">
			<img src="images/woshow.png" id="showEntry" />
		</div>				
	</td>
	</tr>
	<tr>
	<td>	
	<div>
		<label>Line</label>
		<label style="padding-left: 262px;" >Equipment</label>
		<label style="padding-left: 225px;" >Date</label>
		<label style="padding-left: 77px;" >Shift</label>
	</div>
    <div  style="width:120%;" >
		<input type="text" id="cmbCellidSelected" name="cmbCellidSelected" class="easyui-combobox" value="${requestScope.cellId}" style="width: 275px;"/>						
		<span style="padding-left: 1%;">
	    	<input type="text" id="cmbMachineSelected" name="cmbMachineSelected" class="easyui-combobox" value="${requestScope.mchId}" style="width: 275px;"/>
	    </span>	    

		<span style="padding-left: 1%;">
	    	<input id="dtedate" name="dtedate" class="easyui-text" readonly="readonly" required="true" style="width: 90px;" value="${requestScope.date}"/>&nbsp;
	    </span>	    
    	<span style="padding-left: 1%;">
	    	<input type="text" class="easyui-combobox" id="cmbShiftidSelected" name="cmbShiftidSelected" readonly="readonly"  style="width: 85px;"  value="${requestScope.shift}"/>
<!--	    	<input type="button" id="btnViewPCS" name="btnViewPCS" class="easyui-button"  value="View" style=""/>-->
	    </span>
	   	<span style="padding-left: 1%;">
	    	<input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style=""/>
	    </span>
	    	    
	    <span style="padding-left: 1%;">
	    	<input type="button" id="btnResult" name="btnResult" class="easyui-button"  value="Result" style=""/>
	    </span>
	    <span style="padding-left: 1%;">
	    	<input type="button" id="btnRemarks" name="btnRemarks" class="easyui-button"  value="Remarks" style=""/>
	    </span>
	    <span id="err_cmbMachineid" class="tpm-errormsg"> </span>	    
    </div>   
	</td>
	</tr>
	</table>	
<!--	<label style="padding-left: 25px" class="mandatory-lbl" > Double Click the Cell to Enter Details </label>-->
<!--	<label class="mandatory-lbl" style="padding-left: 600px;"> Press F8 to Loss Entry </label>-->

	<div>
<!--		<label style="background-color':'#ffc0ff';"> 	</label> <br>-->
		<input type="text" value="Header" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />		
		<span style="padding-left: 10px">
			<input type="text" value="Parent Loss" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:center; " />
		</span>
		
		<span style="padding-left: 10px">
			<input type="text" value="Double Click for Production Entry" disabled="disabled" style="border:1px solid black; font-size:10px ; width:250px;height:20px;color:black;font-weight:bold;text-align:center; " />
		</span>		
		<span style="padding-left: 10%">
			<input type="text" value="Press F8 on any loss to Loss Entry" disabled="disabled" style="border:1px solid black; font-size:10px ; width:260px;height:20px;color:black;font-weight:bold;text-align:center; " />
		</span>		
		<span style="padding-left: 5%">
			<input type="text" value="No Plan" disabled="disabled" style="border:1px solid black; font-size:10px ; width:70px;height:20px;color:black;font-weight:bold;text-align:center;background-color:#b7b7b7; " />
		</span>		
	</div>
	<div>
		<div style="float:left;">			
			<table id="productionGrid" style="float: left: ;"></table>			
		</div>
		<span style="">
		
	</span>
	</div>		
</div>	
<div title="Loss Entry" id="divLossEntryNew" class="flPopUpBox" style="width:50%;margin-left:15%;height:97%;overflow:auto;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;">
<div style="">
			<div class="sub-header" style="margin-top:0px;">
				<img src="images/panel_tool_collapse.gif" id="hideEntry" />
				PCS Entry
<!--				<img id="imgClosePcsEntry" src="images/cancel.png" style="float:right;"/>-->
			</div>
			<div style="padding-left:8%">
				<label>ROA</label>
				<span>
					<input type="text" value="" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:10px;">ROP</label>
				<span>
					<input type="text" value="" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:10px;">ROQ</label>
				<span>
					<input type="text" value="" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:10px;">OEE</label>
				<span>
					<input type="text" value="" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
			</div>
			<hr style="color:#A7C9E8;">
			<div style="padding-left:8%">
				<label class="mandatory-lbl">Product Model </label>	
				<label class="mandatory-lbl" style="padding-left:177">Product </label>			
			</div>
			<div style="padding-left:8%;" class="easyui-paddingbfpx">
				   <span>
						<input type="text" id="cmbproductmodel" name="cmbproductmodel" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 253px;"/>
				  </span>		
				   <span style="padding-left:1%;">
		    			<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 253px;"/>
		   		  </span>		 
			</div>
			
			<div style="padding-left:8%;">
				<label class="mandatory-lbl">Wo No. </label>
				<label class="mandatory-lbl" style="padding-left:86">Cycle Time </label>
				<label class="mandatory-lbl" style="padding-left:67">Actual Time </label>
			</div>
			<div class="easyui-paddingbfpx" style="padding-left:8%;">
			 	<span>
					<input type="text" id="txtwno" name="txtwno" class="easyui-text" value="" style="width: 122px;"/>
				</span>
				 <span style="padding-left:1%;">
					<input type="text" id="cmbCycletimeCavity" name="cmbCycletimeCavity" class="easyui-combobox" value="" style="width: 122px;text-align:right;"/>
					<input type="hidden" id="txtTheoriticalcycletime" name="txtTheoriticalcycletime" class="easyui-combobox" value="" style="width: 100px;"/>
				</span>
				 <span style="padding-left:1%;">
					<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 122px;text-align:right;"/>
				</span>
			</div>
			
			<div style="padding-left:8%;">
				<label class="mandatory-lbl" style="padding-left:0">Prod. Qty </label>
				<label class="mandatory-lbl" style="padding-left:76">Rej Qty </label>
				<label class="mandatory-lbl" style="padding-left:86">Trim Qty</label>
				<label class="mandatory-lbl" style="padding-left:81">Exp. Qty</label>
			</div>
			<div class="easyui-paddingbfpx" style="padding-left:8%;">
				<span>
			    	<input type="text" class="easyui-text" title="Press F2 or Enter to Save" id="txtproducedqty" name="txtproducedqty" style="width: 122px;text-align:right;"  value=""/>
			    </span>
			    <span style="padding-left:1%;">
			    	<input type="text" class="easyui-text" id="txtRejQty" name="txtRejQty" style="width: 122px;text-align:right;"  value=""/>
			    </span>
			    <span style="padding-left:1%;">
					<input type="text" class="easyui-text" id="txttrimmingqty" name="txttrimmingqty" style="width: 122px;text-align:right;"  value=""/>	
				</span>	    
				<span style="padding-left:1%;">						
					<input type="text" class="easyui-text" id="txtexpansionqty" name="txtexpansionqty" style="width: 122px;text-align:right;"  value=""/>			
				</span>	
			</div>
			
			<div style="padding-left:35%;">				
				<span>
					<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Save" style="width: 50px;height:20px;">
					<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">
				</span>    
			</div>
			
			<div class="sub-header">Loss Entry</div>
			<div style="padding-left:8%">
				<label class="mandatory-lbl">Loss </label>
				<label class="mandatory-lbl" style="padding-left:230">Reason </label>
			</div>
			<div style="padding-left:8%;" class="easyui-paddingbfpx">
				   <span>
					 <input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" onKeyPress="return checkIt(event)" class="easyui-combobox" value="" style="width: 253px;"/>
				  </span>
				   <span style="padding-left:1%;">
		    		<input type="text" id="cmbPlrkReasonid" name="cmbPlrkReasonid" class="easyui-combobox" value="" style="width: 253px;"/>
		   		 </span>
			</div>
			<div style="padding-left:8%">
				<label class="mandatory-lbl">Cause </label>
				<label class="mandatory-lbl" style="padding-left:222">Rootcause </label>
			</div>
			<div style="padding-left:8%;" class="easyui-paddingbfpx">
				  <span>
					  <input type="text" id="cmbPlrkCauseid" name="cmbPlrkCauseid" onKeyPress="return checkIt(event)" class="easyui-combobox" value="" style="width: 253px;"/>
				  </span>
				   <span style="padding-left:1%;">
		    		<input type="text" id="cmbPlrkRootcauseid" name="cmbPlrkRootcauseid" class="easyui-combobox" value="" style="width: 253px;"/>
		   		 </span>
			</div>
			<div style="padding-left:8%">
				<label class="mandatory-lbl">Instance </label>
				<label class="mandatory-lbl">Time/Qty </label>
				<label class="mandatory-lbl" style="padding-left:58">Remarks </label>				
			</div>
			<div style="padding-left:8%;" class="easyui-paddingbfpx">
				   <span style="vertical-align:43px;">
						<input type="text" class="easyui-text" id="txtPlrkInstance" name="txtPlrkInstance" style="width: 122px;text-align:right;"  value=""/>
				  </span>
				   <span style="padding-left:1%;vertical-align:43px;">
						<input type="text" class="easyui-text" id="txtPlrkMinutes" name="txtPlrkMinutes" style="width: 122px;text-align:right;"  value=""/>	
				   </span>	    
				   <span style="padding-left:1%;">	
		    			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" rows="2" style="width:253px;resize:none;"  id="txaPlrkRemarks" name="txaPlrkRemarks"></textarea>
		   		 </span>
		   		  <span style="padding-left: 5px;vertical-align:8px;">	    
		 			<input type="button" id="btnLossInsert" name="btnLossInsert" class="easyui-button" value="Save" style="width: 50px;height:20px;">
		 		</span> 		
		 		<span style="padding-left: 5px;vertical-align:8px;">
		 			<input type="button" id="btnLossClear" name="btnLossClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">
		 		</span>
			</div>
			
		   	
		</div>
</div>
	<div title="Remarks" id="divRemarks" class="flPopUpBox" style="margin-top:5%;display:none;width:30%;height:50%;">
	<div id="titleRemarks" class="sub-header" style="width:104%;margin-left:-11px;margin-bottom:15px;height:19px;margin-top:-10px;">
		<label id="lblRemarks" style="margin-left:1px;font-size:11px;">Remarks</label> 
		<img id="imgRemarks" src="images/close-butt1.png" style="float:right;"/>
	</div>
	 	<div style="float:left;padding:px;margin-left: px;">
			<label class="mandatory-lbl">Remarks</label>
			<span style="padding-left: 2%;">	
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txaPcsRemarks" name="txaPcsRemarks" style="width: 335px;height: 105px" rows="5" cols="1"></textarea>
			</span>
			<br><br>
			<div class="easyui-paddingbfpx"> 	    
			    <span style="padding-left: 25%;">
			    	<input type="button" id="btnOk" name="btnOk" class="easyui-button"  value="Ok" style="width:75px;"/>
			    </span>
			    <span style="padding-left: 2%;">
			    	<input type="button" id="btnCancel" name="btnCancel" class="easyui-button"  value="Cancel" style="width:75px;"/>
			    </span>
			</div>
		</div>	
	</div>
<input type="hidden" id="hdnFactId" value="${requestScope.factId}"/>
<input type="hidden" id="hdnDataStr" value=""/>
</form>
