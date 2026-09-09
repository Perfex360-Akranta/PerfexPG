



<script>
jQuery(document).ready(function(){

	//DailyActivityReport_input.strf(url in table(adm_tl_menumst(Mnum_menunumber=2713)))
	//servlet name is "strudent registrationform" though strf url pattern
 
	processGridnew("DailyActivityReport_input.strf","q=2","capareportgrid","pager","","docDoubleClick");
	            //function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback,selectRowFunction ){

});

function docDoubleClick(id)
{	
	var rowData = jQuery("#capareportgrid").jqGrid('getRowData',id);
	var keyid = rowData.OTH_TL_ID;

	//alert(keyid+code+resource);

	navigateToNextForm("DailyActivity_input.strf?&keyid="+keyid);
	
}



</script>




<div id='wrapperRpt' style="width:85%">


<div>

</div>
<div style="margin-top: -5px">
<table id='capareportgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>





<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>

 

