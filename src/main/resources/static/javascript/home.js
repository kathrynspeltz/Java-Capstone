
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const bookAuthor = document.getElementById("bookAuthor")
const updateBookName = document.getElementById("updateBookName")
const updateBookAuthor = document.getElementById("updateBookAuthor")
const updateGenre = document.getElementById("updateGenre")
const updateReadStatus = document.getElementById("updateReadStatus")
const submitBookForm = document.getElementById("submit-new-book-button")
const addCommentButton = document.getElementById("add-comment-button")
const readBookListContainer = document.getElementById("read-book-list-container")
const unreadBookListContainer = document.getElementById("unread-book-list-container")
const commentsContainer = document.getElementById("comments-container")
let updateBookBtn = document.getElementById('update-book-button')
const editButton = document.getElementsByClassName("edit-button")
const closeModalButton = document.getElementsByClassName("close")
const updateCommentId = document.getElementById("update-comment-id")
const populateBookNameValue = document.getElementById("populate-book-name")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/books/"
const commentBaseUrl = "http://localhost:8080/api/v1/comments/"

function openModal() {
    document.querySelector(".bg-modal").style.display = "flex"
}

function closeModal() {
    document.querySelector(".bg-modal").style.display = "none";
           updateBookName.value = '';
           updateBookAuthor.value = '';
           updateGenre.value = '';
           updateReadStatus.value = '';
}

function openCommentModal() {
    document.querySelector(".bg-modal-2").style.display = "flex"
}

function closeCommentModal() {
    document.querySelector(".bg-modal-2").style.display = "none";
}

function populateCommentModal(obj){
    addCommentButton.setAttribute('comment-book-id', obj);
}

function populateBookName(obj){
    populateBookNameValue.innerHTML = bookName;
}
const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        bookName: document.getElementById("bookName").value,
        bookAuthor: bookAuthor.value,
        genre: document.getElementById("genre").value,
        readStatus: document.getElementById("readStatus").value
    }
    document.getElementById("bookName").value = ""
    bookAuthor.value = ""
    document.getElementById("genre").value = ""
    document.getElementById("readStatus").value = ""
    await addBook(bodyObj);
}
const handleCommentAdd = async (e) => {
    e.preventDefault()
    let bodyObj = {
       commentText: document.getElementById("add-book-comment").value,
    }
    document.getElementById("add-book-comment").value = ""
    await addComment(bodyObj);
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

async function addComment(obj) {
    let commentBookId = addCommentButton.getAttribute('comment-book-id')
    const response = await
    fetch(`http://localhost:8080/api/v1/comments/book/${commentBookId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getComments(commentBookId);
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

async function handleCommentDelete(commentId){
    let deleteCommentId = addCommentButton.getAttribute('comment-book-id')
    await fetch(commentBaseUrl + commentId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getComments(deleteCommentId);
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
    unreadBookListContainer.innerHTML = ''
    readBookListContainer.innerHTML = ''
    array.forEach(obj => {
        let bookCard = document.createElement("div")
        bookCard.classList.add("m-2")
        bookCard.innerHTML = `
            <div class="book-card">
                <div class="card-body">
                    <p class="card-text">Title: ${obj.bookName}</p>
                    <p class="card-text">Author: ${obj.bookAuthor}</p>
                    <p class="card-text">Genre: ${obj.genre}</p>
                    <p class="card-text">Status: ${obj.readStatus}</p>
                    <div class="one-line">
                        <button class="blue-button" onclick="getBookById(${obj.id}); openModal();" type="button">Edit Book</button>
                        <button class="red-button" onclick="handleDelete(${obj.id})">Delete</button>
                        <button class="green-button" onclick="openCommentModal(); getComments(${obj.id}); populateCommentModal(${obj.id});" id="open-book-journal" type="button">Sticky Notes</button>
                    </div>
                </div>
            </div>
        `
        if (obj.readStatus == "Read"){
                readBookListContainer.append(bookCard);
        } else {
            unreadBookListContainer.append(bookCard)
        }
    })
}

async function getComments(bookId) {
    await fetch(`${commentBaseUrl}book/${bookId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCommentCards(data))
        .catch(err => console.error(err))
}

const createCommentCards = (array) => {
    commentsContainer.innerHTML = ''
    array.forEach(obj => {
        let commentCard = document.createElement("div")
        commentCard.classList.add("m-2")
        commentCard.innerHTML = `
            <div class="comment-card">
                <div class="card-body">
                        <p class="handwriting">${obj.commentText}</p>
                        <button class="red-button" onclick="handleCommentDelete(${obj.id})">Discard</button>
                </div>
            </div>
        `
        commentsContainer.append(commentCard);
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{

    updateBookName.value = obj.bookName
    updateBookAuthor.value = obj.bookAuthor
    updateGenre.value = obj.genre
    updateReadStatus.value = obj.readStatus
    updateBookBtn.setAttribute('data-book-id', obj.id)
}

getBooks(userId);

submitBookForm.addEventListener("click", handleSubmit)
addCommentButton.addEventListener("click", handleCommentAdd)


updateBookBtn.addEventListener("click", (e)=>{
    let bookId = e.target.getAttribute('data-book-id')
    handleBookEdit(bookId);
       closeModal();
})

document.getElementById("exit-comment-button").addEventListener("click", closeCommentModal)