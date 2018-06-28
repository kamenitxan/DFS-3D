package cz.kamenitxan.df3d

import org.slf4j.LoggerFactory

object Main {
	private val logger = LoggerFactory.getLogger(this.getClass)

	def main(args: Array[String]) {
		val arguments = args.toList.map(a => {
			val split = a.split("=")
			split.length match {
				case 1 => split(0) -> None
				case 2 => split(0) -> split(1)
			}
		})

		val app =new DFS3DApp()
		app.run()
	}
}
