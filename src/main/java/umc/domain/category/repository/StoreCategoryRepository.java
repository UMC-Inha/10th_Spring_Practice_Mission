package umc.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.category.entity.mapping.StoreCategory;

import java.util.List;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

    @Query("""
        select sc
        from StoreCategory sc
        join fetch sc.store
        join fetch sc.foodCategory
        where sc.store.id in :storeIds
        """)
    List<StoreCategory> findAllByStoreIdsWithFoodCategory(@Param("storeIds") List<Long> storeIds);
}
