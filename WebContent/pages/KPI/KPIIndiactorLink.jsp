<!--
Created By:Dhanalakshmi.R
Date:8.12.12
-->
<script type="text/javascript">
var drillLevel=null;
jQuery(document).ready(function()
{	
	initialiseForm('frmKPIProduction');	
	jQuery('#submitForm').val('frmKPIProduction'); 
	var type=getFieldValue("txtFormType");
	var pillarId=getFieldValue("cmbKidlPillarid");
	fillComboBox("frmKPIProduction", "cmbKidlPillarid", "pillar.commonFilter",'',false);
	fillComboBox("frmKPIProduction","cmbKidlIndicatorid","kpiIndicatorcmb.keyPerInd",'',false);
	jQuery('#chkKpiLink').attr('checked',true);
	drillLevel=jQuery("#hdnDrill").val();
//	clearField("cmbKidlPillarid");
	loadFunctionalLocation("KPIProdfunLocation","functionalLoc.keyPerInd?drillLevel="+drillLevel,"KPIFuntKeyIds","frmKPIProduction","");
	jQuery("#cmbKidlIndicatorid").combobox({onRequest:function( ){	
		 var type=getFieldValue("txtFormType");
		 var pilllarId =getFieldValue("cmbKidlPillarid");  
	   	 return "type="+type+"&pillarId="+pilllarId;
  	 }});
	
	setTimeout(function(){
	clearField("cmbKidlPillarid");
	},150);
		  
});
		
/* jQuery("#cmbKidlIndicatorid").combobox({
	onSelect:function(recordid){
		recallGrid();		
	}
}); */

function frmKPIProductioncmbKidlIndicatorid_onSelect(record){
	var IndiactorValue =record.id;
	recallGrid1(IndiactorValue,null);
}

/* jQuery("#cmbKidlPillarid").combobox({
	onSelect:function(recordid){
		reloadCombo('frmKPIProduction','cmbKidlIndicatorid','kpiIndicatorcmb.keyPerInd');
		recallGrid();		
	}
}); */

function frmKPIProductioncmbKidlPillarid_onSelect(record) {
	
	var PillaridValue =record.id;
	reloadCombo('frmKPIProduction','cmbKidlIndicatorid','kpiIndicatorcmb.keyPerInd');
	recallGrid1(null,PillaridValue);		
	
}

function frmKPIProductioncmbKidlPillarid_onClear() {
	 var PillaridValue ="";
	reloadCombo('frmKPIProduction','cmbKidlIndicatorid','kpiIndicatorcmb.keyPerInd');
	recallGrid1(null,PillaridValue);		
}




function recallGrid1(indicatorparam,pillarParam){
	
	var url = jQuery('#hiddenUrl').val();    
	var types=jQuery('#hdntypes').val();
	//alert("types"+types);
	var typesall=jQuery('#hdntypesall').val();
	//alert("typesall"+typesall);
//	var kpiinactive=jQuery("#hdnkpiinactive").val();
	//alert("kpiinactive::::"+kpiinactive);
	  if(jQuery('#chkKpiLink').is(':checked')==true && typesall=="KPIALL"){

    	//alert("Inside KPI RecallGrid");
	    var compId="";
		var factId="";
		var sectId=jQuery("#frmKPIProduction input[id='section']").val();
		var cellId=jQuery("#frmKPIProduction input[id='cell']").val();
		//var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');
		var indicId = indicatorparam == null ? jQuery("#cmbKidlIndicatorid").combobox('getValue'):indicatorparam;	
		
		var dataString = "&q=1"; 
		//var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
		var pillarId = pillarParam == null ? jQuery("#cmbKidlPillarid").combobox('getValue'):pillarParam;
		//alert("pillarid:"+pillarId);
	
		dataString+="&pillarId="+pillarId;
		compId='CMP001';
		dataString+="&compId="+compId;
		jQuery("#hdnCmpy").val(compId);
		var pillCode=jQuery("#hdnPillCode").val();
		dataString+="&cellId="+cellId;
		dataString+="&sectId="+sectId;
		dataString+="&factId="+factId;	
		if(indicId==undefined ||indicId==''||indicId==""){}
		else
			dataString+="&indicatorId="+indicId;
		if(pillCode==undefined ||pillCode==''||pillCode==""){}
		else
			dataString+="&pillCode="+pillCode;

		var type = jQuery("#frmKPIProduction input[id='elementType']").val();
	//	alert("The type KPIALL"+type);
		var drillevel= type;
		if(type == "SECT")
		{
			drillevel="LIN";
		}else if(type == "FACT")
		{
			drillevel="FCT";
		}else if (drillevel=="CELL")
		{
			drillevel="CEL";
		}
		else if (drillevel=="PBU")
		{
			drillevel="PBU";
		}
		else if (drillevel=="SBU")
		{
			drillevel="SBU";
		}
		else if (drillevel=="LOCN")
		{
			drillevel="LOC";
		}	
		
		setFieldValue('hdnDrill',drillevel);
		dataString+="&drillLevel="+drillevel;
		var flid = jQuery("#frmKPIProduction input[id='flid']").val();
	 	dataString+="&flid="+flid;
	 	dataString+="&typesall="+typesall;
	//	alert("The dataString:::KPIALL"+dataString);
	    viewGrid(url,dataString);
}
	  else if(jQuery('#chkMappedKpi').is(':checked')==true && types=="MAPPEDKPI"){
		   //alert("mappedkpi");
	        var compId="";
			var factId="";
			var sectId=jQuery("#frmKPIProduction input[id='section']").val();
			var cellId=jQuery("#frmKPIProduction input[id='cell']").val();
			//var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');
			var indicId = indicatorparam == null ? jQuery("#cmbKidlIndicatorid").combobox('getValue'):indicatorparam;	
			
			var dataString = "&q=1"; 
			//var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
			var pillarId = pillarParam == null ? jQuery("#cmbKidlPillarid").combobox('getValue'):pillarParam;
			//alert("The pillarId"+pillarId)
			compId='CMP001';
			dataString+="&compId="+compId;
			jQuery("#hdnCmpy").val(compId);
			var pillCode=jQuery("#hdnPillCode").val();
			dataString+="&cellId="+cellId;
			dataString+="&sectId="+sectId;
			dataString+="&factId="+factId;	
			if(indicId==undefined ||indicId==''||indicId==""){}
			else
				dataString+="&indicatorId="+indicId;
			if(pillCode==undefined ||pillCode==''||pillCode==""){}
			else
				dataString+="&pillCode="+pillCode;

			var type = jQuery("#frmKPIProduction input[id='elementType']").val(); 
			//alert("The type mapped"+type);
			var drillevel= type;
			if(type == "SECT")
			{
				drillevel="LIN";
			}else if(type == "FACT")
			{
				drillevel="FCT";
			}else if (drillevel=="CELL")
			{
				drillevel="CEL";
			}
			else if (drillevel=="PBU")
			{
				drillevel="PBU";
			}
			else if (drillevel=="SBU")
			{
				drillevel="SBU";
			}
			else if (drillevel=="LOCN")
			{
				drillevel="LOC";
			}	
			
			setFieldValue('hdnDrill',drillevel);
			dataString+="&drillLevel="+drillevel;
			var flid = jQuery("#frmKPIProduction input[id='flid']").val();
		 	dataString+="&flid="+flid;
			dataString+="&pillarId="+pillarId;
		 	dataString+="&types="+types;
		 //	alert("typesall MAPPED dataString"+dataString)
		    viewGrid(url,dataString);
		
	}
	  
}



function loadComplete()
{
	//alert("LoadComplete");
	if(jQuery("#sno").hasClass("snclass")){
	}
	else
	{   
		//('.jqgridheaderrow1').prepend('<th id="sno" class="snclass" style="width:15px;"></th>');
	}
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
	var colLength=cm.length-1;
	colLength=parseInt(colLength);
	//var levelno1=jQuery("#grdKPIProduction").getCell(2,colLength).trim();
	//alert("colLength:"+colLength+",levelno:"+levelno1);
	//var i=1;
	for(var i=1;i<=allRows.length;i++)
	{  
		//var rowid=allRows[i-1];	
		//alert("rowid"+rowid);		
		var levelno=jQuery("#grdKPIProduction").getCell(i,colLength).trim();
		//alert("cm.length:"+colLength+",levelno:"+levelno);
		var isAssigned=jQuery("#grdKPIProduction").getCell(i, 3).trim();	
		//alert("isAssigned"+isAssigned);
		if(levelno=="1"){
			var gridval=jQuery("#grdKPIProduction").getCell(i,2).trim();			
			jQuery("#grdKPIProduction").jqGrid('setCell',i,2,gridval,{'background-color':'#FFFA7F'});
		}
		//var gridval=jQuery("#grdKPIProduction").getCell(i,2).trim();	
		//alert("cm.length"+cm.length);
		for(var j=3;j<=cm.length-2;j++)
		{    
			//alert("cm[j].name"+cm[j].name);
			if(cm[j].name=="factoryPillar_checkbox"+j)
			{
				var colIndex=parseInt(j)+1;
				var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',i,'isKeyid'+colIndex);
				//var sstatus = jQuery("#grdKPIProduction").jqGrid('getCell',i,'factoryPillar_checkbox2').attr('checked');
				//alert("isKeyid"+isKeyid+"status"+status);
				var status=jQuery("#factoryPillar_checkbox"+i+colIndex).attr('checked');
				
				if(status=="checked")
				{   					
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue'+colIndex,'1');	
				}
				else
				{   
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue'+colIndex,'0');
				}
			}
			j=j+3;
		}
	} 
	
	var length=(cm.length-3)/2;
	var newArray= new Array();
	var j=3;
	for(var i=0;i<=length;i++)
		{
		if(i==0)
			newArray[i]=0;
		else
			{
			newArray[i]=j;
			j+=2;
			}
		}
	jQuery("tr.jqgridheaderrow1>th").click(function(event)
			{	
				var id=event.target.id;
				var no=id.substr(4);
				var mode = jQuery('#modechk').val();
				var pos=jQuery("#pos").val();
			    setTimeout(function() {
				if(mode == 0 || mode == ' ' || mode == '' || mode == null)
					{	
						for(i= 1;i<=allRows.length;i++)
							{
								jQuery('#factoryPillar_checkbox'+i+newArray[no]).attr('checked',true);
								chkboxCheck(i,newArray[no]);
								mode = 1;
								pos='1'+newArray[no];
								jQuery('#modechk').val('1');
								jQuery("#pos").val(pos);
							
						    }
					}
				else if(mode==1)
					{
							var comp='1'+newArray[no];
							if(pos==comp)
							{
								for(i= 1;i<=allRows.length;i++)
								{
									jQuery('#factoryPillar_checkbox'+i+newArray[no]).attr('checked',false);
									chkboxUnCheck(i,newArray[no]);
									mode = 0;
									pos='0'+newArray[no];
									jQuery('#modechk').val('0');
									jQuery("#pos").val(pos);
									
								}	
							}
							else
							{
								for(i= 1;i<=allRows.length;i++)
								{
									
									jQuery('#factoryPillar_checkbox'+i+newArray[no]).attr('checked',true);
									chkboxCheck(i,newArray[no]);
									mode = 1;
									pos='1'+newArray[no];
									jQuery('#modechk').val('1');
									jQuery("#pos").val(pos);
								
							    }
							}					
					}
				
						
				},250);			
						event.preventDefault();
			});		 
}




function frmKPIProduction_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	recallGrid();
}
function frmKPIProduction_deleteSuccessCallback(result)
{
	
	alert(result.successData.msg);
	jQuery("#grdKPIProduction").trigger("reloadGrid");;
	//refrehForm();
	  
}


/*function loadCompleteInactive()
{
	alert("loadCompleteInactive");
	if(jQuery("#sno").hasClass("snclass")){
	}
	else
	{   
	  
	}
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
	var colLength=cm.length-1;
	colLength=parseInt(colLength);
	for(var i=1;i<=allRows.length;i++)
	{  
		var levelno=jQuery("#grdKPIProduction").getCell(i,colLength).trim();
		var isAssigned=jQuery("#grdKPIProduction").getCell(i, 3).trim();
		if(levelno=="1"){
			var gridval=jQuery("#grdKPIProduction").getCell(i,2).trim();			
			jQuery("#grdKPIProduction").jqGrid('setCell',i,2,gridval,{'background-color':'#FFFA7F'});
		}
		for(var j=3;j<=cm.length-2;j++)
		{    
			if(cm[j].name=="factoryPillarInactive_checkbox"+j)
			{
				var colIndex=parseInt(j)+1;
				var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',i,'isKeyid'+colIndex);
				var status=jQuery("#factoryPillarInactive_checkbox"+i+colIndex).attr('checked');
				if(status=="checked")
				{   
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue2'+colIndex,'1');	
				}
				else
				{   
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue2'+colIndex,'0');
				}
			}
			j=j+3;
		}
	} 
	
	var length=(cm.length-3)/2;
//	alert("length"+length);
	var newArray= new Array();
	var j=3;
	for(var i=0;i<=length;i++)
		{
		if(i==0)
			newArray[i]=0;
		else
			{
			newArray[i]=j;
			j+=2;
			}
		}
	
	  jQuery("tr.jqgridheaderrow1>th").click(function(event)
			{	
				var id=event.target.id;
				var no=id.substr(4);
				var mode = jQuery('#modechk').val();
				//alert("The Mode"+mode);
				var pos=jQuery("#pos").val();
			//	alert("The Pos"+pos);
			    setTimeout(function() {
				if(mode == 0 || mode == ' ' || mode == '' || mode == null)
					{	
						for(i= 1;i<=allRows.length;i++)
							{
								jQuery('#factoryPillarInactive_checkbox'+i+newArray[no]).attr('checked',true);
								chkboxCheck1(i,newArray[no]);
								mode = 1;
								pos='1'+newArray[no];
								jQuery('#modechk').val('1');
								jQuery("#pos").val(pos);
							   
						    }
					}
				
				else if(mode==1)
					{
							var comp='1'+newArray[no];
							if(pos==comp)
							
							{
								for(i= 1;i<=allRows.length;i++)
								{
									jQuery('#factoryPillarInactive_checkbox'+i+newArray[no]).attr('checked',true);
									chkboxCheck1(i,newArray[no]);
									mode = 1;
									pos='0'+newArray[no];
									jQuery('#modechk').val('0');
									jQuery("#pos").val(pos);
									
								}	
							}
					}
		
				},250);			
						event.preventDefault();
			});		 
}*/

function recallGrid(){
	
	var url = jQuery('#hiddenUrl').val();    
	var types=jQuery('#hdntypes').val();
	//alert("types"+types);
	var typesall=jQuery('#hdntypesall').val();
	//alert("typesall"+typesall);
//	var kpiinactive=jQuery("#hdnkpiinactive").val();
	//alert("kpiinactive::::"+kpiinactive);
	  if(jQuery('#chkKpiLink').is(':checked')==true && typesall=="KPIALL"){

    	//alert("Inside KPI RecallGrid");
	    var compId="";
		var factId="";
		var sectId=jQuery("#frmKPIProduction input[id='section']").val();
		var cellId=jQuery("#frmKPIProduction input[id='cell']").val();
		var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');	
		var dataString = "&q=1"; 
		var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
		dataString+="&pillarId="+pillarId;
		compId='CMP001';
		dataString+="&compId="+compId;
		jQuery("#hdnCmpy").val(compId);
		var pillCode=jQuery("#hdnPillCode").val();
		dataString+="&cellId="+cellId;
		dataString+="&sectId="+sectId;
		dataString+="&factId="+factId;	
		if(indicId==undefined ||indicId==''||indicId==""){}
		else
			dataString+="&indicatorId="+indicId;
		if(pillCode==undefined ||pillCode==''||pillCode==""){}
		else
			dataString+="&pillCode="+pillCode;

		var type = jQuery("#frmKPIProduction input[id='elementType']").val();
	//	alert("The type KPIALL"+type);
		var drillevel= type;
		if(type == "SECT")
		{
			drillevel="LIN";
		}else if(type == "FACT")
		{
			drillevel="FCT";
		}else if (drillevel=="CELL")
		{
			drillevel="CEL";
		}
		else if (drillevel=="PBU")
		{
			drillevel="PBU";
		}
		else if (drillevel=="SBU")
		{
			drillevel="SBU";
		}
		else if (drillevel=="LOCN")
		{
			drillevel="LOC";
		}	
		
		setFieldValue('hdnDrill',drillevel);
		dataString+="&drillLevel="+drillevel;
		var flid = jQuery("#frmKPIProduction input[id='flid']").val();
	 	dataString+="&flid="+flid;
	 	dataString+="&typesall="+typesall;
	//	alert("The dataString:::KPIALL"+dataString);
	    viewGrid(url,dataString);
}
	  else if(jQuery('#chkMappedKpi').is(':checked')==true && types=="MAPPEDKPI"){
		  // alert("mappedkpi");
	        var compId="";
			var factId="";
			var sectId=jQuery("#frmKPIProduction input[id='section']").val();
			var cellId=jQuery("#frmKPIProduction input[id='cell']").val();
			var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');	
			var dataString = "&q=1"; 
			var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
			//alert("The pillarId"+pillarId)
			compId='CMP001';
			dataString+="&compId="+compId;
			jQuery("#hdnCmpy").val(compId);
			var pillCode=jQuery("#hdnPillCode").val();
			dataString+="&cellId="+cellId;
			dataString+="&sectId="+sectId;
			dataString+="&factId="+factId;	
			if(indicId==undefined ||indicId==''||indicId==""){}
			else
				dataString+="&indicatorId="+indicId;
			if(pillCode==undefined ||pillCode==''||pillCode==""){}
			else
				dataString+="&pillCode="+pillCode;

			var type = jQuery("#frmKPIProduction input[id='elementType']").val(); 
			//alert("The type mapped"+type);
			var drillevel= type;
			if(type == "SECT")
			{
				drillevel="LIN";
			}else if(type == "FACT")
			{
				drillevel="FCT";
			}else if (drillevel=="CELL")
			{
				drillevel="CEL";
			}
			else if (drillevel=="PBU")
			{
				drillevel="PBU";
			}
			else if (drillevel=="SBU")
			{
				drillevel="SBU";
			}
			else if (drillevel=="LOCN")
			{
				drillevel="LOC";
			}	
			
			setFieldValue('hdnDrill',drillevel);
			dataString+="&drillLevel="+drillevel;
			var flid = jQuery("#frmKPIProduction input[id='flid']").val();
		 	dataString+="&flid="+flid;
			dataString+="&pillarId="+pillarId;
		 	dataString+="&types="+types;
		 //	alert("typesall MAPPED dataString"+dataString)
		    viewGrid(url,dataString);
		
	}

	  /* else {
			alert("Inside allKPI RecallGrid & inactive");
			var compId="";
			var factId="";
			var sectId=jQuery("#frmKPIProduction input[id='section']").val();
			var cellId=jQuery("#frmKPIProduction input[id='cell']").val();
			var indicId=jQuery("#cmbKidlIndicatorid").combobox('getValue');	
			var dataString = "&q=1"; 
			var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
			dataString+="&pillarId="+pillarId;
			compId='CMP001';
			dataString+="&compId="+compId;
			jQuery("#hdnCmpy").val(compId);
			var pillCode=jQuery("#hdnPillCode").val();
			dataString+="&cellId="+cellId;
			dataString+="&sectId="+sectId;
			dataString+="&factId="+factId;	
			if(indicId==undefined ||indicId==''||indicId==""){}
			else
				dataString+="&indicatorId="+indicId;
			if(pillCode==undefined ||pillCode==''||pillCode==""){}
			else
				dataString+="&pillCode="+pillCode;

			var type = jQuery("#frmKPIProduction input[id='elementType']").val(); 
			var drillevel= type;
			if(type == "SECT")
			{
				drillevel="LIN";
			}else if(type == "FACT")
			{
				drillevel="FCT";
			}else if (drillevel=="CELL")
			{
				drillevel="CEL";
			}
			else if (drillevel=="PBU")
			{
				drillevel="PBU";
			}
			else if (drillevel=="SBU")
			{
				drillevel="SBU";
			}
			else if (drillevel=="LOCN")
			{
				drillevel="LOC";
			}	
			
			setFieldValue('hdnDrill',drillevel);
			dataString+="&drillLevel="+drillevel;
			var flid = jQuery("#frmKPIProduction input[id='flid']").val();
		 	dataString+="&flid="+flid;
		 	dataString+="&kpiinactive="+kpiinactive;
			alert("The dataString::kpiinactive:"+dataString);
		    viewGrid(url,dataString);
	}*/
}


function chkbox_factoryPillar(id, options, rowObject)
{ 
	var rowId = options.rowId;	
//	alert("rowId"+rowId);
	var pos=options.pos;
//	alert("pos"+pos);
	var position = new Array();
	var rowValue=new Array();
	var k=3;
	var l=2;
	for(var i=0;i<rowObject.length-3;i++)
		{
		position[i]=k;
		rowValue[i]=l;
		k+=4;
		l+=1;
		}
	//alert("rowObject:"+Object.keys(rowObject));
	for(var i=0;i<rowObject.length-3;i++)
	{
		//alert("rowValue["+i+"]"+","+rowObject[rowValue[0]]+","+rowObject[rowValue[1]]+","+rowObject[rowValue[2]]+","+rowObject[rowValue[3]]);
		if(pos==position[i])
			return '<input id="factoryPillar_checkbox'+rowId+pos+'" name="factoryPillar_checkbox" '+ (rowObject[rowValue[pos]]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck(\''+ rowId +'\',\''+pos+'\')}"/>';
			
	}
}

function frmKPIProductioncmbKidlIndicatorid_onSelect(record)
	{
		var url = jQuery('#hiddenUrl').val();
		var indId=record.id;
		var factId=jQuery("#hdnFact").val();
		var compId=jQuery("#hdnCmpy").val();
		var sectId=jQuery("#hdnSect").val();
		var cellId=jQuery("#hdnCell").val();
		var dataString = "?q=1"; 
		dataString+="&indicatorId="+indId;
		if(factId==undefined ||factId==''||factId==""){
			
			if(sectId==undefined ||sectId==''||sectId=="")
				{
					if(cellId==undefined ||cellId==''||cellId==""){}
					else
						{
						
						dataString+="&cellId="+cellId;
						}
					
				}
			else
				dataString+="&sectId="+sectId;	
			}
		else
			{
				if(sectId==undefined ||sectId==''||sectId=="")
				{
					if(cellId==undefined ||cellId==''||cellId=="")
						{
							dataString+="&factId="+factId;
						}
					else
						dataString+="&cellId="+cellId;
				}
				else
				dataString+="&sectId="+sectId;
			}
		if(compId==undefined ||compId==''||compId==""){}
		else
			dataString+="&compId="+compId;
		var pillCode=jQuery("#hdnPillCode").val();
		var pillarId=jQuery("#cmbKidlPillarid").combobox('getValue');
		dataString+="&pillarId="+pillarId;
		if(pillCode==undefined ||pillCode==''||pillCode==""){}
		else
			dataString+="&pillCode="+pillCode;
		viewGrid(url,dataString);
	}
	


	
function chkboxCheck(rowId,pos)
{
	var position=parseInt(pos);
	//alert("position"+position);
	 var val=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue'+position,'1');
	 //alert("chk"+val);
	 var val2=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'isDelete'+position,'N');
    var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillar_checkbox'+(position-1)).text().trim();
}
/*function chkboxUnCheck(rowId,pos)
{
	var position=parseInt(pos);		
	var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillar_checkbox'+(position-1)).text().trim();
	//alert("indicatorId:"+indicatorId+"flId"+flId);	
	processAjaxCalls('keyIndLink_validate.keyPerInd','?q=2&position='+position+'&rowNo='+rowId+'&indicatorId='+indicatorId+'&flId='+flId,'validateLink_SuccessCallback','validateLink_ErrorCallback');

}
function validateLink_SuccessCallback(result){
	var isValidate=result.isValidate;
	var position=result.position;
	var rowNo=result.rowNo;
	var msg=result.msg;
	//alert("rowNo"+rowNo+"position"+position);
	if (isValidate==false){
		jQuery('#factoryPillar_checkbox'+rowNo+position).attr('checked',true);
		alert(msg);
	}
	else{
		jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'checkPillarvalue'+position,'0');
		var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',rowNo,'isKeyid'+position);
		//alert("isKeyid:"+isKeyid);
		if (isKeyid.trim()=="1")
			jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'isDelete'+position,'Y');
	}
}*/


function chkboxUnCheck(rowId,pos)
{
	var position=parseInt(pos);
//	alert("uncheck position"+position);
	var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	//alert("indicatorId:::"+indicatorId);
	var flId=jQuery('th#grdKPIProduction_factoryPillar_checkbox'+(position-1)).text().trim();
	//alert("indicatorId:"+indicatorId+"flId"+flId);
	//var Inactive=confirm("Are You Sure Want To Inactive");
	//if(Inactive){
		//alert("Data Inactive Successfully");
		processAjaxCalls('keyIndLink_validate.keyPerInd','?q=2&position='+position+'&rowNo='+rowId+'&indicatorId='+indicatorId+'&flId='+flId,'validateLink_SuccessCallback','validateLink_ErrorCallback');		
	//} 
}

function validateLink_SuccessCallback(result){
	var isValidate=result.isValidate;
	//alert(isValidate);
	var position=result.position;
	var rowNo=result.rowNo;
	var msg=result.msg;
	//alert("Inactive");
	//alert("rowNo"+rowNo+"position"+position);
	//alert("KPI Inactive");
	if (isValidate==false){
		jQuery('#factoryPillar_checkbox'+rowNo+position).attr('checked',true);
		alert(msg);
	}
	else{
		jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'checkPillarvalue'+position,'0');
		var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',rowNo,'isKeyid'+position);
		//alert("isKeyid:"+isKeyid);
		if (isKeyid.trim()=="1")
			jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'isDelete'+position,'Y');
	}
}


function getSelectdRowsPillFact(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var k = 0; k < allRows.length;k++)
	{
		var row = allRows[k];
		var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
		var colLength=cm.length-2;
		var i=3;	
		
		while(i<=colLength)
		{
			//alert("i:"+i);
			var ckeckForSelColNameNew=ckeckForSelColName+i;
			var	checkBoxColNameNew=checkBoxColName+(i-1);
			var value = row[ckeckForSelColNameNew];
			var isDelete = row["isDelete"+i];
			var isKeyid = row["isKeyid"+i];
			//alert(i+"i"+"isDelete"+isDelete+"isKeyid"+isKeyid+"value"+value);
			if( value != null  &&  value.trim()  != "")
			{			
				//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
				if(value.trim() == '1' && isKeyid.trim()=='0')				
				{
					//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
					jsonArrO += '{';
					for(var colName in row)
					{   
						if(colName == checkBoxColNameNew)
						{	
							var factId=jQuery('th#grdKPIProduction_'+checkBoxColNameNew).text();
							jsonArrO +='KPIKidlDeptid:"'+factId.trim()+'"';
							
						}
					}
					jsonArrO += ",";
				
					for(var colName in row)
					{
						if(colName == 'indicatorID')
						{   var cellValue = parseJqGridCellValue(row[colName]);
							jsonArrO +='KPIKidlIndicatorid:"'+cellValue+'"';
							
						}
					}
					jsonArrO +=',KPIIsDelete:"'+isDelete+'"';
					jsonArrO +=  "},";					
				}
			} 
		i=i+4;
		}
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
} 



function getSelectdRowsPillFactDel(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var k = 0; k < allRows.length;k++)
	{
		var row = allRows[k];
		var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
		var colLength=cm.length-2;
		var i=3;	
		while(i<=colLength)
		{
			var ckeckForSelColNameNew=ckeckForSelColName+i;
			var	checkBoxColNameNew=checkBoxColName+(i-1);
			var value = row[ckeckForSelColNameNew];
			var isDelete = row["isDelete"+i];
			var isKeyid = row["isKeyid"+i];
			
			if( value != null  &&  value.trim()  != "")
			{	
				if(value == '0' && isKeyid=='1' && isDelete=='Y' ){
					//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
					jsonArrO += '{';
					for(var colName in row)
					{   
						if(colName == checkBoxColNameNew)
						{	
							var factId=jQuery('th#grdKPIProduction_'+checkBoxColNameNew).text();
							jsonArrO +='KPIKidlDeptid:"'+factId.trim()+'"';
							
						}
					}
					jsonArrO += ",";
				
					for(var colName in row)
					{
						if(colName == 'indicatorID')
						{   var cellValue = parseJqGridCellValue(row[colName]);
							jsonArrO +='KPIKidlIndicatorid:"'+cellValue+'"';
							
						}
					}
					jsonArrO +=',KPIIsDelete:"'+isDelete+'"';
					jsonArrO +=  "},";
				}
			} 
		i=i+4;
		}
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
} 
function frmKPIProduction_beforeSubmit()
{   
	 
	  var gridData = '&selectedPillFactIDs='+getSelectdRowsPillFact('grdKPIProduction','factoryPillar_checkbox','checkPillarvalue');
	  var gridDataDel='&deleteLinkList='+getSelectdRowsPillFactDel('grdKPIProduction','factoryPillar_checkbox','checkPillarvalue'); 
	  gridData+=gridDataDel;
	  /*var check=gridData.substr(22);
	  if(check==null||check==""||check=='')
		 { alert('No Indicators Selected');return false;}*/
		var indicatorId=jQuery("#cmbKidlIndicatorid").combobox('getValue');
		if(indicatorId==null||indicatorId==""||indicatorId==''){}
		else
	 	 gridData+='&indicatorId='+indicatorId;
	 	 var factId=jQuery("#hdnFact").val();
	 	 if(factId==null||factId==""||factId==''){}
	 	 else
		 {
	 	 	gridData+='&factId='+factId;
		 }	 	 
	 	
	 	var flid = jQuery("#frmKPIProduction input[id='flid']").val();
	 	var elemtype = jQuery("#frmKPIProduction input[id='elementType']").val();  
		
	 	
	 	 var pillarcode=jQuery("#hdnPillCode").val();
	 	 var drillLevel=jQuery("#hdnDrill").val();
	 	 
	 	 gridData+='&pillarcode='+pillarcode;
	 	 gridData+='&drillLevel='+drillLevel;
	 	 
	 	 gridData+='&flid='+flid;
	 	 gridData+='&elemtype='+elemtype;
	 	 
	 	
	  return gridData ; 
}
function frmKPIProduction_successsCallback(result)
{
	jQuery("#grdKPIProduction").trigger("reloadGrid");
	jQuery("#hdnPillCode").val(result.successData.pillCode);
}

function viewGrid(url,filterString)
{ 
	if( validateFilterSelection(filterString))
	{ 	
		var tableCaption="KPIProduction";
		var type=getFieldValue("txtFormType");
		var types=jQuery("#hdntypes").val();
		var typesall=jQuery("#hdntypesall").val();
	//	var kpiinactive=jQuery("#hdnkpiinactive").val();
		      filterString += '&type='+type;
		       if(jQuery('#chkKpiLink').is(':checked')==true && typesall=="KPIALL"){
				//  alert("Inside AllKPI");
				  filterString +='&typesall='+typesall;
	              processGridnew("KPIProdflid_input.keyPerInd",filterString,"grdKPIProduction","grdKPIProductionPager",tableCaption,"","","loadComplete");	
				}
		      
			  else if(jQuery('#chkMappedKpi').is(':checked')==true && types=="MAPPEDKPI"){
				//alert("Inside MappedKPI");
				filterString +='&types='+types;
				processGridnew("KPIProdflid_input.keyPerInd",filterString,"grdKPIProduction","grdKPIProductionPager",tableCaption,"","","loadCompleteMapped");
				}
			 /* else if(kpiinactive=="INACTIVE"){
					alert("Inside Inactive KPI");
					filterString +='&kpiinactive='+kpiinactive;
		            processGridnew("KPIProdflid_input.keyPerInd",filterString,"grdKPIProduction","grdKPIProductionPager",tableCaption,"","","loadCompleteInactive");	
				}*/
         return true;
}
  return false;	
}

function validateFilterSelection(filterString){
	return  true;
}
  
  
/*jQuery("#btninactive").click(function(){
	jQuery('#chkKpiLink').attr('checked',false);
	jQuery('#chkMappedKpi').attr('checked',false);
    var types="INACTIVE";
    jQuery("#hdnkpiinactive").val(types);
    viewGrid("KPIProdflid_input.keyPerInd","?&q=2&types="+types);
    recallGrid();
});*/

jQuery('#chkKpiLink').click(function(){
	var types;
   	if(jQuery("#chkKpiLink").is(":checked")==true){  
   		types="KPIALL";
		jQuery('#chkMappedKpi').attr('checked',false);	
		 jQuery('#chkKpiLink').val("KPIALL");
	}
   	viewGrid("KPIProdflid_input.keyPerInd","?&q=2&types="+types);
   	recallGrid();
});

jQuery('#chkMappedKpi').click(function(){
	 var types;
   	if(jQuery("#chkMappedKpi").is(":checked")==true){
		types="MAPPEDKPI";
		jQuery('#chkKpiLink').attr('checked',false);	
		 jQuery('#chkMappedKpi').val("MAPPEDKPI");
	}
    viewGrid("KPIProdflid_input.keyPerInd","?&q=2&types="+types);
    recallGrid();	
});




function chkbox_factoryPillarActiveInactive(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	var pos=options.pos;	
	var position = new Array();
	var rowValue=new Array();
	var k=3;
	var l=2;
	for(var i=0;i<rowObject.length-3;i++)
		{
		position[i]=k;
		rowValue[i]=l;
		k+=4;
		l+=1;
		}
	for(var i=0;i<rowObject.length-3;i++)
	{
		if(pos==position[i])
			return '<input id="factoryPillarActiveInactive_checkbox'+rowId+pos+'" name="factoryPillarActiveInactive_checkbox" '+ (rowObject[rowValue[pos]]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck1(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck1(\''+ rowId +'\',\''+pos+'\')}"/>';
			
	}
}

//*********inactive*************//
/*function chkbox_factoryPillarInactive(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	var pos=options.pos;	
	var position = new Array();
	var rowValue=new Array();
	var k=3;
	var l=2;
	for(var i=0;i<rowObject.length-3;i++)
		{
		position[i]=k;
		rowValue[i]=l;
		k+=4;
		l+=1;
		}
	for(var i=0;i<rowObject.length-3;i++)
	{
		if(pos==position[i])
			return '<input id="factoryPillarInactive_checkbox'+rowId+pos+'" name="factoryPillarInactive_checkbox" '+ (rowObject[rowValue[pos]]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck2(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck2(\''+ rowId +'\',\''+pos+'\')}"/>';
			
	}
}*/


function chkboxCheck1(rowId,pos)
{
	var position=parseInt(pos);
	//alert("position"+position);
	 var val=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue1'+position,'1');
	 //alert("chk"+val);
	 var val2=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'isDelete'+position,'N');
    var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillarActiveInactive_checkbox'+(position-1)).text().trim();
}


function chkboxUnCheck1(rowId,pos)
{
	var position=parseInt(pos);
	var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillarActiveInactive_checkbox'+(position-1)).text().trim();
	var Inactive=confirm("Are You Sure Want To Inactive");
	if(Inactive){
		alert("Data Inactive Successfully");
		processAjaxCalls('keyInActiveLink_validate.keyPerInd','?q=2&position='+position+'&rowNo='+rowId+'&indicatorId='+indicatorId+'&flId='+flId,'validateInActiveLink_SuccessCallback','validateInActiveLink_ErrorCallback');		
	} 
}

/*function chkboxCheck2(rowId,pos)
{
	var position=parseInt(pos);
	//alert("position"+position);
	 var val=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue2'+position,'1');
	 //alert("chk"+val);
	 var val2=jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'isDelete'+position,'N');
    var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillarInactive_checkbox'+(position-1)).text().trim();
}


function chkboxUnCheck2(rowId,pos)
{
	var position=parseInt(pos);
	var indicatorId=jQuery("#grdKPIProduction").jqGrid('getCell',rowId,'indicatorID').trim();
	var flId=jQuery('th#grdKPIProduction_factoryPillarInactive_checkbox'+(position-1)).text().trim();
	
}*/

function validateInActiveLink_SuccessCallback(result){
	var isValidate=result.isValidate;
	//alert(isValidate);
	var position=result.position;
	var rowNo=result.rowNo;
	var msg=result.msg;
	if (isValidate==false){
		jQuery('#factoryPillarActiveInactive_checkbox'+rowNo+position).attr('checked',true);
		alert(msg);
	}
	else{
		jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'checkPillarvalue1'+position,'0');
		var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',rowNo,'isKeyid'+position);
		if (isKeyid.trim()=="1")
			jQuery("#grdKPIProduction").jqGrid('setCell',rowNo,'isDelete'+position,'Y');
	}      
}



function loadCompleteMapped()
{
//	alert("loadCompleteMapped");
	if(jQuery("#sno").hasClass("snclass")){
	}
	else
	{   
	  
	}
	var allRows = jQuery("#grdKPIProduction").jqGrid('getRowData');
	var cm = jQuery("#grdKPIProduction").jqGrid("getGridParam", "colModel");
	var colLength=cm.length-1;
	colLength=parseInt(colLength);
	for(var i=1;i<=allRows.length;i++)
	{  
		var levelno=jQuery("#grdKPIProduction").getCell(i,colLength).trim();
		var isAssigned=jQuery("#grdKPIProduction").getCell(i, 3).trim();
		if(levelno=="1"){
			var gridval=jQuery("#grdKPIProduction").getCell(i,2).trim();			
			jQuery("#grdKPIProduction").jqGrid('setCell',i,2,gridval,{'background-color':'#FFFA7F'});
		}
		for(var j=3;j<=cm.length-2;j++)
		{    
			if(cm[j].name=="factoryPillarActiveInactive_checkbox"+j)
			{
				var colIndex=parseInt(j)+1;
				var isKeyid=jQuery("#grdKPIProduction").jqGrid('getCell',i,'isKeyid'+colIndex);
				var status=jQuery("#factoryPillarActiveInactive_checkbox"+i+colIndex).attr('checked');
				if(status=="checked")
				{   
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue1'+colIndex,'1');	
				}
				else
				{   
					jQuery("#grdKPIProduction").jqGrid('setCell',i,'checkPillarvalue1'+colIndex,'0');
				}
			}
			j=j+3;
		}
	} 
	
	var length=(cm.length-3)/2;
//	alert("length"+length);
	var newArray= new Array();
	var j=3;
	for(var i=0;i<=length;i++)
		{
		if(i==0)
			newArray[i]=0;
		else
			{
			newArray[i]=j;
			j+=2;
			}
		}
	
	  jQuery("tr.jqgridheaderrow1>th").click(function(event)
			{	
				var id=event.target.id;
				var no=id.substr(4);
				var mode = jQuery('#modechk').val();
				//alert("The Mode"+mode);
				var pos=jQuery("#pos").val();
			//	alert("The Pos"+pos);
			    setTimeout(function() {
				if(mode == 0 || mode == ' ' || mode == '' || mode == null)
					{	
						for(i= 1;i<=allRows.length;i++)
							{
								jQuery('#factoryPillarActiveInactive_checkbox'+i+newArray[no]).attr('checked',true);
								chkboxCheck1(i,newArray[no]);
								mode = 1;
								pos='1'+newArray[no];
								jQuery('#modechk').val('1');
								jQuery("#pos").val(pos);
							   
						    }
					}
				
				else if(mode==1)
					{
							var comp='1'+newArray[no];
							if(pos==comp)
							
							{
								for(i= 1;i<=allRows.length;i++)
								{
									jQuery('#factoryPillarActiveInactive_checkbox'+i+newArray[no]).attr('checked',true);
									chkboxCheck1(i,newArray[no]);
									mode = 1;
									pos='0'+newArray[no];
									jQuery('#modechk').val('0');
									jQuery("#pos").val(pos);
									
								}	
							}
					}
		
				},250);			
						event.preventDefault();
			});		 
}







</script>
<form id="frmKPIProduction" >
	<!-- 	<div  id="frmKPIProductionFuntKeyIds"  >
			<input type="hidden" id="location" name="cmbKPILocationid" value=""/>
			<input type="hidden" id="factory" name="cmbKPIFactoryid" value="" / >
			<input type="hidden" id="section" name="cmbKPISectionid" value="" / >
			<input type="hidden" id="cell" name="cmbKPICellid" value="" / >
			<input type="hidden" id="machine" name="cmbKPIMachine" value="" / >
			<div id="KPIProdfunLocation" style="width:994px;margin-left:110px;"></div>
			<div class="clear"></div>
		</div>   -->
	
        <div  id="frmKPIProductionFuntKeyIds" style="margin-left:110px;margin-top:5px;" >
			<div  class="easyui-paddingbfpx" id="KPIProdfunLocation">
					<%-- <input type="hidden" id="factory" name="cmbPlosFactoryid" value="${requestScope.factId}"  ></input> --%>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbPlosSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbPlosMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbPlosFlid" value=""  ></input>
				</div>
				<div id="KPIFuntKeyIds" style="width:994px;margin-left:110px;"></div>
			</div> 
		
<div style="margin-left:110px;margin-top:5px;">
	<span><label>Indicators</label></span>
	<span style="padding-left:295px;"><label>Pillar</label></span>
</div>
     	<div style="margin-top:6px;">
  		  <span style="border: solid 2px #c1c1c1; padding: 3px;margin-left:800px; margin-top:-5px;"><input id="chkKpiLink" name="chkKpiLink" type="checkbox" /></span>
  		<label><b>All KPI</b></label>
  		</div>
  		<div style="margin-top:-15px;"> 
  		 <span style="border: solid 2px #c1c1c1; padding: 3px;width:60px;height:15px;margin-left:875px; "><input id="chkMappedKpi" name="chkMappedKpi" type="checkbox" /></span>
  		  <label><b>Mapped KPI</b></label>
<!--   		  <input type="button" class="easyui-button" value ="Inactive" id="btninactive" style="height:30px"/>
 -->  		</div>
  		
     
<div style="margin-left:110px;margin-top:-19px;width: 700px;">
	<span><input id="cmbKidlIndicatorid" name="cmbKidlIndicatorid" type="text" class="easyui-combobox" style="width: 350px;" value="" /></span>
	<span><input class="easyui-combo" id="cmbKidlPillarid" name="cmbKidlPillarid" style="width: 200px;"	value="${requestScope.pillarid}" /></span>
</div>
<div style="padding-left:671px;margin-top:-17px;">
	<span style="background-color:#FFFA7F;color:#FFFA7F;border:solid 1px;font-size:10;">.......</span> 
	<label style="font-weight:bold;font-size:12px; ">Parent Indicator</label> 
</div>
<div id="wrapperRpt" style="margin-top:20px;margin-left:108px;" >
	<table id="grdKPIProduction" ></table>
	<div id="grdKPIProductionPager"></div></div>
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnFact" />
	<input type="hidden" id="hdnCmpy"/>	
	<input type="hidden" id="hdnSect"/>
	<input type="hidden" id="hdnCell"/>
	<input type="hidden" id="hdnDrill" value="${requestScope.drillLevel}"/>
	<input type="hidden" id="hdnPillCode" value="${requestScope.pillarcode }"/>
	<input type="hidden" id="hdnflid" value=""/>
	<input type="hidden" id="hdndeptid" value=""/>
	<input type="hidden" id="modechk" />
	<input type="hidden" id="pos"/>
	<input type="hidden"  id="txtFormType" name="txtFormType" value="${requestScope.type}"/>
	<input type="hidden" id="hdntypes" name="hdntypes" value="${requestScope.types}">
	<input type="hidden" id="hdntypesall" name="hdntypesall" value="${requestScope.typesall}">
<%-- 	<input type="hidden" id="hdnkpiinactive" name="hdnkpiinactive" value="${requestScope.kpiinactive}" />
 --%></form>