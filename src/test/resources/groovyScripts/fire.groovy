package groovyScripts

sum
coefficientBelow = 0.014
coefficientAbove = 0.024
threshold = 100

if (sum > threshold) {
    return coefficientAbove
} else {
    return coefficientBelow
}