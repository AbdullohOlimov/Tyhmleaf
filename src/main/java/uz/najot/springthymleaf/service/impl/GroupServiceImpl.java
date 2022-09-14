package uz.najot.springthymleaf.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import uz.najot.springthymleaf.model.GroupModel;
import uz.najot.springthymleaf.model.PageableList;
import uz.najot.springthymleaf.service.GroupService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService, RowMapper<GroupModel> {
    private final JdbcTemplate jdbcTemplate;

    public List<GroupModel> findAll() {
        List<GroupModel> models = jdbcTemplate.query("select id,name from groups order by id", this);
        return models;
    }

    @Override
    public GroupModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GroupModel(rs.getInt(1),rs.getString(2));
    }

    @Override
    public boolean save(GroupModel groupModel) {
        int update;
        if (groupModel.getId()!=null){
             update = jdbcTemplate.update("update groups set  name = ? where id = ?", groupModel.getName(), groupModel.getId());
        }else {
             update = jdbcTemplate.update("insert into groups (name) values (?)", groupModel.getName());
        }

        return update>0;
    }

    @Override
    public Optional<GroupModel> findById(Integer id) {
        GroupModel groupModel = jdbcTemplate.queryForObject("select id,name from groups where id = ?", this, id);

        return Optional.ofNullable(groupModel);
    }

    @Override
    public PageableList<GroupModel> findAll(Integer page_size, Integer page) {
        Integer count = jdbcTemplate.queryForObject("select count(*) from groups where  is_active = true", Integer.class); // umumi soni
        List<GroupModel> models = jdbcTemplate.query("select id,name from groups  offset ? limit ?" +
                "", this,((page)*page_size),page_size);

        return new PageableList<>(count/page_size,models);
    }
}
