package ru.vol2hv.moikaback.entity.json;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ListEntityDto<T> {
    Map<String,List<T>> _embedded = new HashMap<>();
    //    public class City extends LinksForEntity implements Serializable {
    //        @Id
    //        @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //        private Long id;
    //        private String name;
    //        private String region;

    //        public Map<String, HrefBig> _links = new HashMap<>();
    //      }
    Map<String, HrefBig> _links = new HashMap<>();
    //    public class  HrefBig {
    //        String href;
    //        Boolean templated;
    //    }

    Page page = new Page();
}
