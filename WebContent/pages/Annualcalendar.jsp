 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
function Spares_frm_clk(){	
	jQuery("#Acl").hide();
	jQuery("#filtercal").hide();
	jQuery("#spr").load('Anualcal_Spr.acl', function(response, status, xhr) {
  		  if (status == "error") {
  		    var msg = "Sorry but there was an error: ";
  		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
  		  }
  		});	
	}	

function Actschdeule_frm_clk(){
	//jQuery("#Acl").hide();
	jQuery("#spr").hide();
	jQuery( "#Actsch" ).show();
	jQuery( "#Actsch" ).dialog({
		autoOpen: false,
		modal: true,
		height: 490,
		width: 850		
	});
	
	jQuery("#Actsch").load('Anualcal_Actsch.acl', function(response, status, xhr) {
  		  if (status == "error") {
  		    var msg = "Sorry but there was an error: ";
  		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
  		  }
  		});	
	}	
jQuery.noConflict();
jQuery(document).ready(function(){	

	jQuery( "#clickfilter" ).click(function() {
		jQuery( "#filtercal" ).show();
	jQuery( "#filtercal" ).dialog({
		autoOpen: false,
		modal: true,
		height: 500,
		width: 900		
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
	  colNames:[ 'Factory','Section','Cell','Equipment','Assembly','W1','W2','W3','W4','W11','W2','W3','W4','W12','W2','W3','W4'],
		colModel:[ {name:'Sl.no',index:'Sl.no',editable:false, width:60},
		           {name:'Section',index:'Section',editable:false, width:80},
				  	{name:'Cell',index:'Cell',editable:false, width:100},
				   {name:'Equipment',index:'Equipment',editable:false, width:80},
				   {name:'Assembly',index:'Assembly',editable:false, width:80},
				   {name:'W1',index:'W1',formatter: actionFormatterimageIC,editable:false, width:30},
				   {name:'W2',index:'W2',formatter: actionFormatterimageC,editable:false, width:30},
				   {name:'W3',index:'W3',editable:false, width:30},
				   {name:'W4',index:'W4',editable:false, width:30},
				   {name:'W11',index:'W11',editable:false, width:30},
				   {name:'W2',index:'W2',editable:false, width:30},
				   {name:'W3',index:'W3',editable:false, width:30},
				   {name:'W4',index:'W4',editable:false, width:30},
				   {name:'W12',index:'W12',editable:false, width:30},
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
	width:1070,
	height:260,
	loadonce: true 
	
});
});
function actionFormatterimageC(cellvalue, options, rowObject) {
	

    return "<img src =\"images/annualcal/completed.png\" id='Complet' onclick=\" Spares_frm_clk(); \" />" ;
}
function actionFormatterimageIC(cellvalue, options, rowObject) {
	

    return "<img src =\"images/annualcal/incomplete.png\" id='InComplet' onclick=\" Actschdeule_frm_clk(); \" />" ;
}

</script>

<div id="spr">

</div>
<div id="Actsch" title="Activity Schedule Details" >

</div>

<div  id="Acl" class="main-cntborder" align="center">
	<table align="center">
		<tr>
			<td>
			<div>
				<div >
				<img src="images/annualcal/annualplan1.png" />
				<span style="padding-left:20%">
				<input  class="easyui-button" type="button"  value="View" id="vw" />
				<input  class="easyui-button" type="button"  value="Filter" id="clickfilter" />
				<input  class="easyui-button" type="button"  value="Export to Excel" id="exptexl" /> 
				</span>
			</div>
			</div>
					
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div id="grid" >
				<!--	End grid	 Script				-->    

					<table id="anualcal" style="width:100%"><tr><td/></tr></table>
	
					<div id="pager"></div>
										
					<!--	end of grid			-->
		</div>
			</td>
		</tr>
</table>
</div>
	<div id="filtercal"  title="FILTER"  style="display:none;">
		
		
		<div>
			<div  style="float:left; padding-left: 80px;padding-top:6">
						 <div  class="easyui-paddingbfpx"; style="padding-top:10px">
										  <input id="echkbox" type="checkbox"/> <label>Group by Equipment</label>
										  <span  style="margin-left: 2px;">  <input id="schkbox" type="checkbox"/> <label>All Equipment</label></span>
									 </div>
						
					  <div  class="easyui-paddingbfpx "  ><label>Factory</label></div> 
					  <div class="easyui-paddingbfpx" > 
					  <input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value=""  >
					   </div>
						
						<div class="easyui-paddingbfpx "><label>Section</label></div> 
					  <div class="easyui-paddingbfpx "> 
						<input class="easyui-text"  id="txtsctn" name="txtName"  style="width:255px; height : 21px;" value=""  >
						</div>
						<div class="easyui-paddingbfpx"><label>CostCenter</label></div> 
					  <div class="easyui-paddingbfpx"> 
						<input id="cmbCstcntr" name="cmbCstcntr" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						</div>
						<div class="easyui-paddingbfpx"><label>Cell</label></div> 
					  <div class="easyui-paddingbfpx"> 
						<input id="cmbCell" name="cmbCell" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						</div>
					 
						<div class="easyui-paddingbfpx"><label>Circle</label></div> 
					  <div class="easyui-paddingbfpx"> 
						<input id="cmbcircle" name="cmbcircle" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						</div>
						
						<div class="easyui-paddingbfpx"><label>Equipment Group</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbeqpgrp" name="cmbeqpgrp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Equipment</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbeqp" name="cmbeqp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						<input class="easyui-button" type="button"  value="..." />
						</div>
						
						<div class="easyui-paddingbfpx"><label>Assembly</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbasmbly" name="cmbasmbly" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
							
			</div>
			<div style="float:left;padding-left:120px;">
				
						
						<div class="easyui-paddingbfpx"><label>Equipment Rank</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbeqprnk" name="cmbeqprnk" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>JH Step</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbjhs" name="cmbjhs" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Maint. Sec</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbmainsec" name="cmbmainsec" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Activities</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbactvts" name="cmbactvts" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Production Group</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbprodgrp" name="cmbprodgrp" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Job Type</label></div> 
						<div class="easyui-paddingbfpx"> 
						<input id="cmbjobtype" name="cmbjobtype" class="easyui-combobox" style="width:255px;height : 21px;" value=""  >
						
						</div>
						
						<div class="easyui-paddingbfpx"><label>Frequecy</label><span class="lblrr" style="padding-left:80px;">Source</span></div> 
							 <div class="easyui-paddingbfpx"> 
								  <input id="cmbfreq" class="easyui-combobox" required="true"  style="width:113px;"/>
									 
					  <span > 
					  <input id="cmbsrc" class="easyui-combobox" required="true"  style="width:113px;"/>
					  </span> </div>
					  
					  <div class="easyui-paddingbfpx"><label>Equipment condition</label></div>
						<div class="easyui-paddingbfpx" style="padding-top:2px"> 
								  <input id="cmbeqpcndn" class="easyui-combobox" required="true"  style="width:113px;"/>
									<span  style="margin-left: 2px;">  <input id="schkbox" type="checkbox"/> <label>Include Status</label></span> 
					
					  </div>
					  <div class=paddingbfpx"><label>Year</label></div> 
						<div class="easyui-paddingbfpx" style="padding-top:2px"> 
						<input id="cmbyr" name="cmbyr" class="easyui-combobox" style="width:115px;height : 21px;" value=""  >
						
						</div>
							
					
				
			</div>
		
				
		</div>
		<div id="dlg-buttons" align="center" style="width:100%;float:right">
					<input  class="easyui-button" type="button"  value="Ok" id="vw" />
					<input  class="easyui-button" type="button"  value="Clear" id="vw" />
					<input  class="easyui-button" type="button" onclick="close1()"  value="Close" id="vw" />
				</div>
	</div>

