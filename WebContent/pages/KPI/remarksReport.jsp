<script>
jQuery(document).ready(function(){

	initialiseForm("frmKPIRemarks");
	jQuery('#submitForm').val('frmKPIRemarks'); 
	formatDateBox('dtekprmDate','dd-MMM-yyyy');
	var freq = jQuery('#hdnKPIFrequency').val();
	var year = jQuery('#hdnMonthYear').val();
	var dbyear = jQuery('#hdndbyear').val();
	//alert("freq :" +freq);
	//processGridnew("kpiRemarksReport_input.kpiActKk","q=2&Frequency="+freq+"&dbyear="+dbyear,"KPIremarks", "KPIremarksPager","Remarks","","","remarksLoadComplete","","" );
	var selectedDate = jQuery('#dtekprmDate').val();
//alert("kpiMonth date: " + selectedDate); 
processGridnew(
        "kpiRemarksReport_input.kpiActKk",
        "q=2&Frequency=" + freq + "&dbyear=" + dbyear + "&kprmDate=" + selectedDate,
        "KPIremarks", "KPIremarksPager",
        "Remarks", "", "", "remarksLoadComplete", "", ""
    );

	jQuery('#btnAdd').click(function(){ 
		var row  = jQuery("#KPIremarks").jqGrid('getDataIDs');
		addRow(row);
	});
	
	jQuery('#btnDelete').click(function(){ 
		//var id = jQuery("#KPIremarks").jqGrid('getGridParam', 'selrow');
		var allRows = jQuery("#KPIremarks").jqGrid('getRowData');
		var flag=false;
		
		for( var i = 1; i <= allRows.length;i++)
		{
			if(jQuery('#checkbox_'+i).is(':checked') ){
				flag=true;
			}
		}
		if(flag==false){
			alert("Check The Record to Delete");
			return false;
		}
		var action="delete";
		var gridData = JqGridToJsonSelectdRowsForGrid("KPIremarks",action);
		//alert("gridData:" +gridData);
		if(gridData != false ){
			//sriram 19-Nov-2025
			var encoded = encodeURIComponent(gridData);
			saveForm('KPIRemarks','kpiRemarks_save.kpiActKk?&multiplemethods='+encoded+"&action="+action);
		}
		//jQuery("#KPIremarks").delRowData(id);
	});
	
	//fillWithCurrentDate("dtekprmDate");
	var formMode=jQuery("#txtFrmMode").val();
	if (formMode.trim()=="view"){
		disableForm("frmKPIRemarks");
	}
	
	
});

function txaRemarksFormatter(cellvalue, options, rowObject) {	
	var rowId = options.rowId;
	if (cellvalue==' ' || cellvalue==undefined || cellvalue=='undefined ' || cellvalue=='undefined')
		cellvalue="";
	
	var formatStr  = '<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" readonly title="Maximum Length is 500" maxlength="500" id="txaRemarks_'+rowId+'" name="txaRemarks_'+rowId+'" rows=3;cols=60; type="textarea" style="height : 60px;width:245px;"' ;
	formatStr  += 'class="easyui-text" align="left">'+unescape(encodeURIComponent(cellvalue))+'</textarea>';
	//unescape(decodeURIComponent(cellvalue))+'</textarea>';
	return formatStr;
}
function txtcmbKprmKeyidFormatter(cellvalue, options, rowObject) {
	
	if (cellvalue==undefined || cellvalue=='undefined ' || cellvalue=='')
		cellvalue="";
	return '<input readonly type="text" id="txtRemarks_'+options.rowId+'" name="txtRemarks_'+options.rowId+'" '+ 'value=' + cellvalue   +'>';
}
function txtcmbKprmDateFormatter(cellvalue, options, rowObject) {
	
	if (cellvalue==undefined || cellvalue=='undefined ' || cellvalue=='')
		cellvalue=jQuery("#dtekprmDate").val();
	return '<input readonly style="height : 60px;width:80px;" type="text" id="dtekprmDate_'+options.rowId+'" name="dtekprmDate_'+options.rowId+'" '+ 'value=' + cellvalue   +'>';
}
function txtChkBoxFormatter(cellvalue, options, rowObject) {
	
	/*if (cellvalue==undefined || cellvalue=='undefined ' || cellvalue=='')
		cellvalue="";*/
	return '<input disabled type="checkbox" id="checkbox_'+options.rowId+'" name="checkbox_'+options.rowId+'" '  +'>';
}

function addRow(row)
{
		
		 if ( row == null || row == '' || parseInt(row) <= 0) {
		 	var emptyItem =[{cmbKprmKeyid:" ",Remarks:" ",Date:jQuery("#dtekprmDate").val(),DATAORDER:" "}];
			jQuery("#KPIremarks").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++){
				if(i==4){
					alert("For A Single KPI Entry Maximum 5 Remarks ");
					return;
				}
				lastRow = row[i];
			}
			var emptyItem =[{cmbKprmKeyid:" ",Remarks:" ",Date:jQuery("#dtekprmDate").val(),DATAORDER:" "}];
			jQuery("#KPIremarks").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
		 }
}
function KPIRemarks_beforeSubmit()
{
	
}
function KPIRemarks_successsCallback(result) {
	jQuery('#KPIremarks').trigger("reloadGrid");
}
jQuery('#btnSave').click(function(){
	var action="save";
	var gridData = JqGridToJsonSelectdRowsForGrid("KPIremarks",action);
	//alert(gridData);
	
	if(gridData != false ){
		//sriram 19-Nov-2025
		var encoded = encodeURIComponent(gridData);
		saveForm('KPIRemarks','kpiRemarks_save.kpiActKk?&multiplemethods='+encoded+"&action="+action);
	}
	
});


function JqGridToJsonSelectdRowsForGrid(jqGridId,action){
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	//alert("allRows.length:" +allRows.length);
	
	var flag=false;
	var flagDel=false;
	for( var i = 1; i <= allRows.length;i++)
	{	
		
		//alert("i:" +i);
		 var cmbKprmKeyid = jQuery("#txtRemarks_"+i).val();//jQuery("#KPIRemarks").jqGrid('getCell',i,'cmbKprmKeyid');
		// alert(cmbKprmKeyid);		 
		if (cmbKprmKeyid =='undefined'||cmbKprmKeyid=='' || cmbKprmKeyid==' ' || cmbKprmKeyid== null)
			cmbKprmKeyid ="";
		
		var txtkprmDate = jQuery("#dtekprmDate_"+i).val();//jQuery("#KPIRemarks").jqGrid('getCell',i,'cmbKprmKeyid');
		if (txtkprmDate =='undefined'||txtkprmDate=='' || txtkprmDate==' ' || txtkprmDate== null)
			txtkprmDate=jQuery("#dtekprmDate").val();
		/*else{
			txtkprmDate=jQuery("#dtekprmDate").val();
		}*/
		//alert("txtkprmDate:" +txtkprmDate);
		 var remarks =jQuery("#txaRemarks_"+i).val();
		 //var remarks =decodeURIComponent(escape(remark));
		// alert(remarks);
		//var remarks = document.getElementById("#txaRemarks_"+i).value;
		 if (remarks=='' || remarks==' ' || remarks== null||remarks.length=="0") {
			 flag=true;
			 break;
			//popupCommonErrorMsg(" Enter Remarks for all Rows.");
		 	//return false;
		 }
		 
		jsonArrO += '{';
		jsonArrO += '"cmbKprmKeyid":"' + cmbKprmKeyid +'",';
		jsonArrO += '"txtkprmRemarks":"' + encodeURIComponent(unescape(remarks)) +'",';
		jsonArrO += '"txtkprmIndicatorid":"' + jQuery("#hdnKprmIndicatorid").val() +'",';
		jsonArrO += '"txtkprmFlid":"' + getFieldValue('hdnKPIFlid') +'",';
		jsonArrO += '"txtkprmDate":"' + txtkprmDate +'",';
		//jsonArrO += '"txtkprmDate":"' + getFieldValue("dtekprmDate") +'",';
		
	
					
		if(jQuery('#checkbox_'+i).is(':checked') && action=="delete"){
			jsonArrO += '"txtkprmActive":"N",';
		}else{
			jsonArrO += '"txtkprmActive":"Y",'; 
		}
	
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	if(flag==true){
		 alert(" Enter Remarks for all Rows.");
		 return false;
	}
	//alert(jsonArrO.length);
	return jsonArrO;
};
</script>
<form id = "frmKPIRemarks">

<div  >
<!--
	<div>
		<label>KPI Indicator</label>
		<span style="padding-left:90px;"><label>KPI Frequency</label></span>
		<span style="padding-left:32px;"><label>KPI UOM</label></span>
		
	</div>
	  
	<div>
		<input type="text" disabled="disabled" style="width: 150px;" class="easyui-text" value="${requestScope.IndicatiorName}">
		<span style="padding-left:10px;"><input type="text" disabled="disabled" class="easyui-text" style="width: 100px;" value="${requestScope.Frequency}"></span>
		<span style="padding-left:10px;padding-left:20px\9"><input type="text" disabled="disabled" class="easyui-text" style="width: 100px;" value="${requestScope.Uom}"></span>
	</div>

	<div style="padding: 20px;">
		<span style="padding-left:20px;"><input type="button" id="btnAdd"  name="btnAdd" class="easyui-button" value="Add"/></span>
		
		<span style="padding-left: 10px;">
			<input type="button" id="btnSave"  name="btnSave" class="easyui-button" value="Save"/>
		</span>
		<span style="padding-left:20px;"><Font color="blue">Select The Check Box For Delete</font></label></span>
		<span style="padding-left:20px;"><input type="button" id="btnDelete"  name="btnDelete" class="easyui-button" value="Delete"/></span>
	</div>
	-->
	
	<table id="KPIremarks">	</table>
	<div id="KPIremarksPager"></div>
	<input type="hidden" id="hdnKprmIndicatorid" value="${requestScope.IndicatiorId}">
	<input type="hidden" id="hdnIndicatorName" value="${requestScope.IndicatiorName}">
	<input type="hidden" id="hdnKPIUOM" value="${requestScope.Uom}">
	<input type="hidden" id="hdnKPIFrequency" value="${requestScope.Frequency}">
	<input type="hidden" id="hdnKPIFlid" value="${requestScope.flid}">
	<input type="hidden"  id="hdnMonthYear" value='${requestScope.kpiMonth}'/>
	<input type="hidden"  id="hdndbyear" value='${requestScope.dbyear}'/>
	<div style="display: none;">
		<input type="hidden" id="dtekprmDate" name="dtekprmDate" class="easyui-datebox"  style="width:85px;" value="${requestScope.kpiMonth}"  >
	</div>
</div>
</form>