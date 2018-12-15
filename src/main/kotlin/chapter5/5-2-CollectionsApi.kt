package chapter5

data class Animal(var name: String, val age: Int)

fun main(args: Array<String>) {
    val animals = listOf(Animal("Cat", 2), Animal("Dog", 4), Animal("Parrot", 7))

    // filter collection if boolean lambda expression == true
    println(animals.filter { it.age % 2 == 0 })

    // map applies lambda to each element in the collection
    println(listOf(1, 2, 3, 4).map { it * it })
    println(animals.map(Animal::name))

    // be careful when combining lambdas. this calculates the maxAge filter once for every element in the list!
    val maxAge = animals.filter { it.age == animals.maxBy { it.age }!!.age }

    // somewhat improved
    val maxAgeImproved = animals.maxBy { it.age }!!.age
    println(animals.filter { it.age == maxAgeImproved })

    // map function on a map
    val numbers = mapOf(1 to "one", 2 to "two", 3 to "three")
    println(numbers.mapValues { it.value.toUpperCase() })

    // any, all, count and find
    val canBeInClub27 = { p: Person -> p.age <= 27 }
    val people = listOf(Person("Joe", 32), Person("Anne", 24), Person("Bob", 27), Person("Joe", 27))
    println(people.all(canBeInClub27))
    println(people.any(canBeInClub27))
    println(people.count(canBeInClub27))
    println(people.first(canBeInClub27))

    // group by returns a map where the key is the property that was grouped by
    println(people.groupBy { it.age })
    println(people.groupBy { it.name })
    println(people.groupBy(canBeInClub27))
    println(people.map { it.name }.groupBy(String::first))

    // flat map
    class Book(val title: String, val authors: List<String>)
    val books = listOf(Book("ASD", listOf("Author 1", "Author 2")), Book("ASD1", listOf("Author 3", "Author 4")))
    // this returns two lists, with the authors for each book
    println(books.map { it.authors })
    // to combine into one list, use flatten
    println(books.map { it.authors }.flatten())
    // ur simply flatMap
    println(books.flatMap { it.authors })

    // all these methods create collections for intermediate results. to be more efficient, use sequences and collect to a list at the end
    println(animals.asSequence()
        .filter { it.age < 5 }
        .map { it.name }
        .toList())

    // On a collection, this map would be performed on each element
    println(listOf(1, 2, 3, 4, 5).asSequence()
        .map { it * it }
        .find { it > 8 })

    // another way to generate sequences. naturalNumbers computation is deferred until sum is called
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}