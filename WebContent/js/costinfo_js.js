jQuery.noConflict();
				jQuery(document).ready(function(){
							
						fillComboBox("frmCostInfo","cmbspcnFactoryid","factroyCombo.commonFilter" );
						fillComboBox("frmCostInfo","cmbspcnSectionid","sectionCombo.commonFilter" );						
						fillComboBox("frmCostInfo","cmbspcnCellid","cellCombo.commonFilter" );						
						fillComboBox("frmCostInfo","cmbspcnMachineid","machineCombo.commonFilter" );
						fillComboBox("frmCostInfo","cmbspcnAssemblyid","assembly.commonFilter");
						fillComboBox("frmCostInfo","cmbspcnTrade","trade.commonFilter" );						
						fillComboBox("frmCostInfo","cmbPhenomena","combo_phenomena.crt");
						fillComboBox("frmCostInfo","cmbCause","combo_cause.crt");
						
						//empcost 
						fillComboBox("frmCostInfo","cmbmpcpManpowerid","employee.commonFilter");
						fillComboBox("frmCostInfo","cmbmpcpSkillid","combo_empgrade.crt");
						
						//contractorcost 
						fillComboBox("frmCostInfo","cmbccVendor","combo_vendor.crt");
						fillComboBox("frmCostInfo","cmbccContractor","combo_contractor.crt");
						
						//sparecost
						fillComboBox("frmCostInfo","cmbscSpare","spareCombo.commonFilter" );						
						fillComboBox("frmCostInfo","cmbscRequestedby","employee.commonFilter" );
						
						//servicecost
						fillComboBox("frmCostInfo","cmbscService","combo_service.crt");
						//utilcost
						fillComboBox("frmCostInfo","cmbucUtilities","combo_utilities.crt");
						fillComboBox("frmCostInfo","cmbucRequestedby","employee.commonFilter" );
						//othercost
						fillComboBox("frmCostInfo","cmbocExpense","combo_expense.crt");
						fillComboBox("frmCostInfo","cmbocRequestedby","employee.commonFilter" );
						
						
						//fillComboBox("frmCostInfo","cmbbdmsSpareid","spareCombo.commonFilter");
		        		//fillComboBox("frmCostInfo","cmbbdmsSubassemblyid","subassemblyCombo.commonFilter");
		        		//fillComboBox("frmCostInfo","cmbbdmsFinalcause","combo_cause.brdn");		
				
						formatDateBox('dteplanDate','dd-MMM-yyyy');
						formatDateBox('dteworkStart','dd-MMM-yyyy');
						formatDateBox('dteworkEnd','dd-MMM-yyyy');
						
						formatDateBox('dteempDate','dd-MMM-yyyy');						
						formatDateBox('dteccDate','dd-MMM-yyyy');						
						formatDateBox('dtescBilldt','dd-MMM-yyyy');						
						formatDateBox('dteucDate','dd-MMM-yyyy');
						formatDateBox('dteocDate','dd-MMM-yyyy');
						
						//fillWithCurrentDate('dtebdmsReporteddate');
						//processGridnew('costsummary_view.crt',downTimedata,"DwnTmeBrkupGrid","DwnTmeBrkupPager","","","","downTimeGrid","");
						
						processGridnew('costSummary_view.crt',"?q=2","costSumryGrid","costSumryPager","","","","costGridLoad","");
						
						processGridnew('empCostEstimation_view.crt',"?q=2","empCostEstGrid","empCostEstPager","","","","costGridLoad","");						
						processGridnew('empCostActual_view.crt',"?q=2","empCostActGrid","empCostActPager","","","","costGridLoad","");
						processGridnew('empCostManPower_view.crt',"?q=2","empCostManPowerGrid","empCostManPowerPager","","","","costGridLoad","");

						processGridnew('contracotCostEstimation_view.crt',"?q=2","contracotCostEstGrid","contracotCostEstPager","","","","costGridLoad","");						
						processGridnew('contracotCostActual_view.crt',"?q=2","contracotCosttActGrid","contracotCostActPager","","","","costGridLoad","");
						processGridnew('contracotCostManPower_view.crt',"?q=2","contracotCostManPowerGrid","contracotCostManPowerPager","","","","costGridLoad","");

						processGridnew('spareCostEstimation_view.crt',"?q=2","spareCostEstGrid","spareCostEstPager","","","","costGridLoad","");						
						processGridnew('spareCostActual_view.crt',"?q=2","spareCostActGrid","spareCostActPager","","","","costGridLoad","");
						
						processGridnew('serviceCostEstimation_view.crt',"?q=2","serviceCostEstGrid","serviceCostEstPager","","","","costGridLoad","");						
						processGridnew('serviceCostActual_view.crt',"?q=2","serviceCostActGrid","serviceCostActPager","","","","costGridLoad","");

						processGridnew('utilCostEstimation_view.crt',"?q=2","utilCostEstGrid","utilCostEstPager","","","","costGridLoad","");						
						processGridnew('utilCostActual_view.crt',"?q=2","utilCostActGrid","utilCostActPager","","","","costGridLoad","");

						processGridnew('otherCostEstimation_view.crt',"?q=2","otherCostEstGrid","otherCostEstPager","","","","costGridLoad","");						
						processGridnew('otherCostActual_view.crt',"?q=2","otherCostActGrid","otherCostActPager","","","","costGridLoad","");

												
				});

	function costGridLoad()
	{	//alert("loadeddddd");
		
	}