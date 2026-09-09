<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<script type="text/javascript">
						jQuery.noConflict();
						jQuery(document).ready(function(){	
						jQuery("#irmlist").jqGrid({
							datatype: "local",
							colNames:[ 'Name','MSDS No','Link'],
							colModel:[ {name:'name',index:'name',editable:false, width:200},
							           {name:'msdsno',index:'msdsno',editable:false, width:200},
							           {name:'link',index:'link',editable:false, width:200},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#irmpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:850,
							height:100,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#pdlist").jqGrid({
							datatype: "local",
							colNames:[ 'Contractor','Emp Code','Emp Name','Age','Insurance No','Length of Service in Organisation','Nature(Injury illness Env)','Nature(Others)'],
							colModel:[ {name:'contractor',index:'contractor',editable:false, width:90},
							           {name:'empcode',index:'empcode',editable:false, width:90},
							           {name:'empName',index:'empName',editable:false, width:150},
							           {name:'empAge',index:'empAge',editable:false, width:90},
							           {name:'insNo',index:'insNo',editable:false, width:90},
							           {name:'losio',index:'losio',editable:false, width:90},
							           {name:'natureinj',index:'natureinj',editable:false, width:90},
							           {name:'nature',index:'nature',editable:false, width:90},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#pdpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Personnel Details',
							width:850,
							height:100,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#permitlist").jqGrid({
							datatype: "local",
							colNames:[ 'Permit Type','Yes/No'],
							colModel:[ {name:'permittype',index:'permittype',editable:false, width:250},
							           {name:'my_checkbox',index:'my_checkbox', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#permitpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Was Permit Taken(Permit Type)',
							width:350,
							height:170,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#risklist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Evaluation of Risk if not corrected','Select','Severity Potential(as per risk register)','Select','Probability of occurence'],
							colModel:[ {name:'my_checkbox',index:'my_checkbox', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
										{name:'evalofrisk',index:'evalofrisk',editable:false, width:220},
										{name:'my_checkbox2',index:'my_checkbox2', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
										{name:'severity',index:'severity',editable:false, width:220},
										{name:'my_checkbox3',index:'my_checkbox3', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
										{name:'proboccrnce',index:'proboccrnce',editable:false, width:220},
							           
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#riskpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:1050,
							height:130,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#ssactnlist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Substandard Action'],
							colModel:[ {name:'my_checkboxactn',index:'my_checkboxactn', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'subactn',index:'subactn',editable:false, width:350},
							           
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#ssactnpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:480,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#sscondnlist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Substandard Condition'],
							colModel:[ {name:'my_checkboxcondn',index:'my_checkboxcondn', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'subcondn',index:'subcondn',editable:false, width:350},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#sscondnpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:480,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#pflist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Personal Factors'],
							colModel:[ {name:'my_checkboxpf',index:'my_checkboxpf', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'persfactors',index:'persfactors',editable:false, width:190},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#pfpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:260,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#jflist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Job Factors'],
							colModel:[ {name:'my_checkboxjf',index:'my_checkboxjf', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'jobfactors',index:'jobfactors',editable:false, width:190},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#jfpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:260,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#toclist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Type of Contact'],
							colModel:[ {name:'my_checkboxtoc',index:'my_checkboxtoc', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'typeofcontact',index:'typeofcontact',editable:false, width:190},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#tocpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:260,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});
						jQuery("#cwlist").jqGrid({
							datatype: "local",
							colNames:[ 'Select','Contact With'],
							colModel:[ {name:'my_checkboxcw',index:'my_checkboxcw', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'contactwith',index:'contactwith',editable:false, width:190},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#cwpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:260,
							height:500,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#whywhylist").jqGrid({
							datatype: "local",
							colNames:[ 'Why','Answer'],
							colModel:[  {name:'y',index:'y',editable:false, width:220},
										{name:'answer',index:'answer',editable:false, width:220},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#whywhypager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:1050,
							height:130,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#rclist").jqGrid({
							datatype: "local",
							colNames:[ 'Tick','Root Cause Condition'],
							colModel:[ {name:'my_checkboxtick',index:'my_checkboxtick', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },
									   {name:'rcc',index:'rcc',editable:false, width:250},
							           
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#rcpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Root Cause Condition',
							width:500,
							height:200,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#palist").jqGrid({
							datatype: "local",
							colNames:[ 'Preventive action'],
							colModel:[ {name:'prevenactn',index:'prevenactn',editable:false, width:300},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#papager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Preventive Action',
							width:500,
							height:200,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});


						jQuery("#iclist").jqGrid({
							datatype: "local",
							colNames:[ 'Emp Name','Emp Code','First Contact Date','Duty Responsibility'],
							colModel:[ {name:'icempName',index:'icempName',editable:false, width:90},
							           {name:'icempCode',index:'icempCode',editable:false, width:90},
							           {name:'icfcd',index:'icfcd',editable:false, width:90},
							           {name:'icdutyResp',index:'icdutyResp',editable:false, width:90},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#icpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:400,
							height:150,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#iblist").jqGrid({
							datatype: "local",
							colNames:[ 'Emp Code','Emp Name'],
							colModel:[ {name:'ibempCode',index:'icempCode',editable:false, width:90},
									   {name:'ibempName',index:'icempName',editable:false, width:270},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#icpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Investigated By',
							width:400,
							height:150,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#moclist").jqGrid({
							datatype: "local",
							colNames:[ 'Program Elements','P','S','C','Description of Action','Target Date','Status','Responsibility','Completed Date','Completed By','Remarks','Action Plan'],
							colModel:[  {name:'progElements',index:'progElements',editable:false, width:80},
							            {name:'my_checkboxp',index:'my_checkboxp', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:20 },
							            {name:'my_checkboxs',index:'my_checkboxs', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:20 },
							            {name:'my_checkboxc',index:'my_checkboxc', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:20 },
							            {name:'descnActn',index:'descnActn',editable:false, width:80},
							            {name:'targetDate',index:'targetDate',editable:false, width:80},
							            {name:'mocStatus',index:'mocStatus',editable:false, width:80},
							            {name:'responsibility',index:'responsibility',editable:false, width:80},
							            {name:'compltdDate',index:'compltdDate',editable:false, width:80},
							            {name:'compltdBy',index:'compltdBy',editable:false, width:80},
							            {name:'mocRemarks',index:'mocRemarks',editable:false, width:80},
							            {name:'actnPlan',index:'actnPlan',editable:false, width:80},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#mocpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:1050,
							height:130,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});
						

						
			});
		</script>
<table border="0" align="center" width="80%">
	<tr>
	<td  style="width:40%;" valign='top'>
	
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
				                  	<label>Incident No</label>
				                   	<span  style="margin-left: 72px;"><label>Date</label></span>
				</div> 
				<div class="easyui-paddingbfpx" style="padding-left:80px;"> 
							        <input id="incdntno" name="incdntno" class="easyui-combobox"  style="width:110px;" value="" / >
							        <span  style="margin-left: 32px;"> 
							            <input id="incdntdate" name="incdntdate" class="easyui-datebox" required="true" style="width:110px;"/>
							        </span> 
				</div>
				
				
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
				         <label class="mandatory-lbl">Incident No</label>
				</div>
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
							<input id="healthy" type="checkbox"/> <label class="mandatory-lbl">Healthy</label>
                  			<span  style="margin-left: 2px;">  <input id="safety" type="checkbox"/> <label class="mandatory-lbl">Safety</label></span>
                  			<span  style="margin-left: 2px;">  <input id="envt" type="checkbox"/> <label class="mandatory-lbl">Environment</label></span>
				</div>
				
				
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
				                  	<label class="mandatory-lbl">Group</label>
				                   	<span  style="margin-left: 106px;"><label class="mandatory-lbl">Priority</label></span>
				</div> 
				<div class="easyui-paddingbfpx" style="padding-left:80px;"> 
							       <select id="group" class="easyui-combobox" name="group" style="width:110px;" required="true">
											<option value="-">Injury</option>
					  				</select> 
							        <span  style="margin-left: 32px;"> 
							            <select id="priority" class="easyui-combobox" name="priority" style="width:110px;" required="true">
												<option value="-">High</option>
										</select> 
							        </span> 
				</div>
				
			
				
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
				          <label class="mandatory-lbl">Factory</label>
				</div>
				<div  class="easyui-paddingbfpx" style="padding-left:80px;">
				         <input id="cmbfactory" name="cmbfactory" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
                
					</td>
				<td  valign='top' style="width:40%;">
	
	
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				          <label>Section</label>
				</div>
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				         <input id="cmbsection" name="cmbsection" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				          <label>Cell</label>
				</div>
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				         <input id="cmbcel" name="cmbcel" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				
			
				
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				          <label>Equipment</label>
				</div>
				<div  class="easyui-paddingbtpx" style="padding-left:0px;">
				         <input id="cmbeqpmnt" name="cmbeqpmnt" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
                
                <div  class="easyui-paddingbfpx" style="padding-left:0px;padding-top:5px;">
				         	<input id="healthy" type="checkbox"/> <label>Attach Files</label>
				         	 <input type="button" id="filemanager"  class="easyui-button" onclick="" value="File Manager"/>
				</div>


	</td>
	</tr>
	<tr>
	<td colspan="2">
		<div  style="margin-left:20px;width : 1060px; height : 690px;">
	    <div class="easyui-tabs" fit="true" plain="true" style="width:200px; height : 284px;">
		<div title="Reporting" style="padding:10px;">
			<table border="0" align="center" width="90%">
			<tr>
			<td style="width:30%" valign='top'>
			
				<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				                  	<label class="mandatory-lbl">Occured Date</label>
				                   	<span  style="margin-left: 72px;"><label class="mandatory-lbl">Shift</label></span>
				</div> 
				<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							        <input id="occureddate" name="occureddate" class="easyui-datebox" required="true" style="width:110px;"/> 
							        <span  style="margin-left: 12px;"> 
							            <input id="shift" name="shift" class="easyui-combobox"  style="width:110px;" value="" / >
							        </span> 
				</div>
				
				<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				                  	<label>Reported Date</label>
				                   	<span  style="margin-left: 72px;"><label>By</label></span>
				</div> 
				<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							        <input id="reprteddate" name="reprteddate" class="easyui-datebox" required="true" style="width:110px;"/> 
							        <span  style="margin-left: 12px;"> 
							            <input id="by" name="by" class="easyui-combobox"  style="width:110px;" value="" / >
							        </span> 
				</div>
				
				<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				                  	<label>Location</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:30px;">
						 <textarea rows="5" cols="28" id="txtarealocn" name="txtarealocn"></textarea>
				</div>
				
				
				</td>
				<td style="width:30%" valign='top'>
				
				<div  class="easyui-paddingbfpx" style="padding-left:50px;">
				                  	<label>Attended By</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:50px;">
						<select id="attendedby" class="easyui-combobox" name="attendedby" style="width:110px;" required="true">
							<option value="-">---</option>
					  </select> 
				</div>
				
				<div  class="easyui-paddingbfpx" style="padding-left:50px;">
				                  	<label>Name of Hospital</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:50px;">
						 <input type="text"  class="easyui-text"  id="hospitalname" name="hospitalname"  style="width:255px;" value=""  >
				</div>
				
							 	
				
				<div  class="easyui-paddingbfpx" style="padding-left:50px;">
				                  	<label>Problem</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:50px;">
						 <textarea rows="5" cols="28" id="txtareapbm" name="txtareapbm"></textarea>
				</div>
				
				
				

			 </td>
			 <td style="width:30%" valign='top'>
			 
			 	<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				                  	<label>Medical Officer</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:0px;">
						 <input type="text"  class="easyui-text"  id="medoffcrtxt" name="medoffcrtxt"  style="width:255px;" value=""  >
				</div>
				
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				                  	<label>Medical Assistant</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:0px;">
						 <input type="text"  class="easyui-text"  id="medassttxt" name="medassttxt"  style="width:255px;" value=""  >
				</div>
			 

				
				<div  class="easyui-paddingbfpx" style="padding-left:0px;">
				                  	<label>Remarks</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding-left:0px;">
						 <textarea rows="5" cols="28" id="txtarearemarks" name="txtarearemarks"></textarea>
				</div>
			

		                
			 </td>
		 </tr>
		 <tr>
		 	<td colspan="3">
		 		<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				                 <input id="chemicalspill" type="checkbox"/><span style="margin-left:2px;"><label>Chemical Spill</label></span>
				</div>
		 		<div  class="easyui-paddingbfpx">
		 			<table id="irmlist" style="width:100%"><tr><td/></tr></table>
					<div id="irmpager"></div>
				</div>
				<div  class="easyui-paddingbfpx">
		 			<table id="pdlist" style="width:100%"><tr><td/></tr></table>
					<div id="pdpager"></div>
				</div>
				
		 	</td>
		 </tr>
	</table>
	
</div>
		<div title="Operations" style="padding:10px;">
				<div class="sub-header" > State EXACTLY what the Person was doing</div>
				<div class="easyui-paddingbfpx">
		        	 <textarea rows="5" cols="100" id="txtarearemarks" name="txtarearemarks"></textarea>
		        </div>
		        
		        <div class="sub-header"> Caused By</div>
		        
		       
		         <div style="float:left;">
		        	  <div class="cntborder" style="width:350px">
<!--		        	    <div style="padding-bottom: 8px;padding-right:10px;border-style:solid;border-width:thin;border-color:lightblue;width:350px"> -->
		        	            <div class="easyui-paddingbfpx" style="padding-top: 5px;padding-left: 5px;">
		        	            	 <input id="eqpchkbox" type="checkbox"/><span style="margin-left:2px;"><label>Equipment</label></span>
		        	            </div>
		        	    	 	 <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<label> Equipment No </label>
		        	    	 	 </div> 
		        	    	 	  <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<input id="cmbeqpno" name="cmbeqpno" class="easyui-combobox"  style="width:255px;" value="" >
		        	    	 	 </div> 
		        	    	 	 
		        	    	 	 <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<label> Equipment Name </label>
		        	    	 	 </div> 
		        	    	 	  <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<input id="cmbeqpname" name="cmbeqpname" class="easyui-combobox"  style="width:255px;" value="">
		        	    	 	 </div> 


						</div>
				</div>
				
				 <div style="float:left;padding-left:120px;">
				 
				 		  <div class="cntborder" style="width:350px">
<!--		        	    <div style="padding-bottom: 8px;padding-right:10px;border-style:solid;border-width:thin;border-color:lightblue;width:350px"> -->
		        	            <div class="easyui-paddingbfpx" style="padding-top: 5px;padding-left: 5px;">
		        	            	 <input id="vehiclechkbox" type="checkbox"/><span style="margin-left:2px;"><label>Vehicle</label></span>
		        	            </div>
		        	    	 	 <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<label> Vehicle No </label>
		        	    	 	 </div> 
		        	    	 	  <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<input id="cmbvehno" name="cmbvehno" class="easyui-combobox"  style="width:255px;" value=""  >
		        	    	 	 </div> 
		        	    	 	 
		        	    	 	 <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<label> Description </label>
		        	    	 	 </div> 
		        	    	 	  <div class="easyui-paddingbfpx" style="padding-left: 5px;">
		        	    	 	 	<input id="cmbdescn" name="cmbdescn" class="easyui-combobox"  style="width:255px;" value=""  >
		        	    	 	 </div> 


						</div>
		        	

				</div>
			
				<br/>
				<table border="0" align="left">
				<tr>
				<td>
						
				<div class="easyui-paddingbfpx" style="padding:left:30px;">
					<label>Was the work supervised by a Supervisor</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding:left:30px;">
		        	 <select id="supervised" class="easyui-combobox" name="supervised" style="width:110px;" required="true">
							<option value="-">---</option>
					  </select> 
		        </div>
			
				<div class="easyui-paddingbfpx" style="padding:left:30px;">
					<label>If Yes,Name of Supervisor</label>
				</div>
				<div class="easyui-paddingbfpx" style="padding:left:30px;">
		        	<input type="text" class="easyui-text" id="nameofsupervisor" name="nameofsupervisor"  style="width:255px;" value=""  >
		        </div>
				

				</td>
				</tr>
				<tr>
				<td>
				
				
				 <div style="float:left;">
				 <table id="permitlist" style="width:100%"><tr><td/></tr></table>
					<div id="permitpager"></div>
				</div>
				
				 <div style="float:left;padding-left:120px;">
				 				<div class="sub-header"> To be Filled in by the Department Head</div>
				 				<div class="easyui-paddingbfpx" style="padding:left:30px;">
									<label>Dept Manager</label>
								</div>
								<div class="easyui-paddingbfpx" style="padding:left:30px;">
						        	<input type="text" class="easyui-text" id="deptmngr" name="deptmngr"  style="width:255px;" value=""  >
						        </div>
						        
						        <div class="easyui-paddingbfpx" style="padding:left:30px;">
									<label>Date</label>
								</div>
								<div class="easyui-paddingbfpx" style="padding:left:30px;">
						        	 <input id="deptdate" name="deptdate" class="easyui-datebox" required="true" style="width:110px;"/> 
						        </div>
							
								<div class="easyui-paddingbfpx" style="padding:left:30px;">
									<label>Suggestions to avoid similar incident in Future</label>
								</div>
								<div class="easyui-paddingbfpx" style="padding:left:30px;">
						        <textarea rows="5" cols="28" id="txtareasugns" name="txtareasugns"></textarea>
						        </div>
							
	
						</div>
						</td>
						</tr>
				</table>
				
						 	
				

		</div>
		<div title="Risk & Cause Analysis" style="padding:10px;">
			 <table id="risklist" style="width:100%"><tr><td/></tr></table>
			 <div id="riskpager"></div>
			 
			  <div style="float:left;padding-top:10px;">
			 		 <div class="sub-header"> Describe how the event occured</div>
			 		 	<div class="easyui-paddingbfpx">
		        			 <textarea rows="5" cols="50" id="txtareaevtocrd" name="txtareaevtocrd"></textarea>
		    	    </div>
		    	     <div class="sub-header"> What substandard actions/conditions caused/could cause the event</div>
			 			<div class="easyui-paddingbfpx">
		        			 <textarea rows="5" cols="50" id="txtareasubstdactns" name="txtareasubstdactns"></textarea>
		    	    </div>
		      </div>
		      
		        <div style="float:left;padding-left:120px;padding-top:10px;">
			 		 <div class="sub-header"> What specific factors could cause the event</div>
			 		 <div class="easyui-paddingbfpx">
		        			 <textarea rows="5" cols="50" id="txtareafactor" name="txtareafactor"></textarea>
		    	    </div>
		    	      <div class="sub-header"> Remedial actions what could be done to control the causes listed</div>
			 		<div class="easyui-paddingbfpx">
		        			 <textarea rows="5" cols="50" id="txtarearemedial" name="txtarearemedial"></textarea>
		    	    </div>
		      </div>
			 
		</div>
		<div title="Immediate Causes" style="padding:10px;">
				<div class="easyui-paddingbfpx" style="padding-left:450px;">
				<input type="button" id="addupddel"  class="easyui-button" onclick="" value="Add/Update/Delete"/>
				</div>
				<div style="float:left;padding-top:40px;">
					 <table id="ssactnlist" style="width:100%"><tr><td/></tr></table>
					 <div id="ssactnpager"></div>
				</div>
				 <div style="float:left;padding-left:60px;padding-top:40px;">
				 	<table id="sscondnlist" style="width:100%"><tr><td/></tr></table>
					 <div id="sscondnpager"></div>
				 </div>
		</div>
		<div title="Basic Causes" style="padding:10px;">
			
			<table border="0">
			<tr>
			<td colspan="4" align="center">
				<input type="button" id="addupddelete"  class="easyui-button" onclick="" value="Add/Update/Delete"/>
			</td>
			</tr>
			<tr>
			<td>
			 <table id="pflist" style="width:100%"><tr><td/></tr></table>
			 <div id="pfpager"></div>
			</td>
			<td>
			 <table id="jflist" style="width:100%"><tr><td/></tr></table>
			 <div id="jfpager"></div>
			</td>
			<td>
			 <table id="toclist" style="width:100%"><tr><td/></tr></table>
			 <div id="tocpager"></div>
			</td>
			<td>
			 <table id="cwlist" style="width:100%"><tr><td/></tr></table>
			 <div id="cwpager"></div>
			</td>
			</tr>
			</table>
		</div>
		<div title="Root Cause Analysis" style="padding:10px;">
			 <table id="whywhylist" style="width:100%"><tr><td/></tr></table>
			 <div id="whywhypager"></div>
			 <table border="0">
			 <tr>
			 <td>
			 	<div class="sub-header" style="width:520px">Root Cause</div>
				  <div  class="easyui-paddingbfpx">
		        	 <textarea rows="5" cols="61" id="txtarearootcause" name="txtarearootcause"></textarea>
		        </div>
			 </td>
			  <td>
			 	<div class="sub-header" style="width:520px">Counter Measure</div>
				  <div  class="easyui-paddingbfpx">
		        	 <textarea rows="5" cols="61" id="txtareacountermeasure" name="txtareacountermeasure"></textarea>
		        </div>
			 </td>
			 </tr>
			  <tr>
			 <td>
			 	 <table id="rclist" style="width:100%"><tr><td/></tr></table>
			 	<div id="rcpager"></div>
			 </td>
			  <td>
			 	 <table id="palist" style="width:100%"><tr><td/></tr></table>
			 	<div id="papager"></div>
			 </td>
			 </tr>
			 </table>
		</div>
		<div title="HSE Dept" style="padding:10px;">
		<div align="center">
			<table border="0" align="center">
			<tr>
			<td style="margin-left:20px;">
				<div class="sub-header">For Safety Department</div>
				<div  class="easyui-paddingbfpx">
		        	 <label>Reported Date</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	 <input id="reporteddate" name="reporteddate" class="easyui-datebox" required="true" style="width:110px;"/> 
		        </div>
		        
		        <div  class="easyui-paddingbfpx">
		        	 <label>Cause</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	<input type="text" id="txtcause" name="txtcause"  class="easyui-text"  style="width:255px;" value=""  >
		        </div>
		        
		        <div  class="easyui-paddingbfpx">
		        	 <label>Agency</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	 <input type="text" id="txtagency" name="txtagency" class="easyui-text"  style="width:255px;"/> 
		        </div>
		        
		        <div  class="easyui-paddingbfpx">
		        	 <label>Recommendations</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	<textarea rows="5" cols="28" id="txtarearecomdns" name="txtarearecomdns"></textarea>
		        </div>
			
				<div class="sub-header">Incident Category</div>
				 
				 <div  class="easyui-paddingbfpx">
		        	 <label>ILO Classification</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	<input type="text" id="txtiloclassifcn" name="txtiloclassifcn" class="easyui-text"  style="width:255px;"/> 
		        </div>
		        
		         <div  class="easyui-paddingbfpx">
		        	 <label class="mandatory-lbl">Incident Type</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	 <input id="cmbinctype" name="cmbinctype" class="easyui-combobox"  style="width:255px;" value=""  >
		        </div>
		        
		         <div  class="easyui-paddingbfpx">
		        	 <label class="mandatory-lbl">Incident Category</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	 <input id="cmbinccategory" name="cmbinccategory" class="easyui-combobox"  style="width:255px;" value=""  >
		        </div>
		        
		         <div  class="easyui-paddingbfpx">
		        	 <label class="mandatory-lbl">Reason</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	 <input id="cmbreason" name="cmbreason" class="easyui-combobox"  style="width:255px;" value=""  >
		        </div>
		        
		         <div  class="easyui-paddingbfpx">
		        	 <label>Details of Incident</label>
		        </div>
		        <div  class="easyui-paddingbfpx">
		        	<textarea rows="5" cols="28" id="txtareadoi" name="txtareadoi"></textarea>
		        </div>
				 
				 <div  class="easyui-paddingbfpx">
				 	 <table id="iclist" style="width:100%"><tr><td/></tr></table>
			 		 <div id="icpager"></div>
				 </div>
				 
			</td>
			<td valign="top">
				<div class="sub-header">Status of Incidents</div>
				
				 	 <div  class="easyui-paddingbfpx"><input id="underobsvn" type="checkbox"/><span style="margin-left:2px;"> <label>Under Observation </label></span></div>
					 <div  class="easyui-paddingbfpx"><input id="shortclosed" type="checkbox"/><span style="margin-left:2px;"><label> Short Closed</label></span></div>
					 <div  class="easyui-paddingbfpx"><input id="pendingenq" type="checkbox"/><span style="margin-left:2px;"><label> Pending Enquiry </label></span></div>
					 <div  class="easyui-paddingbfpx"><input id="closechkbox" type="checkbox"/><span style="margin-left:2px;"> <label>Close </label></span></div>
				
					<div  class="easyui-paddingbfpx">
						<label>Date/By</label>
					</div> 
			    	<div  class="easyui-paddingbfpx">
					<input id="dateby" name="dateby" class="easyui-datebox" required="true" style="width:110px;"/>
					 <span  style="margin-left: 32px;"> 
                 		 <input id="cmbdateby" name="cmbdateby" class="easyui-combobox"  style="width:110px;" value=""  >
                 	 </span> 
               		 </div>
               		 
<!--               		 <div class="headin" style=" width : 400px; height : 17px;margin-bottom:10px;">Investigated By</div>-->
               		<div  class="easyui-paddingbfpx">
				 	 <table id="iblist" style="width:100%"><tr><td/></tr></table>
			 		 <div id="ibpager"></div>
					 </div>
					 
					  <div  class="easyui-paddingbfpx">
		        	 		<label>Action Taken</label>
		       		 </div>
		       		 <div  class="easyui-paddingbfpx">
		        			<textarea rows="5" cols="28" id="txtareaactntaken" name="txtareaactntaken"></textarea>
		      		  </div>
					 
					  <div  class="easyui-paddingbfpx">
		        	 		<label>By Contractor?</label>
		       		 </div>
		       		 <div  class="easyui-paddingbfpx">
		        			<input id="bycontYes" type="checkbox"/><span  style="margin-left:2px;"> Yes </span>
					  		<input id="bycontNo" type="checkbox"/><span  style="margin-left:2px;"> No </span>
		      		  </div>
					
					  <div  class="easyui-paddingbfpx">
		        	 		<label>Action Taken</label>
		       		 </div>
		       		 <div  class="easyui-paddingbfpx">
		        			<select id="actnTaken" class="easyui-combobox" name="actnTaken" style="width:110px;" required="true">
								<option value="-">---</option>
					 	 	</select> 
		      		  </div>
					 
					   <div  class="easyui-paddingbfpx">
		        	 		<label>Description</label>
		       		 </div>
		       		 <div  class="easyui-paddingbfpx">
		        			<textarea rows="5" cols="28" id="txtareadescrptn" name="txtareadescrptn"></textarea>
		      		  </div>
					
					    <div  class="easyui-paddingbfpx">
		        	 		<label>Penalty(USD)</label>
		       		 </div>
		       		 <div  class="easyui-paddingbfpx">
		        				<input type="text" class="easyui-text" id="txtpenalty" name="txtpenalty"  style="width:255px;" value=""  >
		      		  </div>
					
				
			</td>
			</tr>
			</table>
			</div>
		</div>
		<div title="Review and Control" style="padding:10px;">
		
			<table border="0">
				<tr>
					<td>
						    <div  class="easyui-paddingbfpx">
		        	 				<label>Parameter Inflicting Harm</label>
		       			   </div>
				       	    <div  class="easyui-paddingbfpx">
				        			<textarea rows="5" cols="50" id="txtareaparamIH" name="txtareaparamIH"></textarea>
				      		</div>
				      		
				      		<div  class="easyui-paddingbfpx">
		        	 				<label>Infrastructure Damage</label>
		       			   </div>
				       	    <div  class="easyui-paddingbfpx">
				        			<textarea rows="5" cols="50" id="txtareaISDamage" name="txtareaISDamage"></textarea>
				      		</div>
						
					</td>
					
					<td valign="top">
					
						  <div  class="easyui-paddingbfpx">
		        	 				<label>Nature of Damage</label>
		       			   </div>
				       	    <div  class="easyui-paddingbfpx">
				        			<textarea rows="5" cols="50" id="txtareanatureOfDamage" name="txtareanatureOfDamage"></textarea>
				      		</div>
				      		
				      		<div  class="easyui-paddingbfpx">
		        	 				<label>Cost Incurred(USD)</label>
		       			   </div>
				       	    <div  class="easyui-paddingbfpx">
				        			<input type="text" id="txtcostincurred" name="txtcostincurred"  style="width:435px;" value="" class="easyui-text" / >
				      		</div>
						
					</td>
					</tr>
					<tr>
					<td colspan="2">
						    <div  class="easyui-paddingbfpx">
		        	 				<label>Reviewer's reaction to the Investigator's analysis of the basic causes of this incident and the remedial actions directed at possible inadequacies in the program,its standards or compliance to the standards</label>
		       			    </div>
				       	    <div  class="easyui-paddingbfpx">
				        			<textarea rows="5" cols="118" id="txtareareviewersreactn" name="txtareareviewersreactn"></textarea>
				      		</div>
					
						<div  class="easyui-paddingbfpx">
						 	 <table id="moclist" style="width:100%"><tr><td/></tr></table>
			 				 <div id="mocpager"></div>
						 </div>
					</td>
					
				</tr>
			</table>
		</div>
		</div>
		</div>
	</td>
	</tr>
</table>