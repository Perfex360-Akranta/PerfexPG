<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	

jQuery(document).ready(function(){
	initialiseForm('frmStdWorkSheet');
	jQuery('#submitForm').val('frmStdWorkSheet');
	formatDateBox('dteSWSDate','dd-MMM-yyyy');
	fillComboBox("frmStdWorkSheet","cmbSWSProcess","process.commonFilter" );
	fillComboBox("frmStdWorkSheet","cmbSWSApprovedBy","employee.commonFilter" );
	fillComboBox("frmStdWorkSheet","cmbSWSPreparedBy","employee.commonFilter" );
	loadFunctionalLocation("swsFunctionalLoc","functionalLoc.stdwosh","swsFunctionalLocValues","frmStdWorkSheet","");
	var setNew=getFieldValue('hdnsetNew');
	if (setNew=="Yes"){
		setFieldValue("cmbSWSTypeOfManPower","");
		setFieldValue("dteSWSDate","");
		setFieldValue("spnSWSDate","");
		setFieldValue("cmbSWSProcess","");
		setFieldValue("txtSWSMajorSteps","");
		setFieldValue("cmbSWSPreparedBy","");
		setFieldValue("cmbSWSApprovedBy","");
	}
	else
	{
		setFieldValue("cmbSWSTypeOfManPower","M");
		setFieldValue("dteSWSDate","19-Sep-2013");
		setFieldValue("spnSWSDate","00:00");
		setFieldValue("cmbSWSProcess","TRIMMING");
		setFieldValue("txtSWSMajorSteps","Step 1");
		setFieldValue("cmbSWSPreparedBy","ATPL");
		setFieldValue("cmbSWSApprovedBy","ATPL");
		loadFunctionalLocation("swsFunctionalLoc","functionalLoc.stdwosh","swsFunctionalLocValues","frmStdWorkSheet","&machId=MCH002061");
	}
});
</script>
<form id="frmStdWorkSheet" name="frmStdWorkSheet" style="align:center">
	<div id="wrapper"  style="width:100%">
		<div  class="main-cntborder" style="margin-top:3%;width:800px;">
			<div style="margin:3%;">
				<table width="100%">
					<tr>
						<td colspan = "3">
							<div id="frmStdWorkSheetFuntKeyIds">
								<input type="hidden" id="factory" name="cmbswsFactoryid" value=""></input>
								<input type="hidden" id="section" name="cmbswsSectionid" value=""></input>
								<input type="hidden" id="cell" name="cmbswsCellid" value=""></input>
								<input type="hidden" id="machine" name="cmbswsMachineidhdn" value=""></input>
					  		</div>
					 		<div id="swsFunctionalLoc" style=""></div>		 		
						</td>
					</tr>
				</table>
				<div   style="padding-top:20px;  width:100%">
					<table cellspacing="3%"  width="100%">
						<tr>
							<td >
								<label>Type Of Man Power</label>
							</td>
							<td style="padding-left:50px; ">
							<label>Date & Time</label>
						</td>
					</tr>
					<tr>
						<td>
						    <select id="cmbSWSTypeOfManPower" class="easyui-combobox" name="cmbSWSTypeOfManPower"  panelHeight="80px"  style="width:265px;" >
							<option value="M"> MANUAL WORK</option>
							<option value="P"> PROCESS</option>
							<option value="T"> TRAVEL</option>
							<option value="W"> WAIT</option>
						 </select> 
					</td>
					<td style="padding-left:50px;">
					<input id="dteSWSDate"  name="dteSWSDate" clear="false" class="easyui-datebox" value="" style="width: 180px;" />
					<span class="spinner" ><input  id="spnSWSDate" name="spnSWSDate" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 77px;" ></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>Process</label>
						</td>
						<td style="padding-left:50px;">
							<label>Major Steps</label>
						</td>
					</tr>
					<tr>
						<td>
							<input  id="cmbSWSProcess" name="cmbSWSProcess" class="easyui-combobox" value="" style="width: 265px;" />
					</td>
					<td rowspan="3" style="padding-left:50px;">
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSWSMajorSteps" name="txtSWSMajorSteps" style="resize:none;width:265px;" maxlength="500">  </textarea>
						</td>
					</tr>
					<tr>
						<td>
							<label>Prepared By</label>
						</td>
					</tr>
					<tr>
						<td>
							<input  id="cmbSWSPreparedBy" name="cmbSWSPreparedBy" class="easyui-combobox" value="" style="width: 265px;" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Approved By</label>
						</td>
						<td style="padding-left:50px;" rowspan="2">
					<input type="button" class="easyui-button" value ="Insert" name="btnSWSInsert" id="btnSWSInsert" style="height:23px"/>
					<input type="button" class="easyui-button" value ="Update" name="btnSWSUpdate" id="btnSWSUpdate" style="height:23px"/>
					<input type="button" class="easyui-button" value ="Delete" name="btnSWSDelete" id="btnSWSDelete" style="height:23px"/>
						</td>
					</tr>
					<tr>
					<td>
					<input  id="cmbSWSApprovedBy" name="cmbSWSApprovedBy" class="easyui-combobox" value="" style="width: 265px;" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="hdnsetNew" name="hdnsetNew" value="${requestScope.vnew}"/>
</form>