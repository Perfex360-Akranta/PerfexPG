<script>
jQuery(document).ready(function(){	
			 
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
	 
	jQuery('#cmbEquip').combobox({
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
	
	jQuery("#JHCCLITS").load("getJHCLITMainTable.jhclit", function(response, status, xhr) {
//alert(status);
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		    
		  }
		  else if(Status=="success")
			  {
			  
			  jQuery("#MachineAreaGrid").hide();
			  
			  }
		});
});	
//Machine Area Div
jQuery(document).ready(function(){
	  jQuery("#SubEquipments").jqGrid({
			url:'',
			datatype: "local",
			data:[
				  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
				  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
				  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
				  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
				  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
				  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
				  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
				  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
				  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
				  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
			  ],
			  colNames:[ 'Tick','Name','Code'],
				colModel:[ {name:'Tick',index:'Tick',editable:false, width:300},		          
						   {name:'Name',index:'Name',editable:false, width:280},
						   {name:'Code',index:'Code',editable:false, width:250},
						     ],
			rowNum:50,
			rowList:[5,10,20],
			rownumbers: true,
			shrinkToFit:false,	
			pager: '#pager4', 
			sortname: 'id',
			viewrecords: true,
			sortorder: "asc", 
			caption:'',
			width:900,
			height:300,
			loadonce: true
			
		});
	  jQuery("#Close").click(function (){
			
		   jQuery("#JHCCLITS_machineArea").show();
			jQuery("#JHCCLITS").hide();
			jQuery("#MachineAreaGrid").hide();
			jQuery("#EquipmentArea").hide();
			jQuery("#page2").show();
				
		});
	  });
	 </script>

<div class="main-cntborder" style="height: 500px">
 
<table width="98%" align="center" >  
          <tr width="50%">
          <td style="padding-left: 100px;"> 
               	<div >Factory</div>
               	<div class="easyui-paddingbfpx">
               	<input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	<div ><label>Section</label></div>
               	<div class="easyui-paddingbfpx">
               	<input id="cmbSect" name="cmbSect" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	</td>
<td>
<div ><label>Cell</label></div>
               	<div class="easyui-paddingbfpx">
               	<input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	<div class="mndlbl"><label>Equipment</label></div>
               	<div class="easyui-paddingbfpx">
               	<input id="cmbEquip" name="cmbEquip" class="easyui-combobox"  style="width:255px;" value=""  >
	</div>
	
</td>
</tr>
</table>
<!-- Binding starts here-->
	
<div id="JHCCLITS">

</div>
<div id="JHCCLITS_machineArea">

</div>
<div id="EquipmentArea">

</div>
<div id="MachineAreaGrid" style="display: none;">

<div class="easyui-paddingbfpx"  style="padding-left: 40px;">
<input type="button" value="Find" id="Find" class="easyui-button"/> 
<input type="button" value="Filter" id="Filter" class="easyui-button"/></div>
<div style="padding-right: 120px;">
<table id="SubEquipments"  style="width:100%; " ></table>
			<div id="pager4"></div>
</div>
<div class="easyui-paddingbfpx" align="right" style="padding-right: 180px; padding-top: 5px;">
<input type="button" value="New" id="New" class="easyui-button"/> 
<input type="button" value="Save" id="Save" class="easyui-button"/> 
<input type="button" value="Close" id="Close" class="easyui-button"/>  </div>

</div>
</div>