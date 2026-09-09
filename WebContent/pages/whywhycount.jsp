	
<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var url = jQuery('#hiddenUrl').val();		
	viewGrid(url,"fetch=true");
	jQuery('#btnGraph').click(function(){
		var Counter = jQuery("#chkCounter").is(':checked');
		var RootCause = jQuery("#chkRootCause").is(':checked');
		//alert(RootCause);
		if(RootCause!=null&&RootCause==true){
			showGraphData("WhywhycountRootCause_barchart.why");	
		}
		else if(Counter!=null&&Counter==true){
			showGraphData("WhywhycountCounter_barchart.why");	
		}else{
		showGraphData("Whywhycount_barchart.why");
		}
	});
	
	
	  jQuery("#chkRootCause" ).click(function() {  
   	jQuery('#chkCounter').attr('checked',false);
   	var RootCause = jQuery("#chkRootCause").is(':checked');
   	jQuery("#chkRootCause").val("RootCause");
   	viewGrid("WhywhyRootCausecount_input.why","?&q=2&type=RootCause");
	   });
	  
	  jQuery("#chkCounter" ).click(function(){
			var Counter = jQuery("#chkCounter").is(':checked');
   	jQuery('#chkRootCause').attr('checked',false);
		jQuery("#chkCounter").val("Counter");
   	viewGrid("WhywhyCounterMeasure_input.why","?&q=2&type=Counter");	 
	  });

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		jQuery('#hdnFilterString').val(filterString);
		var tableCaption = "Cummulative Report";
		processGridnew(url,filterString,"grdWhywhydrill","pager",tableCaption,"grdWhywhydrill_doubleClickGrid","","oplcumulativeOnload");
		return true;
	}
}

function grdWhywhydrill_doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("grdWhywhydrill","keyid2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function oplcumulativeOnload()
{
	setDrillDownHeader("jqgh_grdWhywhydrill_FLLOC","grdWhywhydrill","keyid2");
	setTotalRowCss('grdWhywhydrill');
}
function grdWhywhydrill_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("grdWhywhydrill","keyid2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}
 function validateFilterSelection(filterString)
 {
	
	return  true;
}


</script>
<form>
 <div id="wrapperRpt" style="max-width: 1210px;">
 <table>
 <tr>
 <td >
 <div style="margin-top: -28px">
<input type="button" class="easyui-button" value="Bar Graph" id="btnGraph" name="btnGraph" />
</div>
</td>
<td>
<label id="oplnotes" class="notes" style="font-weight: bold; margin-left:10px; display: none;">${requestScope.oplCumMsg}</label>
</td>
</tr>
<tr>
     <td>
				 <div style="margin-left:100px; margin-top:-25px;">			  
				 <input type="checkbox"  id="chkRootCause" name="chkRootCause"  value=""/>
				 <label style="color:green"><b>Root Cause</b></label>	
				  </div>
				</td>
			</tr> 		
			    <tr> 	             
 	             <td>
				 <div style="margin-left:200px; margin-top:-25px;">			  
				 <input type="checkbox"  id="chkCounter" name="chkCounter"  value=""/>
				 <label style="color:green"><b>Counter Measure</b></label>	
				  </div>
				</td>
</tr>
</table>

	
<div style="margin-top: -5px">
	<div id="divGraphContainer" ></div>	
	<table id="grdWhywhydrill" ></table>
	<div id="pager"></div>
	</div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hdnFnlnKeyid" />

</form>