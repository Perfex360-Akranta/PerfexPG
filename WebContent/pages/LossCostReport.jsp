<script>
	jQuery(document).ready(function ()
	{
		var url = jQuery('#hiddenUrl').val();
		processAjaxCalls("LossCostReport_chart.lossCstRpt?ChartType=OEE&cellid="+jQuery("#hdnCellid").val(),"","LossCost1_successCallBack");				
	});

	function LossCost1_successCallBack(result){
		chartDraw(result,'divgraphLossCost1','ROA','2');
	}
	function LossCost2_successCallBack(result){
		chartDraw(result,'divgraphLossCost2','ROQ','3');
	}
	function LossCost3_successCallBack(result){
		chartDraw(result,'divgraphLossCost3','ROP','4');
	}
	function LossCost4_successCallBack(result){
		chartDraw(result,'divgraphLossCost4','EF','5');
	}
	function LossCost5_successCallBack(result){
		chartDraw(result,'divgraphLossCost5','DEFECTNREWORK','6');
	}
	function LossCost6_successCallBack(result){
		chartDraw(result,'divgraphLossCost6','MS','7');
	}
	function LossCost7_successCallBack(result){
		chartDraw(result,'divgraphLossCost7','SETUPANDADJ','8');
	}
	function LossCost8_successCallBack(result){
		chartDraw(result,'divgraphLossCost8','SPEEDLOSS','9');
	}
	function LossCost9_successCallBack(result){
		chartDraw(result,'divgraphLossCost9','TOOLCHANGELOSS','10');
	}
	function LossCost10_successCallBack(result){
		chartDraw(result,'divgraphLossCost10','STARTUPLOSS','11');
	}
	function LossCost11_successCallBack(result){
		chartDraw(result,'divgraphLossCost11','MOTIONLOSS','12');
	}
	function LossCost12_successCallBack(result){
		chartDraw(result,'divgraphLossCost12','LINEORGLOSS','13');
	}
	function LossCost13_successCallBack(result){
		chartDraw(result,'divgraphLossCost13','LOGISTICSLOSS','14');
	}
	function LossCost14_successCallBack(result){
		chartDraw(result,'divgraphLossCost14','MEASUREMENTLOSS','15');
	}
	function LossCost15_successCallBack(result){
		chartDraw(result,'divgraphLossCost15','SHUTDOWNLOSS','16');
	}
	function LossCost16_successCallBack(result){
		chartDraw(result,'divgraphLossCost16','MANAGEMENTLOSS','17');
	}
	function LossCost17_successCallBack(result){
		chartDraw(result,'divgraphLossCost17','COMMONUTILITY','18');
	}
	function LossCost18_successCallBack(result){
		chartDraw(result,'divgraphLossCost18','ENERGYLOSS','19');
	}
	function LossCost19_successCallBack(result){
		chartDraw(result,'divgraphLossCost19','COMMONUTILITY','20');
	}
	function LossCost20_successCallBack(result){
		chartDraw(result,'divgraphLossCost20','YIELDLOSS','21'); 
	}
	function LossCost21_successCallBack(result){
		chartDraw(result,'divgraphLossCost21','YIELDLOSS','');
	}
	
	function chartDraw(result,divId,ChartType,value){
		jQuery('#'+divId).show();
		var chartData = result.chartData.chartData;
		chartData.height ='250';
		chartData.width ='500';
		drawChart(chartData,divId,'Y','N');
		processAjaxCalls("LossCostReport_chart.lossCstRpt?ChartType="+ChartType+"&cellid="+jQuery("#hdnCellid").val(),"","LossCost"+value+"_successCallBack");
	}


	/*
	<hr width="1" size="500">
	
	vr {
    display: block;
    width:10px;
    background-color:#000;
    position:absolute;
    top:0;
    bottom:0;
    left:150px;
}
	<vr />
	<hr width="1" size="500" style="position:absolute;top:100px;left:30px">
	<hr size=1 width=570 align=left style="position:absolute;top:139px;left:35px">
	
	*/
</script>
<form id="frmLossCostReport">
<div style="margin-left:3%;">
	<table style="border-collapse:collapse;">
		<tr>
			<td colspan="6">
				<table style="border-collapse:collapse;">
					<tr>
						<td style="position:relative">

							<div class="main-cntborder" style="width:100px;height:50px;margin-left:550px;">
								<label>OEE (%)</label>
							</div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:550px;"></div>
						</td>
						<td>
							<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
								<div id="divgraphLossCost1"></div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>AVAILABILITY (%)</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost2"></div>
				</div>
			</td>
			<td>
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:10;"><label>QUALITY (%)</label></div>
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost3"></div>
				</div>
			</td>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>PERFORMANCE (%)</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost4"></div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>1.Equipment Failure Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost5"></div>
				</div>
			</td>
			<td>
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:10;"><label>7.Defect & Rework Loss</label></div>
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost6"></div>
				</div>
			</td>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>5.Minor Stoppage and Idling Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost7"></div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>2.Setup & Adjustment Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost8"></div>
				</div>
			</td>
			<td  colspan="3">
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:86%;"><label>6.Speed Loss</label></div>
				<div class="main-cntborder" style="width:102px;height:50px;margin-left:86%;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost9"></div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>3.Tool Change Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost10"></div>
				</div>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>4.Startup Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost11"></div>
				</div>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>10.Operating Motion Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div style="width:518px">
					<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
						<div  id="divgraphLossCost12"></div>
					</div>
				</div>
			</td>
			<td rowspan="4" colspan="4">
				<table style="border: 2px solid black;border-collapse:collapse;">
					<tr>
						<td colspan="2" style="border-right:2px solid black">
							<div class="main-cntborder" style="height:40px;margin-top:3%;">
								<label style="font-weight:bold;font-size:30px;">Losses not accounted for Availability</label>
							</div>
						</td>
						<td colspan="2">
							<div class="main-cntborder" style="height:40px;margin-top:3%;">
								<label style="font-weight:bold;font-size:30px;">Losses resulting in Cost Increase</label>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>8. Shutdown Loss</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td style="border-right:2px solid black">
							<div style="width:518px">
								<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
									<div  id="divgraphLossCost16"></div>
								</div>
							</div>
						</td>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>14.Energy Loss</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td>
							<div style="width:518px">
								<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
									<div id="divgraphLossCost19"></div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>9.Management Loss</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td style="border-right:2px solid black">
							<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
								<div id="divgraphLossCost17"></div>
							</div>
						</td>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>15.Die, Tool & Jig Loss</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td>
							<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
								<div id="divgraphLossCost20" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>No Plan</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td style="border-right:2px solid black">
							<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-bottom:3%;margin-right:3%;">
								<div id="divgraphLossCost18" ></div>
							</div>
						</td>
						<td>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>16.Yield Loss</label></div>
							<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
						</td>
						<td>
							<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-bottom:3%;margin-right:3%;">
								<div id="divgraphLossCost21" ></div>
							</div>
						</td>
					</tr>
				</table>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>11.Line Organization Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost13"></div>
				</div>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>12.Logistics Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost14"></div>
				</div>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"><label>13.Measurement & Adjustment Loss</label></div>
				<div class="main-cntborder" style="width:100px;height:50px;margin-left:10;"></div>
			</td>
			<td>
				<div class="main-cntborder"  style="width:502px;height:277;margin-top:3%;margin-right:3%;">
					<div id="divgraphLossCost15"></div>
				</div>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
	</table>
	<div>
		<input type="hidden" id="hdnCellid" name="hdnCellid"  value="${requestScope.cellid}" />
	</div>
</div>
</form>