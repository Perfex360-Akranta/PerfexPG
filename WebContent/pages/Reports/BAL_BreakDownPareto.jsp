<!--Author Manikandan.R -->
<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	viewGrid(actionPart,"?");

	jQuery('#btnGraph').click(function(){
		//	alert("jjjjj");
			var rowid = jQuery("#breakdownPareto").jqGrid('getGridParam','selrow');
			///*commented on june 13 2012*/if(rowid == null)
				//alert("")
			//alert(rowid);
			var url = "chart.Bbdmprto?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
		//	alert("url" + url);
			showGraphData(url);
		});
	
});


function breakdownPareto_loadComplete()
{	
	//for display and hide back button
	hideShowBack(false);			
	/*var rowIds = jQuery("#breakdownPareto").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#breakdownPareto").jqGrid('getCell', rowIds[0], 'LossId');					
			hideShowBack(true);
			if(parentId.substr(0,3) == 'CMP')			
				hideShowBack(false);		jqgh_breakdownPareto_MName	
		}*/
}
	
function viewGrid(url,filterString)
{
		
	if(filterString=="?"){		
		processGridnew(url,filterString,"breakdownPareto","pager",tableCaption,"breakdownPareto_doubleClickGrid","","breakdownPareto_loadComplete");
		return true;
	}
	else if( validateFilterSelection(filterString))
	{
		var tableCaption = "breakdownPareto Report";
		processGridnew(url,filterString,"breakdownPareto","pager",tableCaption,"breakdownPareto_doubleClickGrid","","breakdownPareto_loadComplete");
		return true;
	}
	return false;	
}


function validateFilterSelection(filterString){
	var txttop = jQuery("#txttop").val();
	
	/*if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
	{
		alert("Select  FromMonth");
		return false;
	}
	else if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
	{
		alert("Select  ToMonth");
		return false;
	}*/	

	 if(txttop > 50){
		alert('Please Enter Top value Below 50');
		return false;
	}
	/*else if(jQuery('#chkallchkbox').attr('disabled',true))
		{
			if(jQuery('#chkoccurchkbox').is(':checked') == true && jQuery('#chktimechkbox').is(':checked') == true)
	 			{
	 				alert("Select Either Time or Occurence not Both");
	 				return false;
	 	 			}
				else
					return  true;
	 	}*/
	else
		return  true;	
	
}
/*function breakdownPareto_doubleClickGrid(id){ 
		var rowData = jQuery("#breakdownPareto").jqGrid('getRowData',id);
		var selId = rowData.keyid;
		/*show hide back in grid toolbar*/
		
		//hideShowBack(true);
		/*if(selId.substr(0,3) != 'MCH'){
			
			var filterData = '&parentId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
			//viewGrid(url,filterData);
			processGridnew(url,filterData,"breakdownPareto","pager",'',"breakdownPareto_doubleClickGrid","","breakdownPareto_loadComplete");	
		}

	}*/

/*function breakdownPareto_onProcessGridBack(){		
		
		var actionPart = jQuery("#hiddenUrl").val();//document.getElementById('hiddenUrl').value;					
		var backVal=true;
		var rowid=jQuery("#breakdownPareto").getDataIDs()[0];
		var rowData = jQuery("#breakdownPareto").jqGrid('getRowData',rowid);
		var keyid = rowData.kaizenId;			
		if(keyid.substring(0,3) != 'CMP')
		{				
			var dataString = '?q=2&backVal='+backVal;
			jQuery("#breakdownPareto").GridUnload();		
			viewGrid(actionPart,dataString);
		}
		else			
			viewGrid(actionPart,"?");
		
 }*/
 function frmFilter_enableDisableSuccessCallBack()
 {
	 enableDisableDatenMonthFilter();
 	//enableFields('dteyear');
 /*	jQuery("#chkDatewise").attr('checked',false);
 	jQuery("#chkMonthwise").attr('checked',true);
	jQuery("#chkMonthwise").attr('disabled',false);		*/					
 }
</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<div id="wrapperRpt">
<div style="margin-top: -28px"><input id="btnGraph" class="easyui-button"  type="button" value="Graph"/></div>
<div id="divGraphContainer" ></div>	
<div>
<table id="breakdownPareto" ></table>
<div id="pager"></div>

</div>
</div>
<input type="hidden" id="hiddenStr" value="sdsd" />
	