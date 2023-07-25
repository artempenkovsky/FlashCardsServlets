<%@ page import="models.FlashCard" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Flash Cards For Learning</title>
    <style>
        table {
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            font-weight: bold;
            text-align: left;
        }

        th:last-child, td:last-child {
            text-align: center;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        form {
            display: inline-block;
            margin-top: 20px;
        }

        input[type="submit"] {
            padding: 5px 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
<h1>Flash Cards For Learning</h1>
<% List<FlashCard> cards = (List<FlashCard>) request.getAttribute("cards");
    Long flashCardSetId = (Long) request.getAttribute("flashCardSetId");
%>
<table>
    <thead>
    <tr>
        <th>Question</th>
        <th>I know</th>
        <th>I don't know</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="flashCard" items="${cards}">
        <tr>
            <td>${flashCard.question}</td>
            <td>
                <form action="/getflashcards" method="post">
                    <input type="hidden" name="flashCardSetId" value=${flashCardSetId}>
                    <input type="hidden" name="knowledge" value="YES">
                    <input type="hidden" name="flashCardId" value=${flashCard.id}>
                    <input type="submit" value="I know">
                </form>
            </td>
            <td>
                <form action="/getflashcards" method="post">
                    <input type="hidden" name="flashCardSetId" value=${flashCardSetId}>
                    <input type="hidden" name="knowledge" value="NO">
                    <input type="hidden" name="flashCardId" value=${flashCard.id}>
                    <input type="submit" value="I don't know">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="/" method="get">
    <input type="submit" value="To main page">
</form>

</body>
</html>
