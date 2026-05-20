

# 🌀 Moody – Mental Health Journal & Sentiment Analyzer

**Moody** is a journaling web application that helps users maintain a daily mood journal by writing entries and getting **sentiment analysis** feedback. Each entry gets an automatically calculated sentiment score so users can track emotional trends over time.

This project is built with **Spring Boot**, **Java**, **Thymeleaf**, and a sentiment analysis component.

---

## 📌 Tech Stack

| Layer              | Technology                            |
| ------------------ | ------------------------------------- |
| Backend            | Java + Spring Boot                    |
| Frontend           | Thymeleaf Templates                   |
| Sentiment Analysis | Custom Java Sentiment Analyzer        |
| Database           | MySQL / H2 / PostgreSQL (your choice) |
| ORM                | Spring Data JPA                       |
| Build System       | Maven                                 |
| UI                 | HTML / CSS / Bootstrap                |
| Utilities          | Lombok                                |

---

## 🧠 Features

✔ User Authentication (login / register)
✔ **Add Daily Mood Entries**
✔ Sentiment Score on each entry
✔ View Past Entries with Sentiment
✔ Trend Tracking over Time
✔ Simple Dashboard UI
✔ Lightweight & Minimal

---

## 📂 Project Structure

```
Moody/
├── src/
│   ├── main/
│   │   ├── java/              – Java source packages
│   │   └── resources/
│   │       ├── static/        – CSS/JS
│   │       └── templates/     – Thymeleaf views
│   └── test/                 – Unit tests
├── pom.xml                  – Maven
├── HELP.md
└── target/                  – Build output
```

---

## 🚀 How to Run

### 1. Clone the Repo

```bash
git clone https://github.com/k-k-j123/Moody.git
cd Moody
```

---

### 2. Configure Database

Create your database:

```sql
CREATE DATABASE moody;
```

Update database settings in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/moody
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

*(You can use H2 if you want in-memory DB for testing.)*

---

### 3. Build the Application

```bash
mvn clean install
```

---

### 4. Run the App

```bash
mvn spring-boot:run
```

OR

```bash
java -jar target/Moody-0.0.1-SNAPSHOT.jar
```

---

### 5. Access in Browser

```
http://localhost:8080
```

---

## 🧪 Sentiment Analysis

Every journal entry is analyzed using a sentiment analyzer that assigns a **score** representing emotional tone:

* Positive sentiment → higher score
* Neutral → mid-range score
* Negative → lower score

This helps users see emotional patterns over time.

---

## 📈 Optional Enhancements

🌟 Add charts to visualize mood trends (e.g., using Chart.js)
🌟 Export entries as CSV or PDF
🌟 OAuth login (Google / Facebook)
🌟 Mobile-friendly UI
🌟 Scheduling reminders for entries

---

## 🔐 Security

* Password hashing
* Session management
* Role-based access (optional)

*(Configure using Spring Security)*

---

## 📄 License

This project is free to use for educational and personal purposes.

---


Just tell me which one you want next!
