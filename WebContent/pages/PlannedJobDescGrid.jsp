<script>
	jQuery(document).ready(function(){
		
			initialiseForm('frmPlannedJobGrid');
			jQuery('#submitForm').val('frmPlannedJobGrid'); 
			var url = jQuery("#hiddenUrl").val();
			var location=jQuery("#hdnLocation").val();
		//alert(location +"  location");
			if(location=="LCN0000001"||location=="LCN0000003"){
				alert("This Menu is Disabled For Your Location Users..!");
				jQuery("#btnNew").hide();
			}
			else{
				jQuery("#btnNew").show();
			viewGrid(url,"?q=2");
			
			}
			jQuery ("#btnNew").click(function()
			{
			  navigateToNextForm("PlannedJobObservationEntry_input.plnJobD");
			});
			
	});
	function viewGrid(url,filterString) {
		
		processGridnew("PlannedJobObservation_view.plnJobD",filterString,"plnJobGrid","plnJobPager","","doubleclick","","loadcomplete");
		//alert(filterString);
		return true;
	}
	function doubleclick(id){

		var Keyid=jQuery("#plnJobGrid").jqGrid('getCell', id,"KEYID");
		navigateToNextForm("PlannedJobObservationEntry_input.plnJobD?q=2&Keyid="+Keyid);
	
	}
	
</script>
<form name="frmPlannedJobGrid" id="frmPlannedJobGrid">
	<div id="WrapperRpt">
		<table>
		
		   <tr>
		       <td>
		         <div>
		               <input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
		         </div>
		       </td>
		   </tr>
			<tr>
				<td>
					<table id='plnJobGrid'>
						<tr>
							<td></td>
						</tr>
					</table>
					<div id='plnJobPager'></div>
				</td>
			</tr>
		</table>				
	</div>
<input type="hidden" id="hdnLocation" name="hdnLocation" value="${requestScope.location}">
	</form>

