# Spring Boot CRUD API (PostgreSQL)

This project is a RESTful API built using Spring Boot and PostgreSQL.
It implements full CRUD operations and was tested using Postman.

---

## ⚙️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Postman

---

## 🚀 How to Run the Application

1. Make sure PostgreSQL is running.
2. Create a database in PostgreSQL.
3. Update `application.properties`:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


4. Run the project:
mvn spring-boot:run


5. The application runs on:
http://localhost:8081


---

## 📌 API Endpoints (CRUD)

### 1️⃣ Create
**POST** `/api/books`

Sample Request Body:
{
"title": "Clean Code",
"author": "Robert Martin",
"isbn": "978-0132350884",
"publicationYear": 2008
}


Response:
- 201 Created

📸 Screenshot:
![Create Screenshot](screenshots/create.png)

---

### 2️⃣ Read All
**GET** `/api/books`

Response:
- 200 OK

📸 Screenshot:
![Get All Screenshot](screenshots/get-all.png)

---

### 3️⃣ Read By ID
**GET** `/api/books/{id}`

Response:
- 200 OK
- 404 Not Found (if not exists)

📸 Screenshot:
![Get By Id Screenshot](screenshots/get-by-id.png)

---

### 4️⃣ Update
**PUT** `/api/books/{id}`

Sample Request Body:
{
"title": "Clean Code Updated",
"author": "Robert Martin",
"isbn": "978-0132350884",
"publicationYear": 2009
}


Response:
- 200 OK

📸 Screenshot:
![Update Screenshot](screenshots/update.png)

---

### 5️⃣ Delete
**DELETE** `/api/books/{id}`

Response:
- 204 No Content

📸 Screenshot:
![Delete Screenshot](screenshots/delete.png)

---

## 📂 Project Structure

src
└── main
└── java
└── controller
└── model
└── repository


---

## ✅ Testing

All endpoints were tested successfully using Postman.
Screenshots are included in the `screenshots` folder.

---

## 👨‍💻 Author

Shema Ryan
