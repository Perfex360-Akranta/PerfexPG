<script> 
jQuery.noConflict();
jQuery(document).ready(function()
{	 
	var pillarHdn = jQuery('#hdnPillarName').val();

	  
	var flId = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	if (flId =='' || flId==' '|| flId==null) {
		flId = jQuery('#hdnFlid').val();
	}
	var rptType = jQuery('#hdnRptType').val();

  //  alert("rptType"+rptType);
	
	var vMonth = jQuery('#hdnvMonth').val();
	//alert("vMonth"+vMonth);


	var monthYear = jQuery('#hdnMonthYear').val();

	var type = getFieldValue("txtIndicatorType");
	var calyr=jQuery("#hdncalYear").val();
	//alert("calyr:::"+calyr);

	
	    processGridnew("kpiIndicatorgrd_input.kpiActKk","?q=2&pillarHdn="+pillarHdn+"&flId="+flId+"&rptType="+rptType+"&monthYear="+monthYear+"&vMonth="+vMonth+"&type="+type,"KpiIndicatorListgrd","kpilstpager","KPI Indicators","kpilst_doubleClickGrid","","kpilst_loadComplete","kpiLstselectRowFunction");

	    jQuery('#btnokkpi').click(function(){
	    	//alert(456);
			var fromSource = jQuery('#hdnfromSource').val();
			var gridData = getSelectdRows("KpiIndicatorListgrd","cb","hdnChkSel");
			//alert("gridData::"+gridData);

			var indicatorName = getSelectdRowsIndicator("KpiIndicatorListgrd","cb","hdnChkSel");
			var calyr=jQuery("#hdncalYear").val();
	        //alert(" indicatorName "+indicatorName);




			var urlgrp= "kpiIndicatorchartpage_input.kpiActKk?&grdData="+gridData+"&indicatorName="+escape(indicatorName)+"&rptType="+rptType+"&monthYear="+monthYear+"&vMonth="+vMonth+"&calyr="+calyr;
		
		//alert("calyr"+calyr);
		//var urlgrp= "page_input.kpiActKk?&grdData="+gridData+"&indicatorName="+escape(indicatorName)+"&rptType="+rptType+"&monthYear="+monthYear+"&calyr="+calyr;
		
		if("MOM"==fromSource){		

			    jQuery('#selePillarId').val(indicatorName);
			    jQuery('#seleKeyid').val(gridData);
				//alert(" Inside jQuery "+jQuery("#selePillarId").val());
				//alert(" Inside jQuery "+jQuery('#seleKeyid').val());
				closePopUpDialoge("divIndicatorPop");
			}
			else{
				 if(gridData!='' && gridData!= ' ' && gridData!= null){
					showGraphData(urlgrp,true);
					//closePopUpDialoge("divIndicatorPop");
				}
				else{ 
					alert('Select Indicators');
					return false;				
				}
			}
		});


	/* if(jQuery("#hdnKpiKeyid").val() != null)
		   MomkpiCheck(jQuery("#hdnKpiKeyid").val());*/

	jQuery('#btnCancelKpi').click(function(){//alert("cancel");
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
function kpilst_loadComplete(){		
	//alert("3");
	if(jQuery("#hdnKpiKeyid").val().trim().length>0)
		 MomkpiCheck(jQuery("#hdnKpiKeyid").val());
	     
}
function MomkpiCheck(kpicheck)
{
	//alert("1");
	var KpiInKeyid = kpicheck.split(',');		
	var row = jQuery("#KpiIndicatorListgrd").jqGrid('getDataIDs');
    for(i=0;i<row.length;i++)
        {
     
         var rowid =row[i];
	      var Indkeyid = jQuery("#KpiIndicatorListgrd").jqGrid('getCell',row[i],"keyid");
	     //alert("Indkeyid "+Indkeyid);
	 for(j=0;j<KpiInKeyid.length;j++)
		{
		var Kpikey=KpiInKeyid[j];		
		if(Kpikey == Indkeyid){
			   // alert("Kpikeyid "+Kpikey+"Indkeyid"+Indkeyid);
				jQuery('#jqg_KpiIndicatorListgrd_'+(i+1)).attr('checked', true);
			    jQuery("#KpiIndicatorListgrd").setCell(rowid, "hdnChkSel","1");
			}
		  }
        }  
    IndicatorNamesetGridOnclose(); 
    
}
	


/**Function for  selecting/Unselecting all Rows**/
function kpiLstselectRowFunction_selectAll(id,status){//alert("+status "+id+ " --  "+status);
	
	for(var i=0; i<id.length; i++){
		if(status)
			selectData(id[i]);
		else
			unselectData(id[i]);
	}
}
/**End**/
/**Function for  selecting/Unselecting  Row**/
/*function kpiLstselectRowFunction_selectRow(id){
	if(jQuery('#jqg_KpiIndicatorListgrd_'+id).is(':checked'))
		selectData(id);
	else
		unselectData(id);
}*/
function KpiIndicatorListgrd_selectAll(id,status){
	//alert("1");
	for(var i=0; i<id.length; i++){
		if(status)
			selectData(id[i]);
		else
			unselectData(id[i]);
	}
}

function KpiIndicatorListgrd_selectRow(id){

	if(jQuery('#jqg_KpiIndicatorListgrd_'+id).is(':checked'))
		selectData(id);
	else
		unselectData(id);
}
/**End**/

function frmKpiIndicatorList_beforeCloseCurrentForm()
{
//alert("2");
	}
function selectData(rowId){
	//var cm = jQuery("#KpiIndicatorListgrd").jqGrid("getGridParam", "colModel");
	//alert(rowId);
  	jQuery("#KpiIndicatorListgrd").setCell(rowId, "hdnChkSel","1");
}
function unselectData(rowId){
	//var cm = jQuery("#KpiIndicatorListgrd").jqGrid("getGridParam", "colModel");
  	jQuery("#KpiIndicatorListgrd").setCell(rowId, "hdnChkSel","0");
}
function IndicatorNamesetGridOnclose()
{
	//alert("2");
	var fromSource = jQuery('#hdnfromSource').val();
	var gridData = getSelectdRows("KpiIndicatorListgrd","cb","hdnChkSel");
	var indicatorName = getSelectdRowsIndicator("KpiIndicatorListgrd","cb","hdnChkSel");
	//var urlgrp= "kpiIndicatorchartpage_input.kpiActKk?&grdData="+gridData+"&indicatorName="+escape(indicatorName);
	var urlgrp="page_input.kpiActKk?&grdData="+gridData+"&indicatorName="+escape(indicatorName);
	if("MOM"==fromSource){  //alert("2");
		jQuery('#selePillarId').val(indicatorName);
		jQuery('#seleKeyid').val(gridData);
		//closePopUpDialoge("divIndicatorPop");
	}
	else{
		if(gridData!='' && gridData!= ' ' && gridData!= null){
			showGraphData(urlgrp,true);
			//closePopUpDialoge("divIndicatorPop");
		}
		else
			alert('Select Indicators');
	}}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
{	//alert(jqGridId+"--"+checkBoxColName+"--"+ckeckForSelColName);
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	 
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		//alert(" val  "+value);		
		if( value != null  &&  value.trim()  != ""){	
				
			if(value == '1')				
			{
				 //alert("value "+value);
				//jsonArrO += '{';
			for(var colName in row) {
				if(colName == 'keyid')
				{
				var cellValue = parseJqGridCellValue(row[colName]);
				jsonArrO +=  cellValue;
				}
			}
			jsonArrO +=  ",";
			}
		}
		//alert("val"); 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
} 
	
function getSelectdRowsIndicator(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];	
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				//jsonArrO += '{';  selePillarId
			for(var colName in row) {
				if(colName == 'Indicator' )
				{
				var cellValue = parseJqGridCellValue(row[colName]);
				jsonArrO +=  cellValue;
				}
			}
			jsonArrO +=  ",";
			
			}
		} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
}
</script>
<form id="frmKpiIndicatorList">
<table id="KpiIndicatorListgrd" ></table>
	<div id="kpilstpager"></div>
	<div id="btnDiv" align="center" style="margin-top:10px;">
	<input type="button" value="Ok" class="easyui-button" id="btnokkpi" style="height:21px;width:85px"><input type="button" value="Cancel" class="easyui-button" id="btnCancelKpi" style="height:21px;width:85px">
	</div>
	<input type="hidden"  id="hdnFlid" value='${requestScope.flid}'/>
	<input type="hidden"  id="hdnPillarName" value='${requestScope.pilarref }'/>
	<input type="hidden"  id="hdnfromSource" value='${requestScope.from }'/>
	<input type="hidden"  id="hdnKpiKeyid" value='${requestScope.Kpikeyid}'/>
	<input type="hidden"  id="hdnRptType" value='${requestScope.rpttype}'/>
	<input type="hidden"  id="hdnMonthYear" value='${requestScope.monthyear}'/>
    <input type="hidden"  id="hdnvMonth" value='${requestScope.vMonth}'/>
	<input type="hidden"  id="hdncalYear" value='${requestScope.calyr}'/>

	<input type="hidden"  id="txtIndicatorType" name="txtIndicatorType" value="${requestScope.type}"/> 
	<input type="hidden"  id="selePillarId" /> 
	<input type="hidden"  id="seleKeyid" /> 
</form>