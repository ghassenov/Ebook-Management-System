document.addEventListener('DOMContentLoaded', function() {
    // Simple initialization if needed
    console.log("My Books page loaded");

    // You can add refresh functionality here if needed
    document.querySelector('.refresh-btn')?.addEventListener('click', () => {
        window.location.reload();
    });
});