## Сервис "Статистика погоды"

Собирает данные о погоде в трех городах (Уфа, Москва, Хабаровск)
с двух серверов (GridForecast, OpenWeatherMap), данные хранятся в h2.
Gridforecast.com долго отдает данные. 

**Сборка Maven** - собирается в war. Разворачивается на сервере Wildfly. 

###Точки доступа к сервису:

 - http://localhost:8080/weather/api/update - вызов сбора погодных данных
 - http://localhost:8080/weather/api/getall - получить собранные данные
 - http://localhost:8080/weather/h2 - Консоль H2

Планировщик Quartz настроен на сбор данных каждые 5 минут.

Настройки планировщика и apiToken'ы для доступа к погодным сервисам
 вынесены в application.yml 
 
**Клиент для сервиса:** Написан на React, доступ по-умолчанию:
 http://localhost:3000/