package advent_kotlin.domain

import java.math.BigInteger

class Monkey(
    var items: MutableList<BigInteger>,
    val operationString: String,
    val testDivisor: BigInteger,
    val targetIfTrue: Int,
    val targetIfFalse: Int
) {
    var numberOfInspections = 0
    fun getNextTarget(worryLevel: BigInteger): Int {
        return if ((worryLevel % testDivisor).toInt() == 0) {
            targetIfTrue
        } else {
            targetIfFalse
        }
    }

    fun getWorryLevelAfterOperation(item: BigInteger): BigInteger {
        return if ("old * old" in operationString) {
            item * item
        } else if ("old + " in operationString) {
            val add = operationString.split(" + ")[1].toBigInteger()
            item + add
        } else {
            val multi = operationString.split(" * ")[1].toBigInteger()
            item * multi
        }
    }

    fun getNextWorryLevel(item: BigInteger): BigInteger {
        numberOfInspections += 1
        return getWorryLevelAfterOperation(item)
    }
}

class Monkeys(val listMonkey: List<Monkey>) {
    fun makeARound() {
        var constantDivisor = BigInteger.ONE
        listMonkey.forEach { monkey ->
            constantDivisor *= monkey.testDivisor
        }

        listMonkey.forEach { monkey ->
            monkey.items.forEach { item ->
                val nextWorryLevel = monkey.getNextWorryLevel(item)
                val nextTarget = monkey.getNextTarget(nextWorryLevel)
                listMonkey[nextTarget].items.add(nextWorryLevel % constantDivisor)
            }
            monkey.items = mutableListOf()
        }
    }

    fun makeNRound(n: Int) {
        for (round in 1..n) {
            makeARound()
        }
    }

    fun getBusinessLevel(): BigInteger {
        val numberOfInspections = mutableListOf<Int>()
        listMonkey.forEach { monkey ->
            numberOfInspections.add(monkey.numberOfInspections)
        }
        return numberOfInspections.sortedDescending()[0].toBigInteger() * numberOfInspections.sortedDescending()[1].toBigInteger()
    }
}