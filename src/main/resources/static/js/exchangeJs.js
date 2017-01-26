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

    var $selects = $('select');
    $selects.on('change', function() {
        var $select = $(this),
            $options = $selects.not($select).find('option'),
            selectedText = $select.children('option:selected').text();
        $options.children('option').each(function() {
           $(this).removeAttr('disabled');
        });
        var $optionsToDisable = $options.filter(function() {
            return $(this).text() == selectedText;
        });
        var $optionsToEnable = $options.filter(function() {
            return $(this).text() != selectedText;
        });
        $optionsToDisable.prop('disabled', true);
        $optionsToEnable.prop('disabled', false);
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
                        else if(currencyCost > previousCost) change = "currencies-rate__rate_green";
                        else change = "currencies-rate__rate_red";
                    }


                    ratesContent += "<p id='" + currencyId + "' class='currencies-rate__rate "+change+"'>" + key + ": " + currencyCost + "</p>";
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
        _csrf : getCookie("XSRF-TOKEN")
    }, function(data) {
    }).done(function() {
        location.reload();
    }).fail(function(xhr, textStatus, errorThrown) {
        $("#submit-operation").after("Недостаточно средств!");
    }).complete(function() {
    });
});

function getCookie(name) {
    var cookie = " " + document.cookie;
    var search = " " + name + "=";
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0) {
        offset = cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = unescape(cookie.substring(offset, end));
        }
    }
    return(setStr);
}