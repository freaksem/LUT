/**
 * Created by Luxoft on 18.01.2017.
 */
$(function(){
    currencyRefresh();
    setInterval(currencyRefresh, 5000);

    $("#amount-to-buy").bind('keyup mouseup', function () {
        var currencyToBuy = $("#buy-select").val();
        var currencyToSell = $("#sell-select").val();
        var exchangeSumm = $("#exchangeCost");
        if(currencyToBuy == 0) {
            exchangeSumm.html("").append("Валюта покупки не выбрана");
        }
        if(currencyToSell == 0) {
            exchangeSumm.html("").append("Валюта продажи не выбрана");
        }
        if(currencyToBuy != 0 && currencyToSell !=0) {
            var exchangeRate = $("input[name='"+currencyToSell+"/"+currencyToBuy+"']").val();
            exchangeSumm.html(exchangeRate * $(this).val());
        }
    });

    $('select').change(function() {
        var value = $(this).val();
        $(this).siblings('select.exchange-select').children('option').each(function() {
            if ( $(this).val() === value ) {
                $(this).attr('disabled', true).siblings().removeAttr('disabled');
            }
        });
    });
});
function currencyRefresh() {
    $.ajax({
        url : 'rateRefresh',
        success : function(data) {
            var ratesContent="";
            for (var key in data) {
                if (data.hasOwnProperty(key)) {
                    var currencyCost = data[key];
                    var currencyId = key.replace("/", "");
                    var previousCost = $("input[name='"+key+"']").val();
                    var change = "";
                    if(previousCost != undefined) {
                        if(currencyCost == previousCost) {
                            change = $("#"+currencyId).attr("class");
                        }
                        else if(currencyCost > previousCost) change = "up";
                        else change = "down";
                    }


                    ratesContent += "<p id='" + currencyId + "' class='"+change+"'>" + key + ": " + currencyCost + "</p>";
                    ratesContent += "<input type='hidden' name='"+key+"' value='" + currencyCost + "' />";
                }
            }
            $('#currRates').html(ratesContent);
        }
    });
}

$("#submit-operation").click(function(event) {
    event.preventDefault();
    $.post("operation", {
        currencyToBuyParam : $("#buy-select").val(),
        currencyToSellParam : $("#sell-select").val(),
        summToBuyParam : $("#amount-to-buy").val(),
        _csrf : $("input[name=_csrf]").val()
    }, function(data) {
    }).done(function() {
        location.reload();
    }).fail(function(xhr, textStatus, errorThrown) {
        $("#submit-operation").after("Недостаточно средств!");
    }).complete(function() {
    });
});