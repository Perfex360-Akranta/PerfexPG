

<script type="text/javascript"><!--
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "DrillDown (OPL) Report";
		processGridnew(url,filterString,"oplGrid","pager",tableCaption,"doubleClickGrid","","opldrillDwn_loadComplete");
		
		//alert(filterString);
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}

function oplGrid_onProcessGridBack(){

	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#oplGrid").getDataIDs();
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	
	var keyField =  jQuery("#oplGrid").jqGrid('getCell', rowIds[0], 'mainkeyid');
	var celldata= keyField.split("#");	
	var parentId=celldata[0];
	var click=getValueBySeparator(celldata[2],"N","#");	
	var dataString = 'drillFlag=b';	
	dataString += '&flid='+ parentId;	
	dataString += "&chkRemoveBlank="+removeBlank;
	if(click.contains('B'))
		return false;
	if( parentId != null&&parentId.length>0){
		
		if(parentId.substr(0,3) == 'CMP')
		{
			return;
		}	
	}

	loadNextDrillLevel("oplGrid",rowIds[0],'b',"mainkeyid");		

}			
					
function doubleClickGrid(id){ 
	var rowData = jQuery("#oplGrid").jqGrid('getRowData',id);
	var circle = jQuery("#hiddencircle").val();
	var keyField = rowData.mainkeyid;
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var celldata= keyField.split("#");	
	var selId=celldata[0];
	var click=getValueBySeparator(celldata[2],"N","#");	
	if(click.contains('Y') || click.contains('B')){
	//if(checkForZeroes("oplGrid",id,1)){	
		//if(selId.substr(0,3) != 'MCH' && circle.substr(0,3) != "CRC"){
	
		//	var filterData ="?";
			
		//	filterData += '&flid='+ selId+'&drillFlag=f';
		//	filterData += "&chkRemoveBlank="+removeBlank;
			
			//var url = jQuery('#hiddenUrl').val();
			//alert("url  "+url);
			loadNextDrillLevel("oplGrid",id,'f',"mainkeyid");

		}
		/*if(selId.substr(0,3) == 'MCH'){}
		else if(selId==null || selId.trim()==''){	
			return false;
		}
	}else
		alert('No Records to view');
	}*/
	else
		return false;
}

function opldrillDwn_loadComplete()
{
	setDrillDownHeader("oplGrid_Company","oplGrid","mainkeyid");
	
	setTotalRowCss('oplGrid');
}


</script>
<div id="wrapperRpt">
<div style="margin-top: -28px">
	<label class="notes" style="font-weight: bold;">${requestScope.oplDrillDwnMsg}</label>
	<table id="oplGrid" ></table>
	<div id="pager"></div>
	</div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
</div>	