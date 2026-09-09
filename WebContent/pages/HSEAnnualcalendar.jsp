<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){	

	jQuery( "#clickfilter" ).click(function() {
		jQuery( "#filter" ).show();
		jQuery( "#filter" ).dialog({
		autoOpen: false,
		modal: true,
		height: 400,
		width: 600		
	});					
});

	
	
	jQuery("#anualcal").jqGrid({
		url:'',
		datatype: "local",
		data:[
			  {id:"1", Factory:1, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"2", Factory:2, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"3", Factory:3, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  {id:"4", Factory:4, Section:"note", Cell:"cylinder",Equipment:"pune",Assembly:"2008-10-02",W1:"IC",W2:"C",W3:"A",W4:"",W1:"C",W2:"A",W3:"",W4:"IC",W1:"IC",W2:"C",W3:"A",W4:""},
			  
			
		  ],
		  colNames:[ 'Factory','Section','Cell','Equipment','Assembly','W1','W2','W3','W4','W1','W2','W3','W4','W1','W2','W3','W4'],
			colModel:[ {name:'Sl.no',index:'Sl.no',editable:false, width:60},
			           {name:'Section',index:'Section',editable:false, width:80},
					  	{name:'Cell',index:'Cell',editable:false, width:100},
					   {name:'Equipment',index:'Equipment',editable:false, width:80},
					   {name:'Assembly',index:'Assembly',editable:false, width:80},
					   {name:'W1',index:'W1',editable:false, width:30},
					   {name:'W2',index:'W2',editable:false, width:30},
					   {name:'W3',index:'W3',editable:false, width:30},
					   {name:'W4',index:'W4',editable:false, width:30},
					   {name:'W1',index:'W1',editable:false, width:30},
					   {name:'W2',index:'W2',editable:false, width:30},
					   {name:'W3',index:'W3',editable:false, width:30},
					   {name:'W4',index:'W4',editable:false, width:30},
					   {name:'W1',index:'W1',editable:false, width:30},
					   {name:'W2',index:'W2',editable:false, width:30},
					   {name:'W3',index:'W3',editable:false, width:30},
					   {name:'W4',index:'W4',editable:false, width:30},
					  
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
		caption:'Double Click on the shaded area to view the Activities',
		width:950,
		height:280,
		loadonce: true,
		
		ondblClickRow: function(rowid) {	
				 alert(rowid);	
				 alert(status);
			 jQuery("#HseAcl").hide();
			
			 jQuery("#subform").load("HSEAnnualCal_view.HSEAnualCal", function(response, status, xhr) {
				 // alert(status);
				  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
				  else if(status == "success")
				  {
					 
					  //jQuery("#EquipmentDefect").show();
				  }
				  
			});
		 }
			 
	});	

	jQuery( "#close" ).click(function() {
		//alert("close");
		
		jQuery("#filter").hide();
	});


});

</script>


<div id="HseAcl" align="center" class="main-cntborder">

	<table align="center">
		<tr>
			<td>
			<div>
				<div style="padding-right:620PX;">
				<img src="images/annualcal/annualplan1.png" />
				
			</div>
				<div style="padding-left:600px; padding-bottom: 5px;">
				
				<input type="button"  value="View" id="vw" class="easyui-button"/>
				<input type="button" class="easyui-button" value="Filter" id="clickfilter" />
				<input type="button" class="easyui-button" value="Export to Excel" id="exptexl" /> 
				</div>	
				
			</div>	
				
				
			</td>
		</tr>
</table>
						
	   
		
			<div id="AnnualCal" style="padding-top:5px;padding-right:100px;">
				
					<table id="anualcal" style="width:100%"><tr><td/></tr></table>	
					<div id="pager"></div>			
				
			</div>	
				
				
				
			<div id="Blending">
			
			</div>	
			
	</div>	
	<div id="subform">
</div>
	 <!-- Filter start-->
<div id="filter"  title="Filter" style="display: none;">
		<form action="">
			<div>
		<div  style="float:left; padding-left: 30px;">
					
					
				  <div ><label >Factory</label></div> 
                  <div class="easyui-paddingbfpx">
                  <input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value=""  >  
                
                   </div>
					
					<div ><label>Section</label></div> 
                  <div class="easyui-paddingbfpx"> 
                	<input id="txtsctn" name="txtsctn"  style="width:255px; height : 21px;" value="" class="easyui-text"; >
					</div>
					
					<div ><label>Cell</label></div> 
                  <div class="easyui-paddingbfpx"> 
                	<input id="cmbCell" name="cmbCell" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
					</div>
				 
					<div ><label>Circle</label></div> 
                  <div class="easyui-paddingbfpx"> 
                	<input id="cmbcircle" name="cmbcircle" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
					</div>
					
					<div ><label >Equipment Group</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbeqpgrp" name="cmbeqpgrp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	
					</div>
					
					<div ><label>Equipment</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbeqp" name="cmbeqp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	<input type="button" class="easyui-button" value="..." style="width : 33px; height : 29px;"/>
					</div>
					
					
						
	    </div>
		<div style="float:right;">
			
					<div ><label>Assembly</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbasmbly" name="cmbasmbly" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >                	
					</div>
					
					<div ><label >Equipment Rank</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbeqprnk" name="cmbeqprnk" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	
					</div>
					
					<div ><label >JH Step</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbjhs" name="cmbjhs" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	
					</div>
					
					<div ><label >Maint. Sec</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbmainsec" name="cmbmainsec" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	
					</div>
					
					
					
					
					<div ><label>Job Type</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                	<input id="cmbjobtype" name="cmbjobtype" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
                	
					</div>
					
					<div ><label >Frequecy</label> <span style="padding-left: 50px"><label >Year</label></span></div> 
						 <div class="easyui-paddingbfpx"> 
                			<input id="cmbfreq" class="easyui-combobox"   style="width:113px;"/>
							<input id="cmbyr" name="cmbyr" class="easyui-combobox" style="width:115px;height : 21px;" value=""> 
                  </div>                
                  
                 
                </div>  	
				</div>
			<div  class="easyui-paddingbfpx"; style="float:left;padding-left:150px;">			
				<input type="button" class="easyui-button" id="ok" value="Ok" />
				<input type="reset" class="easyui-button" id="clr" value="Clear" />
				<input type="button" class="easyui-button" id="close" value="Close" />
				<input type="button" class="easyui-button" id="newAudit" value="New Audit" />
				
			</div>
			
			
			
			</form>
		</div>	
<!-- end of filter-->