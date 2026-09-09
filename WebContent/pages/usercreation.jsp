<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">
	
/*jQuery( "#allmenus" ).click(function() {
	jQuery("#usrCreation").load('all_menus.creat', function(response, status, xhr) {
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  }
	});	
});
jQuery.noConflict();*/

jQuery(document).ready(function(){
	
initialiseForm('frmUsermst');
//alert("U r in usercreation.jsp");
var loc = jQuery("#loc").val();
if(loc=='yes'){
	//alert("Loc :" + loc);
	jQuery("#chbisvalidityreq").hide();
	jQuery("#lblValidity").hide();
	jQuery("#lblForm").hide();
	jQuery("#lblTo").hide();
	jQuery("#dteRange").hide();
	jQuery("#divUserRole").hide();
    jQuery("#userRollGrid").hide();
    jQuery("#Roll").hide();
	
}


	if(screen.width <= 1024)
	{
		jQuery("#Roll").removeClass("padLeft");
		jQuery("#Roll").css("padding-left","90px");
	}
	else if(screen.width<=1280)
		{
		jQuery("#usrCreation").css("width","80%");
		jQuery("#Roll").css("padding-left","90px");
		jQuery("#wrapper").css("width","1094px");
		}	
	else
		{
		jQuery("#Roll").addClass("padLeft");
		}
  	jQuery('#submitForm').val('frmUsermst'); // set the id of form to submit
  	jQuery('#userdet').hide();
  	var url = jQuery('#hiddenUrl').val();
  	//alert(url);
  	fillComboBox("frmUsermst","cmbUsrm_keyid","combo_userid.creat");
  	var key=jQuery("#cmbUsrm_keyid").combobox("getValue");
  	//alert(key);
  	var ccno=jQuery("#cmbUsrm_ccno").combobox("getValue");
  	//var remarks=setFieldValue("txtUsrm_remarks");
  	//alert(ccno);
  	var department=jQuery("#hdndepartment").val();

  	 //jQuery("#cmbUsrm_departmentid").combobox('setValue',department);
  	//jQuery("#cmbUsrm_departmentid").val(department);
  	 
  	if((key!=null) && (key!=undefined)&& (key.length>0) )
  	  {
    	  fillComboBox("frmUsermst","cmbUsrm_ccno","combo_ccno.creat?&ccno="+ccno );
  	  }
  	else
  	  	{
  		  fillComboBox("frmUsermst","cmbUsrm_ccno","combo_ccno.creat");
  	  	}
  	fillComboBox("frmUsermst","cmbUsrm_securitypolicyid","combo_profid.creat" );
  	fillComboBox("frmUsermst","cmbUsrm_departmentid","combo_department.emp" );
  	fillComboBox("frmUsermst","cmbUsrm_designationid","combo_designation.emp" );
  	
  	setTimeout(function () {
  	    var department = jQuery("#hdndepartment").val();
  	    jQuery("#cmbUsrm_departmentid").combobox('setValue', department);
  	}, 500);
  	formatDateBox('dteUsrm_validfrom','dd-MMM-yyyy');
	//fillWithCurrentDate('dteUsrm_validfrom');
	formatDateBox('dteUsrm_validtill','dd-MMM-yyyy');
	//fillWithCurrentDatePloneeighty('dteUsrm_validtill');
	disableField('frmUsermst', 'cmbUsrm_departmentid');
	disableField('frmUsermst', 'cmbUsrm_designationid');
	//jQuery('dteUsrm_validfrom').datebox('setValue',jQuery(vf).val());
	//jQuery('dteUsrm_validtill').datebox('setValue',jQuery(vt).val());
  	//alert(jQuery(hdnUsrm_isvalidityreq).val());
  	//alert(jQuery(vf).val());
  	//lockSetdefaultpass();
  	if(jQuery(hdnUsrm_isvalidityreq).val()=='Y'){
			//jQuery('#chbisvalidityreq').val('on');
			jQuery("#chbisvalidityreq").prop( "checked", true );
			enableFields('dteUsrm_validfrom');
			enableFields('dteUsrm_validtill');
			jQuery('dteUsrm_validfrom').datebox('setValue',jQuery(vf).val());
			jQuery('dteUsrm_validtill').datebox('setValue',jQuery(vt).val());
		/*	if(jQuery(vf).val()=="" && jQuery(vt).val()==""){
				//alert("telling nulling");
				fillWithCurrentDate('dteUsrm_validfrom');
				fillWithCurrentDatePloneeighty('dteUsrm_validtill');
				}
			else {
				//alert(jQuery(requestScope.admTlUsermst.usrm_validtill));
			
				}*/
			//jQuery("#datediv").show();
  	  	}
  	else {
  		//jQuery('#chbisvalidityreq').val('off');
  		jQuery("#chbisvalidityreq").prop( "checked", false );
		//jQuery("#datediv").hide();
  		disableField('frmUsermst', 'dteUsrm_validfrom');
		disableField('frmUsermst', 'dteUsrm_validtill');
		jQuery('dteUsrm_validfrom').datebox('setValue',jQuery(vf).val());
		jQuery('dteUsrm_validtill').datebox('setValue',jQuery(vt).val());
  	  	}
  	jQuery('#frmUsermst .easyui-combobox').css('text-transform', 'uppercase');
   // jQuery('#frmUsermst .easyui-text').css('text-transform', 'uppercase');
  jQuery("#txtUsrm_remarks").css('text-transform', 'uppercase');
   jQuery("#txtUsrm_loginid").css('text-transform', 'uppercase');
  jQuery("#txtUsrm_remarks").css('text-transform', 'uppercase');
   jQuery("#txtUsrm_password").css('text-transform', 'none');
   jQuery("#txtUsrm_defaultpassword").css('text-transform', 'none');
    jQuery('#frmUsermst textarea').css('text-transform', 'uppercase');
    
    numericTextBox('txtUsrm_extensionphone');
    //alert(screen.width);
    
	processGridnew("AddRoll_input.creat","?q=1&userId="+key+"&fromUserform=true","userRollGrid","pager12","","","","loadComplete");

	jQuery('#chbisvalidityreq').click(function() {
		if(jQuery('#chbisvalidityreq').is(':checked') == true){
			//jQuery("#datediv").show();
			enableFields('dteUsrm_validfrom');
			enableFields('dteUsrm_validtill');
			var values='Y';
			setFieldValue("hdnUsrm_isvalidityreq",values);
			if(jQuery(vf).val()=="" && jQuery(vt).val()==""){
				fillWithCurrentDate('dteUsrm_validfrom');
			    fillWithCurrentDatePloneeighty('dteUsrm_validtill');
				}
			else {
				jQuery('dteUsrm_validfrom').datebox('setValue',jQuery(vf).val());
				//setFieldValue("dteUsrm_validtill",values);
				jQuery('dteUsrm_validtill').datebox('setValue',jQuery(vt).val());
				}
			//fillWithCurrentDate('dteUsrm_validfrom');
			//fillWithCurrentDatePloneeighty('dteUsrm_validtill');
			//alert(jQuery('#gift-true').val());	
			}
		else {
			//jQuery("#datediv").hide();
			disableField('frmUsermst', 'dteUsrm_validfrom');
			disableField('frmUsermst', 'dteUsrm_validtill');
			var values='N';
			//alert(values);
			setFieldValue("hdnUsrm_isvalidityreq",values);
			jQuery('dteUsrm_validfrom').datebox('clear');
			jQuery('dteUsrm_validtill').datebox('clear');
			//alert(jQuery('#gift-true').val());
		//	alert(jQuery('#chbisvalidityreq').val());
			}
	});
	 jQuery('#btnuserRole').click(function(event){
	    	
	 		var keyid = getFieldValue('cmbUsrm_keyid');	
	 		
	 		if(keyid.length == 0)
	 		{  
	 			alert(" Select user to add role ");
	 			return false;
	 		}else
	 		{   
	 			 LoadPopUp("divAddRoll","AddRoll_input.creat?userId="+keyid, true,"500px","300px","20px","20%", "multiSelectOk_Callback","User Role Link");
	 		}
				//saveForm("frmUsermst","userform_input.creat?from=btnClick");
								
	});
	 
	/*
    jQuery('#btnuserRole').click(function(event){
       
		//alert('button clicked');
			//jQuery("#from").val("btnClick");
			//alert(jQuery("#from").val());
			saveForm("frmUsermst","userform_input.creat?from=btnClick");
			
		//LoadPopUp("divAddRoll","AddRoll_input.creat?userId="+key, true,"500px","300px","20px","20%", "multiSelectOk_Callback","User Role Link");});
   */
   /* 
   */
    
			/*jQuery("#list").jqGrid({
						datatype: "local",
						colNames:[ 'Menu Caption','Save','Level','Parent Name'],
						colModel:[ {name:'menuCaption',index:'menuCaption',editable:false, width:400},
								   {name:'save',index:'save',editable:false, width:40},
								   {name:'level',index:'level',editable:false, width:60},
								   {name:'parentName',index:'parentName',editable:false, width:400},
								  ],
   							    data:[
									  {id:"1", kaizenDate:"2007-10-01",pillarName:"KOBETSU KAIZEN", pillarCode:"KK", closed:true},
									  {id:"2", kaizenDate:"2007-10-02",pillarName:"QUALITY MAINTENANCE", pillarCode:"QM",closed:false},
									  {id:"3", kaizenDate:"2007-09-01",pillarName:"EDUCATION AND TRAINING", pillarCode:"ET",closed:false},
									  {id:"4", kaizenDate:"2007-10-04",pillarName:"SAFETY,HEALTH AND ENVIRONMENT", pillarCode:"SHE",closed:true },
									  {id:"5", kaizenDate:"2007-10-31",pillarName:"OFFICE TPM", pillarCode:"OTPM",closed:false},
									  {id:"6", kaizenDate:"2007-10-01",pillarName:"DEVELOPEMENT MANAGEMENT", pillarCode:"DM", closed:true},
									  {id:"7", kaizenDate:"2007-10-02",pillarName:"JISHU HOZEN", pillarCode:"JH", closed:false},
									  {id:"8", kaizenDate:"2007-09-01",pillarName:"PLANNED MAINTENANCE", pillarCode:"PM",closed:false},
									 ],		  
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'',
								width:970,
								height:300,
								loadonce: true,
								gridComplete: function()
								{ 
									var ids = jQuery("#list").jqGrid('getDataIDs'); 
									for(var i=0;i < ids.length;i++)
										{ 
											var cl = ids[i]; 
											be = "<input  id='savechk"+i+"' type='checkbox'/>";
											jQuery("#list").jqGrid('setRowData',ids[i],{save:be});
											//jQuery("#list").setCaption("<input  style='float:right;' class='button' type='button' value='All Menus'/>");
										 }
								}
								
							});*/
							
	//	});
	
   		jQuery("#btnresetpwd").click(
		function() {
				
		var userid = getFieldValue('cmbUsrm_keyid','frmUsermst');
		    				
		saveForm("frmUsermst","resetpwd_save.creat?userid="+userid,"");
		});
	
		
	});
/*function viewGrid(urlUserKeyid,filterString)
{
	alert(filterString);
	alert(urlUserKeyid);
	
	if( validateFilterSelection(filterString))
	{	alert('inside validate');
		
		processGridnew("AddRoll_input.creat?userId="+urlUserKeyid,filterString,"userRollGrid","pager12","","","","loadComplete");
		return true;
	}	
}*/
jQuery("#btnEmplink").click(
		function() {			
			var keyId = getFieldValue('cmbUsrm_ccno','frmUsermst');	
				
			LoadPopUp("emplink","emp_input.emp?q=2&keyId="+keyId,true,"90%","80%","20px","20px","","");
			
		});
function loademp()
{
	emp_input.emp
}
function loadComplete(){

	jQuery(".ui-paging-info").css('font-size',10);
	if(screen.width <= 1024 || screen.width <=1280)
		jQuery( "#userRollGrid" ).setGridWidth(250);	
	else
		jQuery( "#userRollGrid" ).setGridWidth(250);
}
function divAddRoll_afterClose()
{//alert('popup closed');
	jQuery("#userRollGrid").trigger("reloadGrid");
	}
function button_AddRoll(id, options, rowObject)
{		//alert('id='+id);		
	var rowId = options.rowId;
	//alert("rowId"+rowId);
	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
}
function lockSetdefaultpass()
{
	var loginId=jQuery("#txtUsrm_loginid").val();
    //alert(loginId);
    jQuery("#txtUsrm_password").val(loginId);
    jQuery("#txtUsrm_defaultpassword").val(loginId);
	
}
function removeOperator(rowId){	
    //alert("rowId"+rowId);
	var rowData = jQuery("#userGrid").jqGrid('getRowData',rowId);
	var keyId = rowData.ROLLID;
	//alert("keyId"+keyId);
	var conFdelete = confirm("Do You Want To Delete");
	if(conFdelete){
		if( keyId != null && keyId.length > 0  ){	
			processAjaxCalls("userRoll_delete.creat","&Id="+keyId+"&rowId="+rowId ,'UserRoll_successCallBack','UserRoll_errorCallBack');
			// deleteRecord('frmAddRoll','userRoll_delete.creat?&Id='+keyId+'&rowId='+rowId);
			//jQuery("#batchGrid").delRowData(rowId);
		}
	}
}       
function validateFilterSelection(filterString){
	return  true;
}


function frmUsermst_beforeDelete()
{
			var delMsg = "Do You Want To Delete This User Data ("+jQuery('#cmbUsrm_keyid').combobox('getValue')+")";
			if(confirm(delMsg) == false)
			{
				return false;
			}
}
function UserRoll_successCallBack(result)
{
	//alert('in success delete');
	jQuery("#userRollGrid").trigger("reloadGrid");	
	
}
/*$(function(){
    $('#chbisvalidityreq').change(function() {
        $("#gift-true").val(($(this).is(':checked')) ? "Y" : "N");
    });
});*/
/*function chkValidity(){
	jQuery('#chbisvalidityreq').click(function() {
		if(jQuery('#chbisvalidityreq').is(':checked') == true){
			jQuery("#datediv").show();
			var values='Y';
			setFieldValue("hdnUsrm_isvalidityreq",values);
			//alert(jQuery('#gift-true').val());	
			}
		else {
			jQuery("#datediv").hide();
			var values='N';
			setFieldValue("hdnUsrm_isvalidityreq",values);
			//alert(jQuery('#gift-true').val());
		//	alert(jQuery('#chbisvalidityreq').val());
			}
	});
}*/
	function UserRoll_errorCallBack()
	{
		}
var oldPassword=jQuery("#txtUsrm_password").val();
var defpassword=jQuery("#txtUsrm_defaultpassword").val();
function chkEnabled()
{
	//alert('chkEnabled');
	var isChecked=false;
	
	//alert(oldPassword);
	
	var isChecked = jQuery('#chkIsdefPwd').is(':checked');
	//alert(isChecked);
	if(isChecked==true)
     {
	// alert('inside true');
     var loginId=jQuery("#txtUsrm_loginid").val();
   
     jQuery("#txtUsrm_password").val(loginId);
     jQuery("#txtUsrm_defaultpassword").val(loginId);
     jQuery("#txtUsrm_password").attr('readonly','readonly');
 	jQuery("#txtUsrm_defaultpassword").attr('readonly','readonly');
     }
	else
		{
		//alert('unchkd');
		jQuery("#txtUsrm_password").val(oldPassword);
		jQuery("#txtUsrm_defaultpassword").val(defpassword);
		jQuery("#txtUsrm_password").attr('readonly',false);
	 	jQuery("#txtUsrm_defaultpassword").attr('readonly',false);
		}
	
	}
function frmUsermstcmbUsrm_keyid_onLoadSuccess()
	{
	}
		
	function  frmUsermstcmbUsrm_keyid_onSelect(record)
	{
		//alert(record.id);
		
		processAjaxCalls("user_recall.creat","keyId="+record.id, "frmUsermstcmbUsrm_keyid_RecallsuccessCallback","frmUsermstcmbUsrm_keyid_RecallerrorCallback");
	}
	function  frmUsermstcmbUsrm_ccno_onSelect(record)
	{
		//alert(record.USRM_USERNAME);
		
		processAjaxCalls("userccno_recall.creat","keyId="+record.id, "frmUsermstcmbUsrm_ccno_RecallsuccessCallback","frmUsermstcmbUsrm_ccno_RecallerrorCallback");
	}
	function frmUsermstcmbUsrm_keyid_successsCallback(result)
	{
			jQuery("#cmbUsrm_keyid").combobox("clear");
			//alert("frmCompany");
		reloadCombo("frmUsermst","cmbUsrm_keyid","combo_userid.creat");
	}	
	
	 function frmUsermstcmbUsrm_keyid_RecallsuccessCallback(result)
	 {
		alert('inside success callback');
		jQuery("#txtUsrm_username").val(result.Userdata.Usrm_username);
	
		jQuery("#cmbUsrm_ccno").combobox("setValue",result.Userdata.Usrm_ccno);
		jQuery("#cmbUsrm_securitypolicyid").combobox("setValue",result.Userdata.Usrm_securitypolicyid);
		jQuery("#txtUsrm_password").val(result.Userdata.Usrm_password);
		jQuery("#txtUsrm_defaultpassword").val(result.Userdata.Usrm_defaultpassword);
		jQuery("#cmbUsrm_departmentid").combobox("setValue",result.Userdata.Usrm_departmentid);	
		jQuery("#cmbUsrm_designationid").combobox("setValue",result.Userdata.Usrm_designationid);
		jQuery("#txtUsrm_loginid").val(result.Userdata.Usrm_loginid);
		jQuery("#txtUsrm_extensionphone").val(result.Userdata.Usrm_extensionphone);
		// alert("#txtUsrm_remarks"+txtUsrm_remarks);
		jQuery("#txtUsrm_remarks").val(result.Userdata.Usrm_remarks);
		
		
	 }
	 function frmUsermstcmbUsrm_ccno_RecallsuccessCallback(result)
	 {
		 alert('inside recall');
		 //alert(result.ccNodata.UserDept);
		 //jQuery("#cmbUsrm_departmentid").combobox("setValue",result.ccNodata.UserDept);
		 //jQuery("#cmbUsrm_designationid").combobox("setValue",result.ccNodata.UserDesc);
		 setFieldValue("cmbUsrm_departmentid",result.ccNodata.UserDept);
		 setFieldValue("cmbUsrm_designationid",result.ccNodata.UserDesc);
		 jQuery("#txtUsrm_username").val(result.ccNodata.UserName);
		 jQuery("#txtUsrm_loginid").val(result.ccNodata.Loginid);
		 
		 
	 }
	 function frmUsermstcmbUsrm_ccno_RecallerrorCallback(result)
	 {
		//alert("Error in callback");
	 }
	
	 function frmUsermstcmbUsrm_keyid_RecallerrorCallback(result)
	 {
		//alert("Error in callback");
	 }
	 function frmUsermstcmbUsrm_keyid_deleteSuccessCallback(result)
	 {  
		alert(result.successData.msg);
	 }
	 function  frmUsermst_successsCallback(result)
	 {
		// alert('in call back');
		// alert(result.displyMsg);
	//alert(result.successData.from);
	//alert(result.successData.UserId);
	
		//var from=jQuery("#from").val();
		//alert(from);
		if (getFieldValue("cmbUsrm_keyid").length==0) 
		 	jQuery("#cmbUsrm_keyid").combobox("setValue",result.successData.UserId);
		
		if(result.successData.from=="btnClick")
				 LoadPopUp("divAddRoll","AddRoll_input.creat?userId="+result.successData.UserId, true,"500px","300px","20px","20%", "multiSelectOk_Callback","User Role Link");
		 }
	 function frmUsermst_beforeSubmit()
	 {   
		 var password = getFieldValue('txtUsrm_password');
		 //alert(password);
		 var chkIsdefPwd = getChkBoxVal("chkIsdefPwd");
		 var confPwd=jQuery("#txtUsrm_defaultpassword").val();
		 var pwdData = '&confPwd='+confPwd+"&chkIsdefPwd="+ (chkIsdefPwd == "1" || chkIsdefPwd == 1 ? 'Y':'N');
		 
		 return pwdData;
	 }

	
		</script>
		<style>
		.padLeft
		{
		padding-left:70px;
		}
		
		</style>
<form name="frmUsermst" id="frmUsermst" >
<div id="wrapper" style="" >
<div class="main-cntborder" id="usrCreation" >
<!--<div id="usrCreation"  style="width: 1100px;margin: 0 auto;border-style:solid;border-width:thin;height:740px;">-->
	<table  border="0" width="80%" id="userCreation" style="margin-top:10px">
		<tr>
<!--left  pane -->
			<td style="width:50%" valign="top">
				<div id="userdet">
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label> User ID </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input id="cmbUsrm_keyid" name="cmbUsrm_keyid" class="easyui-combobox"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_keyid}" <c:out value = "${requestScope.userBean.disableUserKeyid == true ? 'disabled':''}"/> />
					</div>
				</div><!--    #userdet div ends here
				     
					--><div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label> User Name </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input type="text" id="txtUsrm_username" name="txtUsrm_username"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_username}" class="easyui-text" >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> CC No </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input id="cmbUsrm_ccno" name="cmbUsrm_ccno" class="easyui-combobox"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_ccno}"  >
								
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> Profile Id </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input id="cmbUsrm_securitypolicyid" name="cmbUsrm_securitypolicyid" class="easyui-combobox"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_securitypolicyid}"  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> Login ID </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input type="text" id="txtUsrm_loginid" name="txtUsrm_loginid"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_loginid}" class="easyui-text" >
					</div>
					
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> Password </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input type="password" id="txtUsrm_password" name="txtUsrm_password" autocomplete="off"  style="width:120px;" value="${requestScope.admTlUsermst.usrm_password}" class="easyui-text" >
							 <span  style="margin-left: 5px;"> 
							 	<input id="chkIsdefPwd" type="checkbox" onchange="chkEnabled()"/>
							 	<span style="margin-left:5px;">
							 	<label class="mandatory-lbl">Default Password</label></span>
							 </span>
					</div>
					
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> Confirm Password </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input type="password" id="txtUsrm_defaultpassword" name="txtUsrm_defaultpassword"  autocomplete="off"  style="width:120px;" value="${requestScope.admTlUsermst.usrm_password}" class="easyui-text" >
							<span  style="margin-left: 5px;"> 
							 	<input type="button" class="easyui-button"   id="btnresetpwd" name="btnresetpwd" style="height:21px; width : 114px;"value="Release Lock" />
							 </span>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:120px;width:200px">
					<input type="checkbox" id="chbisvalidityreq" value="Y" />
					<span><label id="lblValidity"> Validity Required ?</label></span>		
					</div>
					<div id="datediv">
					<div class="easyui-paddingbfpx" style="padding-left:120px;width:300px">
					<label id="lblForm"> From</label>
					<span style="margin-left:80px;"><label id="lblTo"> To </label></span>
					</div>
					<div id = "dteRange" class="easyui-paddingbfpx" style="padding-left:120px;width:300px">
					<input class="easyui-paddingbfpx" id="dteUsrm_validfrom"  name="dteUsrm_validfrom" clear="false" class="easyui-datebox" value="${requestScope.admTlUsermst.usrm_validfrom}" style="width: 100px;" />
					<span style="margin-left:10px;"><input class="easyui-paddingbfpx" id="dteUsrm_validtill"  name="dteUsrm_validtill" clear="false" class="easyui-datebox" value="${requestScope.admTlUsermst.usrm_validtill}" style="width: 100px;" /></span>
					<div id="err_dteUsrm_validfrom" class="tpm-errormsg" style="display: block;"> </div>
					<div id="err_dteUsrm_validtill" class="tpm-errormsg" style="display: block;"> </div>
					</div>
					</div>
					<div id="Status"  style="margin-left:120px;">
					<label><b>Remarks</b></label>
				<div>
	         		<textarea style="width: 250px; height : 60px;" id="txtUsrm_remarks" maxlength="250" name="txtUsrm_remarks" style="width: 200px; height: 21px;">${requestScope.admTlUsermst.usrm_remarks}</textarea>
			    </div>
			    </div>
					
			</td>
			<td style="width:56%" valign="top">
			
					<div class="easyui-paddingbfpx" style="padding-left:90px;">
							<label> Department</label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:90px;"> 
							<input id="cmbUsrm_departmentid" name="cmbUsrm_departmentid" class="easyui-combobox"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_departmentid}"  >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:90px;">
							<label> Designation </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:90px;"> 
							<input id="cmbUsrm_designationid" name="cmbUsrm_designationid" class="easyui-combobox"  style="width:255px;" value="${requestScope.admTlUsermst.usrm_designationid}"  >
					</div>
					
					
					<div id="divUserRole" style="padding-left:90px; width:200px">
						<div class="sub-header" style="text-align: left;width:248px"><span style="position:relative;">User Role</span>
							<span id="rolbtn" style="position:absolute;margin-left:148px;">
<!--								<input type="button" value="..." style="height:21px;"  class=" easyui-button" id="btnprogRole" name="btnprogRole"/>-->
									<img  class="" style="cursor: pointer;z-index:210;margin-top:-3" src="images/addbtsub.png" title="Add Role" alt="" id="btnuserRole">
							</span>
							</div></div>
					 <div  id="Roll" style="width:80%;height:30px;margin-top:11px;padding-left:90px;">		
					<table id="userRollGrid"  >
							<tr><td><td/></tr></table>
							<!--<div id="pager12"></div>-->
					</div> 
					
					<!-- <div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label> From User </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<input type="text" id="fromusr" name="fromusr"  style="width:255px;" value="" class="easyui-text" >
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
							<label class="mandatory-lbl"> Is Admin </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:120px;"> 
							<select id="isadmin" class="easyui-combobox" name="isadmin" style="width:70px;" required="true">
								<option value="-"> N </option>
							</select> 
							 <span  style="margin-left: 5px;"> 
							 	<input id="lockpwdchkbox" type="checkbox"/> <span style="margin-right:2px;">Lock Password on Failure Attempts</span>
					</span>
					</div> -->
			

			</td>
			<td style="width:500px;" valign="top">
			  
			</td>
		</tr>
	</table>
	<!--  <div style="float:right;padding-right:100px;">
		<input type="button" id="allmenus"  class="easyui-button" onclick="" value="All Menus"/>
	</div>
	<div style="padding-top: 40px;"> 
	 <table id="list" style="width:100%"><tr><td/></tr></table>
	<div id="pager"></div>
	</div>-->
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnUsrm_isvalidityreq" name="hdnUsrm_isvalidityreq" value="${requestScope.admTlUsermst.usrm_isvalidityreq}"/>
	<input type="hidden" id="vf" name="vf" value="${requestScope.admTlUsermst.usrm_validfrom}"/>
	<input type="hidden" id="vt" name="vt" value="${requestScope.admTlUsermst.usrm_validtill}"/>
	<input type="hidden" id="from" name="from" />
	<input type="hidden" id="loc" value="${requestScope.loc}" />
    <input type="hidden" id="hdndepartment" value="${requestScope.department}" />
	</div>
</div></form>
