<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">		
<script type="text/javascript">

	jQuery(document).ready(function() 
			{

		
		initialiseForm('frmImrControlchart');
		jQuery('#submitForm').val('frmImrControlchart');
		var flid = jQuery("#frmImrControlchart input[id='flid']").val();
		
		fillComboBox('frmImrControlchart','cmbcharactericid', 'combo_characteristic.IMR');
		//fillComboBox('frmImrControlchart','cmbproductid', 'productAll.commonFilter?type=IMR');
		fillComboBox('frmImrControlchart','cmbproductid', "comboGradeSpec.commonFilter?q&flid="+flid);
		
		
		
		readOnlyFields("txtupperlimit");
		readOnlyFields("txtlowerlimit");
		jQuery("#grphDiv").hide();
		var FrmView=jQuery("#hdnFrmView").val();
		if(FrmView.trim()=="Y"){
			jQuery("#divupload").hide();
			jQuery("#divbrowse").hide();
			jQuery("#formula").css('margin-left','10px');
			setFieldValue("cmbproductid","PRD0000001");
			setFieldValue("cmbcharactericid","CHRM000001");
		}else if(FrmView.trim()=="N"){
			jQuery("#divupload").show();
			jQuery("#divbrowse").show();
		}
		var factId = jQuery("#frmImrControlchart input[id='factory']").val();
	    var sectionId = jQuery("#frmImrControlchart input[id='section']").val();
	    var cellId = jQuery("#frmImrControlchart input[id='cell']").val();
	    var machId = jQuery("#frmImrControlchart input[id='machine']").val();
	    
	    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
		loadFunctionalLocation("imrfunLocation","functionalLoc.IMR","imrfunLocationValues","frmImrControlchart",dataStr);
		viewGrid("","");
		jQuery("#btnUploadExcel").click(function(){	
			var flid =jQuery("#frmImrControlchart input[id='flid']").val();
			var charid= getFieldValue('cmbcharactericid');
			var productid =getFieldValue('cmbproductid');
			var fileUp=jQuery(".qq-upload-list").html();
			 if(!(charid.length>0) && !(productid.length>0) && !(flid.length>0)){
            	alert("Select Functional Location , Characteristic and Product ");
            	return false;
			 }
			 else if(!(productid.length>0)){
            	alert("Select Product");
            	return false;
			 }
			 else if(!(charid.length>0)){
            	alert("Select Characteristic ");
            	return false;
			 }else if(!(flid.length>0)){
	            	alert("Select Functional Location ");
	            	return false;
			 }else if(!(fileUp.length>0)){
            	alert("Select File to Upload ");
            	return false;
			 }
			if(charid.length>0 && productid.length>0 && fileUp.length>0)
				var gridVal=jQuery("#hdnFrmGrid").val();
				saveForm("frmImrControlchart","file_save.IMR?grid="+gridVal+"&flid="+ flid+"&charid="+charid+"&productid="+productid);
			});
		jQuery("#btnClr").click(function( ){
			jQuery(".qq-upload-list").html('');
			
		});
		jQuery( "#formulaeButton" ).click(function() {
			jQuery("#lgnd-panel").css('width','250px');
			jQuery("#lgnd-panel").css('padding','1%');
			jQuery("#lgnd-panel").css('z-index','2');
			jQuery("#lgnd-panel").css('top','30px');
			jQuery("#lgnd-panel").slideToggle(300);
		});
		jQuery(document).keydown(function(e) {
		    if (e.keyCode == 27) {
		    	jQuery("#lgnd-panel").hide(0);
		    }    
		    jQuery('#lgnd-panel').focusout(function() { 
		 	});
		});

		var uploadedFile = jQuery('#hdnUploadedFile').val();
		if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
		{
			 
		}
		
		var uploader = new qq.FileUploader({
		    // pass the dom node (ex. $(selector)[0] for jQuery users)
		    element:   document.getElementById("fileUpload"),
		    name:"Upload",
		    uploadLabelName:"Browse",
		    // path to server-side upload script
		    action: 'file_upload.IMR',
			// additional data to send, name-value pairs
			params: {},
			numFiles:1,
			// validation
			// ex. ['jpg', 'jpeg', 'png', 'gif'] or []
			allowedExtensions: [],
			// each file size limit in bytes
			// this option isn't supported in all browsers
			sizeLimit: 65242880, // max size
			minSizeLimit: 1, // min size
			// set to true to output server response to console
			debug: false,
			// events
			// you can return false to abort submit
			onSubmit: function(id, fileName){
				if( jQuery(".qq-upload-list").length>0){
					jQuery(".qq-upload-list").html('');
				}
			},
			onProgress: function(id, fileName, loaded, total){},
			onComplete: function(id, fileName, responseJSON){
				jQuery(".qq-upload-failed-text").hide();},
			onCancel: function(id, fileName){},
			messages: {
				 sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
		         minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
		         emptyError: "{file} is empty, please select some other file."
			      
			    //error messages, see qq.FileUploaderBasic for content
			},
			showMessage: function(messages){
				alert(messages);
				}
			});
		});
	
	function viewGrid(url,filterString){
		
		if( validateFilterSelection(filterString))
		{
			var flid = jQuery("#frmImrControlchart input[id='flid']").val();
			var prodId=getFieldValue('cmbproductid');
			var charId=getFieldValue('cmbcharactericid');
			filterString+="&flid="+flid+"&prodId="+prodId+"&charId="+charId;
			var filterStr=filterString;
			jQuery("#hdnFilterStr").val(filterStr);
			if(flid.length>0 && prodId.length>0 &&  charId.length>0)
				filterString="?q=2"+filterString;
			else
				filterString=" ";
			processGridnew("I_MRControlForm_input.IMR",filterString,"imrcontrolGrid","imrpager","","ImrdocDoubleClick","","gridLoadComplete");
			if(charId.length>0 && prodId.length>0){
	          	processAjaxCalls("comboselect_fill.IMR?&flid="+flid+"&charid="+charId+"&prodid="+prodId, "",'fillSuccessCallBack','');
			}
			return true;
		}
		return false;
	}
	function validateFilterSelection(filterString)
	{
		var fromDate=getFilterValue(filterString,"dtFromDate");
		var toDate=getFilterValue(filterString,"dtToDate");
		var flid=getFilterValue(filterString,"flid");
		if(fromDate.length>0){
			var compVal= compareFromToDate(fromDate,toDate,"7");
			return compVal;
		}
		//loadFunctionalLocation("imrfunLocation","functionalLoc.IMR","imrfunLocationValues","frmImrControlchart","&flid="+flid);
		return true;
	}
	function gridLoadComplete(){
		var row= jQuery("#imrcontrolGrid").jqGrid("getDataIDs");
		var filterStr=jQuery("#hdnFilterStr").val();
		if(row.length>0){
			jQuery("#grphDiv").show();
			processAjaxCalls("I_MRControlForm_chart1.IMR?grid=false"+filterStr,"","IMrgraph_successCallBack");
		}else
			jQuery("#grphDiv").hide();
	}
	function frmImrControlchart_FuntLocHierarchy_SuccessCallBack(result){
		viewGrid("","");
		setFunctionalLocWidth("frmImrControlchart","650px");
	}
	function IMrgraph_successCallBack(result){
		var chartData = result.aftchartData.chartData;	
		jQuery('#divgraphIMRControl2').show();
		chartData.height ="275";
		chartData.width ="750";
		chartData.legend="";
		drawChart(chartData,'divgraphIMRControl2','Y','N');
		jQuery("#dashTool").css("width","745");
		var filterStr=jQuery("#hdnFilterStr").val();
		processAjaxCalls("I_MRControl_chart.IMR?grid=false"+filterStr,"","IMRgraph1_successCallBack");
	}
	function IMRgraph1_successCallBack(result){
		var chartData =result.aftchartData.chartData;	
		jQuery('#divgraphIMRControl1').show();
		chartData.height ="275";
		chartData.width ="750";
		chartData.legend="";
		drawChart(chartData,'divgraphIMRControl1','Y','N');
		jQuery("#dashTool").css("width","745");
	}
	function IMrgraphY_successCallBack(result){
		var chartData = result.aftchartData.chartData;
		jQuery('#divgraphIMRControl2').show();	
//		chartData.height ="275";
		chartData.width ="750";
		chartData.legend="";
		drawChart(chartData,'divgraphIMRControl2','Y','N');
		jQuery("#dashTool").css("width","745");
		var filterStr=jQuery("#hdnFilterStr").val();
		processAjaxCalls("I_MRControl_chart.IMR?Spec=Y"+filterStr,"","IMRgraph1Y_successCallBack");
	}
	function IMRgraph1Y_successCallBack(result){
		var chartData = result.aftchartData.chartData;	
		jQuery('#divgraphIMRControl1').show();
		chartData.height ="275";
		chartData.width ="750";
		chartData.legend="";
		drawChart(chartData,'divgraphIMRControl1','Y','N');
		jQuery("#dashTool").css("width","745");
	}
	
	function frmImrControlchart_successsCallback(result)
	{
		jQuery(".qq-upload-list").html('');
		var grid=result.grid;
		if(grid=="false")
			loadFunctionalLocation("imrfunLocation","functionalLoc.IMR","imrfunLocationValues","frmImrControlchart","&flid=");
		else if (grid=="true")
		{
			var flid=result.flid;
			loadFunctionalLocation("imrfunLocation","functionalLoc.IMR","imrfunLocationValues","frmImrControlchart","&flid="+flid);
		}
		viewGrid("","");
	}
	 function frmImrControlchartcmbcharactericid_onSelect(charid)
	 {
		var prodid=getFieldValue("cmbproductid");
		if(prodid.length<=0)
		 	reloadCombo('frmImrControlchart','cmbproductid', 'productAll.commonFilter?type=IMR&charId='+charid.id);
		 getids(charid.id,prodid);
		 viewGrid("","");
	 }
	 function frmImrControlchartcmbproductid_onSelect(prodid)
	 {
		 var charid=getFieldValue("cmbcharactericid");
		 if(charid.length<=0)
		 	reloadCombo('frmImrControlchart','cmbcharactericid', 'combo_characteristic.IMR?prodId='+prodid.id);
		 getids(charid,prodid.id);
		 viewGrid("","");
	 }
	function getids(charid,prodid)
	{
          if(charid.length>0 && prodid.length>0)
          	processAjaxCalls("comboselect_fill.IMR?&charid="+charid+"&prodid="+prodid, "",'fillSuccessCallBack','');
          
	}
	function fillSuccessCallBack(result){
		setFieldValue("txtupperlimit","0");
		setFieldValue("txtlowerlimit","0");
		setFieldValue("txtupperlimit",result[1][0]);
		setFieldValue("txtlowerlimit",result[1][1]);
	}
	
	function chkOnClick(){
		var isChecked = jQuery("#chkGraph").is(':checked');
		var filterStr=jQuery("#hdnFilterStr").val();
		if(isChecked==true){
			processAjaxCalls("I_MRControlForm_chart1.IMR?Spec=Y"+filterStr,"","IMrgraphY_successCallBack");
		}else
		{
			processAjaxCalls("I_MRControlForm_chart1.IMR?"+filterStr,"","IMrgraph_successCallBack");
			
		}
	}
	
</script>

<form id="frmImrControlchart">
	<div style="width:100%;position:relative">
		<table style="margin-left:10px;position:relative;width:100%">
			<tr>
				<td colspan="2" width="25%">
					<div>
						<div>
							<input type="hidden" id="section" name="section"  value=""></input>
							<input type="hidden" id="cell"    name="cell"     value=""></input>
							<input type="hidden" id="machine" name="machine"  value=""></input>	
							<input type="hidden" id="flid" name="cmbImrcFlid" value="${requestScope.flid}"/>								
							<div id="imrfunLocation" ></div>	
						</div>	
					</div>
					
				</td>
				<td id="divbrowse" width="12%">
					<div id="" style="margin-left:-7px;margin-top:20px">
						<div id="fileUpload" style="width:200px;"></div>
					</div>	
				</td>
				<td width="38%">
					<div  id="formula"  style="position:relative;">
						<div style="margin-left:20px">
							<input type="button" class="easyui-button" id="formulaeButton" value="Formulae"/>
						</div>
						<div>
							<div id="lgnd-panel" style="position:absolute;left:1%;">
								<ul>
									<li>MR = Sample Reading (n) - Sample Reading (n-1)</li>
									<li>LCL MR = 0 </li>
									<li>MR Bar = Average (MR)</li>
									<li>UCL MR = 3.267*MR Bar</li>
									<li>LCL I = I Bar - 2.66*MR Bar</li>
									<li>I Bar =  Average (Sample Reading)</li>
									<li>UCL I = I Bar+2.66*MR Bar</li>
								</ul>
						  	 </div>
						</div>
					</div>
				</td>
			</tr>
	        <tr>
	        	<td colspan="2">
	        		<table>
	        			<tr>
				            <td width="20%">
				            	<div>
					            	<div>
					                  <label class="mandatory-lbl">Product</label>
					               </div>
					               <div>
					                    <input id="cmbproductid"  class="easyui-combobox"  value="${requestScope.prodId}" size="15" name="cmbproductid"  maxlength="50"    style=" width :170px;">
					               </div>
				               </div>
				            </td>
				            <td width="20%">
				            	<div style="margin-left:20px;">
					            	<div>
					            		<label class="mandatory-lbl">KPIV/KPOV/Characteristics</label>
					           		 </div>
					           		 <div>
					                 	 <input class="easyui-combobox" id="cmbcharactericid" name="cmbcharactericid" maxlength="30" style="width: 170px; height: 21px;" value="${requestScope.charId}"/>         		
					            	</div>
				            	</div>
				            </td>
				            <td width="17%">
					            <div style="margin-left:20px;">
					               <div>
					                  <label>Upper Limit</label>
					               </div>
					               <div>
					                    <input id="txtupperlimit" type="text" class="easyui-text"  value="0" size="15" name="txtupperlimit"  maxlength="50"    style=" width :140px;">
					               </div>
				               </div>
				            </td>
				             <td width="15%">
					            <div style="margin-left:20px;">
					               <div>
					                  <label>Lower Limit</label>
					               </div>
					               <div>
					                    <input id="txtlowerlimit" type="text" class="easyui-text"  value="0" size="15" name="txtlowerlimit"  maxlength="50"    style=" width :140px;">
					               </div>
								</div>
				            </td>
						</tr>
					</table>
				</td>
				<td width="">
					<div id="divupload" style="margin-left:20px;">	
				  		 <span>
				  		 	<input id="btnUploadExcel" class="easyui-button" name="btnUploadExcel"  type="button" value="Upload" style="height:20px;"/>
			  		 	</span>
		  				<span style ="padding-left:10px;top:5px">	
		   					<input id="btnClr" class="easyui-button"  type="button" value="Clear" style="height:20px;"/>
					   	</span>
				   </div>
				</td>
	        </tr>
			<tr>
				<td valign="top" width="4%">
					<div>
						<table id='imrcontrolGrid'>
							<tr>
								<td></td>
							</tr>
						</table>
						<div id='imrpager'></div>
					</div>
				</td>
				<td colspan="3">
					<div id="grphDiv" style="margin-top: 0px" >
						<div>			
							 <input type="checkbox" id="chkGraph" name="chkGraph" value="Y" onclick="chkOnClick();" />
							 <span style="padding-left:1%;" >
							 	<label>Specification Limit</label>
							 </span>
						</div>
				    	<div  class="dashboard-row" style="height:310px;max-height:310px" >
				    	<div  class="dashboard-cell" style="margin-left:3%;height:310px;max-height:310px">
				    	<div class="dashboard-view" draggable="true">
				    	<div id="divgraphIMRControl1" class="dashboard-view" style="position:relative; margin-left:3%;height:310px;max-height:310px">
				    	
				    	</div>
				    	
				    	</div>
				    	</div>
					    </div>
				    	<div  class="dashboard-row" style="height:310px;margin-top:30px;">
				    	<div class="dashboard-cell" style="margin-left:3%;height:310px;max-height:310px">
				    	<div class="dashboard-view" draggable="true">
				    	<div id="divgraphIMRControl2"  style="margin-left:3%;height:310px;max-height:310px">
					    </div>
					    </div>
					    </div>
					    </div>
				    </div>
				</td>
			</tr>
		</table>	
  	</div>
	<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
	<input type="hidden" id="hdnFrmView" name="hdnFrmView" value="${requestScope.FrmView}"/>
	<input type="hidden" id="hdnFrmGrid" name="hdnFrmGrid" value="${requestScope.grid}"/>
  	
	<input type="hidden" id="hdnFilterStr" name="hdnFilterStr"/>
</form>
			