<script type="text/javascript">

jQuery(document).ready(function(){

	processGridnew("ManagerCompetencyFormat_input.mcf","q=2","Managercompfrmtgrid","Managercompfrmtpager","","docDoubleClick","","");


});

function docDoubleClick(id)
{	
	
	var rowData = jQuery("#Managercompfrmtgrid").jqGrid('getRowData',id);	
	//var keyId = rowData.FVAS_KEYID;
	//alert(keyId);
	LoadPopUp("",'ManagerCompetency_input.mcf',true,"78%","450px","14%","8%","","Manager Competency Format");
    
}


 
</script>


<form action=" " method="post" id="frmmanagercompfrmt" name="frmmanagercompfrmt">
<div id="wrapperRpt">
<div style="margin-top:20px;margin-left:40px;">
		<table id='Managercompfrmtgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='Managercompfrmtpager'></div>
</div>
</div>
</form>