
def loadImage(hex) {
    def arr = new Character[8][8]
    hex.split(' ').eachWithIndex() { row, i ->
        def value = Integer.parseInt(row, 16)
        for (j in 7..0) {
            arr[i][7 - j] = ((value & 2**j) ? 'x' : ' ')
        }
    }

    return arr
}

def zoomIn(image) {
    def zoomedImage = new Character[image.length * 2][image[0].length * 2]
    
    def zx = 0, zy = 0

    for (iy in 0..image.length - 1) {
        for (ix in 0..image[iy].length - 1) {
            zoomedImage[zy][zx] = image[iy][ix]
            zoomedImage[zy + 1][zx++] = image[iy][ix]
            zoomedImage[zy][zx] = image[iy][ix]
            zoomedImage[zy + 1][zx++] = image[iy][ix]
        }
        zx = 0
        zy += 2
    }

    return zoomedImage
}

def zoomOut(image) {
    def zoomedImage = new Character[image.length / 2][image[0].length / 2]

    for (zy in 0..zoomedImage.length - 1) {
        for (zx in 0..zoomedImage[zy].length - 1) {
            zoomedImage[zy][zx] = image[zy * 2][zx * 2]
        }
    }

    return zoomedImage
}

def invert(image) {
    def invertedImage = new Character[image.length][image[0].length]
    for (y in 0..image.length - 1) {
        invertedImage[y] = new String(image[y]).replaceAll(' ', 'X').replaceAll('x', ' ')
            .replaceAll('X', 'x').toCharArray()
    }

    return invertedImage
}

def rotate(image, direction) {
    def rotatedImage = new Character[image.length][image[0].length]
    for (i in 0..(image.length - 1)) {
        for (j in 0..(image.length - 1)) {
            rotatedImage[i][j] = (direction > 0) ? image[image.length - 1 - j][i] : image[j][i]
        }
    }

    return rotatedImage
}

def printImage(image) {
    println()
    image.each {
        it.each {
            print(it)
        }
        println()
    }
    println()
}

def challenge(hex) {
    def image = loadImage hex
    println 'Original:'
    printImage image
    println 'Zoom x2:'
    image = zoomIn image
    printImage image
    println 'Rotate:'
    image = rotate image, 1
    printImage image
    println 'Zoom x2:'
    image = zoomIn image
    printImage image
    println 'Invert:'
    image = invert image
    printImage image
    println 'Zoom out x2:'
    image = zoomOut image
    printImage image
}

['FF 81 BD A5 A5 BD 81 FF',
 'AA 55 AA 55 AA 55 AA 55',
 '3E 7F FC F8 F8 FC 7F 3E',
 '93 93 93 F3 F3 93 93 93'].each() {
    challenge it
 }
