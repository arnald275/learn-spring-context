# Spring ApplicationContext
![alt text](https://spring.io/img/spring-2.svg)

 
### Understanding the Role of ApplicationContext and Bean

We've reviewed some code and instructions, and now it's time to dive deeper into what's really going on.

### What's the Container?

The main idea behind the container and Inversion of Control (IoC) is to promote a separation of concerns in software development by decoupling dependencies between components.
The container acts as a centralized hub, responsible for creating and managing the lifecycle of objects, while IoC ensures that the flow of control is inverted, allowing for components to rely on abstractions rather than concrete implementations.
This promotes modularization, maintainability, and scalability, as it allows components to be developed independently and easily swapped out without affecting the rest of the system. Additionally, it enables developers to write more flexible and loosely coupled code, leading to easier testing, debugging, and overall code quality.

### How does the Spring use this concept?

In the Spring framework, the container responsible for managing objects and their dependencies is called the _ApplicationContext_.
It creates and manages instances of beans and provides them with their required dependencies based on the configuration provided in the application context.
The _ApplicationContext_ is built on the principles of Inversion of Control (IoC) and Dependency Injection (DI).

### What is a Bean in Spring?

In the Spring, a bean is a class that is instantiated, assembled, and managed by the Spring IoC container.
The IoC container takes care of the creation, initialization, and wiring of the beans. The beans can be configured using various techniques, such as annotations, XML, or Java configuration classes.
A bean can have dependencies on other beans, and these dependencies are resolved by the IoC container at runtime.

In other words, instead of the application controlling the creation and management of its objects, the _ApplicationContext_ controls it.
The application simply declares its dependencies, and the _ApplicationContext_ provides them.
To configure the _ApplicationContext_, we can use an XML file called _applicationContext.xml_, which defines the beans and their dependencies.
This file includes information about the beans such as their class, properties, and dependencies.

### ApplicationContext as an Implementation of AbstractFactory Pattern

Spring's _ApplicationContext_ can be seen as an implementation of the Abstract Factory design pattern.
The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes. Similarly, Spring's _ApplicationContext_ provides a way to create and manage related objects that are defined in a configuration file, without requiring the code to know the concrete classes of those objects.
In the case of Spring, the _ApplicationContext_ interface defines the contract for creating and managing beans, which are the objects that form the backbone of a Spring application. Each implementation of the _ApplicationContext_ interface is responsible for creating and managing beans based on a particular configuration format or source.
For example, the _ClassPathXmlApplicationContext_ implementation creates and manages beans based on XML configuration files located on the classpath.

When a Spring application starts up, the _ApplicationContext_ implementation reads the configuration file(s) and creates the corresponding beans, which can be retrieved by name or type.
The _ApplicationContext_ acts as an Abstract Factory in this process, providing a common interface for creating and managing objects, while allowing the concrete implementations to vary based on the specific configuration used by the application.

Spring's ApplicationContext is an implementation of the Abstract Factory pattern, providing a flexible way to create and manage related objects based on configuration files, while decoupling the code from the concrete classes of those objects.

### Implementations of the ApplicationContext

The _ApplicationContext_ interface has several implementations, each suited for a different use case. Here are some of the most commonly used implementations:

1. **ClassPathXmlApplicationContext**: This implementation loads the container configuration from one or more XML files on the classpath. It is suitable for most standalone applications and is the most commonly used ApplicationContext implementation.

2. **FileSystemXmlApplicationContext**: This implementation loads the container configuration from one or more XML files in the filesystem. It is useful for applications that require more control over the location of configuration files.

3. **AnnotationConfigApplicationContext**: This implementation loads the container configuration from one or more Java classes annotated with @Configuration. It is useful for applications that prefer to configure the container using Java code instead of XML.

4. **XmlWebApplicationContext**: This implementation loads the container configuration from one or more XML files in a web application. It is suitable for web applications that use Spring's web framework.

5. **ServletContextAwareWebApplicationContext**: This implementation extends XmlWebApplicationContext and adds support for accessing the ServletContext of a web application. It is suitable for web applications that require access to the ServletContext.

6. **RemoteApplicationContext**: This implementation allows an application to access a remote ApplicationContext over a network using Java RMI (Remote Method Invocation). It is useful for distributed applications that require access to a central configuration server.

Let's review each one in detail.

### ClassPathXmlApplicationContext

_ClassPathXmlApplicationContext_ is an implementation of the _ApplicationContext_ interface in Spring that is used to create a container from one or more XML configuration files located in the classpath.
It is a convenient way to configure and bootstrap a Spring application, as it allows developers to define beans and their dependencies in an easy-to-read XML format.

Use cases:

- _ClassPathXmlApplicationContext_ is commonly used in small to medium-sized Spring applications where the entire application can be configured using a single XML configuration file.

- It is also useful when developing unit tests for Spring applications, as it allows developers to quickly create a container and inject test dependencies.

Suppose we have a simple Spring application that contains a _Person_ class and a _GreetingService_ interface with two implementations: _EnglishGreetingService_ and _FrenchGreetingService_.
We can use _ClassPathXmlApplicationContext_ to create a container that manages the dependencies between these classes.

1. First, we create an XML configuration file called _applicationContext.xml_ that defines the beans for our application:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Define the Person bean -->
    <bean id="person" class="com.example.Person">
        <property name="name" value="John"/>
        <property name="age" value="30"/>
    </bean>

    <!-- Define the EnglishGreetingService bean -->
    <bean id="englishGreetingService" class="com.example.EnglishGreetingService">
        <property name="message" value="Hello"/>
    </bean>

    <!-- Define the FrenchGreetingService bean -->
    <bean id="frenchGreetingService" class="com.example.FrenchGreetingService">
        <property name="message" value="Bonjour"/>
    </bean>
    
</beans>
```
2. Next, we create a Java class called Main that uses _ClassPathXmlApplicationContext_ to create the container and retrieve the beans:
```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the Person bean
        Person person = context.getBean("person", Person.class);
        System.out.println(person);

        // Retrieve the EnglishGreetingService bean and use it to greet the person
        GreetingService englishGreetingService = context.getBean("englishGreetingService", GreetingService.class);
        String englishGreeting = englishGreetingService.greet(person);
        System.out.println(englishGreeting);

        // Retrieve the FrenchGreetingService bean and use it to greet the person
        GreetingService frenchGreetingService = context.getBean("frenchGreetingService", GreetingService.class);
        String frenchGreeting = frenchGreetingService.greet(person);
        System.out.println(frenchGreeting);
    }
}
```
3. Finally, we run the Main class, which uses the container to create and manage the beans:
```text
John (age 30)
Hello, John!
Bonjour, John!
```
In this example, _ClassPathXmlApplicationContext_ is used to create a container from the _applicationContext.xml_ file, which defines three beans: _Person_, _EnglishGreetingService_, and _FrenchGreetingService_.
The Main class retrieves the beans from the container and uses them to greet the Person bean.

### FileSystemXmlApplicationContext

_FileSystemXmlApplicationContext_ creates an application context from an XML file in the file system. It is used to configure and bootstrap Spring applications using XML configuration files.

Use-cases:

1. Simplifying configuration: _FileSystemXmlApplicationContext_ can be used to simplify the configuration of Spring applications by using XML files to define beans, their properties and dependencies.

2. Modularity: It can be used to create modular applications where each module can have its own XML configuration file. This allows the modules to be developed and tested independently.

3. External configuration: By using _FileSystemXmlApplicationContext_, you can externalize the configuration of your Spring application into an XML file, which can be easily modified by system administrators without modifying the application code.

Real Example:
Suppose we have a Java application that needs to access a database to perform some operations. We want to use Spring to manage the database connection and the transactions.

Here's an example XML file (_applicationContext.xml_) that defines a DataSource bean using the BasicDataSource class from the Apache Commons DBCP library:
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mydatabase"/>
        <property name="username" value="myuser"/>
        <property name="password" value="mypassword"/>
    </bean>

</beans>
```
In our Java code, we can create a _FileSystemXmlApplicationContext_ object to load the _applicationContext.xml_ file:

```java
ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
```

This will create an application context that contains the _DataSource_ bean defined in the XML file. You can then use the _getBean()_ method to retrieve the _DataSource_ bean and use it to interact with the database.

```java
DataSource dataSource = (DataSource) context.getBean("dataSource");
// Use the dataSource to interact with the database
```
In this example, _FileSystemXmlApplicationContext_ is used to load an XML file that defines a _DataSource_ bean, but we can use it to define any bean that our application needs.

### AnnotationConfigApplicationContext

**When it comes to configuring the Spring application context in Java-based Spring applications, the AnnotationConfigApplicationContext is a popular choice among developers. There are several reasons why using AnnotationConfigApplicationContext is a good idea, including:**

* Type Safety: Annotations provide greater type safety than XML configuration. Annotations are checked during the compilation process, which helps prevent runtime errors that may occur when using XML configuration. This provides greater type safety and eliminates the need for runtime type conversions.

* Readability: Annotations are more concise and easier to read than XML configuration files. XML can become verbose and difficult to manage for large and complex applications. Annotations, on the other hand, provide a way to add context to the code, making it easier for developers to understand the purpose and functionality of the configuration.

* Centralization: Annotations allow configuration to be centralized in the codebase, making it easier to maintain and manage. With XML configuration, it can be difficult to locate and update the relevant configuration file for a specific component or module.

* Integration with IDEs: Annotations can be integrated with modern IDEs to provide code completion, navigation, and error checking. This provides a better developer experience and makes it easier to work with the code.

* Performance: Annotations are compiled into bytecode, which provides better performance compared to parsing and interpreting XML at runtime.

* Flexibility: Annotations provide greater flexibility compared to XML configuration. For example, you can use annotations to define configuration for specific environments, such as production or development, or to apply configuration selectively to specific beans or components.

Under the hood, _AnnotationConfigApplicationContext_ works by performing several steps to create the application context:

1. Scan for annotated classes:
   When _AnnotationConfigApplicationContext_ is created, it scans the specified packages for classes annotated with Spring's stereotype annotations such as _@Configuration, @Component, @Service, and @Repository_.
   These annotations are used to mark classes as being part of the Spring application and indicate that the class should be registered with the context.

For example, let's consider the following package structure:

```css
com.example.myapp
    ├── config
    │   └── AppConfig.java
    ├── service
    │   ├── UserService.java
    │   └── ProductService.java
    └── Main.java
```
Here, the classes _AppConfig_, _UserService_, and _ProductService_ are annotated with the _@Configuration_ and _@Service_ annotations respectively, which indicates that they should be registered with the Spring application context.

2. Create bean definitions:
   Once the annotated classes are detected, _AnnotationConfigApplicationContext_ creates bean definitions for each of the annotated classes. A bean definition is a configuration metadata that describes how to create and configure a bean, including its class, constructor arguments, property values, and other metadata.

For example, let's consider the following _AppConfig_ class:

```java
@Configuration
public class AppConfig {
    
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
    
    // other bean definitions...
}
```

Here, the _@Bean_ annotation is used to declare the beans _userService_ and _productService_. _AnnotationConfigApplicationContext_ creates bean definitions for these beans, which includes information such as the bean's class, its constructor arguments, and its dependencies.

3. Register bean definitions:
   After the bean definitions are created, _AnnotationConfigApplicationContext_ registers them with the application context. This allows the context to manage the lifecycle of the beans, including creating them when they are needed and destroying them when the context is closed.

4. Wire beans together:
   Once the beans are registered, _AnnotationConfigApplicationContext_ wires them together by resolving dependencies between them. This involves looking up other beans in the context and injecting them into the beans that need them.

For example, let's consider the following _UserService_ and _ProductService_ classes:
```java
@Service
public class UserServiceImpl implements UserService {
    
    private final ProductService productService;
    
    @Autowired
    public UserServiceImpl(ProductService productService) {
        this.productService = productService;
    }
    
    // implementation...
}

@Service
public class ProductServiceImpl implements ProductService {
    
    // implementation...
}
```
Here, the _UserServiceImpl_ depends on the _ProductService_, which is injected via the constructor using the _@Autowired_ annotation. _AnnotationConfigApplicationContext_ resolves this dependency by looking up the _ProductService_ bean that was registered earlier and injecting it into the _UserService_ bean.

5. Initialize the context:
   Finally, _AnnotationConfigApplicationContext_ initializes the context by calling any _@PostConstruct_ methods on the beans and firing any context-related events that have been registered. This allows the beans to perform any additional initialization steps that are required before they are used.

For example, let's consider the following _UserService_ class:

```java
@Service
public class UserServiceImpl implements UserService {
    
    @PostConstruct
    public void init() {
        // perform additional initialization steps here
    }
    
    // implementation...
}
```
Here, the _@PostConstruct_ annotation is used to mark a method that should be called after the bean has been created and wired together. _AnnotationConfigApplicationContext_ calls this method after the _UserService_ bean has been created and wired together.

Once the _AnnotationConfigApplicationContext_ has completed all of the above steps, the Spring application context is fully configured and ready to use.

Developers can retrieve beans from the context by calling the _getBean()_ method and passing in the class of the bean they want.
For example, the following code retrieves the UserService bean from the context:

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

UserService userService = context.getBean(UserService.class);
```
Here, the _getBean()_ method retrieves an instance of the _UserService_ class from the context. The type of the bean to be retrieved is passed as an argument to the _getBean()_ method.

Developers can also register additional beans with the context by calling the _register()_ method and passing in the class of the bean they want to register.
For example, the following code registers a _MyService_ bean with the context:

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

context.register(MyService.class);
```

Here, the _register()_ method adds the _MyService_ class to the list of beans that will be registered with the context.

Finally, when the Spring application is no longer needed, the context should be closed by calling the _close()_ method.
This allows the context to release any resources that it may have acquired during its lifetime. For example:

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
// use the context...
context.close();
```