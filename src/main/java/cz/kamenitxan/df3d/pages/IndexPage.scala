package cz.kamenitxan.df3d.pages

import java.util

import cz.kamenitxan.df3d.model.ProductService
import cz.kamenitxan.jakon.core.configuration.Settings
import cz.kamenitxan.jakon.core.customPages.AbstractCustomPage
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.template.TemplateUtils
import cz.kamenitxan.jakon.webui.ObjectSettings

import scala.collection.JavaConverters._

class IndexPage extends AbstractCustomPage {

	override protected def generate(): Unit = {
		val engine = TemplateUtils.getEngine

		implicit val conn = DBHelper.getConnection
		val products = ProductService.getAll
		conn.close()

		Settings.setDefaultLocale("cs_CZ")
		val model = new util.HashMap[String, AnyRef]()
		model.put("products", products.asJava)
		model.put("lang", "cs")
		engine.render("index", "index", model)

		Settings.setDefaultLocale("la_VA")
		val latinModel = new util.HashMap[String, AnyRef]()
		latinModel.put("products", products.asJava)
		latinModel.put("lang", "la")
		engine.render("index", "latin", latinModel)
	}

	override val objectSettings: ObjectSettings = null
}
