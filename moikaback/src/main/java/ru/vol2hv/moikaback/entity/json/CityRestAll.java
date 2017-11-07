package ru.vol2hv.moikaback.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRestAll implements Serializable {
    private List<CityRest> cities;
    private  LinksOut _links;
    private Page page;
}
