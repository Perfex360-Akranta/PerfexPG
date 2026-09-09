<!-- Created By: Roopa -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script> 

jQuery.noConflict();
//var glbfre=null;
jQuery(document).ready(function()
{	 		
	//alert("KpiTlActualKk.jsp")
	var actionPart = jQuery('#hiddenUrl').val();	
	jQuery('#submitForm').val('frmKpiTlActualKk'); // set the id of form to submit
	initialiseForm('frmKpiTlActualKk');		
	fillComboBox("frmKpiTlActualKk","cmbKaukCalendaryear","comboYearTest.progcal");
	fillComboBox("frmKpiTlActualKk", "cmbKaukPillarid", "pillar.commonFilter");
	//--Frequency Combo--
	fillComboBox("frmKpiTlActualKk", "cmbKpiFrequency", "combo_Frequency1.keyPerInd");
	var varFrequency = jQuery('#hdnFrequency').val();
	//alert("The varFrequency:::"+varFrequency);
	//alert("varFrequency"+varFrequency);
	var location=jQuery("#hdnlocationid").val();
	var rolename=jQuery("#hdnrolename").val();
	var CurrDate=jQuery("#hdnkpiDate").val();
	//alert(CurrDate);
	var CurrMonthYear=jQuery("#hdnCurrMonthYear").val();
	//alert("CurrYear:::"+CurrMonthYear);
    jQuery("#btnExcelVw").hide();
	jQuery('#cmbKpiFrequency').combobox('setValue',varFrequency);
	if(varFrequency=='D'){
		fillWithCurrentMonth("cmbMonth");
		/* jQuery('#cmbMonth').datebox({  
			formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		}); */
		
		formatMonthBox("cmbMonth","Mon-YYYY");
		
		var currentDate =  getServerDateTime();		
		var monthYear=  getMonthStringFromInt(currentDate.getMonth())+'-'+currentDate.getFullYear();	
		jQuery('#cmbMonth').datebox('setValue',monthYear);
		enableFields('cmbMonth');
		enableUIButton("btnGraphDailyPop");
	//	disableUIButton("btnGraphPop");
		
	}else{
		jQuery("#cmbMonth").datebox("clear");
		readOnlyFields('cmbMonth');
		enableUIButton("btnGraphPop");
		disableUIButton("btnGraphDailyPop");
	}
	/*
	fillWithCurrentMonth("cmbMonth");
	jQuery('#cmbMonth').datebox({  
		formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	});
	jQuery("#cmbMonth").datebox("clear");
	readOnlyFields('cmbMonth');*/
	//var currentDate =  getServerDateTime();	
	//alert("currentDate:" +currentDate) ;
	//var monthYear=  getMonthStringFromInt(currentDate.getMonth())+'-'+currentDate.getFullYear();
	//alert("monthYear " +monthYear);
	//jQuery('#cmbMonth').datebox('setValue',monthYear);
	/* jQuery('#cmbMonth').datebox({  	   
	   	onSelect:function(recordid)
			{
	   		fnClear1();
	   			
			} 
	});
	//----
	jQuery('#cmbMonth').datebox({  	   
	   	onClear:function(recordid)
			{
	   		jQuery('#cmbKpiFrequency').combobox('setValue',"M");
	   		fnClear1();
	   			
			} 
	}); */

	var filter = jQuery('#hdnFilter').val();
	var flidDefault =jQuery("#hdnFlid").val();
	var flid;
	if(flidDefault!=null)
		flid=flidDefault;
	else 
		flid=jQuery("#frmKpiTlActualKk input[id='flId']").val();
	var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&flid="+flid ;	
	if(filter == 'N')
	{
		var factId = jQuery('#hdnFactId').val();
		var sectionId = jQuery('#hdnSectId').val();
		var cellId = jQuery('#hdnCellId').val();
		var year = jQuery('#hdnYear').val();	
		//alert("year"+year);
		//cellId = "CEL0000066";
		jQuery('#cmbKaukCalendaryear').combobox('setValue',year);
		
		dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId ;	
	}
		
	loadFunctionalLocation("kpiActKkfunLocation","functionalLoc.kpiActKk","kpiActKkfunLocationValues","frmKpiTlActualKk",dataStr);
	/*----------------------------------*/	
	
	var formMode=jQuery("#txtFrmMode").val();
	//glbfre=jQuery("#cmbKpiFrequency").combobox('getvalue');
	
	if(formMode.trim() == "target"){
	
		jQuery("#graphDiv").hide();	
	}
	
	
	jQuery('#btnGraphPop').click(function(){
		var pilar = getFieldValue("cmbKaukPillarid");	
		var section = jQuery("#frmKpiTlActualKk input[id='section']").val();	
		var type =getFieldValue("txtFormType");	 
		var year =getFieldValue("cmbKaukCalendaryear");
		var vFreq= getFieldValue("cmbKpiFrequency");
		var vMonth= getFieldValue("cmbMonth");
		//alert("vMonth"+vMonth);
	//alert("vFreq"+vFreq);
		//alert(year);
		if(section!="" && section!=null){
			if(vFreq=="M")
			LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pillar="+pilar+"&rptType=M"+"&type="+type+"&dbyear="+year, true,"38%","75%","0px","40%", "multiSelectOk_Callback",type+" Indicators",false);
			else
				//LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pillar="+pilar+"&rptType=DM"+"&type="+type+"&dbyear="+year, true,"38%","75%","0px","40%", "multiSelectOk_Callback",type+" Indicators",false);	
			LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pillar="+pilar+"&rptType=DM&vMonth="+vMonth+"&type="+type, true,"38%","75%","0px","40%", "multiSelectOk_Callback",type+" Indicator",false);
		}
		else
			popupCommonErrorMsg("Select Functional Location");
	});
	
	jQuery('#btnGraphDailyPop').click(function(){
		var pilar = getFieldValue("cmbKaukPillarid");	
		var section = jQuery("#frmKpiTlActualKk input[id='section']").val();		
		var MonthYear="";
		var colIndex=jQuery('#dblColIndex').val()	;
		//alert("colIndex:"+colIndex);	
		//var freq=jQuery("#grdKpiActualkk").getCell(id, 13).trim();
		var controlId="txtInd_"+(colIndex-1) + "_1";
		MonthYear=jQuery('#' + controlId).attr('monthYear');
		//alert("btnGraphDailyPop MonthYear"+MonthYear);
		var currentDate =  getServerDateTime();	
		//alert("MonthYear1"+MonthYear+"currentDate"+currentDate);		
		//var month="01-"+jQuery("#"+controlId).attr('monthyear');					
		var gMonthYear="";
		var type =getFieldValue("txtFormType");	 		 	
		if(section!="" && section!=null){
			//LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pillar="+pilar+"&rptType=D", true,"38%","75%","0px","40%", "multiSelectOk_Callback","KPI Indicator",false);
		}
		else{
			popupCommonErrorMsg("Select Functional Location");
			return false;
		}
			
		if(MonthYear!="" && MonthYear!=null){
			if(MonthYear.length!=11){
				gMonthYear = "01-"+MonthYear;
			}else{
				MonthYear = MonthYear.substring(3,11);
			}
			if (currentDate >= convertStringToDate(gMonthYear)){	
				//alert("MonthYear2"+MonthYear);	
				LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pillar="+pilar+"&rptType=D&monthYear="+MonthYear+"&type="+type, true,"38%","75%","0px","40%", "multiSelectOk_Callback",type+" Indicator",false);
			}
			else{
				popupCommonErrorMsg("Select Valid Month for Daily Report");
			}
		}
		else
			popupCommonErrorMsg("Click Any Month for Daily Report");
	});  

});

jQuery("#btnActionpln").click(function(){
	var colIndex=jQuery('#dblColIndex').val()	;	
//	alert("colIndex:" +colIndex);
	var controlId="txtInd_"+(colIndex-1) + "_1";
	var MonthYear=jQuery('#' + controlId).attr('monthYear');
	//alert("MonthYear"+MonthYear);
	
	
	var cKeyId=jQuery("#hdnKPIKeyid").val();
	//alert("cKeyId :" +cKeyId);
	if(cKeyId=='' ||cKeyId==' '||cKeyId=='undefined'){
		alert("Select a valid KPI entry");
		return false;
	}
	
	var currentDate =  getServerDateTime();	
		if(colIndex>19){
			if(MonthYear.length!=11)
				MonthYear ="01-" + MonthYear;
			if (currentDate >= convertStringToDate(MonthYear)){	
				saveForm('frmKpiTlActualKk','kpiActualKkActual_save.kpiActKk?openactnpln=Y');
			}
			else{
				popupCommonErrorMsg("Select Valid Month for Action Plan");
			}		
		}
		else{
			popupCommonErrorMsg("Click Any Month for Action Plan");
		}
		
		//alert("End of Action Plan");
	 
});

jQuery("#btnYYLink").click(function(){
	var colIndex=jQuery('#dblColIndex').val()	;	
	var controlId="txtInd_"+(colIndex-1) + "_1";
	var MonthYear=jQuery('#' + controlId).attr('monthYear');
	//alert("MonthYear"+MonthYear);
	var currentDate =  getServerDateTime();	
	if(colIndex>19){
		if(MonthYear.length!=11)
			MonthYear ="01-" + MonthYear;
		if (currentDate >= convertStringToDate(MonthYear)){	
			saveForm('frmKpiTlActualKk','kpiActualKkActual_save.kpiActKk?whywhy=Y');
		}
		else{
			popupCommonErrorMsg("Select Valid Month for Why Why");
		}		
	}
	else{
		popupCommonErrorMsg("Click Any Month for Why Why");
	}
});

function divKpiRemarks_onClose() {
	jQuery('#submitForm').val('frmKpiTlActualKk'); // set the id of form to submit
	return true;
}

function viewGrid(url,filterString)
{	
	filterString += "";
	//alert(filterString);
	processGridnew(url,filterString+'',"grdKpiActualkk","pager","","","","GridLoadCompleted");
	return true;	
}

//madhan
function txtFormatter(id, options, rowObject)
{	
	var frmMode=jQuery("#txtFrmMode").val();
	var indicatorId = rowObject[7];		
	var id = options.rowId;	
	///glbtextid=id;
	var columnName = options.colModel.name;	
	var monthYear = columnName.substring(0,columnName.indexOf("_"));
	var columnNo=columnName.substring(columnName.indexOf("_")+1);
	var columnVal =rowObject[columnName.substring(columnName.indexOf("_")+1)];
	//alert(columnNo+"    columnVal  "+columnVal);
	var colKeyId="";
	if(columnVal!=null && columnVal!=undefined && columnVal!=""){
		if(columnVal.indexOf(";") > 0)
		{
			colKeyId=columnVal.substring(0,columnVal.indexOf(";"));
			columnVal=columnVal.substring(columnVal.indexOf(";")+1);
		}
		else{columnVal="";}
	}
	else{columnVal="";}
	//if(frmMode=="view"){
		//return '<span id="txtInd_'+columnNo + '_'+id +'"  type="text" indicatorId="'+indicatorId+'"  keyId="'+colKeyId+'" monthYear="'+monthYear+'" style="width:80px;text-align:right;" maxlength="10" onfocus=textBlur1("txtInd_'+columnNo + '_'+id +'") onblur="textBlur();">'+columnVal+'</span>';
	//}
	///else{
		/** Sugumar Change on 25Apr-2016 for KPI Indicator name TO display On top**/
  		//return '<input id="txtInd_'+columnNo + '_'+id +'"  type="text" indicatorId="'+indicatorId+'" value="'+columnVal+'" keyId="'+colKeyId+'" monthYear="'+monthYear+'" style="width:80px;text-align:right;" maxlength="10" onblur=textBlur("txtInd_'+columnNo + '_'+id +'");  />';
	
  		return '<input id="txtInd_'+columnNo + '_'+id +'"  type="text" indicatorId="'+indicatorId+'" value="'+columnVal+'" keyId="'+colKeyId+'" monthYear="'+monthYear+'" style="width:76px;text-align:right;" maxlength="10"  onfocus=textBlur1("txtInd_'+columnNo + '_'+id +'") onblur=textBlur("txtInd_'+columnNo + '_'+id +'");  />';
	/********/
	//}
}

function txtButton(id, options, rowObject)
{	
	
	var id=options.rowId;
	
	return '<input type="button" id="parm" class="easyui-button" value="..." style="height:20px; width:46px;" onclick="ParameterRpt(\''+id + '\');"/>';
}

function ParameterRpt(id)
{
	
var kpiMonth=getFieldValue("hdnKPIMonth");
//	alert("kpiMonth:"+kpiMonth);
var slctid= jQuery("#hdnselectid").val();
var arr=slctid.split('_');
var rowid=arr.pop();
//alert("id"+id);
//alert("rowid"+rowid);

if(id.length==0&&rowid.length==0)
	{
	
	alert("Please Select the Valid Cell to Enter Remarks");
	return false;
	}

	  if(rowid==id)
		  {
	if (kpiMonth.trim().length>0){
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',id);
		var IndicatiorName = rowData.IndicatiorName;
		var Frequency = rowData.Frequency;
		var Uom = rowData.Uom;
		var indicatorId = rowData.KinkKeyId;
		jQuery("#hdnKPIKeyid").val(indicatorId);
		var flid=jQuery("#frmKpiTlActualKk input[id='flid']").val();	
		//alert(flid);
		//jQuery("#hdnKPIKeyid").val();
		var formMode=jQuery("#txtFrmMode").val();
		//alert("indicatorId " +indicatorId);
		//var cKeyId=jQuery("#hdnKPIKeyid").val(indicatorId);
		var cKeyId = jQuery("#hdnKPIKeyid").val();
	   //alert("cKeyId12:" +cKeyId);
		if(cKeyId=='' ||cKeyId==' '||cKeyId=='undefined'){
			alert("Please Select the Valid Cell to Enter Remarks");
		}else{
			var filterString  = '&Indicator='+encodeURIComponent(cKeyId)+'&Frequency='+encodeURIComponent(Frequency)+"&Uom="+encodeURIComponent(Uom)+"&IndicatiorName="+encodeURIComponent(IndicatiorName);
			filterString += '&flid='+flid+'&kpiMonth='+kpiMonth;
			//filterString+="&mode="+formMode;
			
			LoadPopUp("divKpiRemarks","kpiRemarks_input.kpiActKk?q=2"+filterString,true,"40%","65%","15%","35%","","Remarks");
		}
	}
	else{
		alert("Select Month For Remarks");
	}
		  }

    else{
    	  
               
    	alert("Select Correct Remarks Button");
    	return false;
    }
	
}

/*function GridLoadCompleted(id)
{	
	//alert("grid Load Completed");
	jQuery("#grdKpiActualkk").setGridParam({onCellSelect:function(id,cellidx,cellvalue) {
			//alert("cellvalue.."+cellvalue+"..id.."+id+"..cellidx.."+cellidx);
			jQuery('#hdnSelectRow').val(id);
			jQuery('#hdnSelectCol').val(cellidx);
		}
	});
	
	
	var bdGridId = jQuery("#grdKpiActualkk").jqGrid('getDataIDs');	
	var cm = jQuery("#grdKpiActualkk").jqGrid("getGridParam", "colModel");
	//var calcType=jQuery("#grdKpiActualkk").getCell(rowid, (cm.length-1)).trim();
	var isChild="";
	var isTarget="";
	var controlId="";
	var gridval="";
	var isTargetVal="";
	var freq="";
	var level="0";	
	var formMode=jQuery("#txtFrmMode").val();

	for(var i=1;i<=bdGridId.length;i++)	
	{	
		var rowid=bdGridId[i-1];		
		if(i==3)
		{
			//jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,"Frequency","Weekly" );
		}
		isTarget=jQuery("#grdKpiActualkk").getCell(rowid, 4).trim();
		isChild=jQuery("#grdKpiActualkk").getCell(rowid, 5).trim();
		isManual=jQuery("#grdKpiActualkk").getCell(rowid,6).trim();
		isTargetVal=jQuery("#grdKpiActualkk").getCell(rowid, 11).trim();
		freq=jQuery("#grdKpiActualkk").getCell(rowid, 13).trim();
		level=jQuery("#grdKpiActualkk").getCell(rowid, 8).trim();
		
		if(isChild=="N"){
			//alert("isChild"+isChild);
			for(var j=1;j<=cm.length-1;j++)	
			{ 
				//alert("level"+level);
				if (level=="1"){
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#FFFA7F'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#FFFA7F');
					}
				}
				else if(level=="2"){
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#CE8A8A'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#CE8A8A');
					}
				}
				else if(level=="3"){
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#86C2D6'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#86C2D6');
					}
				}			
			}	
		}
		else if(isTarget=="Y" && isTargetVal=="Target" ){
			//alert("isTargetVal:"+isTargetVal+"formMode"+formMode);
			var controlval="";
			for(j=2;j<=cm.length-1;j++)	
			{
				controlId="txtInd_"+(j-1) + "_"+rowid;
				numericTextBox(controlId);
				//if(formMode=="view"){
				//	controlval=jQuery("#"+controlId).html();}			
				//else	
					controlval=jQuery("#"+controlId).val();	
				
				var keyid=jQuery("#"+controlId).attr('keyid');			
				gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
				jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,gridval,{'background-color':'#C7CEFD'});	 
				
				if(j>15){
					jQuery("#"+controlId).css('background-color', '#C7CEFD');
					numericTextBox(controlId);
					//jQuery("#"+controlId).css('background-color', '#AAAAAA');
					if(formMode!="target"){						
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');      
					}
				}
				//if(formMode=="view")
				//	jQuery("#"+controlId).html(controlval);			
				//else
					jQuery("#"+controlId).val(controlval);
				
				jQuery("#"+controlId).attr('keyid',keyid);	
			}	
		}
		
		else if(isTarget=="N" || (isTarget=="Y" && isTargetVal!="Target")){
			//alert("N:"+isTargetVal+"isTarget:"+isTarget+"formMode"+formMode);
			var currentDate =  getServerDateTime();
			for(j=16;j<=cm.length-1;j++)	
			{
				if(j>15){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					numericTextBox(controlId);
					jQuery("#"+controlId).attr('maxlength','0');
					jQuery("#"+controlId).attr('readonly','readonly');    
					var month="01-"+jQuery("#"+controlId).attr('monthyear');	
					//alert("month"+month);
					if(formMode!="target" ){											
						jQuery("#"+controlId).css('background-color', '#FFFFFF');
						jQuery("#"+controlId).removeAttr('readonly');    
						jQuery("#"+controlId).attr('maxlength', '10');						
						numericTextBox(controlId);	
						if (currentDate < convertStringToDate(month)){
							jQuery("#"+controlId).attr('maxlength','0');
							jQuery("#"+controlId).attr('readonly','readonly');    
						}	
					}	
				}
			}		
		}
		for(j=15;j<=cm.length-1;j++)	
		{ 	
			if(j>16){
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("dblclick", function(){
					jQuery('#dblColIndex').val("0")	;
					//if(j>17){	
						var cell = jQuery(this);
						var index = cell.parent('tr').children().index(cell);
						//alert(index);
						jQuery('#dblColIndex').val(index)	;	
					//}
				});		
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("click", function(){
					jQuery('#dblColIndex').val("0")	;
					//if(j>17){	
						var cell = jQuery(this);
						var index = cell.parent('tr').children().index(cell);
						//alert(index);
						jQuery('#dblColIndex').val(index)	;	
					//}
				});	
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("keydown", function(){					
					var cell = jQuery(this);
					var index = cell.parent('tr').children().index(cell);
					
					var sectId = jQuery("#frmKpiTlActualKk input[id='section']").val();
					//alert("sectId"+sectId);
				  	if (sectId.trim().length==0) {
				 		popupCommonErrorMsg("Select Functional Location");
				 		return false;
				 	}	
						
				});					
			}
		}	
		
		if (freq=="D" || freq=="Daily"){	
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if((vMonth !=null && vMonth !="") && vFrequency == "D" ){	
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					//alert("Month :" + month);
					numericTextBox(controlId);
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						
						if (month!=undefined && month.trim().length>4){
								var currentDate =  getServerDateTime();	
								
								if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('readonly','readonly');   
									jQuery("#"+controlId).attr('maxlength', '0');	
									
								}else{
									jQuery("#"+controlId).attr('maxlength','10');
									jQuery("#"+controlId).removeAttr('readonly');  
								}	
						 }else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
						}	
					}
					if(month == 'SumOrAvg'){
						jQuery("#"+controlId).attr('readonly','readonly');// alert("1" +controlId);
					}
				}
			}
			
		}
		if(freq=="W" || freq=="Weekly"){			
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if(vMonth !=null && vMonth !="" && vFrequency!=null && vFrequency != "" ){	
				for(j=15;j<=cm.length-1;j++)	
					{ 	
						if(j>16){	
							controlId="txtInd_"+(j-1) + "_"+rowid;
							var month=jQuery("#"+controlId).attr('monthyear');
							//alert("month :" +month);
							numericTextBox(controlId);
							if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
								if (month!=undefined && month.trim().length>0){
									if(month.substring(0, 2)=="07" ||month.substring(0, 2)=="14" ||month.substring(0, 2)=="21" ||month.substring(0, 2)=="28" ){
											var currentDate =  getServerDateTime();											
											if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
												jQuery("#"+controlId).attr('maxlength','0');
												jQuery("#"+controlId).attr('readonly','readonly');   
											}
											else{
												jQuery("#"+controlId).removeAttr('readonly');    
												jQuery("#"+controlId).attr('maxlength', '10');	
											}	
										}
										else{
											jQuery("#"+controlId).attr('maxlength','0');
											jQuery("#"+controlId).attr('readonly','readonly');  
										}
									}
								
							}						
						}
					}	
					
			}else{	
				//alert("Vmonth  Null");
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					if(j>16){	
						controlId="txtInd_"+(j-1) + "_"+rowid;
						jQuery("#"+controlId).attr('maxlength','0');
						jQuery("#"+controlId).attr('readonly','readonly'); 
												
					}
				}	
				
			}	
			
		}
		//
		if(freq=="X" || freq=="Fortnight"){			
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if(vMonth !=null && vMonth !="" && vFrequency!=null && vFrequency != "" ){	
				for(j=15;j<=cm.length-1;j++)	
					{ 	
						if(j>16){	
							controlId="txtInd_"+(j-1) + "_"+rowid;
							var month=jQuery("#"+controlId).attr('monthyear');
							//alert("month :" +month);
							numericTextBox(controlId);
							if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
								if (month!=undefined && month.trim().length>0){
									if(month.substring(0, 2)=="14" ||month.substring(0, 2)=="28"){
											var currentDate =  getServerDateTime();											
											if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
												jQuery("#"+controlId).attr('maxlength','0');
												jQuery("#"+controlId).attr('readonly','readonly');   
											}
											else{
												jQuery("#"+controlId).removeAttr('readonly');    
												jQuery("#"+controlId).attr('maxlength', '10');	
											}	
										}
										else{
											jQuery("#"+controlId).attr('maxlength','0');
											jQuery("#"+controlId).attr('readonly','readonly');  
										}
									}
								
							}						
						}
					}	
					
			}else{	
				//alert("Vmonth  Null");
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					if(j>16){	
						controlId="txtInd_"+(j-1) + "_"+rowid;
						jQuery("#"+controlId).attr('maxlength','0');
						jQuery("#"+controlId).attr('readonly','readonly'); 
												
					}
				}	
				
			}	
			
		}
		//
		if (freq=="Q" || freq=="Quarterly"){			
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>=16){	
					//alert(j);
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					numericTextBox(controlId);
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						if (month!=undefined && month.trim().length>0){
							
							if(month.substring(0, 3)=="JUN" ||month.substring(0, 3)=="SEP" ||month.substring(0, 3)=="DEC" ||month.substring(0, 3)=="MAR" ){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
							}
							else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
							}
							if(j==18 || j==19){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
								
							}
							
						}
					}							
				}
			}	
		}	
		if (freq=="H" || freq=="Half Yearly"){			
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>16){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					numericTextBox(controlId);
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						if (month!=undefined && month.trim().length>0){
							
							if(month.substring(0, 3)=="SEP" ||month.substring(0, 3)=="MAR"){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
							}
							else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
							}
							if(j==18 || j==19){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
								
							}
						}
					}							
				}
			}	
		}	
		if (isManual!="M"){
			//alert(isManual);
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>16){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					jQuery("#"+controlId).attr('maxlength','0');	
					jQuery("#"+controlId).attr('readonly','readonly');    
				}
			}
		}	
			
	}
	/*
	for(var i=1;i<=bdGridId.length;i++){	
		
		var rowid=bdGridId[i-1];	
		//alert("Row Id:" + rowid);
		isTarget=jQuery("#grdKpiActualkk").getCell(rowid, 4).trim();
		//alert("isTarget:" + isTarget);
		isChild=jQuery("#grdKpiActualkk").getCell(rowid, 5).trim();
		//alert("Row Id:" + rowid);
		isManual=jQuery("#grdKpiActualkk").getCell(rowid,6).trim();
		//alert("isChild:" + isChild);
		isTargetVal=jQuery("#grdKpiActualkk").getCell(rowid, 11).trim();
		//alert("isTargetVal:" + isTargetVal);
		freq=jQuery("#grdKpiActualkk").getCell(rowid, 13).trim();
		//alert("freq:" + freq);
		level=jQuery("#grdKpiActualkk").getCell(rowid, 8).trim();
		//alert("level:" + level);
		var calcType=jQuery("#grdKpiActualkk").getCell(rowid, (cm.length-1)).trim();
		//alert("calcType: " + calcType);	
		var sumoravg = 0; 	
		for(j=19;j<=cm.length-1;j++){
			if((freq.trim() == "Daily" || freq.trim() == "Weekly" || freq.trim() == "Fortnight") && isChild == 'Y'){
			
			
				controlId="txtInd_"+j + "_"+rowid;			
				cValue=jQuery('#' + controlId).val();
			//	alert("cValue :" +controlId +"  = " +cValue);
				cKeyId=jQuery('#' + controlId).attr('keyId');
				//alert("cKeyId: " +cKeyId);
				cMonthYear=jQuery('#' + controlId).attr('monthYear');
				//alert("cMonthYear Test**: " +cMonthYear);
				if(cValue.length  != 'undefined' || cValue!="" || cValue!=" "){
					sumoravg +=parseInt(cValue);
				}
			
			}
			
		}
		//alert("sumoravg :" + sumoravg);
	}

}*/

function GridLoadCompleted(id)
{	
	//alert("grid Load Completed");
	jQuery("#grdKpiActualkk").setGridParam({onCellSelect:function(id,cellidx,cellvalue) {
			//alert("cellvalue.."+cellvalue+"..id.."+id+"..cellidx.."+cellidx);
			jQuery('#hdnSelectRow').val(id);
			jQuery('#hdnSelectCol').val(cellidx);
		}
	});
	
	
	var bdGridId = jQuery("#grdKpiActualkk").jqGrid('getDataIDs');	
	var cm = jQuery("#grdKpiActualkk").jqGrid("getGridParam", "colModel");
	//var calcType=jQuery("#grdKpiActualkk").getCell(rowid, (cm.length-1)).trim();
	var isChild="";
	var isTarget="";
	var controlId="";
	var gridval="";
	var isTargetVal="";
	var freq="";
	var level="0";	
	var formMode=jQuery("#txtFrmMode").val();

	for(var i=1;i<=bdGridId.length;i++)	
	{	
		var rowid=bdGridId[i-1];		
		if(i==3)
		{
			//jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,"Frequency","Weekly" );
		}
		isTarget=jQuery("#grdKpiActualkk").getCell(rowid, 4).trim();
		isChild=jQuery("#grdKpiActualkk").getCell(rowid, 5).trim();
		isManual=jQuery("#grdKpiActualkk").getCell(rowid,6).trim();
		isTargetVal=jQuery("#grdKpiActualkk").getCell(rowid, 11).trim();
		freq=jQuery("#grdKpiActualkk").getCell(rowid, 13).trim();
		level=jQuery("#grdKpiActualkk").getCell(rowid, 8).trim();
		//alert("The level"+level);
		if(isChild=="N"){
			//alert("isChild"+isChild);
			for(var j=1;j<=cm.length-1;j++)	
			{ 
				//alert("level"+level);
				if (level=="1"){
					//alert("level:1:"+level);
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#FFFA7F'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						jQuery("#"+controlId).on("mousedown keydown click " , function(e){
							e.stopPropagation();
							});
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#FFFA7F');
					}
				}
				else if(level=="2"){
					//alert("level:2:"+level);
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#CE8A8A'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						jQuery("#"+controlId).on("mousedown keydown click " , function(e){
							e.stopPropagation();
							});
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#CE8A8A');
					}
				}
				else if(level=="3"){
					//alert("level:3:"+level);
					gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
					jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,"",{'background-color':'#86C2D6'});
					if(j>15){
						controlId="txtInd_"+(j-1) + "_"+rowid;
						numericTextBox(controlId);
						jQuery("#"+controlId).on("mousedown keydown click " , function(e){
							e.stopPropagation();
							});
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');   
						jQuery("#"+controlId).css('background-color', '#86C2D6');
					}
				}			
			}	
		}
		else if(isTarget=="Y" && isTargetVal=="Target" ){
			//alert("isTargetVal:"+isTargetVal+"formMode"+formMode);
			var controlval="";
			for(j=2;j<=cm.length-1;j++)	
			{
				controlId="txtInd_"+(j-1) + "_"+rowid;
				numericTextBox(controlId);
				jQuery("#"+controlId).on("mousedown keydown click " , function(e){
					e.stopPropagation();
					});
				//if(formMode=="view"){
				//	controlval=jQuery("#"+controlId).html();}			
				//else	
					controlval=jQuery("#"+controlId).val();	
				
				var keyid=jQuery("#"+controlId).attr('keyid');			
				gridval=jQuery("#grdKpiActualkk").getCell(rowid, j).trim();
				jQuery("#grdKpiActualkk").jqGrid('setCell',rowid,j,gridval,{'background-color':'#C7CEFD'});	 
				
				if(j>15){
					jQuery("#"+controlId).css('background-color', '#C7CEFD');
					numericTextBox(controlId);
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					//jQuery("#"+controlId).css('background-color', '#AAAAAA');
					if(formMode!="target"){						
						//jQuery("#"+controlId).attr('disabled', 'disabled');
						jQuery("#"+controlId).attr('maxlength', '0');
						jQuery("#"+controlId).attr('readonly','readonly');      
					}
				}
				//if(formMode=="view")
				//	jQuery("#"+controlId).html(controlval);			
				//else
					jQuery("#"+controlId).val(controlval);
				
				jQuery("#"+controlId).attr('keyid',keyid);	
			}	
		}
		
		else if(isTarget=="N" || (isTarget=="Y" && isTargetVal!="Target")){
			//alert("N:"+isTargetVal+"isTarget:"+isTarget+"formMode"+formMode);
			var currentDate =  getServerDateTime();
			for(j=16;j<=cm.length-1;j++)	
			{
				if(j>15){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					numericTextBox(controlId);
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					jQuery("#"+controlId).attr('maxlength','0');
					jQuery("#"+controlId).attr('readonly','readonly');    
					var month="01-"+jQuery("#"+controlId).attr('monthyear');	
					//alert("month"+month);
					if(formMode!="target" ){											
						jQuery("#"+controlId).css('background-color', '#FFFFFF');
						jQuery("#"+controlId).removeAttr('readonly');    
						jQuery("#"+controlId).attr('maxlength', '10');						
						numericTextBox(controlId);
						jQuery("#"+controlId).on("mousedown keydown click " , function(e){
							e.stopPropagation();
							});
						if (currentDate < convertStringToDate(month)){
							jQuery("#"+controlId).attr('maxlength','0');
							jQuery("#"+controlId).attr('readonly','readonly');    
						}	
					}	
				}
			}		
		}
		for(j=15;j<=cm.length-1;j++)	
		{ 	
			if(j>16){
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("dblclick", function(){
					jQuery('#dblColIndex').val("0")	;
					//if(j>17){	
						var cell = jQuery(this);
						var index = cell.parent('tr').children().index(cell);
						//alert(index);
						jQuery('#dblColIndex').val(index)	;	
					//}
				});		
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("click", function(){
					jQuery('#dblColIndex').val("0")	;
					//if(j>17){	
						var cell = jQuery(this);
						var index = cell.parent('tr').children().index(cell);
						//alert(index);
						jQuery('#dblColIndex').val(index)	;	
					//}
				});	
				jQuery("#grdKpiActualkk tr:nth-child(" +(i+1) + ") td:nth-child(" +(j+1) + ")").bind("keydown", function(){					
					var cell = jQuery(this);
					var index = cell.parent('tr').children().index(cell);
					
					var sectId = jQuery("#frmKpiTlActualKk input[id='section']").val();
					//alert("sectId"+sectId);
				  	if (sectId.trim().length==0) {
				 		popupCommonErrorMsg("Select Functional Location");
				 		return false;
				 	}	
						
				});					
			}
		}	
		
		if (freq=="D" || freq=="Daily"){	
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if((vMonth !=null && vMonth !="") && vFrequency == "D" ){	
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					//alert("Month :" + month);
					numericTextBox(controlId);
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						
						if (month!=undefined && month.trim().length>4){
								var currentDate =  getServerDateTime();	
								
								if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('readonly','readonly');   
									jQuery("#"+controlId).attr('maxlength', '0');	
									
								}else{
									jQuery("#"+controlId).attr('maxlength','10');
									jQuery("#"+controlId).removeAttr('readonly');  
								}	
						 }else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
						}	
					}
					if(month == 'SumOrAvg'){
						jQuery("#"+controlId).attr('readonly','readonly');// alert("1" +controlId);
					}
				}
			}
			
		}
		if(freq=="W" || freq=="Weekly"){			
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if(vMonth !=null && vMonth !="" && vFrequency!=null && vFrequency != "" ){	
				for(j=15;j<=cm.length-1;j++)	
					{ 	
						if(j>16){	
							controlId="txtInd_"+(j-1) + "_"+rowid;
							var month=jQuery("#"+controlId).attr('monthyear');
							//alert("month :" +month);
							numericTextBox(controlId);
							jQuery("#"+controlId).on("mousedown keydown click " , function(e){
								e.stopPropagation();
								});
							if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
								if (month!=undefined && month.trim().length>0){
									if(month.substring(0, 2)=="07" ||month.substring(0, 2)=="14" ||month.substring(0, 2)=="21" ||month.substring(0, 2)=="28" ){
											var currentDate =  getServerDateTime();											
											if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
												jQuery("#"+controlId).attr('maxlength','0');
												jQuery("#"+controlId).attr('readonly','readonly');   
											}
											else{
												jQuery("#"+controlId).removeAttr('readonly');    
												jQuery("#"+controlId).attr('maxlength', '10');	
											}	
										}
										else{
											jQuery("#"+controlId).attr('maxlength','0');
											jQuery("#"+controlId).attr('readonly','readonly');  
										}
									}
								
							}						
						}
					}	
					
			}else{	
				//alert("Vmonth  Null");
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					if(j>16){	
						controlId="txtInd_"+(j-1) + "_"+rowid;
						jQuery("#"+controlId).attr('maxlength','0');
						jQuery("#"+controlId).attr('readonly','readonly'); 
												
					}
				}	
				
			}	
			
		}
		//
		if(freq=="X" || freq=="Fortnight"){			
			var vMonth= getFieldValue("cmbMonth");
			var vFrequency= getFieldValue("cmbKpiFrequency");			
			if(vMonth !=null && vMonth !="" && vFrequency!=null && vFrequency != "" ){	
				for(j=15;j<=cm.length-1;j++)	
					{ 	
						if(j>16){	
							controlId="txtInd_"+(j-1) + "_"+rowid;
							var month=jQuery("#"+controlId).attr('monthyear');
							//alert("month :" +month);
							numericTextBox(controlId);
							jQuery("#"+controlId).on("mousedown keydown click " , function(e){
								e.stopPropagation();
								});
							if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
								if (month!=undefined && month.trim().length>0){
									if(month.substring(0, 2)=="14" ||month.substring(0, 2)=="28"){
											var currentDate =  getServerDateTime();											
											if (currentDate < convertStringToDate(month) && (isTargetVal=="Actual" && formMode=="actual") ){
												jQuery("#"+controlId).attr('maxlength','0');
												jQuery("#"+controlId).attr('readonly','readonly');   
											}
											else{
												jQuery("#"+controlId).removeAttr('readonly');    
												jQuery("#"+controlId).attr('maxlength', '10');	
											}	
										}
										else{
											jQuery("#"+controlId).attr('maxlength','0');
											jQuery("#"+controlId).attr('readonly','readonly');  
										}
									}
								
							}						
						}
					}	
					
			}else{	
				//alert("Vmonth  Null");
				for(j=15;j<=cm.length-1;j++)	
				{ 	
					if(j>16){	
						controlId="txtInd_"+(j-1) + "_"+rowid;
						jQuery("#"+controlId).attr('maxlength','0');
						jQuery("#"+controlId).attr('readonly','readonly'); 
												
					}
				}	
				
			}	
			
		}
		//
		if (freq=="Q" || freq=="Quarterly"){			
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>=16){	
					//alert(j);
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					numericTextBox(controlId);
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						if (month!=undefined && month.trim().length>0){
							
							if(month.substring(0, 3)=="JUN" ||month.substring(0, 3)=="SEP" ||month.substring(0, 3)=="DEC" ||month.substring(0, 3)=="MAR" ){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
							}
							else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
							}
							if(j==18 || j==19){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
								
							}
							
						}
					}							
				}
			}	
		}	
		if (freq=="H" || freq=="Half Yearly"){			
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>16){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					var month=jQuery("#"+controlId).attr('monthyear');
					numericTextBox(controlId);
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					if ((isTargetVal=="Target" && formMode=="target")||(isTargetVal=="Actual" && formMode=="actual" )){
						if (month!=undefined && month.trim().length>0){
							
							if(month.substring(0, 3)=="SEP" ||month.substring(0, 3)=="MAR"){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
							}
							else{
								jQuery("#"+controlId).attr('maxlength','0');
								jQuery("#"+controlId).attr('readonly','readonly');  
							}
							if(j==18 || j==19){
								var currentDate =  getServerDateTime();	
								if (currentDate < convertStringToDate("01-"+month) && (isTargetVal=="Actual" && formMode=="actual") ){
									jQuery("#"+controlId).attr('maxlength','0');
									jQuery("#"+controlId).attr('readonly','readonly');   
								}
								else{
									jQuery("#"+controlId).removeAttr('readonly');    
									jQuery("#"+controlId).attr('maxlength', '10');	
								}	
								
							}
						}
					}							
				}
			}	
		}	
	 	if (isManual!="M"){
			//alert(isManual);
			for(j=15;j<=cm.length-1;j++)	
			{ 	
				if(j>16){	
					controlId="txtInd_"+(j-1) + "_"+rowid;
					jQuery("#"+controlId).on("mousedown keydown click " , function(e){
						e.stopPropagation();
						});
					jQuery("#"+controlId).attr('maxlength','0');	
					jQuery("#"+controlId).attr('readonly','readonly');    
				}
			}
		}	 
			
	}
	
	//alert("bdGridId"+bdGridId);
	for(var i=1;i<=bdGridId.length;i++){	
		
		//for(var i=1;i<=2;i++){	
		
		var rowid=bdGridId[i-1];	
		//alert("Row Id:" + rowid);
		isTarget=jQuery("#grdKpiActualkk").getCell(rowid, 4).trim();
	//	alert("isTarget:" + isTarget);
		isChild=jQuery("#grdKpiActualkk").getCell(rowid, 5).trim();
		//alert("Row Id:" + rowid);
		isManual=jQuery("#grdKpiActualkk").getCell(rowid,6).trim();
	//	alert("isChild:" + isChild);
		isTargetVal=jQuery("#grdKpiActualkk").getCell(rowid, 11).trim();
	//	alert("isTargetVal:" + isTargetVal);
		freq=jQuery("#grdKpiActualkk").getCell(rowid, 13).trim();
	//	alert("freq:" + freq);
		level=jQuery("#grdKpiActualkk").getCell(rowid, 8).trim();
	//	alert("level:" + level);
		var calcType=jQuery("#grdKpiActualkk").getCell(rowid, (cm.length-1)).trim();
	//	alert("calcType: " + calcType);	
	//	alert("cm.length>>>"+cm.length);
		var sumoravg = 0; 	
		for(j=19;j<=cm.length-3;j++){
			if((freq.trim() == "Daily" || freq.trim() == "Weekly" || freq.trim() == "Fortnight") && isChild == 'Y'){
			
				
			
				controlId="txtInd_"+j + "_"+rowid;	
				//alert("controlId>>>>"+controlId);
			//	alert("rowid>>"+rowid);
				cValue=jQuery('#' + controlId).val();
				//alert(cValue.length);
				
				
				
				cKeyId=jQuery('#' + controlId).attr('keyId');
				//alert("cKeyId: " +cKeyId);
				cMonthYear=jQuery('#' + controlId).attr('monthYear');
			//	alert("cMonthYear Test**: " +cMonthYear);
				if(cValue.length  != 'undefined' || cValue!="" || cValue!=" "){
					//alert("cValue in side the if"+cValue.length);
					if(cValue.length==0)
					{
					cValue=0;
					}
					sumoravg=sumoravg+parseInt(cValue);
				//	alert("sumoravg"+sumoravg);
					if(sumoravg==null)
						{
						sumoravg=0;
						}
					//alert("sumorAvg");
					if(calcType=="S" || calcType=="A")
					{	
					if(cMonthYear=="SumOrAvg")
						{
						jQuery('#' + controlId).val(sumoravg);
						}
					}
				}
			
			}
			
		}
		//alert("sumoravg :" + sumoravg);
	}

}





function grdKpiActualkk_keypress(keycode,iRow,iCol)
{
}
/*
function docDoubleClick(id){	
	//alert("Double Click function");
	var colIndex=jQuery('#dblColIndex').val()	;
	jQuery('#dblRowIndex').val(id);
	var isChild=jQuery("#grdKpiActualkk").getCell(id, 5).trim();
	//alert(isChild);
	var freq=jQuery("#grdKpiActualkk").getCell(id, 13).trim();

 	 if (isChild=="N"){
 	 	return false;
 	 }
	 var formMode=jQuery("#grdKpiActualkk").getCell(id, "EntryType").trim();
	 //alert("2");
	 if(formMode=="Target"){
	  	return false;
	 }
	
	 if (freq=="D" || freq=="Daily"){
		if(colIndex>19){
			//alert("col"+colIndex+"row"+id);
			
			var year=getFieldValue('cmbKaukCalendaryear','frmKpiTlActualKk');
			var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
			var flid = jQuery("#frmKpiTlActualKk input[id='flid']").val();
			var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
			var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
			var pillarId=getFieldValue("cmbKaukPillarid");		
			var controlId="txtInd_"+(colIndex-1) + "_"+id;
			var MonthYear=jQuery('#' + controlId).attr('monthYear');				
			//alert("indicatorId:"+indicatorId);
			var keyId=jQuery('#' + controlId).attr('keyid');
			var excellenceVal=jQuery('#txtInd_17_' + id).val();
			var benchMarkVal=jQuery('#txtInd_18_' + id).val();
			var indicatorId=jQuery("#grdKpiActualkk").getCell(id, 9).trim();
			var isTarget=jQuery("#grdKpiActualkk").getCell(id, 4).trim();
			var isChild=jQuery("#grdKpiActualkk").getCell(id, 5).trim();
			var targetVal=jQuery("#grdKpiActualkk").getCell(id, 11).trim();
			var kaukMode=jQuery("#txtFrmMode").val();
			var Uom = jQuery("#grdKpiActualkk").getCell(id, 13).trim();
			//alert(Uom );
			if(sectionId!="" && sectionId!=null ){
				var isActual="Y";
				var params="";
				if(isTarget=="Y" && targetVal=="Target" ){
					var isActual="N";
				}				
				params="&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&pillarId="+pillarId+"&flid="+flid;
				params=params+"&year="+year+"&monthYear=01-"+MonthYear+"&indicatorId="+indicatorId;
				params=params+"&isActual="+isActual+"&keyId="+keyId+"&kaukMode="+kaukMode+"&excellenceVal="+excellenceVal+"&benchMarkVal="+benchMarkVal;
				params=params+"&Uom="+Uom;
				LoadPopUp("divKpiTlActualKkDaily", "kpiActualKkDaily_view.kpiActKk?q=2"+params, true,"75%","85%","0px","8%", "multiSelectOk_Callback","KPI Actual Daily Entry",false);
			}
			else{
				popupCommonErrorMsg("Select Functional Location");
			}
		}
	}	
}*/
function divKpiTlActualKkDaily_onClose(){return true;}
function multiSelectOk_Callback(args){	}
function frmKpiTlActualKkcmbKaukCalendaryear_onLoadSuccess(){}
function frmKpiTlActualKk_beforeRefreshCallback(){}
function GridLoadCompleted_afterLoad(){}

function frmKpiTlActualKk_beforeSubmit(){
	//alert("beforeSubmit");
	var formMode=jQuery("#txtFrmMode").val();
	var type=getFieldValue("txtFormType");
	flid=jQuery("#hdnlogFlid").val();
	//alert(flid);
	 var year =getFieldValue("cmbKaukCalendaryear");
	// alert("Year:::"+year);
	// var vMonth= getFieldValue("cmbMonth");
	 //alert("vMonth:::"+vMonth);
    var CurrDate=jQuery("#hdnkpiDate").val();
    var vFreq= getFieldValue("cmbKpiFrequency");
 //   alert("vFreq::::"+vFreq);
	var CurrMonthYear=jQuery("#hdnCurrMonthYear").val();
//	alert("CurrYear:::"+CurrMonthYear);
	
	if(formMode=="view"){return false;}
	else{
		var section = jQuery("#frmKpiTlActualKk input[id='section']").val();
		if(section!="" && section!=null){
			
		}
		else{
			alert("Select Functional Location");
			return false;
		}	
		var convertStr = convertControlsToJSONString();
		if (convertStr.length<2) {
			popupCommonErrorMsg("No Records to Save...");
			return false;
		}
		else {
			var gridData  = '&type='+type+"&flid="+flid+"&year="+year+"&CurrDate="+CurrDate+"&formMode="+formMode+"&vFreq="+vFreq+"&CurrMonthYear="+CurrMonthYear+'&multiplemethods='+convertStr;	
			return gridData;
		}
	}			
}

function convertControlsToJSONString(){
	//alert("Why1");
	var year=getFieldValue('cmbKaukCalendaryear','frmKpiTlActualKk');
	var flId = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
	var pillarId=getFieldValue("cmbKaukPillarid");	
	var month= getFieldValue("cmbMonth");
	var frequency= getFieldValue("cmbKpiFrequency");
	//alert("frequency " +frequency);
	var bdGridId = jQuery("#grdKpiActualkk").jqGrid('getDataIDs');	
	var cm = jQuery("#grdKpiActualkk").jqGrid("getGridParam", "colModel");
	var rowid="";
	var controlId="";
	var cValue="";
	var cKeyId="";
	var cMonthYear="";
	var cIndicatorId="";
	var isTarget="";
	var isTargetVal="";	
	var excellenceVal="";
	var benchMarkVal="";	
	var jsonArrO='[';
	var rowNumber=0;
	var rowId ="";
	var vFreq ="";
	var frquency="";
	var prevcMonthYear ="";
	var Sumandavg="";
	for(var i=1;i<=bdGridId.length;i++)	
	{	
		//alert("bdGridId.length: " +bdGridId.length);
		//alert("cm.length: " +cm.length);
		rowid=bdGridId[i-1];
		isTarget=jQuery("#grdKpiActualkk").getCell(rowid, 4).trim();
		isTargetVal=jQuery("#grdKpiActualkk").getCell(rowid, 11).trim();
		cIndicatorId=jQuery("#grdKpiActualkk").getCell(rowid, 9).trim();
		frquency=jQuery("#grdKpiActualkk").getCell(rowid, 13).trim();
		excellenceVal=jQuery('#txtInd_17_' + rowid).val();
		benchMarkVal=jQuery('#txtInd_18_' + rowid).val();
		//Sumandavg=jQuery("#grdKpiActualkk").getCell(rowid, 50).trim();
		//alert(Sumandavg);
		//var calcType=jQuery("#grdKpiActualkk").getCell(rowid, (cm.length-1)).trim();
		//alert("calcType: " + '#txtInd_' + (cm.length-4) +'_' + rowid);		
		//var totalValueMonthYear=jQuery('#txtInd_' + 20 +'_' + 2).val();
		//alert("totalValueMonthYear Test: " +totalValueMonthYear);
		//var colidofsumavg = cm.length-2;
		//alert("colidofsumavg: " +colidofsumavg);
		//var totValue=0;
		for(var j=19;j<=cm.length-1;j++)	
		{	
		   
			controlId="txtInd_"+j + "_"+rowid;			
			cValue=jQuery('#' + controlId).val();
			cKeyId=jQuery('#' + controlId).attr('keyId');
			cMonthYear=jQuery('#' + controlId).attr('monthYear');
			if(cMonthYear=="SumOrAvg")
				{
				 break;
				}
			//alert("cMonthYear Test**: " +cMonthYear);
			if(cKeyId!=null && cKeyId!="")
			{	
				if(cValue.length==0 || cValue=="" || cValue==" "){
					cValue = "0";
				}
			}
			if(cValue!=null && cValue!=""){
				
				jsonArrO += '{';				
				jsonArrO += '"txtKaukKeyid":"' + cKeyId +'",';
				jsonArrO += '"txtKaukIndicatorid":"' + cIndicatorId +'",';
				jsonArrO += '"txtKaukDeptid":"' + flId +'",';
				jsonArrO += '"txtKaukFactoryid":"' + factId +'",'; 
				jsonArrO += '"txtKaukSectionid":"' + sectionId +'",'; 
				jsonArrO += '"txtKaukCellid":"' + cellId +'",'; 
				jsonArrO += '"cmbKaukPillarid":"' + pillarId +'",'; 
				jsonArrO += '"txtKaukCalendaryear":"' + year +'",'; 
				//alert("Month:" +month);
				if(month != null || month != "" )
				
				{				
					//alert("inside the if");
					if(cMonthYear.length<5)
						cMonthYear = 'JAN-'+cMonthYear;
					
					//alert("cMonthYear"+);
					jsonArrO += '"txtKaukMonthyear":"01-' + cMonthYear +'",'; 
					jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
					jsonArrO += '"txtKaukFreqtype":"M",'; 
					jsonArrO += '"txtKaukStatus":"P",'; 
					if(cMonthYear == "SumOrAvg")
					{
						//alert(frequency+"BEFORE SUBMIT");
						//alert("prevcMonthYear"+prevcMonthYear);
				        
						if(frequency=="D")
						{
							
						break;
						jsonArrO += '"txtKaukMonthyear":"' +prevcMonthYear +'",'; 
						//jsonArrO += '"txtKaukMonthyear":"01-' + cMonthYear +'",'; 
						jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
						jsonArrO += '"txtKaukFreqtype":"D",'; 
						jsonArrO += '"txtKaukStatus":"P",'; 
						}
					    else{
					    jsonArrO += '"txtKaukMonthyear":"' +prevcMonthYear +'",'; 
					    jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
					    jsonArrO += '"txtKaukFreqtype":"M",'; 
					    jsonArrO += '"txtKaukStatus":"P",'; 
					}
					}
					//if(isTarget=="Y" || isTargetVal=="Target" ){
				}else{
					//alert("cMonthYear :" +cMonthYear);
					//alert("inside the else");
					if(cMonthYear.length==11){						
						jsonArrO += '"txtKaukMonthyear":"' + cMonthYear.substring(4,15) +'",'; 
						//alert("j: " + j+ " cValue:" +cValue);
					//	totValue += parseInt(cValue);							
						jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
						jsonArrO += '"txtKaukFreqtype":"D",'; 
						jsonArrO += '"txtKaukStatus":"P",'; 
						
						
					}else if(cMonthYear == "SumOrAvg"){
						//alert("cMonthYear Test: " +cMonthYear);
						//alert("totValue Test: " +cValue);
						//alert("prevcMonthYear: " +prevcMonthYear);
						
						//if(colidofsumavg == j){
							//alert("colidofsumavg: " +colidofsumavg);
						
							if(frequency=="Daily")
								{
								//jsonArrO += '"txtKaukMonthyear":"' +prevcMonthYear +'",'; 
								jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
								jsonArrO += '"txtKaukFreqtype":"D",'; 
								jsonArrO += '"txtKaukStatus":"P",'; 
								}
							else{
							jsonArrO += '"txtKaukMonthyear":"' +prevcMonthYear +'",'; 
							jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
							jsonArrO += '"txtKaukFreqtype":"M",'; 
							jsonArrO += '"txtKaukStatus":"P",'; 
							}
							//alert(jsonArrO);
						//}
					
					}
				}
				if(isTargetVal=="Target" ){
					jsonArrO += '"txtKaukIsactual":"N",'; 
					jsonArrO += '"txtKaukKeyid":"' + cKeyId +'",';
				}
				else{
					jsonArrO += '"txtKaukIsactual":"Y",'; 
				}				
				jsonArrO += '"txtKaukExcellencevalue":"' + excellenceVal +'",'; 
				jsonArrO += '"txtKaukBenchmarkvalue":"' + benchMarkVal +'",'; 				
				jsonArrO += '"txtKaukActive":"Y",'; 	
							
				jsonArrO = jsonArrO.slice(0, -1) + "},";
				
				//for rowid
				var rowIndex = jQuery('#hdnSelectRow').val();
		    	var colIndex = jQuery('#hdnSelectCol').val();
		    	//alert('rowIndex'+rowIndex +' colIndex'+colIndex);
		    	//alert('colIndex'+colIndex);
		    	rowNumber = parseInt(rowNumber) + 1;
		    	if (parseInt(rowIndex)==parseInt(i) && parseInt(colIndex)==parseInt(j)) {
					rowId = rowNumber;
					//alert('rowId'+rowId);
				}
			}
			prevcMonthYear =cMonthYear;
			//alert('prevcMonthYear'+prevcMonthYear);
			}
			   
	}		
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	
	//alert(rowId);
	return jsonArrO+"&rowId="+rowId; 	
}

/* function  frmKpiTlActualKkcmbKaukCalendaryear_onSelect(record)
{
	fnClear1();
} */


function frmKpiTlActualKkcmbKaukCalendaryear_onSelect(record)
{
    if(record && record.id && record.id.trim().length > 0) {
        // Set the value first
        jQuery('#cmbKaukCalendaryear').combobox('setValue', record.id);
    }
    
   
        fnClear1();
   
}

//changes
function  frmKpiTlActualKkcmbKpiFrequency_onSelect(record)
{
	    var mode= jQuery('#txtFrmMode').val();
	    
		//var vFreq= getFieldValue("cmbKpiFrequency");
		var vFreq= record.id;
		
		var rowIndex = jQuery('#hdnSelectRow').val();
		var colIndex = jQuery('#hdnSelectCol').val();
		var txtind="#txtInd_"+colIndex+"_"+rowIndex;
	    var valueofcol=jQuery(txtind).val();
		
			if(vFreq=='D'){
				
			//	disableUIButton("btnGraphPop");
				fillWithCurrentMonth("cmbMonth");
				/* jQuery('#cmbMonth').datebox({  
					formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				}); */
				
				formatMonthBox("cmbMonth","Mon-YYYY");
			
				//enableFields('cmbMonth');
		        //var controlId="txtInd_"+(j-1) + "_"+rowid;
				var currentDate =  getServerDateTime();	
				//alert("currentDate:" +currentDate) ;
				var monthYear=  getMonthStringFromInt(currentDate.getMonth())+'-'+currentDate.getFullYear();
				//alert("monthYear " +monthYear);
				jQuery('#cmbMonth').datebox('setValue',monthYear);
				enableFields('cmbMonth');
				enableUIButton("btnGraphDailyPop");
			//	disableUIButton("btnGraphPop");
			//	jQuery('#hdnSelectRow').val("");

				
                if((mode=='view')||(valueofcol==null)||(valueofcol=='')||(valueofcol=="")){
					var result = confirm("Do You Change the  Frequency ?");
				//	alert("D"+result);
					if (result == true)
						{
						jQuery('#cmbKpiFrequency').combobox('setValue',"D");
					//	jQuery("#lblTask").val(" ");
						fnClear1();
						
						return true;
						
						}
						
					else {
						
						jQuery('#cmbKpiFrequency').combobox('setValue',"M");
			           	}
				}
			    // jQuery('#hdnSelectRow').val("");
					else if(valueofcol!=null){
						
					//alert("rowIndex"+rowIndex);
			//		var txtind="#txtInd_"+colIndex+"_"+rowIndex;
				//    var valueofcol=jQuery(txtind).val();
						
						
                if((valueofcol==null)||(valueofcol=='')||(valueofcol=="")){
							var result = confirm("Do You Change the  Frequency ?");
							//alert(result);
							if (result == true)
								{
								jQuery('#cmbKpiFrequency').combobox('setValue',"D");
							//	jQuery("#lblTask").val(" ");
								fnClear1();
								
								return true;
								
								}
								
							else {
								
								jQuery('#cmbKpiFrequency').combobox('setValue',"M");
					           	}
						}
					 else{
					     var result = confirm("Do You want to save the data?");
						
						if (result == false)
							{
							
							}
							
						else {
						
						//	jQuery("#lblTask").val(" ");
							saveForm('frmKpiTlActualKk','kpiActualKkActual_save.kpiActKk');
						}
					}
					
					}
			//jQuery('#hdnSelectRow').val("");
		}else{
			if(vFreq=='M')
				//var txtind="#txtInd_"+colIndex+"_"+rowIndex;
		 //   var valueofcol=jQuery(txtind).val();
				disableUIButton("btnGraphDailyPop");

                if((mode=='view')||(valueofcol==null)||(valueofcol=='')||(valueofcol=="")){
					var result = confirm("Do You Change the  Frequency ?");
					
					
					if (result == true)
						{
						

						
						jQuery('#cmbKpiFrequency').combobox('setValue',"M");
						fnClear1();

					//	jQuery("#lblTask").val(" ");
						return true;
						}
						
					else {
					//	return false;
						jQuery('#cmbKpiFrequency').combobox('setValue',"D");
			           	}
				}
					else if(valueofcol!=null){
						
						var result = confirm("Do You want to save the data?");
						
						if (result == false)
							{
							
							}
							
						else {
							

						
						//	var url = "SkillIndexListDetl_remove.sirp?q&keyid="+keyid;
							//var formId = jQuery('#submitForm').val();
							//saveForm(formId,url);
							saveForm('frmKpiTlActualKk','kpiActualKkActual_save.kpiActKk');
							//jQuery("#lblTask").val(" ");
						}
					}
			jQuery('#hdnSelectRow').val("");
			jQuery("#cmbMonth").datebox("clear");
			readOnlyFields('cmbMonth');
			enableUIButton("btnGraphPop");
			disableUIButton("btnGraphDailyPop");
			jQuery('#hdnSelectRow').val("");
		}
		jQuery('#hdnSelectRow').val('');
		//fnClear1();
	

}
function  cmbMonth_onSelect(record)
{
	 //alert("reco " + record);
	    var monthValue = FormatMonthfromISD(record);
	    //alert("recof " + monthValue);
	    fnClear1(monthValue, null, null, null);
	//fnClear1();
}
function   cmbMonth_onClear(record)
{
	  var monthValue = "";
	    //alert("recof " + monthValue);
	    fnClear1(monthValue, null, null, null);
	//fnClear1();
}
function  frmKpiTlActualKkcmbKaukPillarid_onSelect(record)
{
	 var PillaridValue =record.id;
	 fnClear1(null, PillaridValue, null, null);
	//fnClear1();
}
function  frmKpiTlActualKkcmbKaukPillarid_onClear(record)
{
	//alert("frmKpiTlActualKkcmbMonth_onClear");
	 var PillaridValue ="";
	 fnClear1(null, PillaridValue, null, null);
	//fnClear1();
}
function monthCalendarDoubleClickSuccess(result){
	convertJsongrdKpiActualkkToControl(result);
}
/*
function fnClear()
{	
	var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
	var flId = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	var pillarId=getFieldValue("cmbKaukPillarid");
	var year = 	getFieldValue('cmbKaukCalendaryear','frmKpiTlActualKk');
	var frequency=getFieldValue("cmbKpiFrequency");
	
	
	var frmMode=jQuery("#txtFrmMode").val();
	var isActual="Y";
	if(frmMode=="target")
		isActual="N";
	var type=getFieldValue("txtFormType");
	var dataStr = "&flId="+flId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId + "&pillarId=" + pillarId+"&year=" + year+"&isActual="+isActual+"&frmMode="+frmMode+"&type="+type +"&kinkFrequency="+frequency ;	
	//processGridnew('progCalender_input.progcal','?q=2',"grdKpiActualkk","pager","","docDoubleClick","","GridLoadCompleted");	
	//var url = jQuery('#hiddenUrl').val();
	viewGrid("kpiActualKk_input.kpiActKk","?q=2" + dataStr);	
}
*/

/*********************************** sriram 
function fnClear1()
{	
	//calTotal();
	var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
	var flId = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	var month =getFieldValue('cmbMonth','frmKpiTlActualKk');
	//alert("month"+month);
	var pillarId=getFieldValue("cmbKaukPillarid");
	//jQuery("#cmbKaukCalendaryear").combobox('setValue',month.substring(4,8));
	var year = 	getFieldValue('cmbKaukCalendaryear','frmKpiTlActualKk');
	var frequency=getFieldValue("cmbKpiFrequency");
	
	
	//alert("month:::"+ month.substring(4,8));
	//alert("frequency " + frequency + " month:" +month);
	
	var frmMode=jQuery("#txtFrmMode").val();
	var isActual="Y";
	if(frmMode=="target")
		isActual="N";
	var type=getFieldValue("txtFormType");
	var dataStr = "&flId="+flId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId + "&pillarId=" + pillarId+"&year=" + year+"&isActual="+isActual+"&frmMode="+frmMode+"&type="+type +"&kinkFrequency="+frequency +"&month=" +month;	
	//processGridnew('progCalender_input.progcal','?q=2',"grdKpiActualkk","pager","","docDoubleClick","","GridLoadCompleted");	
	//var url = jQuery('#hiddenUrl').val();
	viewGrid("kpiActualKk_input.kpiActKk","?q=2" + dataStr);
	//viewGrid("kpiActualKk_input.kpiActKk","?q=2" + dataStr,"" , "GridLoadCompleted");	
}*//////////////////////////


function fnClear1(monthParam, pillarParam, yearParam, frequencyParam)
{	
	//calTotal();
	var factId = jQuery("#frmKpiTlActualKk input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKk input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKk input[id='cell']").val();
	var flId = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	//var month =getFieldValue('cmbMonth','frmKpiTlActualKk');
	var month = monthParam == null ? getFieldValue('cmbMonth','frmKpiTlActualKk'):monthParam;
	
	//alert("month:"+month);

	//alert("month"+month);
	//var pillarId=getFieldValue("cmbKaukPillarid");
	var pillarId= pillarParam == null ? getFieldValue("cmbKaukPillarid"):pillarParam;
	//alert("pillarid:"+pillarId);
	//jQuery("#cmbKaukCalendaryear").combobox('setValue',month.substring(4,8));
	var year = 	getFieldValue('cmbKaukCalendaryear','frmKpiTlActualKk');
	var frequency=getFieldValue("cmbKpiFrequency");
	
	
	//alert("month:::"+ month.substring(4,8));
	//alert("frequency " + frequency + " month:" +month);
	
	var frmMode=jQuery("#txtFrmMode").val();
	var isActual="Y";
	if(frmMode=="target")
		isActual="N";
	var type=getFieldValue("txtFormType");
	var dataStr = "&flId="+flId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId + "&pillarId=" + pillarId+"&year=" + year+"&isActual="+isActual+"&frmMode="+frmMode+"&type="+type +"&kinkFrequency="+frequency +"&month=" +month;	
	//processGridnew('progCalender_input.progcal','?q=2',"grdKpiActualkk","pager","","docDoubleClick","","GridLoadCompleted");	
	//var url = jQuery('#hiddenUrl').val();
	viewGrid("kpiActualKk_input.kpiActKk","?q=2" + dataStr);
	//viewGrid("kpiActualKk_input.kpiActKk","?q=2" + dataStr,"" , "GridLoadCompleted");	
}





function FormatMonthfromISD(date){
	let newDate = new Date(date);

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = months[newDate.getMonth()] + '-' + newDate.getFullYear();
	return formatted;
 }
function frmKpiTlActualKk_exceptionCallback(){
	//alert("exception");
}


function frmKpiTlActualKk_successsCallback(result){	
	 
	 var openactnpln =result.successData.openactnpln;
	 var whywhy =result.successData.whywhy;
	 var month =result.successData.month;
//	 alert(month);
	 var deptid=result.successData.deptid;
	// alert(deptid);
	 var date=result.successData.date;
	 //alert(date);
	 var pillarId =result.successData.pillarId;
	 var type =result.successData.type;
	 var vMonth=result.successData.vMonth;
	 setFieldValue("txtFormType",type);
	 var DevCount=result.successData.count;
	 //alert(DevCount);
	 var formMode=result.successData.formMode;
	// alert("Mode::;"+formMode);
	 var year =getFieldValue("cmbKaukCalendaryear");
	 //alert("Year:::"+year);
	 jQuery('#cmbKaukPillarid').val(pillarId);
	 var location=jQuery("#hdnlocationid").val();
	// alert(location);
	  var CurrDate=jQuery("#hdnkpiDate").val();
	 // alert("The CurrDate::::"+CurrDate);
	 var frequency=result.successData.frequency;
	// alert(frequency);
	 var CurrMonthYear=result.successData.CurrMonthYear;
    // alert(CurrMonthYear);
	 
     var JHFlids="FNL000124053";
	 var DMTFlids="FNL000124032";
	 if(location=="LCN0000005" && DevCount>0 && formMode=="actual"){		
		if(deptid=="FNL000124053" || DMTFlids=="FNL000124032"){
		// alert("Inside the Process");	
		 processAjaxCalls("KPIDeviation_sendMail.kpiActKk","&deptid="+deptid+"&month="+month+"&CurrDate="+CurrDate+"&frequency="+frequency+"&CurrMonthYear="+CurrMonthYear,"","");
	 }
	 }
	 
 	 
	 if(openactnpln==true)
	 {
		 var keyid = result.successData.keyid;
		 var flid=jQuery("#frmKpiTlActualKk input[id='flid']").val();
		 var maintask = jQuery("#hdnIndicatiorName").val();
		 setFieldValue('cmbAbnmKeyid',keyid);
		 var abnDetectDate = jQuery("#hdnKPIMonth").val();
		 if(keyid=="" || keyid==" "){
			 keyid = jQuery("#hdnKPIKeyid").val();
		 }

		 var vFrequency= getFieldValue("cmbKpiFrequency");
				 if(vFrequency=='D'){
					 maintask = maintask+" - " +abnDetectDate;
				 }else if(vFrequency=='M'){
					 maintask = maintask+" - " +abnDetectDate.substring(3,11);
				 }
		 openActionPlan("actionPlan",keyid,"KPI",flid,maintask,keyid,abnDetectDate,"create");
	 }
	else if(whywhy==true )
	 {
		 var keyid = result.successData.keyid;		 
		 var flid=jQuery("#frmKpiTlActualKk input[id='flid']").val();
		 var problem = jQuery("#hdnIndicatiorName").val();
		 setFieldValue('cmbAbnmKeyid',keyid);
		 var refDocDate = jQuery("#hdnKPIMonth").val();
		 if(keyid=="" || keyid==" "){
			 keyid = jQuery("#hdnKPIKeyid").val();
		 }
		 openWhyWhy("",false,keyid,"KPI",flid, refDocDate, problem, "create", null, null);
		 
	 }
	 jQuery('#hdnSelectRow').val('');
	 fnClear1();
}

function frmKpiTlActualKk_FuntLocHierarchy_SuccessCallBack(keyIds){
	fnClear1();	
	jQuery("#frmKpiTlActualKk div[id=dispFunctionalLoc]").css('width','98%');
}

function  frmFunctLocHierarchcmbFunctLocMachine_onLoadSuccess(){}

/** This Method Is Created For 25Apr2016 for KPI Indicator Name to display**/
function textBlur1(id)
{	
	var arrVars = id.split("_");
	var rowid = arrVars.pop();
	

	var data = id.split('_');
	var cMonthYear = jQuery("#"+id).attr('monthYear');
	var keyid = jQuery("#"+id).attr('keyId');
	if(cMonthYear.length<5)
		cMonthYear = '01-JAN-'+cMonthYear;
	else if(cMonthYear.length==8)
		cMonthYear = '01-'+cMonthYear;

	jQuery("#hdnKPIMonth").val(cMonthYear);
	jQuery("#hdnKPIKeyid").val(keyid);
	
	
	/*validation For Percentage*/
	var formMode=jQuery("#txtFrmMode").val();
//alert("formMode"+formMode);
	if(formMode.trim() == "target"  ){
		var cellValue = jQuery("#"+id).val();
		//alert("Value : " +cellValue);
		var rowId = data[2];
		jQuery("#hdnRowid").val(rowId);
		//alert("rowId: " +rowId );
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	var IndiName="Indicator Name : ";
	var IndicatiorName = rowData.IndicatiorName;
	//alert("IndicatiorName: " +IndicatiorName);
	IndiName+=IndicatiorName;
	//alert("IndicatiorName: " +IndiName);
	jQuery("#lblTask").val(IndiName);
		
		var Uom = rowData.Uom;
		//alert("Uom: " +Uom);
		var isChild = rowData.Child;
	  //  alert("Child :" +isChild);
	/* 
		if(Uom.trim() == "%" && isChild == 'Y'){
			if(parseInt(cellValue) > 100){
				alert("Maximum Percentage Should be 100");
				jQuery("#"+id).val(' ');
				return false;
			}
		}  */
		
		/* Copy The Target Value Automatically for every Cell*/
		var Frequency = rowData.Frequency;
		if(Frequency.trim() == "Daily" && isChild == 'Y'){
			//alert("1");		
			var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	var IndiName="Indicator Name : ";
	var IndicatiorName = rowData.IndicatiorName;
	//alert("IndicatiorName: " +IndicatiorName);
	IndiName+=IndicatiorName;
	//alert("IndicatiorName: " +IndiName);
	jQuery("#lblTask").val(IndiName);
			var dateData = cMonthYear.split('-');			
			var m = dateData[1];
			var y = dateData[2];
			//alert("Month: " + m + " Year: " + y + " mno: " + (changeFormatStringtoNumber(m)+1));
			var noOfDays = daysInMonth((changeFormatStringtoNumber(m)+1), y);
		  //  alert("noOfDays:" +(parseInt(noOfDays)+19));
			/* for(var day = 19 ; day < (parseInt(noOfDays) + 19) ; day++){
			//	alert("#"+data[0]+"_" + day + "_" +data[2]);			
				cellValue = jQuery("#"+data[0]+"_" + day + "_" +data[2]).val();
				if(cellValue.trim().length <= 0 || cellValue.trim() == "0"){					
					var oldVal =	jQuery("#"+data[0]+"_" + "19" + "_" +data[2]).val();				
					jQuery("#"+data[0]+"_" + day + "_" +data[2]).val(oldVal);
				}
				
			} */
		
		}
	}
	
	//Calculate the sum or average
	//alert("Form mode: " +formMode.trim());
	if(formMode.trim() == "actual" || formMode.trim() == "target"){
		//var cellValue = jQuery("#"+id).val();	
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	var IndiName="Indicator Name : ";
	var IndicatiorName = rowData.IndicatiorName;
	//alert("IndicatiorName: " +IndicatiorName);
	IndiName+=IndicatiorName;
//	alert("IndicatiorName: " +IndiName);
	jQuery("#lblTask").val(IndiName);
		var rowId = data[2];
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowId);
		var Frequency = rowData.Frequency;
		var calcType = rowData.Calctype;
		//alert("rowData.Calctype: " +calcType);
		var isChild = rowData.Child;
	    //alert("Child :" +isChild);
	
		var sumoravg = 0; 	
		if((Frequency.trim() == "Daily" || Frequency.trim() == "Weekly" || Frequency.trim() == "Fortnight") && isChild == 'Y'){
			//alert("1");		
			var dateData = cMonthYear.split('-');			
			var m = dateData[1];
			var y = dateData[2];
			//alert("Month: " + m + " Year: " + y + " mno: " + (changeFormatStringtoNumber(m)+1));
			
			var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	       var IndiName="Indicator Name : ";
	      var IndicatiorName = rowData.IndicatiorName;
	     // alert("IndicatiorName: " +IndicatiorName);
	      IndiName+=IndicatiorName;
	    // alert("IndicatiorName: " +IndiName);
	     jQuery("#lblTask").val(IndiName);
			var noOfDays = daysInMonth((changeFormatStringtoNumber(m)+1), y);
		//	alert("noOfDays:" +(parseInt(noOfDays)+19));
			var count = 0;
			/* for(var day = 19 ; day < (parseInt(noOfDays) + 19) ; day++){
				//alert("#"+data[0]+"_" + day + "_" +data[2]);			
				var cellValue = jQuery("#"+data[0]+"_" + day + "_" +data[2]).val();
				//alert("cellValue: " +cellValue);
				if(cellValue.trim().length > 0 ){	
					sumoravg +=parseInt(cellValue);
					count++;
				} 
				
			}*/
		/* 	if(calcType == 'A'){
				//alert("count: "+ count);
				if( count != "0"){
					sumoravg /=parseInt(count);
				}
				sumoravg = sumoravg.toFixed(2);
				jQuery("#"+data[0]+"_" + (parseInt(noOfDays) + 19) + "_" +data[2]).val(sumoravg);
			}else if(calcType == 'S'){
				sumoravg = sumoravg.toFixed(2);
				jQuery("#"+data[0]+"_" + (parseInt(noOfDays) + 19) + "_" +data[2]).val(sumoravg);
			}else if(calcType == 'X'){
				//No Action
			}
			 */
		
		}
	}
	
	if(formMode.trim()=="view")
		{
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	       var IndiName="Indicator Name : ";
	      var IndicatiorName = rowData.IndicatiorName;
	    //  alert("IndicatiorName: " +IndicatiorName);
	      IndiName+=IndicatiorName;
	    //  alert("IndicatiorName: " +IndiName);
	     jQuery("#lblTask").val(IndiName);
		}
	//var data = id.split('_');
		
	var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',data[2]);
	jQuery("#hdnIndicatiorName").val(rowData.IndicatiorName);	
	jQuery('#hdnSelectRow').val(data[2]);
	jQuery('#hdnSelectCol').val(data[1]);
	
}
/***created By sugumar****/
function textBlur(id)
{
	//alert("Id:" +id);
	jQuery("#hdnselectid").val(id);
	var data = id.split('_');
	var cMonthYear = jQuery("#"+id).attr('monthYear');
	var keyid = jQuery("#"+id).attr('keyId');
	if(cMonthYear.length<5)
		cMonthYear = '01-JAN-'+cMonthYear;
	else if(cMonthYear.length==8)
		cMonthYear = '01-'+cMonthYear;

      
	jQuery("#hdnKPIMonth").val(cMonthYear);
	jQuery("#hdnKPIKeyid").val(keyid);
	/*validation For Percentage*/
	var formMode=jQuery("#txtFrmMode").val();
	if(formMode.trim() == "target"  ){
		var cellValue = jQuery("#"+id).val();
		//alert("Value : " +cellValue);
		var rowId = data[2];
		//alert("rowId: " +rowId );
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowId);
		//var IndicatiorName = rowData.IndicatiorName;
		//alert("IndicatiorName: " +IndicatiorName);
		var Uom = rowData.Uom;
		//alert("Uom: " +Uom);
		var isChild = rowData.Child;
	  //  alert("Child :" +isChild);
	
		if(Uom.trim() == "%" && isChild == 'Y'){
			if(parseInt(cellValue) > 100){
				alert("Maximum Percentage Should be 100");
				jQuery("#"+id).val(' ');
				return false;
			}
		} 
		
		/* Copy The Target Value Automatically for every Cell*/
		var Frequency = rowData.Frequency;
		if(Frequency.trim() == "Daily" && isChild == 'Y'){
			//alert("1");		
			var dateData = cMonthYear.split('-');			
			var m = dateData[1];
			var y = dateData[2];
			//alert("Month: " + m + " Year: " + y + " mno: " + (changeFormatStringtoNumber(m)+1));
			var noOfDays = daysInMonth((changeFormatStringtoNumber(m)+1), y);
		  //  alert("noOfDays:" +(parseInt(noOfDays)+19));
			for(var day = 19 ; day < (parseInt(noOfDays) + 19) ; day++){
			//	alert("#"+data[0]+"_" + day + "_" +data[2]);			
				cellValue = jQuery("#"+data[0]+"_" + day + "_" +data[2]).val();
				if(cellValue.trim().length <= 0 || cellValue.trim() == "0"){					
					var oldVal =	jQuery("#"+data[0]+"_" + "19" + "_" +data[2]).val();				
					jQuery("#"+data[0]+"_" + day + "_" +data[2]).val(oldVal);
				}
				
			}
		
		}
	}
	
	//Calculate the sum or average
	//alert("Form mode: " +formMode.trim());
	if(formMode.trim() == "actual" || formMode.trim() == "target"){
		//var cellValue = jQuery("#"+id).val();		
		var rowId = data[2];
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowId);
		var Frequency = rowData.Frequency;
		var calcType = rowData.Calctype;
		//alert("rowData.Calctype: " +calcType);
		var isChild = rowData.Child;
	    //alert("Child :" +isChild);
	
		var sumoravg = 0; 	
		if((Frequency.trim() == "Daily" || Frequency.trim() == "Weekly" || Frequency.trim() == "Fortnight") && isChild == 'Y'){
			//alert("1");		
			var dateData = cMonthYear.split('-');			
			var m = dateData[1];
			var y = dateData[2];
			//alert("Month: " + m + " Year: " + y + " mno: " + (changeFormatStringtoNumber(m)+1));
			var noOfDays = daysInMonth((changeFormatStringtoNumber(m)+1), y);
		//	alert("noOfDays:" +(parseInt(noOfDays)+19));
			var count = 0;
			for(var day = 19 ; day < (parseInt(noOfDays) + 19) ; day++){
				//alert("#"+data[0]+"_" + day + "_" +data[2]);			
				var cellValue = jQuery("#"+data[0]+"_" + day + "_" +data[2]).val();
				//alert("cellValue: " +cellValue);
				if(cellValue.trim().length > 0 ){	
					sumoravg +=parseInt(cellValue);
					count++;
				}
				
			}
			if(calcType == 'A'){
				//alert("count: "+ count);
				if( count != "0"){
					sumoravg /=parseInt(count);
				}
				sumoravg = sumoravg.toFixed(2);
				jQuery("#"+data[0]+"_" + (parseInt(noOfDays) + 19) + "_" +data[2]).val(sumoravg);
			}else if(calcType == 'S'){
				sumoravg = sumoravg.toFixed(2);
				jQuery("#"+data[0]+"_" + (parseInt(noOfDays) + 19) + "_" +data[2]).val(sumoravg);
			}else if(calcType == 'X'){
				//No Action
			}
			
		
		}
		if(formMode.trim()=="view")
		{
		var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',rowid);
	       var IndiName="Indicator Name : ";
	      var IndicatiorName = rowData.IndicatiorName;
	    //  alert("IndicatiorName: " +IndicatiorName);
	      IndiName+=IndicatiorName;
	    //  alert("IndicatiorName: " +IndiName);
	     jQuery("#lblTask").val(IndiName);
		}
	}
	//var data = id.split('_');
	
		
	var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData',data[2]);
	jQuery("#hdnIndicatiorName").val(rowData.IndicatiorName);	
	jQuery('#hdnSelectRow').val(data[2]);
	jQuery('#hdnSelectCol').val(data[1]);
	
}

function daysInMonth(month,year) {
    return new Date(year, month, 0).getDate();
}

jQuery("#btnremarks").click(function() {

    var id = jQuery('#hdnSelectRow').val();
    
    if (!id || id.trim().length == 0) {
        alert("Select The Row Cell");
        return false;
    }

    var kpiMonth = getFieldValue("hdnKPIMonth");
    //console.log("kpiMonth:", kpiMonth);

    var slctid = jQuery("#hdnselectid").val();
    var arr = slctid.split('_');
    var rowid = arr.pop();

    if (!rowid || rowid.trim().length == 0) {
        alert("Please Select the Valid Cell to Enter Remarks");
        return false;
    }

    if (rowid != id) {
        alert("Select Correct Remarks Button");
        return false;
    }

    if (kpiMonth.trim().length == 0) {
        alert("Select Month For Remarks");
        return false;
    }

    var rowData = jQuery("#grdKpiActualkk").jqGrid('getRowData', id);
    var IndicatiorName = rowData.IndicatiorName;
    var Frequency = rowData.Frequency;
    var Uom = rowData.Uom;
    var indicatorId = rowData.KinkKeyId;

    jQuery("#hdnKPIKeyid").val(indicatorId);
    var cKeyId = jQuery("#hdnKPIKeyid").val();

    if (!cKeyId || cKeyId.trim().length == 0) {
        alert("Please Select the Valid Cell to Enter Remarks");
        return false;
    }

    var flid = jQuery("#frmKpiTlActualKk input[id='flid']").val();

    var filterString = '&Indicator=' + encodeURIComponent(cKeyId)
        + '&Frequency=' + encodeURIComponent(Frequency)
        + '&Uom=' + encodeURIComponent(Uom)
        + '&IndicatiorName=' + encodeURIComponent(IndicatiorName)
        + '&flid=' + encodeURIComponent(flid)
        + '&kpiMonth=' + encodeURIComponent(kpiMonth);

    LoadPopUp("divKpiRemarks",
        "kpiRemarks_input.kpiActKk?q=2" + filterString,
        true, "40%", "65%", "15%", "35%", "", "Remarks");

});


jQuery("#btnExcelVw").click(function(){
	var flid = jQuery("#frmKpiTlActualKk input[id='flid']").val();
	//alert(flid);
	var year =getFieldValue("cmbKaukCalendaryear");
	//alert("Year:::"+year);
	 var vMonth= getFieldValue("cmbMonth");
	 var CurrDate=jQuery("#hdnkpiDate").val();
	// alert(CurrDate);
	 var vFreq= getFieldValue("cmbKpiFrequency");
    // alert("vFreq::::"+vFreq);
     var CurrMonthYear=jQuery("#hdnCurrMonthYear").val();
   //  alert("CurrYear:::"+CurrMonthYear);

    // alert("vMonth:::"+vMonth); 
	//var keyid=jQuery("#txtEtcmKeyid").val();
	//if(keyid !=null && keyid.length>0){
	 window.open("KPItlActual_Excelview.kpiActKk?&flid="+flid+"&year="+year+"&CurrDate="+CurrDate+"&vFreq="+vFreq+"&CurrMonthYear="+CurrMonthYear,"Excel View");	
	//}
	/*else{
		alert("Pls Save the Training Calendar First!");
	}*/
 });
		
</script>
<div id="wrapper" style="width:100%">		
	<form name="frmKpiTlActualKk" id="frmKpiTlActualKk">
	   	<table width="100%" align="center" >
	   		<tr>
	   		
			    <td width="100%">
			    <!-- Start -->
			    	<table width="100%" align="center" >
						<tr>
							<!--top left content 11-->

							<td valign="top" width="45%">
								<div id="frmKpiTlActualKkFuntKeyIds">
									<div style="float: left; padding-right: 1px;">

										<input type="hidden" id="deptid" name="cmbKaukDeptid"
											value="${requestScope.kpiTlActualKk.kaukDeptid}"></input> <input
											type="hidden" id="depttype" name="cmbKaukDepttype"
											value="${requestScope.kpiTlActualKk.kaukDepttype}"></input>
									</div>
									<div class="" style="width: 100%; margin-top: -24px">
										<div id="kpiActKkfunLocation"></div>
										<div id="txtFct" class="tpm-errormsg"
											style="padding-left: 30px;" /></div>
										<div id="err_err_cell" class="tpm-errormsg"
											style="padding-left: 30px;" /></div>
									</div>
									<div class="clear"></div>
								</div>
							</td>
							<!--top left content 12-->
							 <td  width="6%">
								<div style="width: 80px; margin-top: -21px; margin-left: 10px">
									<div class="easyui-paddingbfpx">
										<span><label class="mandatory-lbl">Frequency</label></span>
									</div>	
									<div class="easyui-paddingbfpx">
										<span><input id="cmbKpiFrequency" name="cmbKpiFrequency" class="easyui-combobox" style="width:120px" value="" /></span>
									</div>								
									
								</div>
							</td>
							 <td  width="6%">
								<div style="width: 60px; margin-top: -21px; margin-left: 30px">
									<div class="easyui-paddingbfpx">
										<span><label>Month</label></span>
									</div>	
									<div class="easyui-paddingbfpx">
										<span><input id="cmbMonth" name="cmbMonth" class="easyui-datebox"  style="width:80px;" value="" /></span>
									</div>								
									
								</div>
							</td>
							<td valign="top" width="6%">
								<div style="width: 60px; margin-top: -21px; margin-left: 20px">
								
									<div class="easyui-paddingbfpx">
										<span><label class="mandatory-lbl">Year</label></span>
									</div>
									<div class="easyui-paddingbfpx">
										<span><input id="cmbKaukCalendaryear"
											name="cmbKaukCalendaryear" class="easyui-combobox"
											style="width: 60px"
											value="${requestScope.kpiTlActualKk.kaukCalendaryear}"
											"${ requestScope.kpiTlActualKkBean.disableForm == true ? ' disabled':''}"></span>
									</div>
								</div>
							</td>
							<!--top left content 13-->
							<td valign="top" width="13%">
								<div style="width: 60px; margin-top: -21px; margin-left: 10px">
									<div class="easyui-paddingbfpx">
										<span><label>Pillar</label></span>
									</div>
									<div class="easyui-paddingbfpx">
										<span><input class="easyui-combo" id="cmbKaukPillarid"
											name="cmbKaukPillarid" style="width: 130px;" value="" /></span>
										<%-- value="${requestScope.kpiTlActualKk.kaukPillarid}" --%>
									</div>
								</div>
							</td>
						
						</tr>
						
					</table>
			     <!-- End -->
			    </td>
					   
		    </tr>	
		    <tr>
		    	<td colspan="3">
		    		<div style="float:left;width:65%;height:auto;margin-top: -11px">
 						<span style="background-color:#FFFA7F;color:#FFFA7F;border:solid 1px;font-size:10;">.......</span> 
						 <label style="font-weight:bold;font-size:12px; ">Parent Indicator</label> 
						
						<span style="background-color:#FFFFFF;color:#FFFFFF;border:solid 1px;font-size:10">.......</span>
						<label style="font-weight:bold;font-size:12px;">Actual Entry</label>
						<span style="background-color:#C7CEFD;color:#C7CEFD;border:solid 1px;font-size:10">.......</span>
						<label style="font-weight:bold;font-size:12px;">Target Entry</label>
						<span style="padding-left: 50px;">
							<label style="background-color:blue;color:#C7CEFD;border:solid 1px;font-size:12px;font-weight:bold;font-size:12px;">Select Frequency, Month & Year to Enter KPI's</label>
						</span>
						</div><div style="float:right;width:35%;height:auto;margin-top: -11px">
							<c:if	test="${requestScope.frmMode == 'actual' || requestScope.frmMode == 'view'  }">
			                        <div style="margin-left:-74px; margin-bottom:-22px;margin-top:4px">
						              <input type="button" class="easyui-button" value="Remarks" id="btnremarks" style="height: 22px; width: 60px;" />									
										</div>
									<div id="graphDiv" >
									<input id="btnGraphPop" class="easyui-button" style="height: 22px; width: 93px;" type="button" value="Monthly Graph" />
										<input id="btnGraphDailyPop" class="easyui-button" style="height: 22px; width: 71px;" type="button" value="Daily Graph" /> 
										<input type="button" id="btnActionpln" name="btnActionpln" class="easyui-button" style="width: 70px; height: 22px;" value="Action Plan" /> 
										<input type="button" class="easyui-button" value="Why Why" id="btnYYLink" style="height: 22px; width: 70px;" />
                                       <input type="button" class="easyui-button" value="Excel" id="btnExcelVw" style="height: 22px; width: 70px;" />                                         
									</div>


							</c:if>
						<div>
						
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="lblTask" name="lblTask" disabled="disabled" style="border:2px solid black; font-size:11px ;margin-left:-185%; width:1090px;height:26px;color:black;background-color:#fae0aa;font-weight:bold;text-align:left;"></textarea>
						
						</div>
						
					</div>	   
		    	</td>
		    </tr>	
		  <%--   <tr>
		    <td>
		    <div   class="sub-header " style="float:center;width:65%;height:auto;margin-left:200px;margin-top: 11px">
		<!--<span style="background-color:#FFFA7F;color:#FFFA7F;border:solid 1px;font-size:10;">.......</span> --> 
						<span id="txtInd_'+columnNo + '_'+id +'"  type="text" indicatorId="'+indicatorId+'"  keyId="'+colKeyId+'" monthYear="'</span>' 
		    </div>
		    </td></tr>  --%>  		   
		</table> 
		 
	    <div>        		 
		    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
<!--		    <input type="hidden" id="txtKaukPillarid" name="txtKaukPillarid" value="${requestScope.kpiTlActualKk.kaukPillarid}"/>-->
		    <input type="hidden" id="txtKaukPillar" name="txtKaukPillar" value="${requestScope.kaukPillar}"/>
		    <input type="hidden" id="txtFrmMode" name="txtFrmMode" value="${requestScope.frmMode}"/>
		    <input type="hidden" id="dblColIndex" name="dblColIndex" value=""/>  
		    <input type="hidden" id="dblRowIndex" name="dblRowIndex" value=""/>  
		    
		    <input type="hidden" id="hdnSelectRow" name="hdnSelectRow" value=""/>
		    <input type="hidden" id="hdnSelectCol" name="hdnSelectCol" value=""/>
		</div>	
	    
	    <div style="width:85%;">
	    	<table id="grdKpiActualkk"></table>
			<div id="pager"></div>		
		</div>
		<!--  input type="hidden" id="txtTotal" name="txtTotal" value="0"/>-->
	 	<input type="hidden" id="hdnFactId" name="hdnFactId" value="${requestScope.factId}"/>
	 	<input type="hidden" id="hdnselectid" name="hdnselectid" value=" "/>
	    <input type="hidden" id="hdnSectId" name="hdnSectId" value="${requestScope.sectId}"/>
	    <input type="hidden" id="hdnCellId" name="hdnCellId" value="${requestScope.cellId}"/>
	    <input type="hidden" id="hdnPillarId" name="hdnPillarId" value="${requestScope.pillarId}"/>
	    <input type="hidden" id="hdnYear" name="hdnYear" value="${requestScope.year}"/>  
	    <input type="hidden" id="hdnIsActual" name="hdnIsActual" value="${requestScope.isActual}"/> 
	    <input type="hidden" id="hdnFilter" name="hdnFilter" value="${requestScope.filter}"/> 
	    <input type="hidden" id="hdnFlid" name="hdnKaukDeptid" value="${requestScope.kpiTlActualKk.kaukDeptid}"/>	    
	    <input type="hidden" id="hdnKPIMonth" name="hdnKPIMonth" value=""/>
	    <input type="hidden" id="hdnKPIKeyid" name="hdnKPIKeyid" value=""/>
	    <input type="hidden" id="hdnIndicatiorName" name="hdnIndicatiorName" value=""/>	     
	    <input type="hidden"  id="txtFormType" name="txtFormType" value="${requestScope.type}"/> 
	    <input type="hidden" id="hdnFrequency" name="hdnFrequency" value="M"/> 	      
        <input type="hidden" name="hdnlocationid" id="hdnlocationid" value="${requestScope.glbLocation}">
        <input type="hidden" name="hdnlogFlid" id="hdnlogFlid" value="${requestScope.logFlid}">
        <input type="hidden" name="hdnkpiDate" id="hdnkpiDate" value="${requestScope.kpiDate}"> 
        <input type="hidden" name="hdnrolename" id="hdnrolename" value="${requestScope.rolename}">
        <input type="hidden" name="hdnCurrMonthYear" id="hdnCurrMonthYear" value="${requestScope.CurrMonthYear}">   
    </form>
</div>