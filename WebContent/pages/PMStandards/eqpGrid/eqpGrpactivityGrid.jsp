<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery('#backbutn').css('display','block');
		var fltrStr_actv = jQuery('#fltrStr_actv').val();
		if (fltrStr_actv != ' ' && fltrStr_actv!= '' && fltrStr_actv != null)
			fltrStr_actv  = fltrStr_actv ;
		else
			fltrStr_actv  = '';
		//alert(fltrStr_actv);
		jQuery("#top_div").css('display',"block");
		processGridnew("pmActivity_input.eqpStd",fltrStr_actv,"activityGrid","pager_actvity","","dblclick","","activity_loadcomplete");
		//jQuery('.main-cntborder').css('height','400px');	
		jQuery('#imgPendingWrkordr').css('display','block');
		 jQuery('#btnNewstd').css('display','block');
		 jQuery('#headin').css('display','block');
		 jQuery('#lblHeader').html("Activity Type ");														
	});
	function dblclick(id)
	{
		
		var rowData = jQuery("#activityGrid").jqGrid('getRowData',id);																								
		var peqpKeyid = rowData.keyid;
		//alert(peqpKeyid );   
		var hdnMode =jQuery('#hdnfield').val();
		var machineGrpId=jQuery('#hdnEqpGrpKeyId').val();;
		var filterData = '&peqpKeyid='+peqpKeyid;
		filterData += '&machineGrpId='+machineGrpId;
		
		//alert(hdnMode );
		  // alert(filterData);
		   //grid_row_click();
		 navigateToNextForm("equipmentGroupstd_modify.eqpStd?q=2&filterData="+filterData,"PMStandard");
		//navigateToNextForm('eqpStdnt_mntncform_modify.eqpStd?'+filterData,'PMStandard');
		     jQuery('#imgPendingWrkordr').css('display','none');
			 jQuery('#btnNewstd').css('display','none');
			 jQuery('#headin').css('display','none');
			 jQuery('#lblHeader').html("Activity Type ");
			 jQuery("#top_div").css('display',"none");
	}
	function activity_loadcomplete(ids){
			var ids =  jQuery("#activityGrid").getDataIDs();
			
			  for (var i = 0; i<ids.length; i++) 
			  {		
				  jQuery("#activityGrid").setCell(ids[i], 'frequency','', { 'color':'#000','background-color':'#266DAF'});
		  		  // jQuery("#grid2").setCell(ids[i], 'frequency', ' ', {});  
			  }
		}	
	function sprButton(cellvalue, options, rowObject)
	{					
		var rowId = options.rowId;
		var cm = jQuery("#activityGrid").jqGrid("getGridParam", "colModel");
		for(var i=0;i<cm.length;i++){
			if(cm[i].name =='spares' )
			{
				var formatStr ='';
				if(cellvalue == "Y" )
			     formatStr  += '<input type="button" id="sprbtn" style="height:19px;" class="easyui-button" value="..." onclick="sprbtnclick(\''+rowId + '\');"/>'
				     //<img src=images/spr_but.png style=/"cursor:pointer;/" id="sprbtn" onclick="sprbtnclick(\''+rowId + '\');"/>';
				else 
					formatStr  +='<span style=/"background-color:#fff;/"></span> '; 
				return formatStr  ;
			}
			
		}
	}
	function sprbtnclick(rowId){
		var rowData = jQuery("#activityGrid").jqGrid('getRowData',rowId);		
		var pmstdKeyid = rowData.keyid;
		processGridnew("sprpopGrid_input.eqpStd",'&pmstdKeyid='+pmstdKeyid,"sprpopGrid","pager_spr","","dblclick");
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
	/**END**/
	jQuery('#btnBack').click(function(){
		navigateToPrevForm();
		});
</script>
<form id="frmActivityGrid">
  <div id="actEqpGrpHeader">
  <span  style="font-weight:bold;padding-left:0px;display:none;" id="imgPendingWrkordr"> 
		<img src="images/blue-round.PNG" height="18px"/>&nbsp;&nbsp;<label style="font-size: 12px;vertical-align:4px;">Pending Work-Order</label>
	</span>
	<div id="headin" class="sub-header" style=" margin-bottom: 10px;">
    <span style="font-size:15px;font-weight:bold;" id="lblHeader">Activity Wise</span>
	<span style="float:right;">
	<input type="button" value="Back" id="btnBack" class="easyui-button" style="height:21px;">
	</span>
	</div>
</div>	
 <div class="easyui-paddingbfpx">
     <table id="activityGrid" style="width:100%"><tr><td/></tr></table>
     <div id="pager_actvity">
     </div>
 </div>
 <input type="hidden" id="fltrStr_actv" value="${requestScope.filtrstr}"/>
</form>