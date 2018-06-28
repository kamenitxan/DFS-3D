package cz.kamenitxan.df3d.pages

import java.util

import cz.kamenitxan.df3d.model.Product
import cz.kamenitxan.jakon.core.controler.IControler
import cz.kamenitxan.jakon.core.model.Dao.DBHelper
import cz.kamenitxan.jakon.core.template.{TemplateEngine, TemplateUtils}

class ModelController extends IControler {
	val template = "model"

	override protected def generate(): Unit = {
		val e: TemplateEngine = TemplateUtils.getEngine
		val session = DBHelper.getSession
		session.beginTransaction()
		val models: util.List[Product] = session.createCriteria(classOf[Product]).list().asInstanceOf[util.List[Product]]
		session.getTransaction.commit()
		models.forEach(m => {
			val context = new util.HashMap[String, AnyRef]()
			context.put("p", m)
			val path = "view/" + m.getUrl
			e.render(template, path, context)
		})
	}
}