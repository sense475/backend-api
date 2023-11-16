package exchange.v2.backendapi

import org.springframework.web.bind.annotation.*

data class People(
        val name: String,
        val age: Int
)

@RestController
@RequestMapping("/api")
class PeopleController {

    @GetMapping("/healthcheck")
    fun healthCheck(): String {
        // You can add more complex health check logic here if needed
        return "OK"
    }

    @GetMapping("/people")
    fun hello(): List<People> =
            listOf(
                    People(name = "sense", age = 22),
                    People(name = "tanodom", age = 25)
            )

    @PostMapping("/product")
    fun createProduct(@RequestBody product: People): People {
        // Logic to save the product to the database or perform other actions
        // For simplicity, let's just return the received product for now
        return product
    }
}
