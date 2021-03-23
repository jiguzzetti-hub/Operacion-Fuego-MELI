Operación Fuego de Quasar - MELI
Proyecto para challenge de Mercado Libre, se solicita determinar la posición del emisor y el descifrar el mensaje emitido, de ser posible.
Comenzando 🚀
Clona este repositorio:
Git clone https://github.com/jiguzzetti-hub/Operacion-Fuego-MELI.git

Levanta la BD con docker-compose:
Make run-db

Crea la BD con pgAdmin(https://www.pgadmin.org/download/)
Debes conectarte a localhots:5432/fire_operation_db desde tu IDE favorito
Pre-requisitos 📋
Para correr este proyecto necesita:
curl for Windows
docker-desktop
Maven
jdk11



Despliegue 📦
Para buildear la imagen, parado sobre el raíz del proyecto:
Make build-image

Para pushear la imagen al registry configurado en el Makefile:
Make push-image

Datos para correr la app 🛠️
Método /topsecret
curl --location --request POST 'https://fire-operation-meli-kc7i6mi6tq-ue.a.run.app/fireOperation/topsecret' \ --header 'x-apiKey: fireOperationAccessKey' \ --header 'Content-Type: application/json' \ --data-raw '{     "satellites": [         {             "name": "kenobi",             "distance": 51.876356,             "message": ["1A", "", "3A", "4A", "", "","7A"]         },         {             "name": "skywalker",             "distance": 211.5446346,             "message": [ "", "2B", "3B", "", "5B", ""]         },         {             "name": "sato",             "distance": 1463.74545,             "message": ["1C", "", "", "4C", "", "6C", "", "8C"]         }     ] }'

Método /topsecret_split POST
curl --location --request POST 'https://fire-operation-meli-kc7i6mi6tq-ue.a.run.app/fireOperation/topsecret_split/sato' \ --header 'x-apiKey: fireOperationAccessKey' \ --header 'Content-Type: application/json' \ --data-raw '{     "distance": 143.7,            "message": ["1A", "2","7A", "4","3"] }'


Método /topsecret_split GET
curl --location --request GET 'https://fire-operation-meli-kc7i6mi6tq-ue.a.run.app/fireOperation/topsecret_split' \ --header 'x-apiKey: fireOperationAccessKey'

Construido con 🛠️
Menciona las herramientas que utilizaste para crear tu proyecto
•	Spring-boot - Basado en el framework para desarrollar API REST en Java.
•	Maven - Manejador de dependencias
•	Postgresql - Usado dar servicio de BD.
•	Liquibase - Versionador de BD
Autor ✒️
•	Juan Guzzetti – Desarrollo general e implementación - jiguzzetti-hub 
________________________________________
⌨️ con ❤️ por jiguzzetti-hub 😊
