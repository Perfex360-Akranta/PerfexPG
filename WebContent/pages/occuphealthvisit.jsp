<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<table border="0" align="center" width="80%">
	<tr>
	<td style="width:40%" valign='top'>
	
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Occupational No  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmboccno" name="cmboccno" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label  class="mandatory-lbl"> Factory </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbfact" name="cmbfact" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			

	</td>
	<td style="" valign='top'>
	
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Section  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbsect" name="cmbsect" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label  class="mandatory-lbl"> Cell </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbcell" name="cmbcell" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
	
	</td>
	<td style="" valign='top'>
	
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Equipment  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbeqpt" name="cmbeqpt" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label  class="mandatory-lbl"> Visited Date </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
				<input id="visdate" name="visdate" class="easyui-datebox" required="true" style="width:110px;"/> 
			</div>

	</td>
	</tr>
	</table>
	  <div class="cntborder">
<!--	<div style="border-style:solid;border-width:thin;height:496px">-->
	<table border="0" align="center" width="80%">
	<tr>
	<td style="width:40%" valign='top'>
		    <div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Location  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmblocn" name="cmblocn" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
	
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Employee Type  </label>
			</div> 
			<div class="easyui-paddingbtpx" style="padding-left:70px;">
						<span style="margin-right:2px;"><input id="regular" type="checkbox"/></span><span style="margin-right:2px;">Regular</span> 
						<span style="margin-right:2px;"><input id="contract" type="checkbox"/></span><span style="margin-right:2px;">Contract</span> 
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label class="mandatory-lbl"> Employee  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbemp" name="cmbemp" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
		  <div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label class="mandatory-lbl"> Type  </label>
			</div> 
			<div class="easyui-paddingbtpx" style="padding-left:70px;">
						<span style="margin-right:2px;"><input id="individual" type="checkbox"/></span><span style="margin-right:2px;">Individual</span> 
						<span style="margin-right:2px;"><input id="medcheckup" type="checkbox"/></span><span style="margin-right:2px;">Medical Checkup</span><br/>
						<span style="margin-right:2px;padding-top:5px;"><input id="vaccination" type="checkbox"/></span><span style="margin-right:2px;">Vaccination</span>  
			</div>
			
		    <div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Work Area  </label>
			</div> 
			<div class="easyui-paddingbtpx" style="padding-left:70px;">
						<span style="margin-right:2px;"><input id="hazardous" type="checkbox"/></span><span style="margin-right:2px;">Hazardous</span> 
						<span style="margin-right:2px;"><input id="nonhazardous" type="checkbox"/></span><span style="margin-right:2px;">Non Hazardous</span> 
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Visit Type  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbvistype" name="cmbvistype" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
				     <label>Age</label>
				     <span  style="margin-left: 96px;"><label>Exp(yrs)</label></span>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input type="text" id="age" name="age"   style="width:110px;" value="" class="easyui-text" / >
					  <span  style="margin-left: 28px;"> 
							 <input type="text" id="expyrs" name="expyrs"   style="width:110px;" value="" class="easyui-text" / >
					 </span> 
			</div>
		

			<div class="easyui-paddingbfpx" style="padding-left:70px;">
					<label> Body Part  </label>
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					 <input type="text" id="bodypart" name="bodypart"   style="width:255px;" value=""  class="easyui-text"/ >
			</div>
   			

   			
   	
	</td>
	<td style="" valign='top'>
	
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label class="mandatory-lbl">Problem</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="pbm" name="pbm"></textarea>
			</div>
			
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Creation</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="creation" name="creation"></textarea>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label class="mandatory-lbl">Attended By</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="attndby" name="attndby" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Medical Officer</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbmedoff" name="cmbmedoff" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Medical Creation</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="cmbmedcreatn" name="cmbmedcreatn" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label class="mandatory-lbl">Recommendations</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="recm" name="recm"></textarea>
			</div>
			

		</td>
		<td style="" valign='top'>
		
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Medical Advice</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="medadv" name="medadv"></textarea>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Cause</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="cause" name="cause"></textarea>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Preventive Measure</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="prevmeasure" name="prevmeasure"></textarea>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label>Remarks</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<textarea rows="3" cols="28" id="remarkstxtarea" name="remarkstxtarea"></textarea>
			</div>
			
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<label class="mandatory-lbl">Created By</label>                       
			 </div> 
			<div class="easyui-paddingbfpx" style="padding-left:70px;"> 
					<input id="createdby" name="createdby" class="easyui-combobox"  style="width:255px;" value=""  >
			</div>
			

		</td>
	</tr>
</table>
</div>