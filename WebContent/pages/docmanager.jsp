<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmDocMgr'); 
	initialiseForm("frmDocMgr");
    jQuery('#frmDocMgr .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmDocMgr textarea').css('text-transform', 'uppercase');		
	var hiddenUrl = jQuery('#hiddenUrl').val();

	jQuery(function () {
	 	 processTree( jQuery("#DocMgrTree"),'loadval.dcm','searchnode.dcm');
		 jQuery("#DocMgrTree") .bind("select_node.jstree", function (e, data) {	
			 jQuery( "#cmbtxtSearch").val('');
			 getAllFiles(data.rslt.obj.attr("id"),data.rslt.obj.attr("elementType"),data.rslt.obj.attr("displayCode"),data.rslt.obj.attr("elementId"),data.rslt.obj.attr("parentId"),data);
						 		
		 		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
		 		jQuery('#hdnIdParentNodes').val(data.rslt.obj.attr("parentId"));
		 				 	
		 });
		 var fluSearchStr = '';
		 jQuery("#DocMgrTree").bind("search.jstree", function (e, data) {
		
		         if( jQuery(".search_parent_flu-s").length > 0){
					jQuery("#DocMgrLayer").scrollTop(jQuery(".search_parent_flu-s").offset().top -jQuery('#flTreeComponent').offset().top);
		         }	
				 else{
						if( fluSearchStr != data.rslt.str )
						jQuery('li .search-completed-flu-c').removeClass('search-completed-flu-c');					
						fluSearchStr = data.rslt.str;
				
			 			jQuery('#DocMgrTree').find('li').each(function(){	
							 if( jQuery(this).children('a').hasClass('jstree-search') && ! jQuery(this).hasClass('search-completed-flu-c'))
						 	 {	
								 jQuery("#DocMgrLayer").scrollTop(jQuery(this).offset().top -jQuery('#flTreeComponent').offset().top);
								 jQuery(this).addClass('search-completed-flu-c');
								 return false;	 	
						 	 }
			 			 });
				 }
		 });
		jQuery("#DocMgrTree").bind("loaded.jstree", function (event, data) {	
				//processTree( jQuery("#flTreeComponent"),'loadval.funlocn?elementId=CMP/01&elementType=CMP&parentId=CMP/01');
		});
		jQuery("#DocMgrTree").bind("open_node.jstree", function (event, data) { 			
		      if((data.inst._get_parent(data.rslt.obj)).length) { 
		        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
		      } 
	    });
  });	
	  
	jQuery('#cmbtxtSearch').combobox({  
	    mode:'local',
	    url:'search_combo.dcm',		
		dataType:'json',		
		textField:'text',
		valueField:'id',
		onLoadSuccess:function()
		{
			
			var onLoadSuccessName = 'search_onLoadSuccess';
			var args = null;
			dynamicFunctionCall(onLoadSuccessName,args);
		}
	}); 
});



jQuery('#cmbtxtSearch').combobox('textbox').bind('keydown',function( event){	

	if(event.keyCode == 13)
	{
		searchFile();	
		event.preventDefault();
		event.stopPropagation();
	}
	else
	{
	 setTimeout(function() { show_panel(jQuery('#cmbtxtSearch').combobox('getText'),event);},1250);	 
	}
});

jQuery( "#Advsearch").click(function() {
	var fileId = jQuery('#hdnFileId').val();
	var dataString = '?q=2&addMode=AdvSearch&fileId='+fileId;
	//alert(dataString);
	LoadPopUp("divAdvSearch","addDoc_input.dcm"+dataString, true,"48%","65%","10%","20%", "AdvSearch_Callback","Advanced Search");
});

function AdvSearch_Callback(args)
{
	
}

jQuery( "#searchFiles").click(function() {
	searchFile();

});

jQuery( "#imgCloseDocMgr" ).click(function() {
	closePopUp();	
});
jQuery( "#modFileList" ).click(function() {
	
	jQuery( "#modFileList").addClass('docMgrRightClkMenu');
	jQuery( "#delFileList").removeClass('docMgrRightClkMenu');
	jQuery( "#refFileList").removeClass('docMgrRightClkMenu');

	var fileId = jQuery('#hdnFileId').val();	
	var dataString = '?q=2&addMode=File&fileId='+fileId;
	 
	LoadPopUp("divAddFile","addDoc_input.dcm"+dataString, true,"600px","400px","10%","20%", "ADDFile_Callback","Modify File");
});
jQuery( "#modFileList" ).hover(function() {
	jQuery( "#modFileList").addClass('docMgrRightClkMenuHover');
	jQuery( "#delFileList").removeClass('docMgrRightClkMenuHover');
	jQuery( "#refFileList").removeClass('docMgrRightClkMenuHover');
});

jQuery( "#delFileList" ).click(function() {
	jQuery( "#delFileList").addClass('docMgrRightClkMenu');
	jQuery( "#modFileList").removeClass('docMgrRightClkMenu');
	jQuery( "#refFileList").removeClass('docMgrRightClkMenu');
	 var viewsMode = jQuery('#hdnViewsMode').val();
	 
	var fileId = jQuery('#hdnFileId').val();	
	var title = jQuery('#hdnTitle').val();
	var dataString = '?q=2&fileId='+fileId;
	var selFolderVal = jQuery('#hdnSelectedFolder').val();
	
	var selFolderValArr = selFolderVal.split(',');
	if(selFolderValArr[0] != null && selFolderValArr[0] != '' && selFolderValArr[0] != ' ')
		dataString += '&id='+selFolderValArr[0];
	if(selFolderValArr[1] != null && selFolderValArr[1] != '' && selFolderValArr[1] != ' ')
		dataString += '&levelNo='+selFolderValArr[1];
	if(selFolderValArr[2] != null && selFolderValArr[2] != '' && selFolderValArr[2] != ' ')
		dataString += '&folderName='+selFolderValArr[2];
	if(selFolderValArr[3] != null && selFolderValArr[3] != '' && selFolderValArr[3] != ' ')
		dataString += '&elemId='+selFolderValArr[3];
	if(selFolderValArr[4] != null && selFolderValArr[4] != '' && selFolderValArr[4] != ' ')
		dataString += '&parentId='+selFolderValArr[4];
	if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
		dataString += '&viewsMode='+viewsMode;
	if(confirm("Do You want to delete this file : "+title) == true)
		processAjaxCalls('file_del.dcm',dataString,'DelFile','DelFileErr');
});
jQuery( "#delFileList" ).hover(function() {
	jQuery( "#delFileList").addClass('docMgrRightClkMenuHover');
	jQuery( "#modFileList").removeClass('docMgrRightClkMenuHover');
	jQuery( "#refFileList").removeClass('docMgrRightClkMenuHover');
});
jQuery( "#refFileList" ).click(function() {
	jQuery( "#refFileList").addClass('docMgrRightClkMenu');
	jQuery( "#delFileList").removeClass('docMgrRightClkMenu');
	jQuery( "#modFileList").removeClass('docMgrRightClkMenu');
});
jQuery( "#refFileList" ).hover(function() {
	jQuery( "#refFileList").addClass('docMgrRightClkMenuHover');
	jQuery( "#delFileList").removeClass('docMgrRightClkMenuHover');
	jQuery( "#modFileList").removeClass('docMgrRightClkMenuHover');
});
jQuery( "#thumbnailView" ).click(function() {
	//closeRtClkMenu('#dlgModifyFile');
	 jQuery('#DocMgrFileLayer').html('');
	
	 var thumbnailView = jQuery('#hdnThumbnail').html();
	 jQuery('#DocMgrFileLayer').html(thumbnailView);

	 jQuery('#hdnViewsMode').val('thumbnail');
});

jQuery( "#listView" ).click(function() {
	//closeRtClkMenu('#dlgModifyFile');
	 jQuery('#DocMgrFileLayer').html('');
	
	 var listView = jQuery('#hdnList').html();
	 jQuery('#DocMgrFileLayer').html(listView);

	 jQuery('#hdnViewsMode').val('list');
});

jQuery( "#iconView" ).click(function() {
	// closeRtClkMenu('#dlgModifyFile');
	 jQuery('#DocMgrFileLayer').html('');	
	 var iconView = jQuery('#hdnIcon').html();
	
	 jQuery('#DocMgrFileLayer').html(iconView);
	 jQuery('#hdnViewsMode').val('icon');
});

function search_onLoadSuccess()
{
	var searchTxt = jQuery('#hdnSearchComboText').val();
	var checkSearchTxt = jQuery('#cmbtxtSearch').combobox('getText');
	if(checkSearchTxt == null || checkSearchTxt == ' ' || checkSearchTxt == '')
		jQuery('#cmbtxtSearch').combobox('setText',searchTxt);
}
function show_panel(text,event)
{
	jQuery('#hdnSearchComboText').val(text);
    jQuery('#cmbtxtSearch').combobox("reload",'search_combo.dcm?q=2&searchText='+text);
	//event.preventDefault();
	//event.stopPropagation();
}
function loadval_searchCallBack(result){
	 jQuery("#DocMgrSearch").css('display','none');
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
       
        jQuery("#DocMgrTree " + parentIds).addClass("search_parent_flu-s");         
    }    
}
function customMenu(node) {
	
	var mod = jQuery.jstree._focused()._get_node(node).attr('allowModify');
	var del = jQuery.jstree._focused()._get_node(node).attr('allowDel');
	var dl = jQuery.jstree._focused()._get_node(node).attr('allowDownload');
	var rights = jQuery.jstree._focused()._get_node(node).attr('allowRights');
	
	 //closeRtClkMenu('#dlgModifyFile');
	  var x = jQuery.jstree._focused()._get_node(node).attr('id');
	  //var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');
	  var selId = jQuery('#hdnIdNode').val();
	  if(selId != null && selId != '' && selId != ' ')
	   	jQuery("#"+selId+" a").removeClass('jstree-clicked');
	  
	  var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');
	  
	  var prevNode = jQuery("#hdnRightClkNode").val(); 	
	  if(prevNode != null && prevNode != '' && prevNode != ' ')
		  jQuery("#"+prevNode+" a").removeClass('jstree-clicked');	  	
	 
	   jQuery("#hdnRightClkNode").val(x);
	   jQuery("li[id="+x+"]  > a").addClass('jstree-clicked');


	   var url = jQuery('#hiddenUrl').val();
	   if(url.indexOf('view')<=0)
	   {
			if(elemType == '1')
			{
			   var items = {
				        addFolder: { 
				            label: "Add Folder",
				            action: function () {addFolder(node);}
				        }	       
				      
			 };
			}
			else
			{
				
				 var items = {
					        addFolder: { 
					            label: "Add Folder",
					            action: function () {addFolder(node);}
					        },
					        
					       modifyFolder: { 
					            label: "Rename Folder",
					            action: function () {modifyFolder(node);}
					        },
					        delFolder: { 
					            label: "Delete Folder",
					            action: function () {delFolder(node);}
					        },
					        addFile: { 
					            label: "Add File",
					            action: function () {addFile(node);}
					        },
					        userRights: { 
					            label: "User Rights",
					            action: function () {assignRightsToUser(node);}
					        }
				 };

				if( mod == 'N'){
					delete items.modifyFolder;
				}
				if( del == 'N'){
					delete items.delFolder;
				}
				if( rights == 'N'){
					delete items.userRights;
				}
				
			}
			 return items;
	   }
}

function addFolder(obj)
{
	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var displayOrder = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	//openPopUp('Folder','Add Folder');	
	var dataString = '?q=2&addMode=Folder&levelNo='+levelNo+'&displayOrder='+displayOrder+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	
	LoadPopUp("divAddDoc","addDoc_input.dcm"+dataString, true,"23%","50%","10%","20%", "ADDFolder_Callback","Add Folder");
}
function modifyFolder(obj)
{
	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var displayOrder = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	//openPopUp('Folder','Add Folder');	
	var dataString = '?q=2&addMode=Folder&levelNo='+levelNo+'&displayOrder='+displayOrder+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id+'&folderMode=Modify';
	//alert(dataString);
	LoadPopUp("divAddDoc","addDoc_input.dcm"+dataString, true,"23%","50%","10%","20%", "ADDFolder_Callback","Rename Folder");
}
function delFolder(obj)
{

	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var displayOrder = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	var dataString ='?q=2&folderId='+id+'&levelNo='+levelNo+'&dispCode='+dispCode+'&parentId='+parentNodes;
	if(confirm("Do You want to delete this Folder : "+dispCode) == true)
		processAjaxCalls('folder_del.dcm',dataString,'DelFolderCallback','DelFolderCallbackErr');
	
}

function addFile(obj)
{
	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('elementType');
	var displayOrder = jQuery.jstree._focused()._get_node(obj).attr('elementId');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');

	var modify = jQuery.jstree._focused()._get_node(obj).attr("allowModify");
	var del = jQuery.jstree._focused()._get_node(obj).attr("allowDel");
	var downloadRights = jQuery.jstree._focused()._get_node(obj).attr("allowDownload");
	var rights = jQuery.jstree._focused()._get_node(obj).attr("allowRights");
	
	var dataString = '?q=2&addMode=File&levelNo='+levelNo+'&displayOrder='+displayOrder+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	if(modify != null && modify != '' && modify != ' ')
	{
		dataString += '&allowModify='+modify;
	}
	if(del != null && del != '' && del != ' ')
	{
		dataString += '&allowDel='+del;
	}

	if(downloadRights != null && downloadRights != '' && downloadRights != ' ')
	{
		dataString += '&allowDownload='+downloadRights;
	}

	if(rights != null && rights != '' && rights != ' ')
	{
		dataString += '&allowRights='+rights;
	}
	LoadPopUp("divAddFile","addDoc_input.dcm"+dataString, true,"48%","70%","10%","20%", "ADDFile_Callback","Add File");
	
}
function assignRightsToUser(obj)
{
	var name = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var keyId = jQuery.jstree._focused()._get_node(obj).attr('id');
	var dataString = '?q=2';
	if(keyId != null && keyId != '' && keyId != ' ')
	    dataString += '&keyId='+keyId;
	
	LoadPopUp("divUserRights","user_rights.dcm"+dataString, true,"57.5%","70%","10%","20%", "ADDUserRights_Callback","Add User Rights");
	
}
function ADDFolder_Callback()
{	
	jQuery('#divAddDoc').css('overflow','auto');
}
function ADDFile_Callback()
{
	//closeRtClkMenu('#dlgModifyFile');
	//jQuery("#divAddFile").css('margin-top',"6%");
	var uploadFile = jQuery('#hdnUploadedFile').val();
	if(uploadFile != null && uploadFile != '' && uploadFile != ' ')
	{
	
		jQuery(".qq-upload-list").html(uploadFile);
		  jQuery(".qq-upload-list").css('font-size','12px');
		  jQuery(".qq-upload-list").css('line-height','8px');
	}
	
}
function RevHistory_Callback()
{
	jQuery("#divAddFile").css('margin-top',"6%");
	var uploadFile = jQuery('#hdnUploadedFileRevHist').val();
	if(uploadFile != null && uploadFile != '' && uploadFile != ' ')
	{
	
		  jQuery(".qq-upload-list").html(uploadFile);
		  jQuery(".qq-upload-list").css('font-size','12px');
		  jQuery(".qq-upload-list").css('line-height','8px');
	}
	
}
function ADDUserRights_Callback()
{
	
}
function InsertFolder(result)
{	
	var parentId = result.successData.parentId;
	var name = result.successData.name;
	var user_rights_msg = 'Do You want to give user rights';
	if(name != null && name != '' && name != ' ')
		user_rights_msg  += ' to '+name; 
	user_rights_msg  += '?';
	if(result != null)
	{
		closePopUpDialoge('divAddDoc');
		
		  if(jQuery("#DocMgrTree").jstree("is_open",  jQuery('#'+parentId)) == false)
		  {
			   var isLeaf = jQuery("#DocMgrTree").jstree("is_leaf",  jQuery('#'+parentId));
				
				if(isLeaf == true)
				{
					jQuery("#DocMgrTree").jstree("load_node",  jQuery('#'+parentId));
					setTimeout(function() {jQuery("#DocMgrTree").jstree("open_node",  jQuery('#'+parentId));},1250);
				}
				else
		  			jQuery("#DocMgrTree").jstree("open_node",  jQuery('#'+parentId));
		  }
		  else
		  	refreshNode("DocMgrTree",parentId);

		  if(confirm(user_rights_msg) == true)
		  {
			 var keyId = result.successData.keyId;
			 var dataString = '?q=2';
			 if(keyId != null && keyId != '' && keyId != ' ')
				 dataString += '&keyId='+keyId;
			
			 LoadPopUp("divUserRights","user_rights.dcm"+dataString, true,"800px","420px","10%","20%", "ADDUserRights_Callback","Add User Rights");
		  }
		 	var id=result.nodeId;
			var elemType=result.elemType;
			var dispCode=result.dispCode;
			jQuery('#DocMgrFileLayer').html("");
			//processAjaxCalls('getContent.dcm','?q=2&id='+id+'&elemType='+elemType+'&dispCode='+escape(dispCode),'getFiles','getFilesErr');
	}
}
function frmAddFile_successsCallback(result)
{
	
	closePopUpDialoge('divAddFile');
	var id=result.nodeId;
	//alert(id);
	var elemType=result.elemType;
	
	var dispCode=result.dispCode;
	var viewsMode=result.viewsMode;
	var searchTxt = jQuery( "#cmbtxtSearch").val();
	var modify = result.allowModify;
	var del = result.allowDel;
	var downloadRights = result.allowDownload;
	var rights = result.allowRights;
	var dataStr = '?q=2&id='+id+'&elemType='+elemType+'&dispCode='+escape(dispCode)+'&viewsMode='+viewsMode;
	if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
		dataStr += '&keywords='+searchTxt;

	if(modify != null && modify != '' && modify != ' ')
	{
		dataStr += '&allowModify='+modify;
	}
	if(del != null && del != '' && del != ' ')
	{
		dataStr += '&allowDel='+del;
	}

	if(downloadRights != null && downloadRights != '' && downloadRights != ' ')
	{
		dataStr += '&allowDownload='+downloadRights;
	}

	if(rights != null && rights != '' && rights != ' ')
	{
		dataStr += '&allowRights='+rights;
	}
	jQuery('#DocMgrFileLayer').html("");
	
	if(id != null && id!= '' && id!= ' ')
		processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	else
	{
		if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
			processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	}
}
function frmRevisionHistory_successsCallback(result)
{
	
	closePopUpDialoge('divAddRevisionHistory');
	var id=result.nodeId;
	var elemType=result.elemType;
	var dispCode=result.dispCode;
	var viewsMode=result.viewsMode;
	var searchTxt = jQuery( "#cmbtxtSearch").val();
	var dataStr = '?q=2&id='+id+'&elemType='+elemType+'&dispCode='+escape(dispCode)+'&viewsMode='+viewsMode;
	if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
		dataStr += '&keywords='+searchTxt;
	jQuery('#DocMgrFileLayer').html("");
	
	if(id != null && id!= '' && id!= ' ')
		processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	else
	{
		if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
			processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	}
}
function frmAddDocUserRights_successsCallback(result)
{
	var folderId = result.folderId;
	if(folderId != null && folderId!= '' && folderId!= ' ')
	{
		var hdnFolderId = jQuery("#hdnRlriDocid").val();
		if(hdnFolderId == null || hdnFolderId== '' || hdnFolderId== ' ')
		{
			jQuery("#hdnRlriDocid").val(folderId);
		}
	}
	jQuery("#userRightsGrid").trigger("reloadGrid");
}
function DelFile(result)
{
	//closeRtClkMenu('#dlgModifyFile');
	closePopUpDialoge('divAddFile');
	alert(result.successData.msg);
	var id=result.nodeId;
	var elemType=result.elemType;
	
	var dispCode=result.dispCode;
	var viewsMode=result.viewsMode;

	var modify = result.allowModify;
	var del = result.allowDel;
	var downloadRights = result.allowDownload;
	var rights = result.allowRights;
	
	var searchTxt = jQuery( "#cmbtxtSearch").val();
	var dataStr = '?q=2&id='+id+'&elemType='+elemType+'&dispCode='+escape(dispCode)+'&viewsMode='+viewsMode;
	if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
		dataStr += '&searchTxt='+searchTxt;

	if(modify != null && modify != '' && modify != ' ')
	{
		dataStr += '&allowModify='+modify;
	}
	if(del != null && del != '' && del != ' ')
	{
		dataStr += '&allowDel='+del;
	}

	if(downloadRights != null && downloadRights != '' && downloadRights != ' ')
	{
		dataStr += '&allowDownload='+downloadRights;
	}

	if(rights != null && rights != '' && rights != ' ')
	{
		dataStr += '&allowRights='+rights;
	}
	
	jQuery('#DocMgrFileLayer').html("");
	
	if(id != null && id!= '' && id!= ' ')
		processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	else
	{
		if(searchTxt != null && searchTxt!= '' && searchTxt!= ' ')
			processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	}
}
function DelFolderCallback(result)
{
	var id=result.nodeId;
	var elemType=result.elemType;
	var dispCode=result.dispCode;
	alert(result.successData.msg);
	refreshNode("DocMgrTree",id);
}
function getAllFiles(id,elemType,dispCode,elemId,parentId,data)
{
	//alert('elemType'+elemType);
	//alert('id'+id);
	//var view = data.rslt.obj.attr("allowModify");	
	var modify = data.rslt.obj.attr("allowModify");
	var del = data.rslt.obj.attr("allowDel");
	var downloadRights = data.rslt.obj.attr("allowDownload");
	var rights = data.rslt.obj.attr("allowRights");
	jQuery('#DocMgrFileLayer').html("");
	var viewsMode = jQuery('#hdnViewsMode').val();
	var dataStr = '?q=2&id='+id+'&elemType='+elemType+'&dispCode='+escape(dispCode)+'&elemId='+elemId+'&parentId='+parentId+'&viewsMode='+viewsMode;
	if(modify != null && modify != '' && modify != ' ')
	{
		dataStr += '&allowModify='+modify;
	}
	if(del != null && del != '' && del != ' ')
	{
		dataStr += '&allowDel='+del;
	}

	if(downloadRights != null && downloadRights != '' && downloadRights != ' ')
	{
		dataStr += '&allowDownload='+downloadRights;
	}

	if(rights != null && rights != '' && rights != ' ')
	{
		dataStr += '&allowRights='+rights;
	}
	
	
	processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');

}	
	function getFiles(result)
	{		
		 var url = jQuery('#hiddenUrl').val();
		 
		var fileDtlsArr = result.otherDetails;
		var rightClkFlag = result.rightClk;
		var id = result.id;
		var levelNo = result.levelNo;
		var folderName = result.folderName;
		var elemId = result.elemId;
		var parentId = result.parentId;		
		var viewsMode = result.viewsMode;
		
		var allowModify = result.allowModify;
		var allowDel = result.allowDel;
		var allowDownload = result.allowDownload;
		var allowRights = result.allowRights;	
						 
		var htmlString = "<div>";
		var listHtml = htmlString;
		var iconHtml = htmlString;		
		var listHtml = "<table style='table-layout: fixed;width:100%'><tr class='headerListDocMgr'><td class='fileDocMgr'>File</td><td class='descDocMgr'>Keywords</td><td class='createdOnDocMgr'>Created On</td><td class='createdByDocMgr'>Created By</td></tr>";
		//var listHtml = "<div class='headerListDocMgr'><span class='fileDocMgr'>File</span><span class='descDocMgr'>Description</span><span class='createdOnDocMgr'>Created On</span><span class='createdByDocMgr'>Created By</span></div>";
		for(var i=0;i<fileDtlsArr.length;i++)
		{
		//onmousedown=rightclick('"+result.otherDetails[i].fileId+"');	
			htmlString += "<div id='"+result.otherDetails[i].fileId+"' class='sub-cntborder viewLayerDocMgr'>";
			htmlString += "<img src='"+result.otherDetails[i].icon+"'/><span style='margin-left:2px;'> <b>  " +result.otherDetails[i].fileName+"</b> </span>";
			if((url.indexOf('download')>0 && result.otherDetails[i].icon.indexOf('folder')<=0) || url.indexOf('view')>0 && result.otherDetails[i].icon.indexOf('folder')<=0)
			{
				if(allowDownload != null && allowDownload != '' && allowDownload != ' ' && allowDownload == 'Y')
					htmlString += "<span style='float:right;margin-left:1%;'><img id='img"+result.otherDetails[i].fileId+"' onclick='downloadFile(this.id);' title='Download' src='images/defaultIcons/DOWNLOAD_22_1.png'/></span>";
			}
			if(result.otherDetails[i].icon.indexOf('folder')<=0 &&  url.indexOf('view')<=0)			   
			{
				if(allowDel != null && allowDel != '' && allowDel != ' ' && allowDel == 'Y')
					htmlString += "<span style='float:right;margin-left:1%;'><img id='del"+result.otherDetails[i].fileId+"' onclick='fileDel(this.id);' title='Delete' src='images/defaultIcons/Delete-24_1.png'/></span>";
				if(allowModify != null && allowModify != '' && allowModify != ' ' && allowModify == 'Y')
					htmlString += "<span style='float:right;margin-left:1%;'><img id='mod"+result.otherDetails[i].fileId+"' onclick='fileModify(this.id);' title='Modify' src='images/defaultIcons/MODIFY-22.png'/></span>";
			}
			
			
			htmlString += "<div style='margin-top:2%;margin-left:2%;'>";
			htmlString +="<table><tr><td style='float:right;'><label style='color:blue;font-weight:bold;font-size:10'>Subject Area : </label></td><td><label style='font-size:10;'>"+result.otherDetails[i].Subjectarea+"</label></td></tr>";
			htmlString +="<tr><td style='float:right;'><label style='color:blue;font-weight:bold;font-size:10'>Category : </label></td><td><label style='font-size:10;'>"+result.otherDetails[i].Category+"</label></td></tr>";
			htmlString +="<tr><td style='float:right;'><label style='color:blue;font-weight:bold;font-size:10'>Created By : </label></td><td><label style='font-size:10;'>"+result.otherDetails[i].createdBy+"</label></td></tr>";
			htmlString +="<tr><td style='float:right;'><label style='color:blue;font-weight:bold;font-size:10'>Modified By : </label></td><td><label style='font-size:10;'>"+result.otherDetails[i].createdBy+"</label></td></tr>";
			htmlString +="<tr><td style='float:right;'><label style='color:blue;font-weight:bold;font-size:10'>Date :  </label></td><td><label style='font-size:10;'>"+result.otherDetails[i].createdOn+"</label>";
			
		    htmlString += "</td></tr></table>";

		    if(result.otherDetails[i].icon.indexOf('folder')<=0)
				  htmlString += "<span class='revHistBtnDocMgr'><input id='btn"+result.otherDetails[i].fileId+"' onclick='openRevisionHistory(this.id);' class='easyui-button' name='btn"+result.otherDetails[i].fileId+"'  type='button' value='Revision History' style='height:20px;'/></span>";
			htmlString += "</div>";	
			htmlString += "</div>";	
			//onmousedown='rightclick(event,this.id);'
			listHtml += "<tr id='"+result.otherDetails[i].fileId+"' class='viewLayerDocMgr'><td class='fileDocMgr' style='word-wrap: break-word'><img src='"+result.otherDetails[i].icon+"'/> <label style='color:blue;font-weight:bold;font-size:10;vertical-align:super;' id='lbl'"+result.otherDetails[i].fileId+"'> "+result.otherDetails[i].fileName+"</label></td>";
			listHtml += "<td class='descDocMgr' style='word-wrap: break-word'><label style='font-size:10;'>"+result.otherDetails[i].keywords+"</label></td>";
			listHtml += "<td class='createdOnDocMgr' style='word-wrap: break-word'><label style='font-size:10;'>"+result.otherDetails[i].createdOn+"</label></td>";
			listHtml += "<td class='createdByDocMgr' style='word-wrap: break-word'><label style='font-size:10;'>"+result.otherDetails[i].createdBy+"</label>";
			if((url.indexOf('download')>0 && result.otherDetails[i].icon.indexOf('folder')<=0) || url.indexOf('view')>0 && result.otherDetails[i].icon.indexOf('folder')<=0)
			{
				if(allowDownload != null && allowDownload != '' && allowDownload != ' ' && allowDownload == 'Y')
					listHtml += "<span class='listHtmlDlIcon' style='margin-left:1%;'><img id='img"+result.otherDetails[i].fileId+"' onclick='downloadFile(this.id);' title='Download' src='images/defaultIcons/DOWNLOAD_22_1.png'/></span>";
			}

			if(result.otherDetails[i].icon.indexOf('folder')<=0 &&  url.indexOf('view')<=0)	
		    {
				if(allowDel != null && allowDel != '' && allowDel != ' ' && allowDel == 'Y')
					listHtml += "<span class='listHtmlDlIcon' style='margin-left:1%;'><img id='del"+result.otherDetails[i].fileId+"' onclick='fileDel(this.id);' title='Delete' src='images/defaultIcons/Delete-24_1.png'/></span>";
				if(allowModify != null && allowModify != '' && allowModify != ' ' && allowModify == 'Y')
					listHtml += "<span class='listHtmlDlIcon' style='margin-left:1%;'><img id='mod"+result.otherDetails[i].fileId+"' onclick='fileModify(this.id);' title='Modify' src='images/defaultIcons/MODIFY-22.png'/></span>";
			}
			listHtml +="</td></tr>";




			var a=0;
			var b=0;
			iconHtml += "<span class='iconImgSpanDocMgr'>";			
			
			iconHtml += "<div class='sub-cntborder iconImgDivDocMgr' id='"+result.otherDetails[i].fileId+"'>";
			if((url.indexOf('download')>0 && result.otherDetails[i].icon.indexOf('folder')<=0) || url.indexOf('view')>0 && result.otherDetails[i].icon.indexOf('folder')<=0)
			{
				if(allowDownload != null && allowDownload != '' && allowDownload != ' ' && allowDownload == 'Y')
				{
					a = 1;
					iconHtml += "<div><span class='listHtmlDlIcon' style='margin-left:1%;'><img id='img"+result.otherDetails[i].fileId+"' onclick='downloadFile(this.id);' title='Download' src='images/defaultIcons/DOWNLOAD_22_1.png'/></span>";
				}
			}
			if(result.otherDetails[i].icon.indexOf('folder')<=0  &&  url.indexOf('view')<=0)	
		    {
				if((allowDel != null && allowDel != '' && allowDel != ' ' && allowDel == 'Y') || (allowModify != null && allowModify != '' && allowModify != ' ' && allowModify == 'Y'))
					b= 1;
			   
			   if(url.indexOf('download')<=0)
			   {
				   if(b==1)
					iconHtml += "<div>";
			   }
				if(allowDel != null && allowDel != '' && allowDel != ' ' && allowDel == 'Y')
				{
					
					iconHtml += "<span class='listHtmlDlIcon' style='margin-left:1%;'><img id='del"+result.otherDetails[i].fileId+"' onclick='fileDel(this.id);' title='Delete' src='images/defaultIcons/Delete-24_1.png'/></span>";
				}
				if(allowModify != null && allowModify != '' && allowModify != ' ' && allowModify == 'Y')
				{
					b= 1;
					iconHtml += "<span class='listHtmlDlIcon' style=''><img id='mod"+result.otherDetails[i].fileId+"' onclick='fileModify(this.id);' title='Modify' src='images/defaultIcons/MODIFY-22.png'/></span></div>";
				}
				//alert(iconHtml.substring(0,iconHtml.length-6));
				//if(b == 0)
					//iconHtml  = iconHtml.substring(0,iconHtml.length-6);
			}
			 if(url.indexOf('view')>0 )
			 {
				 if(a==1 || b==1)
				   iconHtml += "</div>";
			 }
			iconHtml += "<div><img src='"+result.otherDetails[i].icon+"' class='iconImgDocMgr'/></div>";
		
			iconHtml += "</div><div style='word-wrap: break-word;' class='iconimgFilename'><label style='color:blue;font-weight:bold;font-size:11;' id='lbl'"+result.otherDetails[i].fileId+"'>"+result.otherDetails[i].savedFileName+"</label></div></span>";

			
		}
		htmlString += "</div>";
		iconHtml += "</div>";
		listHtml += "</table></div>";		
		
		
		 if(viewsMode != null & viewsMode != ' ' && viewsMode != '')
		 {
			 if(viewsMode == 'thumbnail')
				 jQuery('#DocMgrFileLayer').html(htmlString);
			 else if(viewsMode == 'list')
				 jQuery('#DocMgrFileLayer').html(listHtml);
			 else if(viewsMode == 'icon')
				 jQuery('#DocMgrFileLayer').html(iconHtml);
		 }
		 else
			 jQuery('#DocMgrFileLayer').html(htmlString);
		 jQuery('#hdnThumbnail').html(htmlString);
		 jQuery('#hdnList').html(listHtml);
		 jQuery('#hdnIcon').html(iconHtml);
		 jQuery('#hdnSelectedFolder').val(id+','+levelNo+','+folderName+','+elemId+','+parentId);
		 jQuery('#hdnUserFolderRights').val(allowModify+','+allowDel+','+allowDownload+','+allowRights);
		 if(rightClkFlag != null & rightClkFlag != ' ' && rightClkFlag != '')
			 jQuery('#hdnRightClkOption').val(rightClkFlag);
		 else
			 jQuery('#hdnRightClkOption').val('');
	}
	function fileModify(id)
	{
		var fileId = id.substring(3);			
		var dataString = '?q=2&addMode=File&fileId='+fileId;
		 
		LoadPopUp("divAddFile","addDoc_input.dcm"+dataString, true,"600px","400px","10%","20%", "ADDFile_Callback","Modify File");
	}
	function fileDel(id)
	{
		    var fileId = id.substring(3);	
			var title = null;
		    var titleLayer = jQuery('#'+fileId).html();
		 
		    var titleArr = titleLayer.split('<b>');
		    
		    if(titleArr[1] != null & titleArr[1] != ' ' && titleArr[1] != '')
			{
				var title = titleArr[1].split('</b>');				
				if(title[0] != null & title[0] != ' ' && title[0] != '')
					title = title[0];
			}
			
		    var viewsMode = jQuery('#hdnViewsMode').val();
			var dataString = '?q=2&fileId='+fileId;
			var selFolderVal = jQuery('#hdnSelectedFolder').val();
			var userFolderRights = jQuery('#hdnUserFolderRights').val();
			var selFolderValArr = selFolderVal.split(',');
			var userFolderRightsArr = userFolderRights.split(',');
			if(selFolderValArr[0] != null && selFolderValArr[0] != '' && selFolderValArr[0] != ' ')
				dataString += '&id='+selFolderValArr[0];
			if(selFolderValArr[1] != null && selFolderValArr[1] != '' && selFolderValArr[1] != ' ')
				dataString += '&levelNo='+selFolderValArr[1];
			if(selFolderValArr[2] != null && selFolderValArr[2] != '' && selFolderValArr[2] != ' ')
				dataString += '&folderName='+selFolderValArr[2];
			if(selFolderValArr[3] != null && selFolderValArr[3] != '' && selFolderValArr[3] != ' ')
				dataString += '&elemId='+selFolderValArr[3];
			if(selFolderValArr[4] != null && selFolderValArr[4] != '' && selFolderValArr[4] != ' ')
				dataString += '&parentId='+selFolderValArr[4];
			if(userFolderRightsArr[0] != null && userFolderRightsArr[0] != '' && userFolderRightsArr[0] != ' ')
				dataString += '&allowModify='+userFolderRightsArr[0];
			if(userFolderRightsArr[1] != null && userFolderRightsArr[1] != '' && userFolderRightsArr[1] != ' ')
				dataString += '&allowDel='+userFolderRightsArr[1];
			if(userFolderRightsArr[2] != null && userFolderRightsArr[2] != '' && userFolderRightsArr[2] != ' ')
				dataString += '&allowDownload='+userFolderRightsArr[2];
			if(userFolderRightsArr[3] != null && userFolderRightsArr[3] != '' && userFolderRightsArr[3] != ' ')
				dataString += '&allowRights='+userFolderRightsArr[3];			
			if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
				dataString += '&viewsMode='+viewsMode;
			if(confirm("Do You want to delete this file : "+title) == true)
				processAjaxCalls('file_del.dcm',dataString,'DelFile','DelFileErr');
	}
	 function rightclick(evt,id) {
		    var titleLayer = jQuery('#'+id).html();
		  
		    var titleArr = titleLayer.split('<b>');
		    
		    if(titleArr[1] != null & titleArr[1] != ' ' && titleArr[1] != '')
			{
				var title = titleArr[1].split('</b>');				
				if(title[0] != null & title[0] != ' ' && title[0] != '')
					jQuery('#hdnTitle').val(title[0]);
			}
		   
		    var rightclick;
		    var rightClkOption =  jQuery('#hdnRightClkOption').val();
		    var e = window.event || evt;
		
		    if (e.which) rightclick = (e.which == 3);
		    else if (e.button) rightclick = (e.button == 2);
		   
		    if(rightclick)
			{
				if(rightClkOption != 'disable')
				{
		    		openRtClkMenu('#dlgModifyFile',e.pageX,e.pageY);
		    		jQuery('#hdnFileId').val(id);
				}
		    	
			}
		    else
		    	closeRtClkMenu('#dlgModifyFile');
			     // true or false, you can trap right click here by if comparison
		}

		function openRevisionHistory(id)
		{
		
			var dataString = '?q=2&addMode=RevHist&fileId='+id.substring(3);
			
			LoadPopUp("divAddRevisionHistory","addDoc_input.dcm"+dataString, true,"800px","400px","10%","10%", "RevHistory_Callback","Revision History");
		}
		function downloadFile(id)
		{
			var dataStr = '?q=2';
			if(id != null && id != '' && id != ' ')
				id = id.substring(3);
			dataStr += '&id='+id;
			window.open("download_file.dcm"+dataStr," ", "height=200, width=200");
			//processAjaxCalls('download_file.dcm',dataStr,'afterDownload','afterDownloadErr');
		}
		
		function searchFile()
		{
			//var searchTxt = jQuery( "#cmbtxtSearch").val();
			var searchTxt = jQuery( "#cmbtxtSearch").combobox('getText');
			
			var viewsMode = jQuery('#hdnViewsMode').val();
			var dataStr = '?q=2';
			if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
			{
				 dataStr += '&viewsMode='+viewsMode;
			}
			if(searchTxt != null && searchTxt != '' && searchTxt != ' ')
			{
				dataStr += '&keywords='+searchTxt;
				
				processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
			}
		}
		
		function openRtClkMenu(dlgId,posX,posY)
		{
			
		
	    	jQuery(dlgId).addClass('custom-popup');
	    	//jQuery('#nodeImage').css('display','none');
	        jQuery( dlgId ).show();	
	        //jQuery( dlgId.replace('dlg','title') ).css('width',w);
	        jQuery( dlgId ).css('left',posX);
	        
	        jQuery( dlgId ).css('border','1px solid #2364CA');
	        jQuery( dlgId ).css('z-index',100);     
	        jQuery( dlgId ).css('width',100);
	    	jQuery( dlgId ).css('height',100);
		}
 function closeRtClkMenu(dlgId)
 {
	 jQuery( dlgId ).hide();			
	 jQuery( dlgId ).removeClass('custom-popup');
	 jQuery( "#delFileList").removeClass('docMgrRightClkMenu');
	 jQuery( "#refFileList").removeClass('docMgrRightClkMenu');			
	 jQuery( "#modFileList").removeClass('docMgrRightClkMenu');
	 jQuery( "#delFileList").removeClass('docMgrRightClkMenuHover');
	 jQuery( "#refFileList").removeClass('docMgrRightClkMenuHover');			
	 jQuery( "#modFileList").removeClass('docMgrRightClkMenuHover');
	 jQuery('#hdnFileId').val('');
 }
		

function divUserRights_onClose()
{
	
	return true;
}




</script>
	<form id="frmDocMgr" name="frmDocMgr" style="width:100%">
	<div id='wrapper'>
	<div>
<!--	<input type="hidden"  id="txtNode" name="txtNode"/>-->
<!--	<input type="hidden"  id="txtformFld" name="txtformFld"/>-->
<!--	<input type="hidden"  id="txtelemId" name="txtelemId"/>-->
<!--	<input type="hidden"  id="hdnParentsNodes" name="hdnParentsNodes"/>-->
	<input type="hidden"  id="hdnTitle" name="hdnTitle"/>
	<input type="hidden"  id="hdnSelectedFolder" name="hdnSelectedFolder"/>
	<input type="hidden"  id="hdnUserFolderRights" name="hdnUserFolderRights"/>
	<input type="hidden"  id="hdnIdNode" name="hdnIdNode"/>
	<input type="hidden"  id="hdnIdParentNodes" name="hdnIdParentNodes"/>
	<input type="hidden" id="hdnRightClkOption" name="hdnRightClkOption"/>
	<input type="hidden"  id="hdnThumbnail" name="hdnThumbnail"/>
	<input type="hidden"  id="hdnList" name="hdnList"/>
	<input type="hidden" id="hdnIcon" name="hdnIcon"/>
	<input type="hidden" id="hdnViewsMode" name="hdnViewsMode"/>
<!--	<input type="hidden" id="hdnMstId" />-->
	<input type="hidden" id="hdnRightClkNode" />
	<input type="hidden" id="hdnSearchComboText" name="hdnSearchComboText"/>
	<input type="hidden" id="hdnEnterSearchText" name="hdnEnterSearchText"/>

	<div style="margin-top: 3px; width:90%;padding:5px 0px 5px 0px;" class="sub-cntborder searchLayerDocMgr" >
	<span style="margin-left:13%;" >
	<span class="searchBoxDocMgr">
		<label class="mandatory-lbl">Search File Using Keywords</label>
		<input type="text" id="cmbtxtSearch" name="cmbtxtSearch" class="easyui-combobox"  clear="false" hasDownArrow="false" style="width:400px;"/>
<!--	<input type="text" id="cmbtxtSearch" name="cmbtxtSearch" class="easyui-text"  style="width:260px;"/>-->
<!--		<div id="searchPanel"></div>-->
	</span>
	<span class="iconsDocMgr" style="position:absolute;">
		<span style="vertical-align: top;padding-left:4px; " >
		<img src="images/defaultIcons/SEARCHfolder.png" title="Search File" id="searchFiles" style="cursor:pointer;"/>
		</span>
		<span style="vertical-align: top;padding-left:2px; ">
		<img src="images/defaultIcons/advsearch.png" title="Advanced Search" id="Advsearch" style="margin-top:-4.5px;margin-left:-5px;cursor:pointer;"/>
		</span>
<!--		<span style="vertical-align: top;padding-left:2px;"><input type="button" id="Advsearch" class="easyui-button" value="Advanced Search" style="height:23px;color:#fff;"/></span>-->
	</span>
	</span>
	<span class="iconsDocMgr" style="position:absolute;right:8%">
		<span><img src="images/defaultIcons/docmgrthumbnail.png" title="Thumbnail View" id="thumbnailView"/></span><!--Thumbnil---1-32.png-->
		<span><img src="images/defaultIcons/docmgrlist.png" title="List View" id="listView"/></span><!--list-_2-24.png-->
		<span><img src="images/defaultIcons/docmgrtiles.png" title="Icon View" id="iconView"/></span><!--TILES-!-24.png-->
	</span>
	
	</div>
	<div style="margin-left:1%;margin-top: 3px;width:100%;">
		<div id="DocMgrLayer" style="float:left;width:40%;height:380px;overflow:auto;background-color:#ffffff" class="sub-cntborder">
			<div id="DocMgrMsg" style="width: 50%;display:none" align="center"></div>
			<div id="DocMgrSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
			<div id="DocMgrTree" class="demo" style="width: 50%;"></div>
	   </div>
	   <span>
	   <div id="DocMgrFileLayer" style="margin-left:20px;width:50%;height:380px;overflow:auto;" class="sub-cntborder">
	   </div>
	   </span>
	</div>
	
	<div id="dlgModifyFile" class="flPopUpBox">
	
	<ul>
		<li id="modFileList">Modify File</li>
	
		<li id="delFileList">Delete File</li>

	</ul>
	<input type="hidden" id="hdnFileId" text="hdnFileId"/> 
	</div>
	</div>
</div>
</form>