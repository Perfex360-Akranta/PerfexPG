

<script type="text/javascript">
	jQuery(document).ready(
			function() {
				initialiseForm('frmActplnReport');
				var url = jQuery("#hiddenUrl").val();
				
				viewGrid(url,"q=2");
				jQuery("#btnNewDet").click(	function() {
							navigateToNextForm("Department_input.SerLevAgr?grid=false&clearfrom=true","Action Plan");
						});
			});
	function docDoubleClick(id) {
		navigateToNextForm("Department_input.SerLevAgr?grid=true&clearfrom=false","Action Plan");
	}
	function viewGrid(url,filterString)
	{
		processGridnew(url, filterString,"ActionPlanReport", "pager1", "", "docDoubleClick", "");
	}
</script>


	<form name="frmActplnReport" id="frmActplnReport" action=" " method="post">
		<div style=" margin-top:20px; padding-left: 40px;">
			<input id="btnNewDet" name="btnNewDet" class="easyui-button" style="width: 75px"
				type="button" value="New Detail"/></div>
<div id='wrapperRpt' style=" margin-top:0%;">
		
				
			<table id="ActionPlanReport">
				<tr>
					<td>
					</td>
				</tr>
			</table>
			<div id='pager1'></div>
		</div>
	</form>




