<script> 
jQuery.noConflict();
jQuery(document).ready(function()
{	
	var pillarHdn = jQuery('#hdnPillarName').val();
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var hdnKpiFlid =jQuery("#hdnKpiFlid").val();
	processGridnew("kpiIndicatorgrd_input.prpo","q=2&pillarHdn="+pillarHdn+"&master="+masterkeyid+"&flid="+hdnKpiFlid,"KpiIndicatorListgrd","kpilstpager","KPI Indicators","kpilst_doubleClickGrid","","kpilst_loadComplete","kpiLstselectRowFunction");

	jQuery('#btnokkpi').click(function(){		
		var id = jQuery('#KpiIndicatorListgrd').jqGrid('getGridParam','selarrrow');		
		var errFlg=false;
		//madhan 
		//var gridProject ="&projectKpi="+convertJsonArrforPro();
		var gridProject =convertJsonArrforPro();
		var errText = getFilterValue(gridProject+'&', 'errText');
		if(errText==null || errText.trim()==""){
		}
		else{	
			errFlg=true;
		}
		
		//alert("convertjson"+gridProject);	
		//alert("errFlg"+errFlg);	
		if(errFlg==true){
			setTimeout(function() {
				showCommonErrorMsg(errText);
			}, 200);
			div_err();
			return false;
		}
		//saveForm("frmKpiIndicatorList","kpiLinkProject_save.prpo?"+encodeURIComponent(gridProject));
		saveForm("frmKpiIndicatorList","kpiLinkProject_save.prpo?projectKpi="+encodeURIComponent(gridProject));
		//processGridnew("projectsKpi_input.prpo","q=2&masterkeyid="+jQuery("#hdnKzpmKeyid").val(),"Kpigrid","pagerKpi","","","","");
				
	});
	
	jQuery('#btnCancelKpi').click(function(){
		//IndicatorNamesetGridOnclose(); 
		closePopUpDialoge("divIndicatorPop");
	});

	jQuery("#cb_KpiIndicatorListgrd").click( function() {
		 var s; s = jQuery("#KpiIndicatorListgrd").jqGrid('getGridParam','selarrrow');
		  //alert(s); 
	}); 
	
	jQuery("#cm1s").click( function() { 
		  jQuery("#KpiIndicatorListgrd").jqGrid('setSelection',"13");
	});
});

function convertJsonArrforPro(){
	var jqGridId="KpiIndicatorListgrd";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var flg =false;
	var jsonArrO='[';
	var errText="";
	var errFlg=false;
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var rowId=parseInt(i)+1;	
		var projectkpikeyid=parseJqGridCellValue(row["KPKL_KEYID"]);
		var keyid=parseJqGridCellValue(row["KINK_KEYID"]);
		var baseVal=jQuery("#BASEVAL_"+jqGridId+"_"+rowId).val();//parseJqGridCellValue(row["KINK_KEYID"]);
		var targetVal=jQuery("#TARGETVAL_"+jqGridId+"_"+rowId).val();//parseJqGridCellValue(row["KINK_KEYID"]);
		
		
		//if(jQuery("#jchkformatter_"+rowId).is(":checked") == true){
		if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (projectkpikeyid.trim().length>0))
		{			
			flg=true;
			//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
			jsonArrO += '{';
			jsonArrO += '"txtKpklKzpmKeyid":"'+masterkeyid+'",';
			jsonArrO += '"txtKpklKeyid":"'+projectkpikeyid+'",';
			jsonArrO += '"txtKpklKinkKeyid":"'+keyid+'",';
			jsonArrO += '"txtKpklBaseval":"'+baseVal+'",';
			jsonArrO += '"txtKpklTargetval":"'+targetVal+'",';	
			jsonArrO += '"hdnIsDelete":"Y"';			
			jsonArrO +=  "},";
		}
		else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true && (projectkpikeyid.trim().length<=0)){
			flg=true;
			var errMsgRow="";
			var errFlgRow=false;
			//alert("projectkpikeyid:"+projectkpikeyid+",keyid="+keyid+",baseVal:"+baseVal+",targetVal:"+targetVal);
			//alert('baseVal:'+baseVal);
			if(baseVal.trim().length<=0){
				errMsgRow="  Base Value";
				jQuery("#" + jqGridId).jqGrid('setCell',rowId,'BASEVAL','',{'background-color':'red'});
				errFlgRow=true;
			}
			//alert('targetVal:'+targetVal);
			if(targetVal.trim().length<=0){				
				if (errFlgRow){ errMsgRow=errMsgRow + ",";}
				errMsgRow=errMsgRow + "  Target Value";
				jQuery("#" + jqGridId).jqGrid('setCell',rowId,'TARGETVAL','',{'background-color':'red'});
				errFlgRow=true;
			}
			
			if (errFlgRow){
				errText=errText + " Enter " + errMsgRow + " in Row " + rowId + "  " ;
				errFlg=true;
				/*for ( var colName in row) {
					jQuery("#" + jqGridId).jqGrid('setCell',rowId,'BASEVAL','',{'background-color':'red'}); 				
				}*/
			}
			else{
				jsonArrO += '{';
				jsonArrO += '"txtKpklKzpmKeyid":"'+masterkeyid+'",';
				jsonArrO += '"txtKpklKeyid":"'+projectkpikeyid+'",';
				jsonArrO += '"txtKpklKinkKeyid":"'+keyid+'",';
				jsonArrO += '"txtKpklBaseval":"'+baseVal+'",';
				jsonArrO += '"txtKpklTargetval":"'+targetVal+'",';
				jsonArrO += '"hdnIsDelete":"N"';				
				jsonArrO +=  "},";
			}	
		}		
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO:"+jsonArrO);
	
	if(flg==false){
		return jsonArrO+"&errText=Select Atleast One KPI";
	}
	if(errFlg){
		return jsonArrO+"&errText="+errText;
	}
	return jsonArrO+"&errText=";
	//return jsonArrO;
}
	
function kpilst_loadComplete(){		
	var rows = jQuery("#KpiIndicatorListgrd").jqGrid('getDataIDs');
	for( var i = 0; i < rows.length;i++){
		var keyid = jQuery("#KpiIndicatorListgrd").jqGrid('getCell',rows[i],"KPKL_KEYID");		
		if(keyid !=null&& keyid!=" "){
			jQuery('#jqg_KpiIndicatorListgrd_'+rows[i]).attr('checked', true);
		}
	}	     
}

function chkFormatterkpi(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input id="chkformatter_"\''+id + '\' type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
}

function frmKpiIndicatorList_beforeCloseCurrentForm()
{
	//alert("2");
}

function KpiIndicatorListgrd_selectRow(rowId)
{		
	var jqGridId="KpiIndicatorListgrd";
	var row = jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'KPKL_KEYID');
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{			
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE','Y');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnChkSel','0');
		jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(row[rowId], false);
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert('checked==true');
		//alert("checked");		
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE','N');	
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnChkSel','1');	
		jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(row[rowId], true);	
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'ISDELETE',' ');}	
}
	
function IndicatorNamesetGridOnclose()
{
	var id = jQuery('#KpiIndicatorListgrd').jqGrid('getGridParam','selarrrow');
	var fromSource = jQuery('#hdnfromSource').val();
	var gridData = getSelectdRows("KpiIndicatorListgrd","chkSel","hdnChkSel");
	var indicatorName = getSelectdRowsIndicator("KpiIndicatorListgrd","chkSel","hdnChkSel");
	var urlgrp= "kpiIndicatorchartpage_input.kpiActKk?&grdData="+gridData+"&indicatorName="+escape(indicatorName);	
}

function FormattarBaseVal(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input id="txtBaseVal_'+rowId+'" name="txtBaseVal_'+rowId+'" maxlength="3"  onfocus=gotFocuse("txtBaseVal_'+rowId+'"); type="text"  value="'+id+'" style="width:30px;text-align:left"/>';
}

function FormattarTargetVal(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input id="txtTargetVal_'+rowId+'" name="txtTargetVal_'+rowId+'" maxlength="3"  onfocus=gotFocuse("txtTargetVal_'+rowId+'"); type="text"  value="'+id+'" style="width:30px;text-align:left"/>';
}

function gotFocuse(keyid )
{
	//alert("kdsnf");
	numericTextBox(keyid);
}

function frmKpiIndicatorList_successsCallback(result)
{
closePopUpDialoge("divIndicatorPop");
}

</script>

<form id="frmKpiIndicatorList">
<table id="KpiIndicatorListgrd" ></table>
	<div id="kpilstpager"></div>
	<div id="btnDiv" align="center" style="margin-top:10px;">
	<input type="button" value="Ok" class="easyui-button" id="btnokkpi" style="height:21px;width:85px"><input type="button" value="Cancel" class="easyui-button" id="btnCancelKpi" style="height:21px;width:85px">
	</div>
	<input type="hidden"  id="hdnPillarName" value='${requestScope.pilarref }'/>
	<input type="hidden"  id="hdnfromSource" value='${requestScope.from }'/>
	<input type="hidden"  id="hdnKpiKeyid" value='${requestScope.Kpikeyid}'/>
	<input type="hidden"  id="hdnKpiFlid" value='${requestScope.flid}'/>
	<input type="hidden"  id="selePillarId" /> 
	<input type="hidden"  id="seleKeyid" /> 
	<input type="hidden"" id="seleProjectKeyid"/>
</form>