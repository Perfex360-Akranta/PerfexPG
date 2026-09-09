<script>
	jQuery(document).ready(function() {
		initialiseForm('frmtrngCal');
		formatDateBox('dteMonth', 'MMM-yyyy');
		formatDateBox('dteSess1', 'dd-MMM-yyyy');
		formatDateBox('dteSess2', 'dd-MMM-yyyy');
		formatDateBox('dteSess3', 'dd-MMM-yyyy');
		formatDateBox('dteSess4', 'dd-MMM-yyyy');

		fillWithCurrentDate('dteSess1');
		fillWithCurrentDate('dteSess2');
		fillWithCurrentDate('dteSess1');
		fillWithCurrentDate('dteSess1');
		var mode = jQuery('#mode').val();
		if (mode=="modify"){
			//jQuery("#dteMonth").datebox('setValue',"Aug-2013");
			setFieldValue("dteMonth","Aug-2013","frmtrngCal");
			setFieldValue("txtTargerGrp","Electrical","frmtrngCal");
			setFieldValue("dteSess1","04-Aug-2013","frmtrngCal");
			setFieldValue("dteSess2","05-Aug-2013","frmtrngCal");
			setFieldValue("dteSess3","06-Aug-2013","frmtrngCal");
			setFieldValue("dteSess4","07-Aug-2013","frmtrngCal");
			setFieldValue("dteSess4","07-Aug-2013","frmtrngCal");
			setFieldValue("dteSess4","07-Aug-2013","frmtrngCal");
			setFieldValue("dteSess4","07-Aug-2013","frmtrngCal");
			/*jQuery('#txtTargerGrp').val("Electrical");
			//jQuery("#dteSess1").datebox('setValue',"04-Aug-2013");
			jQuery("#dteSess2").datebox('setValue',"05-Aug-2013");
			jQuery("#dteSess3").datebox('setValue',"06-Aug-2013");
			jQuery("#dteSess4").datebox('setValue',"07-Aug-2013");	*/			
			
			jQuery('#txtFaculty').val("S.Sriramulu");
			jQuery('#txtTopic').val("Bearing Assembly");
			jQuery('#txtDuration').val("2");
		}		
	});
	
	if(screen.width <= 1024){
		
		jQuery('.main-cntborder').css('margin-left','100');
		
	  
    	}	
   	else{
   		
   	}
	 
</script>
<form id="frmtrngCal" name="frmtrngCal">
	<div id="wrapper" style="">
	<div class="main-cntborder" style="width:700px;margin-left:18%;height:360px\9">
		<table style="margin-left:40px;margin-top:40px;">
			<tr>
				<td valign="top">
					<div class="easyui-paddingbfpx">
						<label>Month</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input id="dteMonth" name="dteMonth" class="easyui-datebox"
							style="width: 105px;" value="" />
					</div></td>
			</tr>
			<tr>
				<td valign="top">
					<div class="easyui-paddingbfpx">
						<label>Target Group</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input id="txtTargerGrp" name="txtTargerGrp" type="text" value="" style="width: 228px;"
							class="easyui-text" />
					</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="width:250px;">
					<span style="padding-left: 60%;padding-left: 68%\9"><label>Duration</label></span>
									
								</div>
								<div class="easyui-paddingbfpx">
									<input id="rboIsMatReady" name="rboIsMatReady" type="radio" value="" style="" />
									<span style="padding-left: 0%"><label>Is Material Ready(Y/N)</label></span>
									<span style="padding-left: 3%"><input id="txtDuration" name="txtDuration" type="text" value="" style="width: 100px;" class="easyui-text" /></span>
								</div>
			</td>
			</tr>
			<tr>
			<td valign="top">
				<div class="easyui-paddingbfpx">
						<label>Faculty</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input id="txtFaculty" name="txtFaculty" type="text" value="" style="width: 228px;"
							class="easyui-text" />
					</div>
				</td>
				<td valign="top" rowspan="2">
					
					<div class="easyui-paddingbfpx">
						<label>Topic</label>
					</div>
					<div class="easyui-paddingbfpx">
						<textarea id="txtTopic" name="txtTopic" class="txtarea" rows="1" tabindex="8"
							style="width: 305px; resize: none; height: 68px;" cols=""></textarea>
					</div>
					
					</td>
				</tr>
				<tr>
				<td  style="">
					<div class="easyui-paddingbfpx" >
					<label>Session  1</label>
					<span style="padding-left: 70px;"><label>Session  2</label></span>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="dteSess1" name="dteMonth" class="easyui-datebox" style="width: 105px;" value=""/>
					<span style="padding-left: 20px\0/;padding-left:20px"><input id="dteSess2" name="dteMonth" class="easyui-datebox" style="width: 105px;" value=""/></span>
				</div>
					</td>
				
			</tr>
			<tr>
			<td style=" width : 314px;">
				<div class="easyui-paddingbfpx" >
					<label>Session  3</label>
					<span  style="padding-left: 20%;padding-left: 21%\9"><label>Session  4</label></span>
				</div>
				<div class="easyui-paddingbfpx" >
					<input id="dteSess3" name="dteMonth" class="easyui-datebox" style="width: 105px;" value=""/>
					<span style="padding-left: 5%">
					<input id="dteSess4" name="dteMonth" class="easyui-datebox" style="width: 105px;" value=""/>
					</span>
				</div>
			</td>
			
			<td >
			<div align="center" style="padding-top:20px;">
				 <input type="button" class="easyui-button" value="Insert" id="btnbut" style="height: 33%" />
				 <span style="padding-left: 10px;"><input type="button" class="easyui-button" value="Update" id="btnbut" style="height: 33%" /></span>
				 <span style="padding-left: 10px;"><input type="button" class="easyui-button" value="Delete" id="btnbut" style="height: 33%" /></span>
			</div>
			</td>
			</tr>
		</table>
		</div>
		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	</div>
</form>
