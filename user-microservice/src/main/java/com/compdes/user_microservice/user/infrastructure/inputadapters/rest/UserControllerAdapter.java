package com.compdes.user_microservice.user.infrastructure.inputadapters.rest;

import com.compdes.user_microservice.common.infrastructure.annotations.WebAdapter;
import com.compdes.user_microservice.user.application.inputports.CreatingUserInputPort;
import com.compdes.user_microservice.user.application.inputports.FindingUserInputPort;
import com.compdes.user_microservice.user.application.usecases.createuser.CreateUserDto;
import com.compdes.user_microservice.user.domain.User;
import com.compdes.user_microservice.user.infrastructure.inputadapters.rest.dto.UserRequestDto;
import com.compdes.user_microservice.user.infrastructure.inputadapters.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users", description = "Operaciones relacionadas a los usuarios")
@RestController
@RequestMapping("/v1/users")
@WebAdapter
public class UserControllerAdapter {

    private final CreatingUserInputPort creatingUserInputPort;
    private final FindingUserInputPort findingUserInputPort;

    public UserControllerAdapter(CreatingUserInputPort creatingUserInputPort, FindingUserInputPort findingUserInputPort) {
        this.creatingUserInputPort = creatingUserInputPort;
        this.findingUserInputPort = findingUserInputPort;
    }

    @Operation(
            summary = "Registrar nuevo usuario",
            description = "Devuelve información relacionada con el usuario creado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado"),
            @ApiResponse(responseCode = "404", description = "Usuario no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDto> create(@Valid UserRequestDto userRequestDto) {
        CreateUserDto createUserDto = userRequestDto.toDomain();
        User user = this.creatingUserInputPort.save(createUserDto);
        UserResponseDto userResponseDto = UserResponseDto.fromDomain(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @Operation(
            summary = "Obtener información de usuario",
            description = "Devuelve información relacionada con el usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable String id) {
        User user = this.findingUserInputPort.findUser(id);
        UserResponseDto userResponseDto = UserResponseDto.fromDomain(user);
        return ResponseEntity.ok(userResponseDto);
    }

}
