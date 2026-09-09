<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<div class="main-header">JH Kaizen</div>
<br/><br/>
<div class="main-cntborder">
	
	<table  border="0" width="80%" ALIGN="CENTER" class="tablealign-center">
		<tr>
<!--left  pane -->
			<td colspan="2">
				<div class="sub-header"> Layout Details </div>
			</td>
		</tr>
		<tr>
			<td style="width:50%" valign="top">
			<div style="float:left;padding-left:4px;" >
			
			 <div  class="easyui-paddingbfpx">
                  	<label>JH Kaizen No</label>
                   	<span  style="margin-left: 78px;"><label>Date</label></span>
             </div> 
			 <div class="easyui-paddingbfpx"> 
			       <input id="jhkno" name="jhkno" class="easyui-combobox"  style="width:130px;" value="" / >
			        <span  style="margin-left: 32px;"> 
			            <input id="jhkdate" name="jhkdate" class="easyui-datebox" required="true" style="width:130px;"/>
			        </span> 
			 </div>
			 
			  <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Factory</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			         <input  id="factory" name="factory" class="easyui-combobox" style="width:300px;"/ >                       
			   </div>
		
                <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Module</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			        <input type="text" id="module" name="module" class="easyui-combobox" style="width:300px;"/ >                      
			   </div>

		   	   <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Cell</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			       <input type="text" id="cell" name="cell" class="easyui-combobox" style="width:300px;"/ >
			   </div>
		   		
		       <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Machine</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			      <input type="text" id="machine" name="machine"  class="easyui-combobox" style="width:300px;"/ >
			   </div>
	
		      <div class="sub-header"> JH Kaizen Details </div>
			
			   <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Kaizen Description</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			     	<textarea rows="3" cols="34" id="kaizendescn" name="kaizendescn"></textarea>
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Implemented By</label>                       
               </div> 
			    <div class="easyui-paddingbfpx"> 
			      <input type="text" id="implby" name="implby"  class="easyui-combobox" style="width:300px;"/ >
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label class="mandatory-lbl">Implementation Date</label>                       
               </div> 
			    <div class="easyui-paddingbfpx"> 
			     <input id="impldate" name="impldate" class="easyui-datebox" required="true" style="width:130px;"/> 
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label>Kaizen Category</label>                       
               </div> 
			    <div class="easyui-paddingbfpx"> 
			        <input type="text" id="kaizencategory" name="kaizencategory"  class="easyui-combobox" style="width:300px;"/ > 
			   </div>
			   
			    <div  class="easyui-paddingbfpx">
                    <label>Problem</label>                       
               </div> 
			    <div class="easyui-paddingbfpx"> 
			       <textarea rows="3" cols="34" id="problem" name="problem"></textarea>
			   </div>
				</div>
			</td>
			<td style="width:50%" valign="top">
				<div style="padding-left:0px;width : 550px; height : 615px;">
					<div class="easyui-tabs" fit="true" plain="true" style="width:200px; height : 384px;">
							<div title="Before Condition" style="padding:10px;">
								 <div class="sub-header"> Before Description</div>
								 
								 <div style="padding-bottom:50px;"> 
			      						 <textarea rows="6" cols="60" id="befdescn" name="befdescn"></textarea>
			 				     </div>
								 <div class="sub-header"> Before Condition
		  						  <span style="float:right">
			  						    <input type="button" id="image" class="easyui-button"   onclick="" value="Image"/>
			  						    <input type="button" id="clearimage"  class="easyui-button"   onclick="" value="Clear Image"/>
		  						   </span>
		  						 </div>
							</div>
							
							<div title="After Condition" style="padding:10px;">
							 <div class="sub-header"> After Description</div>
								 
								 <div style="padding-bottom:50px;"> 
			      						 <textarea rows="6" cols="60" id="aftdescn" name="aftdescn"></textarea>
			 				     </div>
								 <div class="sub-header">After Condition
		  						  <span style="float:right">
			  						   <input type="button" id="afterimage"  class="easyui-button" onclick="" value="Image"/>
									   <input type="button" id="afterclearimage"  class="easyui-button" onclick="" value="Clear Image"/>
		  						   </span>
		  						  </div>
							</div>
							
							<div title="Result" style="padding:10px;">
							
								    <div  class="easyui-paddingbfpx">
					                    <label>Counter Measure</label>                       
					               </div> 
								    <div class="easyui-paddingbfpx"> 
								       <textarea rows="3" cols="60" id="countermeasure" name="countermeasure"></textarea>
								   </div>
								   
								    <div  class="easyui-paddingbfpx">
					                    <label>Improved</label>                       
					               </div> 
								    <div class="easyui-paddingbfpx"> 
								       <textarea rows="3" cols="60" id="improvd" name="improvd"></textarea>
								   </div>
								   
								    <div  class="easyui-paddingbfpx">
					                    <label>Result</label>                       
					               </div> 
								    <div class="easyui-paddingbfpx"> 
								       <textarea rows="3" cols="60" id="result" name="result"></textarea>
								   </div>
								   
								    <div  class="easyui-paddingbfpx">
					                    <label>Benefits</label>                       
					               </div> 
								    <div class="easyui-paddingbfpx"> 
								       <textarea rows="3" cols="60" id="benefits" name="benefits"></textarea>
								   </div>
								   
								    <div  class="easyui-paddingbfpx">
					                    <label>Remarks</label>                       
					               </div> 
								    <div class="easyui-paddingbfpx"> 
								       <textarea rows="3" cols="60" id="rem" name="rem"></textarea>
								   </div>
							</div>
					</div>
				</div>
			</td>
		</tr>
	</table>

</div>