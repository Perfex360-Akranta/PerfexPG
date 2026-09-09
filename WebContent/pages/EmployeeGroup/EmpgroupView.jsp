<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Empgroup View</title>
<script type="text/javascript">

jQuery(document).ready(function(){
	
	 initialiseForm('frmEmpgroupView');				
      	   jQuery('#submitForm').val('frmEmpgroupView');
     		 var url = jQuery('#hiddenUrl').val();
     		var mode = "VIEW";
     		viewDataGrid(url,"?q=");
	 	 
	
});


function viewDataGrid(url,filterString)
{		filterString += "&active=Y";
		processGridnew(url,filterString+'&closeOnSave=true',"viewDataGrid","viewpager","","doubleClickGrid");			
	return true;	
}
function doubleClickGrid(rowid){
		
        var rowData = jQuery("#viewDataGrid").jqGrid('getRowData',rowid);
     	var keyid=rowData.KEYID;
      	navigateToNextForm("EmpgroupCreation_input.emp?keyid="+keyid+"&closeOnSave=true"); 
     
}
function NewPage()
{
	
	 navigateToNextForm("EmpgroupCreation_input.emp");

}
 

</script>

</head>
<body>
<form name="frmEmpgroupView" id="frmEmpgroupView">
  <table style="margin-top:10px;padding-left:25px;"">
	<tr>
		<td>
			<div style="padding-left:4px;">
<!--			       <a href="/pages/VoiceOfCustomer/VOCParamConfig.jsp">btnNEW</a>-->
			     <input id="btnNew" class="easyui-button" type="button" value="New Entry" name="btnNEW" onclick="NewPage()">
			     <span style="padding-left:0px;">
                      <label class="notes"> Double Click the Data row to view the Group Details </label>
                 </span>
			</div>
		</td>
	</tr>
  </table>

<div  class="easyui-paddingbfpx"  style="padding-left:30px;" >
<table id="viewDataGrid"></table>
		<div id="viewpager"></div>
		</div>
		
			<input type="hidden" id="mode" value="create" name="mode" /> 

</form>
</body>
</html>