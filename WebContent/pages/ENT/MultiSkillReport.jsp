	
<script type="text/javascript">

jQuery(document).ready(function(){//alert(12);
     jQuery('#submitForm').val('frmskillReport');
	 var url = jQuery('#hiddenUrl').val();
	 viewGrid("MultiskillRep_input.postass","q=2");

});
function viewGrid(url,filterString)
{
		var tableCaption = "SkillReport";
		processGridnew(url,filterString,"SkillReportGrid","pager",tableCaption,"","","","","");
	    return true;
} 
</script>	
<form name="frmskillReport" id="frmskillReport" >
<div id="wrapperRpt"  >
<table id="SkillReportGrid"  ></table>
		<div id="pager">
		</div>
</div>
</form>