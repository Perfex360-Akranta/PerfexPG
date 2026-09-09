<script><!--
jQuery(document).ready(function() {
	
	//var url = jQuery("#hiddenUrl").val();
	//alert('url'+url);
	var keyid = getFieldValue("hdnkeyid","frmWwblarptGrid");
	var title = getFieldValue("hdntitle","frmWwblarptGrid");
	processGridnew("WwblarptGrid_input.Wwbla","keyid="+keyid+"&title="+title,"WwblarptGrid", "WwblaRptPager", "", "doubleClickGrid", "");


	
});

/*function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
{ 		
/*	var rowData = jQuery("#WwblaRptGrid").jqGrid('getRowData',rowid );
	var keyid = rowData.Keyid;
	alert(keyid);
	navigateToNextForm("WwblarptGrid_input.wwbla?keyid="+keyid);*/


</script>
<form name="frmWwblarptGrid" id="frmWwblarptGrid"  method="post">
	<div id="WrapperRpt">	
   <div style="margin-top: 0px">
		<table id="WwblarptGrid">
			<tr>
				<td>
				</td>
			</tr>
		</table>
		<div id='WwblaRptPager'></div></div>
	</div>
	<input type="hidden" id="hdnformtype" value="${requestScope.formtype}"/>
	<input type="hidden" id="hdnkeyid" value="${requestScope.keyid}"/>
	<input type="hidden" id="hdntitle" value="${requestScope.title}"/>
</form>