<script>
jQuery(document).ready(function(){	
	
	initialiseForm('frmdostemplate');
	jQuery('#submitForm').val('frmdostemplate');
	fillComboBox("frmdostemplate","cmbDtpmKeyid","type_combo.dcm");
	fillDocTempGrid();
	
});	
jQuery("#btnaddrow").click(function(){

	var row = jQuery("#doctemplate").jqGrid('getDataIDs');
	
	addRow(row);
	
});	
function delDocTemp(id){
	
	var key = jQuery("#doctemplate").getCell(id, 'txtDtpdKeyid');
	var docType = jQuery("#doctemplate").getCell(id,'txtDtpdKeyword');
	
	
	if(key!='' && key!=' ' && key!=null){
		var msg ="Do You want to Delete";
		if(docType!=null || docType!='' || docType!=' ')
			msg += " "+docType+" ";
		msg += "?";
		if(confirm(msg) == true)
		{				
			var dataString = '?q=2';
			if(key!='' || key!=' ' || key!=null)
				dataString += '&txtDtpdKeyid='+key;
			processAjaxCalls("DocTemp_del.dcm",dataString,"delDocTempSuccess","delDocTempError");
			}
		
		}
		else {
			 jQuery('#doctemplate').delRowData(id);
			  var g = jQuery('#doctemplate');		
			  var gridData= jQuery("#jqgrid_id").jqGrid('getRowData');
			  g.setGridParam({ data: gridData });
			  g[0].refreshIndex();
			  jQuery("#DocTempRowFlag").val('N');
		}		
}
function delDocTempSuccess(result)
{
	if( result.tpmException ){
		
	
	var msg = result.tpmException.messages;
	
	if(msg != null && msg != '' && msg != ' ' && msg != 'undefined')
		alert(msg);
	}
	alert(result.successData.msg);
	jQuery("#DocTempRowFlag").val('N');
	fillDocTempGrid();
	
}

function  frmdostemplatecmbDtpmKeyid_onSelect(record)
{
	
	var url = jQuery('#hiddenUrl').val();
	
	var dataStr = '?q=2&type='+record.id;
	jQuery("#txtDtpmDocumenttype").val(record.text);
	jQuery("#DocTempRowFlag").val('N');

	processGridnew(url,dataStr,"doctemplate","pager","Key Words","","","DocTempLoadComplete","","");
}
function fillDocTempGrid(){
	var type=jQuery("#cmbDtpmKeyid").combobox('getValue');
	var dataStr = '?q=2&type='+type;
	var url = jQuery('#hiddenUrl').val();
	processGridnew(url,dataStr,"doctemplate","pager","Key Words","","","DocTempLoadComplete","","");

}
function addRow(row)
{
	//alert(row);
	var rowFlag =  jQuery("#DocTempRowFlag").val();	
	
	if(rowFlag != 'Y')
	{	
		if ( row == null || row == '' || parseInt(row) <= 0) {	
		//jQuery("#DocTempRowFlag").val("Y");		
	 	var emptyItem =[{txtDtpdKeyid:" ",txtDtpdKeyword:" ",txtDtpdType:" "}];
		jQuery("#doctemplate").jqGrid('addRowData',1, emptyItem[0]);
		
	 }	
	else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		//jQuery("#DocTempRowFlag").val("Y");
		var emptyItem =[{txtDtpdKeyid:" ",txtDtpdKeyword:" ",txtDtpdType:" "}];
		jQuery("#doctemplate").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
	}
}
function DocTempLoadComplete(){
	
	//alert('load');
	
	//var row = jQuery("#doctemplate").jqGrid('getDataIDs');
	// addRow(row);
	
}
function convertDocTempGridToJSONArr(jqGridId,toDel){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var jsonArrO='[';	
	for( var i = 0; i < allRows.length;i++){			
		var row = allRows[i];
		var flag = true;	
		
		if(flag)
		{			
			jsonArrO += '{';
			for(var colName in row) {				
				if(colName != 'btnDeleteDocTemp')
				{
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
						if(row[colName].indexOf('checkbox')>=0)
						{
							if(jQuery('#'+y.substring(0,z)).is(':checked') == true)
								val = "Y";
							else
								val = "N";
						}
						if(row[colName].indexOf('combobox')>=0)
							val = colName != 'txtDtpdType'? jQuery('#'+y.substring(0,z)).combobox('getValue'):val;
						if(row[colName].indexOf('datebox')>=0)
							val = jQuery('#'+y.substring(0,z)).datebox('getValue');
						if(val == 'undefined')	
							val = '';		
						//alert(escape(val));
						jsonArrO += '"'+colName +'":"' + escape(val) +'",';							
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
function frmdostemplate_beforeSubmit()
{
	var value = jQuery("#txtDtpmDocumenttype").val();
	 if( value == '' || value == ' ' || value == null){
			alert("Enter Document Type");
		return false;
	 }
	else
		return 'doctempDatas='+convertDocTempGridToJSONArr('doctemplate');
}
function frmdostemplate_beforeDelete()
{
	var msg ="Do You want to Delete";
	var value = jQuery("#txtDtpmDocumenttype").val();
	if(value != null && value != ' ' && value != '')
		msg += " "+value;
	msg += "?";
	if(confirm(msg) == false)
	{
		return false;
	}
	
}
function frmdostemplate_successsCallback(result)
{
	reloadCombo("frmdostemplate","cmbDtpmKeyid","type_combo.dcm");
	var key= result.Keyid;
	var type = result.TYPE;
	 setTimeout(function() {jQuery("#cmbDtpmKeyid").combobox('setValue',key); },300);
	 setTimeout(function() {jQuery("#txtDtpmDocumenttype").val(type); },300);
	 setTimeout(function() {jQuery("#doctemplate").trigger("reloadGrid");},1250);
//	var row = jQuery("#doctemplate").jqGrid('getDataIDs');
	// addRow(row);

	
}
function frmdostemplate_deleteSuccessCallback(result)
{
	reloadCombo("frmdostemplate","cmbDtpmKeyid","type_combo.dcm");
	jQuery("#doctemplate").trigger("reloadGrid");
}
function del_Formatter(id, options, rowObject){
	var id = options.rowId;
	return '<img id="del_'+id +'" name="del_'+id +'"   src="images/wrong.png"  onclick="delDocTemp(\''+id + '\');"/>';
	  	
}
function type_Formatter(idVal, options, rowObject){
	var id = options.rowId;
	var comboBox = "<select   id='cmbType_"+id+"' style='width:150px;' name='cmbType_"+id+"'class='easyui-combobox' value='Y'>";
	var selNum=""; var selTxt="";var selDte="";
	if (idVal=="N") 
	{
		selNum =" selected='selected' "; 
	}
	else if (idVal=="T") 
	{
		selTxt =" selected='selected' "; 
	}
	else if (idVal=="D") 
	{
		selDte =" selected='selected' "; 
	}
	
	comboBox += "<option  value=' '>  </option>"; 
	comboBox += "<option  value='N'  " + selNum + " >Number</option>";
	comboBox += "<option  value='T'  " + selTxt + ">Text</option>";
	comboBox += "<option  value='D'  " + selDte + ">Date</option>";		
	comboBox += "</select>";
	return comboBox;
}
function keyword_Formatter(idVal, options, rowObject){
	var id = options.rowId;
	var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";		

	 return '<textarea  id="txaKeyword_'+id +'" name="txaKeyword_'+id +'" maxlength="200" style="width:502px;" >'+value+'</textarea>';
}
</script>

<form id="frmdostemplate" name="frmdostemplate">
<div id="wrapper" >
	<div class="main-cntborder" >
	
	<div align="center" >
<table style="">

	<tr>
	<td  >
	<div class="easyui-paddingbfpx"><label  class="mandatory-lbl">Document Type</label></div>
		<div  class="easyui-paddingbfpx">
			<input id="cmbDtpmKeyid" name="cmbDtpmKeyid" class="easyui-combobox" style="width:300px;" value="${requestScope.DocTlTemplateDefMst.dtpmKeyid}" />
		</div>
		<div class="easyui-paddingbfpx"> <label  class="mandatory-lbl">Document Type</label></div>
		<div class="easyui-paddingbfpx">
			<input type="text" id="txtDtpmDocumenttype" name="txtDtpmDocumenttype"  style="width:300px;" class="easyui-text" value="${requestScope.DocTlTemplateDefMst.dtpmDocumenttype}"  />
			<span style="padding-left:20px;">
			<input type="button" name="btnaddrow" id="btnaddrow" class="easyui-button" value="Add Row"/>
			</span>
		</div>
<!--		<div class="easyui-paddingbfpx" style="">-->
<!--			<input type="button" name="btnaddrow" id="btnaddrow" class="easyui-button" value="Add Row"/>-->
<!--		</div>-->
	</td>
	</tr>
</table>
</div>
	<table id="doctemplate">
		
	</table>
	<div id="pager"></div>
</div>

</div>
<input type="hidden" id="mode" name="mode" value=""/>
<input type="hidden" id="DocTempRowFlag" name="DocTempRowFlag" value=""/>
</form> 