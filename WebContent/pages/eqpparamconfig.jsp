<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
				jQuery("#list1").jqGrid({
						datatype: "local",
						colNames:[ 'Parameter','Option','Selection/Drop down Text','Drop down Save(Single char)','Is Mandatory?','Remove'],
						colModel:[ {name:'parameter',index:'parameter',editable:false, width:270},
						           //{name:'option',index:'option', width:250, editable: true,edittype:"select",formatoptions: {disabled : false},editoptions:{value:"TXT:Text;NUM:Number;DT:Date;SEL:Selection(2 Parameters);DDL:Drop Down List"}},
						            {name:'option',index:'option',editable:false, width:170},
								   {name:'selordd',index:'selordd',editable:false, width:150},
								   {name:'ddsave',index:'ddsave',editable:false, width:150},
								  // {name:'isMand',index:'isMand', width:100, editable: true,edittype:"select",formatoptions: {disabled : false},editoptions:{value:"Y:Yes;N:No"}},
								   {name:'isMand',index:'isMand',editable:false, width:100},
								   {name:'remove',index:'remove',editable:false, width:70},
								 ],
   							    data:[
									  {id:"1", kaizenDate:"2007-10-01",parameter:"KW", theme:"note", closed:true},
									  {id:"2", kaizenDate:"2007-10-02",parameter:"CLAMPING SYSTEM", theme:"note2",closed:false},
									  {id:"3", kaizenDate:"2007-09-01",parameter:"AIR PRESSURE", theme:"note3",closed:false},
									  {id:"4", kaizenDate:"2007-10-04",parameter:"ROTOR SPECIFICATION", theme:"note4",closed:true },
									  {id:"5", kaizenDate:"2007-10-31",parameter:"TECHNICAL SPECIFICATION", theme:"note5",closed:false},
									  {id:"6", kaizenDate:"2007-10-01",parameter:"BELT DRIVE", theme:"note", closed:true},
									  {id:"7", kaizenDate:"2007-10-02",parameter:"MOTOR KW PRM", theme:"note2", closed:false},
									  {id:"8", kaizenDate:"2007-09-01",parameter:"PROCESS PARAMETER", theme:"note3",closed:false},
									  {id:"9", kaizenDate:"2007-10-04",parameter:"test4", theme:"note4", closed:true},
									  {id:"10",kaizenDate:"2007-10-31",parameter:"REMARKS",theme:"note5",closed:false}
									
								  ],		  
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Select activities and click "Copy Theme(s) Button',
								width:990,
								height:250,
								loadonce: true,
								gridComplete: function()
								{ 
									var ids = jQuery("#list1").jqGrid('getDataIDs'); 
									for(var i=0;i < ids.length;i++)
										{ 
											var cl = ids[i]; 
											be = "<input style='' class='easyui-button' type='button' value='X'/>";
											opt ="<select  style='' required='true'><option value='TXT'> Text</option><option value='NUM'> Number</option><option value='DT'> Date</option><option value='SEL'> Selection(2 Parameters)</option><option value='DDL'> Drop Down List</option></select>";
											mand = "<select  style='width:100px;' required='true'><option value='Y'> Yes</option><option value='N'> No</option></select>";
											//onclick=\"jQuery('#rowed2').saveRow('"+cl+"');\"
										
											jQuery("#list1").jqGrid('setRowData',ids[i],{remove:be,option:opt,isMand:mand});
										 }
								}
							});
						});
		
</script>


<br/><br/>
<div id="wrapper">
<div class="main-cntborder" style="padding:10px;">
	 <div style=""> 
			 <table id="list1" style="width:100%"><tr><td/></tr></table>
			 <div id="pager1"></div>
	</div>
	<div class="clearfix"></div>
</div>
</div>