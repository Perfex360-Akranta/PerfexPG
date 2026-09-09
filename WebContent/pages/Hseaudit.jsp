 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery(document).ready(function(){	
	
jQuery( "#filter" ).dialog({
	autoOpen: false,
	height: 350,
	width: 450,
	modal: true

	});
	
close1();
});
						function open1(){
							//document.getElementById("filter").style.display = 'block';
							
							jQuery('#filter').dialog('open');							
						}
						
						function close1(){
							jQuery('#filter').dialog('close');
						}	


						function newadt_frm_clk(){
							jQuery("#viewhseadt").hide();
							jQuery("#newhseadt").load('newhse_view.hsea', function(response, status, xhr) {
					      		  if (status == "error") {
					      			jQuery("#viewhseadt").show(); 
					      		    var msg = "Sorry but there was an error: ";
					      		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					      		  }
					      		  else{
					      				
					      		  }	
					      		});	
							}	
		


jQuery("#list").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", Factory:"BAL",Section:"test1", Cell:"note", Equipment:"cylinder",Location:"pune",AuditDate:"2008-10-02",AuditLevel:"M1",AuditorName:"MAk"},
		  {id:"1", Factory:"BAL",Section:"test2", Cell:"note1", Equipment:"Bal Bearing",Location:"lucknow",AuditDate:"2008-15-05",AuditLevel:"M2",AuditorName:"Roshan"},
		  {id:"1", Factory:"BAL",Section:"test3", Cell:"note2", Equipment:"Belt vendor",Location:"pune",AuditDate:"2008-10-02",AuditLevel:"M1",AuditorName:"Hemanth"},
		  {id:"1", Factory:"BAL",Section:"test4", Cell:"note3", Equipment:"Trinard",Location:"pune",AuditDate:"2008-10-02",AuditLevel:"M1",AuditorName:"Nanda"},
		  
		
	  ],
	  colNames:[ 'Factory','Section','Cell','Equipment','Location','AuditDate','AuditLevel','AuditorName'],
		colModel:[ {name:'Factory',index:'Factory',editable:false, width:60},
		           {name:'Section',index:'Section',editable:false, width:80},
				   {name:'Cell',index:'Cell',editable:false, width:80},
				   {name:'Equipment',index:'Equipment',editable:false, width:100},
				   {name:'Location',index:'Location',editable:false, width:80},
				   {name:'AuditDate',index:'AuditDate',editable:false, width:80},
				   {name:'AuditLevel',index:'AuditLevel',editable:false, width:100},
				   {name:'AuditorName',index:'AuditorName',editable:false, width:80},
				  
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
	caption:'HSE Audit Sheet',
	width:800,
	height:100,
	loadonce: true
	
	/*ondblclick: function(id){alert("view clicked");
		var dataString = 'f_date_from='+ f_date_from +'&f_date_to='+ f_date_to;
		
		jQuery("#list2").setGridParam({url:'FiveW2HRpt_view.fwh?'+dataString,datatype:'xml'}).trigger('reloadGrid');
		alert(setGridParam());
		 }
	*/ 

//}); 
});



</script>

<div id="viewhseadt" class="" width="80%" > 


<div id="" align="center" class="cntborder" style="width: 80%;margin: 0 auto;">

	
				<div class="sub-header" >No of records Processed : </div>
				<div class="sub-header" >Double click on the row to edit Details</div>
				<div style="float: right;padding-bottom: 10px;">
				<input class="easyui-button" type="button" class ="button" value="New Audit" id="newadit" onclick="newadt_frm_clk();"/>
				<input  class="easyui-button" type="button" class ="button" value="View" id="vw" />
				<input  class="easyui-button" type="button" class ="button" value="Filter" id="clickfilter" onclick="open1()"/>
				<input  class="easyui-button" type="button" class ="button" value="Export to Excel" id="exptexl" /> 
				</div>
				<div id="grid" style="padding-top:30px"></div>
				<!--	End grid	 Script				-->    

					<table id="list" style="width:100%"><tr><td/></tr></table>
	
					<div id="pager"></div>
										
					<!--	end of grid			-->
		</div>
</div>
<div id="newhseadt"  ></div>
<div id="filter"   class="" title="FILTER">
<!--	<div class="sub-header" >Filter </div>-->
	<div  style="padding-left:80px;padding-right:50px;">
				
					
				  <div class="easyui-paddingbfpx "><label>Factory</label></div> 
                  <div > 
                  <input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value=""  >
                   </div>
					
					<div class="easyui-paddingbfpx "><label>Section</label></div> 
                  <div > 
                	<input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					<div class="easyui-paddingbfpx"><label>Cell</label></div> 
                  <div > 
                	<input id="cmbCell" name="cmbCell" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
					</div>
				 
					<div class="easyui-paddingbfpx"><label>Circle</label></div> 
                  <div > 
                	<input id="cmbcircle" name="cmbcircle" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx"><label>Equipment</label></div> 
                  	<div > 
                	<input id="cmbeqp" name="cmbeqp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	<input  class="easyui-button" type="button" class="but" value="..." />
					</div>
					
					<div class="easyui-paddingbfpx"><label>Audit Date</label><span class="lblrr" style="padding-left:80px;">To</span></div> 
						 <div> 
                			  <input id="auditdate" class="easyui-datebox" required="true"  style="width:113px;"/>
								 
                  <span class="floatR3" > 
                  <input id="to" class="easyui-datebox" required="true"  style="width:113px;"/>
                  </span> </div>
				
				<div  style="padding-bottom: 10px;"></div>
				<div>
				
				<a href="#" class="easyui-button" onclick="javascript:alert('save')">Ok</a>
				<a href="#" class="easyui-button" onclick="javascript:alert('clear')">Clear</a>
				<a href="#" class="easyui-button" onclick="close1()">Close</a>
				<a href="#" class="easyui-button" onclick="newadt_frm_clk();">New Audit</a>
				
			</div>
				<!--<div >
				
				<input type="button" class ="button" value="Ok" id="ok" />
				<input type="button" class ="button" value="Clear" id="clear" />
				<input type="button" class ="button" value="Close" id="close"onclick="close1()"/>
				<input type="button" class ="button" value="New Audit" id="newadit" "/>
				</div>-->
		</div>
	
	
</div>