<script>
	jQuery(document).ready(function() { 
		initialiseForm('frmCriticality');
		jQuery('#submitForm').val('frmCriticality');
		
		fillComboBox("frmCriticality","cmbCasmDoneby","employee.commonFilter");
		fillComboBox("frmCriticality","cmbCasmTradeid","Combo_Trade.abnForm");
		formatDateBox('dteCasmDate', 'dd-MMM-yyyy');
		if (getFieldValue("dteCasmDate").trim().length<=0 ){
			fillWithCurrentDate('dteCasmDate');
		}
		fillWithCurrentDate('hdncurrentdate');
		
		var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnViewTemplate").val(btnName);										
		jQuery("#btnView").click(function() {
			var flid = jQuery("#frmCriticality input[id='flid']").val(); 
			var jhid = jQuery("#frmCriticality input[id='cell']").val();
			//alert("flid "+flid);
			//var chkHead="0";
		    /* if (jQuery('#chkHead').is(':checked'))
		    		chkHead='1'; */
		    		if (jhid=='' || jhid==' ') 
			    	{		    			
		    			popupCommonErrorMsg(" Select JH " );		    			
		    		}
		    		else
			    	{
		    			viewgrid('q=2&chkHead='+'&flid='+flid);
			    	}
		    		
			
		});
		
		jQuery("#btnViewCriteria").click(function(){
			var flid = jQuery("#frmCriticality input[id='flid']").val(); 
			var jhid = jQuery("#frmCriticality input[id='cell']").val();
		    		if (jhid=='' || jhid==' '){		    			
		    			popupCommonErrorMsg(" Select JH " );		    			
		    		}
		    		else{
		    			 var dataString="&flid="+flid;
		    			// alert(dataString);
		    			 LoadPopUp("CriteriaDef","criteriamasterPopup_input.mchrnkskl?q=2"+dataString,true,"100%","85%","1%","14%","","View Criteria Definition","","",true,"setFileManagerdimension");	
			    	}
		});
		
		jQuery("#btnViewTemplate").click(function() {
							//alert("Read From File");
				 processAjaxCalls("openFile.file?fileName=541 Criticality Assessment.xls","", "", "", "", "new");
			});
                    
		var factId = jQuery("#frmCriticality input[id='factory']").val();
		var sectionId = jQuery("#frmCriticality input[id='section']").val();
		var cellId = jQuery("#frmCriticality input[id='cell']").val();
		var machId = jQuery("#frmCriticality input[id='machine']").val();
		var flid = jQuery("#frmCriticality input[id='flid']").val();

		var dataStr = "&factId=" + factId + "&sectionId="+ sectionId + "&cellId=" + cellId + "&machId="+ machId + "&flid=" +flid;//FNLN00000004

		loadFunctionalLocation("CASMfunLocation","functionalLoc.cras","CASMfunLocationValues", "frmCriticality",dataStr);
		
		jQuery('#btndlgDeleteRp').click( function(){	
		    removedata();
		});	
		
		jQuery('#btnCriteria').click(function(){
	       	 var htmlDiv="<div id='divCriteria' style=' '> <table cellspacing='10' border='1' rules='all' style='font-size:14px;border:solid 1px #c1c1c1;'>";
	       	 htmlDiv+=	 "<thead style='background-color:lightskyblue;border:solid 1px #c1c1c1;'> <tr> <th style='border:solid 1px #c1c1c1;'>Rank</th> <th style='border:solid 1px #c1c1c1;'>Mark</th> </tr> </thead><tbody style='border:solid 1px #c1c1c1;'>";
	       	 htmlDiv+=  "<tr> <td style='padding:5px;border:solid 1px #c1c1c1; '>Rank A</td> <td style='padding:5px;border:solid 1px #c1c1c1; '>80 and Above</td> </tr>"; 
	       	 htmlDiv+=	"<tr> <td style='padding:5px;border:solid 1px #c1c1c1; '>Rank B</td> <td style='padding:5px;border:solid 1px #c1c1c1; '>60 and above</td> </tr>"; 
	       	 htmlDiv+=	"<tr> <td style='padding:5px;border:solid 1px #c1c1c1; '>Rank C</td><td style='padding:5px;border:solid 1px #c1c1c1; '>40 and above</td></tr>"; 
	       	 htmlDiv+=	"<tr> <td style='padding:5px;border:solid 1px #c1c1c1; '>Rank D</td><td style='padding:5px;border:solid 1px #c1c1c1; '>40 and Below</td></tr>"; 
	       	 htmlDiv+=	"</table></div>";
	       	//Above code not used
	       	var flid = jQuery("#frmCriticality input[id='flid']").val();
	       	LoadPopUp("divCriteria","criteriamaster_input.mchrnkskl?flid="+flid, true,"75%","72%","100px","10%", "criteriaOk_Callback","Criteria Definition"," ","true" );
	       	// jQuery('#loadPopUpdivCriteria').append(htmlDiv);
       });
		
		var rmks = getFieldValue('hdnremarks');
		setFieldValue('txtCasmRemarks',rmks);
		
	});

function btnMultipleEup(cellValue, options, rowObject){
	  var rowId = options.rowId;
	  var colId = options.pos;	  
	  return '<input type="button" id="btngrdCriticallity_'+rowId+'" name="btngrdCriticallity_'+rowId+'" onclick="grdBtnClick('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="Multiple Equipment"/>';
}	
function grdBtnClick(rowid){
		var selArray =  jQuery("#grdCriticallity").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';   
	       if(selArray.length>1){	   
		for(var i=0;i<selArray.length;i++)
		{
	       selrowid=selArray[i];
			 	jsonArr+=selrowid+",";		 	
		 	}
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
            jQuery("#hdnKeysArr").val(jsonArr);
LoadPopUp("divMultiple","assCritical_input.cras?&keys="+jsonArr,true,"27%","376px","1px","359px", "showResult_successCallBack","Multiple Equipment ",true);
		 }
		 	else{
		 		popupCommonErrorMsg("Select atleast Two Equipments");
			 	//alert("Select atleast Two Equipments");	
			 	}
	       }
	  	 		
function removedata(){  
	var selArray =  jQuery("#grdCriticallity").jqGrid('getGridParam', 'selarrrow');
	var selrowid="";
	 var jsonArr='';
    if(selArray !=null && selArray!=" " && selArray!=""){
	var r = confirm("Do You Want To Delete?");
	for(var i=0;i<selArray.length;i++)
	{
       selrowid=selArray[i];
       //jsonArr+='[';
	   var Keyid =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"KEYID");
	   var Criteriasplit= Keyid.split(',');
	 	for(var k=0;k<Criteriasplit.length;k++){
		 	var Keyidval=Criteriasplit[k]; 
		 	var keyvalSplit=Keyidval.split(";");
		 	var keyvalu=keyvalSplit[k];
		 	jsonArr += '"'+keyvalu + '",';	
 	    }
	 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += ',';
	  //  alert("jsonArr "+jsonArr);
	}	
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
    var DeleteCriteriaList=jsonArr;
    if(r==true)
    processAjaxCalls("criticalityassessmentMst_Remove.cras", "DeleteCriteriaList="+ DeleteCriteriaList, 'remove_successCallBack','remove_errorCallBack');
    else
        return false;
    }
    else 
        alert("Select Equipment");
        return false;
} 

function remove_successCallBack(result){
		//alert(result.successData.msg);
		jQuery("#grdCriticallity").trigger("reloadGrid");
    }
function frmCriticality_successsCallback(result){
		//alert(" Inside successcallback ");		
		jQuery('#grdCriticallity').trigger("reloadGrid");
		if(result.successData.mode == "Modify") {
	        alert(result.successData.msg);
	        // Stay on current page for modify
	    } else {
	        // For new records, go back to grid
	        alert(result.successData.msg);
	        clearForm("frmCriticality");
	        navigateToPrevForm();
	    }
		  
	}
function viewgrid(filter) {
	processGridnew("criticalityassessmentMst_input.cras", filter,"grdCriticallity","pager", "", "", "", "ongridcompletecallback");
	}	

function ongridcompletecallback(){
	 var row=jQuery("#grdCriticallity").jqGrid('getDataIDs');
	
	 for(var i=0;i<row.length;i++){
		 var rowid=row[i];
		 var totalVal=jQuery("#grdCriticallity").jqGrid('getCell', rowid,"TOTALRATING");
		 if(parseInt(totalVal)>0)
			 jQuery("#grdCriticallity").jqGrid('setCell',rowid,"EQUIPMENT",'',{'background-color':'#99FFCC'});
	 }
		
}
function frmCriticality_FuntLocHierarchy_SuccessCallBack(result) {
		var flid = jQuery("#frmCriticality input[id='flid']").val();
		var celid = jQuery("#frmCriticality input[id='cell']").val();
		var machine = result.machId;
	    var chkHead="0";
	    //alert(celid.lenght);
	    if(celid=='' || celid==' ')
	    {
	    	//alert('Select JH');
	    	viewgrid('q=2&flid=123454566'+'&machine='+ '&chkHead=');
	    }
	    else
	    {   
	    	if (jQuery('#chkHead').is(':checked'))
	    		chkHead='1';
	    	  // alert("Head"+chkHead);
	    	var tradeid = getFieldValue('cmbCasmTradeid');
	    	var crytype = getFieldValue('hdncrytype');
	    	 
			viewgrid('q=2&flid=' + flid + '&machine=' + machine+ '&chkHead='+chkHead+'&tradeid='+tradeid+'&crytype='+crytype);
	    		
	    }
			//alert(flid);
	    if(flid != '' || !flid != ' ' || !flid != undefined)
		 {      
		 	  
			 reloadCombo("frmCriticality","cmbCasmDoneby","employee.commonFilter?&flid="+flid);
			 
			 
		 }
	}	
function frmCriticalitycmbCasmTradeid_onSelect(result)
{   
	var flid = jQuery("#frmCriticality input[id='flid']").val();
	var celid = jQuery("#frmCriticality input[id='cell']").val();
	var tradeid = result.id ;
	//alert(tradeid);
	if(celid == '' || celid == ' ' || celid == undefined)
	{   setFieldValue('cmbCasmTradeid','');
		popupCommonErrorMsg(" Select JH " );
		
	}else
	{
		 if(flid != '' || flid != ' ' || flid != undefined)
		 {  
			viewgrid('&flid=' + flid + '&tradeid=' + tradeid);
		 }
	}
}

function frmCriticalitycmbCasmTradeid_onClear()
{
	var flid = jQuery("#frmCriticality input[id='flid']").val();
	viewgrid('&flid=' + flid );
}


function frmCriticality_beforeSubmit() {
	//var Keyid =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"KEYID");
	//alert("Keyid:::"+Keyid);
		var errText="";
		var errFlg=false;
		var gridData="";		
		var flid = jQuery("#frmCriticality input[id='flid']").val();
		var DoneBy = jQuery("#cmbCasmDoneby").combobox('getValue');
		var date =getFieldValue('dteCasmDate');
		
		var jhid = jQuery("#frmCriticality input[id='cell']").val();
	
		if (jhid=='' || jhid==' ') {
			errFlg=true;
			popupCommonErrorMsg(" Select JH " );
			return false;
		}
		
		if(flid==null || flid==''){
			//alert("Select JH");
			errFlg=true;
			errText+="Select JH,";
		}
		if(date==null || date==''){
			//alert("Select Date");
			errFlg=true;
			errText+="Select Date,";
		}	
		if(DoneBy==null ||DoneBy==' '||DoneBy==''){ 
			//alert("Select DoneBy");
			errFlg=true;
			errText+="Select DoneBy,";	         
		}
		var currentDate = getFieldValue('hdncurrentdate');
		//alert(currentDate  + 's  '+  date);
		if(convertStringToDate(date) > convertStringToDate(currentDate))
		{  
			errFlg=true;
			errText+="Selected date is Greater than current date , ";
			alert('Selected date is Greater than current date' );				
			return false;
						
		}
		var selArray =  jQuery("#grdCriticallity").jqGrid('getGridParam', 'selarrrow');
		//alert(selArray);
        if(selArray.length>0){
   	            /*var griddata = "grdCriticallityData="+ convertCriteriaGridToJSONArr('grdCriticallity');
    	   // alert("griddata "+griddata);
			return griddata;*/										
			var criticality = "&grdCriticallityData="+ convertCriteriaGridToJSONArr('grdCriticallity');
			var errCriticality = getFilterValue(criticality+'&', 'errText');
			if(errCriticality.trim().length<=0){	
				gridData =criticality;  
			}
			else{
				//alert('errText'+errText);
				errFlg=true;
				errText+=errCriticality;
			}			
			//alert("convertjson"+gridData);	
			//alert("errFlg"+errFlg);	
       }
       else{
		         // alert("Select Equipment");
		    errFlg=true;
			errText+=" Select Equipment ";
	   }	  
		
		if(errFlg==true){
			setTimeout(function() {
				showCommonErrorMsg(errText);
			}, 200);
			div_err();
			return false;
		}
		else{
			if(gridData.trim().length>0){
				//alert("convertjson  "+gridData);	
				return gridData;
			}
			else
				return false;
		}
		
	}

	function convertCriteriaGridToJSONArr(jqGridId){           
        var selArray =  jQuery("#" + jqGridId).jqGrid('getGridParam', 'selarrrow');
    	var selrowid="";
		var jsonArr='[';
		var flg=false;	
		var errMsg="";	
		for(var i=0;i<selArray.length;i++)
		{
			 var errFlgRow=false;
			 var errMsgRow="";
			 selrowid=selArray[i];
			 
			 //alert("selrowid:"+selrowid);
			 	var Keyid =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"KEYID");
			 	 //	alert("Keyid"+Keyid);				 
			    if(Keyid==null || Keyid =="" ||Keyid==" "){	
			        var colModel = jQuery("#grdCriticallity").jqGrid("getGridParam","colModel");
			 	    //alert("colModel "+colModel.length);	 	 
			 	  // var colname=getFieldValue("CH0-"+colNameVar);
		 	        for(var j=8;j<colModel.length-2;j++){   
		 	        	 var colNameVar=parseInt(j)-6;
		 	        	 var colname=jQuery("#CH0-"+colNameVar).html();
		 	        	 //alert("j:"+ j+",colNameVar:"+colNameVar+",colname:"+colname);
				 	     var criVal=(colModel[j].name).split("_");	
				 	     var criteriaID=criVal[1];   
				 	     var MachineId =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"MACHINEID");
				 	    var totalRating =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"TOTALRATING");		 	
				 	     var paramid=colModel[j].name+"_grdCriticallity_"+selrowid;		 	
				 	     var parameterval=jQuery("#frmCriticality input[id='"+paramid+"']").val();
				 	     if(parameterval.trim().length<=0 || parseInt(parameterval)==0){
				 	    	//alert("colname:"+ getFieldValue("CH0-"+colNameVar));			
							if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
							errMsgRow=errMsgRow + " " + colname +" ";
							errFlgRow=true;
						 }		
						 jsonArr+= '{';
					     jsonArr += '"txtCasmKeyid":"'+Keyid+'",';
						 jsonArr += '"txtCasmEquipmentid":"'+MachineId+'",';
						 jsonArr += '"txtCasmCriteriaid":"'+criteriaID+'",';
						 jsonArr += '"txtCasmTotalRating":"'+totalRating+'",';
						 jsonArr += '"txtCasmScores":"'+parameterval+'"},';
						// alert("jsonArr "+jsonArr);
		 	           }
				 }
			   else if(Keyid!=null || Keyid !="" ||Keyid!=" "){
			 		 var Criteriasplit= Keyid.split(',');
				 	 
				 	 for(var k=0;k<Criteriasplit.length;k++){
				 		
					 	 var Keyidval=Criteriasplit[k]; 
					 	 var keyvalSplit=Keyidval.split(";");
					 	  //alert("Keyidval "+Keyidval+" <> "+keyvalSplit[0]);
					 	 var criteria=keyvalSplit[1]; 
					 	 var keyvalu=keyvalSplit[0];
					 	  //alert(keyvalu+"  - Crit "+criteria);
				 		 var colModel = jQuery("#grdCriticallity").jqGrid("getGridParam","colModel");
				 	     for(var j=8;j<colModel.length-2;j++){ 
				 	    	 var colNameVar=parseInt(j)-6;
			 	        	 var colname=jQuery("#CH0-"+colNameVar).html();
			 	        	 
					 		 var criVal=(colModel[j].name).split("_"); 
						 	 var criteriaID=criVal[1]; 
					 	     var MachineId =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"MACHINEID");
					 	    var totalRating =jQuery("#grdCriticallity").jqGrid('getCell', selrowid,"TOTALRATING");	
					 	     var paramid=colModel[j].name+"_grdCriticallity_"+selrowid;		 	
						 	 var parameterval=jQuery("#frmCriticality input[id='"+paramid+"']").val();	
					 			
					 		// alert("criteria:"+criteria+",criteriaID:"+criteriaID);
						 	 if(criteria==criteriaID){	
							 	// alert("criteria:"+criteria);	
						 		 if(parameterval.trim().length<=0 || parseInt(parameterval)==0){
							 		// alert("j:"+ j+",colNameVar:"+colNameVar+",colname:"+colname);
						 	    	// alert("colname:"+ getFieldValue("CH0-"+colNameVar));			
									if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
									errMsgRow=errMsgRow + " " + colname +" ";
									errFlgRow=true;
								 }							 		 	 			 	  	 	
							 	 jsonArr+= '{';
								 jsonArr += '"txtCasmKeyid":"'+keyvalu+'",';
								 jsonArr += '"txtCasmEquipmentid":"'+MachineId+'",';
								 jsonArr += '"txtCasmCriteriaid":"'+criteriaID+'",';
								 jsonArr += '"txtCasmTotalRating":"'+totalRating+'",';
								 jsonArr += '"txtCasmScores":"'+parameterval+'"},';
								// alert("jsonArr "+jsonArr);
						 	 }
				 	      }
			    	}
			}
		    if (errFlgRow==true){
				errMsg=errMsg + " Enter " + errMsgRow + " in Row " + parseInt(selrowid) + "  " ;
				flg=true;
				for ( var colName in selrowid) {
					jQuery("#grdCriticallity").jqGrid('setCell',parseInt(selrowid),colName,'',{'background-color':'#ff8040'});  //#94E031				
				}
			}	 	
		
		}
		if (jsonArr=="[")
			jsonArr=="";
		else
			jsonArr = jsonArr.slice(0,-1)+ "]";

		if(flg==true){
			return jsonArr+"&errText="+errMsg; 
		}
		else{
			return jsonArr+"&errText="; 
		}
	}
	
	function allow139(id){
		//alert('allow10'+id);
		var val=jQuery("#"+id).val();
		if(val=="1" || val=="3" || val=="9"){
			jQuery("#"+id).val(val);
		}
		else{
			//alert('val'+val);
			jQuery("#"+id).val('');
		}	
	} 
	
	function convertGridToJSONArr(jqGridId) {
		//alert('convertGridToJSONArr');
		var allRows = jQuery("#" + jqGridId).jqGrid('getRowData');
		var jsonArrO = '[';
		var errMsg = "";		
		var flg=true;			
		for ( var i = 0; i < allRows.length; i++) {
			var row = allRows[i];
			var isvalid = true;			
			var jsonRow = "";			
			var colCnt = 0;
			var err = "";
			for ( var colName in row) {
				var x = row[colName].indexOf("id=") + 4;
				var y = row[colName].substring(x);
				var z = y.indexOf('"');
				var gradData = jQuery('#' + y.substring(0, z)).val();
				if ((gradData != '' && gradData != ' ' && gradData != undefined)
						&& (gradData != '' || gradData != ' ' || gradData != undefined)) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonRow += '"' + colName + '":"' + row[colName] + '",';
					} else {
						//jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#FFFFFFF'}); //#fffffff
						var columnvalue = '"' + colName + '":"' + gradData
								+ '",';
						jsonRow += columnvalue;
					}
				} else {
					//colName
					if (row[colName].substring(0, 6) != '<input') {
						jsonRow += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var colNameVal=colName.replace('txtCasm','');
						if (colName != "txtCasmKeyid" && colName != "txtCasmFlid" && colName != "txtCasmEquipmentid"
							&& colName != "EquipmentCode" && colName != "Equipment" && colName != "txtCasmTotalratings") {
							colCnt += 1;
							if (isvalid == false) {
								err = err + ",";								
							}
							err = err + "Enter " + colNameVal;
							//jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#94E031'});  						
							isvalid = false;
						}
					}
				}
			}
			//alert("isvalid::isvalid"+isvalid);
			if (isvalid == false && colCnt < 9) {				
				err = err + " in Row " + parseInt(i+1) + "  " ;
				flg=false;
				for ( var colName in row) {
					//jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#ff8040'});  
					jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#ff8040'});//#94E031
					
				}
								
			}
			/*else if(isvalid == true && colCnt < 9){
				flg=true;
				
				}**/


				
			 else if (isvalid == true) {
				 //alert("isvalid::isvalid"+isvalid);
				 for ( var colName in row) {
						jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'WHITE'});  //#94E031
						
					}
					 
				jsonArrO += '{';
				jsonArrO += jsonRow;
				jsonArrO = jsonArrO.slice(0, -1) + "},";
			}
			else if(colCnt==9){err="";}
			errMsg=errMsg+err;
		}
		if (flg == false) {
			setTimeout(function() {
				showCommonErrorMsg(errMsg);
			}, 200);
			div_err();
			return false;				
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']' ? jsonArrO : "");
		//alert("jsonArrO" + jsonArrO);
		return jsonArrO;
		
	}


	/*	function grdCriticallityData(){
		var row=jQuery("#grdCriticallity").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
		
		for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		   var KEYID = jQuery("#grdCriticallity").jqGrid('getCell',rowid,"CASM_KEYID");
		 	
		 	//var KEYID=jQuery(this).attr('CASM_KEYID');
		 	//alert("KEYID"+KEYID);

	//		 	eqpcdeval="txtCode_title_"+rowid;	
	//		equipmentcodeval=jQuery('#'+eqpcdeval).val();  //grdCriticallity_CASM_KEYID  txtCode_title_1
		 	
		 	//eqpcdeval="txtCode_title_"+rowid;	
			//equipmentcodeval=jQuery('#'+eqpcdeval).val();   grdCriticallity_EquipmentCode

			//eqpval="txtEquipment_title1_"+rowid;	
			//equipmentval=jQuery('#'+eqpval).val();
			
			
			Fidval="grdCriticallity_Flid";	
			Flnidval=jQuery('#'+Fidval).val();  
			
			machidval="grdCriticallity_machineId";	
			machineidval=jQuery('#'+machidval).val();  
			
		 	critval="txtWeightage_title2_"+rowid;	
			criteriaval=jQuery('#'+critval).val();         //txtWeightage_title2_1     txtKkp_1_7
			
			prdval="txtKkp_"+rowid+"_"+7;	 //txtKkp_1_4
			productval=jQuery('#'+prdval).val();
			
			qtyval="txtKkp_"+rowid+"_"+8;	 //txtKkp_1_5
			qualityval=jQuery('#'+qtyval).val();
			
			cstval="txtKkp_"+rowid+"_"+9;	//txtKkp_1_6
			costval=jQuery('#'+cstval).val();

			dvryval="txtKkp_"+rowid+"_"+10;	//txtKkp_1_7
			deliveryval=jQuery('#'+dvryval).val();

			sftyval="txtKkp_"+rowid+"_"+11;	//txtKkp_1_8
			safetyval=jQuery('#'+sftyval).val();

			oprtval="txtKkp_"+rowid+"_"+12;	//txtKkp_1_9
			operatabilityval=jQuery('#'+oprtval).val();

			maintval="txtKkp_"+rowid+"_"+13;	//txtKkp_1_10
			maintanabilityval=jQuery('#'+maintval).val();

			relbyval="txtKkp_"+rowid+"_"+14;	//txtKkp_1_11
			reliablityval=jQuery('#'+relbyval).val();

			totlrtgsval="txtTotalscore_"+rowid;	//txtTotalscore_1  txtTotalscore_1   txtTotalscore_1
			totalratingsval=jQuery('#'+totlrtgsval).val();

	           //alert("machineidval"+machineidval+"Flnidval"+Flnidval);
			jsonArr+= '{';
			
		 	//jsonArr += '"txtcritcalSlno":"'+rowid+'",';
			jsonArr += '"txtCasmKeyid":"'+KEYID+'",';
			jsonArr += '"txtCasmFlid":"'+Flnidval+'",'; //equipmentval
			jsonArr += '"txtCasmEquipmentid":"'+machineidval+'",';
			jsonArr += '"txtCasmCriteria":"'+criteriaval+'",';
			jsonArr += '"txtCasmProductivity":"'+productval+'",';
			jsonArr += '"txtCasmQuality":"'+qualityval+'",';
			jsonArr += '"txtCasmCost":"'+costval+'",';
			jsonArr += '"txtCasmDelivery":"'+deliveryval+'",';
			jsonArr += '"txtCasmSafety":"'+safetyval+'",';
			jsonArr += '"txtCasmOperatability":"'+operatabilityval+'",';
			jsonArr += '"txtCasmMaintainability":"'+maintanabilityval+'",';
			jsonArr += '"txtCasmReliability":"'+reliablityval+'",';
			jsonArr += '"txtCasmTotalratings":"' + totalratingsval +'"},';
			
		}
		if (jsonArr=="[")
			jsonArr=="";
		else
			jsonArr = jsonArr.slice(0,-1)+ "]";
		alert("jsonArr::jsonArr"+jsonArr);
		return jsonArr;
		
	}*/
	
	function grdCriticallity_selectRow(id){
		//alert(1);	
		//alert(record.id);
		var row=jQuery("#grdCriticallity").jqGrid('getDataIDs');
		var colModel = jQuery("#grdCriticallity").jqGrid("getGridParam","colModel" );

		// 20_PLMC0001_grdCriticallity_2	
		var rowId=id; 
		for(var j=8;j<colModel.length-2;j++){   
	 		var paramid=colModel[j].name+"_grdCriticallity_"+rowId;
	 		jQuery("#frmCriticality input[id='"+paramid+"']").css('text-align','center');
		}
		 var MachineId =jQuery("#grdCriticallity").jqGrid('getCell', id,"MACHINEID");
		 //alert(MachineId);
		    var flid = jQuery("#frmCriticality input[id='flid']").val(); 
		processAjaxCalls("criticalityassessment_remarks.cras", "flid="+flid+"&equm="+ MachineId, 'setremarks_successCallBack','setremarks_errorCallBack');	
	}
	function setremarks_successCallBack(result)
	{  // alert(Object.keys(result.successData.remarks));
		//alert(result.successData.remarks);
		setFieldValue('txtCasmRemarks' ,result.successData.remarks);
	}
	
	function grdCriticallity_onChange(record){  
		//alert("grdCriticallity_onChange");
		var rowId=record.rowId; 
		var txtId=record.txtId; 
		var controlId=txtId+"_grdCriticallity_"+rowId;
		//alert("rowId:"+rowId+",txtId:"+txtId);
		allow139(controlId);
		calSummary(rowId);
	}
	function grdCriticallity_onBlur(record){ 
		var rowId=record.rowId;
		calSummary(rowId);
		
	}  

	function calSummary(rowId){
		//alert("calSummary");
		var res=0;
		var colModel = jQuery("#grdCriticallity").jqGrid("getGridParam","colModel" );
	 	for(var j=8;j<colModel.length-2;j++){   
	 		var mul=0;
	 		var criVal=(colModel[j].name).split("_");
	 		var paramid=colModel[j].name+"_grdCriticallity_"+rowId;
	 		//alert(paramid);
	 		var parameterval=jQuery("#frmCriticality input[id='"+paramid+"']").val();
	 		parameterval=parameterval|0;
	 		//if(parameterval.trim().length>0 || parameterval)
		 		//parameterval=0;
		 	if(criVal[0]>=1){			
				 mul= parseInt(parameterval)*parseInt(criVal[0]);
			}
		 	res+=parseInt(mul);			
	 	}
	 	//alert("res"+res);
	 	jQuery("#grdCriticallity").jqGrid('setCell', rowId, 'TOTALRATING',res);
	    var MachineId =jQuery("#grdCriticallity").jqGrid('getCell', rowId,"MACHINEID");
	    var flid = jQuery("#frmCriticality input[id='flid']").val();
	    var tradeid = getFieldValue('cmbCasmTradeid'); 
	    //alert(3);
	 	processAjaxCalls("criticalityassessment_critria.cras", "rowId=" +rowId+ "&flid="+flid+"&equm="+ MachineId +"&total="+res+"&tradeid="+tradeid, 'setcritiria_successCallBack','setcritiria_errorCallBack',null,null,true);
	}    
	function setcritiria_successCallBack(result)
	{
		//alert(result.successData.critalstatus);
	   jQuery("#grdCriticallity").jqGrid('setCell', result.successData.rowId,"CRITERIA" , result.successData.critalstatus );
	}
	function frmCriticality_beforeDelete(){
		var DoneBy = jQuery("#cmbCasmDoneby").combobox('getValue');
		if(DoneBy!=null && DoneBy!="" && DoneBy!=" "){	
		var flid = jQuery("#frmCriticality input[id='flid']").val();
		if(flid!=null && flid !=" " && flid !="" && flid !="undefined"){
			var r = confirm("Are You Sure To Delete?");
			if(r){	
				processAjaxCalls("criticalityassessmentMst_delete.cras", "DeleteCriteriaFlidList="+ flid, 'remove_SucessCallbackMst','remove_errorCallBack');	
			    return true;}
			else{			
				 return false; }
		}
	}
	}
	function remove_SucessCallbackMst(result){	
		alert(result.successData.mesg);
		navigateToNextForm("criticalityassessment_input.cras?&createmode="+mode,"Criticality Assessment");	
	}
</script>
<form name="frmCriticality" id="frmCriticality">

	<div id="wrapperRpt" style="margin-left: 35px;">
		<table style="width:100%;">
			<tr>
				<td colspan="3">
					<div id="frmCriticalassessmentFuntKeyIds">
						<div style="float: left; padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=""></input>
							 <input type="hidden" id="section" name="section" value=""></input>
							 <input type="hidden" id="cell" name="cell" value=""></input>
							  <input type="hidden" id="machine" name="machine" value=""></input>
							 <input type="hidden" id="flid" name="cmbCasmFlid" value="${requestScope.flid}"></input>
						</div>
						<div id="CASMfunLocation" style="width: 750px;"></div>
					</div></td>
				<td style="padding-left: 74px;">
					<div style="width: 269%;">
					<input type="button" class="easyui-button" id="btnView"name="btnView" value="View" style="height: 22px; width: 50px; margin-top: 20px;" />
						<span style="padding-left:4px;">
						<input type="button" class="easyui-button" id="btnViewCriteria" name="btnViewCriteria" value="View Criteria" style="height: 22px; width:90px; margin-top: 20px;" />
						</span>
						<span style="padding-left:1px;">
<!--						<input type="button" class="easyui-button" id="btnCriteria" name="btnCriteria" value="Criteria Definition" style="height: 22px; width: 112px; margin-top: 20px;" />-->
						</span>
					</div>
				</td>
					
			</tr>
			</table>
	<table>
	<tr>  
	<td>
				
				<div>
				<label id="lblTrade" >Trade </label></div>
				<div>
						<input id="cmbCasmTradeid" name="cmbCasmTradeid"   class="easyui-combobox"  style="width:155px;" value="${requestScope.criaTradeid}"  /> 
				</div>
				
			</td>
			<td style="padding-left: 15px;" >
			     <div><label class="mandatory-lbl">Date</label>
			     <div><input  id="dteCasmDate" name="dteCasmDate" class="easyui-datebox" style="width:100px;" value="${requestScope.Date}"  /></div>
			     </div>
			</td>
			<td style="padding-left: 15px;">
			     <div >
			     <label class="mandatory-lbl">Done By</label>
			     <div><input  id="cmbCasmDoneby" name="cmbCasmDoneby" style=" width : 150px;" value="${requestScope.DoneBy}"  /> </div>
			     </div>
			</td>
			<td style="padding-left: 15px;">
			    <div>
			     <label>Remarks</label>
			     <div >
			     <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtCasmRemarks" name="txtCasmRemarks"  rows="2"  class="easyui-text" Style="width:355px;height:25px;font-size: 12" ></textarea>
			     </div>
			    </div>
			</td>
			
			 <td style="padding-left: 25px;">
			   <input type="button" class="easyui-button" id="btndlgDeleteRp" value="Delete" style="height: 22px; width: 50px; margin-top: 20px;" >
			</td>
   </tr>
   	</table>
		
		 <label  style="font:bolder ;color: blue; ">Critical value should be 1,3 or 9</label>	
			
		<table id='grdCriticallity'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>

	</div>
	
	<input type="hidden" id="mode" name="mode" /> <input type="hidden"
		class="easyui-button" id="hdnBtnName" name="hdnBtnName"
		value="View Report" />
		<!-- <input type="hidden" id="tstflid" name="cmbCasmFlid" value="${requestScope.flid}"></input>  -->
		<input type="hidden" id="hdncurrentdate" name="hdncurrentdate"  value="" />
		<input type="hidden" id="hdncrytype" name="hdncrytype"  value="${requestScope.crytype}" />
		<input type="hidden" id="hdnremarks" name="hdnremarks"  value="${requestScope.Remarks}" />
		<input type="hidden" id="hdnKeysArr" name="hdnKeysArr"  value="" />
		
</form>





