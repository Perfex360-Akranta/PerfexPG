
<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var jobType= null;
	var actionPart = jQuery('#hiddenUrl').val();		
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	var filter = null;//jQuery("#hiddenStr").val();
	
	if(filter != null && filter.length>0)
	{
		var filterString = jQuery("#hiddenStr").val();
				
		processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");	
	}	
	else if( prevDataUrl == null || prevDataUrl.length <=0){	
		viewGrid(actionPart,"q=1");
	}
	else{
		viewGrid(actionPart,"?q=1");
	}	
	

});
jQuery('#chkboxAll').click(function()
		{
if( jQuery('#chkboxAll').is(':checked') == true){
	
	jQuery("#chkboxTbm").prop('checked',false);
	jQuery("#chkboxPrm").prop('checked',false);
	jQuery("#chkboxCbm").prop('checked',false);

	jobType="ALL";
	var url="PMComplianceSummary_input.pmsmrRpt?q=2&jobType="+jobType;
//	alert(jobType);
	var filterString="&jobType="+jobType;
	//processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");	

	
}
else{
	jobType=null;
}
		});
	jQuery('#chkboxPrm').click(function()
			{
	
if( jQuery("#chkboxPrm").is(':checked')==true){
	
	jQuery("#chkboxTbm").prop('checked',false);
	jQuery("#chkboxCbm").prop('checked',false);
	jQuery("#chkboxAll").prop('checked',false);

	jobType="PRM";
	var filterString="&jobType="+jobType;
	//alert(jobType);
	var url="PMComplianceSummary_input.pmsmrRpt?q=2";		
	//processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");	

}
else{
	chkPrm=null;
}
	});
	jQuery('#chkboxCbm').click(function()
			{
if( jQuery("#chkboxCbm").is(':checked')==true){
	
	jobType="CBM";
	jQuery("#chkboxTbm").prop('checked',false);
	jQuery("#chkboxPrm").prop('checked',false);
	jQuery("#chkboxAll").prop('checked',false);
	var filterString="&jobType="+jobType;
	//alert(jobType +"  "+filterString);
	 if(row[i]=='9'  ){
		 
 	   }
	var url="PMComplianceSummary_input.pmsmrRpt?q=2&jobType="+jobType;
	//processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");	

}
else{
	jobType=null;
}
			});
	jQuery('#chkboxTbm').click(function()
			{
if( jQuery("#chkboxTbm").is(':checked') == true){
	jQuery("#chkboxCbm").prop('checked',false);
	jQuery("#chkboxPrm").prop('checked',false);
	jQuery("#chkboxAll").prop('checked',false);
	

	jobType="TBM";
	
}
else{
	

	chkTbm=null;
}
			});
jQuery('#btnGraph').click(function()
		{	
	
	var rowId = jQuery("#PMComplianceSmry").jqGrid('getGridParam','selrow');
if(jobType!=null && rowId!=null){
	var filterString="&jobType="+jobType+"&rowId="+rowId+"&graphType=bar";
	var url ="chart.pmsmrRpt?"+filterString;
showGraphData(url);
	
}
	else{
		
		alert("Select The Activity Type And Row to Show Graph..");
		
	}
			
		
		 });

jQuery('#btnLineGraph').click(function()
		{	
	
	var rowId = jQuery("#PMComplianceSmry").jqGrid('getGridParam','selrow');
if(jobType!=null && rowId!=null){
	var filterString="&jobType="+jobType+"&rowId="+rowId+"&graphType=line";
	var url ="chart.pmsmrRpt?"+filterString;
showGraphData(url);
	
}
	else{
		
		alert("Select The Activity Type And Row to Show Graph..");
		
	}
			
		
		 });

jQuery('#btnView').click(function()
		{	
	var row = jQuery("#PMComplianceSmry").jqGrid("getGridParam", "colModel");
	for(var j=2;j<row.length;j++)
		 {  
		// alert(row[j].name.trim());
		 }
	var i=row.length-2;
	var fromMonth=row[3].name.trim().substr(0,8);
	var toMonth=row[i].name.trim().substr(0,8);
	//alert(fromMonth +" m  " +toMonth);

	// var row = jQuery(this).find(".MONTH").html();
	
	//var rowId = jQuery("#PMComplianceSmry").jqGrid('getGridParam','selrow');
if(jobType!=null ){
	
	var url="PMComplianceSummary_input.pmsmrRpt?q=2&";
	
	filterString="&jobType="+jobType+"&fromMonth="+fromMonth+"&toMonth="+toMonth;
	processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");	
	//alert(filterString);
	
}
	else{
		
		alert("Select The Activity Type ..");
		
	}
		});
function viewGrid(url,filterString)
{
	if(filterString == "q=2")
	{
		jQuery("#hiddenStr").val(filterString);
		//var tableCaption = "Improvement Vs Completed";
		filterString += '&drillFlag=f';
		processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");
		return true;
	}
	else if( validateFilterSelection(filterString))
	{
		jQuery("#hiddenStr").val(filterString);
		var tableCaption = "Improvement Vs Completed";
		filterString += '&drillFlag=f&jobType=ALL';
		
		processGridnew(url,filterString,"PMComplianceSmry","pager","","","","PMCompliance_loadComplete");
		return true;
	}
	return false;	
	}




function validateFilterSelection(filterString){
	return true;
}
function PMCompliance_loadComplete()
{
	 var row = jQuery("#PMComplianceSmry").jqGrid('getDataIDs');
	 var cm = jQuery("#PMComplianceSmry").jqGrid("getGridParam", "colModel");
	
	 for(var i=0;i<row.length;i++)
	 {
		
		 for(var j=0;j<cm.length;j++)
   		 {  
	   		   

			  if(row[i]=='5'  ){
				 
					jQuery("#PMComplianceSmry").setCell(row[i], cm[j].name.trim(), "",{'background-color':'#FEC488'});
	   	 	   }
	    	 
	    	 
			   
			   	
   		 }
	 }
}
function PMCompliance_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("PMComplianceSmry","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	
			
</script>

<div id="wrapperRpt">

	
	<!-- <label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
	<span style="padding-left:20px; margin-top:-10px;">
		<input  type="button" class="easyui-button" id="btnTabSelect" name="btnTabSelect" value="For Tab"/>		
	</span> -->
	<div class="clear"></div>
	<div>
	<span style="margin-left:350px;">
	
		<input type="checkbox" class="easyui-ceckbox" id="chkboxAll" name="chkboxAll"/>
	
	</span>
	<span >
	<label>All</label>
	</span>
	<span style="margin-left:30px;">
		<input type="checkbox" class="easyui-ceckbox" id="chkboxPrm" name="chkboxPrm"/>
	
	</span>
	<span>	<label>PRM</label>
	
	</span>
	<span style="margin-left:30px;">
			<input type="checkbox" class="easyui-ceckbox" id="chkboxCbm" name="chkboxCbm"/>
			
	</span>
	<span>
		<label>CBM</label>
	
	</span>
	<span style='margin-left:30px;'>
		<input type="checkbox" class="easyui-ceckbox" id="chkboxTbm" name="chkboxTbm"/>
		</span>
	<span>
		<label>TBM</label>
	
	</span>
	<span style="margin-left:20px;">
		<input type="button" class="easyui-button" id="btnView" name="btnView" Value="View"/>
		
	</span>
	<span style="margin-left:20px;">
		<input type="button" class="easyui-button" id="btnGraph" name="btnGraph" Value="Bar Graph"/>
		
	</span>
	<span style="margin-left:20px;">
		<input type="button" class="easyui-button" id="btnLineGraph" name="btnLineGraph" Value="Line Graph"/>
		
	</span>
	</div>
	
		<table id="PMComplianceSmry"></table>
	<div id="pager"></div>

	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hdnfromMonth" name="hdnfromMonth" value="${requestScope.fromMonth}"/>
<input type="hidden" id="hdntoMonth" name="hdntoMonth" value="${requestScope.toMonth}"/>
<input type="hidden" id="hiddenRemoveBlank" value=""/>
<input type="hidden" id="hiddencircle" value=""  />
<input type="hidden" id="hdnFnlnKeyid" />
	</div>	