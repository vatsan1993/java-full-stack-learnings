package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


@Entity
// note: we will generally have a table already available in the database. 
// we have use the same table name.  
@Table(name="items")
public class Item implements Serializable{

	@Id
	@Column(name="icode")
	@NotNull(message="Item code cannot be omitted")
	// note: the NotNull should be from jakarta.validation.constraints.
	// it should not be from org.antir.
	@Min(value =1, message="Item code cannot be zero or negative")
    private Integer icode;
	
	@Column(name="title")
	@NotBlank(message="title cannot be blank")
	@NotNull(message="title cannot be omitted")
	@Size(min= 5, max=20, message="title should be between 5 and 20 characters in length")
    private String title;
	
	@Column(name="packageDate")
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="Package Date cannot be omitted")
	@PastOrPresent(message="Package date cannot be a future date")
    private LocalDate packageDate;
	
	@Column(name="fragile")
    private Boolean fragile;
	
	@Column(name="unit")
	@NotBlank(message="unit cannot be blank")
	@NotNull(message="unit cannot be omitted")
	@Size(min= 2, max=10, message="unit should be between 2 and 10 characters in length")
    private String unit;
	
	@Column(name="costPrice" )
	@NotNull(message="Cost price cannot be omitted")
	@Min(value =1, message="Cost price cannot be zero or negative")
    private Double costPrice;
	
	@Column(name="sellingPrice")
	@NotNull(message="Selling price cannot be omitted")
	@Min(value =1, message="seling price cannot be zero or negative")
    private Double sellingPrice;

    public Item(Integer icode, String title, LocalDate packageDate, Boolean fragile, String unit, Double costPrice, Double sellingPrice) {
        this.icode = icode;
        this.title = title;
        this.packageDate = packageDate;
        this.fragile = fragile;
        this.unit = unit;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }


    public Item() {
    }

    public Integer getIcode() {
        return icode;
    }

    public void setIcode(Integer icode) {
        this.icode = icode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(LocalDate packageDate) {
        this.packageDate = packageDate;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item{");
        sb.append("icode=").append(icode);
        sb.append(", title=").append(title);
        sb.append(", packageDate=").append(packageDate);
        sb.append(", fragile=").append(fragile);
        sb.append(", unit=").append(unit);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append('}');
        return sb.toString();
    }


}
