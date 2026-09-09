<script type="text/javascript">

jQuery(document).ready(function(){

	
        jQuery('#submitForm').val('frmexternals');
         var url = jQuery('#hiddenUrl').val();

           processGridnew("Externals_input.exes","q=2","ExternalSevGrid","pager"," ","doubleClickGrid","","loadComplete","","");
		processGridnew("Externalservice_input.exes","q=2","ExternalserviceGrid","pager"," ","doubleClickGrid","","loadComplete","","");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("");
	});
});

	

	
	
</script>


<form name="Externals" id="Externals" >
<div id="wrapperRpt" >

<div style="float:left;">
<table id="ExternalSevGrid"  ></table>
		<div id="pager"></div>
		</div>
	
		<div style="float:left;padding-top: 10px;" >		
<table id="ExternalserviceGrid"  ></table>
		<div id="pager"></div>
	</div>
		</div>	
		


 <input type="hidden" id="mode"/>
 </form>
  