<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.ProductFeedbackData" %>
<%@ page import="model.Feedback" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Feedback</title>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="styles/product-feedback.css" type="text/css"/>
    <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!-- Updated Chart.js version -->
</head>
<body>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>

    <div class="container">
        <div class="feedback-header">
            <h1>Product Feedback Overview</h1>
        </div>
        <div class="product-feedback-info">
            <h2>${feedbackData.productName}</h2>
            <div class="feedback-summary">
                <div class="rating">
                    <p>Average Rating</p>
                    <h3>${feedbackData.averageRating}</h3>
                </div>
                <div class="feedback-count">
                    <p>Number of Feedbacks</p>
                    <h3>${feedbackData.numberOfFeedbacks}</h3>
                </div>
            </div>
            <div class="feedback-chart">
                <canvas id="ratingChart"></canvas>
            </div>
        </div>
    </div>

    <script>
        // Retrieve feedback JSON data
        const feedbackData = JSON.parse('${feedbackJson}');
        const ratingCounts = [0, 0, 0, 0, 0]; // Array to hold counts for each rating

        feedbackData.forEach(feedback => {
            const rating = Math.round(feedback.rating) - 1; // Adjust index for zero-based array
            if (rating >= 0 && rating < ratingCounts.length) {
                ratingCounts[rating]++;
            }
        });

        // Set up the chart
        const ctx = document.getElementById('ratingChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['1 Star', '2 Stars', '3 Stars', '4 Stars', '5 Stars'],
                datasets: [{
                    label: 'Number of Ratings',
                    data: ratingCounts,
                    backgroundColor: 'rgba(54, 162, 235, 0.6)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
    <script src="js/dashboard.js"></script>
</body>
</html>
