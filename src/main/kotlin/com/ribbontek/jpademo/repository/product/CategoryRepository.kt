package com.ribbontek.jpademo.repository.product

import com.ribbontek.jpademo.repository.abstracts.EntityCodeDescriptionRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : EntityCodeDescriptionRepository<CategoryEntity>
