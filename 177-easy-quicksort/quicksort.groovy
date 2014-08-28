import java.util.Random

numbers = [4, 1.2, -5, 127, 5, 13, 10]

quicksort(numbers)
println numbers

def quicksort(numbers) {
    quicksort(numbers, 0, numbers.size - 1)
}

def quicksort(numbers, left, right) {
    if (left < right) {
        def pivotIndex = new Random().nextInt(right - left) + left
        def pivot = numbers[pivotIndex]

        swap(numbers, pivotIndex, right)
        def pos = left
        for (i in left..(right - 1)) {
            if (numbers[i] < pivot) {
                swap(numbers, i, pos++)
            }
        }
        swap(numbers, pos, right)

        quicksort(numbers, left, pos - 1)
        quicksort(numbers, pos + 1, right)
    }
}

def swap(list, i, j) {
    def tmp = numbers[i]
    numbers[i] = numbers[j]
    numbers[j] = tmp
}