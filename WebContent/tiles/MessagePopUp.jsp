<script>
	jQuery(document).ready(	function() {
				//initialiseForm('frmmessagepopup');
				
				var url = jQuery("#hiddenUrl").val();
				var roleId = jQuery('#hdnUserRole').val();
				viewGrid(url,"q=2&roleId="+roleId);
			
				
	function viewGrid(url,filterString)
	{
		processGridnew(url, filterString,"messagepopup", "messagepopuppager", "", "", "");
	}
});
	
</script>

<form name="frmmessagepopup" id="frmmessagepopup" action=" " method="post">
<table id="messagepopup" >
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='messagepopuppager'></div>
</form>
