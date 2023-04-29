package edu.tltsu.medical_app.medical_app.controllers.employee;

import java.util.List;
import edu.tltsu.medical_app.medical_app.dto.employee.EmployeeInfoDTO;
import edu.tltsu.medical_app.medical_app.dto.employee.EmployeeManagerDTO;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin/employees")
public class EmployeeAdminController {

  private final EmployeeService employeeService;

  public EmployeeAdminController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(value = "/all")
  public List<Employee> getAllEmployees() {
    return this.employeeService.getAllEmployees();
  }

  @GetMapping(value = "/id/{id}")
  public Employee getEmployeeById(@PathVariable("id") final Long id) {
    return this.employeeService.findEmployeeById(id);
  }

  @GetMapping(value = "/departments/{departmentId}")
  public List<Employee> getEmployeesListByDepartmentId(@PathVariable("departmentId") final Long departmentId) {
    return this.employeeService.findEmployeesByDepartmentId(departmentId);
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee createEmployee(@Valid @RequestBody final EmployeeInfoDTO employeeInfoDTO) {
    return this.employeeService.saveEmployee(employeeInfoDTO);
  }

  @PutMapping(value = "change/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee changeEmployeeInfo(@PathVariable("id") final Long id, @Valid @RequestBody final EmployeeInfoDTO employeeInfoDTO) {
    return this.employeeService.changeEmployee(id, employeeInfoDTO);
  }

  @PostMapping(value = "/change-manager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee changeEmployeeInfo(@Valid @RequestBody final EmployeeManagerDTO employeeManagerDTO) {
    return this.employeeService.changeParent(employeeManagerDTO);
  }

  @PutMapping(value = "/enable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee enableEmployeeById(@PathVariable("id") final Long id) {
    return this.employeeService.enableEmployeeByEmployeeId(id);
  }

  @PutMapping(value = "/disable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee disableEmployeeById(@PathVariable("id") final Long id) {
    return this.employeeService.disableEmployeeByEmployeeId(id);
  }
}
