
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>
jQuery('#cmbEquipGrp').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

jQuery('#cmbFact').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
}); 

jQuery('#cmbMainGrp').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
});

jQuery('#cmbSubGrp').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
});


jQuery("#JobHazrdAnlys").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {SNo:"EQUIPMENT", Area:"",JobStepsTask:"ADD/VIEW EQUIPMENT", Description:"" , HazardType:"",Hazard:" ",Descrioption:" "},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},
		  
		  		
	  ],
	  colNames:[ 'S.No','Area','Job Steps/Task','Description','Hazard Type','Hazard','Description'],
		colModel:[ {name:'S.No',index:'S.No', editable:false, width:40},		          
				   {name:'Area',index:'Area',editable:false, width:100},
				   {name:'Job Steps/Task',index:'Job Steps/Task',  width:150},
				   {name:'Description',index:'Description',editable:false, width:125},		          
				   {name:'Hazard Type',index:'Hazard Type',editable:false, width:100},
				   {name:'Hazard',index:'Hazard', width:160},
				   {name:'Description',index:'Description', width:160},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Job Hazard Analysis',
	width:900,
	height:100,
	loadonce: true
	
	
});

jQuery("#OpperContol").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"EQUIPMENT", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Control Type','Control','Control Measures'],
		colModel:[ {name:'Control Type',index:'Control Type', editable:false, width:250},		          
				   {name:'Control',index:'Control',editable:false, width:240},
				   {name:'Control Measures',index:'Control Measures',  width:180},				   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager2', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Oppornitunites for control',
	width:900,
	height:100,
	loadonce: true	
});

jQuery("#RelevantInfo").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {RevelantInformation:"Save Operating Procedures", Description:"",Document_Reference:"ADD/VIEW EQUIPMENT", FileAttachments:"	ADD FILE			"},
		  {RevelantInformation:"Save Operating Procedures", Description:"",Document_Reference:"ADD/VIEW EQUIPMENT", FileAttachments:"	ADD FILE			"},
		  {RevelantInformation:"Save Operating Procedures", Description:"",Document_Reference:"ADD/VIEW EQUIPMENT", FileAttachments:"	ADD FILE			"},
		  {RevelantInformation:"Save Operating Procedures", Description:"",Document_Reference:"ADD/VIEW EQUIPMENT", FileAttachments:"	ADD FILE			"},	  		
	  ],
	  colNames:[ 'Revelant Information','Description','Document Reference','FileAttachments'],
		colModel:[ {name:'Revelant Information',index:'Revelant_Information', editable:false, width:200},		          
				   {name:'Description',index:'Description',editable:false, width:100},
				   {name:'Document Reference',index:'Document_Reference',  width:200},	
				   {name:'FileAttachments',index:'FileAttachments',formatter: actionFormatter,editable:false, width:260},			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager3', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Revelent Information',
	width:800,
	height:100,
	loadonce: true	
});

function actionFormatter(cellvalue, options, rowObject) 
{
    return "<input type='button' class=\"easyui-button\" onclick=\"\" value="+ cellvalue +"></input>" ;
}


jQuery("#AddPPE").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'S.No','Catagory','Image','Description of Productive Equipments'],
		colModel:[ {name:'S.No',index:'S.No', editable:false, width:150},		          
				   {name:'Catagory',index:'Catagory',editable:false, width:200},
				   {name:'Image',index:'Image',  width:200},	
				   {name:'Description of Productive Equipments',index:'Description of Productive Equipments',editable:false, width:300},			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager4', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Additional PPE',
	width:900,
	height:100,
	loadonce: true	
});

jQuery("#TrainingInfo").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'S.No','Date','Department','Grade','Employee No','Employee Name','Signature'],
		colModel:[ {name:'S.No',index:'S.No', editable:false, width:65},		          
				   {name:'Date',index:'Date',editable:false, width:100},
				   {name:'Department',index:'Department',  width:160},	
				   {name:'Grade',index:'Grade',editable:false, width:100},	
				   {name:'Employee No',index:'Employee No', editable:false, wCountth:130},		          
				   {name:'Employee Name',index:'Employee Name',editable:false, width:100},
				   {name:'Signature',index:'Signature',  width:160},			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager5', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Training Infomation',
	width:900,
	height:100,
	loadonce: true	
});


</script>
<div class="main-cntborder" style="height: 490px">

<div>
<table width="100%">
<tr>
<td width="33%" style="padding-left: 40px;" valign="top">
<div  >JHA</div>
<div class="easyui-paddingbfpx"> 
<input id="cmbJha" name="cmbJha" class="easyui-combobox"  style="width:255px;" value=""  ></div>

<div ><label class="mandatory-lbl">Applies to</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbApply" name="cmbApply" class="easyui-combobox"  style="width:255px;" value=""  ></div>

<div  ><label class="mandatory-lbl"> Job Type</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbJob" name="cmbJob" class="easyui-combobox"  style="width:255px;" value=""  ></div>
<div  ><label class="mandatory-lbl">Description</label></div>
<div class="easyui-paddingbfpx">
<textarea rows="1" style="width:255px;resize:none;" cols="" id="desc" name="desc"></textarea></div>


</td>
       
<td width="33%" valign="top">   
        
<div>  <label> JHA No</label></div>
<div class="easyui-paddingbfpx" >
<input id="JHANo" type="text" class="easyui-text"; name="JHANo" value="" style="width: 255px; height : 21px;" ></div>

<div  ><label>Department</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbDept" name="cmbDept" class="easyui-combobox"  style="width:255px;" value=""  ></div>

<div ><label>Overall Responsibility</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbrespon" name="cmbrespon" class="easyui-combobox"  style="width:255px;" value=""  ></div>
</td>

<td width="33%" valign="top">
<div  ><label>Highest Loss Potential </label>
<span style="padding-left:10px;">Date</span></div>
<div class="easyui-paddingbfpx">
<input id="cmbrespon" name="cmbrespon" class="easyui-combobox"  style="width:150px;" value=""  >
<span style="padding-left:10px;">
<input id="Effective" class="easyui-datebox" ></input></div>
</span>
<div  ><label class="mandatory-lbl">Prepared By</label>
<span style="padding-left:80px;">Date</span></div>
<div class="easyui-paddingbfpx">
<input id="cmbrespon" name="cmbrespon" class="easyui-combobox"  style="width:150px;" value=""  >
<span style="padding-left:10px;">
<input id="Effective" class="easyui-datebox" ></input></div>
</span>
<div  ><label>Approved By</label>
<span style="padding-left:78px;"><label>Date</label></span></div>
<div class="easyui-paddingbfpx">
<input id="cmbrespon" name="cmbrespon" class="easyui-combobox"  style="width:150px;" value=""  >
<span style="padding-left:10px;">
<input id="Effective" class="easyui-datebox" ></input></div>
</span>

</td>

</tr>
</table>

</div>

<div style="padding-left: 40px;"><b><label style="vertical-align: top;" class="notes">Press Enter to Insert Row</label></b> 
<img  src="images/circle2.bmp"></div>

<div title="Tab5 with sub tabs" closable="true" iconCls="icon-cut" style="padding-left: 40px;width:980px;">
			<div class="easyui-tabs" fit="true" plain="true" style="height:230px;width:800px;">
				<div title="Activity" style="padding:10px;">
				
				
				<table id="JobHazrdAnlys" style="width:100%">
				<tr><td/></tr></table>
				<div id="pager"></div>
				
				</div>
				
				<div title="Opportunites For Control" style="padding:10px;">
				
				<table id="OpperContol" style="width:100%">
				<tr><td/></tr></table>
				<div id="pager2"></div>
				
				</div>
				
				<div title="Relevant Info" style="padding:10px;">
				
				<table id="RelevantInfo" style="width:100%">
				<tr><td/></tr></table>
				<div id="pager3"></div>
				
				</div>
				
				<div title=" Additional PPE" style="padding:10px;">
				
				<table id="AddPPE" style="width:100%">
				<tr><td/></tr></table>
				<div id="pager4"></div>
				
				</div>
				
				<div title="Training Info" style="padding:10px;">
				
				<table id="TrainingInfo" style="width:100%">
				<tr><td/></tr></table>
				<div id="pager5"></div>
				
				</div>
				
			</div>
</div>



</div>

