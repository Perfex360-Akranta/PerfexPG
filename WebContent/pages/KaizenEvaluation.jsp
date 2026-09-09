<script>

var allValueSet = true;

jQuery(document).ready(function(){
	
		initialiseForm('frmKaizenEvalu');
		jQuery('#submitForm').val('frmKaizenEvalu'); 
		
		var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function()
				{
			processAjaxCalls("openFile.file?fileName=565 Kaizen Evaluation Sheet.xls", "", "", "", "", "new");					
		});

		jQuery("#btnLegend").click(function()
		{
			jQuery("#criteria").slideToggle(200);					
		});
		//formatDateBox('dteKznEvlfromMonth','MMM-yyyy');
		
		jQuery('#dteKznEvlfromMonth').datebox({  
			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
		 });

		formatDateBox('dtefromDate', 'dd-MMM-yyyy');        
        fillWithCurrentDate("dtefromDate");
        //alert(" Checking for date Value :: "+getFieldValue("dtefromDate").substring(0,1));
        
        var date=getFieldValue("dtefromDate").substring(0,1);
        var index=getFieldValue("dtefromDate").substring(0,2).indexOf("-");
    
        if (date<=7 && index=="1"){
			fillWithPreviousMonth("dteKznEvlfromMonth");
			fillWithPreviousMonth("hdnKznEvlfromMonth");
        }
		else{
			fillWithKznCurrentMonth("dteKznEvlfromMonth");
			fillWithKznCurrentMonth("hdnKznEvlfromMonth");
		}	
		
		
		var factId = jQuery("#frmKaizenEvalu input[id='factory']").val();
   	    var sectionId = jQuery("#frmKaizenEvalu input[id='section']").val();
   	    var cellId = jQuery("#frmKaizenEvalu input[id='cell']").val();
   	    var machId = jQuery("#frmKaizenEvalu input[id='machine']").val();
   	 	var flid = jQuery("#frmKaizenEvalu input[id='flid']").val();
   	    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
   	    loadFunctionalLocation("frmKaizenEvalfunloc","functionalLoc.kazev","frmKaizenEvalfunlocValues","frmKaizenEvalu",dataStr);
   	 	//fileManagerPopUp("","KEV","frmKaizenEvalu","btnfilemgr","KevaFilemgr");
   	   	 	
});

function fillWithKznCurrentMonth(fieldId)
{
	
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var year = currentTime.getFullYear();	
	month = getMonthStringFromInt(month);
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);	
	else
		jQuery("#"+fieldId).val(month+'-'+year);
}

function fillWithPreviousMonth(fieldId)
{
	var serverTime = srvTime();
	var dateTime = new Date(serverTime);
	var currentTime = dateTime; // new Date();

	var month = currentTime.getMonth();	
	var year = currentTime.getFullYear();
	if(month==0){
		month=month + 11;
		year = year - 1;
	}	
	else
		month = parseInt(month,10)-1;
	
	var day = currentTime.getDate();
	
		month = getMonthStringFromInt(month);
	
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}

	
	if(fieldId.substring(0,3) == "dte"){
		//jQuery("#"+fieldId).datebox('setValue',dd+'-'+month+'-'+year);
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);
	}		
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(month+'-'+year);
	
}

function viewgrid(filter) {
	processGridnew("KaizenEvaluation_input.kazev",filter,"kaizenEvalgrid","kaizenpager","","","","loadcomplete");
}
function loadcomplete(){
	formatDateBox('dteKevaDate_','dd-MMM-yyyy');
}
function frmKaizenEvalu_FuntLocHierarchy_SuccessCallBack(result)
{
	setFunctionalLocWidth('frmKaizenEvalu','600px');
	clearValidationErrorMsg('cmbKevaFlid');

	jQuery('#dteKznEvlfromMonth').datebox('disable');
/*
	var flid = result.flid;
	var KZNKeyid = jQuery("#hdnKZNKeyid").val();
	var kznMonth = jQuery("#dteKznEvlfromMonth").datebox('getValue');
    //alert(" kznMonth :: "+kznMonth);
	var filter="";
	if(flid!=null && flid!="" && flid!=" ")
		filter="?q=2&flid=" + flid + "&kaizenId="+ "&kznMonth="+kznMonth;
	else if(KZNKeyid!=null && KZNKeyid!="" && KZNKeyid!=" ")
	{
		flid= jQuery("#frmKaizenEvalu input[name='cmbKevaFlid']").val();
		filter="?q=2&flid=" + flid + "&kaizenId="+KZNKeyid;
	}
	//alert(" filter :: Outside :: "+filter);
	viewgrid(filter);
	*/
	//setFieldValue("dteKznEvlfromMonth", "nov-2014");
	fnViewGrid();
	
}

jQuery('#dteKznEvlfromMonth').datebox({onSelect:function(recordid)
	{
		fnViewGrid();
	} 
 });  

function fnViewGrid() {
        var flid = jQuery("#frmKaizenEvalu input[id='flid']").val();
		var kznMonth = jQuery("#hdnKznEvlfromMonth").val();
		
		var filter="";
		if(flid!=null && flid!="" && flid!=" ")
			filter="?q=2&flid=" + flid + "&kaizenId="+ "&kznMonth="+kznMonth;
		viewgrid(filter);
		
}
function frmKaizenEvalu_successsCallback(result){
	jQuery("#frmKaizenEvalu input[id='flid']").val(result.successData.flid);
	jQuery("#kaizenEvalgrid").trigger("reloadGrid");
}
function btnfilemgr_click()
{
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"KZE","","","");
	}
}
function chkLoadFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	var colId = options.pos;
    jQuery('#rowId').val(rowId);
	jQuery('#colId').val(colId);
	
	return '<input type="checkbox" id="kzEvCheckbox_'+rowId+'_'+colId+'" name="kzEvCheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck('+rowId +','+colId+');}else{chkboxUnCheck('+rowId +','+colId+');}" />';
}
function chkboxCheck(row,col){
	jQuery("#kaizenEvalgrid").jqGrid('setCell', row, 'CHECKVAL',"1");
	//alert(row+" :: Row :: "+row);
	
	//alert(" rowId :: second "+row+" colId :: second "+col);
	
	var colModel = jQuery("#kaizenEvalgrid").jqGrid("getGridParam","colModel" );
	for(var j=8;j<colModel.length;j++){
		if(j==8){
			//var dteid="kaizenEvalgriddteEvaluatedDate_7_"+row;
			//alert(" dteid : : "+dteid);
			//kaizenEvalgriddteEvaluatedDate_7_2			
        	setFormater("kaizenEvalgrid","frmKaizenEvalu","",row,colModel[j].name,getGridCell("kaizenEvalgrid",row,colModel[j].name),100,"");
        	var kzndate=jQuery("#kaizenEvalgriddteEvaluatedDate_7_"+row).datebox('getValue');
        	jQuery("#kaizenEvalgriddteEvaluatedDate_7_"+row).datebox('setValue',jQuery("#dtefromDate").datebox('getValue'));
        	setTimeout(function(){
        		jQuery("#kaizenEvalgriddteEvaluatedDate_7_"+row).datebox('disable');
            	//disableField("kaizenEvalgriddteEvaluatedDate_7_"+row);
        	},500);
        	
		}	
		else if(j==9){
			var flid = jQuery("#frmKaizenEvalu input[id='flid']").val();
			processAjaxCalls("KaizenEvaluation_modify.kazev","flid="+flid,"updateRoleSuccess","");
			setFormater("kaizenEvalgrid","frmKaizenEvalu","employee.commonFilter?",row,colModel[j].name,colModel[j+1].name,180,false);
			disableField("kaizenEvalgridcmbEvaluatorName_8_"+row, "frmKaizenEvalu");
            setTimeout(function(){
                setFieldValue("kaizenEvalgridcmbEvaluatorName_8_"+row,jQuery('#hdnempdata').val());
                jQuery("#kaizenEvalgridcmbEvaluatorName_8_"+row).combobox("disable");
            },500);

			
		}
		else if(j>11 && j<colModel.length-2){
			setFormater("kaizenEvalgrid","frmKaizenEvalu","",row,colModel[j].name,getGridCell("kaizenEvalgrid",row,colModel[j].name),35,"","",true);
			j++;
	
			//var controlId="kaizenEvalgridtxt25_"+j+"_"+row;
			//alert(" :: controlId :: "+controlId);
//			 onkeyup=allow139(controlId);  
			
			 //alert(" colModel[j].name "+colModel[j].name);

			
		}else if(j==colModel.length-1){
			setFormater("kaizenEvalgrid","frmKaizenEvalu","",row,colModel[j].name,"...",35,"");
		}
	}
}

function updateRoleSuccess(result){
	jQuery('#hdnempdata').val(result[0][0]);
}


function kaizenEvalgrid_onChange(record){  
	allow139(controlId);
}

function allow139(id){

	var row=jQuery('#rowId').val();
	var col=jQuery('#colId').val();

	//alert(" row :: "+row+" col :: "+col);
	var val=jQuery("#"+id).val();
	if(val=="1" || val=="3" || val=="9"){
		jQuery("#"+id).val(val);
	}
	else{
		//alert('val'+val);
		jQuery("#"+id).val('');
		//jQuery("#kaizenEvalgrid_100%").val('');

		//jQuery("#"+kaizenEvalgrid).jqGrid('setCell',row,"100%"," ");
		
		//alert(" After :: ");
	}	
}

/*
function TxtJHLevel(id, options, rowObject)
	{	
		var columnKey="";
		var color='';
		var id = options.rowId;
		var col = options.pos;
		var colName = options.colModel.name;	
		var cName = colName.split("_");
		var columnName = cName[0];
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var idval;
		var rowValue=rowObject[col-1];
		if(rowValue==" ")
			rowValue="";
		if(columnName=="100%"){
			idval='txtTotal_';
			columnNo='score';
			return '<input type="text" id="'+idval+col + '_'+id +'" disabled="disabled" style="width: 70px;text-align:right;" value="'+rowValue+'"  onfocus="gotFocuse('+id+','+col+','+columnName+')" onChange="outFocus('+id+','+col+','+columnName+')">';		
		}
		else
		 if(columnName=="25") {
			idval='txtKkp_';
			return '<input id='+idval+col + '_'+id +' onfocus=gotFocuse(this.id); onblur=lostFocuss(this.id);   onChange=outFocus("'+id+'","'+col+'","'+columnName+'"); type="text" keyId="'+cName[1]+'" mode="D" value="'+rowValue+'"  maxlength="1" style="width: 50px;text-align:right;background-color:'+color+'" / >';	
		}
		else if(columnName=="15" ){
			idval='txtKkp_';
			return '<input id='+idval+col + '_'+id +' onfocus=gotFocuse(this.id); onblur=lostFocuss(this.id); onChange=outFocus("'+id+'","'+col+'","'+columnName+'"); type="text" keyId="'+cName[1]+'" mode="D" value="'+rowValue+'"  maxlength="1" style="width: 50px;text-align:right;background-color:'+color+'" / >';		
			
			}
		else if(columnName=="10"  ){
			idval='txtKkp_';
			return '<input id='+idval+col + '_'+id +' onfocus=gotFocuse(this.id); onblur=lostFocuss(this.id); onChange=outFocus("'+id+'","'+col+'","'+columnName+'"); type="text" keyId="'+cName[1]+'" mode="D" value="'+rowValue+'"  maxlength="1" style="width: 50px;text-align:right;background-color:'+color+'" / >';		
			
			}
		else  {
			idval='txtKeyid_';
			return '<input id='+idval+col + '_'+id +' onfocus=gotFocuse(this.id); onblur=lostFocuss(this.id);   onChange=outFocus("'+id+'","'+col+'","'+columnName+'"); type="text" keyId="'+cName[1]+'" mode="D" value="'+rowValue+'"  maxlength="1" style="width: 50px;text-align:right;background-color:'+color+'" / >';	
		}
	}

	function dteEvaDateFormatter(id, options, rowObject)
	{
		var rowId = options.rowId;
		var colId = options.pos;
		//return '<input type="checkbox" id="kzEvCheckbox_'+rowId+'_'+colId+'" name="kzEvCheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
		return '<input type="text" id="dteKevaDate_'+rowId+'_'+colId+'" class="easyui-datebox" name="dteKevaDate_'+rowId+'_'+colId+'" style="widht:40px;height:21px;" value=""/>';
	}
	function cmbformatter(id, options, rowObject)
	{
		var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="combobox"   id="cmbKevaEmployeeid_'+rowId+'" name="cmbKevaEmployeeid_'+rowId+'"   style=" width :60px;"  value="'+rowObject[0]+'" />';
	}*/
	function chkboxUnCheck(row,col){
		jQuery("#kaizenEvalgrid").jqGrid('setCell', row, 'CHECKVAL',"0");
		var colModel = jQuery("#kaizenEvalgrid").jqGrid("getGridParam","colModel" );
		for(var j=8;j<colModel.length-2;j++){
			if(j==8)
				removeFormater("kaizenEvalgrid","",row,"",colModel[j].name);
			else if(j==9)
				removeFormater("kaizenEvalgrid","",row,"",colModel[j].name);
			else if(j>11){
				removeFormater("kaizenEvalgrid","",row,"",colModel[j].name);
				j++;
			}else if(j==colModel.length-1){
				removeFormater("kaizenEvalgrid","",row,"",colModel[j].name);
			}
		}
	}
	function btnformatter(id, options, rowObject)
	{	
		var columnKey="";
		var color='';
		var id = options.rowId;
		var col = options.pos;
		var colName = options.colModel.name;	
		var cName = colName.split("_");
		var columnName = cName[0];
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var idval;
		var rowValue=rowObject[col-1];
		if(rowValue==" ")
			rowValue="";
		 if(columnName=="VIEWKAIZEN"  ){
			var rowId = options.rowId;
			var colId = options.pos;
			return '<input type="button" id="btnKaizen_'+rowId+'_'+colId+'" name="btnKaizenGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:35px;  height:15px;text-align: center;"   class="easyui-button" value="..."/>';		
		}
	}
	function kaizen(id, colId){

		var KZNKeyid=jQuery("#kaizenEvalgrid").jqGrid('getCell', id,"KZNKEYID");
		var KEType="KaizenEvaluation";
		//navigateToNextForm("kaizen_input.kaizen?q=2&frmName=kznEvtn&Mode=view&kznKeyid="+KZNKeyid);
		LoadPopUp("divIdKaizen","kaizen_input.kaizen?q=2&mode=view&frmName=kznEvtn&kznKeyid="+KZNKeyid+"&KEType="+KEType,true,"95%","90%","1%","1%","","View Kaizen");
		//mode	view   &frmName=kznEvtn
	}
	function  frmKaizenEvalu_beforeSubmit(){
		var flid= jQuery("#frmKaizenEvalu input[name='cmbKevaFlid']").val();
		//alert(flid);
		//var chkbox=kaizenEvalgrid_CHECKBOX; 
		var row  = jQuery("#kaizenEvalgrid").jqGrid('getDataIDs');
		var checkVal ;

		if(flid!=null && flid!="" && flid!=" "){
			clearValidationErrorMsg('cmbKevaFlid');
			var jsonnMst =convertTojsonMaster("N");
			//alert("The jsonnMst::"+jsonnMst);
            for(var i=0;i<row.length;i++){
	 		   checkVal =jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"CHECKVAL");
	 		   var jsonnDtl =convertTojsonDetailMst(i);
     	 		if(checkVal==1){
 					//alert("Select Data");   jsonnMst.length<450 ||
					if(jsonnDtl==false){ 
	     				alert(" Please Enter all Values in Grid ");
		     			return false;
					 }
			 	  }
				}
			//else
				 if(jsonnMst=="Date"){
				alert("Select Evaluated Date");
				return false;
			}else if(jsonnMst=="Name"){
				alert("Select Evaluator Name");
				return false;
			}else{
				//alert(" jsonnMst :: Else :: trim(). "+jsonnMst.trim().length);
				/*if(jsonnMst.length<450 && checkVal==1){
					alert("Select Data");
					return false;
				}else{*/
					var gridData="&kaizenMasterData="+jsonnMst;//+"&kaizenDetailData="+convertTojsonDetail();
					//alert("Master Data"+gridData);
					return gridData;
				//}
			}
	 	  
		}else{
			setTimeout(function(){
				showValidationErrorMsg('cmbKevaFlid','Select Functional Location');
			},50);
			
			return false;
		}
	}
	function convertTojsonMaster(dlt){
		var row=jQuery("#kaizenEvalgrid").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
		var count=0;
		var dltcount=0;
		var chkCount=0;
		allValueSet==true;
		
		for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
	 		var kznkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"KZNKEYID");
	 		//alert(kznkeyid);
	 		var empkeyid = getFieldValue("kaizenEvalgridcmbEvaluatorName_8_"+rowid);
	 		//alert(empkeyid);
	 		//jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"EVAKEYID");
	 		var mstkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"MSTKEYID");
	 		//alert(mstkeyid);
	 		var checkVal =jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"CHECKVAL");
		 	if(checkVal=="1"){
		 		var paramid="kaizenEvalgriddteEvaluatedDate_7_"+rowid;
		 		var parameterval=jQuery("#"+paramid).datebox("getValue");
		 		var currentDate = getServerDateTime();

		 		/*alert(" parameterval :: "+parameterval+" currentDate :: "+currentDate);

		 		if(convertStringToDate(parameterval)> currentDate)
				{   alert(" Date"+convertStringToDate(parameterval));
					alert('Kaizen Evaluation Date Should Not Exceed Current Date');
					fillWithCurrentDate("kaizenEvalgriddteEvaluatedDate_7_"+rowid);
					return false;
				}*/
		 		
			 	if(dlt!="Y"){
				 	if(parameterval==null || parameterval=="" || parameterval==" "){
					 		//alert("Select Evaluated Date");
					 	count++;
					 	return "Date";
				 	}
			 	}
			 	if(dlt=="Y"){
			 		if((parameterval==null || parameterval=="" || parameterval==" ") &&(empkeyid==null || empkeyid=="" || empkeyid==" "))
				 	{
			 			dltcount++;
				 	}	
			 	}
			 	if(dlt!="Y"){
				 	if(empkeyid==null || empkeyid=="" || empkeyid==" "){
				 		//alert("Select Evaluator Name");
					 	count++;
					 	return "Name";
				 	}
			 	}
			 	chkCount++;
			 	jsonArr+= '{';
				jsonArr += '"txtKevaKaizenid":"'+kznkeyid+'",';
				jsonArr += '"txtKevaKeyid":"'+mstkeyid.trim()+'",';
				jsonArr += '"txtKevaFlid":"'+jQuery("#frmKaizenEvalu input[id='flid']").val()+'",';
				jsonArr += '"txtKevaEmployeeid":"'+empkeyid+'",';
				jsonArr += '"txtKevaDate":"'+parameterval+'",';
				jsonArr += '"txtKevaDetail":'+convertTojsonDetailMst(i)+'},';
				//alert(" jsonArr :: "+jsonArr);
		 	}
		}
		if(dltcount==chkCount)
		{
		 	return "No";
		}

		//if(allValueSet==false){
		 	//alert(" Please Enter Values ");
		 //	return false;
	 	//}
	 	
		if (jsonArr=="["){
			jsonArr=="";
			return false;
		}
		else{
			jsonArr = jsonArr.slice(0,-1)+ "]";
			return jsonArr;
		}
	}
	function convertTojsonDetailMst(rowNum){
		var row=jQuery("#kaizenEvalgrid").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
	 	rowid=row[rowNum];
	 	var colModel = jQuery("#kaizenEvalgrid").jqGrid("getGridParam","colModel" );

	 	//alert(colModel.length);
	 	for(var j=12;j<colModel.length-2;j++){   
		 	var criVal=(colModel[j].name).split("_");
	 		//alert("The criVal"+criVal);
		 	var kznkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', rowid,"KZNKEYID");
	 		//alert("convertTojsonDetailMst"+kznkeyid);
	 		var checkVal =jQuery("#kaizenEvalgrid").jqGrid('getCell', rowid,"CHECKVAL");
	 		//alert("convertTojsonDetailMst"+checkVal);
	 		var mstkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', rowid,"MSTKEYID");
	 		//alert("convertTojsonDetailMst"+mstkeyid);
	 		if(checkVal.trim()=="1"){
		 		var paramid="kaizenEvalgridtxt"+criVal[0]+"_"+(j-1)+"_"+rowid;
			 	var parameterval=jQuery("#frmKaizenEvalu input[id='"+paramid+"']").val();
                alert(" parameterval.trim() :: "+parameterval.trim().length);
                //25,JHCR001
              // if(criVal[0].contains("KEYID")){
            	  if(!criVal[0]){
                   // alert("Inside the !criVal");		 		
                    if(parameterval==null || parameterval=="" || parameterval== " ") {
				 		parameterval="0";
				 		allValueSet=false;
				 		return false;
				 	}
				 	
				 	jsonArr+= '{';
					jsonArr += '"txtKedlKzncretriaid":"'+criVal[1]+'",';
					jsonArr += '"txtKedlKznmKeyid":"'+kznkeyid+'",';
					jsonArr += '"txtKedlKeyid":"'+getGridCell("kaizenEvalgrid",rowid,colModel[j+1].name)+'",';
					jsonArr += '"txtKedlKevaKeyid":"'+mstkeyid.trim()+'",';
					jsonArr += '"txtKedlKzncriteriaval":"'+parameterval.trim()+'"},';
					//alert("The jsonArr"+jsonArr);
			 	}
		 	}
	 	}
	 	
		if (jsonArr=="[")
			jsonArr=="";
		else
			jsonArr = jsonArr.slice(0,-1)+ "]";
		return jsonArr;
	}
	
	function convertTojsonDetail(){
		var row=jQuery("#kaizenEvalgrid").jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
		for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var colModel = jQuery("#kaizenEvalgrid").jqGrid("getGridParam","colModel" );
		 	for(var j=12;j<colModel.length-2;j++){   
			 	var criVal=(colModel[j].name).split("_");
		 		var kznkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', rowid,"KZNKEYID");
		 		var checkVal =jQuery("#kaizenEvalgrid").jqGrid('getCell', rowid,"CHECKVAL");
		 		var mstkeyid =jQuery("#kaizenEvalgrid").jqGrid('getCell', row[i],"MSTKEYID");
			 	if(checkVal.trim()=="1"){
			 		var paramid="kaizenEvalgridtxt"+criVal[0]+"_"+(j-1)+"_"+rowid;
				 	var parameterval=jQuery("#frmKaizenEvalu input[id='"+paramid+"']").val();
				 	var dtlKeyval="kaizenEvalgridtxt"+colModel[(j+1)].name+"_"+j+"_"+rowid;
				 	var dtlKeyId=jQuery("#frmKaizenEvalu input[id='"+dtlKeyval+"']").val();
				 	if(!criVal[0].contains("KEYID")){
					 	jsonArr+= '{';
						jsonArr += '"txtKedlKzncretriaid":"'+criVal[1]+'",';
						jsonArr += '"txtKedlKznmKeyid":"'+kznkeyid+'",';
						jsonArr += '"txtKedlKeyid":"'+dtlKeyId+'",';
						jsonArr += '"txtKedlKevaKeyid":"'+mstkeyid.trim()+'",';
						jsonArr += '"txtKedlKzncriteriaval":"'+parameterval.trim()+'"},';
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
	function lostFocuss(id){
		var lastval=jQuery("#hdnmodeval").val();
		var colValue=jQuery("#"+id).val();
		if(lastval==colValue)
			jQuery('#'+paramid).attr('mode', 'D');
		else 
			jQuery('#'+paramid).attr('mode', 'I');
	}
	function kaizenEvalgrid_onFocus(result) {
		gotFocuse(result);
	}
	function kaizenEvalgrid_onBlur(result) {
				
		var rowId=result.rowId; 
		var txtId=result.txtId; 
		var controlId="kaizenEvalgrid"+txtId+"_"+rowId;
		totalValchange(result);
		allow139(controlId);
	}
	function totalValchange(result){//alert(" Object.keys :: "+Object.keys(result));
		var res=0;
		var rowId=result.rowId;
		var colModel = jQuery("#kaizenEvalgrid").jqGrid("getGridParam","colModel" );
	 	for(var j=12;j<colModel.length-2;j++){   
	 		var mul=0;
	 		var criVal=(colModel[j].name).split("_");
	 		//alert(" criVal : : "+criVal);
	 		var paramid="kaizenEvalgridtxt"+criVal[0]+"_"+(j-1)+"_"+rowId;
		 	var parameterval=jQuery("#frmKaizenEvalu input[id='"+paramid+"']").val();
		 	//alert(" parameterval : : "+parameterval);
		 	if(parameterval=="" || parameterval==" " || parameterval==null)
		 		parameterval=0;

	 		//alert(" parameterval Checking :: Inside "+parameterval);
	 		jQuery('#hdnparameterval').val(parameterval);
		 	if(criVal[0]>=1){			
				 mul= parseInt(parameterval)*parseInt(criVal[0]);
			}
		 	res+=parseInt(mul);
		 	
	 	}

        // alert(res);
         
         //alert(" parameterval Checking :: jQuery('#hdnparameterval').val(); "+jQuery('#hdnparameterval').val());
         
	 	jQuery("#kaizenEvalgrid").jqGrid('setCell', rowId, '100%',res/9);
	}
	/*function divIdKaizen_onClose(){
		jQuery("#chkkznmRank").attr({'checked':true});
		if(jQuery("#chkkznmRank").is(':checked') == true){
			var flid = jQuery("#frmKaizenEvalu input[id='flid']").val();
			var filter="";
			if(flid!=null && flid!="" && flid!=" ")
				filter="?q=2&flid=" + flid + "&Rank=Y";
			viewgrid(filter);
		}
		return true;
	}*/
	function frmKaizenEvalu_beforeDelete(){
		var gridData="";
		gridData+="&kaizenMasterData=";
		var jsonMst = convertTojsonMaster("Y");
		if(!jsonMst)
		{
			alert("Select Data");
			return false;
		}else if(jsonMst=="No")
		{
			alert("No Data to Delete");
			return false;
		}
		else
			gridData+=jsonMst;
		var r=confirm("Are You Sure to Delete?");
		if(r)
			return gridData;
		else
			return false;
		
	}
	function chkboxCheckRank(id){
		var flid = jQuery("#frmKaizenEvalu input[id='flid']").val();
		var filter="";
		var KZNKeyid = jQuery("#hdnKZNKeyid").val();
		if(jQuery("#"+id).is(':checked') == true){
			if(flid!=null && flid!="" && flid!=" ")
				filter="?q=2&flid=" + flid + "&Rank=Y&kaizenId="+KZNKeyid;
			viewgrid(filter);
		}else
		{
			if(flid!=null && flid!="" && flid!=" ")
				filter="?q=2&flid=" + flid + "&Rank=N&kaizenId="+KZNKeyid;
			viewgrid(filter);
		}
	}
	function frmKaizenEvalu_deleteSuccessCallback(result){
		alert(result.successData.msg);
		jQuery("#frmKaizenEvalu input[id='flid']").val(result.successData.flid);
		jQuery("#kaizenEvalgrid").trigger("reloadGrid");
	}
	
	function gotFocuse(result){
		var id=result.gridId+result.txtId+"_"+result.rowId;
		jQuery('#'+id).attr('maxlength', '2');
		
		jQuery('#'+id).keydown(function(event) {
		var cVal = jQuery(this).val();
		if  (  event.keyCode === 190 || event.keyCode == 110) 
		{
			if(( cVal != undefined 
            		&& cVal != null && cVal.indexOf(".") > -1 ))
				event.preventDefault();
            		
		}else if(event.keyCode === 46 ||  event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 110 ||
	             // Allow: Ctrl+A
	            (event.keyCode == 65 && event.ctrlKey === true) || 
	             // Allow: home, end, left, right
	            (event.keyCode >= 35 && event.keyCode <= 39))  {
	                 // let it happen, don't do anything
	                 return;
	    }
		else if ( (event.keyCode < 48 || event.keyCode > 57) && ( event.keyCode < 96 || event.keyCode > 105 ) ) {
		        	event.preventDefault();
	    }
		else if ((event.keyCode > 48 || event.keyCode < 58) && (event.keyCode > 96 || event.keyCode < 106 ) ){
				if ((event.keyCode !=49 && event.keyCode != 51 && event.keyCode != 57) && (event.keyCode !=97 && event.keyCode != 99 && event.keyCode != 105))
       				event.preventDefault();
		}
	    });
	}
</script>
<form name="frmKaizenEvalu" id="frmKaizenEvalu">
<div id="WrapperRpt" style="margin-top:4px;" >
<table>
<tr>
<td width="60%">
<div id="frmKaizenFuntKeyIds">							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>
								<input type="hidden" id="flid" name="cmbKevaFlid" value="${requestScope.flid}"></input>							
							</div>						
						<div id="frmKaizenEvalfunloc" style="width:600px;"></div>
						<div id="err_cmbKevaFlid" class="tpm-errormsg"></div>
		</td>
		<td colspan="4" valign="top">
				<div style="margin-left:20px;width:600px;margin-top:10px;">
				<div>
				<label>Month</label>
				</div>
					<div style="">
					    <input id="dteKznEvlfromMonth" name="dteKznEvlfromMonth"  class="easyui-datebox" clear="false" style="width:70px;" value=""/>
						<span style="padding-left:10px;">
						<label style="font-weight:bold ;font-color:blue;margin-left:10px;border:1px solid #a4a4a4;width:100px;height:20px;">Score(1,3,9)</label>
						</span>
						<span style="padding-left:10px;"><input type="checkbox" id="chkkznmRank" name="chkkznmRank"  onclick="chkboxCheckRank(this.id);"/>
				        <label>Rank Based</label></span>
				        <span style="padding-left:10px;display: none;"><input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="View Report" style="height: 25px; width : 75px;margin-left:10px;"/></span>
				        <span style="padding-left:10px;"><input type="button" class="easyui-button" id="btnLegend" name="btnLegend" value="Legend" style="height: 25px; width : 75px;margin-left:10px;"/></span>
				    </div>
					
				</div>
			</td>
			
			
			
		<td valign="bottom">
					<div style='margin-left:5px;'>
							 <span  id="KevaFilemgr" style="" >
		     		
		             </span> 
		             </div>
				</td>
		</tr>
		<tr>
			<td>
			
		    <div  id="criteria" style='margin-left:0px;width:90px;margin-top:0px;display:none;'>
			<table>
					<tr>
						<td style="border: 1px solid black;background-color:#CC3300;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Criteria</label>
							</div>
						</td>
						<td style="border: 1px solid black;background-color:#CC3300;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Score = 1</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#CC3300;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Score = 3</label>
		               		</div>
						</td>
						<td style="border: 1px solid black;background-color:#CC3300;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Score = 9</label>
					 		</div>   
						</td>
					</tr>
					<tr>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
							    <label>Originality</label>
							</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Any Others</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Borrowed Ideas from another DMT</label>
		               		</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:230px;">
							    <label>Unique idea,not implemented anywhere in the plant</label>
							 </div>   
						</td>
					</tr>
					<tr>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Sustainable results</label>
							</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:150px;">
					    		<label>No Control plan in place</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Control plan in place</label>
		               		</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:230px;">
					    		<label>Mistake proofing(permanent Changes)</label>
					 		</div>   
						</td>
					</tr>
					
					<tr>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Impact(monetary benefits)</label>
							</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Less than 1 Lakh per annum</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:200px;">
					    		<label>1-5 Lakh per annum</label>
		               		</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:200px;">
					    		<label>More than 5 Lakh per annum</label>
					 		</div>   
						</td>
					</tr>
					
					<tr>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Horizontal Depolyment Potential across the plant</label>
							</div>	
						</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Low</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Medium</label>
		               		</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFE6E6;padding-left:4px;">
							<div style="width:200px;">
					    		<label>High</label>
							 </div>   
						</td>
					</tr>
					<tr>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
						    	<label>Safety & Environment</label>
							</div>
						</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:150px;">
					    		<label>Any Others</label>
		              		</div>
		              	</td>
						<td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:250px;">
					    		<label>Prevent product/property damage; indirect(positive)impact on Govt regualtions</label>
		               		</div>
						</td>
					    <td style="border: 1px solid black;background-color:#FFCCCC;padding-left:4px;">
							<div style="width:200px;">
					    		<label>Previous injury; Direct(Positive) impact on Govt regulations</label>
					    	</div>   
					    </td>
					    <td>
							<span style="padding-left:10px;display:none;">
			                   <input id="dtefromDate"  class="easyui-datebox" clear="false" style="width:100px;" value=""/>
			                </span>
						</td>
						<td>
							<span style="padding-left:10px;display:none;">
			                   <input id="dtefromMonth"  name="dtefromMonth" class="easyui-datebox" clear="false" style="width:100px;" value=""/>
			                </span>
						</td>
					  </tr>
				  </table>
				</div> 
			  </td>
			</tr>
		</table>
		
		
		<!-- <table>
			<tr>
				<td style="padding-left:0px;">
					<div>
						<label class="mandatory-lbl">Date</label>
					</div>
					<div><input type="text" id="dteKevaDate" class="easyui-text" name="dteKevaDate" style="widht:100px;height:21px;" value="${requestScope.kznTlEvaluationmst.kevaDate}"/></div>
				</td>
				
				<td style="padding-left:10px;">
					<div>
						<label class="mandatory-lbl">Evaluator Name</label>
					</div>
					<div>
						<input type="text"   id="cmbKevaEmployeeid" name="cmbKevaEmployeeid" class="easyui-text" style="width:150px;height:21px;" value="${requestScope.kznTlEvaluationmst.kevaEmployeeid}"/>
					</div>
				</td> 
				
			</tr>
		</table>	-->


<table id='kaizenEvalgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='kaizenpager'></div>
					
</div>
<input type="hidden" id="hdnparameterval" name="hdnparameterval" value="">
<input type="hidden" id="hdnempdata" name="hdnempdata" value="">
<input type="hidden" id="mode" name="mode" /> 
<input type="hidden" id="colId" name="colId" value="" />
<input type="hidden" id="rowId" name="rowId" value="" />
<input type="hidden" id="txtKevaKeyid" name="txtKevaKeyid" value="${requestScope.kznTlEvaluationmst.kevaKeyid}" />
<input type="hidden" id="hdnmodeval" name="hdnmodeval" value="" />
<input type="hidden" id="hdnKZNKeyid" name="hdnKZNKeyid"  value="${requestScope.kaizenId}" />
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
<input type="hidden"  id="hdnKznEvlfromMonth"	value=""  style="display:block"/>
</form>

