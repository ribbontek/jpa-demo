package com.ribbontek.jpademo

import com.ribbontek.jpademo.context.AbstractIntegTest
import com.ribbontek.jpademo.generator.ErdGenerator
import com.ribbontek.jpademo.generator.ErdGeneratorImpl.Companion.ERD_FILE_NAME
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File

class ErdGeneratorTest : AbstractIntegTest() {

    @Autowired
    private lateinit var erdGenerator: ErdGenerator

    @Test
    fun `generates the erd diagram`() {
        erdGenerator.generate()
        assertTrue(File(ERD_FILE_NAME).exists()) { "Expected $ERD_FILE_NAME to exist" }
    }
}
