<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">

    jQuery(document).ready(function(){	
	 initialiseForm('frmnearMissGrid');
	// jQuery('#submitForm').val('frmnearMissGrid');
	 //processGridnew("NearMissReport_input.nmr","q=2","nearMissGrid","nearPager","","doubleclick");
	 var btnName = jQuery("#hdnBtnName").val();
	 var url = jQuery('#hiddenUrl').val();
     var mode=jQuery('#hdnmode').val();
     var formType = jQuery("#hdnFormType").val();
     if (formType=='' || formType==' '){
		formType="create";
		jQuery('#btnNew').show();
	 }
	 else
		jQuery('#btnNew').hide();

     if (mode=='view'){
		 jQuery('#btnNew').hide();
	 }
	 
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("Nearmiss_input.nmr?grid=false&clearfrom=false","Near Miss Entry");
	});
	
	var filterString = jQuery('#hdnFilterString').val();
	
	viewGrid(url,"?&");
	
	
});

function viewGrid(url,dataString)
{
	 var formType = jQuery("#hdnFormType").val();
	 var mode=jQuery('#hdnmode').val();
		if (formType=='' || formType==' '){
				formType="create";
		}
		if(mode=='view')
			mode="VIEW";
		
		dataString+="&formType="+formType+"&mode="+mode;
		
	if(validateFilterSelection(dataString))
	{   
		processGridnew("NearMissReport_input.nmr?q&",dataString,"nearMissGrid","nearPager","","doubleclick");
		return true;
	}
	
}


function validateFilterSelection(filterString){
	
	return true;
}


function doubleclick(id)
{
	var rowData = jQuery("#nearMissGrid").jqGrid('getRowData',id);
	var keyId=rowData.Keyid;
	var status = rowData.Status;
	var mode="MODIFY";
	var mode=jQuery('#hdnmode').val();
	
	if(status=="Completed")
		mode="VIEW";

	if(mode=='view')
		mode="VIEW";
	 
	
	var formType = jQuery("#hdnFormType").val();
	var ds ="?grid=true&filterButton=false&clearfrom=false&keyId="+keyId+"&mode="+mode+"&formType="+formType;
	navigateToNextForm("Nearmiss_input.nmr"+ds,"");

}
</script>
<form id="frmnearMissGrid" name="frmnearMissGrid" >
<div id='wrapperRpt' style="width:100%" >
<table>
	<tr>
	    
		<td style='padding-left:6px;'>
<!--		<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 90px;height:24px;"/>-->
		</td>
		<td >
			<div style=" padding-left:2px;margin-top:-24px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>
<div style="margin-top: -10px">
<table id="nearMissGrid">
<tr><td></td></tr>
</table>
<div id="nearPager"></div></div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
<input type="hidden" class="easyui-button" id="hdnmode"	name="hdnmode" value="${requestScope.mode}" />
</form>
<input type="hidden" class="easyui-button" id="hdnFormType"	name="hdnFormType" value="${requestScope.nearmiss}" />

