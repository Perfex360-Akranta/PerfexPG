<script>
jQuery(document).ready(function(){
	var from = jQuery('#hdnfrom').val();
	processGridnew("visitor_input.mom","?q=2&from="+from, "grdVisitor", "divPager","","","","Load_Complete");
	if("ENT"==from.trim()){ 
		jQuery('#lblVisitor').text("External Resource");
		}
	else{ 
		jQuery('#lblVisitor').text("Visitor Name");
	}
	jQuery('#btnaddVisitor').click(function(){
		var row = jQuery('#hdnLastRow').val();
		addRow(row);
	});
});
function addRow(row){
	var lastRow = jQuery('#hdnLastRow').val();
	 if ( row == null || row == '' || parseInt(row) <= 0) 
	    {
	       var emptyItem =[{Keyid:" ",visitorname: getFieldValue('txtvisitor') ,purpose:getFieldValue('txtpurpose')}];
	       jQuery("#grdVisitor").jqGrid('addRowData',1, emptyItem[0]);
     
	    }	
else
   {
	     for(var i=0;i<row.length;i++)
			lastRow = row[i];
	     	var emptyItem =[{Keyid:" ",visitorname:" ",purpose:" "}];
	        jQuery("#grdVisitor").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
   }
	 jQuery('#hdnLastRow').val();  
jQuery("#grdVisitor").jqGrid('setCell',row.length ,'visitorname',getFieldValue('txtvisitor'));
jQuery("#grdVisitor").jqGrid('setCell',row.length ,'purpose',getFieldValue('txtpurpose'));

}
 
</script>
<div>
<table>
	<tr>
		<td>
		
	<div><label id='lblVisitor'>Visitor Name</label></div>
	<div>
		<input type="text" id="txtvisitor" class="easyui-text" style="width:200px;"/> 
	</div>
	</td>
	<td>
		
	<div><label>Purpose</label></div>
	<div>
		<input type="text" id="txtpurpose" class="easyui-text" style="width:200px;"/> 
	</div>
	</td>
	<td>
		<div>
			<input type="button" value="Add " id="btnaddVisitor" class="easyui-button"/>
		</div>
	</td>
	</tr>
</table>
<div style="margin-top:10px;"><table id="grdVisitor"><tr><td></td></tr></table><div id="divPager"></div></div>
</div>
<input type="hidden" id="hdnLastRow" value="0"/>
<input type="hidden" id="hdnfrom" value="${requestScope.from} "/>