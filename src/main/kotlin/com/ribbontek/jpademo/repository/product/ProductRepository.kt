package com.ribbontek.jpademo.repository.product

import com.ribbontek.jpademo.repository.abstracts.AdminEntityDeleteRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : AdminEntityDeleteRepository<ProductEntity>
