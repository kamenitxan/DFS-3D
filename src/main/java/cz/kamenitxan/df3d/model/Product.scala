package cz.kamenitxan.df3d.model

import cz.kamenitxan.jakon.core.model.{JakonObject, Ordered}
import cz.kamenitxan.jakon.webui.ObjectSettings
import cz.kamenitxan.jakon.webui.entity.JakonField
import javax.persistence.{Column, Entity, Transient}

import scala.beans.BeanProperty
import scala.language.postfixOps

@Entity
class Product(u: Unit = ()) extends JakonObject(classOf[Product].getName) with Ordered {
	@BeanProperty @Column @JakonField
	var name: String = ""
	@BeanProperty @Column @JakonField
	var price: Int = 0
	@BeanProperty @Column @JakonField
	var description: String = ""
	@BeanProperty @Column @JakonField
	var withModel: Boolean = false
	@Column(nullable = false) @JakonField(listOrder = -96, shownInEdit = false, shownInList = false)
	override var objectOrder: Double = _
	@Transient @JakonField(listOrder = -96)
	override var visibleOrder: Int = _

	def this() = this(u=())

	override def getObjectOrder: Double = objectOrder

	override def setObjectOrder(objectOrder: Double): Unit = this.objectOrder = objectOrder

	override def getUrl: String = id toString

	@Transient
	override val objectSettings = new ObjectSettings("fa-file-text-o")
}
