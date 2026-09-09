<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript">
	jQuery(document).ready(function() 
			{
		jQuery('#submitForm').val('frmImrControl');
		formatDateBox('dteImrcFromDate', 'dd-MMM-yyyy');
		formatDateBox('dteImrcToDate', 'dd-MMM-yyyy');
		fillComboBox("frmImrControl", "cmbImrcShiftid", "shift.commonFilter");
		
		numericTextBox('txtImrcSamplereading');
				
			processGridnew("I_MRControlForm_input.IMR","?q=2&setwidth=Y","ShiftDetailsGrid","shiftpager","","docDoubleClick","","GridLoadComplete");
			var factId = jQuery("#frmImrControl input[id='factory']").val();
		    var sectionId = jQuery("#frmImrControl input[id='section']").val();
		    var cellId = jQuery("#frmImrControl input[id='cell']").val();
		    var machId = jQuery("#frmImrControl input[id='machine']").val();
		    var flid = jQuery("#frmImrControl input[id='flid']").val();
		    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
		    
			loadFunctionalLocation("imrfunLocation","ConvMatx_functionalLoc.ConvMatx","ctfxfunLocationValues","frmConversionMatrix",dataStr);
			
			processAjaxCalls("I_MRControlForm_chart1.IMR","","IMrgraph_successCallBack");

			 fileManagerPopUp("","ABN","frmImrControl","btnfilemgr","IMRFilemgr"); 			
		});
function GridLoadComplete(){
	var rowid = jQuery("#ShiftDetailsGrid").jqGrid('getDataIDs');
	var rowData;
	var SampleReading=0;
	var backsample1;
	var backsample2;
	var Mrvalue=0;
	for(var i=0;i<rowid.length;i++){
		rowData = jQuery("#ShiftDetailsGrid").jqGrid('getRowData',i+1);
		SampleReading = rowData.IMRC_SAMPLEREADING;
		if(i==0){
			backsample1=0;
		}
		else{
			backsample1 =jQuery("#ShiftDetailsGrid").jqGrid('getCell',rowid[i-1],"IMRC_SAMPLEREADING");
		}
		backsample2=jQuery("#ShiftDetailsGrid").jqGrid('getCell',rowid[i],"IMRC_SAMPLEREADING"); 
		
		if(backsample1==""){
			Mrvalue=0;
		}
		else{
			Mrvalue=backsample2-backsample1;
		}
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'MR',parseFloat(Math.abs(Mrvalue)).toFixed(2));
		
		
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'LCL_MR','0');
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'MR_BAR','0.72');
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'UCL_MR','0.235224');
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'LCL_I','0.622091');
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'I_BAR','0.813611');
		jQuery("#ShiftDetailsGrid").jqGrid('setCell',i+1,'UCL_I','1.005131');
	}
}
	function btnfilemgr_click()
	{
		//alert("11");
	   // var documentNo =jQuery("#hdnabnkeyID").val();
	   
		if(1 != null && 1 != '')
			{
			fileManagerPopUp(1,"ABN","","","");
		}
		
	}
	function IMrgraph_successCallBack(result){
		jQuery('#divgraphIMRControl2').show();
		var chartData = result.aftchartData.chartData;	
		chartData.height ="250";
		chartData.width ="500";
		drawChart(chartData,'divgraphIMRControl2','Y','N');
		processAjaxCalls("I_MRControl_chart.IMR","","IMRgraph1_successCallBack");
	}
	function IMRgraph1_successCallBack(result){
		jQuery('#divgraphIMRControl1').show();
		jQuery('.dashToolBar').attr("width","500");
		var chartData = result.chartData.chartData;	
		chartData.height ="250";
		chartData.width ="500";
		drawChart(chartData,'divgraphIMRControl1','Y','N');
	}
	function IMrgraphY_successCallBack(result){
		jQuery('#divgraphIMRControl2').show();
		var chartData = result.aftchartData.chartData;	
		chartData.height ="250";
		chartData.width ="500";
		drawChart(chartData,'divgraphIMRControl2','Y','N');
		processAjaxCalls("I_MRControl_chart.IMR?Spec=Y","","IMRgraph1Y_successCallBack");
	}
	function IMRgraph1Y_successCallBack(result){
		jQuery('#divgraphIMRControl1').show();
		jQuery('.dashToolBar').attr("width","500");
		var chartData = result.chartData.chartData;	
		chartData.height ="250";
		chartData.width ="500";
		drawChart(chartData,'divgraphIMRControl1','Y','N');
	}
		
		function formatterTxtJHLevel(id, options, rowObject)
		{	
			var rowId = options.rowId;
					return '<input id="txtMaxPoint_"'+rowId+' name="txtMaxPoint_"'+rowId+'   type="text"  value="'+id+'" style="width:375px;text-align:left"/>';
		}
					
		
		function docDoubleClick(id)
		{	
				var rowData = jQuery("#ShiftDetailsGrid").jqGrid('getRowData',id);
				var KeyId = rowData.IMRC_KEYID;
	            var Date = rowData.IMRC_DATE;
	         	var ShiftDetails = rowData.IMRC_SHIFTID;
				var SampleRead = rowData.IMRC_SAMPLEREADING;
				setFieldValue('txtImrcKeyid',KeyId);
				setFieldValue('dteImrcDate',Date);
				setFieldValue('cmbImrcShiftid',ShiftDetails);
				setFieldValue('txtImrcSamplereading',SampleRead);
				
				    
		}
		 function frmImrControl_successsCallback(result){
			  jQuery("#ShiftDetailsGrid").trigger("reloadGrid");
			 }
			function frmImrControl_deleteSuccessCallback(result)
			{ 
				jQuery("#ShiftDetailsGrid").trigger("reloadGrid");
				alert(result.successData.msg);
				  
			}
			function frmImrControl_FuntLocHierarchy_SuccessCallBack(result){
				 
				
				var flid = result.flid;
				//alert(flid);
				jQuery("#cmbImrcFlid").val(flid);
				
			}
			jQuery( "#formulaeButton" ).click(function() {
				jQuery("#lgnd-panel").css('width','16%');
				jQuery("#lgnd-panel").css('padding','1%');
				jQuery("#lgnd-panel").css('top','17%');
				//jQuery("#lgnd-panel").css('right','21%');
				jQuery("#lgnd-panel").slideToggle(300);
			});
			jQuery(document).keydown(function(e) {
			    if (e.keyCode == 27) {
			    	jQuery("#lgnd-panel").hide(0);
			    }    
			    jQuery('#lgnd-panel').focusout(function() { 
			 	});
			});
			function chkOnClick(){
				var isChecked = jQuery("#chkGraph").is(':checked');
				if(isChecked==true){
					processAjaxCalls("I_MRControlForm_chart1.IMR?Spec=Y","","IMrgraphY_successCallBack");
					
					
				}else
				{
					processAjaxCalls("I_MRControlForm_chart1.IMR","","IMrgraph_successCallBack");
					
				}
			}
</script>


<style type="text/css">


</style>

</head>
<body>


<form action="" method="post" id="frmImrControl">


	<div id='wrapper' style="width:100%;position:relative">
	<table width="100%">
		<tr>
			<td width="70%">
			<div>
				<div>
					<input type="hidden" id="factory" name="factory"  value="" ></input>
					<input type="hidden" id="section" name="section"  value=""></input>
					<input type="hidden" id="cell"    name="cell"     value=""></input>
					<input type="hidden" id="machine" name="machine"  value=""></input>	
					<input type="hidden" id="flid" name="cmbImrcFlid"  value=""/>
									
					<div id="imrfunLocation" ></div>	
				</div>	
			</div>
			</td>
			<td width="40%">
				<div style="position: relative"> 
					<span style="position: absolute;">
						<input type="button" class="easyui-button" id="formulaeButton" value="Formulae"/>
					</span>
				</div>
			</td>
		</tr>
		<tr>
			<td width="75%">
				<table width="70%">
					<tr> 
						<td>
							<div class="easyui-paddingbfpx">
							<label>From Date</label>
							</div>
							<div class="easyui-paddingbfpx"><input class="easyui-datebox"
								id="dteImrcFromDate" name="dteImrcFromDate" maxlength="20"	style="width: 85px; height: 22px;"	value="${requestScope.newGenTlImrcontrolchart.imrcDate}"
								 /></div>    
						</td>
						<td>
							<div class="easyui-paddingbfpx">
							<label>To Date</label>
							</div>
							<div class="easyui-paddingbfpx"><input class="easyui-datebox"
								id="dteImrcToDate" name="dteImrcToDate" maxlength="20"	style="width: 85px; height: 22px;"	value="${requestScope.newGenTlImrcontrolchart.imrcDate}"
								 /></div>    
						</td>
						<td>
							<div class="easyui-paddingbfpx">
							<label>Shift Details</label>
							</div>
							<div class="easyui-paddingbfpx">
							<input class="easyui-text"				id="cmbImrcShiftid" name="cmbImrcShiftid"	onchange="EmpmEmployeetype_onchange();" style="width: 100px;height: 22px;"
								value="${requestScope.newGenTlImrcontrolchart.imrcShiftid} " /></div>
						</td >
						<td>
							<div class="easyui-paddingbfpx">
							<label>Sample Reading
							</label>
							</div>
							<div class="easyui-paddingbfpx" ><input class="easyui-text"
								id="txtImrcSamplereading" name="txtImrcSamplereading" style="width: 150px;height: 22px;" maxlength="20"
								value="${requestScope.newGenTlImrcontrolchart.imrcSamplereading} " />
								 </div>
						</td>
						<td style="_position: relative">
							<span class="easyui-paddingbfpx"  id="IMRFilemgr" style="_position:absolute;" ></span> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>
		
		

		<input type="hidden" id="mode" name="mode" value=""/>
		 <input type="hidden"id="txtImrcKeyid" name="txtImrcKeyid" value="${requestScope.newGenTlImrcontrolchart.imrcKeyid}" />
		 <br><br>
	<table>
		<tr>
			<td>
				<div  style="margin-top:-24%;margin-top:-23%\9;">
					<table id='ShiftDetailsGrid'>
					<tr>
							<td></td>
						</tr>
					</table>
					<div id='shiftpager'></div>
				</div>
			</td>
			<td>
				<div style="margin-left:3%;">
					
					 <input type="checkbox" id="chkGraph" name="chkGraph" value="Y" onclick="chkOnClick();" />
					 <span style="padding-left:1%;" ><label>Specification Limit</label>
					 </span>
				</div>
			    <div id="divgraphIMRControl1" style="margin-left:3%;height:300px;">
			    </div>
			    <div id="divgraphIMRControl2" style="margin-left:3%;margin-top:-18%\9;">
			    </div>
			</td>
		</tr>
		<tr>
    		
		</tr>
	</table>
</div>
<div>
<div id="lgnd-panel" style="position:absolute;right:9%;right:12%\9;">
		<ul>
			<li>MR = Sample Reading (n) - Sample Reading (n-1)</li>
			<li>LCL MR = 0 </li>
			<li>MR Bar = Average (MR)</li>
			<li>UCL MR = 3.267*MR Bar</li>
			<li>LCL I = I Bar - 2.66*MR Bar</li>
			<li>I Bar =  Average (Sample Reading)</li>
			<li>UCL I = I Bar+2.66*MR Bar</li>
		</ul>
  	 </div>
</div>
</form>


</body>
</html>