package cz.kamenitxan.df3d.pages

import java.util

import cz.kamenitxan.df3d.model.ProductService
import cz.kamenitxan.jakon.core.controler.IControler
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.template.{TemplateEngine, TemplateUtils}

class ModelController extends IControler {
	val template = "model"

	override protected def generate(): Unit = {
		val e: TemplateEngine = TemplateUtils.getEngine
		implicit val conn = DBHelper.getConnection
		val products = ProductService.getAll
		conn.close()

		products.foreach(m => {
			val context = new util.HashMap[String, AnyRef]()
			context.put("p", m)
			val path = "view/" + m.url
			e.render(template, path, context)
		})
	}
}
