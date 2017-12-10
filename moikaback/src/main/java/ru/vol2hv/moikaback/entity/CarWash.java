package ru.vol2hv.moikaback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarWach implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String  name ;
    private String  description;
    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "CITY_ID_FK"))
    private City city;

/*
должны быть связи
боксы
адрес
руководитель
владелец
сеть
    private List<WashBox> washBoxes = new ArrayList<>();
    private int  idAddr;
    private int  idManager;
    private int idNet;
    private WashAddr facilityAddr;
    */

}
