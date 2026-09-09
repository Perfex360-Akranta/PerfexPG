<!-- Created By :Siddharth.A -->
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){
	initialiseForm('frmEqpgrpTree');
	jQuery('#submitForm').val('frmEqpgrpTree'); 
	var hiddenUrl = jQuery('#hiddenUrl').val();
	
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	jQuery("#EqgTreeSearch").css('display','block');
	processTree( jQuery("#eqpGrpLinkTree"),'eqpgroupLink_tree.eqg','searchTree.eqg');
	setTimeout(function() {jQuery("#EqgTreeSearch").css('display','none');jQuery("#eqpGrpLinkTree").jstree("search",jQuery('#hdneqpSubgrpId').val());},1250);	
	fillComboBox("frmEqpgrpTree","cmbNodeval","assembly.eqg" );	
});

jQuery("#eqpGrpLinkTree") .bind("select_node.jstree", function (e, data) {
	jQuery('#hdnNodeID').val(data.rslt.obj.attr("id"));
});

var fluSearchStr = '';
jQuery("#eqpGrpLinkTree").bind("search.jstree", function (e, data) {
    if( jQuery(".search_parent_bph-s").length > 0){
			jQuery("#eqgTreelayer").scrollTop(jQuery(".search_parent_bph-s").offset().top -jQuery('#eqpGrpLinkTree').offset().top);
     }	
	 else{
			if( fluSearchStr != data.rslt.str )
			jQuery('li .search-completed-bph-c').removeClass('search-completed-bph-c');
		
			fluSearchStr = data.rslt.str;
	
 			jQuery('#eqpGrpLinkTree').find('li').each(function(){	
				 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-bph-c'))
			 	 {	
					 jQuery("#eqgTreelayer").scrollTop(jQuery(this).offset().top -jQuery('#flTreeComponent').offset().top);
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

				
		reloadCombo("frmEqpgrpTree","cmbNodeval",getComboUrl(recordid.text));
	}
});
jQuery( "#btnBPNext" ).click(function() {	
	if(jQuery("#cmbNodeval").combobox('getValue').trim()!=''&& jQuery("#cmbNodeval").combobox('getValue').trim()!="null")
	{	
		jQuery("#EqgTreeSearch").css('display','block');
		var searchNode = getSearchString(jQuery("#cmbFindnode").combobox('getText'),jQuery('#cmbNodeval').combobox('getText'));
	
		jQuery("#eqpGrpLinkTree").jstree("search",searchNode);	
	}	
});
jQuery( "#btnBPClear" ).click(function() {	
	jQuery('#cmbFindnode').combobox('setValue','Assembly');	
	jQuery('#cmbNodeval').combobox('clear');
	reloadCombo('frmEqpgrpTree','cmbNodeval','assembly.eqg');
});
jQuery( "#btnEqpGrouoLinkLegend" ).click(function() {
	jQuery("#lgnd-panel").slideToggle(200);
	jQuery("#lgnd-panel").css('top','12%');
});

function customMenu(node) 
{

	var nodeId = jQuery.jstree._focused()._get_node(node).attr('id');
	var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');
    var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');
	var id = jQuery.jstree._focused()._get_node(node).attr('id');	
	
	 var selId = jQuery('#hdnNodeID').val();

	 if(selId != null && selId != '' && selId != ' ')
	   	jQuery("#"+selId+" a").removeClass('jstree-clicked');

	 var prevNode = jQuery("#hdnRightClkNodeID").val(); 	
	 if(prevNode != null && prevNode != '' && prevNode != ' ')
		jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
		jQuery("#hdnRightClkNodeID").val(nodeId);
	    jQuery("li[elementid="+elementId+"]  > a").addClass('jstree-clicked');
	
	if(elemType == 'FCT')
	{
	 	var items = {
	 			 addItem: {
			            label: "Add Main Group",
			            action: function () {addNode(node);}
			        }
	 	};
	}
	if(elemType == 'MGP')
	{
	 	var items = {
	 			 addItem: {
			            label: "Add Sub Group",
			            action: function () {addNode(node);}
			        }
	 	};
	}

	if(elemType == 'SGP')
	{
	 	var items = {
	 			 addItem: {
			            label: "Add Assembly",
			            action: function () {addNode(node);}
			        }
	 	};
	}
	
	if(elemType == 'A')
	{
	 	var items = {
	 			 addItem: {
			            label: "Add Child",
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
	jQuery('#hdntreeId').val("eqpGrpLinkTree");
	if(formField == 'ASM')
	{	//jQuery("#txtformFld").val('P');
		jQuery('#hdnchildType').val('-');
		childPopup();
	}
	if(formField == 'PHM')
	{
		jQuery('#hdnchildType').val('');
		jQuery("#txtformFld").val('C');	
		
		funcnLocnPopUp("addPhenCause_input.eqg",elemId+ '&formField='+formField,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Cause");	
	}
	if(formField == "FCT")
	{
		navigateToNextForm("equipmentGroup_input.eqg?eqgFctId="+id);
	}
	if(formField == "ESG")
	{
		var maingrpId=elemId.substring(elemId.indexOf('-')+1);
		//navigateToPrevForm();
		navigateToNextForm("equipmentGroup_input.eqg?eqgFctId="+parentNodes+"&eqgmMainGrpId="+maingrpId);
	//	LoadPopUp("", "equipmentGroup_input.eqg?eqgFctId="+parentNodes+"&eqgmMainGrpId="+maingrpId, true );
	}

	if(formField == 'EQG')
	{	
		jQuery("#txtformFld").val('A');	
		funcnLocnPopUp("addPhenCause_input.eqg",elemId+ '&formField='+formField,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Assembly");	
	}
}
function editNode(obj)
{
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	jQuery("#newMstFrm").slideToggle(200);
	var url = getMstFrmUrl(id,parentNodes,frmMode.edit);
	url += '&closeOnSave=true';	
	//&assmId='+parentNodes.substring(parentNodes.lastIndexOf('-')+1)+'
	loadMasterForm(url,frmMode.edit);
}
function delNode(obj)
{
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	jQuery("#newMstFrm").slideToggle(200);
	var url = getMstFrmUrl(id,parentNodes,frmMode.edit);
	url += '&closeOnSave=true';	
	loadMasterForm(url,frmMode.edit);
}
function openDialog(elemType,dlgId,w,h,dispCode,elemId)
{
	
}
function multiSelectOk_Callback(args)
{
	//alert("Oked");
	//jQuery('#hdnchildType').val('')	
}
function MultiSelectCancel_CallBack(id)
{//alert("closedd");
//	jQuery('#hdnchildType').val('')
	jQuery('#multiselectPopUpId').dialog('close');
}
function frmEqpgrpTree_beforeLoadCurrentForm(result)
{
	//alert(jQuery('#hiddenUrl').val());
	flenableLayout();		
	if(jQuery("#newMstFrm").is(":visible") ==  true)
	{ 
		jQuery("#newMstFrm").hide(0);		
		if(result != null)
		{
			//alert(jQuery('#txtformFld').val());
			if(jQuery('#txtformFld').val() =='A' || jQuery('#txtformFld').val()=='P' || jQuery('#txtformFld').val()=='C' ||jQuery('#txtformFld').val()=='EQG' ||jQuery('#txtformFld').val() =='S')
			{
				openWithPop();
			}
			else
				{
				  if(jQuery("#eqpGrpLinkTree").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
				  {
				  	if(jQuery('#loadFormMode').val() == 'CREATE')
					  	jQuery("#eqpGrpLinkTree").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
				  	else
				  		 refreshTree();
				  }
				  else
				  	  refreshTree();
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
		selId = selId+ '&formField=ASM&childType=PHM';
	else if(ff == 'C')
		selId = selId+ '&formField=PHM';
	else if(ff == 'A')
		selId = selId+ '&formField=EQG';
	else if(ff == 'S')
		selId = selId+ '&formField=ASM&childType=SPR';
	
	if(selId == null || selId == '' || selId == ' ')
		var y = null;
	else
		 funcnLocnPopUp("addPhenCause_input.eqg",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");

}

function childPopup()
{
	jQuery('#dlgAddChild').show();
	jQuery('#dlgAddChild').dialog({
		autoOpen: false,
		show: "blind",
		hide: "explode",
		title: "Child Type",
		width:"300",
		height:"180",
		modal: true
	});
}

jQuery("#cmbasmChild").combobox({
	onSelect:function(recordid){
	
		jQuery('#hdnasmChildVal').val(recordid.id);
	}
});
jQuery('#btndlgOk').click(function()
{
	var elemId= jQuery('#txtelemId').val();
	var title="";
	if(jQuery('#hdnasmChildVal').val().trim()=="-")
	{
		alert("Invalid Selection");
	}
	else
	{
		if(jQuery('#hdnasmChildVal').val() == "S")
		{
			jQuery('#hdnchildType').val('SPR');
			jQuery("#txtformFld").val('S');
			title="Spares";
		}
		else if(jQuery('#hdnasmChildVal').val() == "P")
		{
			jQuery('#hdnchildType').val('PHM');
			jQuery("#txtformFld").val('P');
			title="Phenomena";
		}		
		
		funcnLocnPopUp("addPhenCause_input.eqg",elemId+ '&formField=ASM&childType='+jQuery('#hdnchildType').val(),"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack",title);	
		jQuery('#dlgAddChild').dialog("close");	
	}
 });

jQuery('#btndlgCancel').click(function(){
	jQuery('#dlgAddChild').dialog("close");
});


function refreshTree()
{
	var tree = jQuery.jstree._reference("#eqpGrpLinkTree");
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
	//if(cmbTxt == 'Company')
	//	cmbUrl = "companyCombo.commonFilter";
	if(cmbTxt == 'Assembly')
		cmbUrl = "assembly.eqg";	
	if(cmbTxt == 'Spare')
		cmbUrl = "spareCombo.eqg";
	if(cmbTxt == 'Phenomena')
		cmbUrl = "phenomena.commonFilter";
	if(cmbTxt == 'Cause')
		cmbUrl = "cause.commonFilter";
	
	return cmbUrl;	

}
function eqpgroupLink_tree_searchCallBack(result){
	jQuery("#EqgTreeSearch").css('display','none');
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
  	    jQuery("#eqpGrpLinkTree " + parentIds).addClass("search_parent_bph-s");
        
    }    
}

function getSearchString(cmbTxt,dispField)
{
	var nodearr = new Array();
	nodeArr = dispField.split('-');	
	var cmbUrl = null;
	if(cmbTxt == 'Company')
		cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Unit')
	{
		if(nodeArr.length>2)
			cmbUrl = nodeArr[0].trim()+'-'+nodeArr[1].trim();
		else
			cmbUrl = nodeArr[0].trim();
	}
	if(cmbTxt == 'Assembly')
		cmbUrl = dispField;	
	if(cmbTxt == 'Spare')
		cmbUrl = nodeArr[1];
	if(cmbTxt == 'Phenomena')
		cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Cause')
		cmbUrl = nodeArr[0].trim();
	
	return cmbUrl;	

}


</script>
<form id="frmEqpgrpTree" name="frmEqpgrpTree">
<div style="margin:2%;">
	<table>
		<tr>
			<td>
				 <label>Find</label>
			</td>
			<td>
				<select  id="cmbFindnode" class="easyui-combobox" name="cmbFindnode" style="width:120px;">
					<!--  <option value="unt">${requestScope.FctConst}</option>-->
					<option value="assm">Assembly</option>
					<option value="spr">Spare</option>
					<option value="phen">Phenomena</option>
					<option value="cas">Cause</option>
				</select> 
			</td>
			<td>
				<input id="cmbNodeval" name="cmbNodeval" class="easyui-combobox" style="width:200px;"/>  
			</td>
			<td>
				<input type="button" class="easyui-button" id="btnBPNext" value="Find Next"/>
			</td>
			<td>
				<input type="button" class="easyui-button" id="btnBPClear" value="Clear"/>
			
			</td>
			<td>
				<input type="button" class="easyui-button" style="width:55px;margin-left:568px;margin-left:510px\9;" id="btnEqpGrouoLinkLegend" value="Legend"/>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				 <div style="float:left;overflow:auto;width:500px;height:370px; margin-top:1%;" id="eqgTreelayer" class="sub-cntborder">
					<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
					<div id="EqgTreeSearch" style="float:right;display:none;"><img id="eqgtreeSearchLoading" src="images/FnLocn/searching.gif"/></div>
					<div id="eqpGrpLinkTree" class="demo" style="width: 50%;"></div>		
				</div>	
			</td>
		</tr>
	</table>
</div>		
 <div id="lgnd-panel">
		<ul>
			<li><a href="#"><img src="images/FnLocn/factory.jpg"/><span>${requestScope.FctConst}</span></a></li>
			<li><a href="#"><img src="images/FnLocn/section.jpg"/><span>MainGroup</span></a></li>
			<li><a href="#"><img src="images/FnLocn/machine.jpg"/><span>SubGroup</span></a></li>
			<li><a href="#"><img src="images/FnLocn/assembly.jpg"/><span>Assembly</span></a></li>
			<li><a href="#"><img src="images/FnLocn/spare.png"/><span>Spare</span></a></li>
			<li><a href="#"><img src="images/FnLocn/phen.png"/><span>Phenomena</span></a></li>
			<li><a href="#"><img src="images/FnLocn/cause.png"/><span>Cause</span></a></li>
		</ul>
 </div>
 
  <div id="dlgAddChild" style="display:none" title="Select Child">
 	 <div class="easyui-paddingbfpx" id="forassmChild" style="padding-left:10px;padding-top:10px;"> 
 	  <label>Select Child</label>
 				<select id="cmbasmChild"  name="cmbasmChild" class="easyui-combobox"  style="width:170px;"  >
						<option value="-"> ---</option>						
						<option value="S"> Spare</option>
						<option value="P"> Phenomena</option>
				</select> 
	    </div>
	     <div class="easyui-paddingbfpx" style="padding-left:70px;padding-top:30px;">  
	  
	    		<input type="button" class="easyui-button" id="btndlgOk" value="OK"/>
		      	<input type="button" class="easyui-button" id="btndlgCancel" value="Cancel"/>
		</div>
  
  </div>
<input type="hidden"  id="hdnParentsNodes" name="hdnParentsNodes"/>
<input type="hidden" id="hdnMstId" />
<input type="hidden" id="txtformFld" name="txtformFld"/>
<input type="hidden"  id="txtelemId" name="txtelemId"/>
<input type="hidden"  id="hdnElemType" name="hdnElemType"/>
<input type="hidden" id="hdnchildType" name="hdnchildType"/>
<input type="hidden"  id="hdntreeId" name="hdntreeId"/>
<input type="hidden" id="hdneqpSubgrpId" name="hdneqpSubgrpId" value="${requestScope.eqpSubgrpId}"/>
<input type="hidden" id="hdnasmChildVal"/>
<input type="hidden" id="hdneqgpKeyid" name="hdneqgpKeyid" value="${requestScope.eqgpKeyid}"/>
<input type="hidden"  id="hdnNodeID" name="hdnNodeID"/>
<input type="hidden"  id="hdnRightClkNodeID" name="hdnRightClkNodeID"/>
</form>