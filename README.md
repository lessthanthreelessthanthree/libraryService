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