<script type="text/javascript">

jQuery(document).ready(function(){

	
        jQuery('#submitForm').val('frmexternal');
         var url = jQuery('#hiddenUrl').val();

              processGridnew("External_input.ext","q=2","ExternalGrid","pager"," ","doubleClickGrid","","loadComplete","","");
		processGridnew("Externals_input.ext","q=2","ExternalsGrid","pager"," ","doubleClickGrid","","loadComplete","","");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("");
	});
});

	

	
	
</script>


<form name="External" id="External" >
<div id="wrapperRpt" >
<label>Outer Form</label>
<div style="float:left;">
<table id="ExternalGrid"  ></table>
		<div id="pager"></div>
		</div>
		<div style="float:left;padding-top: 10px;" >		
<table id="ExternalsGrid"  ></table>
		<div id="pager"></div>
	</div>
		</div>
		


 <input type="hidden" id="mode"/>
 </form>
  