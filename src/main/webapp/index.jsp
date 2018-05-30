<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/customers.js" var="customersScriptUrl"/>
        <c:url value="/employees.js" var="employeesScriptUrl"/>
        <c:url value="/artists.js" var="artistsScriptUrl"/>
        <c:url value="/album.js" var="albumScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/back-to-profile.js" var="backToProfileScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${customersScriptUrl}"></script>
        <script src="${employeesScriptUrl}"></script>
        <script src="${artistsScriptUrl}"></script>
        <script src="${albumScriptUrl}"></script>
        <script src="${indexScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${backToProfileScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <title>App</title>
    </head>
<body>
<div id="login-content" class="content">
    <h1>Login</h1>
    <form id="login-form" onsubmit="return false;">
        <input type="text" name="email" placeholder="Type your email">
        <button id="login-button">Login</button>
    </form>
</div>
<div id="profile-content" class="hidden content">
    <h3>Welcome, <span id="user-firstname"></span>!</h3>
    <p>Email: <span id="user-email"></span></p>

</div>

<div id="back-to-profile-content" class="hidden content">
    <button onclick="onBackToProfileClicked();">Back to profile</button>
</div>

<div id="customers" class="hidden">
    <h2>List of the Customers</h2>
    </div>

<div id="employees" class="hidden">
    <h2>List of the Employees</h2>
    </div>

<div id="artists" class="hidden">
    <h2>List of the Available Artists</h2>
    </div>

<div id="addalbumtoartist" class="hidden content">
    <h2>You can add new album an existing Artists</h2>
    <input type= "text" id="album" placeholder="Please type the title of the album"><br><br>
    <input type= "text" id="artist" placeholder="Please type the name of the artist">
    <button id="submit">Submit</button>

    </div>


<div id="logout-content" class="hidden content">
    <button id="logout-button">Logout</button>
    <button id="listcust-button">List Customers</button>
    <button id="listempl-button">List Employees</button>
    <button id="listartist-button">List Artists</button>
    <button id="addalbumtoartist-button">Add Album to Artist</button>
</div>
</body>
</html>
