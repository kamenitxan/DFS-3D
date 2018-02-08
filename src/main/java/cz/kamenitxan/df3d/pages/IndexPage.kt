package cz.kamenitxan.df3d.pages

import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.jakon.core.customPages.CustomPage
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.template.TemplateUtils
import cz.kamenitxan.jakon.webui.ObjectSettings
import java.util.HashMap

class IndexPage : CustomPage() {

	override fun render() {
		val engine = TemplateUtils.getEngine()
		val products = DBHelper.getSession().createCriteria(Product::class.java).list()
		val model = HashMap<String, Any>()
		model.put("products", products)
		engine.render("index", "index", model)
	}

	override fun objectSettings(): ObjectSettings? {
		return null
	}

}