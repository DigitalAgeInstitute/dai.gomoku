#dai.gomoku

This portion is the server portion of the gomoku project. The game logic is implemented in this portion.

##Getting The Server

There are a couple of ways of getting the game:

###Get and Compile the sources

If you have git installed in your system, then, you can clone this repository by running this command

	git clone https://github.com/DigitalAgeInstitute/dai.gomoku.git

This will get the sources and initialise a repository on your system that you could use to contribute back to the project in source

If you do not have git, [download the sources here](https://github.com/DigitalAgeInstitute/dai.gomoku) and click on the 'Download ZIP' button.
This downloads the sources for you that you can then compile and run.

You will also need to get the gson library in-order to enable the system to compile - make sure the gson library is present in the classpath.

####Compiling and Running
If you have Eclipse on your system, you can simply import the project into your Eclipse workspace and run the dai.gomoku.server.GomokuDaemon class.

If you do not have Eclipse, make sure you have the gson library in your class path then compile dai.gomoku.server.GomokuDaemon.java

i.e.

	javac dai.gomoku.server.GomokuDaemon.java

That should compile the entire server and you can run it with:

	java dai.gomoku.server.GomokuDaemon &lt;port&gt;

###Get the Jar File

Simply download the zip file from [here](https://github.com/DigitalAgeInstitute/dai.gomoku/tree/master/dist) and unzip it.

Use the gomoku_db.sql file to create the database.

Now, run the server

	java -jar gomoku-server.jar &lt;port&gt;
