package chapter7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

class ObservableByDelegation(var propValue: Int, val changeSupport: PropertyChangeSupport) {

    operator fun getValue(e: EmployeeWithDelegatedObservableProperty, prop: KProperty<*>): Int = propValue
    operator fun setValue(e: EmployeeWithDelegatedObservableProperty, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class EmployeeWithDelegatedObservableProperty(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableByDelegation(age, changeSupport)
    var salary: Int by ObservableByDelegation(salary, changeSupport)
}

fun main() {

    // through the by keyword, we let the compiler do exctly what we did manually in the example before.
    val employee = EmployeeWithDelegatedObservableProperty("Adam", 34, 39000)
    employee.addPropertyChangeListener(PropertyChangeListener { event -> println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}") })
    employee.age = 37
    employee.salary = 44000
}