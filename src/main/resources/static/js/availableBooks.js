document.addEventListener('DOMContentLoaded', function() {
    // Handle add to my books
    document.querySelectorAll('.add-to-mybooks').forEach(btn => {
        btn.addEventListener('click', function() {
            const bookId = this.getAttribute('data-id');

            fetch(`/add_to_mybooks/${bookId}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                }
            })
                .then(response => {
                    if (response.ok) {
                        // Show success message
                        const alert = document.createElement('div');
                        alert.className = 'alert alert-success alert-dismissible fade show';
                        alert.innerHTML = `
                        Book added to your collection!
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    `;
                        document.querySelector('.container').prepend(alert);

                        // Auto-dismiss after 3 seconds
                        setTimeout(() => {
                            alert.classList.remove('show');
                        }, 3000);
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    });
});