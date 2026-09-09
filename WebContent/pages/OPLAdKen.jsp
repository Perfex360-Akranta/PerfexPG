<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" >
jQuery(document).ready(function(){	
jQuery('#submitForm').val('frmOPL');
fillComboBox("frmOPL","cmbOplNoid","oplno.commonFilter" );

});

function getRelatedFilterValues()
{
	var filterStr="";
	
	var cmbOplNoid = jQuery("#cmbOplNoid").combobox("getValue");
	filterStr += "&cmbOplNoid="+cmbOplNoid;
	
	var cmbGrpByCellid = jQuery("#cmbGrpByCellid").combobox("getValue");
	filterStr += "&cmbGrpByCellid="+cmbGrpByCellid;
	
	var cmbImprovmntNoid = jQuery("#cmbImprovmntNoid").combobox("getValue");
	filterStr += "&cmbImprovmntNoid="+cmbImprovmntNoid;
	
	var cmbPillarid = jQuery("#cmbPillarid").combobox("getValue");
	filterStr += "&cmbPillarid="+cmbPillarid;
	
	var cmbOplTypeid = jQuery("#cmbOplTypeid").combobox("getValue");
	filterStr += "&cmbOplTypeid="+cmbOplTypeid;
	
	var chkboxBK = jQuery("#chkboxBK").attr(value);
	filterStr += "&chkboxBK="+chkboxBK;
	
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
	
	
	
	return filterStr;
	
}
</script>
	<form name="frmOPL" id="frmOPL" >
                        <!--  One Point Lesson & Kaizen Idea Sheet Tab  -->
<!--		<div title="One Point Lesson & Kaizen Idea Sheet Tab" style="padding:10px;">-->
							   <div class="sub-header">Regular</div>
							   	<div align="" style="padding-left:5px;">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>OPL No</label>                       
                    		  
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbOplNoid" name="cmbOplNoid" class="easyui-combobox"  style="width:300px" value=""  >                       
			                 </div></div></td></tr></table>
<!--			                   -->
<!--			                   <div  class="easyui-paddingbfpx">-->
<!--                        			<label>Group By Cell</label>                       -->
<!--                    		   </div> -->
<!--			                    <div class="easyui-paddingbfpx"> -->
<!--			                        <input id="cmbGrpByCellid" name="cmbGrpByCellid" class="easyui-combobox"  style="width:350px" value=""  >                       -->
<!--			                   </div>-->
			                   
			                  
<!--			                   <td style="padding-left:20px;">-->
<!--			                   <div  class="easyui-paddingbfpx">-->
<!--                        			<label>Improvement No</label>                       -->
<!--                    		   </div> -->
<!--			                    <div class="easyui-paddingbfpx"> -->
<!--			                        <input id="cmbImprovmntNoid" name="cmbImprovmntNoid" class="easyui-combobox"  style="width:350px" value=""  >                       -->
<!--			                   </div>-->
<!--			                   -->
<!--			                    <div  class="easyui-paddingbfpx">-->
<!--                        			<label>Pillar</label>                       -->
<!--                    		   </div> -->
<!--			                    <div class="easyui-paddingbfpx"> -->
<!--			                        <input id="cmbPillarid" name="cmbPillarid" class="easyui-combobox"  style="width:350px" value=""  >                       -->
<!--			                   </div>-->
<!--	                   </tr></table></div>-->
<!--			                   <div class="sub-header">Advance</div>-->
<!--			                   	<div align="center">-->
								<table><tr><td valign="top">
			                    <div  class="easyui-paddingbfpx">
                  					 <label>OPL Type</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                         <select id="cmbOplTypeid" class="easyui-combobox" name="cmbOplTypeid" style="width:160px;" required="true">
													<option value="R">Regular </option>
													<option value="S">Stepwise </option>
													<option value="I">Information  </option>
									  </select> 
							    </div>
							    
							     <div  class="easyui-paddingbfpx">
                  					 <label>Classification</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxBK" type="checkbox"/> <label>Basic Knowledge</label></span><br/><br/>
                  					  <span>  <input id="chkboxIC" type="checkbox"/> <label>Improvement Cases</label></span><br/><br/>
                  					  <span>  <input id="chkboxTC" type="checkbox"/> <label>Trouble Cases</label></span>
								</div>
<!--								<div valign="top" style="padding-left:2px;">-->
<!--								 <div  class="easyui-paddingbfpx">-->
<!--                  					 <label>Main Group</label>-->
<!--							     </div> -->
<!--							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">-->
<!--                  					  <input id="chkboxSectWise" type="checkbox"/> <label>Section Wise</label>-->
<!--                  					  <span  style="margin-left: 2px;">  <input id="chkboxCellWise" type="checkbox"/> <label>Cell Wise</label></span>-->
<!--                  					  <span  style="margin-left: 2px;">  <input id="chkboxEqptWise" type="checkbox"/> <label>Equipment Wise</label></span>-->
<!--								</div>-->
<!--								-->
<!--								 <div  class="easyui-paddingbfpx">-->
<!--                  					 <label>Sub Group</label>-->
<!--							     </div> -->
<!--							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">-->
<!--                  					  <input id="chkboxLossWise" type="checkbox"/> <label>Loss Wise</label>-->
<!--                  					  <span  style="margin-left: 2px;">  <input id="chkboxPillarWise" type="checkbox"/> <label>Pillar Wise</label></span>-->
<!--                  					  <span  style="margin-left: 2px;">  <input id="chkboxResultWise" type="checkbox"/> <label>Result Wise</label></span><br/><br/>-->
<!--                  					  <span>  <input id="chkboxEqptGrpWise" type="checkbox"/> <label>Equipment Group Wise</label></span>-->
<!--                  					  </div>-->
<!--								</div>-->
								 </td>
								 <td style="padding-left:20px;" valign="top">
								  <div  class="easyui-paddingbfpx " >
                  					 <label>Pillar Name</label>
							     </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxDM" type="checkbox"/> <label> Development Management(DM)</label><br/><br/>
                  					  <span>  <input id="chkboxET" type="checkbox"/> <label>Education and Training (ET)</label></span><br/><br/>
                  					  <span>  <input id="chkboxJH" type="checkbox"/> <label>Jishu Hozen(JH)</label></span><br/><br/>
                  					  <span>  <input id="chkboxKK" type="checkbox"/> <label>Kobetsu Kaizen(KK)</label></span><br/><br/>
                  					  <span>  <input id="chkboxotpm" type="checkbox"/> <label>Office TPM (OTPM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxPm" type="checkbox"/> <label> Planned Maintenance (PM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxQm" type="checkbox"/> <label>Quality Maintenance(QM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxShe" type="checkbox"/> <label>Safety Health and Environment(SHE)</label></span>
								 </div>
								
								 </td>
								</tr></table></div>
					</div>
						</form>