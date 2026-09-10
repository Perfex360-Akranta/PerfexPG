
<script type="text/javascript">
						jQuery.noConflict();
						jQuery(document).ready(function(){
						jQuery("#grid3").jqGrid({
								datatype: "local",
								colNames:[ 'Skill Name','Skill Type','No of Employee','Minutes'],
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
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager3', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Additional Information',
								width:800,
								height:140,
								loadonce: true
								
								/*ondblclick: function(id){alert("view clicked");
									var dataString = 'f_date_from='+ f_date_from +'&f_date_to='+ f_date_to;
									
									jQuery("#list2").setGridParam({url:'FiveW2HRpt_view.fwh?'+dataString,datatype:'xml'}).trigger('reloadGrid');
									alert(setGridParam());
									 }
								*/ 
					
						//}); 
							
							});
						});
						</script>
<div>

<table width="100%">
  <tr>
    <th ><div class="main-header" style="margin-bottom: 10px;text-align: left"><span>Additional Information</span></div></th>    
  </tr>
  <tr>
  <td >
  <div class="easyui-paddingbfpx">
                <div class="easyui-paddingbfpx">                
                    <span  style="float: right;">
                    <input type="button" class="easyui-button" value="New Skill" onclick="return grid_row_click()">
                    <input type="button" class="easyui-button" value="Delete" onclick="return grid_row_click()">
                    <input type="button" class="easyui-button" value="Close" onclick="return grid_row_click()">
                    </span>
                </div> 
                <br/>
                <br/>
                <div class="easyui-paddingbfpx">
                <table id="grid3" style="width:100%"><tr><td/></tr></table>
                <div id="pager3">
                </div>
                </div>
                </div>    
  </td>
  </tr>
</table>

</div>
