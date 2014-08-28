def rotate(image, direction) {
    def rotatedImage = new Character[image.length][image[0].length]
    
    for (i in 0..(image.length - 1)) {
        for (j in 0..(image.length - 1)) {
            if (direction > 0) {
                rotatedImage[i][j] = image[image.length - 1 - j][i]
            } else {
                rotatedImage[i][j] = image[j][i]            
            }
        }
    }

    return rotatedImage
}

def printImage(image) {
    println()
    image.each() {
        it.each() {
            print(it)
        }
        println()
    }
    println()
}

def arr = new Character[4][4]
arr[0][0] = 'a'
arr[0][1] = 'a'
arr[0][2] = 'a'
arr[0][3] = 'a'
arr[1][0] = 'b'
arr[1][1] = 'b'
arr[1][2] = 'b'
arr[1][3] = 'b'
arr[2][0] = 'c'
arr[2][1] = 'c'
arr[2][2] = 'c'
arr[2][3] = 'c'
arr[3][0] = 'd'
arr[3][1] = 'd'
arr[3][2] = 'd'
arr[3][3] = 'd'

printImage arr
printImage rotate(arr, -1)
printImage rotate(arr, 1)