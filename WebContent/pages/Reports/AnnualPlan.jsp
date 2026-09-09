<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){
	setLoadFormCallBackFrmId("frmannualPlanRpt");
	invokeAfterLoadFormCallBack();
	var url = jQuery('#hiddenUrl').val();
	//viewGrid(url,"?q=2");
	
	var urlArr = url.split("?");
	/*var factId =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var date =jQuery('#hiddendate').val(); 
	var shift =jQuery('#hiddenshift').val();				
	var dataStr ="q=2";
	if(factId != null && factId != '' && factId != ' ')
		dataStr+="&cmbFactid="+factId;
	if(sectId!= null && sectId != '' && sectId != ' ')
		dataStr+="&cmbSectid="+sectId;
	if(cellId!= null && cellId != '' && cellId != ' ')
		dataStr+="&cmbCellid="+cellId;
	if(date!= null && date != '' && date != ' ')
	{
		dataStr+= "&dtFromDate="+escape(date);
		dataStr+= "&dtToDate="+escape(date);
	}
	if(shift!= null && shift != '' && shift != ' ')
		dataStr+= "&shift="+shift;
		dataStr+= "&chkMonthwise=0";
	//if(factId.length>0)
		filterString = 	dataStr;*/
		
	if(jQuery('#hdnPrevDataUrl').val().length > 0) {
		viewGrid(url,filterString);
	}
	else
	{
		if(urlArr.length >=2)
			viewGrid(urlArr[0],urlArr[1]);
	}
});

function frmannualPlanRpt_afterLoadCallBack(){
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
	toggleCommonFilter();		
	else
		jQuery('#hdnSetFilterValues').val('Y');
}

function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{		
			jQuery('#hdnFilterstr').val(filterString);		
			
		if(jQuery('#hdnPrevDataUrl').val().length > 0){
			//alert("inside");
			var factId =jQuery('#hiddenfact').val();
			var sectId =jQuery('#hiddensect').val();
			var cellId =jQuery('#hiddencell').val();
			var date =jQuery('#hiddendate').val(); 
			var shift =jQuery('#hiddenshift').val();
				 //alert(factId +" -- "+sectId+" -- "+cellId+" -- "+date);
			var dataStr ="";
			
			    dataStr+="&cmbFactid="+factId;
			//if(sectId.substring(0,3)=='LIN')
				dataStr+="&cmbSectid="+sectId;
			//if(cellId.substring(0,3)=='CEL')
				dataStr+="&cmbCellid="+cellId;
			//if(date.substring(0,3)=='MCH')
				//datStr+="&cmbMchid="+rowid;
				dataStr+= "&dtFromDate="+escape(date);
				dataStr+= "&dtToDate="+escape(date);
				dataStr+= "&shift="+shift;
				dataStr+= "&chkMonthwise=0";
				if(factId.length>0)
				filterString = 	dataStr;			
		}
		
		processGridnew(url,filterString,"APgrid","APpager","","annualPlanDBLClick","","annualPlanGridComplete");
		
		return true;
	}
	return false;
}
function validateFilterSelection(filterString){
	
	var activity = getFilterValue(filterString, "chkActivity");
	
	if(activity=="Y")
		jQuery("#hdnActivity").val(activity);
	else
		jQuery("#hdnActivity").val("N");

	//var filterString = jQuery('#hiddenUrl').val();
	if(filterString.indexOf('filter')<0)
	{
		 if( filterString == "?q=2")
				return true;
			 else{ 
				 if( getFilterValue(filterString, "cmbSectid") == "" && getFilterValue(filterString, "cmbCircle") == "") 
					{
					alert("Select Section");
					return false;
					}
				else
					return  true;
			 }
	}
	 return true;
}
function annualPlanDBLClick(id){
var dataStr = "&Annual&Plan=Annual";
	dataStr += jQuery('#hdnFilterstr').val();
	var rowData = jQuery("#APgrid").jqGrid('getRowData',id);
	if(jQuery('#hdnFilterstr').val()  == "?q=2"){
	dataStr += '&cmbMchid='+rowData.machine_id;
	dataStr += '&cmbFactid='+rowData.factory_id;
	dataStr += '&cmbSectid='+rowData.line_id;
	dataStr += '&cmbCellid='+rowData.cell_id;
	}
	dataStr += '&fromMonth='+jQuery('#hdnMonth').val();
	dataStr += '&weekNo='+jQuery('#hdnWeekNo').val();
	dataStr += '&cmbAssmbid='+rowData.assembly_id;
	//alert(id+" -- "+dataStr);
	navigateToNextForm("pmActivity_input.prv?q=2&filterString="+dataStr);
}
function genWOView(id,cellidx,cellvalue){

	var colm = jQuery("#APgrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[cellidx].name;
	
	var weekNo = selId.substring(1, 2);
	var month = selId.substring(2,10);
	jQuery('#hdnWeekNo').val(weekNo);
	jQuery('#hdnMonth').val(month);
	
	/* jQuery("#hiddenrowid").val(selId);*/
}
function annualPlanGridComplete()
{
	jQuery('.ui-paging-info').css('font-size','12px');		
	if(screen.width <= 1024)
		jQuery( "#APgrid" ).setGridWidth("80%");	
	else
		jQuery( "#APgrid" ).setGridWidth("90%");
	
	 var row = jQuery("#APgrid").jqGrid('getDataIDs');
	
	 var cm = jQuery("#APgrid").jqGrid("getGridParam", "colModel");
	 var activity = jQuery("#hdnActivity").val();
	 var colStart = 13;
	 if(activity == "Y")
		 colStart = 15;
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=colStart;j<cm.length;j++)
     	 {
			  var zeroVal = jQuery("#APgrid").jqGrid('getCell',row[i],cm[j].name);			 
			  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
			  {
				  if(zeroVal =='A'){
					jQuery("#hdnAcivity").val("A");  
				  }
				  else if(zeroVal =='IC'){
					  jQuery("#hdnAcivity").val("IC");
				  }
				  if(zeroVal =='X'){
						
				  		jQuery("#APgrid").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#FF8040','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});
					  }
				  if(zeroVal =='1' || zeroVal =='C'){
					  
				  		jQuery("#APgrid").jqGrid('setCell',row[i],cm[j].name,"&#10003;",{'color':'#4D27C5','font-weight':'bold','font-size':'18px','background-color':'#ff8040'});
					  }
				  else if(zeroVal =='4'){
					
			  		jQuery("#APgrid").jqGrid('setCell',row[i],cm[j].name,".",{'color':'#ff8040','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});
				  }
				  else{
					 
					  jQuery("#APgrid").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#4D27C5','font-weight':'bold','font-size':'12px','background-color':'#ff8040'});
				  }
			  }
	    }
	 }
	 jQuery("#APgrid").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue) {
				
	 			genWOView(id,cellidx,cellvalue);
			}
	 });
}
function frmFilter_enableDisableSuccessCallBack()
{

	if(jQuery('#hdnSetFilterValues').val() == 'Y')
		setFilterValues();		
	//fillWithCurrentDate("dtefromDate");	
	//fillWithCurrentDate("dtetoDate");	
	enableFields('dteyear');
	//jQuery("#chkDatewise").attr('checked',true);
	jQuery("#chkMonthwise").attr('checked',false);	
	readOnlyFields('chkMonthwise');
	readOnlyFields('dtefromMonth');
	readOnlyFields('dtetoMonth');
	//enableFields('dtefromDate');
	//enableFields('dtetoDate');	
	//enableFields('chkDatewise');				
}

</script>
<form id="frmannualPlanRpt">
<div id="wrapperRpt">
<div style="width:110%" id="monthlyplan">
				<div style="float:left" id="minicon">
<!--					<img src="images/layout_button_left.gif" id="min" />-->
				</div>
				
				<div style="float:left;display:none;" id="maxicon">
					<img src="images/layout_button_right.gif" id="max" />
				</div>
				
				<div>
				<table  style="margin-top:2px;margin-left:10px;">
					<tr>
						<td><span class="mp-activity"></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Activity Exists</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity" ><label style="color:#0000ff;margin-left:4px;"><b>A</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Allotted</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity"><label style="color:#0000ff;margin-left:4px;"><b>IC</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Incomplete</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity"><label style="color:#0000ff;margin-left:4px;"><b>&#10003</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Completed</label></td>
					</tr>
				</table>
<!--					<span  style="padding-left:1%;"><img src="images/annualcal/annualplan1.png" id="show" /></span>-->
				</div>
<!--				 <div style="">-->
<!--				 	<div style="float:left;padding-left:3%;">-->
<!--				 		 <input type="button" id="wogen"  class="easyui-button" onclick="" value="Work Order Generation"/>-->
<!--				 	</div>-->
<!--				 	<div style="float:left;padding-left:10%">-->
<!--				 		<input type="button" id="view"  class="easyui-button" onclick="" value="View"/>-->
<!--               		 	<input type="button" id="filter"  class="easyui-button" onclick="" value="Filter"/>-->
<!--               		 	<input type="button" id="excel"  class="easyui-button" onclick="" value="Export To Excel"/>-->
<!--				 	</div>-->
<!--				 </div>-->
				  <div  style="float:left;padding-left:1%">
				  		<table id="APgrid" style="width:100%"><tr><td/></tr></table>
						<div id="APpager"></div>
				  </div>
				  <div class="clearfix"></div>
		</div>
 <div  style="float:left;padding-left:1%">
  		<table id="APgrid" style="width:100%"><tr><td/></tr></table>
		<div id="APpager"></div>
  </div>
</div>
<input type="hidden" id="hdnFilterstr" name="hdnFilterstr"/>
<input type="hidden" id="hdnWeekNo"/>
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
	<input type="hidden" id="hdnMonth"/>
<input type="hidden" id="hdnActivity"/>
</form>