## Sharing DTOs between services

### 1 Description
To share DTOs between modules one can create a separate module and define common DTOs there. Later adding this module as a dependency solves this problem.
But there is another, more agile option. A service can offer his own DTOs exposed in a client module. So if one wants to be served by the service one should import the client part of the service and use DTOs defined there.
In given example movie-theater-service uses client part of booking-service.