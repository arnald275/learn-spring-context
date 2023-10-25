# learn-spring-context
adding spring-context dependency to our project will adds the following packages:

1.spring-aop
2.spring-bean
3.spring-context
4.spring-core
5.spring-expression
6.spring-jcl

# About ApplicationContext
The org.springframework.context.ApplicationContext is the interface
implemented by classes that provide the configuration for an application. This interface
is an extension of the interface org.springframework.beans.factory.BeanFactory,
which is the root interface for accessing a Spring Bean container. Implementations
of ApplicationContext manage a number of bean definitions uniquely identified
by their name. Multiple Spring application context implementations exist, each one
specific to development needs. For example, if the configuration for an application is
provided using an XML file the implementation to use to read the bean declarations is
XmlWebApplicationContext or any of its extensions. You will be introduced to the most
important of them in this book. An ApplicationContext implementation provides the
following.
• Access to beans using bean factory methods
• The ability to load file resources using relative or absolute
paths or URLs
• The ability to publish events to registered listeners
• The ability to resolve messages and support internationalization
(most used in international web applications)