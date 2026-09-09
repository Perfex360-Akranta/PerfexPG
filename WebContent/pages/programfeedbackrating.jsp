
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery( "#allmenus" ).click(function() {
	jQuery("#programfeedback").load('all_menus.creat', function(response, status, xhr) {
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  }
	});	
});
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			jQuery("#list").jqGrid({
						datatype: "local",
						 colNames:[ 'Sl.No','SKILL LEVEL','SKILL DESCRIPTION','MIN PERCENTAGE','MAX PERCENTAGE'],
								     colModel:[ {name:'Sl.No',index:'Sl.No', editable:false, width:65},		          
										   {name:'SKILL LEVEL',index:'SKILL LEVEL',editable:false, width:100},
										   {name:'SKILL DESCRIPTION',index:'SKILL DESCRIPTION',  width:160},	
										   {name:'MIN PERCENTAGE',index:'MIN PERCENTAGE',editable:false, width:160},
										   {name:'MAX PERCENTAGE',index:'MAX PERCENTAGE',editable:false, width:160},
							                ],
   							    data:[
									  {id:"1", kaizenDate:"2007-10-01",pillarName:"KOBETSU KAIZEN", pillarCode:"KK", closed:true},
									  {id:"2", kaizenDate:"2007-10-02",pillarName:"QUALITY MAINTENANCE", pillarCode:"QM",closed:false},
									  {id:"3", kaizenDate:"2007-09-01",pillarName:"EDUCATION AND TRAINING", pillarCode:"ET",closed:false},
									  {id:"4", kaizenDate:"2007-10-04",pillarName:"SAFETY,HEALTH AND ENVIRONMENT", pillarCode:"SHE",closed:true },
									  {id:"5", kaizenDate:"2007-10-31",pillarName:"OFFICE TPM", pillarCode:"OTPM",closed:false},
									  {id:"6", kaizenDate:"2007-10-01",pillarName:"DEVELOPEMENT MANAGEMENT", pillarCode:"DM", closed:true},
									  {id:"7", kaizenDate:"2007-10-02",pillarName:"JISHU HOZEN", pillarCode:"JH", closed:false},
									  {id:"8", kaizenDate:"2007-09-01",pillarName:"PLANNED MAINTENANCE", pillarCode:"PM",closed:false},
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
								caption:'',
								width:700,
								height:250,
								loadonce: true,
								gridComplete: function()
								{ 
									var ids = jQuery("#list").jqGrid('getDataIDs'); 
									for(var i=0;i < ids.length;i++)
										{ 
											var cl = ids[i]; 
											be = "<input  id='savechk"+i+"' type='checkbox'/>";
											jQuery("#list").jqGrid('setRowData',ids[i],{save:be});
											//jQuery("#list").setCaption("<input  style='float:right;' class='button' type='button' value='All Menus'/>");
										 }
								}
							});
		});
		</script>

<div  align="center" class=" main-cntborder">
<div class="sub-header"> Program Skill Details </div>
	<div id="program skill" >
	<table  width="70%">
	
		<tr>
<!--left  pane -->
			<td >
			
					<div class="easyui-paddingbfpx" style="padding-left:200px;" ><label class="mandatory-lbl"> Skill Level </label></div> 
					<div class="easyui-paddingbfpx" style="padding-left:200px;" >
						<input class ="easyui-text"  id="skilllevel" name="skilllevel" style="width:255px;"   >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:200px;" ><label class="mandatory-lbl"> Skill Description </label></div> 
					<div class="easyui-paddingbfpx" style="padding-left:200px;" >
						<input class ="easyui-text" id="skilldesc" name="skilldesc"  style="width:255px;"   >
					</div>
					
						</td>
			<td style="width:0%" valign="top">
			
					<div class="easyui-paddingbfpx" style="padding-left:150px;" ><label>Min Percentage </label></div> 
					<div class="easyui-paddingbfpx" style="padding-left:150px;" >
						<input class ="easyui-text"  id="minper" name="minper"  style="width:105px;"   >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:150px;" ><label>Max Percentage </label></div> 
					<div class="easyui-paddingbfpx" style="padding-left:150px;" >
						<input class ="easyui-text" id="maxper" name="maxper"  style="width:105px;"  >
					</div>
					
					</td>
		</tr>
	</table>
	<div style="float:right;padding-right:200px">
		<input type="button" id="clear"  class="easyui-button" onclick="" value="Clear"/>
		<input type="button" id="insert"  class="easyui-button" onclick="" value="Insert"/>
	</div>
	<div style="padding-top:20px;"> 
	 <table id="list" style="width:100%"><tr></td></tr></table>
	<div id="pager"></div>
	</div>
</div></div>