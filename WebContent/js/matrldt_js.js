

function materialdatasheetbind(){
	
	jQuery("#main_mtrldtsht_cntnt_dv").load('materialdatanew_input.mtrdt', function(response, status, xhr) {
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  }
		});
	
}


jQuery.noConflict();
						jQuery(document).ready(function(){													
						jQuery("#grid1").jqGrid({
								datatype: "local",
								colNames:[ 'SL NO','PARAMETERS','ESTIMATE','ACTUAL'],
								colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Material Safty Data Sheet',
								width:945,
								height:300,
								loadonce: true							
							});
						
						
						jQuery("#grid2").jqGrid({
							datatype: "local",
							colNames:[ 'GRADE NAME','EMPLOYEE NO','EMPLOYEE NAME','MINUTES','COST','TOTAL COST'],
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
							pager: '#pager2', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Physical Properties',
							width:945,
							height:200,
							loadonce: true						
						});
						
						
						jQuery("#grid3").jqGrid({
							datatype: "local",
							colNames:[ 'DATE','GRADE NAME','EMPLOEE CODE NAME','ACTIVITY','NORMAL MINS','CALL OUT MINS','OT MIN','NORMAL COST','OT COST'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							pager: '#pager3', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Precautions to use',
							width:945,
							height:200,
							loadonce: true
													
						});
						
						
						
						jQuery("#grid4").jqGrid({
							datatype: "local",
							colNames:[ 'GRADE NAME','EMP NO','EMP NAME','NORMAL MINUTES','CALL OUT MINUTES','OT MINUTES','NORMAL','CALL UOT','OT','TOTAL COST'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
									   {name:'aumANSWER',index:'ANSWER',editable:false, width:250},
									   {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							pager: '#pager4', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'grid4',
							width:945,
							height:200,
							loadonce: true								
						});
											
						
						jQuery("#grid5").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							caption:'Contractor Cost Estimation',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						jQuery("#grid6").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							pager: '#pager6', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'grid6',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						
						jQuery("#grid7").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							caption:'grid7',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						jQuery("#grid8").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							pager: '#pager8', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'grid8',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						
						jQuery("#grid9").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							caption:'grid9',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						
						jQuery("#grid10").jqGrid({
							datatype: "local",
							colNames:[ 'WHY WHY','ANSWER'],
							colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
							pager: '#pager10', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'grid10',
							width:945,
							height:200,
							loadonce: true								
						});
						
						
						
						
						});