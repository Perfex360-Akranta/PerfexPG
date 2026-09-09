<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmdivcombo');
	var type=jQuery("#cboType").val();
	//var Type=jQuery("#hdntype").val();
	var value=jQuery("#hdnclearbtnval").val();
	//if(type==undefined)
		//type=Type;
	var datastring=jQuery("#hdndatastring").val();
	fillComboBox("frmdivcombo","hdncombobox","companyCombo.commonFilter");
	loadFunctionalLocation("divcombofunLocation","funlocation.mchact?&type="+type,"divcombofunLocationValues","frmdivcombo",datastring);

	if(value=='true' || value==true){
		var name=jQuery("#cmbname").val();
		var combodata=jQuery('#'+ name).combobox('getData');
		var len=combodata.length;
		for(j=0;j<len;j++)
		{
			if(jQuery('#chk'+combodata[j].id).is(':checked')){
			jQuery('#chk'+combodata[j].id).attr('checked',false);
			}			
		}
		}
	 if(screen.width <= 1024){
		 alert("low resolution");

		 jQuery('.divclass').css('width','440px');

		 }
	
});
jQuery(document).keyup(function(event) {
    if(event.which === 27) {
    	jQuery("#Multiplediv").hide();
    }
});

jQuery("#btnclear").click(function (event){
	
	var type=jQuery("#cboType").val();
	var Type= jQuery("#hiddenType").val();
	if(type==undefined)
		type=Type;
	var name=jQuery("#cmbname").val();
	var divId=jQuery("#hdndivId").val();
	var DataString="";
	var combodata=jQuery('#'+ name).combobox('getData');
	var len=combodata.length;
	for(j=0;j<len;j++)
	{
		if(jQuery('#chk'+combodata[j].id).is(':checked')){
		jQuery('#chk'+combodata[j].id).attr('checked',false);
		}			
	}
	if(divId=="divCompare"){
	loadFunctionalLocation("divcombofunLocation","funlocation.mchact?&type="+type,"divcombofunLocationValues","frmdivcombo","");
	DataString="&type="+type;
	processAjaxCalls("reloaddivcombo_input.mchact",DataString,"reloaddivSuccess","reloaddivError");
	}
	else{
		loadFunctionalLocation("divcombofunLocation","funlocation.mchact","divcombofunLocationValues","frmdivcombo","");
		DataString="&type=CMP";
		processAjaxCalls("reloaddivcombo_input.mchact",DataString,"reloaddivSuccess","reloaddivError");
		}
	
	 
});
function FuncLocnData(){
	var Type= jQuery("#hiddenType").val();
	var type=jQuery("#cboType").val();
	if(type==undefined)
		type=Type;
	
	jQuery("#hdnCompareto").val('false');
	var KeyId='';
	var value='';
	var myTr = [];
	jQuery('#FuncLocnData tr').each(function () {
		jQuery(this).find('td').not(':last').each(function () {
            myTr.push(jQuery(this).text());
         });
    });
	 for(i=0;i<myTr.length;i++){
     	if(jQuery('#chk'+myTr[i]).is(':checked')){
				value=myTr[i].trim();
				KeyId+="'"+value+"'"+",";
     	}
	 }
	KeyId = KeyId.trim();
	KeyId = KeyId.slice(0,-1);
	
	jQuery("#Multiplediv").hide();
	jQuery("#checkedvalues").val(KeyId);
	jQuery("#checkedtype").val(type);
}
jQuery("#btnclosedivcombo").click(function (event){
	FuncLocnData();
});	
jQuery("#btnok").click(function (event){
	FuncLocnData();
});	
function getFnLocnValues()
{
	var frmId="frmdivcombo";
	var companyId =jQuery("#"+frmId+" input[id='company']").val();
	var locnId=jQuery("#"+frmId+" input[id='location']").val();
	var factId = jQuery("#"+frmId+" input[id='factory']").val();
	var sectionId =  jQuery("#"+frmId+" input[id='section']").val();
	var cellId = jQuery("#"+frmId+" input[id='cell']").val();
	var machineId=jQuery("#"+frmId+" input[id='machine']").val();
	var dataStr = "&compId="+companyId+"&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machineId;
	if(companyId==undefined || companyId=='undefined'){
		dataStr = "&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machineId;
		}
	var keyid="";
	var formname=jQuery("#hdnformname").val();
	var type=jQuery("#cboType").val();
	var Name=jQuery("#cmbname").val();
	var Type=jQuery("#hdntype").val();
	var divId=jQuery("#hdndivId").val();
	var DataString="";
	//if(type==undefined)
		//type=Type;

	//if(divId=="divCompare"){
	
	if(companyId.trim().length>0 && locnId.trim().length<=0){
		keyid=companyId;
			if(type==undefined)
				type="LCN";

			jQuery("#hiddenType").val(type);
			DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
			jQuery("#DataStringval").val('true');
		}
	else if(locnId.trim().length>0 && factId.trim().length<=0){
			keyid=locnId;
			if(type==undefined)
				type="FCT";

			jQuery("#hiddenType").val(type);
			DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
			jQuery("#DataStringval").val('true');
				
	}
	else if(factId.trim().length>0 && sectionId.trim().length<=0){
			keyid=factId;
			if(type==undefined)
				type="LIN";

			jQuery("#hiddenType").val(type);
			DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
			jQuery("#DataStringval").val('true');
				
		}
	else if(sectionId.trim().length>0&& cellId.trim().length<=0){
		if(type==undefined)
			type="CEL";
			  keyid=sectionId;

			  jQuery("#hiddenType").val(type);
			  DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
			  jQuery("#DataStringval").val('true');
				
		}
	else if(cellId.trim().length>0 && machineId.trim().length<=0){
			 keyid=cellId;
			 if(type==undefined)
					type="MCH";

			 jQuery("#hiddenType").val(type);
			 DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
			 jQuery("#DataStringval").val('true');
		} 
	else if(machineId.trim().length>0){
		keyid=machineId;
		 if(type==undefined)
				type="MCH";

		 jQuery("#hiddenType").val(type);
		 DataString="&keyid="+keyid+"&type="+type+"&comboname="+Name;
		 jQuery("#DataStringval").val('true');
		}
	//}
	var valuee=jQuery("#DataStringval").val();
	if(valuee=='true' || valuee==true) { 
		jQuery("#DataStringval").val('false');
		processAjaxCalls("reloaddivcombo_input.mchact",DataString,"reloaddivSuccess","reloaddivError");
	}
}
function frmdivcombo_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var type=jQuery("#cboType").val();
	setFieldValue('cmbdivCompanyid',keyIds.compId);
	setFieldValue('cmbdivLocationid',keyIds.locnId);
	setFieldValue('cmbdivFactoryid',keyIds.factId);
	setFieldValue('cmbdivSectionid',keyIds.sectId);
	setFieldValue('cmbdivCellid',keyIds.cellId);
	setFieldValue('cmbdivMachineid',keyIds.machId);
	var dataStr = getFnLocnValues();
	return dataStr;
}
function reloaddivSuccess(result){
	jQuery("#datapart").empty();
	var keys;
	var FunlocnIds=result.jsonarray;
	jQuery("#hiddenFunlocnIds").val(FunlocnIds.length);
	for(i=0;i<FunlocnIds.length;i++){
		var keyid=FunlocnIds[i];
		for(j=0;j<keyid.length;j++){
			
			var number=keyid[2];
			var html='<table id="FuncLocnData" width="100%" cellspacing="4";cellpadding="5">';
			html +='<tr><td style="FONT-SIZE: small;width:30px;">';
			if(number=='1'){
				html +='<input type="checkbox" checked="checked" id=chk'+keyid[0]+' value='+keyid[0]+' /><span  style="display: none;">'+keyid[0]+' </span></td>';
				}
			else{
				html +='<input type="checkbox" id=chk'+keyid[0]+' value="'+keyid[0]+'"/><span  style="display: none;">'+keyid[0]+' </span></td>';
				}
				html +='<td><span style="font-size:11;font-family:arial;"> '+keyid[1]+' </span></td>';
				html +='</tr> </table>';

			}
		jQuery("#divdata").append(jQuery("#datapart").append(html));
		}
	
	
}
function divcombo_SuccessCalBack(result){
	jQuery("#datapart").show();
	
}
jQuery("#btnsave").click(function (event){
		var divId=jQuery("#hdndivId").val();
		jQuery("#Multiplediv").hide();
		var type=jQuery("#cboType").val();
		var Type=jQuery("#hdntype").val();
		if(type==undefined)
			type=Type;
		
		var name=jQuery("#cmbname").val();
		var combodata=jQuery('#'+ name).combobox('getData');
		var len=combodata.length;
		var keyid='';
		for(k=0;k<len;k++)
		{
			if(jQuery('#chk'+combodata[k].id).is(':checked')){
				value=jQuery('#chk'+combodata[k].id).val();	
				keyid += "'"+value+"'"+ ",";
			}			
		}
		keyid = keyid.slice(0,-1);
		if(keyid.trim().length<=0){
			FuncLocnData();
		var DataPart=jQuery("#checkedvalues").val();
		keyid = DataPart;
		}
		if(keyid.trim().length<=0){
			jQuery("#Multiplediv").show();
		if(type=="MCH")
				alert("Check Equipment");
			else if(type=="CEL")
				alert("Check Line");
			else if(type=="LIN")
				alert("Check Section");
			else if(type=="FCT")
				alert("Check Unit");
			else if(type=="CMP")
				alert("Check Company");
			else if(type=="LCN")
				alert("Check Location");

		return false;
			}
		else
		jQuery("#hdncheckdata").val(keyid);
		var checkdata=jQuery("#hdncheckdata").val();
		jQuery("#checkedvalues").val(keyid);
		jQuery("#checkedtype").val(type);
		saveForm('frmdivcombo',"savedivcombo.mchact?&checkdata="+checkdata);
	});
//GEN_TL_MULTIPLEFUNCLOCN  
</script>
<form id="frmdivcombo" name="frmdivcombo">
<div id="btnclosedivcombo" style="margin-top: 1.4%;cursor: pointer"><img src='images/close-butt1.png' width='18px;' height='18px;' class='btn_close' style='position:absolute;right:11;'  title='Close(Esc)' alt='Close' onclick=''/></div>
<table>
	<tr>
		<td>
			<div id="funlocn" >
				
					<input type="hidden" id="company" name="cmbdivCompanyid" value="${requestScope.compId}"  >
					<input type="hidden" id="location" name="cmbdivLocationid" value="${requestScope.locnId}"  >
					<input type="hidden" id="factory" name="cmbdivFactoryid" value="${requestScope.factId}"  >
					<input type="hidden" id="section" name="cmbdivSectionid" value="${requestScope.sectId}"  >
					<input type="hidden" id="cell" name="cmbdivCellid" value="${requestScope.cellId}"  >
					<input type="hidden" id="machine" name="cmbdivMachineid" value="${requestScope.machId}">
				
			 </div>
					<div id="divcombofunLocation" class="divclass";  style=" margin-left: 2%; width: 500px;"></div>
					<div  style="display: none">
						<input type="hidden" id="cmbname" name="cmbname" class="easyui-combobox" value="${requestScope.comboname}"/>
						<input  id="hdntype" value="${requestScope.type}"/>
						<input  id="hdndivId" value="${requestScope.divId}"/>
						<input  id="hdndatastring" value="${requestScope.dataString}"/>
						<input  id="hdnformname" value="${requestScope.formname}"/>
						
					</div>
					
	   </td>
	</tr>
	<tr>
		<td>
			<div id="divdata" style="margin-top: 5; position:relative\9;">
			</div>
				<div style="display: none; " id="datapart" class="multicombodata"  >
					<table>
							<c:forEach var="divComboBean" items="${requestScope.divComboBean}">
							   <tr><td style="FONT-SIZE: small;"><input type="checkbox"  id=chk<c:out value="${divComboBean[0]}"/>  <c:if test="${divComboBean[2] == '1'}"> checked="checked"  </c:if> value="<c:out value="${divComboBean[0]}"/>"  /></td>
							    	<td ><span style="font-size:11;font-family:arial;"><c:out value=" ${divComboBean[1]}" /></span></td>
								</tr>
							 </c:forEach>
						 </table>
				</div>
				
		
			</td>
		</tr>
		<tr><td>
		<div style="padding-top: 200px; padding-top: 170px\9; padding-left: 34%; padding-left: 150\9px; z-index: 2;">
					<input type='button' id='btnok' name='btnok'  class='easyui-button' value='Ok'  style='height:20px;' />
					<span style='padding-left: 3%'><input type='button' id='btnsave' name='btnsave'  class='easyui-button' value='Save'  style='height:20px;' /></span>
					<span style='padding-left: 3%'><input type='button' id='btnclear'  name='btnclear' class='easyui-button' value='Clear' style='height:20px;' /></span>
				</div>
		</td></tr>
</table>
	
	<input type="hidden" id="hdnid"/>
	<input type="hidden" id="hdndatavalue"/>
	<input type="hidden" id="filterval"/>
	<input type="hidden" id="checkedvalues"/>
	<input type="hidden" id="checkedtype"/>
	<input type="hidden" id="hiddenType"/>
	<input type="hidden" id="hiddenFunlocnIds"/>
	<input type="hidden" id="DataStringval" value="false" />
	 <input type="hidden" id="mode" name="mode" value=""/>
	<input type="hidden" id="hdncheckdata" name="hdncheckdata" />
	<input type="hidden" id="hdnCompareto" name="hdnCompareto" value="true"/>
		
</form>

