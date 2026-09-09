jQuery.noConflict();
						jQuery(document).ready(function(){
						jQuery("#grid1").jqGrid({
								datatype: "local",
								colNames:[ 'Product Affected / Running Details',''],
								colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
								           {name:'aumWHYWHY',index:'WHY',editable:false, width:100}
										  ],
   							    data:[
									  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
									  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false}									  							
								  ],
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,								
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Product Affected / Running',
								width:345,
								height:100,
								loadonce: true
							});
						
						
						
						
						jQuery("#grid2").jqGrid({
							datatype: "local",
							colNames:[ 'SL NO','PARAMETERS', 'COST'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100}
									   
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager2', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Estimation',
							width:345,
							height:100,
							loadonce: true
						});
						
						
						
						jQuery("#grid3").jqGrid({
							datatype: "local",
							colNames:[ 'PLANE DATA','TASK / ACTIVITY', 'RESPONSIBLITY', 'STATUS', 'ACTION TAKEN', 'TIME TAKEN'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager3', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Task Breakup and Related Activities',
							width:980,
							height:200,
							loadonce: true
						});
						
						
						
						
						jQuery("#grid4").jqGrid({
							datatype: "local",
							colNames:[ 'SL NO','TEMPORARY ACTION', 'WHEN', 'RELEASED DATE', 'RESPONSIBILITY', 'TARGET DATE','STATUS'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager4', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Temporary Action',
							width:1060,
							height:100,
							loadonce: true
						});
						
						
						
						jQuery("#grid5").jqGrid({
							datatype: "local",
							colNames:[ 'REASON','TIME(Mins)','REMARK'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager5', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Downtime Analysis',
							width:1080,
							height:200,
							loadonce: true
						});
						
						
						jQuery("#grid6").jqGrid({
							datatype: "local",
							colNames:[ 'DATE','SHIFT', 'DOWN TIME'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},									   
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager6', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Downtime Breakdown',
							width:900,
							height:200,
							loadonce: true
						});
						
						
						
						
						
						jQuery("#grid7").jqGrid({
							datatype: "local",
							colNames:[ 'ACTIVITY','FROM ON DATE','TO DATE','MINS','BY'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager7', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Summary',
							width:1080,
							height:200,
							loadonce: true
						});
						
						
						jQuery("#grid8").jqGrid({
							datatype: "local",
							colNames:[ 'PARAMETER','MINS', 'DESCRIPTION'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},									   
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager8', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Downtime Breakdown',
							width:1080,
							height:200,
							loadonce: true
						});
						
						
						
						
						
						
						
						
						jQuery("#grid9").jqGrid({
							datatype: "local",
							colNames:[ 'SL NO','PARAMETER','ESTIMATE','ACTUAL'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},								   
									   
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager9', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Cost Estimation and Actual Information',
							width:900,
							height:200,
							loadonce: true
						});
						
						
						jQuery("#grid11").jqGrid({
							datatype: "local",
							colNames:[ 'DATE','COST TYPE','EMPLOYEE CODE NAME','ACTIVITY','NORMAL MINS','CALL OUT MINS','OT MINS','NORMAL COST(USD)','CALL OUT COST(USD)'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager11', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Activities Done',
							width:1080,
							height:200,
							loadonce: true
						});
						
						
						
						jQuery("#grid12").jqGrid({
							datatype: "local",
							colNames:[ 'DATE','TIME','COMMUNICATION','DEPARTMENT','BY'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},									   
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									  ],
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
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,								
							pager: '#pager12', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Communication',
							width:1080,
							height:300,
							loadonce: true
						});
						
						
						
						});