<!--Created By Suresh.K on Oct 10-->
<!--<script type="text/javascript" src="js/jsTree/jquery.jstree2.js"></script>-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<!-- <script src="js/ajaxupload.3.5.js" type="text/javascript"></script>-->
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	//alert("Functional");
	jQuery('#submitForm').val('frmFuncnLocn'); 
	var hiddenUrl = jQuery('#hiddenUrl').val();
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	//imageUpload(jQuery( "#dlgAddImg" ),'ImageUpload.commonFilter','dlgAddImg','previewField');
//	initialiseForm('frmAddChild');
	//initialiseForm('frmCutEqp');
	initialiseForm('frmCopyAsm');
	initialiseForm('frmFindNode');	
	fillComboBox("frmFindNode","cmbfindTreeNode","companyCombo.commonFilter" );
	comboSel();
	
	jQuery("#searchFuncLocn").hide();
	
	jQuery( "#legendButton" ).click(function() {
		jQuery("#lgnd-panel").slideToggle(200);
	});
	jQuery(document).keydown(function(e) {
	    if (e.keyCode == 27) {
	    	jQuery("#lgnd-panel").hide(0);
	    }    
	    jQuery('#lgnd-panel').focusout(function() { 
	 	});
	});
	jQuery( "#dlgSve" ).click(function() {
		var imageResp = jQuery('#previewField').attr('src');
	 	processAjaxCalls('save_blob.funlocn','?q=2&fileName='+imageResp+'&locnId='+jQuery('#hdnImgLocnId').val(),'saveBlobSuccess','saveBlobFailure');
	 });	

	jQuery( "#btnfindNext" ).click(function() {			
		jQuery('#hdnIdNode').val('');
		var searchNode = getSearchString(jQuery("#findNode").combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getValue'));
		
		if(searchNode != null && searchNode != ' ' && searchNode != '')
		{
			jQuery("#flTreeSearch").css('display','block');
			jQuery("#flTreeComponent").jstree("search",searchNode);		
		}
		else
		{
			alert('Select Filter To Search');
		}
	});
	jQuery( "#btnfindPnlClear" ).click(function() {	
		jQuery('#findNode').combobox('setValue','cmp');	
		jQuery('#cmbfindTreeNode').combobox('clear');
		reloadCombo('frmFindNode','cmbfindTreeNode','companyCombo.commonFilter');
	});
	jQuery( "#btnFrmView" ).click(function() {
		//alert(jQuery('#hdnIdNode').val());
		if(jQuery('#hdnIdNode').val() != null && jQuery('#hdnIdNode').val() !='')	
		{
			if(jQuery('#hdnIdNode').val() != 'FL001')	
			{
				jQuery("#newMstFrm").slideToggle(200);
				var url = getMstFrmUrl(jQuery('#hdnIdNode').val(),jQuery('#hdnIdParentNodes').val(),frmMode.view);	
					//url += '&backToCalledForm=functionalLocn';	
				loadMasterForm(url,frmMode.view,'','Functional Location');
			}
		}
		else
		{
			var searchVal = jQuery('#cmbfindTreeNode').combobox('getValue');
			//alert(searchVal);
			jQuery("#newMstFrm").slideToggle(200);
			var url = getMstFrmUrl(searchVal,'',frmMode.view);
			url += '&closeOnSave=true';	
			//alert(url);
			loadMasterForm(url,frmMode.view,'','Functional Location');
		}
	});
	jQuery( "#btnFrmEdit" ).click(function() {			
		if(jQuery('#hdnIdNode').val() != null && jQuery('#hdnIdNode').val() !='')
		{
			if( jQuery('#hdnIdNode').val() != 'FL001')
			{
				if(jQuery('#hdnIdNode').val().substring(0,3) != 'CMP')	
				{	
					jQuery("#newMstFrm").slideToggle(200);
					var url = getMstFrmUrl(jQuery('#hdnIdNode').val(),jQuery('#hdnIdParentNodes').val(),frmMode.edit);
					url += '&closeOnSave=true';	
					//alert(url);
					loadMasterForm(url,frmMode.edit,'','Functional Location');
				}
			}
		}
		else
		{
			var searchVal = jQuery('#cmbfindTreeNode').combobox('getValue');
			if(searchVal.substring(0,3) != 'CMP' && searchVal.substring(0,3) != 'LCN' && searchVal.substring(0,3) != 'FCT')
			{
				jQuery("#newMstFrm").slideToggle(200);
				var url = getMstFrmUrl(searchVal,'',frmMode.edit);
				url += '&closeOnSave=true';	
				loadMasterForm(url,frmMode.edit,'','Functional Location');
			}
		}
	});
	  //jQuery( "#dlgAddImg" ).live("click", function() {
	jQuery(document).on("click", "#dlgAddImg", function() {
		 //"#dlgAddImg"
		
		//closePopUpDialoge('dlgAddImage');
 	 });
	jQuery( "#dlgDel").click(function() {		
		processAjaxCalls("del_img.funlocn","?q=2&nodeId="+jQuery('#hdnImgLocnId').val(),"delImgSuccess","delImgError");	
	});
	//jQuery( "#btnWarningOk" ).live("click", function() {
	jQuery(document).on("click", "#btnWarningOk", function() {
		 var inactiveDate = jQuery('#dteInactive').datebox('getValue');
		 var warning = jQuery('#hdnwarningFields').val();
		
		 var warningArr = warning.split('::');
		 var url = '?q=2';
		 if(warningArr[0] != null && warningArr[0] != ''&& warningArr[0] != ' ')
			 url += '&elemID='+warningArr[0];
		 if(warningArr[1] != null && warningArr[1] != ''&& warningArr[1] != ' ')
			 url += '&originalId='+warningArr[1];
		 if(warningArr[2] != null && warningArr[2] != ''&& warningArr[2] != ' ')
			 url += '&type='+warningArr[2];
		 if(inactiveDate != null && inactiveDate != ''&& inactiveDate != ' ')
			 url += '&inactiveDate='+inactiveDate;
		
		processAjaxCalls('node_del.funlocn',url,'nodeDelSuccess','nodeDelFailure');
		closeFlDialog('dlgWarning');
	});
	//jQuery( "#btndlgOk" ).live("click", function() {
	jQuery(document).on("click", "#btndlgOk", function() {
		

		var formField = null;
		var elemId = jQuery("#txtelemId").val();
		var type = jQuery('#hdnElemTypeForCombo').val();
		/*if(jQuery("#eqpChild").combobox('getValue') == '-'&& jQuery("#assmChild").combobox('getValue') == '-')
		{
			formField = jQuery("#sectChild").combobox('getValue');
		}
		if(jQuery("#sectChild").combobox('getValue') == '-'&& jQuery("#assmChild").combobox('getValue') == '-')
			formField = jQuery("#eqpChild").combobox('getValue');
		if(jQuery("#sectChild").combobox('getValue') == '-'&& jQuery("#eqpChild").combobox('getValue') == '-')
			formField = jQuery("#assmChild").combobox('getValue');		*/
			if(jQuery("#cboeqpChild").val() == '-'&& jQuery("#cboassmChild").val() == '-')
			{
				formField = jQuery("#cbosectChild").val();
							
			}
			if(jQuery("#cbosectChild").val() == '-'&& jQuery("#cboassmChild").val() == '-')
			{
				formField = jQuery("#cboeqpChild").val();
				
			}
			if(jQuery("#cbosectChild").val() == '-'&& jQuery("#cboeqpChild").val() == '-')
			{
				formField = jQuery("#cboassmChild").val();	
			}
			
		if(formField == '-')
		{		
			alert('Select Value to ADD');
		}	
		else
		{
			elemId = elemId+ '&formField='+formField;			
			jQuery("#txtformFld").val(formField);
			var title= jQuery('#hdnTitleToDisplay').val();

			if(type == 'C')
				title = 'JH-'+title;
			if(type == 'M')
				title = 'Equipment-'+title;
			if(type == 'A')
				title = 'Station-'+title;
			if(type == 'SPR')
				title = 'Spare-'+title;
			funcnLocnPopUp("funcnLocn_input.funlocn",elemId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack",title,true);
			//closeFlDialog('dlgAddChild');
			closePopUpDialoge('dlgAddChild');
				
		}		
	});
	
	//jQuery( "#dlgCancel" ).live("click", function() {
	jQuery(document).on("click", "#dlgCancel", function() {
		//closeFlDialog('dlgAddChild');
		closePopUpDialoge('dlgAddChild');
	});
	
	jQuery("#findNode").combobox({
		onSelect:function(recordid){
			/*if(jQuery("#cmbfindTreeNode").combobox('getValue')=='' || jQuery("#cmbfindTreeNode").combobox('getValue') == null || jQuery("#cmbfindTreeNode").combobox('getValue') == ' ')
					var c;
			else
				jQuery("#cmbfindTreeNode").combobox('setValue','');*/
			jQuery("#cmbfindTreeNode").combobox('clear');
				//alert(getComboUrl(recordid.text));
			//jQuery("#cmbfindTreeNode").combobox({url:getComboUrl(recordid.text)
					//});
			reloadCombo("frmFindNode","cmbfindTreeNode",getComboUrl(recordid.text));
		}
	});

/*	jQuery( "#dlgPaste" ).click(function() {
		
		if(jQuery("#cmbdialogsubUnit").combobox('getValue') ==null || jQuery("#cmbdialogsubUnit").combobox('getValue') ==''||jQuery("#cmbdialogsubUnit").combobox('getValue')==' ')
		{
			alert('Select DMT');
		}
		else if(jQuery("#cmbdialogsect").combobox('getValue') ==null || jQuery("#cmbdialogsect").combobox('getValue') ==''||jQuery("#cmbdialogsect").combobox('getValue')==' ')
		{
			alert('Select JH');
		}
		else
		{
				var dataString="?q=2&unitId="+jQuery("#cmbdialogUnit").combobox('getValue')+"&subUnitId="+jQuery("#cmbdialogsubUnit").combobox('getValue')+"&sectId="+jQuery("#cmbdialogsect").combobox('getValue');
					dataString+= "&elemId="+jQuery("#txtDlgElemId").val()+"&dispCode="+jQuery("#txtDlgDispCode").val();
				//processAjaxCalls("paste_eqp.funlocn",dataString,"pasteEqpSuccess","pasteEqpRecallError");
				//alert(getSearchString('Line',jQuery("#cmbdialogsect").combobox('getText'))+'||'+jQuery("#cmbdialogsect").combobox('getValue'));
				//alert(dataString);
				jQuery("#flTreeComponent").jstree("search",getSearchString('Line',jQuery("#cmbdialogsect").combobox('getText'))+'||'+jQuery("#cmbdialogsect").combobox('getValue'));		
				processAjaxCalls("get_bd.funlocn",dataString,"getBdSuccess","getBdError");
				
		}
	});*/
/*	jQuery( "#dlgCncl" ).live("click", function() {
		closeFlDialog('dlgCutEqp');
	});	*/
	//jQuery( "#dlgCls" ).live("click", function() {
	jQuery(document).on("click", "#dlgCls", function() {
		//closeFlDialog('dlgAddImage');
		closePopUpDialoge('dlgAddImage');
	});
	//jQuery( "#imgCloseCopyAsm" ).live("click", function() {
	jQuery(document).on("click", "#imgCloseCopyAsm", function() {
		closeFlDialog('dlgCopyAsm');
	});
	/*
	jQuery( "#imgCloseCutEqp" ).live("click", function() {
		closeFlDialog('dlgCutEqp');
	});*/
	//jQuery( "#imgCloseAddImage" ).live("click", function() {
	jQuery(document).on("click", "#imgCloseAddImage", function() {	
		//closeFlDialog('dlgAddImage');
		closePopUpDialoge('dlgAddImage');
	});
	//jQuery( "#imgCloseAddChild" ).live("click", function() {
	jQuery(document).on("click", "#imgCloseAddChild", function() {
		//closeFlDialog('dlgAddChild');
		closePopUpDialoge('dlgAddChild');
	});
	//jQuery( "#imgCloseWarning" ).live("click", function() {
	jQuery(document).on("click", "#imgCloseWarning", function() {
		closeFlDialog('dlgWarning');
	});
	//jQuery( "#btnWarningCancel" ).live("click", function() {
	jQuery(document).on("click", "#btnWarningCancel", function() {
		closeFlDialog('dlgWarning');
	});
	//jQuery( "#dlgCopyAsmCncl" ).live("click", function() {
	jQuery(document).on("click", "#dlgCopyAsmCncl", function() {
		closeFlDialog('dlgCopyAsm');
	});
	
	jQuery( "#btnPstAsm" ).click(function() {		
		var selNode = jQuery('#elemIdToPasteAsm').val().split(':');
		var dataString="?q=2";
		if(selNode[0] == 'M')
		{			
			dataString+="&eqpId="+selNode[1];
			dataString+= "&elemId="+jQuery("#CopyAsmElemId").val()+"&dispCode="+jQuery("#CopyAsmDispCode").val();
			
			processAjaxCalls("copy_node.funlocn",dataString,"copySuccess","copyErr");
		}
		else
		{
				alert('Select Equipment to Paste');
		}
	});
	jQuery( "#dlgCopyAsmPaste" ).click(function() {
		if(jQuery("#cmbCopyEquip").combobox('getValue') ==null || jQuery("#cmbCopyEquip").combobox('getValue') ==''||jQuery("#cmbCopyEquip").combobox('getValue')==' ')
		{
			alert('Select Equipment');
		}
		else
		{
			var dataString="?q=2&eqpId="+jQuery("#cmbCopyEquip").combobox('getValue');
				dataString += "&pasteToEqpId="+jQuery("#cmbCopyUnit").combobox('getValue')+'-'+jQuery("#cmbCopysubUnit").combobox('getValue')+'-'+jQuery("#cmbCopysect").combobox('getValue');
				dataString+= "&elemId="+jQuery("#CopyAsmElemId").val()+"&dispCode="+jQuery("#CopyAsmDispCode").val();			
				jQuery("#flTreeComponent").jstree("search",getSearchString('Equipment',jQuery("#cmbCopyEquip").combobox('getText'))+'||'+jQuery("#cmbCopyEquip").combobox('getValue'));
				processAjaxCalls("copy_node.funlocn",dataString,"copySuccess","copyErr");
		}
	});
	 jQuery(function () {
		 	processTree( jQuery("#flTreeComponent"),'loadval.funlocn','searchnode.funlocn');
			jQuery("#flTreeComponent") .bind("select_node.jstree", function (e, data) {
					var elemType = data.rslt.obj.attr("elementType");
					
					if(elemType == 'CMP' || elemType == 'LCN' || elemType == 'F' || elemType == 'FL')
					{
						if(elemType == 'FL')
							disableUIButton('btnFrmView');							
						else
							enableUIButton('btnFrmView');
						
						disableUIButton('btnFrmEdit');	
						   
					}
					else
					{
						enableUIButton('btnFrmView');
						enableUIButton('btnFrmEdit');						
					}
						
					processAjaxCalls("get_image.funlocn","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImageSuccess","getImgErr");					
			 		jQuery('#elemIdToPasteAsm').val(data.rslt.obj.attr("elementType")+':'+data.rslt.obj.attr("elementId"));
			 		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
			 		jQuery('#hdnIdParentNodes').val(data.rslt.obj.attr("parentId"));			 	
			 });
			 var fluSearchStr = '';
			jQuery("#flTreeComponent").bind("search.jstree", function (e, data) {

				
				         //  alert("Found " + data.rslt.nodes.length + " nodes matching '" + data.rslt.str + "'.");
				         //alert('Inside Search '+jQuery(".search_parent_flu-s").length );
		         if( jQuery(".search_parent_flu-s").length > 0){
			         //alert('Top'+jQuery(".search_parent_flu-s").offset().top);
			         // alert('Top'+jQuery("#flTreeComponent").offset().top);
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
				 //alert('s');		
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
       //  alert(parentIds);
         
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
	
	//jQuery("#"+x+" a").removeClass('jstree-clicked');
  	//jQuery("#"+x+" a").css('color','#0000ff');  
    	 
   var items = null; 
   processAjaxCalls("get_image.funlocn","?q=2&nodeId="+jQuery.jstree._focused()._get_node(node).attr("id"),"getImgSuccess","getImgErr");

    if(elemType == 'CMP' )
    {
    	 items = {
				    deleteItem: { // The "delete" menu item
			            label: "Add/Edit/Delete Image",
			            action: function () {imgNode(node);}
			        }
		 };
    }
    /**SBU and PBU added By Manikandan**/
	if(elemType == 'F'|| elemType == 'LCN' ||elemType == 'L' ||elemType == 'C' ||elemType == 'SPR'||elemType == 'SBU'||elemType == 'PBU' )
	{
		 var lblName = getTitle(elemType);		    
		items = {
			        renameItem: { // The "rename" menu item
			            label: lblName,
			            action: function () {if(lblName == 'Delete Spare')deletechecked(node);else addNode(node);}
			        },
			        deleteItem: { // The "delete" menu item
			            label: "Add/Edit/Delete Image",
			            action: function () {imgNode(node);}
			        }
		 };
	}

	if(elemType == 'M')
		{
		 items = {
				 	cutItem: {
				 		 label: "Cut Equipment",
				         action: function () {cutNode(node);}
					 	},
					delItem: {
						label: "Delete Equipment",
				        action: function () {deletechecked(node);}
					 	},
			        renameItem: { // The "rename" menu item
			            label: "Add Child",
			            action: function () {addNode(node);}
			        },
			        deleteItem: { // The "delete" menu item
			            label: "Add/Edit/Delete Image",
			            action: function () {imgNode(node);}
			        }
		 };
		}

	if(elemType == 'A')
	{
	  items = {
			 	copyItem: {
			 		 label: "Copy Assembly",
			         action: function () {copyNode(node);}
				 	},
				delItem: {
					label: "Delete Assembly",
			        action: function () {deletechecked(node);}
				 	},
		        renameItem: { // The "rename" menu item
		            label: "Add Child",
		            action: function () {addNode(node);}
		        },
		        deleteItem: { // The "delete" menu item
		            label: "Add/Edit/Delete Image",
		            action: function () {imgNode(node);}
		        }
	 };
	}
 return items;
}

function addNode(obj)
{
	var elemType = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	var title = jQuery.jstree._focused()._get_node(obj).attr('displaycode');
	jQuery('#hdnParentsNodes').val(parentNodes);
	jQuery('#txtelemId').val(elemId);
	jQuery('#hdnElemType').val(elemId);
	jQuery('#hdnMstId').val(id);
	jQuery('#hdnTitleToDisplay').val(title);
	jQuery('#hdnElemTypeForCombo').val(elemType);

	if(elemType == 'CMP' || elemType == 'LCN' ||elemType == 'F' ||elemType == 'L'||elemType == 'SBU'||elemType == 'PBU'  )
	{
		if(elemType == 'F')
			title = 'Unit-'+title;
		else if(elemType == 'L')
			title = 'DMT-'+title;
		else if(elemType == 'SBU')
			title = 'SBU-'+title;
		else if(elemType == 'PBU')
			title = 'PBU-'+title;
		 funcnLocnPopUp("funcnLocn_input.funlocn",elemId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack",title,true);
	}	
	else if(elemType == 'C')
	{
		openDialog(elemType, "#dlgAddChild",300,180);
		jQuery("#forsectChild").css('display','block');		
		jQuery("#foreqpChild").css('display','none');		
		jQuery("#forassmChild").css('display','none');
		jQuery("#cboeqpChild").val('-');	
		jQuery("#cboassmChild").val('-');				
	}
	else if(elemType == 'M')
	{
		openDialog(elemType, "#dlgAddChild",300,180);
		
		alert("Open Dialog");
		jQuery("#forassmChild").css('display','none');		
		jQuery("#foreqpChild").css('display','block');
		jQuery("#forsectChild").css('display','none');
		jQuery("#cbosectChild").val('-');	
		jQuery("#cboassmChild").val('-');	
	}
	else if(elemType == 'A')
	{
		openDialog(elemType, "#dlgAddChild",300,180);		
		jQuery("#foreqpChild").css('display','none');
		jQuery("#forsectChild").css('display','none');
		jQuery("#forassmChild").css('display','block');
		jQuery("#cboeqpChild").val('-');	
		jQuery("#cbosectChild").val('-');
	}

	
}

function imgNode(obj)
{
	var elemType = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var id=jQuery.jstree._focused()._get_node(obj).attr('id');
	jQuery('#hdnImgLocnId').val('');
	jQuery('#hdnImgLocnId').val(id);
	
	var dataString = '?q=n&elemType = '+elemType;
	if(elemType == 'CMP' || elemType == 'LCN' ||elemType == 'SBU' ||elemType == 'PBU' ||elemType == 'L' ||elemType == 'C' ||elemType == 'M' ||elemType == 'A' ||elemType == 'SPR' )
		{
     	openDialog(elemType, "#dlgAddImage",600,480,dispCode,elemId);//imageee
		}
}

function cutNode(obj)
{
	var elemType = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');	
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	//alert("Element Id:" +elemId);
	var nodeId = jQuery.jstree._focused()._get_node(obj).attr('id');
	//jQuery('#txtDlgElemId').val(elemId);	
	//jQuery('#txtDlgDispCode').val(dispCode);
	//displayText('cmbdialogUnit','');
	//displayText('cmbdialogsubUnit','');
	//displayText('cmbdialogsect','');
	if(elemType == 'M')
	{
		//alert("Ajax call 1 ");
		//processAjaxCalls('cuteqp_check.funlocn','?q=2&elemID='+elemId+'&nodeId='+nodeId,'cutEqpSuccess','cutEqpFailure');	
		processAjaxCalls('cuteqp_check.funlocn','?q=2&elemID='+elemId+'&nodeId='+nodeId+'&dispCode='+dispCode,'cutEqpSuccess','cutEqpFailure');
		//openDialog(elemType, "#dlgCutEqp",300,200,dispCode,elemId);		
	/*	fillComboBox("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter" );
		fillComboBox("frmCutEqp","cmbdialogsect","cellCombo.commonFilter" );*/
	}
}/*
function copyNode(obj)
{
	var elemType = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');	
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var idArr  = elemId.split('-');
	var id= "";
	for(var i=0;i<idArr.length;i++)
	{
		if(idArr[i].substring(0,3) == 'MCH')
			id = idArr[i];
	}	
	jQuery('#CopyAsmElemId').val(elemId);
	jQuery('#CopyAsmDispCode').val(dispCode);
	displayText('cmbCopyUnit','');
	displayText('cmbCopysubUnit','');
	displayText('cmbCopysect','');
	displayText('cmbCopyEquip','');
	
	//alert('Select Machine and Click Paste');
	//jQuery('#btnPstAsm').css('display','block');
	openDialog(elemType, "#dlgCopyAsm",300,350,dispCode);
	fillComboBox("frmCopyAsm","cmbCopyUnit","factroyCombo.commonFilter" );		
	fillComboBox("frmCopyAsm","cmbCopysubUnit","sectionCombo.commonFilter" );
	fillComboBox("frmCopyAsm","cmbCopysect","cellCombo.commonFilter" );
	fillComboBox("frmCopyAsm","cmbCopyEquip","machineCombo.commonFilter?q=2&machineNotToShown="+id);
	//fillComboBox("frmCopyAsm","cmbCopyEquip","machine.funlocn?parent="+condSql );
}*/
function deletechecked(obj)
{
	var elemId = jQuery.jstree._focused()._get_node(obj).attr('elementId');	
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');	
	var type = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var url = 'node_del.funlocn';
	if(type == 'M')
		url = 'mch_del.funlocn';
    if(confirm("Do You Want To Delete?") == true)
	{
		processAjaxCalls(url,'?q=2&elemID='+elemId+'&originalId='+id+'&type='+type,'nodeDelSuccess','nodeDelFailure');
	}  
}  
function openDialog(elemType,dlgId,w,h,dispCode,elemId)
{	//alert("Calling open Dialog");
	//alert("dispCode:" +dispCode);
	//alert("elemId:" +elemId);

	 var dlgTitle = 'Select Child';
	 jQuery( "#dlgSaveButton" ).hide();
	 jQuery( "#dlgDelButton" ).hide();
	 
	 if(jQuery('#hdnBlobimage').val() !='')		
		 jQuery( "#dlgDelButton" ).show();
	 if(elemType == 'CMP')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'COMPANY ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);				
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'LOCATION';
			}
		}
	 if(elemType == 'LCN')
		{
			if(dlgId == '#dlgAddImage')
			{
				
				dlgTitle = 'LOCATION ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'SBU';
			}
		}
	 if(elemType == 'F')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'Unit ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);						
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'SUB UNIT';
			}
		}
	 if(elemType == 'PBU')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'DMT ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'DMT';
			}
		}
	 if(elemType == 'C')
	 {
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'JH ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
	 }
	if(elemType == 'M')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'Equipment ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
		if(dlgId == '#dlgCutEqp')
		{
			dlgTitle = 'Select Destination DMT to Paste';
			//alert('Select Destination DMT to Paste');
		}
	}
	if(elemType == 'A')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'ASSEMBLY ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
		if(dlgId == '#dlgCopyAsm')
		{
			dlgTitle = 'Select Destination Equipment to Paste';
		}
	}
	if(elemType == 'SPR')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'SPARE ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
	}
	if(elemType == 'W')
	{
		dlgTitle = 'Inactivate Date';		
		formatDateBox('dteInactive','dd-MMM-yyyy');	
		fillWithCurrentDate('dteInactive');
		
	}
	 var dataStr = "&elemType="+elemType+"&dlgId="+dlgId.substring(1)+"&w="+w+"&h="+h+"&dispCode="+escape(dispCode)+"&elemId="+escape(elemId);
	// alert("Datastr:" +dataStr);
	// alert("dataStr   "+dataStr );
	//alert("&elemType="+elemType+"&dlgId="+dlgId.substring(1)+"&w="+w+"&h="+h+"&dispCode="+escape(dispCode)+"&elemId="+escape(elemId));
	if(dlgId == '#dlgAddImage')
		LoadPopUp("dlgAddImage", "load_childImage.funlocn?"+dataStr, true,"47%","77%","10%","35%", "dlgAddImage_successCallBack",dlgTitle);
	else if(dlgId == '#dlgAddChild')	
		LoadPopUp("dlgAddChild", "load_childPop.funlocn?"+dataStr, true,"19%","30%","30%","40%", "dlgAddChild_successCallBack",dlgTitle);
	else if(dlgId == '#dlgCutEqp')	
		LoadPopUp("dlgCutEqp", "load_equipCut.funlocn?"+dataStr, true,"25%","38%","30%","50%", " ",dlgTitle);
	
	

}
function dlgAddImage_onClose(){ 
	return true;
}function dlgAddChild_onClose(){ 
	return true;
}
function getTitle(elemType)
{ 
	  if(elemType == 'CMP')
			lblName = '-';
	  else if(elemType == 'LCN')
			lblName = 'Add SBU ';
	  else if(elemType == 'F')
			lblName = 'Add DMT';
	  else if(elemType == 'L')
			lblName = 'Add JH';
	  else if(elemType == 'SBU')
			lblName = 'Add PBU';
	  else if(elemType == 'PBU')
			lblName = 'Add DMT';
	  else if(elemType == 'C')
			lblName = 'Add Child';
	  else if(elemType == 'SPR')
			lblName = 'Delete Spare';

	return lblName;
}

function dlgAddImgOnComplete(response)
{	 
	 jQuery( "#dlgSaveButton" ).show();	 
}


function onSelectChange(){
		
	        var dropdown = document.getElementById("findNode");
	        var index = dropdown.selectedIndex;
	        var ddVal = dropdown.options[index].value;
		    var ddText = dropdown.options[index].text;
			if(ddVal != 0) {
		         output = "You Selected " + ddText;
		    }
		         //alert(output);
}

function getComboUrl(cmbTxt)
{
	var cmbUrl = null;
	if(cmbTxt == 'Company')
		cmbUrl = "companyCombo.commonFilter";
	else if(cmbTxt == 'Location')
		cmbUrl = "location.funlocn";
	else if(cmbTxt == 'SBU')
		cmbUrl = "sbuCombo.commonFilter";
	else if(cmbTxt == 'PBU')
		cmbUrl = "pbuCombo.commonFilter";
	else if(cmbTxt == 'Unit')
		cmbUrl = "factroyCombo.commonFilter";
	else if(cmbTxt == 'Section' || cmbTxt == 'DMT')
		cmbUrl = "sectionCombo.commonFilter";
	else if(cmbTxt == 'JH' || cmbTxt == 'Cell')
		cmbUrl = "cellCombo.commonFilter";
	else if(cmbTxt == 'Equipment')
		cmbUrl = "machineCombo.commonFilter";
	else if(cmbTxt == 'Assembly')
		cmbUrl = "assembly.funlocn";	
	else if(cmbTxt == 'Sub Assembly1')
		cmbUrl = "subassemblyCombo.funlocn";
	else if(cmbTxt == 'Sub Assembly2')
		cmbUrl = "subassemblyCombo.funlocn";
	else if(cmbTxt == 'Spare')
		cmbUrl = "spareCombo.funlocn";
	else if(cmbTxt == 'Instrument')
		cmbUrl = "machineCombo.commonFilter";
	else if(cmbTxt == 'Sub Cell')
		cmbUrl = "subcell.funlocn";
	//alert(cmbUrl);
	return cmbUrl;	

}

function getSearchString(cmbTxt,dispField,cmbId)
{
	
	var nodearr = new Array();
	nodeArr = dispField.split('-');	
	
	var toSearch = dispField.replace('-'+nodeArr[nodeArr.length-1],'');
	var mchToSearch = null;
	
	//alert(nodeArr[nodeArr.length-1]);
	var cmbUrl = null;
	//if(cmbTxt == 'Company')
		//cmbUrl = toSearch;//cmbUrl = nodeArr[0].trim();
	if(cmbTxt == 'Location')
		cmbUrl = nodeArr[1].trim();
	else if(cmbTxt == 'Equipment')
	{
		/*if(nodeArr.length == 2)
			cmbUrl = nodeArr[nodeArr.length-1];
		else
			cmbUrl = nodeArr[nodeArr.length-2] + '-'+nodeArr[nodeArr.length-1];*/
		cmbUrl = dispField.trim();
	}
	else if(cmbTxt == 'DMT' || cmbTxt == 'Section' || cmbTxt == 'Line')
	{
		cmbUrl = nodeArr[0].trim();
	}
	else if(cmbTxt == 'Spare')
	{
		//cmbUrl = nodeArr[1];
		//if(cmbId != null && cmbId != '' && cmbId != ' ')
	      cmbUrl = dispField.trim();
		//cmbUrl = nodeArr[1].trim()+' ('+nodeArr[0].trim()+')';
	}
	else
		cmbUrl =toSearch.trim();
	/*if(cmbTxt == 'Factory')
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
	if(cmbTxt == 'Machine')
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
		cmbUrl =  nodeArr[1];*/
	//alert(cmbUrl);
	return cmbUrl;	

}
function comboSel()
{
	
	jQuery("#cbosectChild").change(function() {
		jQuery("#cboeqpChild").val('-');	
		jQuery("#cboassmChild").val('-');	
	});
	jQuery("#cboeqpChild").change(function() {
		jQuery("#cbosectChild").val('-');	
		jQuery("#cboassmChild").val('-');	
	});
	jQuery("#cboassmChild").change(function() {		
		jQuery("#cboeqpChild").val('-');	
		jQuery("#cbosectChild").val('-');	
	});
		
	/*jQuery("#cbosectChild").combobox({
		onSelect:function(recordid){			
			jQuery("#cboeqpChild").combobox('setValue','-');	
			jQuery("#cboassmChild").combobox('setValue','-');
		}
		});
	jQuery("#cboeqpChild").combobox({
		onSelect:function(recordid){
			alert(recordid);				
			jQuery("#cbosectChild").combobox('setValue','-');	
			jQuery("#cboassmChild").combobox('setValue','-');
		}
		});
	jQuery("#cboassmChild").combobox({
		onSelect:function(recordid){			
			jQuery("#cboeqpChild").combobox('setValue','-');	
			jQuery("#cbosectChild").combobox('setValue','-');
		}
		});*/
}

function frmFindNodecmbfindTreeNode_onLoadSuccess()
{		
}
function frmCutEqpcmbdialogsubUnit_onLoadSuccess()
{		
}
function frmCutEqpcmbdialogsect_onLoadSuccess()
{		
}

function multiSelectOk_Callback(args)
{
	
}
function MultiSelectCancel_CallBack(id)
{
	jQuery('#multiselectPopUpId').dialog('close');
}
function  frmCopyAsmcmbCopyUnit_onSelect(record)
{
	reloadCombo("frmCopyAsm","cmbCopysubUnit","sectionCombo.commonFilter?factId="+ record.id );	
}
function  frmCopyAsmcmbCopysubUnit_onSelect(record)
{
	reloadCombo("frmCopyAsm","cmbCopysect","cellCombo.commonFilter?sectId="+ record.id );	
}
function  frmCopyAsmcmbCopysect_onSelect(record)
{
	reloadCombo("frmCopyAsm","cmbCopyEquip","machineCombo.commonFilter?cellId="+ record.id );	
}
function  frmCopyAsmcmbCopyEquip_onSelect(record)
{
	fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbCopysect","cmbCopysubUnit","cmbCopyUnit");//,"cmbFunctLocComp","cmbFunctLocLocn");
	//reloadCombo("frmCopyAsm","cmbCopyEquip","machineCombo.commonFilter?cellId="+ record.id );	
}

/*
function  frmCutEqpcmbdialogUnit_onSelect(record)
{
	reloadCombo("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter?factId="+ record.id );		
}
function  frmCutEqpcmbdialogsubUnit_onSelect(record)
{
	reloadCombo("frmCutEqp","cmbdialogsect","cellCombo.commonFilter?sectId="+ record.id );		
}
function  frmCutEqpcmbdialogsect_onSelect(record)
{
	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbdialogsubUnit","cmbdialogUnit");//,"cmbFunctLocComp","cmbFunctLocLocn");
}*/


function cutEqpSuccess(result)
{
	//alert("cutEqpSuccess 2");
	var idArr  = result.elementId.split('-');
	//alert("result.elementId:" +result.elementId);
	//alert("result.nodeId:" +result.nodeId);
	jQuery("#hdnnodeId").val(result.nodeId);
	var id= "";
	for(var i=0;i<idArr.length;i++)
	{
		if(idArr[i].substring(0,3) == 'CEL')
			id = idArr[i];
	}	
//	alert("Id: " +id);
	if(result.successData.msg == "No-bd")
	{
		//alert("");
		//openDialog('M', "#dlgCutEqp",300,250,jQuery('#txtDlgDispCode').val());	
		openDialog('M', "#dlgCutEqp",300,200,result.dispCode,result.elementId);
		//fillComboBox("frmCutEqp","cmbdialogUnit","factroyCombo.commonFilter" );		
		//fillComboBox("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter" );
		//fillComboBox("frmCutEqp","cmbdialogsect","cellCombo.commonFilter?lineNotToShown="+id );
	}
	else
	{
		if(confirm(result.successData.msg) == true)
		{
			openDialog('M', "#dlgCutEqp",300,250,result.dispCode);	
			//fillComboBox("frmCutEqp","cmbdialogUnit","factroyCombo.commonFilter" );	
			//fillComboBox("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter" );
			//fillComboBox("frmCutEqp","cmbdialogsect","cellCombo.commonFilter?lineNotToShown="+id );
		}
		else
			alert("Equipment Cannot be Moved");
	}
}
/*
function getBdSuccess(result)
{
	if(confirm(result.successData.msg) == true)
	{
		if(confirm(result.successData.confirmmsg) == true)
		{
			var dataString="?q=2&unitId="+jQuery("#cmbdialogUnit").combobox('getValue')+"&subUnitId="+jQuery("#cmbdialogsubUnit").combobox('getValue')+"&sectId="+jQuery("#cmbdialogsect").combobox('getValue');
				dataString+= "&elemId="+jQuery("#txtDlgElemId").val()+"&dispCode="+jQuery("#txtDlgDispCode").val();
				//alert(dataString);
			processAjaxCalls("paste_eqp.funlocn",dataString,"pasteEqpSuccess","pasteEqpRecallError");	
		}
	}
}*//*
function pasteEqpSuccess(result)
{	
	refreshTree();		
	alert(result.successMsg);
	openNode('flTreeComponent',result.pasteTo);
	jQuery("#flTreeComponent").jstree("search",getSearchString('Equipment',result.pasted));
	closeFlDialog('dlgCutEqp');*/
	//jQuery('#dlgCutEqp').dialog('close');	
	//alert(result.pasteTo);
	 /* jQuery("#flTreeComponent").bind("reopen.jstree", function () {
		  jQuery("#flTreeComponent").jstree("select_node", "#"+result.pasteTo);
		  jQuery("#"+result.pasteTo).parents(".jstree-closed").each(function () {
			  jQuery("#flTreeComponent").jstree("open_node", this, false, true);
      });
    });*/
	//refTree(result.pasteTo);
	//jQuery("#flTreeComponent").jstree("search",result.pasted);		
	/*var parents = jQuery("#flTreeComponent").jstree("get_path",jQuery("#"+result.pasteTo),true); 
	jQuery.each(parents, function(k, v){		   
    	alert(v);
    	alert(jQuery("#flTreeComponent").jstree("is_open", jQuery('#'+v)));
    	jQuery("#flTreeComponent").jstree("open_node",jQuery('#'+v));
    	console.log('LLLLLLLLLLLLLLLLL'+v);
	});*/

/*	if(result.pasted != null)
	{
		refTree(result.pasteTo);
		jQuery("#flTreeComponent").jstree("search",result.pasted);		
	}*/
/*		
}
*/
function copySuccess(result)
{
	alert(result.successMsg);	
	refreshTree();
	//setTimeout(function() {refreshTree();},1250);
	//alert(result.successMsg);	
	//openNode('flTreeComponent',result.copiedTo);
	setTimeout(function() {openNode('flTreeComponent',result.copiedTo);},1250);
	var assm = getSearchString('Assembly',result.copiedData);	
	//jQuery("#flTreeComponent").jstree("search",assm);
	closeFlDialog('dlgCopyAsm');
	//jQuery('#dlgCopyAsm').dialog('close');
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
	if(result.warningMsg != null && result.warningMsg != ''&& result.warningMsg != ' ')
	{
		 if(confirm(result.warningMsg) == true)
		 {
			 var elemId = result.elementID;
			 var id = result.orgID;
			 var type = result.type;
			 jQuery('#hdnwarningFields').val(elemId + "::"+id+"::"+type);			 
			 openDialog('W', "#dlgWarning",200,100);
		 }
	}
	else
	{
		refreshTree();
		alert(result.successData.msg);
	}
}
function saveBlobSuccess(result)
{		
	jQuery('#nodeImage').attr('src','');
	jQuery('#nodeImage').attr('src','tmp/images/'+result.fileName);
	
	if(screen.width <= 1024)
		setImgWidth( jQuery('#nodeImage'),400,416);
	else		
		setImgWidth( jQuery('#nodeImage'),473,368);
	//closeFlDialog('dlgAddImage');
	closePopUpDialoge('dlgAddImage');
	//jQuery( "#dlgAddImage" ).dialog("close");
}
function getImgSuccess(result)
{
	//alert(result.nodeImg.imgToimBlobimage);
	jQuery('#nodeImage').attr('src','');
	jQuery('#nodeImage').attr('src',result.nodeImg.imgToimBlobimage);	
	jQuery('#hdnBlobimage').val(result.nodeImg.imgToimBlobimage);
	if(screen.width <= 1024)
		setImgWidth( jQuery('#nodeImage'),400,416);
	else		
		setImgWidth( jQuery('#nodeImage'),473,368);
}
function getImageSuccess(result)
{
	//alert(result.nodeImg.imgToimBlobimage);
	jQuery('#nodeImage').attr('src','');
	jQuery('#nodeImage').attr('src',result.nodeImg.imgToimBlobimage);	
	//jQuery('#hdnBlobimage').val(result.nodeImg.imgToimBlobimage);
	if(screen.width <= 1024)
		setImgWidth( jQuery('#nodeImage'),400,416);
	else		
		setImgWidth( jQuery('#nodeImage'),473,368);
}
function delImgSuccess(result)
{
	jQuery('#previewField').attr('src','');
	jQuery('#nodeImage').attr('src','');
	alert(result.successData.msg);
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
	//alert(currentNode);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh();
}
function getMstFrmUrl(mstKey,parentDatas,formMode)
{
	
	var mstUrl = null;	
	var dataString = '?q=2&'+frmMode.frmMode+'='+formMode+'&'+frmMode.keyId+'='+mstKey.replace('_','/');
	
	//var parentArr = parentDatas.split('-');
	//for(var i=0;i<parentArr.length;i++)
		//dataString += '&'+parentArr[i].substring(0,3).toLowerCase()+'Key='+parentArr[i];
	
	mstKey = mstKey.substring(0,3);
	//alert(mstKey);
	if(formMode == frmMode.edit)
		dataString += '&'+frmMode.lockFields+'='+addToUrl(mstKey);
	
	if(mstKey == 'CMP')
		mstUrl = "company_input.comp"+dataString;
	else if(mstKey == 'LCN')
		mstUrl = "location_input.locn"+dataString;
	else if(mstKey == 'FCT')
		mstUrl = "factory_input.fact?q=2"+dataString;
	else if(mstKey == 'SBU')
		mstUrl = "SBU_input.sect"+dataString;
	else if(mstKey == 'PBU')
		mstUrl = "PBU_input.sect"+dataString;
	else if(mstKey == 'SEC')
		mstUrl = "section_input.sect"+dataString;
	else if(mstKey == 'CEL')
		mstUrl = "cell_input.cell"+dataString;
	else if(mstKey == 'MCH')
		 mstUrl = "equipment_input.eqp"+dataString;
	else if(mstKey == 'ASM')
		mstUrl = "assembly_input.asb"+dataString;
	else if(mstKey == 'SPR')
		mstUrl = "SparesMaster_input.sprmst"+dataString;
	//alert(mstUrl);
	return mstUrl;	
}

function addToUrl(mstKey)
{
	var dataString=null;

	if(mstKey == 'CMP')
		dataString = "KEYID";
	else if(mstKey == 'LCN')
		dataString = "KEYID,COMPANY";
	else if(mstKey == 'FCT')
		dataString = "KEYID,COMPANY,LOCATION";
	else if(mstKey == 'SBU')
		dataString = "KEYID,COMPANY,LOCATION";
	else if(mstKey == 'PBU')
		dataString = "KEYID,SBU";
	else if(mstKey == 'LIN')
		dataString = "KEYID,COMPANY";
	else if(mstKey == 'CEL')
		dataString = "KEYID,COMPANY,SECTION,FACTORY";
	else if(mstKey == 'MCH')
		dataString = "KEYID,SECTION,FACTORY,CELL";
	else if(mstKey == 'ASM')
		dataString = "KEYID";
	else if(mstKey == 'SPR')
		dataString = "KEYID,FACTORY";
	return dataString;	
}

function frmFuncnLocn_beforeLoadCurrentForm(result)
{

	flenableLayout();
	if(jQuery("#newMstFrm").is(":visible") ==  true)
	{ 
		jQuery("#newMstFrm").hide(0);		
		if(result != null)
		{
			if(jQuery('#txtformFld').val() =='A' || jQuery('#txtformFld').val()=='S')
			{
				openWithPop();
			}
			else
				{				
				  if(jQuery("#flTreeComponent").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
				  {
					if(jQuery('#loadFormMode').val() == 'CREATE')
					{
				  			//jQuery.jstree._reference("#flTreeComponent").refresh(jQuery('#hdnMstId').val());
				  			var isLeaf = jQuery("#"+treeId).jstree("is_leaf", jQuery('#'+jQuery('#hdnMstId').val()));
		
							if(isLeaf == true)
							{
								jQuery("#"+treeId).jstree("load_node",jQuery('#'+jQuery('#hdnMstId').val()));
								 setTimeout(function() {jQuery("#"+treeId).jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));},1250);
							}
							else
					  			jQuery("#flTreeComponent").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
					}
				  	else
				  	   refreshTree();
				  }
				  else
					  refreshNode("flTreeComponent",jQuery('#hdnMstId').val());
				  //jQuery("#flTreeComponent").jstree("refresh",jQuery('#'+jQuery('#hdnMstId').val())); //refreshTree();
				 
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
	
	if(jQuery('#txtformFld').val() == '-')
		selId = selId;
	else
		selId = selId+ '&formField='+jQuery('#txtformFld').val();

	
	if(selId == null || selId == '' || selId == ' ')
		var y = null;
	else
		 funcnLocnPopUp("funcnLocn_input.funlocn",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
}
 function closeFlDialog(dlgId)
 { 
 
	 jQuery( '#'+dlgId ).hide();
	 jQuery('#DIV_FLMASK').removeClass('popup-mask');	
	 jQuery( '#'+dlgId ).removeClass('custom-popup');
 }

</script>

<div id="wrapper">
<input type="text" style="display:none;" id="txtformFld" name="txtformFld"/>
<input type="text" style="display:none;" id="txtelemId" name="txtelemId"/>
<input type="text" style="display:none;" id="hdnParentsNodes" name="hdnParentsNodes"/>
<input type="text" style="display:none;" id="elemIdToPasteAsm" name="elemIdToPasteAsm"/>
<input type="text" style="display:none;" id="hdnImgLocnId" name="hdnImgLocnId"/>
<input type="text" style="display:none;" id="hdnBlobimage" name="hdnBlobimage"/>
<input type="text" style="display:none;" id="hdnIdNode" name="hdnIdNode"/>
<input type="text" style="display:none;" id="hdnIdParentNodes" name="hdnIdParentNodes"/>
<input type="text" style="display:none;" id="hdnElemType" name="hdnElemType"/>
<input type="hidden" id="hdnMstId" />
<input type="hidden" id="hdnRightClkNode" />
<input type="hidden" id="hdnTitleToDisplay" />
<input type="hidden" id="hdnElemTypeForCombo" />
<input type="hidden" id="hdnwarningFields" />

<div id="dlgAddNode" style="display:none">

     <div style="padding-bottom:30px;">
      	<div style="float:left">
	      	<input type="button" class="easyui-button" id="dlgFind" value="Find"/>
	      	<input type="button" class="easyui-button" id="dlgFilter" value="Filter"/>
	    </div>
   	 </div>
	  <div style="padding:top:20px;" class="sub-cntborder">
	     	<table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	  </div>
	  <div style="float:right;padding-top:10px;">
			<input type="button" class="easyui-button" id="" value="New"/>
		    <input type="button" class="easyui-button" id="dlgSave" value="Save"/>
		     <input type="button" class="easyui-button" id="dlgClose" value="Close"/>
	  </div>	
 </div>
<div class="searchLayerFuncLocn" id="searchFuncLocn">
	<form id="frmFindNode" name="frmFindNode">
          <table>	
          <tr>
          <td><label>Find</label></td>
          
		  <td> <select  id="findNode" class="easyui-combobox" name="findNode" style="width:120px;height:20px;">
			   <option value="cmp">Company</option>
			   <option value="lcn">Location</option>
			   <option value="sbu">SBU</option>
			   <option value="subunt">PBU</option>
			   <option value="sect">DMT</option>
			   <option value="cell">JH</option>
			   <option value="eqp">Equipment</option>
			   <option value="assm">Assembly</option>
			   <option value="spr">Spare</option>
		   </select>  </td>
		   <td> 
		   <input id="cmbfindTreeNode" clear="false" name="cmbfindTreeNode" class="easyui-combobox" style="width:200px;height:20px;"/>  
		   </td>
		   <td>
		   <input type="button" class="easyui-button" id="btnfindNext" value="Find Next" style="height:25px;"/>
		   </td>
		   <td>
		   <input type="button" class="easyui-button" id="btnfindPnlClear" value="Clear" style="height:25px;"/>
		   </td>
	       <td>
		   <input type="button" class="easyui-button"  id="btnFrmView" value="View" style="height:25px;"/>			 
		   </td>
		   <td>
		   <input type="button" class="easyui-button" id="btnFrmEdit" value="Edit" style="height:25px;"/>	
            </td>   
			<td>
			<input type="button" class="easyui-button" style="display:none" id="btnPstAsm" value="Paste"/>    
			</td>
			<td style="float:right;width:440px;">
			<input type="button" class="easyui-button" id="legendButton" value="Legend" style="float:right;height:25px;"/> 
	         </td>
	</tr>
	</table>
	
	</form>
</div>
<div style="margin-left:1%;margin-top:0.5%;width:90%;">
	<div id="flTreeLayer" style="float:left;width:500px;height:365px;overflow:auto;background-color:#ffffff;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;" class="sub-cntborder">
	 
		<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
		<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
		 
	</div>
	<span>
	<div id="DocMgrFileLayer" style="width:490px;height:368px;margin-left:51%;overflow:auto;">
	   
	    <img id="nodeImage">
	      <div id="dlgWarning" class="flPopUpBox">
			  <div id="titleWarning" class="fl-popUpHeader" style="width:218px;margin-left:-10px;">
			   	 <label id="lblWarning" style="margin-left:1px;font-size:11px;"></label> 
			 	 <img id="imgCloseWarning" src="images/cancel.png" style="float:right;"/>
			  </div>
			  <form name="frmWarning" id="frmWarning">
			   <div class="easyui-paddingbfpx" style="padding-left:30px;padding-top:20px;"> 
			   		<input id="dteInactive" name="dteInactive" clear="false" class="easyui-datebox"  style="width: 150px;">
			   </div>
			    <div class="easyui-paddingbfpx" style="padding-left:40px;padding-top:20px;"> 
				    <input type="button" class="easyui-button" id="btnWarningOk" value="OK"/>
					<input type="button" class="easyui-button" id="btnWarningCancel" value="Cancel"/>
				</div>
			  </form>
		 </div><!--
	    <div id="dlgAddChild" class="flPopUpBox">
			  <div id="titleAddChild" class="fl-popUpHeader" style="width:318px;margin-left:-10px;">
			   	 <label id="lblAddChild" style="margin-left:1px;font-size:11px;"></label> 
			 	 <img id="imgCloseAddChild" src="images/cancel.png" style="float:right;"/>
			  </div>
			  <form name="frmAddChild" id="frmAddChild">
			 	  <div class="easyui-paddingbfpx" style="padding-left:30px;padding-top:25px;">
						<label> Child Details</label>
				  </div> 
				  <div class="easyui-paddingbfpx" id="forsectChild" style="padding-left:30px;display:none"> 
						<select id="cbosectChild"  name="cbosectChild" class="easyui-combobox" style="width:170px;" required="true" >
						     <option value="-"> ---</option>
							 <option value="E"> Equipment</option>
						</select> 
				  </div>
				  <div class="easyui-paddingbfpx" id="foreqpChild" style="padding-left:30px;display:none"> 
						 <select id="cboeqpChild" name="cboeqpChild" class="easyui-combobox" style="width:170px;" required="true" >
							  <option value="-"> ---</option>
							  <option value="A"> Assembly</option>
							  <option value="S"> Spare</option>
						 </select>
				  </div>		
				  <div class="easyui-paddingbfpx" id="forassmChild" style="padding-left:30px;display:none"> 	
						 <select id="cboassmChild"  name="cboassmChild" class="easyui-combobox"  style="width:170px;" required="true" >
								<option value="-"> ---</option>						
								<option value="S"> Spare</option>
						 </select> 
				   </div>
				    <div class="easyui-paddingbfpx" style="padding-left:70px;padding-top:30px;"> 
				    		<input type="button" class="easyui-button" id="btndlgOk" value="OK"/>
					      	<input type="button" class="easyui-button" id="dlgCancel" value="Cancel"/>
					</div>
				</form>
		</div>
		--><!--<div id="dlgAddImage" class="flPopUpBox">
				  <div id="titleAddImage" class="fl-popUpHeader" style="width:618px;margin-left:-10px;">
				   	<label id="lblAddImage" style="margin-left:1px;font-size:11px;"></label> 
				 	  <img id="imgCloseAddImage" src="images/cancel.png" style="float:right;"/>
				  </div>
						 
				  <div style="padding-bottom:4px;padding-left:4px;padding-right:4px;padding-top:4px;">
					 <img  id="previewField" class="previewField " width="580" height="380" src="">
					 <div class="easyui-paddingbfpx" style="padding-left:200px;padding-top:10px;"> 
							 <input type="button" class="easyui-button" id="dlgAddImg" name="dlgAddImg"  value="Add Image"/>
							 <span id="dlgSaveButton" style="display:none;">
									
							 <input type="button" class="easyui-button"  id="dlgSve" value="Save"/>
							 
							 </span>
							  <span id="dlgDelButton" style="display:none;">
									<input type="button" class="easyui-button"  id="dlgDel" value="Delete"/>
							 </span>
						     <input type="button" class="easyui-button" id="dlgCls" value="Close"/>
						     <input type="hidden" id="dlgElemType" name="dlgElemType">
					</div>
				</div>
			</div>
			 <div id="dlgCutEqp" class="flPopUpBox">
				 <div id="titleCutEqp" class="fl-popUpHeader" style="width:318px;margin-left:-10px;">
					   	<label id="lblCutEqp" style="margin-left:1px;font-size:11px;"></label> 
					 	<img id="imgCloseCutEqp" src="images/cancel.png" style="float:right;"/>
				 </div>
				 <form id="frmCutEqp" name="frmCutEqp">
					  <input type="text" style="display:none;" id="txtDlgElemId" name="txtDlgElemId"/>
					  <input type="text" style="display:none;" id="txtDlgDispCode" name="txtDlgDispCode"/> 
		 			  <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
						   <label>Unit</label>                       
					  </div> 
					  <div class="easyui-paddingbfpx" style="padding-left:10px;"> 
							   <input id="cmbdialogUnit" name="cmbdialogUnit" class="easyui-combobox" style="width:260px;"/ >                       
					 </div>
		 			 <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
						   <label>Section</label>                       
					 </div> 
					 <div class="easyui-paddingbfpx" style="padding-left:10px;"> 
						   <input id="cmbdialogsubUnit" name="cmbdialogsubUnit" class="easyui-combobox" style="width:260px;"/ >                       
					 </div>
				   	 <div  class="easyui-paddingbfpx" style="padding-left:10px;">
						   <label>Line</label>                       
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
						   <input  id="cmbdialogsect" name="cmbdialogsect" class="easyui-combobox" style="width:260px;"/ >                       
					</div>
					 <div class="easyui-paddingbfpx" style="padding-left:80px;padding-top:20px;"> 
		 	    		    <input type="button" class="easyui-button" id="dlgPaste" value="Paste"/>
				      		<input type="button" class="easyui-button" id="dlgCncl" value="Cancel"/>
					</div>
			    </form>
			 </div>-->
			 <div id="dlgCopyAsm" class="flPopUpBox">
				  <div id="titleCopyAsm" class="fl-popUpHeader" style="width:318px;margin-left:-10px;">
					   	<label id="lblCopyAsm" style="margin-left:1px;font-size:11px;"></label> 
					 	 <img id="imgCloseCopyAsm" src="images/cancel.png" style="float:right;"/>
				 </div>
					 
				<form id="frmCopyAsm" name="frmCopyAsm">  
					 <input type="text" style="display:none;" id="CopyAsmElemId" name="CopyAsmElemId"/>
					 <input type="text" style="display:none;" id="CopyAsmDispCode" name="CopyAsmDispCode"/> 
					   	<div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
							<label>Unit</label>                       
						</div> 
						<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
							   <input id="cmbCopyUnit" name="cmbCopyUnit" class="easyui-combobox" style="width:260px;"/ >                       
						</div>
			 			<div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
							   <label>Section</label>                       
						</div> 
						<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
							   <input id="cmbCopysubUnit" name="cmbCopysubUnit" class="easyui-combobox" style="width:260px;"/ >                       
						</div>
						<div  class="easyui-paddingbfpx" style="padding-left:10px;">
							   <label>Line</label>                       
						</div> 
						<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
							   <input  id="cmbCopysect" name="cmbCopysect" class="easyui-combobox" style="width:260px;"/ >                       
						</div>
				 		<div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
								<label>Equipment</label>                       
						</div> 
						<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
								<input id="cmbCopyEquip" name="cmbCopyEquip" class="easyui-combobox" style="width:260px;"/ >                       
						</div>
						 <div class="easyui-paddingbfpx" style="padding-left:80px;padding-top:20px;"> 
				 	    		    <input type="button" class="easyui-button" id="dlgCopyAsmPaste" value="Paste"/>
						      		<input type="button" class="easyui-button" id="dlgCopyAsmCncl" value="Cancel"/>
						</div>
					</form>
				</div>
	</div>
	</span>
	</div>
	 <div id="lgnd-panel" style="position:absolute;right:72px;top:50px;right:24px\9;top:54px\9;">
		<ul>
			<li><a href="#"><img src="images/FnLocn/company.jpg"/><span>Company</span></a></li>
			<li><a href="#"><img src="images/FnLocn/location.jpg"/><span>Location</span></a></li>
			<li><a href="#"><img src="images/FnLocn/sbu.jpg"/><span>SBU</span></a></li>
			<li><a href="#"><img src="images/FnLocn/pbu.jpg"/><span>PBU</span></a></li>
			<li><a href="#"><img src="images/FnLocn/unit.jpg"/><span>DMT</span></a></li>
			<li><a href="#"><img src="images/FnLocn/section.jpg"/><span>JH</span></a></li>
<!--		<li><a href="#"><img src="images/FnLocn/sub-section.jpg"/><span>Cell</span></a></li>-->
			<li><a href="#"><img src="images/FnLocn/machine.jpg"/><span>Equipment</span></a></li>
			<li><a href="#"><img src="images/FnLocn/assembly.jpg"/><span>Assembly</span></a></li>
<!--			<li><a href="#"><img src="images/FnLocn/instrument.jpg"/><span>Instrument</span></a></li>-->
<!--			<li><a href="#"><img src="images/FnLocn/sub-assembly.jpg"/><span>Sub Assembly1</span></a></li>-->
<!--			<li><a href="#"><img src="images/FnLocn/sub-assembly.jpg"/><span>Sub Assembly2</span></a></li>-->
			<li><a href="#"><img src="images/FnLocn/spare.png"/><span>Spare</span></a></li>
		</ul>
  	 </div><!-- /settings-panel -->
  	 </div>
  		   
<input type="hidden" id="hdnnodeId" value=""/>
		
		 