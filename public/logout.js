async function handleLogout() {
    // 1. Get the token from storage
    const token = localStorage.getItem("token");

    // If there is no token, the user is already logged out locally.
    // Just redirect them to login.
    if (!token) {
        window.location.href = "index.html"; 
        return;
    }

    try {
        // 2. Call the Backend to Blacklist the token
        const response = await fetch("http://localhost:8080/auth/logout", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + token, // Send token in Header
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            console.log("Server: Token blacklisted successfully.");
        } else {
            console.warn("Server: Logout endpoint returned error, but we will clear local storage anyway.");
        }

    } catch (error) {
        console.error("Network error during logout:", error);
    } finally {
        // 3. CRITICAL: Always clear the local storage
        // We do this in 'finally' to ensure it happens even if the server is down.
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        localStorage.removeItem("role");

        // 4. Update UI or Redirect
        alert("You have been logged out.");
        
        window.location.href = "login.html";
    }
}