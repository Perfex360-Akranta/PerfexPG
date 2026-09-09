<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("PMPlanvsActual_input.pmpvarpt","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"");
	}	
	jQuery('#btnGraph').click(function(){
		var val = jQuery("#hiddenStr").val();	
	 	var rowid = jQuery("#PMPlanvsActualGrid").jqGrid('getGridParam','selrow');
	 	if(val=="1" || rowid==null)	
		{	jQuery("#hiddenStr").val("");
			alert("Click On Data Row To Generate Graph");
			return false;
		}			
	 	
	 	if(rowid !=null){
			if(checkForZeroes("PMPlanvsActualGrid",rowid,4)){	
	 
				var url = "chart.pmpvarpt?rowid="+rowid+"&" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=L")+"&chType=L";
				showGraphData(url);
		  	 }		
			 else {
		  		alert("No Record to View Graph");
			 }		 
		}
		else {
			var url = "chart.eDTRpt?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);
		}
	});
	
});
 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		
		if(removeBlank.trim() == "")
			jQuery("#hiddenRemoveBlank").val("Y");
		else
			jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		var tableCaption = "PMPlan vs Actual Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"PMPlanvsActualGrid","pager",tableCaption,"doubleClickGrid","","PMPlanvsActualGrid_loadComplete");
		
		
		
		return true;
	}
	return false;	
}

function compareFromToDate1(dtFromDate,dtToDate)
{
	
	var today = convertStringToDate(dtToDate);	
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-1)<=0)
	year=today.getFullYear();
	var backdate = new Date(year,month-1,date);
	
	if(convertStringToDate(dtFromDate)  < backdate){
		alert("From Date Should be within One month");				
		return false;   
	}
	return ;
}

function PMPlanvsActualGrid_loadComplete(){
 
 
	var rowIds = jQuery("#PMPlanvsActualGrid").getDataIDs();
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#PMPlanvsActualGrid").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);				
			}
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	
		}
		jQuery("#eqpnote").css('display','block');	
		jQuery("#eqpfnote").css('display','block');	
		hideJqGridRow("PMPlanvsActualGrid","PMPlanvsActualGridghead_0");

		//if(rowIds[3].trim()=="TOTAL")
			setTotalRowCss("PMPlanvsActualGrid");
	 
			/*if(jQuery('#PMPlanvsActualGrid tr').hasClass('totalRow')){
				jQuery(".totalRow >td:last-child").html("");
				jQuery(".totalRow >td:last-child").prev().html("");
			 } */
		  jQuery("#PMPlanvsActualGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			 jQuery("#hiddeniCol").val(cellcontent);
			  
			/*  var grid = jQuery('#EDTGrid');
			  var sel_id = grid.jqGrid('getGridParam', 'selrow');
			  var CellData = grid.jqGrid('getCell', sel_id,iCol );
				//if(CellData =='0')
					//alert("No Data to view");*/
			  breakdonView(rowid,iCol,cellcontent);
		 
			}	
		});
 }
function breakdonView(rowid,iCol,cellcontent){

 	var colm = jQuery("#PMPlanvsActualGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	 
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	
	 jQuery("#hiddenrowid").val(selId);
	 
}
function doubleClickGrid(id ){ 
	var rowData = jQuery("#PMPlanvsActualGrid").jqGrid('getRowData',id);	
	var circle = jQuery("#hiddencircle").val();
	var selId = rowData.keyid;
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	if(checkForZeroes("PMPlanvsActualGrid",id,5)){
		if(selId.trim() == ''){
		 	
		}
		if(selId.substr(0,3) == 'MCH'){
			alert("No Records to View");
		}
		else if(selId.substr(0,3) != 'PHM' && circle.substr(0,3) != "CRC"){
			var filterData ="?";
		 	filterData += '&parentId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
			filterData += "&chkRemoveBlank="+removeBlank;
			processGridnew(url,filterData,"PMPlanvsActualGrid","pager",'',"doubleClickGrid","","PMPlanvsActualGrid_loadComplete");	
		}
		
	 	if(selId.substr(0,3) == 'PHM'){
			//jQuery('#cmbFact').combobox('setValue',selId);
		}
		
	 }
	else
		alert("No Records to View");
	
}

function PMPlanvsActualGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#PMPlanvsActualGrid").getDataIDs();

	var parentId =  jQuery("#PMPlanvsActualGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var dataString = 'drillFlag=b';		
	dataString += "&chkRemoveBlank="+removeBlank;
	processGridnew(url,dataString,"PMPlanvsActualGrid","pager",'',"doubleClickGrid","","PMPlanvsActualGrid_loadComplete");			
}

/*function getParamName(rowid)
{
	if(rowid.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(rowid.substring(0,3)=='LCN')
		return 'cmbLocnid';
	else if(rowid.substring(0,3)=='FCT')
		return 'cmbFactid';
	else if(rowid.substring(0,3)=='LIN')
		return 'cmbSectid';
	else if(rowid.substring(0,3)=='CEL')
		return 'cmbCellid';
	else if(rowid.substring(0,3)=='MCH')
		return 'cmbMchid';
	else if(rowid.substring(0,3)=='ASM')
		return 'cmbAssmbid';
	else
		return null;
}*/
function frmFilter_enableDisableSuccessCallBack(){
	
	enableDisableDatenMonthFilter();
	
	
}
function validateFilterSelection(filterString){

	if(filterString == "?q=1&firstClick=Y" || filterString.substring(0,4) == "?q=1")
		return true;
	else if(jQuery('#chkDatewise').is(':checked') == true){
		
		var dtFromDate = getFilterValue(filterString,"dtFromDate");
		var dtToDate =  getFilterValue(filterString,"dtToDate");
		
		//compareFromToDate1(fromDate,toDate);
		
		/*var today = convertStringToDate(dtToDate);	
		var month,day,year;
		year=today.getFullYear();
		month=today.getMonth();
		date=today.getDate();
		if((month-1)<=0)
		year=today.getFullYear();
		var backdate = new Date(year,month-1,date);		
		if(convertStringToDate(dtFromDate)  < backdate){
			alert("From Date Should be within one month");				
			return true;   
		}
		return true;*/
		
		
	}
		
	
	/*	dtFromDate dtToDate
 *  var dateTime = getServerDateTime();
    var frmmnth =dateTime.getMonth();
	var currmnth = getMonthStringFromInt(frmmnth);
	currmnth+="-"+dateTime.getFullYear();
	
	var selmnth = jQuery('#fromDate').val();
	//alert("sel   "+selmnth);
	if( filterString.length != 0)
	{
		 if( ! checkFilterValueExist(filterString, "cmbMchid"))
		{
			//alert("SELECT EQUIPMENT");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Enter  MONTH");
			return false;
		}
			return true;
	//	}
		// return false;			
	//}	
	

}	*/	

/*if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
	alert("Either Datewise or Monthwise Checkbox to be Selected");
	return false;
}
  if(jQuery('#chkDatewise').is(':checked') == true){
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
	{
		alert("Select  FromDate");
		return false;
	}
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
	{
		alert("Select  ToDate");
		return false;
	}
}
else if(jQuery('#chkMonthwise').is(':checked') == true){
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
	{
		alert("Select  FromMonth");
		return false;
	}
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
	{
		alert("Select  ToMonth");
		return false;
	}
}*/
return true;
}	
	 


</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<form name="frmEdtreport" id="frmEdtreport" >



<div id="divGraphContainer" ></div>	
 
<table style="margin-left: 3.58%;margin-top:10px;">
<tr><td >
<input id="btnGraph" class="easyui-button btn-HeightSmall"  type="button" value="Graph" />  
 
</td>
<td>
<label id="eqpnote"  style="font-weight: bold;display: none; padding-left:3px;">${requestScope.drilldownMsgs}</label>
</td>
<td><label id="eqpfnote"  style="font-weight: bold;display: none; padding-left:10px;">${requestScope.eqpgraph}</label></td>
</tr>
</table>
<div id="wrapperRpt" style= margin-top:2px;>
<table id="PMPlanvsActualGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hiddencircle" value=""  />
</div>
</form>
	