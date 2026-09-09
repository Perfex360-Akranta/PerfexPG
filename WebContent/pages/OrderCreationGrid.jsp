<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmOrderCreationGrid');
	jQuery('#submitForm').val('frmOrderCreationGrid');
	processGridnew("Order_input.ord","?q=1","OrderGrid","orderPager","OrderCreation", "doubleclick");
	var Cval= "No";
	jQuery('#btnOrdNew').click(function() {
		Cval = "Yes";
		navigateToNextForm('OrderCreation_input.ord'+'?q=1&CreateNew='+Cval,"Order Form");
	});
});
function doubleclick(id){
	var Cval= "No";
	navigateToNextForm('OrderCreation_input.ord'+'?q=1&CreateNew='+Cval,"Order Form");
	
}
</script>
<form id="frmOrderCreationGrid" name="frmOrderCreationGrid">
	<div id="wrapper" style="width:100%;">
		<div style="padding-left:0%;margin-top: -28px">
			<input type="button" class="easyui-button" value ="New" name="btnOrdNew" id="btnOrdNew" style="height:23px;margin-top:12px;"/>
		
		<span style="padding-left:10px;">Double Click on row to input/view details</span>
		</div>
		<div style="float:left;">
			<table id="OrderGrid" ></table> 
		</div>
		<div id="orderPager"></div> 
	</div>
	
</form>