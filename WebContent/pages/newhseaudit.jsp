 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script>

jQuery("#newhse").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", Slno:1, Item:"note", Description:"cylinder",Maxpoints:"pune",Pointsscored:"2008-10-02",Grade:"M1",Remarks:"MAk"},
		  {id:"1", Slno:2, Item:"note", Description:"cylinder",Maxpoints:"pune",Pointsscored:"2008-10-02",Grade:"M1",Remarks:"MAk"},
		  {id:"1", Slno:3, Item:"note", Description:"cylinder",Maxpoints:"pune",Pointsscored:"2008-10-02",Grade:"M1",Remarks:"MAk"},
		  {id:"1", Slno:4, Item:"note", Description:"cylinder",Maxpoints:"pune",Pointsscored:"2008-10-02",Grade:"M1",Remarks:"MAk"},
		  
		
	  ],
	  colNames:[ 'Slno','Item','Description','Maxpoints','Pointsscored','Grade','Remarks'],
		colModel:[ {name:'Sl.no',index:'Sl.no',editable:false, width:60},
		           {name:'Item',index:'Item',editable:false, width:80},
				  	{name:'Description',index:'Description',editable:false, width:100},
				   {name:'Maxpoints',index:'Maxpoints',editable:false, width:80},
				   {name:'Pointsscored',index:'Pointsscored',editable:false, width:80},
				   {name:'Grade',index:'Grade',editable:false, width:100},
				   {name:'Remarks',index:'Remarks',editable:false, width:80},
				  
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

<div  align="center" class="cntborder" style="width: 100%;margin: 0 auto;">

	<table align="center" width="100%">
		<tr>
			<td width="50%">
				<div  >
				
				    <div class="easyui-paddingbfpx"><label>Safety Audit</label></div> 
                  	<div > 
                	<input id="cmbsftyaudit" name="cmbsftyaudit" class="easyui-combobox"  style="width:255px;" value=""  />
					</div>
					
				  <div class="easyui-paddingbfpx mndlbl"><label>Plant</label></div> 
                  <div > 
                  <input id="cmbPlant" name="cmbPlant" class="easyui-combobox"  style="width:255px;" value=""  />
                   </div>
					
					<div class="easyui-paddingbfpx"><label>Section</label></div> 
                  <div > 
                	 <input id="cmbsec" name="cmbsec" class="easyui-combobox"  style="width:255px;" value=""  />
					</div>
					<div class="easyui-paddingbfpx"><label>Cell</label></div> 
                  <div > 
                	<input id="cmbCell" name="cmbCell" class="easyui-combobox" style="width:255px;height : 21px;" value=""  />
					</div>
					<div class="easyui-paddingbfpx"><label>Equipment</label></div> 
                  <div > 
                	<input id="cmbeqp" name="cmbeqp" class="easyui-combobox"  style="width:255px;height : 21px;" value=""  />
					</div>
					<div class="easyui-paddingbfpx"><label>Location</label></div> 
                  <div > 
                	<input id="cmbloc" name="cmbloc" class="easyui-combobox"  style="width:255px;height : 21px;" value="" />
					</div>
				 </div>
				 
				 </td>
				
				 <td valign="top" width="50%">
				<div >
				
								<div class="easyui-paddingbfpx mndlbl" ><label>Template Name</label></div> 
		                  	<div > 
		                		<input id="cmbtmplname" name="cmbtmplname" class="easyui-combobox"  style="width:248px;height:21px;" value=""  />
								</div>
							<div class=" easyui-paddingbfpx mndlbl"><label>Audit Level</label></div> 
		                  	<div > 
		                	<input id="cmbaudlvl" name="cmbaudlvl" class="easyui-combobox"  style="width:248px;height:21px;" value=""  />
							</div>
								
								<div class="easyui-paddingbfpx mndlbl"><label>Auditor </label><span class="lblrrM" style="padding-left:65px; ">Name</span></div> 
								 <div > 
		                			 <input id="cmbDocno" name="cmbDocno" class="easyui-combobox"  style="width:85px;" value=""  />
										 
				                  <span class="floatR3" > 
				                  <input id="date" class="easyui-datebox" required="true"  style="width:143px;"/>
				                  </span>
				                 </div>
				                 <div class="easyui-paddingbfpx mndlbl"><label>Audit Date</label></div> 
				                 <div > 
				                	 <input id="date" class="easyui-datebox" required="true"  style="width:143px;"/>
								 </div>
		                  
							<div class="easyui-paddingbfpx  mndlbl"><label>Leader</label></div> 
		                 	<div > 
		                			 <input class="easyui-text"  id="ldr"  required="true" style=" width : 248px; height : 21px;"/>
						    </div>
						    <div class="easyui-paddingbfpx mndlbl"><label>Total Points</label></div> 
		                 	<div > 
		                			 <input  class="easyui-text"  id="ttlpts"  required="true" style=" width : 148px; height : 21px;"/>
						    </div>
						    
						 </div>
			
			</td>
		</tr>
		</table>
		
		
			<div class="sub-header "  > Safety Audit Details:<span style="float:right;">
				<input class="easyui-button" type="button" class="button" value="File Manager" id="Flmgr" />
				</span>
				</div>
				<div id="grid" style="padding-top:5px">
				<!--	End grid	 Script				-->    

				
					<table id="newhse" ></table>
					<div id="pager"></div>
										
					<!--	end of grid			-->
		</div>
		
		</tr>
		</td>
		

</table>
</div>
