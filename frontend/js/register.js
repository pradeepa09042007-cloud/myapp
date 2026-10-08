document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registerForm');
    const messageBox = document.getElementById('messageBox');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        // Clear old messages
        messageBox.style.display = 'none';
        messageBox.className = '';

        const user = {
            name: document.getElementById('name').value.trim(),
            phone: document.getElementById('phone').value.trim(),
            email: document.getElementById('email').value.trim(),
            password: document.getElementById('password').value
        };

        // Basic client-side validation
        if (!user.name || !user.phone || !user.email || !user.password) {
            showMessage("All fields are required.", "error");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user)
            });

            const data = await response.json();

            if (response.status === 201) {
                showMessage("Registration successful! You can now log in.", "success");
                form.reset();
            } else {
                showMessage(data.message || "Registration failed.", "error");
            }
        } catch (error) {
            console.error("Error:", error);
            showMessage("Server error. Is the backend running?", "error");
        }
    });

    function showMessage(text, type) {
        messageBox.textContent = text;
        messageBox.className = type;
        messageBox.style.display = 'block';
    }
});
