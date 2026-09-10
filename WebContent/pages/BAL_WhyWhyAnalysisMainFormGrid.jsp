<script>
jQuery(document).ready(function(){

	jQuery ("#btnNew").click(function(){
		//alert(jQuery("#hdnFilterString").val());
		navigateToNextForm("whywhyanalysismodify_input.why?"+jQuery("#hdnFilterString").val()+"&filterButton=false");
	});
	
	viewGrid("whywhyanalysismaingrid_input.why","q=2&"+jQuery("#hdnFilterString").val());
});


function viewGrid(url, filterStr) {

	
	//alert("Url :" +url);
	//alert("filter String :" +filterStr);
	processGridnew("whywhyanalysismaingrid_input.why",filterStr,"achievementgrid","pager","","docDoubleClick");
	return true;
	
	
}
function validateFilterSelection(filterString)
{
	
	return  true;
}

function docDoubleClick(rowid)
{	

	var rowData = jQuery("#achievementgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.KEYID;
	// alert(keyid);
	
	navigateToNextForm("whywhyanalysismodify_input.why?keyid="+keyid+"&hdnMode="+jQuery("#hdnMode").val()+"&"+jQuery("#hdnFilterString").val()+"&filterButton=false");
	
}

jQuery('#btnSelectTab').click(function(){

	var rowid=jQuery('#achievementgrid').jqGrid('getGridParam','selrow');
	if(rowid == '' || rowid == null || rowid == 'undefined'){
		alert('Select Row To Input / View Details');
		}
	else
		docDoubleClick(rowid)
		
});
</script>



<form>
<div id='wrapperRpt' style="width:85%">


<div>
<table>
	<tr>
	<td >
			<div style="padding-left:0%;padding-left:0%\9;margin-top: -31px ">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			
			<span style="padding-left:30px;margin-top: 31px ">
				<input id="btnSelectTab" name="btnSelectTab" class="easyui-button"  type="button" value="For Tab" style="width:70px; height:24px;"/>
			</span>
		
<!--		<td style="margin-top: -10px" >
			<div style="width : 332px;width: 325px\9;margin-top: -17px;padding-left: 77px" >-->
			<span style="padding-left:30px;">	<label class="notes";style="margin-top: -10px;font-style :bold;"> Double Click on row to input/view details </label>
			</span></div>
<!--		</td>-->
		</td>
	</tr>
</table>

</div>
<div style="margin-top: -9px">
<table id='achievementgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
</div>

 <input type="hidden" id="hdnFilterString" value="${requestScope.filterStr}" />
 <input type="hidden" id="hdnMode" value="${requestScope.mode}" />

</form>