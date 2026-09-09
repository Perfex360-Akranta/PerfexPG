<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				setLoadFormCallBackFrmId("frmAccessreviewrpt");
				invokeAfterLoadFormCallBack();
				var url = jQuery('#hiddenUrl').val();
			//	viewGrid(url,"&q=2");
				
			});

	function frmAccessreviewrpt_afterLoadCallBack(){
		toggleCommonFilter();
		}	

	function viewGrid(url,filterString)
	{ 
		if( validateFilterSelection(filterString))
		{ 
			
		
			processGridnew("AccessRpt_input.arr",filterString,"accessreviewGrid","pager");
  //processGridnew(url,filterString,"Gridmailreport", "pager", "", "doubleClick");
  return true;
	}
return false;	
	}
	
  function validateFilterSelection(filterString){
    return  true;
}	

   
	

</script>

<form  id="frmAccessreviewrpt">
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >

<table id="accessreviewGrid" ></table>
<div id="pager"></div>

</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>	
</form>
	



