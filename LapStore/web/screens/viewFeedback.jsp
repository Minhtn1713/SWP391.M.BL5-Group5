<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lap Store - Feedback</title>
        <!-- CSS -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/related-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/view-feedback.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet"/>
        <!-- Favicon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link rel="stylesheet" href="styles/main.css" type="text/css"/> <!-- Added link to main.css -->
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>

            <div class="main-content">
                <h1>Feedback for Product</h1>

            <c:if test="${existingFeedback != null}">
                <div class="feedback-form">
                    <h2>Edit Your Feedback</h2>
                    <form action="feedback" method="post">
                        <input type="hidden" name="productId" value="${productId}" />
                        <input type="hidden" name="feedbackId" value="${existingFeedback.id}" />
                        <div class="form-group">
                            <label for="rating">Rating:</label>
                            <div class="rating">
                                <input type="radio" name="rating" id="star5" value="5" ${existingFeedback.rating == 5 ? 'checked' : ''} />
                                <label for="star5" title="5 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star4" value="4" ${existingFeedback.rating == 4 ? 'checked' : ''} />
                                <label for="star4" title="4 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star3" value="3" ${existingFeedback.rating == 3 ? 'checked' : ''} />
                                <label for="star3" title="3 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star2" value="2" ${existingFeedback.rating == 2 ? 'checked' : ''} />
                                <label for="star2" title="2 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star1" value="1" ${existingFeedback.rating == 1 ? 'checked' : ''} />
                                <label for="star1" title="1 star">&#9733;</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="feedback">Your Feedback:</label>
                            <textarea name="feedback" id="feedback" required>${existingFeedback.comment}</textarea>
                        </div>
                        <div class="form-group">
                            <label>
                                <input type="checkbox" name="anonymous" ${existingFeedback.anonymous ? 'checked' : ''}> Post Anonymously
                            </label>
                        </div>

                        <button type="submit" class="submit-btn">Update Feedback</button>
                    </form>
                </div>
            </c:if>

            <c:if test="${existingFeedback == null}">
                <div class="feedback-form">
                    <h2>Leave Your Feedback</h2>
                    <form action="feedback" method="post">
                        <input type="hidden" name="productId" value="${productId}" />
                        <div class="form-group">
                            <label for="rating">Rating:</label>
                            <div class="rating">
                                <input type="radio" name="rating" id="star5" value="5" />
                                <label for="star5" title="5 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star4" value="4" />
                                <label for="star4" title="4 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star3" value="3" />
                                <label for="star3" title="3 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star2" value="2" />
                                <label for="star2" title="2 stars">&#9733;</label>
                                <input type="radio" name="rating" id="star1" value="1" />
                                <label for="star1" title="1 star">&#9733;</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="feedback">Your Feedback:</label>
                            <textarea name="feedback" id="feedback" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>
                                <input type="checkbox" name="anonymous"> Post Anonymously
                            </label>
                        </div>
                        <button type="submit" class="submit-btn">Submit Feedback</button>
                    </form>
                </div>
            </c:if>
        </div>

        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
