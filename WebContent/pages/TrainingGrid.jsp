
<script type="text/javascript">

jQuery(document).ready(function(){
	initialiseForm("frmTraining");
        jQuery('#submitForm').val('frmTarining');
        // var url = jQuery('#hiddenUrl').val();
        viewGrid("AttenceGrid_input.tatnd","q=2");
        jQuery("#btnnew").hide();
    	 jQuery("#btnnew").click(function(){
    		 navigateToNextForm("AttenceGrid_input.tatnd?filterButton=false");
    	   });
});

function viewGrid(url, filterString) {
	url="AttenceGrid_input.tatnd";
    processGridnew(url,filterString,"AttenceGrid","pager","","doubleClickTraining","","");
	return true;
}

function doubleClickTraining(id)
{	
  
	var rowData = jQuery("#AttenceGrid").jqGrid('getRowData',id);	
	var flid = rowData.FLID;
	 var keyId = rowData.PROGKEYID;
	 var batchId = rowData.BACHKEYID;
	 
	 var date=rowData.ASMM_EVALUATION_DATE;
	 var duration=rowData.PROG_MIN_DURATION;
	 var faculty=rowData.ASMM_FACULTY;
	 var assesdBY =rowData.ASSESDBY;
	 var assmDesc  = rowData.DESC;
	// alert("&keyid="+keyId+"&evaldate="+date+"&faculty="+faculty+"&Duration="+duration+"&assesdBY="+assesdBY+"&assmDesc="+assmDesc);
	var ds ="?&keyid="+keyId+"&flid="+flid+"&batchId="+batchId+"&evaldate="+date+"&faculty="+faculty+"&Duration="+duration+"&assesdBY="+assesdBY+"&assmDesc="+assmDesc;
	ds+="&filterButton=false";
	 navigateToNextForm("AttenceGrid_input.tatnd"+ds,"");
	}

</script>

<form name="Attence" id="Attence" >
<div id="wrapperRpt"  >

<input type="button" class="easyui-button" id="btnnew"	name="btnnew" value="New" style="height: 25px; width :70px;"/>
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
<table id="AttenceGrid"  ></table>
		<div id="pager"></div>
		</div>
</div>
 <input type="hidden" id="mode"/>
  
</form>