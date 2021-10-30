package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

 
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() { // 객체가 저장된 컬렉션을 반환하는 findAll()
		// TODO Auto-generated method stub
		return jdbc.query("select id,  name, type from Ingredient",
				this::mapRowToIngredient);
	}

	@Override
	public Ingredient findById(String id) { // 하나의 객체만 반환.
		// TODO Auto-generated method stub		
		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", 
				this::mapRowToIngredient, id);
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(
			rs.getString("id"),
			rs.getString("name"),
			Ingredient.Type.valueOf(rs.getString("type")));
		}

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString()
				);
		
		return ingredient;
		
	}

}


