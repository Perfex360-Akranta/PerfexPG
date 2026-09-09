<script type="text/javascript">	
jQuery(document).ready(function(){

	initialiseForm('frmPMAdherencereportgrid');
	jQuery('#submitForm').val('frmPMAdherencereportgrid');

	processGridnew("PMAdherenceGrid_input.eqp","?q=1","PmadherencereportGrid","Gridpager"," ", "doubleclick");


});

function doubleclick(id)
{	
	var rowData = jQuery("#PmadherencereportGrid").jqGrid('getRowData',id );
	var keyid = rowData.Keyid;
	navigateToNextForm("PMAdherenceForm_input.eqp?keyid="+ keyid);
	setFieldValue("dteMonthdate",'');
	
}


	</script>
	
	
	<form id="frmPMAdherencereportgrid">

    <div id="wrapperRpt">
    
		<table id='PmadherencereportGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='Gridpager'></div>
		
	
	</div>
	<input type="hidden" id="mode" /> 
</form>
	