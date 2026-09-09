<script type="text/javascript">
		jQuery(document).ready(function()
		{   
			//alert(" Inside this File needeed ");
			//var keyid=jQuery("#hdnDmtnKeyid").val();
			//alert("val  key  "+getFieldValue("hdnDmtnKeyid"));
			initialiseForm("frmDMT");
			jQuery('#submitForm').val('frmDMT');
			//alert("reas");
			fillComboBox("frmDMT", "cmbDmtnType", "type.commonFilter");
			fillComboBox("frmDMT", "cmbDmtnResponsibleid", "employee.commonFilter");
			
			//fillComboBox("frmDMT","cmbDmtnResponsibleid", "circle.commonFilter" );
			//alert("hdhs");
			//formatDateBox('dteDmtnfromdate', 'dd-MMM-yyyy');
			//formatDateBox('dteDmtntodate', 'dd-MMM-yyyy');
			formatDateBox('dteDmtmDate', 'dd-MMM-yyyy');
			//fillWithCurrentDate('dteDmtmDate');

			var factId = jQuery(
					"#frmDMT input[id='factory']").val();
			var sectionId = jQuery(
					"#frmDMT input[id='section']").val();
			var cellId = jQuery("#frmDMT input[id='cell']")
					.val();
			var machId = jQuery(
					"#frmDMT input[id='machine']").val();
			var flId = jQuery(
			"#frmDMT input[id='flid']").val();

            var gridparameter =jQuery('#hdngrid').val();

            if(gridparameter=="true"){

             }else{

            	fillWithCurrentDate('dteDmtmDate');
              }
                
            //alert("gridparameter"+gridparameter);
			
			//alert("flId" +flId);
			
			 var dataStr = "&factId=" + factId + "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId + "&flid=" + flId;
 
			 loadFunctionalLocation("DMTNotebookLocation", "functionalLoc.dmtn", "DMTNotebookLocation", "frmDMT",dataStr);

			 var url = jQuery('#hiddenUrl').val();
			 
			 var tableCaption = "Equipment Query";
			
			 processGridnew("DmtNotebookFormatForm_input.dmtn","?q=2","Dmtgrid","Dmtpager","","","","Load_Complete");

           	 fileManagerPopUp("","DMT","frmDMT","btnfilemgr","DmtFilemgr"); 

         	 jQuery("#btnAddNew").click(function(){
             	  
   			 var row  = jQuery("#Dmtgrid").jqGrid('getDataIDs');
     		 addRow(row);				
     		  
   			});
         	 jQuery("#btnvwexlrpt").click(function(){
            	  
         		var Dmtmasterid = jQuery("#hdnDmtmKeyid").val();
         		//alert("masterid:::::::"+Dmtmasterid);
         		var DmtdetailKeyid= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdKeyid");
//         		alert("detailKeyid:::::::"+DmtdetailKeyid);
         		
         		window.open("DmtNotebookFormat_Excelview.dmtn?Dmtmasterid="+Dmtmasterid+"&DmtdetailKeyid="+DmtdetailKeyid);			
         		  //?detailKeyid="+detailKeyid+"&actionplankeyid="+actionplankeyid,
       			});
         	
         	 jQuery("#btnDelete").click(function()
 				   	{	
 						 removeRecord();
 					});

		});
		function remove_successCallBack(result)
		   {
		 		jQuery("#Dmtgrid").trigger("reloadGrid");
		   }

		function frmDMT_successsCallback(result)
		{
			//navigateToPrevForm();
			clearForm("frmDMT");
			jQuery("#Dmtgrid").trigger("reloadGrid"); 
		}
		 function frmDMT_deleteSuccessCallback(result)
		 {

	        alert(result.successData.msg);
		 	clearForm("frmDMT");
		 	jQuery("#Dmtgrid").trigger("reloadGrid");
		 	  
		 }
				   
		function removeRecord(keyid)
		{	
	 		  	var Dmtrow=jQuery("#Dmtgrid").jqGrid('getDataIDs');  //row get data
			   	var rowid="";
			    var r=confirm("Do You Want To Delete?");
			    var i;
			    
			   	for(i=0;i<Dmtrow.length;i++)
			      {
				     rowid=Dmtrow[i];
				     var select= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"selectval");
				     //alert("select"+select);
	                 if(select=='1'){
	                	 var detailKeyid= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdKeyid");
	                	 //alert("detailKeyid:::::: "+detailKeyid);
	                	 var actionplankeyid=jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdActionplanid");
	                	 //alert("actionplankeyid:::::: "+actionplankeyid);
	                	 //alert("detailKeyid"+detailKeyid);
			             //if(detailKeyid!=null && detailKeyid!='undefined'&& detailKeyid!=detailKeyid && keyid!=""){	alert(" detailKeyid::::Inside if ::::1111 ");
	                        	if(r==true){      //alert(" detailKeyid::::Inside if ::::1111 ");
	                        		processAjaxCalls("DmtNotebookDetails_delete.dmtn?detailKeyid="+detailKeyid+"&actionplankeyid="+actionplankeyid,"",'remove_successCallBack');
	                				return true;
						   	       	}
				   			        else 
				   					   return false;
					   			    	          
			   			   // }
	                  
	                  }
	                     
			      }     		
			}

		function dmt_selectRow(id){
			//alert("selct check box");
			}	
		
		function addRow(row)
		{ 
			  var i=0;
			  var val =  jQuery("#hdnVal").val();
			  var j = parseInt(val);

			  var emptyItem;
			  
		     if ( row == null || row == '' || parseInt(row) <= 0) 
			     {
					
	    	        jQuery("#Dmtgrid").jqGrid('setCell',1,'selectAnswer','1');
					emptyItem =[{txtKEYID:" ",txtCheckbox:" ",txtType:" ",txtDetailsofIssueDiscussed:" ",txtActionPlannumber:" ",txtActionPlan:" "}];
					jQuery("#Dmtgrid").jqGrid('addRowData',j, emptyItem[0]);
					var k = j+1;
			    	jQuery("#hdnVal").val(k);
			    	
			     }	
			 else
			      {
			              
				     for(i=0;i<row.length;i++)
						lastRow = row[i];

				     if(j==1){
								emptyItem =[{txtKEYID:" ",txtCheckbox:" ",txtType:" ",txtDetailsofIssueDiscussed:" ",txtActionPlannumber:" ",txtActionPlan:" "}];
							}
				    	
				     	else
					     {
				     		emptyItem =[{txtKEYID:" ",txtCheckbox:" ",txtType:" ",txtDetailsofIssueDiscussed:" ",txtActionPlannumber:" ",txtActionPlan:" "}];
							
						 }
						     	jQuery("#Dmtgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
								var k = j+1;
						    	jQuery("#hdnVal").val(k);			
			      }
		}	
		function Actionplane_onClose()//reload grid
		{
			 jQuery("#Dmtgrid").trigger("reloadGrid");
			  return true;
		}
		function actionplan(rowid){
			
			var refDocId= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdKeyid");
			var refDocDtlissue = getFieldValue("txtdmttext_"+rowid);  //txtdmttext_1
			//var refDocDtl= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdDetlsofisuediscd");  
			//alert("refDocDtlissue::::From Hidden...."+refDocId);
			var flid=jQuery("#frmDMT input[id='flid']").val();
			//alert("flid:::::::   "+flid);
			var refDocMstId=jQuery('#hdnDmtmKeyid').val();
            //alert("Masterid:::::::: "+Masterid);
			var dataString = "refDocId="+refDocId+"&refDocType=DMT&refDocDtl="+escape(refDocDtlissue)+"&refDocMstId="+escape(refDocMstId)+"&flid="+flid;
			LoadPopUp("Actionplane","ActionPlan_input.api?"+dataString, true, "87%", "80%", "7%", "6%", " ", "Action Plan","",true);

			//LoadPopUp("actionplan","ActionPlan_input.api?refDocId="+detailKeyid+"&refDocType="+DMT+"&refDocDtl="+escape(refDocDtlissue) ,true,"90%","500px","1%","3%","","Action Plan","",true);

			}
     	
		function Load_Complete()
		 {		
			var row = jQuery("#Dmtgrid").jqGrid('getDataIDs');
			//alert(" row:::::  "+row);
			for(var i=0;i<row.length;i++){
				rowid=row[i];
			//fillComboBoxWithGrid("frmDMT","cmbdmtcombo","type.commonFilter");  || detailKeyid!="undefined"
			var detailKeyid= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"txtDmtdKeyid");
			
			
				if(detailKeyid!=" " && detailKeyid.length>0){ 
			        //alert(1);
			        //alert(" detailKeyid Inside Load_complete "+detailKeyid);
					chkboxCheck(rowid);
					jQuery('#chkdmtcheckbx_'+rowid).attr('checked',true);
				}   
				else{//alert("rowid;;;;;;;;;;;;;;;;"+rowid);
					
				var selectval= jQuery("#Dmtgrid").jqGrid('getCell',rowid,"selectval");
				
				if(selectval==""){
					
					jQuery('#chkdmtcheckbx_'+rowid).attr('checked',false);
					chkboxUnCheck(rowid);
					}
					//jQuery('#chkdmtcheckbx_'+rowid).attr('checked',false);
					}
			}
			}

		function Txtdmt(id, options, rowObject){
            //alert("rowObject[1]"+rowObject);
			var Id = options.rowId;
			//alert(" Id:::::       "+Id);
			var columnName = options.colModel.name;
			//alert("columnName:        "+columnName);
			var columnid = options.pos;
			var type=rowObject[2];
			var Details=rowObject[3];
			if(type==undefined || Details==undefined)
			{ 
				type="";
				Details= "";
			}
			//alert("columnid:4       "+columnid);         onclick="if(this.checked){chkboxCheck(\''+ Id + '\');}else{chkboxUnCheck(\''+  Id +'\')/
			//return '<input id="chkdmtcheckbx" name="chkdmtcheckbx" type="checkbox" value="" onclick="if(this.checked){chkboxCheck(\''+ Id + '\');}else{chkboxUnCheck(\''+  Id +'\')"/ >';
		
			if(columnid==2){
				//alert(" Inside chkbox formatter "+rowObject[2]);
				return '<input id="chkdmtcheckbx_'+Id+'" name="chkdmtcheckbx" '+ (rowObject[2]!=" " ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+Id + '\');}else{chkboxUnCheck(\''+Id +'\')}" />';
			}
			else if(columnid==3)
				 //return '<input class="easyui-combobox" id="cmbdmtcombo_'+Id+'" name="cmbdmtcombo_'+Id+'"   style=" width :150px;"  value="" />';	
				return '<span><span id="comBoN_'+Id+'"></span><span id="comBoY_'+Id+'" style="display:none; "><input class="easyui-combobox" id="cmbdmtcombo_'+Id+'" name="cmbdmtcombo_'+Id+'"   style=" width :150px;"  value="'+type+'" /></span></span> ';
			else if(columnid==4)
				return '<span><span id="textN_'+Id+'"></span><span id="textY_'+Id+'" style="display:none;"><input id="txtdmttext_'+Id+'" name="txtdmttext_'+Id+'" type="text" value="'+Details+'" style="width:300px;text-align:left;height:20px;text-transform: uppercase;"/></span></span>';
			else if(columnid==6)
				return '<span><span id="btnN_'+Id+'"></span><span id="btnY_'+Id+'" style="display:none;"><input type="button" class="easyui-button" id="btnactionplan_'+Id+'" name="btnactionplan_'+Id+'" onclick="actionplan('+Id+')"  value="..."  style="height:21px;"/></span></span>';
		    
			}
			
		function chkboxCheck(rowid){ //alert("Inside1 ::::: "+rowid);
				//alert(0l);
			 	//alert(rowid);	
			 //alert(" Inside chkboxCheck function :::: ");
             var arr=[];
			 arr.push("comBoY_","textY_","btnY_");
			 showCol(rowid,arr);
			 var array=[];
			 array.push("comBoN_","textN_","btnN_");
 			 hideCol(rowid,array);
 			 jQuery("#Dmtgrid").jqGrid('setCell',rowid,'selectval','1');
			
		}
		function chkboxUnCheck(rowid){ //alert("Inside2 ::::: "+rowid);

			 	
			
             var arr=[];
			 arr.push("comBoY_","textY_","btnY_");
			 
			 var array=[];
			 array.push("comBoN_","textN_","btnN_");

			 showCol(rowid,array);
 			 hideCol(rowid,arr);
 			 
 			 jQuery("#Dmtgrid").jqGrid('setCell',rowid,'selectval','0');
 			 	
		}

		function showCol(rowid,arr){
			
			fillComboBox("frmDMT","cmbdmtcombo_"+rowid,"type.commonFilter");
			 
			 for(var i=0;i<=arr.length;i++){
				 
				 jQuery('#'+arr[i]+rowid).show();
				 
				 }

		}
		function hideCol(rowid,array){
    		 
             var id=parseInt(rowid);
			
			 for(var i=0;i<=array.length;i++){//alert("Inside hideCol show ::::for "+array[i]+id);
				 jQuery('#'+array[i]+id).hide();
				 }
			
		}
		 
		 /*function frmDMT_deleteSuccessCallback(result)
		{
			alert(result.successData.msg);
		}*/
		  
		  function btnfilemgr_click()
	        {
		      //alert("11");
	          //var documentNo =jQuery("#hdnDmtnKeyid").val();

	  	      if(1 != null && 1 != '')
			  {
			 	 fileManagerPopUp(1,"DMT","","","");
		      }
		
	         }

		    function frmDMT_beforeSubmit() {
				
		    	//alert(" Inside beforeSubmit ");

				var griddata = "gridData="+ converToJsonObject("Dmtgrid");

				//alert(" Inside beforeSubmit "+griddata);

				return griddata;
					  
			  
		    }
		    
            function converToJsonObject(jqGridId)
			{
    			//alert(" Inside converToJsonObject 1 ");
				var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
				//alert("row::::"+row);
				var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
				var flag = true;
				//var type;
				//var typeval;
				//alert(" Inside converToJsonObject 2 ");
				var jsonArrO='[';
				for(var i=0;i<row.length;i++)
				{
				// alert(" Inside converToJsonObject 3 ");  var colNameVal=colName.replace('txtCasm','');
				 rowid=row[i];
			     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
			    // alert(" Inside converToJsonObject 4 "+jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval"));
			     var type = getComboBoxText("cmbdmtcombo_"+rowid);
			     //alert(" Inside converToJsonObject 5 ");
			     var detailtext=jQuery("#txtdmttext_"+rowid).val();
			     //alert(" Inside converToJsonObject 6 ");
			     var detailKeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"txtDmtdKeyid");//detailkeyid   
			     //alert(" Inside converToJsonObject 7 "+detailKeyid);

			     var Actionplanid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"txtDmtdActionplanid");
			     //alert(" Inside converToJsonObject "+masterKeyid);
			     
		    	 if(detailKeyid.trim().length !="0"){
		       	   keyid = detailKeyid;
		          }
		        else{
		        	   keyid=" ";        
		            }
		            
			     //if(select=="1"){
			     
			    		jsonArrO+= '{';
						jsonArrO += '"txtDmtdKeyid":"'+keyid+'",';
						//jsonArrO += '"txtDmtdDmtmKeyid":"'+masterKeyid+'"';
						jsonArrO += '"txtDmtdType":"'+type+'",';
						jsonArrO += '"txtDmtdDetlsofisuediscd":"'+detailtext+'",';
						jsonArrO += '"txtDmtdActionplanid":"'+Actionplanid+'"';
						jsonArrO+= '},';	
			       //  }
			    // else if(select=="0") {

				     //alert("Click CheckBox");
			    	 //return false;
				     
			        // }  
			     
			}
				
				jsonArrO = jsonArrO.slice(0, -1) + "]";
				jsonArrO = (jsonArrO != ']'?jsonArrO:"");
                //alert("jsonArrO:::::"+jsonArrO);
				return jsonArrO;
				 
			}

			//processGridnew("DmtNotebookFormatForm_input.Dmtn","q=2","DmtNoteGrid","pager","","docDoubleClick");
			
					
					
          /* function viewGrid(url,dataString,tableCaption)
			  {
			      //alert(url+" dataString  "+dataString+"  tableCaption  "+tableCaption);
			      processGridnew("DmtNotebookFormatForm_input.dmtn",dataString,"DmtNoteGrid","pager",tableCaption,"docDoubleClick");
			  }	*/
			
		
	/*	function docDoubleClick(id)
		{	
			var rowData = jQuery("#DmtNoteGrid").jqGrid('getRowData',id);
			var keyId = rowData.Keyid;
			alert("keyId"+keyId);//Keyid
			processAjaxCalls("PrepareQuestionsRecall_input.prepques?keyId="+keyId,"","selectedReport_onsuccesscallback");
			viewGrid('q=2&keyId='+keyId);
			
		
		}*/
					
		
	
		
</script>

<style type="text/css">


</style>


 <body>
   <form id="frmDMT" name="frmDMT" method="post" >
   
<div style="margin-top:40px;margin-left:120px;">
  
<div id="wrapper" style="width:100%;" >
 
  <table>
  <tr>
  <td colspan="3">
  <div> <div id="frmmomFuntKeyIds">
			<input type="hidden" id="factory" name="cmbdmtnfactory" value=""></input> 
			<input type="hidden" id="section" name="cmbdmtnsection" value=""></input> 
			<input type="hidden" id="cell"    name="cmbdmtncell" value=""></input> 
			<input type="hidden" id="machine" name="cmbdmtnmachine" value=""></input>
			<input type="hidden" id="flid"    name="cmbDmtmFlid" value="${requestScope.genTlDmtnotebookmaster.dmtmFlid}"></input>
	</div>
	<div  class="easyui-paddingbfpx" id="DMTNotebookLocation" style="width:820px;"></div>

  </div>
  </td>
  
	  </tr>
	  </table>
 
       <table>
<!--       <tr>-->
<!--       <td>-->
<!--	       <div><label class="mandatory-lbl">Type</label></div>-->
<!--	       <div><input class="easyui-combobox" style="width:235px; " id="cmbDmtnType" name="cmbDmtnType" value="${requestScope.kzntldmtnotebookmst.dmtnType}" /></div>-->
<!--       </td>-->
<!--       <td>-->
<!--       <div style="padding-left:10px;">-->
<!--       <div><label class="mandatory-lbl">Responsibility</label> </div>-->
<!--       <div><input class="easyui-combobox" style="width:235px; " id="cmbDmtnResponsibleid" name="cmbDmtnResponsibleid" value="${requestScope.kzntldmtnotebookmst.dmtnResponsibleid}" /></div>-->
<!--      </div>-->
<!--       </td>-->
<!--       <td>-->
<!--       <div style="padding-left:10px;">-->
<!--       <div><label class="mandatory-lbl">Target Date</label></div>-->
<!--       <div><input class="easyui-text"id="dteDmtnTargetdate" name="dteDmtnTargetdate" maxlength="20"  value="${requestScope.kzntldmtnotebookmst.dmtnTargetdate}"style="width: 110px; height: 21px;" value=""/></div>-->
<!--       </div>-->
<!--       </td>-->
<!--       <td>-->
<!--       <div style="padding-left:10px;">-->
<!--       <div><label class="mandatory-lbl" style="padding-left:3px;padding-left:3px\9;">Completed Date</label></div>-->
<!--       <div><input class="easyui-text"id="dteDmtnCompleteddate" name="dteDmtnCompleteddate" maxlength="20" style="width:130px;height: 21px;" value="${requestScope.kzntldmtnotebookmst.dmtnCompleteddate}"/></div>-->
<!--       </div>-->
<!--       </td>-->
<!--      -->
<!--       </tr>-->
<!--       <tr>-->
<!--	   <td>-->
<!--	   <span id="err_cmbDmtnType" class="tpm-errormsg"></span>-->
<!--	   </td>-->
<!--	   <td style="padding-left:10px;">-->
<!--	   <span id="err_cmbDmtnResponsibleid" class="tpm-errormsg"></span>-->
<!--	   </td>-->
<!--	   <td style="padding-left:10px;">-->
<!--			<span id="err_dteDmtnTargetdate" class="tpm-errormsg" style=""></span>-->
<!--			-->
<!--	class="mandatory-lbl"   </td>--> 
<!--	   <td style="padding-left:10px;">-->
<!--	   <span id="err_dteDmtnCompleteddate" class="tpm-errormsg"></span>-->
<!--	   </td>-->
<!--   </tr>-->
       <tr>
       <td>
       <div><label class="mandatory-lbl">Issue(s) Discussed</label></div>
       <div><textarea rows="2" style="width:235px;height:50px; resize: none;text-transform: uppercase;" maxlength="200" cols="" id="txtDmtmIssuesdiscussed" name="txtDmtmIssuesdiscussed" >${requestScope.genTlDmtnotebookmaster.dmtmIssuesdiscussed}</textarea></div>
       </td>
       
       <td valign="top">
       <div style="padding-left:10px;">
       <div><label>Date</label></div>
       <div><input class="easyui-datebox"id="dteDmtmDate" name="dteDmtmDate" maxlength="20" style="width:130px;height: 21px;" value="${requestScope.genTlDmtnotebookmaster.dmtmDate}"/></div>
       </div>
       </td>
        <td>
          <div style="padding-left:10px;">
		  <div style="position:relative; ">
		     <span  id="DmtFilemgr" style="position:absolute;top:-18px;left:0px;" ></span> 
		  </div>
		  </div>
	  </td>	
       </tr>
       <tr>
		 <td>
		 <span id="err_txtDmtnDiscussion" class="tpm-errormsg"></span>
		 </td>
		 <td>
		 <span id="" ></span>
		 </td>
		 <td>
		 <span id="" ></span>
		 </td>
 </tr>
	
      </table>

<table> 
<tr>
<td>
	<div style="float:right;">
	<input type="button" class="easyui-button" value ="View Excel Report" id="btnvwexlrpt" name="btnvwexlrpt" style="height:23px"/>
	<input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px"/>
	<span style="padding-left:5px;"><input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/></span>
	</div>
	
</tr>
<tr>
<td>
	 <div>
	<table id='Dmtgrid'><tr><td></td></tr></table>
    <div id='Dmtpager'></div>
	</div>
	</td>
</tr>
</table>
	      
		  
</div>

</div>
        <input id="mode" type="hidden" >
        <input id="hdnDmtmKeyid"  name="hdnDmtmKeyid" type="hidden" value="${requestScope.genTlDmtnotebookmaster.dmtmKeyid}" >
        <input id="hdngrid" name="hdngrid" type="hidden" value="${requestScope.dmtGrid}">
	    <input type="hidden" id="hdnVal" value="1" />
 
 </form>
  
</body>