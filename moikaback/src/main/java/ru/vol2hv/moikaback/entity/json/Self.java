package ru.vol2hv.moikaback.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vol2hv.moikaback.entity.City;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksIn extends City implements Serializable  {
    private Self self;
    private CityLinks city;
    }
