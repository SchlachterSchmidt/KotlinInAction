package chapter4

import chapter4.Expr.Sum
import chapter4.Expr.Num

fun main(args: Array<String>) {

    val button = Button()
    button.click()
    button.showOff()

    val focusableAndClickableButton = FocusableAndClickable()

    focusableAndClickableButton.click()
    focusableAndClickableButton.showOff()
    focusableAndClickableButton.setFocus(false)

    val richerButton = RicherButton()
    richerButton.showOff()
    richerButton.animate()
    richerButton.click()
    richerButton.disabled()

    val animatedDog = AnimatedDog()
    animatedDog.animate()
    animatedDog.stopAnimation()
    animatedDog.animateTwice()

    println(eval(Sum(Num(4), Sum(Num(4), Num(8)))))

    val privateUser = PrivateUser("john.mcclane@nakatomi.com")
    println(privateUser.firstName)

    val classUser = UserClass(name = "Pete")
    classUser.address = "at home"
}