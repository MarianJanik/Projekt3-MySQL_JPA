package cz.marianjanik.mysql_jpa;

import java.util.Comparator;

public class StandardRateComparator implements Comparator<Rate> {

    @Override
    public int compare(Rate first, Rate second) {
        return (int) ((first.getStandardRate() - second.getStandardRate())*100);
    }
}
