package cz.kamenitxan.df3d.model

import java.sql.Connection

import cz.kamenitxan.jakon.core.model.Dao.DBHelper

object ProductService {

	def getAll(implicit conn: Connection): List[Product] = {
		val stmt = conn.prepareStatement("SELECT * FROM Product")
		val res = DBHelper.select(stmt, classOf[Product])
		res.map(r => r.entity.asInstanceOf[Product])
	}
}
