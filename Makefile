
# A simple Makefile
# HelloWorld.class: HelloWorld.java
# javac HelloWorld.java

SRCDIR = src
BINDIR = bin
DOCDIR = doc
TESTDIR = test

JUNIT = ./gson/gson-2.8.6.jar -sourcepath ./src ./src/$*.java


JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)

#to check the src and test folders for the .java files
#also to ensure that class files are in bin 
vpath %.java $(SRCDIR):$(TESTDIR)
vpath %.class $(BINDIR)

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $<

classes: Orders.class Database.class UberEatsRestuarantsApp.class \
		UberEatsMobileApp.class

default: $(CLASSES)

doc:
	javadoc -d $(DOCDIR) -cp $(DOCDIR) $(SRCDIR)/*.java

test_classes: Orders.class Database.class Meal.class Restaurant.class UberEatsRestuarantsApp.class \
		UberEatsMobileApp.class
	      
junit: test_classes
	
Orders.class: Orders.java
	javac -d $(BINDIR) -cp $(JUNIT)
Database.class: Database.java
	javac -d $(BINDIR) -cp $(JUNIT)
Meal.class: Meal.java
	javac -d $(BINDIR) -cp $(JUNIT)
Restaurant.class: Restaurant.java
	javac -d $(BINDIR) -cp $(JUNIT)
UberEatsRestuarantsApp.class: UberEatsRestuarantsApp.java
	javac -d $(BINDIR) -cp $(JUNIT)
UberEatsMobileApp.class: UberEatsMobileApp.java
	javac -d $(BINDIR) -cp $(JUNIT)

#test_classes: SearchItLinearTest.java eDirectoryTest.java #SearchItTest.java PrintItTest.java
	      
#junit: test_classes
#	javac -d $(BINDIR) -cp ../junit/junit-4.12.jar -sourcepath ./src $*.java
#	#java -cp $(BINDIR):$(JUNIT) org.junit.runner.JUnitCore $*.java	

clean:
	rm -f  $(BINDIR)/*.class
	rm -Rf doc
