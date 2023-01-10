package com.greatlearning.Employeemgmt.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.Employeemgmt.model.Employee;
import com.greatlearning.Employeemgmt.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;

	/*
	 * Request Mapping /employees -Get /employees/new /employees/edit/{id}
	 * /employees/delete/{id} /employees -post /employees/{id} -post
	 */
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		List<Employee> result = service.getAllEmployees();
		model.addAttribute("employees", result);
		return "employees";
	}

	@GetMapping("/employees/new")
	public String addNewEmployee(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "create_employee";
	}

	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(Model model, @PathVariable("id") Integer id) {
		Employee emp = service.getEmployeeById(id);
		model.addAttribute("employee", emp);
		return "edit_employee";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		service.deleteEmployeeById(id);
		return "redirect:/employees";
	}

	@PostMapping("/employees")
	public String addEmployee(@ModelAttribute(name = "employee") Employee emp) {
		service.saveOrUpdate(emp);
		return "redirect:employees";
	}

	@PostMapping("/employees/{id}")
	public String updateEmployee(@ModelAttribute(name = "employee") Employee emp, @PathVariable("id") Integer id) {
		Employee exsistingEmp = service.getEmployeeById(id);
		if (exsistingEmp.getId() == emp.getId()) {
			exsistingEmp.setFirstName(emp.getFirstName());
			exsistingEmp.setLastName(emp.getLastName());
			exsistingEmp.setEmail(emp.getEmail());
		}
		service.saveOrUpdate(exsistingEmp);
		return "redirect:/employees";
	}

}
