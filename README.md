Zucchini
========

Zucchini is a BDD-style testing library that integrates well with JUnit and other testing frameworks.

## Installation

### Using Maven

Binaries, sources, and javadocs are available from Maven Central.

You can use the BDD-style fluent interfaces alone:

```xml
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>zucchini-bdd</artifactId>
	<version>1.1.0</version>
</dependency>
```

or with Selenium support:

```xml
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>zucchini-web</artifactId>
	<version>1.1.0</version>
</dependency>
```

### Without Maven

You can easily add this library to your classpath. Make sure you add the following dependencies to your classpath:

* org.slf4j:slf4j-api
* org.mockito:mockito-all
* junit:junit

## Examples

See the zucchini-examples project.

## Contributing

[Pull requests][] are welcome.

## License

CCUnit is released under version 2.0 of the [Apache License][].

[Pull requests]: http://help.github.com/send-pull-requests
[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
