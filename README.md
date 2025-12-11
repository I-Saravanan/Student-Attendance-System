📘 Student Attendance Management System (Java) ![version](https://img.shields.io/badge/version-v1.0.0-blue.svg)


A simple, clean, and scalable Java-based attendance system built using HashMap, HashSet, TreeSet, Scanner, and Switch-case.
This project serves as a foundational version that can be expanded into a full-stack application in the future.

🚀 Features

✔ Mark student attendance

✔ Check if a student is present/absent

✔ View sorted list of present students

✔ View absent students

✔ Total student count, present count, absent count

✔ Input validation (invalid roll numbers)

✔ Prevention of duplicate attendance marking

✔ Clean, menu-driven console interface


🧠 Core Concepts Used

1️⃣ HashMap

Stores student roll numbers and names:

HashMap<Integer, String> studentList

2️⃣ HashSet

Stores unique present roll numbers:

HashSet<Integer> presentList

3️⃣ TreeSet
Used to display present list in sorted order:

TreeSet<Integer> sorted = new TreeSet<>(presentList);

4️⃣ Scanner & Switch-Case

Used for interactive menu and input handling.


📌 Project Structure
📂 StudentAttendanceSystem
 ├── StudentsAttendance.java
 └── README.md

🖥 How to Run

Install Java (JDK 8+ recommended)

Clone this repository:

git clone https://github.com/I-Saravanan/StudentAttendanceSystem.git


Navigate to the folder:

cd StudentAttendanceSystem


Compile the program:

javac StudentsAttendance.java


Run the program:

java StudentsAttendance

📊 Sample Output
---Attendance Menu---
1. Mark Attendance
2. Check Attendance
3. View Present List
4. View Absent List
5. View Total Count
6. Exit
Enter your choice:



🤝 Contributing

Pull requests are welcome!
Feel free to submit issues or enhancements.

📄 License

This project is open-source under the MIT License.

✨ Author
Saravanan Iyappan
