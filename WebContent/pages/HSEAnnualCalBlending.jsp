 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">


jQuery(document).ready(function(){	
	

	jQuery("#blendingGrid").jqGrid({
		url:'',
		datatype: "local",
		data:[
			  {id:"1", Factory:1, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"2", Factory:2, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"3", Factory:3, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"4", Factory:4, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},

		  ],
		  colNames:[ 'Trade','Job Type','Freq','Activity','Responsibility'],
			colModel:[ {name:'Trade',index:'Trade',editable:false, width:160},
			           {name:'JobType',index:'JobType',editable:false, width:180},
					  	{name:'Freq',index:'Freq',editable:false, width:150},
					   {name:'Activity',index:'Activity',editable:false, width:180},
					   {name:'Responsibility',index:'Responsibility',editable:false, width:150},
					   
					  
					  ],
		rowNum:50,
		rowList:[5,10,20],
		rownumbers: true,
		shrinkToFit:false,
		pager: '#pager', 
		sortname: 'id',
		viewrecords: true,
		sortorder: "asc", 
		caption:'HSE Audit Sheet',
		width:900,
		height:250,
		loadonce: true
		
		
	});	
	
	jQuery( "#close" ).click(function() {
		//alert("close");
		jQuery("#HseAcl" ).show();
		jQuery("#blending").hide();
		
	});	
});	

</script>
<div id="blending">
<div class="main-header" style="text-align:center;margin-bottom:50px;border-width:thin;"> Blending Unit-[BPU216235]</div>
<div id="Acl" align="center" class="main-cntborder">

	<table align="center">
		<tr>
			<td>
			<div>
				<div style="padding-right:620PX;">
				
				
				</div>
				<div style="float: right;padding-bottom: 50px;">
				
				<input type="button"  value="Close" id="close" class="easyui-button"/>
				
				</div>	
				
			</div>	
				
				<div  style="padding-top:30px">
				
					<table id="blendingGrid" style="width:100%"></table>	
					<div id="pager"></div>								
				
				
				</div>	
			</td>
		</tr>
</table>
						
	    </div>
</div>		
						
				
