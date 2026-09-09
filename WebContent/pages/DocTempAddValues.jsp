<script type="text/javascript">	
	jQuery(document).ready(function(){		
		initialiseForm('frmDocTempValues');
		jQuery('#submitForm').val('frmDocTempValues');
		jQuery('#frmDocTempValues .easyui-text').css('text-transform', 'uppercase');
		var typeId = jQuery("#hdnDtpvDtpmKeyid").val();
		var fileId = jQuery("#hdnDtpvDmdmKeyid").val();
		var dataStr = "?q=2&typeId="+typeId+"&fileId="+fileId;		
		processGridnew('docTempTypeVal_input.dcm',dataStr,"docTempValGrid","","","","","docTempValLoad","docTempValError");
	});
	
	function value_Formatter(idVal, options, rowObject){
		var id = options.rowId;		
		var type = rowObject[4];
		
		if(type.indexOf('date')>=0 || type.indexOf('DATE')>=0 )
			return '<input id="dteValue'+id+'" name="dteValue'+id+'" style="width:110px;" class="easyui-datebox" value="'+idVal+'" />';
		else 
			return '<input id="txtValue'+id+'" name="txtValue'+id+'" maxlength="50" style="width:100%;" class="easyui-text" value="'+idVal+'" />';
			
	}
	function docTempValLoad()
	{
		var row = jQuery("#docTempValGrid").jqGrid('getDataIDs');
		for(var id = 1; id<=row.length; id++) {
			var type = jQuery("#docTempValGrid").getCell(id, 'txtType');	
			if(type.indexOf('date')>=0 || type.indexOf('DATE')>=0 )
				formatDateBox('dteValue'+id,'dd-MMM-yyyy');
			else if(type.indexOf('number')>=0 || type.indexOf('NUMBER')>=0 )
				 numericTextBox('txtValue'+id); 
		}
	}
	function frmDocTempValues_beforeSubmit()
	{
		return 'keywordDatas='+convertKeywordValGridToJSONArr('docTempValGrid');
	}
	function frmDocTempValues_successsCallback(result)
	{
		jQuery("#docTempValGrid").trigger("reloadGrid");
	}
	function convertKeywordValGridToJSONArr(jqGridId){
		
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
		var jsonArrO='[';	
		for( var i = 0; i < allRows.length;i++){			
			var row = allRows[i];			
			var value = '';
			var valField = row['txtDtpvValue'];
			if(valField.substring(0,6)!='<input')
				value = valField;
			else
			{
				var temp1=valField.indexOf("id=")+4;
				var temp2=valField.substring(temp1);
				var temp3 = temp2.indexOf('"');		
				if(valField.indexOf('datebox')>=0)
					value = jQuery('#'+temp2.substring(0,temp3)).datebox('getValue');
				else
					value = jQuery('#'+temp2.substring(0,temp3)).val();
				
			}
			if(value != null && value != '' && value != ' ')
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
													
							if(row[colName].indexOf('datebox')>=0)
								val = jQuery('#'+y.substring(0,z)).datebox('getValue');
							if(val == 'undefined')	
								val = '';		
							
							jsonArrO += '"'+colName +'":"' + escape(val) +'",';							
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
<form id="frmDocTempValues" name="frmDocTempValues">
<input type="hidden" id="mode"/>
<input type="hidden" id="hdnDtpvDtpmKeyid" name="hdnDtpvDtpmKeyid" value="${requestScope.typeId}"/>
<input type="hidden" id="hdnDtpvDmdmKeyid" name="hdnDtpvDmdmKeyid" value="${requestScope.fileId}"/>
<div style="padding-left:7%;float:left;">			
	<table id="docTempValGrid" style="float: left;"></table>
</div>
</form> 