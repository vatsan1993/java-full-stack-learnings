Need java 17 or 21 for jenkins

# Installing spring tools suit


# sql

table names are plural

# maven
Maven is a plugin based project management tool.
helps to build apps faster.
We can install plugins and dependencies and also manage the project using maven.
To manage all the plugins and dependencies, we have a pom.xml file

To create a maven project we have an option in eclipse that we can make use of.
To do this we need to provide a lot of things like artifactId, groupId, version, name, description, so on.
groupId needs to be like com.example
artifactId needs to be like project-name
Once we create a project we will see a pom.xml file in the project.
We can use the pom.xml file to add dependencies, plugins

Example Dependency:
<dependencies>
    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>

Example Plugins
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <!-- Using the variables to change the default configuration of maven compiler. -->
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M4</version>
            </plugin>
        </plugins>
    </build>


Note: we can get the dependencies and plugins from the maven dependency online repository.

# JDBC:

Java Database connectivity

<p>
jdbc interfaces are available in the java.sql package. It contains the following interfaces
1. Connection
2. Statement
3. PreparedStatement
4. Callable Statement
5. ResultSet

</p>


1. Create Connection Object

when creating a connection object we have to set autoCommit to false to accidental commits.
the connection object contains the prepareStatement() method which returns the PreparedStatement object

statement object has execute(), executeUpdate(), executeQuery.
execute() returns true or false
executeUpdate returns an int which represented the number of rows updated.
executeQuery return ResultSet object;
```Java
Connection con = DriverManager.getConnection("jdbc:drivername:hostname:port/databaseName", "username", "password")
```

# Multi level architecture

entities are singular

# DAO
Data Access Object.
It is A combination of an interface and a class that communicates with the database.

The interface will have all the operations represented in the form of abstract methods.
Then a class will implement these methods. 

# Service
A service is a layer between the UI and DAO to not provide  direct access to the DAO.
The service is also a combination of interface and a class.
the interface contains the operations that we want to perform
The class contains the actual implementations.
The service class can have more methods for validations and other things.

# UI
The UI is where we create the user interface for simple applications it might contain cli but later we will see how we can interface these with web applications.


car-enquiry project

# Project Structure:
src
	-main
		- com.example
			- dao
			- entity
			- exceptions
			- services
			- ui
resources
	-test
		

# Steps to follow:

1. Do not use the existing exceptions. Always create own exception classes.
2. Create an entity classes with all the properties of a table in the database.
3. in dao create a ConnectionFactory class with a static method that returns a connection object.
4. Create Interface for each entity class with abstracts methods that specify the operations we will be performing. We do this as In the future instead of using the queries, we will switch to Hybernate or JPA
5. Each method should throw the custom exception.
6. Create a class for jdbc implementation for the method we specified in the interface . If hibernate is used there will be another class which will have the implementation using hibernate.
7. for jdbc implementation, create all the commands we need as constants at the top of the class and then implement the methods.
8. Create a Service Interface that will contain the methods needed to interact with the Dao and do some extra stuff like validations.
9. Create a Service class that implements all the methods by calling the dao ,methods and performing validations. Add mode methods here if needed. Todo this a dao object is necessary for this.
10. There has tof be 1 validation method for each property in the entity. if the validation is complex, it can be split into more methods. Once we have validations for each, we create a new method that calls these sub methods and generates appropriate error messages if any.

Note we should not create just a class that has all the code for the service. It should have have the interface aswell for the following reasons:
    1. If there is only a class ItemService, it is tightly coupled to ItemDaoJdbcImpl, making it hard to switch databases. We need to change all the code each time a database connection is changed.
    2. It doesn’t use an interface, reducing flexibility and testability.Using dependency injection allows mock testing, making unit tests easier.

✅ By implementing an interface and using dependency injection, we make the service more maintainable, testable, and flexible.

Note :Always use ? for sql parameters in jdbc to avoid sql injection attacks.


-----------
Junit
----------


Needs dependecies and plugins:
<dependencies>
        <!-- junit needs 2 dependencies 1. junit api 2. junit test environment -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.6.2</version>
            <scope>test</scope>
        </dependency>
        <!-- To trigger the junit platform we need the surefire plugin. -->
       	<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine -->
		<dependency>
		    <groupId>org.junit.jupiter</groupId>
		    <artifactId>junit-jupiter-engine</artifactId>
		    <version>5.6.2</version>
		    <scope>test</scope>
		</dependency>
		<!-- params isneeded for ParameterizedTest -->
		<dependency>
		  <groupId>org.junit.jupiter</groupId>
		  <artifactId>junit-jupiter-params</artifactId>
		  <version>5.6.2</version>
		  <scope>test</scope>
		</dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <!-- Using the variables to change the default configuration of maven compiler. -->
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M4</version>
            </plugin>
        </plugins>
    </build>
-------------------
# Servlets and JSP
----------------
Servlet is a java program that can receive a request and send a response. This is used to create backend apps.

We use the following packages , Interfaces and classes.
javax.servlet
	interfaces)
	ServletContext
	ServletConfig
	ServletRequest
	ServletResponse
	Abstract Class
	GenericServlet
	
javax.servlet.http
	HttpServlet extends GenericServlet
	HttpServletResponse
	HttpServletRequest
	Cookie
	HttpSession
	RequestDispatcher
	
Web Project Structure
---------------------
We will develop the project in our computer and then copy the project into the web server. This is called as deployment.
In older versions we has WebContent folder and in new versions we will have webapp
web1
	src
		main
			.java
	WebContent or webapp // considered as content root/web root
		WEB-INF
			lib // will contain all the 3rd party jar files.
				.jar
			classes
				.class // all compiled java files will be here.
			web.xml //deployment descriptor file 
		.html
		.jsp
	
	

web.xml contains list of all the servlets, which servlet to execute when a particular request is made, which file contains the error page and so on.
If we are using servlet 3.0 or above, we dont need to use the web.xml. We can use annotations in servlet java file.

We will not deploy the java files to the web server. Only WebContent will be uploaded in the form of a .war file.
even the 3rd party jar files will be taken care by maven.
the war file will have the same name as the project name.

We can have multiple webapps on a single server with different port numbers.

# Tomcat Folder Structure
------------------------------
bin - contains bash or sh files to start or stop tomcat server.
conf - contains configurations for the webservers.
lib -jar files exists here
logs - contains logs if something goes wrong. Error messages can be seen through the logs created in this folder.
webapps - contains actual webapps that we are trying to deploy.

We integrate tomcat with eclipse so the deployment happens through ide instead of manually copying things.



# Creating Project:
1. We first create tomcat server where we provide the location of the tomcat, port numbers and save it.
2. Then we create a new Dynamic Web Project whcih will create the folder structure needed for the web application. 
3. But this project needs us to add third party libraries manually. So instead we will convert the project into a maven project. 
Right Click Project -> Configure-> Convert to Maven Project
4. We need to provide the Group id and artifact(java-web-1) id(com.example).
5. We need to specify where we can find the contents for webapp in the maven-war-plugin plugin
<configuration>
	<warSourceDirectory>webapp</warSourceDirectory>
</configuration>


# Creating Servlet
Servlet is a java class that will accept a request, process it and send back a response.
It can be created n 2 ways
1. Servlet without any Protocol(it could be http, ftp, smtp )
	class MyServlet extends GenericServlet{
		service(ServletRequest reqObj ServletResponse respObj){
			// access reqObj 
			// process the data
			// put the response in the response object.
		}
	}

2. Servlet with Http protocol exclusively
class MyServlet extends HttpServlet{
	doGetHttpServletRequest reqObj, HttpServletResponse){}
	doPost(){}
	doDelete(){}
	....

}


# Accessing a servlet in the browser:
html files and jsp files can be accessed directly in the browser. but for java files that is not possible.
so the servlet files needs to be linked to a url.
This can be done by following the steps below.
1. by using web.xml (optional in newer versions.)
2. by providing annotations for the servlet class

@WebServlet("/xyz")
class MyServlet extends HttpServlet{
	doGetHttpServletRequest reqObj, HttpServletResponse){}
	doPost(){}
	doDelete(){}
	....

}


# sending form data to servlet.
we simply need to set the action attribute to the url of the servlet.
<form action="ws2">

in the servlet methods, we use the getParameter() on the request object. Which is generally in a string format.


# sending responses:
1. We need to tcreate a PrintWriter object which will be using response.getWriter().
PrintWriter out = response.getWriter();
2. Then we need to set the content type of the response object. Forgetting this might make the webpage not work.
response.setContentType("text/html");
3. Now the PrintWrite2r object contains println() method using which we can send the html
out.println("<html>");
out.println("<body>");
out.println("<h2>Hello! <strong>"+ (num1 + num2) +"</h2>");
out.println("</body>");
out.println("</html>");

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		int num1, num2;
		String val1 = request.getParameter("n1");
		String val2 = request.getParameter("n2");
		if(val1 == null || val2 == null) {
			num1 = 10;
			num2 = 20;
		}else {
			num1 = Integer.parseInt(val1);
			num2 = Integer.parseInt(val2);
		}
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>Hello! <strong>"+ (num1 + num2) +"</h2>");
		out.println("</body>");
		out.println("</html>");
	}
	
	
JSP
----------------
Java Server Pages.
Used to create dynamic html
Most of the page is html with a bit of java.

JSP Elements
---------------
used to embed java into jsp
jSP converts to Servlets internally.
Directives - preprocessing 
	<%@ directiveName attr="val" %>
	3 directives: 
		1. page directive : <%@ page %> 
			-helps to tell if a page ending another jsp , can handle errors, imports
		2. include directive : <%@ include %>
			to include output of one jsp page into another.
		3. taglib directive : <%@ taglib %>
			used to work with tag libraries.
	
Declaratives
	<!@ declaration; %>
	used to declaring functions and other stuff.
	This is class level.  
	
Scriplets
	<% java code; %>
	variable inside this will be method level declarations.
Expressions
	<%=expression %>
	helped to print the result of expression onto jsp body.
Actions
	<js[:action attrib="value" />


---------------
MVC 
--------------
Model View Controller Design Pattern.
Model is the data flow between different layers of an application.
Controller : the component that controls everything . This is the starting point of the flow where the user sends a request to get a page or get data from one page, perform calculations and send the results to another page.
View : Contains the UI . The user interacts with this part.

DAO <-------(model)---------> Service <------(model)-------> Controller <------(model)-------> View

DAO provides persistence Logic
Service Provides Business logic
Controller contains flow logic 
View contains Presentations Logic.

Sevlet = Controller
JSP = view
Java PoJo = Model

# Request forwarding
sending a request from a servlet to another servlet or jsp is called request forwarding.
To do this we use RequestDispatcher.
RequestDispatcher rd = request.getRequestDispatcher("visitorInput.jsp");
rd.forward(request,response);

to send data from the servlet to another servlet or jsp, we need to put that data as attribute in the request. This can be dont by using request.setAttribute();
request.setAttribute("msg", greeting);


To use this in jsp page:
we perform request.getAttribute("msg") inside the jsp expression tag or scriplets.


# JSP implicit objects.
----------------------------
We can use these objects directly in JSP pages. As JSP will be converted to Servlet, all these objects will be available when the conversion happens.

out	- JspWritern object (rarely used) This is used when we dont want to use expressions
request - HttpServletRequest
response - HttpServletResponse
application - ServletContext
config - ServletConfig
session - HttpSession 
exception - Throwable

We have already seen how we canuse the request object in the previous topic.


Expression Language:
----------------------
It helps to accessing the jsp implicit objects directly.

Instead of using request.getAttribute("msg"), we can use ${msg}
It tries to get the msg . If its available , then it will be rendered. If not nothing will be rendered the content The enclosing tag will be present.
We can also perform java operations and method calls in here.

JSTL - Java Standard Tag Language
---------------------------------
helps to work with control structures.
JSTL is not an inbuilt library. So we need to add the dependency if we need to make use of it.
core
core_rt (core runtime)
sql
fmt
xml
etc.

as we will be shifting to things like spring we will not dive deep into these
we will only look at the core tags for interview purposes.
we can use core or core_rt. It allows us to use function calls which core does not allow.

JSTL-core
------------------------

<c:if test = "${msg != null}">
	<p>...</p>
</c:if>

<c:forEach items = "${array}" var = "ele">
	<tag>${ele} </tag>
</c:forEach>

<c:choose>
	<c:when test="{}">
	</c:when>
	<c:when test="{}">
	</c:when>
	<c:when test="{}">
	</c:when>
	<c:when test="{}">
	</c:when>
	<c:otherwise test="{}">
	</c:otherwise>
</c:choose>

c:choose is similar to if else if.

Request Including vs Forwarding vs Request Redirection
----------------------------------------
inclusion:
Client ---req0---> serv/jsp1 ----req1 ---->serv/jsp2
	   <--res1 + res0---	<------res1----		
	If one servlet wants to include data of another servlet, then we can use request dispatcher's include method. (never used)
	RequestDisparcher
		include
If one jsp page wants to include data of another jsp, then we can use 2 ways
1. <%@ include file="..." /> - includes  output at compile time
2. <jsp:include page="---" /> - includes the file at the runtime.

For example:
if we are including page2.jsp in page1.jsp using <%@ include file="..." />  and if page2.jsp is modified after compilation, then the changes will not be included. The file has to be recompiled.
But if we use <jsp:include page="---" />, the page will be included in the runtime. Each time page1.jsp is requested, a new request is sent to page2.jsp and then its included. so any changes that were made will be inc

Forwarding:
when client makes a request to servletA , it will include its processed data and forwards the same request to servletB and so on. No new request needs to be made
Client ---req0---> servA ----fwd req1 ---->servB ---> Page01.jsp ---resp--->client
all data will be included at then end.

RequestDisparcher forward method


Redirection
Insead of forwarding the same request to the the servletA will redirect the client to servletB which means a new request is sent from client to servletB.
Client ---req0---> servA 
       <---->redirect req1 ---->servB
       <---redirect req2--->Page01.jsp 
       <----resp-------
       HttpServletResponse.sentRedirect();
Note: in this situation, the data from ServletA will not be passed to servletB
        

State Management:
-----------------
http is a state less protocol. This means after a request and respose is done nothing related to the previous request is saved.


To maintain some data on the server we can do it in different ways.
sessions is the most used one.



# Session:
--------
Session is a temporary memory assigned to each user(). 
It has expiry time
if a new request is made the session lives on,
if time expires, memory is lost.
Server will create a session automatically.
We need to access that session and dump data into it.
to access a session, we need to use request.getSession().
if we want to create discard old session and create a new one, we have to use request.getSession(true) even when the we want expiry time is within expiry time.
Every session will have a session id.
To kill a session we can use abandon(); and it will create a new one
the default expiration time is 30 mins.
to set a custom expiry time, 
setMaxInactiveInterval(long);
We can also do this in the web.xml file declaratively


# Listeners
Listeners are interfaces that run when a specific event occurs.
There can be different kinds of events that can occur.
For example when the server starts, a server ends, when a session is created and a session ends
we create a listener by following the steps below.
1. Right click on the project.
2. Go to new
3. Click on Listener.
4. Provide a package and the class name for creating the listener and click next.
5. We can now select which kinds of events we want to handle.


# Filter
Something that runs before or after or both cycles.
contains method doFilter. doFilter is the first thing to encounter the request.
If we donot want to request moving forward , we can simply implement Filter interface and overide doFilter method it here.

The Filter interface contains  init(), destroy().

in the example, we will look at the execution time of a request.

we create a filter by following the steps below.
1. Right click on the project.
2. Go to new
3. Click on filter.

The filter contains a url like @WebFilter("/ExecTimeFilter") which means it only executes when we reach /ExecTimeFilter.
To run it for all the urls we change it to /*

We already have a line of code chain.doFilter(request, response); which is simply calling the corresponding request.
if we want to do something before the request is called, then we write it before this line.
if we want to do something after the request is called, then we write it after this line.
The applications of filters are a lot.

CRUD Application (InventoryManagementPortal)
------------------

SignleFrontController Design Pattern
------------------------------------

There will be a single controller that receives all the requests. It can redirect requests to specific jsp or servlet, it can send the request to a helper controller if needed. It can also process the request on its own.

# Structure
DAO    Service    FrontController   Views

There will be only one Controller in this project.

In the invertory-management-system jdbc project, we used DriverManager.gerConnection(). But it does not work in a multi threaded environment.

Serverside apps needs multiple threads.

So we need a Connection pool but not a single connection.
To do this we have DataSource interface which is from javax.sql
In real world applications,
DataSource is created at a server level.
The server admin will create a DataSource and it will have an universal identification name.
We can access the DataSource using that name.

As of now we will create a datasource using the tomcat server at the application level.
But managing datasource is not the job of the developer. It will be taken care by server admin. 

Lets look how to create creating the DataSource at the application level.
We need to create a context.xml in the meta-inf folder and paste the following
<Context>
	<Resource 
	name = "jdbc/MyDB" 
	type="javax.sql.DataSource"
	maxActive="100" 
	maxIdle="30" 
	maxWait="10000"
	username="root" 
	password="ssvnka302#" 
	driverClassName="com.sql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/employees"/>
</Context>

the resource name needs to be written as jdbc/resourceName. This is the general standard that we follow.

The driver name is different for different versions of mysql.
to know the mysql version, we use sudo mysql -u root -p and login

com.mysql.jdbc.Driver is the driver name for mysql 5.
com.mysql.cj.jdbc.Driver for mysql 8

The ConnectionFactory class will change to use the DataSource.

<code>
package com.example.ims.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory  {
    private static DataSource datasource;
    public static Connection getConnection() throws SQLException, NamingException {
    	if(datasource == null) {
    		InitialContext context = new InitialContext();
    		// The parameter for the lookup will be provided by the server admin.
    		datasource = (DataSource) context.lookup("java:/comp/env/jdbc/MyDB");
    	}
    	return datasource.getConnection();
    }
}
	
</code>
 
Now that we have copied everything that is necessary for us and changed the Connection factory, we need to create the front controller.
A servlet controller can take multiple urls.

So we will create an url for each of the operations and provide to the front controller.

List Items /list
Add New Item
	Show Item Form /newItem
	Receive the submitted Data /addItem
Delete An Item	/deleteItem
Edit an Item
	show Item Form	/editItem
	Receive the submitted Data /saveItem

As we can see there are multiple urls.
we can add all the urls to this servlet using an array 

@WebServlet({"/list", "/newItem","/addItem", "/deleteItem", "/editItem", "saveItem"})a

now based on which url we get as part of the request we can perform different operations
we can get access to the url using the following code
<code>
	String url = request.getServletPath();
</code>

we compare this with different urls and perform specific operations.

------------------------
JPA-Hibernate
----------------------
Java Persistence API
ORM - Object Relational Mapping
  
RDBMS 									OOP
DB <---->DRIVER<---->JDBC<--->ORM<---->JAVA_APP
									RDBMS					OOP
									-------------------------------
EntiryDef							Table					Class
Entity								Record/Tuple/Row		Object


An Entity Definition in RDBMS is represented as a table. In java we use a class.
An Entity in RDBMS is represented as a row. In java we use an object.


How different Types of relationships is represented:
We have 2 relationships in java.
is a relationship and has a relationship
For example, lets say we want to represent the following
Manager is an Employee
In java we can use inheritance for this. In RDBMS we can either create a single table that contains details related to employee and manager. We can also create a joined table (one table will have common column and the other contains the remaining columns). We also create 2 different tables.


Has a Relationship
Composition
This is a strong relationship. The one of the object cannot exist without the other
For Example an Employee and Address. Address is always associated with another entity.
In RDBMS, we create a Single Table for this.
In we have one object containing another object as part of it.

Association
Contains aggregation and dependency,
Aggregation
This is a relation with a weak association. The one of the object can exist without the other
For Example an Employee and Address.

This can be represented in 4 relationships when it comes to RDMS
OneToOne
	Employee Can have one Salary account. in RDBM we will PK of employee as FK in SalaryAccount
	In Java an object of Employee will be a field in SalaryAccount and vice versa.
OneToMany
ManyToOne
	Department can have many employees . In RDBMS PPK of Department will be FK if employee
	The PK of One side will become FK of the many side.
	In java we cannot represent this as a uni-directional relationship. we need a bi-directional relationship.
	object of department will be the field of department
	Similarly a set or list of employees belong to department.
	If the order of employee is important for us, we use a list. if not we use a set.
ManyToMany
	Employee and Project. An Employee can have multiple projects and a project can have multiple employee. 
	In RDBMS we create a joined table.
	

Dependency has nothing to do with entity persistence so we ignore them

addressing the translation from Java to jdbc is very hard without an ORM. HYbernate is an ORM.


More details with examples here>https://github.com/avamsykiran/JavaFSD2to7/tree/master/02_JpaHibernate_22July22_25July22


JPA
---------
JAVA persistence API
JPAcontains 2 main packages
javax.persistence (JPA) now changed to jakarta.persistence
javax.transaction(JTA) now changed to jakarta.transaction

This is only a specification. Not the implementation. Contains interfaces, annotaions and method. we need a third party implementations to achieve this.

JPA/JTA implementations
in java 2 most popular implementations are hibernate and Ibate. There are other implementations available.

we will use JPA + Hybernate to build the projects

DB <---->DRIVER<---->JDBC<--->Hibernate<---->JPA<---->JAVA_APP

JPA Annotations and Classes/Interfaces
--------------------------------------
Persistence - Class - helps to load database details into the memory using persistence.xml which contains the databases config,
This class contains a method called createEntityManagerFactory(persistenceUnitName) which returns an EntityManagerFactory object.
This object will contain createEntityManager() method which returns EntityManager object.

EntityManager contains The following methods
	-- persist(entity) - helps to insert an entity(row) into a database
	-- merge(entity) - helps to update
	-- remove(entity) - helps to delete 
	-- find(Entityclassname, id) - returns the object with the matching id
	-- createQuery(queryText) -- return a Query class object.
	-- createQuery(queryText, EntityClassName)  - return an object  of  TypedQuery<E> class.
	-- createNamedQuery("NamedQuery", EntityClassName) - return an object  of  TypedQuery<E> class.
The Query, TypedQuery class object helps us to retrieve the data from the database using a new language called Java Persistence Query Language(JPQL)
The Query, TypedQuery object contains a  method called getResultList() which runs the query and returns a list of object that we are querying
TypedQuery is prefered as we can specify what type of data we are expecting.
	-- getTransaction() -- return entity EntityTransaction object
	
EntityTransaction contains begin(), commit() and rollback() method which helps in handling transactions.

Annotations
@Entity - a class level annotation. Applied on a class. Tells that this class will be an entity
@Table(name="")  class level annotation. This helps to map the entity with a particular table.

@Id - field level annotation. helps to represent a primary key in the class.
@GeneratedValue - field level annotation. helps to auto generate values.
@Column(name="feild name") -- field level annotation. Helps to control the name of the column. If it is nullable or unique and other constrains.
if the Column annotation is not applied, then the field name becomes the column name.
the camelcasing in the name becomes underscores
@Transient - field level annotation. If we dont want to map a field with any of the columns in  the database, then we wil create it as transient. the field will become non-persistent 


Project 1:
we are creating a simple maven project . we are not creating a Web application.
we need hibernate and mysql connector dependencies for the project.
<code>
	<dependencies>
	  	<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-core</artifactId>
		    <version>6.6.6.Final</version>
		    <type>pom</type>
		</dependency>	
		 <!-- Hibernate JPA API (Jakarta Persistence) -->
	    <dependency>
	        <groupId>org.hibernate.orm</groupId>
	        <artifactId>hibernate-jpamodelgen</artifactId>
	        <version>6.6.6.Final</version>
	    </dependency>
	    <!-- Jakarta Persistence API -->
	    <dependency>
	        <groupId>jakarta.persistence</groupId>
	        <artifactId>jakarta.persistence-api</artifactId>
	        <version>3.1.0</version>
	    </dependency>
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.33</version>
		</dependency>
	  </dependencies>
</code>


We need to provide database info in the src/main/resources folder. Create a folder called META_INF. inside that ,create a file called peristence.xml

<code>
	<!-- Example persistence.xml-->
	<?xml version="1.0" encoding="UTF-8"?>
	<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
	    <persistence-unit name="mysqlPU">
	    	<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
	        <properties>
	            <!-- Database Connection Properties -->
	            <!-- The property names and the values depends on the 
            dependency version and the database version we use -->
	            <property name="jakarta.persistence.jdbc.driver" 
	            value="com.mysql.cj.jdbc.Driver"/>
	            <property name="jakarta.persistence.jdbc.url" 
	            value="jdbc:mysql://localhost:3306/hibdb" />
	            <property name="jakarta.persistence.jdbc.user" 
	            value="root"/>
	            <property name="jakarta.persistence.jdbc.password" 
	            value="ssvnka302#"/>
	            <!-- Hibernate Properties -->
	            <!-- Tells which version of database dialect which can be found on org.hibernate.dialect
	            This should depend on the database version you are using. -->
	            <property name="hibernate.dialect" 
	            value="org.hibernate.dialect.MySQL8Dialect"/>
	            <!-- displays the generates sql queries in the console -->
	            <property name="hibernate.show_sql" value="true"/>
	            <property name="hibernate.format_sql" value="true"/>
	            <!-- hibernate mapping to data definition language.
	            if the class entity i create as a class does not exist in the databse
	            what should we do? we have 3 values for this create, update, creat-drop
	            update- if the table does not exist then it will be created.
	            If the schema is not matching with the class, then it will be updated as per the class.
	            create -if already available , then creates it, if not available, creates new.
	            create-drop- when the application shuts down the database is dropped automatically,
	             -->
	            <property name="hibernate.hbm2ddl.auto" value="update"/>
	        </properties>
	    </persistence-unit>
	</persistence>
	
</code>

We will have a root persistence tag . inside that we can create as many persistence units as we want. each persistence unit represents one database. we can give any name.
inside this we will have a provider tag which tells us which implementation of jpa we will be using. In this case we use hibernate provider.

Then we will have properties that define which driver we use, the database url, username, password for the database.
Along with these, we use other properties like hinernate.show_sql, hibernate.dialect, hibernate.format_sql and hibernate.hbm2ddl.auto. These are hibernate related properties.

Once we have the dependenciesn and the persistence.xml, we will create the entity classes where we need to make use of the annotations like @Entity, @Table @Id, @Column to define the table and the columns and primary key.
The class we create has to be serializable so that the objects can be saved into the database.
We create constructor, getter, setter, toString methods.
<code>
	package com.example.entity;
	import java.io.Serializable;
	import java.time.LocalDate;
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToOne;
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Embedded;
	@Entity
	@Table(name = "emps")
	public class Employee implements Serializable{
		@Id
		@Column(name="eno")
		private Long empno;
		@Column(name="fnm", nullable = false)
		private String firstName;
		@Column(name="lnm", nullable = false)
		private String lastName;
		@Column(name="basic", nullable = false)
		private Double basic;
		@Column(name="doj", nullable = false)
		private LocalDate joinDate;
		public Employee() {
		}	
		public Employee(Long empno, String firstName, String lastName, Double basic, LocalDate joinDate) {
			super();
			this.empno = empno;
			this.firstName = firstName;
			this.lastName = lastName;
			this.basic = basic;
			this.joinDate = joinDate;
		}
		public Long getEmpno() {
			return empno;
		}
		public void setEmpno(Long empno) {
			this.empno = empno;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Double getBasic() {
			return basic;
		}
		public void setBasic(Double basic) {
			this.basic = basic;
		}
		public LocalDate getJoinDate() {
			return joinDate;
		}
		public void setJoinDate(LocalDate joinDate) {
			this.joinDate = joinDate;
		}
		@Override
		public String toString() {
			return "Employee [empno=" + empno + ", firstName=" + firstName + ", lastName=" + lastName + ", basic=" + basic
					+ ", joinDate=" + joinDate + "]";
		}
	}
</code>

For this project we are looking at a simple java application. So not servlets, jsp, or string yet. We dont even have a DAO yet.
So we create a class with main method 
In this method we start by creating an object for EntityManagerFactory class. We need to provide the name of the persistenceUnit that we created in the persistence.xml
Then we use this object to create EntityManager object.
Then we can create a EntityTransaction object if we are performing multiple insert or updates.
Then we begin the transaction.
Then we create entities by using the persist method of the EntityManager object.
once we are dont, we commit the tansaction.
then we close the EntityManeger connecton.

<code>
		Employee[] employees = new Employee[] {
			new Employee(101L, "Vamsy", "Aripaka", 25000.0, LocalDate.now()),
			new Employee(102L, "Srinu", "Dachepalli", 55000.0, LocalDate.now()),
			new Employee(103L, "Suseela", "Aripaka", 35000.0, LocalDate.now()),
			new Employee(104L, "Sagar", "Aripaka", 95000.0, LocalDate.now()),
			new Employee(105L, "Indu", "Aripaka", 75000.0, LocalDate.now()),
		};
//		persistenceUnitName - mysqlPU
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		// transaction is better to create transaction if there are multiple insert or update.
		EntityTransaction txn = em.getTransaction();
		// starting transaction
		txn.begin();
		for(Employee emp: employees) {
			// inserting employee
			em.persist(emp);
			System.out.println("Employee is saved!");
		}
		txn.commit();
		em.close();
</code>

Retrieving data:
--------------


Retrieving a record using primary key:
-------------------------------------
the EntityManager class contains a method called as find which can get the classname of the entity and the id to get the entity which is matching the id.

<code>
	Employee emp = em.find(Employee.class, empId);
</code>


Retrieving data with multiple rows:
----------------------------------
To retrieving data we will use something called JPQL(Java Persistence Query Language).

JPQL is similar to sql but instead of using the table  names, we use the name of the class and instead of using the names of the column, we will use the field names in the class.

Syntax:
<code>
	SELECT objectname from ClassName objectName ;
</code>

As we are now allowed use * , we specify the object that will contains all the attributes needed.

Example:
<code>
		String qryText = "SELECT e FROM Employee e";
//		Query empQuery = em.createQuery(qryText);
		TypedQuery<Employee> empQuery = em.createQuery(qryText,Employee.class);
		List<Employee> emps = empQuery.getResultList();
		for(Employee emp : emps) {
			System.out.println(emp);
		}
</code>

The query class might give us a runtime exception if the result is not matching Object that we are expecting. So instead of using the Query class we use the TypedQuery<E> class 
TypedQuery will helps us to check the type of the data we will be getting before the query runs.


using where clause:
-------------------
Here is an example in which we are using a where clause.
<code>
	String qryText = "SELECT e FROM Employee e where e.basic between 10000 and 55000";
</code>

Using parameters for the query:
------------------------------
To provide some input parameter we user :parameterName in the query and then we use the setParameter("parameterName", value) which will be on the TypedQuery object.

<code>
	String qryText = "SELECT e FROM Employee e where e.basic between :limit1 and :limit2";
	TypedQuery<Employee> empQuery = em.createQuery(qryText,Employee.class);
	empQuery.setParameter("limit1", 10000);
	empQuery.setParameter("limit2", 55000);	
</code>

Named Query:
------------
We can store a query with a name and make use of that name instead of typing it each time.
This is called named query
To do this we will use @NamedQueries and @NamedQuery annotations. These are written in the Entity Classes.
NamedQueries will store an array of NamedQuery

Example:
<Code>
package com.example.entity;

import ....

@NamedQueries({
	@NamedQuery(name = "listEmpsInBasicRange",
			query = "SELECT e FROM Employee e where e.basic between :limit1 and :limit2"),
	@NamedQuery(name = "listEmpsWithLastName",
			query = "SELECT e FROM Employee e where e.lastName = :lnm")
})

@Entity
@Table(name = "emps")
public class Employee implements Serializable{
	.....
}
</Code>

Once we have the NamedQueries, we can use them where ever we want to.
To user it we use createNamedQuery("NamedQuery", ClassName) on the EntityManager object.

<code>
	TypedQuery<Employee> empQuery = em.createNamedQuery("listEmpsWithLastName",Employee.class);
	empQuery.setParameter("lnm", "Aripaka");
</code>

Relationships
--------------

Is A relationship uses @Inheritence(strategy=)
There are 3 possible values for strategies.

1. SINGLE_TABLE - All classes involved in the inheritance will be involved in a single table.
Employee class<-------Manager
			  <----- ContractEmployee
We create a single table that will have properties of Employee, Manager, ContractEmployee.
We will have an extra column which uses annotations called @DiscriminatorColumn and @DescriminatorValue to tell us which type of class it is.

<code>
	@Entity
	@Table(name = "ALL_EMPS")
	@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
	@DiscriminatorColumn( name = "etype", discriminatorType = DiscriminatorType.CHAR)
	@DiscriminatorValue("E")
	public class Employee implements Serializable{}
</code>
The DiscrimatorColumn annotation has 2 attributes. name and discriminatorType.
The DiscriminatorValue contains only 1 parameter.
The @Inheritance and the @DiscriminatorColumn are only given to the parent class.
<code>
	@Entity
	@DiscriminatorValue("M")
	public class Manager extends Employee{
</code>
the child classes need to specify its own @DiscriminatorValue

Creating The employees, manager and Contract Employee
<code>
	public static void createEmployees() {
//			persistenceUnitName - mysqlPU
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
			EntityManager em = emf.createEntityManager();
			// transaction is better to create transaction if there are multiple insert or update.
			EntityTransaction txn = em.getTransaction();
			// starting transaction
			txn.begin();
			Employee emp = new Employee(101L, "Vansy", "Aripaka", 25000.0, LocalDate.now());
			Manager mgr = new Manager(102L, "Srinu", "Dachepalli", 55000.0, LocalDate.now(), 8525.0);
			ContractEmployee cemp = new ContractEmployee(103L, "Suseela", "Aripaka", 35000.0, LocalDate.now(), 12);
			em.persist(emp);
			em.persist(mgr);
			em.persist(cemp);
			txn.commit();
			em.close();
	}
</code>

Retrieving data
<code>
public static void getEmployees() {
//		persistenceUnitName - mysqlPU
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		
//		gets all the employees, manager, Contract emp
		final String eQry = "SELECT e from Employee e";
//		gets all the  managers
		final String mQry = "SELECT m from Manager m";
//		gets all contract employees
		final String ceQry = "SELECT ce from ContractEmployee ce";
		List<Employee> emps = em.createQuery(eQry,Employee.class).getResultList();
//		List<Manager> mgrs = em.createQuery(mQry,Manager.class).getResultList();
//		List<ContractEmployee> cemps = em.createQuery(ceQry,ContractEmployee.class).getResultList();
		for(Employee emp : emps) {
			System.out.println(emp);
		}
//		for(Manager emp : mgrs) {
//			System.out.println(emp);
//		}
//		for(ContractEmployee emp : cemps) {
//			System.out.println(emp);
//		}
		em.close();
	}

</code>
jpa-hibernate-demo-2

2. JOINED   
-----------
The joined table will create seperate table for each class. The common columns are stored in the Parent table.
And only the class specific columns will be stored into the child tables. The child tables will have a FK from the parent table id.
We use this when the child classes have too many distinctive properties compared to the parent.
Using SINGLE_TAble is not a good strategy here as there will be too many null values in the table.
To achieve this the staregy has to be set to InheritenceType.JOINED
and each  class will have will have a different table name.
@DiscriminatorColumn, @DescriminatorValue is not necessary.
jpa-hibernate-demo-3



3. Table per class.

Each class will be mapped with a separate table in the database
The @Inheritence(strategy = InheritenceType.TABLE_PER_CLASS)
checkout jpa-hibernate-demo-2

Has A Relationship:
-------------------
Composition
-----------
Including Once class into another class and the they will not have seperate table. theyu will have same table.
@Embeddable - applied on the class internal object
@Embedded - applied on the internal object 

For Example if Employee is composed of address, then the Address class will be labeled with @Embeddable.
The address object in the Employee class will be tabled @Embedded.

checkout jpa-hibernate-demo-4

Aggregations
--------------
In RDBMS This will simply create a fk relationship as this is a one directional relationship in rdbms and bi directional relationship when it comes to programming languags.
1 to 1 - Employee has a bank account
	@OneToOne
	employee object as BankAccount as a field
	bankAccount object as a field for employee class.
	both should have @OneToOne
	@JoinColumn
	This represents in which class we want to create the FK in RDBMS.
	This is applied on a field of one class based on which Entity is dominant entity.
	For Example We have a company that stores employee details, then Employee class is the dominant Entity and we need to create the accountHolder object will get the foreign key in the BankAccount class
	If we are a bank that stores details of Banks, then the Bank class is the dominant entity and and we need to create the account object will get the foreign key in the Employee class
	
1 to m
	@OneToMany
	One Department has many employee   - On List or a set of Employee objects as a field in the department
m to 1
we will have the mappedBy, fetch, cascade properties.This will be dont on OneToMany
	@ManyToOne
	Many employees had one department - on department object as a field in an employee class. 
	
@JoinColumn must be applied on the department object in the employee class as it is the single object.

we use use a Set of Employees ,in the Department class, we need to make the Employee class Comparable as It has to compare the new Employee with the old ones to tells if its not the same employee. This is set does not allow duplicates and compares with other Employee objects.


The OneToOne,OneToMany, ManyToMany have parameter called mappedBy, fetch, cascade. These are properties that we specify for the non FK field. 
	mappedBy this helps to establish a backword relationship This value of this parameter will be name of the object that has the @JoinColumn annotation. The parameter is set to the object where @JoinColumn does not exist.
	fetch tells us how to fetch it. Do we need EagerLoading or LazyLoading. Generally we will go with the Lazy loading as we dont want to perform joins unnecessarily.
	cascade tells us what needs to be done when the associated row goes through some changes in the table. there are 5 different values. all, detach, merge, persist, refresh, remove.
	All basically means the same operation happening on the BankAccount should happen with the Employee.
	Detach. When a Employee is deleted, the FK of BankAccount will be set yo null.
	Merge - When Employee is updated, BankAccount will also be updated.
	Persist-When Employee inserted, BankAccount will also be inserted.
	Refresh - When Employee is refreshed then BankAccount is refreshed.
	Remove, when Employee is removed, Bank account is deleted.
when we do this we dont need to persist each object. 

Typical @OneToMany / @ManyToOne Example Using @JoinColumn

In a @OneToMany / @ManyToOne relationship:
    Owning Side: The side with the @ManyToOne is the owning side, and it controls the foreign key column.
    Inverse Side: The @OneToMany side uses mappedBy to indicate that it is not responsible for managing the relationship.

<code>
In Department Class
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
In Employee class    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

</code>


for both One to Many and Many to one check jpa-hibernate-demo-6

when retrieving data using these classes with relationships, hibernate performs cross join 


m to m - @ManyToMany.
	Both classes will have multiple objects
	Along with @ManyToMany, we need to use the @JoinTable for the object that owns the other object.
<code>
	@ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
</code>



In a `@ManyToMany` relationship in Hibernate, the `@JoinTable` annotation should **only be defined in one of the classes**—the "owning" side of the relationship. The other side should use the `mappedBy` attribute to indicate that it is the inverse side of the relationship.

### Why only one `@JoinTable`?
- **Owning Side**: The side where the `@JoinTable` is defined controls the relationship and how the join table is managed.
- **Inverse Side**: The side with the `mappedBy` attribute is a mirror of the relationship and does not manage the join table.

### Example Clarification
In the previous example:
Student is the owning side (it defines the `@JoinTable`):
  ```java
  @ManyToMany
  @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> courses = new HashSet<>();
  ```
  
Course is the inverse side (it uses `mappedBy`):
  ```java
  @ManyToMany(mappedBy = "courses")
  private Set<Student> students = new HashSet<>();
  ```

### What happens if `@JoinTable` is used in both classes?
- Hibernate would treat both sides as independent relationships, leading to unexpected behavior.
- Instead of one shared join table, Hibernate may try to create two different join tables, or it may lead to errors depending on the configuration.

### Best Practice
Always define the `@JoinTable` on one side only (typically the more "logical" or commonly accessed entity) and use `mappedBy` on the other side to avoid redundant table definitions and maintain proper bidirectional relationships.

# Spring
-----------
Spring is a Java EE Development Platform
String Provides a lot of modules(around 16-17). Also provides dev tools for development, packaging, deployment more trackable.

1. Multi-Modularized - Spring is divided into multiple modules.
	spring core
	spring web mvc
	spring Test
	spring Data
	spring Boot
	Sting Security.
	Spring ORM
	spring AOP
	spring AspectJ
	spring Portlets
	spring Cloud
	-------- etc
	We will mainly concentrate on core, web mvc, Test, Data, Boot, Security, Spring ORM
	we dont need to load entire string platform. we include only the necessary ones in the project. Saves memory.
2. Cross-platform - Will be discussed later.
3. pluggable - If we want to use a third party library which is better than Spring modules, we can include it in the project. Its not restricted to spring packages.
For Example insteads of using Spring MVC we can use Struts.
4. Light weight - As we have multi modularity for spring, it is lightweight.

Spring is a light weight DI container.
container is some software that can handle the lifecycle of a java object. The java object lifecycle is being managed by the container are called beans.
DI- Dependncy Injection
This is why we call it a development platform instead of calling a library or a package.

Note: java beans and beans.xml are not the same. beans.xml file is used for inversion of control will the dependency injection.

Dependency (Functional Dependency)
-------------------------------
If a piece of software is dependent on another software to achieve a task, then they are functionally dependent.
In the multi later architecture that we used till now, each layer is dependent on the other layer. For Example, Controller is dependent on Service layer was dependent on the DAO layer.

For example:
<code>
	interface StudenttDAO
	class StudentJdbcDAOImpl implements StudenttDAO{
		-------------JDBC implementation-----------
	}
	class StudentJpaDAOImpl implements StudenttDAO{
		-------------JPA with hibernate implementation-----------
	}
	interface StudentService
	class StudentServiceImpl implements StudentService{
		StudenttDAO dao;
		public StudentServiceImpl(){
			//dao = new StudentJdbcDAOImpl();
			dao - new StudentJdbcDAOImpl();
		}
	}
</code>

As we can see from the example when we add new implementation like jpa implementation for the project we need to update service layer aswell.
If a real world project is being converted from jdbc to jpa, we make changes in every service which is hard to keep track of and very time consuming.
This is where Dependency Injection comes in.


Dependency Injection
--------------------
Instead of creating a new DAO object we can pass it as a parameter to the Service class and the controller will be responsible for creating the DAO object. 
The main goal here is that we should not modify the Service layer classes.
 This creates a separation of concern. No matter which dao we pass into the Service, the service will perform the operation. The service does not specifically know which dao its working with.As we have the interface for the dao, the jdbc and the jpa implementations will have the same methods and the service simply calls it. Depending on which object is injected into the Service class the way that the task is achieved will differ but service does not care about it.
<code>
	interface StudenttDAO
	class StudentJdbcDAOImpl implements StudenttDAO{
		-------------JDBC implementation-----------
	}
	class StudentJpaDAOImpl implements StudenttDAO{
		-------------JPA with hibernate implementation-----------
	}
	interface StudentService
	class StudentServiceImpl implements StudentService{
		StudenttDAO dao;
		public StudentServiceImpl(StudenttDAO dao){
			this.dao = dao;
		}
	}
	class Controller{
		// dependency injection by constuctor.
		// StudentService studentService = new StudentServiceImpl(new StudentJdbcDAOImpl());
		StudentService studentService = new StudentServiceImpl(new StudentJpaDAOImpl());
	}
</code>

we can create the dependency injection using 
1. constructor injection
2. setter injection - using setter method. very similar to constructor injection
3. field injection - using java reflection classes

The above options are called IoC(Inversion of control). until now, the controller called service and the service called the dao. now the controller creates a dao and passes it to service the action is reversed.

Instead of creating these IoCs manually, we will use Spring IoC Container which automates the process

Spring IoC Container:
--------------------
2 main containers
BeanFactory
ApplicationContext

Using these 2 we can configure the application in way that when we provide details of which dependencies are required, spring will automatically create the bean object with all the dependency injections performed on the beans.
For the BeanFactory or the ApplicationContect we specify the number of classes we have in the project, All the dependencies they have. Then the BeanFactory or the application context will create the object necessary for us with all the injections necessary. 
To tell about the  classes and dependencies to the Spring we use the config files.
The we can simply use the pre created object provided by the Spring.

The configurations are for number of beans and the dependencies needed are to be provided in "Bean Definition Configuration"

Bean Definition Configuration
-----------------------------
We can create Bean Definition Configuration in 3 ways
XML based Config
Annotation Based configuration
Java Based Configuration

We need to learn all three configurations as XML based config, we might get it in interviews.

XML Based Bean Config
----------------------
We can call this with any name. for simplicity, we will call it beans.XML


<beans>
	<bean id=""
			class=""
			score=""
			init-method=""
			destroy-method=""
			autowired ="">
		<constuctor-arg index= "" value="" ref="" />
		<propery name="" value="" ref="" />
	</bean>

</beans>


We will have a beans root element. Inside it, we will have bean element with an id property to refer to the bean in java.
then we will have a class attribute which is the fully qualified name of the class foe which we want to create a bean.
we will talk about score attribute later.
possible values for score attribute are singleton, prototype, request, session, global-session.
the method provided in the init-method attribute will be called each time the bean object is created. Similarly, the method provided in the destroy-method attribute will be called each time the bean object is destroyed.
autowired contains values non,byType, byName, byConstructor.
We will talk about this later
inside this bean element we will be able to inject another bean object. This can be done by using 
constructor-arg - used for constructor injection
propery - used for setter injections

our bean.xml will look something like this


Example:
spring-core-demo-xml project
Step1:
needed dependency: spring core for dependency injection, spring context for containers.
pom.xml file:
<code>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>spring-core-demo-xml</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>SpringCoreDemoXml</name>
  
  <properties>
  	<spring-version>6.2.2</spring-version>
  </properties>
  
  
  <dependencies>
  		<!--For Dependency injection-->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-core</artifactId>
		    <version>${spring-version}</version>
		</dependency>
		<!--Provides spring containers-->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-context</artifactId>
		    <version>${spring-version}</version>
		</dependency>
  </dependencies>
 
</project>

</code>

Step 2:
We have simple project where we created which uses the doWelcome service to display a welcome message . Now how do we make it in a way that the spring will create this instead?
We create a xml file that will store the spring beans config. In this case we create beans1.xml in the resources folder.
This xml will have the beans element and the bean element inside it like we learnt above.

bean1.xml
<code>
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
	    <!-- Define a service bean -->
	    <bean id="wsb" class="com.example.service.WelcomeServiceStandarImpl" />
	</beans>
</code>



Note: if there is an error in eclipse saying that unable to download external files, go to window ->preferences -> XML(Wild Web Developer)
Then enable download external resources (xsd,..)

Note: the bean definition class attribute should not have .java at the end.

Step3:
Using the bean file we created:
In the WelcomeApplication1.java file, we will create ApplicationContext object.
ApplicationContext is an interface. There are many implementations for it and we can use one of it. in  this case, we will use ClassPathXmlApplicationContext class to create the object. weneed to provide the name of the xml file to this.

<code>
	ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");
</code>
This will create a container called context. It we read all the bean definition in the beans1.xml and pulls the beans from the there.

Step4:
Now we can use the context object to create a WelcomeService object. The context object contains a getBean method to which we provide id of the bean we provided in the bean configuration. 

<code>
	WelcomeService ws = (WelcomeService) context.getBean("wsb");
	System.out.println(ws.doWelcome("Srivatsan"));
</code>
At this level we dont have any dependency in the project, so what we created with the beans does not help us a lot.
So let create a bit more advanced implementation. Will create WelcomeServiceAdvancedImpl.java which wil implement the WelcomeService interface.


<code>
	package com.example.service;
	public class WelcomeServiceAdvancedImpl implements WelcomeService {
		private String welcomeMessage;
		public WelcomeServiceAdvancedImpl() {	
		}
		public WelcomeServiceAdvancedImpl(String welcomeMessage) {
			this.welcomeMessage = welcomeMessage;
		}
		@Override
		public String doWelcome(String userName) {
			// TODO Auto-generated method stub
			return welcomeMessage + userName;
		}
		public String getWelcomeMessage() {
			return welcomeMessage;
		}
		public void setWelcomeMessage(String welcomeMessage) {
			this.welcomeMessage = welcomeMessage;
		}
	}
</code>

we can see that this service is ready for constructor injection and setter injection.
Now we will see how we can use spring for injecting the welcome message.

To do this, we need to create a bean element in the bean1.xml
The new bean object will have id and the class for the new class.
As the class needs parameter in the setter method, we need to create a property tag which has name and value attributes.
the name attribute tells the name of the parameter and the value tell the value to the parameter that we want to inject.

<code>
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	                    https://www.springframework.org/schema/beans/spring-beans.xsd">
	    <!-- Define a service bean -->
	    <bean id="wsb" class="com.example.service.WelcomeServiceStandarImpl" />
		<bean id="wsab1" class="com.example.service.WelcomeServiceAdvancedImpl" >
			<property name="welcomeMessage" value="Namasthey! Please come!" />
		</bean>
	</beans>
</code>

To Use the constructor injections we have to use the constructor-arg tab instead.
it has 2 attributes. 
1 index - index tell which value does 
2 value - the value we are injecting.

<code>
	<bean id="wsab1" class="com.example.service.WelcomeServiceAdvancedImpl" >
		<constructor-arg index="0" value="Namasthey! Please come!" />
	</bean>
</code>


Note: The Service class should have both default and parameterized constructor. If not, the setter injection will not work.

Normally we will try to inject DAO into Service . Like we learn in previous topics. Now how do we inject a dao into the service using spring?
We will be injecting one bean into another bean.

The current example does not use a dao directly. but we will see and example of of how to inject one bean into another.
WE create a MessageProvider interface and implement it with the MessageProviderImpl.

<code>
	package com.example.service;

import java.time.LocalTime;

public class MessageProviderImpl implements MessageProvider{
	@Override
	public String getMessage() {
		String message = null;
		int h = LocalTime.now().getHour();
		if(h>=4 && h<=11) {
			message = "Good Morning";
		}else if(h >= 12 && h <= 16) {
			message = "Good AfterNoon";
		}else {
			message = "Good Evening";
		}	
		return message;
	}
}
	

</code>


Then we create a WelcomeServiceEnhancedImpl which gets the MessageProvider injected into it.

<code>
	package com.example.service;

public class WelcomeServiceEnhancedImpl implements WelcomeService {
	private MessageProvider msgProvider;
	public WelcomeServiceEnhancedImpl() {
	}
	public WelcomeServiceEnhancedImpl(MessageProvider msgProvider) {
		this.msgProvider = msgProvider;
	}
	@Override
	public String doWelcome(String userName) {
		// TODO Auto-generated method stub
		return msgProvider.getMessage() +  " "+ userName;
	}
	public MessageProvider getMsgProvider() {
		return msgProvider;
	}
	public void setMsgProvider(MessageProvider msgProvider) {
		this.msgProvider = msgProvider;
	}

}
	
</code>
Now its time to create the beans for MessageProviderImpl and WelcomeServiceEnhancedImpl and link them.
To link two beans we need to provide the ref attribute of the constructor-arg of WelcomeServiceEnhancedImpl the ref attribute gets the id of the MessageProvideImpl bean. we can also create property element if we want to make use of the setter injection.

<code>
	<bean id="mp" class="com.example.service.MessageProviderImpl" >
	</bean>
	<bean id="wseb1" class="com.example.service.WelcomeServiceEnhancedImpl" >
		<constructor-arg index="0" ref="mp" />
		<!--<property name="msgProvider" value="Namasthey! Please come!" />-->
	</bean>
</code>

If we take a look at the main method. Most of the code is pretty much the same except for the name of the bean we are using. This creates separation of concern.

Scope:
-------
Controls how the objects are created when we request a bean. It tells if we create a bean each time a bean is created or not. 
Singeton, prototype, request, session, global-session
request, session, global-session can be used only when we use we create a web appication.
Lets look at Singeton, prototype for now.

If we use a Singleton scope it only one bean is created and the same bean is shared each time a request is made. If its a prototype, then it gives a bean each time we request.

We create a bean2.xml file for this. Will have

<code>
	<bean id="wsab" class="com.example.service.WelcomeServiceAdvancedImpl" >
		<property name="welcomeMessage" value = "Namasthey! Please come!" />
	</bean>
</code>

We can change the data in the bean using the java program aswell.
In our WelcomeApplication2, we type cast the WecomeService to WelcomeServiceAdvancedImplimentation to get access to the setWelcomeMessage() message.
<code>
	// changing the parameter value of the bean using java.
		((WelcomeServiceAdvancedImpl ) ws).setWelcomeMessage("Hello");
		System.out.println(ws.doWelcome("Srivatsan"));	// Hello Srivatsan
		// requesting the same bean again
		WelcomeService ws2 = (WelcomeService) context.getBean("wsab");
		System.out.println(ws2.doWelcome("Srivatsan")); // Hello Srivatsan
</code>

after changing the parameter of the bean, we are trying to request the bean again.
so now will it give the same bean as the first one with the modified parameter or will it give us a new bean without any modification?
the answer for this is it will give us the bean with the modified parameter.
This is because the defautl scope for the bean is Singleton.
But we can change this by setting the scope parameter 

We create the bean3.xml which has the same exact bean with same bean config scope set to prototype.
We can then see the how things change.
<code>
	<bean id="wsabProto" class="com.example.service.WelcomeServiceAdvancedImpl" scope="prototype" >
		<property name="welcomeMessage" value = "Namasthey! Please come!" />
	</bean>
</code>


if we use the scope with request as its value ,for each request made by the client to the web web app, a new bean will be created. 
if it is set to session a new bean will be created when a new session is created.
if we use global-session, the bean will be maintained for the whole servlet context.
We will do that when we work with Spring  MVC.

Auto Wiring:
-----------
Wiring: injecting dependency into appropriate place.
We will create the beans. But injecting the beans happens automatically even if it is not configured properly to constructor or setter injection.
Auto wiring is none by default.

Can be done in 3 ways.
byName 
	if id="bean1" depends on id="bean2"
	bean1 must have a parameter in the code by name bean2
	try to inject using constructor/setter if constructor is not available
byType 
	if id="bean1" depends on id="bean2"
	bean1 must have a parameter whose data type matches the type of bean2's type
	try to inject using constructor/setter if constructor is not available
constructor.
	try to inject using constructor by type

Let create bean4.xml contains the mp bean and the wseb bean from the bean1.xml.
But now we dont provide any property for the wseb

<code>
	<bean id="mp" class="com.example.service.MessageProviderImpl" >
	</bean>
	<bean id="wseb" class="com.example.service.WelcomeServiceEnhancedImpl" />
	
</code>

If we now use this bean in the main method, we will get an null pinter excepption. This is because, by default the  property is null.

<code>
	ApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
	WelcomeService ws = (WelcomeService) context.getBean("wseb");
	System.out.println(ws.doWelcome("Srivatsan"));
</code>

To fix this, we use the autowire property for the bean that will receive the other bean as parameter.


<code>
	<!--byType-->
	<bean id="mp" class="com.example.service.MessageProviderImpl" >
	</bean>
	<bean id="wseb" class="com.example.service.WelcomeServiceEnhancedImpl" autowire="byType" />
</code>

here we cannot use the byName as the criteria for name is not matching. We will be using byType. Will will look at the byName next.

We need to made use that the id of the MessageProviderImpl bean matches the parameter of the WelcomeServiceEnhancedImpl constructor or setter.

<code>
	<!--byName-->
	<bean id="msgProvider" class="com.example.service.MessageProviderImpl" >
	</bean>
	<bean id="wseb" class="com.example.service.WelcomeServiceEnhancedImpl" autowire="byName" />
</code>
This is also in bean4.xml

 
Collection Injections:
-----------------------
How to inject a collection (list, set)
We cannot use the same approach as earlier.
We have a list tag and a set tag available  which we can put inside the property tag.
the list tag will have a bunch of value tags inside it.

<code>
	<bean id="b1" class="MyBean">
		<property name="sts">
			<list>
				<value>s1</value>
				<value>s2</value>
				<value>s3</value>
				<value>s4</value>
			</list>
		</property>
	</bean>
</code>

Note: this might seem not feasible to have hundreds of values. This is just for interviews. Not for actual development.


To inject a set we simply replace the list tag to set

For maps, we need an entry tag that contains key and value as attributes

 <code>
	<bean id="b1" class="MyBean">
		<property name="sts">
			<list>
				<entry key="k1" value="10"/>
				<entry key="k2" value="20"/>
				<entry key="k3" value="30"/>
			</list>
		</property>
	</bean>
</code>

Examples are not in files.
if the values inside the list are not primitive, we can use a ref tag inside the list tag

<code>
    <!-- Define Notification Channel Beans -->
    <bean id="emailNotification" class="com.example.EmailNotification"/>
    <bean id="smsNotification" class="com.example.SmsNotification"/>
    <bean id="slackNotification" class="com.example.SlackNotification"/>
    <!-- Inject the List of Channels into the NotificationService -->
    <bean id="notificationService" class="com.example.NotificationService">
        <property name="channels">
            <list>
                <ref bean="emailNotification"/>
                <ref bean="smsNotification"/>
                <ref bean="slackNotification"/>
            </list>
        </property>
    </bean>
</code>

Annotation based configuration:
--------------------------------
We will not use any xml. instead will be use java annotations.
<bean id="b1" class="..." scope="prototype"> can be done by using Component annotation which is same as bean tag . we can provide an id to it. the scope attribute has a Scope annotation. These apply on a calss.
<code>
	@Component("b1")	
	@Scope("prototype")
	public class MyBean{
	}
<code>
if id is not provided, then the class name becomes the id.

Field Annotations
@Autowired annotation will be applied on field, it does field injection . if applied on constructor, performs constructor injection. if applied on setter, performs constructor injection.

@Qualifier("name") - autowire byName. Needs to be written along with the Autowired annotation. if not specified, its autowired by type. which is the best way to autowire.


@Value("") - Appinjecting premitive values. Also supports spring expression language.

We need to specify what package needs to be scanned to find where these annotations are applied and we need to have a configuration. we need to have @Configuration and @ComponentScan on a configuration class that we will be building.

<code>
	@Configuration
	@ComponentScan("package that has ourComponent")
	public class MyConfiguration{
	}
</code>

Note: if we dont mention the package the @Components will not be loaded.


The component annotation does not tell us what king of bean it is. so we have the following annotations that tells what kind of bean it is.
if our bean is not any one of @Service, @Repository @Controller @RestController @Resourcem then we use @Component.
@Component
	@Service - all services
	@Repository -- all daos
	@Controller -- all controllers
	@RestController - all rest controllers
	@Resource - all resources.

Project1:
spring-core-demo-annot

We copied the xml project and deleted all xml files and extra classes with main methods.

Now we create a config class in the com.example.config
<code>
	package com.example.config;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	@Configuration
	@ComponentScan("com.example.service")
	public class MyConfig {
	}
	
</code>

WE update the MessageProviderImpl with the @Component as it is not exactly a service or a dao.

<code>
	----
	import org.springframework.stereotype.Component;
	@Component
	public class MessageProviderImpl implements MessageProvider{
		@Override
		public String getMessage() {
			-----
		}
	}
	
</code>

for each service we provide @Service
For injecting primitive values, we use the Value annotation
For autowiring, we use @Autowired

Now to use this in the main method, we need to make some changed. instead of 
ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");
we use
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);


Pros and Cons of Annotation based config:
----------------------------------------
pros:
No need xml config.
Easier.

Cons:
in xml config the beans can be created for classes created by us or for class that are in a package that we got from the internet. as we simply need to create a bean file with the qualifying name of the class.
We cannot do this with the annotations based conig as these annotations has to be written inside the classes and we will not have access to the classes if we use jar files that we download.

To fix this we use java based config

Java Based Configuration:
------------------------
It is an  to Annotation based config.
we can create beans using java inside the Configuration class. this can be done by using the @Bean

for example:

<code>
	@Configuration
	@ComponentScan("package that has ourComponent")
	public class MyConfiguration{
		@Bean
		@Scope("prototype")
		public LocalDate today(){
			return LocalDate.now();
		}
		@Bean
		public Scanner scan(){
			return new Scanner(System.in);
		}
	}
</code>

The name of the method becomes the if and the return type becomes the qualifying classname if we write the same thing in xml bean.


usage in main:
<code>
	System.out.println(context.getBean("today"));
</code>


Spring Expression Language
-------------------------
We need a new dependency for this.

<code>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>${spring-version}</version>
	</dependency>
</code>

We use @Value for primitive value. It can also accept a spring expression language , evaluate it and provide appropriate values to inject.
Always has a format of @Value("#{bean}") or @Value("#{bean.property}")

if a bean is available in the config class and if we want to inject it as a parameter to a method, we can use this.

for example:
we had the getMessage() in the MessageProviderImpl, which uses a LocalTime object.
<code>
public String getMessage() {
		String message = null;
		int h = LocalTime.now().getHour();
		...
}	
</code>
Instead of doing so, we can use the exression language like this.
<code>
	@Component
	@Scope("prototype")
	public class MessageProviderImpl implements MessageProvider{
		@Value("#{today.hour}")
		private int h;
		@Override
		public String getMessage() {
			String message = null;
			if(h>=4 && h<=11) {
				message = "Good Morning";
			}else if(h >= 12 && h <= 16) {
				message = "Good AfterNoon";
			}else {
				message = "Good Evening";
			}	
			return message;
		}
	}
		

</code>

if the method has it as parameter we can apply the Value annotation for the parameter.

Property File Injection
-----------------------
Instead of using @Value("Hello! Welcome!") for a field, which is basically meaningless we can read a .properties file and set it to the field.
lets create a .properties file in the resources folder which will have  key value pair 
checkout application.properties

To use the properties 
we need to use @PropertySource("fileName") on the configuration class

To use it
<code>
	@Value("#{'${welcome.message}'}")
	private String welcomeMessage;
</code>


Spring Web MVC
------------
Used to develop Model View Controller.
Helps to Standardize the web application development so the whole team can concentrate on the task instead of worrying about the architecture of the app.

It contains tools to made project with almost 0 configurations.

In a MVC application with single front controller, it does not mean that there will be only one controller inthe whole app.

WE can have any number of controllers and there will be one front controller that use one of the other controllers that are available
DAO1 <--Model--> SERVICE1<--Model--> Controller1
DAO2 <--Model--> SERVICE2<--Model--> Controller2
DAO3 <--Model--> SERVICE3<--Model--> Controller3 <--Model & viewName--> FrontController <---->clients
DAO4 <--Model--> SERVICE4<--Model--> Controller4							 ↓ ↑
DAO5 <--Model--> SERVICE5<--Model--> Controller5 							 Model
																			 ↓ ↑
																			 View


Client = Browser
View = JSP/HTML + js + CSS
Front Controller = DispatcherServlet. Accepts any request that comes from the client. url("/*")
Service = validations and business logic
DAO = DAtabase Entity.
Controller = Any java class(POJO) that has a method that handes a request. Such methods are called actions.
The action must return a viewName or model or both .



DispatcherServlet
Receives any request from the client and forwards the request to corresponding controller
This corresponding controller performs the action necessary and returns viewName and model to the DispatcherServlet.
The Dispatcher Servlet will then return the view to the client.

We need to setup a bean file for this aswell.

How does we map a url to a non from controller or an action?
A non front Controller will have actions related to one specific url.
We can use something called HandlerMapping (an interface) in spring to tell the Dispatcher Servlet know where the incoming request needs to be forwarded to.

There are 3 ways we can implement the handler mapping
1. SimpleUrlHandlerMapping - Default Url mapping.
	@RequestMapping - helps to map a url to a controller and to an action in the controller.
2. BeanNameUrlhandlerMapping 
	if the incoming request url has the bean name of a controller, then that controller is passes with that request. We will not use this as it is not possible to create a controller for each request separately.

3. ControllerNameUrlHandlerMapping (deprecated)


How does the viewName mapped to a specific view?
DisplaycherServlet uses a ViewRolver(interface) for this. 

ViewRolver has the following implementations
1. InternalResourceViewResolver
	has 2 properties suffix and prefix
	we use a formula here where pathOfAview = prefix + viewName + suffix
	For example
	lets say
	suffix = .JSP
	prefix = /WEB-INF/pages/
	viewName = home
	pathOAView = /WEB-INF/pages/home.jsp
2. ResourceBundleViewResollver
	we create a .properties file that has pathofTheView and the viewName
3. XmlResourceViewResolver
	we create a .xml that contains the mappings to viewname and pathName


We can use more than 1 resourceViewResolver.
We can give a priority to specific viewResolvers and based on that a specific resolver will be chosen 
This can be done by using a property order.

Project Name: spring-web-mvc-demo
Project Setup:
maven project with war packaging
note: Spring 6.2.2 did not work properly for us. we were not able to reach the url properly so we used 5.1.3.RELEASE
1 Setup pom.xml file
<code>
<properties>
  	<java-version>1.8</java-version>
  	<spring-version>5.1.3.RELEASE</spring-version>
  </properties>
  
  <dependencies>
  	<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
		<groupId>javax.servlet</groupId>
	    <artifactId>javax.servlet-api</artifactId>
	    <version>3.0.1</version>
	    <scope>provided</scope>
	</dependency>
	<!-- JSTL for JSP -->
		<dependency>
		    <groupId>jakarta.servlet.jsp.jstl</groupId>
		    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		    <version>2.0.0</version>
		</dependency>
		<dependency>
		    <groupId>org.glassfish.web</groupId>
		    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
		    <version>2.0.0</version>
		</dependency>

  </dependencies>
  
  
  <!--Buid plan-->
  <build>
  	<finalName>SpringWEbMvcDemo</finalName>
  	<pluginManagement>
  		<plugins>
  			<!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin -->
			<plugin>
			    <groupId>org.apache.maven.plugins</groupId>
			    <artifactId>maven-compiler-plugin</artifactId>
			    <version>3.8.1</version>
			    <configuration>
			    	<source>${java-version}</source>
			    	<target>${java-version}</target>
			    </configuration>
			</plugin>

  			<!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-dependency-plugin -->
			<plugin>
			    <groupId>org.apache.maven.plugins</groupId>
			    <artifactId>maven-dependency-plugin</artifactId>
			    <version>3.1.1</version>
			    <configuration>
			    	<warSourceDirectory>src/main/webapp</warSourceDirectory>
			    	<warName>SpringWebMvcExample</warName>
			    	<failOnMissingWebXml>false</failOnMissingWebXml>
			    </configuration>
			</plugin>
  		</plugins>
  	</pluginManagement>
  </build>
  
</code>

Step 2:
Configure DispatcherServlet (front controller)
Its a servlet that spring automatically provides. We need to send all the requests to the dispatcher servlet and we have to load it into our context.
To configure the DispatcherServlet, we cannot use as its already provided by spring.
We can use something called Web adapter.
We also need to create Spring bean configuration class.

So we need to create 2 config classes for this in our project
SpringWebMvcDemoConfig - Spring related config
WebAppConfig - web app related config - this is the startup of our project. we need to create the web application adapter, create web application context and load the adapter into the context.

<code>
	// WebAppConfig
	package com.example.swmd;

import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


// equivalent to  web.xml
public class WebAppConfig implements WebApplicationInitializer{
	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
		// TODO Auto-generated method stub
AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		// registering the config cass
		webCtx.register(SpringWebMvcDemoConfig.class);
		// associating web application context with servlet context
		// web app context managed by spring
		// servlet context is managed by tomcat.
		// this merges both of them so that we can use it as one single context.
		webCtx.setServletContext(servletContext);
		//		if we use wen xml
		/*
		 * <web-app>
		 * 	<servlet>
		 * 	<servlet-name>
		 * 		dispatcher
		 *  </servlet-name>
		 *  <servlet-class>
		 *  	org.springframework.web.servlet.DispatcherServlet
		 *  </servlet-class>
		 *  <load-on-startup>1</load-onstartup>
		 *  <servlet-mapping>
		 *  	<servlet-name>
		 * 			dispatcher
		 *  	</servlet-name>
		 *  	<url-pattern>
		 *  		/
		 *  	</url-pattern>
		 *  </servlet-mapping>
		 * 	</servlet>
		 *  </webapp>*/
		
//		Providing custom name for the dispatcher servlet.
		ServletRegistration.Dynamic servlet =
				servletContext.addServlet("dispatcher", new DispatcherServlet(webCtx));
		servlet.setLoadOnStartup(1);
//		mapping all requests to the dispatcher servlet.
		servlet.addMapping("/"); // the / and /* are considered as same.
	}
}

</code>

The WebAppConfig can be simlified by a tool which will learn later.

<code>
	//SpringWebMvcConfig
	package com.example.swmd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@ComponentScan("com.example")
@EnableWebMvc
//public class SpringWebMvcDemoConfig implements WebMvcConfigurationSupport {
// we dont need to perform the implementation as we have the @EnableMcv
public class SpringWebMvcDemoConfig  {
	// SimleUrlHandlerMapping is used by default. we just need to configure the viewResolver
	// We will use InternalReourceViewResover.
	// As its not a bean that we are creating, we need to set it here
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//setting prefix and suffix
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		// setting priority - if there are multiple resolvers
		viewResolver.setOrder(1);
		return viewResolver;
	}
}

</code>

Step 3: Creating a controller
With all the config complete, we can create a controller. We dont need to create servlets anymore for the controller like we had before. We can simply create a class and annotate it as a Controller.
<code>
package com.example.swmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class DefaultController {
	// maps to 
	// localhost:9090/spring-web-mvc-demo1
	// localhost:9090/spring-web-mvc-demo1/
	// localhost:9090/spring-web-mvc-demo1/home
	@RequestMapping(value= {"", "/", "/home"}, method = RequestMethod.GET)
	public String getName() {
		// return the view name 
		// the view needs to be placed in the folder specified in the viewResolver
		// in this case its views which needs to be created inside the webApps.
		// name of the file will be home.jsp
		return "home";
	}
	
}


</code>

creating jsp file:
The jsp pages needs to be in views folder as we are using a view resolver which tries to get the files that are available in the views folder of the webapps folder
<code>
	<html>
		<head>
			<title>My Spring Web</title>
		</head>
		<body>
			<h1>Welcome Home!</h1>
		</body>
	</html>
</code>


Spring Boot:
-------------
It is a Spring module that automates the standard configuration.
In the Spring MVC Project we had to create web application initializer, dispatcher servet, route alll the urls to the dispatcher servlet, we had to create web application context and servlet context and link them together, we had to create view resolver just to setup the project properly.
To reduce this we use the Spring boot.
It has special spring boot dependencies instead of normal dependencies.
We get automated standard configurations for any spring related modules(spring-mvc, spring-security,...). We dont need to waste time on managing all the configurations.

Along with auto config, we get a new feature called embedded server.
We dont need to have an external server anymore like tomcat. spring boot comes with its own server. we need to provide which port number the server should use.

Annotations available for spring boot.
@SpringBootApplication - This is same as using #Configuration, @EnableAutoConfiguration, @ComponentScan

Project:spring-web-mvc-boot-demo
Step1 Create a Spring Starter Project.
This will show a dialog to configure all the details related to the project.
change name, group id, artifact id, 
set the engine to Maven, change java version if you like,
the version is auto selected in the next page.
WE simply add the Spring web package for now and click finish.

Alternately we can create a starter project at  https://start.spring.io/
We can also use spring boot cli to create the project.


When we open the pom.xml file,
we see a parent tag which show which project is the parent project.
The parent project contains a lot of config and it is all abstracted away.

the main dependencies that we have now are
spring-boot-starter-web.
tomcat - brings all servlet apis.
spring-boot-starter-test (for testing the application).

also we have spring-boot-maven-plugin for creating the war files.

jstl is not automatically included so we set that up.

We can see that there is ServletInitializer.java and SpringWebMvcBootDemoApplication.java which are auto generated for us and these the only necessary config files in our project. 
We dont need to modify these.
we can see the SpringWebMvcBootDemoApplication class has a @SpringBootApplication which makes it a Spring boot application and it has a main method similar to our console applications.
We can run this application and it can run on the embedded server and if needed, we can run it on our own server that we want.

Spring boot also automates the view resolvers. But we need to specify the prefixes and suffixres.

If we remeber from spring demo project, we had to specify the package that needs to be scanned. But there we dont need to do that. the entire package in which  @SpringBootApplication is defined, that package will be scanned automatically.

all the daos, controller needs to be sub packages of the current com.example package.
We need to use the annotations similar to spring @Controller, @Service @Entity and so on

Step 2: Creating a controller . This is not the front controller. Front controller is auto configured for us.
<code>
// default controller
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefautlController {
	@RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
	public String homeAction() {
		return "home";
	}
}

</code>
Step 3: Building a jsp page
we create a jsp page inside a folder called pages.
<code>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<h1>Welcome Home!</h1>
	</body>
	</html>
</code>

Step4: Configure Prefixes and suffixes for the view resolver.
This done in the application.properties file which is generated for us inside the resources folder at the time of creating the project. the project automatically reads this file when we run the project.

<code>
	server.port=7777
	spring.application.name=spring-web-mvc-boot-demo
	spring.mvc.view.prefix=/pages/
	spring.mvc.view.suffix=.jsp
</code>

Note: application name already existed. Along with the prefix and suffix, we also added the port number for the server.

Step5: We need to make sure the spring boot recognizes the jsp files and render them instead of simply downloading it. when we visit the server.
We need to include jasper engine dependency in pom.xml 
jasper is a jsp processing engine.
in google search tomcat embedded jasper maven

<code>
	<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
	<dependency>
	    <groupId>org.apache.tomcat.embed</groupId>
	    <artifactId>tomcat-embed-jasper</artifactId>
	    <version>11.0.1</version>
	</dependency>
		
</code

Run as spring boot application.
The app should run and it should say that sever is running at port 7777
visit localhost:7777/
no need to use project name.

The problem with the internal server is, if any changed are made, we need to stop the application and run it again.
we can also use a developer dependency in the pom.xml to run the embedded server with live reload. We will look at this later.

Lets create another spring project
spring-web-mvc-boot-demo-2
The project setup is same,
We are going to use Spring Web, Spring Boot Dev tools.
Will restarts server when any changes are made in the project

lets see how a controller can return data and view together

To do this we need to Use ModelAndView class from  web.servlet package.
the method we will use will return ModelAndView object.


Controller code:
<code>
package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	@GetMapping({"", "/", "/home"})
	public String defaultAction() {
		return "home";
	}
	@RequestMapping("/header") // allows get, post or any other method.
	public ModelAndView headerAction() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("header");
		mv.addObject("title", "My Spring boot MVC App");
		mv.addObject("today", LocalDateTime.now());
		return mv;
	}
}

</code>
Properties file:
<code>
spring.application.name=spring-web-mvc-boot-demo-2
server.port = 7777
spring.mvc.view.prefix=/views/
spring.mvc.view.suffix=.jsp
</code>
jsp files:
<code>
home.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"/>
</body>
</html>
</code>

<code>
header.jsp
<header>
	<h1>${title }</h1>
	<h3>${today }</h3>
</header>
</code>

Note: 
In preferences, go to jsp validations settings and set "Include fragment file not found" to ignore.
css and js files go in static folder of resources.

Handling Forms
--------------
To use a form, need to provide an action attribute with the url for which we want to send the request. we can also provide the request method.
Then in the controller, we need to create a method that mapped to the specified url.
The method will have a parameter for each input , select, text area we create inside the form.
The parameter will have @RequestParam annotation added to it.

if we did not provide the action attribute, then the current url that we are on will be taken automatically.

Updating the home.jsp with a form
<code>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/site.css" />-->
<link rel="stylesheet" href="/css/site.css">

</head>
<body>
	<jsp:include page="/header"/>
	<section>
		<form action="greet">
			<label>Enter Your Name:
				<input type="text" name="unm" />
			</label>
			<button>OK</button>
		</form>
		<p>${msg }</p>
	</section>
</body>
</html>
	
</code>

updating the controller:
<code>
package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	@GetMapping({"", "/", "/home"})
	public String defaultAction() {
		return "home";
	}
	@RequestMapping("/header") // allows get, post or any other method.
	public ModelAndView headerAction() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("header");
		mv.addObject("title", "My Spring boot MVC App");
		mv.addObject("today", LocalDateTime.now());
		return mv;
	}
	@GetMapping("/greet")
	public ModelAndView doGreet(@RequestParam("unm")String userName) {
		return new ModelAndView("home", "msg", "Hello!" + userName);
	}
}
</code>

css file:
<code>
	body{
	background-color: #acfccc;
}
header{
	background-color: #aaaaaa;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	padding: 10px;
}

header h3{
	text-align: right;
}

header h1{
	text-align: center;
}

section{
	background-color: #ffffff;
	padding : 15px;
	min-height: 250px;
}
</code>

For one or two inputs this will work perfectly fine. But it is hard to manage when we have a lot of input. for example, when we have registration page.
This is where model attribute can be Used.

@ModelAttribute:
----------------
It maps a model directly with a form.
We use this annotation on the parameter of the function for which the url is mapped.
We create a new form that takes in the loan principal amount,  interestRate and time duration and calculates the total payable amount.
We create a model called Loan.
Then we create a LoanService and its implementation
Then we create a LoanController which is autowired with the loan service.
The controller receives a Loan object as parameter and it use the @ModelAttribute annotation.
Each property in it should match with the form element name attribute.
WE have the loan-form-page.jsp which displays the form and the loan-ouput-page.jsp which displays the results.

Loan Controller:
<code>
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Loan;
import com.example.service.LoanService;

@Controller
public class LoanController {
	@Autowired
	private LoanService loanService;
	@GetMapping("/loan")
	public String showLoanForm() {
		return "loan-form-page";
	}
	@PostMapping("/loan")
	public ModelAndView receiveLoanFromForm(@ModelAttribute Loan loan) {
		loanService.computeSI(loan);
		return new ModelAndView("loan-output-page", "loan", loan);
	}
}

</code>
Loan.java
<code>
package com.example.model;

public class Loan {
	private double principal;
	private double interestRate;
	private double time;
	private double simpleInterest;
	private double payableAmount;
	public Loan() {
		super();
	}
	public Loan(double principal, double interestRate, double time, double simpleInterest, double payableAmount) {
		super();
		this.principal = principal;
		this.interestRate = interestRate;
		this.time = time;
		this.simpleInterest = simpleInterest;
		this.payableAmount = payableAmount;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getSimpleInterest() {
		return simpleInterest;
	}
	public void setSimpleInterest(double simpleInterest) {
		this.simpleInterest = simpleInterest;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	
}

</code>

Loan Service and its implementation:
<code>
package com.example.service;

import com.example.model.Loan;

public interface LoanService {
	void computeSI(Loan loan);
}



package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.Loan;

@Service
public class LoanServiceIml implements LoanService{
	@Override
	public void computeSI(Loan loan) {
		// TODO Auto-generated method stub
		loan.setSimpleInterest(loan.getPrincipal() * loan.getInterestRate() * loan.getTime()/100);
		loan.setPayableAmount(loan.getPrincipal() + loan.getSimpleInterest());	
	}
}

</code>

loan-form
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/site.css" />-->
<link rel="stylesheet" href="/css/site.css">

</head>
<body>
	<jsp:include page="/header" />
	<section>
		<form  method="POST">
			<div>
				<label>Principal:
					<input type="decimal" name="principal" />
				</label>
			</div>
			<div>
				<label>Rate of Interest:
					<input type="decimal" name="interestRate" />
				</label>
			</div>
			<div>
				<label>time Period:
					<input type="decimal" name="time" />
				</label>
			</div>
			<button>OK</button>
		</form>
		<p>${msg }</p>
	</section>
</body>
</html>

</code>

loan output
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<table>
			<tr><td>Principal: </td><td>${loan.principal }</td></tr>
			<tr><td>Rate of Interest: </td><td>${loan.interestRate }</td></tr>
			<tr><td>Time Period(yrs): </td><td>${loan.time }</td></tr>
			<tr><td>Simple Interest: </td><td>${loan.simpleInterest }</td></tr>
			<tr><td>Payable Amount: </td><td>${loan.payableAmount }</td></tr>
		</table>
	</section>
</body>
</html>
</code>

css constomizations are made.

ModelAtrribute can also be used on a method.
The method can return values and it can be used in various pages of the controller.

Lets use session.
we will use the @Scope("session") 
By default all the beans will have a singleton scope. but to change it, we use the session scope.
FriendsController
<code>
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class FriendsController {
	private List<String> friends;
	@GetMapping("/friends")
	public ModelAndView showFriends() {
		return new ModelAndView("friends-page", "frds", friends);		
	}
	@GetMapping("/addFriend")
	public ModelAndView addFriends(@RequestParam("fnm") String friendName) {
		if(friends == null) {
			friends = new ArrayList<>();
		}
		friends.add(friendName);
		System.out.println(friends);
		return new ModelAndView("friends-page", "frds", friends);
	}
	@GetMapping("/removeFriend")
	public ModelAndView removeFriends(@RequestParam("dnm") String friendName) {
		if(friends!= null) {
			friends.remove(friendName);
		}
		return new ModelAndView("friends-page", "frds", friends);
	}
}

</code>

friends-page.jsp
<code>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<h3>Friends</h3>
		<!-- Used for debuggin
		<p>${frds }</p>
		<p>${not empty frds }</p>
		 -->
		<c:choose>
		    <c:when test="${not empty frds}">
		        <ol>
				<c:forEach items="${frds }" var="f">
					<!-- <li>${f } <a href="removeFriend?fnm=${f }">X</a> </li> -->
					<li>${f } 
					<form action="removeFriend">
						<input type="hidden" name="fnm" value="${f }"/>
						<button>Remove</button>	
					</form> 
					</li>
				</c:forEach>
				</ol>
		    </c:when>
		    <c:otherwise>
		        <p>❌ frds is EMPTY or NULL</p>
		    </c:otherwise>
		</c:choose>
		<form action="addFriend">
			<label>Friend Name</label>
			<input name="fnm" type="text">
			<button>ADD</button>
		</form>
	</section>
</body>
</html>
</code>

Note: The friends will persist even whout using the Scope. We will see the difference when we use multiple browsers to access the webpage and add friends. This will simulate multiple users accessing the webpage.
The c:if did not work properly. so we are using c:choose instead which works.

Spring Form Tags:
-----------------
Spring provides something called spring form tags which helps to create better forms tags to integrate forms simpler.

we can directly send an object of the Model to the from from the controller
the form can capture it using a modelAttribute property

We have special tags availble for us when we use the form taglib.
we can work easily with things like select
these new tags will have a new attribute called path.
we can use path attribute for each input element to directly set the value inside the model class object that we received from the controller.

Item Model:
<code>
package com.example.model;

public class Item {
	private int icode;
	private double price;
	private String name;
	private String category;
	public int getIcode() {
		return icode;
	}
	public void setIcode(int icode) {
		this.icode = icode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}	
}
</code>

Controller:
<code>
package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Item;

@Controller
public class ItemController {
	@GetMapping("/items")
	public ModelAndView showItemForm() {
//		we are sending an empty item to the item form.
//		in the item-form-page we use modelAttribute="item"> to make use of this object 
//		each spring form tag will gave a path attribute which matches with the variables 
//		in the Item class.
		return new ModelAndView("item-form-page", "item", new Item());
	}
	@PostMapping("/items")
	public ModelAndView receiveItemForm(@ModelAttribute Item item) {
		return new ModelAndView("item-output-page", "item",item);
	}
	
//	This will help to send the categories to the item-form-page
//	when ever we use ${categories} in the form, this method is called and the categories are sent.
	@ModelAttribute("categories")
	public List<String> getCategories(){
		return Arrays.asList(new String[] {"Pulses", "Cerals", "Beverages", "Others"});
	}
	
}
</code>

item form-page:
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"/>
	<form:form method="POST" modelAttribute="item">
		<div>
			<form:label path="icode">Item Code</form:label>
			<form:input path="icode" type="number"/>
		</div>
		<div>
			<form:label path="name">Item name</form:label>
			<form:input path="name" type="text"/>
		</div>
		<div>
			<form:label path="price">Item Price</form:label>
			<form:input path="price" type="decimal"/>
		</div>
		<div>
			<form:label path="category">Item Category</form:label>
			<form:select path="category" items="${categories }"/>
		</div>
		<button>OK</button>
	</form:form>
</body>
</html>
</code>

item-output-page
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<table>
			<tr><td>Icode: </td><td>${item.icode }</td></tr>
			<tr><td>name: </td><td>${item.name }</td></tr>
			<tr><td>Price </td><td>${item.price }</td></tr>
			<tr><td>Category: </td><td>${item.category }</td></tr>
		</table>
	</section>
</body>
</html>

</code>


Spring Data:
-----------
Automates Data Repository. 
Standard CRUD Operations are automatically created for us
String Data provides interface CRUDRepository  and implementation JpaRepository for ORM
We can only use the ORM if the database we are using is an RDBMS if its s nosql database ORM is not useful.
If the database we are going to work with is not a RDBMS then we can use some other repositories like Neo4jRepository for cassandra and MongoRepository for mongodb

If we are using spring and not spring boot, we need a lot of configuration.
Spring boot will take care of config automatically. We simply need to specify which sort of driver we will be using.

We need to create entity annotate it , configure all columns.
Then create an interface that extends JpaRepository.
No need to create implementation class.
This will finish the  DAO . we dont need to actually write the CRUD operations.

The JpaRepo contains a lot of method  like 
save
deleteById
findById
findAll
existsById
 
To perform some more operations that are not available in the above methods which are provided above, JpaRepo provide some nomenclature that we can use to use custom quirying,
existBy<FieldName> where propertyName is name of the proprty that we have in the entity.
We put these in our Repository interface. As its an interface, none of these wil have implementations
	for example we can create methods like existsByTitle(String titlle), existByMobile(String mobile)

	
FindBy<FieldName> returns Optional<Entiry> or Entity if exists and null if it doesnt.
	For Example findByTitle(String mobile);, findByMobile(String mobile);

findAlBy<FieldName>  - returns a list of Entity.
	findAllByUnit(String unit);
	findAlBySkill(String skill);
	
in more specific situations we can were the function is not available as mentioned above, we can write our own query by creating a custom function name and apply @Query annotation with the JPQL

@Query("JPQL")
List<Entity> anyfun();
no need for any implementation

we can create these in our repository.

Bean Validation:
----------------
As part of jpa implementations, different vendors have introduced validation annotations which comes from jpa package.
By default the spring data with the spring boot uses hybernate internally for the jpa implementation. so the validation annotations will be implemented by hibernate.

The annotations available are
@NotNull(messge= "The error message here.")
@NotBlank - for strings
@MinLength
@MaxLength
@Min
@Max
@Past - for date
@Future - for date.
@DateTimeFormat(iso=ISO.DATE) - for dates

@Valid- This is applied on the Same object that contains the @Model attribute and We use a new class called as BindingResult which will get the errors.
in the forms, we can use the 
<form:error> tag that maps to the vaidation errors coming from the Controller.

ISO is from import org.springframework.format.annotation.DateTimeFormat.ISO;
and not from the following package.
import javax.print.attribute.standard.MediaSize.ISO;


 and many more annotations are available.
 
 
 	// note: these annotations should be from jakarta.validation.constraints.
	// it should not be from org.antir.

New Project: inventory-management-spring-application
We will log into the mysql databse and drop the items table as there are some inconsistencies in the column names with our old projects.
Add Spring Web, Spring Dev Tools, String Data Jpa, mysql driver
Add jasper and jstl and the spring boot validations to the pom.xml
<code>
		<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
		<!-- JSTL for JSP -->
		<dependency>
		    <groupId>jakarta.servlet.jsp.jstl</groupId>
		    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		    <version>2.0.0</version>
		</dependency>
		<dependency>
		    <groupId>org.glassfish.web</groupId>
		    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
		    <version>2.0.0</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
		<dependency>
		    <groupId>org.apache.tomcat.embed</groupId>
		    <artifactId>tomcat-embed-jasper</artifactId>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
</code>
In the properties folder, we will have properties of server and properties for the database.
<code>
	spring.application.name=inventory-management-spring-application
	server.port=7777
	spring.mvc.view.prefix=/pages/
	spring.mvc.view.suffix=.jsp
	# db config
	spring.datasource.url=jdbc:mysql://localhost:3306/imsDb
	#spring.datasoruce.driver-class-name=com.mysql.jdbc.Driver
	spring.datasoruce.driver-class-name=com.mysql.cj.jdbc.Driver
	spring.datasource.username=root
	spring.datasource.password=ssvnka302#
	spring.jpa.show-sql=true
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

</code>


Then we will create the packages for controller, service, dao, entity, exception, service.

Copy reusable content from the inventory-management-portal application.
All the view, entities, exceptions
The controllers and the DAO needs to be built from scratch

Now we need to set the annotations in the entity classes 
<code>
	package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


@Entity
// note: we will generally have a table already available in the database. 
// we have use the same table name.  
@Table(name="items")
public class Item implements Serializable{

	@Id
	@Column(name="icode")
	@NotNull(message="Item code cannot be omitted")
	// note: the NotNull should be from jakarta.validation.constraints.
	// it should not be from org.antir.
	@Min(value =1, message="Item code cannot be zero or negative")
    private Integer icode;
	
	@Column(name="title")
	@NotBlank(message="title cannot be blank")
	@NotNull(message="title cannot be omitted")
	@Size(min= 5, max=20, message="title should be between 5 and 20 characters in length")
    private String title;
	
	@Column(name="packageDate")
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="Package Date cannot be omitted")
	@PastOrPresent(message="Package date cannot be a future date")
    private LocalDate packageDate;
	
	@Column(name="fragile")
    private Boolean fragile;
	
	@Column(name="unit")
	@NotBlank(message="unit cannot be blank")
	@NotNull(message="unit cannot be omitted")
	@Size(min= 2, max=10, message="unit should be between 2 and 10 characters in length")
    private String unit;
	
	@Column(name="costPrice" )
	@NotNull(message="Cost price cannot be omitted")
	@Min(value =1, message="Cost price cannot be zero or negative")
    private Double costPrice;
	
	@Column(name="sellingPrice")
	@NotNull(message="Selling price cannot be omitted")
	@Min(value =1, message="seling price cannot be zero or negative")
    private Double sellingPrice;

    public Item(Integer icode, String title, LocalDate packageDate, Boolean fragile, String unit, Double costPrice, Double sellingPrice) {
        this.icode = icode;
        this.title = title;
        this.packageDate = packageDate;
        this.fragile = fragile;
        this.unit = unit;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }


    public Item() {
    }

    public Integer getIcode() {
        return icode;
    }

    public void setIcode(Integer icode) {
        this.icode = icode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(LocalDate packageDate) {
        this.packageDate = packageDate;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item{");
        sb.append("icode=").append(icode);
        sb.append(", title=").append(title);
        sb.append(", packageDate=").append(packageDate);
        sb.append(", fragile=").append(fragile);
        sb.append(", unit=").append(unit);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append('}');
        return sb.toString();
    }
}

</code>

now we create ItemRepoitory interface inside the dao package
this interface extends JpaRepository
Note: we need to have extends and not implements as ItemRepoitory is not a concrete class.
The JpaRepository generic that needs two  datatypes. 
The first one is the Entity class for which we are creating the Repository
The second one should be the data type of the primary key in the entity.
we need to also mark this interface with the @Repository annotation
We dont need any extra code but to create very specific or custom queries, we need to write extra abstract methods.
<code>
	package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	// these are queries that we can perform apart from the simple methods that are provided
	Item findByTitle(String title);
	List<Item> findAllByUnit(String unit);
	//This is how we write a custom query
	@Query("SELECT i FROM Item i WHERE i.sellingPrice BETWEEN :lower AND :upper")
	List<Item> findAllInSellingPriceRange(double lower, double upper);
	
}

</code>


When working with the service, we need the same interface as before but we will add some annotations.
So lets get the ItemService interface.

We will remove the validations as the annotations that we have on the entity will take care of the validations.
<code>
	package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.example.exception.ImsException;

public interface  ItemService {
    Item add(Item item) throws ImsException;
    Item save(Item item) throws ImsException;
    boolean deleteItem(Integer icode) throws ImsException;
    Item getItemById(Integer icode) throws ImsException;
    List<Item> getAllItems() throws ImsException;
    public Item searchByTitle(String title) throws ImsException;
    public List<Item> searchByUnit(String unit) throws ImsException ;
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) throws ImsException ;

}

</code>

Now lets build the Service implementation class. 
we need to annotate this class with @Service
We have to autowire the appropriate Repository by creating an instance variable

we then need to implement each method.
These methods get a new annotation called @Transactional which helps to handle transactions.
We need this as we will be performing multiple operations in a method. so having @Transactional will help us maintain ACID rules 

The database operations are handled using the JpaRepository object.

The Jparepository object contains a lot of method like
existsById() - check if a database has the entry in it based on the pk.
save() - persists the provided object into the database.
deleteById() - deletes the entry provided with the pk.
findById(id).orElse(null) - find an item by id. if not exists returns null.

for extra operations, we can use the nomenclature provided by the Jpa repo.
we can also use the @Query for custom query with JPQL.
There are other methods available too but these are the most important ones.
The ItemService interface:
<code>
	package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.example.exception.ImsException;

public interface  ItemService {
    Item add(Item item) throws ImsException;
    Item save(Item item) throws ImsException;
    boolean deleteItem(Integer icode) throws ImsException;
    Item getItemById(Integer icode) throws ImsException;
    List<Item> getAllItems() throws ImsException;
    public Item searchByTitle(String title) throws ImsException;
    public List<Item> searchByUnit(String unit) throws ImsException ;
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) throws ImsException ;

}
	
</code>


The ItemServiceImpl class:

<code>
	package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepo;
	@Override
	@Transactional // to manage transactions, we need to use this.
	public Item add(Item item) throws ImsException {
		if(item != null) {
			if(itemRepo.existsById(item.getIcode())) {
				throw new ImsException("Item code already used!");
			}
			itemRepo.save(item);
			return item;
		}
		return null;
	}
	@Transactional
	@Override
	public Item save(Item item) throws ImsException {
		if(item != null) {
			if(!itemRepo.existsById(item.getIcode())) {
				throw new ImsException("Item code already used!");
			}
			itemRepo.save(item);
			return item;
		}
		return null;
	}
	@Transactional
	@Override
	public boolean deleteItem(Integer icode) throws ImsException {
		// TODO Auto-generated method stub
		if(!itemRepo.existsById(icode)) {
			throw new ImsException("Item code already used!");
		}
		itemRepo.deleteById(icode);
		return true;
	}
	@Transactional
	@Override
	public Item getItemById(Integer icode) throws ImsException {
		return itemRepo.findById(icode).orElse(null);
	}
	@Transactional
	@Override
	public List<Item> getAllItems() throws ImsException {
		return itemRepo.findAll();
	}
	 public Item searchByTitle(String title) throws ImsException{
	     return itemRepo.findByTitle(title);
	 }
    public List<Item> searchByUnit(String unit) throws ImsException {
        return itemRepo.findAllByUnit(unit);
    }
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) throws ImsException {
        return itemRepo.findAllInSellingPriceRange(minPrice, maxPrice);
    }	
}

	
</code>



Its time to build the controllers.
Lets build the DefaultController
<code>
	package com.example.controller;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	@Controller
	public class DefaultController {
		@GetMapping({"", "/", "/home"})
		public String getHome() {
			return "index";
		}
		@RequestMapping("/header")
		public String showHeader(){
			return "header";
		}
	}
</code>


Lets Build a ItemController
The ItemController is using the @ModelAttribute annotation to connect the form to the entity.
@Valid annotation is used to validate the data.
We combine it with the BindingResult
Then we use the <form:error> to display the validation errors.
We use the ModelAndView to return the name of the page along with the data.
<code>
	package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;

import jakarta.validation.Valid;

@Controller
public class itemController {
	
	@Autowired
	private ItemService itemService;
	
	
	@GetMapping("/list")
	public ModelAndView shopItemsList() throws ImsException {
		// we will handle the exceptsions later
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}
	
	@GetMapping("/newItem")
	public ModelAndView getItemForm() {
		
		ModelAndView mv =  new ModelAndView("ItemFormPage", "item", new Item());
		mv.addObject("isNew", true);
		// this makes sure we dont get any error after we click sumbit for the second time 
		return mv;
	}
	
	@PostMapping("/addItem")
	public ModelAndView addNewItem(@ModelAttribute("item") @Valid Item item, BindingResult result) throws ImsException {
//		@Valid  will validate the item and if any errors are there, the errors are stored in result
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("ItemFormPage", "item", item);
			mv.addObject("isNew", true);
			return mv;
		}
		itemService.add(item);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}

	@GetMapping("/editItem")
	public ModelAndView getEditItemForm(@RequestParam("icode") String icode) throws NumberFormatException, ImsException {
		Item item = itemService.getItemById(Integer.parseInt(icode));
		return new ModelAndView("ItemFormPage", "item", item);
	}
	
	@PostMapping("/saveItem")
	public ModelAndView saveItem(@ModelAttribute("item") @Valid Item item, BindingResult result) throws ImsException {
//		@Valid  will validate the item and if any errors are there, the errors are stored in result
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("ItemFormPage", "item", item);
		
			return mv;
		}
		itemService.save(item);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}

	
	@GetMapping("/deleteItem")
	public ModelAndView deleteItem(@RequestParam("icode") int icode) throws  ImsException {
		itemService.deleteItem(icode);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}
	
//	get the options for the select element.
	@ModelAttribute("units")
	public List<String> getUnits(){
		return Arrays.asList(new String[] {"Kg", "Mtr", "Ltr", "Packet","Piece"});
		
	}
	
	 @GetMapping("/search")
	    public ModelAndView showSearchForm() {
	        ModelAndView mv = new ModelAndView("searchFormPage");
	        mv.addObject("searchTypes", Arrays.asList("Title", "Unit", "Price Range"));
	        return mv;
	    }

	    @PostMapping("/searchResults")
	    public ModelAndView searchItems(@RequestParam("searchType") String searchType,
	                                    @RequestParam("searchValue") String searchValue) throws ImsException {
	        
	    	System.out.println("Searching.........");
	    	List<Item> results = null;
	        switch (searchType) {
	            case "Title":
	                results = Arrays.asList(itemService.searchByTitle(searchValue));
	                break;
	            case "Unit":
	                results = itemService.searchByUnit(searchValue);
	                break;
	            case "Price Range":
	                // Assuming input is like "min-max" (e.g., "100-500")
	                String[] range = searchValue.split("-");
	                if (range.length == 2) {
	                    double min = Double.parseDouble(range[0]);
	                    double max = Double.parseDouble(range[1]);
	                    results = itemService.searchByPriceRange(min, max);
	                } else {
	                    results = List.of(); // Empty result if input is invalid
	                }
	                break;
	            default:
	                results = List.of();
	        }
	        return new ModelAndView("itemsListPage", "items", results);
	    }
}
	
</code>


Default Controller:
<code>
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	@GetMapping({"", "/", "/home"})
	public String getHome() {
		return "index";
	}
	@RequestMapping("/header")
	public String showHeader(){
		return "header";
	}

}

</code>

All the  jsp files has to be updated to use the mapping url instead of using the jsp file name as the spring uses the view resolver and the controller simply returns the name and the model for the page.

We also updated the forms to be using the spring form tags 
to do this we need to use the form tag lib with uri and prefix
then we use the spring form tags to work with the forms.

index.jsp
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMS - Home</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
</body>
</html>

</code>

header.jsp
<code>
<header>
	<h1>Inventory Management Portal</h1>
	<hr>
	<nav>
		<a href="/home">Home</a>
		<span>|</span>
		<a href="/list">Items List</a>
		<span>|</span>
		<a href="/newItem">Add New</a>
		<span>|</span>
		<a href="search">Search</a>
	</nav>
</header>
</code>

ItemFormPage.jsp
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<h3>${isNew? "New Item": "EditItem"} </h3>
	<!-- Lets say we type the icode for the first time and the we get an error for one of
	the other properties. When we submit it for the second time we will get an error  if
	we the following commented form. This is because the form changes from addItem to
	 saveItem as the icode already exists. to avoid this, we create a new object in the  
	 in the controller called isNew.
	 -->
	<!--<form:form method="POST" modelAttribute="item" action="${item.icode == null ? '/addItem' : '/saveItem'}"></form:form>-->
	<form:form method="POST" modelAttribute="item" action="${isNew ? '/addItem' : '/saveItem'}">
		<div>
			<form:label path="icode">Item code:</form:label>
			<form:input path="icode" type="number"  readonly="${!isNew}"/>
			<form:errors path="icode" />
		</div>
		<div>
			<form:label path="title">Title:</form:label>
			<form:input path="title" type="text" />
			<form:errors path="title" />
		</div>
		<div>
			<form:label path="unit">Unit:</form:label>
			<form:select path="unit" items= "${units}" />
			<form:errors path="unit" />
		</div>
		<div>
			<form:label path="fragile">Fragile:</form:label>
			<form:checkbox path="fragile" />
			<form:errors path="fragile" />
		</div>
		<div>
			<form:label path="packageDate">Package Date:</form:label>
			<form:input path="packageDate" type="date"/>
			<form:errors path="packageDate" />
		</div>
		<div>
			<form:label path="costPrice">Cost Price:</form:label>
			<form:input path="costPrice" type="number" min="0.01" step="0.01" />
			<form:errors path="costPrice" />
		</div>
		<div>
			<form:label path="sellingPrice">Selling Price:</form:label>
			<form:input path="sellingPrice" type="number" min="0.01" step="0.01"  />
			<form:errors path="sellingPrice" />
		</div>
		<div>
			<button>Save</button>
		</div>
	</form:form>
</body>
</html>
</code>

ItemListPage.jsp
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/header"></jsp:include>

<c:choose>
	<c:when test="${items == null} ">
		<p>No Items found</p>
	</c:when>
	<c:when test="${items.isEmpty()} ">
		<p>All items deleted</p>
	</c:when>
	<c:otherwise>
		<h3>Items</h3>
		<table border="1" cellspacing="5px" cellpadding="5px">
			<tr>
				<th>Icode</th>
				<th>Title</th>
				<th>Is Fragile</th>
				<th>Package Date</th>
				<th>Unit</th>
				<th>Cost Price</th>
				<th>Selling Price</th>
				<th> </th>
				<th> </th>
			</tr>
		<c:forEach var="item" items="${ items }">
			<tr>
				<td>${item.getIcode() }</td>
				<td>${item.getTitle() }</td>
				<td>${item.getFragile() }</td>
				<td>${item.getPackageDate() }</td>
				<td>${item.getUnit()} </td>
				<td>${item.getCostPrice() }</td>
				<td>${item.getSellingPrice() }</td>
				<td>
					<form action="/editItem">
						<input type="hidden" name="icode" value= "${item.getIcode() }" }>
						<button>Edit</button>
					</form>
				</td>
				<td>
					<form action="/deleteItem">
						<input type="hidden" name="icode" value= "${item.getIcode() }">
						<button>Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

</body>
</html>
</code>

The SearchFormPage.jsp
<code>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<h2>Search Items</h2>
	<form action="/searchResults" method="post">
		<label for="searchType">Search By:</label>
		<select name="searchType" value="Title">
	     <c:forEach var="type" items="${searchTypes}">
	     	<option name="${type}" value="${type }" >${type }</option>
	     </c:forEach>
	    </select>
	    <p>${searchType }</p>
	    <label for="searchValue">Search Value:</label>
	    <input type="text" name="searchValue" />
	    <input type="submit" value="Submit" />
	</form>
</body>
</html>
</code>

Now that we have everything working, lets add a item that has the same icode as an existing icode. Then we get and exception.
The @Valid will only handles validations. but any other sql errors or other kinds of errors needs to be handled by us.

Exception Handling:
-------------------
@ExceptionHandler - we generally create this annotation in the Controller where the exception generates. but it will only work in that controller.
If we need the same kind of exception handler, then we use another class with annotation
@ControllerAdvice - This will be handle exceptions globally.

Creating a GlobalExceptionController
<code>
package com.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.exception.ImsException;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(ImsException.class)
	public ModelAndView handleImsException(ImsException exception) {
		return new ModelAndView("errPage", "errMsg", exception.getMessage());
	}

}
</code>


using bootstrap:
----------------
Lets create a copy of the inventory-management spring application and apply bootstrap.
Project name: bootstrap-inventory-managemen-spring-application

Lets get the bootstrap cdn links and add it to the jsp pages.
we added a lot of bootstrap including nav bar, button and table, form styles styles.

Restful web services With Spring Rest:
-------------------------------------
It is a sub module of spring web.
Used to develop resful web services.
Web service: A software design that creates an interoperable(works with different languages for the client) interaction between a stand alone UI and server.

In the old days websites were enough for companies. But now, we need web applications, mobile application, an application on android auto and so on.
So we cannot integrate the UI directly into the server application like what we did till now, Our server will provide data to which ever client app requests it. 

This is where we will build the web services come into the picture,
SOAP Web Services (Simple Object Access Protocol)
	- uses xml for communication.
	- Had problems like xml validations needed complex ddts
	- Converting object to xml and xml to object
	- cannot use xml to transmit images, binary files like pdf, and so on
	

REST (Representational State Transfer).
	Uses HTTP Protocol-  Supports different file types
	Can use JSON, XML, Binary Resposes.
	
The Spring Rest provides with annotations that we can make use of create the application,

@RestController - Its a combination of @Controller and @ResposeBody.
@ResponseBody is used to represent that this controller does not return any view names. It simply returns data.

@RestControllerAdvice - handles exceptions and returns data to the client.

The return type of the methods will be class called ResponseEntity<> 
It contains 2 parameters that we need to pass.
1. payload/body - the data that we want to respond with.
2. status - the http status codes. For this we have a class called HttpStatus which has contans like BAD_REQUEST, INTERNAL_SERVER_ERROR , OK, ....

It is a generic class so it will hold the datatype depending on what we want to return.
for example if we are returning an Item object, we use ResposeEntity<Item>

http2 codes
1xx - request is received and under process.
2xx - request was procesed successfully
3xx - redirectd
4xx - client side error
5xx - server side error.

Until now we create different links for different kinds of requests and they did not follow any particular structure. This is because we developed the UI aswell.

This will now work from now. as UI will be developed by a differnt team and there has to be a standardized way to create urls.
This is part of Restfulness aswel and we have to follow this structure.

So we will have a following structure
			/item
Retrieve	GET
Insert		POST
Update		PUT
Delete		DELETE

so each type of a request will be mapped to a specific request method.
The url pattern will also change if we are working with individual components
for example  GET /item/2 will be used to retrieve the item with id 2.
query parameter is not recommended as we have to communicate which query parameter exists to the ui developers.
Instead we use the url parameter which is the 2 in the url
The url mapping in the code will be written like @GetMapping("/{icode}")
and we use the @PathVariabe on the method's parameter.

If we are going to additional body for the method, such as for the newItem. then we have to use the @RequestBody Attributes which maps to an Entity or a model class.


So each controller will be mapping to one Entity. which helps to maintain the code aswell
So we will directly write the @RequestMappring on the Controller class and not on the internal method. if there is a sub path available, then we can use the extra mapping on the methods.

Project Name: inventory-management-spring-application-rest
Lets clone the bootstrap project 
We will not need the UI stuff. so lets remove the UI related rependencies from the pom.xml
We will remove, jstl, jasper. 

lets also remove prefix and suffix properties

We will also remove any jsp pages.

We need to re write our controllers as the old controller are reurning the page names.
So lets delete the DefaultController and the ItemController.
Lets update the Annotation of the GlobalExceptionController to use @RestControllerAdvice
lets also add another method that handles exceptions that are not related to client side errors.
<code>
	package com.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.exception.ImsException;

@RestControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(ImsException.class)
	public ResponseEntity<String> handleImsException(ImsException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(ImsException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
</code>


Lets now Create a ItemRestController class.


Spring test:
---------------
We will test the dao, service.

h2 is an in memory database that we will use so that we cannot accidentally modify the main database.

we will include the dependency of h2 in our project and create a new properties file in the test folder.


Project Setup:
Copying inventory management spring application.

dependency
<code>
		<!-- h2 database dependency-->
		<dependency>
		    <groupId>com.h2database</groupId>
		    <artifactId>h2</artifactId>
		    <scope>runtime</scope>
		</dependency>
</code>

Let create a new properties in the resources source folder of the test folder.

application-test.properties
<code>
server.port=8887

# DB Config
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=
spring.datasource.password=

# Hibernate Config
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
	
</code>


Repository Test:
@DataJpaTest
used for setting hibernate , spring data and DataSorce , performing @EntityScan, turn on sql logging.
We also want to set which test profile we need to make use of. This can be done by using @ActiveProfiles on the test class.

We will set this on the Test class.
we create a new package called com.example.test in the src/test/java

Now we create the ItemRepoTest which will get the @DataJpaTest.

Normally to test, we perform an operation, then go to the database and check if that is what we have in the database.
But for automated testing, we need to have a trusted channel and an expected channel. We then compare them.

To do this we will have to work with TestEntityManager.

The TestEntityManager has direct access to our database and it has methods like
persist
clear
flush

The test class
<code>
package com.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.dao.ItemRepository;
import com.example.entity.Item;

@ActiveProfiles("test")
@DataJpaTest
public class ItemRepoTest {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private TestEntityManager entityManager;
	private List<Item> testData;
	@BeforeEach
	public void setUp() {
		testData = Arrays.asList(
				new Item[] {
						new Item(101, "Rice Bag", LocalDate.now(), true, "BAG", 1024.0, 2024.0),
						new Item(102, "Sugar Bag", LocalDate.now(), true, "BAG", 3024.0, 4024.0),
						new Item(103, "Channa Bag", LocalDate.now(), true, "PACKET", 1024.0, 2024.0),
						new Item(104, "Wheat Bag", LocalDate.now(), true, "BAG", 5024.0, 7024.0),
						new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0)
				}
		);
		for(Item item: testData) {
			entityManager.persist(item);
		}
	}
	@AfterEach
	public void tearDown() {
		entityManager.clear();
		entityManager.flush();
		testData = null;
	}
	@Test
	public void whenFoundByTitle_GivenExistingTitle_test() {
		Item expected = testData.get(0);
		Item actual = itemRepository.findByTitle(expected.getTitle());
		assertEquals(expected, actual);
	}
	@Test
	public void whenFoundByTitle_GivenNonExistingTitle_test() {
		Item actual = itemRepository.findByTitle("asdjfkjsnfksjn");
		assertNull( actual);
	}
}

</code>


We will create .data file that will contain some data which will be inserted into the database so that we can perform queries and tests on this data.

ServiceUnitTest:
Now lets create a unit test for the Service layer.
The Service layer is dependent on the Repo. If the repo fails, then the test will also fail.
This is where we use Mocking.
We will mock the dao and only test the service. This allows us to assume everything is working in the DAO Layer and we will be only concerned about the Service layer.


1. We will create a ItemServiceUnitTest.java
As we are not testing the Repo here, we will use the following annotation
@SpringJUnitConfig - Acts as a bridge between spring and  junit5.
@TestConfiguration
@MockBean - We use this on the repo object making them mock 

2. In the class we create an object for the Repo and Autowire the service we want to test. 
But the problem here is as the @Autowired will use the original repo and not the mock repo that we created in the test class. to fix this, we use the @TestConfiguation 
This should be a static inner class . The class will contain a Bean configuration method that returns the service implementation object.

Now our class is ready for the testing.
Note: we dont use  the entity manager here.

3. in each test, we need to identify what repo methods the service is using to do the operation and those methods need to be mocked.
To do this we use the mockito.
Example code:
Mockito.when(itemRepo.existsById(106)).thenReturn(false);
Mockito.when(itemRepo.save(item)).thenReturn(item);

we are returning false for existsById as it has a boolean return type and 106 id does not exist yet
we are returning item for save as the save returning the persisted item.

By using the mocking we are only testing service and assuming that repo is not being tested. if we dont do this, it is not unit testing. it will be integrated testing.

<code>
package com.example.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;
import com.example.service.ItemServiceImpl;


@SpringJUnitConfig
public class ItemServiceUnitTest {
	@TestConfiguration
	static class TestComfigurationClass{
		@Bean
		public ItemService itemService() {
			return new ItemServiceImpl();
		}
	}
	@MockBean
	private ItemRepository itemRepo;
	@Autowired
	ItemService itemService;
	private List<Item> testData;
	@BeforeEach
	public void setUp() {
		testData = Arrays.asList(
				new Item[] {
						new Item(101, "Rice Bag", LocalDate.now(), true, "BAG", 1024.0, 2024.0),
						new Item(102, "Sugar Bag", LocalDate.now(), true, "BAG", 3024.0, 4024.0),
						new Item(103, "Channa Bag", LocalDate.now(), true, "PACKET", 1024.0, 2024.0),
						new Item(104, "Wheat Bag", LocalDate.now(), true, "BAG", 5024.0, 7024.0),
						new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0)
				}
		);
	}
	@AfterEach
	public void tearDown() {
		testData = null;
	}
	@Test
	public void whenAdd_givenNonExistingRecord_test() {
		Item item = new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0);
		Mockito.when(itemRepo.existsById(106)).thenReturn(false);
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		try {
			assertEquals(itemService.add(item), item);
		} catch (ImsException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	@Test
	public void whenAdd_givenExistingRecord_test() {
		Item item = testData.get(0);
		Mockito.when(itemRepo.existsById(item.getIcode())).thenReturn(true);
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		assertThrows(ImsException.class, () -> {itemService.add(item);});
	}
	@Test
	public void whenGetItemById_givenExistingIcode_test() throws ImsException {
		Item item = testData.get(0);
		Mockito.when(itemRepo.findById(item.getIcode())).thenReturn(Optional.ofNullable(item));
		assertEquals(itemService.getItemById(item.getIcode()), item);
	}
	
}


</code>





Integration test:
-----------------
To perform Integration test we will not use the mock database but instead, we will use the original database itself.

For integration test, we use the @SpringBootTest Annotation
We will also remove the Mockito Stuff, TestConfiguration class and perform the tests.

<code>
package com.example.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;


@ActiveProfiles("test")
@SpringBootTest
public class ItemServiceIntegrationTest {
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	ItemService itemService;
	private List<Item> testData;
	@BeforeEach
	public void setUp() {
		testData = Arrays.asList(
				new Item[] {
						new Item(101, "Rice Bag", LocalDate.now(), true, "BAG", 1024.0, 2024.0),
						new Item(102, "Sugar Bag", LocalDate.now(), true, "BAG", 3024.0, 4024.0),
						new Item(103, "Channa Bag", LocalDate.now(), true, "PACKET", 1024.0, 2024.0),
						new Item(104, "Wheat Bag", LocalDate.now(), true, "BAG", 5024.0, 7024.0),
						new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0)
				}
		);
		for(Item item: testData) {
			itemRepo.saveAndFlush(item);	
		}
	}
	@AfterEach
	public void tearDown() {
		itemRepo.deleteAll();
		testData = null;
	}
	@Test
	public void whenAdd_givenNonExistingRecord_test() {
		Item item = new Item(106, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0);
		try {
			assertEquals(itemService.add(item), item);
		} catch (ImsException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	@Test
	public void whenAdd_givenExistingRecord_test() {
		Item item = testData.get(0);
		assertThrows(ImsException.class, () -> {itemService.add(item);});
	}
	@Test
	public void whenGetItemById_givenExistingIcode_test() throws ImsException {
		Item expectedItem = testData.get(0);
		Item actualItem = itemService.getItemById(expectedItem.getIcode());
		System.out.println("Actual Item: "+actualItem);
		assertEquals(actualItem.getIcode(), expectedItem.getIcode());
	}
}


</code>


Spring Security:
----------------
Spring allows us to restrict access to different api end points depending on the granted Authorities.
For setting this up, we will have roles table with many to many relationship with the users tables.
The we need too classes that implements from UserDetails and UserDetailsService interfaces that are provided by the spring security.
These two classes along with the user role helps to setup access to each end point. To use pring security, at the project start, we can simply add its dependencies.

JWT
To Setup Spring Security with jwt we need setup the dependencies for them in the pom file.
<code>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>${jjwt-api.version}</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>${jjwt-impl.version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>${jjwt-jackson.version}</version>
			<scope>runtime</scope>
		</dependency>
</code>

Then we need to configure the application to use the jwt 

After this we create a Util class that has methods to generate jwt tokens, validate the tokens, get username from the token and so on,


Thime Leaf with bootstrap:
--------------------





Maven In depth:
---------------
Project Management Tool

Manages dependencies, plugins
Build projects
Structure your project.
Documentation - Easy way to create documentations in our project
Create Reports 
Maintains Release cycle
Distrubutions - creating single point access to multiple projects

Support for maven
1. Maven plugin in ide
2. Spring Initializr
3. Stand alone cli install.

Stand alone setup:
download maven from the websites and then setup the environment system path variable for it.
M2_HOME - the folder where maven is downloaded and extracted. (parent folder for the bin)
M2 - The bin folder.
Then use it in the Path variable.

We need this as we will use it with jenkins later.

Maven Archtypes: predefined template to create a poject.

GAV Coordinates (groupid, Atrtifactid, version): Helps to uniquely identify our project 

Maven Project : we can do this using the ide or by using a command aswell
maven-demo-project

command syntax to create a maven project:
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=com.example -DartifactId=maven-cmd-project

This says use the archetype plugin to generate a project. and then we provide which archetype we need to make use of , the arifact id of the current project and the groupid of the project.


pom.xml
project object model.
contains details of the project, gav co-ordinates , dependencies and plugins, project profile management and so on.

There are 3 kinds of pom files.
1. parent pom (maven inbuilt pom.xml default config)
2. local pom: customizable pom
3. effective pom : parent + local pom runtime pom



Maven update: Helps to download the dependencies and plugins.
To download the dependencies it first check the .m2 folder on your computer. If it is not available, then gets it from the remote central repository and stores it in the local repo.


Scope:
Specifies when a dependency needs to be available
compiler - dependency is required at build, test and runtime. (default)
test - dependency is required at the test.
runtime- only at runtime.
provided - build, test, runtime (will not be packaged with the project). It will be available in the java runtime environment itself in the webserver
system - same as provided. build, test, runtime (will not be packaged with the project). Also will not be provided by the java runtime environment.
we specify a url so that it will be downloaded as per the demand at runtime.


Plugins:
We get plugins for different Maven activity or tasks(caled GOALS).
For examle mavin-war plugin in maven web project.

For example:
in mvn archetype:generate, generate is the goal.

for each goal, we need a maven plugin.

when we do mvn <goal>, maven searches in the effective pom.
if its available,  then it uses the plugin to perform the task
of it is not , then we wil get an error.

we can also do it like
mvn <plugin>:<goal>
This will tell maven to use a specific plugin to do a task.
if there is already a plugin available, then this command will override it.

some example commands:
mvn clean
mvn package

snapshot version:
Always gets the dependency directly from the central repository as the dependency is under development instead of searching the local.
Avoid using snapshot version.

Maven Multi-Module Project:
Can have a parent project controlling multiple child projects
Based on pom.xml

The parent project will have its own pom.xml
Then we can create multiple independent projects that acts like the child.
The child project pom can have inheritance or aggregation relation with the parent pom.xml.

In inheritance, all common dependencies can be stored in parent. 
Note: The parent pom is only a reference and it will not be running artifact.

In aggregation, activities can be intiated in the parent project. We dont need to go to the child project and initiate it.

If we have 20 projects , we dont need to perform build operation for each project. we can aggregate these projects into a single parent project and we can initiate a build on the parent.

This reduces duplication.
Can help run single command to perform multiple operations.


let create a maven project.
<code>
mvn archetype:generate -DgroupId=com.example -DartifactId=maven-parent-project
</code>
This will create a project and a pom.xml file with effective pom.xml.

Now we need to make the parent project not runnable.
we have the packaging tag with pom inside it.

<code>
<packaging>pom</packaging>
</code>

Now lets create the child projects. These are going to be inside the parent project's folder.
<code>
cd maven-parent-project
mvn archetype:generate -DgroupId=com.example -DartifactId=maven-sub-module1
mvn archetype:generate -DgroupId=com.example -DartifactId=maven-sub-module2
mvn archetype:generate -DgroupId=com.example -DartifactId=maven-sub-module3
</code>
now in the parent's pom.xml we will see a new tag called modules
<code>
  <modules>
    <module>maven-sub-module1</module>
    <module>maven-sub-module2</module>
    <module>maven-sub-module3</module>
  </modules>
  
</code>

This is basically aggregation and generated automatically in the parent.
The parent project is registering all the sub modules automatically.

Now we can perform the build on the parent, it will build the chold projects aswell.

<code>
	mvn clean package
</code>
we will see that it keeps track of all the sub modules aswell and perform the build.
we will see target folder in each project.
we will also see a tag in each child that tell what is the parent project
<code>
  <parent>
    <artifactId>maven-parent-project</artifactId>
    <groupId>com.example</groupId>
    <version>1.0-SNAPSHOT</version>
  </parent>

</code>

This is inheritance.
All plugins from parent project willl be inherited by the child project.
By default both the aggregation and inheritance is implemented in the projects.
if we want only one relationship, we can remove the respective tag .
This is basically how our spring-boot project works as it has parent tag specified.

Maven vs Gradle:
Maven:
	Maven is not the only project management tool we have. we also have gradle
	Maven is more generalized . less customizations
	Uses xml for the config
	build phases are linear. first clean the project, then test, then package it. it cannot be reordered.
	maven does not help you take full advantage of the ide that you are using. This is one of the reasons why we use eclipse for the whole time.
	maven creates a lot of conflicts while using dependencies. some times we might need to go to the .m2 folder and perform some deletions when the dependency is not properly downloaded. This makes the dependency corrupt and it will not be auto downloaded to fix the issue. This has to be done manually.

Gradle:
	Gradle inspired from Maven.But has more customizations. For example, gradle can be specifically customised for web, mobile and so on. It is built to overcome the drawbacks of maven.
	Uses domain specific language for config. It is based on Groovy.
	Lot of config code is reduced.
	The phases can be done in any order. Graph based.
	Gradle on the other hand provides a lot more customization so that it can take full advantage of the ide that you want to make use of.
	for example, if we want to create the jsp files in specific locations of our project, then we can customize it.
	gradle manages the dependencies efficiently.
	
	
	
	
CI/CD:
------
Continuous Integration and Continuous Deployment
The main goal of this is to achieve agile process. It is an iterative incremental model.
With each interation, we get an incremental artifact.
There are 2 aspects in this.
1. Develomental team: Provide continuous incremental artifact/ updates in the project
2. Now immediately we need to use this on multiple platforms like staging, testing, production so on.

Traditionally this was done manually.
Challenges when doing this manually:
1. reconfigure the host machine. Manually update the computer.
2. deploy , build, test, generate reports, analyse,....
This slows down the project, creates conflicts and makes it not agile.

CI/CD provide End to End Automation for this post development process and make it smooth and seamless.
We will learn about two tools
1. Docker
2. Jenkins

Jenkins:
--------
A tool that create a CI server for schedule and execution of jobs.
Jobs could be any task that we want on the project as soon as it is commit to github or any other source control tool like build it, test the project, create a package, convert to a container, deploy it to a cloud provider.

So jenkins a tool that can schedule these jobs on the project.
So we specify when each of the job need to be performed and in which order,
We can also configure it to automatically trigger a build, test and deploy it on the server.
All this happen as soon as the commit is made.
So the developers dont need to wait for test team to manually test the project and generate reports.s

How jenkins works:
Similar to maven, jenkins heavyly relies on plugins.
1. We need to configure the plugins as per the requirement. 
for example if we need the jenkins to create a docker container as soon as commit is made, then we need to configure accordingly. if we need testing the docker, then we configure differently. 
2. Jenkins uses multiple Agents to perform a particular job. Once the agents are in place (plugins)
3. Then we can schedule our jobs.


Jenkins provides a browser based enviroment at port 8080.
then it uses credentials.


Jenkins installation:
---------------------
Download jenkins.
During installation, select "Run service as LocalSystem"
In the next step, check if port 8080 is available. If it is not available, setup a different port number or try to free port 8080
Once the installation is done, open localhost:8080 in the browser
Jenkins will open as a website.
provide the admin password. The admin password will be available at the provided location.
Provide the username and password for new admin user. you will need this to login to jenkins from next time.
then setup the plug-in that you want. You can start with default plugins and download the ones you want later.

Using Jenkins Dashboard:
------------------------
Once we are at the login screen we can see the logo of the jenkins which is a butler.
Butler will do the jobs for us.
In the dashboard we will mainly work with manage jenkins tab. Once we go there we can see there are so many options that we can make use of here.
We have  plugins option where we can add, remove or update plugins or even upload a custom plugin if its not avaiable.
We have 2 other options that we will mainly use. 
System - helps to configure tools, their locations and automatic installers.

Plugins necessary for maven based Spring projects
java
git
maven integration

if these are not available, you need to install them
We need to then go to the  Tools configuration section to configure the tools	
Here we wil find the setting to configure maven, jdk, git and other plugins.

We need to configure these. so lets go to Tools configuration section.
Then lets setup jdk.
we need to provide jdk name and home directory location. we can also install it directly if jdk is not part of our computer.
we will use other plugins like nodejs, docker, ant and many more depending on the project. We also have plugins for creating performance report and so on. you can explore the available plugins and see what you can make use of.
Search google for popular jenkins plugins.

Creating A job:
---------------
In the dashboard, we will see new item button that we can make use of. 

provide a name
Then choose one of the options shown.
Lets select free style project.
Now we can configure the job.
Selecting the github project, it will show the repo url .
You can explore all other options available.
Some of the important things that we will work with are the environment, build steps, post build actions.

lets select invoke top-level-maven targets
we will select Local Maven for maven version.
Then in the goals we provide clean test package
jenkins will automatically call these commands one after the other.
Under  Post-build Actions we have a lot of different actions that we can perform.
We can extends the goals tasks and actions when we install more plugins.
for example, we can install plugins to publish javadocs. publish repost, enable email notification, and so on.
we will checkout the plugins later.

Alternatively, we can create a jenkins scipt file that contains all the config that we might need to create the jenkins job.
the jenkins scripts can be created without any extensions.
It can be part of the project that we are working with or it can be outside but it has to be on a git repository.
it should be named jenkinsfile.

In this file, we will use the groovy based language to specify a pipeline which is a the flow of the activities that we want to perform.
we define the things in a composition called node.
we will have different functions called stages 
Each stage can have one or more activities that we want to perform.
The stages will execute in the order that they are provided.

Each stage should have a name

The first activity that we want to do is to read all the code from the git repository. we need to provide the git url and the branch properties 

Then in the second stage we want to build the project.

<code>
node{
	def mvnHome
	def PROJECT_DIR
	stage('Checkout'){
		git url: 'https://github.com/vatsan1993/java-full-stack-learnings.git', branch: 'main'
		mvnHome = tool 'Local Maven'
		PROJECT_DIR = 'java projects/spring-web-mvc-demo'
	}
	stage("Build"){
		dir("${PROJECT_DIR}") {
			if(isUnix()){
				sh "'${mvnHome}'  clean package"
			}else{
				bat(/"${mvnHome}\bin\mvn" clean pacakge/)
			}
		}
	}
}

</code>


we can then push the code to git.
Then we can go to jenkins and configure the item pipeline.
We have to change the scm to git.
Then we provide the repository url and the script path.

Note: before we build the broject in jenkins, we need to verify if it is building locally.
lets use mvn clean in the project folder. if there are aby build errors, we need to fix them and then push it to git before we build the project on the jenkins

Then we build the project.

we can also generate these code for the stages and steps that we need.
When we select the pipeline project, we will see there is a pipeline syntax tab on the left. we can use that and generate the skeleton code.
we also have documentation that we can read and understand.


if the build fails, we can see that in the console output or in pipeline steps.

Build Trigger:
--------------
Although we used the jenkins file, the build process is still not automated.
Lets automate the process.
Lets go to the configure section . In build triggers let select build periodically.
Now click on the question marks beside the schedule and documentation popsup.
we have examples in there and we can use one of the exapmles.
I am choosing 15 mins.
As soon as we provide this we will see a text like "Would last have run at Tuesday, 15 April, 2025, 8:38:36 pm India Standard Time; would next run at Tuesday, 15 April, 2025, 8:53:36 pm India Standard Time.",
Save the config.
from now the build happens in every 15 mins
Lets say we made changes in the code and commited the changed within that 15 mins. then what happens?
It will take only commited in the last commit not the new ones. the new ones will be taken only in the next build.

There is another trigger model called github hook.
This is trigger a build when a commit is made.




	















