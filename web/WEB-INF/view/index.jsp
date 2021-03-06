<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>AWS S3 Folders</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
        crossorigin="anonymous">
  <style>
    <%@include file="styles.css"%>
  </style>
</head>
<body>

<h1 align = "center">AWS S3 Operations</h1>
<p align = "center">Choose a folder to upload</p>

<div class="sm-padding">
  <div class="container justify-content-center">
    <div class="row">
      <div class ="col-md-6 offset-md-3">
        <div class="card w-100">
          <div class="card-body">
            <div class="page-form" >
              <form action = "${pageContext.request.contextPath}/serv?command=index"
                    method = "post" enctype = "multipart/form-data">
                <input type = "file" name = "file" webkitdirectory multiple/> <br />
                <input type = "submit" value = "Upload File" />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class ="col-md-10 offset-md-1">
        <div class="card h-100">
          <div class="card-body">
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Folder ID</th>
                <th scope="col"></th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${downloadFiles}" var="folder">
                <tr>
                  <td scope="col">${folder.getFolderName()}</td>
                  <td scope="col"><a href =${folder.getUrl()}>Download</a></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
