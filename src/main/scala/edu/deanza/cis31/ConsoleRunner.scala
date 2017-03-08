package edu.deanza.cis31

import edu.deanza.cis31.instructions.CPUBurstInstruction
import edu.deanza.cis31.io.{CD, DiskDevice, TapeDevice}
import edu.deanza.cis31.scheduler.FIFOScheduler


object ConsoleRunner {

  def main(args: Array[String]) {
    // TODO read from files instead of creating it in java
    /**
      * Mem   Blk   Num   Max   Pro.  Init  Disk Tape CD    Print
      * Mode  Size  Blks  Proc. Alg.  Qtm.  unts unts unts  every-N-unts
      * 1) F    5000  20    10    FIFO  10     3    3    3      50
      */
    val devices = List(
      new DiskDevice, new DiskDevice, new DiskDevice,
      new DiskDevice, new DiskDevice, new DiskDevice,
      new TapeDevice, new TapeDevice, new TapeDevice,
      new CD, new CD, new CD)
    val params = new OSParameters(OSParameters.MemoryModel.Fixed, 5000, 20, 10, new FIFOScheduler, 10, devices)
    params.schedulerAlgorithm.osParams = params // essentially it's dependency injection

    /**
      * Program  Memory  Init. Run-Time Characteristics
      * Name     Needs   Pri.
      * 1) GOODPGMR  40      3    10, 20-1| 8, 20-1| 8, 20-1|10, 20-1| 8, 20-1| 8
      * 2) ARTSCLAS  120     2    10, 30-1|10, 30-1| 5, 30-1|10, 30-1|10, 30-1| 5
      */
    var instructions = List(
      new CPUBurstInstruction(10),
      new CPUBurstInstruction(8)
    )
    val prgGOODPGMR = new Program("GOODPGMR", 40, 3, instructions)

    instructions = List(
      new CPUBurstInstruction(10),
      new CPUBurstInstruction(10)
    )
    val prgARTSCLAS = new Program("ARTSCLAS", 120, 2, instructions)

    // simulation
    val simulation = new ConsoleSimulation(params, 10, List(prgGOODPGMR, prgARTSCLAS))

    // done with configuration. Run!
    simulation.run()
  }

}
