
<script type="text/javascript">	
jQuery(document).ready(function(){ 
	initialiseForm('frmStdWorkChartGrid');
	jQuery('#submitForm').val('frmStdWorkChartGrid');
	processGridnew("stdwrkchrt_input.wrkchrt","?q=1","StdWoChartGrid","StdWoChartPager"," ", "doubleclick");


	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewPDF").val(btnName);
	jQuery("#btnViewPDF").click(function(){
		
		processAjaxCalls("openFile.file?fileName=Kaizen_Format.pdf", "", "", "", "", "viewPdf");					
	});

	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnView").val(btnName);
	jQuery("#btnView").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=StandrizedWork_chart.xlsx", "", "", "", "", "new");					
	});
	var factId = jQuery("#frmStdWorkChartGrid input[id='factory']").val();
    var sectionId = jQuery("#frmStdWorkChartGrid input[id='section']").val();
    var cellId = jQuery("#frmStdWorkChartGrid input[id='cell']").val();
    var machId = jQuery("#frmStdWorkChartGrid input[id='machine']").val();
    var flid = jQuery("#frmStdWorkChartGrid input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId=MCH0002058"+"&flid="+flid+"";
  //  alert("tamil");
    loadFunctionalLocation("stdfunLoc","functionalLoc.sop","frmStdWorkChartGridLocationfunLocationValues","frmStdWorkChartGrid",dataStr);
    formatDateBox('dteFromDate','dd-MMM-yyyy');
	formatDateBox('dteToDate','dd-MMM-yyyy');
	fillComboBox("frmStdWorkChartGrid","cmbStswApprovedby","employee.commonFilter" );
	});

</script>


<form id="frmStdWorkChartGrid" name="frmStdWorkChartGrid">

<div id="wrapper"  style="width:100%">
<div>
		
	<div class="clitdiv">
		
		<div id="frmStdWorkChartGridFuntKeyIds"  >							
	<input type="hidden" id="factory" name="factory"  value="" ></input>
	<input type="hidden" id="section" name="section"  value=""></input>
	<input type="hidden" id="cell"    name="cell"     value=""></input>
	<input type="hidden" id="machine" name="machine"  value=""></input>
   <input type="hidden" id="flid" name="cmbstdFlnid"  value=""></input>

</div>
	<div id="stdfunLoc" style="width:781px; width:700px\9"></div>
			<table style="width:100%">
		<tr>
			<td style="width:325px; ">
			<div>
			<label>From Date</label>
			<span style="margin-left:32%;">
				<label>To Date</label>
			</span>
			</div>
			<div>
			<input id="dteFromDate" name="dteFromDate" class="easyui-datebox"  value=" " style="width:110px;width:140px;\9" />
			<span style="margin-left:8%;">
			<input id="dteToDate" name="dteToDate" class="easyui-datebox"  value=" " style="width:110px;width:140px;\9" />
			</span>
			</div>
			</td>
			<td style="width:200px;">
				<div class="easyui-paddingbfpx"   style ="padding-right:20%"> 
				<label class ="mandatory-lbl"> Approved By </label>
				</div>
				<div  class="easyui-paddingbfpx"  style ="padding-right:10px">
				<input id="cmbStswApprovedby" name="cmbStswApprovedby" class="easyui-combobox" value="" style="width:300px;" />
				
				</div>
			</td>
			<td style="width:100px; ">
				<div class="easyui-paddingbfpx"   style ="padding-right:20%"> 
				<label class ="mandatory-lbl"> Operation </label>
				</div>
				<div  class="easyui-paddingbfpx"  style ="padding-right:10px">
				<input id="cmbStswOperation" name="cmbStswOperation" class="easyui-text" value="" style="width:150px;" />
				
				</div>
			</td>
			<td>
			<div>
			<div style="margin-left:-12px;">
			<input type="button" class="easyui-button" value ="View Report" name="btnViewPDF" id="btnViewPDF" style="height: 23px; margin-left: 50px; "/>
			</div>
		    </div>
			</td>
		</tr>
		</table>
		<div style="float:left;">
			<table id="StdWoChartGrid" ></table> 
			</div>
			</div>
			<div id="StdWoChartPager"></div>
		</div>
		</div> 
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>


	<input type="hidden" id="hdnBtnName" value="View Report"/> 

</form>