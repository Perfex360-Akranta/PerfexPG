<script>

	jQuery(document).ready(function(){//alert(4);

	     initialiseForm('frmfishchildEntry');
		 //readOnlyFields("txtFismParent");
		
		 var levelno=jQuery('#hdnlevelNo').val();
	     var OrderNo=jQuery('#hdnOrderNo').val();
	     var dispCode=jQuery('#hdndispCode').val();
	     var ParentId=jQuery('#hdnParentId').val();
	     var DtlId=jQuery('#hdnDtlid').val();
	     //alert(" DtlId :: "+DtlId);
	     var Masterid=jQuery('#hdnMasterid').val();
	     
	     if(DtlId=='FB001')  
		 {
			 disableField("frmfishchildEntry","btnaddassamelevel");
			 disableField("frmfishchildEntry","btnedit"); 
			 disableField("frmfishchildEntry","btndelete");
			 readOnlyFields("txtFismParent");
			
		 }    
		 jQuery("#btnaddaschild").click(function()
		 {

        // ------- Vignesh fixing encode url---- 15Dec2025 -----//
        
			//saveForm('frmfishchildEntry',"FishBoneChildEntry_save.fishbone?&levelno="+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&ParentId="+ParentId+"&Masterid="+Masterid+"&DtlId="+DtlId+"&detlid=detlid");	//?&frstIdn=frstIdn									
		
					// 1) make ParentId a proper string (avoid [object Object])
			if (typeof ParentId === "object" && ParentId !== null) {
			  ParentId = ParentId.value || ParentId.id || ParentId.keyid || "";
			}

			// 2) helper to encode safely
			function enc(v) {
			  return encodeURIComponent(String(v == null ? "" : v).trim());
			}

			var url =
			  "FishBoneChildEntry_save.fishbone" +
			  "?levelno=" + enc(levelno) +
			  "&OrderNo=" + enc(OrderNo) +
			  "&dispCode=" + enc(dispCode) +
			  "&ParentId=" + enc(ParentId) +
			  "&Masterid=" + enc(Masterid) +
			  "&DtlId=" + enc(DtlId) +
			  "&detlid=" + enc("detlid");   // if you really want literal "detlid"

			saveForm("frmfishchildEntry", url);

					
					
					// ------- Vignesh fixing encode url---- 15Dec2025 -----//
			 
		 });

		 jQuery("#btnaddassamelevel").click(function()
		 {
			 url = '&levelno='+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&ParentId="+ParentId+"&Masterid="+Masterid+"&detlid=Smelvl";

			 if(DtlId=='FB001')  
			 {
				 url += "&level=level";	
			 }

			 saveForm('frmfishchildEntry',"FishBoneChildEntry_save.fishbone?"+url);	//?&frstIdn=frstIdn

		 });
     // -- Commenting by Vignesh 07Mar2026 for samelevel name issue 
//           jQuery("#btnedit").click(function()
// 		 {
			 
// 		 	  enableFields("txtFismParent");
// 		 	  url = '&levelno='+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&ParentId="+ParentId+"&Masterid="+Masterid+"&DtlId="+DtlId+"&Editval=Editval";
//         	  saveForm('frmfishchildEntry',"FishBoneChildEntry_save.fishbone?"+url);
		 	 
// 		 });
     jQuery("#btnedit").click(function () {

    enableFields("txtFismParent");

    url = '&levelno=' + levelno
        + "&OrderNo="  + OrderNo
        + "&dispCode=" + dispCode
        + "&ParentId=" + ParentId
        + "&Masterid=" + Masterid
        + "&DtlId="    + DtlId
        + "&Editval=Editval"
        + "&mode=edit";   // ✅ NEW

    saveForm('frmfishchildEntry', "FishBoneChildEntry_save.fishbone?" + url);

});
   
          jQuery("#btndelete").click(function()
         {
        	  
       	  if(confirm("Do You Want To Delete All Child Record?") == true)
       	  {
       	     processAjaxCalls("FishBoneChildEntry_delete.fishbone",'&DtlId='+DtlId,'Remove_Sucesscallback','');
       	  }
        			 	
         });
          var viewmode=getFieldValue("hdnViewmode","frmWorkOrder");
          //alert("1:"+viewmode);
          if(viewmode=="View"){
          	disableForm('frmfishchildEntry');
          //	jQuery('#prdDiv').append('<div id="hdnviewprd" style="position:absolute;top:50%;left:293;margin-left:0%;width:75%;z-index:2;opacity:0.4;height:56%;"> </div>');
          	
          }
		 
	});

	function Remove_Sucesscallback(result){

      alert(result.successData.msg);
      jQuery("#fishBoneTreeComponent").jstree("refresh");
	     
	}
	/*function frmpcsEnableDisable_successsCallback(result)
	{
		if(result!=null){
		jQuery('#pcsEbleDbleGrid').clearGridData();
		jQuery('#cmbpelcCellid').combobox('clear');*/

		 // -- Commenting by Vignesh 07Mar2026 for samelevel name issue 
		 
// 	function frmfishchildEntry_successsCallback(result)
// 	{

// 		openChildNode(result.Parentid);
// 		clearField('txtFisdCause');
// 		var ParentText=jQuery('#txtFismParent').val().toUpperCase(); 
		
// 		renamingnodevalue(ParentText,result.Parentid);
		
// 	}
		
		function frmfishchildEntry_successsCallback(result) {

		    openChildNode(result.Parentid);
		    clearField('txtFisdCause');

		    var ParentText = jQuery('#txtFismParent').val().toUpperCase();

		    // ✅ check mode=edit present in url string
		    if (typeof url !== "undefined" && url.indexOf("mode=edit") !== -1) {
		        renamingnodevalue(ParentText, result.Parentid);
		    }
		}

	function renamingnodevalue(ParentText,id){
		
	    jQuery("#"+id).children("a").html(ParentText);
	    jQuery("#"+id).attr("displaycode",ParentText);

	}
	function openChildNode(parentId){
		 if(jQuery("#fishBoneTreeComponent").jstree("is_open",  jQuery('#'+parentId)) == false)
		  {
			   var isLeaf = jQuery("#fishBoneTreeComponent").jstree("is_leaf",  jQuery('#'+parentId));
				
				if(isLeaf == true)
				{
					jQuery("#fishBoneTreeComponent").jstree("load_node",  jQuery('#'+parentId));
					setTimeout(function() {jQuery("#fishBoneTreeComponent").jstree("open_node",  jQuery('#'+parentId));},1250);
				}
				else
		  			jQuery("#fishBoneTreeComponent").jstree("open_node",  jQuery('#'+parentId));
		  }
		  else
		  	refreshNode("fishBoneTreeComponent",parentId);
	}
	
</script>

	<form id="frmfishchildEntry">
	    <table style="margin-top:60px;margin-left:70px;">
	    <tr>
		    <td>
				<div style="margin-left:26%;">
					<div>
				    	<label>Parent</label>
					</div>
					<div>
					    <input class="easyui-text" id="txtFismParent" name="txtFismParent" style="width : 230px;text-transform:uppercase;"  value="${requestScope.dispCode}"></input>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
     			<div style="margin-left:26%;">
					<div>
					    <label class="mandatory-lbl">Cause</label>
					</div>
					<div>
					
<!-- <input class="easyui-text" id="txtFisdCause" name="txtFisdCause" style="width : 230px;text-transform:uppercase;"  value=""></input> -->

<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtFisdCause" name="txtFisdCause" style="resize:none;width:230px;text-transform:uppercase;" maxlength="600"></textarea>
						    
					    
					    
					</div>
					
					<div>
					<span id="err_txtFisdCause" class="tpm-errormsg"></span>
					</div>
				</div>
			</td>
		</tr>
				<tr>
			<td>
     			<div style="margin-left:26%;">
					<div>
					    <label>Remarks</label>
					</div>
					<div>
					
<input class="easyui-text" id="fisdRemarks" name="fisdRemarks" style="width : 230px;text-transform:uppercase;"  value=""></input>
					    
					    
					</div>
					
					<div>
					<span id="err_txtFisdCause" class="tpm-errormsg"></span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="display:none;" >
			  <div style="margin-left:26%;">
					<div>
						<label>Editing Cause</label>
					</div>
					<div>
						<input class="easyui-text" id="txtFisdCause" name="txtFisdCause" style="width : 230px;" value=""></input>
					</div>
              </div>			
			</td>
		</tr>
	</table>
	<table style="margin-left:70px;">
	<tr>
				<td>
				   <div style="margin-left:10%; padding-top:20px; white-space:nowrap; " >
				  	<input type="button" class="easyui-button" id="btnaddaschild" name="btnaddaschild" value="Add As Child" style="height:23px"/>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btnaddassamelevel" name="btnaddassamelevel" value="Add As Same Level" style="height:23px"/>
				  	</span>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btnedit" name="btnedit" value="Edit" style="height:23px"/>
				  	</span>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btndelete" name="btndelete" value="Delete" style="height:23px"/>
				  	</span>
				  	</div>
				</td>
				
			</tr>
	</table>
	
	<input type="hidden" id="hdnlevelNo" name="hdnlevelNo" value="${requestScope.levelNo}"/> 
	<input type="hidden" id="hdnOrderNo" name="hdnOrderNo" value="${requestScope.OrderNo}"/> 
	<input type="hidden" id="hdndispCode" name="hdndispCode" value="${requestScope.dispCode}"/> 
	<input type="hidden" id="hdnParentId" name="hdnParentId" value="${requestScope.ParentId}"/> 
	<input type="hidden" id="hdnMasterid" name="hdnMasterid" value="${requestScope.Masterid}"/> 
	<input type="hidden" id="hdnDtlid" name="hdnDtlid" value="${requestScope.DtlId}"/> 
	<!-- <input type="text" name="fisdRemarks" id="fisdRemarks"> -->
	
	
	</form>