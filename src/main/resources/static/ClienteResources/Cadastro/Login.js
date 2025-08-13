document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector(".login-form");
    const passwordInput = document.getElementById("password");
    const togglePassword = document.querySelector(".toggle-password");
    const errorMessage = document.querySelector(".error-message");
    const eyeOpen = document.querySelector(".eye-open");
    const eyeClosed = document.querySelector(".eye-closed");

    // Toggle password visibility
    togglePassword.addEventListener("click", function() {
        // Toggle password field type
        const type = passwordInput.getAttribute("type") === "password" ? "text" : "password";
        passwordInput.setAttribute("type", type);

        // Toggle eye icon
        if (type === "text") {
            eyeOpen.style.display = "none";
            eyeClosed.style.display = "block";
        } else {
            eyeOpen.style.display = "block";
            eyeClosed.style.display = "none";
        }
    });

    // Form validation
    form.addEventListener("submit", function(e) {
        let valid = true;

        // Reset previous error states
        errorMessage.style.display = "none";
        passwordInput.classList.remove("error");

        // Validate password
        if (passwordInput.value.length < 6) {
            valid = false;
            passwordInput.classList.add("error");
            errorMessage.style.display = "block";
        }

        if (!valid) {
            e.preventDefault();
        }
    });
});