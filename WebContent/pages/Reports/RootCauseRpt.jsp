<script>

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("RootCause_input.rootRpt","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"q=1");
	}	
		jQuery('#btnGraph').click(function(){

		var rowid = jQuery("#rootGrid").jqGrid('getGridParam','selrow');		
		var rowData = jQuery("#rootGrid").jqGrid('getRowData',rowid);		
		var keyId = rowData.keyid;
		if(keyId !=null){
			if(checkForZeroes("rootGrid",rowid,4)){	
		 
			var url = "chart.rootRpt";
			showGraphData(url+"?keyId="+keyId);
		 
			}
			else 
				alert("No Record to View Graph");
		}
		else {
			//var url = "chart.rootRpt";
			//showGraphData(url);
			alert("No Record to View Graph");
		}
	});

		
		 jQuery("#bdbtn").click(function(rowid){	
			 var rowid = jQuery("#rootGrid").jqGrid('getGridParam','selrow');	
			 var rowData = jQuery("#rootGrid").jqGrid('getRowData',rowid);
			 var keyID = rowData.keyid
		
			 if( rowid == null)
					alert("Select on Row to View BreakDown");
			 else
			 {
				 	var selid = jQuery("#hiddenrowid").val();
				 
				 	var selId = selid.substring(0,8);
				 	
				 	var keyId = selid.substring(8,19);
				 	
					var url = jQuery("#rootGrid").jqGrid('getGridParam', 'url');
					url = url.replace("RootCause_getData.rootRpt","RootCause_input.rootRpt");
					url = escape(url); 
					
					if(checkForZeroes("rootGrid",rowid,3)){	
					if(selid != "codeFiel"){	
						var cellcontent =jQuery('#hiddencelVal').val();	
					 if(cellcontent =='0'){
						 alert("No Data to view");
						return false;
					 }else
						navigateToNextForm("brkdown_view.brdn?q=2&"+getParamName(rowid)+"="+rowid+"&selid="+selId+"&keyId="+keyId+"&keyID="+keyID,"Breakdown Analysis View",null,{"filterString":url});
					}else{
						//if(checkForZeroes("rootGrid",selid,2)){	
							navigateToNextForm("brkdown_view.brdn?q=2&"+getParamName(rowid)+"="+rowid,"Breakdown Analysis View",null,{"filterString":url});
					}
	  			  
				}else
					alert("No Data to View");
			}
		 });
	
});

function rootGrid_loadComplete(){ 
	var rowIds = jQuery("#rootGrid").getDataIDs();
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#rootGrid").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);				
			}
			 if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	
		}
	var row = jQuery("#rootGrid").jqGrid('getDataIDs');		
	var grid = jQuery('#rootGrid');
	var sel_id = row.length;
	if(row.length<100)
		sel_id = row.length+2;
	else
		sel_id = row.length;		
	var CellData = grid.jqGrid('getCell',sel_id,'codeField');	
	if(CellData.trim()=='TOTAL')
		setTotalRowCss('rootGrid');		
	

    jQuery("#rootGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
         jQuery('#hiddencelVal').val(cellcontent);	
 		 breakdonView(rowid,iCol,cellcontent);
	 
		}	
	});
 
    
}
function breakdonView(rowid,iCol,cellcontent){
	 
	var colm = jQuery("#rootGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	
	 jQuery("#hiddenrowid").val(selId);
	/*
	var dateflag = selId.substring(0, 1);
	
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	*/
	
	
	 
}
function frmFilter_enableDisableSuccessCallBack(){ 
	enableDisableDatenMonthFilter();
	monthDiff(2,"dtefromMonth");
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
	var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
	var rootCauseID = getFilterValue(filterString, "cmbbdrootcause");
	jQuery("#hiddenRootCauseID").val(rootCauseID);
	
	if(removeBlank.trim() == "")
		jQuery("#hiddenRemoveBlank").val("Y");
	else
		jQuery("#hiddenRemoveBlank").val(removeBlank);
	
	
	
	
		var tableCaption = "Root Cause Report";
		//filterString += '&drillFlag=f';
		filterString += '&drillFlag=f';

		processGridnew(url,filterString,"rootGrid","pager",tableCaption,"doubleClickGrid","","rootGrid_loadComplete");
	
		return true;
	}
	return false;	
}
function doubleClickGrid(id)
{
	var circle = jQuery("#hiddencircle").val();
	var rowData = jQuery("#rootGrid").jqGrid('getRowData',id);
	var selId = rowData.keyid;
	var rootCauseId = jQuery("#hiddenRootCauseID").val();
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	if(checkForZeroes("rootGrid",id,4)){	
	hideGraphData();
	if(selId.substr(0,3) != 'MCH' && circle.substr(0,3) != "CRC"){
		var filterData ="?";
		if(rootCauseId != null && rootCauseId != '')
			filterData += "&cmbbdrootcause="+rootCauseId;
		filterData += "&chkRemoveBlank="+removeBlank;
		filterData += '&parentId='+ selId+'&drillFlag=f';
		
		var url = jQuery('#hiddenUrl').val();
			processGridnew(url,filterData,"rootGrid","pager",'',"doubleClickGrid","","rootGrid_loadComplete");
		}
			if(selId.substr(0,3) == 'MCH'){
		 	}
	}
	else
		alert("No Records to View ");
	
}

function rootGrid_onProcessGridBack(){
		
		var url = jQuery('#hiddenUrl').val();					
		var rowIds = jQuery("#rootGrid").getDataIDs();		
		var parentId =  jQuery("#rootGrid").jqGrid('getCell', rowIds[0], 'keyid');
		var rootCauseId = jQuery("#hiddenRootCauseID").val();
		var removeBlank = jQuery("#hiddenRemoveBlank").val();
		var dataString  ="";	
		
			dataString += 'drillFlag=b';
			if(rootCauseId != null && rootCauseId != '')
				dataString += "&cmbbdrootcause="+rootCauseId;
				dataString += "&chkRemoveBlank="+removeBlank;
		processGridnew(url,dataString,"rootGrid","pager",'',"doubleClickGrid","","rootGrid_loadComplete");			
	}	

function getParamName(rowid)
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
	else
		return null;
}
function validateFilterSelection(filterString){
	
	var selmnth = jQuery('#fromDate').val();
	var selmnths = jQuery('#toDate').val();
	var dtFromMonth = getFilterValue(filterString, "dtFromMonth");
	var dtToMonth = getFilterValue(filterString, "dtToMonth");

	if(filterString == "?q=1&firstClick=Y" )
		return true;
	else if( filterString.length != 0 && jQuery('#chkDatewise').is(':checked') == true)
	{
		 if( ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			//alert("Enter From month");
			return true;
		}
			
		else if( ! checkFilterValueExist(filterString, "dtToDate"))
		{
			//alert("Enter To month");
			return false;
		}
		 return true;			
	}	
	else if(filterString.length != 0 && jQuery('#chkMonthwise').is(':checked') == true)
	{
		
		var today = convertStringToDate('01-'+dtToMonth);
		var month,day,year;
		year=today.getFullYear();
		month=today.getMonth();
		date=today.getDate();
		if((month-12)<=0)
		year=today.getFullYear();
		var backdate = new Date(year,month-12,date);
		
		if(convertStringToDate('01-'+dtFromMonth)  < backdate){
			alert("From Month Should be within 1 Years");
			//jQuery('#dtefromDate').datebox('clear');		
			return false;

		}
		
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
	}
	return true;
	
}

function compareFromToMonth(dtFromMonth,dtToMonth)
{ //alert("hjf");
	var today = convertStringToDate('01-'+dtToMonth);
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-12)<=0)
	year=today.getFullYear();
	var backdate = new Date(year,month-12,date);
	
	if(convertStringToDate('01-'+dtFromMonth)  < backdate){
		alert("From Month Should be within 1 Years");
		//jQuery('#dtefromDate').datebox('clear');		
		return false;

	}
	return ;
}
/*
function checkForZeroes(tableId,selId,colNo)
{
	var Col = colNo+1;
 
	while(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != null)
	{	
		 
		if(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != '0')
			return true;
		Col++;
	}
	 
	return false;
		
}		*/	

</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->


<form name="frmrootreport" id="frmrootreport" >
<div style="margin-left:24px;max-width:900px;">
<table style="width:100%;">
<tr>
	<td>
	    <div style="margin-top:-0px;">
			<label id="eqpnote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;">${requestScope.drilldownMsg}</label>
			<label id="eqpfnote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;">${requestScope.rootgraph}</label>
		
			<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   
			<input type="button" id="bdbtn" onclick="" class="easyui-button" value="View BD"/>
		</div>
	</td>
</tr>
</table>
</div>
<!--<div class="floatright grdGraphBtnPos" style="padding-right:20px;">-->
<!---->
<!--</div>-->
<div id="wrapperRpt" style= margin-top:2px;>

<div id="divGraphContainer" ></div>	
<!--<div class="clear"></div>-->

<table id="rootGrid" ></table>
<div id="pager"></div>

</div>

<!--<div style="margin-top: 25px;">-->
<!--	<label  style="font-weight: bold;padding-left:40px;padding-right:20px; ">${requestScope.drilldownMsg}</label>-->
<!--	<span>-->
<!--		<label  style="font-weight: bold;padding-left:20px;padding-right:20px; ">${requestScope.rootgraph}</label>-->
<!--	</span>-->
<!--	<span style="padding-left:300px;">-->
<!--		<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   -->
<!--		<input type="button" id="bdbtn" onclick="" class="easyui-button" value="View BD"/>-->
<!--	</span>-->
<!--</div>-->
<!---->
<!--<div id="wrapperRpt" style="margin-top: 0px;">-->
<!--	-->
<!--<table id="rootGrid" ></table>-->
<!--<div id="pager"></div>-->
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddencelVal"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hiddenRootCauseID"  />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hiddencircle" value=""  />
</form>