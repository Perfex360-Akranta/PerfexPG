<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	
	  setLoadFormCallBackFrmId('frmJHComplaince');
	  invokeAfterLoadFormCallBack();
	//setLoadFormCallBackFrmId("frmJHComplaince");
	//loadCommonFilter();
	viewGrid(actionPart,"?"); 
	//viewGrid(url,"");
  // numericTextBox('txtactual');//for number only validation

});

function frmJHComplaince_afterLoadCallBack(){

	toggleCommonFilter(false);
	viewGrid(actionPart,"?"); 
}
function viewGrid(url,filterString)
{
	if (filterString=="?")
		processGridnew(url,filterString,"ppGrid","pager",tableCaption,"doubleClickGrid","","ppGrid_loadComplete");
	else if( validateFilterSelection(filterString))
	{
		var tableCaption = "Failure Phenomena Report";
		
		processGridnew(url,filterString,"ppGrid","pager",tableCaption,"doubleClickGrid","","ppGrid_loadComplete");
	
		return true;
	}
	return false;	
}
function ppGrid_loadComplete(){
	//alert("hideJqGridRow");
	//alert("hideJqGridRow" +hideJqGridRow);
	//hideJqGridRow("ppGrid","ppGridghead_0");
}
function validateFilterSelection(filterString){
	
	if( filterString.length != 0)
	{		
		
		
		 if( ! checkFilterValueExist(filterString, "cmbSectid"))
		{
			alert("Select DMT");
			return false;
		}
		
		/*if( ! checkFilterValueExist(filterString, "cmbMchid"))
		{
			alert("SELECT  MACHINE");
			return false;cmbCelliddtFromDate=&dtToDate=
		}*/
	/*else if( ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("ENTER  FROM DATE");
			return false;
		}

		else if( ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("ENTER TO DATE");
			return false;
		}
*/
		return true;
	}
			
}		
	





</script>
<form id = "frmJHComplaince" id = "frmJHComplaince">
<div id="wrapperRpt">
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<div class="clear"></div>
<table id="ppGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnmchId" name="hdnmchId" value=""/>
	</div>
</form>