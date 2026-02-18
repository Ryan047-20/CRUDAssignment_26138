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
http://localhost:8089/api/books


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
<img width="798" height="500" alt="Screenshot 2026-02-18 180121" src="https://github.com/user-attachments/assets/3d1dd9d0-17d3-4eaf-8ad5-ab2c8907c55a" />


---

### 2️⃣ Read All
**GET** `/api/books`

Response:
- 200 OK

📸 Screenshot:

<img width="798" height="500" alt="Screenshot 2026-02-18 180147" src="https://github.com/user-attachments/assets/eedd5f34-7cab-4d73-b57d-cbe9cb589d0b" />

---

### 3️⃣ Read By ID
**GET** `/api/books/{id}`

Response:
- 200 OK
- 404 Not Found (if not exists)

📸 Screenshot:
<img width="798" height="510" alt="Screenshot 2026-02-18 180225" src="https://github.com/user-attachments/assets/ae5ec4bd-9a2f-45f6-8a08-1a85e141f24c" />


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
<img width="750" height="530" alt="Screenshot 2026-02-18 180335" src="https://github.com/user-attachments/assets/9c1889b5-79f6-480a-b7bb-f305a2c5c749" />


---

### 5️⃣ Delete
**DELETE** `/api/books/{id}`

Response:
- 204 No Content

📸 Screenshot:

<img width="813" height="658" alt="Screenshot 2026-02-18 180404" src="https://github.com/user-attachments/assets/f21cab4d-edee-41c2-afa7-8a3170aa91ea" />

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
