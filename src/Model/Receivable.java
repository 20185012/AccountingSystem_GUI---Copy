package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Receivable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receivableId;
    private float receivableSum;
    private LocalDate receivableDate;

    @ManyToOne
    Category category;

    public Receivable(int receivableId, float receivableSum, LocalDate receivableDate) {
        this.receivableId = receivableId;
        this.receivableSum = receivableSum;
        this.receivableDate = receivableDate;
    }

    public Receivable(float receivableSum, LocalDate receivableDate) {
        this.receivableSum = receivableSum;
        this.receivableDate = receivableDate;
    }

    public Receivable() {
    }

    public int getReceivableId() {
        return receivableId;
    }

    public void setReceivableId(int receivableId) {
        this.receivableId = receivableId;
    }

    public float getReceivableSum() {
        return receivableSum;
    }

    public void setReceivableSum(float receivableSum) {
        this.receivableSum = receivableSum;
    }

    public LocalDate getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(LocalDate receivableDate) {
        this.receivableDate = receivableDate;
    }
}
