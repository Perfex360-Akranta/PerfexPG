
<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmTrainingloggrid');
	
	processGridnew("Trainingloggrid_input.tl","q=2","Trainingloggrid","pager","","doubleclick","","");
	jQuery ("#btnNew").click(function(){
		
		//LoadPopUp("Trainingloggrid","Traininglogvw_input.tl?grid=false&clearfrom=true",true,"95%","80%","15%","2%","","Traininglog grid",false,true);
		navigateToNextForm("Traininglogvw_input.tl?&grid=true&clearfrom=false");
		
	});
	
});
function doubleclick(id)
{	
	var rowData = jQuery("#Trainingloggrid").jqGrid('getRowData',id);
	var Program =   rowData.Program;	
	var ProgramBenefiy =   rowData.ProgramBenefiy;
	var TrainingMode =   rowData.TrainingMode;
	var TrainingType =   rowData.TrainingType;
	var Venue =   rowData.Venue;
	var StartDate = rowData.StartDate;
	var EndDate = rowData.EndDate;
	var Task = rowData.Task;
	var Objective = rowData.Objective;
	var duration = rowData.Duration;
	
		
	navigateToNextForm("Traininglogvw_input.tl?&grid=true&clearfrom=false&Program="+Program+"&ProgramBenefiy="+ProgramBenefiy+"&TrainingMode="+TrainingMode+"&TrainingType="+TrainingType+"&Venue="+Venue+"&StartDate="+StartDate+"&EndDate="+EndDate+"&Task="+Task+"&Objective="+Objective+"&Duration="+duration,"");

    
}

</script>

<form name="frmTrainingloggrid" id="frmTrainingloggrid">

<div id="wrapperRpt">

	<table>
	<tr>
	<td >
			<div style=" padding-left:2%;">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		<td >
			<div style="padding-left:10px;padding-left:0px\9;" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>
		<table id='Trainingloggrid'>
			<tr>
				<td></td>
			</tr>
		</table>
	<div id='pager'>
	</div>
</div>



</form>
