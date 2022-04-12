package dokerplp.exception

import dokerplp.equations.Equation

class BreakOfTheFirstKind (override val message: String, val x: Double): BaseException(message)