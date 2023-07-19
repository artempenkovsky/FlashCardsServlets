<%@ page import="models.FlashCard" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: artempenkovskij
  Date: 16.07.23
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flash Cards</title>
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
<h1>List of Flash Cards</h1>
<% List<FlashCard> cards = (List<FlashCard>) request.getAttribute("cards");
%>
<table>
    <thead>
    <tr>
        <th>Question</th>
        <th>Answer</th>
        <th>Learned</th>
    </tr>
    </thead>
    <tbody>
    <% for(FlashCard flashCard :cards){%>

    <tr>
        <td><%=flashCard.getQuestion()%></td>
        <td><%=flashCard.getAnswer()%></td>
        <td><%=flashCard.isLearned()%></td>
    </tr>
    <%}%>
    </tbody>
</table>

</body>
</html>
