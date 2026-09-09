 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
	jQuery(document).ready(
			function() {
				
				initialiseForm('frmSLAMasterReport');
				var source = jQuery('#hdnsource').val();
				
				fillComboBox("frmSLAMasterReport", "cmbDmt1", "sectionCombo.commonFilter");
               //alert(source);
				if (source == "Internal")
					{fillComboBox("frmSLAMasterReport", "cmbDmt2", "sectionCombo.commonFilter");}
				else
					{fillComboBox("frmSLAMasterReport", "cmbDmt2", "customer.commonFilter");}
				
				//formatDateBox('dteFrom','MMM-yyyy');	
				//formatDateBox('dteTo','MMM-yyyy');	
				
				jQuery('#dteFrom').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });

				 jQuery('#dteTo').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });


				 jQuery("#dtetolabel").hide();
				 jQuery("#dtetovalue").hide();
				 var mode = jQuery("#hdnmode").val();

				 if (mode == "View")
				 {						 
					 fillWithCurrentMonth("dteFrom",1);
				 	jQuery('#btnReport').hide();
				 	jQuery('#btnsubauthen').hide();
				 }
				 else
			     {
					 fillWithCurrentMonth("dteFrom");
				 }
				 if (mode == "graph")
				 {						 
					 fillComboBox("frmSLAMasterReport", "cmbDmt2", "sectionCombo.commonFilter");
				 	 jQuery('#btnReport').hide();
					 jQuery("#dtetolabel").show();
					 jQuery("#dtetovalue").show();
					 jQuery('#dteFromlabel').html('From'); 
					 jQuery('#btnsubauthen').hide(); 
				 }
				 fillWithCurrentMonth("dteTo");
				 setFieldValue("hdnallownavg","N");
				 fillWithCurrentDate('hdncurrentdate');
				 
				var url = "servicelevelmaster_input.slam";
				var hdnfrom = jQuery("#hdnfrom").val();
				var gridUrl="servicelevelmaster_input.slam";

				var frmmonth =getFieldValue("dteFrom"); 
				var tomonth =getFieldValue("dteFrom"); 
				
				var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReport"); 
				var todmt =getFieldValue("cmbDmt2","frmSLAMasterReport"); 
				var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
				var type= jQuery("#hdntype").val();
				var section= jQuery("#hdnsection").val();
				//alert("section="+section);
				var role= jQuery("#hdnrole").val();
				var mode= jQuery("#hdnmode").val();
				//alert("role="+role);
				//alert("type"+type);		 
				
				if(gridUrl.length > 20)
				{	
					var mode = jQuery("#hdnmode").val();
					viewGrid(gridUrl,"?q=2&from="+hdnfrom+"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&frmdmt="+frmdmt+"&todmt="+todmt+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type);
				}
				else 
				{		
					var mode = jQuery("#hdnmode").val();
					viewGrid(url,"?q=2&from="+hdnfrom+"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&frmdmt="+frmdmt+"&todmt="+todmt+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type);
				}
                //alert(1);
				//processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+slmkeyid+"&month=01-"+frmmonth+"&type=findcount", "authendcountsuccesssCallback", "", "", "");
				//alert(2);
				    jQuery('#btnView').click(function(){
					    	
					gridUrl = "servicelevelmaster_input.slam";	
					//clearField('hdnslmkeyid');
					frmmonth =getFieldValue("dteFrom"); 
					
					slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
					var type= jQuery("#hdntype").val();
					var mode = jQuery("#hdnmode").val();
					if(mode=="graph"){
					tomonth =getFieldValue("dteTo");  
					}	
					else{
					tomonth =getFieldValue("dteFrom");  
						}				
					var filterstring = "?q=2&frmdmt="+ getFieldValue("cmbDmt1") +"&todmt="+ getFieldValue("cmbDmt2") +"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type ;
					
					viewGrid(gridUrl,filterstring);

					processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+slmkeyid+"&month=01-"+frmmonth+"&type=findcount", "authendcountsuccesssCallback", "", "", "");
						
				});	
				    
				
				var slmkeyid = getFieldValue('hdnslmkeyid');
				
				fileManagerPopUp(slmkeyid,"SLA","frmSLAMasterReport","btnfilemgr","SlaFilemgr");


				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					//alert("Read From File");
					
					processAjaxCalls("openFile.file?fileName=SLA Blank Format.xls", "", "", "", "", "new");					
				});
				
				jQuery('#btnGraph').click(function()
						{
							var rowid = jQuery("#SLAMasterReport").jqGrid('getGridParam','selrow');
							var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',rowid);
							var selId = rowData.sladid;
							
							//getsladidfromgrid();
							var strsladid = jQuery('#hdnsladid').val();
							//alert(strsladid.length);
								//var url = "AbnTagTrendRpt_getChart.abnRpt?rowid="+rowid+"&chType=spline";
								
								if(strsladid.length > 0 )
								{
									var frmmonthrpt =getFieldValue("dteFrom"); 
									var tomonthrpt  = getFieldValue("dteTo"); 
									var slamkeyid =  getFieldValue("hdnslmkeyid"); 
									
									var fromdmt  = getFieldValue("cmbDmt1"); 
									var todmt =  getFieldValue("cmbDmt2"); 
									//alert("fromdmt="+fromdmt);
									//alert("todmt="+todmt);
									
									var url = "servicelevelmaster_getChart.slam?&todmt="+ todmt +"&fromdmt="+ fromdmt +"&sladid="+strsladid+"&slamkeyid="+slamkeyid+"&frommonth="+ frmmonthrpt +"&tomonth="+ tomonthrpt + "&chType=spline&Type=graph";
									showGraphData(url);	
								}
								else
								{
									alert ('Select any One Parameter/Specification');
								}
							
						});
				
			});
	jQuery("#btnsubauthen").click(function(){
		
      alert("Are sure you want permit to authentication");
      var hdnslmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport");
	  var frmmonth =getFieldValue("dteFrom"); 
	  processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+hdnslmkeyid+"&month=01-"+frmmonth+"&type=findcount", "authenticationcountsuccesssCallback", "", "", "");
	    
		
	});
	function authenticationcountsuccesssCallback(result){
		
		var hdnslmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport");
		alert(hdnslmkeyid);
		var frmmonth =getFieldValue("dteFrom"); 
		var count=result.Data[0][0];
		
		if(count>0){
			alert("This Entry is already Submited for authentication");
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
		jQuery("#SLAMasterReport").jqGrid().trigger("reloadGrid");
   }
	jQuery("#btnReport").click(function(){
		var frmmonth =getFieldValue("dteFrom"); 
		var tomonth =getFieldValue("dteFrom"); 
		
		var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReport"); 
		var todmt =getFieldValue("cmbDmt2","frmSLAMasterReport"); 
		var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport");
		var ds="frmmonth="+frmmonth+"&tomonth="+tomonth+"&frmdmt="+frmdmt+"&todmt="+todmt+"&slmkeyid="+slmkeyid;
        //alert("ds"+ds);
        LoadPopUp("divjh","SLAEntryPopupReport_input.slam?q=2&type=report&grid=true&"+ds, true, "95%", "66%", "12%", "1%", " ", "SLA ENTRY REPORT");
        
		});
	function getsladidfromgrid()
	{
		var	rowCnt = jQuery("#SLAMasterReport").getGridParam("reccount");
		
		jQuery('#hdnsladid').val();	
		for (var i = 0; i <=rowCnt ; i++) 
		{
			var chkval = jQuery("#chkslad_"+i).attr('checked');
			
			if(chkval == 'checked')
			{
				var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',i);
				var sladid = jQuery('#hdnsladid').val();
				var newslaid = rowData.sladid;
				sladid = sladid + ',' + "*"+ newslaid +"*";
				jQuery('#hdnsladid').val(sladid);				
			}
			
		}
	}
	function btnfilemgr_click()
	{
		var keyid = jQuery('#hdnslmkeyid').val();
		if(keyid != null && keyid != ''){
			fileManagerPopUp(keyid,"SLA","","","");
	    }
		
	}
	function chkboxCheck(rowId)
	{
		getsladidfromgrid();
		
	}
	function chkboxUnCheck(rowId)
	{

		getsladidfromgrid();
	}
	
	function chkFormater(id, options, rowObject)
	{
		var rowId = options.rowId;	
		var idval;
		idval = 'chkslad';
		return '<input id='+idval+'_'+rowId+' name="slad_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}
	
	function txtFormatter(id, options, rowObject) {

		var id = options.rowId;
		var columnName = options.colModel.name;
		//var columnNo = options.pos;
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var columnVal =rowObject[columnName.substring(columnName.indexOf("_")+1)];
		var colKeyId="";
		var idval;
		idval = 'txtperformance_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id);  type="text"  value="" maxlength="3" style="width: 30px; text-align:right;" / >';		
		
	}
	function gotFocuse(id){

		
		setFieldValue("hdnallownavg","Y");
		//alert("hdnallownavg" + "Y");
		numericTextBox(id);
	
	}
	function viewGrid(url,filterString)
	{  
		processGridnew(url, filterString,"SLAMasterReport", "pager3", "", "SLAMasterReport_DoubleClick",null,"gridLoadComplete");
	}

	function gridLoadComplete_afterLoad(data) {
		
		jQuery("#SLAMasterReport").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			
			
var cm = jQuery("#SLAMasterReport").jqGrid("getGridParam", "colModel");
			
			var colName = cm[cellidx];		
			var dateval =  colName['index'];
			
			dcellidx = cellidx - 14;

			var colN =12;
			var colN1 =12;
			var start = 14;
			var end = cellidx;
			var i=start;
			//alert("cellidx:" +cellidx);
			for (i=start;i<=end; i = i+4){
				//alert("I:" +i);
				if(cellidx>=i && cellidx<=i+4){
					colN = colN1;
				}
				colN1 = colN1 +4;
				
			}
			
			//var colN = 10 + 3*(Math.floor((dcellidx/3))) + ((dcellidx%3)==2?1:(dcellidx%3)) + ((dcellidx%3)==0?1:0);
			/*alert(cellidx);
			var dateval = jQuery("#CH1-" + "12").html();		
			alert("Date Val: " + dateval);
			
			var dateval = jQuery("#CH1-" + "16").html();		
			alert("Date Val: " + dateval);
			*/
			var dateval = jQuery("#CH1-" + colN).html();		
			
			
			
			
			setFieldValue("txtmonth",dateval,"frmSLAMasterReport");							
			setFieldValue("hdnselcelindex",cellidx);
				
			//alert("Date Val: " + dateval);
			
		/*	var cm = jQuery("#SLAMasterReport").jqGrid("getGridParam", "colModel");
			
			var colName = cm[cellidx];		
			var dateval =  colName['index'];
			
			dcellidx = cellidx - 14;

			//var colN = ((cellidx)-(cellidx%3));
			var colN = 10 + 3*(Math.floor((dcellidx/3))) + ((dcellidx%3)==2?1:(dcellidx%3)) + ((dcellidx%3)==0?1:0);
					
			var dateval = jQuery("#CH1-" + colN).html();	
		
			setFieldValue("txtmonth",dateval,"frmSLAMasterReport");		
			//alert(cellidx);
					
			setFieldValue("hdnselcelindex",cellidx);
				
			
			*/
			
		}
		});
	
}

	function authendcountsuccesssCallback(result)
	{
		//alert(1);
		setFieldValue("hdncount",result.Data[0][0]);
		
	}
	function SLAMasterReport_DoubleClick(id) {
	    //alert("Work In progress." +id);
		var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',id);
        //alert("rowdata"+rowData);
		var sladid = rowData.sladid;
		var slemid = rowData.slemid;
		var sleaid = rowData.sleaid;
		//alert("sleaid="+sleaid);
		var freq = rowData.Frequency;
		//alert("slemid"+slemid);
		var effectivedate = rowData.EfectiveDate;	
		//alert("effectivedate:" +effectivedate);
		var currentDate = getFieldValue('hdncurrentdate');
		//alert("currentDate:" +currentDate);
		var curntmonth = currentDate.substring(3,6);
		//alert("curntmonth:" +curntmonth);
		var numcurrentmonth = changeFormatStringtoNumber(curntmonth.trim());
		//alert("numcurrentmonth:" +numcurrentmonth);
		var currentday = currentDate.substring(0,2);
		//alert("currentday:" +currentday);
		//alert( currentDate  + "A"+ currentmonth + "B" + numcurrentmonth  +  "C"+ currentday);
		var effectivemonth = effectivedate.substring(3,6);
		var effectiveday = effectivedate.substring(0,2);
		var numeffectivemonth = changeFormatStringtoNumber(effectivemonth);
		var effectiveyear = effectivedate.substring(7,11);
		var selcmothyear = getFieldValue("txtmonth","frmSLAMasterReport");
		var selmonth = selcmothyear.substring(0,3);
		var numselmonth  = changeFormatStringtoNumber(selmonth);
		var selyear = selcmothyear.substring(4,8);
		var selcellindx = getFieldValue('hdnselcelindex');
		var lockdate = '0';
		var lockcurdate = '0';
		var hdncount=getFieldValue('hdncount');

		
		//alert(hdncount);
		
		
		//alert('selcmothyear--' + selcmothyear +'selmonth' +  selmonth +'selyear' +  selyear + ' numselmonth ' + numselmonth + ' numeffectivemonth ' + numeffectivemonth);
		
		effectivedate = effectivedate.trim();
		//alert("convertStringToDate(effectivedate) " +convertStringToDate(effectivedate));
		//alert("convertStringToDate(currentDate) " +convertStringToDate(currentDate));
		
		//alert ( effectivedate + '   effectivedate ' + convertStringToDate(effectivedate) + '   currentDate' + currentDate);
		
		var selDate = '01-' + selcmothyear;
		
			if(convertStringToDate(effectivedate) > convertStringToDate(currentDate))
			{   
				alert('Effective Date is great than Current Date');
				setFieldValue("hdnallownavg","N");
			}
			
			else if(parseInt(selyear,10) < parseInt(effectiveyear,10))
			{
				alert('Selected Month is lesser than effective date');
				setFieldValue("hdnallownavg","N");
			}

			else if(convertStringToDate(selDate) > convertStringToDate(currentDate))
			{
				alert('Selected Month greater than Current Date');
				setFieldValue("hdnallownavg","N");
			}
			/*
			else if( parseInt(numselmonth,10) < parseInt(numeffectivemonth,10) )
			{
				alert('Selected Month is lesser than effective date');
				setFieldValue("hdnallownavg","N");
			}
			
			else if( parseInt(numselmonth,10) > parseInt(numcurrentmonth,10) )
			{ 
				alert('Selected Month is Greater than Current Month');
				setFieldValue("hdnallownavg","N");
				
			}*/
			else if(parseInt(selcellindx,10) <= 13)
			{
				setFieldValue("hdnallownavg","N");
			}
			else
			{
					
						//alert('Selected Month is lesser than effective date');
						setFieldValue("hdnallownavg","Y");
						//alert( 'selyear ' + selyear + 'effectiveyear ' +  effectiveyear + 'numselmonth ' +  numselmonth + 'numeffectivemonth' + numeffectivemonth);
						if(parseInt(selyear,10) == parseInt(effectiveyear,10))
						{
							if(parseInt(numselmonth,10) == parseInt(numeffectivemonth,10))
							{
								//alert('effectivedate' + effectiveday);
								lockdate = effectiveday-1;
								if(parseInt(effectiveday,10) == parseInt(currentday,10))
								{
									lockcurdate = currentday;
								}
								else
								{
									lockcurdate = currentday;
								}
								
							}
						}
					
				
				}
			
		
		var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReport"); 
		var todmt =getFieldValue("cmbDmt2","frmSLAMasterReport"); 
		//var month = currentDate.substring(3,11);
	    //alert("month"+month);
		//var month =getFieldValue("txtmonth","frmSLAMasterReport");  
		var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
		var AllowToNavigate = getFieldValue("hdnallownavg");
		var mode = jQuery("#hdnmode").val();
		var month =getFieldValue("dteFrom"); 
        var frmmonth = getFieldValue("dteFrom");
		/*var sleafrmqmapproval=rowData.slea_frmqmapproval;
		var sleafrmdmtapproval=rowData.slea_frmdmtapproval;
		var sleatoqmapproval=rowData.slea_toqmapproval;
		var sleatodmtapproval=rowData.slea_todmtapproval;*/
		//alert("sleafrmqmapproval="+sleafrmqmapproval);
		//alert("sleafrmdmtapproval="+sleafrmdmtapproval);
		//alert("sleatoqmapproval="+sleatoqmapproval);
		//alert("sleatodmtapproval="+sleatodmtapproval);
		///compareDate(effectivedate,)
		//alert('AllowToNavigate' + AllowToNavigate);
		//if (AllowToNavigate == "Y")
		//{	
			//setFieldValue("hdnallownavg","N");
			if(mode!=="graph"){
			if (mode == "View")
			{
				//navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&mode="+mode+"&slmkeyid="+slmkeyid+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq,"SLA Report ");
			}
			else
			{   
				
				//alert(123);
				if(sleaid.length == 10){
					alert("This Entry is already submitted for authentication can't be modified");
				}
				else{
				navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&mode="+mode+"&slmkeyid="+slmkeyid+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq+"&lockdate="+lockdate+"&lockcurdate="+lockcurdate,"SLA Entry");
				}
				}
			}
			}
		//}
	
</script>

	<form name="frmSLAMasterReport" id="frmSLAMasterReport" >
		<div style="margin-top:0%;" id='wrapperRpt'>
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx">
						<label>From DMT</label>
					</div>
					
						<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbDmt1" name="cmbDmt1"
							 style="width: 265px;" value="${requestScope.frmdmt}" />
					</div>
					
				</td>
				<td style="padding-left: 20px;" >

					<div class="easyui-paddingbfpx">
						<label>To DMT</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbDmt2" name="cmbDmt2"
							 style="width: 265px;" value="${requestScope.todmt}" />					
					</div>
				</td>							
				<td style="padding-left: 20px;" >					
					<div id="dteFromlabel" class="easyui-paddingbfpx"><label>Month</label></div>					
					<div class="easyui-paddingbfpx">
						<span><input id="dteFrom" name="dteFrom" class="easyui-datebox"  style="width:87px;" value=""  ></span>
					</div>
				</td>
		  <td style="padding-left: 20px;" >
					<div id="dtetolabel"class="easyui-paddingbfpx"><label >To</label></div>				
					<div id="dtetovalue"class="easyui-paddingbfpx">
						<span><input id="dteTo" name="dteTo" class="easyui-datebox"  style="width:87px;" value=""  ></span>
					</div>	
				</td>  
				 <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx" style="margin-top: 15px">
					<span >
						<input class="easyui-button" type="button" value="View"
										id="btnView" name="btnView" style="height: 21px"  /> 
					</span>
					</div>	
				</td>
				  <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx" style="margin-top: 15px">
					<span >
						<input class="easyui-button" type="button" value="Report"
										id="btnReport" name="btnReport" style="height: 21px"  /> 
					</span>
					</div>	
				</td> 
				<td style="padding-left: 20px;" >
				<c:if test="${'graph'  == requestScope.mode }">  
					<div class="easyui-paddingbfpx" style="margin-top: 15px;">
					<span >
						<input class="easyui-button" type="button" value="Graph"
										id="btnGraph" name="btnGraph" style="height: 21px"  /> 
					</span>
					</div>	
					</c:if>
				</td>
				<!--<td >
				<div style="padding-left:4px;padding-top:8px;"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 21px; width : 90px;"/></div>
				</td>
				--><td > 
				<c:if test="${'Entry'  == requestScope.mode }">  
						<div style=" padding-left:-15px; position:relative;margin-top:20px; ">
							 <span  id="SlaFilemgr" style="position:absolute;margin-top:-10px;margin-top:-10px\9;" >
		             </span> 
		             </div>	
            	 </c:if>
            	 <!-- <span style=" padding-left:125px;">
						<input class="easyui-button" type="button" value="Submit for Authentication"
										id="btnsubauthen" name="btnsubauthen" style="height: 21px"  /> 
					</span> -->
				</td>
				
			</tr>
		</table>							
							
		<table id='SLAMasterReport'>
			<tr>
				<td></td>
			</tr>
			
		</table>
		<div id='pager3'></div>
	</div>
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
	<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />
	<input type="hidden" id="txtmonth" name="txtmonth"  value="${requestScope.month}" />
	<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />
	<input type="hidden" id="hdnsource" name="hdnsource"  value="${requestScope.source}" />
	<input type="hidden" id="hdnslmkeyid" name="hdnslmkeyid"  value="${requestScope.slmkeyid}" />
	<input type="hidden" id="hdntype" name="hdntype"  value="${requestScope.type}" />
	<input type="hidden" id="hdnsection" name="hdnsection"  value="${requestScope.section}" />
	<input type="hidden" id="hdnrole" name="hdnrole"  value="${requestScope.role}" />
	<input type="hidden" id="hdnallownavg" name="hdnallownavg"  value="" />
	<input type="hidden" id="hdnselcelindex" name="hdnselcelindex"  value="" />
	
	<input type="hidden" id="hdncurrentdate" name="hdncurrentdate"  value="" />
	
	
	<input type="hidden" id="hdnsladid" name="hdnsladid"  value="" />
	<input type="hidden" id="hdncount" name="hdncount"  value="" />
	
	
</form>
