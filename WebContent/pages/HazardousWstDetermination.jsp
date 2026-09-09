<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>


jQuery('#Frmno').combobox({
	mode:'remote',
	url:'combobox_data.comp',
	valueField:'id',
	textField:'text'
});
jQuery("#Constituents").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Select','Metal Content','State','Remarks'],
		colModel:[ {name:'Select',index:'Select', editable:false, width:150},		          
				   {name:'Metal Content',index:'Metal Content',editable:false, width:200},
				   {name:'State',index:'State',  width:200},	
				   {name:'Remarks',index:'Remarks',editable:false, width:100},			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Constituents',
	width:450,
	height:100,
	loadonce: true	
});

jQuery("#WasteProper").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Select','Physical State','Select','Ph','Select','FlashPoint'],
		colModel:[ {name:'Select',index:'Select', editable:false, width:65},		          
				   {name:'Physical State',index:'Physical State',editable:false, width:100},
				   {name:'Select',index:'Select',  width:160},	
				   {name:'Ph',index:'Ph',editable:false, width:100},	
				   {name:'Select',index:'Select',  width:160},	          
				   {name:'FlashPoint',index:'FlashPoint',editable:false, width:100},
				   			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Waste Properties',
	width:450,
	height:100,
	loadonce: true	
});

jQuery("#Characteristics").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Select','characteristics','Select','Asbestos Content','Select','PCB Content'],
		colModel:[ {name:'Select',index:'Select', editable:false, width:65},		          
				   {name:'characteristics',index:'characteristics',editable:false, width:100},
				   {name:'Select',index:'Select',  width:160},	
				   {name:'Asbestos Content',index:'Asbestos Content',editable:false, width:100},	
				   {name:'Select',index:'Select',  width:160},	          
				   {name:'PCB Content',index:'PCB Content',editable:false, width:100},
				   			   
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Characteristics',
	width:500,
	height:100,
	loadonce: true	
});

jQuery("#Characteristics").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Constituent','Volume','Volume%','Remarks'],
		colModel:[ {name:'Constituent',index:'Constituent', editable:false, width:65},		          
				   {name:'Volume',index:'Volume',editable:false, width:100},
				   {name:'Volume%',index:'Volume%',  width:160},				  
				   {name:'Remarks',index:'Remarks',  width:160},  
				      
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Characteristics',
	width:900,
	height:100,
	loadonce: true	
});

jQuery("#Evaluation").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Analysis','Yes/No','Remrks'],
		colModel:[ {name:'Analysis',index:'Analysis', editable:false, width:85},		          
				   {name:'Yes/No',index:'Yes/No',editable:false, width:110},				   			  
				   {name:'Remarks',index:'Remarks',  width:180},  
				      
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Evaluation',
	width:460,
	height:100,
	loadonce: true	
});

jQuery("#Hazardous").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Classification','Yes/No','Remarks'],
		colModel:[ {name:'Classification',index:'Classification', editable:false, width:115},		          
				   {name:'Yes/No',index:'Yes/No',editable:false, width:100},				   			  
				   {name:'Remarks',index:'Remarks',  width:160},  
				      
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Hazardous Classification',
	width:460,
	height:100,
	loadonce: true	
});

jQuery("#CheckList").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {parameter:"Save Operating Procedures", Count:"",Link:"ADD/VIEW EQUIPMENT", Parameter1:"PMSTANDARDS" , Count:"",Link1:"ADD PMSTANDARDS"},
		  {parameter:"ASSEMBLY", Count:"",Link:"     ADD ASSEMBLY     ", Parameter1:"SPARES", Count:"",Link1:"      ADD SPARES      "},
		  {parameter:"SUB ASSEMBLY", Count:"",Link:" ADD SUB ASSEMBLY1", Parameter1:"ALARMS",Count:"",Link1:"ADD/VIEW ALARMS " },
		  {parameter:"BREAKDOWN PHENOMENA", Count:"",Link:"   ADD PHENOMENA   ", Parameter1:"CAUSE", Count:"",Link1:"      ADD CAUSE        "},		  		
	  ],
	  colNames:[ 'Can the work operation generate a radioactive waste (i.e., activation or radioactive contamination of a metrial/substance)?','Yes','No'],
		colModel:[ {name:'Classification',index:'Classification', editable:false, width:900},		          
				   {name:'Yes/No',index:'Yes/No',editable:false, width:50},				   			  
				   {name:'Remarks',index:'Remarks',  width:50},  
				      
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Check List',
	width:1050,
	height:100,
	loadonce: true	
});

</script>

<div align="center" class="cntborder" style="height: 600px">

<div style="padding-top:5px;padding-bottom:5px;">
<table width="100%">
<tr>
<td width="33%" style="padding-left: 50px" valign="top">
<div ><label>Form No</label></div>
<div class="easyui-paddingbfpx" >
<input id="Frmno" name="Frmno" class="easyui-combobox"  style="width:120px;" value=""  >
<input id="Effective" class="easyui-datebox" ></input></div>

<div  class="mandatory-lbl" > <label>Waste Description</label></div>
<div class="easyui-paddingbfpx">
<textarea rows="1" style="width: 255px; resize:none;" cols="" id="Waste" name="Waste"></textarea></div>


 </td>
       
       <td width="33%" valign="top">   
      
      <div  class="mandatory-lbl" ><label>GenerationProcess</label></div>
<div class="easyui-paddingbfpx">
<textarea rows="1" style="width: 255px;resize:none;" cols="" id="GenProcess" name="GenProcess"></textarea></div>
  
<div ><label>Generated By</label></div>
<div class="easyui-paddingbfpx">
<input id="GenBy" type="text" class="easyui-text"; name="GenBy" value="" style="width: 255px; height : 21px;" ></div>

<div ><label> Location</label></div>
<div class="easyui-paddingbfpx">
<input id="Loc" type="text" class="easyui-text";  name="Loc" value="" style="width: 255px; height : 21px;" ></div>




</td>

<td width="33%" valign="top">

<div ><label>Process</label> </div>
<div class="easyui-paddingbfpx">
<input id="Process" type="text"  class="easyui-text"; name="Process" value="" style="width: 255px; height : 21px;" ></div>

<div > <label>Total Qty Generated</label></div>
<div class="easyui-paddingbfpx">
<input id="TotQtyGen" type="text"  class="easyui-text"; name="TotQtyGen" value="" style="width:255px ; height : 21px;" ></div>

<div ><label>Estimated Generation Rate</label></div>
<div class="easyui-paddingbfpx">
<input id="EstGenRate" type="text" class="easyui-text"; name="EstGenRate" value="" style="width: 255px; height : 21px;" ></div>

</td>
</tr>
</table>

</div>
<div title="Tab5 with sub tabs" closable="true" iconCls="icon-cut" style="padding:10px; height : 475px;width:1175px;">
			<div class="easyui-tabs" fit="true" plain="true" style="height:350px;width:800px;">
				<div title="Identification" style="padding:10px;">
				
				<table width="100%" >
				<tr >
				<td width="50%" valign="top">
				<div   class="sub-header">
				<b><label> Reasons For Generations</label></b></div>
				<textarea rows="1" style="width: 255px; resize:none;"  cols="" id="GenProcess" name="GenProcess" ></textarea>
				
				<div class="easyui-paddingbfpx" style=" padding-right:120px;" >
				<table id=WasteProper ></table>
				<div id="pager"></div>
				</div>
				
				<div class="easyui-paddingbfpx" style=" padding-right:120px;">
				<table id= Constituents></table>
				<div id="pager"></div>
				</div>
				
				<div ><label>Requested By</label>
				 <span style="padding-left:25px;"> Date </span> </div>
				<div class="easyui-paddingbfpx">
				<input id="ReqBy" name="ReqBy" class="easyui-combobox"  style="width:110px;" value=""  >
				<input id="Effective" class="easyui-datebox" ></input>
				</div>
				
				
				</td>
				<td width="50%" valign="top">
				
				<div  class="sub-header">
				<b><label  > Remarks</label></b></div>
				<textarea rows="1" style="width: 255px; resize:none;"  cols="" id="remarks" name="remarks" ></textarea>
				
				<div class="easyui-paddingbfpx" style=" padding-right:120px;">
				<table id= Characteristics></table>
				<div id="pager"></div>
				</div>
				
				<div  >
				<b> <label class="sub-header">Composition(all constituents including debris, any absorbent, freestanding liquid or absorbed liqued)</label></b></div>
				
				<div ><label>Department</label></div>				
				<div class="easyui-paddingbfpx">
				<input id="Dept" name="Dept" class="easyui-combobox"  style="width:255px;" value=""  ></div>
				
				<div ><label>  Contact</label> </div>
				<div class="easyui-paddingbfpx"><input id="Cont" type="text" class="easyui-text"; name="Cont" value="" style="width: 255px; height : 21px;" >
				</div>
				
				</td>
				</tr>
				</table>
				</div>
				
				<div title="Evaluation" style="padding:5px;">
				<table width="100%" >
				<tr>
				<td width=50% valign="top">
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label>Duration of Generation</label>	</div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input type="checkbox" name="dg" /><label>One Time</label>
				<input type="checkbox" name="dg" /><label>Continuous</label>
				<input type="checkbox" name="dg" /><label>Others			</label>	
				<input Type="text" class="easyui-text"; width="10px"/></div>
				<div style="padding-top:5px;padding-bottom:5px;">
				<table id= Evaluation></table>
				<div id="pager"></div></div>
				
				<div class="sub-header">
				<label>Alternate Methods/Meterials to avoid generation of Waste</label></div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<textarea rows="1" style="width: 255px; resize:none;"  cols="" id="GenProcess" name="GenProcess" ></textarea></div>
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label>Transfered to WMG</label></div>
					<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input type="checkbox" name="WMG"/><label>Yes</label>
				<input type="checkbox" name="WMG"/><label>No</label>
				<input type="checkbox" name="WMG"/><label>Others</label>
				<input Type="text" class="easyui-text"; width="10px"/>
				</div>
				
				<div  >
				<label class="sub-header">Proposed Date for resolving the issue preventing treatment/disposal of waste</label></div>
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label>Responsibility</label>
				 <span style="padding-left:25px;"> TargetDate </span> </div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input id="respon" name="respon" class="easyui-combobox"  style="width:105px;" value=""  >
				<input id="Effective" class="easyui-datebox" ></input>
				</div>
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label>Analysed By</label>
				 <span style="padding-left:25px;"> Date </span> </div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input id="respon" name="respon" class="easyui-combobox"  style="width:105px;" value=""  >
				<input id="Effective" class="easyui-datebox" ></input>
				</div>
				
				</td>
				<td width="50%" valign="top">
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label>EPA Waste Code</label>
				 <span style="padding-left:25px;"> <label>CA Waste Code</label> </span> </div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input id="EPAWstCode" type="EPAWstCode" class="easyui-text" name="Cont" value="" style="width: 120px; height : 21px;" >
				<input id="CAWstCode" type="CAWstCode" class="easyui-text" name="Cont" value="" style="width: 100px; height : 21px;" >
				</div>
				
				<div class="easyui-paddingbfpx">
				<table id= Hazardous></table>
				<div id="pager"></div></div>
				
			
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;padding-top:10px;">
				<label class="sub-header">Reasons/Issue Preventing the treatment/disposal of the waste</label></div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<textarea rows="1" style="width: 255px; resize:none;"  cols="" id="resIssue" name="resIssue" ></textarea>
				</div>
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;"><label> Remarks</label></div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<textarea rows="1" style="width: 255px; resize:none;"  cols="" id="remarks" name="remarks" ></textarea></div>
				
				<div class="easyui-paddingbfpx" style="padding-left:55px;">Department
				<span style="padding-left:25px;"> <label>Contact </label></span> </div>
				<div class="easyui-paddingbfpx" style="padding-left:55px;">
				<input id="Dept" name="Dept" class="easyui-combobox"  style="width:105px;" value=""  >
				<input id="Cont" type="text" class="easyui-text";  name="Cont" value="" style="width: 100px height : 21px;" >
				</div>
				
				</td>
				</tr>
				</table>
				
				</div>
				
				<div title="Check List" style="padding:10px;">
				
				<div class="easyui-paddingbfpx">
				<table id= CheckList></table>
				<div id="pager"></div></div>
				
				</div>
				
				
				
			</div>
</div>



</div>

