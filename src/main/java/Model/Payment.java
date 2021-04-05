package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int paymentId;
    float paymentSum;
    LocalDate paymentDate;

    @ManyToOne
    Category category;

    public Payment(int paymentId, float paymentSum, LocalDate paymentDate, Category category) {
        this.paymentId = paymentId;
        this.paymentSum = paymentSum;
        this.paymentDate = paymentDate;
        this.category = category;
    }

    public Payment(float paymentSum, LocalDate paymentDate, Category category) {
        this.paymentSum = paymentSum;
        this.paymentDate = paymentDate;
        this.category = category;
    }

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public float getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(float paymentSum) {
        this.paymentSum = paymentSum;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentSum=" + paymentSum +
                ", paymentDate=" + paymentDate +
                ", category=" + category +
                '}';
    }
}
