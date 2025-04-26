document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const formData = {
            name: document.getElementById('name').value,
            author: document.getElementById('author').value,
            price: document.getElementById('price').value
        };

        fetch('/api/books', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/available_books';
                } else {
                    alert('Error registering book');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error registering book');
            });
    });
});