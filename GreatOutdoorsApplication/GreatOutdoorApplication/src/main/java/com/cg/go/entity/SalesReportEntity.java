package com.cg.go.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class SalesReportEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALES_SEQ")
    @SequenceGenerator(sequenceName = "salesreport_seq", allocationSize = 1, name = "SALES_SEQ")//1000
    private long salesReportId;
    @OneToOne 
    private ProductEntity proddetails;
    private Integer quantitySold;
    private double totalSale;
	public long getSalesReportId() {
		return salesReportId;
	}
	public void setSalesReportId(long salesReportId) {
		this.salesReportId = salesReportId;
	}
	public ProductEntity getProddetails() {
		return proddetails;
	}
	public void setProddetails(ProductEntity proddetails) {
		this.proddetails = proddetails;
	}
	public Integer getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(Integer quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(double totalSale) {
		this.totalSale = totalSale;
	}
	public SalesReportEntity(long salesReportId, ProductEntity proddetails, Integer quantitySold, double totalSale) {
		super();
		this.salesReportId = salesReportId;
		this.proddetails = proddetails;
		this.quantitySold = quantitySold;
		this.totalSale = totalSale;
	}
	public SalesReportEntity() {
		super();
	}   
}