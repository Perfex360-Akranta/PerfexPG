<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	initialiseForm('frmDocManagerView');
	var url = jQuery('#hiddenUrl').val();
	fillComboBox("frmDocManagerView","cmbRefDocType","RefDocType.eupl");
	fillComboBox("frmDocManagerView","cmbRefDocNo","RefDocNo.eupl");
	fillComboBox("frmDocManagerView","cmbKeywords","Keywords.eupl");
	fillComboBox("frmDocManagerView","cmbDescription","Description.eupl");
	viewGrid(url,"");
});

function viewGrid(url,filterString){	
	if( validateFilterSelection(filterString)){		 
		processGridnew(url,"&q=2","DocMgrView","pager","","","","");
		return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	return  true;
}

function imgFormatDownload(id, options, rowObject)
{			
	var rowId = options.rowId;
	 			 
  	return '<input type="button" id="remov" class="grddownloadimg"  onclick="grddownloadimg(\''+rowId + '\');"/>';
}

function grddownloadimg(rowId)
{
	 var rowData = jQuery("#DocMgrView").jqGrid('getRowData',rowId);	
	 var keyid = rowData.hdnKeyid;
	 var filename = rowData.txtFilename;
	// alert(filename);
	// var dataString = '?q=2&keyid='+keyid+'&filename='+filename;
	// window.open("file_download.file?filename="+filename," ", "height=200, width=200");
	window.open("file_download.eupl?filename="+filename,"", "height=200, width=200");
}

jQuery("#btnview").click(function(){
	var url = jQuery('#hiddenUrl').val();
	var RefDocType=jQuery("#cmbRefDocType").combobox("getValue");
	processGridnew(url,"&RefDocType="+RefDocType,"DocMgrView","pager","","","","");

});

</script>

<form id="frmAuditStatusRpt">
<div id="wrapperRpt">
	 	<table style="margin-top:-16px;">
		<tbody>
		<tr>
		<td>
			<div id="status"  style="margin-left:-1%;margin-bottom: 9px;">
			<label>Doc. Type</label>
			<div>	
		<input type="text" class="easyui-combobox"  id="cmbRefDocType" name="cmbRefDocType" maxlength="10"  style=" width :150px;height:25px; text-align:left;" value=""/>					     		
		    </div>
		    </div>
			</td>
			
			<td>
			<div id="status"  style="margin-left:-1%;margin-bottom: 9px;">
			<label>Doc. No</label>
			<div>	
		<input type="text" class="easyui-combobox"  id="cmbRefDocNo" name="cmbRefDocNo" maxlength="10"  style=" width :150px;height:25px; text-align:left;" value=""/>					     		
		    </div>
		    </div>
			</td>
			
			<td>
			<div id="status"  style="margin-left:-1%;margin-bottom: 9px;">
			<label>Keywords</label>
			<div>	
		<input type="text" class="easyui-combobox"  id="cmbKeywords" name="cmbKeywords" maxlength="10"  style=" width :150px;height:25px; text-align:left;" value=""/>					     		
		    </div>
		    </div>
			</td>  
			 
			 <td>
			<div id="status"  style="margin-left:-1%;margin-bottom: 9px;">
			<label>Description</label>
			<div>	
		<input type="text" class="easyui-combobox"  id="cmbDescription" name="cmbDescription" maxlength="10"  style=" width :150px;height:25px; text-align:left;" value=""/>					     		
		    </div>
		    </div>
			</td> 
			  
			  
			   <td>
			    <div style="margin-left:30px;margin-bottom:5px;">
			     <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;">
		  </div>
			  </td>
			
		
		  </tr>
		 </table>
	 <table id="DocMgrView"><tr><td/></tr></table>
	 <div id="pager"></div>
 <input type="hidden" id="hdnJhamAuditpillar" name="hdnJhamAuditpillar" value="${requestScope.jhamAuditpillar}"/>
 <input type="hidden" id="hdnJhamAuditType" name=hdnJhamAuditType value="${requestScope.jhamAudittype}"/>
</form>