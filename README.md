# FileCreator
A smal tool I developed for organizing my self. Feel free to use it however, do not make money with it

The code is written in java. I used openJDK 1.13 


How to use: 
cmd command input line looks like this:
java -jar FileCreator.jar English -f
or 
java -jar FileCreator.jar Sellings -d

In the first case the output will be a folder which has the naming "English Aufgabe vom [date]". [date] is your current date
The "Aufgabe vom" String is stored in the String innerMessage wich is in the Main class. Feel free to change this. 

In the second case however, the output will be "Sellings Aufgabe vom [date]". This time it is a docx file. 

So there are this two different arguments "-f" and "-d" in the moment you only can use one of them at the same time. 
