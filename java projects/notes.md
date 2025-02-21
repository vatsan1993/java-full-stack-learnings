# sql

table names are plural

# maven
Create Maven project

Maven Dependencies




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
6. Create a class for jdbc implementation for the method we specified in the interface . If hybernate is used there will be another class which will have the implementation using hybernate.
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
-----------------
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
	
	Abstact Class
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
@Column -- field level annotation. Helps to control the name of the column. If it is nullable or unique and other constrains.
if the Column annotation is not applied, then the field name becomes the column name.
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



