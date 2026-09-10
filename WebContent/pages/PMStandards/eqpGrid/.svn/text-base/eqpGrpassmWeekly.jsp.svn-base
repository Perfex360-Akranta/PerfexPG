<script type="text/javascript">
jQuery(document).ready(function(){
	
	//jQuery('.main-cntborder').css('height','345px'); 
	jQuery('#mainEqpGroup').css('display','block');
	jQuery('.eqpFrm').css('display','none');
	var url = jQuery('#hiddenUrl').val();
	
	var fltrStr = jQuery('#hdnEqpGrpKeyId').val();
			
		if (fltrStr != ' ' && fltrStr!= '' && fltrStr != null)
			fltrStr  = "&cmbEqpGrpid="+fltrStr ;
		else
			fltrStr  = '';
		processGridnew("assmWeekly_input.eqpStd",fltrStr,"weekWise_Grid","pager_weekWise","","assmWeekly_dblclick","","loadComplete");
});
function assmWeekly_dblclick(rowid){
	var chk_zero = jQuery('#chkVal').val();
	var iCol = jQuery('#getiCol').val();
	//alert(iCol+"---"+chk_zero);	
	var  cellcontent=  jQuery('#getcellContent').val();	
	if(chk_zero != 'zero'){
		cell_click(rowid,iCol,cellcontent);
	}
	else
		openNewStandard(rowid,iCol,cellcontent);
}
function loadComplete(ids){
	var rowid = jQuery("#weekWise_Grid").jqGrid('getDataIDs');
	 var cm = jQuery("#weekWise_Grid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<rowid.length;i++)
	 {
		 for(var j=2;j<cm.length;j++)
     	 {
		  var zeroVal = jQuery("#weekWise_Grid").jqGrid('getCell',rowid[i],cm[j].name);	
			//alert("zeroVal"+		zeroVal); 
			if(zeroVal =='1')	{		
				jQuery("#weekWise_Grid").jqGrid('setCell',rowid[i],cm[j].name,"&#10003;",{'color':'blue','font-weight':'bold','font-size':'18px'});
	     	 }
			else{
				 jQuery("#weekWise_Grid").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px'});
			 }
	 	  }
	 }

jQuery("#weekWise_Grid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
	
	jQuery('#getiCol').val(iCol);	
	
	jQuery('#getcellContent').val(cellcontent);
	if(cellcontent != 0){
		jQuery('#chkVal').val('val');
		//cell_click(rowid,iCol,cellcontent);	
	}
	else{
		jQuery('#chkVal').val('zero');
		//openNewStandard(rowid,iCol,cellcontent);
	}
	}});
}
function cell_click(rowid,iCol,cellcontent){
	var cmbEqpGrpid = jQuery('#hdnEqpGrpKeyId').val();
	var rowData = jQuery("#weekWise_Grid").jqGrid('getRowData',rowid);																								
	var assmId = rowData.Keyid;
	//alert(cmbEqpGrpid );
	navigateToNextForm("pmActivity_input.eqpStd?q=2&cmbEqpGrpid="+cmbEqpGrpid+'&cmbAssmbid='+assmId+'&loadContentDivId=Loadactivitygridfrm&preLoadContentDivId='+"preloadDIVid3");	
}
function openNewStandard(rowid,iCol,cellcontent){
	var rowData = jQuery("#weekWise_Grid").jqGrid('getRowData',rowid);																								
	var assmId = rowData.Keyid;
	//alert(assmId);
	var eqpgroupID = jQuery('#hdnEqpGrpKeyId').val();
	var filterString = '&cmbAssmbid='+assmId;
	//filterString += '&eqpgroupID='+eqpgroupID;
	navigateToNextForm("equipmentGroupstd_input.eqpStd?q=2&filterString="+filterString);
}
</script>

<form id="frmassmGroup">
<table border="0" class="tablealign-center" style="margin-left:0%;">
    <tr>
        <td>
			<div class="notes" style="text-align: left;width:88%;"><span>Double Click on week cell to edit activities</span>
			    <span style="float: right;margin-right:30px;">
			     <input type="button" value="Filter" id="btnfilter" class="easyui-button" style="height:21px;">
			     <input type="button" value="Back" id="btnBack" class="easyui-button" style="height:21px;">
			    </span>
			    </div>
        </td>
    </tr>
    <tr>
    	<td style="float:left">
			 <div class="easyui-paddingbfpx" style="float:left;">
			     <table id="weekWise_Grid" style="width:100%"><tr><td/></tr></table>
			     <div id="pager_weekWise">
			     </div>
			 </div>
			<!-- <input type="hidden" id="fltrStr" value="${requestScope.filterString_assmweek}"/>-->
		</td>
    </tr>
</table>
</form>