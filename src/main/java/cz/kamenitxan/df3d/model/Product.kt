package cz.kamenitxan.df3d.model

import cz.kamenitxan.jakon.core.model.JakonObject
import cz.kamenitxan.jakon.webui.ObjectSettings
import cz.kamenitxan.jakon.webui.entity.JakonField
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Product(
		@Column
		@JakonField
		var name: String?,
		@Column
		@JakonField
		var price: Int?,
		@Column
		@JakonField
		var description: String?
) : JakonObject(Product::class.java.name) {

	constructor() : this("", 0, "")

	override fun objectSettings(): ObjectSettings {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}