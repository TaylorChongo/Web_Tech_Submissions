# RESTful API Assignment -- Full Documentation

This project contains several small Spring Boot REST APIs grouped by
question:

-   **Question 1:** Library Book Management API\
-   **Question 2:** Student Registration API\
-   **Question 3:** Restaurant Menu API\
-   **Question 4:** E-Commerce Product API\
-   **Question 5:** Task Management API\
-   **Bonus:** User Profile API with custom response wrapper

Each API is a standalone module with simple controllers and in-memory
lists.

## How to Run the Applications

1.  Make sure you have:

    -   JDK 17 or above\
    -   Maven installed\
    -   Spring Boot 3.x

2.  Open each project folder (e.g., *question1-library-api*).

3.  Run the following command:

```{=html}
<!-- -->
```
    mvn spring-boot:run

or run it directly from your IDE using the main class.

4.  Use Postman or your browser to test the endpoints.

------------------------------------------------------------------------

# QUESTION 1 -- LIBRARY BOOK API

Base URL: `/api/books`

## Endpoints

  Method   Endpoint                   Description
  -------- -------------------------- -----------------------
  GET      /api/books                 Get all books
  GET      /api/books/{id}            Get book by ID
  GET      /api/books/search?title=   Search books by title
  POST     /api/books                 Add a new book
  DELETE   /api/books/{id}            Delete a book

## Sample Response (GET /api/books)

``` json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

------------------------------------------------------------------------

# QUESTION 2 -- STUDENT REGISTRATION API

Base URL: `/api/students`

## Endpoints

  Method   Endpoint                      Description
  -------- ----------------------------- -----------------------
  GET      /api/students                 Get all students
  GET      /api/students/{id}            Get student by ID
  GET      /api/students/major/{major}   Get students by major
  GET      /api/students/filter?gpa=     Filter by GPA
  POST     /api/students                 Register student
  PUT      /api/students/{id}            Update student

------------------------------------------------------------------------

# QUESTION 3 -- RESTAURANT MENU API

Base URL: `/api/menu`

## Endpoints

  Method   Endpoint                         Description
  -------- -------------------------------- ------------------------
  GET      /api/menu                        Get menu items
  GET      /api/menu/{id}                   Get menu item
  GET      /api/menu/category/{category}    Filter by category
  GET      /api/menu/available?available=   Filter by availability
  GET      /api/menu/search?name=           Search by name
  POST     /api/menu                        Add new item
  PUT      /api/menu/{id}/availability      Toggle availability
  DELETE   /api/menu/{id}                   Delete item

------------------------------------------------------------------------

# QUESTION 4 -- E-COMMERCE PRODUCT API

Base URL: `/api/products`

## Endpoints

  Method   Endpoint                              Description
  -------- ------------------------------------- ---------------------------------
  GET      /api/products                         Get all (+ pagination optional)
  GET      /api/products/{id}                    Product details
  GET      /api/products/category/{category}     Filter by category
  GET      /api/products/brand/{brand}           Filter by brand
  GET      /api/products/search?keyword=         Search
  GET      /api/products/price-range?min=&max=   Price range filter
  GET      /api/products/in-stock                Items in stock
  POST     /api/products                         Add product
  PUT      /api/products/{id}                    Update product
  PATCH    /api/products/{id}/stock?quantity=    Update stock
  DELETE   /api/products/{id}                    Delete product

------------------------------------------------------------------------

# QUESTION 5 -- TASK MANAGEMENT API

Base URL: `/api/tasks`

## Endpoints

  Method   Endpoint                         Description
  -------- -------------------------------- ----------------------
  GET      /api/tasks                       All tasks
  GET      /api/tasks/{id}                  Task by ID
  GET      /api/tasks/status?completed=     Filter by completion
  GET      /api/tasks/priority/{priority}   Filter by priority
  POST     /api/tasks                       Create new task
  PUT      /api/tasks/{id}                  Update task
  PATCH    /api/tasks/{id}/complete         Mark completed
  DELETE   /api/tasks/{id}                  Delete task

------------------------------------------------------------------------

# BONUS -- USER PROFILE API

Base URL: `/api/users`

Uses response wrapper:

``` json
{
  "success": true,
  "message": "text",
  "data": {}
}
```

## Endpoints

  Method   Endpoint                                Description
  -------- --------------------------------------- -------------------
  GET      /api/users                              All users
  GET      /api/users/{id}                         User by ID
  GET      /api/users/search/username?username=    Search username
  GET      /api/users/search/country?country=      Filter by country
  GET      /api/users/search/age-range?min=&max=   Filter by age
  POST     /api/users                              Create user
  PUT      /api/users/{id}                         Update user
  PATCH    /api/users/{id}/activate                Activate
  PATCH    /api/users/{id}/deactivate              Deactivate
  DELETE   /api/users/{id}                         Delete user
