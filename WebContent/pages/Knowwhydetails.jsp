<style>
.table
{
padding-top: 10px; 
}
</style>

<script>
jQuery(document).ready(function(){
	//alert("popup");
	initialiseForm('frmKnowDetails');
	
	processGridnew("KnowWhygridpop_input.KnowWhy","q=2&detailgrid="+true,"knowwhygridpop","pagerkwg","","doubleClickGrid");

	if(screen.width <= 1024){
	
	 jQuery('.table').css('margin-left','-200');
   if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0")
       jQuery('.table').css('margin-left','0');
}    
	else{
		
	}


});

function doubleClickGrid(id){ 
	
	var rowData = jQuery("#knowwhygridpop").jqGrid('getRowData',id);
	var selId = rowData.PossibleCauses;
	var know = rowData.KnowWhy;
	var sol = rowData.Solution;
	var normal = rowData.NormalCondition;
	jQuery("#txtpossibleClauses").val(selId);
	jQuery("#txtKnowwhy").val(know);
	jQuery("#txtSolution").val(sol);
	jQuery("#txtNormalCondition").val(normal);
	//alert("Possible Clauses"+selId+"Know Why"+know+"Solution"+sol+"Normal"+normal);	
	//navigateToNextForm("KnowWhyDetails_view.KnowWhy?possible="+selId+"&know="+know+"&Sol"+sol+"&Normal="+normal,"");

	
}
</script>

<form action="" id="frmKnowDetails" method="post">

	 
		<div style="padding-left:0px;" >
			<table >
				<tr style="padding-top: 12px;">
					<td >
						<div class="easyui-paddingbfpx" >
							<label class="mandatory-lbl">Possible Causes</label>
						</div>		
						<div class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="500" rows="2" cols="80"  id="txtpossibleClauses" name="txtpossibleClauses"  style="width: 255px; ; height : 52px;" ></textarea>	
						</div>
						<div class="easyui-paddingbfpx" >
							<label class="mandatory-lbl">Solution</label>
						</div>		
						<div class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="500" rows="2" cols="80" id="txtSolution" name="txtSolution"  style="width: 255px; height : 52px;" ></textarea>	
						</div>
									
					</td>
					<td style=" padding-left:20px;">
						<div class="easyui-paddingbfpx">
							<label class="mandatory-lbl">Know Why</label>
						</div>		
						<div class="easyui-paddingbfpx">
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="500" rows="2" cols="80" id="txtKnowwhy" name="txtKnowwhy"  style="width: 255px;height : 52px; "></textarea>	
						</div>
						<div class="easyui-paddingbfpx"  >
							<label class="mandatory-lbl">Normal Condition</label>
						</div>		
						<div class="easyui-paddingbfpx">
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="500" rows="2" cols="80" id="txtNormalCondition" name="txtNormalCondition"  style="width: 255px; height : 52px;"></textarea>	
						</div>
					</td>
					<td  style=" padding-left:20px;">
						<div class="easyui-paddingbfpx"  style="width: 56px;">
								<input class="easyui-button" type="button" value="Insert" id="btnIns" name="btnIns" style="height: 24px; width:60px;" />
						</div>
						<div class="easyui-paddingbfpx"  style="width: 56px; padding-top: 10px;">
								<input 	class="easyui-button" type="reset" value="Clear" id="btnUpd" name="btnUpd" style="height: 24px; width:60px;" />
						</div>
						<div class="easyui-paddingbfpx"  style="width: 56px; padding-top: 10px;">
								<input class="easyui-button" type="button" value="Delete" id="btnDel" name="btnDel" style="height: 24px;width:60px;" />
						</div>
					</td>
				</tr>
			</table>

			<div id=""  class="table" >
				<table style=""  id='knowwhygridpop'  ><tr><td></td></tr></table>
				<div id='pagerkwg'></div>
			</div>
		</div>
	

</form>
