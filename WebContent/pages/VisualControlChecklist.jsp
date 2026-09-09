
<script type="text/javascript">
var abnColClicked=false;
jQuery(document).ready(function()
{
	initialiseForm('frmvisualCheck');
	jQuery('#submitForm').val('frmvisualCheck');
	var Keyid = jQuery("#hdnVcclKeyid").val();
	 //alert(Keyid);
	formatDateBox('dteVcclDate', 'dd-MMM-yyyy');
	

	var emp= jQuery("#hdnemp").val();
	var url = jQuery('#hiddenUrl').val();
	 var tableCaption = "Visual Control Check ";
	 var filterString = "";
	 
	 fillComboBox("frmvisualCheck","cmbVcclTitle","combo_title.visc");	 
	fillComboBox("frmvisualCheck","cmbVcclEmployeeid","employee.commonFilter");   
	//fillComboBox("frmvisualCheck","cmbVcclApprovedby","employee.commonFilter");   
	
	var factId = jQuery("#frmvisualCheck input[id='factory']").val();
	var sectionId = jQuery("#frmvisualCheck input[id='section']").val();
	var cellId = jQuery("#frmvisualCheck input[id='cell']").val();
	var machId = jQuery("#frmvisualCheck input[id='machine']").val();
	var flid =jQuery("#frmvisualCheck input[id='flid']").val();

	
	var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	loadFunctionalLocation("visualcheckfunLocation","functionalLoc.visc","visualcheckfunLocation","frmvisualCheck",dataStr);
	 var mode= jQuery('#mode').val();
	fileManagerPopUp("","VISUAL","frmvisualCheck","btnfileVisualmgr","VisualFilemgr", mode);

	 if(Keyid.trim() != '' || Keyid.length<0)
		 filterString = "?q=2&masterkeyid="+Keyid;
	

	 //viewGrid(url,filterString,tableCaption);	
		
	 setTimeout(function() {
		var title = getFieldValue("cmbVcclTitle");
		visualviewbutton(title);			
	},1000);
	 

		jQuery("#btnview").click(function(){
			jQuery("#hdnVcclKeyid").val('');
			if (commonValidation()==false)
				return false;
			
			var title = getFieldValue("cmbVcclTitle");
			visualviewbutton(title);			
		 });

		jQuery("#cmbVcclTitle").combobox({onRequest:function( ){
			var flid =jQuery("#frmvisualCheck input[id='flid']").val();
			return "flid="+flid;
		}});
		
		jQuery('#dteVcclDate').datebox({  	   
		   	onSelect:function(recordid)
				{
		   			jQuery("#VisualGrid").clearGridData();
		   			vcclDateEvt();
				} 
		   });

		
});

function commonValidation() {
	var vcclDate = jQuery('#dteVcclDate').datebox("getValue");
	var title = getFieldValue("cmbVcclTitle");
	var preparedId = getFieldValue("cmbVcclEmployeeid");
	if (vcclDate=='' || vcclDate=='' ) {
		popupCommonErrorMsg(" Select Date" );
		return false;
	}
	if (title=='' || title=='' ) {
		popupCommonErrorMsg(" Select Title " );
		return false;
	}
	if (preparedId=='' || preparedId=='' ) {
		popupCommonErrorMsg(" Select Prepared By " );
		return false;
	}
	return true;
}

function vcclDateEvt()
{
	var currentDate = getServerDateTime();
	var targetDate = jQuery('#dteVcclDate').datebox("getValue");
	
	if(convertStringToDate(targetDate) > currentDate)
	{
		popupCommonErrorMsg ('Date Should Not Greater than Current Date');
		fillWithCurrentDate('dteVcclDate');
	}
	else
	    clearValidationErrorMsg('dteVcclDate');
	
}

function visualviewbutton(title){
	var flid =jQuery("#frmvisualCheck input[id='flid']").val();
	var masterkeyid = jQuery("#hdnVcclKeyid").val();
	var vcclDate = jQuery('#dteVcclDate').datebox('getValue');
	jQuery("#hdnopenAbn").val("N");
	//alert('?q=2&title='+title+"&flid="+flid+"&masterkeyid="+masterkeyid);
	var ds ='?q=2&title='+title+"&flid="+flid+"&masterkeyid="+masterkeyid+'&date='+vcclDate;
	processGridnew('Visual_input.visc',ds,"VisualGrid","pager","Entry","docDoubleClick","","loadComFunction");	
}
function loadVisualControlCheckListCreation(keyid){
	 var condStr = "";
	 if( keyid != undefined && keyid != null )
		 condStr = "&keyid=" + keyid;
	 LoadPopUp("visualDetail","VisualControlPopup_input.visc?&condStr="+condStr,true,"75%","75%","7%","15%", "","VISUAL");
} 
function btnfileVisualmgr_click() {
	
	var mode= jQuery('#mode').val();
	if (mode=="view") {
		var vcclKeyid = jQuery("#hdnVcclKeyid").val();
		fileManagerPopUp(vcclKeyid,"VISUAL","","","",mode);
	}
	else
		saveForm("frmvisualCheck","VisualControlChart_save.visc?&Type=fileMng","");
}
			
		
function frmvisualCheck_FuntLocHierarchy_SuccessCallBack(result)
{
	
	var flid = result.flId;
	jQuery("#cmbVcclFlid").val(flid);
	reloadCombo("frmvisualCheck","cmbVcclTitle","combo_title.visc?q&flid="+flid);
//alert("flid  "+flid );
	var vcclDate=getFieldValue("dteVcclDate");
	if (vcclDate=='' || vcclDate==' ' )
		fillWithCurrentDate("dteVcclDate");

	reloadCombo("frmvisualCheck","cmbVcclEmployeeid","employee.commonFilter?&flid="+flid);
}
function frmvisualCheck_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	navigateToPrevForm();
	clearForm('frmvisualCheck');
	
}


function opnactionplan(rowid){
	saveForm("frmvisualCheck","VisualControlChart_save.visc","");
 	 var rowData = jQuery("#VisualGrid").jqGrid('getRowData',rowid);
	 var keyid = rowData.KEYID;
	 var mstkeyid =rowData.MASTERKEYID;
	// alert(mstkeyid);
	 var flid =jQuery("#frmvisualCheck input[id='flid']").val();
	 var detectDate = getFieldValue("dteVcclDate");
	 openActionPlan("visualActionPlan",mstkeyid,"vlst",flid,"",keyid, detectDate);
	}
function Actionplan(id, options, rowObject){
	var rowId= options.rowId;
	return '<input type="button" class="easyui-button" id="btnvisualactionplan'+rowId+'" name="btnvisualactionplan'+rowId+'" onclick="opnactionplan('+rowId+')" value="..."  style="height:21px;width:30px"/>';

	
}
function visualActionPlan_onClose(){

	jQuery("#VisualGrid").trigger("reloadGrid");
	return true;
}


function frmvisualCheck_successsCallback(result){
	abnColClicked=false;
	//alert("chk the val");
	var url = jQuery('#hiddenUrl').val();
	var locationid=jQuery("#hdnlocationid").val();
    jQuery("#hdnVcclKeyid").val(result.successData.vcclKeyid);
	 if(result.successData.type=="fileMng")
	{
		 var mode= jQuery('#mode').val();
		fileManagerPopUp(result.successData.vcclKeyid,"VISUAL","","","",mode);		
	}
	 else if(result.successData.type=="Abn"){
		 var desc = jQuery("#hdnVal").val();
		 var openAbn = jQuery("#hdnopenAbn").val();
		 var flid =jQuery("#frmvisualCheck input[id='flid']").val();
		 var  filterString = "&flid="+flid+"&detectedBy="+getFieldValue('cmbVcclEmployeeid')+"&remarks="+desc;
		 filterString += "&refDocId="+result.successData.vcdtKeyid+"&refDoctype=VCC";
		 
		 var rowId = jQuery("#VisualGrid").jqGrid('getGridParam', 'selrow');
		 
		 //alert(filterString);
		 if(openAbn=="Y"){
			 jQuery("#hdnopenAbn").val("N");
			 var forwardData = result.forwardData;
			 var persistentData = {"keyid":result.successData.vcclKeyid};
			 
			navigateToNextForm("MultipleAbnormality_input.abnForm?q"+filterString,"MultipleAbnormality",forwardData,persistentData);	 
		 //navigateToNextForm("Abnormality_input.abnForm?q"+filterString,"Abnormality",forwardData,persistentData);
			 	
		 return ;
		 }
	 }
	else{
	//alert("else");
	clearForm('frmvisualCheck');
	navigateToPrevForm();
	}
	 
	 var keyId=result.successData.vcclKeyid;
	 var title = getFieldValue("cmbVcclTitle");
	 visualviewbutton(title);
}

	function viewGrid(url,dataString,tableCaption) {
		processGridnew("Visual_input.visc",dataString,"VisualGrid","pager","Entry","docDoubleClick","","loadComFunction");
	}
	
	function untick(ids,colname,dtlKeyid,colKeyid)
	 {
		
		
		  if(colname == "Yes")
			{
			  jQuery("#VisualGrid").setCell(ids, "Yes",' ',{'background-color':'#FFFFFF'},{'visual':'true','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
			     jQuery("#VisualGrid").setCell(ids, "No",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
			     jQuery("#VisualGrid").setCell(ids, "N/A",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
			     enableUIButton("btnvisualactionplan"+ids);
			}
		else if(colname == "No")
			{  
			 jQuery("#VisualGrid").setCell(ids, "Yes",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "No",' ',{'background-color':'#FFFFFF'},{'visual':'true','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "N/A",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     disableUIButton("btnvisualactionplan"+ids);
				}
		else if(colname == "N/A")
			{ 
			  
			 jQuery("#VisualGrid").setCell(ids, "Yes",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "No",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "N/A",' ',{'background-color':'#FFFFFF'},{'visual':'true','KEYID':'','val':'-','dtlKeyid':dtlKeyid});
		     enableUIButton("btnvisualactionplan"+ids);
			 }else{jQuery("#VisualGrid").setCell(ids, "Yes",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':colKeyid,'val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "No",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':colKeyid,'val':'-','dtlKeyid':dtlKeyid});
		     jQuery("#VisualGrid").setCell(ids, "N/A",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':colKeyid,'val':'-','dtlKeyid':dtlKeyid});}
		  		
	 }
	
	  function tick(ids,columnNumber,keyid,dtlKeyid,no,conf)
	  { 
		// alert(columnNumber);
		untick(ids,"");
		  var tickVal ;	
		   if (isIE()) 
		   { 
			   if(columnNumber == "10")
	  			{
				   tickVal = "&#x2713;";
				   disableUIButton("btnvisualactionplan"+ids);
			        }else if(columnNumber == "11")
		  			{	  			
			  			  tickVal = "&#10005;";
			  			enableUIButton("btnvisualactionplan"+ids);
			  			  
				        }else if(columnNumber == "12")
			  			{
							tickVal = "&#45;";
				  			disableUIButton("btnvisualactionplan"+ids);
				        }else					        
		        			enableUIButton("btnvisualactionplan"+ids);
		   }
     	   else
		   { 
     		  if(columnNumber == "10")
  			{
     	  			 tickVal = "&#10003;";
     	  			disableUIButton("btnvisualactionplan"+ids);
		        }else if(columnNumber == "11")
	  			{
		  			
		  			  tickVal = "&#10005;";
		  			enableUIButton("btnvisualactionplan"+ids);
			        }else if(columnNumber == "12") 
		  			{
			  			tickVal = "&#45;";
		  				disableUIButton("btnvisualactionplan"+ids);
			        }else
			        	enableUIButton("btnvisualactionplan"+ids);
		      
		   }
		   
		  if(columnNumber == "10")
			{
			 jQuery("#hdnVal").val("Y");
			  var yes = jQuery("#hdnVal").val();
			//  alert(yes);
				jQuery("#VisualGrid").setCell(ids, 'Yes', tickVal,{'font-size':'15px','color':'green'},{'visual':'true','KEYID':keyid,'val':yes,'dtlKeyid':dtlKeyid});
				disableUIButton("btnvisualactionplan"+ids);
				}
		else if(columnNumber == "11")
			{	
				jQuery("#hdnVal").val("N"); 
			 	var yes = jQuery("#hdnVal").val();  
			// 	alert(yes);
			 	enableUIButton("btnvisualactionplan"+ids);
			 	jQuery("#VisualGrid").setCell(ids, 'No', tickVal,{'font-size':'15px','color':'red'},{'visual':'true','KEYID':keyid,'val':yes,'dtlKeyid':dtlKeyid});
			 	var desc  = "";
			 	desc = jQuery("#VisualGrid").jqGrid('getCell',ids,'Title') +"-";
			 	desc += jQuery("#VisualGrid").jqGrid('getCell',ids,'Criteria') + "-";
			    desc += jQuery("#VisualGrid").jqGrid('getCell',ids,'CheckPoints');
			
			    jQuery("#hdnVal").val(desc);
			    /* if(conf){	 	
				var openAbn = confirm("Do You want Open Abnormality");
				if(openAbn)
					saveForm("frmvisualCheck","VisualControlChart_save.visc?&Type=Abn","");
			    } */
			    var openAbn = jQuery("#hdnopenAbn").val();
			    var abnId =jQuery("#VisualGrid").getCell(ids, 'AbnormalityNo.');
			    var mode= jQuery('#mode').val();
			    if(mode!="view"){
			    	if (abnId.length<3){
			    		//if(openAbn=="Y")
			    			//saveForm("frmvisualCheck","VisualControlChart_save.visc?&Type=Abn","");
			    	}
			    }
			}
		else if(columnNumber == "12")
			{ 
			  jQuery("#hdnVal").val("X"); 
				 var yes = jQuery("#hdnVal").val();
				 jQuery("#VisualGrid").setCell(ids, 'N/A', tickVal,{'font-size':'15px','color':'black'},{'visual':'true','KEYID':keyid,'val':yes,'dtlKeyid':dtlKeyid});
				 disableUIButton("btnvisualactionplan"+ids);
					 }
	  }
 function docDoubleClick(ids,options)
 {	 
	 var cellId = jQuery("#frmvisualCheck input[id='cell']").val();
 if (cellId == null || jQuery.trim(cellId).length == 0) {
     popupCommonErrorMsg("Select JH");
     return;
 }
	 var mode= jQuery('#mode').val();
	// alert(mode);
	 if(mode=="view")
		 return false;
	 
	    var rowData = jQuery("#VisualGrid").jqGrid('getRowData',ids);
	    var row = jQuery("#VisualGrid").jqGrid('getDataIDs');
	    var isTotalRow = rowData.CheckPoints;
	    if(row.length == ids && isTotalRow.trim()=='PERCENTAGE')
	    	return false;
	    
		var keyid = rowData.MASTERKEYID;
		jQuery("#hdnopenAbn").val("Y");
	 //  alert(keyid);
		//LoadPopUp("visualDetail", "VisualControlPopup_input.visc?q=2&keyid="+keyid, true,"70%","80%","0%","10%", "","visual","",true);
		//
		
		var colKeyid = jQuery("#VisualGrid").jqGrid('getCell',ids,"KEYID");
		var dtlKeyid = jQuery("#VisualGrid").jqGrid('getCell',ids,"DETAILKEYID");
	    var columnNumber = jQuery('#colNo').val();
	 //   alert(columnNumber);

		var abnId =jQuery("#VisualGrid").getCell(ids, 'AbnormalityNo.');
		//alert(abnId);
		if (abnId.length>3)
	    	return;
	    
	    
	    if(  columnNumber != null && columnNumber != "" && parseInt(columnNumber) >=10  ){
		    var cm = jQuery("#VisualGrid").jqGrid("getGridParam", "colModel");
		    var checkTickYes = jQuery("#VisualGrid").jqGrid('getCell',ids,cm[columnNumber].name);
		    if(checkTickYes.trim().length <=0){
		    	
		    	 // ── NEW: same sequential-fill validation as beforeSubmit ──────────────
	            var allRows = jQuery("#VisualGrid").jqGrid('getDataIDs');
	            var currentRowIndex = jQuery.inArray(ids, allRows); // 0-based index

	            for (var j = 0; j < currentRowIndex; j++) {
	                var prevRowData = jQuery("#VisualGrid").jqGrid('getRowData', allRows[j]);

	                // Skip the PERCENTAGE summary row
	                if (prevRowData.CheckPoints && prevRowData.CheckPoints.trim() == 'PERCENTAGE')
	                    continue;

	                var prevYes = jQuery("#VisualGrid").jqGrid('getCell', allRows[j], 'Yes');
	                var prevNo  = jQuery("#VisualGrid").jqGrid('getCell', allRows[j], 'No');
	                var prevNa  = jQuery("#VisualGrid").jqGrid('getCell', allRows[j], 'N/A');

	                if (prevYes.trim().length == 0 && prevNo.trim().length == 0 && prevNa.trim().length == 0) {
	                    alert("Please select any one (Yes/No/N/A) for each checkpoint.!");
	                    return false;  // Block ticking until previous rows are filled
	                }
	            }
	            // ── END NEW ───────────────────────────────────────────────────────────
	            
		        tick(ids,columnNumber,colKeyid,dtlKeyid,"",true);
		       // alert("abnColClicked:"+abnColClicked);
		        if(columnNumber==11 && abnColClicked==false) {
		        	abnColClicked=true;
		        	saveForm("frmvisualCheck","VisualControlChart_save.visc?&Type=Abn","");
		        	
		        }
		    }
		    else
			    {
		    	untick(ids,cm[columnNumber].name,dtlKeyid,colKeyid);
		     }
	    }
	    
	    	     
}

 function frmvisualCheckcmbVcclTitle_onClear(record) {
	 jQuery("#VisualGrid").clearGridData();
 }
 
 function frmvisualCheckcmbVcclTitle_onSelect(record)
 {
			 var titleid=record.id;
			 /*var titleid= jQuery("#cmbVcclTitle").val();
			 alert(titleid);*/
			var title = getFieldValue("cmbVcclTitle");
			 
			 jQuery("#VisualGrid").clearGridData();
			// visualviewbutton(title);			

			//processAjaxCalls("Vclcombo_title.visc?&titleid="+titleid, "",'titlesuccessCallBack','');	
	 }

function titlesuccessCallBack(result){
	//alert(result[0][0]);
	loadFunctionalLocation("visualcheckfunLocation","functionalLoc.visc","visualcheckfunLocation","frmvisualCheck","&flid="+result[0][0]);
		
}




function dteVcclDate_onClear(date){
	jQuery("#VisualGrid").clearGridData();
}

function frmvisualCheckcmbVcclEmployeeid_onSelect (record){
	jQuery("#VisualGrid").clearGridData();
}

function frmvisualCheckcmbVcclEmployeeid_onClear(date){
	jQuery("#VisualGrid").clearGridData();
}

 function dteVcclDate_onSelect(date){		//date	
		var currentDate = getServerDateTime();

		if( date > currentDate)
		{					
			jQuery('#dteVcclDate').datebox('clear');
			showValidationErrorMsg('dteVcclDate','Should Not Exceed Current Date');	
		}
		else
			clearValidationErrorMsg('dteVcclDate');
		
		jQuery("#VisualGrid").clearGridData();
	}
 
 function loadComFunction(ids)
	 {
	 var row = jQuery("#VisualGrid").jqGrid('getDataIDs');
	 var tickVal ;
	 var criteriaId;
	 var colNumber;
	 var dtlKeyid;
	//alert("4");
	var vcclKeyid ="";
	
	if (row.length>0)
		vcclKeyid =jQuery("#VisualGrid").jqGrid('getCell',row[1],"VCCLKEYID");
	
	
	jQuery("#hdnVcclKeyid").val(vcclKeyid);
	
	for(var i=0;i<row.length;i++)
	 {
			
		 if (i==(row.length-1)) {
			 var idd=parseInt(i)+1;
			 var perVal = jQuery('#VisualGrid').getCell(idd,"CheckPoints");
			 if (perVal=="PERCENTAGE") {
				 jQuery("#VisualGrid").setCell(idd, "SNO",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "Criteria",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "CheckPoints",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "Yes",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "No",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "N/A",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
				 jQuery("#VisualGrid").setCell(idd, "Action Plan",'',{'background-color':'','font-size':'12px','font-weight':'bold'});
			 }
		 } 
		 else {
		  tickVal = jQuery("#VisualGrid").jqGrid('getCell',row[i],"CHECK");
		  criteriaId = jQuery("#VisualGrid").jqGrid('getCell',row[i],"KEYID");
		  dtlKeyid = jQuery("#VisualGrid").jqGrid('getCell',row[i],"DETAILKEYID");
		  //alert(tickVal);
		  if("Y" == tickVal)
			  colNumber= "10";
		  else if("N" == tickVal)
			  colNumber="11";
		  else if("X" == tickVal)
			  colNumber="12";
		  else
			  colNumber=" "; 
		  
		  var yesVal = jQuery("#VisualGrid").jqGrid('getCell',row[i],'Yes');
		  var noVal = jQuery("#VisualGrid").jqGrid('getCell',row[i],'No');
		  var naVal = jQuery("#VisualGrid").jqGrid('getCell',row[i],'N/A');
		  
		  
		  if (colNumber!=" " && (yesVal.trim().length>0 || noVal.trim().length>0 || naVal.trim().length>0 ))
		  	tick(row[i],colNumber,criteriaId,dtlKeyid,"",false);
		  
		 }
	 }
	 
	 jQuery("#VisualGrid").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue)
			 {
				jQuery('#colNo').val(cellidx);
			}
 	 });
	 
	 setViewMode();
}
 
 function setViewMode() { 
	 var mode= jQuery('#mode').val();
		
		if(mode=="view"){
			disableForm("frmvisualCheck");
			readOnlyFields("dteVcclDate");
			readOnlyFields("btnfileVisualmgr");
			readOnlyFields("btnview");
			//readOnlyFields("cmbVcclEmployeeid");
			disableField('frmvisualCheck','cmbVcclEmployeeid');
		    //disableField('frmvisualCheck','cmbVcclApprovedby');
			disableField('frmvisualCheck','cmbVcclTitle');
			jQuery('#wrapperRpt').append('<div style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:20%;"> </div>');
		}
		else {
			 jQuery('.panel-title').filter(function(){
				    return jQuery(this).parent('div').parent('div').hasClass('panel layout-panel layout-panel-center');
				}).text("Entry");
		}
			

 }

 function frmvisualCheck_beforeDelete()
 {
	 var mode= jQuery('#mode').val();		
	if(mode=="view")
		return false;
		
	var mstKeyid=jQuery("#hdnVcclKeyid").val();
	 if(mstKeyid==null || mstKeyid=="" || mstKeyid==" ")
	 	return false;
	 else
		 var data=confirm("Are You Sure to Delete?");
	 if(data)
		 return gridData;
	 else
		 return false;
	 
 }


 
/*  function frmvisualCheck_beforeSubmit()
 {
	 
		var gridData  = 'visualCheckData='+visualCheckDatas();	
		//alert(gridData );
		var rowId = jQuery("#VisualGrid").jqGrid('getGridParam', 'selrow');
		return gridData+"&rowId="+rowId;
		
	}
 
  */
  
  function frmvisualCheck_beforeSubmit()
  {
	  
	  var cellId = jQuery("#frmvisualCheck input[id='cell']").val();
	  
	    if (cellId == null || jQuery.trim(cellId).length == 0) {
	        popupCommonErrorMsg("Select JH");
	        return false;
	    }
	    
      // Validate entire grid before saving
      var row = jQuery("#VisualGrid").jqGrid('getDataIDs');
      var allRowsFilled = true;
      var emptyRows = [];
      
      for(var i = 0; i < row.length; i++) {
          // Skip the last row if it's the PERCENTAGE row
          var rowData = jQuery("#VisualGrid").jqGrid('getRowData', row[i]);
          var checkPoints = rowData.CheckPoints;
          
          if(checkPoints && checkPoints.trim() == 'PERCENTAGE') {
              continue; // Skip percentage row
          }
          
          // Check if any option (Yes/No/N/A) is selected
          var yesVal = jQuery("#VisualGrid").jqGrid('getCell', row[i], 'Yes');
          var noVal = jQuery("#VisualGrid").jqGrid('getCell', row[i], 'No');
          var naVal = jQuery("#VisualGrid").jqGrid('getCell', row[i], 'N/A');
          
          // If all three are empty, this row is not filled
          if(yesVal.trim().length == 0 && noVal.trim().length == 0 && naVal.trim().length == 0) {
             // allRowsFilled = false;
              emptyRows.push(i + 1); // Store row number (1-based)
          }
      }
      
      for (var i = 1; i < emptyRows.length; i++) {
    	    if (emptyRows[i] !== emptyRows[i-1] + 1) {
    	    	allRowsFilled = false;
    	    	abnColClicked=false;
    	    	var id = emptyRows[i] - 1
    	    	var dtlKeyid = jQuery("#VisualGrid").jqGrid('getCell',id,"DETAILKEYID");
    	    	jQuery("#VisualGrid").setCell(id, "No",' ',{'background-color':'#FFFFFF'},{'visual':'false','KEYID':'','val':'','dtlKeyid':dtlKeyid});
    	        break;
    	    }
    	}
      
      // If any row is not filled, show alert and prevent save
      if(!allRowsFilled) {
          var message = "Please select any one (Yes/No/N/A) for each checkpoint!";
          //message += "Empty rows: " + emptyRows.join(", ");
          alert(message);
          
          return false;
      }
      
      // All rows are filled, proceed with save
      var gridData = 'visualCheckData=' + visualCheckDatas();	
      var rowId = jQuery("#VisualGrid").jqGrid('getGridParam', 'selrow');
      return gridData + "&rowId=" + rowId;
  }
	function visualCheckDatas(){
	//alert('s');
		var jsonArrO='[';
		
		jQuery("#VisualGrid").find('td[visual="true"]').each (function()
				{//alert('1');
					jsonArrO += '{';
					var aria=jQuery(this).attr('KEYID');
					var val=jQuery(this).attr('val');
					var dtlKeyid = jQuery(this).attr('dtlKeyid');
					jsonArrO += "txtVcdtCriteriaval :\""+val+"\",";
					jsonArrO += "txtVcdtVccdKeyid :\""+ aria+"\",";
					jsonArrO += "txtVcdtKeyid :\""+ dtlKeyid+"\"";
					jsonArrO +=  "},";
				});
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		//alert("jsonArrO   "+jsonArrO);
		return jsonArrO; 

	//	disableForm("frmvisualCheck");
		}
	
	

</script>


<form name="frmvisualCheck"  id="frmvisualCheck">
	<div id="wrapperRpt" style="">
	<div style="margin-top:-10px;">
			<table>
				<tr>
					<td>
						<div id="frmvisualCheckFuntKeyIds"  >							
									<input type="hidden" id="factory" name="factory" value=" "  ></input>			
									<input type="hidden" id="section" name="section" value=" "  ></input>
									<input type="hidden" id="cell" name="cell" value=" "  ></input>
									<input type="hidden" id="machine" name="machine" value=" "  ></input>
									<input type="hidden" id="flid" name="cmbVcclFlid" value="${requestScope.visualcontrol.vcclFlid }"></input>
									<input type="hidden" id="elementId" name="cmbelementid" value="${requestScope.visualcontrol.elementid }"></input>							
						</div>						
						<div id="visualcheckfunLocation" style="width:600px;"></div>
					</td>
					<td>
		             <div style="position:relative;">
					 <span  id="VisualFilemgr" style="position:absolute;right:-380px;" >
     		
                    </span> 
                    </div>
             
             </td>
					</tr>
					</table>
					<table>
					<tr>
					<td valign="top" style="">
						<div class="easyui-paddingbfpx">
							<label class="mandatory-lbl">Title</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbVcclTitle" name="cmbVcclTitle"  value="${requestScope.visualcontrol.vcclTitle }" style=" width : 200px;"  />
<!--							 <span id="err_cmbVcclEmployeeid" class="tpm-errormsg"></span>-->
						</div>
					</td>
					<td valign="top" style="padding-left: 10px;"> 
						<div class="easyui-paddingbfpx" style=" ">
							<label class="mandatory-lbl">Date</label>
						</div>
						<div >
							<input id="dteVcclDate" name="dteVcclDate" class="easyui-datebox" style="width:100px;" value="${requestScope.visualcontrol.vcclDate}"/>
<!--					<span id="err_dteVcclDate" class="tpm-errormsg"></span>-->
						</div>
					</td>
					<td  valign="top" style="padding-left: 10px;">
						<div class="easyui-paddingbfpx">
							<label class="mandatory-lbl" >Prepared by</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbVcclEmployeeid" name="cmbVcclEmployeeid" value="${requestScope.visualcontrol.vcclEmployeeid}" style=" width : 200px;"  />
<!--							 <span id="err_cmbVcclEmployeeid" class="tpm-errormsg"></span>-->
						</div>
					</td>
					
					<%-- <td valign="top" style="padding-left: 10px;">
						<div class="easyui-paddingbfpx">
							<label class="mandatory-lbl" >Approved by</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbVcclApprovedby" name="cmbVcclApprovedby" value="${requestScope.visualcontrol.vcclApprovedby}" style=" width : 200px;"  />
<!--							 <span id="err_cmbVcclEmployeeid" class="tpm-errormsg"></span>-->
						</div>
					</td>
					 --%>
					<td  style="padding-left: 10px;">
						<div class="easyui-paddingbfpx" style="margin-top: 15px">
						<input type="button" class="easyui-button" style="width:75px;" id="btnview" name="btnview" value="View"/>
						</div>
						</td>
					</tr>					
					<tr>
					<td><span id="err_dteVcclDate" class="tpm-errormsg"></span></td>
					<td style="padding-left:32px;"><span id="err_cmbVcclEmployeeid" class="tpm-errormsg"></span></td>
					</tr>
					</table>
					
			<!--<div class="sub-header" style="text-align: left;width:123%;width:830px\9; height:25px\9; position:relative;margin-left:-1px;"><span style="position: absolute;">Visual</span>
 			<div class="sub-header" style="text-align: left;width:1106px;width:830px\9; height:25px\9; position:relative;margin-left:-1px;"><span style="position: absolute;">Visual</span>
 		<span id="sopFileMgr" style="  position:absolute ;right:0px;right:-2\9;top:-1; ">

<img  class="" style="cursor: pointer;z-index:210;height:-10px;height:26px\9;" src="images/addbtsub.png" title="Add skill" alt="" id="btnVisualCheckList"/>
								</span>
							</div>	
			
			-->
			<div class="visualGriddiv">
				<table id="VisualGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
		</div>
		<div id="visualDetail"></div>
		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" /> 
		<input type="hidden" id="hdnVcclKeyid" name="hdnVcclKeyid" value="${requestScope.visualcontrol.vcclKeyid}"/>
		<input type="hidden" id="colNo" value=""/>
		<input type="hidden" id="hdnVal" value=""/>
		<input type="hidden" id="hdnopenAbn" value="N"/>
		<input type="hidden" id="hdnemp" value="${requestScope.mode}"/>
		<input type="hidden" id="hdnlocationid" name="hdnlocationid" value="${requestScope.locationid}">
</form>

