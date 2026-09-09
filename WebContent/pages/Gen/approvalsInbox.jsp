
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	  processGridnew("inbox_input.alerts","&q=0","grdApprovals","approvalpager","","grdApprovals_DoubleClick","","grdApprovals_loadComplete");
});
function grdApprovals_DoubleClick(id){
	var rowData = jQuery("#grdApprovals").jqGrid('getRowData',id);
	var url = rowData.uri;
	var document = rowData.DOCUMENT;
	var docNo    = rowData.DOCUMENTNO;
	var docDate     = rowData.DOCDATE;
	var roleName     = rowData.RoleName;
	var item=rowData.ITEM;
	//alert(" roleName :: "+roleName);
	var formurl     = "";
	var formCaption = "";
		//alert(" document :: "+document);
	if("Kaizen Suggestion"==document)
	{	
		
		formCaption = "Suggestion Accept/Reject";
		formurl = "KaizenBankcVerified_input.kznbnk?&fromMode=inbox&KzbnKeyid="+docNo;
		//alert("FormURLS"+formurl);
	}else if("Safety Suggestion"==document)
	{	
		
		formCaption = "Suggestion Accept/Reject";
		formurl = "KaizenBankcVerified_input.kznbnk?&hsesfty=Y&fromMode=inbox&KzbnKeyid="+docNo;
		//alert("FormURLS"+formurl);
	}
	else if(document=="Service Level Agreement"){
      //  alert("rolename>>"+roleName);
     // alert("URL>>>"+url.length);
      if(url.length==124)
    	{
    	  
    	  formurl ="Department_input.SerLevAgr?&hidebutton=Y&filterButton=false&grid=true&&clearfrom=false&Keyid="+docNo+"&document="+item+"&from=&mode=Entry&status=PENDING&InfromMode=inbox";
    	}
      else{
    	  formurl ="Department_input.SerLevAgr?&filterButton=false&grid=true&&clearfrom=false&Keyid="+docNo+"&document="+item+"&from=&mode=Authentication&status=PENDING&InfromMode=inbox";
    		
      }
	//	formurl ="Department_input.SerLevAgr?&filterButton=false&grid=true&clearfrom=false&Keyid="+docNo+"&document="+item+"&from=&mode=Authentication&status=PENDING&fromMode=inbox";
			
	//alert("formurl in service level agreement");
		//formurl = url.replace("$$KEYID$$",docNo);
		//formurl = formurl.replace("$$keyid$$",docNo) +"&fromMode=inbox";
	}
	else if(roleName=="PROJECT LEAD" && document!="FIPCLO"){

		formurl ="projectsprotoview_input.prpo?type=team&filterButton=false&keyid="+docNo+"&isClosure=&checkList=Y&mode=&stage=MAIC&approval=N";
		//formurl = url.replace("$$KEYID$$",docNo);
		//formurl = formurl.replace("$$keyid$$",docNo) +"&fromMode=inbox";
	}
	
	else
	{   
		url =replaceAll( url, "##" , "&");
		formurl = url.replace("$$KEYID$$",docNo);
		formurl = formurl.replace("$$keyid$$",docNo) +"&fromMode=inbox";
		//alert("formurl"+formurl);

	}
	 //if (formurlKaizenBankcVerified_input.kznbnk)
	//navigateToNextForm("Abnormality_input.abnForm?q=2&refDocId="+refDocId+"&filterButton=false"+"&flid="+selFlid,"Abnormality Identification");
	if( formurl != ""){
		if( jQuery(".layout-split-west").is(":visible")){
			 jQuery('#mainlayout').layout('collapse','west');
		}
		jQuery(".alert_content_div").slideUp("fast");
		setFormMainHeader(document + " - Approval"); 
		navigateToNextForm(formurl,formCaption  );
	}
	
}
function replaceAll(str,sStr,rStr){
	var find = new RegExp(sStr, 'g');
	return str != undefined ? str.replace(find, rStr):"";
}
</script>
<div id="wrapperRpt">
	<div style="margin-left:10px;margin-top:50px;">
		<table id='grdApprovals'><tr><td></td></tr></table>
		<div id='approvalpager'></div>
	</div>
</div>