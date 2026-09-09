<script type="text/javascript">

jQuery(document).ready(function(){//alert(2);

	initialiseForm('frmcritical');
    jQuery('#submitForm').val('frmcritical');
    formatDateBox('dteCrppDate', 'dd-MMM-yyyy');
    //fillWithCurrentDate('dteCrppDate');
    numericTextBox('txtCrpdValue');
    numericTextBox('txtCrpdMin');
    numericTextBox('txtCrpdMax');
    fillComboBox("frmcritical", "cmbCrpdUnit", "uomCombo.commonFilter");
    
	//loadFunctionalLocation("ProcessfunLocation","functionalLoc.commonFilter","ProcessfunLocationnValues","Process","&machId=MCH0002061");

	var sectionId = jQuery("#frmcritical input[id='section']").val();
    var cellId = jQuery("#frmcritical input[id='cell']").val();
    var machId = jQuery("#frmcritical input[id='machine']").val();
    var flid = jQuery("#frmcritical input[id='flid']").val(); 
    var dataStr = " &sectionId=" + sectionId
					+ "&cellId=" + cellId + "&machId="
					+ machId+"&flid="+ flid;

	loadFunctionalLocation("ProcessfunLocation", "functionalLoc.mom", "ProcessfunLocation", "frmcritical",dataStr);

	
		var NewFrm=jQuery('#txtCrppKeyid').val();
	    if(NewFrm.trim().length>0){
	//	 var date=getFieldValue("dteCrppDate");

         //setFieldValue("dteCrppDate",QpointDate,"frmcritical");
         readOnlyFields("txtCrppParameter");
         readOnlyFields("dteCrppDate");
		}
		else{
			fillWithCurrentDate('dteCrppDate');
		}

	     var url = jQuery('#hiddenUrl').val();
		 var tableCaption = "Critical Process Parameter";
		 viewGrid(url,"?q=2");
	    
		 /*jQuery("#btnnew").click(function(){
			navigateToNextForm("");
		});
	
	
	jQuery('#btnView').click(function() {
		  var url = jQuery('#hiddenUrl').val();
	       viewGrid("criticalprocess_input.cprc","q=2");
	});*/

	jQuery('#btnInsert').click(function() {

		saveForm('frmcritical','criticalprocess_save.cprc?&type=type');
		
	});

	jQuery('#btndelete').click(function() {

		var keyid = jQuery("#txtCrpdKeyid").val();
        
        if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
			    var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("criticalprocessDtl_delete.cprc", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
				} else{
					return false;
				} 
          	}else {
               alert(" Select a row to delete ");
			}
		
	});

	/*jQuery('#dteCrppDate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			completedDateEvent();
			} 
	   });*/


	   var mastKeyid = jQuery("#txtCrppKeyid").val();  	
	 setFunctionalLocWidth('frmcritical','600px');

	   //var keyid= jQuery("#hdnkeyid").val();
	   //alert(keyid);
	   
	 fileManagerPopUp(mastKeyid,"CRI","frmcritical","btnfilemgr","criticalFileMgr");
});

function dteCrppDate_onSelect(record) {
	completedDateEvent();
} 


function completedDateEvent(){
	
	var currentDate = getServerDateTime();
	var dteCrppDate=jQuery('#dteCrppDate').datebox("getValue");
	if(convertStringToDate(dteCrppDate )> currentDate)
	{
		alert('Critical Process Date should not exceed current date');
		fillWithCurrentDate('dteCrppDate');
	}	
}


/*function completedDateEvent(){
	
	var currentDate = getServerDateTime();
	var Manufacturedate=jQuery('#dteCrppDate').datebox("getValue");
	if(convertStringToDate(Manufacturedate )> currentDate)
	{
		alert('Critical Date Should Not Exceed Current Date');
		fillWithCurrentDate('dteCrppDate');
	}
	
}
*/

function btnfilemgr_click()
{  
	//var rowData = jQuery("#criticalGrid").jqGrid('getRowData',1 );
	var keyid= jQuery("#txtCrppKeyid").val();
	
	if(keyid != null && keyid != ''){
		fileManagerPopUp(keyid,"CRI","","","");
	}
	else
		saveForm('frmcritical','criticalprocess_save.cprc?filemanger=filemanger');
}

	function frmcritical_beforeSubmit() {
		if (jQussry('#txtCrppParameter').val().length<2) {
			popupCommonErrorMsg ('Enter Paramater');
			setFocusOnField("txtCrppParameter");
			return false;
		}
		
		if (jQuery('#txtCrpdMethod').val().length<2) {
			popupCommonErrorMsg('Enter Method');
			setFocusOnField("txtCrpdMethod");
			return false;
		}
	}

	function frmcritical_beforeDelete(result){
		var r = confirm("Do you want to delete ? click ok to continue");
		if(r )
			return true;
		return false;
			
	}	
	
	
	
	function frmcritical_successsCallback(result){
		jQuery('#txtCrpdKeyid').val('');
	    var Keyid=result.keyId;
	    //alert(" Success :: Keyid :: "+Keyid);
	       var flid = jQuery("#frmcritical input[id='flid']").val(); 
	    jQuery("#txtCrppKeyid").val(Keyid);
	    var Type=result.type;
	    //alert("Type"+Type);
	    
	     //var url = jQuery('#hiddenUrl').val();
		 //alert(" :: Success :: "+url);
	     //var tableCaption = "Critical Process Parameter";
		 //viewGrid(url,"?q=2");
		 
		 viewGrid("criticalprocess_input.cprc","","");
	    
		 if(Type!="type"){
        //alert("Checking");
			 setTimeout(function(){ 
			     navigateToPrevForm();
			     },1000);
			 }
		 
	    var filemanger =result.filemanger;
	    if(Keyid.trim().length>0){
	    	readOnlyFields("txtCrppParameter");
	    	readOnlyFields("dteCrppDate");
	    }
	    
	       if(filemanger==true){
	       	if(Keyid.trim().length>0){
		   		fileManagerPopUp(Keyid,"CRI","","","");
	  		 }
	  	  }
	       
	    setFieldValue("txtCrpdMethod"," ","frmcritical");
	    jQuery("#cmbCrpdUnit").combobox("setValue"," ");
	    setFieldValue("txtCrpdValue"," ","frmcritical");
	    
	    
		 setFocusOnField("txtCrpdMethod");
		 //jQuery("#txtCrppKeyid").val(' ');
		 //jQuery('#txtCrppKeyid').val("");
		 
	   jQuery('#txtCrpdKeyid').val('');	
		 
		 //alert(" Last :: ");
	}
	
	function frmcritical_deleteSuccessCallback(result){
		alert(result.successData.msg);
		navigateToPrevForm();
		//jQuery('#criticalGrid').trigger("reloadGrid");
	}
	function remove_successCallBack(result){
	    alert(result.successData.msg);
	    jQuery('#txtCrpdKeyid').val('');
		jQuery('#criticalGrid').trigger("reloadGrid");
	    setFieldValue("txtCrpdMethod"," ","frmcritical");
	    jQuery("#cmbCrpdUnit").combobox("setValue"," ");
	    setFieldValue("txtCrpdValue"," ","frmcritical");
	    setFocusOnField("txtCrpdMethod");
	}
	
	function frmcritical_FuntLocHierarchy_SuccessCallBack(result){
	     setFunctionalLocWidth('frmcritical','600px');
	}
	function doubleClickGrid(rowid)
	{
		var rowData = jQuery("#criticalGrid").jqGrid('getRowData',rowid );
		var keyid=rowData.KEYID;
		jQuery('#txtCrpdKeyid').val(keyid);
		jQuery('#txtCrpdMethod').val(rowData.METHOD);
		jQuery("#cmbCrpdUnit").combobox("setValue",rowData.UOMID);	
		//setFieldValue("cmbCrpdUnit",rowData.UOMID);
		setFieldValue("txtCrpdValue",rowData.VALUE);
		
		setFieldValue("txtCrpdMin", rowData.MIN, "frmcritical");
        setFieldValue("txtCrpdMax", rowData.MAX, "frmcritical");
        
		//processAjaxCalls("criticalprocess_recall.cprc?&KEYID="+keyid,"","recallsuccessCallBack","errorCallBack");
	}
	function recallsuccessCallBack(result){
		setFieldValue('txtCrpdMethod',result[0][3]);
		var Uom = result[0][4];
		jQuery("#cmbCrpdUnit").combobox("setValue",Uom);
		setFieldValue('txtCrpdValue',result[0][5]);
	}
	
	function viewGrid(url,filterString)
	{
		 var flid = jQuery("#frmcritical input[id='flid']").val(); 
	    var Date=getFieldValue('dteCrppDate');
	    var Parameter=jQuery('#txtCrppParameter').val();
	    var keyid=jQuery('#txtCrppKeyid').val();
	    //alert(" :: View Grid :: "+keyid);
	    if(Parameter.length==0) Parameter ="111";
	    filterString+="&Parameter="+Parameter+"&flId="+flid+"&Date="+Date;
		if(keyid.length > 0){
	       filterString +="&Keyid="+keyid;
	    }
		else{
			filterString ="";
		}
	    processGridnew("criticalprocess_input.cprc",filterString,"criticalGrid","criticalpager","","doubleClickGrid","","loadComplete","","");
	    return true;
	}
		
</script>


<form name="frmcritical" id="frmcritical" >
<div id="wrapperRpt">

	<div  class="easyui-paddingbfpx" style="padding-left:0px;margin-top: -18px">
	<table>
	<tr>
	<td width="80%;">
		<div  class="easyui-paddingbfpx" id="ProcessFuntKeyIds">
			<input type="hidden" id="section" name="cmbCrppsection" value="${requestScope.sectId}"  ></input>
			<input type="hidden" id="cell" name="cmbCrppcell" value="${requestScope.cellId}" ></input>
			<input type="hidden" id="machine" name="cmbCrppmachine" value="${requestScope.mchId}"  ></input>
			<input type="hidden" id="flid" name="cmbCrppFlid" value="${requestScope.qtmTlCriticalprocessmst.crppFlid}"  ></input>
		</div>

		<div id="ProcessfunLocation" style="padding-left: px;" ></div>
	</td>
	<td>
	<div style="padding-top:8px;padding-left:10px;">
          <div  class="easyui-paddingbfpx"> 
		       <label class="mandatory-lbl"> Date </label>
		  </div>
		  <div class="easyui-paddingbfpx">                                                                          
	           <input id="dteCrppDate" name="dteCrppDate" class="easyui-datebox" value="${requestScope.qtmTlCriticalprocessmst.crppDate}" style="width: 120px;"/>
	     </div>
    </div>
	</td>
	<td>
	<div style="padding-top:8px;padding-left:10px;">
      	<div  class="easyui-paddingbfpx"> 
		      <label class="mandatory-lbl"> Parameter </label>
		</div>
		 <div class="easyui-paddingbfpx">                                                                          
		       <input maxlength="190" id="txtCrppParameter" name="txtCrppParameter" type="text" class="easyui-text" value="${requestScope.qtmTlCriticalprocessmst.crppParameter}" style="width: 300px;"/>
		 </div>
    </div>
	</td>
	</tr>
</table>
	<table>
		<tr>
			<td>
				<div style="padding-top:8px;padding-left:0px;">
			      	<div  class="easyui-paddingbfpx"> 
					      <label class="mandatory-lbl"> Method </label>
					</div>
					 <div class="easyui-paddingbfpx">                                                                          
					       <input maxlength="190" id="txtCrpdMethod" name="txtCrpdMethod" type="text" class="easyui-text" value="" style="width: 240px;"/>
					 </div>
			    </div>
	</td>
	<td>
				<div style="padding-top:8px;padding-left:10px;">
			      	<div  class="easyui-paddingbfpx"> 
					      <label> Unit of Measurement</label>
					</div>
					 <div class="easyui-paddingbfpx">                                                                          
					       <input id="cmbCrpdUnit" name="cmbCrpdUnit" type="text" class="easyui-text" value="" style="width: 180px;text-transform:uppercase;"/>
					 </div>
			    </div>
	</td>
	<td>
				<div style="padding-top:8px;padding-left:10px;">
			      	<div  class="easyui-paddingbfpx"> 
					      <label> Value </label>
					</div>
					 <div class="easyui-paddingbfpx">                                                                          
					       <input id="txtCrpdValue" name="txtCrpdValue" type="text" class="easyui-text" maxlength="100" style="width: 100px;text-transform:uppercase;"/>
					 </div>
			    </div>
	</td>
	
	<td>
  <div style="display: flex; flex-direction: column; gap: 4px; padding-top:8px; padding-left:10px;">
    
    <div style="display: flex; gap: 10px; align-items: flex-end;">
      
      <div>
        <label for="txtMinValue">Min Value</label><br/>
        <input id="txtCrpdMin" name="txtCrpdMin" type="text"
       class="easyui-text" maxlength="100"
       style="width: 50px; text-transform: uppercase;" />
      </div>

      <div>
        <label for="txtMaxValue">Max Value</label><br/>
       <input id="txtCrpdMax" name="txtCrpdMax" type="text"
       class="easyui-text" maxlength="100"
       style="width: 50px; text-transform: uppercase;" />
      </div>

    </div>
  </div>
</td>

	<td>
		     <div style="padding-top:24px;padding-left:30px;width:176px;">
			      	 <div class="easyui-paddingbfpx">                                                                          
					       <input id="btnInsert" name="btnInsert" type="button" class="easyui-button" value="Insert" style="width: 80px;height:20px;text-transform:uppercase;"/>
					       <span>
					       <input id="btndelete" name="btndelete" type="button" class="easyui-button" value="Delete" style="width: 80px;height:20px;text-transform:uppercase;"/>
					       </span>
					 </div>
			    </div>
	</td>
		<td> 
			<span  id="criticalFileMgr" style="padding-left:20px;padding-top:30px;" >
			</span>
		</td>	
		</tr>
	</table>

	</div>
		

<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;margin-top: -17px" >
<table id="criticalGrid"></table>
		<div id="criticalpager"></div>
		</div>
</div>

 <input type="hidden" id="mode"/>
 <input type="hidden" id="txtCrpdKeyid"  name="txtCrpdKeyid" value="" />
 <input type="hidden" id="txtCrppKeyid"  name="txtCrppKeyid" value="${requestScope.qtmTlCriticalprocessmst.crppKeyid}" />
 <input type="hidden" id="hdnfnlnid" name="hdnfnlnid" value="${requestScope.FnlnId}"/>
 
 <input type="hidden" id="hdnDate" name="hdnDate" value="${requestScope.Date}"/>
 <input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.keyid}"/>
 <input type="hidden" id="hdnParameter" name="hdnParameter" value="${requestScope.Parameter}"/>
</form>