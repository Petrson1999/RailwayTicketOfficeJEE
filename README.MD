- Система Железнодорожная касса. Пассажир делает Заявку на билет до необходимой ему станции назначения, время и дату поездки. 
Система осуществляет поиск подходящего Поезда. Пассажир делает выбор Поезда и получает Счет на оплату. 
Администратор управляет списком зарегистрированных пассажиров.

1. DB - Postgres
2. Java 1.8.
3. Maven
4. Node + npm
5. Gulp

How to install:

1. Clone project
2. Create DB across scripts
3. Run gulp 
4. Deploy project
5. Run tomcat


Railway ticket office . Business logic:

1. Users could login as user or admin. - LoginCommand
2. user could register on side. - RegisterCommand
3. All could see landing-page. - LandingPageCommand (GET)
4. All could see tickets page. - TicketsCommand (GET)
5. Everyone sees their profile page - ProfileCommand (get)
6. Customer could buy a ticket on the ticket page - BuyTicketCommand (POST)
7. Customer could view own tickets - GetUserTicketsCommand (POST)
8. Admin could add trains - TrainCommand (POST)
9. Admin could add wagons - WagonCommand (POST)
10. Admin could add locomotives - LocomotivesCommand (POST)
11. Admin could add stations - StationsCommand (POST)
12. Admin could add wagon types - WagonTypeCommand (POST)
13. Admin could add flight - FlightCommand (POST)
14. Admin could change train composition - UpdateTrainCommand (POST)
15. User on site could change language - LanguageCommand