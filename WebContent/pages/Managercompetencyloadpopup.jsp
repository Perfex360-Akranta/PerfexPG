<script type="text/javascript">

jQuery(document).ready(function(){

	processGridnew("ManagerCompetency_input.mcf","q=2","Managercompfrmtloadgrid","Managercompfrmtloadpager","","","","managerOncompleteLoad");

    //hideJqGridRow(Managercompfrmtloadgrid, 1);
	
	jQuery("#txtmanager").attr("disabled", "disabled");

});

function managerOncompleteLoad() {

	 hideJqGridRow("Managercompfrmtloadgrid", 1);
	 var row = jQuery("#Managercompfrmtloadgrid").jqGrid('getDataIDs');
	 var cm = jQuery("#Managercompfrmtloadgrid").jqGrid("getGridParam", "colModel");
	 
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=1;j<cm.length;j++)
    	 {
	    	 var zeroVal = jQuery("#Managercompfrmtloadgrid").jqGrid('getCell',row[i],cm[j].name);	
	    	 //alert("zeroVal::Today"+zeroVal);
			//alert(row[i]+"cm[j].name"+cm[j].name);
			//var cVal=cm[j].name.substring(0);
			//cVal=parseInt(cVal)+1;
			//if(i==1){
				//cm[j].name = cm[j].name.replace(cm[j].name,cVal+"col"+cVal);
			//alert("after  :"+cm[j].name);
			//}
		   if(zeroVal=='1'){
			  
			jQuery("#Managercompfrmtloadgrid").jqGrid('setCell',row[i],cm[j].name.trim(),"&#10003;");
			
			//alert("cm[j].sdsd"+cm[j].name);
			// tick(row[i],'Managercompfrmtloadgrid',cm[j].name);  2col2	1col5	2col10	4col16
	 	 }
    	 }
	 }
	 
	     
	      //hideJqGridRow("Managercompfrmtloadgrid", 3);
	      //hideJqGridRow("Managercompfrmtloadgrid", 5);
	 
}


</script>

<form action=" " method="post" id="frmloadmanagercompfrmt" name="frmloadmanagercompfrmt">
<div style="margin-left:20px;">
<div style="margin-top:10px;">
<label>Name of Manager</label>
<span style="padding-left:10px;">
    <input type="text" id="txtmanager" name="txtmanager" clear="false" class="easyui-text"  style="width:100px;" value="SANJAY ROHIT" >
</span>
</div>

<div style="float:left;">
		<table id='Managercompfrmtloadgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='Managercompfrmtloadpager'></div>
</div>
</div>
<input type="hidden" id="colNo" value=""/>
</form>