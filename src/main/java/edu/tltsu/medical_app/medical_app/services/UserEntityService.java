package edu.tltsu.medical_app.medical_app.services;

import edu.tltsu.medical_app.medical_app.dto.UserInfoDTO;
import edu.tltsu.medical_app.medical_app.dto.UserManagerDTO;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.exceptions.UserEntityException;
import edu.tltsu.medical_app.medical_app.repositories.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserEntityService {

  private final UserEntityRepository userEntityRepository;

  @Autowired
  public UserEntityService(final UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Transactional
  public UserInfoDTO saveUser(final UserInfoDTO userInfoDTO) {

    if (this.userEntityRepository.existsByUsername(userInfoDTO.getUsername())) {
      throw new UserEntityException(userInfoDTO.getUsername());
    }

    final UserEntity userEntity = UserEntity
        .builder()
        .parentId(userInfoDTO.getParentId())
        .departmentId(userInfoDTO.getDepartmentId())
        .jobTitle(userInfoDTO.getJobTitle())
        .username(userInfoDTO.getUsername())
        .name(userInfoDTO.getName())
        .surname(userInfoDTO.getSurname())
        .patronymic(userInfoDTO.getPatronymic())
        .build();
    this.userEntityRepository.save(userEntity);

    return userInfoDTO;
  }

  @Transactional
  public void disableUserByUserId(final Long userId) {
    final UserEntity userEntity = this.findUserEntityById(userId);

    userEntity.setIsActiveUser(false);
    this.userEntityRepository.save(userEntity);
    log.debug("User with ID = {} has been disabled!", userId);
  }

  @Transactional
  public UserInfoDTO changeUser(final Long userId, final UserInfoDTO userInfoDTO) {
    final UserEntity userEntity = this.findUserEntityById(userId);

    log.debug("Changing user data. Initial state: {}", userEntity);

    userEntity.setName(userInfoDTO.getName());
    userEntity.setSurname(userInfoDTO.getSurname());
    userEntity.setPatronymic(userInfoDTO.getPatronymic());
    userEntity.setDepartmentId(userInfoDTO.getDepartmentId());
    userEntity.setJobTitle(userInfoDTO.getJobTitle());
    userEntity.setDepartmentId(userInfoDTO.getDepartmentId());
    this.userEntityRepository.save(userEntity);

    log.debug("User: {} has been changed.", userEntity);

    return userInfoDTO;
  }

  // admin/moderator operation
  @Transactional
  public UserManagerDTO changeParent(final UserManagerDTO userManagerDTO) {
    final UserEntity userEntity = this.findUserEntityById(userManagerDTO.getParentId());

    if (this.userEntityRepository.existsByUserId(userManagerDTO.getParentId())) {
      userEntity.setParentId(userManagerDTO.getParentId());
      this.userEntityRepository.save(userEntity);
      log.debug("Parent for user with ID = {} has been changed to {}", userManagerDTO.getUserId(), userManagerDTO.getParentId());
    } else {
      log.error("Failed to change parentId = {} for user = {}!", userManagerDTO.getParentId(), userManagerDTO.getUserId());
      throw new UserEntityException(userManagerDTO.getParentId());
    }
    return userManagerDTO;
  }

  private UserEntity findUserEntityById(final Long userId) {
    return this.userEntityRepository
        .findById(userId)
        .orElseThrow(() -> new UserEntityException(userId));
  }

  // add Paging Implementation for GET
}
