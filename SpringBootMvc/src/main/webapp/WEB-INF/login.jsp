<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow-lg p-4 rounded-3">
                <h2 class="text-center text-primary mb-4">Login</h2>

                <!-- Error Message -->
                <h3 id="errorMsg" style="color:red;">${error}</h3>

                <form action="login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" name="username" class="form-control" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" required/>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>

                <div class="text-center mt-3">
                    <a href="register">Don’t have an account? Register Here</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Auto-hide error after 3 seconds -->
<script>
    setTimeout(() => {
        const errorMsg = document.getElementById("errorMsg");
        if (errorMsg && errorMsg.innerText.trim() !== "") {
            errorMsg.style.display = "none";
        }
    }, 3000);
</script>

</body>
</html>
