 <script><!--
 jQuery(document).ready(function()
 {
	 initialiseForm('frmgrdIndicator');
	 jQuery('#submitForm').val('frmgrdIndicator');
	 jQuery( "#minicon" ).show();
	 jQuery( "#maxicon" ).hide();
	 
	 var url = jQuery('#hiddenUrl').val();
	 var  pillarId=jQuery("#hdnKinkPillarid").val();
	 viewGrid(url,"?q=2&pilllarId="+pillarId);
	 url="IndicatorGridEntry_view.keyPerInd";
	 viewGrid(url,"?q=2");
 });   
jQuery("#max").click(function()
{	 
	maxFnCall();
});
jQuery("#min").click(function() 
{
	minFnCall();
});  
function maxFnCall()
{
	jQuery( "#divIndicatorEntry" ).hide();
	jQuery( "#minicon" ).show();
	jQuery( "#maxicon" ).hide();
	
	jQuery("#divBtns").removeClass("divBtnsForKpiMin");
	jQuery("#indicatordiv").removeClass("IndicatordivMin");
	jQuery("#divIndicatorEntry").removeClass("divIndicatorEntryMin");
	
	jQuery("#divBtns").addClass("divBtnsForKpiMax");
	jQuery("#indicatordiv").addClass("IndicatordivMax");
	jQuery("#divIndicatorEntry").addClass("divIndicatorEntryMax");
	
	if(screen.width>1280 && screen.width<=1366)
		jQuery("#grdIndicator").setGridWidth(1220);		
	else if(screen.width>1024 && screen.width<=1280)
		jQuery("#grdIndicator").setGridWidth(1140);
	else if(screen.width<=1024)
		jQuery("#grdIndicator").setGridWidth(885);	
}
function minFnCall()
{
	jQuery("#divIndicatorEntry").show();
	jQuery("#minicon").hide();
	jQuery("#maxicon").show();
	
	jQuery("#divBtns").removeClass("divBtnsForKpiMax");
	jQuery("#indicatordiv").removeClass("IndicatordivMax");
	jQuery("#divIndicatorEntry").removeClass("divIndicatorEntryMax");
	jQuery("#divBtns").addClass("divBtnsForKpiMin");
	
	if(screen.width>1280 && screen.width<=1366)
		jQuery("#grdIndicator").setGridWidth(680);	
	else if(screen.width>1024 && screen.width<=1280)
	{
		jQuery("#grdIndicator").setGridWidth(660);		
		jQuery("#grdIndicatorEntry").setGridWidth(500);
	}
	else if(screen.width<=1024)
		jQuery("#grdIndicator").setGridWidth(525);	
	
	return;
}
 function viewGrid(url,filterString)
 {
 	if( validateFilterSelection(filterString))
 	{
 		var tableCaption = "";
 		if(url=="IndicatorGridKK_view.keyPerInd" || url=="IndicatorGridPM_view.keyPerInd" )
 	 	{
 			tableCaption = "Indicators";
 		    processGridnew(url,filterString,"grdIndicator","grdIndicatorpager",tableCaption,"","","indi_LoadComplete","","");
 	 	}
 		else if (url=="IndicatorGridEntry_view.keyPerInd")
 		{
 			tableCaption = "IndicatorsEntry";
 			processGridnew(url,filterString,"grdIndicatorEntry","",tableCaption,"","","loadComplete","","");
 	 	}				
 	} 	return true;		
 }
 function AddActionPlan(id,refId) 
 {
	var hdnFromLink="Active_Actual";//jQuery("#hdnFromLink").val();
 	var params="KPIActiveIndicator_input.ap?&mode=INSERT&from="+hdnFromLink;
 	var left="";
 	var height="";
 	var width="";
 	var top="";
 	if(id.trim().length>0)
 	 	params+="&cmbAplmKeyid="+id;
	 if(refId.trim().length>0)
		 params+="&refDocId="+refId+"&refDocType=KAI";
	 if(screen.width>1024 && screen.width<=1280)
	 {
		 left="10%";
		 height="88%";
		 width="81%";
		 top="-16%"; 
	 }
	 else if(screen.width<=1024)
	 {
		 left="6%";
		 height="80%";
		 width="85%"; 
		 top="-10%";
	 }
	 else if(screen.width>1280 && screen.width<=1366)
	 {
		left="11%";
		height="88%";
		width="77%";
		top="-20%"; 
	 }
 	LoadPopUp("divShowActionPlanSave",params,true,width,height,top,left,"multiSelectOk_Callback","Action Plan Save Entry","",true);
 }
 function fillCellValues(rowid,iCol,cellcontent)
 {
	 jQuery('#hdnActiveCell').val(iCol);
	 jQuery('#hdnRowId').val(rowid);
	 jQuery("#hdnCellContent").val(cellcontent);		
	 var cm = jQuery("#grdIndicator").jqGrid("getGridParam","colModel");
	 var allRows = jQuery("#grdIndicatorEntry").jqGrid('getRowData');
	 for(var j=1;j<=allRows.length;j++)
	 {
		for(var k=1;k<=cm.length;k++)
		{
			jQuery("#grdIndicatorEntry").jqGrid('setCell',j,"getKinkParentid"," "); 
			jQuery('#txtIndicatorsEntry_'+j+'_3').val("");
			jQuery("#cmbIndicatorsEntry_"+j+"_4").combobox("setValue", "");
			jQuery("#grdIndicatorEntry").jqGrid('setCell',j,"getKinkKeyid"," ");
			jQuery("#target_checkbox_"+j).attr('checked',false);
		}
	}
	if(iCol<=9)
	{
		for(var i=1;i<=iCol+1;i++)
		{
			var prevCol1 = parseInt(i)-1;
			var indicatorVal =jQuery('#spnIndicators_'+rowid+'_'+i).html();  
			var indicatorId =jQuery('#spnIndicators_'+rowid+'_'+i).attr('name');
			if(indicatorId.substring(0,3)=="spn")
				indicatorId="";
			var indicatorUom =jQuery('#spnIndicators_'+rowid+'_'+i).attr('uomId');
			var targetNeed=jQuery('#spnIndicators_'+rowid+'_'+i).attr('trgtNeed');
			if(i!=(iCol+1))
			{
				if(i == 1)
				{
					jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid"," ");
					jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid",indicatorId);	
				}
				else
				{	 
					var indicatorParentId =jQuery('#spnIndicators_'+rowid+'_'+prevCol1).attr('name');
					jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid"," ");
					jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid",indicatorParentId);
				}
				jQuery('#txtIndicatorsEntry_'+i+'_3').val("");
				jQuery('#txtIndicatorsEntry_'+i+'_3').val(indicatorVal);
				if(indicatorUom.trim().length>0)
				{
					jQuery("#cmbIndicatorsEntry_"+i+"_4").combobox("setValue", "");
					jQuery("#cmbIndicatorsEntry_"+i+"_4").combobox("setValue", indicatorUom);
				}
				jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkKeyid"," ");
				jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkKeyid",indicatorId);
				var chkName='target_checkbox_'+i;
				if(targetNeed=="Y")
					jQuery('#'+chkName).attr('checked','checked');
			} 
			else 
			{
				var indicatorParentId =jQuery('#spnIndicators_'+rowid+'_'+prevCol1).attr('name');
				jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkKeyid"," ");
				jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid"," ");
				jQuery("#grdIndicatorEntry").jqGrid('setCell',i,"getKinkParentid",indicatorParentId);
			}				
		}
	}
	return;
}
 function changeJqGridRowheight(jqgridID,height)
 {
	var trCountOri=jQuery('table#'+jqgridID+' tr:last-child').attr('id');
	trCount=parseInt(trCountOri,10)+1;
	for(var i=2;i<=trCount;i++)
	{
		jQuery('table#'+jqgridID+' tr:nth-child('+i+')').css('height',height);
	}	
	return ;
 }  
 function grdIndicator_keypress(keycode,iRow,iCol)
 {
	if(keycode==37  ||keycode==38 ||keycode==39 ||keycode==40 )
		fillCellValues(iRow,iCol,""); 
 }
 function indi_LoadComplete()
 {
	var cm = jQuery("#grdIndicator").jqGrid("getGridParam", "colModel");
	var allRows = jQuery("#grdIndicator").jqGrid('getRowData');
	for(var j=0;j<=allRows.length;j++)
	{
		for(var i=1;i<cm.length;i++)
		{
			var num=i-1;
			var type=jQuery("tr.jqgridheaderrow1 > th#CH0-"+num).text();
			if(type.charAt(1)=='M')
			{
				jQuery("#grdIndicator").jqGrid('setCell',j,i,"",{'background-color':'#F7D9C3'});
			}
			else if(type.charAt(1)=='P')
			{
				jQuery("#grdIndicator").jqGrid('setCell',j,i,"",{'background-color':'#F2D0D9'});
			}
			else if(type.charAt(1)=='A')
			{
				jQuery("#grdIndicator").jqGrid('setCell',j,i,"",{'background-color':'#CAEDF3'});
			}
		}
	}
	jQuery("tr.jqgridheaderrow1").hide();
	if(screen.width>1024 && screen.width<=1280)
	{
		jQuery("#grdIndicator").setGridWidth(1100);
		jQuery("#grdIndicator").setGridHeight(300);
	}
	else if(screen.width<=1024)
	{
		jQuery("#grdIndicator").setGridWidth(870);
	}
	else if(screen.width>1280 && screen.width<=1366)
	{
		jQuery("#grdIndicator").setGridWidth(1200);
	}
	if(jQuery("#sno").hasClass("snclass"))
	{}
	else
		jQuery('.jqgridheaderrow1').prepend('<th id="sno" class="snclass" style="width:15px;"></th>');
	jQuery("#grdIndicator").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
	{	
		changeJqGridRowheight("grdIndicatorEntry");
		fillCellValues(rowid,iCol,cellcontent);
	}	
 });	
}
 function text_formatter(id,options,rowObject)
 {
	var rowId = options.rowId;
	var count=jQuery("#hdnCount").val();
	var id='spnIndicators_'+rowId+'_'+options.pos;
	var valueOr = rowObject[options.pos-1];
	var keyid="";
	var value="";
	var colVal="";
	var trgtNeed="";
	var uomid="";
	if(rowId>=1)
	{
		if(options.pos<=parseInt(count,10))
		{
			if(valueOr.trim().length > 0 )
			{	
				colVal=valueOr.split(';');
				if(colVal[0].trim().length>0)				
					value=colVal[0];
			    else
				    value="";
				if(colVal[1].trim().length>0)
					keyid=colVal[1];
				else
					keyid="";
				if(colVal[2].trim().length>0)
					uomid=colVal[2];
				else
					uomid="";
				if(colVal[3].trim().length>0)
					trgtNeed=colVal[3];
				else
					trgtNeed="";
			}
			else
			{
				keyid=id;
				value="";
				uomid="";
				trgtNeed="";		
			}
		}
		else
		{
			keyid=id;
			value=valueOr;
			uomid="";
			trgtNeed="";	
		}
	}
	var spanElmt = '<span id="' + id + '" name="' + keyid + '" uomId="' + uomid + '" trgtNeed="' + trgtNeed + '" type="text" value="' + value + '"  style="width:203px;text-align:right;">' + value + '</span>';
	return spanElmt;
 }
 function chk_formatter(id, options, rowObject)
 { 
 	var rowId = options.rowId;
 	return '<input id="target_checkbox_'+rowId+'" name="target_checkbox" '+ (rowObject[2]=="N" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
 }
 function chkboxCheck(rowId)
 {
	jQuery("#grdIndicatorEntry").jqGrid('setCell',rowId,'getKinkTargetneed','Y');	
 }
 function chkboxUnCheck(rowId)
 {
	jQuery("#grdIndicatorEntry").jqGrid('setCell',rowId,'getKinkTargetneed','N');
 }
 function text_formatterforlowergrd(id,options,rowObject)
 {
	 var rowId = options.rowId;
	 var id='txtIndicatorsEntry_'+rowId+'_'+options.pos;
	 return '<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="4" cols="100"  id="'+id+'" name="'+id+'" type="text" class="easyui-text" value=""  style="width:202px;height:100px;"></textarea>';
 }
 function cmb_formatter(id,options,rowObject)
 {
	var rowId = options.rowId;
	var id='cmbIndicatorsEntry_'+rowId+'_'+options.pos;
	return '<input  id="'+id+'" name="'+id+'" type="text" class="easyui-combobox"  style="width:100px; "/>';	
 }
 function btnDeleteFormatter(id, options, rowObject)
 {
	 var id = options.rowId;
	 return '<input type="button" style="width:24px;" class="grdButton"  value="" onclick="deleteData(\''+id + '\');"/>';  	
 }
 function validateFilterSelection(filterString)
 {
	return true;
 }
 function deleteData(id)
 {
	 var retVal = confirm("Do You Want To Delete ?");
	 if( retVal == true )
	 {
		 var rowData = jQuery("#grdIndicatorEntry").jqGrid('getRowData',id);
		 if(rowData.getKinkKeyid.trim().length>0)
		 {
		 	params="q=2&keyId=" + rowData.getKinkKeyid;;
		 	processAjaxCalls('keyIndllevelDel_validate.keyPerInd','?' + params,'nodeDelValidateSuccess','nodeDelValidateFail');		
		 }
		 else
		 {
			 jQuery('#txtIndicatorsEntry_'+id+'_3').val("");
			 jQuery("#cmbIndicatorsEntry_"+id+"_4").combobox("setValue", "");
			 jQuery("#grdIndicatorEntry").jqGrid('setCell',id,"getKinkKeyid"," ");
			 jQuery('#target_checkbox_'+id).attr('checked',false);
		 } 	 
	  }
	  else
	  {
	      return false;
	  }	
 }
 function nodeDelValidateSuccess(result)
 {
	var validate="";
	validate=result.successData.msg;
	if (validate.trim()=="Valid") 
	{
		var originalid = result.successData.keyid;	
		var pillarId=jQuery("#hdnKinkPillarid").val();
		var params="";	
		params="mode=DELETE&keyId="+originalid+"&pillarId="+pillarId ;
		deleteRecord("frmgrdIndicator",'keyInd_delete.keyPerInd?'+ params);
	}
	else
	{
		alert("Indicator  Referred ");
		return false;
	}	
 }
 function nodeDelValidateFail(result)
 {
	return false;
 }
  
 function loadComplete()
 {
	 var allRows = jQuery("#grdIndicatorEntry").jqGrid('getRowData');
	 for(var i=1;i<=allRows.length;i++)
	 {
		 var comboid='cmbIndicatorsEntry_'+i+'_4';
		 initialiseComboBox(comboid); 
		 fillComboBox("frmKeyPerIndicatorPopup",comboid,"combo_Uom.keyPerInd");	
	 }
	 for(var i=1;i<=allRows.length;i++)
	 {
		 jQuery('#grdIndicatorEntry .easyui-text').css('text-transform','uppercase');
	 }
	 changeJqGridRowheight("grdIndicatorEntry","100px");	
 }
 jQuery("#btnaddNew").click(
	function()
	{
		minFnCall();	
		var selCell=getSelectedCellColumn("grdIndicator");
		var actPlanindicatorId =getActionPlanId();
		if((selCell!=null ||selCell!=" "||selCell!='') && parseInt(selCell)<9 && parseInt(selCell)>0 )
		{
			jQuery('#txtIndicatorsEntry_'+selCell+'_3').val("");
			jQuery("#cmbIndicatorsEntry_"+selCell+"_4").combobox("setValue", "");
			jQuery('#grdIndicatorEntry').jqGrid().setCell(selCell,1,null);
			var chkName='target_checkbox_'+selCell;
			jQuery('#'+chkName).attr('checked',false);
		}
		else if(selCell==10)
		{
			var ctrlid=getSelCtrlId();
			var apId = jQuery("#"+ctrlid).html();
			if(apId.trim().length!=0)
				return false;
			if(actPlanindicatorId.substring(0,3)=="KIN")
			{
				AddActionPlan(apId,actPlanindicatorId);
			}
			else 
				alert('Enter KAI To Create an Action Plan');
		}
		else if(selCell=='-1') 
		{
			jQuery("#grdIndicatorEntry").trigger('reloadGrid');
		}
		selCell="";
		jQuery("#hdnActiveCell").val("");	
	});
 jQuery("#btnUpdate").click(
function()
{
	var selCell=getSelectedCellColumn("grdIndicator");
	var actPlanindicatorId =getActionPlanId();
	if(selCell==10)
	{
		var ctrlid=getSelCtrlId();
		var apId = jQuery("#"+ctrlid).html();
		if(apId.trim().length>0)
		{
			if(actPlanindicatorId.substring(0,3)=="KIN")
			{
				AddActionPlan(apId,actPlanindicatorId);
			}
			else 
				alert('Enter KAI To Create an Action Plan');
		}
		else
			return false;
	}
	minFnCall();
});
function getSelCtrlId()
{
	var selCell=jQuery("#hdnActiveCell").val();
	var selRow=jQuery("#hdnRowId").val();
	var colNo=parseInt(selCell)-1;
	var ctrlid="spnIndicators_"+selRow+'_'+colNo;
	return ctrlid;
}
function getActionPlanId()
{
	var selCell=jQuery("#hdnActiveCell").val();
	var selRow=jQuery("#hdnRowId").val();
	var prevCol = parseInt(selCell)-2;//alert(prevCol );
	var actPlanindicatorId =jQuery('#spnIndicators_'+selRow+'_'+prevCol).attr('name');
	return actPlanindicatorId;
}
jQuery("#btnActualEntry").click(
function()
{
	var selCell=jQuery("#hdnActiveCell").val();
	var url="";
	var selCellNew =parseInt(selCell,10)-1;
	var type=jQuery("tr.jqgridheaderrow1 > th#CH0-"+selCellNew).text();
	var pillCode=jQuery("#hdnKinkPillarCode").val();
	var left="";
	var height="";
	var width="";
	var top="";
	if(screen.width>1024 && screen.width<=1280)
	{
		left="1%";
		height="87%";
		width="96%";
		top="-17%";
	}
	else if(screen.width<=1024)
	{
		left="2%";
		height="87%";
		width="92%";
		top="-9%";
	}
	else
	{
		left="2%";
		height="85%";
		width="94%";
		top="-17%";
	}
	if(type.charAt(1)=='M' ||type.charAt(1)=='P')
	{
		url="IndicatorActualMain_view.kpiActKk?&q=1&pillCode="+pillCode+"&type="+type.charAt(1)+"&frmButton=actual";
		LoadPopUp("divShowActual",url,true,width,height,top,left,"","Actual Entry","",true);
	}
	else if(type.charAt(1)=='A')
	{
		alert(' Actual Entry Not Available For Active Inidctors');
	}
	else 
		return false;
	
});
jQuery("#btnTarget").click(
function()
{
	var selCell=jQuery("#hdnActiveCell").val();
	var pillCode=jQuery("#hdnKinkPillarCode").val();
	var url="";
	var selCellNew =parseInt(selCell,10)-1;
	var type=jQuery("tr.jqgridheaderrow1 > th#CH0-"+selCellNew).text();
	var left="";
	var height="";
	var width="";
	var top="";
	if(screen.width>1024 && screen.width<=1280)
	{
		left="1%";
		height="87%";
		width="96%";
		top="-17%";
	}
	else if(screen.width<=1024)
	{
		left="2%";
		height="87%";
		width="92%";
		top="-9%";
	}
	else
	{
		left="2%";
		height="85%";
		width="94%";
		top="-17%";
	}
	if(type.charAt(1)=='M' ||type.charAt(1)=='P')
	{
		url="IndicatorActualMain_view.kpiActKk?&q=1&pillCode="+pillCode+"&type="+type.charAt(1)+"&frmButton=target";
		LoadPopUp("divShowActual",url,true,width,height,top,left,"","Target Entry","",true);
	}
	else if(type.charAt(1)=='A')
	{
		alert(' Target Entry Not Available For Active Inidctors');
	}
	else 
		return false;
});
function getSelectedCellRow(gridId)
{
	return jQuery('table#'+gridId+' tr.selected-row ').index();
}
function getSelectedCellColumn(gridId)
{
	return jQuery('table#'+gridId+' tr.selected-row  > td.ui-state-highlight ').index();
}
function frmgrdIndicator_beforeSubmit()
{       
	var allRows = jQuery("#grdIndicatorEntry").jqGrid('getRowData');
	for(var i=1;i<=allRows.length;i++)
	{
		var chkName='target_checkbox_'+i;
		var isChecked=jQuery("#"+chkName).attr('checked');
		if(isChecked)
			chkboxCheck(i);
		else
			chkboxUnCheck(i);
	}
	var reqColsArr=new Array("getKinkIndicatorname","getKinkKeyid","getKinkUomid","getKinkLevelno","getKinkParentid","getKinkType","getKinkTargetneed");
	var retData=JqGridToJsonSelRowsReqColscustom("grdIndicatorEntry",'getKinkIndicatorname',reqColsArr);
	if(retData.trim().length==0)
		return false;
	else
	{
		var gridData ='&Indicators='+retData;
		return gridData;
	}
} 
function frmgrdIndicator_successsCallback(result)
{
	jQuery("#hdnKinkPillarid").val(result.successData.pillarid);
	jQuery("#hdnKinkPillarCode").val(result.successData.pillCode);
	jQuery("#grdIndicator").trigger('reloadGrid');
	maxFnCall();
}
function frmgrdIndicator_deleteSuccessCallback(result)
{
	jQuery("#hdnKinkPillarid").val(result.successData.pillarId);
	jQuery("#hdnKinkPillarCode").val(result.successData.pillCode);
	jQuery("#grdIndicator").trigger('reloadGrid');
}
function JqGridToJsonSelRowsReqColscustom(jqGridId,ckeckForSelColName,requiredColArr)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var reqColLength=requiredColArr.length;
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		var cellValue = parseJqGridCellValue(row[ckeckForSelColName]);
		if( value != null  &&  value.trim()  != "" &&  value.trim().length  != "0" && cellValue.trim().length>0 && cellValue.trim()!=" " )
		{
			jsonArrO += '{';
			for(var colName in row) 
			{
				for(var j=0;j<reqColLength;j++)
				{
					if(requiredColArr[j]== colName )
					{
						var cellValue = parseJqGridCellValue(row[colName]);
						if( cellValue.trim().length > 0 && cellValue!=" "  ) 
						{
							jsonArrO += '"'+colName +'":"' + escape(cellValue) +'",';	
						}
					}					
				}		
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}			
</script>
 <form id="frmgrdIndicator">
 <div >
 <label class="notes">Select Cells & Click Buttons to Save or Update</label>
 </div>
 <table width="100%">
 	<tr>
 		<td width="56%" valign="top">
 		<div style="float:left;height:auto;margin-left:30px;" >
						<span style="background-color:#F7D9C3;color:#F7D9C3;border:solid 1px;font-size:10;">.......</span>
						<label style="font-weight:bold;font-size:12px; ">KMI</label>
						<span style="background-color:#F2D0D9;color:#F2D0D9;border:solid 1px;font-size:10">.......</span>
						<label style="font-weight:bold;font-size:12px;">KPI</label>
						<span style="background-color:#CAEDF3;color:#CAEDF3;border:solid 1px;font-size:10">.......</span>
						<label style="font-weight:bold;font-size:12px;">KAI</label>
						</div>
 		<div id="divBtns" class="divBtnsForKpiMax">
	 			<table style="margin-top:0px;">
				 	<tr>
				 	
				 		<td>
					 		<input type="button" id="btnaddNew" class="easyui-button" value="New" style="height:21px"/>
					 	</td>
					 	<td>
					 		<input type="button" id="btnUpdate" class="easyui-button" value="Update" style="height:21px"/>
					 	</td>
					 	<td>
					 		<input type="button" id="btnTarget" class="easyui-button" value="Target" style="height:21px"/>
					 	</td>
					 	<td>
					 		<input type="button" id="btnActualEntry" class="easyui-button" value="Actual" style="height:21px"/>
					 	</td>
					 	<td>
					 		<div style="float:left" id="minicon">
								<img src="images/layout_button_left.gif" id="min" />
							</div>
							<div style="float:left;display:none;" id="maxicon">
								<img src="images/layout_button_right.gif" id="max" />
							</div>
					 	</td>
					 	
					 </tr>
				 </table>
 			</div>
 			<div class="easyui-paddingbfpx" id="indicatordiv" style="margin-top:1px;">
			     <table id="grdIndicator" style="width:100%">
			     </table>
			     <div id="grdIndicatorpager"></div>
		   </div>
 		</td>
 		<td width="40%" style="margin-top:5%;float:left;" >
 			
				
 			<div id="divIndicatorEntry"  style="display:none;">
			  	<table id="grdIndicatorEntry" style="width:100%">
			     </table>
			     
			     <div id="grdIndicatorEntrypager"></div>
			</div>
 		</td>
 	</tr>
 </table>
	 
 


<input type="hidden" id="hdnRowId" value=""/>
<input type="hidden" id="mode" name="mode" value=""/>
<input type="hidden" id="hdnActiveCell" value=""/>
<input type="hidden" id="hdncellcontent" value=""/>
<input type="hidden" id="hdnKinkPillarCode" name="hdnKinkPillarCode"  value="${requestScope.pillCode}"/>
<input type="hidden" id="hdnKinkPillarid" name="hdnKinkPillarid"  value="${requestScope.pillarid}"/>
<input type="hidden" id="hdnCount" name="hdnCount"  value="${requestScope.count}"/>
 </form>