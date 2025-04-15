# Simple Weather API in Kotlin integrated with [AccuWeather](https://developer.accuweather.com/)

### How to run app

To run application you need to provide these env variables:

- `ACCU_WEATHER_URL` its `http://dataservice.accuweather.com`
- `ACCU_WEATHER_API_KEY` ( to obtain this key, you have to create an account and receive unique key )

### Technology stack:

- Kotlin 1.9, Java 17
- Spring Boot 3.4
- Feign ( integration with AccuWeather )

### Database:

- MongoDB ( to save weather data ) --- TODO
- Mongock ( create migrations with indexes ) --- TODO

### Tests stack:

- Junit
- WebMVC test --- TODO
- MongoDataTest --- TODO
- WireMock test --- TODO