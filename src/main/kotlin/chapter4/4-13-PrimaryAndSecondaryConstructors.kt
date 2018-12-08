package chapter4

interface EmptyInterface
open class NoAttributes

// if you inherit from a class with default constructor (class with no constructor defined) you must invoke the constructor
// interfaces have no constructors
class SubClassOfNoAttributes : NoAttributes(), EmptyInterface

class Secret private constructor()

// private constructors cannot be called from the outside of the class
// useful to restrict access to companion objects. Kotlin has other features better suited to express a singleton
// val secret = Secret()

// this class does not have a default primary constructor out of the box
open class NoPrimaryConstructor {
    constructor(view: View) {
        // some code
    }
    constructor(view: View, button: Button) {
        // some code
    }
}

// delegating to superclass constructor. Note the `: super()` syntax
class NoPrimaryConstructorSubClass : NoPrimaryConstructor {
    constructor(view: View) : super(view)
    constructor(view: View, button: Button) : super(view, button)
    // defining a 3rd constructor with default value delegating to another constructor with `this`
    constructor() : this(view = UIElement())
}
