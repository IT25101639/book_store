<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.source.model.Author" %>
<!DOCTYPE html>
<html>
<head>
    <title>Author List – Book Store</title>
    <style>
        * { box-sizing: border-box; }
        body { font-family: Arial, sans-serif; margin: 0; background: #f5f7fa; color: #333; }
        .container { max-width: 860px; margin: 40px auto; padding: 0 20px; }
        h1 { color: #2c7a2c; margin-bottom: 4px; }
        h2 { color: #555; font-weight: normal; margin-top: 0; margin-bottom: 20px; }
        .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; }
        .btn-add {
            display: inline-block; padding: 9px 18px;
            background: #4CAF50; color: white; border-radius: 4px;
            text-decoration: none; font-weight: bold; font-size: 14px;
        }
        .btn-add:hover { background: #388e3c; }
        .msg { padding: 10px 16px; border-radius: 4px; margin-bottom: 16px; font-weight: bold; }
        .msg.success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #4CAF50; }
        .msg.error   { background: #fdecea; color: #c62828; border-left: 4px solid #e53935; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 6px; overflow: hidden; box-shadow: 0 2px 6px rgba(0,0,0,0.08); }
        th { background: #4CAF50; color: white; padding: 12px 14px; text-align: left; }
        td { padding: 11px 14px; border-bottom: 1px solid #eee; }
        tr:last-child td { border-bottom: none; }
        tr:hover td { background: #f9fbe7; }
        .empty { text-align: center; padding: 30px; color: #888; }
        .action-btn {
            display: inline-block; padding: 5px 12px; border-radius: 3px;
            text-decoration: none; font-size: 13px; font-weight: bold;
        }
        .btn-edit  { background: #1976d2; color: white; }
        .btn-edit:hover  { background: #1565c0; }
        .btn-delete { background: #e53935; color: white; margin-left: 6px; }
        .btn-delete:hover { background: #c62828; }

        /* Modal */
        .modal-overlay {
            display: none; position: fixed; inset: 0;
            background: rgba(0,0,0,0.45); z-index: 100;
            align-items: center; justify-content: center;
        }
        .modal-overlay.active { display: flex; }
        .modal {
            background: white; border-radius: 8px; padding: 28px 32px;
            max-width: 380px; width: 90%; box-shadow: 0 8px 30px rgba(0,0,0,0.2);
        }
        .modal h3 { margin-top: 0; color: #c62828; }
        .modal-actions { display: flex; gap: 10px; margin-top: 20px; justify-content: flex-end; }
        .modal-actions button {
            padding: 8px 18px; border: none; border-radius: 4px;
            cursor: pointer; font-size: 14px; font-weight: bold;
        }
        .btn-cancel-modal { background: #e0e0e0; color: #333; }
        .btn-cancel-modal:hover { background: #bdbdbd; }
        .btn-confirm-delete { background: #e53935; color: white; }
        .btn-confirm-delete:hover { background: #c62828; }
    </style>
</head>
<body>
<div class="container">
    <h1>📚 Book Store</h1>
    <h2>Author Management</h2>

    <%
        String msg = (String) request.getAttribute("message");
        String msgType = (String) request.getAttribute("messageType");
        if (msg != null) {
    %>
    <div class="msg <%= msgType != null ? msgType : "success" %>"><%= msg %></div>
    <% } %>

    <div class="toolbar">
        <strong>All Authors</strong>
        <a class="btn-add" href="AuthorServlet?action=new">+ Add New Author</a>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Author Name</th>
            <th>Email</th>
            <th style="width:160px">Actions</th>
        </tr>
        <%
            List<Author> authors = (List<Author>) request.getAttribute("authors");
            if (authors == null || authors.isEmpty()) {
        %>
        <tr><td colspan="4" class="empty">No authors found. Add your first one!</td></tr>
        <%
            } else {
                for (Author a : authors) {
        %>
        <tr>
            <td><%= a.getId() %></td>
            <td><%= a.getAuthorName() %></td>
            <td><%= a.getEmail() %></td>
            <td>
                <a class="action-btn btn-edit"
                   href="AuthorServlet?action=edit&id=<%= a.getId() %>">Edit</a>
                <a class="action-btn btn-delete"
                   href="#"
                   onclick="confirmDelete(<%= a.getId() %>, '<%= a.getAuthorName().replace("'", "\\'") %>'); return false;">Delete</a>
            </td>
        </tr>
        <% } } %>
    </table>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal-overlay" id="deleteModal">
    <div class="modal">
        <h3>⚠ Confirm Delete</h3>
        <p>Are you sure you want to delete <strong id="deleteAuthorName"></strong>?<br>
           This action cannot be undone.</p>
        <div class="modal-actions">
            <button class="btn-cancel-modal" onclick="closeModal()">Cancel</button>
            <button class="btn-confirm-delete" onclick="doDelete()">Yes, Delete</button>
        </div>
    </div>
</div>

<script>
    var deleteId = null;
    function confirmDelete(id, name) {
        deleteId = id;
        document.getElementById('deleteAuthorName').textContent = name;
        document.getElementById('deleteModal').classList.add('active');
    }
    function closeModal() {
        document.getElementById('deleteModal').classList.remove('active');
        deleteId = null;
    }
    function doDelete() {
        if (deleteId) window.location.href = 'AuthorServlet?action=delete&id=' + deleteId;
    }
    // Close on overlay click
    document.getElementById('deleteModal').addEventListener('click', function(e) {
        if (e.target === this) closeModal();
    });
</script>
</body>
</html>
