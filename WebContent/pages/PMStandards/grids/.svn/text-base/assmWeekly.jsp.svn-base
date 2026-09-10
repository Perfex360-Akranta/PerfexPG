<script type="text/javascript">
	jQuery(document).ready(function(){
		//setLoadFormCallBackFrmId('frmPmStandard');
		initialiseForm('frmWeekWiseGrid');
		jQuery('#submitForm').val('frmWeekWiseGrid');
		
		
		jQuery("#top_div").css('display',"block");	
		jQuery(".main-cntborder ").css("height","92%");	
		jQuery('#backbutn').show();
		jQuery('#btnChkDiv').css('display','block');		
		jQuery('#lblHeader').html('Assembly Week Wise');
		//jQuery('#chkboxesAA').attr('colspan','1');
		//jQuery('#btnChkDiv').css("margin-top","0%");
		jQuery('#tradeName').css('display','none');
		var url = jQuery('#hiddenUrl').val();
		var gUrl = url.substring(url.indexOf('MouldassmWeekly_input.prv'),url.indexOf('?q=2'));//alert(url +"tt  "+gUrl);
		var grdUrl ="";
		 if(gUrl == 'MouldassmWeekly_input.prv')
			 grdUrl = "MouldassmWeekly_input.prv?q=2";
		 else
			 grdUrl = "assmWeekly_input.prv?q=2";
		var fltrStr = jQuery('#fltrStr').val();
		
		if (fltrStr != ' ' && fltrStr!= '' && fltrStr != null)
			fltrStr  = fltrStr ;
		else
			fltrStr  = '';
		
		processGridnew(grdUrl,fltrStr,"weekWise_Grid","pager_weekWise","","assmWeekly_dblclick","","loadComplete");
		
		jQuery('#imgPendingWrkordr').css('display','none');
		 jQuery('#btnNewstd').css('display','none');
		 //jQuery('#headin').css('display','none');
		 //jQuery('#lblHeader').html("PM Standard ");	
		 jQuery('.legnd').css('visibility','hidden');

		 var actType =  jQuery('#hdnnewActType').val();
			
		// setFieldValue('cmbActivitytype',actType,'frmPmStandard');
		// disableField('frmPmStandard','cmbActivitytype');
		 jQuery("#btnfrmPmStandardmainFunLoc").click(function() {disablControl('disable');
		 });
		 jQuery('#dispFunctionalLoc a').click(function() { disablControl('disable');});
	});
	
	function assmWeekly_dblclick(rowid,iCol,cellcontent){
		var chk_zero = jQuery('#chkZero').val();
		//var assmId = rowData.Keyid;
		
		if(chk_zero != 'zero'){
			cell_click(rowid,iCol,cellcontent);
		}
		else
			openNewStandard(rowid,iCol,cellcontent);

		jQuery('#chkZero').val(' ');
	}
	function loadComplete(ids){
		var rowid = jQuery("#weekWise_Grid").jqGrid('getDataIDs');
		 var cm = jQuery("#weekWise_Grid").jqGrid("getGridParam", "colModel");
		 if(screen.width >= 1366){
			//jQuery( "#weekWise_Grid" ).setGridWidth(908);
		}
		 for(var i=0;i<rowid.length;i++)
		 {
			 for(var j=3;j<cm.length;j++)
	     	 {
			  var zeroVal = jQuery("#weekWise_Grid").jqGrid('getCell',rowid[i],cm[j].name);	
				//alert("zeroVal"+		zeroVal); 
				if(zeroVal !=' ' && zeroVal !='' && zeroVal != null)	{		
					jQuery("#weekWise_Grid").jqGrid('setCell',rowid[i],cm[j].name,"&#10003;",{'color':'blue','font-weight':'bold','font-size':'18px'});
					  
		     	 }
				else{
					jQuery("#weekWise_Grid").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px'});
				 }
		 	  }
		 }
	
	jQuery("#weekWise_Grid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
		if(cellcontent != 0){
			jQuery('#chkZero').val('val');
			//cell_click(rowid,iCol,cellcontent);
		}
		else{
			jQuery('#chkZero').val('zero');
			//openNewStandard(rowid,iCol,cellcontent);
		}
		}});
	//disableField('frmPmStandard','cmbActivitytype');
	
	
	}
	function cell_click(rowid,iCol,cellcontent){
		var rowData = jQuery("#weekWise_Grid").jqGrid('getRowData',rowid);																								
		var assmId = rowData.Keyid;
		
		//alert(assmId);
		var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
		var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
		var cell = jQuery('input:[name=cmbPmsdCellid]').val();
		var mach = jQuery('input:[name=cmbMachineid]').val();
		if( mach == undefined || mach == null || mach.trim().length <=0  )
			mach = jQuery('#cmbPMMachineid').combobox("getValue");
		
        var activitytype =  jQuery('#hdnnewActType').val();
		var filterString = '&cmbFactid='+fact;
		filterString += '&cmbSectid='+sect; 
		filterString += '&cmbCellid='+cell; 
		filterString += '&cmbMchid='+mach;
		filterString += "&cmbjobtype="+activitytype;

   	   //formtemp
		//filterString += '&cmbAssmbid='+assmId;
		filterString += '&cmbAssmbid='+assmId;
	 	var start = (jQuery("#fltrStr").val()).indexOf('&cmbTradeid');
	 	var end   = (jQuery("#fltrStr").val()).indexOf("&cmbjobtype");
	 	var tradeId = (jQuery("#fltrStr").val()).substring(start,end); 
		filterString += tradeId;
	
		var url = jQuery('#hiddenUrl').val();
		var gUrl = url.substring(url.indexOf('MouldassmWeekly_input.prv'),url.indexOf('?q=2'));//alert(url +"tt  "+gUrl);
		var grdUrl ="";
		 if(gUrl == 'MouldassmWeekly_input.prv')
			 grdUrl = "MouldpmActivity_input.prv?q=2&filterString=";
		 else
			 grdUrl = "pmActivity_input.prv?q=2&filterString=";
		// alert(grdUrl+"&filterString="+filterString);
		navigateToNextForm(grdUrl+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm&preLoadContentDivId=preloadDIVid3&isHidePrevForm=true");
	}
</script>
<form id="frmWeekWiseGrid">
 <div class="easyui-paddingbfpx">
     <table id="weekWise_Grid" style="width:100%"><tr><td/></tr></table>
     <div id="pager_weekWise">
     </div>
 </div>
 <input type="hidden" id="fltrStr" value="${requestScope.filterString_assmweek}"/>

 
</form>