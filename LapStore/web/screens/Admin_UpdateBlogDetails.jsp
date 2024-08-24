<%@ page import="model.Blog, model.SubBlog, java.util.List" %>
<%@ page import="dao.BlogDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Blog</title>
    <link rel="stylesheet" href="styles/create-blog.css" type="text/css"/>
    <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <section class="product">
        <div class="top">
            <div class="search-box">
                <i class="bx bx-search icon"></i>
                <input type="text" placeholder="Search here..." />
            </div>
            <h3 class="icon profile">Hi, Admin</h3>
        </div>
        <div class="add-container">
            <div class="overview">
                <div class="title">
                    <i class='bx bx-news icon'></i>
                    <span class="text">Edit Blog</span>
                </div>
                <div class="boxes">
                    <div class="add-form">
                        <form class="add-product" action="admin-update-blog" method="POST"">
                            <input type="hidden" name="blogId" value="${blog.id}"/>
                            <input type="hidden" name="quantitySub" id="quantitySub" value="${subBlogs.size()}"/>
                            <label for="cate">Blog Category</label><br />
                            <select id="cate" name="cate" style="width: 100%; border-radius: 10px; outline: none; padding: 7px">
                                <c:forEach items="${listCate}" var="lc">
                                    <option value="${lc.id}" ${lc.id == blog.categoryId ? 'selected' : ''}>${lc.name}</option>
                                </c:forEach>
                            </select><br />
                            <label for="title">Title</label><br />
                            <input type="text" id="title" name="title" value="${blog.title}" required /><br />
                            <label for="cover">Cover image</label><br />
                            <input type="file" id="cover" name="cover"/><br />
                            <label for="price">Sub Content for Blog</label><br />
                            <div id="sub-content">
                                <c:forEach items="${subBlogs}" var="sub">
                                    <label for="header${sub.id}">Sub Content Header ${sub.id}</label><br />
                                    <input type="text" id="title${sub.id}" name="title${sub.id}" value="${sub.title}" required /><br />
                                    <label for="content${sub.id}">Sub Content ${sub.id}</label><br />
                                    <textarea id="content${sub.id}" name="content${sub.id}">${sub.content}</textarea><br />
                                    <label for="img${sub.id}">Sub Image ${sub.id}</label><br />
                                    <input type="file" id="img${sub.id}" name="img${sub.id}" /><br />
                                </c:forEach>
                            </div>
                            <div class="plus-container">
                                <span id="plus">+</span>
                            </div>
                            <button type="submit" id="update-btn">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="js/dashboard.js"></script>
    <script>
        const plus = document.getElementById("plus");
        const subContent = document.getElementById("sub-content");
        let index = ${subBlogs.size()};
        const loadSubContent = (text) => {
            subContent.innerHTML = text;
        };

        plus.addEventListener("click", () => {
            index += 1;
            let content = `<label for="header`+index+`">Sub Content Header ` + index;
            content += `</label><br />
                <input type="text" id="header`+index+`" name="header`+index+`" required /><br />
                <label for="content`+index+`">Sub Content `+index+`</label><br />
                <textarea id="content`+index+`" name="content`+index+`"></textarea><br />
                <label for="img`+index+`">Sub Image `+index+`</label><br />
                <input type="file" id="img`+index+`" name="img`+index+`"/><br />`;
            const quantitySub = document.getElementById("quantitySub");
            quantitySub.value = index;
            loadSubContent(subContent.innerHTML + content);
        });
    </script>
</body>
</html>
