package cz.kamenitxan.df3d

object Main {
	var port = 4567

	@JvmStatic
	fun main(args: Array<String>) {
		println("Chatlin Started")
		for (arg in args) {
			when {
				arg.contains("-port") -> port = arg.replace("-port=", "").toInt()
			}
		}

		val app = ChatlinApp().run()
	}
}