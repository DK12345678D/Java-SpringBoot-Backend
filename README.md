🎓 Internship Assignment – Application Software Centre, IIT Bombay
This project is a full-stack application developed for the internship assignment.  
It consists of:

A Spring Boot REST API backend to manage courses and instances

A Angular-based frontend to consume and display the API

Dockerized setup for local and cloud deployment

CI/CD workflow and DockerHub publishing (optional)

🏗️ Project Structure
🔙 Backend (spring-boot-courses-api)
Built with Java & Spring Boot

REST API to manage:

Courses (POST, GET, DELETE)

Course delivery instances (POST, GET, DELETE)

Validation of prerequisites and dependency checks

Error handling using appropriate HTTP status codes

🌐 Frontend (Angular-courses-ui)
Built with Angular

Functionalities:

Create & view courses with prerequisites

Create & view course instances

Delete courses (with dependency warning)

View course details by year & semester

🧪 API Features
🔧 Courses API
Endpoint	Description
POST /api/courses	Create a new course (validates prerequisites)
GET /api/courses	List all courses with prerequisites
GET /api/courses/:id	Get course by ID with its prerequisites
DELETE /api/courses/:id	Delete course (blocked if used as prerequisite)

🔧 Course Instances API
Endpoint	Description
POST /api/instances	Create a course instance (year & semester)
GET /api/instances/:year/:semester	List courses for a year/semester
GET /api/instances/:year/:semester/:courseId	View specific course instance
DELETE /api/instances/:year/:semester/:courseId	Delete course instance


📦 Example Course Model (JSON)
```json
{
  "courseId": "CS209",
  "title": "Intro to Computer Programming",
  "description": "This course provides a basic introduction to programming.",
  "prerequisites": ["CS101", "CS105"]
}
```

👨‍💻 Author
Dinkar Prasad 
Java Developer | Angular Enthusiast
📧 Email: dinkarprasad682@gmail.com
🔗 LinkedIn: linkedin.com/in/dinkarprasad682
