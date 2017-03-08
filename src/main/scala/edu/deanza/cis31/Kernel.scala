package edu.deanza.cis31

import edu.deanza.cis31.kernel.Process

class Kernel(params: OSParameters) {

  var time: Int = 0
  val scheduler = params.schedulerAlgorithm

  // callbacks for simulators
  var onTermination : (Process) => Unit = null

  /**
    * Advance clock. Emulate a unit of work.
    */
  def clock(): Unit = {
    time += 1
    val finished = scheduler.currentProcess().cycle()
    if (finished) {
      val curr = scheduler.currentProcess()
      scheduler.terminateCurrentProcess()
      onTermination(curr)
    }

    scheduler.tick()
  }

  // System calls below

  /**
    * Execute a program
    * @param program
    * @return process id
    */
  def exec(program: Program) = {
    val p = new Process(program)
    p.pid = getProcessesList().length +1
    scheduler.exec(p)
    p.pid
  }

  def malloc(mem: Int) {
  }

  def getProcessesList() = {
    scheduler.getAllProcesses
  }

  // private kernel data


}

