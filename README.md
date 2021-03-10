# FileCreator
A smal tool I developed for organizing my self. Feel free to use it however, do not make money with it
The code is written in java. I used openJDK 1.13 

- old

How to use: 
cmd command input line looks like this:
java -jar FileCreator.jar English -f
or 
java -jar FileCreator.jar Sellings -d

In the first case the output will be a folder which has the naming "English Aufgabe vom [date]". [date] is your current date
The "Aufgabe vom" String is stored in the String innerMessage wich is in the Main class. Feel free to change this. 

In the second case however, the output will be "Sellings Aufgabe vom [date]". This time it is a docx file. 

So there are this two different arguments "-f" and "-d" in the moment you only can use one of them at the same time. 

- new

In the last couple of days I spend a lot time working on this program. Therefore, there are now a lot of new features. I am also planning to add a better gui soon. In the following
the latest readMe file and how to use the program. 

I recomend creating a bat file (on windows) for running the jar file automatically. Mine looks like this:
cd ""path to where the bat file is located""
java -jar "path to where the jar is located/FileCreator.jar"

If there are no arguments entered when running the jar, than the program will ask for them in a gui.

input syntax:
first param (param 0) = TypeName (type name)
second param (param 1) = -d or -e or -n (file type)
third param (param 2) = -f or -o (folder? | open created files and dirs?)
fourth param (param 3) = -o (open created files and dirs?)
 
The typeName is how the file is going to be named. E.c. typeName = "Hello"
means the created file is going to be named: "Hello Aufgabe vom 05.03.2021."
the "Aufgabe vom" part is changable. The Date will always be the latest.
Default typeNames are also in existance. Therefore, special strings like "-d"
or "-e" are also possible. In this case "-e" or "-d" are keys. Please look up.
the TypeKeys.java file. In there are valid keys can be found also you can add some since I did overwrite them before this upload.
 
second param: The second param defines whether a word file (-d), a txt file.
(-e) or nothing (-n) will be created. Incase only a folder is wished to be.
created you will have to type "example -n -f" since it is not possible to say.
that a folder must be created with only the first two arguments.
third param: The third param defines if a folder shall be created (-f) or
whether the created word, editor or directory files shall be opened (-o).
 
the fourth param defines (again) whether the created files and dirs shall be.
openend or not. This fourth parameter comes in handy if the third was used to.
say that a folder shall be created.

only the first and the second parameters are important therefore, the third.
and fourth ones can be left out. (Left out means dont do that)
possible input could be: "test -d -f -o". This would now create a word.
document which is in a folder. Both the document and the folder are named:
"test Aufgabe vom 05.03.2021". In Addition, the word file and the created
direcoty will be opened afterwards.


