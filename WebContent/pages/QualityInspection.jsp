<script>
jQuery(document).ready(function(){
	initialiseForm('frmQua');
	formatDateBox('dteGR','dd-MMM-yyyy');
	formatDateBox('dteDate','dd-MMM-yyyy');
	formatDateBox('dteReceipt','dd-MMM-yyyy');
	formatDateBox('dtePO','dd-MMM-yyyy');
	formatDateBox('dtePR','dd-MMM-yyyy');
	var factId = jQuery("#frmQua input[id='factory']").val();
    var sectionId = jQuery("#frmQua input[id='section']").val();
    var cellId = jQuery("#frmQua input[id='cell']").val();
    var machId = jQuery("#frmQua input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;
    if(dataStr.trim().length<=0)
    	dataStr = "";	  					
    loadFunctionalLocation("quaLocation","functionalLoc.brdn","frmQuaLocationfunLocationValues","frmQua",dataStr);
    processGridnew("qualityInspection_input.qins", "q=2", "QualityGrid", "pager", "", "doubleClick", "");
    fileManagerPopUp("","ABN","frmQua","btnfilemgr","abnFilemgr");
});
function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"ABN","","","");
	}
	
}
function doubleClick(id)
{	var rowData = jQuery("#QualityGrid").jqGrid('getRowData',id);
	var Code = rowData.MaterialCode;
	var date = rowData.MasterDate;
	var description = rowData.MaterialDescription;
	var inspected = rowData.QualityInspected;
	var accepted = rowData.QualityAccepted;
	var rejected = rowData.QualityRejected;
	
	LoadPopUp("quality","qualityPopup_input.qins?&code="+Code+"&date="+date+"&description="+description+"&inspec="+inspected+"&accepted="+accepted+"&rejected="+rejected,true, "70%", "78%", "7.4%", "21%", "quality_SuccessCallBack", "Details Of quality");
}
</script>
<form name="frmQua" id='frmQua'>
<div id="wrapper" >

<div >
<div id="frmQuaFuntKeyIds"  >							
									<input type="hidden" id="factory" name="factory"  value="" ></input>
									<input type="hidden" id="section" name="section"  value=""></input>
									<input type="hidden" id="cell"    name="cell"     value=""></input>
									<input type="hidden" id="machine" name="machine"  value=""></input>							
								</div>						
							<div id="quaLocation" style="width:910px;width:926px\9;"></div>	
	<table>
		
		<tr>
			<td >
				<div class='easyui-paddingbfpx'><label>Inspection</label></div>
				<div class='easyui-paddingbfpx'>
					<input type="text" id='txtInspection' name='txtInspection' class='easyui-text' maxlength='20' style="width:255px;height:21px;"/>
				</div>
			</td>
			<td style="padding-left:10px;">
				<div class='easyui-paddingbfpx'><label>GR.No</label><label style="padding-left:87px;">Date</label></div>
				<div class='easyui-paddingbfpx'>
					<input type="text" id='txtGRNo' name='txtGRNo' class='easyui-text' style="width:110px;height:21px;"/>
					<span style="padding-left:10px;">
						<input type="text" id='dteGR' name='dteGR'class='easyui-text'  style="width:100px;height:21px;"/>
					</span>
				</div>
			</td>
			<td style="padding-left:20px;">
			<div class='easyui-paddingbfpx'><label>PR.No</label><label style="padding-left:87px;">Date</label></div>
				<div class='easyui-paddingbfpx' >
					<input type="text" id='txtPRNo' name='txtPRNo' class='easyui-text' style="width:110px;height:21px;"/>
					<span style="padding-left:10px;">
						<input type="text" id='dtePR' name='dtePR'class='easyui-text'  style="width:100px;height:21px;"/>
					</span>
				</div>
			</td>
		</tr>
		<tr>
		
			<td  valign="top">
				<div class='easyui-paddingbfpx'><label>Inspection Type</label></div>
				<div class='easyui-paddingbfpx'>
					
					
					<select  id="cboInspectiontype" name="cboInspectiontype" class="easyui-text" style="width:255px;height:21px;">
										<option value=" "></option>
										<option value="1">Production</option>
										<option value="2">Quality</option>
										<option value="3">Maintenance</option>
										<option value="4">Gate Entry</option>
										<option value="5">Others</option>
					</select>
					
				</div>
			</td>
				
			<td style="padding-left:10px;">
				<div class='easyui-paddingbfpx'><label>PO.No</label><label style="padding-left:87px;">Date</label></div>
				<div class='easyui-paddingbfpx'>
					<input type="text" id='txtPoNo' name='txtPoNo' class='easyui-text' style="width:110px;height:21px;"/>
					<span style="padding-left:10px;">
						<input type="text" id='dtePO' name='dtePO'class='easyui-text'  style="width:100px;height:21px;"/>
					</span>
				</div>
			</td>
			<td style="padding-left:20px;">
			<div class='easyui-paddingbfpx'><label>Lorry Receipt No</label><label style="padding-left:32px;">Date</label></div>
				<div class='easyui-paddingbfpx'>
					<input type="text" id='txtReceiptNo' name='txtReceiptNo' class='easyui-text' style="width:110px;height:21px;"/>
					<span style="padding-left:10px;">
						<input type="text" id='dteReceipt' name='dteReceipt'class='easyui-text'  style="width:100px;height:21px;"/>
					</span>
				</div>
			</td>
		</tr>
		<tr>
		
		
		<td valign="top">
				<div class='easyui-paddingbfpx'><label>Date</label></div>
				<div class='easyui-paddingbfpx'>
					<input type="text" id='dteDate' name='dteDate'class='easyui-text'  style="width:100px;height:21px;"/>
				</div>
			</td>
			
			<td colspan="3">
			<div style=" padding-left:370px;">
					 <span  id="abnFilemgr" style="left:00%;left:00%\9;top:30px;top:30px\9;" >
     		
             </span> 
             </div>
			</td>
			
		</tr>
	</table>
<div style='float:left; margin-left:0px\9;'>
	<table id='QualityGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
</div>
</div>
</form>