<script type="text/javascript" src="js/jsTree/jquery.tree.checkbox.js"></script>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
 <script type="text/javascript">
 //alert("tree");
 jQuery.noConflict();
 jQuery(document).ready(function(){	
	// alert(12365);
 jQuery("#jTreeComponent").jstree({
            "json_data" : {
                            "ajax" : {
                                "url" : 'load.menuTree',
                                "data" : function (n) {
                                	//alert("menu");
                                	return {
                                		"operation" : "get_children", 
                                        "id" : n.attr ? n.attr("id") : 0
                                	};
                                }
		   				    
                            },
		   "progressive_render" : true
            },
          			
          /*  "ui" : {
    			"theme_name" : "checkbox"
    		},  */                    
    		"themes" : {
                   			  
                           // "theme" : "classic",
                            "dots" : true,
                           "icons" : false
                        },
          // "plugins" : [ "themes", "json_data", "ui","hotkeys" ]
                        "plugins" : [ "themes", "json_data", "checkbox", "hotkeys", "ui" ]
        });
		 jQuery("#jTreeComponent").bind("select_node.jstree", function (e, data) {
			//alert(data.rslt.obj.attr("menuName")) ;
			// alert(data.rslt.obj.attr("isParent"));
			 if( data.rslt.obj.attr("isParent") ==  "false" )
			 {
				// alert("hi");
				 //document.location.href = data.rslt.obj.attr("menuName");
				 var ref = data.rslt.obj.attr("menuName");
				 
				 jQuery("#treeContent").load(ref, function(response, status, xhr) {
			
					  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
					});
									 
			 }
			 
         });
 });
 </script>

<div class="sub-header"> Menu Selection List</div>
<table><tr><td>
<div id="treeMsg2" style="width: 50%;display:none" align="left"></div>
<div id="jTreeComponent" class="demo" style="width: 100%"></div>
<!--<div id="treeContent"></div>-->
</td></tr></table>

