<script type="text/javascript">

jQuery(document).ready(function(){

	initialiseForm("frmTraining");
        jQuery('#submitForm').val('frmTarining');
         var url = jQuery('#hiddenUrl').val();

       viewGrid("Kpiv_input.kpiv","q=2");
	   jQuery("#btnnew").click(function(){
		navigateToNextForm(" ");
	});
});

function viewGrid(url,filterString)
{
        var tableCaption = "Training";
	    processGridnew(url,filterString,"AttenceGrid","pager",tableCaption,"","","","","");
	    return true;
} 
</script>

<form name="Attence" id="Attence" >
<div id="wrapperRpt"  >

<input type="button" class="easyui-button" id="btnnew"	name="btnnew" value="New" style="height: 25px; width : 102px;"/>
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
<table id="AttenceGrid"  ></table>
		<div id="pager"></div>
		</div>
</div>
 <input type="hidden" id="mode"/>
  
</form>