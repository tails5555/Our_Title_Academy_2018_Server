package io.kang.unit_test.domain_unit;

import io.kang.domain.redis.TodayRank;
import org.junit.Assert;
import org.junit.Test;

public class TodayRankUnitTest {
    private static final long ID = 1L;
    private static final long REQUEST_ID = 1L;
    private static final long SEQUENCE = 1L;
    private static final double SCORE = 1.0;
    private static final double DIFFERENCE = 1.0;

    @Test
    public void id_getter_and_setter_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setId(ID);
        long id = todayRank.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void request_id_getter_and_setter_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setRequestId(REQUEST_ID);
        long requestId = todayRank.getRequestId();
        Assert.assertEquals(requestId, REQUEST_ID);
    }

    @Test
    public void sequence_getter_and_setter_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setSequence(SEQUENCE);
        long sequence = todayRank.getSequence();
        Assert.assertEquals(sequence, SEQUENCE);
    }

    @Test
    public void score_getter_and_setter_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setScore(SCORE);
        double score = todayRank.getScore();
        Assert.assertEquals(score, SCORE, 0);
    }

    @Test
    public void equals_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setId(ID);
        todayRank.setRequestId(REQUEST_ID);
        todayRank.setSequence(SEQUENCE);
        todayRank.setScore(SCORE);
        todayRank.setDifference(DIFFERENCE);

        TodayRank sameTodayRank = new TodayRank(ID, REQUEST_ID, SEQUENCE, SCORE, DIFFERENCE);
        Assert.assertEquals(todayRank, sameTodayRank);
    }

    @Test
    public void to_string_test(){
        TodayRank todayRank = new TodayRank();
        todayRank.setId(ID);
        todayRank.setRequestId(REQUEST_ID);
        todayRank.setSequence(SEQUENCE);
        todayRank.setScore(SCORE);
        todayRank.setDifference(DIFFERENCE);

        String string = todayRank.toString();
        Assert.assertEquals("TodayRank(id=1, sequence=1, requestId=1, score=1.0, difference=1.0)", string);
    }
}
