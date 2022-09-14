package uz.najot.springthymleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.najot.springthymleaf.model.GroupModel;
import uz.najot.springthymleaf.model.PageableList;
import uz.najot.springthymleaf.service.GroupService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @GetMapping
    public String getGroups(@RequestParam(defaultValue = "2") Integer page_size, @RequestParam(defaultValue = "0") Integer page, Model model){
        model.addAttribute("pageable",groupService.findAll(page_size,page));

        return "index";
    }
    @GetMapping("/group-add")
    public String addForm(Model model){
        model.addAttribute("group",new GroupModel());
        return "group-form";
    }
    @PostMapping("/group-add")
    public String saveForm(@ModelAttribute GroupModel groupModel){
        boolean save = groupService.save(groupModel);
        return "redirect:/";
    }
    @GetMapping("/group/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        Optional<GroupModel> byId = groupService.findById(id);
        model.addAttribute("group", byId.orElseGet(GroupModel::new));
        return "group-form";
    }

}
