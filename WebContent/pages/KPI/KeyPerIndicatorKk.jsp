<!--Created Roopa on 03-Sep-2012 -->
<!--<script type="text/javascript" src="js/jsTree/jquery.jstree2.js"></script>-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
function frmFindNodecmbLocation_onSelect(record){
	//alert(record.text);
	var location = record.id;
	loadTree1(location,null);
	
}

function frmFindNodetxtKinkPillarid_onSelect(record)
{
	//alert(record.text);
	var pillarid= record.id;
	loadTree1(null,pillarid);	
}

function frmFindNodetxtKinkPillarid_onClear(record){
	var pillarid= "";
	loadTree1(null,pillarid);	
}


jQuery(document).ready(function(){		
	var hiddenUrl = jQuery('#hiddenUrl').val();
	//alert("hiddenUrl"+hiddenUrl);
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'&formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	
	initialiseForm('frmFindNode');	
	var pillarId=getFieldValue("txtKinkPillarid");
	fillComboBox("frmFindNode","cmbfindTreeNode","combo_keyPerformParent.keyPerInd?pillarId=" +pillarId );
	fillComboBox("frmFindNode", "cmbLocation", "location.commonFilter");
	fillComboBox("frmFindNode", "txtKinkPillarid", "pillar.commonFilter");
	jQuery("#btnMapFun").click(function() {
		var type=getFieldValue("txtFormType");
		var pillarId=getFieldValue("txtKinkPillarid");
		url = "KPIProdflid_input.keyPerInd?type="+type+"&pillarId="+pillarId;
		navigateToNextForm(url ,type + ' Indicator - Functional Location Link ',null,null);
	});
	
	jQuery( "#legendButton" ).click(function() {
		jQuery("#lgnd-panel").slideToggle(200);
		jQuery("#lgnd-panel").css('top','55px');
		jQuery("#lgnd-panel").css('right','7%');
	});
	
	jQuery(document).keydown(function(e) {
	    if (e.keyCode == 27) {
	    	jQuery("#lgnd-panel").hide(0);
	    }    
	    jQuery('#lgnd-panel').focusout(function() { 
	 	});
	});	
	
	jQuery( "#btnFrmView" ).click(function() {
		
		
		if(jQuery('#hdnIdNode').val() != null && jQuery('#hdnIdNode').val() !='')	
		{
			if(jQuery('#hdnIdNode').val() != 'QC001')	
			{			
				processNode("VIEW");
			}
		}
	});
	
	jQuery( "#btnFrmEdit" ).click(function() {	
		
		if(jQuery('#hdnIdNode').val() != null && jQuery('#hdnIdNode').val() !='')
		{
			
			if( jQuery('#hdnIdNode').val() != 'QC001')
			{
				
				processNode("MODIFY");
			}
		}
	});
	
	jQuery( "#btnfindNext" ).click(function() {					
		var searchNode = getSearchString(jQuery("#findNode").combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getValue'));
		if(searchNode != null && searchNode != ' ' && searchNode != '')	{
			jQuery("#flKpiTreeSearch").css('display','block');
			jQuery("#kpiTreeComponent").jstree("search",searchNode);		
		}
		else{
			alert('Select Filter To Search');
		}
		jQuery('#kpiTreeComponent').find('li').each(function(){
			jQuery("#"+this.id + " > a").removeClass("jstree-search");
			//jQuery("#"+this.id + " > a").removeClass("jstree-clicked");
		});
		
	});
	
	jQuery( "#btnfindPnlClear" ).click(function() {	
		jQuery('#findNode').combobox('setValue','cmp');	
		jQuery('#cmbfindTreeNode').combobox('clear');
		reloadCombo('frmFindNode','cmbfindTreeNode','combo_keyPerformParent.keyPerInd');
	});	
	
	/* jQuery("#findNode").combobox({
		onSelect:function(recordid){	
			jQuery("#cmbfindTreeNode").combobox('clear');
			reloadCombo("frmFindNode","cmbfindTreeNode",getComboUrl(recordid.text));
		}
	}); */
	
	function frmFindNodefindNode_onSelect(record){
		jQuery("#cmbfindTreeNode").combobox('clear');
		reloadCombo("frmFindNode","cmbfindTreeNode",getComboUrl(recordid.text));
		
	}
	
	
	/* jQuery("#cmbLocation").combobox({
		onSelect:function(recordid){
			loadTree();		
		}
	}); */
	
	
	
	
	/* jQuery("#txtKinkPillarid").combobox({
		onSelect:function(recordid){
			loadTree();		
		}
	}); */
	
	
	
	jQuery(function () {
		loadTree();					
  	});
});



function loadTree(){
	var pillarId=getFieldValue("txtKinkPillarid");
	var locationId=getFieldValue("cmbLocation");
	var type=getFieldValue("txtFormType");
 	processTree( jQuery("#kpiTreeComponent"),'loadval.keyPerInd?q=2&pillarId='+pillarId+'&locationId='+locationId+'&type='+type,'searchnode.keyPerInd?q=2&pillarId=' +pillarId);
	jQuery("#kpiTreeComponent") .bind("select_node.jstree", function (e, data) {
		var elemType = data.rslt.obj.attr("elementType");					
		if(elemType == 'QC'){
			disableUIButton('btnFrmView');
			disableUIButton('btnFrmEdit');	
		}
		else
		{
			enableUIButton('btnFrmView');
			enableUIButton('btnFrmEdit');						
		}	
 		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
 		jQuery('#hdnLocnId').val(data.rslt.obj.attr("locationid"));
 		jQuery('#hdnElementId').val(data.rslt.obj.attr("elementid"));
 		jQuery('#hdnElemType').val(data.rslt.obj.attr("elementtype"));
 		jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));
 		jQuery('#hdnIdParentNodes').val(data.rslt.obj.attr("parentId"));
 		jQuery("#hdnOriginalid").val(data.rslt.obj.attr("originalid"));
 		jQuery("#hdnMstId").val(data.rslt.obj.attr("id"));
	});
	var fluSearchStr = '';
	jQuery("#kpiTreeComponent").bind("search.jstree", function (e, data) {
						
	if( jQuery(".search_parent_flu-s").length > 0){			         			       
		jQuery("#flTreeLayer").scrollTop(jQuery(".search_parent_flu-s").offset().top -jQuery('#kpiTreeComponent').offset().top);
	}	
 	else{					 	
		if( fluSearchStr != data.rslt.str )
			jQuery('li .search-completed-flu-c').removeClass('search-completed-flu-c');
			
			fluSearchStr = data.rslt.str;
 			jQuery('#kpiTreeComponent').find('li').each(function(){	
				 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-flu-c'))
			 	 {	
					 jQuery("#flTreeLayer").scrollTop(jQuery(this).offset().top -jQuery('#kpiTreeComponent').offset().top);
					 jQuery(this).addClass('search-completed-flu-c');
					 return false;	 	
			 	 }
 			});
		 }
	});

	jQuery("#kpiTreeComponent").bind("loaded.jstree", function (event, data) {	
	});
	
	jQuery("#kpiTreeComponent").bind("open_node.jstree", function (event, data) { 			
	    if((data.inst._get_parent(data.rslt.obj)).length) { 
	        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
	    } 
    });				
}



function loadTree1(Locationparam,pillarparam){
	//var pillarId=getFieldValue("txtKinkPillarid");
	var pillarId= pillarparam == null ? getFieldValue("txtKinkPillarid"):pillarparam;
	//var locationId=getFieldValue("cmbLocation");
	var locationId= Locationparam == null ? getFieldValue("cmbLocation"):Locationparam;
	
	var type=getFieldValue("txtFormType");
 	processTree( jQuery("#kpiTreeComponent"),'loadval.keyPerInd?q=2&pillarId='+pillarId+'&locationId='+locationId+'&type='+type,'searchnode.keyPerInd?q=2&pillarId=' +pillarId);
	jQuery("#kpiTreeComponent") .bind("select_node.jstree", function (e, data) {
		var elemType = data.rslt.obj.attr("elementType");					
		if(elemType == 'QC'){
			disableUIButton('btnFrmView');
			disableUIButton('btnFrmEdit');	
		}
		else
		{
			enableUIButton('btnFrmView');
			enableUIButton('btnFrmEdit');						
		}	
 		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
 		jQuery('#hdnLocnId').val(data.rslt.obj.attr("locationid"));
 		jQuery('#hdnElementId').val(data.rslt.obj.attr("elementid"));
 		jQuery('#hdnElemType').val(data.rslt.obj.attr("elementtype"));
 		jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));
 		jQuery('#hdnIdParentNodes').val(data.rslt.obj.attr("parentId"));
 		jQuery("#hdnOriginalid").val(data.rslt.obj.attr("originalid"));
 		jQuery("#hdnMstId").val(data.rslt.obj.attr("id"));
	});
	var fluSearchStr = '';
	jQuery("#kpiTreeComponent").bind("search.jstree", function (e, data) {
						
	if( jQuery(".search_parent_flu-s").length > 0){			         			       
		jQuery("#flTreeLayer").scrollTop(jQuery(".search_parent_flu-s").offset().top -jQuery('#kpiTreeComponent').offset().top);
	}	
 	else{					 	
		if( fluSearchStr != data.rslt.str )
			jQuery('li .search-completed-flu-c').removeClass('search-completed-flu-c');
			
			fluSearchStr = data.rslt.str;
 			jQuery('#kpiTreeComponent').find('li').each(function(){	
				 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-flu-c'))
			 	 {	
					 jQuery("#flTreeLayer").scrollTop(jQuery(this).offset().top -jQuery('#kpiTreeComponent').offset().top);
					 jQuery(this).addClass('search-completed-flu-c');
					 return false;	 	
			 	 }
 			});
		 }
	});

	jQuery("#kpiTreeComponent").bind("loaded.jstree", function (event, data) {	
	});
	
	jQuery("#kpiTreeComponent").bind("open_node.jstree", function (event, data) { 			
	    if((data.inst._get_parent(data.rslt.obj)).length) { 
	        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
	    } 
    });				
}

function addTrainingAreaSearch(str){
	var searcharr = new Array();
	if(str != null && str.indexOf("-") > 0 ){
		searcharr =str.split('-');	
		//alert(searcharr[searcharr.length-1] );
		jQuery('li a .jstree-search').removeClass('jstree-search');
		jQuery('li a .jstree-clicked').addClass('.jstree-clicked');  
		openNode('kpiTreeComponent' , searcharr[searcharr.length-1].replace('#',''));	
		jQuery(searcharr[searcharr.length-1] ).children('a').addClass('jstree-search');
	}		
}
 
function loadval_searchCallBack(result){
	 jQuery("#flKpiTreeSearch").css('display','none');
	 jQuery('.search_parent_flu-s').removeClass('search_parent_flu-s');	
     if(result != null){   	  
		  var str = result.toString(); 	
		  //alert(str);
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
         jQuery("#kpiTreeComponent " + parentIds).addClass("search_parent_flu-s");       
         addTrainingAreaSearch(str);
     }    
}

function customMenu(node) {	
   var x = jQuery.jstree._focused()._get_node(node).attr('id');
   var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');  
   var id = jQuery.jstree._focused()._get_node(node).attr('id');    
   var selId = jQuery('#hdnIdNode').val();
   if(selId != null && selId != '' && selId != ' ')
   	jQuery("#"+selId+" a").removeClass('jstree-clicked');
    // The default set of all items
   var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');   
   var prevNode = jQuery("#hdnRightClkNode").val(); 	
   if(prevNode != null && prevNode != '' && prevNode != ' ')
	   jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
   jQuery("#hdnOriginalid").val( jQuery.jstree._focused()._get_node(node).attr('originalid'));  
   jQuery("#hdnRightClkNode").val(x);
   jQuery("li[elementid="+elementId+"]  > a").addClass('jstree-clicked');
   if(id=='TR001'){
	   var items = {
		 	addItem: { // The "add" menu item
		 		label: "Add Indicator",
	            action: function () {validateNode(node);}
	        }
	   };
   }
   else{
		var items = {
		 	addItem: { // The "add" menu item
		 		label: "Add Indicator",
	            action: function () {validateNode(node);}
	        },
	        deleteItem: { // The "delete" menu item
		 		label: "Delete Indicator" ,
	            action: function () {processNode("DELETE");}
	        }
	 	};
   }	
   return items;
}

function validateNode(obj){	
	var originalid = jQuery.jstree._focused()._get_node(obj).attr('originalid');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('levelNo');	
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	
	jQuery('#hdnMstId').val(id);
	jQuery("#hdnOriginalid").val(originalid);
	jQuery("#hdnParentsNodes").val(parentNodes);
	jQuery("#hdnLevelNo").val(levelNo);	
	
	var params="";
	var pillarId=getFieldValue("txtKinkPillarid");
	params="q=2&keyId="+originalid+"&pillarId="+pillarId ;
	processAjaxCalls('keyIndLevel_validate.keyPerInd','?' + params,'nodeValidateSuccess','nodeValidateFail');
}

function nodeValidateSuccess(result)
{
	//alert(result.successData.msg);
	var validate="";
	validate=result.successData.msg;
	if (validate.trim()=="Valid") {		
		var originalid = jQuery("#hdnOriginalid").val();	
		var levelNo = jQuery("#hdnLevelNo").val();
		var url="keyInd_input.keyPerInd";	
		var pillarId=getFieldValue("txtKinkPillarid");
		var locationId=getFieldValue("cmbLocation");
		var type=getFieldValue("txtFormType");		
		//alert("originalid:"+originalid);
		//var params="keyInd_input.keyPerInd?mode=INSERT&parentId="+''+"&levelNo="+levelNo+"&pillarId=" +pillarId +"&Loadpopup=Loadpopup";
		var params="keyInd_input.keyPerInd?mode=INSERT&parentId="+originalid+"&levelNo="+levelNo+"&pillarId="+pillarId +"&locationId="+locationId+"&type="+type+"&Loadpopup=Loadpopup";
		jQuery("#hdnmodeval").val("INSERT");
		LoadPopUp("divShowKeyInd", params, true,"65%","99%","1%","15%", "multiSelectOk_Callback","Indicator Entry",true,true);
	}
	else{
		alert("Maximum Indicator Level Should be " + validate);
		return false;
	}	
}

function divShowKeyInd_onClose(){
	//alert("divShowKeyInd_onClose");
	refreshTree();
	return true;
}

function divShowKeyInd_afterClose()
{
	//alert("divShowKeyInd_afterClose");
	var originalid = jQuery("#hdnOriginalid").val();	
	var mode = jQuery("#hdnmodeval").val();	
	//alert("mode:"+mode+",hdnIdParentNodes:"+jQuery("#hdnIdParentNodes").val());
	if(mode=="MODIFY")
		originalid = jQuery("#hdnIdParentNodes").val();
	//alert("originalid"+originalid);
	refreshNode("kpiTreeComponent",originalid);
	
}

function nodeValidateFail(result){
	return false;
}

function processNode(mode)
{	
	var originalid = jQuery('#hdnIdNode').val();
	var parentNodes = jQuery('#hdnElementId').val();	
	var parent = jQuery("#hdnIdParentNodes").val();
	var locationId=getFieldValue("cmbLocation");
	var levelNo = jQuery('#hdnLevelNo').val();
	var pillarId=getFieldValue("txtKinkPillarid");
	var type=getFieldValue("txtFormType");
	var params="";
	if(mode=="DELETE"){
		if(confirm("Do You Want To Delete?") == true){			
			var originalId=jQuery("#hdnOriginalid").val();
			//alert(originalId);
			params="q=2&keyId=" + originalId;
			processAjaxCalls('keyIndllevelDel_validate.keyPerInd','?' + params,'nodeDelValidateSuccess','nodeDelValidateFail');			
		}  
	}
	else{
		if(mode=="MODIFY")			
			params="mode="+ mode + "&parentId=" + parent +"&keyId=" + originalid +"&levelNo=" +levelNo+"&pillarId="+pillarId +"&locationId="+locationId+"&type="+type+"&Loadpopup=Loadpopup" ;
		else if(mode=="VIEW")
			params="mode="+ mode + "&parentId=" + parent +"&keyId=" + originalid +"&levelNo=" +levelNo+"&pillarId="+pillarId +"&locationId="+locationId+"&type="+type+"&Loadpopup=" ;
		
		jQuery("#hdnmodeval").val(mode);
		LoadPopUp("divShowKeyInd", "keyInd_input.keyPerInd?" +params, true,"60%","80%","15%","15%", "multiSelectOk_Callback","Indicator Entry",false,true);
		//LoadPopUp("divShowKeyInd", "keyInd_input.keyPerInd?" + params, true,"770px","450px","0px","20%", "multiSelectOk_Callback","Indicator Entry",false,true);
	}
}

function loadPopUp_ErrorCallback(args){}
function multiSelectOk_Callback(args){	
}

function MultiSelectCancel_CallBack(id)
{	
	refreshTree();
	closePopUpDialoge("divShowKeyInd");		
}
function frmFindNode_deleteSuccessCallback(result)
{
	var elemId=jQuery("#hdnParentsNodes").val();
	refreshNode("kpiTreeComponent",elemId);		
	alert(result.successData.msg);
}

function nodeDelFailure(result){	
	alert(result.tpmException);
}

function nodeDelValidateSuccess(result){
	//alert(result.successData.msg);
	var validate="";
	validate=result.successData.msg;
	if (validate.trim()=="Valid") {
		var originalid = jQuery("#hdnOriginalid").val();	
		var params="";	
		params="mode=DELETE&keyId="+originalid ;
		deleteRecord("frmFindNode",'keyInd_delete.keyPerInd?'+ params);
	}
	else
	{
		alert("Indicator Already Referred ");
		return false;
	}	
}

function nodeDelValidateFail(result){
	return false;
}

function onSelectChange(){		
   	var dropdown = document.getElementById("findNode");
   	var index = dropdown.selectedIndex;
  	var ddVal = dropdown.options[index].value;
  	var ddText = dropdown.options[index].text;
	if(ddVal != 0) {
        output = "You Selected " + ddText;
   	}
}

function getComboUrl(cmbTxt)
{
	var cmbUrl = null;
	var pillarId=getFieldValue("txtKinkPillarid");
	cmbUrl = "combo_keyPerformParent.keyPerInd?pillarId="+pillarId;	
	return cmbUrl;	
}

function getSearchString(cmbTxt,dispField,cmbId)
{	
	var nodearr = new Array();
	nodeArr = dispField.split('-');	
	var toSearch = dispField.replace('-'+nodeArr[nodeArr.length-1],'');	
	//alert("nodeArr:" + nodeArr[0].trim());	
	var mchToSearch = null;
	var cmbUrl = null;
	cmbUrl =toSearch.trim();	
	return cmbUrl;
}
function frmFindNodecmbfindTreeNode_onLoadSuccess(){}

function multiSelectOk_Callback(args){}

function MultiSelectCancel_CallBack(id)
{
	jQuery('#multiselectPopUpId').dialog('close');
}

function openNode(treeId,nodeId)
{		
	if(jQuery("#"+treeId).jstree("is_open", jQuery('#'+nodeId)) == false)
	{
		jQuery("#"+treeId).jstree("open_node",jQuery('#'+nodeId));
	}	
}

function nodeDelSuccess(result)
{
	refreshTree();
	alert(result.successData.msg);
}

function refreshTree()
{
	var tree = jQuery.jstree._reference("#kpiTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}

function refTree(node)
{
	//alert(node);
	var tree = jQuery.jstree._reference("#kpiTreeComponent");
	var currentNode = tree._get_node(node, true);	
	var parentNode = tree._get_parent(currentNode);
	tree.refresh();
}

 function closeFlDialog(dlgId)
 {
	 jQuery( '#'+dlgId ).hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery( '#'+dlgId ).removeClass('custom-popup');
 }
</script>
<!--	<input type="text" style="display:none;" id="txtKinkPillarid" name="txtKinkPillarid"  value="${requestScope.pillarid}"/>-->
	<input type="text" style="display:none;" id="txtformFld" name="txtformFld"/>
	<input type="text" style="display:none;" id="hdnElementId" name="hdnElementId"/>
	<input type="text" style="display:none;" id="hdnParentsNodes" name="hdnParentsNodes"/>
	<input type="text" style="display:none;" id="hdnIdNode" name="hdnIdNode"/>
	<input type="text" style="display:none;" id="hdnIdParentNodes" name="hdnIdParentNodes"/>
	<input type="text" style="display:none;" id="hdnLevelNo" name="hdnLevelNo"/>
	<input type="text" style="display:none;" id="hdnPopupUrl" name="hdnPopupUrl"/>
	<input type="text" style="display:none;" id="hdnOriginalid" name="hdnOriginalid"/>
	<input type="text" style="display:none;" id="hdnElemType" name="hdnElemType"/>
	<input type="hidden" id="txtFormType" name="txtFormType" value="${requestScope.type}"/>
 	<input type="hidden" id="hdnRightClkNode" />
 	<div  id="wrapper" style="width:90%">
 	<div  class="easyui-paddingbfpx">
		 <form id="frmFindNode" name="frmFindNode">
		 	<div>
		 		<table>
		 			<tr>
		 				<td colspan="3">
		 					<div style="padding-top: 10px;"><label>Location</label></div>
							<div class="easyui-paddingbfpx">
<!--								 <input class="easyui-text"  id="txtLocation" name="txtLocation"	readonly="readonly" style="width: 300px; text-align: left;" value="${requestScope.location}" />-->
								 <input class="easyui-combo" id="cmbLocation" name="cmbLocation" style="width: 300px;"	value="${requestScope.location}" />
							</div>
		 				</td>
		 				<td colspan="6">
		 					<div style="padding-top: 10px;"><label>Pillar</label></div>
							<div class="easyui-paddingbfpx">
								 <input class="easyui-combo" id="txtKinkPillarid" name="txtKinkPillarid" style="width: 300px;"	value="${requestScope.pillarid}" />
							</div>
		 				</td>
		 			</tr>
		 			<tr>
		 				<td>
		 					<label>Find</label>
		 				</td>
		 				<td>
		 					<select  id="findNode" class="easyui-combobox" name="findNode" style="width:120px;">				
								<option value="QC">Indicator</option>	
							</select> 
		 				</td>
		 				<td>
		 					<input id="cmbfindTreeNode" clear="false" name="cmbfindTreeNode" class="easyui-combobox" style="width:200px;"/>  
			 	
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" id="btnfindNext" value="Find Next" style="height:21;"/>
	 			
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" id="btnfindPnlClear" value="Clear" style="height:21;"/>
	      		
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button"  id="btnFrmView" value="View" style="height:21;"/>			 
	          	
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" id="btnFrmEdit" value="Edit" style="height:21;"/>
		 					<input type="hidden" id="hdnMstId" />
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" style="display:none" id="btnPstAsm" value="Paste"/>    
		
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" id="legendButton" value="Legend" style="margin-left:300px;margin-left:280px\9;display: none;"/> 
		 				</td>
		 				<td>
		 					<input type="button" class="easyui-button" id="btnMapFun" value="Map Function Location" style="margin-left:300px;margin-left:320px\9;"/> 
		 				</td>
		 			</tr>
		 		</table>
		 	</div>
		  </form>
		</div>
	<div style="margin-left:1%">
		<div id="flTreeLayer" style="float:left;width:500px;height:365px;height
		:330px\9;overflow:auto;" class="sub-cntborder">
			<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
			<div id="flKpiTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
			<div id="kpiTreeComponent" class="demo" style="width: 50%;"></div>
		</div>		
	</div>	 	 
</div>	
	
<div style="padding-top:4%;">
		
</div>
<div id="lgnd-panel">
	<ul>
		<li><a href="#"><img src="images/FnLocn/location.jpg"/><span>Indicator</span></a></li>
	</ul>
</div>
<input type="hidden" id="hdnmodeval" value="" />

