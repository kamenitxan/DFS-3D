package cz.kamenitxan.df3d

import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.df3d.pages.IndexPage
import cz.kamenitxan.jakon.JakonApp
import cz.kamenitxan.jakon.core.Director
import cz.kamenitxan.jakon.core.configuration.Settings
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.model.DeployMode
import spark.Spark

class ChatlinApp : JakonApp() {
	override fun daoSetup() {
		DBHelper.addDao(Product::class.java)

		Director.registerCustomPage(IndexPage())
	}

	override fun routesSetup() {
		Spark.before("/chatlin/*") { request, response ->
			if (Settings.getDeployMode() == DeployMode.DEVEL) {
				return@before
			}
			if (request.session().attribute<Any>("user") == null) {
				response.redirect("/", 302)
			}
		}


	}

}