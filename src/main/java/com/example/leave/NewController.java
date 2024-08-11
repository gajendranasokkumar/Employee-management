package com.example.leave;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller // used for rendering UI
//@RequestMapping("api/login")
public class NewController {

    private final NewService newService;

    public NewController(NewService newService) {
        this.newService = newService;
    }


    //pre-processing - retrive data from DB
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String checkUser(@ModelAttribute("newLogin") Login newLogin, Model model, Model latestForm)
    {
        String name = newService.checkUser(newLogin);
        if(!name.equals("Unknown"))
        {
            Employee oneEmp = newService.getEmployeeByEmail(newLogin.getEmail());
            model.addAttribute("oneEmpModel",oneEmp);

            Leave latestLeaveForm = newService.getLatestLeaveForm(oneEmp.getId());
            latestForm.addAttribute("latestForm" , latestLeaveForm);
            return "employee";
        }
        else
        {
            return "redirect:/";
        }
    }

    @GetMapping("/manager")
    public String showManagerPage(Model allLeaveModel , Model allEmpNames, Model pastELaveModelManager)
    {
        List<Leave> allLeave = newService.getAllLeave();
        allLeaveModel.addAttribute("allLeaveModel" , allLeave);

        List<Employee> allEmpNamesList = newService.getAllEmployeeList();
        allEmpNames.addAttribute("allEmpNames", allEmpNamesList);

        List<Leave> allPastForManager = newService.getAllPastForManager();
        pastELaveModelManager.addAttribute("allPastForManager",allPastForManager );

        return "manager";
    }

    @PostMapping("viewform/{id}")
    public String getOneLeaveForm(@PathVariable("id") Integer id,Model oneForm)
    {
        Leave oneLeave = newService.getOneLeaveForm(id);
        System.out.println(oneLeave);
        oneForm.addAttribute("oneForm",oneLeave);
        return "viewForm";
    }

    @GetMapping("/manager/addEmployee")
    public String addEmp(Model model)
    {
        return  "addEmployee";
    }



    @GetMapping("/manager/removeemployee")
    public String getAllEmployees(Model model) {
        List<Employee> employee = newService.getAllEmployees();
        model.addAttribute("employees", employee);
        return "removePage";
    }

    @DeleteMapping("/manager/removeemployee/{id}")
    public String removeSingleEmployee(@PathVariable("id") Integer id, Model model)
    {
        System.out.println(id);
        newService.removeEmployee(id);
        return "redirect:/manager/removeemployee";
    }


    @GetMapping("home/{name}")
    public String showEmpPage(Model model)
    {
//        const empHomeDetails = newService.getHomeOfEmp();
//        model.addAttribute("empHomeDetails", empHomeDetails);
        return "employee";
    }

    @GetMapping("/apply_leave")
    public String showApplyPage(Model model)
        {
        return "leaveForm";
    }

    @PostMapping("/goto_apply_leave")
    public String showApplyPage(@ModelAttribute("oneEmpModelgg") Employee newEmployee, Model model)
    {
        System.out.println(newEmployee.getId());
        model.addAttribute("oneEmpModelgg", newEmployee);
        return "leaveForm";
    }


    @PostMapping("/")
    public String addEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
        System.out.println("In add Employee form");

        newService.addEmployees(newEmployee);
        return "addEmployee";
    }

    @PostMapping("/apply_leave")
    public String addLeaveForm(@ModelAttribute("newLeave") Leave newLeave)
    {
        System.out.println("In apply_leave form");
        newService.addLeaveForm(newLeave);
        return "redirect:/apply_leave";
    }


    @PostMapping("approveForm/{id}")
    public String approveForm(@PathVariable("id") Integer id)
    {
        newService.approveForm(id);
        return "redirect:/manager";
    }


    @PostMapping("denyForm/{id}")
    public String denyForm(@PathVariable("id") Integer id)
    {
        newService.denyForm(id);
        return "redirect:/manager";
    }

    @GetMapping("view_past_forms/{empId}")
    public String getAllPast(@PathVariable("empId") Integer empId,Model pastModel)
    {
        List<Leave> allLeaveList = newService.getAllPast(empId);
        pastModel.addAttribute("pastModel",allLeaveList);
        return "viewPast";
    }


}
