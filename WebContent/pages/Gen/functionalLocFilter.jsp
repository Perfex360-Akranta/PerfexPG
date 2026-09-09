

<html>
<head>

<%@ page language="java" contentType="text/html;"   pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function(){
	//--?compId=&locnId=&factId=&sbuId=&pbuId=&sectId=&cellId=
  
	initialiseForm('frmFunctLocHierarch');	
	var isHierarchyLoading = false;
	var compId = getFieldValue('cmbFunctLocComp');
	var locnId = getFieldValue('cmbFunctLocLocn');
	var factId = "";//getFieldValue('cmbFunctLocFact');
	var sbu = getFieldValue('cmbFunctLocSBU');
	//alert(sbu);
	var pbu = getFieldValue('cmbFunctLocPBU');
	var sectId = getFieldValue('cmbFunctLocSect');
	var cellId = jQuery("#cmbFunctLocCell").combobox('getValue');
	
	fillComboBox("frmFunctLocHierarch","cmbFunctLocComp","companyCombo.commonFilter" );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocLocn","location.commonFilter?compId="+compId );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter?compId="+compId+"&locnId="+locnId );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter?compId="+compId+"&locnId="+locnId+"&sbuId="+sbu  );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?compId="+compId+"&locnId="+locnId+"&sbuId="+sbu+"&pbuId="+pbu );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?compId="+compId+"&locnId="+locnId+"&sbuId="+sbu+"&pbuId="+pbu+"&sectId="+sectId );
	fillComboBox("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?compId="+compId+"&locnId="+locnId+"&sbuId="+sbu+"&pbuId="+pbu+"&sectId="+sectId+"&cellId="+cellId);

	
	/* jQuery('.easyui-combobox').combobox({
	    autoSelect: false
	}); */ 
	
	jQuery('#btnFuntLocOk').click(function(){		
		processAjaxCalls("validateFuntLoc.selectFuntLoc", jQuery('#frmFunctLocHierarch').serialize(), "frmFunctLocHierarch_onSuccessCallback", "frmFunctLocHierarch_onerrorCallBack");		
		
	});
	
	 jQuery("#btnFuntLocClear").click(function(){
	        if(  !jQuery('#cmbFunctLocComp').combobox('options').readonly ){
	            jQuery("#cmbFunctLocComp").combobox('clear');
	        }
	        var compId = getFieldValue('cmbFunctLocComp');
	        var locnId = getFieldValue('cmbFunctLocLocn');
	        var factId = "";//getFieldValue('cmbFunctLocFact');
	        var sbu = getFieldValue('cmbFunctLocSBU');
	        var pbu = getFieldValue('cmbFunctLocPBU');
	        var sectId = getFieldValue('cmbFunctLocSect');
	        var cellId = jQuery("#cmbFunctLocCell").combobox('getValue');
	        var teamId = "";//jQuery('#cmbFunctLocTeam').combobox('getValue');   

	        if( ! jQuery('#cmbFunctLocLocn').combobox('options').readonly ){
	            jQuery("#cmbFunctLocLocn").combobox('clear');
	            locnId ="";
	        //     reloadCombo("frmFunctLocHierarch","cmbFunctLocLocn","location.commonFilter");
	            jQuery("#clearLocn").val('true');           
	           
	        }
	        if(!jQuery('#cmbFunctLocSBU').combobox('options').readonly ){
	            jQuery("#cmbFunctLocSBU").combobox('clear');
	            sbu="";
	            reloadCombo("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId );
	            jQuery("#clearSbu").val('true');   
	        }
	       
	        if(!jQuery('#cmbFunctLocPBU').combobox('options').readonly ){
	            jQuery("#cmbFunctLocPBU").combobox('clear');
	            pbu="";
	            reloadCombo("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbu );           
	            jQuery("#clearPbu").val('true');   
	        }
	       
	        if(!jQuery('#cmbFunctLocSect').combobox('options').readonly ){
	            jQuery("#cmbFunctLocSect").combobox('clear');
	            sectId="";
	            reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbu+"&pbuId="+pbu );           
	            jQuery("#clearSect").val('true');   
	        }
	        if(!jQuery('#cmbFunctLocCell').combobox('options').readonly ){
	            jQuery("#cmbFunctLocCell").combobox('clear');
	            cellId="";
	            reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbu+"&pbuId="+pbu+"&sectId="+sectId );
	            jQuery("#clearCell").val('true');   
	        }
	        if(!jQuery('#cmbFunctLocMachine').combobox('options').readonly ){
	            jQuery("#cmbFunctLocMachine").combobox('clear');
	            reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbu+"&pbuId="+pbu+"&sectId="+sectId+"&cellId="+cellId+"&teamId="+teamId);           
	           
	            jQuery("#clearMach").val('true');   
	        }
	        jQuery("#hdnFunctLocFL").val('');
	    });
	});


jQuery("#cmbFunctLocComp").combobox({onRequest:function(opts){
	//    var compId = getFieldValue('cmbFunctLocComp');			
		return "";//"&combokey="+compId;
	}
});
jQuery("#cmbFunctLocLocn").combobox({onRequest:function(opts){
	 var compId = getFieldValue('cmbFunctLocComp');
	//  var locnId = getFieldValue('cmbFunctLocLocn');
	 return "compId="+compId;//+"&combokey="+locnId;		
	}
});
jQuery("#cmbFunctLocSBU").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	//  var sbuId = getFieldValue('cmbFunctLocSBU');
	 return "compId="+compId+"&locnId="+locnId;//+"&combokey="+sbuId;
	}
});
jQuery("#cmbFunctLocPBU").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	  var sbuId = getFieldValue('cmbFunctLocSBU');
	//  var pbuId = getFieldValue('cmbFunctLocPBU');
	//alert(sbuId);	
	  return "compId="+compId+"&locnId="+locnId+"&sbuId="+sbuId;//+"&combokey="+pbuId;
	
	}
});
jQuery("#cmbFunctLocSect").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	  var sbuId = getFieldValue('cmbFunctLocSBU');
	  var pbuId = getFieldValue('cmbFunctLocPBU');
//	  var sectId = getFieldValue('cmbFunctLocSect');
	  return "compId="+compId+"&locnId="+locnId+"&sbuId="+sbuId+"&pbuId="+pbuId;//+"&combokey="+sectId;		
	}
});

jQuery("#cmbFunctLocCell").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	  var sbuId = getFieldValue('cmbFunctLocSBU');
	  var pbuId = getFieldValue('cmbFunctLocPBU');
	  var sectId = getFieldValue('cmbFunctLocSect');
	  
//	  var cellId = getFieldValue('cmbFunctLocCell');
	  return "compId="+compId+"&locnId="+locnId+"&sbuId="+sbuId+"&pbuId=" + pbuId + "&sectId="+sectId;//+"&combokey="+cellId;	
	}
});
jQuery("#cmbFunctLocMachine").combobox({onRequest:function(opts){
	  var compId = getFieldValue('cmbFunctLocComp');
	  var locnId = getFieldValue('cmbFunctLocLocn');
	  var sbuId = getFieldValue('cmbFunctLocSBU');
	  var pbuId = getFieldValue('cmbFunctLocPBU');
	  var sectId = getFieldValue('cmbFunctLocSect');
	  var cellId = getFieldValue('cmbFunctLocCell');
	 // var machineId = getFieldValue('cmbFunctLocMachine');
	  return "compId="+compId+"&locnId="+locnId+"&sbuId="+sbuId+"&pbuId="+pbuId+"&sectId="+sectId+"&cellId="+cellId;//+"&combokey="+machineId ;
	}
});
	


function frmFunctLocHierarch_onSuccessCallback(result){

	jQuery('#frmFunctLocHierarch .tpm-error').removeClass("tpm-error");
	jQuery('#frmFunctLocHierarch div[id^="err_"]').css("display","none");
	jQuery('#frmFunctLocHierarch span[id^="err_"]').css("display","none");
	
	if( result.exception){
		var validMsgs = result.messages;
		
		for(var i = 0; i < validMsgs.length;i++)
		{	
			if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
			{
				if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") ){
					jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+ validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
				}	
				else	
					jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
			}	
			jQuery('#err_'+validMsgs[i][0] ).css("display","block");
			jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
		}
	}	
	else if( result.success == "true" ){
			var divId = result.data.divId;
			var formId = result.data.formId;
			var functString = result.data.functLocDisplay;
			
		/*	var functString ="<a id='lin"+formId +"Company' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocComp") + " </b></u> </a> / &nbsp&nbsp ";
			functString +=  "<a id='lin"+formId +"Location' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocLocn") + "  </b></u></a> / &nbsp&nbsp " ;
			functString +=  "<a id='lin"+formId +"Factory' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocFact") +" </b></u></a> / &nbsp&nbsp ";
			functString +=  "<a id='lin"+ formId +"Section' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocSect") + " </b></u></a> &nbsp&nbsp ";
			functString +=  "<a id='lin"+ formId +"Cell' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocCell") + " </b></u></a> &nbsp&nbsp ";
			functString +=  "<a id='lin"+formId+"Machine' style='cursor: pointer;'><u><b> " + getComboBoxText("cmbFunctLocMachine") + " </b></u></a> &nbsp&nbsp ";
		*/	 
			jQuery("#"+divId).html("");
			jQuery("#"+divId).html(functString);
			//var controls = result.data.controlIds;
			//var value= null; 
			//alert(result.data.controlIds);
			var filterStr ="";
			var keyIds = result.data.functLocHierarchIds;
			 
			if( jQuery("#"+ formId +'FuntKeyIds').length <= 0)
				jQuery("#"+divId ).append('<div id="'+formId +'FuntKeyIds"></div>');
			
			if( jQuery('#'+ formId + ' input[id="company"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='company' name= 'hdncompany' > ");

			
			jQuery('#'+ formId + ' input[id="company"]').val(keyIds.compId);

			
			if( jQuery('#'+ formId + ' input[id="location"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='location' name= 'hdnlocation'/>");

			jQuery('#'+ formId + ' input[id="location"]').val(keyIds.locnId);

			//fact// if( jQuery('#'+ formId + ' input[id="factory"]').length <= 0 )
				//fact// jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='factory' name= 'hdnfactory' />");

			//fact// jQuery('#'+ formId + ' input[id="factory"]').val(keyIds.factId);

			if( jQuery('#'+ formId + ' input[id="sbu"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='sbu' name='hdnsbu' />");

			jQuery('#'+ formId + ' input[id="sbu"]').val(keyIds.sbuId);

			if( jQuery('#'+ formId + ' input[id="pbu"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='pbu' name= 'hdnpbu' />");

			jQuery('#'+ formId + ' input[id="pbu"]').val(keyIds.pbuId);

			if( jQuery('#'+ formId + ' input[id="section"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='section' name= 'hdnsection' />");

			jQuery('#'+ formId + ' input[id="section"]').val(keyIds.sectId);

			if( jQuery('#'+ formId + ' input[id="cell"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='cell' name= 'hdncell' />");

			jQuery('#'+ formId + ' input[id="cell"]').val(keyIds.cellId);

			if( jQuery('#'+ formId + ' input[id="team"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='team' name= 'hdnteam' />");
			
			jQuery('#'+ formId + ' input[id="team"]').val(keyIds.teamId);

			if( jQuery('#'+ formId + ' input[id="machine"]').length <= 0 )
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='machine' name= 'hdnmachine' />");

			jQuery('#'+ formId + ' input[id="machine"]').val(keyIds.machId);
			
			if( jQuery('#'+ formId + ' input[id="flid"]').length <= 0 ){
					
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='flid' name= 'hdnflid' />");
			}				

			
			jQuery('#'+ formId + ' input[id="flid"]').val(keyIds.flid);

			if( jQuery('#'+ formId + ' input[id="elementId"]').length <= 0 ){
				
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='elementId' name= 'hdnelementId' />");
			}

			jQuery('#'+ formId + ' input[id="elementId"]').val(keyIds.elementId);
			if( jQuery('#'+ formId + ' input[id="elementType"]').length <= 0 ){
				
				jQuery("#"+ formId +'FuntKeyIds').append("<input type='hidden' id='elementType' name= 'hdnelementType' />");
			}
			jQuery('#'+ formId + ' input[id="elementType"]').val(keyIds.type);

		//	alert(result.data.controlIds.machId);
			//var data
			if( result.data.controlIds.machId != undefined && keyIds.machId != undefined && keyIds.machId.length > 0){
				setFieldValue(result.data.controlIds.machId,keyIds.machId,formId);
			}
			else if( result.data.controlIds.machId != undefined && jQuery("#"+result.data.controlIds.machId).length > 0 && jQuery("#"+ formId + ' input[id='+result.data.controlIds.machId+"]").hasClass("easyui-combo") ){
				filterStr += "compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sbuId="+keyIds.sbuId+"&pbuId="+keyIds.pbuId+"&sectId="+keyIds.sectId+"&cellId="+keyIds.cellId;
				//if(filterStr!='')
				reloadCombo(formId,result.data.controlIds.machId,'machineCombo.commonFilter?'+filterStr);
			}
				
			/*for( var i = 0;i < controls.length;i++){
				value = jQuery('#frmFunctLocHierarch input[name="' + controls[i] + '"]').val();
				if( jQuery('#'+ formId + ' input[name='+controls[i] +']').length <=0  )
					jQuery("#"+formId +' ' +divId).append('<input type="hidden" id="'+ controls[i] +'" name="'+ controls[i] +'"  />'  );

				jQuery('#'+ formId + ' input[name="'+controls[i] +'"]').val(value);							
			}*/
			
			var javaScriptstr = "<script type=\"text/javascript\"> jQuery('#btn"+ formId + "mainFunLoc').click(function(){";
			javaScriptstr +=	formId+"_fillFunloc(1|2|4|8|16|32|64|128|256);}); ";
			javaScriptstr += " jQuery('#lin"+formId +"Company').click(function(){ " + formId+"_fillFunloc(1);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Location').click(function(){ " + formId+"_fillFunloc(1|2);});";
			//fact// javaScriptstr +=" jQuery('#lin"+formId +"Factory').click(function(){ " + formId+"_fillFunloc(1|2|4);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Sbu').click(function(){ " + formId+"_fillFunloc(1|2|4|8);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Pbu').click(function(){ " + formId+"_fillFunloc(1|2|4|8|16);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Section').click(function(){ " + formId+"_fillFunloc(1|2|4|8|16|32);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Cell').click(function(){ " + formId+"_fillFunloc(1|2|4|8|16|32|64);});";
			javaScriptstr +=" jQuery('#lin"+formId +"Team').click(function(){ " + formId+"_fillFunloc(1|2|4|8|16|32|64|128);}); ";
			javaScriptstr +=" jQuery('#lin"+formId +"Machine').click(function(){ " + formId+"_fillFunloc(1|2|4|8|16|32|64|128|256);}); ";
			//javaScriptstr +=" jQuery('#lin"+formId +"Company').click(function(){ " + formId+"_fillFunloc();});";
			 
			javaScriptstr += " <\/script>";
			 
			jQuery("#"+divId).append(javaScriptstr);		
		try{
			var funLocSuccessCallBack = eval( formId+"_FuntLocHierarchy_SuccessCallBack" );
		 //alert('funLocSuccessCallBack:'+funLocSuccessCallBack);
			if( jQuery.isFunction(funLocSuccessCallBack)){
				var args = [ keyIds ];
				retVal = window[formId+"_FuntLocHierarchy_SuccessCallBack"].apply(this,args);
			}
		}catch(Exception ){
			
		}
		
		jQuery( "#functLocHierarPopupId" ).dialog("close");
		//jQuery("#functLocHierarPopupId").html("");			
	} 
}
function getComboBoxText(id){
	return jQuery("#"+id).combobox("getText");
}

function frmFunctLocHierarch_onerrorCallBack(result){
	
}
function clearCheckClearHiddenFld(){
	//alert("start");
	if(  !jQuery('#cmbFunctLocLocn').combobox('options').readonly ){
		jQuery("#clearLocn").val('');
		jQuery("#cmbFunctLocLocn").combobox('clear');
	}	
	if(  !jQuery('#cmbFunctLocSBU').combobox('options').readonly ){
		jQuery("#cmbFunctLocSBU").combobox('clear');
		jQuery("#clearSbu").val('');
	}	
	if(  !jQuery('#cmbFunctLocPBU').combobox('options').readonly ){
		jQuery("#cmbFunctLocPBU").combobox('clear');
		jQuery("#clearPbu").val('');	
	}	
	//fact// jQuery("#cmbFunctLocFact").combobox('clear');
	if(  !jQuery('#cmbFunctLocSect').combobox('options').readonly ){
		jQuery("#cmbFunctLocSect").combobox('clear');
		jQuery("#clearSect").val('');		
	}	
	if(  !jQuery('#cmbFunctLocCell').combobox('options').readonly ){
		jQuery("#cmbFunctLocCell").combobox('clear');
		jQuery("#clearCell").val('');	
	}	
	if(  !jQuery('#cmbFunctLocMachine').combobox('options').readonly ){
		jQuery("#cmbFunctLocMachine").combobox('clear');
		jQuery("#clearMach").val('');
	}	
	
	
	///jQuery("#cmbFunctLocTeam").combobox('clear');
	
	
	//fact// jQuery("#clearFact").val('');
	
	
	//alert("END");
	
	jQuery("#clearTeam").val('');

}

/*function frmFunctLocHierarchcmbFunctLocComp_onLoadSuccess()
{
	var compId = getFieldValue('cmbFunctLocComp');
	 fillComboBox("frmFunctLocHierarch","cmbFunctLocLocn","location.commonFilter?compId="+compId );
	 
}

function frmFunctLocHierarchcmbFunctLocLocn_onLoadSuccess (){

	var clearLocn = jQuery("#clearLocn").val();
	if( clearLocn != true && clearLocn != 'true'  ){
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		//fact// fillComboBox("frmFunctLocHierarch","cmbFunctLocFact","factroyCombo.commonFilter?compId="+compId +"&locnId="+locnId );
		fillComboBox("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter?compId="+compId +"&locnId="+locnId );
	}
	else{
		jQuery("#cmbFunctLocLocn").combobox('clear');
	}	
}

//fact//
/*
function frmFunctLocHierarchcmbFunctLocfunctionalLocFilter.jspFact_onLoadSuccess()
{
	var clearFact = jQuery("#clearFact").val();
	if( clearFact != true && clearFact != 'true'  ){
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = getFieldValue('cmbFunctLocFact');		
		
		fillComboBox("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId );
	}
	else
		jQuery("#cmbFunctLocFact").combobox('clear');
}
*/
/*function frmFunctLocHierarchcmbFunctLocSBU_onLoadSuccess()
{
	var clearSbu = jQuery("#clearSbu").val();
	if( clearSbu != true && clearSbu != 'true'  ){
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = ""; //getFieldValue('cmbFunctLocFact');
		var sbuId = getFieldValue('cmbFunctLocSBU');
		
		fillComboBox("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbuId );
		
	}
	else
		jQuery("#cmbFunctLocSBU").combobox('clear');
}
function frmFunctLocHierarchcmbFunctLocPBU_onLoadSuccess()
{
	var clearPbu = jQuery("#clearPbu").val();
	if( clearPbu != true && clearPbu != 'true'  ){
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = "";//getFieldValue('cmbFunctLocFact');
		var sbuId = getFieldValue('cmbFunctLocSBU');
		var pbuId = getFieldValue('cmbFunctLocPBU');
		fillComboBox("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbuId+"&pbuId="+pbuId );		
	}
	else
		jQuery("#cmbFunctLocPBU").combobox('clear');
}
function frmFunctLocHierarchcmbFunctLocSect_onLoadSuccess()
{
	var clearSect = jQuery("#clearSect").val();
	if( clearSect != true && clearSect != 'true'  ){
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = ""; //getFieldValue('cmbFunctLocFact');
		var sbuId = getFieldValue('cmbFunctLocSBU');
		var pbuId = getFieldValue('cmbFunctLocPBU');
		var sectId = getFieldValue('cmbFunctLocSect');
		
		fillComboBox("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbuId+"&pbuId="+pbuId+"&sectId="+sectId );
		
	}
	else
		jQuery("#cmbFunctLocSect").combobox('clear');	
}
function frmFunctLocHierarchcmbFunctLocCell_onLoadSuccess()
{
	var clearCell = jQuery("#clearCell").val();
	if( clearCell != true && clearCell != 'true'  ){
	
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = ""; //getFieldValue('cmbFunctLocFact');
		var sbuId = getFieldValue('cmbFunctLocSBU');
		var pbuId = getFieldValue('cmbFunctLocPBU');
		var sectId = getFieldValue('cmbFunctLocSect');
		var cellId = jQuery("#cmbFunctLocCell").combobox('getValue');
		
		fillComboBox("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sbuId="+sbuId+"&pbuId="+pbuId+"&sectId="+sectId+"&cellId="+cellId);
		
		//fillComboBox("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId);
		
	}
	else
		jQuery("#cmbFunctLocCell").combobox('clear');
		
}
function frmFunctLocHierarchcmbFunctLocTeam_onLoadSuccess()
{
	var clearTeam = jQuery("#clearTeam").val();
	if( clearTeam != true && clearTeam != 'true'  ){
	
		var compId = getFieldValue('cmbFunctLocComp');
		var locnId = getFieldValue('cmbFunctLocLocn');
		var factId = ""; //getFieldValue('cmbFunctLocFact');
		var sbuId = getFieldValue('cmbFunctLocSBU');
		var pbuId = getFieldValue('cmbFunctLocPBU');
		var sectId = getFieldValue('cmbFunctLocSect');
		var cellId = jQuery("#cmbFunctLocCell").combobox('getValue');
		var teamId ="";// jQuery('#cmbFunctLocTeam').combobox('getValue');	
		
		fillComboBox("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?compId="+compId +"&locnId="+locnId+"&factId="+factId+"&sectId="+sectId+"&sbuId="+sbuId+"&pbuId="+pbuId+"&cellId="+cellId+"&teamId="+teamId);
		
	}
	//else
	//	jQuery("#cmbFunctLocTeam").combobox('clear');
		
}
function frmFunctLocHierarchchcmbFunctLocMachine_onLoadSuccess()
{
	var clearMchm = jQuery("#clearMchm").val();
	if( clearMchm == true && clearMchm == 'true'  )
		jQuery("#cmbFunctLocMachine").combobox('clear');
}
*/
function frmFunctLocHierarchcmbFunctLocComp_onSelect(record){

	clearCheckClearHiddenFld();
	//jQuery("#cmbFunctLocLocn").combobox('clear');
	//fact// jQuery("#cmbFunctLocFact").combobox('clear');
/*	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
	///jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/
	if(  !jQuery('#cmbFunctLocLocn').combobox('options').readonly )
		reloadCombo("frmFunctLocHierarch","cmbFunctLocLocn","location.commonFilter");//?compId="+record.id);
	//fact// reloadCombo("frmFunctLocHierarch","cmbFunctLocFact","factroyCombo.commonFilter?compId="+record.id);
    if(  !jQuery('#cmbFunctLocSBU').combobox('options').readonly )
    	reloadCombo("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter");//?compId="+record.id);

    reloadCombo("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter");//?compId="+record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter");//?compId="+record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter");//?compId="+record.id  );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?compId="+record.id  );
	
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter");//?compId="+ record.id );	
}

function frmFunctLocHierarchcmbFunctLocLocn_onSelect(record){
	
	
	
	
//	var compId = getFieldValue('cmbFunctLocComp');
	clearCheckClearHiddenFld();	
//	jQuery("#cmbFunctLocComp").combobox('clear');
	//fact// jQuery("#cmbFunctLocFact").combobox('clear');
/*	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
	///jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/

	fillFunctionalLocHierarchy(record.id);	
	//fact// reloadCombo("frmFunctLocHierarch","cmbFunctLocFact","factroyCombo.commonFilter?locnId="+record.id +"&compId="+compId);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter");//?locnId="+record.id +"&compId="+compId);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter");//?locnId="+record.id +"&compId="+compId);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter");//locnId="+record.id+"&compId="+compId);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter");//?locnId="+record.id +"&compId="+compId );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?locnId="+record.id +"compId="+record.id  );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter");//?locnId="+ record.id +"&compId="+compId);

	//filllocationHierarchy("locationHierarchy.commonFilter",record.id,'cmbFunctLocComp');	
	
	
}
/*
function  frmFunctLocHierarchcmbFunctLocFact_onSelect(record)
{
	clearCheckClearHiddenFld();
	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
	//jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');

	reloadCombo("frmFunctLocHierarch","cmbFunctLocSBU","sbuCombo.commonFilter?factId="+record.id );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter?factId="+record.id );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?factId="+record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?factId="+record.id  );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?factId="+record.id   );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?factId="+ record.id );

	///fillfactoryHierarchy("factoryHierarchy.commonFilter",record.id,"cmbFunctLocComp","cmbFunctLocLocn");
	fillFunctionalLocHierarchy(record.id);
}
*/
function  frmFunctLocHierarchcmbFunctLocSBU_onSelect(record)
{
	//if (isHierarchyLoading) return;
	isHierarchyLoading = true;
	clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
///	jQuery("#cmbFunctLocFact").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
//	jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/
	fillFunctionalLocHierarchy(record.id);
	//jQuery("#cmbFunctLocSBU").combobox("setValue",record.id);
	
	reloadCombo("frmFunctLocHierarch","cmbFunctLocPBU","pbuCombo.commonFilter?sbuId="+record.id  );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?sbuId="+record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?sbuId="+record.id  );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?factId="+record.id   );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?sbuId="+ record.id );

	//fillSbuHierarchy("sbuHierarchy.commonFilter",record.id,"cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn");
	
}

function  frmFunctLocHierarchcmbFunctLocPBU_onSelect(record)
{
	
	clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	
	
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
	//jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/	

	fillFunctionalLocHierarchy(record.id);
	//jQuery("#cmbFunctLocPBU").combobox("setValue",record.id);	
	reloadCombo("frmFunctLocHierarch","cmbFunctLocSect","sectionCombo.commonFilter?pbuId="+record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?pbuId="+record.id  );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?factId="+record.id   );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?pbuId="+ record.id );
	
	
	//fillSbuHierarchy("pbuHierarchy.commonFilter",record.id,"cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn");
}
function  frmFunctLocHierarchcmbFunctLocSect_onSelect(record)
{
	
	clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
	//jQuery("#cmbFunctLocTeam").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/	
    //jQuery("#cmbFunctLocSect").combobox('setValue',record.id);
	fillFunctionalLocHierarchy(record.id);	
	//jQuery("#cmbFunctLocSect").combobox('setValue',record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocCell","cellCombo.commonFilter?sectId="+record.id  );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?factId="+record.id   );
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?sectId="+ record.id );
	
	
	//fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn");
}

function  frmFunctLocHierarchcmbFunctLocCell_onSelect(record)
{
	//alert("cell :"+record.text);
	
	clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocMachine").combobox('clear');
//	jQuery("#cmbFunctLocTeam").combobox('clear');
*/	
	fillFunctionalLocHierarchy(record.id);
	//jQuery("#cmbFunctLocCell").combobox('setValue',record.id);
	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?cellId="+ record.id );
	//reloadCombo("frmFunctLocHierarch","cmbFunctLocTeam","teamCombo.commonFilter?factId="+record.id   );
	
	//fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbFunctLocSect","cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn");		
}

function  frmFunctLocHierarchcmbFunctLocTeam_onSelect(record)
{
	
	
	clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	
	jQuery("#cmbFunctLocMachine").combobox('clear');
*/	
//	reloadCombo("frmFunctLocHierarch","cmbFunctLocMachine","machineCombo.commonFilter?teamId="+ record.id );								
	fillFunctionalLocHierarchy(record.id);
	///fillTeamHierarchy("teamHierarchy.commonFilter",record.id,"cmbFunctLocCell","cmbFunctLocSect","cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn","");
	
}
function  frmFunctLocHierarchcmbFunctLocMachine_onSelect(record)
{
	
	//clearCheckClearHiddenFld();
/*	jQuery("#cmbFunctLocComp").combobox('clear');
	jQuery("#cmbFunctLocLocn").combobox('clear');
	jQuery("#cmbFunctLocSBU").combobox('clear');
	jQuery("#cmbFunctLocPBU").combobox('clear');
	jQuery("#cmbFunctLocSect").combobox('clear');
	jQuery("#cmbFunctLocCell").combobox('clear');
*/	
//	jQuery("#cmbFunctLocTeam").combobox('clear');
	///fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbFunctLocCell","cmbFunctLocSect","cmbFunctLocFact","cmbFunctLocComp","cmbFunctLocLocn");
	fillFunctionalLocHierarchy(record.id);
	
}


</script>

</head>
<body>
<form id="frmFunctLocHierarch" >
<div style="margin-left:20%;padding-top:20px; ">
			      <div  class="easyui-paddingbfpx">
                  <label class =  "${ requestScope.functLocHierarchyIdentBean.company.mandatory == true ? 'mandatory-lbl':''}"  > Company</label>                       
                  </div>
                		   
                  <div class="easyui-paddingbfpx"  style="width:140px;"> 
                       <input id="cmbFunctLocComp" name="${requestScope.functLocHierarchyIdentBean.company.name != '' ? requestScope.functLocHierarchyIdentBean.company.name :'cmbFunctLocCompany' }" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.company.value}" ${ requestScope.functLocHierarchyIdentBean.company.disable == true  ? ' readonly':''} >                       
                  </div>
                  
                  <div  class="easyui-paddingbfpx">
                  <label class="${ requestScope.functLocHierarchyIdentBean.location.mandatory == true  ? 'mandatory-lbl':''}" > Location</label>                       
                  </div> 
                  <div class="easyui-paddingbfpx"  style="width:140px;"> 
                       <input id="cmbFunctLocLocn" name="${requestScope.functLocHierarchyIdentBean.location.name != '' ? requestScope.functLocHierarchyIdentBean.location.name :'cmbFunctLocLocn'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.location.value}"  ${ requestScope.functLocHierarchyIdentBean.location.disable == true  ? ' readonly':''}  >                       
                  </div>
                  <%-- 
                  <div  class="easyui-paddingbfpx">
                  <label class=<c:out value = "${ requestScope.functLocHierarchyIdentBean.factory.mandatory == true  ? 'mandatory-lbl':''}"/> > Factory</label>                       
                  </div>
                   
                  <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocFact" name="${requestScope.functLocHierarchyIdentBean.factory.name !=''?requestScope.functLocHierarchyIdentBean.factory.name:'cmbFactory'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.factory.value}" <c:out value = "${ requestScope.functLocHierarchyIdentBean.factory.disable == true  ? ' readonly':''}"/> >                       
                  </div> --%>
                  
                   <div  class="easyui-paddingbfpx">
                  <label class="${ requestScope.functLocHierarchyIdentBean.sbu.mandatory == true  ? 'mandatory-lbl':''}" > SBU</label>                       
                  </div> 
                  <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocSBU" name="${requestScope.functLocHierarchyIdentBean.sbu.name !=''?requestScope.functLocHierarchyIdentBean.sbu.name:'cmbSBU'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.sbu.value}"  ${ requestScope.functLocHierarchyIdentBean.sbu.disable == true  ? ' readonly':''} >                       
                  </div>
                  
                  <div  class="easyui-paddingbfpx">
                  <label class="${ requestScope.functLocHierarchyIdentBean.pbu.mandatory == true  ? 'mandatory-lbl':''}" > PBU</label>                       
                  </div> 
                  <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocPBU" name="${requestScope.functLocHierarchyIdentBean.pbu.name !=''?requestScope.functLocHierarchyIdentBean.pbu.name:'cmbPBU'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.pbu.value}"  ${ requestScope.functLocHierarchyIdentBean.pbu.disable == true  ? ' readonly':''} >                       
                  </div>
                  
                  <div  class="easyui-paddingbfpx">
                  <label class= "${ requestScope.functLocHierarchyIdentBean.section.mandatory == true  ? 'mandatory-lbl':''}">DMT/AET/SET</label>                       
                  </div> 
                  <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocSect" name="${requestScope.functLocHierarchyIdentBean.section.name !=''?requestScope.functLocHierarchyIdentBean.section.name:'cmbSection'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.section.value}" ${ requestScope.functLocHierarchyIdentBean.section.disable == true  ? ' readonly':''} >                       
                  </div>
                  <div  class="easyui-paddingbfpx">
                  <label class= "${ requestScope.functLocHierarchyIdentBean.cell.mandatory == true  ? 'mandatory-lbl':''}">JH/PACT</label>                       
                  </div> 
                   <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocCell" name="${requestScope.functLocHierarchyIdentBean.cell.name != ''?requestScope.functLocHierarchyIdentBean.cell.name:'cmbCell'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.cell.value}"  ${ requestScope.functLocHierarchyIdentBean.cell.disable == true  ? ' readonly':''}  >                       
                   </div>
                  <!--<div  class="easyui-paddingbfpx">
                  <label class=<c:out value = "${ requestScope.functLocHierarchyIdentBean.team.mandatory == true  ? 'mandatory-lbl':''}"/>>JH</label>                       
                  </div> 
                   <div class="easyui-paddingbfpx"> 
                       <input id="cmbFunctLocTeam" name="${requestScope.functLocHierarchyIdentBean.team.name != ''?requestScope.functLocHierarchyIdentBean.team.name:'cmbTeam'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.team.value}" <c:out value = "${ requestScope.functLocHierarchyIdentBean.team.disable == true  ? ' readonly':''}"/>  >                       
                   </div>
                                    
                  --><div  class="easyui-paddingbfpx">
                   <label class="${ requestScope.functLocHierarchyIdentBean.machine.mandatory == true  ? 'mandatory-lbl':''}">Equipment</label>                       
                   </div> 
                   <div class="easyui-paddingbfpx" style="width:140px;"> 
                       <input id="cmbFunctLocMachine" name="${requestScope.functLocHierarchyIdentBean.machine.name != ''?requestScope.functLocHierarchyIdentBean.machine.name:'cmbMachine'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.machine.value}"  ${ requestScope.functLocHierarchyIdentBean.machine.disable == true  ? ' readonly':''} >                       
                  </div>
                  <input type="hidden" id="hdnFunctLocFL" name="${requestScope.functLocHierarchyIdentBean.flid.name != ''?requestScope.functLocHierarchyIdentBean.flid.name:'cmbFlid'}" class="easyui-combobox"  style="width:300px" value="${ requestScope.functLocHierarchyIdentBean.flid.value}"  />
    </div>
                  <div align="center" style="padding-top: 3%;">
               		 <input type="button" id="btnFuntLocOk"  class="easyui-button"  value="Ok" />
               		 <input type="button" id="btnFuntLocClear"  class="easyui-button"  value="Clear"  />
                  </div>
<input type="hidden" id="hdnLockFL" name="disable" value="${requestScope.disable}" />	
	
</form>
<input type="hidden" id="clearLocn" >
<input type="hidden" id="clearFact" >
<input type="hidden" id="clearSbu" >
<input type="hidden" id="clearPbu" >
<input type="hidden" id="clearSect" >
<input type="hidden" id="clearCell" >
<input type="hidden" id="clearTeam" >
<input type="hidden" id="clearMachine" >
</body>
</html>


