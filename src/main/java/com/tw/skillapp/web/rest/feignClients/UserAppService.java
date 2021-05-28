package com.tw.skillapp.web.rest.feignClients;

import com.tw.skillapp.client.AuthorizedFeignClient;
import com.tw.skillapp.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@AuthorizedFeignClient(name = "userapp")
@RequestMapping(value = "/api")
public interface UserAppService {


    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getUserByLogin(@RequestParam("login") String login );

    @RequestMapping(value = "/users/id/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getUserById(@RequestParam("id") String id );

}
