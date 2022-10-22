package org.alaa;

import java.util.List;

public class GetPaired
{
    public static int getCounter(List<Integer> a, int k)
    {
        int counter = 0;

        for (int i = 0; i< a.size(); i++)
        {
            int basNumber= a.get(0);
            a.remove(0);
            for (Integer integer : a)
            {
                int num = basNumber + integer;
                if (num == k)
                    counter++;
            }
        }
        return counter;
    }

}
