## Sharing DTOs between services

### 1 Description
  To share DTOs between modules one can create a separate module and define common DTOs there. Later adding this module as a dependency solves this problem.
But there is another, more agile option. A service can offer his own DTOs exposed in a client module. So if one wants to be served by the service one should import the client part of the service and use DTOs defined there.

  In given example movie-theater-service uses client part of booking-service. Client part potentially may expose exceptions, interfaces or any configuration, e.g. configuration beans instead of hardcoded links .

### 2 Use
#### 2.1 To run
Apps will run inside container on 8080 and 8081 ports.
```aidl
docker compose up --build
```
#### 2.2 Endpoints
To view upcoming movie-shows. Returns id - value pairs
```
curl -X GET http://localhost:8080/api/movie-show
```
To view seats available for a movie-show:
```
curl -X GET http://localhost:8080/api/movie-show/2/seats
```
To book a seat for a movie-show:
```
curl -X POST http://localhost:8080/api/movie-show/2/book 
-H 'Content-Type: application/json' 
-d '{"userPhone":"call me :)","seatNum":"2"}'
```