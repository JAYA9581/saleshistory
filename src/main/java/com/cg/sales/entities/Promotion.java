package com.cg.sales.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name="promotions")
public class Promotion {

    @Id
    @NotNull(message = "Promo id may not be null")
    private int promoId;
    
    @Column(name="promo_name",nullable = false)
    @NotNull(message = "Promo name may not be null")
    private String promoName;
    
    @Column(name="promo_subcategory",nullable = false)
    @NotNull(message = "Promo subcategory may not be null")
    private String promoSubcategory;
    
    @Column(name="promo_subcategory_id",nullable = false)
    @NotNull(message = "Promo subcategory id may not be null")
    private int promoSubcategoryId;
    
    @Column(name="promo_category",nullable = false)
    @NotNull(message = "Promo category may not be null")
    private String promoCategory;
    
    @Column(name="promo_category_id",nullable = false)
    @NotNull(message = "Promo category id may not be null")
    private int promoCategoryId;
    
    @Column(name="promo_cost",nullable = false)
    @NotNull(message = "Promo cost may not be null")
    private BigDecimal promoCost;
    
    @Column(name="promo_begin_date",nullable = false)
    @NotNull(message = "Promo begin date may not be null")
    private Date promoBeginDate;
    
    @Column(name="promo_end_date",nullable = false)
    @NotNull(message = "Promo End Date may not be null")
    private Date promoEndDate;
    
    @Column(name="promo_total",nullable = false)
    private String promoTotal;
    
    @Column(name="promo_total_id",nullable = false)
    private int promoTotalId;

}