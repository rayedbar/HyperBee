<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: duity
  Date: 11/22/16
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<form:form action="/profile" method="post" commandName="profile">
    <table>
        <tr>
            <th>Designation :</th>
            <td><form:input path="designation" type="text"/></td>
        </tr>
        <tr>
            <th>Address :</th>
            <td><form:input path="address" type="text"/></td>
        </tr>
        <tr>
            <th>Contact No. :</th>
            <td><form:input path="contactNo" type="text"/></td>
        </tr>
        <tr>
            <th>Joining Date :</th>
            <td><form:input path="joiningDate" type="text"/></td>
        </tr>
        <tr>
            <th><h2>Education :</h2></th>
        </tr>
        <tr>
            <th>School :</th>
            <td><form:input path="school" type="text"/></td>
        </tr>
        <tr>
            <th>College :</th>
            <td><form:input path="college" type="text"/></td>
        </tr>
        <tr>
            <th>University :</th>
            <td><form:input path="university" type="text"/></td>
        </tr>
        <tr>
            <th><h2>Basic Information :</h2></th>
        </tr>
        <tr>
            <th>Gender :</th>
            <td><form:input path="gender" type="text"/></td>
        </tr>
        <tr>
            <th>Date Of Birth :</th>
            <td><form:input path="dateOfBirth" type="text"/></td>
        </tr>
        <tr>
            <th>Skills :</th>
            <td><form:input path="skills" type="text"/></td>
        </tr>
        <tr>
            <th><h2>Work Experience :</h2></th>
        </tr>
        <tr>
            <th>Years of Experience :</th>
            <td><form:input path="jobExperienceYears" type="text"/></td>
        </tr>
        <tr>
            <th>Image :</th>
            <td><form:input path="imagePath" type="text"/></td>
        </tr>
        <tr>
            <td colspan="2" align="left"><input type="submit" value="Create Profile"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
