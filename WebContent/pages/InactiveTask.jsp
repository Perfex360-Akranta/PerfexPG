<script>
jQuery(document).ready(	function(){
	initialiseForm('frmInActive');
	var url = jQuery("#hiddenUrl").val();
	viewGrid(url,"q=2");
});
function viewGrid(url,filterString){
	processGridnew(url, filterString,"InActiveGrid", "pager", "", "", "","InActiveOnCompleteload");
}
function InActiveOnCompleteload() {

	 var row = jQuery("#InActiveGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#InActiveGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		
		
	    	 
			   if(row[i]=='13' || row[i]=='3' ||row[i]=='5'  ||row[i]=='11'  ||row[i]=='9' ){
					jQuery("#InActiveGrid").setCell(row[i], "INACTIVETASK", "");
	   	 	   }
			   else if(row[i]=='2' || row[i]=='7' ||row[i]=='10' || row[i]=='4' ){
					jQuery("#InActiveGrid").setCell(row[i], "INACTIVETASK", "");	
	   	 	   }
			   else {
					jQuery("#InActiveGrid").setCell(row[i],"INACTIVETASK", "");	
			   }	
  	 	 
	 }
	 
}


</script>
<form name="frmInActive" id="frmInActive" action=" " method="post">
<div id=''>

	<table id="InActiveGrid" ><tr><td></td></tr></table>
	<div id='pager'></div>
</div>
</form>
