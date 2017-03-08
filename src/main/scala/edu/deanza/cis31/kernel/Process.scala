package edu.deanza.cis31.kernel

import edu.deanza.cis31.Program


object Process {
  object State extends Enumeration {
    type State = Value
    val Ready, Running, Waiting, Blocked, Terminated = Value
  }
}

class Process(val program : Program) {

  var pid = 0
  var state : Process.State.State = Process.State.Ready
  var currentInstructionId : Int = 0

  def cycle(): Boolean = {
    assert(currentInstructionId < program.instructions.length)

    val curInstruction = program.instructions(currentInstructionId)
    val finished = curInstruction.cycle()

    if (finished) {
      currentInstructionId += 1
      if (currentInstructionId == program.instructions.length) {
        // finished all instructions
        return true
      }
    }

    return false
  }

}
