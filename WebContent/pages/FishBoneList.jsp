
<!--Created Roopa on 10-Aug-2012 -->
<!--<script type="text/javascript" src="js/jsTree/jquery.jstree2.js"></script>-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	//alert(1);

    //alert(" Element Id :: "+jQuery('#elementId').val());

	initialiseForm('frmFindNode');	
	
	//alert(jQuery('#txtFismrefDoctype').val());
	if(jQuery('#txtFismrefDoctype').val()!="WM")
		jQuery('#submitForm').val('frmFindNode');
	
	var hiddenUrl = jQuery('#hiddenUrl').val();
	//alert(" hiddenUrl :: In Ready Function "+hiddenUrl);
	if(hiddenUrl.indexOf('formId')<=0)
		jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
	//jQuery('#fishBoneTree').css('height',window.innerHeight-180);
	var viewmode=getFieldValue("hdnViewmode","frmWorkOrder");
	var titile=jQuery('#txtFismTitle').val();
	jQuery('#fishBoneAddLayer').hide();
	jQuery('#hdnWMmode').val(viewmode);
	//fillComboBox("frmFindNode","cmbFismPreparedby","employee.commonFilter");
	fillComboBox("frmFindNode","cmbFismApprovedby","employee.commonFilter");
	fillComboBox("frmFindNode","cmbFismDefect","defphen.commonFilter");
	formatDateBox('dteFismPrepareddate','dd-MMM-yyyy');
	formatDateBox('dteFismApproveddate','dd-MMM-yyyy');
	var factId = jQuery("#frmFindNode input[id='factory']").val();
    var sectionId = jQuery("#frmFindNode input[id='section']").val();
    var cellId = jQuery("#frmFindNode input[id='cell']").val();
    var sbuId = jQuery("#frmFindNode input[id='sbuId']").val();
	var machId = jQuery("#frmFindNode input[id='machine']").val();
    var flid = jQuery("#frmFindNode input[id='flid']").val(); 
    //alert(" In Jsp ::   "+flid);
    var DtlId=jQuery('#hdnDtlid').val();
     
    var dataStr = "&factId=" + factId
					+ "&sectionId=" + sectionId
					+ "&sbuId=" + sbuId
					+ "&cellId=" + cellId + "&machId="
					+ machId+"&flid="+ flid;
	//alert("dataStr"+dataStr);
	loadFunctionalLocation("FishBonefunLocation", "functionalLoc.fishbone", "FishBonefunLocation", "frmFindNode",dataStr);
	//alert(jQuery('#hdnRefDoctype').val());
	  fileManagerPopUp("","FB","frmFindNode","btnfilemgr","FishboneFilemgr",mode); 
	buildIndicatorTree();
	var masterId=jQuery("#txtFismKeyid").val();//
   // alert(  "master id in alert" + masterId );
	
	if(masterId.trim().length>0){
		var prepdate=getFieldValue("dteFismPrepareddate");
		var ApprDate=getFieldValue("dteFismApproveddate");

		var PreparedDate  = prepdate.substring(0, 12);
		var ApprovedDate  = ApprDate.substring(0, 12);
	    
		setFieldValue("dteFismApproveddate",ApprovedDate,"frmFindNode");
		setFieldValue("dteFismPrepareddate",PreparedDate,"frmFindNode");
		}
		else{
		fillWithCurrentDate("dteFismPrepareddate");
		fillWithCurrentDate("dteFismApproveddate");
		}

		if(masterId.trim().length<=0){
			
	         var userlogin=jQuery('#hdnpreprdby').val(); 
	         setFieldValue("cmbFismPreparedby",userlogin);
		     setFieldValue("cmbFismApprovedby",userlogin);
		     
		}

		 
		    //alert("1:"+viewmode);
		    if(jQuery('#hdnWMmode').val()=="View"){
		    	disableForm('frmFindNode');
		    //	jQuery('#prdDiv').append('<div id="hdnviewprd" style="position:absolute;top:50%;left:293;margin-left:0%;width:75%;z-index:2;opacity:0.4;height:56%;"> </div>');
		    	
		    }
});

function frmFindNodecmbFismDefect_onSelect(record)
{
	//alert('record'+record);
	var titile=jQuery('#txtFismTitle').val();
	setFieldValue("txtFismTitle",record.text);
}

function btnfilemgr_click()
{
  	var keyid = jQuery('#txtFismKeyid').val();
  //	var title=jQuery("#txtFismTitle").val();
  	//var description=jQuery("#txtFismProblem").val();
  	//alert("title::"+title);
  	//alert("description"+description);
  	//jQuery('#txtDmdmTitle').val(title);
  	//setFieldValue("txtDmdmTitle",record.text);
  	if(keyid.trim().length<=0){
  		saveForm('frmFindNode','FishBoneTree_save.fishbone?filemanger=filemanger');
  	}else if(keyid != null && keyid != ''){
  		var mode = jQuery("#frmFindNode input[id=mode]").val();
  		jQuery("#txtDmdmTitle").val();
  		//setFieldValue("txtFismTitle",record.text);
  		
  	fileManagerPopUp(keyid,"FB","","","",mode);
  		//fileManagerPopUp(keyid,"FB","frmFileMangr","btnfilemgr","FishboneFilemgr",mode); 
	
  	}		
}

function buildIndicatorTree(){ //   //        //        process Tree   
		//alert(1);
	var lineId = jQuery("#frmFindNode input[id='flid']").val();
	var url = jQuery('#hiddenUrl').val();
	var problem=jQuery("#txtFismProblem").val();
	var masterId=jQuery("#txtFismKeyid").val();//
	var id=jQuery('#hdnIdNode').val();
	var refDoc=jQuery("#txtFismRefDoctype").val();
    //alert(" id.trim :: "+id.trim().length);
     
	if(url != null && url!= '' && url != ' ')
		url = url.indexOf('?') > 0 ? url.substring(url.indexOf('?')+1,url.length):"";
     if(refDoc=="WM") 
          url+="&keyId="+masterId;
    url += '&lineId='+lineId+'&problem='+problem+"&masterId="+masterId;

    //alert("url:"+url);
    processTree( jQuery("#fishBoneTreeComponent"),'loadval.fishbone?'+url,'searchnode.fishbone?'+url);
	

	
	var fluSearchStr = '';

	
	jQuery("#fishBoneTreeComponent").bind("open_node.jstree", function (event, data) { 			
	      if((data.inst._get_parent(data.rslt.obj)).length) { 
	        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
	      } 
      }); 
	

	
	 jQuery("#btnsaveFisBone").click(function()
			 {
				
				  var sbu = jQuery("#frmFindNode input[id='sbu']").val(); 
			    	

			    	if(sbu == '' || sbu == ' ' || sbu == undefined)
					 {
			    		 alert("Select SBU");
			    		 return false;
					 }
			    	
			      else
				    {	var wmrefDoc=jQuery("#hdnRefDoctype").val();
				    
				        
				    saveForm('frmFindNode',"FishBoneTree_save.fishbone?&type=type&frstIdn=frstIdn&refDoc="+wmrefDoc);	
				    }	
			 });


	

		jQuery("#fishBoneTreeComponent").bind("loaded.jstree", function (event, data) {		 

			 var title = jQuery("#frmFindNode li[levelno='0']").attr('displaycode');  	
		 	 var key = jQuery("#frmFindNode li[levelno='0']").attr('elementType');  
		 	 if(title != null && title != '' && title != ' ' && jQuery('#hdnWMmode').val()=="View")
		 	 { 	 		//alert(" Inside :: "+title.indexOf('Master')); 
		 	     //readOnlyFields('txtFismProblem');		
		 	  }
	 	 	else
	 	 	 {
 	 		    enableFields('txtFismProblem');
	 	 	 }
		 	
		 	 
		});
			
		jQuery("#fishBoneTreeComponent") .bind("select_node.jstree", function (e, data) {		
		//alert("1:" + data.rslt.obj.attr("elementtype"));
		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
 		jQuery('#hdnElementId').val(data.rslt.obj.attr("elementid"));
 		jQuery('#hdnParentId').val(data.rslt.obj.attr("parentid"));//ParentId
 		jQuery('#hdnElemType').val(data.rslt.obj.attr("elementtype"));//MasterId
 		jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));//Levelno        levelid
 		jQuery('#hdnOrderNo').val(data.rslt.obj.attr("orderno"));//Order no
 		jQuery('#hdnDisplayCode').val(data.rslt.obj.attr("displaycode"));//Cause 

 		//alert(jQuery("#hdnElemType").val());
 	//	alert(jQuery("#hdnParentId").val());
 		var lvlNo = jQuery('#hdnLevelNo').val();
		var sbuId = jQuery("#frmFindNode input[id='sbu']").val();	
        //alert(" lvlNo :: "+lvlNo);
		if(sbuId != null && sbuId != '' && sbuId != ' ')
		{//alert(1);
			if(lvlNo == '0')
			  addToSelectedNode("Fish Bone");
			else if(lvlNo == '1')
				addToSelectedNode("Fish Bone");
		}
		
		//processAjaxCalls("get_image.funlocn","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImageSuccess","getImgErr");		

  });
}

function addToSelectedNode(lbl,fromSearch)
{	
	jQuery('#mstPlanActivityLayer').show();             
	var levelNo = '';			
	var dispCode = '';
	var Masterid = '';
	var OrderNo = '';
	var ParentId = '';
	var Problem = jQuery('#txtFismProblem').val();
	var key = jQuery('#txtFismKeyid').val();
	if(fromSearch != null && fromSearch != ' ' && fromSearch != '')
	{
		//alert(" Inside :: fromSearch "+1);
		var url = jQuery('#hiddenUrl').val();
		if(url != null && url!= '' && url != ' ')
			url = url.indexOf('_')>0?url.substring(url.indexOf('_')+1, url.indexOf('_')>0?url.indexOf('.'):url.length):url;
		levelNo = '2';
		
	}
	else
	{
		
		Masterid=jQuery('#hdnElemType').val();
 		levelNo=jQuery('#hdnLevelNo').val();
 		OrderNo=jQuery('#hdnOrderNo').val();
 		dispCode=jQuery('#hdnDisplayCode').val();
 		ParentId=jQuery('#hdnParentId').val();
 		DtlId=jQuery('#hdnIdNode').val();
 		
	}

	// --- Vignesh -- Fixing the encode url  -- 15Dec2025 ----------------------------------------------------//
	
	
	
//	var dataString = '?levelNo='+levelNo+'&OrderNo='+OrderNo+'&dispCode='+escape(dispCode)+'&ParentId='+ParentId+'&Masterid='+Masterid+'&DtlId='+DtlId;
//	LoadForm("fishBoneAddLayer","prevfishBoneAddLayer","FishBoneChild_modify.fishbone"+dataString,"","","");
	
	// helper: always string + trim + encode
	function enc(v) {
	  return encodeURIComponent(String(v == null ? "" : v).trim());
	}

	// IMPORTANT: ParentId is becoming [object Object]
	// so make sure ParentId is actually the ID value (string)
	var parentVal = ParentId;

	// if ParentId is a DOM element or jQuery object, take its value properly
	if (parentVal && typeof parentVal === "object") {
	  // common cases
	  if (parentVal.value != null) parentVal = parentVal.value;          // DOM element
	  else if (parentVal.id != null) parentVal = parentVal.id;           // object has id
	  else if (parentVal.keyid != null) parentVal = parentVal.keyid;     // your key style
	  else parentVal = "";                                               // fallback
	}

	var dataString =
	  "?levelNo=" + enc(levelNo) +
	  "&OrderNo=" + enc(OrderNo) +
	  "&dispCode=" + enc(dispCode) +
	  "&ParentId=" + enc(parentVal) +
	  "&Masterid=" + enc(Masterid) +
	  "&DtlId=" + enc(DtlId);

	LoadForm(
	  "fishBoneAddLayer",
	  "prevfishBoneAddLayer",
	  "FishBoneChild_modify.fishbone" + dataString,
	  "",
	  "",
	  ""
	);

	
	
	// --- Vignesh -- Fixing the encode url  -- 15Dec2025 ----------------------------------------------------//
	//LoadPopUp("divAddNode","addactivity_input.conf"+dataString, true,"330px","304px","0px","20%", "ADDActivity_Callback",lbl);	
}


function addTrainingAreaSearch(str){
	var searcharr = new Array();
	if(str != null && str.indexOf("-") > 0 ){
		searcharr =str.split('-');	
		//alert(searcharr[searcharr.length-1] );
		jQuery('li a .jstree-search').removeClass('jstree-search');
		jQuery('li a .jstree-clicked').addClass('.jstree-clicked');  
		openNode('fishBoneTreeComponent' , searcharr[searcharr.length-1].replace('#',''));	
		jQuery(searcharr[searcharr.length-1] ).children('a').addClass('jstree-search');
	}		
}
 
function loadval_searchCallBack(result){
	 jQuery("#flFishBoneTreeSearch").css('display','none');
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
         
         jQuery("#fishBoneTreeComponent " + parentIds).addClass("search_parent_flu-s");       
         addTrainingAreaSearch(str);
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
   var LevelNo = jQuery.jstree._focused()._get_node(node).attr('LevelNo');
   var topicId = jQuery.jstree._focused()._get_node(node).attr('refid');
   var prevNode = jQuery("#hdnRightClkNode").val(); 
   if(prevNode != null && prevNode != '' && prevNode != ' ')
	   jQuery("#"+prevNode+" a").removeClass('jstree-clicked');
   jQuery("#hdnOriginalid").val( jQuery.jstree._focused()._get_node(node).attr('originalid'));  
   jQuery("#hdnRightClkNode").val(x);
   jQuery("#hdnParentElements").val(elementId);
   
   jQuery("#hdnTopicId").val(topicId);
   jQuery("li[elementid="+elementId+"]  > a").addClass('jstree-clicked');
   if(parseInt(LevelNo)==2){
	var items = {
		 	addItem: { // The "add" menu item
		 		label: "Add Cause" ,
	            action: function () {processNode(node,"ADD");}
	        }
	 	};		 
	}else if(parseInt(LevelNo)>2){
		var items = {
			 	addItem: { // The "add" menu item
			 		label: "Add Cause",
		            action: function () {processNode(node,"ADD");}
		        },
		        editItem: { // The "add" menu item
			 		label: "Edit Cause",
		            action: function () {processNode(node,"MODIFY");}
		        },
		        deleteItem: { // The "add" menu item
			 		label: "Delete Cause",
		            action: function () {processNode(node,"DELETE");}
		        }
		    	//
		 	};		 
		}
	 else
		   alert('Select Line');
 return items;

}
function processNode(node,mode)
{
	//alert(mode);
	var elemType = jQuery.jstree._focused()._get_node(node).attr('elementType');  
	
	if(mode=="ADD"){
		LoadPopUp("divFishboneModify", "FishBoneCauseAdd_input.fishbone", true,"25%","25%","25%","33%", "", "Add Cause");  
	}else if(mode=="DELETE"){
		if(confirm("Do You Want To Delete?") == true){			
			var originalId=jQuery("#hdnOriginalid").val();		
			params="q=2&keyId=";
			//processAjaxCalls('trAreallevelDel_validate.trArea','?' + params,'nodeDelValidateSuccess','nodeDelValidateFail');			
		}  
	}else if(mode=="MODIFY"){
		//if( elemType!='F1'){			
			LoadPopUp("divFishboneModify", "FishBoneCauseAdd_input.fishbone", true,"25%","25%","25%","33%", "", "Add Cause");			
		//}  
	}
}

function divShowTrAreaPopup_onClose(){
	
	var elemId=jQuery('#hdnIdNode').val();
	var partId=jQuery('#hdnIdParentNodes').val();
	//alert("p  "+jQuery('#hdnIdParentNodes').val());
	//alert("x  "+elemId);
	refreshNode("fishBoneTreeComponent",partId);	
	refreshNode("fishBoneTreeComponent",elemId);	
	return true;
}
function loadPopUp_ErrorCallback(args){}
function multiSelectOk_Callback(args){	
}

function MultiSelectCancel_CallBack(id)
{	
	refreshTree();		
}
function frmFindNode_beforeSubmit(){
	
	var sbuId = getFieldValue('sbu','frmFindNode');
	if (sbuId =='' || sbuId ==' ') {
		popupCommonErrorMsg("Select sbu");
		return false;
	}
}
function frmFindNode_deleteSuccessCallback(result)
{
	var elemId=jQuery("#hdnParentsNodes").val();
	refreshNode("fishBoneTreeComponent",elemId);		
	alert(result.successData.msg);
	navigateToPrevForm();
}
function frmFindNode_successsCallback(result)
{
	//alert(" SucesssCallback :: "+result.Mstkeyid);
	jQuery('#txtFismKeyid').val(result.Mstkeyid);
	//alert(" After :: "+jQuery('#txtFismKeyid').val());
	 var filemanger =result.filemanger;
	 var Type=result.type;
	// alert("Type"+Type);
	 if(Type!="type"){
		 //alert("checking");
		 navigateToPrevForm();
		 }
	 /*if(result.successData.mode=="Modify")
		{
		navigateToPrevForm("ishBone_input.fishbone");
		}*/
		var FismKeyid=result.MomMstkeyid;
	
	buildIndicatorTree();
	if(filemanger==true){
    	if(FismKeyid.trim().length>0){
   			 var keyid=result.MomMstkeyid;
   			 fileManagerPopUp(keyid,"MOM","","","");
			 }
		}
	
}

/*function frmFindNode_deleteSuccessCallback(result)
{
	
	//navigateToPrevForm();
	  
}
*/
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
	var tree = jQuery.jstree._reference("#fishBoneTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}

function refTree(node)
{
	//alert(node);
	var tree = jQuery.jstree._reference("#fishBoneTreeComponent");
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
 
 function frmFindNode_FuntLocHierarchy_SuccessCallBack(result){
	  setFunctionalLocWidth('frmFindNode','862px');
	  var cellId = jQuery("#frmFindNode input[id='cell']").val();
	  var flid = jQuery("#frmFindNode input[id='flid']").val();
	 
	  //alert(cellId);
	  reloadCombo("frmFindNode","cmbFismPreparedby","employee.commonFilter?&cellId="+cellId+"&flid="+flid);
	 // reloadCombo("frmFindNode","cmbFismPreparedby","employee.commonFilter");
	/* if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
		{ 
		    reloadCombo("frmFindNode","cmbFismPreparedby","employee.commonFilter?&flid="+result.cellId);
		
			
		}*/
		
	  var wmrefDoc=jQuery("#txtFismRefDoctype").val();
		var problem=jQuery("#hdnProblem").val();
		//alert(wmrefDoc);
		if(wmrefDoc.trim()=="WM"){
			jQuery('#frmFindNodeFishBonefunLocation').css('display','none');
			jQuery('#txtFismProblem').val(problem);
	 		
		}
		
		
	 
}
 
 jQuery("#btnFisBoneActionplan").click(function(){
	 //alert(1);
	 var FishMstkeyid=jQuery("#txtFismKeyid").val();
	if(FishMstkeyid.trim().length>0)
    { 
		   var keyid=jQuery("#txtFismKeyid").val();
		   var flid=jQuery("#flid").val();
		   var mainTask=getFieldValue("txtFismProblem");
           openActionPlan("ActionPlan",keyid,"FBN",flid,mainTask,'');
    	  //openActionPlan("oplActionPlan",keyid,"OPL",flid,escape(mainTask),"");
    }
	 else
    {
		 saveForm('frmFindNode','FishBoneTree_save.fishbone?openactnpln=openactnpln');
     }		 
	function frmFishBonecmbFismMachineid_onSelect(record)
	{
		//alert(123);
		//alert(record.id);
		loadFunctionalLocation("FishBonefunLocation","functionalLoc.fishbone","FishBonefunLocation","frmFindNode","&machId="+record.id);
	}		
	
});	
</script>

<form id="frmFindNode" name="frmFindNode">	
<div>
     <div id="frmFindNodeFuntKeyIds">
	
		<input type="hidden" id="section" name=cmbFismSectionid value=""></input>
		<input type="hidden" id="sbu" name=cmbFismSbuid value=""></input> 
		<input type="hidden" id="cell"    name="cmbFismCellid" value=""></input> 
		<input type="hidden" id="machine" name="cmbFismMachineid" value=""></input>
		<input type="hidden" id="flid" name="txtFismFlid" value="${requestScope.newGenTlFishbonemst.fismFlid}"></input>
		<input type="hidden" id= "FismSbuid"  name= "hdnFismSbuid" value=""/>     
    
    </div>

    <div  class="easyui-paddingbfpx" id="FishBonefunLocation" style="width: 104%;margin-top:10px;margin-left:56px;width:108%\9;"></div>
<!--    <div id="err_FishBonefunLocation" class="tpm-errormsg" style="padding-left:56px;"></div>-->
    
    
<table style="margin-left:5%;margin-top:-10px;">
<tr>
<td>
	<div style="padding-top:4px;">
		<div>
			<label >Defect</label>
		</div>
		<div>
			<input type="text" class="easyui-combobox" id = "cmbFismDefect" name="cmbFismDefect"  style="width:210px;" value="${requestScope.newGenTlFishbonemst.fismDefect}" />
		</div>
		</div>

   	<div>
			<label class="mandatory-lbl" >Title</label>
		</div>
		<div>
			<input type="text" class="easyui-text" id = "txtFismTitle" name="txtFismTitle"  style="width:210px;" value="${requestScope.newGenTlFishbonemst.fismTitle}" />
		</div>
		</div>
</td>

<td rowspan="2" style="padding-left:30px;">
		<div>
			<label class="mandatory-lbl">Effect / Problem </label>
		</div>
		<div>
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtFismProblem" name="txtFismProblem" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600">${requestScope.newGenTlFishbonemst.fismProblem}</textarea>
		</div>
	</td>
<td style="padding-left:30px;">
<div><label>Prepared Date</label></div>
<div><input id="dteFismPrepareddate" name="dteFismPrepareddate" class="easyui-datebox"  value="${requestScope.newGenTlFishbonemst.fismPrepareddate}"> </div>
</td>

<td style="padding-left:30px;">
<div><label>Prepared By</label></div>
<div><input id="cmbFismPreparedby" name="cmbFismPreparedby" class="easyui-combobox"   style="width:210px;"  value="${requestScope.newGenTlFishbonemst.fismPreparedby}" /> </div>  
</td>
</tr>
<tr>
<td  style="padding-left:0px;">
<div><label>Revision No</label></div>
<div><input type="text" class="easyui-text" id = "txtFismRevisionno" name="txtFismRevisionno"  style="width:210px;" maxlength="10" value="${requestScope.newGenTlFishbonemst.fismRevisionno}" /> </div>
</td>
<td  style="padding-left:30px;display: none;">
<div><label>Approved Date</label></div>
<div><input id="dteFismApproveddate" name="dteFismApproveddate" class="easyui-datebox" value="${requestScope.newGenTlFishbonemst.fismApproveddate}" > </div>
</td>
<td style="padding-left:30px;display: none;">
<div><label>Approved By</label></div>
<div><input id="cmbFismApprovedby" name="cmbFismApprovedby" class="easyui-combobox"   style="width:210px;" value="${requestScope.newGenTlFishbonemst.fismApprovedby}" /> </div>
</td>
<td valign="bottom">
<div style="margin-left:10px; display:none;"><input type="button" id="btnFisBoneActionplan" name="btnFisBoneActionplan" class="easyui-button" style="width:80px;height:22px;" value="Action Plan" />
</div>
</td>
<td valign="bottom">
<div style="margin-left:10px;"><input type="button" id="btnsaveFisBone" name="btnsaveFisBone" class="easyui-button" style="width:110px;height:22px;" value="Save Fish Bone" />
 <span  id="FishboneFilemgr" style="position:absolute;margin-left:10px;margin-left:85px\9;margin-top:-1px;margin-top:-3px\9;" >
	     	</span>
</div>
</td>
</tr>
</table>
</div>
<table>
<tr>
<td>
	<div style="margin-left:5.6%;margin-top:1%;width:100%;height:50%;font-size:12px;text-transform:uppercase;">
		
		<div id="fishBoneTree" style="margin-left:24px;float:left;width:506px;background-color:white;height:306px;height:385px\9;overflow:auto;box-shadow:3px 3px 4px rgba(0, 0, 0, 0.2);" class="sub-cntborder">
			<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
			<div id="flFishBoneTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
			<div id="fishBoneTreeComponent" class="demo" style="width: 50%;"></div>
		</div>
		
	</div>
</td>
<td>

<div id="prevfishBoneAddLayer"></div>
<div id="fishBoneAddLayer" style="box-shadow:3px 3px 4px rgba(0, 0, 0, 0.2);margin-left:11%;width:550px;height:306px;margin-top: 6px;" class="sub-cntborder"></div>

</td>
</tr>
</table>    
    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
    <input type="hidden" id="txtFismKeyid" name="txtFismKeyid" value="${requestScope.newGenTlFishbonemst.fismKeyid}"/>
    <input type="hidden" id="txtFismRefdocid" name="txtFismRefdocid" value="${requestScope.newGenTlFishbonemst.fismRefdocid}"/>
    <input type="hidden" id="txtFismRefDoctype" name="txtFismrefDoctype" value="${requestScope.newGenTlFishbonemst.fismRefdoctype}"/>
    <input type="hidden" id="hdnpreprdby" name="hdnpreprdby" value="${requestScope.UserLogin}"/>
    <input type="hidden" id="hdnaftsave" name="hdnaftsave" value=" "/>
	<input type="hidden" id="txtformFld" name="txtformFld"/>
	<input type="hidden" id="hdnElementId" name="hdnElementId"/>
	<input type="hidden" id="hdnParentsNodes" name="hdnParentsNodes"/>
	<input type="hidden" id="hdnRefDoctype" name="hdnRefDoctype" value="${requestScope.newGenTlFishbonemst.fismRefdoctype}"/>
	<input type="hidden" id="hdnProblem" name="hdnProblem" value="${requestScope.newGenTlFishbonemst.fismProblem}"/>
	<input type="hidden" id="hdnLocnId" name="hdnLocnId"/>
	<input type="hidden" id="hdnIdNode" name="hdnIdNode"/>
	<input type="hidden" id="hdnIdParentNodes" name="hdnIdParentNodes"/>
	<input type="hidden" id="hdnParentElements" name="hdnParentElements"/>
	<input type="hidden" id="hdnElemType" name="hdnElemType"/>
	<input type="hidden" id="hdnLevelNo" name="hdnLevelNo"/>
	<input type="hidden" id="hdnOrderNo" name="hdnOrderNo"/>
	<input type="hidden" id="hdnParentId" name="hdnParentId"/>
	<input type="hidden" id="hdnDisplayCode" name="hdnDisplayCode"/>
	<input type="hidden" id="hdnPopupUrl" name="hdnPopupUrl"/>
	<input type="hidden" id="hdnOriginalid" name="hdnOriginalid"/>
	<input type="hidden" id="hdnElmType" name="hdnElmType"/>
	<input type="hidden" id="hdnRightClkNode" />
     <input type="hidden" id="hdnWMmode" name="hdnWMmode"/>
  
     

</form>
  		   

