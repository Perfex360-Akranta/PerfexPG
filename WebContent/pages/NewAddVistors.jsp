<script>
jQuery(document).ready(function(){
	var from = jQuery('#hdnfrom').val();
	var keyid = jQuery('#txtVisiMomsKeyid').val(); 
	processGridnew("Newvisitor_input.nmom","?q=2&from="+from+"&keyid="+keyid, "grdVisitor", "divPager","","docDoubleClick","","Load_Complete");
	if("ENT"==from.trim()){ 
		jQuery('#lblVisitor').text("External Resource");
		}
	else{ 
		jQuery('#lblVisitor').text("Visitor Name");
	}
	jQuery('#btnaddVisitor').click(function()
			{
		//var row = jQuery('#hdnLastRow').val();
		saveForm('frmVisitors',"MoMVisitorATT_save.mom");
		//addRow(row);
	});
});
/*function addRow(row){
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
jQuery("#grdVisitor").jqGrid('setCell',row.length ,'VistorName',getFieldValue('txtvisitor'));
jQuery("#grdVisitor").jqGrid('setCell',row.length ,'Purpose',getFieldValue('txtpurpose'));

}*/


	function docDoubleClick(id)
	{	

		var Mstkeyid=jQuery('#txtVisiMomsKeyid').val();
	    var rowData = jQuery("#grdVisitor").jqGrid('getRowData',id);
		var keyid = rowData.Keyid;	
	    var VisitorName =rowData.VistorName;
	    var Purpose =rowData.Purpose;
	    setFieldValue('txtVisiVisitorname',VisitorName);
	    setFieldValue('txtVisiPurpose',Purpose);
	    setFieldValue('hdnVisiKeyid',keyid);
	    
		//processAjaxCalls("SelectVisitor_select.mom", "VisitorKey="+keyid, 'remove_successCallBack','remove_errorCallBack')
	}
	function txtAttVisitorDeleteformatter(rowNo,options, rowObject)
	{
		//  var momrow=jQuery("#attandanceGrid").jqGrid('getDataIDs');//
		var keyid = rowObject[0];
		//alert("keyid " +keyid);
		return '<input id="btnDelete" class="grdButton" name="btnDelete" type="button" keyid="'+keyid+'" onclick=removeRecordATT("'+keyid+'"); style="text-align:center"/>';
		
	}
	function remove_successCallBack(result)
	{
		alert(result.successData);
		jQuery("#grdVisitor").trigger("reloadGrid");
	}

  function removeRecordATT(keyid)
	{
		 //alert(keyid);
	  if(keyid!=null && keyid!='undefined' && keyid!=""){
	     var r=confirm("Do You Want To Delete?");
	  if (r==true)
	    {
	       processAjaxCalls("AttendancesVisitor_remove.mom", "keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
	       return true;
		}
	  else 
		 return false;
	   }
		else{
		var r=confirm("Do You Want To Remove Row?");
		if (r==true)
		jQuery("#attandanceGrid").trigger("reloadGrid");
		else
		return false;
		} 
	
	}


	function frmVisitors_successsCallback(result)
	 {
        //alert(result.successData.MstKeyId);
		clearField('txtVisiVisitorname');
		clearField('txtVisiPurpose');
		clearField('hdnVisiKeyid');
		jQuery("#grdVisitor").trigger("reloadGrid");
		jQuery('#txtVisiMomsKeyid').val(result.successData.MstKeyId);
		
		
			          
	}

 
</script>
<form id="frmVisitors">
<div>
<table>
	<tr>
		<td style="padding-left:1px;padding-left:5px\9">
		
	<div><label id='lblVisitor' class="mandatory-lbl">Visitor Name</label></div>
	<div>
		<input type="text" id="txtVisiVisitorname" name="txtVisiVisitorname" class="easyui-text" style="width:200px;text-transform: uppercase;" value=" "/> 
	</div>
	</td>
	<td Style="padding-left:5px\9;padding-left:10px">
		
	<div><label>Purpose</label></div>
	<div>
		<input type="text" id="txtVisiPurpose" name="txtVisiPurpose" class="easyui-text" style="width:200px;text-transform: uppercase;" value=""/> 
	</div>
	</td>
	<td Style="padding-left:5px\9;padding-left:10px;padding-top:10px">
		<div>
			<input type="button" value="Add " id="btnaddVisitor" class="easyui-button" style="height: 20px"/>
		</div>
	</td>
	</tr>
</table>
<div style="margin-top:10px;"><table id="grdVisitor"><tr><td></td></tr></table><div id="divPager"></div></div>
</div>
<input type="hidden" id="hdnLastRow" value="0"/>
<input type="hidden" id="hdnfrom" value=" "/>
<input type="hidden" id="txtVisiMomsKeyid" name="txtVisiMomsKeyid" value="${requestScope.MasterKeyid}"/>
<input type="hidden" id="hdnVisiKeyid" name="hdnVisiKeyid" value=""/>

</form>