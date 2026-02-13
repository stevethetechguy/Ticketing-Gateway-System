<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Raise New Ticket</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="card-title mb-0">Create a Support Ticket</h3>
        </div>
        <div class="card-body">
            <form id="ticketForm" enctype="multipart/form-data">

                <div class="row">
                    <div class="col-md-12 mb-3">
                        <label class="form-label">Ticket Title</label>
                        <input type="text" name="title" id="title" class="form-control" placeholder="Brief summary of the issue" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Category</label>
                        <select name="category" id="category" class="form-select">
                            <option value="Hardware">Hardware</option>
                            <option value="Software">Software</option>
                            <option value="Network">Network</option>
                            <option value="Access Request">Access Request</option>
                        </select>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Priority</label>
                        <select name="priority" id="priority" class="form-select">
                            <c:forEach items="${priorities}" var="p">
                                <option value="${p}">${p}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-12 mb-3">
                        <label class="form-label">Detailed Description</label>
                        <textarea name="description" id="description" class="form-control" rows="4" placeholder="Describe your problem in detail..."></textarea>
                    </div>

                    <div class="col-md-12 mb-4">
                        <label class="form-label">Attach File (Screenshots/Logs)</label>
                        <input type="file" name="ticketFile" id="ticketFile" class="form-control" />
                    </div>
                </div>

                <div class="card-footer text-end bg-transparent">
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Cancel</a>
                    <button type="button" id="btnSubmit" class="btn btn-success px-4">Submit Ticket</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        
        function submitTicket() {
			var formData = new FormData();
			    
			    // 2. Append the fields (Match the @RequestParam names in your Controller)
			    formData.append("title", $("#title").val());
			    formData.append("description", $("#description").val());
			    formData.append("category", $("#category").val());
			    formData.append("priority", $("#priority").val());
			    
			    // 3. Append the file (grabbing the first file from the input)
			    var fileInput = document.getElementById('ticketFile');
			    if (fileInput.files.length > 0) {
			        formData.append("ticketFile", fileInput.files[0]);
			    }

			    // 4. Perform the AJAX call
			    $.ajax({
			        url: "${pageContext.request.contextPath}/saveTicket",
			        type: "POST",
			        data: formData,
			        processData: false,  // Tell jQuery not to process the data
			        contentType: false,  // Tell jQuery not to set contentType (FormData does this)
			        success: function(response) {
			            alert("Success: " + response);
			            window.location.href = "${pageContext.request.contextPath}/home";
			        },
			        error: function(xhr, status, error) {
			            alert("Error: " + xhr.responseText);
			            console.log(error);
			        }
			    });
        }

        $('#btnSubmit').on('click', function() {
            submitTicket();
        });
    });
</script>

</body>
</html>