
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const bookAuthor = document.getElementById("bookAuthor")
const updateBookName = document.getElementById("updateBookName")
const updateBookAuthor = document.getElementById("updateBookAuthor")
const updateGenre = document.getElementById("updateGenre")
const updateReadStatus = document.getElementById("updateReadStatus")
const submitForm = document.getElementById("book-form")
const bookListContainer = document.getElementById("book-list-container")
let updateBookBtn = document.getElementById('update-book-button')
const editButton = document.getElementsByClassName("edit-button")
const closeModalButton = document.getElementsByClassName("close")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/books/"

function openModal() {
    document.querySelector(".bg-modal").style.display = "flex"
}

function closeModal() {
    document.querySelector(".bg-modal").style.display = "none"
}
const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        bookName: document.getElementById("bookName").value,
        bookAuthor: bookAuthor.value,
        genre: document.getElementById("genre").value,
        readStatus: document.getElementById("readStatus").value
    }
    await addBook(bodyObj);
    document.getElementById("bookName").value = ''
    bookAuthor.value = ''
    document.getElementById("genre").value = ''
    document.getElementById("readStatus").value = ''
}

async function addBook(obj) {
    const response = await
    fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getBooks(userId);
    }
}

async function getBooks(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createBookCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(bookId){
    await fetch(baseUrl + bookId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

async function getBookById(bookId){
    await fetch(baseUrl + bookId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleBookEdit(bookId){
    let bodyObj = {
        id: bookId,
        bookAuthor: updateBookAuthor.value,
        bookName: updateBookName.value,
        genre: updateGenre.value,
        readStatus: updateReadStatus.value,
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

const createBookCards = (array) => {
    bookListContainer.innerHTML = ''
    array.forEach(obj => {
        let bookCard = document.createElement("div")
        bookCard.classList.add("m-2")
        bookCard.innerHTML = `
            <div class="book-card">
                <div class="card-body">
                    <p class="card-text">Book: ${obj.bookName}</p>
                    <p class="card-text">Author:${obj.bookAuthor}</p>
                    <p class="card-text">Genre: ${obj.genre}</p>
                    <p class="card-text">Status: ${obj.readStatus}</p>
                    <div class="one-line">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getBookById(${obj.id}); openModal();" type="button">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        bookListContainer.append(bookCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{

    updateBookName.innerText = obj.bookName
    updateBookAuthor.innerText = obj.bookAuthor
    updateGenre.value = obj.genre
    updateReadStatus.value = obj.readStatus
    updateBookBtn.setAttribute('data-book-id', obj.id)
}

getBooks(userId);

submitForm.addEventListener("submit", handleSubmit)
    updateBookName.innerText = ''
    updateBookAuthor.innerText = ''
    updateGenre.value = ''
    updateReadStatus.value = ''
updateBookBtn.addEventListener("click", (e)=>{
    let bookId = e.target.getAttribute('data-book-id')
    handleBookEdit(bookId);
       updateBookName.innerText = '';
       updateBookAuthor.innerText = '';
       updateGenre.value = '';
       updateReadStatus.value = '';
       closeModal();

})