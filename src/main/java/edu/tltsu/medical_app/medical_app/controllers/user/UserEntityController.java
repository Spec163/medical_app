package edu.tltsu.medical_app.medical_app.controllers.user;

import java.util.List;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.services.UserEntityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/public/users")
public class UserEntityController {

  private final UserEntityService userEntityService;

  public UserEntityController(final UserEntityService userEntityService) {
    this.userEntityService = userEntityService;
  }


  @GetMapping("/info")
  public UserEntity getInfo() {
    // getInfo by Token
    return this.userEntityService.getUserByToken("token");
  }

  @GetMapping("/{parentId}")
  public List<UserEntity> getUsersByParent(@PathVariable final Long parentId) {
    return this.userEntityService.getUsersByParent(parentId);
  }

}
