package com.jdc.project.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Value("${find.project.by.id}")
	String findProjectByIdQuery;
	@Value("${search.all.project}")
	String searchAllProjectQuery;
	@Value("${search.project.by.project.name}")
	String searchProjectByProjectNameQuery;
	@Value("${search.project.by.manager.name}")
	String searchProjectByManagerNameQuery;
	@Value("${search.project.by.date}")
	String searchProjectByDateQuery;
	@Value("${update.project.by.id}")
	String updateProjectById;
	@Value("${delete.project.by.id}")
	String deleteProjectById;
	
	@Autowired
	private ProjectHelper projectHelper;
	
	@Autowired
	private SimpleJdbcInsert projectInsert;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Project> rowMapper = (rs,rowNum) -> {
		var project = new Project();
		project.setId(rs.getInt(1));
		project.setName(rs.getString(2));
		project.setDescription(rs.getString(3));
		project.setManagerId(rs.getInt(4));
		project.setStartDate(rs.getDate(5).toLocalDate());
		project.setMonths(rs.getInt(6));
		project.setManagerName(rs.getString(7));
		project.setManagerLogin(rs.getString(8));
		return project;
	};
	
	public int create(Project project) {
		projectHelper.validate(project);
		return projectInsert.executeAndReturnKey(projectHelper.insertParams(project)).intValue();
	}

	public Project findById(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("projectId", id);
		

		return template.queryForObject(findProjectByIdQuery, params, rowMapper);
	}

	public List<Project> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		
		
		if ( StringUtils.hasLength(project)) {
			return template.query(searchProjectByProjectNameQuery, Map.of("projectName",project.concat("%")), rowMapper);
		} else if ( StringUtils.hasLength(manager) ) {
			return template.query(searchProjectByManagerNameQuery, Map.of("managerName",manager.concat("%")), rowMapper);
		} else if (dateFrom != null ) {
			return template.query(searchProjectByDateQuery, Map.of("startDate",dateFrom.toString().concat("%")), rowMapper);
		} else {
			return template.query(searchAllProjectQuery,rowMapper);
		}
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		var params = new MapSqlParameterSource();
		params.addValue("projectId", id);
		params.addValue("projectName", name);
		params.addValue("description", description);
		params.addValue("startDate", Date.valueOf(startDate));
		params.addValue("months", month);
		return template.update(updateProjectById, params);
	}

	public int deleteById(int id) {
		return template.update(deleteProjectById, Map.of("projectId",id));
	}

}
