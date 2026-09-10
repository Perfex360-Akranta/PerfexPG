<script>
jQuery(document).ready(	function(){
	initialiseForm('frmBDVSPMVSABNReport');
	var url = jQuery("#hiddenUrl").val();
	//alert(url);
	viewGrid(url,"q=2");
	jQuery('#btnGraph').click(function(){
		
		var rowid = jQuery("#BDVSPMVSABNGrid").jqGrid('getGridParam','selrow');
		if(checkForZeroes("BDVSPMVSABNGrid",rowid,3)){
		var url = "chartabnvsbdvspm.pmbd?rowid="+rowid; 		
		showGraphData(url);
		}
		else 
			alert("No Record Found");
	});
});
function viewGrid(url,filterString){
	processGridnew(url, filterString,"BDVSPMVSABNGrid", "BDVSPMVSABNpager", "", "", "","GridOnCompleteload");
}
function GridOnCompleteload() {

	 var row = jQuery("#BDVSPMVSABNGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#BDVSPMVSABNGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		
		 for(var j=0;j<cm.length;j++)
   		 {  
	   		   

			  if(row[i]=='9'  ){
				 
					jQuery("#BDVSPMVSABNGrid").setCell(row[i], cm[j].name.trim(), "",{'background-color':'#FEC488'});
	   	 	   }
	    	 
	    	 
			   
			   	
   		 }
	 }
	 
}

</script>
 
<form name="frmBDVSPMVSABNReport" id="frmBDVSPMVSABNReport" action=" " method="post">
<div id='wrapperRpt'>
<div style="margin-top: -28px">
<input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="width:56px;"/>
</div>
	<table id="BDVSPMVSABNGrid" ><tr><td></td></tr></table>
	<div id='BDVSPMVSABNpager'></div>
</div>

</form>
