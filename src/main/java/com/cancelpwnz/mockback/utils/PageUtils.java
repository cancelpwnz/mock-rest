package com.cancelpwnz.mockback.utils;

import com.cancelpwnz.mockback.model.request.SortType;
import com.cancelpwnz.mockback.model.response.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils {


    public static <T,R> Page<R> convert(org.springframework.data.domain.Page<T> source, Function<T,R> mapper){
        return new Page<>(source.getTotalElements(),source.getTotalPages(),source.getNumber(),source.getSize(),source.getNumberOfElements(),
                source.getContent().stream().map(mapper).collect(Collectors.toList()));
    }

}
