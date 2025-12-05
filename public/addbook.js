function validateForm() {
    const title = document.getElementById("title").value.trim();
    const isbn = document.getElementById("isbn").value.trim();
    const author = document.getElementById("author").value.trim();
    const description = document.getElementById("description").value.trim();
    const availability = document.getElementById("available").value;

    const error = document.getElementById("submit-error");

        error.style.display = "none";


    if (!title || !isbn || !author || !description || !availability) {
        error.style.display = "block";
        return false;

    }

    addbook(title, isbn, author, description, availability);
    return true;
}


async function addbook(title, isbn, author, description, availability) {
    const bookinfo = {
        bookTitle: title,
        isbn: isbn,
        Author: author,
        BookDescription: description,
        Availability: availability == 'true' ? true : false
    };

    try {
        const token = localStorage.getItem("token");
        const res = await fetch("http://localhost:8080/api/v1/books/add", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bookinfo)
        });

        if (!res.ok) {
            throw new Error(`HTTP error! status: ${res.status}`);
        }

        alert('Book added successfully!');
        document.getElementById("bookForm").reset();
    } catch (error) {
        console.error('Error adding book:', error);
        alert('Failed to add book. Please try again.');
    }

}