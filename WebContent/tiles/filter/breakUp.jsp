<script>
	jQuery(document).ready(function(){
		initialiseForm('frmBreakUp');
		jQuery('#submitForm').val('frmBreakUp');
		var hdnfrom = getFieldValue('fromDate');
		var hdnTo = getFieldValue('toDate');
		jQuery('#dtefromMonthYear').datebox({
			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
			 onSelect:function(date){			
					var onSelectFunctionName = 'dtefromMonthYear_onSelect';
					if( typeof eval('('+onSelectFunctionName +')') == 'function')
					{
						eval('( '+ onSelectFunctionName +'(date))');
					}
				}
		 });  
	
		 jQuery('#dtetoMonthYear').datebox({  
			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
			 onSelect:function(date){			
					var onSelectFunctionName = 'dtetoMonthYear_onSelect';
					if( typeof eval('('+onSelectFunctionName +')') == 'function')
					{
						eval('( '+ onSelectFunctionName +'(date))');
					}	
				}
		 });  
		 
		 setFieldValue('dtefromMonthYear',hdnfrom);
		 setFieldValue('dtetoMonthYear',hdnTo);
		 jQuery("#dtefromMonthYear").datebox('disable');
		 jQuery("#dtetoMonthYear").datebox('disable');
		 
		processGridnew("breakUp_input.commonFilter","?q=2","breakUpGrid","pager","","","","");
		var fromdate = jQuery('#dtefromMonthYear').datebox('getValue');
		jQuery("#hdnfromdate").val(fromdate);
		setTimeout(function() {
			var row = jQuery("#breakUpGrid").jqGrid('getDataIDs');
			addFirstRow(row);
			filldate("","","");
		},500);
	});
	jQuery('#btnCancel').click(function(){
		closePopUpDialoge("divBreakUp");	
	});
	jQuery('#btnApply').click(function(){
		var row=jQuery("#breakUpGrid").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
		for(var i=1;i<=row.length;i++)
		{
			fromVal=getFieldValue('dteFrom_'+i+'_0');
			toVal=getFieldValue('dteTo_'+i+'_1');
			freq=getFieldValue('cboBreakUp_'+i+'_2');
			if(toVal!=""){
				if (freq==" ")
				{
					alert("Select Break up");
					return false;
				}else{
				 	jsonArr+= '{';
				 	jsonArr += '"cboFREQ":"'+freq+'",';
					jsonArr += '"dteBFROM":"'+fromVal+'",';
					jsonArr += '"dteBTO":"'+toVal+'"},';
				}
			}
		}
		jsonArr = jsonArr.slice(0,-1)+ "]";
		if (jsonArr=="]"){
			jsonArr=""
		}
		alert(jsonArr);
		closePopUpDialoge("divBreakUp");	
		jQuery('#hdnbreakup').val(jsonArr);
	});
	function BreakUp_Formatter(rowNo, options, rowObject){
		var rowNo = options.rowId;
		var columnNo = options.pos;
		var opt;
		opt='<select id="cboBreakUp_'+rowNo+'_'+columnNo+'" class="easyui-combobox"  onchange="cboBreakUp_onChange('+rowNo+','+columnNo+');" style="width:150px;padding-left:10px;">';
		opt+='<option value=" "> </option>';
		opt+='<option value="M">Monthly</option>';
		opt+='<option value="Y">Yearly</option>';
		opt+='<option value="Q">Quarterly</option>';
		opt+='<option value="H">Halfyearly</option>';
		opt+='</select>';
		return opt;
	}
	function From_Formatter(rowNo, options, rowObject){
		var rowNo = options.rowId;
		var columnNo = options.pos;
		var fromDate = jQuery('#dtefromMonthYear').datebox('getValue');// value="'+fromDate+'" 
		return '<input id="dteFrom_'+rowNo+'_'+columnNo+'" name="dteFrom_'+rowNo+'_'+columnNo+'" style="width:90px;" class="easyui-datebox"  />';	
	}
	function To_Formatter(rowNo, options, rowObject){
		var rowNo = options.rowId;
		var columnNo = options.pos;
	
		var toDate = jQuery('#dtetoMonthYear').datebox('getValue');// value="'+toDate+'"
		return '<input id="dteTo_'+rowNo+'_'+columnNo+'" name="dteTo_'+rowNo+'_'+columnNo+'" style="width:90px;" class="easyui-datebox"/>';
	}
	function compareTwoDate(fromDateTimeStr,toDateTimeStr)
	{	
		if(convertStringToDate(fromDateTimeStr)>=convertStringToDate(toDateTimeStr))
			return -1;
		else
			return 1;
	}


	function cboBreakUp_onChange(rownum,colnum)
	{
		var row = jQuery("#breakUpGrid").jqGrid('getDataIDs');
		var cmbVal = jQuery('#cboBreakUp_'+rownum+'_'+colnum).val();
		var QArray=new Array("Mar","Jun","Sep","Dec");
		var HArray = new Array("Jun","Dec");
		
		var ToMonthYear=getFieldValue('dteTo_'+rownum+'_1');
		var FromMonthYear=getFieldValue('dteFrom_'+rownum+'_0');
		var from=convertStringToDate("01-"+FromMonthYear).getMonth();
	 	var fromMonth=getMonthStringFromInt(from);
	 	var to=convertStringToDate("01-"+ToMonthYear).getMonth();
	 	var toMonth=getMonthStringFromInt(to);
	 	var fromYear=convertStringToDate("01-"+FromMonthYear).getFullYear();
		var toYear=convertStringToDate("01-"+ToMonthYear).getFullYear();
		var tocheck = getFieldValue('dtetoMonthYear');
		if (ToMonthYear==""){
			if (cmbVal =="Q"){
				if(fromMonth !='Jan' &&fromMonth != 'Apr' && fromMonth != 'Jul' && fromMonth != 'Oct' )
				{
					setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
					setFieldValue('dteTo_'+rownum+'_1', "" );
					alert("From Month Should be Jan, Apr, Jul or Oct");
					return false;
				}
				else
				{
					var checkQuater=getMonthStringFromInt(from+2)+"-"+fromYear;
					if(compareTwoDate("01-"+tocheck,"01-"+checkQuater)!='1'){
						addRow(row,rownum);	
						filldate((rownum+1),colnum,cmbVal);
					}
					else{
						setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
						setFieldValue('dteTo_'+rownum+'_1', "" );
						alert("To Date Should Not Be Greater Than "+tocheck);
						return false;
					}
				}
			}
			else if (cmbVal =="Y"){
				if(fromMonth !='Jan' )
				{
					setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
					setFieldValue('dteTo_'+rownum+'_1', "" );
					alert("From Month Should be Jan");
					return false;
				}
				else
				{
					var checkYear=getMonthStringFromInt(from+10)+"-"+fromYear;
					if(compareTwoDate("01-"+tocheck,"01-"+checkYear)!='1'){
						addRow(row,rownum);	
						filldate((rownum+1),colnum,cmbVal);
					}
					else{
						setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
						setFieldValue('dteTo_'+rownum+'_1', "" );
						alert("To Date Should Not Be Greater Than "+tocheck);
						return false;
					}
				}
			}
			else if (cmbVal =="H"){
				if(fromMonth !='Jan' &&fromMonth != 'Jul' )
				{
					setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
					setFieldValue('dteTo_'+rownum+'_1', "" );
					alert("From Month Should be Jan or Jul");
					return false;
				}
				else
				{
					var checkHalf=getMonthStringFromInt(from+5)+"-"+fromYear;
					if(compareTwoDate("01-"+tocheck,"01-"+checkHalf)!='1'){
						addRow(row,rownum);	
						filldate((rownum+1),colnum,cmbVal);
					}
					else{
						setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
						setFieldValue('dteTo_'+rownum+'_1', "" );
						alert("To Date Should Not Be Greater Than "+tocheck);
						return false;
					}
				}
			}
			else{
				if (from>10)
					var checkMonthly=getMonthStringFromInt(0)+"-"+fromYear;
				else
					var checkMonthly=getMonthStringFromInt(from+1)+"-"+fromYear;
				if(compareTwoDate("01-"+tocheck,"01-"+checkMonthly)!='1'){
					addRow(row,rownum);	
					filldate((rownum+1),colnum,cmbVal);
				}
				else{
					setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
					setFieldValue('dteTo_'+rownum+'_1', "" );
					alert("To Date Should Not Be Greater Than  "+tocheck);
					return false;
				}
			}
		}
		else
		{
			if(compareTwoDate("01-"+tocheck,"01-"+ToMonthYear)!='1'){
				if (cmbVal =="M"){
					if(toYear<fromYear){
						setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
						setFieldValue('dteTo_'+rownum+'_1', "" );
						alert("To-Year Should be Greater");
						return false;
					}
					else{
						addRow(row,rownum);	
						filldate((rownum+1),colnum,cmbVal);
					}
				}
				else if (cmbVal =="Y"){
					if ((fromMonth !='Jan' || toMonth != 'Dec' )||(toYear < fromYear)){
						if (fromMonth !='Jan' || toMonth != 'Dec' ){
							if (fromMonth !='Jan' ){
								setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
								setFieldValue('dteTo_'+rownum+'_1', "" );
								alert("From Month Should Be Jan ");
								return false;
							}else if (toMonth != 'Dec' ){
								setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
								setFieldValue('dteTo_'+rownum+'_1', "" );
								alert("Month Should Be Jan to Dec");
								return false;
							}
						}else if (toYear < fromYear){
							setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
							setFieldValue('dteTo_'+rownum+'_1', "" );
							alert("Year is Not Correct");
							return false;
						}
					}
					else
					{
						addRow(row,rownum);
						filldate((rownum+1),colnum,cmbVal);
					}
				}
				else if (cmbVal =="H"){
					var checkHalf=getMonthStringFromInt(from+5);
					if (((fromMonth !='Jan'&& fromMonth !='Jul' )||(toYear < fromYear) )|| ((fromMonth !='Jan' && fromMonth !='Jul' )||(toMonth != checkHalf))){
						if(fromYear>toYear){
							setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
							setFieldValue('dteTo_'+rownum+'_1', "" );
							alert("Year is Not correct");
							return false;
						}
						
						if (fromMonth !='Jan' && fromMonth !='Jul' )
						{
							setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
							setFieldValue('dteTo_'+rownum+'_1', "" );
							alert("Select Jan(or) Jul");
							return false;
						}
						if (toMonth != checkHalf){
							var i=0;
							for (var k = 0; k < HArray.length; k++) {
					            if (HArray[k] != toMonth) {
						            i++;
						            if (i>=HArray.length){
							            setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
										setFieldValue('dteTo_'+rownum+'_1', "" );
										alert("To Month Does Not Fall in Half-yearly");
										return false;
									}
					            }else
								{
									addRow(row,rownum);	
									filldate((rownum+1),colnum,cmbVal);
								}
					        }
						}
					}
					else 
					{
						addRow(row,rownum);
						filldate((rownum+1),colnum,cmbVal);
					}
				}
				else if (cmbVal =="Q"){
					var checkQuarterly=getMonthStringFromInt(from+2);
					
					if (((fromMonth !='Jan' && fromMonth != 'Apr' && fromMonth != 'Jul' && fromMonth != 'Oct' )|| (fromYear>toYear)) || ((toMonth != checkQuarterly)|| (fromYear>toYear))){
						if (fromYear>toYear){
							setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
							setFieldValue('dteTo_'+rownum+'_1', "" );
							alert("Year is Not Correct");
							return false;
						}
						if(fromMonth !='Jan' && fromMonth != 'Apr' && fromMonth != 'Jul' && fromMonth != 'Oct' )
						{
							setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
							setFieldValue('dteTo_'+rownum+'_1', "" );
							alert("From Month Should Be Jan or Apr or Jul or Oct");
							return false;
						}
						if(toMonth != checkQuarterly)
						{
							var i=0;
							for (var k = 0; k < QArray.length; k++) {
					            if (QArray[k] != toMonth) {
						            i++;
						            if (i>=QArray.length){
							            setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
										setFieldValue('dteTo_'+rownum+'_1', "" );
										alert("To Month Does Not Fall in Quarterly");
										return false;
									}
					            }else
								{
									addRow(row,rownum);	
									filldate((rownum+1),colnum,cmbVal);
								}
					        }
						}
					}
					else
					{
						addRow(row,rownum);	
						filldate((rownum+1),colnum,cmbVal);
					}
				}
			}
			else{
				setFieldValue('cboBreakUp_'+rownum+'_'+colnum, " " );
				setFieldValue('dteTo_'+rownum+'_1', "" );
				alert("To Date Should Not Be Greater Than "+tocheck);
				return false;
			}
		}
	}
	function addFirstRow(row){
		if ( row == null || row == '' || parseInt(row) <= 0) {
		 	var emptyItem =[{dteFrom:" ",dteTo:" ",txtBreakUp:" "}];
			jQuery("#breakUpGrid").jqGrid('addRowData',1, emptyItem[0]);	
			dateBox(row);	
		 }		
	}
	function addRow(row,rownum)
	{
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{dteFrom:" ",dteTo:" ",txtBreakUp:" "}];
		jQuery("#breakUpGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
		dateBox(rownum);
	}
	function dateBox(rownum)
	{	
		var row=jQuery("#breakUpGrid").jqGrid('getDataIDs');	
		var col=jQuery("#breakUpGrid").jqGrid("getGridParam","colModel");
		var rowid=rownum+1;
		var fromdate="";
		var todate="";
	
		fromdate='dteFrom_'+rowid+'_0';
		todate='dteTo_'+rowid+'_1';
		
		jQuery('#'+fromdate).datebox({  
			formatter: function(date){ return (getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		 });
		jQuery('#'+todate).datebox({  
			formatter: function(date){ return (getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		});
		jQuery("#"+fromdate).datebox('disable');
	}
	function filldate(rownum,colnum,cmbVal)
	{
		var FromMonthYear=jQuery('#dtefromMonthYear').datebox('getValue');
		var tocheckmon=getFieldValue('dtetoMonthYear');
		if (rownum=="") {
	 		setFieldValue('dteFrom_1_0', FromMonthYear );
		}
		else if (rownum>1)
		{
			var nowFrom=getFieldValue('dteFrom_'+(rownum-1)+'_0');
		 	var nowfromYr=convertStringToDate("01-"+nowFrom).getFullYear();
		 	var ToMonthYear=getFieldValue('dteTo_'+(rownum-1)+'_1');
		 	if (cmbVal=="M"){
		 		if(ToMonthYear==""){
					var nowfromMon=convertStringToDate("01-"+nowFrom).getMonth()+1;
		 		}
		 		else{
		 			var nowfromMon=convertStringToDate("01-"+ToMonthYear).getMonth();
		 			var nowfromYr=convertStringToDate("01-"+ToMonthYear).getFullYear();
		 		}
	
		 		if (nowfromMon>11){
			 		var nowfromYr=(convertStringToDate("01-"+nowFrom).getFullYear())+1;
				 	var nowfromMonth=getMonthStringFromInt(0);
			 	}
			 	else{
			 		var nowfromMonth=getMonthStringFromInt(nowfromMon);
			 	}
			 	var nowdate=nowfromMonth+"-"+nowfromYr;
		 		setFieldValue('dteTo_'+(rownum-1)+'_1', nowdate );
			 }
		 	else if (cmbVal=="H"){
		 		var nowfromMon=convertStringToDate("01-"+nowFrom).getMonth();
		 		if (nowfromMon<=5){
		 			var nowfromMonth=getMonthStringFromInt(5);
		 		}
		 		else if (nowfromMon>5){
		 			var nowfromMonth=getMonthStringFromInt(11);
		 		}
		 		if(ToMonthYear!=""){
		 			var nowfromMon=convertStringToDate("01-"+ToMonthYear).getMonth();
		 			var nowfromMonth=getMonthStringFromInt(nowfromMon);
		 			var nowfromYr=convertStringToDate("01-"+ToMonthYear).getFullYear();
		 		}
		 		var nowdate=nowfromMonth+"-"+nowfromYr;
		 		setFieldValue('dteTo_'+(rownum-1)+'_1', nowdate );
		 	}
		 	else if (cmbVal=="Y"){
		 		var nowfromMonth=getMonthStringFromInt(11);
			 	if(ToMonthYear!=""){
		 			var nowfromYr=convertStringToDate("01-"+ToMonthYear).getFullYear();
		 		}
			 	var nowdate=nowfromMonth+"-"+nowfromYr;
			 	setFieldValue('dteTo_'+(rownum-1)+'_1', nowdate );
		 	}
		 	else if (cmbVal=="Q"){
		 		var nowfromMon=convertStringToDate("01-"+nowFrom).getMonth();
		 		var nowfromMon=convertStringToDate("01-"+nowFrom).getMonth()+2;
				var nowfromYr=convertStringToDate("01-"+nowFrom).getFullYear();
		 		if(ToMonthYear!=""){
		 			var nowfromMon=convertStringToDate("01-"+ToMonthYear).getMonth();
		 			var nowfromYr=convertStringToDate("01-"+ToMonthYear).getFullYear();
		 		}
		 		var nowfromMonth=getMonthStringFromInt(nowfromMon);
		 		var nowdate=nowfromMonth+"-"+nowfromYr;
				
				setFieldValue('dteTo_'+(rownum-1)+'_1', nowdate );
		 	}
		 	var tobef= getFieldValue('dteTo_'+(rownum-1)+'_1');
		 	var checkMonth=convertStringToDate("01-"+tobef).getMonth();
		 	if (checkMonth>10){
		 		var fromYr=(convertStringToDate("01-"+tobef).getFullYear())+1;
			 	var fromMonth=getMonthStringFromInt(0);
		 	}
		 	else{
			 	var fromMon=convertStringToDate("01-"+tobef).getMonth()+1;
		 		var fromYr=convertStringToDate("01-"+tobef).getFullYear();
			 	var fromMonth=getMonthStringFromInt(fromMon);
		 	}
	 		var datenew=fromMonth+"-"+fromYr;
 			setFieldValue('dteFrom_'+rownum+'_0', datenew);
 			jQuery('#dteTo_'+(rownum-1)+'_1').datebox('disable');
 			jQuery('#cboBreakUp_'+(rownum-1)+'_2').prop('disabled', 'disabled');
		}
	}

</script>
<form id="frmBreakUp">
	<div>
		<div style="margin-left:20%">
			<span><label>From Month-Year</label>
			</span>
			<span>
				<input id="dtefromMonthYear" class="easyui-datebox" clear="false" style="width:100px;" value=""/>
			</span>
			<span><label>To Month-Year</label>
			</span>
				<input id="dtetoMonthYear" class="easyui-datebox" clear="false" style="width:100px;" value=""/>
			<span>
			</span>
		</div>
		<div>
			<table id="breakUpGrid"><tr><td/></tr></table>	
			<div id="pager"></div>			
		</div>
		<div align="center" style="margin-top: 2%;">
			<span>
				<input type="button" id="btnApply"  class="easyui-button"  value="Apply" />
			</span>
			<span>
				<input type="button" id="btnCancel"  class="easyui-button"  value="Cancel" />
			</span>
		</div>
<!--		<input type="hidden" id="hdndte" class="easyui-datebox" clear="false" style="width:100px;" />-->
		<input type="hidden" id="hdnfromdate" name="hdnfromdate">
		<input type="hidden" id="mode" name="mode">
		<input type ="hidden" id="fromDate" value="${requestScope.FromDate}" />
		<input type ="hidden" id="toDate" value="${requestScope.ToDate}" />
	</div>
</form>