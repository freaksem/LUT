<div class="rate-wrapper">
    <h3>Курс валют</h3>
    <c:choose>
        <c:when test="${not empty ruUsRate}">
            <c:forEach items="${ruUsRate}" var="entry">
                <p>${entry.key}: <span>${entry.value}</span></p>
            </c:forEach>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${not empty ruEuRate}">
            <c:forEach items="${ruEuRate}" var="entry">
                <p>${entry.key}: <span>${entry.value}</span></p>
            </c:forEach>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${not empty usEuRate}">
            <c:forEach items="${usEuRate}" var="entry">
                <p>${entry.key}: <span>${entry.value}</span></p>
            </c:forEach>
        </c:when>
    </c:choose>
</div>
