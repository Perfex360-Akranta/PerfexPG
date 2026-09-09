
<script type="text/javascript">	
//window.onload = function () {window.location.reload()}

	jQuery(document).ready(function(){
		
		//alert("GENTLSBUMSTADD");
		initialiseForm('frmSbu');
		//jQuery("#savingdata").css('display','none');
		jQuery('#submitForm').val('frmSbu');
		var formType = jQuery('#hdnFormType').val();
		if(formType.length>2 && formType!='ADD')
			jQuery('#submitForm').val('frmSbu');
		
		
		//added only for auditreport jsp
		

		//alert(" SbuKeyid :: "+jQuery('#txtSbutKeyid').val());
		
		var keyid=jQuery("#txtSbutKeyid").val();
		//alert("The keyid:::"+keyid);
		var sbuaddupdate=jQuery("#txtsbuadd").val();
	//	alert("sbuaddupdate::::::"+sbuaddupdate);
        
		
		//alert("keyid"+keyid);
	 	//jQuery('#err_SbufunLocation').css('padding-left','218px');
	 	fillComboBox("frmSbu","cmbSbutKeyid","sbuCombo.commonFilter" );
		fillComboBox("frmSbu","cmbSbutCompanyid","companyCombo.commonFilter" );
		fillComboBox("frmSbu","cmbSbutLocationid","location.commonFilter" );

		var factId = jQuery("#frmSbu input[id='factory']").val();
	    var sectionId = jQuery("#frmSbu input[id='section']").val();
	    var cellId = jQuery("#frmSbu input[id='cell']").val();
	    var machId = jQuery("#frmSbu input[id='machine']").val();
        var compId = jQuery("#frmSbu input[id='company']").val(); 
        var locId = jQuery("#frmSbu input[id='location']").val();
	    var flid = jQuery("#frmSbu input[id='flid']").val(); 
	    var sbu = jQuery("#frmSbu input[id='sbu']").val(); 

	
	  

	    jQuery('#frmSbu .easyui-combobox').css('text-transform', 'uppercase');
			jQuery('#frmSbu .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmSbu textarea').css('text-transform', 'uppercase');

        var dataStr = "&factId=" + factId
						+ "&sectionId=" + sectionId
						+ "&cellId=" + cellId + "&machId="+ machId
						+ "&compId=" + compId
						+ "&locId=" + locId
						+"&flid="+ flid
		                +"&sbu"+sbu;
        //alert("flid"+flid);
        
        if(sbuaddupdate=="add")
        {
		  loadFunctionalLocation("SbufunLocation", "functionalLocsbu.emr", "frmSbufunLocation", "frmSbu",dataStr);
        }
		else{
			loadFunctionalLocation("SbufunLocation", "functionalLocsbu.emr", "frmSbufunLocation", "frmSbu",dataStr);
			

			}
			});
	jQuery("#findNode").combobox({
		onSelect:function(recordid){

			 combovalue=jQuery('#findNode').combobox("getValue");
			//alert("combovalue::::::"+combovalue);
           
			if (jQuery('#findNode').combobox("getValue") == "sbu") {
			  // alert("INSIDE THE IF");
		      // sbu();
			navigateToNextForm("SBU_input.commonFilter");
			   //jQuery('#findNode').load('/pages/GenTlSbumst.jsp').fadeIn("slow");
			 // processAjaxCalls("SBU_input.commonFilter" ,"", "","");
			  //processAjaxCalls('SBU_input.commonFilter','','','','');
			   // alert("Not a valid character")
			}
			else if (jQuery('#findNode').combobox("getValue") == "subunt") {
				  // alert("INSIDE THE IF");
				  navigateToNextForm("PBU_input.commonFilter");
				   // alert("Not a valid character")
				}
			else if (jQuery('#findNode').combobox("getValue") == "sect") {
				  // alert("INSIDE THE IF");
				   navigateToNextForm("section_input.sect");
				   // alert("Not a valid character")
				}
			else if (jQuery('#findNode').combobox("getValue") == "cell") {
				  // alert("INSIDE THE IF");
				   navigateToNextForm("cell_input.cell");
				   // alert("Not a valid character")
				}
			
			//navigate(combovalue);
			//jQuery("#cmbfindTableName").combobox('clear');
			
			//reloadCombo("frmfindNode","",getComboUrl(recordid.text));
		
		}
	});


	function frmSbu_FuntLocHierarchy_SuccessCallBack(result)
	{   
	
		var SBU = result.sbuId;
	
		processAjaxCalls("Sbu_recall.funlocn","keyId="+result.sbuId, "frmSbu_recallsuccessCallback","frmSbu_recallerrorCallback");
        
    }
	
   /*function  frmSbucmbSbutKeyid_onSelect(record)
   {
	  		//alert(" Inside :: Sbu :: "+record.id);
			processAjaxCalls("Sbu_recall.funlocn","keyId="+record.id, "","frmSbu_recallerrorCallback");
   }*/
   function frmSbu_successsCallback(result)
   {
	   jQuery("#savingdata").css('display','none');
	   jQuery('#mstfrm_div').hide();
   // loadFunctionalLocation("SbufunLocation", "functionalLocsbuadd.commonFilter?&SBUMASTER=SBUMASTER&frmType=SBU", "frmSbufunLocation", "frmSbu"," ");
   
   //refreshForm();
   	//navigateToPrevForm();
   loadFunctionalLocation("SbufunLocation", "functionalLocsbu.commonFilter?&SBUMASTER=SBUMASTER&frmType=SBU", "frmSbufunLocation", "frmSbu"," ");
   refreshForm();
   	//navigateToPrevForm();
     // navigateToPrevForm("load_view.funlocn");
	  // 	refreshTree();
   	  
   }
   function refreshTree()
   {
   	var tree = jQuery.jstree._reference("#flTreeComponent");
   	var currentNode = tree._get_node(null, false);
   	var parentNode = tree._get_parent(currentNode);
   	tree.refresh(parentNode);
   }
   function frmSbu_deleteSuccessCallback(result)
   {
   	
   	alert(result.successData.msg);
   	refreshForm();
   	navigateToPrevForm();
   	loadFunctionalLocation("SbufunLocation", "functionalLocsbu.commonFilter?&SBUMASTER=SBUMASTER&frmType=SBU", "SbufunLocation", "frmSbu"," ");
   	clearForm("frmSbu");
   	
   	  
   }
   function frmSbu_recallsuccessCallback(record)
   {
	   jQuery("#txtSbutKeyid").val(record.sbumst.SbutKeyid);
	   //jQuery("#cmbSbutFlid").val(record.sbumst.SbutFlid);

	  /* jQuery("#cmbSbutFlid").val(result.shift.SftmFlid);
  	    var dataStr = "&flid="+result.shift.SftmFlid;
  	  loadFunctionalLocation("SbufunLocation", "functionalLocsbuadd.commonFilter?&SBUMASTER=SBUMASTER&frmType=SBU", "SbufunLocation", "frmSbu"," ");*/
	   jQuery("#cmbSbutLocationid").val(record.sbumst.SbutLocationid);
	   jQuery("#txtSbutName").val(record.sbumst.SbutName);
  	   jQuery("#txtSbutCode").val(record.sbumst.SbutCode);
  	  // jQuery("#txtSbutDescription").val(record.sbumst.SbutDescription);
  		
   }

   function frmSbu_recallerrorCallback(record)
   {

   }
	function frmSbu_exceptionCallback(msg) {
			//alert("msg in sucess:::"+msg);	
			jQuery("#savingdata").css('display','none');
			jQuery('#mstfrm_div').hide();
		}
   function  frmSbu_beforeDelete()
   {

   		 var r=confirm("Are You Sure to Delete?");
   		if(r)
   			return true;
   		else
   			return false;
   	}


   function frmSbu_beforeSubmit(){
	   
	   //alert("INSIDE THE SUBMIT");
	   jQuery('#mstfrm_div').addClass('popup-mask');
  	  			jQuery('#mstfrm_div').show();
	   jQuery("#savingdata").css('display','block');
	   var sbuaddupdate=jQuery("#txtsbuadd").val();
	   var sbu = jQuery("#frmSbu input[id='sbu']").val();
	  // alert(sbu);
	   var locId = jQuery("#frmSbu input[id='location']").val();  
	   if(sbuaddupdate=="add"){
		   if(locId == '' || locId == ' ' || locId == undefined)
			 {
	   		 alert("Select Location");
	   		 return false;
			 }
         
		   }
	  /*  else{
	
    	if(sbu == '' || sbu == ' ' || sbu == undefined)
		 {
   		 alert("Select SBU");
   		 return false;
		 }
	   } */
	
}
   
</script>


<form name="frmSbu" id="frmSbu" >
   
   <div id="sbuinsbu" class="sbuinsbu">
   
   

   

	 <div id="sbuform" class="sbuform">
	 <div class="easyui-paddingbfpx" style="height: 414px;padding-top: 10px;padding-left:10px"> 
<div id="frmSbuFuntKeyIds">
					<input type="hidden" id="factory" name="cmbSbutFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbSbutSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbSbutCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbSbutMachineid" value=""></input>
					<input type="hidden" id="company" name="cmbSbutCompanyid" value=""></input>
					<input type="hidden" id="location" name="cmbSbutLocationid" value="${requestScope.genTlSbumst.sbutLocationid}"></input>
					<input type="hidden" id="flid" name="cmbSbutFlid" value="${requestScope.genTlSbumst.sbutFlid}"></input>        
		</div>

     <div  class="easyui-paddingbfpx" id="SbufunLocation" style="width: 70%;margin-left:2%;margin-top:40px;"></div>
     <div  class="tpm-errormsg" id="err_SbufunLocation" style="padding-left:218px;text-color:red;"></div>
 
<div style="margin-left:2%;margin-top:0px;">
 <div  class="easyui-paddingbfpx" style="display:none;">
   <label>SBU</label> </div>         
 <div style="display:none;" >
 <input id="cmbSbutKeyid" name="cmbSbutKeyid" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;display:none;">
 <label class="mandatory-lbl">Company</label>  
 </div>                  
 <div style="display:none;">
 <input id="cmbSbutCompanyid" name="cmbSbutCompanyid" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="${requestScope.genTlSbumst.sbutCompanyid}">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;display:none;">
 <label class="mandatory-lbl">Location</label>  
 </div>                  
 <div style="display:none;">
 <input id="cmbSbutLocationid" name="cmbSbutLocationid" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="${requestScope.genTlSbumst.sbutLocationid}">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
 <label class="mandatory-lbl">Name</label>  
 </div>                  
 <div>
 <input id="txtSbutName" name="txtSbutName" type="text" class="easyui-text" maxlength="100" style="width: 500px; margin-top: 5px;text-transform: uppercase;  " value="${requestScope.genTlSbumst.sbutName}">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
 <label class="mandatory-lbl">Code</label>  
 </div>                  
 <div>
 <input id="txtSbutCode" name="txtSbutCode" type="text" class="easyui-text" maxlength="12" style="width: 90px; margin-top: 5px;text-transform: uppercase;" value="${requestScope.genTlSbumst.sbutCode}">
 </div>
 <div style="margin-left:45%;margin-top:-25%;width:110%;">

	<div id="savingdata" style="right:0;position:fixed;display:none;width:55%;"><img id="treeSearchLoading" src="images/FnLocn/SavingData.gif"/></div>
<!--		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>-->
</div>
 </div>
 
 <!--<div  class="easyui-paddingbfpx" style="margin-top: 10px;">
 <label >Description</label>  
 </div>                  
 <div>
 <textarea  rows="4"  cols="17" id="txtSbutDescription"  name="txtSbutDescription"  style="height : 75px; margin-left: 0px;text-transform: uppercase; width : 300px;"  >${requestScope.genTlSbumst.sbutDescription}</textarea>
 </div>
--></div>

<input type="hidden" id="mode" name="mode" />
<div id="flTreeComponent" class="demo" style="width: 50%;"></div>

<input type="hidden" id="txtSbutid" name="txtSbutid" value="${hidden}"/>
<input type="hidden" id="txtsbuadd" name="txtsbuadd" value="${requestScope.sbuadd}"/>
<input type="hidden" id="hdnFormType" name="hdnFormType" value="${requestScope.formType}"/>

<input type="hidden" id="txtSbutKeyid" name="txtSbutKeyid" value="${requestScope.genTlSbumst.sbutKeyid}"/>
</div>
</div>
</form>