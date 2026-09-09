 <script type="text/javascript">
 jQuery.noConflict();
 jQuery(document).ready(function(){	
	 
	 jQuery(function () {
		 jQuery("#jsTreeComponent").jstree({
            "json_data" : {
                            "ajax" : {
                                "url" : 'load.menuTree',
                                "data" : function (n) {
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
                            "icons" : true
                        },
            "plugins" : [ "themes", "json_data", "ui","hotkeys" ]
        });
		 jQuery("#jsTreeComponent").bind("select_node.jstree", function (e, data) {
			//alert(data.rslt.obj.attr("menuName")) ;
			// alert(data.rslt.obj.attr("isParent"));
			//alert(jQuery("#LoadContent").attr(id));
			
			 if( data.rslt.obj.attr("isParent") ==  "false" )
			 {
				// alert("hi");
				 //document.location.href = data.rslt.obj.attr("menuName");
				 
				 var ref = data.rslt.obj.attr("menuName");
				 
				 jQuery("#hiddenUrl").val(ref);
				 //alert(data.rslt.obj.attr("menuCaption"));
				 jQuery("#layoutPanelTitle_M").text(data.rslt.obj.attr("menuCaption"));
				 //jQuery("#layoutPanelTitle_M").css("color", "red");
				 jQuery("#layoutPanelTitle_M").css("font-size", "18px");				 				 

				 //alert(ref);
		/*		 jQuery("#loading").show();
				 jQuery('#LoadContent').hide();
				 jQuery.ajax({
					 url: ref,
					 success: function(data) {
						 //alert(data);
						 jQuery('#LoadContent').html(data);
						 jQuery("#loading").hide();
						 jQuery('#LoadContent').show();
					}

				 });
		*/		 
				 
			//	 jQuery("#loading").show();
			//	 jQuery("#LoadContent").hide();
				 
				 jQuery("#LoadContent").load(ref, function(response, status, xhr) {
					 
					  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			//		    jQuery("#loading").hide();
					  }
					  else{
					//	  alert('completed');
			//			  jQuery("#loading").hide(); 
			//			  jQuery("#LoadContent").show();
					  }
					  
					});
			
			
			 }
			 
         });
    });
  });    
    </script>
 
<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
<div id="jsTreeComponent" class="demo" style="width: 50%"></div>

  
