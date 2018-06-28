package cz.kamenitxan.df3d

import java.io.IOException

import cz.kamenitxan.jakon.JakonInit
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.df3d.pages.{IndexPage, ModelController}
import cz.kamenitxan.jakon.core.Director
import cz.kamenitxan.jakon.core.configuration.{DeployMode, Settings}
import cz.kamenitxan.jakon.devtools.DevRender
import cz.kamenitxan.jakon.utils.PageContext
import org.slf4j.LoggerFactory
import spark.{Request, Response}
import spark.Spark.{afterAfter, before, port, staticFiles}
import spark.debug.DebugScreen.enableDebugScreen

class DFS3DApp extends JakonInit{
	private val logger = LoggerFactory.getLogger(this.getClass)

	DBHelper.addDao(classOf[Product])

	Director.registerCustomPage(new IndexPage)
	Director.registerControler(new ModelController)

	override def adminControllers(): Unit = {
		super.adminControllers()
	}

	override def run(): Unit = {
		staticFiles.externalLocation(Settings.getOutputDir)
		port(Settings.getPort)

		try
			Settings.init(null)
		catch {
			case e: IOException => logger.error("Jdufanit: cant load jakon settings", e)
		}
		logger.info("Starting in " + Settings.getDeployMode + " mode")

		daoSetup()
		adminControllers()
		Director.start()

		afterAfter((_: Request, _: Response) => PageContext.destroy())
		if (Settings.getDeployMode == DeployMode.DEVEL) {
			before((request: Request, _: Response) => {
				DevRender.rerender(request.pathInfo())
			}
			)
			enableDebugScreen()
		}
		routesSetup()
		taskSetup()
	}
}
