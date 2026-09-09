<script type="text/javascript">
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId("frmKaizenCumulative");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();
	//alert(actionPart);
	//viewGrid(actionPart,"?q=1");
	   
	jQuery('#btnLineGraph').click(function(){
		
		var rowid = jQuery("#kznCumlative").jqGrid('getGridParam','selrow');
		var rowData = jQuery("#kznCumlative").jqGrid('getRowData',rowid);				
		var url = "KznsugglineChart.imvscom";// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
		//alert(url);
		showGraphData(url);
		
	});
   
});
/*function impVscomp_loadComplete()
{	
	var rowIds = jQuery("#impVscomp").getDataIDs();	
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var keyField =  jQuery("#impVscomp").jqGrid('getCell', rowIds[0], 'KEYFIELD2');		
			var celldata= keyField.split("#");	
			var parentId=getValueBySeparator(celldata[2],"N","#");		
			if(parentId.contains('B'))			
				hideShowBack(false);
			else			
				hideShowBack(true);		
			/*if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	*/
/*		}
	var eleType=getValueBySeparator(celldata[1],"L","#");
	var levHeader= setDrillHeader(eleType);
	jQuery("#CH1-0").html(levHeader);
	setTotalRowCss('impVscomp');
	var row = jQuery("#impVscomp").jqGrid('getDataIDs');		
	jQuery("#impVscomp").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	//jQuery("#impVscomp").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
	//{
		doubleClickGrid(rowid,iCol);
				
	//}});	
	
	
}*/

	
function setDrillHeader(eleType){
	var levHeader="";
	if(eleType.trim()=="CMP")
		levHeader="Company";
	else if(eleType.trim()=="LCN")
		levHeader="Mill";
	else if(eleType.trim()=="SBU")
		levHeader="SBU";
	else if(eleType.trim()=="PBU")
		levHeader="PBU";
	else if(eleType.trim()=="L")
		levHeader="DMT";
	else if(eleType.trim()=="C")
		levHeader="JH";
	else if(eleType.trim()=="M")
		levHeader="Machine";
	return levHeader;			
}

function frmFilter_enableDisableSuccessCallBack()
{
	//jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		//filterString += '&skipLine=Y';
		var tableCaption = "Kaizen Cumulative";
		processGridnew(url,filterString,"kznCumlative","pager",tableCaption,"","","kznCumlative_loadComplete");		
		return true;
	}
	return false;	
}
function frmKaizenCumulative_afterLoadCallBack(){
	 toggleCommonFilter();
	}
function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
	/*if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Select Monthwise Checkbox");
		return false;
	}*/
	if(jQuery('#chkMonthwise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		/*else{
			 
			jQuery('#hdnfromDate').val(jQuery('#dtefromMonth').datebox('getValue'));
		}*/
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}
	return  true;
}


function impVscomp_loadComplete()
{
	setDrillDownHeader("CH1-0","kznCumlative","KEYFIELD2");
	setTotalRowCss('kznCumlative');
}

function impVscomp_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("kznCumlative","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			

/*function doubleClickGrid(id){ 	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("kznCumlative","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}*/
</script>
<form id="frmKaizenCumulative">
<div id="wrapperRpt" style="margin-top:9px;"> 
<table> 
<tr>
	<td>
	
	<div>
	<input id="btnLineGraph" class="easyui-button"  type="button" value="Graph"/>
	</div>
	<label id="trendgraph"  style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" > ${requestScope.GraphMsg}</label>
	</td>
    
</tr>
<tr>
<td colspan="2">
<div><table id="kznCumlative" style="width:100%;"><tr><td/></tr></table>
			 <div id="pager"></div>
			 </div>
			 </td>
 </tr>
</table>
</div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
</form>
