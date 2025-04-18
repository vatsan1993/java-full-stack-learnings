package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "ListItemsWithFragileFilter", 
			query = "SELECT i from Item i where i.fragile = :fragile"),
	@NamedQuery(name = "ListSingleItems",
			query = "SELECT i from Item i where i.unit = 'piece'"
			)
})


@Entity
@Table(name = "itm")
public class Item implements Serializable {
	@Id
	@Column(name = "icode")
	private Integer icode;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "packageDate", nullable = false)
	private LocalDate packageDate;
	@Column(name = "fragile", columnDefinition="BOOLEAN default true")
	private Boolean fragile;
	@Column(name = "unit", nullable = false)
	private String unit;
	@Column(name = "costPrice", nullable = false)
	private Integer costPrice;
	@Column(name = "sellingPrice", nullable = false)
	private Integer sellingPrice;
	
	public Item() {
		super();
	}

	public Item(Integer icode, String title, LocalDate packageDate, Boolean fragile, String unit, Integer costPrice,
			Integer sellingPrice) {
		super();
		this.icode = icode;
		this.title = title;
		this.packageDate = packageDate;
		this.fragile = fragile;
		this.unit = unit;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
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

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
	public String toString() {
		return "Item [icode=" + icode + ", title=" + title + ", packageDate=" + packageDate + ", fragile=" + fragile
				+ ", unit=" + unit + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice + "]";
	}

}
