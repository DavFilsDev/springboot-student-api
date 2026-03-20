# Spring Boot Student API

## Overview
A REST API exercise demonstrating data handling in Spring Boot, covering request parameters, path variables, request bodies, and HTTP headers with in-memory data storage.

## Learning Objectives
- Handle HTTP requests using Spring Boot annotations
- Process different types of input data (query params, request body, headers)
- Implement RESTful endpoints (GET, POST)
- Manage application state in memory
- Perform content negotiation based on Accept headers

## Endpoints

### GET /welcome
Returns a personalized welcome message.

**Request Parameter:**
- `name` (string, optional) - Name to greet

**Response:**
- `200 OK` - "Welcome <name>"

**Example:**
```
GET /welcome?name=John
Response: Welcome John
```

### POST /students
Adds a list of students to the in-memory storage.

**Request Body:** Array of student objects
```json
[
  {
    "reference": "STU001",
    "firstName": "John",
    "lastName": "Doe",
    "age": 20
  },
  {
    "reference": "STU002", 
    "firstName": "Jane",
    "lastName": "Smith",
    "age": 22
  }
]
```

**Response:** Comma-separated list of all stored students
```
John Doe, Jane Smith
```

### GET /students
Retrieves all stored student names.

**Accept Header:**
- `text/plain` (default) - Returns student names in plain text format
- Other values - Returns error message "Format non supporté"

**Response:**
- `200 OK` - "John Doe, Jane Smith"
- `400 Bad Request` - "Format non supporté" (if Accept header is not text/plain)

## Technologies
- Java 17+
- Spring Boot 3.x
- Spring Web

## Project Structure
```
src/main/java/com/example/demo/
├── controller/
│   └── StudentController.java
├── service/
│   └── StudentService.java
├── model/
│   └── Student.java
└── repository/
    └── StudentRepository.java
```

## Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/DavFilsDev/springboot-student-api
```

2. Navigate to the project directory
```bash
cd springboot-student-api
```

3. Build the project
```bash
./mvnw clean install
```

4. Run the application
```bash
./mvnw spring-boot:run
```

5. Test the endpoints
```bash
# Test GET /welcome
curl "http://localhost:8080/welcome?name=John"

# Test POST /students
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '[{"reference":"STU001","firstName":"John","lastName":"Doe","age":20}]'

# Test GET /students with Accept header
curl -H "Accept: text/plain" http://localhost:8080/students
```

## Key Spring Boot Concepts
- `@RestController` - Defines RESTful controller
- `@GetMapping` / `@PostMapping` - HTTP method mappings
- `@RequestParam` - Extract query parameters
- `@RequestBody` - Extract and deserialize request body
- `@RequestHeader` - Extract HTTP headers
- In-memory storage using collections (List, Map)

## Exercise Progression
1. **Part A** - Basic GET endpoint with query parameters
2. **Part B** - POST endpoint with request body processing
3. **Part C** - GET endpoint with header-based content negotiation

## License
Educational purposes only