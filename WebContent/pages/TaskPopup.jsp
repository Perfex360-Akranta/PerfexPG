<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmPopupGrid');
	jQuery('#submitForm').val('frmPopupGrid');
	fillComboBox('frmPopupGrid','cmbinactiveby', 'employeeFilter.commonFilter');
	formatDateBox('dteinactivedate','dd-MMM-yyyy');

	 
	 
	jQuery("#btnok").click(function() {
		//("ok");
	       var keyid=jQuery("#hdnKeyid").val();
	       var inactiveby = getFieldValue("cmbinactiveby", "frmPopupGrid");
	       var inactivedate = getFieldValue("dteinactivedate", "frmPopupGrid");
	       var reason =jQuery("#txtreason").val();
		     
	     //  alert("ok1");
	       
		processAjaxCalls("pmtaskinactivedtl_save.task", "&Keyid="+keyid+"&inactiveby="+inactiveby+"&inactivedate="+inactivedate+"&reason="+reason, 'remove_successCallBack','remove_errorCallBack');

	       
		});


	});


function remove_successCallBack(result)
{
	alert(result.successData.msg);
	
	
	closePopUpDialoge("Taskdelete");
}
function remove_errorCallBack()
{
}

	</script>
	
	
	
	
	
	
	<form id="frmPopupGrid">

    <div class="pmtaskpopup">
    
 <table cellspacing="5px">
       <tr>
        <td> 
            <div class="easyui-paddingbfpx">
	          <label> Inactive By</label></div>
	            <div class="easyui-paddingbfpx">
	              <input class="easyui-combobox" style="width:200px; width:200px\9;" id="cmbinactiveby" name="cmbinactiveby" value="" />
	          </div>
       </td>
       <td>
       <div class="easyui-paddingbfpx">
	        <label >Inactive Date</label></div>
               <div class="easyui-paddingbfpx" style="">
	           <input class="easyui-text" id="dteinactivedate" name="dteinactivedate" maxlength="30"  style="width:100px;" value=""/>
	          </div>
      </td>
     </tr>
    
     <tr>
	    <td colspan="2" >
		    <div class="easyui-paddingbfpx">
		      <label> Reason</label></div>
		        <div class="easyui-paddingbfpx">
		          <textarea  id="txtreason" name="txtreason" style="resize:none;width: 310px  ;text-transform:uppercase; width: 200px\9 ; height:60px;" maxlength="100" ></textarea>
		        </div>
        </td>
        </tr>
        
       <tr>
       <td align="center" colspan="2">
       <div >
         <input type="button" class="easyui-button" value ="OK" name="btnok" id="btnok" style="height: 24px; text-align:center "/>
      </div>
       </td>
       </tr>
    </table>
    
    <input id="hdnKeyid" type="hidden" name="hdnKeyid" value="${requestScope.inactivelist}">
    
    
    
    </div>
    </form>