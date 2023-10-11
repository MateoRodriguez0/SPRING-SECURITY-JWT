
# Aplicacion de musica con microservicios

Esta es una aplicacion de muica con la arquitectura de microservicios con spring boot 3. Es una simulacion inspirada en spotify en la que hay usuarios oyentes que disfrutan de la musica publicada en la aplicacion y tambien hay artistas que publican o crean la musica que se encuentra disponible en la app.

Este proyecto solo es un prototipo basado en la arquitectura de microservicios sin ningun tipo de front-end o interfaz grafica, este proyecto utiliza una seguridad basada en spring security con jwt para proteger los endpoints de los usuarios no autorizados. 

Para que esta applicacion pueda funcionar completamente depende de dos microservicios, [artistas](https://github.com/MateoRodriguez0/api-artistas-jwt) y [oyentes](https://github.com/MateoRodriguez0/api-oyentes-jwt) los cuales tienen toda la logica de negocio de la aplicacion. Este proyecto esta hecho con el fin de unir completamente ambos microservicios a traves de OpenFeign y crear una app segura con inicio de sesión y registro de cuenta.





## Tecnlogias implementadas

- Spring Cloud OpenFeign 
- Jakarta Bean Validation
- Spring Data JPA
- Spring Security
- Spring web
- MySQL 8
- Lombok
- JJWT
