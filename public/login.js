console.log("login.js loaded");


async function login(event) {
    event.preventDefault();
    try {
        const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    });

    const data = await response.json();

    localStorage.setItem("token", data.token);
    localStorage.setItem("role", data.role);

    if(data.role.includes("ADMIN")) {
        window.location.href = "addbook.html";
    } else if (data.role.includes("USER")) {
        window.location.href = "index.html";
    }

    } catch (error) {
        alert("Login failed: " + error.message);
    }
}
