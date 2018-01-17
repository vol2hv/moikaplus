package ru.vol2hv.moikaback.probe;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vol2hv.moikaback.entity.json.Page;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ListDto<Entity>  {
    private Map<String, List<EntityWithLinks<Entity>>> _embedded;
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
class EntityWithLinks<T>{
//    private Map<String, HrefBig> _links;
    @JsonUnwrapped
    private  T content;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Data1 <T> {
    private Map<String, HrefBig> _links;
    Class<T> var;
}



