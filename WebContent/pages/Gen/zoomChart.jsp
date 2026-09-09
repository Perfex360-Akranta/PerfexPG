<script>
jQuery(document).ready(function(){ 
	jQuery("#graprZoom").html(" ");
	var divId = jQuery('#chrtDiv').val();
	var chrtDta = jQuery('#'+divId).html() ;
	var chrtDta_obj = JSON.parse(chrtDta);
	
	var charttitle = jQuery('#ChrtNewTitle').html(); 
	jQuery('#ChrtNewTitle').css('margin-top','1');
	 //var chrtDta_obj = JSON.parse(jQuery('#chrtObjData').val());
	  if("gauge"==chrtDta_obj.type)
		  drawGaugeChart('graprZoom' ,chrtDta_obj );
	  else
	 	drawChart(chrtDta_obj,"graprZoom",'Y','Y','','N','dashboard')	;
	// jQuery('#ChrtNewTitle').css('display','none');
	 jQuery('#grapZoom > .sub-header').hide();
	 jQuery("#dashTool").css("background","transparent");
	 jQuery("#grapZoom").css('background','transparent');
	 jQuery("#grapZoom").css('border','none');
	 jQuery("#graprZoom").css('margin-top','-9');
	 jQuery("#dataTableContainer_graprZoom").css('background','#fff');
	 jQuery("#dashTool").css("margin-top","-5"  );
	 jQuery(".btn_close").css('position','absolute');
	 jQuery(".btn_close").css('top','26');
	 jQuery(".btn_close").css('right','18');
	 jQuery(".btn_close").css('z-index','1');
	 
	jQuery("#graprZoom").append(jQuery("#grapContainerZoom").html() );
});
</script>
<div id="graprZoom">
		
		
	</div>

<input type="hidden" value="${requestScope.chrtdivId }" id="chrtDiv"/>