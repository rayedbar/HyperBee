<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>Dashboard</title>
    <script>
        function refreshList() {
            $("#buzzList").load("/buzz/buzzList")
        }

        $(document).ready(function () {
            $("#buzzList").load("/buzz/buzzList")
            setInterval(refreshList, 5000);
        });
    </script>
</head>

<body>
<div class="container-fluid">
    <div id="buzzMain" class="panel panel-primary">
        <div class="panel-heading"><h3 style="font-family:'Helvetica Neue'"><b><i>Buzz!</i></b></h3></div>
        <div class="panel-body">
            <div id="buzzList"></div>
            <form:form id="buzzMessageForm" action="/buzz/sendBuzz" method="POST" modelAttribute="newBuzz">
                <form:input path="message" placeholder="Enter your message..." cssStyle="width: 94%"/>
                <input type="submit" class="btn btn-primary" value="Send"/>

                <a href="/buzz/buzzHistory?prev=0&next=20" target="_blank">Show Buzz History</a>

                <p><form:errors path="message" cssClass="alert-danger"/></p>
            </form:form>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${topStickyNote}" var="item">
            <div class="container-fluid col-lg-3">
                <div class="panel panel-warning">
                    <div class="panel-heading clearfix">
                        <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><strong>${item.title}</strong>
                        </h4>
                    </div>
                    <div class="panel-body">${item.description}</div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right">
                            <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>