<script>
	jQuery(document).ready(function(){
		 
		processGridnew("tenstepspg2_input.tsdi","&q=2" ,"frstGrd","frstPager","","hmDblClkFunction","","hmComplete","")
	});
	function hmDblClkFunction(id){
		var rowData = jQuery("#frstGrd").jqGrid('getRowData',id);
		var processid= rowData.processid;
		var flid= rowData.flid;
		var ctqid= rowData.ctq;
		var Preparedbyid= rowData.Preparedbyid;
		var Defectid= rowData.Defectid;
		var datStr= "&processid="+processid+"&tenStpFlid="+flid+"&ctqid="+ctqid+"&Preparedbyid="+Preparedbyid+"&Defectid="+Defectid;
		  		
		 navigateToNextForm("tenstepQaMatrix_input.tsdi?datStr="+datStr );
		//navigateToNextForm("tenstepsLink_input.tsdi" );
	}
	function hmComplete(){
		 var row = jQuery("#frstGrd").jqGrid('getDataIDs');
			
		 var cm = jQuery("#frstGrd").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++)
		 {
			 for(var j=0;j<cm.length;j++)
	     	 {
				 var okVal = jQuery("#frstGrd").jqGrid('getCell',row[i],cm[j].name);	 
				 
	     	 }
		 }
		 	
	}
</script>
<div id='wrapperRpt'>
	<table id="frstGrd">
		<tr><td></td></tr>
	</table>
	<div id="frstPager"></div>
</div>