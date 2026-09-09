<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function(){	
    initialiseForm('frmMasterPlan');
    jQuery('#submitForm').val('frmMasterPlan');
	var hiddenUrl = jQuery('#hiddenUrl').val();
 
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	

	var factId = jQuery("#frmMasterPlan input[id='factory']").val();
	//alert(factId);
	
	var sectionId = jQuery("#frmMasterPlan input[id='section']").val();
	//alert(sectionId);
	var cellId = jQuery("#frmMasterPlan input[id='cell']").val();
	//alert(cellId);
  
	
	var machId = jQuery("#frmMasterPlan input[id='machine']").val();
	//alert(machId);
	 var flid = jQuery("#frmMasterPlan input[id='flid']").val();
	 
	
	
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	//alert(dataStr);
	loadFunctionalLocation("MspmfunLocation","functionalLoc.conf","MspmfunLocationValues","frmMasterPlan",dataStr);

	buildIndicatorTree();

	jQuery( "#legendButton" ).click(function() {
		var legendPos = jQuery('#hdnLegendPos').val();
		if(legendPos != null && legendPos != '' && legendPos != ' ')
		{
			if(screen.width <=1300)
			{
				jQuery("#lgnd-panel").css('right',-277);
				jQuery("#lgnd-panel").css('top',84);
			}
			else
			{
				jQuery("#lgnd-panel").css('right',50);
				jQuery("#lgnd-panel").css('top',90);
			}
		}
		else
		{
			if(screen.width <=1300)
			{
				//commented for designed correction
				
				//jQuery("#lgnd-panel").css('right','58');
				//jQuery("#lgnd-panel").css('right','9\9');
				//jQuery("#lgnd-panel").css('top','92');
			}
			else
			{
				jQuery("#lgnd-panel").css('right',50);
				jQuery("#lgnd-panel").css('top',83);
			}
		}
		jQuery("#lgnd-panel").slideToggle(200);
		
	});
	
	  
});
function buildIndicatorTree(){            //        process Tree
		var lineId = jQuery("#frmMasterPlan input[id='cell']").val();
		
		var url = jQuery('#hiddenUrl').val();
		var title=jQuery("#txtMspiTitle").val();
		 var Flid = jQuery("#frmMasterPlan input[id='flid']").val();
		//alert( url.substring(url.indexOf('?')+1,url.length));
		if(url != null && url!= '' && url != ' ')
			url = url.indexOf('?') > 0 ? url.substring(url.indexOf('?')+1,url.length):"";
			
		
		var masterId =jQuery('#txtMspiKeyid').val();//alert(masterId );	
		url += '&Flid='+Flid+'&title='+title+"&masterId="+masterId;
		//alert(url);	
	   //processTree( jQuery("#mstPlanTreeComponent"),'loadval.conf','searchnode.conf?lineId=' +lineId+'&urlMode='+url);
	   processTree( jQuery("#mstPlanTreeComponent"),'loadval.conf?'+url,'searchnode.conf?'+url);
	   
	   /*jQuery("#mstPlanTreeComponent").bind("select_node.jstree", function (e, data) {			 
			jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));
 			jQuery('#hdnParentId').val(data.rslt.obj.attr("parentId"));	 			
 			jQuery("#hdnMstId").val(data.rslt.obj.attr("id"));	 			
	   });
	   */
	  var fluSearchStr = '';
	
	jQuery("#mstPlanTreeComponent").bind("open_node.jstree", function (event, data) { 			
	    if((data.inst._get_parent(data.rslt.obj)).length) { 
	        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
	    } 
	});				

	jQuery("#btnSave").click(function(){
		saveForm('frmMasterPlan','addactivityMaster_save.conf');

		
	});		

	jQuery("#mstPlanTreeComponent").bind("select_node.jstree", function (e, data) {
		jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));
		jQuery('#hdnParentId').val(data.rslt.obj.attr("parentId"));	 			
		jQuery("#hdnMstId").val(data.rslt.obj.attr("id"));
		var lvlNo = data.rslt.obj.attr("levelno"); 
		//alert(lvlNo);

		 var Flid = jQuery("#frmMasterPlan input[id='flid']").val();
		//var lineId = jQuery("#frmMasterPlan input[id='cell']").val();			
		if(Flid != null && Flid != '' && Flid != ' ')
		{
			//alert
			if(lvlNo == '0')
				addToSelectedNode(data,"Add Category","Y");
			else if(lvlNo == '1')
				addToSelectedNode(data,"Add Sub Category","Y");
			if(lvlNo == '2')
				addToSelectedNode(data,"Add Activity","Y");
		}
		//else
			//alert('Select JH');
		
		//processAjaxCalls("get_title.conf",'?q=2&cellId='+cellId,'getTitleSuccess','getTitleFailure');
		/*jQuery('#mstPlanAddLayer').html('');
		if(data.rslt.obj.attr("levelno") == '2')
		{ 		
			jQuery('#mstPlanActivityLayer').show();
			var levelNo = data.rslt.obj.attr('LevelNo');
			var parentNodes = data.rslt.obj.attr('parentId');
			var dispCode = data.rslt.obj.attr('displayCode');
			var id = data.rslt.obj.attr('id');
			var pillar = data.rslt.obj.attr('elementtype');

			var factId =  jQuery("#frmMasterPlan input[id='factory']").val();
			var sectId =  jQuery("#frmMasterPlan input[id='section']").val();
			var cellId =  jQuery("#frmMasterPlan input[id='cell']").val();
			
			var dataStr = "?q=2&cellId="+cellId+"&parentId="+id+"&pillar="+pillar;		
		 	processGridnew('showActivity_input.conf',dataStr,"showActivityGrid","","","showActivityDblClick","","showActivityGridLoad","showActivityGridError");
		 	
		 
			var dataString = '?q=2&levelNo='+levelNo+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
				dataString += '&factId='+factId+'&sectId='+sectId+'&cellId='+cellId+'&pillar='+pillar+'&saveMode='+escape('Add Activity');
			LoadForm("mstPlanAddLayer","prevmstPlanAddLayer","addactivity_input.conf"+dataString,"","mstPlan","mstPlanErr");
			//LoadPopUp("divAddNode","addactivity_input.conf"+dataString, true,"330px","304px","0px","20%", "ADDActivity_Callback",lbl);	
		}
		else
		{
			if(jQuery('#mstPlanActivityLayer').hasClass('sub-cntbor') == true)
				jQuery('#mstPlanActivityLayer').removeClass('sub-cntbor');	
			jQuery('#mstPlanActivityLayer').hide();
		}*/
		
 }); 	 
	jQuery("#mstPlanTreeComponent").bind("loaded.jstree", function (event, data) {	 	 	 
	 	 var title = jQuery("#frmMasterPlan li[levelno='0']").attr('displaycode');  	
	 	 var key = jQuery("#frmMasterPlan li[levelno='0']").attr('displayId');  
	 	
	 	 if(title != null && title != '' && title != ' ')
	 	 { 	
	 	 	 if(title.indexOf('Master')<0 && title.indexOf('Plan')<0 )
	 	 	 {
	 	 	 	//jQuery('#txtMspiTitle').val(title);
	 	 	  // readOnlyFields('txtMspiTitle');		
	 	 	 }
	 	 	else
	 	 	 {
	 	 		//jQuery('#txtMspiTitle').val('');
 	 		 enableFields('txtMspiTitle');	
	 	 	 }
	 	 }
	 	
	 	if(key != null && key != '' && key != ' ')
 		jQuery('#txtMasterId').val(key);
	 	else
	 		jQuery('#txtMasterId').val('');
 	 		  	
 	 	
 	 
	 	  
	  var searchNode = "";
	  var name = jQuery("#txtsearchIndName").val();
	  var id = jQuery("#txtsearchSCId").val();		 
	  var level = jQuery("#txtsearchSelLevel").val();
	  if(level == null || level == '' || level == ' ')	
			  level = '0';
	  if((name != null && name != '' && name != ' ')&&(id != null && id != '' && id != ' '))
	  {
		  searchNode = name + ":"+id+ ":"+level;
		  jQuery("#mstPlanTreeComponent").jstree("search",searchNode);
	  }				
});
	jQuery("#mstPlanTreeComponent").bind("search.jstree", function (e, data) {
 });
	

}
     
   


function loadval_searchCallBack(result){	
	// jQuery('.search_parent_flu-s').removeClass('search_parent_flu-s');
	/* if(result != null){
  	  
		  var str = result.toString();
		 		
		  var idS = str.split(',');
		  var parentIds ="";
	      if( str.indexOf(",") > 0)
	      {
	      	  parentIds = str.substring(str.indexOf(",")+1 , str.lastIndexOf(",") < 0 ?str.length:str.lastIndexOf(",")+1);
	          parentIds = parentIds.replace(/#/g," > ul > li ").replace(/[0-9,A-Z]/g,' ').replace(/,/g,' ');
	      }
	  
         str = str.substring( str.lastIndexOf(",") > 0 ? str.lastIndexOf(",")+1:0);        
         parentIds += str.replace(/#/g," > ul > li[id=");
         parentIds += ']';
       
   		 jQuery("#mstPlanTreeComponent " + parentIds).addClass("search_parent_flu-s");
   	
   		if(jQuery('#txtsearchSelLevel').val() == '3')
   	   	{   	   	   
   			setTimeout(function() {addToSelectedNode('','Add Activity','Y','Y',idS[idS.length-1].substring(1));},1250);
   		 	setTimeout(function() { jQuery("#frmMasterPlan li[id="+jQuery('#txtsearchSCId').val()+"]  > a").addClass('jstree-search');},1250);
   		}
   		else
   			setTimeout(function() { jQuery("#frmMasterPlan li[displaycode="+jQuery('#txtsearchIndName').val()+"]  > a").addClass('jstree-search');},1250);
   	 
	}   
	*/ 
}
function frmMasterPlan_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
	jQuery('#mstPlanActivityLayer').hide();
	jQuery('#mstPlanAddLayer').hide();
	var newBtn = jQuery('#hdnMode').val(); 
	var lineId = jQuery("#frmMasterPlan input[id='cell']").val();		
	//var lineId = keyIds.cellId;
	//alert(lineId);
	var url = jQuery('#hiddenUrl').val();
	var title=jQuery("#txtMspiTitle").val();
	//alert(title);
	
	if(url != null && url!= '' && url != ' ')
		url = url.indexOf('_')>0?url.substring(url.indexOf('_')+1, url.indexOf('_')>0?url.indexOf('.'):url.length):url;
	   /*  if("create"==newBtn)
		     {
			//lineId = " ";
		     }
	   else
		     {

	    	// processTree( jQuery("#mstPlanTreeComponent"),'loadval.conf?q=2&lineId='+lineId+'&urlMode='+url+'&title='+title,'searchnode.conf?q=2&lineId='+lineId+'&urlMode='+url);
		     }*/
	    
    
  //  processTree( jQuery("#mstPlanTreeComponent"),'loadval.conf?q=2&lineId='+lineId+'&urlMode='+url+'&title='+title,'searchnode.conf?q=2&lineId='+lineId+'&urlMode='+url);
		    
 	//processTree( jQuery("#mstPlanTreeComponent"),'loadval.conf?q=2&lineId=' +lineId+'&urlMode='+url,'searchnode.conf?q=2&lineId=' +lineId+'&urlMode='+url);
 	
 	//sjQuery('#chbEnableDate').attr('checked',false);
 	//enableFields('changeTitle');
	//changeTitle();
}
function showActivityGridLoad()
{
	jQuery('#mstPlanActivityLayer').addClass('sub-cntbor');	
	 if(screen.width <= 1300)		
	 {	 
		 //jQuery( "#showActivityGrid" ).setGridWidth(410);
	 }
}
function showActivityDblClick(id)
{
	 
	var rowData = jQuery("#showActivityGrid").jqGrid('getRowData',id);
	var levelNo = rowData.actLevel;
	var parentNodes = rowData.actParent;
	var dispCode = rowData.actName;
	var id = rowData.actKeyId;
	var pillar = rowData.actPillar;
	var masterkeyid=jQuery("txtMspiKeyid").val();//TTT
	//alert(masterkeyid);
	var title = jQuery('#txtMspiTitle').val();
	var key = jQuery('#txtMasterId').val();
	 var Flid = jQuery("#frmMasterPlan input[id='flid']").val();
	 //alert(flid);
	 
	var lbl = 'Modify Activity';
	if(levelNo == 1)
		lbl = 'Modify Category';
	else if(levelNo == 2)
		lbl = 'Modify Sub Category';
	
	var factId = jQuery("#frmMasterPlan input[id='factory']").val();
	var sectId = jQuery("#frmMasterPlan input[id='section']").val();
	var cellId = jQuery("#frmMasterPlan input[id='cell']").val();
	
	var dataString = '?q=2&levelNo='+levelNo+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	dataString += '&factId='+factId+'&sectId='+sectId+'&Flid='+Flid+'&pillar='+pillar+'&title='+title+'&key='+key+'&saveMode='+escape(lbl)+"&masterkeyid="+masterkeyid;

	//alert(dataString);
	 
	LoadForm("mstPlanAddLayer","prevmstPlanAddLayer","addactivity_input.conf"+dataString,"","mstPlan","mstPlanErr");
}
function customMenu(node) {	
	//jQuery('#mstPlanAddLayer').show();
	/*jQuery('#mstPlanActivityLayer').hide();	
	if(jQuery('#mstPlanActivityLayer').hasClass('sub-cntbor') == true)
	{
		jQuery('#mstPlanAddLayer').html('');
	}*/
				
   var x = jQuery.jstree._focused()._get_node(node).attr('id');
   var elementId = jQuery.jstree._focused()._get_node(node).attr('elementId');  
   var parId = jQuery.jstree._focused()._get_node(node).attr('parentId');  
   var id = jQuery.jstree._focused()._get_node(node).attr('id'); 
   var levelNo = jQuery.jstree._focused()._get_node(node).attr('levelNo'); 
	var lineId = jQuery("#frmMasterPlan input[id='cell']").val();
   var selId = jQuery('#hdnMstId').val();
  
   if(selId != null && selId != '' && selId != ' ')
   	jQuery("#"+selId+" a").removeClass('jstree-clicked');
  
   var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');   
   var prevNode = jQuery("#hdnRightClkNode").val(); 	
   
   if(prevNode != null && prevNode != '' && prevNode != ' ')
	   jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
   jQuery("#hdnMstId").val( jQuery.jstree._focused()._get_node(node).attr('originalid'));  
   jQuery("#hdnRightClkNode").val(x);
   jQuery("li[id="+x+"]  > a").addClass('jstree-clicked');
 
   /*if(lineId != null && lineId != '' && lineId != ' ')
   {
	   if(levelNo=='0'){		   
		   var items = {
			 	addItem: { // The "add" menu item
			 		label: "Add Category",
		            action: function () {addNode(node,'Add Category','Y');}
		        }
		   };
	   }
	   else{	 
		   if(parseInt(levelNo.trim())<=2)
		   {		  
			   	if(levelNo == "1")
				{
				 	var items = {
					 	addItem: { // The "add" menu item
					 		label: "Add Sub Category",
				            action: function () {addNode(node,"Add Sub Category","Y");}
				        }/*,
				        modifyItem: { // The "add" menu item
					 		label: "Modify Category",
				            action: function () {addNode(node,"Modify Category");}
				        },
				        deleteItem: { // The "delete" menu item
					 		label: "Delete Category",
				            action: function () {deleteNode(node);}
				        }*/
				 	/*sel};
				}
			   	else
				{
			   		var items = {
						 	addItem: { // The "add" menu item
						 		label: "Add Activity",
					            action: function () {addNode(node,"Add Activity","Y");}
					        },
					        /* modifyItem: { // The "add" menu item
						 		label: "Modify Sub Category",
					            action: function () {addNode(node,"Modify Sub Category");}
					        },
					        deleteItem: { // The "delete" menu item
						 		label: "Delete Sub Category",
					            action: function () {deleteNode(node);}
					        },*/
					     /*sel   assignItem: { // The "delete" menu item
						 	  /*sel	label: "Assign Parent",
					            action: function () {assignParent(node);}
					        }
					 	};
				}
		   }
		   else
		   {
			   var items = {
					   modifyItem: { // The "add" menu item
					 		label: "Modify Activity",
				            action: function () {addNode(node,"Modify Activity");}
				        },
				        deleteItem: { // The "delete" menu item
					 		label: "Delete Activity",
				            action: function () {deleteNode(node);}
				        },
				       assignItem: { // The "delete" menu item
					 		label: "Assign Parent",
				            action: function () {assignParent(node);}
				        }
			   };
		   }
	   }	
   }
   else
	   alert('Select Line');
   
   return items;*/
}
function mstPlan()
{	
	
}

function addToSelectedNode(data,lbl,showGrid,fromSearch,searchParent)
{	
	jQuery('#mstPlanActivityLayer').show();             //TTT
	var levelNo = '';//jQuery.jstree._focused()._get_node(obj).attr('LevelNo');			
	var parentNodes = '';//jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = '';//jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = '';//jQuery.jstree._focused()._get_node(obj).attr('id');
	var pillar = '';//jQuery.jstree._focused()._get_node(obj).attr('elementtype');
	var title = jQuery('#txtMspiTitle').val();
	var key = jQuery('#txtMasterId').val();
	if(fromSearch != null && fromSearch != ' ' && fromSearch != '' && fromSearch == 'Y')
	{
		var url = jQuery('#hiddenUrl').val();
		if(url != null && url!= '' && url != ' ')
			url = url.indexOf('_')>0?url.substring(url.indexOf('_')+1, url.indexOf('_')>0?url.indexOf('.'):url.length):url;
		levelNo = '2';
		parentNodes = searchParent;
		dispCode = jQuery('#txtsearchIndName').val();
		pillar = url;
		id =  jQuery('#txtsearchSCId').val();
	}
	else
	{
		levelNo = data.rslt.obj.attr('LevelNo');			
		parentNodes = data.rslt.obj.attr('parentId');
		dispCode = data.rslt.obj.attr('displayCode');
		id = data.rslt.obj.attr('id');
		pillar = data.rslt.obj.attr('elementtype');
	}
/*	if(levelNo != "2")	
		jQuery('#mstPlanActivityLayer').hide();
	else
	{
		 if(lbl.indexOf('Activity')>=0)
			 jQuery('#mstPlanActivityLayer').show();
		 else
			 jQuery('#mstPlanActivityLayer').hide();
	}*/
	//alert(id);	
	var factId = jQuery("#frmMasterPlan input[id='factory']").val();
	var sectId = jQuery("#frmMasterPlan input[id='section']").val();
	var Flid = jQuery("#frmMasterPlan input[id='flid']").val();
	var cellId = jQuery("#frmMasterPlan input[id='cell']").val();
	var title = jQuery("#txtMspiTitle").val();

	if(showGrid != null && showGrid != ' ' && showGrid != '' && showGrid == 'Y')
	{
		var dataStr = "?q=2&Flid="+Flid+"&parentId="+id+"&pillar="+pillar+"&title="+title;		
	 	processGridnew('showActivity_input.conf',dataStr,"showActivityGrid","showActivityPager","","showActivityDblClick","","showActivityGridLoad","showActivityGridError");
	}
	var dataString = '?q=2&levelNo='+levelNo+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	dataString += '&factId='+factId+'&sectId='+sectId+'&Flid='+Flid+'&pillar='+pillar+'&title='+title+'&key='+key+'&saveMode='+escape(lbl);
	LoadForm("mstPlanAddLayer","prevmstPlanAddLayer","addactivity_input.conf"+dataString,"","mstPlan","mstPlanErr");
	//LoadPopUp("divAddNode","addactivity_input.conf"+dataString, true,"330px","304px","0px","20%", "ADDActivity_Callback",lbl);	
}
function addNode(obj,lbl,showGrid,fromSearch,searchParent)
{	
	jQuery('#mstPlanActivityLayer').show();
	var levelNo = '';//jQuery.jstree._focused()._get_node(obj).attr('LevelNo');			
	var parentNodes = '';//jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = '';//jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = '';//jQuery.jstree._focused()._get_node(obj).attr('id');
	var pillar = '';//jQuery.jstree._focused()._get_node(obj).attr('elementtype');
	
	if(fromSearch != null && fromSearch != ' ' && fromSearch != '' && fromSearch == 'Y')
	{
		var url = jQuery('#hiddenUrl').val();
		if(url != null && url!= '' && url != ' ')
			url = url.indexOf('_')>0?url.substring(url.indexOf('_')+1, url.indexOf('_')>0?url.indexOf('.'):url.length):url;
		levelNo = '2';
		parentNodes = searchParent;
		dispCode = jQuery('#txtsearchIndName').val();
		pillar = url;
		id =  jQuery('#txtsearchSCId').val();
	}
	else
	{
		levelNo = jQuery.jstree._focused()._get_node(obj).attr('LevelNo');			
		parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
		dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
		id = jQuery.jstree._focused()._get_node(obj).attr('id');
		pillar = jQuery.jstree._focused()._get_node(obj).attr('elementtype');
	}
/*	if(levelNo != "2")	
		jQuery('#mstPlanActivityLayer').hide();
	else
	{
		 if(lbl.indexOf('Activity')>=0)
			 jQuery('#mstPlanActivityLayer').show();
		 else
			 jQuery('#mstPlanActivityLayer').hide();
	}*/
	
	var factId = jQuery("#frmMasterPlan input[id='factory']").val();
	var sectId = jQuery("#frmMasterPlan input[id='section']").val();
	var cellId = jQuery("#frmMasterPlan input[id='cell']").val();
//	alert(cellId);
	 var Flid = jQuery("#frmMasterPlan input[id='flid']").val();
     var title=jQuery("#txtMspiTitle").val();
     //alert(title);
	if(showGrid != null && showGrid != ' ' && showGrid != '' && showGrid == 'Y')
	{
		var dataStr = "?q=2&Flid="+Flid+"&parentId="+id+"&pillar="+pillar+"&title="+title;		
	 	processGridnew('showActivity_input.conf',dataStr,"showActivityGrid","showActivityPager","","showActivityDblClick","","showActivityGridLoad","showActivityGridError");
	}
	var dataString = '?q=2&levelNo='+levelNo+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	dataString += '&factId='+factId+'&sectId='+sectId+'&cellId='+cellId+'&pillar='+pillar+'&saveMode='+escape(lbl);
	LoadForm("mstPlanAddLayer","prevmstPlanAddLayer","addactivity_input.conf"+dataString,"","mstPlan","mstPlanErr");
	//LoadPopUp("divAddNode","addactivity_input.conf"+dataString, true,"330px","304px","0px","20%", "ADDActivity_Callback",lbl);	
}
function assignParent(obj)
{
	jQuery("#refreshGridFlag").val('');
	jQuery('#mstPlanActivityLayer').hide();
	var levelNo = jQuery.jstree._focused()._get_node(obj).attr('LevelNo');
	var parentNodes = jQuery.jstree._focused()._get_node(obj).attr('parentId');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	
	var factId = jQuery('#factory').val();
	var sectId = jQuery('#section').val();
	var cellId = jQuery('#cell').val();
	var dataString = '?q=2&levelNo='+levelNo+'&parentId='+parentNodes+'&displayCode='+escape(dispCode)+'&nodeId='+id;
	dataString += '&factId='+factId+'&sectId='+sectId+'&cellId='+cellId;
	LoadForm("mstPlanAddLayer","prevmstPlanAddLayer","assignParent_input.conf"+dataString,"","mstPlan","mstPlanErr");
	//LoadPopUp("divAssignParent","assignParent_input.conf"+dataString, true,"330px","304px","0px","20%", "AssignParent_Callback","Assign Parent");	
}
function deleteNode(obj)
{
	jQuery("#refreshGridFlag").val('');
	var id = jQuery.jstree._focused()._get_node(obj).attr('id');
	var dispCode = jQuery.jstree._focused()._get_node(obj).attr('displayCode');	
	var cellId = jQuery('#cell').val();
    if(confirm("Do You Want To Delete?") == true)
	{
		processAjaxCalls("node_del.conf",'?q=2&originalId='+id+'&cellId='+cellId,'delIndicatorsSuccess','delIndicatorsFailure');
	}  
}  
function frmMPActivities_successsCallback(result)
{
	afterChanges(result,'divAddNode','mstPlanAddLayer');
}
function frmMPAddChild_successsCallback(result)
{	
	var title = jQuery('#planHead').html();	
	if(title != null  &&  title !="" && title !=" ")
	{
		//if(title.indexOf('Activity')>=0)
		//{
			//if(title.indexOf('Modify')>=0 || title.indexOf('Add')>=0)
			//{
				jQuery('#hdnClearPrevSession').val('');
				jQuery("#showActivityGrid").trigger("reloadGrid");	
				if(title.indexOf('Modify')>=0)
				{
					if(result.successData.parent != null && result.successData.parent != null != '' && result.successData.parent != null != ' ')
					{
						var level = '2';
						if(title.indexOf('Category')>=0 && title.indexOf('Sub')<0)
							level = '0';
						else if(title.indexOf('Sub')>=0)
							level = '1';
						jQuery('#planHead').html(title.replace('Add','Modify'));
						jQuery('#hdnMsidParentid').val(result.successData.parent);
						jQuery('#hdnMsidLevel').val(level);
						jQuery('#btnDelActivity').hide();						 
					}
				}
			//}
		//}
	}
	var masterKeyid = result.masterKeyid;
	if(masterKeyid != null  &&  masterKeyid !="" && masterKeyid !=" ")
	{
		jQuery("#hdnMspiKeyid").val(masterKeyid);
		jQuery("#txtMasterId").val(masterKeyid);
	}
	jQuery("#refreshGridFlag").val('');
	afterChanges(result,'divAddNode','mstPlanAddLayer');
}
function frmMPAssignPnt_successsCallback(result)
{	
	closePopUpDialoge('divAssignParent');
	refTree(result.successData.parent);	
	setTimeout(function() {openNode('mstPlanTreeComponent',result.successData.parent);},1250);
}
function delIndicatorsSuccess(result)
{
	var title = jQuery('#planHead').html();	
	if(title != null  &&  title !="" && title !=" ")
	{
		//if(title.indexOf('Activity')>=0)
		//{
			jQuery('#hdnClearPrevSession').val('');
			if(title.indexOf('Modify')>=0)
			{
				jQuery("#showActivityGrid").trigger("reloadGrid");
				jQuery('#txtMsidName').val('');
				jQuery('#txtMsidCode').val('');
				jQuery('#txtMsidSortno').val('');
				jQuery('#txtMsidRemarks').val('');
				if(result.successData.parent != null && result.successData.parent != null != '' && result.successData.parent != null != ' ')
				{
					var level = '2';
					if(title.indexOf('Category')>=0 && title.indexOf('Sub')<0)
						level = '0';
					else if(title.indexOf('Sub')>=0)
						level = '1';
					jQuery('#planHead').html(title.replace('Add','Modify'));
					jQuery('#btnDelActivity').hide();
					jQuery('#hdnMsidParentid').val(result.successData.parent);
					jQuery('#hdnMsidLevel').val(level);
				}	
				/*if(jQuery('#mstPlanActivityLayer').hasClass('sub-cntbor') == true)
					jQuery('#mstPlanActivityLayer').removeClass('sub-cntbor');	
				jQuery('#mstPlanActivityLayer').hide();*/
			}
		//}
	}
	refreshTree();
	alert(result.successData.msg);
}

function openNode(treeId,nodeId)
{		
	if(jQuery("#"+treeId).jstree("is_open", jQuery('#'+nodeId)) == false)
	{
		jQuery("#"+treeId).jstree("open_node",jQuery('#'+nodeId));
	}	
}

function refreshTree()
{
	var tree = jQuery.jstree._reference("#mstPlanTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}

function refTree(node)
{
	//alert(node);
	var tree = jQuery.jstree._reference("#mstPlanTreeComponent");
	var currentNode = tree._get_node(node, true);	
	var parentNode = tree._get_parent(currentNode);
	tree.refresh();
}

function afterChanges(result,divId,frmId)
{
	var parentId = result.successData.parent;

	var title = jQuery('#planHead').html();	
	/*if(frmId != null || frmId != '' || frmId != ' ')
	{
		var title = jQuery('#planHead').html();	
		if(title != null  &&  title !="" && title !=" ")
		{
			if(title.indexOf('Activity')>=0)
			{*/
				if(title.indexOf('Activity')>=0)
				{
					jQuery('#dteMilestnFromDt').datebox('clear');
					jQuery('#dteMilestnToDt').datebox('clear');
					readOnlyFields('dteMilestnFromDt');		
					readOnlyFields('dteMilestnToDt');	
					jQuery('#chbEnableDate').attr('checked',false);
				}
				jQuery('#txtMsidName').val('');
				jQuery('#txtMsidCode').val('');
				jQuery('#txtMsidSortno').val('');
				jQuery('#txtMsidRemarks').val('');
			/*}
			else
				jQuery('#'+frmId).html('');
		}
	}*/
	if(result != null)
	{
		closePopUpDialoge(divId);
		
		  if(jQuery("#mstPlanTreeComponent").jstree("is_open",  jQuery('#'+parentId)) == false)
		  {
			   var isLeaf = jQuery("#mstPlanTreeComponent").jstree("is_leaf",  jQuery('#'+parentId));
				
				if(isLeaf == true)
				{
					jQuery("#mstPlanTreeComponent").jstree("load_node",  jQuery('#'+parentId));
					setTimeout(function() {jQuery("#mstPlanTreeComponent").jstree("open_node",  jQuery('#'+parentId));},1250);
				}
				else
		  			jQuery("#mstPlanTreeComponent").jstree("open_node",  jQuery('#'+parentId));
		  }
		  else
		  	refreshNode("mstPlanTreeComponent",parentId);
	}
}
function changeTitle(){
	var oldtitle = jQuery('#MP001').attr('displaycode');
	var title = jQuery('#txtMspiTitle').val(); 
	jQuery('#MP001').attr('displaycode',title);
	var oldHtml = jQuery('#MP001').html();
	jQuery('#MP001').html(' ');
	if(title.trim().length>0)
	jQuery('#MP001').html(oldHtml.replace(oldtitle,title));
	   
}


function frmMasterPlan_successsCallback(result){
	
	jQuery('#txtMspiKeyid').val(result.successData.masterId);
	buildIndicatorTree();	
}

</script>
 <form id="frmMasterPlan" name="frmMasterPlan">
 <div style="margin-left:60px;margin-top:10px;" >
 
    
	<input type="hidden" id="txtKinkPillarid" name="txtKinkPillarid"  value="${requestScope.pillarid}"/>
	<input type="hidden" id="hdnLevelNo" />
	<input type="hidden" id="hdnParentId" name="hdnParentId"/>
	<input type="hidden" id="hdnMstId" />
 	<input type="hidden" id="hdnRightClkNode" />
 	<input type="hidden" id="hdnLegendPos" />
 	<input type="hidden" id="hdnSearchId" />
 	<input type="hidden" id="hdnSearchName" />
 	<input type="hidden" id="txtsearchSCId" name="txtsearchSCId"  value="${requestScope.scId}"/>
 	<input type="hidden" id="txtsearchIndId" name="txtsearchIndId"  value="${requestScope.indId}"/>
 	<input type="hidden" id="txtsearchIndName" name="txtsearchIndName"  value="${requestScope.indName}"/>
 	<input type="hidden" id="txtsearchSelLevel" name="txtsearchSelLevel"  value="${requestScope.selLevel}"/>
 	<input type="hidden" id="txtMasterId" name="txtMasterId" />
 
	<div style="margin-left:1%">
	
	 <label style="color:dark brown;font-weight: bold;margin-left: 150px;position: absolute;">Add Category/Subcategory/Activity</label>
		<div  class="easyui-paddingbfpx" style="padding-bottom:5px;">
			  
			<table><tr><td>
                 <div  id="frmMasterPlanFuntKeyIds">
					<input type="hidden" id="location" name="txtLocationid" value=""/>
					<input type="hidden" id="factory" name="txtFactoryid" value="" / >
					<input type="hidden" id="section" name="txtMspiSectionid" value="${requestScope.newMspTlIndicatorsMst.mspiSectionid}" / >
					<input type="hidden" id="cell" name="txtMspiCellid" value="${requestScope.newMspTlIndicatorsMst.mspiCellid}"/ >
					<input type="hidden" id="machine" name="txtMachine" value="" / >
				   <input type="hidden" id="flid" name="txtMspiFlid"  value="${requestScope.existMspTlIndicatorsMst.mspiFlid}""></input>  
				    
				   <div id="MspmfunLocation" style="width:445px;"></div>		
				<div class="clear" style=" height : 0px;"></div>

				</div>		
			</td>
			
			
			
<!--			<td valign="top">-->
		<td style="padding-left: 380px;margin-top: 10px">
		<div style="padding-top: -0px">
		
				<label class="mandatory-lbl">Title </label></div>
				
			    
					<input type="text" class="easyui-text" id ="txtMspiTitle" name="txtMspiTitle" style="width:150px;margin-top: 15px" value="${requestScope.newMspTlIndicatorsMst.mspiTitle}"/>
					
					<span style="position:absolute;top:29px;padding-left:10px;padding-left:10px\9;">
					<input	type="button" class="easyui-button" id="btnSave"name="btnSave" value="Save"  style=" width : 35px;margin-top: 5px"/>
					</span>
					
					 <table>
					<tr>
					<td><span id="err_txtMspiTitle" class="tpm-errormsg"></span>
					<span id="err_btnSave" class="tpm-errormsg"></span>
					<span id="err_legendButton" class="tpm-errormsg"></span>
					</td>
					</tr>
					</table>
</td>
<td>
             <div style="float:right;width:60%;margin-top: 2px;padding-left:73px">
			         <input type="button" class="easyui-button" id="legendButton" value="Legend" style="height:25px;margin-top: 15px"/>
			         <span id=" " class="tpm-errormsg"></span>
			    </div>
				 
				
			</td>
			
			</tr></table>
<!--			<td valign="bottom">-->
<!--				<input type="button" class="easyui-button" id="legendButton" value="Legend" style="float:right;height:25px;"/>-->
<!--			</td>-->
		</div>
		
		<div id="mstPlanTreeLayer" style="float:left;width:30%;height:80%;padding-top:-0px;overflow:auto;background-color:#ffffff;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;" class="sub-cntborder">
			<div id="mstPlanTreeMsg" style="width: 50%;display:none" align="center"></div>
			<div></div>
<!--			<div id="mstPlanTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>-->
			<div id="mstPlanTreeComponent" class="demo"    style="width: 50%;height:250px;height:360px\9;text-transform: uppercase;" ></div>
		</div>	
<!--	<div style="float:right;width:11%;">-->
<!--		<input type="button" class="easyui-button" id="legendButton" value="Legend" style="height:25px;"/>-->
<!--	</div>-->
		<div id="mstPlanActivityLayer" style="float:left;width:40%;height:86%;overflow:auto;">
			<div  id="activityLayer" style="padding-left:1px;margin-top:-4;float:left;">			
				<table id="showActivityGrid" style="float: left;"></table>
				<div id="showActivityPager"></div>
			</div>
		</div>
		<span>
			<div id="prevmstPlanAddLayer"></div>
			<div id="mstPlanAddLayer" style="float:left;width:25%;height:86%;margin-top:-3;" ></div>
		</span>
	</div>	
	
	 <div id="lgnd-panel" style="position:absolute;right:2px;top:87px;right:90px\9;top:90px\9;">
		<ul>
			<li><a href="#"><img src="images/FnLocn/category.png"/><span>Category</span></a></li>
			<li><a href="#"><img src="images/FnLocn/subcategory.png"/><span>Sub Category</span></a></li>
<!--			<li><a href="#"><img src="images/FnLocn/indicator.png"/><span>Activity</span></a></li>			-->
		</ul>
  	 </div><!-- /settings-panel -->
  	 </div>
  	 <input type="hidden" id="hdnMode" name="hdnMode"  value="${requestScope.mode}"></input>
  	  <input type="hidden" id="txtMspiKeyid" name="txtMspiKeyid"  value="${requestScope.newMspTlIndicatorsMst.mspiKeyid}"></input>
  	  <input type="hidden" id="txtMspiPillar" name="txtMspiPillar"  value="${requestScope.newMspTlIndicatorsMst.mspiPillar}"></input>
</form>

