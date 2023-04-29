package edu.tltsu.medical_app.medical_app.services;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.employee.EmployeeInfoDTO;
import edu.tltsu.medical_app.medical_app.dto.employee.EmployeeManagerDTO;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.exceptions.EmployeeException;
import edu.tltsu.medical_app.medical_app.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(final EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Transactional
  public Employee saveEmployee(final EmployeeInfoDTO employeeInfoDTO) {

    if (this.employeeRepository.existsByAccountId(employeeInfoDTO.getAccountId())) {
      throw new EmployeeException(employeeInfoDTO.getAccountId());
    }

    final Employee employee = Employee
        .builder()
        .parentId(employeeInfoDTO.getParentId())
        .departmentId(employeeInfoDTO.getDepartmentId())
        .jobTitle(employeeInfoDTO.getJobTitle())
        .accountId(employeeInfoDTO.getAccountId())
        .name(employeeInfoDTO.getName())
        .surname(employeeInfoDTO.getSurname())
        .patronymic(employeeInfoDTO.getPatronymic())
        .build();
    this.employeeRepository.save(employee);

    return employee;
  }

  @Transactional
  public Employee disableEmployeeByEmployeeId(final Long employeeId) {
    final Employee employee = this.findEmployeeById(employeeId);

    employee.setIsActiveEmployee(false);
    this.employeeRepository.save(employee);
    log.debug("Employee with ID = {} has been disabled!", employeeId);

    return employee;
  }

  @Transactional
  public Employee enableEmployeeByEmployeeId(final Long employeeId) {
    final Employee employee = this.findEmployeeById(employeeId);

    employee.setIsActiveEmployee(true);
    this.employeeRepository.save(employee);
    log.debug("Employee with ID = {} has been activated!", employeeId);

    return employee;
  }

  @Transactional
  public Employee changeEmployee(final Long employeeId, final EmployeeInfoDTO employeeInfoDTO) {
    final Employee employee = this.findEmployeeById(employeeId);

    log.debug("Changing employee data. Initial state: {}", employee);

    employee.setName(employeeInfoDTO.getName());
    employee.setSurname(employeeInfoDTO.getSurname());
    employee.setPatronymic(employeeInfoDTO.getPatronymic());
    employee.setDepartmentId(employeeInfoDTO.getDepartmentId());
    employee.setJobTitle(employeeInfoDTO.getJobTitle());
    employee.setDepartmentId(employeeInfoDTO.getDepartmentId());
    this.employeeRepository.save(employee);

    log.debug("Employee: {} has been changed.", employee);

    return employee;
  }

  // admin/moderator operation
  @Transactional
  public Employee changeParent(final EmployeeManagerDTO employeeManagerDTO) {
    final Employee employee = this.findEmployeeById(employeeManagerDTO.getParentId());

    if (this.employeeRepository.existsByEmployeeId(employeeManagerDTO.getParentId())) {
      employee.setParentId(employeeManagerDTO.getParentId());
      this.employeeRepository.save(employee);
      log.debug("Parent for employee with ID = {} has been changed to {}", employeeManagerDTO.getEmployeeId(), employeeManagerDTO.getParentId());
    } else {
      log.error("Failed to change parentId = {} for employee = {}!", employeeManagerDTO.getParentId(), employeeManagerDTO.getEmployeeId());
      throw new EmployeeException(employeeManagerDTO.getParentId());
    }
    return employee;
  }

  public Employee findEmployeeById(final Long employeeId) {
    return this.employeeRepository
        .findById(employeeId)
        .orElseThrow(() -> new EmployeeException(employeeId));
  }

  // moderator/parent operation
  public List<Employee> getEmployeesByParent(final Long parentId) {
    return this.employeeRepository.findEmployeesByParentId(parentId);
  }

  // admin/moderator
  public List<Employee> findEmployeesByDepartmentId(final Long parentId) {
    return this.employeeRepository.findEmployeesByDepartmentId(parentId);
  }

  // admin
  public List<Employee> getAllEmployees() {
    return this.employeeRepository.findAll();
  }

  public Employee getEmployeeByToken(final String token) {
    // todo: (Security 2) security issue
    return new Employee();
  }
}
