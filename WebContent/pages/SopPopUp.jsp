<script type="text/javascript">

jQuery(document).ready(function(){
	//alert("dgodfio"+jQuery('#txtSopdKeyid').val());
	jQuery('#submitForm').val('frmSoppopup');
	initialiseForm("frmSoppopup");
	jQuery("#txtSopdWhattodo ").css('text-transform', 'uppercase');
	jQuery("#txtSopdHowtodo ").css('text-transform', 'uppercase');
	jQuery("#txtSopdRemarks ").css('text-transform', 'uppercase');

	fillComboBox("frmSoppopup","cmbSopdTools","employee.commonFilter" );
	jQuery("#btnsave").click(function(){
		saveForm("frmSoppopup","SopPopUp_save.sop");
		});
	numericTextBox("txtSopdTime");
});
	
</script>

<form id="frmSoppopup" name="frmSoppopup">


<div align="center">

<table style="padding-top:10px"  >


	
	
<tr>
		<td>
			
			
			<div >
			 <label> What to do </label>
			 </div>
			<div class="easyui-paddingbfpx" >
									<textarea rows="2" cols="2" style="width: 300px; height : 50px;" maxlength=50   id="txtSopdWhattodo" name="txtSopdWhattodo">${requestScope.qtmTlSopdtl.sopdWhattodo}</textarea>
									
										</div>
							
								
			
			<div>
			<label> How to do </label>
			</div>
			<div class="easyui-paddingbfpx" >
									<textarea rows="2" cols="2" style="width: 300px; height : 50px; "maxlength=50  id="txtSopdHowtodo" name="txtSopdHowtodo">${requestScope.qtmTlSopdtl.sopdHowtodo}</textarea>
								
											
			</div>
			
			
			<div >
			<label> Condition Required </label>
			</div>
			<div class="easyui-paddingbfpx" >
			<input id="txtSopdConditionreq" type="text" class="easyui-text" size="15" name="txtSopdConditionreq"  value="${requestScope.qtmTlSopdtl.sopdConditionreq}" maxlength="15"    style=" width :300px;">
	       </div>
								
								
								
								<div>
									<label> Time </label>
									</div>
									<div class="easyui-paddingbfpx" >
			<input id="txtSopdTime" type="text" class="easyui-text" value="${requestScope.qtmTlSopdtl.sopdTime}" size="15" name="txtSopdTime"  maxlength="15"    style=" width : 300px;">
							
				</div>				
								
								
					<div> 
				 <label>Tools  </label>
				 </div>
				<div class="easyui-paddingbfpx" >
				  	<input id="cmbSopdTools" name="cmbSopdTools" class="easyui-combobox"  value="${requestScope.qtmTlSopdtl.sopdTools}" style="width:300px;" > 
				</div>
		
		<div >
		<label>Remarks  </label>
		</div>
		<div class="easyui-paddingbfpx" >
				<textarea rows="1" cols="80" style="width: 300px; height : 50px;" maxlength=50 id="txtSopdRemarks" name="txtSopdRemarks">${requestScope.qtmTlSopdtl.sopdRemarks}</textarea>
				
		</div>			
								
</td>

</tr>

</table>


<input type="hidden" id="mode"/>


<input id="txtSopdSopmkeyid" type='hidden' name="txtSopdSopmkeyid" value="${requestScope.masterkeyid }" />


<input id="txtSopdKeyid" type='hidden' name="txtSopdKeyid" value="${requestScope.qtmTlSopdtl.sopdKeyid }" />
</div>
</form>





