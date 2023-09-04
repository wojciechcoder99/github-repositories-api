# GitHub Repositories API

## Overview

This Spring Boot API service provides GitHub repository details for a given username in JSON format. The main features include listing GitHub repositories and handling specific error conditions.

## Table of Contents

- [Acceptance Criteria](#acceptance-criteria)
  - [JSON Format](#json-format)
  - [Non-Existing GitHub User](#non-existing-github-user)
  - [Unsupported Accept Header](#unsupported-accept-header)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [API Usage](#api-usage)
- [Contributing](#contributing)
- [License](#license)

---

## Acceptance Criteria

### JSON Format

As an API consumer, given a username and a header `Accept: application/json`, you can list all repositories owned by that user which are not forks. The JSON response will include the following information:

- **Repository Name**
- **Owner Login**
- **For each branch:**
  - **Name of the branch**
  - **SHA of the last commit**

### Non-Existing GitHub User

As an API consumer, if you provide a username that does not exist, you will receive a `404 Not Found` response in JSON format like so:

```json
{
  "status": 404,
  "Message": "User not found."
} 
```
### Unsupported Accept Header

As an API consumer, if you provide a username that does not exist, you will receive a `404 Not Found` response in JSON format like so:

```json
{
  "status": 404,
  "Message": "User not found."
}
```
## Getting Started
### Prerequisites
- Java 17
- Spring Boot 3
- Maven
## Installation
1. Clone the repository:
 ```
git clone https://github.com/wojciechcoder99/github-repositories-api.git
```
2. Navigate to the project directory:
 ```
cd github-repositories
```
3. Build the project:
 ```
mvn clean install
```
4. Run the application:
 ```
mvn spring-boot:run
```
## API Usage
To get a list of GitHub repositories for a user, make a GET request to the following endpoint:
 ```
GET /api/github/repositories/{username}
```
### Headers
- Accept: application/json
## Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests.
## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
