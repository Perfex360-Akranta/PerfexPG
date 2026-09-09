
<script type="text/javascript" >
var status;
var gridid="techocloseGrid";
jQuery.noConflict();
jQuery(document).ready(function(){
	//alert("sample");
	initialiseForm('frmtecoclose');
	jQuery('#submitForm').val('frmtecoclose');
	var url = jQuery('#hiddenUrl').val();
	var orderno=jQuery('#txtwono').val();
	//var status=jQuery('#cmbStatus').val();
	//var url="techountecho_input.techountecho";
	//var url = jQuery('#hiddenUrl').val();
	//jQuery("input[type=submit]").attr("disabled", "disabled");
	//jQuery('input[type="submit"]').prop('disabled', true);
	//jQuery( "#btnupdate" ).prop( "disabled", false );
jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
jQuery("#btnclose").attr("disabled", true).addClass("ui-state-disabled"); 
  viewGrid(url,"q=2&statusvalue="+"TECHO");
  var status= jQuery("#cmbStatus").val();
  var tec="TECHO";
 // alert(status);
   if(status==tec)
	   {
	 //   alert("ISNIDE THE IF IN DOCUMENTATION");
	   //jQuery("#btnupdate").attr("disabled", true).removeClass("ui-state-disabled"); 
	   }
  //var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
  //alert(statusvalue);
 // if(statusvalue=="TECHO")
	 // {
	 //  readOnlyField("btnclose");
	//  }

	//status =jQuery("#cmbStatus option:selected").val();

	



	

	
	
});

/*jQuery('#cmbStatus').click(function() {
	  alert('Handler for .select() called.');
	});
*/


	
function docDoubleClick(id) { 
	//var rowData = jQuery("#list").jqGrid('getRowData',id);
	//var level = rowData.frl_level;
	//var Flid =  rowData.flid;
	//navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
}

function viewGrid(url,dataString)
{
	
	processGridnew(url,dataString,"techocloseGrid","tecoclosePager","","nxtgrid","","docDoubleClick");
	//processGridnew(url,filterString,"techocloseGrid","tecoclosePager","Cost Information","costInfo_dblClick","","LoadInforGrid");

}

function getSelectedCellRow(gridid)
{
	
  var value= jQuery('table#'+gridId+' tr.selected-row ').index();
  return value;
}

/*function techocloseGrid_selectRow(id){
	//var rowData = jQuery("#techocloseGrid").jqGrid('getRowData',id);
	// mainKeyid = rowData.cb;
	var row = jQuery("#techocloseGrid").jqGrid('getDataIDs');
	alert("row");
		/* var MachineId =jQuery("#techocloseGrid").jqGrid('getRowData', id,"rn");
		 var status=getFieldValue("cb_techocloseGrid"+id);
		 var selectval= jQuery("#techocloseGrid").jqGrid('getCell',id,"cb");
		 var chk=jQuery("#jqg_techocloseGrid_"+id).val();
		 alert("chk:::"+chk);
		 gridId="techocloseGrid";
		 if(jQuery('#jqg_'+ gridId +'_'+id).is(':checked'))
				 {
			 //jQuery("input[type=submit]").removeAttr("disabled");
			// jQuery("#btnupdate").attr("disabled", false).removeClass("ui-state-disabled");
				 }
		 
		 if(chk==on)
			 {
			 jQuery('#techocloseGrid').jqGrid().setCell(id,"cb","off");
			 }
		 alert("selectval"+selectval);
		 if(selectval=="false"){
				
				jQuery('#chkdmtcheckbx_'+rowid).attr('checked',true);
				chkboxUnCheck(id);
				}
		 alert("Y:"+status);
	 alert("mainKeyid::"+MachineId);
}

*/



function techocloseGrid_selectAll(id,status){
	   
  	for(var i=0; i<id.length; i++){
  		if(status)
  			selectData(id[i]);
  		else
  			unselectData(id[i]);
  	}
  }
function techocloseGrid_cb_onSelect(){
	
	 var totalVal=jQuery("#techocloseGrid").jqGrid('getCell', rowid,"cb");
}
function getSelectedCellColumn(gridid)
{
	return jQuery('table#'+gridId+' tr.selected-row  > td.ui-state-highlight ').index();
}
jQuery("#btnupdate").click(function(){

	
	//alert("statusvalue::"+statusvalue);
	  var workorder=jQuery('#txtwono').val();
	  var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	 //  alert("statusvalue:::"+statusvalue);
	   var techovalue="TECHO";

	    if(statusvalue==techovalue)
	    	{
		var selArray =  jQuery("#techocloseGrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
	  if(selArray !=null && selArray!=" " && selArray!=""){
		//var r = confirm("Do You Want To Update?");
	//	var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
//	alert("statusvalue:::"+statusvalue);
	
	
		for(var i=0;i<selArray.length;i++)
		{
	      // alert("INSIDE THE LOOP");
			selrowid=selArray[i];
	       //jsonArr+='[';
		   var Keyid =jQuery("#techocloseGrid").jqGrid('getCell', selrowid,"KEYID");
		   
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
	    //alert("UpdateList::"+UpdateList);
	  //  if(statusvalue.equals("TECHO"))
	    //	{
	    //	alert("INSIDE THE TECHO");
	        if(UpdateList!=null)
	    	{ var url="tecoclose_input.tecoclose";
	        	//alert("INSIDE THE IF");
	       processAjaxCalls("tecoclose_update.tecoclose","UpdateList="+UpdateList+"&statusvalue="+statusvalue,"workFlowStatusSuccess","");
	       clearField("txtwono");	
	       processGridnew(url,"q=2&statusvalue="+"TECHO","techocloseGrid","tecoclosePager","","nxtgrid","","docDoubleClick");
	       clearField("cmbStatus");
	    	}
	   
	  }
	  else{
		  
		  //alert("Pls Select Any One Record");
		  popupCommonErrorMsg('Select Any One Record ');
	  }
	    	}
	    else{
	    	popupCommonErrorMsg(' Select Undo-close Or Teco Value In Status');
	    }
});


jQuery("#btnclose").click(function(){

	
	var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	//alert("statusvalue::::"+statusvalue);
	
	  var workorder=jQuery('#txtwono').val();
	  var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	  
	  // alert("statusvalue:::"+statusvalue);
	   var closevalue="CLOSE";
	  if(statusvalue==closevalue)
		  {
		var selArray =  jQuery("#techocloseGrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
	  if(selArray !=null && selArray!=" " && selArray!=""){
		//var r = confirm("Do You Want To Update?");
		
		for(var i=0;i<selArray.length;i++)
		{
	      // alert("INSIDE THE LOOP");
			selrowid=selArray[i];
	       //jsonArr+='[';
		   var Keyid =jQuery("#techocloseGrid").jqGrid('getCell', selrowid,"KEYID");
		   
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
	    	{ var url="tecoclose_input.tecoclose";
	        	//alert("INSIDE THE IF");
	       processAjaxCalls("tecoclose_update.tecoclose","UpdateList="+UpdateList+"&statusvalue="+statusvalue,"workFlowStatusSuccess","");
	       clearField("txtwono");	
	       processGridnew(url,"q=2&statusvalue="+"CLOSE","techocloseGrid","tecoclosePager","","nxtgrid","","docDoubleClick");
		   
	    	}
	   
	  }
	  else{
		  
		  popupCommonErrorMsg('Select Any One Record');
	  }
		  }
	  else
		  {
		  popupCommonErrorMsg(' Select Undo-Teco  or Close Value in Status');
		  }
});

jQuery("#btnview").click(function(){
	
	var totalVal=jQuery("#techocloseGrid").jqGrid('getCell', rowid,"cb");
	//alert(totalVal);
	var url = jQuery('#hiddenUrl').val();
	var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
	//alert("statusvalue::::"+statusvalue);
	//alert("statusvalue::::"+statusvalue.length);
    var workorder=jQuery('#txtwono').val();
   //alert("workorder::"+workorder);
    var res = workorder.substring(0,3);
    var statuslen=0;
    if(statusvalue.lenghth==statuslen)
    	{
         var all="ALL";
    	}
    
    var all="ALL";
    var len=0;
    //alert(res);
    //alert("workorder.length:::"+workorder.length);
    if(workorder.length==0){
    if(workorder.length==len){
    var work=workorder;
    }
    }
    if(statusvalue==all&&workorder==work)
    	{
    	// alert("INSIDE THE IF ::"+workorder.length);
    	 popupCommonErrorMsg('select the Status or Enter the OrderNo');
    	 
    	
    	// clearField("txtwono");
    	//jQuery("#cmbStatus").combobox("clear");
    	// clearField("cmbStatus");
    	// statusvalue="ALL";
    	// workorder=0;
    	}
    
    else
    	{
    	//alert("INSIDE THE ELSE:"+statusvalue);
    	//alert("INSIDE THE ELSE:"+workorder);
    	   if(statusvalue.length==0&&workorder.length==0)
    	  {
	      popupCommonErrorMsg('select the Status or Enter the OrderNo');
    	   }
          else{ 
        	  
        if(workorder.length==0)
            	  {
            	    popupCommonErrorMsg('select the Status or Enter the OrderNo');
            	  }
              else{
	       processGridnew(url,"q=2&statusvalue="+ statusvalue+"&workorder="+workorder,"techocloseGrid","tecoclosePager","","nxtgrid","","docDoubleClick");
             }
              }
    	}
   // clearField("txtwono");	
	// clearField("cmbStatus");

});


jQuery("#cmbStatus").combobox({
	onSelect:function(recordid){			
		
		//alert(123);
		//alert("recordid"+recordid);
		if(recordid.id == 'TECHO')
		{		
		//	alert("TECHO LOOP");
			jQuery("#btnupdate").attr("disabled", false).removeClass("ui-state-disabled");
			//jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
			jQuery("#btnclose").attr("disabled", true).addClass("ui-state-disabled"); 
		}
		else if(recordid.id == 'CLOSE')
		{				
			
		//  alert("CLOSE LOO");
		  jQuery("#btnclose").attr("disabled", false).removeClass("ui-state-disabled"); 
		  jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled"); 
		 // jQuery("#btnclose").attr("disabled", true).addClass("ui-state-disabled"); 
			
		}
		else if(recordid.id == 'ALL')
			{
			jQuery("#btnupdate").attr("disabled", true).addClass("ui-state-disabled");
			 jQuery("#btnclose").attr("disabled", true).addClass("ui-state-disabled");
			}
		else
		{
			jQuery("#btnupdate").attr("disabled", false).addClass("ui-state-disabled");
			 jQuery("#btnclose").attr("disabled", false).addClass("ui-state-disabled");
			}
		
	}
});     	


/*jQuery("select[name='cmbStatus']").change(function()
		{
		    if(jQuery(jQuery(this)).val() == "CLOSE")
		    {
		    	//jQuery("#return_datetime_div").slideDown();
		    	alert(1);
		    }
		    else
		    {
		    	jQuery("#return_datetime_div").slideUp();
		    }
		}
		);
 */ 

</script>
<form id="frmtecoclose">
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
<!--			-->
<!--			<select class="easyui-text"	id="cmbStatus" name="cmbStatus" style="width:105px; height:24px;"">	-->
			<select id="cmbStatus" class="easyui-combobox" name="cmbStatus"   style="width:105px; height:24px;" >  		
				<option value='TECHO'>TECO</option>
				<option value='CLOSE'>CLOSE</option>
<!--				<option value='ALL'>  </option>-->
				
			</select>
		    </div>
		    </div>
			</td>
			<td >
			     <div style="margin-left:15%;margin-bottom: 5px;">
			       <div>
	  			       <label>Order No</label>                       
	                 </div> 
		           <div class="easyui-paddingbfpx"> 
		              <input type="text" class="easyui-text"  id="txtwono" name="txtwono" maxlength="12"   style=" width : 150px;height:23px; text-align:left;"  value=""/>
		                   </div>
		              </div>
			 </td>
			 <td>
			    <div style="margin-left:40%;margin-bottom: 1px;">
			        <input class="easyui-button" type="button" value="View" id="btnview"name="btnview" style="height: 25px;width:80px" />
			    </div>
			 </td>
			 <td>
			    <div style="margin-left:95%;margin-bottom: 1px;">
			         <input class="easyui-button" type="button" value="Undo-TECO" id="btnupdate"name="btnupdate" style="height: 25px;width:80px" />
		    	 </div>
			  </td>
			   <td>
			    <div style="margin-left:70%;margin-bottom: 1px;">
			         <input class="easyui-button" type="button" value="Undo-Close" id="btnclose"name="btnclose" style="height: 25px;width:80px" />
		    	 </div>
			  </td>
		  </tr>
		  <tr>
		  <div style="float: left;padding-left: 30px;margin-top: -15px">
<!--<div style="margin-left:4%; _margin-left:3%;margin-top:-33px; _margin-top:0%;"> -->
	 <table id="techocloseGrid"><tr><td/></tr></table>
	 <div id="tecoclosePager"></div>
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