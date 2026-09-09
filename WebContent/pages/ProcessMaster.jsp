<script type="text/javascript" src="js/jquery.easyui.min.js"></script>



<div class="main-cntborder" align="center">
	<table align="center" style="padding-top:20px;padding-bottom:10px;">
			<tr>
				<td style="padding-left:450px;">
					
							<div><label>Process</label></div> 
                  			<div style="padding-bottom:10px;padding-right:10px;"> 
                				<input id="cmbprcss" name="cmbPrcss" class="easyui-combobox"  style="width:255px;" value=""  >
							</div>
							<div><label class="mndlbl">Name</label></div> 
                  			<div style="padding-bottom:10px;"> 
                				<input id="txtnme" class="easyui-text" name="txtNme"  style="width:255px; height : 21px;" value=""  >
							</div>
							<div><label class="mndlbl">Code</label></div> 
                  			<div style="padding-bottom:10px;padding-right:10px;"> 
                				<input id="txtcde" class="easyui-text" name="txtCde"  style="width:255px; height : 21px;" value=""  >
							</div>
							<div>Remarks</div>
							<div style="padding-bottom:10px;"><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width:255px;resize:none;" cols="" id="manufactRemark" name="manufactRemark"></textarea>
							</div>
					
				</td>
			</tr>
	</table>
</div>
