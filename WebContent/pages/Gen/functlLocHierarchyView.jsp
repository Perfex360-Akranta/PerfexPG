<script type="text/javascript" >
jQuery(document).ready(function (){
	jQuery('#btnShowFuntLocHier').click(function(){
		var divId = jQuery("#hdnFuntViewDiv").val();
		var url = jQuery("#hdnFuntViewUrl").val();	
		fillFunctionalLocationHierarchy(divId,url);
		
	});
	//var formId = hdnCurrentFormId;
	var divId = jQuery("#hdnFuntViewDiv").val();
	
	jQuery("#loadfunlocnviewPage").html('<div id="'+divId +'" /> ' );

});
</script>
<input id="btnShowFuntLocHier" type="button" /> 
<input type="hidden" id="hdnFuntViewDiv" value="${requestScope.functLcnHierViewDivId}"/>
<input type="hidden" id="hdnFuntViewUrl" value="${requestScope.functLcnHierViewUrl}"/>
<input type="hidden" id="hdnCurrentFormId" value="${requestScope.currentFormId}"/>	
<div id="loadfunlocnviewPage" ></div> 