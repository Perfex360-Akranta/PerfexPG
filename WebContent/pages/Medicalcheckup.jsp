 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
function newadt_frm_clk(){
	jQuery("#mcup").load('Anualcal_input.acl', function(response, status, xhr) {
  		  if (status == "error") {
  		    var msg = "Sorry but there was an error: ";
  		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
  		  }
  		});	
	}	
	
jQuery( "#filter" ).click(function() {	
	jQuery("#filterMC").show();
	jQuery( "#filterMC" ).dialog({
		autoOpen: false,
		modal: true,
		height: 490,
		width:410		
	});
					
});

function close1(){
	jQuery('#filterMC').dialog('close');
}	


jQuery( "#newbokin" ).click(function() {	
	jQuery("#newbokin").click(function(){
		jQuery("#mainfrm").hide();
		  });
	jQuery("#mainfrm").load('medicalcheckup_view.mcp', function(response, status, xhr) {
  		  if (status == "error") {
  		    var msg = "Sorry but there was an error: ";
  		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
  		  }
  		});	
	});	
jQuery("#mdclcheckup").jqGrid({
		url:'',
		datatype: "local",
		data:[
			  {Description:"ASSEMBLY", Where:"",FromDate:"     ADD ASSEMBLY     ", Description1:"SPARES", Where:"",Spares:"SPARES"},
			  
			  		
		  ],
		  colNames:[ 'Description','Where','FromDate','ToDate','AttendedBy','MedicalCheckup','TargetGroup','Spares'],
			colModel:[ {name:'Description',index:'Description', editable:false, wWhereth:130},		          
					   {name:'Where',index:'Where',editable:false, width:100},
					   {name:'FromDate',index:'FromDate',  width:160},
					   {name:'Description',index:'Description',editable:false, width:130},		          
					   {name:'AttendedBy',index:'AttendedBy',editable:false, width:100},
					   {name:'MedicalCheckup',index:'MedicalCheckup',editable:false, width:100},
					   {name:'TargetGroup',index:'TargetGroup',editable:false, width:100},
					   {name:'Spares',index:'Spares',  width:68},
					     ],
		rowNum:50,
		rowList:[5,10,20],
		rownumbers: true,
		shrinkToFit:false,	
		pager: '#pager', 
		sortname: 'id',
		viewrecords: true,
		sortorder: "asc", 
		caption:'Medical Checkup Modifications',
		width:950,
		height:300,
		loadonce: true
		
		
	});
	function actionFormatter(cellvalue, options, rowObject) {
		

	    return "<button onclick=\"alert('" + cellvalue + "')\">"+ cellvalue +"</button>" ;
	}

	function actionFormatter1(cellvalue1, options1, rowObject1) {
		
		
	    return "<button onclick=\"alert('" + cellvalue1 + "')\">"+ rowObject.Link +"</button>" ;
	}
});
</script>
<div style="padding-left: 130px;">

<div id="mcup"></div>
	<div id="mainfrm">
		<div id="aclsp" align="center" class="main-cntborder">
			<table align="center">
				<tr>
					<td>
						<div>
							<div style="float: right;padding-right: 50px;">
									<input type="button" class ="easyui-button" value="New Booking" id="newbokin" onclick="newadt_frm_clk()"/>
								<input type="button" class ="easyui-button" value="View" id="View" onclick=""/>
								<input type="button" class ="easyui-button" value="Filter" id="filter" onclick="open1();"/>
								<input type="button" class ="easyui-button" value="Export to Excel" id="exptexl" onclick=""/>
							</div>
							<div class="sub-header" class="cntborder" style="width: 780px;">No of records Processed : <span>Double Click on data row to Edit details</span></div>	
						</div>	
					</td>
				</tr>
				<tr>
					<td>
						<div id="grid" style="padding-right: 80px;">
							<!--	End grid	 Script				-->    
			
								<table id="mdclcheckup" style="width:100%"><tr><td/></tr></table>
								<div id="pager"></div>
							<!--	end of grid			-->
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div id="filterMC"  title="Filter" style="display:none;">
		<div>
			<table>
				<tr>
					<td>
				  		<div class="easyui-paddingbfpx"><label>Medical checkup No</label></div> 
                  		<div><input id="cmbmcno" name="cmbmcno" class="easyui-combobox"  style="width:300px;" value=""  ></div>
						<div class="easyui-paddingbfpx"><label>Description</label></div> 
						<div> 
                			<input id="txtdesc" name="txtDesc"  class="easyui-text" style="width:195px;value=""  >
			      				<span> 
	                  				<select id="" name="" style="width:100px;required="true">
					  					<option value="-">  - - -</option>
					  					<option value="ts"> Begin</option>
					  					<option value="tt"> test2</option>
					  					<option value="tst">test3</option>
					  					<option value="te"> test4</option>		
					  				</select>
                  				</span> 
                 		</div>
                 	</td>
            </tr>
            <tr>
            	<td>
					<div class="easyui-paddingbfpx"><label>Where</label></div> 
						 <div> 
                			<input id="txtwhere" name="txtwhere"  class="easyui-text" style="width:195px;" value=""  >
                  			<span> 
                  				<select id=""  name="" style="width:100px;" required="true">
									<option value="-">  - - -</option>
									<option value="ts"> Begin</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
                  </span> </div>
                </td>
               </tr>
               <tr>
               	<td>
                  <div class="easyui-paddingbfpx" ><label>Medical Checkup</label></div> 
						 <div> 
                			  <input id="txtmdclcp" name="txtmdclcp"  class="easyui-text" style="width:195px;" value=""  >
								 
                  <span> 
                  <select id=""  name="" style="width:100px;" required="true">
									<option value="-">  - - -</option>
									<option value="ts"> Begin</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
                  </span> </div>
                  </td>
                  </tr>
                  <tr>
                  <td>
                  <div class="easyui-paddingbfpx" ><label>Target Group</label></div> 
						 <div> 
                			  <input id="txttrgtgrp" name="txttrgtgrp"  class="easyui-text" style="width:195px;" value=""  >
								 
                  <span> 
                  <select id=""  name="" style="width:100px; required="true">
									<option value="-">  - - -</option>
									<option value="ts"> Begin</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
                  </span> </div>
				</td>
				</tr>
				<tr>
					<td>	
					<div class="easyui-paddingbfpx"><label">Remarks</span></div> 
						 <div> 
                			  <input id="txtrmrks" name="txtrmrks"  class="easyui-text" style="width:195px;" value=""  >
								 
                  <span> 
                  <select id="cmbnatofwork"  name="cmbnatofwork" style="width:100px;" required="true">
									<option value="-">  - - -</option>
									<option value="ts"> Begin</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
                  </span> </div>
                  </td>
				</tr>	
				<tr>
				<td>
					<div class="easyui-paddingbfpx" ><label>Attended BY</label></div> 
							<div> 
							<select id=""  name="" style="width:300px; required="true">
									<option value="-">  - - -</option>
									<option value="ts"> test1</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
		                		
							</div>
				</td>
				</tr>
				<tr>
				<td>			
					<div class="easyui-paddingbfpx"><label>From Date</label><span style="padding-left:90px;">To Date</span></div> 
						 <div> 
                			  <input id="auditdate" class="easyui-datebox" required="true"  style="width:150px;"/>
				  <span> 
                  <input id="to" class="easyui-datebox" required="true"  style="width:150px;"/>
                  </span> </div>
				</td>
				</tr>
				<tr>
				<td>
				<div class="easyui-paddingbfpx" id="dlg-buttons" align="right">
				<input type="button" class ="easyui-button" value="Ok" id="ok" onclick=""/>
				<input type="button" class ="easyui-button" value="Clear" id="clear" onclick=""/>
				<input type="button" class ="easyui-button" value="Close" id="Close" onclick=""/>
				</div>
				</td>
				</tr>
			</table>			
			</div>
				<!--<div >
				
				<input type="button" class ="button" value="Ok" id="ok" />
				<input type="button" class ="button" value="Clear" id="clear" />
				<input type="button" class ="button" value="Close" id="close"onclick="close1()"/>
				<input type="button" class ="button" value="New Audit" id="newadit" "/>
				</div>-->
		</div>
</div>
