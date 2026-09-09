
<script>
jQuery(document).ready(function(){
	initialiseForm('frmknwwhygrd');
	jQuery('#submitForm').val('frmknwwhygrd');
	
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		//processAjaxCalls("openFile.file?fileName=Kaizen_Format.pdf", "", "", "", "", "new");					
	}); 
	
	
	viewGrid("","");
	jQuery ("#btnNewknwWhy").click(function(){
		var mode= jQuery("#mode").val();
		navigateToNextForm("KnowWhygrid_input.KnowWhy?grid=false&clearfrom=true&mode="+mode);

    });	
	
});
function viewGrid(url,filterString)
{

	if( validateFilterSelection(filterString))
	{
		var mode= jQuery("#mode").val();
		if(mode== "approval" && mode!=null){
			jQuery ("#btnNewknwWhy").hide();
			processGridnew("KnowWhy_input.KnowWhy","&mode=modify&"+filterString,"knowwhy","pager","","doubleclickknwWhy");
		}
		 if(mode== "View" && mode!=null){
			jQuery ("#btnNewknwWhy").hide();
			processGridnew("KnwView_input.KnowWhy","&mode=View&"+filterString,"knowwhy","pager","","doubleclickknwWhy");
		}
		else if(mode== "Report" && mode!=null){
			jQuery ("#btnNewknwWhy").hide();
			processGridnew("KnwReport_input.KnowWhy","&mode=Report&"+filterString,"knowwhy","pager","","doubleclickknwWhy");
		}
		else{
			processGridnew("KnowWhy_input.KnowWhy","?q=2&"+filterString,"knowwhy","pager","","doubleclickknwWhy");	
		}
		return true;
		
	}
	
} 
function validateFilterSelection(filterString){
	
	return true;
}
function doubleclickknwWhy(id)
{	
	var rowData = jQuery("#knowwhy").jqGrid('getRowData',id);	
	var keyId = rowData.KEYID;
	var mode= jQuery("#mode").val();
	if((mode== "approval" && mode!=null)){
		//alert("Approval Mode::::"+mode);
		navigateToNextForm("KnowWhygrid_input.KnowWhy?grid=true&clearfrom=false&keyId="+keyId+"&mode=approval"+mode);
		return false;
		}
	else if((mode== "View" && mode!=null)){
		//alert("View Mode:::::"+mode);
		 navigateToNextForm("KnowWhygrid_input.KnowWhy?grid=true&clearfrom=false&keyId="+keyId+"&mode=View"+mode);
		 return false;
		}

	else if(mode== "Report" && mode!=null){
		var rowData = jQuery("#knowwhy").jqGrid('getRowData',id);
		var Knwid = rowData.KEYID;
		var flid=jQuery('#hdnLoginFlid').val();
		//window.open("OplReport_Excelview.oplrpt?oplId="+rowId);
		window.open("KnwwhyRpt_Excelview.KnowWhy?&Knwid="+Knwid+"&flid="+flid);
		return false;
		}
	else
		navigateToNextForm("KnowWhygrid_input.KnowWhy?grid=true&clearfrom=false&keyId="+keyId);
}
</script>

<form id="frmknwwhygrd">
<div id='wrapperRpt' >



<div>
<table>
	<tr>
	    <td>
			<div  style="margin-top: -28px">
			<input id="btnNewknwWhy" name="btnNewknwWhy" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px; display:none"/>
			</div>
		</td>
		<td>
<!--		<div><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>-->
		</td>
		<td>
			<div style="padding-left:10px;margin-top: -23px" >
			<label class="notes">Double Click on row to input/view details</label>
			</div>
		</td>
		
	</tr>
</table>

</div>

	<div style="margin-top: -8px">
<table  id='knowwhy' >
			<tr>
				<td></td>
			</tr>
		</table>
	
		<div id='pager'></div></div>
		</div>
		<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />

<input type="hidden" id="mode" name="mode"  value="${requestScope.mode}"/> 
</form>