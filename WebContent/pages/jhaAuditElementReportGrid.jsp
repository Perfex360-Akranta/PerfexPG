<script>
	jQuery(document).ready(function(){
		 viewGrid("jhAuditElementReportGrid_input.jhAuditItc","?q=2");
		
	});
	function doubleClickFunction(id){
		var rowData = jQuery("#auditEleRepMainGrid").jqGrid('getRowData',id);
		var keyid= rowData.KEYID;
		navigateToNextForm("jhAuditElementReport_input.jhAuditItc?Keyid="+keyid);
	}

	function viewGrid(url,filterString)
	{
		
		if( validateFilterSelection(filterString))
		{
			processGridnew(url,filterString,"auditEleRepMainGrid","auditEleRepMainPager","","doubleClickFunction","","ongridcompletecallback");
			return true;
		}
		return false;
			
	}
	function validateFilterSelection(filterString)
	{
		if(filterString.length>10){
		 if( ! checkFilterValueExist(filterString,"cmbSectid"))
		 {
			if(filterString==null || filterString==''||filterString=="")
			{}
			else
			{
				alert("Select DMT");
				return false;
				}
			}
		}
		return true;
		
	}
</script>

<form id="frmAuditElementGrid">
	<div id="WrapperRpt">
		<table>
			<tr>
				<td>
					<div>
						<table id="auditEleRepMainGrid"><tr><td></td></tr></table>
						<div id="auditEleRepMainPager"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>