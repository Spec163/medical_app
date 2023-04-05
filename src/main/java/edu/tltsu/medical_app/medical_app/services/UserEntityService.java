package edu.tltsu.medical_app.medical_app.services;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.user.UserInfoDTO;
import edu.tltsu.medical_app.medical_app.dto.user.UserManagerDTO;
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
  public UserEntity saveUser(final UserInfoDTO userInfoDTO) {

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

    return userEntity;
  }

  @Transactional
  public UserEntity disableUserByUserId(final Long userId) {
    final UserEntity userEntity = this.findUserEntityById(userId);

    userEntity.setIsActiveUser(false);
    this.userEntityRepository.save(userEntity);
    log.debug("User with ID = {} has been disabled!", userId);

    return userEntity;
  }

  @Transactional
  public UserEntity enableUserByUserId(final Long userId) {
    final UserEntity userEntity = this.findUserEntityById(userId);

    userEntity.setIsActiveUser(true);
    this.userEntityRepository.save(userEntity);
    log.debug("User with ID = {} has been activated!", userId);

    return userEntity;
  }

  @Transactional
  public UserEntity changeUser(final Long userId, final UserInfoDTO userInfoDTO) {
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

    return userEntity;
  }

  // admin/moderator operation
  @Transactional
  public UserEntity changeParent(final UserManagerDTO userManagerDTO) {
    final UserEntity userEntity = this.findUserEntityById(userManagerDTO.getParentId());

    if (this.userEntityRepository.existsByUserId(userManagerDTO.getParentId())) {
      userEntity.setParentId(userManagerDTO.getParentId());
      this.userEntityRepository.save(userEntity);
      log.debug("Parent for user with ID = {} has been changed to {}", userManagerDTO.getUserId(), userManagerDTO.getParentId());
    } else {
      log.error("Failed to change parentId = {} for user = {}!", userManagerDTO.getParentId(), userManagerDTO.getUserId());
      throw new UserEntityException(userManagerDTO.getParentId());
    }
    return userEntity;
  }

  public UserEntity findUserEntityById(final Long userId) {
    return this.userEntityRepository
        .findById(userId)
        .orElseThrow(() -> new UserEntityException(userId));
  }

  // admin/moderator
  public UserEntity findUserEntityByUsername(final String username) {
    return this.userEntityRepository
        .findUserEntityByUsername(username)
        .orElseThrow(() -> new UserEntityException(username));
  }

  // moderator/parent operation
  public List<UserEntity> getUsersByParent(final Long parentId) {
    return this.userEntityRepository.findUserEntitiesByParentId(parentId);
  }

  // admin/moderator
  public List<UserEntity> findUserEntitiesByDepartmentId(final Long parentId) {
    return this.userEntityRepository.findUserEntitiesByDepartmentId(parentId);
  }

  // admin
  public List<UserEntity> getAllUsers() {
    return this.userEntityRepository.findAll();
  }

  public UserEntity getUserByToken(final String token) {
    // todo: (Security 2) security issue
    return new UserEntity();
  }
}
