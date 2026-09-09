
<script>

jQuery(document).ready(function(){//alert(1);	

	processGridnew("criticalprocessNew_input.cprc","?q=1","ProcessCriticalMainGrid","ProcessCriticalpager"," ","docDoubleClick");
	
	jQuery ("#btnNew").hide();
	jQuery ("#btnNew").click(function()
	{

	   navigateToNextForm("criticalprocess_input.cprc","Critical Process Parameter");	
		
    });
	
	//pls send Script for adding constraint.
});

function viewGrid(url,filterString){
	processGridnew("criticalprocessNew_input.cprc","?q=1"+filterString,"ProcessCriticalMainGrid","ProcessCriticalpager"," ","docDoubleClick");
	return true;
}

function docDoubleClick(id)
{	//alert(12);
	var rowData = jQuery("#ProcessCriticalMainGrid").jqGrid('getRowData',id);
	var keyid=rowData.KEYID;
	//alert(" keyid :: "+keyid);
	var flId=rowData.FNLNKEYID;
	var Date=rowData.CDATE;
	//alert(" Date :: "+Date);
	//alert(" SubString :: "+Date.substring(7,11));
	//alert(" Date.append() :: "+Date.append());
	
	//if(Date.SubString(7,10))
    var Parameter=rowData.PARAMETER;
    navigateToNextForm("criticalprocess_input.cprc?&new=F&Date="+Date+"&flId="+flId+"&keyid="+keyid+"&Parameter="+Parameter,"Critical Process Modification");
    
}

</script>
<form>
    <div style="margin-left:20px;margin-top: 10px;">
    <div style="margin-top: 0px;"><input class="easyui-button" type="button" id="btnNew" name="btnNew" value="New" style=" width : 49px;height:22px;">
<!--    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView"  style='height:22px' value="View Report"></span>-->
    <span style="padding-left:10px;">Double Click on row to input/view details</span>
    </div>
		<table id='ProcessCriticalMainGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='ProcessCriticalpager'></div>
	
	 </div>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
</form>