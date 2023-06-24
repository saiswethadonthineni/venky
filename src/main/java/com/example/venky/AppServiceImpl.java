package com.example.venky;

import com.example.venky.serviceinterface.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Component
public class AppServiceImpl implements AppService {
    @Override
    public List<Integer> findNumbers(Integer fromNum, Integer toNum, Integer ch) {

List<Integer> numbers=new ArrayList<>();

            //System.out.println("1.prime\n2.even\n3.odd\n4");

            switch (ch) {
                case 1:
                    numbers.addAll(findAndDisplayPrimes(fromNum, toNum));
                    break;
                case 2:
                    numbers.addAll(displayEvennumbers(fromNum,toNum));

                    break;
                case 3:
                    numbers.addAll(displayOddnumbers(fromNum,toNum));
                    break;
                case 4:
                    System.out.println("bye");
                    break;
            }


        return numbers ;
    }

    @Override
    public List<String> getNumbers(Integer n) {
        List<String>list= new ArrayList<>();
       for(int i=1;i<=n;i++){
           for(int j=1;j<=10;j++){
               /*Map<Integer,Integer> m=new HashMap<>();
               m.put(i,j);
               for(Map.Entry<Integer,Integer>l:m.entrySet()){*/
               String formatted = i + " * " + j + " = " + (i * j);
               list.add(formatted);



           }
       }


        return list;
    }

    @Override
    public List<Integer> getFibonacii(Integer count) {
        int n1=0,n2=1,i,n3;
        List<Integer> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        for(i=2;i<count;i++){
            n3=n1+n2;
            list.add(n3);
            n1=n2;n2=n3;
        }

        return list;
    }


    List<Integer> findAndDisplayPrimes(Integer fromNum, Integer toNum) {
         List<Integer> l = new ArrayList<>();
        for (int i = fromNum; i < toNum; i++) {
            if (isPrime(i) == 2) {
               l.add(i);
            }
        }
        return l;
    }

    public int isPrime(int n) {
        int flag = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                flag += 1;
            }
        }
        return flag;

    }

    public List<Integer> displayEvennumbers(Integer fromNum, Integer toNum) {
        List<Integer> l = new ArrayList<>();
        for (int i = fromNum; i < toNum; i++) {
            if (i % 2 == 0) {
                l.add(i);
                System.out.print(i + " ");
            }
        }

return l;
    }


    public List<Integer> displayOddnumbers(Integer fromNum, Integer toNum) {

        List<Integer> li = new ArrayList<>();
        for (int i = fromNum; i < toNum; i++) {
            if (i % 2 != 0) {
                li.add(i);
                System.out.print(i + " ");
            }
        }
return li;

    }

}
