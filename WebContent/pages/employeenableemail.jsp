<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"><!--
	jQuery(document).ready(function() {
		initialiseForm("frmempmailreport");
		jQuery("#submitForm").val("frmempmailreport");
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url, "q=2");
	});

	function viewGrid(url, filterString){
      processGridnew("employemmailreport_input.emr", filterString,"Gridmailreport", "pager", "", "doubleClick");
	}
	
	 
	function frmempmailreport_beforeSubmit(){
		var paramJsonArr =convertJsonArr();
		alert("paramJsonArr"+paramJsonArr);
  	   if(paramJsonArr.length>2){ 
  		 var paramJsonArr=paramJsonArr;
  		 alert("paramJsonArr2222"+paramJsonArr);
	     return "&paramJsonArr="+paramJsonArr;         
		}
	     else
		  {
          alert("select any Field");
          return false;
		  }
	}	

	function frmempmailreport_successsCallback(result){  
	      processGridnew("employemmailreport_input.emr", "q=2","Gridmailreport", "pager", "", "doubleClick");
	}
	
	function convertJsonArr(){
		var allrow = jQuery("#Gridmailreport").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if (jQuery('#jqg_Gridmailreport_' + rowno).is(':checked')) {
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO=jsonArrO.replace('&',',');
				//jsonArrO=jsonArrO.replace('undefined','Y');
				jsonArrO = jsonArrO.slice(0, -1) + '},';
				
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}
	
	function formatterChkCA(cellValue,options, rowObject){
	     var gridId = options.gid;
	     var checked ="";
	 	 if(cellValue == "Y")
	 	 checked ="checked";		
		 return '<input id="chkCA'+gridId+rowObject[0]+'" name="chkCA'+gridId+rowObject[0]+'" '+ checked+' type="checkbox" />';	
	}
	
   function viewGrid(url,filterString)
	{ 
		if( validateFilterSelection(filterString))
		{
			var functionalloc=getFilterValue(filterString, "cmbCircle");
			jQuery("#hiddencircle").val(functionalloc);
			var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
			jQuery("#hiddenRemoveBlank").val(removeBlank);
			filterString += '&Flid=f&firstClick=Y';	
  processGridnew(url,filterString,"Gridmailreport", "pager", "", "doubleClick");
  return true;
	}
return false;	
	}
   function validateFilterSelection(filterString){
    return  true;
}		
</script>
<form id="frmempmailreport" name="frmempmailreport">
	<div style="margin-top: 40px; margin-left: 70px;">
		<table id="Gridmailreport">
		</table>
		<div id="pager"></div>
	</div>
	<input type="hidden" id="mode" name="mode" value="create" />
</form>