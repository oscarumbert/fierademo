# fierademo

Se utilizo una arquitectura rest en capas utilizando el patron repository para el mapeo entre la base 
y los modelos
Tambien se uso Builder y singleton como patron creacionales
Tambien se uso el patron DTO para proteger los objetos modelos de la base
Se Model mapper para el parseo de modelo a dto

Para correr solo se debe hacer run en FieraApplication
Para soportar una alta demanda usaria ribbon para el balanceo de carga
Se uso la base de datos h2 en memoria 
en el caso que se quiera ingresar a la base se puede hacer a traves de este link
http://localhost:8080/h2-console/login.jsp?

jdbc url: jdbc:h2:./data/demo
user: sa
password: sa
