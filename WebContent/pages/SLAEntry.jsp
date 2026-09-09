<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<style type="text/css">
	.exittargtlvl{
		color:red;
	}
</style>
<script>
	jQuery(document).ready(
			function() {
				//alert(1);
				initialiseForm('frmSLAEntry');
				jQuery('#submitForm').val('frmSLAEntry');
				
				
				fillComboBox("frmSLAEntry", "cmbSlamFromdptid", "sectionCombo.commonFilter");
				fillComboBox("frmSLAEntry", "cmbSlamTodptid", "sectionCombo.commonFilter");				
				formatDateBox('dteFrom','dd-MM-yyyy');	
				//formatDateBox('dteTo','dd-MM-yyyy');
				/*jQuery('#dteFrom').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });*/
				
				var url = jQuery("#hiddenUrl").val();
				var hdnfrom = jQuery("#hdnfrom").val();
				//alert("from="+hdnfrom);
				var gridUrl="servicelevelEntry_input.slam";				
				var frmdmt = jQuery("#txtfrmdmt").val();
				var todmt = jQuery("#txttodmt").val();
				var month = jQuery("#txtmonth").val();
				//alert("month="+month);
				var sladid = jQuery("#txtsladid").val();
				//alert("sladid="+sladid);
				var slemid = jQuery("#txtslemid").val();
				//alert("slemid="+slemid);
				var freq = jQuery("#txtfreq").val();
				//alert("freq"+freq);
				var slmkeyid = jQuery("#txtslmkeyid").val();
				//alert("freq="+freq);		


				
				gridUrl = url.substring(0,url.indexOf("?"));
				
				if(gridUrl.length > 21)
				{ 
					viewGrid(gridUrl,"?q=2&from="+hdnfrom+"&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq+"&slmkeyid="+slmkeyid);
				}
				else 
				{   
					viewGrid(url,"?q=2&from="+hdnfrom+"&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq+"&slmkeyid="+slmkeyid);
				}
				
				

				setTimeout(function() {fillComboBox("frmSLAEntry","cmbApprovedby","employee.commonFilter"); },550);
				
				/*jQuery('#dteFrom').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				});  */
 
				fileManagerPopUp("","SLA","frmSLAEntry","btnfilemgr","SlaFilemgr");


				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					//alert("Read From File");
					var frmdmt = getFieldValue('txtfrmdmt');
					var todmt = getFieldValue('txttodmt');
					var month = getFieldValue('txtmonth');
				

					
					window.open("servicelevelEntryDtl_getExcel.slam?frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month,"Excel View");
						
					//processAjaxCalls("servicelevelEntryDtl_getExcel.slam?frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month, "frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month, "", "", "", "new");					
				});
				
				
				jQuery('#divHours').hide();				
				jQuery('#divLblMin').hide();
				jQuery('#divLblMinVal').hide();
				jQuery("#btnCapaEntry").click(function()
		    	{
					var CapaMasterrefid =getFieldValue('txtSlemKeyid');
					var CapaRefdocid =getFieldValue('txtSlemSladid');
					var CapaRefdoctype ="SLA";
					var CapaActionplanid ="-";
					var CapaProblem =escape(getFieldValue('txtproblem')); 					
					var CapaFlid ="-";					

					var selvalue = getFieldValue("hdnselvalue");
					var min = jQuery("#min").html();
					var max = jQuery("#max").html();

					var pmax = parseInt(max,10);
					var pmin = parseInt(min,10);
					var pselvalue = parseInt(selvalue,10);
					
					if(pselvalue > pmax || pselvalue < pmin )
					{		
						if(CapaMasterrefid != null && CapaMasterrefid !=  undefined )
						{  
							var querstring = "CapaMasterrefid="+CapaMasterrefid+"&CapaRefdocid="+CapaRefdocid+"&CapaRefdoctype="+CapaRefdoctype+"&CapaActionplanid="+CapaActionplanid+"&CapaFlid="+CapaFlid+"&CapaProblem="+CapaProblem;
							if (CapaMasterrefid.trim().length > 5)
							{
								LoadPopUp("CAPA","CAPAEntry_input.capa?"+querstring,true,"55%","74%","20%","20%","","CAPA","",true);
								//void LoadPopUp(any divId, any url, any isModel, any width, any height, any top, any left, any loadpopUpSuccessCallBack, any title, any isInside, any toolBar, any needClose, any classname)
								
							}
						} 	
					}					
					else
					{
						alert("SLA value is with in target");
					}

					
					
					
		    							
		    	});
		    	//alert("frmdmt" + frmdmt);
		    	//alert("todmt" + todmt);
				setFieldValue("cmbSlamFromdptid",frmdmt,"frmSLAEntry");
				setFieldValue("cmbSlamTodptid",todmt,"frmSLAEntry");
				setFieldValue("dteFrom",month,"frmSLAEntry");
			});


	/*function addHourDiv(times) {
		var hrHtml="";
		
		//hrHtml= hrHtml + " <table><tr> <td width=100> <label id='lblHourWeek' name='lblHourWeek' style='padding-left:50%;' class='mandatory-lbl'>Hour</label> </td> ";
		for (var i=1;i<=times;i++) {
			//hrHtml= hrHtml + " <td height=15> <span style='padding-left:10px;'><label>"+i+"</label></span></td> ";
		}
		hrHtml= hrHtml + " </tr> ";
		hrHtml= hrHtml + " <tr> <td width=100>  </td>";
		
		for (var i=1;i<=times;i++) {
			//hrHtml= hrHtml + " <td height=15><span style='padding-left:1px;'><input id='txtsledD"+i+"' name='txtsledD"+i+"' type='text' value='' maxlength='6' ";
			//hrHtml= hrHtml + " style='border:1px solid black;width: 30px; text-align:right;'  ></span></td> ";
		}
		//hrHtml= hrHtml + "<td> <input style='height:20px;' type='button' id='btnHourInsert' name='btnHourInsert' class='easyui-button'  value='Save' onClick=saveHour();> </td> ";
		hrHtml= hrHtml + " </tr></table> ";
		
		jQuery('#divHours').html(hrHtml);
	}*/
	function addHourDiv(times) {
	var frequency = getFieldValue('hdnfrequency');
    //alert("frequency="+frequency);
	if (frequency=="Hourly"  || frequency=="Shift" ) {
		//alert("into the condition frequency="+frequency);
		var hrHtml="";
		
		hrHtml= hrHtml + " <table><tr> <td width=100> <label id='lblHourWeek' name='lblHourWeek' style='padding-left:50%;' class='mandatory-lbl'>Hour</label> </td> ";
		for (var i=1;i<=times;i++) {
			hrHtml= hrHtml + " <td height=15> <span style='padding-left:10px;'><label>"+i+"</label></span></td> ";
		}
		hrHtml= hrHtml + " </tr> ";
		hrHtml= hrHtml + " <tr> <td width=100>  </td>";
		
		for (var i=1;i<=times;i++) {
			hrHtml= hrHtml + " <td height=15><span style='padding-left:1px;'><input id='txtSlahH"+i+"' name='txtSlahH"+i+"' type='text' value='' maxlength='6' ";
			hrHtml= hrHtml + " style='border:1px solid black;width: 30px; text-align:right;'  ></span></td> ";
		}
		hrHtml= hrHtml + "<td> <input style='height:20px;' type='button' id='btnHourInsert' name='btnHourInsert' class='easyui-button'  value='Save' onClick=saveHour();> </td> ";
		hrHtml= hrHtml + " </tr></table> ";
		
		jQuery('#divHours').html(hrHtml);

	}
	//var Valuedate  =  ids[1] - 10 + '-' + getFieldValue('dteFrom');
	//alert("Valuedate"+Valuedate);
	var DocDate = getFieldValue("txtslemDate");
	var slemid = jQuery("#txtslemid").val();
	//alert("slemid="+slemid);
	processAjaxCalls("SLAhourlyrecal_hourly.slam","Date="+DocDate+"&slemid="+slemid, "slahourlyrecalsuccesssCallback", "", "", "");
	}
	
	function saveHour() {
		jQuery('#divHours').hide();
		
		var selId = jQuery('#hdnSelHourId').val();
		var sumVal="0";
		var sumcount="0";
		var min ="0";
		var max ="0";

		var slamin = jQuery("#min").html();
		var slamax = jQuery("#max").html();
		
		var cnt=24;
		
		if (jQuery('#lblHourWeek').text() ==  "Week")
			cnt=1;
		if (jQuery('#lblHourWeek').text() ==  "Shift")
			cnt=3;
		if (jQuery('#lblHourWeek').text() ==  "Daily")
			cnt=1;
		if (jQuery('#lblHourWeek').text() ==  "Monthly")
			cnt=1;
		
		for (var i=1;i<=cnt;i++) {
			var txtVal = jQuery('#txtSlahH'+i).val();
			//alert("txtVal"+txtVal);
			if(txtVal.trim().length>0)
			{
				sumVal= parseInt(sumVal) + parseInt(txtVal);
				sumcount = parseInt(sumcount) + 1;
				if(i==1)
				{  
					
					min = txtVal;
					max = txtVal;				
				}
				
				
				if (parseInt(txtVal) < parseInt(min))
				{  			
					min = txtVal;
					
				}
				if (parseInt(txtVal) > parseInt(max))
				{  
								
					max = txtVal;
					
				}
			}
		}

				
		var sumofvalue="0";
		for (var r=1;r<=cnt;r++) {
			var entrVal = jQuery('#txtSlahH'+r).val();
			
			if(entrVal.trim().length>0)
			{
				sumofvalue = parseInt(sumofvalue) + parseInt(entrVal);
			}
			
		}
		
		if(parseInt(sumofvalue)>0)
		{  
			sumofvalue = parseInt(sumofvalue)/parseInt(cnt);
			sumVal = parseInt(sumVal) / parseInt(cnt);
			 
			if (parseInt(sumofvalue) < parseInt(slamin))
				setFieldValue('txtSlemDeviation','1');			
			else if (parseInt(sumofvalue) > parseInt(slamax))
				setFieldValue('txtSlemDeviation','1');
			else
				setFieldValue('txtSlemDeviation','0');		
		}else
		{ 
		
			setFieldValue('txtSlemDeviation','0');	
		}
		var frequency = jQuery("#txtfreq").val();
		//alert("frequency="+frequency);
		var slemid = jQuery("#txtslemid").val();
		jQuery('#'+selId).val(sumVal);
		setFieldValue("txtslemSlasum",sumVal);
		setFieldValue("txtSlemCount",sumcount);
		setFieldValue("txtSlemMax",max);
		setFieldValue("txtSlemMin",min);
		/*if(frequency=="Hourly")
	    {
		var SlahH1 = jQuery("#txtSlahH1").val();
		if(SlahH1.length==0)
		{
		jQuery("#txtSlahH1").val(0);
		}
		var SlahH2 = jQuery("#txtSlahH2").val();
		if(SlahH2.length==0)
		{
		jQuery("#txtSlahH2").val(0);
		}
		var SlahH3 = jQuery("#txtSlahH3").val();
		if(SlahH3.length==0)
		{
		jQuery("#txtSlahH3").val(0);
		}
		var SlahH4 = jQuery("#txtSlahH4").val();
		if(SlahH4.length==0)
		{
		jQuery("#txtSlahH4").val(0);
		}
		var SlahH5 = jQuery("#txtSlahH5").val();
		if(SlahH5.length==0)
		{
		jQuery("#txtSlahH5").val(0);
		}
		var SlahH6 = jQuery("#txtSlahH6").val();
		if(SlahH6.length==0)
		{
		jQuery("#txtSlahH6").val(0);
		}
		var SlahH7 = jQuery("#txtSlahH7").val();
		if(SlahH7.length==0)
		{
		jQuery("#txtSlahH7").val(0);
		}
		var SlahH8 = jQuery("#txtSlahH8").val();
		if(SlahH8.length==0)
		{
		jQuery("#txtSlahH8").val(0);
		}
		var SlahH9 = jQuery("#txtSlahH9").val();
		if(SlahH9.length==0)
		{
		jQuery("#txtSlahH9").val(0);
		}
		var SlahH10 = jQuery("#txtSlahH10").val();
		if(SlahH10.length==0)
		{
		jQuery("#txtSlahH10").val(0);
		}
		var SlahH11 = jQuery("#txtSlahH11").val();
		if(SlahH11.length==0)
		{
		jQuery("#txtSlahH11").val(0);
		}
		var SlahH12 = jQuery("#txtSlahH12").val();
		if(SlahH12.length==0)
		{
		jQuery("#txtSlahH12").val(0);
		}
		var SlahH13 = jQuery("#txtSlahH13").val();
		if(SlahH13.length==0)
		{
		jQuery("#txtSlahH13").val(0);
		}
		var SlahH14 = jQuery("#txtSlahH14").val();
		if(SlahH14.length==0)
		{
		jQuery("#txtSlahH14").val(0);
		}
		var SlahH15 = jQuery("#txtSlahH15").val();
		if(SlahH15.length==0)
		{
		jQuery("#txtSlahH15").val(0);
		}
		var SlahH16 = jQuery("#txtSlahH16").val();
		if(SlahH16.length==0)
		{
		jQuery("#txtSlahH16").val(0);
		}
		var SlahH17 = jQuery("#txtSlahH17").val();
		if(SlahH17.length==0)
		{
		jQuery("#txtSlahH17").val(0);
		}
		var SlahH18 = jQuery("#txtSlahH18").val();
		if(SlahH18.length==0)
		{
		jQuery("#txtSlahH18").val(0);
		}
		var SlahH19 = jQuery("#txtSlahH19").val();
		if(SlahH19.length==0)
		{
		jQuery("#txtSlahH19").val(0);
		}
		var SlahH20 = jQuery("#txtSlahH20").val();
		if(SlahH20.length==0)
		{
		jQuery("#txtSlahH20").val(0);
		}
		var SlahH21 = jQuery("#txtSlahH21").val();
		if(SlahH21.length==0)
		{
		jQuery("#txtSlahH21").val(0);
		}
		var SlahH22 = jQuery("#txtSlahH22").val();
		if(SlahH22.length==0)
		{
		jQuery("#txtSlahH22").val(0);
		}
		var SlahH23 = jQuery("#txtSlahH23").val();
		if(SlahH23.length==0)
		{
		jQuery("#txtSlahH23").val(0);
		}
		var SlahH24 = jQuery("#txtSlahH24").val();
		if(SlahH24.length==0)
		{
		jQuery("#txtSlahH24").val(0);
		}
	    }
	    if(frequency=="Shift")
		{
	    	var SlahH1 = jQuery("#txtSlahH1").val();
			if(SlahH1.length==0)
			{
			jQuery("#txtSlahH1").val(0);
			}
			var SlahH2 = jQuery("#txtSlahH2").val();
			if(SlahH2.length==0)
			{
			jQuery("#txtSlahH2").val(0);
			}
			var SlahH3 = jQuery("#txtSlahH3").val();
			if(SlahH3.length==0)
			{
			jQuery("#txtSlahH4").val(0);
			}
			var SlahH4 =0;
			var SlahH5 =0;
			var SlahH6 =0;
			var SlahH7 =0;
			var SlahH8 =0;
			var SlahH9 =0;
			var SlahH10 =0;
			var SlahH11 =0;
			var SlahH12 =0;
			var SlahH13 =0;
			var SlahH14 =0;
			var SlahH15 =0;
			var SlahH16 =0;
			var SlahH17 =0;
			var SlahH18 =0;
			var SlahH19 =0;
			var SlahH20 =0;
			var SlahH21 =0;
			var SlahH22 =0;
			var SlahH23 =0;
			var SlahH24 =0;
		}*/
		//var datecount = jQuery("#txtdatecount").val();
		///alert("datecount"+datecount);
		//alert("1"+SlahH1);
		var slemid = jQuery("#txtslemid").val();
	    //alert("slemid="+slemid);
		var DocDate = getFieldValue("txtslemDate");
		processAjaxCalls("SLAhourlyrecal_savehour.slam","Date="+DocDate+"&slemid="+slemid, "hourlysavesuccesssCallback", "", "", "");
		
		/*saveForm("frmSLAEntry","servicelevelmaster_save.slam?slemid="+slemid+"&frequency="+frequency+"datecount="+datecount+"&SlahH1="+SlahH1+
				"&SlahH2="+SlahH2+"&SlahH3="+SlahH3+"&SlahH4="+SlahH4+"&SlahH5="+SlahH5+"&SlahH6="+SlahH6+"&SlahH7="+SlahH7+"&SlahH8="+SlahH8+
				"&SlahH9="+SlahH9+"&SlahH10="+SlahH10+"&SlahH11="+SlahH11+"&SlahH12="+SlahH12+"&SlahH13="+SlahH13+"&SlahH14="+SlahH14+"&SlahH15="+SlahH15+
				"&SlahH16="+SlahH16+"&SlahH17="+SlahH17+"&SlahH18="+SlahH18+"&SlahH19="+SlahH19+"&SlahH20="+SlahH20+"&SlahH21="+SlahH21+"&SlahH22="+SlahH22+"&SlahH23="+SlahH23+"&SlahH24="+SlahH24,"");*/
		
		//jQuery("#gridSLAEntry").jqGrid().trigger("reloadGrid");
		
	}
	function hourlysavesuccesssCallback(result)
	{
		//alert(1);
		//alert(result.Count);
		jQuery("#txtdatecount").val(result.Count);
		
		//setFieldValue("txtdatecount",result.Count);
   jQuery('#divHours').hide();
		
		var selId = jQuery('#hdnSelHourId').val();
		var sumVal="0";
		var sumcount="0";
		var min ="0";
		var max ="0";

		var slamin = jQuery("#min").html();
		var slamax = jQuery("#max").html();
		
		var cnt=24;
		
		if (jQuery('#lblHourWeek').text() ==  "Week")
			cnt=1;
		if (jQuery('#lblHourWeek').text() ==  "Shift")
			cnt=3;
		if (jQuery('#lblHourWeek').text() ==  "Daily")
			cnt=1;
		if (jQuery('#lblHourWeek').text() ==  "Monthly")
			cnt=1;
		
		for (var i=1;i<=cnt;i++) {
			var txtVal = jQuery('#txtSlahH'+i).val();
			//alert("txtVal"+txtVal);
			if(txtVal.trim().length>0)
			{
				sumVal= parseInt(sumVal) + parseInt(txtVal);
				sumcount = parseInt(sumcount) + 1;
				if(i==1)
				{  
					
					min = txtVal;
					max = txtVal;				
				}
				
				
				if (parseInt(txtVal) < parseInt(min))
				{  			
					min = txtVal;
					
				}
				if (parseInt(txtVal) > parseInt(max))
				{  
								
					max = txtVal;
					
				}
			}
		}

				
		var sumofvalue="0";
		for (var r=1;r<=cnt;r++) {
			var entrVal = jQuery('#txtSlahH'+r).val();
			
			if(entrVal.trim().length>0)
			{
				sumofvalue = parseInt(sumofvalue) + parseInt(entrVal);
			}
			
		}
		
		if(parseInt(sumofvalue)>0)
		{  
			sumofvalue = parseInt(sumofvalue)/parseInt(cnt);
			sumVal = parseInt(sumVal) / parseInt(cnt);
			 
			if (parseInt(sumofvalue) < parseInt(slamin))
				setFieldValue('txtSlemDeviation','1');			
			else if (parseInt(sumofvalue) > parseInt(slamax))
				setFieldValue('txtSlemDeviation','1');
			else
				setFieldValue('txtSlemDeviation','0');		
		}else
		{ 
		
			setFieldValue('txtSlemDeviation','0');	
		}
		var frequency = jQuery("#txtfreq").val();
		//alert("frequency="+frequency);
		var slemid = jQuery("#txtslemid").val();
		jQuery('#'+selId).val(sumVal);
		setFieldValue("txtslemSlasum",sumVal);
		setFieldValue("txtSlemCount",sumcount);
		setFieldValue("txtSlemMax",max);
		setFieldValue("txtSlemMin",min);
     if(frequency=="Hourly")
	 {
		var SlahH1 = jQuery("#txtSlahH1").val();
		if(SlahH1.length==0)
			{
			SlahH1 = -999;
			}
		var SlahH2 = jQuery("#txtSlahH2").val();
		if(SlahH2.length==0)
		{
			SlahH2 = -999;
		}
		var SlahH3 = jQuery("#txtSlahH3").val();
		if(SlahH3.length==0)
		{
			SlahH3 = -999;
		}
		var SlahH4 = jQuery("#txtSlahH4").val();
		if(SlahH4.length==0)
		{
			SlahH4 = -999;
		}
		var SlahH5 = jQuery("#txtSlahH5").val();
		if(SlahH5.length==0)
		{
			SlahH5 = -999;
		}
		var SlahH6 = jQuery("#txtSlahH6").val();
		if(SlahH6.length==0)
		{
			SlahH6 = -999;
		}
		var SlahH7 = jQuery("#txtSlahH7").val();
		if(SlahH7.length==0)
		{
		SlahH7 = -999;
		}
		var SlahH8 = jQuery("#txtSlahH8").val();
		if(SlahH8.length==0)
		{
			SlahH8 = -999;
		}
		var SlahH9 = jQuery("#txtSlahH9").val();
		if(SlahH9.length==0)
		{
			SlahH9 = -999;
		}
		var SlahH10 = jQuery("#txtSlahH10").val();
		if(SlahH10.length==0)
		{
			SlahH10 = -999;
		}
		var SlahH11 = jQuery("#txtSlahH11").val();
		if(SlahH11.length==0)
		{
			SlahH11 = -999;
		}
		var SlahH12 = jQuery("#txtSlahH12").val();
		if(SlahH12.length==0)
		{
			SlahH12 = -999;
		}
		var SlahH13 = jQuery("#txtSlahH13").val();
		if(SlahH13.length==0)
		{
			SlahH13 = -999;
		}
		var SlahH14 = jQuery("#txtSlahH14").val();
		if(SlahH14.length==0)
		{
			SlahH14 = -999;
		}
		var SlahH15 = jQuery("#txtSlahH15").val();
		if(SlahH15.length==0)
		{
			SlahH15 = -999;
		}
		var SlahH16 = jQuery("#txtSlahH16").val();
		if(SlahH16.length==0)
		{
			SlahH16 = -999;
		}
		var SlahH17 = jQuery("#txtSlahH17").val();
		if(SlahH17.length==0)
		{
			SlahH17 = -999;
		}
		var SlahH18 = jQuery("#txtSlahH18").val();
		if(SlahH18.length==0)
		{
			SlahH18 = -999;
		}
		var SlahH19 = jQuery("#txtSlahH19").val();
		if(SlahH19.length==0)
		{
			SlahH19 = -999;
		}
		var SlahH20 = jQuery("#txtSlahH20").val();
		if(SlahH20.length==0)
		{
			SlahH20 = -999;
		}
		var SlahH21 = jQuery("#txtSlahH21").val();
		if(SlahH21.length==0)
		{
			SlahH21 = -999;
		}
		var SlahH22 = jQuery("#txtSlahH22").val();
		if(SlahH22.length==0)
		{
			SlahH22 = -999;
		}
		var SlahH23 = jQuery("#txtSlahH23").val();
		if(SlahH23.length==0)
		{
			SlahH23 = -999;
		}
		var SlahH24 = jQuery("#txtSlahH24").val();
		if(SlahH24.length==0)
		{
			SlahH24=-999;
		}
	}
	    if(frequency=="Shift")
		{
	    	var SlahH1 = jQuery("#txtSlahH1").val();
			if(SlahH1.length==0)
			{
				SlahH1 = -999;
			}
			var SlahH2 = jQuery("#txtSlahH2").val();
			if(SlahH2.length==0)
			{
				SlahH2 = -999;
			}
			var SlahH3 = jQuery("#txtSlahH3").val();
			if(SlahH3.length==0)
			{
				SlahH3 = -999;
			}
			var SlahH4 =-999;
			var SlahH5 =-999;
			var SlahH6 =-999;
			var SlahH7 =-999;
			var SlahH8 =-999;
			var SlahH9 =-999;
			var SlahH10 =-999;
			var SlahH11 =-999;
			var SlahH12 =-999;
			var SlahH13 =-999;
			var SlahH14 =-999;
			var SlahH15 =-999;
			var SlahH16 =-999;
			var SlahH17 =-999;
			var SlahH18 =-999;
			var SlahH19 =-999;
			var SlahH20 =-999;
			var SlahH21 =-999;
			var SlahH22 =-999;
			var SlahH23 =-999;
			var SlahH24 =-999;
		}
		//var datecount = jQuery("#txtdatecount").val();
		///alert("datecount"+datecount);
		//alert("1"+SlahH1);
		var DocDate = getFieldValue("txtslemDate");
		var datecount=jQuery("#txtdatecount").val();
		var slmkeyid=jQuery("#txtslmkeyid").val();
		//processAjaxCalls("SLAhourlyrecal_savehour.slam","Date="+DocDate, "hourlysavesuccesssCallback", "", "", "");
		
		saveForm("frmSLAEntry","servicelevelmaster_save.slam?slemid="+slemid+"&frequency="+frequency+"&datecount="+datecount+"&SlahH1="+SlahH1+
				"&SlahH2="+SlahH2+"&SlahH3="+SlahH3+"&SlahH4="+SlahH4+"&SlahH5="+SlahH5+"&SlahH6="+SlahH6+"&SlahH7="+SlahH7+"&SlahH8="+SlahH8+
				"&SlahH9="+SlahH9+"&SlahH10="+SlahH10+"&SlahH11="+SlahH11+"&SlahH12="+SlahH12+"&SlahH13="+SlahH13+"&SlahH14="+SlahH14+"&SlahH15="+SlahH15+
				"&SlahH16="+SlahH16+"&SlahH17="+SlahH17+"&SlahH18="+SlahH18+"&SlahH19="+SlahH19+"&SlahH20="+SlahH20+"&SlahH21="+SlahH21+"&SlahH22="+SlahH22+"&SlahH23="+SlahH23+"&SlahH24="+SlahH24+"&slmkeyid="+slmkeyid,"");
		
		//jQuery("#gridSLAEntry").jqGrid().trigger("reloadGrid");

		/*var frmdmt = jQuery("#txtfrmdmt").val();
		alert("frmdmt="+frmdmt);
		var todmt = jQuery("#txttodmt").val();
		alert("frmdmt="+frmdmt);
		var month = jQuery("#txtmonth").val();
		alert("month="+month);
		var sladid = jQuery("#txtsladid").val();
		alert("sladid="+sladid);
		var slemid = jQuery("#txtslemid").val();
		alert("slemid="+slemid);
		var freq = jQuery("#txtfreq").val();
		alert("freq="+freq);	
		navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&mode="+mode+"&slmkeyid="+slmkeyid+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq+"&lockdate="+lockdate+"&lockcurdate="+lockcurdate,"SLA Entry");*/
	}
	
    
	function btnfilemgr_click()
	{	   
		//alert(123);
		var formtype = getFieldValue('hdnmode');  
		//alert(formtype);
		if(formtype == 'View')
		{   
			var slakeyid = getFieldValue('txtsladid');
			//alert("1"+slakeyid);		
			fileManagerPopUp(slakeyid,"SLA","","","","view");			
			
		}
		else
		{
			var slakeyid = getFieldValue('txtsladid');	
			//alert("2"+slakeyid);
			fileManagerPopUp(slakeyid,"SLA","","","");
		}
			
	}

	function txtFormatter(celVal, options, rowObject) {
		var ret ="" ;		
				var id = options.rowId;
				var columnNo = options.pos;
				var idval;
				idval = 'txtPer_';
		
				
				var splitKeyid = celVal.split('#');
				celVal = splitKeyid[0];
				var keyid="";
				
				if (splitKeyid.length > 1)
				{	 
					 keyid = splitKeyid[1];
			    	
				}
				
				var min=rowObject[4];	
				var max=rowObject[5];
				//alert("min"+min + "max"+max +"celval"+ celVal);
              // var max_value = Math.max(celVal);
              // alert("max_value"+max_value);
				
				
				var colr="";
				if (parseInt(celVal) < parseInt(min))
					{					
						colr="red";
						//alert('min' + celVal + min + colr);
					}
				else if (parseInt(celVal) > parseInt(max))
					{
						colr="red";
						//alert('max' + celVal + max + colr);
					}
				else 
					{
					
						colr="";
						//alert('n' + celVal + max + colr);
					}
					
			   
				ret ='<input id="'+idval+columnNo+'_'+id +'" onfocus=gotFocuse(this.id); onchange=gotFocuseout(this.id) type="text"  ';
				
				if (colr!="")
					{ret =ret + 'style="width: 30px; text-align:right;color:'+colr+';"';}			
				else
					{ret =ret + ' style="width: 30px; text-align:right;color:black;"';}
					
					ret =ret + ' keyid="'+keyid+'"';
					ret =ret + ' value="'+celVal+'" maxlength="10" " / >';				
				
			
		return ret;
	}
	
function convertControlsToJSONString(){
		var rowid="";
		var cValue="";
		var cKeyId="";
		var jsonArrO='[';
		//var rowId ="1";
		var vFreq ="";
		var	rowCnt = jQuery("#gridSLAEntry").getGridParam("reccount");
		
		jsonArrO += '{';

		for (var i = 1; i <=rowCnt ; i++)  {
			for(var j=11;j<=41;j++)	
			{		
				controlId="txtPer_"+j+"_"+i;
				r=j-10;
				cValue="txtSledD"+r+":"+jQuery('#'+ controlId).val();
				//alert("cValue"+cValue);
				//alert(jQuery('#'+ controlId).val());
				var checkvalue=jQuery('#'+ controlId).val();
				//alert("checkvalue= check 1234 "+checkvalue);
				//alert(" trim "+checkvalue.trim().length);
			if(checkvalue=='undefined'||checkvalue== undefined||checkvalue== null||checkvalue.trim().length==0)
				{
				//alert(1);
				var num = -999;
				cValue="txtSledD"+r+":"+num;
				//alert("c value"+cValue);
				}
				if(cValue!=null && cValue!=""){
					
					jsonArrO += cValue +',';
					jsonArrO = jsonArrO.slice(0, -1) + ",";
					
					}
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},{";
		}
			jsonArrO = jsonArrO + "}]";
					//alert("jsonArrO"+jsonArrO);
					return jsonArrO;
			}
function converthourlyJsonValueToString(){
	var rowid="";
	var hValue="";
	var cKeyId="";
	var jsonArrO='[';
	jsonArrO += '{';

		for(var j=1;j<=24;j++)	
		{		
			controlId="txtSlahH"+j;
			hValue="txtSlahH"+j+":"+jQuery('#'+ controlId).val();
			//alert("cValue"+cValue);
		
			if(hValue!=null && hValue!=""){
				
				jsonArrO += hValue +',';
				jsonArrO = jsonArrO.slice(0, -1) + ",";
				
				}
		}

		jsonArrO = jsonArrO.slice(0, -1) + "}]";
				//alert("jsonArrO"+jsonArrO);
				return jsonArrO;
			 	
		}

		
	function frmSLAEntry_beforeSubmit(){
	    //alert("griddata");
	    var dtefrom = getFieldValue("dteFrom","frmSLAEntry");
	    //alert(dtefrom);
	   	var serverTime = srvTime();
	 var dateTime = new Date(serverTime);
	var currentTime = dateTime; // new Date();
	var month = currentTime.getMonth();	
	
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
		month = getMonthStringFromInt(month);		
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	   //alert(day);
	    var createddate=day+"-"+dtefrom;
	    //alert("createddate="+createddate);
        var freq = jQuery("#txtfreq").val();
        if(freq =="Hourly" ||freq=="Shift")
        {   var convertStr = convertControlsToJSONString();
            var Hourlystr = converthourlyJsonValueToString();
            var convertStr ='&convertStr='+convertStr+'&Hourlystr='+Hourlystr+"&createddate="+createddate; 
            //alert("convertStr="+convertStr);
        }
        else
            {		
		var convertStr = convertControlsToJSONString();
		convertStr+='&convertStr='+convertStr+"&createddate="+createddate;
		//alert("convertStr="+convertStr);
            }
		return convertStr; 
	}

	function slahourlyrecalsuccesssCallback(result)
	{   //alert('successCallBack');

		
		//jQuery("#gridSLAEntry").jqGrid().trigger("reloadGrid");

		setFieldValue("txtSlahH1",result.Data[0][0]);
	    setFieldValue("txtSlahH2",result.Data[0][1]);
	    setFieldValue("txtSlahH3",result.Data[0][2]);
	    setFieldValue("txtSlahH4",result.Data[0][3]);
	    setFieldValue("txtSlahH5",result.Data[0][4]);
	    setFieldValue("txtSlahH6",result.Data[0][5]);
	    setFieldValue("txtSlahH7",result.Data[0][6]);
	    setFieldValue("txtSlahH8",result.Data[0][7]);
	    setFieldValue("txtSlahH9",result.Data[0][8]);
	    setFieldValue("txtSlahH10",result.Data[0][9]);
	    setFieldValue("txtSlahH11",result.Data[0][10]);
	    setFieldValue("txtSlahH12",result.Data[0][11]);
	    setFieldValue("txtSlahH13",result.Data[0][12]);
	    setFieldValue("txtSlahH14",result.Data[0][13]);
	    setFieldValue("txtSlahH15",result.Data[0][14]);
	    setFieldValue("txtSlahH16",result.Data[0][15]);
	    setFieldValue("txtSlahH17",result.Data[0][16]);
	    setFieldValue("txtSlahH18",result.Data[0][17]);
	    setFieldValue("txtSlahH19",result.Data[0][18]);
	    setFieldValue("txtSlahH20",result.Data[0][19]);
	    setFieldValue("txtSlahH21",result.Data[0][20]);
	    setFieldValue("txtSlahH22",result.Data[0][21]);
	    setFieldValue("txtSlahH23",result.Data[0][22]);
	    setFieldValue("txtSlahH24",result.Data[0][23]);
	    
	}
	
	function gotFocuse(id){
		var mode = jQuery('#hdnmode').val();
		//alert("hdncurdate in get Focus"+jQuery("#hdncurdte").val());
		
		if (mode != "View")
		{  	
			var cm = jQuery("#gridSLAEntry").jqGrid("getGridParam", "colModel");	
			
			jQuery('#divLblMin').show();
			jQuery('#divLblMinVal').show();   
			var cellid = id ; 
			
			numericTextBox(id);		
			var ids=id.split("_");

			var freq = jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"Frequency");
			setFieldValue('hdnfrequency',freq);
			var lastdate = cm[cm.length - 1];
			//alert("lastdate>>>"+lastdate);
			var endofmonth = cm.length - 1;
			//alert("endofmonth>>>"+endofmonth);

			if(freq=="Monthly")
			{   				 
				id = 'txtPer_'+endofmonth+'_'+ids[2];
			}
		
				var slmid = jQuery("#"+id).attr("keyid");
				var selvalue = jQuery("#"+id).attr("value");
		
			setFieldValue('hdnselvalue',selvalue);
			
				
			//var slemid =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"Keyid");	
			var sladid =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"SLDKeyid");
			
			var qltychar =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"QualityCharacteristics(ProductandService)");
			
			
			var Minimum =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"Minimum");
			var Maximum =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"Maximum");
			var Target =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"Target");
			var problem =jQuery("#gridSLAEntry").jqGrid('getCell',ids[2],"QualityCharacteristics(ProductandService)");

		 	
			setFieldValue("txtproblem",problem);	

			jQuery('#min').text(Minimum);
			jQuery('#max').text(Maximum);
			jQuery('#Avg').text(Target);
			
			
			
			var colName = cm[ids[1]];		
			var dateval =  colName['index'];
				
			if(freq=="Monthly")
			{
				dateval =lastdate.index;
			}
				

			 var Valuedate  =  ids[1] - 10 + '-' + getFieldValue('dteFrom');
			 Valuedate = 'Entering Value For the Date ' + Valuedate ;
			 //alert("Valuedate"+Valuedate);
			 jQuery('#Enterdate').text(Valuedate);

			 var Valuedate1  =  ids[1] - 10 + '-' + getFieldValue('dteFrom');
			 setFieldValue("txtslemDate",Valuedate1);
			 //alert("txtslemDate"+Valuedate);
			 setFieldValue("txtSlemSladid",sladid);
			 setFieldValue("txtSlemKeyid",slmid);
			 setFieldValue("txtQltyremrks",qltychar);
			 
			

			
			 
			if (freq=="Hourly" || freq=="Weekly" || freq=="Shift" || freq=="Daily" || freq=="Monthly" ) {
				if (freq=="Weekly") {	
					setFieldValue("txtslemFrqtype","W");			
					addHourDiv(1);
					setTimeout(function() {jQuery('#lblHourWeek').text('Week');},250);
					numericTextBox('txtsledD1');
				}
				else if (freq=="Hourly") {
					setFieldValue("txtslemFrqtype","H");	
					addHourDiv(24);
					setTimeout(function() {jQuery('#lblHourWeek').text('Hour');},250);
					for (var n=1;n < 24 ; n++)
					{
						numericTextBox('txtSlahH'+n);
					}
				}
				else if (freq=="Shift") {
					
					setFieldValue("txtslemFrqtype","S");
					addHourDiv(3);
					setTimeout(function() {jQuery('#lblHourWeek').text('Shift');},250);
					numericTextBox('txtSlahH1');
					numericTextBox('txtSlahH2');
					numericTextBox('txtSlahH3');

				}
				else if (freq=="Daily") {
					setFieldValue("txtslemFrqtype","D");
					addHourDiv(1);
					setTimeout(function() {jQuery('#lblHourWeek').text('Daily');},250);
					numericTextBox('txtSlahH1');
				}
				else if (freq=="Monthly") {
					setFieldValue("txtslemFrqtype","M");
					addHourDiv(1);
					setTimeout(function() {jQuery('#lblHourWeek').text('Monthly');},250);
					numericTextBox('txtSlahH1');
				}
	
				jQuery('#hdnSelHourId').val(id);	
				jQuery('#divHours').show();
				setTimeout(function() {jQuery("#txtSlahH1").focus();},550);
			}
			else if (freq=="Daily" || freq=="Monthly")
				jQuery('#divHours').hide();
		}
		FillSLAEntryvalues();
	}
	function FillSLAEntryvalues()
	{
		var slaEntryid = getFieldValue('txtSlemKeyid');
		//alert("hdncurdate in fillslavalues"+jQuery("#hdncurdte").val());
		processAjaxCalls("SLAEntryValues_input.slam","keyid="+slaEntryid, "FillSLAvalues", "", "", "new");
		//void processAjaxCalls(any url, any data, any onsuccessCallBack, any onerrorCallBack, any dataType, any requestId, any hideProcessing)
	}
	function FillSLAvalues(result)
	{
		//alert("FILLSLAVALUES");
		var cnt=24;

		//alert("hdncurdate inside fillvalues"+jQuery("#hdncurdte").val());
		if (jQuery('#lblHourWeek').text() ==  "Week")
			cnt=4;
		if (jQuery('#lblHourWeek').text() ==  "Shift")
			cnt=3;
		if (jQuery('#lblHourWeek').text() ==  "Daily")
			cnt=1;
		if (jQuery('#lblHourWeek').text() ==  "Monthly")
			cnt=1;
		
		var vmin = jQuery('#min').text();
		var vmax = jQuery('#max').text();
		var vAvg =  jQuery('#Avg').text();
		//alert(cnt);
		for (var i=1;i<=cnt;i++) {
			
			var txtvalue = result[0][i];
			
			jQuery('#txtSlahH'+i).val(txtvalue);
			
			if (parseInt(txtvalue) < parseInt(vmin))
			{					
				jQuery('#txtSlahH'+i).addClass('exittargtlvl');
			}
			else if (parseInt(txtvalue) > parseInt(vmax))
			{
				jQuery('#txtSlahH'+i).addClass('exittargtlvl');
			}
					
		}
	}

	function viewGrid(url,filterString)
	{

	      processGridnew(url,filterString,"gridSLAEntry","pager3", "", "","AlphaNumericOnly","gridLoadComplete","","");
		
	}
	
	function gridLoadComplete() {
		jQuery("#gridSLAEntry").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			jQuery('#divHours').hide();
			jQuery('#divLblMin').show();
			jQuery('#divLblMinVal').show();
			
		}
		});
		
		//alert("after grif load complete");
		var	rowCnt = jQuery("#gridSLAEntry").getGridParam("reccount");
		//alert("rowCnt"+rowCnt);
		var countCols = jQuery('#gridSLAEntry').jqGrid('getGridParam', 'colNames').length;
		//alert("countCols"+countCols);

		var serverTime = srvTime();
        var dateTime = new Date(serverTime);
        var currentTime = dateTime; // new Date();	
        var day = currentTime.getDate();
        var datefrom=jQuery("#dteFrom").datebox('getValue');
        var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
       // alert("date"+day);
       /*  alert("dateTime>>>"+currentTime.substring(4,7));
        alert("dateTime>>>"+currentTime.substring(8,12)); */
        var month=dateTime.getMonth();
        
        var curnmnt=monthArray[month];
        //var mnth=getIndex(parseInt(month));
      //  alert("curnmnt>>>"+curnmnt);
        var fullyear=dateTime.getFullYear();
        
        var mnthjoin=curnmnt+"-"+fullyear;
       // alert("mnthjoin>>>>"+mnthjoin);
       // alert("datefrom:::>>"+datefrom);
        
       // alert("month>>>>"+month);
        //alert("fullyear::>>"+fullyear);
        //alert("date from>>>>"+datefrom);
		
		//alert("countCols" + countCols);
		for(var j = 9; j < countCols; j++)
		{    
			for (var i = 1; i <=rowCnt ; i++)  {
				var fieldId = "txtPer_"+j+"_"+i;
              // alert("fieldId"+fieldId+"rowcount"+j);
				var mode = jQuery('#hdnmode').val();
				if (mode != "View")
				{ 
					var freq = jQuery("#gridSLAEntry").jqGrid('getCell',i,"Frequency");
					//alert(freq);
					if (freq=="Hourly" || freq=="Shift") {	
						//alert(freq);				
						//jQuery("#frmSLAEntry input[id='"+fieldId+"']").removeClass('maxlength');
						jQuery("#frmSLAEntry input[id='"+fieldId+"']").attr('maxlength','0');
						var m;
						
						if(datefrom==mnthjoin)
						{	
                        for(var k=1;k<=39;k++)
                            {
                        	

                             if(day==k)
                                 {
                            	 
                                 var days=day;
                             
                                  if(days<=10)
                                      {
                                     // alert(days)
                                  	m=k+10+1;
                                  	   for( var l=m;l<=39;l++)
                                         {
                                         
                                        // alert(l);
                     	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                         }
                                     }
                                  else if(days>10&&days<=20)
                                      {
                                      m=k+10+1;
                                      for( var l=m;l<=39;l++)
                                      {
                                      
                                     // alert(l);
                  	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                      }

                                      }
                                  else if(days>20&&days<=30)
                                  {
                                  m=k+10+1;
                                  for( var l=m;l<=39;l++)
                                  {
                                  
                                 // alert(l);
              	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                  }

                                  }
                                }
                   }
						}
					}
					else if (freq=="Daily") 
                    {
	                    
						var m;
						if(datefrom==mnthjoin)
						{	
	                					
                                      for(var k=1;k<=39;k++)
                                          {

                                           if(day==k)
                                               {
                                               var days=day;
                                           
                                                if(days<=10)
                                                    {
                                                   // alert(days)
                                                	m=k+10+1;
                                                	   for( var l=m;l<=39;l++)
                                                       {
                                                       
                                                      // alert(l);
                                   	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                                       }
                                                   }
                                                else if(days>10&&days<=20)
                                                    {
                                                    m=k+10+1;
                                                    for( var l=m;l<=39;l++)
                                                    {
                                                    
                                                   // alert(l);
                                	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                                    }

                                                    }
                                                else if(days>20&&days<=30)
                                                {
                                                m=k+10+1;
                                                for( var l=m;l<=39;l++)
                                                {
                                                
                                               // alert(l);
                            	            disableField("frmSLAEntry","txtPer_"+l+"_"+i);
                                                }

                                                }
                                                   
                                                 
                                             
                                               }
                                 }
                                   
                             
                            


                    }

                    }	
					else if (freq=="Weekly") {
                       
						 if(datefrom==mnthjoin)
						    {	
							
						if(day>=28){
							//alert(day);
							if (!(j ==17  || j ==24 ||j ==31 || j == 38 ))
								{   
								disableField("frmSLAEntry",fieldId);
								 
								}	
					        
						}
						else if(day>=21){
							if (!(j ==17  || j ==24 ||j ==31 ))
									disableField("frmSLAEntry",fieldId);
					        }
						else if(day>=14){
							if (!(j ==17  || j ==24  ))
								{
									disableField("frmSLAEntry",fieldId);
								}		
					        }
						else if(day>=7){
						if (!(j ==17 ))
							
						       disableField("frmSLAEntry",fieldId);
							}
						else{
							disableField("frmSLAEntry",fieldId);
						}
						
						}
						 else{
							 
							 if (!(j ==17  || j ==24 ||j ==31 || j == 38 ))
								{   
								disableField("frmSLAEntry",fieldId);
								 
								}	
						 }

					}
					else if (freq=="Monthly") {
						//if (j != countCols-1)
						if (!(j ==11))
							{disableField("frmSLAEntry",fieldId);}	} 
				}else
					{
						disableField("frmSLAEntry",fieldId);
					}
				
				jQuery("#gridSLAEntry").jqGrid('setCell',i,j,'',{'color':'black','font-size': '11','font-weight' : 'bold', 'text- align':'right'} );
			}	
		}
		var lockcount = getFieldValue('txtlockdate');
		if(lockcount > 0)
		{
				for (var i = 1; i <=rowCnt ; i++)  {
					
					for(var j = 0; j < lockcount; j++)
					{   var c = j + 11 ;
						//alert('lockcount' + c);
						var fieldId = "txtPer_"+c+"_"+i;
						disableField("frmSLAEntry",fieldId);
					}
					
			
				}
			
		}
		var lockcurrcount = getFieldValue('txtlockcurdate');
		if(lockcurrcount > 0)
		{
				for (var i = 1; i <=rowCnt ; i++)  {
					
					for(var j = lockcurrcount; j < countCols; j++)
					{  
						var c = j + 10 ;
						//alert('lockcurrcount' + c);
						var fieldId = "txtPer_"+c+"_"+i;
						//alert("fieldId"+fieldId);
						disableField("frmSLAEntry",fieldId);
					}
					
			
				}
			
		}
			
	}
	jQuery('#btnslaActionpln').click(function(){	
		var refDocDtl = jQuery("#txtSlemKeyid").val(); 
		var refDocId = jQuery("#txtSlemSladid").val(); 
		var mom = "SLA";
		var flid = jQuery("#frmSLAEntry input[id='flid']").val();
		
		var selvalue = getFieldValue("hdnselvalue");
		var min = jQuery("#min").html();
		var max = jQuery("#max").html();

		var pmax = parseInt(max,10);
		var pmin = parseInt(min,10);
		var pselvalue = parseInt(selvalue,10);
		var problem = getFieldValue("txtproblem");
		if(pselvalue > pmax || pselvalue < pmin)
		{		
			if(refDocDtl != null && refDocDtl !=  undefined )
			{  
				openActionPlan("Actionplane",refDocId,mom,flid,problem);
			} 	
		}		
		else
		{
			alert("SLA value is with in target");
		}
		
	});


	function frmSLAEntry_successsCallback(result)
	{
		
			jQuery("#gridSLAEntry").jqGrid().trigger("reloadGrid");
			
		
		var slemid=result.successData.keyId;
		//alert("slemid in success"+slemid);	
		//var slamid=	result.successData.SlamkeyId;		
		var frmdmt = jQuery("#txtfrmdmt").val();
		//alert("frmdmt::"+frmdmt);
		var todmt = jQuery("#txttodmt").val();
		//alert("todmt::"+todmt); 
		var month = jQuery("#txtmonth").val();
		//alert("month="+month);
		var sladid = jQuery("#txtsladid").val();
		//alert("sladid="+sladid);
		var hdnfrom = jQuery("#hdnfrom").val();
		var freq = jQuery("#txtfreq").val();
	//	alert("freq"+freq);
		
		var slmkeyid = jQuery("#txtslmkeyid").val();
		/*if(slmkeyid.length==0)
			{
                var slamkeyid=jQuery("#txtslmkeyid").val();
                slmkeyid=slamkeyid;
			}*/
		//alert("slmkeyid:::"+slmkeyid);
		var ds ="&from="+hdnfrom+"&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&sladid="+sladid+"&freq="+freq+"&slmkeyid="+slmkeyid+"&slemid="+slemid;
       // alert("ds="+ds);
        var url="servicelevelEntryDtl_input.slam?";
        processGridnew(url,ds,"gridSLAEntry","pager3", "", "","AlphaNumericOnly","gridLoadComplete","","");

          if(freq=="Hourly"||freq=="Shift"||freq=="Daily"||freq=="Monthly"||freq=="Weekly")
            {
        	navigateToPrevForm();
            }
		//navigateToPrevForm();
		//jQuery("#SLAMasterReport").jqGrid().trigger("reloadGrid");
		//alert(1);
	}
	jQuery("#btnsubauthen").click(function(){
		
	      alert("Are sure you want permit to authentication");
	      var hdnslmkeyid =jQuery("#gridSLAEntry").jqGrid('getCell',1,"slamkeyid");
	      //alert("hdnslmkeyid="+hdnslmkeyid);
		  var frmmonth =jQuery("#txtmonth").val(); 
		  var month = jQuery("#txtmonth").val(); 
		 // alert("month="+month);
		  
		  processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+hdnslmkeyid+"&month=01-"+frmmonth+"&type=findcount", "authenticationcountsuccesssCallback", "", "", "");
		    
			
		});
		function authenticationcountsuccesssCallback(result){
			
			var hdnslmkeyid =jQuery("#gridSLAEntry").jqGrid('getCell',1,"slamkeyid");
			//alert(hdnslmkeyid);
			var frmmonth =jQuery("#txtmonth").val(); 
			var count=result.Data[0][0];
			
			if(count>0){
				alert("This Entry is already Submited for authentication");
				navigateToPrevForm();
			}
			else
			{
				processAjaxCalls("SLAEntrysubmitauthendication_save.slam","dteFrom="+frmmonth+"&hdnslmkeyid="+hdnslmkeyid, "authendsavesuccesssCallback", "", "", "");
			}
		}
		function authendsavesuccesssCallback(result){
			var sleakeyid=result.successData.keyId;
			var sleamonth=result.successData.Date;
			processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+sleakeyid+"&month="+sleamonth+"&type=update", "authendcountsuccesssCallback", "", "", "");
			alert("Sucessfully submit to authentication");
			//jQuery("#SLAMasterReport").jqGrid().trigger("reloadGrid");
			navigateToPrevForm();
	   }
	jQuery('#btnWhyWhy').click(function(){	
		
		var keyid = jQuery("#txtSlemKeyid").val();
		var flid = jQuery("#frmSLAEntry input[id='flid']").val();
		var problem = getFieldValue("txtproblem");
		var refDocDate = getFieldValue("txtslemDate");

		var selvalue = getFieldValue("hdnselvalue");
		var min = jQuery("#min").html();
		var max = jQuery("#max").html();
		var pmax = parseInt(max,10);
		var pmin = parseInt(min,10);
		var pselvalue = parseInt(selvalue,10);
		
		
		
		if(pselvalue > pmax  || pselvalue < pmin)
		{	
			if(keyid != null && keyid !=  undefined )
			{  
				openWhyWhy("divWhyWhy",false,keyid,"INR",flid, refDocDate, problem, "create");
			} 	
		}		
		else
		{
			alert("SLA value is with in Target");
		}
		
	
	});
	
	
</script>

	<form name="frmSLAEntry" id="frmSLAEntry" action=" " method="post" >
	
	<table style="width: 105%;"><tr><td>
	<div  class="easyui-paddingbfpx" style="padding-top:0%;padding-left:2%;">
		<table>
			<tr>
				<td valign="top">
					<div class="easyui-paddingbfpx">
						<label>From DMT</label>
					</div>
					
						<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbSlamFromdptid" name="cmbSlamFromdptid"
							 style="width: 215px;" disabled="disabled" value="${requestScope.frmdmt}" <c:out value = "${ requestScope.SlaFormBean.disableForm == true ? ' disabled':''}"/>  />
					</div>
						<div >
						<label>To DMT</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbSlamTodptid" name="cmbSlamTodptid"
							 style="width: 215px;" disabled="disabled" value="${requestScope.todmt}" <c:out value = "${ requestScope.SlaFormBean.disableForm == true ? ' disabled':''}"/> />					
					</div>
				</td>
											
				<td valign="top" style="padding-left: 20px;">					
					<div class="easyui-paddingbfpx"><label>Month</label></div>					
					<div class="easyui-paddingbfpx">
						<span><input id="dteFrom" disabled="disabled" name="dteFrom" class="easyui-datebox"  style="width:150px;" value=" "  <c:out value = "${ requestScope.SlaFormBean.disableForm == true ? ' disabled':''}"/>  ></span>
						
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-top: 25px">	
					<label style="padding-left:10px;padding-top:50px; " id="Enterdate" > </label>	
					</div>

				</td>
			
			
				
				<td valign="top" style="padding-top: 15px;">
				<div id="slaactnplanEntry">
			<input type="button" id="btnslaActionpln" name="btnslaActionpln" class="easyui-button"  style="width:80px;height:22px;" <c:out value = "${ requestScope.SlaFormBean.disableFrom == true ? ' disabled':''}"/> value="Action Plan" />
			 <span><input class="easyui-button" type="button"  value="Why Why" <c:out value = "${ requestScope.SlaFormBean.disableFrom == true ? ' disabled':''}"/>
										id="btnWhyWhy" name="btnWhyWhy" style="height: 21px"  /></span>
			<span style="position:absolute;padding-left:3px;">
								<input type="button" class="easyui-button"  id="btnCapaEntry" <c:out value = "${ requestScope.SlaFormBean.disableFrom == true ? ' disabled':''}"/>	name="btnCapaEntry" value="CAPA" style="height: 23px; "/>
								</span>
						<span style=" padding-left:60px;">
						<input class="easyui-button" type="button" value="Submit for Authentication"
										id="btnsubauthen" name="btnsubauthen" style="height: 21px"  /> 
					</span>
			</div>
					<div style=" padding-left:-5px; position:relative; ">
					 <span  id="SlaFilemgr" style="position:absolute;top:-23px;right: -125;" >
				    </span>

             		</div>
            
				</td>
			</tr>
		</table>
	</div>							
	
	</td>
	
	<td width="5%" style="border:1px solid black;">
	<div id="divLblMin">
		<div class="easyui-paddingbfpx">	<label style="padding-left:10px;" class='mandatory-lbl'> Min.</label>	</div>
		<div class="easyui-paddingbfpx">	<label style="padding-left:10px;" class='mandatory-lbl'> Max.</label>	</div>
		<div class="easyui-paddingbfpx">	<label style="padding-left:10px;" class='mandatory-lbl'> Avg.</label>	</div>
	</div>
	</td>
	<td width="5%" style="border:1px solid black;">
	<div id="divLblMinVal">
		<div class="easyui-paddingbfpx">	<label id="min" style="padding-left:10px;"> 25</label>	</div>
		<div class="easyui-paddingbfpx">	<label id="max" style="padding-left:10px;"> 35</label>	</div>
		<div class="easyui-paddingbfpx">	<label id="Avg" style="padding-left:10px;"> 30</label>	</div>
	</div>
	</td>

	</tr>
	<tr><td>
	
	<div id="divHours" class="easyui-paddingbfpx" style="padding-left: 2%;">
	</div>
	</td></tr>
	
	</table>
	
	<div class="easyui-paddingbfpx" style="padding-left: 2%;">					
		<table id='gridSLAEntry'>
		</table>
		<div id='pager3'></div>
	</div>
	
	<input type="hidden" id="txtslemFrqtype" name="txtslemFrqtype" value="${requestScope.JhnTlSlaentry.slemFrqtype}" />
	<input type="hidden" id="txtSlemKeyid"   name="txtSlemKeyid" value="${requestScope.JhnTlSlaentry.SlemKeyid}" />
	<input type="hidden" id="txtSlemSladid"  name="txtSlemSladid" value="${requestScope.JhnTlSlaentry.slemSladid}" />
	<input type="hidden" id="txtslemDate"    name="txtslemDate" value="${requestScope.JhnTlSlaentry.slemDate}" />
	<input type="hidden" id="txtslemSlasum"  name="txtslemSlasum" value="${requestScope.JhnTlSlaentry.slemSlasum}" />
	<input type="hidden" id="txtSlemCount"   name="txtSlemCount" value="${requestScope.JhnTlSlaentry.SlemCount}" />
	<input type="hidden" id="txtSlemMax"     name="txtSlemMax" value="${requestScope.JhnTlSlaentry.SlemMax}" />
	<input type="hidden" id="txtSlemMin"     name="txtSlemMin" value="${requestScope.JhnTlSlaentry.SlemMin}" />
	<input type="hidden" id="txtSlemDeviation" name="txtSlemDeviation" value="${requestScope.JhnTlSlaentry.SlemDeviation}" />
	<input type="hidden" id="txtsledslemid" name="txtsledslemid" value="${requestScope.JhnTlSlaentrydtl.sledslemid}" />
	
	<input type="hidden" id="txtfrmdmt" name="txtfrmdmt" value="${requestScope.frmdmt}" />
	<input type="hidden" id="txttodmt" name="txttodmt" value="${requestScope.todmt}" />
	<input type="hidden" id="txtmonth" name="txtmonth" value="${requestScope.month}" />
	<input type="hidden" id="txtsladid" name="txtsladid" value="${requestScope.sladid}" />
	<input type="hidden" id="txtlockdate" name="txtlockdate" value="${requestScope.lockdate}" />
	<input type="hidden" id="txtlockcurdate" name="txtlockcurdate" value="${requestScope.lockcurdate}" />
	
	<input type="hidden" id="txtslemid" name="txtslemid" value="${requestScope.slemid}"/>
	<input type="hidden" id="txtsladid" name="txtsladid" value="${requestScope.sladid}" />
	<input type="hidden" id="txtfreq" name="txtfreq" value="${requestScope.freq}" />
	<input type="hidden" id="txtslmkeyid" name="txtslmkeyid" value="${requestScope.slmkeyid}" />
	<input type="hidden" id="hdncurdte" name="hdncurdte" value="${requestScope.splitdate}" />
	
	<input type="hidden" id="txtQltyremrks" name="txtQltyremrks" value="${requestScope.Qltyremrks}" />
	<input type="hidden" id="txtproblem" name="txtproblem" value="" />
	<input type="hidden" id="txtdatecount" name="txtdatecount" value="" />
	
	<input type="hidden" id="hdnselvalue" name="hdnselvalue" value="" />
    <input type="hidden" id="hdnfrequency" name="hdnfrequency" value=""/>
	
	
	
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
	<input type="hidden" class="easyui-button" id="hdnSelHourId"	name="hdnSelHourId"  />
	<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />
	<!--<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />-->
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
</form>