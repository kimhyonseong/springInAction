package com.example.tacocloud.data;

import com.example.tacocloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbcTemplate.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
  }

  @Override
  public Ingredient findById(String id) {
    return jdbcTemplate.queryForObject("SELECT id, name, type FROM Ingredient WHERE id = ?",
            new RowMapper<Ingredient>() {
              @Override
              public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
                return mapRowToIngredient(rs, rowNum);
              }
            },
            id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbcTemplate.update("insert into Ingredient (id, name, type) values (?,?,?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType()
    );
    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return new Ingredient(
            rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type"))
    );
  }
}
