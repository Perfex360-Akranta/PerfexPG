<!--  Author ManiKandan-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script>
jQuery(document).ready(function(){	
	
	//viewGrid("AbnRpt_input.abnRpt","");
	var url = jQuery('#hiddenUrl').val();
	if(url == "jhcalendar_input.jhcal")
	jQuery('#actualImg').css('display','none');
    numericTextBox('txtactual');//for number only validation
	
	/*for jh scheduled and standard report **/
	var mchId= jQuery('#jhsshdnKeyid').val();
	var frmMOnth =jQuery('#jhsshdnfltrstring').val();
	var filterStr = jQuery('#hdnFromStandard').val();
 
	 
	//alert('calendar'+filterStr);
	//alert('len'+filterStr.length);
	//alert('s');    
    if (filterStr.length > 0)
    { 
        if(frmMOnth.length > 0 )
        	viewGrid(url,frmMOnth);
        else
	 	   viewGrid(url,filterStr);
    }
    else
    {
    	viewGrid(url,"");
    	
    	if(mchId ==  null || mchId == "" ){
    	//alert("no mch value");
    	
       // processGridnew(url,filterStr,"list","pager","");
       setLoadFormCallBackFrmId('frmjhclitCal');	
    	//toggleCommonFilter();
		//loadCommonFilter();
    	}
    	else{alert('mch value present');}
    }				
    jQuery('#close_actual').click(function(){
    	//var colindex = 	 (parseInt(jQuery('#position').val()));
   		 closeActualDialog();
    	//unselectData(colindex);
    	//jQuery( "#actualval" ).hide();
    	//jQuery( "#actualval" ).css('display','none');
    	
    });
    jQuery('#abnormalityLink').click(function(){
    	 var refDocId = jQuery('#selRefDocId').val();
    	 var selMachineId = jQuery('#selMachId').val();
    		var filtStr=jQuery.cookie("filterString");
    	 navigateToNextForm("Abnormality_input.abnForm?q=2&refDocId="+refDocId+"&filterButton=false"+"&selMachineId="+selMachineId,"Abnormality Identification",null,{"filterString":filtStr});
    });
    jQuery('#msrLink').click(function(){
    	var refDocId = jQuery('#selRefDocId').val();
		var filtStr=jQuery.cookie("filterString");
		var selMachineId = jQuery('#selMachId').val();
	 navigateToNextForm("workReq_input.work?q=2&bookingMode=msrInsert&refDocId="+refDocId+"&selMachineId="+selMachineId,"Maintenance Service Request",null,{"filterString":filtStr});
    	
    });
    jQuery('#actionPlanLink').click(function(){
        //alert(jQuery('#selRefDocId').val());
        
        var refDocId = jQuery('#selRefDocId').val();
        openActionPlan(refDocId);
        
    });
    
});
/*keypress event to enable / disable link buttons*/
jQuery(document).keypress(function(e) {
	 e.stopImmediatePropagation();
	    if( e.which == 13 )
	    {
	        e.preventDefault(); 
        var celval = jQuery(this).html();
        if(celval.trim().length == 1)
    		btnEnableDisable(true); /*function for enable / disable link buttons*/
        else
        	btnEnableDisable(false);/*function for enable / disable link buttons*/
		//alert(celval);         
    }
});

function frmjhclitCal_afterLoadCallBack(){
	
	toggleCommonFilter();	
}
function frmFilter_enableDisableSuccessCallBack(){ 
	
	fillWithCurrentMonth("dtefromMonth");
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = " CLIT - Day and Above Calendar - Dec-2011 (F02-1007 - GAS TREATER) ";
		//alert(url);
		//alert(filterString);
		processGridnew(url,filterString,"list","pager",tableCaption,"","","jhCalLoadComplete");
		 jQuery('.ui-jqgrid-labels').toggleClass('white-colr');
		return true;
	}	
}
function jhCalLoadComplete(){

	 jQuery("#list").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue) {
				jQuery('#hdncelvalue').val(cellvalue);
				if(id > 3)
					if(cellvalue.trim().length == 1)
					{
						var rowData = jQuery("#list").jqGrid('getRowData',id);
						
						jQuery('#selRefDocId').val(rowData.RefDocno);
						btnEnableDisable(true);/*function for enable / disable link buttons*/
					}
					else{
						 
						btnEnableDisable(false);/*function for enable / disable link buttons*/
						
						}	
			}
	 });
}
function btnEnableDisable(status){
	
	if(status){
		jQuery('#actionPlanLink').attr('disabled',false);	
		jQuery('#abnormalityLink').attr('disabled',false);	
		jQuery('#msrLink').attr('disabled',false);	
	 }
	else{
		jQuery('#actionPlanLink').attr('disabled',true);	
		jQuery('#abnormalityLink').attr('disabled',true);	
		jQuery('#msrLink').attr('disabled',true);
			

		}
}
function openActionPlan(id) 
{
	var refDocType="CLI";//jQuery("#hdnFromLink").val();
	var selMachineId = jQuery('#selMachId').val();
	//jQuery("#hdnMstId").val(id);
	var params="ActionPlan_input.ap?mode=INSERT&refDocId="+id+"&refDocType="+refDocType+"&selMachineId="+selMachineId+"&from=popupfrm";
	LoadPopUp("divShowActionPlanSave",params,true,"77%","75%","1%","12%","multiSelectOk_Callback","Action Plan Save Entry","",true);
}

function frmjhclitCal_ongridcomplete(){

	 jQuery('.ui-jqgrid-labels').hide();
	 var ids = jQuery("#list").getDataIDs();
     if (ids.length < 3)
   	  alert("NO PLAN EXISTS");
     jQuery("#list thead").children(":first").toggleClass('remove_border');
     //jQuery("tr[class=ui-jqgrid-labels]").css('height','10px');
	     //hideJqGridRow('list', );
     /*
 	  for (var i = 0; i < rowsToColor.length; i++) {
          var status = jQuery("#" + rowsToColor[i]).find("td").eq(3).html();
          if (status == "Complete") {
          	jQuery("#" + rowsToColor[i]).find("td").css("background-color", "red");
          	jQuery("#" + rowsToColor[i]).find("td").css("color", "silver");
          }
      } */
     
}
function chk_box(id,cellvalue){
  
	if(id=='3'&& cellvalue!="" ){
	formatStr =' <input  type="checkbox" id="chksel" checked="checked" onclick="if(this.checked){selectData('+ position +');}else{unselectData('+position + ');}" />';
	}
  }
  function chkboxCheck(rowId,colid){
	  //alert('#Activity_checkbox'+"_"+rowId+"_"+colid);
		
	jQuery('#Activity_checkbox'+"_"+rowId).attr("value","1");  
  }
  function chkboxUnCheck(rowId,colid){
	  var tableDatas = jQuery("#list").jqGrid('getRowData');
	//	alert(tableDatas[rowId].RefDocno); 
	  jQuery('#Activity_checkbox'+"_"+rowId).attr("value","0");
  }
function chkbox_Activity(id, options, rowObject)
{ 
	var rowId = options.rowId;
	//alert("rowId:"+rowId);
	var pos=options.pos;
	//alert("pos:"+pos);
	
	//alert(rowObject[rowValue[i]]);
		//alert(pos);
		var formatStr = "<span><span>";
		if(parseInt(rowId) > 3 ){
			formatStr ='<input id="Activity_checkbox'+"_"+rowId+'" name="Activity_checkbox"   type="checkbox"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\',\''+pos+'\');}else{chkboxUnCheck(\''+ rowId +'\',\''+pos+'\')}"/>';
		}
	return formatStr ;
}
function clrFormatter(cellvalue, options, rowObject) {	
	/*&#10003;*/
	var rowid = options.rowId;
	 var Chk_data=jQuery("#list").getCell(3, position+1);//to get the value of actual of the selected cell
	 if(Chk_data!= null ||Chk_data != " ")
		//jQuery('#chksel').checked(true);	 
	//for row colors
     var ids = jQuery("#list").getDataIDs();
     var id = options.rowId;
     
     var position = parseInt(options.pos);	
 
	 var create = ids.length <=2;
	 var plan_actual =ids.length <=3;
	// alert("cellvalue" + rowObject[0]);
	var url = jQuery('#hiddenUrl').val();
	
	if(url.indexOf("jhcalendar_input.jhcal")>=0){
		if(create ){
			 
	  		jQuery('#list tr').removeClass("ui-widget-content");
	  		jQuery('#list tr').addClass("header-colr");
		}
  	}  
	else{
		if(plan_actual){
			 
	 		jQuery('#list tr').removeClass("ui-widget-content");
	  		jQuery('#list tr').addClass("header-colr");
		}
 	 	}
 	//alert(rowId,colModel,gid,pos);
	
	var formatStr = "";
	chk_box(id,cellvalue);
	
		if(cellvalue=='chkB' ){
			
			//alert(cellvalue + " id - " + id + ' position -' + position);
			formatStr =' <input  type="checkbox" id="chksel" onclick="if(this.checked){selectData('+ position +');}else{unselectData('+position + ');}" />';
				//alert(formatStr);
	
			}
		
		else if(cellvalue.trim() == '-1' && rowObject[0] != "creation"){
			formatStr = '<span  style="background-color:#C0C0C0;font-weight:bold;font-size:14;padding-bottom:11px;"  class="cellWithoutBackground"></span>' ;
			//formatStr =  '<input id="activity_checkbox'+id+"_"+position+'" name="activity_checkbox"   type="checkbox"  onclick="if(this.checked){chkboxCheck(\''+id + '\',\''+position+'\');}else{chkboxUnCheck(\''+ id +'\',\''+position+'\')}"/>';
		}
		else if( cellvalue.trim() != '0' &&  rowObject[0] != "creation" && rowid > 3 ){
			formatStr = '<span id= "celVal_'+id+'_'+position+'"  style="background-color:#C0C0C0;font-weight:bold;font-size:14;padding-bottom:11px;padding-left:10" class="cellWithoutBackground" >&#10003;</span>' ;
			//formatStr =  '<input id="activity_checkbox'+id+"_"+position+'" name="activity_checkbox"   type="checkbox"  onclick="if(this.checked){chkboxCheck(\''+id + '\',\''+position+'\');}else{chkboxUnCheck(\''+ id +'\',\''+position+'\')}" checked="checked"/>';
		}
		else if(cellvalue=="0")	
			formatStr = "<span></span>  " ;
	    else
			formatStr =cellvalue;
	
	return  formatStr;
	
}

function selectData(position) {	
	/*&#10003;*/
	  jQuery('#position').val(position);
	  var Chk_data=jQuery("#list").getCell(3, position);//to get the value of actual of the selected cell
	  jQuery('#txtactual').val("");//txtboxvalue of actual in pop up
	  if(! isNaN(Chk_data)){
		  jQuery('#txtactual').val(Chk_data);   
	   }
	  acutal_dialog(position);
	
	 // add_acutal(position);
}
function unselectData(position){
  alert('d');
	var col_Idx = (position-9); 
	var Chk_data=jQuery("#list").getCell(3, position);
	
	if(isNaN(Chk_data)){
			//doIt=alert("Cant delete no data available in actual");
		return false;
		}
	else{
		 doIt=confirm('Do You Want To Delete?');
		
	}
	
	  if(doIt){
		  processAjaxCalls("jhClitcalendar_del.jhcal?","&position="+col_Idx ,"unSelSuccess(position)","unselError");
		  jQuery("#list").jqGrid('setCell', 3,position , "0.0");
	  }
	  else{
	   	  //alert("data not deleted");
	  }
		
}
function unSelSuccess(position){
	alert("Deleted");
	//var col_Idx = (position-8); 
	//alert(col_Idx);
	
	//jQuery('#list').trigger("reloadGrid");
}
function unselError(){alert("not deleted");}
function acutal_dialog(position){
	  
	
	jQuery( "#actualval" ).show();
	jQuery( "#actualval" ).dialog({
		autoOpen: false,
		modal: true,
		height: 120,
		width : 200,
		top:210,
		left:800,
		title:navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0"?'-':"actualval",
		onClose:function(){
	    		 jQuery('input:checkbox[id=chksel]').attr('checked',false);
	        	}
			
	});
	if(navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) != "MSIE 8.0,8.0"){ 
		jQuery('div .panel-title ').filter(function() { 
			  return jQuery(this).html() == 'actualval'; 
			}).remove();
	}
	
	jQuery(".window-shadow").hide();
 
}

function add_acutal(position){
	var rowId =3;
	var act_data=jQuery('#txtactual').val();
	jQuery("#list").jqGrid('setRowData',rowId,{position: act_data });
	//alert("position  "+position +"   dsf   "+act_data);
	//jQuery('#txtactual').val("");
	
}
jQuery('#save_actual').click(function(){

	var colIndex = jQuery('#position').val();
	// alert("colIndex   "+colIndex );
 	var planed = jQuery("#list").jqGrid('getCell',"2",colIndex); 
	//alert("planed  "+planed);
	add_acutal(colIndex);
	var actual = jQuery('#txtactual').val();
	//alert("actual  "+actual);
	var tableDatas = jQuery("#list").jqGrid('getRowData');
	// alert("tableDatas   :"+tableDatas.length +"----------"+colIndex );
	var jsonData =  '[';
	

	for(var i =3 ; i<tableDatas.length;i++)
	{
		//alert(Object.keys(tableDatas[i]));
			var backgroundcolor = tableDatas[i][ parseInt(colIndex)-9 ];
			//alert(backgroundcolor );
			if( backgroundcolor != null && backgroundcolor.indexOf('background-color:') > -1 ){
				backgroundcolor = backgroundcolor.substr(backgroundcolor.indexOf('background-color:') + 'background-color:'.length, 7);
				//alert("if  "+backgroundcolor) ;
			}
			//else
				//alert("else  "+backgroundcolor) ;
		if(backgroundcolor =='#C0C0C0' )
		{
			//alert("refDoc   :"+tableDatas[i].RefDocno );
			//alert("time in mins   :"+ tableDatas[i].timeinmins);
			//alert("actual  :"+actual);
			//alert("colind   :"+ parseInt(colIndex) - 6);
			var value = jQuery('#Activity_checkbox'+"_"+i).attr("value");
			if(value == 1){
				//alert(i+"  actual  "+tableDatas[(i-1)].RefDocno);
			jsonData  += '{"txtClcaClirefid":"'+tableDatas[(i-1)].RefDocno   + '","txtClcaDuration":' +
						 '"'+ tableDatas[(i-1)].timeinmins + '","txtClcaActualduration":"'+ actual +'","txtClcaPlandate":"'+(  parseInt(colIndex) - 9)  +
						 '","txtClcaPlanValue":"'+planed+'" },';
			}
			else{
				
			
			}
		}
		
	}
	//alert(' bg -' + backgroundcolor);
	jsonData  +=']';
	  //alert("jsonData   "+jsonData);
	if(jsonData.trim().length<=0 || jsonData.trim().length<=2){
		closeActualDialog();
		alert('Select Activity');
	 	return false;
	}
	var saveData = "clitData=" + jsonData ;
	  //alert("&filterString "+saveData );
	processAjaxCalls("jhClitcalendar_save.jhcal?",saveData ,"frmSuccess","frmError");
	//jQuery( "#actualval" ).dialog("close");
	
});
function closeActualDialog(){
	jQuery( "#actualval" ).dialog("close");
}
function frmSuccess(result){
	if(result.exception == true)
		alert(result.messages);
	else
		{
		//alert("sucess"+Object.keys(result));
		alert(result.successData.msg);
		/*alert("sdf"+Object.keys(result));
		alert("return data "+result.tpmException);*/

	jQuery( "#actualval" ).dialog("close");
	jQuery('#list').trigger("reloadGrid");
		}
}
function frmError(){
alert("fail");
}
function list_keypress(keycode,iRow,iCol)
{
	var celval =jQuery('#celVal_'+iRow+'_'+iCol).html(); 
	if(keycode==13)
	{
		 if(celval == null )
		 	 btnEnableDisable(false); /*function for enable / disable link buttons*/
	     else
	     	btnEnableDisable(true);/*function for enable / disable link buttons*/;
	}
	 
}
function validateFilterSelection(filterString){
	var dateTime = getServerDateTime();
	var frmmnth = dateTime.getMonth();
	var currmnth = getMonthStringFromInt(frmmnth);
	
	var machineID = getFilterValue(filterString,"cmbMchid");
	 
	jQuery('#selMachId').val(machineID);
	currmnth+="-"+dateTime.getFullYear();
	var mchId= jQuery('#jhsshdnKeyid').val();
	var frmMOnth =jQuery('#jhsshdnfltrstring').val();
	var selmnth = jQuery('#fromDate').val();
	//alert("sel   "+jQuery('#jhsshdnfltrstring').val());
	jQuery.cookie("filterString",filterString);
	if( filterString.length != 0)
	{
		//alert(filterString);
		 if( ! checkFilterValueExist(filterString, "cmbMchid") && mchId == "")
		{
			alert("Select Equipment");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtFromMonth") && frmMOnth == "")
		{
			alert("Enter  Month");
			return false;
		}
		/*else if(! checkFilterValueExist(filterString, "dtFromMonth") != currmnth){
			alert("Enter current month");
			return false;
			}*/
			
	}	
	return  true;
}

        </script>
<div id= "wrapperRpt" >
<input type="hidden" name="hdnFromStandard" id="hdnFromStandard"  value="${requestScope.keyId}" />        
        <form name="frmjhclitCal" id="frmjhclitCal" method="post">
         <input type="hidden" name="exporthtml" id="exporthtml" />
        </form>
      
        <div style="margin-left:50px;color:blue;font-weight:bold;vertical-align:top;">
          <table><tr style=" height : 30px;"><td style="font-size: 12px;"><span class="jh_Calen_planed"  style="padding-top:2px;">
          </span></td><td>Planned</td><td style="font-size: 12px;">
          <span class="jh_Calen_actual"  >
          <span style="margin-left:5px;margin-top:15px;font-size:12;">&#10003;</span>
          </span></td><td>Actual</td>
          <td style=" width : 343px;">
           
          	<div id='linkBtns'>
          	 	<c:if test="${(sessionScope.ABN) eq 101 }">
          	 	 
          			<span style="margin-left:2%;"> <input id="abnormalityLink" type="button" class= "easyui-button" style="height:21px;" value="Abnormality " disabled="disabled"/></span>
          		</c:if>
          		<c:if test="${(sessionScope.MSR) eq 102}">
          			<span style="margin-left:2%;"> <input id="msrLink"type="button" class= "easyui-button" style="height:21px;" value="MSR " disabled="disabled"/></span>
          		</c:if>
          		<c:if test="${(sessionScope.ACTPLN)eq 103 }">
          			<span style="margin-left:2%;"> <input id="actionPlanLink" type="button" class= "easyui-button" style="height:21px;" value="Action Plan " disabled="disabled"/></span>
          		</c:if>
          	</div>
          </td>
          </tr></table>
        </div>
        
        	<!--<div style="margin-left:50px;margin-top:10px;color:blue;font-weight:bold;vertical-align:top;"><img src='images/planned.png'/>  PLANNED
        	<span id="actualImg" style="padding-left:20px;">
        	<img src='images/Actual_planned.PNG'/>  ACTUAL</div>
   			</span>
   			--><table id="list" border="1" rules="all" ></table>
			<div id="pager"></div>
	
		<input type="hidden" id="position" name="position" />    
		<div id="actualval" style="background-image: -moz-radial-gradient(left bottom , circle farthest-side, #FFFFFF 0%, #A7B6F2 100%);display:none">
		
		<div style="margin-top:10px;">
		<label style="padding-right:5px">Actual</label>
		<input type="text" id="txtactual" name="txtactual" class="easyui-text"/>
		
		</div>
		<div style="margin-top:10px;margin-left:25px;">
		<input type="button" value="Save" class="easyui-button" id="save_actual"/>
		<input type="button" value="Cancel" class="easyui-button " id="close_actual"/>
		</div>
		</div>
		</div>
		<input type="hidden" id="jhsshdnKeyid" value="${requestScope.keyId}" />
		<input type="hidden" id="selRefDocId" value="" />
		<input type="hidden" id="selMachId" value="" />
		<input type="hidden" id="hdncelvalue" value="" />
		<input type="hidden" id="jhsshdnfltrstring" value="${requestScope.fltrstring}" />
		