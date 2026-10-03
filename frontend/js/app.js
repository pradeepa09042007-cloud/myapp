document.addEventListener('DOMContentLoaded', () => {
    const actionBtn = document.getElementById('actionBtn');

    actionBtn.addEventListener('click', () => {
        actionBtn.textContent = 'Awesome!';
        actionBtn.style.backgroundColor = '#10b981'; // Turn green
        
        setTimeout(() => {
            actionBtn.textContent = 'Click Me';
            actionBtn.style.backgroundColor = ''; // Reset
        }, 2000);
    });

    console.log("Frontend initialized successfully.");
});
