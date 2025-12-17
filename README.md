![version](https://img.shields.io/badge/version-v2.0.0-blue.svg)
# Student Attendance System

A **console-based Student Management System** built using **Java and MySQL (JDBC)**.  
This project evolved step by step from in-memory storage to a **fully database-backed system**, demonstrating real backend development concepts.

---

## 🚀 Features

### 👨‍🎓 Student Management
- Add student
- View all students
- Update student details
- Delete student

### 📝 Attendance Management
- Mark attendance for multiple students at once
- Date-wise attendance tracking
- View attendance history
- View absent list by date
- Attendance percentage calculation

### 📊 Marks Management
- Add or update marks
- View individual student marks
- View all students’ marks

---

## 🛠️ Technologies Used

- **Java** (Core Java, JDBC)
- **MySQL** (Relational Database)
- **Git & GitHub** (Version Control)
- **VS Code** (Development Environment)

---

## 🗄️ Database Design

### `studentlist`
| Column | Type |
|------|------|
| roll | INT (Primary Key) |
| name | VARCHAR |

### `marks`
| Column | Type |
|------|------|
| roll | INT (Primary Key, FK) |
| mark | INT |

### `attendance`
| Column | Type |
|------|------|
| date | DATE |
| roll | INT |
**Primary Key:** `(date, roll)`

---

## ▶️ How to Run the Project

### 1️⃣ Clone the Repository
```
git clone https://github.com/I-Saravanan/Student-Attendance-System.git
cd Student-Attendance-System
```
2️⃣ Compile
```
cd src
javac -cp ".;../lib/mysql-connector-j-9.5.0.jar" main/StudentsAttendance.java
```
3️⃣ Run
```
java -cp ".;../lib/mysql-connector-j-9.5.0.jar" main.StudentsAttendance
```
📌 Project Structure
Student-Attendance-System/
│
├── src/
│   ├── main/
│   ├── database/
│   
│
├── lib/
│   └── mysql-connector-j-9.5.0.jar
│
├── README.md
├── LICENSE
└── .gitignore

🔄 Version History

v1.0.0 – Console-based system using HashMap & File handling

v2.0.0 – MySQL database integration using JDBC


🤝 Contributing

Pull requests are welcome!
Feel free to submit issues or enhancements.

📄 License

This project is open-source under the MIT License.

✨👤 Author 
Saravanan Iyappan
