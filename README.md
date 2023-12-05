## city-information-api ##
City Information API - Job application task for BASF

## Overview

The `docker-compose.yml` file in this project is configured to run three services: pgAdmin, PostgreSQL, and a Java Spring Boot Application. This guide outlines the steps to set up and run these services using Docker Compose.

## How to Run

1. Open a terminal and navigate to the root directory of the project.

2. Run the following command to start the Docker Compose setup in the background:

    docker-compose up -d

3. To view a list of all running containers, execute the following command:

    docker-compose ps

4. Open your web browser and access the pgAdmin interface at http://localhost:5050/.

5. Log in to pgAdmin with the following credentials:

    Email: pgadmin4@pgadmin.org
    Password: admin

## Additional Information
  The pgAdmin interface allows you to manage and visualize the PostgreSQL database.

##  Stopping the Services
  To stop and remove the running containers, use the following command:

    docker-compose down

##  Postman Collection
  Postman collection is in same level as readme.md file
