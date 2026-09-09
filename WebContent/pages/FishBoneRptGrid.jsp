<script>
jQuery(document).ready(function() {
	
	var url = jQuery("#hiddenUrl").val();
	var keyid = getFieldValue("hdnkeyid","frmFishBonerptGrid");
	var title = getFieldValue("hdntitle","frmFishBonerptGrid");
	processGridnew("FishBonerptGrid_input.fishbone","keyid="+keyid+"&title="+title,"FishBoneRptGrid", "FishBoneRptPager", "", "docDoubleClick", "");


	
});

</script>
<form name="frmFishBonerptGrid" id="frmFishBonerptGrid"  method="post">
	<div id="WrapperRpt">	
   <div style="margin-top: 0px">
		<table id="FishBoneRptGrid">
			<tr>
				<td>
				</td>
			</tr>
		</table>
		<div id='FishBoneRptPager'></div></div>
	</div>
	<input type="hidden" id="hdnformtype" value="${requestScope.formtype}"/>
	<input type="hidden" id="hdnkeyid" value="${requestScope.keyid}"/>
	<input type="hidden" id="hdntitle" value="${requestScope.title}"/>
</form>