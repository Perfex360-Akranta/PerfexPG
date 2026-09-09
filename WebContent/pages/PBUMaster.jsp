
 <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  			initialiseForm('frmPBUMst');	
  			
  			jQuery('#submitForm').val('frmPBUMst'); 

  			fillComboBox("frmPBUMst","cmbPbuid","pbuCombo.commonFilter" );
  			fillComboBox("frmPBUMst","cmbPbuCompanyid","companyCombo.commonFilter" );
  			fillComboBox("frmPBUMst","cmbPbuLocationid","location.commonFilter" );
  			fillComboBox("frmPBUMst","cmbPbuSbuid","sbuCombo.commonFilter" );
  			jQuery('#frmPBUMst .easyui-combobox').css('text-transform', 'uppercase');
  			formatDateBox('dteCellEffectivedate','dd-MMM-yyyy');

  			jQuery("#cmbPbuid").combobox("disable");


  			
  			});
       
  </script>





<form name="frmPBUMst" id="frmPBUMst" >
<div class="easyui-paddingbfpx"  style="height: 414px;padding-top: 30px;padding-left:450px">
   
 				<input type="hidden" id="hdnactive" name="hdnactive"></input>
		      
		      <div  class="easyui-paddingbfpx"><label>PBU</label> </div>         
              <div ><input id="cmbPbuid" name="cmbPbuid" type="text" clear="false" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value=""/></div>
					   
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label  class="mandatory-lbl">Company</label>  </div>                  
              <div><input id="cmbPbuCompanyid" name="cmbPbuCompanyid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value=""/></div>
					   
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label  class="mandatory-lbl">Location</label>  </div>                     
              <div ><input id="cmbPbuLocationid" name="cmbPbuLocationid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value=""/></div>
			  
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label  class="mandatory-lbl" >SBU</label>  </div>                     
              <div ><input id="cmbPbuSbuid" name="cmbPbuSbuid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value=""/></div>
                
   		      <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label  class="mandatory-lbl" >Name</label> </div>                      
               			<div><input type="text" id="txtPbuName" name="txtPbuName" class="easyui-text"  maxlength="95" style="width:300px;" value=""/>
			  </div>
			   
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label >Code</label> </div>                      
               <div><input type="text" id="txtCellCode" name="txtCellCode" class="easyui-text"  maxlength="19" style="width:300px;" value="" />
                 </div>
			   <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label >Remarks</label> </div>   
			    <div> <textarea id="txtremarks" name="txtremarks"   style="width:300px;text-transform:uppercase;height:40px;" maxlength="100" value=""> </textarea></div>
		
	
</div>
<input type="hidden" id="mode" value="" />
</form>


 	