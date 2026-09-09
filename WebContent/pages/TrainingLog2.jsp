<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script>

jQuery("#TrainingLog").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Sl.No','Name','Code','Section','Depaartment','Designation'],
		colModel:[ {name:'Sl.No',index:'Sl.No', editable:false, width:65},		          
				   {name:'Name',index:'Parameters',editable:false, width:100},
				   {name:'Code',index:'Code',  width:160},
				   {name:'Section',index:'Section',  width:160},
				   {name:'Department',index:'Department',  width:160},
				   {name:'Designation',index:'Designation',  width:160},	
			    ],
	rowNum:50,
	rowList:[10,20,30],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'',
	width:900,
	height:300,
	loadonce: true,
	ondblClickRow: function(rowid) {		
		 //alert(status);
	 jQuery("#Employee").load("TraningLog_view.tl", function(response, status, xhr) {
		  
		  if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			  }
		  else if(status == "success")
		  {
			  jQuery("#filter").hide();
			  //jQuery("#EquipmentDefect").show();
		  }
		  
	});
}	
});

</script>

<div class="main-cntborder" align="center">
	<table>
		<tr>
			<td>
				<div align="center">
				<table align="center" style="padding-top:20px;easyui-paddingbfpx;">
					<tr>
						<td>
							<div style="floatleft;">
								<div><label>Program</label></div> 
			                	<div style="padding-bottom: 10px;"> 
			                		<input id="cmbProgram" name="cmbProgram" class="easyui-combobox"  style="width:300px;" value=""  >
								</div>
							</div>
						</td>
						<td colspan=2>
							<div  class="floatleft;">
								<div  class="easyui-paddingbfpx">
								<label class="mndlbl">Program Benefit</label></div> 
						        <div  class="easyui-paddingbfpx"> 
						        <input id="cmbProgramBenifits" name="cmbProgramBenefits" class="easyui-combobox"  style="width:300px;" value=""  >
								</div>					
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Factory</label></div> 
			                	<div style="padding-bottom: 10px;"> 
			                	<input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:300px;" value=""  >
								</div>
							</div>
						</td>
						<td colspan=2>
							<div  class="floatleft;">
								<div><label class="mndlbl">Program Classification</label></div> 
					           	<div style="padding-bottom: 10px;">
					           	<input id="cmbProgramClass" name="cmbProgramClass" class="easyui-combobox"  style="width:300px;" value=""  >
								</div>
							</div>			
						</td>
					</tr>
					<tr>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Description</label></div> 
				                <div style="padding-bottom: 10px;"> 
				                <textarea rows="3" style="width:300px;resize:none;" cols="" id="Description" name="Description"></textarea>
								</div>
							</div>
						</td>
						<td colspan=2>
							<div  class="floatleft;">
								<div><label class="mndlbl">Objective</label></div> 
							    <div style="padding-bottom: 10px;">
							    <textarea rows="3" style="width:300px;resize:none;" cols="" id="Description" name="Description"></textarea>
								</div>
							</div>			
						</td>
					</tr>
					<tr>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Code</label></div> 
				                <div style="padding-bottom: 10px;"> 
				                <input id="txtCde" class="easyui-text" name="txtcde"  style="width:300px; height : 21px;" value=""  >
								</div>
							</div>
						</td>
						<td colspan=2>
							<div class="floatleft;">
								<div><label class="mndlbl">Type</label></div> 
							    <div style="padding-bottom: 10px;"> 
							    <input id="cmbType" name="cmbType" class="easyui-combobox"  style="width:300px;" value=""  >
								</div>
							</div>			
						</td>
					</tr>			
					<tr>
						<td>
							<div  style="floatleft;">
								<div><label class="mndlbl">Venue</label></div> 
				                <div style="padding-bottom: 10px;"> 
				                <input id="txtVenue" class="easyui-text" name="txtVenue" style="width:300px;" value=""  >
								</div>
							</div>	
						</td>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Start Date</label></div> 
								<div style="padding-bottom: 10px;"> 
							    <input id="Sdate" class="easyui-datebox" required="true" style="width:145px;"/>
								</div>
							</div>			
						</td>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">End Date</label></div> 
								<div style="padding-bottom: 10px;"> 
							    <input id="Edate" class="easyui-datebox" required="true" style="width:145px;"/>
								</div>
							</div>			
						</td>
					</tr>
					<tr>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Duration</label></div> 
			                	  	<div style="padding-bottom: 10px;">
			                		<input id="txtDuration" class="easyui-text" name="txtduration" style="width:230px;" value=""  >
			                		<label class="notes">(On Hours)</label>
								</div>
							</div>
						</td>
						<td>
							<div  class="floatleft;">
								<div><label class="mndlbl">Training Mode</label></div> 
						        <div class="padding-bottom: 10px;"> 
						        <input id="cmbTrainMaode" name="cmbTrainMode" class="easyui-combobox"  class="width:145px;" value=""  >
								</div>
							</div>	
						</td>
						<td>
							<div  class="floatleft;">
								<div  style="padding-bottom: 8px;"></div>
								<div>
						    	<input type="button" class="easyui-button" value ="File Manager" id="FileManager" style="width:145px;"onclick=""/>
								</div>	
							</div>
						</td>						
					</tr>		
				</table>
				</div>	
			</td>
		</tr>
		<tr>
			<td>
				<div id="Employee">	
					<div><label class="notes">Employee Details. Press F11 to Add Employee. Press Enter Key To Add New Row.</label></div>
						<div>
							<table id="TrainingLog" style="width:100%"><tr><td/></tr></table>	
							<div id="pager"></div>			
						</div>
				</div>					
			</td>
		</tr>
	</table>
</div>	