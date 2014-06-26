@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import java.util.Random
import groovyx.net.http.RESTClient

def getNames(numRecords) {
    def names = []
    def client = new RESTClient('http://api.randomuser.me/?results=20')

    while (names.size < numRecords) {
        client.get(path: '').data.results.each() {
            def name = [firstName: it.user.name.first, lastName: it.user.name.last]
            if (!names.contains(name)) {
                names.add(name)    
            }
        }
    }

    return names
}

def getScores() {
    def rand = new Random()
    def scores = []
    (1..5).each() {
        scores.add(rand.nextInt(101))
    }

    return scores
}

def numRecords = args[0] as int
def names = getNames(numRecords)
for (i in 0..numRecords - 1) {
    printf("%-30s", "${names[i].lastName.toUpperCase()}, ${names[i].firstName.toUpperCase()}")
    getScores().each() {
        print "  ${it}"
    }
    println ''
}