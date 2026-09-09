<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			viewGrid(actionPart,"");

		});

		
		function viewGrid(actionPart,dataString)
		{
			//alert('Inside');
			
			jQuery.ajax(
					    {
					       type: "POST",
					       url: "equipment_view.eqp",
					       data: dataString,
					       dataType: "json",
					       success: function(result)
					       {
						      
							    colD = result.colData;
					            colN = result.colNames;
					            colM = result.colModel;
					            jQuery("#list").GridUnload();
					            jQuery("#list").jqGrid({
								            	jsonReader : {
								            		repeatitems: true,
													id: "0"
								                },
											            				
												    datatype: 'jsonstring',
												    colNames: colN,
													colModel: colM,
													datastr : colD,
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
													caption:'Machine Report',
													width:950,
													height:345,
													//loadonce: true,
													loadComplete : function () {
														//jQuery("#list").jqGrid('setGridParam',{datatype: 'json', data:colD}).trigger("reloadGrid");

													}

												
												});
					          
					                 },
					                 error: function(status){
						            	//alert('hi');     
						  			}
					                 
		                 
				});
			  //setTimeout(function() {$("#list").jqGrid('setGridParam',{datatype:'json'}); },50);
			
		}

</script>

<div class="main-header"align="center">Machine Report </div>



<div class="cntborder">

	 <div style=" "> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
		<div class="clearfix"></div>
</div>