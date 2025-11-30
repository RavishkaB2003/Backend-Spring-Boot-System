async function gettabledata() {
    try{
        const token = localStorage.getItem("token");

        const data = await fetch("http://localhost:8080/api/v1/books/all", {
        headers: {
            "Authorization": "Bearer " + token
        }
        });


        if(!data.ok){
            throw new Error(`HTTP error! status: ${data.status}`);
            
        }
        const jsondata = await data.json();
         let tab = "";

         jsondata.forEach(function(record){
        tab += `<tr>
        <td>${record.BookId}</td>
        <td>${record.bookTitle}</td>
        <td>${record.isbn}</td>
        <td>${record.Availability}</td>
        <td>${record.Author}</td>
        <td>${record.BookDescription}</td>
        </tr>`;
    });

    document.getElementById("tbody").innerHTML = tab;
    } catch(error){
        console.error('Error fetching data:', error);
    }

}
    
    
    

    
