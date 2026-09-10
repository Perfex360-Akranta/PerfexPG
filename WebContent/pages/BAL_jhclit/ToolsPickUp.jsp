
 
<script type="text/javascript">
  
 jQuery.noConflict();
 jQuery(document).ready(function(){	
	 var url=jQuery('#hdnPopupUrl').val();
	 var dataStr =jQuery('#hdnCondition').val();
	 
	 jQuery(function () {
		 
		 jQuery("#flTreeComponent").jstree({
            "json_data" : {
                            "ajax" : {
                                "url" : url+dataStr,
                                "data" : function (n) {
                                	//alert(Object.keys(n));
                                	return {
                                		"operation" : "get_children", 
                                        "id" : n.attr ? n.attr("id") : 0
                                        
                                	};
                                }
		   				    
                            },
		   "progressive_render" : true
            },
                        "themes" : {
                            "theme" : "classic",
                            "dots" : true,
                            "icons" : false
                        },
            "plugins" : [ "themes", "json_data", "checkbox","ui","hotkeys" ]
             
        });
		 //jQuery("#flTreeComponent").jstree("open_all", -1);
		 jQuery("#flTreeComponent").bind("check_node.jstree", function (e, data) {
				if(data.rslt.obj.attr("elementtype")=="C" ||data.rslt.obj.attr("elementtype")=="R")
					{
						alert("Cannot select Parent");
					}
				else {
					var isMultiSelect = jQuery("#hdnIsMultiselect").val();
					if(isMultiSelect=="false")
					 getselected_value();
					}
				 
				//alert("data  :"+data.rslt.obj.attr("displayCode"));
				//alert("data  :"+data.rslt.obj.attr("parentId"));
			 	/*if( jQuery("#filterPanel").length >0)
					  jQuery("#filterPanel").hide();*/  
			 });

		 jQuery("#flTreeComponent").bind("loaded.jstree", function (event, data) {	
			 jQuery("#flTreeComponent").jstree("open_all", -1);
		});
		});
  });    
//for close grid
	function close_div(){
		jQuery( "#multiselectPopUpId").dialog('close');
	}
//function getselected value from tree
jQuery('#btnok').click(function (){
	var toGridId = jQuery("#hdnGridId").val();
	getselected_value();
	var selectCount = jQuery("#hdnSelectCount").val();
	
	if(selectCount > 2)
	{
		jQuery("#"+toGridId).jqGrid("clearGridData", true).trigger("reloadGrid");
		alert("Select maximum 3 Tools Only! ");
		return false;
	}
	else
		jQuery('#multiselectPopUpId').dialog('close');
	 
});
    function getselected_value(){

 	   		//
 	   		
        	var isMultiSelect = jQuery("#hdnIsMultiselect").val();
			var colNames =  jQuery("#hdnColNames").val();
			var toGridId = jQuery("#hdnGridId").val();
			var toRowId = jQuery("#hdnRowId").val();
			//alert(toGridId);
			jQuery("#"+toGridId).jqGrid("clearGridData", true).trigger("reloadGrid");
			jQuery("#flTreeComponent").find(".jstree-checked").each(function(i, element){   
				 // var selectedName= jQuery(element).attr("displaycode");
				var selectedType = jQuery(element).attr("elementtype");
				  var colNamesArr =  colNames.split(","); 
				
				  var rowObject =[];
				  //for setting selected value to grid
				  rowObject[0] = new Object();
				  rowObject[0].id="0";
				  if(selectedType  == "T"){
					  
				  for(var j = 0 ; j<colNamesArr.length;j++){
					  
						var value =  jQuery(element).attr(colNamesArr[j]);
						
						(rowObject[0])[colNamesArr[j]] = value;
					
					}
				 
				  //var rowCount = jQuery("#tools").getGridParam("reccount");
				 
				  var rowCount = jQuery("#tools").getGridParam("reccount");
					
					 jQuery("#hdnSelectCount").val(rowCount);
				  var rowid = jQuery(element).attr(colNamesArr[0]);
				  //alert("rowid :"+rowid);
				  jQuery("#"+toGridId).addRowData(rowid,rowObject,'last',rowid);
				  var getrowids =jQuery("#"+toGridId).jqGrid('getDataIDs');
				  
				  if(isMultiSelect == "false"){
				  	jQuery('#multiselectPopUpId').dialog('close');
				  	return;
				   }
				 
				  } 
		        });
	        
        }
    </script>

<form name="frmtoolpickup" id="frmtoolpickup" action="" method="post">

<div id="toolstree" style="">
 
	 <div style="padding-top:10px;margin-left:19px;">
	   <div style="width:480px;height:420px;overflow:auto;margin-top:5px; padding-left:25px; " >
			<div id="treeMsg" style="width: 50%;display:none;" align="center"></div>
			<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
	   </div>
	</div>
<div style="margin-top:5px;">
<input type="button" class ="easyui-button" value="CANCEL" id="closebtn" style="float:right;margin-right:20px;"onclick='close_div();'/>
<input type="button" class ="easyui-button" value="OK" id="btnok" style="float:right;"/>
</div>
<input type="hidden" id="hdnPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsMultiselect" value="${requestScope.isMultiselect}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnCondition" value="${requestScope.condition}" >
<input type="hidden" id="hdnSelectCount" value="" >
</div>
</form>