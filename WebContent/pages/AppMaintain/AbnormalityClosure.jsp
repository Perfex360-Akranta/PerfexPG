<script>
jQuery(document).ready(function(){
	 initialiseForm('frmAbnClosure');
	 var url = jQuery('#hiddenUrl').val();
	 formatDateBox('dteFromdate','dd-MMM-yyyy');
	 formatDateBox('dteTodate','dd-MMM-yyyy');
	 fillComboBox("frmAbnClosure","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");
	 fillComboBox("frmActionPlanClosure","cmbDMT","sectionCombo.commonFilter");
	 fillComboBox("frmActionPlanClosure","cmbJH","cellCombo.commonFilter");
	 viewGrid(url,"q=2");
	});


function escapeJsonStr(str) {
    return str.replace(/\\/g, '\\\\').replace(/"/g, '\\"');
}

function getSelectedAbnClosureJson() {
    var rowIds = jQuery("#AbnClosureGrd").jqGrid('getGridParam', 'selarrrow');

    if (!rowIds || rowIds.length == 0) {
        alert("Select at least one row to close");
        return null;
    }

    var jsonArr = '[';

    for (var i = 0; i < rowIds.length; i++) {
        var rowId = rowIds[i];

        var AbnmKeyid = jQuery("#AbnClosureGrd").jqGrid('getCell', rowId, "AbnmKeyid");
        var Status = getFieldValue("cmbAbnmStatus_AbnClosureGrd_" + rowId);
        var CounterMeasure = getFieldValue("AbnmCountermeasure_AbnClosureGrd_" + rowId).trim();

        if (CounterMeasure.length == 0) {
            alert("Enter the CounterMeasure (Row: " + AbnmKeyid + ")");
            return null;
        }

        var CompletedDate = getFieldValue("dteAbnmWoendtime_AbnClosureGrd_" + rowId);
        if (CompletedDate.length == 0) {
            alert("Select the CompletedDate (Row: " + AbnmKeyid + ")");
            return null;
        }

        var CompletedBy = getFieldValue("cmbAbnmCompletedby_AbnClosureGrd_" + rowId).trim();
        if (CompletedBy.length == 0) {
            alert("Select the CompletedBy (Row: " + AbnmKeyid + ")");
            return null;
        }

        jsonArr += '{';
        jsonArr += '"keyid":"' + AbnmKeyid + '",';
        jsonArr += '"status":"' + Status + '",';
        jsonArr += '"countermeasure":"' + escapeJsonStr(CounterMeasure) + '",';
        jsonArr += '"woendtime":"' + CompletedDate + '",';
        jsonArr += '"completedby":"' + escapeJsonStr(CompletedBy) + '"';
        jsonArr += '},';
    }

    jsonArr = jsonArr.slice(0, -1) + ']';
    return jsonArr;
}

function getAbnClosureJson(rowId) {
    

    var jsonArr = '[';


        var AbnmKeyid = jQuery("#AbnClosureGrd").jqGrid('getCell', rowId, "AbnmKeyid");
        var Status = getFieldValue("cmbAbnmStatus_AbnClosureGrd_" + rowId);
        var CounterMeasure = getFieldValue("AbnmCountermeasure_AbnClosureGrd_" + rowId).trim();

        if (CounterMeasure.length == 0) {
            alert("Enter the CounterMeasure (Row: " + AbnmKeyid + ")");
            return null;
        }

        var CompletedDate = getFieldValue("dteAbnmWoendtime_AbnClosureGrd_" + rowId);
        if (CompletedDate.length == 0) {
            alert("Select the CompletedDate (Row: " + AbnmKeyid + ")");
            return null;
        }

        var CompletedBy = getFieldValue("cmbAbnmCompletedby_AbnClosureGrd_" + rowId).trim();
        if (CompletedBy.length == 0) {
            alert("Select the CompletedBy (Row: " + AbnmKeyid + ")");
            return null;
        }

        jsonArr += '{';
        jsonArr += '"keyid":"' + AbnmKeyid + '",';
        jsonArr += '"status":"' + Status + '",';
        jsonArr += '"countermeasure":"' + escapeJsonStr(CounterMeasure) + '",';
        jsonArr += '"woendtime":"' + CompletedDate + '",';
        jsonArr += '"completedby":"' + escapeJsonStr(CompletedBy) + '"';
        jsonArr += '}';

    jsonArr +=   ']';
    return jsonArr;
}

jQuery("#btnMultipleClose").click(function(){

    var abnClosureJson = getSelectedAbnClosureJson();

    if (abnClosureJson == null) {
        return false;
    }

    processAjaxCalls(
        "AbnClosure_Update.appm",
        "AbnClosureDetails=" + encodeURIComponent(abnClosureJson),
        'update_successCallBack',
        'remove_errorCallBack'
    );
});

function AbnClosureGrdbtnAbnClosure_onClick(result){	
    var rowid=result.rowId;
    var abnClosureJson = getAbnClosureJson(rowid);

    if (abnClosureJson == null) {
        return false;
    }

    processAjaxCalls(
        "AbnClosure_Update.appm",
        "AbnClosureDetails=" + encodeURIComponent(abnClosureJson),
        'update_successCallBack',
        'remove_errorCallBack'
    );

	}

/* function AbnClosureGrdbtnAbnClosure_onClick(result){	
    var rowid=result.rowId;
	var btnid=result.btnId;
    var AbnmKeyid=jQuery("#AbnClosureGrd").jqGrid('getCell',rowid,"AbnmKeyid");
    var Status=getFieldValue("cmbAbnmStatus_AbnClosureGrd_"+rowid);
    //  alert(Status);
    var CounterMeasure=getFieldValue("AbnmCountermeasure_AbnClosureGrd_"+rowid).trim();
   // alert(CounterMeasure.length);
   if(CounterMeasure.length==0){
	   alert("Enter the CounterMeasure");
	   return false;
   } 
    var CompletedDate=getFieldValue("dteAbnmWoendtime_AbnClosureGrd_"+rowid);
//   alert("Date:"+CompletedDate.length);
   if(CompletedDate.length==0){
	   alert("Select the CompletedDate");
	    return false;
   }
	var CompletedBy=getFieldValue("cmbAbnmCompletedby_AbnClosureGrd_"+rowid).trim();
	//alert(CompletedBy.length);
	if(CompletedBy.length==0){
		alert("Select the CompletedBy");
		return false;
	}
	
	processAjaxCalls("AbnClosure_Update.appm","AbnmKeyid="+AbnmKeyid+"&Status="+Status+"&CounterMeasure="+CounterMeasure+"&CompletedDate="+CompletedDate+"&CompletedBy="+CompletedBy,'update_successCallBack','remove_errorCallBack');

	} */
	
	
/* function AbnClosureGrd_selectRow(rowId){
	var one = jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("getValue");
	alert("date value"+one)
	var existDate = one;

	jQuery("#cmbAbnmStatus_AbnClosureGrd_"+rowId).change(function (){
		
		
		if( this.value != "C")
		{
			jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("clear");
			disableField("frmAbnClosure", "dteAbnmWoendtime_AbnClosureGrd_"+rowId);		
		}
		else{
			
			enableFields("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
			fillWithCurrentDate("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
		}
	});
	jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox({
		
		onSelect:function(value){
			var compdate = jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("getValue");
			
			var currentDate = getCurrentDate();
			
			if( compareDate(compdate,currentDate) < 0)
			{
				alert("Completed date can not be greater than current date");
				fillWithCurrentDate("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
				return false;
			}
			if (compareDate(compdate, existDate) > 0) {
	            alert("Completed date cannot be less than the existing date " + existDate);
	            jQuery("#dteAbnmWoendtime_AbnClosureGrd_" + rowId)
	                .datebox("setValue", existDate);
	            return false;
	        }
		} 
	});
		} */

		
		function AbnClosureGrd_selectRow(rowId){

			var statusFieldId = "cmbAbnmStatus_AbnClosureGrd_" + rowId;

			setTimeout(function(){
				var el = jQuery("#" + statusFieldId);

				if (el.data('combobox')) {
					// it IS an initialized EasyUI combobox
					el.combobox('setValue', 'C');
				} else {
					// plain native <select> (or not yet initialized) - set directly
					el.val('C').trigger('change');
				}
			}, 100);

			var one = jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("getValue");
			var existDate = one;

			jQuery("#cmbAbnmStatus_AbnClosureGrd_"+rowId).change(function (){
				
				if( this.value != "C")
				{
					jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("clear");
					disableField("frmAbnClosure", "dteAbnmWoendtime_AbnClosureGrd_"+rowId);		
				}
				else{
					enableFields("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
					fillWithCurrentDate("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
				}
			});
			jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox({
				
				onSelect:function(value){
					var compdate = jQuery("#dteAbnmWoendtime_AbnClosureGrd_"+rowId).datebox("getValue");
					var currentDate = getCurrentDate();
					
					if( compareDate(compdate,currentDate) < 0)
					{
						alert("Completed date can not be greater than current date");
						fillWithCurrentDate("dteAbnmWoendtime_AbnClosureGrd_"+rowId);
						return false;
					}
					if (compareDate(compdate, existDate) > 0) {
			            alert("Completed date cannot be less than the existing date " + existDate);
			            jQuery("#dteAbnmWoendtime_AbnClosureGrd_" + rowId)
			                .datebox("setValue", existDate);
			            return false;
			        }
				} 
			});
		}

		
function update_successCallBack(result){
	alert(result.successData.msg);
	jQuery('#AbnClosureGrd').trigger("reloadGrid");
}

function viewGrid(url,dataString){  
	processGridnew(url, dataString, "AbnClosureGrd", "Pager", "", "");
	return true;
}

jQuery("#btnview").click(function(){
	
	var fromDate=jQuery("#dteFromdate").datebox("getValue");
	var toDate=jQuery("#dteTodate").datebox("getValue");
	var TagClassId=jQuery("#cmbAbnmTagclassid").combobox("getValue");
	var DMT=jQuery("#cmbDMT").combobox("getValue");
	var JH=jQuery("#cmbJH").combobox("getValue");
	if(fromDate.length==0){
		alert("Select From Date");
		return false;
	}
	if(toDate.length==0){
		alert("Select To Date");
		return false;
	}
	processGridnew("AbnormalityClosure_input.appm","?q=2&fromDate="+fromDate+"&toDate="+toDate+"&TagClassId="+TagClassId+"&DMT="+DMT+"&JH="+JH,"AbnClosureGrd","Pager","","");
});


jQuery("#btnRefresh").click(function(){
	 var url = jQuery('#hiddenUrl').val();
	processGridnew(url,"q=2","AbnClosureGrd", "Pager", "", "");
	jQuery("#dteFromdate").datebox("clear");
	jQuery("#dteTodate").datebox("clear");
    jQuery("#cmbAbnmTagclassid").combobox("clear");
	jQuery("#cmbDMT").combobox("clear");
	jQuery("#cmbJH").combobox("clear");
});



</script>
<form id="frmAbnClosure">
<div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tbody>
		<tr>
		
		
	<td>
		<div>
	<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>                       
	                 </div> 
			    <div style="margin-left:1px;margin-bottom:1px;">
			    
		        <input id="cmbDMT" name="cmbDMT" type="text" class="easyui-combobox" style="width: 212px; /* height: 24px; */ margin-left: 0px;" value="">
			        
			  </div>
			 </td>
			 
			 
		<td>
	<div>
	<label style="margin-left:20px;margin-bottom: 1px;">JH</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom:1px;">
			    
		        <input id="cmbJH" name="cmbJH" type="text" class="easyui-combobox" style="width: 212px; /* height: 24px; */ margin-left: 0px;" value="">
			        
			        </div>
			 </td>
	
		
		
			 <td>
			 <div>
	       <label style="margin-left:20px;margin-bottom: 1px;">From Date</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom: 5px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 87px; /* height: 24px */" id="dteFromdate" name="dteFromdate" value=""/>
			        </div>
			 </td>
			 <td>
			    <label style="margin-left:15px;margin-bottom: 1px;">To Date</label>  
			    <div style="margin-left:15px;margin-bottom: 5px;">
			         <input class="easyui-text" style=" margin-top: 0px; width : 87px; /* height: 24px */" id="dteTodate" name="dteTodate" value=""/>
			          </div>
			  </td>
			  
			   <td>
			    <label style="margin-left:25px;margin-bottom: 1px;">Type</label>  
			    <div style="margin-left:25px;margin-bottom: 5px;">
  		<input id="cmbAbnmTagclassid" name="cmbAbnmTagclassid" tabindex="14" class="easyui-combobox"  style="width:120px;" value=""/>
  

			          </div>
			  </td>
			  
			  
			  
			  
			   <td>
			    <div style="margin-left:10px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;">
		  </div>
			  </td>
			  <td>
			    <div style="margin-left:20px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="Refresh" id="btnRefresh" style="height: 24px; width : 87px;">
		  </div>
			  </td>
			  <td>
    <div style="margin-left:20px;margin-bottom:-10px;">
         <input type="button" class="easyui-button" value="Multiple Close" id="btnMultipleClose" style="height: 24px; width : 110px;">
    </div>
</td>
		  </tr>
		 </table>
<table id="AbnClosureGrd"></table>
<div id="Pager"></div>
</div>
</form>