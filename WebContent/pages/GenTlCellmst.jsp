<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<!--  /**-->
<!-- * Author:N Arun-->
<!-- * Created on:25.11.2011-->
<!-- */-->
  <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  			initialiseForm('frmCell');	
  			
  			jQuery('#submitForm').val('frmCell'); // set the id of form to submit
  			//fillComboBox("frmCell","cmbCellKeyid","cellCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellKeyid","cellCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellCompanyid","companyCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellCostcentreid","costCenter.commonFilter" );
  	/*		fillComboBox("frmCell","cmbCellFactoryid","factroyCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellSectionid","sectionCombo.commonFilter" );
  			fillComboBox("frmCell","cmbCellCostcentreid","costCenter.commonFilter" );
  			
*/			jQuery('#frmCell .easyui-combobox').css('text-transform', 'uppercase');
  			jQuery('#frmCell .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmCell textarea').css('text-transform', 'uppercase');
  			formatDateBox('dteCellEffectivedate','dd-MMM-yyyy');


  			var factId = jQuery("#frmCell input[id='factory']").val();       ///tttt
  		    var sectionId = jQuery("#frmCell input[id='section']").val();
  		   
  		    var cellId = jQuery("#frmCell input[id='cell']").val();
  		    
  			var machId = jQuery("#frmSection input[id='machine']").val();


  			var compId = jQuery("#frmCell input[id='company']").val(); 

  			var locId = jQuery("#frmCell input[id='location']").val(); 
  			 var pbuId = jQuery("#frmCell input[id='pbu']").val();    
  			
  	        var flid = jQuery("#frmCell input[id='flid']").val(); 
  	        
  	        var dataStr = "&factId=" + factId
  							+ "&sectionId=" + sectionId
  							+ "&cellId=" + cellId + "&machId="+ machId
  							+ "&compId=" + compId
  							+ "&locId=" + locId
  							+ "&pbuId=" + pbuId
  							+"&flid="+ flid;
  			  loadFunctionalLocation("CellfunLocation", "functionalLoc.cell", "frmCelfunLocationValues", "frmCell",dataStr);
  			
  				
  			});
  			function frmCell_FuntLocHierarchy_SuccessCallBack(result)
  			{   

  				//alert(" result :: "+Object.keys(result));
  				// compId,flid,machId,cellId,locnId,sbuId,sectId,pbuId
  				
  				var PBU = result.cellId;
  				//alert(PBU);
  				
  				processAjaxCalls("CellAuto_recall.cell","keyId="+result.cellId, "frmCell_recallsuccessCallback","frmCell_recallerrorCallback");
  		        
  		    }
  			 function frmCell_recallsuccessCallback(record)
    		   {
    		   
  				   jQuery("#txtCellKeyid").val(record.cellmst.CellKeyid);
    			   jQuery("#txtCellName").val(record.cellmst.CellName);
    		  	   jQuery("#txtCellCode").val(record.cellmst.CellCode);
    		  	   jQuery("#cmbCellCostcentreid").combobox("setValue",result.cellmst.CellCostcentreid);
    		  	   jQuery("#dteCellEffectivedate").datebox("setValue",result.cellmst.CellEffectivedate);
    		  	   //jQuery("#txtSectFlid").val(record.dmtmst.SectFlid);
    		  		
    		   }


  			 function  frmCell_beforeSubmit(){
  	  			
  				  var sectionId = jQuery("#frmCell input[id='section']").val();
  	  	   	

  	  	    	if(sectionId == '' || sectionId == ' ' || sectionId == undefined)
  	  			 {
  	  	   		 alert("Select DMT");
  	  	   		 return false;
  	  			 }
  	  	}

    		   function frmCell_recallerrorCallback(record)
    		   {

    		   }
    		   function frmCell_deleteSuccessCallback(result){
        		   alert(10);

    		       alert(result.successData.msg);
    		       clearForm('frmCell');
    		       loadFunctionalLocation("CellfunLocation", "functionalLoc.cell", "frmCelfunLocationValues", "frmCell",dataStr);
    		       navigateToPrevForm();
    		       
    			   }
    	  		  

  			function frmCellcmbCellKeyid_onLoadSuccess()
  			{
  				//fillComboBox("frmCell","cmbCellCompanyid","companyCombo.commonFilter" );
  			}
  			function frmCellcmbCellCompanyid_onLoadSuccess()
  			{
  				fillComboBox("frmCell","cmbCellFactoryid","location.commonFilter" );
  			}
  			function frmCellcmbCellFactoryid_onLoadSuccess()
  			{
  				fillComboBox("frmCell","cmbCellSectionid","sectionCombo.commonFilter" );
  			}
  			function frmCellcmbCellSectionid_onLoadSuccess()
  			{
  				
  				fillComboBox("frmCell","cmbCellCostcentreid","costCenter.commonFilter" );
  			}
  			function frmCellcmbCellCostcentreid_onLoadSuccess()
  			{
  	
  			}
  			
  			function  frmCellcmbCellCompanyid_onSelect(record)
  			{
  				jQuery("#cmbCellFlid").combobox('clear'); 	
  				jQuery("#cmbCellFactoryid").combobox('clear'); 	
  				jQuery("#cmbCellSectionid").combobox('clear');
  				jQuery("#cmbCellCostcentreid").combobox('clear');
  				reloadCombo("frmCell","cmbCellFactoryid","location.commonFilter?compId="+record.id);
  				reloadCombo("frmCell","cmbCellSectionid","sectionCombo.commonFilter?compId="+record.id);
  				
  	  		}
  			function  frmCellcmbCellFactoryid_onSelect(record)
  			
  			{
  				jQuery("#cmbCellSectionid").combobox('clear');
  				jQuery("#cmbCellCostcentreid").combobox('clear');
  				reloadCombo("frmCell","cmbCellSectionid","sectionCombo.commonFilter?factId="+record.id);
  				reloadCombo("frmCell","cmbCellCostcentreid","costCenter.commonFilter?factId="+record.id+"&cellId="+getFieldValue('cmbCellKeyid'));
   	  		}
  			function  frmCellcmbCellSectionid_onSelect(record)
  			{
  	  			//alert("record.id")
  				fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbCellFactoryid","cmbCellCompanyid");
  			}
  			
  			function frmCell_successsCallback(result)
  			{
	  			//alert("frmFactory");
	  			jQuery("#cmbCellKeyid").combobox('clear');
  				reloadCombo("frmCell","cmbCellKeyid","cellCombo.commonFilter");
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
  				
  			
	  		function  frmCellcmbCellKeyid_onSelect(record)
	  		{
	  	  		//alert(record.id);
	  			//processAjaxCalls("cell_recall.cell","keyId="+record.id, "frmCell_recallsuccessCallback","frmCell_recallerrorCallback");
	  		}

	  		function frmCell_FuntLocHierarchy_SuccessCallBack(result){
	  			setFunctionalLocWidth('frmCell','600px');
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
	<form name="frmCell" id="frmCell" >
<div class="easyui-paddingbfpx"  style="height: 414px;padding-top: 30px;padding-left:300px">
   
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
               			<div><input type="text" id="txtCellName" name="txtCellName" class="easyui-text"  maxlength="95" style="width:300px;" value="${requestScope.genTlCellmst.cellName}" "${requestScope.genTlCellmstBean.disableCellName == true ? ' disabled':''}"/>
			  </div>
			   
			  <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label class="mandatory-lbl">Code</label> </div>                      
               <div><input type="text" id="txtCellCode" name="txtCellCode" class="easyui-text"  maxlength="19" style="width:300px;" value="${requestScope.genTlCellmst.cellCode}"  "${requestScope.genTlCellmstBean.disableCellCode == true ? ' disabled':''}"/>
                 </div>
                 
                    <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label class="mandatory-lbl">Cost Center</label>  </div>                     
                <div><input id="cmbCellCostcentreid" name="cmbCellCostcentreid" type="text" class="easyui-combobox" style="width: 300px; margin-top: 5px;" value="${requestScope.genTlCellmst.cellCostcentreid}" "${requestScope.genTlCellmstBean.disableCellCostcentreid == true ? ' disabled':''}"/></div>
              
			   <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label >Effective Date</label> </div>   
			    <div><input id="dteCellEffectivedate" name="dteCellEffectivedate" class="easyui-datebox"  style="width:160px;" value="${requestScope.genTlCellmst.cellEffectivedate}" "${requestScope.genTlCellmstBean.disableCellEffectivedate == true ? ' disabled':''}"/></div>
		
	
</div>
<input type="hidden" id="mode" value="${requestScope.genTlCellmstBean.formMode}" />
<input type="hidden" id="txtCellKeyid" name="txtCellKeyid" value="${requestScope.genTlCellmst.cellKeyid}"/>
</form>


 	