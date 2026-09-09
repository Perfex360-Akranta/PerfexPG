<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->

<script type="text/javascript" >
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmOPL');
	initialiseForm('frmOPL');	
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }	

	fillComboBox("frmOPL","cmbOplNoid","oplno.commonFilter" );
    fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter" );
	 
	if(  !jQuery('#cmbOplNoid').is(':disabled') )
		//fillComboBox("frmOPL","cmbOplNoid","oplno.commonFilter" );
	if(  !jQuery('#cmbImprovmntNoid').is(':disabled') )
	 	//fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter" );
	
	 fillComboBox("frmOPL","cmbPillarid","pillar.commonFilter");
	 
	 fillComboBox("frmOPL","cmbKaizenCategory","kaizenCategory.commonFilter" );
	 fillComboBox("frmOPL","cmbLossid","loss.commonFilter");
	 
	 formatDateBox('dteKznDate','dd-MMM-yyyy');
	
	 
	jQuery("#chkboxSectWise").click(function(){		
		if(jQuery('#chkboxSectWise').is(':checked') == true)
		{	
			jQuery("#chkboxCellWise").attr("checked",false);
			jQuery("#chkboxEqptWise").attr("checked",false);
		}		
	});
		
	jQuery("#chkboxCellWise").click(function(){
		if(jQuery('#chkboxCellWise').is(':checked') == true)
		{
			jQuery("#chkboxSectWise").attr("checked",false);
			jQuery("#chkboxEqptWise").attr("checked",false);
		}
	});
	
	jQuery("#chkboxEqptWise").click(function(){
		if(jQuery('#chkboxEqptWise').is(':checked') == true)
		{
			jQuery("#chkboxSectWise").attr("checked",false);
			jQuery("#chkboxCellWise").attr("checked",false);
		}
	});
	jQuery("#chkboxLossWise").click(function(){
		if(jQuery('#chkboxLossWise').is(':checked') == true)
		{
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxPillarWise").click(function(){
		if(jQuery('#chkboxPillarWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxResultWise").click(function(){
		if(jQuery('#chkboxResultWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxEqptGrpWise").click(function(){
		if(jQuery('#chkboxEqptGrpWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
		}
	});
	});
/*function frmOPLcmbOplNoid_onLoadSuccess()
{
	 fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter" );
}
/*function frmOPLcmbPillarid_onLoadSuccess()
{
	 fillComboBox("frmOPL","cmbPillarid","cmbPillarid" );	
}
function frmOPLcmbImprovmntNoid_onLoadSuccess()
{
	
}*/
function  frmOPLcmbOplNoid_onSelect(record)
{
	//jQuery("#cmbPillarid").combobox('clear');
	jQuery("#cmbImprovmntNoid").combobox('clear');
	reloadCombo("frmOPL","cmbOplNoid","oplno.commonFilter?oplNoid="+record.id);
	//reloadCombo("frmFilter","cmbCell","cellCombo.commonFilter?factId="+record.id  );
	//reloadCombo("frmFilter","cmbMachine","machineCombo.commonFilter?factId="+ record.id );
	//jQuery("#cmbOplNoid").combobox('clear');
}

/*function  frmOPLcmbPillarid_onSelect(record)
{
	jQuery("#cmbOplNoid").combobox('clear');
	reloadCombo("frmOPL","cmbPillarid","pillar.commonFilter?pillarid="+record.id);
}*/

function  frmOPLcmbImprovmntNoid_onSelect(record)
{
	jQuery("#cmbOplNoid").combobox('clear');
	//reloadCombo("frmOPL","cmbImprovmntNoid","impno.commonFilter?improvmntNoid="+record.id);
}
	
function getRelatedFilterValues()
{
	var filterStr="";

	var cmbOplNoid = jQuery("#cmbOplNoid").combobox("getValue");
	filterStr += "&cmbOplNoid="+cmbOplNoid;
	
	var cmbKaizenCategory = jQuery("#cmbKaizenCategory").combobox("getValue");
	filterStr += "&cmbKaizenCategory="+cmbKaizenCategory;
	
	var cmbImprovmntNoid = jQuery("#cmbImprovmntNoid").combobox("getValue");
	filterStr += "&cmbImprovmntNoid="+cmbImprovmntNoid;
	
	var cmbPillarid = jQuery("#cmbPillarid").combobox("getValue");
	filterStr += "&cmbPillarid="+cmbPillarid;

	var cboOplTypeid = jQuery("#cboOplTypeid").val();
	filterStr += "&cboOplTypeid="+cboOplTypeid;

	var cboKznStatus= jQuery("#cboKznStatus").val();
	filterStr += "&cboKznStatus="+cboKznStatus;

	var cmbLossid = jQuery('#cmbLossid').combobox('getValue');
	filterStr += "&cmbLossid="+cmbLossid;
	
	
	//alert(filterStr );
	/*var dteKznDate = jQuery('#dteKznDate').datebox('getValue');
	filterStr += "&dteKznDate="+dteKznDate;*/
	
	filterStr += "&chkGrpByCellid="+getChkBoxVal('chkGrpByCellid');
	filterStr += "&chkboxBK="+getChkBoxVal('chkboxBK');
	filterStr += "&chkboxIC="+getChkBoxVal('chkboxIC');
	filterStr += "&chkboxTC="+getChkBoxVal('chkboxTC');
	filterStr += "&chkboxDM="+getChkBoxVal('chkboxDM');
	filterStr += "&chkboxET="+getChkBoxVal('chkboxET');
	filterStr += "&chkboxJH="+getChkBoxVal('chkboxJH');

	filterStr += "&chkboxKK="+getChkBoxVal('chkboxKK');
	filterStr += "&chkboxOTpm="+getChkBoxVal('chkboxOTpm');
	filterStr += "&chkboxPm="+getChkBoxVal('chkboxPm');
	filterStr += "&chkboxQm="+getChkBoxVal('chkboxQm');
	filterStr += "&chkboxShe="+getChkBoxVal('chkboxShe');

	var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');
	filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
	var chkMPWorthy = getChkBoxVal('chkMPWorthy');
	filterStr += "&chkMPWorthy="+(chkMPWorthy=="1" || chkMPWorthy==1? 'Y':'N');
	
	
	var mainGroup;
	
		if(jQuery('#chkboxSectWise').is(':checked') == true)
		mainGroup = "SECTION";
	else if(jQuery('#chkboxCellWise').is(':checked') == true)
		mainGroup = "CELL";
	else if(jQuery('#chkboxEqptWise').is(':checked') == true)
		mainGroup = "MACHINE";
	
	filterStr += "&mainGroup="+mainGroup;
	
	/*filterStr += "&chkboxSectWise="+getChkBoxVal('chkboxSectWise');
	filterStr += "&chkboxCellWise="+getChkBoxVal('chkboxCellWise');
	filterStr += "&chkboxEqptWise="+getChkBoxVal('chkboxEqptWise');*/
	
	var subGroupHD = " ";
	if(jQuery('#chkboxLossWise').is(':checked') == true)
		subGroupHD = "LOSS";
	if(jQuery('#chkboxPillarWise').is(':checked') == true)
		subGroupHD = "PILLAR";
	if(jQuery('#chkboxResultWise').is(':checked') == true)
		subGroupHD = "RESULT";
	if(jQuery('#chkboxEqptGrpWise').is(':checked') == true)
		subGroupHD = "EQPGRP";
	
	filterStr += "&subGroupHD="+subGroupHD;
	filterStr += "&ViewClicked=Y";

	
	
	
	/*
	var chkboxIC = jQuery("#chkboxIC").attr(value);
	filterStr += "&chkboxIC="+chkboxIC;
	
	var chkboxTC = jQuery("#chkboxTC").attr(value);
	filterStr += "&chkboxTC="+chkboxTC;
	
	var chkboxDM = jQuery("#chkboxDM").attr(value);
	filterStr += "&chkboxDM="+chkboxDM;
	
	var chkboxET = jQuery("#chkboxET").attr(value);
	filterStr += "&chkboxET="+chkboxET;
	
	var chkboxJH = jQuery("#chkboxJH").attr(value);
	filterStr += "&chkboxJH="+chkboxJH;
	
	var chkboxKK = jQuery("#chkboxKK").attr(value);
	filterStr += "&chkboxKK="+chkboxKK;
	
	var chkboxOTpm = jQuery("#chkboxOTpm").attr(value);
	filterStr += "&chkboxOTpm="+chkboxOTpm;
	
	var chkboxPm = jQuery("#chkboxPm").attr(value);
	filterStr += "&chkboxPm="+chkboxPm;
	
	var chkboxQm = jQuery("#chkboxQm").attr(value) ;
	filterStr += "&chkboxQm="+chkboxQm;
	
	var chkboxShe = jQuery("#chkboxShe").attr(value);
	filterStr += "&chkboxShe="+chkboxShe;

	var chkboxSectWise = jQuery("#chkboxSectWise").attr(value);
	filterStr += "&chkboxSectWise="+chkboxSectWise;

	var chkboxCellWise = jQuery("#chkboxCellWise").attr(value);
	filterStr += "&chkboxCellWise="+chkboxCellWise;

	var chkboxEqptWise = jQuery("#chkboxEqptWise").attr(value);
	filterStr += "&chkboxEqptWise="+chkboxEqptWise;

	var chkboxLossWise = jQuery("#chkboxLossWise").attr(value);
	filterStr += "&chkboxLossWise="+chkboxLossWise;

	var chkboxPillarWise = jQuery("#chkboxPillarWise").attr(value);
	filterStr += "&chkboxPillarWise="+chkboxPillarWise;

	var chkboxResultWise = jQuery("#chkboxResultWise").attr(value);
	filterStr += "&chkboxResultWise="+chkboxResultWise;

	var  chkboxEqptGrpWise = jQuery("# chkboxEqptGrpWise").attr(value);
	filterStr += "&chkboxEqptGrpWise="+chkboxEqptGrpWise;
*/
//alert("ret  "+filterStr );	
	return filterStr;
}
function getChkBoxVal(Id) {		
	if(jQuery('#'+Id).is(':checked') == true)  		
		return 1;
	else
		return 0;
}
</script>
<form name="frmOPL" id="frmOPL" >
                        <!--  One Point Lesson & Kaizen Idea Sheet Tab  -->
<!--		<div title="One Point Lesson & Kaizen Idea Sheet Tab" style="padding:10px;">-->
							   <div class="sub-header">Regular</div>
							   	<div style="padding-left:75px;">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>OPL No</label>                       
                    		  </div>
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbOplNoid" name="cmbOplNoid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                 </div>
			                    <div  class="easyui-paddingbfpx">
                        			<label>Improvement No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbImprovmntNoid" name="cmbImprovmntNoid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                 	
			                   <td style="padding-left:20px;margin:0px;">    
			                    <div  class="easyui-paddingbfpx">
                        			<label>Pillar</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbPillarid" name="cmbPillarid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                 
							     <div  class="easyui-paddingbfpx">
                        			<label>Loss</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbLossid" name="cmbLossid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                   
			                   
							    
					<!--     <div  class="easyui-paddingbfpx">
                  					 <label>Improvement Date</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                    <input id="dteKznDate" class="easyui-datebox" name="dteKznDate" style="width:160px;" >
							    </div> -->		
							    
							   </td>
							 <td style="padding-left:40px;margin:0px;">
							   
								<div  class="easyui-paddingbfpx">
                  					 <label>OPL Type</label>
								</div> 
								<div class="easyui-paddingbtpx"> 
			                    <select id="cboOplTypeid" class="easyui-combobox" name="cboOplTypeid" style="width:160px;" >
											<option value="R"> Regular </option>
											<option value="S"> Stepwise </option>
											<option value="I"> Information  </option>
									  </select> 
							    </div>								
								
								<div  class="easyui-paddingbfpx">
                  					 <label>Status</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                    <select id="cboKznStatus" class="easyui-combobox" name="cboKznStatus" style="width:160px;" >
											<option></option>
											<option value="A"> Pending </option>
											<option value="C"> Completed </option>
									  </select> 
							    </div>
							   </td> 
							   <td style="padding-left:20px;margin:0px;">
							     <div  class="easyui-paddingbfpx ">
                        		   <span><input type="checkbox" id="chkGrpByCellid" name="chkGrpByCellid" ></span>                          
			     					<span><label>Group By Cell</label></span>                       
                    		   	</div> 
			                    
							   </td> 
						  </tr>
						  <tr>
						  <td style="margin:0px;">    
							     <div  class="easyui-paddingbfpx">
                        			<label>Kaizen Category</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbKaizenCategory" name="cmbKaizenCategory" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                   
							   </td>
	                      
			                   
	                   <td style="padding-left:20px;margin:0px;">
					     	<div  class="easyui-paddingbfpx ">
                      		   <span><input type="checkbox" id="chkRemoveBlank" name="chkRemoveBlank" ></span>                          
	     					<span><label>Remove Blank</label></span>                       
                  		   	</div> 
			                    
			             </td>
			             <td style="padding-left:40px;margin:0px;">
					     	<div  class="easyui-paddingbfpx ">
                      		   <span><input type="checkbox" id="chkMPWorthy" name="chkMPWorthy" ></span>                          
	     					<span><label>MP Worthy</label></span>                       
                  		   	</div> 
			             </td>
			             </tr>
			                   </table></div>
			                   <div class="sub-header">Advance</div>
			                   	<div style="height:360px;" align="center">
								<table><tr><td valign="top">
			                    
							    
							     <div  class="easyui-paddingbfpx">
                  					 <label>Classification</label>
							     </div> 
							     <div id="divClassfn" class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <span><input id="chkboxBK" type="checkbox"/> <label>Basic Knowledge</label></span><br/><br/>
                  					  <span>  <input id="chkboxIC" type="checkbox"/> <label>Improvement Cases</label></span><br/><br/>
                  					  <span>  <input id="chkboxTC" type="checkbox"/> <label>Trouble Cases</label></span>
								</div>
								<div valign="top" style="padding-left:2px;">
								 <div  class="easyui-paddingbfpx">
                  					 <label>Main Group</label>
							     </div> 
							     <div id="divMainGrp" class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxSectWise" type="checkbox" checked="checked"/> <label>Section Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxCellWise" type="checkbox"/> <label>Line Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxEqptWise" type="checkbox"/> <label>Equipment Wise</label></span>
								</div>
								
								 <div  class="easyui-paddingbfpx">
                  					 <label>Sub Group</label>
							     </div> 
							     <div id="divSubGrp"   class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxLossWise" type="checkbox"/> <label>Loss Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxPillarWise" type="checkbox"/> <label>Pillar Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxResultWise" type="checkbox"/> <label>Result Wise</label></span><br/><br/>
                  					  <span>  <input id="chkboxEqptGrpWise" type="checkbox"/> <label>Equipment Group Wise</label></span>
                  					  </div>
								</div>
								 </td>
								 <td style="padding-left:120px;" valign="top">
								  <div  class="easyui-paddingbfpx " >
                  					 <label>Pillar Name</label>
							     </div> 
							     <div id="divPillar"  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxDM" type="checkbox"/> <label> Development Management(DM)</label><br/><br/>
                  					  <span>  <input id="chkboxET" type="checkbox"/> <label>Education and Training (ET)</label></span><br/><br/>
                  					  <span>  <input id="chkboxJH" type="checkbox"/> <label>Jishu Hozen(JH)</label></span><br/><br/>
                  					  <span>  <input id="chkboxKK" type="checkbox"/> <label>Kobetsu Kaizen(KK)</label></span><br/><br/>
                  					  <span>  <input id="chkboxOTpm" type="checkbox"/> <label>Office TPM (OTPM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxPm" type="checkbox"/> <label> Planned Maintenance (PM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxQm" type="checkbox"/> <label>Quality Maintenance(QM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxShe" type="checkbox"/> <label>Safety Health and Environment(SHE)</label></span>
								 </div>
								
								 </td>
					</tr></table></div>
					
                
		
						</form>