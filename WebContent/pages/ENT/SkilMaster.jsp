<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmSkill'); 
	var hiddenUrl = jQuery('#hiddenUrl').val();
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	
	 jQuery(function () {
		 	processTree( jQuery("#flTreeComponent"),'loadval.skil','searchnode.skil');
			jQuery("#flTreeComponent") .bind("select_node.jstree", function (e, data) {
					var elemType = data.rslt.obj.attr("elementType");	
			 		jQuery('#elemIdToPasteAsm').val(data.rslt.obj.attr("elementType")+':'+data.rslt.obj.attr("elementId"));
			 		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
			 		jQuery('#hdnIdParentNodes').val(data.rslt.obj.attr("parentId"));			 	
			 });
			 var fluSearchStr = '';
			jQuery("#flTreeComponent").bind("search.jstree", function (e, data) {
		         if( jQuery(".search_parent_flu-s").length > 0){
					jQuery("#flTreeLayer").scrollTop(jQuery(".search_parent_flu-s").offset().top -jQuery('#flTreeComponent').offset().top);
		         }	
				 else{
						if( fluSearchStr != data.rslt.str )
						jQuery('li .search-completed-flu-c').removeClass('search-completed-flu-c');					
						fluSearchStr = data.rslt.str;
				
			 			jQuery('#flTreeComponent').find('li').each(function(){	
							 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-flu-c'))
						 	 {	
								 jQuery("#flTreeLayer").scrollTop(jQuery(this).offset().top -jQuery('#flTreeComponent').offset().top);
								 jQuery(this).addClass('search-completed-flu-c');
								 return false;	 	
						 	 }
			 			 });
				 }
			});
			jQuery("#flTreeComponent").bind("loaded.jstree", function (event, data) {	
					//processTree( jQuery("#flTreeComponent"),'loadval.funlocn?elementId=CMP/01&elementType=CMP&parentId=CMP/01');
			});
			jQuery("#flTreeComponent").bind("open_node.jstree", function (event, data) { 			
			      if((data.inst._get_parent(data.rslt.obj)).length) { 
			        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
			      } 
		    });
      });
  });
function loadval_searchCallBack(result){
	 jQuery("#flTreeSearch").css('display','none');
	 jQuery('.search_parent_flu-s').removeClass('search_parent_flu-s');
     if(result != null){   	  
		 var str = result.toString(); 	
		 var parentIds ="";
         if( str.indexOf(",") > 0)
         {
       	  	 parentIds = str.substring(str.indexOf(",")+1 , str.lastIndexOf(",") < 0 ?str.length:str.lastIndexOf(",")+1);
             parentIds = parentIds.replace(/#/g," > ul > li ").replace(/[0-9,A-Z]/g,' ').replace(/,/g,' ');
         }
         str = str.substring( str.lastIndexOf(",") > 0 ? str.lastIndexOf(",")+1:0);
         parentIds += str.replace(/#/g," > ul > li[id=");
         parentIds += ']';
         //alert(parentIds);
         jQuery("#flTreeComponent " + parentIds).addClass("search_parent_flu-s");         
     }    
}
function customMenu(node) {
	
   var x = jQuery.jstree._focused()._get_node(node).attr('id');
   var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');
   var selId = jQuery('#hdnIdNode').val();
   if(selId != null && selId != '' && selId != ' ')
   	jQuery("#"+selId+" a").removeClass('jstree-clicked');
   // The default set of all items
   var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');
  
   var prevNode = jQuery("#hdnRightClkNode").val(); 	
   if(prevNode != null && prevNode != '' && prevNode != ' ')
	   jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
  	// jQuery("#"+prevNode+" a").css('color','black');
  
   jQuery("#hdnRightClkNode").val(x);
   jQuery("li[elementid="+elementId+"]  > a").addClass('jstree-clicked');
   if(elementId == '1')
   {
	   var items = {		 	
	        addItem: { // The "add" menu item
	            label: "Add Skill",
	            action: function () {validateNode(node);}
	        }
		};
   }
   else
   {
	   var items = {
		 	viewItem: {// The "view" menu item
		 		 label: "View Skill",
		         action: function () {processNode(node,"VIEW");}
			 	},
			
	        addItem: { // The "add" menu item
	            label: "Add Skill",
	            action: function () {validateNode(node);}
	        },
	        editItem: { // The "edit" menu item
	            label: "Edit Skill",
	            action: function () {processNode(node,"MODIFY");}
	        }
	        ,deleteItem: {// The "delete" menu item
				label: "Delete Skill",
		        action: function () {processNode(node,"DELETE");}
			 	}
		};
   }
 return items;
}
function validateNode(obj){
	var originalid = jQuery.jstree._focused()._get_node(obj).attr('originalid');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	jQuery("#hdnParentsNodes").val(originalid);
	var params="";
	params="q=2&keyId=" + originalid;
	processAjaxCalls('skilllevel_validate.skil','?' + params,'nodeValidateSuccess','nodeValidateFail');
}

function processNode(obj,mode)
{
	var originalid = jQuery.jstree._focused()._get_node(obj).attr('originalid');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	jQuery("#txtelemId").val(originalid);
	
	var params="";
	if(mode=="DELETE"){
		params="mode="+ mode + "&keyId="+originalid;
		if(confirm("Do You Want To Delete?") == true)
		{
			jQuery("#hdnParentsNodes").val(parentNodes);
			deleteRecord("frmSkill",'skil_del.skil?'+ params);
	    	//processAjaxCalls('skil_del.skil','?' + params,'nodeDelSuccess','nodeDelFailure');
		}  
	}
	else{		
		if (mode=="INSERT"){			
			params="mode="+ mode + "&parentId=" + originalid;
		}
		else if(mode=="MODIFY"){			
			params="mode="+ mode + "&parentId=" + parentNodes +"&keyId=" + originalid;
		}	
		else if(mode=="VIEW"){
			jQuery("#hdnParentsNodes").val(parentNodes);
			params="mode="+ mode + "&parentId=" + parentNodes +"&keyId=" + originalid;
		}		
		LoadPopUp("divShowSkill", "skil_input.skil?" + params, true,"760px","410px","0px","20%", "multiSelectOk_Callback");
	}
}

function loadPopUp_ErrorCallback(args){}
function multiSelectOk_Callback(args){	
}

function MultiSelectCancel_CallBack(id)
{	
	refreshTree();
	closePopUpDialoge("divShowSkill");		
}
function frmSkill_deleteSuccessCallback(result)
{
	var elemId=jQuery("#hdnParentsNodes").val();
	refreshNode("flTreeComponent",elemId);	
	alert(result.successData.msg);
}

function nodeDelFailure(result)
{	
	alert(result.tpmException);
}

function nodeValidateSuccess(result)
{
	//alert(result.successData.msg);
	var validate="";
	validate=result.successData.msg;
	if (validate.trim()=="Valid") {
		var originalid = jQuery("#hdnParentsNodes").val();
		var params="";	
		params="mode=INSERT&parentId=" + originalid;
		LoadPopUp("divShowSkill", "skil_input.skil?" + params, true,"760px","410px","0px","20%", "multiSelectOk_Callback");
	}
	else
	{
		alert("Maximum Skill Level Should be " + validate);
		return false;
	}	
}

function nodeValidateFail(result)
{
	return false;
}

function openNode(treeId,nodeId)
{	
	if(jQuery("#"+treeId).jstree("is_open", jQuery('#'+nodeId)) == false)	{
		jQuery("#"+treeId).jstree("open_node",jQuery('#'+nodeId));
	}	
}


function refreshTree()
{
	var tree = jQuery.jstree._reference("#flTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}

function refTree(node)
{
	//alert(node);
	var tree = jQuery.jstree._reference("#flTreeComponent");
	var currentNode = tree._get_node(node, true);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh();
}

function getMstFrmUrl(mstKey,parentDatas,formMode)
{	
	var mstUrl = null;	
	var dataString = '?q=2&'+frmMode.frmMode+'='+formMode+'&'+frmMode.keyId+'='+mstKey.replace('_','/');	
	mstKey = mstKey.substring(0,3);
	if(formMode == frmMode.edit)
		dataString += '&'+frmMode.lockFields+'='+addToUrl(mstKey);
		mstUrl = "skil_input.skil"+dataString;	
	return mstUrl;	
}

function addToUrl(mstKey)
{
	var dataString=null;
		dataString = "keyId";	
	return dataString;	
}

</script>
<form id="frmSkill" name="frmSkill">
	<input type="text" style="display:none;" id="txtNode" name="txtNode"/>
	<input type="text" style="display:none;" id="txtformFld" name="txtformFld"/>
	<input type="text" style="display:none;" id="txtelemId" name="txtelemId"/>
	<input type="text" style="display:none;" id="hdnParentsNodes" name="hdnParentsNodes"/>
	<input type="text" style="display:none;" id="elemIdToPasteAsm" name="elemIdToPasteAsm"/>
	<input type="text" style="display:none;" id="hdnIdNode" name="hdnIdNode"/>
	<input type="text" style="display:none;" id="hdnIdParentNodes" name="hdnIdParentNodes"/>
	<input type="hidden" id="hdnMstId" />
	<input type="hidden" id="hdnRightClkNode" />
	<div style="margin-left:1%">
		<div id="flTreeLayer" style="float:left;width:50%;height:94%;overflow:auto;" class="sub-cntborder">
			<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
			<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
			<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
	   </div>
	</div>
</form>
	 
  		   

