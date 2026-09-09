<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	var dataStr="?q=2";	
	initialiseForm('frmsafe');		
	viewGrid(url,dataStr);	
    //processGridnew(url ,filterString,"SafeReport","pager","","doubleClickGrid");


	  jQuery('#btnGraph').click(function(){
					var rowid = jQuery("#SafeReport").jqGrid('getGridParam','selrow');
					//alert(rowid);
					if(rowid == null || rowid =="")
					{
						alert("Select Row To View Graph");
					}  
					else{
							if(checkForZeroes("SafeReport",rowid,2)){
								 var url = "Safeworkchart.uwp?&rownum="+rowid ;
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
		var flid = getFilterValue(filterString, 'flid');
		var sectid = getFilterValue(filterString, 'cmbSectid');
		if(!(sectid.length>0) && filterString!="?q=2"){
			alert("Select DMT");
			return false; 
		}
		if(flid.length>0)
			jQuery("#flid").val(flid);
		else{
			flid=jQuery("#flid").val();
			filterString+="&flid="+flid;
		}
		 processGridnew(url ,filterString,"SafeReport","pager","","doubleClickGrid");
		
		return true;
	}
	return false;
}
function validateFilterSelection(filterString){
	return true;
	
}
function frmjob_FuntLocHierarchy_SuccessCallBack(result)
{
	var url = jQuery('#hiddenUrl').val();
	var cellId=result.cellId;
    var flId=result.flId;
    //alert(flId);
	var filterString="&cellid="+cellId+"&flid="+flId;
	viewGrid(url,filterString);
}

</script>
<form name="frmsafe" id="frmsafe">
	<div id='wrapperRpt'>
		<div style="float: left;padding-left: 24px">
			<table style="width:100%;">
				<tr>
					<td style="padding-left:0px;"><span><label id="trendgraph"  class="notes"   style="font-weight: bold; display: block;" > </label></span></td>
						<td>
						    <input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>
						</td>
					</tr>
				<tr>
			</table>

<table id='SafeReport'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>


</div>
</div>
</form>