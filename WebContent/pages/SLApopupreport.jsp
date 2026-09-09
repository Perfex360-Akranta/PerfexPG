 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
	jQuery(document).ready(
			function() {
				
				initialiseForm('frmSLAMasterReportpopup');
				//var url = "SLAEntryPopupReport_input.slam";\
				
				var source = jQuery('#hdnsource').val();
				
				fillComboBox("frmSLAMasterReportpopup", "cmbDmt1", "sectionCombo.commonFilter");
               //alert(source);
				if (source == "Internal")
					{fillComboBox("frmSLAMasterReportpopup", "cmbDmt2", "sectionCombo.commonFilter");}
				else
					{fillComboBox("frmSLAMasterReportpopup", "cmbDmt2", "customer.commonFilter");}
				
				//formatDateBox('dteFrom','MMM-yyyy');	
				//formatDateBox('dteTo','MMM-yyyy');	
				
				jQuery('#dteFrom').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });
				 jQuery('#dteTo').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });
				 
				 var mode = jQuery("#hdnmode").val();

				 if (mode == "View")
				 {						 
				 	fillWithPrevMonth("dteFrom",1);
				 }
				 else
			     {
					 fillWithCurrentMonth("dteFrom");
				 }
				 fillWithCurrentMonth("dteTo");
				 setFieldValue("hdnallownavg","N");
				 fillWithCurrentDate('hdncurrentdate');
				 
				var url = "servicelevelmaster_input.slam";
				var hdnfrom = jQuery("#hdnfrom").val();
				var gridUrl="servicelevelmaster_input.slam";
				
				
				
				var hdnfrom = jQuery("#hdnfrom").val();
				//var gridUrl="SLAEntryPopupReport_input.slam";

				var frmmonth =getFieldValue("dteFrom"); 
				var tomonth =getFieldValue("dteFrom"); 
				
				var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReportpopup"); 
				var todmt =getFieldValue("cmbDmt2","frmSLAMasterReportpopup"); 
				var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReportpopup"); 
				var type= "report";		 
				var gridUrl = "servicelevelmaster_input.slam";	
					//clearField('hdnslmkeyid');
					frmmonth =getFieldValue("dteFrom"); 
					tomonth =getFieldValue("dteFrom");  
					slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReportpopup"); 
					
					var mode = jQuery("#hdnmode").val();					
                    var filterstring = "?q=2&frmdmt="+ getFieldValue("cmbDmt1") +"&todmt="+ getFieldValue("cmbDmt2") +"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type ;
					//alert(1);	
	            processGridnew("servicelevelmaster_input.slam",filterstring,"SLAPopupReport", "pager3", "", "",null,"gridLoadComplete");
				    
				
				/*var slmkeyid = getFieldValue('hdnslmkeyid');
				
				fileManagerPopUp(slmkeyid,"SLA","frmSLAMasterReport","btnfilemgr","SlaFilemgr");

				alert("5");
				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					//alert("Read From File");
					
					processAjaxCalls("openFile.file?fileName=SLA Blank Format.xls", "", "", "", "", "new");*/					
				});
				
				/*jQuery('#btnGraph').click(function()
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
									
									
									var url = "servicelevelmaster_getChart.slam?&todmt="+ todmt +"&fromdmt="+ fromdmt +"&sladid="+strsladid+"&slamkeyid="+slamkeyid+"&frommonth="+ frmmonthrpt +"&tomonth="+ tomonthrpt + "&chType=spline";
									showGraphData(url);	
								}
								else
								{
									alert ('Select any One Parameter/Specification');
								}
							
						});
				
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
			var dateval = jQuery("#CH1-" + colN).html();		
			
			
			
			
			setFieldValue("txtmonth",dateval,"frmSLAMasterReport");							
			setFieldValue("hdnselcelindex",cellidx);
			
		}
		});
	
}*/
</script>

	<form name="frmSLAMasterReportpopup" id="frmSLAMasterReportpopup">
		<div style="margin-top:0%;" id='wrapperRpt'>
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" style="padding-left: 20px;display:none;">
						<label>From DMT</label>
					</div>
					
						<div class="easyui-paddingbfpx">
						<input type="hidden" class="easyui-combo" id="cmbDmt1" name="cmbDmt1"
							 style="width: 265px;" value="${requestScope.frmdmt}" />
					</div>
					
				</td>
				<td style="padding-left: 20px;display:none;" >

					<div class="easyui-paddingbfpx">
						<label>To DMT</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input type="hidden" class="easyui-combo" id="cmbDmt2" name="cmbDmt2"
							 style="width: 265px;" value="${requestScope.todmt}" />					
					</div>
				</td>							
				<td style="padding-left: 20px;display:none;" >					
					<div class="easyui-paddingbfpx"><label>Month</label></div>					
					<div class="easyui-paddingbfpx">
						<span><input type="hidden" id="dteFrom" name="dteFrom" class="easyui-datebox"  style="width:87px;" value=""  ></span>
					</div>
				</td>
		 <!--  <td style="padding-left: 20px;display:none;" >
					<div class="easyui-paddingbfpx"><label >To</label></div>				
					<div class="easyui-paddingbfpx">
						<span><input type="hidden" id="dteTo" name="dteTo" class="easyui-datebox"  style="width:87px;" value=""  ></span>
					</div>	
				</td> -->
				 <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx" style="margin-top: 15px">
					<span >
						<input  type="hidden"class="easyui-button" type="button" value="View"
										id="btnView" name="btnView" style="height: 21px"  /> 
					</span>
					</div>	
				</td>
				 <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx" style="margin-top: 15px">
					<span >
						<input  type="hidden" class="easyui-button" type="button" value="Report"
										id="btnReport" name="btnReport" style="height: 21px"  /> 
					</span>
					</div>	
				</td>
				<td style="padding-left: 20px;" >
				<c:if test="${'graph'  == requestScope.mode }">  
					<div class="easyui-paddingbfpx" style="margin-top: 15px;">
					<span >
						<input  type="hidden" class="easyui-button" type="button" value="Graph"
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
						<div style=" padding-left:10px; position:relative; ">
							 <span  id="SlaFilemgr" style="position:absolute;margin-top:-8px;margin-top:-10px\9;" >
		             </span> 
		             </div>	
            	 </c:if>
				</td>
			</tr>
		</table>							
							
		<table id='SLAPopupReport'>
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
	<input type="hidden" id="hdnsleakeyid" name="hdnsleakeyid"  value="${requestScope.sleakeyid}" />
	<input type="hidden" id="hdntype" name="hdntype"  value="${requestScope.type}" />
	<input type="hidden" id="hdnallownavg" name="hdnallownavg"  value="" />
	<input type="hidden" id="hdnselcelindex" name="hdnselcelindex"  value="" />
	<input type="hidden" id="hdnsection" name="hdnsection"  value="${requestScope.section}" />
	<input type="hidden" id="hdnrole" name="hdnrole"  value="${requestScope.rolename}" />	

	<input type="hidden" id="hdncurrentdate" name="hdncurrentdate"  value="" />
	
	
	<input type="hidden" id="hdnsladid" name="hdnsladid"  value="" />
    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
    <input type="hidden" id="hdnfrmdmtqm" name="hdnfrmdmtqm"  value="" />
    <input type="hidden" id="hdncount" name="hdncount"  value="" />
    
</form>
							