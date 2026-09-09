 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
	jQuery(document).ready(
			function() {
				
				initialiseForm('frmSLAMasterReport');
				jQuery('#submitForm').val('frmSLAMasterReport');

				
				
				
				var source = jQuery('#hdnsource').val();
				fillComboBox("frmSLAMasterReport","cmbfromdmtqm","Deptauthendicationfillcombo.slam");
				fillComboBox("frmSLAMasterReport","cmbfromdmtleader","Deptauthendicationfillcombo.slam");
				fillComboBox("frmSLAMasterReport","cmbtodmtqm","Deptauthendicationfillcombo.slam");
				fillComboBox("frmSLAMasterReport","cmbtodmtleader","Deptauthendicationfillcombo.slam");
				
				fillComboBox("frmSLAMasterReport","cmbfromdmtqmby","employee.commonFilter");
				fillComboBox("frmSLAMasterReport","cmbfromdmtleaderby","employee.commonFilter");
				fillComboBox("frmSLAMasterReport","cmbtodmtqmby","employee.commonFilter");
				fillComboBox("frmSLAMasterReport","cmbtodmtleaderby","employee.commonFilter");

				fillComboBox("frmSLAMasterReport", "cmbDmt1", "sectionCombo.commonFilter");

				formatDateBox('dtefrmdmtqmdate', 'dd-MMM-yyyy');
				formatDateBox('dtefrmdmtleaderdate', 'dd-MMM-yyyy');
				formatDateBox('dtetodmtqmdate', 'dd-MMM-yyyy');
				formatDateBox('dtetodmtleaderdate', 'dd-MMM-yyyy');
                fillWithCurrentDate('dtefrmdmtqmdate');
				fillWithCurrentDate('dtefrmdmtleaderdate');
				fillWithCurrentDate('dtetodmtqmdate');
				fillWithCurrentDate('dtetodmtleaderdate');

				jQuery('#dteFrom').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });
				 jQuery('#dteTo').datebox({  
					 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
				 });

					if (source == "Internal")
					{fillComboBox("frmSLAMasterReport", "cmbDmt2", "sectionCombo.commonFilter");}
				else
					{fillComboBox("frmSLAMasterReport", "cmbDmt2", "customer.commonFilter");}
						
				
					disableField("frmSLAMasterReport", "cmbDmt1");
					disableField("frmSLAMasterReport", "cmbDmt2");
					disableField("frmSLAMasterReport", "dteFrom");
				 var mode = jQuery("#hdnmode").val();

				 if (mode == "View")
				 {						 
				 	fillWithPrevMonth("dteFrom",1);
				 }
				 else
			     {
					 //fillWithCurrentMonth("dteFrom");
				 }
				 fillWithCurrentMonth("dteTo");
				 setFieldValue("hdnallownavg","N");
				 fillWithCurrentDate('hdncurrentdate');
	               
               var sect=jQuery("#hdnsection").val();
			   var role=jQuery("#hdnrole").val();
			//   alert("role"+role);
			   var fromdmtbox=jQuery("#cmbDmt1").val();
			   var todmtbox=jQuery("#cmbDmt2").val();
			   var sleakeyid=getFieldValue("hdnsleakeyid","frmSLAMasterReport");
			   var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport");
			   var recallmonth=getFieldValue("dteFrom","frmSLAMasterReport");
			   var sleadate="01-"+recallmonth;

		       
			   processAjaxCalls("SLAentryrecal_authend.slam","sleakeyid="+sleakeyid+"&sleadate="+sleadate, "authendrecalsuccesssCallback", "", "", "");

			   

			   setTimeout(function() {
	             
			   if(role=="QM PILLAR MEMBER"){
                  if(sect.length!=0){
                      if(sect==fromdmtbox){

                          disableField("frmSLAMasterReport", "cmbfromdmtleader");
                    	  disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
                       	  disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
                       	  readOnlyFields("txtSladQualitycharacter2");

                       		disableField("frmSLAMasterReport", "cmbtodmtqm");
                    		disableField("frmSLAMasterReport", "dtetodmtqmdate");
                       		disableField("frmSLAMasterReport", "cmbtodmtqmby");
                       		//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
                       		readOnlyFields("txtSladQualitycharacter3");

                       		disableField("frmSLAMasterReport", "cmbtodmtleader");
                    		disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                       		disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                       		//disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                       		readOnlyFields("txtSladQualitycharacter4");
                    	 
                          }

                    if(sect==todmtbox)
                      {
                  	  var fromdmtleader =getFieldValue("cmbfromdmtleader");
                      //alert("cmbfromdmtleade="+cmbfromdmtleader);
                      if(fromdmtleader=="A"){
                        disableField("frmSLAMasterReport", "cmbfromdmtqm");
                 		disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
                    	disableField("frmSLAMasterReport", "cmbfromdmtqmby");
                    	//disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
                    	readOnlyFields("txtSladQualitycharacter1");

                    	disableField("frmSLAMasterReport", "cmbfromdmtleader");
                		disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
                   		disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
                   		//disableField("frmSLAMasterReport", "txtSladQualitycharacter2");
                   		readOnlyFields("txtSladQualitycharacter2");

                   		disableField("frmSLAMasterReport", "cmbtodmtleader");
                		disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                   		disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                   		//disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                   		readOnlyFields("txtSladQualitycharacter4");
                      }
                      else{
                     		disableField("frmSLAMasterReport", "cmbtodmtqm");
                    		disableField("frmSLAMasterReport", "dtetodmtqmdate");
                       		disableField("frmSLAMasterReport", "cmbtodmtqmby");
                       		//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
                       		readOnlyFields("txtSladQualitycharacter3");
                       	  
                    	  disableField("frmSLAMasterReport", "cmbfromdmtqm");
                   		  disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
                      	  disableField("frmSLAMasterReport", "cmbfromdmtqmby");
                      	  //disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
                      	  readOnlyFields("txtSladQualitycharacter1");

                      	  disableField("frmSLAMasterReport", "cmbfromdmtleader");
                  		  disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
                     	  disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
                          //disableField("frmSLAMasterReport", "txtSladQualitycharacter2");
                     	  readOnlyFields("txtSladQualitycharacter2");

                     	  disableField("frmSLAMasterReport", "cmbtodmtleader");
                  		  disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                     	  disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                     	  //disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                     	  readOnlyFields("txtSladQualitycharacter4");
                      }
                      }
                          }
				   }

			   else  if(role=="DMT LEADER")
				   {
                    // alert(2);
                     if(sect.length!=0)
                     {
                 if(sect==fromdmtbox)
                     {
                	 var fromdmtqmmember =getFieldValue("cmbfromdmtqm"); 
                	 if(fromdmtqmmember=="A"){                      	  
                        disableField("frmSLAMasterReport", "cmbfromdmtqm");
                		disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
                   		disableField("frmSLAMasterReport", "cmbfromdmtqmby");
                   		//disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
                   		readOnlyFields("txtSladQualitycharacter1");

                        
                        disableField("frmSLAMasterReport", "cmbtodmtqm");
                		disableField("frmSLAMasterReport", "dtetodmtqmdate");
                   		disableField("frmSLAMasterReport", "cmbtodmtqmby");
                   		//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
                   		readOnlyFields("txtSladQualitycharacter3");

                   		disableField("frmSLAMasterReport", "cmbtodmtleader");
                		disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                   		disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                   		//disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                   		readOnlyFields("txtSladQualitycharacter4");
                	 }
                	 else{
                 		disableField("frmSLAMasterReport", "cmbfromdmtleader");
                   	    disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
                      	disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
                      	readOnlyFields("txtSladQualitycharacter2");
                      	
                		disableField("frmSLAMasterReport", "cmbfromdmtqm");
                 		disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
                    	disableField("frmSLAMasterReport", "cmbfromdmtqmby");
                    	//disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
                    	readOnlyFields("txtSladQualitycharacter1");

                         
                        disableField("frmSLAMasterReport", "cmbtodmtqm");
                    	disableField("frmSLAMasterReport", "dtetodmtqmdate");
                    	disableField("frmSLAMasterReport", "cmbtodmtqmby");
                    	//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
                    	readOnlyFields("txtSladQualitycharacter3");

                    	disableField("frmSLAMasterReport", "cmbtodmtleader");
                 		disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                    	disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                    	//disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                    	readOnlyFields("txtSladQualitycharacter4");
                	 }
                     }

               if(sect==todmtbox)
                 {
            	   var todmtqmmember =getFieldValue("cmbtodmtqm");
            	   if(todmtqmmember=="A"){
                    disableField("frmSLAMasterReport", "cmbfromdmtqm");
            		disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
               		disableField("frmSLAMasterReport", "cmbfromdmtqmby");
               		//disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
               		readOnlyFields("txtSladQualitycharacter1");

                	disableField("frmSLAMasterReport", "cmbfromdmtleader");
            		disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
               		disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
               		//disableField("frmSLAMasterReport", "txtSladQualitycharacter2");
               		readOnlyFields("txtSladQualitycharacter2");
                    
                    disableField("frmSLAMasterReport", "cmbtodmtqm");
            		disableField("frmSLAMasterReport", "dtetodmtqmdate");
               		disableField("frmSLAMasterReport", "cmbtodmtqmby");
               		//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
               		readOnlyFields("txtSladQualitycharacter3");
            	   }
            	   else{
            		   disableField("frmSLAMasterReport", "cmbfromdmtqm");
               		   disableField("frmSLAMasterReport", "dtefrmdmtqmdate");
                  	   disableField("frmSLAMasterReport", "cmbfromdmtqmby");
                  	   //disableField("frmSLAMasterReport", "txtSladQualitycharacter1");
                  	    readOnlyFields("txtSladQualitycharacter1");

                   	    disableField("frmSLAMasterReport", "cmbfromdmtleader");
               		    disableField("frmSLAMasterReport", "dtefrmdmtleaderdate");
                  		disableField("frmSLAMasterReport", "cmbfromdmtleaderby");
                  		//disableField("frmSLAMasterReport", "txtSladQualitycharacter2");
                  		readOnlyFields("txtSladQualitycharacter2");
                       
                        disableField("frmSLAMasterReport", "cmbtodmtqm");
               		    disableField("frmSLAMasterReport", "dtetodmtqmdate");
                  		disableField("frmSLAMasterReport", "cmbtodmtqmby");
                  		//disableField("frmSLAMasterReport", "txtSladQualitycharacter3");
                  		readOnlyFields("txtSladQualitycharacter3");

                    	disableField("frmSLAMasterReport", "cmbtodmtleader");
                 		disableField("frmSLAMasterReport", "dtetodmtleaderdate");
                    	disableField("frmSLAMasterReport", "cmbtodmtleaderby");
                    	//disableField("frmSLAMasterReport", "txtSladQualitycharacter4");
                    	readOnlyFields("txtSladQualitycharacter4");
            	   }
                 }
                     }
				   }
			   
	 		    },600);				
			   
               

				 
				var url = "servicelevelmaster_input.slam";
				var hdnfrom = jQuery("#hdnfrom").val();
				var gridUrl="servicelevelmaster_input.slam";

				var frmmonth =getFieldValue("dteFrom"); 
				var tomonth =getFieldValue("dteFrom"); 
				
				var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReport"); 
				var todmt =getFieldValue("cmbDmt2","frmSLAMasterReport"); 
				var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
				var type= jQuery("#hdntype").val();
				//alert("type"+type);		 
				
				if(gridUrl.length > 20)
				{	
					var mode = jQuery("#hdnmode").val();
					viewGrid(gridUrl,"?q=2&from="+hdnfrom+"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&frmdmt="+frmdmt+"&todmt="+todmt+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type);
				}
				else 
				{		
					var mode = jQuery("#hdnmode").val();
					viewGrid(url,"?q=2&from="+hdnfrom+"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&frmdmt="+frmdmt+"&todmt="+todmt+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type);
				}


				    
			    
				processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+slmkeyid+"&month=01-"+frmmonth, "authendcountsuccesssCallback", "", "", "");
				


				
				
				var slmkeyid = getFieldValue('hdnslmkeyid');
				
				fileManagerPopUp(slmkeyid,"SLA","frmSLAMasterReport","btnfilemgr","SlaFilemgr");


				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					//alert("Read From File");
					
					processAjaxCalls("openFile.file?fileName=SLA Blank Format.xls", "", "", "", "", "new");					
				});

				
				
				jQuery('#btnGraph').click(function()
						{
							var rowid = jQuery("#SLAMasterReport").jqGrid('getGridParam','selrow');
							var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',rowid);
							var selId = rowData.sladid;
							
							//getsladidfromgrid();
							var strsladid = jQuery('#hdnsladid').val();
							//alert(strsladid.length);
								//var url = "AbnTagTrendRpt_getChart.abnRpt?rowid="+rowid+"&chType=spline";
								
								if(strsladid.length > 0 )
								{
									var frmmonthrpt =getFieldValue("dteFrom"); 
									var tomonthrpt  = getFieldValue("dteTo"); 
									var slamkeyid =  getFieldValue("hdnslmkeyid"); 
									
									var fromdmt  = getFieldValue("cmbDmt1"); 
									var todmt =  getFieldValue("cmbDmt2"); 
									
									
									var url = "servicelevelmaster_getChart.slam?&todmt="+ todmt +"&fromdmt="+ fromdmt +"&sladid="+strsladid+"&slamkeyid="+slamkeyid+"&frommonth="+ frmmonthrpt +"&tomonth="+ tomonthrpt + "&chType=spline";
									showGraphData(url);	
								}
								else
								{
									alert ('Select any One Parameter/Specification');
								}
							
						});
				
			});
	
	function getsladidfromgrid()
	{
		var	rowCnt = jQuery("#SLAMasterReport").getGridParam("reccount");
		
		jQuery('#hdnsladid').val();	
		for (var i = 0; i <=rowCnt ; i++) 
		{
			var chkval = jQuery("#chkslad_"+i).attr('checked');
			
			if(chkval == 'checked')
			{
				var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',i);
				var sladid = jQuery('#hdnsladid').val();
				var newslaid = rowData.sladid;
				sladid = sladid + ',' + "*"+ newslaid +"*";
				jQuery('#hdnsladid').val(sladid);				
			}
			
		}
	}
	function btnfilemgr_click()
	{
		var keyid = jQuery('#hdnslmkeyid').val();
		if(keyid != null && keyid != ''){
			fileManagerPopUp(keyid,"SLA","","","");
	    }
		
	}
	function chkboxCheck(rowId)
	{
		getsladidfromgrid();
		
	}
	function chkboxUnCheck(rowId)
	{

		getsladidfromgrid();
	}
	jQuery('#btnView').click(function(){
    	
		gridUrl = "servicelevelmaster_input.slam";	

		frmmonth =getFieldValue("dteFrom"); 
		tomonth =getFieldValue("dteFrom");  
		slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
		var type= "report";
		var mode = jQuery("#hdnmode").val();
							
		var filterstring = "?q=2&frmdmt="+ getFieldValue("cmbDmt1") +"&todmt="+ getFieldValue("cmbDmt2") +"&frmmonth="+frmmonth+"&tomonth="+tomonth+"&slmkeyid="+slmkeyid+"&mode="+mode+"&type="+type ;
		  var recallmonth=getFieldValue("dteFrom","frmSLAMasterReport");
		   var sleadate="01-"+recallmonth;

	       
		   processAjaxCalls("SLAentryrecal_authend.slam","sleakeyid="+slmkeyid+"&sleadate="+sleadate, "authendrecalsuccesssCallback", "", "", "");

		viewGrid(gridUrl,filterstring);
		
	});	
	function authendrecalsuccesssCallback(result)
	{
		//alert(1);
		setFieldValue("cmbfromdmtqm",result.Data[0][0]);
		//setFieldValue("hdnfrmdmtqm",result.Data[0][0]);
		setFieldValue("dtefrmdmtqmdate",result.Data[0][1]);
		setFieldValue("txtSladQualitycharacter1",result.Data[0][2]);
		setFieldValue("cmbfromdmtqmby",result.Data[0][3]);
		
		setFieldValue("cmbfromdmtleader",result.Data[0][4]);
		setFieldValue("dtefrmdmtleaderdate",result.Data[0][5]);
		setFieldValue("txtSladQualitycharacter2",result.Data[0][6]);
		setFieldValue("cmbfromdmtleaderby",result.Data[0][7]);
		
		setFieldValue("cmbtodmtqm",result.Data[0][8]);
		setFieldValue("dtetodmtqmdate",result.Data[0][9]);
		setFieldValue("txtSladQualitycharacter3",result.Data[0][10]);
		setFieldValue("cmbtodmtqmby",result.Data[0][11]);
		
		setFieldValue("cmbtodmtleader",result.Data[0][12]);
		setFieldValue("dtetodmtleaderdate",result.Data[0][13]);
		setFieldValue("txtSladQualitycharacter4",result.Data[0][14]);
		setFieldValue("cmbtodmtleaderby",result.Data[0][15]);
	}

	function authendcountsuccesssCallback(result)
	{
		//alert(1);
		setFieldValue("hdncount",result.Data[0][0]);
		
	}
	function frmSLAMasterReport_beforeSubmit()
	{
		//alert(1);
		var hdncurrentdate = getFieldValue('hdncurrentdate');
		//alert(hdncurrentdate);
		var hdnslmkeyid=getFieldValue("hdnslmkeyid","frmSLAMasterReport");
		//alert(hdnslmkeyid);
		var hdnsleakeyid=getFieldValue("hdnsleakeyid","frmSLAMasterReport");
		//alert(hdnsleakeyid);
		var dteFrom=getFieldValue("dteFrom","frmSLAMasterReport");
		//alert(dteFrom);
        var count=getFieldValue("hdncount","frmSLAMasterReport");
        //alert(count);
		
		var cmbfromdmtqm =getFieldValue("cmbfromdmtqm","frmSLAMasterReport");
		//alert(cmbfromdmtqm); 
		var dtefrmdmtqmdate =getFieldValue("dtefrmdmtqmdate","frmSLAMasterReport");
		var cmbfromdmtqmby =getFieldValue("cmbfromdmtqmby","frmSLAMasterReport");
        var txtSladQualitycharacter1= jQuery('#txtSladQualitycharacter1').val();

		var cmbfromdmtleader =getFieldValue("cmbfromdmtleader","frmSLAMasterReport"); 
		var dtefrmdmtleaderdate =getFieldValue("dtefrmdmtleaderdate","frmSLAMasterReport");
		var cmbfromdmtleaderby =getFieldValue("cmbfromdmtleaderby","frmSLAMasterReport");
        var txtSladQualitycharacter2= jQuery('#txtSladQualitycharacter2').val();

		var cmbtodmtqm =getFieldValue("cmbtodmtqm","frmSLAMasterReport"); 
		var dtetodmtqmdate =getFieldValue("dtetodmtqmdate","frmSLAMasterReport");
		var cmbtodmtqmby =getFieldValue("cmbtodmtqmby","frmSLAMasterReport");
        var txtSladQualitycharacter3= jQuery('#txtSladQualitycharacter3').val();

		var cmbtodmtleader =getFieldValue("cmbtodmtleader","frmSLAMasterReport"); 
		var dtetodmtleaderdate =getFieldValue("dtetodmtleaderdate","frmSLAMasterReport");
		var cmbtodmtleaderby =getFieldValue("cmbtodmtleaderby","frmSLAMasterReport");
        var txtSladQualitycharacter4= jQuery('#txtSladQualitycharacter4').val();
       // alert(2);
        var gridData = "dteFrom="+dteFrom+"&count="+count+
        "&hdnslmkeyid="+hdnslmkeyid+"&hdnsleakeyid="+hdnsleakeyid+"&cmbfromdmtqm="+cmbfromdmtqm+
		   "&dtefrmdmtqmdate="+ dtefrmdmtqmdate +
		   "&cmbfromdmtqmby="+ cmbfromdmtqmby +
		   "&txtSladQualitycharacter1="+ txtSladQualitycharacter1 +
		   "&cmbfromdmtleader="+ cmbfromdmtleader +
		   "&dtefrmdmtleaderdate="+ dtefrmdmtleaderdate +
		   "&cmbfromdmtleaderby="+ cmbfromdmtleaderby +
		   "&txtSladQualitycharacter2="+ txtSladQualitycharacter2 +
		   "&cmbtodmtqm="+ cmbtodmtqm +
		   "&dtetodmtqmdate="+ dtetodmtqmdate +
		   "&cmbtodmtqmby="+ cmbtodmtqmby +
		   "&txtSladQualitycharacter3="+ txtSladQualitycharacter3 +
		   "&cmbtodmtleader="+ cmbtodmtleader +
		   "&dtetodmtleaderdate="+ dtetodmtleaderdate +
		   "&cmbtodmtleaderby="+ cmbtodmtleaderby +
		   "&txtSladQualitycharacter4="+ txtSladQualitycharacter4;
        //var gridData  = 'authendicationdata='+ jsonStr ;
        //alert(gridData);	
       return gridData; 
		   	
		
	}
	function chkFormater(id, options, rowObject)
	{
		var rowId = options.rowId;	
		var idval;
		idval = 'chkslad';
		return '<input id='+idval+'_'+rowId+' name="slad_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}
	
	function txtFormatter(id, options, rowObject) {

		var id = options.rowId;
		var columnName = options.colModel.name;
		//var columnNo = options.pos;
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		var columnVal =rowObject[columnName.substring(columnName.indexOf("_")+1)];
		var colKeyId="";
		var idval;
		idval = 'txtperformance_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id);  type="text"  value="" maxlength="3" style="width: 30px; text-align:right;" / >';		
		
	}
	function gotFocuse(id){

		
		setFieldValue("hdnallownavg","Y");
		//alert("hdnallownavg" + "Y");
		numericTextBox(id);
	
	}
	function viewGrid(url,filterString)
	{  

		processGridnew(url, filterString,"SLAMasterReport", "pager3", "", "doubleclick",null,"gridLoadComplete");
		
		processAjaxCalls("SLAentryauthenmonth_authend.slam","sleakeyid="+slmkeyid+"&month=01-"+frmmonth+"&type=count", "authendcountsuccesssCallback", "", "", "");
		
		
	}
	function frmSLAMasterReport_successsCallback(result)
	{
       var sleakeyid=result.successData.keyId;
       //alert(sleakeyid);
       processAjaxCalls("SLAentryrecal_authend.slam","sleakeyid="+sleakeyid, "authendrecalsuccesssCallback", "", "", "");
	}
	function gridLoadComplete_afterLoad(data) {
		
		jQuery("#SLAMasterReport").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			
			
var cm = jQuery("#SLAMasterReport").jqGrid("getGridParam", "colModel");
			
			var colName = cm[cellidx];		
			var dateval =  colName['index'];
			
			dcellidx = cellidx - 14;

			var colN =12;
			var colN1 =12;
			var start = 14;
			var end = cellidx;
			var i=start;
			//alert("cellidx:" +cellidx);
			for (i=start;i<=end; i = i+4){
				//alert("I:" +i);
				if(cellidx>=i && cellidx<=i+4){
					colN = colN1;
				}
				colN1 = colN1 +4;
				
			}
			
			//var colN = 10 + 3*(Math.floor((dcellidx/3))) + ((dcellidx%3)==2?1:(dcellidx%3)) + ((dcellidx%3)==0?1:0);
			/*alert(cellidx);
			var dateval = jQuery("#CH1-" + "12").html();		
			alert("Date Val: " + dateval);
			
			var dateval = jQuery("#CH1-" + "16").html();		
			alert("Date Val: " + dateval);
			*/
			var dateval = jQuery("#CH1-" + colN).html();		
			
			
			
			
			setFieldValue("txtmonth",dateval,"frmSLAMasterReport");							
			setFieldValue("hdnselcelindex",cellidx);
				
			//alert("Date Val: " + dateval);
			
		/*	var cm = jQuery("#SLAMasterReport").jqGrid("getGridParam", "colModel");
			
			var colName = cm[cellidx];		
			var dateval =  colName['index'];
			
			dcellidx = cellidx - 14;

			//var colN = ((cellidx)-(cellidx%3));
			var colN = 10 + 3*(Math.floor((dcellidx/3))) + ((dcellidx%3)==2?1:(dcellidx%3)) + ((dcellidx%3)==0?1:0);
					
			var dateval = jQuery("#CH1-" + colN).html();	
		
			setFieldValue("txtmonth",dateval,"frmSLAMasterReport");		
			//alert(cellidx);
					
			setFieldValue("hdnselcelindex",cellidx);
				
			
			*/
			
		}
		});
	
}
	/*function doubleclick(id) {
	   // alert("Work In progress." +id);
		var rowData = jQuery("#SLAMasterReport").jqGrid('getRowData',id);
        //alert("rowdata"+rowData);
		var sladid = rowData.sladid;
		var slemid = rowData.slemid;

		var freq = rowData.Frequency;
		//alert("slemid"+slemid);
		var effectivedate = rowData.EfectiveDate;	
		//alert("effectivedate:" +effectivedate);
		var currentDate = getFieldValue('hdncurrentdate');
		//alert("currentDate:" +currentDate);
		var curntmonth = currentDate.substring(3,6);
		//alert("curntmonth:" +curntmonth);
		var numcurrentmonth = changeFormatStringtoNumber(curntmonth.trim());
		//alert("numcurrentmonth:" +numcurrentmonth);
		var currentday = currentDate.substring(0,2);
		//alert("currentday:" +currentday);
		//alert( currentDate  + "A"+ currentmonth + "B" + numcurrentmonth  +  "C"+ currentday);
		var effectivemonth = effectivedate.substring(3,6);
		var effectiveday = effectivedate.substring(0,2);
		var numeffectivemonth = changeFormatStringtoNumber(effectivemonth);
		var effectiveyear = effectivedate.substring(7,11);
		var selcmothyear = getFieldValue("txtmonth","frmSLAMasterReport");
		var selmonth = selcmothyear.substring(0,3);
		var numselmonth  = changeFormatStringtoNumber(selmonth);
		var selyear = selcmothyear.substring(4,8);
		var selcellindx = getFieldValue('hdnselcelindex');
		var lockdate = '0';
		var lockcurdate = '0';
		//alert('selcmothyear--' + selcmothyear +'selmonth' +  selmonth +'selyear' +  selyear + ' numselmonth ' + numselmonth + ' numeffectivemonth ' + numeffectivemonth);
		
		effectivedate = effectivedate.trim();
		//alert("convertStringToDate(effectivedate) " +convertStringToDate(effectivedate));
		//alert("convertStringToDate(currentDate) " +convertStringToDate(currentDate));
		
		//alert ( effectivedate + '   effectivedate ' + convertStringToDate(effectivedate) + '   currentDate' + currentDate);
		
		var selDate = '01-' + selcmothyear;
		
			if(convertStringToDate(effectivedate) > convertStringToDate(currentDate))
			{   
				alert('Effective Date is great than Current Date');
				setFieldValue("hdnallownavg","N");
			}
			
			else if(parseInt(selyear,10) < parseInt(effectiveyear,10))
			{
				alert('Selected Month is lesser than effective date');
				setFieldValue("hdnallownavg","N");
			}

			else if(convertStringToDate(selDate) > convertStringToDate(currentDate))
			{
				alert('Selected Month greater than Current Date');
				setFieldValue("hdnallownavg","N");
			}
			
			else if(parseInt(selcellindx,10) <= 13)
			{
				setFieldValue("hdnallownavg","N");
			}
			else
			{
					
						//alert('Selected Month is lesser than effective date');
						setFieldValue("hdnallownavg","Y");
						//alert( 'selyear ' + selyear + 'effectiveyear ' +  effectiveyear + 'numselmonth ' +  numselmonth + 'numeffectivemonth' + numeffectivemonth);
						if(parseInt(selyear,10) == parseInt(effectiveyear,10))
						{
							if(parseInt(numselmonth,10) == parseInt(numeffectivemonth,10))
							{
								//alert('effectivedate' + effectiveday);
								lockdate = effectiveday-1;
								if(parseInt(effectiveday,10) == parseInt(currentday,10))
								{
									lockcurdate = currentday;
								}
								else
								{
									lockcurdate = currentday;
								}
								
							}
						}
					
				
				}
			
		
		var frmdmt =getFieldValue("cmbDmt1","frmSLAMasterReport"); 
		var todmt =getFieldValue("cmbDmt2","frmSLAMasterReport"); 
		var month = currentDate.substring(2,11);
	//alert("month"+month);
		//var month =getFieldValue("txtmonth","frmSLAMasterReport");  
		var slmkeyid =getFieldValue("hdnslmkeyid","frmSLAMasterReport"); 
		var AllowToNavigate = getFieldValue("hdnallownavg");
		var mode = jQuery("#hdnmode").val();
		
		
		///compareDate(effectivedate,)
		//alert('AllowToNavigate' + AllowToNavigate);
		//if (AllowToNavigate == "Y")
		//{	
			//setFieldValue("hdnallownavg","N");
			/if (mode == "View")
			{
				navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&mode="+mode+"&slmkeyid="+slmkeyid+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq,"SLA Report ");
			}
			else
			{   
				//alert(123);
				navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&mode="+mode+"&slmkeyid="+slmkeyid+"&sladid="+sladid+"&slemid="+slemid+"&freq="+freq+"&lockdate="+lockdate+"&lockcurdate="+lockcurdate,"SLA Entry");
			}/
			
		//}
	}*/
</script>

	<form name="frmSLAMasterReport" id="frmSLAMasterReport" action=" " method="post">
		<div style="margin-top:0%;" id='wrapperRpt'>
		
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx">
						<label>From DMT</label>
					</div>
					
						<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbDmt1" name="cmbDmt1"
							 style="width: 265px;" value="${requestScope.frmdmt}" />
					</div>
					
				</td>
				<td style="padding-left: 20px;" >

					<div class="easyui-paddingbfpx">
						<label>To DMT</label>
					</div>
					<div class="easyui-paddingbfpx">
						<input class="easyui-combo" id="cmbDmt2" name="cmbDmt2"
							 style="width: 265px;" value="${requestScope.todmt}" />					
					</div>
				</td>							
				<td style="padding-left: 20px;" >					
					<div class="easyui-paddingbfpx"><label>Month</label></div>					
					<div class="easyui-paddingbfpx">
						<span><input id="dteFrom" name="dteFrom" class="easyui-datebox"  style="width:87px;" value="${requestScope.month}"  ></span>
					</div>
				</td>
		 <!-- <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx"><label >To</label></div>				
					<div class="easyui-paddingbfpx">
						<span><input id="dteTo" name="dteTo" class="easyui-datebox"  style="width:87px;" value=""  ></span>
					</div>	
				</td>-->
				 <td style="padding-left: 20px;" >
					<div class="easyui-paddingbfpx" style="margin-top: 15px">
					<span >
						<input class="easyui-button" type="button" value="View"
										id="btnView" name="btnView" style="height: 21px"  /> 
					</span>
					</div>	
				</td>
				<td style="padding-left: 20px;" >
				<c:if test="${'graph'  == requestScope.mode }">  
					<div class="easyui-paddingbfpx" style="margin-top: 15px;">
					<span >
						<input class="easyui-button" type="button" value="Graph"
										id="btnGraph" name="btnGraph" style="height: 21px"  /> 
					</span>
					</div>	
					</c:if>
				</td>
				<!--<td >
				<div style="padding-left:4px;padding-top:8px;"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 21px; width : 90px;"/></div>
				</td>
				--><td > 
				<c:if test="${'Entry'  == requestScope.mode }">  
						<div style=" padding-left:10px; position:relative; ">
							 <span  id="SlaFilemgr" style="position:absolute;margin-top:-8px;margin-top:-10px\9;" >
		             </span> 
		             </div>	
            	 </c:if>
				</td>
			</tr>
		</table>							
				<table style="width:1000px;font-weight:bold ;border:2px solid #a4a4a4;"  >
		<div>
		<tr>
		  <td>
		  <span class="easyui-paddingbfpx" style="padding-top: 3px;dispaly:none;">
		     <label>Approvals</label></span>	    
		     <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 73px;dispaly:none;">
            <label>Accept/Reject</label></span>
             <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 218px;dispaly:none;">
             <label>Remarks</label></span>
             <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 175px;dispaly:none;">
		     <label>Date</label></span>
		     <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 100px;dispaly:none;">
		     <label>Approved By</label></span>
		    </td>
		  </tr>
		  </div>
		  <div>
		  <tr>
		  <td> 
		     <label>From DMT QM Member</label>
		     <span class="easyui-paddingbfpx" style="padding-top: 3px;dispaly:none;">
		     </span>
	    	  <input class="easyui-combobox" id="cmbfromdmtqm" name="cmbfromdmtqm" style="width:120px;padding-left: 73px;"  value="${requestScope.jhnTlSlamst.slamStatus}"/>
			  <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
					<textarea rows="1" cols="40" style="width: 350px; height : 27px;" id="txtSladQualitycharacter1" name="txtSladQualitycharacter1"></textarea>
			  </span>
			<span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="dtefrmdmtqmdate" name="dtefrmdmtqmdate"  class="easyui-datebox" maxlength="95" style="width: 100px;padding-left: 73px;"/>
		    </span>
		     <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="cmbfromdmtqmby" name="cmbfromdmtqmby" class="easyui-combo" style="width: 132px\9; width : 174px;" value="" />
		    </span> 
		    </td>
		    </tr>
		    </div>
		    <div>
		    <tr>
		    <td>
		     <label>From DMT Leader</label>
		     <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 28px;dispaly:none;">
	    	  <input class="easyui-combobox" id="cmbfromdmtleader" name="cmbfromdmtleader" style="width:120px;"  value="${requestScope.jhnTlSlamst.slamStatus}"/>
		      </span>
		      <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
					<textarea rows="1" cols="40" style="width: 350px; height : 27px;" id="txtSladQualitycharacter2" name="txtSladQualitycharacter2"></textarea>
              </span>
               <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="dtefrmdmtleaderdate" name="dtefrmdmtleaderdate"  class="easyui-datebox" maxlength="95" style="width: 100px;"/>
		    </span>
            <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="cmbfromdmtleaderby" name="cmbfromdmtleaderby" class="easyui-combo" style="width: 132px\9; width : 174px;" value="" />
		    </span>
		    </td>
		    </tr> 
		    </div>  
		     <div>
		     <tr>
		     <td>
		     <label>To DMT QM Member</label>
		     <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 16px;dispaly:none;">
	    	  <input class="easyui-combobox" id="cmbtodmtqm" name="cmbtodmtqm" style="width:120px;"  value="${requestScope.jhnTlSlamst.slamStatus}"/>
		      </span>
		        <span class="easyui-paddingbfpx" style="padding-left: 24px;dispaly:none;">
					<textarea rows="1" cols="40" style="width: 350px; height : 27px;" id="txtSladQualitycharacter3" name="txtSladQualitycharacter3"></textarea>
			  </span>
			   
		    <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="dtetodmtqmdate" name="dtetodmtqmdate"  class="easyui-datebox" maxlength="95" style="width: 100px;"/>
		    </span>
		     <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="cmbtodmtqmby" name="cmbtodmtqmby" class="easyui-combo" style="width: 132px\9; width : 174px;" value="" />
		    </span> 
		    </td>
		    </tr>
		    </div>
            <div>
            <tr>
            <td>
            <label>To DMT Leader</label>
               <span class="easyui-paddingbfpx" style="padding-top: 3px;padding-left: 45px;dispaly:none;">
	    	  <input class="easyui-combobox" id="cmbtodmtleader" name="cmbtodmtleader" style="width:120px;"  value="${requestScope.jhnTlSlamst.slamStatus}"/>
		      </span>
		       <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
					<textarea rows="1" cols="40" style="width: 350px; height : 27px;" id="txtSladQualitycharacter4" name="txtSladQualitycharacter4"></textarea>
			  </span>
			   <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="dtetodmtleaderdate" name="dtetodmtleaderdate"  class="easyui-datebox" maxlength="95" style="width: 100px;"/>
		    </span>
		    <span class="easyui-paddingbfpx" style="padding-left: 25px;dispaly:none;">
			<input id="cmbtodmtleaderby" name="cmbtodmtleaderby" class="easyui-combo" style="width: 132px\9; width : 174px;" value="" />
		    </span> 
		   </td>
		   </tr>
		   </div>
		</table>			
		<table id='SLAMasterReport'>
			<tr>
				<td></td>
			</tr>
			
		</table>
		<div id='pager3'></div>
	</div>
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
	<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />
	<input type="hidden" id="txtmonth" name="txtmonth"  value="${requestScope.month}" />
	<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />
	<input type="hidden" id="hdnsource" name="hdnsource"  value="${requestScope.source}" />
	<input type="hidden" id="hdnslmkeyid" name="hdnslmkeyid"  value="${requestScope.slmkeyid}" />
	<input type="hidden" id="hdnsleakeyid" name="hdnsleakeyid"  value="${requestScope.sleakeyid}" />
	<input type="hidden" id="hdntype" name="hdntype"  value="${requestScope.type}" />
	<input type="hidden" id="hdnallownavg" name="hdnallownavg"  value="" />
	<input type="hidden" id="hdnselcelindex" name="hdnselcelindex"  value="" />
	<input type="hidden" id="hdnsection" name="hdnsection"  value="${requestScope.section}" />
	<input type="hidden" id="hdnrole" name="hdnrole"  value="${requestScope.rolename}" />	

	<input type="hidden" id="hdncurrentdate" name="hdncurrentdate"  value="" />
	
	
	<input type="hidden" id="hdnsladid" name="hdnsladid"  value="" />
    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
    <input type="hidden" id="hdnfrmdmtqm" name="hdnfrmdmtqm"  value="" />
    <input type="hidden" id="hdncount" name="hdncount"  value="" />
    
</form>
