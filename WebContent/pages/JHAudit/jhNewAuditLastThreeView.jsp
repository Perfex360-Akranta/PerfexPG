<script>
jQuery(document).ready(function(){
	 initialiseForm('frmAuditViewLst');
	var url = jQuery('#hiddenUrl').val();
	var data = url.split("?");
	url = data[0];
	var filterSreing = data[1];
	var urll="JhAuditLastThree_input.jhAuditItc";
	 processGridnew("JhAuditLastThree_input.jhAuditItc", filterSreing, "tblAuditViewLst", "repAuditViewPager", "", "", "","load_complete","selectRowFunction");	 
});

jQuery("#btnOkClose").click(function(){
	closePopUpDialoge("Last3View");	
});

function validateFilterSelection(filterString){
return  true;
}

</script>
<form id="frmAuditViewLst">
<table id="tblAuditViewLst"></table>
<div id="repAuditViewPager"></div>

 <div align="center"><input type="button" id="btnOkClose" value="close" class="easyui-button"></div> 
<!-- <input type="hidden" id="hdntxtAbnmRemarks" value=""/> -->
<input type="hidden" id="hdnAbnmRepeatedabn" value=""/>
<input type="hidden" id="hiddenUrl" value=""/>
   <input type="hidden" id="hdnjhamAudittype" name="hdnjhamAudittype" value="${requestScope.audittype}" />
<input type="hidden" id="hdnJhamAuditpillar" name="hdnJhamAuditpillar" value="${requestScope.pillar}"/>
<input type="hidden" id="hdnJhamFlid" name="hdnJhamFlid" value="${requestScope.jhaTlAuditmst.flid}" />
</form>