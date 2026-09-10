<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
		 {
		jQuery('#addInfo').css('display','none');
		jQuery('#firstGrid').css('display','block');
		jQuery('#secondGrid').css('display','none');
		jQuery('#thirdGrid').css('display','none');	
			//var hiddenmachineId =jQuery('#hien').val();
			var hiddenmachineId =jQuery('#hdnForviewMach').val();
			jQuery('#addMachine').css('display','none');
			//alert("hiddenmachineId  :"+hiddenmachineId);
			
			var flid = jQuery("#frmJhClitStd input[id='flid']").val();
			//var flid = jQuery("#hdnflid").val();			
	 		if(flid == "" ||flid == null){
	 			//alert("scxcxcx  :"+hiddenmachineId);
	 			//alert('s;');
	 			processGridnew("jhClit_input.baljhclit","?machineID=","clitCntGrid","pager","JH(CLIT) Standard","nxtgrid");
		 		
	 		}
	 		else{
	 			
	 		processGridnew("jhClit_input.baljhclit",'?q=2&machineID='+hiddenmachineId+"&flid="+flid,"clitCntGrid","pager","JH(CLIT) Standard","nxtgrid");
	 		}

	 		
	 		jQuery("#cmbClisMachineid").combobox({onRequest:function(opts){		
	 			var mchId = jQuery("#frmJhClitStd input[id='machine']").val();
	 			
	 			return "&combokey="+mchId+"&machId="+mchId; 		
	 			}
	 		});
	 		
		  });

function nxtgrid(id) 
		{
	    
	  	  	var rowData = jQuery("#clitCntGrid").jqGrid('getRowData',id);																								
			var selId = rowData.machineId;
			var flid = rowData.flid;
// 			var clitId =rowData.noofclit;
		
			
// 			var grid = jQuery('#clitCntGrid');
// 			var sel_id = grid.jqGrid('getGridParam', 'selrow');
// 			var eqpname = grid.jqGrid('getCell', sel_id, 'equipmentname');
// 			var eqpno = grid.jqGrid('getCell', sel_id, 'equipmentno');
			
// 			var id=selId;
// 			var text =eqpno+"-"+eqpname;
// 			var record = id+","+text;
			
// 			filcmbbox(selId);
			//frmJhClitStdcmbClisMachineid_onSelect(selId);
// 			jQuery('#hien').val(selId);
			
			var filterData = "?q=2";
			filterData += "&machineId="+selId;
			filterData += "&grid=clit";
			filterData += "&flid="+flid;
			//filterData += "&flid="+jQuery("#hdnflid").val();
			
			//var filterData = '?q=2&cmbAssmbid='+availId;
			//filterData +='&machAreaName='+escape(machAreaName);
			
			var url = jQuery('#hiddenUrl').val();
			var frmName = jQuery('#hdnFrmName').val();
			reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?combokey="+ selId );
			jQuery("#cmbClisMachineid").combobox('setValue',selId);
			//alert("selId.."+selId);
			jQuery("#machine").val(selId);
		    var keyId=jQuery("#cmbClisMachineid").combobox("getValue");
		    
		    loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+keyId );
		    
		  	//  jQuery('#Loadjhclitgrid2').append('<div id="jhclitgrid2" class="divbrdr" style="width:100%"></div>');
			//	navigateToNextForm("jhClit_mcharea.baljhclit?q=2&loadContentDivId=jhclitgrid1&preLoadContentDivId=preloadDIVid2&cmbMchid="+filterData+"&isHidePrevForm=false",frmName);
		   // navigateToNextForm("jhClit_mcharea.baljhclit?q=2&loadContentDivId=jhclitgrid1&preLoadContentDivId=preloadDIVid2&cmbMchid="+filterData+"&isHidePrevForm=false",frmName);
		    

		   navigateToNextForm("jhClit_grid3.baljhclit"+filterData+"&loadContentDivId=jhclitgrid1&preLoadContentDivId=preloadDIVid2&isHidePrevForm=false",frmName);
		
	}
		
		function dispErr(){
			//alert("disperror");
		}
		function jhclitcountgrid_errorCallBack(){
			alert("error");
		}
		
		function jhclitcountgridOnSuccess()
		{		
			var selId=jQuery('#hien').val();
		    jQuery("#cmbClisMachineid").combobox('setValue',selId);
		    var keyId=jQuery('#cmbClisMachineid').val();
		    
		}
		var selectedmchId=jQuery('#hien').val();
		
		if(selectedmchId != null && selectedmchId != ""){
			//loadFunctionalLocation("clisfunLocation","functionalLoc.baljhclit","clisfunLocationValues","frmJhClitStd","&machId="+selectedmchId);
	 	}
</script>
<form name="frmjhclitcountgrid" id="frmjhclitcountgrid" action="" method="post">

<div id="jhclitmachinearea" >
	<input type=hidden id="first" class="easyui-text" value="a"/>
	</div>
<table id="clitCntGrid" ></table>
<div id="pager"></div>

<div id="Loadjhclitgrid2">

</div>

<input type="hidden" id="fstGrd" value="1a"/>
<input type="hidden" id="hdnForviewMach" value="${requestScope.selctMachineId}"/>
<!--for setting data and sending it via filter string when clicked view-->
<input type="hidden" id="hdnflid" value="${requestScope.flid}"/>

</form>