<script type="text/javascript">
	jQuery(document).ready(function(){
		//setLoadFormCallBackFrmId('frmPmStandard');
		initialiseForm('frmequipmentlist');
		jQuery('#submitForm').val('frmequipmentlist');
		var cellid=jQuery("#hdncellid").val();
		var mchid=jQuery("#hdnmachId").val();
		
		var locnId = jQuery("#frmequipmentlist input[id='location']").val();
		var factId = jQuery("#frmequipmentlist input[id='factory']").val();
		var sectionId = jQuery("#frmequipmentlist input[id='section']").val();
		//var cellId = jQuery("#frmAccidentIncident input[id='cell']").val();
		//var machId = jQuery("#frmAccidentIncident input[id='machine']").val();
		//var flid = jQuery("#frmAccidentIncident input[id='flid']").val();
      var dataStr = "&locnId="+locnId+"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellid+"&disable=N";
		
	
		loadFunctionalLocation("copystdfunLocation","copystdfunctionalLoc.prv","copystdfunLocationValues","frmequipmentlist",dataStr);
		processGridnew("pmActivityequipement_input.prv","?q=1&cellid="+cellid+"&mchid="+mchid,"equipmentGrid","pager_equipment"," ", "doubleclick");
	});
	

	
	
	jQuery("#btnStandard").click(function(){

		
			var selArray =  jQuery("#equipmentGrid").jqGrid('getGridParam', 'selarrrow');
			var selrowid="";
			 var jsonArr='';
		  if(selArray !=null && selArray!=" " && selArray!=""){
			var r = confirm("Do You Want To Apply Copy Standard for this Selected Machines?");
        
			if(r==true)
				{
			for(var i=0;i<selArray.length;i++)
			{
		      // alert("INSIDE THE LOOP");
				selrowid=selArray[i];
		       //jsonArr+='[';
			   var Keyid =jQuery("#equipmentGrid").jqGrid('getCell', selrowid,"keyid");
			   
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
		    var equipList=jsonArr;
		   
		 
		        if(equipList!=null)
		    	{
		        	//alert("INSIDE THE IF");
		    	  var activitylist=jQuery("#activityid").val();
		    	//  var eid=jQuery("#hdncellid").val();
		    	var eid=  jQuery("#frmequipmentlist input[id='cell']").val();
		    	  var tid=jQuery("#hdntrdeId").val();
		    	
		    		// alert("UpdateList::"+equipList);
		      //  processGridnew(url,"q=2&statusvalue="+"TECHO","techoviewGrid","techoViewPager","","nxtgrid","","docDoubleClick");
		   
		      processAjaxCalls("pmActivityequipement_copystandard.prv","q=2?&actvity="+activitylist+"&equilist="+equipList+"&eid="+eid,'getCopyEquipment','getCopyEquipmentError');	
		      
		    	}
				}
		  }
		  else{
			  
			  popupCommonErrorMsg('Pls Select Any One Record');
		  }
		
	});
	
	
	
	function getCopyEquipment(result)
	{
		var rslt=result.stnd.result;
		//alert(rslt);
		if(rslt=="success")
			{
			  alert("Standards are copied Successfully!!!!");
			  closePopUpDialoge("loadequipment");
			}
		else if(rslt=="error")
			{
			alert("Standards are not copied !!!!");
			}
		 
	}
	
	jQuery("#btnView ").click(function(){	
		
		var celid = jQuery("#frmequipmentlist input[id='cell']").val();
		var mchnid = jQuery("#frmequipmentlistt input[id='machine']").val();
		
		processGridnew("pmActivityequipement_input.prv","?q=1&cellid="+celid+"&mchid="+mchnid,"equipmentGrid","pager_equipment"," ", "doubleclick");
	});
		
	/**END**/
</script>
<form id="frmequipmentlist">
	 <div id="frmequipmentlistFuntKeyIds" style="width:450px;">
						     			<input type="hidden" id="location" name="cmbstdLocnKeyid" value=""  ></input>
										<input type="hidden" id="factory" name="cmbstdFactKeyid" value=""  ></input>
										<input type="hidden" id="section" name="cmbstdSectKeyid" value=""  ></input>
										<input type="hidden" id="cell" name="cmbstdCellKeyid" value="" ></input>
										<input type="hidden" id="machine" name="cmbstdMachKeyid" value=""  ></input>
										<input type="hidden" id="flid" name="cmbstdFlid" value=""  ></input>
										<input type="hidden" id="elementId" name="cmbstdElementid" value=""  ></input>
							</div>							
							<div id="copystdfunLocation" style="width:450px;">
						
							</div>

<br>

<table>
<tr>
<td>
<input type="button" value="View" class="easyui-button" id="btnView" style="height: 21px;width:100px;">	
</td>
<td>
<input type="button" value="Apply Standard" class="easyui-button" id="btnStandard" style="height: 21px;width:100px;">
</td>
</tr>
</table>

 <div class="easyui-paddingbfpx">

	     <table id="equipmentGrid" style="width:100%"><tr><td/></tr></table>
	     <div id="pager_equipment">
	     </div>
   </div>
     

 <input type="hidden" id="fltrStr_actv" value="${requestScope.filtrstr}"/>
  <input type="hidden" id="tradeid" name="tradeid" value="${requestScope.pmMaingridCmbTradeid}"/>
   <input type="hidden" id="activityid" name="activityid" value="${requestScope.pmactivity}"/>
    <input type="hidden" id="hdncellid" name="hdncellid" value="${requestScope.cellid}"/>
      <input type="hidden" id="hdnelementid" name="hdnelementid" value="${requestScope.elementId}"/>
       <input type="hidden" id="hdntrdeId" name="hdntrdeId" value="${requestScope.tradeId}"/>
         <input type="hidden" id="hdnmachId" name="hdnmachId" value="${requestScope.machId}"/>
 
 
</form>