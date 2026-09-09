<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			jQuery('#submitForm').val('frmHoliday');
			initialiseForm('frmHoliday'); 	
			jQuery("#day").hide();
			fillComboBox("frmHoliday","cmbHolmFactory","location.commonFilter" );
			fillComboBox("frmHoliday","cmbBeanYear","Combo_Year.holiday");
			formatDateBox('dteHolmDate','dd-MMM-yyyy');			
			jQuery("#rdoDay").attr('checked',true);			
			formatDateBox('dteHolmDate','dd-MMM-yyyy');
			
			fillWithCurrentDate("dteHolmDate");
			var currentTime = getServerDateTime();
			var year = currentTime.getFullYear();
			jQuery("#cmbBeanYear").combobox("setValue",year);
			
			var dataString = "?q=2";
			viewGrid("holiday_input.holiday",dataString);
		});
		
		jQuery("#rdoHolmHolidayflag").click(function(){
			jQuery("#date").hide();
 			jQuery("#day").show();
 			jQuery('#lblName').attr("class","mndlbl");
 			jQuery('#lblDesc').attr("class","mndlbl");
 			readOnlyFields("txtHolmDescription");
 			disableField("frmHoliday", "txtHolmName");
 			jQuery("#txtHolmDescription").val('');
 			jQuery("#txtHolmName").val('');
 			jQuery("#cmbHolmFactory").combobox("setValue",''); 			
 			//jQuery("#dteHolmDate").datebox("setValue",'');
 			fillWithCurrentDate("dteHolmDate");
 			var currentTime = getServerDateTime();
			var year = currentTime.getFullYear();
			//jQuery("#cmbBeanYear").combobox("setValue",year);
 			
 			clearValidationErrorMessages("frmHoliday","txtHolmName");
 			clearValidationErrorMessages("frmHoliday","txtHolmDescription");
 			clearValidationErrorMessages("frmHoliday","cmbHolmFactory");
 			clearValidationErrorMessages("frmHoliday","dteHolmDate");

 			
			var dataString = "&holidaySelect=holiday&holidayFlag=W";
			viewGridData(dataString);
 			
 		});
		
		jQuery("#rdoDay").click(function(){
			jQuery("#day").hide();
 			jQuery("#date").show(); 	
 			enableFields("txtHolmDescription");
 			//enableFormFields("frmHoliday", "txtHolmName");
 			enableFields("txtHolmName");
 			jQuery('#lblName').attr("class","mandatory-lbl");
 			jQuery('#lblDesc').attr("class","mandatory-lbl");
 			clearValidationErrorMessages("frmHoliday","err_cmbHolmFactory");
 			jQuery("#txtHolmDescription").val('');
 			jQuery("#txtHolmName").val('');
 			jQuery("#cmbHolmFactory").combobox("setValue",'');
 			var dataString = "&holidaySelect=holiday&holidayFlag=H";
 			
 			viewGridData(dataString);
 		});
		
		jQuery("#btnInsert").click(function(){		
			if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="W"){
				var year = jQuery("#cmbBeanYear").combobox('getValue');
				var dayVal = jQuery("#cboBeanDay").val();				
				if(dayVal==null || dayVal.trim()=='')
					alert("Select Day");	
				else if(year==null || year.trim()=='')
					alert("Select Year");
				else
					saveForm("frmHoliday","holiday_save.holiday");
			}
			if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="H"){
				saveForm("frmHoliday","holiday_save.holiday");
			}
		});
		
		jQuery("#btnView").click(function(){
			var factId = jQuery('#cmbHolmFactory').combobox('getValue');
			var year = jQuery('#cmbBeanYear').combobox('getValue');
			var dataString = "?q=2&keyId="+factId+"&year="+year;			
	
			if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="W")
				dataString += "&holidayFlag=W";
			else
				dataString += "&holidayFlag=H";
			
			viewGrid("holiday_input.holiday",dataString) ;		
		});
		function viewGridData(filterString)
		{
			var factId = jQuery('#cmbHolmFactory').combobox('getValue');
			var year = jQuery('#cmbBeanYear').combobox('getValue');
			var dataString = "?q=2&keyId="+factId+"&year="+year;
			dataString += filterString;			
			viewGrid("holiday_input.holiday",dataString) ;	
		}
		function frmHoliday_successsCallback(result)
		{
			/*//alert(Object.keys(result.successData));
			//alert(result.successData.HolmKeyid);
			var flag=result.flag;
		
			var dataString = "?q=2&flag="+flag;
			viewGrid("holiday_recall.holiday",dataString);
			//viewGrid("holiday_input.holiday",dataString);*/
			//jQuery("#rdoDay").attr('checked',true);
			//jQuery("#date").show(); 	
			fillWithCurrentDate("dteHolmDate");
			jQuery("#txtHolmDescription").val("");
			if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="H")
				jQuery("#cmbHolmFactory").combobox("setValue",''); 	
			jQuery("#dteHolmDate").datebox("setValue",'');
			var factId = jQuery('#cmbHolmFactory').combobox('getValue');
			var year = jQuery('#cmbBeanYear').combobox('getValue');
			var dataString = "?q=2&keyId="+factId+"&year="+year;		
			if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="W")
				dataString += "&holidayFlag=W";
			else
				dataString += "&holidayFlag=H";	
			
			viewGrid("holiday_input.holiday",dataString) ;	
			
			//jQuery("#list").trigger("reloadGrid");			
		}
		 
		function frmHoliday_exceptionCallback(result)
		{
			
			alert(result.tpmException);
		}
		
		 function viewGrid(url,filterString)
		 {	
		 		processGridnew(url,filterString,"HolidayList","HolidayPager","","list_doubleClickGrid");
		 		return true;
		 }
		 
		function list_doubleClickGrid(id)
		{			
			var filterString = "keyId="+id;
			processAjaxCalls("holiday_recall.holiday",filterString,"recall_successCalBack","recall_errCalBack");
		}
	function recall_successCalBack(result)
	{			
		jQuery("#cmbHolmFactory").combobox("setValue",result.holidayData.HolmFactory);
		jQuery("#txtHolmName").val(result.holidayData.HolmName);
		jQuery("#txtHolmDescription").val(result.holidayData.HolmDescription);
		var date = result.holidayData.HolmDate;
		date = date.substring(0,11);
		var year = date.substring(7,11);
		jQuery("#dteHolmDate").datebox("setValue",date);
		jQuery("#txtHolmKeyid").val(result.holidayData.HolmKeyid);
		
		jQuery("#cmbBeanYear").combobox("setValue",year);
		//jQuery("#cboBeanDay option[text=" + result.holidayData.HolmDescription +"]").attr("selected","selected") ;
		jQuery("#cboBeanDay").val(result.holidayData.HolmDescription);
		//jQuery('#cboBeanDay option[value=c]').attr('selected', 'selected');

		//alert(year);
		var flag = result.holidayData.HolmHolidayflag;
		if(flag == "H"){
			jQuery("#rdoDay").attr('checked',true);
			jQuery("#day").hide();
 			jQuery("#date").show(); 
 			
 			enableFields("txtHolmDescription");
 			enableFields("txtHolmName");
 			jQuery('#lblName').attr("class","mandatory-lbl");
 			jQuery('#lblDesc').attr("class","mandatory-lbl");
		}
		else if(flag == "W"){
			jQuery("#rdoHolmHolidayflag").attr('checked',true);
			jQuery("#day").show();
 			jQuery("#date").hide(); 
 			
 			jQuery('#lblName').attr("class","mndlbl");
 			jQuery('#lblDesc').attr("class","mndlbl");
 			readOnlyFields("txtHolmDescription");
 			disableField("frmHoliday", "txtHolmName");
 			jQuery("#txtHolmDescription").val('');
 			jQuery("#txtHolmName").val('');
		}
	}

	function  frmHolidaycmbHolmFactory_onSelect(record){

		var factId = jQuery('#cmbHolmFactory').combobox('getValue');
		var year = jQuery('#cmbBeanYear').combobox('getValue');
		var dataString = "?q=2&keyId="+factId+"&year="+year;			

		if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="W")
			dataString += "&holidayFlag=W";
		else
			dataString += "&holidayFlag=H";
		
		viewGrid("holiday_input.holiday",dataString) ;	
	}
/*	function frmHoliday_beforeSubmit()
	{       
		return false;
	}*/
	
	jQuery("#btnDelete").click(function(){
		
		if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="W"){
			var dayVal = jQuery("#cboBeanDay").val();	
			if(dayVal==null || dayVal.trim()=='')
				alert("Select Day");
			else 
				deleteRecord("frmHoliday","holiday_delete.holiday");
				//saveForm("frmHoliday","holiday_delete.holiday");
		}
		if(jQuery('input[name=rdoHolmHolidayflag]:checked').val()=="H"){
			deleteRecord("frmHoliday","holiday_delete.holiday");//saveForm("frmHoliday","holiday_delete.holiday");
		}
		//processAjaxCalls("holiday_delete.holiday","","delete_successCalBack","delete_errCalBack");
	});

	 function frmHoliday_beforeDelete(){			
			if(! confirm("Do you want to delete this record?")){
				return false;
			}
		}
		
	function frmHoliday_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		jQuery("#HolidayList").trigger("reloadGrid");
	}
</script>
<form id="frmHoliday">
	<div id="wrapper" style="width:107%">
	<div class="main-cntborder easyui-paddingbtpx" style="height:90%;\0\width:100%;">
	<table style="margin-left:-8px;margin-left:-23%\9;">
		<tr valign="top" style="padding-top: 10px;">
			<td >
				<div align="left" style="padding-left: 35px;"><label>For the Year </label> </div>	
				<div align="left" style="padding-left: 35px;padding-left:35px\9;">
					<select id="cmbBeanYear" name="cmbBeanYear" style="width: 155px;"></select>		
				</div>
			</td>
			<td >
				<div style="padding-left: 70px;"> <label class="mandatory-lbl">Unit</label></div> 
				<div style="padding-left: 70px;"> 
				<input id="cmbHolmFactory"	name="cmbHolmFactory" class="easyui-combobox" style="width: 255px;"	value="">				
				</div> 
			</td>
			<td>
				<div style="padding-left: 50px;"> <label id="lblName" class="mandatory-lbl">Name</label></div>
				<div style="padding-left: 50px;">
				 <input type="text" class="easyui-text" id="txtHolmName" name="txtHolmName" maxlength="15" style="width: 255px; height: 21px;" value="">
				</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="1">
				<div align="left" style="padding-left: 35px;">
					<input type="radio" value="W" id="rdoHolmHolidayflag" name="rdoHolmHolidayflag"> <label>Weekly Off</label>
					<input type="radio" value="H" id="rdoDay" name="rdoHolmHolidayflag"><label> Holiday</label> 
				</div>
				<div id="date" style="padding-left: 35px;">
						<div><label>Date</label></div>
						<div><input id="dteHolmDate" name="dteHolmDate"	class="easyui-datebox" clear="false" style="width: 155px;" value="" /></div>
					</div>
			
					<div id="day" style="padding-left: 35px;">
						<div><label>Day</label></div>
							<div>
								<select id="cboBeanDay" name="cboBeanDay" style="width: 155px;">
									<option value=""></option>
									<option value="SUNDAY">Sunday</option>
									<option value="MONDAY">Monday</option>
									<option value="TUESDAY">Tuesday</option>
									<option value="WEDNESDAY">Wednesday</option>
									<option value="THURSDAY">Thursday</option>
									<option value="FRIDAY">Friday</option>
									<option value="SATURDAY">Saturday</option>
								</select>
							</div>
					</div>
				
				
			</td>
			<td>
				<div><span style="padding-left: 70px;"> <label id="lblDesc" class="mandatory-lbl">Descprition</label></span></div>
				<div style="padding-left: 70px;">
					<textarea  rows="3"  cols="28" maxlength="175" id="txtHolmDescription" name="txtHolmDescription" style=" height : 37px; width : 249px;\0\width:258px;"></textarea></div>
					
			</td>
			
			<td>
				<span style="padding-left: 50px; top: 5px;"> 
				 
				<input type="button" class="easyui-button"	id="btnInsert" name="btnInsert" value="Save" /> 
				<input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" />
				<input type="button" class="easyui-button" id="btnView" name="btnView" value="View" style="display:none;"/> 
				</span>
			</td>
			
		</tr>		
	</table>
	<div style="margin-top: 20px;">
    <table id="HolidayList" style="width: 100%">
	<tr>	<td />	</tr>
	</table>
	<div id="HolidayPager"></div>
	</div></div>
</div>

		<input type="hidden"  id="txtHolmKeyid" name="txtHolmKeyid" /> 
</form>


