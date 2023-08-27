package com.cg.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sales.dto.SalesQtysCategory;
import com.cg.sales.dto.SalesQtysCategoryYear;
import com.cg.sales.dto.SalesSoldCategory;
import com.cg.sales.dto.SalesSoldCategoryYear;
import com.cg.sales.entities.Sale;


@Repository
public interface SalesRepository extends JpaRepository<Sale, Integer> {

	@Query("SELECT s.salesId FROM Sale s")
	//@Query("SELECT s.salesId, p.prodId, c.channelId FROM Sales s JOIN Product p ON p.prodId = s.prodId JOIN Channel c ON c.channelId = s.channelId ORDER BY salesId")
	List<Integer> findAllList();
	
	@Query("SELECT s FROM Sale s JOIN Time t ON s.time.timeId = t.timeId WHERE t.calenderQuarterInt = :quarter")
	List<Sale> getSalesByQuater(@Param("quarter") int quarter);
	
	@Query("SELECT s FROM Sale s JOIN Time t ON s.time.timeId = t.timeId WHERE t.dayIntInMonth = :date")
	List<Sale> getSalesByDate(@Param("date") int date);
	
	@Query("SELECT new com.cg.sales.dto.SalesQtysCategory(p.prodCategory, SUM(s.quantitySold) AS TotalQuantity) FROM Sale s JOIN Product p ON s.product.prodId = p.prodId WHERE p.prodId = s.product.prodId GROUP BY p.prodCategory")
	public List<SalesQtysCategory> getSalesQuantitesByCategory();
	
	@Query("SELECT new com.cg.sales.dto.SalesQtysCategoryYear(p.prodCategory, SUM(s.quantitySold) AS TotalQuantity) FROM Sale s JOIN Product p ON s.product.prodId = p.prodId JOIN Time t ON s.time.timeId = t.timeId WHERE t.calendarYear = :year GROUP BY p.prodCategory")
	public List<SalesQtysCategoryYear> getSalesQuantitiesByCategoryYear(@Param("year") int year);
	
	@Query("SELECT new com.cg.sales.dto.SalesSoldCategory(p.prodCategory, SUM(s.amountSold) AS TotalAmount) FROM Sale s JOIN Product p ON s.product.prodId = p.prodId GROUP BY p.prodCategory")
	public List<SalesSoldCategory> getSalesSoldCategory();
	
	@Query("SELECT new com.cg.sales.dto.SalesSoldCategoryYear(p.prodCategory, SUM(s.amountSold) AS TotalAmount) FROM Sale s JOIN Product p ON s.product.prodId = p.prodId JOIN Time t ON s.time.timeId = t.timeId WHERE t.calendarYear = :year GROUP BY p.prodCategory")
	public List<SalesSoldCategoryYear> getSalesSoldCategoryYear(@Param("year") int year);
}
