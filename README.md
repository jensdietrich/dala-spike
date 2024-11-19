# dala-spike


## Basic Idea

Use capability annotations on objects. Develop agent to instrument code (invocations, field access, object creation sites)
to enforce access rules. Throw runtime exception if a rule is violated.

## Challenge 1 -- Annotating Objects (not Variables or Types)

Objects cannot be annotated directly, but local variables can. I.e. the following annotation looks like a type annotation, but actually 
annotates the local variable `obj`. However, we would need to interpret this as an annotation of the object that is being created here. 
This will require a custom annotation processor that creates sufficient information to inform the instrumentation. 

```java
 @Immutable Box obj = new Box("foo");
```

## DALA Model

The instrumentation uses global maps and sets (something like weak identity maps / sets to avoid messing with GC) to track object state, i.e. the association of objects 
with any of `{Immutable, Isolated,Local}`, and the association of certain objects with threads. If annotated objects are created, they are associated with the current thread.

The code inserted by the instrumentation maintains those global data structures, and uses them to enforce DALA rules.

See also: [Fernandez-Reyes, K., Gariano, I. O., Noble, J., Greenwood-Thessman, E., Homer, M., & Wrigstad, T. (2021, October). Dala: a simple capability-based dynamic language design for data race-freedom. In Proceedings Onward!21](https://dl.acm.org/doi/pdf/10.1145/3486607.3486747)

## Challenge 2 -- Aliasing

Invocation local aliasing can be tolerated, but leaking objects to other methods cannot. Note that the instrumentation does not deal
with variables, but always has access to the actual objects. So from the instrumentation point of view, invocation-local aliasing 
is transparent.

**TODO: double check / discuss**

## Challenge 3 - Transfer of Objects between Threads

1. always check for thread association in method returns, e.g. if an object returned by a method is annotated `@Local` and is associated with a different thread, fail
2. could take advantage of common patterns, i.e. objects used to transfer objects between threads. example: `BlockingQueue` with its `put` and `take` methods, perhaps model an intermediate `transfer` state where no thread is associated with the object (between `put` and `take`). 

see `examples.Isolated3`

## Examples

See `src/main/java/examples` , comments indicate the intended behavioir to be injected by the instrumentation.

## Useful Links

1. the [bytebuddy Maven plugin](https://github.com/raphw/byte-buddy/blob/master/byte-buddy-maven-plugin) -- an example of how bytecode transformations can be applied at build time
2. [Java Annotation Processing](https://www.baeldung.com/java-annotation-processing-builder) could be used to annotate objects using local variable annotations.



