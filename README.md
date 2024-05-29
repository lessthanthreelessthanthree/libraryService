# libraryService

# Book List API Documentation

#### Fetch a list of all books available in the system.

<details>
 <summary><code>GET</code> <code><b>/api/books</b></code> <code>(allows users to fetch a list of all books available in the system)</code></summary>

##### Parameters

> | id      |  isbnNumber     | title               | author                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | None      |  required | required  | required  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`        | `{"id":1,"isbnNumber":"978-3-16-148410-0","title":"The Great Gatsby","author":"F. Scott Fitzgerald"},{"id":2,"isbnNumber":"978-0-7432-7356-5","title":"1984","author":"George Orwell"},{"id":3,"isbnNumber":"978-0-452-28423-4","title":"To Kill a Mockingbird","author":"Harper Lee"}`                                |
> | `204`         | `application/json`         | None                                                                |

##### Example cURL

> ```javascript
>  curl -X GET http://localhost:8080/api/books
> ```

</details>

#### Registers a book into the system.

<details>
 <summary><code>POST</code> <code><b>/api/books/register</b></code> <code>(allows users to register a book into the system)</code></summary>

##### Parameters

> | id      |  isbnNumber     | title               | author                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | None      |  required | required  | required  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`        | `Book registered successfully`                                |
> | `400`         | `application/json`         | `Bad Request`                                                                  |

##### Example cURL

> ```javascript
>  curl -X POST http://localhost:8080/api/books/register -H "Content-Type: application/json"  -d '{"isbnNumber": "978-3-16-148410-0", "title": "The Great Gatsby","author": "F. Scott Fitzgerald"}'
> ```

</details>


# Borrower List API Documentation

#### Registers a borrower into the system.

<details>
 <summary><code>POST</code> <code><b>/api/borrowers/register</b></code> <code>(allows users to register a borrower into the system)</code></summary>

##### Parameters

> | id      |  name     | email               |
> |-----------|-----------|-------------------------|
> | None      |  required | required  |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`        | `Borrower registered successfully`                                |
> | `204`         | `application/json`         | `Email already registered`                                                                  |
> | `400`         | `application/json`         | `Bad request`                                                                  |

##### Example cURL

> ```javascript
>  curl -X POST http://localhost:8080/api/borrowers/register -H "Content-Type: application/json"  -d '{"name": "testttasdasdas", "email": "test@test.com" }'
> ```

</details>

#### Borrow a book with a particular book id.

<details>
 <summary><code>POST</code> <code><b>/api/borrowers/register</b></code> <code>(allows users to borrow a book from the system)</code></summary>

##### Parameters

> | borrowerId      |  bookId     |
> |-----------|-----------|
> | required      |  required |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`        | `Book borrowed successfully`                                |
> | `404`         | `application/json`         | `Book not found`                                                                  |
> | `404`         | `application/json`         | `Borrower not found`                                                                  |
> | `400`         | `application/json`         | `Book has not been returned. Book is not available`                                                                  |

##### Example cURL

> ```javascript
>  curl -X POST http://localhost:8080/api/borrowers/2/borrow/2
> ```

</details>

#### Return a book with a particular book id.

<details>
 <summary><code>POST</code> <code><b>/api/borrowers/register</b></code> <code>(allows users to return a book to the system)</code></summary>

##### Parameters

> | borrowerId      |  bookId     |
> |-----------|-----------|
> | required      |  required |


##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`        | `{"id":2,"borrower":{"id":1,"name":"John Doe","email":"john.doe@example.com"},"book":{"id":2,"isbnNumber":"978-0-7432-7356-5","title":"1984","author":"George Orwell"},"borrowedAt":"2024-05-30T05:25:50.501456","returned":true}`                                |
> | `404`         | `application/json`         | ``                                                                  |

##### Example cURL

> ```javascript
>  curl -X POST http://localhost:8080/api/borrowers/1/return/2
> ```

</details>