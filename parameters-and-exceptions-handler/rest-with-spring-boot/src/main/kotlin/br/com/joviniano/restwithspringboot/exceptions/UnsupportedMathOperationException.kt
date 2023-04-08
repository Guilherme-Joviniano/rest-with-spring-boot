package br.com.joviniano.restwithspringboot.exceptions

import java.lang.RuntimeException

class UnsupportedMathOperationException(exception: String?): RuntimeException(exception)