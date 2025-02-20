# LetMalagaApp

LetMalagaApp is a web application built with Java and Spring Boot that allows users to register, log in, and make reservations for accommodations. The application also includes an image handling feature.

## Features

- User registration and login
- Accommodation reservation
- Image upload and retrieval
- Secure authentication and authorization

## Technologies Used

- Java
- Spring Boot
- Maven
- Thymeleaf
- HTML/CSS
- JavaScript

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- A web browser

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/hugoromerosmr/letmalagaapp.git
    cd letmalagaapp
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

4. Open your web browser and navigate to `http://localhost:8080`.

## Usage

### User Registration

1. Go to the registration page: `http://localhost:8080/auth/register`
2. Fill in the registration form and submit.

### User Login

1. Go to the login page: `http://localhost:8080/auth/login`
2. Enter your credentials and log in.

### Making a Reservation

1. Navigate to the reservation page for a specific accommodation.
2. Fill in the reservation form and submit.

### Viewing Images

1. Upload images to the `src/main/resources/static/uploads/` directory.
2. Access images via `http://localhost:8080/images/{filename}`.

## Project Structure

- `src/main/java/org/example/letmalagaapp/` - Main application code
  - `controllers/` - Controllers for handling web requests
  - `models/` - Data models
  - `repositories/` - Repositories for data access
  - `security/` - Security configuration and services
- `src/main/resources/templates/` - Thymeleaf templates for views
- `src/main/resources/static/` - Static resources (CSS, JS, images)

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any inquiries or issues, please contact [hugoromerosmr](https://github.com/hugoromerosmr).
