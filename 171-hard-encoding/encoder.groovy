
codes = [
    'A': '000001',
    'B': '000010',
    'C': '000011',
    'D': '000100',
    'E': '000101',
    'F': '000110',
    'G': '000111',
    'H': '001000',
    'I': '001001',
    'J': '001010',
    'K': '001011',
    'L': '001100',
    'M': '001101',
    'N': '001110',
    'O': '001111',
    'P': '010000',
    'Q': '010001',
    'R': '010010',
    'S': '010011',
    'T': '010100',
    'U': '010101',
    'V': '010110',
    'W': '010111',
    'X': '011000',
    'Y': '011001',
    'Z': '011010',
    '0': '011011',
    '1': '011100',
    '2': '011101',
    '3': '011110',
    '4': '011111',
    '5': '100000',
    '6': '100001',
    '7': '100010',
    '8': '100011',
    '9': '100100',
    ' ': '000000'
]

decodeCodes = [:]
codes.entrySet().each {
    decodeCodes[it.value] = it.key
}

def encode(message) {
    def encMessage = ''
    message.every {
        encMessage += codes[it]
    }

    return encMessage
}

def decode(encMessage) {
    def message = ''
    def regex = ~/[01]{6}/
    def matcher = encMessage =~ regex
    while (matcher.find()) {
        message += decodeCodes[matcher.group()]
    }

    return message
}

def challenge(message) {
    println "Original message length: ${message.length() * 8} bits"
    def encMessage = encode(message)
    println "Encoded message length: ${encMessage.length()} bits (${(1 -(encMessage.length() / (message.length() * 8))) * 100}%)"
    def decMessage = decode(encMessage)
    println "Decoding message into ${decMessage.length() * 8} bits"
    if (decMessage == message) {
        println "Message matches!"  
    } else {
        println "Message doesn't match"
    }
}

[
    'REMEMBER TO DRINK YOUR OVALTINE',
    'GIANTS BEAT DODGERS 10 TO 9 AND PLAY TOMORROW AT 1300',
    'SPACE THE FINAL FRONTIER THESE ARE THE VOYAGES OF THE BIT STREAM DAILY PROGRAMMER TO SEEK OUT NEW COMPRESSION'
].each() {
    challenge it
}  