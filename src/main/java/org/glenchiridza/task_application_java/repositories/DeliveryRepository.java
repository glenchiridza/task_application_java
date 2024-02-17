package org.glenchiridza.task_application_java.repositories;

import org.glenchiridza.task_application_java.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    Delivery findDeliveriesById(Long id);

    @Query(value = "SELECT * FROM delivery WHERE is_delivery_pending = TRUE",nativeQuery = true)
    List<Delivery> getAllPendingDeliveries();

    @Query(value = "SELECT * FROM delivery WHERE is_delivery_pending = FALSE",nativeQuery = true)
    List<Delivery> getAllCompletedDeliveries();

    @Query(value = "SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM Delivery d WHERE d.productReference = ?1")
    boolean productReferenceExists(String productReference);
}
