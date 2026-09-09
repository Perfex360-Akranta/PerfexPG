<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="js/FIproject/ProjectKaizen.js"></script>
<style type="text/css">
textarea {
   font-family: arial;
   font-size: 12px;
}
</style>

<script>
function frmProject_deleteSuccessCallback(result){
    alert(result.successData.msg);
    navigateToPrevForm();
}
</script>
 <form id="frmProject" name="frmProject">
      <div id="wrapper" style="width:100%;padding-left:0px;">       
      	<div style="padding-left:0px;">
      	<div >
	       	<table style="width:98%;">
	       		<tr>
			    
			         <td colspan="2">
			          <div id="frmProjectFormatFuntKeyIds" >						
							<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
							<input type="hidden" id="section" name="cmbsection"  value=""></input>
							<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
							<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
							<input type="hidden" id= "flid"  name= "cmbKzpmFlid" value="${requestScope.Project.kzpmFlid}"/>
							<input type="hidden" id= "elementId"  name= "cmbelementid" value="${requestScope.Project.elementid}"/>
							<input type="hidden" id= "elementType"  name= "hdnElementType" value=""/>
							<div id="projectfun" style=" ">
							</div>
						</div>
					</td>
					<td  style="padding-left:10px;">												
						<div class="">
							<label class="mandatory-lbl">Start Date</label>
							<span style="margin-left:25%;">
								<label>End Date</label>
							</span>
						</div>
						<div>
							<input id="dteKzpmStartdate" name="dteKzpmStartdate" class="easyui-datebox"  value="${requestScope.Project.kzpmStartdate}" style="width:110px;width:110px;\9" />
							<span style="margin-left:10%;">
							<input id="dteKzpmEnddate" name="dteKzpmEnddate" class="easyui-datebox"  value="${requestScope.Project.kzpmEnddate}" style="width:110px;width:110px;\9" />
							</span>						
						</div>	
						<table>
								<tr>
									<td>
										<span id="err_dteKzpmStartdate" class="tpm-errormsg" style=" "></span>
									</td>
									<td>
										<span id="err_dteKzpmEnddate" class="tpm-errormsg" style=" "></span>
									</td>
								</tr>
						</table>	
					</td>
				
			   			
			    
					<td valign="top" align="center" style="padding-left: 1% ">
						<div style="margin-top:-20px;margin-left:-10px;">   <label style="font-weight:bold" > Project Number</label>
						<div  style="margin-left:12px;" >
							<input class="easyui-text"  maxlength="50" style="background-color:cyan;font-weight:bold;width:125px;text-align: center; height:21px;text-align: left;"  id="txtKzpmProjectno"   name="txtKzpmProjectno" value="${requestScope.Project.kzpmKeyid}" readonly="readonly"/>   
			            </div>
			            </div>
						<div class="sub-header" id="DMAIC" style="margin-left:10px; text-align: left;font-size:15px;float:left;width:100px; width:100px\9;height:18px\9;padding-right: 10px;">
							<span style="position:absolute;">DMAIC-Status</span>
						</div>
					</td>
				</tr>
						
		    	<tr>
	       			<td valign="top">
	               		<div style="padding-top:3px;">   <label class="mandatory-lbl">Project Name  </label></div>
		                <div  class="easyui-paddingbfpx" >
						 <input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmProjectname"   name="txtKzpmProjectname" value="${requestScope.Project.kzpmProjectname}"/>
						</div>
						<div>  
							<label class="mandatory-lbl"> Tangible </label>
							<span style="margin-left:15%;">
								<label id="lblAmount">Amount</label>
							</span>
							<span style="margin-left:5%;">
								<label id="lblVerifyAmount">Verif. Amount</label>
							</span>
					     </div>
						<div >
						<input type="checkbox" name="chkKzpmIstangible" id="chkKzpmIstangible"  value="Y"  <c:out value = "${requestScope.Project.kzpmIstangible == 'Y' ? 'checked':''}"/> /> Yes &nbsp&nbsp 
						<input type="checkbox" name="chkKzpmIsintangible" id="chkKzpmIsintangible"  value="Y" <c:out value = "${requestScope.Project.kzpmIsintangible == 'Y' ? 'checked':''}"/> /> No 
						<span style="margin-left:3%;">	
							
							<input class="easyui-text" id="txtKzpmBenefits" name="txtKzpmBenefits"   style="width:63px; height: 21px;"  value="${requestScope.Project.kzpmBenefits}"  />
							<input class="easyui-text" id="txtKzpmVerifiedamnt" name="txtKzpmVerifiedamnt"   style="width:78px; height: 21px;"  value="${requestScope.Project.kzpmVerifiedamnt}" disabled="disabled" />
						</span>	
							<!--<input class="easyui-text"  maxlength="50" style="width:255px; height:21px;"  id="txtKzpmBenefits"   name="cboKzpmBenefits" value="${requestScope.Project.kzpmBenefits}"size="15"/>-->   
		               </div>
		               <!-- 
		               <div style="padding-top:3px">   <label id='lblKzpmProjectmetrics'> Project Metrics</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmProjectmetrics" name="txtKzpmProjectmetrics" >${requestScope.Project.kzpmProjectmetrics}</textarea>   
		               </div>
		                -->
		               <div style="padding-top:3px"><label id="lblKzpmSavings">Other Benefits</label></div>
						<div  class="easyui-paddingbfpx" >
							<textarea rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmSavings" name="txtKzpmSavings" >${requestScope.Project.kzpmSavings}</textarea>	
							<!-- <input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmSavings"   name="txtKzpmSavings" value="${requestScope.Project.kzpmSavings}"size="15"/> -->   
		               	</div>
		               	
		               
		               <div style="padding-top:3px">   <label id="lblKzpmGoalobj">Goal/Objectives</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea rows="4" cols="80" maxlength="900" style="width:255px;" id="txtKzpmGoalobj" name="txtKzpmGoalobj" >${requestScope.Project.kzpmGoalobj}</textarea>   
		               </div>
		           <!--      <div style="padding-top:10px;" id="Stagesbutton">
			               	<input type="button" class="easyui-button"  value="Define Stage" id="btnDefineStage" style="height: 22px;width:80px;"/>
			               	<input type="button" class="easyui-button"  value="MAIC Stages" id="btnMaicStage" style="height: 22px;width:80px;"/>
			               	<input type="button" class="easyui-button"  value="Closure Stage" id="btnClosureStage" style="height: 22px;width:80px;"/>
		               </div>
		           -->    
	          		</td>
		 		  <td style="padding-left:10px;">
					 <div style="padding-top:5px"><label> Area </label></div>
					 	<div  class="easyui-paddingbfpx" >
						 	<input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtKzpmArea"   name="txtKzpmArea" value="${requestScope.Project.kzpmArea}"size="15"/>   
		             	</div>   
					  	<div  >
					  	<label id="lblImprCategory" class="mandatory-lbl">Improvement Category</label>
					  	</div>
	                 	<div>
			                <input id="cmbKzpmImprCategory" name="cmbKzpmImprCategory" class="easyui-combobox"  style="width: 255px; height: 21px;  display: none; cursor: default;" value="${requestScope.Project.kzpmImprCategory}"  />
			            
						</div>
					  	
		                <div style="padding-top:2px">      <label id='lblKzpmProblemstatement'> Problem Statement</label></div>
						<div  class="easyui-paddingbfpx" >
							<textarea rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtKzpmProblemstatement" name="txtKzpmProblemstatement" >${requestScope.Project.kzpmProblemstatement}</textarea>   
		               </div>
		               <div style="padding-top:3px">   <label>Scope/Constraints</label></div>
							<div  class="easyui-paddingbfpx" >
						 	<textarea rows="4" maxlength="900" cols="80" style="width:255px;" id="txtKzpmScopeconst" name="txtKzpmScopeconst" >${requestScope.Project.kzpmScopeconst}</textarea>   
		               </div>
		 			</td>
	      			<td style="padding-left:10px;" valign="top">
	      				<div style="padding-top:3px">   
			   				 <label class="mandatory-lbl"> Project Champion</label>
	      				</div>
	      				<div>
	    						<input class="easyui-combobox"  style="width:255px; height:21px;"  id="cmbKzpmProjectchamp"   name="cmbKzpmProjectchamp" value="${requestScope.Project.kzpmProjectchamp}"size="15"/>   
	                    </div>
	    				
	    				 <div style="padding-top:3px">   <label id='lblKzpmProjectmetrics'> Project Metrics (Key Perfomance Indicator)</label></div>
					   <div   >
							<input id="cmbKzpmProjectmetrics" name="cmbKzpmProjectmetrics" class="easyui-combobox"  style="width:255px; height: 21px;  cursor: default;" value="${requestScope.Project.kzpmProjectmetrics}"  />   
		               </div>
		               
	    				  
	                    <div style="padding-top:3px">   <label id='lblKzpmBusinesscase'> Business Case</label></div>
	     						<div  >
	    						<textarea rows="4" cols="80"  maxlength="900" style="width:255px;" id="txtKzpmBusinesscase" name="txtKzpmBusinesscase" >${requestScope.Project.kzpmBusinesscase}</textarea>   
	                    </div>
	      				<div style="padding-top:3px">   
			   				 <label class="mandatory-lbl"> Created By</label>
	      				</div>
	      				<div>
	    						<input class="easyui-combobox"  style="width:255px; height:21px;"  id="cmbKzpmCreatedby" disabled="disabled"  name="cmbKzpmCreatedby" value="${requestScope.Project.kzpmCreatedby}"size="15"/>   
	                    </div>
	      				
						<div style="padding-top:5px;" id="linkbutton">					 
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Why Why" id="btnPrjYYLink" style="height: 20px;width:65px;"/></span>
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Action Plan" id="btnActionPlan" style="height: 20px;width:70px;"/></span>
						 	<span id="PrjYYSpan"><input type="button" class="easyui-button"  value="Fish Bone" id="btnFishBone" style="height: 20px;width:65px;"/></span>
						  	<input type="button" class="easyui-button"  value="MOM" id="btnMOM" style="height: 20px;width:40px;"/>
						<!-- 	<div>
		             		<input type="button" class="easyui-button"  value="Check List" id="btnCheckList" style="height: 22px;width:70px;"/>
		             		<div id="divChecklistLoad"></div>
		             		</div>
		             	-->	             
	             		</div>
	             		
		      		</td>
		      		<td align="center" valign="top" style="padding-left: 1%;">
				      	<div id="projectcreation">				
						<div style="padding-top:10px;">
							<input type="text" id="Define" value="Define" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Measure" value="Measure" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Analyse" value="Analyse" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />			
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Improve" value="Improve" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Control"  value="Control" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Closure"  value="Closure" disabled="disabled" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						</div>
						<div style="padding-top:12px;">
							<span  id="abnFilemgr" style="left:00%;left:00%\9;top:160px;top:165px\9;" ></span>
						</div>			
	    			</td>
	      		</tr>
	      	</table>
	      	</div>
	      	<div id="Stagesbutton" style=""  >
	      	<div style="display: none;">
	      	<div class="sub-header" style="text-align: left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
				<span style="position:absolute;">DMAIC</span>
				<span style="position:absolute; right:0%;" id="saveDMAICStatusbtn">
					<input type="button" class="easyui-button" value ="Save" id="btnSaveDMAIC" style="height:19px;"/>
					<!--<img id="btnSaveDMAIC" alt="" title="Save DMAIC Verification" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
					-->
				</span>
     			</div>
			<div id="" style="">
				<table  id='dmaicgrid' >
				</table>
				<div id='pagerdmaic'></div>
			</div>
			</div>
			<c:if test="${requestScope.checkList == 'Y'}">
			<div  >
			<div class="sub-header" style="width:99%; width:800px\9;height:18px\9;">
				<span style="position:absolute;">Check List</span>
			</div>
			<div style="" >
			
			<div id="divChecklistLoad" style="height:50%" ></div>
			</div>
			</div>
			</c:if>
			<div class="sub-header" style="width:99%; width:800px\9;height:18px\9;position:relative;">
				<span style="position:absolute;">Authorization</span>
     		</div>
     		<div id="ttAuthorization" style="margin-top:2px;width :1000px;width : 1000px\9; height : 160px;height : 220px\9;">
			<div id="tabAuthorization" border="false" class="easyui-tabs" fit="true" plain="true" style="height : 160px;height : 300px\9;" align="-20px 0 0 0 0"; tabindex="0">
				<div id="divAutDefine" title="Define" style="padding:10px;width:102.6%\9" tabindex="0">
					<div id="divDefApproval" style="width:100%"></div>
				</div>
				<div id="divAutMeasure" title="Measure" style="padding:10px;width:102.6%\9" tabindex="0">
					<div id="divMeaApproval" style="width:100%"></div>
				</div>
				<div id="divAutAnalysis" title="Analyse" style="padding:10px;width:102.6%\9" tabindex="0">
					<div id="divAnlApproval" style="width:100%"></div>
				</div>
				<div id="divAutImprove" title="Improve" style="padding:10px;width:102.6%\9" tabindex="0">
					<div id="divImpApproval" style="width:100%"></div>
				</div>
				<div id="divAutControl" title="Control" style="padding:10px;width:102.6%\9" tabindex="0">
					<div id="divConApproval" style="width:100%"></div>
				</div>
				<div id="ClosureStage" title="Closure" style="padding:10px;width:102.6%\9" tabindex="0">
				<!-- <div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%;">
					<span style="position:absolute;">Closure Stages</span>
				</div>
	      			<div class="main-cntborder" style="width:auto;float: left"> -->
	      			<div id="divClosureApproval" style="width:100%">					
					</div>
	      			<!-- </div> -->
      			</div>
			</div>
			
		</div>
			<!-- <div id="divMAICApproval" style="float:left;">					
			</div>
 -->	      	  <div style="padding-top:5px;" id="">
			               	<input type="button" class="easyui-button"  value="Define Stage" id="btnDefineStage" style="height: 22px;width:80px;"/>
			               	<input type="button" class="easyui-button"  value="MAIC Stages" id="btnMaicStage" style="height: 22px;width:80px;"/>
			               	<!-- <input type="button" class="easyui-button"  value="Closure Stage" id="btnClosureStage" style="height: 22px;width:80px;"/> -->
		               </div>
      		<div id="DefineStage">
	       		<div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%">
		      		<span style="position:absolute;">Define Stage</span>
				</div>
	      		<div class="main-cntborder" style="width:auto;height:auto;float: left;">
	      			<div style="padding-left:5px;">
	      				<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
					      		<span style="position:absolute;">Key Performance Indicator</span>
					      		<span style="position:absolute; right:0%;" id="skilbtn">
									<img id="btnInsertKpi" alt="" title="Add Details" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
								</span>
					      </div>
						 <div style="float:left;">
						 	 <table  id='Kpigrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagerKpi'></div>
						 </div>
						 <!-- <div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
				      		<span style="position:absolute;">Approval</span>
					     </div>
					     
						 <div id="divDefApproval" style="float:left;width:100%; width:800px\9;margin-right:4%;position:relative;"> 
						 	
						 </div>
						 -->
						
						 <div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
					      		<span style="position:absolute;">Resources</span>
					      		<span style="position:absolute; right:0%;" id="skilbtn">
					      			<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:19px;"/>
					      			<input type="button" class="easyui-button" value ="Save" id="btnAddrow" style="height:19px;"/>
								</span>
			    	      </div>
						 <div style="float:left;"> 
						     <table  id='resourcesgrid' >
								<tr><td></td></tr>
							 </table>
							 <div id='pager'></div>
						 </div>
					</div>
				</div>
			</div>
			<div id="MaicStage">
				<div class="sub-header" style="text-align: left;float:left;width:99.2%;background-color:#FAF687; width:800px\9;height:18px\9;position:relative;margin-right:4%;">
					<span style="position:absolute;">MAIC Stages</span>
				</div>
	      		<div class="main-cntborder" style="width:auto;float: left">
	      			<div style="padding-left:5px;">
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">Milestones</span>
							<span style="position:absolute; right:0%;" id="skilbtn">
								<img id="btnAddMst" alt="" title="Add Milestone" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
							</span>
	      				</div>
						<div style="float:left;">
							<table  id='fourgrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagergrid'></div>
						</div>
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">List Of Kaizens</span>
							<span style="position:absolute; right:0%;" id="skilbtn">
									<img id="btnAddKaizen" alt="" title="Select the Kaizen" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
							</span>
		      			</div>
						<div style="float:left;">
							<table  id='Kaizengrid' >
								<tr><td></td></tr>
							</table>
							<div id='pagerkaizen'></div>
						</div>
				<!-- 		<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">DMAIC</span>
							<span style="position:absolute; right:0%;" id="saveDMAICStatusbtn">
								<input type="button" class="easyui-button" value ="Save" id="btnSaveDMAIC" style="height:19px;"/>
								<!--<img id="btnSaveDMAIC" alt="" title="Save DMAIC Verification" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-3;height:24px;" class="">
								-->
				<!--			</span>
		      			</div>
						<div id="" style="float:left;">
							<table  id='dmaicgrid' >
							</table>
							<div id='pagerdmaic'></div>
						</div>
						<div class="sub-header" style="text-align: left;float:left;width:99%; width:800px\9;height:18px\9;position:relative;margin-right:4%">
							<span style="position:absolute;">Authorization</span>
		      			</div>
						<div id="divMAICApproval" style="float:left;">					
						</div>
				-->		
					</div>
				</div>
			</div>
			
		</div>
	</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdntype" value="${requestScope.type}"/>
	<input type="hidden" id="hdnKzpmDefinestage"  name="hdnKzpmDefinestage" value="${requestScope.Project.kzpmDefinestage}"/>
	<input type="hidden" id="hdnMaicstage"   value=""/>
	<input type="hidden" id="hdnKzpmMeasurestage"  name="hdnKzpmMeasurestage" value="${requestScope.Project.kzpmMeasurestage}"/>
	<input type="hidden" id="hdnKzpmAnalysestage" name="hdnKzpmAnalysestage" value="${requestScope.Project.kzpmAnalysestage}"/>
	<input type="hidden" id="hdnKzpmImprovestage" name="hdnKzpmImprovestage" value="${requestScope.Project.kzpmImprovestage}"/>
	<input type="hidden" id="hdnKzpmControlstage" name="hdnKzpmControlstage" value="${requestScope.Project.kzpmControlstage}"/>
	<input type="hidden" id="hdnKzpmClosurestage" name="hdnKzpmClosurestage" value="${requestScope.Project.kzpmClosurestage}"/>
	<input type="hidden" id="txtKzpmProjectnumber" name="txtKzpmProjectnumber" value="${requestScope.Project.kzpmProjectno}"/>   
	<input type="hidden" id="txtKzpmCreatedby" name="txtKzpmCreatedby" value="${requestScope.Project.kzpmCreatedby}"/>        
	<input type="hidden" id="hdnKzpmKeyid" name="hdnKzpmKeyid" value="${requestScope.Project.kzpmKeyid}"/>
	<input type="hidden" id="hdnBenefits" name="hdnBenefits" value="${requestScope.Project.kzpmBenefits}"/>
	<input type="hidden" id="hdnkkeyid" name="hdnkkeyid" value="${requestScope.kkeyid }"/>
	<input type="hidden" id="hdnIsClosure"  name="hdnIsClosure" value="${requestScope.isClosure}"/>
	<input type="hidden" id="hdnIsCheckList"  name="hdnIsCheckList" value="${requestScope.checkList}"/>
	<input type="hidden" id="hdnIsDefineStage"  name="hdnIsDefineStage" value="${requestScope.defineStage}"/>
	<input type="hidden" id="hdnFIPRODEFCurEmps"  name="hdnFIPRODEFCurEmps" value=""/>
	<input type="hidden" id="hdnFIPRODEFCurRole"  name="hdnFIPRODEFCurRole" value=""/>
	<input type="hidden" id="hdnFIPRODEFCurStage"  name="hdnFIPRODEFCurStage" value=""/>
	<input type="hidden" id="hdnIsCheckListChk"  value=""/>
	<input type="hidden" id="hdnLdCkLWrkFlowClbk"  value="N"/>
	<input type="hidden" id="hdnEnableVerfyAmnt"  name="hdnEnableVerfyAmnt" value="${requestScope.enableVerfyAmnt}"/>
	<input type="hidden" id="hdnOldVerifiedAmnt"  name="hdnOldVerifiedAmnt" value="${requestScope.Project.kzpmVerifiedamnt}"/>
	<input type="hidden" id="hdnstage"  name="hdnstage" value="${requestScope.stage}"/>
 </form>