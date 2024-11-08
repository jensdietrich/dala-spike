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

## Challenge 2 -- Aliasing

Invocation local aliasing can be tolerated, but leaking objects to other methods cannot. Note that the instrumentation does not deal
with variables, but alsways has access with to the actual objects. So from the instrumentaion point of view, invocation-local aliasing 
is transparent.

**TODO: double check / discuss**

## Challenge 3 - Transfer of Objects between Threads

1. always check for thread association in method returns, e.g. if an object returned by a method is annotated `@Local` and is associated with a different thread, fail
2. could take advantage  common patterns, i.e. objects used to transfer objects betwen threads. example: `BlockingQueue` with its `put` and `take` methods, perhaps model an intermediate `transfer` state where no thread owns the object

see `examples.Isolated3`

## Examples

See `src/main/java/examples` , comments indicate the intended behavioir to be injected by the instrumentation.



