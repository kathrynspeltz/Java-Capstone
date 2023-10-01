




//const headers = {
//    'Content-Type': 'application/json'
//}
//
//const baseUrl = "http://localhost:8080/api/v1/comments/"



//
//const handleCommentAdd = async (e) => {
//    let bodyObj = {
//        bookComment: document.getElementById("bookComment").value,
//    }
//    await addComment(bodyObj);
//    document.getElementById("addComment").value = ''
//}
//
//async function addComment(obj) {
//    const response = await
//    fetch(`${baseUrl}book/${bookId}`, {
//        method: "POST",
//        body: JSON.stringify(obj.bookComment),
//        headers: headers
//    })
//        .catch(err => console.error(err.message))
//    if (response.status == 200) {
//        return getComments(bookId);
//    }
//}



//async function handleCommentDelete(commentId){
//    await fetch(baseUrl + commentId, {
//        method: "DELETE",
//        headers: headers
//    })
//        .catch(err => console.error(err))
//
//    return getComments(bookId);
//}
//



//document.getElementById("open-book-journal").addEventListener("click", getComments${obj.Id})
//document.getElementById("add-comment-button").addEventListener("click", handleCommentAdd)
