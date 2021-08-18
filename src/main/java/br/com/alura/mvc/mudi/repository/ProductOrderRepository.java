package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.model.ProductOrder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductOrderRepository  extends JpaRepository<ProductOrder, Long> {

    @Cacheable("ordersByStatus")
    List<ProductOrder> findByStatus(OrderStatus status, Pageable sort);

    @Query("SELECT po FROM ProductOrder po JOIN po.user u WHERE u.username = :username")
    List<ProductOrder> findAllByUser(@Param("username") String username);
    
    @Query("SELECT po FROM ProductOrder po JOIN po.user u WHERE u.username = :username AND po.status = :status")
    List<ProductOrder> findByStatusAndUser(@Param("status") OrderStatus status, @Param("username") String username);
}
