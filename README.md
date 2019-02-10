mvn clean test

########################Hooks####################################
Hooks should be in step definition

########################Read Data from JSON####################################
RegisterEnvironment and Initialize json are static methods
All other methods are non statice - accessed via -> world.data.fill....

########################Cucumber Extent Reports####################################
Use info.cukes instead of io.cucumber

########################log4j - Send emailL####################################
https://www.lifewire.com/allow-email-programs-access-to-gmail-with-password-1171875
log.error("Some text") in the test will send the email

########################Remove MySQL####################################
sudo apt-get remove --purge mysql*
sudo apt-get purge mysql*
sudo apt-get autoremove
sudo apt-get autoclean
sudo apt-get remove dbconfig-mysql
sudo apt-get dist-upgrade

######################Install MySQL############################
sudo apt-get install mysql-server

######################Start MySQL############################
sudo service mysql start
sudo systemctl status mysql

######################Setup MySQL############################
sudo mysql
create database db_example; -- Create the new database
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the 
newly created database
 
USE db_example;
CREATE TABLE TRADE_LETTER(TRADE_LETTER_ID INT ,PARTY_ID INT );
INSERT INTO TRADE_LETTER VALUES (1,11);
INSERT INTO TRADE_LETTER VALUES (2,22);
SELECT * FROM TRADE_LETTER;

exit

###################After Hook ###################################3
