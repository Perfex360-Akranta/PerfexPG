<script type="text/javascript"><!--

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("EquipmentDownTime_input.BeDTRpt","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"");
	}	

	/*
	jQuery('#btnGraph').click(function(){
	 	var rowid = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
	 	
	 	if(rowid !=null){
			if(checkForZeroes("EDTGrid",rowid,4)){	
	 
				var url = "chart.eDTRpt?rowid="+rowid+"&" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=L")+"&chType=L";
				showGraphData(url);
		  	 }		
			 else 
		  		alert("No Record to View Graph");		 
		}
		else {
			var url = "chart.eDTRpt?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);
		}
	});
	*/
	  
	jQuery('#btnGraph').click(function(){

		var rowid = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
		var flid = "";
		var level = "";
		if(rowid == null || rowid == undefined )
			{
			 var keyData = jQuery("#EDTGrid").jqGrid('getCell',"1", 'keyid');
			 flid =getValueBySeparator(keyData,"PF","#");
			 level = "null";
		   }
		else{
			flid=jQuery("#EDTGrid").jqGrid('getCell',rowid,'keyid');
			level = jQuery("#EDTGrid").jqGrid('getCell',rowid,'code');
     		}	
			
		var url = "chart.BeDTRpt?level="+level+"&flid="+flid;
		showGraphData(url);
	});
	
	 jQuery("#bdbtnSplit").click(function(rowid){
			var rowid = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
		  	if( rowid == null)
				alert("Select on Row to View BreakDown Split Breakup ");
			else{
			 	var selid = jQuery("#hiddenrowid").val();
			 	var url = jQuery("#EDTGrid").jqGrid('getGridParam', 'url');
				url = url.replace("EquipmentDownTime_getData.eDTRpt","EquipmentDownTime_input.BeDTRpt");
				url = escape(url); 
				if(checkForZeroes("EDTGrid",rowid,3)){					
				if(selid != "codeFiel"){
				 
					var cellVal =jQuery("#hiddeniCol").val();
					  	if(cellVal =='0'){
							alert("No Data to view");
							return false;
					  	}else
							navigateToNextForm("BDSplitRpt_input.bdFailRpt?"+getParamName(rowid)+"="+rowid+"&selid="+selid,"Breakdown Split Breakup",null,{"filterString":url});
				 
				}else
					navigateToNextForm("BDSplitRpt_input.bdFailRpt?"+getParamName(rowid)+"="+rowid,"Breakdown Split Breakup",null,{"filterString":url});
				}
				else
					alert("No Data to View");
			}
		});
		 	
	
	 jQuery("#bdbtn").click(function(rowid){
		 
	 	var rowid = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
	 //	var keyData = jQuery("#EDTGrid").jqGrid('getCell', rowid, 'keyId');	
	 	var keyData = jQuery("#EDTGrid").jqGrid('getRowData',rowid);				
	 	keyData = keyData.keyid;
	  	if( rowid == null)
			alert("Select on Row to View BreakDown ");
		else{
		 	var selid = jQuery("#hiddenrowid").val();
		 	var url = jQuery("#EDTGrid").jqGrid('getGridParam', 'url');
			url = url.replace("EquipmentDownTime_getData.eDTRpt","EquipmentDownTime_input.BeDTRpt");
			url = url.replace("drillFlag=b","");
			url = escape(url); 
			if(checkForZeroes("EDTGrid",rowid,3)){	
			if(selid != "codeFiel"){
			 
				var cellVal =jQuery("#hiddeniCol").val();
				  	if(cellVal =='0'){
						alert("No Data to view");
						return false;
				  	}else
						navigateToNextForm("brkdown_view.Bbrdn?"+getParamName(keyData)+"="+keyData+"&selid="+selid,"Breakdown Analysis View",null,{"filterString":url});
			 
			}else
				navigateToNextForm("brkdown_view.Bbrdn?"+getParamName(keyData)+"="+keyData,"Breakdown Analysis View",null,{"filterString":url});
			}
			else
				alert("No Data to View");
		}
		
	 
	});
	 	
});
function bdbtn_successCallBack(result)
{
	alert("IN BD BUTTON SUCCESSCALLBACK");
	}

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
		
		var tableCaption = "EquipmentDownTime Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"EDTGrid","pager",tableCaption,"EDTGrid_doubleClickGrid","","EDTGrid_loadComplete");
		
		
		
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

/*function EDTGrid_loadComplete(){
 
 
	var rowIds = jQuery("#EDTGrid").getDataIDs();
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#EDTGrid").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);				
			}
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	
		}
		jQuery("#eqpnote").css('display','block');	
		jQuery("#eqpfnote").css('display','block');	
		//hideJqGridRow("EDTGrid","EDTGridghead_0");

		//if(rowIds[3].trim()=="TOTAL")
			//setTotalRowColorForGroupby("EDTGrid");
	 
			if(jQuery('#EDTGrid tr').hasClass('totalRow')){
				jQuery(".totalRow >td:last-child").html("");
				jQuery(".totalRow >td:last-child").prev().html("");
			 } 
		  jQuery("#EDTGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			 jQuery("#hiddeniCol").val(cellcontent);
			  
			
			  breakdonView(rowid,iCol,cellcontent);
		 
			}	
		});
 }*/
function breakdonView(rowid,iCol,cellcontent){

 	var colm = jQuery("#EDTGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	 
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	
	 jQuery("#hiddenrowid").val(selId);
	 
}/*
function doubleClickGrid(id ){ 
	var rowData = jQuery("#EDTGrid").jqGrid('getRowData',id);	
	var circle = jQuery("#hiddencircle").val();
	var selId = rowData.keyid;
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	if(checkForZeroes("EDTGrid",id,5)){
	
		if(selId.substr(0,3) != 'ASM' && circle.substr(0,3) != "CRC"){
			var filterData ="?";
		 	filterData += '&flid='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
			filterData += "&chkRemoveBlank="+removeBlank;
			processGridnew(url,filterData,"EDTGrid","pager",'',"doubleClickGrid","","EDTGrid_loadComplete");	
		}
	 
		if(selId.substr(0,3) == 'ASM'){
			//jQuery('#cmbFact').combobox('setValue',selId);
		}
	}
	else
		alert("No Records to View");
}

function EDTGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#EDTGrid").getDataIDs();

	var parentId =  jQuery("#EDTGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var dataString = 'drillFlag=b';		
	dataString += "&chkRemoveBlank="+removeBlank;
	processGridnew(url,dataString,"EDTGrid","pager",'',"doubleClickGrid","","EDTGrid_loadComplete");			
}
*/
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
	else if(rowid.substring(0,3)=='ASM')
		return 'cmbAssmbid';
	else
		return null;
}
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
	}
return true;
}


function EDTGrid_doubleClickGrid(id)
{
	alert(58);
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	alert(58 + "  j"+ keyfieldData);
	keyfieldData=setDrillDoubleClick("EDTGrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function EDTGrid_loadComplete()
{
	setDrillDownHeader("CH0-0","EDTGrid","keyid");
	setTotalRowCss('EDTGrid');
}
function EDTGrid_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("EDTGrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	
jQuery('#btnSelectTab').click(function(){

	var rowid=jQuery('#EDTGrid').jqGrid('getGridParam','selrow');
	alert(rowid);
	if(rowid=='' || rowid=='' || rowid==null){
		alert('Select The Location To Open the List');
		}
	else

		//var keyfieldData=jQuery("#hdnFnlnKeyid").val();	
	    //setDrillDoubleClick("EDTGrid","keyid",id,keyfieldData);
		EDTGrid_doubleClickGrid(rowid);
		
});
</script>

<form name="frmEdtreport" id="frmEdtreport" >
<div id="divGraphContainer" ></div>	

<table style="margin-left: 3.58%;">
<tr><td >
		<input id="btnGraph" class="easyui-button btn-HeightSmall"  type="button" value="Graph" />  
		<input type="button" id="bdbtn" onclick="" class="easyui-button btn-HeightSmall" value="View BD"/>
		<input type="button" id="btnSelectTab" onclick="" class="easyui-button btn-HeightSmall" value="For Tab"/>
		
 		<label Style="font-weight:bold;">Select on row to view Breakdown</label>
 		<!--<input type="button" id="bdbtnSplit" onclick="" class="easyui-button btn-HeightSmall" value="View BD Split Breakup" style=""/>
--></td>
<td>
<label id="eqpnote"  style="font-weight: bold;display: none; padding-left:3px;">${requestScope.drilldownMsgs}</label>
</td>
<td><label id="eqpfnote"  style="font-weight: bold;display: none; padding-left:10px;">${requestScope.eqpgraph}</label></td>
</tr>
</table>
<div id="wrapperRpt" style= margin-top:2px;>
<table id="EDTGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddenrowidbtn" value="" />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hiddencircle" value=""  />
<input type="hidden" id="hdnFnlnKeyid" />
</div>
</form>
	