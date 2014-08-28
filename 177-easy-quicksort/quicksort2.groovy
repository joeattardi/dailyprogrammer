import java.util.Random

def numbers = [4, 10, -12.23, 14.41, 104, 31, 2]

println quicksort(numbers)

List quicksort(numbers) {
    if (numbers.size <= 1) {
        return numbers
    }

    def pivot = numbers.remove(new Random().nextInt(numbers.size))
    def result = numbers.split { it < pivot }.collect { quicksort it }
    result.add(1, pivot)
    return result.flatten()
}