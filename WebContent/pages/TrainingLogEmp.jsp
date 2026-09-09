<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
<script>

jQuery("#Employeedet").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Sl.No'/*'Select'*/,'Name','Code','Factory','Section','Department'],
		colModel:[ {name:'Sl.No',index:'Sl.No', editable:false, width:65},		          
		           /*{name:'my_checkbox',index:'my_checkbox', editable:true, editoptions:{value:"True:False" }, edittype:"checkbox", formatter:'checkbox',align: 'center', formatoptions: {disabled : false},width:50 },*/
				   {name:'Name',index:'Name',  width:160},
				   {name:'Code',index:'Code',  width:160},
				   {name:'Factory',index:'Factory',  width:160},
				   {name:'Section',index:'Section',  width:160},
				   {name:'Department',index:'Departmen',  width:160},	
			    ],
	rowNum:50,
	rowList:[10,20,30],
	rownumbers: true,
	multiselect:true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'',
	width:900,
	height:300,
	loadonce: true	
});

</script>
<div class="sub-cntborder" style="width: 980px;">
	<table>
	<tr>
			<td>
				<div class="sub-header"><label>Employee Details</label></div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="sub-cntborder" style="width: 520px;">
					<div class="sub-header">Find</div>
					<table>
						<tr>
							<td>
								<div>
									<div><label>Options</label></div> 
						      		<div style="padding-bottom: 10px;"> 
						       		<input id="cmbOptions" name="cmbOptions" class="easyui-combobox"  style="width:100px;" value=""  >
						       		<input id="txtHaving" class="easyui-text" name="txtHaing"  style="width:200px;" value=""  >
						       		<input type="button" class="easyui-button" value ="Find Next" id="FindNext" onclick=""/>
						       		<input type="button" class="easyui-button" value ="Close" id="close" onclick=""/>
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
				<div align="right" style="width: 975px;">
					<input type="button" class="easyui-button" value ="Filter" id="Filter" onclick=""/>
					<input type="button" class="easyui-button" value ="Find" id="Find" onclick=""/>
					<input type="button" class="easyui-button" value ="Ok" id="Ok" onclick=""/>
					<input type="button" class="easyui-button" value ="Cancel" id="Cancel" onclick=""/>
				</div>
			</td>
		</tr>
		<tr>
			<td>			
				<div>
					<table id="Employeedet" style="width:100%"><tr><td/></tr></table>	
					<div id="pager"></div>			
				</div>	
			</td>
		</tr>
	</table>	
</div>