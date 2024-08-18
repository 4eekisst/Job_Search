package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public List<CategoryDto> findAllCategories() {
        String sql = "SELECT * FROM Category";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToCategoryDto(rs));
    }

    private CategoryDto mapRowToCategoryDto(ResultSet rs) throws SQLException {
        return CategoryDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .parentId(rs.getInt("parent_id"))
                .build();
    }
}
