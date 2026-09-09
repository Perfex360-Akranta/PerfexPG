 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery("#list").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}
		
	  ],
	  colNames:[ 'SI No','ASSEMBLY','SPARES','PROBLEM DESCRIPTION','QUANTITY','IS STANDBY','SPARE REF.NO','STANDYBY','STANDBY DESCRIPTION'],
		colModel:[ {name:'SI No',index:'SI No',editable:false, width:60,height:20},
		           {name:'ASSEMBLY',index:'ASSEMBLY',editable:false, width:80,height:20},
				   {name:'SPARES',index:'SPARES',editable:false, width:80,height:20},
				   {name:'PROBLEM DESCRIPTION',index:'PROBLEM DESCRIPTION',editable:false, width:100,height:20},
				   {name:'QUANTITY',index:'QUANTITY',editable:false, width:80,height:20},
				   {name:'IS STANDBY',index:'IS STANDBY',editable:false, width:80,height:20},
				   {name:'SPARE REF.NO',index:'SPARE REF.NO',editable:false, width:100,height:20},
				   {name:'STANDYBY',index:'STANDYBY',editable:false, width:80,height:20},
				   {name:'STANDBY DESCRIPTION',index:'STANDBY DESCRIPTION',editable:false, width:100,height:20},
				  ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,
	//multiselect: true,
	//multikey: "ctrlKey",
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'External services',
	width:850,
	height:220,
	loadonce: true
	
	/*ondblclick: function(id){alert("view clicked");
		var dataString = 'f_date_from='+ f_date_from +'&f_date_to='+ f_date_to;
		
		jQuery("#list2").setGridParam({url:'FiveW2HRpt_view.fwh?'+dataString,datatype:'xml'}).trigger('reloadGrid');
		alert(setGridParam());
		 }
	*/ 

//}); 

});

</script>
<!--

//-->
<div align="center">
	
		<div align="center" style="width: 1100px;" class="main-cntborder">
			<table>
				<tr>
					<td>
						<div align="center">
							<table align="center">
								<tr>
									<td>
					 					<div class="easyui-paddingbfpx"><label>Doc No :</label></div>
					 					<div class="easyui-paddingbfpx">
					 						<input id="cmbDocno" name="cmbDocno" class="easyui-combobox"  style="width:150px;" value="" />
					 					</div>
									</td>
									<td style="padding-right:5">
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Date</label></div>
										<div class="easyui-paddingbfpx">	
					 						<input id="date" class="easyui-datebox" required="true"  style="width:150px;"/>
					 					</div>
					 				</td>
					 				<td style="padding-right:5">
										<div class="easyui-paddingbfpx"><label>Cost Center</label></div>
										<div class="easyui-paddingbfpx"> 
                							<input id="cmbcstcntr" name="cmbcstcntr" class="easyui-combobox"  style="width:300px;" value=""  >
										</div>
									</td>
									<td>
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Reference No</label></div> 
                  						<div class="easyui-paddingbfpx"> 
                							<input type="text" class="easyui-text" id="Refno" style="width:300px;">
										</div>
									</td>
							 	</tr>
							 	<tr>
					 				<td colspan=2>
					 	 				<div class="easyui-paddingbfpx"><label>Factory</label></div>
                   						<div class="easyui-paddingbfpx"> 
                  							<input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:300px;" value=""  >
               	  						</div>
									</td>
									<td>
										<div class="easyui-paddingbfpx"><label>Cell</label></div>
										<div class="easyui-paddingbfpx"> 
                							<input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:300px;" value=""  >
										</div>
									</td>
									<td rowspan=2>
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Description</label></div> 
                 	 					<div class="easyui-paddingbfpx"> 
                							<textarea rows="5" cols="6"  style="width : 300px; height : 75px; resize:none;"></textarea>
										</div>
									</td>
								</tr>               	  			
								<tr>
									<td colspan=2>
										<div class="easyui-paddingbfpx"><label>Section</label></div>
										<div class="easyui-paddingbfpx"> 
                							<input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:300px;" value=""  >
										</div>
									</td>
									<td>
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Equipment</label></div> 
                  						<div class="easyui-paddingbfpx" > 
                							<input id="cmbeqpmnt" name="cmbeqpmnt" class="easyui-combobox"  style="width:300px;" value=""  >
										</div>
									</td>
								</tr>
							</table>
				 		</div>
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<div id="tt" style="width:920px;height:520px;" align="center">
							<div class="easyui-tabs" fit="true" plain="true" style="height:550px;width:800px;">
								<div title="Status" style="padding:10px;">
									<table>
										<tr>
											<td>
												<div style="float:left;padding-left:50px;">
													<table align="center">
														<tr>	
															<td colspan=2 style="padding-right:90px;"> 
																<div class="sub-header"> Requested Details</div>
															</td>
															<td colspan=2> 
																<div class="sub-header"> Completion Details</div>
															</td>
														</tr>
														<tr>
															<td colspan=2>
																<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Nature of work</label></div> 
																<div class="easyui-paddingbfpx" style="padding-right:100px;"> 
																	<select id="cmbnatofwork"  name="cmbnatofwork" style="width:300px; height : 21px;" required="true">
																		<option value="-">  - - -</option>
																		<option value="ts"> test1</option>
																		<option value="tt"> test2</option>
																		<option value="tst">test3</option>
																		<option value="te"> test4</option>		
																	</select>
					                							</div>
					                						</td>
					                						<td colspan=2>
																<div class="easyui-paddingbfpx"><label>Received By</label></div> 
		                  										<div class="easyui-paddingbfpx"> 
		                											<input id="cmbRcvd" name="cmbRcvd" class="easyui-combobox"  style="width:300px;" value=""  >
																</div>
															</td>
					                					</tr>
					                					<tr>
					                						<td colspan=2>
																<div class="easyui-paddingbfpx"><label>Status</label></div> 
					                  							<div class="easyui-paddingbfpx"> 
					                  								<select id="cmbstatus"  name="cmbstatus" style="width:300px;height : 21px;" required="true">
																		<option value="-">  - - -</option>
																		<option value="ts"> test1</option>
																		<option value="tt"> test2</option>
																		<option value="tst">test3</option>
																		<option value="te"> test4</option>		
																	</select>
					                							</div>
					                						</td>
					                						<td colspan=2>	
																<div class="easyui-paddingbfpx"><label>Date</label></div> 
		                  										<div class="easyui-paddingbfpx"> 
			                										<input id="date" class="easyui-datebox" required="true" style=" width : 148px; height : 21px;"/> 
																</div>
															</td>
					                					</tr>
					                					<tr>
					                						<td colspan=2>
																<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Send By</label></div> 
					                  							<div class="easyui-paddingbfpx"> 
					                								<input id="cmbsndby" name="cmbsndby" class="easyui-combobox"  style="width:300px;" value=""  >
																</div>
															</td>
															<td rowspan=4 colspan=2>
																<div class="easyui-paddingbfpx"><label>Action Taken</label></div> 
		                 										<div class="easyui-paddingbfpx"> 
		                			 								<textarea rows="5" cols="6" style="width : 300px; height : 170px;resize:none;"></textarea>
						    									</div>
						    								</td>
														</tr>
														<tr>
															<td colspan=2 colspan=2>
																<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Sent To</label></div> 
					                  							<div class="easyui-paddingbfpx"> 
					                								<input id="cmbsndto" name="cmbsndto" class="easyui-combobox"  style="width:300px;" value=""  >
																</div>
															</td>
														</tr>
														<tr>
															<td colspan=2>
																<div class="easyui-paddingbfpx"><label>Expected date</label></div> 
					                  							<div class="easyui-paddingbfpx"> 
					                								<input id="Expected date" class="easyui-datebox" required="true" style="width:160px;"/> 
																</div>
															</td>
														</tr>
														<tr>
															<td class="easyui-paddingbfpx">
																<div Style="padding-bottom:7px"><label>Stand By</label></div>
																<div class="easyui-paddingbfpx">												
																	<input type="checkbox" id="Standby" required="true" />
																</div>
															</td>
															<td> 
																<div class="easyui-paddingbfpx"><label>Reference No</label></div>
									 							<div class="easyui-paddingbfpx"> 
			                			 							<input type="text" class="easyui-text"  id="Refno" style="width:210px;">
			                  									</div>
			                  								</td>
			                  							</tr>
			                  							<tr>
			                  								<td colspan=2>
																<div class="easyui-paddingbfpx"><label>Stand by Description</label></div> 
			                 	 								<div class="easyui-paddingbfpx"> 
			                										<textarea rows="5" cols="6" style="width : 300px; height : 61px;resize:none;"></textarea>
																</div>
															</td>
															<td colspan=2>
						    									<div class="easyui-paddingbfpx"><label>Observation</label></div> 
		                 										<div class="easyui-paddingbfpx">  
		                			 								<textarea rows="5" cols="6" style="width : 300px; height : 61px;resize:none;"></textarea>
						    									</div>
						    								</td>
														</tr>
														<tr>
															<td>
																<div Style="padding-bottom:12px"><label>Returned</label></div>
																<div class="easyui-paddingbfpx">
																	<input type="checkbox" id="Standby" required="true" />
																</div>
															</td>
															<td> 
																<div class="easyui-paddingbfpx"><label>Returned Date</label></div>
									 							<div class="easyui-paddingbfpx"> 
			                			 							<input type="text" class="easyui-text"  id="Refno" style="width:210px;">
			                  									</div>
			                  								</td>
			                  								<td>
						    									<div class="easyui-paddingbfpx"><label>Completion Date</label></div> 
		                  										<div class="easyui-paddingbfpx"> 
		                			 								<input id="compdate" class="easyui-datebox" style=" width : 148px;" required="true"/>
																</div>
															</td>
															<td>
																<div class="easyui-paddingbfpx"><label>Service Cost</label></div> 
		                  										<div class="easyui-paddingbfpx"> 
		                											<input type="text" id="srvcst" class="easyui-text"  required="true" style=" width : 145px;"/>
																</div>
															</td>
			                  							</tr>	
			                  						</table>
												</div>
											</td>
										</tr>
									</table>
				 				</div>
								<div title="Component Details" style="padding:10px;">
									<table>
										<tr>
											<td>
												<div style="padding-right:10">
													<div class="easyui-paddingbfpx"><label>Assembly</label></div> 
													<div class="easyui-paddingbfpx"> 
														<input id="cmbasmbly" name="cmbasmbly" class="easyui-combobox"  style="width:250px;" value=""  >
													</div>
													<div class="easyui-paddingbfpx"><label>Spare</label></div> 
													<div> 
														<input id="cmbspr" name="cmbspr" class="easyui-combobox"  style="width:250px;" value=""  >	
													</div>
													<div class="easyui-paddingbfpx"><label>Problem Description</label></div> 
													<div class="easyui-paddingbfpx"> 
														<textarea rows="5" cols="6" style="width : 250px; height : 41px;resize:none;"></textarea>
													</div>
												</div>
											</td>
											<td>
												<div style="padding-right:10">
													<div class="easyui-paddingbfpx"><label>Quantity</label></div> 
													<div class="easyui-paddingbfpx"> 
														<input type="text" class="easyui-text"  id="Quantity" style=" width : 250px; height : 21px;">
													</div>
													<div class="easyui-paddingbfpx"><label style="padding-right:10px;">Stand By</label><label>Reference No</label></div> 
													<div class="easyui-paddingbfpx"> 
														<input type="checkbox" id="Standby1" required="true" /> 
														<span style="padding-left:50px;"> 
															<input type="text" class="easyui-text"  id="Refno1" style="width:180px; height : 21px; " disabled="disabled">
														</span> 
													</div>
													<div class="easyui-paddingbfpx"><label>Stand by Description</label></div> 
													<div class="easyui-paddingbfpx"> 
														<textarea rows="5" cols="6" style="width : 250px; height : 41px;resize:none;" disabled="disabled"></textarea>
													</div>
												</div>
											</td>
											<td>		
												<div>
													<div class="easyui-paddingbfpx"><label style="padding-right:15px;">Returned</label> <label>Returned Date</label></div> 
							 						<div class="easyui-paddingbfpx"> 
														<input type="checkbox" id="Standby1" required="true" /> 
														<span class="easyui-paddingbfpx" style="padding-left:60px;"> 
								   							<input id="retdate1" class="easyui-datebox" style="width : 190px;" required="true"/>
														</span> 
													</div>
													<div class="easyui-paddingbfpx" style="text-align:right;padding-top:60px;">
														<input type="button" class ="easyui-button" value="Insert" id="Insert" "/>
														<input type="button" class ="easyui-button" value="Clear" id="Clear" />
														<input type="button" class ="easyui-button" value="delete" id="delete" />
													</div>
												</div>
											</td>
										</tr>
									</table>
									<div style="clear:both"></div>
									<div id="grid">
										<!--	End grid	 Script				-->    
											<table id="list" style="width:100%"><tr><td/></tr></table>
											<div id="pager"></div>
										<!--	end of grid			-->
									</div>
								</div>
							</div>
							</div>					
						</td>
					</tr>
				</table>
			</div>
		</div>