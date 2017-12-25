package ru.vol2hv.moikaback.entity.json;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class LinksForEntity implements Serializable {
    public Map<String, HrefBig> _links = new HashMap<>();
}