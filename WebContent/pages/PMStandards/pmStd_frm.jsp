<style>
.legnd{
/*-moz-box-shadow: 0 0 5px rgba(238,233,233, 1);*/
box-shadow: 0 0 5px #A5A5A5;
-webkit-box-shadow: 0 0 5px rgba(0,0,0, 1); 
-moz-box-shadow: 0 0 5px rgba(0,0,0, 1);
margin-left:20px;
background-color:#EACCCC;/*#F7BC74*/
font-size:10;
padding:3;
visibility: hidden;  
border:ridge 1px #FFEFEF;
} 
</style>
<!--<script type="text/javascript" src="js/prvntv_mntnc_js.js"></script>-->
<script type="text/javascript">
	jQuery(document).ready(function(){
		setLoadFormCallBackFrmId('frmPmStandard');
		initialiseForm("frmPmStandard");
		jQuery('#submitForm').val('frmPmStandard'); // set the id of form to submit
		
		jQuery(".main-cntborder ").css("height","70%");	
		 
		var url = jQuery('#hiddenUrl').val();
		jQuery('#btnChkDiv').css('display','block');
		//alert(url);
		if(url == 'Mouldpreventive_input.prv'){
			jQuery('#MldDiv').css('display','block');
			jQuery('#machDiv').css('display','none');
		}
		else{
			jQuery('#MldDiv').css('display','none');
			jQuery('#machDiv').css('display','block');
		}
		
		enableFields('btnView');		
		//for getting values in combobox call the action
		
		 
		//fillComboBox("frmPmStandardform","cmbMouldid","mould.commonFilter");
		fillComboBox("frmPmStandard","cmbActivitytype","Pmsd_Jobtype.prv");
		/* for functionalLocation*/
		var factId = jQuery("#frmPmStandard input[id='factory']").val();
		var sectionId = jQuery("#frmPmStandard input[id='section']").val();
		var cellId = jQuery("#frmPmStandard input[id='cell']").val();
		var machId = jQuery("#frmPmStandard input[id='machine']").val();

		fillComboBox("frmPmStandard","cmbPMMachineid","machineCombo.commonFilter");
		fillComboBox("frmPmStandard","cmbPmstdCostCenter","costCenter.commonFilter");
		
		if( machId == null || machId == undefined || machId.trim().length == 0)
			machId = jQuery("#cmbPMMachineid").combobox("getValue");
			
		var flid = jQuery("#frmPmStandard input[id='flid']").val();
		if( jQuery('#hdnTenStpFlid').val()!= "")
			{
				flid = jQuery('#hdnTenStpFlid').val();
				setFieldValue('cmbPMMachineid',' ');
				setFieldValue('cmbPmstdCostCenter',' ');
				
			}
		else 
			flid = jQuery("#frmPmStandard input[id='flid']").val();
		
		if( machId != null && machId != undefined && machId.trim().length > 0)
			var xflid = "";
		var dataStr;
		if(jQuery('#hdnTenStpFlid').val()!= "")
		 	dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId=&flid="+flid;
		else
			dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		
  
		
		loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard",dataStr);
	  //loadFunctionalLocation("bdmsfunLocation","functionalLoc.brdn","bdmsfunLocationValues","frmBDMaster",dataStr);
	/*---------*/
		var url = jQuery('#hiddenUrl').val();
		//alert(url);
	if(jQuery('#chkassmWise').is(':checked') == true){
		//viewGrid(url,"&chkd=chkassmWise");	
		jQuery('#imgPendingWrkordr').hide();
		 jQuery('#btnNewstd').hide();
		// jQuery('#lblHeader').html("PM Standard");
		 }
	//
	
	  setLoadFormCallBackFrmId('frmPmStandard');
	//  var url = getSubmitFormUrl();	
	  //navigateToNextForm("pmAssembly_input.prv?q=2&loadContentDivId=Loadassemblygridfrm&preLoadContentDivId=preloadDIVid1&formId=frmPmStandard");
	//  setSubmitFormUrl(url);
	//LoadForm("Loadassemblygridfrm","preloadDIVid1","pmAssembly_input.prv","dispErr","","pmAssemblygrid_errorCallBack");
    /* jQuery('#btnBase').click(function(){
    	 
    	 LoadPopUp("loadBase", "empEqp_input.base?q=2", true,"97%","90%","1%","0%", "cbmResult_successCallBack","Base Form");
     });*/
    
     
	});
	
	function frmPmStandard_afterLoadCallBack(){
		/*var navig = formNavigations.pop();
		alert(navig.URL);
		if( navig.URL.indexOf("skipForm") < 0 )
			navig.URL += '&skipForm=true'; 
		//alert('ss1');
		formNavigations.push(navig);
		*/
		var hdnMachine = jQuery('#hdnMachine').val();
		 var url = jQuery('#hiddenUrl').val();
		 var grdUrl ='';
	/*		  var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
			var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
			var cell = jQuery('input:[name=cmbPmsdCellid]').val();
			var mach = jQuery('input:[name=cmbMachineid]').val();
*/
			var fact = jQuery("#frmPmStandard input[id='factory']").val();
			var sect = jQuery("#frmPmStandard input[id='section']").val();
			var cell = jQuery("#frmPmStandard input[id='cell']").val();
			var mach = jQuery("#frmPmStandard input[id='machine']").val();
			var flid = jQuery("#frmPmStandard input[id='flid']").val();
			if( mach == null || mach == undefined || mach.trim().length == 0)
				mach = jQuery("#cmbPMMachineid").combobox("getValue");

			
			
			var activitytype = jQuery('#hdnActType').val();
			if( activitytype == null || activitytype == undefined || activitytype.trim().length == 0)
				activitytype = jQuery("#cmbActivitytype").combobox("getValue"); 
			
			var filterString = '&cmbFactid='+fact;
				filterString += '&cmbSectid='+sect; 
				filterString += '&cmbCellid='+cell; 
				filterString += '&cmbMchid='+mach;
				filterString +="&chkd=chkactWise";
		 var costCenter = getFieldValue('cmbPmstdCostCenter','frmPmStandard');
		 var persitData = {"machineId":mach,"cellId":cell,"flid":flid,"activityType":activitytype,"costcenter":costCenter};
						
		 if(hdnMachine != ' ' && hdnMachine != '' && hdnMachine != null){
			// if(url != 'preventive_input.prv')
			//	 grdUrl = "MouldpmActivity_input.prv?q=2&filterString=";
			 //else
				 grdUrl = "pmActivity_input.prv?q=2&filterString=";
			 
				 loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+hdnMachine);
		
		//fillMachineHierarchy("machineHierarchy.commonFilter",hdnMachine,"","","","", "","cmbPmstdCostCenter");		
			/*---------*/
			  
			
			 navigateToNextForm(grdUrl+filterString+"&loadContentDivId=Loadactivitygridfrm&preLoadContentDivId=preloadDIVid3&formId=frmPmStandard1&isHidePrevForm=false","","",persitData,"");
			
			 jQuery('#imgPendingWrkordr').show();
			 jQuery('#btnNewstd').show();
			 //jQuery('#headin').css('display','block');
			 
			 jQuery('#lblHeader').html("Activity Type ");
			 jQuery('input:checkbox[name=chkactWise]').attr('checked',true);
			 jQuery('input:checkbox[name=chkassmWise]').attr('checked',false);
		}
		 else{
			//  if(url != 'preventive_input.prv')
			//	 grdUrl = "MouldpmAssembly_input.prv?q=2";
			 //else
				 grdUrl = "pmAssembly_input.prv?skipForm=true";

			 //LoadForm("Loadassemblygridfrm","preloadDIVid1",grdUrl,"dispErr","","navigateToNext_ErrorCalBack");
			   navigateToNextForm(grdUrl+filterString+"&loadContentDivId=Loadassemblygridfrm&preLoadContentDivId=preloadDIVid1&formId=frmPmStandard&isHidePrevForm=false",null,null,persitData,"frmPmStandrdNavigNext_SuccessCalBack");
			 }
		 
	}
	function frmPmStandrdNavigNext_SuccessCalBack(){
		//formNavigations.pop();	
	}
	function frmPmStandard_beforeCloseCurrentForm(){
		
	/*var hdnMachine = jQuery('#hdnMachine').val();
		if(hdnMachine != ' ' && hdnMachine != '' && hdnMachine != null)
			popFormNavigation();
		else{
			if( ! jQuery(".layout-split-west").is(":visible"))
				jQuery('#mainlayout').layout('expand','west');
			setFormMainHeader("Home");
			popFormNavigation();
		 }
		return false;
		*/
	}
	function frmPmStandard1_beforeCloseCurrentForm(){
		
	/*	var hdnMachine = jQuery('#hdnMachine').val();
			if(hdnMachine != ' ' && hdnMachine != '' && hdnMachine != null)
				popFormNavigation();
			else{
				if( ! jQuery(".layout-split-west").is(":visible"))
					jQuery('#mainlayout').layout('expand','west');
				setFormMainHeader("Home");
				popFormNavigation();
			}
	*/	
	}
	function  frmPmStandardcmbPMMachineid_onLoadSuccess(){
		
		//fillComboBox("frmPmStandard","cmbActivitytype","Pmsd_Jobtype.prv");
	}
	
	function  frmPmStandardcmbActivitytype_onSelect(record)
	{
		jQuery('#hdnActType').val(record.id);
	}
	
	function  frmPmStandardcmbPmstdCostCenter_onLoadSuccess(){
		jQuery('#dispFunctionalLoc').css('width','85%');
	}
	/*function dblclick(id)
	{alert('dblclick');
		var rowData = jQuery("#grid2").jqGrid('getRowData',id);																								
		var pmstdKeyid = rowData.keyid;
		//alert(pmstdKeyid );   
		var hdnMode =jQuery('#hdnfield').val();
		var activitytype = getFieldValue('cmbActivitytype');//jQuery('#hdnActType').val();
		var machineId=jQuery('#hiddenMachField').val();
		var filterData = '&pmstdKeyid='+pmstdKeyid;
		filterData += '&pmsdMachineID='+machineId;
		if(activitytype != '' && activitytype != ' ' && activitytype != null ){
		navigateToNextForm('prvnt_mntncform_modify.prv?'+filterData,'PMStandard');
		}
		else{
		alert('Select Activity Type');
		}
	}*/
	jQuery('#btnNewStandard').click(function(){
		openNewStandard("", "","", "activityGrid");
			
	});
	// To Copy  the Standards from SUGU
	
	jQuery('#btnCopyStandard').click(function(){
		
		//alert(123);
		var gridId="activityGrid";
		  var UpdateList=null;
		
		var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	
		//var iCol="";
		var selArray =  jQuery("#activityGrid").jqGrid('getGridParam', 'selarrrow');
			var selrowid="";
			 var jsonArr='';
		  if(selArray !=null && selArray!=" " && selArray!=""){
						
			for(var i=0;i<selArray.length;i++)
			{
		     
				selrowid=selArray[i];
		       //jsonArr+='[';
			   var Keyid =jQuery("#activityGrid").jqGrid('getCell', selrowid,"keyid");
			   
			   var Criteriasplit= Keyid.split(',');
			 	for(var k=0;k<Criteriasplit.length;k++){
				 	var Keyidval=Criteriasplit[k]; 
				 	var keyvalSplit=Keyidval.split(";");
				 	var keyvalu=keyvalSplit[0];
				 	//jsonArr += '"'+keyvalu + '",';	
				 	//alert("keyvalu"+keyvalu);
				 	jsonArr += '"'+keyvalu + '",';	
		 	    }
			 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
				jsonArr += ',';
			  //  alert("jsonArr "+jsonArr);
			}	
			jsonArr = jsonArr.substring(0,jsonArr.length-1);
		 UpdateList=jsonArr;
		   
		  }
		
		var iCol = jQuery('#getiCol').val();
		var colRowData=jQuery('#assmGrid').jqGrid('getGridParam', 'colNames');
		var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
		//alert("colm    "+colm +"  colRowData"+colRowData);
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	
		var tradeId = selId;
			
		//var tradeId = "TDE00800002";
		//var tradeId = selId;
		//alert("tradeId"+tradeId);
		 var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
		 var sect = jQuery('input:[name=cmbPmsdSectionid]').val();// jQuery("#frmPmStandard input[id='cmbPmsdSectionid']").combobox("getValue");// jQuery('input:[name=cmbPmsdSectionid]').val();
		//alert("sect"+sect);
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();//jQuery("#frmPmStandard input[id='cmbPmsdCellid']").combobox("getValue");//jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('input:[name=cmbPmsdMachineid]').val();//jQuery("#frmPmStandard input[id='cmbPmsdMachineid']").combobox("getValue");//jQuery('input:[name=cmbPmsdMachineid]').val();
		var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
		//alert("activitytype"+activitytype);
			var costcenterId = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
		var flid = jQuery("#frmPmStandard input[id='flid']").val();
		var elementid=jQuery("#cmbPmsdElementid").val();
		var jsonstr = '';
		var filterString='';
			filterString = '&cmbFactid='+fact;
			filterString += '&cmbSectid='+sect; 
			filterString += '&cmbCellid='+cell; 
			filterString += '&cmbMchid='+mach;
			filterString += '&cmbTradeid='+tradeId;
			filterString += "&cmbjobtype="+activitytype;
			filterString += "&cmbPmsdElementid="+elementid;
			//filterString += '&TN='+colRowData[iCol] +'name';
			filterString += '&cmbCostcenter='+costcenterId ;
			filterString += '&flid='+flid ;
			//alert(456);
			 jsonstr = '{"cmbFactid":"'+fact+'" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbjobtype":"'+activitytype+'"}';
			var persistdata =  jQuery.parseJSON(jsonstr);
		

		//alert("filterStr"+filterString);
		var grdUrl ="";
		
			 grdUrl = "pmActivitycopy_input.prv?q=2";
			 
			 LoadPopUp("loadequipment",grdUrl+"&filterString="+filterString+"&actlist="+UpdateList, true,"70%","70%","10%","10%", "","Equipment popup"," "," " );
					 
		//navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
	
	});
	//To Copy the Standards from Suggu
	
	// DELETE THE STANDARDS FROM LIST BY KIRAN 
	

	
	jQuery('#btnDelStandard').click(function(){
			 
		var gridId="activityGrid";
		  var UpdateList=null;		
		var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	
	
		var selArray =  jQuery("#activityGrid").jqGrid('getGridParam', 'selarrrow');
			var selrowid="";
			 var jsonArr='';
		  if(selArray !=null && selArray!=" " && selArray!=""){
						
			for(var i=0;i<selArray.length;i++)
			{
				selrowid=selArray[i];
		    
			   var Keyid =jQuery("#activityGrid").jqGrid('getCell', selrowid,"keyid");
			   
			   var Criteriasplit= Keyid.split(',');
			 	for(var k=0;k<Criteriasplit.length;k++){
				 	var Keyidval=Criteriasplit[k]; 
				 	var keyvalSplit=Keyidval.split(";");
				 	var keyvalu=keyvalSplit[0];
				 	jsonArr += '"'+keyvalu + '",';	
		 	    }
			 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
				jsonArr += ',';
			  
			}	
			jsonArr = jsonArr.substring(0,jsonArr.length-1);
		 UpdateList=jsonArr;		   
		  }		
		var iCol = jQuery('#getiCol').val();
		var colRowData=jQuery('#assmGrid').jqGrid('getGridParam', 'colNames');
		var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
		
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	
		var tradeId = selId;
			
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('input:[name=cmbPmsdMachineid]').val();
		var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
		var flid = jQuery("#frmPmStandard input[id='flid']").val();
		var jsonstr = '';
		var filterString='';
			filterString += '&cmbCellid='+cell; 
			filterString += '&cmbMchid='+mach;
			filterString += "&cmbjobtype="+activitytype;
			filterString += '&flid='+flid ;			
			 jsonstr = '{"cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbjobtype":"'+activitytype+'"}';
			var persistdata =  jQuery.parseJSON(jsonstr);
			saveForm("frmPmStandard","pmActivity_Delete.prv?persistdata="+UpdateList+'&cellId='+cell+'&machineId='+mach);
			
	});
	function frmPmStandard_successsCallback(result)
	{
		jQuery('#assmGrid').trigger('reloadGrid');
		jQuery('#activityGrid').trigger('reloadGrid');
    }
	// END OF DETELE THE STANDARDS FROM LIST BY KIRAN 
	
/* 	function openNewStandard(rowid,iCol,cellcontent,gridId){


	    var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	
			//assmGrid
			//return false;
		//var rowData = jQuery("#"+gridId).jqGrid('getRowData',"0");
		/*var colModel = jQuery("#"+gridId).jqGrid('colModel');
		var tradeId = colModel[iCol].name;
		alert(tradeId);
		*/		
		//var tradeId = rowData[selId];
		/* var tradeId = selId;// jQuery('#hdnTradeId').val();
		var machineId=jQuery('#hiddenMachField').val();
		if( machineId == null || machineId == undefined || machineId.trim().length == 0 )
			machineId = jQuery('#cmbPMMachineid').combobox("getValue");
		 
		var cmbCostcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
			
		var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");//getFieldValue('cmbActivitytype');//jQuery('#hdnActType').val();
		if(activitytype.trim().length<=0)
			activitytype = jQuery('#hdnActType').val();
		var filterStr = "";
		filterStr ='&pmsdMachineID='+machineId;
		filterStr +='&activitytype='+activitytype;
		filterStr +='&cmbjobtype='+activitytype;
		if( cmbCostcenter != undefined )
			filterStr +='&cmbCostcenter='+cmbCostcenter;
		
		var rowDataassm = jQuery("#weekWise_Grid").jqGrid('getRowData',rowid);	
		//if(rowDataassm.length>0)																							
			var assmId = rowDataassm.Keyid;
		//alert(assmId);
		if(assmId!= '' && assmId!= ' ' && assmId!= null ){
			//alert(assmId);
			filterStr += '&cmbAssmbid='+assmId;
		}
		
		if(tradeId != '' && tradeId!= ' ' && tradeId!= null )
			filterStr +='&tradeId='+tradeId;
		else
			filterStr +='&tradeId=" "';
		if(activitytype != '' && activitytype != ' ' && activitytype != null ){
			//navigateToNextForm('prvnt_mntncform_input.prv?q=2'+filterStr,'PM Standard Form');	
		     jQuery('#imgPendingWrkordr').hide();
			 jQuery('#btnNewstd').hide();
			 jQuery('#headin').css('display','none');
			 jQuery('#lblHeader').html(" ");
			 jQuery("#top_div").css('display',"none");	
			 var url = jQuery('#hiddenUrl').val();
			 var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'),url.indexOf('?q=2')); 
			 if(gUrl == "MouldpmActivity_input.prv")
				filterStr+="&relatedTo=Mould";
				//alert(filterStr);chkd
				 var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
				var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
				var cell = jQuery('input:[name=cmbPmsdCellid]').val();
				var flid = jQuery('input:[name=cmbPmsdFlid]').val();
				var elementid = jQuery('input:[name=cmbPmsdElementid]').val();
				var costcenter=jQuery('#cmbPmstdCostCenter').combobox('getValue');
				if( machineId == null || machineId == undefined || machineId.trim().length == 0)
					machineId = jQuery('#cmbPMMachineid').combobox("getValue");
				
				filterStr +="&pmsdelementid="+elementid+"&pmsdflid="+flid+"&cmbCostcenter="+costcenter;
				var jsonstr = '{"cmbFactid":"'+ fact + '" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'","cmbMchid":"'+machineId+'","cmbjobtype":"'+activitytype+'","cmbAssmbid":"'+assmId+'","elementid":"'+elementid+'","flid":"'+flid+'"}';

				
							
				var perstData = jQuery.parseJSON(jsonstr);
		   //Query("#Loadassemblygridfrm").html('');		
	
		   navigateToNextForm("prvnt_mntncform_input.prv?filterStr="+filterStr+"&loadContentDivId=LoadPmStdfrm&preLoadContentDivId=preloadDIVid4&isHidePrevForm=true","Maintenance Standards",null,perstData);
		 
		}
		else
			alert('Select Activity Type ');
	} */ 
	//mano 
	
	/* function openNewStandard(rowid,iCol,cellcontent,gridId){

	    var colm = jQuery("#"+gridId).jqGrid ('getGridParam', 'colModel');
	    var selId ="";
		if(iCol != '' && iCol != ' ' && iCol != null)
			selId = colm[iCol].name;	

		var tradeId = selId;// jQuery('#hdnTradeId').val();
		var machineId=jQuery('#hiddenMachField').val();
		if( machineId == null || machineId == undefined || machineId.trim().length == 0 )
			machineId = jQuery('#cmbPMMachineid').combobox("getValue");
		 
		var cmbCostcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
			
		var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
		if(activitytype.trim().length<=0)
			activitytype = jQuery('#hdnActType').val();
		var filterStr = "";
		filterStr ='&pmsdMachineID='+machineId;
		filterStr +='&activitytype='+activitytype;
		filterStr +='&cmbjobtype='+activitytype;
		if( cmbCostcenter != undefined )
			filterStr +='&cmbCostcenter='+cmbCostcenter;
		
		var rowDataassm = jQuery("#weekWise_Grid").jqGrid('getRowData',rowid);	
			var assmId = rowDataassm.Keyid;
		if(assmId!= '' && assmId!= ' ' && assmId!= null ){
			filterStr += '&cmbAssmbid='+assmId;
		}
		
		if(tradeId != '' && tradeId!= ' ' && tradeId!= null )
			filterStr +='&tradeId='+tradeId;
		else
			filterStr +='&tradeId=" "';
		if(activitytype != '' && activitytype != ' ' && activitytype != null ){
		     jQuery('#imgPendingWrkordr').hide();
			 jQuery('#btnNewstd').hide();
			 jQuery('#headin').css('display','none');
			 jQuery('#lblHeader').html(" ");
			 jQuery("#top_div").css('display',"none");	
			 var url = jQuery('#hiddenUrl').val();
			 var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'),url.indexOf('?q=2')); 
			 if(gUrl == "MouldpmActivity_input.prv")
				filterStr+="&relatedTo=Mould";

				// FIXED: removed stray colon before [name=...] — this was throwing
				// "Uncaught Error: Syntax error, unrecognized expression" and
				// stopping execution before navigateToNextForm ever ran.
				var fact = jQuery('input[name=cmbPmsdFactoryid]').val();
				var sect = jQuery('input[name=cmbPmsdSectionid]').val();
				var cell = jQuery('input[name=cmbPmsdCellid]').val();
				var flid = jQuery('input[name=cmbPmsdFlid]').val();
				var elementid = jQuery('input[name=cmbPmsdElementid]').val();
				var costcenter=jQuery('#cmbPmstdCostCenter').combobox('getValue');
				if( machineId == null || machineId == undefined || machineId.trim().length == 0)
					machineId = jQuery('#cmbPMMachineid').combobox("getValue");
				
				filterStr +="&pmsdelementid="+elementid+"&pmsdflid="+flid+"&cmbCostcenter="+costcenter;
				var jsonstr = '{"cmbFactid":"'+ fact + '" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'","cmbMchid":"'+machineId+'","cmbjobtype":"'+activitytype+'","cmbAssmbid":"'+assmId+'","elementid":"'+elementid+'","flid":"'+flid+'"}';

				var perstData = jQuery.parseJSON(jsonstr);

				//navigateToNextForm("prvnt_mntncform_input.prv?filterStr="+filterStr+"&loadContentDivId=LoadPmStdfrm&preLoadContentDivId=preloadDIVid4&isHidePrevForm=true","Maintenance Standards",null,perstData);
				LoadPopUp(
					    "prvnMntncStdDiv",                              
					    "prvnt_mntncform_multiple_input.prv?filterStr=" + filterStr,
					    true,                                           
					    "90%",                                          
					    "82%",                                          
					    "1%",                                           
					    "1%",                                           
					    "",
					    "Maintenance Standards",                        
					    "",
					    true                                            
					);
			 
		} 
		else
			alert('Select Activity Type ');
	}*/
	/* function openNewStandard(rowid, iCol, cellcontent, gridId) {

	    var colm = jQuery("#" + gridId).jqGrid('getGridParam', 'colModel');
	    var selId = "";
	    if (iCol != '' && iCol != ' ' && iCol != null)
	        selId = colm[iCol].name;

	    var tradeId = (selId || "").trim();
	    var machineId = jQuery('#hiddenMachField').val();
	    if (machineId == null || machineId == undefined || machineId.trim().length == 0)
	        machineId = jQuery('#cmbPMMachineid').combobox("getValue");
	    machineId = (machineId || "").trim();

	    var cmbCostcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
	    cmbCostcenter = (cmbCostcenter || "").trim();

	    var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
	    if (activitytype.trim().length <= 0)
	        activitytype = jQuery('#hdnActType').val();
	    activitytype = (activitytype || "").trim();

	    var filterStr = "";
	    filterStr = '&pmsdMachineID=' + encodeURIComponent(machineId);
	    filterStr += '&activitytype=' + encodeURIComponent(activitytype);
	    filterStr += '&cmbjobtype=' + encodeURIComponent(activitytype);
	    if (cmbCostcenter != undefined)
	        filterStr += '&cmbCostcenter=' + encodeURIComponent(cmbCostcenter);

	    var rowDataassm = jQuery("#weekWise_Grid").jqGrid('getRowData', rowid);
	    var assmId = (rowDataassm.Keyid || "").trim();   // <-- the fix: was leaving a trailing
	                                                      //     space that broke jQuery's .load()
	    if (assmId != '' && assmId != ' ' && assmId != null) {
	        filterStr += '&cmbAssmbid=' + encodeURIComponent(assmId);
	    }

	    if (tradeId != '' && tradeId != ' ' && tradeId != null)
	        filterStr += '&tradeId=' + encodeURIComponent(tradeId);
	    else
	        filterStr += '&tradeId=';   // <-- was '&tradeId=" "' — literal quotes+space, invalid

	    if (activitytype != '' && activitytype != ' ' && activitytype != null) {
	        jQuery('#imgPendingWrkordr').hide();
	        jQuery('#btnNewstd').hide();
	        jQuery('#headin').css('display', 'none');
	        jQuery('#lblHeader').html(" ");
	        jQuery("#top_div").css('display', "none");

	        var url = jQuery('#hiddenUrl').val();
	        var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'), url.indexOf('?q=2'));
	        if (gUrl == "MouldpmActivity_input.prv")
	            filterStr += "&relatedTo=Mould";

	        var fact      = (jQuery('input[name=cmbPmsdFactoryid]').val() || "").trim();
	        var sect      = (jQuery('input[name=cmbPmsdSectionid]').val() || "").trim();
	        var cell      = (jQuery('input[name=cmbPmsdCellid]').val() || "").trim();
	        var flid      = (jQuery('input[name=cmbPmsdFlid]').val() || "").trim();
	        var elementid = (jQuery('input[name=cmbPmsdElementid]').val() || "").trim();
	        var costcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
	        costcenter = (costcenter || "").trim();
	        if (machineId == null || machineId == undefined || machineId.trim().length == 0)
	            machineId = jQuery('#cmbPMMachineid').combobox("getValue");
	        machineId = (machineId || "").trim();

	        filterStr += "&pmsdelementid=" + encodeURIComponent(elementid)
	                   + "&pmsdflid=" + encodeURIComponent(flid)
	                   + "&cmbCostcenter=" + encodeURIComponent(costcenter);

	        var jsonstr = '{"cmbFactid":"' + fact + '" ,"cmbSectid":"' + sect + '","cmbCellid":"' + cell
	                    + '","cmbMchid":"' + machineId + '","cmbjobtype":"' + activitytype
	                    + '","cmbAssmbid":"' + assmId + '","elementid":"' + elementid
	                    + '","flid":"' + flid + '"}';

	        var perstData = jQuery.parseJSON(jsonstr);

	               LoadPopUp(
	            "prvnMntncStdDiv",
	            "prvnt_mntncform_multiple_input.prv?filterStr=" + filterStr,
	            true,
	            "90%", "82%", "1%", "1%", "",
	            "Maintenance Standards", "",
	            true
	        );         
	      // navigateToNextForm("prvnt_mntncform_input.prv?filterStr="+filterStr+"&loadContentDivId=LoadPmStdfrm&preLoadContentDivId=preloadDIVid4&isHidePrevForm=true","Maintenance Standards",null,perstData);
	    } */
	    function openNewStandard(rowid, iCol, cellcontent, gridId) {
	    	alert("mheck");

	        console.log("[openNewStandard] CALLED, rowid:", rowid, "iCol:", iCol, "gridId:", gridId);

	        var colm = jQuery("#" + gridId).jqGrid('getGridParam', 'colModel');
	        var selId = "";
	        if (iCol != '' && iCol != ' ' && iCol != null)
	            selId = colm[iCol].name;

	        var tradeId = (selId || "").trim();
	        console.log("[openNewStandard] tradeId:", tradeId);

	        var machineId = jQuery('#hiddenMachField').val();
	        if (machineId == null || machineId == undefined || machineId.trim().length == 0)
	            machineId = jQuery('#cmbPMMachineid').combobox("getValue");
	        machineId = (machineId || "").trim();

	        var cmbCostcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
	        cmbCostcenter = (cmbCostcenter || "").trim();

	        var comboActType = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
	        var hdnActType   = jQuery('#hdnActType').val();
	        console.log("[openNewStandard] combo Activity Type raw:", comboActType, "| hdnActType raw:", hdnActType);

	        var activitytype = (comboActType || "").trim();
	        if (activitytype.length <= 0)
	            activitytype = (hdnActType || "").trim();

	        console.log("[openNewStandard] FINAL activitytype used:", activitytype);

	        // ... rest unchanged

	        var rowDataassm = jQuery("#weekWise_Grid").jqGrid('getRowData', rowid);
	        var assmId = (rowDataassm.Keyid || "").trim();
	        if (assmId != '' && assmId != ' ' && assmId != null) {
	            filterStr += '&cmbAssmbid=' + encodeURIComponent(assmId);
	        }

	        if (tradeId != '' && tradeId != ' ' && tradeId != null)
	            filterStr += '&tradeId=' + encodeURIComponent(tradeId);
	        else
	            filterStr += '&tradeId=';

	        if (activitytype != '' && activitytype != ' ' && activitytype != null) {
	            jQuery('#imgPendingWrkordr').hide();
	            jQuery('#btnNewstd').hide();
	            jQuery('#headin').css('display', 'none');
	            jQuery('#lblHeader').html(" ");
	            jQuery("#top_div").css('display', "none");

	            var url = jQuery('#hiddenUrl').val();
	            var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'), url.indexOf('?q=2'));
	            if (gUrl == "MouldpmActivity_input.prv")
	                filterStr += "&relatedTo=Mould";

	            var fact      = (jQuery('input[name=cmbPmsdFactoryid]').val() || "").trim();
	            var sect      = (jQuery('input[name=cmbPmsdSectionid]').val() || "").trim();
	            var cell      = (jQuery('input[name=cmbPmsdCellid]').val() || "").trim();
	            var elementid = (jQuery('input[name=cmbPmsdElementid]').val() || "").trim();
	            var costcenter = jQuery('#cmbPmstdCostCenter').combobox('getValue');
	            costcenter = (costcenter || "").trim();
	            if (machineId == null || machineId == undefined || machineId.trim().length == 0)
	                machineId = jQuery('#cmbPMMachineid').combobox("getValue");
	            machineId = (machineId || "").trim();

	            filterStr += "&pmsdelementid=" + encodeURIComponent(elementid)
	                       + "&cmbCostcenter=" + encodeURIComponent(costcenter);

	            var jsonstr = '{"cmbFactid":"' + fact + '" ,"cmbSectid":"' + sect + '","cmbCellid":"' + cell
	                        + '","cmbMchid":"' + machineId + '","cmbjobtype":"' + activitytype
	                        + '","cmbAssmbid":"' + assmId + '","elementid":"' + elementid + '"}';

	            var perstData = jQuery.parseJSON(jsonstr);

	            LoadPopUp(
	                "prvnMntncStdDiv",
	                "prvnt_mntncform_multiple_input.prv?filterStr=" + filterStr,
	                true,
	                "90%", "82%", "1%", "1%", "",
	                "Maintenance Standards", "",
	                true
	            ); 
	        }
	        else
	            alert('Select Activity Type ');
	    }
	    /* }
	    else
	        alert('Select Activity Type '); */

	//function for filemanager
	jQuery('#filemanager').click(function(){
		var vale = jQuery('#cmbPmsdFrequencyunit').find('option:selected').text();
		//alert("jjj- "+vale);
	     var val=jQuery('#cmbPmsdFrequencyunit').combobox('getValue');
	    // alert("val   -"+ val);
		
	});

	
  function  frmPmStandardcmbPMMachineid_onSelect(record)
	{
	 //alert("PmStandard"+Pmstandarad);
	 loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+record.id);
	}


	function frmPmStandardcmbPmstdCostCenter_onSelect(record)
	{

		
	 reloadCombo("frmPmStandard","cmbPMMachineid","machineCombo.commonFilter?costCentreId="+record.id) ;
	}
	
  /* function frmPmStandard_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		//reloadMachine("frmPmStandard","cmbMachineid",keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		//reloadMachine("frmPmStandard","cmbPMMachineid",keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		/* 3/2/2015 updated by Team */
		/* var dataStr ="?flid="+keyIds.flId+"&cellId="+ keyIds.cellId;//+"&sectionId="+keyIds.sectId;
		//setTimeout(function() {reloadCombo("frmPmStandard","cmbPMMachineid","machineCombo.commonFilter"+dataStr);},500);
		reloadCombo("frmPmStandard","cmbPMMachineid","machineCombo.commonFilter"+dataStr);
		//setTimeout(function() {reloadCombo("frmPmStandard","cmbPmstdCostCenter","costCenter.commonFilter?cellId="+ keyIds.cellId+"&sectId="+keyIds.sectId) ;},500);//machId="+keyIds.machId+"&cellId="+keyIds.cellId);
		reloadCombo("frmPmStandard","cmbPmstdCostCenter","costCenter.commonFilter?cellId="+ keyIds.cellId+"&sectId="+keyIds.sectId) ;
		fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","cmbPmstdCostCenter");

		setFieldValue('cmbPmsdcellid',keyIds.cellId);
		setFieldValue('cmbPMMachineid',keyIds.machId);
		jQuery('#hiddenMachField').val(keyIds.machId);
	}  */
	//mano
	function frmPmStandard_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
	    var dataStr ="?flid="+keyIds.flId+"&cellId="+ keyIds.cellId;
	    reloadCombo("frmPmStandard","cmbPMMachineid","machineCombo.commonFilter"+dataStr);
	    reloadCombo("frmPmStandard","cmbPmstdCostCenter","costCenter.commonFilter?cellId="+ keyIds.cellId+"&sectId="+keyIds.sectId);
	    fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","cmbPmstdCostCenter");

	    setFieldValue('cmbPmsdcellid',keyIds.cellId);
	    setFieldValue('cmbPMMachineid',keyIds.machId);
	    jQuery('#hiddenMachField').val(keyIds.machId);

	    // mano - sync SBU into factory field
	    var sbuVal = jQuery("#frmPmStandard input[name='hdnsbu']").val();
	    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
	        sbuVal = jQuery("#hdnsbu").val();

	    if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
	        jQuery("#frmPmStandard input[id='factory']").val(sbuVal);
	    }
	}	
	function cboxSprFormatter(id, options, rowObject)
	{
		var id = options.rowId;
	  	return '<input  type="checkbox" id="addinfo_checkbox" name="addinfo_checkbox"  onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	}
	jQuery('#btnBack').click(function(){
		 jQuery('#tradeName').css('display','none');
		 setFieldValue('cmbActivitytype', jQuery('#hdnActType').val(),'frmPmStandard');
		navigateToPrevForm();
	});
	/**Added on 23rd jun**/
	jQuery('#btnclearcntrl').click(function(){
		jQuery("#frmPmStandard input[id='factory']").val(' ');
		jQuery("#frmPmStandard input[id='section']").val(' ');
		jQuery("#frmPmStandard input[id='cell']").val(' ');
		jQuery("#frmPmStandard input[id='machine']").val(' ');
		jQuery('#hdnActType').val(' ');
		clearField('cmbActivitytype');
		clearField('cmbPmstdCostCenter');
		clearField('cmbPMMachineid');
		
		/* for functionalLocation*/
		var factId = jQuery("#frmPmStandard input[id='factory']").val();
		var sectionId = jQuery("#frmPmStandard input[id='section']").val();
		var cellId = jQuery("#frmPmStandard input[id='cell']").val();
		var machId = jQuery("#frmPmStandard input[id='machine']").val();
		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
		//alert(dataStr);
		loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard",dataStr);
		clearField('');
	});
	jQuery('#btnaddAssembly').click(function(){
		var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
		var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();
		
		var mach = jQuery('input:[name=cmbPmsdMachineid]').val();
		if( mach == null || mach == undefined || mach.trim().length <=0 ){
			mach = jQuery( 'input:[name=cmbPmsdMachineid]').val();
		}	
		var assembly = jQuery('#Loadassemblygridfrm').css("display");
		var activity = jQuery('#Loadactivitygridfrm').css("display");
		var asWeek = jQuery('#LoadassmbWeekgridfrm').css("display");
		var gridLength;
		if(assembly != 'none')
			gridLength = jQuery('#assmGrid').jqGrid('getRowData');
		/*else if(activity != 'none')
			gridLength = jQuery('#activityGrid').jqGrid('getRowData');*/
		else if(asWeek != 'none')
			gridLength = jQuery('#weekWise_Grid').jqGrid('getRowData');

			

		mach = jQuery( 'input:[name=cmbPmsdMachineid]').val();
		//alert("gridLength  1  "+gridLength.length);
		
		//if(gridLength.length<=0 || gridLength.length== undefined) 
		
		//alert("gridLength  2  "+gridLength.length);
		var elementId =  jQuery('#frmPmStandard input[id=elementId]').val(); 
		var elemId =  elementId+"&formField=A&pntId="+elementId+"&masterSelId="+mach;
		if(mach != ' ' && mach != '' && mach != null){
			//LoadPopUp("funcnLocn_getCol.funlocn","?elemType="+fact+"-"+sect+"-"+mach+"-"+MCH000119+"-&formField=A","Functional Location");
			if(gridLength.length>0  )
				funcnLocnPopUp("funcnLocn_input.funlocn",elemId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Assembly","true");
		}
		else
			alert('Select Equipment');
		//navigateToNextForm(url,formheader,forwardData,persistentData,navigateToNext_SuccessCalBack,navigateToNext_ErrorCalBack)
	});
	
	jQuery('#btnView').click(function(){
		var url = jQuery('#hiddenUrl').val();
		
		var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
		var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('input:[name=cmbPmsdMachineid]').val();
		var activitytype = getFieldValue('cmbActivitytype','frmPmStandard');//jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
		var costCenter = getFieldValue('cmbPmstdCostCenter','frmPmStandard');

		if(activitytype.trim().length<=0)
			activitytype = jQuery('#hdnActType').val();
		//alert("activitytype  "+activitytype);

		jQuery('#hdnnewActType').val(activitytype);
		var actType = jQuery('#hdnnewActType').val();
		//alert(jQuery('#hdnnewActType').val());
		var filterString = '&cmbFactid='+fact;
			filterString += '&cmbSectid='+sect; 
			filterString += '&cmbCellid='+cell; 
			filterString += '&cmbMchid='+mach +"&cmbCostCenter="+costCenter;
			
			var hdnMachine = jQuery('#hdnMachine').val();
			 var url = jQuery('#hiddenUrl').val(); 
			 
			 var gUrl = url.substring(url.indexOf('MouldpmAssembly_input.prv'),url.indexOf('?q=2'));
			 var grdUrl ='';
			
				 if('MouldpmAssembly_input.prv'== gUrl )
					 grdUrl = "MouldpmActivity_input.prv?q=2&filterString=";
				 else
					 grdUrl = "pmActivity_input.prv?q=2&filterString=";	
			//alert(actType);
			//alert(activitytype.trim().length);
			/* Commented as per cust requirement*/
				/* if(actType.trim().length<=0 ){
						
					  alert('select activity type ');
					  return false;
					 }*/
				
					 /* if(sect != " " && sect != "" && sect != null ){*/
					 
					// for(var i=formNavigations.length;i>3;i--) {
					//	 popFormNavigation();
					 //}

						 if(jQuery('#chkactWise').is(':checked') == true){
								filterString +="&chkd=chkactWise";
								filterString += '&cmbjobtype='+activitytype;

								navigateToNextForm(grdUrl+filterString+"&loadContentDivId=Loadactivitygridfrm&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false");
								 //processGridnew(url,filterString,"grid2","pager","","dblclick","","");
								 jQuery('#imgPendingWrkordr').show();
								 jQuery('#btnNewstd').show();
								 jQuery('#lblHeader').html("Activity Type");
							}
						 //else if(jQuery('#chkassmWise').is(':checked') == true)
						 else{
								filterString +="&chkd=chkassmWise";
								filterString += '&cmbjobtype='+activitytype;
	
								formNavigations.pop();
								navigateToNextForm("pmAssembly_input.prv?q=2&skipForm=true&filterString="+filterString+"&loadContentDivId=Loadassemblygridfrm&preLoadContentDivId=preloadDIVid1&isHidePrevForm=false",null,null,null,"frmPmStandrdNavigNext_SuccessCalBack");
								// processGridnew(url,filterString,"grid2","pager","","","","loadComFunction");
								 jQuery('#imgPendingWrkordr').hide();
								 jQuery('#btnNewstd').hide();
								 
								 
								//disableField('frmPmStandard','btnView');

									
								 //jQuery('#lblHeader').html("PM Standard");
//								 popFormNavigation();
							}
						 /* }
					 else{
							alert("Select Section");
							return false;
					 }
				 
					}
				  else{
					  alert('select activity type ');
					  return false;
					 }*/

					
					
				 
});
	jQuery('#chkactWise').click(function(){
		
			chekChkBox("chkactWise");
		});
		jQuery('#chkassmWise').click(function(){
			
			chekChkBox("chkassmWise");
		});
		function chekChkBox(chkname){
			 if(chkname == "chkactWise"){
				if(jQuery('#chkactWise').is(':checked') == true){
					jQuery('input:checkbox[name=chkassmWise]').attr('checked',false);
				}
			 }
			 else{
				 if(jQuery('#chkassmWise').is(':checked') == true){
						jQuery('input:checkbox[name=chkactWise]').attr('checked',false);
						
				 }
			 }
		}
		function disablControl(action){
			
			if(action == 'disable'){
				// disableField('frmPmStandard','cmbActivitytype');
				 disableField('frmPmStandard','cmbPMMachineid');
				 disableField('frmPmStandard','cmbPmstdCostCenter');
				 disableField('frmPmStandard','chkassmWise');
				 
				 jQuery("#btnView").removeAttr('class', 'easyui-button');
				 jQuery("#btnView").attr('class', ' disabledButton');
				 jQuery("#btnView").attr('disabled','disabled');
				 jQuery("#btnclearcntrl").removeAttr('class', 'easyui-button');
				 jQuery("#btnclearcntrl").attr('class', 'disabledButton');
				 jQuery('#btnclearcntrl').attr('disabled','disabled');
				 jQuery('#pmsdfunLocation').attr('disabled','disabled');
				// jQuery("#functLocHierarPopupId").dialog('close');
				  
				
			}
			else if(action == 'enable'){
				enableFields('cmbActivitytype');
				enableFields('cmbPMMachineid');
				enableFields('cmbPmstdCostCenter');
				enableFields('chkassmWise');
				// jQuery("#functLocHierarPopupId").dialog('open');
				jQuery("#btnView").removeAttr('class', 'disabledButton');
				 jQuery("#btnView").attr('class', ' easyui-button');
				 jQuery("#btnView").attr('disabled',false);
				 jQuery("#btnclearcntrl").removeAttr('class', 'disabledButton');
				 jQuery("#btnclearcntrl").attr('class', ' easyui-button');
				 jQuery('#btnclearcntrl').attr('disabled',false);
				
				}
		}
		function frmPmStandard_beforeLoadCurrentForm(result)
		{
					
			
			
			jQuery('#btnView').trigger('click');
			flenableLayout();
			
			if(jQuery("#newMstFrm").is(":visible") ==  true){
				jQuery("#newMstFrm").hide(0);	
				return 'OK';
			}
			else		
				return true;
			
			
		}
		function MultiSelectCancel_CallBack(args)
		{
			
			jQuery('#multiselectPopUpId').dialog('close');
			return true;
		}
		function Assm_dblclick(id,iCol,colVal){
          alert(id+"---"+iCol+"---"+colVal);
       
        var colm = jQuery("#grid2").jqGrid ('getGridParam', 'colModel');
		var selId = colm[iCol].name;	
		 var rowData = jQuery("#grid2").jqGrid('getRowData',"1");
		//alert( rowData[selId]);
		var tradeId = rowData[selId];
		//alert(selId);
	
																						
		//var pmstdKeyid = rowData.keyid;
        var url = jQuery('#hiddenUrl').val();
		var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
		var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('input:[name=cmbPmsdMachineid]').val();
		var activitytype= getFieldValue('cmbActivitytype');
		var filterString = '?cmbFactid='+fact;
			filterString += '&cmbSectid='+sect; 
			filterString += '&cmbCellid='+cell; 
			filterString += '&cmbMchid='+mach;
		    filterString += "&cmbjobtype="+activitytype;
            filterString += "&chkd=tradeWise";
            filterString += "&cmbTradeid="+tradeId;
            //processGridnew(url,filterString,"grid2","pager",""," ","","");
		  }
		function multiSelectOk_Callback(args)
		{
			
		}
	/*	jQuery('#btnTabSelect').click(function()
				{	
	//"GenWOGrid","GenWOPager","","genWODBLClick"
					var rowid = jQuery("#assmGrid").jqGrid('getGridParam','selrow');
					alert(rowid);
					if(rowid=='' || rowid== null){

						alert("Select Order Number to Complete The Activities");
						}	
					//else						
					//genWODBLClick(rowid);
				 }); */
				 function grid2_dblclick(id, iCol) {
					 alert(m123);
					    var colm = jQuery("#grid2").jqGrid('getGridParam', 'colModel');
					    var tradeId = (iCol != null && colm[iCol]) ? colm[iCol].name : "";

					    var rowData = jQuery("#grid2").jqGrid('getRowData', id);
					    var mchId = jQuery('#cmbPMMachineid').combobox('getValue');   // confirm actual field
					    var flid  = jQuery("#frmPmStandard input[id='flid']").val();
					    var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");

					    LoadPopUp(
					        "prvnMntncStdDiv",
					        "prvnt_mntncform_multiple_input.prv?pmsdMachineID=" + encodeURIComponent(mchId)
					            + "&tradeId=" + encodeURIComponent(tradeId)
					            + "&activitytype=" + encodeURIComponent(activitytype)
					            + "&pmsdflid=" + encodeURIComponent(flid),
					        true, "90%","82%","1%","1%","",
					        "Maintenance Standards","",true
					    );
					}
</script>
<form id="frmPmStandard" name="frmPmStandard" action="" method="post">
<div id="wrapper" style=" ">
<div id = "frmPmstdbdr" class="main-cntborder easyui-paddingbtpx" style="height:90%">
<div id="top_div" style="margin-left:1.8%;">
<table border="0" class="tablealign-center" style="margin-top:-12;margin-left:30px;" >
    <tr>        
    <td colspan='3' >
			 	<div  id="frmPmStandardFuntKeyIds">
				<input type="hidden" id="factory" name="cmbPmsdFactoryid" value="${requestScope.cliTlStandards.clisFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbPmsdSectionid" value="${requestScope.cliTlStandards.clisSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbPmsdCellid" value="${requestScope.cliTlStandards.clisCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbPmsdMachineid" value="${requestScope.cliTlStandards.clisMachineid}"  ></input>
				<input type="hidden" id="flid" name="cmbPmsdFlid" value="${requestScope.plmTlStandards.pmsdFlid}"  ></input>
				<input type="hidden" id="elementId" name="cmbPmsdElementid" value="${requestScope.plmTlStandards.pmsdElementid}"  ></input>
				</div>
			 	<div id="pmsdfunLocation" style="width:123%;margin-top: 17px;"></div>
       </td>  
<!--        <td rowspan='2' colspan='2' valign="middle">-->
<!--       				<div style="font-weight:bold;width:90%" class="legnd">PRM - Preventive Maintainence</div>-->
<!--					<div style="font-weight:bold;width:90%" class="legnd">CBM - Condition Based Maintainence</div>-->
<!--					<div style="font-weight:bold;width:90%" class="legnd">TBM - Time Based Maintainence</div>-->
<!--        </td>-->
       <!--<td>
       		<div  id="btnChkDiv" style="float:right; width: 36.3%;margin-top: -1.3%; position: absolute; right: 12%;"  class="">
	         <input id="chkactWise" name="chkactWise" value="" type="checkbox" /> <label>Activity Wise</label>
	             <span style="margin-left: -1%; margin-bottom:6px;">
	             <input id="chkassmWise" name="chkassmWise" value="" type="checkbox" checked="checked"/> 
	             <label>Assembly Wise</label>
	             </span>
	             <span>
				 <input type="button" value="View" class="easyui-button" id="btnView" style="height:21px;">
				 </span>
				 <span id="clrButn" style="">
				 <input type="button" value="Clear" class="easyui-button" id="btnclearcntrl" style="height:21px;">
				 </span>
				 <span style="">
				 <input type="button" value="Add Assembly" class="easyui-button" id="btnaddAssembly" style=" height : 21px;">
				 </span>
				 <span id="backbutn" style="float:right;margin-right:16%;">
				 <input type="button" value="Back" class="easyui-button" id="btnBack" style="height:21px;">
				 </span>
      		
      		 </div>
       </td>
	 --></tr>
	 <tr>  
	  <td valign='top' style="width:27%;">
            <div id="tt" style="padding-left:0px;">
                <div><label>Cost Center</label></div> 
                <div   class="easyui-paddingbfpx"> 
                    <input id="cmbPmstdCostCenter" name="cmbPmstdCostCenter" class="easyui-combobox"  style="width:230px;" value="${requestScope.pmMaingridCmbCostcenter}"  >
                </div>
               
            </div>	
        </td>
        <td valign='top' style="width:27%;" >
            <div style="float:left;">
               <div id="machDiv" style="display:none;">
	               <div>
	                    <label>Equipment</label>
	                </div> 
	                <div   class="easyui-paddingbfpx"> 
	                    <input id="cmbPMMachineid" name="cmbPMMachineid" class="easyui-combobox"  style="width:230px;" value="${requestScope.pmMaingridCmbMchid}"  >                    
	                </div>
	                </div>  
            </div>
            <div id="MldDiv" style="display:none;">
            	<div class="easyui-paddingbfpx">
           			<label>Mould</label>                       
       		   </div> 
                <div class=""> 
                     <input id="cmbMouldid" name="cmbMouldid" class="easyui-combobox"  style="width:230px" value=""  >     
               </div>
            </div>
        </td>
        
        <td valign='top'  style="" >
            
                              
                <div>
                    <label >Activity Type</label>                    
                </div>
                <div class="easyui-paddingbfpx "> 
                    <input id="cmbActivitytype" name="cmbActivitytype" class="easyui-combobox"  style="width:230px;" value="${requestScope.pmMaingridCmbjobtype}">                    
                </div> 
              <span style="padding-left:20px; margin-top:-10px;">
			    <input  type="button" class="easyui-button" id="btnTabSelect" name="btnTabSelect" value="For Tab"/>		
		     </span>
        </td>
        <td></td>
       
    </tr>
    
</table>

 </div>
<!-- <br>-->
<div id="pmFirstTable">
	<table width="100%">
  <tr>
    <th >
    <div id="headin" class="sub-header" style=" margin-bottom: 5px; width: 85.5%; width: 856px\9; margin-left: 4.5%;margin-left: 50px\9;float:left; height:20px;">
    <span style="font-size:15px;font-weight:bold;margin-top:10px;" id="lblHeader">Activity Wise</span>
   	<span style="padding-left:140px; ">
    <input id="chkassmWise" name="chkassmWise" value="" type="checkbox"  /><label style="padding-left:10px;">Assembly Wise</label>
    	<input type="button" value="View" class="easyui-button" id="btnView" style="height:21px; width:50px;">
    	<input type="button" value="Clear" class="easyui-button" id="btnclearcntrl" style="height: 21px;width:90px;">
<!-- 		<input type="button" value="base" class="easyui-button" id="btnBase" style="height:21px;"> -->
    	<input type="button" value="Add Assembly" class="easyui-button" id="btnaddAssembly" style=" height : 21px;width:90px;">
     <span id="backbutn" style="margin-left:0.5%; " >
     	<input type="button" value="Back" class="easyui-button" id="btnBack" style="height:21px;width:50px;">
     </span>
    <!--<span style="" class='legnd'>PRM - Preventive Management</span>
    <span style="" class='legnd'>CBM - Condition Based Management</span>
    <span style="" class='legnd'>TBM - Time Based Management</span>
      --><span  style="margin-top:-21px; margin-left:400px;" id="btnNewstd">
    <input type="button" value="New Standard" class="easyui-button" id="btnNewStandard" style="height: 21px;width:90px;">
         <input type="button" value="Copy Standard" class="easyui-button" id="btnCopyStandard" style="height: 21px;width:95px;">
         <input type="button" value="Delete Standard" class="easyui-button" id="btnDelStandard" style="height: 21px;width:105px;">
         <input type="button" value="Back" class="easyui-button" id="btnBack" style="height:21px;width:50px;"></div>
    
<!--    <input type="button" value="Export to Excel" class="easyui-button" onclick="">-->
<!--    <input type="button" value="Back" class="easyui-button" onclick="return find_wndw_click()">-->
	
    </span>
   </span>
    </div>
    <div id="tradeName" style="margin-bottom: 10px;width:96%;margin-left:4%;display:none; ">
    <label id="tradeNameLbl" class="notes" style=" text-align: left;width:60%;"></label>
    <span class="notes floatleft" style="margin-top:-6">
    <span  style="font-weight:bold;padding-left:0px;display:none;margin-top:4;" id="imgPendingWrkordr"> 
    			<span class="circlepmExist" style="padding:0px 10px 0px 6px"></span><label style="font-size: 12px;vertical-align:1px;padding-left:5px;">Pending Work-Order</label>
    			<span class="circle" style="padding:0px 10px 0px 6px"></span><label style="font-size: 12px;vertical-align:1px;padding-left:5px;">From Why Why</label>
	</span>
	</span>
    </div>
    </th>    
    
  </tr>
   <!--<tr>
  <td >
  <div  class="easyui-paddingbfpx">
                <div  class="easyui-paddingbfpx">                    
                </div> 
                <div class="easyui-paddingbfpx">
                <table id="grid2" style="width:100%"><tr><td/></tr></table>
                <div id="pager2">
                </div>
                </div>
                </div>
  </td>
  </tr>
--></table>
</div>  

<div id="preloadDIVid1"></div>
<div id="preloadDIVid2"></div>
<div id="preloadDIVid3"></div>
<div id="preloadDIVid4"></div>
<div id="preloadDIVid5"></div>
<div id="Loadassemblygridfrm" style=' float:left;margin-left:5%'></div>
<div id="LoadassmbWeekgridfrm" style='display:none;float:left;margin-left:5%'></div>
<div id="Loadactivitygridfrm" style='display:none;float:left;margin-left:5%'></div>
<div id="LoadPmStdfrm"></div>
<div id="LoadSparesfrm"></div>
</div>
</div>

<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnActType" name="hdnActType"/>
<input type="hidden" id="hdnTradeId" name="hdnTradeId"/>
<input type="hidden" id="hdnnewActType" name="hdnnewActType"/>
<input type="hidden" id="chkZero" name="chkZero"/>
<input type="hidden" id="getiCol" name="getiCol"/>
<input type="hidden" id="getcellContent" name="getcellContent"/>
<input type="hidden" id="hiddenMachField" name="hiddenMachField" value="${requestScope.whyMachineId}"/>
<input type="hidden" id="hdnMachine" name="hdnMachine" value="${requestScope.whyMachineId}"/>
<input type="hidden" id="hdnActivityType" value="${requestScope.activityType}"/>
<input type="hidden" id="hdnTenStpFlid"  value="${requestScope.tFlid}"/>
</form>
