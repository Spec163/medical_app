package edu.tltsu.medical_app.medical_app.controllers.employee;

import java.util.List;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.services.EmployeeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/public/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/info")
  public Employee getInfo() {
    // getInfo by Token
    return this.employeeService.getEmployeeByToken("token");
  }

  @GetMapping("/{parentId}")
  public List<Employee> getEmployeesByParent(@PathVariable final Long parentId) {
    return this.employeeService.getEmployeesByParent(parentId);
  }
}
