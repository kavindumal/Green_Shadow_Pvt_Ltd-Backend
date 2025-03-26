# Crop Monitoring System

## Overview

Green Shadow (Pvt) Ltd. is a mid-scale farm specializing in root crops and cereals. The company operates at both national and international levels and is renowned in its field for high-quality production. This project aims to introduce a comprehensive system to manage their crops and other assets, focusing on the following areas:

- **Field**: Land allocated for cultivation with several fields designated for specific crop types.
- **Crop**: Type of crop grown in a particular field.
- **Staff**: Manages human resources as well as field and crop operations.
- **Monitoring Log**: Records observations and activities related to fields and crops.
- **Vehicle**: Manages vehicles assigned to staff for monitoring and supporting agricultural operations.
- **Equipment**: Oversees the agricultural equipment used in various operations.

## Business Process

### User Access
Users can log into the system as MANAGER, ADMINISTRATIVE, or SCIENTIST. 

### CRUD Operations
Principal users can perform CRUD (Create, Read, Update, Delete) operations on relevant entities.

### Data Analysis
- **Relational Analysis**: Evaluates relationships, such as driver and labor allocations to vehicles.
- **Spatial and Temporal Analysis**: Supports location-based and time-based analysis of resources.

### Permissions and Access Limitations
- **MANAGER**: Full access to perform all CRUD operations.
- **ADMINISTRATIVE**: Cannot edit crop data, field data, or monitor logs related to crop details.
- **SCIENTIST**: Cannot modify staff, vehicle, or equipment data.

## Main Services
- **Field Service**: Manages fields allocated for cultivation.
- **Crop Service**: Handles information related to crop types and growth stages.
- **Staff Service**: Manages human resources and their assignments.
- **Log Monitoring Service – Crop Details**: Records and tracks crop-related observations and activities.
- **Vehicle Service**: Oversees vehicle management and allocations for staff and operations.
- **Equipment Service**: Manages agricultural equipment used in various operations.
- **Auth Service**: Handles user authentication and access control.

## Guidelines

### General
1. **Validation**: Validation is required on both the client side and the server side.
2. **Sorting**: Entities should be sortable by name, designation, gender, vehicle type, land size, and status.
3. **UI/UX**: Emphasis on user experience, color psychology, and neatness.
4. **Password Security**: User credentials must be validated with password encryption.
5. **Authentication & Authorization**: Use Spring Security and OAuth 2.0 for authentication and authorization.
6. **Database**: Preferred RDBMS is MySQL.
7. **Exception Handling**: Handle all possible exceptions with custom exceptions.
8. **Log Management**: Crucial for the application.
9. **User Messages**: Use custom messages for user acknowledgments.
10. **Repository Documentation**: Include a detailed README file, a proper license, and API documentation in the repository.

## Tech Stack

### Front End
- HTML
- CSS
- CSS-Frameworks
- JavaScript
- jQuery
- AJAX

### Back End
- Spring Boot
- Spring Data
- Spring Web MVC
- Spring Validation
- Spring Security
- Lombok
- Model Mapper
- Jackson
- MySQL
- JWT

## Instructions

### Running the Application
1. Clone the repository:
    ```bash
    git clone https://github.com/kavindumal/Green_Shadow_Pvt_Ltd-Backend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd crop-monitoring-system
    ```
3. Build the project:
    ```bash
    ./mvnw clean install
    ```
4. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

### License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

### Contact
For any inquiries or support, please contact us at kavindu11250403@gmail.com.
