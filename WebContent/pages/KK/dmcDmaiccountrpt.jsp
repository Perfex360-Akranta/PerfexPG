<script type="text/javascript">
jQuery(document).ready(function(){

	fillComboBox("frmDmaicgridid","cmbWaveid","comboWave.prpo");

	var actionPart = jQuery('#hiddenUrl').val();		
	jQuery('#chkcompleted').attr('checked',true);
	viewGrid(actionPart,"?q=1");

	jQuery('#chkcompleted').click(function() {
		if(jQuery('#chkcompleted').is(':checked') ==  true){
			jQuery('#chkpending').attr('checked',false);
		}else if(jQuery('#chkcompleted').is(':checked') ==  false){
			jQuery('#chkpending').attr('checked',true);
		}
	});
	jQuery('#chkpending').click(function() {
		if(jQuery('#chkpending').is(':checked') == true){
			jQuery('#chkcompleted').attr('checked',false);
		}else if(jQuery('#chkpending').is(':checked') == false){
			jQuery('#chkcompleted').attr('checked',true);
		}
	});

	
	jQuery('#btnGraph').click(function(){
		/*if((jQuery('#chkpending').is(':checked') == false)&& (jQuery('#chkcompleted').is(':checked') == false))
		{
			alert(" Select Identified or Removed");
				return false;     
		}*/
			var rowid = jQuery("#dmaicgrd").jqGrid('getGridParam','selrow');
			var url="";
			
			if(rowid!=null && rowid!=''&& rowid!=' '&& rowid!=undefined && rowid!='undefined'){
				var rowData = jQuery("#dmaicgrd").jqGrid('getRowData',rowid);
				var selId = rowData.KEYFIELD1;
				var colData = selId.split("#");
				var conflid=colData[0];
				
				var flid =getValueBySeparator(selId,"PF","#");
				
				//if(jQuery('#chkpending').is(':checked') == true){
					var flag ="Y";
				//}else
				//	flag="";
				if(checkForZeroes("dmaicgrd",rowid,5))
					{	
					url = "dmcpiechart_chrt.prpo?flag="+flag +"&flid="+conflid; 
					showGraphData(url);
			    }else{ 
					alert("No Record to View Graph");
					return false;
			        }
		   }
			else{
				rowid="";
				url = "dmcpiechart_chrt.prpo?flag="+flag+"&flid="+flid;   
				showGraphData(url);

				}
			 
		});
});

 jQuery('#btnView').click(function(){
	 var wave = jQuery("#cmbWaveid").combobox("getText");
	 //var wave = getFieldValue("cmbWaveid");
     //alert("wave="+wave);
     if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddenci").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		var filterString = '&drillFlag=f&firstClick=Y';

		filterString += "&wave="+wave;
	 var tableCaption = "DMAIC Count";
	 var actionPart = jQuery('#hiddenUrl').val();
     processGridnew(actionPart,filterString,"dmaicgrd","dmaicpager",tableCaption,"doubleClickGrid","","dmaic_loadComplete");
     return true;
	}
     });

function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("dmaicgrd","KEYFIELD1",id,keyfieldData);
	
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}

function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	//alert("id:"+id);
	//alert("keyfieldData:"+keyfieldData);
	var rowId=jQuery('#dmaicgrd').jqGrid("getGridParam", 'selrow');	
	//alert("rowId:"+rowId);
	var celValue = jQuery('#dmaicgrd').jqGrid ('getCell', rowId, 'KEYFIELD1'); 
	//alert("celValue:"+celValue);
	//keyfieldData=setDrillDoubleClick("dmaicgrd","KEYFIELD1",id,keyfieldData);
	var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddenci").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		var filterString = '?q=2';
		filterString += '&drillFlag=f&firstClick=N';
		//alert("celValue.substr(0,12):"+celValue.substr(0,12));
		filterString += "&flid="+celValue.substr(0,12);//+"&Keyid="+celValue;

		//filterString += '&skipLine=Y';
		var tableCaption = "DMAIC Count";
		var actionPart = jQuery('#hiddenUrl').val();
		processGridnew(actionPart,filterString,"dmaicgrd","dmaicpager",tableCaption,"doubleClickGrid","","dmaic_loadComplete");
	//alert("keyfieldData:"+keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function dmaic_loadComplete()
{
	setDrillDownHeader("jqgh_dmaicgrd_CODEFIELD","dmaicgrd","KEYFIELD1");
//	setTotalRowCss('dmaicgrd');
}
function dmaicgrd_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("dmaicgrd","KEYFIELD1",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	

function validateFilterSelection(filterString){
	
		return true;
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddenci").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		filterString += '&drillFlag=f&firstClick=Y';
		//filterString += '&skipLine=Y';
		var tableCaption = "DMAIC Count";
		processGridnew(url,filterString,"dmaicgrd","dmaicpager",tableCaption,"doubleClickGrid","","dmaic_loadComplete");		
		return true;
	}
	return false;	
}

</script>
<form id="frmDmaicgridid" name="frmDmaicgrid">
<div id="wrapperRpt"style= margin-top:3px;>
<table>
      <tr>
          
           <td><div>
           <span style="border: solid 2px #c1c1c1;margin-left:8px ;" >
            <!--  <input id="chkcompleted" name="chkcompleted" type="checkbox" /><label>  Completed</label>
			<input id="chkpending" name="chkpending" type="checkbox" style="margin-left:6px ;"/><label> Pending</label>
			-->
	        <input id="btnGraph" class="easyui-button" style="height:25px;width:70px" type="button" value="Pie-Chart"/></span>  </div> </td>
	         <td>
           <div style="margin-top: -3px">
           <label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
          <span style="margin-left:250px;"><label>Wave</label></span>
          <span><input class="easyui-combobox" id="cmbWaveid" name="cmbWaveid" style="width : 80px;" value=" " /></span>
          <span><input class="easyui-button" id="btnView" style="height:25px;width:70px;margin-left:50px;" type="button" value="View"/></span>
          </div>
           </td>
            </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="dmaicgrd" ></table>
	                <div id="dmaicpager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	
	
	
	<input type="hidden" id="hiddenString" value="sdsdsw" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddenci" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
    <input type="hidden" id="hdnFipType" name="hdnFipType" value="${requestScope.fiptype}"/>
</form>