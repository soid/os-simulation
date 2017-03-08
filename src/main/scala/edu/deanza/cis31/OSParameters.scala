package edu.deanza.cis31

import edu.deanza.cis31.io.IODevice
import edu.deanza.cis31.scheduler.Scheduler


class OSParameters(val memoryModel: OSParameters.MemoryModel.MemoryModel,
                   val blockSize: Int,
                   val numberOfBlocks: Int,
                   val maxProcesses: Int,
                   val schedulerAlgorithm: Scheduler,
                   val initialQuantum: Int,
                   val devices: List[IODevice])

object OSParameters {
  object MemoryModel extends Enumeration {
    type MemoryModel = Value
    val Fixed, Variable = Value
  }
}