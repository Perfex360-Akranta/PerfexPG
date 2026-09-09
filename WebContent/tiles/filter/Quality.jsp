<!--<%@ page language="java" contentType="text/html; charset=UTF-8" %>-->
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->
<script type="text/javascript">
jQuery(document).ready(function(){
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }	
	jQuery('#submitForm').val('frmQuality');
	initialiseForm('frmQuality');	
	jQuery('#chkdvp').click(function(){		
 		if(jQuery('#chkdvp').is(':checked') == true)
		{ 			
 				jQuery('input:checkbox[name=chkdvm]').attr('checked',false);			
 		 				
		}
 		else
 			jQuery('input:checkbox[name=chkdvm]').attr('checked',true);	
 		
	}); 	
	
	jQuery('#chkdvm').click(function(){		
 		if(jQuery('#chkdvm').is(':checked') == true)
		{			
 				jQuery('input:checkbox[name=chkdvp]').attr('checked',false); 					
		}
 		else
 			jQuery('input:checkbox[name=chkdvp]').attr('checked',true); 	
	}); 
	
	jQuery('#chkInternalRej').click(function() {		
		jQuery('input:checkbox[name=chkInternalRej]').attr('checked',true);
		jQuery('input:checkbox[name=chkDockInsp]').attr('checked',false);
		jQuery('#hdnDocType').val('I');				
	});
	jQuery('#chkDockInsp').click(function() {		
		jQuery('input:checkbox[name=chkDockInsp]').attr('checked',true);
		jQuery('input:checkbox[name=chkInternalRej]').attr('checked',false);		
		jQuery('#hdnDocType').val('D');
	});

	if(  !jQuery('#cmbcomplaintno').is(':disabled') )
		fillComboBox("frmQuality","cmbcomplaintno","complaintno.commonFilter" );
	
	if(  !jQuery('#cmbcustID').is(':disabled') )
		fillComboBox("frmQuality","cmbcustID","custID.commonFilter" );
	if(  !jQuery('#cmbproduct').is(':disabled') )
		fillComboBox("frmQuality","cmbproduct","product.commonFilter" );
	if(  !jQuery('#cmbdefactparam').is(':disabled') )
		fillComboBox("frmQuality","cmbdefactparam","defactparam.commonFilter" );
	if(  !jQuery('#cmbrecordedby').is(':disabled') )
		fillComboBox("frmQuality","cmbrecordedby","recordedby.commonFilter" );
	if(  !jQuery('#cmbinspection').is(':disabled') )
		fillComboBox("frmQuality","cmbinspection","inspection.commonFilter" );
	if(  !jQuery('#cmbdefphen').is(':disabled') )
		fillComboBox("frmQuality","cmbdefphen","defphen.cscm?processId="+jQuery('#cmbprocess').combobox('getValue'));
	if(  !jQuery('#cmbprocess').is(':disabled') )
		fillComboBox("frmQuality","cmbprocess","process.commonFilter" );
	if( !jQuery('#cmbshift').is(':disabled') )
		fillComboBox("frmQuality","cmbshift","shift.commonFilter" );
	if( !jQuery('#cmbInspectBy').is(':disabled') )
		fillComboBox("frmQuality","cmbInspectBy","employee.commonFilter" );
	
	disableField("frmQuality", "txtProductDesc");

});


	function getRelatedFilterValues()
	{		
		var filterStr="";
		
		var cmbprocess = jQuery("#cmbprocess").combobox("getValue");
		filterStr += "&cmbprocess="+cmbprocess;
		
		var cmbcomplaintno = jQuery("#cmbcomplaintno").combobox("getValue");
		filterStr += "&cmbcomplaintno="+cmbcomplaintno;
		
		var cmbcustID = jQuery("#cmbcustID").combobox("getValue");
		filterStr += "&cmbcustID="+cmbcustID;
		
		var cmbproduct = jQuery("#cmbproduct").combobox("getValue");
		filterStr += "&cmbproduct="+cmbproduct;
		
		var cmbdefactparam = jQuery("#cmbdefactparam").combobox("getValue");
		filterStr += "&cmbdefactparam="+cmbdefactparam;
		
		var cmbdefphen = jQuery("#cmbdefphen").combobox("getValue");
		filterStr += "&cmbdefphen="+cmbdefphen;	
		
		var cmbrecordedby = jQuery("#cmbrecordedby").combobox("getValue");
		filterStr += "&cmbrecordedby="+cmbrecordedby;
		
		var cmbinspection = jQuery("#cmbinspection").combobox("getValue");
		filterStr += "&cmbinspection="+cmbinspection;

		var cmbinspectedby = jQuery("#cmbInspectBy").combobox("getValue");
		filterStr += "&cmbinspectedby="+cmbinspectedby;

		var hdnDocType = jQuery('#hdnDocType').val();
		filterStr += "&hdnDocType="+hdnDocType;

		
		var txttop = jQuery("#txttop").val();
		if(  !jQuery('#txttop').is(':disabled') )
			filterStr += "&txttop="+txttop;
		
		var cbocomplainttype = getFieldValue("cbocomplainttype");			
		filterStr += "&cbocomplainttype="+cbocomplainttype;
		
		var chkrejectionchkbox = getChkBoxVal("chkrejectionchkbox");		
		filterStr += "&chkrejectionchkbox="+ (chkrejectionchkbox == "1" || chkrejectionchkbox == 1 ? 'Y':'N');
		
		
		/*var chkrejectionchkbox = jQuery("#chkrejectionchkbox").attr('checked');
		//filterStr += "&chkrejectionchkbox="+chkrejectionchkbox;
		filterStr += "&chkrejectionchkbox="+getChkBoxVal('chkrejectionchkbox');	*/
		
		var cborptType = getFieldValue("cborptType");			
		filterStr += "&cborptType="+cborptType;
		
		var cboparetooptions= jQuery("#cboparetooptions").val();
		filterStr += "&cboparetooptions="+cboparetooptions;

		var cboProdDesc = jQuery('#cboProdDesc').val();
		filterStr += "&cboProdDesc="+cboProdDesc;

		var cmbshift = jQuery('#cmbshift').combobox('getValue');
		filterStr += "&cmbshift="+cmbshift;

		var cboQtyStatus = jQuery('#cboQtyStatus').val();
		filterStr += "&cboQtyStatus="+cboQtyStatus;
		
		
		var chkojtbox = getChkBoxVal("chkojtbox");
		var chksopbox = getChkBoxVal("chksopbox");
		var chkkkbox = getChkBoxVal("chkkkbox");
		var chketbox = getChkBoxVal("chketbox");
		var chk4mbox = getChkBoxVal("chk4mbox");
		
		if(  !jQuery('#chkojtbox').is(':disabled') )
			filterStr += "&chkojtbox="+ (chkojtbox == "1" || chkojtbox == 1 ? 'Y':'N');			
		if(  !jQuery('#chksopbox').is(':disabled') )
			filterStr += "&chksopbox="+ (chksopbox == "1" || chksopbox == 1 ? 'Y':'N');					
		if(  !jQuery('#chkkkbox').is(':disabled') )
			filterStr += "&chkkkbox="+ (chkkkbox == "1" || chkkkbox == 1 ? 'Y':'N');			
		if(  !jQuery('#chketbox').is(':disabled') )
			filterStr += "&chketbox="+ (chketbox == "1" || chketbox == 1 ? 'Y':'N');	
		if(  !jQuery('#chk4mbox').is(':disabled') )
			filterStr += "&chk4mbox="+ (chk4mbox == "1" || chk4mbox == 1 ? 'Y':'N');
		if(  !jQuery('#chkinstance').is(':disabled') )
			filterStr += "&chkinstance="+getChkBoxVal('chkinstance');	
		if(  !jQuery('#chkquantity').is(':disabled') )
			filterStr += "&chkquantity="+getChkBoxVal('chkquantity');	
		
		
		
		/*var chkjhchkbox = jQuery("#jhchkbox").attr('checked');
		filterStr += "&chkjhchkbox="+chkjhchkbox;
		
		var chkpmchkbox = jQuery("#pmchkbox").attr('checked');
		filterStr += "&chkpmchkbox="+chkpmchkbox;
		
		var chkkkchkbox = jQuery("#kkchkbox").attr('checked');
		filterStr += "&chkkkchkbox="+chkkkchkbox;
		
		var chketchkbox = jQuery("#etchkbox").attr('checked');
		filterStr += "&chketchkbox="+chketchkbox;

		var chk4mchkbox = jQuery("#4mchkbox").attr('checked');
		filterStr += "&chk4mchkbox="+chk4mchkbox;

		var chkimpdonechkbox = jQuery("#impdonechkbox").attr('checked');
		filterStr += "&chkimpdonechkbox="+chkimpdonechkbox;

		
		
		 
*/	
		if(  !jQuery('#chkmacwisechkbox').is(':disabled') )
			filterStr += "&chkmacwisechkbox="+getChkBoxVal('chkmacwisechkbox');
		if(  !jQuery('#chkdvp').is(':disabled') )	
			filterStr += "&chkdvp="+getChkBoxVal('chkdvp');	
		if(  !jQuery('#chkdvm').is(':disabled') )
			filterStr += "&chkdvm="+getChkBoxVal('chkdvm');			
		if(  !jQuery('#chkdpc').is(':disabled') )
			filterStr += "&chkdpc="+getChkBoxVal('chkdpc');	 		
		if(  !jQuery('#chkimpdonebox').is(':disabled') )
			filterStr += "&chkimpdonebox="+getChkBoxVal('chkimpdonebox');	
		if(  !jQuery('#chkrejectionchkbox').is(':disabled') )
			filterStr += "&chkrejectionchkBox="+getChkBoxVal('chkrejectionchkbox');
		if(  !jQuery('#chkcummulative').is(':disabled') )
			filterStr += "&chkcummulative="+getChkBoxVal('chkcummulative');

		return filterStr;
	}	
	function getChkBoxVal(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}

	jQuery('#chkmacwisechkbox').click( function()
	{
		 if(jQuery('#chkmacwisechkbox').is(':checked')==true)	
				enableDisableFilters("DefectMatrixMchWise_input.dMatrix");
		 else
			 enableDisableFilters("DefectMatrix_input.dMatrix");
	
	});
	
	jQuery("#cboProdDesc").change(function(){	
		jQuery("#txtProductDesc").val('');
		var val = jQuery("#cboProdDesc").val();		
		if(val!="N")
			enableFields("txtProductDesc");
		else if(val=="N")
			disableField("frmQuality", "txtProductDesc");		
	});

	function frmQualitycmbprocess_onSelect(record)
	{
		jQuery('#cmbdefphen').combobox("clear");
		reloadCombo("frmQuality","cmbdefphen","defphen.cscm?processId="+record.id);	
	}
	
	jQuery("#cboQtyStatus").change(function(){
		var val = jQuery("#cboQtyStatus").val();
		if(val == "P")
		{
			readOnlyFields('chkojtbox');
			readOnlyFields('chksopbox');
			readOnlyFields('chkkkbox');
			readOnlyFields('chketbox');
			readOnlyFields('chk4mbox');
			jQuery("#chkojtbox").attr('checked',false);
			jQuery("#chksopbox").attr('checked',false);
			jQuery("#chkkkbox").attr('checked',false);
			jQuery("#chketbox").attr('checked',false);
			jQuery("#chk4mbox").attr('checked',false);
			
		}
		else{
			enableFields('chkojtbox');
			enableFields('chksopbox');
			enableFields('chkkkbox');
			enableFields('chketbox');
			enableFields('chk4mbox');
			jQuery("#chkojtbox").attr('checked',true);
			jQuery("#chksopbox").attr('checked',true);
			jQuery("#chkkkbox").attr('checked',true);
			jQuery("#chketbox").attr('checked',true);
			jQuery("#chk4mbox").attr('checked',true);
		}
			
	});
	</script>
		<form id="frmQuality" name="frmQuality">
							<!--   Quality Tab	-->
						<div title="Quality" style="padding:10px;">
						
						 	   <div class="sub-header">Regular</div>
						 	   	<div align="center">
								<table><tr>
								
								<td>
							   <div  class="easyui-paddingbfpx">
                        			<label>Process</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbprocess" name="cmbprocess" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Complaint No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcomplaintno" name="cmbcomplaintno" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Customer Name</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbcustID" name="cmbcustID" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Product</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbproduct" name="cmbproduct" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   </td>
			                   <td style="padding-left: 20px;">
			                    <div  class="easyui-paddingbfpx">
                        			<label>Defect Parameter</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdefactparam" name="cmbdefactparam" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Recorded By</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbrecordedby" name="cmbrecordedby" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Inspection</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbinspection" name="cmbinspection" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Phenomena</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbdefphen" name="cmbdefphen" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   </div>
			                   </td>
			                   
			                   <td valign="top" style="padding-left: 20px;">
			                   		<div  class="easyui-paddingbfpx">
                        			<label>Shift</label>                       
                    		  		 </div> 
			                    	<div class="easyui-paddingbfpx"> 
			                        <input id="cmbshift" name="cmbshift" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   		</div>
			                   		
			                   		<div  class="easyui-paddingbfpx">
                        			<label>Inspected By</label>                       
                    		  		 </div> 
			                    	<div class="easyui-paddingbfpx"> 
			                        <input id="cmbInspectBy" name="cmbInspectBy" class="easyui-combobox"  style="width:228px" value=""  >                       
			                   		</div>
			                   		
			                   		<div  class="easyui-paddingbfpx">
                        			<label>Product Description</label>                       
                    		  		 </div> 
			                    	<div class="easyui-paddingbfpx"> 
			                         <select id="cboProdDesc" class="easyui-combobox" name="cboProdDesc" style="width:100px;" >
													<option value="N"></option>
													<option value="B">Begins</option>
													<option value="E">Ends</option>
													<option value="C">Contains</option>													
									 </select>   
									 <span>
									 	<input id="txtProductDesc" name="txtProductDesc" class="easyui-text"  style="width:125px" value=""  >
									 </span>                   
			                   		</div>
			                   		
			                   		
			                   		
			                   		<div  class="easyui-paddingbfpx">
                        			<label>Status</label>                       
                    		  		 </div> 
			                    	<div class="easyui-paddingbfpx"> 
			                         <select id="cboQtyStatus" class="easyui-combobox" name="cboQtyStatus" style="width:100px;" >													
													<option value="">ALL</option>
													<option value="P">Pending</option>
													<option value="C">Completed</option>																							
									 </select>                          
			                   		</div>
			                   		
			                   </td>
			                   
			                   </tr></table>
			                   
			                   
			                   
			                   </div>
			                    <div class="sub-header">Advanced Filter Criteria</div>
			                    	<div align="center">
								<table><tr><td style="padding-left:25px\9;">
			                    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:344px;width:332px\9;padding-left:5px;padding-left:1px\9;">
                  					  <input id="chkdvp" name="chkdvp" type="checkbox"  value="Y" checked="checked"/> <label>Defect Vs Process</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkdvm" name="chkdvm" type="checkbox"  value="Y"/><label>Defect Vs Machine</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkinstance" name="chkinstance" type="checkbox"  value="Y" checked="checked"/><label>Instance</label></span><br/><br/>
                  					  <span> <input id="chkquantity" name="chkquantity" type="checkbox"  value="Y" checked="checked"/> <label>Quantity</label></span>
                  					  <span  style="margin-left: 2px;"> <input id="chkdpc" name="chkdpc" type="checkbox"  value="Y"  /> <label>Display phenomena in Columns </label></span>
                  			   </div>
                  			   
                  			    <div  class="easyui-paddingbfpx">
                        			<label>View</label>                       
                    		   </div> 
                  			    <div  class="easyui-paddingbtpx" style="padding-left:1px;">
                  			       <input id="chkmacwisechkbox" type="checkbox"/> <label>Machine Wise</label>
                  			   
                  			       <input id="chkcummulative" type="checkbox"/> <label>Cummulative</label>
                  			    </div>
                  			    <div  class="easyui-paddingbfpx">
                        			<label>Type</label>                       
                    		   </div> 
                    		    <div  class="easyui-paddingbtpx" style="padding-left:1px;">
                    		    	<input id="chkDockInsp" name="chkDockInsp" type="checkbox"/> <label>Dock Inspection</label>
                  			   	 	 <input id="chkInternalRej" name="chkInternalRej" type="checkbox"/> <label>Internal Rejection</label>
                  			   	 	 <input type="hidden" id="hdnDocType" name="hdnDocType"/>
                    		    </div>
                    		   
                  			    </td>
                  			    <td style="padding-left:13px;padding-left:20px\9;">
                  			     <div  class="easyui-paddingbfpx">
                  					 <label>Graph Options</label>
                   					 <span  style="margin-left: 105px;"><label>Report Type</label></span>
                    			  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="cboparetooptions" class="easyui-combobox" name="cboparetooptions" style="width:160px;" >
													<option value="PW">Process Wise </option>
													<option value="MW"> Equipment Wise </option>
													<option value="PHW"> Phenomena Wise </option>
													<option value="CW"> Cause Wise </option>
									 </select> 
			                         <span  style="margin-left: 25px;"> 
				                       <select id="cborptType" class="easyui-combobox" name="cborptType" style="width:160px;" >
													<option value="SHIFT"> Shift Wise </option>
													<option value="WEEK"> Week Wise </option>
													<option value="MONTH"> Month Wise </option>
													<option value="MACHINE"> Machine Wise </option>
													<option value="DEFECT"> Defect Wise </option>
													<option value="REJQTYDETL"> Rejected Quantity Details </option>
													<option value="REJQTY"> Rejected Quantity </option>
									   </select> 
			                        </span> 
			                      </div>
			                      
			                       <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:355px;width:415px\9;padding-left:5px;">
                  					  <input id="chkojtbox"  name="chkojtbox" type="checkbox"  value="Y"/> <label>OJT</label>
                  					  <span  style="margin-left: 2px;">  <input id="chksopbox"  name="chksopbox" type="checkbox"  checked="checked" value="Y"/> <label>SOP</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkkkbox"  name="chkkkbox"type="checkbox"   checked="checked" value="Y"/> <label>KZN</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chketbox"  name="chketbox"type="checkbox"  checked="checked"  value="Y"/> <label>OPL</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chk4mbox"  name="chk4mbox" type="checkbox"  checked="checked" value="Y"/> <label>4M </label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkimpdonebox"  name="chkimpdonebox" type="checkbox"  value="Y"/> <label>Improvements Done </label></span>
                  			      </div>
                  			      
                  			      
                  			      <div  class="easyui-paddingbfpx">
                  					 <label>Complaint Type</label>
								  </div> 
                    		      <div class="easyui-paddingbtpx"> 
			                         <select id="cbocomplainttype" class="easyui-combobox" name="cbocomplainttype" style="width:160px;" required="true">
													<option > </option>
													<option value="A">A </option>
													<option value="B">B </option>
													<option value="C">C </option>
									  </select> 
			                         <span  style="margin-left: 25px;padding-left:5px;"> 
				                         <input id="chkrejectionchkbox" type="checkbox"/> <label>Rejection %</label>
			                        </span> 
			                       <span>
			                        
                  					<label>Top</label>
								 	 
									 	<input id="txttop" name="txttop" class="easyui-text"  style="width:20px" value="10"  >
									</span>  
			                      </div>
			                      
                  			     		</td> </tr></table></div>
						</div>
			</form>
