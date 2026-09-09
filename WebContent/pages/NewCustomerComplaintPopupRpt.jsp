<script type="text/javascript">
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var comm=jQuery('hdncomplaintno').val();
	processGridnew(url,"q=","grdCustComPopup","pagar","","","","");
});

</script>
<form id="frmCustComplaintPopup">
<div style="margin-left:2%;">
	<table id="grdCustComPopup" style="width:100%;"><tr><td/></tr></table>
	<div id="pagar"></div>
</div>
<input type="hidden" id="hdncomplaintno" name="hdncomplaintno" value="${requestScope.complaintNo}" />
</form>
