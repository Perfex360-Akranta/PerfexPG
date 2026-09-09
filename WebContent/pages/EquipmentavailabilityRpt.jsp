	
<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	viewGrid("Equipmentavail_input.eqpavlrpt","?q=");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		
		var tableCaption = "Equipment Report";
		processGridnew(url,filterString,"jhGrid","pager",tableCaption," ");
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}


</script>
<div id="wrapperRpt" style="margin-top:1%;">
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<div class="clear"></div>
<div style="border:outset #c1c1c1; border:outset #FFFFFF\9;width : 360px; height : 33px;">
	<table cellspacing="10px;" style=" height : 24px;">
		<tr style="border: inset 1px #000">
			<td style="background-color: #AFD6FE ;font-weight:bold;font-size: 14px" > &nbsp; A&nbsp;&nbsp; </td><td style="font-size: 12px"> - Availability</td>
			<td style="background-color: #AFD6FE ;font-weight:bold;font-size: 14px"> &nbsp;M&nbsp;&nbsp; </td><td style="font-size: 12px"> -  Maintainability</td>
			<td style="background-color: #AFD6FE ;font-weight:bold;font-size: 14px"> &nbsp;R&nbsp;&nbsp; </td><td style="font-size: 12px"> -  Reliability</td>
		</tr>
	</table>
</div>
<table id="jhGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>
	