<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				
				var url = jQuery('#hiddenUrl').val();
				viewGrid(url,"q=2");
				
			});

			
	function viewGrid(url,dataString)
	{
		dataString = dataString +"&drillDown=N";
		processGridnew(url,dataString,"SafetySuggSmryViewGrid","pager","","");
		
		return true;
	}

</script>


<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
   			
	<table id='SafetySuggSmryViewGrid'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>			
	
