<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			
			jQuery("#list").jqGrid({
				datatype: "json",
				url:"",
				colNames:['Detected Date',
				      	'Detected By',
				    	'Equipment',
				    	'Location',
				    	'Description',
				    	'Main Type',
				    	'Why(Reason)?',
				    	'Why it Become so ? (Cause)',
				    	'Counter Measure', 
				    	'Tag Classfication',
				    	'Completion Date',
				    	'# Days',
				    	'Lack Of Machine Guard',
				    	'Insufficient Illumination',
				    	'Operating Without Authority',
				    	'Operating At Unsafe Speed',
				    	'Making Safety Device Inoperative',
				    	'Using Equipment Unsafely',
				    	'Taking Unsafe Posture',
				    	'Fall Of Person	',
				    	'Fall Of Object, Stepping On, Striking Against Or Struck By O',
				    	'Caught In Between',
				    	'Exposure Or Contact',
				     	'To Extreme Temperature',
				    	'Exposure Or Contact To Fumes',
				    	'Exposure To Electric Current',
				    	'Exposure To Harmful Substance / Chemical / Radiation',
				    	'Fooling, Teasing Abusing Workmates',
				    	'Using Equipment Unsafely Or Limbs Instead Of Equipment Suppl',
				    	'Adjusting / Working On Or Near Moving Machinery',
				    	'Failure To Use Personal Protective Equipment',,
				    	'Component Failure',
				    	'Operator Negligience',
				    	'Process Not Ok',
				    	'Loose Connections Electrical System',
				    	'Loose Connections In Gas,Oil Pipeline',	
				    	'Effuluents',
				    	'Damage',
				    	'Gas Leak',
				    	'Fire',
				    	'Arranging Or Placing Object Unsafely',
				    	'Tooling / Equipment Failure',
				    	'Accident',
				    	'Machine Damage',
				    	'Property Damage - Infrasturcture, Resources',
				    	'Dangerous Occurences'],
				colModel:[ {name:'Location',index:'Location',editable:true,width:100},
				           {name:'DetectedDate',index:'DetectedDate',editable:true,width:100},
						   {name:'DetectedBy',index:'DetectedBy',editable:true,width:100},
				           {name:'Equipment',index:'Equipment',editable:true,width:100},
						   {name:'Location',index:'Location',editable:true,width:100},
				           {name:'Description',index:'Description',editable:true,width:100},
						   {name:'MainType',index:'MainType',editable:true,width:100},
				           {name:'WhyReason',index:'WhyReason',editable:true,width:100},
				        	{name:'WhyitBecomeso',index:'WhyitBecomeso',editable:true,width:100},
				        	{name:'CounterMeasure',index:'CounterMeasure',editable:true,width:100},
				        	{name:'TagClassfication',index:'TagClassfication',editable:true,width:100},
				        	{name:'CompletionDate',index:'CompletionDate',editable:true,width:100},
				        	{name:'Days',index:'Days',editable:true,width:100},
				        	{name:'LackOfMachineGuard',index:'LackOfMachineGuard',editable:true,width:100},
				        	{name:'InsufficientIllumination',index:'InsufficientIllumination',editable:true,width:100},
				        	{name:'OperatingWithoutAuthority',index:'OperatingWithoutAuthority',editable:true,width:100},
				        	{name:'OperatingAtUnsafeSpeed',index:'OperatingAtUnsafeSpeed',editable:true,width:100},
				        	{name:'MakingSafetyDeviceInoperative',index:'MakingSafetyDeviceInoperative',editable:true,width:100},
				        	{name:'UsingEquipmentUnsafely',index:'UsingEquipmentUnsafely',editable:true,width:100},
				        	{name:'TakingUnsafePosture',index:'TakingUnsafePosture',editable:true,width:100},
				        	{name:'FallOfPerson',index:'FallOfPerson',editable:true,width:100},
				        	{name:'FallOfObjectSteppingOnStrikingAgainstOrStruckByO',index:'FallOfObjectSteppingOnStrikingAgainstOrStruckByO',editable:true,width:100},
				        	{name:'CaughtInBetween',index:'CaughtInBetween',editable:true,width:100},
				        	{name:'ExposureOrContact',index:'ExposureOrContact',editable:true,width:100},
				        	{name:'ToExtremeTemperature',index:'ToExtremeTemperature',editable:true,width:100},
				        	{name:'ExposureOrContactToFumes',index:'ExposureOrContactToFumes',editable:true,width:100},
				        	{name:'ExposureToElectricCurrent',index:'ExposureToElectricCurrent',editable:true,width:100},
				        	{name:'ExposureToHarmfulSubstanceChemicalRadiation',index:'ExposureToHarmfulSubstanceChemicalRadiation',editable:true,width:100},
				        	{name:'FoolingTeasingAbusingWorkmates',index:'FoolingTeasingAbusingWorkmates',editable:true,width:100},
				        	{name:'UsingEquipmentUnsafelyOrLimbsInsteadOfEquipmentSuppl',index:'UsingEquipmentUnsafelyOrLimbsInsteadOfEquipmentSuppl',editable:true,width:100},
				        	{name:'AdjustingWorkingOnOrNearMovingMachinery',index:'AdjustingWorkingOnOrNearMovingMachinery',editable:true,width:100},
				        	{name:'FailureToUsePersonalProtectiveEquipment',index:'FailureToUsePersonalProtectiveEquipment',editable:true,width:100},
				        	{name:'ComponentFailure',index:'ComponentFailure',editable:true,width:100},
				        	{name:'OperatorNegligience',index:'OperatorNegligience',editable:true,width:100},
				        	{name:'ProcessNotOk',index:'ProcessNotOk',editable:true,width:100},
				        	{name:'LooseConnectionsElectricalSystem',index:'LooseConnectionsElectricalSystem',editable:true,width:100},
				        	{name:'LooseConnectionsInGasOilPipeline',index:'LooseConnectionsInGasOilPipeline',editable:true,width:100},
				        	{name:'Effuluents',index:'Effuluents',editable:true,width:100},
				        	{name:'Damage',index:'Damage',editable:true,width:100},
				        	{name:'GasLeak',index:'GasLeak',editable:true,width:100},
				        	{name:'Fire',index:'Fire',editable:true,width:100},
				        	{name:'ArrangingOrPlacingObjectUnsafely',index:'ArrangingOrPlacingObjectUnsafely',editable:true,width:100},
				        	{name:'ToolingEquipmentFailure',index:'ToolingEquipmentFailure',editable:true,width:100},
				        	{name:'Accident',index:'Accident',editable:true,width:100},
				        	{name:'MachineDamage',index:'MachineDamage',editable:true,width:100},
				        	{name:'PropertyDamageInfrasturctureResources',index:'PropertyDamageInfrasturctureResources',editable:true,width:100},
				        	{name:'DangerousOccurences',index:'DangerousOccurences',editable:true,width:100}
						 	],
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'HSE Abnormality',
								width:1000,
								height:350,
								//loadonce: true,
								
								footerrow: false,
								userDataOnFooter: false,
								gridComplete: function()
								{ 
									//jQuery("#list").jqGrid("setLabel","compName","New",{"text-align":"right"});
								}
							});
						});
		function viewGrid(actionPart,dataString)
		{
			jQuery("#list").setGridParam({url:actionPart+dataString,dataType: "json" }).trigger('reloadGrid');
		}
</script>

<div class="main-header">HSEAbnormality Report </div>

<div class="cntborder">

	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
		<div class="clearfix"></div>
</div>