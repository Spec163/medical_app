package edu.tltsu.medical_app.medical_app.repositories;

import java.util.Collection;
import java.util.List;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findEmployeesByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

  List<Employee> findEmployeesByParentId(Long parentId);

  List<Employee> findEmployeesByDepartmentId(Long departmentId);

  Boolean existsByEmployeeId(Long employeeId);

  Boolean existsByAccountId(Long accountId);

  Employee findEmployeeByAccountId(Long accountId);

  List<Employee> findByEmployeeIdNotIn(Collection<Long> scheduledEmployee);

}
