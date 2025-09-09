package cham.splearn.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class EmailTest : FunSpec({

    test("equality") {
        val email1 = Email.of("cham@splearn.app")
        val email2 = Email.of("cham@splearn.app")

        email1 shouldBe email2
    }

})