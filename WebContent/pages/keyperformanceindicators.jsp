<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery( "#allmenus" ).click(function() {
	jQuery("#keyperformance").load('all_menus.creat', function(response, status, xhr) {
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
						 colNames:[ 'Sl.No','Indicator','UoM','Past Indicator','BM Yr 10','Apr-2011','May-2011','Jun-2011','Jul-2011','Aug-2011','Sep-2011','Oct-2011','Nov-2011','Dec-2011'],
								     colModel:[ {name:'Sl.No',index:'Sl.No', editable:false, width:45},		          
										   {name:'Indicator',index:'Indicator',editable:false, width:160},
										   {name:'UoM',index:'UoM',  width:80},	
										   {name:'Past Indicator',index:'Past Indicator',editable:false, width:100},
										   {name:'BM Yr 10',index:'BM Yr 10',editable:false, width:80},
										   {name:'Apr-2011',index:'Apr-2011',editable:false, width:80},
										   {name:'May-2011',index:'May-2011',editable:false, width:80},
										   {name:'Jun-2011',index:'Jun-2011',editable:false, width:80},
										   {name:'Jul-2011',index:'Jul-2011',editable:false, width:80},
										   {name:'Aug-2011',index:'Aug-2011',editable:false, width:80},
										   {name:'Sep-2011',index:'Sep-2011',editable:false, width:80},
										   {name:'Oct-2011',index:'Oct-2011',editable:false, width:80},
										   {name:'Nov-2011',index:'Nov-2011',editable:false, width:80},
										   {name:'Dec-2011',index:'Dec-2011',editable:false, width:80},
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
								caption:'Target Setting',
								width:780,
								height:300,
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
		 jQuery( "#filter" ).dialog({
			   autoclose: false,

			modal: true
		});

		jQuery( "#clickfilter" ).click(function() 
		{
		//alert('K');	
			jQuery("#filterkp").show();
			jQuery( "#filterkp" ).dialog({
				autoOpen: false,
				modal: true
					
			});
		});
			
		

		

		 

		jQuery( "#closedialoge" ).click(function() {
			jQuery( "#filter" ).dialog( "close" );
		});	
		
		 </script>
	     <div id="filterkp" title="Filter"  style=" width: 400px; height:310px ;display: none;" >
	    
	             <div align="center"><table  ><tr><td>
	             <div class="easyui-paddingbfpx" >
		         	 <label>Factory</label>
		        </div>
                 <div  class="easyui-paddingbfpx"> 
                  	<input id="" name="" class="easyui-combobox"  style="width:255px;" >
               </div>
               
				 <div class="easyui-paddingbfpx" >
			       	<label>Section</label> </div>
               <div  class="easyui-paddingbfpx";> 
            	   	<input id="cmbsec" name="cmbsec" class="easyui-combobox"  style="width:255px" ; >
				</div>
				
				 <div class="easyui-paddingbfpx" >
			   		<label>Cost Center</label></div> 
               <div  class="easyui-paddingbfpx"> 
            	   <input id="cmbCost" name="cmbCost" class="easyui-combobox" style="width:255px; " >
				</div>
				
			  	<div class="easyui-paddingbfpx mndlbl" >
					<label>Cell</label></div> 
                <div  class="easyui-paddingbfpx"> 
            	    <input id="cmbCell" name="cmbCell" class="easyui-combobox" style=" width:255px;" >
				</div>
				
				 <div class="easyui-paddingbfpx mndlbl" >
					<label>Year</label></div> 
              	<div  class="easyui-paddingbfpx"> 
            	     <input id="cmbyr" name="cmbyr" class="easyui-combobox" style="width:255px;"  >
            	
				</div>
				
			
				    <input id="view"  class="easyui-button" onclick="" style="width:70px" value="View" />
	                <input id="clear" class="easyui-button"   onclick="" style="width:70px" value="Clear"/>
	                <input  id="closedialoge"  class="easyui-button" onclick="" style="width:70px" value="Close"/>
	                </td></tr></table>
	               </div>
	             </div>
				
            

             <div id="keyperformance"  class="main-cntborder">
	     	<div style="float:left;padding-left:200px;padding-top: 6px" padding-bottom:100px >
	          <input type="radio" id="button"/><label>Manual Entry</label>
	          </div>
	          
	          <div style="float:right;padding-right:200px; padding-bottom:10px;padding-top: 6px ">
	          
		          <input id="clickfilter"  class="easyui-button" onclick="" style="width:70px; "value="Filter"/>
		          <input  id="insert"  class="easyui-button" onclick="" style="width:70px;"value="View"/>
		          <input id="insert"  class="easyui-button" onclick="" style="width:100px;"value="Export to Excel"/></div>
		    
		    
		     <div style="padding-top: 50px">
                <table id="list" style="width:80%; "><tr><td/></tr></table>
	            <div id="pager"></div> </div>
	            </div>
</div>