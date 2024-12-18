package com.example.rest.controller;

import com.example.rest.exception.EmployersNotFoundException;
import com.example.rest.form.EmployersForm;
import com.example.rest.model.EmployersModel;
import com.example.rest.service.EmployersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/employers")
@RequiredArgsConstructor
public class WebController {

    private final EmployersService employersService;

    @GetMapping
    public String getAllEmployers(Model model) {

        try {
            List<EmployersModel> employers = employersService.getAll();
            model.addAttribute("employers", employers);

        } catch (EmployersNotFoundException e) {
            model.addAttribute("error", "No employers found.");
        }

        return "employers";
    }

    @GetMapping("/{id}")
    public String getEmployerById(@PathVariable Long id, Model model) {

        try {
            EmployersModel employer = employersService.findById(id);
            model.addAttribute("searchedEmployer", employer);

        } catch (EmployersNotFoundException e) {
            model.addAttribute("error", "Employer not found.");
        }

        return "employers";
    }

    @PostMapping
    public String addEmployer(@Valid @ModelAttribute EmployersForm employersForm, Model model) {

        EmployersModel employersModel = new EmployersModel();
        employersModel.setFirstName(employersForm.getFirstName());
        employersModel.setLastName(employersForm.getLastName());
        employersModel.setAge(employersForm.getAge());
        employersModel.setPosition(employersForm.getPosition());
        employersModel.setSalary(employersForm.getSalary());
        employersService.add(employersModel);

        return "redirect:/employers";
    }

    @PutMapping("/{id}")
    public String updateEmployer(@PathVariable Long id, @Valid @ModelAttribute EmployersForm employersForm, Model model) {

        try {
            EmployersModel employersModel = new EmployersModel();
            employersModel.setFirstName(employersForm.getFirstName());
            employersModel.setLastName(employersForm.getLastName());
            employersModel.setAge(employersForm.getAge());
            employersModel.setPosition(employersForm.getPosition());
            employersModel.setSalary(employersForm.getSalary());
            employersService.replace(id, employersModel);

        } catch (EmployersNotFoundException e) {
            model.addAttribute("error", "Employer not found.");
        }

        return "redirect:/employers";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployer(@PathVariable Long id, Model model) {

        try {
            employersService.delete(id);
            model.addAttribute("message", "Employer deleted successfully.");

        } catch (EmployersNotFoundException e) {
            model.addAttribute("error", "Employer not found.");
        }
        return "redirect:/employers";
    }
}
