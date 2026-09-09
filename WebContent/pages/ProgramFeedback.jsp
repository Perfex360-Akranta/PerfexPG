<script>
jQuery(document).ready(function() {

	initialiseForm('frmprogramfeedback');
	formatDateBox('dteprgrmdate', 'dd-MMM-yyyy');
	processGridnew("prgrmFeedbk_input.prgrmfdbk", "q=2", "question", "","PCM", "", "", "");
	jQuery('.tabs-panels').css('width','87\9');
	jQuery(".tabs").css('width','880');
});

function selectcheckbox(id){
	jQuery("#prsntnchkexcelent").attr('checked',false);
	jQuery("#prsntnchkgood").attr('checked',false);
	jQuery("#prsntnchkavg").attr('checked',false);
	jQuery("#prsntnchkfair").attr('checked',false);
	jQuery("#ttchkexcelent").attr('checked',false);
	jQuery("#ttchkgood").attr('checked',false);
	jQuery("#ttchkavg").attr('checked',false);
	jQuery("#ttchkfair").attr('checked',false);
	jQuery('#'+id).attr('checked',true);
}
jQuery("#prsntnchkexcelent").click(function(){
	selectcheckbox("prsntnchkexcelent");
});
jQuery("#prsntnchkgood").click(function(){
	selectcheckbox("prsntnchkgood");
});
jQuery("#prsntnchkavg").click(function(){
	selectcheckbox("prsntnchkavg");
});
jQuery("#prsntnchkfair").click(function(){
	selectcheckbox("prsntnchkfair");
});
jQuery("#ttchkexcelent").click(function(){
	selectcheckbox("ttchkexcelent");
});
jQuery("#ttchkgood").click(function(){
	selectcheckbox("ttchkgood");
});
jQuery("#ttchkavg").click(function(){
	selectcheckbox("ttchkavg");
});
jQuery("#ttchkfair").click(function(){
	selectcheckbox("ttchkfair");
});
</script>
<style>
table.cellbord{ border-collapse:collapse;!important }
table.cellbord td { 
	border-bottom:1px solid #000; !important; 
	border-left:1px solid #000; !important;
    padding:13px;
	}
</style>
<form id="frmprogramfeedback" name="frmprogramfeedback"> 
<div id="wrapper">

<div id="tabplanConfig" class="easyui-tabs" style="height: 400px;border-bottom:1px solid #8DB2E3; width: 882px;width: 880px\9; margin-left: 6%; ">
				
				<div title="Feedback" style="padding: 10px;">
				 	<div class="sub-header" style="width: 79%; width: 81%\9;padding-right: 2%;">
						<label><b>Program Feedback Form(To be filled by the participants)</b>
						</label>
					</div>
					<table width="700px" align="left"  >
						<tr>
							<td   valign="top" >
								<div>
									<label>Program Name</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input id="cmbprgrmname" class="easyui-combobox" name="cmbprgrmname" style="width: 304px;" />
								</div>
								<div>
									<label>Faculty Name</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input id="cmbfcltyname" class="easyui-combobox" name="cmbfcltyname" style="width: 304px;" />
								</div>
								<div>
									<label>Date</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input id="dteprgrmdate" class="easyui-combobox" name="dteprgrmdate" style="width: 304px;" />
								</div>
								</td>
								<td  valign="top"  style="" >
								<div style="margin-left:20px;"> 
								<div>
									<label>Venue</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input id="cmbvenue" class="easyui-combobox" name="cmbvenue" style="width: 304px;" ></input>
								</div>
								<div>
									<label>Participant Name</label>
								</div>
								<div class="easyui-paddingbfpx">
									<input id="cmbpartcpntname" class="easyui-combobox" name="cmbpartcpntname" style="width: 304px;" ></input>
								</div>
								<div>
									<label>How do you rate the program?</label>
								
									<input type="checkbox" id="chkexcelent"></input>
									<input type="checkbox" id="chkgood" ></input>
									<input type="checkbox" id="chkaverage" ></input>
									<input type="checkbox" id="chkfair" ></input>
								
								</div>
								</div>
								</td>
						</tr>
						<tr>
						<td>
								<div class="sub-header" style="width: 98%; padding-right: 2; ">
									<label><b>Presentation</b>
									</label>
								</div>
								<div>
								<table class="cellbord">
									   <tr >
									    <td  class="cellbord" style="border: solid 1px;"><label>Excellent</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Good</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Average</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Fair</label></td>
									  </tr>
									  <tr >
									    <td class="cellbord" align="center" style="border: solid 1px;">4</td>
									    <td class="cellbord" align="center" style="border: solid 1px;">3</td>
									    <td class="cellbord" align="center" style="border: solid 1px;">2</td>
									    <td class="cellbord" align="center" style="border: solid 1px;">1</td>
									  </tr>
									  <tr >
									   <td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="prsntnchkexcelent" align="middle"/ ></td>
									    <td class="cellbord"style="border: solid 1px;" align="center"><input type="checkbox" id="prsntnchkgood" align="middle"/ ></td>
									    <td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="prsntnchkavg" align="middle"/ ></td>
									    <td class="cellbord"style="border: solid 1px;" align="center"><input type="checkbox" id="prsntnchkfair" align="middle"/ ></td>
									   </tr>
									   
									</table>
								</div>
								
								</td>
								<td>
								<div style="margin-left:20px;">
								<div class="sub-header" style="width: 98%; margin-top:-4px;margin-top:0px\9;padding-right: 2; ">
									<label><b>Training Topic</b>
									</label>
								</div>
								<div style="margin-top:8px;">
								<table class="cellbord">
									  <tr >
									    <td  class="cellbord" style="border: solid 1px;"><label>Excellent</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Good</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Average</label></td>
									    <td  class="cellbord" style="border: solid 1px;"><label>Fair</label></td>
									  </tr>
									  <tr >
									    <td class="cellbord"  align="center" style="border: solid 1px;">4</td>
									    <td class="cellbord"  align="center" style="border: solid 1px;">3</td>
									    <td class="cellbord"  align="center" style="border: solid 1px;">2</td>
									    <td class="cellbord" align="center" style="border: solid 1px;">1</td>
									  </tr>
									  <tr>
								 		<td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="ttchkexcelent" align="middle"/ ></td>
									    <td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="ttchkgood" align="middle"/ ></td>
									    <td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="ttchkavg" align="middle"/ ></td>
									    <td class="cellbord" style="border: solid 1px;"align="center"><input type="checkbox" id="ttchkfair" align="middle"/ ></td>
									  
									   </tr>
									  
									</table>
								</div>
								</div>
								</td>
							</tr>	
						</table>
						
				
			
				</div>
				<div title="Question" style="padding: 10px;">
				<div style="float: left; margin-top: -1%; padding-left: 2%;">
						<table id="question">
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				
</div>
</div>
</form>