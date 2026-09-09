<script>
jQuery(document).ready(function(){
	processGridnew("traningLeadPop_input.tatnd",'q=2',"jqGridTrainingPop","PagerPop"," ", "");
});
</script>
<div>
	<div>
		<label>
			Employee
		</label>
	</div>
	<div>
		<input type="text" id="txtEmployee" name="txtEmployee" value="${requestScope.type}" class="easyui-text" style="width:200px;height:21px;" readonly="readonly"/>
	</div>
	<div style="float:left;">
		<table id="jqGridTrainingPop">
			<tr><td></td></tr>
		</table>
		<div id="PagerPop"></div>
	</div>
</div>