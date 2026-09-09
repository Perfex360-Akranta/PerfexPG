

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
	 	 
	jQuery("#EquipmentDefectGrid").jqGrid({
		url:'',
		datatype: "local",
		data:[
			  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
			  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false}
			  	
		  ],
		  colNames:[ 'Select','Phenomena'],
			colModel:[ {name:'Select',index:'Select',editable:false, width:200},
			           {name:'Phenomena',index:'Phenomena',editable:false, width:600},				  	           		
			           			   
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
		height:190,
		loadonce: true
	});	

	jQuery( "#close" ).click(function() 
	{		
		jQuery( "#find" ).hide();
	});	

	jQuery( "#close1" ).click(function() 
	{		
		jQuery( "#SubForm" ).hide();
		jQuery( "#EquipDefectMain").show();
		 
	});	
				
				
</script>
				

 				
<div id="SubForm">
<div class="sub-cntborder" >
<table width=75% style="FONT-WEIGHT: bold;">
<tr style=" height : 49px;">
<td valign="top" style=" width : 819px;">


<div style="float: left;"  >

<div id="find" class="sub-cntborder" style="padding:10px 10px 10px 10px;">
Find
<input id="Find" type="text" class="easyui-text" name="Find" value="" style="width: 211px; height : 21px;" ;>
<input type="button" name="Find"  id="find" value="Find" class="easyui-button"/>
<input type="button" name="Close"  id="close" value="Close" class="easyui-button"/>
</div>

</div>
<div style="float: right;">
<input type="button" name="Find"  id="find" value="Find" class="easyui-button"/>
<input type="button" name="Filter"  id="filter" value="Filter" class="easyui-button"/>
<input type="button" name="Add new Phenomena"  id="add" value="Add new Phenomena" class="easyui-button"/>
<input type="button" name="Refresh"  id="refresh" value="Refresh" class="easyui-button"/>
<input type="button" name="Close"  id="close1" value="Close" class="easyui-button"/>
</div>
	</td>
</tr>
</table>

<table id="EquipmentDefectGrid" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
<div></div>
</div>
