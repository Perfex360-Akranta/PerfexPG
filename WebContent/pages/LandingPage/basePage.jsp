
 <script type="text/javascript" src="js/masterplan.js"></script> 
<style type="text/css">
.btnPadding{
	padding:5px;
}
.tblPadding{
	padding:5px;
	font-size:9px;
	width:12%;
}
.lnkBtn {
/* 	background-color:#315F97;
 background-image:url(images/accordian.png); */
	border:1px solid #c1c1c1;
	display:inline-block;
	color:#000;
	font-family:Arial;
	font-size:10px;
	font-size:10px\9;
	font-weight:bold;
	height:25px;
	line-height:25px;
	line-height:20px\9;
	width:131px;
	text-decoration:none;
	text-align:center;
	cursor: pointer;
}
.lnkBtn:hover {
/* 	background-color:#315F97;  
 background-image:url(images/accordian.png); */
 cursor: pointer;
	border:2px solid #FFF;
	display:inline-block;
	color:#000;
	font-family:Arial;
	font-size:11px;
	font-weight:bold;
	height:25px;
	line-height:25px;
	line-height:20px\9;
	width:131px;
	text-decoration:none;
	text-align:center;
	}

	/**Accordian*/
 .accordion-header >.panel-title{
 font-size:11px;
 font-weight:normal;

 }
.accordion-header{
 height:21px;
 background-image:url(images/accordian.png);
 }
 .accordion-body{
 background-color:#D8D7D7;
 }
 a{
 cursor:pointer;
 }
.tblPadding:hover{
 background-color: #fff;
 }
</style>

<script>
	jQuery(document).ready(function(){ 
		//alert("Welcome");
		initializeForm("frmEmpPage");
		//alert("Welcome1");
	    if(jQuery("#actionTools").length > 0){
		jQuery('#actionTools').accordion({
	        animate:true
	        });
		}
	   jQuery("#btnFieldObservation").hide();
	   jQuery("#btnQCLogging").hide();
		var shD = jQuery("#hdnDashBoardDisplay").val();
		if( shD == "false" || shD == false )
		{
			jQuery('#subDiv').css("height","458px");
			jQuery('#subDiv').css("max-height","458px");
			jQuery('#subDiv').css("width","100%");
			jQuery("#dashlink").show();
		}
		 if(shD == "true" || shD == true){
		  jQuery("#dashlink").hide();
		}
		//LoadForm("graphDiv","","test.charts?url="+ escape(graphurl),"", "loadGraphPage_successCallBack");
		//.dashboard-cell
		setTimeout(function(){ 
			var pillarid= jQuery('#hdnPillarId').val();
			// alert("pillarid 1 :"+pillarid);
			 processAjaxCalls("getPillarMenu.base?",'pillar='+pillarid,'pillarMenu_OnSuccess','pillarMenu_OnError');
			 //alert("pillarid 2 :"+pillarid);
			 fillDashboard(pillarid);
		 	
		},500);
	});
	function OpenLevel(level){
		var  pillarId;
		
		if("JHlevel" == level){
			jQuery('#divDashbrd').show();
			jQuery('#masterplanGrd').hide();
			pillarId = 'JHL';
			if( !jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('expand','west');
				 jQuery('#subDiv').css('width','911');
				 jQuery('#subDiv').css('max-height','397');
			} 
		}
		else if("DMTlevel" == level){
			jQuery('#divDashbrd').show();
			jQuery('#masterplanGrd').hide();
			pillarId = 'DMT';
			if( !jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('expand','west');
				 jQuery('#subDiv').css('width','911');
				 jQuery('#subDiv').css('max-height','397');
			}
		}
		else if("Champion" == level){
			if( jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('collapse','west');
				 jQuery('#subDiv').css('width','1186');
				 jQuery('#subDiv').css('max-height','397');
			}
			//LoadForm("DivmasterForm","preloadDIVid1","MstPlanActual_GEN.conf?q=2&factId=FCT0000011&sectionId=LIN0000025&cellId=CEL0000065&indFlag=Y&showGrid=Y","dispErr","","pcsLossDivData_errorCallBack");
			
			jQuery('#divDashbrd').hide();
			var dataStr ="?q=2&cmbCellid=CEL0000065&dtFromMonth=&dtToMonth=&pillar=GEN";
			if(jQuery('#masterplanGrd').html().length<=62)
				viewMasterPlanGrid("mpGrd",dataStr);
			else
				jQuery('#masterplanGrd').show();
				
		}
		//alert("pillarid 3 :"+pillarid);
		fillDashboard(pillarId);
	}
	function fillDashboard(pillarId){
		//alert("pillarid:"+pillarid);
		 if(pillarId == "EHS")
			 pillarId = "SHE";
		 else if(pillarId == "OTPM")
			 pillarId = "JH";
		LoadForm("graphDiv","","dashboard_input.dashboard?pillar="+pillarId+"&fromPage=landing","","graph_successCallBack");
	}
	function pillarMenu_OnSuccess(result){
	//alert(Object.keys(result))
		// MNUM_LOADFORMARGUMENT,MNUM_SIMILARCOLUMN,MNUM_TABLENAME,MNUM_MENUCAPTION,MNUM_ISPARENT,MNUM_MENUNAME
		
		var tableData ='<table style=" " id="tblLnkMenu">';
 		//alert("length  "+result.length);
		 for(var i=0;i<result.length;i++){  
			//tblLnkMenu
			//,result.relatedfilter,result.isfilterneed,result.formheader,result.ismaster,result.menuname
			//navigatForm('qLink_'+i+','+url+')
			var url = result[i][0];
			var id = 'qLink_'+i ;
			var ismaster = "";//result[i][5];
			var menuname = result[i][1];
			tableData += '<tr>';
			tableData +='<td style="" class="tblPadding">';
			tableData +=	'<a onclick=navigatForm("'+id+'","'+url+'");';
			tableData +=	'loadforargument='+url+'  relatedfilter=""';
			tableData +=	'formheader= "'+menuname+'" ismaster="" menuname="'+menuname+'"';
			tableData +=	'class="clickThe" id= qLink_'+i+'>'+menuname+'</a>'; 
			tableData += '</td > </tr>';
		}
		tableData += '</table>';
		  //alert(tableData);
		 jQuery('#divtablemenu').html(tableData);
		 //alert( jQuery('#divtablemenu').html());
	}
	function graph_successCallBack(){
		//height:100px;max-height:400px;max-width:800;width:800
		setTimeout(function(){
			jQuery('.dashboard-cell').css('max-height','330px');
			jQuery('.dashboard-cell').css('max-width','390px\9');	
		},1150);
	}
	function navigatForm(id,url){
		var factId = jQuery("#frmEmpPage input[id='factory']").val();
		var sectId = jQuery("#frmEmpPage input[id='section']").val();
		var cellId = jQuery("#frmEmpPage input[id='cell']").val();
		var machId = jQuery("#frmEmpPage input[id='machine']").val();
		var flid = jQuery("#frmEmpPage input[id='flid']").val();
		var dataStr = "&mchId="+machId+"&cellId="+cellId+"&sectId="+sectId+"&mode=create"+"&flid="+flid;
		var actionpart = jQuery('#'+id).attr('loadforArgument');
		//alert(url);
		if("qLink_15"==id)
			jQuery('#'+id).attr('loadforArgument',url);
		else
			jQuery('#'+id).attr('loadforArgument',url+"?"+dataStr);
		 triggerPage(id);
	}
	function select(){
		$('#actionTools').accordion('select','Title3');
	}
	var idx = 1;
	function add(){
		$('#actionTools').accordion('add',{
			title:'New Title'+idx,
			content:'New Content'+idx
		});
		idx++;
	}
	function remove(){
		var pp = $('#actionTools').accordion('getSelected');
		if (pp){
			var title = pp.panel('options').title;
			$('#actionTools').accordion('remove',title);
		}
	}

</script>
<!-- <form id="frmDashboard" name ="frmDashboard" > -->
<div class="sub-header" id="dashlink" style="position:relative;width:96%;top:-30px;color:#fff;background-image:url(images/accordian/btnImage.png) ">
					Dashboard
					<div style="position:absolute;top:0;left:10%;">
						<span><input type="button" value="JH" class="lnkBtn" title="JH"  onclick="fillDashboard('JH');" style="width:30;height:21;background-color:#D8D7D7;border:inset 2px #000;"/></span>
						<span><input type="button" value="KK" class="lnkBtn" title="KK" onclick="fillDashboard('KK');"   style="width:30;height:21;background-color:#F9D50A;border:inset 2px #000;"/></span>
						<span><input type="button" value="QM" class="lnkBtn" title="QM"  onclick="fillDashboard('QM');"   style="width:30;height:21;background-color: #D8D7D7;border:inset 2px #000;"/></span>
						<span><input type="button" value="PM" class="lnkBtn" title="PM"    onclick="fillDashboard('OTPM');"   style="width:30;height:21;background-color:#F9D50A;border:inset 2px #000;"/></span>
						
						<span><input type="button" value="EHS" class="lnkBtn" title="EHS"  onclick="fillDashboard('EHS');"   style="width:30;height:21;background-color: #D8D7D7;border:inset 2px #000;"/></span>
						<span><input type="button" value="EM" class="lnkBtn" title="EM" onclick="fillDashboard('EM');"   style="width:30;height:21;background-color:#F9D50A;border:inset 2px #000;"/></span>
						<span><input type="button" value="ET" class="lnkBtn" title="ET"  onclick="fillDashboard('ET');"   style="width:30;height:21;background-color:#D8D7D7;border:inset 2px #000;"/></span>
						<span><input type="button" value="OTPM" class="lnkBtn" title="OTPM"  onclick="fillDashboard('JH');" style="width:38;height:21;background-color:#D8D7D7;border:inset 2px #000;"/></span>
						<span style="margin-left:60px;display:none" >
							<span><input type="button" value="JH Level" class="lnkBtn" title="JH Level"  onclick="OpenLevel('JHlevel');"   style="width:55px; height:21;background-color:#89A54E;border:inset 2px #000;"/></span>
							<span><input type="button" value="DMT Level" class="lnkBtn" title="DMT Level"  onclick="OpenLevel('DMTlevel');"   style="width:65px; height:21;background-color:#89A54E;border:inset 2px #000;"/></span>
							<span><input type="button" value="Champion" class="lnkBtn" title="Champion"  onclick="OpenLevel('Champion');"   style=" width:65px;height:21;background-color:#89A54E;border:inset 2px #000;"/></span>
						</span>
					</div>
					<div style="position:absolute;top:0;right:0;display: none;">
						<span><input type="button" value="D" class="lnkBtn" title="Daily" style="width:18;height:21;background-color: #067612;"/></span>
						<span><input type="button" value="W" class="lnkBtn" title="Weekly"style="width:18;height:21;background-color:#D00C57;"/></span>
						<span><input type="button" value="F" class="lnkBtn" title="Fortnightly" style="width:18;height:21;background-color:#F7D92F;"/></span>
						<span><input type="button" value="M" class="lnkBtn" title="Monthly"   style="width:18;height:21;background-color:#F75B2F;"/></span>
					</div>
					</div>
<div id="divDashbrd" style="margin-left:1%;">
<table style="width:1120;width:1120;max-width:1120;max-width:1120; ">
	<tr>
		 
		
		<td width=82% valign="top"><!-- Right Pane -->
			
			<div><!-- Employee scheduled Report & graph -->
				<div id="empEqpGraph" style="width:92%;max-height:40%;height:50%;max-width:93%;">
					
					<div id="graphDiv" style="width: 108%;max-width: 107%; height: 50%;max-height:40%; overflow: auto;position:relative; "> </div>
				</div>
				
			</div>
		</td>
		<td valign='top' style=" ">
		<c:if test="${requestScope.dashBoardDisplay}">
		    <div id="actionTools" class="easyui-accordion" style="width:160px;height:300px;margin-top:5px;display: none;">
		    <div title="Action/Tools" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;height:42px;font-size:12px;">
		     <div id="divtablemenu">
		     </div>
		     
		    </div>
		    <div title="Quick Links" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;height:22px;">
		     <table style=" ">
				<tr>
					<td style="" class="tblPadding">
						<a onclick="navigatForm('qLink_10','ActionPlan_input.api?grid=false' );" style="" loadforargument="ActionPlan_input.api?grid=false" relatedfilter="" isfilterneed="N" formheader="Action Plan" ismaster="N" menuname="ActionPlan_input.api?grid=false" class="clickThe" id="qLink_10">Action Plan</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
						<a onclick="navigatForm('qLink_11','AbnSmryEmpwiseKaizen_input.abnSmrRpt');" style="" loadforargument="AbnSmryEmpwiseKaizen_input.abnSmrRpt" relatedfilter="WO Related" isfilterneed="" formheader="Number of Kaizen" ismaster="N" menuname="Number of Kaizen" class="clickThe" id="qLink_11">No of Kaizen,OPl,MPSheet,Abn</a> 
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_12','MeetingMin_input.mom');" style="" loadforargument="MeetingMin_input.mom" relatedfilter=" " isfilterneed="" formheader="Minutes of Meeting" ismaster="N" menuname="MeetingMin_input.mom" class="clickThe" id="qLink_12">Minutes of Meeting</a>
					</td >
				</tr>
				 <tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_13','Achievements_input.Achievements');" style="" loadforargument="Achievements_input.Achievements" relatedfilter=" " isfilterneed="" formheader="Achievements" ismaster="N" menuname="Achievements_input.Achievements" class="clickThe" id="qLink_13">Achievements</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_14','modify_input.opl');" style="" loadforargument="modify_input.opl" relatedfilter=" " isfilterneed="" formheader="OPL" ismaster="N" menuname="modify_input.opl" class="clickThe" id="qLink_14">OPL</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_15','KaizenModify_input.kaizen');" style="" loadforargument="KaizenModify_input.kaizen" relatedfilter=" " isfilterneed="" formheader="Improvement Project " ismaster="N" menuname="KaizenModify_input.kaizen" class="clickThe" id="qLink_15">Improvement Project</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_15','MpsSheetModify_input.mps');" style="" loadforargument="MpsSheetModify_input.mps" relatedfilter=" " isfilterneed="" formheader="MP Sheet" ismaster="N" menuname="MpsSheetModify_input.mps" class="clickThe" id="qLink_15">MP Sheet</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_15','jhAuditSheetGrid_input.jhAuditItc?TYPE=JH');" style="" loadforargument="jhAuditSheetGrid_input.jhAuditItc?TYPE=JH" relatedfilter=" " isfilterneed="" formheader="JH Audit" ismaster="N" menuname="jhAuditSheetGrid_input.jhAuditItc?TYPE=JH" class="clickThe" id="qLink_15">JH Audit</a>
					</td >
				</tr>
				<tr>
					<td style="" class="tblPadding">
<!-- 										CLTI Entry -->
						<a onclick="navigatForm('qLink_15','dmtMultiLevel_input.jhAuditItc');" style="" loadforargument="dmtMultiLevel_input.jhAuditItc" relatedfilter=" " isfilterneed="" formheader="DMT Audit" ismaster="N" menuname="dmtMultiLevel_input.jhAuditItc" class="clickThe" id="qLink_15">DMT Audit</a>
					</td >
				</tr>
			</table>
		    </div>
		   <!--  <div title="Title3" style="height:22px;">
		    content3
		    </div> -->
		    </div>
			</c:if>
		</td>
	</tr>

</table>
</div>
<div id="masterplanGrd">
	<table id="mpGrd" ><tr><td></td></tr></table>
<input type="hidden" id="hdnDashBoardDisplay" value="${requestScope.dashBoardDisplay}" >
</div>
<!-- </form> -->
 