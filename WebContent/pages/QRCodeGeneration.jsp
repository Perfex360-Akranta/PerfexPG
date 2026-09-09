

<script  src="js/jquery.qrcode.min.js"></script>
<script  src="js/jquery.print.js"></script>

<script>
        
jQuery(document).ready(function(){

	initialiseForm('frmQrCodeGen'); 

	jQuery('#toolsfunLocation').css("visibility", "hidden");
	   //fillComboBox("frmQrCodeGen","cmbFactoryId","factoryCombo.commonFilter" );
	   fillComboBox("frmQrCodeGen","cmbSbuId","sbuCombo.commonFilter" );
	   fillComboBox("frmQrCodeGen","cmbSectionId","sectionCombo.commonFilter" );
	   fillComboBox("frmQrCodeGen","cmbCellId","cellCombo.commonFilter" );
	   fillComboBox("frmQrCodeGen","cmbMchId","machineCombo.commonFilter" );
	   
	   //var factId = getFieldValue('factory','frmQrCodeGen');
	   var sbuId = getFieldValue('sbu','frmQrCodeGen');
		
		var sectId = getFieldValue('section','frmQrCodeGen');

		var cellId = getFieldValue('cell','frmQrCodeGen');

		var machId = getFieldValue('machine','frmQrCodeGen');	
		var flid = getFieldValue('flid','frmQrCodeGen');

   //var dataStr = "&factId="+factId+"&sectionId="+sectId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	
   var dataStr = "&sbuId="+sbuId+"&sectionId="+sectId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	
	 loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen",dataStr);
	

var dataStr="";
fillComboBox("frmQrCodeGen","cmbsection","sectionCombo.commonFilter" );		
fillComboBox("frmQrCodeGen","cmbcell","cellCombo.commonFilter" );		
fillComboBox("frmQrCodeGen","cmbmachine","machineCombo.commonFilter" );

// loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen",dataStr);



	
});

/* function frmQrCodeGencmbFactoryId_onSelect(record)
{
		jQuery("#cmbSectionId").combobox('clear');
		jQuery("#cmbCellId").combobox('clear');
		jQuery("#cmbMchId").combobox('clear');

		reloadCombo("frmQrCodeGen","cmbSectionId","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmQrCodeGen","cmbCellId","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?cellId="+record.id  );

		}
		 */
		
function frmQrCodeGencmbSbuId_onSelect(record)
{
		jQuery("#cmbSectionId").combobox('clear');
		jQuery("#cmbCellId").combobox('clear');
		jQuery("#cmbMchId").combobox('clear');

		/* reloadCombo("frmQrCodeGen","cmbSectionId","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmQrCodeGen","cmbCellId","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?cellId="+record.id  ); */
		reloadCombo("frmQrCodeGen","cmbSectionId","sectionCombo.commonFilter?sbuId="+record.id);
		reloadCombo("frmQrCodeGen","cmbCellId","cellCombo.commonFilter?sbuId="+record.id  );
		reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?sbuId="+record.id  );

		}

function frmQrCodeGencmbSectionId_onSelect(record)
{ 
		jQuery("#cmbCellId").combobox('clear');
		jQuery("#cmbMchId").combobox('clear');
		lodFuncLoc("onsel"+record.id);
		//loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen","&machId="+record.id);
		loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen","&sectId="+record.id);

		reloadCombo("frmQrCodeGen","cmbCellId","cellCombo.commonFilter?sectId="+record.id  );
		//reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?cellId="+record.id  );
		reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?sectId="+record.id  );

}


//lodFuncLoc("onsel"+record.id);
function frmQrCodeGencmbCellId_onSelect(record)
{ 
	lodFuncLoc("onsel"+record.id);

		//jQuery("#cmbMchId").combobox('clear');
		reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?cellId="+record.id  );
	   //loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen","&machId="+record.id);
	   loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen","&cellId="+record.id);

	//	processAjaxCalls("machineHierarchy.commonFilter","?q=2&machineId="+record.id,"machinIdRecallSuccess","machineIdRecallError");
}

function frmQrCodeGencmbMchId_onSelect(record)
{ 
	//alert(record.id+"88");
	lodFuncLoc("onsel"+record.id);

		//jQuery("#cmbMchId").combobox('clear');
		//reloadCombo("frmQrCodeGen","cmbMchId","machineCombo.commonFilter?cellId="+record.id  );
	   loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen","&machId="+record.id);

	//	processAjaxCalls("machineHierarchy.commonFilter","?q=2&machineId="+record.id,"machinIdRecallSuccess","machineIdRecallError");
}

function lodFuncLoc(datStr){
//	alert(9876 +' datStr '+datStr);
	var compId = getFieldValue('company','frmQrCodeGen');	
	var locnId = getFieldValue('location','frmQrCodeGen');	
	//var factId = getFieldValue('factory','frmQrCodeGen');	
	var sbuId = getFieldValue('sbu','frmQrCodeGen');
	var sectId = getFieldValue('section','frmQrCodeGen');	
	var cellId = getFieldValue('cell','frmQrCodeGen');	
	var machId = getFieldValue('machine','frmQrCodeGen');	
	var flid = getFieldValue('flid','frmQrCodeGen');
	var relTo = '';	
	//var dataStr = "&factId="+factId+"&sectionId="+sectId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	var dataStr = "&sbuId="+sbuId+"&sectionId="+sectId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	var url = jQuery('#hiddenUrl').val();
	//alert(url+" url");	
	var vurl = url.substring(0,url.indexOf('?'));	
  if(datStr !=' ' && datStr !='' && datStr != null){
	relTo = "?relTo="+datStr;

if(datStr.substring(0,5) == "onsel"){
	dataStr = removeValueFromUrl(dataStr,"machId"); 
 	dataStr += "&machId="+datStr.substring(5,datStr.length);
}	

alert(dataStr+" url");	
loadFunctionalLocation("toolsfunLocation","functionalLoc.tlmn","toolsfunLocationValues","frmQrCodeGen",dataStr);


}
}

function sectionIdRecallSuccess(result)
	 {	 
		
 		//displayText('cmbFactoryId',result.sectionHierarchy.factory);
 		displayText('cmbSbuId',result.sectionHierarchy.sbu);
 	//	jQuery('#cmbbdmsFactoryid').combobox('disable');
	
	 }

	 function cellIdRecallSuccess(result)
	 {
		//displayText('cmbFactoryId',result.cellHierarchy.factory);
		displayText('cmbSbuId',result.cellHierarchy.sbu);
 	   	displayText('cmbSectionId',result.cellHierarchy.section);
 	 //displayText('cmbCellId',result.cellHierarchy.cell);          	 	          		
 	//	jQuery('#cmbbdmsFactoryid').combobox('disable');
 //	jQuery('#cmbbdmsSectionid').combobox('disable');
 	///	jQuery('#cmbbdanCostcentre').combobox('disable');
	
}
	 

	
	 function machinIdRecallSuccess(result)
	 {	 
		

		 //displayText('cmbFactoryId',result.machineHierarchy.factory);
		 displayText('cmbSbuId',result.machineHierarchy.sbu);
	 	 displayText('cmbSectionId',result.machineHierarchy.section);
	 	 displayText('cmbCellId',result.machineHierarchy.cell);  
	 	// displayText('cmbMchId',result.machineHierarchy.cell);  

	
	 }

	 function frmQrCodeGen_FuntLocHierarchy_SuccessCallBack(keyIds)
		{
			setFunctionalLocWidth("frmQrCodeGen","220px");
			var cellId=keyIds.cellId;
			//var factId=keyIds.factId;
			var sbuId=keyIds.sbuId;
			var sectId=keyIds.sectId;
		//	alert(cellId+"fact" + factId);
		/* 	if(keyIds.factId != undefined && keyIds.factId != null && keyIds.factId != ''){
				setFieldValue('cmbFactoryId',factId);
					jQuery('#cmbFactoryId').combobox('disable');
				} */
				
			if(keyIds.sbuId != undefined && keyIds.sbuId != null && keyIds.sbuId != ''){
				setFieldValue('cmbSbuId',sbuId);
					jQuery('#cmbSbuId').combobox('disable');
				}
				if(keyIds.sectId != undefined && keyIds.sectId != null && keyIds.sectId != '')
				{						
						setFieldValue('cmbSectionId',sectId);
						jQuery('#cmbSectionId').combobox('disable');

						reloadCombo("frmQrCodeGen","cmbCellId","cellCombo.commonFilter?sectId="+keyIds.sectId);


				}
				if(keyIds.cellId != undefined && keyIds.cellId != null && keyIds.cellId != '')
				{						
						setFieldValue('cmbCellId',cellId);
					//	alert("cmbcell "+cellId);
					
					//reloadCombo("frmBDMaster","cmbbdmsAssemblyid","assembly.commonFilter?machineId="+ keyIds.machId );
					fillCellHierarchy("cellHierarchy.commonFilter",keyIds.cellId,"","","","", "","");	
							
					//fnEnableDisCommon(); 
				//	}
				}
			if(keyIds.machId != undefined && keyIds.machId != null && keyIds.machId != '')
			{	
					
				fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","");	
				
				//fnEnableDisCommon(); 
			//	}
			   
			}
			
			
	 }
jQuery("#btnView").click(function(){
	//alert(12);QRC
var cellid=jQuery("#cmbCellId").combobox('getValue');;
//alert(23);
var sectId=	jQuery('#cmbSectionId').combobox('getValue');
//var factId=	jQuery('#cmbFactoryId').combobox('getValue');
var sbuId=	jQuery('#cmbSbuId').combobox('getValue');
var mchId=	jQuery('#cmbMchId').combobox('getValue');

//var DataString="&cellId="+cellid+"&sectId="+sectId+"&factId="+factId+"&mchId="+mchId;

var DataString="&cellId="+cellid+"&sectId="+sectId+"&sbuId="+sbuId+"&mchId="+mchId;
//alert(12345 + ' ..... '+DataString);

	processGridnew("QREquipmentGrid_input.qrcgn","q=2"+DataString,"gridQrCodeList","gridQrCodeListpager","","","gridQrCodeList_ErrorComplete","gridQrCodeList_loadComplete");
	////alert(123456 + ' ..... '+DataString);

});



function gridQrCodeList_loadComplete(ids){
	
}


function SelectFormater(id, options, rowObject)
{
	var rowId = options.rowId;
	
	
	return '<input id="selectCheckbox_'+rowId+'" name="selectCheckbox" '+ (rowObject[0]=="0" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}


function selectData(rowId)
{
	jQuery("#gridQrCodeList").jqGrid('setCell',rowId,'checkVal','1');	
}

function unselectData(rowId)
{
	jQuery("#gridQrCodeList").jqGrid('setCell',rowId,'checkVal','0');
} 
function imgFormater(cellvalue, options, rowObject){
    var rowId = options.rowId;
    
    //alert(rowId +" rowid");
	return '<div id="qrCodee_'+rowId+'"></div>';
	

		}

jQuery("#btnGenQRCode").click(function(){
	
				var dataIDs = jQuery("#gridQrCodeList").getDataIDs();
				for( var i = 1; i <= dataIDs.length;i++){
					var isChecked = jQuery("#selectCheckbox_"+i).is(':checked');
					var img=null;
					if(isChecked == true)
					{
						
						 var  celValue = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'MACHINENO');
						 var  QRC = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'QRCODE');

							
						 
						 // console.log(canvas);
						// alert(jQuery('#qrCodee_'+i+',canvas').text()+"mlk");
					
						 //  alert(QRC);
						   var txt = jQuery('#qrCodee_'+i+' canvas').text();
						  
						   if(QRC=='<div id="qrCodee_'+i+'"><canvas width="100" height="100"></canvas></div>'){
							 
						   }
						   else{
							   jQuery('#qrCodee_'+i).qrcode({width:100,height:100,text:celValue});
								  var canvas = jQuery('#qrCodee_'+i+' canvas');
								   img = canvas.get(0).toDataURL("image/png"); 

					     	 '<div id="qrCodee_'+i+'><img src='+img+'></div>';
						   }
						 
					}

			}
			
	                                                                                 
});
jQuery("#btnPrntQRCode").click( function(){
	//jQuery('#printContent').print({});
	//window.open('\pages\\qrPrint.html', '', 'height=400,width=800');

var divContents = jQuery("#qrcBody").html();
//jQuery('#qrCodee').qrcode({width:100,height:100,text:"celValue"});	
var canvas = jQuery('#qrCodee canvas');

//-------------------------------
var dataIDs = jQuery("#gridQrCodeList").getDataIDs();
var printWindow = window.open('', '', 'height=400,width=800');

for( var i = 1; i <= dataIDs.length;i++){
	var isChecked = jQuery("#selectCheckbox_"+i).is(':checked');
	var img=null;
	if(isChecked == true)
	{
		//alert(i.length);
		 var  celValue = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'MACHINENO');
		 var  mchName = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'MACHINENAME');
		 var  sectCode = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'SECCODE');
		 var  cellCode = jQuery('#gridQrCodeList').jqGrid ('getCell', i, 'CELCODE');

		//  jQuery('#qrCodee_'+i).qrcode({width:100,height:100,text:celValue});
		  var canvas = jQuery('#qrCodee_'+i+' canvas');
		 // console.log(canvas);
		   img = canvas.get(0).toDataURL("image/png"); 

	

    printWindow.document.write('<html><head><title>Qr Code Print</title>');
    printWindow.document.write('</head><body >');
    printWindow.document.write('<table>');
    printWindow.document.write('<div style="margin-left:250px; margin-top:50px;">');

    printWindow.document.write('<table>');
    printWindow.document.write('<tr><td>'); 

    printWindow.document.write('<div style="margin-top:50px;" id="qrCodee_'+i+'"><img src='+img+'>');
    printWindow.document.write('<BR> </BR>');
    printWindow.document.write('<P> '+celValue+' - '+mchName+' </P>');
    printWindow.document.write('<P> '+sectCode+' - '+cellCode+'</P></div>');

    printWindow.document.write('</td></tr>');
    printWindow.document.write('<tr><td>'); 


    printWindow.document.write('</td></tr>');
    printWindow.document.write('<table>');
    printWindow.document.write('</div>');
   // printWindow.document.write('<input type="button" id="btnprnt" name="btnprnt" onClick=window.print();   class="easyui-button" value="prnt" style="width: 90px" />');

    printWindow.document.write('</body></html>'); 
   // 
   // alert(1234);
	}
}
printWindow.document.close();
	printWindow.print();

}); 


</script>
<form id="frmQrCodeGen" name="frmQrCodeGen">

<div id="wrapperRpt">
<div style="margin-top: -10px">

  <div> 
  	<!-- <span style="padding-left:10px;"><label class="mandatory-lbl">Factory</label></span>  -->
  	<span style="padding-left:10px;"><label class="mandatory-lbl">Sbu-Pbu</label></span> 
  	
  	<span style="margin-left:150px;"><label class="mandatory-lbl">Section</label> </span>
  	<span style="margin-left:240px;"><label class="mandatory-lbl">Cell</label></span>`
	</div>
	</div>
	<div style="margin-top: 5px">
			<span style="margin-left:10px;">
				<!-- <input id="cmbFactoryId" name="cmbFactoryId" value=""  class="easyui-combobox" style="width: 180px" /> -->
				<input id="cmbSbuId" name="cmbSbuId" value=""  class="easyui-combobox" style="width: 180px" />
			   </span>
			   <span style="margin-left:20px;">
			    <input id="cmbSectionId" name="cmbSectionId" value=""  class="easyui-combobox" style="margin-left:20px; width: 270px" />
				</span>
			   <span style="margin-left:20px;">
				<input id="cmbCellId" name="cmbCellId" value=""  class="easyui-combobox" style="padding-left:20px; width: 290px" />
			
			</span>
			
		
			</div>
			<div style="margin-top:10px;">
			
  			<span style="padding-left:5px;"><label>Equipment</label></span> 
			</div>
			<div style="margin-top:0px;">
			
			<span style="margin-left:10px;">
				<input id="cmbMchId" name="cmbMchId" value=""  class="easyui-combobox" style="padding-left:20px; width: 290px" />
			
			</span>
			<span style="margin-left:20px;">
				<input type="button" id="btnView" name="btnView"   class="easyui-button" value="View" style="width: 90px" />
			</span>
			<span style="margin-left:20px;" >
				<input type="button" id="btnGenQRCode" name="btnGenQRCode"   class="easyui-button" value="Generate QR Code" style="width: 150px" />
			</span>
			
			<span style="margin-left:20px;" >
				<input type="button" id="btnPrntQRCode" name="btnPrntQRCode"   class="easyui-button" value="Print QR Code" style="width: 150px" />
			</span>
			</div>
			
			

</div>

<div style="margin-top:10px;"> 
	<div style="margin-left:40px; "> 
	 <table id="gridQrCodeList" ><tr><td/></tr></table>
	 <div id="gridQrCodeListpager"></div>
</div>
</div>
<div style="margin-top: -10px">
 						<input type="hidden" id="factory" name="cmbfactory" value="${requestScope.toolTlDtl.factory}"  ></input>
 						<input type="hidden" id="section" name="cmbsection" value="${requestScope.toolTlDtl.section}" ></input>
						<input type="hidden" id="cell" name="cmbcell" value="${requestScope.toolTlDtl.cell}" ></input>
						<input type="hidden" id="machine" name="cmbmachines" value="${requestScope.toolTlDtl.machine}"></input>
						<input type="hidden" id="flid" name="cmbflid" value="${requestScope.toolTlDtl.flid}"></input>
						
					</div>
	<div id="toolsfunLocation" style="padding-left:30px; display:none;"></div>
<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}" />
 						<input type="hidden" id="hdnSect" name="hdnSect" value="${requestScope.Section}"></input>						
		     	         <input type="hidden" id="hdnCell" name="hdnCell" value="${requestScope.Cell}"></input>
		     	         <input type="hidden" id="hdnMch" name="hdnMch" value="${requestScope.Machine}"></input>						
		     	         
</form>