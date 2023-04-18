package edu.tltsu.medical_app.medical_app.controllers.user;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.user.UserInfoDTO;
import edu.tltsu.medical_app.medical_app.dto.user.UserManagerDTO;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserEntityAdminController {

  private final UserEntityService userEntityService;

  public UserEntityAdminController(final UserEntityService userEntityService) {
    this.userEntityService = userEntityService;
  }

  @GetMapping(value = "/all")
  public List<UserEntity> getAllUsers() {
    return this.userEntityService.getAllUsers();
  }

  @GetMapping(value = "/id/{id}")
  public UserEntity getUserById(@PathVariable("id") final Long id) {
    return this.userEntityService.findUserEntityById(id);
  }

  @GetMapping(value = "/departments/{departmentId}")
  public List<UserEntity> getUsersListByDepartmentId(@PathVariable("departmentId") final Long departmentId) {
    return this.userEntityService.findUserEntitiesByDepartmentId(departmentId);
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserEntity createUser(@Valid @RequestBody final UserInfoDTO userInfoDTO) {
    return this.userEntityService.saveUser(userInfoDTO);
  }

  @PutMapping(value = "change/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserEntity changeUserInfo(@PathVariable("id") final Long id, @Valid @RequestBody final UserInfoDTO userInfoDTO) {
    return this.userEntityService.changeUser(id, userInfoDTO);
  }

  @PostMapping(value = "/change-manager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserEntity changeUserInfo(@Valid @RequestBody final UserManagerDTO userManagerDTO) {
    return this.userEntityService.changeParent(userManagerDTO);
  }

  @PutMapping(value = "/enable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserEntity enableUserById(@PathVariable("id") final Long id) {
    return this.userEntityService.enableUserByUserId(id);
  }

  @PutMapping(value = "/disable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserEntity disableUserById(@PathVariable("id") final Long id) {
    return this.userEntityService.disableUserByUserId(id);
  }
}
