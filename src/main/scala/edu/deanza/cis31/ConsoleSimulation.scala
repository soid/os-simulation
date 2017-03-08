package edu.deanza.cis31


class ConsoleSimulation(val osParameters : OSParameters, val printEveryUnits : Int,
                        val programs: List[Program]) {

  def run(): Unit = {
    val kernel: Kernel = new Kernel(osParameters);

    // setup
    kernel.onTermination = {
      case process => println("Completed: " + process.program.name + " at MC time: " + kernel.time)
    }

    // execute all programs setup in the simulation
    programs.foreach { case program =>
      kernel.exec(program);
    }

    println("Starting O/S Simulation")
    println

    while (kernel.getProcessesList().size > 0) {
      kernel.clock();

      // print out all processes
      if (java.lang.Math.floorMod(kernel.time, printEveryUnits) == 0) {
        println("=================================== " + Integer.toString(kernel.time)
          + " ===================================")

        // print all programs status
        kernel.getProcessesList().foreach {
          case proc =>
            println("Pid: " + proc.pid + " Name: " + proc.program.name + " State: " + proc.state)
        }

        println("=================================== " + Integer.toString(kernel.time)
          + " ===================================")
        println
      }
    }

    // done
    println
    println("Ended O/S Simulation. Last clock: " + kernel.time)
  }
}
