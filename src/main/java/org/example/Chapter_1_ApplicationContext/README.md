# ApplicatinContext
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



**note: The @Configuration annotation is a specialization of the @Component annotation,
which is the core annotation for creating beans. This means that the configuration
class itself is a declaration for a bean**