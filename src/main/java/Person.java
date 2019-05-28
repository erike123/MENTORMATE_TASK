public class Person {

    private String name;

    private Double totalSales;

    private Integer salesPeriod;

    private Double experienceMultiplier;

    public String getName() {
        return name;
    }

    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Report report) {
       if (report.getUseExprienceMultiplier()){
           this.score = this.getTotalSales() / this.getSalesPeriod() * this.getExperienceMultiplier();
       }
       else {
           this.score = this.getTotalSales() / this.getSalesPeriod();

       }
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(Integer salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    public Double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public void setExperienceMultiplier(Double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    @Override
    public String toString() {
        return this.getName()+", "+this.getScore();
    }
}
