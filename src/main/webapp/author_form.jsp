<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.source.model.Author" %>
<!DOCTYPE html>
<html>
<head>
    <title>Author Form – Book Store</title>
    <style>
        * { box-sizing: border-box; }
        body { font-family: Arial, sans-serif; margin: 0; background: #f5f7fa; color: #333; }
        .container { max-width: 480px; margin: 60px auto; padding: 0 20px; }
        h1 { color: #2c7a2c; margin-bottom: 4px; }
        .card {
            background: white; border-radius: 8px; padding: 32px 36px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.1); margin-top: 24px;
        }
        .card h2 { margin-top: 0; color: #555; }
        label { display: block; font-weight: bold; margin-bottom: 5px; font-size: 14px; }
        input[type=text], input[type=email] {
            width: 100%; padding: 9px 12px; border: 1px solid #ccc;
            border-radius: 4px; font-size: 14px; margin-bottom: 18px;
            transition: border-color 0.2s;
        }
        input[type=text]:focus, input[type=email]:focus {
            outline: none; border-color: #4CAF50;
            box-shadow: 0 0 0 3px rgba(76,175,80,0.15);
        }
        .form-actions { display: flex; gap: 12px; margin-top: 8px; }
        .btn-submit {
            flex: 1; padding: 10px; background: #4CAF50; color: white;
            border: none; border-radius: 4px; font-size: 15px;
            font-weight: bold; cursor: pointer;
        }
        .btn-submit:hover { background: #388e3c; }
        .btn-cancel {
            flex: 1; padding: 10px; background: #e0e0e0; color: #333;
            border: none; border-radius: 4px; font-size: 15px;
            font-weight: bold; cursor: pointer; text-align: center;
            text-decoration: none; display: inline-block; line-height: 1.4;
        }
        .btn-cancel:hover { background: #bdbdbd; }
        .badge {
            display: inline-block; padding: 3px 10px; border-radius: 12px;
            font-size: 12px; font-weight: bold; margin-left: 8px; vertical-align: middle;
        }
        .badge-edit   { background: #fff3e0; color: #e65100; }
        .badge-add    { background: #e8f5e9; color: #2e7d32; }
    </style>
</head>
<body>
<div class="container">
    <h1>📚 Book Store</h1>

    <%
        Author editAuthor = (Author) request.getAttribute("editAuthor");
        boolean isEdit = (editAuthor != null);
    %>

    <div class="card">
        <h2>
            <%= isEdit ? "Edit Author" : "Add New Author" %>
            <span class="badge <%= isEdit ? "badge-edit" : "badge-add" %>">
                <%= isEdit ? "Editing #" + editAuthor.getId() : "New" %>
            </span>
        </h2>

        <form action="AuthorServlet" method="post">
            <% if (isEdit) { %>
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%= editAuthor.getId() %>"/>
            <% } else { %>
                <input type="hidden" name="action" value="add"/>
            <% } %>

            <label for="authorName">Author Name</label>
            <input type="text" id="authorName" name="authorName" required
                   placeholder="Enter full name"
                   value="<%= isEdit ? editAuthor.getAuthorName() : "" %>"/>

            <label for="email">Email Address</label>
            <input type="text" id="email" name="email" required
                   placeholder="Enter email address"
                   value="<%= isEdit ? editAuthor.getEmail() : "" %>"/>

            <div class="form-actions">
                <a class="btn-cancel" href="AuthorServlet">Cancel</a>
                <button type="submit" class="btn-submit">
                    <%= isEdit ? "Update Author" : "Add Author" %>
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
