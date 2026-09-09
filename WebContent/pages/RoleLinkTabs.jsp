<!-- <div id="tabRoleLink" class="easyui-tabs" style="height:auto; width: 1110px; margin-top:0px;"> -->
<div id="tabRoleLink" class="easyui-tabs" style="height:auto; width: 100%; margin-top:0px;">

    <div title="Trade Role Link">
        <%@ include file="TradeRoleLink.jsp" %>
    </div>

    <div title="Pillar Role Link">
        <%@ include file="PillarRoleLink.jsp" %>
    </div>

</div>

<input type="hidden" id="submitForm" name="submitForm"/>

<script>
jQuery(document).ready(function()
{
    jQuery("#tabRoleLink").tabs(
    {
        onSelect: function(title)
        {
            var $tabs = jQuery("#tabRoleLink");

            if (title == "Trade Role Link")
            {
                jQuery('#submitForm').val('frmTradeRoleLink');
                if (!$tabs.data('trlInit'))
                {
                    initTradeRoleLinkTab();
                    $tabs.data('trlInit', true);
                }
            }
            else if (title == "Pillar Role Link")
            {
                jQuery('#submitForm').val('frmPillarRoleLink');
                if (!$tabs.data('prlInit'))
                {
                    initPillarRoleLinkTab();
                    $tabs.data('prlInit', true);
                }
            }
        }
    });

    jQuery('#submitForm').val('frmTradeRoleLink');
    initTradeRoleLinkTab();
    jQuery("#tabRoleLink").data('trlInit', true);
    
    // added by priyanka 
    jQuery("#tabRoleLink").on('mousedown click focusin', function(e)
    	    {
    	        var $frmTrade  = jQuery(e.target).closest('#frmTradeRoleLink');
    	        var $frmPillar = jQuery(e.target).closest('#frmPillarRoleLink');

    	        if ($frmTrade.length > 0)
    	        {
    	            jQuery('#submitForm').val('frmTradeRoleLink');
    	        }
    	        else if ($frmPillar.length > 0)
    	        {
    	            jQuery('#submitForm').val('frmPillarRoleLink');
    	        }
    	    });
    // end
});
</script>