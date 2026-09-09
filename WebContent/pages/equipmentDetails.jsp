<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">

jQuery(document).ready(function(){

//	var url = jQuery('#hiddenUrl').val();
	var datastr = "eqpId="+jQuery('#hdnEquipno').val();
	//var url=jQuery("#hiddenrl").val();
	var url=jQuery("#hiddenUrl").val();
	processGridnew(url,datastr,"grdEquipDetails","pgrEquipDetails");
	
});

</script>

<div style="margin-left:2%;">
	<table id="grdEquipDetails" style="width:100%;"><tr><td/></tr></table>
	<div id="pgrEquipDetails"></div>
	<input type="hidden" id="hdnEquipno" value="${requestScope.eqpId}"/>
</div>
