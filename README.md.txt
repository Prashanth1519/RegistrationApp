# Registration App

This project is a simple registration application with separate backend and frontend components.

Prerequisites
Backend:
	Java JDK 17 or later
	Maven
	MySQL
Frontend:
Any modern web browser (e.g. Chrome)


Steps to Run Backend.
1.Navigate to the backend directory:
	cd backend
2.Build and run the Spring Boot application:
	mvn spring-boot:run
3.The backend will start at http://localhost:9090.

Steps to Run Frontend.
1.Navigate to the frontend directory.
2.Open index.html in any browser.

To Test API Endpoints use postman.
Example API Endpoints:
POST /v1/registration: Create a new registration.
GET /v1/registration: Retrieve all registrations.
DELETE/v1/registration/{id}: To delete particular record.
PUT/v1/registration/{id}: To update particular record.