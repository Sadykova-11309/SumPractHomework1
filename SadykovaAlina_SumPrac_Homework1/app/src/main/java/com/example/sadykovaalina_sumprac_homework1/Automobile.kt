package com.example.sadykovaalina_sumprac_homework1

import kotlin.random.Random

open class Automobile(
    val brand: String,
    val model: String,
    val releaseYear: Int,
    val maxSpeed: Int,
){
    open fun printInfo(){
        print("Brand: $brand, Model: $model, Release Year: ${releaseYear.toString()}, Maximum Speed: ${maxSpeed.toString()}")
    }
}

class Crossover(brand: String, model: String, releaseYear: Int, maxSpeed: Int,val enginePower : Int,) : Automobile(
    brand = brand,
    model = model,
    releaseYear = releaseYear,
    maxSpeed = maxSpeed,
)
{
    override fun printInfo() {
        super.printInfo()
        print("Engine Power: ${enginePower.toString()}")
    }
}

class Sedan(brand: String, model: String, releaseYear: Int, maxSpeed: Int, val color: String,) : Automobile(
    brand = brand,
    model = model,
    releaseYear = releaseYear,
    maxSpeed = maxSpeed,
)
{
    override fun printInfo() {
        super.printInfo()
        print(": $color")
    }
}

class Hatchback(brand: String, model: String, releaseYear: Int, maxSpeed: Int, val trunkCapacity: Int,) : Automobile(
    brand = brand,
    model = model,
    releaseYear = releaseYear,
    maxSpeed = maxSpeed,
)
{
    override fun printInfo() {
        super.printInfo()
        print("Trunk Capacity: ${trunkCapacity.toString()}")
    }
}

class Truck(brand: String, model: String, releaseYear: Int, maxSpeed: Int, val cargoCapacity: Int) : Automobile(
    brand = brand,
    model = model,
    releaseYear = releaseYear,
    maxSpeed = maxSpeed,
)
{
    override fun printInfo() {
        super.printInfo()
        print("Cargo Capacity: ${cargoCapacity.toString()}")
    }
}

fun createRandomCar(): Automobile {
    val brands = listOf("Toyota", "Honda", "Ford", "BMW", "Mercedes")
    val models = listOf("Camry", "Civic", "Focus", "X5", "C-Class")

    val brand = brands.random()
    val model = models.random()
    val releaseYear = Random.nextInt(1990, 2024)
    val maxSpeed = Random.nextInt(150, 300)
    val type = Random.nextInt(4)

    return when (type) {
        0 -> Crossover(brand, model, releaseYear, maxSpeed, Random.nextInt(200, 350))
        1 -> Sedan(brand, model, releaseYear, maxSpeed, listOf("Red", "White", "Black").random())
        2 -> Hatchback(brand, model, releaseYear,maxSpeed, Random.nextInt(200, 350))
        3 -> Truck(brand, model, releaseYear, maxSpeed, Random.nextInt(1000, 5000))
        else -> Automobile(brand, model, releaseYear, maxSpeed)
    }
}

fun automobileRace (car1: Automobile, car2: Automobile) : Automobile{
    val winner = if (car1.maxSpeed > car2.maxSpeed) car1 else car2
    println("Race Between ${car1.brand} ${car1.model} and ${car2.brand} ${car2.model}," +
            " Winner ${winner.brand} ${winner.model}")
    return winner
}

fun tournament(cars: MutableList<Automobile>): Automobile {
    while (cars.size > 1) {
        val racers = mutableListOf<Automobile>()
        for (i in 0 until cars.size step 2) {
            if (i + 1 < cars.size) {
                racers.add(automobileRace(cars[i], cars[i + 1]))
            } else {
                println(" ${cars[i].brand} ${cars[i].model} - No pair, next round")
                racers.add(cars[i])
            }
        }
        cars.clear()
        cars.addAll(racers)
    }
    return cars.first()
}

fun main() {
    print("Enter the number of cars: ")
    val carCount = readLine()?.toIntOrNull() ?: 0

    val cars = mutableListOf<Automobile>()
    for (i in 1..carCount) {
        cars.add(createRandomCar())
    }

    val winner = tournament(cars)
    println("The Winner: ${winner.brand} ${winner.model}")
}
