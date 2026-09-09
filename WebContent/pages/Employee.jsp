<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
 jQuery(document).ready(function(){
      
	 var dateTime = null;
	 jQuery("#hdnDateTime").val(dateTime);
	 initialiseForm('frmEmployee'); 	
	 jQuery("#chk").hide();		
	 var url = jQuery('#hiddenUrl').val();	

	 
			
	//jQuery("#btnimgEmpAdd").css("display","none");
	//jQuery("#btnimgEmpDel").css("display","none");
	 	
		jQuery('#submitForm').val('frmEmployee'); // set the id of form to submit	
		//checkBoxSel();
		fillComboBox("frmEmployee","cmbEmpmKeyid","Combo_Employee.emp" );			
		fillComboBox("frmEmployee","cmbEmpmFactoryid","factroyCombo.commonFilter" );
		fillComboBox("frmEmployee","cmbEmpmDepartmentid","combo_department.emp" );	
		
		fillComboBox("frmEmployee","cmbEmpmCompany","combo_company.emp" );		
		fillComboBox("frmEmployee","cmbEmpmLocation","combo_location.emp" );
		
		fillComboBox("frmEmployee","cmbEmpmDesignationid","combo_designation.emp");			
		fillComboBox("frmEmployee","cmbEmpmSectionid","sectionCombo.commonFilter");			
		fillComboBox("frmEmployee","cmbEmpmCellid","cellCombo.commonFilter");		
		fillComboBox("frmEmployee","cmbEmpmGradeid","Combo_Gradeid.emp");
		fillComboBox("frmEmployee","cmbEmpmTradeid","Combo_Tradeid.emp");
		fillComboBox("frmEmployee","cmbEmpdCityid","Combo_Cityid.emp");
		fillComboBox("frmEmployee","cmbEmpdStateid","Combo_Stateid.emp");
		fillComboBox("frmEmployee","cmbEmpdCountryid","Combo_Countryid.emp");	
		fillComboBox("frmEmployee","cmbEmpmRoleid","roleMst.commonFilter");	
		fillComboBox("frmEmployee","cmbEmpmEmployeetype","combo_EmployeeCategory.emp");	
		
		jQuery('#frmEmployee .easyui-text').css('text-transform', 'uppercase');
		jQuery('#frmEmployee textarea').css('text-transform', 'uppercase');
		
		formatDateBox('dteEmpmJoineddate','dd-MMM-yyyy');
		formatDateBox('dteEmpdBirthdate','dd-MMM-yyyy');
	
		 //jQuery("#btnDept").attr("disabled", true);
		 //jQuery("#btnDesg").attr("disabled", true);	
		
		 var dlgimage =  jQuery("#btnimgEmpAdd");
		 imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	
		
		
		 if(url=="emp_input.emp"){
			 
				fillWithCurrentDate('dteEmpmJoineddate');
		 }
		 //comment by sriram oct-22-2025
		 if(jQuery('#cmbEmpmKeyid').combobox('getValue') != null && jQuery('#cmbEmpmKeyid').combobox('getValue') != '')
		{
			processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbEmpmKeyid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
		}
 
 //sriram end
		
		 /*jQuery("#btnimgEmpAdd").click(function(){		
			 
			 var dlgimage =  jQuery("#btnimgEmpAdd");
			 	imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	  
			});*/

		jQuery("#btnimgEmpDel").click(function(){	
				
			jQuery('#imgEmployee').attr('src', "images/EmpDefaultImg.jpg");	   		
			});	
	
		//-----------------------Number only TextBox field---------------------// 

		  
		 numericTextBox('txtEmpdCurrentexperience');
		 numericTextBox('txtEmpdOtherexperience');
		 //numericTextBox('txtEmpmExtensionphone');
		// numericTextBox('txtEmpmFax');
		// numericTextBox('txtEmpmMobile');
		// numericTextBox('txtEmpdPhone');
		//textBox('txtEmpmExtensionphone');
		//textBox('txtEmpmFax');
		//textBox('txtEmpmMobile');
		//textBox('txtEmpdPhone');

		// nemericCheck('txtEmpmMobile');
		 jQuery('.tabs-panels').css('height','380');
		
		 disableField("frmEmployee",'chkEmpmActive'); 
		 jQuery("#frmEmployeeinput[id=chkEmpmActive ]").attr('disabled',true);
		 
		 var catg = jQuery("#hdnCatg").val();	
		 var active = jQuery("#hdnactive").val();
		 
		// if(active=="N")
		 	//jQuery("#chkEmpmActive").attr('checked',true);
		// jQuery("#cmbEmpmEmployeetype").val(catg);
		  jQuery("#cmbEmpmEmployeetype").combobox("setText",catg);
		  var type = jQuery("#cmbEmpmEmployeetype").combobox("getValue");
			
			if(type=="C")
			{		
				jQuery("#lblEmpmCode").removeClass('mandatory-lbl');
				jQuery("#lblEmpmEmployeenumber").removeClass('mandatory-lbl');				
				//disableField("frmEmployee",'txtEmpmCode');
				disableField("frmEmployee",'txtEmpmEmployeenumber');
				enableFields("chkEmpmActive");
				
				if(jQuery("#chkEmpmActive").is(':checked') == true)
					jQuery("#chkEmpmActive").val("N");
				else
					jQuery("#chkEmpmActive").val("Y");
			}

			currentExperience();
			totalExperience();

			//var dataStr ='?q=2&empmid='+roleid;
			
			processGridnew(url,"&S=2","roleempgrid","roleemppager","","","","roleEmpGrid_onComplete");	
 });
 
 function roleEmpGrid_onComplete(result){
 }
 
 function dteEmpmJoineddate_onSelect(date)
 {              	
	 currentExperience();
	 totalExperience();
 }
 function currentExperience(){
	 var joinDate;			
		joinDate = jQuery('#dteEmpmJoineddate').datebox('getValue');			
		if(joinDate=='')
			jQuery('#txtEmpdCurrentexperience').val("0");
		else{
			var dateTime = jQuery("#hdnDateTime").val();
			
			if(dateTime == null || dateTime.trim() == "" )
				dateTime = getServerDateTime();
			
				var fm = dateTime.getMonth();
				var fromMon = getMonthStringFromInt(fm);
				var currDate = dateTime.getDay()+"-"+fromMon+"-"+dateTime.getFullYear();	
				oDiff = timeDifference(joinDate,currDate);	
				jQuery('#txtEmpdCurrentexperience').val(oDiff.years);
			}


		
	 }
 function totalExperience()
 {
	 var x= jQuery("#txtEmpdCurrentexperience").val();
		var y=jQuery("#txtEmpdOtherexperience").val();
		var z;
		if(x=="")
			x=0;
		else if(y=="")
			y=0;
		 z=parseInt(x)+parseInt(y);			 
		 if(x=="" && y=="")
				z=0;
		jQuery("#txtEmpdTotalexperience").val(z);
}
 
 /* function EmpmEmployeetype_onchange(){
	var type = jQuery("#cmbEmpmEmployeetype").combobox("getValue");
	alert("Type"+type);
	if(type=="C"){		
		jQuery("#lblEmpmCode").removeClass('mandatory-lbl');
		jQuery("#lblEmpmEmployeenumber").removeClass('mandatory-lbl');
		clearField('txtEmpmCode');
		clearField('txtEmpmEmployeenumber');
		disableField("frmEmployee",'txtEmpmCode');
		disableField("frmEmployee",'txtEmpmEmployeenumber');
		if(jQuery("#chkEmpmActive").is(':checked') == true)
			jQuery("#chkEmpmActive").val("N");
		else
			jQuery("#chkEmpmActive").val("Y");
	}
	else
		{
		jQuery("#lblEmpmCode").addClass('mandatory-lbl');
		jQuery("#lblEmpmEmployeenumber").addClass('mandatory-lbl');
		enableFields('txtEmpmCode');
		enableFields('txtEmpmEmployeenumber');
		}
 } */
 
 jQuery("#cmbEmpmEmployeetype").combobox({  	   
		onSelect:function(recordid)
			{ 
			var type = jQuery("#cmbEmpmEmployeetype").combobox("getValue");
			//alert("Type"+type);
			if(type=="C"){		
				jQuery("#lblEmpmCode").removeClass('mandatory-lbl');
				jQuery("#lblEmpmEmployeenumber").removeClass('mandatory-lbl');
				clearField('txtEmpmCode');
				clearField('txtEmpmEmployeenumber');
				disableField("frmEmployee",'txtEmpmCode');
				disableField("frmEmployee",'txtEmpmEmployeenumber');
				if(jQuery("#chkEmpmActive").is(':checked') == true)
					jQuery("#chkEmpmActive").val("N");
				else
					jQuery("#chkEmpmActive").val("Y");
			}
			else
				{
				jQuery("#lblEmpmCode").addClass('mandatory-lbl');
				jQuery("#lblEmpmEmployeenumber").addClass('mandatory-lbl');
				enableFields('txtEmpmCode');
				enableFields('txtEmpmEmployeenumber');
				}
			} 
			
		}); 
			
 
 
 function  frmEmployeecmbEmpmKeyid_onSelect(record)
 {
	getEmployee(record.id);	
 }
 function getEmployee(keyid){	 
	 processAjaxCalls("recall_select.emp" ,"EmployeeKeyId="+keyid, "frmEmployeeSelect_successsCallback","frmEmployee_errorCallback");	 
 }

 function frmEmployeeSelect_successsCallback(result)
 {
	 	//alert("frmEmployeeSelect_successsCallback");
	 	var empmId=result.employeeData.EmpmKeyid;//getFieldValue('cmbEmpmKeyid');
		//alert('empmId:'+empmId);
		jQuery("#cmbEmpmFactoryid").combobox("setValue", result.employeeData.EmpmFactoryid);
		setFieldValue("txtEmpmName",result.employeeData.EmpmName,"frmEmployee");
	 	//jQuery("#txtEmpmName").val(result.employeeData.EmpmName);
		jQuery("#frmEmployee input[id='txtEmpmCode']").val(result.employeeData.EmpmCode);			
		jQuery("#frmEmployee input[id='txtEmpmPersonalinfo']").val(result.employeeData.EmpmPersonalinfo);			
	 	jQuery("#frmEmployee input[id='dteEmpmJoineddate']").datebox("setValue",result.employeeData.EmpmJoineddate);		 	
		//jQuery("#txtEmpmFax").val(result.employeeData.EmpmFax);		
		jQuery("#frmEmployee input[id='cmbEmpmDesignationid']").combobox("setValue", result.employeeData.EmpmDesignationid);
		jQuery("#frmEmployee input[id='cmbEmpmCellid']").combobox("setValue", result.employeeData.EmpmCellid);
		jQuery("#frmEmployee input[id='cmbEmpmGradeid']").combobox("setValue", result.employeeData.EmpmGradeid);			
		jQuery("#frmEmployee input[id='cmbEmpmTradeid']").combobox("setValue", result.employeeData.EmpmTradeid);
		jQuery("#frmEmployee input[id='txtEmpmEmployeenumber']").val(result.employeeData.EmpmEmployeenumber);
		jQuery("#frmEmployee input[id='cmbEmpmEmployeetype']").combobox("setValue", result.employeeData.EmpmEmployeetype);			
		jQuery("#frmEmployee input[id='cmbEmpmDepartmentid']").combobox("setValue", result.employeeData.EmpmDepartmentid);	
		jQuery("#frmEmployee input[id='txtEmpmSkillcategory']").val(result.employeeData.EmpmSkillcategory);
		jQuery("#frmEmployee input[id='txtEmpmRemarks']").val(result.employeeData.EmpmRemarks);
		jQuery("#frmEmployee input[id='cmbEmpmSectionid']").combobox("setValue", result.employeeData.EmpmSectionid);
		jQuery("#frmEmployee input[id='txtEmpmMobile']").val(result.employeeData.EmpmMobile);			
		jQuery("#frmEmployee input[id='cmbEmpmRoleid']").combobox("setValue", result.employeeData.EmpmRoleid);	
		jQuery("#frmEmployee input[id='txtEmpmEmail']").val(result.employeeData.EmpmEmail);	
		jQuery("#frmEmployee input[id='cmbEmpmCompany']").combobox("setValue", result.employeeData.EmpmCompany);
		jQuery("#frmEmployee input[id='cmbEmpmLocation']").combobox("setValue", result.employeeData.EmpmLocation);	
		jQuery("#frmEmployee input[id='hdnEmpmSbuId']").val(result.employeeData.EmpmSbuId);		
		jQuery("#frmEmployee input[id='txtEmpmExtensionphone']").val(result.employeeData.EmpmExtensionphone);
		jQuery("#frmEmployee input[id='txtEmpmIscellmanager']").val(result.employeeData.EmpmIscellmanager);
		jQuery("#frmEmployee input[id='txtEmpmIssectionmanager']").val(result.employeeData.EmpmIssectionmanager);		
		jQuery("#frmEmployee input[id='chkEmpmActive']").val(result.employeeData.EmpmActive);			
		processGridnew("emp_input.emp?keyId="+empmId,"&S=2","roleempgrid","roleemppager","","","","roleEmpGrid_onComplete");
		 //comment by sriram oct-22-2025
		processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbEmpmKeyid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");			
		
		//alert('result.employeeDtl.EmpdCurrentexperience:'+result.employeeDtl.EmpdCurrentexperience);
		jQuery("#txtEmpdCurrentexperience").val(result.employeeDtl.EmpdCurrentexperience);
		jQuery("#txtEmpdOtherexperience").val(result.employeeDtl.EmpdOtherexperience);
		jQuery("#txtEmpdTotalexperience").val(result.employeeDtl.EmpdTotalexperience);
		jQuery("#dteEmpdBirthdate").datebox("setValue",result.employeeDtl.EmpdBirthdate);
		jQuery("#txtEmpdAddress").val(result.employeeDtl.EmpdAddress);
		jQuery("#cmbEmpdCityid").combobox("setValue",result.employeeDtl.EmpdCityid);			
		jQuery("#cmbEmpdStateid").combobox("setValue",result.employeeDtl.EmpdStateid);			
		jQuery("#cmbEmpdCountryid").combobox("setValue",result.employeeDtl.EmpdCountryid);			
		jQuery("#txtEmpdPhone").val(result.employeeDtl.EmpdPhone);					
		jQuery("#txtEmpdRemarks").val(result.employeeDtl.EmpdRemarks);		
		//checkBoxSel();	
		//processAjaxCalls("get_image.funlocn","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImgSuccess","getImgErr");
		
 }

 var employee=jQuery("#cmbEmpmCellid").val();
 fillCellHierarchy("cellHierarchy.commonFilter",employee,"cmbEmpmSectionid","cmbEmpmFactoryid");
 
 function getEmpImgErr(result)
 {
	 
 }
 function frmEmployee_errorCallback(result)
	 {
		//alert("error !");
	 }
 function frmEmployee_beforeDelete(){
		
		if(! confirm("Do you want to delete this record?")){
			return false;
		}
	}
	function frmEmployee_deleteSuccessCallback(result)
	{
		
		alert(result.successData.msg);
		 jQuery("#cmbEmpmKeyid").combobox('clear');
		 reloadCombo("frmEmployee","cmbEmpmKeyid","Combo_Employee.emp");
		 jQuery('#imgEmployee').attr('src', "images/EmpDefaultImg.jpg");	  
	}
 function frmEmployee_successsCallback(result)
 {
	 jQuery("#cmbEmpmKeyid").combobox('clear');
	 reloadCombo("frmEmployee","cmbEmpmKeyid","Combo_Employee.emp");
	 var mode = result.formMode;
	 if( mode != null && mode == "emp")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;		
		 navigateToNextForm("emp_input.emp",result.formHeader,forwardData,persistentData );
	 }	 

	 jQuery('#imgEmployee').attr('src', "images/EmpDefaultImg.jpg");	   		
	//jQuery('#'+controlId).attr("src",jQuery('#'+controlId).attr('src').replace('../',''));
		
 }
 
 function frmEmployee_errorCallback(result)
 {
	 
 }
 
jQuery("#txtEmpdOtherexperience").change(function (){
	totalExperience();
});

jQuery("#txtEmpdTotalexperience").click(function(){
	totalExperience();
	 });


function check_email(Obj){

    	var numericExpression = /^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$/;

    	if(Obj.value.match(numericExpression)){ 
    		return true;
    	}else{
        	alert("Enter valid E-Mail.");        	
        	txtEmpmEmail.value="";                      
    		return false;
    	}    	

    }

 function check_birthDate(Obj)
 {
  //alert(Obj.value);
 }

 function  frmEmployeecmbEmpmFactoryid_onSelect(record)
    	{
	   		jQuery("#cmbEmpmSectionid").combobox('clear');
    		jQuery("#cmbEmpmCellid").combobox('clear');    		
    		reloadCombo("frmEmployee","cmbEmpmSectionid","sectionCombo.commonFilter?factId="+record.id);
    		reloadCombo("frmEmployee","cmbEmpmCellid","cellCombo.commonFilter?factId="+record.id  );    		
    	}

    
function  frmEmployeecmbEmpmSectionid_onSelect(record)
{    	
    fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbEmpmFactoryid");
}


  function  frmEmployeecmbEmpmCellid_onSelect(record){
    	//getEmployee(record.id);cellHierarchy.commonFilter    	
    	//fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbEmpmSectionid","cmbEmpmFactoryid");
	 }
	 //-------------------------cell Manitory/not-------------------//
	 
	 /*jQuery('#echkbox').click(function() {
	 var sat = jQuery('#echkbox:checked').val();
	 if(jQuery('#schkbox').is(':checked') == true)	
		{
			jQuery("#txtEmpmIscellmanager").val("Y");
			jQuery("#txtEmpmIssectionmanager").val("N");
			
		}
	 
	 if(sat=="on"){
		// jQuery("#chk").show();
		// jQuery("#unchk").hide();
		 jQuery("#unchk").show();
		 jQuery("#cmbEmpmCellid").attr("required",true);
		 if(jQuery('#echkbox:checked').val() == "on")	
		 	jQuery("#schkbox").removeAttr("checked");	 
		 }
	 else
	 {
		jQuery("#unchk").show();	
	 	jQuery("#chk").hide(); 
	 }
	
	});

	 jQuery('#schkbox').click(function() {
		 var sat = jQuery('#schkbox:checked').val();
		 jQuery("#echkbox").removeAttr("checked");	
		 jQuery("#unchk").show();	
		 jQuery("#chk").hide(); 
		 
		 if(jQuery('#schkbox').is(':checked') == true)	
			{
				jQuery("#txtEmpmIscellmanager").val("N");
				jQuery("#txtEmpmIssectionmanager").val("Y");
				
			}
		 });*/
	  function frmEmployee_beforeSubmit()
	  {			
			 
		  var sat = jQuery('#echkbox:checked').val();		
		  var val =jQuery("#cmbEmpmCellid").combobox("getValue");		
		  if(sat=="on" && val==""){			
				showValidationErrorMsg('cmbEmpmCellid','Select Line');
				return false;			
			  }		
		  return "hdnEmpImgUrl="+jQuery("#hdnEmpImgUrl").val();		  
		}



	 
	function frmEmployeecmbEmpmSectionid_onLoadSuccess()
	{
		//fillComboBox("frmEmployee","cmbEmpmSectionid","sectionCombo.commonFilter" );
	} 
	
	function frmEmployeecmbEmpmFactoryid_onLoadSuccess()
	{		
		//fillComboBox("frmEmployee","cmbEmpmCellid","cellCombo.commonFilter" );
	}
	function frmEmployeecmbEmpmKeyid_onLoadSuccess (){}
	function frmEmployeecmbEmpmDepartmentid_onLoadSuccess(){}
	function frmEmployeecmbEmpmDesignationid_onLoadSuccess(){}
	function frmEmployeecmbEmpmCellid_onLoadSuccess(){}
	function frmEmployeecmbEmpmGradeid_onLoadSuccess(){}
	function frmEmployeecmbEmpmTradeid_onLoadSuccess(){}
	function frmEmployeecmbEmpdCityid_onLoadSuccess(){}
	function frmEmployeecmbEmpdStateid_onLoadSuccess(){}
	function frmEmployeecmbEmpdCountryid_onLoadSuccess(){}
	
	function imgEmpAddOnComplete(response)
	{		
		jQuery("#hdnEmpImgUrl").val(response);
		var dlgimage =  jQuery("#btnimgEmpAdd");
		 imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	
		
	}

function frmEmployeecmbEmpdCityid_onSelect(record)
{
	fillCityHierarchy("cityHierarchy_select.commonFilter",record.id,"cmbEmpdStateid","cmbEmpdCountryid");
	
	}
function frmEmployeecmbEmpmSectionid_onSelect(record)
{
	 reloadCombo("frmEmployee","cmbEmpmCellid","cellCombo.commonFilter?sectId="+record.id);
}

//Recall Cell Label with Caption
/*function checkBoxSel()
{
	//alert(jQuery('#txtEmpmIscellmanager').val());
	if(jQuery('#txtEmpmIscellmanager').val() == 'Y')
	{
		jQuery('#echkbox').attr('checked',true);
		jQuery('#schkbox').attr('checked',false);
		//jQuery("#chk").show();
		//jQuery("#unchk").hide();
		jQuery("#unchk").show();
	}
	else if(jQuery('#txtEmpmIscellmanager').val() == 'N')
	{
		jQuery('#schkbox').attr('checked',true);
		jQuery('#echkbox').attr('checked',false);
		jQuery("#chk").hide();
		jQuery("#unchk").show();
	}
	else
	{
		jQuery('#schkbox').attr('checked',false);
		jQuery('#echkbox').attr('checked',false);
	}
		
}
*/
function getEmpImgSuccess(result)
{ 

	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
		
	jQuery('#imgEmployee').attr('src','');
	jQuery('#imgEmployee').attr('src',result.empImg.imgToimBlobimage);	
	}
	else{
		
	      //alert('no image');
			}
		//setImgWidth( jQuery('#nodeImage'),580,432);
}

  var emploid = getFieldValue("cmbEmpmKeyid", "frmEmployee");
  

jQuery( "#btnDept" ).click(function() {	
	LoadPopUp("divShowTrAreaPopup", "loadmst_grid.gnms?q=2&menuCaption=Department&menuName=MNUMASDEPARTMENT&isMMC=Y&loadFormArg=MNUMASDEPARTMENT" , true,"91%","87%","3%","2%", "mstFrm","Department",false,true);
	});	

jQuery( "#btnDesg" ).click(function() {	
	LoadPopUp("divShowTrAreaPopup", "loadmst_grid.gnms?q=2&menuCaption=Designation&menuName=MNUMASDESIGNATION&isMMC=Y&loadFormArg=MNUMASDESIGNATION" , true,"91%","87%","3%","2%", "mstFrm","Designation",false,true);
	});

jQuery( "#btnFact" ).click(function() {	

	   var empid=jQuery('#cmbEmpmKeyid').val();
        if(empid.length !=0){
		loadfunLoc("FACT");	
		return true;
        }else
            {
            alert("Employee does not save. save Employee");
            return false;
            }
		
	});	

jQuery( "#btnSect" ).click(function() {	
	    
		 var empid=jQuery('#cmbEmpmKeyid').val();
	        if(empid.length !=0){
	        loadfunLoc("SECT");		
			return true;
	        }else
	            {
	            alert("Employee does not save. save Employee");
	            return false;
	            }
});


jQuery( "#btnLine" ).click(function() {	
	
		    var empid=jQuery('#cmbEmpmKeyid').val();
	        if(empid.length !=0){
	        loadfunLoc("CELL");
			return true;
	        }else
	            {
	            alert("Employee does not save. save Employee");
	            return false;
	            }


	});

 jQuery("#btnEqlnk").click(function(){

	 var empid=jQuery('#cmbEmpmKeyid').val();
     if(empid.length !=0){
     loadfunLoc("MCHM");
		return true;
     }else
         {
         alert("Employee does not save. save Employee");
         return false;
         }


	 });

function loadfunLoc(funcLocType){
        
        
        var Title=null;
    
    	if(funcLocType=="FACT"){
    		
        Title="Factory";
    	} 
		else if(funcLocType=="SECT"){
			//alert(funcLocType);
	    Title="Section";
		}
		else if(funcLocType=="CELL"){
			//alert(funcLocType);
    	Title="CELL";
		}
		else if(funcLocType=="MCHM"){
			Title="Equipment";
	   }
    	//alert(Title);
	LoadPopUp("divShowTrAreaPopup","fac_input.emp?filterString="+emploid+"&funcLocType="+funcLocType, true,"32%","62%","20%","34%", "mstFrm",Title);
}
 </script>
<form name="frmEmployee" id="frmEmployee" action="" method="post">
<div id="wrapper" style="width:100%">
<input type="easyui-text" id="hdnEmpImgUrl" name="hdnEmpImgUrl" style="display:none;" value=""/>

	<table  width=10%% style="width:60%;">
		<tr>
			<td valign="top" style="width : 100%;padding-left:0px;">
				<div  style="float:left;width:100%;margin-left:30px;" >
				
				    <div><label >Employee</label></div> 
                  	<div class="easyui-paddingbfpx" > 
                		<input id="cmbEmpmKeyid" name="cmbEmpmKeyid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmKeyid}"  >
					</div>
					
				    <div style="display:none;" ><label>Factory</label></div> 
                  	<div class="easyui-paddingbfpx" style="display:none;"> 
                  		<input id="cmbEmpmFactoryid" name="cmbEmpmFactoryid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmFactoryid}"  >
                   	    
                   	     <input type="button" value ="..." id="btnFact" name="btnFact" class="easyui-button" style="height:21px;"/>
                    
                         <span id="err_cmbEmpmFactoryid" class="tpm-errormsg"></span>
                   	</div>
                   	
                   	<div class="easyui-paddingbfpx" style="display:none;" > 
						<input type="checkbox" id="chkEmpmActive" name="chkEmpmActive">
						<label>Inactive Employee</label>
			        </div>
			        <div class="mndlbl" style=" width : 256px;"><label id="lblEmpmCode" class="mandatory-lbl">Code/No</label></div> 
              	  	<div class="easyui-paddingbfpx" style=" width : 258px;"> 
             		 <input  class="easyui-text" maxlength="12" id="txtEmpmCode" name="txtEmpmCode"  style="width:255px;height : 21px;" value="${requestScope.genTlEmployeemst.empmCode}"  >
				  	</div>
                  </div> 
                  
            </td>
            <td valign="top" style="padding-left:0px; width : 80%;">       
                   <div style="position: absolute; left: 360px; width : 250px;padding-left:20px">
	                   	<div class="mndlbl" style=" width : 257px;" ><label class="mandatory-lbl">Name</label></div> 
	                  	<div class="easyui-paddingbfpx" style=" width :255px;"> 
	                	<input class="easyui-text"  id="txtEmpmName" name="txtEmpmName"  style="width:255px; height : 21px;" value="${requestScope.genTlEmployeemst.empmName}"  >
						</div>
						
						<div style=padding-top:10px;>
						 <input type="button" value ="Equipment Link" id="btnEqlnk" name="btnEqlnk" class="easyui-button" style="height:24px;width:100px;"/>
						</div>
				 </div>
             </td>
             <td >     
                 <div style="float: left;  position: absolute; top: 10px; left: 660px;">
					<img id="imgEmployee" name="imgEmployee" src="images/EmpDefaultImg.jpg" width="100px" height="100px"/>
<!--					<span><img style="cursor: pointer;" id="imgEmpAdd" alt="" src="images/menu-icon/imgpluse.png" width="20px" height="20px"></span>-->
<!--					<span><img style="cursor: pointer;" id="imgEmpDel" alt="" src="images/menu-icon/imgMinus.png" width="20px" height="20px"></span>-->
					<span style="position:absolute;bottom:-20px;left:0px;top:100px;"><input type="button" class="easyui-button" id="btnimgEmpAdd" name="imgEmpAdd" value="+" style="width:25px;"/></span>
					<span style="position:absolute;bottom:-20px;left:76px;top:100px;"><input type="button" class="easyui-button" id="btnimgEmpDel" name="imgEmpDel" value="-" style="width:25px;"/></span>
				</div>
				
			</td>
		</tr>
	</table>

		<div style="height:300px;margin-top:10px;;margin-bottom:2%;padding-left:20px;">	
		<div  id="tabEmp" class="easyui-tabs"  style="width:999px;height:430px;border-bottom:solid 1px#8DB2E3;">
			
			<div title="Details" style="padding:10px;">		
				
		    	<div style="float:left;padding-left:2%%;display:none;">
				
					<div class="mndlbl"><label id="lblEmpmEmployeenumber" class="mandatory-lbl">Emp No</label></div> 
				    <div class="easyui-paddingbfpx"> 
				    	<input  class="easyui-text" id="txtEmpmEmployeenumber" name="txtEmpmEmployeenumber" maxlength="15"   style="width:255px;height : 21px;" value="${requestScope.genTlEmployeemst.empmEmployeenumber}"  >
			        </div>				
					
					<div class="easyui-paddingbfpx"><label>Employee Role</label></div> 
					<div class="easyui-paddingbfpx"> 
		                <input id="cmbEmpmRoleid" name="cmbEmpmRoleid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmRoleid}"  >
					</div>
					
					<div><label>Section</label></div> 
		            <div class="easyui-paddingbfpx"> 
		                <input id="cmbEmpmSectionid" name="cmbEmpmSectionid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmSectionid}"  >
					    <input type="button" value ="..." id="btnSect" name="btnSect" class="easyui-button" style="height:21px;"/>
					    <span id="err_cmbEmpmSectionid" class="tpm-errormsg"></span>
					</div>					
	<!--			<div class="easyui-paddingbfpx" > -->
	<!--				<select id="cboEmpmActive" name="cboEmpmActive" onchange="EmpmActive_onchange();" style="width:255px;"  >-->
	<!--						<option value="Y">ACTIVE</option>-->
	<!--						<option value="N">INACTIVE</option>						-->
	<!--				</select>-->
	<!--	        </div>-->
				</div>
				
				<div style="float:left;  padding-left:25px; margin-top: 15px;display:none;" >
					<div  class="easyui-paddingbfpx">
		<!--			<input id="chkEmpmIscellmanager" name="chkEmpmCellManager" type="checkbox" value="Y" <c:out value = "${ requestScope.genTlEmployeemst.empmIscellmanager == 'Y' ? ' checked':''}"/>/> -->
						<input type="checkbox" id="chkEmpmIscellmanager" name="chkEmpmIscellmanager" value="Y" <c:out value = "${ requestScope.genTlEmployeemst.empmIscellmanager == 'Y' ? ' checked':''}"/>/><label> Line Member</label>
		<!--         		<input id="echkbox" name="chkEmpmCellManager" type="checkbox"/> <label>Cell Member</label>-->
		            	<span  style="margin-left: 2px;">  
		            	<input type="checkbox" id="chkEmpmIssectionmanager" name="chkEmpmIssectionmanager" value="Y" <c:out value = "${ requestScope.genTlEmployeemst.empmIssectionmanager == 'Y' ? ' checked':''}"/>/><label> Section Member</label></span>
		           		<input type="checkbox" id="chkEmpmIsoperator" name="chkEmpmIsoperator" value="Y" <c:out value = "${ requestScope.genTlEmployeemst.empmIsoperator == 'Y' ? ' checked':''}"/>/><label> Operator</label> 	
		<!--            	<input id="schkbox"  name="chkEmpmSectionManager" type="checkbox"/> <label>Section Manager</label></span>-->
		<!--            	<input id="txtEmpmIscellmanager" type="text" name="txtEmpmIscellmanager" value="${requestScope.genTlEmployeemst.empmIscellmanager}" class="easyui-text" style="display:none;"/>-->
		<!--            	<input id="txtEmpmIssectionmanager" type="text" name="txtEmpmIssectionmanager" value="${requestScope.genTlEmployeemst.empmIssectionmanager}" class="easyui-text" style="display:none;"/>-->
	            	</div> 
					<div  id="chk" class="mndlbl" ><label class="mandatory-lbl">Line</label></div> 
						<div id="unchk" class="mndlbl" ><label >Line</label></div> 
			            <div class="easyui-paddingbfpx"> 
			                <input id="cmbEmpmCellid" name="cmbEmpmCellid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmCellid}"  >
			                <input type="button" value ="..." id="btnLine" name="btnSect" class="easyui-button" style="height:21px;"/>
						    <span id="err_cmbEmpmCellid" class="tpm-errormsg"></span>		               
	                </div>
					<div  ><label>Grade</label></div> 
		            <div class="easyui-paddingbfpx" > 
		                <input id="cmbEmpmGradeid" name="cmbEmpmGradeid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmGradeid}"  >
					</div>
					<div class="mndlbl" style="padding-top: 5px;"><label>Maint. Section</label></div> 
		            <div class="easyui-paddingbfpx"> 
		                <input id="cmbEmpmTradeid" name="cmbEmpmTradeid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmTradeid}"  >
					</div>
					<div  style="padding-top: 8px;"><label>Skill Category</label></div> 
	               	<div class="easyui-paddingbfpx"> 
	               		<input  class="easyui-text" id="txtEmpmSkillcategory" name="txtEmpmSkillcategory"  maxlength="100" style="width:255px;height : 21px;" value="${requestScope.genTlEmployeemst.empmSkillcategory}"  >
					</div>
				</div>
				<div style="padding-bottom:2px;width:93%;">
		     		<table id="roleempgrid"><tr><td/></tr></table>
				 	<div id="roleemppager"></div>
			    </div>		
			</div>
			<div title="Additional Information" style="padding:10px;">
				<div class="sub-header" style="width:93%;">Information</div>
				
					<div style="float:left;padding-left:0px;">	
					
							 <div class="mndlbl"><label class="mandatory-lbl">Company</label></div> 
						<div class="easyui-paddingbfpx" > 
							 <input id="cmbEmpmCompany" name="cmbEmpmCompany" class="easyui-combobox"   style="width:255px;" value="${requestScope.genTlEmployeemst.empmCompany}"  >
				        </div>
				        
				        
				         <div class="mndlbl"><label class="mandatory-lbl">Location</label></div> 
						<div class="easyui-paddingbfpx" > 
							 <input id="cmbEmpmLocation" name="cmbEmpmLocation" class="easyui-combobox"   style="width:255px;" value="${requestScope.genTlEmployeemst.empmLocation}"  >
				        </div>
				        			
						 <div class="mndlbl"><label class="mandatory-lbl">Category</label></div>
						  
						<div class="easyui-paddingbfpx" > 
					<!-- sriram 25-oct-2025 only added value are in requestScope-->	
						 <input id="cmbEmpmEmployeetype" name="cmbEmpmEmployeetype" class="easyui-combobox"   style="width:255px;" value="${requestScope.genTlEmployeemst.empmEmployeetype}"  >
					
<!-- 					  <select id="cmbEmpmEmployeetype" name="cmbEmpmEmployeetype" onchange="EmpmEmployeetype_onchange();" style="width:255px;"  >
 -->							
							<!-- 
									<option value="R"> EMPLOYEE</option>
									<option value="A"> ASSOCIATE</option>
									<option value="C"> CONTRACT</option>
									<option value="M"> MANAGER</option>
									<option value="B"> BADLI</option>
									<option value="T"> TRAINEE</option>
									<option value="E"> EXECUTIVE</option>
									<option value="O"> OTHERS</option>
							</select> -->
							
							
				        </div>
				        
				        <div class="mndlbl" ><label class="lbl">Department</label></div> 
			            <div class="easyui-paddingbfpx"> 
					        <input id="cmbEmpmDepartmentid" name="cmbEmpmDepartmentid" class="easyui-combobox"   style="width:255px;" value="${requestScope.genTlEmployeemst.empmDepartmentid}"  >
							<input type="button" value ="..." id="btnDept" name="btnDept" class="easyui-button" style="height:21px"/>
							<span id="err_cmbEmpmDepartmentid" class="tpm-errormsg"></span>
						</div>
					
						<div class="mndlbl" ><label class="lbl">Designation</label></div> 
			            <div class="easyui-paddingbfpx"> 
					        <input id="cmbEmpmDesignationid" name="cmbEmpmDesignationid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.empmDesignationid}"  >
							<input class="easyui-button" type="button" value ="..." id="btnDesg" name="btnDesg" style="height:21px" />
							<span id="err_cmbEmpmDesignationid" class="tpm-errormsg"></span>
						</div>
						
				        <div class="mndlbl"  ><label class="mandatory-lbl">Join Date</label></div> 
			            <div class="easyui-paddingbfpx"> 
			                <input id="dteEmpmJoineddate" name="dteEmpmJoineddate" class="easyui-datebox" clear="false"  style="width:160px;" value="${requestScope.genTlEmployeemst.empmJoineddate}" /> 
							<span id="err_dteEmpmJoineddate" class="tpm-errormsg"></span>
						</div>
						<div ><label>Exp (Yrs)</label><span class="lblr1" style="padding-left:0px;">Other Experience(Yrs)</span></div> 
					 	<div class="easyui-paddingbfpx"> 
		           			 <input  class="easyui-text" type="text" id="txtEmpdCurrentexperience"  name="txtEmpdCurrentexperience" style="width:85px; height : 21px; text-align:right;" value="${requestScope.genTlEmployeeDtl.empdCurrentexperience}" onkeypress="return checkIt(event,this,5)"> 
		                 	 <span class="floatR2"> 
		                 	<input  class="easyui-text" type="text" id="txtEmpdOtherexperience" name="txtEmpdOtherexperience" style="width:170px; height : 21px;text-align:right;" value="${requestScope.genTlEmployeeDtl.empdOtherexperience}">
	                  		</span> 
	                    </div>								
						<div ><label>Total Experience</label></div> 
	                 	<div class="easyui-paddingbfpx"> 
	                		<input class="easyui-text" type="text" id="txtEmpdTotalexperience" name="txtEmpdTotalexperience" style="width:180px; height : 21px;text-align:right;" readonly="readonly" value="${requestScope.genTlEmployeeDtl.empdTotalexperience}" >
						</div>
				    </div>
			    
					<div style="float:left;padding-left:10px;">							
							<div ><label>Phone No(s)</label></div> 
		                  	<div class="easyui-paddingbfpx"> 
		                <!--  		<input  class="easyui-text" id="txtEmpmExtensionphone" name="txtEmpmExtensionphone" onchange="return phoneNumberValidate(this);" maxlength="20" style="width:248px;height:21px;" value="${requestScope.genTlEmployeemst.empmExtensionphone}"  >  -->
		                <input class="easyui-text" id="txtEmpmExtensionphone" name="txtEmpmExtensionphone"  maxlength="20" style="width:256px;" value="${requestScope.genTlEmployeemst.empmExtensionphone}"  >
								</div>							
							
							<%-- <div><label>Fax No(s)</label></div> 
		                  	<div class="easyui-paddingbfpx"> 
		                		<input class="easyui-text" id="txtEmpmFax" name="txtEmpmFax" maxlength="30" style=" width : 248px; height : 21px;" value="${requestScope.genTlEmployeemst.empmFax}"/> 
							</div> --%>
							
						
							<div ><label>Mobile No(s)</label></div> 
		                 	<div class="easyui-paddingbfpx"> 
		                			 <input class="easyui-text" id="txtEmpmMobile" name="txtEmpmMobile" maxlength="30" style=" width : 248px; height : 21px;" value="${requestScope.genTlEmployeemst.empmMobile}"
		                			 onchange="return check_mobileNo(this);"/>
						    </div>
						    <div ><label>Email ID</label></div> 
		                 	<div class="easyui-paddingbfpx"> 
		                			 <input class="easyui-text" id="txtEmpmEmail" name="txtEmpmEmail" maxlength="60"  style=" width : 248px; height : 21px;"  value="${requestScope.genTlEmployeemst.empmEmail}"
		                			 onchange="return check_email(this);"/><span id="err_txtEmpdTotalexperience" class="tpm-errormsg"></span>
						    </div>
						    <div ><label>Remarks</label></div> 
		                  	<div class="easyui-paddingbfpx"> 
		                			 <textarea rows="3" cols="28" maxlength="175" id="txtEmpmRemarks" name="txtEmpmRemarks">${requestScope.genTlEmployeemst.empmRemarks}</textarea>
							</div>
							 <div ><label>Personal Information</label></div> 
		                  	<div class="easyui-paddingbfpx"> 
		                			 <textarea rows="3" cols="28" maxlength="175" id="txtEmpmPersonalinfo" name="txtEmpmPersonalinfo" >${requestScope.genTlEmployeemst.empmPersonalinfo}</textarea>
							</div>
							
							 <div class="mndlbl"><label class="mandatory-lbl">Gender</label></div> 
						<div class="easyui-paddingbfpx" > 
						<select id="cboEmpmGender" name="cboEmpmGender" style="width:255px;" >
									<option value="M" ${requestScope.genTlEmployeemst.empmGender eq "M" ? "Selected" : "" }>MALE</option>
									<option value="F" ${requestScope.genTlEmployeemst.empmGender eq "F" ? "Selected" : "" }>FEMALE</option>
							</select>
				        </div>
				 	</div>
				 	
				
					<div style="float:left;padding-left:10px;">
						<div  ><label>Birth Date</label></div> 
							<div> 
							  <input id="dteEmpdBirthdate" name="dteEmpdBirthdate" class="easyui-datebox" cleat="false"  onchange="return check_birthDate(this);" style="width:260px;" value="${requestScope.genTlEmployeeDtl.empdBirthdate}"/> 
							</div>
						<div ><label>Address</label></div> 
						<div> 
							<textarea rows="1" cols="29" style="width:256px;"  style=""  maxlength="175" id="txtEmpdAddress"name="txtEmpdAddress" > ${requestScope.genTlEmployeeDtl.empdAddress}</textarea>
						</div>
						<div ><label>City</label></div> 
						<div > 
							<input id="cmbEmpdCityid" name="cmbEmpdCityid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeeDtl.empdCityid}"  >	
						</div>
						<div><label>State</label></div> 
						<div> 
							<input id="cmbEmpdStateid" name="cmbEmpdStateid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeeDtl.empdStateid}" >	
						</div>
						<div  ><label>Country</label></div> 
						<div> 
							<input id="cmbEmpdCountryid" name="cmbEmpdCountryid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeeDtl.empdCountryid}"  >
						</div>						
						<div><label>Phone No(Res)</label></div> 
						<div> 
							<input class="easyui-text" id="txtEmpdPhone" name="txtEmpdPhone"  maxlength="30" style="width:256px;" value="${requestScope.genTlEmployeeDtl.empdPhone}"  >
						</div>
						
				  		<div><label>Remarks</label></div> 
						<div> 
						<textarea rows="3" cols="28" style="width:256px;" style="" maxlength="50" id="txtEmpdRemarks"  name="txtEmpdRemarks">${requestScope.genTlEmployeeDtl.empdRemarks}</textarea>
						</div>
						 
					</div>
				
			</div>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" />
	<input type="hidden" id="test" name="test" value="${requestScope.genTlEmployeemst.empmIssectionmanager}" />
	<input type="hidden" id="hdnCatg" name="hdnCatg" value="${requestScope.genTlEmployeemst.empmEmployeetype}" />
	<input type="hidden" id="hdnactive" name="hdnactive" value="${requestScope.genTlEmployeemst.empmActive}" />
	<!--  <input type="hidden" id="hdnEmpmLocation" name="hdnEmpmLocation" value="${requestScope.genTlEmployeemst.empmLocation}" /> -->
	<input type="hidden" id="hdnEmpmSbuId" name="hdnEmpmSbuId" value="${requestScope.genTlEmployeemst.empmSbuId}" />
	<input type="hidden" id="hdnDateTime" name="hdnactive" value="" />
	
	<input type="hidden" id="hdnCellMgr" name="hdnactive" value="" />
	<input type="hidden" id="hdnSectMgr" name="hdnactive" value="" />
	<input type="hidden" id="hdnOPtrMgr" name="hdnactive" value="" />
</div>	
</form>