 <script type="text/javascript" src="js/masterplan.js"></script>
 
 <style>
 	.eqppd{
 		position:absolute;right:30px;top:100px;
 	}
 </style>
<script type="text/javascript">	
	jQuery(document).ready(function(){
        // alert(1);	
		initialiseForm('frmMstPlanAct');
		jQuery('#submitForm').val('frmMstPlanAct');
		jQuery('#frmMstPlanAct .easyui-text').css('text-transform', 'uppercase');
		var type = jQuery('#hdnType').val();
		
		 if(screen.width <=1400)
		 {
			 if(type=="eqp" || type=="prod")
				jQuery('#dteControlsnlbls').css('width','38%');
			else
				jQuery('#dteControlsnlbls').css('width','49%');
		 }
		 if(type=="eqp"){
			 	jQuery('#txtEqupProd').val('New Equipment 1');
			 
			 	jQuery("#EqupProd").show();
			 	jQuery("#color").addClass("eqppd");
			 	jQuery("#eqpdFlmngr").show();
			 	 fileManagerPopUp("","EQP","frmMstPlanAct","btnfilemgr","EqpFilemgr"); 
			 	 
			 	jQuery("#Equp").show();
			 	jQuery("#Prod").hide();
		 }
		 else if(type=="prod"){
			 jQuery('#txtEqupProd').val('New Product 1');
			 jQuery("#eqpdFlmngr").show();
			 fileManagerPopUp("","EQP","frmMstPlanAct","btnfilemgr","EqpFilemgr"); 
			 jQuery("#EqupProd").show();
			 jQuery("#color").addClass("eqppd");
			 jQuery("#Prod").show();
			 jQuery("#Equp").hide();
		 }else{
			 jQuery("#eqpdFlmngr").hide();
			 jQuery("#EqupProd").hide();
			 jQuery("#color").removeClass("eqppd");
			 jQuery("#Equp").hide();
			 jQuery("#Prod").hide();
			  
		  }
					 
		 
		 jQuery('#dteMstFromMonth').datebox({  
			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		 });  

		 jQuery('#dteMstToMonth').datebox({  
			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		 });  
		 var url = jQuery('#hiddenUrl').val();	
		 if(url.indexOf("Plan")>=0)
		 {
			 jQuery('#lblDblClickDetails').html('Double click  on week for correspondent Plan to specify Mile stone details');
			 if(url.indexOf("Actual")>=0)
				 jQuery('#lblDblClickDetails').html('Double click  on week for correspondent Plan/Actual to specify Mile stone details');
			 //jQuery('#spnPlanActual').css('background-color','#fee9bf');
			// jQuery('#spnPlanActualLbl').html('Plan');			 
		 }
		 else if(url.indexOf("Actual")>=0)
		 {
			 jQuery('#lblDblClickDetails').html('Double click  on week for correspondent Actual to specify Mile stone details');
			// jQuery('#spnPlanActual').css('background-color','#f19e3d');
			// jQuery('#spnPlanActualLbl').html('Actual');	
		 }
		 
		 
		var pendingCol =  jQuery('#txtPendingcolor').val();
	    var workCol =  jQuery('#txtWIPColor').val();
		var completedCol =  jQuery('#txtCompColor').val();
		var planCol =  jQuery('#txtPlanColor').val();
		jQuery('#spnPlan').css('background-color',(planCol!= null && planCol != '' && planCol != ' ')?planCol:'#fee9bf');
		jQuery('#spnActual').css('background-color',(completedCol!= null && completedCol != '' && completedCol != ' ')?completedCol:'#f19e3d');
		jQuery('#spnPending').css('background-color',(pendingCol!= null && pendingCol != '' && pendingCol != ' ')?pendingCol:'#00ffda');
		jQuery('#spnWorkProgress').css('background-color',(workCol!= null && workCol != '' && workCol != ' ')?workCol:'#059780');
		
	    var factId = jQuery("#frmMstPlanAct input[id='factory']").val();
	    var sectionId = jQuery("#frmMstPlanAct input[id='section']").val();
	    var cellId = jQuery("#frmMstPlanAct input[id='cell']").val();
	    var machId = jQuery("#frmMstPlanAct input[id='machine']").val();
	    var flid = jQuery("#frmMstPlanAct input[id='flid']").val();
	     
	    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  
	    //alert(dataStr);  					
	    loadFunctionalLocation("mspafunLocation","functionalLocPlanAct.conf","mspafunLocationValues","frmMstPlanAct",dataStr);
	    //processGridnew('planactual_input.conf',"?q=2","mstPlanActualGrid","","","mstPlanActualDblClick","","mstPlanActualLoad","mstPlanActualError");
	   
	    fillWithCurrentMonth("dteMstToMonth");
		fillWithCurrentMonth("dteMstFromMonth");
		
	   
	});	
	jQuery('#btnView').click(function(){
		fillPlanActualGrid();
	});
	jQuery('#btnAddAct').click(function(){
		var url = jQuery('#hiddenUrl').val();
		 
		if(url != null && url!= '' && url != ' ')
			url = "master"+url.substring(url.indexOf('_'));
			var indId = jQuery('#hdnSelectedIndicatorId').val();	
			var indName = jQuery('#hdnSelectedIndicator').val();
			var selLevel= jQuery('#hdnSelectedLevel').val();
		  	var factId = jQuery("#frmMstPlanAct input[id='factory']").val();
		    var sectionId = jQuery("#frmMstPlanAct input[id='section']").val();
		    var cellId = jQuery("#frmMstPlanAct input[id='cell']").val();
		    var machId = jQuery("#frmMstPlanAct input[id='machine']").val();
		    var dataStr = "?q=2&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&indId="+indId+"&indName="+escape(indName)+"&selLevel="+selLevel;
		   // alert(dataStr);
		 
		 LoadPopUp("divAddActivity",url+dataStr, true,"90%","80%","8%","10", "AddActivity_Callback","Add Activity","");
		// function LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/
		 jQuery("#refreshGridFlag").val('Y');
	});

/*	function Activity4_formatter(rowId, tv, rawObject, cm, rdata){	
		
		 if(rawObject[4]=="B22")
			return 'rowspan=2';
		 else
			return 'rowspan=0';
	}*/
	function divAddActivity_onClose() {	
		var refreshGridFlag = jQuery('#refreshGridFlag').val();		
		if(refreshGridFlag != 'Y')	
		setTimeout(function() {fillPlanActualGrid();},600);	
		return true;
	}
	
	function AddActivity_Callback(){
		jQuery('#hdnLegendPos').val('Y');			
		setTimeout(function() {
					jQuery('#mstPlanTreeLayer').css('height','76%');
					//jQuery('#mstPlanActivityLayer').css('height','76%');
					//jQuery('#mstPlanActivityLayer').css('width','38%');
		},550);		
	}
	function fillPlanActualGrid(showLineValid)
	{
         //alert(1);
		 var cellId = jQuery("#frmMstPlanAct input[id='cell']").val();
		 var fromMonth = jQuery("#dteMstFromMonth").datebox('getValue');
		 var toMonth = jQuery("#dteMstToMonth").datebox('getValue');
		 var url = jQuery('#hiddenUrl').val();
		 var pillarCode=getFilterValue(url,"pillarCode");
		 //alert(getFilterValue(pillar,"pillarCode"));
		 var masterkeyid=jQuery("#txtMspiKeyid").val();

		 var type = jQuery('#hdnType').val();
		 type ="eqp";

		 if(type=="eqp" || type=="prod"){
			 //var dataStr ="?q=2&dtFromMonth=&dtToMonth=&pillar="+pillarCode+"&type="+type+"&keyid="+masterkeyid;
			 var dataStr ="?q=2&dtFromMonth="+fromMonth+"&dtToMonth="+toMonth+"&pillar="+pillarCode+"&type="+type+"&keyid="+masterkeyid;
			 //alert(" cellId :: dataStr "+dataStr);
			//if(jQuery('#eqpplanGrd').html().length<=62)
				viewMasterPlanGrid("eqGrd",dataStr);
			//else
			//	jQuery('#eqpplanGrd').show();
		 }else{
			// if(pillar != null && pillar!= '' && pillar != ' ')
				//pillar = pillar.indexOf('_')>0?pillar.substring(pillar.indexOf('_')+1, pillar.indexOf('_')>0?pillar.indexOf('.'):pillar.length):pillar;
			//if(type=="eqp" || type=="prod")
				//pillar="GEN";
			
			if(cellId != null && cellId != '' && cellId != ' ')
			 {
			 	var dataStr = "?q=2&cmbCellid="+cellId+"&dtFromMonth="+fromMonth+"&dtToMonth="+toMonth+"&pillar="+pillarCode+"&keyid="+masterkeyid;
			 	//alert(dataStr);
				/***changes made for prototype function available in  the script mentioned above*/
			 	viewMasterPlanGrid("mstPlanActualGrid","Pager",dataStr);
			 	//processGridnew('planactual_input.conf',dataStr,"mstPlanActualGrid","","","mstPlanActualDblClick","","mstPlanActualLoad","mstPlanActualError");
			 }
			 else
			 {
				// if(showLineValid != null && showLineValid != '' && showLineValid != ' ' && showLineValid != 'Y')
				//	 alert('Select JH');
			 }
		 }
	}
	function dteMstFromMonth_onSelect(date)
    {    
		
    }
	function frmMstPlanAct_FuntLocHierarchy_SuccessCallBack(keyIds)
	{		
		var showGrid = jQuery('#txtShowGrid').val();
		var masterkeyid=jQuery("#txtMspiKeyid").val();
		//alert("masterkeyid  "+masterkeyid);
		if(showGrid != null && showGrid != '' && showGrid != ' ' && showGrid == 'Y')
		{
			var lineId = keyIds.cellId;
			
			//if(lineId != null && lineId != '' && lineId != ' ')
			if(masterkeyid != null && masterkeyid != '' && masterkeyid != ' ')
			{
				fillPlanActualGrid();
				//jQuery('#txtShowGrid').val('');
				/*if(masterkeyid != null && masterkeyid!=' ')
					{
				       jQuery("#txtMspiKeyid").val('');
					}
				*/
			}
		}
	}
	function btnfilemgr_click()
	{
		fileManagerPopUp("","EQP","","","");
	}
			
	
	
	
</script>
<form id="frmMstPlanAct" >
<div id="wrapper" style="width:90%" >
<input type="hidden" id="mode">
<input type="hidden" id="refreshGridFlag">
<input type="hidden" id="hdnFormLock">
<input type="hidden" id="hdnSelectedWM">
<input type="hidden" id="hdnSelectedIndicator">
<input type="hidden" id="hdnSelectedIndicatorId">
<input type="hidden" id="hdnSelectedLevel">
<input type="hidden" id="txtMspiKeyid" name="txtMspiKeyid"  value="${requestScope.MspTlIndicatorsMst.mspiKeyid}"></input>
<input type="hidden" id="txtShowGrid"  value="${requestScope.showPlanGrid}" />
<input type="hidden" id="txtPendingcolor"  value="${requestScope.MSTPNAPEND}" />
<input type="hidden" id="txtWIPColor"  value="${requestScope.MSTPNAWIP}" />
<input type="hidden" id="txtCompColor"  value="${requestScope.MSTPNACOMP}" />
<input type="hidden" id="txtPlanColor"  value="${requestScope.MSTPNAPLAN}" />
<input type="hidden" id="hdnType"  value="${requestScope.type}" />

		<div  class="easyui-paddingbfpx"  style="padding-left:25px;">
			<table style="width:100%;"><tr><td>
			<div  class="easyui-paddingbfpx" id="frmMstPlanActFuntKeyIds">
					<input type="hidden" id="factory" name="cmbMspaFactoryid" value="${requestScope.factory}"  ></input>
					<input type="hidden" id="section" name="cmbMspaSectionid" value="${requestScope.section}"  ></input>
					<input type="hidden" id="cell" name="cmbMspaCellid" value="${requestScope.cell}" ></input>
					<input type="hidden" id="machine" name="cmbMspamachine" value="${requestScope.machine}"  ></input>
					<input type="hidden" id="flid" name="txtMspiFlid" value="${requestScope.MspTlIndicatorsMst.mspiFlid}"  ></input>
			</div>
			<div id="mspafunLocation" style="padding-left: px;width:994px;" ></div>
			
			</td>
			</tr></table>
		</div>
		<div id="eqpdFlmngr">
		<span  id="EqpFilemgr" style="position:absolute;top:50;right:160px;" ></span>
		</div>
		<div style="padding-left:25px;">
		<table width="100%"><tr><td id="dteControlsnlbls" >
			<div>
				<span> <label>From Month </label></span>
				<span style="padding-left:18px;"><label>To Month </label></span>			
			</div>
			<div class="easyui-paddingbfpx">   			
			    <span  style="padding-left:1px;">
					<input id="dteMstFromMonth" name="dteMstFromMonth" class="easyui-datebox"  style="width:85px;"/>
				</span>
				<span  style="padding-left:1px;">
					<input id="dteMstToMonth" name="dteMstToMonth" class="easyui-datebox"  style="width:85px;"/>
				</span>
			    <span style="padding-left:1px;">
			    	<input type="button" id="btnView" name="btnView" class="easyui-button" value="View" style="width: 50px;height:20px;">
			    	<input type="button" id="btnAddAct" name="btnAddAct" class="easyui-button" value="Add Activity" style="height:20px;">
				 </span>		
			 </div>	
		  </td>
		  <td>
		  	<div class="easyui-paddingbfpx" id="EqupProd">
		  		<span id="Equp"><label>Equipment Description</label></span>
		  		<span id="Prod"><label>Product Description</label></span>
		  	
		  	<div class="easyui-paddingbfpx">
		  		<input id="txtEqupProd"  type="text" value=" New Product" class="easyui-text"  style="width:275;"/>
		  	</div>
		  	</div>
		  </td>
		  <td style="position:relative;">
		    <span id="color" style="padding-left:1px;">    
			     	<table style="border: 1px solid #a4a4a4;" >
			     	<tr>			     	
			     	<td><span class="wo-priority" id="spnPlan"></span></td>
			     	<td>&nbsp;</td>
			    	<td><label class="wo-LegendLabel" id="spnPlanLbl">Plan</label></td>
			    	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			    	<td><span class="wo-priority" id="spnActual"></span></td>
			     	<td>&nbsp;</td>
			    	<td><label class="wo-LegendLabel" id="spnActualLbl">Completed</label></td>
			    	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			    	<td><span class="wo-priority" id="spnPending"></span></td>
			     	<td>&nbsp;</td>
			    	<td><label class="wo-LegendLabel" id="spnPendingLbl">Pending</label></td>
			    	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			    	<td><span class="wo-priority" id="spnWorkProgress"></span></td>
			     	<td>&nbsp;</td>
			    	<td><label class="wo-LegendLabel" id="spnWorkProgressLbl">Work In Progress</label></td>
			    	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			    	<td> </td>
			    	</tr>			    
			    	</table>		    	
		    </span>
		  </td>
<!--		  <td valign="bottom">-->
<!--				<span style="float:left;right:10">-->
<!--					-->
<!--				</span>-->
<!--			</td>-->
		  </tr></table>
		  </div>
<!--		       <span style="float:right;">-->
<!--		    	<input type="button" id="btnAddAct" name="btnAddAct" class="easyui-button" value="Add Activity" style="height:20px;">-->
<!--	    </span>		-->
		     
	
		<div style="padding-left:25px;">    
			<span id="err_cmbPrplMachineid" class="tpm-errormsg"></span>
			<span id="err_dtePrplPlandate" class="tpm-errormsg" style="padding-left:468px;"></span>	
		</div>		
		<div><label style="color:dark brown;font-weight: bold;padding-left:27px;" id="lblDblClickDetails"></label></div>
		<div  style="padding-left:25px;float:left;" class="prodPlanDiv">			
				<table id="mstPlanActualGrid" style="float: left;"></table>
			<div id="Pager"></div>
		</div>	
		<div id="eqpplanGrd"  style="margin-left:26px;">
		<table id="eqGrd" >
			<tr style=" height : 2px;">
				<td style=" width : 2px;"></td>
			</tr>
		</table>
</div>
</div>

</form>   
