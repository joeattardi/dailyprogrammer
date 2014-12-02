var morseCodeTranslations = {
    "a": ".-", "b": "-...", "c": "-.-.", "d": "-..", "e": ".", 
    "f": "..-.", "g": "--.", "h": "....", "i": "...", "j": ".---",
    "k": "-.-", "l": ".-..", "m": "--", "n": "-.", "o": "---",
    "p": ".--.", "q": "--.-", "r": ".-.", "s": "...", "t": "-",
    "u": "..-", "v": "...-", "w": ".--", "x": "-..-", "y": "-.--", 
    "z": "--..", " ": "/"
};

var durations = {
    ".": 50,
    "-": 300,
    " ": 200,
    "/": 300
};

var context = new webkitAudioContext();

window.onload = function() {
    document.getElementById("translateButton").addEventListener("click", translateMorseCode);
    document.getElementById("listenButton").addEventListener("click", playMorseCode);
}

function translateMorseCode(e) {
    var inputText = document.getElementById("inputText").value.toLowerCase();
    var morseText = "";

    for (var i = 0; i < inputText.length; i++) {
        morseText += morseCodeTranslations[inputText[i]] + " ";
    }

    document.getElementById("output").style.display = "block";
    document.getElementById("morseCode").innerHTML = morseText;
}

function playMorseCode(e) {
    var morseText = document.getElementById("morseCode").innerHTML;
    
    var playSequence = [];

    for (var i = 0; i < morseText.length; i++) {
        playSequence.push({
            duration: durations[morseText[i]],
            tone: (morseText[i] === '.' || morseText[i] === '-')
        });
    }

    playTone(playSequence, 0);
}

function playTone(sequence, index) {
    if (index < sequence.length) {
        var duration = sequence[index].duration;

        if (sequence[index].tone) {
            var oscillator = context.createOscillator();
            oscillator.type = 0;
            oscillator.frequency.value = 800;
            oscillator.connect(context.destination);
            oscillator.noteOn && oscillator.noteOn(0);

            setTimeout(function() {
                oscillator.noteOff(0);
                oscillator.disconnect();
                setTimeout(function() {
                    playTone(sequence, index + 1);
                }, 100);
            }, duration);
        } else {
            setTimeout(function() {
                playTone(sequence, index + 1);
            }, duration);
        }
    }
}
