package ru.neoflex.findcreditiduploadpdf.model;


import javax.persistence.*;

@Entity
@Table(name="processed_requests")
public class ProcessedRequests {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "summa")
    private Integer summa;

    @Column(name = "count_month")
    private Integer countMonth;
    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSumma() {
        return summa;
    }

    public void setSumma(Integer summa) {
        this.summa = summa;
    }

    public Integer getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(Integer countMonth) {
        this.countMonth = countMonth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProcessedRequests() {
    }
}