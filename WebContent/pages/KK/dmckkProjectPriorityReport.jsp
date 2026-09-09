<script type="text/javascript">
	jQuery(document).ready(function() {
		initialiseForm('frmKKProject');
		jQuery('#submitForm').val('frmKKProject');
		var url = jQuery("#hiddenUrl").val();

//		ViewGrid(url,"");
		fillComboBox("frmKKProject","cmbDmpmApprovedby","employee.commonFilter");
		var factId = jQuery("#frmKKProject input[id='factory']").val();
		var sectionId = jQuery("#frmKKProject input[id='section']").val();
		var cellId = jQuery("#frmKKProject input[id='cell']").val();
		var machId = jQuery("#frmKKProject input[id='machine']").val();
		var flid = jQuery("#frmKKProject input[id='flid']").val();
		//ViewGrid('q=2&flid='+flid);
		var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
		loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject",dataStr);
		formatDateBox('dteKKPDate','dd-MMM-yyyy');
		readOnlyFields("cmbDmpmApprovedby");
		var kk = jQuery("#hdnKkkname").val();
		jQuery("hdnKKK").val(kk.slice(1));
		//jQuery("#cmbDmpmApprovedby").combobox("disabled");
		jQuery('#chkCheck').click(function(){
		if(jQuery('#chkCheck').is(':checked') == true){
			enableFields('cmbDmpmApprovedby');
		}else{
			readOnlyFields("cmbDmpmApprovedby");
			}
		});

	});
	function ViewGrid(url,filter){
		processGridnew(url,filter,"KKProject", "pager", "", "","AlphaNumericOnly","loadOnComplete");
	}
	function loadOnComplete(){
		//alert("loadOnComplete");
		var jqGridId="KKProject";
		var kk = jQuery("#hdnKkkname").val();
		var kklength = kk.slice(1).split(",");
		var colslength = kklength.length-parseInt(5);
		var projectLength = kklength.length-parseInt(4);
		
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		var cnt=0;
		for( var j = 0; j < allRows.length;j++){
			var rowIdaa=parseInt(j)+1;
			var totScore=jQuery("#txtProject_"+projectLength+"_"+rowIdaa).val();
			var status=jQuery("#"+jqGridId).jqGrid('getCell',rowIdaa,'DefineStatus');
			//alert("status"+status);
			if(status=="C"){
				jQuery("#chkdmtcheckbx_"+rowIdaa).attr('disabled','disabled');
			}
			totScore=totScore|0;
			//alert('totScore:'	+totScore);
			disableUIButton("btnDefinestage_"+rowIdaa);
			if(parseInt(totScore)>0){
				cnt=cnt+1;		
				//alert('cnt:'+cnt);
				jQuery("#txtRank_12_"+rowIdaa).val(cnt);
			}
		}
	}
	function frmKKProject_FuntLocHierarchy_SuccessCallBack(result){
		jQuery("#frmKKProject div[id=dispFunctionalLoc]").css('width','470px');		
		jQuery("#txtDmpmFlid").val(result.flId);
		//alert(Object.keys(result));
		var flid = result.flId;//jQuery("#frmKKProject input[id='flid']").val();
		//alert('flid:'+flid);
		var url = jQuery("#hiddenUrl").val();
		ViewGrid(url, '&flid='+flid);		
	}
	function frmKKProject_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		jQuery("#KKProject").clearGridData();
		loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject"," ");
	}
	function frmKKProject_exceptionCallback(result)
	{
		
		if( result.rowId != undefined )
			enableUIButton("btnDefinestage_"+result.rowId);
	}	
	
	function frmKKProject_successsCallback(result)
	{
		
		if( result.rowId != undefined )
			enableUIButton("btnDefinestage_"+result.rowId);
		
		if(result.successData.define !="define"){
			//alert("!define");
			jQuery("#KKProject").trigger("reloadGrid");
			refreshForm();
			//loadFunctionalLocation("kkprojectfunction","functionalLoc.kkpp","kkprojectfunctionLocation","frmKKProject"," ");
		}else{
			//alert("define");
			var flid = jQuery("#frmKKProject input[id='flid']").val();
			var approvedby = jQuery('#cmbDmpmApprovedby').combobox('getValue');
			var forwardData = {"flid":flid,"approvedby":approvedby};
			var persistentData = {"flid":flid,"approvedby":approvedby};
			var checkList = jQuery('#hdnIsCheckList').val();
			var dfiwkeyId ="DMCMKEYID";
			//alert(dfiwkeyId);
			navigateToNextForm("dmcprojectsprotoview_input.prpo?type=define&isDefineStage=Y&keyid="+jQuery("#hdnMasterKeyid").val()+"&dfiwkeyId="+dfiwkeyId+"&checkList="+checkList,"Project Define",null,forwardData);
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

		var width= options.colModel.width; 
		width = width-4;
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var idval;
		var array=[];
		var i=0;
		if(columnName=="ProjectScore"){
			idval='txtProject_';
			columnNo='score';
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+' </span><span id="textJ_'+columnid+ '_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'" disabled="disabled" style="width: '+width+';text-align:right;"  value="'+rowObject[columnid - 1]+'"  ></span></span>';
          }
		else if(columnName=="Rank"){
			idval='txtRank_';
			columnNo='score';
			//return rowObject[columnid - 1];
			//return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'" disabled="disabled"  style="width: 75px;text-align:right;" onfocus="gotFocuse(this.id);" value="'+rowObject[columnid - 1]+'" maxlength="3"></span></span>';
			return '<span><input type="text" id="'+idval+columnid + '_'+id +'" readonly="readonly"  style="width:'+ width +'px;text-align:right;" onfocus="gotFocuse(this.id);" value="'+rowObject[columnid - 1]+'" maxlength="3"></span>';
          }
		else if(columnName=="ProjectName"){
			idval='txtProjectname_';
			columnNo='score';
			return rowObject[columnid - 1];
			//return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;"><input type="text" id="'+idval+columnid + '_'+id +'" disabled="disabled" style="width: 150px;text-align:left;"  value="'+rowObject[columnid - 1]+'" ></span></span>';
          }
		else if(columnName=="SELECT"){
			 
			return '<input id="chkdmtcheckbx_'+id+'" name="chkdmtcheckbx_'+id+'" type="checkbox" ' + 'onclick="if(this.checked){chkboxChk(\''+id + '\',\''+kklength.length+ '\');}else{chkboxUnChk(\''+id +'\',\''+kklength.length+ '\')}" />';
          }
		else if(columnName=="Stage"){
			return '<input id="btnDefinestage_'+id+'" name="btnDefinestage_'+id+'" type="button" style="width:80px;height:20px;" value="DefineStage"' + 'onclick="defineStage(\''+id + '\',\''+rowObject[1]+ '\')" class="easyui-button" />';
	      }
		else if(columnName=="DefineStatus"){
			return rowObject[columnid - 1];
	      }
		
		else {
			idval = 'txtKkp_';
			
			//alert(columnName);
			/*if (columnid=="8" || columnid=="7"){
				width="100";				
			}
			else if (columnid=="5" || columnid=="9"){
				width="150";
			}
			else if (columnid=="6"){
				width="230";
			}
			else if (columnid=="10"){
				width="80";
			}
			else if (columnid=="11"){
				width="80";
			}
			else{
				width="75";
			}*/
			//alert('columnid:'+ columnid+'width:'+width);
			return '<span><span id="textN_'+columnid+'_'+ id+'">'+rowObject[columnid - 1]+'</span><span id="textJ_'+columnid+'_'+ id+'" style="display:none;">'
					+ '<input id='+ idval+ columnid+ '_'+ id
					+' onkeyup=allow139("'+ id+ '","'+ columnid+ '");  '
					+' onfocus=gotFocuse(this.id);  onChange=outFocus("'
					+ id
					+ '","'
					+ columnid
					+ '","'
					+ columnName
					+ '"); type="text" keyId="'
					+ columnName
					+ '" value="'
					+ rowObject[columnid - 1]
					+ '" maxlength="2" style="width:'+width+'px;height:22px;text-align:right;background-color:'
					+ color + '" / ></span></span>';

		}
		
	}
	function defineStage(rowid,keyid){
		//chkdmtcheckbx_1
		if(jQuery("#chkdmtcheckbx_"+rowid).is(":checked") == true){
			var projectkeyid= jQuery("#KKProject").jqGrid('getCell',rowid,"KEYID");
			var dfiwkeyid= jQuery("#KKProject").jqGrid('getCell',rowid,"DFIWKEYID");
			var masterKeyId= jQuery("#KKProject").jqGrid('getCell',rowid,"MASTERKEYID");
			var type="define";
			jQuery("#hdnMasterKeyid").val(projectkeyid);
			jQuery("#hdnDfiwKeyid").val(dfiwkeyid);
			disableUIButton("btnDefinestage_"+rowid);
			var griddata = "&MasterProject="+ convertGridToJSONArrMaster("KKProject")+"&DetailProject="+convertGridToJSONArrDetail("KKProject");
			saveForm("frmKKProject","dmckkprojectpriority_save.kkpp?"+griddata+"&define=define&rowid="+rowid);
			/*if(masterKeyId.trim().length<=0){
				saveForm("frmKKProject","kkprojectpriority_save.kkpp?"+griddata+"&define=define");
			}else{
				var factId = jQuery("#frmKKProject input[id='factory']").val();
				var sectionId = jQuery("#frmKKProject input[id='section']").val();
				var cellId = jQuery("#frmKKProject input[id='cell']").val();
				var machId = jQuery("#frmKKProject input[id='machine']").val();
				var flid = jQuery("#frmKKProject input[id='flid']").val();
				var approvedby = jQuery('#cmbDmpmApprovedby').combobox('getValue');
				var forwardData = {"flid":flid,"approvedby":approvedby};
				var persistentData = {"flid":flid,"approvedby":approvedby};
				navigateToNextForm("projectsprotoview_input.prpo?type=define&keyid="+projectkeyid,"Project Modification",null,persistentData);
			}		*/	
		}
		else{
			alert("Select Row");
		}
			//navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&keyid="+projectkeyid+"&isHidePrevForm=true","Project Modification" );
	}
	function chkboxChk(rowid,colLength){
		showCol(rowid,colLength,"textJ_");
		hideCol(rowid,colLength,"textN_");
		jQuery("#KKProject").jqGrid('setCell',rowid,'selectval','1');
		var status=jQuery("#KKProject").jqGrid('getCell',rowid,'DefineStatus');
		//alert("status"+status);
		if(status!="C"){enableUIButton("btnDefinestage_"+rowid);}
		
	}
	function chkboxUnChk(rowid,colLength){ //alert("Inside2 ::::: "+rowid);
		showCol(rowid,colLength,"textN_");
		hideCol(rowid,colLength,"textJ_");
		jQuery("#KKProject").jqGrid('setCell',rowid,'selectval','0');
		disableUIButton("btnDefinestage_"+rowid);
	}

	function showCol(rowid,arr,id){
		 for(var i=3;i<=arr-3;i++){
			 jQuery('#'+id+i+"_"+rowid).show();
			 jQuery("#"+id+i+"_"+rowid).on(' click keydown', function(e){
				    e.stopPropagation();
				});
	 	 }
	}
	function hideCol(rowid,array,id){
		 for(var i=3;i<=array-3;i++){
			 jQuery('#'+id+i+"_"+rowid).hide();
	 	 }
	}	
	function allow139(id,colid){
		
		var val=jQuery("#txtKkp_"+colid+"_" +id).val();
		val=parseInt(val);
		//alert('allow10'+"#txtKkp_" + colid + "_" + id);
		//alert('allow10:'+val);
		//alert((val==1) +"||"+( val=="3")+" ||"+ (val=="9"));
		if(val==1 || val==3 || val==9){
			jQuery("#txtKkp_" + colid + "_" + id).val(val);
		}
		else{
			//alert('val'+val);
			jQuery("#txtKkp_" + colid + "_" + id).val('');
		}	
	} 

	function outFocus(id,colid,colname) {
		//alert("setfocus");
		var score = "0";
		var Tot = 0;
		var grdTot = 0;
		var cellValue = "0";
		var value;
		var kk = jQuery("#hdnKkkname").val();
		var kklength = kk.slice(1).split(",");
		var colslength = kklength.length-parseInt(5);
		//alert("colslength:"+colslength);
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
				//alert("cellValue:"+cellValue);
				score = parseInt(kklength[i]);
				score = score | 0;
				//alert("score:"+score);
				if(cellValue>=0 && cellValue<=9){
					Tot = parseInt(cellValue) * parseInt(score);
					grdTot = parseInt(grdTot) + parseInt(Tot);
				}
				else{					
					cellValue = parseInt(jQuery("#txtKkp_" + colId + "_" + id).val(0));
					flag = false;
				}
		}
		/*alert("grdTot"+grdTot);
		alert("projectLength:"+projectLength);*/
		jQuery("#txtProject_"+projectLength+"_"+id).val(parseInt(grdTot));
		if(flag){
		}else{
			alert("Value should between 1 to 9");
		}
		//Assign Rank
		var jqGridId="KKProject";
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		var cnt=0;
		for( var j = 0; j < allRows.length;j++){
			var rowIdaa=parseInt(j)+1;
			var totScore=jQuery("#txtProject_"+projectLength+"_"+rowIdaa).val();
			totScore=totScore|0;
			if(parseInt(totScore)>0){
				cnt=cnt+1;				
				jQuery("#txtRank_12_"+rowIdaa).val(cnt);
			}
		}
		//return false;
		cnt=0;
		if(allRows.length>0){
			for( var i = 0; i < allRows.length;i++){
				var rowId=	parseInt(i)+1;
				var totScore=jQuery("#txtProject_"+projectLength+"_"+rowId).val();
				//var rank=jQuery("#txtProject_"+projectLength+"_"+rowId).val();
				totScore=totScore|0;
				var sortflg=false;
				var rankaa=jQuery("#txtRank_12_"+rowId).val();
				/*alert("rowId"+rowId);
				alert("rankaa"+rankaa);*/
				//alert("rowId"+rowId+"totScore"+totScore+"rankaa"+rankaa);
				if(parseInt(totScore)>0){
				for( var j = 0; j < allRows.length;j++){					
					var rowIdaa=parseInt(j)+1;
					if(parseInt(rowIdaa)>parseInt(rowId)){
						
						var totScoreaa=jQuery("#txtProject_"+projectLength+"_"+rowIdaa).val();
						totScoreaa=totScoreaa|0;	
						//alert("rowIdaa"+rowIdaa+"totScoreaa"+totScoreaa);					
						//if(sortflg==false){
							if(parseInt(totScoreaa)>0){
								if (parseInt(totScoreaa)>parseInt(totScore)){
									//alert("totScore"+totScore+"totScoreaa:"+totScoreaa);	
									var rank=jQuery("#txtRank_12_"+rowIdaa).val();									
									//alert("assigned rank"+rank);
									//alert("assigned rankaa"+rankaa);															
									//if(rank.trim().length<=0){
										//alert("rank"+rowId);
										//cnt=cnt+1;
										sortflg=true;
										jQuery("#txtRank_12_"+rowId).val(rank);
										jQuery("#txtRank_12_"+rowIdaa).val(rankaa);										
									//}
								}
							}
						//}
					}
				}
				}
				//jQuery("#txtRank_12_"+rowId).val();
			}
		}
	}

	function gotFocuse(id,colId,rowId){
		/*var val=jQuery("#" + id).val();
		if(val=="1" || val=="3" || val=="9"){
			jQuery("#" + id).val(val);
		}
		else{
			//alert('val'+val);
			jQuery("#" + id).val('');
		}*/
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
						jsonArrO += '"txtDmpmKeyid":"'+masterkeyid+'"';
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
     //alert("select:"+select);
     var projectkeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"KEYID");
     //alert("projectkeyid:"+projectkeyid);
     var dfiwkeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"DFIWKEYID");
     var masterkeyid = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"MASTERKEYID");
     //alert("masterkeyid:"+masterkeyid);
     var kk = jQuery("#hdnKkkname").val();
	 var kklength = kk.slice(1).split(",");
	 
	 var projectLength = kklength.length-parseInt(4);
	 var ranklength = kklength.length-parseInt(3);
	 //alert("ranklength:"+ranklength+"  "+kklength );
     var score = jQuery("#txtProject_"+projectLength+"_"+rowid).val();
     //alert("ranklength"+ranklength);
     //var rank = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"Rank");
     var rank =jQuery("#txtRank_"+ranklength+"_"+rowid).val();
     //alert(rank +"Rank");
     var flid = jQuery("#frmKKProject input[id='flid']").val();
     //alert("flid"+flid);
     var approvedby = jQuery("#cmbDmpmApprovedby").combobox('getValue');
     //alert("approvedby"+approvedby);
     if(masterkeyid.trim().length !="0"){	
    	 keyid = masterkeyid;
     }
	else{
    	   keyid="";        
     }
     if(select=="1"){
    	 if((projectkeyid!=null && projectkeyid!="")&&(select!=null&&select!="") ) {
	    	 
				jsonArrO+= '{';
				jsonArrO += '"txtDmpmKeyid":"'+keyid+'",';
				jsonArrO += '"txtDmpmDmcmKeyid":"'+projectkeyid+'",';
				jsonArrO += '"txtDmpmFlid":"'+flid+'",';
				jsonArrO += '"txtDmpmApprovedby":"'+approvedby+'",';
				jsonArrO += '"txtDmpmRank":"'+rank+'",';
				jsonArrO += '"txtDmpmProjectscore":"'+ score+'"';
				jsonArrO+= '},';
				
			}
         }
     }
     jsonArrO = jsonArrO.slice(0, -1) + "]";
	 jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	 return encodeURIComponent(jsonArrO) ; //jsonArrO;
 
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
	for(var i=0;i<row.length;i++){
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
				jsonArrO += '"txtDmdlDmpmKeyid":"'+keyid+'",';
				jsonArrO += '"txtDmdlKkpmKeyid":"'+colIndexName+'",';
				jsonArrO += '"txtDmdlScore":"'+fieldvalue+'"';
				jsonArrO+= '},';				
			}
         }
         }
         }  
     

	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	 return encodeURIComponent(jsonArrO) ;//jsonArrO;
 
}	
	</script>


	
<div id='WrapperRpt' style="margin-top: 5px"  >			
<form name="frmKKProject" id="frmKKProject" action=" " method="post">

<table>
<tr>
<td>
 <div id="frmKKProjectFormatFuntKeyIds" >						
									<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
									<input type="hidden" id="section" name="cmbsection"  value=""></input>
									<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
									<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
									 <input type="hidden" id= "flid"  name= "txtDmpmFlid" value="${requestScope.fild}"/>
									<div id="kkprojectfunction" style=" ">
									</div>
									</div>
</td>
<td style="padding-left: 10px;padding-top:0px;" >
<div>
	<input type="checkbox" id="chkCheck" name="chkCheck" />
</div>
</td>
<td style="padding-left: 10px;">
<div>
<label>Approved by</label>
</div>
<div><input type="combobox" id="cmbDmpmApprovedby" name="cmbDmpmApprovedby" class="easyui-combobox"  style="width:255px;text-align:right;" value="${requestScope.approved }"/></div>
</td>

</tr>
</table>
<div id="jqGridKKproject">
<table id=KKProject><tr><td></td></tr></table>
<div id='pager'></div>
</div>

 <input type="hidden" id="mode"/>
 <input type="hidden" id="hdnKkkname" value="${requestScope.kkk}"/>
 <input type="hidden" id="hdnKKK" value=""/>
 <input type="hidden" id="hdnMasterKeyid" value=""/>
  <input type="hidden" id="hdnDfiwKeyid" value=""/>
 <input type="hidden" id="hdnFilt" value="${requestScope.fild}"/>
 <input type="hidden" id="hdnIsCheckList" value="${requestScope.checkList}"/>
</form>
</div>