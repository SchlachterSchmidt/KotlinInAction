package chapter7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        val propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class EmployeeWithObservableProperties(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value) }

    private val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value) }
}

fun main() {

    // we have removed the duplication out of the Employee class, and now this looks a lot like the example 1 from delegation
    val employee = EmployeeWithObservableProperties("Pete", 44, 50000)
    employee.addPropertyChangeListener(PropertyChangeListener { event -> println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}") })
    employee.age = 55
    employee.salary = 55000
    // in the next example we will make the ObservableProperty a delegate
}