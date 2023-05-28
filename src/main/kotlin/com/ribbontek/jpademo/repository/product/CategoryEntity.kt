package com.ribbontek.jpademo.repository.product

import com.ribbontek.jpademo.repository.abstracts.AbstractEntityCodeDescription
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

enum class CategoryEnum {
    CLOTHING,
    FOOTWEAR,
    COMPUTERS,
    COMPUTERS_SOFTWARE,
    COMPUTERS_NETWORKING
}

@Entity
@Table(name = "category")
@AttributeOverride(name = "id", column = Column(name = "category_id"))
class CategoryEntity() : AbstractEntityCodeDescription() {
    constructor(categoryEnum: CategoryEnum) : this() {
        code = categoryEnum.name
    }
}
