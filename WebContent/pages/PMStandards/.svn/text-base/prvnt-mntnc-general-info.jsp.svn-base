

<script type="text/javascript">


jQuery(document).ready(function(){	
	/**functions to be called during load*/
	alert('sds');
	//combo_onSelect();//for combobox select
	initialiseForm("frmPmStandard");
	toolgrid();//for tool Grid show
	chk_select();//for tool check box 
	pm_spares();//for spares grid
	jQuery('#cmbPmsdSupplierid').combobox('disable');
	jQuery('#submitForm').val('frmPmStandard'); // set the id of form to submit
	//for getting values in combobox call the action
	fillComboBox("frmPmStandard","cmbPmsdTradeid","combo_pmsdTrade.prv");
	fillComboBox("frmPmStandard","cmbPmsdPreparedbyid","combo_pmsdpreparedby.prv");
	fillComboBox("frmPmStandard","cmbPmsdAssemblyid","Pmsd_assemblyId.prv");
	fillComboBox("frmPmStandard","cmbPmsdSubassemblyid","Pmsd_SubassemblyId.prv");
	fillComboBox("frmPmStandard","cmbPmsdJobtype","Pmsd_Jobtype.prv");
	jQuery( "#additional_info" ).click(function() 
	{	
	  jQuery("#additional_info_div").load('preventive_addinfo_input.prv', function(response, status, xhr) {
				  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				  }
				});
    	//}
	jQuery( "#additional_info_frm" ).dialog( {autoOpen: false,
			modal: true,
			height: 450,
			width: 900} );
	return false;
	});
	
	jQuery( "#rsrc_pln" ).click(function() 
	{	
		//if(document.getElementById('additional_info_div').innerHTML.trim().length == 0){        		
    		
    		jQuery("#rsrc_pln_div").load('prvnt-mntnc-rsrc_input.prv', function(response, status, xhr) {
				  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				  }
				});
    	//}
	jQuery( "#rsrc_pln_frm" ).dialog( {autoOpen: false,
			modal: true,
			height: 450,
			width: 900});
	return false;
	});	

	jQuery("#closebtn").click(function(){
		jQuery( "#howmethodgrid").dialog('close');
		var tableDatas = jQuery("#list").jqGrid('getRowData');
		var rowIds = "";
		jQuery( "#txtPmsdHowmethod").val(tableDatas[0].txtMlmmMethoddescription);
		if(tableDatas==null && tableDatas[0] == null)
			 alert("No Rows Available - ");
		
		
	});
	

//function for edit row
	jQuery("#edtbtn").click(function(){
		var row_id = jQuery("#list").jqGrid('getGridParam','selrow');
	
		 if( row_id != null ){ 
			 addRow("edit",row_id);
		 }
		 else{
			 alert("Select Row");
		 }
	});
//function for add row
	jQuery("#addmthd").click(function(id){
		var howMethod = jQuery('#txtPmsdHowmethod').val();
		  alert("adDDhowMethod  :"+howMethod);
	 addRow("add");
	 });
	 
//function for clear/delete row

	 jQuery('#clrbtn').click(function (){
		var rowid = jQuery("#list").jqGrid('getGridParam', 'selrow');
		alert("rowid="+rowid);
		jQuery("#list").delRowData(rowid);
		});


	

//function for check one select and deselect the other check box						
	jQuery('#chkPmsdSource').click(function(){
		jQuery('input:checkbox[name=chkPmsdSource1]').attr('checked',false);
		jQuery('input:checkbox[name=chkPmsdSource]').attr('checked',true);
		jQuery('#cmbPmsdSupplierid').combobox('disable');
		});
	jQuery('input:checkbox[name=chkPmsdSource1]').click(function(){		
		 jQuery('input:checkbox[name=chkPmsdSource]').attr('checked',false);
		 jQuery('input:checkbox[name=chkPmsdSource1]').attr('checked',true);
		 jQuery('#cmbPmsdSupplierid').combobox('enable');
	});
//function for combobox getvalue
jQuery('#filemanager').click(function(){
	var vale = jQuery('#cmbPmsdFrequencyunit').find('option:selected').text();
	alert("jjj- "+vale);
     var val=jQuery('#cmbPmsdFrequencyunit').combobox('getValue');
     alert("val   -"+ val);
	
});
jQuery('#subtypebtn').click(function(){
	multiSelectPop("subType_input.prv","","","","","true","","subTypePopOk_Callback");	
	
});
jQuery('#spares').click(function(){
	//var Ass
	//subFormPop("Sparepickup_input.sprpckup","480","1180","spares","");
	//subFormPop("PhenCause_input.pcl","Phenomena-Cause-Link","?q=2");
	/*jQuery( "#Spares_div" ).show();
	jQuery( "#Spares_div" ).dialog({
		autoOpen: false,
		modal: true,
		height: 380,
		width: 1180,	
		top:150	
	});	*/
	//panel-header panel-header-noborder window-header
	//navigateToNextForm("Sparepickup_input.sprpckup");
	
	//jQuery(".window-header").hide();
	//jQuery( ".window-shadow" ).hide(); 
	
	
});
jQuery('#close_spares').click(function(){
jQuery( "#Spares_div" ).dialog("close");

});
});
function  combo_onSelect(){
	
      jQuery("#cmbPmsdFrequencyunit").combobox({
 		onSelect:function(recordid){
 			if(recordid.value == 'Y'){
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Year(s)");
 			}
 			else if(recordid.value == 'M'){
				
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Months(s)");
 			} 
 			else{
 				jQuery('#txtPmsdFrequency').attr('disabled','disable');
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').removeAttr("class","mandatory-lbl");
				jQuery('#lblVal').html("frequency Unit");
				
 	 			}
 			}
     });
    
}
//function for subtype button


function subTypePopOk_Callback(rowIds){
	
	//var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds);
	//alert(rowObject.txtPmsdActsubtype);
	var subType = "";
	var subTypeKeyId = "";
	var seprator = ",";
	for(var i=0;i<rowIds.length;i++){
		var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds[i]);
		jQuery("#multiSelectGrid").getCell(rowIds[i],"my_checkbox","False");
		
		subType +=rowObject.txtPmsdActsub;
		subType	+=seprator;		
		subTypeKeyId+=rowObject.txtPmsdActsubtype;
		subTypeKeyId+=seprator;
		alert(subType+"----"+subTypeKeyId);
		
	}
	subType = subType.slice(0,-1);
	jQuery('#txtPmsdActsub').val(subType);
	subTypeKeyId =subTypeKeyId.slice(0,-1);
	jQuery('#txtPmsdActsubtype').val(subTypeKeyId);
  	//alert(row_Ids.txtPmsdActsubtype);
	
	
}
		

//functions for tools pickup
//function for tool  grid
function toolgrid(){
	//alert("tools");
	processGridnew("tool_input.prv","&q=0","tools","toolspager","","");	
}
//function tools pop up  
function toolpop(){
	
	//multiSelectPop("tool_pop.mselect","","","","","")
	var tableDatas = jQuery("#tools").jqGrid('getRowData');
	var rowIds = "";
	if(tableDatas!=null && tableDatas[0] != null)
		// alert("tableDatas -"+tableDatas[0].elementid);
	 for(var i=0;i<tableDatas.length;i++){
		 rowIds += tableDatas[i].txtPtldToolid+',';
	 }

	 var colNames = 'txtPtldToolid,displaycode';
	 ToolsTree('tool_tree.prv','?rowIds='+rowIds,'tools','1',colNames,'true');
}
//function for spares
function pm_spares(){
	
	processGridnew("spare_input.prv","","sparesGrid","pager1","","");	
}
//fumction for how method button click spare_input.prv
	function howmethod()
	{
		//alert("howmthd");
		var pmsdkeyID =	jQuery('#pmsdkeyhidn').val();
		processGridnew("howmthd_input.prv?pmsdkeyID="+pmsdkeyID,"&q=0","list","pager","","");
		
		
		//jQuery('#howmethodgrid').css('display','block');
		jQuery( "#howmethodgrid" ).show();
		jQuery( "#howmethodgrid" ).dialog({
			autoOpen: false,
			modal: true,
			height: 380,
			width: 640,	
			top:180	
		});	 
	//jQuery(".window-header").hide()  ; 
	  }
//for close grid
	
	 function howmethodRemove_onSuccess(result)
	 {
	 	
	 	if( result.tpmException != null )
	 	{
	 		showCommonErrorMsg(result.tpmException);
	 	}
	 	else
	 		jQuery("#list").delRowData(howmethodId);	
	 	
	 }
	 function howmethodRemove_onError(status)
	 {
	 	alert(Object.keys(status));
	 	alert(status.responseText);
	 	showCommonErrorMsg(status.error);
	 }
//FUNCTION FOR ADD ROW	 
	function addRow(para,id){
		
	  var val=null;
	  var txtHowMethod ;	
	  
	  var prvkeyID =	jQuery('#stdhidn').val();
		 if(para=="edit")
			val=id;
		 else if(para=="add")
			val="new";
		jQuery("#list").jqGrid('editGridRow',val,{
			height:150,
			reloadAfterSubmit:true,
			top : 70,
			left:20,
			beforeSubmit:function()
			{
			  var  msg ;	
			  var val;
			  
			   txtHowMethod = jQuery("#txtMlmmMethoddescription").val();	
			  
	   		   if((txtHowMethod==null||txtHowMethod==""))
				 msg = [false,"Enter Method"];
	 		   else
	 			 msg = [true,null];
	 	//jQuery("#list").jqGrid('addRowData',0,[{"hmkeyid":"0","txthowmethod":txtHowMethod,"txtduration":txtDuration }]);
		 	   return  msg; 
			}
		
//	afterSubmit:
	});
//getting rowCount
	//alert(id);
	 var rowCount = jQuery("#list").getGridParam("reccount");
	 var mlmmKeyid = jQuery("#list").jqGrid('getRowData',id);
	 //alert("mlmmKeyid -"+mlmmKeyid.txtmethodKeyid);
	 //alert(Object.keys(mlmmKeyid));
	 var tmpMlmKeyid =( mlmmKeyid.txtmethodKeyid ? mlmmKeyid.txtmethodKeyid:null);//alert("tmpMlmKeyid  -"+tmpMlmKeyid);

		 jQuery("#list").jqGrid().setGridParam({editurl :'howmthd_input.prv?prvkeyID='+prvkeyID +'&txtMlmmKeyid='+tmpMlmKeyid},
		 function(response, status,result, xhr)
		  {
			   if (status == "error") 
			  {
			       var msg = "Sorry but there was an error: ";
			       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		      }
			   else 
				 	alert(Object.keys(result));
			   		alert("response="+response);
		   			alert("response="+responseText);
		});	
	
	 
}
//function for spares div show spares

	//function for checkbox selection
	//jQuery(":checkbox").click(chk_select);
	 function chk_select()
		  {
		 jQuery("#tooltree").removeAttr('class', 'easyui-button');
	     jQuery("#tooltree").attr('disabled','disabled');
	    // var toolreq = jQuery('#chkpmsdIstoolsreqchecked').val();		
			  jQuery("#chkPmsdIstoolsreq").click(function(){
				  
				  
					if (jQuery("#chkPmsdIstoolsreq").is(":checked"))
					{
						//show the hidden div 
						
						jQuery("#whttools").css('color','red');
						jQuery("#tooltree").attr('class', 'easyui-button');
						jQuery("#tooltree").removeAttr('disabled');
					}
					else{
						jQuery("#whttools").css('color','#000');
						 jQuery("#tooltree").removeAttr('class', 'easyui-button');
					     jQuery("#tooltree").attr('disabled','disabled');
						}
				
				  });
			  //for Spares
			     jQuery("#spares").removeAttr('class', 'easyui-button');
			     jQuery("#spares").attr('disabled','disabled');
			     jQuery("#chkPmsdIssparesreq").click(function(){
					  
					  
						if (jQuery("#chkPmsdIssparesreq").is(":checked"))
						{
							//show the hidden div 
							
							jQuery("#whtspares").attr('class','mandatory-lbl');
							navigateToNextForm("Sparepickup_input.sprpckup");
							//jQuery("#spares").attr('class', 'easyui-button');
							//jQuery("#spares").removeAttr('disabled');
						}
						else{
							jQuery("#whtspares").removeAttr('class', 'mandatory-lbl');
							navigateToNextForm("prvnt_mntnc_ginfo_input.prv");
							 //jQuery("#spares").removeAttr('class', 'mandatory-lbl');
						    // jQuery("#spares").attr('disabled','disabled');
							}
					
					  });
			
			}

		//for save details
		function frmPmStandard_beforeSubmit(){
			//if(jQuery("#chkClisIstoolsreq").is(":checked")&&)
			var tableDatas = jQuery("#tools").jqGrid('getRowData');
			
			if(jQuery("#chkPmsdIstoolsreq").is(":checked")&&tableDatas.length==0)
				{
					alert("Select TOOLS");
					return false;
				}
				
			
			var gridData  = '&multiplemethods='+convertJqGridToJSONObjectArr('list');
				gridData +='&toolsGrid='+convertJqGridToJSONObjectArr('tools');
				//alert(gridData);
				return gridData; 
			
		}
</script>	
<style>
.ui-dialog-titlebar { display: none; } 
</style>				
<div id="">
<div class="sub-header" style="text-align: left;width:80%;margin-left:5.5%;"><span>General Information</span>
    <span style="float: right;">
    <input type="button" value="Additional Information" id="additional_info" class="easyui-button" style="height:21px;">
    <input type="button" value="File Manager"  id="filemanager" class="easyui-button" style="height:21px;" disabled="disable">
    <input type="button" value="Resource Planning" id="rsrc_pln" class="easyui-button" style="height:21px;">
    <input type="button" value="Back" onclick="source_div_actws_click();" class="easyui-button" style="height:21px;">
    </span>
    </div>
<table class="tablealign-center" width="85%">
  <tr>
  <td width="50%" valign="top">
  <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Assembly</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdAssemblyid" name="cmbPmsdAssemblyid" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlStandards.pmsdAssemblyid}"  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Sub Assembly1</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdSubassemblyid" name="cmbPmsdSubassemblyid" class="easyui-combobox"  style="width:330px;"  >                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Source</label>                    
                </div> 
                <div class="easyui-paddingbfpx " style="border-style:solid;border-width:thin;width:317px;padding:5px 5px 5px 5px;"> 
                    <input type="checkbox" id="chkPmsdSource" name="chkPmsdSource" value="I"><label>Internal(Activity Done in House)</label><br/>
                    <input type="checkbox" id="chkPmsdSource" name="chkPmsdSource1"><label>External(Activity Done with the help of supplier)</label>
                </div>
                <!--<div class="easyui-paddingbfpx" style="padding-top:10px;"> 
                    <input id="cmbPmsd" name="cmbPmsd" class="easyui-combobox"  style="width:333px;" value=""  >                    
                </div> -->
                <div  class="easyui-paddingbfpx">
                    <label>Supplier</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdSupplierid" name="cmbPmsdSupplierid" class="easyui-combobox"  style="width:330px;"   >                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Maint Section</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdTradeid" name="cmbPmsdTradeid" class="easyui-combobox"  style="width:330px;"   >                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Job Type</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdJobtype" name="cmbPmsdJobtype" class="easyui-combobox"  style="width:330px;"    >                    
                </div>                
                <div  class="easyui-paddingbfpx">
                    <label>Sub Type</label>
                    <span  style="margin-left: 130px;font-size:12px;" class="mandatory-lbl">Mcs Condition</span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="txtPmsdActsubtype" name="txtPmsdActsubtype"  style="width:140px;" value="" type="text" class="easyui-text"  >
<!--                    <input id="txtPmsdActsubtype" name="txtPmsdActsubtype"  style="width:140px;" value="" type="hidden" class="easyui-text"  >-->
                    <input type="button" value="..." class="easyui-button" id="subtypebtn">
	                <select id="cmbPmsdMachinecond" name="cmbPmsdMachinecond" class="easyui-combobox" style="width:142px;" required="true"  >
					<option value=""> </option>
					<option value="B"> BOTH</option>
					<option value="S"> SHUTDOWN</option>
					<option value="R"> RUNNING</option>
					</select>
	            </div>                
                <div  class="easyui-paddingbfpx ">
                    <label>What (Freq)</label>
                    <span  style="margin-left: 105px;" >
                    <label id="lblVal" >Frequency Unit</label>
                   
                    </span>
                </div> 
                <div class="easyui-paddingbfpx">                     
<!--                   <input id="txtPmsdFrequencyunit" name="txtPmsdFrequencyunit" class="easyui-combobox"  style="width:172px;" value=""  >-->
                <select id="cmbPmsdFrequencyunit" name="cmbPmsdFrequencyunit" class="easyui-combobox" style="width:172px;" required="true"  >
				<option value=""> </option>
				<option value="W"> WEEKLY</option>
				<option value="F"> FORTNIGHTLY</option>
				<option value="M"> MONTHLY</option>
				<option value="Q"> QUARTELY</option>
				<option value="H"> HALF YEARLY</option>
				<option value="Y"> YEARLY</option>
			</select>
                    <input id="txtPmsdFrequency" name="txtPmsdFrequency" type="text" style="width:140px;margin-left:10px" value=""  class="easyui-text" >                    
                </div>                
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>How Much</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <input id="txtPmsdHowmuchduration" name="txtPmsdHowmuchduration" type="text" class="easyui-text" style="width:120px;" value="30" >Minutes
                </div>
                
                <div  class="easyui-paddingbfpx">
                    <label>Where(Location)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPmsdWherelocation" name="txtPmsdWherelocation"></textarea>
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>What(Activity)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPmsdWhatactivity" name="txtPmsdWhatactivity"></textarea>
                </div>
                  
  </td>
  <td></td>
  <td width="50%" valign="top">
  
  <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>How(Method)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtpmsdHowmethod" name="txtpmsdHowmethod"></textarea>
                    <input type="button" class ="easyui-button" value="..." id="howmtd" style="vertical-align:top;" onclick='howmethod();'/>
                </div> 
<div  class="easyui-paddingbfpx">
                    <label>What(Standard)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPmsdWhatstandard" name="txtPmsdWhatstandard"></textarea>
                </div>
                                
                <div  class="easyui-paddingbfpx">
                <div class="sub-header" style=" margin-bottom: 10px;">Spares Information</div>
                    <label id="whtspares">What Spares</label>       
                    <input type="checkbox" id="chkPmsdIssparesreq" name="chkPmsdIssparesreq">             
                </div> 
              
                <div class="easyui-paddingbfpx" style="float: left;">
                <table id="sparesGrid" style="width:100%"><tr><td/></tr></table>
                <div id="pager1">
                </div>
                </div>
                <div class="clearfix"></div>
                <div  class="easyui-paddingbfpx">
                <div class="sub-header" style=" margin-bottom: 10px;">Tools Information</div>
                    <label id="whttools">What(Tools)</label>       
                    <input type="checkbox" id="chkPmsdIstoolsreq" name="chkPmsdIstoolsreq" value="Y">             
                </div> 
                <div style="float:right;vertical-align:top;margin-top:20px;margin-right:160px">
          <input type="button"  value="..." id="tooltree" style="vertical-align:top;background-color:lightgray; width : 41px;" onclick='toolpop();' />
           </div>
                <div class="easyui-paddingbfpx floatleft" >                
                    <table id="tools" style="width:100%"></table>
                <div id="pager">
                </div>
                </div>
                <div class="clearfix" ></div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Prepared By</label>       
                                 
                </div> 
                <div class="easyui-paddingbfpx">                
                    <input id="cmbPmsdPreparedbyid" name="cmbPmsdPreparedbyid" class="easyui-combobox"  style="width:330px;" value=""  >
                </div>
                
</td>
  </tr>
</table>      
</div>
<!--how Method-->
<form id="frmHowMethod" name="frmHowMethod"  action="" method="post">
<div id="howmethodgrid" style="display:none;" title="How Method">

<table id="list" ></table>
<div id="pager"></div>

<input type="button" class ="easyui-button" value="ADD METHOD" id="addmthd" style="width:200px;width:100px\9;"/>
<input type="button" class ="easyui-button" value="EDIT" id="edtbtn" style="margin-left:50px;"/>
<input type="button" class ="easyui-button" value="CLEAR" id="clrbtn" style="margin-left:50px;" />
<input type="button" class ="easyui-button" value="CLOSE" id="closebtn" />

</div>
</form>
<!--end-->
<!--Spares Div-->
<div id="Spares_div" style="background-image: url('images/tools/-spares13.jpg');">
<span id="close_spares" style="cursor:pointer;border-style:solid;background-color:LightGrey;border-color:#000;border-width:thin;float:right;">

</span>
</div>
<!--  Open popup window -->
<div id="additional_info_frm" title="Additional Information" >
<div id="additional_info_div" ></div>
</div>

<div id="rsrc_pln_frm"  title="Resource Plan">
<div id="rsrc_pln_div"> </div>
</div>
