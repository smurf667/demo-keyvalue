# Demonstrate KeyValue QueryEngine issue

This example project shows that with Spring Boot 3.3.0 the default `QueryEngine` in KeyValue has changed
from `SpelQueryEngine` to `PredicateQueryEngine`, breaking existing code.

The [documentation](https://docs.spring.io/spring-data/keyvalue/reference/keyvalue/template.html#key-value.template-query) for 3.3.0 states

> Running queries is managed by a `QueryEngine`. As mentioned earlier, you can instruct the `KeyValueAdapter` to use an implementation-specific `QueryEngine` that allows access to native functionality. When used without further customization, queries can be run by using `SpELQueryEngine`.

Both engines are package-protected, so it is unclear to me how to define the engine to use.
