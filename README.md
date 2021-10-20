
# Nearby Place Search
You can find places near you just using three parameters: Latitude, Longitude and Radius.
Places will be listed with their id, latitude, longitude and name.
In addition, places will be marked on the map, you can zoom in and out find the places.

## Tech / Framework
- [Spring Boot](https://spring.io/)
- [Angular-12.2.10](https://angularjs.org/)
- [PostgreSQL](https://www.postgresql.org/)

## How to Run This Project?

### Database

```bash
cd nearby-places-assessment/
docker-compose up -d
```

### Backend

```bash
cd nearby-places/
./mvnw spring-boot:run
```

### Frontend

```bash
cd ../nearby-places-frontend/
npm install
ng serve -0
```
Navigate to `http://localhost:4200/`.
