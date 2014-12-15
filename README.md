Zucchini
========

Zucchini is a BDD-style testing library that integrates well with JUnit and other testing frameworks.

## Getting Started

You might want to take a look at the blog post [Getting Started with Zucchini](https://blog.codecentric.de/en/2014/07/getting-started-zucchini/).

## Installation

### Using Maven

Binaries, sources, and javadocs are available from Maven Central.

You can use the BDD-style fluent interfaces alone:

```xml
<dependency>
	<groupId>de.codecentric.zucchini</groupId>
	<artifactId>zucchini-bdd</artifactId>
	<version>2.0.1</version>
</dependency>
```

or with Selenium support:

```xml
<dependency>
	<groupId>de.codecentric.zucchini</groupId>
	<artifactId>zucchini-web</artifactId>
	<version>2.0.1</version>
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

Zucchini is released under version 2.0 of the [Apache License][].

[Pull requests]: http://help.github.com/send-pull-requests
[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
