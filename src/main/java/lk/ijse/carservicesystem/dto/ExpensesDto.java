package lk.ijse.carservicesystem.dto;

public class ExpensesDto {
    private String date;
    private String amount;
    private String description;
    private String userId;

    public ExpensesDto() {
    }

    public ExpensesDto(String date, String amount, String description, String userId) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String
    toString() {
        return "ExpensesDto{" +
                "date='" + date + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
