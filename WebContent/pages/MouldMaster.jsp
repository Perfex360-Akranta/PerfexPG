<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">	
	
jQuery(document).ready(function(){	
	    
 		initialiseForm('frmMould');   
 		//alert(MouldMaster);
 	   processGridnew('mould_input.mld','?q=2',"Machine","pager2","","","","MouldrefreshComplete"); 		
 	   processGridnew("Product_input.mld","?q=2","ProductGRID","pager3","","","","ProdRefreshComplete"); 
 	   processGridnew("Phen_input.mld","?q=2","PhenMouldGrid","pager4","","","","PhenRefreshComplete");
 		 
 	   jQuery('#submitForm').val('frmMould'); 		
       fillComboBox("frmMould","cmbMldmmouldid","combo_mould.mld" );
       fillComboBox("frmMould","cmbMldmowner","combo_mouldOwner.mld" );
       fillComboBox("frmMould","cmbMldmcurrentlocation","combo_location.mld" );
       fillComboBox("frmMould","cmbMldmsupplierid","combo_Supplier.mld" );
       fillComboBox("frmMould","cmbMldmsize","combo_Size.mld" );
       fillComboBox("frmMould","cmbMldmweight","combo_weight.mld" );
       fillComboBox("frmMould","cmbEqpmain","combo_Eqpmain.mld" ); 
       fillComboBox("frmMould","cmbMchmKeyid","combo_cmbMchmKeyid.mld" );
       fillComboBox("frmMould","cmbMldmmouldstatus","combo_Status.mld" );       
       fillComboBox("frmMould","cmbFactkeyid","combo_cmbFactkeyid.mld" );
       fillComboBox("frmMould","cmbYcmdkeyid","combo_cmbYcmdkeyid.mld" );
       fillComboBox("frmMould","cmbPdfmkeyid","combo_cmbPdfmkeyid.mld" );  
       var typeval=jQuery('#hdnType').val();
      
       if(typeval.trim() != '' && typeval.length>0){
    	   
    	   jQuery('#cboMldmtype').val(typeval);
           }
       
       var statusval=jQuery('#hdnStatus').val();
       
       if(statusval.trim() != '' && statusval.length>0){
    	   
    	   jQuery('#cboMldmmouldstatus').val(statusval);
           }

      if(jQuery('#cmbMldmmouldid').combobox("getValue") != null && jQuery('#cmbMldmmouldid').combobox("getValue") != '' && jQuery('#cmbMldmmouldid').combobox("getValue") != ' ')
          {
 				//alert('if...'+jQuery('#cmbMldmmouldid').combobox("getValue"));
 				 disableField('frmMould','cmbMldmmouldid');
          }
      else
          {
    	 // alert('else..');
         
          }
      fileManagerPopUp(getFieldValue('cmbMldmmouldid','frmMould'),"MLD","frmMould","btnFileManager","mouldMstFileMgr");
         
   });
       var factId = jQuery("#frmMould input[id='factory']").val();
       var sectionId = jQuery("#frmMould input[id='section']").val();
       var cellId = jQuery("#frmMould input[id='cell']").val();
       var machId = jQuery("#frmMould input[id='machine']").val();

       var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  	
		
       loadFunctionalLocation("mldmfunLocation","functionalLoc.mld","mldmfunLocationValues","frmMould",dataStr);

       var mode = jQuery("#frmMould input[id = 'mode']").val();

       function isNumberKey(evt)
       {
         var charCode = (evt.which) ? evt.which : event.keyCode;
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
         return true;
      }
      
     function frmMould_successsCallback(result)
	 {	  
		Mouldreload();
		jQuery("#cmbMldmmouldstatus").combobox("setValue","A");	
		jQuery("#factory").combobox('clear');
		jQuery("#section").combobox('clear');
		jQuery("#cell").combobox('clear');
		jQuery("#Machine").combobox('clear');
	 }	 
     jQuery("#tabMould").tabs({ onSelect:function(title){  
	   //if(title == "Link Phenomena" )
		//{
			
			var dataString = "?q=2&mldKeyId="+jQuery("#cmbMldmmouldid").combobox("getValue");
			processGridnew("mould_input.mld",dataString,"Machine","pager2","Machine","","","MouldComplete");
			processGridnew("Product_input.mld",dataString,"ProductGRID","pager3","","","","MouldProdComplete");
		   	processGridnew("Phen_input.mld",dataString,"PhenMouldGrid","pager4","","","","PhenComplete");  
		//}
   }
   }); 
  
	jQuery("#btnFileManager").click(function(){
		/*var documentNo =getFieldValue('cmbMldmmouldid','frmMould');	
		
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"MLD","","");
		}
		else
 			alert("Mould should be selected to view FileManager");
		*/
	});
	function btnFileManager_click(){
		
		var documentNo =getFieldValue('cmbMldmmouldid','frmMould');	
		
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"MLD","","","");
		}
		else
 			alert("Mould should be selected to view FileManager");
		
	}
   function  frmMouldcmbMldmmouldid_onSelect(record)
   {	 
		processAjaxCalls("MouldMaster_recall.mld","?q=2&mldField="+record.id,"mouldIdRecallSuccess","mouldNoRecallError");
		processGridnew("mould_input.mld","?q=2&mldField="+record.id,"Machine","pager2","Machine","","","MouldComplete");
		processGridnew("Product_input.mld","?q=2&mldField="+record.id,"ProductGRID","pager3","Product","","","MouldProdComplete");
		processGridnew("Phen_input.mld","?q=2&mldField="+record.id,"PhenMouldGrid","pager4","","","","PhenComplete");    
		fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbGmntLineid","cmbGmntSectionid","cmbGmntFactoryid","cmbComp");   	
   }      
   function  frmMouldcmbMchmKeyid_onSelect(record)
	{ 		
		loadFunctionalLocation("mldmfunLocation","functionalLoc.mld","mldmfunLocationValues","frmMould","&machId="+record.id);		
		 var mldField = jQuery("#cmbMldmmouldid").combobox("getValue");
		 var mldEqpField = jQuery("#cmbEqpmain").combobox("getValue");
		 processAjaxCalls("eqpNo_recall.mld","?q=2&mldMchField="+record.id,"eqpRecallSuccess","eqpRecallError");		  
	}

	jQuery('#btnMchClear').click(function ()
	{		
		jQuery('#dispFunctionalLoc').html('');		
		jQuery('#cmbEqpmain').combobox('clear');
		jQuery('#cmbMchmKeyid').combobox('clear');
		//jQuery('#Machine').html('');		
		//jQuery('#pager2').html('');		
		jQuery('#Machine').clearGridData();
	});
	jQuery('#btnMchView').click(function()
		{
		 mode = 0;
		var mchmKeyid = jQuery("#cmbMchmKeyid").combobox("getValue");
		var mouldId = jQuery('#cmbMldmmouldid').combobox('getValue');		
		var mldEqpField = jQuery("#cmbEqpmain").combobox("getValue");   	
		
		var dataString = "?q=2&mldMchField="+mchmKeyid;
		if(mouldId != null && mouldId != '' && mouldId != ' ')
			dataString += "&mldField="+mouldId;
		if(mldEqpField != null && mldEqpField != '' && mldEqpField != ' ')
			dataString += "&mldEqpField="+mldEqpField;
		processGridnew("mould_input.mld",dataString,"Machine","pager2","Machine","","","MouldComplete");
		
		//processGridnew("mould_input.mld","?q=2&mldMchField="+keyIds.machId+"&mldField="+mldField+"&mldEqpField="+mldEqpField,"Machine","pager2","Machine");
		});
	
	jQuery('#btnProView').click(function()
		{
		
		var mouldId = jQuery('#cmbMldmmouldid').combobox('getValue');
		var proFact = jQuery('#cmbFactkeyid').combobox('getValue');
		var proCompId = jQuery('#cmbYcmdkeyid').combobox('getValue');
		var proFamily = jQuery('#cmbPdfmkeyid').combobox('getValue');
		//jQuery('#ProductGRID').trigger('reloadGrid');
	  	var dataProstring = "?q=2&mldField="+mouldId;
	  	if(proFact != null && proFact != '' && proFact != ' ')
	  	dataProstring += "&proFact="+proFact;
	  	if(proCompId != null && proCompId != '' && proCompId != ' ')
		  	dataProstring += "&proCompId="+proCompId;
	  	if(proFamily != null && proFamily != '' && proFamily != ' ')
		  	dataProstring += "&proFamily="+proFamily;
	    processGridnew("Product_input.mld",dataProstring,"ProductGRID","pager3","","","","MouldProdComplete");		
		});

	jQuery('#btnProClear').click(function ()
			{						
				jQuery('#cmbFactkeyid').combobox('clear');
				jQuery('#cmbYcmdkeyid').combobox('clear');
				jQuery('#cmbPdfmkeyid').combobox('clear');
				//jQuery('#ProductGRID').html('');
				//jQuery('#pager3').html('');
				jQuery('#ProductGRID').clearGridData(); 

			});
	
   function frmMould_FuntLocHierarchy_SuccessCallBack(keyIds)
   {  	   
	   	setFieldValue('cmbMchmKeyid',keyIds.machId);   	
	   	reloadMachine("frmMould",'cmbMchmKeyid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	   	processAjaxCalls("eqpNo_recall.mld","?q=2&mldMchField="+keyIds.machId,"eqpRecallSuccess","eqpRecallError");
	   	var mldField = jQuery("#cmbMldmmouldid").combobox("getValue");
		//var mldEqpField = jQuery("#cmbEqpmain").combobox("getValue");
	   	//processGridnew("mould_input.mld","?q=2&mldMchField="+keyIds.machId+"&mldField="+mldField+"&mldEqpField="+mldEqpField,"Machine","pager2","Machine");
   }
   function mouldIdRecallSuccess(result)
   { 	   
  	  	jQuery("#txtMldmslno").val(result.mould.Mldmslno);  	
  		jQuery('#txtMldmdescription').val(result.mould.Mldmdescription);
  	   	jQuery("#txtMldmnoofcavities").val(result.mould.Mldmnoofcavities);
  		jQuery('#cmbMldmowner').combobox('setValue',result.mould.Mldmowner);
  		
  		//jQuery('#cboMldmtype').combobox('setValue',result.mould.Mldmtype);
  		
  		jQuery('#cboMldmtype').val(result.mould.Mldmtype);
  		jQuery('#cboMldmmouldstatus').val(result.mould.Mldmmouldstatus);
  		
  		//jQuery('#cboMldmmouldstatus').combobox('setValue',result.mould.Mldmmouldstatus);
  		jQuery('#cmbMldmcurrentlocation').combobox('setValue',result.mould.Mldmcurrentlocation);
  		jQuery('#cmbMldmsupplierid').combobox('setValue',result.mould.Mldmsupplierid);
  		jQuery('#cmbMldmsize').combobox('setValue',result.mould.Mldmsize);
  		jQuery('#cmbMldmweight').combobox('setValue',result.mould.Mldmweight);
  		jQuery('#ProductGRID').clearGridData(); 
  		jQuery('#Machine').clearGridData(); 
  		clearValidationErrorMessages("frmMould","dispErr");  		
  		//jQuery('#cmbmldmcp').combobox('setValue',result.mould.Mldmcp);  	 		
   }
   function eqpRecallSuccess(result)
	{
	   jQuery("#cmbEqpmain").combobox('setValue',result.eqpGrp); 
	   var mchID = jQuery("#cmbMchmKeyid").combobox("getValue");
	   
	}
   function frmMould_beforeDelete()
   {
	  // var mouldId = jQuery("#frmMould input[id='cmbMldmmouldid']").val();
	   if (jQuery("#cmbMldmmouldid").combobox("getValue") == "")
	   {
		   alert("Select mould for deletion");  	 
	   }
	   else
		   {   
			   if(confirm("Do you want to inactivate?") == true)
				{				  
				   
				}
			   else
				{			   
				   return false;				   
				}
		   }
	  }

	  function frmMould_deleteSuccessCallback()
	  {
		  alert("Inactivated Sucessfully");
		  Mouldreload();  
	  }
	  function Mouldreload()
	  {			//alert("CLEAR GRID");	   
		 	reloadCombo("frmMould","cmbMldmmouldid","combo_mould.mld" );
			reloadCombo("frmMould","cmbMldmowner","combo_mouldOwner.mld" );
			reloadCombo("frmMould","cmbMldmcurrentlocation","combo_location.mld" );
			reloadCombo("frmMould","cmbMldmsupplierid","combo_Supplier.mld" );
			reloadCombo("frmMould","cmbMldmsize","combo_Size.mld" );
			reloadCombo("frmMould","cmbMldmweight","combo_weight.mld" );
			jQuery('#ProductGRID').clearGridData(); 
	  		jQuery('#Machine').clearGridData(); 
	  		jQuery('#PhenMouldGrid').clearGridData(); 
	  		//alert("CLEAR END");	
 	 }   
   function mouldIdRecallError(result)
   {
  	 alert("Error");
   }
  /* function chkbox_Machine(id, options, rowObject)
	  {					
	  	var rowId = options.rowId;
	  	alert('s');
	  	return '<input type="checkbox" id="chkmchid" value="" onclick="removeOperator(\''+rowId + '\');"/>';
	  }*/

   function chkbox_Phen(id, options, rowObject)
	  {					
	  	var rowId = options.rowId;
	  	
	  	return '<input id="Phen_checkbox" name="Phen_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxPhenCheck(\''+rowId + '\');}else{chkboxPhenUnCheck(\''+ rowId +'\')}"/>';
	  }
	 
	  function chkbox_Product(id, options, rowObject)
	  {
	  	var rowId = options.rowId;	  	
	  	return '<input id="Product_checkbox" name="Product_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxProdCheck(\''+rowId + '\');}else{chkboxProdUnCheck(\''+ rowId +'\')}"/>';
	  }
	  function chkbox_Machine(id, options, rowObject)
	  {
	  	var rowId = options.rowId;
	  
	  	return '<input id="Machine_checkbox" name="Machine_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	  }
	  function chkboxCheck(rowId)
	  {
		 
	  	jQuery("#Machine").jqGrid('setCell',rowId,'checkmachvalue','1');	
	  	
	  }
	  function chkboxUnCheck(rowId)
	  {
	  	jQuery("#Machine").jqGrid('setCell',rowId,'checkmachvalue','0');
	  	
	  }
	  function chkboxPhenCheck(rowId)
	  {
	  	jQuery("#PhenMouldGrid").jqGrid('setCell',rowId,'checkphenvalue','1');
	  
	  }
	  function chkboxPhenUnCheck(rowId)
	  {
	  	jQuery("#PhenMouldGrid").jqGrid('setCell',rowId,'checkphenvalue','0');
	  	
	  }
	  
	  function chkboxProdCheck(rowId)
	  {
	  
	  	jQuery("#ProductGRID").jqGrid('setCell',rowId,'checkvalue','1');	
	  }
	  function chkboxProdUnCheck(rowId)
	  {
	  	
	  	jQuery("#ProductGRID").jqGrid('setCell',rowId,'checkvalue','0');	
	  }
		
	function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
		
		var allRows = jQuery("#Machine").jqGrid('getRowData');
		
		var jsonArrO='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			var value = row[ckeckForSelColName];
			//alert("value"+value);		
			if( value != null  &&  value.trim()  != ""){			
				if(value == '1')				
				{
					jsonArrO += '{';
				for(var colName in row) {
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("cellValue"+cellValue);			
						if(colName == 'txtmmlkmachineid')
							{
							//alert("colName   " +colName);
							jsonArrO += '"'+colName +'":"' + cellValue+'"';
							}
				}
				jsonArrO +=  "},";
				
			}
		} 
		}
		//jsonArrO = jsonArrO.slice(0, -1) ;
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		//alert("jsonArrO--mch   "+jsonArrO);
		return jsonArrO; 
	}  
	function getSelectdRowsProd(jqGridId,checkBoxColName,ckeckForSelColName){
		
		var allRows = jQuery("#ProductGRID").jqGrid('getRowData');
		
		var jsonArrObj='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			var value = row[ckeckForSelColName];
			//alert("value"+value);		
			if( value != null  &&  value.trim()  != ""){			
				if(value == '1')				
				{
					jsonArrObj += '{';
				for(var colName in row) {
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("cellValue"+cellValue);			
						if(colName == 'txtmlplproductid')
							{
							//alert("colName   " +colName);
							jsonArrObj += '"'+colName +'":"' + cellValue+'"';
							}
				}
				jsonArrObj +=  "},";
				
			}
		} 
		}
		//jsonArrO = jsonArrO.slice(0, -1) ;
		jsonArrObj = jsonArrObj.slice(0, -1) + "]";
		jsonArrObj = (jsonArrObj != ']'?jsonArrObj:"");
		//alert("jsonArrO-prod   "+jsonArrO);
		return jsonArrObj; 
	}  

function getSelectdRowsMould(jqGridId,checkBoxColName,ckeckForSelColName){
		
		var allRows = jQuery("#PhenMouldGrid").jqGrid('getRowData');
		
		var jsonArrObj='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			var value = row[ckeckForSelColName];
			//alert("value"+value);		
			if( value != null  &&  value.trim()  != ""){			
				if(value == '1')				
				{
					jsonArrObj += '{';
				for(var colName in row) {
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("cellValue"+cellValue);			
						if(colName == 'txtMphlBphmid')
							{
							//alert("colName   " +colName);
							jsonArrObj += '"'+colName +'":"' + cellValue+'"';
							}
				}
				jsonArrObj +=  "},";
				
			}
		} 
		}
		//jsonArrO = jsonArrO.slice(0, -1) ;
		jsonArrObj = jsonArrObj.slice(0, -1) + "]";
		jsonArrObj = (jsonArrObj != ']'?jsonArrObj:"");
		//alert("jsonArrO-prod   "+jsonArrO);
		return jsonArrObj; 
	}  
	
//selecting all the checkbox on header click
	function MouldComplete_afterLoad() // added by Ramya.T
	{	//alert('mode');
		jQuery("#Machine_Machine_checkbox").click(function()
		{			//alert('machinegrid');	
					var allRows = jQuery("#Machine").jqGrid('getRowData');
					//alert('allRows'+allRows.length);
					if(mode == 0)
					{			
					for(i= 0;i<=allRows.length;i++)
						{
						jQuery('input:checkbox[name=Machine_checkbox]').attr('checked',true);
						chkboxCheck(i);
						mode = 1;
					    }
					}
					else
					{
						for(i= 0;i<=allRows.length;i++)
						{
							jQuery('input:checkbox[name=Machine_checkbox]').attr('checked',false);
							chkboxUnCheck(i);
							mode = 0;
						}						
					}				
					event.preventDefault();
		});		
	}
	function MouldProdComplete() 
	{
		jQuery('.ui-paging-info').css('font-size','12px');		
		jQuery("#ProductGRID_Product_checkbox").click(function()
		{	
					var allRows = jQuery("#ProductGRID").jqGrid('getRowData');
					if(mode == 0)
					{			
					for(i= 0;i<=allRows.length;i++)
						{
						jQuery('input:checkbox[name=Product_checkbox]').attr('checked',true);
						chkboxProdCheck(i);
						mode = 1;
					    }
					}
					else
					{
						for(i= 0;i<=allRows.length;i++)
						{
							jQuery('input:checkbox[name=Product_checkbox]').attr('checked',false);
							chkboxProdUnCheck(i);
							mode = 0;
						}						
					}				
					event.preventDefault();
		});		
	}
	function MouldrefreshComplete_afterLoad()
	{
		jQuery('#Machine').html('');
		jQuery('#pager2').html('');
		
	} 
	function ProdRefreshComplete()
	{
		jQuery('#ProductGRID').html('');
		jQuery('#pager3').html('');	
	}
	function PhenRefreshComplete()
	{
		jQuery('#PhenMouldGrid').html('');
		jQuery('#pager4').html('');	
	}

	 //------Grid save-----
	  function frmMould_beforeSubmit()
	  {
		  var gridData = '&Machine='+getSelectdRows('Machine','Machine_checkbox','checkmachvalue');
		  //alert("mch DATA "+gridData);
		      gridData     += '&Product='+getSelectdRowsProd('ProductGRID','Product_checkbox','checkvalue');

		      gridData     += '&Phenomena='+getSelectdRowsMould('PhenMouldGrid','Phen_checkbox','checkphenvalue');
	
		  //alert("gridData "+gridData);
		 
	  // return false;
	  // return;
		return gridData; 
		}	
 </script>	
       <form name="frmMould" id="frmMould" class= "" >
<div align="center"  style = "padding-left:20px;" >
  <table align = "center" >
       <tr>
	      <td >
	       	  <div  class="easyui-paddingbfpx"  style = "padding-left:100px;padding-right:100px;"><label >Mould</label></div>
<!--			  <div  class="easyui-paddingbfpx" style = "margin-left:  100px;"><input id="cmbMldmmouldid" name="cmbMldmmouldid" value="${requestScope.genTlMouldmst.mldmmouldid}"  type="text" class="easyui-combobox" style="width: 240px;" /></div>-->
                  <div  class="easyui-paddingbfpx" style = "padding-left:100px;padding-right:100px;"><input id="cmbMldmmouldid" name="cmbMldmmouldid" value="${requestScope.genTlMouldmst.mldmmouldid}"  class="easyui-combobox" style="width: 240px;" /></input></div>
		  </td>			
		  <td >
			  <div  class="easyui-paddingbfpx" style="margin-right:10px;"><label class="mandatory-lbl">Sl.No</label></div>
			  <div  class="easyui-paddingbfpx"  style="margin-right:10px;"><input id="txtMldmslno" name="txtMldmslno" value="${requestScope.genTlMouldmst.mldmslno}"  class="easyui-text" maxlength="100" style="width: 240px;" ></div>
		  </td>
	</tr>
		
	<tr>
       	<td>
       		<div  class="easyui-paddingbfpx" style = "margin-left: 100px;"><label class="mandatory-lbl">Description</label></div>	          					
			<div  class="easyui-paddingbfpx" style = "margin-left: 100px;"><input id="txtMldmdescription" name="txtMldmdescription" value="${requestScope.genTlMouldmst.mldmdescription}" type="text" class="easyui-text" maxlength="95"  style="width: 240px;" /></div>
		</td>		
		<td >
			<div  class="easyui-paddingbfpx" style="margin-right:10px;"><label class="mandatory-lbl">No of Cavities</label></div>
			<div  class="easyui-paddingbfpx" style="margin-right:10px;"><input id="txtMldmnoofcavities" name="txtMldmnoofcavities" maxlength="10" onkeypress="return isNumberKey(event)" value="${requestScope.genTlMouldmst.mldmnoofcavities}" type="text" accept="" class="easyui-text" maxlength="95"  style="width: 240px;" /></div>
		</td>
	</tr>		
	<tr>
       	<td width="100px">
       		<div  class="easyui-paddingbfpx" style = "padding-left:100px;padding-right:100px;"><label class="mandatory-lbl" >Owner</label></div>	          					
			<div  class="easyui-paddingbfpx" style = "padding-left:100px;padding-right:100px;"><input id="cmbMldmowner" name="cmbMldmowner"  value="${requestScope.genTlMouldmst.mldmowner}" class="easyui-combobox" style="width: 240px;" /></input> </div>
		</td>		
		<td width="100px" >
		<div  style="width:100px;">
		    <div  class="easyui-paddingbfpx" style="margin-right:10px;"><label class="mandatory-lbl">Type</label></div>
		    <div class="easyui-paddingbfpx" style="margin-right:10px;">
	           <span > <select id="cboMldmtype" class="easyui-combobox" name="cboMldmtype"   style="height: 22px;width:105px; ">					
					<option value=" "> </option>
					<option value="CR"> CR</option>
					<option value="HR"> HR</option>
					<option value="CM"> CM</option>									
			    </select> </span>
		
	        	<span id="mouldMstFileMgr" style="margin-left:120%;margin-top:-26px;">
<!--            	<input id="btnFileManager" name="btnFileManager" type="button" class="easyui-button"  value="File Manager"  style="width:140px; height:25px;"/> </input> -->
                </span>
            
            	</div>
				</div>
			 		
           		<span id="err_cboMldmtype" class="tpm-errormsg" style="float:left;" ></span>             	
		</td>
	</tr>		
	<tr>
		<td colspan="2" >
       				<div class="floatleft" id="costInformationTab" style="width : 700px ; height : 300px; margin-left:80px; ">
			             <div id="tabMould" class="easyui-tabs" fit="true" plain="true" style="width:100%;height : 300px;">	  
		 		            <div id="costSumTab" title="Details" style="padding:0px;">
								<div  style="padding-left:200px; "  >			
									<div class="easyui-paddingbfpx"><label class="mandatory-lbl" >Mould Status</label> </div>
										<div class="easyui-paddingbfpx" >
											<select id="cboMldmmouldstatus" class="easyui-combobox" name="cboMldmmouldstatus"  style="height: 22px;width:123px;">					
												<option value=" "> </option>
												<option value="A"> Active</option>
												<option value="U"> unload</option>
												<option value="P"> Production Mould</option>
												<option value="R"> Ready for loading</option>																		
											</select>							
				     						</div>
											<div  class="easyui-paddingbfpx"><label class="mandatory-lbl" >Current Location</label></div>
					     					<div  class="easyui-paddingbfpx"><input id="cmbMldmcurrentlocation" name="cmbMldmcurrentlocation" value="${requestScope.genTlMouldmst.mldmcurrentlocation}" type="text" class="easyui-combobox" style="width: 300px;" /></div>
					     					<div  class="easyui-paddingbfpx"><label >Supplier</label></div>
					     					<div  class="easyui-paddingbfpx"><input id="cmbMldmsupplierid" name="cmbMldmsupplierid" value="${requestScope.genTlMouldmst.mldmsupplierid}" type="text" class="easyui-combobox" style="width: 300px;" /></div>
					     					<div  class="easyui-paddingbfpx"><label >Size</label></div>
					     					<div  class="easyui-paddingbfpx"><input id="cmbMldmsize" name="cmbMldmsize" type="text" value="${requestScope.genTlMouldmst.mldmsize}" class="easyui-combobox" style="width: 300px;" /></div>
					     					<div  class="easyui-paddingbfpx"><label >Weight</label></div>
					     					<div  class="easyui-paddingbfpx"><input id="cmbMldmweight" name="cmbMldmweight" value="${requestScope.genTlMouldmst.mldmweight}" type="text" class="easyui-combobox" style="width: 300px;" /></div>     					
										</div>
							</div>				
					
				<div id="costSumTab" title="Link Machine" style="padding-top:20px; padding-left:30px; ">
						
						<div id="frmMouldFuntKeyIds">
							<input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
							<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
							<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
							<input type="hidden" id="machine" name="cmbMchmKeyid" value="${requestScope.bdmTlMst.bdmsMachineid}"  ></input>
						</div>
						<div id="mldmfunLocation"></div>
						
						<div> <label>Equipment </label></div>
       		 			  <div class="easyui-paddingbfpx">
   							<input id="cmbMchmKeyid" name="cmbMchmKeyid" class="easyui-combobox"  style="width: 300px;" /> 
						
						<div  class="easyui-paddingbfpx"><label >Equipment Main Group</label></div>
	     					<div  class="easyui-paddingbfpx"><input id="cmbEqpmain" name="cmbEqpmain"  type="text" class="easyui-combobox" style="width: 300px;" />
	     					<span><input id="btnMchClear" name="btnMchClear" type="button" class="easyui-button"  value="Clear"  style="width:100px; height:25px;"/></span>
	     					<span></span>
	     					<span><input id="btnMchView" name="btnMchView" type="button" class="easyui-button"  value="View"  style="width:100px; height:25px;"/></span>
	     					</div>
		     			<div style="width:10%;padding-right:90px;height:350px;">		
						<table id="Machine" >
							<tr><td><td/></tr></table>
							<div id="pager2"></div>
						</div>
				</div>
			
				</div>
				<div id="costSumTab" title="Link Product" style="padding-top:20px; padding-left:30px; ">
					<div> <label>Factory</label></div>
       		 		<div class="easyui-paddingbfpx">
   							<input id="cmbFactkeyid" name="cmbFactkeyid" class="easyui-combobox"  style="width: 300px;" />
   							<div > <label>Compound</label></div>       		 			  
   							<input id="cmbYcmdkeyid" name="cmbycmdkeyid" class="easyui-combobox"  style="width: 300px;" />
   							<div > <label>Product Family</label></div>
       		 			     <input id="cmbPdfmkeyid" name="cmbPdfmkeyid" class="easyui-combobox"  style="width: 300px;" />
       		 			     <span><input id="btnProClear" name="btnProClear" type="button" class="easyui-button"  value="Clear"  style="width:100px; height:25px;"/></span>
       		 			     <span></span>
       		 			     <input id="btnProView" name="btnProView" type="button" class="easyui-button"  value="View"  style="width:100px; height:25px;"/>
       		 			          		 			     
       		 		</div> 							
					<div style="width:100%;padding-right:50px;height:350px;">		
						<table id="ProductGRID">
							<tr><td><td/></tr></table>
							<div id="pager3"></div>
						</div>
				</div>
				<div id="costSumTab" title="Link Phenomena" style="padding-top:20px; padding-left:30px; ">
					<table id="PhenMouldGrid">
							<tr><td><td/></tr></table>
							<div id="pager4"></div>
						</div>
				</div>
				
			</div>
	</div>
	</td>
	</tr>
	</table>
</div>					
				
<input type="hidden" id="mode" value="0"/>
<input type="hidden" id="hdnType" value="${requestScope.genTlMouldmst.mldmtype}"/>
<input type="hidden" id="hdnStatus" value="${requestScope.genTlMouldmst.mldmmouldstatus}" />
</form> 
