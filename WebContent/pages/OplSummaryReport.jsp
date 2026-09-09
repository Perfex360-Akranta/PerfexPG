<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				
				var url = jQuery('#hiddenUrl').val();
				//viewGrid(url,"q=2");
				setLoadFormCallBackFrmId("frmOplSmryReport");
				invokeAfterLoadFormCallBack();
				
				
			});

			
	function viewGrid(url,dataString)
	{
		processGridnew(url,dataString,"OPLSmryViewGrid","pager","","");
		
		return true;
	}
	function frmOplSmryReport_afterLoadCallBack(){ 
		toggleCommonFilter();
		} 

</script>


<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
   			
	<table id='OPLSmryViewGrid'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>			
	
