<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmMessageDashBoard');
	jQuery('#submitForm').val('frmMessageDashBoard');
	formatDateBox('dteMsgbEffectivefrom', 'dd-MMM-yyyy');
	formatDateBox('dteMsgbEffectiveto', 'dd-MMM-yyyy');
	numericTextBox('txtMsgbShownfordays');
	jQuery("#txtMsgbContent").css('text-transform', 'uppercase');
	jQuery("#txtMsgbTitle").css('text-transform', 'uppercase');
});
function frmMessageDashBoard_beforeDelete(){	
	//alert(result.successData.Keyid);
	var keyid=jQuery("#txtMsgbKeyid").val();
	if(keyid !=null && keyid !="" && keyid !=" " && keyid !="undefined"){
	var r = confirm("Are You Sure To Delete?");
    if(r){		
           return true;
        }
     else{			
	     return false; };               
     }
}
function  frmMessageDashBoard_deleteSuccessCallback(result){	
	    var keyid=jQuery("#txtMsgbKeyid").val();
	    if(keyid !=null && keyid !="" && keyid !=" " && keyid !="undefined"){
        alert(result.successData.msg);
	    clearForm('frmMessageDashBoard');  
	    }	
}
function frmMessageDashBoard_successsCallback(result) {
	//clearForm('frmMessageDashBoard');    
}
</script>
<form id="frmMessageDashBoard" name="frmMessageDashBoard">
<table style="padding-left:360px;">
<tr>
  <td style="padding-left: 10px;padding-top: 50px;"> <label class="mandatory-lbl">Message Title</label> 
        <div class="easyui-paddingbfpx"> 
                  <input id="txtMsgbTitle" name="txtMsgbTitle" style="width:410px" maxlength="100" class="easyui-text"   value="${requestScope.Mesg.msgbTitle}" />                   
         </div> 
  </td>
</tr>
<tr>
	<td>
	        <div style="padding-left: 10px;padding-top: 10px;"> <label>Content</label>
			     <div><textarea id="txtMsgbContent" name="txtMsgbContent" rows="2"  maxlength="498"  class="easyui-text" Style="width:410px;height:50px;font-size: 12" >${requestScope.Mesg.msgbContent}</textarea>
			     </div>
			    </div>
	</td>
</tr>
<tr>
        <td style="padding-top: 10px;">
        
        		<div style="padding-left: 10px;"><label>Effectivefrom</label>
			     <div><input  id="dteMsgbEffectivefrom" name="dteMsgbEffectivefrom" class="easyui-datebox" style="width:100px;" value="${requestScope.Mesg.msgbEffectivefrom}"  /></div>
			     </div></td>
        <td style="padding-top: 10px;">
        		<div style="margin-left:-287px;"><label>EffectiveTo</label>
			     <div><input  id="dteMsgbEffectiveto" name="dteMsgbEffectiveto" class="easyui-datebox" style="width:100px;" value="${requestScope.Mesg.msgbEffectiveto}"  /></div>
			     </div>
        </td>
        <td style="padding-top: 10px;"> <div style="margin-left:-176px;"><label>Show For Days</label>
             <div class="easyui-paddingbfpx"> 
                  <input id="txtMsgbShownfordays" name="txtMsgbShownfordays" value="${requestScope.Mesg.msgbShownfordays}" style="width:83px" maxlength="5" class="easyui-text" />                   
             </div> 
             </div>
         </td>
          <td style="padding-top: 10px;"> 
             <div style="margin-left:-65px;">
                 <div  class="easyui-paddingbfpx">
					               <label >Active</label>                    
					                </div>	                
					                <div class="easyui-paddingbfpx">					               
				                    <select id="cboMsgbActive" class="easyui-combobox" name="cboMsgbActive" style="width:60px;" required="true"  <c:out value = "${requestScope.Mesg.msgbActive}"/>>
										<option value="Y">Yes</option>
										<option value="N">No</option>																
									</select> 
                                    
                 </div> 
             </div>
         </td>
</tr>
<tr>     
         
        
</tr>
</table>
  <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
  <input type="hidden" id="txtMsgbKeyid" name="txtMsgbKeyid" value="${requestScope.Mesg.msgbKeyid}"/>
</form>