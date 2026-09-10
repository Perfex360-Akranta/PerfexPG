  


<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
	
	{
	//alert(0);
	jQuery('#thridGrid').css('display','none');	
	jQuery("#vwebtn").css('display','none');
	
	jQuery('#firstGrid').css('display','none');
	jQuery('#secondGrid').css('display','block');
	jQuery('#thridGrid').css('display','none');
	var mode = jQuery('#modeHdn').val();
	 //alert(mode);
	if( mode == "view" ){
		jQuery('#addMachine').css('display','none');
		
	}	
	else
	{
		//alert("else");
		jQuery('#addMachine').css('display','block');
		}
	//for disabling factory section cell and machine
	var url = jQuery('#hiddenUrl').val();
	jQuery("#newstndard").css('display','none');
	jQuery('#addInfo').css('display','none');
	jQuery('#cmbClisFactoryid').combobox("disable");
	jQuery('#cmbClisSectionid').combobox("disable");
	jQuery('#cmbClisCellid').combobox("disable");
	jQuery('#cmbClisMachineid').combobox("disable");
	var bdmMachID =jQuery('#bdmHdn').val();
	var mchId =jQuery('#hien').val();
	//alert("hidden "+mchId);
	if(bdmMachID != "" && bdmMachID != null){				
		processGridnew("jhClit_input.baljhclit","?q=2&cmbMchid="+bdmMachID,"mchareagrid","mchareapager","","dblclick");
		//alert("if");
	}
	else{
		//alert('else');
		processGridnew("jhClit_input.baljhclit","?q=2&cmbMchid="+mchId,"mchareagrid","mchareapager","","dblclick");
	}


	
});

	

function dblclick(id)
{
	var rowData = jQuery("#mchareagrid").jqGrid('getRowData',id);																								
	var availId = rowData.keyId;
	//alert(availId);
	var availability = rowData.Availability;
	var mchId = rowData.machineId;
	//alert(mchId);
	jQuery('#hien').val(mchId);
	setFieldValue('cmbClisMachineid',mchId);
	var machAreaName = rowData.machineareaname;
	machAreaName= machAreaName.replace('&','AND');
	//alert("tick "+machAreaName);
	//var mchId=jQuery('#hien').val();
	//alert("Before Success"+jQuery("#cmbClisMachineid").combobox('getValue'));
	var filterData = '?q=2&cmbAssmbid='+availId;
		filterData +='&machAreaName='+escape(machAreaName);
		filterData += "&machineId="+mchId;
	    jQuery('#txtClisAssemblyid').val(availId);
	    var frmName = jQuery('#hdnFrmName').val();
	    
	// alert("2ndfrm"+filterData);
	   //jQuery('#Loadjhclitgrid3').append('<div id="jhclitgrid3" class="divbrdr" style="width:100%"></div>');
	   navigateToNextForm("jhClit_grid3.baljhclit"+filterData+"&loadContentDivId=jhclitgrid2&preLoadContentDivId=preloadDIVid2",frmName);
	  // LoadForm("jhclitcountgrid","preloadDIVid","jhClit_grid3.baljhclit"+filterData,"dispErr", "","jhclitcountgrid_errorCallBack");
	   function dispErr(){alert("disperror");}
		function jhclitcountgrid_errorCallBack(){alert("error");}
	    /*jQuery("#jhclitcountgrid").load("jhClit_grid3.baljhclit"+filterData, function(response, status, xhr)
	   		 {
	   			  if (status == "error") {
	   				    var msg = "Sorry but there was an error: ";
	   				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
	   				  }
	   			  else if(status == "success")
	   			  {
		   			 // alert('2Success');
		   			 var selectId = jQuery('#hien').val();

					   jQuery("#cmbClisMachineid").combobox('setValue',selectId);
	   				//alert("Success"+jQuery("#cmbClisMachineid").combobox('getValue'));
	   				  //jQuery("#Employee").show();
	   				    //alert("keyId"+keyId);
	   				 //jQuery("#JHCLIT").load("jhClit_grid3.baljhclit");
	   			  }
	   		 });*/
}
function actionFormatter(cellvalue, options, rowObject)
{
	if(cellvalue == 'Y')
	 {
   		return '<span style="font-size:15px;color:#8B0000;margin-left:250px;"> &#10003;</span>';
     }
	else  {
		return  " ";
	 }	
}
//button click for add machine
	
	function multiSelectSaveOk_Callback(result){
		alert("Saved SuccessFully");
		
		jQuery('#mchareagrid').trigger("reloadGrid");
		}
	
</script>
<form name="frmJhClitmcharea" id="frmJhClitmcharea" action="" method="post">
<!--<input type=text id="dfs" class="easyui-text" value=""/>-->

	<div style="" id="JHCLITmachinearea"> 
	<input type=hidden id="second" class="easyui-text" value="b"/>
	
<table id="mchareagrid" ></table>
<div id="mchareapager"></div>
	
</div>
<div id="Loadjhclitgrid3">

</div>
<input type="hidden" id=txtClisAssemblyid" name="txtClisAssemblyid"/>
<input type="hidden" id="bdmHdn" name="bdmHdn" value="${requestScope.machineID}"/>


</form>