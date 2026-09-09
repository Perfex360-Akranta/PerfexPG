<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			
			jQuery("#list").jqGrid({
				datatype: "json",
				url:"",
				colNames:['Location',
						  'Equipment',
						  'OrderNo',
						  'EntryDate',
						  'MaintType',
						  'Assembly',
						  'Problem',
						  'Shift',
						  'DocNo',
						  'WorkStartDate',
						  'WorkEndDate',
						  'DownTime',
						  'Cost',
						  'RootcauseCategory',
						  'RootCause',
						  'CounterMeasure',
						  'Supervisor'],
				colModel:[ {name:'Location',index:'Location',editable:true,width:100},
						   {name:'Equipment',index:'Equipment',editable:true,width:100},
						   {name:'OrderNo',index:'OrderNo',editable:true,width:100},
						   {name:'EntryDate',index:'EntryDate',editable:true,width:100},
						   {name:'MaintType',index:'MaintType',editable:true,width:100},
						   {name:'Assembly',index:'Assembly',editable:true,width:100},
						   {name:'Problem',index:'Problem',editable:true,width:100},
						   {name:'Shift',index:'Shift',editable:true,width:100},
						   {name:'DocNo',index:'DocNo',editable:true,width:100},
						   {name:'WorkStartDate',index:'WorkStartDate',editable:true,width:100},
						   {name:'WorkEndDate',index:'WorkEndDate',editable:true,width:100},
						   {name:'DownTime',index:'',editable:true,width:100},
						   {name:'Cost',index:'Cost',editable:true,width:100},
						   {name:'RootcauseCategory',index:'RootcauseCategory',editable:true,width:100},
						   {name:'RootCause',index:'RootCause',editable:true,width:100},
						   {name:'CounterMeasure',index:'CounterMeasure',editable:true,width:100},
						   {name:'Supervisor',index:'Supervisor',editable:true,width:100}
						 ],
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Eqipment History',
								width:850,
								height:350,
								//loadonce: true,
								
								footerrow: false,
								userDataOnFooter: false,
								gridComplete: function()
								{ 
									//jQuery("#list").jqGrid("setLabel","compName","New",{"text-align":"right"});
								}
							});
						});
		function viewGrid(actionPart,dataString)
		{
			jQuery("#list").setGridParam({url:actionPart+dataString,dataType: "json" }).trigger('reloadGrid');
		}
</script>
<div class="main-header">Equipment history</div>
<br/><br/>
<div class="cntborder"style="width: 930px;">
	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>