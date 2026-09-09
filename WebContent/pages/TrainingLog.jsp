
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>

jQuery(document).ready(function(){
	initialiseForm('frmTraininglog');

	formatDateBox("dtestrdate",'dd-MMM-yyyy');
	formatDateBox("dteenddate",'dd-MMM-yyyy');	
});



/*jQuery("#TrainingLog").jqGrid({
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
	ondblClickRow: function(rowid) 
	{		
		 //alert(status);
	 jQuery("#Employee").load("TraningLog_view.tl", function(response, status, xhr) 
			 {
		  
		 		 if (status == "error") 
			 		 {
			    		var msg = "Sorry but there was an error: ";
			    		jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			  		  }
		  		else if(status == "success")
		  			{
			  			jQuery("#filter").hide();
			  			//jQuery("#EquipmentDefect").show();
		  			}
		  
			}
		);
	}	
});*/

</script>
<form action="" id="frmTraininglog" name="frmTraininglog" method="post">
<div id="" style="">
<div class="main-cntborder" style="width:800px;height:400px;margin-top:0px;margin-left:8%;">
<div style="" align="center">
	<table>
		<tr>
			<td>
				<div align="center">
				<table align="center" style="padding-top:20px;easyui-paddingbfpx;">
					<tr>
						<td>
							<div style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Program</label></div> 
			                	<div class="easyui-paddingbfpx" style="padding-right: 5px;"> 
			                		<input id="cmbProgram" name="cmbProgram" class="easyui-combobox"  style="width:300px;" value="${requestScope.Program}"  >
								</div>
							</div>
						</td>
						<td>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Program Benefit</label></div> 
						        <div class="easyui-paddingbfpx" style="padding-right: 5px;"> 
						        <input id="cmbProgramBenifits" name="cmbProgramBenefits" class="easyui-combobox"  style="width:300px;" value="${requestScope.ProgramBenefiy}"  >
								</div>					
							</div>
						</td>
						
						<td colspan=2>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Duration</label></div> 
			                	  	<div class="easyui-paddingbfpx">
			                		<input id="txtDuration" class="easyui-text" name="txtduration" style="width:100px;" value="${requestScope.Durtion}"  >
			                		<label class="notes">(On Hours)</label>
								</div>
							</div>
						</td>
						
					</tr>
					<tr>
					<td>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Training Mode</label></div> 
						        <div class="easyui-paddingbfpx"> 
						        <input id="cmbTrainMaode" name="cmbTrainMode" class="easyui-combobox"  style="width:300px;" value="${requestScope.TrainingMode}"  >
								</div>
							</div>	
						</td>
						
						<td>
							<div style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Type</label></div> 
							    <div class="easyui-paddingbfpx"> 
									<select id="cbobType" class="easyui-combobox" name="cbobType" value="${requestScope.TrainingType}"  style="height: 22px;width:250px;font-size:9px;" <c:out value = "${requestScope.TrainingType == true ? ' disabled':''}"/>>					
											<option value="R"> Regular</option>
											<option value="A"> Advanced</option>
											<option value="E"> Expert</option>						
									</select> 	
							   
								</div>
							</div>			
						</td>
					</tr>
					<tr>
						<td colspan>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Venue</label></div> 
				                <div class="easyui-paddingbfpx"> 
				                <input id="txtVenue" class="easyui-text" name="txtVenue" style="width:300px;" value="${requestScope.Venue}"  >
								</div>
							</div>	
						</td>
						<td>
							<div  style="floatleft;">
								<span><label class="mandatory-lbl">Start Date</label></span>
								<span style="padding-left:77px; "><label class="mandatory-lbl">End Date</label></span>
								<div class="easyui-paddingbfpx"> 
							    <input id="dtestrdate" class="easyui-datebox" value="${requestScope.StartDate}"  style="width:100px;"/>
							    <span style="padding-left:40px">
							     <input id="dteenddate" class="easyui-datebox" value="${requestScope.EndDate}" style="width:100px;"/></span>
								</div>
							</div>			
						</td>
						<td>
							<div >
								<div class="easyui-paddingbfpx"></div> 
								<div class="easyui-paddingbfpx"> 
							    
								</div>
							</div>			
						</td>
						
					</tr>
					<tr>
					
					</tr>
					<tr>
					
						<td rowspan=2>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Task</label></div> 
				                <div class="easyui-paddingbfpx"> 
				                <textarea rows="3" style="width:300px;resize:none;" cols="" id="Description" name="Description">${requestScope.Task}</textarea>
								</div>
							</div>
						</td>
						<td rowspan=2>
							<div  style="floatleft;">
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Objective</label></div> 
							    <div class="easyui-paddingbfpx">
							    <textarea rows="3" style="width:300px;resize:none;" cols=""  id="Description" name="Description">${requestScope.Objective}</textarea>
								</div>
							</div>			
						</td>
						
					</tr>
					<tr>					
					</tr>
					<tr>						
						<td>
							<div  style="floatleft;">
								<div  style="padding-bottom: 15px;"></div>
								<div style="padding-left: 155px;">
						    	<input type="button" class="easyui-button" value ="File Manager" id="FileManager" style="width:145px;"onclick=""/>
								</div>	
							</div>
						</td>	
					</tr>			
				</table>
				</div>	
			</td>
		</tr>		
	</table>
</div>	
</div>
</div>
</form>