<script>
jQuery(document).ready(function(){
	//alert(1);
	 initialiseForm('frmabnAllocation');
	var url = jQuery('#hiddenUrl').val();
	//alert(2 + url );
	jQuery('#submitForm').val('frmabnAllocation');
	//alert(3 );
	 setLoadFormCallBackFrmId("frmabnAllocation");
	 invokeAfterLoadFormCallBack();
	 
	 //alert(4);
	//viewGrid(url, "?q=2&cmbdetectedBy="+jQuery("#hdnLoginId").val());
	// processGridnew(url, "?q=2&cmbdetectedBy="+jQuery("#hdnLoginId").val(), "tblAbnAllocation", "abnAllocationPager", "", "", "","load_complete","selectRowFunction");
	//viewGrid(url, "?q=2&cmbdetectedBy="+jQuery("#hdnLoginId").val());
});
function frmabnAllocation_afterLoadCallBack(){
	//alert(5);
	toggleCommonFilter(); 
	}
function load_complete(){ 
	formatDateBoxWithGrid("dteAbnmTargetDate_",'dd-MMM-yyyy');		
	fillComboBoxWithGrid("frmabnAllocation","cmbAbnmTrade_","Combo_Trade.abnForm");
	fillComboBoxWithGrid("frmabnAllocation","cmbAbnmResponse_","employee.commonFilter");
	
}
function viewGrid(url,filterString)
{
	/*var urlArr = url.split('?');*/
	
	if( validateFilterSelection(filterString))
	{					
		filterString+="&cmbdetectedBy="+jQuery("#hdnLoginId").val();
		processGridnew(url,filterString , "tblAbnAllocation", "abnAllocationPager", "", "", "","load_complete","selectRowFunction");
		
		return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	return  true;
}

 function formattorTargetDate(cellVal, options, rowObject)
{
	return '<input id="dteAbnmTargetDate_'+options.rowId+'" name="dteAbnmTargetDate_"'+options.rowId+'" style="width:100px;"  class="easyui-datebox" value="" onchange="onkeyup('+options.rowId+')"/>';
}
function formattorTrade(cellVal, options, rowObject)
{
	return '<input id="cmbAbnmTrade_'+options.rowId+'" name="cmbAbnmTrade_"'+options.rowId+'" style="width:116px;"  class="easyui-combo" value=""/>';
}
function formattorRespons(cellVal, options, rowObject)
{
	return '<input id="cmbAbnmResponse_'+options.rowId+'" name="cmbAbnmResponse_"'+options.rowId+'" style="width:200px;"  class="easyui-combo" value=""/>';
} 
function validateDate(rowid)
{
	
	var tarDate = jQuery("dteAbnmTargetDate_"+rowid).datebox("getValue");
	
}
function tblAbnAllocation_selectRow(id){
	if(jQuery('#jqg_tblAbnAllocation_'+id).is(':checked')){
		chkboxCheck(id);
	}
	else{
		chkboxUnCheck(id);
	}
}
function tblAbnAllocation_selectAll(id,status){
	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}


function tblAbnAllocationcmbAbnmTrade_onSelect(record)
{	
	//alert(record.text);
	var rowId = jQuery("#tblAbnAllocation").jqGrid('getGridParam','selrow');
	var rowData = jQuery("#tblAbnAllocation").jqGrid('getRowData',rowId);
	var targetDate = jQuery("#tblAbnAllocationdteTargetDate_"+rowId).datebox("getValue");
	var detectedDate = rowData.DETECDT;
	detectedDate = detectedDate+" 00:00";
	targetDate = targetDate+" 00:00";
	 var cellid = rowData.CELL_KEYID;
	
	 var oldTrade = jQuery("#hdnTradeID").val();
	 
	 if(oldTrade != record.id)
	 {
		 
		 
		 jQuery("#tblAbnAllocationdteTargetDate_"+rowId).datebox('clear');
		 jQuery("#tblAbnAllocationcmbAbnmResponse_"+rowId).combobox('setValue',"");
		 
		 jQuery("#tblAbnAllocationdteTargetDate_"+rowId).datebox("disable");		 
		 jQuery("#tblAbnAllocationcmbAbnmResponse_"+rowId).combobox("disable");
		 
		 
		 jQuery("#tblAbnAllocation").jqGrid('setCell',rowId,"EMPID","{}");
		 return false;
	 }
	 else{
		 jQuery("#tblAbnAllocationdteTargetDate_"+rowId).datebox("enable");		 
		 jQuery("#tblAbnAllocationcmbAbnmResponse_"+rowId).combobox("enable");
		 
	 }
	/* 
	if((detectedDate)>=convertStringToDate(targetDate))
		{
		alert("23");
		} */
	if((detectedDate) > (targetDate))
	{
		//showValidationErrorMsg('dteAbnmenddate','Should Not Exceed Current Date/Time');
//		alert("Proposed Target Date will be Greater Than Detected Date! ");
		//popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	}
	
	var location = jQuery("#frmEmpPageFuntKeyIds input[id='location']").val();
//	reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+record.id+"&locnId="+location);
	//reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+record.id);
	//reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+record.id+"&locnId="+location+"&cellId="+cellId);
	setFormater("tblAbnAllocation","frmabnAllocation","employee.commonFilter?trade="+record.id+"&locnId="+location+"&cellId="+cellid,rowId,"cmbAbnmResponse","EMPID","180px",false);
	
}

function chkboxCheck(rowId)
{	
	jQuery("#hdnRowNo").val(rowId);
	//var rowId = jQuery("#tblAbnAllocation").jqGrid('getGridParam','selrow');
	var rowData = jQuery("#tblAbnAllocation").jqGrid('getRowData',rowId);
	
	setFormater("tblAbnAllocation","frmabnAllocation","",rowId,"EFFDT","EFFDT",100,false);
	setFormater("tblAbnAllocation","frmabnAllocation","Combo_Trade.abnForm",rowId,"cmbAbnmTrade","TRADEID",100,false);
	var cellid = rowData.CELL_KEYID;
	var location = jQuery("#frmEmpPageFuntKeyIds input[id='location']").val();
	
	setTimeout(function() {
		var tradeId = jQuery("#tblAbnAllocation").jqGrid('getCell',rowId,'TRADEID');
		jQuery("#hdnTradeID").val(tradeId);
		
		jQuery("tblAbnAllocationcmbAbnmTrade_"+rowId).combobox('setValue',tradeId);
		setFormater("tblAbnAllocation","frmabnAllocation","employee.commonFilter?trade="+tradeId+"&locnId="+location+"&cellId="+cellid,rowId,"cmbAbnmResponse","EMPID",180,false);
		setTimeout(function() {
			var empId = jQuery("#tblAbnAllocation").jqGrid('getCell',rowId,'EMPID');
			//alert(empId);
			jQuery("tblAbnAllocationcmbAbnmResponse_"+rowId).combobox('setValue',empId);
		},300);
	},400);
	
	jQuery("#tblAbnAllocation").jqGrid('setCell',rowId,'CHKBOX','1');
	
}

function chkboxUnCheck(rowId)
{
	jQuery("#tblAbnAllocation").jqGrid('setCell',rowId,'CHKBOX','0');
	removeFormater("tblAbnAllocation","",rowId,"","EFFDT");
	removeFormater("tblAbnAllocation","",rowId,"","cmbAbnmTrade");
	removeFormater("tblAbnAllocation","",rowId,"","cmbAbnmResponse");
}
function frmabnAllocation_beforeSubmit()
{
	var gridData = JqGridToJsonSelectdRowsForGrid("tblAbnAllocation","cb","CHKBOX");
	
	if(gridData!=false){
		
		gridData = "&allocationGridData="+gridData;
		if(gridData != null || gridData.length>0){
			return gridData;
		}
		else{
			alert("Select a Row For Save!");
			return false;
		}
	}
	else
		return false;
}
function JqGridToJsonSelectdRowsForGrid(jqGridId,checkBoxColName,ckeckForSelColName){
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		if( value != null  &&  value.trim()  != "" &&  value.trim()  != "0" && value.trim()  == "1"){
			jsonArrO += '{';
			var cnt = parseInt(i)+parseInt(1);
			var targetdate = jQuery("#"+jqGridId+"dteTargetDate_"+cnt).datebox("getValue");
			
 			var detectionDate = row["DETECDT"];
 			detectionDate = detectionDate+"00:00";
 			var respons = row["EMPID"];
 			
 			if( respons.trim()!='' && respons.trim()!='{}'){
				if(convertStringToDate(detectionDate) > convertStringToDate(targetdate))
				{
					if(targetdate.length<1)
						alert(" Select Proposed Target Date  ");
					else
						alert("Proposed Target Date will be Greater Than Detected Date! ");
					//setFocusOnField("#"+jqGridId+"dteTargetDate_"+cnt);
					return false;
				}
 			}
			var trade = row["TRADEID"];
			
			
			var oldTrade = jQuery("#hdnTradeID").val();
			//alert(oldTrade+trade);
			if(oldTrade == trade)
			{
				if(targetdate != null && targetdate.trim() != "")
					jsonArrO += '"dteAbnmEffectivedate":"' + targetdate +'",';
				else{
					if( respons.trim()!=''){
					alert("Select Proposed Date");
					return false;
					}
				}
				
				if(respons != null && respons.trim() != "" ){
				
					jsonArrO += '"cmbAbnmResponsibleid":"' + respons +'",';
				}
				else{
					if( targetdate.trim()!=''){
					alert("Select Responsible Person");
					return false;
					}
				}
			}
			else
				{
				var respons1 = "{}";
				var targetdate1 = '01-Jan-1801';
				jsonArrO += '"cmbAbnmResponsibleid":"' + respons +'",';
				jsonArrO += '"dteAbnmEffectivedate":"' + targetdate +'",';
				}
			if(trade != null && trade.trim() != "")
				jsonArrO += '"cmbAbnmTradeid":"' + trade +'",';
			else{
				alert("Select Trade");
				return false;
			}
			for(var colName in row) {
				if( checkBoxColName != colName ){
					if(colName == "ABNNO"){
						cellValue = parseJqGridCellValue(row[colName]);
						colName = "cmbAbnmKeyid";
						jsonArrO += '"'+colName +'":"' + cellValue +'",';
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

function frmabnAllocation_successsCallback()
{
	
	jQuery('#tblAbnAllocation').trigger("reloadGrid");
	}
</script>
<form id="frmabnAllocation">
<div id="wrapperRpt" style="">
<table id="tblAbnAllocation"></table>
<div id="abnAllocationPager"></div>
<input type="hidden" id="hdntxtAbnmRemarks" value=""/>
<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.loginUser}" >
</div>
<input type="hidden" id="mode"  />
<input type="hidden" id="hdnRowNo"  name="hdnRowNo" value=""/>
<input type="hidden" id="hdnTradeID"  name="hdnTradeID" value="" />

</form>