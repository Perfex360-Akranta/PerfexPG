<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				setLoadFormCallBackFrmId("frmKaizenSuggestSummary");
				invokeAfterLoadFormCallBack();
				var url = jQuery('#hiddenUrl').val();
				
				//viewGrid(url,"q=2");
				
			});

	function frmKaizenSuggestSummary_afterLoadCallBack(){
		toggleCommonFilter();
		}	
	function viewGrid(url,dataString)
	{
		dataString = dataString +"&drillDown=N";
		processGridnew(url,dataString,"kaizenSuggSmryViewGrid","pager","","");
		
		return true;
	}

</script>
<form id="frmKaizenSuggestSummary">

<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
   			
	<table id='kaizenSuggSmryViewGrid'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>			
	
