<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Resolve Tickets</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <h2 class="mb-4">Tickets Ready for Resolution</h2>

    <div class="table-responsive bg-white shadow-sm p-3 rounded">
        <table class="table table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Priority</th>
                    <th>Resolve</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${assignedTickets}" var="ticket">
                    <tr>
                        <td>${ticket.id}</td>
                        <td>${ticket.title}</td>
                        <td>${ticket.description}</td>
                        <td>${ticket.priority}</td>
                        <td>
                            <button class="btn btn-success btn-sm"
                                    onclick="openResolveModal(${ticket.id})">
                                Resolve
                            </button>
							<button class="btn btn-primary btn-sm"
							        onclick="openTicketHistoryeModal(${ticket.id})">
							    History
							</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- RESOLVE MODAL -->
<div class="modal" id="resolveModal" style="background: rgba(0,0,0,0.5);">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Resolve Ticket</h5>
                <button type="button" class="btn-close" onclick="closeResolveModal()"></button>
            </div>

            <div class="modal-body">
                <input type="hidden" id="resolveTicketId">

                <div class="mb-3">
                    <label class="form-label">Resolution Notes</label>
                    <textarea id="resolutionNotes"
                              class="form-control"
                              rows="3"
                              placeholder="Describe how the issue was resolved"></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Attach Resolution PDF</label>
                    <input type="file" id="resolutionPdf" class="form-control" accept="application/pdf">
                </div>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" onclick="closeResolveModal()">Cancel</button>
                <button class="btn btn-success" onclick="submitResolution()">Resolve Ticket</button>
            </div>

        </div>
    </div>
</div>
<!-- ================= TICKET HISTORY MODAL ================= -->
<div class="modal" id="ticketHistoryModal" style="background: rgba(0,0,0,0.5);">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Ticket History</h5>
                <button type="button" class="btn-close" onclick="closeTicketHistoryModal()"></button>
            </div>

            <div class="modal-body" style="max-height: 400px; overflow-y: auto;">
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>Action</th>
                            <th>Performed By</th>
                            <th>Date</th>
                            <th>Comments</th>
                        </tr>
                    </thead>
                    <tbody id="ticketHistoryBody">
                        <!-- Ticket history rows will be dynamically inserted here -->
                    </tbody>
                </table>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" onclick="closeTicketHistoryModal()">Close</button>
            </div>

        </div>
    </div>
<script>
function openResolveModal(ticketId) {
    $("#resolveTicketId").val(ticketId);
    $("#resolutionNotes").val("");
    $("#resolutionPdf").val("");
    $("#resolveModal").show();
}

function closeResolveModal() {
    $("#resolveModal").hide();
}

function submitResolution() {
	/*
	var fileInput = document.getElementById('ticketFile');
	if (fileInput.files.length > 0) {
	    formData.append("ticketFile", fileInput.files[0]);
	}
	*/
    var formData = new FormData();
    formData.append("ticketId", $("#resolveTicketId").val());
    formData.append("notes", $("#resolutionNotes").val());

    if ($("#resolutionPdf")[0].files.length > 0) {
        formData.append("pdf", $("#resolutionPdf")[0].files[0]);
    }

    $.ajax({
        url: "${pageContext.request.contextPath}/resolveTicket",
        type: "PUT",
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            alert("Ticket resolved successfully");
            window.location.reload();
        },
        error: function (xhr) {
            alert("Error resolving ticket: " + xhr.responseText);
        }
    });
}

/* ===== TICKET HISTORY ===== */
function openTicketHistoryeModal(ticketId) {
    // Clear previous data
    $("#ticketHistoryBody").empty();
	console.log(ticketId);
    // Make AJAX call to fetch ticket history
    $.ajax({
        url: "${pageContext.request.contextPath}/ticketHistory/" + ticketId,
        type: "GET",
        success: function(data) {
            if(data && data.length > 0){
                data.forEach(function(history){
					console.log(history.action)
					console.log(history.actionBy.name)
                    let row = `<tr>
                        <td>\${history.action}</td>
                        <td>\${history.actionBy ? history.actionBy.name : 'N/A'}</td>
						<td>\${new Date(history.actionDate).toLocaleString()}</td>
                        <td>\${history.comments ? history.comments : ''}</td>
                    </tr>`;
                    $("#ticketHistoryBody").append(row);
                });
            } else {
                $("#ticketHistoryBody").append('<tr><td colspan="4" class="text-center">No history found</td></tr>');
            }
            $("#ticketHistoryModal").show();
        },
        error: function() {
            alert("Failed to fetch ticket history.");
        }
    });
}
function closeTicketHistoryModal() {
    $("#ticketHistoryModal").hide();
}
</script>

</body>
</html>
