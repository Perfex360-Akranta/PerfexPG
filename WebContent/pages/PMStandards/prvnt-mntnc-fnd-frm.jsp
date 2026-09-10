<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
						jQuery.noConflict();
						jQuery(document).ready(function(){
						jQuery("#grid2").jqGrid({
								datatype: "local",
								colNames:[ 'SLNO','Equipment Name','Equipment No','Civil','Electrical','Instrumentation','Mechanical','Others'],
								colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
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
									  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false}									  
									
								  ],		  
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager2', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Preventive Maintanance',
								width:800,
								height:190,
								loadonce: true
							
							});
						});
						</script>
<div>

<table width="100%">  
  <tr>
    <th >
    <div style="width: 100%; height: 50px; margin-bottom: 10px;text-align: left">
    <span  style="float: left;">
    <div id="find_cntnt" style="margin: 5px 0px 0px 50px;float:left" class="sub-cntborder">
    <h3 style="margin: 0px;padding: 0px">Find</h3>
    Option<input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:100px;" value=""  ><input class="easyui-text" type="text" value="" size="30"/>
    <input type="button" value="Find Nex" class="easyui-button" class="easyui-button"> 
    <input type="button" value="Close" onclick="close_all_fnd_wndw();" class="easyui-button">
	</div>
	
	<div id="fltr_cntnt" style="margin: 5px 0px 0px 50px;display:none;float:left" class="sub-cntborder">
    <h3 style="margin: 0px;padding: 0px">Fillter</h3>
    Option<input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:100px;" value=""  ><input type="text" class="easyui-text" value="" size="30"/>
<input type="button" value="Find Nex" class="easyui-button"> 
    <input type="button" value="Close" onclick="close_all_fnd_wndw();" class="easyui-button">
	</div>
	 <span style="font-weight: normal;float: left;">
    <br/>
    <input type="checkbox">Activity wise
    <br/>
    <input type="checkbox">Assembly wise
    </span>
    </span>
   
    <span  style="float: right;">
    <input type="button" value="Find" onclick="find_open();" style="width: 60px;margin:0px;padding:0px" class="easyui-button"> 
    <input type="button" value="Filter" onclick="filter_open();" class="easyui-button" style="width: 60px;margin:0px;padding:0px">
    <input type="button" value="Plane Configuration" class="easyui-button"> <br/>
    <input type="button" value="View" style="width: 124px;margin:0px;padding:0px" class="easyui-button">
    <input type="button" value="Assembly" style="width: 132px" class="easyui-button">
     </span>
    </div>
    </th>    
  </tr>
  <tr>
  <td >
  <div  class="easyui-paddingbfpx">
                <div  class="easyui-paddingbfpx">                
                    
                </div> 
                <div class="easyui-paddingbfpx">
                <table id="grid2" style="width:100%"><tr><td/></tr></table>
<div id="pager2">
</div>
</div>
</div>
</td>
  </tr>
</table>

</div>
