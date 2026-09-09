<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>  


<script type="text/javascript">
jQuery.noConflict();
var generalMastTitle = "General";
var bdmTitle = "Breakdown", jhTitle = "JishuHozen",kznTitle ="KobetsuKaizen";
var pmTitle ="PlannedMaintenance",HSETitle="EHS",ETTitle="EducationAndTraining",QTMTitle="QualityMaintenance";
var admTitle ="Administration" ;
var sapTitle ="SAP" ;
var allTitle ="All" ;									

jQuery(document).ready(function(){	
				if( jQuery("#genTab").length >0)
					generalMastTitle=jQuery("#genTab").attr("title").replace(/\s/g, ""); 	
				if( jQuery("#bdmTab").length >0)
					bdmTitle=jQuery("#bdmTab").attr("title").replace(/\s/g, "");
				if( jQuery("#jhTab").length >0)
					jhTitle=jQuery("#jhTab").attr("title").replace(/\s/g, "");
				if( jQuery("#kznTab").length >0){
					kznTitle=jQuery("#kznTab").attr("title").replace(/\s/g, "");
				}	
				if( jQuery("#pmdTab").length >0)
					pmTitle=jQuery("#pmdTab").attr("title").replace(/\s/g, "");
				if( jQuery("#ehsTab").length >0)
					HSETitle=jQuery("#ehsTab").attr("title").replace(/\s/g, "");
				if( jQuery("#etTab").length >0)
					ETTitle=jQuery("#etTab").attr("title").replace(/\s/g, "");
				if( jQuery("#qtmTab").length >0)
					QTMTitle=jQuery("#qtmTab").attr("title").replace(/\s/g, "");
				if( jQuery("#admTab").length >0)
					admTitle=jQuery("#admTab").attr("title").replace(/\s/g, "");
				if( jQuery("#allTab").length >0)
					allTitle=jQuery("#allTab").attr("title").replace(/\s/g, "");

				if( jQuery("#SAPTab").length >0)
					sapTitle=jQuery("#SAPTab").attr("title").replace(/\s/g, "");
				initialiseForm('frmMasterTbl');


				var title = jQuery('#hdntitle').val();
								
				title = unescape(title);

				if( title != null && title !='' && title != undefined && title.length > 0)
				{
					jQuery("#tabTableView").tabs('select',title);
					//tblId = 'module='+title.replace(' ','').replace(' ','');
				}

				if( title == null || title == undefined || title.length <= 0 )
					title = allTitle;//generalMastTitle;
				else
					title = title.replace(/\s/g, "");//replace(/^\s+|\s+$/g,"");						


				var  tblId = 'module='+title;
				
				
				
				if( generalMastTitle ==  title )
					buildMastMenuLinkTbl(generalMastTitle , "gen_module_pager", generalMastTitle,tblId );
				else if( bdmTitle == title )
					buildMastMenuLinkTbl(bdmTitle, "pager2", bdmTitle,tblId);
				else if(jhTitle == title)
					buildMastMenuLinkTbl(jhTitle, "pager3", jhTitle,tblId);
				else if( kznTitle == title )
					buildMastMenuLinkTbl(kznTitle, "pager4", kznTitle,tblId );
				else if( pmTitle == title )						
					buildMastMenuLinkTbl(pmTitle, "pager5", pmTitle,tblId);
				else if( HSETitle == title)
					buildMastMenuLinkTbl(HSETitle, "pager6",HSETitle,tblId  );
				else if(ETTitle == title )
					buildMastMenuLinkTbl(ETTitle, "pager7", ETTitle, tblId );
				else if(admTitle == title)
					buildMastMenuLinkTbl(admTitle, "pager8",admTitle,tblId );
				else if(QTMTitle == title)
					buildMastMenuLinkTbl(QTMTitle, "pager9",QTMTitle,tblId );
				else if(sapTitle == title)
					buildMastMenuLinkTbl(sapTitle, "pager11",sapTitle,tblId );
				else {
					//if( allTitle == title ){
					buildMastMenuLinkTbl(allTitle, "pager10", allTitle,tblId);
				
				//	buildMastMenuLinkTbl(allTitle, "pager10", allTitle);
				}
				if( allTitle != title )
					buildMastMenuLinkTbl(allTitle, "pager10", allTitle);
				if( generalMastTitle !=  title )
					buildMastMenuLinkTbl(generalMastTitle , "gen_module_pager", generalMastTitle );
				if( bdmTitle != title )
					buildMastMenuLinkTbl(bdmTitle, "pager2", bdmTitle);
				if( jhTitle != title )
					buildMastMenuLinkTbl(jhTitle, "pager3",jhTitle );
				if( kznTitle != title )
					buildMastMenuLinkTbl(kznTitle, "pager4", kznTitle );
				
				if( pmTitle != title )
					buildMastMenuLinkTbl(pmTitle, "pager5",pmTitle );
				if( HSETitle != title )
					buildMastMenuLinkTbl(HSETitle, "pager6",HSETitle  );
				if( ETTitle != title )
					buildMastMenuLinkTbl(ETTitle, "pager7", ETTitle );
				if( admTitle != title )
					buildMastMenuLinkTbl(admTitle, "pager8",admTitle);
				if( QTMTitle != title )
					buildMastMenuLinkTbl(QTMTitle, "pager9",QTMTitle);
				if( sapTitle != title )
					buildMastMenuLinkTbl(sapTitle, "pager11",sapTitle);

				//reloadMasterGrid(tblId,tblId);						
			//jQuery(".tabs-title").bind("click", function(event){
			jQuery("#tabTableView").tabs({ onSelect:function(title){  
				
				var tblId = title.replace(/\s/g, "");
				if( jQuery("#"+tblId).getGridParam('reccount') <= 0 )
				{
					reloadMasterGrid(tblId,tblId);
				}
			   }	
		   });
					
								
	// jQuery("#General").setGridParam({url:'general_getData.gnms?module=General',datatype:'json'}).trigger('reloadGrid');
						
					
});
function reloadMasterGrid(tableId, module){
	tableId = tableId.replace(' ','').replace(' ','');
	module = module.replace(' ','').replace(' ','');
	
	jQuery("#"+tableId).setGridParam({url:'general_getData.gnms?module='+module,datatype:'json'}).trigger('reloadGrid');					
}
function buildMastMenuLinkTbl(tblId, pagerId,caption, data )
{
	if(jQuery("#tabTableView table[id=" + tblId +"]").length > 0 ){		
		if( data==null || data == undefined) data ="";
		else
			data = "&"+data;

		//alert(tblId);
		processGridnew("general_input.gnms?moduleCol="+caption,data,tblId,pagerId,caption,tblId+"_ondoubleclickCallback","","","",true);

/*		jQuery('#'+tblId).jqGrid('setGridParam', { ondblClickRow: function(id){ 
			alert('s '+ tblId);
			doubleClickTable(tblId, id);
		} } );
*/		
		/*jQuery("#"+tblId).jqGrid( {ondblClickRow: function(id){
			doubleClickTable(tblId, id);
		}
		});
		*/	
	}
}						
function SAP_ondoubleclickCallback(id){
	doubleClickTable(sapTitle, id,'N');
}
function General_ondoubleclickCallback(id){
	doubleClickTable(generalMastTitle, id);
}
function Breakdown_ondoubleclickCallback(id){
	doubleClickTable(bdmTitle, id);
}
function JishuHozen_ondoubleclickCallback(id){
	doubleClickTable(jhTitle, id);
}
function KobetsuKaizen_ondoubleclickCallback(id){
	doubleClickTable(kznTitle, id);
}
function PlannedMaintenance_ondoubleclickCallback(id){
	doubleClickTable(pmTitle, id);
}
function QualityMaintenance_ondoubleclickCallback(id){
	doubleClickTable(QTMTitle, id);
}
function EHS_ondoubleclickCallback(id){
	doubleClickTable(HSETitle, id);
}
function EducationAndTraining_ondoubleclickCallback(id){
	doubleClickTable(ETTitle, id);
}
function Administration_ondoubleclickCallback(id){
	doubleClickTable(admTitle, id);
}
function All_ondoubleclickCallback(id){
	doubleClickTable(allTitle, id);
}

function doubleClickTable(tableId,rowid,clickEnable){

	
	var menuName = jQuery("#"+tableId).getCell(rowid, 'menuName');
	var menuCaption = jQuery("#"+tableId).getCell(rowid, 'menucaption');
	var formTitle = menuCaption;
	var caption = menuCaption.split(" ");
	var menu = null;
	menu = caption[0];
	for (var i=1; i<caption.length; i++)
		menu += caption[i];
	if(clickEnable==undefined || clickEnable==null || clickEnable=='' || clickEnable==' ')
		clickEnable="Y";
	var isMMC = jQuery("#"+tableId).getCell(rowid, 'isMMC');	

	var loadFormArg = jQuery("#"+tableId).getCell(rowid, 'loadFormArgument');
	var dataString = 'q=2&menuCaption='+menu+'&menuName='+menuName + '&isMMC='+isMMC +'&clickEnable='+clickEnable +'&loadFormArg='+escape(loadFormArg+'?');
	var pp = jQuery('#tabTableView').tabs('getSelected');  
	var title = pp.panel('options').title;
	
	title = escape(title);
	 var persistData = null; 	
	 persistData =   {'title':title};
	if( isMMC == "M")
	    navigateToNextForm(loadFormArg,formTitle,null,persistData );	
	else		
	  	navigateToNextForm('loadmst_grid.gnms?'+dataString,formTitle,null,persistData );
}
					</script>				
					<!--	End grid	 Script				-->    
<div style="width:100%;margin-left:6%;height:100%;"  >
<form id="frmMasterTbl"  >
<div style="margin-top:0px;" tabindex="0">
<div id="masterTableConfig">
   <div class="notes">  Double Click on Row to View Data </div>
<!--	<div class="headin" style=" width : 980px; height : 17px;margin-bottom:10px;"> Double Click on Row to View Data </div>-->
	<div id="tt" style="width :97%;width : 99%\9; height : 90%;height : 400px\9;">
	<div id="tabTableView" class="easyui-tabs" fit="false" plain="true" style="height : 400px\9;" align="-20px 0 0 0 0"; tabindex="0">
	
		<%-- <c:if test="${(requestScope.showAll)}">
		<div id="allTab" title="${requestScope.titleAll}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblAll}" style="width:100%"><tr><td/></tr></table>
			<div id="pager10"></div>
		</div>
		</c:if>	 --%>
		
		<%
		    if ((Boolean) request.getAttribute("showAll")) {
		%>
		    <div id="allTab" title="${requestScope.titleAll}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblAll}" style="width:100%"><tr><td/></tr></table>
			<div id="pager10"></div>
		</div>
		<%
		    }
		%>

		
		
			
		<%-- <c:if test="${(requestScope.showGen )}">	
		<div id="genTab" title="${requestScope.titleGen}" class="" style="padding:10px;width:100%" tabindex="0">
			<table id="${requestScope.tblGen}"  style="width:100%;"><tr><td/></tr></table>
			<div id="gen_module_pager"></div>
		</div>
		</c:if> --%>
		
		<%
    if ((Boolean) request.getAttribute("showGen")) {
%>
    <div id="genTab" title="${requestScope.titleGen}" class="" style="padding:10px;width:100%" tabindex="0">
			<table id="${requestScope.tblGen}"  style="width:100%;"><tr><td/></tr></table>
			<div id="gen_module_pager"></div>
		</div>
<%
    }
%>

		
		<%-- <c:if test="${(requestScope.showBdm )}">
		<div  id="bdmTab" title="${requestScope.titleBdm}" style="padding:10px;width:102.6%\9" id="bdmTab" tabindex="0">
			<table id="${requestScope.tblBdm}" style="width:100%;"><tr><td/></tr></table>
			<div id="pager2"></div>
		</div>
		</c:if> --%>
		<%
		    if ((Boolean) (request.getAttribute("showBdm"))) {
		%>
			<div  id="bdmTab" title="${requestScope.titleBdm}" style="padding:10px;width:102.6%\9" id="bdmTab" tabindex="0">
			<table id="${requestScope.tblBdm}" style="width:100%;"><tr><td/></tr></table>
			<div id="pager2"></div>
		</div>
		<%
		}
		%>
		
		
		
		<%-- <c:if test="${(requestScope.showJH)}">
		<div id="jhTab" title="${requestScope.titleJh}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblJh}" style="width:100%;"><tr><td/></tr></table>
			<div id="pager3"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showJH"))) {
			%>
				<div id="jhTab" title="${requestScope.titleJh}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblJh}" style="width:100%;"><tr><td/></tr></table>
			<div id="pager3"></div>
		</div>
			<%
			}
			%>
		
		
		
		<%-- <c:if test="${(requestScope.showKZN )}">
		<div id="kznTab" title="${requestScope.titleKzn}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblKzn}"  style="width:100%"><tr><td/></tr></table>
			<div id="pager4"></div>
		</div> 
		</c:if>--%>
		
		<%
		if ((Boolean) (request.getAttribute("showKZN"))) {
		%>
			<<div id="kznTab" title="${requestScope.titleKzn}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblKzn}"  style="width:100%"><tr><td/></tr></table>
			<div id="pager4"></div>
		</div> 
		<%
		}
		%>
		
		
		
		<%-- <c:if test="${(requestScope.showPM)}">
		<div id="pmdTab" title="${requestScope.titlePmd}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblPmd}" style="width:100%"><tr><td/></tr></table>
			<div id="pager5"></div>
		</div>
		</c:if> --%>
		<%
		if ((Boolean) (request.getAttribute("showPM"))) {
		%>
			<div id="pmdTab" title="${requestScope.titlePmd}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblPmd}" style="width:100%"><tr><td/></tr></table>
			<div id="pager5"></div>
		</div>
		<%
		}
		%>
				
		
		<%-- <c:if test="${(requestScope.showHSE)}">
		<div title="${requestScope.titleEhs}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblEhs}" style="width:100%"><tr><td/></tr></table>
			<div id="pager6"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showHSE"))) {
		%>
			<div title="${requestScope.titleEhs}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblEhs}" style="width:100%"><tr><td/></tr></table>
			<div id="pager6"></div>
		</div>
		<%
		}
		%>
				
		<%-- <c:if test="${(requestScope.showET)}">
		<div id="etTab" title="${requestScope.titleEt}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblEt}" style="width:100%"><tr><td/></tr></table>
			<div id="pager7"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showET"))) {
		%>
			<div id="etTab" title="${requestScope.titleEt}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblEt}" style="width:100%"><tr><td/></tr></table>
			<div id="pager7"></div>
		</div>
		<%
		}
		%>
		
		
	<%-- 	<c:if test="${(requestScope.showQTM)}">
		<div  id="qtmTab" title="${requestScope.titleQtm}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblQtm}" style="width:100%"><tr><td/></tr></table>
			<div id="pager9"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showQTM"))) {
		%>
			<div  id="qtmTab" title="${requestScope.titleQtm}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblQtm}" style="width:100%"><tr><td/></tr></table>
			<div id="pager9"></div>
		</div>
		<%
		}
		%>
		
		<%-- <c:if test="${(requestScope.showAdm)}">
		<div id="admTab" title="${requestScope.titleAdm}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblAdm}" style="width:100%"><tr><td/></tr></table>
			<div id="pager8"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showAdm"))) {
		%>
			<div id="admTab" title="${requestScope.titleAdm}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblAdm}" style="width:100%"><tr><td/></tr></table>
			<div id="pager8"></div>
		</div>
		<%
		}
		%>
		
		
		<%-- <c:if test="${(requestScope.showSAP)}">
		<div id="SAPTab" title="${requestScope.titleSAP}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblSAP}" style="width:100%"><tr><td/></tr></table>
			<div id="pager11"></div>
		</div>
		</c:if> --%>
		
		<%
		if ((Boolean) (request.getAttribute("showSAP"))) {
		%>
		    <div id="SAPTab" title="${requestScope.titleSAP}" style="padding:10px;width:102.6%\9" tabindex="0">
			<table id="${requestScope.tblSAP}" style="width:100%"><tr><td/></tr></table>
			<div id="pager11"></div>
		</div>
		<%
		    }
		%>

		
		
	</div>
	</div>
					
</div>
<input type="hidden" id="hdntitle" value='${requestScope.title }' />
<!--</div>-->
<div id="loadMaster" >
</div>
</div>
</form>
</div>