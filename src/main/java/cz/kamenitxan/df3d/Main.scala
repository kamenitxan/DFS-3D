package cz.kamenitxan.df3d

import org.slf4j.LoggerFactory

object Main {
	private val logger = LoggerFactory.getLogger(this.getClass)

	def main(args: Array[String]) {
		val app =new DFS3DApp()
		app.run(args)
	}
}
