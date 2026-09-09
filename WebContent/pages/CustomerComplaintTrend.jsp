<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
var url = jQuery('#hiddenUrl').val();
var dataString ="";
var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
var url = jQuery('#hiddenUrl').val();
if( prevDataUrl == null || prevDataUrl.length <=0){	
	viewGrid(url,"?q=1&firstClick=Y&module=Quality");
}	
else{
	viewGrid(unescape(prevDataUrl),"?&q=1");
}	
jQuery('#btnGraph').click(function(){
	var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
	var rowData = jQuery("#list").jqGrid('getRowData',rowid);
	var selId = rowData.keyid;
	if(rowid == null || rowid =="")
	{
		if( jQuery('#chkcummulative').is(':checked') == true){	
			var url = "customerCumplaintTrendchart.customerComplaintRpt?rowid=" + selId + "&rownum="+rowid + "&chkcummulative=1";
			showGraphData(url);
		}
		else if(jQuery('#chkcummulative').is(':checked') == false)
			alert("Select Row To View Graph");
	}  
	else{
		if(checkForZeroes("list",rowid,2)){	
			 if(jQuery('#chkcummulative').is(':checked') == true){	
			   var url = "customerCumplaintTrendchart.customerComplaintRpt?rowid=" + selId + "&rownum="+rowid + "&chkcummulative=1";
			 }else{
			   var url = "customerCumplaintTrendchart.customerComplaintRpt?rowid=" + selId + "&rownum="+rowid ;	 
			 } 
			showGraphData(url);
		}else 
			alert("No Record to View Graph");
	}
});
	
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{	
		/*if(jQuery('#chkrejectionchkbox').val()=="on"){
			filterString+="&chkrejectionchkbox=1";
	 	}else{
	 		filterString+="&chkrejectionchkbox=0";
	 	}*/
		filterString +='&module=Quality';
		filterString += '&drillFlag=f';
		jQuery('#hdnFilterString').val(filterString);
		
		processGridnew(url,filterString,"list","pager","","doubleClickGrid","","trendGrid_loadComplete");
		return true;
	}
	return false;
}

function validateFilterSelection(filterString){
		return  true;
}
function doubleClickGrid(id){ 
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var selId = rowData.keyid;

	var filterData ="";
	
 	filterData += 'parentId='+ selId+'&drillFlag=f&module=Quality';
	var url = jQuery('#hiddenUrl').val();
 	var filter=jQuery('#hdnFilterString').val();
 	
 	if(selId.trim() != "")
		processGridnew(url,filterData,"list","pager",'',"doubleClickGrid","","trendGrid_loadComplete");	
		
}
function trendGrid_loadComplete(){
	var row = jQuery("#list").jqGrid('getDataIDs');
	var rowIds = jQuery("#list").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#list").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
			}
		}
	setTotalRowCss('list');
	jQuery("#" + row[row.length-2]).find("td").addClass('cumulativeRow');
	setTotalRowCss('list');
	jQuery("#" + row[row.length-1]).find("td").addClass('totalRow');
	jQuery("#trendnotes").css('display','block');	
	jQuery("#trendgraph").css('display','block');
}
function list_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#list").getDataIDs();

	var parentId =  jQuery("#list").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b&module=Quality';		
	
	processGridnew(url,dataString,"list","pager",'',"doubleClickGrid","","trendGrid_loadComplete");			
}

</script>
<form>
<table style="margin-top:10px;">
<tr >
<td>
<div style="margin-left:20px;width:400px;">
<label id="trendnotes"  class=""   style="font-weight: bold; padding-left:20px; margin-top:0px;display: none;" > ${requestScope.drilldownMsg}</label>
<label id="trendgraph"  class=""   style="font-weight: bold; padding-left:20px;margin-top:2px; display: none;" > ${requestScope.GraphMsg}</label>
</div>
</td>
<td>
<div  style="valign:top;margin-left:674px;margin-left:654px\9;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="width:54px;"/></div>
</td>
</tr>
</table>

 <div id="wrapperRpt" style= "margin-top:2px;align:center;">
<div id="divGraphContainer" ></div>

<table id="list" style="valign:top;">
	</table>
<!--	<table id="EDTGrid" ></table>-->
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid" />
<input type="hidden" id="hiddeniCol" />
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
</div>
</form>