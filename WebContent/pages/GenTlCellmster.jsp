<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

  <script type="text/javascript">
  			jQuery(document).ready(function(){	
  			initialiseForm('frmCell');	
			jQuery('#submitForm').val('frmCell');

  		//	jQuery("#savingdata").css('display','none');

  			
  			//jQuery('#submitForm').val('frmCell'); // set the id of form to submit
  			var keyid=jQuery("#txtCellKeyid").val();
  			var celladdupdate=jQuery("#txtCellAdd").val();
  			//alert("celladdupdate>>>>>>"+celladdupdate);
  			//alert("KEYID:::"+keyid);

  			

  			
  			
			 var elemType =jQuery("#hdnelemType").val();
  			// alert("elemType:"+elemType);
  			 var dlgId=jQuery("#hdndlgId").val();
  			//alert("dlgId: " +dlgId);
  			
  			// var dispCode=jQuery("#hdndispCode").val();
  			 
  			 var elemId=jQuery("#hdnelemId").val();
  			// alert("elementid::::"+elemId);


   			var dataStr ='';		
   			if(jQuery('#hdnFuncformField').val() != '' || jQuery('#hdnFuncformField').val() != null)
   				dataStr +='?elemType='+jQuery('#hdnFuncCondition').val()+'&formField='+jQuery('#hdnFuncformField').val();
   			else
   				dataStr +='?elemType='+jQuery('#hdnFuncCondition').val();	
   			
 
  			
  			//fillComboBox("frmCell","cmbCellKeyid","cellCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellKeyid","cellCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellCompanyid","companyCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellFactoryid","factroyCombo.commonFilter" );
  			//fillComboBox("frmCell","cmbCellCostcentreid","costCenter.commonFilter" );
  			fillComboBox("frmCell","cmbCellSectionid","sectionCombo.commonFilter" );
  	/*		fillComboBox("frmCell","cmbCellFactoryid","factroyCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellSectionid","sectionCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellCostcentreid","costCenter.commonFilter" );*/			

  			jQuery('#frmCell .easyui-combobox').css('text-transform', 'uppercase');
  			jQuery('#frmCell .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmCell textarea').css('text-transform', 'uppercase');
  			formatDateBox('dteCellEffectivedate','dd-MMM-yyyy');


  			// var location = jQuery("#frmActionPlan input[id='location']").val();
  		    // var flid =jQuery("#frmActionPlan input[id='flid']").val();
	         

  			var factId = jQuery("#frmCell input[id='factory']").val();
  			       ///tttt
  		    var sectionId = jQuery("#frmCell input[id='section']").val();
  		   
  		    var cellId = jQuery("#frmCell input[id='cell']").val();
  		    
  			var machId = jQuery("#frmSection input[id='machine']").val();

  		    //var sbuId = jQuery("#frmPbu input[id='sbu']").val(); 

  		      
  		    var sbuId = jQuery("#frmPbu input[id='sbu']").val();   
  		  //  alert(sbuId); 

  			var compId = jQuery("#frmCell input[id='company']").val(); 

  			var locId = jQuery("#frmCell input[id='location']").val(); 
  			 var pbuId = jQuery("#frmCell input[id='pbu']").val();    
  			
  	        var flid = jQuery("#frmCell input[id='flid']").val(); 

  	       // alert("flId-----"+flid);
  	        
  	        var dataStr = "&factId=" + factId
  							+ "&sectionId=" + sectionId
  							+ "&cellId=" + cellId + "&machId="+ machId
  							+ "&compId=" + compId
  							+ "&locId=" + locId
  							+ "&pbuId=" + pbuId
  							+ "&sbuId=" + sbuId
  							+"&flid="+ flid;

			if(celladdupdate=="add")
				{
				loadFunctionalLocation("CellfunLocation", "functionalLocadd.cell", "frmCelfunLocationValues", "frmCell",dataStr);
                
				}
			else{
			
  			  loadFunctionalLocation("CellfunLocation", "functionalLoc.cell", "frmCelfunLocationValues", "frmCell",dataStr);

			}
  				
  			});
  			


  	 		 function closeFlDialog(dlgId)
	  		 { 
	  		 
	  			 jQuery( '#'+dlgId ).hide();
	  			// jQuery('#DIV_FLMASK').removeClass('popup-mask');	
	  			// jQuery( '#'+dlgId ).removeClass('custom-popup');
	  		 }
	  			  	
  
  			 function frmCell_recallsuccessCallback(record)
    		   {
    		   
  				   jQuery("#txtCellKeyid").val(record.cellmst.CellKeyid);
    			   jQuery("#txtCellName").val(record.cellmst.CellName);
    		  	   jQuery("#txtCellCode").val(record.cellmst.CellCode);
    		  	  // jQuery("#cmbCellCostcentreid").combobox("setValue",result.cellmst.CellCostcentreid);
    		  	  // jQuery("#dteCellEffectivedate").datebox("setValue",result.cellmst.CellEffectivedate);
    		  	 
    		  		
    		   }


  			 function  frmCell_beforeSubmit(){
  				jQuery('#mstfrm_div').addClass('popup-mask');
  	  			jQuery('#mstfrm_div').show();
  				jQuery("#savingdata").css('display','block');
  				//jQuery("#flTreeLayer").scrollTop(jQuery(".search_parent_flu-s").offset().top);
  				var cellId = jQuery("#frmCell input[id='cell']").val();
  				 var sectionId = jQuery("#frmCell input[id='section']").val();
  				var celladdupdate=jQuery("#txtCellAdd").val();
  				//alert("sectionId"+sectionId);
  		  
  				/* var sbuId = jQuery("#frmPbu input[id='sbu']").val(); 
  				 alert(sbuId);*/
  	  	   	
                if(celladdupdate=="add"){
                	if(sectionId == '' || sectionId == ' ' || sectionId == undefined)
    	  			 {
    	  	   		 alert("Select DMT");
    	  	   		 return false;
    	  			 }
  	  	    	
                }
                /* else{
                	if(cellId == '' || cellId == ' ' || cellId == undefined)
     	  			 {
     	  	   		 alert("Select JH");
     	  	   		 return false;
     	  			 }
                } */
  	  	}
  			 
  			function frmCell_errorCallback(msg) {
  				alert("msg in error:::"+msg);
  				jQuery("#savingdata").css('display','none');
  			}
  			function frmCell_exceptionCallback(msg) {
  				//alert("msg in sucess:::"+msg);	
  				jQuery("#savingdata").css('display','none');
  				jQuery('#mstfrm_div').hide();
  			}
  			

  			function frmCell_errorCallBack(result){
  				//alert("fail"+result.tpmException);
  				jQuery("#savingdata").css('display','none');
  			}
    		   function frmCell_recallerrorCallback(record)
    		   {
    			   
    		   }
    	
    		   function  frmSbu_beforeDelete()
    		   {

    		   		 var r=confirm("Are You Sure to Delete?");
    		   		if(r)
    		   			return true;
    		   		else
    		   			return false;
    		   	}

  	
  		
  			
  		
  			function frmCell_deleteSuccessCallback(result)
  			{
	  			//alert("frmFactory");
	  			//jQuery("#cmbCellKeyid").combobox('clear');
  				//reloadCombo("frmCell","cmbCellKeyid","cellCombo.commonFilter");
  				if(result.successData.msg=="OriginalIdExists")
				{
					var inactmsg = result.successData.Errmsg;
  				//reloadCombo("frmSection","cmbSectKeyid","sectionCombo.commonFilter");
					var t=confirm("Record is refered, can not delete\! Do you want to make inactive? ");
					if(inactmsg)
					{			
						checkConfirm();
					}	
				}
				//alert(result.successData.msg);
			}
			function checkConfirm()
			{
				jQuery('#hdnInactive').val("Inactive");	
				deleteRecord("frmCell","cell_delete.cell?q=2&hdnInactive=Inactive");			
				//saveForm("frmCell","cell_delete.cell?q=2&hdnInactive=Inactive","");
			}
  		

	  		function frmCell_FuntLocHierarchy_SuccessCallBack(result){
	  			//alert(" result :: "+Object.keys(result));
	  			var SBU = result.cellId;
	  			
	  		 processAjaxCalls("CellAuto_recall.cell","keyId="+result.cellId, "frmCell_recallsuccessCallback","frmCell_recallerrorCallback");
	  			
	  		  	
	  	}

	  		function  frmCellcmbCellSectionid_onSelect(record)
  			{
  	  			//alert("record.id")
  				//fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbCellFactoryid","cmbCellCompanyid");
  			}

		  	
	  		function frmCell_successsCallback(result)
  			{
	  			//alert("frmFactory");
	  			  //refreshForm();
	  		// navigateToPrevForm();
	  		jQuery("#savingdata").css('display','none');
	  		jQuery('#mstfrm_div').hide();
	  			 loadFunctionalLocation("CellfunLocation", "functionalLoc.cell?&CELLMASTER=CELLMASTER&frmType=SBU", "frmCellfunLocation", "frmCell"," ");
	  		   //	navigateToPrevForm("load_view.funlocn");
		  		   refreshForm();
	  		 
  			}	

	  		function  frmCellcmbCellKeyid_onSelect(record)
	  		{
	  	  		//alert(record.id);
	  			processAjaxCalls("cell_recall.cell","keyId="+record.id, "frmCell_recallsuccessCallback","frmCell_recallerrorCallback");
	  		}
	  		
	 		/* function frmCell_recallsuccessCallback(result)
	 		 { 
	 	  		
	 	  		
	 	  		//alert("sucess");
	 	  		jQuery("#cmbCellKeyid").combobox("setValue",result.cell.CellKeyid);
	 	  		jQuery("#cmbCellCompanyid").combobox("setValue",result.cell.CellCompanyid);
	 	  	    jQuery("#cmbCellFactoryid").combobox("setValue",result.cell.CellFactoryid);
	 	  	 	jQuery("#cmbCellSectionid").combobox("setValue",result.cell.CellSectionid);
	 	  	 	jQuery("#cmbCellCostcentreid").combobox("setValue",result.cell.CellCostcentreid);
	 	  		jQuery("#txtCellName").val(result.cell.CellName);
	 	  		jQuery("#txtCellCode").val(result.cell.CellCode);
	 	  		jQuery("#dteCellEffectivedate").datebox("setValue",result.cell.CellEffectivedate);
	 	  	
	 		 }*/
	 	
	  		/* function frmCell_recallerrorCallback(result)
	  		 {
	  			//alert("Error in callback");
	  		 }
	  		/* function frmCell_deleteSuccessCallback(result)
	  		 {  
		  		//  if(confirm( result.successData.msg ) == true){	
	  			alert(result.successData.msg);
		  		  //}
	  		 }*/
	  		 
  		
  
</script>
	<form name="frmCell" id="frmCell" ><!--
	
<!--<div class="easyui-paddingbfpx"  style="height: 414px;padding-top: 10px;padding-left:245px">-->
   <div style="margin-left:03%;margin-top:0px;">
 				<input type="hidden" id="hdnInactive" name="hdnInactive"></input>
		      
                <div id="frmCellFuntKeyIds">
<!--					<input type="hidden" id="factory" name="cmbCellFactoryid" value=""></input> -->
					<input type="hidden" id="section" name=cmbCellSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbCellMachineid" value=""></input>
					<input type="hidden" id="company" name="cmbCellCompanyid" value=""></input>
					<input type="hidden" id="location" name="cmbCellLocationid" value=""></input>
				
					<input type="hidden" id="pbu" name=cmbCellFactoryid  value="${requestScope.genTlCellmst.cellFactoryid}"></input> 
					<input type="hidden" id="flid" name="cmbCellFlid" value="${requestScope.genTlCellmst.cellFlid}"></input>        
		</div>

     <div  class="easyui-paddingbfpx" id="CellfunLocation" style="width: 70%;margin-top:40px;"></div>
     
           

   		      <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label class="mandatory-lbl">Name</label> </div>                      
               			<div><input type="text" id="txtCellName" name="txtCellName" class="easyui-text"  maxlength="100" style="width:500px;" value="${requestScope.genTlCellmst.cellName}"/>
			  </div>
			   
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label class="mandatory-lbl">Code</label> </div>                      
               <div><input type="text" id="txtCellCode" name="txtCellCode" class="easyui-text"  maxlength="12" style="width:90px;" value="${requestScope.genTlCellmst.cellCode}" />
                
                
               
		 
	</div>
	
                
                 </div><!--
                 
                    <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label class="mandatory-lbl">Cost Center</label>  </div>                     
                <div><input id="cmbCellCostcentreid" name="cmbCellCostcentreid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="${requestScope.genTlCellmst.cellCostcentreid}"<c:out value = "${requestScope.genTlCellmstBean.disableCellCostcentreid == true ? ' disabled':''}"/>/></div>
              
			   <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label >Effective Date</label> </div>   
			    <div><input id="dteCellEffectivedate" name="dteCellEffectivedate" class="easyui-datebox"  style="width:160px;" value="${requestScope.genTlCellmst.cellEffectivedate}"<c:out value = "${requestScope.genTlCellmstBean.disableCellEffectivedate == true ? ' disabled':''}"/>/></div>
		
	
-->
</div>
<div style="margin-left:45%;margin-top:-25%;width:110%;">

	<div id="savingdata" style="right:0;position:fixed;display:none;width:55%;"><img id="treeSearchLoading" src="images/FnLocn/SavingData.gif"/></div>
<!--		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>-->
</div>
<input type="hidden" id="mode" value="${requestScope.genTlCellmstBean.formMode}" />
<input type="hidden" id="txtCellKeyid" name="txtCellKeyid" value="${requestScope.genTlCellmst.cellKeyid}"/>
		<input type="hidden" id="txtCellAdd" name="txtCellAdd" value="${requestScope.celladd}"></input>        
</form>


 	