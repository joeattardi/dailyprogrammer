addends = System.console().readLine().split('\\+')
numDigits = addends.max() { it.length() }.length()
digits = addends*.padLeft(numDigits, '0')*.toList()
carry = ('0' * numDigits).split('')
sum = addends*.toInteger().sum()

for (i in (numDigits - 1)..0) {
    digitSum = digits.inject(0, { acc, val -> 
        acc + val[i].toInteger()
    }) + carry[i].toInteger()

    if (i > 0) carry[i - 1] = (digitSum / 10).toInteger()
}

sumLength = sum.toString().length()
println carry.join('').replace('0', ' ').padLeft(sumLength + 2, ' ')
println '-' * (sumLength + 2)
addends.eachWithIndex() { num, index ->
    if (index == addends.length - 1) println '+' + num.padLeft(sumLength + 1, ' ')
    else println num.padLeft(sumLength + 2, ' ') 
}
println '-' * (sumLength + 2)
println sum.toString().padLeft(sumLength + 2)
