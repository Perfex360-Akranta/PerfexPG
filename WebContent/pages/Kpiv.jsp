<script type="text/javascript">

jQuery(document).ready(function(){

	
        jQuery('#submitForm').val('Kpiv');
         var url = jQuery('#hiddenUrl').val();

         var btnName = jQuery("#hdnBtnName").val();
 		jQuery("#btnViewTemplate").val(btnName);
 		jQuery("#btnViewTemplate").click(function()
 				{
 			//alert("Read From File");
 			
 			processAjaxCalls("openFile.file?fileName=KPIV.xlsx", "", "", "", "", "new");					
 		});
       viewGrid("Kpiv_input.kpiv","q=2");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("");
	});
});

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "Sop";
		
		processGridnew(url,filterString,"KpivGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	
} 
	function loadComplete(){
		
		var row = jQuery("#KpivGrid").jqGrid('getDataIDs');
		//var color="#c1c1c1";
		 var cm = jQuery("#KpivGrid").jqGrid("getGridParam", "colModel");
		
		 for(var i=0;i<row.length;i++)
		 {
			 for(var j=2;j<cm.length;j++)
	     	 {
		     	 var zeroVal = jQuery("#KpivGrid").jqGrid('getCell',row[i],cm[j].name);
		     	 // alert("zeroVal"+zeroVal);
				  if(zeroVal ==" "){
					
				  		jQuery("#KpivGrid").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#c1c1c1'});
				  }
				  
				 
		}
		 }
	}

	
</script>


<form name="Kpiv" id="Kpiv" >
<div id="wrapperRpt"  >

<!--<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/>-->
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;margin-top: -43px" >
<table id="KpivGrid"  ></table>
		<div id="pager"></div>
		</div>
</div>
 <input type="hidden" id="mode"/>
  <input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>