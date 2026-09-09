<script>
jQuery(document).ready(function(){
	initialiseForm('frmabn');
	jQuery('#submitForm').val('frmabn');
	fillComboBox('frmabn','cmbmodifyby','employee.commonFilter');
	formatDateBox('dtedate','dd-MMM-yyyy');
	});
	
	
</script>

<form>
  <div >
     <table align="center">
       <tr>
        <td>
         <div class="easyui-paddingbfpx"><label>Abnormality Id</label></div>
         <div>
          <input type="text" class="easyui-text"  id="txtabnid" name="txtabnid" maxlength="10"   style=" width : 180px;height:20px; text-align:left;" value=""/>
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
         <div class="easyui-paddingbfpx"><label>Date</label></div>
         <div>
          <input type="text" class="easyui-text"  id="dtedate" name="dtedate" maxlength="10"   style=" width : 100px;height:18px; text-align:left;" value=""/>
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
         <div class="easyui-paddingbfpx"><label>Modify By</label></div>
         <div>
          <input type="text" class="easyui-combobox"  id="cmbmodifyby" name="cmbmodifyby" maxlength="10"   style=" width : 180px;height:20px; text-align:left;" value=""/>
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
         <div class="easyui-paddingbfpx"><label>Reason</label></div>
         <div>
          <textarea maxlength="200" rows="2" cols="80" style="width: 180px; height : 50px;text-transform: uppercase;" id="txtSusmDescription" name="txtSusmDescription"></textarea>
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
     <input type="button" class="easyui-button" id="btnok" name="btnok"style="width:50px;height:21px;" value="Ok" />
      <span style="padding-left: 60px">
     <input type="button" class="easyui-button" id="btncancel" name="btncancel"style="width:70px;height:21px;" value="Cancel" /></span>
     </td>
     </tr>
     
     </table>

 </div>
</form>