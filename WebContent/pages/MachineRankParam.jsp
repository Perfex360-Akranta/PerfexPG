<script>
	jQuery(document).ready(function(){
		initialiseForm('frmMachineRankParam');
		jQuery('#submitForm').val('frmMachineRankParam');
	
		processGridnew("MachRankParam_input.mrp","?q=2","MRParam","pager","","","","");
			jQuery("#btnAddNew").click(function(){
				var row = jQuery("#MRParam").jqGrid('getDataIDs');
				addRow(row);
			});
	});
	function MrkpParametername_Formatter(rowNo, options, rowObject){
		var rowNo = options.rowId;
		var columnNo = options.pos;
		var mode = 'I' ;//Not Changed mode
		var  columnVal =rowObject[parseInt(columnNo)-1];
		var keyid = rowObject[0];

		
		if(columnVal==null || columnVal=='undefined' || columnVal=="")
			columnVal="";
		if (columnNo==2)
			return '<input id="txtMrkpParametername_'+columnNo+'_'+rowNo+'" name="txtMrkpParametername_'+columnNo+'_'+rowNo+'" onfocus=gotFocuse("'+keyid+'","'+columnNo+'","'+rowNo+'"); onblur=lostFocuss("'+keyid+'","'+columnNo+'","'+rowNo+'");  type="text" keyid="'+keyid+'" mode="'+mode+'" value="'+columnVal+'" maxlength="1" style="width:75px;text-align:center;"/>';
		else if (columnNo==4)
			return '<input id="txtMrkpParametername_'+columnNo+'_'+rowNo+'" name="txtMrkpParametername_'+columnNo+'_'+rowNo+'" onfocus=gotFocuse("'+keyid+'","'+columnNo+'","'+rowNo+'"); onblur=lostFocuss("'+keyid+'","'+columnNo+'","'+rowNo+'");  type="text" keyid="'+keyid+'" mode="'+mode+'" value="'+columnVal+'" maxlength="3" style="width:100px;text-align:right;"/>';
		else 
			return '<input id="txtMrkpParametername_'+columnNo+'_'+rowNo+'" name="txtMrkpParametername_'+columnNo+'_'+rowNo+'" onfocus=gotFocuse("'+keyid+'","'+columnNo+'","'+rowNo+'"); onblur=lostFocuss("'+keyid+'","'+columnNo+'","'+rowNo+'"); type="text" keyid="'+keyid+'" mode="'+mode+'" value="'+columnVal+'" style="width:375px;text-align:left"/>';
	}
	function Delete_Formatter(rowNo,options, rowObject)
	{
		 	var keyid = rowObject[0];
		 	return '<input id="btnDelete" class="grdButton" name="btnDelete" type="button" keyid="'+keyid+'" onclick=removeRecord("'+keyid+'"); style="text-align:left"/>';
		 
	}
	function removeRecord(keyid){	
		//alert(" Checking "+keyid);
		if(keyid!=null && keyid!='undefined' && keyid!=""){
			var r=confirm("Do You Want To Delete?");
			if (r==true)
			{
				processAjaxCalls("MachRankParam_remove.mrp", "keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
				return true;
			}
			else 
				return false;
		}
		else{
			var r=confirm("Do You Want To Remove Row?");
			if (r==true)
				jQuery("#MRParam").trigger("reloadGrid");
			else
				 return false;
		} 
			
	}

	function remove_successCallBack(result)
	{
		alert(result.successData);
		jQuery("#MRParam").trigger("reloadGrid");
	}
	function remove_errorCallBack()
	{
	}
	function gotFocuse(keyid,columnNo, rowNo )
	{
		var txtvalue = jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).val();
		var tmptxtVal = jQuery('#hdnmode').val(txtvalue);
		if (columnNo==4){
			numericTextBox("txtMrkpParametername_"+columnNo+"_"+rowNo);
			jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).css('text-align','left');
		}
	}
	function frmMachineRankParam_exceptionCallback(result)
	{
	}
	function frmMachineRankParam_successsCallback(result)
	{
		jQuery("#MRParam").trigger("reloadGrid");
	}
	function lostFocuss(keyid,columnNo, rowNo)
	{
		var txtvalue = jQuery("#txtMrkpParametername_"+(columnNo)+"_"+rowNo).val();
		var tmptxtVal = jQuery('#hdnmode').val();
		if(keyid!=null && keyid!='undefined' && keyid!=""){
			if ((columnNo==4) && (txtvalue>100)){
				alert("Max.Marks is 100");
				if (tmptxtVal!="")
					jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).val(tmptxtVal);
				else
					jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).val("");
			}
			if(txtvalue != tmptxtVal)
				 jQuery("#txtMrkpParametername_"+(columnNo)+"_"+rowNo).attr('mode','U');//Changed mode	
			else if(txtvalue == tmptxtVal)
				jQuery("#txtMrkpParametername_"+(columnNo)+"_"+rowNo).attr('mode','I');
		}
		else{
			if ((columnNo==4) && (txtvalue>100)){
				alert("Max.Marks is 100");
				if (tmptxtVal!="")
					jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).val(tmptxtVal);
				else
					jQuery("#txtMrkpParametername_"+columnNo+"_"+rowNo).val("");
			}
			
			jQuery("#txtMrkpParametername_"+(columnNo)+"_"+rowNo).attr('mode','U');
		}
		if (columnNo==4)
			jQuery("#txtMrkpParametername_"+4+"_"+rowNo).css('text-align','right');
	}
	function addRow(row)
	{
			 if ( row == null || row == '' || parseInt(row) <= 0) {
			 	var emptyItem =[{txtMrkpKeyid:" ",txtMrkpParametername:" ",txtMrkpParametername:" ",txtMrkpParametername:" "}];
				jQuery("#MRParam").jqGrid('addRowData',1, emptyItem[0]);
			 }	
			 else
			 {
				for(var i=0;i<row.length;i++)
						lastRow = row[i];
				var emptyItem =[{txtMrkpKeyid:" ",txtMrkpParametername:" ",txtMrkpParametername:" ",txtMrkpParametername:" "}];
				jQuery("#MRParam").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
			 }
	}
	function frmMachineRankParam_beforeSubmit()
	{
		if (convertMachinerankParam()=="[")
			return false;
		else
		 	return "machineRankParam="+convertMachinerankParam();
	}
	function convertMachinerankParam()
	{
		var row=jQuery("#MRParam").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
	
		for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	
		 	keyval="txtMrkpParametername_"+2+"_"+rowid;	
			keyid=jQuery('#'+keyval).attr('keyid');

			if(keyid==null || keyid=='undefined' || keyid=="")
				keyid="";
			
			resultval="txtMrkpParametername_"+2+"_"+rowid;	
			resultArval=jQuery('#'+resultval).val();
			moderesult=jQuery('#'+resultval).attr('mode');
			
			paramval="txtMrkpParametername_"+3+"_"+rowid;	
			parameterval=jQuery('#'+paramval).val();
			modeParam=jQuery('#'+paramval).attr('mode');
			
			maxval="txtMrkpParametername_"+4+"_"+rowid;	
			maxmarksval=jQuery('#'+maxval).val();
			modemax=jQuery('#'+maxval).attr('mode');
			if(moderesult== 'U'||modeParam== 'U'||modemax== 'U')//If Changed mode
			{
			 	jsonArr+= '{';
			 	jsonArr += '"txtMrkpSlno":"'+rowid+'",';
				jsonArr += '"txtMrkpKeyid":"'+keyid+'",';
				jsonArr += '"txtMrkpResultarea":"'+resultArval+'",';
				jsonArr += '"txtMrkpParametername":"'+parameterval+'",';
				jsonArr += '"txtMrkpMaximummarks":"' + maxmarksval +'"},';
			}
			if(resultArval== '' || maxmarksval== '' || parameterval==''){
				if (maxmarksval== '' && resultArval!= '' && parameterval==''){
					if (maxmarksval== '' && parameterval!=''){
						alert("Enter Max.Marks");
						jsonArr="[";
					}else if (maxmarksval!= '' && parameterval==''){
						alert("Enter Parameter ");
						jsonArr="[";
					}else{
						alert("Enter Max.Marks and Parameter ");
						jsonArr="[";
					}
				}
				else if(resultArval== '' && maxmarksval!= '' && parameterval==''){
					if (resultArval== '' && parameterval!=''){
						alert("Enter Result Area");
						jsonArr="[";
					}else if (resultArval!= '' && parameterval==''){
						alert("Enter Parameter ");
						jsonArr="[";
					}else{
						alert("Enter Result Area and Parameter ");
						jsonArr="[";
					}
				}
				else if(resultArval== '' && maxmarksval== '' && parameterval!=''){
					if (resultArval== '' && maxmarksval!=''){
						alert("Enter Result Area");
						jsonArr="[";
					}else if (resultArval!= '' && maxmarksval==''){
						alert("Enter Max.Marks ");
						jsonArr="[";
					}else{
						alert("Enter Result Area and Max.Marks ");
						jsonArr="[";
					}
				}
				else{
					if (resultArval!= '' && maxmarksval!= '' && parameterval==''){
						alert("Enter Parameter");
						jsonArr="[";
					}
					else if (resultArval== '' && maxmarksval!= '' && parameterval!=''){
						alert("Enter Result Area");
						jsonArr="[";
					}
					else if (resultArval!= '' && maxmarksval== '' && parameterval!=''){
						alert("Enter Max.Marks");
						jsonArr="[";
					}else{
						alert("Enter Result Area, Parameter and Max.Marks");
						jsonArr="[";
					}
				}
			}
		}
		if (jsonArr=="[")
			jsonArr=="";
		else
			jsonArr = jsonArr.slice(0,-1)+ "]";
		return jsonArr;
	}
</script>

<form id="frmMachineRankParam" name="frmMachineRankParam">
<div id="wrapperRpt">
	<div class="main-cntborder"  style="width:900px;height:420px;margin-left:90px;">
		<div style="margin-top:1%"> 
			<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="margin-left:50%;height:23px"/>
<!--			<input type="button" class="easyui-button" value ="Import Excel" id="btnImportExcel" style="height:23px"/>-->
		</div>
		<div>
			<table id="MRParam"><tr><td/></tr></table>	
			<div id="pager"></div>			
		</div>
		<input type="hidden" id="mode" name="mode">
		<input type="hidden" id="hdnmode" name="hdnmode">
	</div>
	</div>
</form>