package groovyScripts

sum
coefficientBelow = 0.11
coefficientAbove = 0.05
threshold = 15

if (sum >= threshold) {
    return coefficientAbove
} else {
    return coefficientBelow
}