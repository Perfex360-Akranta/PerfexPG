<script>
	jQuery(document).ready(function ()
		{
			var url = jQuery('#hiddenUrl').val();
			processAjaxCalls("AbnUnsafeActionUnsafePlanDrill_chart.abnGenRpt?Type=Act&ChartType="+jQuery("#hdnChartType").val(),"","AbnUnsafeAct_successCallBack");				
		});
function AbnUnsafeAct_successCallBack(result){
	jQuery('#UnsafeActionChart').show();
	var chartData = result.chartData.chartData;
	chartData.height ='370';
	chartData.width ='570';
	drawChart(chartData,"UnsafeActionChart",'Y','N','N','Y');
	jQuery("#dataTableContainer_UnsafeActionChart").css('width','500px');
	processAjaxCalls("AbnUnsafeActionUnsafePlanDrill_chart.abnGenRpt?Type=Condition&ChartType="+jQuery("#hdnChartType").val(),"","AbnUnsafeCondition_successCallBack");
}
function AbnUnsafeCondition_successCallBack(result){
	jQuery('#UnsafePlanChart').show();
	var chartData = result.chartData.chartData;
	chartData.height ='370';
	chartData.width ='570';
	drawChart(chartData,"UnsafePlanChart",'Y','N','N','Y');
	jQuery("#dataTableContainer_UnsafePlanChart").css('width','500px');
}
</script>
<form>
	<div>
		<table width="100%">
			<tr>	
				<td>
					<div style="height:100px;">
						<span id="UnsafeActionChart"></span>
					</div>
				</td>
				<td>
					<div style="height:100px;">
						<span id="UnsafePlanChart"></span>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="hdnChartType" name="hdnChartType" value="${requestScope.ChartType}"/>
</form>