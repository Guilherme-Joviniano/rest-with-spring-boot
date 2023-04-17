package br.com.joviniano.restwithspringboot.controllers

import br.com.joviniano.restwithspringboot.exceptions.UnsupportedMathOperationException
import br.com.joviniano.restwithspringboot.helpers.convertToDouble
import br.com.joviniano.restwithspringboot.helpers.isNumeric
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController {
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double? {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double? {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/divide/{numberOne}/{numberTwo}"])
    fun divide(@PathVariable(value = "numberOne") numberOne: String?,
               @PathVariable(value = "numberTwo") numberTwo: String?): Double? {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")

        if (convertToDouble(numberTwo) == 0.0)
            throw UnsupportedMathOperationException("Please set a divisor higher than zero")

        return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/multiple/{numberOne}/{numberTwo}"])
    fun multiple(@PathVariable(value = "numberOne") numberOne: String?,
               @PathVariable(value = "numberTwo") numberTwo: String?): Double? {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")

        return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }
}