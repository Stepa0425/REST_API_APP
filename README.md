Данный Java проект реализует RESTful API, выполняя CRUD операции. Он работает на 21 версии Java, используя сборщик Maven версии 3.9.7, а также работающий с базой данных PostreSQL версии 16.3.
Для того, чтобы запустить это приложение у себя на компьютере необходимо иметь следующее программное обеспечение:
- Java JDK 21. Можно скачать по ссылке:
https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
- PostgreSQL. Можно скачать по ссылке:
https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Так же при установке у себя PostgeSQL необходимо создать пароль при установке wERREA49. Порт для PostgreSQL оставить по умолчанию. Далее создать базу данных car_dealership, владельца оставить по умолчанию. 

Шаги для запуска у себя:
1. Скачать zip папку кода себе на компьютер, распаковать, запомнить местоположение.
2. Перейти в командой строке(либо PowerShell) в папку, в которой будет находится ваш код(корневой каталог).
3. Далее выполните команду:
   ./mvnw.cmd clean install package
5. Это команда в самом конце будет содержать фразу Replacing main artifact *path_to_jar_file*.jar .....
6. Далее при успешной сборке jar файла вводим следующую команду:
java -jar *path_to_jar_file*.jar
7. В случае успеха запустится приложение. В первый раз происходит автоматически инициализация бд и заполнение данными,
   при последующем использовании необходимо перейти в корневой каталог в src\main\resources\application.properties
8. Поставить в последних 2-х строках в самом начале '#'. Повторить шаги 2-5.
9. Для того, чтобы закончить работу приложения необходимо нажать Ctrl+C(в противном случае закрыть комнадную строку/PowerShell).

10. Коллекция Postman для полной проверки REST API.
- POST localhost:8080/test/app/clients
  Body:
  {
"firstName" : "Vasja",
"lastName" : "Pupkin",
"email" : "gfsfsfs@gmail.com",
"phone" : "375294567891"

- GET localhost:8080/test/app/clients
- GET localhost:8080/test/app/clients/4

-PUT localhost:8080/test/app/clients/24
  Body:
  {
"firstName" : "James",
"lastName" : "LeBrone",
"email" : "jalebrone@ja.com",
"phone" : "375298956231"
}

-DELETE localhost:8080/test/app/clients/2

-GET localhost:8080/test/app/soldCars
-GET localhost:8080/test/app/soldCars/15

-DELETE localhost:8080/test/app/clients/9/soldCars/6

-POST localhost:8080/test/app/clients/9/soldCars
Body:
{
            "brand": "Volvo",
            "model": "XC90",
            "price": 27394.58
}

-PUT localhost:8080/test/app/soldCars/20
Body:
{
    "brand": "Volvo",
    "model": "XC90",
    "price": 26000
}

       
