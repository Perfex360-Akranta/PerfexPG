<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function(){
	//jQuery('#submitForm').val('frmTraining');
	var type=jQuery('#cmbSkillType').val();
	jQuery('#cmbSkillType').val(type);
	if(screen.width <= 1024)
	{
		//jQuery('.easyui-combobox').css('width','286px');
		jQuery('.easyui-datebox').css('width','120px');
		//jQuery('#tblAdvanced').css('padding-left','0px');
		//jQuery('#txtspareDescn').css('width','133px');
		jQuery('#advaDiv').css('padding-right','81px');
		//jQuery('#shelfLbl').css('margin-left','139px');
		jQuery('#spokeLbl').css('margin-left',' 142px');
		jQuery('#Filter').css('height','418px');
	 }	
	initialiseForm('frmTraining');
	 formatDateBox("dteBeftoDate","dd-MMM-yyyy");
	 formatDateBox("dteAfttoDate","dd-MMM-yyyy");
	 formatDateBox("dteBefFromDate","dd-MMM-yyyy");
	 formatDateBox("dteAftFromDate","dd-MMM-yyyy");
	 fillComboBox("frmTraining","cmbAssmtType","combo_empType.sirp" );	 
	 fillWithCurrentDate('dteBeftoDate');
	 fillWithCurrentDate('dteAfttoDate');
	 fillWithCurrentDate('dteBefFromDate');
	 fillWithCurrentDate('dteAftFromDate');
	 numericTextBox('txtknowavg');
	 numericTextBox('txtskillavg');
	 numericTextBox('txtattavg');
	 jQuery('#chkprogramwise').click(function(){		
		
		
		if(jQuery('#chkprogramwise').is(':checked') == true)
		{
				if(jQuery('#chkemployeewise').is(':checked') == true)
		 			{
					jQuery('input:checkbox[name=chkemployeewise]').attr('checked',false); 	
					}
		}	
	}); 
	
	jQuery('#chkemployeewise').click(function(){		
		if(jQuery('#chkemployeewise').is(':checked') == true)
		{
				if(jQuery('#chkprogramwise').is(':checked') == true)
	 			{
				jQuery('input:checkbox[name=chkprogramwise]').attr('checked',false); 
	 			}
		}					
	}); 
	jQuery('#btnfrmTrainingFilter').click(function(){
		openPopFuncArea();
	});
	readOnlyFields('dteAftFromDate');
});	
if(  !jQuery('#cmbemployee').is(':disabled') )
	fillComboBox("frmTraining","cmbemployee","employee.commonFilter" );	
if(  !jQuery('#cmbdesignation').is(':disabled') )
	fillComboBox("frmTraining","cmbdesignation","roleMst.commonFilter" );
if(  !jQuery('#cmbprogm').is(':disabled') )
	fillComboBox("frmTraining","cmbprogm","program.commonFilter" );
if(  !jQuery('#cmbpgmbenefit').is(':disabled') )
	fillComboBox("frmTraining","cmbpgmbenefit","programBenefit.commonFilter" );

if(  !jQuery('#cmbbatch').is(':disabled') )
	fillComboBox("frmTraining","cmbbatch","Batch.commonFilter" );

if(  !jQuery('#cmbspoke').is(':disabled') )
	fillComboBox("frmTraining","cmbspoke","Spoke.commonFilter" );

if(  !jQuery('#cmbtopic').is(':disabled') )
	fillComboBox("frmTraining","cmbtopic","Topics.commonFilter" );
	
if(  !jQuery('#cmbpgmno').is(':disabled') )
	fillComboBox("frmTraining","cmbpgmno","ProgNum.commonFilter" );
	
if(  !jQuery('#cmbSkillType').is(':disabled') )
	//fillComboBox("frmTraining","cmbSkillType","skillType.commonFilter" );
	fillComboBox("frmTraining","cmbSkillType","traningType.tatnd","",false );
	function getRelatedFilterValues()
	{
		var filterStr='';
		
		var cmbdesignation = jQuery("#cmbdesignation").combobox("getValue");
		filterStr += "&cmbdesignation="+cmbdesignation;
		
		var cmbprogm = jQuery("#cmbprogm").combobox("getValue");
		filterStr += "&cmbprogm="+cmbprogm;

		var cmbpgmbenefit = jQuery("#cmbpgmbenefit").combobox("getValue");
		filterStr += "&cmbpgmbenefit="+cmbpgmbenefit;
	
		/*var dtstartDate = jQuery("#startDate").datebox("getValue");
		filterStr += "&dtstartDate="+dtstartDate;
		
		var dtendDate = jQuery("#endDate").datebox("getValue");
		filterStr += "&dtendDate="+dtendDate;*/
		
		var cmbbatch = jQuery("#cmbbatch").combobox("getValue");
		filterStr += "&cmbbatch="+cmbbatch;

		var cmbspoke=jQuery("#cmbspoke").combobox("getValue");/* */
		//alert(cmbspoke);
		filterStr += "&cmbspoke="+cmbspoke;
		
		var cmbtopic=jQuery("#cmbtopic").combobox("getValue");
		filterStr += "&cmbtopic="+cmbtopic;
		
		var cmbemployee = jQuery("#cmbemployee").combobox("getValue");
		filterStr += "&cmbemployee="+cmbemployee;
		
		
		var cmbpgmno = jQuery("#cmbpgmno").combobox("getValue");
		filterStr += "&cmbpgmno="+cmbpgmno;
		
		
		var cmbtrainingtype = jQuery("#cbotrainingtype").val();
		filterStr += "&cmbtrainingtype="+cmbtrainingtype;

		var cmbknowavg = jQuery("#cboknowavg").val();
		filterStr += "&cmbknowavg="+cmbknowavg;
		
		var cmbSkillType = jQuery("#cboskillavg").val();
		filterStr += "&cmbSkillType="+cmbSkillType;
		
		var cmbskillType = jQuery('#cmbSkillType').combobox("getValue");
		filterStr += "&cmbskillType="+cmbskillType;
		
		var cmbcompavg = jQuery("#cbocompavg").val();
		filterStr += "&cmbcompavg="+cmbcompavg;
		
		var cmbtrainingcategory = jQuery("#cbotrainingcategory").val();
		filterStr += "&cmbtrainingcategory="+cmbtrainingcategory;
		
		var chkprogramwise = jQuery('#chkprogramwise').attr('checked');
		//filterStr += "&chkrejectionchkbox="+chkrejectionchkbox;
		filterStr += "&chkprogramwise="+getChkBoxVal('chkprogramwise');	
		
		var chkemployeewise = jQuery('#chkemployeewise').attr('checked');
		filterStr += "&chkemployeewise="+getChkBoxVal('chkemployeewise');
		
		var cmbAssmtType = jQuery('#cmbAssmtType').combobox("getValue");
		filterStr += "&cmbAssmtType="+cmbAssmtType;
		
	/*************** Added By Dhanalakshmi.R for Training Area Hierarchy Filter **********************/	
		
		var trnClsId=jQuery('#classficid').html();
		var trnLcnId=jQuery('#locationid').html();
		var trnAreaId = jQuery('#functionid').html();
		var trnprocessAreaId = jQuery('#processid').html();
		var trnUnitID=jQuery('#unitid').html();
		var depId  = jQuery('#deptid').html();
		
		if(trnAreaId==null || trnAreaId == "" || trnAreaId==' '||trnAreaId=='undefined')
			if(depId==null || depId=='undefined' || depId=="" || depId==' ')
				if(trnprocessAreaId==null||trnprocessAreaId=='undefined'||trnprocessAreaId=="" ||trnprocessAreaId==' ' )
					if((trnUnitID==null || trnUnitID=='undefined' || trnUnitID=="" || trnUnitID==' '))
						filterStr=getClassIdStatus(trnClsId,trnLcnId,filterStr);
					else 
					{
						filterStr += "&trnAreaId="+trnUnitID;
						filterStr += "&trnAreaDrillLevelId="+trnClsId;
						filterStr +="&parentId=MAN";
					}		
				else
				{
					filterStr += "&trnAreaId="+trnprocessAreaId;
					filterStr += "&trnAreaDrillLevelId="+trnUnitID;
					filterStr +="&parentId=UNT";
				}
			else
			{
				filterStr += "&trnAreaId="+depId;
				filterStr += "&trnAreaDrillLevelId="+trnClsId;
				filterStr +="&parentId=NON";
			}
		else
		{
			filterStr += "&trnAreaId="+trnAreaId;
			filterStr += "&trnAreaDrillLevelId="+depId;
			filterStr +="&parentId=DPT";
		}
/***************************************************************************************************************/		
		var knowAvg = jQuery('#txtknowavg').val();
		filterStr += "&knowval="+knowAvg;

		var skillavg = jQuery('#txtskillavg').val();
		filterStr += "&skillval="+skillavg;

		var attavg = jQuery('#txtattavg').val();
		filterStr += "&attval="+attavg;

		var chkbefAftDt = getChkBoxVal('chkbefAftDt');
		
		if(chkbefAftDt==1){
			var befToDt = jQuery('#dteBeftoDate').datebox("getValue");
			filterStr += "&befToDt="+befToDt;
	
			var befFromDt = jQuery('#dteBefFromDate').datebox("getValue");
			filterStr += "&befFromDt="+befFromDt;
	
			var AftToDt = jQuery('#dteAfttoDate').datebox("getValue");
			filterStr += "&AftToDt="+AftToDt;
	
			var AftFromDt = jQuery('#dteAftFromDate').datebox("getValue");
			filterStr += "&AftFromDt="+AftFromDt;
		}	
		return filterStr;
	}
function getClassIdStatus(trnClsId,trnLcnId,filterStr)
{
	if(trnClsId==null||trnClsId=='undefined'||trnClsId=="" ||trnClsId==' ')
	{	
		filterStr += "&trnAreaId="+trnLcnId;
		filterStr += "&trnAreaDrillLevelId="+trnLcnId;
	}
	else
	{
		filterStr += "&trnAreaId="+trnClsId;
		filterStr += "&trnAreaDrillLevelId="+trnLcnId;
		filterStr +="&parentId=LCN";
	}	
	return filterStr;
}
	function openPopFuncArea(){
		
		var dataString="q=2";
		dataString+="&hdnDivid=divTrainFilterFuncLoc";	
		dataString+="&funcAreaKeyIds=divfcareaTrnFilterID";// changed by Dhanalakshmi.R on 6/3/13 for emp role view
		var locId = jQuery('#locationid').html();
		var classficatId =jQuery('#classficid').html();
		var unitId = jQuery('#unitid').html();
		var procesId = jQuery('#processid').html();
		var funcId = jQuery('#functionid').html();
		var depId  = jQuery('#deptid').html();
		var chkmanuf= jQuery('#chkmanuf').html();
		var frmDbRtalkey =  jQuery('#trtProgTrarKeyid').val();
		var txtRtalTrarKeyid ='';
		if(frmDbRtalkey !=' '&& frmDbRtalkey !='' && frmDbRtalkey != null)
			txtRtalTrarKeyid = frmDbRtalkey;
		else	
			txtRtalTrarKeyid = jQuery('#txtRtalTrarKeyid').val();
			if(locId !=' '&& locId !='' && locId != null)
			dataString+="&locId="+locId;
		
		if(classficatId !=' '&& classficatId !='' && classficatId != null)
			dataString+="&classficatId="+classficatId;
		
			if(unitId !=' '&& unitId !='' && unitId != null)
				dataString+="&unitId="+unitId.trim();
			
			if(procesId !=' '&& procesId !='' && procesId != null){
				dataString+="&procesId="+procesId.trim();
				jQuery('#trtProgTrarKeyid').val(procesId);
			}
			if(funcId !=' '&& funcId !='' && funcId != null){
				dataString+="&funcId="+funcId.trim();
				jQuery('#trtProgTrarKeyid').val(funcId);
			}
			else{
				//dataString+="&funcId="+frmDbRtalkey;alert('else'+frmDbRtalkey);
				}
			
			if(depId !=' '&& depId !='' && depId != null)
			dataString+="&depId="+depId.trim();
		
		    dataString+="&txtRtalTrarKeyid="+txtRtalTrarKeyid;
		
		LoadPopUp("loadTrainingPopup", "loadTraingFuncLoc_input.tfl?"+dataString,  true,"28%","48%","5%","3%",  "traing_successCallBack","Training Functional Location", false);
		
		}
	function frmTrainingcmbspoke_onSelect(record)
	{
		
		spokeId=jQuery("#cmbspoke").combobox("getValue");
		//alert(spokeId);
		trarKeyid = jQuery('#txtRtalTrarKeyid').val();
		//alert(trarKeyid);
		
		reloadCombo("frmTraining","cmbtopic","Topics.commonFilter?&spokeId="+spokeId+"&trarkeyid="+trarKeyid);
			return true;
	}
	function dteAfttoDate_onSelect(date)
  	{
	  	
	 	var fromdate = jQuery('#dteAftFromDate').datebox('getValue');
	    var fromDate = convertStringToDate(fromdate);
	   // fillWithCurrentDate('dteAfttoDate');
		var currentDate = getServerDateTime();	
		if(date > currentDate)
		{					
			jQuery('#dteAfttoDate').datebox('clear');
			showValidationErrorMsg('dteAfttoDate','Should Not Exceed Current Date');	
		}	
		else if(fromDate > date)
		{
			jQuery('#dteAfttoDate').datebox('clear');
			showValidationErrorMsg('dteAfttoDate','To Date Must be Greater than FromDate');	
		}
		else
			clearValidationErrorMsg('dteAfttoDate');		
  	}
  	
	function dteAftFromDate_onSelect(date)
  	{			
		var currentDate = getServerDateTime();
		displayDate(date);	
		if(date > currentDate)
		{					
			jQuery('#dteAftFromDate').datebox('clear');
			showValidationErrorMsg('dteAfttoDate','Should Not Exceed Current Date');	
		}	
		
		else
			clearValidationErrorMsg('dteAfttoDate');		
  	}	
	function dteBeftoDate_onSelect(date)
  	{
	  		
	  	var fromdate = jQuery('#dteBefFromDate').datebox('getValue');
		var fromDate = convertStringToDate(fromdate);
		var befToDt=jQuery('#dteBeftoDate').datebox('getValue');
		  var nDate = convertStringToDate(befToDt);
			
		  var d = nDate.getDate();
		  var m = nDate.getMonth();
		  var y = nDate.getFullYear();
		
		  var NextDate= new Date(y, m, d+1);		 
		  var nextMon = NextDate.getMonth();
		  	  nextMon=getMonthStringFromInt(nextMon);
		  	  
		  var Ndate=NextDate.getDate()+"-"+nextMon+"-"+NextDate.getFullYear();
		  jQuery('#dteAftFromDate').datebox('setValue',Ndate);
		//jQuery('#dteAftFromDate').datebox('setValue',befToDt);
		var currentDate = getServerDateTime();	
			
		if(date > currentDate)
		{					
			jQuery('#dteAfttoDate').datebox('clear');
			showValidationErrorMsg('dteBeftoDate','Should Not Exceed Current Date');	
		}	
		else if(fromDate > date)
		{
			jQuery('#dteBeftoDate').datebox('clear');
			showValidationErrorMsg('dteBeftoDate','To Date Must be Greater then FromDate');	
		}
		else
			clearValidationErrorMsg('dteBeftoDate');		
  	}
  	
	function dteBefFromDate_onSelect(date)
  	{			
		var currentDate = getServerDateTime();
		
		displayDate(date);	
		if(date > currentDate)
		{					
			jQuery('#dteBefFromDate').datebox('clear');
			showValidationErrorMsg('dteAfttoDate','Should Not Exceed Current Date');	
		}	
		
		else
			clearValidationErrorMsg('dteAfttoDate');		
  	}	
	
	</script>
	<form id="frmTraining" name="frmTraining"> 
						
						<!--   Training Tab	-->
						<div title="Training Report" style="padding:10px;">
						
							   <div class="sub-header">Regular Filter</div>
						<div align="center">
							<table  width="90%">
								<tr >
										<td colspan="2">
<!-- 											<div  align="left"><label>Training Classification</label>											</div> -->
<!-- 													<div id="divfcareaTrnFilter" style="position:relative ;font-weight: bold;font-size: 11px;border:solid 1px  #008BC2;width:85.5%;width:85.5%\9;height:19px;background-color: #fff;"> -->
<!-- 														<span id="divTrainFilterFuncLoc" style="padding-left:5px;width:100%\9;"></span> -->
<!-- 														<input id="cmbtraingareatype" name="cmbtraingareatype"  class="easyui-combobox"  style="width:126px;"   /> -->
<!-- 														<span style="position: absolute ; right:-3.3%;"><img style="width:18px;height:18px; " src="images/functionalLocimage.jpeg" id="btnfrmTrainingFilter"></span> -->
<!-- 													</div> -->
											<div id="divfcareaTrnFilterID" style="display:none;">
											</div>
											</td>
											</tr>
									<tr>
									<td>		
										   <div  >
			                        			<label>Role</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbdesignation" name="cmbdesignation" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                  
						             
						               <div>
			                        			<label>Program</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbprogm" name="cmbprogm" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div  >
                  					  <label>Employee</label>                   					  
                    			</div> 
			                     <div class="easyui-paddingbfpx"> 
			                        <input id="cmbemployee" name="cmbemployee" class="easyui-combobox"  style="width:250px" value=""  >			                        
			                     </div>
			                     <div><label>Criteria</label></div>
			                     <div style=""> 
						         <input id="cmbspoke" name="cmbspoke" class="easyui-combobox"  style="width:250px" value=""  >                       
						                  </div> 
						      </td>
						      
					                    <td>
						                    <!--<div  class="easyui-paddingbfpx">
			                  					  <label>Start Date</label>
			                   					   <span  style="margin-left: 85px;">End Date</span>
			                    			</div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="startDate" class="easyui-datebox" required="true" style="width:120px;"/>
						                         <span  style="margin-left: 25px;">
						                        	<input id="endDate" class="easyui-datebox" required="true" style="width:120px;"/>
						                         </span>
						                     </div>
						                     
						                     -->
						                    
						                     <div>
			                        			 <label>Batch</label>                       
			                    		 	 </div> 
						                     <div class="easyui-paddingbfpx"> 
						                         <input id="cmbbatch" name="cmbbatch" class="easyui-combobox"  style="width:270px" value=""  >                       
						                     </div>
						                   
						                      <div  >
			                        			 <label>Program No</label>                       
			                    		     </div> 
						                      <div class="easyui-paddingbfpx"> 
						                          <input id="cmbpgmno" name="cmbpgmno" class="easyui-combobox"  style="width:270px" value=""  >                       
						                     </div>
						                     <div >
			                        			<label>Program Benefit</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbpgmbenefit" name="cmbpgmbenefit" class="easyui-combobox"  style="width:270px" value=""  >                       
						                   </div>
						    				<div><label>Topic</label></div>		 
			                        		<div class="easyui-paddingbfpx"> 
						                        <input id="cmbtopic" name="cmbtopic" class="easyui-combobox"  style="width:270px;" value=""  >                       
						                  </div>
					                     </td>
					                     
					                     <td valign="top">
					                     	<div><label>Assesment Type</label></div>		 
			                        		<div class="easyui-paddingbfpx"> 
						                        <input id="cmbAssmtType" name="cmbAssmtType" class="easyui-combobox"  style="width:170px;" value=""  >                       
						                  	</div>
					                     </td>
				                </tr>
				                  
				              
			                </table>
			            </div>
			                      <div class="sub-header">Advanced Filter Criteria</div>
			                    <div id="advaDiv" align="center" style="padding-right:100px;padding-right:50px\9;">
									<table>
									<tr class="">
					                	
					                	<td colspan='2' class="easyui-chkbxgroup" style="width:94%;width:100%\9;">
					                		<div style="float:left;margin-top:25px;padding-left:5px;">
					                			 <input id="chkbefAftDt" name="chkbefAftDt" type="checkbox"  value="Y"/>
					                		</div>
					                		<div style="padding-top:5px;margin-left:25px;width:340px;">
					                		
						                		
											 	 <label> Before Skill Analysis Date</label>
											 	
												
									    	 	<div>
													  <span> 
							                       		 <input id="dteBefFromDate" class="easyui-datebox"  style="width:160px;"value=""/>
							 						 </span>
													 <span  style="padding-left:10px;"> 
							                       		 <input id="dteBeftoDate" class="easyui-datebox"  style="width:160px;"value=""/>
							                       		  <span id="err_dteBfrtoDate" class="tpm-errormsg"></span>		
							 						</span>
											    </div>		 
											</div> 
					                	
						                	<div style="margin-left:51%;margin-left:50%\9;margin-top:-45px;margin-top:-48px\9;">
											 	 <label>After Skill Analysis Date</label>
											 	 
									    	 	<div>
													  <span style=""> 
							                       		 <input id="dteAftFromDate" class="easyui-datebox" style="width:160px;width:100px\9;"value=""/>
							 						 </span>
													 <span  style="padding-left:10px;padding-left:10px\9;"> 
							                       		 <input id="dteAfttoDate" class="easyui-datebox" style="width:160px;width:100px\9;"value=""/>
							                       		  <span id="err_dteAfttoDate" class="tpm-errormsg"></span>		
							 						</span>
											    </div>		 
											 </div> 
					                </td>
					                
					               </tr>
										<tr>
											<td>
							                      <div  class="easyui-paddingbfpx">
				                  					 <label>Type</label>
				                   					 <span  style="margin-left: 158px;"><label>Know.Avg</label></span>
				                    			  </div> 
				                    		      <div class="easyui-paddingbtpx">
				                    		      <!-- <select id="cbotrainingtype" class="easyui-combobox" name="cbotrainingtype" style="width:160px;" required="true">
																<option value="S"> Skill</option>
																<option value="K">Knowledge</option>
																<option value="B">Both </option>
													 </select> -->
													   <input id="cmbSkillType" name="cmbSkillType" class="easyui-combobox"  style="width:160px" value="U"  >    
													 <span  style="margin-left: 25px;"> 
								                       <select id="cboknowavg" class="easyui-combobox" name="cboknowavg" style="width:70px;" required="true">
																	<option value="EQ"> = </option>
																	<option value="GT"> > </option>
																	<option value="LT"> < </option>
																	<option value="GE"> >= </option>
																	<option value="LE"> <= </option>
													   </select> 
													   <input type="text" id="txtknowavg" class="easyui-text" name="txtknowavg" style=" width:25px;" />
							                        </span> 
							                         <span  style="margin-left: 25px;padding-left:5px;"> 
									                         <input id="chkprogramwise" name="chkprogramwise" type="checkbox"/> <label>Program wise</label>
								                     </span> 
								                     <span  style="margin-left: 25px;padding-left:5px;"> 
									                         <input id="chkemployeewise" name="chkemployeewise" type="checkbox"/> <label>Employee wise</label>
								                     </span> 
							                      </div>
							                      
							                       <div  class="easyui-paddingbfpx">
				                  					 <label>Skill Avg</label>
				                   					 <span  style="margin-left: 83px;"><label>Comp.Avg</label></span>
				                   					 <span style="margin-left: 70px;"><label>Category</label></span> 
				                   					 <span id="spokeLbl" style="margin-left: 129px;">
			                    		   </span>     
				                    			  </div> 
				                    		      <div class="easyui-paddingbtpx"> 
							                         <select id="cboskillavg" class="easyui-combobox" name="cboskillavg" style="width:70px;" required="true">
																	<option value="EQ"> = </option>
																	<option value="GT"> > </option>
																	<option value="LT"> < </option>
																	<option value="GE"> >= </option>
																	<option value="LE"> <= </option>
													 </select> 
													 <input type="text" id="txtskillavg" class="easyui-text" name="txtskillavg" style=" width:25px;" />
							                        <span  style="margin-left: 25px;"> 
								                       <select id="cbocompavg" class="easyui-combobox" name="cbocompavg" style="width:70px;" required="true">
																	<option value="EQ"> = </option>
																	<option value="GT"> > </option>
																	<option value="LT"> < </option>
																	<option value="GE"> >= </option>
																	<option value="LE"> <= </option>
													   </select> 
													   <input type="text" id="txtattavg" class="easyui-text" name="txtattavg" style=" width:25px;" />
							                        </span>
							                        
							                       <span  style="margin-left: 30px;"> 
							                           <select id="cbotrainingcategory" class="easyui-combobox" name="cbotrainingcategory" style="width:160px;" required="true">
																<option value="R"> Regular</option>
																<option value="A">Associate</option>
																<option value="C">Contractor </option>

													   </select> 
													   
												 </span>    
			                        			              
			                    		    
						                  
							                      </div>
							                    
							                    
											</td>
					                     </tr>
					                     
				                     </table>
			                     </div>
						</div>
						<input type="hidden" id="hdntrnAreaid" name="hdntrnAreaid" value="${requestScope.funcId}"/>
</form>