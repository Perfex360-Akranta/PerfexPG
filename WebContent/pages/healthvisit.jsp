<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	


jQuery( "#filter" ).click(function() {
	jQuery( "#dialog" ).show();
	jQuery( "#dialog" ).dialog({
		autoOpen: false,
		modal: true,
		height: 500,
		width: 800			
	});
});

jQuery( "#newbook" ).click(function() {
	jQuery("#ohvm").load('health_view.ohv', function(response, status, xhr) {
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  }
		});	
});

	
	jQuery("#list1").jqGrid({
		datatype: "local",
		colNames:[ 'Factory','Equipment','Location','Employee Name','Employee Type','Visit'],
		colModel:[ {name:'factory',index:'factory',editable:false, width:260},
				   {name:'equipmnt',index:'equipmnt',editable:false, width:260},
				   {name:'locn',index:'locn',editable:false, width:120},
				   {name:'empName',index:'empName',editable:false, width:130},
				   {name:'empType',index:'empType',editable:false, width:130},
				   {name:'visit',index:'visit',editable:false, width:130},
				  ],
		    data:[
			  {id:"1", invdate:"2007-10-01",factory:"test1", equipmnt:"note", closed:true},
			  {id:"2", invdate:"2007-10-02",factory:"test2", equipmnt:"note2",closed:false},
			  {id:"3", invdate:"2007-09-01",factory:"test3", equipmnt:"note3",closed:false},
			  {id:"4", invdate:"2007-10-04",factory:"test4", equipmnt:"note4",closed:true },
			  {id:"5", invdate:"2007-10-31",factory:"test5", equipmnt:"note5",closed:false},
			  {id:"6", invdate:"2007-10-01",factory:"test1", equipmnt:"note", closed:true},
			  {id:"7", invdate:"2007-10-02",factory:"test2", equipmnt:"note2", closed:false},
			  {id:"8", invdate:"2007-09-01",factory:"test3", equipmnt:"note3",closed:false},
			  {id:"9", invdate:"2007-10-04",factory:"test4", equipmnt:"note4", closed:true},
			  {id:"10",invdate:"2007-10-31",factory:"test5",equipmnt:"note5",closed:false}
			
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
		caption:' Double Click on data row to Edit details',
		width:990,
		height:480,
		loadonce: true,
		ondblClickRow: function(id){
			var rowData = jQuery("#list1").jqGrid('getRowData',id);
			
		 }
	});	
	});
	</script>	
	
<div id="dialog" title="Filter" style="display:none;">
<form id="filterDialog" name="filterDialog">
	<table border="0" align="center">
		<tr>
			<td style="width:50%" valign='top'>
					<div class="easyui-paddingbfpx" style="padding-left:70px;">
						<label> Factory </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						<input id="cmbfact" name="cmbfact" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
							
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label> Section </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						<input id="cmbsect" name="cmbsect" class="easyui-combobox"  style="width:255px;" value=""  > 
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label> Cell </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
							<input id="cmbcell" name="cmbcell" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
							
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Circle</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbcircle" name="cmbcircle" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Equipment</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbeqpt" name="cmbeqpt" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Employee Type</label>
					</div> 
					<div style="padding-left:70px;">
				   <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:255px;">
						    <input id="regular" type="checkbox"/> <label>Regular</label>
						    <span  style="margin-left: 2px;"><input id="contract" type="checkbox"/> <label>Contract</label></span>
					</div></div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Employee</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbemp" name="cmbemp" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<input id="visitchkbox" type="checkbox"/><label>Visit From</label>
						<span  style="margin-left: 78px;"><label>Visit To</label></span>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						<input id="visitfrom" name="visitfrom" class="easyui-datebox" required="true" style="width:110px;"/>
					     <span  style="margin-left: 32px;"> 
			                  <input id="visitto" name="visitto" class="easyui-datebox" required="true" style="width:110px;"/>
			            </span> 
   				   </div>
			</td>
			<td style="width:50%" valign='top'>
			
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Location</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmblocn" name="cmblocn" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Body Part</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbbody" name="cmbbody" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Created By</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbcreatedby" name="cmbcreatedby" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Attended By</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbattnby" name="cmbattnby" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Visit Type</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <input id="cmbvisit" name="cmbvisit" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Work Area</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <select id="workarea" class="easyui-combobox" name="workarea" style="width:130px;" required="true">
								<option value="-"> Both</option>
							</select> 
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:70px; ">
						<label>Type</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
						    <select id="type" class="easyui-combobox" name="type" style="width:130px;" required="true">
								<option value="-"> All</option>
							</select> 
					</div>
			</td>
		</tr>
		<tr>
		<td colspan="2" align="center" style="padding-left:50px;">
			   <input type="button" id="ok"  class="easyui-button" onclick="" value="Ok"/>
               <input type="button" id="clear"  class="easyui-button" onclick="" value="Clear"/>
		</td>
		</tr>
	</table>
</form>
</div>

<!--<div id="ohvm" align="center" style="width: 1100px;margin: 0 auto;border-style:solid;border-width:thin;height:600px">-->
<div class="main-cntborder" id="ohvm">

 <div class="easyui-paddingbfpx" style="padding-top: 5px;padding-left:630px;">
		   <input type="button" id="newbook"  class="easyui-button" onclick="" value="New Booking"/>
		   <input type="button" id="view"  class="easyui-button" onclick="" value="View"/>
		   <input type="button" id="filter"  class="easyui-button" onclick="" value="Filter"/>
		   <input type="button" id="excel"  class="easyui-button" onclick="" value="Export to Excel"/>
 </div>
<table id="list1" style="width:100%"><tr><td/></tr></table>
<div id="pager1"></div>
</div>