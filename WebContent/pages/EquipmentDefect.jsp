

<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>	 
jQuery('#cmbCostCen').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
	
}); 
 	 
jQuery('#cmbSect').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
	
}); 
	 	 

	 	 
jQuery('#cmbFact').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
	
}); 
		 
jQuery('#cmbCell').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
	
}); 

jQuery("#EquipmentDefect").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false}
		  	
	  ],
	  colNames:[ 'Equipment No','Equipment Name','Phenomena Count'],
		colModel:[ {name:'Equipment No',index:'Equipment No',editable:false, width:160},
		           {name:'Equipment Name',index:'Equipment Name',editable:false, width:500},					  
				   {name:'Phenomena Count',index:'Phenomena Count',editable:false, width:160},			           		
		           			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'',
	width:870,
	height:350,
	loadonce: true,

	ondblClickRow: function(rowid) {		
			
		    jQuery("#EquipmentDefect1").load("EquipmentDefectmaingrid_view.EqpDefect", function(response, status, xhr) {
				  
				  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
				  else if(status == "success")
				  {
					  jQuery("#EquipDefectMain").hide();
					  jQuery("#EquipmentDefect").show();
				  }
				  
			});
		 }
});
	 	 
/*jQuery("#EquipDefectMain").load("EquipmentDefectmaingrid_view.EqpDefect", function(response, status, xhr) {
	alert(status);
			  if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			    
			  }
			  else if(Status=="success")
				  {
				  
				  //jQuery("#MachineAreaGrid").hide();
				  
				  }
			});
	*/

	
				</script>
				
<div id="edp" class="main-cntborder" >
				

<table width=100%  align="center">
<tr >
<td valign="top" width=33% style="padding-left: 210px;">

<div   >Factory:</div>
<div class="easyui-paddingbfpx">
<input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:250%;" value=""  >
	</div>
	
	<div >Section:</div>
<div class="easyui-paddingbfpx">

<input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:250%;" value=""  >
</div>

	
	
	
</td>

<td valign="top" width=33% style="padding-left:180px; ">

<div >Cost Center:</div>
<div class="easyui-paddingbfpx">
<input id="cmbCostCen" name="cmbCostCen" class="easyui-combobox"  style="width:250%;" value=""  >
	</div>
	
	<div class="mandatory-lbl">Cell: </div>
<div class="easyui-paddingbfpx">
<input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:250%;" value=""  >
</div>
	
</td>
<td width="33%" valign="bottom" style="padding-right: 150px;">

<div style="padding-left:10%;margin-top:10px;padding-bottom:5px;">
<input type="button" value="View" style=" height : 21px;" Id="view" class="easyui-button">
</div>

</td>
</tr>
</table>





<div id="EquipDefectMain">
<div class="notes " style="margin-bottom:10px;padding-top:5px;padding-left:100px;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Double Click on the shaded area to view the Activities</div>
<table id="EquipmentDefect" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
<div id=EquipmentDefect1></div>
</div>

