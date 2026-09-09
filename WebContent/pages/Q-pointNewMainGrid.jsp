
<script>

jQuery(document).ready(function(){	//alert(12);

	jQuery("#btnNew").hide();
	//processGridnew("QPointNew_input.qp","?q=1","QPointMainGrid","QPointpager"," ","docDoubleClick");
	
	jQuery ("#btnNew").click(function()
	{

	   navigateToNextForm("QPointFormNew_input.qp?&filterButton=false","Q-Point");	
		
    });
	
	var url = jQuery('#hiddenUrl').val();
	//var flid=jQuery('#hdnLoginFlid').val();
	
	var filterString = "?q=1&firstClick=Y";//+flid;
	viewGrid(url,filterString);
		
});
/* 

function viewGrid(url,filterString)
{
		
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Q-Point Modification";
		if(filterString.length>=430){
		//var flid = getFilterValue(dataString, 'flid');
		//var dtFromDate = getFilterValue(dataString, 'dtFromDate');
		//var dtToDate = getFilterValue(dataString, 'dtToDate');
		//processGridnew(url,filterString,"oplGrid","pager",tableCaption,"doubleClickGrid","","oplGridOnLoad");
		//filterString+="&fliter="+flid+"&dtFromDate="+dtFromDate+"&dtToDate="+dtToDate;
		
		//filterString+="&filter=Gridfilter";
		processGridnew("QPointNew_input.qp",filterString,"QPointMainGrid","QPointpager",tableCaption,"docDoubleClick");
		
		}
		else
		 {
			processGridnew("QPointNew_input.qp","?q=1","QPointMainGrid","QPointpager"," ","docDoubleClick");
			//processGridnew("QPointNew_input.qp",filterString,"QPointMainGrid","QPointpager",tableCaption,"docDoubleClick");
		 }	
		
		return true;
	}	
} */

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Q-Point Modification";
		filterString+="&filter=Gridfilter";
		processGridnew("QPointNew_input.qp",filterString,"QPointMainGrid","QPointpager",tableCaption,"docDoubleClick");
	  return true;
	}
	else{
		return false;
	}
}  

function validateFilterSelection(filterString){
	return  true;
}


function docDoubleClick(id)
{	
	var rowData = jQuery("#QPointMainGrid").jqGrid('getRowData',id);
	var flId=rowData.FNLNKEYID;
    var Date=rowData.QPOINTDATE;
    var AREA=rowData.AREA;
    var KPOV=rowData.KPOV;
    var PREPAREDBY=rowData.PREPAREDID;
    var KEYID=rowData.KEYID;
    navigateToNextForm("QPointFormNew_input.qp?&new=F&filterButton=false&Date="+Date+"&flId="+flId+"&KEYID="+KEYID+"&AREA="+AREA+"&KPOV="+KPOV+"&PREPAREDBY="+PREPAREDBY,"Q-Point");
    
}

</script>
<form>
    <div style="margin-left:20px;margin-top: 10px;">
    <div style="margin-top: 0px;"><input class="easyui-button" type="button" id="btnNew" name="btnNew" value="New" style=" width : 49px;height:22px;">
<!--    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView"  style='height:22px' value="View Report"></span>-->
    <span style="padding-left:10px;">Double Click on row to input/view details</span>
    </div>
		<table id='QPointMainGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='QPointpager'></div>
	
	 </div>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
</form>