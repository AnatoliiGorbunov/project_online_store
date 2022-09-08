package ru.geekbrains.project_online_store.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.project_online_store.services.UserService;
import ru.geekbrains.project_online_store.soap.soap.GetAllUsersRequest;
import ru.geekbrains.project_online_store.soap.soap.GetAllUsersResponse;
import ru.geekbrains.project_online_store.soap.soap.User;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://www.mvg.com/spring/ws/users";
    private final UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {//аналог реквестбоди
        GetAllUsersResponse response = new GetAllUsersResponse();
        userService.findAllUsers().forEach(u -> {
            User user = new User();
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setEmail(u.getEmail());
            response.getUsers().add(user);
        });
        return response;
    }
}
