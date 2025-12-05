async function bookbyid(){
        let id = document.getElementById("bookid").value.trim();

        if(!id){
            alert("Please enter a Book ID");
            return;
        }

    try{
        const token = localStorage.getItem("token");
        const data = await fetch(`http://localhost:8080/api/v1/books/book=${id}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        if(!data.ok){
            throw new Error(`HTTP error! status: ${data.status}`);
        }

        const book = await data.json();
         let tab = `
         <tr>
         <td>${book.bookTitle}</td>
         <td>${book.isbn}</td>
         <td>${book.Availability}</td>
         <td>${book.Author}</td>
         <td>${book.BookDescription}</td>
         </tr>`;

    document.getElementById("tbody").innerHTML = tab;
    } catch(error){
        alert('Error fetching book by ID. Please try again.');
        console.error('Error fetching book by ID:', error);
    }
}


async function bookbytitle() {
    let title = document.getElementById("booktitle").value.trim();

    if (!title) {
        alert("Please enter a Book Title");
        return;
    }

    try {
        const token = localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/api/v1/books/by-title?bookTitle=${encodeURIComponent(title)}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const book = await response.json();

        let tab = `
            <tr>
                <td>${book.bookTitle}</td>
                <td>${book.isbn}</td>
                <td>${book.Availability}</td>
                <td>${book.Author}</td>
                <td>${book.BookDescription}</td>
            </tr>`;

        document.getElementById("tbody").innerHTML = tab;
    } catch (error) {
        alert('Error fetching book by title. Please try again.');
        console.error('Error fetching book by title:', error);
    }
}

async function bookbyisbn(){
    let isbn = document.getElementById("bookisbn").value.trim();

    if(!isbn){
        alert("Please enter a Book ISBN");
        return;
    }

    try {
        const token = localStorage.getItem("token");
        const data = await fetch(`http://localhost:8080/api/v1/books/by-isbn?isbn=${encodeURIComponent(isbn)}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if(!data.ok){
            throw new Error(`HTTP error! status: ${data.status}`);
        }

        const book = await data.json();
         let tab = `
         <tr>
         <td>${book.bookTitle}</td>
         <td>${book.isbn}</td>
         <td>${book.Availability}</td>
         <td>${book.Author}</td>
         <td>${book.BookDescription}</td>
         </tr>`;

    document.getElementById("tbody").innerHTML = tab;
    } catch(error){
        alert('Error fetching book by ISBN. Please try again.');
        console.error('Error fetching book by ISBN:', error);
    }
}
