<%@page import="model.Account"%>
<%@page import="dao.AccountDAO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DURIAN STORE</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/blog-detail.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
            rel="stylesheet"
            />
        <!-- Favicon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        
            <section class="container">
            <div class="blog-container">
                <div class="row">
                            <div class="col-lg-12 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="/post/add" method="post" onsubmit="" enctype="multipart/form-data">
                                        <div class="row">
                                            <div class="col-md-9">
                                                <div class="mb-3">
                                                    <label class="form-label">Title</label>
                                                    <input name="title" id="title" type="text" class="form-control " placeholder="Title :" value="${title}">   
                                                    <p class="text-danger">${errorTitle}</p>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <select class="form-control" name="status" required>
                                                        <option value="DRAFT">DRAFT</option>
                                                        <option value="PUBLISHED">PUBLISHED</option>
                                                        <option value="PRIVATE">PRIVATE</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6 col-lg-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Categories</label>
                                                    <ul id="category-list" name="category-list" class="list-group">
                                                    </ul>
                                                    <input type="text" id="category-input" class="form-control mt-2" placeholder="Add a category" onkeypress="addCategory(event)">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Content</label>
                                                    <textarea class="form-control" name="content" id="content" value="${content}"></textarea>
                                                    <div class="valid-feedback">
                                                        Please check again if this is the expected status.
                                                    </div>
                                                    <p class="text-danger">${errorContent}</p>
                                                </div>                                                                               
                                            </div><!--end col-->
                                        </div><!--end row-->

                                        <button type="submit" class="btn btn-primary" >Add Blog</button>

                                    </form>
                                </div>
                            </div><!--end col-->
                        </div>
            </div>
            <hr/>
        </section>


        <jsp:include page="../screens/footer.jsp"></jsp:include>


    </body>
</html>
