<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
	{
	var url = jQuery('#hiddenUrl').val();
	//alert(url);

	viewGrid(url,"&s=1");
					
	});
function viewGrid(url,filterString)
{
		
	 if( validateFilterSelection(filterString))
	{//var url = jQuery('#hiddenUrl').val();
		//alert(url+""+filterString);
		var tableCaption = "PMStandard";
		 processGridnew(url,filterString,"grid2","pager","","dblclick");
		return true;
	}
	return false;	
}
function validateFilterSelection(filterString){
	return  true;
}
function dblclick(id)
{
	var rowData = jQuery("#grid2").jqGrid('getRowData',id);																								
	var pmstdKeyid = rowData.keyid;
	//alert(pmstdKeyid );   
	var hdnMode =jQuery('#hdnfield').val();
	
	var filterData = '&pmstdKeyid='+pmstdKeyid;
	//alert(hdnMode );
	   alert(filterData);
	   //grid_row_click();
	navigateToNextForm('prvnt_mntnc_ginfo_input.prv?q=2&loadContentDivId=bind_source_div_ginfo&preLoadContentDivId=preloadDIVid2'+filterData,'PMStandard');
	 
	   
}
jQuery('#btnNewStandard').click(function(){
	navigateToNextForm("prvnt_mntnc_form.prv?q=2&loadContentDivId=pmForm&preLoadContentDivId=preloadDIVid4",'PM Standard Form');
	
});
function sprButton(id, options, rowObject)
{					
	var rowId = options.rowId;
//  alert('s');
	return '<input type="button" id="sprbtn" class="easyui-button" value="Spare" onclick="sprbtnclick(\''+rowId + '\');"/>';
}
function sprbtnclick(rowId){
	var rowData = jQuery("#grid2").jqGrid('getRowData',rowId);		
	var pmstdKeyid = rowData.keyid;
	processGridnew("sprpopGrid_input.prv",'&pmstdKeyid='+pmstdKeyid,"sprpopGrid","pager_spr","","dblclick");
	jQuery( "#sparepopDiv" ).show();
	jQuery( "#sparepopDiv" ).dialog({
		autoOpen: false,
		modal: true,
		top  :180,
		height: 360,
		width : 660,
		title:"Spares Detail"		
	});
	
}
jQuery("#spr_close").click(function(){
	//alert("closed");		
	jQuery( "#sparepopDiv" ).dialog('close');
});
</script>
<form name="frmpmstdActivity" id="frmpmstdActivity" action="" method="post">

<table width="100%">
  <tr>
    <th ><div class="main-header" style=" margin-bottom: 10px;"><span>Active Wise</span></div></th>    
  </tr>
  <tr>
    <th ><div  style="margin-bottom: 10px;">
    <span class="notes floatleft" style="margin-left:70px;">
    <ul id="" style="list-style: circle">
    <li>Pending Work-Order</li>
    </ul>
    
    </span>
    <span  style="float: right;margin-right:50px">
    <input type="button" value="New Standard" class="easyui-button" id="btnNewStandard">
    <input type="button" value="Export to Excel" class="easyui-button" onclick="">
    <input type="button" value="Back" class="easyui-button" onclick="return find_wndw_click()">
    </span>
    </div></th>    
  </tr>
  <tr>
  <td >
  <div  class="easyui-paddingbfpx">
                <div  class="easyui-paddingbfpx">                    
                </div> 
                <div class="easyui-paddingbfpx">
                <table id="grid2" style="width:100%"><tr><td/></tr></table>
                <div id="pager2">
                </div>
                </div>
                </div>
  </td>
  </tr>
</table>

<div id="sparepopDiv" style="margin:10px;display:none;">
<table id="sprpopGrid" ><tr><td></td></tr></table>
<div id="pager-spr"></div>
<div style="float:right;margin-right:30px;margin-top:10px;"><input type="button" id="spr_close" name="spr_close" class="easyui-button" value="Cancel"/></div>
</div>
</form>