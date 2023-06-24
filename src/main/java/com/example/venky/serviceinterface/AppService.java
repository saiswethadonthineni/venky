package com.example.venky.serviceinterface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppService {



    List<Integer> findNumbers(Integer fromNum, Integer toNum, Integer ch);

    List<String> getNumbers(Integer n);

    List<Integer> getFibonacii(Integer count);
}
