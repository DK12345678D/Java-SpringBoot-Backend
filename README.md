
# 🎓 Internship Assignment – Application Software Centre, IIT Bombay  

## 📌 Overview   
| **Component**        | **Description** | 
|----------------------|-----------------|
| **Backend**          | Spring Boot REST API to manage courses and course instances | 
| **Frontend**         | Angular-based UI for interacting with the API |
| **Deployment**       | Dockerized for local & cloud deployment |
| **Extras**           | Optional CI/CD workflow & DockerHub publishing |

---

## 🏗️ Project Structure  
| **Module** | **Technology** | **Key Features** |
|------------|---------------|------------------|
| **spring-boot-courses-api** | Java, Spring Boot | REST API, Validation, Dependency Checks, Error Handling |
| **Angular-courses-ui**      | Angular           | Create/View/Delete Courses & Instances, Prerequisite Handling |

---

## 🌐 Backend (Spring Boot API)  

### **Courses API**
| **Method** | **Endpoint**                  | **Description** |
|------------|--------------------------------|-----------------|
| POST       | `/api/courses`                | Create a new course (validates prerequisites) |
| GET        | `/api/courses`                | List all courses with prerequisites |
| GET        | `/api/courses/:id`            | Get course by ID with prerequisites |
| DELETE     | `/api/courses/:id`            | Delete course (blocked if used as prerequisite) |

### **Course Instances API**
| **Method** | **Endpoint**                                      | **Description** |
|------------|---------------------------------------------------|-----------------|
| POST       | `/api/instances`                                 | Create a course instance (year & semester) |
| GET        | `/api/instances/:year/:semester`                 | List courses for a given year/semester |
| GET        | `/api/instances/:year/:semester/:courseId`       | View a specific course instance |
| DELETE     | `/api/instances/:year/:semester/:courseId`       | Delete a course instance |

---

## 🌐 Frontend (Angular UI)  
| **Feature** | **Description** |
|-------------|-----------------|
| Course Management | Create & view courses with prerequisites |
| Instance Management | Create & view course instances |
| Deletion Logic | Block deletion if course is a prerequisite |
| Filtering | View course details by year & semester |

---

## 📦 Example Course Model (JSON)
```json
{
  "courseId": "CS209",
  "title": "Intro to Computer Programming",
  "description": "This course provides a basic introduction to programming.",
  "prerequisites": ["CS101", "CS105"]
}
```

---

## 👨‍💻 Author  
| **Name**       | **Role**                  | **Email**                     | **LinkedIn** |
|----------------|---------------------------|--------------------------------|--------------|
| Dinkar Prasad  | Java Developer, Angular Enthusiast | dinkarprasad682@gmail.com | [linkedin.com/in/dinkarprasad682](https://linkedin.com/in/dinkarprasad682) |
