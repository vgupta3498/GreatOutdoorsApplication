package com.cg.go.entity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class GrowthReportEntity {

		@Id	
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROWTH_SEQ")
	    @SequenceGenerator(sequenceName = "growth_seq", allocationSize = 1, name = "GROWTH_SEQ")//700
		private long growthReportId;
		private LocalDate currentdate;
		private double revenue;
		private double amountChange;
		private double percentageGrowth;
		private String colorCode;		
		public long getGrowthReportId() {
			return growthReportId;
		}
		public void setGrowthReportId(long growthReportId) {
			this.growthReportId = growthReportId;
		}
		public LocalDate getCurrentdate() {
			return currentdate;
		}
		public void setCurrentdate(LocalDate currentdate) {
			this.currentdate = currentdate;
		}
		public double getRevenue() {
			return revenue;
		}
		public void setRevenue(double revenue) {
			this.revenue = revenue;
		}
		public double getAmountChange() {
			return amountChange;
		}
		public void setAmountChange(double amountChange) {
			this.amountChange = amountChange;
		}
		public double getPercentageGrowth() {
			return percentageGrowth;
		}
		public void setPercentageGrowth(double percentageGrowth) {
			this.percentageGrowth = percentageGrowth;
		}
		public String getColorCode() {
			return colorCode;
		}
		public void setColorCode(String colorCode) {
			this.colorCode = colorCode;
		}
		public GrowthReportEntity(long growthReportId, LocalDate currentdate, double revenue, double amountChange,
				double percentageGrowth, String colorCode) {
			super();
			this.growthReportId = growthReportId;
			this.currentdate = currentdate;
			this.revenue = revenue;
			this.amountChange = amountChange;
			this.percentageGrowth = percentageGrowth;
			this.colorCode = colorCode;
		}
		public GrowthReportEntity() {
			super();
		}
		@Override
		public String toString() {
			return "GrowthReportEntity [growthReportId=" + growthReportId + ", currentdate=" + currentdate
					+ ", revenue=" + revenue + ", amountChange=" + amountChange + ", percentageGrowth="
					+ percentageGrowth + ", colorCode=" + colorCode + "]";
		}
		
}
