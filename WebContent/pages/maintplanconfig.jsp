<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
						jQuery.noConflict();
						jQuery(document).ready(function(){	
							//alert('hi');
					
						jQuery("#list1").jqGrid({
								datatype: "local",
								colNames:[ 'Select','Code','Name','Week','Monthly','Quarterly','Half Yearly','Yearly','2 Years','3 Years','4 Years','5 Years','6 Years','7 Years','8 Years','9 Years','10 Years'],
								colModel:[ {name:'select',index:'select',editable:false, width:60},
										   {name:'code',index:'code',editable:false, width:60},
										   {name:'mpname',index:'mpname',editable:false, width:150},
										   {name:'week',index:'week',editable:false, width:60},
										   {name:'monthly',index:'monthly',editable:false, width:60},
										   {name:'quarterly',index:'quarterly',editable:false, width:60},
										   {name:'halfyearly',index:'halfyearly',editable:false, width:60},
										   {name:'yearly',index:'yearly',editable:false, width:60},
										   {name:'2yrs',index:'2yrs',editable:false, width:60},
										   {name:'3yrs',index:'3yrs',editable:false, width:60},
										   {name:'4yrs',index:'4yrs',editable:false, width:60},
										   {name:'5yrs',index:'5yrs',editable:false, width:60},
										   {name:'6yrs',index:'6yrs',editable:false, width:60},
										   {name:'7yrs',index:'7yrs',editable:false, width:60},
										   {name:'8yrs',index:'8yrs',editable:false, width:60},
										   {name:'9yrs',index:'9yrs',editable:false, width:60},
										   {name:'10yrs',index:'10yrs',editable:false, width:60},
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
								width:990,
								height:180,
								loadonce: true
							});
						});
</script>
<div id="wrapper">
<div class="main-cntborder">

<table  border="0" class="tablealign-center">
		<tr>
<!--left  pane -->
			<td style="width:30%" valign="top">
			<div style="float:left;padding-right:5px;">
			
			   <div  class="easyui-paddingbfpx">
                    <label>Factory</label>                       
               </div> 
			   <div class="easyui-paddingbfpx"> 
			        <input id="cmbfact" name="cmbfact" class="easyui-combobox"  style="width:255px;" value=""  >                      
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label>Section</label>                       
                </div> 
			     <div class="easyui-paddingbfpx"> 
			         	<input id="cmbsect" name="cmbsect" class="easyui-combobox"  style="width:255px;" value=""  >                       
			     </div>
			   
			     <div  class="easyui-paddingbfpx">
                    <label> Cost Center</label>                       
                 </div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbcostcen" name="cmbcostcen" class="easyui-combobox" style="width:255px;"/ >                       
			     </div>
			 </div>
			</td>
			 <td valign='top' style="width:30%">
			 
			 	<div  class="easyui-paddingbfpx">
                    <label> Equipment</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			          <input id="cmbeqpt" name="cmbeqpt" class="easyui-combobox"  style="width:255px;" value=""  >                     
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl"> Cell</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			         <input  id="cmbcell" name="cmbcell" class="easyui-combobox" style="width:255px;"/ >                       
			   </div>
			
			 <div  class="easyui-paddingbfpx">
                  	<label>Week No</label>
                   	<span  style="margin-left: 78px;"><label>Next Due</label></span>
             </div> 
			 <div class="easyui-paddingbtpx"> 
			       <input type="text" id="weekno" name="weekno"  class="easyui-text" style="width:110px;" value="" / >
			        <span  style="margin-left: 32px;"> 
			            <input id="nextduedate" name="nextduedate" class="easyui-datebox" required="true" style="width:110px;"/>
			        </span> 
			 </div>
		
		 
		 
		  	
		   </td>
		   <td valign='top' style="">
							
		   <div  style="padding-top:20px;" class="easyui-paddingbtpx">
					<input id="applytoall" type="checkbox"/> <span style="padding-right:2px;"><label>Apply to all</label></span>
					<input id="applytonew" type="checkbox"/> <span><label>Apply to new plan</label></span>
		     </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label> Options</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			         <select id="options" class="easyui-combobox" name="options" style="width:130px;" required="true">
									<option value="-"> Equipment</option>
						</select> 
						<span  style="font-weight:bold;padding-left:15px;"> 
								<img src="images/blue-circle.png" height="18px"/>&nbsp;&nbsp;&nbsp;&nbsp;Standard Not Available
						</span>
			   </div>
				
			 <div  style="padding-top:20px;" class="easyui-paddingbtpx">
                         <input id="equipwise" type="checkbox"/> <label>Equipment Wise</label>
                  		 <span  style="margin-left: 2px;">  <input id="pillarwisechkbox" type="checkbox"/> <label>Assembly Wise</label></span>
                  		 <span  style="margin-left: 2px;">  <input type="text" id="manual" name="manual"  style="width:50px;" value="" class="easyui-text" / > <label>Manual Entry</label></span><br/><br/>
				</div>
		</td>
		</tr>
		</table>
		<div class="sub-header"> Enter Week No and specify due date in early column
		   <span style="float:right;padding-right:70px;">
			  	<input type="button" id="view"  class="easyui-button" onclick="" value="View"/>
			    <input type="button" id="applynextdue"  class="easyui-button" onclick="" value="Apply Next Due"/>
				<input type="button" id="woresp"  class="easyui-button" onclick="" value="WO Responsibilty"/>
				 <input type="button" id="excel"  class="easyui-button" onclick="" value="Export To Excel"/>
   		   </span>
		</div>
		
		
				<table id="list1" style="width:100%"><tr><td/></tr></table>
				<div id="pager1"></div>
				<div class="clearfix"></div>
		
</div>			

</div>