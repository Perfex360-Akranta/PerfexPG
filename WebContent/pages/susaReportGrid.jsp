<script>
jQuery(document).ready(function(){
	initialiseForm('frmsusagrid');
	jQuery('#submitForm').val('frmsusagrid');
//	processGridnew("susa_input.saau","?q=2","susaGrid","pagersusa","","susamainDoubleClick","","");
	
	viewGrid("susaReport_input.saau","q=2");
	
/*	jQuery ("#btnNew").click(function(){
           
		
		navigateToNextForm("susaform_input.saau?grid=false","");
	});*/
    

});

function viewGrid(url,dataString)
{
	
	//dataString += "&disable=N";
	//alert(dataString);
	processGridnew(url,dataString,"susaGrid","pagersusa","","susamainDoubleClick","","");
	return true;
}

function susamainDoubleClick(id)
{	
	var rowData = jQuery("#susaGrid").jqGrid('getRowData',id);	
	var keyId = rowData.KEYID;
	var susaflid=rowData.susaflid;
	//alert(keyId + "  " +susaflid);
	if(keyId != null && keyId.length > 0)
		window.open("Susaexcel_Excelview..saau?susaid="+keyId+"&flid="+susaflid);
		
   
	//navigateToNextForm("susaform_input.saau?&keyId="+keyId,"");
	
}


</script>

<div id='wrapperRpt' >
<table >
<tr>
	<td >
			<div >
			<!-- 
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
				 -->
			</div>
		</td>
		
		</tr>
</table>
<table id='susaGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagersusa'></div>
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>
<input type="hidden" name="hdnFlid" id="hdnFlid" value=""/>
