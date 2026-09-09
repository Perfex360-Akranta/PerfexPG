<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript" src="js/FIproject/dmcProjectKaizen.js"></script>

<style type="text/css">
textarea {
   font-family: arial;
   font-size: 12px;
}

</style>
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
							<input type="hidden" id= "flid"  name= "cmbDmcmFlid" value="${requestScope.Project.dmcmFlid}"/>
							<input type="hidden" id= "elementId"  name= "cmbelementid" value="${requestScope.Project.elementid}"/>
							<input type="hidden" id= "elementType"  name= "hdnElementType" value=""/>
							<div id="projectfun" style=" ">
							</div>
						</div>
					</td>
					<td  style="padding-left:10px;">												
						<div class="">
							<label class="mandatory-lbl">Start Date</label>
							<span style="margin-left:8%;">
								<label>End Date</label>
							</span>
							<span style="margin-left:11%;">
								<label class="mandatory-lbl">Wave</label>
							</span>
							<span style="margin-left:4%;">
								<label>Belt</label>
							</span>
						</div>
						<div>
							<input id="dteDmcmStartdate" name="dteDmcmStartdate" class="easyui-datebox"  value="${requestScope.Project.dmcmStartdate}" style="width:80px;/* width:80px;\9 */" />
							<span style="margin-left:2%;">
							<input id="dteDmcmEnddate" name="dteDmcmEnddate" class="easyui-datebox"  value="${requestScope.Project.dmcmEnddate}" style="width:80px;/* width:80px;\9 */" />
							</span>	
							<span style="margin-left:2%;">
							<input class="easyui-text"  maxlength="2" style="/* height:21px; */width:30px;" id="txtDmcmWave" name="txtDmcmWave" value="${requestScope.Project.dmcmWave}" />
							</span>
							<span style="margin-left:4%;">
							<input class="easyui-combobox"  maxlength="2" style="/* height:21px; */width:45px;" id="cmbDmcmBelt" name="cmbDmcmBelt" value="${requestScope.Project.dmcmBelt}" />
							</span>						
						</div>	
						<table>
								<tr>
									<td>
										<span id="err_dteDmcmStartdate" class="tpm-errormsg" style=" "></span>
									</td>
									<td>
										<span id="err_dteDmcmEnddate" class="tpm-errormsg" style=" "></span>
									</td>
								</tr>
						</table>	
					</td>
				
			   		<td valign="top" align="center" style="padding-left: 1% ">
						<div style="margin-top:0px;margin-left:-10px;">   <label style="font-weight:bold" > Project Number</label>
						<div  style="margin-left:12px;" >
							<input class="easyui-text"  maxlength="50" style="background-color:cyan;font-weight:bold;width:125px;text-align: center; height:21px;text-align: left;"  id="txtDmcmProjectno"   name="txtDmcmProjectno" value="${requestScope.Project.dmcmKeyid}" readonly="readonly"/>   
			            </div>
			            </div>
			            <div class="sub-header" id="DMAIC" style="margin-left:10px; text-align: left;font-size:15px;float:left;width:100px; /* width:100px\9;height:18px\9; */padding-right: 10px;">
							<span style="position:absolute;">DMAIC-Status</span>
						</div>
					</td>
				</tr>
						
		    	<tr>
	       			<td valign="top">
	               		<div style="padding-top:3px;">   <label class="mandatory-lbl">Project Name  </label></div>
		                <div  class="easyui-paddingbfpx" >
						 <input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtDmcmProjectname"   name="txtDmcmProjectname"  tabindex="1" value="${requestScope.Project.dmcmProjectname}"/>
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
						<input type="checkbox" name="chkDmcmIstangible" id="chkDmcmIstangible"  tabindex="2" value="Y"  ${requestScope.Project.dmcmIstangible == 'Y' ? 'checked':''} /> Yes &nbsp&nbsp 
						<input type="checkbox" name="chkDmcmIsintangible" id="chkDmcmIsintangible"  tabindex="3" value="Y" ${requestScope.Project.dmcmIsintangible == 'Y' ? 'checked':''} />  No 
						<span style="margin-left:3%;">	
							
							<input class="easyui-text" id="txtDmcmBenefits" name="txtDmcmBenefits"  tabindex="4" style="width:63px; height: 21px;"  value="${requestScope.Project.dmcmBenefits}"  />
							<input class="easyui-text" id="txtDmcmVerifiedamnt" name="txtDmcmVerifiedamnt"    style="width:78px; height: 21px;"  value="${requestScope.Project.dmcmVerifiedamnt}" readonly="readonly" />
						</span>	
							<!--<input class="easyui-text"  maxlength="50" style="width:255px; height:21px;"  id="txtDmcmBenefits"   name="cboDmcmBenefits" value="${requestScope.Project.dmcmBenefits}"size="15"/>-->   
		               </div>
		               <!-- 
		               <div style="padding-top:3px">   <label id='lblDmcmProjectmetrics'> Project Metrics</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtDmcmProjectmetrics" name="txtDmcmProjectmetrics" >${requestScope.Project.dmcmProjectmetrics}</textarea>   
		               </div>
		                -->
		                	
		               <div style="padding-top:3px"><label id="lblDmcmSavings">Other Benefits</label></div>
						<div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtDmcmSavings" name="txtDmcmSavings" tabindex="5" >${requestScope.Project.dmcmSavings}</textarea>	
							<!-- <input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtDmcmSavings"   name="txtDmcmSavings" value="${requestScope.Project.dmcmSavings}"size="15"/> -->   
		               	</div>
		               	
		               
		               <div style="padding-top:3px">   <label id="lblDmcmGoalobj">Goal/Objectives</label></div>
					   <div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="4" cols="80" maxlength="900" style="width:255px;" id="txtDmcmGoalobj" name="txtDmcmGoalobj"  tabindex="6" >${requestScope.Project.dmcmGoalobj}</textarea>   
		               </div>
		               <div  >
					  	<label id="lblProjectleader" class="mandatory-lbl">Project Leader</label>
					  	</div>
	                 	<div>
			                <input id="cmbDfiwProjectleader" name="cmbDfiwProjectleader" tabindex="14" class="easyui-combobox"  style="width: 255px; /* height: 21px; */  display: none; cursor: default;"  value="${requestScope.DmcProject.dfiwProjectleader}"  />
			            
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
						 	<input class="easyui-text" maxlength="50" style="width:255px; height:21px;"  id="txtDmcmArea"   name="txtDmcmArea" tabindex="7" value="${requestScope.Project.dmcmArea}"size="15"/>   
		             	</div>   
					  	 <div  >
					  	<label id="lblImprCategory" class="mandatory-lbl">Improvement Category</label>
					  	</div>
	                 	<div>
			                <input id="cmbDmcmImprcategory" name="cmbDmcmImprcategory" class="easyui-combobox"    style="width: 255px; /* height: 21px; */  display: none; cursor: default;" tabindex="8" value="${requestScope.Project.dmcmImprcategory}"  />
			            
						</div> 
					  
		                <div style="padding-top:2px">      <label id='lblDmcmProblemstatement'> Problem Statement</label></div>
						<div  class="easyui-paddingbfpx" >
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="500" style="width:255px;height:44px;" id="txtDmcmProblemstatement" name="txtDmcmProblemstatement" tabindex="9" >${requestScope.Project.dmcmProblemstatement}</textarea>   
		               </div>
		               <div style="padding-top:3px">   <label>Scope/Constraints</label></div>
							<div  class="easyui-paddingbfpx" >
						 	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="4" maxlength="900" cols="80" style="width:255px;" id="txtDmcmScopeconst" name="txtDmcmScopeconst" tabindex="10" >${requestScope.Project.dmcmScopeconst}</textarea>   
		               </div>
		               	<div  >
					  	<label id="lblPbuhead" class="mandatory-lbl">PBU Head</label>
					  	</div>
	                 	<div>
			                <input id="cmbDfiwPbuhead"  readonly="readonly" name="cmbDfiwPbuhead" class="easyui-combobox"  style="width: 255px; /* height: 21px; */  display: none; cursor: default;"  value="${requestScope.DmcProject.dfiwPbuhead}" />
			            
						</div>
		 			</td>
	      			<td style="padding-left:10px;" valign="top">
	      				<div style="padding-top:3px">   
			   				 <label class="mandatory-lbl"> Project Champion</label>
	      				</div>
	      				<div>
	    						<input class="easyui-combobox"  style="width:255px; /* height:21px; */"  id="cmbDmcmProjectchamp"   name="cmbDmcmProjectchamp" tabindex="11" value="${requestScope.Project.dmcmProjectchamp}"size="15"/>   
	                    </div>
	    				
	    				 <div style="padding-top:3px">   <label id='lblDmcmProjectmetrics'> Project Metrics (Key Perfomance Indicator)</label></div>
					   <div   >
							<input id="cmbDmcmProjectmetrics" name="cmbDmcmProjectmetrics"  class="easyui-combobox"  style="width:255px; /* height: 21px; */  cursor: default;" tabindex="12" value="${requestScope.Project.dmcmProjectmetrics}"  />   
		               </div>
		               
		              
						
	                    <div style="padding-top:3px">   <label id='lblDmcmBusinesscase'> Business Case</label></div>
	     						<div  >
	    						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="4" cols="80"  maxlength="900" style="width:255px;" id="txtDmcmBusinesscase" name="txtDmcmBusinesscase" tabindex="13" >${requestScope.Project.dmcmBusinesscase}</textarea>   
	                    </div>
	      				<div style="padding-top:3px">   
			   				 <label class="mandatory-lbl"> Created By</label>
	      				</div>
	      				<div>
	    						<input class="easyui-combobox"  style="width:255px; /* height:21px; */ "  id="cmbDmcmCreatedby" readonly="readonly"  name="cmbDmcmCreatedby" value="${requestScope.Project.dmcmCreatedby}"size="15"/>   
	                    </div>
	      				 <div  >
					  	<label id="lblKkchampion" class="mandatory-lbl">KK Champion</label>
					  	</div>
	                 	<div>
			                <input id="cmbDfiwKkchampion" name="cmbDfiwKkchampion"  class="easyui-combobox"  style="width: 255px; /* height: 21px;  */ display: none; cursor: default;" tabindex="15" value="${requestScope.DmcProject.dfiwKkchampion}"  />
			            
						</div>
	    				 <div  >
					  	<label id="lblFinance" class="mandatory-lbl">Finance Head</label>
					  	</div>
	                 	<div>
			                <input id="cmbDfiwFinancehead" name="cmbDfiwFinancehead" class="easyui-combobox"  style="width: 255px; /* height: 21px; */  display: none; cursor: default;" tabindex="16" value="${requestScope.DmcProject.dfiwFinancehead}"  />
			            
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
							<input type="text" id="Define" value="Define" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Measure" value="Measure" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Analyse" value="Analyse" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />			
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Improve" value="Improve" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Control"  value="Control" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
						</div>
						<div style="padding-top:10px;">
							<input type="text" id="Closure"  value="Closure" readonly="readonly" style="border:1px solid black; font-size:15px ; width:120px;height:25x;color:black;font-weight:bold;text-align:center; " />
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
     		
     		<div id="ttAuthorization" style="margin-top:17px;width :1000px;width : 1000px\9; height : 160px;height : 220px\9;">
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
	<!-- <div style="margin-left:480px;margin-top:-200px;">
     			<label id="lblApprovalDMAIC" style="color:green;"><b>Approve DMAIC</b></label>
				<span>	<input type="button" class="easyui-button"  value="Approve Dmaic Stages" id="btnDMAIC" style="height: 25px;width:150px;"/></span>
				</div> -->
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdntype" value="${requestScope.type}"/>
	<input type="hidden" id="hdnDmcmDefinestage"  name="hdnDmcmDefinestage" value="${requestScope.Project.dmcmDefinestage}"/>
	<input type="hidden" id="hdnMaicstage"   value=""/>
	<input type="hidden" id="hdnDmcmMeasurestage"  name="hdnDmcmMeasurestage" value="${requestScope.Project.dmcmMeasurestage}"/>
	<input type="hidden" id="hdnDmcmAnalysestage" name="hdnDmcmAnalysestage" value="${requestScope.Project.dmcmAnalysestage}"/>
	<input type="hidden" id="hdnDmcmImprovestage" name="hdnDmcmImprovestage" value="${requestScope.Project.dmcmImprovestage}"/>
	<input type="hidden" id="hdnDmcmControlstage" name="hdnDmcmControlstage" value="${requestScope.Project.dmcmControlstage}"/>
	<input type="hidden" id="hdnDmcmClosurestage" name="hdnDmcmClosurestage" value="${requestScope.Project.dmcmClosurestage}"/>
	<input type="hidden" id="txtDmcmProjectnumber" name="txtDmcmProjectnumber" value="${requestScope.Project.dmcmProjectno}"/>   
	<input type="hidden" id="txtDmcmCreatedby" name="txtDmcmCreatedby" value="${requestScope.Project.dmcmCreatedby}"/>        
	<input type="hidden" id="hdnDmcmKeyid" name="hdnDmcmKeyid" value="${requestScope.Project.dmcmKeyid}"/>
	<input type="hidden" id="hdnWrinKeyid" name="hdnWrinKeyid" value="${requestScope.wrinkeyid}"/>
	<input type="hidden" id="hdnBenefits" name="hdnBenefits" value="${requestScope.Project.dmcmBenefits}"/>
	<input type="hidden" id="hdnkkeyid" name="hdnkkeyid" value="${requestScope.kkeyid }"/>
	<input type="hidden" id="hdnIsClosure"  name="hdnIsClosure" value="${requestScope.isClosure}"/>
	<input type="hidden" id="hdnIsCheckList"  name="hdnIsCheckList" value="${requestScope.checkList}"/>
	<input type="hidden" id="hdnIsDefineStage"  name="hdnIsDefineStage" value="${requestScope.isDefineStage}"/>
	<input type="hidden" id="hdnFIPRODEFCurEmps"  name="hdnFIPRODEFCurEmps" value=""/>
	<input type="hidden" id="hdnFIPRODEFCurRole"  name="hdnFIPRODEFCurRole" value=""/>
	<input type="hidden" id="hdnFIPRODEFCurStage"  name="hdnFIPRODEFCurStage" value=""/>
	<input type="hidden" id="hdnIsCheckListChk"  value=""/>
	<input type="hidden" id="hdnLdCkLWrkFlowClbk"  value="N"/>
	<input type="hidden" id="hdnEnableVerfyAmnt"  name="hdnEnableVerfyAmnt" value="${requestScope.enableVerfyAmnt}"/>
	<input type="hidden" id="hdnOldVerifiedAmnt"  name="hdnOldVerifiedAmnt" value="${requestScope.Project.dmcmVerifiedamnt}"/>
	<input type="hidden" id="hdnstage"  name="hdnstage" value="${requestScope.stage}"/>
	<input type="hidden" id="hdnLocation"  name="hdnLocation" value="${requestScope.location}"/>
		
	<input type="hidden" id="hdnDelKeyid"  name="hdnDeleyid" value="${requestScope.hdnDelKeyid}"/>
	<input type="hidden" id="hdnDfiwKeyid"  name="hdnDfiwKeyid" value="${requestScope.DmcProject.dfiwKeyid}"/>
	<input type="hidden" id="hdnDfiwFipno"  name="hdnDfiwFipno" value="${requestScope.DmcProject.dfiwFipno}"/>
	<input type="hidden" id="hdnDfiwProjectleader"  name="hdnDfiwProjectleader" value="${requestScope.DmcProject.dfiwProjectleader}"/>
	<input type="hidden" id="hdnDfiwPbuhead"  name="hdnDfiwPbuhead" value="${requestScope.DmcProject.dfiwPbuhead}"/>
	<input type="hidden" id="hdnDfiwKkchampion"  name="hdnDfiwKkchampion" value="${requestScope.DmcProject.dfiwKkchampion}"/>
	<input type="hidden" id="hdnDfiwFinancehead"  name="hdnDfiwFinancehead" value="${requestScope.DmcProject.dfiwFinancehead}"/>
	<input type="hidden" id="hdnDfiwFipstage"  name="hdnDfiwFipstage" value="${requestScope.DmcProject.dfiwFipstage}"/>
	<input type="hidden" id="hdnDfiwApprovalstatus"  name="hdnDfiwApprovalstatus" value="${requestScope.DmcProject.dfiwApprovalstatus}"/>
	<input type="hidden" id="hdnDfiwStatusmessage"  name="hdnDfiwStatusmessage" value="${requestScope.DmcProject.dfiwStatusmessage}"/>
	<input type="hidden" id="hdnDfiwCreatedby"  name="hdnDfiwCreatedby" value="${requestScope.DmcProject.dfiwCreatedby}"/>
	<input type="hidden" id="hdnDfiwModifiedby"  name="hdnDfiwModifiedby" value="${requestScope.DmcProject.dfiwModifiedby}"/>
 </form>