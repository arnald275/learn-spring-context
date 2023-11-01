# 1 .ApplicatinContext
An application context is created by the Spring IoC container and initialized
with a configuration provided by a resource that can be an XML file (ClassPathXmlApplicationContext or FileSystemXmlApplicationContext) or a
configuration class (AnnotationConfigApplicationContext) or both

## An ApplicationContext implementation provides the following
1. Access to beans using bean factory methods
2. The ability to load file resources using ***relative(resources/config.xml) or absolute
paths(/resources/config.xml) or URLs(file:///c:/resources/config.xml)***
3. The ability to publish events to registered listeners 
4. The ability to resolve messages and support internationalization
(most used in international web applications)

## Resource Loading
When the resource is provided as a String value,
the Spring container tries to load the resource based on the prefix of that string value.
When instantiating an application context, different classes are used, based on the prefix


## _Prefixes and Corresponding Paths_
| prefix     |                              location                               |                                                                                                     comment                                                                                                      |
|:-----------|:-------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| no prefix  | In root directory where the class creating the context is executed  |                                                         The resource being loaded has a type that depends on the ApplicationContext instance being used                                                          |
| classpath: |         The resource should be obtained from the class-path         |          In the resources directory and the resource will be of type ClassPathResource. If the resource is used to create an application context, the ClassPathXmlApplicationContext class is suitable.          |
| file:      |            In the absolute location following the prefix            |  Resource is loaded as a URL, from the filesystemand the resource will be of type UrlResource. If the resource is used to create an application context, the FileSystemXmlApplicationContext class is suitable.  |
| http:      |               In the web location followingthe prefix               |                  Resource is loaded as a URL and the resourcewill be of type UrlResource. If the resource is used to create an application context, the WebApplicationContext class is suitable                  |

Depending on the context class used, the resource loaded can have one of the
following types.
1. If ctx is a ClassPathXmlApplicationContext instance resource type
will be ClassPathResource
2. If ctx is a FileSystemXmlApplicationContext instance resource type
will be FileSystemResource
3. If ctx is a WebApplicationContext instance resource type will be
ServletContextResource

***If we want to force the resource type, no matter
what context type is used, the resource must be specified using the desired prefix***


# 2. Bean Definition

A Spring IoC container manages one or more beans. These beans are created with the configuration
metadata that you supply to the container (for example, in the form of XML <bean/> definitions).
Within the container itself, these bean definitions are represented as BeanDefinition objects, which
contain (among other information) the following metadata:
1. A package-qualified class name: typically, the actual implementation class of the bean being defined.
2. Bean behavioral configuration elements, which state how the bean should behave in the
container (scope, lifecycle callbacks, and so forth).
3. References to other beans that are needed for the bean to do its work. These references are also
called collaborators or dependencies.
4. Other configuration settings to set in the newly created object — for example, the size limit of
the pool or the number of connections to use in a bean that manages a connection pool.

|      PROPERTY       |       EXPLANATION        |
|:-------------------:|:------------------------:|
|        class        |   Instantiating beans    |
|        name         |       naming beans       |
|        scope        |       Beans Scope        |
|  constructor Args   |   dependency injection   |
|     properties      |   dependency injection   |
|   Autowiring mode   | Autowiring Collaborator  |
| Lazy initialization | Lazy initializating bean |
| initializing method |  initializing callback   |
| destructing method  |   destructing callback   |



### Naming Beans
Every bean has one or more identifiers. These identifiers must be unique within the container that
hosts the bean. A bean usually has only one identifier. However, if it requires more than one, the
extra ones can be considered aliases.

The convention is to use the standard Java convention for instance field names when naming
beans. That is, bean names start with a lowercase letter and are camel-cased from there.
Examples of such names include accountManager, accountService, userDao, loginController, and
so forth.

### Instantiating Beans
A bean definition is essentially a recipe for creating one or more objects. The container looks at the
recipe for a named bean when asked and uses the configuration metadata encapsulated by that
bean definition to create (or acquire) an actual object.

### Instantiation with a Constructor
The Spring IoC container can manage virtually any class you want it to manage. It is not limited to
managing true JavaBeans. Most Spring users prefer actual JavaBeans with only a default (noargument) constructor and appropriate setters and getters modeled after the properties in the
container. You can also have more exotic non-bean-style classes in your container.

### Instantiation with a Static Factory Method
When defining a bean that you create with a static factory method, use the class attribute to specify
the class that contains the static factory method and an attribute named factory-method to specify
the name of the factory method itself.***You should be able to call this method and return a live object, which subsequently is treated as if it had
been created through a constructor***. One use for such a bean definition is to call static factories in
legacy code.

### Instantiation by Using an Instance Factory Method

Similar to instantiation through a static factory method, instantiation with an instance factory
method invokes a non-static method of an existing bean from the container to create a new bean.
To use this mechanism, leave the class attribute empty and, in the factory-bean attribute, specify
the name of a bean in the current (or parent or ancestor) container that contains the instance
method that is to be invoked to create the object. Set the name of the factory method itself with the
factory-method attribute.




























**note: The @Configuration annotation is a specialization of the @Component annotation,
which is the core annotation for creating beans. This means that the configuration
class itself is a declaration for a bean**