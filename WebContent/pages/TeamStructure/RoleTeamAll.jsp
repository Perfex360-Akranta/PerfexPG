<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){	

	initialiseForm('frmRoleTeam');	
	jQuery('#submitForm').val('frmRoleTeam');
	jQuery('#showAddRolEmp').hide();	
	var flid = jQuery("#txtFrlFnlnKeyid").val();
	var level = jQuery("#txtFrlLevel").val();
	var roleid=jQuery('#txtRoleId').val();	
	
	fillComboBox("frmRoleTeam", "cmbFlid", "FillFlid.commonFilter");
	fillComboBox("frmRoleTeam", "cmbLocation", "FillFlid.commonFilter?rolelocn=LCN");
	
	
	disableBtns();
	jQuery("#btnProc").click(function (){	
		hideRoleEmpList();
	});

	jQuery('#btnShowAddRolEmp').click(function(){
		showRoleEmpList();					
	});
	
 	processTree( jQuery("#flTreeComponent"),'loadval.roleteam','searchnode.roleteam');
	jQuery("#flTreeComponent") .bind("select_node.jstree", function (e, data) {
		//alert('node:'+data.rslt.obj.attr("flid"));
		//alert('node:'+data.rslt.obj.attr("location"));
		var inst=data.inst;
     	var level=inst.get_path().length;
      	var selected=inst.get_selected();
      	var id=selected.attr('id');
      	var name=selected.prop('tagName');
		var flid=data.rslt.obj.attr("flid");
		var location=data.rslt.obj.attr("location");
		
		//alert('flid:'+flid);
		level=parseInt(level,10)-1;
		//var level=inst.get_path().length;	
		//alert('level:'+level);
		
		//alert('location:'+Query("#txtLocation").val());	
		if(flid!="1"){
			jQuery("#txtFrlFnlnKeyid").val(flid);
			jQuery("#txtLocation").val(location);		
			jQuery("#txtFrlLevel").val(level);			
			jQuery('#spnFnln').html(' '+data.rslt.obj.attr("displaycode"));
			var prevLevl = jQuery("#hdnPrevLevel").val();
			var prevLocation = jQuery("#hdnPrevLocat").val();
			if(level == "1" || prevLevl=="1"  || prevLocation != location ){
				if(level == "1"	)
					location="";
				processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&level="+level+"&location="+location,"empgrid","emppager","","","","Load_Complete","","");
			}
			jQuery("#hdnPrevLevel").val(level);
			jQuery("#hdnPrevLocat").val(location);		
			LoadFnlnData(flid,level);			
		}
		else{
			disableBtns();
		}
		
		
		enableUIButton("btnProc");
		processAjaxCalls("get_image.roleteam","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImgSuccess","getImgErr");
     });	
	jQuery("#flTreeComponent").bind("loaded.jstree", function (event, data) {
		if (flid!=null){				
			jQuery('#flTreeComponent').jstree("open_node","#"+flid);
			var originalId=jQuery("#txtOriginalId").val();	
			//alert('originalId'+originalId);
			var searchNode =originalId;//'LOC 1';//'LCN0000002';
			if(searchNode != null && searchNode != ' ' && searchNode != '')
			{
				jQuery("#flTreeComponent").jstree("search",searchNode);
			}							
		}
	});
				
	jQuery("#flTreeComponent").bind("search.jstree", function (e, data) {
        //alert("Found " + data.rslt.nodes.length + " nodes matching '" + data.rslt.str + "'.");
	});
	
	jQuery("#btnAdd").click(function(){
		saveRoleTeam("Employee");
	});
	jQuery("#btnRemove").click(function(){
		removeRoleTeam("Employee");
	});
	jQuery("#btnSave").click(function(){
		saveEmpTeamTrade();
	});
	jQuery("#btnNewRole").click(function(){
		openRoleMaster();
	});
	jQuery("#btnNewEmp").click(function(){
		openEmpMaster();
	});
	
	jQuery("#btnRoleAdd").click(function(){
		saveRoleTeam("Role");
	});
	jQuery("#btnRoleRemove").click(function(){		
	//	removeRoleTeam("Role");
	});
	
	jQuery('#tabRoleTm .tabs-panels').css('height','300px');
	jQuery('#tabRoleTm .tabs-panels').css('height','300px');	 	
	jQuery("#tabRoleTm").tabs({ onSelect:function(title){ 			
			/*if(title == "Role" && getGridRecordCount("rolegrid") <= 0 ){	
				processGridnew("roleall_input.roleteam","?q=2&flid="+flid,"rolegrid","rolepager","","","","Load_Complete","","");
			}
			else if(title == "Employee"){
				processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid,"empgrid","emppager","","","","Load_Complete","","");
			}*/
		}
	});
	var location=jQuery("#txtLocation").val();	
	processGridnew("roleteamfnln_input.roleteam","?q=2&flid="+flid+"&level="+level,"roleteamgrid","","","","","Load_Complete","","");
	processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+"&level="+level,"empteamgrid","","","","","empteamgrid_onComplete");
	processGridnew("roleall_input.roleteam","?q=2&flid="+flid+"&level="+level,"rolegrid","rolepager","","","","Load_Complete","","");
	processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+"&level="+level+"&location="+location,"empgrid","emppager","","","","Load_Complete","","");	
});

function frmRoleTeamcmbLocation_onSelect(record) {

	setFieldValue("cmbFlid"," ");
	reloadCombo("frmRoleTeam","cmbFlid","FillFlid.commonFilter?&flid="+record.id);
	processGridnew("empall_input.roleteam","?q=2&flid="+record.id,"empgrid","emppager","","","","Load_Complete","","");
	

}

function frmRoleTeamcmbFlid_onSelect(record) {
	reloadAllGrids(record.id);
	
}

function reloadAllGrids(flid)
{  
	//var flid = getFieldValue('cmbFlid','frmRoleTeam');
	
	processAjaxCalls("getrolelevel.roleteam?&flid="+flid, flid, 'getrolelevel_successCallBack')	;	

	processAjaxCalls("getlocation.roleteam?&flid="+flid, "", 'getlocation_successCallBack')	;
	 // -- vignesh uncommenting
	var location=jQuery("#txtLocation").val();	
	processGridnew("roleteamfnln_input.roleteam","?q=2&flid="+flid+"&level="+level,"roleteamgrid","","","","","Load_Complete","","");
	processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+"&level="+level,"empteamgrid","","","","","empteamgrid_onComplete");
	processGridnew("roleall_input.roleteam","?q=2&flid="+flid+"&level="+level,"rolegrid","rolepager","","","","Load_Complete","","");
	processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+"&level="+level+"&location="+location,"empgrid","emppager","","","","Load_Complete","",""); 
}




function getlocation_successCallBack(result)
{  
	 setFieldValue("cmbLocation",result[0][0]);
}


function getrolelevel_successCallBack(result)
{  
	var level = result.successData.level;
	var location =  result.successData.location;	
	var flid = getFieldValue('cmbFlid','frmRoleTeam');
	
	var roleid ='';
	enableBtns();
	setFieldValue("txtLocation",location);
	setFieldValue("txtFrlFnlnKeyid",flid);
	setFieldValue("txtFrlLevel",level);
	
	//jQuery("#txtLocation").val(location);	
	processGridnew("roleteamfnln_input.roleteam","?q=2&flid="+flid+"&level="+level,"roleteamgrid","","","","","Load_Complete","","");
	processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+"&level="+level,"empteamgrid","","","","","empteamgrid_onComplete");
	processGridnew("roleall_input.roleteam","?q=2&flid="+flid+"&level="+level,"rolegrid","rolepager","","","","Load_Complete","","");
	processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+"&level="+level+"&location="+location,"empgrid","emppager","","","","Load_Complete","",""); 
}
function showRoleEmpList(){
	jQuery('#empteamgrid').setGridWidth(425);
	jQuery('#showAddRolEmp').hide();
	jQuery("#divAddRoleEmp").show();
}
function hideRoleEmpList(){
	jQuery('#empteamgrid').setGridWidth(790);
	jQuery("#divAddRoleEmp").hide();
	jQuery('#showAddRolEmp').show();		
}
function enableBtns(){
	enableUIButton("btnAdd");
	//enableUIButton("btnRoleRemove");
	enableUIButton("btnRemove");
	enableUIButton("btnProc");
	enableUIButton("btnSave");
	enableUIButton("btnNewRole");
	//enableUIButton("btnRoleAdd");
	enableUIButton("btnNewEmp");				
}
function disableBtns(){
	disableUIButton("btnAdd");
	disableUIButton("btnRoleRemove");
	disableUIButton("btnRemove");
	disableUIButton("btnProc");
	disableUIButton("btnSave");	
	disableUIButton("btnNewRole");
	//disableUIButton("btnRoleAdd");
	disableUIButton("btnNewEmp");
}
function openRoleMaster(){
	LoadPopUp("divRoleMaster","RoleMaster_link.gnms",true,"90%","550px","1%","3%","","Role Master","",true);
}
function openEmpMaster(){
	//LoadPopUp("divEmpMaster","loadmst_grid.gnms?q=2&menuCaption=Employee&menuName=MNUGENEMPLOYEE&isMMC=N&loadFormArg=emp_input.emp",true,"90%","550px","1%","3%","","Employee Master","",true);
	LoadPopUp("divEmpMaster","emp_input.emp?masterForm=N",true,"90%","550px","1%","3%","","Employee Master","",true);
	
}
// function removeRoleTeam(removeType){
// 	alert("Click " + removeType);
// 	alert(keyId +"  In side single ");	

// 	if (removeType=="Role"){		
// 		var griddata= roleMapDelete("roleteamgrid",removeType);
// 		processAjaxCalls("rolemappingentry_delete.roleteam",griddata,"deleteRoleMapSuccess","deleteRoleMapErr");		
// 	}
// 	else if (removeType=="Employee"){
// 		var griddata=roleMapDelete("empteamgrid",removeType); 
// 		processAjaxCalls("roleTeamEmp_delete.roleteam", griddata, 'removeRoleEmp_successCallBack','removeRoleEmp_errorCallBack')	;	
// 	}	
// }

function removeRoleTeam(removeType){
   // alert("Click " + removeType); // keep your existing alert

    if (removeType == "Role") {
        var griddata = roleMapDelete("roleteamgrid", removeType);
        if (griddata !== false) {
            processAjaxCalls("rolemappingentry_delete.roleteam", griddata,
                "deleteRoleMapSuccess", "deleteRoleMapErr");
        }
    } else if (removeType == "Employee") {
        var griddata = roleMapDelete("empteamgrid", removeType);
        if (griddata !== false) {
            processAjaxCalls("roleTeamEmp_delete.roleteam", griddata,
                "removeRoleEmp_successCallBack", "removeRoleEmp_errorCallBack");
        }
    }
}

function roleMapDelete(jqGridId,title){
	var errText="";
	var errFlg=false;

    gridVal=getSelectdRows(jqGridId,title);
    gridData  = gridVal;
    var errText = getFilterValue(gridVal+'&', 'errText');
 	if(errText==null || errText.trim()==""){
 	}
 	else{
 		//alert('errText'+errText);
 		errFlg=true;	 		
 	}
 	//alert("errFlg"+errFlg);	
 	if(errFlg==true){
 		setTimeout(function() {
 			showCommonErrorMsg(errText);
 		}, 200);
 		div_err();
 		return false;
 	}
 	else{
 		return gridData;
 	}
}

function getSelectdRows(jqGridId,title) {
	//alert('getSelectdRows');
	var flg=false;	
	var isSelect=false;	
	var errMsg="";	
	var datarow = jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
	var datacol = jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var rowid = "";
	var jsonArrO = '[';
	var deleteIds="";
	for (i = 0; i < datarow.length; i++) {
		rowid = datarow[i];
		var errFlgRow=false;
		var errMsgRow="";
		//alert('rowid:'+rowid);
		var keyId = jQuery("#"+jqGridId).jqGrid('getCell', rowid,"keyid"); // Call detail Key Id	
		var CHEKVal = jQuery("#"+jqGridId).jqGrid('getCell',rowid, "select");
		if (CHEKVal == "1") {
			isSelect=true;		
			if (deleteIds.trim().length==0){
				deleteIds=keyId;
			}
			else{
				deleteIds+=","+keyId;
			}
		}
	}
	//alert('deleteIds:'+deleteIds);
	if(isSelect==false){		
		errMsg=errMsg + " Check Atleast One " +title ;
		flg=true;
	}
	
	deleteIds="&keyid="+deleteIds;
	
	if(flg==true){				
		return deleteIds+"&errText="+errMsg; 
	}
	else{
		return deleteIds+"&errText="; 
	}		
}
function deleteRoleMapSuccess(result){
	
	jQuery("#roleteamgrid").trigger("reloadGrid");
	jQuery("#empteamgrid").trigger("reloadGrid");
}
function deleteRoleMapErr(result){
}

function removeRoleEmp_successCallBack(result)
{
	jQuery("#empteamgrid").trigger("reloadGrid");
	if (result.tpmException!=null)
		alert(result.tpmException);
}

function removeRoleEmp_errorCallBack(result) {
}
//----------------------- Vignesh Altered for save 08Nov2025 ---------------------------//
// function saveEmpTeamTrade(){
// 	var errText="";
// 	var errFlg=false;
// 	var gridId="empteamgrid";
// 	//alert('saveEmpTeam');
//     var gridVal=convertJqGridToJSONStrEmpTeam('empteamgrid');
//     var gridData  = '&teamRoleEmp='+gridVal;	
//     //alert('gridVal:'+gridVal);     
//     var errText = getFilterValue(gridVal+'&', 'errText');
//  	if(errText==null || errText.trim()==""){
//  	}
//  	else{
//  		//alert('errText'+errText);
//  		errFlg=true;	 		
//  	}	
//  	//alert("errFlg"+errFlg);	
//  	if(errFlg==true){
//  		setTimeout(function() {
//  			showCommonErrorMsg(errText);
//  		}, 200);
//  		div_err();
//  		return false;
//  	}
//  	else{
//  	 	//alert('gridData:'+gridData);
//  	 	var level = jQuery("#txtFrlLevel").val();
//  	 	var flid = jQuery("#txtFrlFnlnKeyid").val();	
// 		var roleid=jQuery('#txtRoleId').val();	
//  	 	saveForm('frmRoleTeam','roleteamemp_save.roleteam?title=Trade&flid='+flid+'&roleid='+roleid+'&level='+level+gridData,''); 		
//  	}
// }

function saveEmpTeamTrade(){
    var errText = "";
    var errFlg  = false;
    var gridId  = "empteamgrid";

    // Get the current grid payload exactly as you already do
    var gridVal  = convertJqGridToJSONStrEmpTeam('empteamgrid');
    var gridData = '&teamRoleEmp=' + gridVal; // kept for compatibility with existing variables

    // Existing error extraction stays exactly as-is (reads from the raw string)
    errText = getFilterValue((gridVal || '') + '&', 'errText');
    if (errText != null && errText.trim() !== "") {
        errFlg = true;
    }

    if (errFlg === true){
        setTimeout(function(){ showCommonErrorMsg(errText); }, 200);
        div_err();
        return false;
    } else {
        var level   = jQuery("#txtFrlLevel").val();
        var flid    = jQuery("#txtFrlFnlnKeyid").val();
        var roleid  = jQuery("#txtRoleId").val();

        // --- Encode all query values to satisfy Tomcat 11 (RFC 7230/3986) ---
        // gridVal may be JSON or already-stringified; ensure string & lightly sanitize trailing commas
        var gridValStr = (gridVal == null ? "" : String(gridVal));
        gridValStr = gridValStr.replace(/,\s*]/g, ']'); // tolerate accidental trailing comma in array

        var url = "roleteamemp_save.roleteam"
                + "?title="       + encodeURIComponent("Trade")
                + "&flid="        + encodeURIComponent(flid || "")
                + "&roleid="      + encodeURIComponent(roleid || "")
                + "&level="       + encodeURIComponent(level || "")
                + "&teamRoleEmp=" + encodeURIComponent(gridValStr);

        // Keep your existing submission mechanism
        saveForm('frmRoleTeam', url, '');
        return true;
    }
}

//----------------------- Vignesh Altered for save 08Nov2025 ---------------------------//
function convertJqGridToJSONStrEmpTeam(jqGridId){	
	//alert('convertJqGridToJSONStringArrLocal');
	var rowIds = jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var rowObject = jQuery("#"+jqGridId).getRowData(rowIds[0]);
	var roleid=jQuery("#txtRoleId").val();	
	var flid=jQuery('#txtFrlFnlnKeyid').val();
	var frlkeyid=jQuery('#txtFrlKeyid').val();
	var totalCol = 0;
	var flg=false;	
	var isSelect=false;
	var errMsg="";	
	for(var col in rowObject) totalCol++;
		
		var jsonArrO='[';
		//var jsonArrOTrade='[';
		var jsonArrOTrade='';
		for( var i = 0; i < rowIds.length;i++){		
			var isAdd= jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'select');
			var keyid=jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'keyid');
			var frtfrlkeyid=jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'frlkeyid');
			if (frtfrlkeyid.trim().length>0 && frtfrlkeyid.trim()!='{}'){
				frtfrlkeyid=frlkeyid;
			}
			if (isAdd=='1')
			{
				//alert('keyid'+keyid);
				isSelect=true;
				//if( keyid == null ||  keyid.trim()==""){
				var empmkeyid=jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'empm_keyid');
				//alert('empmkeyid'+empmkeyid); 
				var tradeid= getFieldValue(jqGridId+"cmbTrade_"+rowIds[i]);//jQuery("#"+jqGridId+"cmbTrade_"+rowIds[i]).combobox("getValue");	//jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],"trade");
				//alert('tradeid:'+tradeid);
				var processid = jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],"process"); 
				//alert('processid:'+processid);
				var subprocessid = jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],"subprocess"); 
				//alert('subprocessid:'+subprocessid);
				var subsubprocid = jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],"subsubproc"); 
				//alert('subsubprocid:'+subsubprocid);
				jsonArrO += '{';
				jsonArrO += '"txtFrtKeyid":"' + keyid + '",';	
				jsonArrO += '"txtFrtFrlKeyid":"' + frlkeyid + '",';					
				jsonArrO += '"txtFrtFnlnKeyid":"' + flid + '",';					
				jsonArrO += '"txtFrtRoleKeyid":"' + roleid + '",';					
				jsonArrO += '"txtFrtEmpmKeyid":"' + empmkeyid + '",';						
				jsonArrO += '"txtTrade":"' + tradeid + '",';						
				jsonArrO += '"txtProcessId":"' + processid + '",';					
				jsonArrO += '"txtSubProcessId":"' + subprocessid + '",';					
				jsonArrO += '"txtSubSubProcessId":"' + subsubprocid + '",';					
				jsonArrO += '},';
			}
	}
	if (jsonArrO != "["){
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		//jsonArrOTrade=jsonArrOTrade.slice(0, -1) + "]";
		//jsonArrOTrade="&teamTradeLink="+jsonArrOTrade;
	}
	else
		jsonArrO = ""; 
	//alert('jsonArrO:'+jsonArrO);
	if(isSelect==false){
		errMsg=errMsg + " Select Atleast One Employee, " ;
		flg=true;
	}
	
	if(flg==true){				
		return jsonArrO+"&errText="+errMsg; 
	}
	else{
		return jsonArrO+"&errText="; 
	}		
}
 // ------------ Vignesh 08Nov2025  ------- //
function saveRoleTeam(saveType){
    var flid     = (jQuery("#txtFrlFnlnKeyid").val() || "");
    var level    = (jQuery("#txtFrlLevel").val() || "");
    var roleid   = (jQuery('#txtRoleId').val() || "");
    var frlKeyid = (jQuery('#txtFrlKeyid').val() || "");
    var selected = jQuery('#tabRoleTm').tabs('getSelected');
    var title    = selected ? (selected.panel('options').title || "") : "";

    // Basic validations (kept as-is)
    if (level.trim().length <= 0){
        alert('Select Functional Location Level');
        return false;
    }
    if (saveType == "Employee"){
        if (roleid.trim().length <= 0){
            alert('Select Role');
            return false;
        }
    }

    if (title.trim() == saveType){
        if (saveType == "Role"){
            var gridval = getGridSelectArray('rolegrid');   // may be array or string
            if (gridval == null) gridval = "";
            if (typeof gridval !== "string") {
                try { gridval = JSON.stringify(gridval); } catch(e){ gridval = String(gridval); }
            }

            var url = "rolemappingentry_save.roleteam"
                + "?level="   + encodeURIComponent(level)
                + "&title="   + encodeURIComponent(title)
                + "&flid="    + encodeURIComponent(flid)
                + "&rolemap=" + encodeURIComponent(gridval);

            // keep existing flow
            saveForm('frmRoleTeam', url, '');
        }
        else if (saveType == "Employee"){
            var gridvalEmp = getGridSelectArray('empgrid'); // may be array or string
            if (gridvalEmp == null) gridvalEmp = "";
            if (typeof gridvalEmp !== "string") {
                try { gridvalEmp = JSON.stringify(gridvalEmp); } catch(e){ gridvalEmp = String(gridvalEmp); }
            }

            var urlEmp = "roleteamentry_save.roleteam"
                + "?level="       + encodeURIComponent(level)
                + "&title="       + encodeURIComponent(title)
                + "&flid="        + encodeURIComponent(flid)
                + "&roleid="      + encodeURIComponent(roleid)
                + "&frlkeyid="    + encodeURIComponent(frlKeyid)
                + "&teamRoleEmp=" + encodeURIComponent(gridvalEmp);

            // keep existing flow
            saveForm('frmRoleTeam', urlEmp, '');
        }
    }
    return true;
}
 
// ------------ Vignesh 08Nov2025  ------- //

// function saveRoleTeam(saveType){
// 	var flid = jQuery("#txtFrlFnlnKeyid").val();
// 	var level = jQuery("#txtFrlLevel").val();	
// 	var roleid=jQuery('#txtRoleId').val();	
// 	var frlKeyid=jQuery('#txtFrlKeyid').val();
// 	var selected =jQuery('#tabRoleTm').tabs('getSelected');
// 	//alert('selected:'+selected);
// 	var title = selected.panel('options').title;
	
// 	//alert( 'flid ' + flid + 'level ' +  level + ' roleid '+  roleid + 'frlKeyid ' + frlKeyid + 'selected '  + selected + 'title ' + title )
// 	/*if (flid.trim().length<=0){	 
// 		alert('Select Functional Location');
// 		return false;
// 	}*/
// 	if (level.trim().length<=0){	
// 		alert('Select Functional Location Level');
// 		return false;
// 	}
// 	if (saveType=="Employee"){
// 		if (roleid.trim().length<=0){	
// 			alert('Select Role');
// 			return false;
// 		}	
// 	}
// 	 // ---- ALtered by Vignesh 08Nov 2025 for Integration ---- //
// 	if (title.trim()==saveType){	
// 		if (saveType=="Role"){		
// 			//var gridval=getGridSelectArray('rolegrid');  
// 		 //   var gridData  = '&rolemap='+gridval;
// 		    var params =
// 		    	  "level="     + encodeURIComponent(level)     + "&" +
// 		    	  "title="     + encodeURIComponent(title)     + "&" +
// 		    	  "flid="      + encodeURIComponent(flid)      + "&" +
// 		    	  "roleid="    + encodeURIComponent(roleid)    + "&" +
// 		    	  "frlkeyid="  + encodeURIComponent(frlkeyid)  + "&" +
// 		    	  "teamRoleEmp="+ encodeURIComponent(rolemap); 
		    
// 		    processAjaxCalls("roleteamentry_save.roleteam", params,
// 	                 "saveRoleTeamEmp_success", "saveRoleTeamEmp_error");
// 		//	saveForm('frmRoleTeam','rolemappingentry_save.roleteam?level='+level+'&title='+title+'&flid='+flid+gridData,'');		
// 		}
// 		else if (saveType=="Employee"){			
// 		//	var gridval=getGridSelectArray('empgrid');  
// 		//    var gridData  = '&teamRoleEmp='+gridval;	
// 		    var params =
// 		    	  "level="     + encodeURIComponent(level)     + "&" +
// 		    	  "title="     + encodeURIComponent(title)     + "&" +
// 		    	  "flid="      + encodeURIComponent(flid)      + "&" +
// 		    	  "roleid="    + encodeURIComponent(roleid)    + "&" +
// 		    	  "frlkeyid="  + encodeURIComponent(frlkeyid)  + "&" +
// 		    	  "teamRoleEmp="+ encodeURIComponent(rolemap); 
// 		    processAjaxCalls("roleteamentry_save.roleteam", params,
// 	                 "saveRoleTeamEmp_success", "saveRoleTeamEmp_error");
		    
// 			saveForm('frmRoleTeam','roleteamentry_save.roleteam?level='+level+'&title='+title+'&flid='+flid+'&roleid='+roleid+'&frlkeyid='+frlKeyid+gridData,'');		
// 		}
// 	}
// }

function frmRoleTeam_successsCallback(result)
{
	var title=result.successData.title;
	
	if (title.trim()=="Employee"){	
		jQuery("#empteamgrid").trigger("reloadGrid"); 
		jQuery("#empgrid").trigger("reloadGrid"); 
	}
	else if (title.trim()=="Role"){	
		jQuery("#roleteamgrid").trigger("reloadGrid");	
		jQuery("#rolegrid").trigger("reloadGrid");
		jQuery("#empteamgrid").trigger("reloadGrid"); 
	}
	else if (title.trim()=="Trade"){	
		jQuery("#empteamgrid").trigger("reloadGrid"); 
	}
} 
function LoadFnlnData(flid,level){
	enableBtns();
	showRoleEmpList();		
	jQuery('#txtRoleId').val('');
	jQuery('#txtFrlKeyid').val('');
	processGridnew("roleteamfnln_input.roleteam","?q=2&flid="+flid+'&level='+level,"roleteamgrid","","","","","Load_Complete","","");
	processGridnew("roleall_input.roleteam","?q=2&flid="+flid+'&level='+level,"rolegrid","rolepager","","","","Load_Complete","","");
	processGridnew("empteam_input.roleteam","?q=2&flid="+flid+"&level="+level,"empteamgrid","","","","","empteamgrid_onComplete");
	
}
function getGridRecordCount(gridId){
	var rc= 0;
	try{
		rc = jQuery("#"+gridId).getGridParam("reccount");
	}catch(Ex){
		rc = 0;
	}
	if( rc == undefined ) rc =0;

	return rc;
}

function empteamgrid_onComplete(result){
}

function loadval_searchCallBack(result){
	 //jQuery("#flTreeSearch").css('display','none');
	 var flid=jQuery("#txtFrlFnlnKeyid").val();
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
       //jQuery("#flTreeComponent " + parentIds).addClass("search_parent_flu-s");   
       jQuery("#flTreeComponent").jstree("open_node",parentIds);
       jQuery("#flTreeComponent " + parentIds).addClass("jstree-clicked");  
       var flid=jQuery("#txtFrlFnlnKeyid").val();
       var level=jQuery("#txtFrlLevel").val();       
	   LoadFnlnData(flid,level);	         
   }       
}

function customMenu(node) {	
	  processAjaxCalls("get_image.roleteam","?q=2&nodeId="+jQuery.jstree._focused()._get_node(node).attr("id"),"getImgSuccess","getImgErr");	
}

function getImgSuccess(result)
{
	jQuery('#nodeImage').attr('src','');
	jQuery('#nodeImage').attr('src',result.nodeImg.imgToimBlobimage);	
	jQuery('#hdnBlobimage').val(result.nodeImg.imgToimBlobimage);
	if(screen.width <= 1024)
		setImgWidth( jQuery('#nodeImage'),400,416);
	else		
		setImgWidth( jQuery('#nodeImage'),480,416);
}

function refreshTree()
{
	var tree = jQuery.jstree._reference("#flTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);
}
function formatterChkRoleMap(id, options, rowObject)
{		
	var rowId = options.rowId;	
	return '<input id="roleMap_checkbox_'+rowId+'" name="roleMap_checkbox_" '+ (rowObject[0]&&id=="2" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){roleMapCheck(\''+rowId + '\');}else{roleMapCheckUnCheck(\''+ rowId +'\')}"/>';
}

function radioCheckbox(gridId,columnName,rowId)
{
	var rowIds = jQuery("#"+gridId).getDataIDs();
	for(var i=1;i<=rowIds.length;i++)
	{	
		if(i==rowId){
			jQuery("#"+columnName+"_"+i).prop('checked',true);			
		}
		else{			
			jQuery("#"+columnName+"_"+i).prop('checked',false);
			jQuery("#roleteamgrid").jqGrid('setCell',i,'select','0');
		}		
	}
}

function roleMapCheck(rowId)
{
	//setFormater("roleteamgrid","frmRoleTeam","noOfEmpCombo.roleteam?",rowId,"noofpersons","frl_NoOfPersons","100px",false);
	jQuery("#roleteamgrid").jqGrid('setCell', rowId, 'select', '1');
	radioCheckbox("roleteamgrid","roleMap_checkbox",rowId);
	var roleid=jQuery("#roleteamgrid").jqGrid('getCell', rowId, 'role_keyid');
	var frlkeyid=jQuery("#roleteamgrid").jqGrid('getCell', rowId, 'keyid');
	var rolename=jQuery("#roleteamgrid").jqGrid('getCell', rowId, 'role_name');
	var flid = jQuery("#txtFrlFnlnKeyid").val();	
	var level = jQuery("#txtFrlLevel").val();	
	var location=jQuery("#txtLocation").val();
	
	if (roleid.trim().length>0){
		//alert(roleid);
		showRoleEmpList();		
		jQuery('#txtRoleId').val(roleid);
		jQuery('#spnRole').html(' '+ rolename);
		jQuery('#txtFrlKeyid').val(frlkeyid); 				
		jQuery('#tabRoleTm').tabs('select', "Employee");
		processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+'&level='+level+"&frlkeyid="+frlkeyid,"empteamgrid","","","","","empteamgrid_onComplete");
		processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+'&level='+level+"&frlkeyid="+frlkeyid+"&location="+location,"empgrid","emppager","","","","Load_Complete","","");	  
	}	
	else{
		jQuery('#tabRoleTm').tabs('select', "Role");
	}
}

function roleMapCheckUnCheck(rowId){
	//alert('uncheck');		
	jQuery('#tabRoleTm').tabs('select', "Role");
	jQuery("#roleteamgrid").jqGrid('setCell',rowId,'select','0');
	jQuery('#spnRole').html('');
	var flid=jQuery("#txtFrlFnlnKeyid").val();
	jQuery('#txtRoleId').val('');	
    var roleid=jQuery('#txtRoleId').val();
    var level = jQuery("#txtFrlLevel").val();	
    var frlkeyid=jQuery('#txtFrlKeyid').val(); 
    var location=jQuery('#txtLocation').val();
    
	processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+'&level='+level+"&frlkeyid="+frlkeyid,"empteamgrid","","","","","empteamgrid_onComplete");
	processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+'&level='+level+"&frlkeyid="+frlkeyid+"&location="+location,"empgrid","emppager","","","","Load_Complete","","");	  	
}

function formatterChkRoleEmp(id, options, rowObject)
{		
	var rowId = options.rowId;	
	return '<input id="roleEmp_checkbox_'+rowId+'" name="roleEmp_checkbox_" '+ (rowObject[0]&&id=="2" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){empCheck(\''+rowId + '\');}else{empUnCheck(\''+ rowId +'\')}"/>';
}

function empCheck(rowId)
{
	//alert('check');	
	var arrayVal="";
	var gridId="empteamgrid";
	var tradeId = jQuery("#"+gridId).jqGrid('getCell',rowId,"trade"); 	
	var processId = jQuery("#"+gridId).jqGrid('getCell',rowId,"process"); 
	var subProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subprocess"); 
	var subSubProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subsubproc"); 
		
	setFormater(gridId,"frmRoleTeam","trade.commonFilter?",rowId,"tradename","trade","100px",false);
	if  (tradeId.trim().length>0 ){	
		//alert('tradeId:'+tradeId);
		setFieldValue(gridId+"cmbTrade_"+rowId,tradeId);
		//jQuery("#"+gridId+"cmbTrade_"+rowId).combobox("setValue",tradeId);
		processAjaxCalls("getTradeType.roleteam","&q=2&row="+rowId+"&trade="+tradeId,"TradeSuccessCallBack","TradeErrorCallBack");		
	}
		
 	jQuery("#"+gridId+"cmbTrade_"+rowId).combobox({onSelect:function(record){
		//alert(record.id);	
		processAjaxCalls("getTradeType.roleteam","&q=2&row="+rowId+"&trade="+record.id,"TradeSuccessCallBack","TradeErrorCallBack");
		
	}});
	jQuery("#"+gridId).jqGrid('setCell', rowId, 'select', '1');
}

function TradeSuccessCallBack(result){	
	var trade=result.trade;
	var tradeType=result.tradeType;
	var gridId="empteamgrid";
	var rowId=result.row;
	var arrayVal="";
	var processId = jQuery("#"+gridId).jqGrid('getCell',rowId,"process"); 
	var subProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subprocess"); 
	var subSubProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subsubproc"); 
	if (tradeType=="P"){
		setFormater(gridId,"frmRoleTeam","processCombo.roleteam?",rowId,"processname","process","200px",true);
		/*setFormater(gridId,"frmRoleTeam","subProcessCombo.roleteam?",rowId,"subprocessname","subprocess","250px",true);
		setFormater(gridId,"frmRoleTeam","subSubProcessCombo.roleteam?",rowId,"subsubprocname","subsubproc","250px",true);*/
		
		if  (processId.trim().length>0 ){
			//alert('processId:'+processId);
			setFormater(gridId,"frmRoleTeam","subProcessCombo.roleteam?processId="+processId,rowId,"subprocessname","subprocess","200px",true);
			setFieldValue(gridId+"cmbProcess_"+rowId,processId.toString());
		}
		if  (subProcId.trim().length>0 ){
			//alert('subProcId:'+subProcId);
			setFormater(gridId,"frmRoleTeam","subProcessCombo.roleteam?processId="+processId,rowId,"subprocessname","subprocess","200px",true);
			setFormater(gridId,"frmRoleTeam","subSubProcessCombo.roleteam?subProcessId="+subProcId,rowId,"subsubprocname","subsubproc","250px",true);
			setFieldValue(gridId+"cmbSubProc_"+rowId,subProcId.toString());				
		}
		if  (subSubProcId.trim().length>0 ){			
			setFormater(gridId,"frmRoleTeam","subSubProcessCombo.roleteam?subProcessId="+subProcId,rowId,"subsubprocname","subsubproc","250px",true);
			//alert('subSubProcId:'+subSubProcId);
			setFieldValue(gridId+"cmbSubSubProc_"+rowId,subSubProcId.toString());
		}
		jQuery("#"+gridId+"cmbProcess_"+rowId).combobox({onSelect:function(record){
			//alert(record.id);	
			var processid=getFieldValue(gridId+"cmbProcess_"+rowId);
			var procVal =processid.toString();
			jQuery("#"+gridId).jqGrid('setCell',rowId,"process",procVal);
			setFormater(gridId,"frmRoleTeam","subProcessCombo.roleteam?processId="+processid,rowId,"subprocessname","subprocess","200px",true);
			
			jQuery("#"+gridId+"cmbSubProc_"+rowId).combobox({onSelect:function(record){
				//alert(record.id);
				var subProcessid=getFieldValue(gridId+"cmbSubProc_"+rowId);//jQuery("#"+gridId+"cmbSubProc_"+rowId).combobox("getValues");
				var subProcVal =subProcessid.toString();
				jQuery("#"+gridId).jqGrid('setCell',rowId,"subprocess",subProcVal);
				//alert('bmn subProcessid:'+subProcessid);	
				setFormater(gridId,"frmRoleTeam","subSubProcessCombo.roleteam?subProcessId="+subProcessid,rowId,"subsubprocname","subsubproc","250px",true);
				
				jQuery("#"+gridId+"cmbSubSubProc_"+rowId).combobox({onSelect:function(record){
					//alert(record.id);
					var subsubProcessid=getFieldValue(gridId+"cmbSubSubProc_"+rowId);//jQuery("#"+gridId+"cmbSubProc_"+rowId).combobox("getValues");
					var subsubProcVal =subsubProcessid.toString();
					//alert('subsubProcessid:'+subsubProcessid);
					jQuery("#"+gridId).jqGrid('setCell',rowId,"subsubproc",subsubProcVal);							
				}});	
			}});	
			//setFormater(gridId,"frmRoleTeam","subSubProcessCombo.roleteam?processId="+processid,rowId,"subsubprocname","subsubproc","250px",true);				
		}});	
		
	}
	else{		 	
		//alert("remove formater");
		jQuery("#"+gridId).jqGrid('setCell',rowId,"process",""); 
		jQuery("#"+gridId).jqGrid('setCell',rowId,"subprocess",""); 
		jQuery("#"+gridId).jqGrid('setCell',rowId,"subsubproc","");
		 	
		jQuery("#"+gridId).jqGrid('setCell',rowId,"processname",""); 
		jQuery("#"+gridId).jqGrid('setCell',rowId,"subprocessname",""); 
		jQuery("#"+gridId).jqGrid('setCell',rowId,"subsubprocname","");
		
		removeFormater(gridId,"combo",rowId,"","processname");	
		removeFormater(gridId,"combo",rowId,"","subprocessname");	
		removeFormater(gridId,"combo",rowId,"","subsubprocname");	
	}
}

function TradeErrorCallBack(result){
	
}

function empUnCheck(rowId){
	//alert('uncheck');	
	var gridId="empteamgrid";
	var tradeId = jQuery("#"+gridId).jqGrid('getCell',rowId,"trade"); 	
	var processId = jQuery("#"+gridId).jqGrid('getCell',rowId,"process"); 
	var subProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subprocess"); 
	var subSubProcId = jQuery("#"+gridId).jqGrid('getCell',rowId,"subsubproc");	
	
	removeFormater(gridId,"combo",rowId,tradeId,"tradename");	
	removeFormater(gridId,"combo",rowId,processId,"processname");	
	removeFormater(gridId,"combo",rowId,subProcId,"subprocessname");	
	removeFormater(gridId,"combo",rowId,subSubProcId,"subsubprocname");	
	
	jQuery("#"+gridId).jqGrid('setCell',rowId,'select','0');	
}

function Load_Complete() {

}

</script> 
<div style="margin-left:5px;margin-top:5px;">
	<div id="flTreeLayer" style="float:left;width:313px;height:97%;overflow:auto;background-color:#ffffff;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;" class="sub-cntborder">
		
		<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
		<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
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
	</ul>
 </div>
  		   
<input type="text" style="display:none;" id="hdnBlobimage" name="hdnBlobimage"/>
<form name="frmRoleTeam" id="frmRoleTeam">	
	<div style="padding-left:322px;">
		<div id="divRoleMapping">			
			<div style="float:left;position:relative;width:890px;">	
				<div style="float:left; margin-left:2px;width:auto;">	
				<div>
					<div class="easyui-paddingbfpx" >
					    <label>LOCATION</label>
						<span style="padding-left:80px;"><label>FUNCTIONAL LOCATION</label></span>
					</div>
					<div class="easyui-paddingbfpx"> 
					    <input class="easyui-combo" id="cmbLocation" name="cmbLocation" style="width: 120px;"/>
						<span style="padding-left:18px;">
						    <input class="easyui-combo" id="cmbFlid" name="cmbFlid" style="width: 254px;"/>
						</span>
					</div>
		 		</div>
					<div>
						<span style="padding-left:0px;"><input type="button" title="New Role" value="New"  class="easyui-button" style="height : 20px; width : 30px;" id="btnNewRole"></span>
						<label class="lbl" style='font-weight:bold;'>Roles Assigned For Fuctional Location : <span class="mandatory-lbl" id="spnFnln"></span></label>
					</div>		
					<div>
						<table id='roleteamgrid'></table>						
					</div>
					<div>
						<span style="padding-left:0px;"><input type="button" title="New Employee" value="New"  class="easyui-button" style="height : 20px; width : 30px;" id="btnNewEmp"></span>						
						<label class="lbl" style='font-weight:bold;'>Employees Assigned For Role : <span class="mandatory-lbl" id="spnRole"></span></label>		
						<span style="padding-left:10px;"><input type="button" title="Save Sub Process" value="Save"  class="easyui-button" style="height : 20px; width : 35px;" id="btnSave"></span>				
					</div>
					<div>						
						<table id='empteamgrid'></table>						
					</div>
				</div>
				<span id="showAddRolEmp" display="none">
				<input type="button" id="btnShowAddRolEmp" title="Hide Sub Process" value="<<" >
				</span>
				<div id="divAddRoleEmp">				
					<div style="float:left;margin-left:1px;" >
						<div class="easyui-paddingbfpx" style="margin-top:10px;">
	 						<span><input type="button" title="Show Sub Process" value=">>" id="btnProc"></span> 
						</div>
						<div style="float:left;margin-top:30px;">
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Add Role" value="<<" tool-tip="Add" class="easyui-button" style="height : 21px; width : 30px;" id="btnRoleAdd"></span> 
							</div>	
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Remove Role" value=">>" tool-tip="Remove" class="easyui-button" style="height : 21px; width : 30px;" id="btnRoleRemove"></span> 
							</div>													
						</div>				
						<div id="divR" style="margin-top:240px;">		
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Add Employee" value="<<" class="easyui-button" style="height : 21px; width : 30px;" id="btnAdd"> </span>
							</div>	
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Remove Employee" value=">>" class="easyui-button" style="height : 21px; width : 30px;" id="btnRemove"></span> 
							</div>	
							
						</div>	
					</div>				
					<div style="float:left;padding-left:10px;">				
					<div id="tabRoleTm" class="easyui-tabs" style="width:360px; height : auto;">					  
		   				<div title="Role" style="padding:4px;">
		   					<div>
								<table id='rolegrid'></table>
								<div id='rolepager'>	</div>
							</div>	
		   				</div>	
		   				<div title="Employee" style="padding:4px;">
		   					<div>
								<table id='empgrid'></table>
								<div id='emppager'>	</div>
							</div>		
		   				</div>
					</div>
				</div>
			</div>
		</div>						
	</div>
	  </div>
	<input type="hidden" id="mode" name="mode" value=""/>	
</form>
<input type="hidden" id="txtFrlLevel" name="txtFrlLevel" value="${requestScope.level}"/>
<input type="hidden" id="txtFrlKeyid" name="txtFrlKeyid" value="${requestScope.FrlKeyid}"/>
<input type="hidden" id="frmType" name="frmType" value="${requestScope.frmType}"/>
<input type="hidden" id="txtFrlFnlnKeyid" name="txtFrlFnlnKeyid" value="${requestScope.FlId}"/>
<input type="hidden" id="txtLocation" name="txtLocation" value="${requestScope.location}"/>
<input type="hidden" id="txtRoleId" name="txtRoleId" value="${requestScope.roleId}"/>
<input type="hidden" id="txtOriginalId" name="txtOriginalId" value="${requestScope.OriginalId}"/>
<input type="hidden" id="hdnPrevLevel" />
<input type="hidden" id="hdnPrevLocat" />