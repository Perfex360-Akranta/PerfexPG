<script>
jQuery(document).ready(function(){
	initialiseForm('frmKaizenList');
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	
	processGridnew("KaizenList_input.prpo","q=2&flid="+flid+"&master="+masterkeyid,"KaizenListgrd","kaizenlstpager","","kpilst_doubleClickGrid","","kaizenList_loadComplete","kaizenlistselectrow");
	
	jQuery('#btnokkpi').click(function(){
		
		/*var id = jQuery('#KaizenListgrd').jqGrid('getGridParam','selarrrow');
		var Kaizenkeyid = getSelectdRows("KaizenListgrd","chkSel","'HDNCHKSEL'");
		var projectKaizenkeyid = getSelectdRowsKaizen("KaizenListgrd","chkSel","'HDNCHKSEL'");*/
		//var gridProject ="&projectKaizen="+convertJsonArrforKaizen(Kaizenkeyid,projectKaizenkeyid);
		var gridProject ="&projectKaizen="+encodeURIComponent(convertJsonArrforPro());
		saveForm("frmKaizenList","kaizenLinkProject_save.prpo?"+gridProject);
		//processGridnew("projectsKpi_input.prpo","q=2&masterkeyid="+jQuery("#hdnKzpmKeyid").val(),"Kpigrid","pagerKpi","","","","");
		
		
	});
	
	jQuery('#btnCancelKpi').click(function(){
		IndicatorNamesetGridOnclose(); 
		closePopUpDialoge("divKaizenPop");
	});
});
function KaizenListgrd_selectRow(id){
	var jqGridId="KaizenListgrd";
	var row = jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'KEYID');
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{	
		
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE','Y');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'HDNCHKSEL','0');
		jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(row[rowId], false);
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert('checked==true');
		
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE','N');	
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'HDNCHKSEL','1');	
		jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(row[rowId], true);	
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE',' ');}
	
	/*
	if(jQuery('#jqg_KaizenListgrd_'+id).is(':checked'))
		selectData(id);
	else
		unselectData(id);*/
}
function kaizenList_loadComplete(){		
	var rows = jQuery("#KaizenListgrd").jqGrid('getDataIDs');
	for( var i = 0; i < rows.length;i++){
		var keyid = jQuery("#KaizenListgrd").jqGrid('getCell',rows[i],"KEYID");
		if(keyid.trim().length>0){
			jQuery('#jqg_KaizenListgrd_'+rows[i]).attr('checked', true);
		}
	}
	     
}
function selectData(rowId){
	jQuery("#KaizenListgrd").setCell(rowId, "HDNCHKSEL","1");
}
function unselectData(rowId){
	jQuery("#KaizenListgrd").setCell(rowId, "HDNCHKSEL","0");
}
//jQuery('#btnokkpi').click(function(){
	
	/*var id = jQuery('#KaizenListgrd').jqGrid('getGridParam','selarrrow');
	var Kaizenkeyid = getSelectdRows("KaizenListgrd","chkSel","'HDNCHKSEL'");
	var projectKaizenkeyid = getSelectdRowsKaizen("KaizenListgrd","chkSel","'HDNCHKSEL'");*/
	//var gridProject ="&projectKaizen="+convertJsonArrforKaizen(Kaizenkeyid,projectKaizenkeyid);
	//var gridProject ="&projectKaizen="+convertJsonArrforPro();
	//saveForm("frmKaizenList","kaizenLinkProject_save.prpo?"+gridProject);
	//processGridnew("projectsKpi_input.prpo","q=2&masterkeyid="+jQuery("#hdnKzpmKeyid").val(),"Kpigrid","pagerKpi","","","","");
	
	
//});

function frmKaizenList_successsCallback(result){
	closePopUpDialoge("divKaizenPop");
}
/* jQuery('#btnCancelKpi').click(function(){
	IndicatorNamesetGridOnclose(); 
	closePopUpDialoge("divKaizenPop");
}); */
function convertJsonArrforPro(){
	//alert("projectkpikeyid:");
	var jqGridId="KaizenListgrd";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var flg =false;
	var jsonArrO='[';
	var errText="";
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//alert("masterkeyid:"+masterkeyid);
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var rowId=parseInt(i)+1;
		//alert("rowId:"+rowId);
		//alert("row:"+row);
		//var value = parseJqGridCellValue(row["hdnChkSel"]);
		//alert("value:"+value);
		var projectkpikeyid=jQuery("#"+jqGridId).jqGrid('getCell',rowId,"kaizenno"); //parseJqGridCellValue(row["KAIZENNO"]);
		//alert("projectkpikeyid:"+projectkpikeyid);
		var keyid=jQuery("#"+jqGridId).jqGrid('getCell',rowId,"keyid"); //parseJqGridCellValue(row["KEYID"]);
		//alert("keyid:"+keyid);
		//alert("projectkpikeyid:"+projectkpikeyid+",value:"+value+",keyid="+keyid);
		if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyid.trim().length>0))
		{			
			flg=true;
			//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
			jsonArrO += '{';
			jsonArrO += '"txtKplkKzpmKeyid":"'+masterkeyid+'",';
			jsonArrO += '"txtKplkKeyid":"'+keyid+'",';
			jsonArrO += '"txtKplkKznmKeyid":"'+projectkpikeyid+'",';	
			jsonArrO += '"hdnIsDelete":"Y"';			
			jsonArrO +=  "},";
		}
		else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true && (keyid.trim().length<=0)){
			flg=true;
			//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
			jsonArrO += '{';
			jsonArrO += '"txtKplkKzpmKeyid":"'+masterkeyid+'",';
			jsonArrO += '"txtKplkKeyid":"'+keyid+'",';
			jsonArrO += '"txtKplkKznmKeyid":"'+projectkpikeyid+'",';
			jsonArrO += '"hdnIsDelete":"N"';				
			jsonArrO +=  "},";	
		}
		/*if(jQuery("#jqg_KaizenListgrd_"+rowId).is(":checked") == true){
		//if( value != null  &&  value.trim()  != ""){			
			//if(value == "1")
			//{				
				//alert("projectkpikeyid:"+projectkpikeyid+",value:"+value+",keyid="+keyid);
				flg=true;
				//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
				jsonArrO += '{';
				jsonArrO += '"txtKplkKzpmKeyid":"'+masterkeyid+'",';
				jsonArrO += '"txtKplkKeyid":"'+keyid+'",';
				jsonArrO += '"txtKplkKznmKeyid":"'+projectkpikeyid+'"';			
				jsonArrO +=  "},";
		//	}			
		//} 
		}*/
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO:"+jsonArrO);
	if(flg==false){
		setTimeout(function() {
			showCommonErrorMsg("Select Atleast One Keizen");
		}, 200);
		div_err();
		return false;
	}
	return jsonArrO;
}

function convertJsonArrforKaizen(kpikeyid,projectkeyid){
	var keyid = kpikeyid.split(",");
	var projectkpikeyid = projectkeyid.split(",");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var jsonArrO='[';
	for(var i=0;i<keyid.length;i++){
		jsonArrO+= '{';
		jsonArrO += '"txtKplkKzpmKeyid":"'+masterkeyid+'",';
		jsonArrO += '"txtKplkKeyid":"'+projectkpikeyid[i]+'",';
		jsonArrO += '"txtKplkKznmKeyid":"'+keyid[i]+'"';
		jsonArrO+= '},';
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO;
}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
{	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];		
		if( value != null  &&  value.trim()  != ""){	
				
			if(value == '1')				
			{
				//alert("value "+value);
				//jsonArrO += '{';
			for(var colName in row) {
				if(colName == 'KAIZENNO')
				{
				var cellValue = parseJqGridCellValue(row[colName]);
				jsonArrO +=  cellValue;
				}
			}
			
			}else if(value=="0"){
				for(var colName in row) {
					if(colName == 'KAIZENNO')
					{
					var cellValue = parseJqGridCellValue(row[colName]);
					jsonArrO +=  " ";
					}
				}
				}
			jsonArrO +=  ",";
		}
		//alert("val"); 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
} 
function getSelectdRowsKaizen(jqGridId,checkBoxColName,ckeckForSelColName)
{	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];		
		if( value != null  &&  value.trim()  != ""){	
				
			if(value == '1')				
			{
				//alert("value "+value);
				//jsonArrO += '{';
			for(var colName in row) {
				if(colName == 'KEYID')
				{
				var cellValue = parseJqGridCellValue(row[colName]);
				jsonArrO +=  cellValue;
				}
			}
			
			}else if(value=="0"){
				for(var colName in row) {
					if(colName == 'KEYID')
					{
					var cellValue = parseJqGridCellValue(row[colName]);
					jsonArrO += cellValue;
					}
				}
				}
			jsonArrO +=  ",";
		}
		//alert("val"); 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
} 
</script>
<form id="frmKaizenList" name="frmKaizenList" >
<div>
<table id="KaizenListgrd">
<tr>
	<td>
	</td>
</tr>
</table>
<div id="kaizenlstpager"></div>
</div>
<div id="btnDiv" align="center" style="margin-top:10px;">
	<input type="button" value="Ok" class="easyui-button" id="btnokkpi" style="height:21px;width:85px"><input type="button" value="Cancel" class="easyui-button" id="btnCancelKpi" style="height:21px;width:85px">
	</div>
</form>