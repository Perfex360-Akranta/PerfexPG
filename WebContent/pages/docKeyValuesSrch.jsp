<script type="text/javascript">	
	jQuery(document).ready(function(){		
		initialiseForm('frmSearchTempValues');
		jQuery('#submitForm').val('frmSearchTempValues');
		jQuery('#frmSearchTempValues .easyui-text').css('text-transform', 'uppercase');
		var typeId = jQuery("#hdnTypeKeyid").val();
		var dataStr = "?q=2&typeId="+typeId+"&showSrch=Y";			
		processGridnew('docTempTypeVal_input.dcm',dataStr,"docTempValSearchGrid","","","","","docTempValSearchLoad","docTempValSearchError");
	});
	jQuery('#chbAny').click(function() {
		jQuery('#chbAny').attr('checked',true);
		jQuery('#chbAll').attr('checked',false);
	});
	jQuery('#chbAll').click(function() {
		jQuery('#chbAll').attr('checked',true);
		jQuery('#chbAny').attr('checked',false);
	});
	jQuery('#btnKeyValSearch').click(function() {
		 //if(jQuery('#chbAny').is(':checked') == true || jQuery('#chbAll').is(':checked') == true )
		 //{
			 var dataStr = '?q=2';
			 var condn = '&schCond=all';
			 if(jQuery('#chbAny').is(':checked') == true)
				 condn = '&schCond=any';
				 /*dataStr += '&schCond=any';
			 else
				 dataStr += '&schCond=all';*/
				 dataStr += condn;
			 var values = convertSearchValGridToJSONArr('docTempValSearchGrid');
			 if(values != null && values != '' && values != ' ')
			 {
				 dataStr += '&searchValues='+convertSearchValGridToJSONArr('docTempValSearchGrid');
				 processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
				 closePopUpDialoge('divKeywordValues');
				 closePopUpDialoge('divAdvSearch');
			 }
			 else
				 alert('Enter Values to Search');
			
		// }
		// else
			// alert('Select Condition to Search');
	});
	function SrchVal_Formatter(idVal, options, rowObject){
		var id = options.rowId;		
		var type = rowObject[3];
		
		if(type.indexOf('date')>=0 || type.indexOf('DATE')>=0 )
			return '<input id="dteSrchValue'+id+'" name="dteSrchValue'+id+'" style="width:110px;" class="easyui-datebox" value="'+idVal+'" />';
		else 
			return '<input id="txtSrchValue'+id+'" name="txtSrchValue'+id+'" maxlength="50" style="width:100%;" class="easyui-text" value="'+idVal+'" />';
			
	}
	function docTempValSearchLoad()
	{
		var row = jQuery("#docTempValSearchGrid").jqGrid('getDataIDs');
		for(var id = 1; id<=row.length; id++) {
			var type = jQuery("#docTempValSearchGrid").getCell(id, 'txtType');	
			if(type.indexOf('date')>=0 || type.indexOf('DATE')>=0 )
				formatDateBox('dteSrchValue'+id,'dd-MMM-yyyy');
			else if(type.indexOf('number')>=0 || type.indexOf('NUMBER')>=0 )
				 numericTextBox('txtSrchValue'+id); 
		}
	}
	
	
	function convertSearchValGridToJSONArr(jqGridId){
		
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
<form id="frmSearchTempValues" name="frmSearchTempValues">
<input type="hidden" id="mode"/>
<input type="hidden" id="hdnTypeKeyid" name="hdnTypeKeyid" value="${requestScope.typeId}"/>
<input type="hidden" id="hdnSearchTemplate" name="hdnSearchTemplate" value="${requestScope.showSearch}"/>
<div id="tempSearchDiv" style="padding-left:30%;">
	<input type="checkbox" id="chbAny" name="chbAny">
	<label class="mandatory-lbl">Match Any</label>
	<span style="padding-left:1%;">
		<input type="checkbox" id="chbAll" name="chbAll">
		<label class="mandatory-lbl">Match All</label>
	</span>
	<span style="padding-left:1%;">
		<input id="btnKeyValSearch" class="easyui-button" name="btnKeyValSearch"  type="button" value="Search" style="height:20px;"  />
	</span>
</div>
<div style="padding-left:10px;padding-left:28px\9;">			
	<table id="docTempValSearchGrid" style="float: left;"></table>
</div>
</form> 