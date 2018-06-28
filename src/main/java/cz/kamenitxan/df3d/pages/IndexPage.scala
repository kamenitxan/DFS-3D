package cz.kamenitxan.df3d.pages

import java.util

import cz.kamenitxan.jakon.core.customPages.CustomPage
import cz.kamenitxan.jakon.webui.ObjectSettings
import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.template.TemplateUtils

class IndexPage extends CustomPage {
	override protected def generate(): Unit = {
		val engine = TemplateUtils.getEngine

		val session = DBHelper.getSession
		session.beginTransaction()
		val products = session.createCriteria(classOf[Product]).list()
		session.getTransaction.commit()

		val model = new util.HashMap[String, AnyRef]()
		model.put("products", products)
		model.put("lang", "cs")
		engine.render("index", "index", model)

		val latinModel = new util.HashMap[String, AnyRef]()
		latinModel.put("products", products)
		latinModel.put("lang", "la")
		engine.render("index", "latin", latinModel)
	}

	override val objectSettings: ObjectSettings = null
}
