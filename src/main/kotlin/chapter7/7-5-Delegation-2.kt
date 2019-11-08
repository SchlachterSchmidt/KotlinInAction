package chapter7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Employee(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}


fun main() {

    // this is a longwinded way to add listeners to the properties
    val employee = Employee("James", 45, 65000)
    employee.addPropertyChangeListener(PropertyChangeListener { event -> println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}") })
    employee.age = 50
    employee.salary = 70000

    // in the next example we'll remove the duplication in the setters by extracting this out into a class
}