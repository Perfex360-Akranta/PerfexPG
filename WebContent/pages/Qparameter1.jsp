<script type="text/javascript">
jQuery(document).ready(function(){
	//alert("dgodfio"+jQuery('#txtSopdKeyid').val());
	jQuery('#submitForm').val('frmQPFormatdetail');
	initialiseForm("frmQPFormatdetail");
	fillComboBox('frmQPFormatdetail','cmbQpdFrequency' ,'frequency.commonFilter') ;
    fillComboBox('frmQPFormatdetail','cmbQpdFourm' ,'4m.qp') ;
	var keyid = jQuery("#txtQpdQpmkeyid" ).val();
	var keyid1 = jQuery("#xtQpdKeyid" ).val();
	 viewGrid("QP_input.qp","?q=2&keyid="+keyid );
	
});
	

         
function frmQPFormatdetail_deleteSuccessCallback(result)
{

	alert (result.successData.msg);
	jQuery("#QPGrid").trigger("reloadGrid");
}
function viewGrid(url,filterString)
{
	
	
		var tableCaption = "QPGrid";
		
		processGridnew(url,filterString,"QPGrid","pagerqp",tableCaption,"doubleClickGrid","","loadComplete","","");
		
	    return true;
	
}




</script>



<form id="frmQPFormatdetail" name="frmQPFormatdetail">


<div  align="center" style="padding-top:0% " >
<table>
	  <tr >
		      <td>
		           <div style ="padding-top: 2%"> 
		                        <label> Q Parameter </label>
		
		 <span  style="padding-left: 408px" >  <label> 4M  </label>
		  </span>
		  
		  
		<div  class="easyui-paddingbfpx">
		
		
<input class="easyui-text" style="width:335px; height:25px;text-transform: uppercase;"  id="txtQpdQparameter"   name="txtQpdQparameter" value="${requestScope.newqtmTlQpointdtl.qpdQparameter }" size="15"/>   
		
		<span style="padding-left: 142px" >    
<input class="easyui-text"  style="width:335px; height:25px ;text-transform: uppercase;" id="cmbQpdFourm" name="cmbQpdFourm" value="${requestScope.newqtmTlQpointdtl.qpdFourm }" size="15" />  
          </span>
		     </div>
		</div>
		
		
		<div >
		<label>  Specification </label>  
<span  style="padding-left: 408px">      
          <label> Monitoring  </label>                  </span>
		</div >
		<div class="easyui-paddingbfpx">
<input class="easyui-text"  style="width:335px; height:25px ;text-transform: uppercase;" id="txtQpdSpecification"   name="txtQpdSpecification" value="${requestScope.newqtmTlQpointdtl.qpdSpecification }" size="15"  / >
		 <span style="padding-left: 142px" >   
<input class="easyui-text" style="width:335px; height:25px ;text-transform: uppercase;"  id="txtQpdMonitoringmethod" name="txtQpdMonitoringmethod"  value="${requestScope.newqtmTlQpointdtl.qpdMonitoringmethod }" />    </span>
		</div>  
		<div > <label> Measuring  </label>   
		<span style="padding-left: 420px">  <label>  Frequency </label> 
	</span>  
		</div>
		  <div class="easyui-paddingbfpx">
<input class="easyui-text" style="width:335px; height:25px ;text-transform: uppercase;" id="txtQpdMeasuringequip" name="txtQpdMeasuringequip" value=" ${requestScope.newqtmTlQpointdtl.qpdMeasuringequip }"  size="15"/>
		  <span  style="padding-left: 142px">    
		  <input class="easyui-combobox" style=" width:335px; height:21px "  id="cmbQpdFrequency"   name="cmbQpdFrequency"  value="${requestScope.newqtmTlQpointdtl.qpdFrequency }" />      
	</span>
		  </div> 
		  <div> 
		<label>  Effect Of Q Parameter </label>  
		   </div>
		  <div class="easyui-paddingbfpx"> 
		  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80"  style="width:335px;height:50px;" maxlength = "50"  id="txtQpdEffectofparam" name="txtQpdEffectofparam">${requestScope.newqtmTlQpointdtl. qpdEffectofparam } 
		</textarea>  
  </div>
		</td>
	</tr>
</table>


<div  style="margin-left: 1.1%% ;margin-left: 0%\9 ">
	
	<table id='QPGrid'>
	
	<tr>
		<td>   </td>
	</tr>

</table>


<div id="pagerqp"></div>
     </div>
</div>
<input type="hidden" id="mode" name="mode" value=""/>
 <input id="txtQpdKeyid" type='hidden' name="txtQpdKeyid" value="${requestScope.newqtmTlQpointdtl.qpdKeyid}" />
 <input id="txtQpdQpmkeyid" type='hidden' name="txtQpdQpmkeyid" value="${requestScope.masterkeyid}" />

</form>