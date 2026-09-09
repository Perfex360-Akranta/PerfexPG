<script type="text/javascript">
	jQuery(document).ready(function(){
	
		initialiseForm('frmIntRejEntry');
		jQuery('#submitForm').val('frmIntRejEntry');
		
		fillComboBox("frmIntRejEntry","cmbMachineNo","machineCombo.commonFilter" );	
		numericTextBox('txtQirmInspectionqty');
		formatDateBox('dteQirmInspectiondate','dd-MMM-yyyy');	
		fnFillProcessGrid();
		readOnlyFields("txtQirmInspectionqty");

		var reportType =jQuery('#hdnReportType').val();		
		if (reportType!="DIR")
			jQuery('#filter').css('display','block');

		var acceptQty = jQuery('#txtQirmAcceptedqty').val();
		if (acceptQty=="" || acceptQty == " ") {
			jQuery('#txtQirmAcceptedqty').val("0");
		}

		var rejQty = jQuery('#txtQirmBacklogqty').val();
		if (rejQty=="" || rejQty == " ") {
			jQuery('#txtQirmBacklogqty').val("0");
		}

		processAjaxCalls('setFormActionMode.ire','?q=2&mode=create',"formModeSuccess");
	});
jQuery('#btnSave').click(function(){
	var rejGrid = convertIntRejGridToJSONArr('intRejEntryGrid');	
	var url = "internalRejection_save.ire?q=2&rejGrid="+rejGrid ;
	url+= "&saveMode=create";
	var reportType = jQuery('#hdnReportType').val();
	var qihbKeyid = jQuery('#hdnQihbKeyid').val();	
	url+= "&reportType="+reportType;
	url+= "&qihbKeyid="+qihbKeyid;
	 var filterProcessId = jQuery('#cmbProcessid').combobox('getValue');			 
	 var filterPhenomenaId = jQuery('#cmbPhenomenaid').combobox('getValue');
	url+= "&filterProcessId="+filterProcessId+"&filterPhenomenaId="+filterPhenomenaId;
	var formId = jQuery('#submitForm').val();	
	if(formId.length > 0 )
		saveForm(formId,url);	
});
function convertIntRejGridToJSONArr(jqGridId){
	
	//url+= "&QirmKeyid="+QirmKeyid+"&QirdKeyid="+QirdKeyid+"&PlrkKeyid="+PlrkKeyid;
	//url+= "&Qird4mtype="+Qird4mtype+"&QirdType="+QirdType+"&QirdRemarks="+QirdRemarks;
	//url+= "&processId="+processId+"&phenId="+phenId+"&causeId="+causeId+"&inspectedQty="+inspectedQty+"&QirdWwmasterid="+yyId;
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var jsonArrO='[';	
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var flag = true;
		var detailId = row['txtQirdKeyid'];	
		
		var quantity = '';	
		if(row['txtQirdQuantity'].substring(0,6)!='<input' )
		{
			quantity = row['txtQirdQuantity'];
		}
		else
		{
			var qty=row['txtQirdQuantity'].indexOf("id=")+4;
			var qnty=row['txtQirdQuantity'].substring(qty);
			var q = qnty.indexOf('"');		
			quantity = jQuery('#'+qnty.substring(0,q)).val();
		}
		
		if((quantity == '' || quantity == ' ') && (detailId == '' || detailId == ' '))
			flag = false;
		if(quantity == '0')
			flag = false;
		if(flag)
		{			
			jsonArrO += '{';
			for(var colName in row) {
			
				if(colName != 'chkSelectType' && colName != 'btnSave')
				{
				
					if(row[colName].substring(0,6)!='<input' && row[colName].substring(0,7)!='<select' && row[colName].substring(0,9)!='<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"')
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
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
		
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
	return jsonArrO; 
}
	function frmIntRejEntrycmbMachineNo_onLoadSuccess() 	{
		fillComboBox("frmIntRejEntry","cmbQirmInspectionid","employee.commonFilter" );
	}

	function frmIntRejEntrycmbQirmInspectionid_onLoadSuccess() 	{
		var factId = jQuery("#frmIntRejEntry input[id='factory']").val();
		fillComboBox("frmIntRejEntry","cmbQirmInspectedshiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
	}

	function frmIntRejEntrycmbQirmInspectedshiftid_onLoadSuccess() {
		var factId = jQuery("#frmIntRejEntry input[id='factory']").val();
		var machId = jQuery('#cmbMachineNo').combobox('getValue');
		var entryDate = jQuery('#hdnQirmEntrydate').val();
		var dataStr = "&factId="+factId+"&prdModelId=+&mchId="+machId+"&entryDate="+entryDate;
		fillComboBox("frmIntRejEntry","cmbQirmProductid","combo_product.pcs?q=2"+dataStr);	
	}

	function frmIntRejEntrycmbQirmProductid_onLoadSuccess() {		
		fillComboBox("frmIntRejEntry","cmbProcessid","combo_process.ire");			
	}
	function frmIntRejEntrycmbProcessid_onLoadSuccess() {		
		var dataStr ="?q=2&processId="+jQuery('#cmbProcessid').combobox('getValue');
		fillComboBox("frmIntRejEntry","cmbPhenomenaid","combo_Phenomena.ire"+dataStr );			
	}
	function frmIntRejEntrycmbProcessid_onSelect(record) {
		var dataStr ="?q=2&processId="+jQuery('#cmbProcessid').combobox('getValue');
		reloadCombo("frmIntRejEntry","cmbPhenomenaid","combo_Phenomena.ire"+dataStr );
		fnFillProcessGrid();
	}
	function frmIntRejEntrycmbPhenomenaid_onSelect(record) {
		fnFillProcessGrid();
	}
	
	
	function fnFillProcessGrid() {		
		var dataString="?q=2";
		var date = jQuery('#txtQirmProductiondate').val();
		var machId = jQuery('#cmbMachineNo').combobox('getValue');
		var prdId = jQuery('#cmbQirmProductid').combobox('getValue');
		var QirmKeyid=jQuery('#hdnQirmKeyidEntry').val();
		var processId = jQuery('#cmbProcessid').combobox('getValue');
		var phenId =jQuery('#cmbPhenomenaid').combobox('getValue');		
		var masterId =jQuery('#hdnQirmPlmasterid').val();
		var pldetailsId =jQuery('#hdnQirmPldetailid').val();
		if(masterId=="" || masterId==" ") masterId="{}";
		dataString+= "&condParam=PLDETAILSID="+pldetailsId+";MACHINEID="+machId+";DATE="+date+";MASTERID="+masterId+";ENTRYTYPE=D";
		dataString+=";PROCESSID="+processId+";PHENOMENAID="+phenId;
		dataString+= ";PRODUCTID="+prdId;
		var reportType =jQuery('#hdnReportType').val();
		//reportType = (reportType === 'DIR') ? 'D' : reportType;
		//reportType="QAH";
		dataString+= ";REPORTYPE="+reportType;
		dataString+= ";QIRMKEYID="+QirmKeyid;
		var qihbKeyid = jQuery('#hdnQihbKeyid').val();
		dataString+= ";QIHBKEYID="+qihbKeyid;
		//alert(dataString);
		processGridnew('intRejEntryGrid_view.ire',dataString,"intRejEntryGrid","intRejEntryPager","Internal Rejection Entry","intRejEntrydblClick","","intRejEntryGrid_loadComplete","intRejEntryGridError");		
	}

	function intRejEntryGrid_loadComplete() {	

		processAjaxCalls("getIsHourlyEntry.ire","","isHourlyRecallSuccess","isHoyrlyRecallError");
			
		var rowIds = jQuery("#intRejEntryGrid").getDataIDs();		
		for(var id = 1; id<=rowIds.length; id++) {			
			numericTextBox('txtQty_'+id);
			jQuery('#txtQty_'+id).css("text-align", "center");
			//disableUIButton("btn_"+id);
			var QirdType=jQuery('#cmbType_'+id).val();
						
			if (QirdType=="B")
				disableUIButton("btn_"+id);
			else
				enableUIButton("btn_"+id);

			jQuery("#intRejViewGrid").jqGrid('hideCol',"btnSave");
			
			var rowData = jQuery("#intRejEntryGrid").jqGrid('getRowData',id);
			jQuery("#intRejEntryGrid").jqGrid('setCell',id,"txtQirdQuantity","","",{'title':''});
			jQuery("#intRejEntryGrid").jqGrid('setCell',id,"cmbQird4mtype","","",{'title':''});
			jQuery("#intRejEntryGrid").jqGrid('setCell',id,"cmbQirdType","","",{'title':''});
			jQuery("#intRejEntryGrid").jqGrid('setCell',id,"txaQirdRemarks","","",{'title':''});
			var yyId = rowData.txtQirdWwmasterid;
			//alert(yyId);
			if (!(yyId=="" || yyId==" ")) {
				disableField("frmIntRejEntry","cmb4mType_"+id);
				disableField("frmIntRejEntry","cmbType_"+id);
				disableField("frmIntRejEntry","txaRemarks_"+id);				
			}			
		}					
	}

	function isHourlyRecallSuccess(result) {		
		if (result.value=="Y")		
			jQuery("#intRejEntryGrid").jqGrid('hideCol',"btnSave");
	}
						
	
	function intRejEntryGridError() {
		alert("Error");
	}

	function txaRemaksFormatter(id, options, rowObject){
		var id = options.rowId;
		//return '<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txaRemarks_'+id +'" name="txaRemarks_'+id +'" maxlength="495" style="width: 160px;height: 40px" rows="4" cols="1" ">'+rowObject[11]+'</textarea>';
		return '<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txaRemarks_'+id +'" name="txaRemarks_'+id +'" maxlength="495" style="width: 160px;height: 40px" rows="4" cols="1">'+rowObject[11]+'</textarea>';
		 //<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txampcsActivity" name="txampcsActivity" >				
	}

	function txtQtyFormatter(id, options, rowObject){
		var id = options.rowId;
		var reportType =jQuery('#hdnReportType').val();
		var lock="";
		//alert(reportType);
		if (reportType=="DIR")
			lock=" disabled ";		
		//alert(rowObject[7]);
		var rowVal = rowObject[7];
		if(rowVal == '0')
			rowVal = '';
		return '<input  onclick="gotFocuse('+id +')" onchange="lostFocuse('+id +')" id="txtQty_'+id +'" name="txtQty_'+id +'" '+lock+' value="'+rowVal +'" style="border:0px;width:50px;" type="text" class="easyui-text"  "/>';
				
	}
	
	function gotFocuse(id) {
		jQuery('#txtQty_'+id).keydown(function(event) {
			if(event.keyCode == 110)
				 event.preventDefault();  
		});
		var oldQty=jQuery('#txtQty_'+id).val().trim();
		if (oldQty=="" || oldQty==" ") oldQty="0";
		jQuery('#hdnOldQty').val(oldQty);
		//alert(oldQty);
	}
	
	function lostFocuse(id) {
		var acceptQty=jQuery('#txtQirmAcceptedqty').val();
		var rejQty=jQuery('#txtQirmBacklogqty').val();
		var inspectedQty=jQuery('#txtQirmInspectionqty').val();
		var qty=jQuery('#txtQty_'+id).val().trim();
		var oldQty=jQuery('#hdnOldQty').val();
		
		if(qty == '0' || qty.substring(0,1)== '0' || parseInt(qty) == 0)
		{
			if (oldQty=="0") oldQty="";			
			jQuery('#txtQty_'+id).val(oldQty);
			alert("Value Should Not Be Zero");	
			return false;
		}
		if (oldQty=="" || oldQty==" ") oldQty="0";
		if (qty=="" || qty==" ") qty="0";
		//alert('oldQty'+oldQty);
		qty =parseInt(qty)-parseInt(oldQty);		
		//alert('qty'+qty);
		//alert('inspectedQty'+inspectedQty);
		var totRej = parseInt(qty) + parseInt(rejQty);
		var totAccept =  parseInt(inspectedQty) - parseInt(totRej);
		//alert(totAccept);

		if (parseInt(totAccept)<0) {
			if (oldQty=="0") oldQty="";			
			jQuery('#txtQty_'+id).val(oldQty);
			alert("Rejected Qty should be less than Inspected Qty");	
			return false;
		}
			
		jQuery('#txtQirmBacklogqty').val(totRej);
		jQuery('#txtQirmAcceptedqty').val(totAccept);
	}

	function cmb4mTypeFormatter(id, options, rowObject){
		var id = options.rowId;
		//return '<input id="cmb4mType_'+id +'" type="select"  "/>';
		var comboBox = "<select   id='cmb4mType_"+id+"' style='width:80px;' name='cmb4mType_"+id+"'class='easyui-combobox' value='Y'>";			
	//	if(rowObject[3]== comboVal[i])
	//		comboBox += "<option selected='selected' value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
	//	else
	
		var selectedMen=""; var selectedMethod=""; var selectedMaterial=""; var selectedMachine="";
		if (rowObject[9]=="MEN") selectedMen =" selected='selected' "; 
		if (rowObject[9]=="METHOD") selectedMethod =" selected='selected' ";
		if (rowObject[9]=="MATERIAL") selectedMaterial =" selected='selected' ";
		if (rowObject[9]=="MACHINE") selectedMachine =" selected='selected' ";
	
		comboBox += "<option  value=' '>  </option>"; 
		comboBox += "<option  value='MEN'  " + selectedMen + ">MEN</option>";
		comboBox += "<option  value='METHOD' " + selectedMethod + ">METHOD</option>";
		comboBox += "<option  value='MATERIAL' " + selectedMaterial + ">MATERIAL</option>";
		comboBox += "<option  value='MACHINE' " + selectedMachine + ">MACHINE</option>";
		comboBox += "</select>";		
		return comboBox;
	}

	function cmbTypeFormatter(id, options, rowObject){
		var id = options.rowId;
	//	return '<input id="cmbType_'+id +'" type="select"  "/>';
		var comboBox = "<select  onblur='yyfocuse("+id +")'  id='cmbType_"+id+"' style='width:60px;' name='cmbType_"+id+"'class='easyui-combobox' value='Y'>";			
		//	if(rowObject[3]== comboVal[i])
		//		comboBox += "<option selected='selected' value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
		//	else
		var selectedA=""; var selectedB=""; var selectedC="";
		if (rowObject[10]=="A") selectedA =" selected='selected' "; 
		if (rowObject[10]=="B") selectedB =" selected='selected' ";
		if (rowObject[10]=="C") selectedC =" selected='selected' ";

		comboBox += "<option  value=' '> </option>"; 
		comboBox += "<option  value='A' " + selectedA + "> A </option>";
		comboBox += "<option  value='B' " + selectedB + "> B </option>";
		comboBox += "<option  value='C' " + selectedC + "> C </option>";
		comboBox += "</select>";		
		return comboBox;
	}	
	
	function btn_YYFormatter(id, options, rowObject){
		var id = options.rowId;
		//return '<input id="btn_'+id +'" type="button" '+ (rowObject[15]=="Y" ? 'checked':'') + ' class="easyui-button"  onclick="openYY(\''+id + '\');"/>';
		return '<input id="btn_'+id +'" name="btn_'+id +'" type="button" value="." class="easyui-button"  onclick="openYY(\''+id + '\');"/>';
		  	
	}
	function frmIntRejEntry_beforeSubmit()
	{
		var url= "&saveMode=create";		
		var reportType = jQuery('#hdnReportType').val();
		var qihbKeyid = jQuery('#hdnQihbKeyid').val();	
		url+= "&reportType="+reportType;
		url+= "&qihbKeyid="+qihbKeyid;		
		var filterProcessId = jQuery('#cmbProcessid').combobox('getValue');			 
		var filterPhenomenaId = jQuery('#cmbPhenomenaid').combobox('getValue');		 
		url+= "&filterProcessId="+filterProcessId+"&filterPhenomenaId="+filterPhenomenaId;				
		return 'rejGrid='+convertIntRejGridToJSONArr('intRejEntryGrid')+url;
	
	}
	function yyfocuse(id) {		
		var QirdType=jQuery('#cmbType_'+id).val();
		if (QirdType=="B")
			disableUIButton("btn_"+id);
		else
			enableUIButton("btn_"+id);
	}
	

	function btnSaveFormatter(id, options, rowObject){
		var id = options.rowId;
	  	return '<input type="button" class="easyui-button" style="width:40px;height:21px;height:18px\9" value="Save" onclick="saveData('+id +');"/>';  	
	}
	
	function openYY(id) {
	
		//if(jQuery('#btn_'+id).is(':checked') == true) {
			if (checkInspectedDetail(id) == false)
				return;	

			var formId = jQuery('#submitForm').val();
			var rowData = jQuery("#intRejEntryGrid").jqGrid('getRowData',id);
			var QirmKeyid=jQuery('#hdnQirmKeyidEntry').val();
			var machId = jQuery('#cmbMachineNo').combobox('getValue');			

			if 	(QirmKeyid=="" || QirmKeyid==" ") 	
			 QirmKeyid=rowData.txtQirdMasterid;
			  
			var QirdKeyid=rowData.txtQirdKeyid; 
			var PlrkKeyid=rowData.cmbPlrkKeyid;
			
			var Qird4mtype=jQuery('#cmb4mType_'+id).val(); 
			var QirdType=jQuery('#cmbType_'+id).val(); 
			var QirdRemarks= jQuery('#txaRemarks_'+id).val().trim();

			var processId=rowData.cmbQirdProcessid;
			var phenId=rowData.cmbQirdPhenomenaid;
			var causeId=rowData.cmbQirdCauseid;
			//var inspectedQty=rowData.txtQpreQuantity;
			var	inspectedQty = jQuery('#txtQty_'+id).val().trim();
			//alert(inspectedQty);
			var yyId = rowData.txtQirdWwmasterid;
			
			var url = "internalRejection_save.ire?q=2" ;
			url+= "&saveMode=createYY&saveFrom=yy";
			url+= "&QirmKeyid="+QirmKeyid+"&QirdKeyid="+QirdKeyid+"&PlrkKeyid="+PlrkKeyid;
			url+= "&Qird4mtype="+Qird4mtype+"&QirdType="+QirdType+"&QirdRemarks="+QirdRemarks;
			url+= "&processId="+processId+"&phenId="+phenId+"&causeId="+causeId+"&inspectedQty="+inspectedQty+"&QirdWwmasterid="+yyId;//+'&cmbWwmsMachineid='+machId;
			//alert(url);
			
			var reportType = jQuery('#hdnReportType').val();
			var qihbKeyid = jQuery('#hdnQihbKeyid').val();
			//alert(reportType);
			url+= "&reportType="+reportType;
			url+= "&qihbKeyid="+qihbKeyid;

			var filterProcessId = jQuery('#cmbProcessid').combobox('getValue');			 
			var filterPhenomenaId = jQuery('#cmbPhenomenaid').combobox('getValue');
			url+= "&filterProcessId="+filterProcessId+"&filterPhenomenaId="+filterPhenomenaId;
		//alert(1);
		navigateToNextForm('whywhy_input.why','Why Why Analysis');	
			//if(formId.length > 0 )
//				saveForm(formId,url);
				//navigateToNextForm('whywhy_input.why','Why Why Analysis',"","" );
			/*var rowData = jQuery("#intRejEntryGrid").jqGrid('getRowData',id);
			var QirdKeyid=rowData.txtQirdKeyid; 
			var dataString = '?q=2';
			dataString+= '&txtformType=IMT';

			var machId = jQuery('#cmbMachineNo').combobox('getValue');			
			var phenId=rowData.cmbQirdPhenomenaid;
			var causeId=rowData.cmbQirdOriginalid;
			var wwmsKeyid=rowData.txtWwmsKeyid;
			
			dataString+= '&cmbWwmsMachineid='+machId;
			dataString+= '&cmbWwmsPhenomenaid='+phenId;
			dataString+= '&cmbwwmsCauseid='+causeId;

			if(wwmsKeyid != null && wwmsKeyid != '' && wwmsKeyid != ' ')
				dataString+= '&wwmsKeyid='+wwmsKeyid;
			
			if(QirdKeyid != null && QirdKeyid != '' && QirdKeyid != ' ')
				dataString+= '&cmbwwmsRefdocno='+QirdKeyid;
			navigateToNextForm('whywhy_input.why'+dataString,'Why Why Analysis');*/
			//LoadPopUp("loadYYPopup", "whywhy_input.why"+dataString,  true,"95%","94%","1px","1px", "showYY_successCallBack","Why Why Analysis",true,true);
		//}
	}
	function saveData(id) {		
		if (checkInspectedDetail(id) == false)
			return;	

		var formId = jQuery('#submitForm').val();
		var rowData = jQuery("#intRejEntryGrid").jqGrid('getRowData',id);
		var QirmKeyid=jQuery('#hdnQirmKeyidEntry').val();

		if 	(QirmKeyid=="" || QirmKeyid==" ") 	
		 QirmKeyid=rowData.txtQirdMasterid;
		  
		var QirdKeyid=rowData.txtQirdKeyid; 
		var PlrkKeyid=rowData.cmbPlrkKeyid;
		
		var Qird4mtype=jQuery('#cmb4mType_'+id).val(); 
		var QirdType=jQuery('#cmbType_'+id).val(); 
		var QirdRemarks= jQuery('#txaRemarks_'+id).val().trim();
		var yyId = rowData.txtQirdWwmasterid;
		var processId=rowData.cmbQirdProcessid;
		var phenId=rowData.cmbQirdPhenomenaid;
		var causeId=rowData.cmbQirdCauseid;
		//var inspectedQty=rowData.txtQpreQuantity;
		var inspectedQty = jQuery('#txtQty_'+id).val().trim();
		
		var url = "internalRejection_save.ire?q=2" ;
		url+= "&saveMode=create";
		url+= "&QirmKeyid="+QirmKeyid+"&QirdKeyid="+QirdKeyid+"&PlrkKeyid="+PlrkKeyid;
		url+= "&Qird4mtype="+Qird4mtype+"&QirdType="+QirdType+"&QirdRemarks="+QirdRemarks;
		url+= "&processId="+processId+"&phenId="+phenId+"&causeId="+causeId+"&inspectedQty="+inspectedQty+"&QirdWwmasterid="+yyId;

		var reportType = jQuery('#hdnReportType').val();
		var qihbKeyid = jQuery('#hdnQihbKeyid').val();
		//alert(reportType);
		url+= "&reportType="+reportType;
		url+= "&qihbKeyid="+qihbKeyid;

		 var filterProcessId = jQuery('#cmbProcessid').combobox('getValue');			 
		 var filterPhenomenaId = jQuery('#cmbPhenomenaid').combobox('getValue');
		url+= "&filterProcessId="+filterProcessId+"&filterPhenomenaId="+filterPhenomenaId;
		//alert(url);
		if(formId.length > 0 )
			saveForm(formId,url);		
	}

	function frmIntRejEntry_successsCallback(result) {	
		var mode = result.openForm;
		 if (jQuery('#hdnQirmKeyidEntry').val()=="" || jQuery('#hdnQirmKeyidEntry').val()==" ")
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
			jQuery("#intRejEntryGrid").jqGrid().trigger("reloadGrid");
			}
	}
		
	function checkInspectedDetail(id) {		
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

		var productDt = jQuery('#txtQirmProductiondate').val();
		var inspectDt = jQuery('#dteQirmInspectiondate').datebox('getValue');
				
		if( compareDateTime(productDt,inspectDt) < 0 ) {		
			alert("Inpsection Date should be greater than or equal to Production Date");		return false;
		}

		var reportType =jQuery('#hdnReportType').val();
		//alert(reportType);
		if (reportType!="DIR") {
			//var id = jQuery("#intRejEntryGrid").jqGrid('getGridParam', 'selrow');		
			var enterQty = jQuery('#txtQty_'+id).val().trim();
			
			if (enterQty =="" )  {
				alert("Enter Qty");		return false;			
			}
			var inspectedQty = jQuery('#txtQirmInspectionqty').val();
			var totRej = jQuery('#txtQirmBacklogqty').val();
			
			if (parseInt(totRej) > parseInt(inspectedQty) )  {
				alert("Rejected Qty should be less than Inspected Qty");		return false;			
			}
		}
		return true;
	}

	function loadIntRejEntryPopup_onClose() {
		refreshForm();
		return true;		
	}
</script>

<form id="frmIntRejEntry" >
<div id="wrapper" style="width:100%;">
<div class="easyui-paddingbfpx" style="padding-left: 10px;padding-top:10px; width:99%" >

<div> <input type="hidden" id="hdnReportType" name="hdnReportType" value="${requestScope.reportType}"></div>

	<div> <input type="hidden" id="hdnOldQty" name="hdnOldQty"></div>
	<input type="hidden" id="mode" name="mode" >
	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
	<div> <input type="hidden" id="hdnQirmKeyidEntry" name="hdnQirmKeyidEntry" value="${requestScope.QirmKeyid}"></div>
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
		<label class="mandatory-lbl">Machine No</label>
		<label class="mandatory-lbl" style="padding-left: 155px">Product</label>
		<label class="mandatory-lbl" style="padding-left: 105px; padding-left: 106px\9">Total Produced</label>
		<label class="mandatory-lbl" style="padding-left: 25px">Product Date</label>
		<label class="mandatory-lbl" style="padding-left: 25px;padding-left: 27px">Accepted Qty</label>
		<label class="mandatory-lbl" style="padding-left: 25px;padding-left: 23px">Rejected Qty</label>
	</div>
	<div >		
		<input type="text" id="cmbMachineNo" name="cmbMachineNo" disabled value="${requestScope.machId}" style="width: 210px;"/>					
		<span style="padding-left: 10px">
			<input id="cmbQirmProductid" name="cmbQirmProductid"  class="easyui-combobox" type="text" value="${requestScope.PrdId}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:140px;/* height:20px; */color:black;font-weight:bold;text-align:center; " />
		</span>
		<span style="padding-left: 10px">		
			<input id="txtQirmTotalproduction" name="txtQirmTotalproduction" type="text" value="${requestScope.totalProduced}"  disabled="disabled" style="text-align:right;border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;text-align:center; " />
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

<!--	-->
	<div class="easyui-paddingbfpx" style="padding-top: 0px">
		<label class="mandatory-lbl">Inspected By</label>
		<label class="mandatory-lbl" style="padding-left: 148px">Inspected Date</label>
		<label class="mandatory-lbl" style="padding-left: 64px;padding-left: 66px">Inspected Shift</label>
		<label class="mandatory-lbl" style="padding-left: 25px">Total Inspected Qty</label>		
	</div>
	
	<div >		 
		<input type="text" class="easyui-combobox" id="cmbQirmInspectionid" name="cmbQirmInspectionid" value="${requestScope.inspectedBy}" style="width: 210px;"/>					
		<span style="padding-left: 10px">
			<input class="easyui-text" id="dteQirmInspectiondate" name="dteQirmInspectiondate" type="text" value="${requestScope.inspectedDate}" style="width:140px;height:20px;color:black;" />
		</span>
		<span style="padding-left: 10px">
			<input type="text" class="easyui-combobox" id="cmbQirmInspectedshiftid" name="cmbQirmInspectedshiftid" readonly="readonly"  style="width: 90px;"  value="${requestScope.inspectedShift}"/>
		</span>
		<span style="padding-left: 20px">
			<input type="text" class="easyui-text" id="txtQirmInspectionqty" name="txtQirmInspectionqty" style="width: 85px;text-align:right;"  value="${requestScope.inspectedQty}"/>
		</span>
	</div>	
	
	<div id="filter" style="display: none;">
	<div class="easyui-paddingbfpx" style="padding-top: 0px">
		<label class="mandatory-lbl">Process</label>
		<label class="mandatory-lbl" style="padding-left: 173px">Phenomena</label>
	</div>
	
	<div >		 
		<input type="text" class="easyui-combobox" id="cmbProcessid" name="cmbProcessid" value="${requestScope.processId}" style="width: 210px;"/>					
		<span style="padding-left: 10px">
			<input class="easyui-text" id="cmbPhenomenaid" name="cmbPhenomenaid" type="text" value="${requestScope.phenomenaId}" style="width:140px;" />
		</span>
<!--		<span style="padding-left: 10px">-->
<!--			<input type="button" value="Save" id="btnSave" class="easyui-button" >-->
<!--		</span>-->
	</div>	
	</div>
	
	<div style="" class="internalRejectionEntryDiv">			
			<table id="intRejEntryGrid" style="float: left: ;"></table>
	</div>	
	
	</div>
	</div>
	</div>
</form>