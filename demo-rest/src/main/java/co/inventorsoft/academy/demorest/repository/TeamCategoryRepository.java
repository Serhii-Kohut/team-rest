package co.inventorsoft.academy.demorest.repository;

import co.inventorsoft.academy.demorest.entity.TeamCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamCategoryRepository extends JpaRepository<TeamCategory, Long> {

}
