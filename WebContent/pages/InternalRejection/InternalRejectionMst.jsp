<script type="text/javascript">

	jQuery(document).ready(function(){		
		//initialiseForm('frmInternalRejectionMst');
		//jQuery('#submitForm').val('frmInternalRejectionMst');
		
		//any url, any filterString, any tableId, any pagerId, any tableCaption, any  doubleClickFunction, any tableHeaderSpanCallback, any ongridcompletecallback, any selectRowFunction, any filterNeed)
		//processGridnew('intRejEntryMstGrid_input.ire?',"q=1","internalRejectionMst","","Internal Rejection Entry Master","","","","");
		processGridnew("intRejEntryMstGrid_input.ire","?q=2","intRejMstGrid", "intRejpager", "", "doubleClickGrid", "");
		
		jQuery('#btnNew').click(function() {
			var masterid  = " " ;
			//alert('masterid' + masterid);
			navigateToNextForm("intRejEntryTest_input.ire?&masterid="+masterid);
		});
	
	});
	
	function doubleClickGrid(rowid) 
	{
		var rowData = jQuery("#intRejMstGrid").jqGrid('getRowData',rowid );
		var masterid  = rowData.Keyid;		
		navigateToNextForm("intRejEntryTest_input.ire?&masterid="+masterid);
	}
	
	
</script>

<form id="frmInternalRejectionMst" >
	<div style="margin-top:30px;margin-left:30px">
	<div>
		<table>
		<tr>
		<td >
			<div style="padding-left:0%\9; ">
					<!-- <input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
		 -->		</div>
			</td>
			<td >
				<div style="width : 600px;" >
					<label class="notes"> Double Click on row to input/view details </label>
				</div>
			</td>
			
		</tr>
		</table>

	</div>
	<div class="easyui-paddingbfpx" style="padding-left: 0px;width:100%" >
	
			<div id="wrapperRpt" style="margin-top:10px;margin-left:10px" >			
				<table id="intRejMstGrid"></table>
				<div id="intRejpager"></div>
			</div>	
		
	</div>
	</div>

	<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnType" name="hdnType" value="${requestScope.type}"/>
</form>