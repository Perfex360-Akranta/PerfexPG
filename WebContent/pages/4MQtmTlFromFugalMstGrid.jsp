<script type="text/javascript">

jQuery(document).ready(function(){
	//alert('inside');
	var from=jQuery("#hdnfromValue").val();
	if(from=="View")
	{
	//alert('inside');
	disableForm("frmgrd4MQtmTlFrmFugalMst");
	
	}
	 var url = jQuery('#hiddenUrl').val();
	// alert(url);
	viewGrid(url,"?q=2");
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "4MFromFugal";
		
		processGridnew(url,filterString,"grd4MQtmTlFrmFugalMst","grd4MQtmTlFrmFugalMstpager1",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	}
	
} 
function validateFilterSelection(filterString){
	
	return true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#grd4MQtmTlFrmFugalMst").jqGrid('getRowData',rowid );
	//alert(rowData.KEYID);
	//alert(rowData.LOGINID);
	var url="QtmTl4mfuguaimst_input.4mfugal?&keyId="+rowData.KEYID+"&mode=U";
	var from=jQuery("#hdnfromValue").val();
		//alert(from);
		if(from=="View")
			url+="&from=View";
			else if(from=="Updation")
				{
				url+="&from=Updation";
				}
			else if(from=="Approval")
				{
				url+="&from=Approval";
				}
	navigateToNextForm(url);
}
</script>


<form name="frmgrd4MQtmTlFrmFugalMst" id="frmgrd4MQtmTlFrmFugalMst" >
<div id="wrapperRpt" >
		<table id="grd4MQtmTlFrmFugalMst" ></table>
		<div id="grd4MQtmTlFrmFugalMstpager1"></div>
</div>
<input type="hidden" id="hdnfromValue" name="hdnfromValue" value="${requestScope.from}"/>
</form> 