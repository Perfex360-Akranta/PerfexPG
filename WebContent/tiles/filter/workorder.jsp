<!--<%@ page language="java" contentType="text/html; charset=UTF-8" %>-->
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<!--<script type="text/javascript" src="js/jquery.easyui.min.js">-->
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmWO');
	initialiseForm('frmWO');
	jQuery('#lblLossFilter').hide();
	jQuery('#spnLossFilter').hide();
	fillComboBox("frmWorkOrder","cmbType","combo_activity.work" );
	formatDateBox('dteOccuredfrm','dd-MMM-yyyy');
	formatDateBox('dteOccuredto','dd-MMM-yyyy');
	formatDateBox('dteReportedfrm','dd-MMM-yyyy');
	formatDateBox('dteReportedto','dd-MMM-yyyy');
	formatDateBox('dteAllotedfrm','dd-MMM-yyyy');
	formatDateBox('dteAllotedto','dd-MMM-yyyy');
	formatDateBox('dteWorkStartfrm','dd-MMM-yyyy');
	formatDateBox('dteWorkStartto','dd-MMM-yyyy');
	formatDateBox('dteWorkEndfrm','dd-MMM-yyyy');
	formatDateBox('dteWorkEndto','dd-MMM-yyyy');
	formatDateBox('dteProductionfrm','dd-MMM-yyyy');
	formatDateBox('dteProductionto','dd-MMM-yyyy');	
	
	
	fillWithCurrentDate('spnOccuredfrm');
	fillWithCurrentDate('spnProductionto');
	fillWithCurrentDate('spnProductionfrm');
	fillWithCurrentDate('spnWorkStartto');
	fillWithCurrentDate('spnWorkStartfrm');
	fillWithCurrentDate('spnReportedto');
	fillWithCurrentDate('spnReportedfrm');
	fillWithCurrentDate('spnOccuredto');
	fillWithCurrentDate('spnAllotedfrm');
	fillWithCurrentDate('spnAllotedto');
	fillWithCurrentDate('spnWorkEndfrm');
	fillWithCurrentDate('spnWorkEndto');
	fillWithCurrentDate('dteOccuredfrm');
	fillWithCurrentDate('dteOccuredto');
	fillWithCurrentDate('dteReportedfrm');
	fillWithCurrentDate('dteReportedto');
	fillWithCurrentDate('dteProductionfrm');
	fillWithCurrentDate('dteProductionto');
	fillWithCurrentDate('dteWorkEndfrm');
	fillWithCurrentDate('dteWorkEndto');
	fillWithCurrentDate('dteWorkStartfrm');
	fillWithCurrentDate('dteWorkStartto');
	fillWithCurrentDate('dteAllotedfrm');
	fillWithCurrentDate('dteAllotedto');
	
	if(  !jQuery('#cmbMSRId').is(':disabled') )
		fillComboBox("frmWO","cmbMSRId","msr.commonFilter","",true );
	
	enabledisableDateInWOFilter('chkOccuredDate','dteOccuredfrm','dteOccuredto','spnOccuredfrm','spnOccuredto');
	enabledisableDateInWOFilter('chkReportedDate','dteReportedfrm','dteReportedto','spnReportedfrm','spnReportedto');
	enabledisableDateInWOFilter('chkAllottedDate','dteAllotedfrm','dteAllotedto','spnAllotedfrm','spnAllotedto');
	enabledisableDateInWOFilter('chkWostartDate','dteWorkStartfrm','dteWorkStartto','spnWorkStartfrm','spnWorkStartto');
	enabledisableDateInWOFilter('chkWoendDate','dteWorkEndfrm','dteWorkEndto','spnWorkEndfrm','spnWorkEndto');
	enabledisableDateInWOFilter('chkProdDate','dteProductionfrm','dteProductionto','spnProductionfrm','spnProductionto');
	processGridnew("activityType_input.commonFilter","?q=1","activityTypeGrid","","Activity Type","actType","","actTypeComplete");
	

});	
/*function viewGrid(url,filterString)
{
	processGridnew(url,filterString,"activityTypeGrid","activityTypePager","Activity Type","actType","","actTypeComplete");
	return true;	
}*/
function actTypeFormatter(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input  type="checkbox" id ="'+rowObject[1]+'_'+id+'" onclick="if(this.checked){selectActivity(\''+rowObject[1] + '\');}else{unselectActivity(\''+rowObject[1] + '\');}"/>';
}
function selectActivity(rowId){
	if(rowId == 'A')
	{
		jQuery('#lblLossFilter').show();
		jQuery('#spnLossFilter').show();
	}	
}
function unselectActivity(rowId){
	if(rowId == 'A')
	{
		jQuery('#lblLossFilter').hide();
		jQuery('#spnLossFilter').hide();
	}
}
function frmWorkOrdercmbType_onSelect(record)
{
	if(record.id == 'A')
	{
		jQuery('#lblLossFilter').show();
		jQuery('#spnLossFilter').show();
	}
	else
	{
		jQuery('#lblLossFilter').hide();
		jQuery('#spnLossFilter').hide();
	}
}

	
function enabledisableDateInWOFilter(chbId,dateId,toDateId,spinnerId,toSpinnerId)
{
	jQuery('#'+chbId).click(function(){
	if(jQuery('#'+chbId).is(':checked') == true)
	{	
		jQuery("#"+dateId).datebox('enable');
		jQuery("#"+toDateId).datebox('enable');	
		jQuery("#"+spinnerId).spinner('enable');		
		jQuery("#"+toSpinnerId).spinner('enable');			
	}
	else
	{
		jQuery("#"+dateId).datebox('disable');
		jQuery("#"+toDateId).datebox('disable');
		jQuery("#"+spinnerId).spinner('disable');		
		jQuery("#"+toSpinnerId).spinner('disable');		
		jQuery("#"+dateId).datebox("setValue","");
		jQuery("#"+toDateId).datebox('setValue',"");
		jQuery("#"+spinnerId).spinner("setValue","");
		jQuery("#"+toSpinnerId).spinner('setValue',"");
	}
});
}
function getRelatedFilterValues()
{
	var getAct = getSelectdRows('activityTypeGrid','actType_checkbox','chkactTypeFlag','txtLossName','N');
	var loss = getSelectdRows('activityTypeGrid','actType_checkbox','chkactTypeFlag','txtLossName','Y');
	
	var filterStr="";
	
	var txtPrblm = jQuery("#txtPrblm").val();
	filterStr += "&txtPrblm="+txtPrblm;


	//var cmbType = jQuery("#cmbType").combobox('getValue');	
	filterStr += "&cmbType="+getAct;	
	
	var cmbMSRKeyId = jQuery("#cmbMSRId").combobox("getValue");
	filterStr += "&cmbMSRKeyId="+cmbMSRKeyId;
	
	var cmbLoss = jQuery("#cmbLoss").combobox('getValue');	
	filterStr += "&cmbLoss="+loss;	

	var CmbPriority = jQuery("#cmbPriority").combobox("getValue");
	filterStr += "&CmbPriority="+CmbPriority;
	
	var CboStatus = jQuery("#cboStatus").val();
	filterStr += "&CmbStatus="+CboStatus;
	
	filterStr += "&chkOccuredDate="+getChkBoxVal('chkOccuredDate');
	filterStr += "&chkReportedDate="+getChkBoxVal('chkReportedDate');
	filterStr += "&chkAllottedDate="+getChkBoxVal('chkAllottedDate');
	filterStr += "&chkWostartDate="+getChkBoxVal('chkWostartDate');
	filterStr += "&chkWoendDate="+getChkBoxVal('chkWoendDate');
	filterStr += "&chkProdDate="+getChkBoxVal('chkProdDate');
	
	
	var dteOccuredfrm = jQuery("#dteOccuredfrm").datebox("getValue")+' '+ jQuery("#spnOccuredfrm").spinner('getValue');
	filterStr += "&dteOccuredfrm="+dteOccuredfrm;


	var dteOccuredto = jQuery("#dteOccuredto").datebox("getValue")+' '+ jQuery("#spnOccuredto").spinner('getValue');
	filterStr += "&dteOccuredto="+dteOccuredto;


	var dteReportedfrm = jQuery("#dteReportedfrm").datebox("getValue")+' '+ jQuery("#spnReportedfrm").spinner('getValue');
	filterStr += "&dteReportedfrm="+dteReportedfrm;


	var dteReportedto = jQuery("#dteReportedto").datebox("getValue")+' '+ jQuery("#spnReportedto").spinner('getValue');
	filterStr += "&dteReportedto="+dteReportedto;

	

	var dteAllotedfrm = jQuery("#dteAllotedfrm").datebox("getValue")+' '+ jQuery("#spnAllotedfrm").spinner('getValue');
	filterStr += "&dteAllotedfrm="+dteAllotedfrm;

	var dteAllotedto = jQuery("#dteAllotedto").datebox("getValue")+' '+ jQuery("#spnAllotedto").spinner('getValue');
	filterStr += "&dteAllotedto="+dteAllotedto;



	var dteWorkStartfrm = jQuery("#dteWorkStartfrm").datebox("getValue")+' '+ jQuery("#spnWorkStartfrm").spinner('getValue');
	filterStr += "&dteWorkStartfrm="+dteWorkStartfrm;

	

	var dteWorkStartto = jQuery("#dteWorkStartto").datebox("getValue")+' '+ jQuery("#spnWorkStartto").spinner('getValue');
	filterStr += "&dteWorkStartto="+dteWorkStartto;


	var dteWorkEndfrm = jQuery("#dteWorkEndfrm").datebox("getValue")+' '+ jQuery("#spnWorkEndfrm").spinner('getValue');
	filterStr += "&dteWorkEndfrm="+dteWorkEndfrm;



	var dteWorkEndto = jQuery("#dteWorkEndto").datebox("getValue")+' '+ jQuery("#spnWorkEndto").spinner('getValue');
	filterStr += "&dteWorkEndto="+dteWorkEndto;


	var dteProductionfrm = jQuery("#dteProductionfrm").datebox("getValue")+' '+ jQuery("#spnProductionfrm").spinner('getValue');
	filterStr += "&dteProductionfrm="+dteProductionfrm;

	var dteProductionto = jQuery("#dteProductionto").datebox("getValue")+' '+ jQuery("#spnProductionto").spinner('getValue');
	filterStr += "&dteProductionto="+dteProductionto;


	return filterStr;
}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName,lossColName,lossFlag){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO=null;
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		for(var colName in row) {
			
			if(row[colName].substring(0,6)=='<input')
			{
				var x=row[colName].indexOf("id=")+4;				
				var y=row[colName].substring(x);				
				var z = y.indexOf('"');	
				var chbId = y.substring(0,z);							
					
				var isChecked = jQuery('#'+chbId).is(':checked');
			
				if(isChecked == true)
				{
					
					var columnName = chbId.substring(0,chbId.indexOf('_'));	
					var loss = jQuery("#"+jqGridId).getCell(chbId.substring(chbId.indexOf('_')+1), lossColName);
					if(lossFlag == 'Y'){
					if(loss == 'Loss')
					{
						var schedule = jQuery("#"+jqGridId).getCell(chbId.substring(chbId.indexOf('_')+1), ckeckForSelColName);	
									
					
						if(jsonArrO == null)
							jsonArrO = schedule +",";
						else
							jsonArrO += schedule +",";
					}
					}
					else{
						if(loss != 'Loss')
						{
							var schedule = jQuery("#"+jqGridId).getCell(chbId.substring(chbId.indexOf('_')+1), ckeckForSelColName);	
							if(jsonArrO == null)
								jsonArrO = schedule +",";
							else
								jsonArrO += schedule +",";
						}
						}
					//jsonArrO = jsonArrO.slice(0, -1) + "},"; 
				}							
				
			}
			
		}
	}
	
	//jsonArrO = jsonArrO.slice(0, -1) + "]";
	//jsonArrO = (jsonArrO != ']'?jsonArrO:"");		

	return jsonArrO; 
}

function actTypeComplete()
{
	var rowIds = jQuery("#activityTypeGrid").getDataIDs();
	for(var i = 1; i<=rowIds.length; i++)
	{
	var dataType = jQuery("#activityTypeGrid").getCell(i, 'txtLossName');
	/*if(dataType == "Loss")
		hideJqGridRow('activityTypeGrid', i);*/
	}
	
}
</script>

	<form id="frmWO" name="frmFilter">

						<div  title="Wo Related" style="padding:10px;">
								
							   	<div align="center">
							   
									<table><tr>
									<td valign="top">
										<div  class="easyui-paddingbfpx">
                        					<label>Problem</label>                       
                    		  			 </div> 
                    		  			 <div  class="easyui-paddingbfpx">
                        					<input id="txtPrblm" name="txtPrblm" class="easyui-text"  style="width:350px" value="" >                       
                    		  			 </div>	

                    		  			 
                    		  			 <div  class="easyui-paddingbfpx">
                        					<label>Priority</label>
                        					<span style="margin-left: 145px;"><label>Status</label></span>                       
                    		  			 </div>
                    		  			 <div class="easyui-paddingbfpx"> 
			                        		<select id="cmbPriority" class="easyui-combobox" name="cmbPriority" style="width:170px;" >
													<option value="">ALL </option>
													<option value="L"> LOW</option>
													<option value="M"> MEDIUM</option>
													<option value="H"> HIGH</option>
													<option value="V"> VERY HIGH</option>
									 		</select> 
			                        		<span style="margin-left: 10px;">
			                        			<select id="cboStatus" class="easyui-combobox" name="cboStatus" style="width:170px;" >
													<option value=""> All</option>
													<option value="B"> Booked</option>
													<option value="L"> Allotted</option>
													<option value="C"> Completed</option>
													<option value="E"> Production Approval</option>
									 			</select> 
			                        		</span>                       
			                   			 </div>

			                   			 
                    		  			 <div  class="easyui-paddingbfpx">
                        					<label>Reported From</label>
                        					<span style="margin-left: 97px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			 <div class="easyui-paddingbfpx"> 
                    		  			  <input type="checkbox"  id="chkReportedDate" name="chkReportedDate" value="Y"/> 
			                        		<input id="dteReportedfrm" name="dteReportedfrm" class="easyui-datebox"  style="width:100px" value=""  >
												<span class="spinner"><input id="spnReportedfrm" name="spnReportedfrm"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
											<span style="margin-left: 10px;">
			                        			<input id="dteReportedto" name="dteReportedto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnReportedto" name="spnReportedto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>

			                   			 </div>
			                   			  

			                   			
                    		  			 
			                   			
                    		  			 <div  class="easyui-paddingbfpx">
                        					<label>Work Start From</label>
                        					<span style="margin-left: 95px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			 
                    		  			  <div class="easyui-paddingbfpx"> 
                    		  			   <input type="checkbox"  id="chkWostartDate" name="chkWostartDate" value="Y"/> 
			                        		<input id="dteWorkStartfrm" name="dteWorkStartfrm" class="easyui-datebox"  style="width:100px" value=""  >
												<span class="spinner"><input id="spnWorkStartfrm" name="spnWorkStartfrm"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
											<span style="margin-left: 10px;">
			                        			<input id="dteWorkStartto" name="dteWorkStartto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnWorkStartto" name="spnWorkStartto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>

			                   			 </div>
			                   			
                    		  			 
			                   			

                    		  			 <div  class="easyui-paddingbfpx">
                        					<label>Production From</label>
                        					<span style="margin-left: 92px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			 
                    		  			  <div class="easyui-paddingbfpx"> 
                    		  			   <input type="checkbox"  id="chkProdDate" name="chkProdDate" value="Y"/> 
			                        		<input id="dteProductionfrm" name="dteProductionfrm" class="easyui-datebox"  style="width:100px" value=""  >
												<span class="spinner"><input id="spnProductionfrm" name="spnProductionfrm"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
											<span style="margin-left: 10px;">
			                        			<input id="dteProductionto" name="dteProductionto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnProductionto" name="spnProductionto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>

			                   			 </div>
			                   			 
			                   			 <div  class="easyui-paddingbfpx">
                        					<label>MSR</label>                       
                    		  			 </div> 
                    		  			 <div  class="easyui-paddingbfpx">
                        					<input id="cmbMSRId" name="cmbMSRId" class="easyui-text"  style="width:350px" value="" hasDownArrow="false">                       
                    		  			 </div>	

									</td>
										
									<td valign="top" style="padding-left: 25px;">
										
                    		  			 
			                   			 
			                   			 <div  class="easyui-paddingbfpx">
                        					<label>Occured From</label>
                        					<span style="margin-left: 170px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			  <div class="easyui-paddingbfpx">
                    		  			   <input type="checkbox"  id="chkOccuredDate" name="chkOccuredDate" value="Y"/> 
			                        		<input id="dteOccuredfrm" name="dteOccuredfrm" class="easyui-datebox"  style="width:100px" value=""  >
			                        		<span class="spinner"><input id="spnOccuredfrm"" name="spnOccuredfrm""  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>

											<span style="margin-left: 10px;">
			                        			<input id="dteOccuredto" name="dteOccuredto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnOccuredto" name="spnOccuredto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>

			                   			 </div>
			                   			 
			                   			 <div  class="easyui-paddingbfpx">
                        					<label>Alloted From</label>
                        					<span style="margin-left: 112px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			 
                    		  			  <div class="easyui-paddingbfpx">
                    		  			   <input type="checkbox"  id="chkAllottedDate" name="chkAllottedDate" value="Y"/> 
			                        		<input id="dteAllotedfrm" name="dteAllotedfrm" class="easyui-datebox"  style="width:100px" value=""  >
												<span class="spinner"><input id="spnAllotedfrm"" name="spnAllotedfrm""  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
											
											<span style="margin-left: 10px;">
			                        			<input id="dteAllotedto" name="dteAllotedto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnAllotedto" name="spnAllotedto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>

			                   			 </div>
			                   			 
			                   			 <div  class="easyui-paddingbfpx">
                        					<label>Work End From</label>
                        					<span style="margin-left: 100px;"><label>To</label></span>                       
                    		  			 </div>
                    		  			 
                    		  			  <div class="easyui-paddingbfpx"> 
                    		  			  <input type="checkbox"  id="chkWoendDate" name="chkWoendDate" value="Y"/> 
			                        		<input id="dteWorkEndfrm" name="dteWorkEndfrm" class="easyui-datebox"  style="width:100px" value=""  >
											<span class="spinner"><input id="spnWorkEndfrm" name="spnWorkEndfrm"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
											
											<span style="margin-left: 10px;">
			                        			<input id="dteWorkEndto" name="dteWorkEndto" class="easyui-datebox"  style="width:100px" value=""  >
			                        			<span class="spinner"><input id="spnWorkEndto" name="spnWorkEndto"  class="easyui-timespinner spinner-text validatebox-text" min="00:00" showseconds="false" style="width: 60px;" value="" ></span>
			                        		</span>
			                   			 </div>
<!--			                   			 <div  class="easyui-paddingbfpx">-->

<!--                        					<label>Activity Type</label>  -->
                        					                
<!--                    		  			 </div>-->
			                   			<div class="easyui-paddingbfpx">
			                   			
				                   			<div style="float:left;">
								 		  	 <table id="activityTypeGrid" style="width:100%"><tr><td></td></tr></table>
		 									 <div id="activityTypePager"></div>
		 									 </div>
	 									
	 									  <span style="float:right;" id="spnLossFilter">
	 									  <label style="padding-left:2%" id="lblLossFilter">Affect Loss</label>    
													<select id="cmbLoss" class="easyui-combobox" clear="false" name="cmbLoss" style="height: 22px;width:170px;">
														<option value=""> </option>
														<option value="U"> UNPLANNED MAINTENANCE</option>
														<option value="J"> JH TAG REMOVAL</option>							
														<option value="M"> M AND A</option>		
													</select>
												</span>
	 									 </div>
<!--	 									 <div class="easyui-paddingbfpx"> -->
                    		  			 		
<!--												<input  id="cmbType" name="cmbType" class="easyui-combobox" style="width: 170px;" />-->
											
<!--										</div>-->
									</td>
									</tr>
									</table>
								</div>
						</div>
						</form>