
<script type="text/javascript">
var status;
var gridid="techoviewGrid";
jQuery.noConflict();
jQuery(document).ready(function(){
	initialiseForm('frmtechno');
	jQuery('#submitForm').val('frmtechno');
//	var url = jQuery('#hiddenUrl').val();
	var orderno=jQuery('#txtwono').val();cmbStatus
	//var status=jQuery('#cmbStatus').val();
	//var url="techountecho_input.techountecho";
	//var url = jQuery('#hiddenUrl').val();
	readOnlyFields("cmbStatus");
	var url ="techountecho_input.techountecho";
	jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
   viewGrid(url,"q=2&statusvalue="+"TECHO");
	//status =jQuery("#cmbStatus option:selected").val();
	
});


	
function docDoubleClick(id) { 
	//var rowData = jQuery("#list").jqGrid('getRowData',id);
	//var level = rowData.frl_level;
	//var Flid =  rowData.flid;
	//navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
}

function viewGrid(url,dataString)
{
	
	processGridnew(url,dataString,"techoviewGrid","techoViewPager","","nxtgrid","","docDoubleClick");
	//processGridnew(url,filterString,"techoviewGrid","techoViewPager","Cost Information","costInfo_dblClick","","LoadInforGrid");

}

function getSelectedCellRow(gridid)
{
	return jQuery('table#'+gridId+' tr.selected-row ').index();
}
function getSelectedCellColumn(gridid)
{
	return jQuery('table#'+gridId+' tr.selected-row  > td.ui-state-highlight ').index();
}
jQuery("#btnupdate").click(function(){

	
	//alert("statusvalue::"+statusvalue);
	  var workorder=jQuery('#txtwono').val();
	  var techovalue="TECHO";

	  /*  if(statusvalue==techovalue)
	    	{*/
	
		var url ="techountecho_input.techountecho";
		var selArray =  jQuery("#techoviewGrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
	  if(selArray !=null && selArray!=" " && selArray!=""){
		//var r = confirm("Do You Want To Update?");
		var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	//alert("statusvalue:::"+statusvalue);
		for(var i=0;i<selArray.length;i++)
		{
	      // alert("INSIDE THE LOOP");
			selrowid=selArray[i];
	       //jsonArr+='[';
		   var Keyid =jQuery("#techoviewGrid").jqGrid('getCell', selrowid,"KEYID");
		   
		   var Criteriasplit= Keyid.split(',');
		 	for(var k=0;k<Criteriasplit.length;k++){
			 	var Keyidval=Criteriasplit[k]; 
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	//jsonArr += '"'+keyvalu + '",';	
			 	//alert("keyvalu"+keyvalu);
			 	jsonArr += '"'+keyvalu + '",';	
	 	    }
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
			jsonArr += ',';
		  //  alert("jsonArr "+jsonArr);
		}	
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
	    var UpdateList=jsonArr;
	   // alert("UpdateList::"+UpdateList);
	  //  if(statusvalue.equals("TECHO"))
	    //	{
	    //	alert("INSIDE THE TECHO");
	        if(UpdateList!=null)
	    	{//alert("INSIDE THE IF");
	       processAjaxCalls("techountecho_update.techountecho","UpdateList="+UpdateList+"&statusvalue="+statusvalue,"workFlowStatusSuccess","");
	    	//}
	     //  viewGrid(url,"q=2");
	      clearField("txtwono");	
	       processGridnew(url,"q=2&statusvalue="+"TECHO","techoviewGrid","techoViewPager","","nxtgrid","","docDoubleClick");
	    	}
	   
	  }
	  else{
		  
		  popupCommonErrorMsg('Pls Select Any One Record');
	  }
	    	//}
	  /*  else{
	    	
	    	popupCommonErrorMsg('Pls Select Teco-undo Button');
	    }*/
});
jQuery("#btnview").click(function(){
	var url = jQuery('#hiddenUrl').val();
	var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	//alert("statusvalue::::"+statusvalue);
    var workorder=jQuery('#txtwono').val();
   // alert("workorder::"+workorder);
    var res = workorder.substring(0,3);
    var all="ALL";
    //alert(res);
    var work="WB0";
    

	processGridnew(url,"q=2&statusvalue="+ statusvalue+"&workorder="+workorder,"techoviewGrid","techoViewPager","","nxtgrid","","docDoubleClick");


	 
	 



   // processGridnew(url,"q=2&statusvalue="+ statusvalue+"&workorder="+workorder,"techoviewGrid","techoViewPager","","nxtgrid","","docDoubleClick");



});

function techoviewGrid_selectRow(id){
	gridId="techoviewGrid";
/*	var row = jQuery("#"+gridId).jqGrid('getDataIDs');
	var keyId = jQuery("#"+gridId).jqGrid('getCell',id, 'KEYID');
	var taskrow = jQuery("#"+gridId).jqGrid('getDataIDs');
	var rowData = jQuery("#techoviewGrid").jqGrid('getRowData',id);
	alert("rowData::"+rowData);
	var selectval= jQuery("#techocloseGrid").jqGrid('getCell',id,"cb");
	alert("selectval"+selectval);
	var chk=jQuery("#jqg_techoviewGrid_"+id).val();
	alert("chk:::"+chk);
	//alert("keyId::"+keyId);
	//alert("row::"+row);
	/*for ( var i = 0; i < taskrow.length; i++) {
	rowid = taskrow[i];
	  //if(rowid==1)
	}*/
	/*for(var i=0; i<id.length; i++){
		
		alert(id.length);
	   if(jQuery('#jqg_'+ gridId +'_'+id).is(':checked')==)
	 {
//jQuery("input[type=submit]").removeAttr("disabled");
        jQuery("#btnupdate").attr("disabled", false).removeClass("ui-state-disabled");
	 }
	 else if(jQuery('#jqg_'+ gridId +'_'+id).is(':unchecked'))
		   
	    {alert("ISNIDE THE ELSE IF");
		   jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
	    }
	 else{
		 alert("INSIDE THE ELSE");
			jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
	 }
	}*/
	 if(jQuery('#jqg_'+ gridId +'_'+id).is(':checked'))
			 {
		        //jQuery("#techoviewGrid").jqGrid('setCell', rowId, 'Cb', '1');
		        var chk=jQuery("#jqg_techoviewGrid_"+id).val();
		       // alert("chk::"+chk);
			 }
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*alert("INSIDE THE SUBMIT");
	var ordertext = jQuery('#txtwono').val();
	
	alert("txtwono::"+ordertext);
	var gridval=getGridSelectArray('techoviewGrid');  
	alert("gridval"+gridval);
	var status =jQuery("#cmbStatus option:selected").val();
	alert("Status:"+status);
	var rowIds = jQuery('#techoviewGrid').jqGrid().getDataIDs();
	alert("rowIds"+rowIds);
	var grid = jQuery('#techoviewGrid');
	var sel_id = grid.jqGrid('getGridParam', 'selrow');
	alert("sel_id::"+sel_id);
	var selectValues=jQuery('#techoviewGrid').getCell(sel_id,"WOMSKEYID");
	alert("selectValues:::"+selectValues);
	//var mainTask = jQuery("#techoviewGrid").jqGrid('getCell',rowId, "txtRasdActivity");
//	alert("mainTask"+mainTask);
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#techoviewGrid').getCell(rowIds[i],"WOMSKEYID");
		alert("cellVal::"+cellVal);
	
	
		
		
	} 		
	

	var allRows = jQuery("#techoviewGrid").jqGrid('getRowData');
	alert("allRows:"+allRows);*/
/*	for(var i=1;i<=allRows.length;i++)
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
	}*/
//} 
  

</script>
<form id="frmtechno">
<div id="wrapperRpt" >
	<div id="completedBlock" Style="margin-top:-2px">
	
	
	 	<table style="margin-top:-16px;">
		<tbody>
		<tr>
		
			<!--<td>
			<div id="com">
				<span class="hse-comp"></span>
				</div>
			</td>
			--><!--<td>
			<div id="comptxt">
				<label style="color:black;font-weight: bold">Completed</label>
				</div>
			</td>
	
			--><!--<td>
				<span style="background-color:red;" class="hse-comp"></span>
			</td>
			<td>
				<label style="color:black;font-weight: bold">Red Tag</label>
			</td>
			--><td>
			<div id="status"  style="margin-left:-1%;margin-bottom: 9px;">
			<label>Status</label>
			<div>
			<select id="cmbStatus" name="cmbStatus" style="width:105px; height:24px;"   >				
				<!--<option value='ALL'>  </option>-->
				<option value='TECHO'>TECO</option>
				<!--<option value='UNTECHO'>UNDO-TECO</option>-->
		</select>
		    </div>
		    </div>
			</td>
			<td >
			     <div style="margin-left:10%;margin-bottom: 5px;">
			       <div>
	  			       <label>Order No</label>                       
	                 </div> 
		           <div class="easyui-paddingbfpx"> 
		              <input type="text" class="easyui-text"  id="txtwono" name="txtwono" maxlength="12"   style=" width : 150px;height:23px; text-align:left;"  value=""/>
		                   </div>
		              </div>
			 </td>
			 <td>
			    <div style="margin-left:20%;margin-bottom: 1px;">
			        <input class="easyui-button" type="button" value="View" id="btnview"name="btnview" style="height: 25px;width:80px" />
			    </div>
			 </td>
			 <td>
			    <div style="margin-left:80%;margin-bottom: 1px;">
			         <input class="easyui-button" type="button" value="Undo-TECO" id="btnupdate"name="btnupdate" style="height: 25px;width:80px" />
		    	 </div>
			  </td>
		  </tr>
		  <tr>
		  <div style="float: left;padding-left: 30px;margin-top: -15px">
<!--<div style="margin-left:4%; _margin-left:3%;margin-top:-33px; _margin-top:0%;"> -->
	 <table id="techoviewGrid"><tr><td/></tr></table>
	 <div id="techoViewPager"></div>
</div>
		  </tr>
		 </tbody>
		</table>
		
	
	 
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	
	<input type="hidden" id="hdnPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsMultiselect" value="${requestScope.isMultiselect}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnCondition" value="${requestScope.condition}" >
<input type="hidden" id="hdnSelectCount" value="" >
<input type="hidden" id="mode" value="">

	
	<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.loginUser}" >
	 </div>	
	 
</div>
</form>