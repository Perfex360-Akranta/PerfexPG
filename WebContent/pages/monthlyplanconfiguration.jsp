<script type="text/javascript">

jQuery(document).ready(function(){

	processGridnew("monplanconsolidate_input.mpc","q=2","monplncongftngrid","monplncongftnpager","","","","monplanconfigurationOncompleteLoad");

});

function monplanconfigurationOncompleteLoad() {
	
	 var row = jQuery("#monplncongftngrid").jqGrid('getDataIDs');
	 var cm = jQuery("#monplncongftngrid").jqGrid("getGridParam", "colModel");
	 //alert("cm"+cm.name);
	 for(var i=0;i<row.length;i++)
	 {  
		  var w1 = jQuery("#monplncongftngrid").jqGrid('getCell',row[i],"W1col2");
		  var w2 = jQuery("#monplncongftngrid").jqGrid('getCell',row[i],"W2col3");
		  var w3 = jQuery("#monplncongftngrid").jqGrid('getCell',row[i],"W3col4");
		  var w4 = jQuery("#monplncongftngrid").jqGrid('getCell',row[i],"W4col5");
		  

		// jQuery("#Tempgrid").jqGrid('setCell',row[i],"Equipmentcol0", rn,{'background-color':'#94E031'});  W2col3  W3col4 W4col5
		jQuery("#monplncongftngrid").jqGrid('setCell',row[i],"W1col2", w1,{'background-color':'#ff8040'});
		jQuery("#monplncongftngrid").jqGrid('setCell',row[i],"W2col3", w2,{'background-color':'#ff8040'});
		jQuery("#monplncongftngrid").jqGrid('setCell',row[i],"W3col4", w3,{'background-color':'#ff8040'});
		jQuery("#monplncongftngrid").jqGrid('setCell',row[i],"W4col5", w4,{'background-color':'#ff8040'});
		   
		 for(var j=1;j<cm.length;j++)
   	 {     
		   	 
	    	 var zeroVal = jQuery("#monplncongftngrid").jqGrid('getCell',row[i],cm[j].name);	
	    	
			
		   if(zeroVal=='C'){
			  
			jQuery("#monplncongftngrid").jqGrid('setCell',row[i],cm[j].name.trim(),"&#10003;");
			 
			
	 	 } 
   	 }
	 }
	 
	     
	      //hideJqGridRow("Managercompfrmtloadgrid", 3);
	      //hideJqGridRow("Managercompfrmtloadgrid", 5);
	 
}

 
</script>


<form action=" " method="post" id="frmmonplnconfiguration" name="frmmonplnconfiguration">
<div id="wrapperRpt">
<div style="margin-top:20px;margin-left:40px;">
		<table id='monplncongftngrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='monplncongftnpager'></div>
</div>
</div>
</form>