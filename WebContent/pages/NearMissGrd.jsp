
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmNearMissCase');
	jQuery('#submitForm').val('frmNearMissCase');
	processGridnew("NearMissReport_input.nmr","?q=1","NearMissCase","NearMissPager"," ", "doubleclick");

	var nearmiss= jQuery("#nearmiss").val();
	if(nearmiss== "approval" && nearmiss!=null){
		jQuery ("#btnNew").hide();
	}
	else{
			jQuery ("#btnNew").click(function(){
			navigateToNextForm("Nearmiss_input.nmr?grid=false&clearfrom=true");
	});	
	}

	
});

</script>





<form id="frmNearMissCase" name="frmNearMissCase">
  <div id="wrapper" >
  

			<div >
				<table id="NearMissCase" ></table> 
			</div>
			<div id="NearMissPager"></div>
  
  
  
  
  </div>
  </form>