
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {				
			  initialiseForm('frmMsgBrdNw');				
			 jQuery('#submitForm').val('frmMsgBrdNw');
			 formatDateBox('dte_from','dd-MMM-yyyy');
			 formatDateBox('dte_till','dd-MMM-yyyy');		
				var url = jQuery('#hiddenUrl').val();
				viewGrid(url,"q=2");
				fillComboBox("frmServiceBooking","cmbPillar","pillar_combo.serv?");
				if(jQuery(hdnisfilter).val()=='Y'){
					//jQuery('#chbisvalidityreq').val('on');
					jQuery("#hdnisfilter").prop( "checked", true );
					enableFields('dte_from');
					enableFields('dte_till');	
		  	  	}
		  	else {
		  		jQuery("#chbisvalidityreq").prop( "checked", false );
		  		disableField('frmServiceBooking', 'dte_from');
				disableField('frmServiceBooking', 'dte_till');
		  	  	}
			});
	
	jQuery('#chbisfilter').click(function() {
		if(jQuery('#chbisfilter').is(':checked') == true){
			//jQuery("#datediv").show();
			enableFields('dte_from');
			enableFields('dte_till');
			var values='Y';
			setFieldValue("hdnisfilter",values);
			fillWithCurrentDate('dte_from');
			fillWithCurrentDate('dte_till');
			
			}
		else {		
			disableField('frmServiceBooking', 'dte_from');
			disableField('frmServiceBooking', 'dte_till');
			var values='N';
			setFieldValue("hdnisfilter",values);
			jQuery('dte_from').datebox('clear');
			jQuery('dte_till').datebox('clear');
			}
	});
	
	 jQuery( "#btnfilter" ).click( function() {
		 var url = jQuery('#hiddenUrl').val();
		 var fromdate=getFieldValue("dte_from");
			var todate=getFieldValue("dte_till");
			var pillar=getFieldValue("cmbPillar");
			var values='Y';
			//alert(pillar);
			setFieldValue("hdnreq",values);
		//alert(jQuery(hdnreq).val());
		if(jQuery('#chbisfilter').is(':checked') == true){
			//alert("YY");
			var data="q=2";
			data+="&pillar=";
			data+=pillar;
			data+="&fromdate=";
			data+=fromdate;
			data+="&todate=";
			data+=todate;
			data+="&datewise=Y&req=Y";
			//alert(data);
			viewGrid(url,data);
			 }
		 else{
			 var data="q=2";
				data+="&pillar=";
				data+=pillar;
				data+="&datewise=N&req=Y";
				//alert(data);
				viewGrid(url,data);
			 }
		 
	 });
	function viewGrid(url,dataString)
	{
		//alert(2);
		processGridnew(url,dataString,"secviceBookingSmryGrd","pager","","","","impVscomp_loadComplete");
		//fillComboBox("frmServiceBooking","cmbPillar","pillar_combo.serv");	
		return true;
	}
	
	function impVscomp_loadComplete()
	{
		//setDrillDownHeader("CH1-0","impVscomp","KEYFIELD2");
		setTotalRowCss('secviceBookingSmryGrd');
	}

</script>

<form id="frmServiceBooking" name="frmServiceBooking">
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
	<div class="easyui-paddingbfpx">
	<table>
	<tr>
	<td> <label>Pillar</label></td>
	<td> <label>    </label></td>
	<td> <label> From</label></td>
	<td> <label> To</label></td>
	</tr>
	<tr>
	<td><input class="easyui-combobox" id="cmbPillar" name="cmbPillar"  style="width: 200px;" /></td>
	<td><input type="checkbox" id="chbisfilter" value="Y" /></td>
	<td><input class="easyui-paddingbfpx" id="dte_from"  name="dte_from" clear="false" class="easyui-datebox" value="" style="width: 100px;" /></td>
	<td><input class="easyui-paddingbfpx" id="dte_till"  name="dte_till" clear="false" class="easyui-datebox" value="" style="width: 100px;" /></td>
	<td ><input type="button" id="btnfilter" name="btnfilter" class="easyui-button" value="Filter"/></td>
	</tr>
	</table>
	</div> 
	<!--<input id="cmbLocation" type="easy"  	/>-->	
	<table id='secviceBookingSmryGrd'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" id="hdnisfilter" name="hdnisfilter" value=""/>
<input type="hidden" id="hdnreq" name="hdnreq" value=""/>
</form>			
	
