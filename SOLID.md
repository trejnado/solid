<div id="table-of-contents">
<h2>SOLID Cheat Sheet</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#orgheadline8">Single Responsibility Principle</a>
<li><a href="#orgheadline15">Open Closed Principle</a>
<li><a href="#orgheadline20">Liskov Substitution Principle</a>
<li><a href="#orgheadline26">Interface Segregation Principle</a>
<li><a href="#orgheadline32">Dependency Inversion Principle</a>
<li><a href="#orgheadline35">Reading</a>
</ul>
</div>
</div>

# Single Responsibility Principle<a id="orgheadline8"></a>

"A class should have one, and only one, reason to change."

It doesn't mean that a class should have only one method, but that all its
members are related to its primary function.

Responsibility in SRP = reason of change

## What for<a id="orgheadline1"></a>

-   classes with many responsibilities are usually harder to test, because
    they have more dependencies, and that leads to bugs
-   classes with many responsibilities are harder to understand, which leads
    to bugs
-   mixed responsibilities are coupled and can cause unwanted, mutual changes;
    it means a need to test modules/applications that are using this class,
    but for different reasons
-   modules using one responsibility are coupled with dependencies bring by
    other modules that are using other responsibilities and that makes them
    harder to modify
-   increase probability of conflicts when working on source code, because
    programmers may want to modify the same class, but for different reasons
    and that may result in teammate violation :-)

## When to apply<a id="orgheadline2"></a>

-   it depends on how application is modified
-   we can talk about "axis of change" if and only if such changes are really
    happening
-   if modifications don't require adjusting different areas of
    responsibilities, then no need to change classes; for example a change in
    requirements is causing a modification of only one area of responsibility;
    it doesn't apply to mixing business rules with other mechanisms
    (e.g. persistency), because although their usually change at the same
    time, they change for different reasons

## How to find<a id="orgheadline3"></a>

-   class/interface methods are on different levels of abstraction (see Modem)
-   methods of a class work on different business areas; for example
    Rectangle - calculates area (geometry) and drawing on a screen (GUI), or
    Employee and persistence subsystem
-   refactoring to Composed Method isolates methods that are not doing what
    the class contract is about (for example OrderProcessor class may have
    methods that convert dates or search in collections, etc.)
-   TDD usually naturally leads to separation of responsibilities

## How to introduce<a id="orgheadline4"></a>

Refactoring to Composed Method allows identify different responsibilities
appearing in a class. Such code can be moved to separate classes with
properly defined responsibilities.

## Examples<a id="orgheadline7"></a>

### Rectangle<a id="orgheadline5"></a>

Before SRP:

    public interface Rectangle {
        void draw();
        double area();
    }

After SRP:

    public interface Rectangle {
        void draw();
    }
    
    public interface GeometricRectangle {
        double area();
    }

### Modem -> ModemConnection + ModemDataChannel<a id="orgheadline6"></a>

Before SRP:

    public interface Modem {
         public void dial (String phoneNumber);
         public void send (byte [ ] message);
         public byte [ ] receive();
         public void hangUp();
    }

After SRP:

    public interface ModemConnection {
         public void dial (String phoneNumber);
         public void hangUp();
    }
    
    public interface ModemDataChannel {
         public void send (byte [ ] message);
         public byte [ ] receive();
    }

# Open Closed Principle<a id="orgheadline15"></a>

"Software entities (classes, modules, functions, etc.) should be open for
extension, but closed for modification."

"You should be able to extend a classes behavior, without modifying it."

## What for<a id="orgheadline9"></a>

To reduce impact of change, which means:

-   flexibility,
-   code reusability,
-   maintainability.

Modules that conform to OCP are:

1.  Open for extension by adding new functionality.
2.  Closed for modifications, which means that existing code nor binaries
    will be modified.

## When to apply<a id="orgheadline11"></a>

On the first change of requirements.

### How to stimulate changes:<a id="orgheadline10"></a>

-   TDD
-   short development cycles (in days)
-   implement functionality before infrastructure and demo frequently
-   prioritize functionalities
-   release fast, release often

## How to find<a id="orgheadline12"></a>

-   a change in requirements is causing a series of changes in dependent
    modules; in such case there is a need to refactor to OCP
-   proliferation of code (if-else, switch) implementing different behaviors
    that depend on broken abstraction (type)

## How to introduce<a id="orgheadline13"></a>

Introduce constant abstractions and exchangeable implementations.

Remember:

-   "closed for modifications" refers to selected type of changes
-   ability to foresight changes steam from experience and understanding of
    business domain
-   mind costs of OCP: increases complexity and amount of work

## Examples<a id="orgheadline14"></a>

Everything what introduces abstractions to shield from changeability, e.g.:
Template Method, Strategy, Bridge, etc.

# Liskov Substitution Principle<a id="orgheadline20"></a>

"Derived classes must be substitutable for their base classes."

LSP is about behavior (IS-A).

LSP violation leads to violation of OCP.

Model (class hierarchy) is always evaluated in client's context, so project
correctness should be also analyzed in context of specific applications.

## What for<a id="orgheadline16"></a>

-   ability to freely substitute reduces complexity
-   allows to extend modules without direct modification of them

## How to find<a id="orgheadline17"></a>

Subclasses eliminate part of functionality of their superclasses:

-   subclass inherits unneeded properties (for example Square inherits height
    and wight from Rectangle)
-   violation of LSP manifests itself as verification of types (instance-of)
-   change of subclass requires modification of its superclass

## How to introduce<a id="orgheadline18"></a>

-   break invalid inheritance hierarchy
-   create hierarchy model around different concept
-   Design By Contract (descriptions and/or tests of class/interface contracts)
-   Re-declaration of a procedure in subclass can replace preconditions only
    with conditions that are equal or weaker, and postconditions with
    conditions equal or stronger than its base class.

## Examples<a id="orgheadline19"></a>

Violation: Square inherits from Rectangle, Compound Processors in Camel
LSP: collections in Java, streams, Adapter pattern

# Interface Segregation Principle<a id="orgheadline26"></a>

"Make fine grained interfaces that are client specific."

## What for<a id="orgheadline21"></a>

-   elimination of fat, unwieldy interfaces
-   reduction of conceptual weight
-   reduction of coupling between clients, hence impact of changes

## When to apply<a id="orgheadline22"></a>

-   a client is forced to change only because other client is causing changes
    in a part of the same interface, but set of functions never used by
    affected client
-   an interface is hard to understand

## How to find<a id="orgheadline23"></a>

-   different clients are using different sets of methods

## How to introduce<a id="orgheadline24"></a>

-   complex and not cohesive interfaces split into separate interfaces that
    contain smaller groups of methods; each group serves different set of
    clients
-   clients have access to implementing object through delegation or base
    class; no direct access
-   introduce new interfaces into existing objects

## Examples<a id="orgheadline25"></a>

Big controllers splint into small, based on use cases.

# Dependency Inversion Principle<a id="orgheadline32"></a>

"Depend on abstractions, not on concretions."

1.  High-level modules should not depend on low-level modules. Both should
    depend on abstractions.

2.  Abstractions should not depend on details. Details should depend on
    abstractions.

High-level modules = business logic (strategic decisions and application's
business models). Low-level modules cannot force them to change.

With DIP - OO project, without DIP - procedural (no matter which language).

## What for<a id="orgheadline27"></a>

-   resistance of code to changes
-   reuse of high level modules (business logic)
-   to build projects based on frameworks
-   ease of maintenance

## When to apply<a id="orgheadline28"></a>

-   applicable always when objects exchange messages :-)

## How to find<a id="orgheadline29"></a>

-   abstractions depend on implementations
-   business logic depends on supplementary mechanisms (e.g. an algorithm
    depends on device handling)

## How to introduce<a id="orgheadline30"></a>

-   define abstract interfaces in higher layers, which can be implemented by
    lower layers
-   elements (parts of system), which should stay immutable, operate on
    abstractions

## Examples<a id="orgheadline31"></a>

Layered Architecture, Hexagonal Architecture, game logic

# Reading<a id="orgheadline35"></a>

- Composed Method in "Refactoring to Patterns" by Joshua Kerievsky
- Chapters 8 to 12 in "Agile Principles, Patterns, and Practices in C#" by Robert Martin<a id="orgheadline33"></a>
- GRASP Patterns in "Applying UML and Patterns" by Craig Larman<a id="orgheadline34"></a>
