<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<script type="text/javascript">
	jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmBD');
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }	
	initialiseForm('frmBD');	

	setTimeout(function() {disableField('frmBD', 'cboJobType');},500);
	if(  !jQuery('#cmbBDKeyId').is(':disabled') )
		fillComboBox("frmBD","cmbBDKeyId","breakdown.commonFilter" );
	if(  !jQuery('#cmbMSRKeyId').is(':disabled') )
		fillComboBox("frmBD","cmbMSRKeyId","msr.commonFilter" );
	if(  !jQuery('#cmbFailureType').is(':disabled') )
		fillComboBox("frmBD","cmbFailureType","failuretype.commonFilter" );
	if(  !jQuery('#cmbprodcngroup').is(':disabled') )
		fillComboBox("frmBD","cmbprodcngroup","productiongrpp.commonFilter" );
	if(  !jQuery('#cmbbdrootcause').is(':disabled') )
		fillComboBox("frmBD","cmbbdrootcause","rootcausebd.commonFilter" );
	if(  !jQuery('#cmbdefectpheno').is(':disabled') )
		fillComboBox("frmBD","cmbdefectpheno","phenomena.commonFilter" );	
	if(  !jQuery('#cmbcause').is(':disabled') )	
		fillComboBox("frmBD","cmbcause","cause.commonFilter" );
	if(  !jQuery('#cmbshiftincharge').is(':disabled') )
		fillComboBox("frmBD","cmbshiftincharge","employee.commonFilter" );
	if(  !jQuery('#cmbEngineer').is(':disabled') )
		fillComboBox("frmBD","cmbEngineer","employee.commonFilter" );
	if(  !jQuery('#cmbyyy').is(':disabled') )
		fillComboBox("frmBD","cmbyyy","yyy.commonFilter" );
	
	//if(  !jQuery('#cmbActivity').is(':disabled') )
		//fillComboBox("frmBD","cmbActivity","activity.commonFilter" );
	
	if(  !jQuery('#cmbyyy').is(':disabled') )
		fillComboBox("frmBD","cmbstep","jhstep.commonFilter" );
	if(jQuery('#txttop').val()=='' || jQuery('#txttop').val() == null)
		jQuery('#txttop').val('20');
	
	
	/*Added by manikandan for bdpareto 20.3.12
	if(jQuery('#chkallchkbox').attr('disabled',true)){
		jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
		jQuery('input:checkbox[name=chktimechkbox]').attr('checked',false);
	}
	/*End*/
	});

	/*function frmBDcmbFailureType_onLoadSuccess() 	{		
		fillComboBox("frmBD","cmbbdrootcause","rootcausebd.commonFilter" );
	}
	function frmBDcmbbdrootcause_onLoadSuccess() 	{
		fillComboBox("frmBD","cmbdefectpheno","phenomena.commonFilter" );		
	}
	function frmBDcmbdefectpheno_onLoadSuccess() 	{
		fillComboBox("frmBD","cmbcause","cause.commonFilter" );
	}
	function frmBDcmbcause_onLoadSuccess() 	{
		fillComboBox("frmBD","cmbshiftincharge","employee.commonFilter" );
	}		
	function frmBDcmbshiftincharge_onLoadSuccess() 	{
		fillComboBox("frmBD","cmbEngineer","employee.commonFilter" );
	}	
	function frmBDcmbEngineer_onLoadSuccess() 	{
		fillComboBox("frmBD","cmbyyy","yyy.commonFilter" );
	}	
	function frmBDcmbyyy_onLoadSuccess() 	{
		
	}*/		
	/*jQuery('#chkallchkbox').click(function(){
		
			  			
		jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
		jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
	
	});
	*/	
	jQuery('#chkfrequencychkbox').click(function(){		
 		if(jQuery('#chkfrequencychkbox').is(':checked') == true)
		{
 			
 			if(jQuery('#chkseveritychkbox').is(':checked') == true)
 	 			{
 				jQuery('input:checkbox[name=chkseveritychkbox]').attr('checked',false); 	
 					
 	 			}
		}	
		 	}); 

	jQuery('#chkseveritychkbox').click(function(){		
 		if(jQuery('#chkseveritychkbox').is(':checked') == true)
		{
 			
 			if(jQuery('#chkfrequencychkbox').is(':checked') == true)
 	 			{
 				jQuery('input:checkbox[name=chkfrequencychkbox]').attr('checked',false); 
 	 			}
		}					
		 	}); 

	jQuery('#chktimechkbox').click(function(){		
 		if(jQuery('#chktimechkbox').is(':checked') == false)
		{
 			if(jQuery('#chkallchkbox').is(':checked') == false && jQuery('#chkoccurchkbox').is(':checked') == false)
 				jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
 			else
 				jQuery('input:checkbox[name=chktimechkbox]').attr('checked',false);
 			
 			jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	false);
 				
		}
 		else
 	 	{
			jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
			if(jQuery('#chkoccurchkbox').is(':checked') == true)
				jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	true);
 	 	}
 	 	
					
		//}
 	}); 		

	jQuery('#chkoccurchkbox').click(function(){
		if(jQuery('#chkoccurchkbox').is(':checked') == false)
		{
			if(jQuery('#chkallchkbox').is(':checked') == false && jQuery('#chktimechkbox').is(':checked') == false)
 				jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
 			else
 				jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',false);
 			
 			jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	false);
 				
		}
 		else
 	 	{
			jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
			if(jQuery('#chktimechkbox').is(':checked') == true)
				jQuery('input:checkbox[name=chkallchkbox]').attr('checked',	true);
 	 	}	
	});
	jQuery('#chkallchkbox').click(function(){
		if(jQuery('#chkallchkbox').is(':checked') == true)
		{
			jQuery('input:checkbox[name=chkoccurchkbox]').attr('checked',true);
			jQuery('input:checkbox[name=chktimechkbox]').attr('checked',true);
		}			
	});
	jQuery('#chkallparameter').click(function(){
		if(jQuery('#chkallparameter').is(':checked') == true)
		{
			jQuery('input:checkbox[name=chkemployee]').attr('checked',true);
			jQuery('input:checkbox[name=chkcontract]').attr('checked',true);
			jQuery('input:checkbox[name=chkspare]').attr('checked',true);
			jQuery('input:checkbox[name=chkservice]').attr('checked',true);
			jQuery('input:checkbox[name=chkutility]').attr('checked',true);
			jQuery('input:checkbox[name=chkother]').attr('checked',true);
			jQuery('input:checkbox[name=chktotal]').attr('checked',true);
		}	
		else
		{ 
			jQuery('input:checkbox[name=chkemployee]').attr('checked',false);
			jQuery('input:checkbox[name=chkcontract]').attr('checked',false);
			jQuery('input:checkbox[name=chkspare]').attr('checked',false);
			jQuery('input:checkbox[name=chkservice]').attr('checked',false);
			jQuery('input:checkbox[name=chkutility]').attr('checked',false);
			jQuery('input:checkbox[name=chkother]').attr('checked',false);
			jQuery('input:checkbox[name=chktotal]').attr('checked',false);
			 
		}		
	}); 

	
	function  frmBDcmbdefectpheno_onSelect(record)
	{	
		jQuery("#cmbcause").combobox('clear');
		reloadCombo("frmBD","cmbcause","cause.commonFilter?Phenomena="+ record.id );
		
	}
	
	
	
	
	
	jQuery('#chktradewise').click(function(){		
 		if(jQuery('#chktradewise').is(':checked') == true)
		{
 			
 				jQuery('input:checkbox[name=chkjobtypwise]').attr('checked',false);
 				jQuery('input:checkbox[name=chkPMCount]').attr('checked',false);
 				disableField('frmBD', 'cboJobType');
 		 				
		}
	}); 	
	
	jQuery('#chkjobtypwise').click(function(){		
 		if(jQuery('#chkjobtypwise').is(':checked') == true)
		{
 			
 				jQuery('input:checkbox[name=chktradewise]').attr('checked',false);
 				jQuery('input:checkbox[name=chkPMCount]').attr('checked',false);
 				enableFormFields('frmBD', 'cboJobType');
		}
	});

	jQuery('#chkPMCount').click(function(){		
 		if(jQuery('#chkPMCount').is(':checked') == true)
		{
 			
 				jQuery('input:checkbox[name=chktradewise]').attr('checked',false);
 				jQuery('input:checkbox[name=chkjobtypwise]').attr('checked',false);
 				enableFormFields('frmBD', 'chkPMCount');
		}
	}); 	 	
	
	jQuery('#chkActwise').click(function(){		
 		if(jQuery('#chkActwise').is(':checked') == true)
		{
 			
 				jQuery('input:checkbox[name=chkMonwise]').attr('checked',false);
 			
 		 				
		}
	}); 	
	
	jQuery('#chkMonwise').click(function(){		
 		if(jQuery('#chkMonwise').is(':checked') == true)
		{
 			
 				jQuery('input:checkbox[name=chkActwise]').attr('checked',false);
 					
		}
	}); 	

	
	
	function getRelatedFilterValues()
	{
		var filterStr="";

		var cmbBDKeyId = jQuery("#cmbBDKeyId").combobox("getValue");
		filterStr += "&cmbBDKeyId="+cmbBDKeyId;

		var cmbMSRKeyId = jQuery("#cmbMSRKeyId").combobox("getValue");
		filterStr += "&cmbMSRKeyId="+cmbMSRKeyId;

		var cmbFailureType = jQuery("#cmbFailureType").combobox("getValue");
		filterStr += "&cmbFailureType="+cmbFailureType;

		var cmbdefectpheno = jQuery("#cmbdefectpheno").combobox("getValue");
		filterStr += "&cmbPhenomenaId="+cmbdefectpheno;

		var cmbcause = jQuery("#cmbcause").combobox("getValue");
		filterStr += "&cmbcause="+cmbcause;

		var cmbbdrootcause = jQuery("#cmbbdrootcause").combobox("getValue");
		filterStr += "&cmbbdrootcause="+cmbbdrootcause;

		var cmbprodcngroup = jQuery("#cmbprodcngroup").combobox("getValue");
		filterStr += "&cmbprodcngroup="+cmbprodcngroup;
		
		var cmbshiftincharge = jQuery("#cmbshiftincharge").combobox("getValue");
		filterStr += "&cmbshiftincharge="+cmbshiftincharge;
		
		var cmbstep = jQuery("#cmbstep").combobox("getValue");
		filterStr += "&cmbstep="+cmbstep;
		

		var cmbEngineer = jQuery("#cmbEngineer").combobox("getValue");
		filterStr += "&cmbEngineer="+cmbEngineer;
				
		var cmbyyy = jQuery("#cmbyyy").combobox("getValue");
		filterStr += "&cmbyyy="+cmbyyy;

		var cboselbdtype = jQuery("#cboselBdType").val();
		filterStr += "&cboselbdtype="+cboselbdtype;

		var cboFrequency = jQuery("#cboFrequency").val();
		filterStr += "&cboFrequency="+cboFrequency;
		
		if(  !jQuery('#cboJobType').is(':disabled') )
		{
			var cboJobType = jQuery("#cboJobType").val();
			filterStr += "&cboJobType="+cboJobType;
		}
		var cbostatus= jQuery("#cbostatus").val();
		filterStr += "&cbostatus="+cbostatus;

		var cbocounter= jQuery("#cbocounter").val();
		filterStr += "&cbocounter="+cbocounter;
		
		var cbooptions= jQuery("#cbooptions").val();
		filterStr += "&cbooptions="+cbooptions;
		//for breakdown pareto by manikandan
		var cboparetooptions= jQuery("#cboparetooptions").val();
		filterStr += "&cboparetooptions="+cboparetooptions;
		
		//var txtengineer = jQuery("#cmbengineer").val();
		//filterStr += "&txtengineer="+txtengineer;
		
		var cbosparesSelectBox = jQuery("#cbosparesSelectBox").val();
		filterStr += "&cbosparesSelectBox="+cbosparesSelectBox;
	
		var chkoccurchkbox = getChkBoxVal("chkoccurchkbox");		
		filterStr += "&chkoccurchkbox="+ (chkoccurchkbox == "1" || chkoccurchkbox == 1 ? 'Y':'N');
		
		var chktimechkbox = getChkBoxVal("chktimechkbox");		
		filterStr += "&chktimechkbox="+ (chktimechkbox == "1" || chktimechkbox == 1 ? 'Y':'N');
	
	
		filterStr += "&chkfrequencychkbox="+getChkBoxVal('chkfrequencychkbox');
		filterStr += "&chkseveritychkbox="+getChkBoxVal('chkseveritychkbox');
		
		filterStr += "&chkzerobdchkbox="+getChkBoxVal('chkzerobdchkbox');
		filterStr += "&chkallchkbox="+getChkBoxVal('chkallchkbox');
		filterStr += "&chkremallchkbox="+getChkBoxVal('chkremallchkbox');
		filterStr += "&chkfachkbox="+getChkBoxVal('chkfachkbox');
		filterStr += "&chkpillarchkbox="+getChkBoxVal('chkpillarchkbox');
		filterStr += "&chkrcchkbox="+getChkBoxVal('chkrcchkbox');		
		filterStr += "&chkrccchkbox="+getChkBoxVal('chkrccchkbox');
		filterStr += "&chkcmchkbox="+getChkBoxVal('chkcmchkbox');	

		filterStr += "&chkircchkbox="+getChkBoxVal('chkircchkbox');

		filterStr += "&chkiyychkbox="+getChkBoxVal('chkiyychkbox');
		
		
		var chktradewise = getChkBoxVal("chktradewise");	
		if(chktradewise == "1")
			filterStr += "&reporttype=TRADE";
		var chkjobtypwise = getChkBoxVal("chkjobtypwise");	
		if(chkjobtypwise == "1")
			filterStr += "&reporttype=JOBTYPE";
		var chkPMCount = getChkBoxVal("chkPMCount");	
		if(chkPMCount == "1")
			filterStr += "&reporttype=PMCOUNT";
		//filterStr += "&reporttype="+ (chktradewise == "1" || chktradewise == 1 ? 'TRADE':'JOBTYPE');
		
		
		
		filterStr += "&chktradewise="+getChkBoxVal('chktradewise');
		filterStr += "&chkjobtypwise="+getChkBoxVal('chkjobtypwise');

		filterStr += "&chkActwise="+getChkBoxVal('chkActwise');

		filterStr += "&chkMonwise="+getChkBoxVal('chkMonwise');
		filterStr += "&chkgrpByEqp="+getChkBoxVal('chkgrpByEqp');
		filterStr += "&chkAllEqpmnt="+getChkBoxVal('chkAllEqpmnt');
		filterStr += "&chkgrpByAsm="+getChkBoxVal('chkgrpByAsm');
		
		var txttop = jQuery("#txttop").val();
		filterStr += "&txttop="+txttop;
		
		var cboactivitytype = jQuery('#cboactivityType').val();
		filterStr += "&cboactivityType="+cboactivitytype;

		var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');
		filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
		var chkActivity = getChkBoxVal('chkActivity');
		filterStr += "&chkActivity="+(chkActivity=="1" || chkActivity==1? 'Y':'N');
		
		filterStr += "&chkjhchkbox="+getChkBoxVal('chkjhchkbox');
		filterStr += "&chkdesignchkbox="+getChkBoxVal('chkdesignchkbox');
		filterStr += "&chkpmchkbox="+getChkBoxVal('chkpmchkbox');
		filterStr += "&chketchkbox="+getChkBoxVal('chketchkbox');
				
		filterStr += "&chkemployee="+getChkBoxVal('chkemployee');
		filterStr += "&chkcontract="+getChkBoxVal('chkcontract');
		filterStr += "&chkspare="+getChkBoxVal('chkspare');
		filterStr += "&chkservice="+getChkBoxVal('chkservice');
		filterStr += "&chkutility="+getChkBoxVal('chkutility');
		filterStr += "&chkother="+getChkBoxVal('chkother');
		filterStr += "&chktotal="+getChkBoxVal('chktotal');
		filterStr += "&chkallparameter="+getChkBoxVal('chkallparameter');
		filterStr += "&chkInternal="+getChkBoxVal('chkInternal');
		filterStr += "&chkExternal="+getChkBoxVal('chkExternal');
		filterStr += "&chkundefinedPP="+getChkBoxVal('chkundefinedPP');
		filterStr += "&chkrepeatedBD="+getChkBoxVal('chkrepeatedBD');
		filterStr += "&chkCompleted="+getChkBoxVal('chkCompleted');
		filterStr += "&chkPending="+getChkBoxVal('chkPending');

	
		
		return filterStr;
	}

	function getChkBoxVal(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}
	
	
	
	
	</script>
	<form id="frmBD" name="frmFilter">
	
						<!--   BD Related Tab	-->
						<div title="BD Related" style="padding:10px;">
						
								<div class="sub-header">Regular</div>
								<div align="center">
								<table>
									<tr>
										<td valign="top">
												<div  class="easyui-paddingbfpx">
				                        			<label>Breakdown</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbBDKeyId" name="cmbBDKeyId" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
											   	<div  class="easyui-paddingbfpx">
				                        			<label>Failure Type</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbFailureType" name="cmbFailureType" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
							                   
							                   <div  class="easyui-paddingbfpx">
				                        			<label>Phenomena</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbdefectpheno" name="cmbdefectpheno" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
							                   
							                    <div  class="easyui-paddingbfpx">
				                        			<label>Cause</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbcause" name="cmbcause" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
							                    <div  class="easyui-paddingbfpx">
				                        			<label>Aut.Maint.Step</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbstep" name="cmbstep" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
					                  			</td>
					                  			<td style="padding-left:20px;">
					                  			
					                  			<div  class="easyui-paddingbfpx">
		                        					<label>MSR</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbMSRKeyId" name="cmbMSRKeyId" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
									   			<div  class="easyui-paddingbfpx">
		                        						<label>BD Root Cause</label>                       
				                    		   </div> 
							                    <div class="easyui-paddingbfpx"> 
							                        <input id="cmbbdrootcause" name="cmbbdrootcause" class="easyui-combobox"  style="width:350px" value=""  >                       
							                   </div>
					                   
					                    <div  class="easyui-paddingbfpx">
		                        			<label>Production Group</label>                       
		                    		   </div> 
					                    <div class="easyui-paddingbfpx"> 
					                        <input id="cmbprodcngroup" name="cmbprodcngroup" class="easyui-combobox"  style="width:350px" value=""  >                       
					                   </div>
					                   
					                   <div  class="easyui-paddingbfpx">
		                        			<label>Shift Incharge</label>                       
		                    		   </div> 
					                    <div class="easyui-paddingbtpx"> 
					                        <input id="cmbshiftincharge" name="cmbshiftincharge" class="easyui-combobox"  style="width:350px" value=""  >
					                    </div>
					                    
					                    <div>
		                        			<label>Status</label>                       
		                    		   </div> 
					                    <div class="easyui-paddingbtpx"> 
					                       <select id="cbostatus" class="easyui-combobox" name="cbostatus" style="width:350px;" >
				                      		<option value="A"> ALL</option>
				                      		<option value="F">FINAL ACTION PENDING</option>
											<option value="W"> WHY-WHY PENDING</option>
											<option value="M"> COUNTER MEASURE PENDING</option>
											<option value="R"> REPEATED BREAKDOWN</option>
											<option value="C"> COMPLETED</option>
											
										</select>  
					                    </div>
			                  			</td>
			                  			
			                   		</tr>
			                   </table>
			                   </div>
			                    <div class="sub-header">Advanced Filter Criteria</div>
			                    <br>
			                    <div align="center">
								<table><tr><td>
					            <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
					            <span>  <input id="chkallchkbox" name="chkallchkbox" type="checkbox"  checked="checked"  value="Y"/> <label>All</label></span>
		                  			    <span  style="margin-left: 13.4%; margin-left: 11%\9;"> <input id="chkoccurchkbox" name="chkoccurchkbox" type="checkbox" checked="checked" value="Y"/> <label>Occurence</label></span>
		                   			    <span  style="margin-left: 13.4%;">  <input id="chktimechkbox" name="chktimechkbox" type="checkbox" checked="checked"  value="Y"/> <label>Time</label></span>	<br/><br/>
		                   				<span  style="margin-left: 0%;">  <input id="chkzerobdchkbox" name="chkzerobdchkbox" type="checkbox"  value="Y"/> <label>Include Zero Breakdowns</label></span>
										<span style="padding-left:13.4%;"> <input id="chkRemoveBlank" name="chkRemoveBlank" type="checkbox" checked=checked/><label>Remove Blanks</label></span>
		                    	</div> 
                    		
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px; padding-top: 0%\9;">
                  					  <input id="chkfachkbox" name="chkfachkbox" type="checkbox" checked="checked"  value="Y"/> <label>Final Action</label>
                  					  <span  style="margin-left: 13.4%; margin-left: 7%\9;">  <input id="chkpillarchkbox" name="chkpillarchkbox" type="checkbox" checked="checked"  value="Y"/> <label>Pillar</label></span>
                   					  <span  style="margin-left: 8.6%; margin-left: 12.5%\9;">  <input id="chkrcchkbox"  name="chkrcchkbox" type="checkbox" checked="checked"  value="Y"/> <label>Root Cause</label></span><br/><br/>
                   					  <span>  <input id="chkrccchkbox"  name="chkrccchkbox" type="checkbox" checked="checked"  value="Y"/> <label>Root Cause Classification</label></span>
                   					  <span  style="margin-left: 13.1%;">  <input id="chkcmchkbox" name="chkcmchkbox" type="checkbox" checked="checked"  value="Y"/> <label>Counter Measure</label></span>
								 </div> 
                    		    
                    		     <div  class="easyui-paddingbfpx" style="padding-left:0px;">
                        			 <label>BD Type</label>   <span  style="padding-left:154px;"> <label>Spares</label> </span>
                    		     </div> 
								 <div class="easyui-paddingbtpx" style="padding-left:px;"> 
				                      <select id="cboselBdType" class="easyui-combobox" name="cboselBdType" style="width:150px;" >
												<option value="MAJOR"> MAJOR </option>
												<option value="MINOR"> MINOR </option>
												<option value="MEDIUM"> MEDIUM </option>
												<option value="ALL"> ALL </option>
									  </select> 
									<span class="floatR3  " style= "padding-left:50px" > 
								
				                      <select id="cbosparesSelectBox" class="easyui-combobox" name="cbosparesSelectBox" style="width:150px;" >
				                      				<option value="ALL">ALL</option>
													<option value="Y"> YES</option>
													<option value="N"> NO</option>
													<option value="W"> WAITING</option>
									  </select> </span>
								</div>
								
								<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					 <input id="chkircchkbox" name="chkircchkbox" type="checkbox" checked="checked" value="Y"/> <label>Included Root Cause</label>
                  					 <span  style="margin-left: 21%;">  <input id="chkiyychkbox"  name="chkiyychkbox" type="checkbox" checked="checked" value="Y"/> <label>Included Why-Why</label></span>
                  				</div>
                  				<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					 <input id="chktradewise" name="chktradewise" type="checkbox" checked="checked" value="Y"/> <label>Trade Wise</label>
                  					 <span  style="margin-left: 4%;">  <input id="chkjobtypwise"  name="chkjobtypwise" type="checkbox"  /> <label>Job Type Wise</label></span>
                  					 <span  style="margin-left: 3.5%; margin-left: 2%;">  <input id="chkPMCount"  name="chkPMCount" type="checkbox"  /> <label>PM Count</label></span>
                  				</div>
                  				<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					 <input id="chkActwise" name="chkActwise" type="checkbox" checked="checked" value="Y"/> <label>Activity Wise</label>
                  					 <span  style="margin-left: 3.3%;">  <input id="chkMonwise"  name="chkMonwise" type="checkbox"  /> <label>Month Wise</label></span>
                  				</div>
                  				<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					 <input id="chkInternal" name="chkInternal" type="checkbox"  value="Y"/> <label>Internal</label>
                  					 <span  style="margin-left: 10.5%;">  <input id="chkExternal"  name="chkExternal" type="checkbox"  /> <label>External</label></span>
                  				</div>
                  				<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					 <input id="chkgrpByEqp" name="chkgrpByEqp" type="checkbox"  value="Y"/> <label>Group By Equipment</label>
                  					 <span  style="margin-left: 2px; margin-left: 23%\9;">  <input id="chkAllEqpmnt"  name="chkAllEqpmnt" type="checkbox"  /> <label>All Equipment</label></span>
                  					 <span  style="margin-left: 2px;">  <input id="chkgrpByAsm"  name="chkgrpByAsm" type="checkbox"  /> <label>Group By Station</label></span>
                  					 
                  				</div>
                  				    <div  class="easyui-paddingbfpx" >
                        			 <label>Frequency</label>   <span  style="padding-left:145px;"> <label>Job Type</label> </span>
                    		     </div> 
                  				<div class="easyui-paddingbtpx" style="padding-left:px;"> 
				                      <select id="cboFrequency" class="easyui-combobox" name="cboFrequency" style="width:150px;" >
												<option value="ALL"> All </option>
												<option value="MAA"> Month And Above </option>
												<option value="MAB"> Monthly And Below </option>
											
									  </select> 
									<span class="floatR3  " style= "padding-left:50px" > 
								
				                      <select id="cboJobType" class="easyui-combobox" name="cboJobType" style="width:150px;" >
				                      				<option value="">All</option>
													<option value="CBM"> Condition Based Maintanance</option>
													<option value="TBM"> Time Based Maintanance</option>
													<option value="PRM">Preventive Maintanance</option>
													<option value="MBR">Meter Based Maintanance</option>
													<option value="RBM">Run Based Maintanance</option>
													<option value="CAL">Calibration</option>
													<option value="SDM">ShutDown Maintanace</option>
													
									  </select> </span>
								</div>
								
				                   
					                 
                  				</td>
                  				<td style="padding-left:20px;" valign="top">
                  				  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
					            	<span> <input id="chkfrequencychkbox" name="chkfrequencychkbox" type="checkbox"  checked="checked"  value="Y"/> <label>Frequency</label></span>
		                  			  <span style="margin-left: 31%; margin-left: 29%\9; ">  <input id="chkseveritychkbox" name="chkseveritychkbox" type="checkbox" checked="checked" value="Y"/> <label>Severity</label></span>
		                   			    
		                    	</div> 
                  				<div  class="easyui-paddingbfpx">
									<label>Counter Measure</label>  
<!--									<span style="padding-left: 100px;"><label>Activities</label></span>                     -->
                    		    </div> 
                    		    <div class="easyui-paddingbfpx"> 
									 <select id="cbocounter" class="easyui-combobox" name="cbocounter" style="width:150px;" >
									 		<option value="ALL"> ALL</option>
				                      		<option value="JH"> JH</option>
											<option value="PM"> PM</option>
											<option value="CI"> KK</option>
											<option value="ET"> OPL</option>
									</select> 
									
									<span style="padding-left: 40px;">
									 <input id="chkActivity" name="chkActivity" type="checkbox"> <label>ActivityWise</label>
									</span>
							   </div>
                  				<div  class="easyui-paddingbfpx">
                        			<label>ParetoOptions</label> 
                        			<span class="floatR3  " style= "padding-left:113px" ><label>Top</label></span>                        
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
<!--			                    	//for breakdown pareto by manikandan-->
			                    <select id="cboparetooptions" class="easyui-combobox" name="cboparetooptions" style="width:158px;" >
				                      		<option value="SW"> Section Wise</option>
											<option value="CW"> Line Wise</option>
											<option value="EGW"> Equipment Group Wise</option>
											<option value="EW"> Equipment Wise</option>
											<option value="AW"> Assembly Wise</option>
											<option value="PW"> Phenomena Wise</option>
											<option value="CauseW"> Cause Wise</option>
									  </select> 
			                          
			                        
			                        <span class="floatR3  "  style= "padding-left:35px" > 
				                      <input id="txttop" name="top" class="easyui-text"  style="width:152px" value=""  >
								</span>
								                     
			                   </div>
			                  
<!--                    		     -->
								   <div  class="easyui-paddingbfpx" style="padding-left:8px;">
                        			 <label>Options</label>   <span  style="padding-left:145px;"> <label>Activity Type</label> </span>
                    		     </div> 
								 <div class="easyui-paddingbtpx" style="padding-left:px;"> 
				                      <select id="cbooptions" class="easyui-combobox" name="cbooptions" style="width:160px;" >
				                      		<option value="TR"> MTTR</option>
				                      		<option value="BF"> MTBF</option>
<!--											<option value="SW"> Shop Wise</option>-->
<!--											<option value="CW"> Cell Wise</option>-->
<!--											<option value="EGW"> Equipment Group Wise</option>-->
<!--											<option value="EW"> Equipment Wise</option>-->
<!--											<option value="AW"> Assembly Wise</option>-->
<!--											<option value="PW"> Phenomena Wise</option>-->
<!--											<option value="CauseW"> Cause Wise</option>-->
									  </select> 
									<span class="floatR3  " style= "padding-left:35px" > 
								
				                      <select id="cboactivityType" class="easyui-combobox" name="cboactivityType" style="width:150px;" >
													<option value="">ALL</option>
													<option value="O"> Others</option>
													<option value="U">UNSCHEDULED(WITHOUT WO)</option>
													<option value="M">MACHINE UNDER TRAIL</option>
									  </select> 
								</span></div>
<!--								-->
								<div  class="easyui-paddingbfpx">
                        			<label>Engineer</label>                       
                    		    </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbEngineer" name="cmbEngineer" class="easyui-combobox"  style="width:350px" value=""  >                       
			                   </div>
																			
								
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkjhchkbox" name="chkjhchkbox" type="checkbox" checked="checked" value="Y"/> <label>JH</label>
                  					  <span  style="margin-left: 16%; margin-left: 14%\9;">  <input id="chkdesignchkbox" name="chkdesignchkbox" type="checkbox" checked="checked" value="Y"/> <label>Design</label></span>
                   					  <span  style="margin-left: 13%; margin-left: 9%\9;">  <input id="chkpmchkbox" name="chkpmchkbox" type="checkbox" checked="checked" value="Y"/> <label>PM</label></span>
                   					  <span  style="margin-left: 16%; margin-left: 11%\9;">  <input id="chketchkbox"  name="chketchkbox" type="checkbox" checked="checked" value="Y"/> <label>ET</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                        			<label>Why Why</label>                       
                    		    </div> 
							   <div class="easyui-paddingbfpx"> 
			                        <input id="cmbyyy" name="cmbyyy" class="easyui-combobox"   style="width:350px" value=""  >  
<!--			                          onkeypress="return onlyNumbers(event)"                   -->
			                   </div>
			                      <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkemployee" name="chkemployee" type="checkbox"  value="Y"/> <label>Employee</label>
                  					  <span  style="margin-left: 2%;">  <input id="chkcontract" name="chkcontract" type="checkbox"  value="Y"/> <label>Contractor</label></span>
                   					  <span  style="margin-left: 2%;">  <input id="chkspare" name="chkspare" type="checkbox"  value="Y"/> <label>Spare</label></span>
                   					  <span  style="margin-left: 2%;">  <input id="chkservice"  name="chkservice" type="checkbox"  value="Y"/> <label>Service</label></span>
                   					  <span  style="margin-left: 2%;">  <input id="chkutility"  name="chkutility" type="checkbox"  value="Y"/> <label>Utility</label></span>
                   					  <span  style="margin-left: 2%; margin-left: 4%\9;">  <input id="chkother"  name="chkother" type="checkbox"  value="Y"/> <label>others</label></span>
							   		  <span  style="margin-left: 6%;  margin-left: 4%\9; ">  <input id="chktotal"  name="chktotal" type="checkbox" checked="checked" value="Y"/> <label>Total</label></span>
							   		  <span  style="margin-left: 11%; margin-left: 4%\9;" >  <input id="chkallparameter"  name="chkallparameter" type="checkbox"  value="Y"/> <label>All Parameter</label></span>
							   		  
							   </div>
							    <div class="easyui-paddingbfpx"> 
							      		<input id="chkundefinedPP" name="chkundefinedPP" type="checkbox"  value="Y"/> <label>Undefined PP</label>
							      		<span style="margin-left: 34%; margin-left: 3%\9;"><input id="chkrepeatedBD" name="chkrepeatedBD" type="checkbox"  value="Y"/> <label>Repeated Breakdown</label></span>
							    </div>
			                    <div align="center" style="padding-bottom: 0px;">
<!--               		 			<input type="button" id="view"  class="easyui-button"  value="View" />-->
<!--               		 			<input type="button" id="btnClear"  class="easyui-button"  value="Clear"  />-->
                				</div>
                				 <div  class="easyui-paddingbfpx" >
                        			<label>Production group</label> 
                    		     </div> 
                				<div class="easyui-paddingbtpx" style="padding-left:px;"> 
				                      <input id="cmbProductionGroup" name="cmbProductionGroup" class="easyui-combobox"   style="width:150px" value=""  > 
								</div>
								<span  style="margin-left: 0px;">  <input id="chkCompleted" name="chkCompleted" type="checkbox"  value="Y"/> <label>%Completed</label></span>
								<span  style="margin-left: 3%;">  <input id="chkPending" name="chkPending" type="checkbox"  value="Y"/> <label>%Pending</label></span>
			                   </td></tr></table></div>
			                   <br>
			                   <br>
						</div>
						</form>
