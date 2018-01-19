package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vol2hv.moikaback.entity.City;
import ru.vol2hv.moikaback.entity.json.Page;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.List;
import java.util.Map;
// Нужные
@Data
@AllArgsConstructor
@NoArgsConstructor
class ListFullDto<EntityDto>  {
    private Map<String, List<EntityDto>> _embedded;
    private Map<String, HrefBig> _links;
    private Page page;
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
class LinksEntity {
    private Map<String, HrefBig> _links;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class EntityDto<Entity> extends LinksEntity {
    @JsonUnwrapped
    @XmlAnyElement
    private  Entity content;
}
//**********************************************
// Желательно найти дрогое с обобщениями
@Data
@AllArgsConstructor
@NoArgsConstructor
class CityDto extends City {
    private Map<String, HrefBig> _links;
}
//**********************************************
// поиски экспиременты
//так не работает
//class EntityDto<Entity> {
//    @JsonUnwrapped
//    @XmlAnyElement
//    private  Entity content;
//    private Map<String, HrefBig> _links = new HashMap<>();
//    EntityDto(){
//        this.content = null;
////        this._links = null;
//    }
//}
//class ResourcesOtherLinks<T> extends Resource<T> {
//    private Map<String, String> _links = new HashMap<>();
//    ResourcesOtherLinks(){
//
//    }
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Data1 <T> {
    private Map<String, HrefBig> _links;
    Class<T> var;
}



