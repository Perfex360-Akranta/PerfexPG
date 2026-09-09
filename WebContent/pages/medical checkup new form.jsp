 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<div align="center" class="main-cntborder" style="width: 800px;">
	<table align="center">
		<tr>
			<td>
				<div class="sub-header">Medical CheckUp</div>
			    <div>
				    <table>
		    			<tr>
		    				<td>
		    					<div class="easyui-paddingbfpx"><label>Medical CheckUp No</label></div> 
		    					<input id="cmbmcno" name="cmbmcno" class="easyui-combobox"  style="width:200px;" value=""  >
		    					<div class="easyui-paddingbfpx">Description of Medical CheckUp</div>
              					<textarea rows="5" cols="6" style="resize:none;width:200px;height : 50px;"></textarea>     
            				</td>
		       				<td style="padding-top:90px">
		        				<label style="padding-left:165px">Email</label>
								<input type="checkbox" id="Standby" required="true" /> 
                   				<input type="button" class ="easyui-button" value="Send Mail" id="newbokin" onclick=""/>
		        			</td>
		        		</tr>
		      		</table>
				</div>
				<table>
					<tr>
						<td style="padding-right:60px">
							<div  class="sub-header">When And Where Details</div>
							<div ><label class="mandatory-lbl">Where</label></div> 
		        			<div> 
		            			<input id="cmbmcno" name="cmbmcno" class="easyui-combobox"  style="width:250px;" value=""  >
		    				</div>
		    				<div><label>From Date</label><label style="padding-left:60px">To Date</label></div> 
							<div> 
                				<input id="auditdate" class="easyui-datebox" required="true"  style="width:124px;"/>			 
                  				<span> 
                  					<input id="to" class="easyui-datebox" required="true"  style="width:124px;"/>
                  				</span> 
                			</div>
              				<div><label class="mandatory-lbl">Attended BY</label></div> 
							<div> 
								<select id=""  name="" style="width:250px;" required="true">
									<option value="-">  - - -</option>
									<option value="ts"> test1</option>
									<option value="tt"> test2</option>
									<option value="tst">test3</option>
									<option value="te"> test4</option>		
								</select>
							</div>
						</td>
						<td>
		       				<div class="sub-header"> Medical CheckUp Details</div>
              				<textarea rows="5" cols="6" style="resize:none; width : 250px; height : 110px;"></textarea>
              			</td>
              		</tr>
              		<tr>
              			<td style="padding-right:60px"> 
							<div class="sub-header"> Target Group Details</div>
              				<textarea rows="5" cols="6" style="resize:none; width : 250px; height : 110px;"></textarea>
              			</td>
              			<td> 
               				<div class="sub-header">Remarks Details</div>
              				<textarea rows="5" cols="6" style="resize:none; width : 250px; height : 110px;"></textarea>
              			</td>
              		</tr> 
				</table>
			</td>
		</tr>
	</table>
</div>
