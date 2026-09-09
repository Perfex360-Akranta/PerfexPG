<script type="text/javascript">

jQuery(document).ready(function(){
	
var roleId = jQuery("#hdnUserRole").val();
	
	if(roleId != 'AROL0055')
	{
		 console.log("Access DENIED - Role mismatch");
	        console.log("Expected: 'QM PILLAR MEMBER' (AROL0055)");
	       
	        
	        // Hide all form content
	        jQuery('#frmQPointFormat').hide();
	        
	        // Show error message
	        if(typeof popupCommonErrorMsg === 'function') {
	            popupCommonErrorMsg("Please select the QM PILLAR MEMBER");
	        } else {
	            alert("Please select the QM PILLAR MEMBER");
	        }
	        
	        // Redirect back after showing message
	        setTimeout(function() {
	            navigateToPrevForm();
	        }, 5000);
	        
	        return false;
		
	}

	initialiseForm("frmQPointFormat");
	jQuery('#submitForm').val('frmQPointFormat'); 
	formatDateBox('dteQptmDate', 'dd-MMM-yyyy');
	//fillComboBox('frmQPointFormat','cmbQptmPreparedby' ,'employee.commonFilter') ;
	
	numericTextBox("txtQptdNooflocations");
	//var FLID=jQuery('#hdnfnlnid').val();
	//jQuery('#flid').val(FLID);
    
    //alert(" FIELD  FLID NEW :: "+jQuery('#flid').val());
    fillWithCurrentDate('dteQptmDate');
    
    var hdnnewFrm=jQuery('#hdnnewFrm').val();
    //alert(" hdnnewFrm :: "+hdnnewFrm);
    
    if(hdnnewFrm.trim().length>0)
    {
    	readOnlyFields("txtQptmArea");
    	readOnlyFields("txtQptmKpov");
    	readOnlyFields("cmbQptmPreparedby");
    	readOnlyFields("dteQptmDate");
    	
    }
	
	var NewFrm=jQuery('#hdnnewFrm').val();
     if(NewFrm.trim().length>0){
		  var date=getFieldValue("dteQptmDate");
          var QpointDate  = date.substring(0, 12);
          setFieldValue("dteUpsdDate",QpointDate,"frmQPointFormat");
		}
		else{
			fillWithCurrentDate('dteQptmDate');
		}
	
	var factId = jQuery("#frmQPointFormat input[id='factory']").val();
    var sectionId = jQuery("#frmQPointFormat input[id='section']").val();
    var cellId = jQuery("#frmQPointFormat input[id='cell']").val();
    var machId = jQuery("#frmQPointFormat input[id='machine']").val();
    var flid = jQuery("#frmQPointFormat input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId  +"&flid="+flid;
 
   loadFunctionalLocation("QPform","functionalLoc.qp","frmQPointFormatLocationfunLocationValues","frmQPointFormat",dataStr);

     var url = jQuery('#hiddenUrl').val();
	 var tableCaption = "Q-Point";
	 viewGrid(url,"?q=2",tableCaption);

	 fileManagerPopUp(flid,"QPT","frmQPointFormat","btnfilemgr","QPNTFilemgr","create");
	 //fileManagerPopUp(documentNo,"ABN","frmAbnormality","btnFilManage","abnFilemgr");

	 jQuery('#btnInsert').click(function() {
		 jQuery("#hdninsrt").val("insert");
		    saveForm('frmQPointFormat','QPointFormNew_save.qp?&type=type');
			
		});

		jQuery('#btndelete').click(function() {

			var keyid = jQuery("#txtQptdKeyid").val();
	        
	        if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
				    var r = confirm("Do You Want To Delete?");
					if (r == true) {
						processAjaxCalls("QPointForm_delete.qp", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
					} else{
						return false;
					} 
	          	}else{
	          		   alert(" Select a row to delete ");
	                   jQuery("#QPointGrid").trigger("reloadGrid");
				}
			
		});
		
	 
	});
function frmQPointFormat_beforeDelete()
{

	var mstKeyid=jQuery("#txtQptmKeyid").val();
	 if(mstKeyid==null || mstKeyid=="" || mstKeyid==" ")
	 	return false;
	 else
		 var r=confirm("Are You Sure to Delete?");
		if(r)
			return true;
		else
			return false;
	}
	function dteQptmDate_onSelect(record) {
		completedDateEvt();
	} 


	function completedDateEvt()
	{
		var currentDate = getServerDateTime();
		var qpointDate = jQuery('#dteQptmDate').datebox("getValue");
		
		if(convertStringToDate(qpointDate)> currentDate)
		{
			alert('Q-Point Date Should Not Exceed Current Date');
			fillWithCurrentDate('dteQptmDate');
		}
	}


	function remove_successCallBack(result){
		
	    alert(result.successData.msg);
	    jQuery('#txtQptdKeyid').val('');
		jQuery('#QPointGrid').trigger("reloadGrid");
		//setFieldValue("txtQptmArea"," ","frmQPointFormat");
	    //setFieldValue("txtQptmKpov"," ","frmQPointFormat");
	    jQuery('#txtQptdQpoint').val("");
	    setFieldValue("txtQptdQpoint"," ","frmQPointFormat");
	    //setFieldValue("cmbCrppUnit"," ","frmcritical");
	    //jQuery("#cmbQptmPreparedby").combobox("setValue"," ");
	    //setFieldValue("dteQptmDate"," ","frmQPointFormat");
	    setFieldValue("txtQptdNooflocations"," ","frmQPointFormat");
	    
	    setFocusOnField('txtQptdQpoint');

	    
	    //navigateToPrevForm();
	
		
	}
	function btnfilemgr_click(){
	    
		 var keyid = jQuery("#txtQptmKeyid").val();
	  	
		if(keyid.trim().length>0){
	  		fileManagerPopUp(keyid,"QPT","","","");
	  		
	  	}else{
	  		saveForm('frmQPointFormat','QPointFormNew_save.qp?filemanger=filemanger');	
		 }
	  	
	}
	
	function frmQPointFormat_beforeSubmit()
	{
		
		var qPoint =jQuery("#txtQptdQpoint").val();
		if(qPoint.trim().length<=0)
		{
			alert("Please Enter Q Point");
			return false;
		}

		
	}

	
	function frmQPointFormat_successsCallback(result){		
		    //clearForm("frmQPointFormat");
		    //navigateToPrevForm();
		    var MstKeyid=result.keyId;
		    var Type=result.type;
		    //alert("Type"+Type);
		    var Flid= jQuery("#hdnfnlnid").val();
		    jQuery("#txtQptmKeyid").val(MstKeyid);
		   // jQuery("#txtQptdKeyid").val('');
		    jQuery("#txtQptdQpoint").val('');
		    jQuery("#txtQptdNooflocations").val('');
		    jQuery('#txtQptdKeyid').val('');


		    //alert(" Checking :: "+type+" insert "+insert);
		    
	   		//if(type.trim().length>0 && insert.trim().length>0){
	      		//alert("checking");
	   	   //	}
	   		
		    if(Type!="type"){
				
		  		  //alert("checking");
		  		navigateToPrevForm();
			}
	   	   	
		    /*if(result.successData.mode=="Modify")
			{
				navigateToPrevForm();
			}*/
		  
		    var filemanger =result.filemanger;
		    
		    viewGrid("QPointFormNew_input.qp","","");

		    

			/*if(Type=="type" && insert!="insert"){
				
		  		  alert("checking");
		  		navigateToPrevForm();
			}*/
			jQuery('#hdninsrt').val('');
			   	
		    if(filemanger==true){
	        	if(Flid.trim().length>0){
		   			
	        		fileManagerPopUp(Flid,"QPT","","","");
	        		
	   			 }
	   		}
	   		if(masterKeyid.trim().length>0)
		    {
		    	readOnlyFields("txtQptmArea");
		    	readOnlyFields("txtQptmKpov");
		    	readOnlyFields("cmbQptmPreparedby");
		    	readOnlyFields("dteQptmDate");
		    	
		    }
		    
		    jQuery('#QPointGrid').trigger("reloadGrid");
		    
		    setFieldValue("txtQptdQpoint"," ","frmQPointFormat");
		    setFieldValue("txtQptdNooflocations"," ","frmQPointFormat");
		    setFieldValue("txtQptdKeyid"," ","frmQPointFormat");
		    jQuery('#txtQptdKeyid').val('');
		    setFocusOnField('txtQptdQpoint');
		    
		}



	function viewGrid(url,dataString,tableCaption)
	{
		 var flid = jQuery("#frmQPointFormat input[id='flid']").val();
		var Date=jQuery('#dteQptmDate').val();
		var Area=jQuery('#txtQptmArea').val();
		var Kpov=jQuery('#txtQptmKpov').val();
		var Preparedby=jQuery('#cmbQptmPreparedby').combobox('getValue');
		var Mstkeyid = jQuery("#txtQptmKeyid").val();
		  //alert(Mstkeyid);
		dataString+="&keyId="+Mstkeyid+"&Area="+Area+"&Kpov="+Kpov+"&Preparedby="+Preparedby +"&flId="+flid+"&Date="+Date;
		if( Kpov.trim().length == 0 || Area.trim().length == 0)
			dataString = "";
		processGridnew("QPointFormNew_input.qp",dataString,"QPointGrid","QPointpager",tableCaption,"doubleClickGrid");
	}
	
	function doubleClickGrid(rowid)
	{
		var filterStr="q=2";
		var rowData = jQuery("#QPointGrid").jqGrid('getRowData',rowid);
		var dtlkeyid=rowData.DTLKEYID;
		//alert("dtlkeyid::::"+dtlkeyid);
		var keyid=rowData.KEYID;
	    var qpoint= rowData.QPOINT; 
	    var qnoofloc=rowData.NOOFLOCATIONS;
	  //  alert("qpoint::"+qpoint);
	//	alert(":::Double Click:keyid"+keyid);
		//QPointGrid_QPOINT
		jQuery('#txtQptmKeyid').val(keyid);
		jQuery('#txtQptdKeyid').val(dtlkeyid);
		processAjaxCalls("QPointFormNew_recall.qp?q=2&keyid="+keyid,"","recallsuccessCallBack","errorCallBack");
		jQuery('#txtQptdQpoint').val(qpoint);
		jQuery('#txtQptdNooflocations').val(qnoofloc);
	}
	function recallsuccessCallBack(result){  //alert(" Inside successcallback :: ");
	  // alert("result"+result);
	   var qpointsucess=jQuery('#txtQptdQpoint').val();
	   var qnooflocation=jQuery('#txtQptdNooflocations').val();
	//   alert("qpointsucess::"+qpointsucess);
		setFieldValue('escape(txtQptmArea)',result[0][0]);
		setFieldValue('txtQptmKpov',result[0][1]);
		setFieldValue('txtQptdQpoint',result[0][2]);
		setFieldValue('cmbQptmPreparedby',result[0][3]);
		setFieldValue('txtQptdNooflocations',result[0][4]);
		jQuery('#txtQptdQpoint').val(qpointsucess);
		jQuery('#txtQptdNooflocations').val(qnooflocation);
		var Date = result[0][5];
		var QPointDate  = Date.substring(0, 12);
		setFieldValue('dteUpsdDate',QPointDate);
	}
	           
	function frmQPointFormat_deleteSuccessCallback(result)
	{
		
		alert(result.successData.msg);
		jQuery('#QPointGrid').trigger("reloadGrid");
		setFieldValue("txtQptmArea"," ","frmQPointFormat");
	    setFieldValue("cmbQptmPreparedby"," ","frmQPointFormat");
	    setFieldValue("txtQptmKpov"," ","frmQPointFormat");
	    setFieldValue("dteQptmDate"," ","frmQPointFormat");
	    setFieldValue("txtQptdQpoint"," ","frmQPointFormat");
	    setFieldValue("txtQptdNooflocations"," ","frmQPointFormat");
		//navigateToPrevForm();
		
	}
	
	function frmQPointFormat_FuntLocHierarchy_SuccessCallBack(result){
		
		var cellId = jQuery("#frmQPointFormat input[id='cell']").val();
		var flid = jQuery("#frmQPointFormat input[id='flid']").val();
	    jQuery("#txtQpmFlnid").val(flid);
	    
	    reloadCombo("frmQPointFormat","cmbQptmPreparedby","employee.commonFilter?cellId="+cellId);

	    var masterKeyid=jQuery('#txtQptmKeyid').val();
	    var pBy = getFieldValue("cmbQptmPreparedby"); 
	    if(masterKeyid.trim().length == 0 && pBy.trim().length == 0){
	    	
	    	var UserId=jQuery('#hdnuserId').val();
	    	setFieldValue("cmbQptmPreparedby",UserId);
	    	
	    }
	   
	    		
	}

 function isDtl(id){
    	//alert(id);
    	 if(jQuery("#"+id).val().trim().length>0)
    		jQuery('#hdnIsDtlTrue').val("true");	
    		var data=jQuery("hdnIsDtlTrue").val();
    	//	alert(data);
    }
</script>


   <form id="frmQPointFormat" name="frmQPointFormat">

      <div id="wrapper" style="width: 70%;" align="center" >
      <table>
              <tr>
                  <td  colspan="4">
	                    <div id="frmQPointFormatFuntKeyIds" >						
						    <input type="hidden" id="factory" name="cmbQptmfactory"  value="" ></input>
							<input type="hidden" id="section" name="cmbQptmsection"  value=""></input>
							<input type="hidden" id="cell"    name="cmbQptmcell"     value=""></input>
							<input type="hidden" id="machine" name="cmbQptmmachine"  value=""></input>	   
							                                                                          
						    <input type="hidden" id="flid"  name= "cmbQptmFlid" value="${requestScope.newQtmTlQpoint.qptmFlid}"/>
						</div>
						 
						<div id="QPform" style="width : 857px; width : 1060px\9; padding-left: 0px;padding-left: 55px\9;">
							
						</div>	
						 
				</td>
				
				<td>
					<div style="">
			            <span id="QPNTFilemgr" style="position:absolute;" >
		       		    </span> 
		            </div>
				</td>
       
         </tr>
               </table>
      <table>
      <tr>
      <td>
       <div class="easyui-paddingbfpx" >
	               		<label class ="mandatory-lbl">Area</label> 
	               </div>
		           <div class="easyui-paddingbfpx" >
			          	<input id="txtQptmArea"  name="txtQptmArea"  class="easyui-text" maxlength="290" style="width: 204px;text-transform:uppercase;" value="${requestScope.newQtmTlQpoint.qptmArea}"/>
        </div>
      </td>
      <td>
      <div style="padding-left:10px;">
       <div  class="easyui-paddingbfpx"> 
			          <label class ="mandatory-lbl"> KPOV / Impact </label>
			  </div>
			  <div class="easyui-paddingbfpx">  
		             <input id='txtQptmKpov' name="txtQptmKpov"  class="easyui-text" maxlength="290" style="width: 204px;text-transform:uppercase;" value="${requestScope.newQtmTlQpoint.qptmKpov}"/>
		        
             </div>
       </div>
      </td>
      
      <td>
         <div style="padding-left:10px;">
		   <div class="easyui-paddingbfpx" >
       	    	<label>  Prepared By </label> 
            </div>
           <div class="easyui-paddingbfpx" >  
	          	<input id="cmbQptmPreparedby"  name="cmbQptmPreparedby"  class="easyui-combobox" value="${requestScope.newQtmTlQpoint.qptmPreparedby}" maxlength="10" style="width: 200px;"/>
	       </div>
	      </div>

      </td>
      
      <td>
         <div style="padding-left:10px;">
	      <div  class="easyui-paddingbfpx"> 
			       <label class ="mandatory-lbl"> Date </label>
		  </div>
		  <div class="easyui-paddingbfpx">                                                                          
	           <input id="dteQptmDate" name="dteQptmDate" type="text" class="easyui-datebox" value="${requestScope.newQtmTlQpoint.qptmDate}"  maxlength="50" style="width: 140px;"/>
	      </div>
      </div>
      </td>
   </tr>
   
   <tr>
   
	   <td colspan="2">
	     <div  class="easyui-paddingbfpx"> 
			          <label class ="mandatory-lbl"> Q-Point </label>
			  </div>
			  <div class="easyui-paddingbfpx">  
                 
                 <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="290" rows="2" cols="80" style=" width : 420px; height : 65px;" id="txtQptdQpoint" name="txtQptdQpoint"  onblur="isDtl('txtQptdQpoint');"></textarea>
		        
             </div>
	   
	   
	   </td>
	   
	   <td valign="top">
	    <div style="padding-top:4px;padding-left:10px;">
	      <div  class="easyui-paddingbfpx"> 
			          <label> No. of locations/gauges </label>
			  </div>
			  <div class="easyui-paddingbfpx" >  
		             <input id='txtQptdNooflocations' name="txtQptdNooflocations"  class="easyui-text" value=""  maxlength="300" style="width:130px;text-transform:uppercase;" onblur="isDtl('txtQptdNooflocations');"/>
		        
              </div>
         </div>
	   </td>
	   
	    <td>
           <div style="padding-top:64px;padding-left:20px;">
			      	                                                                          
					       <input id="btnInsert" name="btnInsert" type="button" class="easyui-button" value="Insert" style="width: 60px;height:20px;text-transform:uppercase;"/>
					       <span>
					        <input id="btndelete" name="btndelete" type="button" class="easyui-button" value="Delete" style="width: 60px;height:20px;text-transform:uppercase;"/>
					       </span>
					 
			    </div>
           </td>
           
           
	   
	   
   </tr>
 </table>
	</div>
	
	<div style="margin-left:46px;">
		<table id='QPointGrid'>
        </table>
		<div id='QPointpager'></div>
	</div>
          
	           
	<input type="hidden" id="mode" name="mode" />
	<input type="hidden" id="txtQptmKeyid"  name="txtQptmKeyid" value="${requestScope.newQtmTlQpoint.qptmKeyid}" />
    <input type="hidden" id="hdnfnlnid" name="hdnfnlnid" value="${requestScope.FnlnId}"/>
	<input type="hidden" id="hdnnewFrm" name="hdnnewFrm" value="${requestScope.New}"/>
	<input type="hidden" id="hdnuserId" name="hdnnewFrm" value="${requestScope.userId}"/>
	<input type="hidden" id="hdnUser" name="hdnUser" value="${requestScope.User}"/>
	<input type="hidden" id="hdnIsDtlTrue" name="hdnIsDtlTrue" value="" />
	<input type="hidden" id="txtQptdKeyid"  name="txtQptdKeyid" value="" />
</form>	
          
       