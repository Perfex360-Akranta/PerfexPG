<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<!--  /**-->
<!-- * Author:N Arun-->
<!-- * Created on:25.11.2011-->
<!-- */-->
  <script type="text/javascript">	
  				jQuery(document).ready(function(){

  	  	  		initialiseForm('frmAssembly');			
  	  	  		
  	  	  		var parentSubmitForm = jQuery('#submitForm').val();   
  	      		var parentSubmitUrl  = getSubmitFormUrl();
  				jQuery('#submitForm').val('frmAssembly'); // set the id of form to submit
  				setSubmitFormUrl('assembly_save.asb');
  				
  				if (!window.__assemblyOrigCaptured) {
  			        window.__assemblyOrigPopupSave   = window.popup_OnSaveForm;
  			        window.__assemblyOrigPopupDelete = window.popup_OnDeleteForm;
  			        window.__assemblyOrigCaptured    = true;
  			    }
  				
  				 window.popup_OnDeleteForm = function () {
  			        var keyId = jQuery('#cmbAssmKeyid').combobox('getValue');
  			        if (!keyId || keyId.trim().length === 0) {
  			            alert('Select an Assembly to delete');
  			            return false;
  			        }
  			      processAjaxCalls(
  			            'assembly_delete.asb',
  			            'cmbAssmKeyid=' + keyId,
  			            'frmAssembly_deleteSuccessCallback',
  			            'frmAssembly_errorCallback'
  			        );
  				 };
  			        
  			        //deleteRecord('frmAssembly', 'assembly_delete.asb');
  			    /* window.__restoreAssemblyContext = function () {
  			        jQuery('#submitForm').val(parentSubmitForm);
  			        setSubmitFormUrl(parentSubmitUrl);
  			        window.popup_OnSaveForm   = window.__assemblyOrigPopupSave;
  			        window.popup_OnDeleteForm = window.__assemblyOrigPopupDelete;
  			        window.__assemblyOrigCaptured = false;
  			    };  */
  			    
  			  window.__restoreAssemblyContext = function () {
  			      jQuery('#submitForm').val(parentSubmitForm);
  			      setSubmitFormUrl(parentSubmitUrl);
  			      window.popup_OnSaveForm = window.__assemblyOrigPopupSave;
  			      if (typeof window.__assemblyOrigPopupDelete === 'function') {
  			          window.popup_OnDeleteForm = window.__assemblyOrigPopupDelete;
  			      }
  			      window.__assemblyOrigCaptured = false;
  			  };
  				
  			    
  				//fillComboBox("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter" );
  				fillComboBox("frmAssembly","cmbAssmKeyid","assembly.commonFilter" );
  				fillComboBox("frmAssembly","cmbAssmMachineid","machineCombo.commonFilter");
				jQuery('#frmAssembly .easyui-text').css('text-transform', 'uppercase');
				jQuery('#frmAssembly .easyui-combobox').css('text-transform', 'uppercase');
  				jQuery('#frmAssembly textarea').css('text-transform', 'uppercase');
  				
  				//added here by priyanka on 18/06/2026
  				//loadAssemblyGrid();
  				
  				//end
  				//formatDateBox('dteMchmManufactureddate','dd-MMM-yyyy');
  				//formatDateBox('dteMchmPodate','dd-MMM-yyyy');
  				//formatDateBox('dteMchmPurchasedate','dd-MMM-yyyy');
  				//formatDateBox('dteMchmAmcdate','dd-MMM-yyyy');
  				//formatDateBox('dteMchmAmcrenewaldate','dd-MMM-yyyy');
  				//formatDateBox('dteMchmWarrantydate','dd-MMM-yyyy');
  			});
  		
  				

  		/*		jQuery("#cmbMchmManufacturerid").combobox("setValue",result.equipmentData.MchmManufacturerid);
  				jQuery("#cmbMchmMake").combobox("setValue",result.equipmentData.MchmMake);
  				jQuery("#cmbMchmModel").combobox("setValue",result.equipmentData.MchmModel);
  				
  				jQuery("#cmbMchmSupplierid").combobox("setValue",result.equipmentData.MchmSupplierid);
  				
  				jQuery("#txtMchmPono").val(result.equipmentData.MchmPono);
  				jQuery("#txtMchmPurchaseprice").val(result.equipmentData.MchmPurchaseprice);
  				jQuery("#txtMchmMfrslno").val(result.equipmentData.MchmMfrslno);
  				jQuery("#txtMchmAmcremarks").val(result.equipmentData.MchmAmcremarks);
  				jQuery("#txtMchmMfrremarks").val(result.equipmentData.MchmMfrremarks);
  				jQuery("#txtMchmSupplierremarks").val(result.equipmentData.MchmSupplierremarks);
  				jQuery("#cmbMchmAmcvendor").combobox("setValue",result.equipmentData.MchmAmcvendor);
  				
  				jQuery("#cmbMchmCurrencyid").combobox("setValue",result.equipmentData.MchmCurrencyid);
  				jQuery("#dteMchmPodate").datebox("setValue",result.equipmentData.MchmPodate);
  			 	jQuery("#dteMchmManufactureddate").datebox("setValue",result.equipmentData.MchmManufactureddate);
  			 	jQuery("#dteMchmPurchasedate").datebox("setValue",result.equipmentData.MchmPurchasedate);
  			 	jQuery("#dteMchmWarrantydate").datebox("setValue",result.equipmentData.MchmWarrantydate);
  			 	jQuery("#grdoperator").trigger("reloadGrid"); 
  			 	jQuery("#dteMchmAmcdate").datebox("setValue",result.equipmentData.MchmAmcdate);
  			 	jQuery("#dteMchmAmcrenewaldate").datebox("setValue",result.equipmentData.MchmAmcrenewaldate);
  			*/	function frmAssemblycmbAssmKeyid_onLoadSuccess()
  				{ 
  	  			}
  			function  frmAssemblycmbAssmKeyid_onSelect(record)
  			{
  	  			//alert( " fdf"+record.id);
  	  		
  				processAjaxCalls("assembly_recall.asb","keyId="+record.id, "frmAssembly_successCallback","frmAssembly_errorCallback");
  				//alert("after process ajax" + record.id);
  			}
  			function frmAssembly_successsCallback(result)
			{
  				jQuery("#cmbAssmKeyid").combobox("clear");
  				reloadCombo("frmAssembly","cmbAssmKeyid","assembly.commonFilter");
  				 
  				//alert("frmCompany");
				//reloadCombo("frmAssembly","cmbAssmKeyid","assembly.commonFilter");
				//added here by priyanka on 18/06/2026
				
				//_assmGridLoaded = false;
				//loadAssemblyGrid();
				//jQuery('#submitForm').val(parentSubmitForm);
    			//setSubmitFormUrl(parentSubmitUrl);
    			jQuery('#submitForm').val('frmBDMaster');
    			setSubmitFormUrl('Breakdown_save.Bbrdn');
    			closePopUpDialoge("divAsmblyLink");
			//	closePopUpDialoge("divAsmblyLink");
				
				// end 
			}
  			
  			// added here by priyanka on 22/06/2026 
  			function frmAssemblycmbAssmMachineid_onSelect(record) {
  			    //_assmGridLoaded = false;
  			    //jQuery("#AssmListGrid").jqGrid('GridDestroy');
  			    //jQuery("#AssmListGrid").empty();
  			    //console.log(record.id);
  			  console.log("Selected Machine : " + record.id);
  			  jQuery("#cmbAssmMachineid").combobox('setValue',record.id);
  			    //_assmGridLoaded = false;
  			    //loadAssemblyGrid();
  			}
  			function frmAssembly_deleteSuccessCallback()
			{
  				//jQuery("#cmbAssmKeyid").combobox("clear");
  				//alert("frmCompany");
				reloadCombo("frmAssembly","cmbAssmKeyid","assembly.commonFilter");
				if (window.__restoreAssemblyContext) window.__restoreAssemblyContext();
				jQuery('#submitForm').val('frmBDMaster');
				setSubmitFormUrl('Breakdown_save.Bbrdn');		
				var t=confirm("Record is refered, can not delete\! Do you want to make inactive?");
				if(t)
				{			
					checkConfirm();
				}	
				closePopUpDialoge("divAsmblyLink");
			}
			function divAsmblyLink_onClose(){
				   if (window.__restoreAssemblyContext) window.__restoreAssemblyContext();
				   return true;
			}
			
			function checkConfirm()
			{
				jQuery('#hdnInactive').val("Inactive");				
				saveForm("frmAssembly","assembly_delete.asb","");
			}
				
		   function frmAssembly_successCallback(result)
  			{ 
  	  			//alert("recallesssd");
  	  	
  	  			jQuery("#cmbAssmKeyid").combobox('setValue',result.assmdata.AssmKeyid);	
  	  			jQuery("#txtAssmName").val(result.assmdata.AssmName);
  	  		 	jQuery("#txtAssmDescription").val(result.assmdata.AssmDescription);
  	  			jQuery("#txtAssmRemarks").val(result.assmdata.AssmRemarks);
  	  			
  	  			 
  			}
		   
		   function frmAssemblycmbAssmMachineid_onLoadSuccess() {
			    if (_assmGridLoaded) return;
			    
			    var machId = jQuery("#hdnMachineId").val();
			    if (machId && machId.trim().length > 0) {
			        jQuery("#cmbAssmMachineid").combobox('setValue', machId);
			        var data = jQuery("#cmbAssmMachineid").combobox('getData');
			        for (var i = 0; i < data.length; i++) {
			            if (data[i].id == machId) {
			                jQuery("#cmbAssmMachineid").combobox('setText', data[i].text);
			                break;
			            }
			        }
			    }
			    loadAssemblyGrid();
			}
  		 	
  			 
  			function frmAssembly_errorCallback(result)
  			{
  				//alert("function2");
  			}
  			
  			/* added here by priyanka on 17/06/2026  */
  			
  			function grdAssmList_doubleClick_CallBack(rowId){
   				 var url = 'assembly_recall.asb?keyid=' + rowId;
    			processAjaxCalls(url, "", "assemblyRecallSuccessCallBack", "assemblyRecallErrorCallBack");
				}
  			// end here    			
  			/*function frmAssembly_deleteSuccessCallback(result)
	  		 {  
	  			alert(result.successData.msg);
	  		 }*/

  	/*	 function frmEquipmentcmbMchmSbno_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmManufacturerid","Combo_Manufact.eqp");
					
				 }
			 function frmEquipmentcmbMchmManufacturerid_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmMake","Combo_Make.eqp");
					
				 }
			 function frmEquipmentcmbMchmMake_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmModel","Combo_Model.eqp");
					
				 }
			 function frmEquipmentcmbMchmModel_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmSupplierid","Combo_Supplier.eqp");
					
					 }
			 function frmEquipmentcmbMchmSupplierid_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmCurrencyid","Combo_Unit.eqp");
					
					}
			 function frmEquipmentcmbMchmCurrencyid_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmAmcvendor","Combo_Provider.eqp");
				 }
			 function frmEquipmentcmbProvider_onLoadSuccess(){}

  				
*/


//ASSEMBLY GRID LOAD

 
var _assmGridLoaded = false;

function loadAssemblyGrid() {
    if (_assmGridLoaded) return;
    _assmGridLoaded = true;

    // Get selected machine — filter grid by it
    var machineId = "";
    try {
        machineId = jQuery("#cmbAssmMachineid").combobox('getValue');
        
        //machineIdVal = jQuery("#cmbAssmMachineid").combobox('getValue');
        //alert("machineId"+machineId);
    } catch(e) {
        machineId = jQuery("#hdnMachineId").val() || "";
    }

    var params = "&q=1";
    if (machineId && machineId.trim().length > 0) {
        params += "&machineId=" + machineId;
    }

    processGridnew(
        "assembly_input.asb",
        params,
        "AssmListGrid",
        "AssmListPager",
        "",
        "doubleClickAssmGrid",
        "",
        "applyAssmGridScroll"
    );

    setTimeout(function () {
    	// Set grid body height - this creates vertical scroll inside grid only
        jQuery("#AssmListGrid").jqGrid('setGridParam', {height: 350});
        
        // Apply directly to jqGrid's body div
        jQuery("#AssmListGrid").closest(".ui-jqgrid-view")
            .find(".ui-jqgrid-bdiv")
            .css({
                "height"    : "350px",
                "overflow-y": "auto",
                "overflow-x": "hidden"
            });

        jQuery("#AssmListGrid").setGridWidth(
            jQuery("#AssmListGrid").closest("div").width() - 10
        );
    }, 100);
}
 

//DOUBLE CLICK - populate form from grid row

function doubleClickAssmGrid(rowid) {

 console.log("=== doubleClickAssmGrid rowid=[" + rowid + "] ===");

 var keyid = jQuery("#AssmListGrid").jqGrid('getCell', rowid, 'assm_keyid');

 if (!keyid || keyid.trim().length === 0) {
     var rowData = jQuery("#AssmListGrid").jqGrid('getRowData', rowid);
     keyid = rowData['assm_keyid'] || "";
 }

 if (!keyid || keyid.trim().length === 0) {
     console.log("No keyid found");
     return;
 }

 console.log("doubleClickAssmGrid keyid=[" + keyid + "]");

 processAjaxCalls(
     "assembly_recall.asb",
     "keyId=" + keyid,
     "frmAssembly_successCallback",
     "frmAssembly_errorCallback"
 );
}

function clearMachineFilter() {
    jQuery("#cmbAssmMachineid").combobox('clear');
    
    jQuery("#cmbAssmKeyid").combobox('clear');
    jQuery("#txtAssmName").val('');
    jQuery("#txtAssmDescription").val('');
    jQuery("#txtAssmRemarks").val('');
    _assmGridLoaded = false;
    loadAssemblyGrid();
}

function viewMachineFilter() {
	if (jQuery.data(jQuery("#AssmListGrid")[0], 'jqGrid')) {
        jQuery("#AssmListGrid").jqGrid('GridDestroy');
        jQuery("#AssmListGrid").empty();
	    }
	_assmGridLoaded = false;
    loadAssemblyGrid();
}
 
//loadAssemblyGrid();
 
</script>
<form name="frmAssembly" id="frmAssembly">
<div id="wrapper">
    <table width="100%">        <%-- ONLY ONE table tag here --%>
        <tr>
            <td colspan="2" style="padding: 8px 12px 4px 12px;">
                <label class="mandatory-lbl">Equipment</label>&nbsp;
                <input id="cmbAssmMachineid"
                       name="cmbAssmMachineid"
                       type="text"
                       class="easyui-combobox"
                       style="width: 300px;"
                       <%-- value="${requestScope.genTlAssemblymstBean.machineId}" --%>/>
                       <input type="button" class="easyui-button" value="view" 
               			style="height:22px; width:40px;" 
               			onclick="viewMachineFilter();"/>
                       <input type="button" class="easyui-button" value="clear" 
               			style="height:22px; width:40px;" 
               			onclick="clearMachineFilter();"/>
            </td>
        </tr>
        <tr>
            <td width="55%" valign="top">
    		<div style="padding: 10px 12px; 
                height: 400px;">
         	<table id="AssmListGrid"></table>
        	<div id="AssmListPager" style="margin-top:4px;"></div>
    		</div>
			</td>
            <td width="45%" valign="top">
                <div class="easyui-paddingbfpx" style="padding-top:10px; padding-left:20px;">
                    <input type="hidden" id="hdnInactive" name="hdnInactive"/>
                    <div><label>Assembly</label></div>
                    <div class="easyui-paddingbfpx">
                        <input id="cmbAssmKeyid" name="cmbAssmKeyid" type="text"
                               class="easyui-combobox" style="width: 300px;"
                               value="${requestScope.genTlAssemblymst.assmKeyid}"/>
                    </div>
                    <div class="easyui-paddingbfpx"><label class="mandatory-lbl">Name</label></div>
                    <div class="easyui-paddingbfpx">
                        <input id="txtAssmName" name="txtAssmName" type="text"
                               class="easyui-text" maxlength="49" style="width: 300px;"
                               value="${requestScope.genTlAssemblymst.assmName}"/>
                    </div>
                    <div class="easyui-paddingbfpx"><label>Description</label></div>
                    <div class="easyui-paddingbfpx">
                        <textarea id="txtAssmDescription" name="txtAssmDescription"
                                  cols="34" rows="5" maxlength="95">${requestScope.genTlAssemblymst.assmDescription}</textarea>
                    </div>
                    <div class="easyui-paddingbfpx"><label>Remarks</label></div>
                    <div class="easyui-paddingbfpx">
                        <textarea id="txtAssmRemarks" name="txtAssmRemarks"
                                  cols="34" rows="5" maxlength="95">${requestScope.genTlAssemblymst.assmRemarks}</textarea>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>

	          			
	          			
		<!-- 		</div>--><!-- Close Tab1 Div -->  

<!--					<div title="Manufacture (Mfr)/Supplier/MC-info" style="padding:10px;">-->
<!--				<div style="padding:20px 0 0 3%;">-->
<!--				<table width="98%"  align="center"> -->
<!--           <tr width=50% > -->
<!--             <td colspan="" class="sub-header" > <b><label >Manufacture Information</label> </b></td> -->
<!--             <td colspan="" class="sub-header" > <b><label class="">Supplier Information</label></b> </td> -->
<!--             <td colspan="" class="sub-header" ><b><label class="">Maintenance Contract(MC)Information</label></b> </td> -->
<!--          </tr> -->
<!--							  <tr> -->
<!--            <td width="34%" valign="top"><div class="Maindiv" style="padding-top:15px;"> -->
<!--                <div class="easyui-paddingbfpx"" > <label>Mfr.</label>-->
<!--                  <div class="easyui-paddingbfpx" >-->
<!--                    <div style="vertical-align:top; "> -->
<!--                     <input id="cmbMchmManufacturerid"   name="cmbMchmManufacturerid" class="easyui-combobox" maxlength="10" clear="false" style="width:255px;" value="${requestScope.genTlMachinemst.mchmManufacturerid}"  <c:out value = "${requestScope.EquipmentBean.disableMchmManufacturerid == true ? ' disabled':''}"/>> -->
<!--                      </div> -->
<!--                  </div> -->
<!--                </div> -->
<!--                <div class="easyui-paddingbfpx"" ><label> Make</label>-->
<!--                  <div class="easyui-paddingbfpx"" > -->
<!--                    <div style="vertical-align:top; "> -->
<!--                      <input id="cmbMchmMake" name="cmbMchmMake" class="easyui-combobox" maxlength="30" clear="false"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmMake}"   <c:out value = "${requestScope.EquipmentBean.disableMchmMake == true ? ' disabled':''}"/>> -->
<!--                      </div> -->
<!--                  </div> -->
<!--                </div> -->
<!--                <div class="easyui-paddingbfpx"" ><label> Model</label>-->
<!--                  <div class="easyui-paddingbfpx"" >-->
<!--                    <div style="vertical-align:top; "> -->
<!--                      <input id="cmbMchmModel" name="cmbMchmModel" class="easyui-combobox" maxlength="30" clear="false" style="width:255px;" value="${requestScope.genTlMachinemst.mchmModel}"  <c:out value = "${requestScope.EquipmentBean.disableMchmModel == true ? ' disabled':''}"/> > -->
<!--                      </div> -->
<!--                  </div> -->
<!--                </div> -->
<!--                <div class="easyui-paddingbfpx"" ><label class="mandatory-lbl"> Mfr.S.No</label>-->
<!--		  <div class="easyui-paddingbfpx"" >-->
<!--			<div style="vertical-align:top; "> -->
<!--			   <input id="txtMchmMfrslno" type="text" class="easyui-text"  name="txtMchmMfrslno" maxlength="30" style=" height : 21px;width:255px;" value="${requestScope.genTlMachinemst.mchmMfrslno}"  <c:out value = "${requestScope.EquipmentBean.disableMchmMfrslno == true ? ' disabled':''}"/>> -->
<!--			  </div> -->
<!--		  </div> -->
<!--    </div>-->
<!--	-->
<!--	-->
<!--	<div class="easyui-paddingbfpx" ><label>Mfr.Dt</label>-->
<!--		  <div class="easyui-paddingbfpx" >-->
<!--			<div style="vertical-align:top; "> -->
<!--			   <input id="dteMchmManufactureddate"  clear="false" name="dteMchmManufactureddate" class="easyui-datebox" style="width:255px;" value="${requestScope.genTlMachinemst.mchmManufactureddate}"  <c:out value = "${requestScope.EquipmentBean.disableMchmManufactureddate == true ? ' disabled':''}"/> />-->
<!--			  </div> -->
<!--		  </div> -->
<!--    </div> -->
<!--                <div class="easyui-paddingbfpx" ><label>Remarks</label>-->
<!--                  <div class="easyui-paddingbfpx"" > -->
<!--                    <textarea rows="2" style="width:255px;resize:none;"  maxlength="200" cols="" id="txtMchmMfrremarks" name="txtMchmMfrremarks" maxlength="200"  <c:out value = "${requestScope.EquipmentBean.disableMchmMfrremarks == true ? ' disabled':''}"/>>${requestScope.genTlMachinemst.mchmMfrremarks}</textarea> -->
<!--                  </div> -->
<!--                </div> -->
<!--              </div></td> -->
<!--            <td width="33%" valign="top" style="padding-top:15px;"> -->
<!--            <div class="easyui-paddingbfpx" ><label>Supplier</label>-->
<!--                <div class="easyui-paddingbfpx" >-->
<!--                  <div style="vertical-align:top; "> -->
<!--                    <input id="cmbMchmSupplierid" name="cmbMchmSupplierid" maxlength="8" class="easyui-combobox" clear="false"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmMfrremarks}"  <c:out value = "${requestScope.EquipmentBean.disableMchmSupplierid == true ? ' disabled':''}"/>> -->
<!--                    </div> -->
<!--                </div> -->
<!--              </div> -->
<!--               <div class="easyui-paddingbfpx" ><label>P.O.No</label>-->
<!--               <span style="padding-left:120px;"><label>Date</label></span>-->
<!--               <div class="easyui-paddingbfpx" > -->
<!--                   <input id="txtMchmPono" type="text" class="easyui-text" maxlength="18"   name="txtMchmPono" value="${requestScope.genTlMachinemst.mchmPono}" style=" height : 21px; width: 135px;"  <c:out value = "${requestScope.EquipmentBean.disableMchmPono == true ? ' disabled':''}"/>> -->
<!--                   <span style="padding-left:20px;">  -->
<!--                   <input id="dteMchmPodate" name="dteMchmPodate" clear="false" maxlength="18" class="easyui-datebox"  value="${requestScope.genTlMachinemst.mchmPodate}"  <c:out value = "${requestScope.EquipmentBean.disableMchmPodate == true ? ' disabled':''}"/>/> </span>-->
<!--                 </div> -->
<!--              </div> -->
<!--               <div class="easyui-paddingbfpx" > <label>PO Price</label>-->
<!--               <span style="padding-left:110px;"><label>Unit</label></span> -->
<!--                <div class="easyui-paddingbfpx" > -->
<!--                   <input id="txtMchmPurchaseprice" type="text" class="easyui-text" maxlength="18"  name="txtMchmPurchaseprice" value="${requestScope.genTlMachinemst.mchmPurchaseprice}" style=" height : 21px; width: 135px;"  <c:out value = "${requestScope.EquipmentBean.disableMchmPurchaseprice == true ? ' disabled':''}"/>>                   -->
<!--                	<span style="padding-left:20px;">  -->
<!--                	<input id="cmbMchmCurrencyid"   name="cmbMchmCurrencyid" maxlength="12" clear="false" class="easyui-datebox" value="${requestScope.genTlMachinemst.mchmCurrencyid}" style=" height : 21px; width: 100px;"  <c:out value = "${requestScope.EquipmentBean.disableMchmCurrencyid == true ? ' disabled':''}"/>/> -->
<!--                   </span> </div> -->
<!--              </div> -->
<!--               <div class="easyui-paddingbfpx"" > -->
<!--                <div class="easyui-paddingbfpx"" ><span class="lbl"><label>Purchase Date</label></span>-->
<!--                </div> -->
<!--                <div style="padding-bottom: 3px;"> -->
<!--                   -->
<!--                  <input id="dteMchmPurchasedate"  clear="false" name="dteMchmPurchasedate" class="easyui-datebox" style="width:260px;" value="${requestScope.genTlMachinemst.mchmPurchasedate}"   <c:out value = "${requestScope.EquipmentBean.disableMchmPurchasedate == true ? ' disabled':''}"/>/> -->
<!--                  -->
<!--                  </div>-->
<!--                  <div>-->
<!--                    <div class="easyui-paddingbfpx" ><label>Warranty</label><span style="padding-left:50px;"><label>Warranty End Date</label></span></div> -->
<!--                  <div class="easyui-paddingbfpx"" > -->
<!--                    <input type="radio" value="0" id="Wrdchk" name="ECK"> -->
<!--                   <label> Yes</label>-->
<!--                     <input type="radio" value="1" id="Wrdchk1" name="ECK"> -->
<!--                   <label> No</label>  -->
<!--                   -->
<!--                   <span style="padding-left:20px;"> -->
<!--                   -->
<!--                  <input id="dteMchmWarrantydate" clear="false" name="dteMchmWarrantydate" class="easyui-datebox" style="width:163px;" value="${requestScope.genTlMachinemst.mchmWarrantydate}"  <c:out value = "${requestScope.EquipmentBean.disableMchmWarrantydate == true ? ' disabled':''}"/> /> -->
<!--                  </span> </div> </div>-->
<!--                </div> -->
<!--         -->
<!--               <div class="easyui-paddingbfpx"" ><label>Remarks</label>-->
<!--                <div class="easyui-paddingbfpx"" >-->
<!--                   <textarea rows="2" style="width: 255px;resize:none;" cols="" id="txtMchmSupplierremarks" maxlength="200" name="txtMchmSupplierremarks" value=""<c:out value = "${requestScope.EquipmentBean.disableMchmSupplierremarks == true ? ' disabled':''}"/>>${requestScope.genTlMachinemst.mchmSupplierremarks}</textarea> -->
<!--                 </div> -->
<!--              </div></td> -->
<!--            <td width="33%" valign="top"> <div class="Maindiv" style="padding-top:15px;"> -->
<!--               <div class="easyui-paddingbfpx" >-->
<!--                  <div class="easyui-paddingbfpx" ><label>Under MC</label></div> -->
<!--                  <div class="easyui-paddingbfpx"" > -->
<!--                    <input type="radio" value="0" id="rdchk" name="CK"> -->
<!--                   <label> Yes</label>-->
<!--                     <input type="radio" value="1" id="rdchk1" name="CK"> -->
<!--                   <label> No</label> </div> -->
<!--                </div> -->
<!--                <div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Provider</label>-->
<!--                  <div class="easyui-paddingbfpx" >-->
<!--                    <div style="vertical-align:top; "> -->
<!--                      <input id="cmbMchmAmcvendor" name="cmbMchmAmcvendor" maxlength="12" class="easyui-combobox" clear="false" style="width:255px;" value="${requestScope.genTlMachinemst.mchmAmcvendor}"  <c:out value = "${requestScope.EquipmentBean.disableMchmAmcvendor == true ? ' disabled':''}"/> > -->
<!--                      </div> -->
<!--                  </div> -->
<!--                </div> -->
<!--                <div class="easyui-paddingbfpx" >-->
<!--                  <div class="easyui-paddingbfpx" ><label>Contract Date </label>-->
<!--                  <span style="padding-left:65px;"> <label>Renewal Date</label></span>   </div> -->
<!--                  <div class="easyui-paddingbfpx" >         -->
<!--                                          -->
<!--                    <input id="dteMchmAmcdate" clear="false" name="dteMchmAmcdate" class="easyui-datebox" style="width: 110px;"  value="${requestScope.genTlMachinemst.mchmAmcdate}"  <c:out value = "${requestScope.EquipmentBean.disableMchmAmcdate == true ? ' disabled':''}"/>/>-->
<!--                    <span style="padding-left:30px;">-->
<!--                    <input id="dteMchmAmcrenewaldate" clear="false" name="dteMchmAmcrenewaldate" class="easyui-datebox" style="width: 110px;"  value="${requestScope.genTlMachinemst.mchmAmcrenewaldate}"  <c:out value = "${requestScope.EquipmentBean.disableMchmAmcrenewaldate == true ? ' disabled':''}"/>/></span>-->
<!--                    <span id="err_dteMchmAmcdate" class="tpm-errormsg"></span>-->
<!--					<span id="err_dteMchmAmcrenewaldate" class="tpm-errormsg"></span>-->
<!--                  </div> -->
<!--                </div> -->
<!--               -->
<!--                <div class="easyui-paddingbfpx"" >-->
<!--                  <div class="easyui-paddingbfpx"" ><label>MC Remarks</label></div> -->
<!--                  <div class="easyui-paddingbfpx"" >-->
<!--                    <textarea rows="2" style="width: 255px;resize:none;" cols="" maxlength="200" name="txtMchmAmcremarks" id="txtMchmAmcremarks" value="" <c:out value = "${requestScope.EquipmentBean.disableMchmAmcremarks == true ? ' disabled':''}"/>>${requestScope.genTlMachinemst.mchmAmcremarks}</textarea> -->
<!--                  </div> -->
<!--                </div> -->
<!--              </div></td> -->
<!--          </tr> -->
<!--          </div>-->
<!--          </div>-->
<!--          </div>-->
<!--          -->
<!--          </div>-->
<!--          </div>-->
<!--          </div>-->
<!--          </div>-->
          
         
  
  
<input id="hdnMachineId" name="hdnMachineId" 
       value="${requestScope.genTlAssemblymstBean.machineId}" type="hidden"/>
<input id="mode" name="mode" 
       value="${requestScope.genTlAssemblymstBean.formMode}" type="hidden"/>
<input id="refreshFlag" name="refreshFlag" value="Y" type="hidden"/>       
</form>