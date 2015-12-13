<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. SOLID</a>
<ul>
<li><a href="#sec-1-1">1.1. Single Responsibility Principle</a>
<ul>
<li><a href="#sec-1-1-1">1.1.1. What for:</a></li>
<li><a href="#sec-1-1-2">1.1.2. When to apply:</a></li>
<li><a href="#sec-1-1-3">1.1.3. How to find:</a></li>
<li><a href="#sec-1-1-4">1.1.4. How to introduce:</a></li>
<li><a href="#sec-1-1-5">1.1.5. Examples</a></li>
</ul>
</li>
<li><a href="#sec-1-2">1.2. Open Closed Principle</a>
<ul>
<li><a href="#sec-1-2-1">1.2.1. What for:</a></li>
<li><a href="#sec-1-2-2">1.2.2. When to apply:</a></li>
<li><a href="#sec-1-2-3">1.2.3. How to find:</a></li>
<li><a href="#sec-1-2-4">1.2.4. How to introduce:</a></li>
<li><a href="#sec-1-2-5">1.2.5. Examples:</a></li>
</ul>
</li>
<li><a href="#sec-1-3">1.3. Liskov Substitution Principle</a>
<ul>
<li><a href="#sec-1-3-1">1.3.1. What for:</a></li>
<li><a href="#sec-1-3-2">1.3.2. How to find:</a></li>
<li><a href="#sec-1-3-3">1.3.3. How to introduce:</a></li>
<li><a href="#sec-1-3-4">1.3.4. Examples:</a></li>
</ul>
</li>
<li><a href="#sec-1-4">1.4. Interface Segregation Principle</a>
<ul>
<li><a href="#sec-1-4-1">1.4.1. What for:</a></li>
<li><a href="#sec-1-4-2">1.4.2. When to apply:</a></li>
<li><a href="#sec-1-4-3">1.4.3. How to find:</a></li>
<li><a href="#sec-1-4-4">1.4.4. How to introduce:</a></li>
<li><a href="#sec-1-4-5">1.4.5. Examples:</a></li>
</ul>
</li>
<li><a href="#sec-1-5">1.5. Dependency Inversion Principle</a>
<ul>
<li><a href="#sec-1-5-1">1.5.1. What for:</a></li>
<li><a href="#sec-1-5-2">1.5.2. When to apply:</a></li>
<li><a href="#sec-1-5-3">1.5.3. How to find:</a></li>
<li><a href="#sec-1-5-4">1.5.4. How to introduce:</a></li>
<li><a href="#sec-1-5-5">1.5.5. Examples:</a></li>
</ul>
</li>
</ul>
</li>
</ul>
</div>
</div>

# SOLID<a id="sec-1" name="sec-1"></a>

## Single Responsibility Principle<a id="sec-1-1" name="sec-1-1"></a>

"A class should have one, and only one, reason to change."

Responsibility in SRP = reason of change

### What for:<a id="sec-1-1-1" name="sec-1-1-1"></a>

-   mixed responsibilities are coupled and can cause unwanted, mutual changes;
    it means a need to test modules/applications that are using this class,
    but for different reasons
-   modules using one responsibility are coupled with dependencies bring by
    other modules that are using other responsibilities
-   classes with many responsibilities are usually harder to test, because
    they have more dependencies
-   increase probability of conflicts when working on source code, because
    programmers may want to modify the same class, but for different reasons

### When to apply:<a id="sec-1-1-2" name="sec-1-1-2"></a>

-   it depends on how application is modified
-   we can talk about "axis of change" if and only if such changes are really
    happening
-   if modifications don't require adjusting different areas of
    responsibilities, then no need to change classes; for example a change in
    requirements is causing a modification of only one area of responsibility;
    it doesn't apply to mixing business rules with other mechanisms
    (e.g. persistency), because although their usually change at the same
    time, they change for different reasons

### How to find:<a id="sec-1-1-3" name="sec-1-1-3"></a>

-   class/interface methods are on different levels of abstraction (see Modem)
-   methods of a class work on different business areas
    For example Rectangle - calculates area (geometry) and drawing on a screen
    (GUI), or Employee and persistence subsystem
-   refactoring to Composed Method isolates methods that are not doing what
    the class contract is about (for example OrderProcessor class may have
    methods converting dates or that search in collections, etc.)
-   TDD usually naturally leads to separation of responsibilities

### How to introduce:<a id="sec-1-1-4" name="sec-1-1-4"></a>

Refactoring to Composed Method allows identify different responsibilities
appearing in a class. Such code can be moved to separate/own classes with
properly defined responsibilities.

### Examples<a id="sec-1-1-5" name="sec-1-1-5"></a>

1.  Rectangle

        public interface Rectangle {
            void draw();
            double area();
        }
        
        public interface Rectangle {
            void draw();
        }
        
        public interface GeometricRectangle {
            double area();
        }

2.  Modem -> ModemConnection + ModemDataChannel

        public interface Modem {
             public void dial (String phoneNumber);
             public void send (byte [ ] message);
             public byte [ ] receive();
             public void hangUp();
        }
        
        public interface ModemConnection {
             public void dial (String phoneNumber);
             public void hangUp();
        }
        
        public interface ModemDataChannel {
             public void send (byte [ ] message);
             public byte [ ] receive();
        }

## Open Closed Principle<a id="sec-1-2" name="sec-1-2"></a>

"Software entities (classes, modules, functions, etc.) should be open for
extension, but closed for modification."

"You should be able to extend a classes behavior, without modifying it."

### What for:<a id="sec-1-2-1" name="sec-1-2-1"></a>

To reduce impact of change, which means:
-   flexibility,
-   code reusability,
-   maintainability.

Modules that conform to OCP are:
1.  Open for extension by adding new functionality.
2.  Closed for modifications, which means that existing code nor binaries
    will be modified.

### When to apply:<a id="sec-1-2-2" name="sec-1-2-2"></a>

On the first change of requirements.

1.  How to stimulate changes:

    -   TDD
    -   short development cycles (in days)
    -   implement functionality before infrastructure and demo frequently
    -   prioritize functionalities
    -   release fast, release often

### How to find:<a id="sec-1-2-3" name="sec-1-2-3"></a>

-   a change in requirements is causing a series of changes in dependent
    modules; in such case there is a need to refactor to OCP
-   proliferation of code (if-else, switch) implementing different behaviors
    that depend on broken abstraction (type)

### How to introduce:<a id="sec-1-2-4" name="sec-1-2-4"></a>

Introduce constant abstractions and exchangeable implementations.

-   closing refers to selected type of changes
-   ability to foresight changes steam from experience and understanding of
    business domain
-   mind costs of OCP: increases complexity and amount of work

### Examples:<a id="sec-1-2-5" name="sec-1-2-5"></a>

Everything what introduces abstractions to shield from changeability, e.g.:
Template Method, Strategy, Bridge, etc.

## Liskov Substitution Principle<a id="sec-1-3" name="sec-1-3"></a>

"Derived classes must be substitutable for their base classes."

LSP is about behavior (IS-A).

LSP violation leads to violation of OCP.

Model (class hierarchy) is always evaluated in client's context, so project
correctness should be also analyzed in context of specific applications.

### What for:<a id="sec-1-3-1" name="sec-1-3-1"></a>

-   correct inheritance hierarchies
-   ability to freely substitute reduces complexity
-   allows to extend modules without direct modification of them

### How to find:<a id="sec-1-3-2" name="sec-1-3-2"></a>

Subclasses eliminate part of functionality of their superclasses:
-   subclass inherits unneeded properties (for example Square inherits height
    and wight from Rectangle)
-   violation of LSP manifests itself as verification of types (instance-of)
-   change of subclass requires modification of its superclass

### How to introduce:<a id="sec-1-3-3" name="sec-1-3-3"></a>

-   Design By Contract (descriptions and/or tests of class/interface contracts)
-   Re-declaration of a procedure in subclass can replace preconditions only
    with conditions that are equal or weaker, and postconditions with
    conditions equal or stronger than its base class.

### Examples:<a id="sec-1-3-4" name="sec-1-3-4"></a>

Violation: Square inherits from Rectangle
LSP: collections in Java, streams, sorting algorithm, Strategy pattern

## Interface Segregation Principle<a id="sec-1-4" name="sec-1-4"></a>

"Make fine grained interfaces that are client specific."

### What for:<a id="sec-1-4-1" name="sec-1-4-1"></a>

-   elimination of fat, unwieldy interfaces
-   reduction of conceptual weight
-   reduction of coupling between clients, hence impact of changes

### When to apply:<a id="sec-1-4-2" name="sec-1-4-2"></a>

-   a client is forced to change only because other client is causing changes
    in a part of the same interface, but set of functions never used by
    affected client

### How to find:<a id="sec-1-4-3" name="sec-1-4-3"></a>

-   different clients are using different sets of methods

### How to introduce:<a id="sec-1-4-4" name="sec-1-4-4"></a>

-   complex and not cohesive interfaces split into separate interfaces that
    contain smaller groups of methods; each group serves different set of
    clients
-   clients have access to implementing object through delegation or base
    class; no direct access
-   introduce new interfaces into existing objects

### Examples:<a id="sec-1-4-5" name="sec-1-4-5"></a>

RestMQ as OwnMessageQueue

## Dependency Inversion Principle<a id="sec-1-5" name="sec-1-5"></a>

"Depend on abstractions, not on concretions."

1.  High-level modules should not depend on low-level modules. Both should
    depend on abstractions.

2.  Abstractions should not depend on details. Details should depend on
    abstractions.

High-level modules = business logic (strategic decisions and application
business models). Low-level modules cannot force them to change.

With DIP - OO project, without DIP - procedural (no matter which language).

### What for:<a id="sec-1-5-1" name="sec-1-5-1"></a>

-   resistance of code to changes
-   reuse of high level modules (business logic)
-   to build projects based on frameworks
-   ease of maintenance

### When to apply:<a id="sec-1-5-2" name="sec-1-5-2"></a>

-   applicable always when objects exchange messages

### How to find:<a id="sec-1-5-3" name="sec-1-5-3"></a>

-   abstractions depend on implementations
-   business logic depends on supplementary mechanisms (e.g. an algorithm
    depends on device handling)

### How to introduce:<a id="sec-1-5-4" name="sec-1-5-4"></a>

-   define abstract interfaces in higher layers, which can be implemented by
    lower layers
-   elements, which should stay immutable, operate on abstractions

### Examples:<a id="sec-1-5-5" name="sec-1-5-5"></a>

Button->SwitchableDevice->Lamp, Layered Architecture
