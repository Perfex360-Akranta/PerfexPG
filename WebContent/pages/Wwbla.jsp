<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script>

jQuery.noConflict();
	jQuery(document).ready(function(){	
	   initialiseForm('frmWwbla');	
	   if(jQuery('#txtWwblFlid').val()!="WM")
	   jQuery('#submitForm').val('frmWwbla');
	
var NearMissDesc=jQuery("#hdnLopcDesc").val();
var NearMissEmployeeId=jQuery("#hdnLopcEmployeeId").val();
var NearMissTier=jQuery("#hdnLopcTier").val();
/* if(NearMissDesc!=null ){ */
	
	if(NearMissDesc.length>1){
	//alert("inside");
	setFieldValue('txtWwblPhenomena',NearMissDesc);	
	
}
if(NearMissTier.length>1){
	setFieldValue('txtWwblProblem',NearMissTier);		
}

if(NearMissEmployeeId.length>1){
	setFieldValue('cmbLopcEmployee',NearMissEmployeeId);		
}

var LopcFlid=jQuery("#hdnLopcFlid").val();


	   var hiddenUrl = jQuery('#hiddenUrl').val();
		//alert(" hiddenUrl :: In Ready Function "+hiddenUrl);
		/* if(hiddenUrl.indexOf('formId')<=0)
			jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val()); */
			if(hiddenUrl.indexOf('formId')<=0 && jQuery('#txtWwblFlid').val()!="WM")
			    jQuery('#hiddenUrl').val(hiddenUrl.indexOf('?')>0?hiddenUrl+'&formId='+jQuery('#submitForm').val():hiddenUrl+'?formId='+jQuery('#submitForm').val());
		//jQuery('#fishBoneTree').css('height',window.innerHeight-180);
		var viewmode=getFieldValue("hdnViewmode","frmWorkOrder");
		

	   jQuery('#wwblaAddLayer').hide();
		jQuery('#hdnWMmode').val(viewmode);
	   
	   formatDateBox('dteWwblPrepareddate','dd-MM-yyyy');
	   fillComboBox("frmWwbla","cmbType","");
	   fillComboBox("frmWwbla","cmbLopcEmployee","employee.commonFilter");
	   fillWithCurrentDate('dteWwblPrepareddate');
	   var factId = jQuery("#frmWwbla input[id='factory']").val();
	    var sectionId = jQuery("#frmWwbla input[id='section']").val();
	    var cellId = jQuery("#frmWwbla input[id='cell']").val();
	    var sbuId = jQuery("#frmWwbla input[id='sbuId']").val();
		var machId = jQuery("#frmWwbla input[id='machine']").val();
		var LopcFlid=jQuery("#hdnLopcFlid").val();
		if(LopcFlid.length>1){
			var flid=jQuery("#hdnLopcFlid").val();

			//alert("LopcFlid"+flid);
		   // loadFunctionalLocation("WwblafunLocation","functionalLoc.fishbone","WwblafunLocation","frmWwbla","&flid="+flid);

		}
		else{
	    var flid = jQuery("#frmWwbla input[id='flid']").val(); 
		}
	    var dataStr = "&factId=" + factId
						+ "&sectionId=" + sectionId
						+ "&sbuId=" + sbuId
						+ "&cellId=" + cellId 
						+ "&machId="+ machId
						+"&flid="+ flid;

	    loadFunctionalLocation("WwblafunLocation","functionalLoc.fishbone","WwblafunLocation","frmWwbla",dataStr);

		    buildIndicatorTree();
		    var masterId=jQuery("#txtWwblKeyid").val();
			
			if(masterId.trim().length>0){
				var prepdate=getFieldValue("dtePrepareddate");
				var PreparedDate  = prepdate.substring(0, 12);
				setFieldValue("dtePrepareddate",PreparedDate,"frmWwbla");
			}
			else{
				fillWithCurrentDate("dtePrepareddate");
		    }

			if(masterId.trim().length<=0){
				
		         var userlogin=jQuery('#hdnpreprdby').val(); 
		         setFieldValue("cmbPreparedby",userlogin);
			     
			     
			}
			 if(jQuery('#hdnWMmode').val()=="View"){
			    	disableForm('frmWwbla');
 }
 });
	jQuery("#btnsavewwbla").click(function(){
	
		  var sbu = jQuery("#frmWwbla input[id='sbu']").val(); 
		  var problem=getFieldValue("txtWwblProblem");
		  var phenomena=getFieldValue("txtWwblPhenomena");
		  var mechanism=getFieldValue("txtWwblMechanism");
		  var LopcId=jQuery("#hdnLopcKeyid").val();
		  //var wwbla_keyid = jQuery("#txtWwblKeyid").val();
		 //alert("wwbla_keyid "+wwbla_keyid);
		  var EmployeeId=getFieldValue("cmbLopcEmployee", "frmWwbla");
		//  alert("EmployeeId"+EmployeeId);

	    	if(sbu.trim().length == '' || sbu == ' ')
			 {
	    		 popupCommonErrorMsg(" Select SBU ");
	    		 return false;
			 }
	    	if(problem.trim().length == 0 || problem == ' ')
			 {
	    		 popupCommonErrorMsg(" Enter Problem ");
	    		 return false;
			 }
	    	if(phenomena.trim().length == '' || phenomena == ' ')
			 {
	    		 popupCommonErrorMsg(" Enter Phenomena ");
	    		 return false;
			 }
	    	if(mechanism.trim().length == '' || mechanism == ' ')
			 {
	    		 popupCommonErrorMsg(" Enter Mechanism ");
	    		 return false;
			 }
	    	 
	    	else
		    {	var wwblkeyid=jQuery("#txtWwblKeyid").val();
		        
		    //saveForm('frmWwbla',"WwblaTree_save.wwbla?&frstIdn=frstIdn&wwblKeyid="+wwbla_keyid+"&LopcId="+LopcId+"&EmployeeId="+EmployeeId);
		        saveForm('frmWwbla',"WwblaTree_save.wwbla?&frstIdn=frstIdn&wwblKeyid="+wwblkeyid+"&EmployeeId="+EmployeeId);										
		    }	
	    	
	 });

	function frmWwbla_FuntLocHierarchy_SuccessCallBack(result){
		setFunctionalLocWidth('frmWwbla','520px');
		  var cellId = jQuery("#frmWwbla input[id='cell']").val();
		  var flid = jQuery("#frmWwbla input[id='flid']").val();
		 
		// alert(flid);
		  reloadCombo("frmWwbla","cmbPreparedby","employee.commonFilter?&cellId="+cellId+"&flid="+flid);
		 // reloadCombo("frmFindNode","cmbFismPreparedby","employee.commonFilter");
		if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
			{ 
			    reloadCombo("frmWwbla","cmbPreparedby","employee.commonFilter?&flid="+result.cellId);
			}


		 var wmflid=jQuery("#txtWwblFlid").val();
			var problem=jQuery("#hdnProblem").val();
			//alert(wmrefDoc);
			if(wmflid.trim()=="WM"){
				jQuery('#frmWwblaWwblafunLocation').css('display','none');
				jQuery('#txtWwblProblem').val(problem);
		 		
			}
			var mode=jQuery("#hdnfrmMode").val();
		    if(mode=="View"){
		        jQuery('#cmbPreparedby').combobox('disable');
		    }	
	}
	function buildIndicatorTree(){  
		
	var lineId = jQuery("#frmWwbla input[id='flid']").val();
	var url = jQuery('#hiddenUrl').val();
	var problem=jQuery("#txtWwblProblem").val();
	
	var phenomena=jQuery("#txtWwblPhenomena").val();
	
	var mechanism=jQuery("#txtWwblMechanism").val();
	
	var masterId=jQuery("#txtWwblKeyid").val();//
	var id=jQuery('#hdnIdNode').val();
	//var treeid=jQuery("#wwblaTree");
	//var url;

	if(url != null && url!= '' && url != ' ')
		url = url.indexOf('?') > 0 ? url.substring(url.indexOf('?')+1,url.length):"";
    url += '&lineId='+lineId+'&problem='+problem+"&phenomena="+phenomena+"&mechanism="+mechanism+"&masterId="+masterId;
    
    //processTree( jQuery("#wwblaTreeComponent"),'loadval.fishbone?'+url,'searchnode.fishbone?'+url);
    processTree(jQuery("#wwblaTreeComponent"),'loadval.wwbla?'+url,'searchnode.wwbla?'+url);
  
	var fluSearchStr = '';

	jQuery("#wwblaTreeComponent").bind("open_node.jstree", function (event, data) { 			
	      if((data.inst._get_parent(data.rslt.obj)).length) { 
	        data.inst.open_node(data.inst._get_parent(data.rslt.obj), false,true); 
	      }
	   
	} ); 
	 	 jQuery("#wwblaTreeComponent").bind("loaded.jstree", function (event, data) {		 

		 var title = jQuery("#frmWwbla li[levelno='0']").attr('displaycode');  	
	 	 var key = jQuery("#frmWwbla li[levelno='0']").attr('elementType');  
	 	 if(title != null && title != '' && title != ' ' && jQuery('#hdnWMmode').val()=="View")
	 	 { 	 
		 	 		
	 	  }
 	 	else
 	 	 {
	 		    enableFields('txtWwblProblem');
 	 	 }
	 	
	 	 
	});
	    
	jQuery("#wwblaTreeComponent") .bind("select_node.jstree", function (e, data) {
		//alert("inside");
		
		jQuery('#hdnIdNode').val(data.rslt.obj.attr("id"));
 		jQuery('#hdnElementId').val(data.rslt.obj.attr("elementid"));
 		jQuery('#hdnParentId').val(data.rslt.obj.attr("parentid"));//ParentId
 		jQuery('#hdnElemType').val(data.rslt.obj.attr("elementtype"));//MasterId
 		jQuery('#hdnLevelNo').val(data.rslt.obj.attr("levelno"));//Levelno        levelid
 		jQuery('#hdnOrderNo').val(data.rslt.obj.attr("orderno"));//Order no
 		jQuery('#hdnDisplayCode').val(data.rslt.obj.attr("displaycode"));//Cause 

 		var lvlNo = jQuery('#hdnLevelNo').val();
		var sbuId = jQuery("#frmWwbla input[id='sbu']").val();	
        //alert(" lvlNo :: "+lvlNo);
		if(sbuId != null && sbuId != '' && sbuId != ' ')
		{
		  if(lvlNo == '0')
			addToSelectedNode("Wwbla");
		  else if(lvlNo == '1')
			addToSelectedNode("Wwbla");
        }
});
}
	function frmWwbla_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		navigateToPrevForm();
	}	
	/*function frmWwbla_SuccessCallback(result)
	{
		alert(result.successData.msg);
		navigateToPrevForm();
	}	*/
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
		
	
	 function addToSelectedNode(lbl,fromSearch)
	 {	
		 
	 
	 	jQuery('#mstPlanActivityLayer').show();             
	 	var levelNo = '';			
	 	var dispCode = '';
	 	var Masterid = '';
	 	var OrderNo = '';
	 	var ParentId = '';
	 	var Problem = jQuery('#txtWwblProblem').val();
	 	var phenomena=jQuery("#txtWwblPhenomena").val();
		var mechanism=jQuery("#txtWwblMechanism").val();

	 	var key = jQuery('#txtWwblKeyid').val();
	 	 var mode=jQuery("#hdnfrmMode").val();
	 	 var Status=jQuery("#hdnStatus").val();
	 
	 	if(fromSearch != null && fromSearch != ' ' && fromSearch != '')
	 	{
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
	  		
	  		if(DtlId=="WWBLA001"){
	  			dispCode=dispCode+"/"+phenomena;
	  		}
	  		
	 	}

        //var dataString = '?levelNo='+levelNo+'&OrderNo='+OrderNo+'&dispCode='+escape(dispCode)+'&ParentId='+ParentId+'&Masterid='+Masterid+'&DtlId='+DtlId+'&mode='+mode+'&Status='+Status;
	 	var dataString = '?levelNo=' + encodeURIComponent(levelNo) + 
                 '&OrderNo=' + encodeURIComponent(OrderNo) + 
                 '&dispCode=' + encodeURIComponent(dispCode) + 
                 '&ParentId=' + encodeURIComponent(ParentId) + 
                 '&Masterid=' + encodeURIComponent(Masterid) + 
                 '&DtlId=' + encodeURIComponent(DtlId) + 
                 '&mode=' + encodeURIComponent(mode) + 
                 '&Status=' + encodeURIComponent(Status);
        if(mechanism.length<1){
        
        	   popupCommonErrorMsg("Please save LOPC Incident");
        
        }
        else{
	 LoadForm("wwblaAddLayer","prevwwblaAddLayer","wwbla_modify.wwbla"+dataString,"","","");
	 	//LoadPopUp("divAddNode","addactivity_input.conf"+dataString, true,"330px","304px","0px","20%", "ADDActivity_Callback",lbl);	
        }
}
	 function addTrainingAreaSearch(str){
		 	var searcharr = new Array();
		 	if(str != null && str.indexOf("-") > 0 ){
		 		searcharr =str.split('-');	
		 		//alert(searcharr[searcharr.length-1] );
		 		jQuery('li a .jstree-search').removeClass('jstree-search');
		 		jQuery('li a .jstree-clicked').addClass('.jstree-clicked');  
		 		openNode('wwblaTreeComponent' , searcharr[searcharr.length-1].replace('#',''));	
		 		jQuery(searcharr[searcharr.length-1] ).children('a').addClass('jstree-search');
		 	}		
		 }      
	 function loadval_searchCallBack(result){
		 jQuery("#flwwblaTreeSearch").css('display','none');
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
	         
	         jQuery("#wwblaTreeComponent" + parentIds).addClass("search_parent_flu-s");       
	         addTrainingAreaSearch(str);
	     }    
	}

	 function refreshTree()
	 {
	 	var tree = jQuery.jstree._reference("#wwblaTreeComponent");
	 	var currentNode = tree._get_node(null, false);
	 	var parentNode = tree._get_parent(currentNode);
	 	tree.refresh(parentNode);
	 }

	 function refTree(node)
	 {
	 	//alert(node);
	 	var tree = jQuery.jstree._reference("#wwblaTreeComponent");
	 	var currentNode = tree._get_node(node, true);	
	 	var parentNode = tree._get_parent(currentNode);
	 	tree.refresh();
	 }
	 	 
	 function frmWwbla_successsCallback(result)
	 {
		//alert(result.Mstkeyid+"Resutt value");
		 jQuery('#txtWwblKeyid').val(result.Mstkeyid);
		 var  WWblaKeyid=jQuery("#txtWwblKeyid").val();
		// alert("Value "+WWblaKeyid);
	 	buildIndicatorTree();
	 }
	   var mode=jQuery("#hdnfrmMode").val();
	   if(mode=="View"){
		disableUIButton("btnsavewwbla");   
		jQuery("#txtWwblProblem").attr("disabled",true);
		jQuery("#txtWwblPhenomena").attr("disabled",true);
		jQuery("#txtWwblMechanism").attr("disabled",true);
		 disableField("frmWwbla","dteWwblPrepareddate");
		 disableField("frmWwbla","cmbPreparedby");
	   }
	   
	    jQuery("#btnLopcExcelView").click(function(){
	    
	    	
	    	  var Tier=jQuery("#txtWwblProblem").val();
	    	
	    	  var Description=jQuery("#txtWwblPhenomena").val();
	    	  var Incident=jQuery("#txtWwblMechanism").val();
	    	 
	    	  var InvestigationDate=jQuery("#dteWwblPrepareddate").datebox('getValue');
	    	
	    	  var PreparedBy=jQuery("#cmbPreparedby").combobox("getValue");
	    	 
	    	  var Flid=jQuery("#txtWwblFlid").val();
	    	  var  WWblaKeyid=jQuery("#txtWwblKeyid").val();
	    	  
	    	 
		 	if(WWblaKeyid != null && WWblaKeyid.length >0){
		 		window.open("WWBLAExcelView.wwbla?&WWblaKeyid="+WWblaKeyid+"&Flid="+Flid+"&Tier="+Tier+"&Description="+Description+"&Incident="+Incident+"&InvestigationDate="+InvestigationDate+"&PreparedBy="+PreparedBy);
		 	}
	 });
	    	   
	   
</script>


<form name="frmWwbla" id="frmWwbla">
<div>
<table>
<tr>
<td colspan="2">
<div id="frmWwbla1">
		<input type="hidden" id="section" name=cmbWwblSectionid value=""></input>
		<input type="hidden" id="sbu" name=cmbWwblSbuid value=""></input> 
		<input type="hidden" id="cell"    name="cmbWwblCellid" value=""></input> 
		<input type="hidden" id="machine" name="cmbWwblMachineid" value=""></input>
		<%-- <input type="hidden" id="flid" name="txtWwblFlid" value="${requestScope.LopcFlid}"></input> --%>
<input type="hidden" id="flid" name="txtWwblFlid" value="${requestScope.newBdmTlWwblamst.wwblFlid}"></input>
</div>
<div  class="easyui-paddingbfpx" id="WwblafunLocation" style="width: 104%;margin-top:10px;margin-left:56px;width:108%\9;"></div>
</td>
<td>
<div style="padding-left:20px;">
	<div>
		<label >InvestigationBy</label>
		<span style= "padding-left:96px;"><label>Investigation Date</label></span>
	</div>
	<div>
		<input id="cmbPreparedby" name="cmbWwblPreparedby" class="easyui-combobox"   style="width:160px;" value="${requestScope.newBdmTlWwblamst.wwblPreparedby}"/>
		<span style= "padding-left:15px;"><input  class="easyui-date" id = "dteWwblPrepareddate" name="dteWwblPrepareddate"  style="width:80px;"  value="${requestScope.newBdmTlWwblamst.wwblPrepareddate}"/></span>
	<span style= "padding-left:20px;"><input type="button" id="btnLopcExcelView" name="btnLopcExcelView" class="easyui-button" style="width:80px;height:25px;display:;" value="Excel View" />
 </span>
	</div>
</div>
</td>
</tr>
</table>
<table style="margin-left:5%;margin-top:-10px;">

<tr>
<td>	
       <div>
			<label class="mandatory-lbl">LOPC Tier</label>
			<span style= "padding-left:100px;"><label class="mandatory-lbl">Description of LOPC</label></span>
			<span style= "padding-left:150px;"><label class="mandatory-lbl">Incident/Problem Description</label></span>
			<span style= "padding-left:110px;"><label >Lopc PreparedBy</label></span>
		</div>
		<div>
			<textarea id="txtWwblProblem" name="txtWwblProblem" style="resize:none;width:150px;text-transform:uppercase;" maxlength="600">${requestScope.newBdmTlWwblamst.wwblProblem}</textarea>
			<span style= "padding-left:10px;"><textarea id="txtWwblPhenomena" name="txtWwblPhenomena" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600">${requestScope.newBdmTlWwblamst.wwblPhenomena}</textarea></span>
		    <span style= "padding-left:10px;"><textarea id="txtWwblMechanism" name="txtWwblMechanism" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600">${requestScope.newBdmTlWwblamst.wwblMechanism}</textarea></span>
		 <div style= "margin-left:730px;margin-top:-42px;">	<input id="cmbLopcEmployee" name="cmbLopcEmployee" disabled="disabled"class="easyui-combobox"   style="width:185px;" value="${requestScope.newBdmTlWwblamst.wwblPreparedby}"/>
	</div>  
		    <div style= "margin-left:740px;margin-top:10px;"><input type="button" id="btnsavewwbla" name="btnsavewwbla" class="easyui-button" style="width:150px;height:22px;" value="Save LOPC Incident" /></div>
	    </div>
		</tr>
		</div>
		</table>
		
		<table style="margin-left:5%;">
		<tbody>
<tr>
<td>
	<div style="margin-top:1%;width:80%;height:50%;font-size:12px;text-transform:uppercase;">
		
		<div id="wwblaTree" style="margin-left:0px; float:left;width:580px;background-color:white;height:306px;height:385px\9;overflow:auto;box-shadow:3px 3px 4px rgba(0, 0, 0, 0.2);" class="sub-cntborder">
			<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
			<div id="flwwblaTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
			<div id="wwblaTreeComponent" class="demo" style="width: 50%;"></div>
		</div>
	</div>
	</td>
	<td>
	   <div id="prevfishBoneAddLayer"></div>
       <div id="wwblaAddLayer" style="box-shadow:3px 3px 4px rgba(0, 0, 0, 0.2);margin-left:12%;width:550px;height:306px;margin-top: 6px;" class="sub-cntborder"></div>
	</td>
	</tr>
	</tbody>
 </table>
	</div>
	 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdnfrmMode" name="hdnfrmMode" value="${requestScope.frmMode}"/>
	  <input type="hidden" id="txtWwblKeyid" name="txtWwblKeyid" value="${requestScope.newBdmTlWwblamst.wwblKeyid}"/>
	  <input type="hidden" id="txtWwblFlid" name="txtWwblflid" value="${requestScope.newBdmTlWwblamst.wwblFlid}"/>
	<input type="hidden" id="hdnpreprdby" name="hdnpreprdby" value="${requestScope.UserLogin}"/>
     <input type="hidden" id="hdnaftsave" name="hdnaftsave" value=" "/>
	<input type="hidden" id="txtformFld" name="txtformFld"/>
	<input type="hidden" id="hdnElementId" name="hdnElementId"/>
	<input type="hidden" id="hdnParentsNodes" name="hdnParentsNodes"/>
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
     <input type="hidden" id="hdnLopcFlid" name="hdnLopcFlid" value="${requestScope.LopcFlid}"/>
     <input type="hidden" id="hdnLopcDesc" name="hdnLopcDesc" value="${requestScope.LopcDesc}"/>
     <input type="hidden" id="hdnLopcMode" name="hdnLopcMode" value="${requestScope.LopcMode}"/>
      <input type="hidden" id="hdnWwblLopcId" name="hdnWwblLopcId" value="${requestScope.LopcId}"/>
        <input type="hidden" id="hdnLopcEmployeeId" name="hdnLopcEmployeeId" value="${requestScope.EmployeeId}"/>
          <input type="hidden" id="hdnLopcTier" name="hdnLopcTier" value="${requestScope.Tier}"/>
      <input type="hidden" id="hdnStatus" name="hdnStatus" value="${requestScope.Status}"/>
     </form>