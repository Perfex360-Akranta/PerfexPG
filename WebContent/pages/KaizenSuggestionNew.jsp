<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmKaizen');
	jQuery('#submitForm').val('frmKaizen');

	 fillComboBox("frmImprovementPrj","cmbkznmKeyid","combo_improvemnetNo.kaizen" );
	 
	 fillComboBox("frmImprovementPrj","cmbkznmCategory","kaizenCategory.commonFilter" );
	 fillComboBox("frmImprovementPrj","cmbkznmSubCategory","KaizenBankFillCombo.kznbnk" );//KZN_TL_SUBCATEGORYMST
	 formatDateBox('dteKzbnDate','dd-MMM-yyyy');
	 fillWithCurrentDate('dteKzbnDate');
	 
	 
	 readOnlyFields("cmbkznmKeyid");
	 
	 var factId = jQuery("#frmKaizen input[id='factory']").val();
	 var sectionId = jQuery("#frmKaizen input[id='section']").val();
	 var cellId = jQuery("#frmKaizen input[id='cell']").val();
	 var machId = jQuery("#frmKaizen input[id='machine']").val();
	 var flid = jQuery("#frmKaizen input[id='flid']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
	 
	 loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmKaizen",dataStr);
	 
	    var compId = getFieldValue('company','frmKaizen');
		var locnId = getFieldValue('location','frmKaizen');
		var factId = getFieldValue('factory','frmKaizen');
		var sectId = getFieldValue('section','frmKaizen');
		var cellId = getFieldValue('cell','frmKaizen');
		var machId = getFieldValue('machine','frmKaizen');
		var flid = getFieldValue('flid','frmKaizen');
	//	
	 
	 fillComboBox("frmKaizen","cmbkznmMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid);
	 
	 setFunctionalLocWidth('frmKaizen','670px');
	 //alert(11);
	 
});

function frmKaizen_beforeSubmit()
{
	var cellId = getFieldValue('cell','frmKaizen');
	if (cellId =='' || cellId ==' ') {
		popupCommonErrorMsg("Select JH");
		return false;
	}
}

function  frmKaizencmbkznmMachineid_onSelect(record)
{
	loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmKaizen","&machId="+record.id);
	reloadCombo("frmKaizen","cmbkznmMachineid","machineCombo.commonFilte?machineId="+ record.id );
 	
}

/*function  frmKaizencmbkznmCategory_onSelect(record)
{
	alert(record.id);
	alert(1);
	val category= jQuery("#cmbkznmCategory").combobox('getValues');
	alert(" category :: "+category);
}*/

</script>

<form name="frmKaizen" id="frmKaizen" >
<div id="wrapper" style="width:90%;margin-top:20px;">
	<div style="padding-left:0%;">
		<div class="sub-header"   style="width:1080px">Kaizen Suggestion</div>
				<div class="main-cntborder" style="height: 360px;width:1080px;margin-left:0%;">
					<div>
						<div>
						<table cellspacing="2" style="padding-left:5%;">
							<tr>
								<td colspan="2">
								<div id="frmKaizenFuntKeyIds"  >							
										<input type="hidden" id="factory" name="factory"  value="" ></input>
										<input type="hidden" id="section" name="section"  value=""></input>
										<input type="hidden" id="cell"    name="cell"     value=""></input>
										<input type="hidden" id="machine" name="machine"  value=""></input>
										<input type="hidden" id="flid" name="cmbKzbnFlid"  value=""></input>							
									</div>						
									<div id="frmKaizenfunloc" style="width:100%;"></div>
								
								
								</td>
								<td>
								<div style="padding-top:8px;padding-left:20px;">
								<div  class="easyui-paddingbfpx" ><label>Kaizen No</label> </div>
						
								<div class="easyui-paddingbfpx" >
									<span><input id="cmbkznmKeyid" name="cmbkznmKeyid" class="easyui-combobox"  style="width:135px;" value="" /></span>
								</div>
						       </div>
								</td>
							</tr>
							<tr>
								
									<td>								
									<div class="easyui-paddingbfpx"><label>Equipment</label></div>
							        <div class="easyui-paddingbfpx">
		                		      <input id="cmbkznmMachineid" name="cmbkznmMachineid" class="easyui-combobox"  style="width:340px;" value="" />
								    </div>
								    </td>
								    
								    <td>
								    
								    <div style="padding-left:20px;">
										
									<div class="easyui-paddingbfpx"><label >Date</label></div>
									<div class="easyui-paddingbfpx">
										<input id="dteKzbnDate"  name="dteKzbnDate" clear="false" class="easyui-datebox" value=""  style="width:160px;height:21px;"  />
									</div>
									
									</div>
								
								    </td>
					</tr>
					<tr>			    
								    <td>
								    <div class="easyui-paddingbfpx"><label>Kaizen Category</label></div>
							        <div class="easyui-paddingbfpx">
		                		      <input id="cmbkznmCategory" name="cmbkznmCategory" class="easyui-combobox"  style="width:340px;" value=""   />
								    </div>
								    </td>
								    <td>
								    <div style="padding-left:20px;">
								     <div class="easyui-paddingbfpx"><label>Sub Category</label></div>
							        <div class="easyui-paddingbfpx">
		                		      <input id="cmbkznmSubCategory" name="cmbkznmSubCategory" class="easyui-combobox"  style="width:318px;" value="" />
								    </div>
								    </div>
								    
								    </td>
								    
								    </tr>
								    
								    
								    <tr>
								    <td>
								    <div  class="easyui-paddingbfpx"> 
									  <label > Theme </label>
									  </div>
									  <div class="easyui-paddingbfpx">  
						                 
						                 <textarea rows="2" cols="80" style=" width : 340px; height : 65px;" id="txtkznmTheme" name="txtkznmTheme" ></textarea>
								        
						             </div>
						             </td>
						             <td>
						             <div style="padding-left:20px;">
						             <div  class="easyui-paddingbfpx"> 
									          <label > Idea </label>
									  </div>
									  <div class="easyui-paddingbfpx">  
						                 
						                 <textarea rows="2" cols="80" style=" width : 320px; height : 65px;" id="txtkznmIdea" name="txtkznmIdea" ></textarea>
								        
						             </div>
						             </div>
								    </td>
								
							</tr>
						</table>
						</div>
				</div>
				</div>
				</div>
				</div>	
							
</form>