<script type="text/javascript">
	jQuery(document).ready(function() {
		initialiseForm('frmKKProject');
		jQuery('#submitForm').val('frmKKProject');
		var url = jQuery("#hiddenUrl").val();
		ViewGrid("q=2");
		fillComboBox("frmKKProject","cmbKppmApprovedby","employee.commonFilter");
		var factId = jQuery("#frmKKProject input[id='factory']").val();
		var sectionId = jQuery("#frmKKProject input[id='section']").val();
		var cellId = jQuery("#frmKKProject input[id='cell']").val();
		var machId = jQuery("#frmKKProject input[id='machine']").val();
		var flid = jQuery("#frmKKProject input[id='flid']").val();
		//ViewGrid('q=2&flid='+flid);
		var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
		loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject",dataStr);
		formatDateBox('dteKKPDate','dd-MMM-yyyy');
		readOnlyFields("cmbKppmApprovedby");
		var kk = jQuery("#hdnKkkname").val();
		jQuery("hdnKKK").val(kk.slice(1));
		//jQuery("#cmbKppmApprovedby").combobox("disabled");
		jQuery('#chkCheck').click(function(){
		if(jQuery('#chkCheck').is(':checked') == true){
			enableFields('cmbKppmApprovedby');
		}else{
			readOnlyFields("cmbKppmApprovedby");
			}
		});
	});
	function ViewGrid(filter){
		processGridnew("kkprojectpriority_input.kkpp",filter,"KKProject", "pager", "", "","AlphaNumericOnly");
	}
	function frmKKProject_FuntLocHierarchy_SuccessCallBack(result){
		jQuery("#frmKKProject div[id=dispFunctionalLoc]").css('width','470px');		
		jQuery("#txtKppmFlid").val(result.flId);
		//alert(Object.keys(result));
		var flid = result.flId;//jQuery("#frmKKProject input[id='flid']").val();
		//alert('flid:'+flid);
		ViewGrid('q=2&flid='+flid);		
	}
	function frmKKProject_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		jQuery("#KKProject").clearGridData();
		loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject"," ");
	}
	function frmKKProject_successsCallback(result)
	{
		if(result.successData.define !="define"){
		jQuery("#KKProject").clearGridData();
		loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject"," ");
		}else{
			var flid = jQuery("#frmKKProject input[id='flid']").val();
			var approvedby = jQuery('#cmbKppmApprovedby').combobox('getValue');
			var forwardData = {"flid":flid,"approvedby":approvedby};
			var persistentData = {"flid":flid,"approvedby":approvedby};
			navigateToNextForm("projectsprotoview_input.prpo?type=define&keyid="+jQuery("#hdnMasterKeyid").val(),"Project Modification",null,forwardData);
		}
	}
	function txtFormatter(id, options, rowObject)
	{	
		var columnKey="";
		var color='';
		var id = options.rowId;
		var columnid = options.pos;
		var columnName = options.colModel.name;	
		var kk = jQuery("#hdnKkkname").val();
		var kklength = kk.slice(1).split(",");
		 
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var idval;
		var array=[];
		var i=0;
		if(columnName=="ProjectScore"){
			idval='txtProject_';
			columnNo='score';
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+' </span><span id="textJ_'+columnid+ '_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'" disabled="disabled" style="width: 50px;text-align:right;"  value="'+rowObject[columnid - 1]+'"  ></span></span>';
          }
		else if(columnName=="Rank"){
			idval='txtRank_';
			columnNo='score';
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'"  style="width: 50px;text-align:right;" "onfocus=gotFocuse(this.id);" value="'+rowObject[columnid - 1]+'" maxlength="3"></span></span>';
          }
		else if(columnName=="ProjectName"){
			idval='txtProjectname_';
			columnNo='score';
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'" disabled="disabled" style="width: 150px;text-align:left;"  value="'+rowObject[columnid - 1]+'" ></span></span>';
          }
		else if(columnName=="SELECT"){
			 
			return '<input id="chkdmtcheckbx_'+id+'" name="chkdmtcheckbx_'+id+'" type="checkbox" ' + 'onclick="if(this.checked){chkboxChk(\''+id + '\',\''+kklength.length+ '\');}else{chkboxUnChk(\''+id +'\',\''+kklength.length+ '\')}" />';
          }
		else if(columnName=="Stage"){
			return '<input id="btnDefinestage_'+id+'" name="btnDefinestage_'+id+'" type="button" value="DefineStage"' + 'onclick="defineStage(\''+id + '\',\''+rowObject[1]+ '\')" class="easyui-button" />';
	      }
		else {
			idval = 'txtKkp_';
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;"><input id='
					+ idval
					+ columnid
					+ '_'
					+ id
					+ ' onfocus=gotFocuse(this.id); onChange=outFocus("'
					+ id
					+ '","'
					+ columnid
					+ '","'
					+ columnName
					+ '"); type="text" keyId="'
					+ columnName
					+ '" value="'
					+ rowObject[columnid - 1]
					+ '" maxlength="2" style="width: 75px;text-align:right;background-color:'
					+ color + '" / ></span></span>';

		}
		
	}
	function defineStage(rowid,keyid){
		var projectkeyid= jQuery("#KKProject").jqGrid('getCell',rowid,"KEYID");
		var masterKeyId= jQuery("#KKProject").jqGrid('getCell',rowid,"MASTERKEYID");
		var type="define";
		jQuery("#hdnMasterKeyid").val(projectkeyid);
		var griddata = "&MasterProject="+ convertGridToJSONArrMaster("KKProject")+"&DetailProject="+convertGridToJSONArrDetail("KKProject");
		if(masterKeyId.trim().length<=0){
			saveForm("frmKKProject","kkprojectpriority_save.kkpp?"+griddata+"&define=define");
		}else{
			var factId = jQuery("#frmKKProject input[id='factory']").val();
			var sectionId = jQuery("#frmKKProject input[id='section']").val();
			var cellId = jQuery("#frmKKProject input[id='cell']").val();
			var machId = jQuery("#frmKKProject input[id='machine']").val();
			var flid = jQuery("#frmKKProject input[id='flid']").val();
			var approvedby = jQuery('#cmbKppmApprovedby').combobox('getValue');
			var forwardData = {"flid":flid,"approvedby":approvedby};
			var persistentData = {"flid":flid,"approvedby":approvedby};
			navigateToNextForm("projectsprotoview_input.prpo?type=define&keyid="+projectkeyid,"Project Modification",null,persistentData);
		}
			//navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&keyid="+projectkeyid+"&isHidePrevForm=true","Project Modification" );
	}
	function chkboxChk(rowid,colLength){
		showCol(rowid,colLength,"textJ_");
		hideCol(rowid,colLength,"textN_");
		jQuery("#KKProject").jqGrid('setCell',rowid,'selectval','1');
	}
	function chkboxUnChk(rowid,colLength){ //alert("Inside2 ::::: "+rowid);
		showCol(rowid,colLength,"textN_");
		hideCol(rowid,colLength,"textJ_");
		jQuery("#KKProject").jqGrid('setCell',rowid,'selectval','0');
	}

	function showCol(rowid,arr,id){
		 for(var i=3;i<=arr-3;i++){
			 jQuery('#'+id+i+"_"+rowid).show();
	 	 }
	}
	function hideCol(rowid,array,id){
		 for(var i=3;i<=array-3;i++){
			 jQuery('#'+id+i+"_"+rowid).hide();
	 	 }
	}	

	function outFocus(id,colid,colname) {
		var score = "0";
		var Tot = 0;
		var grdTot = 0;
		var cellValue = "0";
		var value;
		var kk = jQuery("#hdnKkkname").val();
		var kklength = kk.slice(1).split(",");
		var colslength = kklength.length-parseInt(5);
		var projectLength = kklength.length-parseInt(4);
		var critval;
		var criteriaval;
		var prdval;
		var productval;
		var flag = true;
		for ( var i = 4; i < colslength; i++) {
				var colId = i+parseInt(1);
				cellValue = parseInt(jQuery("#txtKkp_" + colId + "_" + id).val());
				cellValue = cellValue | 0;
				score = parseInt(kklength[i]);
				score = score | 0;
				if(cellValue>=0 && cellValue<=9){
				Tot = parseInt(cellValue) * parseInt(score);
				grdTot = parseInt(grdTot) + parseInt(Tot);
				}
				else{
					
					cellValue = parseInt(jQuery("#txtKkp_" + colId + "_" + id).val(0));
					flag = false;
					}
				}

		jQuery("#txtProject_"+projectLength+"_"+id).val(parseInt(grdTot));
		if(flag){
			}else{
				alert("Value should between 1 to 9");
				}

	}

	function gotFocuse(id,colId,rowId){
	
		numericTextBox(id);
	}
function frmKKProject_beforeSubmit() {
	var griddata = "&MasterProject="+ convertGridToJSONArrMaster("KKProject")+"&DetailProject="+convertGridToJSONArrDetail("KKProject");
	return griddata;
}
function frmKKProject_beforeDelete() {
	var griddata = "&DeleteProject="+ convertGridToJSONDeleteMaster("KKProject");
	return griddata;
}
function convertGridToJSONDeleteMaster(jqGridId)
{
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");	
	var jsonArrO='[';
	for(var i=0;i<row.length;i++)
	{
		 var rowid=row[i];
		 var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
		 var masterkeyid = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"MASTERKEYID");
		   if(select=="1"){
		    	 if(masterkeyid.trim().length>0 ) {
						jsonArrO+= '{';
						jsonArrO += '"txtKppmKeyid":"'+masterkeyid+'"';
						jsonArrO+= '},';
						
					}
		         }
	  }  
			jsonArrO = jsonArrO.slice(0, -1) + "]";
			jsonArrO = (jsonArrO != ']'?jsonArrO:"");
			 return jsonArrO;
		 
		}	


function convertGridToJSONArrMaster(jqGridId)
{
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var colAnswer="";
	var alertmsg="";
	var flag = true;
	var jsonArrO='[';
	var keyid;
	for(var i=0;i<row.length;i++)
		{
	 var rowid=row[i];
     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
     var projectkeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"KEYID");
     var masterkeyid = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"MASTERKEYID");
     var kk = jQuery("#hdnKkkname").val();
	 var kklength = kk.slice(1).split(",");
	 var projectLength = kklength.length-parseInt(4);
	 var ranklength = kklength.length-parseInt(3);
     var score = jQuery("#txtProject_"+projectLength+"_"+rowid).val();
     var rank = jQuery("#txtRank_"+ranklength+"_"+rowid).val();
     var flid = jQuery("#frmKKProject input[id='flid']").val();
     var approvedby = jQuery("#cmbKppmApprovedby").combobox('getValue');
     if(masterkeyid.trim().length !="0"){	
    	 keyid = masterkeyid;
     }
	else{
    	   keyid="";        
     }
     if(select=="1"){
    	 if((projectkeyid!=null && projectkeyid!="")&&(select!=null&&select!="") ) {
	    	 
				jsonArrO+= '{';
				jsonArrO += '"txtKppmKeyid":"'+keyid+'",';
				jsonArrO += '"txtKppmKzpmKeyid":"'+projectkeyid+'",';
				jsonArrO += '"txtKppmFlid":"'+flid+'",';
				jsonArrO += '"txtKppmApprovedby":"'+approvedby+'",';
				jsonArrO += '"txtKppmRank":"'+rank+'",';
				jsonArrO += '"txtKppmProjectscore":"'+ score+'"';
				jsonArrO+= '},';
				
			}
         }
         }  
     

	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	 return jsonArrO;
 
}	
function convertGridToJSONArrDetail(jqGridId)
{
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var colAnswer="";
	var alertmsg="";
	var flag = true;
	var keyid;
	var jsonArrO='[';
	for(var i=0;i<row.length;i++)
		{
	 var rowid=row[i];
     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
     var masterkeyid = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"MASTERKEYID");
     if(masterkeyid.trim().length !="0"){	
    	 keyid = masterkeyid;
     }
	else{
    	   keyid="";        
     }
     var column = col.length-parseInt(4);
     if(select=="1"){
         for(var j=4;j<column;j++){
             var flag = parseInt(j)-parseInt(3);
        	 var colIndexName = col[j].name;
             var fieldvalue = jQuery("#txtKkp_"+j+'_'+rowid).val();
    	 if((fieldvalue!=null && fieldvalue!="")&&(select!=null&&select!="") ) {
	    	 
				jsonArrO+= '{';
				jsonArrO += '"txtFlag":"'+flag+'",';
				jsonArrO += '"txtKppdKppmKeyid":"'+keyid+'",';
				jsonArrO += '"txtKppdKkpmKeyid":"'+colIndexName+'",';
				jsonArrO += '"txtKppdScore":"'+fieldvalue+'"';
				jsonArrO+= '},';
				
			}
         }
         }
         }  
     

	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	 return jsonArrO;
 
}	
	</script>


	
			
<form name="frmKKProject" id="frmKKProject" action=" " method="post">
<div id='WrapperRpt' >
<table>
<tr>
<td>
 <div id="frmKKProjectFormatFuntKeyIds" >						
									<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
									<input type="hidden" id="section" name="cmbsection"  value=""></input>
									<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
									<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
									 <input type="hidden" id= "flid"  name= "txtKppmFlid" value="${requestScope.fild}"/>
									<div id="kkprojectfunction" style=" ">
									</div>
									</div>
</td>
<td style="padding-left: 10px;padding-top:15px;" >
<div>
	<input type="checkbox" id="chkCheck" name="chkCheck" />
</div>
</td>
<td style="padding-left: 10px;">
<div>
<label>Approved by</label>
</div>
<div><input type="combobox" id="cmbKppmApprovedby" name="cmbKppmApprovedby" class="easyui-combobox"  style="width:255px;height:21px;text-align:right;" value="${requestScope.approved }"/></div>
</td>

</tr>
</table>
<div id="jqGridKKproject">
<table id=KKProject><tr><td></td></tr></table>
<div id='pager'></div>
</div>
</div>
 <input type="hidden" id="mode"/>
 <input type="hidden" id="hdnKkkname" value="${requestScope.kkk}"/>
 <input type="hidden" id="hdnKKK" value=""/>
 <input type="hidden" id="hdnMasterKeyid" value=""/>
 <input type="hidden" id="hdnFilt" value="${requestScope.fild}"/>
</form>
