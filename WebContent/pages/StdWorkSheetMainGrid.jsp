<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	

jQuery(document).ready(function()
		 {	
 
	var mainForm = jQuery("#mainFormValClti").val();
	   
	if (mainForm !=true) 
	{
		
		
		jQuery(".clitdiv").attr('id', 'wrapperRpt');
		
	}
			 var url = jQuery('#hiddenUrl').val();
			 var tableCaption = "Equipment Query";
			 viewGrid(url,"?q=2",tableCaption);

			// var btnName = jQuery("#hdnBtnName").val();
				//jQuery("#btnViewPDF").val(btnName);
				jQuery("#btnViewPDF").click(function(){
					
					processAjaxCalls("openFile.file?fileName=standrizedWork_sheet.xlsx", "", "", "", "", "viewPdf");					
				});
				var factId = jQuery("#frmStdWorkSheetGrid input[id='factory']").val();
			    var sectionId = jQuery("#frmStdWorkSheetGrid input[id='section']").val();
			    var cellId = jQuery("#frmStdWorkSheetGrid input[id='cell']").val();
			    var machId = jQuery("#frmStdWorkSheetGrid input[id='machine']").val();
			    var flid = jQuery("#frmStdWorkSheetGrid input[id='flid']").val();
			    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId=MCH0002058"+"&flid="+flid+"";
			  //  alert("tamil");
			    loadFunctionalLocation("stdfunLoc","functionalLoc.sop","frmStdWorkSheetGridLocationfunLocationValues","frmStdWorkSheetGrid",dataStr);
			    formatDateBox('dteFromDate','dd-MMM-yyyy');
				formatDateBox('dteToDate','dd-MMM-yyyy');
				fillComboBox("frmStdWorkSheetGrid","cmbStswApprovedby","employee.commonFilter" );
	 });

		   function viewGrid(url,dataString,tableCaption)
		     {
		    processGridnew("STDWorkSheet_input.stdwosh",dataString,"StdWoSheetGrid","StdWoSheetPager",tableCaption," "," ","");
		     }	

				
function doubleclick(id){
	var vnew= "No";
	/*
	var rowData = jQuery('#list').jqGrid("getDataIDs",id);
	var Process = rowData.Process;
	var TypeOfManpower = rowData.TypeOfManpower;
	var FunctionalLocation = rowData.FunctionalLocation;
	var MajorSteps = rowData.MajorSteps;
	var DateTime = rowData.DateTime;
	var PreparedBy = rowData.PreparedBy;
	var ApprovedBy = rowData.ApprovedBy;
	navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&Process='+Process+'&TypeOfManpower='+TypeOfManpower+'&FunctionalLocation='+FunctionalLocation+'&MajorSteps='+MajorSteps+'&DateTime='+DateTime+'&PreparedBy='+PreparedBy+'&ApprovedBy='+ApprovedBy,"Standardized Work Sheet");
	*/
	//navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&new='+vnew,"Standardized Work Sheet");
	
}
</script>
<form id="frmStdWorkSheetGrid" name="frmStdWorkSheetGrid">
<div id="wrapper" style="width:100%" >
<div id="frmStdWorkSheetGridFuntKeyIds"  >							
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
			 <table id="StdWoSheetGrid" ></table> 
			<div id="StdWoSheetPager"></div>
		</div> 
	
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	</div>
	</div>
</form>