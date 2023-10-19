# Music application with microservices

Esta es una aplicación de muica con la arquitectura de microservicios con spring boot 3. Es una simulación inspirada en spotify en la que hay usuarios oyentes que disfrutan de la musica publicada en la aplicacion y tambien hay artistas que publican o crean la musica que se encuentra disponible en la app.

Este proyecto solo es un prototipo basado en la arquitectura de microservicios sin ningun tipo de front-end o interfaz grafica, este proyecto utiliza una seguridad basada en spring security con jwt para proteger los endpoints de los usuarios no autorizados. 

Para que esta applicacion pueda funcionar completamente depende de dos microservicios, [artistas](https://github.com/MateoRodriguez0/api-artistas-jwt) y [oyentes](https://github.com/MateoRodriguez0/api-oyentes-jwt) los cuales tienen toda la logica de negocio de la aplicacion. Este proyecto esta hecho con el fin de unir completamente ambos microservicios a traves de OpenFeign y crear una app segura con inicio de sesión y registro de cuenta.

## Spring Security architecture diagram

![diagrama Spring Security](https://github.com/MateoRodriguez0/spring-security-jwt/assets/107595139/15187af2-b3c4-4bc4-a708-c2c1bfd0eecc)

## project architecture
![appmusic](https://github.com/MateoRodriguez0/spring-security-jwt/assets/107595139/b8786e08-489c-480b-b683-753e4bb83cc2)


## Technologies implemented

- Spring Cloud OpenFeign 
- Jakarta Bean Validation
- Spring Data JPA
- Spring Security
- Spring web
- MySQL 8
- Lombok
- JJWT


# Datasource and pool connection 
``` java
spring.datasource.url=jdbc:mysql://localhost:3306/appmusic?useSSL=false&serverTimezone=America/Bogota&AllowpublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.generate-ddl=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.show-sql=false
spring.jpa.open-in-view=true

spring.datasource.hikari.pool-name=conexionOyentes
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.connection-timeout=45000

```

# feignClients
```Java
@FeignClient(name = "servicio-artistas",url = "localhost:8004")
public interface ArtistsFeignClient {

    }


@FeignClient(name = "servicio-clientes",url = "localhost:8003")
public interface OyentsFeignClient {
	
    }
```

# Requeriments 
* Java 17 o una version superior.
* MySQL 8
* Lombock

# Instalation 
- Primero Descargamos la version mas actual del proyecto en el siguiente [enlace](https://github.com/MateoRodriguez0/spring-security-jwt/releases/tag/VERSION-FINAL-DOCUMENTADA)
- Descargamos el archivo [appmusic.sql](https://github.com/MateoRodriguez0/spring-security-jwt/blob/master/appmusic.sql) 
- Descargamos la versión mas reciente de la [API ARTISTAS](https://github.com/MateoRodriguez0/api-artistas-jwt)
- Descargamos la versión mas reciente de la [API OYENTES](https://github.com/MateoRodriguez0/api-oyentes-jwt)
- importamos nuestros proyectos en el IDE.
- luego importamos la base de datos en nuestro gestor SGBD MySQL.

por ultimo corremos nuestros proyectos y se veria asi: 
![Captura de pantalla 2023-10-19 115526](https://github.com/MateoRodriguez0/spring-security-jwt/assets/107595139/ab529611-3595-44bf-9ae4-93b1fd6c8b37)



# API Reference

Para Realizar una peticion se debe enviar el JWT en las cabeceras de la peticion.


#### Example: 

![Captura de pantalla 2023-10-14 082846](https://github.com/MateoRodriguez0/spring-security-jwt/assets/107595139/719e13f7-60e7-4361-b86f-06f7f45c4cc3)



# Usage/Examples

### Sing In

```http
  POST musicapp/auth/singin
```
#### Body
```javascript
    {
        "username":"mateo204r@gmail.com",
        "password":"******"
    }
```
Si el inicio de session es exitoso deberi devolver en la respuesta el JSON Web Token.

### Sing Up 


```http
  POST /musicapp/auth/singup
```
#### Body para Sing Up Oyente
```javascript
    {
        "nombre":"oyente3",
        "email":"oyente3@gmail.com",
        "password":"oyente3.12345",
        "idRol":1
    }
```

#### Body para Sing Up Artista
```javascript
    {
        "nombre":"Artis3",
        "email":"artis3@gmail.com",
        "noombreArtistico":"Artistmusic"
        "password":"Artis3.12345",
        "idRol":2
    }
```
# Tests in Postman

![Captura de pantalla 2023-10-14 082628](https://github.com/MateoRodriguez0/spring-security-jwt/assets/107595139/6ad25ec3-5be6-4467-877d-38f60a6d8519)




# Documentation

- [API oyentes](https://github.com/MateoRodriguez0/api-oyentes-jwt)
- [API Artistas](https://github.com/MateoRodriguez0/api-artistas-jwt)


## Authors

- [Mateo Josue Rodriguez Chico](https://github.com/MateoRodriguez0)

