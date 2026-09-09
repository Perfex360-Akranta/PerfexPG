<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){
	initialiseForm('phnCauseTree');
	jQuery('#submitForm').val('phnCauseTree'); 
	var hiddenUrl = jQuery('#hiddenUrl').val();
	
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	
	
	processTree( jQuery("#phenCauseLinkTree"),'PhenCause_tree.pcl','searchTree.pcl');
	fillComboBox("phnCauseTree","cmbNodeval","companyCombo.commonFilter" );	
});
jQuery("#phenCauseLinkTree") .bind("select_node.jstree", function (e, data) {
	jQuery('#hdnNodeID').val(data.rslt.obj.attr("id"));
});

var fluSearchStr = '';
jQuery("#phenCauseLinkTree").bind("search.jstree", function (e, data) {
	
    if( jQuery(".search_parent_bph-s").length > 0){
			jQuery("#bdphnTreelayer").scrollTop(jQuery(".search_parent_bph-s").offset().top -jQuery('#phenCauseLinkTree').offset().top);
     }	
	 else{
			if( fluSearchStr != data.rslt.str )
			jQuery('li .search-completed-bph-c').removeClass('search-completed-bph-c');
		
			fluSearchStr = data.rslt.str;
	
 			jQuery('#phenCauseLinkTree').find('li').each(function(){	
				 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-bph-c'))
			 	 {	
					 jQuery("#bdphnTreelayer").scrollTop(jQuery(this).offset().top -jQuery('#flTreeComponent').offset().top);
					 jQuery(this).addClass('search-completed-bph-c');
					 return false;	 	
			 	 }
 			 });
	 }
});
jQuery("#cmbFindnode").combobox({
	onSelect:function(recordid){
		
		if(jQuery("#cmbNodeval").combobox('getValue')=='' || jQuery("#cmbNodeval").combobox('getValue') == null || jQuery("#cmbNodeval").combobox('getValue') == ' ')
				var c;
		else
			jQuery("#cmbNodeval").combobox('setValue','');
				
		reloadCombo("phnCauseTree","cmbNodeval",getComboUrl(recordid.text));
	}
});
jQuery( "#btnBPNext" ).click(function() {			
	
	var searchNode = getSearchString(jQuery("#cmbFindnode").combobox('getText'),jQuery('#cmbNodeval').combobox('getText'));
	jQuery("#PCTreeSearch").css('display','block');
	jQuery("#phenCauseLinkTree").jstree("search",searchNode);		
});
jQuery( "#btnBPClear" ).click(function() {	
	jQuery('#cmbFindnode').combobox('setValue','cmp');	
	jQuery('#cmbNodeval').combobox('clear');
	reloadCombo('phnCauseTree','cmbNodeval','companyCombo.commonFilter');
});
jQuery( "#btnPhenCauseLinkLegend" ).click(function() {
	jQuery("#lgnd-panel").slideToggle(200);
	jQuery("#lgnd-panel").css('top','36px');
	jQuery("#lgnd-panel").css('top','3px\9');
});

function customMenu(node) {

	 var nodeId = jQuery.jstree._focused()._get_node(node).attr('id');
	 var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');	
	 var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');
	 var selId = jQuery('#hdnNodeID').val();

	 if(selId != null && selId != '' && selId != ' ')
	   	jQuery("#"+selId+" a").removeClass('jstree-clicked');

	 var prevNode = jQuery("#hdnRightClkNodeID").val(); 	
	 if(prevNode != null && prevNode != '' && prevNode != ' ')
		jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
		jQuery("#hdnRightClkNodeID").val(nodeId);
	    jQuery("li[elementid="+elementId+"]  > a").addClass('jstree-clicked');
		
	
	if(elemType == 'A')
	{
	 	var items = {
	 			 addItem: {
			            label: "Add Phenomena",
			            action: function () {addNode(node);}
			        }
	 	};
	}
	if(elemType == 'PHN')
	{
	 	var items = {
	 			    addItem: {
			            label: "Add Cause",
			            action: function () {addNode(node);}
			        },
				 	editItem: {
			            label: "Edit Phenomena",
			            action: function () {editNode(node);}
			        },
			        delItem: {
			            label: "Delete Phenomena",
			            action: function () {delNode(node);}
			        }
	 	};
	}
	if(elemType == 'CAS')
	{
	 	var items = {
	 			
	 			editItem: {
		            label: "Edit Cause",
		            action: function () {editNode(node);}
		        },
		        delItem: {
		            label: "Delete Cause",
		            action: function () {delNode(node);}
		        }
	 	};
	}
	return items;
}
function addNode(obj)
{
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	var formField = id.substring(0,3);		
	jQuery('#hdnParentsNodes').val(parentNodes);	
	jQuery('#hdnMstId').val(id);
	jQuery('#txtelemId').val(elemId);
	jQuery('#hdnElemType').val(elemId);	
	jQuery('#hdntreeId').val('phenCauseLinkTree');
	if(formField == 'ASM')
		jQuery("#txtformFld").val('P');	
	if(formField == 'PHM')
		jQuery("#txtformFld").val('C');	
	funcnLocnPopUp("addPhenCause_input.pcl",elemId+ '&formField='+formField,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");	
}
function editNode(obj)
{
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	jQuery("#newMstFrm").slideToggle(200);
	var url = getMstFrmUrl(id,parentNodes,frmMode.edit);
	url += '&closeOnSave=true';	
	loadMasterForm(url,frmMode.edit,'','Phenomena Cause Tree');
}
function delNode(obj)
{
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	jQuery("#newMstFrm").slideToggle(200);
	var url = getMstFrmUrl(id,parentNodes,frmMode.edit);
	url += '&closeOnSave=true';	
	loadMasterForm(url,frmMode.edit,'','Phenomena Cause Tree');
}
function openDialog(elemType,dlgId,w,h,dispCode,elemId)
{
	
}
function multiSelectOk_Callback(args)
{
	
}
function MultiSelectCancel_CallBack(id)
{
	jQuery('#multiselectPopUpId').dialog('close');
}
function phnCauseTree_beforeLoadCurrentForm(result)
{
	//alert(jQuery('#hiddenUrl').val());
	flenableLayout();	
	if(jQuery("#newMstFrm").is(":visible") ==  true)
	{ 
		jQuery("#newMstFrm").hide(0);		
		if(result != null)
		{
			//alert(jQuery('#txtformFld').val());
			if(jQuery('#txtformFld').val() =='A' || jQuery('#txtformFld').val()=='P' || jQuery('#txtformFld').val()=='C')
			{
				openWithPop();
			}
			else
				{
				  if(jQuery("#phenCauseLinkTree").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
				  {
				  	if(jQuery('#loadFormMode').val() == 'CREATE')
					  	jQuery("#phenCauseLinkTree").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
				  	else
				  		 refreshTree();
				  }
				  else
					  refreshNode("phenCauseLinkTree",jQuery('#hdnMstId').val());
				}
			//refreshTree();
		}
		else
		{
			if(jQuery('#loadFormMode').val() == 'CREATE')
			{
				openWithPop();
			}
		}
	
		return 'OK';
	}
	else		
		return true;
	//jQuery("#flTreeComponent").jstree("search",result.successData.description);					
}
function openWithPop()
{
	var selId = jQuery('#txtelemId').val();
	var ff = jQuery('#txtformFld').val();
	if(ff == '-')
		selId = selId;
	else if(ff == 'P')
		selId = selId+ '&formField=ASM';
	else if(ff == 'C')
		selId = selId+ '&formField=PHM';
	
	if(selId == null || selId == '' || selId == ' ')
		var y = null;
	else
		 funcnLocnPopUp("addPhenCause_input.pcl",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
}
function refreshTree()
{
	var tree = jQuery.jstree._reference("#phenCauseLinkTree");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}
function getMstFrmUrl(mstKey,parentDatas,formMode)
{
	
	var mstUrl = null;	
	var dataString = '?q=2&'+frmMode.frmMode+'='+formMode+'&'+frmMode.keyId+'='+mstKey.replace('_','/');
	
	mstKey = mstKey.substring(0,3);
	
	if(formMode == frmMode.edit)
		dataString += '&'+frmMode.lockFields+'='+addToUrl(mstKey);
	
	if(mstKey == 'CMP')
		mstUrl = "company_input.comp"+dataString;
	if(mstKey == 'LCN')
		mstUrl = "location_input.locn"+dataString;
	if(mstKey == 'FCT')
		mstUrl = "factory_input.fact?q=2"+dataString;
	if(mstKey == 'LIN')
		mstUrl = "section_input.sect"+dataString;
	if(mstKey == 'CEL')
		mstUrl = "cell_input.cell"+dataString;
	if(mstKey == 'MCH')
		 mstUrl = "equipment_input.eqp"+dataString;
	if(mstKey == 'ASM')
		mstUrl = "assembly_input.asb"+dataString;
	if(mstKey == 'SPR')
		mstUrl = "SparesMaster_input.sprmst"+dataString;
	if(mstKey == 'PHM')
		 mstUrl = "PhenomenaMst_link.gnms?q=2";
	if(mstKey == 'CSM')
		mstUrl = "CauseMst_link.gnms?q=2";
	
	return mstUrl;	
}

function addToUrl(mstKey)
{
	var dataString=null;

	if(mstKey == 'CMP')
		dataString = "KEYID";
	if(mstKey == 'LCN')
		dataString = "KEYID,COMPANY";
	if(mstKey == 'FCT')
		dataString = "KEYID,COMPANY,LOCATION";
	if(mstKey == 'LIN')
		dataString = "KEYID,COMPANY,FACTORY";
	if(mstKey == 'CEL')
		dataString = "KEYID,COMPANY,SECTION,FACTORY";
	if(mstKey == 'MCH')
		dataString = "KEYID,SECTION,FACTORY,CELL";
	if(mstKey == 'ASM')
		dataString = "KEYID";
	if(mstKey == 'SPR')
		dataString = "KEYID,FACTORY";
	if(mstKey == 'PHM')
		dataString = "KEYID,ASSEMBLY";
	if(mstKey == 'CSM')
		dataString = "KEYID,PHENOMENA";
	return dataString;	
}
function getComboUrl(cmbTxt)
{
	var cmbUrl = null;
	if(cmbTxt == 'Company')
		cmbUrl = "companyCombo.commonFilter";
	if(cmbTxt == 'Location')
		cmbUrl = "location.funlocn";
	if(cmbTxt == 'Unit')
		cmbUrl = "factroyCombo.commonFilter";
	if(cmbTxt == 'Section')
		cmbUrl = "sectionCombo.commonFilter";
	if(cmbTxt == 'Line')
		cmbUrl = "cellCombo.commonFilter";
	if(cmbTxt == 'Equipment')
		cmbUrl = "machineCombo.commonFilter";
	if(cmbTxt == 'Assembly')
		cmbUrl = "assembly.funlocn";	
	if(cmbTxt == 'Spare')
		cmbUrl = "spareCombo.funlocn";
	if(cmbTxt == 'Phenomena')
		cmbUrl = "phenomena.commonFilter";
	if(cmbTxt == 'Cause')
		cmbUrl = "cause.commonFilter";
	
	return cmbUrl;	

}
function PhenCause_tree_searchCallBack(result){
	jQuery("#PCTreeSearch").css('display','none');
	jQuery('.search_parent_bph-s').removeClass('search_parent_bph-s');
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
  	    jQuery("#phenCauseLinkTree " + parentIds).addClass("search_parent_bph-s");
        
    }    
}

function getSearchString(cmbTxt,dispField)
{
	var nodearr = new Array();
	nodeArr = dispField.split('-');	
	var cmbUrl = null;
	if(cmbTxt == 'Company')
		cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Location')
		cmbUrl = nodeArr[1];
	if(cmbTxt == 'Unit')
	{
		if(nodeArr.length>2)
			cmbUrl = nodeArr[0].trim()+'-'+nodeArr[1].trim();
		else
			cmbUrl = nodeArr[0].trim();
	}
	if(cmbTxt == 'Section')
	{
		if(nodeArr.length>2)
			cmbUrl = nodeArr[0].trim()+'-'+nodeArr[1].trim();
		else
			cmbUrl = nodeArr[0].trim();
	}
		
	if(cmbTxt == 'Line')
		cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Equipment')
	{	
		if(nodeArr.length == 2)
			cmbUrl = nodeArr[nodeArr.length-1];
		else
			cmbUrl = nodeArr[nodeArr.length-2] + '-'+nodeArr[nodeArr.length-1];
	}
	if(cmbTxt == 'Assembly')
		cmbUrl = dispField;	
	if(cmbTxt == 'Sub Assembly1')
		cmbUrl = nodeArr[1];
	if(cmbTxt == 'Sub Assembly2')
		cmbUrl = nodeArr[1];
	if(cmbTxt == 'Spare')
		cmbUrl = nodeArr[1];
	if(cmbTxt == 'Instrument')
		cmbUrl =  nodeArr[2] + '-'+nodeArr[3];
	if(cmbTxt == 'Sub Cell')
		cmbUrl =  nodeArr[1];
	if(cmbTxt == 'Phenomena')
		cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Cause')
		cmbUrl = nodeArr[0].trim();
	
	return cmbUrl;	

}
</script>
<form id="phnCauseTree" name="phnCauseTree">
<div style="margin-top:5px;margin-left:3px;">
	 <div style="float:left;width:50%;height:97%;height:460px\9;overflow:auto;" id="bdphnTreelayer" class="sub-cntborder">
		<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
		<div id="PCTreeSearch" style="float:right;display:none;"><img id="pctreeSearchLoading" src="images/FnLocn/searching.gif"/></div>
		<div id="phenCauseLinkTree" class="demo" style="width: 50%;"></div>		
	</div>	

	<div style="position:absolute;right:92px;right:60px\9;">
		<input type="button" class="easyui-button" id="btnPhenCauseLinkLegend" value="Legend"/>
	</div>	
</div>

		
 <div id="lgnd-panel">
		<ul>
			<li><a href="#"><img src="images/FnLocn/company.jpg"/><span>Company</span></a></li>
			<li><a href="#"><img src="images/FnLocn/location.jpg"/><span>Location</span></a></li>
			<li><a href="#"><img src="images/FnLocn/factory.jpg"/><span>Unit</span></a></li>
			<li><a href="#"><img src="images/FnLocn/unit.jpg"/><span>Section</span></a></li>
			<li><a href="#"><img src="images/FnLocn/section.jpg"/><span>Line</span></a></li>
			<li><a href="#"><img src="images/FnLocn/machine.jpg"/><span>Equipment</span></a></li>
			<li><a href="#"><img src="images/FnLocn/assembly.jpg"/><span>Assembly</span></a></li>
			<li><a href="#"><img src="images/FnLocn/spare.png"/><span>Spare</span></a></li>
			<li><a href="#"><img src="images/FnLocn/phen.png"/><span>Phenomena</span></a></li>
			<li><a href="#"><img src="images/FnLocn/cause.png"/><span>Cause</span></a></li>
		</ul>
 </div>
<input type="hidden"  id="hdnParentsNodes" name="hdnParentsNodes"/>
<input type="hidden" id="hdnMstId" />
<input type="hidden" id="txtformFld" name="txtformFld"/>
<input type="hidden"  id="txtelemId" name="txtelemId"/>
<input type="hidden"  id="hdnElemType" name="hdnElemType"/>
<input type="hidden"  id="hdntreeId" name="hdntreeId"/>
<input type="hidden"  id="hdnNodeID" name="hdnNodeID"/>
<input type="hidden"  id="hdnRightClkNodeID" name="hdnRightClkNodeID"/>

</form>