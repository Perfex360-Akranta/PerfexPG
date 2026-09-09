<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
	jQuery(document).ready(
			function() {
				initialiseForm('frmVisualReport');
				var url = jQuery("#hiddenUrl").val();
				
				
				
				viewGrid(url,"?q=2");
				jQuery("#btnNew").click(	function() {	
					 		var formtype = getFieldValue('hdnformtype','frmVisualReport');	
							navigateToNextForm("VisualSopDetailUpdate_input.VisualSop?&mode=create&formtype="+formtype,"Visual SOP");
						});
				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					processAjaxCalls("openFile.file?fileName=512 Visual SOP Blank format.xls", "", "", "", "", "new");					
				}); 
			});
	
	function viewGrid(url,filterString)
	{  		
		processGridnew("VisualSop_input.VisualSop",filterString,"VisualReport", "pager", "", "docDoubleClick", "");
		return true;
	}
	function docDoubleClick(id) {
		var rowData = jQuery("#VisualReport").jqGrid('getRowData',id);
		//sriram 27-Oct-2025 chnage uppercase to lowercase vsom_keyid 
		var keyId = rowData.vsom_keyid;
		
		//alert("Key ID" +keyId);
		//var formtype = getFieldValue('hdnformtype','frmVisualReport');
		var formtype = jQuery("#hdnformtype").val();
		//alert(" hdnformtype :: "+formtype);	
		navigateToNextForm("VisualSopDetailUpdate_input.VisualSop?&filterButton=false&closeOnSave=true&mode=MODIFY&keyId="+keyId+"&formtype="+formtype,"Visual SOP");
	}
</script>


<form name="frmVisualReport" id="frmVisualReport">

<div id='wrapperRpt'>
<div style="width : 332px;width: 325px\9;padding-left:2px;" >
				<div>
				<span><label class="notes" style="font-weight: bold"> Double Click on row to input/view details </label></span>
				</div>
				<br>
</div>

		
		<div style="margin-top: -19px">
			
			<table id=VisualReport >
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div></div>
		</div>
		<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
		<input type="hidden" id="hdnformtype" name="hdnformtype" value="${requestScope.formtype}" />
	</form>
