
<script type="text/javascript"><!--
jQuery(document).ready(function()
{	
	initialiseForm('frmPhenomenaLossMapping');	
	jQuery('#submitForm').val('frmPhenomenaLossMapping'); 
	var type=getFieldValue("txtFormType");
    var factId = jQuery("#frmPhenomenaLossMapping input[id='factory']").val();
	
    var sectionId = jQuery("#frmPhenomenaLossMapping input[id='section']").val();
   
    var cellId = jQuery("#frmPhenomenaLossMapping input[id='cell']").val();
    
	var machId = jQuery("#frmPhenomenaLossMapping input[id='machine']").val();
	
    // flid = jQuery("#frmPhenomenaLossMapping input[id='flid']").val(); 
    
     
    var dataStr = "&factId=" + factId
					+ "&sectionId=" + sectionId
					+ "&cellId=" + cellId + "&machId="
					+ machId;
					//&flid="+ flid;
	
	
	var drillLevel=jQuery("#hdnDrill").val();  
	loadFunctionalLocation("frmPhenomenaLossMappingFuntKeyIds","functLoc.pcsEnable?drillLevel="+drillLevel , "frmPhenomenaLossMappingFuntKeyIds", "frmPhenomenaLossMapping",dataStr);
	
	
		 
	
});
		
jQuery("#cmbPlpmKeyid").combobox({
	onSelect:function(recordid){
		recallGrid();		
	}
});

jQuery("#cmbPlpmKeyid").combobox({
	onSelect:function(recordid){
		reloadCombo("frmPhenomenaLossMapping","cmbPlpmKeyid","phenomena_combo.pcsEnable");
		recallGrid();		
	}
});

jQuery('#btnView').click(function()
		{
	        alert(" Select Functional Location DMT ");
			var url = "PhenomenaFunctionalLocationMapping_getCol.pcsEnable" ;
			url+= "";
			saveForm("frmPhenomenaLossMapping",url);		
		});	

function frmPFLProductioncmbKidlPillarid_onClear() {
	reloadCombo("frmPhenomenaLossMapping","cmbPlpmKeyid","phenomena_combo.pcsEnable");
	recallGrid();		
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption="phenomenaMapping";		
		processGridnew(url,filterString,"grdphenomenaMapping","grdphenomenaMappingpager");
		
		return true;
	}	
}

function validateFilterSelection(filterString){
	return  true;
}

function formatterChk(cellValue,options, rowObject)
{
	var gridId = options.gid;
	
		return '<input id="chk'+gridId+options.rowId+'" name="chk'+gridId+options.rowId+'" type="checkbox" value="N" onclick="if(this.checked){chkboxCheck(\''+gridId+options.rowId + '\');}else{chkboxUnCheck(\''+ gridId+options.rowId +'\');}"/>'; 
}

function loadComplete()
{
	//jQuery("tr.ui-jqgrid-labels").hide();
	if(jQuery("#sno").hasClass("snclass")){
	}
	else
	{   
		//('.jqgridheaderrow1').prepend('<th id="sno" class="snclass" style="width:15px;"></th>');
	}
	var allRows = jQuery("#grdphenomenaMapping").jqGrid('getRowData');
	var cm = jQuery("#grdphenomenaMapping").jqGrid("getGridParam", "colModel");
	var colLength=cm.length-1;
	colLength=parseInt(colLength);
	var levelno1=jQuery("#grdphenomenaMapping").getCell(2,colLength).trim();
	//alert("colLength:"+colLength+",levelno:"+levelno1);
	var i=1;
	for(var i=1;i<=allRows.length;i++)
	{  
		var rowid=allRows[i-1];	
		//alert("rowid"+rowid);		
		var levelno=jQuery("#grdphenomenaMapping").getCell(i,colLength).trim();
		//alert("cm.length:"+colLength+",levelno:"+levelno);
		var isAssigned=jQuery("#grdphenomenaMapping").getCell(i, 3).trim();	
		//alert("isAssigned"+isAssigned);
		if(levelno=="1"){
			var gridval=jQuery("#grdphenomenaMapping").getCell(i,2).trim();			
			jQuery("#grdphenomenaMapping").jqGrid('setCell',i,2,gridval,{'background-color':'#FFFA7F'});
		}
		//var gridval=jQuery("#grdphenomenaMapping").getCell(i,2).trim();	
		//alert("cm.length"+cm.length);
		for(var j=3;j<=cm.length-2;j++)
		{    
			//alert("cm[j].name"+cm[j].name);
			if(cm[j].name=="factoryPillar_checkbox"+j)
			{
				var colIndex=parseInt(j)+1;
				var isKeyid=jQuery("#grdphenomenaMapping").jqGrid('getCell',i,'isKeyid'+colIndex);
				//var sstatus = jQuery("#grdphenomenaMapping").jqGrid('getCell',i,'factoryPillar_checkbox2').attr('checked');
				//alert("isKeyid"+isKeyid+"status"+status);
				var status=jQuery("#factoryPillar_checkbox"+i+colIndex).attr('checked');
				
				if(status=="checked")
				{   					
					jQuery("#grdphenomenaMapping").jqGrid('setCell',i,'checkPillarvalue'+colIndex,'1');	
				}
				else
				{   
					jQuery("#grdphenomenaMapping").jqGrid('setCell',i,'checkPillarvalue'+colIndex,'0');
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
function frmPhenomenaLossMapping_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	recallGrid();
}

function recallGrid(){
	var url = jQuery('#hiddenUrl').val();
	var compId="";
	var factId="";
	var sectId=jQuery("#frmPhenomenaLossMapping input[id='section']").val();
	var cellId=jQuery("#frmPhenomenaLossMapping input[id='cell']").val();
	
	
	var dataString = "&q=1"; 
	
	compId='CMP001';
	dataString+="&compId="+compId;
	jQuery("#hdnCmpy").val(compId);

	dataString+="&cellId="+cellId;
	dataString+="&sectId="+sectId;
	dataString+="&factId="+factId;	
	
	
	var type = jQuery("#frmPhenomenaLossMapping input[id='elementType']").val(); 
	
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
	
	var flid = jQuery("#frmPhenomenaLossMapping input[id='flid']").val();
 	dataString+="&flid="+flid;
 	
	viewGrid(url,dataString);
	
}
function chkbox_factoryPillar(id, options, rowObject)
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
	//alert("rowObject:"+Object.keys(rowObject));
	for(var i=0;i<rowObject.length-3;i++)
	{
		//alert("rowValue["+i+"]"+","+rowObject[rowValue[0]]+","+rowObject[rowValue[1]]+","+rowObject[rowValue[2]]+","+rowObject[rowValue[3]]);
		if(pos==position[i])
			return '<input id="factoryPillar_checkbox'+rowId+pos+'" name="factoryPillar_checkbox" '+ (rowObject[rowValue[pos]]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck(\''+ rowId +'\',\''+pos+'\')}"/>';
			
	}
}

function chkboxCheck(rowId,pos)
{/*
	var position=parseInt(pos);
	jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'checkPillarvalue'+position,'1');
	jQuery("#grdKPIProduction").jqGrid('setCell',rowId,'isDelete'+position,'N');*/
	var position=parseInt(pos);
	
	jQuery("#grdphenomenaMapping").jqGrid('setCell',rowId,'checkPillarvalue'+position,'1');
	jQuery("#grdphenomenaMapping").jqGrid('setCell',rowId,'isDelete'+position,'N');
}
function chkboxUnCheck(rowId,pos)
{
	//alert("chkboxUnCheck 1");
	var position=parseInt(pos);		
	var phenomenaID=jQuery("#grdphenomenaMapping").jqGrid('getCell',rowId,'phenomenaID').trim();
	var flId=jQuery('th#grdphenomenaMapping_factoryPillar_checkbox'+(position-1)).text().trim();
	//alert("phenomenaID:"+ phenomenaID +"flId"+flId);	
	processAjaxCalls('keyPheLink_validate.pcsEnable','?q=2&position='+position+'&rowNo='+rowId+'&phenomenaID='+phenomenaID+'&flId='+flId,'validateLink_SuccessCallback','validateLink_ErrorCallback');
	
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
		jQuery("#grdphenomenaMapping").jqGrid('setCell',rowNo,'checkPillarvalue'+position,'0');
		var isKeyid=jQuery("#grdphenomenaMapping").jqGrid('getCell',rowNo,'isKeyid'+position);
		//alert("isKeyid:"+isKeyid);
		if (isKeyid.trim()=="1")
			//alert("jqGrid('setCell',rowNo,'isDelete'+position,'Y')");
			jQuery("#grdphenomenaMapping").jqGrid('setCell',rowNo,'isDelete'+position,'Y');
	}
}
function getSelectdRowsPillFact(jqGridId,checkBoxColName,ckeckForSelColName)
{
	//alert("SAVE :" );
	var allRows = jQuery("#grdphenomenaMapping").jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var k = 0; k < allRows.length;k++)
	{
		var row = allRows[k];
		/*for(var colName in row)
		{
			alert(colName);
		}
		alert("Row :[" +k + "] " + row);*/
		var cm = jQuery("#grdphenomenaMapping").jqGrid("getGridParam", "colModel");
		var colLength=cm.length-2;
		var i=3;	
		
		while(i<=colLength)
		{
			//alert("i:"+i);
			var ckeckForSelColNameNew=ckeckForSelColName+i;
			//alert("ckeckForSelColNameNew :" +ckeckForSelColNameNew);
			var	checkBoxColNameNew=checkBoxColName+(i-1);
		//	alert("checkBoxColNameNew :" +checkBoxColNameNew);
			var value = row[ckeckForSelColNameNew];
			//alert("value :" +value);
			var isDelete = row["isDelete"+i];
			var isKeyid = row["isKeyid"+i];
			//alert(i+" i "+"isDelete "+isDelete+" isKeyid "+isKeyid+" value "+value);
			if( value != null  &&  value.trim()  != "")
			{			
				//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
				if(value.trim() == '1' && isKeyid.trim()=='0')				
				{
					//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
					jsonArrO += '{';
					for(var colName in row)
					{   
						//alert("colName :" +colName + " checkBoxColNameNew: "+ checkBoxColNameNew);
						if(colName == checkBoxColNameNew)
						{	
							var factId=jQuery('th#grdphenomenaMapping_'+checkBoxColNameNew).text();
							jsonArrO +='PFLppflFactoryid:"'+factId.trim()+'"';
							
						}
					}
					jsonArrO += ",";
					for(var colName in row)
					{
						if(colName == 'phenomenaID')
						{   var cellValue = parseJqGridCellValue(row[colName]);
							jsonArrO +='PFLppflPlpmKeyid:"'+cellValue+'"';
							
						}
					}
					
					jsonArrO +=',PFLIsDelete:"'+isDelete+'"';
					jsonArrO +=  "},";					
				}
			} 
		i=i+4;
		}
		//alert("jsonArrO:" +jsonArrO);
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
} 



function getSelectdRowsPillFactDel(jqGridId,checkBoxColName,ckeckForSelColName)
{
	var allRows = jQuery("#grdphenomenaMapping").jqGrid('getRowData');
	//alert("Deltee");
	var jsonArrO='[';
	for( var k = 0; k < allRows.length;k++)
	{
		var row = allRows[k];
		var cm = jQuery("#grdphenomenaMapping").jqGrid("getGridParam", "colModel");
		var colLength=cm.length-2;
		var i=3;	
		/*
		for(var colName in row){
			alert("colName:" +colName);
		}*/
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
					//alert("Del 3:" );
					//alert(isDelete+"isDelete"+"isKeyid"+isKeyid+"value"+value);
					jsonArrO += '{';
					for(var colName in row)
					{   
						if(colName == checkBoxColNameNew)
						{	
							var factId=jQuery('th#grdphenomenaMapping_'+checkBoxColNameNew).text();
							jsonArrO +='PFLppflFactoryid:"'+factId.trim()+'"';
							
						}
					}
					jsonArrO += ",";
					
					for(var colName in row)
					{
						if(colName == 'phenomenaID')
						{   var cellValue = parseJqGridCellValue(row[colName]);
							jsonArrO +='PFLppflPlpmKeyid:"'+cellValue+'"';
							
						}
					}
					
					jsonArrO +=',PFLIsDelete:"'+isDelete+'"';
					jsonArrO +=  "},";
					//alert("Del 20:" +jsonArrO);
				}
			} 
		i=i+4;
		}
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("Del aRRAY:" +jsonArrO);
	return jsonArrO; 
}



function frmPhenomenaLossMapping_beforeSubmit()
{   
	
	  var gridData =  '&selectedPillFactIDs='+getSelectdRowsPillFact('grdphenomenaMapping','factoryPillar_checkbox','checkPillarvalue');
	//  alert("1:" +gridData);
	
	  var gridDataDel='&deleteLinkList='+getSelectdRowsPillFactDel('grdphenomenaMapping','factoryPillar_checkbox','checkPillarvalue'); 
	  // alert("gridDataDel: " +gridDataDel);
	  gridData+=gridDataDel;
	  var check=gridData.substr(22);
	  var gridValue='';
	 /* if(jQuery("#grdphenomenaMapping").jqGrid('getRowData').length>0)
		{
			gridValue = convertGridTo("grdphenomenaMapping");
			 
		}*/
	  var factId=jQuery("#hdnFact").val();
	 	 if(factId==null||factId==""||factId==''){}
	 	 else
		 {
	 	 	gridData+='&factId='+factId;
		 }	 	 
	 //	alert("1:" +gridData);
	 	var flid = jQuery("#frmPhenomenaLossMapping input[id='flid']").val();
	 	var elemtype = jQuery("#frmPhenomenaLossMapping input[id='elementType']").val();
	 	
	 	 var drillLevel=jQuery("#hdnDrill").val();
	 
	 	 gridData+='&drillLevel='+drillLevel;
	 	 
	 	 gridData+='&flid='+flid;
	 	 gridData+='&elemtype='+elemtype;
	 	
	 	//var paramArr='['+gridValue.substring(0, gridValue.length-1)+']';
	 	
	 	//gridData+='&paramArr='+paramArr;
	 //	alert(" final: " +gridData);
	  return gridData ; 
	  
}
function frmPhenomenaLossMapping_successsCallback(result)
{
	jQuery("#grdphenomenaMapping").trigger("reloadGrid");
}

function convertGridTo(jqGridId){
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var countCols =  jQuery("#"+jqGridId).jqGrid('getGridParam', 'colNames').length;
	var colIds  = jQuery("#"+jqGridId).jqGrid('getGridParam', 'colNames');
	var jsonArrO='';
	var val = "";
	var colval="";
	var flag=true;
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '{';
		var j=0;
		for(var colName in row) {
			//alert(colName+"colName");
			if(row[colName].substring(0,3)=='VPC')
				//jsonArrO +=  row[colName] +',';
				//jsonArrO += '"txtVsrdVserKeyid":"' + row[colName] +'",';
			
			if(row[colName].substring(0,3)=='VSP')
				jsonArrO += '"txtVsrdVsprKeyid":"' + row[colName] +'",';
			
			if(row[colName].substring(0,6)!='<input')
			{
				jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
			}
			else
			{
				var x=row[colName].indexOf("id=")+4;
				var y=row[colName].substring(x);
				var z = y.indexOf('"');
				var cId = y.substring(0,z);
				
				if( jQuery("#"+cId).attr("type") == "checkbox")
				{
						val =   jQuery('#'+cId).is(':checked') ? 'Y':'' ;
				} 
				else
					    val =   jQuery('#'+cId).val() ;

				jsonArrO += '"'+colName +'":"' + val +'",'; 
			if (val=='')
			{
				j++;
			}
				 
			}
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO"+jsonArrO);
	if(flag== true)
	return jsonArrO;
	else return false; 
	}





</script>
<form id="frmPhenomenaLossMapping" >
		
        <div  id="frmPhenomenaLossMappingFuntKeyIds" style="margin-left:110px;margin-top:5px;" >
			<div  class="easyui-paddingbfpx" id="pflfunLocation">
					 <input type="hidden" id="factory" name="cmbPlosFactoryid" value="${requestScope.factId}"  ></input>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.flid }"  ></input>
				</div>
				<div id="pflfunLocations" style="width:994px;margin-left:110px;"></div>
			</div> 
		<input id="btnView" class="easyui-button" type="button" style="width: 49px; margin-left:85%; margin-top:10; height: 22px; cursor: default;" value="VIEW" name="btnView">

<div id="wrapperRpt" style="margin-top:20px;margin-left:108px;" >
	<table id="grdphenomenaMapping" ></table>
	<div id="grdphenomenaMappingpager"></div></div>
	<input type="hidden" id="mode" name="create"/>
	<input type="hidden" id="hdnFact" />
	<input type="hidden" id="hdnCmpy"/>	
	<input type="hidden" id="hdnSect"/>
	<input type="hidden" id="hdnCell"/>
	<input type="hidden" id="hdnDrill" value="${requestScope.drillLevel}"/>
	
	
	<input type="hidden" id="hdnflid" value=""/>
	<input type="hidden" id="hdndeptid" value=""/>
	
	<input type="hidden" id="modechk" />
	<input type="hidden" id="pos"/>
	<input type="hidden"  id="txtFormType" name="txtFormType" value="${requestScope.type}"/>
</form>