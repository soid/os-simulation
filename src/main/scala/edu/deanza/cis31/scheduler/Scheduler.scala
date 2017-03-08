package edu.deanza.cis31.scheduler

import edu.deanza.cis31.OSParameters
import edu.deanza.cis31.kernel.Process

abstract class Scheduler {

  var osParams: OSParameters = null

  def currentProcess() : Process
  def tick(): Unit
  def exec(process: Process): Unit
  def getAllProcesses: List[Process]
  def terminateCurrentProcess(): Unit

}
