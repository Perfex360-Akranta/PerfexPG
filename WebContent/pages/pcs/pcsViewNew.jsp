<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	var glbIsHalol=false;
	var glbPldetailIdChanged="";
	
	jQuery(document).ready(function(){
		initialiseForm('frmPcsView');
		jQuery('#submitForm').val('frmPcsView');
		jQuery( "#divLossEntryNew" ).hide();
		jQuery("#pcsEntryLayer").hide();		
		//jQuery('#hdnFormLock').val("lock");
		var auto = jQuery('#hdnFormAuto').val();
		if(auto == 'true') {
			jQuery('#divMain').hide();
		}
		jQuery("#spnCavity").hide();
		jQuery("#spnLblCavity").hide();
	
		fillComboBox("frmPcsView","cmbShiftidSelected","shift.commonFilter?&frmRfilter=yes");
		

		var mainflid = jQuery("#frmEmpPage input[id='flid']").val();
		jQuery("#frmEmpPage input[id='flid']").val(mainflid);
		
		fillComboBox("frmPcsView","cmbCellidSelected","cellCombo.commonFilter" );
		var factId ="";// jQuery("#frmPcsView input[id='factory']").val();
		fillComboBox("frmPcsView","cmbMachineSelected","machineCombo.commonFilter?&pcsEnabled=Y&factId="+factId  );
		formatDateBox('dtedate','dd-MMM-yyyy');
		fillComboBox("frmPcsView","cmbrawmaterialtype","combo_rawtype.pcs" );
		fillComboBox("frmPcsView","cmbGrade","comboGradeno.commonFilter" );
		fillComboBox("frmPcsView","cmbGradeSpec","comboGrade.commonFilter" );

		jQuery('#spnlblMachineid').hide();
		jQuery('#spnMachineid').hide();
		
		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");
		fillWithCurrentDate('dtedate');
		//numericTextBox('txtProducedqty');
		//numericTextBox('txtTime');
		//numericTextBox('txtNoplan');

	   var factId = "";//jQuery("#frmPcsView input[id='factory']").val();	   
	   var sectionId = jQuery("#frmPcsView input[id='section']").val();
	   var cellId = jQuery("#frmPcsView input[id='cell']").val();
	   var machId = jQuery("#frmPcsView input[id='machine']").val();
	   var flid = jQuery("#frmPcsView input[id='flid']").val();
	   							
	   var dataStr = "&cmbPrlmFactoryid="+factId+"&cmbPrlmSectionid="+sectionId+"&cmbPrlmCellid="+cellId+"&cmbPrlmMachineid="+machId+"&flid="+flid;;	  					
	   if (flid != null)
	   	   loadFunctionalLocation("pcsefunLocation","functionalLoc_Calendar.pcs","pcsefunLocationnValues","frmPcsView","&flid="+flid);
	   else
		   loadFunctionalLocation("pcsefunLocation","functionalLoc_Calendar.pcs","pcsefunLocationValues","frmPcsView",dataStr);

	   
	   /* setTimeout(function() {fnGetTotalResultArray();},550); */	   
	   
		var cellId = jQuery("#frmPcsView input[id='cell']").val();		
		var dataStr = "?&pcsEnabled=Y&factId="+factId+"&cellId="+cellId;		
		reloadCombo("frmPcsView","cmbMachineSelected","machineCombo.commonFilter"+dataStr  );
		
		if (keyIds.cellId!="null")
			setFieldValue('cmbCellidSelected',keyIds.cellId);
		if (keyIds.machId!="null")
			setFieldValue('cmbMachineSelected',keyIds.machId);
		
		
		   
		   setTimeout(function() {
			   var entryDate=jQuery('#dtedate').datebox('getValue');
			   var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
			   var cellId = jQuery("#frmPcsView input[id='cell']").val();
		       processAjaxCalls('getMasterKeyid.pcs','?&entryDate='+entryDate+'&shift='+shift+'&cellId='+cellId,"getMasterIdSuccess");
							
				//jQuery("#divRemarks").dialog("close");
				var mode = jQuery('#hdnMode').val();
				//alert(mode);
				processAjaxCalls('setFormActionMode.pcs','?&mode='+mode,"formModeSuccess");
		   },550);


		jQuery("#cmbPlrkLossid").combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});		
				

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
		
		jQuery("#txtproducedqty").change(function (){
			backLogQty();
		});
		jQuery("#txtrejectedqty").change(function (){
			backLogQty();
		});
		jQuery("#txtexpansionqty").change(function (){
			backLogQty();
		});
	});
	
	function setCurrentShiftId() { 
		var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var hours = currentTime.getHours();
		var minutes = currentTime.getMinutes();
		
		if (minutes < 10){
			minutes = "0" + minutes;
		}	
		
		var factId = "";//jQuery("#frmPcsCalendar input[id='factory']").val();
		var dataString = '?&factId='+factId+'&sectId='+jQuery("#frmPcsView input[id='section']").val();
		dataString += '&cellId='+jQuery("#frmPcsView input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
		setTimeout(function() {
			processAjaxCalls('txt_shift.brdn',dataString,'getPCSShift','getPCSShiftErr');
		},10);
		
	}
	
	function  getPCSShift(record)
	{
		//alert(record.shift);
		setFieldValue('cmbShiftidSelected',record.shift);
		
		setTimeout(function() {
			fnGetTotalResultArray();
			disableUIButton('btnView');
		},10);	   
		
	}
	
	function getDetailTableName() {
		var sectionId = jQuery("#frmPcsView input[id='section']").val();
		var entryDate=jQuery('#dtedate').datebox('getValue');
		processAjaxCalls('getDetailTableName.pcs','?&sectId='+sectionId+"&entryDate="+entryDate,"detailTableRecallSuccess");
		
	}
	
	function backLogQty()
	{
		var prodQty = jQuery("#txtproducedqty").val();
		var rejQty = jQuery("#txtrejectedqty").val();
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

	jQuery('#btnOperatordtls').click(function() {
		LoadPopUp("loadOpertorDetails", "pcsOperatordtlsNew_input.pcs?", true,"350px","300px","100px","200px", "showResult_successCallBack","Operator Details",true);
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
		processAjaxCalls('getMasterKeyid.pcs','?&entryDate='+entryDate+'&shift='+shift+'&cellId='+cellId,"getMasterIdSuccess");
		
      	var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		//alert(factId);
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();		
		var dataStr1="";
		dataStr1+= "&date="+date+"&shift="+shift+"&sectId="+sectId+"&cellId="+cellId+"&mchId=";
 	    processAjaxCalls('getTotalResult.pcs',dataStr1,"getTotalResultSuccess");

 	   setTimeout(function() { fnHideEntry(); },500);
	}
	
	function getTotalResultSuccess(result) {
		
	    var entryDate=jQuery('#dtedate').datebox('getValue');
        var date=jQuery('#dtedate').datebox('getValue');
		var shiftId=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		//var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var cellId = jQuery('#cmbCellidSelected').combobox('getValue');	
		var mchId = jQuery('#cmbMachineSelected').combobox('getValue');
				
		var dataString = "?&factId="+factId+"&sectId="+sectId+"&shiftId="+shiftId+"&entryDate="+entryDate+"&cellId="+cellId+"&mchId="+mchId;
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
			setTimeout(function() {
			 var dataString = jQuery("#hdnDataStr").val();
			processGridnew('productionGrid_view.pcs',dataString,"productionGrid","productionPager","PCS Entry","productiondblClick","","ProductionGrid_loadComplete","productionGridError"); 
			},400);
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
		
		/* if (jQuery('#hdnPlmMasterId').val()=="") {
			alert("To view the Results, Save the PCS Entry");
			return ;
		}
		 */	 
        var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery('#cmbMachineSelected').combobox("getValue");
		var dataStr="";		
		//alert('mchId'+mchId);
		if (mchId != null && mchId != '' && mchId != ' ' && mchId != 'undefined')
			mchId =mchId ;
		else {
			//alert("Select the Machine Column");
			//return ;
			mchId ="";			
		}
		
		
		dataStr+= "&date="+date+"&shift="+shift+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId;
	//	alert(dataStr);
		LoadPopUp("loadPCSResult", "pcsResult_input.pcs?&dataStr="+escape(dataStr), true,"700px","400px","10px","100px", "showResult_successCallBack","PCS Result",true);				
	}); 

	function showResult_successCallBack() {
		
	}
	

	function fnView() {  
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var dataString = "?&factId="+factId+"&sectId="+jQuery("#frmPcsView input[id='section']").val()+"&shiftId="+jQuery('#cmbShiftidSelected').combobox('getValue')+"&entryDate="+jQuery('#dtedate').datebox('getValue')+"&cellId="+jQuery('#cmbCellidSelected').combobox('getValue');
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
			
				/* if (cellId == "") {
					alert("Select Cell");		return ;
				} */
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
				dataStr+= "&cellId="+cellId;
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
					processAjaxCalls('checkIsOpenMsr.pcs','?&date='+date+'&shift='+shift+'&mchId='+mchId,"openMsrSuccForAutoSave");	
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
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery("#productionGrid").jqGrid('getCell',1,iCol);
		//var Pldetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
		//var prdId = jQuery("#productionGrid").jqGrid('getCell',3,iCol);
		var dataString = "?&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Pldetailsid="+Pldetailsid+"&lossParentId="+lossParentId+"&lossId="+lossId;		
		var isQtyLoss;
		if (jQuery("#productionGrid").jqGrid('getCell',iRow,12) == "Nos")					
			isQtyLoss = "Y";
		else
			isQtyLoss = "N";

		var totalProduced ;
		if (glbIsHalol==true)
			totalProduced = jQuery("#productionGrid").jqGrid('getCell',18,iCol);
		else
			totalProduced = jQuery("#productionGrid").jqGrid('getCell',17,iCol);
				
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
			var dataStr = '?&mchId='+mchId+'&date='+date+'&shift='+shift;
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

	function pcsLossEntryGrid_loadComplete(result) {		
		jQuery("#pcsLossEntryGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			enableUIButton('btnLossDelete');
		}
		});
		if (jQuery('#hdnIsDblClick').val()=='1') {
			jQuery('#hdnIsDblClick').val('0');
			jQuery('#divMain').show();
		}			
	} 
		
	function ProductionGrid_loadComplete(result){
		getDetailTableName();
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
						jQuery("#productionGrid").jqGrid('setCell',i+1,colIds[j]+j,'',{'color':'black','font-size': '11','font-weight' : 'bold', 'text- align':'right'} );
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
								jQuery("#productionGrid").jqGrid('setCell',flag+1,colIds[j]+j,'',{'background-color':'#b7b7b7','text- align':'right'});
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
							jQuery("#productionGrid").jqGrid('setCell',i+1,colIds[j]+j,"",{'text- align':'right', 'background-color':'#'+colorId});				
						}					
				}

				if(dataType== 'I')
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff', 'text- align':'right'});
				if(dataType== 'N'){
					//jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"txtmplkTempfield1","",{'background-color':'red'});
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff', 'text- align':'right'});
					 // jQuery("#productionGrid").jqGrid('setRowData',rowIds[i],{txtmplkTempfield1:description});
				}
				if(dataType== 'S') {
					jQuery("#productionGrid").jqGrid('setCell',rowIds[i],"Col1","",{'background-color':'#c0ffc0','color':'#ffffff', 'text- align':'right'});
				}

				if (i ==  7)
					for (j=23;j<= countCols-1;j++)  {
						jQuery("#productionGrid").jqGrid('setCell',i,colIds[j]+j,"",{'font-size': '11','font-weight' : 'bold','color':'red', 'text- align':'right'});
					}
					
			}

		jQuery("#productionGrid").setGridParam({

			/*beforeShowSearch:function(form){
			    form.keydown(function(e) {
				    alert(e.which);
		    	});
			},*/			
			
			onCellSelect:function(id,cellidx,cellvalue) {
				jQuery("#divMain").css("padding-left","1%");
				jQuery('#hdnMachineId').val(jQuery("#productionGrid").jqGrid('getCell',1,cellidx));
				jQuery('#hdnDetailIdTempfield').val(jQuery("#productionGrid").jqGrid('getCell',2,cellidx));
				glbPldetailIdChanged=jQuery("#productionGrid").jqGrid('getCell',2,cellidx);
				//alert(glbPldetailIdChanged);
				jQuery('#hdnProductIdTempfield').val(jQuery("#productionGrid").jqGrid('getCell',4,cellidx));
				if (glbIsHalol==true)
					jQuery('#hdnProdQtyTempField').val(jQuery("#productionGrid").jqGrid('getCell',18,cellidx));
				else
					jQuery('#hdnProdQtyTempField').val(jQuery("#productionGrid").jqGrid('getCell',17,cellidx));
				
				jQuery('#hdnSelectCell').val(cellidx);
				var formLock = jQuery('#hdnFormLock').val();	
				//alert(cellidx);							
				if(cellidx == 11 && formLock!="lock")					 
					fnShowHideSubLoss(id);

				fnHideEntry();
			}
		});	

		var formLock = jQuery('#hdnFormLock').val();
		if (formLock=="lock") {
			disableForm("frmPcsView");
			disableField("cmbShiftidSelected");
			//disableField('frmPcsView','btnfrmPcsViewmainFunLoc');
					
		}

		//jQuery("#productionGrid").jqGrid('setFrozenColumns');
		//for direct view 12-11-2013
		var auto = jQuery('#hdnFormAuto').val();
		//auto='Y';
		//alert(auto);
		
		if(auto == 'true') {
			var cellidx =23;
			jQuery('#hdnMachineId').val(jQuery("#productionGrid").jqGrid('getCell',1,cellidx));
			jQuery('#hdnDetailIdTempfield').val(jQuery("#productionGrid").jqGrid('getCell',2,cellidx));
			glbPldetailIdChanged=jQuery("#productionGrid").jqGrid('getCell',2,cellidx);
			//alert(glbPldetailIdChanged);
			jQuery('#hdnProductIdTempfield').val(jQuery("#productionGrid").jqGrid('getCell',4,cellidx));		
			jQuery('#hdnSelectCell').val(cellidx);			
			productiondblClick(10);
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
		jQuery('#hdnIsDblClick').val('1');
		jQuery('#divMain').hide();
		jQuery("#divMain").css("padding-left","0%");		
		//alert(id);
		//alert(jQuery('#hdnSelectCell').val());
		var formLock = jQuery('#hdnFormLock').val();
		
		if(formLock=="lock")	return;
			
		var dataStr = "";
		var cellId = jQuery('#cmbCellidSelected').combobox("getValue");
		var mchId = jQuery('#hdnMachineId').val(); 
		var prdId = "";		
		var date = jQuery('#dtedate').datebox('getValue');
		var shift = jQuery('#cmbShiftidSelected').combobox("getValue");
		//var sectId = jQuery('#section').val();
		var dtlId = jQuery('#hdnDetailIdTempfield').val();
		
		var toHide = jQuery("#productionGrid").jqGrid('getCell',id,21);
					
		if(toHide == 'M')
		{
			var lossId = jQuery("#productionGrid").jqGrid('getCell',id,7);			
			var i = parseInt(id)+parseInt(1);
			while( jQuery("#productionGrid").jqGrid('getCell',i,21) == 'S')
			{
				var lossVal = jQuery("#productionGrid").jqGrid('getCell',i,23);				
				if(lossVal != null && lossVal != '' && lossVal != ' ')
					lossId  = jQuery("#productionGrid").jqGrid('getCell',i,7);				
				i++;
			}			
			jQuery('#hdnLossIdTempField').val(lossId);			
		}
		else
			jQuery('#hdnLossIdTempField').val('');

		
		jQuery('#hdnPldetailsid').val("");
		if(dtlId != null && dtlId != '' && dtlId != ' ')
			jQuery('#hdnPldetailsid').val(dtlId);
					
		
		/* if (cellId == "") {
			alert("Select Cell");		return ;
		}
		 */
		 if (date == "") {
			alert("Select Date");		return ;
		}
		if (shift == "") {
			alert("Select Shift");		return ;
		}
		
		if (mchId == "" || mchId == " "|| mchId == null) {
			return ;
		}
	
		LoadEntryInView();//fnCheckIsOpenMsr();	
	
	}

	function fnCheckIsOpenMsr() {
		var mchId = jQuery('#hdnMachineId').val();
        var date=jQuery('#dtedate').datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');		
		show_winMask(1);
		processAjaxCalls('checkIsOpenMsr.pcs','?&date='+date+'&shift='+shift+'&mchId='+mchId,"CheckIsOpenMsrSuccess");
			
	}

	function CheckIsOpenMsrSuccess(result) {
		show_winMask(0);	
		if (result.msrData.isOpenMsr == "1") {
			var msg ="Open MSRs are found for the shift. Would you like to continue PCS entry?";
			if(confirm(msg) == false)				
				return;
		}		
		
	}	

	function LoadEntryInView()
	{		
		//alert('LoadEntryInView');
		var dataStr = "";
		var cellId = jQuery('#cmbCellidSelected').combobox("getValue");
		var mchId = jQuery('#hdnMachineId').val();				
		var date = jQuery('#dtedate').datebox('getValue');				
		var shift = jQuery('#cmbShiftidSelected').combobox("getValue");				
				
		dataStr+= "&cellId="+cellId;
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;
		dataStr+= "&mchId="+mchId;
		//if(prdId != null && prdId != ' ' && prdId != '')
		//	dataStr+= "&prdId="+prdId;		
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();

		dataStr+= "&sectId="+sectId+"&factId="+factId;
		var url = jQuery("#hiddenUrl").val();
		//alert(url);	
		if(url.indexOf('Test') >=0)
		{	
			jQuery("#pcsEntryLayer").show();
			jQuery("#showEntryLayer").hide();
			jQuery("#hideEntryLayer").show();
			//alert("i"+jQuery("#divLossEntryNew").css('display'));
				if(jQuery("#divLossEntryNew").css('display') == 'none')
				jQuery("#divLossEntryNew").toggle("slow");
			jQuery("#pcsViewFields").hide();
			jQuery( "#hideFunLocn" ).show();
			jQuery( "#showFunLocn" ).hide();
						
			jQuery("#pcsImageLayer").show();
			jQuery("#divLossEntryNew").animate({width:'575px',opacity:'0.8'},"slow");
			jQuery("#pcsViewLayer").css('width','260');
			jQuery("#pcsEntryLayer").css('width','480');
			/*jQuery("#lblEquipmentPcsView").css("padding-left","36%");
			jQuery("#lblDatePcsView").css("padding-left","28%");
			jQuery("#lblShiftPcsView").css("padding-left","7%");*/
			jQuery("#productionGrid").setGridWidth(580);
			var flag = jQuery('#hdnEntryShowCount').val();
			if(flag != 'N')
				jQuery('#hdnEntryShowCount').val('Y');
			fnClear('Y');
			fnLossClear();
			loadPcsEntryFields();   			
		}
		else
		{	
			jQuery("#pcsEntryLayer").show();		
			jQuery("#showEntryLayer").hide();
			jQuery("#hideEntryLayer").show();
			jQuery("#divLossEntryNew").toggle("slow");
			jQuery("#pcsViewFields").hide();
			jQuery("#pcsImageLayer").show();
			jQuery("#divLossEntryNew").animate({width:'575px',opacity:'0.8'},"slow");
			jQuery("#divLossEntryNew").show();		
			jQuery("#pcsViewLayer").css('width','260');
			jQuery("#pcsEntryLayer").css('width','480');
			/*jQuery("#lblEquipmentPcsView").css("padding-left","36%");
			jQuery("#lblDatePcsView").css("padding-left","28%");
			jQuery("#lblShiftPcsView").css("padding-left","7%");*/
			jQuery("#productionGrid").setGridWidth(580); 	
			var flag = jQuery('#hdnEntryShowCount').val();
			if(flag != 'N')
				jQuery('#hdnEntryShowCount').val('Y');
			fnClear('Y');
			fnLossClear();
			loadPcsEntryFields();   

			jQuery( "#hideFunLocn" ).hide();
			jQuery( "#showFunLocn" ).show();
			jQuery("#pcsViewFields").hide();
		}
	}	
	
	jQuery( "#showFLImage" ).click(function() {
		jQuery( "#hideFunLocn" ).show();
		jQuery( "#showFunLocn" ).hide();
		jQuery("#pcsViewFields").show();
	});
	
	jQuery( "#hideFLImage" ).click(function() {		
		jQuery( "#hideFunLocn" ).hide();
		jQuery( "#showFunLocn" ).show();
		jQuery("#pcsViewFields").hide();
	});

	jQuery( "#hideEntry" ).click(function() {
			fnHideEntry();
	});

	function fnHideEntry() {		
		jQuery( "#showEntryLayer" ).show();
		jQuery( "#hideEntryLayer" ).hide();
		jQuery("#divLossEntryNew").hide();
		jQuery("#pcsViewLayer").css('width','90%');	
		jQuery("#pcsEntryLayer").css('width','2%');
		/*jQuery("#lblEquipmentPcsView").css("padding-left","21%");
		jQuery("#lblDatePcsView").css("padding-left","16%");
		jQuery("#lblShiftPcsView").css("padding-left","9%");*/
		jQuery( "#productionGrid" ).setGridWidth(1100);		 
	}
	
	jQuery( "#showEntry" ).click(function() {
		fnShowEntry();
	});

	function fnShowEntry() {				
		jQuery( "#showEntryLayer" ).hide();
		jQuery( "#hideEntryLayer" ).show();
		jQuery("#divLossEntryNew").show();	
		jQuery("#pcsViewLayer").css('width','50%');
		jQuery("#pcsEntryLayer").css('width','50%');
		/*jQuery("#lblEquipmentPcsView").css("padding-left","36%");
		jQuery("#lblDatePcsView").css("padding-left","28%");
		jQuery("#lblShiftPcsView").css("padding-left","7%");*/
		jQuery("#productionGrid").setGridWidth(580);
	}
		
	jQuery("#txtproducedqty").keypress(function(e){ 		
		if(e.keyCode == 113)
		{
			InsertLossEntry('');
		}
		else if(e.keyCode == 13)
		{
			InsertLossEntry('N');
		}		
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
	jQuery("#txtwno").keypress(function(e){ 		
		if(e.keyCode == 113)
		{
			InsertLossEntry('');
		}
		else if(e.keyCode == 9 || e.keyCode == 13)
		{
			InsertLossEntry('N');
		}
	});

	jQuery("#txtweight").keypress(function(e){ 		
		if(e.keyCode == 113)
		{
			InsertLossEntry('');
		}
		else if(e.keyCode == 9 || e.keyCode == 13)
		{
			InsertLossEntry('N');
		}
	});
	
/* 	jQuery('#cmbPlrkRootcauseid').combobox({		
		keyHandler: {
			enter: function(){
				InsertSubLossForProduct();
				enableFields('txaPlrkRemarks');
				enableUIButton('btnLossInsert');
				enableUIButton('btnLossClear');
				//enableUIButton('btnLossDelete');
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
*/	
	jQuery("#txtPlrkInstance").change(function(){ 
		enableFields('txtPlrkMinutes');	
	});
	
	jQuery("#txtPlrkMinutes").keypress(function(e){ 
		var causeFlag = jQuery('#causeLossPCS').css('display');
		//alert(causeFlag );		
		if(causeFlag == 'none' || causeFlag == 'inline')
		{
			if(e.keyCode == 9 || e.keyCode == 13)
			{
				InsertSubLossForProduct();
				enableFields('txaPlrkRemarks');
				enableUIButton('btnLossInsert');
				enableUIButton('btnLossClear');
				//enableUIButton('btnLossDelete');
			}
		}
	});
	
	jQuery("#txtPlrkMinutes").change(function(){ 
		var causeFlag = jQuery('#causeLossPCS').css('display');		
		if(causeFlag == 'none')
		{
			enableFields('txaPlrkRemarks');
			enableUIButton('btnLossInsert');
			enableUIButton('btnLossClear');
			//enableUIButton('btnLossDelete');	
		}
		else
		{
			enableFields('cmbPlrkCauseid');
			setFocusOnField('cmbPlrkCauseid');	
		}
	});

	
	function loadPcsEntryFields()
	{
		var lossId = jQuery('#hdnLossIdTempField').val();
		var producedQty = jQuery('#hdnProdQtyTempField').val();
		var dtlId = jQuery('#hdnDetailIdTempfield').val();
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();		
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var entryDate = jQuery("#dtedate").datebox('getValue');
		var mchId = jQuery("#hdnMachineId").val();
		var cellId = jQuery('#hdnSelectCell').val();
		var shiftName = jQuery('#cmbShiftidSelected').combobox('getText');		
		var cm = jQuery("#productionGrid").jqGrid("getGridParam", "colModel");
		var mchNo = cm[parseInt(cellId)].name;
		if(mchNo != null && mchNo != '' && mchNo != ' ' && mchNo != 'undefined')
			jQuery('#pcsMchName').html("Equipment : "+mchNo);
		jQuery('#pcsShiftLbl').html("Shift : "+shiftName);
		jQuery('#pcsDateLbl').html("Entry Date : "+entryDate);
		
		var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;	
		
		
		var flag = jQuery('#hdnEntryShowCount').val();
				
		if(flag == 'Y')	
		{
			fillComboBox("frmPcsView","cmbproductmodel","combo_productModel.pcs?"+cond );		
			cond += "&prdModelId=";
			
			fillComboBox("frmPcsView","cmbproductid","combo_product.pcs?"+cond );
			var sectId = jQuery("#frmPcsView input[id='section']").val();			
			var ds = "?&sectId="+sectId+"&isQtyLoss="+jQuery('#hdnIsQtyLoss').val();
			fillComboBox("frmPcsView","cmbPlrkLossid","combo_Loss.pcs"+ds);
			fillComboBox("frmPcsView","cmbPlrkReasonid","combo_Phenomena.pcs?");		
			fillComboBox("frmPcsView","cmbPlrkCauseid","combo_cause.pcs");
			fillComboBox("frmPcsView","cmbPlrkRootcauseid","combo_rootcause.pcs?");
		}
		else
		{
			fillComboBox("frmPcsView","cmbproductmodel","combo_productModel.pcs?"+cond );			
			cond += "&prdModelId=";
			fillComboBox("frmPcsView","cmbproductid","combo_product.pcs?"+cond );		
			var ds = "?&sectId="+sectId+"&isQtyLoss="+jQuery('#hdnIsQtyLoss').val();
			fillComboBox("frmPcsView","cmbPlrkLossid","combo_Loss.pcs"+ds);
			fillComboBox("frmPcsView","cmbPlrkReasonid","combo_Phenomena.pcs?");		
			fillComboBox("frmPcsView","cmbPlrkCauseid","combo_cause.pcs");
			fillComboBox("frmPcsView","cmbPlrkRootcauseid","combo_rootcause.pcs?");
		}
		var dataStr ="?&date=&machineId=&cellId=&productId=";
		fillComboBox("frmPcsView","cmbCycletimeCavity","combo_Cycletime.pcs"+dataStr );
		fillComboBox("frmPcsView","cmbCavityavailable","combo_Cavity.pcs"+dataStr );
		fillComboBox("frmPcsView","cmbrawmaterialtype","combo_rawtype.pcs" );
		disableLossFields();
		fillValueForQtyOrLoss();		
		checkMasterExists();
		setTimeout(function() {fillLossEntryGrid('');},1000);  
		numericTextBox('txtproducedqty');		
		numericTextBox('txtrejectedqty');		
		numericTextBox('txttrimmingqty');
		numericTextBox('txtexpansionqty');
		numericTextBox('txtActualcycletime');				
		numericTextBox('txtPlrkMinutes');
		numericTextBox('txtPlrkInstance');		
		var plmasterId =jQuery('#hdnPlmMasterId').val();	
		var productId = jQuery('#hdnProductIdTempfield').val();
			
		//alert("productId"+productId);
		
		if (productId=="" || productId==" " || productId==undefined)
			checkLastShiftProduct();
		else
		{			
			
			var dataStr = "&factId="+factId+"&prdModelId=&mchId="+mchId+"&entryDate="+entryDate;
			reloadCombo("frmPcsView","cmbproductid","combo_product.pcs?"+dataStr);

			setTimeout(function() {setProductModel(productId);},550);
			
			setTimeout(function() {jQuery('#cmbproductid').combobox('setValue',productId);},550);			
										
			setTimeout(function() {fillCycleTime();},550);			
			var rejQty = jQuery("#productionGrid").jqGrid('getCell',20,cellId);
			
			var cycleTime = jQuery("#productionGrid").jqGrid('getCell',15,cellId);
			var actualCycleTime = jQuery("#productionGrid").jqGrid('getCell',16,cellId);
			var cavAvl = jQuery("#productionGrid").jqGrid('getCell',13,cellId);
			var cavUsed = jQuery("#productionGrid").jqGrid('getCell',14,cellId);
			
			jQuery('#txtrejectedqty').val(rejQty);
		
			jQuery('#txtActualcycletime').val(actualCycleTime);
			jQuery('#cmbCycletimeCavity').combobox('setValue',cycleTime);
			jQuery('#txtCavityavailable').val(cavAvl);
			jQuery('#txtCavityused').val(cavUsed);
			

			var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);
			
			setTimeout(function() {calculateOldTime(producedQty,actualCycleTime,cavUsed);},500);
			setTimeout(function() {getWorkNoTrimQty();},550);
			
		}
			
		//readOnlyFields("txttrimmingqty");
		//readOnlyFields("txtexpansionqty");

	
		if(flag == 'Y' && dtlId != null && dtlId != ' ' && dtlId != '')	
			enableFields('cmbPlrkLossid');
		jQuery('#hdnPlrkPldetailid').val(dtlId);
		if(producedQty != null && producedQty != '' && producedQty != ' ')
			jQuery('#txtproducedqty').val(producedQty);
		if(lossId != null && lossId != '' && lossId != ' ')
		{
			getLossValues(lossId,dtlId);			
			jQuery('#cmbPlrkLossid').combobox('setValue',lossId);
		}
		processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");
		
		setTimeout(function() { setFocusOnField('txtproducedqty'); },550);
			
	}
	
	function calculateOldTime(producedQty,actualCycleTime,cavUsed)
	{
		var oldTime ="0";	
		
		if(cavUsed != null && cavUsed != '' && cavUsed != ' ')
		{
			if (parseFloat(cavUsed)=="0")
				oldTime ="0";
			else	
				oldTime = (parseFloat(producedQty) * parseFloat(actualCycleTime))
									 / parseFloat(cavUsed);	
		}
		
		jQuery('#hdnOldTime').val(oldTime);
	}
	
	function fillLossEntryGrid(isClear)
	{
		var pldetailsid =jQuery('#hdnDetailIdTempfield').val();
		var rowData = jQuery("#pcsEntryGrid").jqGrid('getRowData',1);		
		
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
			processGridnew('pcsLossEntryGridNew_view.pcs',"?&pldetailsid="+pldetailsid,"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","pcsLossEntryGrid_loadComplete","pcsLossEntryGridError");
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
		enableFields("cmbPlrkLossid");
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
			reloadCombo("frmPcsView","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
		else
			reloadCombo("frmPcsView","cmbPlrkReasonid","combo_Phenomena.pcs?&lossId="+lossId);	
		
		var oldTime = jQuery('#txtPlrkMinutes').val() ;
				
		if (rowData.cmbPlrkProcessid  == null || rowData.cmbPlrkProcessid  == "" || rowData.cmbPlrkProcessid  == " ") 
			var parentId = rowData.cmbPlrkReasonid;
		else
			var parentId = rowData.cmbPlrkProcessid + "-" + rowData.cmbPlrkReasonid;
				
		var dataStr = "?&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;
		reloadCombo("frmPcsView","cmbPlrkCauseid","combo_cause.pcs"+dataStr);		

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
			disableField("frmPcsView","txtPlrkMinutes");
		}
		else
			enableFields('txtPlrkMinutes');
		/*if(isQtyLoss != 'Y')
			setTimeout(function() { fnGetPendingTime(); },550);*/
	
		enableFields("cmbPlrkReasonid");
		enableFields('txtPlrkInstance');
		enableFields('cmbPlrkCauseid');
		enableFields('cmbPlrkRootcauseid');
		enableFields('txaPlrkRemarks');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');
		//enableUIButton('btnLossDelete');
		setTimeout(function() { setFocusOnField('cmbPlrkReasonid'); },550);
	}
	function getLossValues(lossId,plDetailId)
	{
		var dataStr = "?&lossId="+lossId+"&plDetailId="+plDetailId;		
		processAjaxCalls("getLossRelatedValues.pcs",dataStr,"fillLossValues","LossValuesError");
	}
	function fillLossValues(result)
	{
		jQuery('#cmbPlrkReasonid').combobox('setValue',result.reasonId);
		var dataStr = "?&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;
		reloadCombo("frmPcsView","cmbPlrkCauseid","combo_cause.pcs"+dataStr);
		setTimeout(function() {jQuery('#cmbPlrkCauseid').combobox('setValue',result.causeId);},550);
		
		jQuery('#cmbPlrkRootcauseid').combobox('setValue',result.rootcauseId);
		jQuery('#txtPlrkMinutes').val(result.minutes);
		jQuery('#txtPlrkInstance').val(result.instance);
		jQuery('#txaPlrkRemarks').val(result.remarks);
	}
	
	function getWorkNoTrimQty() {
		var dataStr ="";
		var entryDate=jQuery("#dtedate").datebox('getValue');	
		var machId = jQuery("#hdnMachineId").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var ProductId = jQuery("#cmbproductid").combobox('getValue');		
		var selCol = jQuery('#hdnSelectCell').val();
		var mstId = jQuery("#productionGrid").jqGrid('getCell',3,parseInt(selCol));
		
		dataStr+= "?&entryDate="+entryDate+"&productId="+ProductId+"&mchId="+machId+"&sectId="+sectId+"&mstId="+mstId+"&plDetailId=";
		
		processAjaxCalls("getDetailIdValues.pcs",dataStr,"getDetailIdValuesRecallSuccess","getDetailIdValuesRecallError");
		
	}
	
	function getDetailIdValuesRecallSuccess(result){
		var dtlId = result.detailId;
		var wno = result.wno;
		var trimQty = result.trimQty;
		var expQty = result.expQty;
		var weight = result.weight;
		var backLogQty = result.backLogQty;
		var rawMaterial = result.rawMaterial;

		if(weight != '' && weight != ' ' && weight != null)
			setTimeout(function() {jQuery('#txtweight').val(weight);},550);
			
		if(backLogQty  != '' && backLogQty  != ' ' && backLogQty  != null)
			setTimeout(function() {jQuery('#txtbacklogqty').val(backLogQty);},550);
		if(rawMaterial != '' && rawMaterial != ' ' && rawMaterial != null)
			setTimeout(function() {jQuery('#cmbrawmaterialtype').combobox('setValue',rawMaterial);},550);			
			
		if(wno != '' && wno != ' ' && wno != null)
			setTimeout(function() {jQuery('#txtwno').val(wno);},550);			
		if(trimQty != '' && trimQty != ' ' && trimQty != null)
			setTimeout(function() {jQuery('#txttrimmingqty').val(trimQty);},550);			
		if(expQty != '' && expQty != ' ' && expQty != null)
			setTimeout(function() {jQuery('#txtexpansionqty').val(expQty);},550);
			
		
	}
	
	function checkMasterExists() {
		var dataStr ="";
		var entryDate=jQuery("#dtedate").datebox('getValue');
		var shift=jQuery('#cmbShiftidSelected').combobox('getValue');
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		dataStr+= "?&entryDate="+entryDate+"&shift="+shift+"&cellId="+cellId+"&sectId="+sectId;
		//alert(dataStr);
		processAjaxCalls("getMasterKeyid.pcs",dataStr,"checkMasterIdRecallSuccess","checkMasterIdRecallError");
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
		disableField("frmPcsView","chkTrimming");
		disableField("frmPcsView","chkExpansion");
	}
	
	function checkLastShiftProduct() {
							
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var mchId = jQuery("#hdnMachineId").val();
		var entryDate = jQuery("#dtedate").datebox('getValue');
		var shift = jQuery('#cmbShiftidSelected').combobox('getValue');
		
		var dataStr = "?&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&entryDate="+entryDate+"&shift="+shift;
		processAjaxCalls("getLastShiftProduct.pcs",dataStr,"prevProductRecallSuccess","prevProductRecallError");
	}
	
	function prevProductRecallSuccess(result) {
		//alert(result.productId);
		if (result.productId.length>1) {
			var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
			var mchId = jQuery("#hdnMachineId").val();
			var entryDate = jQuery("#dtedate").datebox('getValue');					
			
			fillCycleTime();			
			showAllTime();
			var dataStr = "&factId="+factId+"&prdModelId=&mchId="+mchId+"&entryDate="+entryDate;
			reloadCombo("frmPcsView","cmbproductid","combo_product.pcs?"+dataStr);			
			
			setTimeout(function() {jQuery('#cmbproductid').combobox('setValue',result.productId);},550);
			setTimeout(function() {productIdOnSelect(result.productId);	},550);
			//alert(result.wno);
			setTimeout(function() {jQuery('#txtwno').val(result.wno);},550);
							
			//setProductModel(result.productId);
					
		}					
	}
	
	function fillCycleTime() {	
		var dataStr ="?";
		dataStr+= "&date="+jQuery("#dtedate").datebox('getValue');	
		dataStr+= "&machineId="+jQuery("#hdnMachineId").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();	
		dataStr+= "&cellId="+cellId+"&sectId="+sectId;		
		dataStr+= "&productId="+jQuery('#cmbproductid').combobox('getValue');
				
		reloadCombo("frmPcsView","cmbCycletimeCavity","combo_Cycletime.pcs"+dataStr );
	}
	
	function setProductModel(id) {
		var dataStr="?&prdId="+id;
		processAjaxCalls("selectedProductModel.pcs",dataStr,"modelIdSuccess","modelIdSuccessError");
	}

	function modelIdSuccess(result) {
		//alert(result.modelKeyid);
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var mchId = jQuery("#hdnMachineId").val();
		var entryDate=jQuery("#dtedate").datebox('getValue');
		var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;

		reloadCombo("frmPcsView","cmbproductmodel","combo_productModel.pcs?"+cond );
			
		jQuery('#cmbproductmodel').combobox('setValue',result.modelKeyid);		
	}
	function fillcmbProduct() 	{		
		var mchId = jQuery("#hdnMachineId").val();
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var prdModelId = jQuery('#cmbproductmodel').combobox('getValue');
		var entryDate=jQuery("#dtedate").datebox('getValue');	
			
		var dataStr = "&factId="+factId+"&prdModelId="+prdModelId+"&mchId="+mchId+"&entryDate="+entryDate;		
		jQuery('#cmbproductid').combobox('clear');	
		reloadCombo("frmPcsView","cmbproductid","combo_product.pcs?"+dataStr);
	}
	
	function showUnreportedTime() {
		getCalendarTime();
	}
	function getCalendarTime() {
		var dataStr ="";
		var machineId = jQuery('#hdnMachineId').val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var plmasterId =jQuery('#hdnPlmMasterId').val();
		var entryDate=jQuery("#dtedate").datebox('getValue');			
		dataStr+= "?&sectId="+sectId+"&machineId="+machineId+"&plmasterId="+plmasterId+"&entryDate="+entryDate;
		//alert(dataStr);
		processAjaxCalls("getCalendarTime.pcs",dataStr,"getCalenderTimeRecallSuccess","getCalendarTimeRecallError");
		
	}
	function getCalenderTimeRecallSuccess(result) {
	//alert(result.calendarTime);
		if (result.calendarTime > 480) 
			jQuery('#txtcalendartime').val(480);
		else if (result.calendarTime<=0)
			jQuery('#txtcalendartime').val(0);
		else
			jQuery('#txtcalendartime').val(result.calendarTime);		

		var unreportTime =jQuery('#txtcalendartime').val();
		jQuery('#txtUnreportedtime').val(unreportTime);
		
	}
	jQuery('#chkTrimming').click(function() {
		if(jQuery('#chkTrimming').is(':checked')==true ) 
			enableFields("txttrimmingqty");
		else
			disableField("frmPcsView","txttrimmingqty");
			//readOnlyFields("txttrimmingqty");
	});

	jQuery('#chkExpansion').click(function() {
		if(jQuery('#chkExpansion').is(':checked')==true ) 
			enableFields("txtexpansionqty");
		else
			disableField("frmPcsView","txtexpansionqty");
			//readOnlyFields("txtexpansionqty");				
	});
	jQuery('#btnInsert').click(function() {		 
		InsertLossEntry('');
	});
	jQuery('#btnClear').click(function() {
		fnClear();	
	});

	jQuery('#btnDelete').click(function() {
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		//var pldetailsId =rowData.txtPldetailsid;		
		//var tableName = jQuery("#hdnTableName").val();
		var pldetailsId = jQuery('#hdnDetailIdTempfield').val();
		var tableName = jQuery('#hdnTableName').val();		

		var Plmasterid = jQuery('#hdnPlmMasterId').val();
		var workOrderNo =jQuery('#txtwno').val();				
		//var tableName = jQuery("#hdnTableName").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();		
		var machId = jQuery('#hdnMachineId').val();
		var entryDate=jQuery("#dtedate").datebox('getValue');
		var url = "pcsEntry_delete.pcs" ;//defined in classic.jsp
		url+= "?&sectId="+sectId+"&pldetailsId="+pldetailsId+"&workOrderNo="+workOrderNo+"&Plmasterid="+Plmasterid+"&machId="+machId+"&entryDate="+entryDate;		
		//alert(url);
		if(formId.length > 0 )
		saveForm(formId,url);	});
	
	function disableLossFields()
	{
		   numericTextBox('txtPlrkMinutes');
		   numericTextBox('txtPlrkInstance');
		   readOnlyFields('cmbPlrkLossid');				
		   readOnlyFields('cmbPlrkReasonid');
		   disableField('frmPcsView', 'txtPlrkInstance');
		   disableField('frmPcsView', 'txtPlrkMinutes');
		   //readOnlyFields('txtPlrkInstance'); 
		  // readOnlyFields('txtPlrkMinutes');
		   readOnlyFields('cmbPlrkCauseid');
		   readOnlyFields('cmbPlrkRootcauseid');
		  
		   disableField('frmPcsView', 'txaPlrkRemarks');
		   
		   readOnlyFields('txaPlrkRemarks');	
		   disableUIButton('btnLossInsert');
		   disableUIButton('btnLossClear');
		   disableUIButton('btnLossDelete');		  	
	}

	function fillValueForQtyOrLoss()
	{		
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		if (isQtyLoss =="Y") {			
			jQuery("#lblCause").addClass('mandatory-lbl');
			jQuery('#lblTimeOrQty').html("Loss Qty");			
			jQuery('#lblCause').show();
			jQuery('#lblRootCause').show();	
			jQuery('#causeLossPCS').show();						
			jQuery('#rootcauseLossPCS').show();
			jQuery('#divCauseRootCause').show();			
		}
		else {
			jQuery("#lblCause").removeClass('mandatory-lbl');
			jQuery('#lblTimeOrQty').html("Loss Time");
			jQuery('#lblCause').hide();
			jQuery('#lblRootCause').hide();	
			jQuery('#causeLossPCS').hide();
			jQuery('#rootcauseLossPCS').hide();
			jQuery('#divCauseRootCause').hide();		
			//jQuery('#cmbPlrkWno').combobox('clear');
			jQuery('#cmbPlrkCauseid').combobox('clear');
			jQuery('#cmbPlrkRootcauseid').combobox('clear');
		}		
	}
	
	function getRejectionQty()
	{
		
		var flag = jQuery('#spnRejQty').css('display');	
	
		if(flag.indexOf('none') < 0)
		{
			var lossEntryGrid = jQuery("#pcsLossEntryGrid").jqGrid('getDataIDs');
			var rejQty = jQuery("#txtrejectedqty").val();
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
				
				jQuery("#txtrejectedqty").val(q);
			}
		}
		
	}
	function getFieldsForPcsSuccess(result) {		
		if (result.value.indexOf("EQ") != -1) {
			jQuery('#spnExpansionLabel').css('display','none');
			jQuery('#spnExpansionQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtexpansionqty");
			//jQuery('#spnRejQty').css('display','none');	
			//jQuery('#lblRejQty').css('display','none');				
		}
		else 
			glbIsHalol=true;
		if (result.value.indexOf("TQ") != -1) {			
			jQuery('#spnTrimmingLabel').css('display','none');
			jQuery('#spnTrimmingQty').css('display','none');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txttrimmingqty");
			//jQuery('#spnRejQty').css('display','none');	
			//jQuery('#lblRejQty').css('display','none');	
		}
		
		if (result.value.indexOf("NOPLAN") != -1) {
			jQuery( '#spnNoPlan').hide();
			jQuery('#lblCause').show();
			jQuery('#lblRootCause').show();	
			jQuery('#causeLossPCS').show();						
			jQuery('#rootcauseLossPCS').show();
			jQuery('#divCauseRootCause').show();					
		}
		if (result.value.indexOf("WO") != -1) {
			jQuery('#spnLabelWno').css('display','none');
			jQuery('#spntxtWno').css('display','none');	
			jQuery('#spnBackLogQty').css('display','none');	
			jQuery('#lblBackLog').css('display','none');		
			jQuery('#spncyctime').css('padding-left','145px');
			jQuery("#pcsEntryGrid").jqGrid('hideCol',"txtwno");			
		}
	}
	
	function InsertLossEntry(isClear)
	{
		var prodQty = jQuery('#txtproducedqty').val();	
		if (prodQty.length <= 0) {
			 alert("Enter Produced Qty");
			 return ;
		}		
		var rejQty = jQuery('#txtrejectedqty').val();		
	    if ((prodQty != null && prodQty != '' && prodQty != ' ')  && (rejQty != null && rejQty != '' && rejQty != ' '))
		{ 
			if(parseInt(rejQty)>parseInt(prodQty))
			{
				alert("Rejection Qty should Not exceed Produced Qty"); 
				return ;
			}
		}
		var mchId = jQuery('#hdnMachineId').val();
		var date  =	jQuery("#dtedate").datebox('getValue');
		var shift =	jQuery('#cmbShiftidSelected').combobox('getValue');	
		var ds = '?&date='+date+'&shift='+shift+'&mchId='+mchId;
		if(isClear != null && isClear != '' && isClear != ' ')
			ds += '&isClear='+isClear;
		//alert('before')	;	
		
		//jQuery('#divLossEntryNew').addClass('window-mask');
		//jQuery('#divLossEntryNew > div.save-loading').show();
		showLoadingMsg(1);
		processAjaxCalls('checkIsOpenMsr.pcs',ds,"CheckMsrSuccess","CheckMsrError");
		
	}
	
	function CheckMsrError() {
		showLoadingMsg(0);	
	}
	
	function CheckMsrSuccess(result) {
		//jQuery('#divLossEntryNew').removeClass('window-mask');
		//jQuery('#divLossEntryNew > div.save-loading').hide();
	
		var isOpenMst = "N";
		if (result.msrData.isOpenMsr == "1") {
			var msg ="Open MSRs are found for the shift. Would you like to continue PCS entry?";
			if(confirm(msg) == false) {
				showLoadingMsg(0);				
				return;
			}
			else
				isOpenMst="Y";
		}			

		if (jQuery("#spntxtWno").is(":visible")) {			
			var wno=jQuery("#txtwno").val();			
			if  (wno=="" || wno== " " || wno==null) {
				alert("Enter Work Order No");
				setFocusOnField('txtwno');
				showLoadingMsg(0);
				return;
			}
		}
		
		var formId = jQuery('#submitForm').val();		
		var url = "pcsentrynew_save.pcs?";		
		var date  =	jQuery("#dtedate").datebox('getValue');
		var shift =	jQuery('#cmbShiftidSelected').combobox('getValue');			
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery('#hdnMachineId').val();		
		var Plmasterid = jQuery('#hdnPlmMasterId').val();
		var Pldetailsid = jQuery('#hdnPldetailsid').val();		
		var Ptwokeyid = jQuery('#hdnPtwokeyid').val();		
		var oldTime = jQuery('#hdnOldTime').val();				
		if (Plmasterid == "" || Plmasterid == " " || Plmasterid == null)
			url += "&saveMode=Save";
		else
			url += "&saveMode=Update";
		
		var plDtlId = jQuery('#hdnDetailIdTempfield').val();
		
		var insertNewCol = "N";
		if(plDtlId != null && plDtlId != '' && plDtlId != ' ')
			insertNewCol = "Y";		
		
		if (oldTime=="" || oldTime==" " || oldTime==null) 
			oldTime="0";

		var firstEntry = "N";
		var selectedCell  = jQuery('#hdnSelectCell').val();
		
		if (plDtlId=="" || plDtlId==" " || plDtlId==null) 
		{
			firstEntry=selectedCell;
			var detailId = jQuery("#productionGrid").jqGrid('getCell',2,parseInt(selectedCell));
			if (detailId!="" && detailId!=" " &&  detailId!=null) 
				insertNewCol = "Y";		
		}
		
		url += "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid+"&Ptwokeyid="+Ptwokeyid+"&oldTime="+oldTime;
		url += "&isOpenMst="+isOpenMst+"&insertNewCol="+insertNewCol+"&plDtlId="+plDtlId+"&firstEntry="+firstEntry;	
		url += "&cmbPrlmFactoryid="+factId+"&cmbPrlmSectionid="+sectId+"&cmbPrlmCellid="+cellId+"&cmbmachineid="+mchId+"&hdnPrlmEntrydate="+date+"&hdnPrlmShiftid="+shift;
		
		if(result.isClear != null && result.isClear != '' && result.isClear != ' ')
			url += "&isClear="+result.isClear;	
				
		if(formId.length > 0 )
			saveForm(formId,url);
				
	}

	function frmPcsView_errorCallback(msg) {
		showLoadingMsg(0);	
	}
	function frmPcsView_exceptionCallback(msg) {
		showLoadingMsg(0);	
	}
	
	
	function frmPcsView_successsCallback(result) {
	
		showLoadingMsg(0);
		if (result.type=="D-L") {			
			var lossId = result.lossId;
			fnLossSaveSuccessCallBack(result);
			//removeValueinLossRow(lossId);
			jQuery("#pcsLossEntryGrid").jqGrid().trigger("reloadGrid");
			fnLossClear();			
			showAllTimes(result);				
			return;
		}
		
		else if (result.type=="D-P") {
			alert(result.msg);
			var chkDtlId = result.keyId;			
			removeValuesInPcsGrid(chkDtlId);
			fnHideEntry();
			return;
		}
		else if (result.type=="S-L") {			
			fnLossSaveSuccessCallBack(result);			
			return;	
		}	
		
		alert(result.msg);		
		jQuery('#hdnDetailIdTempfield').val(result.PLDetailsID);

		//alert('glbPldetailIdChanged'+glbPldetailIdChanged);
		//alert(result.PLDetailsID);
		/*if (glbPldetailIdChanged!="" && glbPldetailIdChanged!=undefined && glbPldetailIdChanged!=result.PLDetailsID) {
			//alert('changed');
			fnView();			
						
		}*/
		//alert('result.insertNewCol '+result.insertNewCol );
		
		if( "Y" == result.insertNewCol)
		{
			//alert('ok');
			var rowIds = jQuery("#productionGrid").getDataIDs();
			var countCols = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames').length;
			var colIds  = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames');
			var colArray = new Array(countCols); 
			var machId = result.machineId;	
			var chkDtlId = result.PLDetailsID;
			//alert(chkDtlId );
			jQuery('#hdnPldetailsid').val(chkDtlId);			
			if(chkDtlId != "")	
			{
				var colNo = -1;
				for (j=23;j<= countCols-1;j++) {
					var  dtlId = jQuery("#productionGrid").jqGrid('getCell',rowIds[1],j);						
					if(dtlId == chkDtlId)
						colNo =j;
				}
				//alert(colNo);
				if(colNo > 0)
					setValuesInPcsGrid(result,'',colNo);
			
			else{		
				var x=0;			
				for(var i=0;i<colArray.length;i++)
				{
					if(colIds[i] == "TOTAL")
						x = parseInt(i)+1;											
				}		
				
				var flag=-1;
				var prevFlag=-1;
			//	var prevInc = false;
				for (j=23;j<= countCols-1;j++) {
					var  mchId = jQuery("#productionGrid").jqGrid('getCell',rowIds[0],j);						
					if(machId == mchId)	
					{
						flag = j+1;
						//x = j+2;
					}
				}
				if(x != 0)
				{
					jQuery("#productionGrid").jqGrid('showCol',[x.toString()]);
				}
			
				for(var i=0;i<colArray.length;i++)
				{
					if(flag != i)
					{
						if(prevFlag <0)
							colArray[i] = i;
						else
						{
							
							colArray[i] = prevFlag;
							//if(prevInc)
							if(i != x)
								prevFlag++;
							else
								prevFlag = prevFlag +2;
							/*else
							{
								prevFlag = x+1;
								prevInc = true;
							}*/
							
						}
					}	
					else
					{
						colArray[i] = x;
						prevFlag = i;
					}		
				}
				/*for(var i=0;i<colArray.length;i++)
				{
					alert( i + " : "+colArray[i]);
				}*/
				setValuesInPcsGrid(result,x.toString());
				//jQuery("#productionGrid").jqgrid("setLabel", x.toString(), result.colHeader);
				//[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,25,26,27,24]
				
				jQuery("#productionGrid").remapColumns(colArray,true,false);
				jQuery("#jqgh_productionGrid_"+x.toString()).html(result.colHeader);

				processGridnew('pcsLossEntryGridNew_view.pcs',"?&pldetailsid="+jQuery('#hdnPlrkPldetailid').val(),"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","","pcsLossEntryGridError");
			}			
			}
		}
		else
		{
			var rowIds = jQuery("#productionGrid").getDataIDs();
			var colIds  = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames');
			var countCols = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames').length;
			var dtlKey = result.dtlKeyId;
			var firstEntry = result.firstEntry;
			var colNo = -1;
			
			if(firstEntry == 'N')
			{
				for (j=23;j<= countCols-1;j++) {
					var  dtlId = jQuery("#productionGrid").jqGrid('getCell',rowIds[1],j);						
					if(dtlKey == dtlId)
						colNo =j;
				}
			}
			else
			{
				if(firstEntry != '' && firstEntry != ' ' && firstEntry != null)
					colNo =parseInt(firstEntry);
			}
				
			if(colNo > 0)
				setValuesInPcsGrid(result,'',colNo);
		}
		if (jQuery('#hdnPlmMasterId').val()=="" || jQuery('#hdnPlmMasterId').val()==" ")
			jQuery('#hdnPlmMasterId').val(result.keyId);

		if (result.status =="Success_withMaster")
			jQuery('#hdnPlmMasterId').val("");
		
		jQuery('#hdnPlrkPldetailid').val(result.dtlKeyId);

		setTimeout(function() {fillLossEntryGrid(result.isClear);},2500);   
		fnLossClear();
		
		var rejQty = jQuery('#txtrejectedqty').val();
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
				processAjaxCalls('getLineRejectionLoss.pcs','?',"LineRejQtySuccess");				
			}
			else
			{
				setFocusOnField('cmbproductid');	
				fnClear();
				jQuery('#hdnPlrkPldetailid').val('');
			}
		}		
		else
		{
			setFocusOnField('cmbPlrkLossid');		
			if(lineRejection)		
			{
				processAjaxCalls('getLineRejectionLoss.pcs','?',"LineRejQtySuccess");	
			}		
		}			 
		/*fnLossClear();
		var rejQty = jQuery('#txtrejectedqty').val();
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
				processAjaxCalls('getLineRejectionLoss.pcs','?',"LineRejQtySuccess");				
			}
			else
			{
				setFocusOnField('cmbproductid');	
				fnClear();
				jQuery('#hdnPlrkPldetailid').val('');
			}
		}		
		else
		{
			setFocusOnField('cmbPlrkLossid');		
			if(lineRejection)		
			{
				processAjaxCalls('getLineRejectionLoss.pcs','?',"LineRejQtySuccess");	
			}		
		}*/

		//showUnreportedTime();
		showAllTimes(result);
		setTimeout(function() { setFocusOnField('cmbPlrkLossid'); },550);
		
		
	}

	function showAllTimes(result) {
		jQuery('#txtAssttime').val(result.asstTime);
		jQuery('#txtProdtime').val(result.prodTime);
		jQuery('#txtLosstime').val(result.lossTime);
		jQuery('#txtUnreportedtime').val(result.unreportTime);
		jQuery('#txtcalendartime').val(result.unreportTime);
	}

	function LineRejQtySuccess(result)
	{
		//alert(result.loss);
		if(result.loss != '') {
			jQuery('#cmbPlrkLossid').combobox('setValue',result.loss);
			fnLossidOnSelect(result.loss);
		}
					
		//enableFields('cmbPlrkReasonid');
		
		reloadCombo("frmPcsView","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );														
		jQuery('#lblTimeOrQty').html("Loss Qty");			
		jQuery('#lblCause').show();
		jQuery('#lblRootCause').show();	
		jQuery('#causeLossPCS').show();
		jQuery('#rootcauseLossPCS').show();		
		setTimeout(function() { setComboDefaultValue("frmPcsView", "cmbPlrkCauseid"); },550);		
	}

	function removeValueinLossRow(lossId) {
		
		var rowIds = jQuery("#productionGrid").getDataIDs();
		var selCol = jQuery('#hdnSelectCell').val();
		var rowNo=0;		
		for(i=2;i<rowIds.length;i++) {
			var  lsId = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],7);									
			if(lsId == lossId) {
				rowNo =i;					
			}
	 	}	 
		
	 	if (rowNo>0) {
	 		//alert('selCol'+selCol);
		 	//alert('rowNo'+rowNo);			
			jQuery("#productionGrid").jqGrid('setCell',parseInt(rowNo)+1,parseInt(selCol)," ",{'text- align':'right'});
	 	}
	}
	
	function setValuesInPcsGrid(result,colName,colNo)
	{		
 		var colVal = result.colValues;
 		//alert('colVal'+colVal);
 		var colValArr = colVal.split(',');
 		//alert('colValArr'+colValArr[0]);
	    for(i=0;i<colValArr.length;i++)	
	 	{	
		 	if(colName != '')
		 		jQuery("#productionGrid").jqGrid('setCell',i,colName,colValArr[i],{'text- align':'right'});
		 	else
		 		jQuery("#productionGrid").jqGrid('setCell',i,colNo,colValArr[i],{'text- align':'right'});
	 	}
	 	
	    //jQuery("#productionGrid").jqgrid('setcolumnproperty', colName, 'width', 'auto');
	    //jQuery("#productionGrid").jqgrid("setLabel", colName, result.colHeader);
	}

	function removeValuesInPcsGrid(chkDtlId)	{	
				
		var countCols = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames').length;
		var rowIds = jQuery("#productionGrid").getDataIDs();
		var colNo = -1;

		for (j=23;j<= countCols-1;j++) {
			var  dtlId = jQuery("#productionGrid").jqGrid('getCell',rowIds[1],j);						
			if(dtlId == chkDtlId)
				colNo =j;
		}
		
		if(colNo > 0) {		
	    	for(i=2;i<rowIds.length;i++) {			 	
			 	jQuery("#productionGrid").jqGrid('setCell',i,colNo," ",{'text- align':'right'});			 	
		 	}
		}
	}

	
	function fnLossSaveSuccessCallBack(result) {
		//alert('type-l'+result.type);		
		
		jQuery('#hdnPlrkKeyid').val('');
		jQuery('#hdnLossMode').val('');
		
		var rowIds = jQuery("#productionGrid").getDataIDs();		
		var countCols = jQuery('#productionGrid').jqGrid('getGridParam', 'colNames').length;
		var dtlKey = result.detailId;
		var lossIdVal = result.lossId;
		var colNo = -1;
		var lossRow = -1;
		//alert('dtlKey'+dtlKey);
		for (j=23;j<= countCols-1;j++) {
			var  dtlId = jQuery("#productionGrid").jqGrid('getCell',rowIds[1],j);						
			if(dtlKey == dtlId)
				colNo =j;
		}
		//alert('colNo'+colNo);
		setValuesInPcsGrid(result,'',colNo);
		/*for (i=0;i<rowIds.length;i++) {
			var  lossId = jQuery("#productionGrid").jqGrid('getCell',rowIds[i],0);
			if(lossIdVal == lossId)
				lossRow =i+1;	
		}		
		
		if(colNo > 0 && lossRow > 0)
		{
			jQuery("#productionGrid").jqGrid('setCell',lossRow,colNo,result.minutes);
		}*/
		processGridnew('pcsLossEntryGridNew_view.pcs',"?&pldetailsid="+jQuery('#hdnPlrkPldetailid').val(),"pcsLossEntryGrid","pcsLossEntryPager","PCS Entry","pcsLossEntryGrid_dblClick","","","pcsLossEntryGridError");
		fnLossClear();
		enableFields('cmbPlrkLossid');
		setFocusOnField('cmbPlrkLossid');
		
		//showUnreportedTime();		
		showAllTimes(result);
		
		//for msr, abn, clit
		var lossNo = result.lossNo;
		//alert(lossNo);
		
		var saveMsg = result.msg;
		if (lossNo.substring(0,3)=="1.1") {
			var msg = " Do You want Open MSR";
			msg= saveMsg + ", " + msg;
			if(confirm(msg)) {
				openRelatedForm("MSR");
			}
		}
		else if (lossNo.substring(0,4)=="9.07") {
			var msg = " Do You want process CLIT ";
			msg= saveMsg + ", " + msg;
			if(confirm(msg)) {
				//msg = " OK for ABNORMALITY / cancel for CLIT ";
				/* if(confirm(msg)) {
					openRelatedForm("ABN");
				}
				else { */ 
					openRelatedForm("CLIT");
				//}					
			}
		}
		else {
			alert(saveMsg);
		}
		
		
	}
	
	function openRelatedForm(type) {
		//alert(type);
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();	   
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery("#frmPcsView input[id='machine']").val();
		var flid = jQuery("#frmPcsView input[id='flid']").val();
		var mode = "Update";
		
		var date=jQuery('#dtedate').datebox('getValue');
		var shift = jQuery('#cmbShiftidSelected').combobox('getValue');
		
		var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode};
		var url ;
		
		if (type == "ABN") {
			url = "Abnormality_input.abnForm?&AbnId=AB1301091&tagClass=RED&abnStatus=PENDING&mode=modify&WOID=MW13110052&filterButton=false";
			navigateToNextForm(url ,'Abnormality Modification',null,persistentData);			
		}
		
		//jhClit_frm.jhclit?&loadContentDivId=Loadjhclitfrm&preLoadContentDivId=preloadDIVid4
				
		if (type == "MSR"){			
			var refDocId="";
			url = "workReq_input.work?&bookingMode=msrInsert&refDocId="+refDocId+"&selMachineId="+mchId;			
			navigateToNextForm(url,"Maintenance Service Request",null,persistentData);
		}
		
		if (type == "CLIT") {
			var jsonstr = '{"mchId":"MCH0002060" ,"areaId":"MMA/0005","month":"Nov-2013"}';
			var perstData = jQuery.parseJSON(jsonstr);
			//url = "jhClit_input.jhclit?&fromPage=pcs";
			//url = "jhcalendar_input.jhcal?filterString=%3Fq%3D2%26dtFromMonth%3DNov-2013%26dtToMonth%3DNov-2013%26cmbMchid%3DMCH0002060%26areaId%3DMMA/0005&filterButton=false";
			url = "jhcalendar_input.jhcal?filterString=%3Fq%3D2%26dtFromMonth%3DDec-2013%26dtToMonth%3DDec-2013%26cmbMchid%3DMCH0002060%26areaId%3DMMA/0005&filterButton=false";
			
			navigateToNextForm(url,"CLTI Schedule",null,perstData );
			//LoadPopUp("divCLIT",url, true,"90%","90%","2%","10px", "clit_Callback","CLIT Creation",false,false);		
		}
		
		//popFormNavigation();
		//navigateToNextForm("Abnormality_input.abnForm?&refDocId="+refDocId+"&filterButton=false"+"&selMachineId="+selMachineId,"Abnormality Identification",null,{"filterString":filtStr});
		//closePopUpDialoge("divPcsView");
	}

	function clit_Callback() {
		
	} 
	
	function fnClear(fill) {
		jQuery('#cmbproductmodel').combobox('clear');
		jQuery('#cmbproductid').combobox('clear');
		jQuery('#cmbCycletimeCavity').combobox('clear');	
		jQuery('#cmbrawmaterialtype').combobox('clear');
	
		jQuery('#txtproducedqty').val('');
		jQuery('#txtrejectedqty').val('');
		jQuery('#txttrimmingqty').val('0');
		jQuery('#txtexpansionqty').val('0');	
		//jQuery('#txtwno').val('');			
		jQuery('#txtActualcycletime').val('');
		jQuery('#txtplannedqty').val('');
		jQuery('#txtPlrkMinutes').val('');		
		jQuery('#txtCavityavailable').val('');
		jQuery('#txtCavityused').val('');		
		jQuery('#hdnOldTime').val('0');
		
		jQuery('#txtweight').val('');
		jQuery('#txtbacklogqty').val('');
		//readOnlyFields("txttrimmingqty");
		//readOnlyFields("txtexpansionqty");

		if(fill != 'Y')
		{
			var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
			var mchId = jQuery("#hdnMachineId").val();
			var entryDate=jQuery("#dtedate").datebox('getValue');	
			
			var cond="&factId="+factId+"&mchId="+mchId+"&entryDate="+entryDate;
			reloadCombo("frmPcsView","cmbproductmodel","combo_productModel.pcs?"+cond );				
			
			fillcmbProduct();
			getCalendarTime();	
		}	
	}
	jQuery('#btnLossInsert').click(function() {
		InsertSubLossForProduct();
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
		//var Pldetailsid = jQuery('#txtPlrkPldetailid').val();
		//var sectId = jQuery("#frmPcsView input[id='hdnPlrkSectionid']").val();
		var Pldetailsid = jQuery('#hdnDetailIdTempfield').val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();		

		if (!msrNo==null && !msrNo=="" && !msrNo==" ") {
			return ;}

		var plmasterId =jQuery('#hdnPlmMasterId').val();
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var machineId = jQuery('#hdnMachineId').val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var entryDate = jQuery("#dtedate").datebox('getValue');
		var shift = jQuery('#cmbShiftidSelected').combobox('getValue');
		
		var url = "pcsLossEntryNew_delete.pcs" ;
		url+= '?&PlrkKeyid='+PlrkKeyid+'&Pldetailsid='+Pldetailsid+'&factId='+factId+'&sectId='+sectId+'&cellId='+cellId+'&lossId='+lossId+'&lossVal='+lossVal;
		url+= '&plmasterId='+plmasterId+'&machineId='+machineId+'&entryDate='+entryDate+'&shift='+shift;		
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);
	});
	
	function InsertSubLossForProduct()
	{
		var lossTime = jQuery('#txtPlrkMinutes').val();
		var wno = jQuery('#txtwno').val();
		var lossId = jQuery('#cmbPlrkLossid').combobox('getText');
		var mode = jQuery('#hdnLossMode').val();		

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
				var oldUsedTime = jQuery('#hdnOldUsedTime').val();
				//alert(oldUsedTime);
				mins = parseFloat(s) + parseFloat(mins)  -parseFloat(oldUsedTime);
				
				var producedQty = jQuery('#txtproducedqty').val();				
				if(parseFloat(mins)>parseFloat(producedQty))
				{
					alert("Rejection Breakup Should not Exceed Produced Quantity");
					jQuery('#txtPlrkMinutes').val('');
					setTimeout(function() {setFocusOnField('txtPlrkMinutes');},550); 
					return;
				}
				
				var rejQty = jQuery('#txtrejectedqty').val();
				
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
			//var pendTime = jQuery('#hdnPendingTime').val();
			var pendTime = jQuery('#txtUnreportedtime').val();
			var oldUsedTime = jQuery('#hdnOldUsedTime').val();

			if (oldUsedTime == null || oldUsedTime == "" || oldUsedTime == " ")
				oldUsedTime="0";
			if (pendTime == null || pendTime == "" || pendTime == " ")
				pendTime="0";
			
			var diffTime = (parseFloat(pendTime) + parseFloat(oldUsedTime)) - parseFloat(lossTime);
			
			if (diffTime != null && diffTime < 0) {
				alert("Production Time can not be great than Available Time");
				return; 			
			}
		}
			
		var formId = 'frmPcsView';
		var url = "pcsLossEntryNew_save.pcs?";		
		if (jQuery('#hdnLossMode').val() == "Update")
			url+= '&saveMode=Update';
		else
			url+= '&saveMode=Save';		
		
		
		var lossId = jQuery('#cmbPlrkLossid').combobox('getValue');	
		var Pldetailsid = jQuery('#hdnDetailIdTempfield').val();
		var plmasterId =jQuery('#hdnPlmMasterId').val();
		//var Pldetailsid = jQuery('#hdnDetailIdTempField').val();
		//alert(Pldetailsid);
		//var Pldetailsid = jQuery('#hdnPlrkPldetailid').val();
		var PlrkKeyid = jQuery('#hdnPlrkKeyid').val();
			
		url += "&PlrkKeyid="+PlrkKeyid+"&plmasterId="+plmasterId+"&Pldetailsid="+Pldetailsid+"&lossId="+lossId+"&lossValue="+mins;		
		var date  =	jQuery("#dtedate").datebox('getValue');
		var shift =	jQuery('#cmbShiftidSelected').combobox('getValue');			
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var cellId = jQuery("#frmPcsView input[id='cell']").val();
		var mchId = jQuery('#hdnMachineId').val();
		//alert(mchId);
		url += "&txtPlrkDate="+date+"&txtPlrkShiftid="+shift+"&txtPlrkFactoryid="+factId+"&txtPlrkSectionid="+sectId+"&txtPlrkCellid="+cellId+"&txtPlrkMachineid="+mchId+'&txtPlrkWno='+wno;

		showLoadingMsg(1);
		
		if(formId.length > 0 )
			saveForm(formId,url);		
	}
	
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
	
	function fnLossClear() {				
			jQuery('#cmbPlrkLossid').combobox('clear');
			jQuery('#cmbPlrkReasonid').combobox('clear');
			jQuery('#cmbPlrkCauseid').combobox('clear');
			jQuery('#cmbPlrkRootcauseid').combobox('clear');	
			jQuery('#txtPlrkMinutes').val('');
			jQuery('#txtPlrkInstance').val('');	
			jQuery('#txaPlrkRemarks').val('');		
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
			reloadCombo("frmPcsView","cmbPlrkReasonid","combo_QTYPhenomena.pcs" );
		else							
			reloadCombo("frmPcsView","cmbPlrkReasonid","combo_Phenomena.pcs?&lossId="+lossId);
		if (lossId != "" && lossId != " ") {						
			if (isQtyLoss =="Y") {			
				setTimeout(function() { setComboDefaultValue("frmPcsView", "cmbPlrkCauseid"); },550);							
			}
		}
	}
	function frmPcsViewcmbproductmodel_onLoadSuccess() 	{
		setComboDefaultValue("frmPcsView","cmbproductmodel");		
	}
	function frmPcsViewcmbproductmodel_onSelect(record)	{		
		fillcmbProduct();
	}
	
	function frmPcsViewcmbproductid_onLoadSuccess() 	{
		setComboDefaultValue("frmPcsView","cmbproductid");			
	}
	
	function frmPcsViewcmbproductid_onSelect(record)	{
		productIdOnSelect(record.id);	
	}

	function productIdOnSelect(prdId) {
		
		jQuery('#cmbCycletimeCavity').combobox('clear');		
		jQuery('#txtActualcycletime').val('');	
		jQuery('#txtCavityavailable').val('');
		jQuery('#txtCavityused').val('');
		jQuery('#txtproducedqty').val('');	
		jQuery('#txtrejectedqty').val('');
		jQuery('#txttrimmingqty').val('0');	
		jQuery('#txtexpansionqty').val('0');
		jQuery('#txtwno').val('');
		jQuery('#txtweight').val('');
		jQuery('#txtbacklogqty').val('');
			
		fillCycleTime();
		showAllTime();
		setProductModel(prdId);
		reloadCombo("frmPcsView","cmbrawmaterialtype","combo_rawtype.pcs?&productId="+prdId);		
		jQuery('#hdnPldetailsid').val("");		
	}
	
	function frmPcsViewcmbCycletimeCavity_onLoadSuccess()	{		
		//showUnreportedTime();
		showAllTime();
		setTimeout(function() {setComboDefaultValue("frmPcsView","cmbCycletimeCavity");},300);		
	}

	function showAllTime() {
		var dataStr ="";
		var machineId = jQuery('#hdnMachineId').val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();
		var plmasterId =jQuery('#hdnPlmMasterId').val();
		var entryDate=jQuery("#dtedate").datebox('getValue');			
		dataStr+= "?&sectId="+sectId+"&machineId="+machineId+"&plmasterId="+plmasterId+"&entryDate="+entryDate;
		
		processAjaxCalls("getAllTime.pcs",dataStr,"getAllTimeRecallSuccess","getAllTimeRecallError");
		
	}

	function getAllTimeRecallSuccess(result) {
		showAllTimes(result);
	}
	
	function frmPcsViewcmbCycletimeCavity_onSelect(record)	{
				
		jQuery('#txtActualcycletime').val(record.text);
		
		jQuery('#txtTheoriticalcycletime').val(record.text);
		
		var cavRaw =  record.id.split('##');
			
		jQuery('#txtCavityavailable').val(cavRaw[0]);		
		jQuery('#txtCavityused').val(cavRaw[0]);

		jQuery('#cmbrawmaterialtype').combobox('setValue',cavRaw[1]);
		jQuery('#txtweight').val(cavRaw[2]);
				
		var actTime;	
		if (jQuery('#hdnMode').val() == "Update")
			actTime = (parseFloat(jQuery('#txtcalendartime').val()) +  parseFloat(jQuery('#hdnOldTime').val()));
		else
			actTime = parseFloat(jQuery('#txtcalendartime').val()) ;
		
		setTimeout(function() {jQuery('#txtcalendartime').val(actTime);},550);
	
		var cycleTime = record.text;
		var cavityAvail = record.id;
		
		var planQty = (parseFloat(actTime) / parseFloat(cycleTime)) * parseFloat(cavityAvail) ;		
		jQuery('#txtplannedqty').val(planQty);	
	}
	
	function frmPcsViewcmbPlrkLossid_onSelect(record) {	
		fnLossidOnSelect(record.id);
		if (record.text.substring(0,3)=="8.3") {				
			disableField("frmPcsView","txtPlrkMinutes");				
		}
		else
			jQuery('#txtPlrkMinutes').css('enabled','false');	
		setTimeout(function() {fnGetPendingTime();},550);
			
	}

	function fnLossidOnSelect(id) {
		//alert(id);
		enableFields('cmbPlrkLossid');
		enableFields('cmbPlrkReasonid');
		jQuery('#cmbPlrkReasonid').combobox('clear');
		jQuery('#hdnOldUsedTime').val("0");		
		jQuery('#txtPlrkInstance').val("1");			
		processAjaxCalls('checkIsQtyLoss.pcs','?&lossId='+id,"checkQtyOrLossSuccess");
		
	}
	
	function frmPcsViewcmbPlrkReasonid_onSelect(record) {
		
		enableFields('cmbPlrkCauseid');
		enableFields('txtPlrkInstance');				
		var instance = jQuery('#txtPlrkInstance').val();
		if(instance != null && instance != '' && instance != ' ')
			enableFields('txtPlrkMinutes');		
		jQuery('#cmbPlrkCauseid').combobox('clear');
		var isQtyLoss = jQuery('#hdnIsQtyLoss').val();
		var parentId = record.id;		
		var dataStr = "?&isQtyLoss="+isQtyLoss+"&parentId="+parentId ;

		var lossName = jQuery("#cmbPlrkLossid").combobox('getText');
		
		if(lossName.substring(0,3)=="7.1")
			reloadCombo("frmPcsView","cmbPlrkCauseid","combo_cause.pcs"+dataStr);
		else		
			reloadCombo("frmPcsView","cmbPlrkCauseid","combo_pcsLossCause.pcs"+dataStr);

		jQuery('#lblCause').show();			
		jQuery('#causeLossPCS').show();
		jQuery('#divCauseRootCause').show();
		
		//reloadCombo("frmPcsView","cmbPlrkCauseid","combo_cause.pcs"+dataStr);		
	}
	function frmPcsViewcmbPlrkReasonid_onLoadSuccess() 	{		
		var lossId =jQuery('#cmbPlrkLossid').combobox('getValue');
		if (lossId != "" && lossId != " ") 
			setComboDefaultValue("frmPcsView", "cmbPlrkReasonid");		
	}	
	function  frmPcsViewcmbPlrkCauseid_onSelect(record) {	
		enableFields('cmbPlrkRootcauseid');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');	
	}	
	function  frmPcsViewcmbPlrkRootcauseid_onSelect(record) {	
		enableFields('txaPlrkRemarks');
		enableUIButton('btnLossInsert');
		enableUIButton('btnLossClear');
		enableUIButton('btnLossDelete');		
	}
	
	function fnGetPendingTime() {		
		var pldetailsId = jQuery('#hdnPlrkPldetailid').val();		
		var detailTable = jQuery('#hdnTableName').val();	
		var dataStr= "?&detailTable="+detailTable+"&pldetailsId="+pldetailsId;
		//alert(dataStr);	
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
		
		if(totalPend.indexOf('-')==0)
			jQuery('#txtUnreportedTime').val('0');
		else
			jQuery('#txtUnreportedTime').val(totalPend);
	}
	function showLoss_successCallBack(result) {
		
	}	
	function productionGridError(result)
	{
		alert(result);
		alert("error");
	}
	
	
	function whywhy_Formatter(cellvalue, options, rowObject) {	
		var rowId = options.rowId;
		var formatStr  = '<input id="btn_'+rowId+'"' ;
		formatStr  += ' type="button" class="easyui-button"  align="left" value="..." style="height: 22px;" ';
		formatStr  += ' onClick=showWhyWhy("btn_'+rowId+'") >'; 
		return formatStr;
	}

	function showWhyWhy(id){
				
		var rowId = id.split("_");
		
		if (rowId[1] == null || rowId[1] < 0) {	return; }	
		
		var rowData = jQuery("#pcsLossEntryGrid").jqGrid('getRowData',rowId[1]);
		
		var QirdKeyid=""; 
		var dataString = '?';
		dataString+= '&txtformType=IMT';

		var machId = jQuery('#cmbMachineSelected').combobox('getValue');			
		var phenId=rowData.cmbPlrkReasonid;
		var causeId=rowData.cmbPlrkCauseid;
		var wwmsKeyid="";
		
		dataString+= '&cmbWwmsMachineid='+machId;
		dataString+= '&cmbWwmsPhenomenaid='+phenId;
		dataString+= '&cmbwwmsCauseid='+causeId;

		if(wwmsKeyid != null && wwmsKeyid != '' && wwmsKeyid != ' ')
			dataString+= '&wwmsKeyid='+wwmsKeyid;
		
		if(QirdKeyid != null && QirdKeyid != '' && QirdKeyid != ' ')
			dataString+= '&cmbwwmsRefdocno='+QirdKeyid;
		//navigateToNextForm('whywhy_input.why'+dataString,'Why Why Analysis');
		navigateToNextForm('whywhyanalysismodify_input.why','Why Why Analysis');

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
		fillComboBox("frmPcsView","cmbShiftidSelected","shift.commonFilter?&frmRfilter=yes" );		
	}

	function clearGrid() {
		var dataString = "?&factId=&sectId=&shiftId=&entryDate=&cellId=";				
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
		//jQuery('#cmbShiftid').combobox('clear');
		var factId = "";//jQuery("#frmPcsView input[id='factory']").val();
		var sectId = jQuery("#frmPcsView input[id='section']").val();		
		//reloadCombo("frmPcsView","cmbCellidSelected","cellCombo.commonFilter?&pcsEnabled=Y&factId="+factId+"&sectId="+sectId );
		reloadCombo("frmPcsView","cmbCellidSelected","cellCombo.commonFilter?&pcsEnabled=Y&factId="+factId+"&sectId="+sectId );
		
		//alert(2);
		//reloadCombo("frmPcsView","cmbShiftidSelected","shift.commonFilter"+"?&frmRfilter=yes");

		setCurrentShiftId();
		
		/* setTimeout(function() {
			fnGetTotalResultArray();
			disableUIButton('btnView');
		},1550);	   
		 */
		setTimeout(function() {fnAlignFunLocn();},300);
		   
		
	}
	
	function fnAlignFunLocn()
	{
		if(screen.width >= 1300)		
		{
			jQuery("#frmPcsView div[id=dispFunctionalLoc]").css('width','74%');
			//jQuery('#dispFunctionalLoc').css('width','74%');
		}
	    else
		{
	    	jQuery("#frmPcsView div[id=dispFunctionalLoc]").css('width','95%');
	    	jQuery("#frmPcsView div[id=dispFunctionalLoc]").css('height','13px');
		   //jQuery('#dispFunctionalLoc').css('width','95%');
		   //jQuery('#dispFunctionalLoc').css('height','13px');
		}
	}

	function divPcsView_onClose() {			
		if (checkRejectionExists()==true) {
			fnViewPcs();
			return true;
		}
	}

	function checkRejectionExists() {	
		var lossEntryGrid = jQuery("#pcsLossEntryGrid").jqGrid('getDataIDs');		
		var rejQty = jQuery('#txtrejectedqty').val();		
		var msg ="Are you sure want to Close";		
		var prdId =jQuery('#cmbproductid').combobox('getValue');
		//alert(prdId);					
		if(rejQty != null && rejQty != '' && rejQty != ' ')
		{
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
			  
				if(parseInt(rejQty) > parseInt(q))
				{
					msg = "Rejection Qty is not matching with Rejection Breakup."+msg;
				}
				
			}
			else
			{
				msg = "Rejection Breakup has not Entered."+msg;
			}			
			if  (prdId!="" && prdId != " " && prdId!=null) {				
				if(confirm(msg)) {				 				
					return true;
				}
				else
					return false;
			}
			else
			{
				return true;
			}				
		}
		else
		{
			if  (prdId!="" && prdId != " " && prdId!=null) {
				if(confirm(msg)) { 				
					return true;
				}
				else
					return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	/*	
	function loadPCSPopUp_onClose() {
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

		var detailTable = jQuery('#hdnDetailTable').val();		
		var iCol = jQuery('#hdnSelectCell').val();				
		var Pldetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);		
		var	rowCnt = jQuery("#productionGrid").getGridParam("reccount");
		//var remarks =jQuery("#productionGrid").jqGrid('getCell',rowCnt,iCol);
		
		if (Pldetailsid == "" || Pldetailsid == " ") {			
			alert("To Entry the Remarks, Save the PCS Entry for Machine");			
			return;
		}
		
		processAjaxCalls('getPldRemarks.pcs','?&detailTable='+detailTable+'&Pldetailsid='+Pldetailsid,"getRemarkSuccess");
		
		//alert(remarks);
		/*jQuery('#txaPcsRemarks').val(remarks);
		if (Pldetailsid == "" || Pldetailsid == " ") {			
			alert("To Entry the Remarks, Save the PCS Entry for Machine");			
			return;
		}	
			
		openRemarksDialog();
		*/
		/*jQuery( "#divRemarks" ).show();
		jQuery( "#divRemarks" ).dialog({
			autoOpen: false,
			modal: true,
			height: 250,
			width: 380		
		});*/				
});

	function getRemarkSuccess(result) {
		//alert(result.remarks);
		jQuery('#txaPcsRemarks').val(result.remarks);			
		openRemarksDialog();
	}

	jQuery('#btnOk').click(function() {			
		var iCol = jQuery('#hdnSelectCell').val();				
		var plDetailsid =jQuery("#productionGrid").jqGrid('getCell',2,iCol);
		var remarks = jQuery('#txaPcsRemarks').val();
		var detailTable = jQuery('#hdnDetailTable').val();
	//	alert(remarks);
		processAjaxCalls('updatePldRemarks.pcs','?&detailTable='+detailTable+'&plDetailsid='+plDetailsid+'&remarks='+remarks,"updtRemarkSuccess");				
	});

	function updtRemarkSuccess(result) {
		//alert(result.updtFlg);
		if (result.updtFlg == "1" ) {			
			jQuery('#txaPcsRemarks').val("");
			//jQuery("#divRemarks").dialog("close");
			closeRemarksDialog('divRemarks');
			//refreshForm();
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

<form id="frmPcsView" name="frmPcsView">

<input type="hidden" id="hdnEntryShowCount" name="hdnEntryShowCount"/>
<input type="hidden" id="hdnSelectCell" name="hdnSelectCell"/>
<input type="hidden" id="hdnPlmMasterId" name="hdnPlmMasterId"/>
<input type="hidden" id="hdnDetailTable" name="hdnDetailTable"/>
<input type="hidden" id="hdnMachineId" name="hdnMachineId"/>
<input type="hidden" id="hdnDetailIdTempfield" name="hdnDetailIdTempfield"/>
<input type="hidden" id="hdnProductIdTempfield" name="hdnProductIdTempfield"/>
<input type="hidden" id="hdnProdQtyTempField" name="hdnProdQtyTempField"/>
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />
<input type="hidden" id="hdnFormLock" name="hdnFormLock" value="${requestScope.formLock}" />
<input type="hidden" id="hdnFormAuto" name="hdnFormAuto" value="${requestScope.formAuto}" />
<input type="hidden" id="hdnIsDblClick" name="hdnIsDblClick" value="" />


<div class="easyui-paddingbfpx" id="divMain" style="padding-left: 1%;padding-top:0%; " >
<div style="display:${requestScope.formAuto ? 'none' : 'block'};">
<div style="padding-bottom:1;padding-top:0px; display:none;" id="pcsImageLayer">
	<div style="float:left" id="showFunLocn">
		<img src="images/Down_layer.gif" id="showFLImage" />
	</div>			
	<div style="float:left;display:none;" id="hideFunLocn">
		<img src="images/Up_layer.gif" id="hideFLImage" />
	</div>
</div>
</div>
<div>
 
<table style="width:1200px;display:${requestScope.formAuto ? 'none' : 'block'};" id="pcsViewFields">
	<tr>	
		<td style="width:30%;">
			<div  id="pcsFunLocnDivLayer" class="easyui-paddingbfpx" style=" ">
			<div  class="easyui-paddingbfpx" id="frmPcsViewFuntKeyIds">
					<%-- <input type="hidden" id="factory" name="cmbPrlmFactoryid" value="${requestScope.factId}"  ></input> --%>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbPrlmSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbPrlmCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbPrlmMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbPrlmFlid" value="${requestScope.requestScope.flid}"  ></input>
				</div>
				<div id="pcsefunLocation" style="width:450px; "></div>
			</div>	
		</td >	
		<td style=" ">	
			<div style=" ">
				<label> JH </label>
				<span id="spnlblMachineid" > <label id="lblEquipmentPcsView" style="padding-left: 157;" >Equipment</label> </span>
				<label id="lblDatePcsView" style="padding-left: 161;" >Date</label>
				<label id="lblShiftPcsView" style="padding-left: 70;" >Shift</label>
			</div>
    		<div>
    			<input type="text" id="cmbCellidSelected" name="cmbCellidSelected" class="easyui-combobox" value="${requestScope.cellId}" style="width: 180px;"/>						
				<span id="spnMachineid" style="padding-left:0px; ">
			    	<input type="text" id="cmbMachineSelected" name="cmbMachineSelected" class="easyui-combobox" value="${requestScope.mchId}" style="width: 200px;"/>
			    </span>	    
				<span style="padding-left: ">
			    	<input id="dtedate" name="dtedate" class="easyui-text" readonly="readonly"  style="width: 90px;" value="${requestScope.date}"/>&nbsp;
			    </span>	    
		    	<span style="padding-left: ">
			    	<input type="text" class="easyui-combobox" id="cmbShiftidSelected" name="cmbShiftidSelected" readonly="readonly"  style="width: 40px;"  value="${requestScope.shift}"/>
			    </span>
			   	<span style="padding-left: ;">
			    	<input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style="width:6%;height:20"/>
			    </span>
	    		 <span style="padding-left: ;">
			    	<input type="button" id="btnResult" name="btnResult" class="easyui-button"  value="Result" style="width:8%;height:20"/>
			    </span>
			    <span style="padding-left: ;">
			    	<input type="button" id="btnRemarks" name="btnRemarks" class="easyui-button"  value="Remarks" style="width:9%;height:20"/>
			    </span>
			    <span id="err_cmbMachineid" class="tpm-errormsg"> </span>	    
    		</div>
    		
    		<div>
				<input type="text" value="Header" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />		
				<span style="padding-left: 10px">
					<input type="text" value="Parent Loss" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:90px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:center; " />
				</span>
				
				<span style="padding-left: 10px">
					<input type="text" value="Double Click for Production Entry" disabled="disabled" style="border:1px solid black; font-size:10px ; width:250px;height:20px;color:black;font-weight:bold;text-align:center; " />
				</span>
				<span style="padding-left: 5%">
					<input type="text" value="No Plan" disabled="disabled" style="border:1px solid black; font-size:10px ; width:70px;height:20px;color:black;font-weight:bold;text-align:center;background-color:#b7b7b7; " />
				</span>		
			</div>   
		</td>	
	</tr>	
<!--	<tr><td colspan="2">-->
<!--		<div>-->
<!--			<input type="text" value="Header" disabled="disabled" style="border:1px solid black;  font-size:9px ; width:50px;height:15px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />		-->
<!--			<span style="padding-left: 1%">-->
<!--				<input type="text" value="Parent Loss" disabled="disabled" style="border:1px solid black;  font-size:9px ; width:50px;height:15px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:center; " />-->
<!--			</span>-->
<!--			<span style="padding-left: 1%">-->
<!--				<input type="text" value="Double Click for Production Entry" disabled="disabled" style="border:1px solid black; font-size:9px ; width:224px;height:15px;color:black;font-weight:bold;text-align:center; " />-->
<!--			</span>		-->
<!--			<span style="padding-left: 1%">-->
<!--				<input type="text" value="Press F8 on any loss to Loss Entry" disabled="disabled" style="border:1px solid black; font-size:9px ; width:218px;height:15px;color:black;font-weight:bold;text-align:center; " />-->
<!--			</span>		-->
<!--			<span style="padding-left: 1%">-->
<!--				<input type="text" value="No Plan" disabled="disabled" style="border:1px solid black; font-size:9px ; width:58px;height:15px;color:black;font-weight:bold;text-align:center;background-color:#b7b7b7; " />-->
<!--			</span>		-->
<!--			-->
<!--		</div>-->
<!--	</td></tr>			-->
</table>
</div>

<div style="width:99%;max-width:99%;">
<table style="width:98%;">
	<tr>
	<td id="pcsViewLayer" valign="top" align="left"  style="vertical-align:top;width:49%; ">
		<div style="float:left;margin-top:-5px;">			
				<table id="productionGrid" style=""></table>			
		</div>
	</td>	
	<td id="pcsEntryLayer" align="left"  style="width:49%;vertical-align:top;margin-left: 5px;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;">
		<div style="position:relative;">	
			<div style="padding-bottom:14px;position: absolute;z-index:1;">
				<div style="float:left" id="showEntryLayer">
					<img src="images/layout_button_left.gif" id="showEntry" />
				</div>			
				<div style="float:left;display:none;padding-bottom:2px;" id="hideEntryLayer">
					<img src="images/layout_button_right.gif" id="hideEntry" />
				</div>
				<div>
<!--					<span>-->
<!--						<input type="text" value="" disabled="disabled" id="pcsMchName" name="pcsMchName" style="border:1px solid black;  font-size:11px ; width:50px;height:15px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />-->
<!--					</span>-->
<!--					<span>-->
<!--						<input type="text" value="" disabled="disabled" id="pcsDateLbl" name="pcsDateLbl" style="border:1px solid black;  font-size:11px ; width:50px;height:15px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />-->
<!--					</span>-->
<!--					<span>-->
<!--						<input type="text" value="" disabled="disabled" id="pcsShiftLbl" name="pcsShiftLbl" style="border:1px solid black;  font-size:11px ; width:50px;height:15px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />-->
<!--					</span>-->
				</div>
			</div>
			<div title="Loss Entry" id="divLossEntryNew" style="width:400px;max-width:600px;position:relative;">
			<div class="sub-header" style="margin-top:0px;height:21px\9;"> <label style="padding-left: 20px;">PCS Entry </label><label style="padding-left:10px;" id="pcsMchName"></label><label style="padding-left:10px;" id="pcsDateLbl"></label><label style="padding-left:10px;" id="pcsShiftLbl"></label>
			<span style="position:absolute;float:right;padding-left:6%;padding-left:6%\9;"><img src="images/save_pcs.png"  style="height:17;" id="btnInsert" />
			<img src="images/clear_pcs.png" style="height:17;"  id="btnClear" />
			<img src="images/defaultIcons/Delete-24_1.png" style="height:17;"  id="btnDelete" />
			</span>
			</div>
			<div style="padding-left:1%">
				<label>Asset Avl. Time</label>
				<span>
					<input type="text" id="txtAssttime" name="txtAssttime" value="" disabled="disabled" style="border:1px solid black;  font-size:12px ; width:50px;height:17px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:1px;">Prod. Time</label>
				<span>
					<input type="text" id="txtProdtime" name="txtProdtime" value="" disabled="disabled" style="border:1px solid black;  font-size:12px ; width:50px;height:17px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:1px;">Loss Time</label>
				<span>
					<input type="text" id="txtLosstime" name="txtLosstime" value="" disabled="disabled" style="border:1px solid black;  font-size:12px ; width:50px;height:17px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
				</span>
				<label style="padding-left:1px;">Unrep. Time</label>
				<span>
					<input type="text" value="" id="txtUnreportedtime" name="txtUnreportedtime" disabled="disabled" style="border:1px solid black;  font-size:12px ; width:50px;height:17px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
					<input type="hidden" class="easyui-text" id="txtcalendartime" name="txtcalendartime" readonly="readonly"  style="width: 50px;"  value=""/>
					<input type="hidden" class="easyui-text" id="hdnIsQtyLoss" name="hdnIsQtyLoss" readonly="readonly"  style="width: 50px;"  value=""/>
					<input type="hidden" class="easyui-text" id="hdnLossIdTempField" name="hdnLossIdTempField"/>
					<input type="hidden" class="easyui-text" id="hdnPldetailsid" name="hdnPldetailsid"/>
					<input type="hidden" class="easyui-text" id="hdnPtwokeyid" name="hdnPtwokeyid"/>
					<input type="hidden" class="easyui-text" id="hdnOldTime" name="hdnOldTime"/>
					<input type="hidden" class="easyui-text" id="txtplannedqty" name="txtplannedqty" readonly="readonly"  style="width: 50px;"  value=""/>
									
				</span>
			</div>
			<hr style="color:#A7C9E8;">
			<div style="padding-left:1%">
				<label style="padding-left:0;font-size:11px;">Grade</label><label style="padding-left:92;font-size:11px;">Grade Spec</label>	
				<label class="mandatory-lbl" style="padding-left:70px;font-size:11px;">Product </label>			
			</div>
			<div style="padding-left:1%;" class="easyui-paddingbfpx">
				   <span>
						<input type="text" id="cmbGrade" name="cmbGrade" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 120px;"/>
				  </span>
				  <span>
						<input type="text" id="cmbGradeSpec" name="cmbGradeSpec" class="easyui-combobox" value="${requestScope.PrdModel}" style="width: 120px;"/>
				  </span>		
				   <span style="padding-left:1%;">
		    			<input type="text" id="cmbproductid" name="cmbproductid" class="easyui-combobox" value="${requestScope.PrdId}" style="width: 253px;"/>
		   		  </span>		 
			</div>
			<div style="padding-left:1%;">
				<label class="mandatory-lbl" style="padding-left:0;font-size: 11px;">Prod. Qty </label>
				<span id="lblRejQty"><label class="mandatory-lbl" style="padding-left:14;">Rej Qty </label></span>
				<span style="padding-left:17;font-size: 11px;" id="spnTrimmingLabel" title="Trimming Qty">
					<input type="checkbox" id="chkTrimming" name="chkTrimming" value="Y" > 
					<label>Trim Qty</label>
				</span>
				<span style="padding-left:;font-size: 11px;" id="spnExpansionLabel" title="Expansion Qty">
					<input type="checkbox" id="chkExpansion" name="chkExpansion" value="Y" > 
					<label>Exp.Qty</label>
				</span>				
				<label class="mandatory-lbl" style="padding-left:13px;font-size: 11px;">Wo No. </label>
				<label class="mandatory-lbl" style="padding-left:25px;font-size: 11px;">ThroughPut</label>
				<label class="mandatory-lbl" style="padding-left:6px;font-size: 11px;">Act ThroughPut</label>
				<span id="spnLblCavity">
					<label class="mandatory-lbl" style="padding-left:1px;font-size: 11px;">Cav. Avl. </label>
					<label class="mandatory-lbl" style="padding-left:5px;font-size: 11px;">Cav. Used</label>
				</span>
			</div>
			<div class="easyui-paddingbfpx" style="padding-left:1%;">
				<span>
			    	<input type="text" class="easyui-text" title="Press F2 or Enter to Save" id="txtproducedqty" name="txtproducedqty" style="width: 54px;text-align:right;"  value=""/>
			    </span>
			    <span style="padding-left:1.5%;" id="spnRejQty">
			    	<input type="text" class="easyui-text" id="txtrejectedqty" name="txtrejectedqty" style="width: 54px;text-align:right;"  value=""/>
			    </span>
			    <span style="padding-left:1.5%;" id="spnTrimmingQty">
					<input type="text" class="easyui-text" id="txttrimmingqty" name="txttrimmingqty" style="width: 54px;text-align:right;"  value=""/>	
				</span>	    
				<span style="padding-left:1.5%;" id="spnExpansionQty">						
					<input type="text" class="easyui-text" id="txtexpansionqty" name="txtexpansionqty" style="width: 54px;text-align:right;"  value=""/>			
				</span>	
				
				<span id="spntxtWno" style="padding-left:1%;">				
					<input type="text" id="txtwno" name="txtwno" class="easyui-text" value="" style="width: 54px;"/>
				</span>
				 <span style="padding-left:1%;">
					<input type="text" id="cmbCycletimeCavity" name="cmbCycletimeCavity" class="easyui-combobox" value="" style="width: 60px;text-align:right;"/>
					<input type="hidden" id="txtTheoriticalcycletime" name="txtTheoriticalcycletime" style="width: 70px;"/>
				</span>
				 <span style="padding-left:1%;">
					<input type="text" id="txtActualcycletime" name="txtActualcycletime" class="easyui-text" value="" style="width: 50px;text-align:right;"/>
				</span>
				<span id="spnCavity">
					<span style="padding-left:1%;">					
						<input type="text" id="txtCavityavailable" name="txtCavityavailable" class="easyui-text" style="width: 50px;text-align:right;"/>
					</span>
					 <span style="padding-left:1%;">
						<input type="text" id="txtCavityused" name="txtCavityused" class="easyui-text" value="" style="width: 50px;text-align:right;"/>
					</span>
				</span>
				<span style="padding-left: 110px;">
					<input type="button" id="btnSAPLink" name="btnSAPLink"
			class="easyui-button" value="Fetch from SAP" style="height: 20px;" />
				</span>				
			</div>
			
			<div>
				<span id="lblBackLog"  style="display:none1" title="BackLog.Qty" class="pcsPopUp-acttym">BackLog.Qty</span>		
				<span id="spnBackLogQty"  class="pcsPopUp-pcsFieldsspan" style="display: none1">						
					<input type="text" class="easyui-text" id="txtbacklogqty" name="txtbacklogqty" style="width: 40px;text-align:right;" disabled="disabled"  value=""/>			
				</span>
				<span class="pcsPopUp-lbldate">	
					<label > Raw Material Type</label>
				</span>		    		
				<span class="pcsPopUp-pcsFieldsspan">	
					<input type="text" id="cmbrawmaterialtype" name="cmbrawmaterialtype" class="easyui-combobox" style="width: 110px;"/>
				</span>
				<span "style="padding-left:5px;">
					<label > Weight</label>
				</span>
				<span class="pcsPopUp-pcsFieldsspan">
					<input type="text" class="easyui-text" id="txtweight" name="txtweight" style="width: 40px;text-align:right;"  value=""/>			
				</span>
				<span id="spnNoPlan" class="pcsPopUp-noplanspan">	
					<input type="checkbox" id="chkNoPlan" name="chkNoPlan" value="Y" >
					<label style="font-family:sans-serif; ;font-size: 12px;font-weight: bold;">No Plan</label>
				</span>
				
				<input type="button" id="btnOperatordtls" name="btnOperatordtls" class="easyui-button" value="Operator Details" style="height:20px;"/>
				
			</div>
			
<!--			<div class="easyui-paddingbfpx" style="padding-left:43%;">-->
<!--				<span>-->
<!--					<input type="button" id="btnInsert" name="btnInsert" class="easyui-button" value="Save" style="width: 50px;height:20px;">-->
<!--					<input type="button" id="btnClear" name="btnClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">-->
<!--				</span>-->
<!--			</div>-->
			<input type="hidden" id="hdnFactId" value="${requestScope.factId}"/>
			<input type="hidden" id="hdnDataStr" value=""/>
					<input type="hidden" id="hdnOldUsedTime" name="hdnOldUsedTime">
					<input type="hidden" id="hdnPendingTime" name="hdnPendingTime">
					<input type="hidden" id="hdnLossParentId" name="hdnLossParentId" value="${requestScope.lossParentId}">
					<input type="hidden" id="hdnPlrkKeyid">
					<input type="hidden" id="hdnPlrkPldetailid" name="hdnPlrkPldetailid">
					<input type="hidden" id="hdnLossMode">
				<div class="sub-header" style="width:%;height:21px\9;">Loss Entry <label style="float:right;font-size: 10;padding-right:100px;">Press Tab or Enter on Loss Time to Enter Loss</label>
					<span style="position:absolute;float:right;padding-left:77%;padding-left:84%\9;"><img src="images/save_pcs.png"  style="height:17;" id="btnLossInsert" title="Save Loss"/>
					<img src="images/clear_pcs.png" style="height:17;"  id="btnLossClear" title="Clear Loss"/>
					<img src="images/defaultIcons/Delete-24_1.png" style="height:17;"  id="btnLossDelete" title="Delete Loss"/>
					</span>
				</div>
				<div style="padding-left:1%">
					<label class="mandatory-lbl" style="font-size: 11px;">Loss </label>
					<label class="mandatory-lbl" style="padding-left:232;font-size: 11px;">Reason </label>
				</div>
				<div style="padding-left:1%;" class="easyui-paddingbfpx">
					   <span>
						 <input type="text" id="cmbPlrkLossid" name="cmbPlrkLossid" onKeyPress="return checkIt(event)" class="easyui-combobox" value="" style="width: 253px;"/>
					  </span>
					   <span style="padding-left:1%;">
			    		<input type="text" id="cmbPlrkReasonid" name="cmbPlrkReasonid" class="easyui-combobox" value="" style="width: 253px;"/>
			   		 </span>
				</div>
				<div style="padding-left:1%">
					<label id="lblCause" >Cause</label>
					<label id="lblRootCause" class="mandatory-lbl" style="padding-left: 224px;font-size: 11px;">Root Cause</label>
<!--					<label class="mandatory-lbl">Cause </label>-->
<!--					<label class="mandatory-lbl" style="padding-left:224">Rootcause </label>-->
				</div>
				
				<div id="divCauseRootCause" style="padding-left:1%;" class="easyui-paddingbfpx">
					   <span id="causeLossPCS">
						  <input type="text" id="cmbPlrkCauseid" name="cmbPlrkCauseid" Clear="false" onKeyPress="return checkIt(event)" class="easyui-combobox" value="" style="width: 253px;"/>
					  </span>
					   <span id="rootcauseLossPCS" style="padding-left:1%;">
			    		<input type="text" id="cmbPlrkRootcauseid" name="cmbPlrkRootcauseid" class="easyui-combobox" value="" style="width: 253px;"/>
			   		 </span>
				</div>
				<div style="padding-left:1%">				
					<label class="mandatory-lbl" style="font-size: 11px;">Instance </label>
					<label id="lblTimeOrQty" style="padding-left: 83;font-size: 11px;" class="mandatory-lbl">Time/Qty</label>
<!--					<label class="mandatory-lbl" style="padding-left:78">Time/Qty </label>-->
					<label  style="padding-left:68;font-size: 11px;">Remarks </label>				
				</div>
				<div style="padding-left:1%;" class="easyui-paddingbfpx" >
					   <span style="vertical-align:31px;">
							<input type="text" class="easyui-text" id="txtPlrkInstance" name="txtPlrkInstance" style="width: 122px;text-align:right;"  value=""/>
					  </span>
					   <span style="padding-left:1%;vertical-align:31px;">
							<input type="text" class="easyui-text" id="txtPlrkMinutes" name="txtPlrkMinutes" style="width: 122px;text-align:right;"  value=""/>	
					   </span>
					   <span style="padding-left:1.3%;">	
			    			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea"  style="width:253px;height:50px;resize:none;"  id="txaPlrkRemarks" name="txaPlrkRemarks"></textarea>
			   		 </span>
					 <span style="padding-left:1%;padding-top:0px; vertical-align:0px;">
					   	<label style="float:left;font-size: 10;"> Double click on Loss to Modify or Delete </label>	    
					 </span>
			   		 
				</div>
<!--				<div style="padding-left:44%;">	-->
<!--					<span>-->
<!--						<input type="button" id="btnLossInsert" name="btnLossInsert" class="easyui-button" value="Save" style="width: 50px;height:20px;">-->
<!--						<input type="button" id="btnLossClear" name="btnLossClear" class="easyui-button" value="Clear" style="width: 50px;height:20px;">-->
<!--					</span>    -->
<!--				</div>-->
				<div style="margin-left:1%;">
					<div  style="float:left;">			
							<table id="pcsLossEntryGrid" style="float:left;"></table>
					</div>	
				</div>			
			</div>
		</div>
	</td>
	</tr>
	</table>
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
</form>