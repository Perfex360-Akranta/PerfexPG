

<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>	 
	jQuery('#cmbTemp').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery("#HSEAuditParameter").jqGrid({
		url:'',
		datatype: "local",
		data:[
			  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
			  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false}
			  	
		  ],
		  colNames:[ 'S.No','Item','Description','MaxPoint'],
			colModel:[ {name:'S.No',index:'S.No',editable:false, width:150},
			           {name:'Item',index:'Item',editable:false, width:200},					  
					   {name:'Description',index:'Description',editable:false, width:150},			           		
			           {name:'MaxPoint',index:'MaxPoint',editable:false, width:150},			   
					     ],
		rowNum:50,
		rowList:[5,10,20],
		rownumbers: true,
		shrinkToFit:false,	
		pager: '#pager', 
		sortname: 'id',
		viewrecords: true,
		sortorder: "asc", 
		caption:'HSE Audit Parameter',
		width:870,
		height:100,
		loadonce: true
	});
	
	

	
				</script>
				
<div  class="main-cntborder" style="height: 490px" align="center">
 		

<table width=80%>
<tr >
<td valign="top" width=33%>

<div style="padding-left:100px;">Template Name</div>
<div class="easyui-paddingbfpx" style="padding-left:100px;" >
<input id="cmbTemp" name="cmbTemp" class="easyui-combobox"  style="width:250px;" value=""  >
	</div>
	
<div style="padding-left:100px;">Name</div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input id="Name" type="text"  name="Name" value="" style="width: 250px; height : 21px;" height="10px" class="easyui-text";>
	</div>
	
<div style="padding-left:100px;"><label class="mandatory-lbl">Code</label></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input id="Code" type="text" class=easyui-text  name="Code" value="" style="width: 250px; height : 21px;" height="10px";>
	</div>
</td>

<td valign="top" width=33%>

<div style="padding-left:100px;">Remarks</div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<textarea rows="2"  cols="28" id="Remark" name="Remark"></textarea>
	</div>
	
	<div style="padding-left:100px;"><label class="mandatory-lbl">Revision No</label>
	<span style="margin-left:14px;"><label class="mandatory-lbl">Date</label></span> </div>
<div class="easyui-paddingbfpx" style="padding-left:100px;" >
<input id="RevisionNo" type="text" class=easyui-text name="RevisionNo" value="" style="width: 90px; height : 21px;" height="10px";>
<span ><input id="Unit" class="easyui-datebox" /></span> 
	</div>
	
</td>

<td valign="top" width=33%>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="Download Excel Format" class="easyui-button" style="height : 21px; width : 160px;" id="DownloadExcel"></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="Import From Excel" class="easyui-button" style="height : 21px; width : 160px;" id="ImportExcel"></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="File Manager" class="easyui-button" style=" height : 21px; width : 160px;" id="FileMgr"></div>
</td>
</tr>
</table>
<div title="Tab5 with sub tabs" closable="true" iconCls="icon-cut" style="padding-left:100px;width:960px;">
			<div class="easyui-tabs" fit="true" plain="true" style="height:300px;width:310px;">
				<div title="Template" style="padding:10px;">

<table id="HSEAuditParameter" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
</div>
</div>
</div>