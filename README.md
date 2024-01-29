# Social Network Springboot (Backend)

This project is a backend implementation for a social network, developed using the Springboot framework as part of a learning journey in Java. It's designed to provide a robust foundation for social networking features.

## Features

- **Authentication (`/api/v1/auth`):** Provides user login functionality.
- **Friend Requests (`/api/v1/friendRequest`):** Allows users to send, accept, and view all pending friend requests.
- **User Management (`/api/v1/user`):** Enables retrieving user details, deleting users, and updating usernames, emails, or passwords.
- **Registration (`/api/v1/registration`):** Handles new user registrations.

## Getting Started

### Prerequisites

- Java JDK
- MySQL
- Docker (for deploying with Docker Compose)

### Deployment

To deploy the project with a MySQL database:

1. Use the provided `docker-compose.yml` file.
2. Run the following command:

   ```bash
   docker compose up -d
## License
This project is open-sourced under the MIT License.
