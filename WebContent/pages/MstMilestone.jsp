<script type="text/javascript">	
	jQuery(document).ready(function(){		
		
		initialiseForm('frmMilestone');

		jQuery('#frmMilestone div[id=dispFunctionalLoc]').html(jQuery("#frmMstPlanAct div[id=dispFunctionalLoc]").html());
		
		jQuery('#submitForm').val('frmMilestone');
		jQuery('#frmMilestone .easyui-text').css('text-transform', 'uppercase');
		formatDateBox('dteMilestoneFromDt','dd-MMM-yyyy');
		formatDateBox('dteMilestoneToDt','dd-MMM-yyyy');
		fillComboBox("frmMilestone","cmbMpmsResponsibility","employee.commonFilter");
		
		setStatus();
		fillMilestoneGrid();
		
		setFieldValue("cmbMpmsStatus" ,jQuery('#hdnmspStatus').val());
		
		jQuery("#btnMultiEmp").click(function(){
		var flid = jQuery("#frmMstPlanAct input[id='flid']").val();   ///TTTTTT
		var keyId=jQuery("#hdnMpmsIndicatorid").val();
        openActionPlan("frmMilestone",keyId,"Mil",flid);
           
        //function openActionPlan(divId,keyid,refDocType,flid)
		//LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/
		//LoadPopUp("MultiSelectEmployee","multiEmpSelect_input.api?q=2",true,"20%","88%","1%","14%","","Employee Selection","","",true,"setFileManagerdimension");
				
		});
		readOnlyFields("cmbMpmsStatus");
		///alert(readOnlyFieldsWithText("cmbMpmsStatus",jQuery('#hdnmspStatus').val()));
		
	});
	function fillMilestoneGrid()
	{
		var fromDate = jQuery('#dteMilestoneFromDt').datebox('getValue');
		var toDate = jQuery('#dteMilestoneToDt').datebox('getValue');
		var resp = jQuery('#cmbMpmsResponsibility').combobox('getValue');
		var cellId = jQuery("#hdnMpmsCellid").val();		
		var indicatorId = jQuery("#hdnMpmsIndicatorid").val();	
		var flid1=jQuery("#hdnMpmsFlid").val();	
		var keyId=jQuery("#hdnMpmsKeyid").val();
		var mode = jQuery("#hdnFormMode").val();	
		var dataStr = '?q=2&fromDate='+fromDate+'&toDate='+toDate+'&resp='+resp;
			dataStr += '&cellId='+cellId+'&indicatorId='+indicatorId+'&mode='+mode;	
			dataStr += '&flid1='+flid1+'&keyId='+keyId;		
			//alert(" Checking ::dataStr :: "+dataStr);
		processGridnew('milestone_input.conf',dataStr,"milestoneGrid","","","","","milestoneLoad","milestoneError");
	}
	function setStatus()
	{
		var status = jQuery('#cmbMpmsStatus').val();
		//alert(" status :: "+status);
		var mode = jQuery("#hdnFormMode").val();
		
		if(status!=null && status!= ''&& status!=' ')
		{
			jQuery('#cmbMpmsStatus').combobox('setValue',status);
			if(status == 'C')
			{
				readOnlyFields('dteMilestoneFromDt');
				readOnlyFields('dteMilestoneToDt');
				readOnlyFields('cmbMpmsResponsibility');
				readOnlyFields('cmbMpmsStatus');
			}
		}
		if(mode == 'PLAN')
			readOnlyFields('cmbMpmsStatus');
	}
	function addRow(row)
	{
		 var flag =  jQuery("#rowFlag").val();
		 if(flag != "Y")
		 {
			 if ( row == null || row == '' || parseInt(row) <= 0) {	
				jQuery("#rowFlag").val("Y");		
			 	var emptyItem =[{txtMspdKeyid:" ",txtMspdMilestone:" ",txtMspdTargetdate:" ",chkRevised:" ",dteRevisedTargetDate:" ",dteRevisedTargetDate:" ",txtMspdAssignedto:" ",txtMspdStatus:" ",txtMspdRemarks:" ",btnDeleteMilestone:" ",btnHistoryMilestone:" "}];
				jQuery("#milestoneGrid").jqGrid('addRowData',1, emptyItem[0]);
				
			 }	
			 else
			 {
				for(var i=0;i<row.length;i++)
						lastRow = row[i];
				jQuery("#rowFlag").val("Y");
				var emptyItem =[{txtMspdKeyid:" ",txtMspdMilestone:" ",txtMspdTargetdate:" ",chkRevised:" ",dteRevisedTargetDate:" ",dteRevisedTargetDate:" ",txtMspdAssignedto:" ",txtMspdStatus:" ",txtMspdRemarks:" ",btnDeleteMilestone:" ",btnHistoryMilestone:" "}];
				jQuery("#milestoneGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
			 }
		 }
	}
	function milestoneLoad()
	{		
		if(screen.width <=1300)
		{				
			 jQuery( "#milestoneGrid" ).setGridHeight('40%');
			 jQuery( "#milestoneGrid" ).setGridWidth('23%');			
		}
		var row = jQuery("#milestoneGrid").jqGrid('getDataIDs');
		var mode = jQuery("#hdnFormMode").val();
		var status = jQuery('#txtMstStatus').val();	
		
		if(mode =null&&mode!=''&&mode!=' ')
		{
			if(mode != 'ACTUAL' && status != 'C')
				addRow(row);
		}
		else
		{
			if(status != 'C')
				addRow(row);
		}
		var flid = jQuery("#frmMstPlanAct input[id='flid']").val();	
		for(var id = 1; id<=row.length; id++) {	
			formatDateBox('dteTargetdate'+id,'dd-MMM-yyyy');
			//readOnlyFields('chkRevised'+id);
			formatDateBox('dteRevTargetdate'+id,'dd-MMM-yyyy');
			readOnlyFields('dteRevTargetdate'+id);		
			formatDateBox('dteCompdate'+id,'dd-MMM-yyyy');
			fillComboBoxWithGrid("frmMilestone","cmbAssignedto"+id,"employee.commonFilter?q=2&flid="+flid);
			fillComboBoxWithGrid("frmMilestone","cmbResponsibility"+id,"employee.commonFilter");
			fillComboBoxWithGrid("frmMilestone","cmbCompeletedBy"+id,"employee.commonFilter?q=2&flid="+flid);
			//jQuery("#frmMilestone select[id='cmbStatus_'"+id+"]").attr('disabled',"disabled");
			if(jQuery("#milestoneGrid").getCell(id, 'txtMspdKeyid') == " ")
			{
				//disableUIButton("del_"+id);
				disableUIButton("hist_"+id);
			}
			var sta =jQuery("#cmbStatus_"+id).val();
			if(sta =='C'){
				enableFields("dteCompdate"+id);
			}
			else if(sta =='P' ){
				clearField("dteCompdate"+id);
				readOnlyFields("dteCompdate"+id);
				}
			else if(sta =='S' ){
				clearField("dteCompdate"+id);
				readOnlyFields("dteCompdate"+id);
					}
			else if(sta =='W' ){
				clearField("dteCompdate"+id);
				readOnlyFields("dteCompdate"+id);
						}
		
		}	

			
	}
	function milestone_Formatter(idVal, options, rowObject){
		var id = options.rowId;		
		var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";	
		var status = rowObject[8];
	
		if(status != null && status != '' && status != ' ' && status != 'undefined')
		{		
			if(status == 'C')	
				return '<textarea  id="txtMilestone'+id +'" name="txtMilestone'+id +'" maxlength="90" style="width:240px;" disabled="disabled">'+value+'</textarea>';
			else
				return '<textarea  id="txtMilestone'+id +'" name="txtMilestone'+id +'" maxlength="90" style="width:240px;">'+value+'</textarea>';
		}
		else
			return '<textarea  id="txtMilestone'+id +'" name="txtMilestone'+id +'" maxlength="90" style="width:240px;">'+value+'</textarea>';
		//return '<textarea  id="txtMilestone'+id +'" name="txtMilestone'+id +'" maxlength="40" style="width:200px;">'+value+'</textarea>';	
		//return '<input id="txtMilestone'+id+'" name="txtMilestone'+id+'" class="easyui-text" maxlength="50" style="border:0px;width:120px;" value="'+value+'"/>';
	}	
	function targetDate_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		var fromDate = jQuery('#dteMilestoneFromDt').datebox('getValue');
		var mode = jQuery('#hdnFormMode').val();
		var w = "110px";
		
		if(mode != null && mode != '' && mode != ' ')
		{
			if(mode == 'ACTUAL')
				w = "90px";
		}
		var mode = jQuery('#hdnFormMode').val();
		//alert(" idVal :: "+idVal);
		//var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";		
		if(idVal !=null&&idVal!=''&&idVal!=' '){
			return '<input id="dteTargetdate'+id+'" name="dteTargetdate'+id+'" style="width:'+w+';" class="easyui-datebox" value="'+idVal+'" disabled="disabled" />';
		}
		else{
			return '<input id="dteTargetdate'+id+'" name="dteTargetdate'+id+'" style="width:'+w+';" class="easyui-datebox" value="'+fromDate+'"  onchange="completedDateEvt('+id+')" />';
		}
	}
	
	/*function milestoneGriddteTargetdate_onSelect(record) {alert("Some");
	  completedDateEvt();
    }*/

     //gridId+fieldID +'_onSelect'

    
	function chkRevised_Formatter(id, options, rowObject){     
		var id = options.rowId;
		var dtlId = rowObject[0];	
		var status = rowObject[8];
		if(dtlId != null && dtlId != '' && dtlId != ' ' && dtlId != 'undefined')
		{
			if(status != 'C')
				return '<input id="chkRevised'+id+'" name="chkRevised'+id+'" type="checkbox" onclick="gotRevision('+id +')"/>';
			else 
				return '<input id="chkRevised'+id+'" name="chkRevised'+id+'" type="checkbox" onclick="gotRevision('+id +')" disabled=true/>';	////ttttt
		}
		else
			return '<input id="chkRevised'+id+'" name="chkRevised'+id+'" type="checkbox" onclick="gotRevision('+id +')" disabled=true/>';	
	}
	function revisedTargetDate_Formatter(id, options, rowObject){
		var id = options.rowId;
		return '<input id="dteRevTargetdate'+id+'" name="dteRevTargetdate'+id+'" style="width:110px;" class="easyui-datebox" disabled=true/>';
	}
	function compDate_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		//alert(id);
		//alert(rowObject);
		//var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";
		var mode = jQuery('#hdnFormMode').val();
		var w = "110px";
		if(mode != null && mode != '' && mode != ' ')
		{
			if(mode == 'ACTUAL')
				w = "90px";
		}		
		if(idVal !=null&&idVal!=''&&idVal!=' ')
			return '<input id="dteCompdate'+id+'" name="dteCompdate'+id+'" style="width:'+w+';" class="easyui-datebox" value="'+idVal+'" disabled="disabled"/>';
		else
			return '<input id="dteCompdate'+id+'" name="dteCompdate'+id+'" style="width:'+w+';" class="easyui-datebox"/>';



		}
	function responsibility_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";		
		return '<input id="cmbResponsibility'+id+'" name="cmbResponsibility'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'"/>';
	}
	function assignedto_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		var mode = jQuery('#hdnFormMode').val();
		var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";	
		var status = rowObject[8];
		if(mode != null && mode != '' && mode != ' ')
		{
			if(mode == 'ACTUAL' || status == 'C')
				return '<input id="cmbAssignedto'+id+'" name="cmbAssignedto'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'" />';   //dis
			else
				return '<input id="cmbAssignedto'+id+'" name="cmbAssignedto'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'"/>';
		}
		else
			return '<input id="cmbAssignedto'+id+'" name="cmbAssignedto'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'"/>';
	}
	function compBy_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";	
		var status = rowObject[8];	
		if(status != null && status != '' && status != ' ' && status != 'undefined')
		{		
			if(status == 'C')
				return '<input id="cmbCompeletedBy'+id+'" name="cmbCompeletedBy'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'" disabled="disabled"/>';
			else		
				return '<input id="cmbCompeletedBy'+id+'" name="cmbCompeletedBy'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'"/>';
		}
		else
			return '<input id="cmbCompeletedBy'+id+'" name="cmbCompeletedBy'+id+'" class="easyui-combobox" style="width:160px;" value="'+value+'" />';
	}
	
	function status(id){
        //alert(" Checking :: New :: id :: "+id);
          var allRows = jQuery("#milestoneGrid").jqGrid('getDataIDs');
		var s = jQuery('#cmbStatus_'+id).val();
		if(s =='C'){
			enableFields("dteCompdate"+id);
		}
		else if(s =='P' ){
			clearField("dteCompdate"+id);
			readOnlyFields("dteCompdate"+id);
			}
		else if(s =='S' ){
			clearField("dteCompdate"+id);
			readOnlyFields("dteCompdate"+id);
				}
		else if(s =='W' ){
			clearField("dteCompdate"+id);
			readOnlyFields("dteCompdate"+id);
					}
		
		
        for(var i=0; i<allRows.length;i++){
			var id=i+1;
			var status = jQuery('#cmbStatus_'+id).val();
			
			if(status=='C'){
			  setFieldValue("cmbMpmsStatus",status);
			}
			if(status=='P'){ 
			  setFieldValue("cmbMpmsStatus",status);
			  return false;
			}
		
		}
     }
	
	function status_Formatter(idVal, options, rowObject){		
		var id = options.rowId;	
		var mode = jQuery('#hdnFormMode').val();
		var w = "100px";
		if(mode != null && mode != '' && mode != ' ')
		{
			if(mode == 'ACTUAL')
				w = "80px";
		}	
		
		var comboBox = '<select class="easyui-combobox"  id="cmbStatus_'+id+'"   style="width:80;" name="cmbStatus_'+id+'" onchange="status('+id+')" />' ;
		
		var selPend=""; var selComp="";var selWIP="";var selSC ;
		if(mode !=null&&mode!=''&&mode!=' ')
		{
		//	if(mode == 'PLAN')
		//		comboBox = "<select   id='cmbStatus_"+id+"' style='width:"+w+";' name='cmbStatus_"+id+"'class='easyui-combobox' value='Y'>"; //dis
		}
		
		//alert(" idVal :: "+idVal);
		if (idVal=="P") 
		{
			//alert(" P ");
			selPend =" selected='selected' "; 
			//setFieldValue("cmbMpmsStatus",idVal);
			//if(mode == 'ACTUAL')
			// selComp =" selected='selected' "; 
		}
		else if (idVal=="C") 
		{
			//comboBox = "<select   id='"+id+"' style='width:"+w+";' name='cmbStatus_"+id+"'class='easyui-combobox' value='Y' disabled=false >"; // dis  disabled="disabled"
			jQuery('#statusFlag').val(idVal);
			selComp =" selected='selected' ";
			//setFieldValue("cmbMpmsStatus",idVal);
		}
		else if(idVal=="S")   //T
		{
			selSC =" selected='selected' "; 
		}
		else if(idVal=="W") 
		{
			selWIP =" selected='selected' "; 
		}
		else
		{
			if(mode !=null&&mode!=''&&mode!=' ')
			{
				if(mode == 'PLAN')
					selPend =" selected='selected' "; 
				//else if(mode == 'ACTUAL')
					//selComp =" selected='selected' "; 
			}
		}
		
			
		comboBox += "<option  value=' '>  </option>"; 
		comboBox += "<option  value='P'  " + selPend + ">Pending</option>";
		comboBox += "<option  value='C' " + selComp + ">Completed</option>";
		comboBox += "<option  value='W' " + selWIP + ">Work In Progress</option>";
		comboBox += "<option  value='S' " + selSC + ">Short Close</option>";
		comboBox += "</select>";		
		return comboBox;
	}

	
	function remarks_Formatter(idVal, options, rowObject){
		var id = options.rowId;
		var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";		
		var status = rowObject[8];	
		if(status != null && status != '' && status != ' ' && status != 'undefined')		
		{	
			if(status == 'C')
			   return '<textarea  id="txaRemarks_'+id +'" name="txaRemarks_'+id +'" maxlength="90" style="width:240px;"   disabled="disabled">'+value+'</textarea>';
			else
				return '<textarea  id="txaRemarks_'+id +'" name="txaRemarks_'+id +'" maxlength="90" style="width:240px;">'+value+'</textarea>';
		}
		else
			return '<textarea  id="txaRemarks_'+id +'" name="txaRemarks_'+id +'" maxlength="90" style="width:240px;">'+value+'</textarea>';
			
	}
	function del_Formatter(id, options, rowObject){
		var id = options.rowId;
		return '<img id="del_'+id +'" name="del_'+id +'"   src="images/wrong.png"  onclick="delMilestone(\''+id + '\');"/>';
		//return '<input id="del_'+id +'" name="del_'+id +'" type="button" value="Delete" class="easyui-button"  onclick="delMilestone(\''+id + '\');"/>';
		  	
	}
	function hist_Formatter(id, options, rowObject){
		var id = options.rowId;
		return '<input id="hist_'+id +'" name="hist_'+id +'" type="button" value="History" class="easyui-button"  onclick="openHist(\''+id + '\');"/>';
	}
	function gotRevision(id)
	{
		if(jQuery('#chkRevised'+id).is(':checked') == true)
			
		// jQuery('#dteRevTargetdate').attr({'disabled':false});
		{
			var tgtDate = jQuery('#dteTargetdate'+id).datebox('getValue');
			jQuery('#dteRevTargetdate'+id).datebox('setValue',tgtDate);
			//disableField('dteRevTargetdate'+id);
			enableFields('dteRevTargetdate'+id);
		}
		else
		{
			jQuery('#dteRevTargetdate'+id).datebox('clear');
			//readOnlyFields('dteRevTargetdate'+id);
			disableField('dteRevTargetdate'+id);
		}
	}
	function delMilestone(id)
	{
		
		var key = jQuery("#milestoneGrid").getCell(id, 'txtMspdKeyid');
		var milestone = jQuery("#txtMilestone"+id).val();
		var status = jQuery("#cmbStatus_"+id).val();
		if(key != null && key != '' && key != ' ')
		{
			jQuery("#refreshGridFlag").val('');
			if(status != 'C')
			{
				var msg ="Do You want to Delete";
				if(milestone != null && milestone != '' && milestone != ' ')
					msg += " "+milestone+" ";
				msg += "?";
				if(confirm(msg) == true)
				{				
					var dataString = '?q=2';
					if(key != null && key != '' && key != ' ')
						dataString += '&txtMspdKeyid='+key;
					processAjaxCalls("milestone_del.conf",dataString,"delMilestoneSuccess","delMilestoneError");
				}
			}
			else
				alert('Completed Milestone cannot be deleted');
		}
		else
		{
		     
	        // if(jQuery('#chkRevised'+id).is(':checked') == true)
		     jQuery('#milestoneGrid').delRowData(id);
			  var g = jQuery('#milestoneGrid');		
			  var gridData= jQuery("#jqgrid_id").jqGrid('getRowData');
			  g.setGridParam({ data: gridData });
			  g[0].refreshIndex();
			  var row = jQuery("#milestoneGrid").jqGrid('getDataIDs');
			  
			  for(var i=0;i<row.length;i++)
			  {
				  if(id!=row[i]){ 
					  jQuery('input:checkbox[id=chkRevised'+row[i]+ ']').attr('checked',true);
					  gotRevision(row[i]);
				      //if(jQuery('#chkRevised'+row[i]).is(':checked') == true)
					    //{
			        	//enableFields('dteRevTargetdate'+row[i] );
					    //}
				  }
		       }
	}
	}
	function openHist(id)
	{
		 var key = jQuery("#milestoneGrid").getCell(id, 'txtMspdKeyid');
		 var dataStr = "?q=2&dtlId="+key;

			var flid1=jQuery("#hdnMpmsFlid").val();
			//alert(flid1);
			var keyId=jQuery("#hdnMpmsKeyid").val();
			//alert(keyId);
		 	
		/// alert(dtlId);		 
	 	 LoadPopUp("divHistory","history_view.conf"+dataStr, true,"92%","80%","2%","3%", "History_Callback","History","isOutside",true);
	 	//function LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname)
	}
	function frmMilestone_beforeSubmit()
	{
		var mode = jQuery("#hdnFormMode").val();
		var allRows = jQuery("#milestoneGrid").jqGrid('getDataIDs');
		
			for(var i=0; i<allRows.length;i++){
			
				var id=i+1;
			    var toDate = jQuery('#dteMilestoneToDt').datebox("getValue");
				var fromDate = jQuery('#dteMilestoneFromDt').datebox("getValue");
				var targetDate = jQuery('#dteTargetdate'+id).datebox('getValue');
				if((convertStringToDate(fromDate) > convertStringToDate( targetDate)) || (convertStringToDate(toDate) < convertStringToDate(targetDate)))
				{
				    alert(' Milestone Target Date should be within "from date" and  "to date" ');
					return false;
				}
		     
	    }
		

	 	 jQuery("#refreshGridFlag").val('');
	     // alert(convertMilestoneGridToJSONArr('milestoneGrid','N'));
	 	 return 'milestoneDatas='+convertMilestoneGridToJSONArr('milestoneGrid','N');//	
		 	
	}

 function convertMilestoneGridToJSONArr(jqGridId,toDel){
		//var row=jQuery("#jqGridAnswer").jqGrid('getDataIDs');//	row get data
	
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
		var jsonArrO='[';	
		
		for( var i = 0; i < allRows.length;i++){			
			var row = allRows[i];
			var flag = true;	
			
			var status = '';
			var id = i+1;
		    status = jQuery("#cmbStatus_"+id).val();
		    
		    //alert(" status :: New "+jQuery("#cmbStatus_"+id).attr("selected","selected"));
		    statusMode = jQuery("#cmbStatus_"+id).attr('disabled');
		     // var rowid=row[i];
		      var flid=jQuery("#hdnMpmsFlid").val();
		    
			if(status == 'C' && statusMode == 'disabled')
				flag = false;			
			if(toDel == 'Y')
				flag = true;
			if(flag)
			{			
				jsonArrO += '{';

				for(var colName in row) {
									
					if(colName != 'btnDeleteMilestone' && colName != 'btnHistoryMilestone')
					{
						if("txtMspdFlid"==colName){
						  	jsonArrO += '"'+colName +'":"' + flid +'",';
					   	}else if(row[colName].substring(0,6)!='<input' && row[colName].substring(0,7)!='<select' && row[colName].substring(0,9)!='<textarea')
						{
							jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
						}			
						else
						{
							var x=row[colName].indexOf("id=")+4;
							var y=row[colName].substring(x);
							var z = y.indexOf('"');		
							var val = jQuery('#'+y.substring(0,z)).val();
							if(row[colName].indexOf('checkbox')>=0)
							{
								if(jQuery('#'+y.substring(0,z)).is(':checked') == true)
									val = "Y";
								else
									val = "N";
							}
							if(row[colName].indexOf('combobox')>=0)
								val = colName != 'txtMspdStatus'? jQuery('#'+y.substring(0,z)).combobox('getValue'):val;
							if(row[colName].indexOf('datebox')>=0)
								val = jQuery('#'+y.substring(0,z)).datebox('getValue');
							if(val == 'undefined')	
								val = '';
							  jsonArrO += '"'+colName +'":"' + escape(val) +'",';							
						}	
					}
				}
				
				jsonArrO = jsonArrO.slice(0, -1) + "},"; 
			}
			
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		//alert("jsonArrO  "+jsonArrO);						
		return jsonArrO; 
	}
	
	function frmMilestone_beforeDelete()
	{
		var allow = jQuery("#hdnDelAllow").val('');
		if(allow == 'Y')
		{
			jQuery("#refreshGridFlag").val('');
			var allRows = jQuery("#milestoneGrid").jqGrid('getRowData');
			for( var i = 0; i < allRows.length;i++){
				var id = i+1;
				var milestone = jQuery('#txtMilestone'+id).val();
				//alert(milestone);
				var targetDate = jQuery('#dteTargetdate'+id).datebox('getValue');	
				//alert(" targetDate :: "+targetDate);
				if((milestone == null || milestone == '' || milestone == ' ') || (targetDate == null || targetDate == '' || targetDate == ' '))
				{
					return false;
				}
			}
		
	   		 return 'milestoneDatas='+convertMilestoneGridToJSONArr('milestoneGrid','Y');
		}
		else
		{
			return false;
		}
		
	}
	function frmMilestone_successsCallback(result)
	{
	    /*var allRows = jQuery("#milestoneGrid").jqGrid('getDataIDs');
		
		for(var i=0; i<allRows.length;i++){
			var id=i+1;
			var status = jQuery('#cmbStatus_'+id).val();
		  
			if(status=='C'){
			  setFieldValue("cmbMpmsStatus",status);
			}
			else{ 
			  setFieldValue("cmbMpmsStatus",status);
			  return false;
			}
		
		}
		*/
        jQuery("#rowFlag").val('N');
		fillMilestoneGrid();
	}
	function delMilestoneSuccess(result)
	{
		alert(result.successData.msg);
		jQuery("#rowFlag").val('N');
		fillMilestoneGrid();
	}
	
	function divMileStone_onClose() {	
		var refreshGridFlag = jQuery('#refreshGridFlag').val();
	
		if(refreshGridFlag != 'Y')	
		setTimeout(function() {fillPlanActualGrid();},1200);	
		return true;
	}
</script>
<form id="frmMilestone" name="frmMilestone">
<input type="hidden" id="mode">
<input type="hidden" id="rowFlag">
<input type="hidden" id="statusFlag">
<input type="hidden" id="hdnDelAllow" name="hdnDelAllow" value="${requestScope.formDelAllow}">
<input type="hidden" id="hdnFormMode" name="hdnFormMode" value="${requestScope.flag}">
<input type="hidden" id="hdnMpmsIndicatorid" name="hdnMpmsIndicatorid" value="${requestScope.indicatorId}"/>

<input type="hidden" id="hdnMpmsKeyid" name="hdnMpmsKeyid" value="${requestScope.KeyId}"/> 
<input type="hidden" id="hdnMpmsFlid" name="hdnMpmsFlid" value="${requestScope.flid}"/>

<input type="hidden" id="hdnMpmsFactoryid" name="hdnMpmsFactoryid" value="${requestScope.factId}"/>
<input type="hidden" id="hdnMpmsSectionid" name="hdnMpmsSectionid" value="${requestScope.sectId}"/>
<input type="hidden" id="hdnMpmsCellid" name="hdnMpmsCellid" value="${requestScope.cellId}"/>
<input type="hidden" id="hdnMpmsCellid" name="hdnMpmsCellid" value="${requestScope.cellId}"/>
<input type="hidden" id="hdnMilestoneFromDupDt" name="hdnMilestoneFromDupDt" value="${requestScope.oldFromDate}"/>
<input type="hidden" id="hdnMilestoneToDupDt" name="hdnMilestoneToDupDt" value="${requestScope.oldToDate}"/>
	
	<input type="hidden" id="hdnmspStatus" name="hdnmspStatus" value="${requestScope.mspStatus}"/>
	
	<div style="padding-left:5px;margin-top: -5px;">
		<label class="mandatory-lbl" > Function Location </label>
		<div id="dispFunctionalLoc" style="width:90%;"  class="easyui-paddingbfpx"> 	</div>
	</div>
	<div style="padding-left:5px;padding-top: 5px;">
	
		<span style="padding-left:1px;"> <label class="mandatory-lbl">Category </label></span>
		<span style="padding-left:40px;"> <label class="mandatory-lbl">Sub Category  </label></span>
		<span style="padding-left:16px;"> <label class="mandatory-lbl">Activity  </label></span>
		<span style="padding-left:126px;"> <label class="mandatory-lbl">Plan/Actual  </label></span>
		<span style="padding-left:0px;"> <label class="mandatory-lbl">From Date </label></span>
		<span style="padding-left:30px;"><label class="mandatory-lbl">To Date </label></span>		
		<span style="padding-left:56px;"><label>Responsibility </label></span>	
		<span style="padding-left:158px;"><label>Status </label></span>					
		<span style="padding-left:100px;"><label>Week </label></span>
	</div>
	<div class="easyui-paddingbfpx" style="padding-left:5px;">
	   	<input type="text" class="easyui-text" id = "txtCategory" name="txtCategory"  disabled value="${requestScope.category}"    style="width:100px;background-color:#ece9d8;font-weight:bold;"/>
	   	
	    <span  style="padding-left:1px;">
	    	<input type="text" class="easyui-text" id = "txtSubCategory" name="txtSubCategory"  disabled value="${requestScope.subcategory}"  style="width:100px;background-color:#ece9d8;font-weight:bold;"/>
	    </span>

	    <span  style="padding-left:1px;">
	    	<input type="text" class="easyui-text" id = "txtActivity" name="txtActivity"  disabled value="${requestScope.plan}"  style="width:180px;background-color:#ece9d8;font-weight:bold;"/>
	    </span>

	    <span  style="padding-left:1px;">
	    	<input type="text" class="easyui-text" id = "txtPlanAct" name="txtPlanAct"  disabled value="${requestScope.actualplan}"  style="width:70px;background-color:#ece9d8;font-weight:bold;"/>
	    </span>
	    
	    <span  style="padding-left:1px;">
			<input id="dteMilestoneFromDt" name="dteMilestoneFromDt" class="easyui-datebox"  style="width:100px;" value="${requestScope.fromDate}"/>
		</span>
		<span  style="padding-left:1px;">
			<input id="dteMilestoneToDt" name="dteMilestoneToDt" class="easyui-datebox"  style="width:100px;" value="${requestScope.toDate}"/>
		</span>
	    <span style="padding-left:1px;">
	    	<input  id="cmbMpmsResponsibility" name="cmbMpmsResponsibility" class="easyui-combobox" style="width:200px;" value="${requestScope.mspTlMst.mpmsResponsibility}"/ >	
	    </span>
	    <span style="padding-left:1px;" >
	    	<input type="button" id="btnMultiEmp" class="easyui-button" name="btnMultiEmp" value="..."/>
	    </span>
 		<span style="padding-left:1px;">	
 				<select id="cmbMpmsStatus" class="easyui-combobox" name="cmbMpmsStatus"   style="height: 22px;width:135px;" value="${requestScope.mspStatus}" >
						<option value="P">Pending </option>
						<option value="W">Work In Progress</option>	
						<option value="C">Completed</option>							
				</select>     
    	<input type="hidden" class="easyui-text" id = "txtMstStatus" name="txtMstStatus"  tabindex = "-1" disabled value="${requestScope.mspTlMst.mpmsStatus}" style="width:150px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;"/>
	    </span>
	    
	    <span style="padding-left:1px;">
	    	<input type="text" style="width:50px;" id="txtMpmsPlanstartweek" name="txtMpmsPlanstartweek" class="easyui-text" value="${requestScope.week}"  />
	    </span>
	</div>	
	<div class="easyui-paddingbfpx" style="padding-left:5px;padding-top: -5px;">    		
		<span id="err_cmbMpmsResponsibility" class="tpm-errormsg" style="padding-left:468px;"></span>	
	</div>		
	<div  style="padding-left:5px;float:left;" class="prodPlanDiv">			
		<table id="milestoneGrid" style="float: left;"></table>
<!--	<div id="mstPlanActualPager"></div>-->
	</div>
</form>   