package ru.neoflex.kafkatopostgresql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Заявка на кредит
 */
public class RestReceiveRequest   {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("fio")
    private String fio;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("summa")
    private Integer summa;

    public RestReceiveRequest() {
    }

    @JsonProperty("count_month")
    private Integer countMonth;

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
}
