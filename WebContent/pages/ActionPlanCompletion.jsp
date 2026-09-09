<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			initialiseForm("frmAPlanComp");
			jQuery('#submitForm').val("frmAPlanComp");
			var url = jQuery('#hiddenUrl').val();
			//alert(url);
			viewGrid(url,"");
		   //  var flid = jQuery("#frmAPlanComp input[id='flid']").val();
            // alert(flid);
          var mode =jQuery('#frmAPlanComp input[id=mode]').val();
          var userRole=jQuery("#hdnUserId").val();     
            if(userRole=="JH LEADER" || userRole=="DMT LEADER" || userRole=="TPM CELL"){
            	jQuery("#chkEmptask").attr("disabled",false).removeClass("ui-state-disabled");
                jQuery("#chkTeamtask").attr("disabled",false).removeClass("ui-state-disabled");
            }
            else{
            	jQuery("#chkEmptask").attr("disabled",true).removeClass("ui-state-disabled");
                jQuery("#chkTeamtask").attr("disabled",true).removeClass("ui-state-disabled");
                  } 
            
               if(mode=="view"){
            		jQuery("#chkEmptask").attr("disabled",true).removeClass("ui-state-disabled");
                    jQuery("#chkTeamtask").attr("disabled",true).removeClass("ui-state-disabled");
                    jQuery("#lblEmptask").attr("disabled",true).removeClass("ui-state-disabled");
                    jQuery("#lblTeamtask").attr("disabled",true).removeClass("ui-state-disabled");
               }
            
            
		});
		
		function viewGrid(url,filterString)
		{
			 
			if( validateFilterSelection(filterString))
			{
				var mode =jQuery('#frmAPlanComp input[id=mode]').val();
				filterString += "&mode="+mode; 
			
				processGridnew(url,filterString,"grdAPComp","grdAPCompPager");
				return true;
			}	
		}
		
		function validateFilterSelection(filterString){
			return  true;
		}

function frmAPlanComp_beforeSubmit(){
	var saveArr =  getGridSelectArray("grdAPComp");

	if( saveArr != ""){
		if( validateMandtory(saveArr))
			return "apcompdt="+saveArr;
	}	
	//alert("Sele");
	return false;
}		

function frmAPlanComp_successsCallback(result){
	jQuery("#grdAPComp").trigger("reloadGrid");
}

function formatDate(date){
 	let newDate = new Date(date);
 		   
 	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

 	// Format DD-MMM-YYYY
     let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
 				            months[newDate.getMonth()] + '-' +
 				            newDate.getFullYear();
 	return formatted;
  }
  
function grdAPComp_selectRow(rowId){
	var currentDate = getCurrentDate();
	
	
	
	jQuery("#selApldStatus_grdAPComp_"+rowId).change(function (){
		
		
		if( this.value != "C")
		{
			jQuery("#dteApldCompleatedon_grdAPComp_"+rowId).datebox("clear");
			disableField("frmAPlanComp", "dteApldCompleatedon_grdAPComp_"+rowId);		
		}
		else{
			//enableFields("dteApldCompleatedon_grdAPComp_"+rowId);
			fillWithCurrentDate("dteApldCompleatedon_grdAPComp_"+rowId);
		}
			
		if( this.value == "W")
		{
		}
	});
	jQuery("#dteApldCompleatedon_grdAPComp_"+rowId).datebox({  	   
		onSelect:function(value){
			
			var compdate =formatDate(value);// jQuery("#dteApldCompleatedon_grdAPComp_"+rowId).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
				alert("Completed date can not be greater than current date");
				fillWithCurrentDate("dteApldCompleatedon_grdAPComp_"+rowId);
				return false;
			}
		} 
	});
	
	jQuery("#dteApldTargetdate_grdAPComp_"+rowId).datebox({  	   
		onSelect:function(value){
			//alert(value);
			var compdate = formatDate(value);// jQuery("#dteApldTargetdate_grdAPComp_"+rowId).datebox("getValue");
			//alert(compdate);
			//alert(compareDate(currentDate,compdate));
			if( compareDate(currentDate,compdate) < 0 )
			{
				alert("Target date can not be lesser than current date");
				setTimeout(function () {fillWithCurrentDate("dteApldTargetdate_grdAPComp_"+rowId);},100);
				return false;
			}
		} 
	});
	disableField("frmAPlanComp", "dteApldCompleatedon_grdAPComp_"+rowId);
	/*var selRowData =getGridRowData("grdAPComp",rowId );
	var rowObj = jQuery.parseJSON( selRowData );
	if(rowObj[0].selApldStatus =="W" ){
		if( rowObj[0].dteApldTargetdate.trim() == ""){
			alert("Select Target date");
			return false;
		}
		if( compareDate(selArr[i].dteApldTargetdate,currentDate) > 0 )
		{
			alert("Target date can not be lesser than current date");
			return false;
		}
	}
	else if(rowObj[0].selApldStatus =="C" ){ 
		if( rowObj[0].dteApldCompleatedon.trim() == "" ){
			alert("Select Completed date");
			return false;
		} 
	
		if( compareDate(selArr[i].dteApldCompleatedon,currentDate) < 0 )
		{
			alert("Completed date can not be greater than current date");
			return false;
		}
		
	}*/
}
function validateMandtory(gridSelArr){
	var selArr = JSON.parse(gridSelArr);
	var currentDate = getCurrentDate();
	for(var i = 0;i<selArr.length;i++){

		if(selArr[i].selApldStatus =="W" ){
			if( selArr[i].dteApldTargetdate.trim() == ""){
				alert("Select Target date");
				return false;
			}
			if( compareDate(currentDate,selArr[i].dteApldTargetdate) < 0 )
			{
				alert("Target date can not be lesser than current date");
				return false;
			}
		}
		else if(selArr[i].selApldStatus =="C" ){ 
			if( selArr[i].dteApldCompleatedon.trim() == "" ){
				alert("Select Completed date");
				return false;
			} 
		
			if( compareDate(selArr[i].dteApldCompleatedon,currentDate) < 0 )
			{
				alert("Completed date can not be greater than current date");
				return false;
			}
			
		}
	}
	return true;
}


jQuery("#chkEmptask").click(function(){
// alert("Cek");	
 
 if( jQuery("#chkEmptask").is(':checked')==true){
    jQuery("#chkTeamtask").prop("checked",false);
   // processGridnew(url,filterString,"grdAPComp","grdAPCompPager"); 
    processGridnew("actionplanCompletion_input.api","?q=2","grdAPComp","grdAPCompPager");
 }	
});

jQuery("#chkTeamtask").click(function(){
	var filterString="";
	  var flid=jQuery("#hdnFlid").val();
    //  alert(flid);
	if( jQuery("#chkTeamtask").is(':checked')==true){
	    jQuery("#chkEmptask").prop("checked",false);
	    var Teamchk=jQuery("#chkTeamtask").is(':checked');
	    Teamchk="Y";
	  //  alert(Teamchk);
	    //processGridnew("actionplanCompletion_input.api",filterString+"flid="+flid+"Teamchk="+Teamchk,"grdAPComp","grdAPCompPager"); 
	    processGridnew("actionplanCompletion_input.api","?q=2&flid="+flid+"&Teamchk="+Teamchk,"grdAPComp","grdAPCompPager");
	}	
});




		
	</script>
<form id="frmAPlanComp">	
<table>	
<tr> 	             
 	             <td>
				 <div style="margin-left:700px; margin-top:20px;">			  
				 <input type="checkbox"  id="chkEmptask" name="chkEmptask"  value=""/>
				 <label class="lbl" id="lblEmptask">Employee Task</label>	
				  </div>
				</td>
			</tr>
	<tr> 	             
     <td>
				 <div style="margin-left:830px; margin-top:-19px;">			  
				 <input type="checkbox"  id="chkTeamtask" name="chkTeamtask"  value=""/>
				 <label class="lbl" id="lblTeamtask">Team Task</label>	
				  </div>
				</td>
			</tr> 		 
</table>
	<div id="wrapperRpt" style="margin-top:4px;margin-left:60px" >
	<table id="grdAPComp" ></table>
	<div id="grdAPCompPager"></div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdnFlid" name="hdnFlid" value="${requestScope.flid}">
	<input type="hidden" id="hdnUserId" name="hdnUserId" value="${requestScope.rolename}">
</form>
	 	