
<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmCompGallery');
	jQuery ("#btnNew").hide();
	jQuery ("#btnNew").click(function(){
		LoadPopUp("ComplaintGalleryView","ComplaintGalleryView_input.compg?grid=false&clearfrom=true",true,"80%","94%","0%","5%","","Complaint Gallery",false,true);
	});
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
	{
		processAjaxCalls("openFile.file?fileName=Complaint Gallery.xlsx", "", "", "", "", "new");					
	});
	viewGrid("ComplaintGallery_input.compg","?q=2");
	
});
function doubleclick(id)
{	var rowData = jQuery("#complaintgrid").jqGrid('getRowData',id);
	var Keyid = rowData.KEYID ;
	var dataStr = "&grid=true&clearfrom=false&Keyid="+Keyid;
	LoadPopUp("ComplaintGalleryView","ComplaintGalleryView_input.compg?"+dataStr ,true,"80%","94%","0%","5%","","Complaint Gallery",false,true);
}
function ComplaintGalleryView_onClose(){
	jQuery("#complaintgrid").trigger("reloadGrid");
	return true;
}
function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"complaintgrid","compGalPager","","doubleclick","","");
		return true;
	}
	return false;
		
}
function validateFilterSelection(filterString)
{
	/* if( ! checkFilterValueExist(filterString,"cmbCompid"))
	 {
		if(filterString==null || filterString==''||filterString=="")
		{}
		else
		{
			alert("Select Functional Location");
			return false;}
		}*/
	return true;
}
</script>

<form name="frmCompGallery" id="frmCompGallery">

<div id="wrapperRpt">
	<table>
		<tr>
			<td >
				<div style=" padding-left:2%;margin-top: -30px">
					<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
				</div>
			</td>
			<td >
				<div style="padding-left:10px;padding-left:0px\9;" >
		<!--		<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 24px; width : 90px;"/>-->
		   		</div>
			</td>
			<td >
				<div style="padding-left:10px;padding-left:0px\9;margin-top: -25px" >
					<label class="notes"> Double Click on row to input/view details </label>
				</div>
			</td>
		</tr>
	</table>
	<div style="margin-top: -8px">
		<table id='complaintgrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='compGalPager'></div>
	</div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>
