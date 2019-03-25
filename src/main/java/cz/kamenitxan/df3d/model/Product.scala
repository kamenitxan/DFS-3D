package cz.kamenitxan.df3d.model

import java.sql.Connection

import cz.kamenitxan.jakon.core.model.{JakonObject, Ordered}
import cz.kamenitxan.jakon.webui.ObjectSettings
import cz.kamenitxan.jakon.webui.entity.JakonField
import javax.persistence.Transient

import scala.language.postfixOps

class Product(u: Unit = ()) extends JakonObject(classOf[Product].getName) with Ordered {
	@JakonField
	var name: String = ""
	@JakonField
	var price: Int = 0
	@JakonField
	var description: String = ""
	@JakonField
	var withModel: Boolean = false
	 @JakonField(listOrder = -96, shownInEdit = false, shownInList = false)
	override var objectOrder: Double = _
	@JakonField(listOrder = -96)
	override var visibleOrder: Int = _

	def this() = this(u=())

	override def getObjectOrder: Double = objectOrder

	override def setObjectOrder(objectOrder: Double): Unit = this.objectOrder = objectOrder

	override def getUrl: String = id toString

	@Transient
	override val objectSettings = new ObjectSettings("fa-file-text-o")

	override def createObject(jid: Int, conn: Connection): Int = {
		val stmt = conn.prepareStatement("INSERT INTO Product (name, price, description, withModel, objectOrder) VALUES (?, ?, ?, ?, ?)")
		stmt.setString(1, name)
		stmt.setInt(2, price)
		stmt.setString(3, description)
		stmt.setBoolean(4, withModel)
		stmt.setDouble(5, objectOrder)
		executeInsert(stmt)
	}

	override def updateObject(jid: Int, conn: Connection): Unit = {
		throw new UnsupportedOperationException
	}
}
