package ru.vol2hv.moikaback.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityLisnRest implements Serializable {
    private int size;
    private int totalElements;
    private int totalPages;
    private int number;
}