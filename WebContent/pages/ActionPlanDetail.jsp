 <script type="text/javascript">
 jQuery(document).ready(function()
			{
	
 fillComboBox("frmactnplnadd","cmbAplmPhenomenaid","phenomena.ap");	
 fillComboBox("frmactnplnadd","cmbAplmCauseid","cause.commonFilter");
 viewGrid("ActionPlanDetailAdd_input.api","q=2");
 jQuery("#btnInsertadd").click(function(){
		
		saveForm('frmactnplnadd','ActionPlanAddDetail_save.api');
		
	});
			});
 function viewGrid(url,dataString)
 {
 	processGridnew(url,dataString,"actiondetgrid", "pagergrid","test");
 }
 function frmactnplnadd_successsCallback(result){
	 var keyid=result.AplId;

	 jQuery("#actiondetgrid").trigger("reloadGrid");
	 }
 </script>
 <form id="frmactnplnadd" name="">
 <div style="padding-left: 6%;  padding-left: 7%\9;padding-top: 20px; " >
 <table>
 <tr>
				<td>
					
			
	
				<div class="easyui-paddingbfpx">
					<label>Phenomena</label>
				</div>
				<div class="easyui-paddingbfpx">
			<input id="cmbAplmPhenomenaid" name="cmbAplmPhenomenaid" type="text" class="easyui-text" maxlength="95" style="width:300px;" value="${requestScope.genTlActionplanmst.aplmPhenomenaid}" />
					
				</div>
				<div class="easyui-paddingbfpx">
					<label>Cause</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="cmbAplmCauseid" name="cmbAplmCauseid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.genTlActionplanmst.aplmCauseid}" />
					
				</div>
				<div class="easyui-paddingbfpx">
				<label >Reason</label>
					
				</div>
				<div class="easyui-paddingbfpx">
				<textarea rows="1" cols="34"  id="txtAplmReason" name="txtAplmReason" style="width: 300px; height : 60px;">${requestScope.genTlActionplanmst.aplmReason}</textarea>
					
				</div>
				
				
						<div class="easyui-paddingbfpx"  style="padding-top 22px;">
									<span><input class="easyui-button" type="button" value="Insert"
										id="btnInsertadd" name="btnInsertadd" style="height: 21px; width : 47px;"  /> </span><span style="padding-left: 8px;"><input
										class="easyui-button" type="button" value="Delete"
										id="btnDelete" name="btnDelete" style="height: 21px;width : 47px;" /></span> <span style="padding-left: 8px;"><input
										class="easyui-button" type="button" value="Clear"
										id="btnClear" name="btnClear" style="height: 21px;width : 41px;" /> </span>
								</div>
					
					
					
				
	
				</td>
				<td valign="top" style="padding-left: 35px">
			
					<div class="easyui-paddingbfpx">
				<label>How To Do</label>
				</div>
				<div class="easyui-paddingbfpx">
					<textarea rows="5" cols="34"  id="txtAplmHowexplanation" name="txtAplmHowexplanation" style="width: 300px; height : 70px;">${requestScope.genTlActionplanmst.aplmHowexplanation}</textarea>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Target</label>
				</div>
				<div class="easyui-paddingbfpx">
<input id="txtAplmTarget" name="txtAplmTarget" type="text" class="easyui-text" maxlength="95" style="width:300px;" value="${requestScope.genTlActionplanmst.aplmTarget}" />		
		</div>
				<div class="easyui-paddingbfpx">
				<label>Saving</label>
				</div>
				<div class="easyui-paddingbfpx">
<input id="txtAplmSavings" name="txtAplmSavings" type="text" class="easyui-text" maxlength="95" style="width:300px;" value="${requestScope.genTlActionplanmst.aplmSavings}" />				
</div>

				
</tr>
 
 </table>
 </div>
 	<div style="padding-top: 20px; padding-left: 7%\9;">
			<table id='actiondetgrid'>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pagergrid'></div>
			</div>
 
 
 </form>