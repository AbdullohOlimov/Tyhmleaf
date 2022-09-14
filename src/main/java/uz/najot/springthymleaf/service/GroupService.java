package uz.najot.springthymleaf.service;

import uz.najot.springthymleaf.model.GroupModel;
import uz.najot.springthymleaf.model.PageableList;

import java.util.List;

public interface GroupService extends BasicService<GroupModel> {
    PageableList<GroupModel> findAll(Integer page_size, Integer page);

}
