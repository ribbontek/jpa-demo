package com.ribbontek.jpademo.config

import org.hibernate.boot.model.FunctionContributions
import org.hibernate.boot.model.FunctionContributor
import org.hibernate.dialect.function.StandardSQLFunction
import org.hibernate.type.StandardBasicTypes

class PostgresFunctionContributor : FunctionContributor {

    override fun contributeFunctions(functionContributions: FunctionContributions) {
        functionContributions.functionRegistry.register(
            "hasRole",
            StandardSQLFunction(
                "jpademo.has_role", StandardBasicTypes.BOOLEAN
            )
        )
    }
}
