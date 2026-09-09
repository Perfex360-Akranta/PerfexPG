<html>
<head>

<script>
	jQuery(document).ready(
			function() {
				initialiseForm('frmNewIndicator');
				var url = jQuery("#hiddenUrl").val();
				viewGrid(url,"q=2");
				jQuery("#btnNew").click(	function() {
							navigateToNextForm("newIndicatorPMModify_input.keyPerInd?&mode=create","New IndicatorKPI");
						});
				
			});
	
	function viewGrid(url,filterString)
	{
		processGridnew(url, filterString,"NewIndicatorReport", "pager", "", "docDoubleClick", "");
	}
	function docDoubleClick(id) {
		navigateToNextForm("newIndicatorPMModify_input.keyPerInd?&closeOnSave=true&mode=MODIFY","New IndicatorKPI");
	}
</script>

</head>
<body>
	<form name="frmNewIndicator" id="frmNewIndicator" action=" " method="post">

<div id='wrapperRpt'>
		<table>
	<tr>
	<td >
			<div style="padding-left:0%;padding-left:0%\9; margin-top: -32px">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		<td >
			<div style="width : 332px;width: 325px\9;padding-left:10px;margin-top: -27px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>
		
		
			
			<table id="NewIndicatorReport" >
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
	</form>
</html>