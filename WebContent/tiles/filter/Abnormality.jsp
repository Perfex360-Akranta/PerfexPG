
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<style>
	.removeblanks
	{
	
	padding-top:15px;
	padding-top:20px\9;
	
	}
	
	
	</style>
	<script type="text/javascript">

	jQuery(document).ready(function(){
		initialiseForm('frmAbnormalityRelated');
	 	var abnType = jQuery("#hdnAbnType").val();	 	
	 	if(screen.width <= 1024){
			 
			 jQuery('#divAdvanced').css('padding-left','0');
			 jQuery('#Filter').css('height','418px');
		 }	
	 	fillComboBox("frmAbnormalityRelated","cmbPrgdProdGroupId","productiongroup.commonFilter");
	 	fillComboBox("frmAbnormalityRelated","cmbAbnmJhStep","jhstep.commonFilter");
		if(  !jQuery('#cmbFilterAbnmType').is(':disabled') ) 
			fillComboBox("frmAbnormalityRelated","cmbFilterAbnmType","Combo_Type.abnForm?abnType="+abnType);
		if(  !jQuery('#cmbFilterCategoryid').is(':disabled') ) 
			fillComboBox("frmAbnormalityRelated","cmbFilterCategoryid","Combo_Category.abnForm");
		if(  !jQuery('#cmbFilterImpactid').is(':disabled') ) 
			fillComboBox("frmAbnormalityRelated","cmbFilterImpactid","Combo_Impact.abnForm");
		if(  !jQuery('#cmbPrgdProdGroupId').is(':disabled') ) 
			//fillComboBox("frmAbnormalityRelated","cmbPrgdProdGroupId","productiongroup.commonFilter");
		if(  !jQuery('#cmbAbnmJhStep').is(':disabled') ) 
			//fillComboBox("frmAbnormalityRelated","cmbAbnmJhStep","jhstep.commonFilter");
		if(  !jQuery('#cmbdetectedBy').is(':disabled') ) 
			fillComboBox("frmAbnormalityRelated","cmbdetectedBy","employee.commonFilter");
		if(  !jQuery('#cmbResponsibility').is(':disabled') ) 
			fillComboBox("frmAbnormalityRelated","cmbResponsibility","employee.commonFilter");
		fillComboBox("frmAbnormalityRelated","cmbAbnmTagclass","Combo_TagClass.abnForm",false);
	//selectAllCheckBox();  
	jQuery("#divSelect").hide();
	disableWhileSelChkbox('chkdectbychkbox');
	disableWhileSelChkbox('chkdectdtchkbox');
	disableWhileSelChkbox('chkcauschkbox');
	disableWhileSelChkbox('chkabncatchkbox');
	disableWhileSelChkbox('chkabnimpchkbox');
	disableWhileSelChkbox('chkallchkbox');
	disableWhileSelChkbox('chkAbnType');
	disableWhileSelChkbox('chkMould');
	disableWhileSelChkbox('chkWhyWhyHappen');
	disableWhileSelChkbox('chkTrade');
	disableWhileSelChkbox('chkTagClass');
	disableWhileSelChkbox('chkAbnormality');
	disableWhileSelChkbox('chkStatus');
	disableWhileSelChkbox('chkHTAType');
	
	
		if(abnType != 'SHE')
		{
			setTimeout(function() {jQuery('#cboSafetypatrol').attr('disabled','disabled');},1250);	
		}
	
	});
	

	function selectAllCheckBox()
	{
		if(  !jQuery('#chkdectbychkbox').is(':disabled') )
			jQuery('#chkdectbychkbox').attr('checked',true);
		if(  !jQuery('#chkdectdtchkbox').is(':disabled') )
			jQuery('#chkdectdtchkbox').attr('checked',true);
		if(  !jQuery('#chkcauschkbox').is(':disabled') )
			jQuery('#chkcauschkbox').attr('checked',true);
		if(  !jQuery('#chkabncatchkbox').is(':disabled') )
			jQuery('#chkabncatchkbox').attr('checked',true);
		if(  !jQuery('#chkabnimpchkbox').is(':disabled') )
			jQuery('#chkabnimpchkbox').attr('checked',true);
		if(  !jQuery('#chkAbnType').is(':disabled') )
			jQuery('#chkAbnType').attr('checked',true);
		if(  !jQuery('#chkMould').is(':disabled') )
			jQuery('#chkMould').attr('checked',true);
		if(  !jQuery('#chkStatus').is(':disabled') )
			jQuery('#chkStatus').attr('checked',true);
		if(  !jQuery('#chkAbnormality').is(':disabled') )
			jQuery('#chkAbnormality').attr('checked',true);
		if(  !jQuery('#chkTrade').is(':disabled') )
			jQuery('#chkTrade').attr('checked',true);
		if(  !jQuery('#chkWhyWhyHappen').is(':disabled') )
			jQuery('#chkWhyWhyHappen').attr('checked',true);
		if(  !jQuery('#chkTagClass').is(':disabled') )
			jQuery('#chkTagClass').attr('checked',true);
		if(  !jQuery('#chkallchkbox').is(':disabled') )
			jQuery('#chkallchkbox').attr('checked',true);
		if(  !jQuery('#chkHTAType').is(':disabled') )
			jQuery('#chkHTAType').attr('checked',true);
	}
	
	function unselectAllCheckBox()
	{
		jQuery('#chkdectbychkbox').attr('checked',false);
		jQuery('#chkdectdtchkbox').attr('checked',false);
		jQuery('#chkcauschkbox').attr('checked',false);
		jQuery('#chkabncatchkbox').attr('checked',false);
		jQuery('#chkabnimpchkbox').attr('checked',false);
		jQuery('#chkAbnType').attr('checked',false);
		jQuery('#chkMould').attr('checked',false);
		jQuery('#chkAbnormality').attr('checked',false);
		jQuery('#chkTrade').attr('checked',false);
		jQuery('#chkWhyWhyHappen').attr('checked',false);
		jQuery('#chkTagClass').attr('checked',false);
		jQuery('#chkallchkbox').attr('checked',false);
		jQuery('#chkStatus').attr('checked',false);
		jQuery('#chkHTAType').attr('checked',false);
	}

	jQuery("#chkallchkbox").click(function(){
		if(jQuery("#chkallchkbox").is(':checked') == true)
		{
			selectAllCheckBox();
		}
		else if(jQuery("#chkallchkbox").is(':checked') == false)
		{
			unselectAllCheckBox();
		}
	});
	
	function checkClick(selChb)
	{
		jQuery('#'+selChb).click(function() {
			 if(jQuery('#'+selChb).is(':checked') == false)
				jQuery('#chkallchkbox').attr('checked',false);				
			
		 });
	}
	
	function disableWhileSelChkbox(selChb)
	{
		 jQuery('#'+selChb).click(function() {
			 if(jQuery('#'+selChb).is(':checked') == true)
				jQuery('#'+selChb).attr('checked',true);				
			 else
				jQuery('#'+selChb).attr('checked',false);
		 });
	} 
	function getRelatedFilterValues()
	{
		var filterStr="";
		
		var cmbabntype = jQuery("#cmbFilterAbnmType").combobox("getValue");
		filterStr += "&cmbabntype="+cmbabntype;

		var cmbabncategory = jQuery("#cmbFilterCategoryid").combobox("getValue");
		filterStr += "&cmbabncategory="+cmbabncategory;
		
		var cmbabnimpact = jQuery("#cmbFilterImpactid").combobox("getValue");
		filterStr += "&cmbabnimpact="+cmbabnimpact;

		var cmbabnjhstep = jQuery("#cmbAbnmJhStep").combobox("getValue");
		filterStr += "&cmbabnjhstep="+cmbabnjhstep;
	
		var abnstatus = jQuery("#cboabnstatus").val();
		filterStr += "&cboabnstatus="+abnstatus;

		var cmbAbnmTagclass = jQuery('#cmbAbnmTagclass').combobox("getValue");
		filterStr += "&cmbAbnmTagclassid="+cmbAbnmTagclass;

		var cboAbndImprovementteam = jQuery('#cboAbndImprovementteam').val();
		filterStr += "&cboAbndImprovementteam="+cboAbndImprovementteam;

		var cboSafetypatrol = jQuery('#cboSafetypatrol').val();
		filterStr += "&cboSafetypatrol="+cboSafetypatrol;

		/*var chkdectbychkbox = getAbnChkBoxVal("chkdectbychkbox");//jQuery("#chkdectbychkbox").attr('checked');
		filterStr += "&chkdectbychkbox="+chkdectbychkbox;

		var chkdectdtchkbox = getAbnChkBoxVal("chkdectdtchkbox");//jQuery("#chkdectdtchkbox").attr('checked');
		filterStr += "&chkdectdtchkbox="+chkdectdtchkbox;
	
		var chkcauschkbox = getAbnChkBoxVal("chkcauschkbox");//jQuery("#chkcauschkbox").attr('checked');
		filterStr += "&chkcauschkbox="+chkcauschkbox;
		
		var chkAbnType = getAbnChkBoxVal("chkAbnType");
		filterStr += "&chkAbnType="+chkAbnType;
		
		var chkMould = getAbnChkBoxVal("chkMould");
		filterStr += "&chkMould="+chkMould;
		
		var chkTrade = getAbnChkBoxVal("chkTrade");
		filterStr += "&chkTrade="+chkTrade;
		
		var chkStatus = getAbnChkBoxVal("chkStatus");
		filterStr += "&chkStatus="+chkStatus;
		
		var chkAbnormality = getAbnChkBoxVal("chkAbnormality");
		filterStr += "&chkAbnormality="+chkAbnormality;
		
		var chkTagClass = getAbnChkBoxVal("chkTagClass");
		filterStr += "&chkTagClass="+chkTagClass;
		
		var chkWhyWhyHappen = getAbnChkBoxVal("chkWhyWhyHappen");
		filterStr += "&chkWhyWhyHappen="+chkWhyWhyHappen;
		
		var chkHTAType = getAbnChkBoxVal("chkHTAType");
		filterStr += "&chkHTAType="+chkHTAType;

		var chkabncatchkbox = getAbnChkBoxVal("chkabncatchkbox");//jQuery("#chkabncatchkbox").attr('checked');
		filterStr += "&chkabncatchkbox="+chkabncatchkbox;

		var chkabnimpchkbox = getAbnChkBoxVal("chkabnimpchkbox");//jQuery("#chkabnimpchkbox").attr('checked');
		filterStr += "&chkabnimpchkbox="+chkabnimpchkbox;
	
		var chkallchkbox = getAbnChkBoxVal("chkallchkbox");//jQuery("#chkallchkbox").attr('checked');
		filterStr += "&chkallchkbox="+chkallchkbox;
		
		var chkAssm = getAbnChkBoxVal("chkAssm");		
		filterStr += "&chkAssm="+ (chkAssm == "1" || chkAssm == 1 ? 'Y':'N');

		var chkCircle = getAbnChkBoxVal("chkCircle");		
		filterStr += "&chkCircle="+ (chkCircle == "1" || chkCircle == 1 ? 'Y':'N');
		*/
		
		var cmbLoss = jQuery("#cboLoss").val();
		filterStr += "&cboLoss="+cmbLoss;	
		
		/*var cmbdetectedBy = jQuery('#cmbdetectedBy').combobox("getValue");
		filterStr += "&cmbdetectedBy="+cmbdetectedBy;*/
		
		/*var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');		
		filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');		
		*/
		var chkRepeatedAbn = getChkBoxVal('chkRepeatedAbn');
		filterStr += "&chkRepeatedAbn="+chkRepeatedAbn;
		
		var chkAbnViewIdent = getAbnChkBoxValue('chkAbnViewIdent');
		filterStr += "&chkAbnViewIdent="+chkAbnViewIdent;
		
		var chkAbnViewComp = getAbnChkBoxValue('chkAbnViewComp');
		filterStr += "&chkAbnViewComp="+chkAbnViewComp;
		
		var cmbResponsibility = jQuery('#cmbResponsibility').combobox("getValue");
		filterStr += "&cmbResponsibility="+cmbResponsibility;

		/*var chkTradewise = getChkBoxVal('chkTradewise');
		filterStr += "&chkTradewise="+(chkTradewise=="1" || chkTradewise==1? 'Y':'N');*/
		//alert(filterStr);
		
		return filterStr;
		
		
	}
	function getAbnChkBoxVal(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}
	
	function getAbnChkBoxValue(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 'Y';
		else
			return 'N';
	}
	jQuery("#chkAbnViewIdent").click(function(){
		if(jQuery("#chkAbnViewIdent").is(':checked') == true)
		{
			jQuery("#chkAbnViewComp").attr("checked",false);
		}
		else 
		{
			jQuery("#chkAbnViewComp").attr("checked",true);
		}
	});
	
	jQuery("#chkAbnViewComp").click(function(){
		if(jQuery("#chkAbnViewComp").is(':checked') == true)
		{
			jQuery("#chkAbnViewIdent").attr("checked",false);
		}
		else 
		{
			jQuery("#chkAbnViewIdent").attr("checked",true);
		}
	});
	</script>
<form id="frmAbnormalityRelated" id= "frmAbnormalityRelated">	
		<!--   Abnormality Report Tab	-->
		<div title="Abnormality Report" style="padding:10px;">
						
				 <div class="sub-header">Regular</div>
				 <div align="center">
				 <table><tr><td valign="top">
					   <div>
                      		<label>ABN Type</label>                       
                  	  </div> 
	                   <div class="easyui-paddingbfpx"> 
	                        <input id="cmbFilterAbnmType" name="cmbFilterAbnmType" class="easyui-combobox"  style="width:250px" value=""  >                       
	                   </div>
	                   
	                   <div>
                        	<label>JH Step</label>                       
                       </div> 
			           <div class="easyui-paddingbfpx"> 
			                <input id="cmbAbnmJhStep" name="cmbAbnmJhStep" class="easyui-combobox"  style="width:250px" value=""  >                       
			           </div>
			           
			           <div>
	                      	<label>Improvement Type</label>                       
	                  	</div> 
		                   		 <div class="easyui-paddingbfpx">
								<select id="cboAbndImprovementteam" name="cboAbndImprovementteam" class="easyui-combobox"  style="width:250px;font-size: 12px"  >  
		                        	<option value=""> </option>
		                        	<option title="C" value="C">CIT</option>
		                        	<option title="O" value="O">OIT</option>
		                        	<option title="M" value="M">MP</option> 
		                        	<option title="I" value="I">MI</option>
    							</select>
							</div>
							
							 <div>
	                      			<label>Responsibility By</label>                       
	                  		</div> 
		                    <div class="easyui-paddingbfpx">
		                        <input id="cmbResponsibility" name="cmbResponsibility" class="easyui-combobox"  style="width:250px" value=""  >                       
		                    </div>
			         </td>
			         <td valign="top" style=" padding-left:20px;">
							<div>
                        		<label>ABN Category</label>                       
                    		</div> 
			                <div class="easyui-paddingbfpx"> 
			                     <input id="cmbFilterCategoryid" name="cmbFilterCategoryid" class="easyui-combobox"  style="width:250px" value=""  >                       
			                </div>
			                
			                <div>
                        		  <label>Production Group</label>                       
                    		</div> 
			                <div class="easyui-paddingbfpx"> 
			                      <input id="cmbPrgdProdGroupId" name="cmbPrgdProdGroupId" class="easyui-combobox"  style="width:250px" value=""  >                       
			               </div>
			               	<c:if test="${(sessionScope.showGen )}">
		
			               <div>
                        		  <label>Affect Loss</label>                       
                    		</div>
			               <div class="easyui-paddingbfpx"> 
<!--			                    <span  style="margin-left:  1%;">  <input id="chkAssm" type="checkbox"  value="Y"/> <label>Assembly</label></span>                       -->
											<span  id="spnLossFilter">
													<select id="cboLoss"  name="cboLoss" style="height: 22px;width:250px;">
														<option value=""> </option>
														<option value="U"> UNPLANNED MAINTENANCE</option>
														<option value="J"> JH TAG REMOVAL</option>							
														<option value="M"> M AND A</option>		
													</select>
												</span>
			               </div>
			               </c:if>
			                <div>
                        		  <label>Safety Patrol</label>                       
                    		</div>
			               <div class="easyui-paddingbfpx">
				                <select id="cboSafetypatrol"  name="cboSafetypatrol"  tabindex="1" style="height: 22px;width:105px;">
				                	<option value="-"> </option>
									<option value="SHE">SHE</option>
				   		 			<option value="JH">JH</option>							
								</select> 
								<span style="padding-left:15px;"> <input type="checkbox" name="chkCircle" id="chkCircle" /> <label>Based On Circle</label></span>
								
							</div>
			          </td>
			          <td valign="top" style=" padding-left:20px;">
							  <div>
	                      			<label>ABN Impact</label>                       
	                  		   </div> 
		                    <div class="easyui-paddingbfpx"> 
		                        <input id="cmbFilterImpactid" name="cmbFilterImpactid" class="easyui-combobox"  style="width:250px" value=""  >                       
		                   </div>
		                   <div>
	                      			<label>ABN Class</label>                       
	                  		   </div> 
		                    <div class="easyui-paddingbfpx">
		                        <input id="cmbAbnmTagclass" name="cmbAbnmTagclass" class="easyui-combobox"  style="width:250px" value=""  >                       
		                    </div>
		                    
		                     <div>
	                      			<label>Detected By</label>                       
	                  		   </div> 
		                    <div class="easyui-paddingbfpx">
		                        <input id="cmbdetectedBy" name="cmbdetectedBy" class="easyui-combobox"  style="width:250px" value=""  >                       
		                    </div>
		                    
		                   <div class="removeblanks">
<!-- 		                        <span style="padding-left:10px;"><label>Remove Blanks</label><input id="chkRemoveBlank" name="chkRemoveBlank" type="checkbox" checked="checked"/></span>                        -->
									<span style="padding-left:7px;"><label>Repeated Abnormality  </label><input id="chkRepeatedAbn" name="chkRepeatedAbn" type="checkbox" value="Y"/></span>
									<span style="padding-left:7px;"><label>Trade Wise  </label><input id="chkTradewise" name="chkTradewise" type="checkbox" /></span>
		                    </div>
					 </td></tr></table></div>
			         <div class="sub-header">Advanced Filter Criteria</div>
			         <div id="divAdvanced" style="padding-left:88px;">
						<table><tr><td>
			                <div>
                  				<label>Status</label>
							</div> 
                    		<div class="easyui-paddingbtpx"> 
		                         <select id="cboabnstatus" class="easyui-combobox" name="cboabnstatus" style="width:250px;" required="true">
		                         			<option value=""> All</option>
											<option value="P"> Status-Pending</option>
											<option value="C"> Status-Completed</option>
											<option value="TP"> Tag Exceeds Target Date -Pending</option>
											<option value="TC"> Tag Exceeds Target Date -Completed</option>
											<option value="TT"> Tag Completed on Time</option>												
								 </select> 
							  </div>
							  
							  
			                  </td>
			                  <td>
			                  		<input type="checkbox" name="chkAbnViewIdent" id="chkAbnViewIdent" checked="checked" value="I" />
			                  		<label>Identified</label>
			                  		<input type="checkbox" name="chkAbnViewComp" id="chkAbnViewComp" value="C" />
			                  		<label>Completed</label>
			                  		<input type="checkbox" name="chkAbnViewAfeem" id="chkAbnViewAfeem" value="A" />
			                  		<label>Afeem</label>
			                  </td>
			                  
			                  <td colspan="2" valign="top" style=" padding-left:20px;">
			   			     <div id="divSelect" class="easyui-paddingbtpx easyui-chkbxgroup" style="width:611px; width:620px\9;padding-left:5px;">
                 					  <input id="chkdectbychkbox" type="checkbox" onclick=checkClick(id); value="1"/> <label>Detected By</label>
                  					  <span  style="margin-left: 1%;">  <input id="chkdectdtchkbox" type="checkbox" onclick=checkClick(id); value="1"/> <label>Detected Date</label></span>
                  					  <span  style="margin-left:  1%;">  <input id="chkcauschkbox" type="checkbox" onclick=checkClick(id); value="1"/> <label>Cause</label></span>
                  					  <span  style="margin-left:  1%;">  <input id="chkAbnType" type="checkbox" onclick=checkClick(id); value="1"/> <label>AbnType</label></span>                  					  
                  					  <span style="margin-left:  4%; margin-left:  2%\9;">  <input id="chkabncatchkbox" type="checkbox" onclick=checkClick(id); value="1"/> <label>Abnormality Category</label></span>
                  					  <span  style="margin-left:  4.6%;  margin-left:  2%\9;">  <input id="chkMould" type="checkbox" onclick=checkClick(id); value="1"/> <label>Mould</label></span><br>
                  					  
                  					  <span  style="margin-left: 0px;">  <input id="chkTrade" type="checkbox" onclick=checkClick(id); value="1"/> <label>Trade</label></span>
                  					  <span  style="margin-left: 6.4%; margin-left:  6.3%\9;">  <input id="chkTagClass" type="checkbox" onclick=checkClick(id); value="1"/> <label>TagClass</label></span>
<!--                  					  <span  style="margin-left:  1%;">  <input id="chkAbnormality" type="checkbox" value="1"/> <label>Abnormality</label></span>-->
                  					  <span  style="margin-left:  5.1%; margin-left:  4.9%\9;">  <input id="chkStatus" type="checkbox"  onclick=checkClick(id); value="1"/> <label>Status</label></span>
                 					  <span  style="margin-left:  1.4%; margin-left:  1.3%\9;">  <input id="chkabnimpchkbox" type="checkbox" onclick=checkClick(id); value="1"/> <label>Abnormality Impact</label></span>
                 					  <span  style="margin-left:  1%;">  <input id="chkWhyWhyHappen" type="checkbox" onclick=checkClick(id); value="1"/> <label>WhyWhy Happen</label></span>
                 					  <span  style="margin-left:  2%; margin-left:  10%\9;">  <input id="chkHTAType" type="checkbox"  onclick=checkClick(id); value="1"/> <label>HTA Type</label></span>
                  					  <span  style="margin-left:  2.6%; margin-left:  3%\9;">  <input id="chkallchkbox" type="checkbox" value="1"/> <label>All</label></span>
							  </div> 
							  </td></tr></table></div>
						</div>
						
</form>