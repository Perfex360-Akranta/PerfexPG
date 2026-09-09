<script>
	jQuery(document).ready(function(){
		initialiseForm('frmjhActEntry');
		jQuery('#submitForm').val('frmjhActEntry');
		var url = jQuery('#hiddenUrl').val();
		fillComboBox('frmjhActEntry','cmbAchmFrequency', 'jhFrequency_combo.dashboard',"",false);
		viewgrid(url,"q=2");
		jQuery("#btnAddRow").click(function()
		{					 
			var row  = jQuery("#jhActDtlGrid").jqGrid('getDataIDs');			
			//addNewGridRow("jhActDtlGrid");
			addRow(row);						     
		});
		/*var keyid=jQuery("#hdnAchmKeyid").val();
		if(keyid.length>0){
			enableUIButton("btnDelete");
		}else
			disableUIButton("btnDelete");*/
		jQuery("#btnDelete").click(function()
		{			
			var keyid=jQuery("#hdnAchmKeyid").val();
			if(keyid.length>0){
				var row  = jQuery("#jhActDtlGrid").jqGrid('getDataIDs');
				var count=0;
				var countDtl=0;
				for(var i=0;i<row.length;i++)	{
					var rowid=row[i];
					var dtlkeyid=getGridCell("jhActDtlGrid",rowid,"txtJacdKeyid");
					if(!(dtlkeyid.trim().length>0))
						count++;
					else if(jQuery("#jqg_jhActDtlGrid_"+rowid).is(':checked')==true && dtlkeyid.trim().length>0 )
						countDtl++;
				}
				if(countDtl>0){
					deleterow();
					var gridData=getGridSelectArray('jhActDtlGrid');
					if(gridData.trim().length>0){
						deleterow();
						gridData="&jhActDtl="+gridData;
						var r=confirm("Are You Sure To Delete?");
						if(r)
							processAjaxCalls("jHActDtl_delete.dashboard?q=2",gridData,"successCallBack_delete","errorCallBack");
					}
					else{
						deleterow();
						if(!(count>0))
							alert("Select data to Delete");	
					}
				}else
					deleterow();
			}else{
				deleterow();
			}	 
										     
		});
	});
	function deleterow(){
		var row  = jQuery("#jhActDtlGrid").jqGrid('getDataIDs');
		for(var i=0;i<row.length;i++)	{
			var rowid=row[i];
			var dtlkeyid=getGridCell("jhActDtlGrid",rowid,"txtJacdKeyid");
			if(jQuery("#jqg_jhActDtlGrid_"+rowid).is(':checked')==true && !(dtlkeyid.trim().length>0))
				jQuery("#jhActDtlGrid").delRowData(rowid);
		}
	}
	function viewgrid(url,filter){
		var keyid=jQuery("#hdnAchmKeyid").val();
		if(keyid.length>0)
			filter="&MSTKEYID="+keyid;
		else
			filter="";
		
		processGridnew(url,filter,"jhActDtlGrid","jhActDtlPager","","","","load_complete");
	}
	function addRow(row)
	{
			 if ( row == null || row == '' || parseInt(row) <= 0) {
			 	var emptyItem =[{txtJacdKeyid:" ",shift:" ",txtJacdShiftid:" ",txtJacdDescription:" ",txtJacdTime:" ",txtJacdRemarks:" "}];
				jQuery("#jhActDtlGrid").jqGrid('addRowData',1, emptyItem[0]);
			 }	
			 else
			 {
				for(var i=0;i<row.length;i++)
						lastRow = row[i];
				var emptyItem =[{txtJacdKeyid:" ",shift:" ",txtJacdShiftid:" ",txtJacdDescription:" ",txtJacdTime:" ",txtJacdRemarks:" "}];
				jQuery("#jhActDtlGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
			 }
	}
	function frmjhActEntry_beforeDelete(){
		var keyid=jQuery("#hdnAchmKeyid").val();
		if(keyid.length>0){
			var r=confirm("Are You Sure To Delete?");
			if(r)
				return true;
			else
				return false;
		}else
			return false;
		
	}
	function frmjhActEntry_beforeSubmit(){
		var keyid=jQuery("#hdnAchmKeyid").val();
		var r;
		var row  = jQuery("#jhActDtlGrid").jqGrid('getDataIDs');
		if(row.length>0){
			var gridData=getGridSelectArray('jhActDtlGrid');
			if(gridData.length>0){
				gridData="&jhActDtl="+gridData;
				if(keyid.length>0){
					r=confirm("Data Changed. Do You Want to Proceed?");
					if(r){
						return gridData;
					}
					else
						return false;
				}else{
					return gridData;
				}
			}else{
				var count=0;
				for(var i=0;i<row.length;i++){
					rowid=row[i];
					if(jQuery("#jqg_jhActDtlGrid_"+rowid).is(':checked')==true)
						count++;
				}
				if(count>0)
					return false;
				else{
					if(keyid.length>0){
						r=confirm("Data Changed. Do You Want to Proceed?");
						if(r){
							return true;
						}
						else
							return false;
					}
				}
			}
		}
	}
	function frmjhActEntry_deleteSuccessCallback(result){
		alert(result.successData.msg);
		navigateToPrevForm();
	}
	function successCallBack_delete(result){
		alert(result.successData.msg);
		jQuery("#jhActDtlGrid").trigger("reloadGrid");
	}
	function frmjhActEntry_successsCallback(result){
		jQuery("#jhActDtlGrid").trigger("reloadGrid");
	}
</script>
<form id="frmjhActEntry">
	<div id="wrapper">
		<div>
			<table width="100%">
				<tr>
					<td width="30%" valign="top">
						<div>
							<label class="mandatory-lbl" class="easyui-paddingbfpx">Activity</label>
						</div>
						<div>
							<textarea style="width:255px" id="txtAchmActivity" name="txtAchmActivity">${requestScope.genTlJhactivitychartmst.achmActivity}</textarea>
						</div>
					</td>
					<td valign="top" width="30%">
						<div>
							<label class="mandatory-lbl" class="easyui-paddingbfpx">Frequency</label>
						</div>
						<div>
							<input type="text" class="easyui-combobox" style="width:255px" id="cmbAchmFrequency" name="cmbAchmFrequency" value="${requestScope.genTlJhactivitychartmst.achmFrequency}"/>
						</div>
					</td>
					<td>
						<div>
							<input type="button" class="easyui-button" value ="Add" id="btnAddRow" style="height:23px"/>
							<input type="button" class="easyui-button" value ="Delete" id="btnDelete" style="height:23px"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">	
						<div>
							<table id="jhActDtlGrid">	
								<tr>
									<td>
									</td>
								</tr>
							</table>
						</div>
						<div id="jhActDtlPager"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnAchmKeyid" name="hdnAchmKeyid" value="${requestScope.genTlJhactivitychartmst.achmKeyid}"/>
</form>