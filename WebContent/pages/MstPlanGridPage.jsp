<script type="text/javascript"><!--	
	jQuery(document).ready(function(){	
		var url = jQuery('#hiddenUrl').val();
		//jQuery('#hiddenUrl').val(url+'?q=2&masterForm=Y');
		var dataStr = '&pillar='+jQuery('#hdncatRelatedMaster').val();  //Ta
		//alert(" dataStr :: Now :: Checking :: "+dataStr);
		
	    /* if(url != null && url!= '' && url != ' ')
		{
			if(url.indexOf('-')>0)
				dataStr += '&pillar='+url.substring(url.indexOf('-')+1,url.indexOf('_'));
			else
			{
				if(url.indexOf('_')>0)
					dataStr += '&pillar='+url.substring(0,url.indexOf('_'));
			}
		}
		*/
		
		//alert(" url :: "+url);
		processGridnew(url,dataStr,"indicatorsGrid","indicatorsPager","","indicatorsDblClk","","indicatorsLoad","indicatorsReload");
	});
	jQuery('#btnnewPlan').click(function(){
		var url = jQuery('#hiddenUrl').val();
		//alert(" url :: "+url);
		
		if(url != null && url!= '' && url != ' ')
		{
			if(url.indexOf('-')>=0)
			{
				var action = url.substring(url.indexOf('.'));
				url = url.substring(0,url.indexOf('_')).replace('-','_');
				url = url+action;
			}
			else
				url = "master_"+url.substring(0,url.indexOf('_'))+url.substring(url.indexOf('.'));
		}
		 var dataStr = "mode=create";
		 var pillar = jQuery("#hdncatRelatedMaster").val();
		 dataStr += "&pillarCode="+pillar; 
		 if( url.indexOf("?") > 0)
			 url += "&";
		 else 
			 url = url +"?";
		 
		 url += dataStr +"&";
		 
		 navigateToNextForm(url,"Masters",null,{"filterString":url});
		
	});
	function indicatorsLoad()
	{
		//if(screen.width <=1300)
		{			
			//jQuery( "#indicatorsGrid" ).setGridHeight('61%');
			//jQuery( "#indicatorsGrid" ).setGridWidth('100%');			
		}
	}
	function indicatorsDblClk(id)    ///Double click
	{   
		var url = jQuery('#hiddenUrl').val();
		//alert(" :: url :: "+url);
		var rowData = jQuery("#indicatorsGrid").jqGrid('getRowData',id);
		var keyid=rowData.KEYID;
		var plnType=jQuery('#hdnplnType').val();
	    var dataStr = "&keyid="+keyid+"&plnType="+plnType;	
	      
	    //alert(" dataStr :: "+dataStr);
	
		if(url != null && url!= '' && url != ' ')
		{   
			if(url.indexOf('-')>=0)
			{   
				var action = url.substring(url.indexOf('.'));
				url = url.substring(0,url.indexOf('_')).replace('-','_');
				url = url+action;
				dataStr += "&showGrid=Y";
			}
			else
				url = "master_"+url.substring(0,url.indexOf('_'))+url.substring(url.indexOf('.'));
		}
		//	var indId = rowData.indicatorKeyId;	
		//	var indName = rowData.indicatorName;	
		//	var selLevel= rowData.level;	
		 
		   
		    //+"&indId="+indId+"&indName="+escape(indName)+"&selLevel="+selLevel
			navigateToNextForm(url+dataStr+"&","",null,{"filterString":url});
	}

	jQuery('#indicatorsReload').click(function(){
		//alert('Refresh');
		jQuery("#indicatorsGrid").trigger("reloadGrid");
		
      });
	
</script>

<form id="frmmstPlan">
<div id="wrapperRpt">
<div style="margin-top: -4px">	
	<input type="button" class="easyui-button"  id="btnnewPlan" value="Add New" style="height: 25px; width : 60px;margin-top: -20px "/>
</div>
<div>			
	<table id="indicatorsGrid" style="float: left;"></table>
	 <div id="indicatorsPager"></div>
</div>
 </div>

 <input type="hidden" id="mode" name="mode" value="new"/>


 <input type="hidden" id="hdncatRelatedMaster" name="hdncatRelatedMaster" value="${requestScope.catRelatedMaster}"/>
<input type="hidden" id="hdnplnType" name="hdnplnType" value="${requestScope.plnType}" >

</form>
