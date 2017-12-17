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
    Map<String, HrefBig> _links = new HashMap<>();
    Page page = new Page();
}
