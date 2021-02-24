package com.befree.adapter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //origem e destino
    public static  <O, D>D parseObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }

    public static <O,D> List<D> parseListObjects(List<O>origin,Class<D> destination){
        List<D> destinationObjects =new ArrayList<>();
        //para cada objeto de origem dentro da lista, vamos adicionálo no destino
        for (Object o: origin){
            destinationObjects.add(mapper.map(o,destination));
        }
        return destinationObjects;
    }
}
