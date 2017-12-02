package ru.vol2hv.moikaback.probe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vol2hv.moikaback.entity.json.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    public Embedded _embedded;
    private LinksOut _links;
    private Page page;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Embedded{
    private List<CityWithRel> cities = new ArrayList<>();
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CityWithRel{
    private String name;
    private String region;
    private LinksIn _links;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class LinksIn{
    private Href self;
    private Href city;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class LinksOut{
    private HrefBig self;
    private Href profile;
    private Href search;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class  HrefBig {
    String href;
    Boolean templated;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Href {
    String href;
}


