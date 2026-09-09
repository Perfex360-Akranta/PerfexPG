<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
						jQuery.noConflict();
						jQuery(document).ready(function(){	
							//alert('hi');
			
						jQuery("#btnAdd").click( function(){
							var rowid = jQuery("#list3").jqGrid('getGridParam', 'selrow');
							//alert(rowid);
							var rowData = jQuery("#list3").jqGrid('getRowData',rowid);
							var prodName = rowData.prodName;
							var prodCode = rowData.prodCode;
							if(rowid==null)
								{//do nothing
								}
							else{
									jQuery("#list4").addRowData(rowid,rowData);

									jQuery("#list3").delRowData(rowid);
								}
							});

						jQuery("#btnRemove").click( function(){
							var rowid = jQuery("#list4").jqGrid('getGridParam', 'selrow');
							//alert(rowid);
							var rowData = jQuery("#list4").jqGrid('getRowData',rowid);
							var prodName = rowData.prodName;
							var prodCode = rowData.prodCode;
							if(rowid==null)
							{//do nothing
							}
							else{
									jQuery("#list4").delRowData(rowid);

									jQuery("#list3").addRowData(rowid,rowData);
								}
							});
			
						jQuery("#list3").jqGrid({
							url:'',
							datatype: "local",
							data:[
								  {prodName:"1", prodCode:"2007-10-01"},
								  {prodName:"2", prodCode:"2007-10-02"},
								  {prodName:"3", prodCode:"2007-09-01"},
								  {prodName:"4", prodCode:"2007-10-04"},
								  {prodName:"5", prodCode:"2007-10-31"},
								  {prodName:"6", prodCode:"2007-10-01"},
								  {prodName:"7", prodCode:"2007-10-02"}
							  ],
							colNames:[ 'PRODUCT NAME','PRODUCT CODE'],
							colModel:[ {name:'prodName',index:'prodName',editable:false},
									   {name:'prodCode',index:'prodCode',editable:false}
									  ],
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#pager3', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:400,
							height:300,
							loadonce: true,
							
							ondblClickRow: function(id){
								var rowData = jQuery("#list3").jqGrid('getRowData',id);
								var prodName = rowData.prodName;
								var prodCode = rowData.prodCode;
								
								jQuery("#list4").addRowData(id,rowData);
								
								jQuery("#list3").delRowData(id);
								
							}
								

						
						});

						jQuery("#list4").jqGrid({
							url:'',
							datatype: "local",
							
							colNames:[ 'PRODUCT NAME','PRODUCT CODE'],
							colModel:[ {name:'prodName',index:'prodName',editable:false},
									   {name:'prodCode',index:'prodCode',editable:false}
									  ],
							rowNum:50,
							rowList:[5,10,20],
							rownumbers: true,
							shrinkToFit:false,
							//multiselect: true,
							//multikey: "ctrlKey",
							pager: '#pager4', 
							sortname: 'id',
							viewrecords: true,
							sortorder: "asc", 
							caption:'',
							width:400,
							height:300,
							loadonce: true,
							
							ondblClickRow: function(id){
								var rowData = jQuery("#list4").jqGrid('getRowData',id);
								var prodName = rowData.prodName;
								var prodCode = rowData.prodCode;
								if(id==null)
								{//do nothing
								}
								else{
								jQuery("#list3").addRowData(id,rowData);
								
								jQuery("#list4").delRowData(id);
								}
							}
				
						});
						

					});	
					</script>

<script type = "text/javascript">
		function replace() {
			document.getElementById("Fnddiv").style.display="block";
		}
		function clse() 	{
			document.getElementById("Fnddiv").style.display="none";
	     }
</script>

<div>

		
	<div class="" style="width: 1100px;margin: 0 auto;">
	<table rules="none" border="0">
		<tr>

			<td class="valigncnt" style="width:50%" >
			
					<div class="floatleft" style="padding-right: 150px;">&nbsp;</div>
					<div class="floatleft" style="margin-top:  2%;padding-right: 12%">
						<div><label>Factory</label></div>
						<div style="margin-top: 1%;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 2%;"><label class="mandatory-lbl">Section</label></div>
						<div style="margin-top: 1%;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 2%;"><label class="mandatory-lbl">Sub Group</label></div>
						<div style="margin-top: 1%;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
					</div>
					
					
			       <div class="floatleft" style="padding: 2%;">
						<div><label>Cost Center</label></div>
						<div style="margin-top: 1%;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 2%;"><label class="mandatory-lbl">Cell</label></div>
						<div style="margin-top: 1%;"><input type="text"  class="easyui-combobox" style="width: 300px;"/></div>
			       		<div style="margin-top: 2%;"><label class="mandatory-lbl">Equipment</label></div>
			       		<div style="margin-top: 1%;">
							<span><input type="text" class="easyui-combobox" style="width: 300px;"/></span>
							<span><input type="button" id="view" class="easyui-button" value="Value" style="height: 21px;"/></span>
						</div>
						<div><img>Product Referred in Cell Manning</div>
				 </div>
			
	   <div class="both;"></div> 
			   
			   <div class="sub-cntborder" style="margin: 0%">
			<table >
		    	<tr>
					<td class="valigncnt" style="width:50%">
			   			<div class="floatleft" style="padding-right: 7%">&nbsp;</div>
			   			<div class="floatleft" style="padding-left: 60px;">
			   				<div class="sub-header" style="height : 17px;">Product List</div><br/>
			   				<div class="notes">Double Click the list to add the Product from the Product List</div>
			   					<div id="griddiv" class="floatleft" style="padding-right: 2%"><!-- Grid -->
									<div><table id="list3" width="400px" class="floatleft"></table> </div>
									<div id="pager3"></div>  
								</div><!-- End of Grid -->
							</div><!-- End of Left Side -->
						
			   
			   			
			   			
			   			<div class="floatleft" style="margin-top: 17%"><!-- Center Buttons -->
			   					<input type="button" value=">>"  id="btnAdd" class="easyui-button" style="height: 2%;"/><br/><br/>
			   					<input type="button"  id="btnRemove" class="easyui-button" style="height: 2%;"  value="<<"/>
			   			</div><!-- ********* -->
			   			
			   			<div class="floatleft"><!-- Right Side -->
			   					<div class="sub-header" style="height : 17px;">Selected Product List</div><br/>
			   					<div class="notes" >Double Click the list to remove the Product from the Product List</div>
			   					<div id="griddiv" style="float: left;margin:0px;"><!-- Grid -->
									<div><table id="list4" width="400px" class="floatleft"></table> </div>
									<div id="pager4"></div>  
						  		 </div><!-- End Of Grid -->
							
						</div><!-- ****End Of Right Side****** -->	
							
						<div class="clear"></div>
						<div class="floatleft" style="padding-right: 1.2%">&nbsp;</div>
						<div id="Fnddiv" class="cntborder floatleft" style="display: none;margin-top: 1%;width: 42.5%;"><!-- Filter Div -->
						<!--	<table class="div-border">
								<tr style="width:10%" class="valigncnt">
								 <td>-->
								<div>
								   	<div >
										<span class="sub-header" style="padding-right: 4%;">&nbsp;Find</span>
										<span style="padding-right: 5%;"><label>Search Column:</label></span>
										<span style="padding-right: 2%;"><label>PRODUCT CODE</label></span>
									</div>
									<div style="margin-top: 1%;">
										<span >&nbsp;Options</span>
										<span ><input type="text" class="easyui-combobox"/></span>
										<span><input type="text" style="height: 2%"/></span>
										<span><input type="button" value="Find" id="btnFnd" class="easyui-button" style="height: 2%" /></span>
										<span><input type="button" value="Close" id="btnClse" class="easyui-button" onclick="clse()" style="height: 2%" /></span>
									</div>
							<!-- 	  </td>
								</tr>	
							</table> -->
							</div>
						</div><!-- End of Filter Div -->
						<div class="floatright" style="padding-right: 12%;margin-top: 5px;">
								
								<span><input type="button" value="Find" id="btnFind" class="easyui-button" onclick="replace()" style="height: 2%" /></span>
								<span><input type="button" value="Filter" id="btnFilter" class="easyui-button" style="height: 2%" /></span>
								<span style="">&nbsp;</span>
						</div>	   
			          </td>
			        </tr>
			        </table>
			   </div><!-- grid border -->
			   
		 </td>
	 </tr>
   </table>
</div><!-- Outer Border Close  -->
	

</div>