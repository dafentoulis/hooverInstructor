# Hoover instructor
Using this application, you can navigate an imaginary robotic hoover (much like a Roomba) through an equally
imaginary room based on room dimensions, locations of patched of dirt, initial position and cardinal directions.

### Assumptions
For this implementation, we assume that the room is rectangular, there are no obstacles (except for the room walls), no doors and all
the locations in the room will be clean (hoovering has no effect) except for the locations of the patches of dirt presented in the program
input.
Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for
the remainder of the program run. The hoover is always on.
Also, driving into a wall has no effect and the initial position may contain a patch too.

### Technologies Used
* Java 17
* [Spring Boot](https://spring.io/projects/spring-boot/)
* [Mapstruct](https://mapstruct.org/)
* [Lombok](https://projectlombok.org/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)

### Dependencies / Libraries
In order to use this application, download and install [Docker](https://docs.docker.com/get-started/get-docker/).
All code dependencies are provided in the pom file.

## Installation Guide and Usage
Clone this repository.

Inside the project's folder, run `docker-compose up --build` to build and run the application in a docker container.
The application will run at http://localhost:8080, exposing the following endpoint

| Method | URL                                      | Description    |
|--------| ---------------------------------------- |----------------|
| `POST` | `/operations/cleaning`                   | Cleans the area using the given instructions |

The API documentation can be found at http://localhost:8080/swagger-ui/index.html

## Authors
[Dimitrios Afentoulis](https://github.com/dafentoulis)

## Copyright & Licensing Information
This project is available for use under the MIT License.