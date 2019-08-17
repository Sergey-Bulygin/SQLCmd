# SQLcmd 
*(консольная программа для работы с базой данных PostgreSQL)*

![Alt-текст](http://www.altay.com.tr/en/resimler/09-03-2018-21-31-40DataBase_1920x500.jpg)

--------

### Техническое задание на проект SQLCmd
***Задача*** - написание приложения на языке Java, реализующего функционал консольного клиента для
работы с конкретной базой данных. Проект должен отвечать следующим требованиям:
* Приложение должно использовать паттерн MVC (https://youtu.be/7Vo0bGL2AGs?t=483)
* Приложение должно иметь консольный интерфейс взаимодействия с пользователем, то есть
  реализован ввод команд с клавиатуры и вывод результатов на экран.
* Должны быть реализованы следующие консольные команды:
   - ***сonnect***
       - Команда для подключения к соответствующей БД
       - Формат команды: connect | database | username | password
           - где: database - имя БД
           - username -  имя пользователя БД
           - password - пароль пользователя БД
       - Формат вывода: текстовое сообщение с результатом выполнения операции
   - ***tables***
       - Команда выводит список всех таблиц
       - Формат: tables (без параметров)
       - Формат вывода:
       - в любом удобном формате
       - например [table1, table2, table3]
   - ***clear***
       - Команда очищает содержимое указанной (всей) таблицы
       - Формат: clear | tableName
           - где tableName - имя очищаемой таблицы
       - Формат вывода: текстовое сообщение с результатом выполнения операции
   - ***drop***
       - Команда удаляет заданную таблицу
       - Формат: drop | tableName
           - где tableName - имя удаляемой таблицы
       - Формат вывода: текстовое сообщение с результатом выполнения операции
   - ***create***
       - Команда создает новую таблицу с заданными полями
       - Формат: create | tableName | column1 | column2 | ... | columnN
           - где: tableName - имя таблицы
           - column1 - имя первого столбца записи
           - column2 - имя второго столбца записи
           - columnN - имя n-го столбца записи
       - Формат вывода: текстовое сообщение с результатом выполнения операции
   - ***find***
       - Команда для получения содержимого указанной таблицы
       - Формат: find | tableName
           - где tableName - имя таблицы
       - Формат вывода: табличка в консольном формате
~~~
             +--------+---------+------------------+
             +  col1  +  col2   +       col3       +
             +--------+---------+------------------+
             +  123   +  stiven +     pupkin       +
             +  345   +  eva    +     pupkina      +
             +--------+---------+------------------+
~~~
*
   - ***insert***
       - Команда для вставки одной строки в заданную таблицу
       - Формат: insert | tableName | column1 | value1 | column2 | value2 | ... | columnN | valueN
           - где: tableName - имя таблицы
           - column1 - имя первого столбца записи
           - value1 - значение первого столбца записи
           - column2 - имя второго столбца записи
           - value2 - значение второго столбца записи
           - columnN - имя n-го столбца записи
           - valueN - значение n-го столбца записи
       - Формат вывода: текстовое сообщение с результатом выполнения операции
   - ***update***
       - Команда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1
       - Формат: update | tableName | column1 | value1 | column2 | value2
           - где: tableName - имя таблицы
           - column1 - имя столбца записи которое проверяется
           - value1 - значение которому должен соответствовать столбец column1 для обновляемой записи
           - column2 - имя обновляемого столбца записи
           - value2 - значение обновляемого столбца записи
           - columnN - имя n-го обновляемого столбца записи
           - valueN - значение n-го обновляемого столбца записи
       - Формат вывода: табличный, как при find со старыми значениями обновленных записей.
   - ***delete***
       - Команда удаляет одну или несколько записей для которых соблюдается условие column = value
       - Формат: delete | tableName | column | value
           - где: tableName - имя таблицы
           - Column - имя столбца записи которое проверяется
           - value - значение которому должен соответствовать столбец column1 для удаляемой записи
       - Формат вывода: табличный, как при find со старыми значениями удаляемых записей.
   - ***help***
       - Команда выводит в консоль список всех доступных команд
       - Формат: help (без параметров)
       - Формат вывода: текст, описания команд с любым форматированием
   - ***exit***
       - Команда для отключения от БД и выход из приложения
       - Формат: exit (без параметров)
       - Формат вывода: текстовое сообщение с результатом выполнения операции

    ### User Stories
    
    - ***RA01[Run application]*** 
       - As a USER I want to run application. 
       - I can connect to database and view and edit data.
    - ***RA01.1[Application runs successfully]***  
       - I see welcoming message from application. 
    - ***RA01.2 [List of commands]*** 
       - As a USER I want to see list of commands. 
       - I use command ‘help’.
       - I see list of commands.
    
    - ***CD01 [Connect to database]*** 
       - As a USER I want to connect to database. 
       - I can view and edit data.
    - ***CD01.1 [Successful]*** 
       - I see welcoming message from application. 
       - I use command ’connect’ and input database name, user name and password. 
       - I see confirmation that I connected to database.
    - ***CD01.1 [Wrong password or name]*** 
       - I see welcoming message from application. 
       - I use command ’connect’ and input database name, user name and password (wrong). 
       - I see message that password or user name is wrong. Application requests to enter correct name/password. 
       - I use command ’connect’ and input database name, user name and password(correct). 
       - I see confirmation that I connected to database. 
     - ***CD01.2 [Wrong database]*** 
       - I see welcoming message from application. 
       - I use command ’connect’ and input database name (wrong), user name and password. 
       - I see message that database don’t exist. Application requests to enter existent database name.
    
    - ***LT01 [List of tables]*** 
       - As a USER I want to see list of tables in database. 
       - I can view and edit data.
    - ***LT01.1 [List of tables]*** 
       - See that I have connection to database. 
       - I use command ‘tables’. 
       - I see list of tables in the format "[tableName1, tableName2, tableName3]."
    - ***LT01.2 [Wrong command]*** 
       - See that I have connection to database. 
       - I use command ‘tbl’. 
       - I see message about wrong command.
    
    - ***VTD01 [View table data]*** 
       - As a USER I want to see data of table.
       - I can view and edit data.
    - ***VTD01.1 [View all data of the table]*** 
       - See that I have connection to database. 
       - I use command ‘find’. 
       - I see data in formatted view.
    - ***VTD01.2 [Wrong table name]*** 
       - See that I have connection to database. 
       - I use command ‘find’ (wrong). 
       - I see message that table don’t exist.
    
    - ***CrT01 [Create table]*** 
       - As a USER I want to create table.
       - I can store data.
    - ***CrT01.1 [Create table]*** 
       - See that I have connection to database. 
       - I use command ‘create’ with list of columns. 
       - I see confirmation that table is created.
    - ***CrT01.2 [Create table with existent name]*** 
       - See that I have connection to database. 
       - I use command ‘create’ (wrong). 
       - I see message that table can’t be created.
    
    - ***DT01 [Delete table]*** 
       - As a USER I want to delete table.
       - I can erase data.
    - ***DT01.1 [Delete table]*** 
       - See that I have connection to database. 
       - I use command ‘drop’. 
       - I see confirmation that table is deleted.
    - ***DT01.2 [Delete non-existent table]*** 
       - See that I have connection to database. 
       - I use command ‘drop’ (wrong). 
       - I see message that table don’t exist.
    
    - ***ClT01 [Clear table]*** 
       - As a USER I want to clear table data.
       - I can have empty table.
    - ***ClT01.1 [Delete table]*** 
       - See that I have connection to database. 
       - I use command ‘clear’. 
       - I see confirmation that table is cleared.
    - ***ClT01.2 [Delete data in non-existent table]*** 
       - See that I have connection to database. 
       - I use command ‘clear’ (wrong). 
       - I see message that table don’t exist
    
    - ***UT01 [Update table]*** 
       - As a USER I want to update table data.
       - I can have corrected data.
    - ***UT01.1 [Update existent table]*** 
       - See that I have connection to database. 
       - I use command ‘update’. 
       - I see confirmation that table is updated.
    - ***UT01.2 [Update data in non-existent table]*** 
       - See that I have connection to database. 
       - I use command ‘update’ (wrong). 
       - I see message that table don’t exist.
    
    - ***IDT01 [Insert data in table]*** 
       - As a USER I want to add data to table.
       - I can have data stored.
    - ***IDT01.1 [Insert data in existent table]*** 
       - See that I have connection to database. 
       - I use command ‘insert’ . 
       - I see confirmation that data is inserted.
    - ***IDT01.2 [Update data in non-existent table]*** 
       - See that I have connection to database. 
       - I use command ‘insert’ (wrong). 
       - I see message that table don’t exist.
    
    - ***DDT01 [Delete data from table]*** 
       - As a USER I want to delete some data from table. 
       - I can have correct data.
    - ***DDT01.1 [Delete data from table]*** 
       - See that I have connection to database. 
       - I use command ‘delete’. 
       - I see confirmation that data is deleted.
    - ***DDT01.2 [Delete data from non-existent table]*** 
       - See that I have connection to database. 
       - I use command ‘delete’ (wrong). 
       - I see message that table don’t exist.
    
    - ***EA01[Exit application]*** 
       - As a USER I want to exit application.
    - ***EA01.1[Exit application]*** 
       - I use command ‘exit’ . 
       - I see confirmation that I exit application.