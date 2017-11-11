package com.journaler.api.security

import com.journaler.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var service: UserService

    val encoder = BCryptPasswordEncoder(11)

    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUsers() = service.getUsers()

    @PutMapping(
            value = "/admin",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertAdmin(
            @RequestBody user: Admin
    ): User {
        user.pwd = encoder.encode(user.password)
        return service.save(user)
    }

    @PutMapping(
            value = "/member",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertMember(
            @RequestBody user: Member
    ): User {
        user.pwd = encoder.encode(user.password)
        return service.save(user)
    }

}