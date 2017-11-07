package ru.vol2hv.moikaback.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfOut implements Serializable  {
    private String href;
    private boolean templated;
    }
