<%@ page import="models.FlashCard" %>
<%@ page import="java.util.List" %>
<%@ page import="models.FlashCardSet" %><%--
  Created by IntelliJ IDEA.
  User: artempenkovskij
  Date: 16.07.23
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flash Card Sets</title>
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
<h1>List of Flash Card Sets</h1>
<% List<FlashCardSet> sets = (List<FlashCardSet>) request.getAttribute("sets");
%>

<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Count of Cards</th>
        <th>Count of Learned Card</th>
        <th>Let's study</th>
    </tr>
    </thead>
    <tbody>
    <% for (FlashCardSet flashCardSet : sets) {%>

    <tr>
        <td><%=flashCardSet.getTitle()%>
        </td>
        <td><%=flashCardSet.getCountOfCard()%>
        </td>
        <td><%=flashCardSet.getCountOfLearnedCard()%>
        </td>
        <td>
            <form action="/getflashcards" method="get">
                <input type="hidden" name="flashCardSetId" value=<%=flashCardSet.getId()%>>
                <input type="submit" value="Study">

            </form>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<br>
<h1>Add flash card set</h1>
<form action="/add/cardSet" method="post">
    Name of set <input type="text" name="title">
    <input type="submit" value="Add set">
</form>
<br>
<h1>Add flash card to set</h1>
<form action="/add/card" method="post">
    <select id="category" name="flashCardSet">
        <% for (FlashCardSet flashCardSet : sets) {%>
        <option value=<%=flashCardSet.getId()%>><%=flashCardSet.getTitle()%></option>
        <%}%>
    </select>
    Title <input type="text" name="title">
    Question <input type="text" name="question">
    Answer <input type="text" name="answer">
    <input type="submit" value="Add flash card">
</form>
</body>
</html>
