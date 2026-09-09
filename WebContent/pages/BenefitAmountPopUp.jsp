<script>

jQuery(document).ready(function(){

	
	initialiseForm('benefitAmt');
	jQuery('#submitForm').val('benefitAmt');
	
	var savingAmt=jQuery('#valSavings').val();
	
	
	jQuery('#txtSavingamt').val("Rs. "+convertedSavingAmount(jQuery('#valSavings').val()));
	
	readOnlyFields("txtSavingamt");
});



function convertedSavingAmount(svgAmt) {
	  var sglDigit = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"],
	    dblDigit = ["Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"],
	    tensPlace = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"],
	    handle_tens = function(dgt, prevDgt) {
	      return 0 == dgt ? "" : " " + (1 == dgt ? dblDigit[prevDgt] : tensPlace[dgt])
	    },
	    handle_utlc = function(dgt, nxtDgt, denom) {
	      return (0 != dgt && 1 != nxtDgt ? " " + sglDigit[dgt] : "") + (0 != nxtDgt || dgt > 0 ? " " + denom : "")
	    };

	  var str = "",
	    digitIdx = 0,
	    digit = 0,
	    nxtDigit = 0,
	    words = [];
	  if (svgAmt += "", isNaN(parseInt(svgAmt))) str = "";
	  else if (parseInt(svgAmt) > 0 && svgAmt.length <= 10) {
	    for (digitIdx = svgAmt.length - 1; digitIdx >= 0; digitIdx--) switch (digit = svgAmt[digitIdx] - 0, nxtDigit = digitIdx > 0 ? svgAmt[digitIdx - 1] - 0 : 0, svgAmt.length - digitIdx - 1) {
	      case 0:
	        words.push(handle_utlc(digit, nxtDigit, ""));
	        break;
	      case 1:
	        words.push(handle_tens(digit, svgAmt[digitIdx + 1]));
	        break;
	      case 2:
	        words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != svgAmt[digitIdx + 1] && 0 != svgAmt[digitIdx + 2] ? " and" : "") : "");
	        break;
	      case 3:
	        words.push(handle_utlc(digit, nxtDigit, "Thousand"));
	        break;
	      case 4:
	        words.push(handle_tens(digit, svgAmt[digitIdx + 1]));
	        break;
	      case 5:
	        words.push(handle_utlc(digit, nxtDigit, "Lakh"));
	        break;
	      case 6:
	        words.push(handle_tens(digit, svgAmt[digitIdx + 1]));
	        break;
	      case 7:
	        words.push(handle_utlc(digit, nxtDigit, "Crore"));
	        break;
	      case 8:
	        words.push(handle_tens(digit, svgAmt[digitIdx + 1]));
	        break;
	      case 9:
	        words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != svgAmt[digitIdx + 1] || 0 != svgAmt[digitIdx + 2] ? " and" : " Crore") : "")
	    }
	    str = words.reverse().join("")
	  } else str = "";
	  return str
	  

	}
jQuery("#amtCrct").click(function(){
	var savingAmt=parseInt(jQuery('#valSavings').val());
	var recmpAmt=parseInt(jQuery("#reConfirmAmount").val());
	
	if(recmpAmt==null||recmpAmt=='NaN'||recmpAmt=="" ||recmpAmt==0){
		alert(" Re confirm The Amount ..");
		 setFocusOnField("reConfirmAmount");
		  return false;
		 
	}
	else if(savingAmt==recmpAmt){
		
	closePopUpDialoge("KaizenBenAmt");
	
	}
	else if(recmpAmt!=0 &&parseInt(recmpAmt)<500000){
		alert(" Amount Should be Greater Than 500000 ");
	}
		if(parseInt(recmpAmt)>500000){
		closePopUpDialoge("KaizenBenAmt");
		jQuery('#txtKznmBenefitvalue').val(recmpAmt);
	
	}	
});

jQuery("#amtNotCrct").click(function(){
	
		  alert("Re Confirm The  Amount ");
		  setFocusOnField("reConfirmAmount");
		  return false;
});
jQuery( "#chkYes" ).click(function() {
	
	
		closePopUpDialoge("KaizenBenAmt");
		 setFocusOnField("fileName");
});

jQuery( "#chkNo" ).click(function() {
	
	
	 closePopUpDialoge("KaizenBenAmt");
	 setFocusOnField("txtKznmBenefitvalue");

});



</script>
<form id="benefitAmt">
 <div id = "wrapperRpt">
 <table>
 <tr>
 <td>
 <label>
 Benefit Value As Per Kaizen Sheet
 </label>
 </td>
 
  </tr>
  <tr>
  <td >
  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSavingamt" name="txtSavingamt"  rows="4" cols="40"  maxlength="550"></textarea>
  </td>  
  </tr>
  <tr  >
  <td >
  <div style="margin-top:20px;">
 <label >
 Re Confirm The Amount 
 </label>
 </div>
 </td>
  </tr>
  <!-- <tr>
   <td >
 
 
   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  style="margin-top:10px;" rows="2" cols="20" id="reConfirmAmount" Name="reConfirmAmount" ></textarea>
  </td>
  </tr> 
 
  <tr>
  <td>
  <!-- 
 <span style="margin-top: 10px; margin-left: 100px;">
  	 	<input class="easyui-button" type="button" value ="Yes" id="amtCrct" Name="amtCrct" style="height:20px;"/>  
  
 </span>
 <span style="margin-top: 10px; margin-top: 20px;">
  	 	<input class="easyui-button" type="button" value ="No" id="amtNotCrct" Name="amtNotCrct" style="height:20px;"/>  
   </span>
    -->
    <tr>
    <td>
    
    <span style="padding-left: 5px;">
			<input id="chkYes" name="chkYes" type="checkbox"    />
			<span Style="padding-left:5px;"> <label> Yes</label> </span>
	
    
    </span>
    
    <span style="padding-left: 35px;">
			<input id="chkNo" name="chkNo" type="checkbox"   />
			<span Style="padding-left:5px;"> <label> No</label> </span>
	
    
    </span>
    
  </td>
  </tr>
  </table>
  <input type="hidden" id="valSavings" name="valSavings" value="${requestScope.valSavings}">
  </div>
</form>