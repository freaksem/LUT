<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<spring:url value="/css/main.css" var="springCss" />
	<spring:url value="/js/jquery.js" var="jQuery" />
	<link href="${springCss}" rel="stylesheet" />

</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Spring Boot</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="/login">Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">

        <div class="starter-template">

            <p>Привет, ${loggedUserName}!</p>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Выход"/>
            </form>

        </div>
        <div class="row">
            <div class="balance-wrapper col-md-3">
                <h3>Баланс:</h3>
                <c:choose>
                    <c:when test="${not empty currencyBalances}">
                        <c:forEach items="${currencyBalances}" var="balance">
                            <c:forEach items="${balance}" var="entry">
                                <p>${entry.key}: ${entry.value}</p>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>

            <div class="rate-wrapper col-md-3">
                <h3>Курс валют</h3>
                <div id="currRates"></div>
            </div>

            <div class="exchange-wrapper col-md-6">
                <h3>Покупка валюты</h3>
                <form name="operationForm" action="/operation" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <label for="buy-select">Валюта к покупке</label>
                    <select class="exchange-select" id="buy-select" name="buy-select">
                        <option value="0">Выбрать</option>
                        <c:choose>
                            <c:when test="${not empty currencyList}">
                                <c:forEach items="${currencyList}" var="item">
                                    <option value="${item.currencyShortName}">${item.currencyShortName}</option>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </select>
                    <label for="amount-to-buy">Сколько:</label>
                    <input id="amount-to-buy" type="number" size="15"/>
                    <label for="sell-select">Покупаем за</label>
                    <select class="exchange-select" id="sell-select" name="sell-select">
                        <option value="0">Выбрать</option>
                        <c:choose>
                            <c:when test="${not empty currencyList}">
                                <c:forEach items="${currencyList}" var="item">
                                    <option value="${item.currencyShortName}">${item.currencyShortName}</option>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </select>
                    <p>Стоимость: <span id="exchangeCost">0</span></p>
                    <input type="hidden" value="" id="exchangeCurrencies" />
                    <button id="submit-operation">Купить</button>
                </form>
            </div>
        </div>

        <div class="row">
            <div class="operations-wrapper col-md-12">
                <h3>История операций</h3>
                <c:choose>
                    <c:when test="${not empty userOperations}">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr class="success">
                                <th>Покупка</th>
                                <th>Сумма</th>
                                <th>Оплата</th>
                                <th>Сумма</th>
                                <th>Курс</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userOperations}" var="item">
                                <tr>
                                    <td><c:out escapeXml="false" value="${item.currencyBuyId}"/></td>
                                    <td><c:out escapeXml="false" value="${item.currencyBuySumm}"/></td>
                                    <td><c:out escapeXml="false" value="${item.currencySellId}"/></td>
                                    <td><c:out escapeXml="false" value="${item.currencySellSumm}"/></td>
                                    <td><c:out escapeXml="false" value="${item.exchangeRate}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>Операции не найдены</p>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>

    <script type="text/javascript" src="${jQuery}"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
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
                console.log(data);
                location.reload();
            }).done(function() {
            }).fail(function(xhr, textStatus, errorThrown) {
                $("#submit-operation").after("Недостаточно средств!");
            }).complete(function() {
            });
        });

    </script>
</body>

</html>