
 <script type="text/javascript" >
 var compid = null;
 var factid=null;
 var sectId = null;
 var cellId = null;
 var machineId = null;

 jQuery(document).ready(function(){
	 
	 processFactroyCombo('factroyCombo.commonFilter');
	 processSectionCombo('sectionCombo.commonFilter');
	 processCellCombo('cellCombo.commonFilter');
	 processMachineCombo('machineCombo.commonFilter');

	 
	jQuery('#cmbComp').combobox({
			mode:'remote',
			url:'companyCombo.commonFilter',
			valueField:'id',
			textField:'text',
			onSelect:function(record)
			{
				compid = record.id;	
				processFactroyCombo('factroyCombo.commonFilter?compId='+compid);
				processSectionCombo('sectionCombo.commonFilter?compId='+compid,compid);
			},
			onUnselect:function()
			{
				//alert('un select');
			},
			
			selected: function(event, ui) {
				//var compId = $("#cmbCompId").val();
			 }
		});

	jQuery('#cmbCostCenter').combobox({
		mode:'remote',
		url:'costCenter.commonFilter',
		//url:url,		
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			
			
		},
		onUnselect:function()
		{
			//alert('un select');
		},
		
		selected: function(event, ui) {
			//var compId = $("#cmbCompId").val();
		 }
	});
 });
 
function processFactroyCombo(url)
{
	jQuery('#cmbFact').combobox({
		mode:'remote',
		//url:'factroyCombo.commonFilter?compId='+compid,
		url:url,		
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			factid = record.id;
			processSectionCombo('sectionCombo.commonFilter?compId='+compid+'&factId='+factid,compid,factid);						
		},
		onUnselect:function()
		{
			//alert('un select');
		},
		
		selected: function(event, ui) {
			//var compId = $("#cmbCompId").val();
		 }
	});

}
			
function processSectionCombo(url,compid,factid)
{
	
	jQuery('#cmbShop').combobox({
		mode:'remote',
		//url:'sectionCombo.commonFilter?compId='+compid+'&factId='+factid,
		url:url,		
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			sectId = record.id;
			processCellCombo('cellCombo.commonFilter?compId='+compid+'&factId='+factid+'&sectId='+sectId,compid,factid,sectId);
		},
		onUnselect:function()
		{
			//alert('un select');
		},
		
		selected: function(event, ui) {
			//var compId = $("#cmbCompId").val();
		 }
	});

}
//costCenter.commonFilter
function processCellCombo(url,compid,factid,sectId)
{
	jQuery('#cmbCell').combobox({
				mode:'remote',
				//url:'cellCombo.commonFilter?compId='+compid+'&factId='+factid+'&sectId='+sectId,
				url:url,		
				valueField:'id',
				textField:'text',
				onSelect:function(record)
				{
					cellId = record.id;
					processMachineCombo('machineCombo.commonFilter?compId='+compid+'&factId='+factid+'&sectId='+sectId+'&cellId='+cellId);
				},
				onUnselect:function()
				{
					//alert('un select');
				},
				
				selected: function(event, ui) {
					//var compId = $("#cmbCompId").val();
				 }
			});
			
}
//cmbCostCenter			
function processMachineCombo(url)
{
	jQuery('#cmbMachine').combobox({
		mode:'remote',
		//url:'machineCombo.commonFilter?compId='+compid+'&factId='+factid+'&sectId='+sectId+'&cellId='+cellId,
		url:url,		
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			
			
		},
		onUnselect:function()
		{
			//alert('un select');
		},
		
		selected: function(event, ui) {
			//var compId = $("#cmbCompId").val();
		 }
	});
}
 

jQuery( "#view" ).click(function(response) {
 	
	var myLoc = document.getElementById('hiddenUrl').value;
	var actionPart = myLoc.replace('input','view');
	var fromDate = jQuery('#fromDate').datebox('getValue');
	var toDate = jQuery('#toDate').datebox('getValue');


	var sparesChkBox = null;
	if(document.extras.awisechkbox.checked == true)
		sparesChkBox = "ACTWISE";
	if(document.extras.atypechkbox.checked == true)
		sparesChkBox = "ACTTYPE";
	if(document.extras.mwisechkbox.checked == true)
		sparesChkBox = "MONTHWISE";
	if(document.extras.summarychkbox.checked == true)
		sparesChkBox = "SUMMARY";

		//alert(sparesChkBox);
	var dataString = '?compid='+ compid +'&fromDate='+ fromDate +'&toDate='+ toDate  +'&chbval='+ sparesChkBox;
	alert(dataString);
	viewGrid(actionPart,dataString);
	//jQuery("#list").jqGrid('setCell',"1", "tax", '', {color:'red','text-align':'center'},{title:'compName'}) ;
	//jQuery("#list").jqGrid("setLabel","compName","New",{"text-align":"right"});
	//jQuery("#list").setCaption("Test Report");
	//jQuery("#list").setGridParam({url:actionPart+dataString,dataType: "json" }).trigger('reloadGrid');

});




function chekActi_deselect(chk_Act)
{

	divopt_uncheckall();
	jQuery("#"+chk_Act).attr('checked', true);

}
function divopt_uncheckall()
{
	jQuery("#awisechkbox").attr('checked', false);
	jQuery("#atypechkbox").attr('checked', false);
	jQuery("#mwisechkbox").attr('checked', false);
	jQuery("#summarychkbox").attr('checked', false);
}

  

 function onlyNumbers(evt)
 {
 	   var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
           return false;

        return true;
 }
</script>

<div id="tt" style="padding-left:0px;width : 540px; height : 530px;">
	<div class="easyui-tabs" fit="true" plain="true" style="width:200px; height : 284px;">
<!--   Filter Tab	-->
			<div title="Filter" style="padding:10px;">
				<div  style="padding-left:0px;width : 500px; height : 444px;">
					<div class="easyui-tabs" fit="true" plain="true" style="width:140px; height : 284px;">
						<!--   Common Tab	-->
						<div title="Common" style="padding:10px;">
							
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        			<label>Company</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbComp" name="cmbComp" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Factory</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Shop</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbShop" name="cmbShop" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Cost Center</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbCostCenter" name="cmbCostCenter" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Cell</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Circle</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbCircle" name="cmbCircle" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Machine</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Trade</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbTrade" name="cmbTrade" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   
			                   <div class="sub-header">Advance</div>
			                   <div align="center">
								<table><tr><td>
								 <div  class="easyui-paddingbfpx">
                  					  <label>From Date</label>
                   					   <span  style="margin-left: 113px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="fromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                        <span  style="margin-left: 25px;"> 
			                        <input id="toDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                        </span> 
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                  					  <label>From Month</label>
                   					   <span  style="margin-left: 103px;">To Month</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="fromMonth" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="toMonth" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                      <div class="easyui-paddingbfpx">
                        			<label>Year</label>                       
                    		   	  </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="year" name="year" class="easyui-datebox"  style="width:160px" value=""  >                       
			                   </div>
							</td>
							</tr>
							</table>
							</div>
						</div>
						<!--   Equipment Related Tab	-->
						<div title="Equipment Related" style="padding:10px;">
							   	<div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Equipment Group</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbeqpGroup" name="cmbeqpGroup" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Equipment</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbeqpmnt" name="cmbeqpmnt" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Assembly</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbassembly" name="cmbassembly" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>JH Step</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbjhStep" name="cmbjhStep" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Machine Rank</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbmachineRank" name="cmbmachineRank" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Shift</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbshift" name="cmbshift" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Supervisor</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsupervisor" name="cmbsupervisor" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   	<div class="sub-header">Advance</div>
			                   	<div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                        			<label>Purpose</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbpurpose" name="cmbpurpose" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcategory" name="cmbcategory" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Sub Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsubCategory" name="cmbsubCategory" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Ins From Date</label>
                   					   <span  style="margin-left: 93px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="insFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                        <span  style="margin-left: 25px;"> 
			                        <input id="insToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                        </span> 
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                  					  <label>AMC From Date</label>
                   					   <span  style="margin-left: 87px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="amcFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="amcToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                        			 <label>BD Categorized</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbfpx"> 
				                      <select id="bdCategorized" class="easyui-combobox" name="type" style="width:160px;" required="true">
												<option value="G"> Gold</option>
												<option value="S"> Silver</option>
												<option value="B"> Bronze</option>
												<option value="A"> All</option>
									 </select> 
								</div>
								
								 <div  class="easyui-paddingbfpx">
                        			<label>AMC Renewal</label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="amcRenewal" name="amcRenewal" class="easyui-text"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Warranty Expires</label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="warrantyExpires" name="warrantyExpires" class="easyui-text"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
						</div>
						
						<!--   Spare Parts query Tab	-->
						<div title="Spare Parts Query" style="padding:10px;">
						
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Spare Part No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsparePartNo" name="cmbsparePartNo" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Spare Description</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbspareDescn" name="cmbspareDescn" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Criticality</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcriticality" name="cmbcriticality" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Classification</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbclassifcn" name="cmbclassifcn" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbspqcategory" name="cmbspqcategory" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Sub Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbspqSubCat" name="cmbspqSubCat" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>UOM</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbuom" name="cmbuom" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Make</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbmake" name="cmbmake" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Model</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbmodel" name="cmbmodel" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   	<div class="sub-header">Advance</div>
			                   	<div align="center">
								<table><tr><td>
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Options</label>
                   					   <span  style="margin-left: 134px;">Source</span>
                    			</div> 
			                    <div class="easyui-paddingbfpx"> 
			                         <select id="options" class="easyui-combobox" name="options" style="width:160px;" required="true">
												<option value="B"> Begins</option>
												<option value="E"> Ends</option>
												<option value="C"> Contains</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="source" class="easyui-combobox" name="source" style="width:160px;" required="true">
													<option value="L"> Local</option>
													<option value="IA"> Import All</option>
									   </select> 
			                        </span> 
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>ABC Class</label>
                   					   <span  style="margin-left: 121px;">Shelf Life Item</span>
                    			  </div> 
			                      <div class="easyui-paddingbfpx"> 
			                         <select id="abcClass" class="easyui-combobox" name="abcClass" style="width:160px;" required="true">
												<option value="A"> A</option>
												<option value="B"> B</option>
												<option value="C"> C</option>
												<option value="All"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="shelfLife" class="easyui-combobox" name="shelfLife" style="width:160px;" required="true">
													<option value="Y"> Yes</option>
													<option value="N"> No</option>
													<option value="ALL">All</option>
									   </select> 
			                        </span> 
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Type</label>
                   					   <span  style="margin-left: 158px;"><label>Machine Specific</label></span>
                    			  </div> 
			                      <div class="easyui-paddingbfpx"> 
			                         <select id="spqType" class="easyui-combobox" name="spqType" style="width:160px;" required="true">
												<option value="Direct"> Direct</option>
												<option value="Reuse"> Reusable</option>
												<option value="A"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="machineSpec" class="easyui-combobox" name="machineSpec" style="width:160px;" required="true">
													<option value="Y"> Yes</option>
													<option value="N"> No</option>
													<option value="ALL">All</option>
									   </select> 
			                        </span> 
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Shelf Life Unit</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbtpx"> 
				                      <select id="shelfLife" class="easyui-combobox" name="shelfLife" style="width:160px;" required="true">
												<option value="DAY"> Day</option>
												<option value="YEAR"> Year</option>
												<option value="MON"> Month</option>
												<option value="NONE"> None</option>
												<option value="A"> All</option>
									 </select> 
								</div>
			         
			         		    <div  class="easyui-paddingbfpx">
                  					  <input id="eqpwisechkbox" type="checkbox"/> <label>Equipment Wise</label>
                   					  <span  style="margin-left: 2px;">  <input id="sprwisechkbox" type="checkbox"/> <label>Spare Wise</label></span>
                    		    </div> 
                    		    </td></tr></table></div>
						</div>
						
						<!--   Manpower Cost Tab	-->
						<div title="Manpower Cost" style="padding:10px;">
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        			<label>Grade</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbgrade" name="cmbgrade" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Employee</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbemp" name="cmbemp" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                  </td></tr></table></div>
			                   	<div class="sub-header">Advance</div>
			                    <div style="padding-left:60px;">
								<table><tr><td>
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Cost</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbfpx"> 
				                      <select id="cost" class="easyui-combobox" name="cost" style="width:160px;" required="true">
												<option value="OC"> Other Cost</option>
												<option value="HC"> Holiday Cost</option>
												<option value="NC"> Normal Cost</option>
												<option value="A"> All</option>
									 </select> 
								</div>
								</td></tr></table></div>
						</div>
						
						<!--   Manpower Utilization Tab	-->
						<div title="Manpower Utilization" style="padding:10px;">
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        			<label>Team</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbteam" name="cmbteam" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Department</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdept" name="cmbdept" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   <div class="sub-header">Advance</div>
			                   <div align="center">
								<table><tr><td>
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Transaction Type</label>                       
                    		     </div> 
			                     <div class="easyui-paddingbtpx"> 
				                      <select id="cost" class="easyui-combobox" name="cost" style="width:160px;" required="true">
												<option value="BD"> Breakdown</option>
												<option value="GM"> General Maintenance</option>
												<option value="PM"> Planned Maintenance</option>
												<option value="A"> All</option>
									 </select> 
								</div>
								
								 <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="minschkbox" type="checkbox"/> <label>Minutes</label>
                   					  <span  style="margin-left: 2px;">  <input id="hrschkbox" type="checkbox"/> <label>Hours</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="planchkbox" type="checkbox"/> <label>Plan</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="actualchkbox" type="checkbox"/> <label>Actual</label></span>
                    		    </div> 
			                   </td></tr></table></div>
						</div>
						
						<!--   BD Related Tab	-->
						<div title="BD Related" style="padding:10px;">
						
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Failure Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbfailtype" name="cmbfailtype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Defect Phenomena</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdefectpheno" name="cmbdefectpheno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Cause</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcause" name="cmbcause" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>BD Root Cause</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbbdrootcause" name="cmbbdrootcause" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Production Group</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbprodcngroup" name="cmbprodcngroup" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Shift Incharge</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbtpx"> 
			                        <input id="cmbshiftincharge" name="cmbshiftincharge" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                    <div class="sub-header">Advance</div>
			                    <div align="center">
								<table><tr><td>
					            <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
		                  			    <input id="occurchkbox" type="checkbox"/> <label>Occurence</label>
		                   			    <span  style="margin-left: 2px;">  <input id="timechkbox" type="checkbox"/> <label>Time</label></span>
		                   				<span  style="margin-left: 2px;">  <input id="zerobdchkbox" type="checkbox"/> <label>Include Zero Breakdowns</label></span><br/><br/>
		                   				<span>  <input id="allchkbox" type="checkbox"/> <label>All</label></span>
		                   				<span  style="margin-left: 2px;">  <input id="remallchkbox" type="checkbox"/> <label>Remove All</label></span>
		                    	</div> 
                    		   
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="fachkbox" type="checkbox"/> <label>Final Action</label>
                  					  <span  style="margin-left: 2px;">  <input id="pillarchkbox" type="checkbox"/> <label>Pillar</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="rcchkbox" type="checkbox"/> <label>Root Cause</label></span><br/><br/>
                   					  <span>  <input id="rccchkbox" type="checkbox"/> <label>Root Cause Classification</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="cmchkbox" type="checkbox"/> <label>Counter Measure</label></span>
								 </div> 
                    		    
                    		     <div  class="easyui-paddingbfpx">
                        			 <label>BD Type</label>                       
                    		     </div> 
								 <div class="easyui-paddingbtpx"> 
				                      <select id="cost" class="easyui-combobox" name="cost" style="width:160px;" required="true">
												<option value="Major"> Major</option>
												<option value="Minor"> Minor</option>
									  </select> 
								</div>
								
								<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					 <input id="ircchkbox" type="checkbox"/> <label>Included Root Cause</label>
                  					 <span  style="margin-left: 2px;">  <input id="iyychkbox" type="checkbox"/> <label>Included Why-Why</label></span>
                  				</div>
                  				
                  				<div  class="easyui-paddingbfpx">
                        			<label>Top</label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="top" name="top" class="easyui-text"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Options</label>                       
                    		     </div> 
								 <div class="easyui-paddingbtpx"> 
				                      <select id="options" class="easyui-combobox" name="options" style="width:160px;" required="true">
												<option value="SW"> Shop Wise</option>
												<option value="CW"> Cell Wise</option>
												<option value="EGW"> Equipment Group Wise</option>
												<option value="EW"> Equipment Wise</option>
												<option value="AW"> Assembly Wise</option>
												<option value="PW"> Phenomena Wise</option>
												<option value="CauseW"> Cause Wise</option>
									  </select> 
								</div>
								
								<div  class="easyui-paddingbfpx">
                        			<label>Engineer</label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="engineer" name="engineer" class="easyui-text"  style="width:350px" value=""  >                       
			                   </div>
								
								 <div  class="easyui-paddingbfpx">
                        			 <label>Spares</label>                       
                    		     </div> 
								 <div class="easyui-paddingbtpx"> 
				                      <select id="sparesSelectBox" class="easyui-combobox" name="sparesSelectBox" style="width:160px;" required="true">
													<option value="Y"> Yes</option>
													<option value="N"> No</option>
													<option value="ALL">All</option>
									  </select> 
								</div>
								
								
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="jhchkbox" type="checkbox"/> <label>JH</label>
                  					  <span  style="margin-left: 2px;">  <input id="designchkbox" type="checkbox"/> <label>Design</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="pmchkbox" type="checkbox"/> <label>PM</label></span>
                   					  <span  style="margin-left: 2px;">  <input id="etchkbox" type="checkbox"/> <label>ET</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                        			<label>Why Why</label>                       
                    		    </div> 
							   <div class="easyui-paddingbfpx"> 
			                        <input id="yy" name="yy" class="easyui-text"  />                       
			                   </div>
			                   </td></tr></table></div>
						</div>
						
						<!--   PM Related Tab	-->
						<div title="PM Related" style="padding:10px;">
								<div class="sub-header">Regular</div>
								<div align="center">
								<table><tr><td>
								<div  class="easyui-paddingbfpx">
                        			<label>Job Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbjobtype" name="cmbjobtype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Activities</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbactivities" name="cmbactivities" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Supplier</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsupplier" name="cmbsupplier" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Tools</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbtools" name="cmbbdrootcause" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   <div class="sub-header">Advance</div>
			                   	<div align="center">
								<table><tr><td>
			                    <div  class="easyui-paddingbfpx">
                  					 <label>Sources</label>
                   					 <span  style="margin-left: 137px;"><label>Frequency</label></span>
                    			</div> 
			                    <div class="easyui-paddingbtpx"> 
			                         <select id="sources" class="easyui-combobox" name="sources" style="width:160px;" required="true">
												<option value="INTERN"> Internal</option>
												<option value="EXTERN"> External</option>
												<option value="BOTH"> Both</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="freq" class="easyui-combobox" name="freq" style="width:160px;" required="true">
													<option value="AM"> Above Monthly</option>
													<option value="MB"> Monthly and Below</option>
													<option value="ALL">All</option>
									   </select> 
			                        </span> 
			                    </div>
			                    
			                     <div  class="easyui-paddingbfpx">
                        			<input id="noofdayschkbox" type="checkbox"/> <label>Modified Records No. of Days</label>                     
                    		   </div> 
                    		   
                    		   <div  class="easyui-paddingbfpx">
                  					 <label>Source</label>
                   					 <span  style="margin-left: 142px;"><label>Equipment Condition</label></span>
                    			</div> 
                    		   <div class="easyui-paddingbtpx"> 
			                         <select id="pmsource" class="easyui-combobox" name="pmsource" style="width:160px;" required="true">
												<option value="INTERN"> Internal</option>
												<option value="EXTERN"> External</option>
												<option value="BOTH"> Both</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="eqpCondn" class="easyui-combobox" name="eqpCondn" style="width:160px;" required="true">
													<option value="RUNNING"> Running</option>
													<option value="SHUT"> Shutdown</option>
													<option value="BOTH">Both</option>
									   </select> 
			                        </span> 
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
                        			<input id="includestatuschkbox" type="checkbox"/> <label>Include Status</label>                     
                    		   </div> 
                    		   
                    		     <div  class="easyui-paddingbfpx">
                  					 <label>Status</label>
                   					 <span  style="margin-left: 144px;"><label>Drill For</label></span>
                    			</div> 
                    		     <div class="easyui-paddingbtpx"> 
			                         <select id="pmstatus" class="easyui-combobox" name="pmstatus" style="width:160px;" required="true">
												<option value="A"> All</option>
												<option value="P"> Pending</option>
												<option value="C"> Completed</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="drillfor" class="easyui-combobox" name="drillfor" style="width:160px;" required="true">
													<option value="M"> Monthly</option>
													<option value="W"> Weekly</option>
									   </select> 
			                        </span> 
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
                        			<input id="remblankchkbox" type="checkbox"/> <label>Remove Blanks</label>                     
                    		   </div> 
                    		   
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="compchkbox" type="checkbox"/> <label>%Completed</label>
                  					  <span  style="margin-left: 2px;">  <input id="pendchkbox" type="checkbox"/> <label>%Pending</label></span>
							   </div> 
							   
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="empchkbox" type="checkbox"/> <label>Employee</label>
                  					  <span  style="margin-left: 2px;">  <input id="contractorchkbox" type="checkbox"/> <label>Contractor</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="sparechkbox" type="checkbox"/> <label>Spare</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="servicechkbox" type="checkbox"/> <label>Service</label></span><br/><br/>
                  					  <span>  <input id="utilchkbox" type="checkbox"/> <label>Utility</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="otherchkbox" type="checkbox"/> <label>Other</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="totalchkbox" type="checkbox"/> <label>Total</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="apchkbox" type="checkbox"/> <label>All Parameter</label></span>
							   </div> 
							   
							    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="twchkbox" type="checkbox"/> <label>Trade Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="jtwchkbox" type="checkbox"/> <label>Job Type Wise</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                  					 <label>Job Type</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="pmjobtype" class="easyui-combobox" name="pmjobtype" style="width:160px;" required="true">
												<option value="CBM"> Condition Based Maintenance</option>
												<option value="TBM"> Time Based Maintenance</option>
												<option value="PM"> Preventive Maintenance</option>
												<option value="MBM"> Meter Based Maintenance</option>
												<option value="RBM">Run Based Maintenance</option>
												<option value="CAL"> Calibration</option>
												<option value="SM"> Shutdown Maintenance</option>
												<option value="A"> All</option>
									 </select> 
							  </div>
							  
							  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="actwisechkbox" type="checkbox"/> <label>Activity Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="monwisechkbox" type="checkbox"/> <label>Month Wise</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                  					 <label>Parameter1</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="param" class="easyui-combobox" name="param" style="width:160px;" required="true">
												<option value="BDN"> Breakdown</option>
												<option value="PMT"> PM Time</option>
												<option value="K"> Kaizen</option>
												<option value="CLIT"> CLIT Time</option>
												<option value="ABN">Abnormality</option>
									 </select> 
							  </div>
							  
							  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="awisechkbox" type="checkbox"/> <label>Activity Wise</label>
                  					  <span style="margin-left: 2px;">  <input id="atypechkbox" type="checkbox"/> <label>Activity Type</label></span>
                  					  <span style="margin-left: 2px;">  <input id="mwisechkbox" type="checkbox"/> <label>Month Wise</label></span><br/><br/>
                  					  <span>  <input id="summarychkbox" type="checkbox"/> <label>Summary</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                  					 <label>Display in Column</label>
							   </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="sectchkbox" type="checkbox"/> <label>Section</label>
                  					   <span  style="margin-left: 2px;">  <input id="cellchkbox" type="checkbox"/> <label>Cell</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="eqpmntchkbox" type="checkbox"/> <label>Equipment</label></span>
							  </div>
			                  </td></tr></table></div> 
						</div>
						
						<!--   Action Plan Tab	-->
						<div title="Action Plan" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Refernce No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbrefno" name="cmbrefno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Detected By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdetectedby" name="cmbdetectedby" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Detected From Date</label>
                   					   <span  style="margin-left: 87px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="dtdFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="dtdToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Target From Date</label>
                   					   <span  style="margin-left: 87px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="tgtFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="tgtToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                      <div  class="easyui-paddingbfpx">
                  					  <label>Completed From Date</label>
                   					   <span  style="margin-left: 87px;">To Date</span>
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="compltdFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="compltdToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     </td></tr></table></div>
						</div>
						
						<!--   Abnormality Report Tab	-->
						<div title="Abnormality Report" style="padding:10px;">
						
						  	   <div class="sub-header">Regular</div>
						  	   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>ABN Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbabntype" name="cmbabntype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>ABN Category</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbabncategory" name="cmbabncategory" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>ABN Impact</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbabnimpact" name="cmbabnimpact" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   <div class="sub-header">Advance</div>
			                    <div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Status</label>
							   </div> 
                    		   <div class="easyui-paddingbtpx"> 
			                         <select id="abnstatus" class="easyui-combobox" name="abnstatus" style="width:160px;" required="true">
												<option value="SP"> Status-Pending</option>
												<option value="SC"> Status-Completed</option>
												<option value="TETDP"> Tag Exceeds Target Date -Pending</option>
												<option value="TETDC"> Tag Exceeds Target Date -Completed</option>
												<option value="TCOT"> Tag Completed on Time</option>
												<option value="ALL"> All</option>
									 </select> 
							  </div>
			                     
			                   <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="sectchkbox" type="checkbox"/> <label>Detected By</label>
                  					  <span  style="margin-left: 2px;">  <input id="cellchkbox" type="checkbox"/> <label>Detected Date</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="eqpmntchkbox" type="checkbox"/> <label>Cause</label></span><br/><br/>
                  					  <span>  <input id="eqpmntchkbox" type="checkbox"/> <label>Abnormality Category</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="eqpmntchkbox" type="checkbox"/> <label>Abnormality Impact</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="eqpmntchkbox" type="checkbox"/> <label>All</label></span>
							  </div>
							  </td></tr></table></div>
						</div>
						
						<!--   JH (CLIT) Standard Report Tab	-->
						<div title="JH (CLIT) Standard Report" style="padding:10px;">
						
						    <div class="sub-header">Advance</div>
			                     	<div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Frequency</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="jhfreq" class="easyui-combobox" name="jhfreq" style="width:160px;" required="true">
												<option value="DAYNABOVE"> Day and Above</option>
												<option value="SHIFTWISE"> Shift Wise</option>
									 </select> 
							  </div>
							  
							    <div  class="easyui-paddingbfpx">
                  					 <label>Duration</label>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="jhduration" class="easyui-combobox" name="jhduration" style="width:160px;" required="true">
												<option value="EQ"> = </option>
												<option value="GT"> > </option>
												<option value="LT"> < </option>
												<option value="GTE"> >= </option>
												<option value="LTE"> <= </option>
												<option value="BET">Between </option>
									 </select> 
							  </div>
							  </td></tr></table></div>
						</div>
						
						<!--   PCS Reports Tab	-->
						<div title="PCS Reports" style="padding:10px;">
						
						  	   <div class="sub-header">Regular</div>
						  	   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Sub Group</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbpcssubgrp" name="cmbpcssubgrp" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Product</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbpcsprrod" name="cmbpcsprrod" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                    <div class="sub-header">Advance</div>
			                    <div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>View Option</label>
							   </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="efchkbox" type="checkbox"/> <label>Entry Format</label>
                  					   <span  style="margin-left: 2px;">  <input id="dfchkbox" type="checkbox"/> <label>Detailed Format</label></span>
								</div>
								
							   <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="archkbox" type="checkbox"/> <label>AR</label>
                  					  <span  style="margin-left: 2px;">  <input id="prchkbox" type="checkbox"/> <label>PR</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="qrchkbox" type="checkbox"/> <label>QR</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="oeechkbox" type="checkbox"/> <label>OEE</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="allchkbox" type="checkbox"/> <label>All</label></span>
								</div>
								
								  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="occurencechkbox" type="checkbox"/> <label>Occurence</label>
                  					  <span  style="margin-left: 2px;">  <input id="timechkbox" type="checkbox"/> <label>Time</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="vaichkbox" type="checkbox"/> <label>View Additional Information</label></span>
                  				 </div>
                  				 
                  				 <div  class="easyui-paddingbfpx">
                  					 <label>PCS Option</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="hourchkbox" type="checkbox"/> <label>Hour</label>
                  					  <span  style="margin-left: 2px;">  <input id="shiftchkbox" type="checkbox"/> <label>Shift</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="daychkbox" type="checkbox"/> <label>Day</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="weekchkbox" type="checkbox"/> <label>Week</label></span>
								 </div>
								 </td></tr></table></div>
						</div>
						
						<!--   Safety Tab	-->
						<div title="Safety" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Tag No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbtagno" name="cmbtagno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Sub Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsafetysubtype" name="cmbsafetysubtype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Incident No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbincidentno" name="cmbincidentno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Incident Type</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbincidenttype" name="cmbincidenttype" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Incident From Date</label>
                   					   <span  style="margin-left: 63px;">To Date</span>
                    			</div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="incdntFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="incdntdToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     </td></tr></table></div>
			                     
			                      <div class="sub-header">Advance</div>
			                      	<div align="center">
								<table><tr><td>
			                      <div  class="easyui-paddingbfpx">
                  					 <label>Improvement</label>
                   					 <span  style="margin-left: 102px;"><label>Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="improvmnt" class="easyui-combobox" name="improvmnt" style="width:160px;" required="true">
												<option value="CIT"> CIT</option>
												<option value="OIT"> OIT</option>
												<option value="MP"> MP</option>
												<option value="MI"> MI</option>
												<option value="ALL"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="safetytype" class="easyui-combobox" name="safetytype" style="width:160px;" required="true">
													<option value="TW"> Type wise</option>
													<option value="STW"> Sub Type Wise</option>
													<option value="CW"> Category Wise</option>
													<option value="IW"> Impact Wise</option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      <div  class="easyui-paddingbfpx">
                  					 <label>Priority</label>
                   					 <span  style="margin-left: 142px;"><label>Related To</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="priority" class="easyui-combobox" name="priority" style="width:160px;" required="true">
												<option value="LOW"> Low</option>
												<option value="MED"> Medium</option>
												<option value="HIGH"> High</option>
												<option value="ALL"> All</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="relatedto" class="easyui-combobox" name="relatedto" style="width:160px;" required="true">
													<option value="S"> Safety</option>
													<option value="E">Environment</option>
													<option value="H"> Health</option>
													<option value="A"> All</option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="instancechkbox" type="checkbox"/> <label>Instance</label>
                  					  <span  style="margin-left: 2px;">  <input id="mdlchkbox" type="checkbox"/> <label>Man Days lost</label></span>
								  </div>
								  
								  <div  class="easyui-paddingbfpx">
                  					 <label>Report Type</label>
                   					 <span  style="margin-left: 112px;"><label>Work Area/Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="reptype" class="easyui-combobox" name="reptype" style="width:160px;" required="true">
												<option value="INJ"> Injury</option>
												<option value="RSN">Reason</option>
												<option value="INCTYPE">Incident Type</option>
												<option value="INCCGRY">Incident Category</option>
												<option value="BP"> Body Part</option>
												<option value="RT"> Related To</option>
												<option value="SSA"> Substandard Action</option>
												<option value="SSC"> Substandard Condition</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="workarea" class="easyui-combobox" name="workarea" style="width:160px;" required="true">
													<option value="WA"> Work Area</option>
													<option value="T">Type</option>
													<option value="HV"> Health Visit</option>
													<option value="A"> All</option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      </td></tr></table></div>
						</div>
						
						<!--   Training Tab	-->
						<div title="Training Report" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Designation</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdesignation" name="cmbdesignation" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Program</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbprogm" name="cmbprogm" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Program Benefit</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbpgmbenefit" name="cmbpgmbenefit" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                  					  <label>Start Date</label>
                   					   <span  style="margin-left: 121px;">End Date</span>
                    			</div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="startDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="endDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                        			 <label>Batch</label>                       
                    		 	 </div> 
			                     <div class="easyui-paddingbfpx"> 
			                         <input id="cmbbatch" name="cmbbatch" class="easyui-combobox"  style="width:350px" value=""  >                       
			                     </div>
			                   
			                      <div  class="easyui-paddingbfpx">
                        			 <label>Program No</label>                       
                    		     </div> 
			                      <div class="easyui-paddingbfpx"> 
			                          <input id="cmbpgmno" name="cmbpgmno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                     </div>
			                     </td></tr></table></div>
			                      <div class="sub-header">Advance</div>
			                      	<div align="center">
								<table><tr><td>
			                      <div  class="easyui-paddingbfpx">
                  					 <label>Type</label>
                   					 <span  style="margin-left: 158px;"><label>Know.Avg</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="trainingtype" class="easyui-combobox" name="trainingtype" style="width:160px;" required="true">
												<option value="S"> Skill</option>
												<option value="K">Knowledge</option>
												<option value="B">Both </option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="knowavg" class="easyui-combobox" name="knowavg" style="width:160px;" required="true">
													<option value="EQ"> = </option>
													<option value="GT"> > </option>
													<option value="LT"> < </option>
													<option value="GE"> >= </option>
													<option value="LE"> <= </option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                       <div  class="easyui-paddingbfpx">
                  					 <label>Skill Avg</label>
                   					 <span  style="margin-left: 134px;"><label>Comp.Avg</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="skillavg" class="easyui-combobox" name="skillavg" style="width:160px;" required="true">
													<option value="EQ"> = </option>
													<option value="GT"> > </option>
													<option value="LT"> < </option>
													<option value="GE"> >= </option>
													<option value="LE"> <= </option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="compavg" class="easyui-combobox" name="compavg" style="width:160px;" required="true">
													<option value="EQ"> = </option>
													<option value="GT"> > </option>
													<option value="LT"> < </option>
													<option value="GE"> >= </option>
													<option value="LE"> <= </option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                      <div  class="easyui-paddingbfpx">
                        			 <label>Category</label>                       
                    		     </div> 
			                      <div class="easyui-paddingbfpx"> 
			                           <select id="trainingcategory" class="easyui-combobox" name="trainingcategory" style="width:160px;" required="true">
												<option value="R"> Regular</option>
												<option value="A">Associate</option>
												<option value="C">Contractor </option>
									   </select>                        
			                     </div>
			                     </td></tr></table></div>
						</div>
						
						<!--   Quality Tab	-->
						<div title="Quality" style="padding:10px;">
						
						 	   <div class="sub-header">Regular</div>
						 	   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Process</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbprocess" name="cmbprocess" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Complaint No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcomplaintno" name="cmbcomplaintno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Customer ID</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcustID" name="cmbcustID" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Defect Parameter</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdefactparam" name="cmbdefactparam" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Recorded By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbrecordedby" name="cmbrecordedby" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Inspection</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbinspection" name="cmbinspection" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                    <div class="sub-header">Advance</div>
			                    	<div align="center">
								<table><tr><td>
			                    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="dvpchkbox" type="checkbox"/> <label>Defect Vs Process</label>
                  					  <span  style="margin-left: 2px;">  <input id="dvmchkbox" type="checkbox"/> <label>Defect Vs Machine</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="instancechkbox" type="checkbox"/> <label>Instance</label></span><br/><br/>
                  					  <span>  <input id="quanchkbox" type="checkbox"/> <label>Quantity</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="dpichkbox" type="checkbox"/> <label>Display phenomena in Columns </label></span>
                  			   </div>
                  			   
                  			    <div  class="easyui-paddingbfpx">
                        			<label>View</label>                       
                    		   </div> 
                  			    <div  class="easyui-paddingbtpx">
                  			       <input id="macwisechkbox" type="checkbox"/> <label>Machine Wise</label>
                  			    </div>
                  			    
                  			     <div  class="easyui-paddingbfpx">
                  					 <label>Graph Options</label>
                   					 <span  style="margin-left: 94px;"><label>Report Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="graphoptions" class="easyui-combobox" name="graphoptions" style="width:160px;" required="true">
													<option value="PW">Process Wise </option>
													<option value="MW"> Machine Wise </option>
													<option value="PHW"> Phenomena Wise </option>
													<option value="CW"> Cause Wise </option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                       <select id="rptType" class="easyui-combobox" name="rptType" style="width:160px;" required="true">
													<option value="SW"> Shift Wise </option>
													<option value="WW"> Week Wise </option>
													<option value="MONW"> Month Wise </option>
													<option value="MCW"> Machine Wise </option>
													<option value="DW"> Defect Wise </option>
													<option value="RQD"> Rejected Quantity Details </option>
													<option value="RQ"> Rejected Quantity </option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                       <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="jhchkbox" type="checkbox"/> <label>JH</label>
                  					  <span  style="margin-left: 2px;">  <input id="pmchkbox" type="checkbox"/> <label>PM</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="kkchkbox" type="checkbox"/> <label>KK</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="etchkbox" type="checkbox"/> <label>ET</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="4mchkbox" type="checkbox"/> <label>4M </label></span>
                  					  <span  style="margin-left: 2px;">  <input id="impdonechkbox" type="checkbox"/> <label>Improvements Done </label></span>
                  			      </div>
                  			      
                  			      
                  			      <div  class="easyui-paddingbfpx">
                  					 <label>Complaint Type</label>
								  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="complainttype" class="easyui-combobox" name="complainttype" style="width:160px;" required="true">
													<option value="A">A </option>
													<option value="B">B </option>
													<option value="C"> C </option>
									  </select> 
			                         <span  style="margin-left: 25px;"> 
				                         <input id="rejectionchkbox" type="checkbox"/> <label>Rejection %</label>
			                        </span> 
			                      </div>
			                      
                  			     		</td> </tr></table></div>
						</div>
						
                        <!--  One Point Lesson & Kaizen Idea Sheet Tab  -->
						<div title="One Point Lesson & Kaizen Idea Sheet Tab" style="padding:10px;">
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>OPL No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmboplno" name="cmboplno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Group By Cell</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbgrpbycell" name="cmbgrpbycell" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Improvement No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbimprovmntno" name="cmbimprovmntno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                    <div  class="easyui-paddingbfpx">
                        			<label>Pillar</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbpillar" name="cmbpillar" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                   <div class="sub-header">Advance</div>
			                   	<div align="center">
								<table><tr><td>
			                    <div  class="easyui-paddingbfpx">
                  					 <label>OPL Type</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="opltype" class="easyui-combobox" name="opltype" style="width:160px;" required="true">
													<option value="R">Regular </option>
													<option value="S">Stepwise </option>
													<option value="I">Information  </option>
									  </select> 
							    </div>
							    
							     <div  class="easyui-paddingbfpx">
                  					 <label>Classification</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="bkchkbox" type="checkbox"/> <label>Basic Knowledge</label>
                  					  <span  style="margin-left: 2px;">  <input id="icchkbox" type="checkbox"/> <label>Improvement Cases</label></span><br/><br/>
                  					  <span>  <input id="tcchkbox" type="checkbox"/> <label>Trouble Cases</label></span>
								</div>
								 
								  <div  class="easyui-paddingbfpx">
                  					 <label>Pillar Name</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="dmchkbox" type="checkbox"/> <label> Development Management(DM)</label><br/><br/>
                  					  <span>  <input id="etchkbox" type="checkbox"/> <label>Education and Training (ET)</label></span><br/><br/>
                  					  <span>  <input id="jhchkbox" type="checkbox"/> <label>Jishu Hozen(JH)</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="kkchkbox" type="checkbox"/> <label>Kobetsu Kaizen(KK)</label></span><br/><br/>
                  					  <span>  <input id="otpmchkbox" type="checkbox"/> <label>Office TPM (OTPM)</label></span>
                  					  <span   style="margin-left: 2px;">  <input id="pmchkbox" type="checkbox"/> <label> Planned Maintenance (PM)</label></span><br/><br/>
                  					  <span>  <input id="qmchkbox" type="checkbox"/> <label>Quality Maintenance(QM)</label></span><br/><br/>
                  					  <span>  <input id="shechkbox" type="checkbox"/> <label>Safety Health and Environment(SHE)</label></span>
								 </div>
								 
								 <div  class="easyui-paddingbfpx">
                  					 <label>Main Group</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="sectwisechkbox" type="checkbox"/> <label>Section Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="cellwisechkbox" type="checkbox"/> <label>Cell Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="eqptwisechkbox" type="checkbox"/> <label>Equipment Wise</label></span>
								</div>
								
								 <div  class="easyui-paddingbfpx">
                  					 <label>Sub Group</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="losswisechkbox" type="checkbox"/> <label>Loss Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="pillarwisechkbox" type="checkbox"/> <label>Pillar Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="resultwisechkbox" type="checkbox"/> <label>Result Wise</label></span><br/><br/>
                  					  <span>  <input id="eqptgrpwisechkbox" type="checkbox"/> <label>Equipment Group Wise</label></span>
								</div>
								</td></tr></table></div>
						</div>
						
						<!--   Calibration Tab	-->
						<div title="Calibration" style="padding:10px;">
						
							   <div class="sub-header">Regular</div>
							   	<div align="center">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>IMTE </label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbimte" name="cmbimte" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>SlNo</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbslno" name="cmbslno" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Calib From Date</label>
                   					 <span  style="margin-left: 85px;">To Date</span>
                    		   </div> 
			                   <div class="easyui-paddingbfpx"> 
			                        <input id="calibFromDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         <span  style="margin-left: 25px;">
			                        	<input id="calibToDate" class="easyui-datebox" required="true" style="width:160px;"/>
			                         </span>
			                  </div>
			                  
							   <div  class="easyui-paddingbfpx">
                        			<label>Gauge </label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbgauge" name="cmbgauge" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Parameter</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbparameter" name="cmbparameter" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
								<div  class="easyui-paddingbfpx">
                        			<label>External Agencies </label>                       
                    		   	</div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbexternalagencies" name="cmbexternalagencies" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Method</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbmethod" name="cmbmethod" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
							    <div  class="easyui-paddingbfpx">
                        			<label>Decision </label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdecision" name="cmbdecision" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Completed By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcompltdby" name="cmbcompltdby" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
			                   </td></tr></table></div>
			                    <div class="sub-header">Advance</div>
			                   	<div align="center">
								<table><tr><td>
			                   <div  class="easyui-paddingbfpx">
                  					 <label>Issued To</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="issuedto" class="easyui-combobox" name="issuedto" style="width:160px;" required="true">
													<option value="L">Line </option>
													<option value="V">Vendor </option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                         <input id="exportchkbox" type="checkbox"/> <label>Export to HTML</label>
			                        </span> 
			                     </div>
			                     
			                     <div  class="easyui-paddingbfpx">
                  					 <label>Issued To</label>
                  					  <span  style="margin-left: 125px;">Service Type</span>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="issuedtocalib" class="easyui-combobox" name="issuedtocalib" style="width:160px;" required="true">
													<option value="L">Line </option>
													<option value="V">Vendor </option>
													<option value="B">Broken</option>
													<option value="LO">Lost</option>
													<option value="GS"> Gauges Stores</option>
													<option value="S"> Scrap</option>
													<option value="OC"> Out of Calib</option>
													<option value="AC"> Associate Office</option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                          <select id="servicetype" class="easyui-combobox" name="servicetype" style="width:160px;" required="true">
													<option value="CR">Calibration Request </option>
													<option value="SR">Service Request </option>
													<option value="CRET">Calibration Return</option>
													<option value="SRET"> Service Return</option>
									      </select> 
			                        </span> 
			                     </div>
			                   </td></tr></table></div>
						</div>
						
					</div>
				</div>
				 <div align="center" style="padding-bottom: 5px;padding-right:10px;">
               		 <input type="button" id="view"  class="easyui-button"  value="View"/>
                </div>
			</div>	
			<!--   Dash Board Tab	-->
			
	  </div>
</div>