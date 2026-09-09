<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">


jQuery.noConflict();
jQuery(document).ready(function(){	


jQuery( "#filter" ).click(function() {
	jQuery( "#dialog" ).show();
	jQuery( "#dialog" ).dialog({
		autoOpen: false,
		show: "blind",
		hide: "explode",
		height: 500,
		width: 850,
		modal: true
	});
});

jQuery( "#addnlfilters" ).click(function() {
	jQuery( "#addnfilterdialog" ).show();
	jQuery( "#addnfilterdialog" ).dialog({
		autoOpen: false,
		show: "blind",
		hide: "explode",
		height: 400,
		width: 700,
		modal: true
	});
});


						
						jQuery("#list1").jqGrid({
								datatype: "local",
								colNames:[ 'Incident No','Related To','Factory','Section','Cell','Equipment','Accident Date'],
								colModel:[ {name:'incidentno',index:'incidentno',editable:false, width:150},
										   {name:'relatedto',index:'relatedto',editable:false, width:150},
										   {name:'factory',index:'factory',editable:false, width:150},
										   {name:'sect',index:'sect',editable:false, width:150},
										   {name:'cell',index:'cell',editable:false, width:150},
										   {name:'equipmnt',index:'equipmnt',editable:false, width:150},
										   {name:'accidentdt',index:'accidentdt',editable:false, width:150},
										  ],
   							    data:[
									  {id:"1", accidentdt:"2007-10-01",relatedto:"test1", equipmnt:"note", closed:true},
									  {id:"2", accidentdt:"2007-10-02",relatedto:"test2", equipmnt:"note2",closed:false},
									  {id:"3", accidentdt:"2007-09-01",relatedto:"test3", equipmnt:"note3",closed:false},
									  {id:"4", accidentdt:"2007-10-04",relatedto:"test4", equipmnt:"note4",closed:true },
									  {id:"5", accidentdt:"2007-10-31",relatedto:"test5", equipmnt:"note5",closed:false},
									  {id:"6", accidentdt:"2007-10-01",relatedto:"test1", equipmnt:"note", closed:true},
									  {id:"7", accidentdt:"2007-10-02",relatedto:"test2", equipmnt:"note2", closed:false},
									  {id:"8", accidentdt:"2007-09-01",relatedto:"test3", equipmnt:"note3",closed:false},
									  {id:"9", accidentdt:"2007-10-04",relatedto:"test4", equipmnt:"note4", closed:true},
									  {id:"10",accidentdt:"2007-10-31",relatedto:"test5", equipmnt:"note5",closed:false}
									
								  ],		  
								rowNum:50,
								rowList:[5,10,20],
								//rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'Double Click on data row to Edit details',
								width:1040,
								height:300,
								loadonce: true,
								ondblClickRow: function(id){
									//var rowData = jQuery("#list1").jqGrid('getRowData',id);
									jQuery("#irm").load('incident_report.modfn', function(response, status, xhr) {
										  if (status == "error") {
										    var msg = "Sorry but there was an error: ";
										    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
										  }
									});	
								 }
						});

						jQuery("#list2").jqGrid({
							datatype: "local",
							colNames:[ 'Body Part'],
							colModel:[ {name:'bodypart',index:'bodypart',editable:false, width:450},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,
							multiselect: true,
							//multikey: "ctrlKey",
							pager: '#pager1', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Double Click on data row to Edit details',
							width:800,
							height:150,
							loadonce: true,
							ondblClickRow: function(id){
								var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});

						jQuery("#afdlist").jqGrid({
							datatype: "local",
							colNames:[ 'Options','Operators','Values'],
							colModel:[ {name:'options',index:'options',editable:false, width:200},
							           {name:'operators',index:'operators',editable:false, width:200},
							           {name:'values',index:'values',editable:false, width:200},
									 ],
							    data:[
								  {id:"1", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"2", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2",closed:false},
								  {id:"3", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"4", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4",closed:true },
								  {id:"5", accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false},
								  {id:"6", accidentdt:"2007-10-01",bodypart:"test1", equipmnt:"note", closed:true},
								  {id:"7", accidentdt:"2007-10-02",bodypart:"test2", equipmnt:"note2", closed:false},
								  {id:"8", accidentdt:"2007-09-01",bodypart:"test3", equipmnt:"note3",closed:false},
								  {id:"9", accidentdt:"2007-10-04",bodypart:"test4", equipmnt:"note4", closed:true},
								  {id:"10",accidentdt:"2007-10-31",bodypart:"test5", equipmnt:"note5",closed:false}
								
							  ],		  
							rowNum:50,
							rowList:[5,10,20],
							//rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#afdpager', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'Double Click on data row to Edit details',
							width:650,
							height:200,
							loadonce: true,
							ondblClickRow: function(id){
								//var rowData = jQuery("#list1").jqGrid('getRowData',id);
								//jQuery( "#mpdialog" ).dialog( "open" );
							 }
					});
});
		</script>
<!--						First Dialog				-->

<div id="dialog" title="Filter" style="display:none;">
<form id="filterDialog" name="filterDialog">

<div  style="padding-left:0px;width : 820px; height : 700px;">
	<div class="easyui-tabs" fit="true" plain="true" style="width:200px; height : 284px;">
			<div title="Dispensary Filter" style="padding:10px;">
				<table border="0" align="center">
				<tr>
				<td style="width:40%" valign='top'>
				
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Incident No </label>
					</div> 
					<div class="easyui-paddingbtpx" style="padding-left:50px;"> 
							<input id="cmbincidentno" name="cmbincidentno" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:50px;">
							<input id="forcontemp" type="checkbox"/><label> For Contract Employee </label>
					</div> 
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Name Of Injured Person </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbnameofinjdpersn" name="cmbnameofinjdpersn" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Factory </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbfact" name="cmbfact" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Section</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbsect" name="cmbsect" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Cell</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbcell" name="cmbcell" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label>Circle </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbcircle" name="cmbcircle" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Equipment</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbeqpt" name="cmbeqpt" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
				</td>
				<td style="width:50%" valign='top'>
				
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Shift</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="cmbshift" name="cmbshift" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Referred To</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<select id="referdto" class="easyui-combobox" name="referdto" style="width:130px;" required="true">
								<option value="-"> ---</option>
							</select> 
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Nature Of Injury</label>
					</div> 
					<div class="easyui-paddingbtpx" style="padding-left:50px;"> 
							<input id="cmbnatureofinj" name="cmbnatureofinj" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
						    <input type="text" id="natureofinj" name="natureofinj" style="width:255px;" class="easyui-text" >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Medical Officer</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input type="text" id="medofficer" name="medofficer" style="width:255px;" class="easyui-text" >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label> Medical Assistant</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input type="text" id="medasst" name="medasst" style="width:255px;" class="easyui-text" >
					</div>
					
					 <div  class="easyui-paddingbfpx" style="padding-left:50px;">
				             <input id="incidentchkbox" type="checkbox"/><span style="margin-left:2px;"><label>From Date</label></span>
				             <span  style="margin-left: 96px;"><label>To Date</label></span>
				     </div> 
					 <div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							  <input id="incdfrom" name="incdfrom" class="easyui-datebox" required="true" style="width:110px;"/>
							  <span  style="margin-left: 32px;"> 
							      <input id="incdto" name="incdto" class="easyui-datebox" required="true" style="width:110px;"/>
							  </span> 
					 </div>
					
					</td>
				</tr>
				<tr>
				<td colspan="2">
					<table id="list2" style="width:100%"><tr><td/></tr></table>
					<div id="pager2"></div>
				</td>
				</tr>
				</table>
	
			</div>
			
			<div title="Department Filter" style="padding:10px;">
				<table border="0" align="center">
				<tr>
				<td width="50%" valign='top'>
				
					<div class="easyui-paddingbtpx" style="padding-left:50px;">
							<label>Type</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:50px;"> 
							<input id="temp" type="checkbox"/><span  style="margin-right:10px;"> <label>Temporary </label></span>
							<input id="permnt" type="checkbox"/><span  style="margin-right:10px;"> <label>Permanent </label></span>
					</div>
				
					<div class="easyui-paddingbfpx" style="padding-left:50px;">
							<label>Was he expected to be at the Place of Accident</label>
					</div>
					<div class="easyui-paddingbtpx" style="padding-left:50px;">
							<select id="placeofaccdnt" class="easyui-combobox" name="placeofaccdnt" style="width:255px;" required="true">
								<option value="-"> ---</option>
							</select> 
					</div>
				
					<div class="easyui-paddingbtpx" style="padding-left:50px;">
							<input id="causedbyeqp" type="checkbox"/><span  style="margin-left:2px;"><label>If caused by Equipment</label></span>
					</div>
					<div style="padding-left:50px;">
					<div class="cntborder">
						  <div  class="easyui-paddingbtpx" style="padding-left:5px;padding-top:5px;">
						  		<input id="powerdriven" type="checkbox"/> <label>Power Driven</label>
                  			    <span  style="margin-left: 2px;">  <input id="manlyopertd" type="checkbox"/> <label>Manually Operated</label></span>
                  		  </div>
						  <div class="easyui-paddingbfpx" style="padding-left:5px;">
						  		<label>Who Operated?</label>
						  </div>
						   <div class="easyui-paddingbfpx" style="padding-left:5px;">
						   		 <select id="whoopertd" class="easyui-combobox" name="whoopertd" style="width:110px;" required="true">
									<option value="-"> ---</option>
								</select> 
								 <span  style="margin-left: 32px;"> 
								 		  <input type="text" class="easyui-text" id="whooperted" name="whooperted" style="width:110px;">
								 </span>
						   </div>
						    <div class="easyui-paddingbfpx" style="padding-left:5px;">
						    	 <label>Was he authorized</label>
						  </div>
						  <div class="easyui-paddingbfpx" style="padding-left:5px;">
						    	 <select id="washeauthorized" class="easyui-combobox" name="washeauthorized" style="width:110px;" required="true">
									<option value="-"> ---</option>
								</select> 
						  </div>
					</div>
					</div>
			
						 
				</td>
				<td width="50%" valign='top'>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
							<label>Was the work in progress being supervised by a Supervisor</label>
					</div>
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							 <select id="workinprogress" class="easyui-combobox" name="workinprogress" style="width:110px;" required="true">
								<option value="-"> ---</option>
						    </select>  
						     <span  style="margin-left: 32px;"> 
						     		<input type="text" id="supervisor" name="supervisor" style="width:110px;" class="easyui-text">
						     </span>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<label>Hot Work Permit</label>
					</div>
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<input id="hwpyes" type="checkbox"/> <label>Yes</label>
                  			<span  style="margin-left: 2px;">  <input id="hwpno" type="checkbox"/> <label>No</label></span>
                  			<span  style="margin-left: 2px;">  <input id="hlwpna" type="checkbox"/> <label>Not Applicable</label></span>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<label>Late Work Permit</label>
					</div>
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<input id="lwpyes" type="checkbox"/> <label>Yes</label>
                  			<span  style="margin-left: 2px;">  <input id="lwpno" type="checkbox"/> <label>No</label></span>
                  			<span  style="margin-left: 2px;">  <input id="lwpna" type="checkbox"/> <label>Not Applicable</label></span>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<label>Working at height</label>
					</div>
					<div class="easyui-paddingbtpx" style="padding-left:40px;">
							<input id="wahyes" type="checkbox"/> <label>Yes</label>
                  			<span  style="margin-left: 2px;">  <input id="wahno" type="checkbox"/> <label>No</label></span>
                  			<span  style="margin-left: 2px;">  <input id="wahna" type="checkbox"/> <label>Not Applicable</label></span>
					</div>
					

				</td>
				</tr>
				</table>
			</div>
			
			<div title="Safety Filter" style="padding:10px;">
				<table border="0" align="center">
				<tr>
				<td>
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<input id="receiptchkbox" type="checkbox"/><span  style="margin-left: 2px;">  <label>Date of Receipt of Report</label></span>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="receiptfrom" name="receiptfrom" class="easyui-datebox" required="true" style="width:110px;"/>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<label>To</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="receiptto" name="receiptto" class="easyui-datebox" required="true" style="width:110px;"/>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<label>Incident Category</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="cmbincdntcatgry" name="cmbincdntcatgry" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<label>Incident Type</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="cmbincdnttype" name="cmbincdnttype" class="easyui-combobox"  style="width:255px;" value=""  >
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<label>Reason</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="cmbreason" name="cmbreason" class="easyui-datebox" required="true" style="width:110px;"/>
					</div>
					
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<input id="dtofcntctchkbox" type="checkbox"/><span  style="margin-left: 2px;">  <label>Date of Contact with injured Person</label></span>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="contctinjprsnfrom" name="contctinjprsnfrom" class="easyui-datebox" required="true" style="width:110px;"/>
					</div>
					
					<div class="easyui-paddingbtpx" style="padding-left:250px;">
							<label>To</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:250px;"> 
							<input id="contctinjprsnto" name="contctinjprsnto" class="easyui-datebox" required="true" style="width:110px;"/>
					</div>
			
			
					</td></tr></table>
			</div>
			
		
	</div>
</div>

 <div style="padding-left:200px;">
			<input type="button" id="addnlfilters"  class="easyui-button" onclick="" value="Additional Filters"/>
		   <input type="button" id="ok"  class="easyui-button" onclick="" value="OK"/>
		    <input type="button" id="clear"  class="easyui-button" onclick="" value="Clear"/>
		     <input type="button" id="cancel"  class="easyui-button" onclick="" value="Cancel"/>
</div>
</form>
</div>
<div id="addnfilterdialog" title="Additional Filter" style="display:none;">
<form id="addnlfilterDialog" name="addnlfilterDialog">
			<div class="easyui-paddingbfpx" style="padding top:20px;padding-left:50px;">
				<label>Fields</label>
			</div> 
			<div style="padding-bottom: 8px;padding-left:50px;"> 
				<input id="cmbfields" name="cmbfields" class="easyui-combobox"  style="width:255px;" value=""  >
				 <span  style="margin-left: 32px;"> 
					<input type="text" id="txtfields" name="txtfields"  class="easyui-text"  style="width:255px;">
					<input type="button" id="insert"  class="easyui-button" onclick="" value="Insert"/>
				</span>
			</div> 
			
			<table id="afdlist" style="width:100%"><tr><td/></tr></table>
			<div id="afdpager"></div>
	
</form>
</div>

<div class="main-cntborder" id="irm">
<!--<div id="irm" align="center" style="width: 1100px;margin: 0 auto;border-style:solid;border-width:thin;height:900px">-->

 <div style="padding-bottom: 5px;padding-top:5px;padding-left:900px;">
			<input type="button" id="view"  class="easyui-button" onclick="" value="View"/>
		   <input type="button" id="filter"  class="easyui-button" onclick="" value="Filter"/>
</div>
<table id="list1" style="width:100%"><tr><td/></tr></table>
<div id="pager1"></div>
</div>