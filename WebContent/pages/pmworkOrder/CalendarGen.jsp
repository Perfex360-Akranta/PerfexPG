<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	jQuery.noConflict();
 	jQuery(document).ready(function(){
	initialiseForm('frmCalGen');
	jQuery('#submitForm').val('frmCalGen');
	var flid = jQuery("#frmCalGen input[id='flid']").val();
	alert(1 +"flid" +flid);
	formatDateBox("dteAllocationstartdate",'dd-MMM-yyyy');
	formatDateBox("dteAllocationEnddate",'dd-MMM-yyyy');
	//formatDateBox("dteCalgenyr",'yyyy');
	fillWithCurrentDate("dteAllocationstartdate");
	fillWithCurrentDate("dteAllocationEnddate");
	//fillWithCurrentDate("dteCalgenyr",'yyyy');
	var locationid = jQuery("#frmCalGen input[id='sbu']").val();	
	/*var factoryid = jQuery("#frmCalGen input[id='pbu']").val();
	var sectionid = jQuery("#frmCalGen input[id='section']").val();	
	var cellId = jQuery("#frmCalGen input[id='cell']").val();
	var flId=jQuery("#frmCalGen input[id='flid']").val();
	
	fillComboBox("frmCalGen","cmbCalGenMachine","machineCombo.commonFilter?cellId="+cellId+"&flid="+flId );	
	fillComboBox("frmCalGen","cmbCalGenSection","sectionCombo.commonFilter?factoryid="+factoryid+"&flid="+flId );
	fillComboBox("frmCalGen","cmbCalGenCell","cellCombo.commonFilter?sectionid="+sectionid+"&flid="+flId );*/
	fillComboBox("frmCalGen","cmbCalGenFactory","factoryCombo.commonFilter?locnId="+locationid+"&keyid=FCT0000001" );
	
	jQuery('#chkCalGen').prop('checked',true);
	if (jQuery('#chkCalGen').is(':checked') == true){
		jQuery('#chkAlloAct').prop('checked',false);
		readOnlyFields("dteAllocationstartdate");
		readOnlyFields("dteAllocationEnddate");
		readOnlyFields("btnAllocateAct");
		//enableFields("dteCalgenyr");
		//	enableFields("btnGenCal");
		}
	else{
		jQuery('#chkAlloAct').prop('checked',true);
	/*	enableFields("dteAllocationstartdate");
		enableFields("dteAllocationEnddate");
		enableFields("btnAllocateAct");
		readOnlyFields("dteCalgenyr");
		readOnlyFields("dteCalgenyr");
*/
		}
	jQuery('#chkCalGen').click(function(){
		if (jQuery('#chkCalGen').is(':checked') == true){
			jQuery('#chkAlloAct').prop('checked',false);
			readOnlyFields("dteAllocationstartdate");
			readOnlyFields("dteAllocationEnddate");
			readOnlyFields("btnAllocateAct");
			enableFields("dteCalgenyr");
			enableFields("btnGenCal");
			}
		else{
			jQuery('#chkAlloAct').prop('checked',true);
			enableFields("dteAllocationstartdate");
			enableFields("dteAllocationEnddate");
			enableFields("btnAllocateAct");
			readOnlyFields("dteCalgenyr");
			readOnlyFields("dteCalgenyr");
		}
		
	});
	jQuery('#dteCalgenyr').datebox({  
		 formatter: function(date){ return date.getFullYear(); }  
	 }); 
	
	
		loadFunctionalLocation("CalGenLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmCalGen","&flid="+flid);

		var flid = jQuery("#frmCalGen input[id='flid']").val();
	
		var sbu = jQuery("#frmCalGen input[id='sbu']").val();
	


		 fillComboBox("frmCalGen","cmbCalGenfactory","factoryCombo.commonFilter" );		
		 fillComboBox("frmCalGen","cmbCalGenSection","sectionCombo.commonFilter" );		
		 fillComboBox("frmCalGen","cmbCalGenCell","cellCombo.commonFilter" );
		 fillComboBox("frmCalGen","cmbCalGenMachine","machineCombo.commonFilter" );		



		 function frmToolChangecmbfactory_onSelect(record)
		 {
		 		jQuery("#cmbbdmsSectionid").combobox('clear');
		 		jQuery("#cmbbdmsCellid").combobox('clear');
		 		jQuery("#cmbbdmsMachineid").combobox('clear');
		 		reloadCombo("frmCalGen","cmbSection","sectionCombo.commonFilter?factId="+record.id);
		 		reloadCombo("frmCalGen","cmbCellid","cellCombo.commonFilter?factId="+record.id  );
		 		//reloadCombo("frmBDMaster","cmbbdmsMachineid","machineCombo.commonFilter?factId="+ record.id );
		 		//reloadCombo("frmBDMaster","cmbbdmsMachineid","costCenter.commonFilter?factId="+ record.id );
		 }
		 function frmToolChangecmbmachine_onSelect(record)
		 {	
		 	//	alert("machId="+ record.id);
		 		lodFuncLoc("onsel"+record.id);
		 	   loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmCalGen","&machId="+record.id);
		 	    jQuery("#cmbToolMstKeyid").combobox('clear');
		 	 	reloadCombo("frmCalGen","cmbToolMstKeyid","tool_Combo.tlmn?machId="+record.id ); 	    
		 	    
		 }
		 function frmToolChangecmbsection_onSelect(record)
		 {	
		 	    loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmCalGen","&secId="+record.id);
		 	    
		 }
		
		
		jQuery('#chkAlloAct').click(function(){
	 		if(jQuery('#chkAlloAct').is(':checked') == true)
			{	
	 			jQuery('#chkCalGen').prop('checked',false);
				
	 			//disableField("frmPcsLoss", "dteCalgenyr");
				readOnlyFields("dteCalgenyr");
				readOnlyFields("btnGenCal");
				enableFields("dteAllocationstartdate");
	 			enableFields("dteAllocationEnddate");
	 			enableFields("btnAllocateAct");
	 			
	 			// jQuery('#dteCalgenyr').prop('disabled',true);
				//jQuery('#dteCalgenyr').hide();
				//jQuery('#btnGenCal').hide();
			}
	 		else{
	 			jQuery('#chkCalGen').prop('checked',true);
	 			enableFields("dteCalgenyr");
	 			enableFields("btnGenCal");
	 			
	 			readOnlyFields("dteAllocationstartdate");
				readOnlyFields("dteAllocationEnddate");
				readOnlyFields("btnAllocateAct");
				
		 		}
		});
 	});	
	
	</script>
	<form id="frmCalGen" name="frmCalGen">
	
		<div id="wrapper">
			
				<div  style="height:450px; width:1200px;">
					<div style="padding-left:00px;margin-top:0px;">
					<div style="height:40%;">
						<div  id="frmCalGen" >
							<div   id="frmCalGenFuntKeyIds" style="width:101.1%;width:280px\9;">
						<%-- <input type="hidden" id="factory" name="cmbPlosFactoryid" value="${requestScope.factId}"  ></input> --%>
								<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
								<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
								<input type="hidden" id="section" name="cmbPlosSectionid" value="${requestScope.sectId}"  ></input>
								<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
								<input type="hidden" id="flid" name="cmbPlosFlid" value="${requestScope.requestScope.flid}"  ></input>
							</div>
							<div id="CalGenLocation" style=" width : 300px;padding-left:30px;"></div>	
							</div>	
							<div style="margin-top: 15px; padding-left:80px;">
							<span><input id="chkCalGen" name="chkCalGen" type="checkbox" /></span><span><label> Calendar Generation  </label></span><span style="padding-left:20px;"><input id="chkAlloAct" name="chkAlloAct" type="checkbox" /></span><span><label> Activity Allocation  </label><span></span>
							</div>
							
							<div id= "Calgen"  class="main-cntborder" style="margin-top: 20px;" >
							<table style="margin-top: 20px;">	
							<tr><td style="padding-left: 0px;"> 
							<label> Factory </label></td><td style="padding-left: 20px;"><label> Section </label></td><td style="padding-left: 20px;"><label> Cell </label></td><td style="padding-left: 20px;"><label> Equipment </label></td>
							<td style="padding-left: 20px;"><label> Year </label></td><td style="padding-left: 20px;"><label> From Date </label></td><td style="padding-left: 20px;"><label> To Date </label></td></tr>
							<tr><td style="padding-left: 0px;">		
		   			    <input class="easyui-combobox" id="cmbCalGenfactory"  name="cmbCalGenfactory" readonly="readonly"  style="width: 150px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalGenSection"  name="cmbCalGenSection" readonly="readonly"  style="width: 150px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalGenCell"        name="cmbCalGenCell" readonly="readonly"  style="width: 200px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalGenMachine"  name="cmbCalGenMachine" readonly="readonly"  style="width: 250px;"  value="${requestScope.shift}"/>
					</td>
					<td style="padding-left: 20px;">
						<input class="easyui-datebox" id="dteCalgenyr"  name="dteCalgenyr"  readonly="readonly" style="width:80px;"  />
					</td>
					<td style="padding-left: 20px;">
						<input  class="easyui-datebox" id="dteAllocationstartdate" name="dteAllocationstartdate"  style=" width : 85px;" value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-datebox" id="dteAllocationEnddate"  name="dteAllocationEnddate"   style=" width : 85px;" value="${requestScope.shift}"/>
					</td></tr>					
					</table>
					<div style="padding-left: 50px;">
        			   <span >  <input type="button" class="easyui-button" id="btnGenCal" name="btnGenCal"  value="Generate Calendar"	style="height: 25px; width:px; margin-top:20px;" /></span>
        			   <span style="padding-left: 30px;">  <input type="button" class="easyui-button" id="btnAllocateAct" name="btnAllocateAct"  value="Allocate Activities"	style="height: 25px; width:px; margin-top:20px;" /></span>
					</div>
					</div>
					</div>					
					</div>
					<!-- 
					
					<div id="allocateActivity" style="margin-top: 100px; height:40%;" class="main-cntborder" >
							<table style="margin-top: 20px;">	
							<tr><td style="padding-left: 0px;"> 
							<label> Factory </label></td><td style="padding-left: 20px;"><label> Section </label></td><td style="padding-left: 20px;"><label> Cell </label></td><td style="padding-left: 20px;"><label> Equipment </label></td><td style="padding-left: 20px;"><label> From Date </label></td><td style="padding-left: 20px;"><label> To Date </label></td>
							</tr>
							<tr><td style="padding-left: 0px;">		
		   			    <input class="easyui-combobox" id="cmbCalAllocatefactory"  name="cmbCalAllocatefactory" readonly="readonly"  style="width: 200px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalAllocateSection"  name="cmbCalAllocateSection" readonly="readonly"  style="width: 200px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalAllocateCell"        name="cmbCalAllocateCell" readonly="readonly"  style="width: 200px;"  value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-combobox" id="cmbCalAllocateMachine"  name="cmbCalAllocateMachine" readonly="readonly"  style="width: 250px;"  value="${requestScope.shift}"/>
					</td><td style="padding-left: 20px;">
						<input  class="easyui-datebox" id="dteAllocationstartdate" name="dteAllocationstartdate"  style=" width : 85px;" value="${requestScope.shift}"/>
						</td><td style="padding-left: 20px;">
						<input class="easyui-datebox" id="dteAllocationEnddate"  name="dteAllocationEnddate"   style=" width : 85px;" value="${requestScope.shift}"/>
					</td></tr>					
					</table>
					<div style="padding-left: 180px;">
        			     <input type="button" class="easyui-button" id="btnAllocateAct" name="btnAllocateAct"  value="Allocate Activities"	style="height: 25px; width:px; margin-top:20px;" />
					</div>
					</div> -->
		</div>
	</div>
	
	
	
	
	</form>