package cz.kamenitxan.df3d

import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.df3d.pages.{IndexPage, ModelController}
import cz.kamenitxan.jakon.JakonInit
import cz.kamenitxan.jakon.core.Director
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import org.slf4j.LoggerFactory

class DFS3DApp extends JakonInit{
	private val logger = LoggerFactory.getLogger(this.getClass)

	daoSetup = () => {
		DBHelper.addDao(classOf[Product])
	}



	Director.registerCustomPage(new IndexPage)
	Director.registerControler(new ModelController)

	override def adminControllers(): Unit = {
		super.adminControllers()
	}


}
