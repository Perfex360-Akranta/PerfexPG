<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

 <script type="text/javascript">
 jQuery.noConflict();
 jQuery(document).ready(function(){	


	// alert(98658);
	// jQuery(function () {
		
		 jQuery("#treMenu").jstree({
            "json_data" : {
                            "ajax" : {
                                "url" : 'load.menuTree',
                                "data" : function (n) {
                                	return {
                                		"operation" : "get_children", 
                                        "id" : n.attr ? n.attr("id") : 0
                                		};
                                	},
                                "success":function(resp){
                                    
                                     if(! isArray(resp) )
                                     {	
                                	 	if( resp != null &&  resp.sExpires){
                                     		//showCommonErrorMsg(resp.sExpires) ;
                               		  		//document.location.href = "perfex";
                               		  		loadFormSessionTimeOut(resp, "", "");
                                       	}
                                     }

                            	    	     
                                    },
                                "error":function(resp){
                                    if( resp != null &&  resp.sExpires){
                                    	//showCommonErrorMsg(resp.sExpires) ;
                              		  	//document.location.href = "perfex";
                                    	loadFormSessionTimeOut(resp);
                                      }
                                    
                                  }    	
                                    
		   				    
                             	},
		   					 "progressive_render" : true
              },
              "themes" : {
                  "theme" : "classic",
                  "dots" : true,
                  "icons" : true
              },
              "search" : {
                  "case_insensitive" : true,
                   "ajax" : {
                         "url" :"search.menuTree",
                         "data" : function (str) {
                             
                         	return {
                         	 	   "operation" : "search",
                         	       "search_str" : str
                         	};
                          }	,
                          "success":function(result){
							//alert('sea');
                        	  //jQuery("#treMenu li[id=616] a").trigger("click);
                              jQuery('.search_parent_mnu-s').removeClass('search_parent_mnu-s');

                              if(result != null){
                            	  
								  var str = result.toString(); 	
								  var parentIds ="";
	                              if( str.indexOf(",") > 0)
	                              {
	                            	  parentIds = str.substring(str.indexOf(",")+1 , str.lastIndexOf(",") < 0 ?str.length:str.lastIndexOf(",")+1);
		                              parentIds = parentIds.replace(/#/g," > ul > li ").replace(/[0-9]/g,' ').replace(/,/g,' ');
 	                              }
	                              str = str.substring( str.lastIndexOf(",") > 0 ? str.lastIndexOf(",")+1:0);
	                              parentIds += str.replace(/#/g," > ul > li[id=");
	                              parentIds += ']';
	                              jQuery("#treMenu " + parentIds).addClass("search_parent_mnu-s");
	                              
                              } 
                           }	 
                   	 }
     	                 
       	       }, 
       	  
             "plugins" : [ "themes", "json_data", "ui","hotkeys","contextmenu","search"],
       	     "contextmenu":{"items": menuTreeContextMenu}  
       	     	 
        }).bind("select_node.jstree", function (e, data) {
    	
		if(data.rslt.obj.attr("isParent")=="true"){ 
			jQuery("#hdnreportFileName").val(''); 
		}else{ 
			jQuery("#hdnreportFileName").val(data.rslt.obj.attr("reportFileName")); 
		}
		 		loadFormsFromMenu(data.rslt.obj.attr("isParent") ,
				 data.rslt.obj.attr("menuName"),data.rslt.obj.attr("isMaster"),
				 data.rslt.obj.attr("formName"),data.rslt.obj.attr("isFilterNeed"),
				 data.rslt.obj.attr("relatedFilter"),false);
		 
     }).bind('loaded.jstree', function(e, data) {
		        // invoked after jstree has loaded
		       // alert("s");
		        var dirOpenMenuId = jQuery("#dirOpenMenuId").val();
		        if(dirOpenMenuId !=  undefined  && dirOpenMenuId != null && dirOpenMenuId.trim().length >0 )
	     		{		 
			        if(jQuery("li#"+dirOpenMenuId).length > 0 ){
				      //  alert('ddss');				
			        	jQuery("#treMenu").jstree("select_node","#"+dirOpenMenuId,true);
			        }	
			        else{
			        	jQuery("#treMenu").jstree("search", "id="+dirOpenMenuId);//jQuery("#flTreeComponent").jstree("search",searchNode);
			        }	
			    }		
		    }).bind("open_node.jstree", function (event, data) {
		    	//alert("s"); 
		       // if((data.inst._get_parent(data.rslt.obj)).length) {
		        //  data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true);
            	  var dirOpenMenuId = jQuery("#dirOpenMenuId").val();
            	//  alert(dirOpenMenuId);
    			  if(dirOpenMenuId !=  undefined  && dirOpenMenuId != null && dirOpenMenuId.trim().length >0 )
		          {	
            	    jQuery("#treMenu").jstree("select_node","#"+dirOpenMenuId);
		           }	
       				
		 //       } 
		      });
			 
			 
		jQuery(".tre-refresh-menu").click(function(){
			jQuery("#treMenu").jstree("refresh");
			//jQuery("#treMenu").jstree(true).refresh(true, true);
			
		});
		jQuery(".tre-expand-all").click(function(){

			
			jQuery("#treMenu").jstree("open_all", -1);
			
			
		});
		jQuery(".tre-collapse-all").click(function(){
			jQuery("#treMenu").jstree("close_all", -1);
			
		});
		
		 jQuery("#treMenu").bind("click.jstree", function (e, data) {
			 	if( jQuery("#filterPanel").length >0)
				  jQuery("#filterPanel").hide();  
		 });
		 var searchStr =null;
		 jQuery("#treMenu").bind("search.jstree", function (e, data) {

				
			if( jQuery(".search_parent_mnu-s").length > 0)
				jQuery("#tree_brdr").scrollTop(jQuery(".search_parent_mnu-s > ").offset().top -jQuery('#treMenu').offset().top);
			else{
				if( searchStr != data.rslt.str )
					jQuery('li .search-completed-mnu-c').removeClass('search-completed-mnu-c');
					
				searchStr = data.rslt.str;
				
			 	jQuery('#treMenu').find('li').each(function(){	
					 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-mnu-c'))
					 {	
						 jQuery("#tree_brdr").scrollTop(jQuery(this).offset().top -jQuery('#treMenu').offset().top);
						 jQuery(this).addClass('search-completed-mnu-c');
						 return false;	 	
					 }
			 	 });
			 	
			 	//jQuery(".search-completed-mnu-c").removeClass('search-completed-mnu-c');
		 	} 
	        	         
		});		 
				 
		 //jQuery("#treMenu
		 openSetHomePage();
  //  });

	    jQuery("#treMenuContainer").bind("mouseover" ,function (){
	    	jQuery("#treMenu").jstree("enable_hotkeys");
		});
	    jQuery("#treMenuContainer").bind("mouseout",function (){
	    	jQuery("#treMenu").jstree("disable_hotkeys");
		});


/*	    var dirOpenMenuId = jQuery("#dirOpenMenuId").val();
	    alert("dirOpenMenuId " + dirOpenMenuId);
 		if(dirOpenMenuId !=  undefined  && dirOpenMenuId != null && dirOpenMenuId.trim().length >0 )
 		{    alert("dirOpenMenuId 1 " + dirOpenMenuId);
		    var interval_id = setInterval(function(){
		        // $("li#"+id).length will be zero until the node is loaded
		        alert(" ddd ");
		        
		         
		        if(jQuery("li#"+dirOpenMenuId).length != 0){
		            // "exit" the interval loop with clearInterval command
		            clearInterval(interval_id);
		            // since the node is loaded, now we can open it without an error
		            jQuery("#treMenu").jstree("open_node", jQuery('#'+dirOpenMenuId));
		            jQuery("#treMenu li[id=" + dirOpenMenuId + "] a").click();
		         }
		   }, 5);
 		}
 		*/	   
  });    

function menuTreeContextMenu(node){
	//alert(Object.keys(node));
	var isParent = jQuery.jstree._focused()._get_node(node).attr('isparent');
	if( isParent == "true" )
		return "";
	
	var items = {
		 	newTab: {
		 		 label: "Open In New Tab",
		         action: function () {openInNewTab(node);}
			 	}
			};
	return items; 		
}
function openInNewTab(node){
	var menuName = jQuery.jstree._focused()._get_node(node).attr('menuname');
	var id = jQuery.jstree._focused()._get_node(node).attr('id');
	
	document.frmOpenFormDirect.method="POST";
	document.frmOpenFormDirect.action = "perfex";//"perfex";
	document.frmOpenFormDirect.menuName.value=menuName;
	document.frmOpenFormDirect.menuId.value=id;	
	document.frmOpenFormDirect.target ="_blank";
	document.frmOpenFormDirect.submit();
}

function tr_click(value,name){
	
	jQuery("#treMenu").jstree("search",value);					
}    
</script>
    <style type="text/css">

#personalize ul li,
#accTools ul li{
margin:10px 15px; 
}

#personalize ul li,
#accTools ul li{
clear: both;
height: 20px;
}


#personalize ul li div,
#personalize ul li img,
#accTools ul div,
#accTools ul img{
float: left; 
}


#personalize ul li div,
#accTools ul div{
margin-left: 10px;
}

#tree_brdr{
height:88%;
height:88%\9;
width:300px\9;
width:270px;
}
</style>
    
    	
   	    <div style="width: 40%\9;margin-bottom:5px;">
		<input id="treeSearch" class="easyui-searchbox" searcher="tr_click" prompt="Menu search"  style="width:263px;" ></input>
	
		</div>
		<div> <a href='#' title='Refresh Menu' tabindex="0" class='tre-refresh-menu' style="text-decoration:none;"><img src='images/refresh_menu.jpg'  /> Refresh</a> <a href='#' title='Open All Nodes' tabindex="0" class='tre-expand-all' style="text-decoration:none;"> <img src='images/expand_menu.jpg'  /> Expand All</a> <a href='#' title='Close All Nodes' tabindex="0" class='tre-collapse-all' style="text-decoration:none;"><img src='images/collapse_menu.jpg'  /> Collapse All</a> </div>
    	<div id="tree_brdr" style="overflow:auto;  border:1px solid #a4a4a4; background:white"  tabindex="0" >
	  	<div style="padding:10px"  id="treMenuContainer" tabindex="0" >
	  	
	 	<div id="treeMsg" style="width: 50%;display:none" align="center" ;></div>
		<div id="treMenu" class="demo" style="width: 50%"></div>
		</div>
		</div>
		
		<%-- <div id="accPersonalize" class="easyui-accordion" >
    	<div id="personalize" title="${sessionScope.userLogin.loginId} - ${sessionScope.userLogin.employeeName} " iconCls="" style="overflow:auto;padding:0px;fit:true;hieght:200;" >		
		<ul>
		<li>		
		<!-- <img alt="" src="images/themes-small.png"> -->
		<div>Login Time  : ${sessionScope.userLogin.loginTime}</div>
		</li>
		<li>
		<!--  <img alt="" src="images/fav-small.spng"> -->
		<div>Department  : ${sessionScope.userLogin.deptName}</div>		
		</li>
		<li>		
		<!-- <img alt="" src="images/themes-small.png"> -->
		<div>Designation : ${sessionScope.userLogin.designation}</div>
		</li>
		</ul>
		</div>	
		<!--<div id="accTools" title="Tools" iconCls="" selected="false">
		<ul>
		<li>
		<img alt="" src="images/help.png">
		<div>Help</div>		
		</li>
		<li>		
		<img alt="" src="images/change-password-small.png">
		<div>Change Password</div>
		</li>
		<li>		
		<img alt="" src="images/change-login-small.png">
		<div>Change Login</div>
		</li>
		</ul>
		</div>
	--></div> --%>
	 <input type="hidden"id="hdnreportFileName" name="hdnreportFileName" value="" />
	<div id="openHomePage"></div>
	<form id="frmOpenFormDirect" name="frmOpenFormDirect">
	<input type="hidden" name="menuName" >
	<input type="hidden" name="menuId" >
	</form>
	
	<input type="hidden" id="dirOpenMenuId" value="${requestScope.dirOpenMenuId}"/>
	<input type="hidden" id="dirOpenMenuName" value="${requestScope.dirOpenMenuName}"/>