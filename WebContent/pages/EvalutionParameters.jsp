<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>
jQuery("#EvaluationParam").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Sl.No','Evaluation Parameters'],
		colModel:[ {name:'Sl.No',index:'Sl.No', editable:false, width:65},		          
				   {name:'Evaluation Parameters',index:'Parameters',editable:false, width:100},
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
	height:200,
	loadonce: true	
});
</script>


<div class="main-cntborder"  align="center">
	<table align="center" style="padding-top:20px;easyui-paddingbfpx">
			<tr>
				<td>
					<div style="floatleft;" align="center">
						<table>
							<tr>
								<td>
									<div class="easyui-paddingbfpx"><label class="mndlbl">Parameter Type</label></div> 
	                  					<div style="padding-bottom: 10px;"> 
	                					<input type="checkbox" id="CellSpecific" />
	        							<label>Cell Specific</label>
	        							<input type="checkbox" id="CellSpecific" />
	        							<label>Common Parameter</label>
        							</div>
        							
        							<div class="easyui-paddingbfpx"><label class="mndlbl">Factory</label></div> 
                  					<div style="padding-bottom: 30px;"> 
                						<input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value=""  >
									</div>
									<div style="padding-bottom: 10px;"> 
                						<input id="cmbFactory1" name="cmbFactory1" class="easyui-combobox"  style="width:255px;" value=""  >
									</div>
        						</td>
        						<td>
        						<div class="easyui-paddingbfpx" style="padding-top:42px;"><label>Section</label></div> 
                  					<div class="easyui-paddingbfpx" > 
                						<input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:255px;" value=""  >
									</div>
									
										<div class="easyui-paddingbfpx"><label>Cell</label></div> 
               						<div style="padding-bottom: 10px;"> 
        	  	    					<input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:255px;" value=""  >
        	  	    					<input type="button" class="easyui-button" value ="View" id="View" onclick=""/>
										<input type="button" class="easyui-button" value ="Import Excel" id="ImportExcel" onclick=""/>
									</div>
									</td>
        					</tr>
       				
						</table>	
						</div>
				</td>
			</tr>
			<tr>
				<td>
					<div><label class="notes">Double Click Last Row Enter New Data</label></div>
					<div>
					<table id="EvaluationParam" style="width:100%"><tr><td/></tr></table>	
					<div id="pager"></div>			
				</div>		
				</td>
				<td>
					<div style="padding-bottom: 5px;"> 
        				<input type="button" class="easyui-button" value ="" id="up" onclick=""/>
        			</div>
        			<div style="padding-bottom: 5px;">
						<input type="button" class="easyui-button" value ="" id="doun" onclick=""/>
					</div>	
				</td>
			</tr>
	</table>
</div>