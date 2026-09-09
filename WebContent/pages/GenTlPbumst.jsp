<script type="text/javascript">	
	jQuery(document).ready(function(){	
		initialiseForm('frmPbu');
		//jQuery("#savingdata").css('display','none');
		jQuery('#submitForm').val('frmPbu');
		//alert("INSIDE THE PBUMST JSP");


		jQuery('#frmPbu .easyui-combobox').css('text-transform', 'uppercase');
		jQuery('#frmPbu .easyui-text').css('text-transform', 'uppercase');
		jQuery('#frmPbu textarea').css('text-transform', 'uppercase');

		var keyid=jQuery("#txtPbutKeyid").val();
		//alert("pbuId:"+keyid);
		var pbuaddupdate=jQuery("#txtpbuadd").val();
		//alert("pbuaddupdate::"+pbuaddupdate);
		//alert("pbuaddupdate"+pbuaddupdate);
	 	//fillComboBox("frmSbu","cmbPbutSbuid","sbuCombo.commonFilter" );
		//fillComboBox("frmSbu","cmbPbutKeyid","pbuCombo.commonFilter" );

		var factId = jQuery("#frmPbu input[id='factory']").val();
		
	    var sectionId = jQuery("#frmPbu input[id='section']").val();
	    var cellId = jQuery("#frmPbu input[id='cell']").val();

	    var sbuId = jQuery("#frmPbu input[id='sbu']").val();   
	    var companyId = jQuery("#frmPbu input[id='company']").val(); 

	    var locationId = jQuery("#frmPbu input[id='location']").val(); 
		var machId = jQuery("#frmPbu input[id='machine']").val();
		
        var flid = jQuery("#frmPbu input[id='flid']").val(); 
      //  alert(flid);
        var dataStr = "&factId=" + factId
						+ "&sectionId=" + sectionId
						+ "&cellId=" + cellId
						+ "&sbuId=" + sbuId
						+ "&companyId=" + companyId
						+ "&locationId=" + locationId
						+ "&machId="
						+ machId+"&flid="+ flid;
		if(pbuaddupdate=="add")
			{
			
			loadFunctionalLocation("PbufunLocation", "functionalLocpbuadd.emr", "frmPbufunLocation", "frmPbu",dataStr);
			//loadFunctionalLocation("PbufunLocation", "functionalLocpbuadd.commonFilter?PBUMASTER=PBUMASTER&frmType=PBU", "frmPbufunLocation", "frmPbu",dataStr);
            
			}
		 else{
	       loadFunctionalLocation("PbufunLocation", "functionalLocpbuadd.emr", "frmPbufunLocation", "frmPbu",dataStr);
		}
	 	});
	
	function frmPbu_FuntLocHierarchy_SuccessCallBack(result)
	{   

		//alert(" result :: "+Object.keys(result));
		// compId,flid,machId,cellId,locnId,sbuId,sectId,pbuId
		var PBU = result.pbuId;
		processAjaxCalls("PBU_recall.funlocn","keyId="+result.pbuId, "frmPbu_recallsuccessCallback","frmPbu_recallerrorCallback");
        
    }
	 function frmPbu_recallsuccessCallback(record)
	   {
	       //alert(" Successcallback :: "+record.sbumst.SbutActive);
	       //alert(" Successcallback :: "+record.sbumst.SbutName);
		   jQuery("#txtPbutKeyid").val(record.pbumst.PbutKeyid);
		   jQuery("#txtPbutName").val(record.pbumst.PbutName);
	  	   jQuery("#txtPbutCode").val(record.pbumst.PbutCode);
	  	   jQuery("#txtPbutDescription").val(record.pbumst.PbutDescription);
	  		
	   }

	   function frmPbu_successsCallback(result)
	   {
		   //navigateToPrevForm();
		   	//refreshForm();
		   	jQuery("#savingdata").css('display','none');
		   	jQuery('#mstfrm_div').hide();
		loadFunctionalLocation("PbufunLocation", "functionalLocpbu.commonFilter?&PBUMASTER=PBUMASTER&frmType=PBU", "frmPbufunLocation", "frmPbu"," ");
		//navigateToPrevForm("load_view.funlocn");
		refreshForm();
		
	   	  
	   }
		function frmPbu_exceptionCallback(msg) {
				//alert("msg in sucess:::"+msg);	
				jQuery("#savingdata").css('display','none');
				jQuery('#mstfrm_div').hide();
			}
	   function frmPbu_deleteSuccessCallback(result){

       alert(result.successData.msg);
       clearForm("frmPbu");
      loadFunctionalLocation("PbufunLocation", "functionalLocpbu.commonFilter?&PBUMASTER=PBUMASTER&frmType=PBU", "frmPbufunLocation", "frmPbu"," ");
       navigateToPrevForm();
      /* if(result.successData.msg=="OriginalIdExists")
		{
			var inactmsg = result.successData.Errmsg;
			//reloadCombo("frmSection","cmbSectKeyid","sectionCombo.commonFilter");
			var t=confirm("Record is refered, can not delete\! Do you want to make inactive? ");
			if(inactmsg)
			{			
				checkConfirm();
			}	
		}*/
		//alert(result.successData.msg);
	}

			
       

	   function frmPbu_recallerrorCallback(record)
	   {

	   }

	   function  frmPbu_beforeDelete()
	   {
	
	   		 var r=confirm("Are You Sure to Delete?");
	   		if(r)
	   			return true;
	   		else
	   			return false;
	   	}
	   
		function frmPbu_exceptionCallback(msg) {
				//alert("msg in sucess:::"+msg);	
				jQuery("#savingdata").css('display','none');
				jQuery('#mstfrm_div').hide();
			}
function frmPbu_beforeSubmit(){

	//alert("INSIDE OF PBUMST JSP");
	jQuery('#mstfrm_div').addClass('popup-mask');
  			jQuery('#mstfrm_div').show();
	jQuery("#savingdata").css('display','block');
	var pbuaddupdate=jQuery("#txtpbuadd").val();
	var sbu = jQuery("#frmPbu input[id='sbu']").val(); 
	var pbuId = jQuery("#frmPbu input[id='pbu']").val();  
     if(pbuaddupdate=="add")
	 {
    		if(sbu == '' || sbu == ' ' || sbu == undefined)
   		 {
      		 alert("Select SBU");
      		 return false;
   		 }
    	
	 }
     /* else
         {
   	      if(pbuId == '' || pbuId == ' ' || pbuId == undefined)
		  {
   		  alert("Select PBU");
   		   return false;
		  }
       } */
}
</script>

<form name="frmPbu" id="frmPbu" >

 
	 
	 <div style="margin-left:03%;margin-top:20px;">
		 
	
                
<div id="frmpbuFuntKeyIds">
					<input type="hidden" id="factory" name="cmbPbutFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbPbutSectionid value=""></input> 
					<input type="hidden" id="sbu"  name=cmbPbutSbuid  value="${requestScope.genTlPbumst.pbutSbuid}"></input> 
					<input type="hidden" id="cell"    name="cmbPbutCellid" value=""></input> 
					<input type="hidden" id="company" name="cmbCompany" value=""></input>
					<input type="hidden" id="location" name="cmbLocation" value=""></input>
					<input type="hidden" id="machine" name="cmbPbutMachineid" value=""></input>
					<input type="hidden" id="flid" name="cmbPbutFlid" value="${requestScope.genTlPbumst.pbutFlid}"></input>        
</div>
<div  class="easyui-paddingbfpx" id="PbufunLocation" style="width: 70%;margin-top:40px;"></div>


<div  class="easyui-paddingbfpx" style="display:none;">
   <label>PBU</label> </div>         
 <div style="display:none;">
 <input id="cmbPbutKeyid" name="cmbPbutKeyid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="">
 </div>

 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
 <label class="mandatory-lbl">Name</label>  
 </div>                  
 <div>                         
 <input id="txtPbutName" name="txtPbutName" type="text" class="easyui-text" maxlength="100" style="width: 500px; margin-top: 5px;" value="${requestScope.genTlPbumst.pbutName}">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
 <label class="mandatory-lbl">Code</label>  
 </div>                  
 <div>
 <input id="txtPbutCode" name="txtPbutCode" type="text" class="easyui-text" maxlength="12"  style="width: 90px; margin-top: 5px;" value="${requestScope.genTlPbumst.pbutCode}">
 </div>
 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
  <!--<label >Description</label>  
 </div>                  
<div>                              
 <textarea  rows="4"  cols="17" id="txtPbutDescription" name="txtPbutDescription" maxlength="300" style="height : 75px; margin-left: 0px;text-transform: uppercase; width : 300px;"  >${requestScope.genTlPbumst.pbutDescription}</textarea>
 </div>
--></div>
<div style="margin-left:45%;margin-top:-25%;width:110%;">

	<div id="savingdata" style="right:0;position:fixed;display:none;width:55%;"><img id="treeSearchLoading" src="images/FnLocn/SavingData.gif"/></div>
<!--		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>-->
</div>
</div> 
<input type="hidden" id="mode" name="mode" />
<input type="hidden" id="txtpbuadd" name="txtpbuadd" value="${requestScope.pbuadd}"/>
 <input type="hidden" id="hdnInactive" name="hdnInactive"></input>
<input type="hidden" id="txtPbutKeyid" name="txtPbutKeyid" value="${requestScope.genTlPbumst.pbutKeyid}"/>
</form>