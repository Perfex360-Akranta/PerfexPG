<script type="text/javascript">

jQuery(document).ready(function(){

 
        jQuery('#submitForm').val('frmQuikLink');
         var url = jQuery('#hiddenUrl').val();
          viewGrid("Quick_input.ap","q=2");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("");
	});
});

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "Quick";
		
		processGridnew(url,filterString,"QuickGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	
} 

	
</script>


<form name="Quick" id="Quick" >
<div id="wrapperRpt"  >

<table id="QuickGrid"  ></table>
		<div id="pager"></div>
		</div>
 <input type="hidden" id="mode"/>
  
</form>