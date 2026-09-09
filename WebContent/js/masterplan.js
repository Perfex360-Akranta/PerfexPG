var gblGrdid;

function viewMasterPlanGrid(gridId,filterString){
	gblGrdid=gridId;
	//alert(" filterString " + filterString+" gridId :: "+gridId );
	var type = jQuery('#hdnType').val();
	//alert(" type :: "+type);
	if(type=="eqp" || type=="prod"){
		 processGridnew('planactual_input.conf',filterString,gridId,"","","eqpActualDblClick","","mstPlanActualLoad","mstPlanActualError");
	}
	 else{//alert(2);
		 processGridnew('planactual_input.conf',filterString,gridId,"","","mstPlanActualDblClick","","mstPlanActualLoad","mstPlanActualError");
	 }
}
function eqpActualDblClick(id){
	
	var dataStr = "?q=2&factId=&flag=PLAN&indicatorId=";
	LoadPopUp("divMileStone","milestone_view.conf"+dataStr, true,"97%","87%","0%","2px", "Milestone_Callback","Milestone",false,true);
}
function mstPlanActualDblClick(id)
	{
	//var flid1=jQuery("#hdnMpmsFlid").val();
	//alert(flid);
	//var keyId=jQuery("#hdnMpmsKeyid").val();
	//alert(keyId);
		var formLock = jQuery('#hdnFormLock').val();
		var url = jQuery('#hiddenUrl').val();	
		
		/**Added for Demo purpose**/
		if(url.substring(0,3)=="emp") 
			url="MstPlanActual_GEN.conf?q=2&indFlag=Y&showGrid=Y";
		
		if(formLock=="lock")	return;
		else
		{
		      var selCol = jQuery('#hdnSelectedWM').val();
			  var month =jQuery("#"+gblGrdid).jqGrid('getCell',1,selCol);
			  var w =jQuery("#"+gblGrdid).jqGrid('getCell',2,selCol);
			  var pa =jQuery("#"+gblGrdid).jqGrid('getCell',id,'Plan/Actual5');	
			  if(url.toLowerCase().indexOf(pa.toLowerCase())>=0)
			  {
				  var flid = jQuery("#frmMstPlanAct input[id='flid']").val();
				//  alert(flid);
					//var keyId=jQuery("#hdnMpmsIndicatorid").val();
					//alert(keyId);
				  var indicatorId =jQuery("#"+gblGrdid).jqGrid('getCell',id,'IndicatorId1');			 
				  var factId = jQuery("#frmMstPlanAct input[id='factory']").val();
				  var sectionId = jQuery("#frmMstPlanAct input[id='section']").val();
			      var cellId = jQuery("#frmMstPlanAct input[id='cell']").val();
			      //alert(" cellId :: "+cellId);
			       if(flid == undefined || flid.trim().length<=0){
			      }
			      if(flid != null && flid!= '' && flid != ' ')
				  {
					 var dataStr = "?q=2&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId+"&month="+month+"&wkNo="+w+"&flag="+pa+"&indicatorId="+indicatorId+"&flid="+flid;// TTTS
					 if(false == month || "false" == month) {
				 		 dataStr = "?q=5&month=DEC-2013&wkNo=W2&flag=PLAN";
				 	 } 
					     LoadPopUp("divMileStone","milestone_view.conf"+dataStr, true,"97%","93%","-2%","0%", "Milestone_Callback","Milestone",true,true);
		     			 jQuery("#refreshGridFlag").val('Y');	
				  }
			      //else
				      //alert('Select Line');
			      
			  }
		}
	}
	/**Load Complete**/
	function mstPlanActualLoad()
	{
		 if(screen.width <=1300)
		 {			
			 jQuery( "#"+gblGrdid ).setGridHeight('40%');
			 jQuery( "#"+gblGrdid ).setGridWidth('90%');			
		 }
		 jQuery("#"+gblGrdid+" tr[id=2]").hide(); 
		 jQuery("#"+gblGrdid+" tr[id=1]").hide();
		 
		
		
		
		jQuery("#"+gblGrdid).setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {  					
			if(cellidx < 6)	
			{		
				if(cellidx == 4 ||cellidx == 3 || cellidx == 2)
				{
					jQuery('#hdnSelectedIndicatorId').val(jQuery("#"+gblGrdid).getCell(id, 'IndicatorId1'));
					jQuery('#hdnSelectedIndicator').val(cellvalue);
					if(cellidx == 2)
						jQuery('#hdnSelectedLevel').val(cellidx);
					else
					{
						var lvl = "3";
						jQuery('#hdnSelectedLevel').val(lvl);
						var cv = jQuery("#"+gblGrdid).getCell(id, 'Sub Category3');
						if(cellidx == 4)
							jQuery('#hdnSelectedIndicator').val(cv);
					}
				}	
				else
				{
					jQuery('#hdnSelectedIndicatorId').val('');	
					jQuery('#hdnSelectedIndicator').val('');
					jQuery('#hdnSelectedLevel').val('');						
				}	
				jQuery('#hdnSelectedWM').val('');	
				jQuery('#hdnFormLock').val("lock");
			}
			else
			{
				jQuery('#hdnSelectedIndicatorId').val('');	
				jQuery('#hdnSelectedIndicator').val('');	
				jQuery('#hdnSelectedLevel').val('');
				jQuery('#hdnSelectedWM').val(cellidx);
				jQuery('#hdnFormLock').val("");
			}
		}
		});
		var url = jQuery('#hiddenUrl').val();	
		var rowId = jQuery("#"+gblGrdid).jqGrid('getDataIDs');
		var countCols = jQuery('#'+gblGrdid).jqGrid('getGridParam', 'colNames').length;
		var colIds  = jQuery('#'+gblGrdid).jqGrid('getGridParam', 'colNames');		
		var pendingCol =  jQuery('#txtPendingcolor').val();
		var workCol =  jQuery('#txtWIPColor').val();
		var completedCol =  jQuery('#txtCompColor').val();
		var planCol =  jQuery('#txtPlanColor').val();		
		for(i=3;i<=rowId.length;i++)	
		{	
			for (j=6;j<= countCols-1;j++) {							
				if(jQuery("#"+gblGrdid).getCell(i, 'Plan/Actual5').indexOf("PLAN")>=0)
				{
					var pa =jQuery("#"+gblGrdid).getCell(i, 'Plan/Actual5');
					var col = (planCol!= null && planCol != '' && planCol != ' ')?planCol:'#fee9bf';
					var planEmpCol = '#ffffff';		 
					if(url.toLowerCase().indexOf(pa.toLowerCase())<0)
					{
						col = planEmpCol = '#ecb4bc';
						var k= 0;						
						while(parseInt(k)<6)
						{							
							jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[k]+k,'',{'background-color':col} );
							k++;
						}
						col = (planCol!= null && planCol != '' && planCol != ' ')?planCol:'#fee9bf';
						planEmpCol = '#ffffff';	
					}
					if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("0")>=0)
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,' ',{'background-color':planEmpCol});						
					else if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("*")>=0)
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,'*',{'background-color':col,'color':'black','font-size': '13','font-weight' : 'bold'} );
					else
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,' ',{'background-color':col} );			
				}
				else if(jQuery("#"+gblGrdid).getCell(i, 'Plan/Actual5').indexOf("ACTUAL")>=0)
				{
					var pa =jQuery("#"+gblGrdid).getCell(i, 'Plan/Actual5');
					var pendCol = (pendingCol!= null && pendingCol != '' && pendingCol != ' ')?pendingCol:'#00ffda';
					var progCol = (workCol!= null && workCol != '' && workCol != ' ')?workCol:'#059780';
					var compCol = (completedCol!= null && completedCol != '' && completedCol != ' ')?completedCol:'#f19e3d';
					var empCol = '#ffffff';		 
					if(url.toLowerCase().indexOf(pa.toLowerCase())<0)
					{
						pendCol = empCol =compCol=progCol= '#ecb4bc';
						var k= 0;						
						while(parseInt(k)<6)
						{							
							jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[k]+k,'',{'background-color':pendCol} );
							k++;
						}
						 pendCol = (pendingCol!= null && pendingCol != '' && pendingCol != ' ')?pendingCol:'#00ffda';
						 progCol = (workCol!= null && workCol != '' && workCol != ' ')?workCol:'#059780';
						 compCol = (completedCol!= null && completedCol != '' && completedCol != ' ')?completedCol:'#f19e3d';
						 empCol = '#ffffff';		
					}
					
					var val = ' ';
					if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("*")>=0)
						val = '*';
					if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("C")>=0)
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,val,{'background-color':compCol,'color':'black','font-size': '13','font-weight' : 'bold'} );
					else if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("W")>=0)
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,val,{'background-color':progCol,'color':'black','font-size': '13','font-weight' : 'bold'} );
					else if(jQuery("#"+gblGrdid).getCell(i, colIds[j]+j).indexOf("P")>=0)
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,val,{'background-color':pendCol,'color':'black','font-size': '13','font-weight' : 'bold'} );
					else
						jQuery("#"+gblGrdid).jqGrid('setCell',i,colIds[j]+j,val,{'background-color':empCol,'color':'black','font-size': '13','font-weight' : 'bold'} );
				}
			}
		}
	}	
