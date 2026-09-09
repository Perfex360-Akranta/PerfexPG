<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script>
			jQuery.noConflict();
						jQuery(document).ready(function(){	
							//alert('hi');
					
						jQuery("#list1").jqGrid({
								datatype: "local",
								colNames:[ 'Trade','Job Type','Frequency','Activity','Designation','Responsibility'],
								colModel:[ {name:'aumTrade',index:'Trade',editable:false, width:100},
										   {name:'aumJobType',index:'JobType',editable:false, width:100},
										   {name:'aumFrequency',index:'Frequency',editable:false, width:100},
										   {name:'aumActivity',index:'Activity',editable:false, width:300},
										   {name:'aumDesignation',index:'Designation',editable:false, width:100},
										   {name:'aumResponsibility',index:'Responsibility',editable:false, width:150},
										  ],
   							    data:[
									  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
									  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
									  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
									  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
									  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
									  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
									  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
									  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
									  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
									  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}
									
								  ],		  
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'',
								width:950,
								height:250,
								loadonce: true
								
								/*ondblclick: function(id){alert("view clicked");
									var dataString = 'f_date_from='+ f_date_from +'&f_date_to='+ f_date_to;
									
									jQuery("#list2").setGridParam({url:'FiveW2HRpt_view.fwh?'+dataString,datatype:'xml'}).trigger('reloadGrid');
									alert(setGridParam());
									 }
								*/ 
					
					}); 
							
				});


		function replace()
		 {
			document.getElementById("griddiv").style.display="none";
			document.getElementById("contentdiv").style.display="block";
		}
		function back() 
		{
			document.getElementById("contentdiv").style.display="none";
			document.getElementById("griddiv").style.display="block";
			
		}

</script>



<div style="margin: 20px">
		<div style="float: left;padding-right: 180px;">&nbsp</div>
	<div style="float: left;padding-right: 160px">	
		<div style="margin-top: 12px;"><label class="mandatory-lbl">Factory</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Section</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Cell</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
	</div>	
	
	<div style="float: left;">	
		<div style="margin-top: 12px;"><label>Equipment</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Assembly</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label class="mandatory-lbl">Week</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 150px;"/></div>
	</div>	
	
</div><!-- End of Fixed div -->

<div style="clear: both;"></div>

	<div style="float: left;padding-right: 22px;">&nbsp;</div>
	 	<div id="griddiv" style="float: left;margin:0px;"><!-- Start of Grid -->
			
				<div  style=" width : 1100px;padding-top:5px;">
					<span style="padding-right: 5%;">&nbsp;</span>
					<span class="notes" style="padding-right: 392px;margin-top: 2px;padding-top:5px;">	Double Click on the Activity to edit the Standard</span>
					<span>	<input type="button" value="New Standard" id=newstand" class="easyui-button" onclick="replace()" /></span>
					<span>	<input type="button" value="View" id="view" class="easyui-button" /></span>
					<span>	<input type="button" value="Export to Excel" class="easyui-button" id="exportexcel" /></span>
					</div>
					
					<div><table id="list1" width="400px" style="float: left;"></table> </div>
							<div id="pager1"></div>  
			
				</div> <!-- End Of Grid -->
									
			
			
				

		<div class="" id="contentdiv" style="margin-top: 12px;margin: 12px;display:none;">
				<table class="sub-cntborder">
					<tr>
						<td class="valigncnt" style="width:50%">
				
					<div class="floatright">
							<div style="margin-top: 8px;">
									<input type="button" id="back" value="Back" class="easyui-button" onclick="back()" style="height: 22px; width : 66px;" />
							</div>
					</div>
				
				<div style="float: left;padding-left: 95px;">&nbsp;</div>
				<div class="div-border" style="float: left;margin-top: 12px;padding-right: 13%">
				  <div style="margin:15px;padding-left: 15%">
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Job Type</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 180px;"/></div>
						<div style="margin-top: 8px;"><label>Equip. Condition</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 180px;"/></div>
						<div style="margin-top: 8px;"><label>No of Year</label><label class="notes">(s)</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-text"  style="width: 120px;height: 21px;"/></div>
						<div style="margin-top: 8px;"><label>How Much</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-text"  style="width: 120px;height: 21px;"/><label class="notes">(Duration in minutes)</label></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Next Due</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-datebox" style="width: 120px;"/></div>
						<div style="margin-top: 8px;"><label>Effective from</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-datebox" style="width: 120px;"/></div>
						<div style="margin-top: 8px;"><label>Maintenance Section</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Designation</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label>Responsibility</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">What</label><label class="mandatory-lbl notes">(Activity)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
				  </div>
				</div><!-- left side -->
				
				<div class="div-border" style="float: left;margin-top: 12px;">
				   <div style="margin:12px;">
						
						<div style="margin-top: 8px;"><label class="mandatory-lbl">How Method</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea>
							 <input type="button" class="easyui-button" value="..." style="height: 21px; width : 42px;"/>
						</div>
						<div style="margin-top: 8px;"><label>Where</label><label class="notes">(Location)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label>What</label><label class="notes">(Standard)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label>Observation</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Prepared By</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
                   </div>				
				</div><!-- right side -->
					
				</td>
			</tr>
		</table>
		</div>
	
	
<!--




</div> Outermost div  

</div>-->