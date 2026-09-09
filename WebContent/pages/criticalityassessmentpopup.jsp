 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm("frmCriticalityass");
    var datastr="q=2";
	var url=jQuery("#hiddenUrl").val();
	
	numericTextBox("txtproductivity",false);
	numericTextBox("txtQuality",false);
	numericTextBox("txtCost",false);
	numericTextBox("txtDelivery",false);
	numericTextBox("txtSafety",false);
	numericTextBox("txtOperatability",false);
	numericTextBox("txtMaintanability",false);
	numericTextBox("txtRelability",false);

});

jQuery('#btnCancel').click( function()
{	
   closePopUpDialoge("divMultiple");	
});	

function frmCriticalityass_deleteSuccessCallback()
{
	closePopUpDialoge("divMultiple");
}

/* jQuery("#btnApplyselectequipment").click(function(){	
var productivity=jQuery("#txtproductivity").val();
var quality=jQuery("#txtQuality").val();
var cost=jQuery("#txtCost").val();
var delivery=jQuery("#txtDelivery").val();
var safety=jQuery("#txtSafety").val();
var operatability=jQuery("#txtOperatability").val();
var maintanability=jQuery("#txtMaintanability").val();
var relability=jQuery("#txtRelability").val();

   if(productivity.length==0)
   {
	   alert("Enter the Productivity");
	   return false;
   }
   else{
	   if(productivity!=1 && productivity!=3 && productivity!=9)
		   {
		      alert("Enter the No 1,3,9");
		      setFieldValue("txtproductivity","");
		      return false;
		   }
	   else{
		   productivity=productivity;
	   } 
   }
   if(quality.length==0){
	   alert("Enter the Quality");
	   return false;
   }
   
   else{
	   if(quality!=1 && quality!=3 && quality!=9)
		   {
		      alert("Enter the No 1,3,9");
		      setFieldValue("txtQuality","");
		      return false;
		   }
	   else{
		   quality=quality;
	   } 
   }
   
    if(cost.length==0){
	   alert("Enter the Cost");
	   return false;
   }
    else{
 	   if(cost!=1 && cost!=3 && cost!=9)
 		   {
 		      alert("Enter the No 1,3,9");
 		      setFieldValue("txtCost","");
 		      return false;
 		   }
 	   else{
 		  cost=cost;
 	   } 
    }   
       
    
    if(delivery.length==0){
	   alert("Enter the Delivery");
	   return false;
   }

    else{
  	   if(delivery!=1 && delivery!=3 && delivery!=9)
  		   {
  		      alert("Enter the No 1,3,9");
  		      setFieldValue("txtDelivery","");
  		      return false;
  		   }
  	   else{
  		 delivery=delivery;
  	   } 
     } 
     
    if(safety.length==0){
	   alert("Enter the Safety");
	   return false;
   }
    else{
   	   if(safety!=1 && safety!=3 && safety!=9)
   		   {
   		      alert("Enter the No 1,3,9");
   		      setFieldValue("txtSafety","");
   		      return false;
   		   }
   	   else{
   		safety=safety;
   	   } 
      } 
     

    if(operatability.length==0){
	   alert("Enter the Operatability");
	   return false;
   }
    else{
    	   if(operatability!=1 && operatability!=3 && operatability!=9)
    		   {
    		      alert("Enter the No 1,3,9");
    		      setFieldValue("txtOperatability","");
    		      return false;
    		   }
    	   else{
    		   operatability=operatability;
    	   } 
       } 
    if(maintanability.length==0){
	   alert("Enter the Maintanability");
	   return false;
   }
    else{
 	   if(maintanability!=1 && maintanability!=3 && maintanability!=9)
 		   {
 		      alert("Enter the No 1,3,9");
 		      setFieldValue("txtMaintanability","");
 		      return false;
 		   }
 	   else{
 		  maintanability=maintanability;
 	   } 
    } 
    if(relability.length==0){
	   alert("Enter the Reliability");
	   return false;
   }
    else{
  	   if(relability!=1 && relability!=3 && relability!=9)
  		   {
  		      alert("Enter the No 1,3,9");
  		      setFieldValue("txtRelability","");
  		      return false;
  		   }
  	   else{
  		 relability=relability;
  	   } 
     } 
     
var Keys = jQuery("#frmCriticality input[id='hdnKeysArr']").val();
var rowid=Keys.split(",");
for(var k=0;k<rowid.length;k++){
	var prd="20_PLMC0001_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+prd+"]").val(productivity);
	var qlt="20_PLMC0002_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+qlt+"]").val(quality);
	var costs="15_PLMC0003_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+costs+"]").val(cost);
	var deliverys="10_PLMC0004_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+deliverys+"]").val(delivery);
	var safy="20_PLMC0005_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+safy+"]").val(safety);
	var oprlity="5_PLMC0006_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+oprlity+"]").val(operatability);
	var mality="5_PLMC0007_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+mality+"]").val(maintanability);
	var relity="5_PLMC0008_grdCriticallity_"+rowid[k];
	jQuery("#frmCriticality input[id="+relity+"]").val(relability);
}
alert("Apply the Selected Equipment");
}); */
//mano
jQuery("#btnApplyselectequipment").click(function(){	
    var productivity=jQuery("#txtproductivity").val();
    var quality=jQuery("#txtQuality").val();
    var cost=jQuery("#txtCost").val();
    var delivery=jQuery("#txtDelivery").val();
    var safety=jQuery("#txtSafety").val();
    var operatability=jQuery("#txtOperatability").val();
    var maintanability=jQuery("#txtMaintanability").val();
    var relability=jQuery("#txtRelability").val();

    // Validation code (keeping your existing validation)
    if(productivity.length==0) {
        alert("Enter the Productivity");
        return false;
    }
    else {
        if(productivity!=1 && productivity!=3 && productivity!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtproductivity","");
            return false;
        }
    }
    
    if(quality.length==0){
        alert("Enter the Quality");
        return false;
    }
    else {
        if(quality!=1 && quality!=3 && quality!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtQuality","");
            return false;
        }
    }
    
    if(cost.length==0){
        alert("Enter the Cost");
        return false;
    }
    else {
        if(cost!=1 && cost!=3 && cost!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtCost","");
            return false;
        }
    }   
    
    if(delivery.length==0){
        alert("Enter the Delivery");
        return false;
    }
    else {
        if(delivery!=1 && delivery!=3 && delivery!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtDelivery","");
            return false;
        }
    } 
    
    if(safety.length==0){
        alert("Enter the Safety");
        return false;
    }
    else {
        if(safety!=1 && safety!=3 && safety!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtSafety","");
            return false;
        }
    } 
    
    if(operatability.length==0){
        alert("Enter the Operatability");
        return false;
    }
    else {
        if(operatability!=1 && operatability!=3 && operatability!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtOperatability","");
            return false;
        }
    } 
    
    if(maintanability.length==0){
        alert("Enter the Maintanability");
        return false;
    }
    else {
        if(maintanability!=1 && maintanability!=3 && maintanability!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtMaintanability","");
            return false;
        }
    } 
    
    if(relability.length==0){
        alert("Enter the Reliability");
        return false;
    }
    else {
        if(relability!=1 && relability!=3 && relability!=9) {
            alert("Enter the No 1,3,9");
            setFieldValue("txtRelability","");
            return false;
        }
    } 
    
    var Keys = jQuery("#frmCriticality input[id='hdnKeysArr']").val();
    var rowid=Keys.split(",");
    
    for(var k=0; k<rowid.length; k++){
        var prd="20_PLMC0001_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+prd+"]").val(productivity);
        
        var qlt="20_PLMC0002_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+qlt+"]").val(quality);
        
        var costs="15_PLMC0003_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+costs+"]").val(cost);
        
        var deliverys="10_PLMC0004_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+deliverys+"]").val(delivery);
        
        var safy="20_PLMC0005_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+safy+"]").val(safety);
        
        var oprlity="5_PLMC0006_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+oprlity+"]").val(operatability);
        
        var mality="5_PLMC0007_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+mality+"]").val(maintanability);
        
        var relity="5_PLMC0008_grdCriticallity_"+rowid[k];
        jQuery("#frmCriticality input[id="+relity+"]").val(relability);
        
        // **KEY FIX**: Call calSummary for each row to recalculate totals
        calSummary(rowid[k]);
    }
    
    alert("Applied to Selected Equipment");
    
    // **KEY FIX**: Close the popup after applying values
    closePopUpDialoge("divMultiple");
});

function frmCriticalityass_SuccessCallback(result){
	
}

function err_OnSave(){
	alert("Error");
}

function totalData(){
     var productivity=jQuery("#txtproductivity").val();
     var quality=jQuery("#txtQuality").val();
     var cost=jQuery("#txtCost").val();
     var delivery=jQuery("#txtDelivery").val();
     var safety=jQuery("#txtSafety").val();
     var operatability=jQuery("#txtOperatability").val();
     var maintanability=jQuery("#txtMaintanability").val();
     var relability=jQuery("#txtRelability").val();

if(productivity.length!=0){
 if(productivity<=9){	
 if(productivity==1 || productivity==3 || productivity==9)	
 {
 }
else
	{
	setFieldValue("txtproductivity","");
	}
 }
}


if(quality.length!=0){
if(quality<=9){
	if(quality==1 || quality==3 || quality==9)
	{
	}
	else
		{
		setFieldValue("txtQuality","");
		}
		}
	}
	
if(cost.length!=0){
	if(cost<=9){
		if(cost==1 || cost==3 || cost==9)
		{
		}
		else
			{
			setFieldValue("txtCost","");
			}
			}
		}
	
if(delivery.length!=0){
	if(delivery<=9){
		if(delivery==1 || delivery==3 || delivery==9)
		{
		}
		else
			{
			setFieldValue("txtDelivery","");
			}
			}
		}


if(safety.length!=0){
	if(safety<=9){
		if(safety==1 || safety==3 || safety==9)
		{
		}
		else
			{
			setFieldValue("txtSafety","");
			}
			}
		}


if(operatability.length!=0){
	if(operatability<=9){
		if(operatability==1 || operatability==3 || operatability==9)
		{
		}
		else
			{
			setFieldValue("txtOperatability","");
			}
			}
		}

if(maintanability.length!=0){
	if(maintanability<=9){
		if(maintanability==1 || maintanability==3 || maintanability==9)
		{
		}
		else
			{
			setFieldValue("txtMaintanability","");
			}
			}
		}

if(relability.length!=0){
	if(relability<=9){
		if(relability==1 || relability==3 || relability==9)
		{
		}
		else
			{
			setFieldValue("txtRelability","");
			}
			}
		}
}

</script>
<form id="frmCriticalityass" name="frmCriticalityass" style="width: 105%;">
<div style="margin-left:8%;">
	<table id="grdCriticalitygid" style="width:100%;"><tr><td/></tr></table>
	<div id="pgrMultipleEquipment"></div>
	<input type="hidden" id="hdngrid" value=""/>
<label  style="font:bolder ;color: blue; ">Enter the Critical value should be 1,3 or 9</label>	
</div>
<div style="padding-left:0px;">
<span style="padding-left:40px;">
<label>Productivity</label>
</span>
<div style="padding-left:40px;">
<input id="txtproductivity" type="text" class="easyui-text" size="20" name="txtproductivity" value="" onkeydown="totalData()" maxlength="1">	
</div>

 <span style="padding-left:40px;">
 <label>Quality</label>
 </span>
 <div style="padding-left:40px;">
<input id="txtQuality" type="text" class="easyui-text" size="20" name="txtQuality" value="" onkeydown="totalData()" maxlength="1">	
</div>	    

<span style="padding-left:40px;">
<label>Cost</label>
</span>
<div style="padding-left:40px;">
 <input id="txtCost" type="text" class="easyui-text" size="20" name="txtCost" value="" onkeydown="totalData()" maxlength="1">	 
 </div>
 
<span style="padding-left:40px;"> 
<label>Delivery</label>
</span>
<div style="padding-left:40px;">
<input id="txtDelivery" type="text" class="easyui-text" size="20" name="txtDelivery" value="" onkeydown="totalData()" maxlength="1">	
</div>

<span style="padding-left:40px;">
<label>Safety</label>
</span>
<div style="padding-left:40px;">
<input id="txtSafety" type="text" class="easyui-text" size="20" name="txtSafety" value="" onkeydown="totalData()" maxlength="1">	
</div>	  
	  
<span style="padding-left:40px;">
<label>Operatability</label>
</span>
<div style="padding-left:40px;">	  	
<input id="txtOperatability" type="text" class="easyui-text" size="20" name="txtOperatability" value="" onkeydown="totalData()" maxlength="1">	
</div>
	
<span style="padding-left:40px;">
<label>Maintanability</label>
</span>
<div style="padding-left:40px;">	  	
<input id="txtMaintanability" type="text" class="easyui-text" size="20" name="txtMaintanability" value="" onkeydown="totalData()" maxlength="1">	
</div>

<span style="padding-left:40px;">
<label>Relability</label>
</span>
<div style="padding-left:40px;">
<input id="txtRelability" type="text" class="easyui-text" size="20" name="txtRelability" value="" onkeydown="totalData()" maxlength="1">	
</div>

</div>

<div class="easyui-paddingbfpx" style="padding-left:0px;"> 
<input type="button" class="easyui-button" style=" width : 190px;" id="btnApplyselectequipment" name="btnApplyselectequipment" value="Apply To Selected Equipments"/>
<input type="button" class="easyui-button" style=" width : 70px;" id="btnCancel" name="btnCancel" value="Cancel"/>
</div>
<input type="hidden" id="hdnCriticalKey" name="hdnCriticalKey" value="">
</form>

