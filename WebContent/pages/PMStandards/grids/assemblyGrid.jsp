<script type="text/javascript">
	jQuery(document).ready(function(){
		 disablControl('enable');
		 
		jQuery("#top_div").css('display',"block");
		jQuery(".main-cntborder ").css("height","91%");
		//jQuery('#btnChkDiv').css("margin-top","-1%");
		//jQuery('#headin').css('display','none');
		jQuery('#headin').show();
		jQuery('#lblHeader').html('Assembly Wise');
		jQuery('#btnChkDiv').css('display','block');
		var url = jQuery('#hiddenUrl').val();
		var gUrl = url.substring(url.indexOf('preventive_input.prv'),url.indexOf('?q=2'));
		 var actType =  jQuery('#cmbActivitytype').combobox("getValue");
		var grdUrl ="";
		 if(gUrl ==  'pmAssembly_input.prv')
			 grdUrl = "pmAssembly_input.prv?q=2";
		 else
			 grdUrl = "MouldpmAssembly_input.prv?q=2";
		var fltrStr = jQuery('#fltrStr').val();
		if (fltrStr != ' ' && fltrStr!= '' && fltrStr != null)
			fltrStr  = '&'+fltrStr ;
		else
			fltrStr  = '-';
		if(fltrStr.substring(fltrStr.indexOf("FCT"),fltrStr.indexOf("&cmbSectid"))=="cmbFactid=null")
			fltrStr  = '-';

		var machidgrid =jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");// rowData.KEYID;
		fltrStr += "&cmbMchid="+machidgrid;
		var costcntr = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
		fltrStr += "&cmbCostCenter="+costcntr+"&cmbjobtype="+actType;
		
		/*if(jQuery("#hdnTenStpFlid").val()!= null)
			{
			fltrStr="";
			fltrStr="&cmbFactid=&cmbSectid=&cmbCellid=&cmbMchid=&cmbjobtype=null&chkdchkactWise&cmbMchid= &cmbCostCenter= &cmbjobtype=";
			}*/
		
			
		processGridnew(grdUrl,fltrStr,"assmGrid","","","pmAssembly_dblclick","","loadComFunction");
		jQuery('#imgPendingWrkordr').css('display','none');
		 jQuery('#btnNewstd').css('display','none');
		// jQuery('#lblHeader').html("PM Standard");
		 jQuery('#backbutn').css('display','none');	
		 jQuery('.legnd').css('visibility','hidden');
		 jQuery('#fltrStr').val(' ');
		

		 if( jQuery("#btnView").length > 0){
			 jQuery("#btnView").removeAttr('class', 'disabledButton');
			 jQuery("#btnView").attr('class', ' easyui-button');
			 jQuery("#btnView").attr('disabled',false);
	        }
		// setFieldValue('cmbActivitytype',actType,'frmPmStandard');
		
	});
/* 	function pmAssembly_dblclick(rowid){
		alert(" Correct Double Click ");

		//var grdmachid = jQuery("#assmGrid").jqGrid('getCell',rowid,"MCHM_KEYID");
		var grdmachid = jQuery("#assmGrid").jqGrid('getCell',rowid,"mchm_keyid");
	
		alert(grdmachid+'grdmachid');
		setFieldValue('cmbPMMachineid',grdmachid,'frmPmStandard');
		
		var chk_zero = jQuery('#chkZero').val();
		var filterString='';
		var iCol = jQuery('#getiCol').val();
		var colRowData=jQuery('#assmGrid').jqGrid('getGridParam', 'colNames');// jQuery("#assmGrid").jqGrid ('getRowData','RN');
		var colm = jQuery("#assmGrid").jqGrid ('getGridParam','colModel');
      	var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
      	var perflid = jQuery("#frmPmStandard input[id='flid']").val();
      	var machidgrid =jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");
      	alert(3643);// rowData.KEYID;
		if( machidgrid == null || machidgrid == "" || machidgrid.length == 0)
			machidgrid  = rowData.mchm_keyid ; 
		alert(123);

		
		setFieldValue('cmbPMMachineid',machidgrid,'frmPmStandard');
		var record = {"id":machidgrid};

		frmPmStandardcmbPMMachineid_onSelect(record);

		setTimeout(function() {
		//jQuery('#cmbMachineid').val(machidgrid);		
							
        if( jQuery("#btnView").length > 0){
        	jQuery("#btnView").attr('class', ' disabledButton');
			jQuery("#btnView").attr('disabled','disabled');
        }	
		jQuery('#hiddenMachField').val(machidgrid);
		var selId =colRowData[iCol];//colRowData[colm[iCol].name];
		//alert(selId);
		//var tradename = jQuery('#jqgh_assmGrid_'+selId).html(); 
		//alert(tradename);
		//var  trdname = tradename.substring(0,tradename.indexOf("<span")) ;
		   //alert(trdname );
	
		var tradeId = colm[iCol].name;//selId;
		var cellcontent= jQuery('#getcellContent').val();
		var url = jQuery('#hiddenUrl').val();
		var gUrl = url.substring(url.indexOf('pmAssembly_input.prv'),url.indexOf('?q=2'));
		var grdUrl ="";
		// if(gUrl == 'pmAssembly_input.prv')
			 grdUrl = "pmActivity_input.prv?q=2";
		 //else
		//	 grdUrl = "MouldpmActivity_input.prv?q=2";
		// alert(" chk_zero::::: "+chk_zero);
		 if(chk_zero != 'zero' ){
			 if(jQuery('#chkassmWise').is(':checked') == false){
				 var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();//jQuery("#frmPmStandard input[id='cmbPmsdFactoryid']").combobox("getValue");//jQuery('input:[name=cmbPmsdFactoryid]').val();
					var sect =jQuery('input:[name=cmbPmsdSectionid]').val();// jQuery("#frmPmStandard input[id='cmbPmsdSectionid']").combobox("getValue");// jQuery('input:[name=cmbPmsdSectionid]').val();
					var cell = jQuery('input:[name=cmbPmsdCellid]').val();//jQuery("#frmPmStandard input[id='cmbPmsdCellid']").combobox("getValue");//jQuery('input:[name=cmbPmsdCellid]').val();
					var mach = jQuery('input:[name=cmbPmsdMachineid]').val();//jQuery("#frmPmStandard input[id='cmbPmsdMachineid']").combobox("getValue");//jQuery('input:[name=cmbPmsdMachineid]').val();
					var activitytype = jQuery('#hdnActType').val();

						var costcenterId = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
					var flid = jQuery("#frmPmStandard input[id='flid']").val();
					var jsonstr = '';
						filterString = '&cmbFactid='+fact;
						filterString += '&cmbSectid='+sect; 
						filterString += '&cmbCellid='+cell; 
						filterString += '&cmbMchid='+mach;
						filterString += '&cmbTradeid='+tradeId;
						filterString += "&cmbjobtype="+activitytype;
						filterString += '&TN='+colRowData[iCol] +'name';
						filterString += '&cmbCostcenter='+costcenterId ;
						filterString += '&flid='+flid ;
						 jsonstr = '{"cmbFactid":"'+fact+'" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbTradeid":"'+tradeId+'","cmbjobtype":"'+activitytype+'"}';
						var persistdata =  jQuery.parseJSON(jsonstr);
						var forwarddata =jQuery.parseJSON( '{"flid":"'+perflid+'" }');
				 if(iCol >= 1 && iCol <=5){
						alert("Click on Equipment Row and Maint.Section Column");
					 }
				 else{
					jQuery("#Loadassemblygridfrm").html('');
					jQuery("#hdnTenStpFlid").val(flid);
					navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
				 }
				 //processGridnew(url,filterString,"grid2","pager","","dblclick","","");
				 jQuery('#imgPendingWrkordr').css('display','block');
				 jQuery('#btnNewstd').css('display','block');
				 jQuery('#lblHeader').html("Activity Type");
			 }
			 else{
				 //keyid
				 var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
				 var machid_grid = rowData.mchm_keyid;
				
				 jQuery('#hiddenMachField').val(machid_grid);
				 
				  setFieldValue('cmbPMMachineid',jQuery('#hiddenMachField').val(),'frmPmStandard');
				  loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+machid_grid);
					fillMachineHierarchy("machineHierarchy.commonFilter",machid_grid,"cmbCell","cmbSection","cmbFactory","", "","cmbCostCenter");	
					 if(iCol >= 1 && iCol <=5){
							alert("Click on Equipment Row and Maint.Section Column");
						 }
					 else{//alert('inside else');
					 Assm_dblclick(rowid,iCol,cellcontent);
					 }
				 }
 			}
			else{//alert(" machidgrid.trim().length:::: "+machidgrid.trim().length);
				if(machidgrid.trim().length<=0)
				{
					
					loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+grdmachid);
					openNewStandard(rowid,iCol,cellcontent,"assmGrid");
					//alert('Select Equipment');
					//return false;
				 }
				else{	//alert(" going to that grid ");
					openNewStandard(rowid,iCol,cellcontent,"assmGrid");
			}}
		},800);
		// disablControl('disable');
		} */
		
		/* function pmAssembly_dblclick(rowid){
			alert(" Correct Double Click ");
			console.log("=== pmAssembly_dblclick fired ===");
			console.log("rowid:", rowid);

			var grdmachid = jQuery("#assmGrid").jqGrid('getCell',rowid,"mchm_keyid");
			console.log("grdmachid:", grdmachid);

			setFieldValue('cmbPMMachineid',grdmachid,'frmPmStandard');
			
			var chk_zero = jQuery('#chkZero').val();
			console.log("chk_zero:", chk_zero);

			var filterString='';
			var iCol = jQuery('#getiCol').val();
			console.log("iCol (raw from #getiCol):", iCol, typeof iCol);

			var colRowData=jQuery('#assmGrid').jqGrid('getGridParam', 'colNames');
			console.log("colRowData (colNames):", colRowData);

			var colm = jQuery("#assmGrid").jqGrid ('getGridParam','colModel');
			console.log("colModel full array:", colm);
			console.log("colModel names in order:", colm.map(function(c,i){ return i+":"+c.name; }));

	      	var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
	      	console.log("rowData for this row:", rowData);

	      	var perflid = jQuery("#frmPmStandard input[id='flid']").val();
	      	var machidgrid =jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");
	      	console.log("perflid:", perflid, "| machidgrid (from combobox):", machidgrid);

			if( machidgrid == null || machidgrid == "" || machidgrid.length == 0)
				machidgrid  = rowData.mchm_keyid ; 
			console.log("machidgrid (final, after fallback to rowData.MCHM_KEYID):", machidgrid);

			setFieldValue('cmbPMMachineid',machidgrid,'frmPmStandard');
			var record = {"id":machidgrid};

			frmPmStandardcmbPMMachineid_onSelect(record);

			setTimeout(function() {
	        if( jQuery("#btnView").length > 0){
	        	jQuery("#btnView").attr('class', ' disabledButton');
				jQuery("#btnView").attr('disabled','disabled');
	        }	
			jQuery('#hiddenMachField').val(machidgrid);
			var selId =colRowData[iCol];
			console.log("selId (colRowData[iCol]):", selId);

			var tradeId = colm[iCol].name;
			console.log(">>> tradeId (colm[iCol].name) - THIS IS THE COLUMN KEY CLICKED:", tradeId, "| iCol used:", iCol);

			var cellcontent= jQuery('#getcellContent').val();
			console.log("cellcontent:", cellcontent);

			var url = jQuery('#hiddenUrl').val();
			console.log("hiddenUrl raw:", url);

			var gUrl = url.substring(url.indexOf('pmAssembly_input.prv'),url.indexOf('?q=2'));
			console.log("gUrl parsed:", gUrl);

			var grdUrl ="";
			grdUrl = "pmActivity_input.prv?q=2";
			console.log("grdUrl:", grdUrl);

			 console.log("chkassmWise checked?:", jQuery('#chkassmWise').is(':checked'));

			 if(chk_zero != 'zero' ){
				 if(jQuery('#chkassmWise').is(':checked') == false){
					 var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
						var sect =jQuery('input:[name=cmbPmsdSectionid]').val();
						var cell = jQuery('input:[name=cmbPmsdCellid]').val();
						var mach = jQuery('input:[name=cmbPmsdMachineid]').val();
						var activitytype = jQuery('#hdnActType').val();

							var costcenterId = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
						var flid = jQuery("#frmPmStandard input[id='flid']").val();
						var jsonstr = '';
							filterString = '&cmbFactid='+fact;
							filterString += '&cmbSectid='+sect; 
							filterString += '&cmbCellid='+cell; 
							filterString += '&cmbMchid='+mach;
							filterString += '&cmbTradeid='+tradeId;
							filterString += "&cmbjobtype="+activitytype;
							filterString += '&TN='+colRowData[iCol] +'name';
							filterString += '&cmbCostcenter='+costcenterId ;
							filterString += '&flid='+flid ;
							 jsonstr = '{"cmbFactid":"'+fact+'" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbTradeid":"'+tradeId+'","cmbjobtype":"'+activitytype+'"}';
							var persistdata =  jQuery.parseJSON(jsonstr);
							var forwarddata =jQuery.parseJSON( '{"flid":"'+perflid+'" }');

					 console.log("---- ABOUT TO CHECK iCol RANGE BLOCK ----");
					 console.log("iCol value:", iCol, "| iCol >= 1 && iCol <= 5 ?", (iCol >= 1 && iCol <= 5));

					 if(iCol >= 1 && iCol <=5){
							console.warn("BLOCKED: iCol is in the 1-5 range, showing alert instead of navigating. tradeId was:", tradeId);
							alert("Click on Equipment Row and Maint.Section Column");
						 }
					 else{
						console.log("PASSED range check — proceeding to navigateToNextForm");
						console.log("Final filterString:", filterString);
						console.log("Final grdUrl + filterString:", grdUrl+"&filterString="+filterString);
						jQuery("#Loadassemblygridfrm").html('');
						jQuery("#hdnTenStpFlid").val(flid);
						navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
					 }
					 jQuery('#imgPendingWrkordr').css('display','block');
					 jQuery('#btnNewstd').css('display','block');
					 jQuery('#lblHeader').html("Activity Type");
				 }
				 else{
					 var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
					 var machid_grid = rowData.mchm_keyid;
					 console.log("assmWise checked branch — machid_grid:", machid_grid);
					
					 jQuery('#hiddenMachField').val(machid_grid);
					 
					  setFieldValue('cmbPMMachineid',jQuery('#hiddenMachField').val(),'frmPmStandard');
					  loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+machid_grid);
						fillMachineHierarchy("machineHierarchy.commonFilter",machid_grid,"cmbCell","cmbSection","cmbFactory","", "","cmbCostCenter");	
						 if(iCol >= 1 && iCol <=5){
								console.warn("BLOCKED (assmWise branch): iCol in 1-5 range");
								alert("Click on Equipment Row and Maint.Section Column");
							 }
						 else{
						 console.log("PASSED range check (assmWise branch) — calling Assm_dblclick");
						 Assm_dblclick(rowid,iCol,cellcontent);
						 }
					 }
	 			}
				else{
					console.log("chk_zero == 'zero' branch. machidgrid.trim().length:", machidgrid.trim().length);
					if(machidgrid.trim().length<=0)
					{
						console.log("No machine id — loading functional location + openNewStandard with grdmachid:", grdmachid);
						loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+grdmachid);
						openNewStandard(rowid,iCol,cellcontent,"assmGrid");
					 }
					else{
						console.log("Machine id present — openNewStandard directly");
						openNewStandard(rowid,iCol,cellcontent,"assmGrid");
				}}
			},800);
			} */
		/*	 function pmAssembly_dblclick(rowid){
				alert("mano123");
			    var grdmachid = jQuery("#assmGrid").jqGrid('getCell',rowid,"mchm_keyid");
			    setFieldValue('cmbPMMachineid',grdmachid,'frmPmStandard');
			    
			    var chk_zero = jQuery('#chkZero').val();
			    var filterString='';
			    var iCol = jQuery('#getiCol').val();
			    var colRowData=jQuery('#assmGrid').jqGrid('getGridParam', 'colNames');
			    var colm = jQuery("#assmGrid").jqGrid ('getGridParam','colModel');
			    var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
			    var perflid = jQuery("#frmPmStandard input[id='flid']").val();
			    var machidgrid =jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");

			    if( machidgrid == null || machidgrid == "" || machidgrid.length == 0)
			        machidgrid  = rowData.mchm_keyid ; 

			    setFieldValue('cmbPMMachineid',machidgrid,'frmPmStandard');
			    var record = {"id":machidgrid};
			    frmPmStandardcmbPMMachineid_onSelect(record);

			    setTimeout(function() {
			        if( jQuery("#btnView").length > 0){
			            jQuery("#btnView").attr('class', ' disabledButton');
			            jQuery("#btnView").attr('disabled','disabled');
			        }	
			        jQuery('#hiddenMachField').val(machidgrid);
			        var selId =colRowData[iCol];
			        var tradeId = colm[iCol].name;
			        var cellcontent= jQuery('#getcellContent').val();
			        var url = jQuery('#hiddenUrl').val();
			        var gUrl = url.substring(url.indexOf('pmAssembly_input.prv'),url.indexOf('?q=2'));
			        var grdUrl = "pmActivity_input.prv?q=2";

			        var nonTradeColumns = ["mchm_machinename", "mchm_machinecode"]; // confirm exact names from colModel
			        var isNonTradeColumn = nonTradeColumns.indexOf((tradeId || "").toLowerCase()) !== -1;

			        if(chk_zero != 'zero' ){
			            if(jQuery('#chkassmWise').is(':checked') == false){
			                // FIX: removed the invalid colon before [name=...] — this was
			                // throwing "Syntax error, unrecognized expression" and killing
			                // the whole function before the popup could ever open
			                var fact = jQuery('input[name=cmbPmsdFactoryid]').val();
			                var sect = jQuery('input[name=cmbPmsdSectionid]').val();
			                var cell = jQuery('input[name=cmbPmsdCellid]').val();
			                var mach = jQuery('input[name=cmbPmsdMachineid]').val();
			                var activitytype = jQuery('#hdnActType').val();
			                var costcenterId = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
			                var flid = jQuery("#frmPmStandard input[id='flid']").val();

			                filterString = '&cmbFactid='+fact;
			                filterString += '&cmbSectid='+sect; 
			                filterString += '&cmbCellid='+cell; 
			                filterString += '&cmbMchid='+mach;
			                filterString += '&cmbTradeid='+tradeId;
			                filterString += "&cmbjobtype="+activitytype;
			                filterString += '&TN='+colRowData[iCol] +'name';
			                filterString += '&cmbCostcenter='+costcenterId ;
			                filterString += '&flid='+flid ;
			                var jsonstr = '{"cmbFactid":"'+fact+'" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbTradeid":"'+tradeId+'","cmbjobtype":"'+activitytype+'"}';
			                var persistdata =  jQuery.parseJSON(jsonstr);

			                if(isNonTradeColumn){
			                    alert("Click on Equipment Row and Maint.Section Column");
			                }
			                else{
			                    jQuery("#Loadassemblygridfrm").html('');
			                    jQuery("#hdnTenStpFlid").val(flid);
			                    navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
			                }
			                jQuery('#imgPendingWrkordr').css('display','block');
			                jQuery('#btnNewstd').css('display','block');
			                jQuery('#lblHeader').html("Activity Type");
			            }
			            else{
			                var rowData = jQuery("#assmGrid").jqGrid('getRowData',rowid);	
			                var machid_grid = rowData.mchm_keyid;
			                jQuery('#hiddenMachField').val(machid_grid);
			                setFieldValue('cmbPMMachineid',jQuery('#hiddenMachField').val(),'frmPmStandard');
			                loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+machid_grid);
			                fillMachineHierarchy("machineHierarchy.commonFilter",machid_grid,"cmbCell","cmbSection","cmbFactory","", "","cmbCostCenter");	

			                if(isNonTradeColumn){
			                    alert("Click on Equipment Row and Maint.Section Column");
			                }
			                else{
			                    Assm_dblclick(rowid,iCol,cellcontent);
			                }
			            }
			        }
			        else{
			            if(machidgrid.trim().length<=0)
			            {
			                loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard","&machId="+grdmachid);
			                openNewStandard(rowid,iCol,cellcontent,"assmGrid");
			             }
			            else{
			                openNewStandard(rowid,iCol,cellcontent,"assmGrid");
			            }
			        }
			    },800);
			} */
			/* function pmAssembly_dblclick(rowid) {

			    var iCol = jQuery('#getiCol').val();          // set by onCellSelect in loadComFunction
			    var colm = jQuery("#assmGrid").jqGrid('getGridParam', 'colModel');
			    var tradeId = (iCol != null && colm[iCol]) ? colm[iCol].name : "";

			    // block only the machine-identity columns — NOT trade columns.
			    // CONFIRM these two names match colm[].name for your "Machine Name" /
			    // "Machine No" columns (log colm in console once to be 100% sure).
			    var nonTradeColumns = ["mchm_machinename", "mchm_machinecode"];
			    if (nonTradeColumns.indexOf((tradeId || "").toLowerCase()) !== -1) {
			        alert("Click on a Trade/Maint. Section column");
			        return;
			    }

			    var machineId = jQuery("#assmGrid").jqGrid('getCell', rowid, "mchm_keyid");
			    if (!machineId || machineId.trim().length == 0) {
			        machineId = jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");
			    }
			    machineId = (machineId || "").trim();

			    var activitytype = jQuery('#hdnActType').val();

			    // Was: jQuery("#frmPmStandard input[id='flid']").val() - that field belongs to the
			    // single-entry PM Standard form and is stale/unrelated to the machine row just
			    // clicked (confirmed by the FNL000000001 bug). BAL_PLM_VW_FACTORYLAYOUT (checked)
			    // has no flid column, so there's no server-side view to derive the correct value
			    // from machineId either. Until a real per-machine flid source is identified, omit
			    // it rather than pass a wrong value that silently filters out every row.
			    // prvnt_mntncform_multiple_getData.prv / getMultiplePmsdList already treats a
			    // missing/blank flid as "no flid filter" (falls back to machine + trade).
			    LoadPopUp(
			        "prvnMntncStdDiv",
			        "prvnt_mntncform_multiple_input.prv?pmsdMachineID=" + encodeURIComponent(machineId)
			            + "&tradeId=" + encodeURIComponent(tradeId)
			            + "&activitytype=" + encodeURIComponent(activitytype || ""),
			        true, "90%", "82%", "1%", "1%", "",
			        "Maintenance Standards", "", true
			    );
			}
 */	
 
 /*  function pmAssembly_dblclick(rowid) {

	    console.log("=== pmAssembly_dblclick FIRED, rowid:", rowid, "===");

	    var iCol = jQuery('#getiCol').val();
	    var colm = jQuery("#assmGrid").jqGrid('getGridParam', 'colModel');
	    var tradeId = (iCol != null && colm[iCol]) ? colm[iCol].name : "";

	    console.log("[pmAssembly_dblclick] iCol:", iCol, "| tradeId (column name):", tradeId);
	    console.log("[pmAssembly_dblclick] full colModel names:", colm.map(function(c,i){ return i+":"+c.name; }));

	    var nonTradeColumns = ["mchm_machinename", "mchm_machinecode"];
	    var isBlocked = nonTradeColumns.indexOf((tradeId || "").toLowerCase()) !== -1;
	    console.log("[pmAssembly_dblclick] isBlocked (matched nonTradeColumns)?", isBlocked);

	    if (isBlocked) {
	        alert("Click on a Trade/Maint. Section column");
	        return;
	    }

	    var machineId = jQuery("#assmGrid").jqGrid('getCell', rowid, "mchm_keyid");
	    console.log("[pmAssembly_dblclick] machineId from grid cell:", machineId);
	    if (!machineId || machineId.trim().length == 0) {
	        machineId = jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");
	        console.log("[pmAssembly_dblclick] fallback machineId from combobox:", machineId);
	    }
	    machineId = (machineId || "").trim();

	    var activitytype = jQuery('#hdnActType').val();
	    console.log("[pmAssembly_dblclick] activitytype:", activitytype);

	    var popupUrl = "prvnt_mntncform_multiple_input.prv?pmsdMachineID=" + encodeURIComponent(machineId)
	        + "&tradeId=" + encodeURIComponent(tradeId)
	        + "&activitytype=" + encodeURIComponent(activitytype || "");
	    console.log("[pmAssembly_dblclick] LoadPopUp URL:", popupUrl);

	    LoadPopUp(
	        "prvnMntncStdDiv",
	        popupUrl,
	        true, "90%", "82%", "1%", "1%", "",
	        "Maintenance Standards", "", true
	    );

	    console.log("=== pmAssembly_dblclick END ===");
	}  */
	function pmAssembly_dblclick(rowid) {

	    console.log("=== pmAssembly_dblclick FIRED, rowid:", rowid, "===");

	    var iCol = jQuery('#getiCol').val();
	    var colm = jQuery("#assmGrid").jqGrid('getGridParam', 'colModel');
	    var tradeId = (iCol != null && colm[iCol]) ? colm[iCol].name : "";

	    console.log("[pmAssembly_dblclick] iCol:", iCol, "| tradeId (column name):", tradeId);
	    console.log("[pmAssembly_dblclick] full colModel names:", colm.map(function(c,i){ return i+":"+c.name; }));

	    var nonTradeColumns = ["mchm_machinename", "mchm_machinecode"];
	    var isBlocked = nonTradeColumns.indexOf((tradeId || "").toLowerCase()) !== -1;
	    console.log("[pmAssembly_dblclick] isBlocked (matched nonTradeColumns)?", isBlocked);

	    if (isBlocked) {
	        alert("Click on a Trade/Maint. Section column");
	        return;
	    }

	    var machineId = jQuery("#assmGrid").jqGrid('getCell', rowid, "mchm_keyid");
	    console.log("[pmAssembly_dblclick] machineId from grid cell:", machineId);
	    if (!machineId || machineId.trim().length == 0) {
	        machineId = jQuery("#frmPmStandard input[id='cmbPMMachineid']").combobox("getValue");
	        console.log("[pmAssembly_dblclick] fallback machineId from combobox:", machineId);
	    }
	    machineId = (machineId || "").trim();

	    // NEW: resolve activity type the same way btnView does — combobox first,
	    // fall back to the hidden field — so we catch the case where neither is set.
	    var activitytype = jQuery("#frmPmStandard input[id=cmbActivitytype]").combobox("getValue");
	    if (!activitytype || activitytype.trim().length <= 0) {
	        activitytype = jQuery('#hdnActType').val();
	    }
	    activitytype = (activitytype || "").trim();
	    console.log("[pmAssembly_dblclick] resolved activitytype:", activitytype);

	    // NEW: block navigation and alert when no Activity Type is selected
	    if (activitytype.length <= 0) {
	        alert("Select Activity Type");
	        return;
	    }

	    var popupUrl = "prvnt_mntncform_multiple_input.prv?pmsdMachineID=" + encodeURIComponent(machineId)
	        + "&tradeId=" + encodeURIComponent(tradeId)
	        + "&activitytype=" + encodeURIComponent(activitytype);
	    console.log("[pmAssembly_dblclick] LoadPopUp URL:", popupUrl);

	    LoadPopUp(
	        "prvnMntncStdDiv",
	        popupUrl,
	        true, "90%", "82%", "1%", "1%", "",
	        "Maintenance Standards", "", true
	    );

	    console.log("=== pmAssembly_dblclick END ===");
	}
 function loadComFunction(ids){
		hideJqGridRow("assmGrid","RN");
		hideJqGridRow("assmGrid","0");
		if(screen.width >= 1366){
			//jQuery( "#assmGrid" ).setGridWidth(908);
		}
		 var rowid = jQuery("#assmGrid").jqGrid('getDataIDs');
			// alert(rowid.length);
			/*to Remove Zero(0) from grid
			 var cm = jQuery("#assmGrid").jqGrid("getGridParam", "colModel");
			 for(var i=0;i<rowid.length;i++)
			 {
				 for(var j=3;j<cm.length;j++)
		     	 {
				  var zeroVal = jQuery("#assmGrid").jqGrid('getCell',rowid[i],cm[j].name);	
					 // alert("zeroVal"+		zeroVal); 
					if(zeroVal =='0')	{		
					  jQuery("#assmGrid").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px'});
			     	 }
			 }*/
			 
			 
			
		jQuery("#assmGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			jQuery('#getcellContent').val(cellcontent);
			jQuery('#getiCol').val(iCol);	
			
			   //alert("cellcontent"+cellcontent);
			if(cellcontent != 0 && cellcontent != '&nbsp;'){
				jQuery('#chkZero').val('val');
				//cell_click(rowid,iCol,cellcontent);
			}/*else if(cellcontent != '&nbsp;'){

				jQuery('#chkZero').val('zero');

			}*/
			else{
				jQuery('#chkZero').val('zero');
			  }
			
			}});
			 var tworow= jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('-'),jQuery('.ui-paging-info').html().indexOf('of'));
			 tworow =tworow.substring(1);
			 /*jQuery(".jqgrid-rownum").each(function(){
				if(tworow <=100)
					jQuery(this).html(   parseInt(jQuery(this).html())-2 );
					
				});*/
				var getPage = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('w'),jQuery('.ui-paging-info').html().indexOf('-'));
				getPage = getPage.substring(1);

				var totrow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('of'));
				totrow =totrow.substring(2);

				/*alert( jQuery('.ui-paging-info').html());
				alert(getPage);
				alert(tworow );
				alert(totrow );*/
				var lastRowCnt = jQuery('#assmGrid tr:last').find(' td:first-child').html();
				//if(lastRowCnt < 100)
					//jQuery('.ui-paging-info').html('View '+getPage +' - '+totrow+' of '+totrow);
				//else
					//jQuery('.ui-paging-info').html('View '+getPage +' - '+lastRowCnt+' of '+lastRowCnt);
	     }
			
	
	function Assm_dblclick(id,iCol,colVal){
		var colRowData= jQuery("#assmGrid").jqGrid ('getRowData','RN');
      	var colm = jQuery("#assmGrid").jqGrid ('getGridParam','colModel');
		var selId =colRowData[ colm[iCol].name];	
		var rowData = jQuery("#assmGrid").jqGrid('getRowData',"1");
		var tradeId = selId;
		
		jQuery('#hdnTradeId').val(tradeId );
     	var url = jQuery('#hiddenUrl').val();
		var fact = '';// jQuery('input:[name=cmbPmsdFactoryid]').val();
		var sect = '';//jQuery('input:[name=cmbPmsdSectionid]').val();
		var cell = '';//jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('#hiddenMachField').val();
		var url = jQuery('#hiddenUrl').val();
		var gUrl = url.substring(url.indexOf('preventive_input.prv'),url.indexOf('?q=2'));
		var grdUrl ="";
		 if(gUrl == 'MouldpmAssembly_input.prv'){//alert("if "+gUrl);
			 grdUrl = "MouldassmWeekly_input.prv?q=2";
		 }
		 else{//alert("else "+gUrl);
			 grdUrl = "assmWeekly_input.prv?q=2";
		 } 
		
		var activitytype=  jQuery('#hdnnewActType').val();
		//alert(activitytype);
		if( mach == undefined || mach == null || mach.trim().length <=0  )
			mach = jQuery('#cmbPMMachineid').combobox("getValue");
		var costcenterId = jQuery("#frmPmStandard input[id='cmbPmstdCostCenter']").combobox("getValue");
		var filterString = '&cmbFactid=';//+fact;
			filterString += '&cmbSectid=';//+sect; 
			filterString += '&cmbCellid=';//+cell; 
			filterString += '&cmbMchid='+mach;
		    filterString += "&cmbjobtype="+activitytype;
            filterString += "&chkd=tradeWise";
            filterString += "&cmbTradeid="+tradeId;
            filterString += '&cmbCostcenter='+costcenterId ;
          //alert(filterString);
          jsonstr = '{"cmbFactid":"'+fact+'" ,"cmbSectid":"'+sect+'","cmbCellid":"'+cell+'" ,"cmbMchid":"'+mach + '" ,"cmbTradeid":"'+tradeId+'" ,"cmbjobtype":"'+activitytype+'"}';
		  var persistdata =  jQuery.parseJSON(jsonstr);
		  jQuery("#Loadassemblygridfrm").html('');
		  //alert(" Going To 2222 ");
		  //openNewStandard(rowid,iCol,cellcontent,"assmGrid");
          navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=LoadassmbWeekgridfrm&preLoadContentDivId=preloadDIVid2&isHidePrevForm=false",'',null,persistdata);
          //processGridnew("assmWeekly_input.prv",filterString,"assmGrid","pager",""," ","","");
         
		  }
	function isLetter(s)
	{
	  return s.match("^[a-zA-Z\(\)]+$");    
	}
	jQuery('#btnTabSelect').click(function()
			{	
		var grid=jQuery('#gridName').val();
//"GenWOGrid","GenWOPager","","genWODBLClick"
				//var rowidactivity = jQuery("#activityGrid").jqGrid('getGridParam','selrow');
				if(grid=="assemblygrid"){
				var rowidasm = jQuery("#assmGrid").jqGrid('getGridParam','selrow');
				alert(rowidasm+ 'rowidactivity ');
				if(rowidasm =='' || rowidasm == null || rowidasm =='undefined'){
					alert("Select The Record To Open Activities"); 	
					}	
							
				else
					pmAssembly_dblclick(rowidasm);
				}
				else{}		
				
				
			 });

			
</script>
<form id="frmAssmGrid">
 <div class="easyui-paddingbfpx" style="margin-top:-5;">
     <table id="assmGrid" style="width:100%"><tr><td/></tr></table>
      <div id="pager_assm"> 
     </div>
 </div>
 <input type="hidden" id="fltrStr" value="${requestScope.filterString}"/>
 <input type="hidden" id="gridName" value="assemblygrid"/>
</form>